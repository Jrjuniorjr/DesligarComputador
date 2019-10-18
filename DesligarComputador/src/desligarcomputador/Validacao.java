/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desligarcomputador;

/**
 *
 * @author Junior
 */
public class Validacao {
    
    public static boolean validar(String string){
        if(string.isEmpty()){
           return false; 
        }
        else if(string.length() != 5){
            return false;
        }
        else{
            for(int i = 0; i < 5; i++){
                if(i == 2){
                    if(!(string.charAt(i) == ':')){
                        return false;
                    }
                }
                else{
                    if(!Character.isDigit(string.charAt(i))){
                        return false;
                    }
                }
            }
        }
      return true;
    }
   
}
