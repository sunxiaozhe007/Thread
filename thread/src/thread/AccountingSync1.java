package thread;

/**
 * 将synchronized作用于一个给定的对象，保证线程安全
 * @author sunxiaozhe
 * @time 2018/9/5 19:17
 */
public class AccountingSync1 implements Runnable {

    static AccountingSync1 instance = new AccountingSync1();
    static  int i = 0;
    public static void increase(){
        i++;
    }
    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++){
            synchronized (instance){  //对instance加锁
                i++;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
        //正确输出20000000
    }
}
