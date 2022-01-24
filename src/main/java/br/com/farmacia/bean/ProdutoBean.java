package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {
	private ArrayList<Produtos> itens;
	private ArrayList<Produtos> itensFiltrados;
	private Produtos produto;
	private ArrayList<Fornecedores> combofornecedores;

	public ArrayList<Fornecedores> getCombofornecedores() {
		return combofornecedores;
	}

	public void setCombofornecedores(ArrayList<Fornecedores> combofornecedores) {
		this.combofornecedores = combofornecedores;
	}

	public Produtos getProduto() {
		return produto;
	}

	public void setProduto(Produtos produto) {
		this.produto = produto;
	}

	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}

	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public ArrayList<Produtos> getItens() {
		return itens;
	}

	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}

	@PostConstruct
	public void preapararLista() {
		try {
			ProdutoDAO fdao = new ProdutoDAO();
			itens = fdao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void prepararNovo() {
		produto = new Produtos();
		FornecedoresDAO dao = new FornecedoresDAO();
		try {
			combofornecedores = dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void novo() {
		try {
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.salvar(produto);
			JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso!");
			this.preapararLista();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void Excluir() {
		try {
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.excluir(produto);

			itens = fdao.listar();
			JSFUtil.adicionarMensagemSucesso("Produto excluido com sucesso!");
			this.preapararLista();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir um fornecedor que tenha um produto associado!");
			e.printStackTrace();
		}

	}

	public void Editar() {
		try {
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.editar(produto);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso!");
			this.preapararLista();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Não é possível editar");
			e.printStackTrace();
		}

	}
}
