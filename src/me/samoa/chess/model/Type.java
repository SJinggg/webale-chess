package me.samoa.chess.model;

public enum Type{
  Arrow, Chevron, Plus, Triangle, Sun;

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