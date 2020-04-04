package org.kotlin.annotationProcessor

import org.yanex.takenoko.*
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic.Kind.*

@Target(AnnotationTarget.CLASS)
annotation class TestAnnotation

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("org.kotlin.annotationProcessor.TestAnnotation")
@SupportedOptions(TestAnnotationProcessor.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class TestAnnotationProcessor : AbstractProcessor() {
    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment): Boolean {
        val annotatedElements = roundEnv.getElementsAnnotatedWith(TestAnnotation::class.java)
        if (annotatedElements.isEmpty()) return false

        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: run {
            processingEnv.messager.printMessage(ERROR, "Can't find the target directory for generated Kotlin files.")
            return false
        }

        println("-----------------------------------------------------------------")
        println("-----------------------------------------------------------------")
        println("-----------------------------------------------------------------")
        println("--------------------         kapt        ------------------------")
        println("-----------------------------------------------------------------")
        println("-----------------------------------------------------------------")
        println("-----------------------------------------------------------------")
        println(kaptKotlinGeneratedDir)
        var dir = File(kaptKotlinGeneratedDir)
        while (dir.parentFile != null && dir.name != "source")
            dir = dir.parentFile
        File(File(dir, "kapt/compile"), "ProxyEntity.java").apply {
            parentFile.mkdirs()
            println("writing $this")
            writeText("""
package org.kotlin.test;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class ProxyEntity {

    public final Entity entity;

    public ProxyEntity(Entity entity) {
        this.entity = entity;
    }

    public ProxyEntity(JsonObject json) {
        this.entity = new Entity(json.getString("message"));
    }

    public JsonObject toJson() {
        return JsonObject.mapFrom(entity);
    }

}
            """.trimIndent())
        }

        return true
    }

}