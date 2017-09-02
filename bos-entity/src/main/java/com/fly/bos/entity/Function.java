package com.fly.bos.entity;

import java.util.HashSet;
import java.util.Set;


/**
 * Function entity. @author MyEclipse Persistence Tools
 */

public class Function  implements java.io.Serializable {


    // Fields    

     private String id;
     private Function parentFunction;
     private String name;
     private String code;
     private String description;
     private String page;
     private String generatemenu;
     private Integer zindex;
     private Set functions = new HashSet(0);
     private Set roles = new HashSet(0);


     public String getpId(){
    	 if(parentFunction == null){
    		 return "0";
    	 }
    	 return parentFunction.getId();
     }
     
    // Constructors

    /** default constructor */
    public Function() {
    }

	/** minimal constructor */
    public Function(String id) {
        this.id = id;
    }
    
    public Function(String id, Function parentFunction, String name, String code, String description, String page,
			String generatemenu, Integer zindex, Set functions, Set roles) {
		super();
		this.id = id;
		this.parentFunction = parentFunction;
		this.name = name;
		this.code = code;
		this.description = description;
		this.page = page;
		this.generatemenu = generatemenu;
		this.zindex = zindex;
		this.functions = functions;
		this.roles = roles;
	}

	public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public Function getParentFunction() {
		return parentFunction;
	}

	public void setParentFunction(Function parentFunction) {
		this.parentFunction = parentFunction;
	}

	public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPage() {
        return this.page;
    }
    
    public void setPage(String page) {
        this.page = page;
    }

    public String getGeneratemenu() {
        return this.generatemenu;
    }
    
    public void setGeneratemenu(String generatemenu) {
        this.generatemenu = generatemenu;
    }

    public Integer getZindex() {
        return this.zindex;
    }
    
    public void setZindex(Integer zindex) {
        this.zindex = zindex;
    }

    public Set getFunctions() {
        return this.functions;
    }
    
    public void setFunctions(Set functions) {
        this.functions = functions;
    }

    public Set getRoles() {
        return this.roles;
    }
    
    public void setRoles(Set roles) {
        this.roles = roles;
    }
   








}