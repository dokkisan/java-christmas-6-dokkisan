package christmas.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTypeConverter {

    public static Map<String, Integer> convertToMapOfMenuItems(List<String> target) {
        Map<String, Integer> menuItems = new HashMap<>();
        for (int i = 0; i < target.size(); i += 2) {
            String menuName = target.get(i);
            int orderCount = Integer.parseInt(target.get(i + 1));
            menuItems.put(menuName, orderCount);
        }
        return menuItems;
    }
}
