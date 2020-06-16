package it.polimi.ingsw.PSP33.utils;

public class Connection {

    private final String server;
    private final int port;

    public Connection(String server_ip, int port) {
        this.server = server_ip;
        this.port = port;
    }

    public String getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }
}
