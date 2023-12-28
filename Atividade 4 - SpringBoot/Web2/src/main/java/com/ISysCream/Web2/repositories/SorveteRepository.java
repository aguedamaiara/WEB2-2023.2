package com.ISysCream.Web2.repositories;

import com.ISysCream.Web2.model.entities.Sabor;
import com.ISysCream.Web2.model.entities.Sorvete;
import com.ISysCream.Web2.model.entities.TipoSorvete;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SorveteRepository implements Repository<Sorvete> {

    @Override
    public void create(Sorvete sorvete) throws SQLException {
        String sql = "INSERT INTO sorvete (dataCompra, tipoSorvete_codigo) VALUES (?, ?)";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setDate(1, sorvete.getDataCompra());
            pstm.setInt(2, sorvete.getTipoSorvete().getCodigo());
            pstm.execute();
        }
    }

    @Override
    public void update(Sorvete sorvete) throws SQLException {
        // Supondo que você deseja atualizar apenas a data de compra do sorvete
        String sql = "UPDATE sorvete SET dataCompra = ? WHERE codigo = ?";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setDate(1, sorvete.getDataCompra());
            pstm.setInt(2, sorvete.getCodigo());
            pstm.execute();
        }
    }

    @Override
    public Sorvete read(int codigo) throws SQLException {
        String sql = "SELECT * FROM sorvete WHERE codigo = ?";

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
        String sql = "SELECT * FROM sorvete";

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

    public List<Sabor> readAllSaboresPorSorvete(int codigoSorvete) throws SQLException {
        List<Sabor> sabores = new ArrayList<>();
        String sql = "SELECT sabor.* FROM sorvete_sabor " +
                "JOIN sabor ON sorvete_sabor.sabor_codigo = sabor.codigo " +
                "WHERE sorvete_sabor.sorvete_codigo = ?";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setInt(1, codigoSorvete);

            try (ResultSet result = pstm.executeQuery()) {
                while (result.next()) {
                    Sabor sabor = extractSaborFromResultSet(result);
                    sabores.add(sabor);
                }
            }
        }

        return sabores;
    }

    private Sabor extractSaborFromResultSet(ResultSet result) throws SQLException {
        int codigo = result.getInt("codigo");
        String nome = result.getString("nome");
        String descricao = result.getString("descricao");

        return new Sabor(codigo, nome, descricao);
    }

    private Sorvete extractSorveteFromResultSet(ResultSet result) throws SQLException {
        int codigo = result.getInt("codigo");
        java.sql.Date dataCompra = result.getDate("dataCompra");
        int tipoSorveteCodigo = result.getInt("tipoSorvete_codigo");

        Sorvete sorvete = new Sorvete(codigo, dataCompra);

        TipoSorvete tipoSorvete = RepositoryService.getInstance().readTipoSorvete(tipoSorveteCodigo);
        sorvete.setTipoSorvete(tipoSorvete);

        List<Sabor> sabores = readAllSaboresPorSorvete(codigo);
        sorvete.getSabores().addAll(sabores);

        return sorvete;
    }
}

