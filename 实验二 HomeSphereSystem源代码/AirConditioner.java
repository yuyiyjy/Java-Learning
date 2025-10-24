package cn.edu.nwpu.homesphere;

import java.util.Date;

/**
 * 空调设备类，继承自设备基类{@link Device}，并实现了能耗报告接口{@link EnergyReporting}。
 * <p>
 * 该类用于模拟空调设备的基本功能，包括温度控制、功率获取及能耗计算等。
 *
 * @author 余燚
 * @version 1.0
 */
public class AirConditioner extends Device implements EnergyReporting {
    /**
     * 空调的当前温度（单位：摄氏度）
     */
    private double currTemp;

    /**
     * 空调设置的目标温度（单位：摄氏度）
     */
    private double targetTemp;

    /**
     * 空调的额定功率（单位：瓦特），默认值为700W
     */
    private final double power = 700;

    /**
     * 构造方法：创建空调设备实例
     *
     * @param deviceId    设备唯一标识符
     * @param name        设备名称
     * @param manufacturer 设备制造商信息（{@link Manufacturer}对象）
     */
    public AirConditioner(int deviceId, String name, Manufacturer manufacturer) {
        super(deviceId, name, manufacturer);
    }

    /**
     * 获取空调当前温度
     *
     * @return 当前温度（单位：摄氏度）
     */
    public double getCurrTemp() {
        return currTemp;
    }

    /**
     * 设置空调当前温度
     *
     * @param currTemp 待设置的当前温度（单位：摄氏度）
     */
    public void setCurrTemp(double currTemp) {
        this.currTemp = currTemp;
    }

    /**
     * 获取空调的目标温度
     *
     * @return 目标温度（单位：摄氏度）
     */
    public double getTargetTemp() {
        return targetTemp;
    }

    /**
     * 设置空调的目标温度
     *
     * @param targetTemp 待设置的目标温度（单位：摄氏度）
     */
    public void setTargetTemp(double targetTemp) {
        this.targetTemp = targetTemp;
    }

    /**
     * 实现{@link EnergyReporting}接口的方法，获取空调当前功率
     * <p>
     * 若设备处于开机状态（{@link #isPowerStatus()}返回true），返回额定功率700W；
     * 若设备关机，返回0W。
     *
     * @return 当前功率（单位：瓦特）
     */
    @Override
    public double getPower() {
        if (this.isPowerStatus()) {
            return power;
        } else {
            return 0;
        }
    }

    /**
     * 实现{@link EnergyReporting}接口的方法，计算指定时间段内的空调能耗
     * <p>
     * 能耗计算公式：能耗（度）= 额定功率（kW）× 运行时间（小时）
     * 注：1度 = 1千瓦·时（1kW·h）
     *
     * @param startTime 统计开始时间
     * @param endTime   统计结束时间
     * @return 时间段内的总能耗（单位：度）
     * @throws IllegalArgumentException 若开始时间晚于结束时间，可能导致计算结果为负数
     */
    @Override
    public double getReport(Date startTime, Date endTime) {
        long startMs = startTime.getTime();
        long endMs = endTime.getTime();

        double diff = (double) (endMs - startMs) / (60 * 60 * 1000);

        return (power / 1000) * diff;
    }
}