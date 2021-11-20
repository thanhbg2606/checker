/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hnonstoph
 */
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.util.concurrent.ThreadLocalRandom;
 
public class IPAddress implements Serializable{
    private static final long serialVersionUID = 20210811012L;
    private String host;
    private int port;
     
    public IPAddress() {
        super();
    }
 
    public IPAddress(String host, int port) {
        super();
        this.host = host;
        this.port = port;
    }
 
    public String getHost() {
        return host;
    }
 
    public void setHost(String host) {
        this.host = host;
    }
 
    public int getPort() {
        return port;
    }
 
    public void setPort(int port) {
        this.port = port;
    }
    
    public int ramdom() throws IOException{
        int ranNum = ThreadLocalRandom.current().nextInt(1025, 48000);
        this.port = ranNum;
        return this.port;
    }
}