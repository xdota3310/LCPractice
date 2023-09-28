package com.lr.baidubaikespider.proxypool;

/**
 */
public class Proxy {
    /**
     * 代理ip地址
     */
    private String ip;
    /**
     * 代理端口
     */
    private int port;

    public Proxy(String m_ip, int m_port) {
        this.ip = m_ip;
        this.port = m_port;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public void setIp(String m_ip) {
        this.ip = m_ip;
    }

    public void setPort(int m_port) {
        this.port = m_port;
    }

    @Override
    public String toString() {
        return "IP:" + ip + " Port:" + port;
    }

}
