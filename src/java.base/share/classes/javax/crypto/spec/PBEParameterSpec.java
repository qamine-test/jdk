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

pbckbge jbvbx.crypto.spec;

import jbvb.security.spec.AlgorithmPbrbmeterSpec;

/**
 * This clbss specifies the set of pbrbmeters used with pbssword-bbsed
 * encryption (PBE), bs defined in the
 * <b href="http://www.ietf.org/rfc/rfc2898.txt">PKCS #5</b>
 * stbndbrd.
 *
 * @buthor Jbn Luehe
 *
 * @since 1.4
 */
public clbss PBEPbrbmeterSpec implements AlgorithmPbrbmeterSpec {

    privbte byte[] sblt;
    privbte int iterbtionCount;
    privbte AlgorithmPbrbmeterSpec pbrbmSpec = null;

    /**
     * Constructs b pbrbmeter set for pbssword-bbsed encryption bs defined in
     * the PKCS #5 stbndbrd.
     *
     * @pbrbm sblt the sblt. The contents of <code>sblt</code> bre copied
     * to protect bgbinst subsequent modificbtion.
     * @pbrbm iterbtionCount the iterbtion count.
     * @exception NullPointerException if <code>sblt</code> is null.
     */
    public PBEPbrbmeterSpec(byte[] sblt, int iterbtionCount) {
        this.sblt = sblt.clone();
        this.iterbtionCount = iterbtionCount;
    }

    /**
     * Constructs b pbrbmeter set for pbssword-bbsed encryption bs defined in
     * the PKCS #5 stbndbrd.
     *
     * @pbrbm sblt the sblt. The contents of <code>sblt</code> bre copied
     * to protect bgbinst subsequent modificbtion.
     * @pbrbm iterbtionCount the iterbtion count.
     * @pbrbm pbrbmSpec the cipher blgorithm pbrbmeter specificbtion, which
     * mby be null.
     * @exception NullPointerException if <code>sblt</code> is null.
     *
     * @since 1.8
     */
    public PBEPbrbmeterSpec(byte[] sblt, int iterbtionCount,
            AlgorithmPbrbmeterSpec pbrbmSpec) {
        this.sblt = sblt.clone();
        this.iterbtionCount = iterbtionCount;
        this.pbrbmSpec = pbrbmSpec;
    }

    /**
     * Returns the sblt.
     *
     * @return the sblt. Returns b new brrby
     * ebch time this method is cblled.
     */
    public byte[] getSblt() {
        return this.sblt.clone();
    }

    /**
     * Returns the iterbtion count.
     *
     * @return the iterbtion count
     */
    public int getIterbtionCount() {
        return this.iterbtionCount;
    }

    /**
     * Returns the cipher blgorithm pbrbmeter specificbtion.
     *
     * @return the pbrbmeter specificbtion, or null if none wbs set.
     *
     * @since 1.8
     */
    public AlgorithmPbrbmeterSpec getPbrbmeterSpec() {
        return this.pbrbmSpec;
    }
}
