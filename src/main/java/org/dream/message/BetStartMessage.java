package org.dream.message;

import java.io.*;


/**
 * 某个台开始接受投注
 */
public class BetStartMessage extends TableMessage
{
	/**
	 * 倒计时时间，单位毫秒
	 */
	protected Integer m_iCountdown;
	public Integer getCountdown()
	{
		return m_iCountdown;
	}
	public void setCountdown(Integer countdown)
	{
		m_iCountdown = countdown;
	}

	/**
	 * 0:投注，1:加注，2:咪牌
	 */
	protected Integer m_iBettype;
	public Integer getBettype()
	{
		return m_iBettype;
	}
	public void setBettype(Integer bettype)
	{
		m_iBettype = bettype;
	}

	/**
	 * 靴序号
	 */
	protected Integer m_iShoeSeq;
	public Integer getShoeSeq()
	{
		return m_iShoeSeq;
	}
	public void setShoeSeq(Integer shoeSeq)
	{
		m_iShoeSeq = shoeSeq;
	}

	/**
	 * 局序号
	 */
	protected Integer m_iGameSeq;
	public Integer getGameSeq()
	{
		return m_iGameSeq;
	}
	public void setGameSeq(Integer gameSeq)
	{
		m_iGameSeq = gameSeq;
	}

	public BetStartMessage()
	{
		this.m_iType = BET_START_MESSAGE;
	}

	public BetStartMessage(long tableid, int countdown, int bettype, int shoeSeq, int gameSeq)
	{
		this.m_iType = BET_START_MESSAGE;
		m_lTableid = tableid;
		m_iCountdown = countdown;
		m_iBettype = bettype;
		m_iShoeSeq = shoeSeq;
		m_iGameSeq = gameSeq;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		m_lTableid = readLong(bytes);

		m_iCountdown = readInt(bytes);

		m_iBettype = readInt(bytes);

		m_iShoeSeq = readInt(bytes);

		m_iGameSeq = readInt(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, m_iCountdown);

		iPos = intToBytes(retBytes, iPos, m_iBettype);

		iPos = intToBytes(retBytes, iPos, m_iShoeSeq);

		iPos = intToBytes(retBytes, iPos, m_iGameSeq);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "BetStartMessage<>";
	}

	public String toString()
	{
		return "BetStartMessage<" + "tableid:" + m_lTableid + ", " + "countdown:" + m_iCountdown + ", " + "bettype:" + m_iBettype + ", " + "shoeSeq:" + m_iShoeSeq + ", " + "gameSeq:" + m_iGameSeq + ">";
	}
}