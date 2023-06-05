package co.unicauca.openmarket.server.acces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.List;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import co.unicauca.openmarket.commons.domain.Category;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.Rol;
import co.unicauca.openmarket.commons.domain.StateProduct;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.server.access.IProductRepository;
import co.unicauca.openmarket.server.access.ProductRepository;

/**
 * Pruebas unitarias para la clase ProductRepository
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductRepositoryTest{

    IProductRepository repository = new ProductRepository(); // Instancia de la clase ProductRepository
    DatabaseTestManagement database = DatabaseTestManagement.getInstance(); // Instancia de la clase DatabaseTestManagement

    /**
     * Metodo para limpiar la base de datos antes de cada test
     */
    @BeforeEach
    public  void cleanDatabase(){
        database.truncateTablebyName("Product");
        database.insertProduct();

    }

    /**
     * Metodo para limpiar la base de datos una vez se hayan ejecutado todos los Test
     */
    @AfterAll
    public void cleanDatabaseAtEnd(){
        database.truncateTablebyName("Product");
        database.insertProduct();
    }
    /**
     * Test de la funcion encontrar por estado Exitosa
     */
    @Test
    public void testFindByStatusSuccess() {
        List<Product> products = repository.findByStatus(new StateProduct(1L,"disponible"));
        assertEquals(3, products.size()); // Espera como resultado 2 que nos indica que se encontraron 2 productos
    }

    /**
     * Test de la funcion encontrar por estado fallida
     */
    @Test
    public void testFindByStatusFailed() {
        List<Product> products = repository.findByStatus(new StateProduct(4L,"fallar"));
        assertEquals(0, products.size()); // Espera como resultado 0 que nos indica que no se encontraron productos
    }

    /**
     * Test de la funcion findAll
     */
    @Test
    public void testFindByNameAndDescriptionSuccess() {
        List<Product> productsFindByNameAndDescription  = repository.findByNameAndDescription("Lambo Rey", "En un carro Deportivo");
        assertEquals(1, productsFindByNameAndDescription.size()); // Espera como resultado 1 que nos indica que se encontro un producto
        assertEquals("Lambo Rey", productsFindByNameAndDescription.get(0).getName()); // Espera como resultado el nombre del producto 1
    }

    /**
     * Test de la funcion findAll fallida
     */
    @Test
    public void testFindByNameAndDescriptionFailed() {
        List<Product> productsFindByNameAndDescription  = repository.findByNameAndDescription("Producto 5", "Descripción del producto 5");
        assertEquals(0, productsFindByNameAndDescription.size()); // Espera como resultado 0 que nos indica que no se encontro un producto
    }

    /**
     * Test de la funcion findById
     */
    @Test 
    public void findByIdSuccess(){
        Product productTest = repository.findById(3L);
        assertEquals(3L, productTest.getOwner().getId());
        assertEquals("Lambo Rey", productTest.getName());
        assertEquals(10,productTest.getStock());
    }

    /**
     * Test de la funcion findById fallida
     * probar con un id que se conozca que no esta en la base de datos
     */
    @Test
    public void findByIdFailed(){
        Product productTest = repository.findById(50L);
        assertNull(productTest);
    }


    /**
     * Test de la funcion save
     */
    @Test
   public void testSaveSuccess() {
        User usuario = new User(1L,new Rol(1L,"Comprador"),new Date(0),"Example4@email.com","1234","1234","pablo","pablo1","123","cr-32");
        Product producTest = new Product(4L,usuario,new Category(1L,"Categoría 1"),new StateProduct(1L,"disponible" ),"Papa","Papa de la mejor calidad",1000,100L,100,100);
        boolean result = repository.save(producTest);
        assertTrue(result); // Espera como resultado true que nos indica una inserccion
    }

    /**
     * Test de la funcion save fallada
     */
    @Test
    public void testSaveFailed() {
        Product producTest = null;
        boolean result = repository.save(producTest);
        assertFalse(result); // Espera como resultado false que nos indica una inserccion fallida
    }

    /**
     * Test de la funcion update
     * En este test se editarae el producto con id = 1 de la base de datos
     */
    @Test
    public void testUpdateSuccess() {
        User usuario = new User(1L,new Rol(1L,"Vendedor"),new Date(0),"Example7@email.com","1234","1234","pablo","pablo1","123","cr-32");
        Product producTest = new Product(1L,usuario,new Category(2L, "Categoría 2"),new StateProduct(2L,"no disponible"),"jabon","jabon de la mejor calidad",1000,100L,100,100);
        boolean result = repository.update(producTest);
        Product productTest = repository.findById(1L);
        assertTrue(result); // Espera como resultado true que nos indica una actualizacion
        assertEquals(1L, productTest.getOwner().getId()); // Espera como resultado un id = 1 que nos indica una actualizacion
        assertEquals("jabon", productTest.getName()); // Espera como resultado que el nombre sea jabon que nos indica una actualizacion
        assertEquals(100,productTest.getStock()); // Espera como resultado el stock sea de 100 que nos indica una actualizacion
    }

    /**
     * Test de la funcion update fallida
     */
    @Test
    public void testUpdateFailed() {
        Product producTest = null;
        boolean result = repository.update(producTest);
        assertFalse(result); // Espera como resultado false que nos indica una actualizacion fallida
    }

    /**
     * Test de la funcion delete
     * En este test se eliminara el producto con id = 3 de la base de datos
     */
    @Test
    public void testDeleteSuccess() {
        User usuario = new User(3L,new Rol(1L,"Comprador"),new Date(0),"Example4@email.com","1234","1234","pablo","pablo1","123","cr-32");
        Product producTest = new Product(3L,usuario,new Category(1L,"Categoría 1"),new StateProduct(2L,"no disponible" ),"Producto 3","Papa de la mejor calidad",1000,100L,100,100);
        Boolean result = repository.delete(producTest);
        assertTrue(result); // Espera como resultado true que nos indica una eliminacion

    }

    /**
     * Test de la funcion delete fallida
     */
    @Test
    public void testDeleteFailed() {
        Product producTest = null;
        Boolean result = repository.delete(producTest);
        assertFalse(result); // Espera como resultado false que nos indica una eliminacion fallida
    }

        /**
     * Test de la funcion de encontrar producto por propietario
     */
    @Test 
    public void testFindByOwner(){
        User user = new User(1L, new Rol(1L, "Vendedor"), new Date(0), "example@example.com", "1+234567890","1234 5678 9012 3456","Jojan Esteban", "jojanE", "password123", "123 Street, City");
        List<Product> listFindByOwner = repository.findByOwner(user);
        assertEquals(1,listFindByOwner.get(0).getId());
        assertEquals(1, listFindByOwner.size());
        assertNotNull(listFindByOwner);
    }

    /**
     *  Test de la funcion de encontrar producto por propietario fallada
     */
    @Test
    public void testFindByOwnerFailed(){
        User user = new User(60L, new Rol(1L, "Vendedor"), new Date(0), "example@example.com", "1+234567890","1234 5678 9012 3456","Jojan Esteban", "jojanE", "password123", "123 Street, City");
        List<Product> listFindByOwner = repository.findByOwner(user);
        assertEquals(0, listFindByOwner.size());
        assertTrue(listFindByOwner.isEmpty());
    }




    
}
