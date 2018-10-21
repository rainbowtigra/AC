/*
package repository.JDBCRepository.ds;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

*/
/**
 * The {@code DbUtil} class is responsible for creation and managing connection to DB.
 *//*

public class DbUtil {

    //private static final Logger LOGGER = LogManager.getLogger(DbUtil.class);

    private static DataSource dataSource;

    static {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://localhost:3306/airlinedb?useSSL=false");
        p.setDriverClassName("com.mysql.jdbc.Driver");
        p.setUsername("root");
        p.setPassword("1111");
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(100);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        */
/*p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
                        + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer;"
                        + "org.apache.tomcat.jdbc.pool.interceptor.ResetAbandonedTimer");*//*



        dataSource = new DataSource();
        dataSource.setPoolProperties(p);

    }

    */
/**
     * Gets connection java.sql.Connection from connection pool ({@code DataSource} class)
     *
     * @return opened connection
     *//*

    public static synchronized Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();

        } catch (SQLException e) {

            //LOGGER.error(e.getMessage());
        }
        return connection;
    }

    */
/**
     * Receives connection and closes it
     *
     * @param connection java.sql.Connection
     *//*

    public static void closeConnection(Connection connection) {
        try {
            connection.close();

        } catch (SQLException e) {

            //LOGGER.error(e.getMessage());
        }
    }

    */
/**
     * Receives connection and starts transaction by setting autocommit to false
     *
     * @param connection java.sql.Connection
     *//*

    public static void startTransaction(Connection connection) {
        try {

            connection.setAutoCommit(false);

        } catch (SQLException e) {

            //LOGGER.error(e.getMessage());
        }
    }

    */
/**
     * Receives connection and commits transaction
     *
     * @param connection java.sql.Connection
     *//*

    public static void commitTransaction(Connection connection) {

        try {
            connection.commit();
        } catch (SQLException e) {

            //LOGGER.error(e.getMessage());
            rollbackTransaction(connection);
        }
    }

    */
/**
     * Receives connection and rollbacks transaction
     *
     * @param connection java.sql.Connection
     *//*

    public static void rollbackTransaction(Connection connection) {
        try {
            connection.rollback();

        } catch (SQLException e) {

            //LOGGER.error(e.getMessage());
        }
    }

}
*/
