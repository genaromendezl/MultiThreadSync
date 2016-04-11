/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itver.topicos.multithreadsync;

class PrintDemo {
   public void printCount(){
    try {
         for(int i = 50; i > 0; i--) {
            System.out.println("Contador   ---   "  + i );
         }
     } catch (Exception e) {
         System.out.println("Thread  interrumpido.");
     }
   }

}

class ThreadDemo extends Thread {
   private Thread t;
   private String threadName;
   PrintDemo  PD;

   ThreadDemo( String name,  PrintDemo pd){
       threadName = name;
        PD = pd;
   }
   public void run() {
     PD.printCount();
     System.out.println("Thread " +  threadName + " terminando.");
   }

   public void start ()
   {
      System.out.println("Iniciando " +  threadName );
      if (t == null)
      {
         t = new Thread (this, threadName);
         t.start ();
      }
   }

}

public class TestThreadNoSync {
   public static void main(String args[]) {

      PrintDemo PD = new PrintDemo();

      ThreadDemo T1 = new ThreadDemo( "Thread - 1 ", PD );
      ThreadDemo T2 = new ThreadDemo( "Thread - 2 ", PD );

      T1.start();
      T2.start();

      // wait for threads to end
      try {
         T1.join();
         T2.join();
      } catch( Exception e) {
         System.out.println("Interrumpido.");
      }
   }
}
