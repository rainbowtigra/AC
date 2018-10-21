/*
package repository.JDBCRepository.ds;

import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.io.IOException;
import java.util.Properties;

*/
/**
 * Class set properties for pool connection.
 *//*

public class AppPropertiesHolder {
    private static final Logger LOGGER = Logger.getLogger(AppPropertiesHolder.class);
    private PoolProperties poolProperties = new PoolProperties();

    */
/**
     * Constructor loads properties for pool connection from property file during initialization.
     *//*

    public AppPropertiesHolder() {
        loadProperties();
    }

    */
/**
     * Method creates instance of class Properties
     * and sets properties of the pool connection
     * which were received from application properties file.
     *//*

    private void loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(AppPropertiesHolder.class.getClassLoader().getResourceAsStream("application.properties"));
            poolProperties.setUrl(properties.getProperty("db.url"));
            poolProperties.setDriverClassName(properties.getProperty("db.driver"));
            poolProperties.setUsername(properties.getProperty("db.user"));
            poolProperties.setPassword(properties.getProperty("db.password"));
            poolProperties.setJmxEnabled(Boolean.parseBoolean(properties.getProperty("db.jmxEnabled")));
            poolProperties.setTestWhileIdle(Boolean.parseBoolean(properties.getProperty("db.testWhileIdle")));
            poolProperties.setTestOnBorrow(Boolean.parseBoolean(properties.getProperty("db.testOnBorrow")));
            poolProperties.setValidationQuery(properties.getProperty("db.validationQuery"));
            poolProperties.setTestOnReturn(Boolean.parseBoolean(properties.getProperty("db.testOnReturn")));
            poolProperties.setValidationInterval(Long.parseLong(properties.getProperty("db.validationInterval")));
            poolProperties.setTimeBetweenEvictionRunsMillis(Integer.parseInt(properties.getProperty("db.timeBetweenEvictionRunsMillis")));
            poolProperties.setMaxActive(Integer.parseInt(properties.getProperty("db.maxActive")));
            poolProperties.setInitialSize(Integer.parseInt(properties.getProperty("db.initialSize")));
            poolProperties.setMaxWait(Integer.parseInt(properties.getProperty("db.maxWait")));
            poolProperties.setRemoveAbandonedTimeout(Integer.parseInt(properties.getProperty("db.removeAbandonedTimeout")));
            poolProperties.setMinEvictableIdleTimeMillis(Integer.parseInt(properties.getProperty("db.minEvictableIdleTimeMillis")));
            poolProperties.setMinIdle(Integer.parseInt(properties.getProperty("db.minIdle")));
            poolProperties.setLogAbandoned(Boolean.parseBoolean(properties.getProperty("db.logAbandoned")));
            poolProperties.setRemoveAbandoned(Boolean.parseBoolean(properties.getProperty("db.removeAbandoned")));
            poolProperties.setJdbcInterceptors(properties.getProperty("db.jdbcInterceptors"));
        } catch (IOException io) {
            io.printStackTrace();
            LOGGER.fatal("IOException im method 'loadProperties' in class AppPropertiesHolder");
        }
    }

    public PoolProperties getPoolProperties() {
        return poolProperties;
    }

    public void setPoolProperties(PoolProperties poolProperties) {
        this.poolProperties = poolProperties;
    }
}*/
