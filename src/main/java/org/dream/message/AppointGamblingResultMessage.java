package org.dream.message;

import java.io.*;


/**
 * 返回指定局结果消息 
 */
public class AppointGamblingResultMessage extends TableMessage
{
	/**
	 * 靴号 
	 */
	protected Integer m_iShoenum;
	public Integer getShoenum()
	{
		return m_iShoenum;
	}
	public void setShoenum(Integer shoenum)
	{
		m_iShoenum = shoenum;
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
	 * 下注额&派彩额
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
	 * 牌结果 
	 */
	protected String m_strCardresult;
	public String getCardresult()
	{
		return m_strCardresult;
	}
	public void setCardresult(String cardresult)
	{
		m_strCardresult = cardresult;
	}

	public AppointGamblingResultMessage()
	{
		this.m_iType = APPOINT_GAMBLING_RESULT_MESSAGE;
	}

	public AppointGamblingResultMessage(long tableid, int shoenum, int gamblingnum, long userid, String option, double amount, String cardresult)
	{
		this.m_iType = APPOINT_GAMBLING_RESULT_MESSAGE;
		m_lTableid = tableid;
		m_iShoenum = shoenum;
		m_iGamblingnum = gamblingnum;
		m_lUserid = userid;
		m_strOption = option;
		m_dAmount = amount;
		m_strCardresult = cardresult;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lTableid = readLong(bytes);

		m_iShoenum = readInt(bytes);

		m_iGamblingnum = readInt(bytes);

		m_lUserid = readLong(bytes);

		iLength = readInt(bytes);
		m_strOption = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_dAmount = readDouble(bytes);

		iLength = readInt(bytes);
		m_strCardresult = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += LONG_SIZE;

		byte[] optionBytes = null;
		optionBytes = m_strOption == null ? null : m_strOption.getBytes("utf-8");
		iAllLength += INT_SIZE + (optionBytes == null ? 0 : optionBytes.length);

		iAllLength += DOUBLE_SIZE;

		byte[] cardresultBytes = null;
		cardresultBytes = m_strCardresult == null ? null : m_strCardresult.getBytes("utf-8");
		iAllLength += INT_SIZE + (cardresultBytes == null ? 0 : cardresultBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, m_iShoenum);

		iPos = intToBytes(retBytes, iPos, m_iGamblingnum);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = intToBytes(retBytes, iPos, optionBytes == null ? 0 : optionBytes.length);
		if (optionBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, optionBytes, optionBytes.length);
		}

		iPos = doubleToBytes(retBytes, iPos, m_dAmount);

		iPos = intToBytes(retBytes, iPos, cardresultBytes == null ? 0 : cardresultBytes.length);
		if (cardresultBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, cardresultBytes, cardresultBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "AppointGamblingResultMessage<>";
	}

	public String toString()
	{
		return "AppointGamblingResultMessage<" + "tableid:" + m_lTableid + ", " + "shoenum:" + m_iShoenum + ", " + "gamblingnum:" + m_iGamblingnum + ", " + "userid:" + m_lUserid + ", " + "option:" + m_strOption + ", " + "amount:" + m_dAmount + ", " + "cardresult:" + m_strCardresult + ">";
	}
}