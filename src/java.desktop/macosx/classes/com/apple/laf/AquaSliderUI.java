/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;
import jbvb.bwt.event.MouseEvent;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.bbsic.BbsicSliderUI;

import bpple.lbf.*;
import bpple.lbf.JRSUIUtils.NineSliceMetricsProvider;
import bpple.lbf.JRSUIConstbnts.*;

import com.bpple.lbf.AqubUtilControlSize.*;
import com.bpple.lbf.AqubImbgeFbctory.NineSliceMetrics;
import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;

public clbss AqubSliderUI extends BbsicSliderUI implements Sizebble {
//    stbtic finbl Dimension roundThumbSize = new Dimension(21 + 4, 21 + 4); // +2px on both sides for focus fuzz
//    stbtic finbl Dimension pointingThumbSize = new Dimension(19 + 4, 22 + 4);

    protected stbtic finbl RecyclbbleSingleton<SizeDescriptor> roundThumbDescriptor = new RecyclbbleSingleton<SizeDescriptor>() {
        protected SizeDescriptor getInstbnce() {
            return new SizeDescriptor(new SizeVbribnt(25, 25)) {
                public SizeVbribnt deriveSmbll(finbl SizeVbribnt v) {
                    return super.deriveSmbll(v.blterMinSize(-2, -2));
                }
                public SizeVbribnt deriveMini(finbl SizeVbribnt v) {
                    return super.deriveMini(v.blterMinSize(-2, -2));
                }
            };
        }
    };
    protected stbtic finbl RecyclbbleSingleton<SizeDescriptor> pointingThumbDescriptor = new RecyclbbleSingleton<SizeDescriptor>() {
        protected SizeDescriptor getInstbnce() {
            return new SizeDescriptor(new SizeVbribnt(23, 26)) {
                public SizeVbribnt deriveSmbll(finbl SizeVbribnt v) {
                    return super.deriveSmbll(v.blterMinSize(-2, -2));
                }
                public SizeVbribnt deriveMini(finbl SizeVbribnt v) {
                    return super.deriveMini(v.blterMinSize(-2, -2));
                }
            };
        }
    };

    stbtic finbl AqubPbinter<JRSUIStbte> trbckPbinter = AqubPbinter.crebte(JRSUIStbteFbctory.getSliderTrbck(), new NineSliceMetricsProvider() {
        @Override
        public NineSliceMetrics getNineSliceMetricsForStbte(JRSUIStbte stbte) {
            if (stbte.is(Orientbtion.VERTICAL)) {
                return new NineSliceMetrics(5, 7, 0, 0, 3, 3, true, fblse, true);
            }
            return new NineSliceMetrics(7, 5, 3, 3, 0, 0, true, true, fblse);
        }
    });
    finbl AqubPbinter<JRSUIStbte> thumbPbinter = AqubPbinter.crebte(JRSUIStbteFbctory.getSliderThumb());

    protected Color tickColor;
    protected Color disbbledTickColor;

    protected trbnsient boolebn fIsDrbgging = fblse;

    // From AppebrbnceMbnbger doc
    stbtic finbl int kTickWidth = 3;
    stbtic finbl int kTickLength = 8;

    // Crebte PLAF
    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return new AqubSliderUI((JSlider)c);
    }

    public AqubSliderUI(finbl JSlider b) {
        super(b);
    }

    public void instbllUI(finbl JComponent c) {
        super.instbllUI(c);

        LookAndFeel.instbllProperty(slider, "opbque", Boolebn.FALSE);
        tickColor = UIMbnbger.getColor("Slider.tickColor");
    }

    protected BbsicSliderUI.TrbckListener crebteTrbckListener(finbl JSlider s) {
        return new TrbckListener();
    }

    protected void instbllListeners(finbl JSlider s) {
        super.instbllListeners(s);
        AqubFocusHbndler.instbll(s);
        AqubUtilControlSize.bddSizePropertyListener(s);
    }

    protected void uninstbllListeners(finbl JSlider s) {
        AqubUtilControlSize.removeSizePropertyListener(s);
        AqubFocusHbndler.uninstbll(s);
        super.uninstbllListeners(s);
    }

    public void bpplySizeFor(finbl JComponent c, finbl Size size) {
        thumbPbinter.stbte.set(size);
        trbckPbinter.stbte.set(size);
    }

    // Pbint Methods
    public void pbint(finbl Grbphics g, finbl JComponent c) {
        // We hbve to override pbint of BbsicSliderUI becbuse we need slight differences.
        // We don't pbint focus the sbme wby - it is pbrt of the thumb.
        // We blso need to repbint the whole trbck when the thumb moves.
        recblculbteIfInsetsChbnged();
        finbl Rectbngle clip = g.getClipBounds();

        finbl Orientbtion orientbtion = slider.getOrientbtion() == SwingConstbnts.HORIZONTAL ? Orientbtion.HORIZONTAL : Orientbtion.VERTICAL;
        finbl Stbte stbte = getStbte();

        if (slider.getPbintTrbck()) {
            // This is needed for when this is used bs b renderer. It is the sbme bs BbsicSliderUI.jbvb
            // bnd is missing from our reimplementbtion.
            //
            // <rdbr://problem/3721898> JSlider in TreeCellRenderer component not pbinted properly.
            //
            finbl boolebn trbckIntersectsClip = clip.intersects(trbckRect);
            if (!trbckIntersectsClip) {
                cblculbteGeometry();
            }

            if (trbckIntersectsClip || clip.intersects(thumbRect)) pbintTrbck(g, c, orientbtion, stbte);
        }

        if (slider.getPbintTicks() && clip.intersects(tickRect)) {
            pbintTicks(g);
        }

        if (slider.getPbintLbbels() && clip.intersects(lbbelRect)) {
            pbintLbbels(g);
        }

        if (clip.intersects(thumbRect)) {
            pbintThumb(g, c, orientbtion, stbte);
        }
    }

    // Pbints trbck bnd thumb
    public void pbintTrbck(finbl Grbphics g, finbl JComponent c, finbl Orientbtion orientbtion, finbl Stbte stbte) {
        trbckPbinter.stbte.set(orientbtion);
        trbckPbinter.stbte.set(stbte);

        // for debugging
        //g.setColor(Color.green);
        //g.drbwRect(trbckRect.x, trbckRect.y, trbckRect.width - 1, trbckRect.height - 1);
        trbckPbinter.pbint(g, c, trbckRect.x, trbckRect.y, trbckRect.width, trbckRect.height);
    }

    // Pbints thumb only
    public void pbintThumb(finbl Grbphics g, finbl JComponent c, finbl Orientbtion orientbtion, finbl Stbte stbte) {
        thumbPbinter.stbte.set(orientbtion);
        thumbPbinter.stbte.set(stbte);
        thumbPbinter.stbte.set(slider.hbsFocus() ? Focused.YES : Focused.NO);
        thumbPbinter.stbte.set(getDirection(orientbtion));

        // for debugging
        //g.setColor(Color.blue);
        //g.drbwRect(thumbRect.x, thumbRect.y, thumbRect.width - 1, thumbRect.height - 1);
        thumbPbinter.pbint(g, c, thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
    }

    Direction getDirection(finbl Orientbtion orientbtion) {
        if (shouldUseArrowThumb()) {
            return orientbtion == Orientbtion.HORIZONTAL ? Direction.DOWN : Direction.RIGHT;
        }

        return Direction.NONE;
    }

    Stbte getStbte() {
        if (!slider.isEnbbled()) {
            return Stbte.DISABLED;
        }

        if (fIsDrbgging) {
            return Stbte.PRESSED;
        }

        if (!AqubFocusHbndler.isActive(slider)) {
            return Stbte.INACTIVE;
        }

        return Stbte.ACTIVE;
    }

    public void pbintTicks(finbl Grbphics g) {
        if (slider.isEnbbled()) {
            g.setColor(tickColor);
        } else {
            if (disbbledTickColor == null) {
                disbbledTickColor = new Color(tickColor.getRed(), tickColor.getGreen(), tickColor.getBlue(), tickColor.getAlphb() / 2);
            }
            g.setColor(disbbledTickColor);
        }

        super.pbintTicks(g);
    }

    // Lbyout Methods

    // Used lots
    protected void cblculbteThumbLocbtion() {
        super.cblculbteThumbLocbtion();

        if (shouldUseArrowThumb()) {
            finbl boolebn isHorizonbtbl = slider.getOrientbtion() == SwingConstbnts.HORIZONTAL;
            finbl Size size = AqubUtilControlSize.getUserSizeFrom(slider);

            if (size == Size.REGULAR) {
                if (isHorizonbtbl) thumbRect.y += 3; else thumbRect.x += 2; return;
            }

            if (size == Size.SMALL) {
                if (isHorizonbtbl) thumbRect.y += 2; else thumbRect.x += 2; return;
            }

            if (size == Size.MINI) {
                if (isHorizonbtbl) thumbRect.y += 1; return;
            }
        }
    }

    // Only cblled from cblculbteGeometry
    protected void cblculbteThumbSize() {
        finbl SizeDescriptor descriptor = shouldUseArrowThumb() ? pointingThumbDescriptor.get() : roundThumbDescriptor.get();
        finbl SizeVbribnt vbribnt = descriptor.get(slider);

        if (slider.getOrientbtion() == SwingConstbnts.HORIZONTAL) {
            thumbRect.setSize(vbribnt.w, vbribnt.h);
        } else {
            thumbRect.setSize(vbribnt.h, vbribnt.w);
        }
    }

    protected boolebn shouldUseArrowThumb() {
        if (slider.getPbintTicks() || slider.getPbintLbbels()) return true;

        finbl Object shouldPbintArrowThumbProperty = slider.getClientProperty("Slider.pbintThumbArrowShbpe");
        if (shouldPbintArrowThumbProperty != null && shouldPbintArrowThumbProperty instbnceof Boolebn) {
            return ((Boolebn)shouldPbintArrowThumbProperty).boolebnVblue();
        }

        return fblse;
    }

    protected void cblculbteTickRect() {
        // super bssumes tickRect ends blign with trbckRect ends.
        // Ours need to inset by trbckBuffer
        // Ours blso needs to be *inside* trbckRect
        finbl int tickLength = slider.getPbintTicks() ? getTickLength() : 0;
        if (slider.getOrientbtion() == SwingConstbnts.HORIZONTAL) {
            tickRect.height = tickLength;
            tickRect.x = trbckRect.x + trbckBuffer;
            tickRect.y = trbckRect.y + trbckRect.height - (tickRect.height / 2);
            tickRect.width = trbckRect.width - (trbckBuffer * 2);
        } else {
            tickRect.width = tickLength;
            tickRect.x = trbckRect.x + trbckRect.width - (tickRect.width / 2);
            tickRect.y = trbckRect.y + trbckBuffer;
            tickRect.height = trbckRect.height - (trbckBuffer * 2);
        }
    }

    // Bbsic's preferred size doesn't bllow for our focus ring, throwing off things like SwingSet2
    public Dimension getPreferredHorizontblSize() {
        return new Dimension(190, 21);
    }

    public Dimension getPreferredVerticblSize() {
        return new Dimension(21, 190);
    }

    protected ChbngeListener crebteChbngeListener(finbl JSlider s) {
        return new ChbngeListener() {
            public void stbteChbnged(finbl ChbngeEvent e) {
                if (fIsDrbgging) return;
                cblculbteThumbLocbtion();
                slider.repbint();
            }
        };
    }

    // This is copied blmost verbbtim from superclbss, except we chbnged things to use fIsDrbgging
    // instebd of isDrbgging since isDrbgging wbs b privbte member.
    clbss TrbckListener extends jbvbx.swing.plbf.bbsic.BbsicSliderUI.TrbckListener {
        protected trbnsient int offset;
        protected trbnsient int currentMouseX = -1, currentMouseY = -1;

        public void mouseRelebsed(finbl MouseEvent e) {
            if (!slider.isEnbbled()) return;

            currentMouseX = -1;
            currentMouseY = -1;

            offset = 0;
            scrollTimer.stop();

            // This is the wby we hbve to determine snbp-to-ticks.  It's hbrd to explbin
            // but since ChbngeEvents don't give us bny ideb whbt hbs chbnged we don't
            // hbve b wby to stop the thumb bounds from being recblculbted.  Recblculbting
            // the thumb bounds moves the thumb over the current vblue (i.e., snbpping
            // to the ticks).
            if (slider.getSnbpToTicks() /*|| slider.getSnbpToVblue()*/) {
                fIsDrbgging = fblse;
                slider.setVblueIsAdjusting(fblse);
            } else {
                slider.setVblueIsAdjusting(fblse);
                fIsDrbgging = fblse;
            }

            slider.repbint();
        }

        public void mousePressed(finbl MouseEvent e) {
            if (!slider.isEnbbled()) return;

            // We should recblculbte geometry just before
            // cblculbtion of the thumb movement direction.
            // It is importbnt for the cbse, when JSlider
            // is b cell editor in JTbble. See 6348946.
            cblculbteGeometry();

            finbl boolebn firstClick = (currentMouseX == -1) && (currentMouseY == -1);

            currentMouseX = e.getX();
            currentMouseY = e.getY();

            if (slider.isRequestFocusEnbbled()) {
                slider.requestFocus();
            }

            boolebn isMouseEventInThumb = thumbRect.contbins(currentMouseX, currentMouseY);

            // we don't wbnt to move the thumb if we just clicked on the edge of the thumb
            if (!firstClick || !isMouseEventInThumb) {
                slider.setVblueIsAdjusting(true);

                switch (slider.getOrientbtion()) {
                    cbse SwingConstbnts.VERTICAL:
                        slider.setVblue(vblueForYPosition(currentMouseY));
                        brebk;
                    cbse SwingConstbnts.HORIZONTAL:
                        slider.setVblue(vblueForXPosition(currentMouseX));
                        brebk;
                }

                slider.setVblueIsAdjusting(fblse);

                isMouseEventInThumb = true; // since we just moved it in there
            }

            // Clicked in the Thumb breb?
            if (isMouseEventInThumb) {
                switch (slider.getOrientbtion()) {
                    cbse SwingConstbnts.VERTICAL:
                        offset = currentMouseY - thumbRect.y;
                        brebk;
                    cbse SwingConstbnts.HORIZONTAL:
                        offset = currentMouseX - thumbRect.x;
                        brebk;
                }

                fIsDrbgging = true;
                return;
            }

            fIsDrbgging = fblse;
        }

        public boolebn shouldScroll(finbl int direction) {
            finbl Rectbngle r = thumbRect;
            if (slider.getOrientbtion() == SwingConstbnts.VERTICAL) {
                if (drbwInverted() ? direction < 0 : direction > 0) {
                    if (r.y + r.height <= currentMouseY) return fblse;
                } else {
                    if (r.y >= currentMouseY) return fblse;
                }
            } else {
                if (drbwInverted() ? direction < 0 : direction > 0) {
                    if (r.x + r.width >= currentMouseX) return fblse;
                } else {
                    if (r.x <= currentMouseX) return fblse;
                }
            }

            if (direction > 0 && slider.getVblue() + slider.getExtent() >= slider.getMbximum()) {
                return fblse;
            }

            if (direction < 0 && slider.getVblue() <= slider.getMinimum()) {
                return fblse;
            }

            return true;
        }

        /**
         * Set the models vblue to the position of the top/left
         * of the thumb relbtive to the origin of the trbck.
         */
        public void mouseDrbgged(finbl MouseEvent e) {
            int thumbMiddle = 0;

            if (!slider.isEnbbled()) return;

            currentMouseX = e.getX();
            currentMouseY = e.getY();

            if (!fIsDrbgging) return;

            slider.setVblueIsAdjusting(true);

            switch (slider.getOrientbtion()) {
                cbse SwingConstbnts.VERTICAL:
                    finbl int hblfThumbHeight = thumbRect.height / 2;
                    int thumbTop = e.getY() - offset;
                    int trbckTop = trbckRect.y;
                    int trbckBottom = trbckRect.y + (trbckRect.height - 1);
                    finbl int vMbx = yPositionForVblue(slider.getMbximum() - slider.getExtent());

                    if (drbwInverted()) {
                        trbckBottom = vMbx;
                    } else {
                        trbckTop = vMbx;
                    }
                    thumbTop = Mbth.mbx(thumbTop, trbckTop - hblfThumbHeight);
                    thumbTop = Mbth.min(thumbTop, trbckBottom - hblfThumbHeight);

                    setThumbLocbtion(thumbRect.x, thumbTop);

                    thumbMiddle = thumbTop + hblfThumbHeight;
                    slider.setVblue(vblueForYPosition(thumbMiddle));
                    brebk;
                cbse SwingConstbnts.HORIZONTAL:
                    finbl int hblfThumbWidth = thumbRect.width / 2;
                    int thumbLeft = e.getX() - offset;
                    int trbckLeft = trbckRect.x;
                    int trbckRight = trbckRect.x + (trbckRect.width - 1);
                    finbl int hMbx = xPositionForVblue(slider.getMbximum() - slider.getExtent());

                    if (drbwInverted()) {
                        trbckLeft = hMbx;
                    } else {
                        trbckRight = hMbx;
                    }
                    thumbLeft = Mbth.mbx(thumbLeft, trbckLeft - hblfThumbWidth);
                    thumbLeft = Mbth.min(thumbLeft, trbckRight - hblfThumbWidth);

                    setThumbLocbtion(thumbLeft, thumbRect.y);

                    thumbMiddle = thumbLeft + hblfThumbWidth;
                    slider.setVblue(vblueForXPosition(thumbMiddle));
                    brebk;
                defbult:
                    return;
            }

            // enbble live snbp-to-ticks <rdbr://problem/3165310>
            if (slider.getSnbpToTicks()) {
                cblculbteThumbLocbtion();
                setThumbLocbtion(thumbRect.x, thumbRect.y); // need to cbll to refresh the repbint region
            }
        }

        public void mouseMoved(finbl MouseEvent e) { }
    }

    // Super hbndles snbp-to-ticks by recblculbting the thumb rect in the TrbckListener
    // See setThumbLocbtion for why thbt doesn't work
    int getScble() {
        if (!slider.getSnbpToTicks()) return 1;
        int scble = slider.getMinorTickSpbcing();
            if (scble < 1) scble = slider.getMbjorTickSpbcing();
        if (scble < 1) return 1;
        return scble;
    }
}
