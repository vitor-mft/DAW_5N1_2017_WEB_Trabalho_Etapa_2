package br.edu.ifsul.controle;

import br.edu.ifsul.dao.UnidadeCondominalDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.UnidadeCondominal;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Vitor Mateus T
 */
@ManagedBean(name = "controleUnidadeCondominal")
@SessionScoped
public class ControleUnidadeCondominal implements Serializable {

    private UnidadeCondominalDAO dao;
    private UnidadeCondominal objeto;
    private PessoaDAO daoPessoa;
  

    public ControleUnidadeCondominal() {
        dao = new UnidadeCondominalDAO();
        daoPessoa = new PessoaDAO();
        
    }

    public String listar() {
        return "/privado/unidadeCondominal/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new UnidadeCondominal();
        return "formulario?faces-redirect=true";
    }

    public String salvar() {
        if (dao.salvar(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }

    public String cancelar() {
        return "listar?faces-redirect=true";
    }

    public String editar(Integer id) {
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public UnidadeCondominalDAO getDao() {
        return dao;
    }

    public void setDao(UnidadeCondominalDAO dao) {
        this.dao = dao;
    }

    public UnidadeCondominal getObjeto() {
        return objeto;
    }

    public void setObjeto(UnidadeCondominal objeto) {
        this.objeto = objeto;
    }

    public PessoaDAO getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

 

}
