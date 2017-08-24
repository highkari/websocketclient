package org.dream.message;

import java.io.*;


/**
 * dealerduan登录响应接口 
 */
public class DealerLoginResMessage extends TableMessage
{
	/**
	 * 台名称 
	 */
	protected String m_strTableNum;
	public String getTableNum()
	{
		return m_strTableNum;
	}
	public void setTableNum(String tableNum)
	{
		m_strTableNum = tableNum;
	}

	/**
	 * 视讯地址-IP:PORT 
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

	/**
	 * 视讯名称 
	 */
	protected String m_strVideoName;
	public String getVideoName()
	{
		return m_strVideoName;
	}
	public void setVideoName(String videoName)
	{
		m_strVideoName = videoName;
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
	 * 荷官编号 
	 */
	protected String m_strDealerNo;
	public String getDealerNo()
	{
		return m_strDealerNo;
	}
	public void setDealerNo(String dealerNo)
	{
		m_strDealerNo = dealerNo;
	}

	public DealerLoginResMessage()
	{
		this.m_iType = DEALER_LOGIN_RES_MESSAGE;
	}

	public DealerLoginResMessage(long tableid, String tableNum, String videoline, String videoName, int gameType, String dealerNo)
	{
		this.m_iType = DEALER_LOGIN_RES_MESSAGE;
		m_lTableid = tableid;
		m_strTableNum = tableNum;
		m_strVideoline = videoline;
		m_strVideoName = videoName;
		m_iGameType = gameType;
		m_strDealerNo = dealerNo;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lTableid = readLong(bytes);

		iLength = readInt(bytes);
		m_strTableNum = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strVideoline = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strVideoName = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iGameType = readInt(bytes);

		iLength = readInt(bytes);
		m_strDealerNo = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] tableNumBytes = null;
		tableNumBytes = m_strTableNum == null ? null : m_strTableNum.getBytes("utf-8");
		iAllLength += INT_SIZE + (tableNumBytes == null ? 0 : tableNumBytes.length);

		byte[] videolineBytes = null;
		videolineBytes = m_strVideoline == null ? null : m_strVideoline.getBytes("utf-8");
		iAllLength += INT_SIZE + (videolineBytes == null ? 0 : videolineBytes.length);

		byte[] videoNameBytes = null;
		videoNameBytes = m_strVideoName == null ? null : m_strVideoName.getBytes("utf-8");
		iAllLength += INT_SIZE + (videoNameBytes == null ? 0 : videoNameBytes.length);

		iAllLength += INT_SIZE;

		byte[] dealerNoBytes = null;
		dealerNoBytes = m_strDealerNo == null ? null : m_strDealerNo.getBytes("utf-8");
		iAllLength += INT_SIZE + (dealerNoBytes == null ? 0 : dealerNoBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, tableNumBytes == null ? 0 : tableNumBytes.length);
		if (tableNumBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, tableNumBytes, tableNumBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, videolineBytes == null ? 0 : videolineBytes.length);
		if (videolineBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, videolineBytes, videolineBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, videoNameBytes == null ? 0 : videoNameBytes.length);
		if (videoNameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, videoNameBytes, videoNameBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, m_iGameType);

		iPos = intToBytes(retBytes, iPos, dealerNoBytes == null ? 0 : dealerNoBytes.length);
		if (dealerNoBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, dealerNoBytes, dealerNoBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "DealerLoginResMessage<>";
	}

	public String toString()
	{
		return "DealerLoginResMessage<" + "tableid:" + m_lTableid + ", " + "tableNum:" + m_strTableNum + ", " + "videoline:" + m_strVideoline + ", " + "videoName:" + m_strVideoName + ", " + "gameType:" + m_iGameType + ", " + "dealerNo:" + m_strDealerNo + ">";
	}
}