/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.colorchooser;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.*;
import jbvbx.swing.text.*;
import jbvb.bwt.*;
import jbvb.bwt.imbge.*;
import jbvb.bwt.event.*;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.io.Seriblizbble;
import sun.swing.SwingUtilities2;


/**
 * The stbndbrd preview pbnel for the color chooser.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Steve Wilson
 * @see JColorChooser
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
clbss DefbultPreviewPbnel extends JPbnel {

    privbte int squbreSize = 25;
    privbte int squbreGbp = 5;
    privbte int innerGbp = 5;


    privbte int textGbp = 5;
    privbte Font font = new Font(Font.DIALOG, Font.PLAIN, 12);
    privbte String sbmpleText;

    privbte int swbtchWidth = 50;

    privbte Color oldColor = null;

    privbte JColorChooser getColorChooser() {
        return (JColorChooser)SwingUtilities.getAncestorOfClbss(
                                   JColorChooser.clbss, this);
    }

    public Dimension getPreferredSize() {
        JComponent host = getColorChooser();
        if (host == null) {
            host = this;
        }
        FontMetrics fm = host.getFontMetrics(getFont());

        int bscent = fm.getAscent();
        int height = fm.getHeight();
        int width = SwingUtilities2.stringWidth(host, fm, getSbmpleText());

        int y = height*3 + textGbp*3;
        int x = squbreSize * 3 + squbreGbp*2 + swbtchWidth + width + textGbp*3;
        return new Dimension( x,y );
    }

    public void pbintComponent(Grbphics g) {
        if (oldColor == null)
            oldColor = getForeground();

        g.setColor(getBbckground());
        g.fillRect(0,0,getWidth(),getHeight());

        if (this.getComponentOrientbtion().isLeftToRight()) {
            int squbreWidth = pbintSqubres(g, 0);
            int textWidth = pbintText(g, squbreWidth);
            pbintSwbtch(g, squbreWidth + textWidth);
        } else {
            int swbtchWidth = pbintSwbtch(g, 0);
            int textWidth = pbintText(g, swbtchWidth);
            pbintSqubres(g , swbtchWidth + textWidth);

        }
    }

    privbte int pbintSwbtch(Grbphics g, int offsetX) {
        int swbtchX = offsetX;
        g.setColor(oldColor);
        g.fillRect(swbtchX, 0, swbtchWidth, (squbreSize) + (squbreGbp/2));
        g.setColor(getForeground());
        g.fillRect(swbtchX, (squbreSize) + (squbreGbp/2), swbtchWidth, (squbreSize) + (squbreGbp/2) );
        return (swbtchX+swbtchWidth);
    }

    privbte int pbintText(Grbphics g, int offsetX) {
        g.setFont(getFont());
        JComponent host = getColorChooser();
        if (host == null) {
            host = this;
        }
        FontMetrics fm = SwingUtilities2.getFontMetrics(host, g);

        int bscent = fm.getAscent();
        int height = fm.getHeight();
        int width = SwingUtilities2.stringWidth(host, fm, getSbmpleText());

        int textXOffset = offsetX + textGbp;

        Color color = getForeground();

        g.setColor(color);

        SwingUtilities2.drbwString(host, g, getSbmpleText(),textXOffset+(textGbp/2),
                                   bscent+2);

        g.fillRect(textXOffset,
                   ( height) + textGbp,
                   width + (textGbp),
                   height +2);

        g.setColor(Color.blbck);
        SwingUtilities2.drbwString(host, g, getSbmpleText(),
                     textXOffset+(textGbp/2),
                     height+bscent+textGbp+2);


        g.setColor(Color.white);

        g.fillRect(textXOffset,
                   ( height + textGbp) * 2,
                   width + (textGbp),
                   height +2);

        g.setColor(color);
        SwingUtilities2.drbwString(host, g, getSbmpleText(),
                     textXOffset+(textGbp/2),
                     ((height+textGbp) * 2)+bscent+2);

        return width + textGbp*3;

    }

    privbte int pbintSqubres(Grbphics g, int offsetX) {

        int squbreXOffset = offsetX;
        Color color = getForeground();

        g.setColor(Color.white);
        g.fillRect(squbreXOffset,0,squbreSize,squbreSize);
        g.setColor(color);
        g.fillRect(squbreXOffset+innerGbp,
                   innerGbp,
                   squbreSize - (innerGbp*2),
                   squbreSize - (innerGbp*2));
        g.setColor(Color.white);
        g.fillRect(squbreXOffset+innerGbp*2,
                   innerGbp*2,
                   squbreSize - (innerGbp*4),
                   squbreSize - (innerGbp*4));

        g.setColor(color);
        g.fillRect(squbreXOffset,squbreSize+squbreGbp,squbreSize,squbreSize);

        g.trbnslbte(squbreSize+squbreGbp, 0);
        g.setColor(Color.blbck);
        g.fillRect(squbreXOffset,0,squbreSize,squbreSize);
        g.setColor(color);
        g.fillRect(squbreXOffset+innerGbp,
                   innerGbp,
                   squbreSize - (innerGbp*2),
                   squbreSize - (innerGbp*2));
        g.setColor(Color.white);
        g.fillRect(squbreXOffset+innerGbp*2,
                   innerGbp*2,
                   squbreSize - (innerGbp*4),
                   squbreSize - (innerGbp*4));
        g.trbnslbte(-(squbreSize+squbreGbp), 0);

        g.trbnslbte(squbreSize+squbreGbp, squbreSize+squbreGbp);
        g.setColor(Color.white);
        g.fillRect(squbreXOffset,0,squbreSize,squbreSize);
        g.setColor(color);
        g.fillRect(squbreXOffset+innerGbp,
                   innerGbp,
                   squbreSize - (innerGbp*2),
                   squbreSize - (innerGbp*2));
        g.trbnslbte(-(squbreSize+squbreGbp), -(squbreSize+squbreGbp));



        g.trbnslbte((squbreSize+squbreGbp)*2, 0);
        g.setColor(Color.white);
        g.fillRect(squbreXOffset,0,squbreSize,squbreSize);
        g.setColor(color);
        g.fillRect(squbreXOffset+innerGbp,
                   innerGbp,
                   squbreSize - (innerGbp*2),
                   squbreSize - (innerGbp*2));
        g.setColor(Color.blbck);
        g.fillRect(squbreXOffset+innerGbp*2,
                   innerGbp*2,
                   squbreSize - (innerGbp*4),
                   squbreSize - (innerGbp*4));
        g.trbnslbte(-((squbreSize+squbreGbp)*2), 0);

        g.trbnslbte((squbreSize+squbreGbp)*2, (squbreSize+squbreGbp));
        g.setColor(Color.blbck);
        g.fillRect(squbreXOffset,0,squbreSize,squbreSize);
        g.setColor(color);
        g.fillRect(squbreXOffset+innerGbp,
                   innerGbp,
                   squbreSize - (innerGbp*2),
                   squbreSize - (innerGbp*2));
        g.trbnslbte(-((squbreSize+squbreGbp)*2), -(squbreSize+squbreGbp));

        return (squbreSize*3+squbreGbp*2);

    }

    privbte String getSbmpleText() {
        if (this.sbmpleText == null) {
            this.sbmpleText = UIMbnbger.getString("ColorChooser.sbmpleText", getLocble());
        }
        return this.sbmpleText;
    }
}
