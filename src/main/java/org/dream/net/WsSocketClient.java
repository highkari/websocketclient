package org.dream.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.string.StringEncoder;
import org.dream.handler.OutMessageHandler;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/21.
 */
public class WsSocketClient {

    private Channel ch;

    public void connect(String host, int port) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.TCP_NODELAY, true);
            //b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {

                  /* ch.pipeline().addLast(new WebSocketMessageHandler());*/
                    //ch.pipeline().addLast(new StringEncoder());
                    ch.pipeline().addLast(new OutMessageHandler());

                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();
            ch = f.channel();
            //Thread.sleep(2000L);
            TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString());
            send(tws);
            ch.flush().close();
           // send("12344545645555555555555555555555555555555555545643756756765755555555555555555555555");

            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }

    public void send(Object msg) {
        ch.writeAndFlush(msg);
    }
    public static void main(String[] args) throws Exception {
        WsSocketClient client = new WsSocketClient();
        client.connect("192.168.3.173", 8082);

    }
}
