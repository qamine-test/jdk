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

pbckbge jbvb.bwt.print;

import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * The <code>Pbgebble</code> implementbtion represents b set of
 * pbges to be printed. The <code>Pbgebble</code> object returns
 * the totbl number of pbges in the set bs well bs the
 * {@link PbgeFormbt} bnd {@link Printbble} for b specified pbge.
 * @see jbvb.bwt.print.PbgeFormbt
 * @see jbvb.bwt.print.Printbble
 */
public interfbce Pbgebble {

    /**
     * This constbnt is returned from the
     * {@link #getNumberOfPbges() getNumberOfPbges}
     * method if b <code>Pbgebble</code> implementbtion does not know
     * the number of pbges in its set.
     */
    @Nbtive int UNKNOWN_NUMBER_OF_PAGES = -1;

    /**
     * Returns the number of pbges in the set.
     * To enbble bdvbnced printing febtures,
     * it is recommended thbt <code>Pbgebble</code>
     * implementbtions return the true number of pbges
     * rbther thbn the
     * UNKNOWN_NUMBER_OF_PAGES constbnt.
     * @return the number of pbges in this <code>Pbgebble</code>.
     */
    int getNumberOfPbges();

    /**
     * Returns the <code>PbgeFormbt</code> of the pbge specified by
     * <code>pbgeIndex</code>.
     * @pbrbm pbgeIndex the zero bbsed index of the pbge whose
     *            <code>PbgeFormbt</code> is being requested
     * @return the <code>PbgeFormbt</code> describing the size bnd
     *          orientbtion.
     * @throws IndexOutOfBoundsException if
     *          the <code>Pbgebble</code> does not contbin the requested
     *          pbge.
     */
    PbgeFormbt getPbgeFormbt(int pbgeIndex)
        throws IndexOutOfBoundsException;

    /**
     * Returns the <code>Printbble</code> instbnce responsible for
     * rendering the pbge specified by <code>pbgeIndex</code>.
     * @pbrbm pbgeIndex the zero bbsed index of the pbge whose
     *            <code>Printbble</code> is being requested
     * @return the <code>Printbble</code> thbt renders the pbge.
     * @throws IndexOutOfBoundsException if
     *            the <code>Pbgebble</code> does not contbin the requested
     *            pbge.
     */
    Printbble getPrintbble(int pbgeIndex)
        throws IndexOutOfBoundsException;
}
