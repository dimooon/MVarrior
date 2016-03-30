package com.mv.dimooon.mvarrior;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mv.dimooon.mvarrior.cvparser.CVParser;
import com.mv.dimooon.mvarrior.dao.ContactInfo;
import com.mv.dimooon.mvarrior.dao.Education;
import com.mv.dimooon.mvarrior.dao.Experience;
import com.mv.dimooon.mvarrior.dao.Languages;
import com.mv.dimooon.mvarrior.dao.Player;
import com.mv.dimooon.mvarrior.dao.Skill;
import com.mv.dimooon.mvarrior.fragments.PdfFileListFragment;
import com.mv.dimooon.mvarrior.fragments.SettingsFragment;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by dimooon on 22.02.16.
 */
public class CVBaseActivity extends AppCompatActivity {

    private static final String TAG =CVBaseActivity.class.getSimpleName() ;
    protected PermissionsGuard permissionsGuard;
    private PdfFileListFragment pdfFileListFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        permissionsGuard = new PermissionsGuard(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {

        if(View.VISIBLE == findViewById(R.id.filesListFragmentContainer).getVisibility()){
            findViewById(R.id.filesListFragmentContainer).setVisibility(View.GONE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            return;
        }

        if (getSupportFragmentManager().getBackStackEntryCount()>0){
            getSupportFragmentManager().popBackStackImmediate();
            return;
        }
        if(getFragmentManager().getBackStackEntryCount()>0){
            getFragmentManager().popBackStackImmediate();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));

            SettingsFragment newFragment = new SettingsFragment();
            getFragmentManager().beginTransaction().replace(R.id.main_activity_root, newFragment).addToBackStack(null).commit();

            return true;
        }else if(id == R.id.action_find_pdf_cv){
            if(pdfFileListFragment==null) {

                String readedPdf = new FileUtils().readFromFile(getApplicationContext(), new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "DNCVText.txt"));

                TextView tv = ((TextView) findViewById(R.id.helloText));
                tv.setText((Arrays.asList(parseCV(readedPdf))).toString());

                if(1==1){
                    return true;
                }

                pdfFileListFragment = new PdfFileListFragment();
                pdfFileListFragment.setListener(new FileSelectListener() {
                    @Override
                    public void onFilePicked(File file) {
                        onBackPressed();

                        new CVParser(CVBaseActivity.this).asyncParsePdf(file, new PdfParserListener() {
                            @Override
                            public void onParseComplete(String parsedPdf) {
                                ((TextView) findViewById(R.id.helloText)).setText(parsedPdf);

                            }
                        });
                    }
                });
                getSupportFragmentManager().beginTransaction().add(R.id.filesListFragmentContainer, pdfFileListFragment).commit();
            }

            findViewById(R.id.filesListFragmentContainer).setVisibility(View.VISIBLE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        }else if(id == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsGuard.GrantPermissionListener listener = permissionsGuard.getListener(requestCode);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            listener.onGrant();
        } else {
            listener.onDeclined();
        }
        return;

    }

    private String parseCV(String sv){

        String EXPERIENCE_TAG = "Experience";
        String PROJECTS_TAG = "Projects";
        String LANGUAGES_TAG = "Languages";
        String SKILL_LIST_AND_EXPERIENCE_TAG = "Skills & Expertise";
        String EDUCATION_TAG = "Education";
        String ACTIVITIES_AND_SOCIETIES = "Activities and Societies";

        String[] tags = new String[] {EXPERIENCE_TAG,PROJECTS_TAG,LANGUAGES_TAG,SKILL_LIST_AND_EXPERIENCE_TAG,EDUCATION_TAG};

        Player.deleteAll(Player.class);

        int NAME_AND_SERNAME = 1;
        int EMAIL = 3;

        String delims = "[\n]+";
        String delimName = "[ ]+";
        String[] tokens = sv.split(delims);

        String eMail = "";

        Player player = null;
        ContactInfo contact;

        for(int i=0;i<tokens.length;i++){

            if(NAME_AND_SERNAME == i){
                Log.e(TAG,"grabbing UserName and SerName");
                String nameAndSerName = tokens[NAME_AND_SERNAME];
                String[] nameAndSernameArray = nameAndSerName.split(delimName);

                player = new Player(nameAndSernameArray[0],nameAndSernameArray[1]);
                player.save();

            }

            if(EMAIL == i){
                Log.e(TAG,"grabbing eMail");

                contact = new ContactInfo("main contact",eMail,"","",player);
                contact.save();

            }
            //Parsing experience part
            if(EXPERIENCE_TAG.equals(tokens[i])){
                int currentI = i+1;
                String title = "";
                StringBuilder descriptionBuilder;
                do{
                    Log.e(TAG, "title:" + tokens[currentI]);
                    descriptionBuilder = new StringBuilder();
                    title = tokens[currentI];
                    do{
                        currentI++;
                        if(tokens[currentI].contains("#)")){
                            Log.e(TAG,"date:"+tokens[currentI]);
                            int tempI = currentI;
                            if(tokens[tempI+2].contains("#)")){
                                Log.e(TAG,"no description assigned - skip");
                                currentI++;
                                break;
                            }else{
                                currentI++;
                                Log.e(TAG,"will grab description");
                                while (!(tokens[currentI].contains("#)"))&&!(PROJECTS_TAG.equals(tokens[currentI]))){
                                    Log.e(TAG, "description:" + tokens[currentI]);
                                    descriptionBuilder.append(tokens[currentI]);
                                    currentI++;
                                }
                                currentI--;
                            }
                        }else if(PROJECTS_TAG.equals(tokens[currentI])){
                            break;
                        }
                    }while ((!tokens[currentI].contains("#)")));
                    new Experience(title,new Date().getTime(),new Date().getTime(),descriptionBuilder.toString(),player).save();
                }while (!PROJECTS_TAG.equals(tokens[currentI]));
            }

            if(LANGUAGES_TAG.equals(tokens[i])){
                int currentI = i;
                currentI++;
                while (!SKILL_LIST_AND_EXPERIENCE_TAG.equals(tokens[currentI])){
                    Log.e(TAG, "language:" + tokens[currentI]);
                    new Languages(tokens[currentI],"",player).save();
                    currentI++;
                }
            }

            if(SKILL_LIST_AND_EXPERIENCE_TAG.equals(tokens[i])){
                int currentI = i;
                currentI++;
                while (!EDUCATION_TAG.equals(tokens[currentI])){
                    Log.e(TAG, "skill:" + tokens[currentI]);
                    new Skill(tokens[currentI],player).save();
                    currentI++;
                }
            }

            if(EDUCATION_TAG.equals(tokens[i])){
                int currentI = i;
                currentI++;
                Log.e(TAG, "education:" + tokens[currentI]);
                new Education("education some",tokens[currentI],tokens[currentI+1],new Date().getTime(),"",player).save();
                currentI++;
            }
        }
        return player.toString();
    }
}