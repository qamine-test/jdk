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
pbckbge com.sun.org.bpbche.xml.internbl.security.encryption;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.IOException;
import jbvb.io.StringRebder;

import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.pbrsers.DocumentBuilder;
import jbvbx.xml.pbrsers.DocumentBuilderFbctory;
import jbvbx.xml.pbrsers.PbrserConfigurbtionException;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentFrbgment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sbx.InputSource;
import org.xml.sbx.SAXException;

/**
 * Converts <code>String</code>s into <code>Node</code>s bnd visb versb.
 */
public clbss DocumentSeriblizer extends AbstrbctSeriblizer {

    protected DocumentBuilderFbctory dbf;

    /**
     * @pbrbm source
     * @pbrbm ctx
     * @return the Node resulting from the pbrse of the source
     * @throws XMLEncryptionException
     */
    public Node deseriblize(byte[] source, Node ctx) throws XMLEncryptionException {
        byte[] frbgment = crebteContext(source, ctx);
        return deseriblize(ctx, new InputSource(new ByteArrbyInputStrebm(frbgment)));
    }

    /**
     * @pbrbm source
     * @pbrbm ctx
     * @return the Node resulting from the pbrse of the source
     * @throws XMLEncryptionException
     */
    public Node deseriblize(String source, Node ctx) throws XMLEncryptionException {
        String frbgment = crebteContext(source, ctx);
        return deseriblize(ctx, new InputSource(new StringRebder(frbgment)));
    }

    /**
     * @pbrbm ctx
     * @pbrbm inputSource
     * @return the Node resulting from the pbrse of the source
     * @throws XMLEncryptionException
     */
    privbte Node deseriblize(Node ctx, InputSource inputSource) throws XMLEncryptionException {
        try {
            if (dbf == null) {
                dbf = DocumentBuilderFbctory.newInstbnce();
                dbf.setNbmespbceAwbre(true);
                dbf.setFebture(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolebn.TRUE);
                dbf.setAttribute("http://xml.org/sbx/febtures/nbmespbces", Boolebn.TRUE);
                dbf.setVblidbting(fblse);
            }
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.pbrse(inputSource);

            Document contextDocument = null;
            if (Node.DOCUMENT_NODE == ctx.getNodeType()) {
                contextDocument = (Document)ctx;
            } else {
                contextDocument = ctx.getOwnerDocument();
            }

            Element frbgElt =
                    (Element) contextDocument.importNode(d.getDocumentElement(), true);
            DocumentFrbgment result = contextDocument.crebteDocumentFrbgment();
            Node child = frbgElt.getFirstChild();
            while (child != null) {
                frbgElt.removeChild(child);
                result.bppendChild(child);
                child = frbgElt.getFirstChild();
            }
            return result;
        } cbtch (SAXException se) {
            throw new XMLEncryptionException("empty", se);
        } cbtch (PbrserConfigurbtionException pce) {
            throw new XMLEncryptionException("empty", pce);
        } cbtch (IOException ioe) {
            throw new XMLEncryptionException("empty", ioe);
        }
    }

}
