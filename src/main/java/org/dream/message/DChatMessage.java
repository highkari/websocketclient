package org.dream.message;

import java.io.*;


/**
 *  dealer展示聊天消息 
 */
public class DChatMessage extends UserMessage
{
	/**
	 * 台主键
	 */
	protected Long m_lTableid;
	public Long getTableid()
	{
		return m_lTableid;
	}
	public void setTableid(Long tableid)
	{
		m_lTableid = tableid;
	}

	/**
	 * 显示名称
	 */
	protected String m_strShowname;
	public String getShowname()
	{
		return m_strShowname;
	}
	public void setShowname(String showname)
	{
		m_strShowname = showname;
	}

	/**
	 * 消息内容
	 */
	protected String m_strContent;
	public String getContent()
	{
		return m_strContent;
	}
	public void setContent(String content)
	{
		m_strContent = content;
	}

	/**
	 * 座位号
	 */
	protected Integer m_iSeatid;
	public Integer getSeatid()
	{
		return m_iSeatid;
	}
	public void setSeatid(Integer seatid)
	{
		m_iSeatid = seatid;
	}

	public DChatMessage()
	{
		this.m_iType = D_CHAT_MESSAGE;
	}

	public DChatMessage(long userid, long tableid, String showname, String content, int seatid)
	{
		this.m_iType = D_CHAT_MESSAGE;
		m_lUserid = userid;
		m_lTableid = tableid;
		m_strShowname = showname;
		m_strContent = content;
		m_iSeatid = seatid;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lUserid = readLong(bytes);

		m_lTableid = readLong(bytes);

		iLength = readInt(bytes);
		m_strShowname = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strContent = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iSeatid = readInt(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] shownameBytes = null;
		shownameBytes = m_strShowname == null ? null : m_strShowname.getBytes("utf-8");
		iAllLength += INT_SIZE + (shownameBytes == null ? 0 : shownameBytes.length);

		byte[] contentBytes = null;
		contentBytes = m_strContent == null ? null : m_strContent.getBytes("utf-8");
		iAllLength += INT_SIZE + (contentBytes == null ? 0 : contentBytes.length);

		iAllLength += INT_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, shownameBytes == null ? 0 : shownameBytes.length);
		if (shownameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, shownameBytes, shownameBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, contentBytes == null ? 0 : contentBytes.length);
		if (contentBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, contentBytes, contentBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, m_iSeatid);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "DChatMessage<>";
	}

	public String toString()
	{
		return "DChatMessage<" + "userid:" + m_lUserid + ", " + "tableid:" + m_lTableid + ", " + "showname:" + m_strShowname + ", " + "content:" + m_strContent + ", " + "seatid:" + m_iSeatid + ">";
	}
}