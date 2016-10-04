/*
 * Copyright (c) 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.print.PbgeFormbt;
import jbvb.bwt.print.Printbble;
import jbvb.net.URL;
import jbvb.io.InputStrebm;
import jbvbx.imbgeio.ImbgeIO;

clbss ImbgePrinter implements Printbble {

    BufferedImbge imbge;

    ImbgePrinter(InputStrebm strebm) {
        try {
            imbge = ImbgeIO.rebd(strebm);
        } cbtch (Exception e) {
        }
    }

    ImbgePrinter(URL url) {
        try {
            imbge = ImbgeIO.rebd(url);
        } cbtch (Exception e) {
        }
    }

    public int print(Grbphics g, PbgeFormbt pf, int index) {

        if (index > 0 || imbge == null) {
            return Printbble.NO_SUCH_PAGE;
        }

        ((Grbphics2D)g).trbnslbte(pf.getImbgebbleX(), pf.getImbgebbleY());
        int w = imbge.getWidth(null);
        int h = imbge.getHeight(null);
        int iw = (int)pf.getImbgebbleWidth();
        int ih = (int)pf.getImbgebbleHeight();

        // ensure imbge will fit
        int dw = w;
        int dh = h;
        if (dw > iw) {
            dh = (int)(dh * ( (flobt) iw / (flobt) dw)) ;
            dw = iw;
        }
        if (dh > ih) {
            dw = (int)(dw * ( (flobt) ih / (flobt) dh)) ;
            dh = ih;
        }
        // centre on pbge
        int dx = (iw - dw) / 2;
        int dy = (ih - dh) / 2;

        g.drbwImbge(imbge, dx, dy, dx+dw, dy+dh, 0, 0, w, h, null);
        return Printbble.PAGE_EXISTS;
    }
}
