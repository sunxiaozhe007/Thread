package thread;

/**
 * volatile可以保证数据的可见性和有序性
 * @author sunxiaozhe
 * @time 2018/9/5 16:35
 */
public class NoVisibility {

    private static volatile boolean ready;
    private static int number;

    private static class ReaderThread extends Thread{
        public void run(){
            while (!ready);
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(1000);
        number = 42;
        ready = true;
        Thread.sleep(5000);
        //volatile修饰ready变量，线程才可以发现ready的改动
    }
}
