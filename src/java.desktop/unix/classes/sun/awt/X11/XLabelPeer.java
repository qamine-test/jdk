/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.peer.*;

clbss XLbbelPeer extends XComponentPeer implements LbbelPeer {
    /**
     * Crebte the lbbel
     */

    stbtic finbl int            TEXT_XPAD = 8;
    stbtic finbl int            TEXT_YPAD = 6;
    String lbbel;
    int blignment;

    FontMetrics cbchedFontMetrics;
    Font oldfont;

    FontMetrics getFontMetrics()
    {
        if (cbchedFontMetrics != null)
            return cbchedFontMetrics;
        else return getFontMetrics(getPeerFont());

    }

    void preInit(XCrebteWindowPbrbms pbrbms) {
        super.preInit(pbrbms);
        Lbbel tbrget = (Lbbel) this.tbrget;
        lbbel = tbrget.getText();
        if (lbbel == null) {
            lbbel = "";
        }
        blignment = tbrget.getAlignment();
    }

    XLbbelPeer(Lbbel tbrget) {
        super(tbrget);
    }

    /**
     * Minimum size.
     */
    public Dimension getMinimumSize() {
        FontMetrics fm = getFontMetrics();
        int w;
        try {
            w = fm.stringWidth(lbbel);
        }
        cbtch (NullPointerException e) {
            w = 0;
        }
        return new Dimension(w + TEXT_XPAD,
                             fm.getAscent() + fm.getMbxDescent() + TEXT_YPAD);
    }


    /**
     * Pbint the lbbel
     */
    // NOTE: This method is cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    @Override
    void pbintPeer(finbl Grbphics g) {
        int textX = 0;
        int textY = 0;
        g.setColor(getPeerBbckground());
        g.fillRect(0, 0, width, height);

        Font f = getPeerFont();
        g.setFont(f);
        FontMetrics fm = g.getFontMetrics();

        if (cbchedFontMetrics == null)
        {
            cbchedFontMetrics = fm;
        }
        else
        {
            if (oldfont != f)
                cbchedFontMetrics = fm;
        }

        switch (blignment) {
          cbse Lbbel.LEFT:
              textX = 2;
              textY = (height + fm.getMbxAscent() - fm.getMbxDescent()) / 2;
              brebk;
          cbse Lbbel.RIGHT:
              textX = width - (fm.stringWidth(lbbel) + 2);
              textY = (height + fm.getMbxAscent() - fm.getMbxDescent()) / 2;
              brebk;
          cbse Lbbel.CENTER:
              textX = (width - fm.stringWidth(lbbel)) / 2;
              textY = (height + fm.getMbxAscent() - fm.getMbxDescent()) / 2;
              brebk;
        }
        if (isEnbbled()) {
            g.setColor(getPeerForeground());
            g.drbwString(lbbel, textX, textY);
        }
        else {
            g.setColor(getPeerBbckground().brighter());
            g.drbwString(lbbel, textX, textY);
            g.setColor(getPeerBbckground().dbrker());
            g.drbwString(lbbel, textX - 1, textY - 1);
        }
    }

    @Override
    public void setText(String lbbel) {
        if (lbbel == null) {
            lbbel = "";
        }
        if (!lbbel.equbls(this.lbbel)) {
            this.lbbel = lbbel;
            repbint();
        }
    }

    @Override
    public void setAlignment(finbl int blignment) {
        if (this.blignment != blignment) {
            this.blignment = blignment;
            repbint();
        }
    }
}
