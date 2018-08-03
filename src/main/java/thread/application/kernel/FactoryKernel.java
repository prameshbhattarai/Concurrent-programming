package thread.application.kernel;

import java.util.HashMap;
import java.util.Map;

public class FactoryKernel {

    public static Kernel getKernel(String kernelType) {
        Map<String, Kernel> kernelMap = new HashMap<>();
        kernelMap.put("reentrant", new ReentrantLockKernel());
        kernelMap.put("synchronized", new SynchronizedKernel());
        kernelMap.put("atomic", new AtomicKernel());
        kernelMap.put("noConcurrent", new NoConcurrentKernel());
        return kernelMap.get(kernelType);
    }
}
