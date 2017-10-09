/*
Copyright 2017 BMC Software, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.bmc.truesight.apiclient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Optional;

/**
 * Object which defines a Metric which is used in createMetrics() method.
 */
public final class Metric implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final String name;    
    private final String displayName;
    private final String displayNameShort;
    private final MetricUnits unit;
    private final MetricAggregates defaultAggregate;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<String> description;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<String> type;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<String> unitDescription; 
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<Integer> defaultResolutionMS;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<Boolean> isDisabled;

    /**
     * 
     * @param name Name of the metric, must be globally unique, recommended that you add your own namespace
     * @param displayName Short name to use when referring to the metric
     * @param displayNameShort Short name for the Metric, less than 15 characters preferred
     * @param unit The units of measurement for the metric, can be percent, number, bytecount, or duration
     * @param defaultAggregate When graphing the aggregate function that makes most sense for this metric. Can be sum, avg, max, or min.
     * @see MetricUnits
     * @see MetricAggregates
     */
    public Metric(String name, String displayName, String displayNameShort,
            MetricUnits unit, MetricAggregates defaultAggregate)
    {
        this.name = name;        
        this.displayName = displayName;
        this.displayNameShort = displayNameShort;
        this.unit = unit;        
        this.defaultAggregate = defaultAggregate;    
    }    
    
    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDisplayNameShort() {
        return displayNameShort;
    }

    public MetricUnits getUnit() {
        return unit;
    }
    
    public MetricAggregates getDefaultAggregate() {
        return defaultAggregate;
    }

    
    
    public Optional<String> getDescription() {
        return description;
    }

    public Optional<String> getType() {
        return type;
    }

    public Optional<String> getUnitDescription() {
        return unitDescription;
    }

    public Optional<Integer> getDefaultResolutionMS() {
        return defaultResolutionMS;
    }

    public Optional<Boolean> getIsDisabled() {
        return isDisabled;
    }
    
    /**
     * 
     * @param description Description of the metric (optional)
     */
    public void setDescription(String description) {
        this.description = Optional.of(description);
    }
    
    /**
     * 
     * @param type Type of metric, could be a device metric, a plugin metric or any arbitrary type (required)
     */
    public void setType(String type) {
        this.type = Optional.of(type);
    }

    /**
     * 
     * @param unitDescription An optional description of the unit like "$ per second" or "meters per second". Can only be set for metrics with unit of number. (optional)
     */
    public void setUnitDescription(String unitDescription) {
        this.unitDescription = Optional.of(unitDescription);
    }

    /**
     * 
     * @param defaultResolutionMS Expected polling time of data in milliseconds. Used to improve rendering of graphs for non-one-second polled metrics. (optional)
     */
    public void setDefaultResolutionMS(int defaultResolutionMS) {
        this.defaultResolutionMS = Optional.of(defaultResolutionMS);
    }

    /**
     * 
     * @param isDisabled Is this metric disabled (optional)
     */
    public void setIsDisabled(boolean isDisabled) {
        this.isDisabled = Optional.of(isDisabled);
    }
}

