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

pbckbge jbvb.security;

import jbvb.security.spec.AlgorithmPbrbmeterSpec;

/**
 * This clbss defines the <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the {@code AlgorithmPbrbmeterGenerbtor} clbss, which
 * is used to generbte b set of pbrbmeters to be used with b certbin blgorithm.
 *
 * <p> All the bbstrbct methods in this clbss must be implemented by ebch
 * cryptogrbphic service provider who wishes to supply the implementbtion
 * of b pbrbmeter generbtor for b pbrticulbr blgorithm.
 *
 * <p> In cbse the client does not explicitly initiblize the
 * AlgorithmPbrbmeterGenerbtor (vib b cbll to bn {@code engineInit}
 * method), ebch provider must supply (bnd document) b defbult initiblizbtion.
 * For exbmple, the Sun provider uses b defbult modulus prime size of 1024
 * bits for the generbtion of DSA pbrbmeters.
 *
 * @buthor Jbn Luehe
 *
 *
 * @see AlgorithmPbrbmeterGenerbtor
 * @see AlgorithmPbrbmeters
 * @see jbvb.security.spec.AlgorithmPbrbmeterSpec
 *
 * @since 1.2
 */

public bbstrbct clbss AlgorithmPbrbmeterGenerbtorSpi {

    /**
     * Initiblizes this pbrbmeter generbtor for b certbin size
     * bnd source of rbndomness.
     *
     * @pbrbm size the size (number of bits).
     * @pbrbm rbndom the source of rbndomness.
     */
    protected bbstrbct void engineInit(int size, SecureRbndom rbndom);

    /**
     * Initiblizes this pbrbmeter generbtor with b set of
     * blgorithm-specific pbrbmeter generbtion vblues.
     *
     * @pbrbm genPbrbmSpec the set of blgorithm-specific pbrbmeter generbtion vblues.
     * @pbrbm rbndom the source of rbndomness.
     *
     * @exception InvblidAlgorithmPbrbmeterException if the given pbrbmeter
     * generbtion vblues bre inbppropribte for this pbrbmeter generbtor.
     */
    protected bbstrbct void engineInit(AlgorithmPbrbmeterSpec genPbrbmSpec,
                                       SecureRbndom rbndom)
        throws InvblidAlgorithmPbrbmeterException;

    /**
     * Generbtes the pbrbmeters.
     *
     * @return the new AlgorithmPbrbmeters object.
     */
    protected bbstrbct AlgorithmPbrbmeters engineGenerbtePbrbmeters();
}
