/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.com.seraph.webtime;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author John Lawrence M. Penafiel <penafieljlm@gmail.com>
 */
public class WebTime {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Options options = new Options(args);
        Reporter reporter = new Reporter(options.isVerboseEnabled());
        
        try {
            //get date and time from web
            reporter.println("connecting to " + options.getUrl() + "...");
            URL url = new URL(options.getUrl());
            URLConnection connection;
            if(options.isProxyEnabled()) {
                reporter.println("setting proxy to " + options.getProxyHost() + ":" + options.getProxyPort() + "...");
                InetAddress proxyip = Inet4Address.getByName(options.getProxyHost());
                InetSocketAddress proxysock = new InetSocketAddress(proxyip, options.getProxyPort());
                Proxy proxy = new Proxy(Proxy.Type.HTTP, proxysock);
                connection = url.openConnection(proxy);
            }
            else {
                connection = url.openConnection();
            }
            //acquire timestamp
            Date datetime = new Date(connection.getDate());
            DateFormat dateformat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
            String datestring = dateformat.format(datetime);
            reporter.println("timestamp acquired: " + datestring + "...");
            
            //set date and time to system
            reporter.println("applying timestamp...");
            Runtime runtime = Runtime.getRuntime();
            String[] command = {"date", "-s", datestring};
            reporter.println("executing commands...");
            reporter.println(StringT.combine(command, " "));
            runtime.exec(command);
        } catch(Exception e) {
            System.out.println(
                      "\n  WebTime v1.00, by John Lawrence M. Penafiel <penafieljlm@gmail.com>"
                    + "\n    Description:"
                    + "\n      This tool allows you to synchronize the clock of your linux machine to"
                    + "\n      the clock of any web server of your choosing. This tool arose from the"
                    + "\n      need of the author to constantly adjust the clock of the computer hos-"
                    + "\n      ting their college thesis project, because of a broken CMOS battery."
                    + "\n    Usage:"
                    + "\n      /<flag>            - sets the flag with the specified name to true"
                    + "\n      -<key> <value>     - sets the given key to the given value"
                    + "\n    Flags:"
                    + "\n      /verbose           - displays output"
                    + "\n      /proxy             - the connection will use a proxy"
                    + "\n    Value Parameters:"
                    + "\n      -url <value>       - the url to connect to"
                    + "\n      -proxyhost <value> - the hostname or ip of the proxy server to use"
                    + "\n      -proxyport <value> - the port number of the proxy server to use"
                    + "\n    Examples:"
                    + "\n      java -jar web-time.jar -url http://www.foobar.com /proxy -proxyhost"
                    + "\n        proxy.foobar.com -proxyport 80 /verbose"
                    + "\n      java -jar web-time.jar -url http://www.foobar.com /verbose"
                    + "\n");
        }
    }
    
    
}
