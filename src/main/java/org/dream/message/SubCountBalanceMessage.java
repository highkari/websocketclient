package org.dream.message;

import java.io.*;


/**
 * 子账户余额信息
 */
public class SubCountBalanceMessage extends UserMessage
{
	protected String m_strMark;
	public String getMark()
	{
		return m_strMark;
	}
	public void setMark(String mark)
	{
		m_strMark = mark;
	}

	/**
	 * 子账户余额
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

	public SubCountBalanceMessage()
	{
		this.m_iType = SUBCOUNTBALANCE_MESSAGE;
	}

	public SubCountBalanceMessage(long userid, String mark, double account)
	{
		this.m_iType = SUBCOUNTBALANCE_MESSAGE;
		m_lUserid = userid;
		m_strMark = mark;
		m_dAccount = account;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lUserid = readLong(bytes);

		iLength = readInt(bytes);
		m_strMark = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_dAccount = readDouble(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] markBytes = null;
		markBytes = m_strMark == null ? null : m_strMark.getBytes("utf-8");
		iAllLength += INT_SIZE + (markBytes == null ? 0 : markBytes.length);

		iAllLength += DOUBLE_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = intToBytes(retBytes, iPos, markBytes == null ? 0 : markBytes.length);
		if (markBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, markBytes, markBytes.length);
		}

		iPos = doubleToBytes(retBytes, iPos, m_dAccount);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "SubCountBalanceMessage<>";
	}

	public String toString()
	{
		return "SubCountBalanceMessage<" + "userid:" + m_lUserid + ", " + "mark:" + m_strMark + ", " + "account:" + m_dAccount + ">";
	}
}