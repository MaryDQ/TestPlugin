package danny.jiang.asm;


import org.gradle.api.Project;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.*;

class OnClickMethodVisitor extends MethodVisitor {
    private Project project;

    public OnClickMethodVisitor(MethodVisitor mv, Project project) {
        super(Opcodes.ASM6, mv);
        this.project = project;
    }


    @Override
    public void visitCode() {
        super.visitCode();

        OnClickExtension extension = (OnClickExtension) this.project.getExtensions().findByName("OnClickExtension");
        if (extension.checkClass != null && !extension.checkClass.equals("")) {
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "getId", "()I", false);
            mv.visitMethodInsn(INVOKESTATIC, extension.checkClass, "isFastClick", "()Z", false);
            Label l1 = new Label();
            mv.visitJumpInsn(IFEQ, l1);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(10, l2);
            mv.visitInsn(RETURN);
            mv.visitLabel(l1);
            mv.visitLineNumber(12, l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        }


    }
}
