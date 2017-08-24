package org.dream.message;

import java.io.*;


/**
 *  客户端桌主操作 
 */
public class MasterOpMessage extends TableMessage
{
	/**
	 *  1、换靴，2、换荷官，3、飞牌，4、倒计时结束，5、单个开牌，6、开庄家牌，7、开闲家牌，8、开庄闲牌，9、咪牌结束 
	 */
	protected Byte m_iOptype;
	public Byte getOptype()
	{
		return m_iOptype;
	}
	public void setOptype(Byte optype)
	{
		m_iOptype = optype;
	}

	/**
	 *  开牌时的变量，发牌顺序
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
	 *  结果 
	 */
	protected Integer m_iResult;
	public Integer getResult()
	{
		return m_iResult;
	}
	public void setResult(Integer result)
	{
		m_iResult = result;
	}

	public MasterOpMessage()
	{
		this.m_iType = MASTER_OP_MESSAGE;
	}

	public MasterOpMessage(long tableid, byte optype, int order, int result)
	{
		this.m_iType = MASTER_OP_MESSAGE;
		m_lTableid = tableid;
		m_iOptype = optype;
		m_iOrder = order;
		m_iResult = result;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		m_lTableid = readLong(bytes);

		m_iOptype = readByte(bytes);

		m_iOrder = readInt(bytes);

		m_iResult = readInt(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += BYTE_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = byteToBytes(retBytes, iPos, m_iOptype);

		iPos = intToBytes(retBytes, iPos, m_iOrder);

		iPos = intToBytes(retBytes, iPos, m_iResult);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "MasterOpMessage<>";
	}

	public String toString()
	{
		return "MasterOpMessage<" + "tableid:" + m_lTableid + ", " + "optype:" + m_iOptype + ", " + "order:" + m_iOrder + ", " + "result:" + m_iResult + ">";
	}
}