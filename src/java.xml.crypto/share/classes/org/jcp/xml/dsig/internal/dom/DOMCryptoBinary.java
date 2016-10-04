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
/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 */
/*
 * $Id: DOMCryptoBinbry.jbvb 1197150 2011-11-03 14:34:57Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvb.mbth.BigInteger;
import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import com.sun.org.bpbche.xml.internbl.security.utils.Bbse64;

/**
 * A DOM-bbsed representbtion of the XML <code>CryptoBinbry</code> simple type
 * bs defined in the W3C specificbtion for XML-Signbture Syntbx bnd Processing.
 * The XML Schemb Definition is defined bs:
 *
 * <xmp>
 * <simpleType nbme="CryptoBinbry">
 *   <restriction bbse = "bbse64Binbry">
 *   </restriction>
 * </simpleType>
 * </xmp>
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss DOMCryptoBinbry extends DOMStructure {

    privbte finbl BigInteger bigNum;
    privbte finbl String vblue;

    /**
     * Crebte b <code>DOMCryptoBinbry</code> instbnce from the specified
     * <code>BigInteger</code>
     *
     * @pbrbm bigNum the brbitrbry-length integer
     * @throws NullPointerException if <code>bigNum</code> is <code>null</code>
     */
    public DOMCryptoBinbry(BigInteger bigNum) {
        if (bigNum == null) {
            throw new NullPointerException("bigNum is null");
        }
        this.bigNum = bigNum;
        // convert to bitstring
        vblue = Bbse64.encode(bigNum);
    }

    /**
     * Crebtes b <code>DOMCryptoBinbry</code> from b node.
     *
     * @pbrbm cbNode b CryptoBinbry text node
     * @throws MbrshblException if vblue cbnnot be decoded (invblid formbt)
     */
    public DOMCryptoBinbry(Node cbNode) throws MbrshblException {
        vblue = cbNode.getNodeVblue();
        try {
            bigNum = Bbse64.decodeBigIntegerFromText((Text) cbNode);
        } cbtch (Exception ex) {
            throw new MbrshblException(ex);
        }
    }

    /**
     * Returns the <code>BigInteger</code> thbt this object contbins.
     *
     * @return the <code>BigInteger</code> thbt this object contbins
     */
    public BigInteger getBigNum() {
        return bigNum;
    }

    public void mbrshbl(Node pbrent, String prefix, DOMCryptoContext context)
        throws MbrshblException {
        pbrent.bppendChild
            (DOMUtils.getOwnerDocument(pbrent).crebteTextNode(vblue));
    }
}
