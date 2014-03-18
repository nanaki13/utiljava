/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.string;

/**
 *
 * @author jonathan
 */
public class StringTool {

    public static final int LEFT = 0;
    public static final int MIDDLE = 1;
    public static final int RIGHT = 2;

    public static void main(String[] args) {
        String r = raccourci("aaaaaaaaaaaaaaaa", 6, LEFT);
        System.out.println(r);
        System.out.println(r.length());
    }

    public static String raccourci(String in, int size) {
        String ret;
        String troisPt = "...";
        if (in == null || in.length() < 5 || size >= in.length() || size < 6) {
            ret = in;
        } else {
            int demiLength;
            int delta = 0;
            if (size % 2 == 0) {
                demiLength = size / 2;
                delta = 2;
            } else {
                demiLength = size / 2;
                delta = 1;
            }
            String deb = in.substring(0, demiLength - 1);
            String fin = in.substring(in.length() - demiLength + delta, in.length());
            ret = deb + troisPt + fin;
        }
        return ret;
    }

    public static String raccourci(String in, int size, int pos) {
        switch (pos) {
            case MIDDLE:
                return raccourci(in, size);
            case LEFT:
                String ret;
                String troisPt = "...";
                if (in == null || in.length() < 5 || size >= in.length() || size < 6) {
                    ret = in;
                } else {
                    String fin = in.substring(in.length() - size + 3);
                    ret = troisPt+fin;
                }
                return ret;
            default: return in;
        }

    }

}
