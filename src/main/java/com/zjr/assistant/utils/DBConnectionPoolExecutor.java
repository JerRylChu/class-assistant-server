package com.zjr.assistant.utils;


import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class DBConnectionPoolExecutor {
    private static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://42.192.230.241:3306/assistant?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&useAffectedRows=true";
    private static final String user = "root";
    private static final String password = "ZJR199925";

    private int corePoolSize;
    private int maximumPoolSize;
    private BlockingQueue<Connection> busyQueue;
    private BlockingQueue<Connection> freeQueue;

    public DBConnectionPoolExecutor(int corePoolSize, int maximumPoolSize){
        if(corePoolSize <= 0 || maximumPoolSize <= 0 || maximumPoolSize < corePoolSize){
            throw new IllegalArgumentException();
        }
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        busyQueue = new LinkedBlockingQueue<>();
        freeQueue = new LinkedBlockingQueue<>(corePoolSize);
    }

    public Connection getConnection() throws SQLException {
        if(busyQueue.size() < corePoolSize ){
            if(freeQueue.size() == 0){
                Connection connection = DriverManager.getConnection(url, user, password);
                busyQueue.offer(connection);
                return connection;
            }
            return freeQueue.poll();
        }else{
            if(busyQueue.size() < maximumPoolSize){
                Connection connection = DriverManager.getConnection(url, user, password);
                busyQueue.offer(connection);
                return connection;
            }else {
                try {
                    return freeQueue.poll(5000, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    public void releaseConnection(Connection connection){
        if(connection != null){
            busyQueue.remove(connection);
            freeQueue.offer(connection);
        }
    }

    public void check(){
        for(int i = 0; i < freeQueue.size(); i++){
            Connection connection = freeQueue.poll();
            try {
                if(!connection.isValid(2000)){
                    connection = DriverManager.getConnection(url,user,password);
                }
                freeQueue.offer(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
