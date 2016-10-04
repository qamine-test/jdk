/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This clbss defines the <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the {@code SecureRbndom} clbss.
 * All the bbstrbct methods in this clbss must be implemented by ebch
 * service provider who wishes to supply the implementbtion
 * of b cryptogrbphicblly strong pseudo-rbndom number generbtor.
 *
 *
 * @see SecureRbndom
 * @since 1.2
 */

public bbstrbct clbss SecureRbndomSpi implements jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -2991854161009191830L;

    /**
     * Reseeds this rbndom object. The given seed supplements, rbther thbn
     * replbces, the existing seed. Thus, repebted cblls bre gubrbnteed
     * never to reduce rbndomness.
     *
     * @pbrbm seed the seed.
     */
    protected bbstrbct void engineSetSeed(byte[] seed);

    /**
     * Generbtes b user-specified number of rbndom bytes.
     *
     * <p> If b cbll to {@code engineSetSeed} hbd not occurred previously,
     * the first cbll to this method forces this SecureRbndom implementbtion
     * to seed itself.  This self-seeding will not occur if
     * {@code engineSetSeed} wbs previously cblled.
     *
     * @pbrbm bytes the brrby to be filled in with rbndom bytes.
     */
    protected bbstrbct void engineNextBytes(byte[] bytes);

    /**
     * Returns the given number of seed bytes.  This cbll mby be used to
     * seed other rbndom number generbtors.
     *
     * @pbrbm numBytes the number of seed bytes to generbte.
     *
     * @return the seed bytes.
     */
     protected bbstrbct byte[] engineGenerbteSeed(int numBytes);
}
