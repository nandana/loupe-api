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
public class Source {

    @ApiModelProperty(value = "SPARQL Endpoint", required = true, example = "http://3cixty.eurecom.fr/sparql")
    private String sparqlEndpoint;

    @ApiModelProperty(value = "NamedGraph", example = "http://3cixty.com/cotedazur/crt")
    private String namedGraph;

    public Source() {

    }

    public Source(String sparqlEndpoint, String namedGraph) {
        this.sparqlEndpoint = sparqlEndpoint;
        this.namedGraph = namedGraph;
    }

    public String getSparqlEndpoint() {
        return sparqlEndpoint;
    }

    public void setSparqlEndpoint(String sparqlEndpoint) {
        this.sparqlEndpoint = sparqlEndpoint;
    }

    public String getNamedGraph() {
        return namedGraph;
    }

    public void setNamedGraph(String namedGraph) {
        this.namedGraph = namedGraph;
    }
}
