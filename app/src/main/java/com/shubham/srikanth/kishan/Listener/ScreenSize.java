package com.shubham.srikanth.kishan.Listener;

import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;


public class ScreenSize {



    public  double getScreenSize(WindowManager windowManager){

        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int width=displayMetrics.widthPixels;
        int height=displayMetrics.heightPixels;
        double wi=(double)width/(double)displayMetrics.xdpi;
        double hi=(double)height/(double)displayMetrics.ydpi;
        double x = Math.pow(wi,2);
        double y = Math.pow(hi,2);
         return Math.sqrt(x+y);


    }
}
