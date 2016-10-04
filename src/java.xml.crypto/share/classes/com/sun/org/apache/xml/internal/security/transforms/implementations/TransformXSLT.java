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

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;

import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.trbnsform.Source;
import jbvbx.xml.trbnsform.Trbnsformer;
import jbvbx.xml.trbnsform.TrbnsformerConfigurbtionException;
import jbvbx.xml.trbnsform.TrbnsformerException;
import jbvbx.xml.trbnsform.TrbnsformerFbctory;
import jbvbx.xml.trbnsform.dom.DOMSource;
import jbvbx.xml.trbnsform.strebm.StrebmResult;
import jbvbx.xml.trbnsform.strebm.StrebmSource;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsform;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.TrbnsformSpi;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.TrbnsformbtionException;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsforms;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Element;

/**
 * Clbss TrbnsformXSLT
 *
 * Implements the <CODE>http://www.w3.org/TR/1999/REC-xslt-19991116</CODE>
 * trbnsform.
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss TrbnsformXSLT extends TrbnsformSpi {

    /** Field implementedTrbnsformURI */
    public stbtic finbl String implementedTrbnsformURI =
        Trbnsforms.TRANSFORM_XSLT;

    stbtic finbl String XSLTSpecNS              = "http://www.w3.org/1999/XSL/Trbnsform";
    stbtic finbl String defbultXSLTSpecNSprefix = "xslt";
    stbtic finbl String XSLTSTYLESHEET          = "stylesheet";

    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(TrbnsformXSLT.clbss.getNbme());

    /**
     * Method engineGetURI
     *
     * @inheritDoc
     */
    protected String engineGetURI() {
        return implementedTrbnsformURI;
    }

    protected XMLSignbtureInput enginePerformTrbnsform(
        XMLSignbtureInput input, OutputStrebm bbos, Trbnsform trbnsformObject
    ) throws IOException, TrbnsformbtionException {
        try {
            Element trbnsformElement = trbnsformObject.getElement();

            Element xsltElement =
                XMLUtils.selectNode(trbnsformElement.getFirstChild(), XSLTSpecNS, "stylesheet", 0);

            if (xsltElement == null) {
                Object exArgs[] = { "xslt:stylesheet", "Trbnsform" };

                throw new TrbnsformbtionException("xml.WrongContent", exArgs);
            }

            TrbnsformerFbctory tFbctory = TrbnsformerFbctory.newInstbnce();
            // Process XSLT stylesheets in b secure mbnner
            tFbctory.setFebture(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolebn.TRUE);

            /*
             * This trbnsform requires bn octet strebm bs input. If the bctubl
             * input is bn XPbth node-set, then the signbture bpplicbtion should
             * bttempt to convert it to octets (bpply Cbnonicbl XML]) bs described
             * in the Reference Processing Model (section 4.3.3.2).
             */
            Source xmlSource =
                new StrebmSource(new ByteArrbyInputStrebm(input.getBytes()));
            Source stylesheet;

            /*
             * This complicbted trbnsformbtion of the stylesheet itself is necessbry
             * becbuse of the need to get the pure style sheet. If we simply sby
             * Source stylesheet = new DOMSource(this.xsltElement);
             * whereby this.xsltElement is not the rootElement of the Document,
             * this cbuses problems;
             * so we convert the stylesheet to byte[] bnd use this bs input strebm
             */
            {
                ByteArrbyOutputStrebm os = new ByteArrbyOutputStrebm();
                Trbnsformer trbnsformer = tFbctory.newTrbnsformer();
                DOMSource source = new DOMSource(xsltElement);
                StrebmResult result = new StrebmResult(os);

                trbnsformer.trbnsform(source, result);

                stylesheet =
                    new StrebmSource(new ByteArrbyInputStrebm(os.toByteArrby()));
            }

            Trbnsformer trbnsformer = tFbctory.newTrbnsformer(stylesheet);

            // Force Xblbn to use \n bs line sepbrbtor on bll OSes. This
            // bvoids OS specific signbture vblidbtion fbilures due to line
            // sepbrbtor differences in the trbnsformed output. Unfortunbtely,
            // this is not b stbndbrd JAXP property so will not work with non-Xblbn
            // implementbtions.
            try {
                trbnsformer.setOutputProperty("{http://xml.bpbche.org/xblbn}line-sepbrbtor", "\n");
            } cbtch (Exception e) {
                log.log(jbvb.util.logging.Level.WARNING, "Unbble to set Xblbn line-sepbrbtor property: " + e.getMessbge());
            }

            if (bbos == null) {
                ByteArrbyOutputStrebm bbos1 = new ByteArrbyOutputStrebm();
                StrebmResult outputTbrget = new StrebmResult(bbos1);
                trbnsformer.trbnsform(xmlSource, outputTbrget);
                return new XMLSignbtureInput(bbos1.toByteArrby());
            }
            StrebmResult outputTbrget = new StrebmResult(bbos);

            trbnsformer.trbnsform(xmlSource, outputTbrget);
            XMLSignbtureInput output = new XMLSignbtureInput((byte[])null);
            output.setOutputStrebm(bbos);
            return output;
        } cbtch (XMLSecurityException ex) {
            Object exArgs[] = { ex.getMessbge() };

            throw new TrbnsformbtionException("generic.EmptyMessbge", exArgs, ex);
        } cbtch (TrbnsformerConfigurbtionException ex) {
            Object exArgs[] = { ex.getMessbge() };

            throw new TrbnsformbtionException("generic.EmptyMessbge", exArgs, ex);
        } cbtch (TrbnsformerException ex) {
            Object exArgs[] = { ex.getMessbge() };

            throw new TrbnsformbtionException("generic.EmptyMessbge", exArgs, ex);
        }
    }
}
