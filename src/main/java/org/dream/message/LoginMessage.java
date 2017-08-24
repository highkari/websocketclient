package org.dream.message;

import java.io.*;


/**
 * 用户登录消息
 */
public class LoginMessage extends Message
{
	protected String m_strName;
	public String getName()
	{
		return m_strName;
	}
	public void setName(String name)
	{
		m_strName = name;
	}

	/**
	 * 接口验证码
	 */
	protected String m_strCode;
	public String getCode()
	{
		return m_strCode;
	}
	public void setCode(String code)
	{
		m_strCode = code;
	}

	public LoginMessage()
	{
		super(LOGIN_MESSAGE);
	}

	public LoginMessage(String name, String code)
	{
		super(LOGIN_MESSAGE);
		m_strName = name;
		m_strCode = code;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		iLength = readInt(bytes);
		m_strName = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strCode = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		byte[] nameBytes = null;
		nameBytes = m_strName == null ? null : m_strName.getBytes("utf-8");
		iAllLength += INT_SIZE + (nameBytes == null ? 0 : nameBytes.length);

		byte[] codeBytes = null;
		codeBytes = m_strCode == null ? null : m_strCode.getBytes("utf-8");
		iAllLength += INT_SIZE + (codeBytes == null ? 0 : codeBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = intToBytes(retBytes, iPos, nameBytes == null ? 0 : nameBytes.length);
		if (nameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, nameBytes, nameBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, codeBytes == null ? 0 : codeBytes.length);
		if (codeBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, codeBytes, codeBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "LoginMessage<>";
	}

	public String toString()
	{
		return "LoginMessage<" + "name:" + m_strName + ", " + "code:" + m_strCode + ">";
	}
}