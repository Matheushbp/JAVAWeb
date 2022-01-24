package ProdutoDAOTeste;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;

public class TesteProdutoDAO {
	public static void main(String[] args) throws SQLException {
		ProdutoDAO pdao = new ProdutoDAO();
		ArrayList<Produtos> lista = pdao.listar();
		Produtos pd = new Produtos();
		Fornecedores f = new Fornecedores();
		f.setCodigo(4);
		pd.setCodigo((long) 2);
		
		pdao.excluir(pd);
		pd.setCodigo((long) 3);
		pd.setDescricao("Brasil");
		pd.setPreco(3.40);
		pd.setFornecedores(f);
		pd.setQuantidade((long) 30);
		
		for(Produtos p : lista) {
			System.out.println("codigo: " + p.getCodigo());
			System.out.println("Descricao: " + p.getDescricao());
			System.out.println("Preco: " + p.getPreco());
			System.out.println("Quantidade: " + p.getQuantidade());
			System.out.println("codigo do fornecedor: " + p.getFornecedores().getCodigo());
			System.out.println("descricao do fornecedor" + p.getFornecedores().getDescricao());
		}
		
		pdao.editar(pd);
		
	}
}