package org.dream.message;

import java.io.*;


/**
 * 选台操作，由用户触发
 */
public class ChooseTableMessage extends UserMessage
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

	/**
	 * 以多大台面限红进入游戏
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

	/**
	 * 进入方式，单人0，多人1,旁观2
	 */
	protected Integer m_iEntertype;
	public Integer getEntertype()
	{
		return m_iEntertype;
	}
	public void setEntertype(Integer entertype)
	{
		m_iEntertype = entertype;
	}

	/**
	 * 座位号
	 */
	protected Byte m_iSeatno;
	public Byte getSeatno()
	{
		return m_iSeatno;
	}
	public void setSeatno(Byte seatno)
	{
		m_iSeatno = seatno;
	}

	public ChooseTableMessage()
	{
		this.m_iType = CHOOSE_TABLE_MESSAGE;
	}

	public ChooseTableMessage(long userid, long tableid, String limitKey, int entertype, byte seatno)
	{
		this.m_iType = CHOOSE_TABLE_MESSAGE;
		m_lUserid = userid;
		m_lTableid = tableid;
		m_strLimitKey = limitKey;
		m_iEntertype = entertype;
		m_iSeatno = seatno;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lUserid = readLong(bytes);

		m_lTableid = readLong(bytes);

		iLength = readInt(bytes);
		m_strLimitKey = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iEntertype = readInt(bytes);

		m_iSeatno = readByte(bytes);

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

		iAllLength += INT_SIZE;

		iAllLength += BYTE_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, limitKeyBytes == null ? 0 : limitKeyBytes.length);
		if (limitKeyBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, limitKeyBytes, limitKeyBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, m_iEntertype);

		iPos = byteToBytes(retBytes, iPos, m_iSeatno);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "ChooseTableMessage<>";
	}

	public String toString()
	{
		return "ChooseTableMessage<" + "userid:" + m_lUserid + ", " + "tableid:" + m_lTableid + ", " + "limitKey:" + m_strLimitKey + ", " + "entertype:" + m_iEntertype + ", " + "seatno:" + m_iSeatno + ">";
	}
}