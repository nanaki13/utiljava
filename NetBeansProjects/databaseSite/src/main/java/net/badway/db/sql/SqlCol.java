/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db.sql;

/**
 *
 * @author jonathan
 */
public class SqlCol {
    private String table;
    private String column;

    public SqlCol(String table, String column) {
        this.table = table;
        this.column = column;
    }
    
     public SqlCol(String column) {
        
        this.column = column;
    }

    public String getTable() {
        return table;
    }

    public String getColumn() {
        return column;
    }
    
    public String toString(){
        if(table!=null && !table.isEmpty()){
            return table+"."+column;
        }
        return column;
    }
    
    
    
}
