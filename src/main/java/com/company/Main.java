package com.company;

public class Main {

        public int sharedState;
        public void nonSafeAction() {
            while (sharedState < 100000) {
                int former = sharedState++;
                int latter = sharedState;
                if (former != latter - 1) {
                    System.out.printf("Observed data race, former is " +
                    former + ", " + "latter is " + latter+"/n");
                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            Main sample = new Main();
            Thread threadA = new Thread(){
                @Override
                public void run(){
                    sample.nonSafeAction();
                }
            };
            Thread threadB = new Thread(){
                @Override
                public void run(){
                    sample.nonSafeAction();
                }
            };
            threadA.start();
            threadB.start();
            threadA.join();
            threadB.join();
        }

}
