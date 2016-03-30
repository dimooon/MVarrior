package com.mv.dimooon.mvarrior.cvparser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;

import com.mv.dimooon.mvarrior.PdfParserListener;

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

    public void asyncParsePdf(final File f,final PdfParserListener listener){
        new AsyncTask<Void,Void,String>(){

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
            protected String doInBackground(Void... params) {

                try {
                    return readPdf(f);
                } catch (IOException e) {
                    e.printStackTrace(); //TODO: add handling;
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                progressDialog.dismiss();
                if(listener!=null){
                    listener.onParseComplete(result);
                }
            }
        }.execute();
    }

    private String readPdf(File fileToRead) throws IOException {
        PDFTextStripper stripper = new PDFTextStripper();
        PDDocument pdDoc = PDDocument.load(fileToRead);
        StringWriter writer = new StringWriter();
        stripper.writeText(pdDoc, writer);
        return writer.toString();
    }

}