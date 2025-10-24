package cn.edu.nwpu.homesphere;

import java.util.List;
import java.util.ArrayList;

/**
 * 房间类，用于封装智能家居系统中的房间信息及房间内的设备集合，是设备的物理归属容器。
 * <p>
 * 该类管理房间的基本属性（编号、名称、面积），并提供设备的添加、移除及查询功能，建立“房间-设备”的物理关联关系。
 *
 * @author 余燚
 * @version 1.0
 */
public class Room {
    /**
     * 房间唯一编号（不可修改，用于区分家庭内不同房间）
     */
    private int roomId;

    /**
     * 房间名称（如“客厅”“主卧”“厨房”“书房”等，反映房间功能或位置）
     */
    private String name;

    /**
     * 房间面积（单位默认：平方米/㎡，用于空间维度的设备规划）
     */
    private double area;

    /**
     * 房间内所有设备的集合（如客厅的空调、台灯，卧室的体重秤等），初始化为空列表
     */
    private List<Device> devices = new ArrayList<Device>();

    /**
     * 无参构造方法，创建一个默认的房间实例
     * <p>
     * 注意：使用此构造后需手动设置房间编号、名称等核心属性，否则无法完整标识房间信息。
     */
    public Room() {

    }

    /**
     * 有参构造方法，创建指定编号、名称和面积的房间实例
     *
     * @param roomId 房间唯一编号
     * @param name   房间名称（如“客厅”）
     * @param area   房间面积（单位：平方米/㎡）
     */
    public Room(int roomId, String name, double area) {
        this.roomId = roomId;
        this.name = name;
        this.area = area;
    }

    /**
     * 获取房间唯一编号
     *
     * @return 房间编号（int类型）
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * 获取房间名称
     *
     * @return 房间名称（字符串）
     */
    public String getName() {
        return name;
    }

    /**
     * 设置房间名称（如房间功能变更时更新，例：“储物间”改为“书房”）
     *
     * @param name 新的房间名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取房间面积
     *
     * @return 房间面积（单位：平方米/㎡）
     */
    public double getArea() {
        return area;
    }

    /**
     * 设置房间面积（如房屋改造后更新面积数据）
     *
     * @param area 新的房间面积（单位：平方米/㎡）
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * 获取房间内所有设备的集合
     * <p>
     * 若房间内无设备，会打印“房间里没有设备，请添加”的提示信息并返回null；否则返回设备列表。
     *
     * @return 设备列表（{@link List}<{@link Device}>），无设备时返回null
     */
    public List<Device> getDevices() {
        if (devices.isEmpty()) {
            System.out.println("房间里没有设备，请添加");
            return null;
        }
        return devices;
    }

    /**
     * 向房间内添加一个设备（建立设备与房间的物理归属关系）
     *
     * @param device 待添加的设备（{@link Device}或其子类实例，如空调、灯泡等）
     */
    public void addDevice(Device device) {
        this.devices.add(device);
    }

    /**
     * 根据设备编号从房间内移除指定设备
     * <p>
     * 通过设备编号匹配目标设备，移除前记录原设备数量，移除后对比数量判断操作结果：
     * - 移除成功：打印“成功移除设备号为X的设备，共移除Y台”；
     * - 移除失败（未找到设备）：打印“未找到设备号为X的设备，操作执行失败”。
     *
     * @param deviceId 待移除设备的唯一编号
     */
    public void removeDevice(int deviceId) {
        int originalSize = this.devices.size();
        devices.removeIf(device -> device.getDeviceId() == deviceId);

        if (originalSize > devices.size()) {
            System.out.println("成功移除设备号为" + deviceId + "的设备，共移除" + (originalSize - devices.size()) + "台");
        } else {
            System.out.println("未找到设备号为" + deviceId + "的设备，操作执行失败");
        }
    }

    /**
     * 重写equals方法，判断两个房间是否为同一房间（基于房间唯一编号匹配）
     *
     * @param o 待比较的对象
     * @return {@code true}若为同一房间（房间编号相同），否则返回{@code false}
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomId == room.roomId;
    }

    /**
     * 重写toString方法，返回房间的完整信息字符串
     *
     * @return 包含房间编号、名称、面积及设备集合的字符串（设备信息依赖{@link Device#toString()}）
     */
    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", devices=" + devices +
                '}';
    }
}