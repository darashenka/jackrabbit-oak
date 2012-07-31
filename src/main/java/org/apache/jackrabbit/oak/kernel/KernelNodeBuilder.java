/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jackrabbit.oak.kernel;

import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.jackrabbit.oak.plugins.memory.MemoryNodeBuilder;
import org.apache.jackrabbit.oak.spi.state.NodeState;

class KernelNodeBuilder extends MemoryNodeBuilder {

    private final KernelRootBuilder root;

    public KernelNodeBuilder(
            MemoryNodeBuilder parent, String name,
            NodeState base, KernelRootBuilder root) {
        super(parent, name, base);
        this.root = checkNotNull(root);
    }

    //--------------------------------------------------< MemoryNodeBuilder >---

    @Override
    protected MemoryNodeBuilder createChildBuilder(
            String name, NodeState child) {
        return new KernelNodeBuilder(this, name, child, root);
    }

    @Override
    protected void updated() {
        root.updated();
    }

}