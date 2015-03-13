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
class TimerAnimation extends Timer{
    private final Animation animation;
    private Runnable finalOperation;

    public TimerAnimation(Runnable r, long time,Animation animation) {
        super(r, time);
        this.animation = animation;
    }
     public TimerAnimation(Runnable r,Runnable finalOperation, long time,Animation animation) {
        super(r, time);
        this.animation = animation;
        this.finalOperation = finalOperation;
                
    }

    @Override
    public void doInWhile(int count) {
        super.doInWhile(count);
        if(animation.isFinish()){
//            System.out.println("animation timer interupt");
            interrupt();
            finalOperation.run();
        }
    }

   
    
    
}
