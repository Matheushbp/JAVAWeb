package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {
	private ArrayList<Fornecedores> itens;
	private ArrayList<Fornecedores> itensFiltrados;
	private Fornecedores fornecedores;

	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}

	public ArrayList<Fornecedores> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fornecedores> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Fornecedores> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Fornecedores> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void preapararLista() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			itens = fdao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void prepararNovo() {
		fornecedores = new Fornecedores();
	}

	public void novo() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.salvar(fornecedores);
			JSFUtil.adicionarMensagemSucesso("Fornecedor salvo com sucesso!");
			this.preapararLista();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void Excluir() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.excluir(fornecedores);

			itens = fdao.listar();
			JSFUtil.adicionarMensagemSucesso("Fornecedor excluido com sucesso!");
			this.preapararLista();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir um fornecedor que tenha um produto associado!");
			e.printStackTrace();
		}

	}

	public void Editar() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.editar(fornecedores);

			itens = fdao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Fornecedor editado com sucesso!");
			this.preapararLista();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Não é possível editar");
			e.printStackTrace();
		}

	}

}
