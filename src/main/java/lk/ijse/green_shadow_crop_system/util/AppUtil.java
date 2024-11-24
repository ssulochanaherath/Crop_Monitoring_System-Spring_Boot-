package lk.ijse.green_shadow_crop_system.util;

import java.util.UUID;

public class AppUtil {
    public static String generateUserId(){
        return "USER-" + UUID.randomUUID();
    }
}
