/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.store.kahadb;

import java.io.File;

import junit.framework.Test;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.BrokerTest;
import org.apache.activemq.util.IOHelper;

/**
 * Once the wire format is completed we can test against real persistence storage.
 */
public class KahaDBStoreBrokerTest extends BrokerTest {

   @Override
   protected void setUp() throws Exception {
      this.setAutoFail(true);
      super.setUp();
   }

   @Override
   protected BrokerService createBroker() throws Exception {
      BrokerService broker = new BrokerService();
      KahaDBStore kaha = new KahaDBStore();
      File directory = new File("target/activemq-data/kahadb");
      IOHelper.deleteChildren(directory);
      kaha.setDirectory(directory);
      kaha.deleteAllMessages();
      broker.setPersistenceAdapter(kaha);
      return broker;
   }

   protected BrokerService createRestartedBroker() throws Exception {
      BrokerService broker = new BrokerService();
      KahaDBStore kaha = new KahaDBStore();
      kaha.setDirectory(new File("target/activemq-data/kahadb"));
      broker.setPersistenceAdapter(kaha);
      return broker;
   }

   public static Test suite() {
      return suite(KahaDBStoreBrokerTest.class);
   }

   public static void main(String[] args) {
      junit.textui.TestRunner.run(suite());
   }

}
