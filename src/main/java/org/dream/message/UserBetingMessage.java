package org.dream.message;

import java.io.*;


/**
 * 用户准备进行投注, 未确定筹码
 */
public class UserBetingMessage extends UserMessage
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

	protected Long m_lSeatid;
	public Long getSeatid()
	{
		return m_lSeatid;
	}
	public void setSeatid(Long seatid)
	{
		m_lSeatid = seatid;
	}

	/**
	 * 投注点
	 */
	protected String m_strOption;
	public String getOption()
	{
		return m_strOption;
	}
	public void setOption(String option)
	{
		m_strOption = option;
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
	 * 局号
	 */
	protected Integer m_iGamblingnum;
	
	public Integer getGamblingnum()
	{
		return m_iGamblingnum;
	}
	public void setGamblingnum(Integer gamblingnum)
	{
		m_iGamblingnum = gamblingnum;
	}

	public UserBetingMessage()
	{
		this.m_iType = BET_ING_MESSAGE;
	}

	public UserBetingMessage(long userid, long tableid, long seatid, String option, double amount , int gamblingnum)
	{
		this.m_iType = BET_ING_MESSAGE;
		m_lUserid = userid;
		m_lTableid = tableid;
		m_lSeatid = seatid;
		m_strOption = option;
		m_dAmount = amount;
		m_iGamblingnum = gamblingnum;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lUserid = readLong(bytes);

		m_lTableid = readLong(bytes);

		m_lSeatid = readLong(bytes);

		iLength = readInt(bytes);
		m_strOption = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_dAmount = readDouble(bytes);
		
		m_iGamblingnum = readInt(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] optionBytes = null;
		optionBytes = m_strOption == null ? null : m_strOption.getBytes("utf-8");
		iAllLength += INT_SIZE + (optionBytes == null ? 0 : optionBytes.length);

		iAllLength += DOUBLE_SIZE;
		
		iAllLength += INT_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = longToBytes(retBytes, iPos, m_lSeatid);

		iPos = intToBytes(retBytes, iPos, optionBytes == null ? 0 : optionBytes.length);
		if (optionBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, optionBytes, optionBytes.length);
		}

		iPos = doubleToBytes(retBytes, iPos, m_dAmount);
		
		iPos = intToBytes(retBytes, iPos, m_iGamblingnum);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "UserBetingMessage<>";
	}

	public String toString()
	{
		return "UserBetingMessage<" + "userid:" + m_lUserid + ", " + "tableid:" + m_lTableid + ", " + "seatid:" + m_lSeatid + ", " + "option:" + m_strOption + ", " + "amount:" + m_dAmount + ">";
	}
}