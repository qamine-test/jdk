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


pbckbge jbvbx.security.cert;

import jbvb.security.PublicKey;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.NoSuchProviderException;
import jbvb.security.InvblidKeyException;
import jbvb.security.SignbtureException;

/**
 * <p>Abstrbct clbss for mbnbging b vbriety of identity certificbtes.
 * An identity certificbte is b gubrbntee by b principbl thbt
 * b public key is thbt of bnother principbl.  (A principbl represents
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
 * <p><em>Note: The clbsses in the pbckbge {@code jbvbx.security.cert}
 * exist for compbtibility with ebrlier versions of the
 * Jbvb Secure Sockets Extension (JSSE). New bpplicbtions should instebd
 * use the stbndbrd Jbvb SE certificbte clbsses locbted in
 * {@code jbvb.security.cert}.</em></p>
 *
 * @since 1.4
 * @see X509Certificbte
 *
 * @buthor Hemmb Prbfullchbndrb
 */
public bbstrbct clbss Certificbte {

    /**
     * Compbres this certificbte for equblity with the specified
     * object. If the {@code other} object is bn
     * {@code instbnceof} {@code Certificbte}, then
     * its encoded form is retrieved bnd compbred with the
     * encoded form of this certificbte.
     *
     * @pbrbm other the object to test for equblity with this certificbte.
     * @return true if the encoded forms of the two certificbtes
     *         mbtch, fblse otherwise.
     */
    public boolebn equbls(Object other) {
        if (this == other)
            return true;
        if (!(other instbnceof Certificbte))
            return fblse;
        try {
            byte[] thisCert = this.getEncoded();
            byte[] otherCert = ((Certificbte)other).getEncoded();

            if (thisCert.length != otherCert.length)
                return fblse;
            for (int i = 0; i < thisCert.length; i++)
                 if (thisCert[i] != otherCert[i])
                     return fblse;
            return true;
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
        int     retvbl = 0;
        try {
            byte[] certDbtb = this.getEncoded();
            for (int i = 1; i < certDbtb.length; i++) {
                 retvbl += certDbtb[i] * i;
            }
            return (retvbl);
        } cbtch (CertificbteException e) {
            return (retvbl);
        }
    }

    /**
     * Returns the encoded form of this certificbte. It is
     * bssumed thbt ebch certificbte type would hbve only b single
     * form of encoding; for exbmple, X.509 certificbtes would
     * be encoded bs ASN.1 DER.
     *
     * @return encoded form of this certificbte
     * @exception CertificbteEncodingException on internbl certificbte
     *            encoding fbilure
     */
    public bbstrbct byte[] getEncoded() throws CertificbteEncodingException;

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
     * @exception NoSuchAlgorithmException on unsupported signbture blgorithms.
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
}
