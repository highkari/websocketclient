package org.dream.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.*;
import org.dream.message.Message;

/**
 * Created by Administrator on 2017/8/21.
 */
public class WebSocketMessageHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        WebSocketFrame frame =  msg;
        if(frame instanceof BinaryWebSocketFrame)  {
            ByteBuf buf = msg.content();
            int type = buf.readInt();
            int length = buf.readInt(); //buf.skipByte(4);  type和length相当于BODY里的消息头，去掉后再去转换为message.
            Message oMessage = Message.newInstance(type);
            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);
            try {
                oMessage.byteToMessage(req);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            System.out.println("Received :"+oMessage);
        } else if (frame instanceof TextWebSocketFrame) {
            TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
            System.out.println("WebSocket Client received message: " + textFrame.text());
        } else if (frame instanceof PongWebSocketFrame) {
            System.out.println("WebSocket Client received pong");
        } else if (frame instanceof CloseWebSocketFrame) {
            System.out.println("WebSocket Client received closing");
            ctx.close();
        }
    }
}
