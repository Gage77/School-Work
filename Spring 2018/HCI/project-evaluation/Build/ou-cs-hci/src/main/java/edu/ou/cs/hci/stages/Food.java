package edu.ou.cs.hci.stages;

public class Food
{
  //**********************************************************************
  // Public Class Members
  //**********************************************************************

  //**********************************************************************
  // Private Class Members
  //**********************************************************************

  private String name;
  private String amount;
  private String expDate;
  private boolean isFavorite;
  private boolean isLeftover;

  //**********************************************************************
  // Constructor(s)
  //**********************************************************************

  public Food () {
    name = "null_food";
    amount = "";
    expDate = "";
    isFavorite = false;
    isLeftover = false;
  }

  public Food (String name, String amount, String expDate, boolean isFavorite, boolean isLeftover) {
    this.name = name;
    this.amount = amount;
    this.expDate = expDate;
    this.isFavorite = isFavorite;
    this.isLeftover = isLeftover;
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

  public String getExpDate() {
    return this.expDate;
  }

  public boolean isFavorite() {
    return this.isFavorite;
  }

  public boolean isLeftover() {
    return this.isLeftover;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public void setExpDate(String expDate) {
    this.expDate = expDate;
  }

  public void setIsFavorite(boolean isFavorite) {
    this.isFavorite = isFavorite;
  }

  public void setIsLeftover(boolean isLeftover) {
    this.isLeftover = isLeftover;
  }

  //**********************************************************************
  // Public Methods
  //**********************************************************************

  //**********************************************************************
  // Private Methods
  //**********************************************************************
}
