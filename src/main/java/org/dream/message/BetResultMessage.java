package org.dream.message;

import java.io.*;


/**
 * 某个台的当前局结果
 */
public class BetResultMessage extends UserMessage
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
	 * 胜出投注点，可能有多个
	 */
	protected String[] m_strWinOptions;
	public String[] getWinOptions()
	{
		return m_strWinOptions;
	}
	public void setWinOptions(String[] winOptions)
	{
		m_strWinOptions = winOptions;
	}

	/**
	 * 本局输赢，玩家，有可能为负
	 */
	protected Double m_dWinAmount;
	public Double getWinAmount()
	{
		return m_dWinAmount;
	}
	public void setWinAmount(Double winAmount)
	{
		m_dWinAmount = winAmount;
	}

	/**
	 * 当天第一次赢   -0，没有-1。有 
	 */
	protected Byte m_iFirstbetwin;
	public Byte getFirstbetwin()
	{
		return m_iFirstbetwin;
	}
	public void setFirstbetwin(Byte firstbetwin)
	{
		m_iFirstbetwin = firstbetwin;
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

	public BetResultMessage()
	{
		this.m_iType = BET_RESULT_MESSAGE;
	}

	public BetResultMessage(long userid, long tableid, String[] winOptions, double winAmount, byte firstbetwin, String cardresult)
	{
		this.m_iType = BET_RESULT_MESSAGE;
		m_lUserid = userid;
		m_lTableid = tableid;
		m_strWinOptions = winOptions;
		m_dWinAmount = winAmount;
		m_iFirstbetwin = firstbetwin;
		m_strCardresult = cardresult;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		int iArrLength;
		m_lUserid = readLong(bytes);

		m_lTableid = readLong(bytes);

		iArrLength = readInt(bytes);
		m_strWinOptions = iArrLength == 0 ? null : new String[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			iLength = readInt(bytes);
			m_strWinOptions[i] = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		}
		m_dWinAmount = readDouble(bytes);

		m_iFirstbetwin = readByte(bytes);

		iLength = readInt(bytes);
		m_strCardresult = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[][] winOptionsBytes = m_strWinOptions != null ? new byte[m_strWinOptions.length][] : null;
		iAllLength += INT_SIZE;
		for (int i = 0; m_strWinOptions != null && i < m_strWinOptions.length; i++)
		{
			winOptionsBytes[i] = m_strWinOptions[i] == null ? null : m_strWinOptions[i].getBytes("utf-8");
			iAllLength += INT_SIZE + (winOptionsBytes[i] == null ? 0 : winOptionsBytes[i].length);

		}
		iAllLength += DOUBLE_SIZE;

		iAllLength += BYTE_SIZE;

		byte[] cardresultBytes = null;
		cardresultBytes = m_strCardresult == null ? null : m_strCardresult.getBytes("utf-8");
		iAllLength += INT_SIZE + (cardresultBytes == null ? 0 : cardresultBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, m_strWinOptions != null ? m_strWinOptions.length : 0);
		for (int i = 0; m_strWinOptions != null && i < m_strWinOptions.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, winOptionsBytes[i] == null ? 0 : winOptionsBytes[i].length);
			if (winOptionsBytes[i] != null)
			{
				iPos = bytesToBytes(retBytes, iPos, winOptionsBytes[i], winOptionsBytes[i].length);
			}

		}
		iPos = doubleToBytes(retBytes, iPos, m_dWinAmount);

		iPos = byteToBytes(retBytes, iPos, m_iFirstbetwin);

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
		return "BetResultMessage<>";
	}

	public String toString()
	{
		return "BetResultMessage<" + "userid:" + m_lUserid + ", " + "tableid:" + m_lTableid + ", " + "winOptions:" + m_strWinOptions + ", " + "winAmount:" + m_dWinAmount + ", " + "firstbetwin:" + m_iFirstbetwin + ", " + "cardresult:" + m_strCardresult + ">";
	}
}