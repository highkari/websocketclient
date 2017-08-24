package org.dream.message;

import java.io.*;


/**
 * 某个台的荷官给某个座位发了一张牌
 */
public class DSeatDealMessage extends TableMessage
{
	/**
	 * 座位ID，前后端约定的，比如百家乐，闲-0，庄-1
	 */
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
	 * 花色：1-黑桃，2-红桃，3-梅花，4-方块
	 */
	protected Byte m_iColor;
	public Byte getColor()
	{
		return m_iColor;
	}
	public void setColor(Byte color)
	{
		m_iColor = color;
	}

	/**
	 * 点数：A、2、3、4……
	 */
	protected String m_strPoint;
	public String getPoint()
	{
		return m_strPoint;
	}
	public void setPoint(String point)
	{
		m_strPoint = point;
	}

	/**
	 * 发牌的顺序
	 */
	protected Integer m_iOrder;
	public Integer getOrder()
	{
		return m_iOrder;
	}
	public void setOrder(Integer order)
	{
		m_iOrder = order;
	}

	/**
	 * 翻牌状态（vip）0翻开，1未翻开
	 */
	protected Byte m_iState;
	public Byte getState()
	{
		return m_iState;
	}
	public void setState(Byte state)
	{
		m_iState = state;
	}

	public DSeatDealMessage()
	{
		this.m_iType = D_SEAT_DEAL_MESSAGE;
	}

	public DSeatDealMessage(long tableid, byte seatid, byte color, String point, int order, byte state)
	{
		this.m_iType = D_SEAT_DEAL_MESSAGE;
		m_lTableid = tableid;
		m_iSeatid = seatid;
		m_iColor = color;
		m_strPoint = point;
		m_iOrder = order;
		m_iState = state;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lTableid = readLong(bytes);

		m_iSeatid = readByte(bytes);

		m_iColor = readByte(bytes);

		iLength = readInt(bytes);
		m_strPoint = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iOrder = readInt(bytes);

		m_iState = readByte(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += BYTE_SIZE;

		iAllLength += BYTE_SIZE;

		byte[] pointBytes = null;
		pointBytes = m_strPoint == null ? null : m_strPoint.getBytes("utf-8");
		iAllLength += INT_SIZE + (pointBytes == null ? 0 : pointBytes.length);

		iAllLength += INT_SIZE;

		iAllLength += BYTE_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = byteToBytes(retBytes, iPos, m_iSeatid);

		iPos = byteToBytes(retBytes, iPos, m_iColor);

		iPos = intToBytes(retBytes, iPos, pointBytes == null ? 0 : pointBytes.length);
		if (pointBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, pointBytes, pointBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, m_iOrder);

		iPos = byteToBytes(retBytes, iPos, m_iState);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "DSeatDealMessage<>";
	}

	public String toString()
	{
		return "DSeatDealMessage<" + "tableid:" + m_lTableid + ", " + "seatid:" + m_iSeatid + ", " + "color:" + m_iColor + ", " + "point:" + m_strPoint + ", " + "order:" + m_iOrder + ", " + "state:" + m_iState + ">";
	}
}