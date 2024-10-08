// Copyright 2021-present StarRocks, Inc. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.starrocks.lake.compaction;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

public class RandomSorter implements Sorter {
    public RandomSorter() {
    }

    @NotNull
    public List<PartitionStatisticsSnapshot> sort(@NotNull List<PartitionStatisticsSnapshot> partitionStatistics) {
        Collections.shuffle(partitionStatistics);
        return partitionStatistics.stream()
                .sorted(Comparator.comparingInt((PartitionStatisticsSnapshot stats) -> stats.getPriority().getValue())
                        .reversed())
                .collect(Collectors.toList());
    }
}
