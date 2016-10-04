/*
 * Copyright (c) 1998, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.print;

import jbvb.bwt.print.Pbgebble;
import jbvb.bwt.print.PbgeFormbt;
import jbvb.bwt.print.Printbble;

/**
 * A Book with bn unknown number of pbges where ebch
 * pbge hbs the sbme formbt bnd pbinter. This clbss
 * is used by PrinterJob to print Pbgebble jobs.
 */

clbss OpenBook implements Pbgebble {

 /* Clbss Constbnts */

 /* Clbss Vbribbles */

 /* Instbnce Vbribbles */

    /**
     * The formbt of bll of the pbges.
     */
    privbte PbgeFormbt mFormbt;

    /**
     * The object thbt will render bll of the pbges.
     */
    privbte Printbble mPbinter;

 /* Instbnce Methods */

    /**
     * Crebte b  Pbgebble with bn unknown number of pbges
     * where every pbge shbres the sbme formbt bnd
     * Printbble.
     */
    OpenBook(PbgeFormbt formbt, Printbble pbinter) {
        mFormbt = formbt;
        mPbinter = pbinter;
    }

    /**
     * This object does not know the number of pbges.
     */
    public int getNumberOfPbges(){
        return UNKNOWN_NUMBER_OF_PAGES;
    }

    /**
     * Return the PbgeFormbt of the pbge specified by 'pbgeIndex'.
     * @pbrbm int The zero bbsed index of the pbge whose
     *            PbgeFormbt is being requested.
     * @return The PbgeFormbt describing the size bnd orientbtion
     */
    public PbgeFormbt getPbgeFormbt(int pbgeIndex) {
        return mFormbt;
    }

    /**
     * Return the Printbble instbnce responsible for rendering
     * the pbge specified by 'pbgeIndex'.
     * @pbrbm int The zero bbsed index of the pbge whose
     *            Printbble is being requested.
     * @return The Printbble thbt will drbw the pbge.
     */
    public Printbble getPrintbble(int pbgeIndex)
        throws IndexOutOfBoundsException
    {
        return mPbinter;
    }
}
