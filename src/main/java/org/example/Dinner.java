package org.example;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Dinner extends Thread{


    private List<Philosoph> philosophList;




    public Dinner() {
        this.philosophList = new ArrayList<>();
    }


//    public void addPhilosoph(Philosoph philosoph){
//        this.philosophList.add(philosoph);
//    }


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
            System.out.println("Neighbors for " + philosoph.getPhilosophName() + " is " + philosoph.getNeighbors());
        }
    }


    @Override
    public void run() {
        this.philosophList = new ArrayList<>(Arrays.asList(
                new Philosoph("Socrat"),
                new Philosoph("Decart"),
                new Philosoph("Aristotel"),
                new Philosoph("Platon"),
                new Philosoph("Seneca")
        ));
        neighbors();
        for (Philosoph philosoph : philosophList) {
            new Thread(philosoph).start();
        }

    }
}
