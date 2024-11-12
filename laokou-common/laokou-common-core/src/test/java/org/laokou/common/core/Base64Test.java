/*
 * Copyright (c) 2022-2024 KCloud-Platform-IoT Author or Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.laokou.common.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.laokou.common.core.utils.Base64Util;

import java.nio.charset.StandardCharsets;

/**
 * @author laokou
 */
class Base64Test {

	@Test
	void testBase64() {
		String name = "zzzzzzzzzzzccccc\nccccccccccccccccccc231231qweqweqw";
		String encodeToString = Base64Util.encodeToString(name.getBytes(StandardCharsets.UTF_8));
		byte[] decode = Base64Util.decode(encodeToString);
		Assertions.assertArrayEquals(name.getBytes(StandardCharsets.UTF_8), decode);
	}

	@Test
	void testMimeBase64() {
		String name = "zzzzzzzzzzzccccc\nccccccccccccccccccc231231qweqweqw";
		String encodeToStringOfMime = Base64Util.encodeToStringOfMime(name.getBytes(StandardCharsets.UTF_8));
		byte[] mimeDecode = Base64Util.decodeOfMime(encodeToStringOfMime);
		Assertions.assertArrayEquals(name.getBytes(StandardCharsets.UTF_8), mimeDecode);
	}

}
