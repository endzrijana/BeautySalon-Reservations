/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to update this template
 */
package operation;

import repository.Repository;
import repository.db.DbRepository;
import repository.db.impl.DbRepositoryGeneric;

/**
 *
 * @author Ana
 */
public abstract class AbstractGenericOperation {
    protected final Repository broker;

    public AbstractGenericOperation() {
        this.broker = new DbRepositoryGeneric();
    }
    
    public final void execute(Object objekat, String key) throws Exception{
        try{
            validate(objekat);
            connect();
            executeOperation(objekat, key);
            commit();
        }catch(Exception e){
            rollback();
            throw e;
        }finally{
            //disconnect();
        }
    }

    protected abstract void validate(Object param) throws Exception;

    protected abstract void executeOperation(Object param, String key) throws Exception;
    
    private void connect() throws Exception{
        ((DbRepository) broker).connect();
    }

    private void commit() throws Exception {
        ((DbRepository) broker).commit();
    }

    private void rollback() throws Exception {
        ((DbRepository) broker).rollback();
    }

    private void disconnect() throws Exception {
        ((DbRepository) broker).disconnect();
    }
    
}
