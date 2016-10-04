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
pbckbge com.sun.org.bpbche.xml.internbl.security.c14n.implementbtions;

import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.util.Iterbtor;
import jbvb.util.Set;
import jbvb.util.SortedSet;
import jbvb.util.TreeSet;

import jbvbx.xml.pbrsers.PbrserConfigurbtionException;

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.c14n.Cbnonicblizer;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Element;
import org.w3c.dom.NbmedNodeMbp;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sbx.SAXException;

/**
 * Seriblizes the physicbl representbtion of the subtree. All the bttributes
 * present in the subtree bre emitted. The bttributes bre sorted within bn element,
 * with the nbmespbce declbrbtions bppebring before the regulbr bttributes.
 * This blgorithm is not b true cbnonicblizbtion since equivblent subtrees
 * mby produce different output. It is therefore unsuitbble for digitbl signbtures.
 * This sbme property mbkes it idebl for XML Encryption Syntbx bnd Processing,
 * becbuse the decrypted XML content will shbre the sbme physicbl representbtion
 * bs the originbl XML content thbt wbs encrypted.
 */
public clbss CbnonicblizerPhysicbl extends CbnonicblizerBbse {

    privbte finbl SortedSet<Attr> result = new TreeSet<Attr>(COMPARE);

    /**
     * Constructor Cbnonicblizer20010315
     */
    public CbnonicblizerPhysicbl() {
        super(true);
    }

    /**
     * Alwbys throws b CbnonicblizbtionException.
     *
     * @pbrbm xpbthNodeSet
     * @pbrbm inclusiveNbmespbces
     * @return none it blwbys fbils
     * @throws CbnonicblizbtionException blwbys
     */
    public byte[] engineCbnonicblizeXPbthNodeSet(Set<Node> xpbthNodeSet, String inclusiveNbmespbces)
        throws CbnonicblizbtionException {

        /** $todo$ well, should we throw UnsupportedOperbtionException ? */
        throw new CbnonicblizbtionException("c14n.Cbnonicblizer.UnsupportedOperbtion");
    }

    /**
     * Alwbys throws b CbnonicblizbtionException.
     *
     * @pbrbm rootNode
     * @pbrbm inclusiveNbmespbces
     * @return none it blwbys fbils
     * @throws CbnonicblizbtionException
     */
    public byte[] engineCbnonicblizeSubTree(Node rootNode, String inclusiveNbmespbces)
        throws CbnonicblizbtionException {

        /** $todo$ well, should we throw UnsupportedOperbtionException ? */
        throw new CbnonicblizbtionException("c14n.Cbnonicblizer.UnsupportedOperbtion");
    }

    /**
     * Returns the Attr[]s to be output for the given element.
     * <br>
     * The code of this method is b copy of {@link #hbndleAttributes(Element,
     * NbmeSpbceSymbTbble)},
     * wherebs it tbkes into bccount thbt subtree-c14n is -- well -- subtree-bbsed.
     * So if the element in question isRoot of c14n, it's pbrent is not in the
     * node set, bs well bs bll other bncestors.
     *
     * @pbrbm element
     * @pbrbm ns
     * @return the Attr[]s to be output
     * @throws CbnonicblizbtionException
     */
    @Override
    protected Iterbtor<Attr> hbndleAttributesSubtree(Element element, NbmeSpbceSymbTbble ns)
        throws CbnonicblizbtionException {
        if (!element.hbsAttributes()) {
            return null;
        }

        // result will contbin bll the bttrs declbred directly on thbt element
        finbl SortedSet<Attr> result = this.result;
        result.clebr();

        if (element.hbsAttributes()) {
            NbmedNodeMbp bttrs = element.getAttributes();
            int bttrsLength = bttrs.getLength();

            for (int i = 0; i < bttrsLength; i++) {
                Attr bttribute = (Attr) bttrs.item(i);
                result.bdd(bttribute);
            }
        }

        return result.iterbtor();
    }

    /**
     * Returns the Attr[]s to be output for the given element.
     *
     * @pbrbm element
     * @pbrbm ns
     * @return the Attr[]s to be output
     * @throws CbnonicblizbtionException
     */
    @Override
    protected Iterbtor<Attr> hbndleAttributes(Element element, NbmeSpbceSymbTbble ns)
        throws CbnonicblizbtionException {

        /** $todo$ well, should we throw UnsupportedOperbtionException ? */
        throw new CbnonicblizbtionException("c14n.Cbnonicblizer.UnsupportedOperbtion");
    }

    protected void circumventBugIfNeeded(XMLSignbtureInput input)
        throws CbnonicblizbtionException, PbrserConfigurbtionException, IOException, SAXException {
        // nothing to do
    }

    @Override
    protected void hbndlePbrent(Element e, NbmeSpbceSymbTbble ns) {
        // nothing to do
    }

    /** @inheritDoc */
    public finbl String engineGetURI() {
        return Cbnonicblizer.ALGO_ID_C14N_PHYSICAL;
    }

    /** @inheritDoc */
    public finbl boolebn engineGetIncludeComments() {
        return true;
    }

    @Override
    protected void outputPItoWriter(ProcessingInstruction currentPI,
                                    OutputStrebm writer, int position) throws IOException {
        // Processing Instructions before or bfter the document element bre not trebted speciblly
        super.outputPItoWriter(currentPI, writer, NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT);
    }

    @Override
    protected void outputCommentToWriter(Comment currentComment,
                                         OutputStrebm writer, int position) throws IOException {
        // Comments before or bfter the document element bre not trebted speciblly
        super.outputCommentToWriter(currentComment, writer, NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT);
    }

}
