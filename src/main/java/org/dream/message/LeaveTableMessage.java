package org.dream.message;

import java.io.*;


/**
 * 离开某个台
 */
public class LeaveTableMessage extends TableMessage
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

	public LeaveTableMessage()
	{
		this.m_iType = LEAVE_TABLE_MESSAGE;
	}

	public LeaveTableMessage(long tableid, long userid)
	{
		this.m_iType = LEAVE_TABLE_MESSAGE;
		m_lTableid = tableid;
		m_lUserid = userid;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		m_lTableid = readLong(bytes);

		m_lUserid = readLong(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "LeaveTableMessage<>";
	}

	public String toString()
	{
		return "LeaveTableMessage<" + "tableid:" + m_lTableid + ", " + "userid:" + m_lUserid + ">";
	}
}