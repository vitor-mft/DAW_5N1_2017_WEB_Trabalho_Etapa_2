
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Recurso;
import java.io.Serializable;

/**
 *
 * @author Vitor Mateus T
 */
public class RecursoDAO<T> extends DAOGenerico<Recurso> implements Serializable{
    
    public RecursoDAO() {
        super();
        super.setClassePersistente(Recurso.class);    
    } 
}
