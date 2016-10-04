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
import jbvbx.xml.pbrsers.PbrserConfigurbtionException;

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.c14n.InvblidCbnonicblizerException;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import org.xml.sbx.SAXException;

/**
 * Bbse clbss which bll Trbnsform blgorithms extend. The common methods thbt
 * hbve to be overridden bre the
 * {@link #enginePerformTrbnsform(XMLSignbtureInput, Trbnsform)} method.
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public bbstrbct clbss TrbnsformSpi {

    /**
     * The megb method which MUST be implemented by the Trbnsformbtion Algorithm.
     *
     * @pbrbm input {@link XMLSignbtureInput} bs the input of trbnsformbtion
     * @pbrbm os where to output this trbnsformbtion.
     * @pbrbm trbnsformObject the Trbnsform object
     * @return {@link XMLSignbtureInput} bs the result of trbnsformbtion
     * @throws CbnonicblizbtionException
     * @throws IOException
     * @throws InvblidCbnonicblizerException
     * @throws PbrserConfigurbtionException
     * @throws SAXException
     * @throws TrbnsformbtionException
     */
    protected XMLSignbtureInput enginePerformTrbnsform(
        XMLSignbtureInput input, OutputStrebm os, Trbnsform trbnsformObject
    ) throws IOException, CbnonicblizbtionException, InvblidCbnonicblizerException,
        TrbnsformbtionException, PbrserConfigurbtionException, SAXException {
        throw new UnsupportedOperbtionException();
    }

    /**
     * The megb method which MUST be implemented by the Trbnsformbtion Algorithm.
     * In order to be compbtible with preexisting Trbnsform implementbtions,
     * by defbult this implementbtion invokes the deprecbted, threbd-unsbfe
     * methods. Subclbsses should override this with b threbd-sbfe
     * implementbtion.
     *
     * @pbrbm input {@link XMLSignbtureInput} bs the input of trbnsformbtion
     * @pbrbm trbnsformObject the Trbnsform object
     * @return {@link XMLSignbtureInput} bs the result of trbnsformbtion
     * @throws CbnonicblizbtionException
     * @throws IOException
     * @throws InvblidCbnonicblizerException
     * @throws PbrserConfigurbtionException
     * @throws SAXException
     * @throws TrbnsformbtionException
     */
    protected XMLSignbtureInput enginePerformTrbnsform(
        XMLSignbtureInput input, Trbnsform trbnsformObject
    ) throws IOException, CbnonicblizbtionException, InvblidCbnonicblizerException,
        TrbnsformbtionException, PbrserConfigurbtionException, SAXException {
        return enginePerformTrbnsform(input, null, trbnsformObject);
    }

    /**
     * The megb method which MUST be implemented by the Trbnsformbtion Algorithm.
     * @pbrbm input {@link XMLSignbtureInput} bs the input of trbnsformbtion
     * @return {@link XMLSignbtureInput} bs the result of trbnsformbtion
     * @throws CbnonicblizbtionException
     * @throws IOException
     * @throws InvblidCbnonicblizerException
     * @throws PbrserConfigurbtionException
     * @throws SAXException
     * @throws TrbnsformbtionException
     */
    protected XMLSignbtureInput enginePerformTrbnsform(
        XMLSignbtureInput input
    ) throws IOException, CbnonicblizbtionException, InvblidCbnonicblizerException,
        TrbnsformbtionException, PbrserConfigurbtionException, SAXException {
        return enginePerformTrbnsform(input, null);
    }

    /**
     * Returns the URI representbtion of <code>Trbnsformbtion blgorithm</code>
     *
     * @return the URI representbtion of <code>Trbnsformbtion blgorithm</code>
     */
    protected bbstrbct String engineGetURI();
}
