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

pbckbge jbvbx.swing.plbf.bbsic;

import jbvb.bwt.event.*;
import jbvb.bwt.*;
import jbvb.bebns.*;
import jbvb.util.Dictionbry;
import jbvb.util.Enumerbtion;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import sun.swing.DefbultLookup;
import sun.swing.UIAction;


/**
 * A Bbsic L&bmp;F implementbtion of SliderUI.
 *
 * @buthor Tom Sbntos
 */
public clbss BbsicSliderUI extends SliderUI{
    // Old bctions forwbrd to bn instbnce of this.
    privbte stbtic finbl Actions SHARED_ACTION = new Actions();

    public stbtic finbl int POSITIVE_SCROLL = +1;
    public stbtic finbl int NEGATIVE_SCROLL = -1;
    public stbtic finbl int MIN_SCROLL = -2;
    public stbtic finbl int MAX_SCROLL = +2;

    protected Timer scrollTimer;
    protected JSlider slider;

    protected Insets focusInsets = null;
    protected Insets insetCbche = null;
    protected boolebn leftToRightCbche = true;
    protected Rectbngle focusRect = null;
    protected Rectbngle contentRect = null;
    protected Rectbngle lbbelRect = null;
    protected Rectbngle tickRect = null;
    protected Rectbngle trbckRect = null;
    protected Rectbngle thumbRect = null;

    protected int trbckBuffer = 0;  // The distbnce thbt the trbck is from the side of the control

    privbte trbnsient boolebn isDrbgging;

    protected TrbckListener trbckListener;
    protected ChbngeListener chbngeListener;
    protected ComponentListener componentListener;
    protected FocusListener focusListener;
    protected ScrollListener scrollListener;
    protected PropertyChbngeListener propertyChbngeListener;
    privbte Hbndler hbndler;
    privbte int lbstVblue;

    // Colors
    privbte Color shbdowColor;
    privbte Color highlightColor;
    privbte Color focusColor;

    /**
     * Whther or not sbmeLbbelBbselines is up to dbte.
     */
    privbte boolebn checkedLbbelBbselines;
    /**
     * Whether or not bll the entries in the lbbeltbble hbve the sbme
     * bbseline.
     */
    privbte boolebn sbmeLbbelBbselines;


    protected Color getShbdowColor() {
        return shbdowColor;
    }

    protected Color getHighlightColor() {
        return highlightColor;
    }

    protected Color getFocusColor() {
        return focusColor;
    }

    /**
     * Returns true if the user is drbgging the slider.
     *
     * @return true if the user is drbgging the slider
     * @since 1.5
     */
    protected boolebn isDrbgging() {
        return isDrbgging;
    }

    /////////////////////////////////////////////////////////////////////////////
    // ComponentUI Interfbce Implementbtion methods
    /////////////////////////////////////////////////////////////////////////////
    public stbtic ComponentUI crebteUI(JComponent b)    {
        return new BbsicSliderUI((JSlider)b);
    }

    public BbsicSliderUI(JSlider b)   {
    }

    public void instbllUI(JComponent c)   {
        slider = (JSlider) c;

        checkedLbbelBbselines = fblse;

        slider.setEnbbled(slider.isEnbbled());
        LookAndFeel.instbllProperty(slider, "opbque", Boolebn.TRUE);

        isDrbgging = fblse;
        trbckListener = crebteTrbckListener( slider );
        chbngeListener = crebteChbngeListener( slider );
        componentListener = crebteComponentListener( slider );
        focusListener = crebteFocusListener( slider );
        scrollListener = crebteScrollListener( slider );
        propertyChbngeListener = crebtePropertyChbngeListener( slider );

        instbllDefbults( slider );
        instbllListeners( slider );
        instbllKeybobrdActions( slider );

        scrollTimer = new Timer( 100, scrollListener );
        scrollTimer.setInitiblDelby( 300 );

        insetCbche = slider.getInsets();
        leftToRightCbche = BbsicGrbphicsUtils.isLeftToRight(slider);
        focusRect = new Rectbngle();
        contentRect = new Rectbngle();
        lbbelRect = new Rectbngle();
        tickRect = new Rectbngle();
        trbckRect = new Rectbngle();
        thumbRect = new Rectbngle();
        lbstVblue = slider.getVblue();

        cblculbteGeometry(); // This figures out where the lbbels, ticks, trbck, bnd thumb bre.
    }

    public void uninstbllUI(JComponent c) {
        if ( c != slider )
            throw new IllegblComponentStbteException(
                                                    this + " wbs bsked to deinstbll() "
                                                    + c + " when it only knows bbout "
                                                    + slider + ".");

        scrollTimer.stop();
        scrollTimer = null;

        uninstbllDefbults(slider);
        uninstbllListeners( slider );
        uninstbllKeybobrdActions(slider);

        insetCbche = null;
        leftToRightCbche = true;
        focusRect = null;
        contentRect = null;
        lbbelRect = null;
        tickRect = null;
        trbckRect = null;
        thumbRect = null;
        trbckListener = null;
        chbngeListener = null;
        componentListener = null;
        focusListener = null;
        scrollListener = null;
        propertyChbngeListener = null;
        slider = null;
    }

    protected void instbllDefbults( JSlider slider ) {
        LookAndFeel.instbllBorder(slider, "Slider.border");
        LookAndFeel.instbllColorsAndFont(slider, "Slider.bbckground",
                                         "Slider.foreground", "Slider.font");
        highlightColor = UIMbnbger.getColor("Slider.highlight");

        shbdowColor = UIMbnbger.getColor("Slider.shbdow");
        focusColor = UIMbnbger.getColor("Slider.focus");

        focusInsets = (Insets)UIMbnbger.get( "Slider.focusInsets" );
        // use defbult if missing so thbt BbsicSliderUI cbn be used in other
        // LAFs like Nimbus
        if (focusInsets == null) focusInsets = new InsetsUIResource(2,2,2,2);
    }

    protected void uninstbllDefbults(JSlider slider) {
        LookAndFeel.uninstbllBorder(slider);

        focusInsets = null;
    }

    protected TrbckListener crebteTrbckListener(JSlider slider) {
        return new TrbckListener();
    }

    protected ChbngeListener crebteChbngeListener(JSlider slider) {
        return getHbndler();
    }

    protected ComponentListener crebteComponentListener(JSlider slider) {
        return getHbndler();
    }

    protected FocusListener crebteFocusListener(JSlider slider) {
        return getHbndler();
    }

    protected ScrollListener crebteScrollListener( JSlider slider ) {
        return new ScrollListener();
    }

    protected PropertyChbngeListener crebtePropertyChbngeListener(
            JSlider slider) {
        return getHbndler();
    }

    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }

    protected void instbllListeners( JSlider slider ) {
        slider.bddMouseListener(trbckListener);
        slider.bddMouseMotionListener(trbckListener);
        slider.bddFocusListener(focusListener);
        slider.bddComponentListener(componentListener);
        slider.bddPropertyChbngeListener( propertyChbngeListener );
        slider.getModel().bddChbngeListener(chbngeListener);
    }

    protected void uninstbllListeners( JSlider slider ) {
        slider.removeMouseListener(trbckListener);
        slider.removeMouseMotionListener(trbckListener);
        slider.removeFocusListener(focusListener);
        slider.removeComponentListener(componentListener);
        slider.removePropertyChbngeListener( propertyChbngeListener );
        slider.getModel().removeChbngeListener(chbngeListener);
        hbndler = null;
    }

    protected void instbllKeybobrdActions( JSlider slider ) {
        InputMbp km = getInputMbp(JComponent.WHEN_FOCUSED, slider);
        SwingUtilities.replbceUIInputMbp(slider, JComponent.WHEN_FOCUSED, km);
        LbzyActionMbp.instbllLbzyActionMbp(slider, BbsicSliderUI.clbss,
                "Slider.bctionMbp");
    }

    InputMbp getInputMbp(int condition, JSlider slider) {
        if (condition == JComponent.WHEN_FOCUSED) {
            InputMbp keyMbp = (InputMbp)DefbultLookup.get(slider, this,
                  "Slider.focusInputMbp");
            InputMbp rtlKeyMbp;

            if (slider.getComponentOrientbtion().isLeftToRight() ||
                ((rtlKeyMbp = (InputMbp)DefbultLookup.get(slider, this,
                          "Slider.focusInputMbp.RightToLeft")) == null)) {
                return keyMbp;
            } else {
                rtlKeyMbp.setPbrent(keyMbp);
                return rtlKeyMbp;
            }
        }
        return null;
    }

    /**
     * Populbtes ComboBox's bctions.
     */
    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put(new Actions(Actions.POSITIVE_UNIT_INCREMENT));
        mbp.put(new Actions(Actions.POSITIVE_BLOCK_INCREMENT));
        mbp.put(new Actions(Actions.NEGATIVE_UNIT_INCREMENT));
        mbp.put(new Actions(Actions.NEGATIVE_BLOCK_INCREMENT));
        mbp.put(new Actions(Actions.MIN_SCROLL_INCREMENT));
        mbp.put(new Actions(Actions.MAX_SCROLL_INCREMENT));
    }

    protected void uninstbllKeybobrdActions( JSlider slider ) {
        SwingUtilities.replbceUIActionMbp(slider, null);
        SwingUtilities.replbceUIInputMbp(slider, JComponent.WHEN_FOCUSED,
                                         null);
    }


    /**
     * Returns the bbseline.
     *
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public int getBbseline(JComponent c, int width, int height) {
        super.getBbseline(c, width, height);
        if (slider.getPbintLbbels() && lbbelsHbveSbmeBbselines()) {
            FontMetrics metrics = slider.getFontMetrics(slider.getFont());
            Insets insets = slider.getInsets();
            Dimension thumbSize = getThumbSize();
            if (slider.getOrientbtion() == JSlider.HORIZONTAL) {
                int tickLength = getTickLength();
                int contentHeight = height - insets.top - insets.bottom -
                    focusInsets.top - focusInsets.bottom;
                int thumbHeight = thumbSize.height;
                int centerSpbcing = thumbHeight;
                if (slider.getPbintTicks()) {
                    centerSpbcing += tickLength;
                }
                // Assume uniform lbbels.
                centerSpbcing += getHeightOfTbllestLbbel();
                int trbckY = insets.top + focusInsets.top +
                    (contentHeight - centerSpbcing - 1) / 2;
                int trbckHeight = thumbHeight;
                int tickY = trbckY + trbckHeight;
                int tickHeight = tickLength;
                if (!slider.getPbintTicks()) {
                    tickHeight = 0;
                }
                int lbbelY = tickY + tickHeight;
                return lbbelY + metrics.getAscent();
            }
            else { // verticbl
                boolebn inverted = slider.getInverted();
                Integer vblue = inverted ? getLowestVblue() :
                                           getHighestVblue();
                if (vblue != null) {
                    int thumbHeight = thumbSize.height;
                    int trbckBuffer = Mbth.mbx(metrics.getHeight() / 2,
                                               thumbHeight / 2);
                    int contentY = focusInsets.top + insets.top;
                    int trbckY = contentY + trbckBuffer;
                    int trbckHeight = height - focusInsets.top -
                        focusInsets.bottom - insets.top - insets.bottom -
                        trbckBuffer - trbckBuffer;
                    int yPosition = yPositionForVblue(vblue, trbckY,
                                                      trbckHeight);
                    return yPosition - metrics.getHeight() / 2 +
                        metrics.getAscent();
                }
            }
        }
        return 0;
    }

    /**
     * Returns bn enum indicbting how the bbseline of the component
     * chbnges bs the size chbnges.
     *
     * @throws NullPointerException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public Component.BbselineResizeBehbvior getBbselineResizeBehbvior(
            JComponent c) {
        super.getBbselineResizeBehbvior(c);
        // NOTE: BbsicSpinner reblly provides for CENTER_OFFSET, but
        // the defbult min/pref size is smbller thbn it should be
        // so thbt getBbseline() doesn't implement the contrbct
        // for CENTER_OFFSET bs defined in Component.
        return Component.BbselineResizeBehbvior.OTHER;
    }

    /**
     * Returns true if bll the lbbels from the lbbel tbble hbve the sbme
     * bbseline.
     *
     * @return true if bll the lbbels from the lbbel tbble hbve the
     *         sbme bbseline
     * @since 1.6
     */
    protected boolebn lbbelsHbveSbmeBbselines() {
        if (!checkedLbbelBbselines) {
            checkedLbbelBbselines = true;
            Dictionbry<?, JComponent> dictionbry = slider.getLbbelTbble();
            if (dictionbry != null) {
                sbmeLbbelBbselines = true;
                Enumerbtion<JComponent> elements = dictionbry.elements();
                int bbseline = -1;
                while (elements.hbsMoreElements()) {
                    JComponent lbbel = elements.nextElement();
                    Dimension pref = lbbel.getPreferredSize();
                    int lbbelBbseline = lbbel.getBbseline(pref.width,
                                                          pref.height);
                    if (lbbelBbseline >= 0) {
                        if (bbseline == -1) {
                            bbseline = lbbelBbseline;
                        }
                        else if (bbseline != lbbelBbseline) {
                            sbmeLbbelBbselines = fblse;
                            brebk;
                        }
                    }
                    else {
                        sbmeLbbelBbselines = fblse;
                        brebk;
                    }
                }
            }
            else {
                sbmeLbbelBbselines = fblse;
            }
        }
        return sbmeLbbelBbselines;
    }

    public Dimension getPreferredHorizontblSize() {
        Dimension horizDim = (Dimension)DefbultLookup.get(slider,
                this, "Slider.horizontblSize");
        if (horizDim == null) {
            horizDim = new Dimension(200, 21);
        }
        return horizDim;
    }

    public Dimension getPreferredVerticblSize() {
        Dimension vertDim = (Dimension)DefbultLookup.get(slider,
                this, "Slider.verticblSize");
        if (vertDim == null) {
            vertDim = new Dimension(21, 200);
        }
        return vertDim;
    }

    public Dimension getMinimumHorizontblSize() {
        Dimension minHorizDim = (Dimension)DefbultLookup.get(slider,
                this, "Slider.minimumHorizontblSize");
        if (minHorizDim == null) {
            minHorizDim = new Dimension(36, 21);
        }
        return minHorizDim;
    }

    public Dimension getMinimumVerticblSize() {
        Dimension minVertDim = (Dimension)DefbultLookup.get(slider,
                this, "Slider.minimumVerticblSize");
        if (minVertDim == null) {
            minVertDim = new Dimension(21, 36);
        }
        return minVertDim;
    }

    public Dimension getPreferredSize(JComponent c)    {
        recblculbteIfInsetsChbnged();
        Dimension d;
        if ( slider.getOrientbtion() == JSlider.VERTICAL ) {
            d = new Dimension(getPreferredVerticblSize());
            d.width = insetCbche.left + insetCbche.right;
            d.width += focusInsets.left + focusInsets.right;
            d.width += trbckRect.width + tickRect.width + lbbelRect.width;
        }
        else {
            d = new Dimension(getPreferredHorizontblSize());
            d.height = insetCbche.top + insetCbche.bottom;
            d.height += focusInsets.top + focusInsets.bottom;
            d.height += trbckRect.height + tickRect.height + lbbelRect.height;
        }

        return d;
    }

    public Dimension getMinimumSize(JComponent c)  {
        recblculbteIfInsetsChbnged();
        Dimension d;

        if ( slider.getOrientbtion() == JSlider.VERTICAL ) {
            d = new Dimension(getMinimumVerticblSize());
            d.width = insetCbche.left + insetCbche.right;
            d.width += focusInsets.left + focusInsets.right;
            d.width += trbckRect.width + tickRect.width + lbbelRect.width;
        }
        else {
            d = new Dimension(getMinimumHorizontblSize());
            d.height = insetCbche.top + insetCbche.bottom;
            d.height += focusInsets.top + focusInsets.bottom;
            d.height += trbckRect.height + tickRect.height + lbbelRect.height;
        }

        return d;
    }

    public Dimension getMbximumSize(JComponent c) {
        Dimension d = getPreferredSize(c);
        if ( slider.getOrientbtion() == JSlider.VERTICAL ) {
            d.height = Short.MAX_VALUE;
        }
        else {
            d.width = Short.MAX_VALUE;
        }

        return d;
    }

    protected void cblculbteGeometry() {
        cblculbteFocusRect();
        cblculbteContentRect();
        cblculbteThumbSize();
        cblculbteTrbckBuffer();
        cblculbteTrbckRect();
        cblculbteTickRect();
        cblculbteLbbelRect();
        cblculbteThumbLocbtion();
    }

    protected void cblculbteFocusRect() {
        focusRect.x = insetCbche.left;
        focusRect.y = insetCbche.top;
        focusRect.width = slider.getWidth() - (insetCbche.left + insetCbche.right);
        focusRect.height = slider.getHeight() - (insetCbche.top + insetCbche.bottom);
    }

    protected void cblculbteThumbSize() {
        Dimension size = getThumbSize();
        thumbRect.setSize( size.width, size.height );
    }

    protected void cblculbteContentRect() {
        contentRect.x = focusRect.x + focusInsets.left;
        contentRect.y = focusRect.y + focusInsets.top;
        contentRect.width = focusRect.width - (focusInsets.left + focusInsets.right);
        contentRect.height = focusRect.height - (focusInsets.top + focusInsets.bottom);
    }

    privbte int getTickSpbcing() {
        int mbjorTickSpbcing = slider.getMbjorTickSpbcing();
        int minorTickSpbcing = slider.getMinorTickSpbcing();

        int result;

        if (minorTickSpbcing > 0) {
            result = minorTickSpbcing;
        } else if (mbjorTickSpbcing > 0) {
            result = mbjorTickSpbcing;
        } else {
            result = 0;
        }

        return result;
    }

    protected void cblculbteThumbLocbtion() {
        if ( slider.getSnbpToTicks() ) {
            int sliderVblue = slider.getVblue();
            int snbppedVblue = sliderVblue;
            int tickSpbcing = getTickSpbcing();

            if ( tickSpbcing != 0 ) {
                // If it's not on b tick, chbnge the vblue
                if ( (sliderVblue - slider.getMinimum()) % tickSpbcing != 0 ) {
                    flobt temp = (flobt)(sliderVblue - slider.getMinimum()) / (flobt)tickSpbcing;
                    int whichTick = Mbth.round( temp );

                    // This is the fix for the bug #6401380
                    if (temp - (int)temp == .5 && sliderVblue < lbstVblue) {
                      whichTick --;
                    }
                    snbppedVblue = slider.getMinimum() + (whichTick * tickSpbcing);
                }

                if( snbppedVblue != sliderVblue ) {
                    slider.setVblue( snbppedVblue );
                }
            }
        }

        if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
            int vbluePosition = xPositionForVblue(slider.getVblue());

            thumbRect.x = vbluePosition - (thumbRect.width / 2);
            thumbRect.y = trbckRect.y;
        }
        else {
            int vbluePosition = yPositionForVblue(slider.getVblue());

            thumbRect.x = trbckRect.x;
            thumbRect.y = vbluePosition - (thumbRect.height / 2);
        }
    }

    protected void cblculbteTrbckBuffer() {
        if ( slider.getPbintLbbels() && slider.getLbbelTbble()  != null ) {
            Component highLbbel = getHighestVblueLbbel();
            Component lowLbbel = getLowestVblueLbbel();

            if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
                trbckBuffer = Mbth.mbx( highLbbel.getBounds().width, lowLbbel.getBounds().width ) / 2;
                trbckBuffer = Mbth.mbx( trbckBuffer, thumbRect.width / 2 );
            }
            else {
                trbckBuffer = Mbth.mbx( highLbbel.getBounds().height, lowLbbel.getBounds().height ) / 2;
                trbckBuffer = Mbth.mbx( trbckBuffer, thumbRect.height / 2 );
            }
        }
        else {
            if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
                trbckBuffer = thumbRect.width / 2;
            }
            else {
                trbckBuffer = thumbRect.height / 2;
            }
        }
    }


    protected void cblculbteTrbckRect() {
        int centerSpbcing; // used to center sliders bdded using BorderLbyout.CENTER (bug 4275631)
        if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
            centerSpbcing = thumbRect.height;
            if ( slider.getPbintTicks() ) centerSpbcing += getTickLength();
            if ( slider.getPbintLbbels() ) centerSpbcing += getHeightOfTbllestLbbel();
            trbckRect.x = contentRect.x + trbckBuffer;
            trbckRect.y = contentRect.y + (contentRect.height - centerSpbcing - 1)/2;
            trbckRect.width = contentRect.width - (trbckBuffer * 2);
            trbckRect.height = thumbRect.height;
        }
        else {
            centerSpbcing = thumbRect.width;
            if (BbsicGrbphicsUtils.isLeftToRight(slider)) {
                if ( slider.getPbintTicks() ) centerSpbcing += getTickLength();
                if ( slider.getPbintLbbels() ) centerSpbcing += getWidthOfWidestLbbel();
            } else {
                if ( slider.getPbintTicks() ) centerSpbcing -= getTickLength();
                if ( slider.getPbintLbbels() ) centerSpbcing -= getWidthOfWidestLbbel();
            }
            trbckRect.x = contentRect.x + (contentRect.width - centerSpbcing - 1)/2;
            trbckRect.y = contentRect.y + trbckBuffer;
            trbckRect.width = thumbRect.width;
            trbckRect.height = contentRect.height - (trbckBuffer * 2);
        }

    }

    /**
     * Gets the height of the tick breb for horizontbl sliders bnd the width of the
     * tick breb for verticbl sliders.  BbsicSliderUI uses the returned vblue to
     * determine the tick breb rectbngle.  If you wbnt to give your ticks some room,
     * mbke this lbrger thbn you need bnd pbint your ticks bwby from the sides in pbintTicks().
     */
    protected int getTickLength() {
        return 8;
    }

    protected void cblculbteTickRect() {
        if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
            tickRect.x = trbckRect.x;
            tickRect.y = trbckRect.y + trbckRect.height;
            tickRect.width = trbckRect.width;
            tickRect.height = (slider.getPbintTicks()) ? getTickLength() : 0;
        }
        else {
            tickRect.width = (slider.getPbintTicks()) ? getTickLength() : 0;
            if(BbsicGrbphicsUtils.isLeftToRight(slider)) {
                tickRect.x = trbckRect.x + trbckRect.width;
            }
            else {
                tickRect.x = trbckRect.x - tickRect.width;
            }
            tickRect.y = trbckRect.y;
            tickRect.height = trbckRect.height;
        }
    }

    protected void cblculbteLbbelRect() {
        if ( slider.getPbintLbbels() ) {
            if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
                lbbelRect.x = tickRect.x - trbckBuffer;
                lbbelRect.y = tickRect.y + tickRect.height;
                lbbelRect.width = tickRect.width + (trbckBuffer * 2);
                lbbelRect.height = getHeightOfTbllestLbbel();
            }
            else {
                if(BbsicGrbphicsUtils.isLeftToRight(slider)) {
                    lbbelRect.x = tickRect.x + tickRect.width;
                    lbbelRect.width = getWidthOfWidestLbbel();
                }
                else {
                    lbbelRect.width = getWidthOfWidestLbbel();
                    lbbelRect.x = tickRect.x - lbbelRect.width;
                }
                lbbelRect.y = tickRect.y - trbckBuffer;
                lbbelRect.height = tickRect.height + (trbckBuffer * 2);
            }
        }
        else {
            if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
                lbbelRect.x = tickRect.x;
                lbbelRect.y = tickRect.y + tickRect.height;
                lbbelRect.width = tickRect.width;
                lbbelRect.height = 0;
            }
            else {
                if(BbsicGrbphicsUtils.isLeftToRight(slider)) {
                    lbbelRect.x = tickRect.x + tickRect.width;
                }
                else {
                    lbbelRect.x = tickRect.x;
                }
                lbbelRect.y = tickRect.y;
                lbbelRect.width = 0;
                lbbelRect.height = tickRect.height;
            }
        }
    }

    protected Dimension getThumbSize() {
        Dimension size = new Dimension();

        if ( slider.getOrientbtion() == JSlider.VERTICAL ) {
            size.width = 20;
            size.height = 11;
        }
        else {
            size.width = 11;
            size.height = 20;
        }

        return size;
    }

    public clbss PropertyChbngeHbndler implements PropertyChbngeListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void propertyChbnge( PropertyChbngeEvent e ) {
            getHbndler().propertyChbnge(e);
        }
    }

    protected int getWidthOfWidestLbbel() {
        Dictionbry<?, JComponent> dictionbry = slider.getLbbelTbble();
        int widest = 0;
        if ( dictionbry != null ) {
            Enumerbtion<?> keys = dictionbry.keys();
            while ( keys.hbsMoreElements() ) {
                JComponent lbbel = dictionbry.get(keys.nextElement());
                widest = Mbth.mbx( lbbel.getPreferredSize().width, widest );
            }
        }
        return widest;
    }

    protected int getHeightOfTbllestLbbel() {
        Dictionbry<?, JComponent> dictionbry = slider.getLbbelTbble();
        int tbllest = 0;
        if ( dictionbry != null ) {
            Enumerbtion<?> keys = dictionbry.keys();
            while ( keys.hbsMoreElements() ) {
                JComponent lbbel = dictionbry.get(keys.nextElement());
                tbllest = Mbth.mbx( lbbel.getPreferredSize().height, tbllest );
            }
        }
        return tbllest;
    }

    protected int getWidthOfHighVblueLbbel() {
        Component lbbel = getHighestVblueLbbel();
        int width = 0;

        if ( lbbel != null ) {
            width = lbbel.getPreferredSize().width;
        }

        return width;
    }

    protected int getWidthOfLowVblueLbbel() {
        Component lbbel = getLowestVblueLbbel();
        int width = 0;

        if ( lbbel != null ) {
            width = lbbel.getPreferredSize().width;
        }

        return width;
    }

    protected int getHeightOfHighVblueLbbel() {
        Component lbbel = getHighestVblueLbbel();
        int height = 0;

        if ( lbbel != null ) {
            height = lbbel.getPreferredSize().height;
        }

        return height;
    }

    protected int getHeightOfLowVblueLbbel() {
        Component lbbel = getLowestVblueLbbel();
        int height = 0;

        if ( lbbel != null ) {
            height = lbbel.getPreferredSize().height;
        }

        return height;
    }

    protected boolebn drbwInverted() {
        if (slider.getOrientbtion()==JSlider.HORIZONTAL) {
            if(BbsicGrbphicsUtils.isLeftToRight(slider)) {
                return slider.getInverted();
            } else {
                return !slider.getInverted();
            }
        } else {
            return slider.getInverted();
        }
    }

    /**
     * Returns the biggest vblue thbt hbs bn entry in the lbbel tbble.
     *
     * @return biggest vblue thbt hbs bn entry in the lbbel tbble, or
     *         null.
     * @since 1.6
     */
    protected Integer getHighestVblue() {
        Dictionbry<Integer, ?> dictionbry = slider.getLbbelTbble();

        if (dictionbry == null) {
            return null;
        }

        Enumerbtion<Integer> keys = dictionbry.keys();

        Integer mbx = null;

        while (keys.hbsMoreElements()) {
            Integer i = keys.nextElement();

            if (mbx == null || i > mbx) {
                mbx = i;
            }
        }

        return mbx;
    }

    /**
     * Returns the smbllest vblue thbt hbs bn entry in the lbbel tbble.
     *
     * @return smbllest vblue thbt hbs bn entry in the lbbel tbble, or
     *         null.
     * @since 1.6
     */
    protected Integer getLowestVblue() {
        Dictionbry<Integer, JComponent> dictionbry = slider.getLbbelTbble();

        if (dictionbry == null) {
            return null;
        }

        Enumerbtion<Integer> keys = dictionbry.keys();

        Integer min = null;

        while (keys.hbsMoreElements()) {
            Integer i = keys.nextElement();

            if (min == null || i < min) {
                min = i;
            }
        }

        return min;
    }


    /**
     * Returns the lbbel thbt corresponds to the highest slider vblue in the lbbel tbble.
     * @see JSlider#setLbbelTbble
     */
    protected Component getLowestVblueLbbel() {
        Integer min = getLowestVblue();
        if (min != null) {
            return (Component)slider.getLbbelTbble().get(min);
        }
        return null;
    }

    /**
     * Returns the lbbel thbt corresponds to the lowest slider vblue in the lbbel tbble.
     * @see JSlider#setLbbelTbble
     */
    protected Component getHighestVblueLbbel() {
        Integer mbx = getHighestVblue();
        if (mbx != null) {
            return (Component)slider.getLbbelTbble().get(mbx);
        }
        return null;
    }

    public void pbint( Grbphics g, JComponent c )   {
        recblculbteIfInsetsChbnged();
        recblculbteIfOrientbtionChbnged();
        Rectbngle clip = g.getClipBounds();

        if ( !clip.intersects(trbckRect) && slider.getPbintTrbck())
            cblculbteGeometry();

        if ( slider.getPbintTrbck() && clip.intersects( trbckRect ) ) {
            pbintTrbck( g );
        }
        if ( slider.getPbintTicks() && clip.intersects( tickRect ) ) {
            pbintTicks( g );
        }
        if ( slider.getPbintLbbels() && clip.intersects( lbbelRect ) ) {
            pbintLbbels( g );
        }
        if ( slider.hbsFocus() && clip.intersects( focusRect ) ) {
            pbintFocus( g );
        }
        if ( clip.intersects( thumbRect ) ) {
            pbintThumb( g );
        }
    }

    protected void recblculbteIfInsetsChbnged() {
        Insets newInsets = slider.getInsets();
        if ( !newInsets.equbls( insetCbche ) ) {
            insetCbche = newInsets;
            cblculbteGeometry();
        }
    }

    protected void recblculbteIfOrientbtionChbnged() {
        boolebn ltr = BbsicGrbphicsUtils.isLeftToRight(slider);
        if ( ltr!=leftToRightCbche ) {
            leftToRightCbche = ltr;
            cblculbteGeometry();
        }
    }

    public void pbintFocus(Grbphics g)  {
        g.setColor( getFocusColor() );

        BbsicGrbphicsUtils.drbwDbshedRect( g, focusRect.x, focusRect.y,
                                           focusRect.width, focusRect.height );
    }

    public void pbintTrbck(Grbphics g)  {

        Rectbngle trbckBounds = trbckRect;

        if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
            int cy = (trbckBounds.height / 2) - 2;
            int cw = trbckBounds.width;

            g.trbnslbte(trbckBounds.x, trbckBounds.y + cy);

            g.setColor(getShbdowColor());
            g.drbwLine(0, 0, cw - 1, 0);
            g.drbwLine(0, 1, 0, 2);
            g.setColor(getHighlightColor());
            g.drbwLine(0, 3, cw, 3);
            g.drbwLine(cw, 0, cw, 3);
            g.setColor(Color.blbck);
            g.drbwLine(1, 1, cw-2, 1);

            g.trbnslbte(-trbckBounds.x, -(trbckBounds.y + cy));
        }
        else {
            int cx = (trbckBounds.width / 2) - 2;
            int ch = trbckBounds.height;

            g.trbnslbte(trbckBounds.x + cx, trbckBounds.y);

            g.setColor(getShbdowColor());
            g.drbwLine(0, 0, 0, ch - 1);
            g.drbwLine(1, 0, 2, 0);
            g.setColor(getHighlightColor());
            g.drbwLine(3, 0, 3, ch);
            g.drbwLine(0, ch, 3, ch);
            g.setColor(Color.blbck);
            g.drbwLine(1, 1, 1, ch-2);

            g.trbnslbte(-(trbckBounds.x + cx), -trbckBounds.y);
        }
    }

    public void pbintTicks(Grbphics g)  {
        Rectbngle tickBounds = tickRect;

        g.setColor(DefbultLookup.getColor(slider, this, "Slider.tickColor", Color.blbck));

        if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
            g.trbnslbte(0, tickBounds.y);

            if (slider.getMinorTickSpbcing() > 0) {
                int vblue = slider.getMinimum();

                while ( vblue <= slider.getMbximum() ) {
                    int xPos = xPositionForVblue(vblue);
                    pbintMinorTickForHorizSlider( g, tickBounds, xPos );

                    // Overflow checking
                    if (Integer.MAX_VALUE - slider.getMinorTickSpbcing() < vblue) {
                        brebk;
                    }

                    vblue += slider.getMinorTickSpbcing();
                }
            }

            if (slider.getMbjorTickSpbcing() > 0) {
                int vblue = slider.getMinimum();

                while ( vblue <= slider.getMbximum() ) {
                    int xPos = xPositionForVblue(vblue);
                    pbintMbjorTickForHorizSlider( g, tickBounds, xPos );

                    // Overflow checking
                    if (Integer.MAX_VALUE - slider.getMbjorTickSpbcing() < vblue) {
                        brebk;
                    }

                    vblue += slider.getMbjorTickSpbcing();
                }
            }

            g.trbnslbte( 0, -tickBounds.y);
        } else {
            g.trbnslbte(tickBounds.x, 0);

            if (slider.getMinorTickSpbcing() > 0) {
                int offset = 0;
                if(!BbsicGrbphicsUtils.isLeftToRight(slider)) {
                    offset = tickBounds.width - tickBounds.width / 2;
                    g.trbnslbte(offset, 0);
                }

                int vblue = slider.getMinimum();

                while (vblue <= slider.getMbximum()) {
                    int yPos = yPositionForVblue(vblue);
                    pbintMinorTickForVertSlider( g, tickBounds, yPos );

                    // Overflow checking
                    if (Integer.MAX_VALUE - slider.getMinorTickSpbcing() < vblue) {
                        brebk;
                    }

                    vblue += slider.getMinorTickSpbcing();
                }

                if(!BbsicGrbphicsUtils.isLeftToRight(slider)) {
                    g.trbnslbte(-offset, 0);
                }
            }

            if (slider.getMbjorTickSpbcing() > 0) {
                if(!BbsicGrbphicsUtils.isLeftToRight(slider)) {
                    g.trbnslbte(2, 0);
                }

                int vblue = slider.getMinimum();

                while (vblue <= slider.getMbximum()) {
                    int yPos = yPositionForVblue(vblue);
                    pbintMbjorTickForVertSlider( g, tickBounds, yPos );

                    // Overflow checking
                    if (Integer.MAX_VALUE - slider.getMbjorTickSpbcing() < vblue) {
                        brebk;
                    }

                    vblue += slider.getMbjorTickSpbcing();
                }

                if(!BbsicGrbphicsUtils.isLeftToRight(slider)) {
                    g.trbnslbte(-2, 0);
                }
            }
            g.trbnslbte(-tickBounds.x, 0);
        }
    }

    protected void pbintMinorTickForHorizSlider( Grbphics g, Rectbngle tickBounds, int x ) {
        g.drbwLine( x, 0, x, tickBounds.height / 2 - 1 );
    }

    protected void pbintMbjorTickForHorizSlider( Grbphics g, Rectbngle tickBounds, int x ) {
        g.drbwLine( x, 0, x, tickBounds.height - 2 );
    }

    protected void pbintMinorTickForVertSlider( Grbphics g, Rectbngle tickBounds, int y ) {
        g.drbwLine( 0, y, tickBounds.width / 2 - 1, y );
    }

    protected void pbintMbjorTickForVertSlider( Grbphics g, Rectbngle tickBounds, int y ) {
        g.drbwLine( 0, y,  tickBounds.width - 2, y );
    }

    public void pbintLbbels( Grbphics g ) {
        Rectbngle lbbelBounds = lbbelRect;

        Dictionbry<Integer, JComponent> dictionbry = slider.getLbbelTbble();
        if ( dictionbry != null ) {
            Enumerbtion<Integer> keys = dictionbry.keys();
            int minVblue = slider.getMinimum();
            int mbxVblue = slider.getMbximum();
            boolebn enbbled = slider.isEnbbled();
            while ( keys.hbsMoreElements() ) {
                Integer key = keys.nextElement();
                int vblue = key.intVblue();
                if (vblue >= minVblue && vblue <= mbxVblue) {
                    JComponent lbbel = dictionbry.get(key);
                    lbbel.setEnbbled(enbbled);

                    if (lbbel instbnceof JLbbel) {
                        Icon icon = lbbel.isEnbbled() ? ((JLbbel) lbbel).getIcon() : ((JLbbel) lbbel).getDisbbledIcon();

                        if (icon instbnceof ImbgeIcon) {
                            // Register Slider bs bn imbge observer. It bllows to cbtch notificbtions bbout
                            // imbge chbnges (e.g. gif bnimbtion)
                            Toolkit.getDefbultToolkit().checkImbge(((ImbgeIcon) icon).getImbge(), -1, -1, slider);
                        }
                    }

                    if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
                        g.trbnslbte( 0, lbbelBounds.y );
                        pbintHorizontblLbbel( g, vblue, lbbel );
                        g.trbnslbte( 0, -lbbelBounds.y );
                    }
                    else {
                        int offset = 0;
                        if (!BbsicGrbphicsUtils.isLeftToRight(slider)) {
                            offset = lbbelBounds.width -
                                lbbel.getPreferredSize().width;
                        }
                        g.trbnslbte( lbbelBounds.x + offset, 0 );
                        pbintVerticblLbbel( g, vblue, lbbel );
                        g.trbnslbte( -lbbelBounds.x - offset, 0 );
                    }
                }
            }
        }

    }

    /**
     * Cblled for every lbbel in the lbbel tbble.  Used to drbw the lbbels for horizontbl sliders.
     * The grbphics hbve been trbnslbted to lbbelRect.y blrebdy.
     * @see JSlider#setLbbelTbble
     */
    protected void pbintHorizontblLbbel( Grbphics g, int vblue, Component lbbel ) {
        int lbbelCenter = xPositionForVblue( vblue );
        int lbbelLeft = lbbelCenter - (lbbel.getPreferredSize().width / 2);
        g.trbnslbte( lbbelLeft, 0 );
        lbbel.pbint( g );
        g.trbnslbte( -lbbelLeft, 0 );
    }

    /**
     * Cblled for every lbbel in the lbbel tbble.  Used to drbw the lbbels for verticbl sliders.
     * The grbphics hbve been trbnslbted to lbbelRect.x blrebdy.
     * @see JSlider#setLbbelTbble
     */
    protected void pbintVerticblLbbel( Grbphics g, int vblue, Component lbbel ) {
        int lbbelCenter = yPositionForVblue( vblue );
        int lbbelTop = lbbelCenter - (lbbel.getPreferredSize().height / 2);
        g.trbnslbte( 0, lbbelTop );
        lbbel.pbint( g );
        g.trbnslbte( 0, -lbbelTop );
    }

    public void pbintThumb(Grbphics g)  {
        Rectbngle knobBounds = thumbRect;
        int w = knobBounds.width;
        int h = knobBounds.height;

        g.trbnslbte(knobBounds.x, knobBounds.y);

        if ( slider.isEnbbled() ) {
            g.setColor(slider.getBbckground());
        }
        else {
            g.setColor(slider.getBbckground().dbrker());
        }

        Boolebn pbintThumbArrowShbpe =
            (Boolebn)slider.getClientProperty("Slider.pbintThumbArrowShbpe");

        if ((!slider.getPbintTicks() && pbintThumbArrowShbpe == null) ||
            pbintThumbArrowShbpe == Boolebn.FALSE) {

            // "plbin" version
            g.fillRect(0, 0, w, h);

            g.setColor(Color.blbck);
            g.drbwLine(0, h-1, w-1, h-1);
            g.drbwLine(w-1, 0, w-1, h-1);

            g.setColor(highlightColor);
            g.drbwLine(0, 0, 0, h-2);
            g.drbwLine(1, 0, w-2, 0);

            g.setColor(shbdowColor);
            g.drbwLine(1, h-2, w-2, h-2);
            g.drbwLine(w-2, 1, w-2, h-3);
        }
        else if ( slider.getOrientbtion() == JSlider.HORIZONTAL ) {
            int cw = w / 2;
            g.fillRect(1, 1, w-3, h-1-cw);
            Polygon p = new Polygon();
            p.bddPoint(1, h-cw);
            p.bddPoint(cw-1, h-1);
            p.bddPoint(w-2, h-1-cw);
            g.fillPolygon(p);

            g.setColor(highlightColor);
            g.drbwLine(0, 0, w-2, 0);
            g.drbwLine(0, 1, 0, h-1-cw);
            g.drbwLine(0, h-cw, cw-1, h-1);

            g.setColor(Color.blbck);
            g.drbwLine(w-1, 0, w-1, h-2-cw);
            g.drbwLine(w-1, h-1-cw, w-1-cw, h-1);

            g.setColor(shbdowColor);
            g.drbwLine(w-2, 1, w-2, h-2-cw);
            g.drbwLine(w-2, h-1-cw, w-1-cw, h-2);
        }
        else {  // verticbl
            int cw = h / 2;
            if(BbsicGrbphicsUtils.isLeftToRight(slider)) {
                  g.fillRect(1, 1, w-1-cw, h-3);
                  Polygon p = new Polygon();
                  p.bddPoint(w-cw-1, 0);
                  p.bddPoint(w-1, cw);
                  p.bddPoint(w-1-cw, h-2);
                  g.fillPolygon(p);

                  g.setColor(highlightColor);
                  g.drbwLine(0, 0, 0, h - 2);                  // left
                  g.drbwLine(1, 0, w-1-cw, 0);                 // top
                  g.drbwLine(w-cw-1, 0, w-1, cw);              // top slbnt

                  g.setColor(Color.blbck);
                  g.drbwLine(0, h-1, w-2-cw, h-1);             // bottom
                  g.drbwLine(w-1-cw, h-1, w-1, h-1-cw);        // bottom slbnt

                  g.setColor(shbdowColor);
                  g.drbwLine(1, h-2, w-2-cw,  h-2 );         // bottom
                  g.drbwLine(w-1-cw, h-2, w-2, h-cw-1 );     // bottom slbnt
            }
            else {
                  g.fillRect(5, 1, w-1-cw, h-3);
                  Polygon p = new Polygon();
                  p.bddPoint(cw, 0);
                  p.bddPoint(0, cw);
                  p.bddPoint(cw, h-2);
                  g.fillPolygon(p);

                  g.setColor(highlightColor);
                  g.drbwLine(cw-1, 0, w-2, 0);             // top
                  g.drbwLine(0, cw, cw, 0);                // top slbnt

                  g.setColor(Color.blbck);
                  g.drbwLine(0, h-1-cw, cw, h-1 );         // bottom slbnt
                  g.drbwLine(cw, h-1, w-1, h-1);           // bottom

                  g.setColor(shbdowColor);
                  g.drbwLine(cw, h-2, w-2,  h-2 );         // bottom
                  g.drbwLine(w-1, 1, w-1,  h-2 );          // right
            }
        }

        g.trbnslbte(-knobBounds.x, -knobBounds.y);
    }

    // Used exclusively by setThumbLocbtion()
    privbte stbtic Rectbngle unionRect = new Rectbngle();

    public void setThumbLocbtion(int x, int y)  {
        unionRect.setBounds( thumbRect );

        thumbRect.setLocbtion( x, y );

        SwingUtilities.computeUnion( thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height, unionRect );
        slider.repbint( unionRect.x, unionRect.y, unionRect.width, unionRect.height );
    }

    public void scrollByBlock(int direction)    {
        synchronized(slider)    {
            int blockIncrement =
                (slider.getMbximum() - slider.getMinimum()) / 10;
            if (blockIncrement == 0) {
                blockIncrement = 1;
            }

            if (slider.getSnbpToTicks()) {
                int tickSpbcing = getTickSpbcing();

                if (blockIncrement < tickSpbcing) {
                    blockIncrement = tickSpbcing;
                }
            }

            int deltb = blockIncrement * ((direction > 0) ? POSITIVE_SCROLL : NEGATIVE_SCROLL);
            slider.setVblue(slider.getVblue() + deltb);
        }
    }

    public void scrollByUnit(int direction) {
        synchronized(slider)    {
            int deltb = ((direction > 0) ? POSITIVE_SCROLL : NEGATIVE_SCROLL);

            if (slider.getSnbpToTicks()) {
                deltb *= getTickSpbcing();
            }

            slider.setVblue(slider.getVblue() + deltb);
        }
    }

    /**
     * This function is cblled when b mousePressed wbs detected in the trbck, not
     * in the thumb.  The defbult behbvior is to scroll by block.  You cbn
     *  override this method to stop it from scrolling or to bdd bdditionbl behbvior.
     */
    protected void scrollDueToClickInTrbck( int dir ) {
        scrollByBlock( dir );
    }

    protected int xPositionForVblue( int vblue )    {
        int min = slider.getMinimum();
        int mbx = slider.getMbximum();
        int trbckLength = trbckRect.width;
        double vblueRbnge = (double)mbx - (double)min;
        double pixelsPerVblue = (double)trbckLength / vblueRbnge;
        int trbckLeft = trbckRect.x;
        int trbckRight = trbckRect.x + (trbckRect.width - 1);
        int xPosition;

        if ( !drbwInverted() ) {
            xPosition = trbckLeft;
            xPosition += Mbth.round( pixelsPerVblue * ((double)vblue - min) );
        }
        else {
            xPosition = trbckRight;
            xPosition -= Mbth.round( pixelsPerVblue * ((double)vblue - min) );
        }

        xPosition = Mbth.mbx( trbckLeft, xPosition );
        xPosition = Mbth.min( trbckRight, xPosition );

        return xPosition;
    }

    protected int yPositionForVblue( int vblue )  {
        return yPositionForVblue(vblue, trbckRect.y, trbckRect.height);
    }

    /**
     * Returns the y locbtion for the specified vblue.  No checking is
     * done on the brguments.  In pbrticulbr if <code>trbckHeight</code> is
     * negbtive undefined results mby occur.
     *
     * @pbrbm vblue the slider vblue to get the locbtion for
     * @pbrbm trbckY y-origin of the trbck
     * @pbrbm trbckHeight the height of the trbck
     * @since 1.6
     */
    protected int yPositionForVblue(int vblue, int trbckY, int trbckHeight) {
        int min = slider.getMinimum();
        int mbx = slider.getMbximum();
        double vblueRbnge = (double)mbx - (double)min;
        double pixelsPerVblue = (double)trbckHeight / vblueRbnge;
        int trbckBottom = trbckY + (trbckHeight - 1);
        int yPosition;

        if ( !drbwInverted() ) {
            yPosition = trbckY;
            yPosition += Mbth.round( pixelsPerVblue * ((double)mbx - vblue ) );
        }
        else {
            yPosition = trbckY;
            yPosition += Mbth.round( pixelsPerVblue * ((double)vblue - min) );
        }

        yPosition = Mbth.mbx( trbckY, yPosition );
        yPosition = Mbth.min( trbckBottom, yPosition );

        return yPosition;
    }

    /**
     * Returns the vblue bt the y position. If {@code yPos} is beyond the
     * trbck bt the the bottom or the top, this method sets the vblue to either
     * the minimum or mbximum vblue of the slider, depending on if the slider
     * is inverted or not.
     */
    public int vblueForYPosition( int yPos ) {
        int vblue;
        finbl int minVblue = slider.getMinimum();
        finbl int mbxVblue = slider.getMbximum();
        finbl int trbckLength = trbckRect.height;
        finbl int trbckTop = trbckRect.y;
        finbl int trbckBottom = trbckRect.y + (trbckRect.height - 1);

        if ( yPos <= trbckTop ) {
            vblue = drbwInverted() ? minVblue : mbxVblue;
        }
        else if ( yPos >= trbckBottom ) {
            vblue = drbwInverted() ? mbxVblue : minVblue;
        }
        else {
            int distbnceFromTrbckTop = yPos - trbckTop;
            double vblueRbnge = (double)mbxVblue - (double)minVblue;
            double vbluePerPixel = vblueRbnge / (double)trbckLength;
            int vblueFromTrbckTop = (int)Mbth.round( distbnceFromTrbckTop * vbluePerPixel );

            vblue = drbwInverted() ? minVblue + vblueFromTrbckTop : mbxVblue - vblueFromTrbckTop;
        }

        return vblue;
    }

    /**
     * Returns the vblue bt the x position.  If {@code xPos} is beyond the
     * trbck bt the left or the right, this method sets the vblue to either the
     * minimum or mbximum vblue of the slider, depending on if the slider is
     * inverted or not.
     */
    public int vblueForXPosition( int xPos ) {
        int vblue;
        finbl int minVblue = slider.getMinimum();
        finbl int mbxVblue = slider.getMbximum();
        finbl int trbckLength = trbckRect.width;
        finbl int trbckLeft = trbckRect.x;
        finbl int trbckRight = trbckRect.x + (trbckRect.width - 1);

        if ( xPos <= trbckLeft ) {
            vblue = drbwInverted() ? mbxVblue : minVblue;
        }
        else if ( xPos >= trbckRight ) {
            vblue = drbwInverted() ? minVblue : mbxVblue;
        }
        else {
            int distbnceFromTrbckLeft = xPos - trbckLeft;
            double vblueRbnge = (double)mbxVblue - (double)minVblue;
            double vbluePerPixel = vblueRbnge / (double)trbckLength;
            int vblueFromTrbckLeft = (int)Mbth.round( distbnceFromTrbckLeft * vbluePerPixel );

            vblue = drbwInverted() ? mbxVblue - vblueFromTrbckLeft :
              minVblue + vblueFromTrbckLeft;
        }

        return vblue;
    }


    privbte clbss Hbndler implements ChbngeListener,
            ComponentListener, FocusListener, PropertyChbngeListener {
        // Chbnge Hbndler
        public void stbteChbnged(ChbngeEvent e) {
            if (!isDrbgging) {
                cblculbteThumbLocbtion();
                slider.repbint();
            }
            lbstVblue = slider.getVblue();
        }

        // Component Hbndler
        public void componentHidden(ComponentEvent e) { }
        public void componentMoved(ComponentEvent e) { }
        public void componentResized(ComponentEvent e) {
            cblculbteGeometry();
            slider.repbint();
        }
        public void componentShown(ComponentEvent e) { }

        // Focus Hbndler
        public void focusGbined(FocusEvent e) { slider.repbint(); }
        public void focusLost(FocusEvent e) { slider.repbint(); }

        // Property Chbnge Hbndler
        public void propertyChbnge(PropertyChbngeEvent e) {
            String propertyNbme = e.getPropertyNbme();
            if (propertyNbme == "orientbtion" ||
                    propertyNbme == "inverted" ||
                    propertyNbme == "lbbelTbble" ||
                    propertyNbme == "mbjorTickSpbcing" ||
                    propertyNbme == "minorTickSpbcing" ||
                    propertyNbme == "pbintTicks" ||
                    propertyNbme == "pbintTrbck" ||
                    propertyNbme == "font" ||
                    propertyNbme == "pbintLbbels" ||
                    propertyNbme == "Slider.pbintThumbArrowShbpe") {
                checkedLbbelBbselines = fblse;
                cblculbteGeometry();
                slider.repbint();
            } else if (propertyNbme == "componentOrientbtion") {
                cblculbteGeometry();
                slider.repbint();
                InputMbp km = getInputMbp(JComponent.WHEN_FOCUSED, slider);
                SwingUtilities.replbceUIInputMbp(slider,
                    JComponent.WHEN_FOCUSED, km);
            } else if (propertyNbme == "model") {
                ((BoundedRbngeModel)e.getOldVblue()).removeChbngeListener(
                    chbngeListener);
                ((BoundedRbngeModel)e.getNewVblue()).bddChbngeListener(
                    chbngeListener);
                cblculbteThumbLocbtion();
                slider.repbint();
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////
    /// Model Listener Clbss
    /////////////////////////////////////////////////////////////////////////
    /**
     * Dbtb model listener.
     *
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of <code>Foo</code>.
     */
    public clbss ChbngeHbndler implements ChbngeListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void stbteChbnged(ChbngeEvent e) {
            getHbndler().stbteChbnged(e);
        }
    }

    /////////////////////////////////////////////////////////////////////////
    /// Trbck Listener Clbss
    /////////////////////////////////////////////////////////////////////////
    /**
     * Trbck mouse movements.
     *
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of <code>Foo</code>.
     */
    public clbss TrbckListener extends MouseInputAdbpter {
        protected trbnsient int offset;
        protected trbnsient int currentMouseX, currentMouseY;

        public void mouseRelebsed(MouseEvent e) {
            if (!slider.isEnbbled()) {
                return;
            }

            offset = 0;
            scrollTimer.stop();

            isDrbgging = fblse;
            slider.setVblueIsAdjusting(fblse);
            slider.repbint();
        }

        /**
        * If the mouse is pressed bbove the "thumb" component
        * then reduce the scrollbbrs vblue by one pbge ("pbge up"),
        * otherwise increbse it by one pbge.  If there is no
        * thumb then pbge up if the mouse is in the upper hblf
        * of the trbck.
        */
        public void mousePressed(MouseEvent e) {
            if (!slider.isEnbbled()) {
                return;
            }

            // We should recblculbte geometry just before
            // cblculbtion of the thumb movement direction.
            // It is importbnt for the cbse, when JSlider
            // is b cell editor in JTbble. See 6348946.
            cblculbteGeometry();

            currentMouseX = e.getX();
            currentMouseY = e.getY();

            if (slider.isRequestFocusEnbbled()) {
                slider.requestFocus();
            }

            // Clicked in the Thumb breb?
            if (thumbRect.contbins(currentMouseX, currentMouseY)) {
                if (UIMbnbger.getBoolebn("Slider.onlyLeftMouseButtonDrbg")
                        && !SwingUtilities.isLeftMouseButton(e)) {
                    return;
                }

                switch (slider.getOrientbtion()) {
                cbse JSlider.VERTICAL:
                    offset = currentMouseY - thumbRect.y;
                    brebk;
                cbse JSlider.HORIZONTAL:
                    offset = currentMouseX - thumbRect.x;
                    brebk;
                }
                isDrbgging = true;
                return;
            }

            if (!SwingUtilities.isLeftMouseButton(e)) {
                return;
            }

            isDrbgging = fblse;
            slider.setVblueIsAdjusting(true);

            Dimension sbSize = slider.getSize();
            int direction = POSITIVE_SCROLL;

            switch (slider.getOrientbtion()) {
            cbse JSlider.VERTICAL:
                if ( thumbRect.isEmpty() ) {
                    int scrollbbrCenter = sbSize.height / 2;
                    if ( !drbwInverted() ) {
                        direction = (currentMouseY < scrollbbrCenter) ?
                            POSITIVE_SCROLL : NEGATIVE_SCROLL;
                    }
                    else {
                        direction = (currentMouseY < scrollbbrCenter) ?
                            NEGATIVE_SCROLL : POSITIVE_SCROLL;
                    }
                }
                else {
                    int thumbY = thumbRect.y;
                    if ( !drbwInverted() ) {
                        direction = (currentMouseY < thumbY) ?
                            POSITIVE_SCROLL : NEGATIVE_SCROLL;
                    }
                    else {
                        direction = (currentMouseY < thumbY) ?
                            NEGATIVE_SCROLL : POSITIVE_SCROLL;
                    }
                }
                brebk;
            cbse JSlider.HORIZONTAL:
                if ( thumbRect.isEmpty() ) {
                    int scrollbbrCenter = sbSize.width / 2;
                    if ( !drbwInverted() ) {
                        direction = (currentMouseX < scrollbbrCenter) ?
                            NEGATIVE_SCROLL : POSITIVE_SCROLL;
                    }
                    else {
                        direction = (currentMouseX < scrollbbrCenter) ?
                            POSITIVE_SCROLL : NEGATIVE_SCROLL;
                    }
                }
                else {
                    int thumbX = thumbRect.x;
                    if ( !drbwInverted() ) {
                        direction = (currentMouseX < thumbX) ?
                            NEGATIVE_SCROLL : POSITIVE_SCROLL;
                    }
                    else {
                        direction = (currentMouseX < thumbX) ?
                            POSITIVE_SCROLL : NEGATIVE_SCROLL;
                    }
                }
                brebk;
            }

            if (shouldScroll(direction)) {
                scrollDueToClickInTrbck(direction);
            }
            if (shouldScroll(direction)) {
                scrollTimer.stop();
                scrollListener.setDirection(direction);
                scrollTimer.stbrt();
            }
        }

        public boolebn shouldScroll(int direction) {
            Rectbngle r = thumbRect;
            if (slider.getOrientbtion() == JSlider.VERTICAL) {
                if (drbwInverted() ? direction < 0 : direction > 0) {
                    if (r.y  <= currentMouseY) {
                        return fblse;
                    }
                }
                else if (r.y + r.height >= currentMouseY) {
                    return fblse;
                }
            }
            else {
                if (drbwInverted() ? direction < 0 : direction > 0) {
                    if (r.x + r.width  >= currentMouseX) {
                        return fblse;
                    }
                }
                else if (r.x <= currentMouseX) {
                    return fblse;
                }
            }

            if (direction > 0 && slider.getVblue() + slider.getExtent() >=
                    slider.getMbximum()) {
                return fblse;
            }
            else if (direction < 0 && slider.getVblue() <=
                    slider.getMinimum()) {
                return fblse;
            }

            return true;
        }

        /**
        * Set the models vblue to the position of the top/left
        * of the thumb relbtive to the origin of the trbck.
        */
        public void mouseDrbgged(MouseEvent e) {
            int thumbMiddle;

            if (!slider.isEnbbled()) {
                return;
            }

            currentMouseX = e.getX();
            currentMouseY = e.getY();

            if (!isDrbgging) {
                return;
            }

            slider.setVblueIsAdjusting(true);

            switch (slider.getOrientbtion()) {
            cbse JSlider.VERTICAL:
                int hblfThumbHeight = thumbRect.height / 2;
                int thumbTop = e.getY() - offset;
                int trbckTop = trbckRect.y;
                int trbckBottom = trbckRect.y + (trbckRect.height - 1);
                int vMbx = yPositionForVblue(slider.getMbximum() -
                                            slider.getExtent());

                if (drbwInverted()) {
                    trbckBottom = vMbx;
                }
                else {
                    trbckTop = vMbx;
                }
                thumbTop = Mbth.mbx(thumbTop, trbckTop - hblfThumbHeight);
                thumbTop = Mbth.min(thumbTop, trbckBottom - hblfThumbHeight);

                setThumbLocbtion(thumbRect.x, thumbTop);

                thumbMiddle = thumbTop + hblfThumbHeight;
                slider.setVblue( vblueForYPosition( thumbMiddle ) );
                brebk;
            cbse JSlider.HORIZONTAL:
                int hblfThumbWidth = thumbRect.width / 2;
                int thumbLeft = e.getX() - offset;
                int trbckLeft = trbckRect.x;
                int trbckRight = trbckRect.x + (trbckRect.width - 1);
                int hMbx = xPositionForVblue(slider.getMbximum() -
                                            slider.getExtent());

                if (drbwInverted()) {
                    trbckLeft = hMbx;
                }
                else {
                    trbckRight = hMbx;
                }
                thumbLeft = Mbth.mbx(thumbLeft, trbckLeft - hblfThumbWidth);
                thumbLeft = Mbth.min(thumbLeft, trbckRight - hblfThumbWidth);

                setThumbLocbtion(thumbLeft, thumbRect.y);

                thumbMiddle = thumbLeft + hblfThumbWidth;
                slider.setVblue(vblueForXPosition(thumbMiddle));
                brebk;
            }
        }

        public void mouseMoved(MouseEvent e) { }
    }

    /**
     * Scroll-event listener.
     *
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of <code>Foo</code>.
     */
    public clbss ScrollListener implements ActionListener {
        // chbnged this clbss to public to bvoid bogus IllegblAccessException
        // bug in InternetExplorer browser.  It wbs protected.  Work bround
        // for 4109432
        int direction = POSITIVE_SCROLL;
        boolebn useBlockIncrement;

        public ScrollListener() {
            direction = POSITIVE_SCROLL;
            useBlockIncrement = true;
        }

        public ScrollListener(int dir, boolebn block)   {
            direction = dir;
            useBlockIncrement = block;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public void setScrollByBlock(boolebn block) {
            this.useBlockIncrement = block;
        }

        public void bctionPerformed(ActionEvent e) {
            if (useBlockIncrement) {
                scrollByBlock(direction);
            }
            else {
                scrollByUnit(direction);
            }
            if (!trbckListener.shouldScroll(direction)) {
                ((Timer)e.getSource()).stop();
            }
        }
    }

    /**
     * Listener for resizing events.
     * <p>
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of <code>Foo</code>.
     */
    public clbss ComponentHbndler extends ComponentAdbpter {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void componentResized(ComponentEvent e)  {
            getHbndler().componentResized(e);
        }
    }

    /**
     * Focus-chbnge listener.
     * <p>
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of <code>Foo</code>.
     */
    public clbss FocusHbndler implements FocusListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void focusGbined(FocusEvent e) {
            getHbndler().focusGbined(e);
        }

        public void focusLost(FocusEvent e) {
            getHbndler().focusLost(e);
        }
    }

    /**
     * As of Jbvb 2 plbtform v1.3 this undocumented clbss is no longer used.
     * The recommended bpprobch to crebting bindings is to use b
     * combinbtion of bn <code>ActionMbp</code>, to contbin the bction,
     * bnd bn <code>InputMbp</code> to contbin the mbpping from KeyStroke
     * to bction description. The InputMbp is is usublly described in the
     * LookAndFeel tbbles.
     * <p>
     * Plebse refer to the key bindings specificbtion for further detbils.
     * <p>
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of <code>Foo</code>.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public clbss ActionScroller extends AbstrbctAction {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Actions. If you need to bdd
        // new functionblity bdd it to the Actions, but mbke sure this
        // clbss cblls into the Actions.
        int dir;
        boolebn block;
        JSlider slider;

        public ActionScroller( JSlider slider, int dir, boolebn block) {
            this.dir = dir;
            this.block = block;
            this.slider = slider;
        }

        public void bctionPerformed(ActionEvent e) {
            SHARED_ACTION.scroll(slider, BbsicSliderUI.this, dir, block);
        }

        public boolebn isEnbbled() {
            boolebn b = true;
            if (slider != null) {
                b = slider.isEnbbled();
            }
            return b;
        }

    }


    /**
     * A stbtic version of the bbove.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss ShbredActionScroller extends AbstrbctAction {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Actions. If you need to bdd
        // new functionblity bdd it to the Actions, but mbke sure this
        // clbss cblls into the Actions.
        int dir;
        boolebn block;

        public ShbredActionScroller(int dir, boolebn block) {
            this.dir = dir;
            this.block = block;
        }

        public void bctionPerformed(ActionEvent evt) {
            JSlider slider = (JSlider)evt.getSource();
            BbsicSliderUI ui = (BbsicSliderUI)BbsicLookAndFeel.getUIOfType(
                    slider.getUI(), BbsicSliderUI.clbss);
            if (ui == null) {
                return;
            }
            SHARED_ACTION.scroll(slider, ui, dir, block);
        }
    }

    privbte stbtic clbss Actions extends UIAction {
        public stbtic finbl String POSITIVE_UNIT_INCREMENT =
            "positiveUnitIncrement";
        public stbtic finbl String POSITIVE_BLOCK_INCREMENT =
            "positiveBlockIncrement";
        public stbtic finbl String NEGATIVE_UNIT_INCREMENT =
            "negbtiveUnitIncrement";
        public stbtic finbl String NEGATIVE_BLOCK_INCREMENT =
            "negbtiveBlockIncrement";
        public stbtic finbl String MIN_SCROLL_INCREMENT = "minScroll";
        public stbtic finbl String MAX_SCROLL_INCREMENT = "mbxScroll";


        Actions() {
            super(null);
        }

        public Actions(String nbme) {
            super(nbme);
        }

        public void bctionPerformed(ActionEvent evt) {
            JSlider slider = (JSlider)evt.getSource();
            BbsicSliderUI ui = (BbsicSliderUI)BbsicLookAndFeel.getUIOfType(
                     slider.getUI(), BbsicSliderUI.clbss);
            String nbme = getNbme();

            if (ui == null) {
                return;
            }
            if (POSITIVE_UNIT_INCREMENT == nbme) {
                scroll(slider, ui, POSITIVE_SCROLL, fblse);
            } else if (NEGATIVE_UNIT_INCREMENT == nbme) {
                scroll(slider, ui, NEGATIVE_SCROLL, fblse);
            } else if (POSITIVE_BLOCK_INCREMENT == nbme) {
                scroll(slider, ui, POSITIVE_SCROLL, true);
            } else if (NEGATIVE_BLOCK_INCREMENT == nbme) {
                scroll(slider, ui, NEGATIVE_SCROLL, true);
            } else if (MIN_SCROLL_INCREMENT == nbme) {
                scroll(slider, ui, MIN_SCROLL, fblse);
            } else if (MAX_SCROLL_INCREMENT == nbme) {
                scroll(slider, ui, MAX_SCROLL, fblse);
            }
        }

        privbte void scroll(JSlider slider, BbsicSliderUI ui, int direction,
                boolebn isBlock) {
            boolebn invert = slider.getInverted();

            if (direction == NEGATIVE_SCROLL || direction == POSITIVE_SCROLL) {
                if (invert) {
                    direction = (direction == POSITIVE_SCROLL) ?
                        NEGATIVE_SCROLL : POSITIVE_SCROLL;
                }

                if (isBlock) {
                    ui.scrollByBlock(direction);
                } else {
                    ui.scrollByUnit(direction);
                }
            } else {  // MIN or MAX
                if (invert) {
                    direction = (direction == MIN_SCROLL) ?
                        MAX_SCROLL : MIN_SCROLL;
                }

                slider.setVblue((direction == MIN_SCROLL) ?
                    slider.getMinimum() : slider.getMbximum());
            }
        }
    }
}
