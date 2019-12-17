package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Livro;
import DB.Connect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LivroDAO implements iDAO<Livro> {
    private final String INSERT = "INSERT INTO livro(TITULO, AUTOR,CATEGORIA, STATUS,PRECO, PAGINAS, ISBN) VALUES (?,?, ?, ?, ?,?,?)";
    private final String UPDATE = "UPDATE vendedor SET PAGINAS=?, TITULO=?, CATEGORIA=?, PRECO=?, AUTOR=?,STATUS=? WHERE ISBN =?";
    private final String DELETE = "DELETE FROM livro WHERE ISBN =?";
    private final String LISTALL = "SELECT * FROM livro";
    private final String LISTBYISBN = "SELECT * FROM livro WHERE ISBN=?";
    private final String LISTBYPRECO = "SELECT * FROM livro WHERE PRECO like ?";
    
    private Connect conn = null;
    private Connection conexao = null;

    @Override
    public Livro inserir(Livro novoLivro) {
    conexao = this.getConnect().connection;
        if (novoLivro != null && conexao != null) {
            try {
                PreparedStatement transacaoSQL;
                transacaoSQL = conexao.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                
                //INSERT INTO livro(TITULO, AUTOR,CATEGORIA, STATUS,PRECO, PAGINAS, ISBN)

                transacaoSQL.setString(1, novoLivro.getTitulo());
                transacaoSQL.setString(2, novoLivro.getAutor());
                transacaoSQL.setString(3, novoLivro.getCategoria());
                transacaoSQL.setBoolean(4, novoLivro.isStatus());
                transacaoSQL.setDouble(5, novoLivro.getPreco());
                transacaoSQL.setInt(6, novoLivro.getPaginas());
                transacaoSQL.setInt(7, novoLivro.getISBN());
                
                

                transacaoSQL.execute();
                JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso", "Registro inserido", JOptionPane.INFORMATION_MESSAGE);

                try (ResultSet generatedKeys = transacaoSQL.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        novoLivro.setISBN(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Não foi possível recuperar o ID.");
                    }
                }

                conn.fechaConexao(conexao, transacaoSQL);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir o Livro no banco de" + "dados. \n" + e.getMessage(), "Erro na transação SQL", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Os dados do livro não podem estar vazios.", "Livro não informado", JOptionPane.ERROR_MESSAGE);
        }

        return novoLivro;
    }
    

    @Override
    public Livro atualizar(Livro livroEditado) {
        conexao = this.getConnect().connection;
        if (livroEditado != null && conexao != null) {
            try {
                PreparedStatement transacaoSQL;
                transacaoSQL = conexao.prepareStatement(UPDATE);

                // INSERT INTO livro(TITULO, AUTOR,CATEGORIA, STATUS,PRECO, PAGINAS, ISBN)
                
                transacaoSQL.setString(1, livroEditado.getTitulo());
                transacaoSQL.setString(2, livroEditado.getAutor());
                transacaoSQL.setString(3, livroEditado.getCategoria());
                transacaoSQL.setBoolean(4, livroEditado.isStatus());
                transacaoSQL.setDouble(5, livroEditado.getPreco());
                transacaoSQL.setInt(6, livroEditado.getPaginas());
                transacaoSQL.setInt(7, livroEditado.getISBN());

                int resultado = transacaoSQL.executeUpdate();

                if (resultado == 0) {
                    JOptionPane.showMessageDialog(null, "Não foi possível atualizar as informações", "Erro ao atualizar", JOptionPane.ERROR_MESSAGE);
                    throw new SQLException("Creating book failed, no rows affected.");
                }

                JOptionPane.showMessageDialog(null, "Livro atualizado com sucesso", "Registro atualizado", JOptionPane.INFORMATION_MESSAGE);

                conn.fechaConexao(conexao, transacaoSQL);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir o livro no banco de" + "dados. \n" + e.getMessage(), "Erro na transação SQL", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Os dados do livro não podem estar vazios.", "Livro não informado", JOptionPane.ERROR_MESSAGE);
        }

        return livroEditado;
    }

    @Override
    public void excluir(int isbnLivro) {
        int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este livro?", "Confirmar exclusão",
			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        // 0 - Sim  1 - Não
        if(confirmar == 1) {
            return;
        }
        conexao = this.getConnect().connection;
        if (conexao != null) {
            try {
                PreparedStatement transacaoSQL;
                transacaoSQL = conexao.prepareStatement(DELETE);

                transacaoSQL.setInt(1, isbnLivro);

                boolean erroAoExcluir = transacaoSQL.execute();

                if (erroAoExcluir) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir", "Não foi possível excluir as informações", JOptionPane.ERROR_MESSAGE);
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                JOptionPane.showMessageDialog(null, "Registro excluido", "Livro excluido com sucesso", JOptionPane.INFORMATION_MESSAGE);

                conn.fechaConexao(conexao, transacaoSQL);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro na transação SQL", "Erro ao excluir do livro no banco de" + "dados. \n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Problemas de conexão", "Não foi possível se conectar ao banco.", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public List<Livro> buscarTodos() {
        conexao = this.getConnect().connection;

        ResultSet resultado = null;
        ArrayList<Livro> livros = new ArrayList<Livro>();

        if (conexao != null) {
            try {
                PreparedStatement transacaoSQL;
                transacaoSQL = conexao.prepareStatement(LISTALL);

                resultado = transacaoSQL.executeQuery();

                while (resultado.next()) {
                    Livro livroEncontrado = new Livro();

                    //INSERT INTO livro(TITULO, AUTOR,CATEGORIA, STATUS,PRECO, PAGINAS, ISBN)
                    
                    
                    livroEncontrado.setTitulo(resultado.getString("Titulo"));
                    livroEncontrado.setAutor(resultado.getString("Autor"));
                    livroEncontrado.setCategoria(resultado.getString("Categoria"));
                    livroEncontrado.setStatus(resultado.getBoolean("status"));
                    livroEncontrado.setPreco(resultado.getDouble("Preco"));
                    livroEncontrado.setPaginas(resultado.getInt("Paginas"));
                    livroEncontrado.setISBN(resultado.getInt("ISBN"));
                   
                    livros.add(livroEncontrado);
                }
                
                conn.fechaConexao(conexao, transacaoSQL);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro na transação SQL", "Erro ao procurar livros no banco de" + "dados. \n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Problemas de conexão", "Não foi possível se conectar ao banco.", JOptionPane.ERROR_MESSAGE);
        }

        return livros;
    }
    

    public Livro buscarPorISBN(int isbn) {
        conexao = this.getConnect().connection;
        
        ResultSet resultado = null;
        Livro livroEncontrado = new Livro();

        if (conexao != null) {
            try {
                PreparedStatement transacaoSQL;
                transacaoSQL = conexao.prepareStatement(LISTBYISBN);
                transacaoSQL.setInt(1, isbn);

                resultado = transacaoSQL.executeQuery();

                while (resultado.next()) {

                    // INSERT INTO livro(TITULO, AUTOR,CATEGORIA, STATUS,PRECO, PAGINAS, ISBN)
                    
                    
                    livroEncontrado.setTitulo(resultado.getString("Titulo"));
                    livroEncontrado.setAutor(resultado.getString("Autor"));
                    livroEncontrado.setCategoria(resultado.getString("Categoria"));
                    livroEncontrado.setStatus(resultado.getBoolean("status"));
                    livroEncontrado.setPreco(resultado.getDouble("Preco"));
                    livroEncontrado.setPaginas(resultado.getInt("Paginas"));
                    livroEncontrado.setISBN(resultado.getInt("isbn"));
                   
                }
                
                conn.fechaConexao(conexao, transacaoSQL);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro na transação SQL", "Erro ao procurar livro no banco de" + "dados. \n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Problemas de conexão", "Não foi possível se conectar ao banco.", JOptionPane.ERROR_MESSAGE);
        }

        return livroEncontrado;
    }
    
    
    public Livro buscarPorPreco (double preco){
        conexao = this.getConnect().connection;
        
        ResultSet resultado = null;
        Livro livroEncontrado = new Livro();

        if (conexao != null) {
            try {
                PreparedStatement transacaoSQL;
                transacaoSQL = conexao.prepareStatement(LISTBYPRECO);
                transacaoSQL.setString(1, "%"+preco+"%");

                resultado = transacaoSQL.executeQuery();

                while (resultado.next()) {

                    // INSERT INTO livro(TITULO, AUTOR,CATEGORIA, STATUS,PRECO, PAGINAS, ISBN)
                    
                    
                    livroEncontrado.setTitulo(resultado.getString("Titulo"));
                    livroEncontrado.setAutor(resultado.getString("Autor"));
                    livroEncontrado.setCategoria(resultado.getString("Categoria"));
                    livroEncontrado.setStatus(resultado.getBoolean("status"));
                    livroEncontrado.setPreco(resultado.getDouble("Preco"));
                    livroEncontrado.setPaginas(resultado.getInt("paginas"));
                    livroEncontrado.setISBN(resultado.getInt("isbn"));
                   
                }
                
                conn.fechaConexao(conexao, transacaoSQL);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro na transação SQL", "Erro ao procurar livro no banco de" + "dados. \n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Problemas de conexão", "Não foi possível se conectar ao banco.", JOptionPane.ERROR_MESSAGE);
        }

        return livroEncontrado;
    }
    public Connect getConnect() {
        this.conn = new Connect("root","","NovaLivraria");
        return this.conn;
    }
    
}
