package cn.edu.nwpu.homesphere;
import java.util.Date;

/**
 * 设备运行日志类，用于记录智能设备的运行事件（如开机、状态变更、异常等）及相关信息。
 * <p>
 * 该类通过时间、事件描述、事件类型（INFO/WARNING/ERROR）和备注，完整记录设备运行轨迹，
 * 支持通过枚举或数字两种方式设置事件类型，便于日志的分类查询和问题排查。
 *
 * @author 余燚
 * @version 1.0
 */
public class RunningLog {
    /**
     * 日志记录的具体时间（精确到毫秒，反映事件发生的时间点）
     */
    private Date dateTime;

    /**
     * 日志对应的事件描述（如“设备开机”“温度设置为26℃”“网络连接失败”）
     */
    private String event;

    /**
     * 事件类型编码：0表示INFO（普通信息），1表示WARNING（警告），2表示ERROR（错误）
     * <p>
     * 编码与内部枚举{@link Type}一一对应，可通过枚举或直接传入数字设置。
     */
    private int type;

    /**
     * 日志备注信息（补充事件细节，如“开机触发源为用户手动操作”“错误原因：密码验证失败”）
     */
    private String note;

    /**
     * 日志事件类型枚举，定义日志的标准化类型及对应编码。
     * <p>
     * 枚举值与{@link #type}字段的编码严格对应，避免类型值混乱。
     */
    enum Type{
        /** 普通信息日志，对应编码0（如设备正常状态变更、操作记录） */
        INFO(0),
        /** 警告日志，对应编码1（如低电量提醒、参数超出建议范围） */
        WARNING(1),
        /** 错误日志，对应编码2（如设备故障、功能执行失败） */
        ERROR(2);

        /** 事件类型对应的数字编码（与{@link RunningLog#type}字段值一致） */
        public final int code;

        /**
         * 枚举构造方法，绑定类型与编码
         * @param code 事件类型对应的数字编码
         */
        Type(int code){
            this.code = code;
        }

        /**
         * 获取枚举类型对应的数字编码
         * @return 事件类型编码（0/1/2）
         */
        int getCode(){
            return this.code;
        }

    }

    /**
     * 无参构造方法，创建一个默认的运行日志实例
     * <p>
     * 注意：使用此构造后需通过有参构造或手动设置字段值，否则日志信息不完整。
     */
    public RunningLog(){

    }

    /**
     * 有参构造方法1：通过数字编码设置事件类型，创建运行日志实例
     * <p>
     * 适合已知类型编码（0/1/2）的场景，类型编码需与{@link Type}枚举对应，否则日志类型可能异常。
     *
     * @param dateTime 日志记录时间（{@link Date}对象）
     * @param event    事件描述（如“设备关机”）
     * @param type     事件类型编码（0=INFO，1=WARNING，2=ERROR）
     * @param note     备注信息（补充事件细节）
     */
    RunningLog(Date dateTime, String event, int type, String note) {
        this.dateTime = dateTime;
        this.event = event;
        this.type = type;
        this.note = note;
    }

    /**
     * 有参构造方法2：通过{@link Type}枚举设置事件类型，创建运行日志实例
     * <p>
     * 推荐使用此构造，通过枚举确保类型值合法，自动关联对应的数字编码，避免手动传错编码。
     *
     * @param dateTime 日志记录时间（{@link Date}对象）
     * @param event    事件描述（如“设备开机”）
     * @param type     事件类型（{@link Type}枚举值，如Type.INFO）
     * @param note     备注信息（补充事件细节）
     */
    RunningLog(Date dateTime,String event,Type type,String note) {
        this.dateTime = dateTime;
        this.event = event;
        this.type = type.getCode();
        this.note = note;
    }

    /**
     * 获取日志记录时间
     * @return 日志时间（{@link Date}对象）
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * 获取事件描述
     * @return 事件描述字符串（如“温度调节至24℃”）
     */
    public String getEvent() {
        return event;
    }

    /**
     * 根据传入的int类型的type获取字符串型的Type
     *
     * @return 返回对应的字符串型tType
     */
    public Type getType() {

        return switch (type) {
            case 0 -> Type.INFO;
            case 1 -> Type.WARNING;
            case 2 -> Type.ERROR;
            default -> null;
        };

    }

    /**
     * 获取日志备注信息
     * @return 备注字符串（补充事件细节）
     */
    public String getNote() {
        return note;
    }
}