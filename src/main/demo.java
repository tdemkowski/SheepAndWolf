package main;



public class demo implements Runnable
{
 
        public void run()
        {
                lib.Counter.decrement();
 
                System.out.println("Decremented");
        }
 
        public static void main(String[] args)
        {
                lib.Counter.set(21);
 
                Thread A = new Thread(new demo());
                Thread B = new Thread(new demo());
                Thread C = new Thread(new demo());
 
                A.start();
                B.start();
                C.start();
 
                // wait until all threads are completed
                try { Thread.sleep(500); } catch(InterruptedException e) {}
                // we expect the count to be 18
 
                int local_count = 0;
                while (!lib.Counter.depleted())
                {
                       local_count++;
                       lib.Counter.decrement();
                       System.out.println("Loop - Decremented");
                }
                System.out.println("Local count = "+local_count);
                
 
                // local_count expected to be 18
                // Typically results in 20 instead, meaning that two of the threads failed to decrement the Counter
 
        }
}
