package edu.ou.cs.cg.interaction;

import java.util.ArrayList;
import java.awt.Color;
import java.util.Random;

public final class Node
{
  //**********************************************************************
  // Private Class Members
  //**********************************************************************

  private static String name;
  private static int sides;
  private static double r;
  private static Color color;
  private static double posX;
  private static double posY;
  private static boolean isItemSelected;
  private static boolean isNode;

  //**********************************************************************
	// Constructor(s)
	//**********************************************************************

  public Node(String name, int sides, double r, Color color) {
    this.name = name;
    this.sides = sides;
    this.r = r;
    this.color = color;
    this.isItemSelected = false;
    this.isNode = false;

    Random rand = new Random();
    posX = -0.2 + (0.35 + 0.2) * rand.nextDouble();
    posY = -0.2 + (0.35 + 0.2) * rand.nextDouble();

    System.out.println("New node created for: " + name);
  }

  //**********************************************************************
	// Getters and Setters
	//**********************************************************************

  public void setName(String name) {
    this.name = name;
  }

  public void setSides(int sides) {
    this.sides = sides;
  }

  public void setRadius(double r) {
    this.r = r;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public void setPosX(double pos) {
    this.posX = pos;
  }

  public void setPosY(double pos) {
    this.posY = pos;
  }

  public void setSelected(boolean s) {
    this.isItemSelected = s;
  }

  public String getName() {
    return this.name;
  }

  public int getSides() {
    return this.sides;
  }

  public double getRadius() {
    return this.r;
  }

  public Color getColor() {
    return this.color;
  }

  public double getPosX() {
    return this.posX;
  }

  public double getPosY() {
    return this.posY;
  }

  public boolean isSelected() {
    return this.isItemSelected;
  }


  //**********************************************************************
  // Public methods
  //**********************************************************************

  //**********************************************************************
  // Private methods
  //**********************************************************************
}
