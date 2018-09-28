package thread;

/**
 * @author sunxiaozhe
 * @time 2018/9/4 21:04
 */
public interface Callable<S> {
    String call() throws Exception;
}
