package org.dream.message;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.SocketTimeoutException;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public abstract class Message {
	private static final HashMap<Integer, Class> MESSAGE_MAP = new HashMap<Integer, Class>();

	public static final int ERROR_MESSAGE = 0;
	static {
		MESSAGE_MAP.put(ERROR_MESSAGE, ErrorMessage.class);
	}
	// 状态消息
	public static final int STATE_MESSAGE = 1;
	// 聊天消息
	public static final int CHAT_MESSAGE = 2;
	static {
		MESSAGE_MAP.put(CHAT_MESSAGE, ChatMessage.class);
	}
	// 登录消息
	public static final int LOGIN_MESSAGE = 3;
	static {
		MESSAGE_MAP.put(LOGIN_MESSAGE, LoginMessage.class);
	}
	// 登录回应消息
	public static final int LOGIN_RESPONSE_MESSAGE = 4;
	static {
		MESSAGE_MAP.put(LOGIN_RESPONSE_MESSAGE, LoginResponseMessage.class);
	}
	// 大厅消息
	public static final int LOBBY_MESSAGE = 5;
	static {
		MESSAGE_MAP.put(LOBBY_MESSAGE, LobbyMessage.class);
	}
	// 进入大厅消息
	public static final int ENTER_LOBBY_MESSAGE = 6;
	static {
		MESSAGE_MAP.put(ENTER_LOBBY_MESSAGE, EnterLobbyMessage.class);
	}
	// 某个台的信息，包括历史数据，用于绘制路纸
	public static final int TABLE_INFO_MESSAGE = 7;
	static {
		MESSAGE_MAP.put(TABLE_INFO_MESSAGE, TableInfoMessage.class);
	}
	// 选台消息
	public static final int CHOOSE_TABLE_MESSAGE = 8;
	static {
		MESSAGE_MAP.put(CHOOSE_TABLE_MESSAGE, ChooseTableMessage.class);
	}
	// 在某个台坐下消息
	public static final int ENTER_TABLE_MESSAGE = 9;
	static {
		MESSAGE_MAP.put(ENTER_TABLE_MESSAGE, EnterTableMessage.class);
	}
	// 下注点消息
	public static final int BET_POINT_MESSAGE = 10;
	static {
		MESSAGE_MAP.put(BET_POINT_MESSAGE, BetPointMessage.class);
	}
	// 接受投注消息
	public static final int BET_START_MESSAGE = 11;
	static {
		MESSAGE_MAP.put(BET_START_MESSAGE, BetStartMessage.class);
	}
	// 下注消息
	public static final int BET_MESSAGE = 12;
	static {
		MESSAGE_MAP.put(BET_MESSAGE, BetMessage.class);
	}
	// 下注回应消息
	public static final int BET_RESPONSE_MESSAGE = 13;
	static {
		MESSAGE_MAP.put(BET_RESPONSE_MESSAGE, BetResponseMessage.class);
	}
	// 派牌消息
	public static final int DEAL_START_MESSAGE = 14;
	static {
		MESSAGE_MAP.put(DEAL_START_MESSAGE, DealStartMessage.class);
	}
	// 某个台的荷官给某个座位发了一张牌
	public static final int SEAT_DEAL_MESSAGE = 15;
	static {
		MESSAGE_MAP.put(SEAT_DEAL_MESSAGE, SeatDealMessage.class);
	}
	// 某个台的某个座位上的牌面大小
	public static final int SEAT_RESULT_MESSAGE = 16;
	static {
		MESSAGE_MAP.put(SEAT_RESULT_MESSAGE, SeatResultMessage.class);
	}
	// 当前局结果消息
	public static final int BET_RESULT_MESSAGE = 17;
	static {
		MESSAGE_MAP.put(BET_RESULT_MESSAGE, BetResultMessage.class);
	}
	// //操作消息
	// public static final int OPERATING_MESSAGE = 18;
	// static {
	// MESSAGE_MAP.put(OPERATING_MESSAGE, OperatingMessage.class);
	// }
	// 离开某个台消息
	public static final int LEAVE_TABLE_MESSAGE = 19;
	static {
		MESSAGE_MAP.put(LEAVE_TABLE_MESSAGE, LeaveTableMessage.class);
	}
	// 输赢对账信息
	public static final int RECONCILIATION_MESSAGE = 20;
	static {
		MESSAGE_MAP.put(RECONCILIATION_MESSAGE, ReconciliationMessage.class);
	}
	// 台消息，所有与台相关消息的父类
	public static final int TABLE_MESSAGE = 21;
	static {
		MESSAGE_MAP.put(TABLE_MESSAGE, TableMessage.class);
	}
	// 用户消息
	public static final int USER_MESSAGE = 22;
	static {
		MESSAGE_MAP.put(USER_MESSAGE, UserMessage.class);
	}

	// 会员选组
	public static final int CHOOSE_GROUP_MESSAGE = 23;
	static {
		MESSAGE_MAP.put(CHOOSE_GROUP_MESSAGE, ChooseGroupMessage.class);
	}
	// 某台上某组上某作词的下注情况
	public static final int SEAT_BETTING_MESSAGE = 24;
	static {
		MESSAGE_MAP.put(SEAT_BETTING_MESSAGE, SeatBettingMessage.class);
	}

	public static final int SUBCOUNTBALANCE_MESSAGE = 25;
	static {
		MESSAGE_MAP.put(SUBCOUNTBALANCE_MESSAGE, SubCountBalanceMessage.class);
	}

	public static final int BETAMOUNT_MESSAGE = 26;
	static {
		MESSAGE_MAP.put(BETAMOUNT_MESSAGE, BetAmountMessage.class);
	}

	public static final int CHOOSE_BANKER_MESSAGE = 27;
	static {
		MESSAGE_MAP.put(CHOOSE_BANKER_MESSAGE, ChooseBankerMessage.class);
	}

	public static final int USER_GAMERECORD_MESSAGE = 28;
	static {
		MESSAGE_MAP.put(USER_GAMERECORD_MESSAGE, UserGameRecordMessage.class);
	}

	public static final int COMPBANKER_MESSAGE = 29;
	static {
		MESSAGE_MAP.put(COMPBANKER_MESSAGE, CompBankerMessage.class);
	}

	public static final int ONLINE_MESSAGE = 30;
	static {
		MESSAGE_MAP.put(ONLINE_MESSAGE, OnlineMessage.class);
	}

	// 大厅公告和游戏厅公告
	public static final int NOTICE_MESSAGE = 31;
	static {
		MESSAGE_MAP.put(NOTICE_MESSAGE, NoticeMessage.class);
	}

	// 对战密码验证
	public static final int BATTLE_VP_MESSAGE = 32;
	static {
		MESSAGE_MAP.put(BATTLE_VP_MESSAGE, BattleVPMessage.class);
	}

	// 现场操作消息
	public static final int DEALER_OP_MESSAGE = 33;
	static {
		MESSAGE_MAP.put(DEALER_OP_MESSAGE, DealerOpMessage.class);
	}

	// 现场登录消息
	public static final int DEALER_LOGIN_MESSAGE = 34;
	static {
		MESSAGE_MAP.put(DEALER_LOGIN_MESSAGE, DealerLoginMessage.class);
	}

	// 现场登录响应消息
	public static final int DEALER_LOGIN_RES_MESSAGE = 35;
	static {
		MESSAGE_MAP.put(DEALER_LOGIN_RES_MESSAGE, DealerLoginResMessage.class);
	}

	// 服务心跳消息
	public static final int HEART_MESSAGE = 37;// 心跳
	static {
		MESSAGE_MAP.put(HEART_MESSAGE, HeartMessage.class);
	}

	// 包桌消息
	public static final int JOBDESK_MESSAGE = 38;// 包桌
	static {
		MESSAGE_MAP.put(JOBDESK_MESSAGE, JobDeskMessage.class);
	}

	public static final int CHANGE_MICARD_MESSAGE = 39;// 咪牌设定
	static {
		MESSAGE_MAP.put(CHANGE_MICARD_MESSAGE, ChangeMiCardMessage.class);
	}

	public static final int MASTER_OP_MESSAGE = 40;// 桌主操作消息
	static {
		MESSAGE_MAP.put(MASTER_OP_MESSAGE, MasterOpMessage.class);
	}

	public static final int MICARD_ACTION_MESSAGE = 41;// 咪牌动作消息
	static {
		MESSAGE_MAP.put(MICARD_ACTION_MESSAGE, MiCardActionMessage.class);
	}

	public static final int TABLE_INSTANT_MESSAGE = 42;// 桌主操作消息
	static {
		MESSAGE_MAP.put(TABLE_INSTANT_MESSAGE, TableInstantMessage.class);
	}

	public static final int DEALER_TABLEINFO_MESSAGE = 43;// dealer 台状态消息
	static {
		MESSAGE_MAP.put(DEALER_TABLEINFO_MESSAGE, DealerTableInfoMessage.class);
	}

	public static final int DEALER_ASSIGN_MESSAGE = 44;// dealer 端台操作消息（vip） 
	static {
		MESSAGE_MAP.put(DEALER_ASSIGN_MESSAGE, DealerAssignMessage.class);
	}

	public static final int MODIFY_NICKNAME_MESSAGE = 45;// 修改昵称消息
	static {
		MESSAGE_MAP.put(MODIFY_NICKNAME_MESSAGE, ModifyNickNameMessage.class);
	}

	public static final int APPLY_HANDLE_MESSAGE = 46;// 竞咪申请操作消息
	static {
		MESSAGE_MAP.put(APPLY_HANDLE_MESSAGE, ApplyHandleMessage.class);
	}
	
	public static final int BET_ING_MESSAGE = 47;// 用户准备进行投注, 未确定筹码
	static {
		MESSAGE_MAP.put(BET_ING_MESSAGE, UserBetingMessage.class);
	}
	
	public static final int USER_GIVE_MESSAGE = 48;// 用户打赏消息
	static {
		MESSAGE_MAP.put(USER_GIVE_MESSAGE, UserGiveMessage.class);
	}
	
	public static final int APPOINT_GAMBLING_RESULT_MESSAGE = 49;// 指定游戏局的结果
	static {
		MESSAGE_MAP.put(APPOINT_GAMBLING_RESULT_MESSAGE, AppointGamblingResultMessage.class);
	}

	public static final int D_APPLY_HANDLE_MESSAGE = 50;// dealer&主播申请操作消息
	static {
		MESSAGE_MAP.put(D_APPLY_HANDLE_MESSAGE, DApplyHandleMessage.class);
	}
	
	// 现场操作消息
	public static final int DEALER_OP_MESSAGE_OPCARD = 51;
	static {
		MESSAGE_MAP.put(DEALER_OP_MESSAGE_OPCARD, DealerOpMessage.class);
	}
	
	public static final int POKER_STYLE_MESSAGE = 52;// 荷官选牌背风格
	static {
		MESSAGE_MAP.put(POKER_STYLE_MESSAGE, PokerStyleMessage.class);
	}
	
	public static final int CURRENCY_MESSAGE = 53;// 币种消息
	static {
		MESSAGE_MAP.put(CURRENCY_MESSAGE, CurrencyMessage.class);
	}


	/**
	 * dealer端代码重构出，今后统一消息从101开始。
	 */
	// dealer展示聊天的消息
	public static final int D_CHAT_MESSAGE = 101;
	static {
		MESSAGE_MAP.put(D_CHAT_MESSAGE, DChatMessage.class);
	}
	// dealer可视图消息
	public static final int D_VISUAL_MESSAGE = 102;
	static {
		MESSAGE_MAP.put(D_VISUAL_MESSAGE, DVisualMessage.class);
	}
	public static final int D_MICARD_ACTION_MESSAGE = 103;// 咪牌动作消息
	static {
		MESSAGE_MAP.put(D_MICARD_ACTION_MESSAGE, DMiCardActionMessage.class);
	}
	// 某个台的荷官给某个座位发了一张牌
	public static final int D_SEAT_DEAL_MESSAGE = 104;
	static {
		MESSAGE_MAP.put(D_SEAT_DEAL_MESSAGE, DSeatDealMessage.class);
	}
	
	//保存视频消息
	public static final int D_SAVE_VIDEO_MESSAGE = 105;
	static {
		MESSAGE_MAP.put(D_SAVE_VIDEO_MESSAGE, DSaveVideoMessage.class);
	}
	
	//读取游戏局状态
	public static final int D_GAMBLING_STATE_MESSAGE = 106;
	static {
		MESSAGE_MAP.put(D_GAMBLING_STATE_MESSAGE, GamblingStateMessage.class);
	}	

	public static final int M_MODIFY_VERIFY_MESSAGE = 107;
	static {
		MESSAGE_MAP.put(M_MODIFY_VERIFY_MESSAGE, ModifyPWMessage.class);
	}
	
	public static final int D_UPDATE_SEAT_MESSAGE = 108;
	static {
		MESSAGE_MAP.put(D_UPDATE_SEAT_MESSAGE, DUpdateSeatMessage.class);
	}
	
	// 服务内部通信消息
	public static final int INTERNAL_MESSAGE = 1001;// ScanlerLoginMessage
	static {
		MESSAGE_MAP.put(INTERNAL_MESSAGE, InternalMessage.class);
	}
	

	// protected static final HashMap<Integer, Class> REGISTER = new
	// HashMap<Integer, Class>();

	protected static final int BYTE_SIZE = 1;
	protected static final int INT_SIZE = 4;
	protected static final int LONG_SIZE = 8;
	protected static final int FLOAT_SIZE = 4;
	protected static final int DOUBLE_SIZE = 8;

	private static final Random ran = new Random();

	// 消息来源IP，如果是收到的消息，为发送者IP，否则为null
	protected transient String m_strSourceIP;

	protected transient int m_iReadCursor = 0;

	public transient Message m_oAttachedMsg = null;

	protected transient byte[] bytesToSend = null;

	// 消息类型
	protected int m_iType;
	// 消息的Id(一条消息的唯一标识)
	protected long m_lMessageID;

	public Message(int iType) {
		m_iType = iType;
	}

	public void clear() {
		m_strSourceIP = null;
		m_oAttachedMsg = null;
		bytesToSend = null;
	}

	public int getType() {
		return m_iType;
	}

	public long getMessageID() {
		return m_lMessageID;
	}

	public String getSourceIP() {
		return m_strSourceIP;
	}

	protected void intToOS(OutputStream o, int i) throws IOException {
		o.write((byte) (i >> 24));
		o.write((byte) (i >> 16));
		o.write((byte) (i >> 8));
		o.write((byte) (i));
	}

	public static Message newInstance(int iType) {
		Message oMessage = null;
		Class msgClass = MESSAGE_MAP.get(iType);
		if (msgClass != null) {
			try {
				oMessage = (Message) msgClass.newInstance();
			} catch (Throwable e) {
				throw new RuntimeException("new instance of [" + msgClass + "] failed :", e);
			}
		} else {
			throw new RuntimeException("unknown message type: " + iType);
		}
		return oMessage;
	}

	protected static void writeInt(SocketChannel oChannel, int i) throws IOException {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (i >> 24);
		bytes[1] = (byte) (i >> 16);
		bytes[2] = (byte) (i >> 8);
		bytes[3] = (byte) (i);

		oChannel.socket().getOutputStream().write(bytes);
	}

	public static void readFully(SocketChannel oChannel, InputStream is, byte[] bytes) throws IOException {
		int iBytes = 0;
		while (iBytes < bytes.length) {
			int n = 0;
			try {
				n = is.read(bytes, iBytes, bytes.length - iBytes);
			} catch (SocketTimeoutException e) {
				throw new RuntimeException("Broken channel [" + oChannel + "], want " + bytes.length + " bytes, but get " + iBytes + " !", e);
			}
			if (n <= 0) {
				throw new RuntimeException("Broken channel (n == " + n + ") [" + oChannel + "], want " + bytes.length + " bytes, but get " + iBytes + " !");
			}
			iBytes += n;
		}
	}

	public static int readInt(byte[] bytes, int iCursor) {
		return bytes[iCursor + 3] & 0x000000ff | ((bytes[iCursor + 2] << 8) & 0x0000ff00) | ((bytes[iCursor + 1] << 16) & 0x00ff0000)
				| ((bytes[iCursor] << 24) & 0xff000000);
	}

	protected byte readByte(byte[] bytes) {
		return bytes[m_iReadCursor++];
	}

	protected int readInt(byte[] bytes) {
		int i = readInt(bytes, m_iReadCursor);
		m_iReadCursor += 4;
		return i;
	}

	protected long readLong(byte[] bytes) {
		return (((long) readInt(bytes)) << 32 & 0xffffffff00000000l) | (readInt(bytes) & 0x00000000ffffffffl);
	}

	protected float readFloat(byte[] bytes) {
		return Float.intBitsToFloat(readInt(bytes));
	}

	protected double readDouble(byte[] bytes) {
		return Double.longBitsToDouble(readLong(bytes));
	}

	protected Object readObject(byte[] bytes) throws IOException, ClassNotFoundException {
		int iLength = readInt(bytes);
		if (iLength > 0) {
			ObjectInputStream oIn = new ObjectInputStream(new ByteArrayInputStream(bytes, m_iReadCursor, iLength));
			m_iReadCursor += iLength;
			return oIn.readObject();
		}

		return null;
	}

	protected byte[] readBytes(byte[] bytes, int iLength) {
		byte[] retBytes = new byte[iLength];
		System.arraycopy(bytes, m_iReadCursor, retBytes, 0, iLength);
		m_iReadCursor += iLength;
		return retBytes;
	}

	protected int byteToBytes(byte[] bytes, int iStart, int b) {
		bytes[iStart] = (byte) b;
		return iStart + 1;
	}

	protected int intToBytes(byte[] bytes, int iStart, int i) {
		bytes[iStart] = (byte) (i >> 24);
		bytes[iStart + 1] = (byte) (i >> 16);
		bytes[iStart + 2] = (byte) (i >> 8);
		bytes[iStart + 3] = (byte) i;
		return iStart + 4;
	}

	protected int longToBytes(byte[] bytes, int iStart, long l) {
		iStart = intToBytes(bytes, iStart, (int) (l >> 32));
		return intToBytes(bytes, iStart, (int) l);
	}

	protected int floatToBytes(byte[] bytes, int iStart, float f) {
		return intToBytes(bytes, iStart, Float.floatToIntBits(f));
	}

	protected int doubleToBytes(byte[] bytes, int iStart, double d) {
		return longToBytes(bytes, iStart, Double.doubleToLongBits(d));
	}

	protected int bytesToBytes(byte[] bytes, int iStart, byte[] srcBytes, int iLength) {
		if (iLength <= 0) {
			return iStart;
		}
		System.arraycopy(srcBytes, 0, bytes, iStart, iLength);
		return iStart + iLength;
	}

	protected long createMessageId() {
		return System.currentTimeMillis() * 10000 + ran.nextInt(10000);
	}

	public String showMessageID(long lMessageID) {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
		return s.format(new Date(lMessageID / 10000)) + "|" + (lMessageID % 10000);
	}

	public String toString() {
		StringBuffer oBuf = new StringBuffer(this.getClass().getName()).append("<type:").append(m_iType).append(", id:").append(showMessageID(m_lMessageID))
				.append(">");
		return oBuf.toString();
	}

	public String toDetailedString() {
		StringBuffer oBuf = new StringBuffer(this.getClass().getName()).append("<id=").append(showMessageID(m_lMessageID));

		Field[] oFieldArr = this.getClass().getDeclaredFields();
		int iLength = oFieldArr == null ? 0 : oFieldArr.length;
		for (int i = 0; i < iLength; i++) {
			if (Modifier.isStatic(oFieldArr[i].getModifiers()) || Modifier.isPrivate(oFieldArr[i].getModifiers())) {
				continue;
			} else {
				try {
					oBuf.append(", ").append(oFieldArr[i].getName()).append("=").append(oFieldArr[i].get(this));
				} catch (Throwable e) {
					oBuf.append("***ERROR***").append(e).append("***ERROR***");
				}
			}
		}

		return oBuf.append(">").toString();
	}

	public abstract void byteToMessage(byte[] bytes) throws Throwable;

	public abstract String toEigenString();

	public abstract byte[] messageToByte() throws Throwable;

	
	public static Map<Integer, Class> getMap()
	{
		return MESSAGE_MAP;
	}
}
