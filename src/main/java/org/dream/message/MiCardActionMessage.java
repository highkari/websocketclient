package org.dream.message;

import java.io.*;


/**
 *  咪牌动作消息 
 */
public class MiCardActionMessage extends TableMessage
{
	/**
	 *  开牌时的变量，发牌顺序
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

	/**
	 *  鼠标X坐标  
	 */
	protected Double m_dMouseX;
	public Double getMouseX()
	{
		return m_dMouseX;
	}
	public void setMouseX(Double mouseX)
	{
		m_dMouseX = mouseX;
	}

	/**
	 *  鼠标Y坐标 
	 */
	protected Double m_dMouseY;
	public Double getMouseY()
	{
		return m_dMouseY;
	}
	public void setMouseY(Double mouseY)
	{
		m_dMouseY = mouseY;
	}

	/**
	 *  翻牌的角度（角度 和 边缘翻牌，共8个） 
	 */
	protected Integer m_iARROW;
	public Integer getARROW()
	{
		return m_iARROW;
	}
	public void setARROW(Integer ARROW)
	{
		m_iARROW = ARROW;
	}

	public MiCardActionMessage()
	{
		this.m_iType = MICARD_ACTION_MESSAGE;
	}

	public MiCardActionMessage(long tableid, int order, double mouseX, double mouseY, int ARROW)
	{
		this.m_iType = MICARD_ACTION_MESSAGE;
		m_lTableid = tableid;
		m_iOrder = order;
		m_dMouseX = mouseX;
		m_dMouseY = mouseY;
		m_iARROW = ARROW;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		m_lTableid = readLong(bytes);

		m_iOrder = readInt(bytes);

		m_dMouseX = readDouble(bytes);

		m_dMouseY = readDouble(bytes);

		m_iARROW = readInt(bytes);

		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		iAllLength += INT_SIZE;

		iAllLength += DOUBLE_SIZE;

		iAllLength += DOUBLE_SIZE;

		iAllLength += INT_SIZE;

		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, m_iOrder);

		iPos = doubleToBytes(retBytes, iPos, m_dMouseX);

		iPos = doubleToBytes(retBytes, iPos, m_dMouseY);

		iPos = intToBytes(retBytes, iPos, m_iARROW);

		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "MiCardActionMessage<>";
	}

	public String toString()
	{
		return "MiCardActionMessage<" + "tableid:" + m_lTableid + ", " + "order:" + m_iOrder + ", " + "mouseX:" + m_dMouseX + ", " + "mouseY:" + m_dMouseY + ", " + "ARROW:" + m_iARROW + ">";
	}
}