/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.com.seraph.webtime;

/**
 *
 * @author John Lawrence M. Penafiel <penafieljlm@gmail.com>
 */
public class StringT {
    
    public static String combine(String[] str, String separator) {
        StringBuilder b = new StringBuilder(str[0]);
        for(int i = 1 ; i < str.length ; i++) {
            b.append(separator);
            b.append(str[i]);
        }
        return b.toString();
    }
    
}
