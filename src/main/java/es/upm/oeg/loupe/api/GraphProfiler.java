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
 */

package es.upm.oeg.loupe.api;

import com.google.common.base.Predicates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EnableSwagger2
public class GraphProfiler {

    public static void main(String[] args) {
        SpringApplication.run(GraphProfiler.class, args);
    }

    @Bean
    public Docket patentApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("loupe-api")
                .apiInfo(getApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/error"))) // Exclude Spring error controllers
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(
                "validatorUrl",// url
                "list",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "schema",     // defaultModelRendering => schema
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                false,        // enableJsonEditor      => true | false
                true,         // showRequestHeaders    => true | false
                60000L);      // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
    }

    /***
     * Provide API information
     * @return ApiInfo of the dummy patent service.
     */
    private ApiInfo getApiInfo() {

        Contact contact = new Contact("Ontology Engineering Group", "http://www.oeg-upm.net/", "nmihindu@fi.upm.es");
        ApiInfo apiInfo = new ApiInfo("Loupe Linked Data Profiler API", "A REST API for profiling Linked Data with Loupe.",
                "0.0.1", "http://www.oeg-upm.net/", contact, "Apache Licence", "https://www.apache.org/licenses/LICENSE-2.0" );

        return apiInfo;
    }

}
