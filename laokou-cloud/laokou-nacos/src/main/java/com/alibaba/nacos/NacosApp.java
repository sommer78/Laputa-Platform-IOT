/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Nacos starter.
 * <p>
 * Use @SpringBootApplication and @ComponentScan at the same time, using CUSTOM type
 * filter to control module enabled.
 * </p>
 *
 * @author nacos
 */
@ServletComponentScan
@SpringBootApplication
@EnableEncryptableProperties
public class NacosApp {

	public static void main(String[] args) {
		// @formatter:off
		// -Dnacos.home => Nacos的根目录
		// Nacos控制台 => 【http/https】://【ip:8848】/nacos
		// -Dnacos.remote.server.rpc.tls.enableTls=true
		// -Dnacos.remote.server.rpc.tls.mutualAuthEnable=true
		// -Dnacos.remote.server.rpc.tls.compatibility=false
		// -Dnacos.remote.server.rpc.tls.certChainFile=nacos-server-cert.pem
		// -Dnacos.remote.server.rpc.tls.certPrivateKey=nacos-server-key.pem
		// -Dnacos.remote.server.rpc.tls.trustCollectionCertFile=nacos-ca-cert.pem
		// -Dnacos.remote.server.rpc.tls.certPrivateKeyPassword=laokou123
		// --add-opens=java.base/java.lang=ALL-UNNAMED
		// @formatter:on
		new SpringApplicationBuilder(NacosApp.class).web(WebApplicationType.SERVLET).run(args);
	}

}
