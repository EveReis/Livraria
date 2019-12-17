package contoller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Livro;
import model.DAO.LivroDAO;

public class LivroController {
    
    private Livro LivroSelecionado;
    private List<Livro> tabelaDeLivros;
    private LivroDAO lDAO;
    
    public LivroController() {
        lDAO = new LivroDAO();
    }
    
    public void listarTodos(DefaultTableModel modeloTabela) {
        modeloTabela.setNumRows(0);
        List<Livro> listaLivros = lDAO.buscarTodos();

        for (Livro l : listaLivros) {
            modeloTabela.addRow(new Object[]{l.getISBN(), l.getTitulo(), l.getAutor() , l.getCategoria() ,
                l.getPaginas(), l.getPreco(), l.isStatus() ? "1 - Ativo" : "2 - Inativo"});
        }
    }
    
    public void listarPorISBN(DefaultTableModel modeloTabela, int ISBN) {
        modeloTabela.setNumRows(0);
        Livro livroBuscado = lDAO.buscarPorISBN(ISBN);

        modeloTabela.addRow(new Object[]{livroBuscado.getISBN(), livroBuscado.getTitulo(),
            livroBuscado.getAutor(), livroBuscado.getCategoria(),livroBuscado.getPaginas(),
            livroBuscado.getPreco(), livroBuscado.isStatus() ? "1 - Ativo" : "2 - Inativo"});
    }
    
     public void listarPorPreco(DefaultTableModel modeloTabela, double Preco){
        modeloTabela.setNumRows(0);
        Livro livroBuscado = lDAO.buscarPorPreco(Preco);
        
        modeloTabela.addRow(new Object[]{livroBuscado.getPreco(), livroBuscado.getISBN(),
            livroBuscado.getTitulo(), livroBuscado.getAutor(), livroBuscado.getCategoria(),
            livroBuscado.getPaginas(),livroBuscado.getPreco(),
            livroBuscado.isStatus() ? "1 - Ativo" : "2 - Inativo"});
        }
     
     public void salvar(DefaultTableModel modeloTabela, Livro livro, boolean novo ) {
         System.out.println(livro);
        if( novo ) {
            lDAO.inserir(livro);
        } else {
            lDAO.atualizar(livro);
        }
        this.listarTodos(modeloTabela);
    }
     
     public void excluir(DefaultTableModel modeloTabela, Livro livro ) {
        System.out.println("Excluindo livro No.: " + livro.getISBN());
        if( livro.getISBN() != 0 ) {
            lDAO.excluir(livro.getISBN());
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível excluir as informações.\nLivro não localizado.", "Erro ao excluir", JOptionPane.ERROR_MESSAGE);
        }
        this.listarTodos(modeloTabela);
    }
     
     
}
