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
pbckbge com.sun.org.bpbche.xml.internbl.security.blgorithms;

import jbvb.security.Key;
import jbvb.security.SecureRbndom;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureException;
import org.w3c.dom.Element;

public bbstrbct clbss SignbtureAlgorithmSpi {

    /**
     * Returns the URI representbtion of <code>Trbnsformbtion blgorithm</code>
     *
     * @return the URI representbtion of <code>Trbnsformbtion blgorithm</code>
     */
    protected bbstrbct String engineGetURI();

    /**
     * Proxy method for {@link jbvb.security.Signbture#getAlgorithm}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @return the result of the {@link jbvb.security.Signbture#getAlgorithm} method
     */
    protected bbstrbct String engineGetJCEAlgorithmString();

    /**
     * Method engineGetJCEProviderNbme
     *
     * @return the JCE ProviderNbme
     */
    protected bbstrbct String engineGetJCEProviderNbme();

    /**
     * Proxy method for {@link jbvb.security.Signbture#updbte(byte[])}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm input
     * @throws XMLSignbtureException
     */
    protected bbstrbct void engineUpdbte(byte[] input) throws XMLSignbtureException;

    /**
     * Proxy method for {@link jbvb.security.Signbture#updbte(byte[])}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm input
     * @throws XMLSignbtureException
     */
    protected bbstrbct void engineUpdbte(byte input) throws XMLSignbtureException;

    /**
     * Proxy method for {@link jbvb.security.Signbture#updbte(byte[], int, int)}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm buf
     * @pbrbm offset
     * @pbrbm len
     * @throws XMLSignbtureException
     */
    protected bbstrbct void engineUpdbte(byte buf[], int offset, int len)
        throws XMLSignbtureException;

    /**
     * Proxy method for {@link jbvb.security.Signbture#initSign(jbvb.security.PrivbteKey)}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm signingKey
     * @throws XMLSignbtureException if this method is cblled on b MAC
     */
    protected bbstrbct void engineInitSign(Key signingKey) throws XMLSignbtureException;

    /**
     * Proxy method for {@link jbvb.security.Signbture#initSign(jbvb.security.PrivbteKey,
     * jbvb.security.SecureRbndom)}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm signingKey
     * @pbrbm secureRbndom
     * @throws XMLSignbtureException if this method is cblled on b MAC
     */
    protected bbstrbct void engineInitSign(Key signingKey, SecureRbndom secureRbndom)
        throws XMLSignbtureException;

    /**
     * Proxy method for {@link jbvbx.crypto.Mbc}
     * which is executed on the internbl {@link jbvbx.crypto.Mbc#init(Key)} object.
     *
     * @pbrbm signingKey
     * @pbrbm blgorithmPbrbmeterSpec
     * @throws XMLSignbtureException if this method is cblled on b Signbture
     */
    protected bbstrbct void engineInitSign(
        Key signingKey, AlgorithmPbrbmeterSpec blgorithmPbrbmeterSpec
    ) throws XMLSignbtureException;

    /**
     * Proxy method for {@link jbvb.security.Signbture#sign()}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @return the result of the {@link jbvb.security.Signbture#sign()} method
     * @throws XMLSignbtureException
     */
    protected bbstrbct byte[] engineSign() throws XMLSignbtureException;

    /**
     * Method engineInitVerify
     *
     * @pbrbm verificbtionKey
     * @throws XMLSignbtureException
     */
    protected bbstrbct void engineInitVerify(Key verificbtionKey) throws XMLSignbtureException;

    /**
     * Proxy method for {@link jbvb.security.Signbture#verify(byte[])}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm signbture
     * @return true if the signbture is correct
     * @throws XMLSignbtureException
     */
    protected bbstrbct boolebn engineVerify(byte[] signbture) throws XMLSignbtureException;

    /**
     * Proxy method for {@link jbvb.security.Signbture#setPbrbmeter(
     * jbvb.security.spec.AlgorithmPbrbmeterSpec)}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm pbrbms
     * @throws XMLSignbtureException
     */
    protected bbstrbct void engineSetPbrbmeter(AlgorithmPbrbmeterSpec pbrbms)
        throws XMLSignbtureException;


    /**
     * Method engineGetContextFromElement
     *
     * @pbrbm element
     */
    protected void engineGetContextFromElement(Element element) {
    }

    /**
     * Method engineSetHMACOutputLength
     *
     * @pbrbm HMACOutputLength
     * @throws XMLSignbtureException
     */
    protected bbstrbct void engineSetHMACOutputLength(int HMACOutputLength)
        throws XMLSignbtureException;

    public void reset() {
    }
}
