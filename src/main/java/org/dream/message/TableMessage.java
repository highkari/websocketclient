package org.dream.message;

import java.io.*;


/**
 * 台消息，所有与台相关消息的父类
 */
public class TableMessage extends Message
{
	/**
	 * 台主键
	 */
	protected Long m_lTableid;
	public Long getTableid()
	{
		return m_lTableid;
	}
	public void setTableid(Long tableid)
	{
		m_lTableid = tableid;
	}

	public TableMessage()
	{
		super(TABLE_MESSAGE);
	}

	public TableMessage(long tableid)
	{
		super(TABLE_MESSAGE);
		m_lTableid = tableid;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		m_lTableid = readLong(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "TableMessage<>";
	}

	public String toString()
	{
		return "TableMessage<" + "tableid:" + m_lTableid + ">";
	}
}