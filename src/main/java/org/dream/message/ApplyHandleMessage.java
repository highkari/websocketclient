package org.dream.message;

import java.io.*;


/**
 *  咪牌动作消息 
 */
public class ApplyHandleMessage extends TableMessage
{
	/**
	 * 用户主键
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
	 *  昵称  
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
	 *  操作类型（1、等我，2、开牌）
	 */
	protected Byte m_iOptype;
	public Byte getOptype()
	{
		return m_iOptype;
	}
	public void setOptype(Byte optype)
	{
		m_iOptype = optype;
	}

	/**
	 *  -1失败  1成功 
	 */
	protected Integer m_iResult;
	public Integer getResult()
	{
		return m_iResult;
	}
	public void setResult(Integer result)
	{
		m_iResult = result;
	}

	public ApplyHandleMessage()
	{
		this.m_iType = APPLY_HANDLE_MESSAGE;
	}

	public ApplyHandleMessage(long tableid, long userid, String showname, byte optype, int result)
	{
		this.m_iType = APPLY_HANDLE_MESSAGE;
		m_lTableid = tableid;
		m_lUserid = userid;
		m_strShowname = showname;
		m_iOptype = optype;
		m_iResult = result;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lTableid = readLong(bytes);

		m_lUserid = readLong(bytes);

		iLength = readInt(bytes);
		m_strShowname = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iOptype = readByte(bytes);

		m_iResult = readInt(bytes);

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

		iAllLength += BYTE_SIZE;

		iAllLength += INT_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = intToBytes(retBytes, iPos, shownameBytes == null ? 0 : shownameBytes.length);
		if (shownameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, shownameBytes, shownameBytes.length);
		}

		iPos = byteToBytes(retBytes, iPos, m_iOptype);

		iPos = intToBytes(retBytes, iPos, m_iResult);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "ApplyHandleMessage<>";
	}

	public String toString()
	{
		return "ApplyHandleMessage<" + "tableid:" + m_lTableid + ", " + "userid:" + m_lUserid + ", " + "showname:" + m_strShowname + ", " + "optype:" + m_iOptype + ", " + "result:" + m_iResult + ">";
	}
}