package com.example.jo_shinichi1.mbisasewa.utils;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by jo_shinichi1 on 7/28/2016.
 */
public class Mysingleton {

    public static Fragment fragment;
    public RequestQueue requestQueue;
    public ImageLoader imageLoader;
    public static Mysingleton mysingleton;

    public Mysingleton(Fragment ctx){
        fragment = ctx;
        this.requestQueue = getRequestque();
        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public RequestQueue getRequestque(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(fragment.getActivity());
        }
        return requestQueue;
    }

    public static synchronized Mysingleton getMysingleton(Fragment context){
        if(mysingleton == null){
            mysingleton = new Mysingleton(context);
        }
        return mysingleton;
    }
    public ImageLoader getImageLoader(){
        return imageLoader;
    }

    public <T> void addToRequestQue(Request<T> request){
        requestQueue.add(request);
    }
}
