package edu.hw11.task2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;


public class Changer {

    private Changer() {}

    public static void changeMethod() {
        ByteBuddyAgent.install();

        DynamicType.Unloaded<?> unloadedType = new ByteBuddy()
            .redefine(RewrittenArithmeticUtils.class)
            .name(ArithmeticUtils.class.getName())
            .make();

        unloadedType.load(ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
    }
}
