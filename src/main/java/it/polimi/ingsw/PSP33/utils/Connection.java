package it.polimi.ingsw.PSP33.utils;

public class Connection {

    private final String server;
    private final int port;

    public Connection(String server, int port) {
        this.server = server;
        this.port = port;
    }

    public String getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }
}
