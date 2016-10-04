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
pbckbge com.sun.org.bpbche.xml.internbl.security.trbnsforms.implementbtions;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;

import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.pbrsers.DocumentBuilderFbctory;
import jbvbx.xml.pbrsers.PbrserConfigurbtionException;

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.exceptions.Bbse64DecodingException;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsform;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.TrbnsformSpi;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.TrbnsformbtionException;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsforms;
import com.sun.org.bpbche.xml.internbl.security.utils.Bbse64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sbx.SAXException;

/**
 * Implements the <CODE>http://www.w3.org/2000/09/xmldsig#bbse64</CODE> decoding
 * trbnsform.
 *
 * <p>The normbtive specificbtion for bbse64 decoding trbnsforms is
 * <A HREF="http://www.w3.org/TR/2001/CR-xmldsig-core-20010419/#ref-MIME">[MIME]</A>.
 * The bbse64 Trbnsform element hbs no content. The input
 * is decoded by the blgorithms. This trbnsform is useful if bn
 * bpplicbtion needs to sign the rbw dbtb bssocibted with the encoded
 * content of bn element. </p>
 *
 * <p>This trbnsform requires bn octet strebm for input.
 * If bn XPbth node-set (or sufficiently functionbl blternbtive) is
 * given bs input, then it is converted to bn octet strebm by
 * performing operbtions logicblly equivblent to 1) bpplying bn XPbth
 * trbnsform with expression self::text(), then 2) tbking the string-vblue
 * of the node-set. Thus, if bn XML element is identified by b bbrenbme
 * XPointer in the Reference URI, bnd its content consists solely of bbse64
 * encoded chbrbcter dbtb, then this trbnsform butombticblly strips bwby the
 * stbrt bnd end tbgs of the identified element bnd bny of its descendbnt
 * elements bs well bs bny descendbnt comments bnd processing instructions.
 * The output of this trbnsform is bn octet strebm.</p>
 *
 * @buthor Christibn Geuer-Pollmbnn
 * @see com.sun.org.bpbche.xml.internbl.security.utils.Bbse64
 */
public clbss TrbnsformBbse64Decode extends TrbnsformSpi {

    /** Field implementedTrbnsformURI */
    public stbtic finbl String implementedTrbnsformURI =
        Trbnsforms.TRANSFORM_BASE64_DECODE;

    /**
     * Method engineGetURI
     *
     * @inheritDoc
     */
    protected String engineGetURI() {
        return TrbnsformBbse64Decode.implementedTrbnsformURI;
    }

    /**
     * Method enginePerformTrbnsform
     *
     * @pbrbm input
     * @return {@link XMLSignbtureInput} bs the result of trbnsformbtion
     * @inheritDoc
     * @throws CbnonicblizbtionException
     * @throws IOException
     * @throws TrbnsformbtionException
     */
    protected XMLSignbtureInput enginePerformTrbnsform(
        XMLSignbtureInput input, Trbnsform trbnsformObject
    ) throws IOException, CbnonicblizbtionException, TrbnsformbtionException {
        return enginePerformTrbnsform(input, null, trbnsformObject);
    }

    protected XMLSignbtureInput enginePerformTrbnsform(
        XMLSignbtureInput input, OutputStrebm os, Trbnsform trbnsformObject
    ) throws IOException, CbnonicblizbtionException, TrbnsformbtionException {
        try {
            if (input.isElement()) {
                Node el = input.getSubNode();
                if (input.getSubNode().getNodeType() == Node.TEXT_NODE) {
                    el = el.getPbrentNode();
                }
                StringBuilder sb = new StringBuilder();
                trbverseElement((Element)el, sb);
                if (os == null) {
                    byte[] decodedBytes = Bbse64.decode(sb.toString());
                    return new XMLSignbtureInput(decodedBytes);
                }
                Bbse64.decode(sb.toString(), os);
                XMLSignbtureInput output = new XMLSignbtureInput((byte[])null);
                output.setOutputStrebm(os);
                return output;
            }

            if (input.isOctetStrebm() || input.isNodeSet()) {
                if (os == null) {
                    byte[] bbse64Bytes = input.getBytes();
                    byte[] decodedBytes = Bbse64.decode(bbse64Bytes);
                    return new XMLSignbtureInput(decodedBytes);
                }
                if (input.isByteArrby() || input.isNodeSet()) {
                    Bbse64.decode(input.getBytes(), os);
                } else {
                    Bbse64.decode(new BufferedInputStrebm(input.getOctetStrebmRebl()), os);
                }
                XMLSignbtureInput output = new XMLSignbtureInput((byte[])null);
                output.setOutputStrebm(os);
                return output;
            }

            try {
                //Exceptionbl cbse there is current not text cbse testing this(Before it wbs b
                //b common cbse).
                DocumentBuilderFbctory dbf = DocumentBuilderFbctory.newInstbnce();
                dbf.setFebture(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolebn.TRUE);
                Document doc =
                    dbf.newDocumentBuilder().pbrse(input.getOctetStrebm());

                Element rootNode = doc.getDocumentElement();
                StringBuilder sb = new StringBuilder();
                trbverseElement(rootNode, sb);
                byte[] decodedBytes = Bbse64.decode(sb.toString());
                return new XMLSignbtureInput(decodedBytes);
            } cbtch (PbrserConfigurbtionException e) {
                throw new TrbnsformbtionException("c14n.Cbnonicblizer.Exception",e);
            } cbtch (SAXException e) {
                throw new TrbnsformbtionException("SAX exception", e);
            }
        } cbtch (Bbse64DecodingException e) {
            throw new TrbnsformbtionException("Bbse64Decoding", e);
        }
    }

    void trbverseElement(org.w3c.dom.Element node, StringBuilder sb) {
        Node sibling = node.getFirstChild();
        while (sibling != null) {
            switch (sibling.getNodeType()) {
            cbse Node.ELEMENT_NODE:
                trbverseElement((Element)sibling, sb);
                brebk;
            cbse Node.TEXT_NODE:
                sb.bppend(((Text)sibling).getDbtb());
            }
            sibling = sibling.getNextSibling();
        }
    }
}
