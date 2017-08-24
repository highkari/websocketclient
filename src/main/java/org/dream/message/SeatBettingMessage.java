package org.dream.message;

import java.io.*;

public class SeatBettingMessage extends TableMessage
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
	 * 用户昵称
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

	/**
	 * 座位组号
	 */
	protected Long m_lGroupid;
	public Long getGroupid()
	{
		return m_lGroupid;
	}
	public void setGroupid(Long groupid)
	{
		m_lGroupid = groupid;
	}

	/**
	 * 投注点
	 */
	protected String m_strOption;
	public String getOption()
	{
		return m_strOption;
	}
	public void setOption(String option)
	{
		m_strOption = option;
	}

	/**
	 * 用户在虚拟房间中的座次号，以左起第一个为1
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
	 * 下注额
	 */
	protected Double m_dAmount;
	public Double getAmount()
	{
		return m_dAmount;
	}
	public void setAmount(Double amount)
	{
		m_dAmount = amount;
	}

	/**
	 * 筹码详情
	 */
	protected Double m_dChips;
	public Double getChips()
	{
		return m_dChips;
	}
	public void setChips(Double chips)
	{
		m_dChips = chips;
	}

	/**
	 * 闲咪牌权座位号,庄咪牌权桌位号（默认-1,-1）
	 */
	protected String m_strMiseat;
	public String getMiseat()
	{
		return m_strMiseat;
	}
	public void setMiseat(String miseat)
	{
		m_strMiseat = miseat;
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
	 * 用户对应币种
	 */
	protected Long m_lCurrencyid;
	public Long getCurrencyid()
	{
		return m_lCurrencyid;
	}
	public void setCurrencyid(Long currencyid)
	{
		m_lCurrencyid = currencyid;
	}

	public SeatBettingMessage()
	{
		this.m_iType = SEAT_BETTING_MESSAGE;
	}

	public SeatBettingMessage(long tableid, long userid, String nickname, long groupid, String option, int seatid, double amount, double chips, String miseat, int sbanker, long currencyid)
	{
		this.m_iType = SEAT_BETTING_MESSAGE;
		m_lTableid = tableid;
		m_lUserid = userid;
		m_strNickname = nickname;
		m_lGroupid = groupid;
		m_strOption = option;
		m_iSeatid = seatid;
		m_dAmount = amount;
		m_dChips = chips;
		m_strMiseat = miseat;
		m_iSbanker = sbanker;
		m_lCurrencyid = currencyid;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		m_lTableid = readLong(bytes);

		m_lUserid = readLong(bytes);

		iLength = readInt(bytes);
		m_strNickname = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lGroupid = readLong(bytes);

		iLength = readInt(bytes);
		m_strOption = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iSeatid = readInt(bytes);

		m_dAmount = readDouble(bytes);

		m_dChips = readDouble(bytes);

		iLength = readInt(bytes);
		m_strMiseat = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iSbanker = readInt(bytes);

		m_lCurrencyid = readLong(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] nicknameBytes = null;
		nicknameBytes = m_strNickname == null ? null : m_strNickname.getBytes("utf-8");
		iAllLength += INT_SIZE + (nicknameBytes == null ? 0 : nicknameBytes.length);

		iAllLength += LONG_SIZE;

		byte[] optionBytes = null;
		optionBytes = m_strOption == null ? null : m_strOption.getBytes("utf-8");
		iAllLength += INT_SIZE + (optionBytes == null ? 0 : optionBytes.length);

		iAllLength += INT_SIZE;

		iAllLength += DOUBLE_SIZE;

		iAllLength += DOUBLE_SIZE;

		byte[] miseatBytes = null;
		miseatBytes = m_strMiseat == null ? null : m_strMiseat.getBytes("utf-8");
		iAllLength += INT_SIZE + (miseatBytes == null ? 0 : miseatBytes.length);

		iAllLength += INT_SIZE;

		iAllLength += LONG_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = intToBytes(retBytes, iPos, nicknameBytes == null ? 0 : nicknameBytes.length);
		if (nicknameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, nicknameBytes, nicknameBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lGroupid);

		iPos = intToBytes(retBytes, iPos, optionBytes == null ? 0 : optionBytes.length);
		if (optionBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, optionBytes, optionBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, m_iSeatid);

		iPos = doubleToBytes(retBytes, iPos, m_dAmount);

		iPos = doubleToBytes(retBytes, iPos, m_dChips);

		iPos = intToBytes(retBytes, iPos, miseatBytes == null ? 0 : miseatBytes.length);
		if (miseatBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, miseatBytes, miseatBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, m_iSbanker);

		iPos = longToBytes(retBytes, iPos, m_lCurrencyid);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "SeatBettingMessage<>";
	}

	public String toString()
	{
		return "SeatBettingMessage<" + "tableid:" + m_lTableid + ", " + "userid:" + m_lUserid + ", " + "nickname:" + m_strNickname + ", " + "groupid:" + m_lGroupid + ", " + "option:" + m_strOption + ", " + "seatid:" + m_iSeatid + ", " + "amount:" + m_dAmount + ", " + "chips:" + m_dChips + ", " + "miseat:" + m_strMiseat + ", " + "sbanker:" + m_iSbanker + ", " + "currencyid:" + m_lCurrencyid + ">";
	}
}