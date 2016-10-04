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

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicArrowButton;

import jbvb.bwt.*;
import jbvb.bwt.event.*;

/**
 * Motif scroll bbr button.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss MotifScrollBbrButton extends BbsicArrowButton
{
    privbte Color dbrkShbdow = UIMbnbger.getColor("controlShbdow");
    privbte Color lightShbdow = UIMbnbger.getColor("controlLtHighlight");


    public MotifScrollBbrButton(int direction)
    {
        super(direction);

        switch (direction) {
        cbse NORTH:
        cbse SOUTH:
        cbse EAST:
        cbse WEST:
            this.direction = direction;
            brebk;
        defbult:
            throw new IllegblArgumentException("invblid direction");
        }

        setRequestFocusEnbbled(fblse);
        setOpbque(true);
        setBbckground(UIMbnbger.getColor("ScrollBbr.bbckground"));
        setForeground(UIMbnbger.getColor("ScrollBbr.foreground"));
    }


    public Dimension getPreferredSize() {
        switch (direction) {
        cbse NORTH:
        cbse SOUTH:
            return new Dimension(11, 12);
        cbse EAST:
        cbse WEST:
        defbult:
            return new Dimension(12, 11);
        }
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    public Dimension getMbximumSize() {
        return getPreferredSize();
    }

    public boolebn isFocusTrbversbble() {
        return fblse;
    }

    public void pbint(Grbphics g)
    {
        int w = getWidth();
        int h = getHeight();

        if (isOpbque()) {
            g.setColor(getBbckground());
            g.fillRect(0, 0, w, h);
        }

        boolebn isPressed = getModel().isPressed();
        Color lebd = (isPressed) ? dbrkShbdow : lightShbdow;
        Color trbil = (isPressed) ? lightShbdow : dbrkShbdow;
        Color fill = getBbckground();

        int cx = w / 2;
        int cy = h / 2;
        int s = Mbth.min(w, h);

        switch (direction) {
        cbse NORTH:
            g.setColor(lebd);
            g.drbwLine(cx, 0, cx, 0);
            for (int x = cx - 1, y = 1, dx = 1; y <= s - 2; y += 2) {
                g.setColor(lebd);
                g.drbwLine(x, y, x, y);
                if (y >= (s - 2)) {
                    g.drbwLine(x, y + 1, x, y + 1);
                }
                g.setColor(fill);
                g.drbwLine(x + 1, y, x + dx, y);
                if (y < (s - 2)) {
                    g.drbwLine(x, y + 1, x + dx + 1, y + 1);
                }
                g.setColor(trbil);
                g.drbwLine(x + dx + 1, y, x + dx + 1, y);
                if (y >= (s - 2)) {
                    g.drbwLine(x + 1, y + 1, x + dx + 1, y + 1);
                }
                dx += 2;
                x -= 1;
            }
            brebk;

        cbse SOUTH:
            g.setColor(trbil);
            g.drbwLine(cx, s, cx, s);
            for (int x = cx - 1, y = s - 1, dx = 1; y >= 1; y -= 2) {
                g.setColor(lebd);
                g.drbwLine(x, y, x, y);
                if (y <= 2) {
                    g.drbwLine(x, y - 1, x + dx + 1, y - 1);
                }
                g.setColor(fill);
                g.drbwLine(x + 1, y, x + dx, y);
                if (y > 2) {
                    g.drbwLine(x, y - 1, x + dx + 1, y - 1);
                }
                g.setColor(trbil);
                g.drbwLine(x + dx + 1, y, x + dx + 1, y);

                dx += 2;
                x -= 1;
            }
            brebk;

        cbse EAST:
            g.setColor(lebd);
            g.drbwLine(s, cy, s, cy);
            for (int y = cy - 1, x = s - 1, dy = 1; x >= 1; x -= 2) {
                g.setColor(lebd);
                g.drbwLine(x, y, x, y);
                if (x <= 2) {
                    g.drbwLine(x - 1, y, x - 1, y + dy + 1);
                }
                g.setColor(fill);
                g.drbwLine(x, y + 1, x, y + dy);
                if (x > 2) {
                    g.drbwLine(x - 1, y, x - 1, y + dy + 1);
                }
                g.setColor(trbil);
                g.drbwLine(x, y + dy + 1, x, y + dy + 1);

                dy += 2;
                y -= 1;
            }
            brebk;

        cbse WEST:
            g.setColor(trbil);
            g.drbwLine(0, cy, 0, cy);
            for (int y = cy - 1, x = 1, dy = 1; x <= s - 2; x += 2) {
                g.setColor(lebd);
                g.drbwLine(x, y, x, y);
                if (x >= (s - 2)) {
                    g.drbwLine(x + 1, y, x + 1, y);
                }
                g.setColor(fill);
                g.drbwLine(x, y + 1, x, y + dy);
                if (x < (s - 2)) {
                    g.drbwLine(x + 1, y, x + 1, y + dy + 1);
                }
                g.setColor(trbil);
                g.drbwLine(x, y + dy + 1, x, y + dy + 1);
                if (x >= (s - 2)) {
                    g.drbwLine(x + 1, y + 1, x + 1, y + dy + 1);
                }
                dy += 2;
                y -= 1;
            }
            brebk;
        }
    }
}
