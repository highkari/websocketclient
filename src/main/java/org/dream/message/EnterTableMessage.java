package org.dream.message;

import java.io.*;


/**
 *  在某个台坐下，由系统通知用户 
 */
public class EnterTableMessage extends UserMessage
{
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
	 * 以多大台面限红进入游戏
	 */
	protected Integer m_iMinlimit;
	public Integer getMinlimit()
	{
		return m_iMinlimit;
	}
	public void setMinlimit(Integer minlimit)
	{
		m_iMinlimit = minlimit;
	}

	/**
	 * 以多大台面限红进入游戏
	 */
	protected Integer m_iMaxlimit;
	public Integer getMaxlimit()
	{
		return m_iMaxlimit;
	}
	public void setMaxlimit(Integer maxlimit)
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
	 * 用户在虚拟房间中的座次号，以左起第一个为0，如果未进入虚拟房间，座次号也为0 
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

	/**
	 * 是否免佣投注0:否,1:是
	 */
	protected Integer m_iSbanker;
	public Integer getSbanker()
	{
		return m_iSbanker;
	}
	public void setSbanker(Integer sbanker)
	{
		m_iSbanker = sbanker;
	}

	/**
	 *  视讯地址 
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
	 *  斗牛排序0否, 1是 
	 */
	protected Integer m_iOrder;
	public Integer getOrder()
	{
		return m_iOrder;
	}
	public void setOrder(Integer order)
	{
		m_iOrder = order;
	}

	public EnterTableMessage()
	{
		this.m_iType = ENTER_TABLE_MESSAGE;
	}

	public EnterTableMessage(long userid, long tableid, int gameType, String tableSeq, int shoeSeq, int gameSeq, int minlimit, int maxlimit, int hedge, int seatid, int sbanker, String videoline, int order)
	{
		this.m_iType = ENTER_TABLE_MESSAGE;
		m_lUserid = userid;
		m_lTableid = tableid;
		m_iGameType = gameType;
		m_strTableSeq = tableSeq;
		m_iShoeSeq = shoeSeq;
		m_iGameSeq = gameSeq;
		m_iMinlimit = minlimit;
		m_iMaxlimit = maxlimit;
		m_iHedge = hedge;
		m_iSeatid = seatid;
		m_iSbanker = sbanker;
		m_strVideoline = videoline;
		m_iOrder = order;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lUserid = readLong(bytes);

		m_lTableid = readLong(bytes);

		m_iGameType = readInt(bytes);

		iLength = readInt(bytes);
		m_strTableSeq = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iShoeSeq = readInt(bytes);

		m_iGameSeq = readInt(bytes);

		m_iMinlimit = readInt(bytes);

		m_iMaxlimit = readInt(bytes);

		m_iHedge = readInt(bytes);

		m_iSeatid = readInt(bytes);

		m_iSbanker = readInt(bytes);

		iLength = readInt(bytes);
		m_strVideoline = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iOrder = readInt(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += INT_SIZE;

		byte[] tableSeqBytes = null;
		tableSeqBytes = m_strTableSeq == null ? null : m_strTableSeq.getBytes("utf-8");
		iAllLength += INT_SIZE + (tableSeqBytes == null ? 0 : tableSeqBytes.length);

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		byte[] videolineBytes = null;
		videolineBytes = m_strVideoline == null ? null : m_strVideoline.getBytes("utf-8");
		iAllLength += INT_SIZE + (videolineBytes == null ? 0 : videolineBytes.length);

		iAllLength += INT_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, m_iGameType);

		iPos = intToBytes(retBytes, iPos, tableSeqBytes == null ? 0 : tableSeqBytes.length);
		if (tableSeqBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, tableSeqBytes, tableSeqBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, m_iShoeSeq);

		iPos = intToBytes(retBytes, iPos, m_iGameSeq);

		iPos = intToBytes(retBytes, iPos, m_iMinlimit);

		iPos = intToBytes(retBytes, iPos, m_iMaxlimit);

		iPos = intToBytes(retBytes, iPos, m_iHedge);

		iPos = intToBytes(retBytes, iPos, m_iSeatid);

		iPos = intToBytes(retBytes, iPos, m_iSbanker);

		iPos = intToBytes(retBytes, iPos, videolineBytes == null ? 0 : videolineBytes.length);
		if (videolineBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, videolineBytes, videolineBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, m_iOrder);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "EnterTableMessage<>";
	}

	public String toString()
	{
		return "EnterTableMessage<" + "userid:" + m_lUserid + ", " + "tableid:" + m_lTableid + ", " + "gameType:" + m_iGameType + ", " + "tableSeq:" + m_strTableSeq + ", " + "shoeSeq:" + m_iShoeSeq + ", " + "gameSeq:" + m_iGameSeq + ", " + "minlimit:" + m_iMinlimit + ", " + "maxlimit:" + m_iMaxlimit + ", " + "hedge:" + m_iHedge + ", " + "seatid:" + m_iSeatid + ", " + "sbanker:" + m_iSbanker + ", " + "videoline:" + m_strVideoline + ", " + "order:" + m_iOrder + ">";
	}
}