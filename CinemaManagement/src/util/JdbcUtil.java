package util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 高杰
 * @name JDBC数据库访问类
 * @date 2022-08-30
 * @description 数据库链接，初始化，增删改查等等
 */
public class JdbcUtil {
    // jdbc:使用jdbc连接技术
    // mysql://localhost:3306 使用的是MySQL数据库协议，访问的是本地计算机3306端口
    private static String url = "jdbc:mysql://localhost:3306/database?serverTimezone=Asia/Shanghai";

    private static String username = "root";

    private static String password = "root";

    // 加载驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("驱动程序未加载");
        }
    }

    /**
     * 创建 PreparedStatement
     *
     * @param conn
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    private static PreparedStatement createPreparedStatement(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(sql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        }
        return ps;
    }

    /**
     * 关闭连接、执行器、结果集
     *
     * @param closeables
     */
    private static void close(AutoCloseable... closeables) {
        if (closeables != null && closeables.length > 0) {
            for (AutoCloseable ac : closeables) {
                if (ac != null) {
                    try {
                        ac.close();
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    /**
     * 利用反射创建实例
     *
     * @param clazz
     * @param rs
     * @param <T>
     * @return
     * @throws Exception
     */
    private static <T> T createInstance(Class<T> clazz, ResultSet rs) throws Exception {
        Constructor<T> c = clazz.getConstructor();//获取无参构造
        T t = c.newInstance();//创建对象
        Field[] fields = clazz.getDeclaredFields(); //获取类中定义的字段
        for (Field field : fields) {
            String fieldName = field.getName();
            //setId => set id => set + I + d
            String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method m = clazz.getDeclaredMethod(methodName, field.getType());
            try {
                Object value = rs.getObject(fieldName, field.getType());
                m.invoke(t, value);
            } catch (Exception e) {
            }
        }
        return t;
    }

    /**
     * 查询
     *
     * @param sql
     * @param clazz
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> query(String sql, Class<T> clazz, Object... params) {
        List<T> dataList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = createPreparedStatement(conn, sql, params);
            rs = ps.executeQuery();
            while (rs.next()) {
//                T t = createInstance(clazz, rs);
                // 获取 blob对象
                Blob inBlob = rs.getBlob(1);
                // 获取二进制流对象
                InputStream is = inBlob.getBinaryStream();
                // 带缓冲区的流对象
                BufferedInputStream bis = new BufferedInputStream(is);
                // 一次性全部读到buff中
                byte[] buff = new byte[(int) inBlob.length()];
                while (-1 != (bis.read(buff, 0, buff.length))) {
                    ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buff));
                    // 读出对象
                    T t = (T) in.readObject();
                    dataList.add(t);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, conn);
        }
        return dataList;
    }

    /**
     * 更新数据库
     *
     * @param sql
     * @param params
     * @return
     */
    public static int update(String sql, Object... params) {
        int result = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            ps = createPreparedStatement(conn, sql, params);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(ps, conn);
        }
        return result;
    }

}
