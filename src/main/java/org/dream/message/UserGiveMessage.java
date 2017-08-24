package org.dream.message;

import java.io.*;


/**
 * 用户对主播打赏 
 */
public class UserGiveMessage extends TableMessage
{
	/**
	 *  用户id 
	 */
	protected Long m_lUserid;
	public Long getUserid()
	{
		return m_lUserid;
	}
	public void setUserid(Long userid)
	{
		m_lUserid = userid;
	}

	/**
	 * 用户昵称
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

	/**
	 * 主播昵称
	 */
	protected String m_strEmcee;
	public String getEmcee()
	{
		return m_strEmcee;
	}
	public void setEmcee(String emcee)
	{
		m_strEmcee = emcee;
	}

	/**
	 * 游戏类型 
	 */
	protected Integer m_iGameType;
	public Integer getGameType()
	{
		return m_iGameType;
	}
	public void setGameType(Integer gameType)
	{
		m_iGameType = gameType;
	}

	/**
	 *  靴数 
	 */
	protected Long m_lShoenum;
	public Long getShoenum()
	{
		return m_lShoenum;
	}
	public void setShoenum(Long shoenum)
	{
		m_lShoenum = shoenum;
	}

	/**
	 *  局数 
	 */
	protected Long m_lGamblingnum;
	public Long getGamblingnum()
	{
		return m_lGamblingnum;
	}
	public void setGamblingnum(Long gamblingnum)
	{
		m_lGamblingnum = gamblingnum;
	}

	/**
	 *  打赏金额 
	 */
	protected Double m_dGiveamount;
	public Double getGiveamount()
	{
		return m_dGiveamount;
	}
	public void setGiveamount(Double giveamount)
	{
		m_dGiveamount = giveamount;
	}

	public UserGiveMessage()
	{
		this.m_iType = USER_GIVE_MESSAGE;
	}

	public UserGiveMessage(long tableid, long userid, String userName, String emcee, int gameType, long shoenum, long gamblingnum, double giveamount)
	{
		this.m_iType = USER_GIVE_MESSAGE;
		m_lTableid = tableid;
		m_lUserid = userid;
		m_strUserName = userName;
		m_strEmcee = emcee;
		m_iGameType = gameType;
		m_lShoenum = shoenum;
		m_lGamblingnum = gamblingnum;
		m_dGiveamount = giveamount;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lTableid = readLong(bytes);

		m_lUserid = readLong(bytes);

		iLength = readInt(bytes);
		m_strUserName = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strEmcee = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iGameType = readInt(bytes);

		m_lShoenum = readLong(bytes);

		m_lGamblingnum = readLong(bytes);

		m_dGiveamount = readDouble(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] userNameBytes = null;
		userNameBytes = m_strUserName == null ? null : m_strUserName.getBytes("utf-8");
		iAllLength += INT_SIZE + (userNameBytes == null ? 0 : userNameBytes.length);

		byte[] emceeBytes = null;
		emceeBytes = m_strEmcee == null ? null : m_strEmcee.getBytes("utf-8");
		iAllLength += INT_SIZE + (emceeBytes == null ? 0 : emceeBytes.length);

		iAllLength += INT_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += DOUBLE_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = intToBytes(retBytes, iPos, userNameBytes == null ? 0 : userNameBytes.length);
		if (userNameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, userNameBytes, userNameBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, emceeBytes == null ? 0 : emceeBytes.length);
		if (emceeBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, emceeBytes, emceeBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, m_iGameType);

		iPos = longToBytes(retBytes, iPos, m_lShoenum);

		iPos = longToBytes(retBytes, iPos, m_lGamblingnum);

		iPos = doubleToBytes(retBytes, iPos, m_dGiveamount);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "UserGiveMessage<>";
	}

	public String toString()
	{
		return "UserGiveMessage<" + "tableid:" + m_lTableid + ", " + "userid:" + m_lUserid + ", " + "userName:" + m_strUserName + ", " + "emcee:" + m_strEmcee + ", " + "gameType:" + m_iGameType + ", " + "shoenum:" + m_lShoenum + ", " + "gamblingnum:" + m_lGamblingnum + ", " + "giveamount:" + m_dGiveamount + ">";
	}
}