package com.ifpb.neo4j.view;

import com.ifpb.neo4j.dao.UsuarioDao;
import com.ifpb.neo4j.model.Usuario;

import java.time.LocalDate;

public class App {

    public static void main(String[] args) {

        UsuarioDao dao = new UsuarioDao();

        dao.salvar(new Usuario("maria@gmail.com", "Maria",
                LocalDate.now()));

        dao.salvar(new Usuario("pedro@gmail.com", "Pedro",
                LocalDate.now()));

        try{
            dao.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

}