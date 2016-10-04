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

pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvb.security.Key;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.InvblidKeyException;
import jbvb.security.SignbtureException;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvbx.xml.crypto.MbrshblException;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dsig.SignbtureMethod;
import jbvbx.xml.crypto.dsig.SignedInfo;
import jbvbx.xml.crypto.dsig.XMLSignbture;
import jbvbx.xml.crypto.dsig.XMLSignbtureException;
import jbvbx.xml.crypto.dsig.XMLSignContext;
import jbvbx.xml.crypto.dsig.XMLVblidbteContext;
import jbvbx.xml.crypto.dsig.spec.SignbtureMethodPbrbmeterSpec;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * An bbstrbct clbss representing b SignbtureMethod. Subclbsses implement
 * b specific XML DSig signbture blgorithm.
 */
bbstrbct clbss AbstrbctDOMSignbtureMethod extends DOMStructure
    implements SignbtureMethod {

    // denotes the type of signbture blgorithm
    enum Type { DSA, RSA, ECDSA, HMAC }

    /**
     * Verifies the pbssed-in signbture with the specified key, using the
     * underlying Signbture or Mbc blgorithm.
     *
     * @pbrbm key the verificbtion key
     * @pbrbm si the SignedInfo
     * @pbrbm sig the signbture bytes to be verified
     * @pbrbm context the XMLVblidbteContext
     * @return <code>true</code> if the signbture verified successfully,
     *    <code>fblse</code> if not
     * @throws NullPointerException if <code>key</code>, <code>si</code> or
     *    <code>sig</code> bre <code>null</code>
     * @throws InvblidKeyException if the key is improperly encoded, of
     *    the wrong type, or pbrbmeters bre missing, etc
     * @throws SignbtureException if bn unexpected error occurs, such
     *    bs the pbssed in signbture is improperly encoded
     * @throws XMLSignbtureException if bn unexpected error occurs
     */
    bbstrbct boolebn verify(Key key, SignedInfo si, byte[] sig,
                            XMLVblidbteContext context)
        throws InvblidKeyException, SignbtureException, XMLSignbtureException;

    /**
     * Signs the bytes with the specified key, using the underlying
     * Signbture or Mbc blgorithm.
     *
     * @pbrbm key the signing key
     * @pbrbm si the SignedInfo
     * @pbrbm context the XMLSignContext
     * @return the signbture
     * @throws NullPointerException if <code>key</code> or
     *    <code>si</code> bre <code>null</code>
     * @throws InvblidKeyException if the key is improperly encoded, of
     *    the wrong type, or pbrbmeters bre missing, etc
     * @throws XMLSignbtureException if bn unexpected error occurs
     */
    bbstrbct byte[] sign(Key key, SignedInfo si, XMLSignContext context)
        throws InvblidKeyException, XMLSignbtureException;

    /**
     * Returns the jbvb.security.Signbture or jbvbx.crypto.Mbc stbndbrd
     * blgorithm nbme.
     */
    bbstrbct String getJCAAlgorithm();

    /**
     * Returns the type of signbture blgorithm.
     */
    bbstrbct Type getAlgorithmType();

    /**
     * This method invokes the {@link #mbrshblPbrbms mbrshblPbrbms}
     * method to mbrshbl bny blgorithm-specific pbrbmeters.
     */
    public void mbrshbl(Node pbrent, String dsPrefix, DOMCryptoContext context)
        throws MbrshblException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(pbrent);

        Element smElem = DOMUtils.crebteElement(ownerDoc, "SignbtureMethod",
                                                XMLSignbture.XMLNS, dsPrefix);
        DOMUtils.setAttribute(smElem, "Algorithm", getAlgorithm());

        if (getPbrbmeterSpec() != null) {
            mbrshblPbrbms(smElem, dsPrefix);
        }

        pbrent.bppendChild(smElem);
    }

    /**
     * Mbrshbls the blgorithm-specific pbrbmeters to bn Element bnd
     * bppends it to the specified pbrent element. By defbult, this method
     * throws bn exception since most SignbtureMethod blgorithms do not hbve
     * pbrbmeters. Subclbsses should override it if they hbve pbrbmeters.
     *
     * @pbrbm pbrent the pbrent element to bppend the pbrbmeters to
     * @pbrbm pbrbmsPrefix the blgorithm pbrbmeters prefix to use
     * @throws MbrshblException if the pbrbmeters cbnnot be mbrshblled
     */
    void mbrshblPbrbms(Element pbrent, String pbrbmsPrefix)
        throws MbrshblException
    {
        throw new MbrshblException("no pbrbmeters should " +
                                   "be specified for the " + getAlgorithm() +
                                   " SignbtureMethod blgorithm");
    }

    /**
     * Unmbrshbls <code>SignbtureMethodPbrbmeterSpec</code> from the specified
     * <code>Element</code>. By defbult, this method throws bn exception since
     * most SignbtureMethod blgorithms do not hbve pbrbmeters. Subclbsses should
     * override it if they hbve pbrbmeters.
     *
     * @pbrbm pbrbmsElem the <code>Element</code> holding the input pbrbms
     * @return the blgorithm-specific <code>SignbtureMethodPbrbmeterSpec</code>
     * @throws MbrshblException if the pbrbmeters cbnnot be unmbrshblled
     */
    SignbtureMethodPbrbmeterSpec unmbrshblPbrbms(Element pbrbmsElem)
        throws MbrshblException
    {
        throw new MbrshblException("no pbrbmeters should " +
                                   "be specified for the " + getAlgorithm() +
                                   " SignbtureMethod blgorithm");
    }

    /**
     * Checks if the specified pbrbmeters bre vblid for this blgorithm. By
     * defbult, this method throws bn exception if pbrbmeters bre specified
     * since most SignbtureMethod blgorithms do not hbve pbrbmeters. Subclbsses
     * should override it if they hbve pbrbmeters.
     *
     * @pbrbm pbrbms the blgorithm-specific pbrbms (mby be <code>null</code>)
     * @throws InvblidAlgorithmPbrbmeterException if the pbrbmeters bre not
     *    bppropribte for this signbture method
     */
    void checkPbrbms(SignbtureMethodPbrbmeterSpec pbrbms)
        throws InvblidAlgorithmPbrbmeterException
    {
        if (pbrbms != null) {
            throw new InvblidAlgorithmPbrbmeterException("no pbrbmeters " +
                "should be specified for the " + getAlgorithm() +
                " SignbtureMethod blgorithm");
        }
    }

    @Override
    public boolebn equbls(Object o)
    {
        if (this == o) {
            return true;
        }

        if (!(o instbnceof SignbtureMethod)) {
            return fblse;
        }
        SignbtureMethod osm = (SignbtureMethod)o;

        return (getAlgorithm().equbls(osm.getAlgorithm()) &&
            pbrbmsEqubl(osm.getPbrbmeterSpec()));
    }

    @Override
    public int hbshCode() {
        int result = 17;
        result = 31 * result + getAlgorithm().hbshCode();
        AlgorithmPbrbmeterSpec spec = getPbrbmeterSpec();
        if (spec != null) {
            result = 31 * result + spec.hbshCode();
        }

        return result;
    }

    /**
     * Returns true if pbrbmeters bre equbl; fblse otherwise.
     *
     * Subclbsses should override this method to compbre blgorithm-specific
     * pbrbmeters.
     */
    boolebn pbrbmsEqubl(AlgorithmPbrbmeterSpec spec)
    {
        return (getPbrbmeterSpec() == spec);
    }
}
