/**
 * Copyright 2015 Palantir Technologies
 *
 * Licensed under the BSD-3 License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.palantir.atlasdb.keyvalue.rdbms;

import com.palantir.atlasdb.keyvalue.api.KeyValueService;
import com.palantir.atlasdb.keyvalue.impl.SimpleKvsTimestampBoundStore;
import com.palantir.atlasdb.spi.AtlasDbFactory;
import com.palantir.atlasdb.spi.KeyValueServiceConfig;
import com.palantir.timestamp.PersistentTimestampService;
import com.palantir.timestamp.TimestampService;

public class PostgresAtlasDbFactory implements AtlasDbFactory {

    @Override
    public String getType() {
        return PostgresKeyValueConfiguration.TYPE;
    }

    @Override
    public KeyValueService createRawKeyValueService(KeyValueServiceConfig config) {
        return PostgresKeyValueService.create((PostgresKeyValueConfiguration) config);
    }

    @Override
    public TimestampService createTimestampService(KeyValueService rawKvs) {
        return PersistentTimestampService.create(SimpleKvsTimestampBoundStore.create(rawKvs));
    }
}
