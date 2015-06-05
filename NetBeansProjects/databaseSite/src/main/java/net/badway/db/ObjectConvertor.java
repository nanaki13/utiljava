/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db;

import net.badway.db.annotation.ManyToOne;
import net.badway.db.annotation.SqlField;
import net.badway.db.annotation.Id;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.badway.db.sql.Jointure;
import net.badway.db.sql.Select;
import net.badway.db.sql.SqlCol;

/**
 *
 * @author jonathan
 */
public class ObjectConvertor implements ProvierConvertor{

    public static ObjectConvertor ckeckCache(Class<? extends Object> aClass, Map<Class<?>, ObjectConvertor> cache) throws IncompleteObjectException {
        ObjectConvertor get;
        get = cache.get(aClass);
        if (get == null) {
            get = new ObjectConvertor(aClass, cache);
        }
        return get;
    }
    private LinkedList<SqlCol> sqlFieldsString;

    public String getIdFieldName() {
        return idField.getFieldName();
    }

   
    private ObjectFieldAndMethod idField;
    private List<ObjectFieldAndMethod> manyToOnes = new LinkedList<>();
    private Map<String, ObjectFieldAndMethod> fieldsSql = new ArrayMap<>();
    private Class<?> clazz;
    private final Map<Class<?>, ObjectConvertor> cache;

    public ObjectConvertor(Class<?> clazz, Map<Class<?>, ObjectConvertor> cache) throws IncompleteObjectException {
        Field[] fields = clazz.getDeclaredFields();
        this.cache = cache;
        Method[] objectGetters = Reflexivite.getObjectGetters(clazz);
        Method[] objectSetters = Reflexivite.getObjectSetters(clazz);
        this.clazz = clazz;
        for (Field f : fields) {
            List<Annotation> anotConcernigUs = processAnnotaion(f);
            if (!anotConcernigUs.isEmpty()) {
                Method getter = findLike(f.getName(), objectGetters);
                if (getter == null) {
                    throw new IncompleteObjectException("no getter found for property " + f.getName() + " class " + clazz.getSimpleName());
                }
                Method setter = findLike(f.getName(), objectSetters);
                if (setter == null) {
                    throw new IncompleteObjectException("no setter found for property " + f.getName() + " class " + clazz.getSimpleName());
                }
                ObjectFieldAndMethod ofam = new ObjectFieldAndMethod(anotConcernigUs, f, getter, setter,this);
                fieldsSql.put(ofam.getFieldName(), ofam);
                if(ofam.isIdField()){
                    idField = ofam;
                }
                if(ofam.isManyToOne()){
                    manyToOnes.add(ofam);
                }
            

            }
            sqlFieldsString =new LinkedList<>();
        for(ObjectFieldAndMethod ofam : fieldsSql.values()){
            String teble =  getTableName();
            sqlFieldsString.add(new SqlCol(teble,  ofam.getFieldName()));
            if(ofam.isManyToOne()){
                sqlFieldsString.addAll(ofam.getListStringSqlFields());
            }
        }

     
            cache.put(clazz, this);
        }
    }

    public String getCreateTable() throws IncompleteObjectException {
        StringBuilder builder = new StringBuilder();
        boolean autoIncrement = false;
        if (idField != null) {
           autoIncrement = idField.isAutoincremant();
        }
        builder.append("CREATE TABLE IF NOT EXISTS ").append(getTableName()).append(" (\n");
        builder.append("    ").append(idField.getField().getName()).append(" ")
                .append(idField.getSqlType())
                .append(" NOT NULL ");
        if (autoIncrement) {
            builder.append("AUTO_INCREMENT ");
        }
        builder.append("PRIMARY KEY ");
        ObjectFieldAndMethod f;
        StringBuilder edding = null;
        for (Map.Entry<String, ObjectFieldAndMethod> e : fieldsSql.entrySet()) {
            f = e.getValue();        
            if (f.isManyToOne()) {
                edding = new StringBuilder();
                Class<?> type = f.getField().getType();
                edding.append(",\n\tFOREIGN KEY (").append(f.getFieldName()).append(") REFERENCES ").append(type.getSimpleName()).append("(").append(f.getForeignKeyName()).append(")");
                builder.append(",\n    ").append(f.getFieldName()).append(" ").append(f.getTypeForeignKey());
            } else {
                builder.append(",\n    ").append(f.getFieldName()).append(" ").append(f.getSqlType());
            }

        }
        if (edding != null) {
            builder.append(edding);
        }
        builder.append("\n);");
        return builder.toString();
    }

    public String getTableName() {
        return clazz.getSimpleName();
    }

    PreparedStatement prepareInsert(Object p, DataBase db) throws SQLException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IncompleteObjectException {

        StringBuilder value = new StringBuilder();
        StringBuilder label = new StringBuilder();
        int size = fieldsSql.size();
        int delta = 0;
        if (idField != null) {
            delta++;
            label.append(idField.getField().getName());
            value.append("?");
            if (!fieldsSql.isEmpty()) {
                label.append(',');
                value.append(',');
            }
        }

        int count = 0;
        List<Pair<Integer, ObjectFieldAndMethod>> l = new LinkedList<>();
        for (ObjectFieldAndMethod f : fieldsSql.values()) {
            l.add(new Pair<>(count + delta, f));
            label.append(f.getFieldName());
            value.append("?");
            count++;
            if (count != size) {
                label.append(',');
                value.append(',');
            }
        }
        String insert = "INSERT INTO " + getTableName() + " (" + label + ")VALUES" + " (" + value + ")";
        PreparedStatement preparedStatement = db.getPreparedStatement(insert);
        fillPreparedStatement(preparedStatement, p, l);

        return preparedStatement;

    }

    private void fillPreparedStatement(PreparedStatement preparedStatement, Object p, List<Pair<Integer, ObjectFieldAndMethod>> mapping) throws SQLException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {

        if (idField != null) {
            preparedStatement.setObject(1, idField.getGetter().invoke(p));
        }
        for (Pair<Integer, ObjectFieldAndMethod> pair : mapping) {
            preparedStatement.setObject(pair.t0 + 1, pair.t1.getGetter().invoke(p));
        }
    }

    private Method findLike(String name, Method[] objectGetters) {
        for (Method m : objectGetters) {
            if (m.getName().toLowerCase().contains(name)) {
                return m;
            }
        }
        return null;

    }

    PreparedStatement prepareSelectId(Object p, DataBase aThis) throws IncompleteObjectException, SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (idField == null) {
            throw new IncompleteObjectException(getTableName() + " don't have id field");
        }
        PreparedStatement preparedStatement = aThis.getPreparedStatement("SELECT " + idField.getField().getName() + " FROM " + getTableName() + " WHERE " + idField.getField().getName() + "= ?");
        preparedStatement.setObject(1, idField.getGetter().invoke(p));
        return preparedStatement;

    }

    <U> PreparedStatement prepareSelect(U id, DataBase aThis) throws SQLException, IncompleteObjectException {
        if (idField == null) {
            throw new IncompleteObjectException(getTableName() + " don't have id field");
        }

        Select s = new Select(sqlFieldsString, getTableName());
        s.setWhereClause(idField.getField().getName() + " = ?");
        
        if (!manyToOnes.isEmpty()) {
            for (ObjectFieldAndMethod manyToOne : manyToOnes) {

                Jointure j = new Jointure("JOIN", manyToOne.getTableJointure(),  manyToOne.getTableJointure() + "." + manyToOne.getForeignKeyName() + " = " + getTableName() + "." + manyToOne.getFieldName());
                s.add(j);
            }
        }
        String se = s.toString();
        System.out.println(se);
        PreparedStatement preparedStatement = aThis.getPreparedStatement(se);
        preparedStatement.setObject(1, id);
        return preparedStatement;
    }

    <T> void fillObject(T newInstance, ResultSet executeQuery) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String name ;
        Object object ;
      
        for (ObjectFieldAndMethod ofm : fieldsSql.values()) {
            if(ofm.isSqlType() || ofm.isIdField()){
                name = ofm.getFieldName();
                object = executeQuery.getObject(name);
                ofm.getSetter().invoke(newInstance, object);
            }
            
        }
    }

   

    private static String findId(Class<?> type) {
        for (Field f : type.getDeclaredFields()) {
            if (f.getAnnotation(Id.class) != null) {
                return f.getName();
            }
        }
        return null;

    }

    public List<ObjectConvertor> getListCreate() throws IncompleteObjectException {
        List<ObjectConvertor> l = new LinkedList();
        getListCreate(l);
        return l;
    }

    private void getListCreate(List<ObjectConvertor> l) throws IncompleteObjectException {
        for (ObjectFieldAndMethod f : manyToOnes) {
            ObjectConvertor ckeckCache = ckeckCache(f.getField().getType(), cache);
            ckeckCache.getListCreate(l);
            l.add(ckeckCache);
        }

    }

   

    public String getTypeId() {
        return idField.getSqlType();
    }

    private List<Annotation> processAnnotaion(Field field) {
        List<Annotation> l = new LinkedList<>();
        Annotation annotation = field.getAnnotation(Id.class);
        if (annotation != null) {
            l.add(annotation);

        }
        annotation = field.getAnnotation(ManyToOne.class);
        if (annotation != null) {
            l.add(annotation);
        }
        annotation = field.getAnnotation(SqlField.class);
        if (annotation != null) {
            l.add(annotation);
        }
        return l;

    }



    LinkedList<SqlCol> getListStringField() {
        return sqlFieldsString;
    }

    @Override
    public ObjectConvertor getObjectFieldAndMethod(Class<?> aClass) {
        try {
            return ckeckCache(aClass, cache);
        } catch (IncompleteObjectException ex) {
            Logger.getLogger(ObjectConvertor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    

    private static class Pair<T0, T1> {

        T0 t0;
        T1 t1;

        public Pair() {
        }

        private Pair(T0 count, T1 f) {
            t0 = count;
            t1 = f;
        }
    }

}
