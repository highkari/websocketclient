package org.dream.message;

import java.io.*;


/**
 * 用户在某个台的输赢对账信息
 */
public class ReconciliationMessage extends UserMessage
{
	protected Long m_lTableid;
	public Long getTableid()
	{
		return m_lTableid;
	}
	public void setTableid(Long tableid)
	{
		m_lTableid = tableid;
	}

	/**
	 * 账户余额
	 */
	protected Double m_dAccount;
	public Double getAccount()
	{
		return m_dAccount;
	}
	public void setAccount(Double account)
	{
		m_dAccount = account;
	}

	public ReconciliationMessage()
	{
		this.m_iType = RECONCILIATION_MESSAGE;
	}

	public ReconciliationMessage(long userid, long tableid, double account)
	{
		this.m_iType = RECONCILIATION_MESSAGE;
		m_lUserid = userid;
		m_lTableid = tableid;
		m_dAccount = account;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		m_lUserid = readLong(bytes);

		m_lTableid = readLong(bytes);

		m_dAccount = readDouble(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += DOUBLE_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = doubleToBytes(retBytes, iPos, m_dAccount);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "ReconciliationMessage<>";
	}

	public String toString()
	{
		return "ReconciliationMessage<" + "userid:" + m_lUserid + ", " + "tableid:" + m_lTableid + ", " + "account:" + m_dAccount + ">";
	}
}