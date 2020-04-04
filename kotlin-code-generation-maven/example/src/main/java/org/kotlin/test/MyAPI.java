package org.kotlin.test;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

@VertxGen
@ProxyGen
public interface MyAPI {

  void doSomething(org.kotlin.test.ProxyEntity input, Handler<AsyncResult<org.kotlin.test.ProxyEntity>> resultHandler);

  static MyAPI createProxy(Vertx vertx) {
    return new io.vertx.serviceproxy.ServiceProxyBuilder(vertx).setAddress("myapi").build(MyAPI.class);
  }

}
