package com.ISysCream.Web2.repositories;

import com.ISysCream.Web2.model.entities.RelatorioVendas;
import com.ISysCream.Web2.model.entities.Sabor;
import com.ISysCream.Web2.model.entities.Sorvete;
import com.ISysCream.Web2.model.entities.TipoSorvete;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SorveteRepository implements Repository<Sorvete> {

    @Override
    public void create(Sorvete sorvete) throws SQLException {
//        String sql = "INSERT INTO sorvete (codigo, dataCompra, codigo_tipoSorvete) VALUES (?, ?, ?)";
//
//        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
//            pstm.setInt(1, sorvete.getCodigo());
//            pstm.setDate(2, sorvete.getDataCompra());
//            pstm.setInt(3, sorvete.getTipoSorvete().getCodigo()); // Use o código do tipo de sorvete aqui
//            pstm.execute();
//
//            // Adiciona os sabores do sorvete
//            for (Sabor sabor : sorvete.getSabores()) {
//                adicionarSaborAoSorvete(sorvete.getCodigo(), sabor);
//            }
//        }
//    }
        String sql = "INSERT INTO sorvete (dataCompra, codigo_tipoSorvete) VALUES (?, ?)";
        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setDate(1, sorvete.getDataCompra());
            pstm.setInt(2, sorvete.getTipoSorvete().getCodigo());
            pstm.execute();

            // Obtenha o código do sorvete gerado
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int codigoSorvete = generatedKeys.getInt(1);

                    // Adicione os sabores do sorvete
                    for (Sabor sabor : sorvete.getSabores()) {
                        adicionarSaborAoSorvete(codigoSorvete, sabor);
                    }
                } else {
                    throw new SQLException("Falha ao obter o código gerado para o sorvete.");
                }
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
        // Atualiza a data e o tipo de sorvete
        String updateSorveteSql = "UPDATE sorvete SET dataCompra = ?, codigo_tipoSorvete = ? WHERE codigo = ?";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(updateSorveteSql)) {
            pstm.setDate(1, sorvete.getDataCompra());
            pstm.setInt(2, sorvete.getTipoSorvete().getCodigo());
            pstm.setInt(3, sorvete.getCodigo());
            pstm.execute();
        }

        // Remove os sabores associados ao sorvete
        String deleteSaboresSql = "DELETE FROM associacao_sabor_sorvete WHERE codigo_sorvete = ?";

        try (PreparedStatement pstmDeleteSabores = ConnectionManager.getCurrentConnection().prepareStatement(deleteSaboresSql)) {
            pstmDeleteSabores.setInt(1, sorvete.getCodigo());
            pstmDeleteSabores.execute();
        }

        // Adiciona novamente os sabores atualizados
        String addSaboresSql = "INSERT INTO associacao_sabor_sorvete (codigo_sorvete, codigo_sabor, quantidade) VALUES (?, ?, ?)";

        try (PreparedStatement pstmAddSabores = ConnectionManager.getCurrentConnection().prepareStatement(addSaboresSql)) {
            for (Sabor sabor : sorvete.getSabores()) {
                pstmAddSabores.setInt(1, sorvete.getCodigo());
                pstmAddSabores.setInt(2, sabor.getCodigo());
                pstmAddSabores.setInt(3, sabor.getQuantidade());
                pstmAddSabores.execute();
            }
        }

        // Após a atualização, faça uma nova consulta para obter o estado atualizado
        Sorvete sorveteAtualizado = read(sorvete.getCodigo());
        if (sorveteAtualizado != null) {
            // Converta o objeto Sorvete para JSON e retorne na resposta
            String jsonAtualizado = convertSorveteToJson(sorveteAtualizado);
            // Retorne a resposta com o JSON atualizado
        } else {
            // Trate o caso em que o sorvete não pôde ser recuperado após a atualização
            throw new SQLException("Falha ao obter o sorvete atualizado após a atualização.");
        }
    }

    // Método auxiliar para converter o objeto Sorvete para JSON
    private String convertSorveteToJson(Sorvete sorvete) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(sorvete);
        } catch (Exception e) {
            // Trate a exceção conforme necessário
            e.printStackTrace();
            return null;
        }    }


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
        // Antes de excluir o sorvete, exclua as associações correspondentes
        deleteAssociacoesSaborSorvete(codigo);
        String sql = "DELETE FROM sorvete WHERE codigo = ?";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setInt(1, codigo);
            pstm.execute();
        }
    }

    // Método auxiliar para excluir as associações de sabor do sorvete
    private void deleteAssociacoesSaborSorvete(int codigoSorvete) throws SQLException {
        String deleteAssociacoesSql = "DELETE FROM associacao_sabor_sorvete WHERE codigo_sorvete = ?";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(deleteAssociacoesSql)) {
            pstm.setInt(1, codigoSorvete);
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