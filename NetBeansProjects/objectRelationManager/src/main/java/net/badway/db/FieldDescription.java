/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import net.badway.db.annotation.Id;
import net.badway.db.annotation.ManyToOne;
import net.badway.db.annotation.SqlField;
import net.badway.db.sql.SqlCol;

/**
 *
 * @author jonathan
 */
class FieldDescription {

    private Method getter;
    private Method setter;
    private Field field;
    private List<Annotation> annotations;
    private String foreignKeyName;
    private String typeForeignKey;
    private String sqlTypeString;
    private boolean idField;
    private boolean autoincremant;
    private boolean manyToOne;
    private boolean sqlType;
    private Collection<SqlCol> listStringSqlFields;

    public FieldDescription() {
    }

    public FieldDescription(List<Annotation> annotations, Field field, Method getter, Method setter, ProvierConvertor pofam) {
        this.annotations = annotations;
        this.field = field;
        this.getter = getter;
        this.setter = setter;
        for (Annotation a : annotations) {
            if (a instanceof SqlField) {
                sqlTypeString = ((SqlField) a).sqlType();
                sqlType = true;
            }
            if (a instanceof Id) {
                idField = true;
                autoincremant = ((Id) a).autoIncrement();
            }
            if (a instanceof ManyToOne) {
                manyToOne = true;
                ObjectConvertor oc = pofam.getObjectFieldAndMethod(field.getType());
                listStringSqlFields = oc.getListStringField();
                foreignKeyName =  oc.getIdFieldName();
                typeForeignKey = oc.getTypeId();
            }
        }

    }

    public Field getField() {
        return field;
    }

    public Method getGetter() {
        return getter;
    }

    public Method getSetter() {
        return setter;
    }

    String getFieldName() {
        if (!isManyToOne()) {
            return field.getName();
        } else {
            return field.getName() + '_' + foreignKeyName;
        }
    }

    public String getSqlType() {
        return sqlTypeString;
    }

    void setForeignKeyName(String idFieldName) {
        this.foreignKeyName = idFieldName;
    }

    void setTypeForeignKey(String typeId) {
        this.typeForeignKey = typeId;
    }

    public boolean isAutoincremant() {
        return autoincremant;
    }

    public boolean isIdField() {
        return idField;
    }

    public boolean isManyToOne() {
        return manyToOne;
    }

    public boolean isSqlType() {
        return sqlType;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public String getForeignKeyName() {
        return foreignKeyName;
    }

    public String getTypeForeignKey() {
        return typeForeignKey;
    }

    public String getTableJointure() {
        return field.getType().getSimpleName();
    }

    Collection<SqlCol> getListStringSqlFields() {
        return listStringSqlFields;
    }

}
