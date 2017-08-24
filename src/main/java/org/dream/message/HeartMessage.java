package org.dream.message;



/**
 * 心跳接口 
 */
public class HeartMessage extends Message
{
	/**
	 * 心跳数 
	 */
	protected Byte m_iNum;
	public Byte getNum()
	{
		return m_iNum;
	}
	public void setNum(Byte num)
	{
		m_iNum = num;
	}

	public HeartMessage()
	{
		super(HEART_MESSAGE);
	}

	public HeartMessage(byte num)
	{
		super(HEART_MESSAGE);
		m_iNum = num;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		m_iNum = readByte(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += BYTE_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = byteToBytes(retBytes, iPos, m_iNum);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "HeartMessage<>";
	}

	public String toString()
	{
		return "HeartMessage<" + "num:" + m_iNum + ">";
	}
}