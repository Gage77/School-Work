package edu.ou.cs.hci.stages;

public class GroceryItem
{
  //**********************************************************************
  // Public Class Members
  //**********************************************************************

  //**********************************************************************
  // Private Class Members
  //**********************************************************************

  private static String name;
  private static String amount;

  //**********************************************************************
  // Constructor(s)
  //**********************************************************************

  public GroceryItem () {
    name = "";
    amount = "";
  }

  public GroceryItem (String name, String amount) {
    this.name = name;
    this.amount = amount;
  }

  //**********************************************************************
  // Getters & Setters
  //**********************************************************************

  public String getName() {
    return this.name;
  }

  public String getAmount() {
    return this.amount;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  //**********************************************************************
  // Public Methods
  //**********************************************************************

  //**********************************************************************
  // Private Methods
  //**********************************************************************
}
