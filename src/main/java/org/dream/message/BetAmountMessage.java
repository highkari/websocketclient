package org.dream.message;

import java.io.*;


/**
 * 某个台投注点和金额信息
 */
public class BetAmountMessage extends TableMessage
{
	/**
	 * 投注点
	 */
	protected String[] m_strPoint;
	public String[] getPoint()
	{
		return m_strPoint;
	}
	public void setPoint(String[] point)
	{
		m_strPoint = point;
	}

	/**
	 * 投注点总投注人数
	 */
	protected Integer[] m_iUsertotal;
	public Integer[] getUsertotal()
	{
		return m_iUsertotal;
	}
	public void setUsertotal(Integer[] usertotal)
	{
		m_iUsertotal = usertotal;
	}

	/**
	 * 投注点投注金额总和
	 */
	protected Double[] m_dAmount;
	public Double[] getAmount()
	{
		return m_dAmount;
	}
	public void setAmount(Double[] amount)
	{
		m_dAmount = amount;
	}

	/**
	 * 雀九公司
	 */
	protected Double[] m_dBankerCom;
	public Double[] getBankerCom()
	{
		return m_dBankerCom;
	}
	public void setBankerCom(Double[] bankerCom)
	{
		m_dBankerCom = bankerCom;
	}

	/**
	 * 雀九地庄
	 */
	protected Double[] m_dBankerMem;
	public Double[] getBankerMem()
	{
		return m_dBankerMem;
	}
	public void setBankerMem(Double[] bankerMem)
	{
		m_dBankerMem = bankerMem;
	}

	public BetAmountMessage()
	{
		this.m_iType = BETAMOUNT_MESSAGE;
	}
	
	public BetAmountMessage(long tableid, String[] point, Integer[] usertotal, Double[] amount)
	{
		this.m_iType = BETAMOUNT_MESSAGE;
		m_lTableid = tableid;
		m_strPoint = point;
		m_iUsertotal = usertotal;
		m_dAmount = amount;
		m_lMessageID = super.createMessageId();
	}

	public BetAmountMessage(long tableid, String[] point, Integer[] usertotal, Double[] amount, Double[] bankerCom, Double[] bankerMem)
	{
		this.m_iType = BETAMOUNT_MESSAGE;
		m_lTableid = tableid;
		m_strPoint = point;
		m_iUsertotal = usertotal;
		m_dAmount = amount;
		m_dBankerCom = bankerCom;
		m_dBankerMem = bankerMem;
		m_lMessageID = super.createMessageId();
	}

	public void byteToMessage(byte[] bytes) throws Throwable
	{
		int iLength;
		int iArrLength;
		m_lTableid = readLong(bytes);

		iArrLength = readInt(bytes);
		m_strPoint = iArrLength == 0 ? null : new String[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			iLength = readInt(bytes);
			m_strPoint[i] = iLength == 0 ? "" : new String(readBytes(bytes, iLength), "utf-8");

		}
		iArrLength = readInt(bytes);
		m_iUsertotal = iArrLength == 0 ? null : new Integer[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			m_iUsertotal[i] = readInt(bytes);

		}
		iArrLength = readInt(bytes);
		m_dAmount = iArrLength == 0 ? null : new Double[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			m_dAmount[i] = readDouble(bytes);

		}
		iArrLength = readInt(bytes);
		m_dBankerCom = iArrLength == 0 ? null : new Double[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			m_dBankerCom[i] = readDouble(bytes);

		}
		iArrLength = readInt(bytes);
		m_dBankerMem = iArrLength == 0 ? null : new Double[iArrLength];
		for (int i = 0; i < iArrLength; i++)
		{
			m_dBankerMem[i] = readDouble(bytes);

		}
		m_lMessageID = readLong(bytes);
	}

	public byte[] messageToByte() throws Throwable
	{
		int iAllLength = LONG_SIZE;

		iAllLength += LONG_SIZE;

		byte[][] pointBytes = m_strPoint != null ? new byte[m_strPoint.length][] : null;
		iAllLength += INT_SIZE;
		for (int i = 0; m_strPoint != null && i < m_strPoint.length; i++)
		{
			pointBytes[i] = m_strPoint[i] == null ? null : m_strPoint[i].getBytes("utf-8");
			iAllLength += INT_SIZE + (pointBytes[i] == null ? 0 : pointBytes[i].length);

		}
		iAllLength += INT_SIZE;
		for (int i = 0; m_iUsertotal != null && i < m_iUsertotal.length; i++)
		{
			iAllLength += INT_SIZE;

		}
		iAllLength += INT_SIZE;
		for (int i = 0; m_dAmount != null && i < m_dAmount.length; i++)
		{
			iAllLength += DOUBLE_SIZE;

		}
		iAllLength += INT_SIZE;
		for (int i = 0; m_dBankerCom != null && i < m_dBankerCom.length; i++)
		{
			iAllLength += DOUBLE_SIZE;

		}
		iAllLength += INT_SIZE;
		for (int i = 0; m_dBankerMem != null && i < m_dBankerMem.length; i++)
		{
			iAllLength += DOUBLE_SIZE;

		}
		byte[] retBytes = new byte[INT_SIZE + INT_SIZE + iAllLength];
		int iPos = intToBytes(retBytes, 0, m_iType);
		iPos = intToBytes(retBytes, iPos, iAllLength);

		iPos = longToBytes(retBytes, iPos, m_lTableid);

		iPos = intToBytes(retBytes, iPos, m_strPoint != null ? m_strPoint.length : 0);
		for (int i = 0; m_strPoint != null && i < m_strPoint.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, pointBytes[i] == null ? 0 : pointBytes[i].length);
			if (pointBytes[i] != null)
			{
				iPos = bytesToBytes(retBytes, iPos, pointBytes[i], pointBytes[i].length);
			}

		}
		iPos = intToBytes(retBytes, iPos, m_iUsertotal != null ? m_iUsertotal.length : 0);
		for (int i = 0; m_iUsertotal != null && i < m_iUsertotal.length; i++)
		{
			iPos = intToBytes(retBytes, iPos, m_iUsertotal[i]);

		}
		iPos = intToBytes(retBytes, iPos, m_dAmount != null ? m_dAmount.length : 0);
		for (int i = 0; m_dAmount != null && i < m_dAmount.length; i++)
		{
			iPos = doubleToBytes(retBytes, iPos, m_dAmount[i]);

		}
		iPos = intToBytes(retBytes, iPos, m_dBankerCom != null ? m_dBankerCom.length : 0);
		for (int i = 0; m_dBankerCom != null && i < m_dBankerCom.length; i++)
		{
			iPos = doubleToBytes(retBytes, iPos, m_dBankerCom[i]);

		}
		iPos = intToBytes(retBytes, iPos, m_dBankerMem != null ? m_dBankerMem.length : 0);
		for (int i = 0; m_dBankerMem != null && i < m_dBankerMem.length; i++)
		{
			iPos = doubleToBytes(retBytes, iPos, m_dBankerMem[i]);

		}
		iPos = longToBytes(retBytes, iPos, m_lMessageID);
		return retBytes;
	}

	public String toEigenString()
	{
		return "BetAmountMessage<>";
	}

	public String toString()
	{
		return "BetAmountMessage<" + "tableid:" + m_lTableid + ", " + "point:" + java.util.Arrays.toString(m_strPoint)  + ", " + "usertotal:" + java.util.Arrays.toString(m_iUsertotal) + ", " + "amount:" + java.util.Arrays.toString(m_dAmount) + ", " + "bankerCom:" + java.util.Arrays.toString(m_dBankerCom) + ", " + "bankerMem:" + java.util.Arrays.toString(m_dBankerMem) + ">";
	}
}