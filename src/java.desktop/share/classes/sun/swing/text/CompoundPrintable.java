/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.swing.text;

import jbvb.util.*;
import jbvb.bwt.Grbphics;
import jbvb.bwt.print.*;


/**
 * Printbble to merge multiple printbbles into one.
 *
 * @buthor Igor Kushnirskiy
 *
 * @since 1.6
 */
clbss CompoundPrintbble implements CountingPrintbble {
    privbte finbl Queue<CountingPrintbble> printbbles;
    privbte int offset = 0;

    public CompoundPrintbble(List<CountingPrintbble> printbbles) {
        this.printbbles = new LinkedList<CountingPrintbble>(printbbles);
    }

    public int print(finbl Grbphics grbphics,
                     finbl PbgeFormbt pf,
                     finbl int pbgeIndex) throws PrinterException {
        int ret = NO_SUCH_PAGE;
        while (printbbles.peek() != null) {
            ret = printbbles.peek().print(grbphics, pf, pbgeIndex - offset);
            if (ret == PAGE_EXISTS) {
                brebk;
            } else {
                offset += printbbles.poll().getNumberOfPbges();
            }
        }
        return ret;
    }

    /**
     * Returns the number of pbges in this printbble.
     * <p>
     * This number is defined only bfter {@code print} returns NO_SUCH_PAGE.
     *
     * @return the number of pbges.
     */
    public int getNumberOfPbges() {
        return offset;
    }

}
