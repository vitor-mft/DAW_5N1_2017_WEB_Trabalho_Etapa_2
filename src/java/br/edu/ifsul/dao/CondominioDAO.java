
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.modelo.UnidadeCondominal;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Vitor Mateus T
 */
public class CondominioDAO<T> extends DAOGenerico<Condominio> implements Serializable {

  
    private List<UnidadeCondominal> listaUnCondominal;

    public CondominioDAO() {
        super();
        super.setClassePersistente(Condominio.class);
    }
    
    //parte apra usar no Aluguel. 

    public List<UnidadeCondominal> getListaUnCondominal() {
        String jpql = "from " + UnidadeCondominal.class.getSimpleName() + " order by numero";
        return em.createQuery(jpql).getResultList();
    }

    public void setListaUnCondominal(List<UnidadeCondominal> listaUnCondominal) {
        this.listaUnCondominal = listaUnCondominal;
    }

}
