package org.dream.message;

import java.io.*;


/**
 * 三公游戏选庄
 */
public class ChooseBankerMessage extends TableMessage
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

	/**
	 * 庄的位置
	 */
	protected String m_strSeatid;
	public String getSeatid()
	{
		return m_strSeatid;
	}
	public void setSeatid(String seatid)
	{
		m_strSeatid = seatid;
	}

	public ChooseBankerMessage()
	{
		this.m_iType = CHOOSE_BANKER_MESSAGE;
	}

	public ChooseBankerMessage(long tableid, long userid, String seatid)
	{
		this.m_iType = CHOOSE_BANKER_MESSAGE;
		m_lTableid = tableid;
		m_lUserid = userid;
		m_strSeatid = seatid;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lTableid = readLong(bytes);

		m_lUserid = readLong(bytes);

		iLength = readInt(bytes);
		m_strSeatid = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] seatidBytes = null;
		seatidBytes = m_strSeatid == null ? null : m_strSeatid.getBytes("utf-8");
		iAllLength += INT_SIZE + (seatidBytes == null ? 0 : seatidBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = intToBytes(retBytes, iPos, seatidBytes == null ? 0 : seatidBytes.length);
		if (seatidBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, seatidBytes, seatidBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "ChooseBankerMessage<>";
	}

	public String toString()
	{
		return "ChooseBankerMessage<" + "tableid:" + m_lTableid + ", " + "userid:" + m_lUserid + ", " + "seatid:" + m_strSeatid + ">";
	}
}