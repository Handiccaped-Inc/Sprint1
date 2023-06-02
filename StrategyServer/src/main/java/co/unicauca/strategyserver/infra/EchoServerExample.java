package co.unicauca.strategyserver.infra;

/**
 * Echo Server
 * @author ahurtado
 */
public class EchoServerExample{
    
    /**
     * Constructor default
     */
    public EchoServerExample(){}

    /**
     * Main
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocketMultiThread server;
        server = new ServerSocketMultiThread(5000);
        ServerHandler echo = new ServerHandler(){
            @Override
            public String processRequest(String request) {
                return request;
            }
        };
        server.setServerHandler(echo);
        server.startServer();
    }
}
