/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itver.topicos.multithreadsync;

class PrintDemoS {
   public void printCount(String name){
    try {
         for(int i = 500; i > 0; i--) {
            System.out.println("Counter "+name+"  ---   "  + i );
            Thread.sleep(300);
         }
     } catch (Exception e) {
         System.out.println("Thread  interrupted.");
     }
   }

}

class ThreadDemoS extends Thread {
   private Thread t;
   private String threadName;
   PrintDemoS  PD;
   int x = 500;

   ThreadDemoS( String name,  PrintDemoS pd){
       threadName = name;
       PD = pd;
   }
   @Override
   public void run() {
     synchronized(PD) {
        PD.printCount(threadName);
     }
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

public class TestThreadSync {
   public static void main(String args[]) {

      System.out.println("Iniciando procesos sincronizados ");
      PrintDemoS PD = new PrintDemoS();

      ThreadDemoS T1 = new ThreadDemoS( "Thread - 1 ", PD );
      ThreadDemoS T2 = new ThreadDemoS( "Thread - 2 ", PD );

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