package br.com.fernanda.usermanagement.constant;

public enum TelephoneType {


    CELL_PHONE("CELL_PHONE"),
    COMMERCIAL("COMMERCIAL"),
    RESIDENTIAL("RESIDENTIAL");


    private String name;

    TelephoneType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }




}
