package org.dream.message;

import java.io.*;


/**
 *  修改昵称消息 
 */
public class ModifyNickNameMessage extends UserMessage
{
	/**
	 * 昵称
	 */
	protected String m_strNickname;
	public String getNickname()
	{
		return m_strNickname;
	}
	public void setNickname(String nickname)
	{
		m_strNickname = nickname;
	}

	public ModifyNickNameMessage()
	{
		this.m_iType = MODIFY_NICKNAME_MESSAGE;
	}

	public ModifyNickNameMessage(long userid, String nickname)
	{
		this.m_iType = MODIFY_NICKNAME_MESSAGE;
		m_lUserid = userid;
		m_strNickname = nickname;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lUserid = readLong(bytes);

		iLength = readInt(bytes);
		m_strNickname = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] nicknameBytes = null;
		nicknameBytes = m_strNickname == null ? null : m_strNickname.getBytes("utf-8");
		iAllLength += INT_SIZE + (nicknameBytes == null ? 0 : nicknameBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = intToBytes(retBytes, iPos, nicknameBytes == null ? 0 : nicknameBytes.length);
		if (nicknameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, nicknameBytes, nicknameBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "ModifyNickNameMessage<>";
	}

	public String toString()
	{
		return "ModifyNickNameMessage<" + "userid:" + m_lUserid + ", " + "nickname:" + m_strNickname + ">";
	}
}