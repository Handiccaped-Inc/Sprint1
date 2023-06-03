package co.unicauca.openmarket.server.infra;

/**
 * Clase openmarket
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import co.unicauca.openmarket.server.access.CategoryRepository;
import co.unicauca.openmarket.server.access.DeliveryRepository;
import co.unicauca.openmarket.server.access.IDeliveryRepository;
import co.unicauca.openmarket.server.access.OrderRepository;
import co.unicauca.openmarket.server.access.ShoppingCartRepository;
import co.unicauca.openmarket.server.access.UserRepository;
import co.unicauca.openmarket.server.access.ProductRepository;
import co.unicauca.openmarket.server.domain.service.DeliveryService;
import co.unicauca.openmarket.server.domain.service.IDeliveryService;
import co.unicauca.openmarket.server.domain.service.OrderService;
import co.unicauca.openmarket.server.domain.service.ProductService;
import co.unicauca.openmarket.server.domain.service.ShoppingCartService;
import co.unicauca.openmarket.server.domain.service.UserService;
import co.unicauca.strategyserver.infra.ServerSocketMultiThread;
import java.util.Scanner;

/**
 * Clase
 * @author ahurtado
 */
public class OpenMarketServer {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el puerto de escucha");
        int port = teclado.nextInt();
        ServerSocketMultiThread myServer = new ServerSocketMultiThread(port);

        OpenMarketHandler myHandler = new OpenMarketHandler(
            new DeliveryService(new DeliveryRepository()), 
            new OrderService(new OrderRepository()), 
            new ProductService(new ProductRepository()), 
            new ShoppingCartService(new ShoppingCartRepository()), 
            new UserService(new UserRepository())
        );
        myServer.setServerHandler(myHandler);
        
        myServer.startServer();
    }

}