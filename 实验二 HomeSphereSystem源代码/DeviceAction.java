package cn.edu.nwpu.homesphere;

import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Method;

/**
 * 设备动作类，用于封装对智能设备的操作指令、参数及目标设备，支持通过反射执行设备动作。
 * <p>
 * 该类通过命令映射机制将抽象指令转换为设备实际支持的方法调用，实现对不同设备的统一动作管理。
 *
 * @author 余燚
 * @version 1.0
 */
public class DeviceAction {
    /**
     * 动作指令名称（如"setTemperature"表示设置温度）
     */
    private String command;

    /**
     * 动作执行所需的参数（字符串形式，需与设备方法参数类型匹配）
     */
    private String parameters;

    /**
     * 执行当前动作的目标设备（{@link Device}或其子类实例）
     */
    private Device device;

    /**
     * 无参构造方法，创建一个空的设备动作实例
     */
    public DeviceAction() {

    }

    /**
     * 有参构造方法，创建指定指令、参数和目标设备的动作实例
     *
     * @param command    动作指令（如"setTemperature"）
     * @param parameters 动作参数（字符串形式，如"26.5"）
     * @param device     目标设备（{@link Device}对象）
     */
    public DeviceAction(String command, String parameters, Device device) {
        this.command = command;
        this.parameters = parameters;
        this.device = device;
    }

    /**
     * 获取动作指令名称
     *
     * @return 指令名称（字符串）
     */
    public String getCommand() {
        return command;
    }

    /**
     * 设置动作指令名称
     *
     * @param command 新的指令名称（如"setPower"）
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * 获取动作参数
     *
     * @return 参数值（字符串形式）
     */
    public String getParameters() {
        return parameters;
    }

    /**
     * 设置动作参数
     *
     * @param parameters 新的参数值（字符串形式，需与设备方法参数兼容）
     */
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    /**
     * 获取执行动作的目标设备
     *
     * @return 目标设备（{@link Device}对象）
     */
    public Device getDevice() {
        return device;
    }

    /**
     * 设置执行动作的目标设备
     *
     * @param device 新的目标设备（{@link Device}对象）
     */
    public void setDevice(Device device) {
        this.device = device;
    }

    /**
     * 执行当前动作，通过反射调用目标设备的对应方法
     * <p>
     * 执行逻辑：
     * 1. 打印动作执行信息（指令、参数、设备）；
     * 2. 通过命令映射表将抽象指令转换为设备实际方法名（如"setTemperature"映射为"setTargetTem"）；
     * 3. 将字符串参数转换为double类型；
     * 4. 反射调用设备的目标方法并传入参数。
     *
     * @throws NoSuchMethodException 若映射的方法在设备类中不存在
     */
    public void execute() throws NoSuchMethodException {
        System.out.println("执行动作：" + this.command + ",参数为：" + this.parameters + "，执行的设备为：" + device.getName() + "\n");

        // 建立方法名映射表（抽象指令 -> 设备实际方法名）
        Map<String, String> methodMap = new HashMap<String, String>();
        methodMap.put("setTemperature", "setTargetTemp"); // 示例：温度设置指令映射

        // 从映射表中获取实际方法名
        String actualMethodName = methodMap.get(this.command);

        try {
            // 将字符串参数转换为double
            double param = Double.parseDouble(parameters);

            // 反射获取设备类中对应的方法（参数类型为double）
            Method method = device.getClass().getMethod(actualMethodName, double.class);

            // 调用方法，传入参数
            method.invoke(device, param);

        } catch (NumberFormatException e) {
            System.out.println("参数转换失败：" + parameters + " 不是有效的数字");
        } catch (Exception e) {
            e.printStackTrace(); // 处理反射调用中的其他异常（如方法不可访问、参数不匹配等）
        }
    }
}