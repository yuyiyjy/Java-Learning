package cn.edu.nwpu.homesphere;
import java.util.Date;

/**
 * 能耗报告接口，定义智能设备能耗相关的核心功能规范。
 * <p>
 * 实现此接口的设备（如空调、热水器等）需提供功率查询和指定时间段能耗计算的能力，
 * 为智能家居系统的能耗统计、用电分析提供统一的数据接口。
 *
 * @author 余燚
 * @version 1.0
 * @see AirConditioner
 */
public interface EnergyReporting {

    /**
     * 获取设备当前的功率值。
     * <p>
     * 功率值需反映设备当前运行状态下的实际功率（如开机时返回额定功率，关机时返回0），
     * 单位默认统一为瓦特（W），具体需与实现类保持一致。
     *
     * @return 设备当前功率（单位：瓦特/W）
     */
    double getPower();

    /**
     * 计算并获取设备在指定时间段内的总能耗报告。
     * <p>
     * 能耗计算需基于设备功率和实际运行时间，结果单位默认统一为度（1度 = 1千瓦·时，kW·h），
     * 若开始时间晚于结束时间，需由实现类明确异常处理逻辑或返回合理结果。
     *
     * @param startTime 能耗统计的开始时间
     * @param endTime   能耗统计的结束时间
     * @return 时间段内的总能耗（单位：度/kW·h）
     */
    double getReport(Date startTime, Date endTime);

}
