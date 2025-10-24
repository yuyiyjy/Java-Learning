package cn.edu.nwpu.homesphere;

import java.util.Date;

/**
 * 智能灯泡设备类，继承自设备基类{@link Device}，并实现能耗报告接口{@link EnergyReporting}。
 * <p>
 * 该类用于模拟智能灯泡的核心功能，包括亮度/色温调节、功率获取及指定时间段能耗计算，额定功率默认设为50W。
 *
 * @author 余燚
 * @version 1.0
 */
public class LightBulb extends Device implements EnergyReporting {
    /**
     * 灯泡当前亮度（建议取值范围：0-100，0表示最低亮度，100表示最高亮度）
     */
    private int brightness;

    /**
     * 灯泡当前色温（单位：开尔文/K，常见范围：2700K-6500K，如2700K为暖光，6500K为冷光）
     */
    private int colorTemp;

    /**
     * 灯泡额定功率（固定值50瓦特/W，不受亮度、色温调节影响）
     */
    private final double power = 50;

    /**
     * 构造方法：创建智能灯泡设备实例
     *
     * @param deviceId     设备唯一标识符
     * @param name         设备名称（如“客厅主灯”“卧室台灯”）
     * @param manufacturer 设备制造商信息（{@link Manufacturer}对象）
     */
    public LightBulb(int deviceId, String name, Manufacturer manufacturer) {
        super(deviceId, name, manufacturer);
    }

    /**
     * 获取灯泡当前亮度
     *
     * @return 亮度值（参考范围：0-100）
     */
    public int getBrightness() {
        return brightness;
    }

    /**
     * 设置灯泡亮度
     *
     * @param brightness 待设置的亮度值（建议控制在0-100范围内，避免超出硬件支持范围）
     */
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    /**
     * 获取灯泡当前色温
     *
     * @return 色温值（单位：开尔文/K）
     */
    public int getColorTemp() {
        return colorTemp;
    }

    /**
     * 设置灯泡色温
     *
     * @param colorTemp 待设置的色温值（建议控制在2700K-6500K常见范围，具体以硬件支持为准）
     */
    public void setColorTemp(int colorTemp) {
        this.colorTemp = colorTemp;
    }

    /**
     * 实现{@link EnergyReporting}接口方法，获取灯泡当前实际功率
     * <p>
     * 逻辑规则：若灯泡处于开机状态（{@link #isPowerStatus()}返回true），返回额定功率50W；
     * 若处于关机状态，返回0W（表示无功率消耗）。
     *
     * @return 当前功率（单位：瓦特/W）
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
     * 实现{@link EnergyReporting}接口方法，计算指定时间段内灯泡的总能耗
     * <p>
     * 能耗计算公式：能耗（度）= 额定功率（kW）× 运行时间（小时），其中1度 = 1千瓦·时（1kW·h）。
     * 注：该计算基于“开机期间持续以额定功率运行”的假设，未考虑亮度调节对实际功率的影响（若需精确计算需额外扩展）。
     *
     * @param startTime 能耗统计的开始时间
     * @param endTime   能耗统计的结束时间
     * @return 时间段内的总能耗（单位：度/kW·h）；若开始时间晚于结束时间，返回负数（需调用者自行处理异常场景）
     */
    @Override
    public double getReport(Date startTime, Date endTime) {
        long startMs = startTime.getTime();
        long endMs = endTime.getTime();

        double diff = (double) (endMs - startMs) / (60 * 60 * 1000);

        return (power / 1000) * diff;
    }
}