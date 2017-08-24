package org.dream.message;


public class ModifyPWMessage extends TableMessage
{
	/**
	 *  modify密码 
	 */
	protected String m_strPassword;
	public String getPassword()
	{
		return m_strPassword;
	}
	public void setPassword(String password)
	{
		m_strPassword = password;
	}

	/**
	 *  状态码 
	 */
	protected String m_strStatus;
	public String getStatus()
	{
		return m_strStatus;
	}
	public void setStatus(String status)
	{
		m_strStatus = status;
	}

	/**
	 *  信息报文 
	 */
	protected String m_strMsg;
	public String getMsg()
	{
		return m_strMsg;
	}
	public void setMsg(String msg)
	{
		m_strMsg = msg;
	}

	public ModifyPWMessage()
	{
		this.m_iType = M_MODIFY_VERIFY_MESSAGE;
	}

	public ModifyPWMessage(long tableid, String password, String status, String msg)
	{
		this.m_iType = M_MODIFY_VERIFY_MESSAGE;
		m_lTableid = tableid;
		m_strPassword = password;
		m_strStatus = status;
		m_strMsg = msg;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lTableid = readLong(bytes);

		iLength = readInt(bytes);
		m_strPassword = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strStatus = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strMsg = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] passwordBytes = null;
		passwordBytes = m_strPassword == null ? null : m_strPassword.getBytes("utf-8");
		iAllLength += INT_SIZE + (passwordBytes == null ? 0 : passwordBytes.length);

		byte[] statusBytes = null;
		statusBytes = m_strStatus == null ? null : m_strStatus.getBytes("utf-8");
		iAllLength += INT_SIZE + (statusBytes == null ? 0 : statusBytes.length);

		byte[] msgBytes = null;
		msgBytes = m_strMsg == null ? null : m_strMsg.getBytes("utf-8");
		iAllLength += INT_SIZE + (msgBytes == null ? 0 : msgBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, passwordBytes == null ? 0 : passwordBytes.length);
		if (passwordBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, passwordBytes, passwordBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, statusBytes == null ? 0 : statusBytes.length);
		if (statusBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, statusBytes, statusBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, msgBytes == null ? 0 : msgBytes.length);
		if (msgBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, msgBytes, msgBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "ModifyPWMessage<>";
	}

	public String toString()
	{
		return "ModifyPWMessage<" + "tableid:" + m_lTableid + ", " + "password:" + m_strPassword + ", " + "status:" + m_strStatus + ", " + "msg:" + m_strMsg + ">";
	}
}