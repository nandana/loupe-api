/*
 *  Copyright 2014-2017 Ontology Engineering Group, Universidad Politécnica de Madrid, Spain
 *  <p>
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  <p>
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

package es.upm.oeg.loupe.api.config;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Nandana Mihindukulasooriya
 * @since 8/02/17.
 */
public class ProfilerOptions {

    @ApiModelProperty(value = "Set true all options", required = true, example = "false")
    private boolean complete;

    @ApiModelProperty(value = "High-level summary statistics", required = true, example = "true")
    private boolean summary;

    @ApiModelProperty(value = "Vocabulary usage profiling", required = true, example = "false")
    private boolean vocabUsage;

    @ApiModelProperty(value = "Language tagged strings profiling", required = true, example = "false")
    private boolean languagePartitions;

    @ApiModelProperty(value = "Property value profiling", required = false)
    private boolean valueDistributions;

    public ProfilerOptions() {
    }

    public ProfilerOptions(boolean vocabUsage) {
        this.vocabUsage = vocabUsage;
    }

    public boolean isVocabUsage() {
        return vocabUsage;
    }

    public void setVocabUsage(boolean vocabUsage) {
        this.vocabUsage = vocabUsage;
    }

    public boolean isLanguagePartitions() {
        return languagePartitions;
    }

    public void setLanguagePartitions(boolean languagePartitions) {
        this.languagePartitions = languagePartitions;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isValueDistributions() {
        return valueDistributions;
    }

    public void setValueDistributions(boolean valueDistributions) {
        this.valueDistributions = valueDistributions;
    }

    public boolean isSummary() {
        return summary;
    }

    public void setSummary(boolean summary) {
        this.summary = summary;
    }
}
