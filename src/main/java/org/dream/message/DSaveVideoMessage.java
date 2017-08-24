package org.dream.message;

import java.io.*;

public class DSaveVideoMessage extends TableMessage
{
	/**
	 *  视频名称 
	 */
	protected String m_strVideoName;
	public String getVideoName()
	{
		return m_strVideoName;
	}
	public void setVideoName(String VideoName)
	{
		m_strVideoName = VideoName;
	}

	/**
	 *  视频名称 
	 */
	protected String m_strPicName;
	public String getPicName()
	{
		return m_strPicName;
	}
	public void setPicName(String PicName)
	{
		m_strPicName = PicName;
	}

	public DSaveVideoMessage()
	{
		this.m_iType = D_SAVE_VIDEO_MESSAGE;
	}

	public DSaveVideoMessage(long tableid, String VideoName, String PicName)
	{
		this.m_iType = D_SAVE_VIDEO_MESSAGE;
		m_lTableid = tableid;
		m_strVideoName = VideoName;
		m_strPicName = PicName;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lTableid = readLong(bytes);

		iLength = readInt(bytes);
		m_strVideoName = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strPicName = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] VideoNameBytes = null;
		VideoNameBytes = m_strVideoName == null ? null : m_strVideoName.getBytes("utf-8");
		iAllLength += INT_SIZE + (VideoNameBytes == null ? 0 : VideoNameBytes.length);

		byte[] PicNameBytes = null;
		PicNameBytes = m_strPicName == null ? null : m_strPicName.getBytes("utf-8");
		iAllLength += INT_SIZE + (PicNameBytes == null ? 0 : PicNameBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, VideoNameBytes == null ? 0 : VideoNameBytes.length);
		if (VideoNameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, VideoNameBytes, VideoNameBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, PicNameBytes == null ? 0 : PicNameBytes.length);
		if (PicNameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, PicNameBytes, PicNameBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "DSaveVideoMessage<>";
	}

	public String toString()
	{
		return "DSaveVideoMessage<" + "tableid:" + m_lTableid + ", " + "VideoName:" + m_strVideoName + ", " + "PicName:" + m_strPicName + ">";
	}
}