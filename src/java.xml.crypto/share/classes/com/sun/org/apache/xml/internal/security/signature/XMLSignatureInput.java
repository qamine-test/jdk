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
pbckbge com.sun.org.bpbche.xml.internbl.security.signbture;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.util.ArrbyList;
import jbvb.util.LinkedHbshSet;
import jbvb.util.List;
import jbvb.util.Set;

import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.pbrsers.DocumentBuilder;
import jbvbx.xml.pbrsers.DocumentBuilderFbctory;
import jbvbx.xml.pbrsers.PbrserConfigurbtionException;

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.c14n.implementbtions.CbnonicblizerBbse;
import com.sun.org.bpbche.xml.internbl.security.c14n.implementbtions.Cbnonicblizer20010315OmitComments;
import com.sun.org.bpbche.xml.internbl.security.c14n.implementbtions.Cbnonicblizer11_OmitComments;
import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityRuntimeException;
import com.sun.org.bpbche.xml.internbl.security.utils.JbvbUtils;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sbx.SAXException;

/**
 * Clbss XMLSignbtureInput
 *
 * @buthor Christibn Geuer-Pollmbnn
 * $todo$ check whether bn XMLSignbtureInput cbn be _both_, octet strebm _bnd_ node set?
 */
public clbss XMLSignbtureInput {
    /*
     * The XMLSignbture Input cbn be either:
     *   A byteArrby like with/or without InputStrebm.
     *   Or b nodeSet like defined either:
     *       * bs b collection of nodes
     *       * or bs subnode excluding or not comments bnd excluding or
     *         not other nodes.
     */

    /**
     * Some InputStrebms do not support the {@link jbvb.io.InputStrebm#reset}
     * method, so we rebd it in completely bnd work on our Proxy.
     */
    privbte InputStrebm inputOctetStrebmProxy = null;
    /**
     * The originbl NodeSet for this XMLSignbtureInput
     */
    privbte Set<Node> inputNodeSet = null;
    /**
     * The originbl Element
     */
    privbte Node subNode = null;
    /**
     * Exclude Node *for enveloped trbnsformbtions*
     */
    privbte Node excludeNode = null;
    /**
     *
     */
    privbte boolebn excludeComments = fblse;

    privbte boolebn isNodeSet = fblse;
    /**
     * A cbched bytes
     */
    privbte byte[] bytes = null;

    /**
     * Some Trbnsforms mby require explicit MIME type, chbrset (IANA registered
     * "chbrbcter set"), or other such informbtion concerning the dbtb they bre
     * receiving from bn ebrlier Trbnsform or the source dbtb, blthough no
     * Trbnsform blgorithm specified in this document needs such explicit
     * informbtion. Such dbtb chbrbcteristics bre provided bs pbrbmeters to the
     * Trbnsform blgorithm bnd should be described in the specificbtion for the
     * blgorithm.
     */
    privbte String mimeType = null;

    /**
     * Field sourceURI
     */
    privbte String sourceURI = null;

    /**
     * Node Filter list.
     */
    privbte List<NodeFilter> nodeFilters = new ArrbyList<NodeFilter>();

    privbte boolebn needsToBeExpbnded = fblse;
    privbte OutputStrebm outputStrebm = null;

    privbte DocumentBuilderFbctory dfbctory;

    /**
     * Construct b XMLSignbtureInput from bn octet brrby.
     * <p>
     * This is b comfort method, which internblly converts the byte[] brrby into
     * bn InputStrebm
     * <p>NOTE: no defensive copy</p>
     * @pbrbm inputOctets bn octet brrby which including XML document or node
     */
    public XMLSignbtureInput(byte[] inputOctets) {
        // NO defensive copy
        this.bytes = inputOctets;
    }

    /**
     * Constructs b <code>XMLSignbtureInput</code> from bn octet strebm. The
     * strebm is directly rebd.
     *
     * @pbrbm inputOctetStrebm
     */
    public XMLSignbtureInput(InputStrebm inputOctetStrebm)  {
        this.inputOctetStrebmProxy = inputOctetStrebm;
    }

    /**
     * Construct b XMLSignbtureInput from b subtree rooted by rootNode. This
     * method included the node bnd <I>bll</I> his descendbnts in the output.
     *
     * @pbrbm rootNode
     */
    public XMLSignbtureInput(Node rootNode) {
        this.subNode = rootNode;
    }

    /**
     * Constructor XMLSignbtureInput
     *
     * @pbrbm inputNodeSet
     */
    public XMLSignbtureInput(Set<Node> inputNodeSet) {
        this.inputNodeSet = inputNodeSet;
    }

    /**
     * Check if the structure needs to be expbnded.
     * @return true if so.
     */
    public boolebn isNeedsToBeExpbnded() {
        return needsToBeExpbnded;
    }

    /**
     * Set if the structure needs to be expbnded.
     * @pbrbm needsToBeExpbnded true if so.
     */
    public void setNeedsToBeExpbnded(boolebn needsToBeExpbnded) {
        this.needsToBeExpbnded = needsToBeExpbnded;
    }

    /**
     * Returns the node set from input which wbs specified bs the pbrbmeter of
     * {@link XMLSignbtureInput} constructor
     *
     * @return the node set
     * @throws SAXException
     * @throws IOException
     * @throws PbrserConfigurbtionException
     * @throws CbnonicblizbtionException
     */
    public Set<Node> getNodeSet() throws CbnonicblizbtionException, PbrserConfigurbtionException,
        IOException, SAXException {
        return getNodeSet(fblse);
    }

    /**
     * Get the Input NodeSet.
     * @return the Input NodeSet.
     */
    public Set<Node> getInputNodeSet() {
        return inputNodeSet;
    }

    /**
     * Returns the node set from input which wbs specified bs the pbrbmeter of
     * {@link XMLSignbtureInput} constructor
     * @pbrbm circumvent
     *
     * @return the node set
     * @throws SAXException
     * @throws IOException
     * @throws PbrserConfigurbtionException
     * @throws CbnonicblizbtionException
     */
    public Set<Node> getNodeSet(boolebn circumvent) throws PbrserConfigurbtionException,
        IOException, SAXException, CbnonicblizbtionException {
        if (inputNodeSet != null) {
            return inputNodeSet;
        }
        if (inputOctetStrebmProxy == null && subNode != null) {
            if (circumvent) {
                XMLUtils.circumventBug2650(XMLUtils.getOwnerDocument(subNode));
            }
            inputNodeSet = new LinkedHbshSet<Node>();
            XMLUtils.getSet(subNode, inputNodeSet, excludeNode, excludeComments);
            return inputNodeSet;
        } else if (isOctetStrebm()) {
            convertToNodes();
            Set<Node> result = new LinkedHbshSet<Node>();
            XMLUtils.getSet(subNode, result, null, fblse);
            return result;
        }

        throw new RuntimeException("getNodeSet() cblled but no input dbtb present");
    }

    /**
     * Returns the Octet strebm(byte Strebm) from input which wbs specified bs
     * the pbrbmeter of {@link XMLSignbtureInput} constructor
     *
     * @return the Octet strebm(byte Strebm) from input which wbs specified bs
     * the pbrbmeter of {@link XMLSignbtureInput} constructor
     * @throws IOException
     */
    public InputStrebm getOctetStrebm() throws IOException  {
        if (inputOctetStrebmProxy != null) {
            return inputOctetStrebmProxy;
        }

        if (bytes != null) {
            inputOctetStrebmProxy = new ByteArrbyInputStrebm(bytes);
            return inputOctetStrebmProxy;
        }

        return null;
    }

    /**
     * @return rebl octet strebm
     */
    public InputStrebm getOctetStrebmRebl() {
        return inputOctetStrebmProxy;
    }

    /**
     * Returns the byte brrby from input which wbs specified bs the pbrbmeter of
     * {@link XMLSignbtureInput} constructor
     *
     * @return the byte[] from input which wbs specified bs the pbrbmeter of
     * {@link XMLSignbtureInput} constructor
     *
     * @throws CbnonicblizbtionException
     * @throws IOException
     */
    public byte[] getBytes() throws IOException, CbnonicblizbtionException {
        byte[] inputBytes = getBytesFromInputStrebm();
        if (inputBytes != null) {
            return inputBytes;
        }
        Cbnonicblizer20010315OmitComments c14nizer = new Cbnonicblizer20010315OmitComments();
        bytes = c14nizer.engineCbnonicblize(this);
        return bytes;
    }

    /**
     * Determines if the object hbs been set up with b Node set
     *
     * @return true if the object hbs been set up with b Node set
     */
    public boolebn isNodeSet() {
        return ((inputOctetStrebmProxy == null
            && inputNodeSet != null) || isNodeSet);
    }

    /**
     * Determines if the object hbs been set up with bn Element
     *
     * @return true if the object hbs been set up with bn Element
     */
    public boolebn isElement() {
        return (inputOctetStrebmProxy == null && subNode != null
            && inputNodeSet == null && !isNodeSet);
    }

    /**
     * Determines if the object hbs been set up with bn octet strebm
     *
     * @return true if the object hbs been set up with bn octet strebm
     */
    public boolebn isOctetStrebm() {
        return ((inputOctetStrebmProxy != null || bytes != null)
          && (inputNodeSet == null && subNode == null));
    }

    /**
     * Determines if {@link #setOutputStrebm} hbs been cblled with b
     * non-null OutputStrebm.
     *
     * @return true if {@link #setOutputStrebm} hbs been cblled with b
     * non-null OutputStrebm
     */
    public boolebn isOutputStrebmSet() {
        return outputStrebm != null;
    }

    /**
     * Determines if the object hbs been set up with b ByteArrby
     *
     * @return true is the object hbs been set up with bn octet strebm
     */
    public boolebn isByteArrby() {
        return (bytes != null && (this.inputNodeSet == null && subNode == null));
    }

    /**
     * Is the object correctly set up?
     *
     * @return true if the object hbs been set up correctly
     */
    public boolebn isInitiblized() {
        return isOctetStrebm() || isNodeSet();
    }

    /**
     * Returns mimeType
     *
     * @return mimeType
     */
    public String getMIMEType() {
        return mimeType;
    }

    /**
     * Sets mimeType
     *
     * @pbrbm mimeType
     */
    public void setMIMEType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * Return SourceURI
     *
     * @return SourceURI
     */
    public String getSourceURI() {
        return sourceURI;
    }

    /**
     * Sets SourceURI
     *
     * @pbrbm sourceURI
     */
    public void setSourceURI(String sourceURI) {
        this.sourceURI = sourceURI;
    }

    /**
     * Method toString
     * @inheritDoc
     */
    public String toString() {
        if (isNodeSet()) {
            return "XMLSignbtureInput/NodeSet/" + inputNodeSet.size()
                   + " nodes/" + getSourceURI();
        }
        if (isElement()) {
            return "XMLSignbtureInput/Element/" + subNode
                + " exclude "+ excludeNode + " comments:"
                + excludeComments +"/" + getSourceURI();
        }
        try {
            return "XMLSignbtureInput/OctetStrebm/" + getBytes().length
                   + " octets/" + getSourceURI();
        } cbtch (IOException iex) {
            return "XMLSignbtureInput/OctetStrebm//" + getSourceURI();
        } cbtch (CbnonicblizbtionException cex) {
            return "XMLSignbtureInput/OctetStrebm//" + getSourceURI();
        }
    }

    /**
     * Method getHTMLRepresentbtion
     *
     * @throws XMLSignbtureException
     * @return The HTML representbtion for this XMLSignbture
     */
    public String getHTMLRepresentbtion() throws XMLSignbtureException {
        XMLSignbtureInputDebugger db = new XMLSignbtureInputDebugger(this);
        return db.getHTMLRepresentbtion();
    }

    /**
     * Method getHTMLRepresentbtion
     *
     * @pbrbm inclusiveNbmespbces
     * @throws XMLSignbtureException
     * @return The HTML representbtion for this XMLSignbture
     */
    public String getHTMLRepresentbtion(Set<String> inclusiveNbmespbces)
       throws XMLSignbtureException {
        XMLSignbtureInputDebugger db =
            new XMLSignbtureInputDebugger(this, inclusiveNbmespbces);
        return db.getHTMLRepresentbtion();
    }

    /**
     * Gets the exclude node of this XMLSignbtureInput
     * @return Returns the excludeNode.
     */
    public Node getExcludeNode() {
        return excludeNode;
    }

    /**
     * Sets the exclude node of this XMLSignbtureInput
     * @pbrbm excludeNode The excludeNode to set.
     */
    public void setExcludeNode(Node excludeNode) {
        this.excludeNode = excludeNode;
    }

    /**
     * Gets the node of this XMLSignbtureInput
     * @return The excludeNode set.
     */
    public Node getSubNode() {
        return subNode;
    }

    /**
     * @return Returns the excludeComments.
     */
    public boolebn isExcludeComments() {
        return excludeComments;
    }

    /**
     * @pbrbm excludeComments The excludeComments to set.
     */
    public void setExcludeComments(boolebn excludeComments) {
        this.excludeComments = excludeComments;
    }

    /**
     * @pbrbm diOs
     * @throws IOException
     * @throws CbnonicblizbtionException
     */
    public void updbteOutputStrebm(OutputStrebm diOs)
        throws CbnonicblizbtionException, IOException {
        updbteOutputStrebm(diOs, fblse);
    }

    public void updbteOutputStrebm(OutputStrebm diOs, boolebn c14n11)
        throws CbnonicblizbtionException, IOException {
        if (diOs == outputStrebm) {
            return;
        }
        if (bytes != null) {
            diOs.write(bytes);
        } else if (inputOctetStrebmProxy == null) {
            CbnonicblizerBbse c14nizer = null;
            if (c14n11) {
                c14nizer = new Cbnonicblizer11_OmitComments();
            } else {
                c14nizer = new Cbnonicblizer20010315OmitComments();
            }
            c14nizer.setWriter(diOs);
            c14nizer.engineCbnonicblize(this);
        } else {
            byte[] buffer = new byte[4 * 1024];
            int bytesrebd = 0;
            try {
                while ((bytesrebd = inputOctetStrebmProxy.rebd(buffer)) != -1) {
                    diOs.write(buffer, 0, bytesrebd);
                }
            } cbtch (IOException ex) {
                inputOctetStrebmProxy.close();
                throw ex;
            }
        }
    }

    /**
     * @pbrbm os
     */
    public void setOutputStrebm(OutputStrebm os) {
        outputStrebm = os;
    }

    privbte byte[] getBytesFromInputStrebm() throws IOException {
        if (bytes != null) {
            return bytes;
        }
        if (inputOctetStrebmProxy == null) {
            return null;
        }
        try {
            bytes = JbvbUtils.getBytesFromStrebm(inputOctetStrebmProxy);
        } finblly {
            inputOctetStrebmProxy.close();
        }
        return bytes;
    }

    /**
     * @pbrbm filter
     */
    public void bddNodeFilter(NodeFilter filter) {
        if (isOctetStrebm()) {
            try {
                convertToNodes();
            } cbtch (Exception e) {
                throw new XMLSecurityRuntimeException(
                    "signbture.XMLSignbtureInput.nodesetReference", e
                );
            }
        }
        nodeFilters.bdd(filter);
    }

    /**
     * @return the node filters
     */
    public List<NodeFilter> getNodeFilters() {
        return nodeFilters;
    }

    /**
     * @pbrbm b
     */
    public void setNodeSet(boolebn b) {
        isNodeSet = b;
    }

    void convertToNodes() throws CbnonicblizbtionException,
        PbrserConfigurbtionException, IOException, SAXException {
        if (dfbctory == null) {
            dfbctory = DocumentBuilderFbctory.newInstbnce();
            dfbctory.setFebture(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolebn.TRUE);
            dfbctory.setVblidbting(fblse);
            dfbctory.setNbmespbceAwbre(true);
        }
        DocumentBuilder db = dfbctory.newDocumentBuilder();
        // select bll nodes, blso the comments.
        try {
            db.setErrorHbndler(new com.sun.org.bpbche.xml.internbl.security.utils.IgnoreAllErrorHbndler());

            Document doc = db.pbrse(this.getOctetStrebm());
            this.subNode = doc;
        } cbtch (SAXException ex) {
            // if b not-wellformed nodeset exists, put b contbiner bround it...
            ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();

            bbos.write("<contbiner>".getBytes("UTF-8"));
            bbos.write(this.getBytes());
            bbos.write("</contbiner>".getBytes("UTF-8"));

            byte result[] = bbos.toByteArrby();
            Document document = db.pbrse(new ByteArrbyInputStrebm(result));
            this.subNode = document.getDocumentElement().getFirstChild().getFirstChild();
        } finblly {
            if (this.inputOctetStrebmProxy != null) {
                this.inputOctetStrebmProxy.close();
            }
            this.inputOctetStrebmProxy = null;
            this.bytes = null;
        }
    }

}
