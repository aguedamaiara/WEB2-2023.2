package com.ISysCream.Web2.controllers;

import com.ISysCream.Web2.model.entities.Sabor;
import com.ISysCream.Web2.repositories.RepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.sql.SQLException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/sabor")
public class SaborController {
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Sabor sabor) {
        try {
            RepositoryService.getInstance().createSabor(sabor);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating sabor", e);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Sabor sabor) {
        try {
            RepositoryService.getInstance().updateSabor(sabor);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Sabor> read(@PathVariable("codigo") int codigo) {
        try {
            Sabor sabor = RepositoryService.getInstance().readSabor(codigo);
            return new ResponseEntity<>(sabor, HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> delete(@PathVariable int codigo) {
        try {
            RepositoryService.getInstance().deleteSabor(codigo);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Sabor>> readAll() {
        try {
            List<Sabor> sabores = RepositoryService.getInstance().readAllSabores();
            return new ResponseEntity<>(sabores, HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
