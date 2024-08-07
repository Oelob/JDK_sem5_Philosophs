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
        if (!this.philosophList.isEmpty()){


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


        for (Philosoph philosoph : philosophList) {
            new Thread(philosoph).start();
        }


    }
}
