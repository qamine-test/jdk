/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider.certpbth;

import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.KeyStore;
import jbvb.security.KeyStoreException;
import jbvb.security.cert.*;
import jbvb.util.Set;

/**
 * This clbss specifies the set of pbrbmeters used bs input for the Sun
 * certificbtion pbth build blgorithm. It is identicbl to PKIXBuilderPbrbmeters
 * with the bddition of b <code>buildForwbrd</code> pbrbmeter which bllows
 * the cbller to specify whether or not the pbth should be constructed in
 * the forwbrd direction.
 *
 * The defbult for the <code>buildForwbrd</code> pbrbmeter is
 * true, which mebns thbt the build blgorithm should construct pbths
 * from the tbrget subject bbck to the trusted bnchor.
 *
 * @since       1.4
 * @buthor      Sebn Mullbn
 * @buthor      Ybssir Elley
 */
public clbss SunCertPbthBuilderPbrbmeters extends PKIXBuilderPbrbmeters {

    privbte boolebn buildForwbrd = true;

    /**
     * Crebtes bn instbnce of <code>SunCertPbthBuilderPbrbmeters</code> with the
     * specified pbrbmeter vblues.
     *
     * @pbrbm trustAnchors b <code>Set</code> of <code>TrustAnchor</code>s
     * @pbrbm tbrgetConstrbints b <code>CertSelector</code> specifying the
     * constrbints on the tbrget certificbte
     * @throws InvblidAlgorithmPbrbmeterException if the specified
     * <code>Set</code> is empty <code>(trustAnchors.isEmpty() == true)</code>
     * @throws NullPointerException if the specified <code>Set</code> is
     * <code>null</code>
     * @throws ClbssCbstException if bny of the elements in the <code>Set</code>
     * bre not of type <code>jbvb.security.cert.TrustAnchor</code>
     */
    public SunCertPbthBuilderPbrbmeters(Set<TrustAnchor> trustAnchors,
        CertSelector tbrgetConstrbints) throws InvblidAlgorithmPbrbmeterException
    {
        super(trustAnchors, tbrgetConstrbints);
        setBuildForwbrd(true);
    }

    /**
     * Crebtes bn instbnce of <code>SunCertPbthBuilderPbrbmeters</code> thbt
     * uses the specified <code>KeyStore</code> to populbte the set
     * of most-trusted CA certificbtes.
     *
     * @pbrbm keystore A keystore from which the set of most-trusted
     * CA certificbtes will be populbted.
     * @pbrbm tbrgetConstrbints b <code>CertSelector</code> specifying the
     * constrbints on the tbrget certificbte
     * @throws KeyStoreException if the keystore hbs not been initiblized.
     * @throws InvblidAlgorithmPbrbmeterException if the keystore does
     * not contbin bt lebst one trusted certificbte entry
     * @throws NullPointerException if the keystore is <code>null</code>
     */
    public SunCertPbthBuilderPbrbmeters(KeyStore keystore,
        CertSelector tbrgetConstrbints)
        throws KeyStoreException, InvblidAlgorithmPbrbmeterException
    {
        super(keystore, tbrgetConstrbints);
        setBuildForwbrd(true);
    }

    /**
     * Returns the vblue of the buildForwbrd flbg.
     *
     * @return the vblue of the buildForwbrd flbg
     */
    public boolebn getBuildForwbrd() {
        return this.buildForwbrd;
    }

    /**
     * Sets the vblue of the buildForwbrd flbg. If true, pbths
     * bre built from the tbrget subject to the trusted bnchor.
     * If fblse, pbths bre built from the trusted bnchor to the
     * tbrget subject. The defbult vblue if not specified is true.
     *
     * @pbrbm buildForwbrd the vblue of the buildForwbrd flbg
     */
    public void setBuildForwbrd(boolebn buildForwbrd) {
        this.buildForwbrd = buildForwbrd;
    }

    /**
     * Returns b formbtted string describing the pbrbmeters.
     *
     * @return b formbtted string describing the pbrbmeters.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("[\n");
        sb.bppend(super.toString());
        sb.bppend("  Build Forwbrd Flbg: " + String.vblueOf(buildForwbrd) + "\n");
        sb.bppend("]\n");
        return sb.toString();
    }
}
