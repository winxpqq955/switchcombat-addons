package me.ho3.switchcombat;

import java.lang.reflect.Method;
import java.util.UUID;

public class OCMAPI {

    public static void setCombatMode(UUID playeruuid, UUID world, String mode) {
        try {
            if (Main.ocm != null) {
                final Object instance = Main.ocm;
                final Method setCombatModeMethod = instance.getClass().getMethod("setCombatMode", UUID.class, UUID.class, String.class);
                setCombatModeMethod.invoke(instance, playeruuid, world, mode);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean isOldCombatMechanicsEnabled() {
        return Main.ocm != null;
    }
}
