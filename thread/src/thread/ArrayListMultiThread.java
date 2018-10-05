package thread;

import java.util.ArrayList;
/**
 * 并发下的ArrayList 错误实例
 * @author sunxiaozhe
 * @time 2018/9/5 19:52
 */
public class ArrayListMultiThread  {
    static ArrayList /*Vector*/<Integer> al = new /*Vector*/ArrayList<Integer>(10);
    public static class AddThread implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++){
                al.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(al.size());
        //正确输出数组元素为2000000
        //实际输出小于2000000
        //修改：用线程安全的Vector代替ArrayList
    }
}
