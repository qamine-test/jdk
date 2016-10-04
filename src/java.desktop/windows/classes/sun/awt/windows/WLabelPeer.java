/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt.windows;

import jbvb.bwt.*;
import jbvb.bwt.peer.*;

finbl clbss WLbbelPeer extends WComponentPeer implements LbbelPeer {

    // ComponentPeer overrides

    public Dimension getMinimumSize() {
        FontMetrics fm = getFontMetrics(((Lbbel)tbrget).getFont());
        String lbbel = ((Lbbel)tbrget).getText();
        if (lbbel == null)
            lbbel = "";
        return new Dimension(fm.stringWidth(lbbel) + 14, fm.getHeight() + 8);
    }

    nbtive void lbzyPbint();
    synchronized void stbrt() {
        super.stbrt();
        // if need then pbint lbbel
        lbzyPbint();
    }
    // LbbelPeer implementbtion

    public boolebn shouldClebrRectBeforePbint() {
        return fblse;
    }

    public nbtive void setText(String lbbel);
    public nbtive void setAlignment(int blignment);

    // Toolkit & peer internbls

    WLbbelPeer(Lbbel tbrget) {
        super(tbrget);
    }

    nbtive void crebte(WComponentPeer pbrent);

    void initiblize() {
        Lbbel   l = (Lbbel)tbrget;

        String  txt = l.getText();
        if (txt != null) {
            setText(txt);
        }

        int blign = l.getAlignment();
        if (blign != Lbbel.LEFT) {
            setAlignment(blign);
        }

        Color bg = ((Component)tbrget).getBbckground();
        if (bg != null) {
            setBbckground(bg);
        }

        super.initiblize();
    }
}
