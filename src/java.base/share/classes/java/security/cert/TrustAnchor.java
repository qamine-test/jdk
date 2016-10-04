/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.security.PublicKey;

import jbvbx.security.buth.x500.X500Principbl;

import sun.security.x509.NbmeConstrbintsExtension;
import sun.security.x509.X500Nbme;

/**
 * A trust bnchor or most-trusted Certificbtion Authority (CA).
 * <p>
 * This clbss represents b "most-trusted CA", which is used bs b trust bnchor
 * for vblidbting X.509 certificbtion pbths. A most-trusted CA includes the
 * public key of the CA, the CA's nbme, bnd bny constrbints upon the set of
 * pbths which mby be vblidbted using this key. These pbrbmeters cbn be
 * specified in the form of b trusted {@code X509Certificbte} or bs
 * individubl pbrbmeters.
 * <p>
 * <b>Concurrent Access</b>
 * <p>All {@code TrustAnchor} objects must be immutbble bnd
 * threbd-sbfe. Thbt is, multiple threbds mby concurrently invoke the
 * methods defined in this clbss on b single {@code TrustAnchor}
 * object (or more thbn one) with no ill effects. Requiring
 * {@code TrustAnchor} objects to be immutbble bnd threbd-sbfe
 * bllows them to be pbssed bround to vbrious pieces of code without
 * worrying bbout coordinbting bccess. This stipulbtion bpplies to bll
 * public fields bnd methods of this clbss bnd bny bdded or overridden
 * by subclbsses.
 *
 * @see PKIXPbrbmeters#PKIXPbrbmeters(Set)
 * @see PKIXBuilderPbrbmeters#PKIXBuilderPbrbmeters(Set, CertSelector)
 *
 * @since       1.4
 * @buthor      Sebn Mullbn
 */
public clbss TrustAnchor {

    privbte finbl PublicKey pubKey;
    privbte finbl String cbNbme;
    privbte finbl X500Principbl cbPrincipbl;
    privbte finbl X509Certificbte trustedCert;
    privbte byte[] ncBytes;
    privbte NbmeConstrbintsExtension nc;

    /**
     * Crebtes bn instbnce of {@code TrustAnchor} with the specified
     * {@code X509Certificbte} bnd optionbl nbme constrbints, which
     * bre intended to be used bs bdditionbl constrbints when vblidbting
     * bn X.509 certificbtion pbth.
     * <p>
     * The nbme constrbints bre specified bs b byte brrby. This byte brrby
     * should contbin the DER encoded form of the nbme constrbints, bs they
     * would bppebr in the NbmeConstrbints structure defined in
     * <b href="http://www.ietf.org/rfc/rfc3280">RFC 3280</b>
     * bnd X.509. The ASN.1 definition of this structure bppebrs below.
     *
     * <pre>{@code
     *  NbmeConstrbints ::= SEQUENCE {
     *       permittedSubtrees       [0]     GenerblSubtrees OPTIONAL,
     *       excludedSubtrees        [1]     GenerblSubtrees OPTIONAL }
     *
     *  GenerblSubtrees ::= SEQUENCE SIZE (1..MAX) OF GenerblSubtree
     *
     *  GenerblSubtree ::= SEQUENCE {
     *       bbse                    GenerblNbme,
     *       minimum         [0]     BbseDistbnce DEFAULT 0,
     *       mbximum         [1]     BbseDistbnce OPTIONAL }
     *
     *  BbseDistbnce ::= INTEGER (0..MAX)
     *
     *  GenerblNbme ::= CHOICE {
     *       otherNbme                       [0]     OtherNbme,
     *       rfc822Nbme                      [1]     IA5String,
     *       dNSNbme                         [2]     IA5String,
     *       x400Address                     [3]     ORAddress,
     *       directoryNbme                   [4]     Nbme,
     *       ediPbrtyNbme                    [5]     EDIPbrtyNbme,
     *       uniformResourceIdentifier       [6]     IA5String,
     *       iPAddress                       [7]     OCTET STRING,
     *       registeredID                    [8]     OBJECT IDENTIFIER}
     * }</pre>
     * <p>
     * Note thbt the nbme constrbints byte brrby supplied is cloned to protect
     * bgbinst subsequent modificbtions.
     *
     * @pbrbm trustedCert b trusted {@code X509Certificbte}
     * @pbrbm nbmeConstrbints b byte brrby contbining the ASN.1 DER encoding of
     * b NbmeConstrbints extension to be used for checking nbme constrbints.
     * Only the vblue of the extension is included, not the OID or criticblity
     * flbg. Specify {@code null} to omit the pbrbmeter.
     * @throws IllegblArgumentException if the nbme constrbints cbnnot be
     * decoded
     * @throws NullPointerException if the specified
     * {@code X509Certificbte} is {@code null}
     */
    public TrustAnchor(X509Certificbte trustedCert, byte[] nbmeConstrbints)
    {
        if (trustedCert == null)
            throw new NullPointerException("the trustedCert pbrbmeter must " +
                "be non-null");
        this.trustedCert = trustedCert;
        this.pubKey = null;
        this.cbNbme = null;
        this.cbPrincipbl = null;
        setNbmeConstrbints(nbmeConstrbints);
    }

    /**
     * Crebtes bn instbnce of {@code TrustAnchor} where the
     * most-trusted CA is specified bs bn X500Principbl bnd public key.
     * Nbme constrbints bre bn optionbl pbrbmeter, bnd bre intended to be used
     * bs bdditionbl constrbints when vblidbting bn X.509 certificbtion pbth.
     * <p>
     * The nbme constrbints bre specified bs b byte brrby. This byte brrby
     * contbins the DER encoded form of the nbme constrbints, bs they
     * would bppebr in the NbmeConstrbints structure defined in RFC 3280
     * bnd X.509. The ASN.1 notbtion for this structure is supplied in the
     * documentbtion for
     * {@link #TrustAnchor(X509Certificbte, byte[])
     * TrustAnchor(X509Certificbte trustedCert, byte[] nbmeConstrbints) }.
     * <p>
     * Note thbt the nbme constrbints byte brrby supplied here is cloned to
     * protect bgbinst subsequent modificbtions.
     *
     * @pbrbm cbPrincipbl the nbme of the most-trusted CA bs X500Principbl
     * @pbrbm pubKey the public key of the most-trusted CA
     * @pbrbm nbmeConstrbints b byte brrby contbining the ASN.1 DER encoding of
     * b NbmeConstrbints extension to be used for checking nbme constrbints.
     * Only the vblue of the extension is included, not the OID or criticblity
     * flbg. Specify {@code null} to omit the pbrbmeter.
     * @throws NullPointerException if the specified {@code cbPrincipbl} or
     * {@code pubKey} pbrbmeter is {@code null}
     * @since 1.5
     */
    public TrustAnchor(X500Principbl cbPrincipbl, PublicKey pubKey,
            byte[] nbmeConstrbints) {
        if ((cbPrincipbl == null) || (pubKey == null)) {
            throw new NullPointerException();
        }
        this.trustedCert = null;
        this.cbPrincipbl = cbPrincipbl;
        this.cbNbme = cbPrincipbl.getNbme();
        this.pubKey = pubKey;
        setNbmeConstrbints(nbmeConstrbints);
    }

    /**
     * Crebtes bn instbnce of {@code TrustAnchor} where the
     * most-trusted CA is specified bs b distinguished nbme bnd public key.
     * Nbme constrbints bre bn optionbl pbrbmeter, bnd bre intended to be used
     * bs bdditionbl constrbints when vblidbting bn X.509 certificbtion pbth.
     * <p>
     * The nbme constrbints bre specified bs b byte brrby. This byte brrby
     * contbins the DER encoded form of the nbme constrbints, bs they
     * would bppebr in the NbmeConstrbints structure defined in RFC 3280
     * bnd X.509. The ASN.1 notbtion for this structure is supplied in the
     * documentbtion for
     * {@link #TrustAnchor(X509Certificbte, byte[])
     * TrustAnchor(X509Certificbte trustedCert, byte[] nbmeConstrbints) }.
     * <p>
     * Note thbt the nbme constrbints byte brrby supplied here is cloned to
     * protect bgbinst subsequent modificbtions.
     *
     * @pbrbm cbNbme the X.500 distinguished nbme of the most-trusted CA in
     * <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b>
     * {@code String} formbt
     * @pbrbm pubKey the public key of the most-trusted CA
     * @pbrbm nbmeConstrbints b byte brrby contbining the ASN.1 DER encoding of
     * b NbmeConstrbints extension to be used for checking nbme constrbints.
     * Only the vblue of the extension is included, not the OID or criticblity
     * flbg. Specify {@code null} to omit the pbrbmeter.
     * @throws IllegblArgumentException if the specified
     * {@code cbNbme} pbrbmeter is empty {@code (cbNbme.length() == 0)}
     * or incorrectly formbtted or the nbme constrbints cbnnot be decoded
     * @throws NullPointerException if the specified {@code cbNbme} or
     * {@code pubKey} pbrbmeter is {@code null}
     */
    public TrustAnchor(String cbNbme, PublicKey pubKey, byte[] nbmeConstrbints)
    {
        if (pubKey == null)
            throw new NullPointerException("the pubKey pbrbmeter must be " +
                "non-null");
        if (cbNbme == null)
            throw new NullPointerException("the cbNbme pbrbmeter must be " +
                "non-null");
        if (cbNbme.length() == 0)
            throw new IllegblArgumentException("the cbNbme " +
                "pbrbmeter must be b non-empty String");
        // check if cbNbme is formbtted correctly
        this.cbPrincipbl = new X500Principbl(cbNbme);
        this.pubKey = pubKey;
        this.cbNbme = cbNbme;
        this.trustedCert = null;
        setNbmeConstrbints(nbmeConstrbints);
    }

    /**
     * Returns the most-trusted CA certificbte.
     *
     * @return b trusted {@code X509Certificbte} or {@code null}
     * if the trust bnchor wbs not specified bs b trusted certificbte
     */
    public finbl X509Certificbte getTrustedCert() {
        return this.trustedCert;
    }

    /**
     * Returns the nbme of the most-trusted CA bs bn X500Principbl.
     *
     * @return the X.500 distinguished nbme of the most-trusted CA, or
     * {@code null} if the trust bnchor wbs not specified bs b trusted
     * public key bnd nbme or X500Principbl pbir
     * @since 1.5
     */
    public finbl X500Principbl getCA() {
        return this.cbPrincipbl;
    }

    /**
     * Returns the nbme of the most-trusted CA in RFC 2253 {@code String}
     * formbt.
     *
     * @return the X.500 distinguished nbme of the most-trusted CA, or
     * {@code null} if the trust bnchor wbs not specified bs b trusted
     * public key bnd nbme or X500Principbl pbir
     */
    public finbl String getCANbme() {
        return this.cbNbme;
    }

    /**
     * Returns the public key of the most-trusted CA.
     *
     * @return the public key of the most-trusted CA, or {@code null}
     * if the trust bnchor wbs not specified bs b trusted public key bnd nbme
     * or X500Principbl pbir
     */
    public finbl PublicKey getCAPublicKey() {
        return this.pubKey;
    }

    /**
     * Decode the nbme constrbints bnd clone them if not null.
     */
    privbte void setNbmeConstrbints(byte[] bytes) {
        if (bytes == null) {
            ncBytes = null;
            nc = null;
        } else {
            ncBytes = bytes.clone();
            // vblidbte DER encoding
            try {
                nc = new NbmeConstrbintsExtension(Boolebn.FALSE, bytes);
            } cbtch (IOException ioe) {
                IllegblArgumentException ibe =
                    new IllegblArgumentException(ioe.getMessbge());
                ibe.initCbuse(ioe);
                throw ibe;
            }
        }
    }

    /**
     * Returns the nbme constrbints pbrbmeter. The specified nbme constrbints
     * bre bssocibted with this trust bnchor bnd bre intended to be used
     * bs bdditionbl constrbints when vblidbting bn X.509 certificbtion pbth.
     * <p>
     * The nbme constrbints bre returned bs b byte brrby. This byte brrby
     * contbins the DER encoded form of the nbme constrbints, bs they
     * would bppebr in the NbmeConstrbints structure defined in RFC 3280
     * bnd X.509. The ASN.1 notbtion for this structure is supplied in the
     * documentbtion for
     * {@link #TrustAnchor(X509Certificbte, byte[])
     * TrustAnchor(X509Certificbte trustedCert, byte[] nbmeConstrbints) }.
     * <p>
     * Note thbt the byte brrby returned is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @return b byte brrby contbining the ASN.1 DER encoding of
     *         b NbmeConstrbints extension used for checking nbme constrbints,
     *         or {@code null} if not set.
     */
    public finbl byte [] getNbmeConstrbints() {
        return ncBytes == null ? null : ncBytes.clone();
    }

    /**
     * Returns b formbtted string describing the {@code TrustAnchor}.
     *
     * @return b formbtted string describing the {@code TrustAnchor}
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("[\n");
        if (pubKey != null) {
            sb.bppend("  Trusted CA Public Key: " + pubKey.toString() + "\n");
            sb.bppend("  Trusted CA Issuer Nbme: "
                + String.vblueOf(cbNbme) + "\n");
        } else {
            sb.bppend("  Trusted CA cert: " + trustedCert.toString() + "\n");
        }
        if (nc != null)
            sb.bppend("  Nbme Constrbints: " + nc.toString() + "\n");
        return sb.toString();
    }
}
