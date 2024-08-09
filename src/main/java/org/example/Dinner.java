package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Dinner extends Thread{

    private List<Philosoph> philosophList;


    public Dinner() {
        this.philosophList = new ArrayList<>();
    }

    public void neighbors(){
        if (this.philosophList.size() == 2 ){
            philosophList.getFirst().setNeighbor(philosophList.getLast());
            philosophList.getLast().setNeighbor(philosophList.getFirst());
        } else if (this.philosophList.size() > 2){
            philosophList.getFirst().setNeighbors(philosophList.getLast(),philosophList.get(1));
            philosophList.getLast().setNeighbors(philosophList.getFirst(),philosophList.get(philosophList.size()-2));
            for (int i = 1; i < philosophList.size()-1; i++) {
                philosophList.get(i).setNeighbors(philosophList.get(i-1), philosophList.get(i+1));
            }
        }
        for (Philosoph philosoph : philosophList) {
            System.out.println("Neighbors for " + philosoph.getPhilosophName() + " is " + philosoph.getNeighborsNames());
        }
    }


    @Override
    public void run() {
        this.philosophList = new ArrayList<>(Arrays.asList(
                new Philosoph("Socrat", this, false, new Semaphore(2)),
                new Philosoph("Decart", this, false, new Semaphore(2)),
                new Philosoph("Aristotel", this,false, new Semaphore(2)),
                new Philosoph("Platon", this, false, new Semaphore(2)),
                new Philosoph("Seneca", this, false, new Semaphore(2))
        ));
        neighbors();
        for (Philosoph ph: philosophList) {
            new Thread(ph).start();
        }

    }
}
