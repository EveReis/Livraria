package view;

import contoller.LivroController;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.bean.Livro;

public class TelaCadastroLivro extends javax.swing.JInternalFrame {

    private javax.swing.table.DefaultTableModel tabelaModelo;
    private LivroController lController;
    private Livro lSelecionado = new Livro();
    private boolean podeEditar = false;

    public TelaCadastroLivro() {
        lController = new LivroController();
        CriarTabelaModelo();
        initComponents();
        lController.listarTodos(tabelaModelo);
        limparCampos();
    }

    public void CriarTabelaModelo() {

        tabelaModelo = new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null, null}
                },
                // livro(TITULO, AUTOR,CATEGORIA, STATUS,PRECO, PAGINAS, ISBN)
                new String[]{
                    "Titulo", "Autor", "Categoria", "Status", "Preço", "Paginas", "ISBN"
                }
        ) {
            Class[] types = new Class[]{
                // java.lang.Integer.class, java.lang.String.class
                java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.Double.class,java.lang.Integer.class,java.lang.Integer.class
            };

            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

        };
    }

    public void preencherSelecionado(ListSelectionEvent e) {
        int linha = tabelaDeLivros.getSelectedRow();
        try {
            String Titulo = tabelaModelo.getValueAt(linha, 0).toString();
            String Autor = tabelaModelo.getValueAt(linha, 1).toString();
            String Categoria = tabelaModelo.getValueAt(linha, 2).toString();
             boolean status = tabelaModelo.getValueAt(linha, 3).toString().equals("1 - Ativo");
            double Preco = tabelaModelo.getValueAt(linha, 4).hashCode();
            int Paginas = tabelaModelo.getValueAt(linha, 5).hashCode(); 
            int isbn = Integer.parseInt(tabelaModelo.getValueAt(linha, 6).toString());
           

            this.preencherLivro(lSelecionado, Titulo, Autor, Categoria, status,  Preco,Paginas,isbn);

            this.preencherCampos();
            this.habilitarCamposEdicao();
        } catch (Exception erro) {
            this.limparCampos();
        }
    }

    public void preencherLivro(
            Livro l, String Titulo,String Autor,String Categoria, boolean status, double Preco,int Paginas,int isbn ) {
        //if (Titulo != null && Autor != null && Categoria != null && Paginas != 0 && Preco != 0 && isbn !=0) {
        if (true) {
            System.out.println("Aqui");
            
            l.setTitulo(Titulo);
            l.setAutor(Autor);
            l.setCategoria(Categoria);
            l.setPaginas(Paginas);
            l.setPreco(Preco);
            l.setStatus(status);
            l.setISBN(isbn);
        } else {
            this.limparCampos();
        }
    }

    public void preencherCampos() {
         // livro(TITULO, AUTOR,CATEGORIA, STATUS,PRECO, PAGINAS, ISBN)
        tituloText.setText(lSelecionado.getTitulo());
        autorText.setText(lSelecionado.getAutor());
        categoriaText.setText(lSelecionado.getCategoria());
        String status_index = lSelecionado.isStatus() ? "1 - Ativo" : "2 - Inativo";
        precoText.setText(String.valueOf(lSelecionado.getPreco()));
        paginasText.setText(String.valueOf(lSelecionado.getPaginas()));
        isbnText.setText(String.valueOf(lSelecionado.getISBN()));
    
        statusLivro.getModel().setSelectedItem(status_index);
    }

    public void limparCampos() {
        lSelecionado = new Livro();
         // livro(TITULO, AUTOR,CATEGORIA, STATUS,PRECO, PAGINAS, ISBN)
        tituloText.setText("");
        autorText.setText("");
        categoriaText.setText("");
        String status_index = "2 - Inativo";
        precoText.setText("");
        paginasText.setText("");
        isbnText.setText("");
        
        statusLivro.getModel().setSelectedItem(status_index);

        tabelaDeLivros.getSelectionModel().clearSelection();
        this.desabilitarCamposEdicao();
    }

    public void desabilitarCamposEdicao() {
        // livro(TITULO, AUTOR,CATEGORIA, STATUS,PRECO, PAGINAS, ISBN)
        this.tituloText.setEnabled(false);
        this.autorText.setEnabled(false);
        this.categoriaText.setEnabled(false);
        this.statusLivro.setEnabled(false);
        this.precoText.setEnabled(false);
        this.paginasText.setEnabled(false);
        this.isbnText.setEnabled(false);

        this.podeEditar = false;
    }

    public void habilitarCamposEdicao() {
        // livro(TITULO, AUTOR,CATEGORIA, STATUS,PRECO, PAGINAS, ISBN)
        this.tituloText.setEnabled(true);
        this.autorText.setEnabled(true);
        this.categoriaText.setEnabled(true);
        this.statusLivro.setEnabled(true);
        this.precoText.setEnabled(true);
        this.paginasText.setEnabled(true);
        this.isbnText.setEnabled(true);

        this.podeEditar = true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tituloText = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnProcurar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        autorText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        categoriaText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        paginasText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        isbnText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        precoText = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        statusLivro = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaDeLivros = new javax.swing.JTable();

        setClosable(true);
        setTitle("Cadastro de Livros");
        setName(""); // NOI18N

        jLabel1.setText("TÍTULO");

        tituloText.setText("Título do Livro");
        tituloText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tituloTextActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnProcurar.setText("Procurar");
        btnProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcurarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        jLabel2.setText("AUTOR");

        autorText.setText("Autor do Livro");

        jLabel3.setText("CATEGORIA");

        categoriaText.setText("Categoria do Livro");

        jLabel4.setText("PÁGINAS");

        paginasText.setText("0");

        jLabel5.setText("ISBN");

        isbnText.setText("0");
        isbnText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isbnTextActionPerformed(evt);
            }
        });

        jLabel6.setText("PREÇO");

        precoText.setText("0");

        jLabel7.setText("STATUS");

        statusLivro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 - Ativo", "2 - Inativo" }));
        statusLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusLivroActionPerformed(evt);
            }
        });

        tabelaDeLivros.setModel(tabelaModelo);
        tabelaDeLivros.getTableHeader().setReorderingAllowed(false);
        tabelaDeLivros.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                preencherSelecionado(e);
            }
        });
        jScrollPane1.setViewportView(tabelaDeLivros);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tituloText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(autorText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoriaText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(paginasText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(isbnText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precoText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusLivro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tituloText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(autorText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(categoriaText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(paginasText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(isbnText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(precoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(statusLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed

        if (!this.podeEditar) {
            JOptionPane.showMessageDialog(this, "Selecione um Livro na tabela ou clique em NOVO.\nÉ preciso preencher todos os campos.");
            return;
        }
        String Titulo = tituloText.getText();
        System.out.println("Meu titulo é: " + Titulo);
        String Autor = autorText.getText();
        String Categoria = categoriaText.getText();
        boolean status = statusLivro.getSelectedItem().toString().equals("1 - Ativo");
        double preco = lSelecionado.getPreco();
        int Paginas = lSelecionado.getPaginas();
        int isbn = lSelecionado.getISBN();
 

        this.preencherLivro(lSelecionado,Titulo , Autor, Categoria, status, preco,Paginas, isbn);
        
        System.out.println("VS: " + lSelecionado);

        //if (lSelecionado != null && !(id == 0 || Titulo.equals("") || Autor.equals("") || Categoria.equals("") || Paginas == 0 || preco == 0)) {
        if (lSelecionado != null  ) {
            if (lSelecionado.getISBN() != 0) {
                // atualizar
                lController.salvar(tabelaModelo, lSelecionado, false);
            } else {
                // criar novo
                lController.salvar(tabelaModelo, lSelecionado, true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "É preciso preencher todos os campos.");
        }

        limparCampos();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void tituloTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tituloTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tituloTextActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        this.habilitarCamposEdicao();
        this.isbnText.requestFocus();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcurarActionPerformed
        // TODO add your handling code here:
        Object[] possibilities = {"Todos", "Pelo ISBN ", "Pelo Preço "};
        String escolha = (String) JOptionPane.showInputDialog(
                this,
                "Escolha o tipo de busca\n"
                + "que deseja efetuar",
                "Buscar Livro",
                JOptionPane.QUESTION_MESSAGE,
                null,
                possibilities,
                possibilities[0]);

        escolha = escolha == null ? "" : escolha;

        switch (escolha) {
            case "Pelo ISBN":
                int isbn = Integer.parseInt(JOptionPane.showInputDialog(this, "Id do Livro: "));
                lController.listarPorISBN(tabelaModelo, isbn);
                break;
            case "Pelo Preco":
                double preco = Double.parseDouble(JOptionPane.showInputDialog(this, "Digite o Preço:"));
                lController.listarPorPreco(tabelaModelo, preco);
                break;
            default:
                lController.listarTodos(tabelaModelo);
        }
    }//GEN-LAST:event_btnProcurarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        if (lSelecionado == null) {
            JOptionPane.showMessageDialog(this, "O livro selecionado não existe no banco de dados.\nTente selecionar um vendedor da tabela abaixo.");
        } else {
            lController.excluir(tabelaModelo, lSelecionado);
        }

        limparCampos();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void isbnTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isbnTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isbnTextActionPerformed

    private void statusLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusLivroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusLivroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField autorText;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnProcurar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField categoriaText;
    private javax.swing.JTextField isbnText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField paginasText;
    private javax.swing.JTextField precoText;
    private javax.swing.JComboBox<String> statusLivro;
    private javax.swing.JTable tabelaDeLivros;
    private javax.swing.JTextField tituloText;
    // End of variables declaration//GEN-END:variables
}
