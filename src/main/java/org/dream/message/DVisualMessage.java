package org.dream.message;

import java.io.*;


/**
 *   
 */
public class DVisualMessage extends UserMessage
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
	 * 座位号（-10000 为离开，旁观为-1，其他从0开始）
	 */
	protected Integer m_iSeatid;
	public Integer getSeatid()
	{
		return m_iSeatid;
	}
	public void setSeatid(Integer seatid)
	{
		m_iSeatid = seatid;
	}

	/**
	 * 显示名称
	 */
	protected String m_strShowname;
	public String getShowname()
	{
		return m_strShowname;
	}
	public void setShowname(String showname)
	{
		m_strShowname = showname;
	}

	/**
	 * 当前余额
	 */
	protected Double m_dBalance;
	public Double getBalance()
	{
		return m_dBalance;
	}
	public void setBalance(Double balance)
	{
		m_dBalance = balance;
	}

	/**
	 * 准备下注状态（0未，1有）
	 */
	protected Integer m_iIsbet;
	public Integer getIsbet()
	{
		return m_iIsbet;
	}
	public void setIsbet(Integer isbet)
	{
		m_iIsbet = isbet;
	}

	/**
	 * 请求加时状态（0未，1有）
	 */
	protected Integer m_iTime;
	public Integer getTime()
	{
		return m_iTime;
	}
	public void setTime(Integer time)
	{
		m_iTime = time;
	}

	/**
	 * 请求开牌状态（0未，1有）
	 */
	protected Integer m_iOpen;
	public Integer getOpen()
	{
		return m_iOpen;
	}
	public void setOpen(Integer open)
	{
		m_iOpen = open;
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
	 *  加注状态（0未，1有）
	 */
	protected Integer m_iIsaddbet;
	public Integer getIsaddbet()
	{
		return m_iIsaddbet;
	}
	public void setIsaddbet(Integer isaddbet)
	{
		m_iIsaddbet = isaddbet;
	}

	/**
	 * 闲咪牌权座位号,庄咪牌权桌位号（默认-1,-1）
	 */
	protected String m_strMiseat;
	public String getMiseat()
	{
		return m_strMiseat;
	}
	public void setMiseat(String miseat)
	{
		m_strMiseat = miseat;
	}

	/**
	 * 用户对应币种
	 */
	protected Long m_lCurrencyid;
	public Long getCurrencyid()
	{
		return m_lCurrencyid;
	}
	public void setCurrencyid(Long currencyid)
	{
		m_lCurrencyid = currencyid;
	}

	public DVisualMessage()
	{
		this.m_iType = D_VISUAL_MESSAGE;
	}

	public DVisualMessage(long userid, long tableid, int seatid, String showname, double balance, int isbet, int time, int open, String[] option, Double[] amount, int isaddbet, String miseat, long currencyid)
	{
		this.m_iType = D_VISUAL_MESSAGE;
		m_lUserid = userid;
		m_lTableid = tableid;
		m_iSeatid = seatid;
		m_strShowname = showname;
		m_dBalance = balance;
		m_iIsbet = isbet;
		m_iTime = time;
		m_iOpen = open;
		m_strOption = option;
		m_dAmount = amount;
		m_iIsaddbet = isaddbet;
		m_strMiseat = miseat;
		m_lCurrencyid = currencyid;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		int iArrLength;
		m_lUserid = readLong(bytes);

		m_lTableid = readLong(bytes);

		m_iSeatid = readInt(bytes);

		iLength = readInt(bytes);
		m_strShowname = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_dBalance = readDouble(bytes);

		m_iIsbet = readInt(bytes);

		m_iTime = readInt(bytes);

		m_iOpen = readInt(bytes);

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
		m_iIsaddbet = readInt(bytes);

		iLength = readInt(bytes);
		m_strMiseat = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lCurrencyid = readLong(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += INT_SIZE;

		byte[] shownameBytes = null;
		shownameBytes = m_strShowname == null ? null : m_strShowname.getBytes("utf-8");
		iAllLength += INT_SIZE + (shownameBytes == null ? 0 : shownameBytes.length);

		iAllLength += DOUBLE_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

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

		byte[] miseatBytes = null;
		miseatBytes = m_strMiseat == null ? null : m_strMiseat.getBytes("utf-8");
		iAllLength += INT_SIZE + (miseatBytes == null ? 0 : miseatBytes.length);

		iAllLength += LONG_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, m_iSeatid);

		iPos = intToBytes(retBytes, iPos, shownameBytes == null ? 0 : shownameBytes.length);
		if (shownameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, shownameBytes, shownameBytes.length);
		}

		iPos = doubleToBytes(retBytes, iPos, m_dBalance);

		iPos = intToBytes(retBytes, iPos, m_iIsbet);

		iPos = intToBytes(retBytes, iPos, m_iTime);

		iPos = intToBytes(retBytes, iPos, m_iOpen);

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
		iPos = intToBytes(retBytes, iPos, m_iIsaddbet);

		iPos = intToBytes(retBytes, iPos, miseatBytes == null ? 0 : miseatBytes.length);
		if (miseatBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, miseatBytes, miseatBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lCurrencyid);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "DVisualMessage<>";
	}

	public String toString()
	{
		return "DVisualMessage<" + "userid:" + m_lUserid + ", " + "tableid:" + m_lTableid + ", " + "seatid:" + m_iSeatid + ", " + "showname:" + m_strShowname + ", " + "balance:" + m_dBalance + ", " + "isbet:" + m_iIsbet + ", " + "time:" + m_iTime + ", " + "open:" + m_iOpen + ", " + "option:" + m_strOption + ", " + "amount:" + m_dAmount + ", " + "isaddbet:" + m_iIsaddbet + ", " + "miseat:" + m_strMiseat + ", " + "currencyid:" + m_lCurrencyid + ">";
	}
}