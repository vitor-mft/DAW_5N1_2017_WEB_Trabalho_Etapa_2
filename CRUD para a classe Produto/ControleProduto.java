package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ProdutoDAO;
import br.edu.ifsul.dao.EstadoDAO;
import br.edu.ifsul.dao.GrupoDAO;
import br.edu.ifsul.dao.MarcaDAO;
import br.edu.ifsul.modelo.Grupo;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Vitor Mateus T
 */
@ManagedBean(name = "controleProduto")
@SessionScoped
public class ControleProduto implements Serializable {

    private ProdutoDAO dao;
    private Produto objeto;
    private GrupoDAO daoGrupo;
    private MarcaDAO daoMarca;

    public ControleProduto() {
        dao = new ProdutoDAO();
        daoGrupo = new GrupoDAO();
        daoMarca = new MarcaDAO();
    }

    public String listar() {
        return "/privado/produto/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Produto();
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

    public ProdutoDAO getDao() {
        return dao;
    }

    public void setDao(ProdutoDAO dao) {
        this.dao = dao;
    }

    public Produto getObjeto() {
        return objeto;
    }

    public void setObjeto(Produto objeto) {
        this.objeto = objeto;
    }

    public GrupoDAO getDaoGrupo() {
        return daoGrupo;
    }

    public void setDaoGrupo(GrupoDAO daoGrupo) {
        this.daoGrupo = daoGrupo;
    }

    public MarcaDAO getDaoMarca() {
        return daoMarca;
    }

    public void setDaoMarca(MarcaDAO daoMarca) {
        this.daoMarca = daoMarca;
    }

}
