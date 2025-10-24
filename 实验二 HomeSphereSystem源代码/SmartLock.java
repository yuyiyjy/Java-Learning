package cn.edu.nwpu.homesphere;

/**
 * 智能锁设备类，继承自设备基类{@link Device}，用于模拟智能锁的核心状态管理功能。
 * <p>
 * 该类封装智能锁的核心属性——锁具开关状态和电池电量，支持状态查询与更新，适配智能家居系统的设备统一管理逻辑。
 *
 * @author 余燚
 * @version 1.0
 */
public class SmartLock extends Device {
    /**
     * 智能锁的开关状态：{@code true}表示锁定状态，{@code false}表示解锁状态
     */
    private boolean isLocked;

    /**
     * 智能锁的电池电量百分比：取值范围为0-100，0表示电量耗尽，100表示满电
     */
    private int batteryLevel;             //电池电量百分比

    /**
     * 构造方法：创建智能锁设备实例
     * <p>
     * 调用父类{@link Device}的构造方法初始化设备编号、名称和制造商，锁具初始状态（锁定/解锁）需通过{@link #setLocked(boolean)}手动设置。
     *
     * @param deviceId     设备唯一标识符
     * @param name         设备名称（如“前门智能锁”“卧室衣柜锁”）
     * @param manufacturer 设备制造商信息（{@link Manufacturer}对象）
     */
    public SmartLock(int deviceId, String name, Manufacturer manufacturer) {
        super(deviceId, name, manufacturer);
    }

    /**
     * 获取智能锁当前的开关状态
     *
     * @return {@code true}表示锁定，{@code false}表示解锁
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * 设置智能锁的开关状态（锁定或解锁）
     *
     * @param locked 目标状态：{@code true}设为锁定，{@code false}设为解锁
     */
    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    /**
     * 获取智能锁当前的电池电量百分比
     *
     * @return 电池电量百分比（0-100）
     */
    public int getBatteryLevel() {
        return batteryLevel;
    }

    /**
     * 设置智能锁的电池电量百分比
     * <p>
     * 建议传入0-100范围内的数值，超出范围可能导致电量显示异常（需由调用者确保参数合法性）。
     *
     * @param batteryLevel 目标电池电量百分比
     */
    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
}