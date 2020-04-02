package com.lwp.optimizingtest;

import android.app.Application;
import android.os.StrictMode;

import com.github.moduth.blockcanary.BlockCanary;
import com.lwp.optimizingtest.block.AppBlockCanaryContext;
import com.lwp.optimizingtest.memory.MemoryLeakActivity;

/**
 * <pre>
 *     author : 李蔚蓬（简书_凌川江雪）
 *     time   : 2020/3/31 22:19
 *     desc   :
 * </pre>
 */
public class TestApp extends Application {

//    static MemoryLeakActivity i = new MemoryLeakActivity();
//    static MemoryLeakActivity j = new MemoryLeakActivity();

    @Override
    public void onCreate() {
        super.onCreate();

        //实例限制检测 测试
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .setClassInstanceLimit(MemoryLeakActivity.class, 1)
                .detectLeakedClosableObjects() //API等级11
                .penaltyLog()
                .build());

        //AndroidPerformanceMonitor测试
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
    }
}
