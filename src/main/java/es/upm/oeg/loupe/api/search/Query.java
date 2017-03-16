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

package es.upm.oeg.loupe.api.search;

import es.upm.oeg.loupe.api.config.Source;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

public class Query {

    @ApiModelProperty(value = "Source information", required = true)
    Source source;

    @ApiModelProperty(value = "From criteria", required = false, example = "2016-02-07")
    LocalDate from;

    @ApiModelProperty(value = "To criteria", required = false, example = "2018-02-07")
    LocalDate to;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }
}
