package com.ISysCream.Web2.repositories;

import com.ISysCream.Web2.model.entities.Sabor;
import com.ISysCream.Web2.model.entities.Sorvete;
import com.ISysCream.Web2.model.entities.TipoSorvete;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SorveteRepository implements Repository<Sorvete> {

    @Override
    public void create(Sorvete sorvete) throws SQLException {
        String sql = "INSERT INTO sorvete (codigo, dataCompra, codigo_tipoSorvete) VALUES (?, ?, ?)";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setInt(1, sorvete.getCodigo());
            pstm.setDate(2, sorvete.getDataCompra());
            pstm.setInt(3, sorvete.getTipoSorvete().getCodigo()); // Use o código do tipo de sorvete aqui
            pstm.execute();

            // Adiciona os sabores do sorvete
            for (Sabor sabor : sorvete.getSabores()) {
                adicionarSaborAoSorvete(sorvete.getCodigo(), sabor);
            }
        }
    }

    // Método auxiliar para adicionar sabores ao sorvete
    private void adicionarSaborAoSorvete(int codigoSorvete, Sabor sabor) throws SQLException {
        String sql = "INSERT INTO associacao_sabor_sorvete (codigo_sorvete, codigo_sabor, quantidade) VALUES (?, ?, ?)";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setInt(1, codigoSorvete);
            pstm.setInt(2, sabor.getCodigo());
            pstm.setInt(3, sabor.getQuantidade());
            pstm.execute();
        }
    }

//    @Override
//    public void update(Sorvete sorvete) throws SQLException {
//        String sql = "UPDATE sorvete SET dataCompra = ?, codigo_tipoSorvete = ? WHERE codigo = ?";
//
//        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
//            pstm.setDate(1, sorvete.getDataCompra());
//            pstm.setInt(2, sorvete.getTipoSorvete().getCodigo()); // Use getTipoSorvete().getCodigo()
//            pstm.setInt(3, sorvete.getCodigo());
//            pstm.execute();
//        }
//    }


    @Override
    public void update(Sorvete sorvete) throws SQLException {

        try {
            // Remove os sabores associados ao sorvete
            String deleteSaboresSql = "DELETE FROM associacao_sabor_sorvete WHERE codigo_sorvete = ?";
            try (PreparedStatement pstmDeleteSabores = ConnectionManager.getCurrentConnection().prepareStatement(deleteSaboresSql)) {
                pstmDeleteSabores.setInt(1, sorvete.getCodigo());
                pstmDeleteSabores.execute();
            }

            // Atualiza a data e o tipo de sorvete
            String updateSorveteSql = "UPDATE sorvete SET dataCompra = ?, codigo_tipoSorvete = ? WHERE codigo = ?";
            try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(updateSorveteSql)) {
                pstm.setDate(1, sorvete.getDataCompra());
                pstm.setInt(2, sorvete.getTipoSorvete().getCodigo());
                pstm.setInt(3, sorvete.getCodigo());
                pstm.execute();
            }

            // Adiciona novamente os sabores atualizados
            String addSaboresSql = "INSERT INTO associacao_sabor_sorvete (codigo_sorvete, codigo_sabor, quantidade) VALUES (?, ?, ?)";
            try (PreparedStatement pstmAddSabores = ConnectionManager.getCurrentConnection().prepareStatement(addSaboresSql)) {
                for (Sabor sabor : sorvete.getSabores()) {
                    pstmAddSabores.setInt(1, sorvete.getCodigo());
                    pstmAddSabores.setInt(2, sabor.getCodigo());
                    pstmAddSabores.setInt(3, sabor.getQuantidade()); // Adicione a quantidade aqui
                    pstmAddSabores.execute();
                }
            }
        } catch (SQLException e) {
            // Trate exceções, faça log ou propague a exceção conforme necessário
            throw e;
        }
    }

    @Override
    public Sorvete read(int codigo) throws SQLException {
        String sql = "SELECT * FROM sorvete JOIN tipoSorvete ON sorvete.codigo_tipoSorvete = tipoSorvete.codigo WHERE sorvete.codigo = ?";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setInt(1, codigo);

            try (ResultSet result = pstm.executeQuery()) {
                if (result.next()) {
                    return extractSorveteFromResultSet(result);
                }
            }
        }

        return null;
    }

    @Override
    public void delete(int codigo) throws SQLException {
        String sql = "DELETE FROM sorvete WHERE codigo = ?";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setInt(1, codigo);
            pstm.execute();
        }
    }

    @Override
    public List<Sorvete> readAll() throws SQLException {
        String sql = "SELECT * FROM sorvete JOIN tipoSorvete ON sorvete.codigo_tipoSorvete = tipoSorvete.codigo";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            try (ResultSet result = pstm.executeQuery()) {
                List<Sorvete> sorvetes = new ArrayList<>();

                while (result.next()) {
                    sorvetes.add(extractSorveteFromResultSet(result));
                }

                return sorvetes;
            }
        }
    }

    // Método auxiliar para extrair um sorvete do ResultSet
    private Sorvete extractSorveteFromResultSet(ResultSet result) throws SQLException {
        int codigo = result.getInt("codigo");
        Date dataCompra = result.getDate("dataCompra");
        TipoSorvete tipoSorvete = TipoSorvete.extractTipoSorveteFromResultSet(result);

        Sorvete sorvete = new Sorvete(codigo, dataCompra, tipoSorvete);

        // Adiciona os sabores do sorvete
        List<Sabor> sabores = getSaboresDoSorvete(codigo);
        for (Sabor sabor : sabores) {
            sorvete.adicionarSabor(sabor);
        }

        return sorvete;
    }

    // Método auxiliar para obter os sabores associados a um sorvete
    private List<Sabor> getSaboresDoSorvete(int codigoSorvete) throws SQLException {
        String sql = "SELECT sabor.* FROM sabor INNER JOIN associacao_sabor_sorvete ON sabor.codigo = associacao_sabor_sorvete.codigo_sabor WHERE associacao_sabor_sorvete.codigo_sorvete = ?";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setInt(1, codigoSorvete);

            try (ResultSet result = pstm.executeQuery()) {
                List<Sabor> sabores = new ArrayList<>();

                while (result.next()) {
                    sabores.add(new Sabor(result.getInt("codigo"), result.getString("nome"), result.getString("descricao")));
                }

                return sabores;
            }
        }
    }


}