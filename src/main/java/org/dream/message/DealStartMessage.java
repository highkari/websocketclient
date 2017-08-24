package org.dream.message;

import java.io.*;


/**
 * 某个台开始派牌
 */
public class DealStartMessage extends TableMessage
{
	public DealStartMessage()
	{
		this.m_iType = DEAL_START_MESSAGE;
	}

	public DealStartMessage(long tableid)
	{
		this.m_iType = DEAL_START_MESSAGE;
		m_lTableid = tableid;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		m_lTableid = readLong(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "DealStartMessage<>";
	}

	public String toString()
	{
		return "DealStartMessage<" + "tableid:" + m_lTableid + ">";
	}
}