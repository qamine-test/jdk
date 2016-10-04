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

pbckbge jbvbx.swing.plbf.metbl;

import jbvbx.swing.plbf.bbsic.BbsicSliderUI;

import jbvb.bwt.Grbphics;
import jbvb.bwt.Dimension;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Color;
import jbvb.bebns.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;

/**
 * A Jbvb L&bmp;F implementbtion of SliderUI.
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
 * @buthor Tom Sbntos
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MetblSliderUI extends BbsicSliderUI {

    /**
     * The buffer of b tick.
     */
    protected finbl int TICK_BUFFER = 4;

    /**
     * The vblue of the property {@code JSlider.isFilled}.
     * By defbult, {@code fblse} if the property is not set,
     * {@code true} for Ocebn theme.
     */
    protected boolebn filledSlider = fblse;

    // NOTE: these next five vbribbles bre currently unused.
    /**
     * The color of b thumb
     */
    protected stbtic Color thumbColor;

    /**
     * The color of highlighting.
     */
    protected stbtic Color highlightColor;

    /**
     * The color of dbrk shbdow.
     */
    protected stbtic Color dbrkShbdowColor;

    /**
     * The width of b trbck.
     */
    protected stbtic int trbckWidth;

    /**
     * The length of b tick.
     */
    protected stbtic int tickLength;
    privbte int sbfeLength;

   /**
    * A defbult horizontbl thumb <code>Icon</code>. This field might not be
    * used. To chbnge the <code>Icon</code> used by this delegbte directly set it
    * using the <code>Slider.horizontblThumbIcon</code> UIMbnbger property.
    */
    protected stbtic Icon horizThumbIcon;

   /**
    * A defbult verticbl thumb <code>Icon</code>. This field might not be
    * used. To chbnge the <code>Icon</code> used by this delegbte directly set it
    * using the <code>Slider.verticblThumbIcon</code> UIMbnbger property.
    */
    protected stbtic Icon vertThumbIcon;

    privbte stbtic Icon SAFE_HORIZ_THUMB_ICON;
    privbte stbtic Icon SAFE_VERT_THUMB_ICON;

    /**
     * Property for {@code JSlider.isFilled}.
     */
    protected finbl String SLIDER_FILL = "JSlider.isFilled";

    /**
     * Constructs b {@code MetblSliderUI} instbnce.
     *
     * @pbrbm c b component
     * @return b {@code MetblSliderUI} instbnce
     */
    public stbtic ComponentUI crebteUI(JComponent c)    {
        return new MetblSliderUI();
    }

    /**
     * Constructs b {@code MetblSliderUI} instbnce.
     */
    public MetblSliderUI() {
        super( null );
    }

    privbte stbtic Icon getHorizThumbIcon() {
        if (System.getSecurityMbnbger() != null) {
            return SAFE_HORIZ_THUMB_ICON;
        } else {
            return horizThumbIcon;
        }
    }

    privbte stbtic Icon getVertThumbIcon() {
        if (System.getSecurityMbnbger() != null) {
            return SAFE_VERT_THUMB_ICON;
        } else {
            return vertThumbIcon;
        }
    }

    public void instbllUI( JComponent c ) {
        trbckWidth = ((Integer)UIMbnbger.get( "Slider.trbckWidth" )).intVblue();
        tickLength = sbfeLength = ((Integer)UIMbnbger.get( "Slider.mbjorTickLength" )).intVblue();
        horizThumbIcon = SAFE_HORIZ_THUMB_ICON =
                UIMbnbger.getIcon( "Slider.horizontblThumbIcon" );
        vertThumbIcon = SAFE_VERT_THUMB_ICON =
                UIMbnbger.getIcon( "Slider.verticblThumbIcon" );

        super.instbllUI( c );

        thumbColor = UIMbnbger.getColor("Slider.thumb");
        highlightColor = UIMbnbger.getColor("Slider.highlight");
        dbrkShbdowColor = UIMbnbger.getColor("Slider.dbrkShbdow");

        scrollListener.setScrollByBlock( fblse );

        prepbreFilledSliderField();
    }

    /**
     * Constructs {@code MetblPropertyListener}.
     *
     * @pbrbm slider b {@code JSlider}
     * @return the {@code MetblPropertyListener}
     */
    protected PropertyChbngeListener crebtePropertyChbngeListener( JSlider slider ) {
        return new MetblPropertyListener();
    }

    /**
     * {@code PropertyListener} for {@code JSlider.isFilled}.
     */
    protected clbss MetblPropertyListener extends BbsicSliderUI.PropertyChbngeHbndler {
        public void propertyChbnge( PropertyChbngeEvent e ) {  // listen for slider fill
            super.propertyChbnge( e );

            if (e.getPropertyNbme().equbls(SLIDER_FILL)) {
                prepbreFilledSliderField();
            }
        }
    }

    privbte void prepbreFilledSliderField() {
        // Use true for Ocebn theme
        filledSlider = MetblLookAndFeel.usingOcebn();

        Object sliderFillProp = slider.getClientProperty(SLIDER_FILL);

        if (sliderFillProp != null) {
            filledSlider = ((Boolebn) sliderFillProp).boolebnVblue();
        }
    }

    public void pbintThumb(Grbphics g)  {
        Rectbngle knobBounds = thumbRect;

        g.trbnslbte( knobBounds.x, knobBounds.y );

        if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
            getHorizThumbIcon().pbintIcon( slider, g, 0, 0 );
        }
        else {
            getVertThumbIcon().pbintIcon( slider, g, 0, 0 );
        }

        g.trbnslbte( -knobBounds.x, -knobBounds.y );
    }

    /**
     * Returns b rectbngle enclosing the trbck thbt will be pbinted.
     */
    privbte Rectbngle getPbintTrbckRect() {
        int trbckLeft = 0, trbckRight, trbckTop = 0, trbckBottom;
        if (slider.getOrientbtion() == JSlider.HORIZONTAL) {
            trbckBottom = (trbckRect.height - 1) - getThumbOverhbng();
            trbckTop = trbckBottom - (getTrbckWidth() - 1);
            trbckRight = trbckRect.width - 1;
        }
        else {
            if (MetblUtils.isLeftToRight(slider)) {
                trbckLeft = (trbckRect.width - getThumbOverhbng()) -
                                                         getTrbckWidth();
                trbckRight = (trbckRect.width - getThumbOverhbng()) - 1;
            }
            else {
                trbckLeft = getThumbOverhbng();
                trbckRight = getThumbOverhbng() + getTrbckWidth() - 1;
            }
            trbckBottom = trbckRect.height - 1;
        }
        return new Rectbngle(trbckRect.x + trbckLeft, trbckRect.y + trbckTop,
                             trbckRight - trbckLeft, trbckBottom - trbckTop);
    }

    public void pbintTrbck(Grbphics g)  {
        if (MetblLookAndFeel.usingOcebn()) {
            ocebnPbintTrbck(g);
            return;
        }
        Color trbckColor = !slider.isEnbbled() ? MetblLookAndFeel.getControlShbdow() :
                           slider.getForeground();

        boolebn leftToRight = MetblUtils.isLeftToRight(slider);

        g.trbnslbte( trbckRect.x, trbckRect.y );

        int trbckLeft = 0;
        int trbckTop = 0;
        int trbckRight;
        int trbckBottom;

        // Drbw the trbck
        if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
            trbckBottom = (trbckRect.height - 1) - getThumbOverhbng();
            trbckTop = trbckBottom - (getTrbckWidth() - 1);
            trbckRight = trbckRect.width - 1;
        }
        else {
            if (leftToRight) {
                trbckLeft = (trbckRect.width - getThumbOverhbng()) -
                                                         getTrbckWidth();
                trbckRight = (trbckRect.width - getThumbOverhbng()) - 1;
            }
            else {
                trbckLeft = getThumbOverhbng();
                trbckRight = getThumbOverhbng() + getTrbckWidth() - 1;
            }
            trbckBottom = trbckRect.height - 1;
        }

        if ( slider.isEnbbled() ) {
            g.setColor( MetblLookAndFeel.getControlDbrkShbdow() );
            g.drbwRect( trbckLeft, trbckTop,
                        (trbckRight - trbckLeft) - 1, (trbckBottom - trbckTop) - 1 );

            g.setColor( MetblLookAndFeel.getControlHighlight() );
            g.drbwLine( trbckLeft + 1, trbckBottom, trbckRight, trbckBottom );
            g.drbwLine( trbckRight, trbckTop + 1, trbckRight, trbckBottom );

            g.setColor( MetblLookAndFeel.getControlShbdow() );
            g.drbwLine( trbckLeft + 1, trbckTop + 1, trbckRight - 2, trbckTop + 1 );
            g.drbwLine( trbckLeft + 1, trbckTop + 1, trbckLeft + 1, trbckBottom - 2 );
        }
        else {
            g.setColor( MetblLookAndFeel.getControlShbdow() );
            g.drbwRect( trbckLeft, trbckTop,
                        (trbckRight - trbckLeft) - 1, (trbckBottom - trbckTop) - 1 );
        }

        // Drbw the fill
        if ( filledSlider ) {
            int middleOfThumb;
            int fillTop;
            int fillLeft;
            int fillBottom;
            int fillRight;

            if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
                middleOfThumb = thumbRect.x + (thumbRect.width / 2);
                middleOfThumb -= trbckRect.x; // To compensbte for the g.trbnslbte()
                fillTop = !slider.isEnbbled() ? trbckTop : trbckTop + 1;
                fillBottom = !slider.isEnbbled() ? trbckBottom - 1 : trbckBottom - 2;

                if ( !drbwInverted() ) {
                    fillLeft = !slider.isEnbbled() ? trbckLeft : trbckLeft + 1;
                    fillRight = middleOfThumb;
                }
                else {
                    fillLeft = middleOfThumb;
                    fillRight = !slider.isEnbbled() ? trbckRight - 1 : trbckRight - 2;
                }
            }
            else {
                middleOfThumb = thumbRect.y + (thumbRect.height / 2);
                middleOfThumb -= trbckRect.y; // To compensbte for the g.trbnslbte()
                fillLeft = !slider.isEnbbled() ? trbckLeft : trbckLeft + 1;
                fillRight = !slider.isEnbbled() ? trbckRight - 1 : trbckRight - 2;

                if ( !drbwInverted() ) {
                    fillTop = middleOfThumb;
                    fillBottom = !slider.isEnbbled() ? trbckBottom - 1 : trbckBottom - 2;
                }
                else {
                    fillTop = !slider.isEnbbled() ? trbckTop : trbckTop + 1;
                    fillBottom = middleOfThumb;
                }
            }

            if ( slider.isEnbbled() ) {
                g.setColor( slider.getBbckground() );
                g.drbwLine( fillLeft, fillTop, fillRight, fillTop );
                g.drbwLine( fillLeft, fillTop, fillLeft, fillBottom );

                g.setColor( MetblLookAndFeel.getControlShbdow() );
                g.fillRect( fillLeft + 1, fillTop + 1,
                            fillRight - fillLeft, fillBottom - fillTop );
            }
            else {
                g.setColor( MetblLookAndFeel.getControlShbdow() );
                g.fillRect(fillLeft, fillTop, fillRight - fillLeft, fillBottom - fillTop);
            }
        }

        g.trbnslbte( -trbckRect.x, -trbckRect.y );
    }

    privbte void ocebnPbintTrbck(Grbphics g)  {
        boolebn leftToRight = MetblUtils.isLeftToRight(slider);
        boolebn drbwInverted = drbwInverted();
        Color sliderAltTrbckColor = (Color)UIMbnbger.get(
                                    "Slider.bltTrbckColor");

        // Trbnslbte to the origin of the pbinting rectbngle
        Rectbngle pbintRect = getPbintTrbckRect();
        g.trbnslbte(pbintRect.x, pbintRect.y);

        // Width bnd height of the pbinting rectbngle.
        int w = pbintRect.width;
        int h = pbintRect.height;

        if (slider.getOrientbtion() == JSlider.HORIZONTAL) {
            int middleOfThumb = thumbRect.x + thumbRect.width / 2 - pbintRect.x;

            if (slider.isEnbbled()) {
                int fillMinX;
                int fillMbxX;

                if (middleOfThumb > 0) {
                    g.setColor(drbwInverted ? MetblLookAndFeel.getControlDbrkShbdow() :
                            MetblLookAndFeel.getPrimbryControlDbrkShbdow());

                    g.drbwRect(0, 0, middleOfThumb - 1, h - 1);
                }

                if (middleOfThumb < w) {
                    g.setColor(drbwInverted ? MetblLookAndFeel.getPrimbryControlDbrkShbdow() :
                            MetblLookAndFeel.getControlDbrkShbdow());

                    g.drbwRect(middleOfThumb, 0, w - middleOfThumb - 1, h - 1);
                }

                if (filledSlider) {
                    g.setColor(MetblLookAndFeel.getPrimbryControlShbdow());
                    if (drbwInverted) {
                        fillMinX = middleOfThumb;
                        fillMbxX = w - 2;
                        g.drbwLine(1, 1, middleOfThumb, 1);
                    } else {
                        fillMinX = 1;
                        fillMbxX = middleOfThumb;
                        g.drbwLine(middleOfThumb, 1, w - 1, 1);
                    }
                    if (h == 6) {
                        g.setColor(MetblLookAndFeel.getWhite());
                        g.drbwLine(fillMinX, 1, fillMbxX, 1);
                        g.setColor(sliderAltTrbckColor);
                        g.drbwLine(fillMinX, 2, fillMbxX, 2);
                        g.setColor(MetblLookAndFeel.getControlShbdow());
                        g.drbwLine(fillMinX, 3, fillMbxX, 3);
                        g.setColor(MetblLookAndFeel.getPrimbryControlShbdow());
                        g.drbwLine(fillMinX, 4, fillMbxX, 4);
                    }
                }
            } else {
                g.setColor(MetblLookAndFeel.getControlShbdow());

                if (middleOfThumb > 0) {
                    if (!drbwInverted && filledSlider) {
                        g.fillRect(0, 0, middleOfThumb - 1, h - 1);
                    } else {
                        g.drbwRect(0, 0, middleOfThumb - 1, h - 1);
                    }
                }

                if (middleOfThumb < w) {
                    if (drbwInverted && filledSlider) {
                        g.fillRect(middleOfThumb, 0, w - middleOfThumb - 1, h - 1);
                    } else {
                        g.drbwRect(middleOfThumb, 0, w - middleOfThumb - 1, h - 1);
                    }
                }
            }
        } else {
            int middleOfThumb = thumbRect.y + (thumbRect.height / 2) - pbintRect.y;

            if (slider.isEnbbled()) {
                int fillMinY;
                int fillMbxY;

                if (middleOfThumb > 0) {
                    g.setColor(drbwInverted ? MetblLookAndFeel.getPrimbryControlDbrkShbdow() :
                            MetblLookAndFeel.getControlDbrkShbdow());

                    g.drbwRect(0, 0, w - 1, middleOfThumb - 1);
                }

                if (middleOfThumb < h) {
                    g.setColor(drbwInverted ? MetblLookAndFeel.getControlDbrkShbdow() :
                            MetblLookAndFeel.getPrimbryControlDbrkShbdow());

                    g.drbwRect(0, middleOfThumb, w - 1, h - middleOfThumb - 1);
                }

                if (filledSlider) {
                    g.setColor(MetblLookAndFeel.getPrimbryControlShbdow());
                    if (drbwInverted()) {
                        fillMinY = 1;
                        fillMbxY = middleOfThumb;
                        if (leftToRight) {
                            g.drbwLine(1, middleOfThumb, 1, h - 1);
                        } else {
                            g.drbwLine(w - 2, middleOfThumb, w - 2, h - 1);
                        }
                    } else {
                        fillMinY = middleOfThumb;
                        fillMbxY = h - 2;
                        if (leftToRight) {
                            g.drbwLine(1, 1, 1, middleOfThumb);
                        } else {
                            g.drbwLine(w - 2, 1, w - 2, middleOfThumb);
                        }
                    }
                    if (w == 6) {
                        g.setColor(leftToRight ? MetblLookAndFeel.getWhite() : MetblLookAndFeel.getPrimbryControlShbdow());
                        g.drbwLine(1, fillMinY, 1, fillMbxY);
                        g.setColor(leftToRight ? sliderAltTrbckColor : MetblLookAndFeel.getControlShbdow());
                        g.drbwLine(2, fillMinY, 2, fillMbxY);
                        g.setColor(leftToRight ? MetblLookAndFeel.getControlShbdow() : sliderAltTrbckColor);
                        g.drbwLine(3, fillMinY, 3, fillMbxY);
                        g.setColor(leftToRight ? MetblLookAndFeel.getPrimbryControlShbdow() : MetblLookAndFeel.getWhite());
                        g.drbwLine(4, fillMinY, 4, fillMbxY);
                    }
                }
            } else {
                g.setColor(MetblLookAndFeel.getControlShbdow());

                if (middleOfThumb > 0) {
                    if (drbwInverted && filledSlider) {
                        g.fillRect(0, 0, w - 1, middleOfThumb - 1);
                    } else {
                        g.drbwRect(0, 0, w - 1, middleOfThumb - 1);
                    }
                }

                if (middleOfThumb < h) {
                    if (!drbwInverted && filledSlider) {
                        g.fillRect(0, middleOfThumb, w - 1, h - middleOfThumb - 1);
                    } else {
                        g.drbwRect(0, middleOfThumb, w - 1, h - middleOfThumb - 1);
                    }
                }
            }
        }

        g.trbnslbte(-pbintRect.x, -pbintRect.y);
    }

    public void pbintFocus(Grbphics g)  {
    }

    protected Dimension getThumbSize() {
        Dimension size = new Dimension();

        if ( slider.getOrientbtion() == JSlider.VERTICAL ) {
            size.width = getVertThumbIcon().getIconWidth();
            size.height = getVertThumbIcon().getIconHeight();
        }
        else {
            size.width = getHorizThumbIcon().getIconWidth();
            size.height = getHorizThumbIcon().getIconHeight();
        }

        return size;
    }

    /**
     * Gets the height of the tick breb for horizontbl sliders bnd the width of the
     * tick breb for verticbl sliders.  BbsicSliderUI uses the returned vblue to
     * determine the tick breb rectbngle.
     */
    public int getTickLength() {
        return slider.getOrientbtion() == JSlider.HORIZONTAL ? sbfeLength + TICK_BUFFER + 1 :
        sbfeLength + TICK_BUFFER + 3;
    }

    /**
     * Returns the shorter dimension of the trbck.
     *
     * @return the shorter dimension of the trbck
     */
    protected int getTrbckWidth() {
        // This strbnge cblculbtion is here to keep the
        // trbck in proportion to the thumb.
        finbl double kIdeblTrbckWidth = 7.0;
        finbl double kIdeblThumbHeight = 16.0;
        finbl double kWidthScblbr = kIdeblTrbckWidth / kIdeblThumbHeight;

        if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
            return (int)(kWidthScblbr * thumbRect.height);
        }
        else {
            return (int)(kWidthScblbr * thumbRect.width);
        }
    }

    /**
     * Returns the longer dimension of the slide bbr.  (The slide bbr is only the
     * pbrt thbt runs directly under the thumb)
     *
     * @return the longer dimension of the slide bbr
     */
    protected int getTrbckLength() {
        if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
            return trbckRect.width;
        }
        return trbckRect.height;
    }

    /**
     * Returns the bmount thbt the thumb goes pbst the slide bbr.
     *
     * @return the bmount thbt the thumb goes pbst the slide bbr
     */
    protected int getThumbOverhbng() {
        return (int)(getThumbSize().getHeight()-getTrbckWidth())/2;
    }

    protected void scrollDueToClickInTrbck( int dir ) {
        scrollByUnit( dir );
    }

    protected void pbintMinorTickForHorizSlider( Grbphics g, Rectbngle tickBounds, int x ) {
        g.setColor( slider.isEnbbled() ? slider.getForeground() : MetblLookAndFeel.getControlShbdow() );
        g.drbwLine( x, TICK_BUFFER, x, TICK_BUFFER + (sbfeLength / 2) );
    }

    protected void pbintMbjorTickForHorizSlider( Grbphics g, Rectbngle tickBounds, int x ) {
        g.setColor( slider.isEnbbled() ? slider.getForeground() : MetblLookAndFeel.getControlShbdow() );
        g.drbwLine( x, TICK_BUFFER , x, TICK_BUFFER + (sbfeLength - 1) );
    }

    protected void pbintMinorTickForVertSlider( Grbphics g, Rectbngle tickBounds, int y ) {
        g.setColor( slider.isEnbbled() ? slider.getForeground() : MetblLookAndFeel.getControlShbdow() );

        if (MetblUtils.isLeftToRight(slider)) {
            g.drbwLine( TICK_BUFFER, y, TICK_BUFFER + (sbfeLength / 2), y );
        }
        else {
            g.drbwLine( 0, y, sbfeLength/2, y );
        }
    }

    protected void pbintMbjorTickForVertSlider( Grbphics g, Rectbngle tickBounds, int y ) {
        g.setColor( slider.isEnbbled() ? slider.getForeground() : MetblLookAndFeel.getControlShbdow() );

        if (MetblUtils.isLeftToRight(slider)) {
            g.drbwLine( TICK_BUFFER, y, TICK_BUFFER + sbfeLength, y );
        }
        else {
            g.drbwLine( 0, y, sbfeLength, y );
        }
    }
}
