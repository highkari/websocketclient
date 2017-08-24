package org.dream.message;

import java.io.*;


/**
 * 登录结果消息
 */
public class LoginResponseMessage extends UserMessage
{
	/**
	 * 登录结果反馈
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

	/**
	 * 登录名
	 */
	protected String m_strName;
	public String getName()
	{
		return m_strName;
	}
	public void setName(String name)
	{
		m_strName = name;
	}

	/**
	 * 显示名称
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
	 * 账户余额
	 */
	protected Double m_dAccount;
	public Double getAccount()
	{
		return m_dAccount;
	}
	public void setAccount(Double account)
	{
		m_dAccount = account;
	}

	/**
	 * 视频地址
	 */
	protected String[] m_strVideoaddress;
	public String[] getVideoaddress()
	{
		return m_strVideoaddress;
	}
	public void setVideoaddress(String[] videoaddress)
	{
		m_strVideoaddress = videoaddress;
	}

	/**
	 * 服务器IP
	 */
	protected String m_strServerip;
	public String getServerip()
	{
		return m_strServerip;
	}
	public void setServerip(String serverip)
	{
		m_strServerip = serverip;
	}

	/**
	 * 服务器端口
	 */
	protected Long m_lServerport;
	public Long getServerport()
	{
		return m_lServerport;
	}
	public void setServerport(Long serverport)
	{
		m_lServerport = serverport;
	}

	/**
	 * 服务安全沙箱端口
	 */
	protected Long m_lPolicyport;
	public Long getPolicyport()
	{
		return m_lPolicyport;
	}
	public void setPolicyport(Long policyport)
	{
		m_lPolicyport = policyport;
	}

	/**
	 * 登录key
	 */
	protected String m_strCode;
	public String getCode()
	{
		return m_strCode;
	}
	public void setCode(String code)
	{
		m_strCode = code;
	}

	/**
	 * 是否首次登陆  0、是 1、否
	 */
	protected Byte m_iFirstlanding;
	public Byte getFirstlanding()
	{
		return m_iFirstlanding;
	}
	public void setFirstlanding(Byte firstlanding)
	{
		m_iFirstlanding = firstlanding;
	}

	/**
	 * 昵称
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
	 * 是否开通打赏 0是关闭，1是开启
	 */
	protected Integer m_iIsGive;
	public Integer getIsGive()
	{
		return m_iIsGive;
	}
	public void setIsGive(Integer isGive)
	{
		m_iIsGive = isGive;
	}

	/**
	 *  平台类型  0-是其它平台，1-自用信用网平台 
	 */
	protected Byte m_iPlatformType;
	public Byte getPlatformType()
	{
		return m_iPlatformType;
	}
	public void setPlatformType(Byte platformType)
	{
		m_iPlatformType = platformType;
	}

	/**
	 * 视频点播地址
	 */
	protected String m_strVODaddress;
	public String getVODaddress()
	{
		return m_strVODaddress;
	}
	public void setVODaddress(String VODaddress)
	{
		m_strVODaddress = VODaddress;
	}

	/**
	 * 是否白名单用户
	 */
	protected Integer m_iIsWhiteList;
	public Integer getIsWhiteList()
	{
		return m_iIsWhiteList;
	}
	public void setIsWhiteList(Integer isWhiteList)
	{
		m_iIsWhiteList = isWhiteList;
	}

	public LoginResponseMessage()
	{
		this.m_iType = LOGIN_RESPONSE_MESSAGE;
	}

	public LoginResponseMessage(long userid, int result, String name, String showname, double account, String[] videoaddress, String serverip, long serverport, long policyport, String code, byte firstlanding, String nickname, int isGive, byte platformType, String VODaddress, int isWhiteList)
	{
		this.m_iType = LOGIN_RESPONSE_MESSAGE;
		m_lUserid = userid;
		m_iResult = result;
		m_strName = name;
		m_strShowname = showname;
		m_dAccount = account;
		m_strVideoaddress = videoaddress;
		m_strServerip = serverip;
		m_lServerport = serverport;
		m_lPolicyport = policyport;
		m_strCode = code;
		m_iFirstlanding = firstlanding;
		m_strNickname = nickname;
		m_iIsGive = isGive;
		m_iPlatformType = platformType;
		m_strVODaddress = VODaddress;
		m_iIsWhiteList = isWhiteList;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		int iArrLength;
		m_lUserid = readLong(bytes);

		m_iResult = readInt(bytes);

		iLength = readInt(bytes);
		m_strName = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strShowname = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_dAccount = readDouble(bytes);

		iArrLength = readInt(bytes);
		m_strVideoaddress = iArrLength == 0 ? null : new String[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			iLength = readInt(bytes);
			m_strVideoaddress[i] = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		}
		iLength = readInt(bytes);
		m_strServerip = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lServerport = readLong(bytes);

		m_lPolicyport = readLong(bytes);

		iLength = readInt(bytes);
		m_strCode = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iFirstlanding = readByte(bytes);

		iLength = readInt(bytes);
		m_strNickname = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iIsGive = readInt(bytes);

		m_iPlatformType = readByte(bytes);

		iLength = readInt(bytes);
		m_strVODaddress = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_iIsWhiteList = readInt(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += INT_SIZE;

		byte[] nameBytes = null;
		nameBytes = m_strName == null ? null : m_strName.getBytes("utf-8");
		iAllLength += INT_SIZE + (nameBytes == null ? 0 : nameBytes.length);

		byte[] shownameBytes = null;
		shownameBytes = m_strShowname == null ? null : m_strShowname.getBytes("utf-8");
		iAllLength += INT_SIZE + (shownameBytes == null ? 0 : shownameBytes.length);

		iAllLength += DOUBLE_SIZE;

		byte[][] videoaddressBytes = m_strVideoaddress != null ? new byte[m_strVideoaddress.length][] : null;
		iAllLength += INT_SIZE;
		for (int i = 0; m_strVideoaddress != null && i < m_strVideoaddress.length; i++)
		{
			videoaddressBytes[i] = m_strVideoaddress[i] == null ? null : m_strVideoaddress[i].getBytes("utf-8");
			iAllLength += INT_SIZE + (videoaddressBytes[i] == null ? 0 : videoaddressBytes[i].length);

		}
		byte[] serveripBytes = null;
		serveripBytes = m_strServerip == null ? null : m_strServerip.getBytes("utf-8");
		iAllLength += INT_SIZE + (serveripBytes == null ? 0 : serveripBytes.length);

		iAllLength += LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[] codeBytes = null;
		codeBytes = m_strCode == null ? null : m_strCode.getBytes("utf-8");
		iAllLength += INT_SIZE + (codeBytes == null ? 0 : codeBytes.length);

		iAllLength += BYTE_SIZE;

		byte[] nicknameBytes = null;
		nicknameBytes = m_strNickname == null ? null : m_strNickname.getBytes("utf-8");
		iAllLength += INT_SIZE + (nicknameBytes == null ? 0 : nicknameBytes.length);

		iAllLength += INT_SIZE;

		iAllLength += BYTE_SIZE;

		byte[] VODaddressBytes = null;
		VODaddressBytes = m_strVODaddress == null ? null : m_strVODaddress.getBytes("utf-8");
		iAllLength += INT_SIZE + (VODaddressBytes == null ? 0 : VODaddressBytes.length);

		iAllLength += INT_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lUserid);

		iPos = intToBytes(retBytes, iPos, m_iResult);

		iPos = intToBytes(retBytes, iPos, nameBytes == null ? 0 : nameBytes.length);
		if (nameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, nameBytes, nameBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, shownameBytes == null ? 0 : shownameBytes.length);
		if (shownameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, shownameBytes, shownameBytes.length);
		}

		iPos = doubleToBytes(retBytes, iPos, m_dAccount);

		iPos = intToBytes(retBytes, iPos, m_strVideoaddress != null ? m_strVideoaddress.length : 0);
		for (int i = 0; m_strVideoaddress != null && i < m_strVideoaddress.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, videoaddressBytes[i] == null ? 0 : videoaddressBytes[i].length);
			if (videoaddressBytes[i] != null)
			{
				iPos = bytesToBytes(retBytes, iPos, videoaddressBytes[i], videoaddressBytes[i].length);
			}

		}
		iPos = intToBytes(retBytes, iPos, serveripBytes == null ? 0 : serveripBytes.length);
		if (serveripBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, serveripBytes, serveripBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lServerport);

		iPos = longToBytes(retBytes, iPos, m_lPolicyport);

		iPos = intToBytes(retBytes, iPos, codeBytes == null ? 0 : codeBytes.length);
		if (codeBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, codeBytes, codeBytes.length);
		}

		iPos = byteToBytes(retBytes, iPos, m_iFirstlanding);

		iPos = intToBytes(retBytes, iPos, nicknameBytes == null ? 0 : nicknameBytes.length);
		if (nicknameBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, nicknameBytes, nicknameBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, m_iIsGive);

		iPos = byteToBytes(retBytes, iPos, m_iPlatformType);

		iPos = intToBytes(retBytes, iPos, VODaddressBytes == null ? 0 : VODaddressBytes.length);
		if (VODaddressBytes != null)
		{
			iPos = bytesToBytes(retBytes, iPos, VODaddressBytes, VODaddressBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, m_iIsWhiteList);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "LoginResponseMessage<>";
	}

	public String toString()
	{
		return "LoginResponseMessage<" + "userid:" + m_lUserid + ", " + "result:" + m_iResult + ", " + "name:" + m_strName + ", " + "showname:" + m_strShowname + ", " + "account:" + m_dAccount + ", " + "videoaddress:" + m_strVideoaddress + ", " + "serverip:" + m_strServerip + ", " + "serverport:" + m_lServerport + ", " + "policyport:" + m_lPolicyport + ", " + "code:" + m_strCode + ", " + "firstlanding:" + m_iFirstlanding + ", " + "nickname:" + m_strNickname + ", " + "isGive:" + m_iIsGive + ", " + "platformType:" + m_iPlatformType + ", " + "VODaddress:" + m_strVODaddress + ", " + "isWhiteList:" + m_iIsWhiteList + ">";
	}
}