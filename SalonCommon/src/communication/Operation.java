/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package communication;

import java.io.Serializable;

/**
 *
 * @author Ana
 */
public enum Operation implements Serializable{
    LOGIN, LOAD_CLIENTS, DELETE_CLIENT, LOAD_TYPE, ADD_CLIENT, CREATE_CLIENT,
    LOAD_RESERVATIONS, LOAD_ITEMS, DELETE_ITEM, UPDATE_CONFIRMATION, DELETE_CONFIRMATION, LOAD_SERVICES,
    LOAD_COSMETICIAN, CREATE_CONFIRMATION, ADD_SERVICE, UPDATE_SERVICE, DELETE_SERVICE, LOAD_CERTIFICATE, DELETE_COSMETICIAN,
    LOAD_RESERVATIONS_PARAM, LOAD_CLIENTS_PARAM, ADD_CERTIFICATE, LOAD_CLIENT, LOAD_CONFIRMATION,
}
