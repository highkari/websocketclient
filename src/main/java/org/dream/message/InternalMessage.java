package org.dream.message;

import java.io.*;


/**
 * 内部通信协议 
 */
public class InternalMessage extends Message
{
	/**
	 *  协议编号 
	 */
	protected Byte m_iMtype;
	public Byte getMtype()
	{
		return m_iMtype;
	}
	public void setMtype(Byte mtype)
	{
		m_iMtype = mtype;
	}

	/**
	 * JSON消息体 
	 */
	protected String m_strJsonStr;
	public String getJsonStr()
	{
		return m_strJsonStr;
	}
	public void setJsonStr(String jsonStr)
	{
		m_strJsonStr = jsonStr;
	}

	public InternalMessage()
	{
		super(INTERNAL_MESSAGE);
	}

	public InternalMessage(byte mtype, String jsonStr)
	{
		super(INTERNAL_MESSAGE);
		m_iMtype = mtype;
		m_strJsonStr = jsonStr;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_iMtype = readByte(bytes);

		iLength = readInt(bytes);
		m_strJsonStr = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += BYTE_SIZE;

		byte[] jsonStrBytes = null;
		jsonStrBytes = m_strJsonStr == null ? null : m_strJsonStr.getBytes("utf-8");
		iAllLength += INT_SIZE + (jsonStrBytes == null ? 0 : jsonStrBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = byteToBytes(retBytes, iPos, m_iMtype);

		iPos = intToBytes(retBytes, iPos, jsonStrBytes == null ? 0 : jsonStrBytes.length);
		if (jsonStrBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, jsonStrBytes, jsonStrBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "InternalMessage<>";
	}

	public String toString()
	{
		return "InternalMessage<" + "mtype:" + m_iMtype + ", " + "jsonStr:" + m_strJsonStr + ">";
	}
}