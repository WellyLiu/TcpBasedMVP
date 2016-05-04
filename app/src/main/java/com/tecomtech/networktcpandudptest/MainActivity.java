package com.tecomtech.networktcpandudptest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tecomtech.networktcpandudptest.network.BaseNetwork;
import com.tecomtech.networktcpandudptest.network.Network;

public class MainActivity extends AppCompatActivity implements BaseView{

    private final static int RECIVED_MSG = 1000;

    private EditText serverIP, serverPort;
    private TextView mHeartCount, mReceived;
    private Button send, mConncet, mDisconnect;
    private EditText sendMsg;

    BasePresenter mPresenter;

    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        mPresenter = new Presenter(this);

        initHandler();
    }

    private void initHandler() {

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch(msg.what)
                {
                    case RECIVED_MSG:
                        mReceived.setText((String)msg.obj);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initUI() {
        mHeartCount = (TextView)this.findViewById(R.id.heart_count);
        mReceived = (TextView)this.findViewById(R.id.received_msg);
        send = (Button)this.findViewById(R.id.send_button);;
        serverIP = (EditText)this.findViewById(R.id.server_ip);
        serverPort = (EditText)this.findViewById(R.id.server_port);

        sendMsg = (EditText)this.findViewById(R.id.send_msg_edit);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = sendMsg.getEditableText().toString();
                mPresenter.sendMsg(msg);
            }
        });

        mConncet = (Button)this.findViewById(R.id.connect_button);
        mConncet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = serverIP.getEditableText().toString();
                String tmp = serverPort.getEditableText().toString();
                if(TextUtils.isEmpty(ip) || TextUtils.isEmpty(tmp))
                {
                    mPresenter.startNetwork();
                }else
                {
                    int port = Integer.parseInt(tmp);
                    Log.d("tst", "Connect to server ip:" + ip + "  port:" + port);
                    mPresenter.startNetwork(ip, port);
                }
                       }
        });

        mDisconnect = (Button)this.findViewById(R.id.disconnect_button);
        mDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.stopTcpClent();
            }
        });
    }


    @Override
    public void updateHeartCount(int count) {
        mHeartCount.setText(String.valueOf(count));
    }

    @Override
    public void updateReceivedMsg(String str) {
        Log.d("tst", "5====" + str);
        Message msg = mHandler.obtainMessage();
        msg.what = RECIVED_MSG;
        msg.obj = str;
        mHandler.sendMessage(msg);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.stopTcpClent();
    }
}
