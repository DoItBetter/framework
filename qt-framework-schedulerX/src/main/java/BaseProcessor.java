import com.alibaba.schedulerx.worker.domain.JobContext;
import com.alibaba.schedulerx.worker.processor.JavaProcessor;

/**
 * Created by IntelliJ IDEA.
 * User: amyyu
 * Date: 2019-08-14
 * Time: 19:06
 */
public abstract class BaseProcessor extends JavaProcessor {
    private volatile boolean stop = false;

    @Override
    public void kill(JobContext context) {
        stop = true;
    }
}

