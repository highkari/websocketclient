package org.dream.message;

import java.io.*;


/**
 * 大厅消息 
 */
public class LobbyMessage extends Message
{
	/**
	 * 大厅类型：0-首页、1-现场游戏、2-星级百家乐、3-对战游戏等 
	 */
	protected Integer m_iLobbyType;
	public Integer getLobbyType()
	{
		return m_iLobbyType;
	}
	public void setLobbyType(Integer lobbyType)
	{
		m_iLobbyType = lobbyType;
	}

	/**
	 * 大厅主键 
	 */
	protected Long m_lLobbyid;
	public Long getLobbyid()
	{
		return m_lLobbyid;
	}
	public void setLobbyid(Long lobbyid)
	{
		m_lLobbyid = lobbyid;
	}

	/**
	 * 大厅公告 
	 */
	protected String[] m_strNotice;
	public String[] getNotice()
	{
		return m_strNotice;
	}
	public void setNotice(String[] notice)
	{
		m_strNotice = notice;
	}

	/**
	 * 游戏列表 bacc:1:opname:icon#bacc:2:opname2:icon2# 
	 */
	protected String[] m_strGameList;
	public String[] getGameList()
	{
		return m_strGameList;
	}
	public void setGameList(String[] gameList)
	{
		m_strGameList = gameList;
	}

	/**
	 * 视频地址 
	 */
	protected String m_strVideoline;
	public String getVideoline()
	{
		return m_strVideoline;
	}
	public void setVideoline(String videoline)
	{
		m_strVideoline = videoline;
	}

	public LobbyMessage()
	{
		super(LOBBY_MESSAGE);
	}

	public LobbyMessage(int lobbyType, long lobbyid, String[] notice, String[] gameList, String videoline)
	{
		super(LOBBY_MESSAGE);
		m_iLobbyType = lobbyType;
		m_lLobbyid = lobbyid;
		m_strNotice = notice;
		m_strGameList = gameList;
		m_strVideoline = videoline;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		int iArrLength;
		m_iLobbyType = readInt(bytes);

		m_lLobbyid = readLong(bytes);

		iArrLength = readInt(bytes);
		m_strNotice = iArrLength == 0 ? null : new String[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			iLength = readInt(bytes);
			m_strNotice[i] = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		}
		iArrLength = readInt(bytes);
		m_strGameList = iArrLength == 0 ? null : new String[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			iLength = readInt(bytes);
			m_strGameList[i] = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		}
		iLength = readInt(bytes);
		m_strVideoline = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += LONG_SIZE;

		byte[][] noticeBytes = m_strNotice != null ? new byte[m_strNotice.length][] : null;
		iAllLength += INT_SIZE;
		for (int i = 0; m_strNotice != null && i < m_strNotice.length; i++)
		{
			noticeBytes[i] = m_strNotice[i] == null ? null : m_strNotice[i].getBytes("utf-8");
			iAllLength += INT_SIZE + (noticeBytes[i] == null ? 0 : noticeBytes[i].length);

		}
		byte[][] gameListBytes = m_strGameList != null ? new byte[m_strGameList.length][] : null;
		iAllLength += INT_SIZE;
		for (int i = 0; m_strGameList != null && i < m_strGameList.length; i++)
		{
			gameListBytes[i] = m_strGameList[i] == null ? null : m_strGameList[i].getBytes("utf-8");
			iAllLength += INT_SIZE + (gameListBytes[i] == null ? 0 : gameListBytes[i].length);

		}
		byte[] videolineBytes = null;
		videolineBytes = m_strVideoline == null ? null : m_strVideoline.getBytes("utf-8");
		iAllLength += INT_SIZE + (videolineBytes == null ? 0 : videolineBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = intToBytes(retBytes, iPos, m_iLobbyType);

		iPos = longToBytes(retBytes, iPos, m_lLobbyid);

		iPos = intToBytes(retBytes, iPos, m_strNotice != null ? m_strNotice.length : 0);
		for (int i = 0; m_strNotice != null && i < m_strNotice.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, noticeBytes[i] == null ? 0 : noticeBytes[i].length);
			if (noticeBytes[i] != null)
			{
				iPos = bytesToBytes(retBytes, iPos, noticeBytes[i], noticeBytes[i].length);
			}

		}
		iPos = intToBytes(retBytes, iPos, m_strGameList != null ? m_strGameList.length : 0);
		for (int i = 0; m_strGameList != null && i < m_strGameList.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, gameListBytes[i] == null ? 0 : gameListBytes[i].length);
			if (gameListBytes[i] != null)
			{
				iPos = bytesToBytes(retBytes, iPos, gameListBytes[i], gameListBytes[i].length);
			}

		}
		iPos = intToBytes(retBytes, iPos, videolineBytes == null ? 0 : videolineBytes.length);
		if (videolineBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, videolineBytes, videolineBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "LobbyMessage<>";
	}

	public String toString()
	{
		return "LobbyMessage<" + "lobbyType:" + m_iLobbyType + ", " + "lobbyid:" + m_lLobbyid + ", " + "notice:" + m_strNotice + ", " + "gameList:" + m_strGameList + ", " + "videoline:" + m_strVideoline + ">";
	}
}