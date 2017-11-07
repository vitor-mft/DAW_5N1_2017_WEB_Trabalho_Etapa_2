
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Locatario;
import java.io.Serializable;

/**
 *
 * @author Vitor Mateus T
 */
public class LocatarioDAO<T> extends DAOGenerico<Locatario> implements Serializable {

    public LocatarioDAO() {
        super();
        super.setClassePersistente(Locatario.class);
    }

}
