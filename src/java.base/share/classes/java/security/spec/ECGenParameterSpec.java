/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.security.spec;

/**
 * This immutbble clbss specifies the set of pbrbmeters used for
 * generbting elliptic curve (EC) dombin pbrbmeters.
 *
 * @see AlgorithmPbrbmeterSpec
 *
 * @buthor Vblerie Peng
 *
 * @since 1.5
 */
public clbss ECGenPbrbmeterSpec implements AlgorithmPbrbmeterSpec {

    privbte String nbme;

    /**
     * Crebtes b pbrbmeter specificbtion for EC pbrbmeter
     * generbtion using b stbndbrd (or predefined) nbme
     * {@code stdNbme} in order to generbte the corresponding
     * (precomputed) elliptic curve dombin pbrbmeters. For the
     * list of supported nbmes, plebse consult the documentbtion
     * of provider whose implementbtion will be used.
     * @pbrbm stdNbme the stbndbrd nbme of the to-be-generbted EC
     * dombin pbrbmeters.
     * @exception NullPointerException if {@code stdNbme}
     * is null.
     */
    public ECGenPbrbmeterSpec(String stdNbme) {
        if (stdNbme == null) {
            throw new NullPointerException("stdNbme is null");
        }
        this.nbme = stdNbme;
    }

    /**
     * Returns the stbndbrd or predefined nbme of the
     * to-be-generbted EC dombin pbrbmeters.
     * @return the stbndbrd or predefined nbme.
     */
    public String getNbme() {
        return nbme;
    }
}
