package org.dream.message;

import java.io.*;


/**
 * 验证对战密码
 */
public class BattleVPMessage extends Message
{
	/**
	 * 返回状态：0成功
	 */
	protected Integer m_iSuccess;
	public Integer getSuccess()
	{
		return m_iSuccess;
	}
	public void setSuccess(Integer success)
	{
		m_iSuccess = success;
	}

	/**
	 * 对战用户名
	 */
	protected String m_strUserName;
	public String getUserName()
	{
		return m_strUserName;
	}
	public void setUserName(String userName)
	{
		m_strUserName = userName;
	}

	protected Double m_dUserCoin;
	public Double getUserCoin()
	{
		return m_dUserCoin;
	}
	public void setUserCoin(Double userCoin)
	{
		m_dUserCoin = userCoin;
	}

	/**
	 * 对战密码
	 */
	protected String m_strPwd;
	public String getPwd()
	{
		return m_strPwd;
	}
	public void setPwd(String pwd)
	{
		m_strPwd = pwd;
	}

	/**
	 * 对战sessionid
	 */
	protected String m_strSessionid;
	public String getSessionid()
	{
		return m_strSessionid;
	}
	public void setSessionid(String sessionid)
	{
		m_strSessionid = sessionid;
	}

	public BattleVPMessage()
	{
		super(BATTLE_VP_MESSAGE);
	}

	public BattleVPMessage(int success, String userName, double userCoin, String pwd, String sessionid)
	{
		super(BATTLE_VP_MESSAGE);
		m_iSuccess = success;
		m_strUserName = userName;
		m_dUserCoin = userCoin;
		m_strPwd = pwd;
		m_strSessionid = sessionid;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_iSuccess = readInt(bytes);

		iLength = readInt(bytes);
		m_strUserName = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_dUserCoin = readDouble(bytes);

		iLength = readInt(bytes);
		m_strPwd = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strSessionid = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += INT_SIZE;

		byte[] userNameBytes = null;
		userNameBytes = m_strUserName == null ? null : m_strUserName.getBytes("utf-8");
		iAllLength += INT_SIZE + (userNameBytes == null ? 0 : userNameBytes.length);

		iAllLength += DOUBLE_SIZE;

		byte[] pwdBytes = null;
		pwdBytes = m_strPwd == null ? null : m_strPwd.getBytes("utf-8");
		iAllLength += INT_SIZE + (pwdBytes == null ? 0 : pwdBytes.length);

		byte[] sessionidBytes = null;
		sessionidBytes = m_strSessionid == null ? null : m_strSessionid.getBytes("utf-8");
		iAllLength += INT_SIZE + (sessionidBytes == null ? 0 : sessionidBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = intToBytes(retBytes, iPos, m_iSuccess);

		iPos = intToBytes(retBytes, iPos, userNameBytes == null ? 0 : userNameBytes.length);
		if (userNameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, userNameBytes, userNameBytes.length);
		}

		iPos = doubleToBytes(retBytes, iPos, m_dUserCoin);

		iPos = intToBytes(retBytes, iPos, pwdBytes == null ? 0 : pwdBytes.length);
		if (pwdBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, pwdBytes, pwdBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, sessionidBytes == null ? 0 : sessionidBytes.length);
		if (sessionidBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, sessionidBytes, sessionidBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "BattleVPMessage<>";
	}

	public String toString()
	{
		return "BattleVPMessage<" + "success:" + m_iSuccess + ", " + "userName:" + m_strUserName + ", " + "userCoin:" + m_dUserCoin + ", " + "pwd:" + m_strPwd + ", " + "sessionid:" + m_strSessionid + ">";
	}
}