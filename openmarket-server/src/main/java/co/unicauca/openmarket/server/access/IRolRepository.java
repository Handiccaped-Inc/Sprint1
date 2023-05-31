package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Rol;
/**
 * Interface del respositorio de roles
 * @author Arturo Restrepo Ruiz
 */
public interface IRolRepository {
   /**
     * Busca un rol por id
     * @param id id del rol
     * @return retorna el rol
     */
    public Rol findById(long id);
}
