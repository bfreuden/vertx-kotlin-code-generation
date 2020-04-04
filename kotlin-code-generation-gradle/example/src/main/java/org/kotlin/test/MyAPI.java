package org.kotlin.test;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@VertxGen
@ProxyGen
public interface MyAPI {

  void doSomething(ProxyEntity input, Handler<AsyncResult<ProxyEntity>> resultHandler);

}
