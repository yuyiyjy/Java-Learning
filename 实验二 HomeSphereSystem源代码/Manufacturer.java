package cn.edu.nwpu.homesphere;

import java.util.List;
import java.util.ArrayList;

/**
 * 设备制造商类，用于封装设备生产商的基础信息及该厂商生产的所有设备。
 * <p>
 * 该类管理制造商的唯一标识、名称、支持的通信协议，并提供设备的添加、移除及查询功能，
 * 建立“制造商-设备”的关联关系，支持对厂商生产设备的全生命周期管理。
 *
 * @author 余燚
 * @version 1.0
 */
public class Manufacturer {
    /**
     * 制造商唯一编号（不可修改，用于区分不同生产商）
     */
    private int manufacturerId;

    /**
     * 制造商名称（如“小米”“海尔”“格力”等）
     */
    private String name;

    /**
     * 制造商支持的通信协议（如“WiFi”“蓝牙”“ZigBee”，多协议用逗号或其他符号分隔）
     */
    private String protocols;

    /**
     * 该制造商生产的所有设备集合，初始化为空列表
     */
    private List<Device> devices = new ArrayList<Device>();

    /**
     * 无参构造方法，创建一个默认的制造商实例
     * <p>
     * 注意：使用此构造后需通过有参构造或手动补充信息，否则制造商编号、名称等核心属性为空。
     */
    public Manufacturer() {

    }

    /**
     * 有参构造方法，创建指定编号、名称和支持协议的制造商实例
     *
     * @param manufacturerId 制造商唯一编号
     * @param name           制造商名称
     * @param protocols      支持的通信协议（如“WiFi,ZigBee”）
     */
    public Manufacturer(int manufacturerId, String name, String protocols) {
        this.manufacturerId = manufacturerId;
        this.name = name;
        this.protocols = protocols;
    }

    /**
     * 获取制造商唯一编号
     *
     * @return 制造商编号（int类型）
     */
    public int getManufacturerId() {
        return manufacturerId;
    }

    /**
     * 获取制造商名称
     *
     * @return 制造商名称（字符串）
     */
    public String getName() {
        return name;
    }

    /**
     * 获取制造商支持的通信协议
     *
     * @return 支持的协议（字符串，多协议需按约定格式解析）
     */
    public String getProtocols() {
        return protocols;
    }

    /**
     * 向当前制造商添加一个其生产的设备
     *
     * @param device 待添加的设备（{@link Device}或其子类实例，需确保设备确实由该厂商生产）
     */
    public void addDevice(Device device) {
        devices.add(device);
    }

    /**
     * 从当前制造商的设备集合中移除指定设备
     * <p>
     * 先遍历设备集合判断目标设备是否存在：存在则移除，不存在则打印“当前制造商所制造的设备里没有[设备名]”的提示。
     * 设备匹配逻辑依赖{@link Device#equals(Object)}方法（基于设备编号匹配）。
     *
     * @param device 待移除的设备（{@link Device}或其子类实例）
     */
    public void removeDevice(Device device) {
        boolean found = false;
        for (Device device1 : devices) {
            if (device1.equals(device)) {
                found = true;
                break;
            }
        }
        if (found) {
            devices.remove(device);
        } else {
            System.out.println("当前制造商所制造的设备里没有" + device.getName());
        }
    }

    /**
     * 获取当前制造商生产的所有设备集合
     * <p>
     * 若制造商无生产设备，会打印“当前制造商没有设备”的提示信息，返回设备列表。
     *
     * @return 设备列表（{@link List}<{@link Device}>），无设备时返回null
     */
    public List<Device> getDevices() {
        if(devices.isEmpty()) {
            System.out.println("当前制造商没有设备");
        }
        return devices;
    }
}