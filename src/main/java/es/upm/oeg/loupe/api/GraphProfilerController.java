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

package es.upm.oeg.loupe.api;

import es.upm.oeg.loupe.api.config.Config;
import es.upm.oeg.loupe.api.config.ProfilerOptions;
import es.upm.oeg.loupe.api.config.Source;
import es.upm.oeg.loupe.api.search.Query;
import es.upm.oeg.loupe.indexer.Indexer;
import es.upm.oeg.loupe.indexer.IndexerConfig;
import es.upm.oeg.loupe.indexer.IndexerOptions;
import es.upm.oeg.loupe.search.SearchEngine;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.jena.graph.Graph;
import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import virtuoso.jena.driver.VirtGraph;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@Api(value = "loupe-api", description = "Profile RDF Data", tags = "loupe-api")
@RequestMapping("/loupe")
public class GraphProfilerController {

    private AtomicInteger counter = new AtomicInteger(1);

    private LocalDate date = LocalDate.now();

    private static final String ENDPOINT = "http://172.17.0.1:8891/sparql";

    private static final String GRAPH = "http://data.loupe.linked.es/";

    private static final String JDBC = "jdbc:virtuoso://172.17.0.1:1112";

    private static final String VIRTUOSO_USER = "nandana";
    private static final String VIRTUOSO_PASS = "Ns00riya.21";

    SearchEngine searchEngine = new SearchEngine(ENDPOINT, GRAPH);

    @ApiOperation(value = "Returns the profile information for a given RDF source.")
    @RequestMapping(method= RequestMethod.POST, value = "/profile", produces = {"text/turtle"})
    @Consumes(value = "application/json")
    @Produces(value = "text/turtle")
    public @ResponseBody String profileRDFGraph(@RequestBody @ApiParam(name = "config", required = true)Config config) {

            Source source = config.getSource();
            String sparql = source.getSparqlEndpoint();
            String namedGraph =  source.getNamedGraph();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ProfilerOptions profilerOptions = config.getOptions();

        IndexerConfig indexerConfig = new IndexerConfig(getID(), sparql, namedGraph, baos);
        indexerConfig.setSparql(true);

        IndexerOptions options = indexerConfig.getOptions();
        if(profilerOptions.isComplete()) {
            options.setComplete(true);
        } else {
            if(profilerOptions.isVocabUsage()){
                options.setVocabUsage(true);
            }
            if(profilerOptions.isLanguagePartitions()) {
                options.setLanguagePartitions(true);
            }
            if(profilerOptions.isValueDistributions()) {
                options.setValueDistribution(true);
            }
        }

        Indexer indexer = new Indexer(indexerConfig);
        indexer.run();

        if (config.isPresist()) {

            VirtGraph virtGraph =  new VirtGraph(GRAPH, JDBC, VIRTUOSO_USER, VIRTUOSO_PASS);
            virtGraph.setInsertBNodeAsVirtuosoIRI(true);

            Model model = indexer.getModel();
            Graph graph = model.getGraph();
            ExtendedIterator<Triple> tripleIterator = graph.find(Triple.ANY);

            while (tripleIterator.hasNext()) {
                Triple triple = tripleIterator.next();
                virtGraph.add(triple);
            }
        }

        return new String( baos.toByteArray());

    }

    @ApiOperation(value = "Returns a list of namedgraphs found in an SPARQL endpoint")
    @RequestMapping(method= RequestMethod.GET, value = "/namedgraphs", produces = {"application/json"})
    @Produces(value = "application/json")
    public @ResponseBody Set<String> listNamedgraphs(@ApiParam(name = "endpoint", required = true) @RequestParam String endpoint) {

        Set<String> namedgraphs = Indexer.getNamedgraphs(endpoint);
        return namedgraphs;
    }

    @ApiOperation(value = "Returns a list of dataset profile results based on the search criteria.")
    @RequestMapping(method= RequestMethod.POST, value = "/search", produces = {"application/json"})
    @Consumes(value = "application/json")
    @Produces(value = "application/json")
    public @ResponseBody Set<String> searchDatasetProfile(@RequestBody
            @ApiParam(name = "query", required = true)Query query) {

        return searchEngine.searchDatasetProfile(query.getSource().getSparqlEndpoint(),
                query.getSource().getNamedGraph(),
                query.getFrom(),
                query.getTo());
    }



    private synchronized String getID(){

        LocalDate newDate = LocalDate.now();
        if (newDate.isAfter(date)) {
            date = newDate;
            counter.set(0);
        }

        LocalDateTime newDateTime = LocalDateTime.now();
        return  String.format("%d%d%d%d%d%d", newDateTime.getYear(), newDateTime.getMonthValue(),
                newDateTime.getDayOfMonth(), newDateTime.getHour(), newDateTime.getMinute(), counter.getAndIncrement());

    }

}
