package org.kotlin.test

import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.serviceproxy.ServiceBinder
import org.junit.Test

class MyAPIImpl: MyAPI {

    override fun doSomething(input: ProxyEntity, resultHandler: Handler<AsyncResult<ProxyEntity>>) {
        resultHandler.handle(Future.succeededFuture(input))
    }

}

class AnnotationTest {

    @Test fun testVertx() {
        val vertx = Vertx.vertx()
        val binder =  ServiceBinder(vertx)
        binder.setAddress("myapi")
                .register(MyAPI::class.java, MyAPIImpl())

        val api = MyAPI.createProxy(vertx)
        val entity = Entity("hello")
        api.doSomething(org.kotlin.test.ProxyEntity(entity)) {
            if (it.succeeded()) {
                print(it.result().entity)
            } else {
                it.cause().printStackTrace()
            }
            vertx.close()
        }
    }
}
