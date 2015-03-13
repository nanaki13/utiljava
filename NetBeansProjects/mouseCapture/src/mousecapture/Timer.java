/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture;

/**
 *
 * @author jonathan
 */
public class Timer extends Thread {

    private final Runnable r;
    private final long time;
    private Integer nb;
    private long locTime;

    public Timer(Runnable r, long time) {
        this.r = r;
        this.time = time;
    }

    public Timer(Runnable runnable, int time, int nb) {
        this.r = runnable;
        this.time = time;
        this.nb = nb;
    }

    public synchronized void run() {
        int count = 0;
        while (!isInterrupted()) {
         doInWhile(count);
         count++;
        }
        
    }

    public void doInWhile(int count ) {
//           System.out.println("je suis timer");
            long start = System.currentTimeMillis();
            if (nb == null) {
//                System.out.println("nb == null");
                r.run();

                try {
                    locTime = time - System.currentTimeMillis() + start;
                    wait(locTime>=0 ? locTime : 0);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            } else {

                if (count > nb - 1) {
                    interrupt();
                
                } else {
                    try {
                        r.run();
                        wait(time - System.currentTimeMillis() + start);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }

    }

}
