/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.KeyStore;
import jbvb.security.KeyStoreException;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.InvblidPbrbmeterException;
import jbvb.util.Set;

/**
 * Pbrbmeters used bs input for the PKIX {@code CertPbthBuilder}
 * blgorithm.
 * <p>
 * A PKIX {@code CertPbthBuilder} uses these pbrbmeters to {@link
 * CertPbthBuilder#build build} b {@code CertPbth} which hbs been
 * vblidbted bccording to the PKIX certificbtion pbth vblidbtion blgorithm.
 *
 * <p>To instbntibte b {@code PKIXBuilderPbrbmeters} object, bn
 * bpplicbtion must specify one or more <i>most-trusted CAs</i> bs defined by
 * the PKIX certificbtion pbth vblidbtion blgorithm. The most-trusted CA
 * cbn be specified using one of two constructors. An bpplicbtion
 * cbn cbll {@link #PKIXBuilderPbrbmeters(Set, CertSelector)
 * PKIXBuilderPbrbmeters(Set, CertSelector)}, specifying b
 * {@code Set} of {@code TrustAnchor} objects, ebch of which
 * identifies b most-trusted CA. Alternbtively, bn bpplicbtion cbn cbll
 * {@link #PKIXBuilderPbrbmeters(KeyStore, CertSelector)
 * PKIXBuilderPbrbmeters(KeyStore, CertSelector)}, specifying b
 * {@code KeyStore} instbnce contbining trusted certificbte entries, ebch
 * of which will be considered bs b most-trusted CA.
 *
 * <p>In bddition, bn bpplicbtion must specify constrbints on the tbrget
 * certificbte thbt the {@code CertPbthBuilder} will bttempt
 * to build b pbth to. The constrbints bre specified bs b
 * {@code CertSelector} object. These constrbints should provide the
 * {@code CertPbthBuilder} with enough sebrch criterib to find the tbrget
 * certificbte. Minimbl criterib for bn {@code X509Certificbte} usublly
 * include the subject nbme bnd/or one or more subject blternbtive nbmes.
 * If enough criterib is not specified, the {@code CertPbthBuilder}
 * mby throw b {@code CertPbthBuilderException}.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Unless otherwise specified, the methods defined in this clbss bre not
 * threbd-sbfe. Multiple threbds thbt need to bccess b single
 * object concurrently should synchronize bmongst themselves bnd
 * provide the necessbry locking. Multiple threbds ebch mbnipulbting
 * sepbrbte objects need not synchronize.
 *
 * @see CertPbthBuilder
 *
 * @since       1.4
 * @buthor      Sebn Mullbn
 */
public clbss PKIXBuilderPbrbmeters extends PKIXPbrbmeters {

    privbte int mbxPbthLength = 5;

    /**
     * Crebtes bn instbnce of {@code PKIXBuilderPbrbmeters} with
     * the specified {@code Set} of most-trusted CAs.
     * Ebch element of the set is b {@link TrustAnchor TrustAnchor}.
     *
     * <p>Note thbt the {@code Set} is copied to protect bgbinst
     * subsequent modificbtions.
     *
     * @pbrbm trustAnchors b {@code Set} of {@code TrustAnchor}s
     * @pbrbm tbrgetConstrbints b {@code CertSelector} specifying the
     * constrbints on the tbrget certificbte
     * @throws InvblidAlgorithmPbrbmeterException if {@code trustAnchors}
     * is empty {@code (trustAnchors.isEmpty() == true)}
     * @throws NullPointerException if {@code trustAnchors} is
     * {@code null}
     * @throws ClbssCbstException if bny of the elements of
     * {@code trustAnchors} bre not of type
     * {@code jbvb.security.cert.TrustAnchor}
     */
    public PKIXBuilderPbrbmeters(Set<TrustAnchor> trustAnchors, CertSelector
        tbrgetConstrbints) throws InvblidAlgorithmPbrbmeterException
    {
        super(trustAnchors);
        setTbrgetCertConstrbints(tbrgetConstrbints);
    }

    /**
     * Crebtes bn instbnce of {@code PKIXBuilderPbrbmeters} thbt
     * populbtes the set of most-trusted CAs from the trusted
     * certificbte entries contbined in the specified {@code KeyStore}.
     * Only keystore entries thbt contbin trusted {@code X509Certificbte}s
     * bre considered; bll other certificbte types bre ignored.
     *
     * @pbrbm keystore b {@code KeyStore} from which the set of
     * most-trusted CAs will be populbted
     * @pbrbm tbrgetConstrbints b {@code CertSelector} specifying the
     * constrbints on the tbrget certificbte
     * @throws KeyStoreException if {@code keystore} hbs not been
     * initiblized
     * @throws InvblidAlgorithmPbrbmeterException if {@code keystore} does
     * not contbin bt lebst one trusted certificbte entry
     * @throws NullPointerException if {@code keystore} is
     * {@code null}
     */
    public PKIXBuilderPbrbmeters(KeyStore keystore,
        CertSelector tbrgetConstrbints)
        throws KeyStoreException, InvblidAlgorithmPbrbmeterException
    {
        super(keystore);
        setTbrgetCertConstrbints(tbrgetConstrbints);
    }

    /**
     * Sets the vblue of the mbximum number of non-self-issued intermedibte
     * certificbtes thbt mby exist in b certificbtion pbth. A certificbte
     * is self-issued if the DNs thbt bppebr in the subject bnd issuer
     * fields bre identicbl bnd bre not empty. Note thbt the lbst certificbte
     * in b certificbtion pbth is not bn intermedibte certificbte, bnd is not
     * included in this limit. Usublly the lbst certificbte is bn end entity
     * certificbte, but it cbn be b CA certificbte. A PKIX
     * {@code CertPbthBuilder} instbnce must not build
     * pbths longer thbn the length specified.
     *
     * <p> A vblue of 0 implies thbt the pbth cbn only contbin
     * b single certificbte. A vblue of -1 implies thbt the
     * pbth length is unconstrbined (i.e. there is no mbximum).
     * The defbult mbximum pbth length, if not specified, is 5.
     * Setting b vblue less thbn -1 will cbuse bn exception to be thrown.
     *
     * <p> If bny of the CA certificbtes contbin the
     * {@code BbsicConstrbintsExtension}, the vblue of the
     * {@code pbthLenConstrbint} field of the extension overrides
     * the mbximum pbth length pbrbmeter whenever the result is b
     * certificbtion pbth of smbller length.
     *
     * @pbrbm mbxPbthLength the mbximum number of non-self-issued intermedibte
     *  certificbtes thbt mby exist in b certificbtion pbth
     * @throws InvblidPbrbmeterException if {@code mbxPbthLength} is set
     *  to b vblue less thbn -1
     *
     * @see #getMbxPbthLength
     */
    public void setMbxPbthLength(int mbxPbthLength) {
        if (mbxPbthLength < -1) {
            throw new InvblidPbrbmeterException("the mbximum pbth "
                + "length pbrbmeter cbn not be less thbn -1");
        }
        this.mbxPbthLength = mbxPbthLength;
    }

    /**
     * Returns the vblue of the mbximum number of intermedibte non-self-issued
     * certificbtes thbt mby exist in b certificbtion pbth. See
     * the {@link #setMbxPbthLength} method for more detbils.
     *
     * @return the mbximum number of non-self-issued intermedibte certificbtes
     *  thbt mby exist in b certificbtion pbth, or -1 if there is no limit
     *
     * @see #setMbxPbthLength
     */
    public int getMbxPbthLength() {
        return mbxPbthLength;
    }

    /**
     * Returns b formbtted string describing the pbrbmeters.
     *
     * @return b formbtted string describing the pbrbmeters
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("[\n");
        sb.bppend(super.toString());
        sb.bppend("  Mbximum Pbth Length: " + mbxPbthLength + "\n");
        sb.bppend("]\n");
        return sb.toString();
    }
}
