package com.ISysCream.Web2.repositories;

import com.ISysCream.Web2.model.entities.TipoSorvete;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoSorveteRepository implements Repository<TipoSorvete> {

    @Override
    public void create(TipoSorvete tipoSorvete) throws SQLException {
        String sql = "INSERT INTO tiposorvete (tipo, quantBolas, peso, descricao, valor) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setString(1, tipoSorvete.getTipo());
            pstm.setInt(2, tipoSorvete.getQuantBolas());
            pstm.setDouble(3, tipoSorvete.getPeso());
            pstm.setString(4, tipoSorvete.getDescricao());
            pstm.setDouble(5, tipoSorvete.getValor());
            pstm.execute();
        }
    }

    @Override
    public void update(TipoSorvete tipoSorvete) throws SQLException {
        String sql = "UPDATE tiposorvete SET tipo = ?, quantBolas = ?, peso = ?, descricao = ?, valor = ? WHERE codigo = ?";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setString(1, tipoSorvete.getTipo());
            pstm.setInt(2, tipoSorvete.getQuantBolas());
            pstm.setDouble(3, tipoSorvete.getPeso());
            pstm.setString(4, tipoSorvete.getDescricao());
            pstm.setDouble(5, tipoSorvete.getValor());
            pstm.setInt(6, tipoSorvete.getCodigo());
            pstm.execute();
        }
    }

    @Override
    public TipoSorvete read(int codigo) throws SQLException {
        String sql = "SELECT * FROM tiposorvete WHERE codigo = ?";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setInt(1, codigo);

            try (ResultSet result = pstm.executeQuery()) {
                if (result.next()) {
                    return extractTipoSorveteFromResultSet(result);
                }
            }
        }

        return null;
    }

    @Override
    public void delete(int codigo) throws SQLException {
        String sql = "DELETE FROM tiposorvete WHERE codigo = ?";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setInt(1, codigo);
            pstm.execute();
        }
    }

    @Override
    public List<TipoSorvete> readAll() throws SQLException {
        String sql = "SELECT * FROM tiposorvete";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            try (ResultSet result = pstm.executeQuery()) {
                List<TipoSorvete> tiposSorvete = new ArrayList<>();

                while (result.next()) {
                    tiposSorvete.add(extractTipoSorveteFromResultSet(result));
                }

                return tiposSorvete;
            }
        }
    }

    private TipoSorvete extractTipoSorveteFromResultSet(ResultSet result) throws SQLException {
        int codigo = result.getInt("codigo");
        String tipo = result.getString("tipo");
        int quantBolas = result.getInt("quantBolas");
        double peso = result.getDouble("peso");
        String descricao = result.getString("descricao");
        double valor = result.getDouble("valor");

        return new TipoSorvete(codigo, tipo, quantBolas, peso, descricao, valor);
    }
}