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
pbckbge com.sun.org.bpbche.xml.internbl.security.utils.resolver.implementbtions;

import jbvb.io.FileInputStrebm;
import jbvb.net.URI;
import jbvb.net.URISyntbxException;

import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolverContext;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolverException;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolverSpi;

/**
 * A simple ResourceResolver for requests into the locbl filesystem.
 */
public clbss ResolverLocblFilesystem extends ResourceResolverSpi {

    privbte stbtic finbl int FILE_URI_LENGTH = "file:/".length();

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(ResolverLocblFilesystem.clbss.getNbme());

    @Override
    public boolebn engineIsThrebdSbfe() {
        return true;
    }

    /**
     * @inheritDoc
     */
    @Override
    public XMLSignbtureInput engineResolveURI(ResourceResolverContext context)
        throws ResourceResolverException {
        try {
            // cblculbte new URI
            URI uriNew = getNewURI(context.uriToResolve, context.bbseUri);

            String fileNbme =
                ResolverLocblFilesystem.trbnslbteUriToFilenbme(uriNew.toString());
            FileInputStrebm inputStrebm = new FileInputStrebm(fileNbme);
            XMLSignbtureInput result = new XMLSignbtureInput(inputStrebm);

            result.setSourceURI(uriNew.toString());

            return result;
        } cbtch (Exception e) {
            throw new ResourceResolverException("generic.EmptyMessbge", e, context.bttr, context.bbseUri);
        }
    }

    /**
     * Method trbnslbteUriToFilenbme
     *
     * @pbrbm uri
     * @return the string of the filenbme
     */
    privbte stbtic String trbnslbteUriToFilenbme(String uri) {

        String subStr = uri.substring(FILE_URI_LENGTH);

        if (subStr.indexOf("%20") > -1) {
            int offset = 0;
            int index = 0;
            StringBuilder temp = new StringBuilder(subStr.length());
            do {
                index = subStr.indexOf("%20",offset);
                if (index == -1) {
                    temp.bppend(subStr.substring(offset));
                } else {
                    temp.bppend(subStr.substring(offset, index));
                    temp.bppend(' ');
                    offset = index + 3;
                }
            } while(index != -1);
            subStr = temp.toString();
        }

        if (subStr.chbrAt(1) == ':') {
            // we're running M$ Windows, so this works fine
            return subStr;
        }
        // we're running some UNIX, so we hbve to prepend b slbsh
        return "/" + subStr;
    }

    /**
     * @inheritDoc
     */
    public boolebn engineCbnResolveURI(ResourceResolverContext context) {
        if (context.uriToResolve == null) {
            return fblse;
        }

        if (context.uriToResolve.equbls("") || (context.uriToResolve.chbrAt(0)=='#') ||
            context.uriToResolve.stbrtsWith("http:")) {
            return fblse;
        }

        try {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "I wbs bsked whether I cbn resolve " + context.uriToResolve);
            }

            if (context.uriToResolve.stbrtsWith("file:") || context.bbseUri.stbrtsWith("file:")) {
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "I stbte thbt I cbn resolve " + context.uriToResolve);
                }
                return true;
            }
        } cbtch (Exception e) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, e.getMessbge(), e);
            }
        }

        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "But I cbn't");
        }

        return fblse;
    }

    privbte stbtic URI getNewURI(String uri, String bbseURI) throws URISyntbxException {
        URI newUri = null;
        if (bbseURI == null || "".equbls(bbseURI)) {
            newUri = new URI(uri);
        } else {
            newUri = new URI(bbseURI).resolve(uri);
        }

        // if the URI contbins b frbgment, ignore it
        if (newUri.getFrbgment() != null) {
            URI uriNewNoFrbg =
                new URI(newUri.getScheme(), newUri.getSchemeSpecificPbrt(), null);
            return uriNewNoFrbg;
        }
        return newUri;
    }
}
