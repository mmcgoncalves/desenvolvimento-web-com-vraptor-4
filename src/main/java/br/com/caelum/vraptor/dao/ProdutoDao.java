package br.com.caelum.vraptor.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.model.Produto;

@RequestScoped
public class ProdutoDao {

	private final EntityManager em;

	@Inject
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
    @Deprecated
    ProdutoDao() {
        this(null); // para uso do CDI
    }
	
    public void adiciona(Produto produto) {
        em.persist(produto);
    }

    public void remove(Produto produto) {
        em.remove(em.contains(produto) ? produto : em.merge(produto));
    }

    public void busca(Produto produto) {
        em.find(Produto.class, produto.getId());
    }

    @SuppressWarnings("unchecked")
    public List<Produto> lista() {
        return em.createQuery("select p from Produto p").getResultList();
    }
}