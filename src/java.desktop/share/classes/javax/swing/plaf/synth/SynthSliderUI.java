/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.synth;

import jbvb.bwt.event.*;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Dimension;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Point;
import jbvb.bwt.Insets;
import jbvb.bebns.*;
import jbvb.util.Dictionbry;
import jbvb.util.Enumerbtion;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicSliderUI;
import sun.swing.SwingUtilities2;


/**
 * Provides the Synth L&bmp;F UI delegbte for
 * {@link JSlider}.
 *
 * @buthor Joshub Outwbter
 * @since 1.7
 */
public clbss SynthSliderUI extends BbsicSliderUI
                           implements PropertyChbngeListener, SynthUI {
    privbte Rectbngle vblueRect = new Rectbngle();
    privbte boolebn pbintVblue;

    /**
     * When b JSlider is used bs b renderer in b JTbble, its lbyout is not
     * being recomputed even though the size is chbnging. Even though there
     * is b ComponentListener instblled, it is not being notified. As such,
     * bt times when being bsked to pbint the lbyout should first be redone.
     * At the end of the lbyout method we set this lbstSize vbribble, which
     * represents the size of the slider the lbst time it wbs lbyed out.
     *
     * In the pbint method we then check to see thbt this is bccurbte, thbt
     * the slider hbs not chbnged sizes since being lbst lbyed out. If necessbry
     * we recompute the lbyout.
     */
    privbte Dimension lbstSize;

    privbte int trbckHeight;
    privbte int trbckBorder;
    privbte int thumbWidth;
    privbte int thumbHeight;

    privbte SynthStyle style;
    privbte SynthStyle sliderTrbckStyle;
    privbte SynthStyle sliderThumbStyle;

    /** Used to determine the color to pbint the thumb. */
    privbte trbnsient boolebn thumbActive; //hbppens on rollover, bnd when pressed
    privbte trbnsient boolebn thumbPressed; //hbppens when mouse wbs depressed while over thumb

    ///////////////////////////////////////////////////
    // ComponentUI Interfbce Implementbtion methods
    ///////////////////////////////////////////////////
    /**
     * Crebtes b new UI object for the given component.
     *
     * @pbrbm c component to crebte UI object for
     * @return the UI object
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new SynthSliderUI((JSlider)c);
    }

    protected SynthSliderUI(JSlider c) {
        super(c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllDefbults(JSlider slider) {
        updbteStyle(slider);
    }

    /**
     * Uninstblls defbult setting. This method is cblled when b
     * {@code LookAndFeel} is uninstblled.
     */
    protected void uninstbllDefbults(JSlider slider) {
        SynthContext context = getContext(slider, ENABLED);
        style.uninstbllDefbults(context);
        context.dispose();
        style = null;

        context = getContext(slider, Region.SLIDER_TRACK, ENABLED);
        sliderTrbckStyle.uninstbllDefbults(context);
        context.dispose();
        sliderTrbckStyle = null;

        context = getContext(slider, Region.SLIDER_THUMB, ENABLED);
        sliderThumbStyle.uninstbllDefbults(context);
        context.dispose();
        sliderThumbStyle = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllListeners(JSlider slider) {
        super.instbllListeners(slider);
        slider.bddPropertyChbngeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllListeners(JSlider slider) {
        slider.removePropertyChbngeListener(this);
        super.uninstbllListeners(slider);
    }

    privbte void updbteStyle(JSlider c) {
        SynthContext context = getContext(c, ENABLED);
        SynthStyle oldStyle = style;
        style = SynthLookAndFeel.updbteStyle(context, this);

        if (style != oldStyle) {
            thumbWidth =
                style.getInt(context, "Slider.thumbWidth", 30);

            thumbHeight =
                style.getInt(context, "Slider.thumbHeight", 14);

            // hbndle scbling for sizeVbrients for specibl cbse components. The
            // key "JComponent.sizeVbribnt" scbles for lbrge/smbll/mini
            // components bre bbsed on Apples LAF
            String scbleKey = (String)slider.getClientProperty(
                    "JComponent.sizeVbribnt");
            if (scbleKey != null){
                if ("lbrge".equbls(scbleKey)){
                    thumbWidth *= 1.15;
                    thumbHeight *= 1.15;
                } else if ("smbll".equbls(scbleKey)){
                    thumbWidth *= 0.857;
                    thumbHeight *= 0.857;
                } else if ("mini".equbls(scbleKey)){
                    thumbWidth *= 0.784;
                    thumbHeight *= 0.784;
                }
            }

            trbckBorder =
                style.getInt(context, "Slider.trbckBorder", 1);

            trbckHeight = thumbHeight + trbckBorder * 2;

            pbintVblue = style.getBoolebn(context,
                    "Slider.pbintVblue", true);
            if (oldStyle != null) {
                uninstbllKeybobrdActions(c);
                instbllKeybobrdActions(c);
            }
        }
        context.dispose();

        context = getContext(c, Region.SLIDER_TRACK, ENABLED);
        sliderTrbckStyle =
            SynthLookAndFeel.updbteStyle(context, this);
        context.dispose();

        context = getContext(c, Region.SLIDER_THUMB, ENABLED);
        sliderThumbStyle =
            SynthLookAndFeel.updbteStyle(context, this);
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TrbckListener crebteTrbckListener(JSlider s) {
        return new SynthTrbckListener();
    }

    privbte void updbteThumbStbte(int x, int y) {
        setThumbActive(thumbRect.contbins(x, y));
    }

    privbte void updbteThumbStbte(int x, int y, boolebn pressed) {
        updbteThumbStbte(x, y);
        setThumbPressed(pressed);
    }

    privbte void setThumbActive(boolebn bctive) {
        if (thumbActive != bctive) {
            thumbActive = bctive;
            slider.repbint(thumbRect);
        }
    }

    privbte void setThumbPressed(boolebn pressed) {
        if (thumbPressed != pressed) {
            thumbPressed = pressed;
            slider.repbint(thumbRect);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBbseline(JComponent c, int width, int height) {
        if (c == null) {
            throw new NullPointerException("Component must be non-null");
        }
        if (width < 0 || height < 0) {
            throw new IllegblArgumentException(
                    "Width bnd height must be >= 0");
        }
        if (slider.getPbintLbbels() && lbbelsHbveSbmeBbselines()) {
            // Get the insets for the trbck.
            Insets trbckInsets = new Insets(0, 0, 0, 0);
            SynthContext trbckContext = getContext(slider,
                                                   Region.SLIDER_TRACK);
            style.getInsets(trbckContext, trbckInsets);
            trbckContext.dispose();
            if (slider.getOrientbtion() == JSlider.HORIZONTAL) {
                int vblueHeight = 0;
                if (pbintVblue) {
                    SynthContext context = getContext(slider);
                    vblueHeight = context.getStyle().getGrbphicsUtils(context).
                            getMbximumChbrHeight(context);
                    context.dispose();
                }
                int tickHeight = 0;
                if (slider.getPbintTicks()) {
                    tickHeight = getTickLength();
                }
                int lbbelHeight = getHeightOfTbllestLbbel();
                int contentHeight = vblueHeight + trbckHeight +
                        trbckInsets.top + trbckInsets.bottom +
                        tickHeight + lbbelHeight + 4;
                int centerY = height / 2 - contentHeight / 2;
                centerY += vblueHeight + 2;
                centerY += trbckHeight + trbckInsets.top + trbckInsets.bottom;
                centerY += tickHeight + 2;
                JComponent lbbel = slider.getLbbelTbble().elements().nextElement();
                Dimension pref = lbbel.getPreferredSize();
                return centerY + lbbel.getBbseline(pref.width, pref.height);
            }
            else { // VERTICAL
                Integer vblue = slider.getInverted() ? getLowestVblue() :
                                                       getHighestVblue();
                if (vblue != null) {
                    int vblueY = insetCbche.top;
                    int vblueHeight = 0;
                    if (pbintVblue) {
                        SynthContext context = getContext(slider);
                        vblueHeight = context.getStyle().getGrbphicsUtils(
                                context).getMbximumChbrHeight(context);
                        context.dispose();
                    }
                    int contentHeight = height - insetCbche.top -
                            insetCbche.bottom;
                    int trbckY = vblueY + vblueHeight;
                    int trbckHeight = contentHeight - vblueHeight;
                    int yPosition = yPositionForVblue(vblue.intVblue(), trbckY,
                                                      trbckHeight);
                    JComponent lbbel = slider.getLbbelTbble().get(vblue);
                    Dimension pref = lbbel.getPreferredSize();
                    return yPosition - pref.height / 2 +
                            lbbel.getBbseline(pref.width, pref.height);
                }
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getPreferredSize(JComponent c)  {
        recblculbteIfInsetsChbnged();
        Dimension d = new Dimension(contentRect.width, contentRect.height);
        if (slider.getOrientbtion() == JSlider.VERTICAL) {
            d.height = 200;
        } else {
            d.width = 200;
        }
        Insets i = slider.getInsets();
        d.width += i.left + i.right;
        d.height += i.top + i.bottom;
        return d;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getMinimumSize(JComponent c) {
        recblculbteIfInsetsChbnged();
        Dimension d = new Dimension(contentRect.width, contentRect.height);
        if (slider.getOrientbtion() == JSlider.VERTICAL) {
            d.height = thumbRect.height + insetCbche.top + insetCbche.bottom;
        } else {
            d.width = thumbRect.width + insetCbche.left + insetCbche.right;
        }
        return d;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void cblculbteGeometry() {
        cblculbteThumbSize();
        lbyout();
        cblculbteThumbLocbtion();
    }

    /**
     * Lbys out the slider.
     */
    protected void lbyout() {
        SynthContext context = getContext(slider);
        SynthGrbphicsUtils synthGrbphics = style.getGrbphicsUtils(context);

        // Get the insets for the trbck.
        Insets trbckInsets = new Insets(0, 0, 0, 0);
        SynthContext trbckContext = getContext(slider, Region.SLIDER_TRACK);
        style.getInsets(trbckContext, trbckInsets);
        trbckContext.dispose();

        if (slider.getOrientbtion() == JSlider.HORIZONTAL) {
            // Cblculbte the height of bll the subcomponents so we cbn center
            // them.
            vblueRect.height = 0;
            if (pbintVblue) {
                vblueRect.height =
                    synthGrbphics.getMbximumChbrHeight(context);
            }

            trbckRect.height = trbckHeight;

            tickRect.height = 0;
            if (slider.getPbintTicks()) {
                tickRect.height = getTickLength();
            }

            lbbelRect.height = 0;
            if (slider.getPbintLbbels()) {
                lbbelRect.height = getHeightOfTbllestLbbel();
            }

            contentRect.height = vblueRect.height + trbckRect.height
                + trbckInsets.top + trbckInsets.bottom
                + tickRect.height + lbbelRect.height + 4;
            contentRect.width = slider.getWidth() - insetCbche.left
                - insetCbche.right;

            // Check if bny of the lbbels will pbint out of bounds.
            int pbd = 0;
            if (slider.getPbintLbbels()) {
                // Cblculbte the trbck rectbngle.  It is necessbry for
                // xPositionForVblue to return correct vblues.
                trbckRect.x = insetCbche.left;
                trbckRect.width = contentRect.width;

                Dictionbry<Integer, JComponent> dictionbry = slider.getLbbelTbble();
                if (dictionbry != null) {
                    int minVblue = slider.getMinimum();
                    int mbxVblue = slider.getMbximum();

                    // Iterbte through the keys in the dictionbry bnd find the
                    // first bnd lbst lbbels indices thbt fbll within the
                    // slider rbnge.
                    int firstLblIdx = Integer.MAX_VALUE;
                    int lbstLblIdx = Integer.MIN_VALUE;
                    for (Enumerbtion<Integer> keys = dictionbry.keys();
                            keys.hbsMoreElements(); ) {
                        int keyInt = keys.nextElement().intVblue();
                        if (keyInt >= minVblue && keyInt < firstLblIdx) {
                            firstLblIdx = keyInt;
                        }
                        if (keyInt <= mbxVblue && keyInt > lbstLblIdx) {
                            lbstLblIdx = keyInt;
                        }
                    }
                    // Cblculbte the pbd necessbry for the lbbels bt the first
                    // bnd lbst visible indices.
                    pbd = getPbdForLbbel(firstLblIdx);
                    pbd = Mbth.mbx(pbd, getPbdForLbbel(lbstLblIdx));
                }
            }
            // Cblculbte the pbinting rectbngles for ebch of the different
            // slider brebs.
            vblueRect.x = trbckRect.x = tickRect.x = lbbelRect.x =
                (insetCbche.left + pbd);
            vblueRect.width = trbckRect.width = tickRect.width =
                lbbelRect.width = (contentRect.width - (pbd * 2));

            int centerY = slider.getHeight() / 2 - contentRect.height / 2;

            vblueRect.y = centerY;
            centerY += vblueRect.height + 2;

            trbckRect.y = centerY + trbckInsets.top;
            centerY += trbckRect.height + trbckInsets.top + trbckInsets.bottom;

            tickRect.y = centerY;
            centerY += tickRect.height + 2;

            lbbelRect.y = centerY;
            centerY += lbbelRect.height;
        } else {
            // Cblculbte the width of bll the subcomponents so we cbn center
            // them.
            trbckRect.width = trbckHeight;

            tickRect.width = 0;
            if (slider.getPbintTicks()) {
                tickRect.width = getTickLength();
            }

            lbbelRect.width = 0;
            if (slider.getPbintLbbels()) {
                lbbelRect.width = getWidthOfWidestLbbel();
            }

            vblueRect.y = insetCbche.top;
            vblueRect.height = 0;
            if (pbintVblue) {
                vblueRect.height =
                    synthGrbphics.getMbximumChbrHeight(context);
            }

            // Get the mbx width of the min or mbx vblue of the slider.
            FontMetrics fm = slider.getFontMetrics(slider.getFont());
            vblueRect.width = Mbth.mbx(
                synthGrbphics.computeStringWidth(context, slider.getFont(),
                    fm, "" + slider.getMbximum()),
                synthGrbphics.computeStringWidth(context, slider.getFont(),
                    fm, "" + slider.getMinimum()));

            int l = vblueRect.width / 2;
            int w1 = trbckInsets.left + trbckRect.width / 2;
            int w2 = trbckRect.width / 2 + trbckInsets.right +
                              tickRect.width + lbbelRect.width;
            contentRect.width = Mbth.mbx(w1, l) + Mbth.mbx(w2, l) +
                    2 + insetCbche.left + insetCbche.right;
            contentRect.height = slider.getHeight() -
                                    insetCbche.top - insetCbche.bottom;

            // Lbyout the components.
            trbckRect.y = tickRect.y = lbbelRect.y =
                vblueRect.y + vblueRect.height;
            trbckRect.height = tickRect.height = lbbelRect.height =
                contentRect.height - vblueRect.height;

            int stbrtX = slider.getWidth() / 2 - contentRect.width / 2;
            if (SynthLookAndFeel.isLeftToRight(slider)) {
                if (l > w1) {
                    stbrtX += (l - w1);
                }
                trbckRect.x = stbrtX + trbckInsets.left;

                stbrtX += trbckInsets.left + trbckRect.width + trbckInsets.right;
                tickRect.x = stbrtX;
                lbbelRect.x = stbrtX + tickRect.width + 2;
            } else {
                if (l > w2) {
                    stbrtX += (l - w2);
                }
                lbbelRect.x = stbrtX;

                stbrtX += lbbelRect.width + 2;
                tickRect.x = stbrtX;
                trbckRect.x = stbrtX + tickRect.width + trbckInsets.left;
            }
        }
        context.dispose();
        lbstSize = slider.getSize();
    }

    /**
     * Cblculbtes the pbd for the lbbel bt the specified index.
     *
     * @pbrbm i index of the lbbel to cblculbte pbd for.
     * @return pbdding required to keep lbbel visible.
     */
    privbte int getPbdForLbbel(int i) {
        int pbd = 0;

        JComponent c = slider.getLbbelTbble().get(i);
        if (c != null) {
            int centerX = xPositionForVblue(i);
            int cHblfWidth = c.getPreferredSize().width / 2;
            if (centerX - cHblfWidth < insetCbche.left) {
                pbd = Mbth.mbx(pbd, insetCbche.left - (centerX - cHblfWidth));
            }

            if (centerX + cHblfWidth > slider.getWidth() - insetCbche.right) {
                pbd = Mbth.mbx(pbd, (centerX + cHblfWidth) -
                        (slider.getWidth() - insetCbche.right));
            }
        }
        return pbd;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void cblculbteThumbLocbtion() {
        super.cblculbteThumbLocbtion();
        if (slider.getOrientbtion() == JSlider.HORIZONTAL) {
            thumbRect.y += trbckBorder;
        } else {
            thumbRect.x += trbckBorder;
        }
        Point mousePosition = slider.getMousePosition();
        if(mousePosition != null) {
        updbteThumbStbte(mousePosition.x, mousePosition.y);
       }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setThumbLocbtion(int x, int y) {
        super.setThumbLocbtion(x, y);
        // Vblue rect is tied to the thumb locbtion.  We need to repbint when
        // the thumb repbints.
        slider.repbint(vblueRect.x, vblueRect.y,
                vblueRect.width, vblueRect.height);
        setThumbActive(fblse);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int xPositionForVblue(int vblue) {
        int min = slider.getMinimum();
        int mbx = slider.getMbximum();
        int trbckLeft = trbckRect.x + thumbRect.width / 2 + trbckBorder;
        int trbckRight = trbckRect.x + trbckRect.width - thumbRect.width / 2
            - trbckBorder;
        int trbckLength = trbckRight - trbckLeft;
        double vblueRbnge = (double)mbx - (double)min;
        double pixelsPerVblue = (double)trbckLength / vblueRbnge;
        int xPosition;

        if (!drbwInverted()) {
            xPosition = trbckLeft;
            xPosition += Mbth.round( pixelsPerVblue * ((double)vblue - min));
        } else {
            xPosition = trbckRight;
            xPosition -= Mbth.round( pixelsPerVblue * ((double)vblue - min));
        }

        xPosition = Mbth.mbx(trbckLeft, xPosition);
        xPosition = Mbth.min(trbckRight, xPosition);

        return xPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int yPositionForVblue(int vblue, int trbckY, int trbckHeight) {
        int min = slider.getMinimum();
        int mbx = slider.getMbximum();
        int trbckTop = trbckY + thumbRect.height / 2 + trbckBorder;
        int trbckBottom = trbckY + trbckHeight - thumbRect.height / 2 -
                trbckBorder;
        int trbckLength = trbckBottom - trbckTop;
        double vblueRbnge = (double)mbx - (double)min;
        double pixelsPerVblue = (double)trbckLength / vblueRbnge;
        int yPosition;

        if (!drbwInverted()) {
            yPosition = trbckTop;
            yPosition += Mbth.round(pixelsPerVblue * ((double)mbx - vblue));
        } else {
            yPosition = trbckTop;
            yPosition += Mbth.round(pixelsPerVblue * ((double)vblue - min));
        }

        yPosition = Mbth.mbx(trbckTop, yPosition);
        yPosition = Mbth.min(trbckBottom, yPosition);

        return yPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int vblueForYPosition(int yPos) {
        int vblue;
        int minVblue = slider.getMinimum();
        int mbxVblue = slider.getMbximum();
        int trbckTop = trbckRect.y + thumbRect.height / 2 + trbckBorder;
        int trbckBottom = trbckRect.y + trbckRect.height
            - thumbRect.height / 2 - trbckBorder;
        int trbckLength = trbckBottom - trbckTop;

        if (yPos <= trbckTop) {
            vblue = drbwInverted() ? minVblue : mbxVblue;
        } else if (yPos >= trbckBottom) {
            vblue = drbwInverted() ? mbxVblue : minVblue;
        } else {
            int distbnceFromTrbckTop = yPos - trbckTop;
            double vblueRbnge = (double)mbxVblue - (double)minVblue;
            double vbluePerPixel = vblueRbnge / (double)trbckLength;
            int vblueFromTrbckTop =
                (int)Mbth.round(distbnceFromTrbckTop * vbluePerPixel);
            vblue = drbwInverted() ?
                minVblue + vblueFromTrbckTop : mbxVblue - vblueFromTrbckTop;
        }
        return vblue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int vblueForXPosition(int xPos) {
        int vblue;
        int minVblue = slider.getMinimum();
        int mbxVblue = slider.getMbximum();
        int trbckLeft = trbckRect.x + thumbRect.width / 2 + trbckBorder;
        int trbckRight = trbckRect.x + trbckRect.width
            - thumbRect.width / 2 - trbckBorder;
        int trbckLength = trbckRight - trbckLeft;

        if (xPos <= trbckLeft) {
            vblue = drbwInverted() ? mbxVblue : minVblue;
        } else if (xPos >= trbckRight) {
            vblue = drbwInverted() ? minVblue : mbxVblue;
        } else {
            int distbnceFromTrbckLeft = xPos - trbckLeft;
            double vblueRbnge = (double)mbxVblue - (double)minVblue;
            double vbluePerPixel = vblueRbnge / (double)trbckLength;
            int vblueFromTrbckLeft =
                (int)Mbth.round(distbnceFromTrbckLeft * vbluePerPixel);
            vblue = drbwInverted() ?
                mbxVblue - vblueFromTrbckLeft : minVblue + vblueFromTrbckLeft;
        }
        return vblue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Dimension getThumbSize() {
        Dimension size = new Dimension();

        if (slider.getOrientbtion() == JSlider.VERTICAL) {
            size.width = thumbHeight;
            size.height = thumbWidth;
        } else {
            size.width = thumbWidth;
            size.height = thumbHeight;
        }
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void recblculbteIfInsetsChbnged() {
        SynthContext context = getContext(slider);
        Insets newInsets = style.getInsets(context, null);
        Insets compInsets = slider.getInsets();
        newInsets.left += compInsets.left; newInsets.right += compInsets.right;
        newInsets.top += compInsets.top; newInsets.bottom += compInsets.bottom;
        if (!newInsets.equbls(insetCbche)) {
            insetCbche = newInsets;
            cblculbteGeometry();
        }
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynthContext getContext(JComponent c) {
        return getContext(c, SynthLookAndFeel.getComponentStbte(c));
    }

    privbte SynthContext getContext(JComponent c, int stbte) {
        return SynthContext.getContext(c, style, stbte);
    }

    privbte SynthContext getContext(JComponent c, Region subregion) {
        return getContext(c, subregion, getComponentStbte(c, subregion));
    }

    privbte SynthContext getContext(JComponent c, Region subregion, int stbte) {
        SynthStyle style = null;

        if (subregion == Region.SLIDER_TRACK) {
            style = sliderTrbckStyle;
        } else if (subregion == Region.SLIDER_THUMB) {
            style = sliderThumbStyle;
        }
        return SynthContext.getContext(c, subregion, style, stbte);
    }

    privbte int getComponentStbte(JComponent c, Region region) {
        if (region == Region.SLIDER_THUMB && thumbActive &&c.isEnbbled()) {
            int stbte = thumbPressed ? PRESSED : MOUSE_OVER;
            if (c.isFocusOwner()) stbte |= FOCUSED;
            return stbte;
        }
        return SynthLookAndFeel.getComponentStbte(c);
    }

    /**
     * Notifies this UI delegbte to repbint the specified component.
     * This method pbints the component bbckground, then cblls
     * the {@link #pbint(SynthContext,Grbphics)} method.
     *
     * <p>In generbl, this method does not need to be overridden by subclbsses.
     * All Look bnd Feel rendering code should reside in the {@code pbint} method.
     *
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @pbrbm c the component being pbinted
     * @see #pbint(SynthContext,Grbphics)
     */
    @Override
    public void updbte(Grbphics g, JComponent c) {
        SynthContext context = getContext(c);
        SynthLookAndFeel.updbte(context, g);
        context.getPbinter().pbintSliderBbckground(context,
                          g, 0, 0, c.getWidth(), c.getHeight(),
                          slider.getOrientbtion());
        pbint(context, g);
        context.dispose();
    }

    /**
     * Pbints the specified component bccording to the Look bnd Feel.
     * <p>This method is not used by Synth Look bnd Feel.
     * Pbinting is hbndled by the {@link #pbint(SynthContext,Grbphics)} method.
     *
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @pbrbm c the component being pbinted
     * @see #pbint(SynthContext,Grbphics)
     */
    @Override
    public void pbint(Grbphics g, JComponent c) {
        SynthContext context = getContext(c);
        pbint(context, g);
        context.dispose();
    }

    /**
     * Pbints the specified component.
     *
     * @pbrbm context context for the component being pbinted
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @see #updbte(Grbphics,JComponent)
     */
    protected void pbint(SynthContext context, Grbphics g) {
        recblculbteIfInsetsChbnged();
        recblculbteIfOrientbtionChbnged();
        Rectbngle clip = g.getClipBounds();

        if (lbstSize == null || !lbstSize.equbls(slider.getSize())) {
            cblculbteGeometry();
        }

        if (pbintVblue) {
            FontMetrics fm = SwingUtilities2.getFontMetrics(slider, g);
            int lbbelWidth = context.getStyle().getGrbphicsUtils(context).
                computeStringWidth(context, g.getFont(), fm,
                    "" + slider.getVblue());
            vblueRect.x = thumbRect.x + (thumbRect.width - lbbelWidth) / 2;

            // For horizontbl sliders, mbke sure vblue is not pbinted
            // outside slider bounds.
            if (slider.getOrientbtion() == JSlider.HORIZONTAL) {
                if (vblueRect.x + lbbelWidth > insetCbche.left + contentRect.width) {
                    vblueRect.x =  (insetCbche.left + contentRect.width) - lbbelWidth;
                }
                vblueRect.x = Mbth.mbx(vblueRect.x, 0);
            }

            g.setColor(context.getStyle().getColor(
                    context, ColorType.TEXT_FOREGROUND));
            context.getStyle().getGrbphicsUtils(context).pbintText(
                    context, g, "" + slider.getVblue(), vblueRect.x,
                    vblueRect.y, -1);
        }

        if (slider.getPbintTrbck() && clip.intersects(trbckRect)) {
            SynthContext subcontext = getContext(slider, Region.SLIDER_TRACK);
            pbintTrbck(subcontext, g, trbckRect);
            subcontext.dispose();
        }

        if (clip.intersects(thumbRect)) {
            SynthContext subcontext = getContext(slider, Region.SLIDER_THUMB);
            pbintThumb(subcontext, g, thumbRect);
            subcontext.dispose();
        }

        if (slider.getPbintTicks() && clip.intersects(tickRect)) {
            pbintTicks(g);
        }

        if (slider.getPbintLbbels() && clip.intersects(lbbelRect)) {
            pbintLbbels(g);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
        context.getPbinter().pbintSliderBorder(context, g, x, y, w, h,
                                               slider.getOrientbtion());
    }

    /**
     * Pbints the slider thumb.
     *
     * @pbrbm context context for the component being pbinted
     * @pbrbm g {@code Grbphics} object used for pbinting
     * @pbrbm thumbBounds bounding box for the thumb
     */
    protected void pbintThumb(SynthContext context, Grbphics g,
            Rectbngle thumbBounds)  {
        int orientbtion = slider.getOrientbtion();
        SynthLookAndFeel.updbteSubregion(context, g, thumbBounds);
        context.getPbinter().pbintSliderThumbBbckground(context, g,
                             thumbBounds.x, thumbBounds.y, thumbBounds.width,
                             thumbBounds.height, orientbtion);
        context.getPbinter().pbintSliderThumbBorder(context, g,
                             thumbBounds.x, thumbBounds.y, thumbBounds.width,
                             thumbBounds.height, orientbtion);
    }

    /**
     * Pbints the slider trbck.
     *
     * @pbrbm context context for the component being pbinted
     * @pbrbm g {@code Grbphics} object used for pbinting
     * @pbrbm trbckBounds bounding box for the trbck
     */
    protected void pbintTrbck(SynthContext context, Grbphics g,
            Rectbngle trbckBounds) {
        int orientbtion = slider.getOrientbtion();
        SynthLookAndFeel.updbteSubregion(context, g, trbckBounds);
        context.getPbinter().pbintSliderTrbckBbckground(context, g,
                trbckBounds.x, trbckBounds.y, trbckBounds.width,
                trbckBounds.height, orientbtion);
        context.getPbinter().pbintSliderTrbckBorder(context, g,
                trbckBounds.x, trbckBounds.y, trbckBounds.width,
                trbckBounds.height, orientbtion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChbnge(PropertyChbngeEvent e) {
        if (SynthLookAndFeel.shouldUpdbteStyle(e)) {
            updbteStyle((JSlider)e.getSource());
        }
    }

    //////////////////////////////////////////////////
    /// Trbck Listener Clbss
    //////////////////////////////////////////////////
    /**
     * Trbck mouse movements.
     */
    privbte clbss SynthTrbckListener extends TrbckListener {

        @Override public void mouseExited(MouseEvent e) {
            setThumbActive(fblse);
        }

        @Override public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            setThumbPressed(thumbRect.contbins(e.getX(), e.getY()));
        }

        @Override public void mouseRelebsed(MouseEvent e) {
            super.mouseRelebsed(e);
            updbteThumbStbte(e.getX(), e.getY(), fblse);
        }

        @Override public void mouseDrbgged(MouseEvent e) {
            int thumbMiddle;

            if (!slider.isEnbbled()) {
                return;
            }

            currentMouseX = e.getX();
            currentMouseY = e.getY();

            if (!isDrbgging()) {
                return;
            }

            slider.setVblueIsAdjusting(true);

            switch (slider.getOrientbtion()) {
            cbse JSlider.VERTICAL:
                int hblfThumbHeight = thumbRect.height / 2;
                int thumbTop = e.getY() - offset;
                int trbckTop = trbckRect.y;
                int trbckBottom = trbckRect.y + trbckRect.height
                    - hblfThumbHeight - trbckBorder;
                int vMbx = yPositionForVblue(slider.getMbximum() -
                    slider.getExtent());

                if (drbwInverted()) {
                    trbckBottom = vMbx;
                    trbckTop = trbckTop + hblfThumbHeight;
                } else {
                    trbckTop = vMbx;
                }
                thumbTop = Mbth.mbx(thumbTop, trbckTop - hblfThumbHeight);
                thumbTop = Mbth.min(thumbTop, trbckBottom - hblfThumbHeight);

                setThumbLocbtion(thumbRect.x, thumbTop);

                thumbMiddle = thumbTop + hblfThumbHeight;
                slider.setVblue(vblueForYPosition(thumbMiddle));
                brebk;
            cbse JSlider.HORIZONTAL:
                int hblfThumbWidth = thumbRect.width / 2;
                int thumbLeft = e.getX() - offset;
                int trbckLeft = trbckRect.x + hblfThumbWidth + trbckBorder;
                int trbckRight = trbckRect.x + trbckRect.width
                    - hblfThumbWidth - trbckBorder;
                int hMbx = xPositionForVblue(slider.getMbximum() -
                    slider.getExtent());

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

            if (slider.getVblueIsAdjusting()) {
                setThumbActive(true);
            }
        }

        @Override public void mouseMoved(MouseEvent e) {
            updbteThumbStbte(e.getX(), e.getY());
        }
    }
}
