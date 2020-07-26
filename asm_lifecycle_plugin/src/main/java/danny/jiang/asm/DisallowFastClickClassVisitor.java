package danny.jiang.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import org.gradle.api.Project;

public class DisallowFastClickClassVisitor extends ClassVisitor {
    private String className;
    private String superName;
    private Project project;

    public DisallowFastClickClassVisitor(ClassVisitor cv, Project project) {
        super(Opcodes.ASM5, cv);
        this.project = project;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.className = name;
        this.superName = superName;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("mlx search :" + name + ",desc:" + desc + ",access:" + access);
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
//        System.out.println("OnClickClassVisitor:visitMethod ---------->started:" + this.className);
        if (Utils.isViewOnclickMethod(access, name, desc)) {
            System.out.println("OnClickClassVisitor : change method ---->" + name);
            return new OnClickMethodVisitor(mv, this.project);
        } else if (Utils.isListViewOnItemOnclickMethod(access, name, desc)) {
            System.out.println("OnClickClassVisitor : change method ---->" + name);
            return new OnClickMethodVisitor(mv, this.project);
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
