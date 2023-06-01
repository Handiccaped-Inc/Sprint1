package co.unicauca.openmarket.server.domain.service;

import org.junit.jupiter.api.Test;

import co.unicauca.openmarket.commons.domain.Category;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.Rol;
import co.unicauca.openmarket.commons.domain.StateProduct;
import co.unicauca.openmarket.commons.domain.StatusOrder;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.server.access.IProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.Provider.Service;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/*
 * Pruebas unitarias del servicio de productos
 */
public class ProductServiceTest {

    private ProductService service;
    private IProductRepository repository;

    ProductServiceTest(){
        repository = new MockProductRepository();
        service = new ProductService(repository);
    }

    /*
     * Prueba para encontrar un producto por su nombre y descripcion
     */
    @Test
    public void TestFinbyNameAndDescriptionSucces(){
        List<Product> productsTest = service.findByNameAndDescription("Carro","This is an example product");
        assertFalse(productsTest.isEmpty());
    }

    /*
     * Prueba de fallo para encontrar un producto por su nombre y descripcion
     */
    @Test
    public void TestFinbyNameAndDescriptionFaild(){
        List<Product> productsTest = service.findByNameAndDescription("Non Product","This is an example product");
        assertTrue(productsTest.isEmpty());
    }
    
    /*
     * Prueba para encontrar un producto por su estado
     */

    @Test
    public void finbyEstatusSucces(){
        List<Product> productsTest = service.findByStatus(new StateProduct(1L,"disponible"));
        assertFalse(productsTest.isEmpty());
        assertEquals(1L,productsTest.get(0).getId());
    }

    /*
     * prueba de fallo para encontrar un producto por su estado
     */
    @Test
    public void finbyEstatusFailed(){
        List<Product> productsTest = service.findByStatus(new StateProduct(1L," "));
        assertTrue(productsTest.isEmpty());
    }

    /*
     * Prueba para encontrar un producto por su id
     */
    @Test
    public void findByIdSucces(){
        Product productTest = service.findById(1L);
        assertEquals(1L,productTest.getId());
    }

    /*
     * Prueba de fallo para encontrar un producto por su id
     */
    @Test
    public void findByIdFaild(){
        Product productTest = service.findById(3L);
        assertNull(productTest);
    }

    /*
     * Prueba para guardar un producto
     */
    @Test
    public void SaveProductSucces(){
        User user = new User(1L, new Rol(1L, "Vendedor"), new Date(0), "example@example.com", "1+234567890","1234 5678 9012 3456","Jojan Esteban", "jojanE", "password123", "123 Street, City");
        Product product = new Product(3L, user, new Category(3L,"Tecnologia"), new StateProduct(2L,"no disponible"), "Tv", "This is an example product", 10.99, 50L, 0.0, 0.0);
        service.save(product);
        Product testProdutAux = service.findById(3L);
        assertEquals(product, testProdutAux);

    }

    /*
     * Prueba de fallo para guardar un producto
     */

    @Test
    public void SaveProductFail(){
        User user = null;
        Product product = new Product(3L, user, new Category(3L,"Tecnologia"), new StateProduct(2L,"no disponible"), "Tv", "This is an example product", 10.99, 50L, 0.0, 0.0);
        service.save(product);
        Product testProdutAux = service.findById(3L);
        assertNotEquals(product, testProdutAux);

    }
    

    /*
     * Prueba de fallo para guardar un producto
     */
    @Test
    public void SaveProductFail2(){
        User user = null;
        Product product = new Product(3L, user,null, new StateProduct(2L,"no disponible"), "Tv", "This is an example product", 10.99, 50L, 0.0, 0.0);
        service.save(product);
        Product testProdutAux = service.findById(3L);
        assertNotEquals(product, testProdutAux);

    }

    /*
     * Prueba para editar un producto
     */
    @Test
    public void editProdutSucces(){
        User user = new User(1L, new Rol(1L, "Vendedor"), new Date(0), "example@example.com", "1+234567890","1234 5678 9012 3456","Jojan Esteban", "jojanE", "password123", "123 Street, City");
        Product product = new Product(1L, user, new Category(1L,"Juguetes"), new StateProduct(2L,"no disponible"), "Carro", "This is an example product", 66.99, 100L, 0.0, 0.0);
        service.update(product);
        Product testProdutAux = service.findById(1L);
        assertEquals(product.getId(), testProdutAux.getId());
    }

    /*
     * Prueba de fallo para editar un producto
     */
    @Test
    public void editProdutfail(){
        User user = null;
        Product product = new Product(1L, user, null, new StateProduct(2L,"no disponible"), "Carro", "This is an example product", 66.99, 100L, 0.0, 0.0);
        service.update(product);
        Product testProdutAux = service.findById(1L);
        assertNotEquals(product, testProdutAux);
        
    }

    /*
     * Prueba para eliminar un producto
     */
    @Test
    public void DeleteProductSucces(){
        User user = new User(1L, new Rol(1L, "Vendedor"), new Date(0), "example@example.com", "1+234567890","1234 5678 9012 3456","Jojan Esteban", "jojanE", "password123", "123 Street, City");
        Product product = new Product(1L, user, new Category(1L,"Juguetes"), new StateProduct(1L,"disponible"), "Carro", "This is an example product", 9.99, 100L, 0.0, 0.0);
        String response = service.delete(product);
        assertEquals("ok", response);
    }

    /*
     * Prueba de fallo para eliminar un producto
     */
    @Test
    public void DeleteProductFail(){
        User user = null;
        Product product = new Product(1L, user, new Category(1L,"Juguetes"), new StateProduct(1L,"disponible"), "Carro", "This is an example product", 9.99, 100L, 0.0, 0.0);
        service.delete(product);
        Product response = service.findById(1L);
        assertNotNull(response);

    }


    
    private class MockProductRepository implements IProductRepository{
        List<Product> products;

        public MockProductRepository(){
            products = new ArrayList<>();
            User user = new User(1L, new Rol(1L, "Vendedor"), new Date(0), "example@example.com", "1+234567890","1234 5678 9012 3456","Jojan Esteban", "jojanE", "password123", "123 Street, City");
            Product product1 = new Product(1L, user, new Category(1L,"Juguetes"), new StateProduct(1L,"disponible"), "Carro", "This is an example product", 9.99, 100L, 0.0, 0.0);
            Product product2 = new Product(2L, user, new Category(2L,"Ropa"), new StateProduct(2L,"no disponible"), "Jean", "This is an example product", 10.99, 50L, 0.0, 0.0);
            products.add(product2);
            products.add(product1);
        }

        @Override
        public boolean save(Product newProduct) {
            
            return products.add(newProduct);
        }
        

        @Override
        public boolean update(Product newProduct) {
            for (Product product : products) {
                if(product.getId() == newProduct.getId()){
                    product = newProduct;
                    return true;
                }
            }

            return false;
        }

        @Override
        public boolean delete(Product newProduct) {
            for (Product product : products) {
                if(product.getId() == newProduct.getId()){
                    products.remove(newProduct);
                    return true;
                }
            }
            return false;
        }

        @Override
        public List<Product> findByStatus(StateProduct status) {
            List<Product> productsbyStatus = new ArrayList<>();
            for (Product product : products) {
                if(product.getState().getId().equals(status.getId()) && product.getState().getName().equals(status.getName())){
                    productsbyStatus.add(product);
                }
            }
            return productsbyStatus;
        }

        @Override
        public List<Product> findByNameAndDescription(String name, String description) {
            List<Product> productsFindByNameAndDescrip = new ArrayList<>();
            for (Product product : products) {
                if(product.getName() == name && product.getDescription() == description){
                    productsFindByNameAndDescrip.add( product);
                }
            }

            return productsFindByNameAndDescrip;
        }

        @Override
        public Product findById(Long id) {
            for (Product product : products) {
                if (product.getId() == id) {
                    return product;
                }
            }
            return null;
        }
    }
}
