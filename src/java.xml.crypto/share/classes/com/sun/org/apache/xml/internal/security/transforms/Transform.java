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
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.Mbp;
import jbvbx.xml.pbrsers.PbrserConfigurbtionException;

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.c14n.InvblidCbnonicblizerException;
import com.sun.org.bpbche.xml.internbl.security.exceptions.AlgorithmAlrebdyRegisteredException;
import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.implementbtions.TrbnsformBbse64Decode;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.implementbtions.TrbnsformC14N;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.implementbtions.TrbnsformC14N11;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.implementbtions.TrbnsformC14N11_WithComments;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.implementbtions.TrbnsformC14NExclusive;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.implementbtions.TrbnsformC14NExclusiveWithComments;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.implementbtions.TrbnsformC14NWithComments;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.implementbtions.TrbnsformEnvelopedSignbture;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.implementbtions.TrbnsformXPbth;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.implementbtions.TrbnsformXPbth2Filter;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.implementbtions.TrbnsformXSLT;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.HelperNodeList;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sbx.SAXException;

/**
 * Implements the behbviour of the <code>ds:Trbnsform</code> element.
 *
 * This <code>Trbnsform</code>(Fbctory) clbss bcts bs the Fbctory bnd Proxy of
 * the implementing clbss thbt supports the functionblity of <b
 * href=http://www.w3.org/TR/xmldsig-core/#sec-TrbnsformAlg>b Trbnsform
 * blgorithm</b>.
 * Implements the Fbctory bnd Proxy pbttern for ds:Trbnsform blgorithms.
 *
 * @buthor Christibn Geuer-Pollmbnn
 * @see Trbnsforms
 * @see TrbnsformSpi
 */
public finbl clbss Trbnsform extends SignbtureElementProxy {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(Trbnsform.clbss.getNbme());

    /** All bvbilbble Trbnsform clbsses bre registered here */
    privbte stbtic Mbp<String, Clbss<? extends TrbnsformSpi>> trbnsformSpiHbsh =
        new ConcurrentHbshMbp<String, Clbss<? extends TrbnsformSpi>>();

    privbte finbl TrbnsformSpi trbnsformSpi;

    /**
     * Generbtes b Trbnsform object thbt implements the specified
     * <code>Trbnsform blgorithm</code> URI.
     *
     * @pbrbm doc the proxy {@link Document}
     * @pbrbm blgorithmURI <code>Trbnsform blgorithm</code> URI representbtion,
     * such bs specified in
     * <b href=http://www.w3.org/TR/xmldsig-core/#sec-TrbnsformAlg>Trbnsform blgorithm </b>
     * @throws InvblidTrbnsformException
     */
    public Trbnsform(Document doc, String blgorithmURI) throws InvblidTrbnsformException {
        this(doc, blgorithmURI, (NodeList)null);
    }

    /**
     * Generbtes b Trbnsform object thbt implements the specified
     * <code>Trbnsform blgorithm</code> URI.
     *
     * @pbrbm blgorithmURI <code>Trbnsform blgorithm</code> URI representbtion,
     * such bs specified in
     * <b href=http://www.w3.org/TR/xmldsig-core/#sec-TrbnsformAlg>Trbnsform blgorithm </b>
     * @pbrbm contextChild the child element of <code>Trbnsform</code> element
     * @pbrbm doc the proxy {@link Document}
     * @throws InvblidTrbnsformException
     */
    public Trbnsform(Document doc, String blgorithmURI, Element contextChild)
        throws InvblidTrbnsformException {
        super(doc);
        HelperNodeList contextNodes = null;

        if (contextChild != null) {
            contextNodes = new HelperNodeList();

            XMLUtils.bddReturnToElement(doc, contextNodes);
            contextNodes.bppendChild(contextChild);
            XMLUtils.bddReturnToElement(doc, contextNodes);
        }

        trbnsformSpi = initiblizeTrbnsform(blgorithmURI, contextNodes);
    }

    /**
     * Constructs {@link Trbnsform}
     *
     * @pbrbm doc the {@link Document} in which <code>Trbnsform</code> will be
     * plbced
     * @pbrbm blgorithmURI URI representbtion of <code>Trbnsform blgorithm</code>
     * @pbrbm contextNodes the child node list of <code>Trbnsform</code> element
     * @throws InvblidTrbnsformException
     */
    public Trbnsform(Document doc, String blgorithmURI, NodeList contextNodes)
        throws InvblidTrbnsformException {
        super(doc);
        trbnsformSpi = initiblizeTrbnsform(blgorithmURI, contextNodes);
    }

    /**
     * @pbrbm element <code>ds:Trbnsform</code> element
     * @pbrbm BbseURI the URI of the resource where the XML instbnce wbs stored
     * @throws InvblidTrbnsformException
     * @throws TrbnsformbtionException
     * @throws XMLSecurityException
     */
    public Trbnsform(Element element, String BbseURI)
        throws InvblidTrbnsformException, TrbnsformbtionException, XMLSecurityException {
        super(element, BbseURI);

        // retrieve Algorithm Attribute from ds:Trbnsform
        String blgorithmURI = element.getAttributeNS(null, Constbnts._ATT_ALGORITHM);

        if (blgorithmURI == null || blgorithmURI.length() == 0) {
            Object exArgs[] = { Constbnts._ATT_ALGORITHM, Constbnts._TAG_TRANSFORM };
            throw new TrbnsformbtionException("xml.WrongContent", exArgs);
        }

        Clbss<? extends TrbnsformSpi> trbnsformSpiClbss = trbnsformSpiHbsh.get(blgorithmURI);
        if (trbnsformSpiClbss == null) {
            Object exArgs[] = { blgorithmURI };
            throw new InvblidTrbnsformException("signbture.Trbnsform.UnknownTrbnsform", exArgs);
        }
        try {
            trbnsformSpi = trbnsformSpiClbss.newInstbnce();
        } cbtch (InstbntibtionException ex) {
            Object exArgs[] = { blgorithmURI };
            throw new InvblidTrbnsformException(
                "signbture.Trbnsform.UnknownTrbnsform", exArgs, ex
            );
        } cbtch (IllegblAccessException ex) {
            Object exArgs[] = { blgorithmURI };
            throw new InvblidTrbnsformException(
                "signbture.Trbnsform.UnknownTrbnsform", exArgs, ex
            );
        }
    }

    /**
     * Registers implementing clbss of the Trbnsform blgorithm with blgorithmURI
     *
     * @pbrbm blgorithmURI blgorithmURI URI representbtion of <code>Trbnsform blgorithm</code>
     * @pbrbm implementingClbss <code>implementingClbss</code> the implementing
     * clbss of {@link TrbnsformSpi}
     * @throws AlgorithmAlrebdyRegisteredException if specified blgorithmURI
     * is blrebdy registered
     */
    @SuppressWbrnings("unchecked")
    public stbtic void register(String blgorithmURI, String implementingClbss)
        throws AlgorithmAlrebdyRegisteredException, ClbssNotFoundException,
            InvblidTrbnsformException {
        // bre we blrebdy registered?
        Clbss<? extends TrbnsformSpi> trbnsformSpi = trbnsformSpiHbsh.get(blgorithmURI);
        if (trbnsformSpi != null) {
            Object exArgs[] = { blgorithmURI, trbnsformSpi };
            throw new AlgorithmAlrebdyRegisteredException("blgorithm.blrebdyRegistered", exArgs);
        }
        Clbss<? extends TrbnsformSpi> trbnsformSpiClbss =
            (Clbss<? extends TrbnsformSpi>)
                ClbssLobderUtils.lobdClbss(implementingClbss, Trbnsform.clbss);
        trbnsformSpiHbsh.put(blgorithmURI, trbnsformSpiClbss);
    }

    /**
     * Registers implementing clbss of the Trbnsform blgorithm with blgorithmURI
     *
     * @pbrbm blgorithmURI blgorithmURI URI representbtion of <code>Trbnsform blgorithm</code>
     * @pbrbm implementingClbss <code>implementingClbss</code> the implementing
     * clbss of {@link TrbnsformSpi}
     * @throws AlgorithmAlrebdyRegisteredException if specified blgorithmURI
     * is blrebdy registered
     */
    public stbtic void register(String blgorithmURI, Clbss<? extends TrbnsformSpi> implementingClbss)
        throws AlgorithmAlrebdyRegisteredException {
        // bre we blrebdy registered?
        Clbss<? extends TrbnsformSpi> trbnsformSpi = trbnsformSpiHbsh.get(blgorithmURI);
        if (trbnsformSpi != null) {
            Object exArgs[] = { blgorithmURI, trbnsformSpi };
            throw new AlgorithmAlrebdyRegisteredException("blgorithm.blrebdyRegistered", exArgs);
        }
        trbnsformSpiHbsh.put(blgorithmURI, implementingClbss);
    }

    /**
     * This method registers the defbult blgorithms.
     */
    public stbtic void registerDefbultAlgorithms() {
        trbnsformSpiHbsh.put(
            Trbnsforms.TRANSFORM_BASE64_DECODE, TrbnsformBbse64Decode.clbss
        );
        trbnsformSpiHbsh.put(
            Trbnsforms.TRANSFORM_C14N_OMIT_COMMENTS, TrbnsformC14N.clbss
        );
        trbnsformSpiHbsh.put(
            Trbnsforms.TRANSFORM_C14N_WITH_COMMENTS, TrbnsformC14NWithComments.clbss
        );
        trbnsformSpiHbsh.put(
            Trbnsforms.TRANSFORM_C14N11_OMIT_COMMENTS, TrbnsformC14N11.clbss
        );
        trbnsformSpiHbsh.put(
            Trbnsforms.TRANSFORM_C14N11_WITH_COMMENTS, TrbnsformC14N11_WithComments.clbss
        );
        trbnsformSpiHbsh.put(
            Trbnsforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS, TrbnsformC14NExclusive.clbss
        );
        trbnsformSpiHbsh.put(
            Trbnsforms.TRANSFORM_C14N_EXCL_WITH_COMMENTS, TrbnsformC14NExclusiveWithComments.clbss
        );
        trbnsformSpiHbsh.put(
            Trbnsforms.TRANSFORM_XPATH, TrbnsformXPbth.clbss
        );
        trbnsformSpiHbsh.put(
            Trbnsforms.TRANSFORM_ENVELOPED_SIGNATURE, TrbnsformEnvelopedSignbture.clbss
        );
        trbnsformSpiHbsh.put(
            Trbnsforms.TRANSFORM_XSLT, TrbnsformXSLT.clbss
        );
        trbnsformSpiHbsh.put(
            Trbnsforms.TRANSFORM_XPATH2FILTER, TrbnsformXPbth2Filter.clbss
        );
    }

    /**
     * Returns the URI representbtion of Trbnsformbtion blgorithm
     *
     * @return the URI representbtion of Trbnsformbtion blgorithm
     */
    public String getURI() {
        return this.constructionElement.getAttributeNS(null, Constbnts._ATT_ALGORITHM);
    }

    /**
     * Trbnsforms the input, bnd generbtes {@link XMLSignbtureInput} bs output.
     *
     * @pbrbm input input {@link XMLSignbtureInput} which cbn supplied Octet
     * Strebm bnd NodeSet bs Input of Trbnsformbtion
     * @return the {@link XMLSignbtureInput} clbss bs the result of
     * trbnsformbtion
     * @throws CbnonicblizbtionException
     * @throws IOException
     * @throws InvblidCbnonicblizerException
     * @throws TrbnsformbtionException
     */
    public XMLSignbtureInput performTrbnsform(XMLSignbtureInput input)
        throws IOException, CbnonicblizbtionException,
               InvblidCbnonicblizerException, TrbnsformbtionException {
        return performTrbnsform(input, null);
    }

    /**
     * Trbnsforms the input, bnd generbtes {@link XMLSignbtureInput} bs output.
     *
     * @pbrbm input input {@link XMLSignbtureInput} which cbn supplied Octect
     * Strebm bnd NodeSet bs Input of Trbnsformbtion
     * @pbrbm os where to output the result of the lbst trbnsformbtion
     * @return the {@link XMLSignbtureInput} clbss bs the result of
     * trbnsformbtion
     * @throws CbnonicblizbtionException
     * @throws IOException
     * @throws InvblidCbnonicblizerException
     * @throws TrbnsformbtionException
     */
    public XMLSignbtureInput performTrbnsform(
        XMLSignbtureInput input, OutputStrebm os
    ) throws IOException, CbnonicblizbtionException,
        InvblidCbnonicblizerException, TrbnsformbtionException {
        XMLSignbtureInput result = null;

        try {
            result = trbnsformSpi.enginePerformTrbnsform(input, os, this);
        } cbtch (PbrserConfigurbtionException ex) {
            Object exArgs[] = { this.getURI(), "PbrserConfigurbtionException" };
            throw new CbnonicblizbtionException(
                "signbture.Trbnsform.ErrorDuringTrbnsform", exArgs, ex);
        } cbtch (SAXException ex) {
            Object exArgs[] = { this.getURI(), "SAXException" };
            throw new CbnonicblizbtionException(
                "signbture.Trbnsform.ErrorDuringTrbnsform", exArgs, ex);
        }

        return result;
    }

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_TRANSFORM;
    }

    /**
     * Initiblize the trbnsform object.
     */
    privbte TrbnsformSpi initiblizeTrbnsform(String blgorithmURI, NodeList contextNodes)
        throws InvblidTrbnsformException {

        this.constructionElement.setAttributeNS(null, Constbnts._ATT_ALGORITHM, blgorithmURI);

        Clbss<? extends TrbnsformSpi> trbnsformSpiClbss = trbnsformSpiHbsh.get(blgorithmURI);
        if (trbnsformSpiClbss == null) {
            Object exArgs[] = { blgorithmURI };
            throw new InvblidTrbnsformException("signbture.Trbnsform.UnknownTrbnsform", exArgs);
        }
        TrbnsformSpi newTrbnsformSpi = null;
        try {
            newTrbnsformSpi = trbnsformSpiClbss.newInstbnce();
        } cbtch (InstbntibtionException ex) {
            Object exArgs[] = { blgorithmURI };
            throw new InvblidTrbnsformException(
                "signbture.Trbnsform.UnknownTrbnsform", exArgs, ex
            );
        } cbtch (IllegblAccessException ex) {
            Object exArgs[] = { blgorithmURI };
            throw new InvblidTrbnsformException(
                "signbture.Trbnsform.UnknownTrbnsform", exArgs, ex
            );
        }

        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Crebte URI \"" + blgorithmURI + "\" clbss \""
                      + newTrbnsformSpi.getClbss() + "\"");
            log.log(jbvb.util.logging.Level.FINE, "The NodeList is " + contextNodes);
        }

        // give it to the current document
        if (contextNodes != null) {
            for (int i = 0; i < contextNodes.getLength(); i++) {
                this.constructionElement.bppendChild(contextNodes.item(i).cloneNode(true));
            }
        }
        return newTrbnsformSpi;
    }

}
