package org.dream.message;

import java.io.*;


/**
 * 大厅和进入游戏公告
 */
public class NoticeMessage extends Message
{
	/**
	 * 公告类型：2大厅，3游戏桌台
	 */
	protected Integer m_iNtype;
	public Integer getNtype()
	{
		return m_iNtype;
	}
	public void setNtype(Integer ntype)
	{
		m_iNtype = ntype;
	}

	/**
	 * 中文内容
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
	 * 英文内容
	 */
	protected String[] m_strEnotice;
	public String[] getEnotice()
	{
		return m_strEnotice;
	}
	public void setEnotice(String[] enotice)
	{
		m_strEnotice = enotice;
	}

	/**
	 * 繁体内容
	 */
	protected String[] m_strTnotice;
	public String[] getTnotice()
	{
		return m_strTnotice;
	}
	public void setTnotice(String[] tnotice)
	{
		m_strTnotice = tnotice;
	}

	public NoticeMessage()
	{
		super(NOTICE_MESSAGE);
	}

	public NoticeMessage(int ntype, String[] notice, String[] enotice, String[] tnotice)
	{
		super(NOTICE_MESSAGE);
		m_iNtype = ntype;
		m_strNotice = notice;
		m_strEnotice = enotice;
		m_strTnotice = tnotice;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		int iArrLength;
		m_iNtype = readInt(bytes);

		iArrLength = readInt(bytes);
		m_strNotice = iArrLength == 0 ? null : new String[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			iLength = readInt(bytes);
			m_strNotice[i] = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		}
		iArrLength = readInt(bytes);
		m_strEnotice = iArrLength == 0 ? null : new String[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			iLength = readInt(bytes);
			m_strEnotice[i] = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		}
		iArrLength = readInt(bytes);
		m_strTnotice = iArrLength == 0 ? null : new String[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			iLength = readInt(bytes);
			m_strTnotice[i] = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		}
		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += INT_SIZE;

		byte[][] noticeBytes = m_strNotice != null ? new byte[m_strNotice.length][] : null;
		iAllLength += INT_SIZE;
		for (int i = 0; m_strNotice != null && i < m_strNotice.length; i++)
		{
			noticeBytes[i] = m_strNotice[i] == null ? null : m_strNotice[i].getBytes("utf-8");
			iAllLength += INT_SIZE + (noticeBytes[i] == null ? 0 : noticeBytes[i].length);

		}
		byte[][] enoticeBytes = m_strEnotice != null ? new byte[m_strEnotice.length][] : null;
		iAllLength += INT_SIZE;
		for (int i = 0; m_strEnotice != null && i < m_strEnotice.length; i++)
		{
			enoticeBytes[i] = m_strEnotice[i] == null ? null : m_strEnotice[i].getBytes("utf-8");
			iAllLength += INT_SIZE + (enoticeBytes[i] == null ? 0 : enoticeBytes[i].length);

		}
		byte[][] tnoticeBytes = m_strTnotice != null ? new byte[m_strTnotice.length][] : null;
		iAllLength += INT_SIZE;
		for (int i = 0; m_strTnotice != null && i < m_strTnotice.length; i++)
		{
			tnoticeBytes[i] = m_strTnotice[i] == null ? null : m_strTnotice[i].getBytes("utf-8");
			iAllLength += INT_SIZE + (tnoticeBytes[i] == null ? 0 : tnoticeBytes[i].length);

		}
		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = intToBytes(retBytes, iPos, m_iNtype);

		iPos = intToBytes(retBytes, iPos, m_strNotice != null ? m_strNotice.length : 0);
		for (int i = 0; m_strNotice != null && i < m_strNotice.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, noticeBytes[i] == null ? 0 : noticeBytes[i].length);
			if (noticeBytes[i] != null)
			{
				iPos = bytesToBytes(retBytes, iPos, noticeBytes[i], noticeBytes[i].length);
			}

		}
		iPos = intToBytes(retBytes, iPos, m_strEnotice != null ? m_strEnotice.length : 0);
		for (int i = 0; m_strEnotice != null && i < m_strEnotice.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, enoticeBytes[i] == null ? 0 : enoticeBytes[i].length);
			if (enoticeBytes[i] != null)
			{
				iPos = bytesToBytes(retBytes, iPos, enoticeBytes[i], enoticeBytes[i].length);
			}

		}
		iPos = intToBytes(retBytes, iPos, m_strTnotice != null ? m_strTnotice.length : 0);
		for (int i = 0; m_strTnotice != null && i < m_strTnotice.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, tnoticeBytes[i] == null ? 0 : tnoticeBytes[i].length);
			if (tnoticeBytes[i] != null)
			{
				iPos = bytesToBytes(retBytes, iPos, tnoticeBytes[i], tnoticeBytes[i].length);
			}

		}
		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "NoticeMessage<>";
	}

	public String toString()
	{
		return "NoticeMessage<" + "ntype:" + m_iNtype + ", " + "notice:" + m_strNotice + ", " + "enotice:" + m_strEnotice + ", " + "tnotice:" + m_strTnotice + ">";
	}
}