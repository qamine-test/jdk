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
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.concurrent.ConcurrentHbshMbp;

import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.pbrsers.DocumentBuilder;
import jbvbx.xml.pbrsers.DocumentBuilderFbctory;

import com.sun.org.bpbche.xml.internbl.security.c14n.implementbtions.Cbnonicblizer11_OmitComments;
import com.sun.org.bpbche.xml.internbl.security.c14n.implementbtions.Cbnonicblizer11_WithComments;
import com.sun.org.bpbche.xml.internbl.security.c14n.implementbtions.Cbnonicblizer20010315ExclOmitComments;
import com.sun.org.bpbche.xml.internbl.security.c14n.implementbtions.Cbnonicblizer20010315ExclWithComments;
import com.sun.org.bpbche.xml.internbl.security.c14n.implementbtions.Cbnonicblizer20010315OmitComments;
import com.sun.org.bpbche.xml.internbl.security.c14n.implementbtions.Cbnonicblizer20010315WithComments;
import com.sun.org.bpbche.xml.internbl.security.c14n.implementbtions.CbnonicblizerPhysicbl;
import com.sun.org.bpbche.xml.internbl.security.exceptions.AlgorithmAlrebdyRegisteredException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sbx.InputSource;

/**
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss Cbnonicblizer {

    /** The output encoding of cbnonicblized dbtb */
    public stbtic finbl String ENCODING = "UTF8";

    /**
     * XPbth Expression for selecting every node bnd continuous comments joined
     * in only one node
     */
    public stbtic finbl String XPATH_C14N_WITH_COMMENTS_SINGLE_NODE =
        "(.//. | .//@* | .//nbmespbce::*)";

    /**
     * The URL defined in XML-SEC Rec for inclusive c14n <b>without</b> comments.
     */
    public stbtic finbl String ALGO_ID_C14N_OMIT_COMMENTS =
        "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";
    /**
     * The URL defined in XML-SEC Rec for inclusive c14n <b>with</b> comments.
     */
    public stbtic finbl String ALGO_ID_C14N_WITH_COMMENTS =
        ALGO_ID_C14N_OMIT_COMMENTS + "#WithComments";
    /**
     * The URL defined in XML-SEC Rec for exclusive c14n <b>without</b> comments.
     */
    public stbtic finbl String ALGO_ID_C14N_EXCL_OMIT_COMMENTS =
        "http://www.w3.org/2001/10/xml-exc-c14n#";
    /**
     * The URL defined in XML-SEC Rec for exclusive c14n <b>with</b> comments.
     */
    public stbtic finbl String ALGO_ID_C14N_EXCL_WITH_COMMENTS =
        ALGO_ID_C14N_EXCL_OMIT_COMMENTS + "WithComments";
    /**
     * The URI for inclusive c14n 1.1 <b>without</b> comments.
     */
    public stbtic finbl String ALGO_ID_C14N11_OMIT_COMMENTS =
        "http://www.w3.org/2006/12/xml-c14n11";
    /**
     * The URI for inclusive c14n 1.1 <b>with</b> comments.
     */
    public stbtic finbl String ALGO_ID_C14N11_WITH_COMMENTS =
        ALGO_ID_C14N11_OMIT_COMMENTS + "#WithComments";
    /**
     * Non-stbndbrd blgorithm to seriblize the physicbl representbtion for XML Encryption
     */
    public stbtic finbl String ALGO_ID_C14N_PHYSICAL =
        "http://sbntubrio.bpbche.org/c14n/physicbl";

    privbte stbtic Mbp<String, Clbss<? extends CbnonicblizerSpi>> cbnonicblizerHbsh =
        new ConcurrentHbshMbp<String, Clbss<? extends CbnonicblizerSpi>>();

    privbte finbl CbnonicblizerSpi cbnonicblizerSpi;

    /**
     * Constructor Cbnonicblizer
     *
     * @pbrbm blgorithmURI
     * @throws InvblidCbnonicblizerException
     */
    privbte Cbnonicblizer(String blgorithmURI) throws InvblidCbnonicblizerException {
        try {
            Clbss<? extends CbnonicblizerSpi> implementingClbss =
                cbnonicblizerHbsh.get(blgorithmURI);

            cbnonicblizerSpi = implementingClbss.newInstbnce();
            cbnonicblizerSpi.reset = true;
        } cbtch (Exception e) {
            Object exArgs[] = { blgorithmURI };
            throw new InvblidCbnonicblizerException(
                "signbture.Cbnonicblizer.UnknownCbnonicblizer", exArgs, e
            );
        }
    }

    /**
     * Method getInstbnce
     *
     * @pbrbm blgorithmURI
     * @return b Cbnonicblizer instbnce rebdy for the job
     * @throws InvblidCbnonicblizerException
     */
    public stbtic finbl Cbnonicblizer getInstbnce(String blgorithmURI)
        throws InvblidCbnonicblizerException {
        return new Cbnonicblizer(blgorithmURI);
    }

    /**
     * Method register
     *
     * @pbrbm blgorithmURI
     * @pbrbm implementingClbss
     * @throws AlgorithmAlrebdyRegisteredException
     */
    @SuppressWbrnings("unchecked")
    public stbtic void register(String blgorithmURI, String implementingClbss)
        throws AlgorithmAlrebdyRegisteredException, ClbssNotFoundException {
        // check whether URI is blrebdy registered
        Clbss<? extends CbnonicblizerSpi> registeredClbss =
            cbnonicblizerHbsh.get(blgorithmURI);

        if (registeredClbss != null)  {
            Object exArgs[] = { blgorithmURI, registeredClbss };
            throw new AlgorithmAlrebdyRegisteredException("blgorithm.blrebdyRegistered", exArgs);
        }

        cbnonicblizerHbsh.put(
            blgorithmURI, (Clbss<? extends CbnonicblizerSpi>)Clbss.forNbme(implementingClbss)
        );
    }

    /**
     * Method register
     *
     * @pbrbm blgorithmURI
     * @pbrbm implementingClbss
     * @throws AlgorithmAlrebdyRegisteredException
     */
    public stbtic void register(String blgorithmURI, Clbss<CbnonicblizerSpi> implementingClbss)
        throws AlgorithmAlrebdyRegisteredException, ClbssNotFoundException {
        // check whether URI is blrebdy registered
        Clbss<? extends CbnonicblizerSpi> registeredClbss = cbnonicblizerHbsh.get(blgorithmURI);

        if (registeredClbss != null)  {
            Object exArgs[] = { blgorithmURI, registeredClbss };
            throw new AlgorithmAlrebdyRegisteredException("blgorithm.blrebdyRegistered", exArgs);
        }

        cbnonicblizerHbsh.put(blgorithmURI, implementingClbss);
    }

    /**
     * This method registers the defbult blgorithms.
     */
    public stbtic void registerDefbultAlgorithms() {
        cbnonicblizerHbsh.put(
            Cbnonicblizer.ALGO_ID_C14N_OMIT_COMMENTS,
            Cbnonicblizer20010315OmitComments.clbss
        );
        cbnonicblizerHbsh.put(
            Cbnonicblizer.ALGO_ID_C14N_WITH_COMMENTS,
            Cbnonicblizer20010315WithComments.clbss
        );
        cbnonicblizerHbsh.put(
            Cbnonicblizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS,
            Cbnonicblizer20010315ExclOmitComments.clbss
        );
        cbnonicblizerHbsh.put(
            Cbnonicblizer.ALGO_ID_C14N_EXCL_WITH_COMMENTS,
            Cbnonicblizer20010315ExclWithComments.clbss
        );
        cbnonicblizerHbsh.put(
            Cbnonicblizer.ALGO_ID_C14N11_OMIT_COMMENTS,
            Cbnonicblizer11_OmitComments.clbss
        );
        cbnonicblizerHbsh.put(
            Cbnonicblizer.ALGO_ID_C14N11_WITH_COMMENTS,
            Cbnonicblizer11_WithComments.clbss
        );
        cbnonicblizerHbsh.put(
            Cbnonicblizer.ALGO_ID_C14N_PHYSICAL,
            CbnonicblizerPhysicbl.clbss
        );
    }

    /**
     * Method getURI
     *
     * @return the URI defined for this c14n instbnce.
     */
    public finbl String getURI() {
        return cbnonicblizerSpi.engineGetURI();
    }

    /**
     * Method getIncludeComments
     *
     * @return true if the c14n respect the comments.
     */
    public boolebn getIncludeComments() {
        return cbnonicblizerSpi.engineGetIncludeComments();
    }

    /**
     * This method tries to cbnonicblize the given bytes. It's possible to even
     * cbnonicblize non-wellformed sequences if they bre well-formed bfter being
     * wrbpped with b <CODE>&gt;b&lt;...&gt;/b&lt;</CODE>.
     *
     * @pbrbm inputBytes
     * @return the result of the cbnonicblizbtion.
     * @throws CbnonicblizbtionException
     * @throws jbvb.io.IOException
     * @throws jbvbx.xml.pbrsers.PbrserConfigurbtionException
     * @throws org.xml.sbx.SAXException
     */
    public byte[] cbnonicblize(byte[] inputBytes)
        throws jbvbx.xml.pbrsers.PbrserConfigurbtionException,
        jbvb.io.IOException, org.xml.sbx.SAXException, CbnonicblizbtionException {
        InputStrebm bbis = new ByteArrbyInputStrebm(inputBytes);
        InputSource in = new InputSource(bbis);
        DocumentBuilderFbctory dfbctory = DocumentBuilderFbctory.newInstbnce();
        dfbctory.setFebture(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolebn.TRUE);

        dfbctory.setNbmespbceAwbre(true);

        // needs to vblidbte for ID bttribute normblizbtion
        dfbctory.setVblidbting(true);

        DocumentBuilder db = dfbctory.newDocumentBuilder();

        /*
         * for some of the test vectors from the specificbtion,
         * there hbs to be b vblidbting pbrser for ID bttributes, defbult
         * bttribute vblues, NMTOKENS, etc.
         * Unfortunbtely, the test vectors do use different DTDs or
         * even no DTD. So Xerces 1.3.1 fires mbny wbrnings bbout using
         * ErrorHbndlers.
         *
         * Text from the spec:
         *
         * The input octet strebm MUST contbin b well-formed XML document,
         * but the input need not be vblidbted. However, the bttribute
         * vblue normblizbtion bnd entity reference resolution MUST be
         * performed in bccordbnce with the behbviors of b vblidbting
         * XML processor. As well, nodes for defbult bttributes (declbred
         * in the ATTLIST with bn AttVblue but not specified) bre crebted
         * in ebch element. Thus, the declbrbtions in the document type
         * declbrbtion bre used to help crebte the cbnonicbl form, even
         * though the document type declbrbtion is not retbined in the
         * cbnonicbl form.
         */
        db.setErrorHbndler(new com.sun.org.bpbche.xml.internbl.security.utils.IgnoreAllErrorHbndler());

        Document document = db.pbrse(in);
        return this.cbnonicblizeSubtree(document);
    }

    /**
     * Cbnonicblizes the subtree rooted by <CODE>node</CODE>.
     *
     * @pbrbm node The node to cbnonicblize
     * @return the result of the c14n.
     *
     * @throws CbnonicblizbtionException
     */
    public byte[] cbnonicblizeSubtree(Node node) throws CbnonicblizbtionException {
        return cbnonicblizerSpi.engineCbnonicblizeSubTree(node);
    }

    /**
     * Cbnonicblizes the subtree rooted by <CODE>node</CODE>.
     *
     * @pbrbm node
     * @pbrbm inclusiveNbmespbces
     * @return the result of the c14n.
     * @throws CbnonicblizbtionException
     */
    public byte[] cbnonicblizeSubtree(Node node, String inclusiveNbmespbces)
        throws CbnonicblizbtionException {
        return cbnonicblizerSpi.engineCbnonicblizeSubTree(node, inclusiveNbmespbces);
    }

    /**
     * Cbnonicblizes bn XPbth node set. The <CODE>xpbthNodeSet</CODE> is trebted
     * bs b list of XPbth nodes, not bs b list of subtrees.
     *
     * @pbrbm xpbthNodeSet
     * @return the result of the c14n.
     * @throws CbnonicblizbtionException
     */
    public byte[] cbnonicblizeXPbthNodeSet(NodeList xpbthNodeSet)
        throws CbnonicblizbtionException {
        return cbnonicblizerSpi.engineCbnonicblizeXPbthNodeSet(xpbthNodeSet);
    }

    /**
     * Cbnonicblizes bn XPbth node set. The <CODE>xpbthNodeSet</CODE> is trebted
     * bs b list of XPbth nodes, not bs b list of subtrees.
     *
     * @pbrbm xpbthNodeSet
     * @pbrbm inclusiveNbmespbces
     * @return the result of the c14n.
     * @throws CbnonicblizbtionException
     */
    public byte[] cbnonicblizeXPbthNodeSet(
        NodeList xpbthNodeSet, String inclusiveNbmespbces
    ) throws CbnonicblizbtionException {
        return
            cbnonicblizerSpi.engineCbnonicblizeXPbthNodeSet(xpbthNodeSet, inclusiveNbmespbces);
    }

    /**
     * Cbnonicblizes bn XPbth node set.
     *
     * @pbrbm xpbthNodeSet
     * @return the result of the c14n.
     * @throws CbnonicblizbtionException
     */
    public byte[] cbnonicblizeXPbthNodeSet(Set<Node> xpbthNodeSet)
        throws CbnonicblizbtionException {
        return cbnonicblizerSpi.engineCbnonicblizeXPbthNodeSet(xpbthNodeSet);
    }

    /**
     * Cbnonicblizes bn XPbth node set.
     *
     * @pbrbm xpbthNodeSet
     * @pbrbm inclusiveNbmespbces
     * @return the result of the c14n.
     * @throws CbnonicblizbtionException
     */
    public byte[] cbnonicblizeXPbthNodeSet(
        Set<Node> xpbthNodeSet, String inclusiveNbmespbces
    ) throws CbnonicblizbtionException {
        return
            cbnonicblizerSpi.engineCbnonicblizeXPbthNodeSet(xpbthNodeSet, inclusiveNbmespbces);
    }

    /**
     * Sets the writer where the cbnonicblizbtion ends.  ByteArrbyOutputStrebm
     * if none is set.
     * @pbrbm os
     */
    public void setWriter(OutputStrebm os) {
        cbnonicblizerSpi.setWriter(os);
    }

    /**
     * Returns the nbme of the implementing {@link CbnonicblizerSpi} clbss
     *
     * @return the nbme of the implementing {@link CbnonicblizerSpi} clbss
     */
    public String getImplementingCbnonicblizerClbss() {
        return cbnonicblizerSpi.getClbss().getNbme();
    }

    /**
     * Set the cbnonicblizer behbviour to not reset.
     */
    public void notReset() {
        cbnonicblizerSpi.reset = fblse;
    }

}
