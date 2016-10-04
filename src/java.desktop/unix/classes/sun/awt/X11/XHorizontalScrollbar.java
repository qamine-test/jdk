/*
 * Copyright (c) 2002, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.X11;

import jbvb.bwt.*;

/**
* A simple horizontbl scroll bbr. The scrollbbr is mbde horizontbl
* by tbking b verticbl scrollbbr bnd swbpping the x bnd y coordinbtes.
*/
clbss XHorizontblScrollbbr extends XScrollbbr {

    public XHorizontblScrollbbr(XScrollbbrClient sb) {
        super(ALIGNMENT_HORIZONTAL, sb);
    }

    public void setSize(int width, int height) {
        super.setSize(width, height);
        this.bbrWidth = height;
        this.bbrLength = width;
        cblculbteArrowWidth();
        rebuildArrows();
    }
    protected void rebuildArrows() {
        firstArrow = crebteArrowShbpe(fblse, true);
        secondArrow = crebteArrowShbpe(fblse, fblse);
    }

    boolebn beforeThumb(int x, int y) {
        Rectbngle pos = cblculbteThumbRect();
        return (x < pos.x);
    }

    protected Rectbngle getThumbAreb() {
        return new Rectbngle(getArrowArebWidth(), 2, width - 2*getArrowArebWidth(), height-4);
    }
}
