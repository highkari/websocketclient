package org.dream.message;

import java.io.*;


/**
 * dealer端操作 
 */
public class DealerOpMessageForOpcard extends DealerOpMessage
{
	/**
	 * 申请的用户名，多个用逗号分开如a,b 
	 */
	protected String m_strUsername;
	public String getUsername()
	{
		return m_strUsername;
	}
	public void setUsername(String username)
	{
		m_strUsername = username;
	}

	public DealerOpMessageForOpcard()
	{
		this.m_iType = DEALER_OP_MESSAGE_OPCARD;
	}

	public DealerOpMessageForOpcard(long tableid, byte optype, byte order, byte card, long shoenum, long gamblingnum, String username)
	{
		this.m_iType = DEALER_OP_MESSAGE_OPCARD;
		m_lTableid = tableid;
		m_iOptype = optype;
		m_iOrder = order;
		m_iCard = card;
		m_lShoenum = shoenum;
		m_lGamblingnum = gamblingnum;
		m_strUsername = username;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lTableid = readLong(bytes);

		m_iOptype = readByte(bytes);

		m_iOrder = readByte(bytes);

		m_iCard = readByte(bytes);

		m_lShoenum = readLong(bytes);

		m_lGamblingnum = readLong(bytes);

		iLength = readInt(bytes);
		m_strUsername = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += BYTE_SIZE;

		iAllLength += BYTE_SIZE;

		iAllLength += BYTE_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] usernameBytes = null;
		usernameBytes = m_strUsername == null ? null : m_strUsername.getBytes("utf-8");
		iAllLength += INT_SIZE + (usernameBytes == null ? 0 : usernameBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = byteToBytes(retBytes, iPos, m_iOptype);

		iPos = byteToBytes(retBytes, iPos, m_iOrder);

		iPos = byteToBytes(retBytes, iPos, m_iCard);

		iPos = longToBytes(retBytes, iPos, m_lShoenum);

		iPos = longToBytes(retBytes, iPos, m_lGamblingnum);

		iPos = intToBytes(retBytes, iPos, usernameBytes == null ? 0 : usernameBytes.length);
		if (usernameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, usernameBytes, usernameBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "DealerOpMessageForOpcard<>";
	}

	public String toString()
	{
		return "DealerOpMessageForOpcard<" + "tableid:" + m_lTableid + ", " + "optype:" + m_iOptype + ", " + "order:" + m_iOrder + ", " + "card:" + m_iCard + ", " + "shoenum:" + m_lShoenum + ", " + "gamblingnum:" + m_lGamblingnum + ", " + "username:" + m_strUsername + ">";
	}
}