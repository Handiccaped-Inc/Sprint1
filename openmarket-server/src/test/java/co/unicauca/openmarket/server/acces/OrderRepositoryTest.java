package co.unicauca.openmarket.server.acces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import co.unicauca.openmarket.commons.domain.Category;
import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.Rol;
import co.unicauca.openmarket.commons.domain.StateProduct;
import co.unicauca.openmarket.commons.domain.StatusOrder;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.server.access.IOrderRepository;
import co.unicauca.openmarket.server.access.OrderRepository;

public class OrderRepositoryTest {

    IOrderRepository repository = new OrderRepository();
    DatabaseTestManagement database = DatabaseTestManagement.getInstance();

    @BeforeAll
    public void cleanDatabase(){
        database.truncateTablebyName("Order");
        database.insertOrders();
    }

    @Test
    public void testFindByStateSuccess(){
        List<Order> listFindByState = repository.findByState(new StatusOrder(1L,"Entregado"));
        assertEquals(1, listFindByState.size());
        assertEquals(1, listFindByState.get(0).getCustomer().getId());
        assertFalse(listFindByState.isEmpty());
    }

    
    @Test
    public void testFindByStatefailed(){
        List<Order> listFindByState = repository.findByState(new StatusOrder(4L,"Aduanas"));
        assertEquals(0, listFindByState.size());
        assertTrue(listFindByState.isEmpty());
    }

    @Test
    public void testFindByUserSuccess(){
        List<Order> listFindByUser = repository.findByUser(1L);
        assertEquals(1, listFindByUser.size());
        assertEquals(10.99, listFindByUser.get(0).getPrice());
        assertFalse(listFindByUser.isEmpty());
    }
    @Test
    public void testFindByUserFailed(){
        List<Order> listFindByUser = repository.findByUser(4L);
        assertEquals(0, listFindByUser.size());
        assertTrue(listFindByUser.isEmpty());

    }

    /**
     * 
     */
    @Test
    public void testSaveOrderSuccess() {
        User usuario = new User(1L,new Rol(1L,"Comprador"),new Date(0),"Example4@email.com","1234","1234","pablo","pablo1","123","cr-32");
        Product producTest = new Product(1L,usuario,new Category(1L,"Categoría 1"),new StateProduct(1L,"disponible" ),"Producto 1","Descripción del producto 1",10.99,50L,100,100);
        Order orderTest = new Order(4L,usuario,producTest,new StatusOrder(1L,"Enviado"),50.0,new Date(0),5.0);

    }
    
}
