package com.mv.dimooon.mvarrior;

import android.content.Context;
import android.os.Environment;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.File;
import java.util.List;

/**
 * Created by dimooon on 25.03.16.
 */
public class PdfFileLoader extends AsyncTaskLoader<List<File>> {

    private static final String TAG = PdfFileLoader.class.getSimpleName();

    public PdfFileLoader(Context context) {
        super(context);
        Log.e(TAG,"created");
    }

    @Override
    public List<File> loadInBackground() {
        Log.e(TAG,"loadInBackground");
        return new FileUtils().walkDir(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
    }

    @Override
    public void deliverResult(List<File> data) {
        Log.e(TAG,"deliverResult: "+data);
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        Log.e(TAG, "onStartLoading");
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
        Log.e(TAG,"onStopLoading");
    }

    @Override
    protected void onReset() {
        Log.e(TAG,"onReset");
    }

    @Override
    public void onCanceled(List<File> data) {
        Log.e(TAG,"onCancelled");
    }


}
