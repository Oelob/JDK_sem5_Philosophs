package org.example;


import java.util.ArrayList;
import java.util.List;



public class Philosoph extends Thread{


    private boolean isEat;//думает или кушает
    private List<Philosoph> neighbors = new ArrayList<>();//два соседа объекта
    private String name;


    public Philosoph(String name) {
        this.name = name;
    }


    public boolean isEat() {
        return isEat;
    }

   public String getPhilosophName(){
        return this.name;
   }

    /**
     * Метод назначения соседа в случае двух участников
     * @param philosoph
     */
    public void setNeighbor(Philosoph philosoph) {
        this.neighbors.add(philosoph);
    }

    /**
     * Метод назначения соседа в случае участия более двух философов
     * @param philosoph1
     * @param philosoph2
     */
    public void setNeighbors(Philosoph philosoph1, Philosoph philosoph2) {
        this.neighbors.add(philosoph1);
        this.neighbors.add(philosoph2);
    }

    public String getNeighbors() {

        String result = "";

        for (Philosoph neighbor : neighbors) {
            result += neighbor.getPhilosophName()+" ";
        }
        return result;
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

