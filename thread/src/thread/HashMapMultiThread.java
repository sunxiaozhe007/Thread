package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 并发下的HasMap 错误实例
 * @author sunxiaozhe
 * @time 2018/9/5 20:12
 */
public class HashMapMultiThread {
    static Map<String,String> map = new /*ConcurrentHashMap*/HashMap<>();

    public static class AddThread implements Runnable{
        int start = 0;
        public AddThread(int start){
            this.start = start;
        }
        @Override
        public void run() {
            for (int i = start; i < 100000; i+=2){
                map.put(Integer.toString(i),Integer.toBinaryString(i));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new HashMapMultiThread.AddThread(0));
        Thread t2 = new Thread(new HashMapMultiThread.AddThread(1));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(map.size());
        //可能结果
        //1.程序正常结束，HasMap大小为100000；
        //2.程序正常结束，输出结果错误，小于100000；
        //3.程序永远无法结束
        //修改方案使用ConcurrentHashMap代替HasMap；
    }
}
