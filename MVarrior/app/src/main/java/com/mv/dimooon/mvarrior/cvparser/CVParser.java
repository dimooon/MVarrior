package com.mv.dimooon.mvarrior.cvparser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by dimooon on 24.02.16.
 */
public class CVParser {

    private Activity activity;

    public CVParser(Activity activity) {
        this.activity = activity;
    }

    public void asyncParsePdf(){
        new AsyncTask<Void,Void,Void>(){

            private ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(activity);
                progressDialog.setMessage("parsing pdf cv");
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    readPdf();
                } catch (IOException e) {
                    e.printStackTrace(); //TODO: add handling;
                }finally {

                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                progressDialog.dismiss();
            }
        }.execute();
    }

    private void readPdf() throws IOException {
        PDFTextStripper stripper = new PDFTextStripper();
        PDDocument pdDoc = PDDocument.load(new File(Environment.getExternalStorageDirectory(),"Ihor Demedyuk.pdf"));
        StringWriter writer = new StringWriter();
        stripper.writeText(pdDoc, writer);
        System.out.println(writer.toString());
    }

}
