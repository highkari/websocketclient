package org.dream.message;

import java.io.*;


/**
 * 在线用户数消息
 */
public class OnlineMessage extends Message
{
	protected Integer m_iNum;
	public Integer getNum()
	{
		return m_iNum;
	}
	public void setNum(Integer num)
	{
		m_iNum = num;
	}

	public OnlineMessage()
	{
		super(ONLINE_MESSAGE);
	}

	public OnlineMessage(int num)
	{
		super(ONLINE_MESSAGE);
		m_iNum = num;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		m_iNum = readInt(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += INT_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = intToBytes(retBytes, iPos, m_iNum);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "OnlineMessage<>";
	}

	public String toString()
	{
		return "OnlineMessage<" + "num:" + m_iNum + ">";
	}
}