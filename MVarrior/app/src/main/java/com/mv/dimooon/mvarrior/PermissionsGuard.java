package com.mv.dimooon.mvarrior;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dimooon on 24.02.16.
 */
public class PermissionsGuard {

    public static final int STORAGE_PERMISSION_ID = 0;

    private AppCompatActivity compatActivity;
    private HashMap<Integer,GrantPermissionListener> listeners = new HashMap<>();

    public PermissionsGuard(AppCompatActivity compatActivity) {
        this.compatActivity = compatActivity;
    }

    public void grantPermission(String[] permissionsToCheck,int permissionsId,GrantPermissionListener listener){

        List<String> permissions = new ArrayList<>();

        for (String permission: permissionsToCheck) {
            if(PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(compatActivity, permission)){
                permissions.add(permission);
            }
        }

        if (!permissions.isEmpty()) {
            String[] params = permissions.toArray(new String[permissions.size()]);
            ActivityCompat.requestPermissions(compatActivity, params, permissionsId);
        }

        listener.onGrant();
    }

    public void grantStoragePermission(GrantPermissionListener listener) {

        if(listener == null){
            throw new UnsupportedOperationException("listener is required");
        }

        listeners.put(STORAGE_PERMISSION_ID,listener);
        grantPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_ID, listener);
    }

    public GrantPermissionListener getListener(Integer permissionId){
        return listeners.get(permissionId);
    }

    public interface GrantPermissionListener{
        void onGrant();
        void onDeclined();
    }
}