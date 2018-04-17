package edu.ou.cs.hci.stages;

import java.util.ArrayList;


public class Recipe
{
  //**********************************************************************
  // Public Class Members
  //**********************************************************************

  //**********************************************************************
  // Private Class Members
  //**********************************************************************

  private static String name;
  private static String descriptionPath;
  private static String[] ingredients;

  //**********************************************************************
  // Constructor(s)
  //**********************************************************************

  public Recipe () {
    name = "";
    descriptionPath = "";
    ingredients = new String[1];
  }

  public Recipe (String name, String descriptionPath, String[] ingredients) {
    this.name = name;
    this.descriptionPath = descriptionPath;
    this.ingredients = ingredients;
  }

  //**********************************************************************
  // Getters & Setters
  //**********************************************************************

  public String getName() {
    return this.name;
  }

  public String getDescriptionPath() {
    return this.descriptionPath;
  }

  public String[] getIngredients() {
    return this.ingredients;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescriptionPath(String descriptionPath) {
    this.descriptionPath = descriptionPath;
  }

  public void setIngredients(String[] ingredients) {
    this.ingredients = ingredients;
  }

  //**********************************************************************
  // Public Methods
  //**********************************************************************

  //**********************************************************************
  // Private Methods
  //**********************************************************************
}
