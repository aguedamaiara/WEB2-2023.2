package com.ISysCream.Web2.controllers;

import com.ISysCream.Web2.model.entities.RelatorioVendas;
import com.ISysCream.Web2.model.entities.RelatorioVendasPorDia;
import com.ISysCream.Web2.model.entities.Sorvete;
import com.ISysCream.Web2.repositories.RepositoryService;
import com.ISysCream.Web2.repositories.SorveteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/sorvete")
public class SorveteController {

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Sorvete sorvete) {
        try {
            RepositoryService.getInstance().createSorvete(sorvete);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating sorvete", e);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Sorvete sorvete) {
        try {
            RepositoryService.getInstance().updateSorvete(sorvete);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Sorvete> read(@PathVariable("codigo") int codigo) {
        try {
            Sorvete sorvete = RepositoryService.getInstance().readSorvete(codigo);
            return new ResponseEntity<>(sorvete, HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> delete(@PathVariable int codigo) {
        try {
            RepositoryService.getInstance().deleteSorvete(codigo);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Sorvete>> readAll() {
        try {
            List<Sorvete> sorvetes = RepositoryService.getInstance().readAllSorvetes();
            return new ResponseEntity<>(sorvetes, HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vendas-sabor")
    public List<RelatorioVendas> getRelatorioVendas() {
        List<RelatorioVendas> relatorio = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/isyscream", "root", "root")) {
            String query = "SELECT sabor.nome AS sabor, COUNT(associacao_sabor_sorvete.codigo_sorvete) AS quantidade_vendida " +
                    "FROM associacao_sabor_sorvete " +
                    "JOIN sabor ON associacao_sabor_sorvete.codigo_sabor = sabor.codigo " +
                    "GROUP BY sabor.nome";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    String sabor = resultSet.getString("sabor");
                    int quantidadeVendida = resultSet.getInt("quantidade_vendida");
                    relatorio.add(new RelatorioVendas(sabor, quantidadeVendida));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Tratar adequadamente a exceção em um ambiente de produção
        }

        return relatorio;
    }
    @GetMapping("/vendas-por-dia")
    public List<RelatorioVendasPorDia> getRelatorioVendasPorDia() {
        List<RelatorioVendasPorDia> relatorio = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/isyscream", "root", "root")) {
            String query = "SELECT sorvete.dataCompra AS dia, COUNT(sorvete.codigo) AS quantidade_vendida " +
                    "FROM sorvete " +
                    "GROUP BY sorvete.dataCompra";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    String dia = resultSet.getString("dia");
                    int quantidadeVendida = resultSet.getInt("quantidade_vendida");
                    relatorio.add(new RelatorioVendasPorDia(dia, quantidadeVendida));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Tratar adequadamente a exceção em um ambiente de produção
        }

        return relatorio;
    }
}
