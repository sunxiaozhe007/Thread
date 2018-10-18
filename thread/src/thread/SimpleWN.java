package thread;

/**
 * 简单的等待和通知
 * @author sunxiaozhe
 * @time 2018/9/4 22:06
 */
public class SimpleWN {
    final static Object object = new Object();

    public static class T1 extends Thread{
        public void run(){
            synchronized (object){
                System.out.println(System.currentTimeMillis()+":T1 Start!");
                try {
                    System.out.println(System.currentTimeMillis()+":T1 wait for object");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+":T1 end!");
            }
        }
    }
    public static class T2 extends Thread{
        public void run(){
            synchronized (object){
                System.out.println(System.currentTimeMillis()+":T2 start! notify one thread");
                object.notify();
                System.out.println(System.currentTimeMillis()+":T2 end!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
        //在T2通知T1继续执行后，T1并不能立即继续执行，而是等待T2释放object的锁，成功获得锁后才能继续执行
    }
}
