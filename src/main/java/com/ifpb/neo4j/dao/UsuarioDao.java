package com.ifpb.neo4j.dao;

import com.ifpb.neo4j.database.DriverFactory;
import com.ifpb.neo4j.model.Usuario;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.exceptions.ClientException;

import java.time.LocalDate;

import static org.neo4j.driver.v1.Values.parameters;

public class UsuarioDao implements AutoCloseable{

    private Driver driver;
    private Session session;

    public UsuarioDao(){
        driver = new DriverFactory().getDriver();
        session = driver.session();
    }

    public boolean salvar(Usuario usuario){
        int cont = 0;

        try(Transaction tx = session.beginTransaction()){
            StatementResult result = tx.run(
                    "CREATE (:Usuario{email:$email, nome:$nome," +
                            "nascimento:$nascimento})",
                    parameters("email", usuario.getEmail(),
                            "nome", usuario.getNome(),
                            "nascimento", usuario.getNascimento()));

            cont = result.summary().counters().nodesCreated();

            tx.success();
        }catch(ClientException ex){
            return false;
        }

        return cont>0;
    }

    public Usuario buscarPorEmail(String email){
        try(Transaction tx = session.beginTransaction()){
            StatementResult result = tx.run(
                    "MATCH (u:Usuario) WHERE u.email = $email " +
                            "return u.email, u.nome, u.nascimento",
                    parameters("email", email));

            if(result.hasNext()){
                Record record = result.next();

                String nome = record.get("u.nome").asString();
                LocalDate nascimento = record.get("u.nascimento").asLocalDate();

                return new Usuario(email, nome, nascimento);

            }else{
                return null;
            }

        }
    }

    @Override
    public void close() throws Exception {
        session.close();
        driver.close();
    }

}
