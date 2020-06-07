package it.polimi.ingsw.PSP33.utils;

public class Connection {

    private final String server_ip;
    private final int port;

    public Connection(String server_ip, int port) {
        this.server_ip = server_ip;
        this.port = port;
    }

    public String getServer_ip() {
        return server_ip;
    }

    public int getPort() {
        return port;
    }
}
