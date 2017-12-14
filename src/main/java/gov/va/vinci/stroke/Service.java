package gov.va.vinci.stroke;

/*
 * #%L
 * Leo Examples
 * %%
 * Copyright (C) 2010 - 2014 Department of Veterans Affairs
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import gov.va.vinci.stroke.pipeline.Pipeline;
import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import groovy.util.ConfigObject;
import groovy.util.ConfigSlurper;
import org.apache.commons.beanutils.BeanUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Set;

public class Service {
    File[] serviceConfigFile;

    /**
     * @param args
     */

    public static void main(String[] args) {
        File[] configFiles = new File[]{new File("config/ServiceConfig.groovy")};
        try {
            new Service().run(configFiles);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(File[] configFiles) {
        serviceConfigFile = configFiles;

        gov.va.vinci.leo.Service service = null;

        try {
            service = new gov.va.vinci.leo.Service();
            setServerProperties(service);
            //Custom code

            deleteFilesFromDescriptorDirectory(service);
            /** Create an aggregate of the components. */
            Pipeline pipeline = new Pipeline();
            LeoAEDescriptor aggregate = pipeline.getPipeline();

            /**
             * If the type system does not exist in your code, or has been changed, run JCasGen to re-generate the
             * type classes. This can safetly be run anytime, though adds a bit of overhead to startup time of the service.
             */
            if (false) {
                pipeline.getLeoTypeSystemDescription().jCasGen("src/main/java/", "target/classes");
            }
            aggregate.setIsAsync(false);
            aggregate.setNumberOfInstances(10);

			/* Deploy the service. */
            service.deploy(aggregate);

            System.out.println("Deployment: " + service.getDeploymentDescriptorFile());
            System.out.println("Aggregate: " + service.getAggregateDescriptorFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Use this method with caution. It deletes ALL .xml files in the directory. Make sure you do not point to a wrong directory!!!
     *
     * @param service
     */
    private void deleteFilesFromDescriptorDirectory(gov.va.vinci.leo.Service service) {
        if (!service.isDeleteOnExit()) {
            File files[] = new File(service.getDescriptorDirectory()).listFiles();
            if (files != null) {
                for (int index = 0; index < files.length; index++) {
                    if ((files[index]).isFile() && (files[index]).getName().endsWith(".xml")) {
                        String path = (files[index]).getAbsolutePath();
                        if ((files[index]).delete()) {
                            System.out.println("Deleted : " + path);
                        } else {
                            System.out.println("Failed to delete : " + path);
                        }
                    }
                }
            }
        }

    }

    /**
     * Loading properties from configuration file
     *
     * @param leoServer
     * @return
     * @throws MalformedURLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    protected gov.va.vinci.leo.Service setServerProperties(gov.va.vinci.leo.Service leoServer) throws MalformedURLException,
            InvocationTargetException, IllegalAccessException {
        if (serviceConfigFile.length != 1) {
            return leoServer;
        }

        ConfigSlurper configSlurper = new ConfigSlurper();
        ConfigObject o = configSlurper.parse(serviceConfigFile[0].toURI().toURL());

        Set<Map.Entry> entries = o.entrySet();
        for (Map.Entry e : entries) {
            System.out.println("Setting property " + e.getKey() + " on service to " + e.getValue() + ".");
            BeanUtils.setProperty(leoServer, e.getKey().toString(), e.getValue());
        }
        return leoServer;
    }
}
