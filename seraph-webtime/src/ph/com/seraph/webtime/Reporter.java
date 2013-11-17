/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.com.seraph.webtime;

/**
 *
 * @author John Lawrence M. Penafiel <penafieljlm@gmail.com>
 */
public class Reporter {
    
    private boolean verbose;
    
    public Reporter(boolean verbose) {
        this.verbose = verbose;
    }
    
    public void print(Object obj) {
        if(verbose) {
            System.out.print(obj);
        }
    }

    public void println(Object x) {
        if(verbose) {
            System.out.println(x);
        }
    }
    
}
