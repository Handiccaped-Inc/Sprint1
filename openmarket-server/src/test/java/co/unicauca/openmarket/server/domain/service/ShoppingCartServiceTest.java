package co.unicauca.openmarket.server.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.server.access.IShoppingCartRepository;

public class ShoppingCartServiceTest {
    private ShoppingCartService service;
    private IShoppingCartRepository repository;

    public ShoppingCartServiceTest() {
        repository = new MockShoppingCartServiceRepository();
        service = new ShoppingCartService(repository);
    }

    /**
     * Test para comprobar el resultado de guardar un carrito
     */
    @Test
    public void testSaveShoppingCartSuccess() {
        ShoppingCart cart = new ShoppingCart(1L, new User(), new Product(), 1L);
        //Guarda el carrito y toma el resultado
        String result = service.saveShoppingCart(cart);
        //result debe ser 'ok' que indica que la operacion fue existosa
        assertEquals("ok", result);
    }

    /**
     * Test para comprobar el resultado de guardar un carrito sin parametros o nulo
     */
    @Test
    public void testSaveShoppingCartMissingFields() {
        ShoppingCart cart = new ShoppingCart();
        //Trata de guardar el carrito
        String result = service.saveShoppingCart(cart);
        //Se comprueba que result no sea nulo
        assertNotNull(result);
        //result tiene una longitud mayor a 20 debido a que se capto un error
        //y por consiguiente se devolvio un mensaje con una longitud 
        //que cumple la comprobacion
        assertTrue(result.length() > 20);
    }

    /**
     * Test para comprobar el resultado de buscar los productos del carrito por su dueño
     */
    @Test
    public void testFindByOwner() {
        User owner = new User();
        //Trata de buscar los productos del carrito de su dueño
        List<Product> products = service.findByOwner(owner);
        //Verifica que la lista de productos contenga elementos
        assertNotNull(products);
    }

    /**
     * Test para comprobar el resultado de buscar los productos del carrito por su dueño nulo o sin carrito
     */
    @Test
    public void testFindByOwnerWithNullOwner() {
        //Trata de buscar los productos del carrito usando un nulo
        List<Product> products = service.findByOwner(null);
        //products toma el valor de []
        assertNotNull(products);
        //Verifica que products esta vacio
        assertEquals(0, products.size());
    }

    /**
     * Test para comprobar el resultado de buscar todos los carritos existentes hasta ahora
     */
    @Test
    public void testFindAll() {
        //Busca los carritos y los guarda en una lista
        List<ShoppingCart> carts = service.findAll();
        //Verifica que la lista no sea un nulo
        assertNotNull(carts);
    }

    /**
     * Test para comprobar el resultado de buscar un owner
     */
    @Test
    public void testFindRepoByOwner() {
        User owner = new User();
        //Busca y devuelve un carrito segun su dueño
        List<ShoppingCart> carts = service.findRepoByOwner(owner);
        //Verifica que la lista de carritos no sea nula
        assertNotNull(carts);
    }
    /**
     * Test para comprobar el resultado de buscar un owner inexistente o nulo
     */
    @Test
    public void testFindRepoByOwnerWithNullOwner() {
        //Busca y devuelve un carrito segun su dueño
        List<ShoppingCart> carts = service.findRepoByOwner(null);
        //Verifica que carts tenga el valor retornado que es []
        assertNotNull(carts);
        //Verifica que carts no tenga elementos
        assertEquals(0, carts.size());
    }

    /**
     * Test para comprobar el resultado de hacer un delete exitoso
     */
    @Test
    public void testDelete() {
        User owner = new User();
        //Elimina el carrito de "owner"
        String result = service.delete(owner);
        //result debe ser 'ok' que indica que la operacion fue existosa
        assertEquals("ok", result);
    }

    /**
     * Test para comprobar el resultado de hacer un delete sin owner
     */
    @Test
    public void testDeleteWithNullOwner() {
        //Trata de eliminar un carrito de un owner inexistente o nulo
        String result = service.delete(null);
        //Se verifica que result devuelva algo
        assertNotNull(result);
        //result tiene una longitud mayor a 20 debido a que se capto un error
        //y por consiguiente se devolvio un mensaje con una longitud 
        //que cumple la comprobacion
        assertTrue(result.length() > 20);
    }

    /** Mock para las pruebas de ShoppingCartService */
    private class MockShoppingCartServiceRepository implements IShoppingCartRepository {
        List<ShoppingCart> carts;
        public MockShoppingCartServiceRepository() {
            this.carts = new ArrayList<>();
            ShoppingCart cart = new ShoppingCart(1L, new User(), new Product(), 1L);
            carts.add(cart);
        }

        public boolean save(ShoppingCart cart) {
            if (cart != null) {
                carts.add(cart);
                return true;
            }
            return false;
        }

        public List<Product> findByOwner(User owner) {
            if (owner != null) {
                return new ArrayList<>();
            }
            return null;
        }

        public List<ShoppingCart> findAll() {
            return carts;
        }

        public List<ShoppingCart> findRepoByOwner(User owner) {
            if (owner != null) {
                return new ArrayList<>();
            }
            return null;
        }

        public boolean delete(User owner) {
            if (owner != null) {
                return true;
            }
            return false;
        }
    }
}
