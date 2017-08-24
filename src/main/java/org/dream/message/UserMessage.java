package org.dream.message;

import java.io.*;


/**
 * 用户消息，为抽象父类
 */
public class UserMessage extends Message
{
	/**
	 * 用户主键
	 */
	protected Long m_lUserid;
	public Long getUserid()
	{
		return m_lUserid;
	}
	public void setUserid(Long userid)
	{
		m_lUserid = userid;
	}

	public UserMessage()
	{
		super(USER_MESSAGE);
	}

	public UserMessage(long userid)
	{
		super(USER_MESSAGE);
		m_lUserid = userid;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		m_lUserid = readLong(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "UserMessage<>";
	}

	public String toString()
	{
		return "UserMessage<" + "userid:" + m_lUserid + ">";
	}
}