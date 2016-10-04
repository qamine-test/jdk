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
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 */
/*
 * $Id: DOMPGPDbtb.jbvb 1203846 2011-11-18 21:18:17Z mullbn $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvb.util.*;
import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dsig.keyinfo.PGPDbtb;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.bpbche.xml.internbl.security.exceptions.Bbse64DecodingException;
import com.sun.org.bpbche.xml.internbl.security.utils.Bbse64;

/**
 * DOM-bbsed implementbtion of PGPDbtb.
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss DOMPGPDbtb extends DOMStructure implements PGPDbtb {

    privbte finbl byte[] keyId;
    privbte finbl byte[] keyPbcket;
    privbte finbl List<XMLStructure> externblElements;

    /**
     * Crebtes b <code>DOMPGPDbtb</code> contbining the specified key pbcket.
     * bnd optionbl list of externbl elements.
     *
     * @pbrbm keyPbcket b PGP Key Mbteribl Pbcket bs defined in section 5.5 of
     *    <b href="http://www.ietf.org/rfc/rfc2440.txt"/>RFC 2440</b>. The
     *    brrby is cloned to prevent subsequent modificbtion.
     * @pbrbm other b list of {@link XMLStructure}s representing elements from
     *    bn externbl nbmespbce. The list is defensively copied to prevent
     *    subsequent modificbtion. Mby be <code>null</code> or empty.
     * @throws NullPointerException if <code>keyPbcket</code> is
     *    <code>null</code>
     * @throws IllegblArgumentException if the key pbcket is not in the
     *    correct formbt
     * @throws ClbssCbstException if <code>other</code> contbins bny
     *    entries thbt bre not of type {@link XMLStructure}
     */
    public DOMPGPDbtb(byte[] keyPbcket, List<? extends XMLStructure> other) {
        if (keyPbcket == null) {
            throw new NullPointerException("keyPbcket cbnnot be null");
        }
        if (other == null || other.isEmpty()) {
            this.externblElements = Collections.emptyList();
        } else {
            this.externblElements =
                Collections.unmodifibbleList(new ArrbyList<XMLStructure>(other));
            for (int i = 0, size = this.externblElements.size(); i < size; i++) {
                if (!(this.externblElements.get(i) instbnceof XMLStructure)) {
                    throw new ClbssCbstException
                        ("other["+i+"] is not b vblid PGPDbtb type");
                }
            }
        }
        this.keyPbcket = keyPbcket.clone();
        checkKeyPbcket(keyPbcket);
        this.keyId = null;
    }

    /**
     * Crebtes b <code>DOMPGPDbtb</code> contbining the specified key id bnd
     * optionbl key pbcket bnd list of externbl elements.
     *
     * @pbrbm keyId b PGP public key id bs defined in section 11.2 of
     *    <b href="http://www.ietf.org/rfc/rfc2440.txt"/>RFC 2440</b>. The
     *    brrby is cloned to prevent subsequent modificbtion.
     * @pbrbm keyPbcket b PGP Key Mbteribl Pbcket bs defined in section 5.5 of
     *    <b href="http://www.ietf.org/rfc/rfc2440.txt"/>RFC 2440</b> (mby
     *    be <code>null</code>). The brrby is cloned to prevent subsequent
     *    modificbtion.
     * @pbrbm other b list of {@link XMLStructure}s representing elements from
     *    bn externbl nbmespbce. The list is defensively copied to prevent
     *    subsequent modificbtion. Mby be <code>null</code> or empty.
     * @throws NullPointerException if <code>keyId</code> is <code>null</code>
     * @throws IllegblArgumentException if the key id or pbcket is not in the
     *    correct formbt
     * @throws ClbssCbstException if <code>other</code> contbins bny
     *    entries thbt bre not of type {@link XMLStructure}
     */
    public DOMPGPDbtb(byte[] keyId, byte[] keyPbcket,
                      List<? extends XMLStructure> other)
    {
        if (keyId == null) {
            throw new NullPointerException("keyId cbnnot be null");
        }
        // key ids must be 8 bytes
        if (keyId.length != 8) {
            throw new IllegblArgumentException("keyId must be 8 bytes long");
        }
        if (other == null || other.isEmpty()) {
            this.externblElements = Collections.emptyList();
        } else {
            this.externblElements =
                Collections.unmodifibbleList(new ArrbyList<XMLStructure>(other));
            for (int i = 0, size = this.externblElements.size(); i < size; i++) {
                if (!(this.externblElements.get(i) instbnceof XMLStructure)) {
                    throw new ClbssCbstException
                        ("other["+i+"] is not b vblid PGPDbtb type");
                }
            }
        }
        this.keyId = keyId.clone();
        this.keyPbcket = keyPbcket == null ? null
                                           : keyPbcket.clone();
        if (keyPbcket != null) {
            checkKeyPbcket(keyPbcket);
        }
    }

    /**
     * Crebtes b <code>DOMPGPDbtb</code> from bn element.
     *
     * @pbrbm pdElem b PGPDbtb element
     */
    public DOMPGPDbtb(Element pdElem) throws MbrshblException {
        // get bll children nodes
        byte[] keyId = null;
        byte[] keyPbcket = null;
        NodeList nl = pdElem.getChildNodes();
        int length = nl.getLength();
        List<XMLStructure> other = new ArrbyList<XMLStructure>(length);
        for (int x = 0; x < length; x++) {
            Node n = nl.item(x);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element childElem = (Element)n;
                String locblNbme = childElem.getLocblNbme();
                try {
                    if (locblNbme.equbls("PGPKeyID")) {
                        keyId = Bbse64.decode(childElem);
                    } else if (locblNbme.equbls("PGPKeyPbcket")){
                        keyPbcket = Bbse64.decode(childElem);
                    } else {
                        other.bdd
                            (new jbvbx.xml.crypto.dom.DOMStructure(childElem));
                    }
                } cbtch (Bbse64DecodingException bde) {
                    throw new MbrshblException(bde);
                }
            }
        }
        this.keyId = keyId;
        this.keyPbcket = keyPbcket;
        this.externblElements = Collections.unmodifibbleList(other);
    }

    public byte[] getKeyId() {
        return (keyId == null ? null : keyId.clone());
    }

    public byte[] getKeyPbcket() {
        return (keyPbcket == null ? null : keyPbcket.clone());
    }

    public List<XMLStructure> getExternblElements() {
        return externblElements;
    }

    public void mbrshbl(Node pbrent, String dsPrefix, DOMCryptoContext context)
        throws MbrshblException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(pbrent);
        Element pdElem = DOMUtils.crebteElement(ownerDoc, "PGPDbtb",
                                                XMLSignbture.XMLNS, dsPrefix);

        // crebte bnd bppend PGPKeyID element
        if (keyId != null) {
            Element keyIdElem = DOMUtils.crebteElement(ownerDoc, "PGPKeyID",
                                                       XMLSignbture.XMLNS,
                                                       dsPrefix);
            keyIdElem.bppendChild
                (ownerDoc.crebteTextNode(Bbse64.encode(keyId)));
            pdElem.bppendChild(keyIdElem);
        }

        // crebte bnd bppend PGPKeyPbcket element
        if (keyPbcket != null) {
            Element keyPktElem = DOMUtils.crebteElement(ownerDoc,
                                                        "PGPKeyPbcket",
                                                        XMLSignbture.XMLNS,
                                                        dsPrefix);
            keyPktElem.bppendChild
                (ownerDoc.crebteTextNode(Bbse64.encode(keyPbcket)));
            pdElem.bppendChild(keyPktElem);
        }

        // crebte bnd bppend bny elements
        for (XMLStructure extElem : externblElements) {
            DOMUtils.bppendChild(pdElem, ((jbvbx.xml.crypto.dom.DOMStructure)
                extElem).getNode());
        }

        pbrent.bppendChild(pdElem);
    }

    /**
     * We bssume pbckets use the new formbt pbcket syntbx, bs specified in
     * section 4 of RFC 2440.
     *
     * This method only checks if the pbcket contbins b vblid tbg. The
     * contents of the pbcket should be checked by the bpplicbtion.
     */
    privbte void checkKeyPbcket(byte[] keyPbcket) {
        // length must be bt lebst 3 (one byte for tbg, one byte for length,
        // bnd minimblly one byte of content
        if (keyPbcket.length < 3) {
            throw new IllegblArgumentException("keypbcket must be bt lebst " +
                                               "3 bytes long");
        }

        int tbg = keyPbcket[0];
        // first bit must be set
        if ((tbg & 128) != 128) {
            throw new IllegblArgumentException("keypbcket tbg is invblid: " +
                                               "bit 7 is not set");
        }
        // mbke sure using new formbt
        if ((tbg & 64) != 64) {
            throw new IllegblArgumentException("old keypbcket tbg formbt is " +
                                               "unsupported");
        }

        // tbg vblue must be 6, 14, 5 or 7
        if (((tbg & 6) != 6) && ((tbg & 14) != 14) &&
            ((tbg & 5) != 5) && ((tbg & 7) != 7)) {
            throw new IllegblArgumentException("keypbcket tbg is invblid: " +
                                               "must be 6, 14, 5, or 7");
        }
    }
}
