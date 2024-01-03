package com.ISysCream.Web2.repositories;


import com.ISysCream.Web2.model.entities.Sabor;
import com.ISysCream.Web2.model.entities.Sorvete;
import com.ISysCream.Web2.model.entities.TipoSorvete;

import java.sql.SQLException;
import java.util.List;

public class RepositoryService {

    // Implementação do singleton
    private static RepositoryService instance = null;

    private Repository<Sabor> saborRepository = null;
    private Repository<TipoSorvete> tipoSorveteRepository = null;
    private Repository<Sorvete> sorveteRepository = null;


    private RepositoryService() {
        saborRepository = new SaborRepository();
        tipoSorveteRepository = new TipoSorveteRepository();
        sorveteRepository = new SorveteRepository();
    }

    public static RepositoryService getInstance() {
        if (instance == null) {
            instance = new RepositoryService();
        }
        return instance;
    }
    //fim da implementação do singleton

    // Métodos específicos para manipular sabores
    public void createSabor(Sabor sabor) throws SQLException {
        saborRepository.create(sabor);
    }

    public void updateSabor(Sabor sabor) throws SQLException {
        saborRepository.update(sabor);
    }

    public Sabor readSabor(int codigo) throws SQLException {
        return saborRepository.read(codigo);
    }

    public void deleteSabor(int codigo) throws SQLException {
        saborRepository.delete(codigo);
    }

    public List<Sabor> readAllSabores() throws SQLException {
        return saborRepository.readAll();
    }

    // Métodos específicos para manipular tipos de sorvete
    public void createTipoSorvete(TipoSorvete tipoSorvete) throws SQLException {
        tipoSorveteRepository.create(tipoSorvete);
    }

    public void updateTipoSorvete(TipoSorvete tipoSorvete) throws SQLException {
        tipoSorveteRepository.update(tipoSorvete);
    }

    public TipoSorvete readTipoSorvete(int codigo) throws SQLException {
        return tipoSorveteRepository.read(codigo);
    }

    public void deleteTipoSorvete(int codigo) throws SQLException {
        tipoSorveteRepository.delete(codigo);
    }

    public List<TipoSorvete> readAllTiposSorvete() throws SQLException {
        return tipoSorveteRepository.readAll();
    }

    // Métodos específicos para manipular sorvetes
    public void createSorvete(Sorvete sorvete) throws SQLException {
        sorveteRepository.create(sorvete);
    }

    public void updateSorvete(Sorvete sorvete) throws SQLException {
        sorveteRepository.update(sorvete);
    }

    public Sorvete readSorvete(int codigo) throws SQLException {
        return sorveteRepository.read(codigo);
    }

    public void deleteSorvete(int codigo) throws SQLException {
        sorveteRepository.delete(codigo);
    }

    public List<Sorvete> readAllSorvetes() throws SQLException {
        return sorveteRepository.readAll();
    }


}
