package com.lwp.optimizingtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.moduth.blockcanary.BlockCanary;
import com.lwp.optimizingtest.block.AppBlockCanaryContext;
import com.lwp.optimizingtest.memory.MemoryLeakActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AndroidPerformanceMonitor测试
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void go(View view) {
        startActivity(new Intent(MainActivity.this, MemoryLeakActivity.class));
    }
}
