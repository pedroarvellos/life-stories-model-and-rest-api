package br.com.lifestories.model;

import java.sql.Connection;
import org.postgresql.ds.PGPoolingDataSource;

public class ConnectionManager {

    private PGPoolingDataSource dataSource;

    public Connection getConnection() throws Exception {
        Connection conn = dataSource.getConnection();
        conn.setAutoCommit(false);
        return conn;
    }
    //Inicio Singleton

    private ConnectionManager() {
        dataSource = new PGPoolingDataSource();
        dataSource.setDataSourceName("d4tsgf5l53s0a9");
        dataSource.setServerName("ec2-107-22-171-11.compute-1.amazonaws.com");
        dataSource.setDatabaseName("d4tsgf5l53s0a9");
        dataSource.setUser("szdczoibhjoaqx");
        dataSource.setPassword("47eb178e294faf8ffe749a48f0e119b84f20a84028c6dff6a639d64bd0831923");
        dataSource.setMaxConnections(30);
        dataSource.setInitialConnections(10);
    }
//    private ConnectionManager() {
//        dataSource = new PGPoolingDataSource();
//        dataSource.setDataSourceName("lifestories");
//        dataSource.setServerName("localhost");
//        dataSource.setPortNumber(5432);
//        dataSource.setDatabaseName("lifestories");
//        dataSource.setUser("postgres");
//        dataSource.setPassword("postgres");
//        dataSource.setMaxConnections(30);
//        dataSource.setInitialConnections(10);
//    }
    private static ConnectionManager instance;

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }
    //Fim Singleton
}
