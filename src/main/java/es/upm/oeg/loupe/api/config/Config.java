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

public class Config {

    @ApiModelProperty(value = "RDF Source", required = true)
    private Source source;

    private ProfilerOptions options;

    @ApiModelProperty(value = "Persist results", example = "false")
    private boolean presist;

    public Config() {
    }

    public Config(Source source, ProfilerOptions options, boolean presist) {
        this.source = source;
        this.options = options;
        this.presist = presist;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public ProfilerOptions getOptions() {
        return options;
    }

    public void setOptions(ProfilerOptions options) {
        this.options = options;
    }

    public boolean isPresist() {
        return presist;
    }

    public void setPresist(boolean presist) {
        this.presist = presist;
    }
}
