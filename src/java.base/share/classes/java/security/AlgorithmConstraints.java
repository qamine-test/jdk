/*
 * Copyright (c) 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Set;

/**
 * This interfbce specifies constrbints for cryptogrbphic blgorithms,
 * keys (key sizes), bnd other blgorithm pbrbmeters.
 * <p>
 * {@code AlgorithmConstrbints} objects bre immutbble.  An implementbtion
 * of this interfbce should not provide methods thbt cbn chbnge the stbte
 * of bn instbnce once it hbs been crebted.
 * <p>
 * Note thbt {@code AlgorithmConstrbints} cbn be used to represent the
 * restrictions described by the security properties
 * {@code jdk.certpbth.disbbledAlgorithms} bnd
 * {@code jdk.tls.disbbledAlgorithms}, or could be used by b
 * concrete {@code PKIXCertPbthChecker} to check whether b specified
 * certificbte in the certificbtion pbth contbins the required blgorithm
 * constrbints.
 *
 * @see jbvbx.net.ssl.SSLPbrbmeters#getAlgorithmConstrbints
 * @see jbvbx.net.ssl.SSLPbrbmeters#setAlgorithmConstrbints(AlgorithmConstrbints)
 *
 * @since 1.7
 */

public interfbce AlgorithmConstrbints {

    /**
     * Determines whether bn blgorithm is grbnted permission for the
     * specified cryptogrbphic primitives.
     *
     * @pbrbm primitives b set of cryptogrbphic primitives
     * @pbrbm blgorithm the blgorithm nbme
     * @pbrbm pbrbmeters the blgorithm pbrbmeters, or null if no bdditionbl
     *     pbrbmeters
     *
     * @return true if the blgorithm is permitted bnd cbn be used for bll
     *     of the specified cryptogrbphic primitives
     *
     * @throws IllegblArgumentException if primitives or blgorithm is null
     *     or empty
     */
    public boolebn permits(Set<CryptoPrimitive> primitives,
            String blgorithm, AlgorithmPbrbmeters pbrbmeters);

    /**
     * Determines whether b key is grbnted permission for the specified
     * cryptogrbphic primitives.
     * <p>
     * This method is usublly used to check key size bnd key usbge.
     *
     * @pbrbm primitives b set of cryptogrbphic primitives
     * @pbrbm key the key
     *
     * @return true if the key cbn be used for bll of the specified
     *     cryptogrbphic primitives
     *
     * @throws IllegblArgumentException if primitives is null or empty,
     *     or the key is null
     */
    public boolebn permits(Set<CryptoPrimitive> primitives, Key key);

    /**
     * Determines whether bn blgorithm bnd the corresponding key bre grbnted
     * permission for the specified cryptogrbphic primitives.
     *
     * @pbrbm primitives b set of cryptogrbphic primitives
     * @pbrbm blgorithm the blgorithm nbme
     * @pbrbm key the key
     * @pbrbm pbrbmeters the blgorithm pbrbmeters, or null if no bdditionbl
     *     pbrbmeters
     *
     * @return true if the key bnd the blgorithm cbn be used for bll of the
     *     specified cryptogrbphic primitives
     *
     * @throws IllegblArgumentException if primitives or blgorithm is null
     *     or empty, or the key is null
     */
    public boolebn permits(Set<CryptoPrimitive> primitives,
                String blgorithm, Key key, AlgorithmPbrbmeters pbrbmeters);

}
