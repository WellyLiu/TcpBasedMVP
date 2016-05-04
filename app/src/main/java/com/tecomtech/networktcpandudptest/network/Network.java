package com.tecomtech.networktcpandudptest.network;

import android.util.Log;

import com.tecomtech.networktcpandudptest.BasePresenter;

/**
 * Created by welly on 2016/4/18.
 */
public class Network implements BaseNetwork {

    private BasePresenter mPresenter;
    private TcpClient mTcpClient;

    @Override
    public void setPresenter(BasePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void init() {

        new Thread(){
            @Override
            public void run() {
                mTcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
                    @Override
                    //here the messageReceived method is implemented
                    public void messageReceived(String message) {
                        //this method calls the onProgressUpdate
                        // publishProgress(message);
                        onReceived(message);
                    }
                });
                mTcpClient.run();
            }
        }.start();


    }

    @Override
    public void init(final String server, final int port) {
        new Thread(){
            @Override
            public void run() {
                mTcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
                    @Override
                    //here the messageReceived method is implemented
                    public void messageReceived(String message) {
                        //this method calls the onProgressUpdate
                        // publishProgress(message);
                        onReceived(message);
                        Log.d("tst", "2====" + message);
                    }
                });
                mTcpClient.run(server, port);
            }
        }.start();
    }

    @Override
    public void stop() {
        // disconnect
        if(mTcpClient != null) {
            mTcpClient.stopClient();
            mTcpClient = null;
        }
    }

    @Override
    public void sendMsg(String msg) {
        mTcpClient.sendMessage(msg);
    }

    @Override
    public void onReceived(String msg) {
        mPresenter.refreshReceivedMsg(msg);
    }
}
