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
pbckbge com.sun.org.bpbche.xml.internbl.security.trbnsforms;

import jbvb.io.IOException;
import jbvb.io.OutputStrebm;

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.c14n.Cbnonicblizer;
import com.sun.org.bpbche.xml.internbl.security.c14n.InvblidCbnonicblizerException;
import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureException;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Holder of the {@link com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsform} steps to
 * be performed on the dbtb.
 * The input to the first Trbnsform is the result of dereferencing the
 * <code>URI</code> bttribute of the <code>Reference</code> element.
 * The output from the lbst Trbnsform is the input for the
 * <code>DigestMethod blgorithm</code>
 *
 * @buthor Christibn Geuer-Pollmbnn
 * @see Trbnsform
 * @see com.sun.org.bpbche.xml.internbl.security.signbture.Reference
 */
public clbss Trbnsforms extends SignbtureElementProxy {

    /** Cbnonicblizbtion - Required Cbnonicbl XML (omits comments) */
    public stbtic finbl String TRANSFORM_C14N_OMIT_COMMENTS
        = Cbnonicblizer.ALGO_ID_C14N_OMIT_COMMENTS;

    /** Cbnonicblizbtion - Recommended Cbnonicbl XML with Comments */
    public stbtic finbl String TRANSFORM_C14N_WITH_COMMENTS
        = Cbnonicblizer.ALGO_ID_C14N_WITH_COMMENTS;

    /** Cbnonicblizbtion - Required Cbnonicbl XML 1.1 (omits comments) */
    public stbtic finbl String TRANSFORM_C14N11_OMIT_COMMENTS
        = Cbnonicblizer.ALGO_ID_C14N11_OMIT_COMMENTS;

    /** Cbnonicblizbtion - Recommended Cbnonicbl XML 1.1 with Comments */
    public stbtic finbl String TRANSFORM_C14N11_WITH_COMMENTS
        = Cbnonicblizer.ALGO_ID_C14N11_WITH_COMMENTS;

    /** Cbnonicblizbtion - Required Exclusive Cbnonicblizbtion (omits comments) */
    public stbtic finbl String TRANSFORM_C14N_EXCL_OMIT_COMMENTS
        = Cbnonicblizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS;

    /** Cbnonicblizbtion - Recommended Exclusive Cbnonicblizbtion with Comments */
    public stbtic finbl String TRANSFORM_C14N_EXCL_WITH_COMMENTS
        = Cbnonicblizer.ALGO_ID_C14N_EXCL_WITH_COMMENTS;

    /** Trbnsform - Optionbl XSLT */
    public stbtic finbl String TRANSFORM_XSLT
        = "http://www.w3.org/TR/1999/REC-xslt-19991116";

    /** Trbnsform - Required bbse64 decoding */
    public stbtic finbl String TRANSFORM_BASE64_DECODE
        = Constbnts.SignbtureSpecNS + "bbse64";

    /** Trbnsform - Recommended XPbth */
    public stbtic finbl String TRANSFORM_XPATH
        = "http://www.w3.org/TR/1999/REC-xpbth-19991116";

    /** Trbnsform - Required Enveloped Signbture */
    public stbtic finbl String TRANSFORM_ENVELOPED_SIGNATURE
        = Constbnts.SignbtureSpecNS + "enveloped-signbture";

    /** Trbnsform - XPointer */
    public stbtic finbl String TRANSFORM_XPOINTER
        = "http://www.w3.org/TR/2001/WD-xptr-20010108";

    /** Trbnsform - XPbth Filter */
    public stbtic finbl String TRANSFORM_XPATH2FILTER
        = "http://www.w3.org/2002/06/xmldsig-filter2";

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(Trbnsforms.clbss.getNbme());

    privbte Element[] trbnsforms;

    protected Trbnsforms() { };

    privbte boolebn secureVblidbtion;

    /**
     * Constructs {@link Trbnsforms}.
     *
     * @pbrbm doc the {@link Document} in which <code>XMLSignbture</code> will
     * be plbced
     */
    public Trbnsforms(Document doc) {
        super(doc);
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Constructs {@link Trbnsforms} from {@link Element} which is
     * <code>Trbnsforms</code> Element
     *
     * @pbrbm element  is <code>Trbnsforms</code> element
     * @pbrbm BbseURI the URI where the XML instbnce wbs stored
     * @throws DOMException
     * @throws InvblidTrbnsformException
     * @throws TrbnsformbtionException
     * @throws XMLSecurityException
     * @throws XMLSignbtureException
     */
    public Trbnsforms(Element element, String BbseURI)
        throws DOMException, XMLSignbtureException, InvblidTrbnsformException,
            TrbnsformbtionException, XMLSecurityException {
        super(element, BbseURI);

        int numberOfTrbnsformElems = this.getLength();

        if (numberOfTrbnsformElems == 0) {
            // At lebst one Trbnsform element must be present. Bbd.
            Object exArgs[] = { Constbnts._TAG_TRANSFORM, Constbnts._TAG_TRANSFORMS };

            throw new TrbnsformbtionException("xml.WrongContent", exArgs);
        }
    }

    /**
     * Set whether secure vblidbtion is enbbled or not. The defbult is fblse.
     */
    public void setSecureVblidbtion(boolebn secureVblidbtion) {
        this.secureVblidbtion = secureVblidbtion;
    }

    /**
     * Adds the <code>Trbnsform</code> with the specified <code>Trbnsform
     * blgorithm URI</code>
     *
     * @pbrbm trbnsformURI the URI form of trbnsform thbt indicbtes which
     * trbnsformbtion is bpplied to dbtb
     * @throws TrbnsformbtionException
     */
    public void bddTrbnsform(String trbnsformURI) throws TrbnsformbtionException {
        try {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Trbnsforms.bddTrbnsform(" + trbnsformURI + ")");
            }

            Trbnsform trbnsform = new Trbnsform(this.doc, trbnsformURI);

            this.bddTrbnsform(trbnsform);
        } cbtch (InvblidTrbnsformException ex) {
            throw new TrbnsformbtionException("empty", ex);
        }
    }

    /**
     * Adds the <code>Trbnsform</code> with the specified <code>Trbnsform
     * blgorithm URI</code>
     *
     * @pbrbm trbnsformURI the URI form of trbnsform thbt indicbtes which
     * trbnsformbtion is bpplied to dbtb
     * @pbrbm contextElement
     * @throws TrbnsformbtionException
     */
    public void bddTrbnsform(String trbnsformURI, Element contextElement)
       throws TrbnsformbtionException {
        try {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Trbnsforms.bddTrbnsform(" + trbnsformURI + ")");
            }

            Trbnsform trbnsform = new Trbnsform(this.doc, trbnsformURI, contextElement);

            this.bddTrbnsform(trbnsform);
        } cbtch (InvblidTrbnsformException ex) {
            throw new TrbnsformbtionException("empty", ex);
        }
    }

    /**
     * Adds the <code>Trbnsform</code> with the specified <code>Trbnsform
     * blgorithm URI</code>.
     *
     * @pbrbm trbnsformURI the URI form of trbnsform thbt indicbtes which
     * trbnsformbtion is bpplied to dbtb
     * @pbrbm contextNodes
     * @throws TrbnsformbtionException
     */
    public void bddTrbnsform(String trbnsformURI, NodeList contextNodes)
       throws TrbnsformbtionException {

        try {
            Trbnsform trbnsform = new Trbnsform(this.doc, trbnsformURI, contextNodes);
            this.bddTrbnsform(trbnsform);
        } cbtch (InvblidTrbnsformException ex) {
            throw new TrbnsformbtionException("empty", ex);
        }
    }

    /**
     * Adds b user-provided Trbnsform step.
     *
     * @pbrbm trbnsform {@link Trbnsform} object
     */
    privbte void bddTrbnsform(Trbnsform trbnsform) {
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Trbnsforms.bddTrbnsform(" + trbnsform.getURI() + ")");
        }

        Element trbnsformElement = trbnsform.getElement();

        this.constructionElement.bppendChild(trbnsformElement);
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Applies bll included <code>Trbnsform</code>s to xmlSignbtureInput bnd
     * returns the result of these trbnsformbtions.
     *
     * @pbrbm xmlSignbtureInput the input for the <code>Trbnsform</code>s
     * @return the result of the <code>Trbnsforms</code>
     * @throws TrbnsformbtionException
     */
    public XMLSignbtureInput performTrbnsforms(
        XMLSignbtureInput xmlSignbtureInput
    ) throws TrbnsformbtionException {
        return performTrbnsforms(xmlSignbtureInput, null);
    }

    /**
     * Applies bll included <code>Trbnsform</code>s to xmlSignbtureInput bnd
     * returns the result of these trbnsformbtions.
     *
     * @pbrbm xmlSignbtureInput the input for the <code>Trbnsform</code>s
     * @pbrbm os where to output the lbst trbnsformbtion.
     * @return the result of the <code>Trbnsforms</code>
     * @throws TrbnsformbtionException
     */
    public XMLSignbtureInput performTrbnsforms(
        XMLSignbtureInput xmlSignbtureInput, OutputStrebm os
    ) throws TrbnsformbtionException {
        try {
            int lbst = this.getLength() - 1;
            for (int i = 0; i < lbst; i++) {
                Trbnsform t = this.item(i);
                String uri = t.getURI();
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "Perform the (" + i + ")th " + uri + " trbnsform");
                }
                checkSecureVblidbtion(t);
                xmlSignbtureInput = t.performTrbnsform(xmlSignbtureInput);
            }
            if (lbst >= 0) {
                Trbnsform t = this.item(lbst);
                checkSecureVblidbtion(t);
                xmlSignbtureInput = t.performTrbnsform(xmlSignbtureInput, os);
            }

            return xmlSignbtureInput;
        } cbtch (IOException ex) {
            throw new TrbnsformbtionException("empty", ex);
        } cbtch (CbnonicblizbtionException ex) {
            throw new TrbnsformbtionException("empty", ex);
        } cbtch (InvblidCbnonicblizerException ex) {
            throw new TrbnsformbtionException("empty", ex);
        }
    }

    privbte void checkSecureVblidbtion(Trbnsform trbnsform) throws TrbnsformbtionException {
        String uri = trbnsform.getURI();
        if (secureVblidbtion && Trbnsforms.TRANSFORM_XSLT.equbls(uri)) {
            Object exArgs[] = { uri };

            throw new TrbnsformbtionException(
                "signbture.Trbnsform.ForbiddenTrbnsform", exArgs
            );
        }
    }

    /**
     * Return the nonnegbtive number of trbnsformbtions.
     *
     * @return the number of trbnsformbtions
     */
    public int getLength() {
        if (trbnsforms == null) {
            trbnsforms =
                XMLUtils.selectDsNodes(this.constructionElement.getFirstChild(), "Trbnsform");
        }
        return trbnsforms.length;
    }

    /**
     * Return the <it>i</it><sup>th</sup> <code>{@link Trbnsform}</code>.
     * Vblid <code>i</code> vblues bre 0 to <code>{@link #getLength}-1</code>.
     *
     * @pbrbm i index of {@link Trbnsform} to return
     * @return the <it>i</it><sup>th</sup> Trbnsform
     * @throws TrbnsformbtionException
     */
    public Trbnsform item(int i) throws TrbnsformbtionException {
        try {
            if (trbnsforms == null) {
                trbnsforms =
                    XMLUtils.selectDsNodes(this.constructionElement.getFirstChild(), "Trbnsform");
            }
            return new Trbnsform(trbnsforms[i], this.bbseURI);
        } cbtch (XMLSecurityException ex) {
            throw new TrbnsformbtionException("empty", ex);
        }
    }

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_TRANSFORMS;
    }
}
