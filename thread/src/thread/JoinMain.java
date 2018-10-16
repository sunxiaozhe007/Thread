package thread;

/**
 * join（）实例
 * @author sunxiaozhe
 * @time 2018/9/5 16:01
 */
public class JoinMain {
    public volatile static int i = 0;
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for (i = 0; i < 10000000; i++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread at = new AddThread();
        at.start();
        at.join();
        System.out.println(i);
        //如果不使用join()等待AddThread，那么输出的i可能是0或者一个很小的数
        //因为AddThread才开始执行，i就输出了
        //使用join（）方法后，表示主线程愿意等待AddThread执行完毕，所以输出i的值为10000000
    }
}
