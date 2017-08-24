package org.dream.message;

import java.io.*;


/**
 * 用户对某个台进行投注
 */
public class BetMessage extends UserMessage
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
	 * 投注点，可以是多个
	 */
	protected String[] m_strOption;
	public String[] getOption()
	{
		return m_strOption;
	}
	public void setOption(String[] option)
	{
		m_strOption = option;
	}

	/**
	 * 投注金额，可以是多个
	 */
	protected Double[] m_dAmount;
	public Double[] getAmount()
	{
		return m_dAmount;
	}
	public void setAmount(Double[] amount)
	{
		m_dAmount = amount;
	}

	/**
	 * 是否免佣投注0:否,1:是
	 */
	protected Integer m_iSbanker;
	public Integer getSbanker()
	{
		return m_iSbanker;
	}
	public void setSbanker(Integer sbanker)
	{
		m_iSbanker = sbanker;
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

	public BetMessage()
	{
		this.m_iType = BET_MESSAGE;
	}

	public BetMessage(long userid, long tableid, String[] option, Double[] amount, int sbanker , int gamblingnum)
	{
		this.m_iType = BET_MESSAGE;
		m_lUserid = userid;
		m_lTableid = tableid;
		m_strOption = option;
		m_dAmount = amount;
		m_iSbanker = sbanker;
		m_iGamblingnum = gamblingnum;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		int iArrLength;
		m_lUserid = readLong(bytes);

		m_lTableid = readLong(bytes);

		iArrLength = readInt(bytes);
		m_strOption = iArrLength == 0 ? null : new String[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			iLength = readInt(bytes);
			m_strOption[i] = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		}
		iArrLength = readInt(bytes);
		m_dAmount = iArrLength == 0 ? null : new Double[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			m_dAmount[i] = readDouble(bytes);

		}
		m_iSbanker = readInt(bytes);
		
		m_iGamblingnum = readInt(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[][] optionBytes = m_strOption != null ? new byte[m_strOption.length][] : null;
		iAllLength += INT_SIZE;
		for (int i = 0; m_strOption != null && i < m_strOption.length; i++)
		{
			optionBytes[i] = m_strOption[i] == null ? null : m_strOption[i].getBytes("utf-8");
			iAllLength += INT_SIZE + (optionBytes[i] == null ? 0 : optionBytes[i].length);

		}
		iAllLength += INT_SIZE;
		for (int i = 0; m_dAmount != null && i < m_dAmount.length; i++)
		{
			iAllLength += DOUBLE_SIZE;

		}
		iAllLength += INT_SIZE;
		
		iAllLength += INT_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, m_strOption != null ? m_strOption.length : 0);
		for (int i = 0; m_strOption != null && i < m_strOption.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, optionBytes[i] == null ? 0 : optionBytes[i].length);
			if (optionBytes[i] != null)
			{
				iPos = bytesToBytes(retBytes, iPos, optionBytes[i], optionBytes[i].length);
			}

		}
		iPos = intToBytes(retBytes, iPos, m_dAmount != null ? m_dAmount.length : 0);
		for (int i = 0; m_dAmount != null && i < m_dAmount.length; i++)
		{
			iPos = doubleToBytes(retBytes, iPos, m_dAmount[i]);

		}
		iPos = intToBytes(retBytes, iPos, m_iSbanker);
		
		iPos = intToBytes(retBytes, iPos, m_iGamblingnum);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "BetMessage<>";
	}

	public String toString()
	{
		return "BetMessage<" + "userid:" + m_lUserid + ", " + "tableid:" + m_lTableid + ", " + "option:" + java.util.Arrays.toString(m_strOption)  + ", " + "amount:" + java.util.Arrays.toString(m_dAmount) + ", " + "sbanker:" + m_iSbanker + ", m_iGamblingnum:" + m_iGamblingnum + ">";
	}
}