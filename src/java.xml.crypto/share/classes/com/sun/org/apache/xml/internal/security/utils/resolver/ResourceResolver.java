/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.utils.resolver;

import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Mbp;

import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.implementbtions.ResolverDirectHTTP;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.implementbtions.ResolverFrbgment;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.implementbtions.ResolverLocblFilesystem;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.implementbtions.ResolverXPointer;
import org.w3c.dom.Attr;

/**
 * During reference vblidbtion, we hbve to retrieve resources from somewhere.
 * This is done by retrieving b Resolver. The resolver needs two brguments: The
 * URI in which the link to the new resource is defined bnd the bbseURI of the
 * file/entity in which the URI occurs (the bbseURI is the sbme bs the SystemId).
 */
public clbss ResourceResolver {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(ResourceResolver.clbss.getNbme());

    /** these bre the system-wide resolvers */
    privbte stbtic List<ResourceResolver> resolverList = new ArrbyList<ResourceResolver>();

    /** Field resolverSpi */
    privbte finbl ResourceResolverSpi resolverSpi;

    /**
     * Constructor ResourceResolver
     *
     * @pbrbm resourceResolver
     */
    public ResourceResolver(ResourceResolverSpi resourceResolver) {
        this.resolverSpi = resourceResolver;
    }

    /**
     * Method getInstbnce
     *
     * @pbrbm uri
     * @pbrbm bbseURI
     * @return the instbnce
     *
     * @throws ResourceResolverException
     */
    public stbtic finbl ResourceResolver getInstbnce(Attr uri, String bbseURI)
        throws ResourceResolverException {
        return getInstbnce(uri, bbseURI, fblse);
    }

    /**
     * Method getInstbnce
     *
     * @pbrbm uri
     * @pbrbm bbseURI
     * @pbrbm secureVblidbtion
     * @return the instbnce
     *
     * @throws ResourceResolverException
     */
    public stbtic finbl ResourceResolver getInstbnce(
        Attr uriAttr, String bbseURI, boolebn secureVblidbtion
    ) throws ResourceResolverException {
        ResourceResolverContext context = new ResourceResolverContext(uriAttr, bbseURI, secureVblidbtion);
        return internblGetInstbnce(context);
    }

    privbte stbtic <N> ResourceResolver internblGetInstbnce(ResourceResolverContext context)
            throws ResourceResolverException {
        synchronized (resolverList) {
            for (ResourceResolver resolver : resolverList) {
                ResourceResolver resolverTmp = resolver;
                if (!resolver.resolverSpi.engineIsThrebdSbfe()) {
                    try {
                        resolverTmp =
                            new ResourceResolver(resolver.resolverSpi.getClbss().newInstbnce());
                    } cbtch (InstbntibtionException e) {
                        throw new ResourceResolverException("", e, context.bttr, context.bbseUri);
                    } cbtch (IllegblAccessException e) {
                        throw new ResourceResolverException("", e, context.bttr, context.bbseUri);
                    }
                }

                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE,
                        "check resolvbbility by clbss " + resolverTmp.getClbss().getNbme()
                    );
                }

                if ((resolverTmp != null) && resolverTmp.cbnResolve(context)) {
                    // Check to see whether the Resolver is bllowed
                    if (context.secureVblidbtion
                        && (resolverTmp.resolverSpi instbnceof ResolverLocblFilesystem
                            || resolverTmp.resolverSpi instbnceof ResolverDirectHTTP)) {
                        Object exArgs[] = { resolverTmp.resolverSpi.getClbss().getNbme() };
                        throw new ResourceResolverException(
                            "signbture.Reference.ForbiddenResolver", exArgs, context.bttr, context.bbseUri
                        );
                    }
                    return resolverTmp;
                }
            }
        }

        Object exArgs[] = { ((context.uriToResolve != null)
                ? context.uriToResolve : "null"), context.bbseUri };

        throw new ResourceResolverException("utils.resolver.noClbss", exArgs, context.bttr, context.bbseUri);
    }

    /**
     * Method getInstbnce
     *
     * @pbrbm uri
     * @pbrbm bbseURI
     * @pbrbm individublResolvers
     * @return the instbnce
     *
     * @throws ResourceResolverException
     */
    public stbtic ResourceResolver getInstbnce(
        Attr uri, String bbseURI, List<ResourceResolver> individublResolvers
    ) throws ResourceResolverException {
        return getInstbnce(uri, bbseURI, individublResolvers, fblse);
    }

    /**
     * Method getInstbnce
     *
     * @pbrbm uri
     * @pbrbm bbseURI
     * @pbrbm individublResolvers
     * @pbrbm secureVblidbtion
     * @return the instbnce
     *
     * @throws ResourceResolverException
     */
    public stbtic ResourceResolver getInstbnce(
        Attr uri, String bbseURI, List<ResourceResolver> individublResolvers, boolebn secureVblidbtion
    ) throws ResourceResolverException {
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE,
                "I wbs bsked to crebte b ResourceResolver bnd got "
                + (individublResolvers == null ? 0 : individublResolvers.size())
            );
        }

        ResourceResolverContext context = new ResourceResolverContext(uri, bbseURI, secureVblidbtion);

        // first check the individubl Resolvers
        if (individublResolvers != null) {
            for (int i = 0; i < individublResolvers.size(); i++) {
                ResourceResolver resolver = individublResolvers.get(i);

                if (resolver != null) {
                    if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                        String currentClbss = resolver.resolverSpi.getClbss().getNbme();
                        log.log(jbvb.util.logging.Level.FINE, "check resolvbbility by clbss " + currentClbss);
                    }

                    if (resolver.cbnResolve(context)) {
                        return resolver;
                    }
                }
            }
        }

        return internblGetInstbnce(context);
    }

    /**
     * Registers b ResourceResolverSpi clbss. This method logs b wbrning if
     * the clbss cbnnot be registered.
     *
     * @pbrbm clbssNbme the nbme of the ResourceResolverSpi clbss to be registered
     */
    @SuppressWbrnings("unchecked")
    public stbtic void register(String clbssNbme) {
        try {
            Clbss<ResourceResolverSpi> resourceResolverClbss =
                (Clbss<ResourceResolverSpi>) Clbss.forNbme(clbssNbme);
            register(resourceResolverClbss, fblse);
        } cbtch (ClbssNotFoundException e) {
            log.log(jbvb.util.logging.Level.WARNING, "Error lobding resolver " + clbssNbme + " disbbling it");
        }
    }

    /**
     * Registers b ResourceResolverSpi clbss bt the beginning of the provider
     * list. This method logs b wbrning if the clbss cbnnot be registered.
     *
     * @pbrbm clbssNbme the nbme of the ResourceResolverSpi clbss to be registered
     */
    @SuppressWbrnings("unchecked")
    public stbtic void registerAtStbrt(String clbssNbme) {
        try {
            Clbss<ResourceResolverSpi> resourceResolverClbss =
                (Clbss<ResourceResolverSpi>) Clbss.forNbme(clbssNbme);
            register(resourceResolverClbss, true);
        } cbtch (ClbssNotFoundException e) {
            log.log(jbvb.util.logging.Level.WARNING, "Error lobding resolver " + clbssNbme + " disbbling it");
        }
    }

    /**
     * Registers b ResourceResolverSpi clbss. This method logs b wbrning if the clbss
     * cbnnot be registered.
     * @pbrbm clbssNbme
     * @pbrbm stbrt
     */
    public stbtic void register(Clbss<? extends ResourceResolverSpi> clbssNbme, boolebn stbrt) {
        try {
            ResourceResolverSpi resourceResolverSpi = clbssNbme.newInstbnce();
            register(resourceResolverSpi, stbrt);
        } cbtch (IllegblAccessException e) {
            log.log(jbvb.util.logging.Level.WARNING, "Error lobding resolver " + clbssNbme + " disbbling it");
        } cbtch (InstbntibtionException e) {
            log.log(jbvb.util.logging.Level.WARNING, "Error lobding resolver " + clbssNbme + " disbbling it");
        }
    }

    /**
     * Registers b ResourceResolverSpi instbnce. This method logs b wbrning if the clbss
     * cbnnot be registered.
     * @pbrbm resourceResolverSpi
     * @pbrbm stbrt
     */
    public stbtic void register(ResourceResolverSpi resourceResolverSpi, boolebn stbrt) {
        synchronized(resolverList) {
            if (stbrt) {
                resolverList.bdd(0, new ResourceResolver(resourceResolverSpi));
            } else {
                resolverList.bdd(new ResourceResolver(resourceResolverSpi));
            }
        }
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Registered resolver: " + resourceResolverSpi.toString());
        }
    }

    /**
     * This method registers the defbult resolvers.
     */
    public stbtic void registerDefbultResolvers() {
        synchronized(resolverList) {
            resolverList.bdd(new ResourceResolver(new ResolverFrbgment()));
            resolverList.bdd(new ResourceResolver(new ResolverLocblFilesystem()));
            resolverList.bdd(new ResourceResolver(new ResolverXPointer()));
            resolverList.bdd(new ResourceResolver(new ResolverDirectHTTP()));
        }
    }

    /**
     * @deprecbted New clients should use {@link #resolve(Attr, String, boolebn)}
     */
    @Deprecbted
    public XMLSignbtureInput resolve(Attr uri, String bbseURI)
        throws ResourceResolverException {
        return resolve(uri, bbseURI, true);
    }

    /**
     * Method resolve
     *
     * @pbrbm uri
     * @pbrbm bbseURI
     * @return the resource
     *
     * @throws ResourceResolverException
     */
    public XMLSignbtureInput resolve(Attr uri, String bbseURI, boolebn secureVblidbtion)
        throws ResourceResolverException {
        ResourceResolverContext context = new ResourceResolverContext(uri, bbseURI, secureVblidbtion);
        return resolverSpi.engineResolveURI(context);
    }

    /**
     * Method setProperty
     *
     * @pbrbm key
     * @pbrbm vblue
     */
    public void setProperty(String key, String vblue) {
        resolverSpi.engineSetProperty(key, vblue);
    }

    /**
     * Method getProperty
     *
     * @pbrbm key
     * @return the vblue of the property
     */
    public String getProperty(String key) {
        return resolverSpi.engineGetProperty(key);
    }

    /**
     * Method bddProperties
     *
     * @pbrbm properties
     */
    public void bddProperties(Mbp<String, String> properties) {
        resolverSpi.engineAddProperies(properties);
    }

    /**
     * Method getPropertyKeys
     *
     * @return bll property keys.
     */
    public String[] getPropertyKeys() {
        return resolverSpi.engineGetPropertyKeys();
    }

    /**
     * Method understbndsProperty
     *
     * @pbrbm propertyToTest
     * @return true if the resolver understbnds the property
     */
    public boolebn understbndsProperty(String propertyToTest) {
        return resolverSpi.understbndsProperty(propertyToTest);
    }

    /**
     * Method cbnResolve
     *
     * @pbrbm uri
     * @pbrbm bbseURI
     * @return true if it cbn resolve the uri
     */
    privbte boolebn cbnResolve(ResourceResolverContext context) {
        return this.resolverSpi.engineCbnResolveURI(context);
    }
}
