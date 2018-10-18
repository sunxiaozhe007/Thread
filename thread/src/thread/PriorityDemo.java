package thread;

/**
 * 线程优先级
 * @author sunxiaozhe
 * @time 2018/9/5 17:33
 */
public class PriorityDemo {
    public static class HightPriority extends Thread{
        static int count = 0;
        public void run(){
            while (true){
                synchronized (PriorityDemo.class){
                    count++;
                    if (count > 10000000){
                        System.out.println("HeightPriority is complete");
                        break;
                    }
                }
            }
        }
    }
    public static class LowPriority extends Thread{
        static int count = 0;
        public void run(){
            while (true){
                synchronized (PriorityDemo.class){
                    count++;
                    if (count > 10000000){
                        System.out.println("LowPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread high = new HightPriority();
        LowPriority low = new LowPriority();
        high.setPriority(Thread.MAX_PRIORITY);//高优先级
        low.setPriority(Thread.MIN_PRIORITY);//低优先级
        low.start();
        high.start();
    }
}
