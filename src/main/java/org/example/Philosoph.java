package org.example;


import java.util.ArrayList;
import java.util.List;



public class Philosoph extends Thread{


    private boolean isEat;//думает или ест
    private List<Philosoph> neighbor = new ArrayList<>();
    private final String name;


    public Philosoph(String name) {
        this.name = name;
    }


    public boolean isEat() {
        return isEat;
    }


    public void setNeighbor(List<Philosoph> neighbor, Philosoph philosoph) {
        this.neighbor.add(philosoph);
    }


    @Override
    public void run() {
        if(isEat) {
            System.out.println("Great philosoph " + this.name + " is eating");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println("Great philosoph " + this.name + " is thinking");
        }
    }
}

