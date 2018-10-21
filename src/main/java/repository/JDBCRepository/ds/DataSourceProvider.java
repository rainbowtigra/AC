/*
package repository.JDBCRepository.ds;

import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

*/
/**
 * Class for creating and  maintaining connection with database.
 *//*

public class DataSourceProvider {

    private DataSource datasource;
    private static final Logger LOGGER = Logger.getLogger(DataSourceProvider.class);

    */
/**
     * Constructs a {@code DataSourceProvider} and
     * sets pool properties for datasource.
     *//*

    public DataSourceProvider() {
        datasource = new DataSource();
        AppPropertiesHolder appPropertiesHolder = new AppPropertiesHolder();
        datasource.setPoolProperties(appPropertiesHolder.getPoolProperties());
    }

    public void close() {
        datasource.purge();
    }

    public Connection getConnection() {
        try {
            return datasource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.fatal("SQLException in method 'getConnection' in class DataSourceProvider");
        }
        return null;
    }
}*/
