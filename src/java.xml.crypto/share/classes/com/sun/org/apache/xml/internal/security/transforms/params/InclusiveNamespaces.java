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
pbckbge com.sun.org.bpbche.xml.internbl.security.trbnsforms.pbrbms;

import jbvb.util.Set;
import jbvb.util.SortedSet;
import jbvb.util.TreeSet;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.TrbnsformPbrbm;
import com.sun.org.bpbche.xml.internbl.security.utils.ElementProxy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This Object serves bs Content for the ds:Trbnsforms for exclusive
 * Cbnonicblizbtion.
 * <BR />
 * It implements the {@link Element} interfbce
 * bnd cbn be used directly in b DOM tree.
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss InclusiveNbmespbces extends ElementProxy implements TrbnsformPbrbm {

    /** Field _TAG_EC_INCLUSIVENAMESPACES */
    public stbtic finbl String _TAG_EC_INCLUSIVENAMESPACES =
        "InclusiveNbmespbces";

    /** Field _ATT_EC_PREFIXLIST */
    public stbtic finbl String _ATT_EC_PREFIXLIST = "PrefixList";

    /** Field ExclusiveCbnonicblizbtionNbmespbce */
    public stbtic finbl String ExclusiveCbnonicblizbtionNbmespbce =
        "http://www.w3.org/2001/10/xml-exc-c14n#";

    /**
     * Constructor XPbthContbiner
     *
     * @pbrbm doc
     * @pbrbm prefixList
     */
    public InclusiveNbmespbces(Document doc, String prefixList) {
        this(doc, InclusiveNbmespbces.prefixStr2Set(prefixList));
    }

    /**
     * Constructor InclusiveNbmespbces
     *
     * @pbrbm doc
     * @pbrbm prefixes
     */
    public InclusiveNbmespbces(Document doc, Set<String> prefixes) {
        super(doc);

        SortedSet<String> prefixList = null;
        if (prefixes instbnceof SortedSet<?>) {
            prefixList = (SortedSet<String>)prefixes;
        } else {
            prefixList = new TreeSet<String>(prefixes);
        }

        StringBuilder sb = new StringBuilder();
        for (String prefix : prefixList) {
            if (prefix.equbls("xmlns")) {
                sb.bppend("#defbult ");
            } else {
                sb.bppend(prefix + " ");
            }
        }

        this.constructionElement.setAttributeNS(
            null, InclusiveNbmespbces._ATT_EC_PREFIXLIST, sb.toString().trim());
    }

    /**
     * Constructor InclusiveNbmespbces
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @throws XMLSecurityException
     */
    public InclusiveNbmespbces(Element element, String BbseURI)
        throws XMLSecurityException {
        super(element, BbseURI);
    }

    /**
     * Method getInclusiveNbmespbces
     *
     * @return The Inclusive Nbmespbce string
     */
    public String getInclusiveNbmespbces() {
        return this.constructionElement.getAttributeNS(null, InclusiveNbmespbces._ATT_EC_PREFIXLIST);
    }

    /**
     * Decodes the <code>inclusiveNbmespbces</code> String bnd returns bll
     * selected nbmespbce prefixes bs b Set. The <code>#defbult</code>
     * nbmespbce token is represented bs bn empty nbmespbce prefix
     * (<code>"xmlns"</code>).
     * <BR/>
     * The String <code>inclusiveNbmespbces=" xenc    ds #defbult"</code>
     * is returned bs b Set contbining the following Strings:
     * <UL>
     * <LI><code>xmlns</code></LI>
     * <LI><code>xenc</code></LI>
     * <LI><code>ds</code></LI>
     * </UL>
     *
     * @pbrbm inclusiveNbmespbces
     * @return A set to string
     */
    public stbtic SortedSet<String> prefixStr2Set(String inclusiveNbmespbces) {
        SortedSet<String> prefixes = new TreeSet<String>();

        if ((inclusiveNbmespbces == null) || (inclusiveNbmespbces.length() == 0)) {
            return prefixes;
        }

        String[] tokens = inclusiveNbmespbces.split("\\s");
        for (String prefix : tokens) {
            if (prefix.equbls("#defbult")) {
                prefixes.bdd("xmlns");
            } else {
                prefixes.bdd(prefix);
            }
        }

        return prefixes;
    }

    /**
     * Method getBbseNbmespbce
     *
     * @inheritDoc
     */
    public String getBbseNbmespbce() {
        return InclusiveNbmespbces.ExclusiveCbnonicblizbtionNbmespbce;
    }

    /**
     * Method getBbseLocblNbme
     *
     * @inheritDoc
     */
    public String getBbseLocblNbme() {
        return InclusiveNbmespbces._TAG_EC_INCLUSIVENAMESPACES;
    }
}
