package cn.edu.nwpu.homesphere;

import java.util.List;
import java.util.ArrayList;

/**
 * 自动化场景类，用于管理智能家居中的场景配置及动作执行。
 * <p>
 * 一个场景可包含多个设备动作（{@link DeviceAction}），支持手动触发场景以执行所有关联动作，
 * 例如“早安场景”“离家模式”等典型智能家居场景。
 *
 * @author  余燚
 * @version 1.0
 */
public class AutomationScene {
    /**
     * 场景唯一编号（不可修改）
     */
    private int sceneId;

    /**
     * 场景名称，如“早安场景”“离家模式”“影院模式”等
     */
    private String name;

    /**
     * 场景的详细描述，用于说明场景的用途或执行效果
     */
    private String description;

    /**
     * 场景所包含的全部设备动作集合，初始化为空列表
     */
    private List<DeviceAction> actions = new ArrayList<DeviceAction>();

    /**
     * 无参构造方法，创建一个默认的自动化场景实例
     */
    public AutomationScene() {

    }

    /**
     * 有参构造方法，创建指定编号、名称和描述的自动化场景
     *
     * @param sceneId    场景唯一编号
     * @param name       场景名称（如“早安场景”）
     * @param description 场景的详细描述
     */
    public AutomationScene(int sceneId, String name, String description) {
        this.sceneId = sceneId;
        this.name = name;
        this.description = description;
    }

    /**
     * 获取场景的唯一编号
     *
     * @return 场景编号（int类型）
     */
    public int getSceneId() {
        return sceneId;
    }

    /**
     * 获取场景名称
     *
     * @return 场景名称（字符串）
     */
    public String getName() {
        return name;
    }

    /**
     * 设置场景名称
     *
     * @param name 新的场景名称（如“睡眠模式”）
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取场景的详细描述
     *
     * @return 场景描述（字符串）
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置场景的详细描述
     *
     * @param description 新的场景描述（说明场景用途或效果）
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 向当前场景添加一个设备动作
     *
     * @param action 要添加的设备动作（{@link DeviceAction}实例）
     */
    public void addAction(DeviceAction action) {
        this.actions.add(action);
    }

    /**
     * 从当前场景中移除指定的设备动作
     * <p>
     * 遍历动作集合，若找到与参数匹配的动作则移除，并输出操作结果；
     * 若未找到则提示“场景中没有这个动作”。
     *
     * @param action 要移除的设备动作（{@link DeviceAction}实例）
     */
    public void removeAction(DeviceAction action) {
        boolean isFound = false;
        for (DeviceAction deviceAction : this.actions) {
            if (deviceAction.equals(action)) {
                this.actions.remove(deviceAction);
                isFound = true;
            }
        }
        if (isFound) {
            System.out.println("移除成功！");
        } else {
            System.out.println("场景中没有这个动作");
        }
    }

    /**
     * 获取当前场景中包含的所有设备动作
     * <p>
     * 若场景中无动作，会打印提示信息并返回null；否则返回动作集合。
     *
     * @return 设备动作列表（{@link List}<{@link DeviceAction}>），无动作时返回null
     */
    public List<DeviceAction> getActions() {
        if (this.actions.isEmpty()) {
            System.out.println("场景中还没有动作，请添加");
            return null;
        } else {
            return actions;
        }
    }

    /**
     * 手动触发场景，执行场景中包含的所有设备动作
     * <p>
     * 触发时会打印场景名称和描述，并依次执行每个动作的{@link DeviceAction#execute()}方法。
     *
     * @throws NoSuchMethodException 若动作执行过程中出现方法调用异常（具体取决于{@link DeviceAction#execute()}的实现）
     */
    public void manualTrig() throws NoSuchMethodException {
        System.out.println("成功触发场景" + name + ",场景为：" + description + " ");
        System.out.println("执行场景的动作有：\n");
        for (DeviceAction deviceAction : actions) {
            deviceAction.execute();
        }
    }

    /**
     * 重写toString方法，返回场景的详细信息字符串
     *
     * @return 包含场景编号、名称、描述及动作集合的字符串
     */
    @Override
    public String toString() {
        return "AutomationScene{" +
                "sceneId=" + sceneId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", actions=" + actions +
                '}';
    }
}