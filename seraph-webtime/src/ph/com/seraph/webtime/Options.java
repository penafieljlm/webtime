/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.com.seraph.webtime;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author John Lawrence M. Penafiel <penafieljlm@gmail.com>
 */
public class Options {
    
    private static final String URL_FLAG = "-url";
    private static final String PROXY_HOST = "-proxyhost";
    private static final String PROXY_PORT = "-proxyport";
    private static final String PROXY_ENABLE = "/proxy";
    private static final String VERBOSE_ENABLE = "/verbose";
    
    private HashMap<String, Object> valueoptions;
    private ArrayList<String> flagoptions;
    
    public boolean isVerboseEnabled() {
        return flagoptions.contains(VERBOSE_ENABLE);
    }
    
    public void setVerboseEnabled(boolean enabled) {
        if(enabled && !flagoptions.contains(VERBOSE_ENABLE)) {
            flagoptions.add(VERBOSE_ENABLE);
        }
        if(!enabled && flagoptions.contains(VERBOSE_ENABLE)) {
            flagoptions.remove(VERBOSE_ENABLE);
        }
    }
    
    public boolean isProxyEnabled() {
        return flagoptions.contains(PROXY_ENABLE);
    }
    
    public void setProxyEnabled(boolean enabled) {
        if(enabled && !flagoptions.contains(PROXY_ENABLE)) {
            flagoptions.add(PROXY_ENABLE);
        }
        if(!enabled && flagoptions.contains(PROXY_ENABLE)) {
            flagoptions.remove(PROXY_ENABLE);
        }
    }
    
    public int getProxyPort() {
        return Integer.parseInt((String)valueoptions.get(PROXY_PORT));
    }
    
    public void setProxyPort(int port) {
        valueoptions.put(PROXY_PORT, "" + port);
    }
    
    public String getProxyHost() {
        return (String)valueoptions.get(PROXY_HOST);
    }
    
    public void setProxyHost(String url) {
        valueoptions.put(PROXY_HOST, url);
    }
    
    public String getUrl() {
        return (String)valueoptions.get(URL_FLAG);
    }
    
    public void setUrl(String url) {
        valueoptions.put(URL_FLAG, url);
    }
    
    public Options(String[] args) {
        valueoptions = new HashMap<>();
        flagoptions = new ArrayList<>();
        for(int i = 0 ; i < args.length ; i++) {
            if(args[i].startsWith("-")) {
                String k = args[i];
                i++;
                String v = args[i];
                valueoptions.put(k,v);
                continue;
            }
            if(args[i].startsWith("/")) {
                flagoptions.add(args[i]);
                continue;
            }
        }
    }
    
}
