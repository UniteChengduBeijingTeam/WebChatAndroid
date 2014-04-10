package com.unitedeveloper.webchat.activity;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.unitedeveloper.webchat.R;
import com.unitedeveloper.webchat.base.WebChatBaseActivity;
import com.unitedeveloper.webchat.view.customview.WebChatNavigationBar.NavigationBarItemType;

public class MainActivity extends WebChatBaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		super.setNavigationBarTitle("Main");
		super.setNavigationBarHidden(false);
		super.setTabHostBarHidden(true);
		super.setContentView(R.layout.activity_main_layout);
	}

	@Override
	public void onNavigationBarItemClicked(NavigationBarItemType itemType) {
		if(itemType == NavigationBarItemType.NavigationBarLeftItem){
			self.finish();
		}else if(itemType == NavigationBarItemType.NavigationBarRightItem){
			self.startActivity(new Intent(self, MainActivity.class));
		}
	}
	
	private static int PORT=5469;
	public boolean sendLoginInfo(String u){
		boolean b=false;
		System.setProperty("java.net.preferIPv6Addresses", "false");//2.2会有ipv6的问题，
		SocketConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(300000);
		DefaultIoFilterChainBuilder chain=connector.getFilterChain();
		chain.addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		SocketSessionConfig cfg = connector.getSessionConfig();
		cfg.setUseReadOperation(true); 
		IoSession session = connector.connect(new InetSocketAddress("10.0.2.2", PORT))  
	            .awaitUninterruptibly().getSession();
		connector.setHandler(new IoHandlerAdapter(){

			@Override
			public void exceptionCaught(IoSession session, Throwable cause)
					throws Exception {
				// TODO Auto-generated method stub
				super.exceptionCaught(session, cause);
			}

			@Override
			public void messageReceived(IoSession session, Object message)
					throws Exception {
				// TODO Auto-generated method stub
				super.messageReceived(session, message);
			}

			@Override
			public void messageSent(IoSession session, Object message)
					throws Exception {
				// TODO Auto-generated method stub
				super.messageSent(session, message);
			}

			@Override
			public void sessionClosed(IoSession session) throws Exception {
				// TODO Auto-generated method stub
				super.sessionClosed(session);
			}

			@Override
			public void sessionCreated(IoSession session) throws Exception {
				// TODO Auto-generated method stub
				super.sessionCreated(session);
			}

			@Override
			public void sessionIdle(IoSession session, IdleStatus status)
					throws Exception {
				// TODO Auto-generated method stub
				super.sessionIdle(session, status);
			}

			@Override
			public void sessionOpened(IoSession session) throws Exception {
				// TODO Auto-generated method stub
				super.sessionOpened(session);
			}
			
		});
		//发送并等待完成
		session.write(u).awaitUninterruptibly();
		//接收
		ReadFuture readFuture = session.read();
		//接收超时
		if (readFuture.awaitUninterruptibly(30000,TimeUnit.SECONDS)) {  
//            VQMessage message = (VQMessage) readFuture.getMessage();
//            //如果是登陆成功的信息
//            if(message.getType().equals(VQMessageType.SUCCESS)){
//            	b= true;
//            }
        } else {
        	b= false;
        } 
		//断开连接
		session.close(true);  
        session.getService().dispose(); 
        return b;
	}


}
