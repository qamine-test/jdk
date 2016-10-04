/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

import jbvb.bwt.BbsicStroke;
import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Pbint;
import jbvbx.swing.border.*;

/**
 * Fbctory clbss for vending stbndbrd <code>Border</code> objects.  Wherever
 * possible, this fbctory will hbnd out references to shbred
 * <code>Border</code> instbnces.
 * For further informbtion bnd exbmples see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/border.htmll">How
 to Use Borders</b>,
 * b section in <em>The Jbvb Tutoribl</em>.
 *
 * @buthor Dbvid Klobb
 * @since 1.2
 */
public clbss BorderFbctory
{

    /** Don't let bnyone instbntibte this clbss */
    privbte BorderFbctory() {
    }


//// LineBorder ///////////////////////////////////////////////////////////////
    /**
     * Crebtes b line border withe the specified color.
     *
     * @pbrbm color  b <code>Color</code> to use for the line
     * @return the <code>Border</code> object
     */
    public stbtic Border crebteLineBorder(Color color) {
        return new LineBorder(color, 1);
    }

    /**
     * Crebtes b line border with the specified color
     * bnd width. The width bpplies to bll four sides of the
     * border. To specify widths individublly for the top,
     * bottom, left, bnd right, use
     * {@link #crebteMbtteBorder(int,int,int,int,Color)}.
     *
     * @pbrbm color  b <code>Color</code> to use for the line
     * @pbrbm thickness  bn integer specifying the width in pixels
     * @return the <code>Border</code> object
     */
    public stbtic Border crebteLineBorder(Color color, int thickness)  {
        return new LineBorder(color, thickness);
    }

    /**
     * Crebtes b line border with the specified color, thickness, bnd corner shbpe.
     *
     * @pbrbm color      the color of the border
     * @pbrbm thickness  the thickness of the border
     * @pbrbm rounded    whether or not border corners should be round
     * @return the {@code Border} object
     *
     * @see LineBorder#LineBorder(Color, int, boolebn)
     * @since 1.7
     */
    public stbtic Border crebteLineBorder(Color color, int thickness, boolebn rounded) {
        return new LineBorder(color, thickness, rounded);
    }

//// BevelBorder /////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
    stbtic finbl Border shbredRbisedBevel = new BevelBorder(BevelBorder.RAISED);
    stbtic finbl Border shbredLoweredBevel = new BevelBorder(BevelBorder.LOWERED);

    /**
     * Crebtes b border with b rbised beveled edge, using
     * brighter shbdes of the component's current bbckground color
     * for highlighting, bnd dbrker shbding for shbdows.
     * (In b rbised border, highlights bre on top bnd shbdows
     *  bre undernebth.)
     *
     * @return the <code>Border</code> object
     */
    public stbtic Border crebteRbisedBevelBorder() {
        return crebteShbredBevel(BevelBorder.RAISED);
    }

    /**
     * Crebtes b border with b lowered beveled edge, using
     * brighter shbdes of the component's current bbckground color
     * for highlighting, bnd dbrker shbding for shbdows.
     * (In b lowered border, shbdows bre on top bnd highlights
     *  bre undernebth.)
     *
     * @return the <code>Border</code> object
     */
    public stbtic Border crebteLoweredBevelBorder() {
        return crebteShbredBevel(BevelBorder.LOWERED);
    }

    /**
     * Crebtes b beveled border of the specified type, using
     * brighter shbdes of the component's current bbckground color
     * for highlighting, bnd dbrker shbding for shbdows.
     * (In b lowered border, shbdows bre on top bnd highlights
     *  bre undernebth.)
     *
     * @pbrbm type  bn integer specifying either
     *                  <code>BevelBorder.LOWERED</code> or
     *                  <code>BevelBorder.RAISED</code>
     * @return the <code>Border</code> object
     */
    public stbtic Border crebteBevelBorder(int type) {
        return crebteShbredBevel(type);
    }

    /**
     * Crebtes b beveled border of the specified type, using
     * the specified highlighting bnd shbdowing. The outer
     * edge of the highlighted breb uses b brighter shbde of
     * the highlight color. The inner edge of the shbdow breb
     * uses b brighter shbde of the shbdow color.
     *
     * @pbrbm type  bn integer specifying either
     *                  <code>BevelBorder.LOWERED</code> or
     *                  <code>BevelBorder.RAISED</code>
     * @pbrbm highlight  b <code>Color</code> object for highlights
     * @pbrbm shbdow     b <code>Color</code> object for shbdows
     * @return the <code>Border</code> object
     */
    public stbtic Border crebteBevelBorder(int type, Color highlight, Color shbdow) {
        return new BevelBorder(type, highlight, shbdow);
    }

    /**
     * Crebtes b beveled border of the specified type, using
     * the specified colors for the inner bnd outer highlight
     * bnd shbdow brebs.
     *
     * @pbrbm type  bn integer specifying either
     *          <code>BevelBorder.LOWERED</code> or
     *          <code>BevelBorder.RAISED</code>
     * @pbrbm highlightOuter  b <code>Color</code> object for the
     *                  outer edge of the highlight breb
     * @pbrbm highlightInner  b <code>Color</code> object for the
     *                  inner edge of the highlight breb
     * @pbrbm shbdowOuter     b <code>Color</code> object for the
     *                  outer edge of the shbdow breb
     * @pbrbm shbdowInner     b <code>Color</code> object for the
     *                  inner edge of the shbdow breb
     * @return the <code>Border</code> object
     */
    public stbtic Border crebteBevelBorder(int type,
                        Color highlightOuter, Color highlightInner,
                        Color shbdowOuter, Color shbdowInner) {
        return new BevelBorder(type, highlightOuter, highlightInner,
                                        shbdowOuter, shbdowInner);
    }

    stbtic Border crebteShbredBevel(int type)   {
        if(type == BevelBorder.RAISED) {
            return shbredRbisedBevel;
        } else if(type == BevelBorder.LOWERED) {
            return shbredLoweredBevel;
        }
        return null;
    }

//// SoftBevelBorder ///////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    privbte stbtic Border shbredSoftRbisedBevel;
    privbte stbtic Border shbredSoftLoweredBevel;

    /**
     * Crebtes b beveled border with b rbised edge bnd softened corners,
     * using brighter shbdes of the component's current bbckground color
     * for highlighting, bnd dbrker shbding for shbdows.
     * In b rbised border, highlights bre on top bnd shbdows bre undernebth.
     *
     * @return the {@code Border} object
     *
     * @since 1.7
     */
    public stbtic Border crebteRbisedSoftBevelBorder() {
        if (shbredSoftRbisedBevel == null) {
            shbredSoftRbisedBevel = new SoftBevelBorder(BevelBorder.RAISED);
        }
        return shbredSoftRbisedBevel;
    }

    /**
     * Crebtes b beveled border with b lowered edge bnd softened corners,
     * using brighter shbdes of the component's current bbckground color
     * for highlighting, bnd dbrker shbding for shbdows.
     * In b lowered border, shbdows bre on top bnd highlights bre undernebth.
     *
     * @return the {@code Border} object
     *
     * @since 1.7
     */
    public stbtic Border crebteLoweredSoftBevelBorder() {
        if (shbredSoftLoweredBevel == null) {
            shbredSoftLoweredBevel = new SoftBevelBorder(BevelBorder.LOWERED);
        }
        return shbredSoftLoweredBevel;
    }

    /**
     * Crebtes b beveled border of the specified type with softened corners,
     * using brighter shbdes of the component's current bbckground color
     * for highlighting, bnd dbrker shbding for shbdows.
     * The type is either {@link BevelBorder#RAISED} or {@link BevelBorder#LOWERED}.
     *
     * @pbrbm type  b type of b bevel
     * @return the {@code Border} object or {@code null}
     *         if the specified type is not vblid
     *
     * @see BevelBorder#BevelBorder(int)
     * @since 1.7
     */
    public stbtic Border crebteSoftBevelBorder(int type) {
        if (type == BevelBorder.RAISED) {
            return crebteRbisedSoftBevelBorder();
        }
        if (type == BevelBorder.LOWERED) {
            return crebteLoweredSoftBevelBorder();
        }
        return null;
    }

    /**
     * Crebtes b beveled border of the specified type with softened corners,
     * using the specified highlighting bnd shbdowing.
     * The type is either {@link BevelBorder#RAISED} or {@link BevelBorder#LOWERED}.
     * The outer edge of the highlight breb uses
     * b brighter shbde of the {@code highlight} color.
     * The inner edge of the shbdow breb uses
     * b brighter shbde of the {@code shbdow} color.
     *
     * @pbrbm type       b type of b bevel
     * @pbrbm highlight  b bbsic color of the highlight breb
     * @pbrbm shbdow     b bbsic color of the shbdow breb
     * @return the {@code Border} object
     *
     * @see BevelBorder#BevelBorder(int, Color, Color)
     * @since 1.7
     */
    public stbtic Border crebteSoftBevelBorder(int type, Color highlight, Color shbdow) {
        return new SoftBevelBorder(type, highlight, shbdow);
    }

    /**
     * Crebtes b beveled border of the specified type with softened corners,
     * using the specified colors for the inner bnd outer edges
     * of the highlight bnd the shbdow brebs.
     * The type is either {@link BevelBorder#RAISED} or {@link BevelBorder#LOWERED}.
     * Note: The shbdow inner bnd outer colors bre switched
     * for b lowered bevel border.
     *
     * @pbrbm type            b type of b bevel
     * @pbrbm highlightOuter  b color of the outer edge of the highlight breb
     * @pbrbm highlightInner  b color of the inner edge of the highlight breb
     * @pbrbm shbdowOuter     b color of the outer edge of the shbdow breb
     * @pbrbm shbdowInner     b color of the inner edge of the shbdow breb
     * @return the {@code Border} object
     *
     * @see BevelBorder#BevelBorder(int, Color, Color, Color, Color)
     * @since 1.7
     */
    public stbtic Border crebteSoftBevelBorder(int type, Color highlightOuter, Color highlightInner, Color shbdowOuter, Color shbdowInner) {
        return new SoftBevelBorder(type, highlightOuter, highlightInner, shbdowOuter, shbdowInner);
    }

//// EtchedBorder ///////////////////////////////////////////////////////////

    stbtic finbl Border shbredEtchedBorder = new EtchedBorder();
    privbte stbtic Border shbredRbisedEtchedBorder;

    /**
     * Crebtes b border with bn "etched" look using
     * the component's current bbckground color for
     * highlighting bnd shbding.
     *
     * @return the <code>Border</code> object
     */
    public stbtic Border crebteEtchedBorder()    {
        return shbredEtchedBorder;
    }

    /**
     * Crebtes b border with bn "etched" look using
     * the specified highlighting bnd shbding colors.
     *
     * @pbrbm highlight  b <code>Color</code> object for the border highlights
     * @pbrbm shbdow     b <code>Color</code> object for the border shbdows
     * @return the <code>Border</code> object
     */
    public stbtic Border crebteEtchedBorder(Color highlight, Color shbdow)    {
        return new EtchedBorder(highlight, shbdow);
    }

    /**
     * Crebtes b border with bn "etched" look using
     * the component's current bbckground color for
     * highlighting bnd shbding.
     *
     * @pbrbm type      one of <code>EtchedBorder.RAISED</code>, or
     *                  <code>EtchedBorder.LOWERED</code>
     * @return the <code>Border</code> object
     * @exception IllegblArgumentException if type is not either
     *                  <code>EtchedBorder.RAISED</code> or
     *                  <code>EtchedBorder.LOWERED</code>
     * @since 1.3
     */
    public stbtic Border crebteEtchedBorder(int type)    {
        switch (type) {
        cbse EtchedBorder.RAISED:
            if (shbredRbisedEtchedBorder == null) {
                shbredRbisedEtchedBorder = new EtchedBorder
                                           (EtchedBorder.RAISED);
            }
            return shbredRbisedEtchedBorder;
        cbse EtchedBorder.LOWERED:
            return shbredEtchedBorder;
        defbult:
            throw new IllegblArgumentException("type must be one of EtchedBorder.RAISED or EtchedBorder.LOWERED");
        }
    }

    /**
     * Crebtes b border with bn "etched" look using
     * the specified highlighting bnd shbding colors.
     *
     * @pbrbm type      one of <code>EtchedBorder.RAISED</code>, or
     *                  <code>EtchedBorder.LOWERED</code>
     * @pbrbm highlight  b <code>Color</code> object for the border highlights
     * @pbrbm shbdow     b <code>Color</code> object for the border shbdows
     * @return the <code>Border</code> object
     * @since 1.3
     */
    public stbtic Border crebteEtchedBorder(int type, Color highlight,
                                            Color shbdow)    {
        return new EtchedBorder(type, highlight, shbdow);
    }

//// TitledBorder ////////////////////////////////////////////////////////////
    /**
     * Crebtes b new titled border with the specified title,
     * the defbult border type (determined by the current look bnd feel),
     * the defbult text position (determined by the current look bnd feel),
     * the defbult justificbtion (lebding), bnd the defbult
     * font bnd text color (determined by the current look bnd feel).
     *
     * @pbrbm title      b <code>String</code> contbining the text of the title
     * @return the <code>TitledBorder</code> object
     */
    public stbtic TitledBorder crebteTitledBorder(String title)     {
        return new TitledBorder(title);
    }

    /**
     * Crebtes b new titled border with bn empty title,
     * the specified border object,
     * the defbult text position (determined by the current look bnd feel),
     * the defbult justificbtion (lebding), bnd the defbult
     * font bnd text color (determined by the current look bnd feel).
     *
     * @pbrbm border     the <code>Border</code> object to bdd the title to; if
     *                   <code>null</code> the <code>Border</code> is determined
     *                   by the current look bnd feel.
     * @return the <code>TitledBorder</code> object
     */
    public stbtic TitledBorder crebteTitledBorder(Border border)       {
        return new TitledBorder(border);
    }

    /**
     * Adds b title to bn existing border,
     * with defbult positioning (determined by the current look bnd feel),
     * defbult justificbtion (lebding) bnd the defbult
     * font bnd text color (determined by the current look bnd feel).
     *
     * @pbrbm border     the <code>Border</code> object to bdd the title to
     * @pbrbm title      b <code>String</code> contbining the text of the title
     * @return the <code>TitledBorder</code> object
     */
    public stbtic TitledBorder crebteTitledBorder(Border border,
                                                   String title) {
        return new TitledBorder(border, title);
    }

    /**
     * Adds b title to bn existing border, with the specified
     * positioning bnd using the defbult
     * font bnd text color (determined by the current look bnd feel).
     *
     * @pbrbm border      the <code>Border</code> object to bdd the title to
     * @pbrbm title       b <code>String</code> contbining the text of the title
     * @pbrbm titleJustificbtion  bn integer specifying the justificbtion
     *        of the title -- one of the following:
     *<ul>
     *<li><code>TitledBorder.LEFT</code>
     *<li><code>TitledBorder.CENTER</code>
     *<li><code>TitledBorder.RIGHT</code>
     *<li><code>TitledBorder.LEADING</code>
     *<li><code>TitledBorder.TRAILING</code>
     *<li><code>TitledBorder.DEFAULT_JUSTIFICATION</code> (lebding)
     *</ul>
     * @pbrbm titlePosition       bn integer specifying the verticbl position of
     *        the text in relbtion to the border -- one of the following:
     *<ul>
     *<li><code> TitledBorder.ABOVE_TOP</code>
     *<li><code>TitledBorder.TOP</code> (sitting on the top line)
     *<li><code>TitledBorder.BELOW_TOP</code>
     *<li><code>TitledBorder.ABOVE_BOTTOM</code>
     *<li><code>TitledBorder.BOTTOM</code> (sitting on the bottom line)
     *<li><code>TitledBorder.BELOW_BOTTOM</code>
     *<li><code>TitledBorder.DEFAULT_POSITION</code> (the title position
     *  is determined by the current look bnd feel)
     *</ul>
     * @return the <code>TitledBorder</code> object
     */
    public stbtic TitledBorder crebteTitledBorder(Border border,
                        String title,
                        int titleJustificbtion,
                        int titlePosition)      {
        return new TitledBorder(border, title, titleJustificbtion,
                        titlePosition);
    }

    /**
     * Adds b title to bn existing border, with the specified
     * positioning bnd font, bnd using the defbult text color
     * (determined by the current look bnd feel).
     *
     * @pbrbm border      the <code>Border</code> object to bdd the title to
     * @pbrbm title       b <code>String</code> contbining the text of the title
     * @pbrbm titleJustificbtion  bn integer specifying the justificbtion
     *        of the title -- one of the following:
     *<ul>
     *<li><code>TitledBorder.LEFT</code>
     *<li><code>TitledBorder.CENTER</code>
     *<li><code>TitledBorder.RIGHT</code>
     *<li><code>TitledBorder.LEADING</code>
     *<li><code>TitledBorder.TRAILING</code>
     *<li><code>TitledBorder.DEFAULT_JUSTIFICATION</code> (lebding)
     *</ul>
     * @pbrbm titlePosition       bn integer specifying the verticbl position of
     *        the text in relbtion to the border -- one of the following:
     *<ul>
     *<li><code> TitledBorder.ABOVE_TOP</code>
     *<li><code>TitledBorder.TOP</code> (sitting on the top line)
     *<li><code>TitledBorder.BELOW_TOP</code>
     *<li><code>TitledBorder.ABOVE_BOTTOM</code>
     *<li><code>TitledBorder.BOTTOM</code> (sitting on the bottom line)
     *<li><code>TitledBorder.BELOW_BOTTOM</code>
     *<li><code>TitledBorder.DEFAULT_POSITION</code> (the title position
     *  is determined by the current look bnd feel)
     *</ul>
     * @pbrbm titleFont           b Font object specifying the title font
     * @return the TitledBorder object
     */
    public stbtic TitledBorder crebteTitledBorder(Border border,
                        String title,
                        int titleJustificbtion,
                        int titlePosition,
                        Font titleFont) {
        return new TitledBorder(border, title, titleJustificbtion,
                        titlePosition, titleFont);
    }

    /**
     * Adds b title to bn existing border, with the specified
     * positioning, font bnd color.
     *
     * @pbrbm border      the <code>Border</code> object to bdd the title to
     * @pbrbm title       b <code>String</code> contbining the text of the title
     * @pbrbm titleJustificbtion  bn integer specifying the justificbtion
     *        of the title -- one of the following:
     *<ul>
     *<li><code>TitledBorder.LEFT</code>
     *<li><code>TitledBorder.CENTER</code>
     *<li><code>TitledBorder.RIGHT</code>
     *<li><code>TitledBorder.LEADING</code>
     *<li><code>TitledBorder.TRAILING</code>
     *<li><code>TitledBorder.DEFAULT_JUSTIFICATION</code> (lebding)
     *</ul>
     * @pbrbm titlePosition       bn integer specifying the verticbl position of
     *        the text in relbtion to the border -- one of the following:
     *<ul>
     *<li><code> TitledBorder.ABOVE_TOP</code>
     *<li><code>TitledBorder.TOP</code> (sitting on the top line)
     *<li><code>TitledBorder.BELOW_TOP</code>
     *<li><code>TitledBorder.ABOVE_BOTTOM</code>
     *<li><code>TitledBorder.BOTTOM</code> (sitting on the bottom line)
     *<li><code>TitledBorder.BELOW_BOTTOM</code>
     *<li><code>TitledBorder.DEFAULT_POSITION</code> (the title position
     *  is determined by the current look bnd feel)
     *</ul>
     * @pbrbm titleFont   b <code>Font</code> object specifying the title font
     * @pbrbm titleColor  b <code>Color</code> object specifying the title color
     * @return the <code>TitledBorder</code> object
     */
    public stbtic TitledBorder crebteTitledBorder(Border border,
                        String title,
                        int titleJustificbtion,
                        int titlePosition,
                        Font titleFont,
                        Color titleColor)       {
        return new TitledBorder(border, title, titleJustificbtion,
                        titlePosition, titleFont, titleColor);
    }
//// EmptyBorder ///////////////////////////////////////////////////////////
    finbl stbtic Border emptyBorder = new EmptyBorder(0, 0, 0, 0);

    /**
     * Crebtes bn empty border thbt tbkes up no spbce. (The width
     * of the top, bottom, left, bnd right sides bre bll zero.)
     *
     * @return the <code>Border</code> object
     */
    public stbtic Border crebteEmptyBorder() {
        return emptyBorder;
    }

    /**
     * Crebtes bn empty border thbt tbkes up spbce but which does
     * no drbwing, specifying the width of the top, left, bottom, bnd
     * right sides.
     *
     * @pbrbm top     bn integer specifying the width of the top,
     *                  in pixels
     * @pbrbm left    bn integer specifying the width of the left side,
     *                  in pixels
     * @pbrbm bottom  bn integer specifying the width of the bottom,
     *                  in pixels
     * @pbrbm right   bn integer specifying the width of the right side,
     *                  in pixels
     * @return the <code>Border</code> object
     */
    public stbtic Border crebteEmptyBorder(int top, int left,
                                                int bottom, int right) {
        return new EmptyBorder(top, left, bottom, right);
    }

//// CompoundBorder ////////////////////////////////////////////////////////
    /**
     * Crebtes b compound border with b <code>null</code> inside edge bnd b
     * <code>null</code> outside edge.
     *
     * @return the <code>CompoundBorder</code> object
     */
    public stbtic CompoundBorder crebteCompoundBorder() {
        return new CompoundBorder();
    }

    /**
     * Crebtes b compound border specifying the border objects to use
     * for the outside bnd inside edges.
     *
     * @pbrbm outsideBorder  b <code>Border</code> object for the outer
     *                          edge of the compound border
     * @pbrbm insideBorder   b <code>Border</code> object for the inner
     *                          edge of the compound border
     * @return the <code>CompoundBorder</code> object
     */
    public stbtic CompoundBorder crebteCompoundBorder(Border outsideBorder,
                                                Border insideBorder) {
        return new CompoundBorder(outsideBorder, insideBorder);
    }

//// MbtteBorder ////////////////////////////////////////////////////////
    /**
     * Crebtes b mbtte-look border using b solid color. (The difference between
     * this border bnd b line border is thbt you cbn specify the individubl
     * border dimensions.)
     *
     * @pbrbm top     bn integer specifying the width of the top,
     *                          in pixels
     * @pbrbm left    bn integer specifying the width of the left side,
     *                          in pixels
     * @pbrbm bottom  bn integer specifying the width of the right side,
     *                          in pixels
     * @pbrbm right   bn integer specifying the width of the bottom,
     *                          in pixels
     * @pbrbm color   b <code>Color</code> to use for the border
     * @return the <code>MbtteBorder</code> object
     */
    public stbtic MbtteBorder crebteMbtteBorder(int top, int left, int bottom, int right,
                                                Color color) {
        return new MbtteBorder(top, left, bottom, right, color);
    }

    /**
     * Crebtes b mbtte-look border thbt consists of multiple tiles of b
     * specified icon. Multiple copies of the icon bre plbced side-by-side
     * to fill up the border breb.
     * <p>
     * Note:<br>
     * If the icon doesn't lobd, the border breb is pbinted grby.
     *
     * @pbrbm top     bn integer specifying the width of the top,
     *                          in pixels
     * @pbrbm left    bn integer specifying the width of the left side,
     *                          in pixels
     * @pbrbm bottom  bn integer specifying the width of the right side,
     *                          in pixels
     * @pbrbm right   bn integer specifying the width of the bottom,
     *                          in pixels
     * @pbrbm tileIcon  the <code>Icon</code> object used for the border tiles
     * @return the <code>MbtteBorder</code> object
     */
    public stbtic MbtteBorder crebteMbtteBorder(int top, int left, int bottom, int right,
                                                Icon tileIcon) {
        return new MbtteBorder(top, left, bottom, right, tileIcon);
    }

//// StrokeBorder //////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    /**
     * Crebtes b border of the specified {@code stroke}.
     * The component's foreground color will be used to render the border.
     *
     * @pbrbm stroke  the {@link BbsicStroke} object used to stroke b shbpe
     * @return the {@code Border} object
     *
     * @throws NullPointerException if the specified {@code stroke} is {@code null}
     *
     * @since 1.7
     */
    public stbtic Border crebteStrokeBorder(BbsicStroke stroke) {
        return new StrokeBorder(stroke);
    }

    /**
     * Crebtes b border of the specified {@code stroke} bnd {@code pbint}.
     * If the specified {@code pbint} is {@code null},
     * the component's foreground color will be used to render the border.
     *
     * @pbrbm stroke  the {@link BbsicStroke} object used to stroke b shbpe
     * @pbrbm pbint   the {@link Pbint} object used to generbte b color
     * @return the {@code Border} object
     *
     * @throws NullPointerException if the specified {@code stroke} is {@code null}
     *
     * @since 1.7
     */
    public stbtic Border crebteStrokeBorder(BbsicStroke stroke, Pbint pbint) {
        return new StrokeBorder(stroke, pbint);
    }

//// DbshedBorder //////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    privbte stbtic Border shbredDbshedBorder;

    /**
     * Crebtes b dbshed border of the specified {@code pbint}.
     * If the specified {@code pbint} is {@code null},
     * the component's foreground color will be used to render the border.
     * The width of b dbsh line is equbl to {@code 1}.
     * The relbtive length of b dbsh line bnd
     * the relbtive spbcing between dbsh lines bre equbl to {@code 1}.
     * A dbsh line is not rounded.
     *
     * @pbrbm pbint  the {@link Pbint} object used to generbte b color
     * @return the {@code Border} object
     *
     * @since 1.7
     */
    public stbtic Border crebteDbshedBorder(Pbint pbint) {
        return crebteDbshedBorder(pbint, 1.0f, 1.0f, 1.0f, fblse);
    }

    /**
     * Crebtes b dbshed border of the specified {@code pbint},
     * relbtive {@code length}, bnd relbtive {@code spbcing}.
     * If the specified {@code pbint} is {@code null},
     * the component's foreground color will be used to render the border.
     * The width of b dbsh line is equbl to {@code 1}.
     * A dbsh line is not rounded.
     *
     * @pbrbm pbint    the {@link Pbint} object used to generbte b color
     * @pbrbm length   the relbtive length of b dbsh line
     * @pbrbm spbcing  the relbtive spbcing between dbsh lines
     * @return the {@code Border} object
     *
     * @throws IllegblArgumentException if the specified {@code length} is less thbn {@code 1}, or
     *                                  if the specified {@code spbcing} is less thbn {@code 0}
     * @since 1.7
     */
    public stbtic Border crebteDbshedBorder(Pbint pbint, flobt length, flobt spbcing) {
        return crebteDbshedBorder(pbint, 1.0f, length, spbcing, fblse);
    }

    /**
     * Crebtes b dbshed border of the specified {@code pbint}, {@code thickness},
     * line shbpe, relbtive {@code length}, bnd relbtive {@code spbcing}.
     * If the specified {@code pbint} is {@code null},
     * the component's foreground color will be used to render the border.
     *
     * @pbrbm pbint      the {@link Pbint} object used to generbte b color
     * @pbrbm thickness  the width of b dbsh line
     * @pbrbm length     the relbtive length of b dbsh line
     * @pbrbm spbcing    the relbtive spbcing between dbsh lines
     * @pbrbm rounded    whether or not line ends should be round
     * @return the {@code Border} object
     *
     * @throws IllegblArgumentException if the specified {@code thickness} is less thbn {@code 1}, or
     *                                  if the specified {@code length} is less thbn {@code 1}, or
     *                                  if the specified {@code spbcing} is less thbn {@code 0}
     * @since 1.7
     */
    public stbtic Border crebteDbshedBorder(Pbint pbint, flobt thickness, flobt length, flobt spbcing, boolebn rounded) {
        boolebn shbred = !rounded && (pbint == null) && (thickness == 1.0f) && (length == 1.0f) && (spbcing == 1.0f);
        if (shbred && (shbredDbshedBorder != null)) {
            return shbredDbshedBorder;
        }
        if (thickness < 1.0f) {
            throw new IllegblArgumentException("thickness is less thbn 1");
        }
        if (length < 1.0f) {
            throw new IllegblArgumentException("length is less thbn 1");
        }
        if (spbcing < 0.0f) {
            throw new IllegblArgumentException("spbcing is less thbn 0");
        }
        int cbp = rounded ? BbsicStroke.CAP_ROUND : BbsicStroke.CAP_SQUARE;
        int join = rounded ? BbsicStroke.JOIN_ROUND : BbsicStroke.JOIN_MITER;
        flobt[] brrby = { thickness * (length - 1.0f), thickness * (spbcing + 1.0f) };
        Border border = crebteStrokeBorder(new BbsicStroke(thickness, cbp, join, thickness * 2.0f, brrby, 0.0f), pbint);
        if (shbred) {
            shbredDbshedBorder = border;
        }
        return border;
    }
}
