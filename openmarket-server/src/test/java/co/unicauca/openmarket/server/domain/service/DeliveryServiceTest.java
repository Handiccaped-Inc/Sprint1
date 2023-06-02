package co.unicauca.openmarket.server.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import co.unicauca.openmarket.commons.domain.Category;
import co.unicauca.openmarket.commons.domain.Delivery;
import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.Rol;
import co.unicauca.openmarket.commons.domain.StateProduct;
import co.unicauca.openmarket.commons.domain.StatusOrder;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.server.access.IDeliveryRepository;

/**
 * Test de la clase DeliveryService
 * 
 */

public class DeliveryServiceTest {
    IDeliveryRepository repository;
    DeliveryService service;

    public DeliveryServiceTest() {
        repository = new mockDeliveryRepository();
        service = new DeliveryService(repository);
    }

    /*
     * Prueba para guardar un delivery
     */
    @Test
    public void TestSaveSucces(){
            User usuario = new User(2L, new Rol(1L, "Comprador"), new Date(0), "Example2@email.com", "123454678", "1234567890", "nombre2", "usuarioDePrueba2", "12345", "calle-1");
            Product product = new Product(2L, usuario, new Category(1L, "Juguetes"), new StateProduct(1L, "Disponible"), "Carro", "Es un carro", 1000L, 3L, 0, 0);
            Order order = new Order(1L, usuario, product, new StatusOrder(1L,"Enviado"), (double) 100L,new Date(0), 3.0);
            Delivery delivery = new Delivery(1l, order, usuario, usuario, new Date(0));
            String responde = service.save(delivery);
            assertEquals(responde, "ok");
    } 

    /*
     * Prueba para guardar un delivery fallido
     */
    @Test
    public void TestsaveFail(){
            User usuario = new User(2L, new Rol(1L, "Comprador"), new Date(0), "Example2@email.com", "123454678", "1234567890", "nombre2", "usuarioDePrueba2", "12345", "calle-1");
            Product product = new Product(2L, usuario, new Category(1L, "Juguetes"), new StateProduct(1L, "Disponible"), "Carro", "Es un carro", 1000L, 3L, 0, 0);
            Order order = null;
            Delivery delivery = new Delivery(1l, order, usuario, usuario, new Date(0));
            String responde = service.save(delivery);
            assertTrue(responde.length() > 3);
    } 

    /*
     * Prueba para actualizar un delivery
     */
    @Test
    public void UpdateDeliverySucces(){
            User usuario = new User(1L, new Rol(1L, "Comprador"), new Date(0), "Example@email.com", "123454678", "1234567890", "nombre", "usuarioDePrueba", "12345", "calle-1");
            Product product = new Product(2L, usuario, new Category(1L, "Juguetes"), new StateProduct(1L, "Disponible"), "Carro", "Es un carro", 1000L, 3L, 0, 0);
            Order order = new Order(3L, usuario, product, new StatusOrder(1L,"Devuelto"), (double) 100L,new Date(0), 3.0);
            Delivery delivery = new Delivery(1l, order, usuario, usuario, new Date(0));
            String responde = service.update(delivery);
            assertEquals(responde, "ok");
        
        }

        /*
         * Prueba para actualizar un delivery fallido
         */
        @Test
         public void updateDeliveryFail(){
            User usuario = null;
            Product product = new Product(2L, usuario, new Category(1L, "Juguetes"), new StateProduct(1L, "Disponible"), "Carro", "Es un carro", 1000L, 3L, 0, 0);
            Order order = new Order(3L, usuario, product, new StatusOrder(1L,"Devuelto"), (double) 100L,new Date(0), 3.0);
            Delivery delivery = new Delivery(1l, order, usuario, usuario, new Date(0));
            String responde = service.update(delivery);
            assertTrue(responde.length() > 3);
        
        }




    private class mockDeliveryRepository implements IDeliveryRepository {
        List<Delivery> deliveries;

        public mockDeliveryRepository(){
            deliveries = new ArrayList<>();
            User usuario = new User(1L, new Rol(1L, "Comprador"), new Date(0), "Example@email.com", "123454678", "1234567890", "nombre", "usuarioDePrueba", "12345", "calle-1");
            Product product = new Product(2L, usuario, new Category(1L, "Juguetes"), new StateProduct(1L, "Disponible"), "Carro", "Es un carro", 1000L, 3L, 0, 0);
            Order order = new Order(3L, usuario, product, new StatusOrder(1L,"Enviado"), (double) 100L,new Date(0), 3.0);
            Delivery delivery = new Delivery(1l, order, usuario, usuario, new Date(0));
            deliveries.add(delivery);
        }

        @Override
        public boolean save(Delivery newDelivery) {
            // TODO Auto-generated method stub
            return this.deliveries.add(newDelivery);
        }

        @Override
        public boolean update(Delivery newDelivery) {
            // TODO Auto-generated method stub
            for (Delivery delivery : deliveries) {
                if(delivery.getId() == newDelivery.getId()){
                    delivery.setDate(newDelivery.getDate());
                    delivery.setDeliveryMan(newDelivery.getDeliveryMan());
                    delivery.setId(newDelivery.getId());
                    delivery.setOrder(newDelivery.getOrder());
                    return true;
                }

                }
                return false;
            }
        }

    }
    

