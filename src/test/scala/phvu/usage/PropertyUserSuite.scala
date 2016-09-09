/* Copyright 2016 The Cebes Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, version 2.0 (the "License").
 * You may not use this work except in compliance with the License,
 * which is available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 *
 * Created by phvu on 09/09/16.
 */

package phvu.usage

import com.google.inject.Guice
import org.scalatest.FunSuite
import phvu.prop.{Property, PropertyModule}

class PropertyUserSuite extends FunSuite {

  test("Default setting") {
    Property.values().foreach(p => System.clearProperty(p.getPropertyKey))
    val injector = Guice.createInjector(new PropertyModule)
    val user = injector.getInstance(classOf[PropertyUser])
    assert(user.server === Property.SERVER.getDefaultValue)
    assert(user.port === Property.PORT.getDefaultValue.toInt)
  }

  test("Correct java property") {
    System.setProperty(Property.SERVER.getPropertyKey, "mycrazyserver")
    System.setProperty(Property.PORT.getPropertyKey, "44332211")
    val injector = Guice.createInjector(new PropertyModule)
    val user = injector.getInstance(classOf[PropertyUser])
    assert(user.server === "mycrazyserver")
    assert(user.port === 44332211)
  }
}
