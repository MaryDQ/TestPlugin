package danny.jiang.plugin

import com.android.build.gradle.AppExtension
import danny.jiang.asm.OnClickExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

public class LifeCyclePlugin implements Plugin<Project> {
    void apply(Project project) {
        System.out.println("I'm mlx's plugin")


        project.extensions.add("OnClickExtension", OnClickExtension)
        def android = project.extensions.getByType(AppExtension)
        println '----------register AutoTrackTransform----------'
        LifeCycleTransform transform = new LifeCycleTransform(project)
        android.registerTransform(transform)
    }
}