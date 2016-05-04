package com.tecomtech.networktcpandudptest;

import android.util.Log;

import com.tecomtech.networktcpandudptest.network.BaseNetwork;
import com.tecomtech.networktcpandudptest.network.Network;

/**
 * Created by welly on 2016/4/18.
 */
public class Presenter implements BasePresenter {

    private BaseView mView;
    private BaseNetwork network;
    public Presenter(BaseView view) {
        mView = view;
    }


    @Override
    public void startNetwork(String ip, int port) {
        network = new Network();
        network.setPresenter(this);
        this.setNetworkProxy(network);
        this.startTcpClient(ip, port);
    }

    @Override
    public void startNetwork() {
        network = new Network();
        network.setPresenter(this);
        this.setNetworkProxy(network);
        this.startTcpClient();
    }

    @Override
    public void setNetworkProxy(BaseNetwork mBaseNetwork)
    {
        network = mBaseNetwork;
    }

    @Override
    public void startTcpClient(String server, int port) {
        network.init(server, port);
    }

    @Override
    public void startTcpClient() {
        network.init();
    }

    @Override
    public void stopTcpClent() {
        network.stop();
    }

    @Override
    public void startHeartProcess() {

    }

    @Override
    public void stopHeartProcess() {

    }

    @Override
    public void refreshHeartCount(int count) {
        mView.updateHeartCount(count);
    }

    @Override
    public void refreshReceivedMsg(String msg) {
        Log.d("tst", "4====" + msg);
        mView.updateReceivedMsg(msg);
    }

    @Override
    public int sendMsg(String msg) {
        network.sendMsg(msg);
        return 0;
    }
}
