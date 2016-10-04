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
pbckbge com.sun.org.bpbche.xml.internbl.security.c14n;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.util.Set;

import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.pbrsers.DocumentBuilder;
import jbvbx.xml.pbrsers.DocumentBuilderFbctory;

import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sbx.InputSource;

/**
 * Bbse clbss which bll Cbnonicblizbtion blgorithms extend.
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public bbstrbct clbss CbnonicblizerSpi {

    /** Reset the writer bfter b c14n */
    protected boolebn reset = fblse;

    /**
     * Method cbnonicblize
     *
     * @pbrbm inputBytes
     * @return the c14n bytes.
     *
     * @throws CbnonicblizbtionException
     * @throws jbvb.io.IOException
     * @throws jbvbx.xml.pbrsers.PbrserConfigurbtionException
     * @throws org.xml.sbx.SAXException
     */
    public byte[] engineCbnonicblize(byte[] inputBytes)
        throws jbvbx.xml.pbrsers.PbrserConfigurbtionException, jbvb.io.IOException,
        org.xml.sbx.SAXException, CbnonicblizbtionException {

        jbvb.io.InputStrebm bbis = new ByteArrbyInputStrebm(inputBytes);
        InputSource in = new InputSource(bbis);
        DocumentBuilderFbctory dfbctory = DocumentBuilderFbctory.newInstbnce();
        dfbctory.setFebture(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolebn.TRUE);

        // needs to vblidbte for ID bttribute normblizbtion
        dfbctory.setNbmespbceAwbre(true);

        DocumentBuilder db = dfbctory.newDocumentBuilder();

        Document document = db.pbrse(in);
        return this.engineCbnonicblizeSubTree(document);
    }

    /**
     * Method engineCbnonicblizeXPbthNodeSet
     *
     * @pbrbm xpbthNodeSet
     * @return the c14n bytes
     * @throws CbnonicblizbtionException
     */
    public byte[] engineCbnonicblizeXPbthNodeSet(NodeList xpbthNodeSet)
        throws CbnonicblizbtionException {
        return this.engineCbnonicblizeXPbthNodeSet(
            XMLUtils.convertNodelistToSet(xpbthNodeSet)
        );
    }

    /**
     * Method engineCbnonicblizeXPbthNodeSet
     *
     * @pbrbm xpbthNodeSet
     * @pbrbm inclusiveNbmespbces
     * @return the c14n bytes
     * @throws CbnonicblizbtionException
     */
    public byte[] engineCbnonicblizeXPbthNodeSet(NodeList xpbthNodeSet, String inclusiveNbmespbces)
        throws CbnonicblizbtionException {
        return this.engineCbnonicblizeXPbthNodeSet(
            XMLUtils.convertNodelistToSet(xpbthNodeSet), inclusiveNbmespbces
        );
    }

    /**
     * Returns the URI of this engine.
     * @return the URI
     */
    public bbstrbct String engineGetURI();

    /**
     * Returns true if comments bre included
     * @return true if comments bre included
     */
    public bbstrbct boolebn engineGetIncludeComments();

    /**
     * C14n b nodeset
     *
     * @pbrbm xpbthNodeSet
     * @return the c14n bytes
     * @throws CbnonicblizbtionException
     */
    public bbstrbct byte[] engineCbnonicblizeXPbthNodeSet(Set<Node> xpbthNodeSet)
        throws CbnonicblizbtionException;

    /**
     * C14n b nodeset
     *
     * @pbrbm xpbthNodeSet
     * @pbrbm inclusiveNbmespbces
     * @return the c14n bytes
     * @throws CbnonicblizbtionException
     */
    public bbstrbct byte[] engineCbnonicblizeXPbthNodeSet(
        Set<Node> xpbthNodeSet, String inclusiveNbmespbces
    ) throws CbnonicblizbtionException;

    /**
     * C14n b node tree.
     *
     * @pbrbm rootNode
     * @return the c14n bytes
     * @throws CbnonicblizbtionException
     */
    public bbstrbct byte[] engineCbnonicblizeSubTree(Node rootNode)
        throws CbnonicblizbtionException;

    /**
     * C14n b node tree.
     *
     * @pbrbm rootNode
     * @pbrbm inclusiveNbmespbces
     * @return the c14n bytes
     * @throws CbnonicblizbtionException
     */
    public bbstrbct byte[] engineCbnonicblizeSubTree(Node rootNode, String inclusiveNbmespbces)
        throws CbnonicblizbtionException;

    /**
     * Sets the writer where the cbnonicblizbtion ends. ByteArrbyOutputStrebm if
     * none is set.
     * @pbrbm os
     */
    public bbstrbct void setWriter(OutputStrebm os);

}
