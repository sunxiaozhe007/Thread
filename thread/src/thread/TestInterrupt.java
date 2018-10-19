package thread;

/**
 * 线程中断
 * @author sunxiaozhe
 * @time 2018/9/4 21:52
 */
public class TestInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while (true){
                    if (Thread.currentThread().isInterrupted()){//检查中断标志是否被中断
                        System.out.println("Interruted!");
                        break;
                    }
                    Thread.yield();//线程让步，当线程调用这个方法后，它就会把自己cpu执行的时间让掉，让自己或其他线程运行，把线程由运行状态变为就绪状态
                }
            }
        };
        t1.start();
        Thread.sleep(5);
        t1.interrupt();  //设置中断
    }
}
