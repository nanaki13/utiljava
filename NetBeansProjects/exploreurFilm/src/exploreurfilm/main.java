/*
 * Copyright (C) 2014 jonathan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package exploreurfilm;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author jonathan
 */
public class main {
    
    public static void main(String[] args){
        Map<String, String> env = System.getenv();
        Set<Map.Entry<String, String>> entrySet = env.entrySet();
        System.out.println("environement du système");
        for (Map.Entry<String, String> entry : entrySet) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        
        Properties properties = System.getProperties();
        Set<String> stringPropertyNames = properties.stringPropertyNames();
        System.out.println("propriétés du système");
        for (String entry : stringPropertyNames) {
            System.out.println(entry+" : "+properties.getProperty(entry));
        }
        
    }
    
}
