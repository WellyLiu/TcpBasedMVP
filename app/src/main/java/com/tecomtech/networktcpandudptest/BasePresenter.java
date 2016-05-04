package com.tecomtech.networktcpandudptest;

import com.tecomtech.networktcpandudptest.network.BaseNetwork;

/**
 * Created by welly on 2016/4/18.
 */
public interface BasePresenter {

    public void setNetworkProxy(BaseNetwork mBaseNetwork);
    public void startTcpClient(String server, int port);
    public void startTcpClient();
    public void stopTcpClent();
    public void startNetwork(String ip, int port);
    public void startNetwork();
    public void startHeartProcess();
    public void stopHeartProcess();

    public void refreshHeartCount(int count);
    public void refreshReceivedMsg(String msg);

    public int sendMsg(String msg);
}
