package co.unicauca.openmarket.client.acces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import co.unicauca.openmarket.client.access.OpenMarketFacadeAccesImplSockets;
import co.unicauca.openmarket.client.infra.SessionManager;
import co.unicauca.openmarket.commons.domain.Category;
import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.Rol;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.StateProduct;
import co.unicauca.openmarket.commons.domain.User;

public class OpenMarketFacadeAccesImplSocketsTest {

    OpenMarketFacadeAccesImplSockets instance = new OpenMarketFacadeAccesImplSockets();

    

    @Test
    public void findUserByEmailAndPasswordTest(){
        String email = "usuario10@example.com";
        String password = "1";
        User user = instance.findUserByEmailAndPassword(email,password);
        assertEquals("usuario4", user.getUserName());
        assertEquals("Usuario 4", user.getRealName());
    }
    
    @Test
    public void findAvailableProducts(){
        User usuario = new User(4L,new Rol(2L,"Comprador"),new Date(0),"usuario10@example.com","1234","9876543210","pablo","pablo1","1","cr-32");
        SessionManager.getInstance().setUser(usuario);
        List<Product> products = instance.findAvailableProducts();
        assertEquals(3,products.size());
        assertFalse(products.isEmpty());
    }

    @Test
    public void findProductByNameAndDescription(){
        User usuario = new User(4L,new Rol(2L,"Comprador"),new Date(0),"usuario10@example.com","1234","9876543210","pablo","pablo1","1","cr-32");
        SessionManager.getInstance().setUser(usuario);
        List<Product> products = instance.findProductByNameAndDescription("Lambo Rey", "En un carro Deportivo");
        assertEquals(1,products.size());
        assertFalse(products.isEmpty());
    }

    @Test
    public void buyProduct(){
        User usuario = new User(4L,new Rol(2L,"Comprador"),new Date(0),"usuario10@example.com","1234","9876543210","pablo","pablo1","1","cr-32");
        SessionManager.getInstance().setUser(usuario);
        List<Product> products = instance.findAvailableProducts();
        boolean result = instance.buyProduct(products.get(1));
        assertTrue(result);
    }

    @Test
    public void addProductToShoppingCart(){
        User usuario = new User(4L,new Rol(2L,"Comprador"),new Date(0),"usuario10@example.com","1234","9876543210","pablo","pablo1","1","cr-32");
        SessionManager.getInstance().setUser(usuario);
        List<Product> products = instance.findAvailableProducts();
        boolean result = instance.addProductToTheShoppingCart(products.get(0),4L);
        assertTrue(result);
    }

    @Test
    public void getShoppingCart(){
        User usuario = new User(4L,new Rol(2L,"Comprador"),new Date(0),"usuario10@example.com","1234","9876543210","pablo","pablo1","1","cr-32");
        SessionManager.getInstance().setUser(usuario);
        List<ShoppingCart> listaShoppingCarts = instance.getShoppingCart();
        assertEquals(2,listaShoppingCarts.size());
        assertFalse(listaShoppingCarts.isEmpty());

    }

    @Test
    public void buyShoppingCart(){
        User usuario = new User(4L,new Rol(2L,"Comprador"),new Date(0),"usuario10@example.com","1234","1234","pablo","pablo1","1","cr-32");
        SessionManager.getInstance().setUser(usuario);
        boolean result = instance.buyShoppingCart();
        assertTrue(result);
    }

    @Test
    public void deleteShoppingCart(){
        User usuario = new User(4L,new Rol(2L,"Comprador"),new Date(0),"usuario10@example.com","1234","1234","pablo","pablo1","1","cr-32");
        SessionManager.getInstance().setUser(usuario);
        boolean result = instance.deleteShoppingCart();
        assertTrue(result);

    }

    @Test
    public void saveProduct(){
        User usuario = new User(3L,new Rol(1L,"Vendedor"),new Date(0),"usuario3@example.com","1234","1234567890","pablo","pablo1","1","cr-32");
        SessionManager.getInstance().setUser(usuario);
        Product product = new Product(3L,usuario,new Category(2L,"Cateogiria 2"),new StateProduct(1L,"disponble"),"TwinGod","TwinGOD tuneado",100L,60L,100.00,2000.00);
        boolean result = instance.saveProduct(product);
    }

    @Test
    public void getOwnProducts(){
        User usuario = new User(3L,new Rol(1L,"Vendedor"),new Date(0),"usuario3@example.com","1234","1234567890","pablo","pablo1","1","cr-32");
        SessionManager.getInstance().setUser(usuario);
        List<Product> listProducts = instance.getOwnProducts();
        assertEquals(2, listProducts.size());
        assertFalse(listProducts.isEmpty());

    }

    @Test
    public void updteProduct(){
        User usuario = new User(3L,new Rol(1L,"Vendedor"),new Date(0),"usuario3@example.com","1234","1234567890","pablo","pablo1","1","cr-32");
        SessionManager.getInstance().setUser(usuario);
        Product product = new Product(3L,usuario,new Category(2L,"Cateogiria 2"),new StateProduct(1L,"disponble"),"Lamborey","Pa feontear papa",100L,60L,100.00,2000.00);
        boolean result = instance.updateProduct(product);
        assertTrue(result);
    }

    @Test
    public void  getOrders(){
        User usuario = new User(2L,new Rol(3L,"Repartidor"),new Date(0),"usuario2@example.com","1234","1234567890","pablo","pablo1","1","cr-32");
        SessionManager.getInstance().setUser(usuario);
        List<Order> gerOrders = instance.getOrders();
        assertEquals(2,gerOrders.size());

    }

    @Test
    public void confirmOrder(){
        User usuario = new User(2L,new Rol(3L,"Repartidor"),new Date(0),"usuario2@example.com","1234","1234567890","pablo","pablo1","1","cr-32");
        SessionManager.getInstance().setUser(usuario);
        List<Order> getOrders = instance.getOrders();
        assertTrue(instance.confirmOrder(getOrders.get(0)));
    }





}
