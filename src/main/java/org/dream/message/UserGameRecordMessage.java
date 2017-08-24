package org.dream.message;

import java.io.*;


/**
 * 登录结果消息
 */
public class UserGameRecordMessage extends UserMessage
{
	/**
	 * 日期
	 */
	protected String m_strDate;
	public String getDate()
	{
		return m_strDate;
	}
	public void setDate(String date)
	{
		m_strDate = date;
	}

	/**
	 * 要查询的页数
	 */
	protected Integer m_iPage;
	public Integer getPage()
	{
		return m_iPage;
	}
	public void setPage(Integer page)
	{
		m_iPage = page;
	}

	/**
	 * 总页数
	 */
	protected Integer m_iTotal;
	public Integer getTotal()
	{
		return m_iTotal;
	}
	public void setTotal(Integer total)
	{
		m_iTotal = total;
	}

	/**
	 * 记录内容
	 */
	protected String[] m_strContent;
	public String[] getContent()
	{
		return m_strContent;
	}
	public void setContent(String[] content)
	{
		m_strContent = content;
	}

	public UserGameRecordMessage()
	{
		this.m_iType = USER_GAMERECORD_MESSAGE;
	}

	public UserGameRecordMessage(long userid, String date, int page, int total, String[] content)
	{
		this.m_iType = USER_GAMERECORD_MESSAGE;
		m_lUserid = userid;
		m_strDate = date;
		m_iPage = page;
		m_iTotal = total;
		m_strContent = content;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		int iArrLength;
		m_lUserid = readLong(bytes);

		iLength = readInt(bytes);
		m_strDate = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iPage = readInt(bytes);

		m_iTotal = readInt(bytes);

		iArrLength = readInt(bytes);
		m_strContent = iArrLength == 0 ? null : new String[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			iLength = readInt(bytes);
			m_strContent[i] = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		}
		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] dateBytes = null;
		dateBytes = m_strDate == null ? null : m_strDate.getBytes("utf-8");
		iAllLength += INT_SIZE + (dateBytes == null ? 0 : dateBytes.length);

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		byte[][] contentBytes = m_strContent != null ? new byte[m_strContent.length][] : null;
		iAllLength += INT_SIZE;
		for (int i = 0; m_strContent != null && i < m_strContent.length; i++)
		{
			contentBytes[i] = m_strContent[i] == null ? null : m_strContent[i].getBytes("utf-8");
			iAllLength += INT_SIZE + (contentBytes[i] == null ? 0 : contentBytes[i].length);

		}
		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = intToBytes(retBytes, iPos, dateBytes == null ? 0 : dateBytes.length);
		if (dateBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, dateBytes, dateBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, m_iPage);

		iPos = intToBytes(retBytes, iPos, m_iTotal);

		iPos = intToBytes(retBytes, iPos, m_strContent != null ? m_strContent.length : 0);
		for (int i = 0; m_strContent != null && i < m_strContent.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, contentBytes[i] == null ? 0 : contentBytes[i].length);
			if (contentBytes[i] != null)
			{
				iPos = bytesToBytes(retBytes, iPos, contentBytes[i], contentBytes[i].length);
			}

		}
		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "UserGameRecordMessage<>";
	}

	public String toString()
	{
		return "UserGameRecordMessage<" + "userid:" + m_lUserid + ", " + "date:" + m_strDate + ", " + "page:" + m_iPage + ", " + "total:" + m_iTotal + ", " + "content:" + m_strContent + ">";
	}
}