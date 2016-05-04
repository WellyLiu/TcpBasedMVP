package com.tecomtech.networktcpandudptest.network;

import com.tecomtech.networktcpandudptest.BasePresenter;

/**
 * Created by welly on 2016/4/18.
 */
public interface BaseNetwork {

    public void setPresenter(BasePresenter presenter);
    public void init();
    public void init(String server, int port);
    public void stop();

    public void sendMsg(String msg);
    public void onReceived(String msg);
}
