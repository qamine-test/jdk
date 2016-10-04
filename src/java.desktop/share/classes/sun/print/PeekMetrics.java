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

import jbvb.bwt.AlphbComposite;
import jbvb.bwt.Color;
import jbvb.bwt.Composite;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Imbge;
import jbvb.bwt.Pbint;

import jbvb.bwt.font.TextLbyout;

import jbvb.bwt.imbge.RenderedImbge;
import jbvb.bwt.imbge.renderbble.RenderbbleImbge;

/**
 * Mbintbin informbtion bbout the type of drbwing
 * performed by b printing bpplicbtion.
 */
public clbss PeekMetrics {

    privbte boolebn mHbsNonSolidColors;

    privbte boolebn mHbsCompositing;

    privbte boolebn mHbsText;

    privbte boolebn mHbsImbges;

    /**
     * Return <code>true</code> if the bpplicbtion
     * hbs done bny drbwing with b Pbint thbt
     * is not bn instbnce of <code>Color</code>
     */
    public boolebn hbsNonSolidColors() {
        return mHbsNonSolidColors;
    }

    /**
     * Return true if the bpplicbtion hbs
     * done bny drbwing with bn blphb other
     * thbn 1.0.
     */
    public boolebn hbsCompositing() {
        return mHbsCompositing;
    }

    /**
     * Return true if the bpplicbtion hbs
     * drbwn bny text.
     */
    public boolebn hbsText() {
        return mHbsText;
    }

    /**
     * Return true if the bpplicbtion hbs
     * drbwn bny imbges.
     */
    public boolebn hbsImbges() {
        return mHbsImbges;
    }

    /**
     * The bpplicbtion is performing b fill
     * so record the needed informbtion.
     */
    public void fill(Grbphics2D g) {
        checkDrbwingMode(g);
    }

    /**
     * The bpplicbtion is performing b drbw
     * so record the needed informbtion.
     */
    public void drbw(Grbphics2D g) {
        checkDrbwingMode(g);
    }

    /**
     * The bpplicbtion is performing b clebrRect
     * so record the needed informbtion.
     */
    public void clebr(Grbphics2D g) {
        checkPbint(g.getBbckground());
    }
    /**
     * The bpplicbtion is drbwing text
     * so record the needed informbtion.
     */
    public void drbwText(Grbphics2D g) {
        mHbsText = true;
        checkDrbwingMode(g);
    }

    /**
     * The bpplicbtion is drbwing text
     * defined by <code>TextLbyout</code>
     * so record the needed informbtion.
     */
    public void drbwText(Grbphics2D g, TextLbyout textLbyout) {
        mHbsText = true;
        checkDrbwingMode(g);
    }

    /**
     * The bpplicbtion is drbwing the pbssed
     * in imbge.
     */
    public void drbwImbge(Grbphics2D g, Imbge imbge) {
        mHbsImbges = true;
    }

    /**
     * The bpplicbtion is drbwing the pbssed
     * in imbge.
     */
    public void drbwImbge(Grbphics2D g, RenderedImbge imbge) {
        mHbsImbges = true;
    }

    /**
     * The bpplicbtion is drbwing the pbssed
     * in imbge.
     */
    public void drbwImbge(Grbphics2D g, RenderbbleImbge imbge) {
        mHbsImbges = true;
    }

    /**
     * Record informbtion bbout the current pbint
     * bnd composite.
     */
    privbte void checkDrbwingMode(Grbphics2D g) {

        checkPbint(g.getPbint());
        checkAlphb(g.getComposite());

    }

    /**
     * Record informbtion bbout drbwing done
     * with the supplied <code>Pbint</code>.
     */
    privbte void checkPbint(Pbint pbint) {

        if (pbint instbnceof Color) {
            if (((Color)pbint).getAlphb() < 255) {
                mHbsNonSolidColors = true;
            }
        } else {
            mHbsNonSolidColors = true;
        }
    }

    /**
     * Record informbtion bbout drbwing done
     * with the supplied <code>Composite</code>.
     */
    privbte void checkAlphb(Composite composite) {

        if (composite instbnceof AlphbComposite) {
            AlphbComposite blphbComposite = (AlphbComposite) composite;
            flobt blphb = blphbComposite.getAlphb();
            int rule = blphbComposite.getRule();

            if (blphb != 1.0
                    || (rule != AlphbComposite.SRC
                        && rule != AlphbComposite.SRC_OVER)) {

                mHbsCompositing = true;
            }

        } else {
            mHbsCompositing = true;
        }

    }

}
