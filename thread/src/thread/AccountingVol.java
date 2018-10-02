package thread;

/**
 * 两个线程同时对一个数据修改，volatile不能保证线程安全
 * @author sunxiaozhe
 * @time 2018/9/5 19:17
 */
public class AccountingVol implements Runnable {

    static AccountingVol instance = new AccountingVol();
    static volatile int i = 0;
    public static void increase(){
        i++;
    }
    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++){
            increase();
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
        //实际输出小于20000000
    }
}
