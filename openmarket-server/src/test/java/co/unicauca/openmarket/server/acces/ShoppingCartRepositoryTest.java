package co.unicauca.openmarket.server.acces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import co.unicauca.openmarket.commons.domain.Category;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.Rol;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.StateProduct;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.server.access.IShoppingCartRepository;
import co.unicauca.openmarket.server.access.ShoppingCartRepository;

/**
 * Pruebas unitarias para la clase ShoppingCartRepository
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShoppingCartRepositoryTest {

    IShoppingCartRepository repository = new ShoppingCartRepository();
    DatabaseTestManagement database = DatabaseTestManagement.getInstance();

    /**
     * Limpia la base de datos antes de cada test
     */
    @BeforeEach
    public void cleanDatabase(){
        database.truncateTablebyName("shopping_cart");
        database.insertShoppingCart();
    }

      /**
     * limpia la base de datos una vez se hayan ejecutado los test
     */
    @AfterAll
    public void cleanDatabaseAtFinal() {
        database.truncateTablebyName("shopping_cart");
        database.insertShoppingCart();
    }

    /**
     * Pruebas uniatrias para guardar un carrito de compras exitosa
     */
    @Test
    public void saveTestSuccess(){
        User usuario = new User(1L,new Rol(1L,"Comprador"),new Date(0),"Example4@email.com","1234","1234","pablo","pablo1","123","cr-32");
        Product producTest = new Product(1L,usuario,new Category(1L,"Categoría 1"),new StateProduct(1L,"disponible" ),"Producto 1","Descripción del producto 1",10.99,50L,100,100);
        ShoppingCart newCart = new ShoppingCart(1L,usuario,producTest,5L);
        Boolean result = repository.save(newCart);
        assertTrue(result);
    }

    /**
     * Pruebas unitarias para guardar un carrito de compras fallida
     */
    @Test
    public void saveTestFailed(){
        ShoppingCart newCart = null;
        Boolean result = repository.save(newCart);
        assertFalse(result);
    }

    /**
     * pruebas unitarias para encontrar todos los carritos de compras 
     */
    @Test
    public void findAllTest(){
        List<ShoppingCart> listFindAllSucces = repository.findAll();
        assertFalse(listFindAllSucces.isEmpty());
        assertEquals(4, listFindAllSucces.size());
    }

    /**
     * pruebas unitarias para encontrar un carrito de compras por el propietario 
     */
    @Test
    public void findRepoByOwnerSuccess(){
        User usuario = new User(1L,new Rol(1L,"Comprador"),new Date(0),"Example4@email.com","1234","1234","pablo","pablo1","123","cr-32");
        List<ShoppingCart> listFindRepoByOwner = repository.findRepoByOwner(usuario);
        assertFalse(listFindRepoByOwner.isEmpty());
        assertEquals(1, listFindRepoByOwner.size());
        assertEquals(2L, listFindRepoByOwner.get(0).getQuantity());
    }

    /**
     * pruebas unitarias para encontrar un carrito de compras por el propietario fallida
     */
    @Test
    public void findRepoByOwnerFailed(){
        User usuario = null;
        List<ShoppingCart> listFindRepoByOwner = repository.findRepoByOwner(usuario);
        assertTrue(listFindRepoByOwner.isEmpty());
    }

    /**
     * Pruebas unitarias para encontrar Los productos de un carrito de compras por el propitario exitosa
     */
    @Test
    public void findByOwnerSuccess(){
        User usuario = new User(1L,new Rol(1L,"Comprador"),new Date(0),"Example4@email.com","1234","1234","pablo","pablo1","123","cr-32");
        List<Product> listFindRepoByOwner = repository.findByOwner(usuario);
        assertFalse(listFindRepoByOwner.isEmpty());
        assertEquals(1, listFindRepoByOwner.size());
        assertEquals("Producto 1", listFindRepoByOwner.get(0).getName());
    }

    /**
     * Pruebas unitarias para encontrar Los productos de un carrito de compras por el propitario fallida
     */
    @Test
    public void findByOwnerFailed(){
        User usuario = null;
        List<Product> listFindRepoByOwner = repository.findByOwner(usuario);
        assertTrue(listFindRepoByOwner.isEmpty());
    }

    /**
     * Pruebas unitarias para eliminar un carrito de compras exitosa
     */
    @Test
    public void deleteTestSuccess(){
        User usuario = new User(1L,new Rol(1L,"Comprador"),new Date(0),"Example4@email.com","1234","1234","pablo","pablo1","123","cr-32");
        Boolean result = repository.delete(usuario);
        assertTrue(result);
    }

    /**
     * Pruebas unitarias para eliminar un carrito de compras fallida
     */
    @Test
    public void deleteTestFailed(){
        User usuario = null;
        Boolean result = repository.delete(usuario);
        assertFalse(result);
    }


    
    
}
