/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This clbss is bn bbstrbction of certificbte revocbtion lists (CRLs) thbt
 * hbve different formbts but importbnt common uses. For exbmple, bll CRLs
 * shbre the functionblity of listing revoked certificbtes, bnd cbn be queried
 * on whether or not they list b given certificbte.
 * <p>
 * Speciblized CRL types cbn be defined by subclbssing off of this bbstrbct
 * clbss.
 *
 * @buthor Hemmb Prbfullchbndrb
 *
 *
 * @see X509CRL
 * @see CertificbteFbctory
 *
 * @since 1.2
 */

public bbstrbct clbss CRL {

    // the CRL type
    privbte String type;

    /**
     * Crebtes b CRL of the specified type.
     *
     * @pbrbm type the stbndbrd nbme of the CRL type.
     * See Appendix A in the <b href=
     * "../../../../technotes/guides/security/crypto/CryptoSpec.html#AppA">
     * Jbvb Cryptogrbphy Architecture API Specificbtion &bmp; Reference </b>
     * for informbtion bbout stbndbrd CRL types.
     */
    protected CRL(String type) {
        this.type = type;
    }

    /**
     * Returns the type of this CRL.
     *
     * @return the type of this CRL.
     */
    public finbl String getType() {
        return this.type;
    }

    /**
     * Returns b string representbtion of this CRL.
     *
     * @return b string representbtion of this CRL.
     */
    public bbstrbct String toString();

    /**
     * Checks whether the given certificbte is on this CRL.
     *
     * @pbrbm cert the certificbte to check for.
     * @return true if the given certificbte is on this CRL,
     * fblse otherwise.
     */
    public bbstrbct boolebn isRevoked(Certificbte cert);
}
