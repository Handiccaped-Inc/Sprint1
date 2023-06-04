package co.unicauca.openmarket.server.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.unicauca.openmarket.commons.domain.Category;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.Rol;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.StateProduct;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.server.acces.DatabaseTestManagement;
import co.unicauca.openmarket.server.access.DeliveryRepository;
import co.unicauca.openmarket.server.access.OrderRepository;
import co.unicauca.openmarket.server.access.ProductRepository;
import co.unicauca.openmarket.server.access.ShoppingCartRepository;
import co.unicauca.openmarket.server.access.UserRepository;

public class OpenMarketFacadeTest {

     
        /** 
         * Creacion del facade
         */
        IDeliveryService deliveryService = new DeliveryService(new DeliveryRepository());
        IOrderService orderService = new OrderService(new OrderRepository());
        IProductService productService = new ProductService(new ProductRepository());
        IShoppingCartService shoppingCartService = new ShoppingCartService(new ShoppingCartRepository());
        IUserService userService = new UserService(new UserRepository());
        OpenMarketFacade facade = new OpenMarketFacade(deliveryService, orderService, productService, shoppingCartService, userService,null);

        DatabaseTestManagement database = DatabaseTestManagement.getInstance();

        @BeforeEach
        public void cleanDataBase(){
            database.truncateTableAll();
            database.insertRols();
            database.insertOrderStatus();
            database.insertStateProduct();
            database.insertCategory();
            database.insertUser();
            database.insertProduct();
            database.insertShoppingCart();
            database.insertOrders();
            database.insertDelivery();
        }

        @Test
        public void setRequesterTest(){
            User usuario = new User(4L,new Rol(2L,"Comprador"),new Date(0),"usuario10@example.com","1234","1234","pablo","pablo1","1","cr-32");
            facade.setRequester(usuario);
        }

        @Test
        public void  findAvailableProducts(){
            List<Product> products = facade.findAvailableProducts();
            assertEquals(2,products.size());
            assertFalse(products.isEmpty());
        }

        @Test 
        public void buyProductTest(){
            User usuario = new User(4L,new Rol(2L,"Comprador"),new Date(0),"usuario10@example.com","1234","1234","pablo","pablo1","1","cr-32");
            facade.setRequester(usuario);
            User vendedor = new User(3L,new Rol(1L,"Vendedor"),new Date(0),"Example3@email.com","1234","1234","pablo","pablo1","123","cr-32");
            Product producTest = new Product(3L,vendedor,new Category(2L, "Categoría 2"),new StateProduct(2L,"no disponible"),"jabon","jabon de la mejor calidad",10,1L,100,100);
            String Response = facade.buyProduct(producTest);
            assertEquals("ok", Response);
        }

        @Test
        public void addProductToTheShoppingCart(){
            User usuario = new User(4L,new Rol(2L,"Comprador"),new Date(0),"usuario10@example.com","1234","1234","pablo","pablo1","1","cr-32");
            facade.setRequester(usuario);
            User vendedor = new User(3L,new Rol(1L,"Vendedor"),new Date(0),"Example3@email.com","1234","1234","pablo","pablo1","123","cr-32");
            Product producTest = new Product(3L,vendedor,new Category(2L, "Categoría 2"),new StateProduct(2L,"no disponible"),"Producto 3","Descripción del producto 3",10,1L,100,100);
            assertEquals("ok",facade.addProductToTheShoppingCart(producTest, 2L));


        }

        @Test
        public void getShoppingCartTest(){
            User usuario = new User(4L,new Rol(2L,"Comprador"),new Date(0),"usuario10@example.com","1234","1234","pablo","pablo1","1","cr-32");
            facade.setRequester(usuario);
            User vendedor = new User(3L,new Rol(1L,"Vendedor"),new Date(0),"Example3@email.com","1234","1234","pablo","pablo1","123","cr-32");
            Product producTest = new Product(3L,vendedor,new Category(2L, "Categoría 2"),new StateProduct(2L,"no disponible"),"Producto 3","Descripción del producto 3",10,1L,100,100);
            facade.addProductToTheShoppingCart(producTest, 2L);
            facade.setRequester(usuario);
            List<ShoppingCart> listShoppingCarts = facade.getShoppingCart();
            assertEquals(1, listShoppingCarts.size());

        }

        @Test
        public void buyShoppingCart(){
            User usuario = new User(4L,new Rol(2L,"Comprador"),new Date(0),"usuario10@example.com","1234","1234","pablo","pablo1","1","cr-32");
            facade.setRequester(usuario);
            User vendedor = new User(3L,new Rol(1L,"Vendedor"),new Date(0),"Example3@email.com","1234","1234","pablo","pablo1","123","cr-32");
            Product producTest = new Product(3L,vendedor,new Category(2L, "Categoría 2"),new StateProduct(2L,"no disponible"),"Producto 3","Descripción del producto 3",10,1L,100,100);
            facade.addProductToTheShoppingCart(producTest, 2L);
            facade.setRequester(usuario);
            assertEquals("ok", facade.buyShoppingCart());
        }

        @Test
        public void deleteShoppingCart(){
            User usuario = new User(4L,new Rol(2L,"Comprador"),new Date(0),"usuario10@example.com","1234","1234","pablo","pablo1","1","cr-32");
            facade.setRequester(usuario);
            User vendedor = new User(3L,new Rol(1L,"Vendedor"),new Date(0),"Example3@email.com","1234","1234","pablo","pablo1","123","cr-32");
            Product producTest = new Product(3L,vendedor,new Category(2L, "Categoría 2"),new StateProduct(2L,"no disponible"),"Producto 3","Descripción del producto 3",10,1L,100,100);
            facade.addProductToTheShoppingCart(producTest, 2L);
            assertEquals("ok", facade.deleteShoppingCart());

        }

        @Test
        public void saveProductTestSuccess(){
            User usuario = new User(3L,new Rol(1L,"Vendedor"),new Date(0),"usuario3@example.com","1234","1234","pablo","pablo1","1","cr-32");
            facade.setRequester(usuario);
            Product producTest = new Product(1L,usuario,new Category(2L, "Categoría 2"),new StateProduct(1L,"disponible"),"Gaseosa","es una gaseosa",600,100L,100,100);
            assertEquals("ok", facade.saveProduct(producTest));
        }

        @Test
        public void saveProductTestFail(){
            User usuario = new User(4L,new Rol(2L,"Comprador"),new Date(0),"usuario10@example.com","1234","1234","pablo","pablo1","1","cr-32");
            facade.setRequester(usuario);
            Product producTest = new Product(1L,usuario,new Category(2L, "Categoría 2"),new StateProduct(1L,"disponible"),"Gaseosa","es una gaseosa",600,100L,100,100);
            assertEquals("!error", facade.saveProduct(producTest));
        }
        }
    

