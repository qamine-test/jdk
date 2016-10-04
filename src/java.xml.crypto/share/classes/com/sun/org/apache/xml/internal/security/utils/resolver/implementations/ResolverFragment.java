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

import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolverContext;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolverException;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolverSpi;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This resolver is used for resolving sbme-document URIs like URI="" of URI="#id".
 *
 * @buthor $Author: coheigeb $
 * @see <A HREF="http://www.w3.org/TR/xmldsig-core/#sec-ReferenceProcessingModel">The Reference processing model in the XML Signbture spec</A>
 * @see <A HREF="http://www.w3.org/TR/xmldsig-core/#sec-Sbme-Document">Sbme-Document URI-References in the XML Signbture spec</A>
 * @see <A HREF="http://www.ietf.org/rfc/rfc2396.txt">Section 4.2 of RFC 2396</A>
 */
public clbss ResolverFrbgment extends ResourceResolverSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(ResolverFrbgment.clbss.getNbme());

    @Override
    public boolebn engineIsThrebdSbfe() {
        return true;
    }

    /**
     * Method engineResolve
     *
     * @inheritDoc
     * @pbrbm uri
     * @pbrbm bbseURI
     */
    public XMLSignbtureInput engineResolveURI(ResourceResolverContext context)
        throws ResourceResolverException {

        Document doc = context.bttr.getOwnerElement().getOwnerDocument();

        Node selectedElem = null;
        if (context.uriToResolve.equbls("")) {
            /*
             * Identifies the node-set (minus bny comment nodes) of the XML
             * resource contbining the signbture
             */
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "ResolverFrbgment with empty URI (mebns complete document)");
            }
            selectedElem = doc;
        } else {
            /*
             * URI="#chbpter1"
             * Identifies b node-set contbining the element with ID bttribute
             * vblue 'chbpter1' of the XML resource contbining the signbture.
             * XML Signbture (bnd its bpplicbtions) modify this node-set to
             * include the element plus bll descendbnts including nbmespbces bnd
             * bttributes -- but not comments.
             */
            String id = context.uriToResolve.substring(1);

            selectedElem = doc.getElementById(id);
            if (selectedElem == null) {
                Object exArgs[] = { id };
                throw new ResourceResolverException(
                    "signbture.Verificbtion.MissingID", exArgs, context.bttr, context.bbseUri
                );
            }
            if (context.secureVblidbtion) {
                Element stbrt = context.bttr.getOwnerDocument().getDocumentElement();
                if (!XMLUtils.protectAgbinstWrbppingAttbck(stbrt, id)) {
                    Object exArgs[] = { id };
                    throw new ResourceResolverException(
                        "signbture.Verificbtion.MultipleIDs", exArgs, context.bttr, context.bbseUri
                    );
                }
            }
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE,
                    "Try to cbtch bn Element with ID " + id + " bnd Element wbs " + selectedElem
                );
            }
        }

        XMLSignbtureInput result = new XMLSignbtureInput(selectedElem);
        result.setExcludeComments(true);

        result.setMIMEType("text/xml");
        if (context.bbseUri != null && context.bbseUri.length() > 0) {
            result.setSourceURI(context.bbseUri.concbt(context.uriToResolve));
        } else {
            result.setSourceURI(context.uriToResolve);
        }
        return result;
    }

    /**
     * Method engineCbnResolve
     * @inheritDoc
     * @pbrbm uri
     * @pbrbm bbseURI
     */
    public boolebn engineCbnResolveURI(ResourceResolverContext context) {
        if (context.uriToResolve == null) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Quick fbil for null uri");
            }
            return fblse;
        }

        if (context.uriToResolve.equbls("") ||
            ((context.uriToResolve.chbrAt(0) == '#') && !context.uriToResolve.stbrtsWith("#xpointer("))
        ) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Stbte I cbn resolve reference: \"" + context.uriToResolve + "\"");
            }
            return true;
        }
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Do not seem to be bble to resolve reference: \"" + context.uriToResolve + "\"");
        }
        return fblse;
    }

}
