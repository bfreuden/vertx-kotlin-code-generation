# vertx-kotlin-code-generation

This is a tentative to use Kapt to generate (from Kotlin code) Java code to be used by Vertx annotation processors.

So far I didn't manage to get it working with Gradle. 

However there is something working with Maven.

The Maven project is structured differently with an annotations project because I had the impression that referencing the annotation-processor jar in the dependencies (in order to get the annotation) was forcing the use of the annotation processor.

It is working in Maven because I run kapt twice: first to run the annotation-processor (generating Java files from Kotlin), then to run the Vert.x annotation processor only.

The annotation processor is generating Java files in the kapt1 directory (that is declared as a source directory for the Kotlin compiler) because I had the impression the second call was removing the kapt directory (thus removing the ProxyEntity.java file).
