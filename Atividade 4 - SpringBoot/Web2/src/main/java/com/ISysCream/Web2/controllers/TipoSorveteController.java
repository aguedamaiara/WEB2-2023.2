package com.ISysCream.Web2.controllers;

import com.ISysCream.Web2.model.entities.TipoSorvete;
import com.ISysCream.Web2.repositories.RepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("*")
@RestController
@RequestMapping("/tipoSorvete")

public class TipoSorveteController {
    @PostMapping
    public ResponseEntity<?> create(@RequestBody TipoSorvete tipoSorvete) {
        try {
            RepositoryService.getInstance().createTipoSorvete(tipoSorvete);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TipoSorvete tipoSorvete) {
        try {
            RepositoryService.getInstance().updateTipoSorvete(tipoSorvete);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<TipoSorvete> read(@PathVariable("codigo") int codigo) {
        try {
            TipoSorvete tipoSorvete = RepositoryService.getInstance().readTipoSorvete(codigo);
            return new ResponseEntity<>(tipoSorvete, HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> delete(@PathVariable int codigo) {
        try {
            RepositoryService.getInstance().deleteTipoSorvete(codigo);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<TipoSorvete>> readAll() {
        try {
            List<TipoSorvete> tiposSorvete = RepositoryService.getInstance().readAllTiposSorvete();
            return new ResponseEntity<>(tiposSorvete, HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{codigo}/quantBolas")
    public ResponseEntity<Integer> getQuantBolas(@PathVariable("codigo") int codigo) {
        try {
            TipoSorvete tipoSorvete = RepositoryService.getInstance().readTipoSorvete(codigo);
            if (tipoSorvete != null) {
                return new ResponseEntity<>(tipoSorvete.getQuantBolas(), HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
