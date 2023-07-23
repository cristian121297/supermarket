package model;

public class ModelUser {
    
    // crear los atributos necesarios para nuestro codigo
    
    private String name;
    private String birthday;
    private int id;
    private String cargo;

    public ModelUser(){
        
    }
    
    public ModelUser(String name, String birthday, int id, String cargo) {
        this.name = name;
        this.birthday = birthday;
        this.id = id;
        this.cargo = cargo;
    }
    /* insertar los metodos get (retornan el valor de la variable) 
    y set  (no retornan nada=void) de todos los atributos*/  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getCargo(Object selectedItem) {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}
