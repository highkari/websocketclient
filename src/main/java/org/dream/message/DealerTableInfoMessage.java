package org.dream.message;

import java.io.*;


/**
 *  
 */
public class DealerTableInfoMessage extends TableMessage
{
	/**
	 * 台状态
	 */
	protected String m_strState;
	public String getState()
	{
		return m_strState;
	}
	public void setState(String state)
	{
		m_strState = state;
	}

	/**
	 * 靴号 
	 */
	protected Integer m_iShoenum;
	public Integer getShoenum()
	{
		return m_iShoenum;
	}
	public void setShoenum(Integer shoenum)
	{
		m_iShoenum = shoenum;
	}

	/**
	 * 局号 
	 */
	protected Integer m_iInningnum;
	public Integer getInningnum()
	{
		return m_iInningnum;
	}
	public void setInningnum(Integer inningnum)
	{
		m_iInningnum = inningnum;
	}

	/**
	 * 倒计时 
	 */
	protected Integer m_iCountdown;
	public Integer getCountdown()
	{
		return m_iCountdown;
	}
	public void setCountdown(Integer countdown)
	{
		m_iCountdown = countdown;
	}

	/**
	 * 牌结果 
	 */
	protected String m_strCardresult;
	public String getCardresult()
	{
		return m_strCardresult;
	}
	public void setCardresult(String cardresult)
	{
		m_strCardresult = cardresult;
	}

	/**
	 * 视讯名称 
	 */
	protected String m_strVname;
	public String getVname()
	{
		return m_strVname;
	}
	public void setVname(String vname)
	{
		m_strVname = vname;
	}

	/**
	 * 最大时间 
	 */
	protected Integer m_iMaxtime;
	public Integer getMaxtime()
	{
		return m_iMaxtime;
	}
	public void setMaxtime(Integer maxtime)
	{
		m_iMaxtime = maxtime;
	}

	/**
	 * 百家乐，龙虎历史数据
	 */
	protected Byte[] m_iHistory;
	public Byte[] getHistory()
	{
		return m_iHistory;
	}
	public void setHistory(Byte[] history)
	{
		m_iHistory = history;
	}

	/**
	 * 斗牛历史数据
	 */
	protected String[] m_strHistory2;
	public String[] getHistory2()
	{
		return m_strHistory2;
	}
	public void setHistory2(String[] history2)
	{
		m_strHistory2 = history2;
	}

	/**
	 * 轮盘历史数据
	 */
	protected Integer[] m_iHistory3;
	public Integer[] getHistory3()
	{
		return m_iHistory3;
	}
	public void setHistory3(Integer[] history3)
	{
		m_iHistory3 = history3;
	}

	/**
	 * 是否包桌>0是, =0否
	 */
	protected Long m_lMaster;
	public Long getMaster()
	{
		return m_lMaster;
	}
	public void setMaster(Long master)
	{
		m_lMaster = master;
	}

	public DealerTableInfoMessage()
	{
		this.m_iType = DEALER_TABLEINFO_MESSAGE;
	}

	public DealerTableInfoMessage(long tableid, String state, int shoenum, int inningnum, int countdown, String cardresult, String vname, int maxtime, Byte[] history, String[] history2, Integer[] history3, long master)
	{
		this.m_iType = DEALER_TABLEINFO_MESSAGE;
		m_lTableid = tableid;
		m_strState = state;
		m_iShoenum = shoenum;
		m_iInningnum = inningnum;
		m_iCountdown = countdown;
		m_strCardresult = cardresult;
		m_strVname = vname;
		m_iMaxtime = maxtime;
		m_iHistory = history;
		m_strHistory2 = history2;
		m_iHistory3 = history3;
		m_lMaster = master;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		int iArrLength;
		m_lTableid = readLong(bytes);

		iLength = readInt(bytes);
		m_strState = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iShoenum = readInt(bytes);

		m_iInningnum = readInt(bytes);

		m_iCountdown = readInt(bytes);

		iLength = readInt(bytes);
		m_strCardresult = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strVname = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iMaxtime = readInt(bytes);

		iArrLength = readInt(bytes);
		m_iHistory = iArrLength == 0 ? null : new Byte[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			m_iHistory[i] = readByte(bytes);

		}
		iArrLength = readInt(bytes);
		m_strHistory2 = iArrLength == 0 ? null : new String[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			iLength = readInt(bytes);
			m_strHistory2[i] = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		}
		iArrLength = readInt(bytes);
		m_iHistory3 = iArrLength == 0 ? null : new Integer[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			m_iHistory3[i] = readInt(bytes);

		}
		m_lMaster = readLong(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] stateBytes = null;
		stateBytes = m_strState == null ? null : m_strState.getBytes("utf-8");
		iAllLength += INT_SIZE + (stateBytes == null ? 0 : stateBytes.length);

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		byte[] cardresultBytes = null;
		cardresultBytes = m_strCardresult == null ? null : m_strCardresult.getBytes("utf-8");
		iAllLength += INT_SIZE + (cardresultBytes == null ? 0 : cardresultBytes.length);

		byte[] vnameBytes = null;
		vnameBytes = m_strVname == null ? null : m_strVname.getBytes("utf-8");
		iAllLength += INT_SIZE + (vnameBytes == null ? 0 : vnameBytes.length);

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;
		for (int i = 0; m_iHistory != null && i < m_iHistory.length; i++)
		{
			iAllLength += BYTE_SIZE;

		}
		byte[][] history2Bytes = m_strHistory2 != null ? new byte[m_strHistory2.length][] : null;
		iAllLength += INT_SIZE;
		for (int i = 0; m_strHistory2 != null && i < m_strHistory2.length; i++)
		{
			history2Bytes[i] = m_strHistory2[i] == null ? null : m_strHistory2[i].getBytes("utf-8");
			iAllLength += INT_SIZE + (history2Bytes[i] == null ? 0 : history2Bytes[i].length);

		}
		iAllLength += INT_SIZE;
		for (int i = 0; m_iHistory3 != null && i < m_iHistory3.length; i++)
		{
			iAllLength += INT_SIZE;

		}
		iAllLength += LONG_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, stateBytes == null ? 0 : stateBytes.length);
		if (stateBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, stateBytes, stateBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, m_iShoenum);

		iPos = intToBytes(retBytes, iPos, m_iInningnum);

		iPos = intToBytes(retBytes, iPos, m_iCountdown);

		iPos = intToBytes(retBytes, iPos, cardresultBytes == null ? 0 : cardresultBytes.length);
		if (cardresultBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, cardresultBytes, cardresultBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, vnameBytes == null ? 0 : vnameBytes.length);
		if (vnameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, vnameBytes, vnameBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, m_iMaxtime);

		iPos = intToBytes(retBytes, iPos, m_iHistory != null ? m_iHistory.length : 0);
		for (int i = 0; m_iHistory != null && i < m_iHistory.length; i++)
		{
			iPos = byteToBytes(retBytes, iPos, m_iHistory[i]);

		}
		iPos = intToBytes(retBytes, iPos, m_strHistory2 != null ? m_strHistory2.length : 0);
		for (int i = 0; m_strHistory2 != null && i < m_strHistory2.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, history2Bytes[i] == null ? 0 : history2Bytes[i].length);
			if (history2Bytes[i] != null)
			{
				iPos = bytesToBytes(retBytes, iPos, history2Bytes[i], history2Bytes[i].length);
			}

		}
		iPos = intToBytes(retBytes, iPos, m_iHistory3 != null ? m_iHistory3.length : 0);
		for (int i = 0; m_iHistory3 != null && i < m_iHistory3.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, m_iHistory3[i]);

		}
		iPos = longToBytes(retBytes, iPos, m_lMaster);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "DealerTableInfoMessage<>";
	}

	public String toString()
	{
		return "DealerTableInfoMessage<" + "tableid:" + m_lTableid + ", " + "state:" + m_strState + ", " + "shoenum:" + m_iShoenum + ", " + "inningnum:" + m_iInningnum + ", " + "countdown:" + m_iCountdown + ", " + "cardresult:" + m_strCardresult + ", " + "vname:" + m_strVname + ", " + "maxtime:" + m_iMaxtime + ", " + "history:" + m_iHistory + ", " + "history2:" + m_strHistory2 + ", " + "history3:" + m_iHistory3 + ", " + "master:" + m_lMaster + ">";
	}
}