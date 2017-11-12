package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CondominioDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.dao.RecursoDAO;
import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Recurso;
import br.edu.ifsul.modelo.UnidadeCondominal;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author Vitor Mateus T
 */
@ManagedBean(name = "controleCondominio")
@SessionScoped
public class ControleCondominio implements Serializable {
    private CondominioDAO<Condominio> dao;
    private Condominio objeto;
    private UnidadeCondominal unCondominal;
    private Boolean novoUnCondominal;
    private PessoaDAO<Pessoa> daoPessoa;
    
    

    public ControleCondominio() {
        dao = new CondominioDAO<>();
        daoPessoa = new PessoaDAO<>();
        
    }

    public String listar() {
        return "/privado/condominio/listar?faces-redirect=true";
    }
 
    public void novo() {
        objeto = new Condominio();
    }

    public void salvar() {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public void editar(Integer id) {
        try {
            dao.rollback();
            objeto = dao.localizar(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto" + Util.getMensagemErro(e));
        }
    }

    public void remover(Integer id) {
        try {
            objeto = dao.localizar(id);
            if(dao.remover(objeto)){
                Util.mensagemInformacao(dao.getMensagem());
            } else {
                Util.mensagemErro(dao.getMensagem());
            }  
        } catch(Exception e){
            Util.mensagemErro(e.getMessage());
        }
    }

    public void novoUnCondominal(){
        unCondominal = new UnidadeCondominal(); 
        novoUnCondominal = true;
    }
    
    public void alterarUnCondominal(int index){
        unCondominal = objeto.getUnidades_condominais().get(index);
        novoUnCondominal = false;
    }
    
    public void salvarUnCondominal(){
        if (novoUnCondominal){
            objeto.adicionarUnidadesCondominais(unCondominal);
        }
        Util.mensagemInformacao("Unidade Condominal salva com sucesso!");
    }
    
    public void removerUnCondominal(int index){
        objeto.removerUnidadesCondominais(index);
        Util.mensagemInformacao("Unidade Condominal removida com sucesso!");
    }
    
    public CondominioDAO<Condominio> getDao() {
        return dao;
    }

    public void setDao(CondominioDAO<Condominio> dao) {
        this.dao = dao;
    }

    public Condominio getObjeto() {
        return objeto;
    }

    public void setObjeto(Condominio objeto) {
        this.objeto = objeto;
    }

    public UnidadeCondominal getUnCondominal() {
        return unCondominal;
    }

    public void setUnCondominal(UnidadeCondominal unCondominal) {
        this.unCondominal = unCondominal;
    }

    public Boolean getNovoUnCondominal() {
        return novoUnCondominal;
    }

    public void setNovoUnCondominal(Boolean novoUnCondominal) {
        this.novoUnCondominal = novoUnCondominal;
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }


}