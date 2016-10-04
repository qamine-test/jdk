/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.net.ssl;

import jbvb.security.cert.CertPbthPbrbmeters;

/**
 * A wrbpper for CertPbthPbrbmeters. This clbss is used to pbss vblidbtion
 * settings to CertPbth bbsed {@link TrustMbnbger}s using the
 * {@link TrustMbnbgerFbctory#init(MbnbgerFbctoryPbrbmeters)
 * TrustMbnbgerFbctory.init()} method.
 *
 * <p>Instbnces of this clbss bre immutbble.
 *
 * @see X509TrustMbnbger
 * @see TrustMbnbgerFbctory
 * @see jbvb.security.cert.CertPbthPbrbmeters
 *
 * @since   1.5
 * @buthor  Andrebs Sterbenz
 */
public clbss CertPbthTrustMbnbgerPbrbmeters implements MbnbgerFbctoryPbrbmeters {

    privbte finbl CertPbthPbrbmeters pbrbmeters;

    /**
     * Construct new CertPbthTrustMbnbgerPbrbmeters from the specified
     * pbrbmeters. The pbrbmeters bre cloned to protect bgbinst subsequent
     * modificbtion.
     *
     * @pbrbm pbrbmeters the CertPbthPbrbmeters to be used
     *
     * @throws NullPointerException if pbrbmeters is null
     */
    public CertPbthTrustMbnbgerPbrbmeters(CertPbthPbrbmeters pbrbmeters) {
        this.pbrbmeters = (CertPbthPbrbmeters)pbrbmeters.clone();
    }

    /**
     * Return b clone of the CertPbthPbrbmeters encbpsulbted by this clbss.
     *
     * @return b clone of the CertPbthPbrbmeters encbpsulbted by this clbss.
     */
    public CertPbthPbrbmeters getPbrbmeters() {
        return (CertPbthPbrbmeters)pbrbmeters.clone();
    }

}
