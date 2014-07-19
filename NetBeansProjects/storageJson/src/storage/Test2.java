/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storage;

import java.util.List;

/**
 *
 * @author jonathan
 */
class Test2 {
     private String string;
        private int nb;
        private String id;
        private boolean boo;
        private List<Integer> listInt;
        private List<Test2> listTest;
        private List<String> listS;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<Test2> getListTest() {
            return listTest;
        }

        public void setListTest(List<Test2> listTest) {
            this.listTest = listTest;
        }

        public String getString() {
            return string;
        }

        public int getNb() {
            return nb;
        }

        public List<Integer> getListInt() {
            return listInt;
        }

        public void setString(String string) {
            this.string = string;
        }

        public void setNb(int nb) {
            this.nb = nb;
        }

        public void setBoo(boolean boo) {
            this.boo = boo;
        }

        public boolean isBoo() {
            return boo;
        }

        public void setListInt(List<Integer> listInt) {
            this.listInt = listInt;
        }

        public void setListS(List<String> listS) {
            this.listS = listS;
        }

        public List<String> getListS() {
            return listS;
        }

        public Test2() {

        }
}
