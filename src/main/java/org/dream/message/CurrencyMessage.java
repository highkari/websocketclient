package org.dream.message;

import java.io.*;


/**
 *   
 */
public class CurrencyMessage extends UserMessage
{
	protected Long m_lSysCurrencyId;
	public Long getSysCurrencyId()
	{
		return m_lSysCurrencyId;
	}
	public void setSysCurrencyId(Long sysCurrencyId)
	{
		m_lSysCurrencyId = sysCurrencyId;
	}

	protected Long m_lUserCurrencyId;
	public Long getUserCurrencyId()
	{
		return m_lUserCurrencyId;
	}
	public void setUserCurrencyId(Long userCurrencyId)
	{
		m_lUserCurrencyId = userCurrencyId;
	}

	protected String m_strCurrencyList;
	public String getCurrencyList()
	{
		return m_strCurrencyList;
	}
	public void setCurrencyList(String currencyList)
	{
		m_strCurrencyList = currencyList;
	}

	public CurrencyMessage()
	{
		this.m_iType = CURRENCY_MESSAGE;
	}

	public CurrencyMessage(long userid, long sysCurrencyId, long userCurrencyId, String currencyList)
	{
		this.m_iType = CURRENCY_MESSAGE;
		m_lUserid = userid;
		m_lSysCurrencyId = sysCurrencyId;
		m_lUserCurrencyId = userCurrencyId;
		m_strCurrencyList = currencyList;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lUserid = readLong(bytes);

		m_lSysCurrencyId = readLong(bytes);

		m_lUserCurrencyId = readLong(bytes);

		iLength = readInt(bytes);
		m_strCurrencyList = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] currencyListBytes = null;
		currencyListBytes = m_strCurrencyList == null ? null : m_strCurrencyList.getBytes("utf-8");
		iAllLength += INT_SIZE + (currencyListBytes == null ? 0 : currencyListBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = longToBytes(retBytes, iPos, m_lSysCurrencyId);

		iPos = longToBytes(retBytes, iPos, m_lUserCurrencyId);

		iPos = intToBytes(retBytes, iPos, currencyListBytes == null ? 0 : currencyListBytes.length);
		if (currencyListBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, currencyListBytes, currencyListBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "CurrencyMessage<>";
	}

	public String toString()
	{
		return "CurrencyMessage<" + "userid:" + m_lUserid + ", " + "sysCurrencyId:" + m_lSysCurrencyId + ", " + "userCurrencyId:" + m_lUserCurrencyId + ", " + "currencyList:" + m_strCurrencyList + ">";
	}
}