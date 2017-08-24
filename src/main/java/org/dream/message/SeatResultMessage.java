package org.dream.message;

import java.io.*;


/**
 * 某个台的某个座位上的牌面大小
 */
public class SeatResultMessage extends TableMessage
{
	protected Byte m_iSeatid;
	public Byte getSeatid()
	{
		return m_iSeatid;
	}
	public void setSeatid(Byte seatid)
	{
		m_iSeatid = seatid;
	}

	/**
	 * 牌花色大小字符串
	 */
	protected String m_strValue;
	public String getValue()
	{
		return m_strValue;
	}
	public void setValue(String value)
	{
		m_strValue = value;
	}

	public SeatResultMessage()
	{
		this.m_iType = SEAT_RESULT_MESSAGE;
	}

	public SeatResultMessage(long tableid, byte seatid, String value)
	{
		this.m_iType = SEAT_RESULT_MESSAGE;
		m_lTableid = tableid;
		m_iSeatid = seatid;
		m_strValue = value;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lTableid = readLong(bytes);

		m_iSeatid = readByte(bytes);

		iLength = readInt(bytes);
		m_strValue = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += BYTE_SIZE;

		byte[] valueBytes = null;
		valueBytes = m_strValue == null ? null : m_strValue.getBytes("utf-8");
		iAllLength += INT_SIZE + (valueBytes == null ? 0 : valueBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = byteToBytes(retBytes, iPos, m_iSeatid);

		iPos = intToBytes(retBytes, iPos, valueBytes == null ? 0 : valueBytes.length);
		if (valueBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, valueBytes, valueBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "SeatResultMessage<>";
	}

	public String toString()
	{
		return "SeatResultMessage<" + "tableid:" + m_lTableid + ", " + "seatid:" + m_iSeatid + ", " + "value:" + m_strValue + ">";
	}
}