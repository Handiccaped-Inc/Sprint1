package co.unicauca.openmarket.server.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import co.unicauca.openmarket.commons.domain.Category;
import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.Rol;
import co.unicauca.openmarket.commons.domain.StateProduct;
import co.unicauca.openmarket.commons.domain.StatusOrder;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.server.access.IOrderRepository;

/**
 * Pruebas unitarias del OrderService
 */
public class OrderServiceTest {

    IOrderRepository repository;
    OrderService service;


    public OrderServiceTest(){
        repository = new mockOrderRepository();
        service = new OrderService(repository);
    }

    /**
     * Prueba para guardar una orden
     */
    @Test
    public void testsaveSuccess(){
        User usuario = new User(4L, new Rol(1L,"Comprador"), new Date(0), "Example5@emai.com","12345","12345","TestName5", "TestUsername5", "1234", "cr-4");
        User usuario2 = new User(2L, new Rol(1L,"Vendedor"), new Date(0), "Example2@Email.com","2345","12345","TestName2", "TestUsername2", "1234", "cr-1");
        Product product = new Product(1L, usuario2, new Category(1L,"Juguetes"), new StateProduct(1L,"Disponible"), "Carro", "Es un carro", 1000L, 3L, 0, 0);
        Order order = new Order(3L, usuario, product, new StatusOrder(1L,"Enviado"), (double) 100L,new Date(0), 3.0);
        String responde = service.save(order);
        assertEquals(responde, "ok"); // se espera que la orden se guarde
        assertEquals(service.findByUser(4L).get(0).getId(), order.getId()); // se espera que el id de la orden encontrada sea 4
    }

    /**
     *  Prueba para guardar una orden fallida
     */
    @Test
    public void testsaveFailed(){
        User usuario = null;
        User usuario2 = new User(2L, new Rol(1L,"Vendedor"), new Date(0), "Example2@Email.com","2345","12345","TestName2", "TestUsername2", "1234", "cr-1");
        Product product = new Product(1L, usuario2, new Category(1L,"Juguetes"), new StateProduct(1L,"Disponible"), "Carro", "Es un carro", 1000L, 3L, 0, 0);
        Order order = new Order(3L, usuario, product, new StatusOrder(1L,"Enviado"), (double) 100L,new Date(0), 3.0);
        String responde = service.save(order);
        assertTrue(responde.length() > 3); // se espera que la orden no se guarde
        assertEquals(service.findByUser(5L).size(), 0); // se espera que el tamaño del arreglo de regreso sea 0
    }

    /**
     *  Prueba para actualizar una orden
     */
    @Test
    public void testupdateSucces(){
        User usuario2 = new User(2L, new Rol(1L,"Vendedor"), new Date(0), "Example2@Email.com","2345","12345","TestName2", "TestUsername2", "1234", "cr-1");
        User usuario3 = new User(3L, new Rol(1L,"Comprador"), new Date(0), "Example3@emai.com","3456","12345","TestName3", "TestUsername3", "1234", "cr-2");
        Product product1 = new Product(1L, usuario2, new Category(1L,"Juguetes"), new StateProduct(1L,"Disponible"), "Carro", "Es un carro", 1000L, 3L, 0, 0);
        Order orden2 = new Order(2L, usuario3, product1, new StatusOrder(2L,"Entregado"), (double) 100L,new Date(0), 3.0);
        String responde = service.update(orden2);
        assertEquals(responde, "ok");
        assertEquals(service.findByUser(3L).get(0).getStatus().getName(),"Entregado");
    }

    /*
     * Prueba para actualizar una orden fallida
     */
    @Test
    public void TestupdateFailed(){
        User usuario2 = new User(2L, new Rol(1L,"Vendedor"), new Date(0), "Example2@Email.com","2345","12345","TestName2", "TestUsername2", "1234", "cr-1");
        User usuario3 = null;
        Product product1 = new Product(1L, usuario2, new Category(1L,"Juguetes"), new StateProduct(1L,"Disponible"), "Carro", "Es un carro", 1000L, 3L, 0, 0);
        Order orden2 = new Order(2L, usuario3, product1, new StatusOrder(2L,"Entregado"), (double) 100L,new Date(0), 3.0);
        String responde = service.update(orden2);
        assertTrue(responde.length() > 3);
        assertEquals(service.findByUser(3L).get(0).getStatus().getName(),"Devuelto");
    }

    /*
     * Prueba para encontrar una orden por su estado
     */
    @Test
    public void FinbyStateTestSucces(){
        List<Order> orderFindByState = service.findByState(new StatusOrder(1L,"Enviado"));
        assertEquals(orderFindByState.size(), 1);
    }

    /* 
     * Prueba para encontrar una orden por su estado fallida
     */
    @Test
    public void FinbyStateFailed(){
        List<Order> orderFindByState = service.findByState(new StatusOrder(3L,"Recibido"));
        assertTrue(orderFindByState.isEmpty());
    }

    /*
     * Prueba para encontrar una orden por su usuario
     */
    @Test
    public void findByUserSucces(){
        List<Order> orderFindByUser = service.findByUser(1L);
        assertEquals(orderFindByUser.size(), 1);
    }

    /*
     * Prueba para encontrar una orden por su usuario fallida
     */
    @Test
    public void findByUserFailed(){
        List<Order> orderFindByUser = service.findByUser(6L);
        assertTrue(orderFindByUser.isEmpty());
    }

    
    private class mockOrderRepository implements IOrderRepository{

        List<Order> orders;

        public mockOrderRepository(){
            orders = new ArrayList<>();
            User usuario1 = new User(1L, new Rol(1L,"Comprador"), new Date(0), "Example@Email.com", "12235", "12345","TestName", "TestUsername", "1234", "cr-0");
            User usuario2 = new User(2L, new Rol(1L,"Vendedor"), new Date(0), "Example2@Email.com","2345","12345","TestName2", "TestUsername2", "1234", "cr-1");
            User usuario3 = new User(3L, new Rol(1L,"Comprador"), new Date(0), "Example3@emai.com","3456","12345","TestName3", "TestUsername3", "1234", "cr-2");
            Product product1 = new Product(1L, usuario2, new Category(1L,"Juguetes"), new StateProduct(1L,"Disponible"), "Carro", "Es un carro", 1000L, 3L, 0, 0);
            Order orden1 = new Order(1L, usuario1, product1, new StatusOrder(1L,"Enviado"), (double) 100L,new Date(0), 3.0);
            Order orden2 = new Order(2L, usuario3, product1, new StatusOrder(2L,"Devuelto"), (double) 100L,new Date(0), 3.0);
            orders.add(orden1);
            orders.add(orden2);
        }

        @Override
        public boolean save(Order newOrder) {
            return orders.add(newOrder);
        }

        @Override
        public boolean update(Order newOrder) {
            // TODO Auto-generated method stub
            for (Order order : orders) {
                if(order.getId() == newOrder.getId()){
                    order.setCustomer(newOrder.getCustomer());
                    order.setDate(newOrder.getDate());
                    order.setPrice(newOrder.getPrice());
                    order.setProduct(newOrder.getProduct());
                    order.setQualification(newOrder.getQualification());
                    order.setStatus(newOrder.getStatus());
                    return true;
                }
            }
            return false;
        }

        @Override
        public List<Order> findByState(StatusOrder status) {
            List<Order> ordersbYState = new ArrayList<>();
            for (Order order : orders) {
                if(order.getStatus().getId() == status.getId()){
                    ordersbYState.add(order);
                }
            }
            return ordersbYState;
        }

        @Override
        public List<Order> findByUser(Long UserId) {
            List<Order> ordersByUser = new ArrayList<>();
            for (Order order : orders) {
                if(order.getCustomer().getId() == UserId){
                    ordersByUser.add(order);
                }
            }
            return ordersByUser;
        }

    }


    
}
