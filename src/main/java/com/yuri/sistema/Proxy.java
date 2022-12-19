/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yuri.sistema;

/**
 *
 * @author JUAN-PC
 */
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JUAN-PC
 */
public class Proxy {
    
    /**
     *
     * @param senha
     * @return
     * @throws UnsupportedEncodingException
     */
    public String codificaSenha(String senha) throws UnsupportedEncodingException{
        
        try {
            MessageDigest mD1 = MessageDigest.getInstance("SHA-256");
            byte messDigest [] = mD1.digest(senha.getBytes("UTF-8"));
            
            StringBuilder sB1= new StringBuilder();
            for(byte b : messDigest){
                sB1.append(String.format("%02X", 0xFF & b));
            }
            String senhaTratada = sB1.toString();
            return senhaTratada;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Proxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
