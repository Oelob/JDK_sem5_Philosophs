package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Philosoph extends Thread {
    private boolean isEat;//думает или кушает
    private final List<Philosoph> neighbors = new ArrayList<>();//два соседа объекта
    private final String name;
    private final Dinner dinner;
    Semaphore sem;
    private AtomicInteger COUNT_EATING = new AtomicInteger();


    public Philosoph(String name, Dinner dinner, boolean isEat, Semaphore sem) {
        this.name = name;
        this.dinner = dinner;
        this.isEat = isEat;
        this.sem = sem;
    }

    public String getPhilosophName() {
        return this.name;
    }

    /**
     * Метод назначения соседа в случае двух участников
     *
     * @param philosoph
     */
    public void setNeighbor(Philosoph philosoph) {
        this.neighbors.add(philosoph);
    }

    /**
     * Метод назначения соседа в случае участия более двух философов
     *
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
            result += neighbor.getPhilosophName() + " ";
        }
        return result;
    }

    public List<Philosoph> getNeighbors() {
        return this.neighbors;
    }

    public boolean neigborsState() {
        return !this.getNeighbors().getFirst().isEat && !this.getNeighbors().getLast().isEat;
    }

    public void philosophsEating() throws InterruptedException {
        sem.acquire();
        this.isEat = true;
        if (!this.getNeighbors().getFirst().isEat && !this.getNeighbors().getLast().isEat) {
            System.out.println("Great philosoph " + this.getPhilosophName() + " is eating "
                    + COUNT_EATING.incrementAndGet() + " " + getNeighbors());
            sleep(1000);
            System.out.println("Great philosoph " + this.getPhilosophName() + " is thinking");
            this.isEat = false;
            sem.release();
        }else {
            isEat = false;
            sem.release();
        }
    }
    @Override
    public void run() {
        try {
            while (COUNT_EATING.get()<3){
                philosophsEating();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return
                "name='" + name + "\' " + isEat;
    }
}

