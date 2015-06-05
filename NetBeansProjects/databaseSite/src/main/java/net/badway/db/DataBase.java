/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db;

import net.badway.db.test.User;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jonathan
 */
public class DataBase {
    private String driverClass;
    private DriverManager driverManager;
    private Connection connection;
    private String url;
    private String user;
    private String password;
    private Statement statement;
    private HashMap<Class<?>, ObjectConvertor> cache = new HashMap<>();

    public DataBase(String driverClass) throws ClassNotFoundException {
        this.driverClass = driverClass;
        Class.forName(driverClass);
        
    }
    
    public Connection getConnecion() throws SQLException{
        if(connection == null || connection.isClosed() ){
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    public DataBase(String driverClass, String url, String user, String password) throws ClassNotFoundException {
        this(driverClass);
        this.url = url;
        this.user = user;
        this.password = password;
    }
    
    public Statement getStatment() throws SQLException{
        if(statement == null || statement.isClosed()){
            statement = getConnecion().createStatement();
        }
        return statement;
    }
    public void closeStatement() throws SQLException{
        statement.close();
    }
    
    public void closeConnection() throws SQLException{
        connection.close();
    }
    
    public ResultSet executeQuery(String execute) throws SQLException{
        return getStatment().executeQuery(execute);
    }
    
  
    public ResultSet executeQuesry(String sql, Object ... param) throws SQLException{
        PreparedStatement preparedStatement = getPreparedStatement(sql);
        for(int i = 0 ; i < param.length ; i++){
            preparedStatement.setObject(i+1, param[i]);
        }
        return preparedStatement.executeQuery();
    }
    
    public int executeUpdate(String sql, Object ... param) throws SQLException{
        PreparedStatement preparedStatement = getPreparedStatement(sql);
        for(int i = 0 ; i < param.length ; i++){
            preparedStatement.setObject(i+1, param[i]);
        }
        return preparedStatement.executeUpdate();
    }
    
    public int createTable(Class<?> clazz) throws SQLException, IncompleteObjectException{
        ObjectConvertor get = cache.get(clazz);
        if(get==null){
            get = new ObjectConvertor(clazz,cache);
        }
        List<ObjectConvertor> creates = get.getListCreate();
        for(ObjectConvertor occ : creates){
            executeUpdate(occ.getCreateTable());
        }
        String createTable = get.getCreateTable();
        return executeUpdate(createTable);
    }
    
    public int createDataBase(String database) throws SQLException{
        return executeUpdate("create database IF NOT EXISTS "+database);
    }

    public void use(String site_perso) throws SQLException {
        executeUpdate("use "+site_perso);
    }

    PreparedStatement getPreparedStatement(String sql) throws SQLException {
        if(statement!=null && !statement.isClosed()){
            statement.close();
        }
        statement=getConnecion().prepareStatement(sql);
        return (PreparedStatement)  statement;
    }

    public int save(Object p) throws IllegalArgumentException, IllegalAccessException, SQLException, IncompleteObjectException, InvocationTargetException {
        ObjectConvertor get = ckeckCache(p.getClass());
        PreparedStatement prepareInsert = get.prepareInsert(p,this);
        return prepareInsert.executeUpdate();
        
    }


    boolean exists(Object p) throws IncompleteObjectException, SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return exists( p,p.getClass()) ;
    }

    private  boolean exists(Object p, Class<? extends Object> aClass) throws IncompleteObjectException, SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ObjectConvertor oc = ckeckCache(aClass);
        PreparedStatement prepareSelectId = oc.prepareSelectId(p, this);
       
        ResultSet resultSet = prepareSelectId.executeQuery();
        return resultSet.next();
    }

    public <T,U>   T select(Class<T> aClass, U id) throws IncompleteObjectException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ObjectConvertor oc = ckeckCache(aClass);
        T newInstance;
        try (PreparedStatement p = oc.prepareSelect(id, this)) {
            ResultSet executeQuery = p.executeQuery();
            newInstance = null;
            if(executeQuery.next()){
                newInstance = aClass.newInstance();
                oc.fillObject(newInstance, executeQuery);
                
            }
        }
        return newInstance;
    }

    public int dropTable(Class<?> aClass) throws SQLException, IncompleteObjectException {
        ObjectConvertor ckeckCache = ckeckCache(aClass);
        Statement statment = getStatment();
        return statment.executeUpdate("DROP TABLE IF EXISTS "+ckeckCache.getTableName());
    }

    private ObjectConvertor ckeckCache(Class<? extends Object> aClass) throws IncompleteObjectException {
        return ObjectConvertor.ckeckCache(aClass, cache);
    }

    void update(User p) {
    }
    
    
    
    
    
    
    
}
