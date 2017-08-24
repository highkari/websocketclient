package org.dream.message;

public class ErrorMessage extends Message {
	protected Integer m_iErrorid;// -41 扫牌器错误

	public Integer getErrorid() {
		return m_iErrorid;
	}

	public void setErrorid(Integer errorid) {
		m_iErrorid = errorid;
	}

	protected String m_strDesc;

	public String getDesc() {
		return m_strDesc;
	}

	public void setDesc(String desc) {
		m_strDesc = desc;
	}

	public ErrorMessage() {
		super(ERROR_MESSAGE);
	}

	public ErrorMessage(int errorid, String desc) {
		super(ERROR_MESSAGE);
		m_iErrorid = errorid;
		m_strDesc = desc;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable {
		int iLength;
		m_iErrorid = readInt(bytes);

		iLength = readInt(bytes);
		m_strDesc = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable {
		int iAllLength = LONG_SIZE;

		iAllLength += INT_SIZE;

		byte[] descBytes = null;
		descBytes = m_strDesc == null ? null : m_strDesc.getBytes("utf-8");
		iAllLength += INT_SIZE + (descBytes == null ? 0 : descBytes.length);

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = intToBytes(retBytes, iPos, m_iErrorid);

		iPos = intToBytes(retBytes, iPos, descBytes == null ? 0 : descBytes.length);
		if (descBytes != null) {
			iPos = bytesToBytes(retBytes, iPos, descBytes, descBytes.length);
		}

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString() {
		return "ErrorMessage<>";
	}

	public String toString() {
		return "ErrorMessage<" + "errorid:" + m_iErrorid + ", " + "desc:" + m_strDesc + ">";
	}
}