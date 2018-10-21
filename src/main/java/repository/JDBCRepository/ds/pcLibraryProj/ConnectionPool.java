package repository.JDBCRepository.ds.pcLibraryProj;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class ConnectionPool {

    private static final Logger logger = Logger.getLogger(ConnectionPool.class);


    //private static final String DB_RESOURCE_BUNDLE = "db";
    private static final String SQL_LOGIN = "root";
    private static final String SQL_PASSWORD = "1111";
    private static final String URL = "jdbc:mysql://localhost:3306/airlinedb?useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String INIT_COUNT_CONNECTIONS = "7";


    private Queue<Connection> freeConnections = new LinkedList<>();
    private List<Connection> usedConnections = new LinkedList<>();



    public ConnectionPool(){
        try{
            //ResourceBundle rb = ResourceBundle.getBundle(DB_RESOURCE_BUNDLE);
            int initCount = Integer.parseInt(INIT_COUNT_CONNECTIONS);
            for (int i = 0; i < initCount; i++){
                Connection con = getConnection();
                if (con != null){
                    freeConnections.offer(con);
                }
            }
        } catch (ConnectionPoolException ex){
            logger.warn(ex);
        }

    }

    public synchronized Connection retrieve() throws ConnectionPoolException{
        Connection newConn = null;
        if (freeConnections.size() == 0){
            newConn = getConnection();
        } else {
            newConn = freeConnections.poll();
        }
        usedConnections.add(newConn);
        if (newConn == null){
            throw new ConnectionPoolException("Error retrieve Connection");
        }
        return newConn;
    }

    public synchronized void putback(Connection connection) throws ConnectionPoolException{
        if (connection!=null){
            if (usedConnections.remove(connection)){
                freeConnections.offer(connection);
            } else {
                throw new ConnectionPoolException("Error put back Connection");
            }
        }
    }

    public int getFreeConnectionsCount() {
        return freeConnections.size();
    }

    private Connection getConnection() throws ConnectionPoolException{
        //ResourceBundle rb = ResourceBundle.getBundle(DB_RESOURCE_BUNDLE);
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, SQL_LOGIN, SQL_PASSWORD);
        } catch (ClassNotFoundException ex){
            throw new ConnectionPoolException("Driver Class not found", ex);
        }
        catch (SQLException ex) {
            throw new ConnectionPoolException("Error get Connection", ex);
        }
        return connection;
    }
}
