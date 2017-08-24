package org.dream.message;

import java.io.*;


/**
 * 某个台的投注点禁用信息
 */
public class BetPointMessage extends TableMessage
{
	/**
	 * 投注点
	 */
	protected String m_strPointid;
	public String getPointid()
	{
		return m_strPointid;
	}
	public void setPointid(String pointid)
	{
		m_strPointid = pointid;
	}

	/**
	 * 是否开放
	 */
	protected Boolean m_bOpen;
	public Boolean getOpen()
	{
		return m_bOpen;
	}
	public void setOpen(Boolean open)
	{
		m_bOpen = open;
	}

	public BetPointMessage()
	{
		this.m_iType = BET_POINT_MESSAGE;
	}

	public BetPointMessage(long tableid, String pointid, boolean open)
	{
		this.m_iType = BET_POINT_MESSAGE;
		m_lTableid = tableid;
		m_strPointid = pointid;
		m_bOpen = open;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lTableid = readLong(bytes);

		iLength = readInt(bytes);
		m_strPointid = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_bOpen = (readByte(bytes) != 0);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] pointidBytes = null;
		pointidBytes = m_strPointid == null ? null : m_strPointid.getBytes("utf-8");
		iAllLength += INT_SIZE + (pointidBytes == null ? 0 : pointidBytes.length);

		iAllLength += BYTE_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, pointidBytes == null ? 0 : pointidBytes.length);
		if (pointidBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, pointidBytes, pointidBytes.length);
		}

		iPos = byteToBytes(retBytes, iPos, m_bOpen ? 1 : 0);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "BetPointMessage<>";
	}

	public String toString()
	{
		return "BetPointMessage<" + "tableid:" + m_lTableid + ", " + "pointid:" + m_strPointid + ", " + "open:" + m_bOpen + ">";
	}
}