package com.ISysCream.Web2.repositories;
//interface genérica Repository, definindo métodos básicos de CRUD
//(Create, Read, Update, Delete) que serão implementados por classes específicas.

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {

    public void create(T t) throws SQLException;
    public void update(T t) throws SQLException;
    public T read(int codigo) throws SQLException;
    public void delete(int codigo) throws SQLException;
    public List<T> readAll() throws SQLException;


}
