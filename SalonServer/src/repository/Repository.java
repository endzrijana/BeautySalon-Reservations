/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to update this template
 */
package repository;

import java.util.List;

/**
 *
 * @author Ana
 */
public interface Repository<T> {
    List<T> getAll(T param, String uslov) throws Exception;
    void add(T param) throws Exception;
    void update(T param) throws Exception;
    void delete(T param) throws Exception;
    List<T> getAll();
    int addReturnKey(T param) throws Exception;
}
