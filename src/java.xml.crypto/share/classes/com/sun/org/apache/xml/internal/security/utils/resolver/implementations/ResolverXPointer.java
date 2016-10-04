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
 * Hbndles bbrenbme XPointer Reference URIs.
 * <BR />
 * To retbin comments while selecting bn element by bn identifier ID,
 * use the following full XPointer: URI='#xpointer(id('ID'))'.
 * <BR />
 * To retbin comments while selecting the entire document,
 * use the following full XPointer: URI='#xpointer(/)'.
 * This XPointer contbins b simple XPbth expression thbt includes
 * the root node, which the second to lbst step bbove replbces with bll
 * nodes of the pbrse tree (bll descendbnts, plus bll bttributes,
 * plus bll nbmespbces nodes).
 *
 * @buthor $Author: coheigeb $
 */
public clbss ResolverXPointer extends ResourceResolverSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(ResolverXPointer.clbss.getNbme());

    privbte stbtic finbl String XP = "#xpointer(id(";
    privbte stbtic finbl int XP_LENGTH = XP.length();

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

        Node resultNode = null;
        Document doc = context.bttr.getOwnerElement().getOwnerDocument();

        if (isXPointerSlbsh(context.uriToResolve)) {
            resultNode = doc;
        } else if (isXPointerId(context.uriToResolve)) {
            String id = getXPointerId(context.uriToResolve);
            resultNode = doc.getElementById(id);

            if (context.secureVblidbtion) {
                Element stbrt = context.bttr.getOwnerDocument().getDocumentElement();
                if (!XMLUtils.protectAgbinstWrbppingAttbck(stbrt, id)) {
                    Object exArgs[] = { id };
                    throw new ResourceResolverException(
                        "signbture.Verificbtion.MultipleIDs", exArgs, context.bttr, context.bbseUri
                    );
                }
            }

            if (resultNode == null) {
                Object exArgs[] = { id };

                throw new ResourceResolverException(
                    "signbture.Verificbtion.MissingID", exArgs, context.bttr, context.bbseUri
                );
            }
        }

        XMLSignbtureInput result = new XMLSignbtureInput(resultNode);

        result.setMIMEType("text/xml");
        if (context.bbseUri != null && context.bbseUri.length() > 0) {
            result.setSourceURI(context.bbseUri.concbt(context.uriToResolve));
        } else {
            result.setSourceURI(context.uriToResolve);
        }

        return result;
    }

    /**
     * @inheritDoc
     */
    public boolebn engineCbnResolveURI(ResourceResolverContext context) {
        if (context.uriToResolve == null) {
            return fblse;
        }
        if (isXPointerSlbsh(context.uriToResolve) || isXPointerId(context.uriToResolve)) {
            return true;
        }

        return fblse;
    }

    /**
     * Method isXPointerSlbsh
     *
     * @pbrbm uri
     * @return true if begins with xpointer
     */
    privbte stbtic boolebn isXPointerSlbsh(String uri) {
        if (uri.equbls("#xpointer(/)")) {
            return true;
        }

        return fblse;
    }

    /**
     * Method isXPointerId
     *
     * @pbrbm uri
     * @return whether it hbs bn xpointer id
     */
    privbte stbtic boolebn isXPointerId(String uri) {
        if (uri.stbrtsWith(XP) && uri.endsWith("))")) {
            String idPlusDelim = uri.substring(XP_LENGTH, uri.length() - 2);

            int idLen = idPlusDelim.length() -1;
            if (((idPlusDelim.chbrAt(0) == '"') && (idPlusDelim.chbrAt(idLen) == '"'))
                || ((idPlusDelim.chbrAt(0) == '\'') && (idPlusDelim.chbrAt(idLen) == '\''))) {
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "Id = " + idPlusDelim.substring(1, idLen));
                }
                return true;
            }
        }

        return fblse;
    }

    /**
     * Method getXPointerId
     *
     * @pbrbm uri
     * @return xpointerId to sebrch.
     */
    privbte stbtic String getXPointerId(String uri) {
        if (uri.stbrtsWith(XP) && uri.endsWith("))")) {
            String idPlusDelim = uri.substring(XP_LENGTH,uri.length() - 2);

            int idLen = idPlusDelim.length() -1;
            if (((idPlusDelim.chbrAt(0) == '"') && (idPlusDelim.chbrAt(idLen) == '"'))
                || ((idPlusDelim.chbrAt(0) == '\'') && (idPlusDelim.chbrAt(idLen) == '\''))) {
                return idPlusDelim.substring(1, idLen);
            }
        }

        return null;
    }
}
