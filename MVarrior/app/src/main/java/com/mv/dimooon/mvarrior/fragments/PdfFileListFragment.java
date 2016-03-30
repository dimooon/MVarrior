package com.mv.dimooon.mvarrior.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mv.dimooon.mvarrior.FileSelectListener;
import com.mv.dimooon.mvarrior.PdfFileLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PdfFileListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<File>> {

    private static final String TAG = PdfFileListFragment.class.getSimpleName();
    private PdfFilesListAdapter filesAdapter;
    private FileSelectListener listener;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        filesAdapter = new PdfFilesListAdapter(new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new ArrayList()));

        filesAdapter.assignData(null);
        setListAdapter(filesAdapter.getAdapter());

        getLoaderManager().initLoader(0, null, this).forceLoad();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(listener!=null){
            listener.onFilePicked(filesAdapter.handleItemClick(position));
        }
    }

    @Override
    public Loader<List<File>> onCreateLoader(int id, Bundle args) {
        return new PdfFileLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<File>> loader, List<File> data) {
        Log.e(TAG, "onLoadFinished: " + data);
        filesAdapter.assignData(data);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        Log.e(TAG,"onLoaderReset");
        filesAdapter.assignData(null);
    }

    public void setListener(FileSelectListener listener){
        this.listener = listener;
    }

    private class PdfFilesListAdapter{

        private ArrayAdapter<String> filesAdapter;
        private ArrayList<File> data = new ArrayList<>();

        public PdfFilesListAdapter(ArrayAdapter<String> filesAdapter) {
            this.filesAdapter = filesAdapter;
        }

        public void assignData(List<File> objects){
            ArrayList<String> fileName;
            data.clear();

            if(objects!=null){

                fileName = new ArrayList<>();

                for (File f: objects){
                    fileName.add(f.getName());
                }

                filesAdapter.addAll(fileName);
                data.addAll(objects);
            }else{
                filesAdapter.clear();
            }


            filesAdapter.notifyDataSetChanged();
        }

        public ArrayAdapter<String> getAdapter(){
            return filesAdapter;
        }

        public File handleItemClick(int position){
            return data == null || data.size() < position ? null : data.get(position);
        }

    }
}
