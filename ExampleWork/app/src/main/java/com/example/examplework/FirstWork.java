package com.example.examplework;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class FirstWork extends Worker {
    public FirstWork(@NonNull Context context, @NonNull  WorkerParameters workerParameters){
        super(context,workerParameters);

    }

    @Override
    public Result doWork() {
        Log.i("APDSSC","This is the first work");
        return Result.success();
    }
}
