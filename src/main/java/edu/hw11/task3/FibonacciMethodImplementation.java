package edu.hw11.task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;

class FibonacciMethodImplementation implements ByteCodeAppender {
    private static final int STACK_SIZE = 5;
    private static final String FUNC_NAME = "fib";
    private static final String CLASS_NAME = "FibonacciClass";
    private static final String DESCRIPTOR = "(I)J";

    @Override
    public @NotNull Size apply(
        MethodVisitor methodVisitor,
        Implementation.@NotNull Context context,
        @NotNull MethodDescription methodDescription
    ) {
        methodVisitor.visitCode();

        Label startLabel = new Label();
        Label endLabel = new Label();

        methodVisitor.visitLabel(startLabel);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPLE, endLabel);

        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitInsn(Opcodes.ISUB);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, CLASS_NAME, FUNC_NAME, DESCRIPTOR, false);

        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.ICONST_2);
        methodVisitor.visitInsn(Opcodes.ISUB);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, CLASS_NAME, FUNC_NAME, DESCRIPTOR, false);

        methodVisitor.visitInsn(Opcodes.LADD);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        methodVisitor.visitLabel(endLabel);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.I2L);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        methodVisitor.visitEnd();

        return new Size(STACK_SIZE, 0);
    }
}
