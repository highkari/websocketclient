package org.dream.message;

import java.io.*;


/**
 * 进入大厅消息
 */
public class EnterLobbyMessage extends Message
{
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

	public EnterLobbyMessage()
	{
		super(ENTER_LOBBY_MESSAGE);
	}

	public EnterLobbyMessage(long lobbyid)
	{
		super(ENTER_LOBBY_MESSAGE);
		m_lLobbyid = lobbyid;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		m_lLobbyid = readLong(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lLobbyid);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "EnterLobbyMessage<>";
	}

	public String toString()
	{
		return "EnterLobbyMessage<" + "lobbyid:" + m_lLobbyid + ">";
	}
}