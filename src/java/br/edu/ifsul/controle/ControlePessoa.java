package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author Vitor Mateus T
 */
@ManagedBean(name = "controlePessoa")
@SessionScoped
public class ControlePessoa implements Serializable {
  
    private PessoaDAO<Pessoa> dao;
    private Pessoa objeto;

    public ControlePessoa() {
        dao = new PessoaDAO<>();
    }

    public String listar() {
        return "/privado/pessoa/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Pessoa();

        return "formulario";
    }

    public String salvar() {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
            return "listar";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario";
        }
    }

    public String cancelar() {
        return "listar";
    }

    public String editar(Integer id) {
        try {
            objeto = dao.localizar(id);
            return "formulario";
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto" + Util.getMensagemErro(e));
            return "listar";
        }
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }

    }

    public PessoaDAO getDao() {
        return dao;
    }

    public void setDao(PessoaDAO dao) {
        this.dao = dao;
    }

    public Pessoa getObjeto() {
        return objeto;
    }

    public void setObjeto(Pessoa objeto) {
        this.objeto = objeto;
    }

}
