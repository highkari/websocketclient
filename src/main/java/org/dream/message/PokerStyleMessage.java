package org.dream.message;

import java.io.*;


/**
 *  荷官选牌背风格 
 */
public class PokerStyleMessage extends TableMessage
{
	/**
	 * 0读取， 1：保存  
	 */
	protected Integer m_iOptype;
	public Integer getOptype()
	{
		return m_iOptype;
	}
	public void setOptype(Integer optype)
	{
		m_iOptype = optype;
	}

	/**
	 *  牌背风格 0:蓝色 1:红色 
	 */
	protected Integer m_iPokerStyle;
	public Integer getPokerStyle()
	{
		return m_iPokerStyle;
	}
	public void setPokerStyle(Integer pokerStyle)
	{
		m_iPokerStyle = pokerStyle;
	}

	/**
	 *  改牌背时靴号 
	 */
	protected Integer m_iShoeNum;
	public Integer getShoeNum()
	{
		return m_iShoeNum;
	}
	public void setShoeNum(Integer shoeNum)
	{
		m_iShoeNum = shoeNum;
	}

	/**
	 *  改牌背时局号 
	 */
	protected Integer m_iGamblingNum;
	public Integer getGamblingNum()
	{
		return m_iGamblingNum;
	}
	public void setGamblingNum(Integer gamblingNum)
	{
		m_iGamblingNum = gamblingNum;
	}

	public PokerStyleMessage()
	{
		this.m_iType = POKER_STYLE_MESSAGE;
	}

	public PokerStyleMessage(long tableid, int optype, int pokerStyle, int shoeNum, int gamblingNum)
	{
		this.m_iType = POKER_STYLE_MESSAGE;
		m_lTableid = tableid;
		m_iOptype = optype;
		m_iPokerStyle = pokerStyle;
		m_iShoeNum = shoeNum;
		m_iGamblingNum = gamblingNum;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		m_lTableid = readLong(bytes);

		m_iOptype = readInt(bytes);

		m_iPokerStyle = readInt(bytes);

		m_iShoeNum = readInt(bytes);

		m_iGamblingNum = readInt(bytes);

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

		iPos = intToBytes(retBytes, iPos, m_iOptype);

		iPos = intToBytes(retBytes, iPos, m_iPokerStyle);

		iPos = intToBytes(retBytes, iPos, m_iShoeNum);

		iPos = intToBytes(retBytes, iPos, m_iGamblingNum);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "PokerStyleMessage<>";
	}

	public String toString()
	{
		return "PokerStyleMessage<" + "tableid:" + m_lTableid + ", " + "optype:" + m_iOptype + ", " + "pokerStyle:" + m_iPokerStyle + ", " + "shoeNum:" + m_iShoeNum + ", " + "gamblingNum:" + m_iGamblingNum + ">";
	}
}