package org.dream.message;

import java.io.*;


/**
 * 用户对某个台进行抢庄
 */
public class CompBankerMessage extends UserMessage
{
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
	 * 投注金额
	 */
	protected Double m_dAmount;
	public Double getAmount()
	{
		return m_dAmount;
	}
	public void setAmount(Double amount)
	{
		m_dAmount = amount;
	}

	/**
	 * 胜出者id
	 */
	protected Long m_lWinuserid;
	public Long getWinuserid()
	{
		return m_lWinuserid;
	}
	public void setWinuserid(Long winuserid)
	{
		m_lWinuserid = winuserid;
	}

	/**
	 * 胜出者昵称
	 */
	protected String m_strWinnikename;
	public String getWinnikename()
	{
		return m_strWinnikename;
	}
	public void setWinnikename(String winnikename)
	{
		m_strWinnikename = winnikename;
	}

	/**
	 * 胜出者投注金额
	 */
	protected Double m_dWinamount;
	public Double getWinamount()
	{
		return m_dWinamount;
	}
	public void setWinamount(Double winamount)
	{
		m_dWinamount = winamount;
	}

	public CompBankerMessage()
	{
		this.m_iType = COMPBANKER_MESSAGE;
	}

	public CompBankerMessage(long userid, long tableid, double amount, long winuserid, String winnikename, double winamount)
	{
		this.m_iType = COMPBANKER_MESSAGE;
		m_lUserid = userid;
		m_lTableid = tableid;
		m_dAmount = amount;
		m_lWinuserid = winuserid;
		m_strWinnikename = winnikename;
		m_dWinamount = winamount;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lUserid = readLong(bytes);

		m_lTableid = readLong(bytes);

		m_dAmount = readDouble(bytes);

		m_lWinuserid = readLong(bytes);

		iLength = readInt(bytes);
		m_strWinnikename = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_dWinamount = readDouble(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += DOUBLE_SIZE;

		iAllLength += LONG_SIZE;

		byte[] winnikenameBytes = null;
		winnikenameBytes = m_strWinnikename == null ? null : m_strWinnikename.getBytes("utf-8");
		iAllLength += INT_SIZE + (winnikenameBytes == null ? 0 : winnikenameBytes.length);

		iAllLength += DOUBLE_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = doubleToBytes(retBytes, iPos, m_dAmount);

		iPos = longToBytes(retBytes, iPos, m_lWinuserid);

		iPos = intToBytes(retBytes, iPos, winnikenameBytes == null ? 0 : winnikenameBytes.length);
		if (winnikenameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, winnikenameBytes, winnikenameBytes.length);
		}

		iPos = doubleToBytes(retBytes, iPos, m_dWinamount);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "CompBankerMessage<>";
	}

	public String toString()
	{
		return "CompBankerMessage<" + "userid:" + m_lUserid + ", " + "tableid:" + m_lTableid + ", " + "amount:" + m_dAmount + ", " + "winuserid:" + m_lWinuserid + ", " + "winnikename:" + m_strWinnikename + ", " + "winamount:" + m_dWinamount + ">";
	}
}