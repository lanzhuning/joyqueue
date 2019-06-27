/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jd.joyqueue.broker.protocol.command;

import com.jd.joyqueue.broker.network.traffic.FetchTrafficPayload;
import com.jd.joyqueue.broker.network.traffic.Traffic;

/**
 * FetchTopicMessageResponse
 * author: gaohaoxiang
 * email: gaohaoxiang@jd.com
 * date: 2019/5/16
 */
public class FetchTopicMessageResponse extends com.jd.joyqueue.network.command.FetchTopicMessageResponse implements FetchTrafficPayload {

    private Traffic traffic;

    public void setTraffic(Traffic traffic) {
        this.traffic = traffic;
    }

    @Override
    public Traffic getTraffic() {
        return traffic;
    }
}