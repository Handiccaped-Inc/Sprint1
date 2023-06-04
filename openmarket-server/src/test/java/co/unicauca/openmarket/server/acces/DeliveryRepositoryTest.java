package co.unicauca.openmarket.server.acces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import co.unicauca.openmarket.commons.domain.Category;
import co.unicauca.openmarket.commons.domain.Delivery;
import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.Rol;
import co.unicauca.openmarket.commons.domain.StateProduct;
import co.unicauca.openmarket.commons.domain.StatusOrder;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.server.access.DeliveryRepository;
import co.unicauca.openmarket.server.access.IDeliveryRepository;

/**
 * Pruebas Unitarias del deliveryRepository
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeliveryRepositoryTest {

    IDeliveryRepository repository = new DeliveryRepository();
    DatabaseTestManagement database = DatabaseTestManagement.getInstance();
    
    @BeforeEach
    public void cleanDatabase(){
        database.truncateTablebyName("delivery");
        database.insertDelivery();
    }

     /**
     * Metodo para limpiar la base de datos una vez se hayan ejecutado todos los Test
     */
    @AfterAll
    public void cleanDatabaseAtEnd(){
        database.truncateTablebyName("delivery");
        database.insertDelivery();
    }

    /**
     *  prueba de exito de guardar un delivery 
     */
    @Test
    public void saveDeliverySuccess(){
        User usuario = new User(1L,new Rol(1L,"Comprador"),new Date(0),"Example4@email.com","1234","1234","pablo","pablo1","123","cr-32");
        Product producTest = new Product(1L,usuario,new Category(1L,"Categoría 1"),new StateProduct(1L,"disponible" ),"Producto 1","Descripción del producto 1",10.99,50L,100,100);
        Order orderTest = new Order(2L,usuario,producTest,new StatusOrder(1L,"Enviado"),50.0,new Date(0),5.0);
        Delivery deliveryTest = new Delivery(5L, orderTest, usuario, usuario,new Date(0));
        Boolean result = repository.save(deliveryTest);
        assertTrue(result);
    }

    
    /**
     *  prueba fallada de guardar un delivery 
     */

    @Test
    public void saveDeliveryFailed(){
        Delivery deliveryTest = null;
        Boolean result = repository.save(deliveryTest);
        assertFalse(result);
    }

    
    /**
     *  prueba de exito de actualizar un delivery 
     */
    @Test
    public void UpdateDeliverySuccess(){
        User usuario = new User(1L,new Rol(1L,"Comprador"),new Date(0),"Example4@email.com","1234","1234","pablo","pablo1","123","cr-32");
        Product producTest = new Product(1L,usuario,new Category(1L,"Categoría 1"),new StateProduct(1L,"disponible" ),"Producto 1","Descripción del producto 1",10.99,50L,100,100);
        Order orderTest = new Order(3L,usuario,producTest,new StatusOrder(1L,"Enviado"),50.0,new Date(0),5.0);
        Delivery deliveryTest = new Delivery(3L, orderTest, usuario, usuario,new Date(0));
        Boolean result = repository.save(deliveryTest);
        assertTrue(result);
    }

    
    /**
     *  prueba fallada de actualizar un delivery 
     */
    @Test
    public void updateDeliveryFailed(){
        Delivery deliveryTest = null;
        Boolean result = repository.save(deliveryTest);
        assertFalse(result);
    }


    }

    

