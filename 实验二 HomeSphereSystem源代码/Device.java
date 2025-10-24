package cn.edu.nwpu.homesphere;

import java.util.ArrayList;
import java.util.List;


/**
 * 设备基类，定义所有智能设备的通用属性和行为。
 * <p>
 * 该类为智能家居系统中的各类设备（如空调、体重秤等）提供基础功能，包括设备标识、状态管理、
 * 电源控制及运行日志记录等。子类可继承此类扩展特定设备的功能。
 *
 * @author 余燚
 * @version 1.0
 * @see AirConditioner
 * @see LightBulb
 * @see SmartLock
 * @see BathroomScale
 */
public class Device {
    /**
     * 设备唯一编号（不可修改，用于设备身份标识）
     */
    private int deviceId;

    /**
     * 设备名称（如“客厅空调”“卧室体重秤”）
     */
    private String name;

    /**
     * 设备制造商信息（包含制造商名称、联系方式等）
     */
    private Manufacturer manufacturer;

    /**
     * 设备在线状态：{@code true}表示在线，{@code false}表示离线
     */
    private boolean isOnline;

    /**
     * 设备电源状态：{@code true}表示开机，{@code false}表示关机
     */
    private boolean powerStatus;

    /**
     * 设备运行日志集合，记录设备的操作历史（如开机、关机、状态变更等）
     */
    private List<RunningLog> runningLogs = new ArrayList<RunningLog>();

    /**
     * 无参构造方法，创建一个默认的设备实例。
     * <p>
     * 注意：使用此构造方法时需手动设置设备编号、名称等必要属性。
     */
    public Device() {

    }

    /**
     * 有参构造方法，创建指定编号、名称和制造商的设备实例。
     * <p>
     * 初始状态下，设备默认处于离线（{@code isOnline = false}）和关机（{@code powerStatus = false}）状态。
     *
     * @param deviceId     设备唯一编号
     * @param name         设备名称
     * @param manufacturer 设备制造商信息（{@link Manufacturer}对象）
     */
    public Device(int deviceId, String name, Manufacturer manufacturer) {
        this.deviceId = deviceId;
        this.name = name;
        this.manufacturer = manufacturer;
        this.isOnline = false;
        this.powerStatus = false;
    }

    /**
     * 获取设备唯一编号
     *
     * @return 设备编号（int类型）
     */
    public int getDeviceId() {
        return deviceId;
    }

    /**
     * 获取设备名称
     *
     * @return 设备名称（字符串）
     */
    public String getName() {
        return name;
    }

    /**
     * 设置设备名称
     *
     * @param name 新的设备名称（如“主卧空调”）
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取设备在线状态
     *
     * @return {@code true}表示在线，{@code false}表示离线
     */
    public boolean isOnline() {
        return isOnline;
    }

    /**
     * 设置设备在线状态
     *
     * @param online 新的在线状态：{@code true}为在线，{@code false}为离线
     */
    public void setOnline(boolean online) {
        isOnline = online;
    }

    /**
     * 获取设备电源状态
     *
     * @return {@code true}表示开机，{@code false}表示关机
     */
    public boolean isPowerStatus() {
        return powerStatus;
    }

    /**
     * 获取设备制造商信息
     *
     * @return 制造商对象（{@link Manufacturer}）
     */
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    /**
     * 设备开机操作
     * <p>
     * 调用此方法会将设备电源状态设置为开机（{@code powerStatus = true}）。
     * 注意：开机后需手动调用{@link #setOnline(boolean)}设置在线状态。
     */
    public void powerOn() {
        this.powerStatus = true;
    }

    /**
     * 设备关机操作
     * <p>
     * 调用此方法会将设备电源状态设置为关机（{@code powerStatus = false}），
     * 同时自动将在线状态设置为离线（{@code isOnline = false}）。
     */
    public void powerOff() {
        this.powerStatus = false;
        this.isOnline = false;
    }

    /**
     * 获取设备的运行日志列表
     *
     * @return 运行日志集合（{@link List}<{@link RunningLog}>）
     */
    public List<RunningLog> getRunningLogs() {
        return runningLogs;
    }

    /**
     * 向设备添加一条运行日志
     *
     * @param log 待添加的运行日志（{@link RunningLog}对象）
     */
    public void addRunningLogs(RunningLog log) {
        runningLogs.add(log);
    }

    /**
     * 重写equals方法，判断两个设备是否为同一设备（基于设备编号）
     *
     * @param o 待比较的对象
     * @return {@code true}如果是同一设备（设备编号相同），否则返回{@code false}
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return deviceId == device.deviceId;
    }

    /**
     * 重写toString方法，返回设备的核心信息字符串
     *
     * @return 包含设备编号、名称、在线状态和电源状态的字符串
     */
    @Override
    public String toString() {
        return "Device{" +
                "deviceId=" + deviceId +
                ", name='" + name + '\'' +
                ", isOnline=" + isOnline +
                ", powerStatus=" + powerStatus +
                '}';
    }
}