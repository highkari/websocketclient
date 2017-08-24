package org.dream.message;

import java.io.*;


/**
 *  游戏局状态 
 */
public class GamblingStateMessage extends TableMessage
{
	/**
	 *   0:结束 1:更正中 
	 */
	protected Integer m_iGamblingState;
	public Integer getGamblingState()
	{
		return m_iGamblingState;
	}
	public void setGamblingState(Integer gamblingState)
	{
		m_iGamblingState = gamblingState;
	}

	public GamblingStateMessage()
	{
		this.m_iType = D_GAMBLING_STATE_MESSAGE;
	}

	public GamblingStateMessage(long tableid, int gamblingState)
	{
		this.m_iType = D_GAMBLING_STATE_MESSAGE;
		m_lTableid = tableid;
		m_iGamblingState = gamblingState;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		m_lTableid = readLong(bytes);

		m_iGamblingState = readInt(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += INT_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, m_iGamblingState);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "GamblingStateMessage<>";
	}

	public String toString()
	{
		return "GamblingStateMessage<" + "tableid:" + m_lTableid + ", " + "gamblingState:" + m_iGamblingState + ">";
	}
}