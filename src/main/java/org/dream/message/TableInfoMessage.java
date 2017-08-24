package org.dream.message;

import java.io.*;


/**
 * 某个台的信息，包括历史数据，用于绘制路纸
 */
public class TableInfoMessage extends TableMessage
{
	/**
	 * 游戏类型  百家乐BACCARAT:1 龙虎DRAGON-TIGER:2 轮盘ROULETTE:3 骰宝SIC-BO:4 斗牛DOU-NIU:5 麻雀牌九MAHJONG:6 三公TRIPLE-FACES:7 加勒比海CARIBBEAN:8 
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
	 * 靴序号
	 */
	protected String m_strTableSeq;
	public String getTableSeq()
	{
		return m_strTableSeq;
	}
	public void setTableSeq(String tableSeq)
	{
		m_strTableSeq = tableSeq;
	}

	/**
	 * 靴序号
	 */
	protected Integer m_iShoeSeq;
	public Integer getShoeSeq()
	{
		return m_iShoeSeq;
	}
	public void setShoeSeq(Integer shoeSeq)
	{
		m_iShoeSeq = shoeSeq;
	}

	/**
	 * 局序号
	 */
	protected Integer m_iGameSeq;
	public Integer getGameSeq()
	{
		return m_iGameSeq;
	}
	public void setGameSeq(Integer gameSeq)
	{
		m_iGameSeq = gameSeq;
	}

	/**
	 * 台面限额，键值
	 */
	protected String[] m_strLimitKeys;
	public String[] getLimitKeys()
	{
		return m_strLimitKeys;
	}
	public void setLimitKeys(String[] limitKeys)
	{
		m_strLimitKeys = limitKeys;
	}

	/**
	 * 台面限额，下限
	 */
	protected Integer[] m_iMinlimit;
	public Integer[] getMinlimit()
	{
		return m_iMinlimit;
	}
	public void setMinlimit(Integer[] minlimit)
	{
		m_iMinlimit = minlimit;
	}

	/**
	 * 台面限额，上限
	 */
	protected Integer[] m_iMaxlimit;
	public Integer[] getMaxlimit()
	{
		return m_iMaxlimit;
	}
	public void setMaxlimit(Integer[] maxlimit)
	{
		m_iMaxlimit = maxlimit;
	}

	/**
	 * 台面限红
	 */
	protected Integer m_iHedge;
	public Integer getHedge()
	{
		return m_iHedge;
	}
	public void setHedge(Integer hedge)
	{
		m_iHedge = hedge;
	}

	/**
	 * 百家乐，龙虎历史数据
	 * 1-庄赢
	 * 2-闲赢
	 * 3-和
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
	 * 当前在线会员人数
	 */
	protected Integer m_iOnlinenum;
	public Integer getOnlinenum()
	{
		return m_iOnlinenum;
	}
	public void setOnlinenum(Integer onlinenum)
	{
		m_iOnlinenum = onlinenum;
	}

	/**
	 * 台状态
	 */
	protected String m_strTablestate;
	public String getTablestate()
	{
		return m_strTablestate;
	}
	public void setTablestate(String tablestate)
	{
		m_strTablestate = tablestate;
	}

	/**
	 * 荷官名称
	 */
	protected String m_strDealername;
	public String getDealername()
	{
		return m_strDealername;
	}
	public void setDealername(String dealername)
	{
		m_strDealername = dealername;
	}

	/**
	 * 荷官图标
	 */
	protected String m_strDealericon;
	public String getDealericon()
	{
		return m_strDealericon;
	}
	public void setDealericon(String dealericon)
	{
		m_strDealericon = dealericon;
	}

	/**
	 * 主持人名称
	 */
	protected String m_strEmcee;
	public String getEmcee()
	{
		return m_strEmcee;
	}
	public void setEmcee(String emcee)
	{
		m_strEmcee = emcee;
	}

	/**
	 * 主持头像
	 */
	protected String m_strEmceeicon;
	public String getEmceeicon()
	{
		return m_strEmceeicon;
	}
	public void setEmceeicon(String emceeicon)
	{
		m_strEmceeicon = emceeicon;
	}

	/**
	 * 主持语言类型
	 */
	protected Byte m_iEmceelanguage;
	public Byte getEmceelanguage()
	{
		return m_iEmceelanguage;
	}
	public void setEmceelanguage(Byte emceelanguage)
	{
		m_iEmceelanguage = emceelanguage;
	}

	/**
	 * 附加台面限额，键值
	 */
	protected String[] m_strAlimitKeys;
	public String[] getAlimitKeys()
	{
		return m_strAlimitKeys;
	}
	public void setAlimitKeys(String[] alimitKeys)
	{
		m_strAlimitKeys = alimitKeys;
	}

	/**
	 * 附加台面限额，下限
	 */
	protected Integer[] m_iAminlimit;
	public Integer[] getAminlimit()
	{
		return m_iAminlimit;
	}
	public void setAminlimit(Integer[] aminlimit)
	{
		m_iAminlimit = aminlimit;
	}

	/**
	 * 附加台面限额，上限
	 */
	protected Integer[] m_iAmaxlimit;
	public Integer[] getAmaxlimit()
	{
		return m_iAmaxlimit;
	}
	public void setAmaxlimit(Integer[] amaxlimit)
	{
		m_iAmaxlimit = amaxlimit;
	}

	/**
	 * 游戏风格（0:默认，1:旗舰 2:博达） 
	 */
	protected Integer m_iGamestyle;
	public Integer getGamestyle()
	{
		return m_iGamestyle;
	}
	public void setGamestyle(Integer gamestyle)
	{
		m_iGamestyle = gamestyle;
	}

	/**
	 * 进入方式，单人0，多人1,旁观2
	 */
	protected Integer m_iEntertype;
	public Integer getEntertype()
	{
		return m_iEntertype;
	}
	public void setEntertype(Integer entertype)
	{
		m_iEntertype = entertype;
	}

	/**
	 * 座位号
	 */
	protected Integer m_iSeatno;
	public Integer getSeatno()
	{
		return m_iSeatno;
	}
	public void setSeatno(Integer seatno)
	{
		m_iSeatno = seatno;
	}

	/**
	 * 台面限额，键值
	 */
	protected String m_strLastlimitKey;
	public String getLastlimitKey()
	{
		return m_strLastlimitKey;
	}
	public void setLastlimitKey(String lastlimitKey)
	{
		m_strLastlimitKey = lastlimitKey;
	}

	/**
	 *  牌背风格 0:蓝色 1:红色 
	 */
	protected Integer m_iPokerStyle;
	public Integer getPokerStyle()
	{
		return m_iPokerStyle;
	}
	public void setPokerStyle(Integer pokerStyle)
	{
		m_iPokerStyle = pokerStyle;
	}

	public TableInfoMessage()
	{
		this.m_iType = TABLE_INFO_MESSAGE;
	}

	public TableInfoMessage(long tableid, int gameType, String tableSeq, int shoeSeq, int gameSeq, String[] limitKeys, Integer[] minlimit, Integer[] maxlimit, int hedge, Byte[] history, String[] history2, Integer[] history3, int onlinenum, String tablestate, String dealername, String dealericon, String emcee, String emceeicon, byte emceelanguage, String[] alimitKeys, Integer[] aminlimit, Integer[] amaxlimit, int gamestyle, int entertype, int seatno, String lastlimitKey, int pokerStyle)
	{
		this.m_iType = TABLE_INFO_MESSAGE;
		m_lTableid = tableid;
		m_iGameType = gameType;
		m_strTableSeq = tableSeq;
		m_iShoeSeq = shoeSeq;
		m_iGameSeq = gameSeq;
		m_strLimitKeys = limitKeys;
		m_iMinlimit = minlimit;
		m_iMaxlimit = maxlimit;
		m_iHedge = hedge;
		m_iHistory = history;
		m_strHistory2 = history2;
		m_iHistory3 = history3;
		m_iOnlinenum = onlinenum;
		m_strTablestate = tablestate;
		m_strDealername = dealername;
		m_strDealericon = dealericon;
		m_strEmcee = emcee;
		m_strEmceeicon = emceeicon;
		m_iEmceelanguage = emceelanguage;
		m_strAlimitKeys = alimitKeys;
		m_iAminlimit = aminlimit;
		m_iAmaxlimit = amaxlimit;
		m_iGamestyle = gamestyle;
		m_iEntertype = entertype;
		m_iSeatno = seatno;
		m_strLastlimitKey = lastlimitKey;
		m_iPokerStyle = pokerStyle;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		int iArrLength;
		m_lTableid = readLong(bytes);

		m_iGameType = readInt(bytes);

		iLength = readInt(bytes);
		m_strTableSeq = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iShoeSeq = readInt(bytes);

		m_iGameSeq = readInt(bytes);

		iArrLength = readInt(bytes);
		m_strLimitKeys = iArrLength == 0 ? null : new String[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			iLength = readInt(bytes);
			m_strLimitKeys[i] = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		}
		iArrLength = readInt(bytes);
		m_iMinlimit = iArrLength == 0 ? null : new Integer[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			m_iMinlimit[i] = readInt(bytes);

		}
		iArrLength = readInt(bytes);
		m_iMaxlimit = iArrLength == 0 ? null : new Integer[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			m_iMaxlimit[i] = readInt(bytes);

		}
		m_iHedge = readInt(bytes);

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
		m_iOnlinenum = readInt(bytes);

		iLength = readInt(bytes);
		m_strTablestate = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strDealername = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strDealericon = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strEmcee = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strEmceeicon = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iEmceelanguage = readByte(bytes);

		iArrLength = readInt(bytes);
		m_strAlimitKeys = iArrLength == 0 ? null : new String[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			iLength = readInt(bytes);
			m_strAlimitKeys[i] = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		}
		iArrLength = readInt(bytes);
		m_iAminlimit = iArrLength == 0 ? null : new Integer[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			m_iAminlimit[i] = readInt(bytes);

		}
		iArrLength = readInt(bytes);
		m_iAmaxlimit = iArrLength == 0 ? null : new Integer[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			m_iAmaxlimit[i] = readInt(bytes);

		}
		m_iGamestyle = readInt(bytes);

		m_iEntertype = readInt(bytes);

		m_iSeatno = readInt(bytes);

		iLength = readInt(bytes);
		m_strLastlimitKey = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iPokerStyle = readInt(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += INT_SIZE;

		byte[] tableSeqBytes = null;
		tableSeqBytes = m_strTableSeq == null ? null : m_strTableSeq.getBytes("utf-8");
		iAllLength += INT_SIZE + (tableSeqBytes == null ? 0 : tableSeqBytes.length);

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		byte[][] limitKeysBytes = m_strLimitKeys != null ? new byte[m_strLimitKeys.length][] : null;
		iAllLength += INT_SIZE;
		for (int i = 0; m_strLimitKeys != null && i < m_strLimitKeys.length; i++)
		{
			limitKeysBytes[i] = m_strLimitKeys[i] == null ? null : m_strLimitKeys[i].getBytes("utf-8");
			iAllLength += INT_SIZE + (limitKeysBytes[i] == null ? 0 : limitKeysBytes[i].length);

		}
		iAllLength += INT_SIZE;
		for (int i = 0; m_iMinlimit != null && i < m_iMinlimit.length; i++)
		{
			iAllLength += INT_SIZE;

		}
		iAllLength += INT_SIZE;
		for (int i = 0; m_iMaxlimit != null && i < m_iMaxlimit.length; i++)
		{
			iAllLength += INT_SIZE;

		}
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
		iAllLength += INT_SIZE;

		byte[] tablestateBytes = null;
		tablestateBytes = m_strTablestate == null ? null : m_strTablestate.getBytes("utf-8");
		iAllLength += INT_SIZE + (tablestateBytes == null ? 0 : tablestateBytes.length);

		byte[] dealernameBytes = null;
		dealernameBytes = m_strDealername == null ? null : m_strDealername.getBytes("utf-8");
		iAllLength += INT_SIZE + (dealernameBytes == null ? 0 : dealernameBytes.length);

		byte[] dealericonBytes = null;
		dealericonBytes = m_strDealericon == null ? null : m_strDealericon.getBytes("utf-8");
		iAllLength += INT_SIZE + (dealericonBytes == null ? 0 : dealericonBytes.length);

		byte[] emceeBytes = null;
		emceeBytes = m_strEmcee == null ? null : m_strEmcee.getBytes("utf-8");
		iAllLength += INT_SIZE + (emceeBytes == null ? 0 : emceeBytes.length);

		byte[] emceeiconBytes = null;
		emceeiconBytes = m_strEmceeicon == null ? null : m_strEmceeicon.getBytes("utf-8");
		iAllLength += INT_SIZE + (emceeiconBytes == null ? 0 : emceeiconBytes.length);

		iAllLength += BYTE_SIZE;

		byte[][] alimitKeysBytes = m_strAlimitKeys != null ? new byte[m_strAlimitKeys.length][] : null;
		iAllLength += INT_SIZE;
		for (int i = 0; m_strAlimitKeys != null && i < m_strAlimitKeys.length; i++)
		{
			alimitKeysBytes[i] = m_strAlimitKeys[i] == null ? null : m_strAlimitKeys[i].getBytes("utf-8");
			iAllLength += INT_SIZE + (alimitKeysBytes[i] == null ? 0 : alimitKeysBytes[i].length);

		}
		iAllLength += INT_SIZE;
		for (int i = 0; m_iAminlimit != null && i < m_iAminlimit.length; i++)
		{
			iAllLength += INT_SIZE;

		}
		iAllLength += INT_SIZE;
		for (int i = 0; m_iAmaxlimit != null && i < m_iAmaxlimit.length; i++)
		{
			iAllLength += INT_SIZE;

		}
		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		byte[] lastlimitKeyBytes = null;
		lastlimitKeyBytes = m_strLastlimitKey == null ? null : m_strLastlimitKey.getBytes("utf-8");
		iAllLength += INT_SIZE + (lastlimitKeyBytes == null ? 0 : lastlimitKeyBytes.length);

		iAllLength += INT_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, m_iGameType);

		iPos = intToBytes(retBytes, iPos, tableSeqBytes == null ? 0 : tableSeqBytes.length);
		if (tableSeqBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, tableSeqBytes, tableSeqBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, m_iShoeSeq);

		iPos = intToBytes(retBytes, iPos, m_iGameSeq);

		iPos = intToBytes(retBytes, iPos, m_strLimitKeys != null ? m_strLimitKeys.length : 0);
		for (int i = 0; m_strLimitKeys != null && i < m_strLimitKeys.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, limitKeysBytes[i] == null ? 0 : limitKeysBytes[i].length);
			if (limitKeysBytes[i] != null)
			{
				iPos = bytesToBytes(retBytes, iPos, limitKeysBytes[i], limitKeysBytes[i].length);
			}

		}
		iPos = intToBytes(retBytes, iPos, m_iMinlimit != null ? m_iMinlimit.length : 0);
		for (int i = 0; m_iMinlimit != null && i < m_iMinlimit.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, m_iMinlimit[i]);

		}
		iPos = intToBytes(retBytes, iPos, m_iMaxlimit != null ? m_iMaxlimit.length : 0);
		for (int i = 0; m_iMaxlimit != null && i < m_iMaxlimit.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, m_iMaxlimit[i]);

		}
		iPos = intToBytes(retBytes, iPos, m_iHedge);

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
		iPos = intToBytes(retBytes, iPos, m_iOnlinenum);

		iPos = intToBytes(retBytes, iPos, tablestateBytes == null ? 0 : tablestateBytes.length);
		if (tablestateBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, tablestateBytes, tablestateBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, dealernameBytes == null ? 0 : dealernameBytes.length);
		if (dealernameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, dealernameBytes, dealernameBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, dealericonBytes == null ? 0 : dealericonBytes.length);
		if (dealericonBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, dealericonBytes, dealericonBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, emceeBytes == null ? 0 : emceeBytes.length);
		if (emceeBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, emceeBytes, emceeBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, emceeiconBytes == null ? 0 : emceeiconBytes.length);
		if (emceeiconBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, emceeiconBytes, emceeiconBytes.length);
		}

		iPos = byteToBytes(retBytes, iPos, m_iEmceelanguage);

		iPos = intToBytes(retBytes, iPos, m_strAlimitKeys != null ? m_strAlimitKeys.length : 0);
		for (int i = 0; m_strAlimitKeys != null && i < m_strAlimitKeys.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, alimitKeysBytes[i] == null ? 0 : alimitKeysBytes[i].length);
			if (alimitKeysBytes[i] != null)
			{
				iPos = bytesToBytes(retBytes, iPos, alimitKeysBytes[i], alimitKeysBytes[i].length);
			}

		}
		iPos = intToBytes(retBytes, iPos, m_iAminlimit != null ? m_iAminlimit.length : 0);
		for (int i = 0; m_iAminlimit != null && i < m_iAminlimit.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, m_iAminlimit[i]);

		}
		iPos = intToBytes(retBytes, iPos, m_iAmaxlimit != null ? m_iAmaxlimit.length : 0);
		for (int i = 0; m_iAmaxlimit != null && i < m_iAmaxlimit.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, m_iAmaxlimit[i]);

		}
		iPos = intToBytes(retBytes, iPos, m_iGamestyle);

		iPos = intToBytes(retBytes, iPos, m_iEntertype);

		iPos = intToBytes(retBytes, iPos, m_iSeatno);

		iPos = intToBytes(retBytes, iPos, lastlimitKeyBytes == null ? 0 : lastlimitKeyBytes.length);
		if (lastlimitKeyBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, lastlimitKeyBytes, lastlimitKeyBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, m_iPokerStyle);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "TableInfoMessage<>";
	}

	public String toString()
	{
		return "TableInfoMessage<" + "tableid:" + m_lTableid + ", " + "gameType:" + m_iGameType + ", " + "tableSeq:" + m_strTableSeq + ", " + "shoeSeq:" + m_iShoeSeq + ", " + "gameSeq:" + m_iGameSeq + ", " + "limitKeys:" + m_strLimitKeys + ", " + "minlimit:" + m_iMinlimit + ", " + "maxlimit:" + m_iMaxlimit + ", " + "hedge:" + m_iHedge + ", " + "history:" + m_iHistory + ", " + "history2:" + m_strHistory2 + ", " + "history3:" + m_iHistory3 + ", " + "onlinenum:" + m_iOnlinenum + ", " + "tablestate:" + m_strTablestate + ", " + "dealername:" + m_strDealername + ", " + "dealericon:" + m_strDealericon + ", " + "emcee:" + m_strEmcee + ", " + "emceeicon:" + m_strEmceeicon + ", " + "emceelanguage:" + m_iEmceelanguage + ", " + "alimitKeys:" + m_strAlimitKeys + ", " + "aminlimit:" + m_iAminlimit + ", " + "amaxlimit:" + m_iAmaxlimit + ", " + "gamestyle:" + m_iGamestyle + ", " + "entertype:" + m_iEntertype + ", " + "seatno:" + m_iSeatno + ", " + "lastlimitKey:" + m_strLastlimitKey + ", " + "pokerStyle:" + m_iPokerStyle + ">";
	}
}