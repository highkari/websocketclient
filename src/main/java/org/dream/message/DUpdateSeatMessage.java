package org.dream.message;

import java.io.*;

public class DUpdateSeatMessage extends TableMessage
{
	/**
	 * 座位号（格式为：座位ID1,座位ID2...）
	 */
	protected String m_strSeatids;
	public String getSeatids()
	{
		return m_strSeatids;
	}
	public void setSeatids(String seatids)
	{
		m_strSeatids = seatids;
	}

	/**
	 * 显示玩家名称（格式为：玩家名称1,玩家名称2...）
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
	 * 显示玩家余额（格式为：玩家余额1,玩家余额2...）
	 */
	protected String m_strBalances;
	public String getBalances()
	{
		return m_strBalances;
	}
	public void setBalances(String balances)
	{
		m_strBalances = balances;
	}

	/**
	 * 用户对应币种（格式为：玩家的币种1,玩家的币种2...）
	 */
	protected String m_strCurrencyids;
	public String getCurrencyids()
	{
		return m_strCurrencyids;
	}
	public void setCurrencyids(String currencyids)
	{
		m_strCurrencyids = currencyids;
	}

	public DUpdateSeatMessage()
	{
		this.m_iType = D_UPDATE_SEAT_MESSAGE;
	}

	public DUpdateSeatMessage(long tableid, String seatids, String showname, String balances, String currencyids)
	{
		this.m_iType = D_UPDATE_SEAT_MESSAGE;
		m_lTableid = tableid;
		m_strSeatids = seatids;
		m_strShowname = showname;
		m_strBalances = balances;
		m_strCurrencyids = currencyids;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lTableid = readLong(bytes);

		iLength = readInt(bytes);
		m_strSeatids = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strShowname = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strBalances = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strCurrencyids = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] seatidsBytes = null;
		seatidsBytes = m_strSeatids == null ? null : m_strSeatids.getBytes("utf-8");
		iAllLength += INT_SIZE + (seatidsBytes == null ? 0 : seatidsBytes.length);

		byte[] shownameBytes = null;
		shownameBytes = m_strShowname == null ? null : m_strShowname.getBytes("utf-8");
		iAllLength += INT_SIZE + (shownameBytes == null ? 0 : shownameBytes.length);

		byte[] balancesBytes = null;
		balancesBytes = m_strBalances == null ? null : m_strBalances.getBytes("utf-8");
		iAllLength += INT_SIZE + (balancesBytes == null ? 0 : balancesBytes.length);

		byte[] currencyidsBytes = null;
		currencyidsBytes = m_strCurrencyids == null ? null : m_strCurrencyids.getBytes("utf-8");
		iAllLength += INT_SIZE + (currencyidsBytes == null ? 0 : currencyidsBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, seatidsBytes == null ? 0 : seatidsBytes.length);
		if (seatidsBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, seatidsBytes, seatidsBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, shownameBytes == null ? 0 : shownameBytes.length);
		if (shownameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, shownameBytes, shownameBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, balancesBytes == null ? 0 : balancesBytes.length);
		if (balancesBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, balancesBytes, balancesBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, currencyidsBytes == null ? 0 : currencyidsBytes.length);
		if (currencyidsBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, currencyidsBytes, currencyidsBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "DUpdateSeatMessage<>";
	}

	public String toString()
	{
		return "DUpdateSeatMessage<" + "tableid:" + m_lTableid + ", " + "seatids:" + m_strSeatids + ", " + "showname:" + m_strShowname + ", " + "balances:" + m_strBalances + ", " + "currencyids:" + m_strCurrencyids + ">";
	}
}