package cn.edu.nwpu.homesphere;

/**
 * 浴室体重秤设备类，继承自设备基类{@link Device}。
 * <p>
 * 该类用于模拟体重秤设备的基本功能，包括体重数据记录和电池电量管理。
 *
 * @author 余燚
 * @version 1.0
 */
public class BathroomScale extends Device {
    /**
     * 记录的体重数据（单位：千克，kg）
     */
    private double bodyMass;

    /**
     * 电池电量百分比（范围：0-100，其中0表示电量耗尽，100表示满电）
     */
    private int batteryLevel;

    /**
     * 构造方法：创建体重秤设备实例
     *
     * @param deviceId     设备唯一标识符
     * @param name         设备名称（如“智能体重秤”）
     * @param manufacturer 设备制造商信息（{@link Manufacturer}对象）
     */
    public BathroomScale(int deviceId, String name, Manufacturer manufacturer) {
        super(deviceId, name, manufacturer);
    }

    /**
     * 获取当前记录的体重数据
     *
     * @return 体重值（单位：千克，kg）
     */
    public double getBodyMass() {
        return bodyMass;
    }

    /**
     * 设置体重数据（通常由设备测量后自动更新）
     *
     * @param bodyMass 待记录的体重值（单位：千克，kg）
     */
    public void setBodyMass(double bodyMass) {
        this.bodyMass = bodyMass;
    }

    /**
     * 获取电池当前电量百分比
     *
     * @return 电量百分比（0-100）
     */
    public int getBatteryLevel() {
        return batteryLevel;
    }

    /**
     * 设置电池电量百分比（通常由设备自检后更新）
     *
     * @param batteryLevel 电池电量百分比（需在0-100范围内）
     */
    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
}