package com.lwp.optimizingtest.memory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lwp.optimizingtest.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * 模拟内存泄露的Activity
 */
public class MemoryLeakActivity extends AppCompatActivity implements CallBack{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoryleak);

        ImageView imageView = findViewById(R.id.iv_memoryleak);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.splash);
        imageView.setImageBitmap(bitmap);

        CallBackManager.addCallBack(this);

        //磁盘读写检测 测试
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .penaltyLog()
                .build());

        findViewById(R.id.iv_memoryleak).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToExternalStorage();
            }
        });
    }

    /**
     * 文件系统的操作
     */
    public void writeToExternalStorage() {
        try {
            File externalStorage = Environment.getExternalStorageDirectory();

            File mbFile = new File(externalStorage, "xxx.txt");
            if (mbFile.exists()){
                mbFile.createNewFile();
            }

            OutputStream output = new FileOutputStream(mbFile, true);
            output.write("www.wooyun.org".getBytes());
            output.flush();
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        CallBackManager.removeCallBack(this);
    }

    @Override
    public void dpOperate() {
        // do sth
    }
}
