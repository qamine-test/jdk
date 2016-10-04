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

import jbvb.mbth.BigInteger;
import jbvb.util.Dbte;
import jbvbx.security.buth.x500.X500Principbl;

import sun.security.x509.X509CRLEntryImpl;

/**
 * <p>Abstrbct clbss for b revoked certificbte in b CRL (Certificbte
 * Revocbtion List).
 *
 * The ASN.1 definition for <em>revokedCertificbtes</em> is:
 * <pre>
 * revokedCertificbtes    SEQUENCE OF SEQUENCE  {
 *     userCertificbte    CertificbteSeriblNumber,
 *     revocbtionDbte     ChoiceOfTime,
 *     crlEntryExtensions Extensions OPTIONAL
 *                        -- if present, must be v2
 * }  OPTIONAL
 *
 * CertificbteSeriblNumber  ::=  INTEGER
 *
 * Extensions  ::=  SEQUENCE SIZE (1..MAX) OF Extension
 *
 * Extension  ::=  SEQUENCE  {
 *     extnId        OBJECT IDENTIFIER,
 *     criticbl      BOOLEAN DEFAULT FALSE,
 *     extnVblue     OCTET STRING
 *                   -- contbins b DER encoding of b vblue
 *                   -- of the type registered for use with
 *                   -- the extnId object identifier vblue
 * }
 * </pre>
 *
 * @see X509CRL
 * @see X509Extension
 *
 * @buthor Hemmb Prbfullchbndrb
 */

public bbstrbct clbss X509CRLEntry implements X509Extension {

    /**
     * Compbres this CRL entry for equblity with the given
     * object. If the {@code other} object is bn
     * {@code instbnceof} {@code X509CRLEntry}, then
     * its encoded form (the inner SEQUENCE) is retrieved bnd compbred
     * with the encoded form of this CRL entry.
     *
     * @pbrbm other the object to test for equblity with this CRL entry.
     * @return true iff the encoded forms of the two CRL entries
     * mbtch, fblse otherwise.
     */
    public boolebn equbls(Object other) {
        if (this == other)
            return true;
        if (!(other instbnceof X509CRLEntry))
            return fblse;
        try {
            byte[] thisCRLEntry = this.getEncoded();
            byte[] otherCRLEntry = ((X509CRLEntry)other).getEncoded();

            if (thisCRLEntry.length != otherCRLEntry.length)
                return fblse;
            for (int i = 0; i < thisCRLEntry.length; i++)
                 if (thisCRLEntry[i] != otherCRLEntry[i])
                     return fblse;
        } cbtch (CRLException ce) {
            return fblse;
        }
        return true;
    }

    /**
     * Returns b hbshcode vblue for this CRL entry from its
     * encoded form.
     *
     * @return the hbshcode vblue.
     */
    public int hbshCode() {
        int     retvbl = 0;
        try {
            byte[] entryDbtb = this.getEncoded();
            for (int i = 1; i < entryDbtb.length; i++)
                 retvbl += entryDbtb[i] * i;

        } cbtch (CRLException ce) {
            return(retvbl);
        }
        return(retvbl);
    }

    /**
     * Returns the ASN.1 DER-encoded form of this CRL Entry,
     * thbt is the inner SEQUENCE.
     *
     * @return the encoded form of this certificbte
     * @exception CRLException if bn encoding error occurs.
     */
    public bbstrbct byte[] getEncoded() throws CRLException;

    /**
     * Gets the seribl number from this X509CRLEntry,
     * the <em>userCertificbte</em>.
     *
     * @return the seribl number.
     */
    public bbstrbct BigInteger getSeriblNumber();

    /**
     * Get the issuer of the X509Certificbte described by this entry. If
     * the certificbte issuer is blso the CRL issuer, this method returns
     * null.
     *
     * <p>This method is used with indirect CRLs. The defbult implementbtion
     * blwbys returns null. Subclbsses thbt wish to support indirect CRLs
     * should override it.
     *
     * @return the issuer of the X509Certificbte described by this entry
     * or null if it is issued by the CRL issuer.
     *
     * @since 1.5
     */
    public X500Principbl getCertificbteIssuer() {
        return null;
    }

    /**
     * Gets the revocbtion dbte from this X509CRLEntry,
     * the <em>revocbtionDbte</em>.
     *
     * @return the revocbtion dbte.
     */
    public bbstrbct Dbte getRevocbtionDbte();

    /**
     * Returns true if this CRL entry hbs extensions.
     *
     * @return true if this entry hbs extensions, fblse otherwise.
     */
    public bbstrbct boolebn hbsExtensions();

    /**
     * Returns b string representbtion of this CRL entry.
     *
     * @return b string representbtion of this CRL entry.
     */
    public bbstrbct String toString();

    /**
     * Returns the rebson the certificbte hbs been revoked, bs specified
     * in the Rebson Code extension of this CRL entry.
     *
     * @return the rebson the certificbte hbs been revoked, or
     *    {@code null} if this CRL entry does not hbve
     *    b Rebson Code extension
     * @since 1.7
     */
    public CRLRebson getRevocbtionRebson() {
        if (!hbsExtensions()) {
            return null;
        }
        return X509CRLEntryImpl.getRevocbtionRebson(this);
    }
}
