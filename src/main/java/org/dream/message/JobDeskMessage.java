package org.dream.message;

import java.io.*;


/**
 *  包桌消息 
 */
public class JobDeskMessage extends TableMessage
{
	/**
	 *  包桌用户id 
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

	/**
	 *  包桌限额关键字 
	 */
	protected String m_strLimitKey;
	public String getLimitKey()
	{
		return m_strLimitKey;
	}
	public void setLimitKey(String limitKey)
	{
		m_strLimitKey = limitKey;
	}

	public JobDeskMessage()
	{
		this.m_iType = JOBDESK_MESSAGE;
	}

	public JobDeskMessage(long tableid, long userid, String limitKey)
	{
		this.m_iType = JOBDESK_MESSAGE;
		m_lTableid = tableid;
		m_lUserid = userid;
		m_strLimitKey = limitKey;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lTableid = readLong(bytes);

		m_lUserid = readLong(bytes);

		iLength = readInt(bytes);
		m_strLimitKey = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] limitKeyBytes = null;
		limitKeyBytes = m_strLimitKey == null ? null : m_strLimitKey.getBytes("utf-8");
		iAllLength += INT_SIZE + (limitKeyBytes == null ? 0 : limitKeyBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = intToBytes(retBytes, iPos, limitKeyBytes == null ? 0 : limitKeyBytes.length);
		if (limitKeyBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, limitKeyBytes, limitKeyBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "JobDeskMessage<>";
	}

	public String toString()
	{
		return "JobDeskMessage<" + "tableid:" + m_lTableid + ", " + "userid:" + m_lUserid + ", " + "limitKey:" + m_strLimitKey + ">";
	}
}