
package PacoteJavaGUI;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutosDAO {
    
    private Connection con;
    
    public ProdutosDAO(Connection con) {

       setCon(con);
    }
    
    public String inserir (ProdutosBean produtos){
        
        String sql = "insert into produtos (codigo, nome, qtde, valor) values(?,?,?,?)";
        
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, produtos.getCodigo());
            ps.setString(2, produtos.getNome());
            ps.setInt(3, produtos.getQtde());
            ps.setDouble(4, produtos.getValor());
            if (ps.executeUpdate() > 0) {
                return "inserido com sucesso";

            } else {

                return "Erro ao inserir";
            }
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }
    
    public String alterar(ProdutosBean produtos) {

        String sql = "update produtos nome = ?, qtde = ?, valor = ? where codigo = ?";

        try {

            PreparedStatement ps = getCon().prepareStatement(sql);

            ps.setInt(1, produtos.getCodigo());
            ps.setString(2, produtos.getNome());
            ps.setInt(3, produtos.getQtde());
            ps.setDouble(4, produtos.getValor());

            if (ps.executeUpdate() > 0) {
                return "alterado com sucesso";

            } else {

                return "Erro ao alterar";
            }

        } catch (SQLException ex) {
            return ex.getMessage();
        }

    }
    
    public String excluir(ProdutosBean produtos) {

        String sql = "delete from carro where placa = ?";

        try {

            PreparedStatement ps = getCon().prepareStatement(sql);

            ps.setInt(1, produtos.getCodigo());

            if (ps.executeUpdate() > 0) {
                return "excluido com sucesso";

            } else {

                return "Erro ao excluir";
            }

        } catch (SQLException ex) {
            return ex.getMessage();
        }

    }

    public List<ProdutosBean> listarTodos() {

        String sql = "select * from carro";
        List<ProdutosBean> listaProdutos = new ArrayList<ProdutosBean>();
        try {

            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    ProdutosBean cb = new ProdutosBean();
                    cb.setCodigo(rs.getInt(1));
                    cb.setNome(rs.getString(2));
                    cb.setQtde(rs.getInt(3));
                    cb.setValor(rs.getDouble(3));
                    listaProdutos.add(cb);

                }
                return listaProdutos;

            } else {

                return null;
            }

        } catch (SQLException e) {

            return null;
        }
    }
    
     public Connection getCon() {

        return con;
    }

    public void setCon(Connection con) {

        this.con = con;
    }
}
