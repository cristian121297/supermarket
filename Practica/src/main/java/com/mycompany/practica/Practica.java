
package com.mycompany.practica;

// importtamos los elementos apra poder usarlo en esta clase

import view.View;
import controller.Control;
import model.ModelUser;

public class Practica {

    public static void main(String[] args) {
        
        //Inicializar objetos 
        View objView = new View();
        ModelUser objUser = new ModelUser();
        Control objControl = new Control(objView,objUser);   
    }
}
