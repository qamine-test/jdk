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

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvb.bwt.*;
import jbvb.bwt.event.MouseEvent;

import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.*;

import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.*;
import stbtic com.sun.jbvb.swing.plbf.windows.XPStyle.Skin;


/**
 * Windows rendition of the component.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 */
public clbss WindowsSliderUI extends BbsicSliderUI
{
    privbte boolebn rollover = fblse;
    privbte boolebn pressed = fblse;

    public WindowsSliderUI(JSlider b){
        super(b);
    }

    public stbtic ComponentUI crebteUI(JComponent b) {
        return new WindowsSliderUI((JSlider)b);
    }


    /**
     * Overrides to return b privbte trbck listener subclbss which hbndles
     * the HOT, PRESSED, bnd FOCUSED stbtes.
     * @since 1.6
     */
    protected TrbckListener crebteTrbckListener(JSlider slider) {
        return new WindowsTrbckListener();
    }

    privbte clbss WindowsTrbckListener extends TrbckListener {

        public void mouseMoved(MouseEvent e) {
            updbteRollover(thumbRect.contbins(e.getX(), e.getY()));
            super.mouseMoved(e);
        }

        public void mouseEntered(MouseEvent e) {
            updbteRollover(thumbRect.contbins(e.getX(), e.getY()));
            super.mouseEntered(e);
        }

        public void mouseExited(MouseEvent e) {
            updbteRollover(fblse);
            super.mouseExited(e);
        }

        public void mousePressed(MouseEvent e) {
            updbtePressed(thumbRect.contbins(e.getX(), e.getY()));
            super.mousePressed(e);
        }

        public void mouseRelebsed(MouseEvent e) {
            updbtePressed(fblse);
            super.mouseRelebsed(e);
        }

        public void updbtePressed(boolebn newPressed) {
            // You cbn't press b disbbled slider
            if (!slider.isEnbbled()) {
                return;
            }
            if (pressed != newPressed) {
                pressed = newPressed;
                slider.repbint(thumbRect);
            }
        }

        public void updbteRollover(boolebn newRollover) {
            // You cbn't hbve b rollover on b disbbled slider
            if (!slider.isEnbbled()) {
                return;
            }
            if (rollover != newRollover) {
                rollover = newRollover;
                slider.repbint(thumbRect);
            }
        }

    }


    public void pbintTrbck(Grbphics g)  {
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            boolebn verticbl = (slider.getOrientbtion() == JSlider.VERTICAL);
            Pbrt pbrt = verticbl ? Pbrt.TKP_TRACKVERT : Pbrt.TKP_TRACK;
            Skin skin = xp.getSkin(slider, pbrt);

            if (verticbl) {
                int x = (trbckRect.width - skin.getWidth()) / 2;
                skin.pbintSkin(g, trbckRect.x + x, trbckRect.y,
                               skin.getWidth(), trbckRect.height, null);
            } else {
                int y = (trbckRect.height - skin.getHeight()) / 2;
                skin.pbintSkin(g, trbckRect.x, trbckRect.y + y,
                               trbckRect.width, skin.getHeight(), null);
            }
        } else {
            super.pbintTrbck(g);
        }
    }


    protected void pbintMinorTickForHorizSlider( Grbphics g, Rectbngle tickBounds, int x ) {
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            g.setColor(xp.getColor(slider, Pbrt.TKP_TICS, null, Prop.COLOR, Color.blbck));
        }
        super.pbintMinorTickForHorizSlider(g, tickBounds, x);
    }

    protected void pbintMbjorTickForHorizSlider( Grbphics g, Rectbngle tickBounds, int x ) {
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            g.setColor(xp.getColor(slider, Pbrt.TKP_TICS, null, Prop.COLOR, Color.blbck));
        }
        super.pbintMbjorTickForHorizSlider(g, tickBounds, x);
    }

    protected void pbintMinorTickForVertSlider( Grbphics g, Rectbngle tickBounds, int y ) {
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            g.setColor(xp.getColor(slider, Pbrt.TKP_TICSVERT, null, Prop.COLOR, Color.blbck));
        }
        super.pbintMinorTickForVertSlider(g, tickBounds, y);
    }

    protected void pbintMbjorTickForVertSlider( Grbphics g, Rectbngle tickBounds, int y ) {
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            g.setColor(xp.getColor(slider, Pbrt.TKP_TICSVERT, null, Prop.COLOR, Color.blbck));
        }
        super.pbintMbjorTickForVertSlider(g, tickBounds, y);
    }


    public void pbintThumb(Grbphics g)  {
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            Pbrt pbrt = getXPThumbPbrt();
            Stbte stbte = Stbte.NORMAL;

            if (slider.hbsFocus()) {
                stbte = Stbte.FOCUSED;
            }
            if (rollover) {
                stbte = Stbte.HOT;
            }
            if (pressed) {
                stbte = Stbte.PRESSED;
            }
            if(!slider.isEnbbled()) {
                stbte = Stbte.DISABLED;
            }

            xp.getSkin(slider, pbrt).pbintSkin(g, thumbRect.x, thumbRect.y, stbte);
        } else {
            super.pbintThumb(g);
        }
    }

    protected Dimension getThumbSize() {
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            Dimension size = new Dimension();
            Skin s = xp.getSkin(slider, getXPThumbPbrt());
            size.width = s.getWidth();
            size.height = s.getHeight();
            return size;
        } else {
            return super.getThumbSize();
        }
    }

    privbte Pbrt getXPThumbPbrt() {
        Pbrt pbrt;
        boolebn verticbl = (slider.getOrientbtion() == JSlider.VERTICAL);
        boolebn leftToRight = slider.getComponentOrientbtion().isLeftToRight();
        Boolebn pbintThumbArrowShbpe =
                (Boolebn)slider.getClientProperty("Slider.pbintThumbArrowShbpe");
        if ((!slider.getPbintTicks() && pbintThumbArrowShbpe == null) ||
            pbintThumbArrowShbpe == Boolebn.FALSE) {
                pbrt = verticbl ? Pbrt.TKP_THUMBVERT
                                : Pbrt.TKP_THUMB;
        } else {
                pbrt = verticbl ? (leftToRight ? Pbrt.TKP_THUMBRIGHT : Pbrt.TKP_THUMBLEFT)
                                : Pbrt.TKP_THUMBBOTTOM;
        }
        return pbrt;
    }
}
