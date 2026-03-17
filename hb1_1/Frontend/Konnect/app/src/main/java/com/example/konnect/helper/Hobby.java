package com.example.konnect.helper;

/**
 * This class represents a Hobby object with various properties and methods.
 *
 * @author Chanho Yang
 */
public class Hobby {
    /**
     * The name of the Hobby.
     */
    private String name;

    /**
     * The type of the Hobby.
     */
    private String type;

    /**
     * Sets the name and type of the Hobby.
     * @param name
     * @param type
     */
    public Hobby(String name, String type){
        this.name = name;
        this.type = type;

    }

    /**
     * Returns the name of the Hobby.
     * @return name
     */
    public String getName(){
        return name;

    }

    /**
     * Returns the type of the Hobby.
     * @return type
     */
    public String getType(){
        return type;
    }

}
