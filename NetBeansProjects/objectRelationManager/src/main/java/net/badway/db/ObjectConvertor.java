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
import net.badway.db.sql.Insert;
import net.badway.db.sql.Jointure;
import net.badway.db.sql.Select;
import net.badway.db.sql.SqlCol;
import net.badway.db.sql.Update;

/**
 *
 * @author jonathan
 */
public class ObjectConvertor implements ProvierConvertor {

    public static ObjectConvertor ckeckCache(Class<? extends Object> aClass, Map<Class<?>, ObjectConvertor> cache) throws IncompleteObjectException {
        ObjectConvertor get;
        get = cache.get(aClass);
        if (get == null) {
            get = new ObjectConvertor(aClass, cache);
        }
        return get;
    }
    private LinkedList<SqlCol> sqlFieldsString;
    private LinkedList<SqlCol> sqlFieldsStringNonDistant;
    private String updateUnitaireString;
    private String selcectUnitaire;
    private String insertUnitaire;

    public String getIdFieldName() {
        return idField.getFieldName();
    }

    private FieldDescription idField;
    private List<FieldDescription> manyToOnes = new LinkedList<>();
    private Map<String, FieldDescription> fieldsSql = new ArrayMap<>();
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
                FieldDescription ofam = new FieldDescription(anotConcernigUs, f, getter, setter, this);
                fieldsSql.put(ofam.getFieldName(), ofam);
                if (ofam.isIdField()) {
                    idField = ofam;
                }
                if (ofam.isManyToOne()) {
                    manyToOnes.add(ofam);
                }

            }
            sqlFieldsString = new LinkedList<>();
            for (FieldDescription ofam : fieldsSql.values()) {
                String teble = getTableName();
                sqlFieldsString.add(new SqlCol(teble, ofam.getFieldName(), false));
                if (ofam.isManyToOne()) {
                    sqlFieldsString.addAll(copy(ofam.getListStringSqlFields(), true));
                }
            }
            sqlFieldsStringNonDistant = new LinkedList<>();
            for (SqlCol sf : sqlFieldsString) {
                if (!sf.isDistante()) {
                    sqlFieldsStringNonDistant.add(sf);
                }
            }
        Update update = new Update(sqlFieldsStringNonDistant, getTableName());

        update.setWhereClause(idField.getFieldName()+" = ?");
        updateUnitaireString = update.toString();
        
        Select s = new Select(sqlFieldsString, getTableName());
        s.setWhereClause(idField.getField().getName() + " = ?");
        
        if (!manyToOnes.isEmpty() ) {
            for (FieldDescription manyToOne : manyToOnes) {

                Jointure j = new Jointure("LEFT JOIN", manyToOne.getTableJointure(), manyToOne.getTableJointure() + "." + manyToOne.getForeignKeyName() + " = " + getTableName() + "." + manyToOne.getFieldName());
                s.add(j);
            }
        }
        selcectUnitaire = s.toString();
        Insert insert = new Insert(sqlFieldsStringNonDistant, getTableName());
        insertUnitaire = insert.toString();
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
        FieldDescription f;
        StringBuilder edding = null;
        for (Map.Entry<String, FieldDescription> e : fieldsSql.entrySet()) {
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

    public final String getTableName() {
        return clazz.getSimpleName();
    }

    PreparedStatement prepareInsert(Object p, DataBase db) throws SQLException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IncompleteObjectException {

        
 
        
        PreparedStatement preparedStatement = db.getPreparedStatement(insertUnitaire);
        fillOn(sqlFieldsStringNonDistant,preparedStatement,p);
       
        return preparedStatement;

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

        
        System.out.println(selcectUnitaire);
        PreparedStatement preparedStatement = aThis.getPreparedStatement(selcectUnitaire);
        preparedStatement.setObject(1, id);
        return preparedStatement;
    }

    <T> void fillObject(T newInstance, ResultSet executeQuery) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IncompleteObjectException, InstantiationException {
        String name;
        Object object;

        for (FieldDescription ofm : fieldsSql.values()) {
            if (ofm.isSqlType() || ofm.isIdField()) {
                name = ofm.getFieldName();
                object = executeQuery.getObject(name);
                ofm.getSetter().invoke(newInstance, object);
            } else if (ofm.isManyToOne()) {
                Class<?> type = ofm.getField().getType();
                ObjectConvertor ckeckCache = ckeckCache(type, cache);
                Object newInstance1 = type.newInstance();
                ckeckCache.fillObject(newInstance1, executeQuery);
                ofm.getSetter().invoke(newInstance, newInstance1);
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
        for (FieldDescription f : manyToOnes) {
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

    private Collection<? extends SqlCol> copy(Collection<SqlCol> listStringSqlFields, boolean distant) {
        LinkedList<SqlCol> l = new LinkedList();
        for (SqlCol sq : listStringSqlFields) {
            l.add(sq.copy(distant));
        }
        return l;

    }

    private Method getGetterId() {
        return idField.getGetter();
    }

    <T> int update(T t,DataBase base) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IncompleteObjectException {
        Object id = idField.getGetter().invoke(t);
        PreparedStatement preparedStatement = base.getPreparedStatement(updateUnitaireString);
        fillOn(sqlFieldsStringNonDistant, preparedStatement, t);
        preparedStatement.setObject(sqlFieldsStringNonDistant.size()+1, id);
        return preparedStatement.executeUpdate();
        
    }

    private void fillOn(LinkedList<SqlCol> sqlFieldsStringNonDistant, PreparedStatement preparedStatement,Object p) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IncompleteObjectException, SQLException {
         int i = 1;
        for (SqlCol sc : sqlFieldsStringNonDistant) {
            String column = sc.getColumn();
            FieldDescription get = fieldsSql.get(column);
            Object o;
            if (!get.isManyToOne()) {
                o = get.getGetter().invoke(p);
            } else {
                o = get.getGetter().invoke(p);
                if (o != null) {
                    ObjectConvertor disant = ckeckCache(get.getField().getType(), cache);
                    Method getDist = disant.getGetterId();
                    o = getDist.invoke(o);
                }

            }
            preparedStatement.setObject(i, o);
            i++;
        }
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
