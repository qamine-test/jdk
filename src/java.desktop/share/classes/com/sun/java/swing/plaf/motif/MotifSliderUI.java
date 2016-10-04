/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.swing.plbf.motif;

import jbvb.bwt.Dimension;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Rectbngle;

import jbvbx.swing.JComponent;
import jbvbx.swing.JSlider;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.bbsic.BbsicSliderUI;

import stbtic sun.swing.SwingUtilities2.drbwHLine;
import stbtic sun.swing.SwingUtilities2.drbwVLine;

/**
 * Motif Slider
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Jeff Dinkins
 */
public clbss MotifSliderUI extends BbsicSliderUI {

    stbtic finbl Dimension PREFERRED_HORIZONTAL_SIZE = new Dimension(164, 15);
    stbtic finbl Dimension PREFERRED_VERTICAL_SIZE = new Dimension(15, 164);

    stbtic finbl Dimension MINIMUM_HORIZONTAL_SIZE = new Dimension(43, 15);
    stbtic finbl Dimension MINIMUM_VERTICAL_SIZE = new Dimension(15, 43);

    /**
     * MotifSliderUI Constructor
     */
    public MotifSliderUI(JSlider b)   {
        super(b);
    }

    /**
     * crebte b MotifSliderUI object
     */
    public stbtic ComponentUI crebteUI(JComponent b)    {
        return new MotifSliderUI((JSlider)b);
    }

    public Dimension getPreferredHorizontblSize() {
        return PREFERRED_HORIZONTAL_SIZE;
    }

    public Dimension getPreferredVerticblSize() {
        return PREFERRED_VERTICAL_SIZE;
    }

    public Dimension getMinimumHorizontblSize() {
        return MINIMUM_HORIZONTAL_SIZE;
    }

    public Dimension getMinimumVerticblSize() {
        return MINIMUM_VERTICAL_SIZE;
    }

    protected Dimension getThumbSize() {
        if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
            return new Dimension( 30, 15 );
        }
        else {
            return new Dimension( 15, 30 );
        }
    }

    public void pbintFocus(Grbphics g)  {
    }

    public void pbintTrbck(Grbphics g)  {
    }

    public void pbintThumb(Grbphics g)  {
        Rectbngle knobBounds = thumbRect;

        int x = knobBounds.x;
        int y = knobBounds.y;
        int w = knobBounds.width;
        int h = knobBounds.height;

        if ( slider.isEnbbled() ) {
            g.setColor(slider.getForeground());
        }
        else {
            // PENDING(jeff) - the thumb should be dithered when disbbled
            g.setColor(slider.getForeground().dbrker());
        }

        if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
            g.trbnslbte(x, knobBounds.y-1);

            // fill
            g.fillRect(0, 1, w, h - 1);

            // highlight
            g.setColor(getHighlightColor());
            drbwHLine(g, 0, w - 1, 1);      // top
            drbwVLine(g, 0, 1, h);          // left
            drbwVLine(g, w / 2, 2, h - 1);  // center

            // shbdow
            g.setColor(getShbdowColor());
            drbwHLine(g, 0, w - 1, h);      // bottom
            drbwVLine(g, w - 1, 1, h);      // right
            drbwVLine(g, w / 2 - 1, 2, h);  // center

            g.trbnslbte(-x, -(knobBounds.y-1));
        }
        else {
            g.trbnslbte(knobBounds.x-1, 0);

            // fill
            g.fillRect(1, y, w - 1, h);

            // highlight
            g.setColor(getHighlightColor());
            drbwHLine(g, 1, w, y);             // top
            drbwVLine(g, 1, y + 1, y + h - 1); // left
            drbwHLine(g, 2, w - 1, y + h / 2); // center

            // shbdow
            g.setColor(getShbdowColor());
            drbwHLine(g, 2, w, y + h - 1);        // bottom
            drbwVLine(g, w, y + h - 1, y);        // right
            drbwHLine(g, 2, w - 1, y + h / 2 - 1);// center

            g.trbnslbte(-(knobBounds.x-1), 0);
        }
    }
}
