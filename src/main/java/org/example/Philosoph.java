package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


public class Philosoph extends Thread{

    private boolean isEat;//думает или кушает
    private List<Philosoph> neighbors = new ArrayList<>();//два соседа объекта
    private final String name;
    private final Dinner dinner;
    Semaphore sem = new Semaphore(2);


    public Philosoph(String name, Dinner dinner, boolean isEat, Semaphore sem) {
        this.name = name;
        this.dinner = dinner;
        this.isEat = isEat;
        this.sem = sem;
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

    public String getNeighborsNames() {

        String result = "";

        for (Philosoph neighbor : neighbors) {
            result += neighbor.getPhilosophName()+" ";
        }
        return result;
    }

    public List<Philosoph> getNeighbors(){
        return this.neighbors;
    }

    public boolean neigborsState(){
        if (this.getNeighbors().getFirst().isEat == false && this.getNeighbors().getLast().isEat == false) {
            return true;
        }
        else {return false;}
    }

    public boolean getIsEat(){
        return this.isEat;
    }

    public boolean changeIsEat(){
        return this.isEat = !isEat;
    }

    public void philosophsEating() throws InterruptedException {
        if (this.neigborsState()) {
            sem.acquire();
            this.changeIsEat();
            System.out.println("Great philosoph " + this.getPhilosophName() + " is eating");
            this.changeIsEat();
//            sleep(1000);
            sem.release();
        }
        System.out.println("Great philosoph " + this.getPhilosophName() + " is thinking");
        sleep(5000);

    }

    @Override
    public void run() {
//        if(isEat) {
//            System.out.println("Great philosoph " + this.name + " is eating");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }else {
//            System.out.println("Great philosoph " + this.name + " is thinking");
//        }
//        try {
//        try {
//            this.dinner.philosophsEating(this);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        try {
            philosophsEating();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}

