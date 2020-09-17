package me.samoa.chess.model;

/**
 * Indicates the type of webale piece
 */
public enum Type {

  Arrow, Chevron, Plus, Triangle, Sun;

  /**
   * toString() function
   */
  public String toString(){
    switch(this){
    case Arrow:
      return "Arrow";
    case Chevron:
      return "Chevron";
    case Plus:
      return "Plus";
    case Triangle:
      return "Triangle";
    case Sun:
      return "Sun";
    }
    return null;
  }

  /**
   * Get the type from the String
   * 
   * @param str the string to be matched with the type
   * @return return the type
   */
  public static Type getType (String str){
    if(str.equals(Arrow.toString()))
      return Arrow;
    else if(str.equals(Chevron.toString()))
      return Chevron;
    else if(str.equals(Plus.toString()))
      return Plus;
    else if(str.equals(Triangle.toString()))
      return Triangle;
    else if(str.equals(Sun.toString()))
      return Sun;
    else return null;
  }
}