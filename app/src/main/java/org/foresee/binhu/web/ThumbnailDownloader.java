package org.foresee.binhu.web;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ThumbnailDownloader<T> extends HandlerThread {
    private static final String TAG="ThumbnailDownloader";
    private boolean mHasQuit=false;
    private static final int MESSAGE_DOWNLOAD = 0;
    private Handler mRequestHandler;
    private ConcurrentMap<T,String> mRequestMap = new ConcurrentHashMap<>();
    private Handler mResponseHandler;
    private ThumbnailDownloadListener<T> mThumbnailDownloadListener;
    public interface ThumbnailDownloadListener<T> {
        void onThumbnailDownloaded(T obj, Bitmap thumbnail);
        void onError(T obj);
    }
    public void setThumbnailDownloadListener(ThumbnailDownloadListener<T> listener) {
        mThumbnailDownloadListener = listener;
    }
    public ThumbnailDownloader(Handler responseHandler) {
        super(TAG);
        mResponseHandler=responseHandler;
    }
    @Override
    public boolean quit() {
        mHasQuit = true;
        return super.quit();
    }
    public void clearQueue(){
        mResponseHandler.removeMessages(MESSAGE_DOWNLOAD);
        mRequestMap.clear();
    }
    @Override
    protected void onLooperPrepared() {
        mRequestHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_DOWNLOAD) {
                    T obj = (T) msg.obj;
                    Log.i(TAG, "Got a request for URL: " + mRequestMap.get(obj));
                    handleRequest(obj);
                }
            }
        };
    }

    private void handleRequest(final T obj) {
        try {
            final String url = mRequestMap.get(obj);
            if (url == null) {
                return;
            }
            byte[] bitmapBytes = new Fetchr().getUrlBytes(url);
            final Bitmap bitmap = BitmapFactory
                    .decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
            Log.i(TAG, "Bitmap created");
            mResponseHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(mRequestMap.get(obj)!=url || mHasQuit){
                        return;
                    }
                    mRequestMap.remove(obj);
                    mThumbnailDownloadListener.onThumbnailDownloaded(obj, bitmap);
                }
            });
        } catch (IOException ioe) {
            Log.e(TAG, "Error downloading image", ioe);
            mResponseHandler.post(new Runnable() {
                @Override
                public void run() {
                    mThumbnailDownloadListener.onError(obj);
                }
            });
        }
    }

    public void queueThumbnail(T obj, String url) {
        Log.i(TAG, "Got a URL: " + url);
        if (url == null) {
            mRequestMap.remove(obj);
        } else {
            mRequestMap.put(obj, url);
            mRequestHandler.obtainMessage(MESSAGE_DOWNLOAD, obj)
                    .sendToTarget();
        }
    }
}
