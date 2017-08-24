package org.dream.message;

/**
 * 咪牌设定
 */
public class ChangeMiCardMessage extends TableMessage {
	/**
	 * 用户主键
	 */
	protected Long m_lUserid;

	public Long getUserid() {
		return m_lUserid;
	}

	public void setUserid(Long userid) {
		m_lUserid = userid;
	}

	/**
	 * 是否咪牌1是0否
	 */
	protected Integer m_iIsmiCard;

	public Integer getIsmiCard() {
		return m_iIsmiCard;
	}

	public void setIsmiCard(Integer ismiCard) {
		m_iIsmiCard = ismiCard;
	}

	/**
	 * 是否竖向1是0否
	 */
	protected Integer m_iAnyway;

	public Integer getAnyway() {
		return m_iAnyway;
	}

	public void setAnyway(Integer anyway) {
		m_iAnyway = anyway;
	}

	/**
	 * 是否咪庄闲 1是0否
	 */
	protected Integer m_iBanker;

	public Integer getBanker() {
		return m_iBanker;
	}

	public void setBanker(Integer banker) {
		m_iBanker = banker;
	}

	/**
	 * 是否同时咪庄闲牌 1是0否
	 */
	protected Integer m_iBankerAnd;

	public Integer getBankerAnd() {
		return m_iBankerAnd;
	}

	public void setBankerAnd(Integer bankerAnd) {
		m_iBankerAnd = bankerAnd;
	}

	/**
	 * 是否设置密码1是0否
	 */
	protected Integer m_iIsPasswd;

	public Integer getIsPasswd() {
		return m_iIsPasswd;
	}

	public void setIsPasswd(Integer isPasswd) {
		m_iIsPasswd = isPasswd;
	}

	/**
	 * 密码
	 */
	protected String m_strPasswd;

	public String getPasswd() {
		return m_strPasswd;
	}

	public void setPasswd(String passwd) {
		m_strPasswd = passwd;
	}

	public ChangeMiCardMessage() {
		this.m_iType = CHANGE_MICARD_MESSAGE;
	}

	public ChangeMiCardMessage(long tableid, long userid, int ismiCard, int anyway, int banker, int bankerAnd, int isPasswd, String passwd) {
		this.m_iType = CHANGE_MICARD_MESSAGE;
		m_lTableid = tableid;
		m_lUserid = userid;
		m_iIsmiCard = ismiCard;
		m_iAnyway = anyway;
		m_iBanker = banker;
		m_iBankerAnd = bankerAnd;
		m_iIsPasswd = isPasswd;
		m_strPasswd = passwd;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable {
		int iLength;
		m_lTableid = readLong(bytes);

		m_lUserid = readLong(bytes);

		m_iIsmiCard = readInt(bytes);

		m_iAnyway = readInt(bytes);

		m_iBanker = readInt(bytes);

		m_iBankerAnd = readInt(bytes);

		m_iIsPasswd = readInt(bytes);

		iLength = readInt(bytes);
		m_strPasswd = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable {
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += INT_SIZE;

		byte[] passwdBytes = null;
		passwdBytes = m_strPasswd == null ? null : m_strPasswd.getBytes("utf-8");
		iAllLength += INT_SIZE + (passwdBytes == null ? 0 : passwdBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = intToBytes(retBytes, iPos, m_iIsmiCard);

		iPos = intToBytes(retBytes, iPos, m_iAnyway);

		iPos = intToBytes(retBytes, iPos, m_iBanker);

		iPos = intToBytes(retBytes, iPos, m_iBankerAnd);

		iPos = intToBytes(retBytes, iPos, m_iIsPasswd);

		iPos = intToBytes(retBytes, iPos, passwdBytes == null ? 0 : passwdBytes.length);
		if (passwdBytes != null) {
			iPos = bytesToBytes(retBytes, iPos, passwdBytes, passwdBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString() {
		return "ChangeMiCardMessage<>";
	}

	public String toString() {
		return "ChangeMiCardMessage<" + "tableid:" + m_lTableid + ", " + "userid:" + m_lUserid + ", " + "ismiCard:" + m_iIsmiCard + ", " + "anyway:"
				+ m_iAnyway + ", " + "banker:" + m_iBanker + ", " + "bankerAnd:" + m_iBankerAnd + ", " + "isPasswd:" + m_iIsPasswd + ", " + "passwd:"
				+ m_strPasswd + ">";
	}
}