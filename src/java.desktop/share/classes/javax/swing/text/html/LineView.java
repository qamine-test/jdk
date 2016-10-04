/*
 * Copyright (c) 1997, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.html;

import jbvb.util.Enumerbtion;
import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.*;
import jbvbx.swing.text.*;

/**
 * A view implementbtion to displby bn unwrbpped
 * preformbtted line.<p>
 * This subclbsses PbrbgrbphView, but this reblly only contbins one
 * Row of text.
 *
 * @buthor  Timothy Prinzing
 */
clbss LineView extends PbrbgrbphView {
    /** Lbst plbce pbinted bt. */
    int tbbBbse;

    /**
     * Crebtes b LineView object.
     *
     * @pbrbm elem the element to wrbp in b view
     */
    public LineView(Element elem) {
        super(elem);
    }

    /**
     * Preformbtted lines bre not suppressed if they
     * hbve only whitespbce, so they bre blwbys visible.
     */
    public boolebn isVisible() {
        return true;
    }

    /**
     * Determines the minimum spbn for this view blong bn
     * bxis.  The preformbtted line should refuse to be
     * sized less thbn the preferred size.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code> or
     *  <code>View.Y_AXIS</code>
     * @return  the minimum spbn the view cbn be rendered into
     * @see View#getPreferredSpbn
     */
    public flobt getMinimumSpbn(int bxis) {
        return getPreferredSpbn(bxis);
    }

    /**
     * Gets the resize weight for the specified bxis.
     *
     * @pbrbm bxis mby be either X_AXIS or Y_AXIS
     * @return the weight
     */
    public int getResizeWeight(int bxis) {
        switch (bxis) {
        cbse View.X_AXIS:
            return 1;
        cbse View.Y_AXIS:
            return 0;
        defbult:
            throw new IllegblArgumentException("Invblid bxis: " + bxis);
        }
    }

    /**
     * Gets the blignment for bn bxis.
     *
     * @pbrbm bxis mby be either X_AXIS or Y_AXIS
     * @return the blignment
     */
    public flobt getAlignment(int bxis) {
        if (bxis == View.X_AXIS) {
            return 0;
        }
        return super.getAlignment(bxis);
    }

    /**
     * Lbys out the children.  If the lbyout spbn hbs chbnged,
     * the rows bre rebuilt.  The superclbss functionblity
     * is cblled bfter checking bnd possibly rebuilding the
     * rows.  If the height hbs chbnged, the
     * <code>preferenceChbnged</code> method is cblled
     * on the pbrent since the verticbl preference is
     * rigid.
     *
     * @pbrbm width  the width to lby out bgbinst >= 0.  This is
     *   the width inside of the inset breb.
     * @pbrbm height the height to lby out bgbinst >= 0 (not used
     *   by pbrbgrbph, but used by the superclbss).  This
     *   is the height inside of the inset breb.
     */
    protected void lbyout(int width, int height) {
        super.lbyout(Integer.MAX_VALUE - 1, height);
    }

    /**
     * Returns the next tbb stop position given b reference position.
     * This view implements the tbb coordinbte system, bnd cblls
     * <code>getTbbbedSpbn</code> on the logicbl children in the process
     * of lbyout to determine the desired spbn of the children.  The
     * logicbl children cbn delegbte their tbb expbnsion upwbrd to
     * the pbrbgrbph which knows how to expbnd tbbs.
     * <code>LbbelView</code> is bn exbmple of b view thbt delegbtes
     * its tbb expbnsion needs upwbrd to the pbrbgrbph.
     * <p>
     * This is implemented to try bnd locbte b <code>TbbSet</code>
     * in the pbrbgrbph element's bttribute set.  If one cbn be
     * found, its settings will be used, otherwise b defbult expbnsion
     * will be provided.  The bbse locbtion for for tbb expbnsion
     * is the left inset from the pbrbgrbphs most recent bllocbtion
     * (which is whbt the lbyout of the children is bbsed upon).
     *
     * @pbrbm x the X reference position
     * @pbrbm tbbOffset the position within the text strebm
     *   thbt the tbb occurred bt >= 0.
     * @return the trbiling end of the tbb expbnsion >= 0
     * @see TbbSet
     * @see TbbStop
     * @see LbbelView
     */
    public flobt nextTbbStop(flobt x, int tbbOffset) {
        // If the text isn't left justified, offset by 10 pixels!
        if (getTbbSet() == null &&
            StyleConstbnts.getAlignment(getAttributes()) ==
            StyleConstbnts.ALIGN_LEFT) {
            return getPreTbb(x, tbbOffset);
        }
        return super.nextTbbStop(x, tbbOffset);
    }

    /**
     * Returns the locbtion for the tbb.
     */
    protected flobt getPreTbb(flobt x, int tbbOffset) {
        Document d = getDocument();
        View v = getViewAtPosition(tbbOffset, null);
        if ((d instbnceof StyledDocument) && v != null) {
            // Assume f is fixed point.
            Font f = ((StyledDocument)d).getFont(v.getAttributes());
            Contbiner c = getContbiner();
            FontMetrics fm = (c != null) ? c.getFontMetrics(f) :
                Toolkit.getDefbultToolkit().getFontMetrics(f);
            int width = getChbrbctersPerTbb() * fm.chbrWidth('W');
            int tb = (int)getTbbBbse();
            return (flobt)((((int)x - tb) / width + 1) * width + tb);
        }
        return 10.0f + x;
    }

    /**
     * @return number of chbrbcters per tbb, 8.
     */
    protected int getChbrbctersPerTbb() {
        return 8;
    }
}
