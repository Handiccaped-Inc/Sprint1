package co.unicauca.openmarket.client.acces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import co.unicauca.openmarket.client.access.OpenMarketFacadeAccesImplSockets;
import co.unicauca.openmarket.client.infra.SessionManager;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.Rol;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
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
        assertEquals(2,products.size());
        assertFalse(products.isEmpty());
    }

    @Test
    public void findProductByNameAndDescription(){
        User usuario = new User(4L,new Rol(2L,"Comprador"),new Date(0),"usuario10@example.com","1234","9876543210","pablo","pablo1","1","cr-32");
        SessionManager.getInstance().setUser(usuario);
        List<Product> products = instance.findProductByNameAndDescription("Producto 1", "DescripciÃ³n del producto 1");
        assertEquals(1,products.size());
        assertFalse(products.isEmpty());
    }

    @Test
    public void buyProduct(){
        User usuario = new User(4L,new Rol(2L,"Comprador"),new Date(0),"usuario10@example.com","1234","9876543210","pablo","pablo1","1","cr-32");
        SessionManager.getInstance().setUser(usuario);
        List<Product> products = instance.findAvailableProducts();
        boolean result = instance.buyProduct(products.get(0));
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


}
