package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
public class Dinner {
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

    public void run() {
        Semaphore sem = new Semaphore(2);
        this.philosophList = new ArrayList<>(Arrays.asList(
                new Philosoph("Socrat", this, false, sem),
                new Philosoph("Decart", this, false, sem),
                new Philosoph("Aristotel", this,false, sem),
                new Philosoph("Platon", this, false, sem),
                new Philosoph("Seneca", this, false, sem)
        ));
        neighbors();
        for (Philosoph ph: philosophList) {
            new Thread(ph).start();
        }
    }
}
