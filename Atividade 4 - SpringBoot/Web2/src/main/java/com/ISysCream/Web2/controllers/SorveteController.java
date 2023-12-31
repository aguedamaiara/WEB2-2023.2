package com.ISysCream.Web2.controllers;

import com.ISysCream.Web2.model.entities.Sorvete;
import com.ISysCream.Web2.repositories.RepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
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


}
