package co.unicauca.openmarket.server.infra.tcpip;

import java.util.Scanner;

import co.unicauca.openmarket.server.access.DeliveryRepository;
import co.unicauca.openmarket.server.domain.service.DeliveryService;
import co.unicauca.openmarket.server.domain.service.IDeliveryService;
import co.unicauca.openmarket.server.domain.service.IOrderService;
import co.unicauca.openmarket.server.domain.service.IProductService;
import co.unicauca.openmarket.server.domain.service.IShoppingCartService;
import co.unicauca.openmarket.server.domain.service.IUserService;
import co.unicauca.openmarket.server.domain.service.OpenMarketFacade;
import co.unicauca.openmarket.server.domain.service.OrderService;
import co.unicauca.openmarket.server.domain.service.ProductService;
import co.unicauca.openmarket.server.domain.service.ShoppingCartService;
import co.unicauca.openmarket.server.domain.service.UserService;
import co.unicauca.strategyserver.infra.ServerSocketMultiThread;
import co.unicauca.openmarket.server.access.OrderRepository;
import co.unicauca.openmarket.server.access.ProductRepository;
import co.unicauca.openmarket.server.access.ShoppingCartRepository;
import co.unicauca.openmarket.server.access.UserRepository;

/**
 * Clase OpenMarketServer
 */
public class OpenMarketServer {
    /** main of the server
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el puerto de escucha");
        int port = teclado.nextInt();
        
        /** 
         * Creacion del facade
         */
        IDeliveryService deliveryService = new DeliveryService(new DeliveryRepository());
        IOrderService orderService = new OrderService(new OrderRepository());
        IProductService productService = new ProductService(new ProductRepository());
        IShoppingCartService shoppingCartService = new ShoppingCartService(new ShoppingCartRepository());
        IUserService userService = new UserService(new UserRepository());
        OpenMarketFacade facade = new OpenMarketFacade(deliveryService, orderService, productService, shoppingCartService, userService,null);

        /**
         * Creacion del server y de los handler
         */
        ServerSocketMultiThread myServer = new ServerSocketMultiThread(port);
        OpenMarketHandler myHandler = new OpenMarketHandler();
        myHandler.SetOpenMarketFacade(facade);
        myServer.setServerHandler(myHandler);
        myServer.startServer();
    }
}
