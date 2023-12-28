package com.ISysCream.Web2.repositories;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ISysCream.Web2.model.entities.Sabor;

public class SaborRepository implements Repository<Sabor> {

        @Override
        public void create(Sabor sabor) throws SQLException {
            String sql = "INSERT INTO sabor (nome, descricao) VALUES (?, ?)";

            try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
                pstm.setString(1, sabor.getNome());
                pstm.setString(2, sabor.getDescricao());
                pstm.execute();
            }
        }

        @Override
        public void update(Sabor sabor) throws SQLException {
            String sql = "UPDATE sabor SET nome = ?, descricao = ? WHERE codigo = ?";

            try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
                pstm.setString(1, sabor.getNome());
                pstm.setString(2, sabor.getDescricao());
                pstm.setInt(3, sabor.getCodigo());
                pstm.execute();
            }
        }

        @Override
        public Sabor read(int codigo) throws SQLException {
            String sql = "SELECT * FROM sabor WHERE codigo = ?";

            try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
                pstm.setInt(1, codigo);

                try (ResultSet result = pstm.executeQuery()) {
                    if (result.next()) {
                        return extractSaborFromResultSet(result);
                    }
                }
            }

            return null;
        }

        @Override
        public void delete(int codigo) throws SQLException {
            String sql = "DELETE FROM sabor WHERE codigo = ?";

            try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
                pstm.setInt(1, codigo);
                pstm.execute();
            }
        }

        @Override
        public List<Sabor> readAll() throws SQLException {
            String sql = "SELECT * FROM sabor";

            try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
                try (ResultSet result = pstm.executeQuery()) {
                    List<Sabor> sabores = new ArrayList<>();

                    while (result.next()) {
                        sabores.add(extractSaborFromResultSet(result));
                    }

                    return sabores;
                }
            }
        }

    private Sabor extractSaborFromResultSet(ResultSet result) throws SQLException {
        // Extrai os valores das colunas do ResultSet
        int codigo = result.getInt("codigo");
        String nome = result.getString("nome");
        String descricao = result.getString("descricao");

        // Cria e retorna uma nova instância de Sabor com os valores extraídos
        return new Sabor(codigo, nome, descricao);
    }

    }
