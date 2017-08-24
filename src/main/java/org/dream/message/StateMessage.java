package org.dream.message;

import java.nio.ByteBuffer;


public class StateMessage extends Message
{
	public static final int CONTINUE = 0;
	public static final int CLOSE = 1;
	
	protected int m_iState;

	public StateMessage()
	{
		super(STATE_MESSAGE);
	}
	
	public StateMessage(Message oRequest, int oState)
	{
		super(STATE_MESSAGE);
		m_iState = oState;
		if (oRequest != null)
		{
			m_lMessageID = oRequest.getMessageID();
		}
	}
	
	public void byteToMessage(byte[] bytes) throws Throwable
	{
		m_iState = readInt(bytes);
		m_lMessageID = readLong(bytes);
	}

	
	public byte[] messageToByte()
	{
		int iAllLength =  INT_SIZE + LONG_SIZE;
		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);
		iPos = intToBytes(retBytes, iPos, m_iState);
		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		
		return retBytes;
	}

//	public ByteBuffer messageToByte()
//	{
//		int oAllLength =  INT_SIZE + LONG_SIZE;
//		ByteBuffer o_ByteBuffer = ByteBuffer.allocate(INT_SIZE + INT_SIZE + oAllLength);
//		o_ByteBuffer.putInt(m_iType).putInt(oAllLength).putInt(m_iState).putLong(m_lMessageID);
//		return o_ByteBuffer;
//	}
//
	public int getState()
	{
		return m_iState;
	}

	public void clear()
	{
		super.clear();
	}

	@Override
	public String toEigenString()
	{
		return null;
	}
}
