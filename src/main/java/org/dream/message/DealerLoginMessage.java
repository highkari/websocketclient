package org.dream.message;

/**
 * dealerduan登录接口
 */
public class DealerLoginMessage extends Message {
	/**
	 * 用户名
	 */
	protected String m_strUserName;

	public String getUserName() {
		return m_strUserName;
	}

	public void setUserName(String userName) {
		m_strUserName = userName;
	}

	/**
	 * 密码
	 */
	protected String m_strPwd;

	public String getPwd() {
		return m_strPwd;
	}

	public void setPwd(String pwd) {
		m_strPwd = pwd;
	}

	/**
	 * 机器码
	 */
	protected String m_strMachineCode;

	public String getMachineCode() {
		return m_strMachineCode;
	}

	public void setMachineCode(String machineCode) {
		m_strMachineCode = machineCode;
	}

	public DealerLoginMessage() {
		super(DEALER_LOGIN_MESSAGE);
	}

	public DealerLoginMessage(String userName, String pwd, String machineCode) {
		super(DEALER_LOGIN_MESSAGE);
		m_strUserName = userName;
		m_strPwd = pwd;
		m_strMachineCode = machineCode;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable {
		int iLength;
		iLength = readInt(bytes);
		m_strUserName = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strPwd = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		iLength = readInt(bytes);
		m_strMachineCode = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable {
		int iAllLength = LONG_SIZE;

		byte[] userNameBytes = null;
		userNameBytes = m_strUserName == null ? null : m_strUserName.getBytes("utf-8");
		iAllLength += INT_SIZE + (userNameBytes == null ? 0 : userNameBytes.length);

		byte[] pwdBytes = null;
		pwdBytes = m_strPwd == null ? null : m_strPwd.getBytes("utf-8");
		iAllLength += INT_SIZE + (pwdBytes == null ? 0 : pwdBytes.length);

		byte[] machineCodeBytes = null;
		machineCodeBytes = m_strMachineCode == null ? null : m_strMachineCode.getBytes("utf-8");
		iAllLength += INT_SIZE + (machineCodeBytes == null ? 0 : machineCodeBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = intToBytes(retBytes, iPos, userNameBytes == null ? 0 : userNameBytes.length);
		if (userNameBytes != null) {
			iPos = bytesToBytes(retBytes, iPos, userNameBytes, userNameBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, pwdBytes == null ? 0 : pwdBytes.length);
		if (pwdBytes != null) {
			iPos = bytesToBytes(retBytes, iPos, pwdBytes, pwdBytes.length);
		}

		iPos = intToBytes(retBytes, iPos, machineCodeBytes == null ? 0 : machineCodeBytes.length);
		if (machineCodeBytes != null) {
			iPos = bytesToBytes(retBytes, iPos, machineCodeBytes, machineCodeBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString() {
		return "DealerLoginMessage<>";
	}

	public String toString() {
		return "DealerLoginMessage<" + "userName:" + m_strUserName + ", " + "pwd:" + m_strPwd + ", " + "machineCode:" + m_strMachineCode + ">";
	}
}