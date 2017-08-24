package org.dream.message;

import java.io.*;


/**
 *  type=42 _ 某个台即时消息（包括在座人数，等）
 */
public class TableInstantMessage extends TableMessage
{
	/**
	 * 在座人数 
	 */
	protected Integer m_iSeatNum;
	public Integer getSeatNum()
	{
		return m_iSeatNum;
	}
	public void setSeatNum(Integer seatNum)
	{
		m_iSeatNum = seatNum;
	}

	/**
	 * 座位号字符集 
	 */
	protected String m_strSeats;
	public String getSeats()
	{
		return m_strSeats;
	}
	public void setSeats(String seats)
	{
		m_strSeats = seats;
	}

	/**
	 * 真实在座座位号集合 
	 */
	protected String m_strRealseats;
	public String getRealseats()
	{
		return m_strRealseats;
	}
	public void setRealseats(String realseats)
	{
		m_strRealseats = realseats;
	}

	/**
	 * 桌上总筹码 
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

	public TableInstantMessage()
	{
		this.m_iType = TABLE_INSTANT_MESSAGE;
	}

	public TableInstantMessage(long tableid, int seatNum, String seats, String realseats, double amount)
	{
		this.m_iType = TABLE_INSTANT_MESSAGE;
		m_lTableid = tableid;
		m_iSeatNum = seatNum;
		m_strSeats = seats;
		m_strRealseats = realseats;
		m_dAmount = amount;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lTableid = readLong(bytes);

		m_iSeatNum = readInt(bytes);

		iLength = readInt(bytes);
		m_strSeats = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strRealseats = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_dAmount = readDouble(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += INT_SIZE;

		byte[] seatsBytes = null;
		seatsBytes = m_strSeats == null ? null : m_strSeats.getBytes("utf-8");
		iAllLength += INT_SIZE + (seatsBytes == null ? 0 : seatsBytes.length);

		byte[] realseatsBytes = null;
		realseatsBytes = m_strRealseats == null ? null : m_strRealseats.getBytes("utf-8");
		iAllLength += INT_SIZE + (realseatsBytes == null ? 0 : realseatsBytes.length);

		iAllLength += DOUBLE_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, m_iSeatNum);

		iPos = intToBytes(retBytes, iPos, seatsBytes == null ? 0 : seatsBytes.length);
		if (seatsBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, seatsBytes, seatsBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, realseatsBytes == null ? 0 : realseatsBytes.length);
		if (realseatsBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, realseatsBytes, realseatsBytes.length);
		}

		iPos = doubleToBytes(retBytes, iPos, m_dAmount);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "TableInstantMessage<>";
	}

	public String toString()
	{
		return "TableInstantMessage<" + "tableid:" + m_lTableid + ", " + "seatNum:" + m_iSeatNum + ", " + "seats:" + m_strSeats + ", " + "realseats:" + m_strRealseats + ", " + "amount:" + m_dAmount + ">";
	}
}