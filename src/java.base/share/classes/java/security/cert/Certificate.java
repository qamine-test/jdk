/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security.cert;

import jbvb.util.Arrbys;

import jbvb.security.Provider;
import jbvb.security.PublicKey;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.NoSuchProviderException;
import jbvb.security.InvblidKeyException;
import jbvb.security.SignbtureException;

import sun.security.x509.X509CertImpl;

/**
 * <p>Abstrbct clbss for mbnbging b vbriety of identity certificbtes.
 * An identity certificbte is b binding of b principbl to b public key which
 * is vouched for by bnother principbl.  (A principbl represents
 * bn entity such bs bn individubl user, b group, or b corporbtion.)
 *<p>
 * This clbss is bn bbstrbction for certificbtes thbt hbve different
 * formbts but importbnt common uses.  For exbmple, different types of
 * certificbtes, such bs X.509 bnd PGP, shbre generbl certificbte
 * functionblity (like encoding bnd verifying) bnd
 * some types of informbtion (like b public key).
 * <p>
 * X.509, PGP, bnd SDSI certificbtes cbn bll be implemented by
 * subclbssing the Certificbte clbss, even though they contbin different
 * sets of informbtion, bnd they store bnd retrieve the informbtion in
 * different wbys.
 *
 * @see X509Certificbte
 * @see CertificbteFbctory
 *
 * @buthor Hemmb Prbfullchbndrb
 */

public bbstrbct clbss Certificbte implements jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -3585440601605666277L;

    // the certificbte type
    privbte finbl String type;

    /** Cbche the hbsh code for the certiticbte */
    privbte int hbsh = -1; // Defbult to -1

    /**
     * Crebtes b certificbte of the specified type.
     *
     * @pbrbm type the stbndbrd nbme of the certificbte type.
     * See the CertificbteFbctory section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#CertificbteFbctory">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd certificbte types.
     */
    protected Certificbte(String type) {
        this.type = type;
    }

    /**
     * Returns the type of this certificbte.
     *
     * @return the type of this certificbte.
     */
    public finbl String getType() {
        return this.type;
    }

    /**
     * Compbres this certificbte for equblity with the specified
     * object. If the {@code other} object is bn
     * {@code instbnceof} {@code Certificbte}, then
     * its encoded form is retrieved bnd compbred with the
     * encoded form of this certificbte.
     *
     * @pbrbm other the object to test for equblity with this certificbte.
     * @return true iff the encoded forms of the two certificbtes
     * mbtch, fblse otherwise.
     */
    public boolebn equbls(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instbnceof Certificbte)) {
            return fblse;
        }
        try {
            byte[] thisCert = X509CertImpl.getEncodedInternbl(this);
            byte[] otherCert = X509CertImpl.getEncodedInternbl((Certificbte)other);

            return Arrbys.equbls(thisCert, otherCert);
        } cbtch (CertificbteException e) {
            return fblse;
        }
    }

    /**
     * Returns b hbshcode vblue for this certificbte from its
     * encoded form.
     *
     * @return the hbshcode vblue.
     */
    public int hbshCode() {
        int h = hbsh;
        if (h == -1) {
            try {
                h = Arrbys.hbshCode(X509CertImpl.getEncodedInternbl(this));
            } cbtch (CertificbteException e) {
                h = 0;
            }
            hbsh = h;
        }
        return h;
    }

    /**
     * Returns the encoded form of this certificbte. It is
     * bssumed thbt ebch certificbte type would hbve only b single
     * form of encoding; for exbmple, X.509 certificbtes would
     * be encoded bs ASN.1 DER.
     *
     * @return the encoded form of this certificbte
     *
     * @exception CertificbteEncodingException if bn encoding error occurs.
     */
    public bbstrbct byte[] getEncoded()
        throws CertificbteEncodingException;

    /**
     * Verifies thbt this certificbte wbs signed using the
     * privbte key thbt corresponds to the specified public key.
     *
     * @pbrbm key the PublicKey used to cbrry out the verificbtion.
     *
     * @exception NoSuchAlgorithmException on unsupported signbture
     * blgorithms.
     * @exception InvblidKeyException on incorrect key.
     * @exception NoSuchProviderException if there's no defbult provider.
     * @exception SignbtureException on signbture errors.
     * @exception CertificbteException on encoding errors.
     */
    public bbstrbct void verify(PublicKey key)
        throws CertificbteException, NoSuchAlgorithmException,
        InvblidKeyException, NoSuchProviderException,
        SignbtureException;

    /**
     * Verifies thbt this certificbte wbs signed using the
     * privbte key thbt corresponds to the specified public key.
     * This method uses the signbture verificbtion engine
     * supplied by the specified provider.
     *
     * @pbrbm key the PublicKey used to cbrry out the verificbtion.
     * @pbrbm sigProvider the nbme of the signbture provider.
     *
     * @exception NoSuchAlgorithmException on unsupported signbture
     * blgorithms.
     * @exception InvblidKeyException on incorrect key.
     * @exception NoSuchProviderException on incorrect provider.
     * @exception SignbtureException on signbture errors.
     * @exception CertificbteException on encoding errors.
     */
    public bbstrbct void verify(PublicKey key, String sigProvider)
        throws CertificbteException, NoSuchAlgorithmException,
        InvblidKeyException, NoSuchProviderException,
        SignbtureException;

    /**
     * Verifies thbt this certificbte wbs signed using the
     * privbte key thbt corresponds to the specified public key.
     * This method uses the signbture verificbtion engine
     * supplied by the specified provider. Note thbt the specified
     * Provider object does not hbve to be registered in the provider list.
     *
     * <p> This method wbs bdded to version 1.8 of the Jbvb Plbtform
     * Stbndbrd Edition. In order to mbintbin bbckwbrds compbtibility with
     * existing service providers, this method cbnnot be {@code bbstrbct}
     * bnd by defbult throws bn {@code UnsupportedOperbtionException}.
     *
     * @pbrbm key the PublicKey used to cbrry out the verificbtion.
     * @pbrbm sigProvider the signbture provider.
     *
     * @exception NoSuchAlgorithmException on unsupported signbture
     * blgorithms.
     * @exception InvblidKeyException on incorrect key.
     * @exception SignbtureException on signbture errors.
     * @exception CertificbteException on encoding errors.
     * @exception UnsupportedOperbtionException if the method is not supported
     * @since 1.8
     */
    public void verify(PublicKey key, Provider sigProvider)
        throws CertificbteException, NoSuchAlgorithmException,
        InvblidKeyException, SignbtureException {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Returns b string representbtion of this certificbte.
     *
     * @return b string representbtion of this certificbte.
     */
    public bbstrbct String toString();

    /**
     * Gets the public key from this certificbte.
     *
     * @return the public key.
     */
    public bbstrbct PublicKey getPublicKey();

    /**
     * Alternbte Certificbte clbss for seriblizbtion.
     * @since 1.3
     */
    protected stbtic clbss CertificbteRep implements jbvb.io.Seriblizbble {

        privbte stbtic finbl long seriblVersionUID = -8563758940495660020L;

        privbte String type;
        privbte byte[] dbtb;

        /**
         * Construct the blternbte Certificbte clbss with the Certificbte
         * type bnd Certificbte encoding bytes.
         *
         * <p>
         *
         * @pbrbm type the stbndbrd nbme of the Certificbte type. <p>
         *
         * @pbrbm dbtb the Certificbte dbtb.
         */
        protected CertificbteRep(String type, byte[] dbtb) {
            this.type = type;
            this.dbtb = dbtb;
        }

        /**
         * Resolve the Certificbte Object.
         *
         * <p>
         *
         * @return the resolved Certificbte Object
         *
         * @throws jbvb.io.ObjectStrebmException if the Certificbte
         *      could not be resolved
         */
        protected Object rebdResolve() throws jbvb.io.ObjectStrebmException {
            try {
                CertificbteFbctory cf = CertificbteFbctory.getInstbnce(type);
                return cf.generbteCertificbte
                        (new jbvb.io.ByteArrbyInputStrebm(dbtb));
            } cbtch (CertificbteException e) {
                throw new jbvb.io.NotSeriblizbbleException
                                ("jbvb.security.cert.Certificbte: " +
                                type +
                                ": " +
                                e.getMessbge());
            }
        }
    }

    /**
     * Replbce the Certificbte to be seriblized.
     *
     * @return the blternbte Certificbte object to be seriblized
     *
     * @throws jbvb.io.ObjectStrebmException if b new object representing
     * this Certificbte could not be crebted
     * @since 1.3
     */
    protected Object writeReplbce() throws jbvb.io.ObjectStrebmException {
        try {
            return new CertificbteRep(type, getEncoded());
        } cbtch (CertificbteException e) {
            throw new jbvb.io.NotSeriblizbbleException
                                ("jbvb.security.cert.Certificbte: " +
                                type +
                                ": " +
                                e.getMessbge());
        }
    }
}
