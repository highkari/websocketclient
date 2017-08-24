package org.dream.message;

import java.io.*;


/**
 * 用户对某个台进行投注的系统回应
 */
public class BetResponseMessage extends BetMessage
{
	protected Integer m_iResult;
	public Integer getResult()
	{
		return m_iResult;
	}
	public void setResult(Integer result)
	{
		m_iResult = result;
	}

	public BetResponseMessage()
	{
		this.m_iType = BET_RESPONSE_MESSAGE;
	}

	public BetResponseMessage(long userid, long tableid, String[] option, Double[] amount, int result)
	{
		this.m_iType = BET_RESPONSE_MESSAGE;
		m_lUserid = userid;
		m_lTableid = tableid;
		m_strOption = option;
		m_dAmount = amount;
		m_iResult = result;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		int iArrLength;
		m_lUserid = readLong(bytes);

		m_lTableid = readLong(bytes);

		iArrLength = readInt(bytes);
		m_strOption = iArrLength == 0 ? null : new String[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			iLength = readInt(bytes);
			m_strOption[i] = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		}
		iArrLength = readInt(bytes);
		m_dAmount = iArrLength == 0 ? null : new Double[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			m_dAmount[i] = readDouble(bytes);

		}
		m_iResult = readInt(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[][] optionBytes = m_strOption != null ? new byte[m_strOption.length][] : null;
		iAllLength += INT_SIZE;
		for (int i = 0; m_strOption != null && i < m_strOption.length; i++)
		{
			optionBytes[i] = m_strOption[i] == null ? null : m_strOption[i].getBytes("utf-8");
			iAllLength += INT_SIZE + (optionBytes[i] == null ? 0 : optionBytes[i].length);

		}
		iAllLength += INT_SIZE;
		for (int i = 0; m_dAmount != null && i < m_dAmount.length; i++)
		{
			iAllLength += DOUBLE_SIZE;

		}
		iAllLength += INT_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, m_strOption != null ? m_strOption.length : 0);
		for (int i = 0; m_strOption != null && i < m_strOption.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, optionBytes[i] == null ? 0 : optionBytes[i].length);
			if (optionBytes[i] != null)
			{
				iPos = bytesToBytes(retBytes, iPos, optionBytes[i], optionBytes[i].length);
			}

		}
		iPos = intToBytes(retBytes, iPos, m_dAmount != null ? m_dAmount.length : 0);
		for (int i = 0; m_dAmount != null && i < m_dAmount.length; i++)
		{
			iPos = doubleToBytes(retBytes, iPos, m_dAmount[i]);

		}
		iPos = intToBytes(retBytes, iPos, m_iResult);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "BetResponseMessage<>";
	}

	public String toString()
	{
		return "BetResponseMessage<" + "userid:" + m_lUserid + ", " + "tableid:" + m_lTableid + ", " + "option:" + java.util.Arrays.toString(m_strOption) + ", " + "amount:" + java.util.Arrays.toString(m_dAmount) + ", " + "result:" + m_iResult + ">";
	}
}