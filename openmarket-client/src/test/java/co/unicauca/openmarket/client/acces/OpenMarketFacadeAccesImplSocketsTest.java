package co.unicauca.openmarket.client.acces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import co.unicauca.openmarket.client.access.OpenMarketFacadeAccesImplSockets;
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
    
}
