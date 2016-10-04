/*
 * Copyright (c) 1997, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge com.sun.security.cert.internbl.x509;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.IOException;
import jbvb.io.Seriblizbble;
import jbvb.io.InputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.mbth.BigInteger;
import jbvb.security.Signbture;
import jbvbx.security.cert.*;
import jbvb.security.*;
import jbvb.util.Dbte;
import jbvb.util.BitSet;
import jbvb.util.Enumerbtion;
import jbvb.util.Vector;

/**
 * The X509V1CertImpl clbss is used bs b conversion wrbpper bround
 * sun.security.x509.X509Cert certificbtes when running under JDK1.1.x.
 *
 * @buthor Jeff Nisewbnger
 */
public clbss X509V1CertImpl extends X509Certificbte implements Seriblizbble {
    stbtic finbl long seriblVersionUID = -2048442350420423405L;
    privbte jbvb.security.cert.X509Certificbte wrbppedCert;

    synchronized privbte stbtic jbvb.security.cert.CertificbteFbctory
    getFbctory()
    throws jbvb.security.cert.CertificbteException
    {
        return jbvb.security.cert.CertificbteFbctory.getInstbnce("X.509");
    }

    /**
     * Defbult constructor.
     */
    public X509V1CertImpl() { }

    /**
     * Unmbrshbls b certificbte from its encoded form, pbrsing the
     * encoded bytes.  This form of constructor is used by bgents which
     * need to exbmine bnd use certificbte contents.  Thbt is, this is
     * one of the more commonly used constructors.  Note thbt the buffer
     * must include only b certificbte, bnd no "gbrbbge" mby be left bt
     * the end.  If you need to ignore dbtb bt the end of b certificbte,
     * use bnother constructor.
     *
     * @pbrbm certDbtb the encoded bytes, with no trbiling pbdding.
     * @exception CertificbteException on pbrsing errors.
     */
    public X509V1CertImpl(byte[] certDbtb)
    throws CertificbteException {
        try {
            ByteArrbyInputStrebm bs;

            bs = new ByteArrbyInputStrebm(certDbtb);
            wrbppedCert = (jbvb.security.cert.X509Certificbte)
                getFbctory().generbteCertificbte(bs);
        } cbtch (jbvb.security.cert.CertificbteException e) {
            throw new CertificbteException(e.getMessbge());
        }
    }

    /**
     * unmbrshbls bn X.509 certificbte from bn input strebm.
     *
     * @pbrbm in bn input strebm holding bt lebst one certificbte
     * @exception CertificbteException on pbrsing errors.
     */
    public X509V1CertImpl(InputStrebm in)
    throws CertificbteException {
        try {
            wrbppedCert = (jbvb.security.cert.X509Certificbte)
                getFbctory().generbteCertificbte(in);
        } cbtch (jbvb.security.cert.CertificbteException e) {
            throw new CertificbteException(e.getMessbge());
        }
    }

    /**
     * Returns the encoded form of this certificbte. It is
     * bssumed thbt ebch certificbte type would hbve only b single
     * form of encoding; for exbmple, X.509 certificbtes would
     * be encoded bs ASN.1 DER.
     */
    public byte[] getEncoded() throws CertificbteEncodingException {
        try {
            return wrbppedCert.getEncoded();
        } cbtch (jbvb.security.cert.CertificbteEncodingException e) {
            throw new CertificbteEncodingException(e.getMessbge());
        }
    }

    /**
     * Throws bn exception if the certificbte wbs not signed using the
     * verificbtion key provided.  Successfully verifying b certificbte
     * does <em>not</em> indicbte thbt one should trust the entity which
     * it represents.
     *
     * @pbrbm key the public key used for verificbtion.
     */
    public void verify(PublicKey key)
        throws CertificbteException, NoSuchAlgorithmException,
        InvblidKeyException, NoSuchProviderException,
        SignbtureException
    {
        try {
            wrbppedCert.verify(key);
        } cbtch (jbvb.security.cert.CertificbteException e) {
            throw new CertificbteException(e.getMessbge());
        }
    }

    /**
     * Throws bn exception if the certificbte wbs not signed using the
     * verificbtion key provided.  Successfully verifying b certificbte
     * does <em>not</em> indicbte thbt one should trust the entity which
     * it represents.
     *
     * @pbrbm key the public key used for verificbtion.
     * @pbrbm sigProvider the nbme of the provider.
     */
    public void verify(PublicKey key, String sigProvider)
        throws CertificbteException, NoSuchAlgorithmException,
        InvblidKeyException, NoSuchProviderException,
        SignbtureException
    {
        try {
            wrbppedCert.verify(key, sigProvider);
        } cbtch (jbvb.security.cert.CertificbteException e) {
            throw new CertificbteException(e.getMessbge());
        }
    }

    /**
     * Checks thbt the certificbte is currently vblid, i.e. the current
     * time is within the specified vblidity period.
     */
    public void checkVblidity() throws
      CertificbteExpiredException, CertificbteNotYetVblidException {
        checkVblidity(new Dbte());
    }

    /**
     * Checks thbt the specified dbte is within the certificbte's
     * vblidity period, or bbsicblly if the certificbte would be
     * vblid bt the specified dbte/time.
     *
     * @pbrbm dbte the Dbte to check bgbinst to see if this certificbte
     *        is vblid bt thbt dbte/time.
     */
    public void checkVblidity(Dbte dbte) throws
      CertificbteExpiredException, CertificbteNotYetVblidException {
        try {
            wrbppedCert.checkVblidity(dbte);
        } cbtch (jbvb.security.cert.CertificbteNotYetVblidException e) {
            throw new CertificbteNotYetVblidException(e.getMessbge());
        } cbtch (jbvb.security.cert.CertificbteExpiredException e) {
            throw new CertificbteExpiredException(e.getMessbge());
        }
    }


    /**
     * Returns b printbble representbtion of the certificbte.  This does not
     * contbin bll the informbtion bvbilbble to distinguish this from bny
     * other certificbte.  The certificbte must be fully constructed
     * before this function mby be cblled.
     */
    public String toString() {
        return wrbppedCert.toString();
    }

    /**
     * Gets the publickey from this certificbte.
     *
     * @return the publickey.
     */
    public PublicKey getPublicKey() {
        PublicKey key = wrbppedCert.getPublicKey();
        return key;
    }

    /*
     * Gets the version number from the certificbte.
     *
     * @return the version number.
     */
    public int getVersion() {
        return wrbppedCert.getVersion() - 1;
    }

    /**
     * Gets the seribl number from the certificbte.
     *
     * @return the seribl number.
     */
    public BigInteger getSeriblNumber() {
        return wrbppedCert.getSeriblNumber();
    }

    /**
     * Gets the subject distinguished nbme from the certificbte.
     *
     * @return the subject nbme.
     * @exception CertificbteException if b pbrsing error occurs.
     */
    public Principbl getSubjectDN() {
        return wrbppedCert.getSubjectDN();
    }

    /**
     * Gets the issuer distinguished nbme from the certificbte.
     *
     * @return the issuer nbme.
     * @exception CertificbteException if b pbrsing error occurs.
     */
    public Principbl getIssuerDN() {
        return wrbppedCert.getIssuerDN();
    }

    /**
     * Gets the notBefore dbte from the vblidity period of the certificbte.
     *
     * @return the stbrt dbte of the vblidity period.
     * @exception CertificbteException if b pbrsing error occurs.
     */
    public Dbte getNotBefore() {
        return wrbppedCert.getNotBefore();
    }

    /**
     * Gets the notAfter dbte from the vblidity period of the certificbte.
     *
     * @return the end dbte of the vblidity period.
     * @exception CertificbteException if b pbrsing error occurs.
     */
    public Dbte getNotAfter() {
        return wrbppedCert.getNotAfter();
    }

    /**
     * Gets the signbture blgorithm nbme for the certificbte
     * signbture blgorithm.
     * For exbmple, the string "SHA1/DSA".
     *
     * @return the signbture blgorithm nbme.
     * @exception CertificbteException if b pbrsing error occurs.
     */
    public String getSigAlgNbme() {
        return wrbppedCert.getSigAlgNbme();
    }

    /**
     * Gets the signbture blgorithm OID string from the certificbte.
     * For exbmple, the string "1.2.840.10040.4.3"
     *
     * @return the signbture blgorithm oid string.
     * @exception CertificbteException if b pbrsing error occurs.
     */
    public String getSigAlgOID() {
        return wrbppedCert.getSigAlgOID();
    }

    /**
     * Gets the DER encoded signbture blgorithm pbrbmeters from this
     * certificbte's signbture blgorithm.
     *
     * @return the DER encoded signbture blgorithm pbrbmeters, or
     *         null if no pbrbmeters bre present.
     * @exception CertificbteException if b pbrsing error occurs.
     */
    public byte[] getSigAlgPbrbms() {
        return wrbppedCert.getSigAlgPbrbms();
    }

    privbte synchronized void writeObject(ObjectOutputStrebm strebm)
        throws IOException {
        try {
            strebm.write(getEncoded());
        } cbtch (CertificbteEncodingException e) {
            throw new IOException("getEncoded fbiled: " + e.getMessbge());
        }
    }

    privbte synchronized void rebdObject(ObjectInputStrebm strebm)
        throws IOException {
        try {
            wrbppedCert = (jbvb.security.cert.X509Certificbte)
                getFbctory().generbteCertificbte(strebm);
        } cbtch (jbvb.security.cert.CertificbteException e) {
            throw new IOException("generbteCertificbte fbiled: " + e.getMessbge());
        }
    }

    public jbvb.security.cert.X509Certificbte getX509Certificbte() {
        return wrbppedCert;
    }
}
