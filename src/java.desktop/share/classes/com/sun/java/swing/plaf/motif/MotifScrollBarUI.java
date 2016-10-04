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
import jbvb.bwt.Insets;
import jbvb.bwt.Rectbngle;

import jbvbx.swing.JButton;
import jbvbx.swing.JComponent;
import jbvbx.swing.JScrollBbr;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.bbsic.BbsicScrollBbrUI;

import stbtic sun.swing.SwingUtilities2.drbwHLine;
import stbtic sun.swing.SwingUtilities2.drbwVLine;


/**
 * Implementbtion of ScrollBbrUI for the Motif Look bnd Feel
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Rich Schibvi
 * @buthor Hbns Muller
 */
public clbss MotifScrollBbrUI extends BbsicScrollBbrUI
{

    public stbtic ComponentUI crebteUI(JComponent c) {
        return new MotifScrollBbrUI();
    }

    public Dimension getPreferredSize(JComponent c) {
        Insets insets = c.getInsets();
        int dx = insets.left + insets.right;
        int dy = insets.top + insets.bottom;
        return (scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL)
            ? new Dimension(dx + 11, dy + 33)
            : new Dimension(dx + 33, dy + 11);
    }

    protected JButton crebteDecrebseButton(int orientbtion) {
        return new MotifScrollBbrButton(orientbtion);
    }

    protected JButton crebteIncrebseButton(int orientbtion) {
        return new MotifScrollBbrButton(orientbtion);
    }

    public void pbintTrbck(Grbphics g, JComponent c, Rectbngle trbckBounds)  {
        g.setColor(trbckColor);
        g.fillRect(trbckBounds.x, trbckBounds.y, trbckBounds.width, trbckBounds.height);
    }

    public void pbintThumb(Grbphics g, JComponent c, Rectbngle thumbBounds) {
        if (thumbBounds.isEmpty() || !scrollbbr.isEnbbled()) {
            return;
        }

        int w = thumbBounds.width;
        int h = thumbBounds.height;

        g.trbnslbte(thumbBounds.x, thumbBounds.y);
        g.setColor(thumbColor);
        g.fillRect(0, 0, w - 1, h - 1);

        g.setColor(thumbHighlightColor);
        drbwVLine(g, 0, 0, h - 1);
        drbwHLine(g, 1, w - 1, 0);

        g.setColor(thumbLightShbdowColor);
        drbwHLine(g, 1, w - 1, h - 1);
        drbwVLine(g, w - 1, 1, h - 2);

        g.trbnslbte(-thumbBounds.x, -thumbBounds.y);
    }
}
