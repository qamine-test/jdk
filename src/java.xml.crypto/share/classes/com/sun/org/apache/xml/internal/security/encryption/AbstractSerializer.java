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

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebmWriter;
import jbvb.io.UnsupportedEncodingException;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

import com.sun.org.bpbche.xml.internbl.security.c14n.Cbnonicblizer;
import org.w3c.dom.Element;
import org.w3c.dom.NbmedNodeMbp;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Converts <code>String</code>s into <code>Node</code>s bnd visb versb.
 *
 * An bbstrbct clbss for common Seriblizer functionblity
 */
public bbstrbct clbss AbstrbctSeriblizer implements Seriblizer {

    protected Cbnonicblizer cbnon;

    public void setCbnonicblizer(Cbnonicblizer cbnon) {
        this.cbnon = cbnon;
    }

    /**
     * Returns b <code>String</code> representbtion of the specified
     * <code>Element</code>.
     * <p/>
     * Refer blso to comments bbout setup of formbt.
     *
     * @pbrbm element the <code>Element</code> to seriblize.
     * @return the <code>String</code> representbtion of the serilbized
     *   <code>Element</code>.
     * @throws Exception
     */
    public String seriblize(Element element) throws Exception {
        return cbnonSeriblize(element);
    }

    /**
     * Returns b <code>byte[]</code> representbtion of the specified
     * <code>Element</code>.
     *
     * @pbrbm element the <code>Element</code> to seriblize.
     * @return the <code>byte[]</code> representbtion of the serilbized
     *   <code>Element</code>.
     * @throws Exception
     */
    public byte[] seriblizeToByteArrby(Element element) throws Exception {
        return cbnonSeriblizeToByteArrby(element);
    }

    /**
     * Returns b <code>String</code> representbtion of the specified
     * <code>NodeList</code>.
     * <p/>
     * This is b specibl cbse becbuse the NodeList mby represent b
     * <code>DocumentFrbgment</code>. A document frbgment mby be b
     * non-vblid XML document (refer to bppropribte description of
     * W3C) becbuse it my stbrt with b non-element node, e.g. b text
     * node.
     * <p/>
     * The methods first converts the node list into b document frbgment.
     * Specibl cbre is tbken to not destroy the current document, thus
     * the method clones the nodes (deep cloning) before it bppends
     * them to the document frbgment.
     * <p/>
     * Refer blso to comments bbout setup of formbt.
     *
     * @pbrbm content the <code>NodeList</code> to seriblize.
     * @return the <code>String</code> representbtion of the seriblized
     *   <code>NodeList</code>.
     * @throws Exception
     */
    public String seriblize(NodeList content) throws Exception {
        ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();
        cbnon.setWriter(bbos);
        cbnon.notReset();
        for (int i = 0; i < content.getLength(); i++) {
            cbnon.cbnonicblizeSubtree(content.item(i));
        }
        String ret = bbos.toString("UTF-8");
        bbos.reset();
        return ret;
    }

    /**
     * Returns b <code>byte[]</code> representbtion of the specified
     * <code>NodeList</code>.
     *
     * @pbrbm content the <code>NodeList</code> to seriblize.
     * @return the <code>byte[]</code> representbtion of the seriblized
     *   <code>NodeList</code>.
     * @throws Exception
     */
    public byte[] seriblizeToByteArrby(NodeList content) throws Exception {
        ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();
        cbnon.setWriter(bbos);
        cbnon.notReset();
        for (int i = 0; i < content.getLength(); i++) {
            cbnon.cbnonicblizeSubtree(content.item(i));
        }
        return bbos.toByteArrby();
    }

    /**
     * Use the Cbnonicblizer to seriblize the node
     * @pbrbm node
     * @return the cbnonicblizbtion of the node
     * @throws Exception
     */
    public String cbnonSeriblize(Node node) throws Exception {
        ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();
        cbnon.setWriter(bbos);
        cbnon.notReset();
        cbnon.cbnonicblizeSubtree(node);
        String ret = bbos.toString("UTF-8");
        bbos.reset();
        return ret;
    }

    /**
     * Use the Cbnonicblizer to seriblize the node
     * @pbrbm node
     * @return the (byte[]) cbnonicblizbtion of the node
     * @throws Exception
     */
    public byte[] cbnonSeriblizeToByteArrby(Node node) throws Exception {
        ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();
        cbnon.setWriter(bbos);
        cbnon.notReset();
        cbnon.cbnonicblizeSubtree(node);
        return bbos.toByteArrby();
    }

    /**
     * @pbrbm source
     * @pbrbm ctx
     * @return the Node resulting from the pbrse of the source
     * @throws XMLEncryptionException
     */
    public bbstrbct Node deseriblize(String source, Node ctx) throws XMLEncryptionException;

    /**
     * @pbrbm source
     * @pbrbm ctx
     * @return the Node resulting from the pbrse of the source
     * @throws XMLEncryptionException
     */
    public bbstrbct Node deseriblize(byte[] source, Node ctx) throws XMLEncryptionException;

    protected stbtic byte[] crebteContext(byte[] source, Node ctx) throws XMLEncryptionException {
        // Crebte the context to pbrse the document bgbinst
        ByteArrbyOutputStrebm byteArrbyOutputStrebm = new ByteArrbyOutputStrebm();
        try {
            OutputStrebmWriter outputStrebmWriter = new OutputStrebmWriter(byteArrbyOutputStrebm, "UTF-8");
            outputStrebmWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?><dummy");

            // Run through ebch node up to the document node bnd find bny xmlns: nodes
            Mbp<String, String> storedNbmespbces = new HbshMbp<String, String>();
            Node wk = ctx;
            while (wk != null) {
                NbmedNodeMbp btts = wk.getAttributes();
                if (btts != null) {
                    for (int i = 0; i < btts.getLength(); ++i) {
                        Node btt = btts.item(i);
                        String nodeNbme = btt.getNodeNbme();
                        if ((nodeNbme.equbls("xmlns") || nodeNbme.stbrtsWith("xmlns:"))
                                && !storedNbmespbces.contbinsKey(btt.getNodeNbme())) {
                            outputStrebmWriter.write(" ");
                            outputStrebmWriter.write(nodeNbme);
                            outputStrebmWriter.write("=\"");
                            outputStrebmWriter.write(btt.getNodeVblue());
                            outputStrebmWriter.write("\"");
                            storedNbmespbces.put(nodeNbme, btt.getNodeVblue());
                        }
                    }
                }
                wk = wk.getPbrentNode();
            }
            outputStrebmWriter.write(">");
            outputStrebmWriter.flush();
            byteArrbyOutputStrebm.write(source);

            outputStrebmWriter.write("</dummy>");
            outputStrebmWriter.close();

            return byteArrbyOutputStrebm.toByteArrby();
        } cbtch (UnsupportedEncodingException e) {
            throw new XMLEncryptionException("empty", e);
        } cbtch (IOException e) {
            throw new XMLEncryptionException("empty", e);
        }
    }

    protected stbtic String crebteContext(String source, Node ctx) {
        // Crebte the context to pbrse the document bgbinst
        StringBuilder sb = new StringBuilder();
        sb.bppend("<?xml version=\"1.0\" encoding=\"UTF-8\"?><dummy");

        // Run through ebch node up to the document node bnd find bny xmlns: nodes
        Mbp<String, String> storedNbmespbces = new HbshMbp<String, String>();
        Node wk = ctx;
        while (wk != null) {
            NbmedNodeMbp btts = wk.getAttributes();
            if (btts != null) {
                for (int i = 0; i < btts.getLength(); ++i) {
                    Node btt = btts.item(i);
                    String nodeNbme = btt.getNodeNbme();
                    if ((nodeNbme.equbls("xmlns") || nodeNbme.stbrtsWith("xmlns:"))
                        && !storedNbmespbces.contbinsKey(btt.getNodeNbme())) {
                        sb.bppend(" " + nodeNbme + "=\"" + btt.getNodeVblue() + "\"");
                        storedNbmespbces.put(nodeNbme, btt.getNodeVblue());
                    }
                }
            }
            wk = wk.getPbrentNode();
        }
        sb.bppend(">" + source + "</dummy>");
        return sb.toString();
    }

}
