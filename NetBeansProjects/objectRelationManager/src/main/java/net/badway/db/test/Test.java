/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db.test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import net.badway.db.DataBase;
import net.badway.db.IncompleteObjectException;

/**
 *
 * @author jonathan
 */
public class Test {
        public static  void main(String[] args) throws ClassNotFoundException, SQLException, IllegalArgumentException, IllegalAccessException, IncompleteObjectException, InvocationTargetException, InstantiationException{
            DataBase base = new DataBase("org.mariadb.jdbc.Driver", "jdbc:mysql://localhost:3306/site_perso", "root", "M1a2r3i4a5");
//            ResultSet r = base.executeQuery("select 1");
//            r.next();
//            
//            System.out.println(r.getObject(1));
//           
//            int nb = base.createDataBase("site_perso");
////             r.next();
//            
//            System.out.println("create : "+nb);
//             r = base.executeQuery("select 1");
//            r.next();
//            System.out.println(r.getObject(1));
//               base.closeConnection();
            
//          User p = new User();
//          p.setId(3);
//          p.setLogin("bonjour");
//          p.setPassword("aaqsdaaa");
//          p.setDate(new Date());
//          ObjectConvertor convertor = new ObjectConvertor(User.class);
//          System.out.println(convertor.getCreateTable());
            try{
//            base.use("site_perso");
//          base.dropTable(User.class);
//           base.dropTable(Country.class);
//         base.createTable(User.class);
            User select = base.select(User.class, 2);
            System.out.println(select);
//            base.save(p);
            User select1 = base.select(User.class, 3);
            Country fr = base.select(Country.class, "FR");
            select1.setCountry(fr);
            select1.setId(4);   
//            base.save(select1);
            Country cc = new Country();
            cc.setCode("GB");
            cc.setName("Angleterre");
            select1.setCountry(cc);
            base.saveOrUpdate(cc);
            base.update(select1);
//            System.out.println("e"+base.exists(p));
//          ObjectConvertor convertor = new ObjectConvertor(User.class, new HashMap<Class<?>, ObjectConvertor>());
//          
//          for(ObjectConvertor objectConvertor : convertor.getListCreate()){
//              System.out.println(objectConvertor.getCreateTable());
//          }
//          System.out.println(" -----  ");
//          System.out.println(convertor.getCreateTable());
      
            }catch(Exception  e){
                e.printStackTrace();
            }finally{
                base.closeConnection();
            }
//          
        }
}
