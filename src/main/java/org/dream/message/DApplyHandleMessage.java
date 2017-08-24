package org.dream.message;

import java.io.*;


/**
 *  主播端加时操作 
 */
public class DApplyHandleMessage extends TableMessage
{
	/**
	 *  加时  
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

	public DApplyHandleMessage()
	{
		this.m_iType = D_APPLY_HANDLE_MESSAGE;
	}

	public DApplyHandleMessage(long tableid, byte optype, String username)
	{
		this.m_iType = D_APPLY_HANDLE_MESSAGE;
		m_lTableid = tableid;
		m_iOptype = optype;
		m_strUsername = username;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lTableid = readLong(bytes);

		m_iOptype = readByte(bytes);

		iLength = readInt(bytes);
		m_strUsername = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += BYTE_SIZE;

		byte[] usernameBytes = null;
		usernameBytes = m_strUsername == null ? null : m_strUsername.getBytes("utf-8");
		iAllLength += INT_SIZE + (usernameBytes == null ? 0 : usernameBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = byteToBytes(retBytes, iPos, m_iOptype);

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
		return "DApplyHandleMessage<>";
	}

	public String toString()
	{
		return "DApplyHandleMessage<" + "tableid:" + m_lTableid + ", " + "optype:" + m_iOptype + ", " + "username:" + m_strUsername + ">";
	}
}