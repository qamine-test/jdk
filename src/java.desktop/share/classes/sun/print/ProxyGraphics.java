/*
 * Copyright (c) 2000, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.print;

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Imbge;
import jbvb.bwt.Polygon;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;

import jbvb.bwt.imbge.ImbgeObserver;

import jbvb.text.AttributedChbrbcterIterbtor;

/**
 * Implements the Grbphics API but does bll
 * rendering through b second Grbphics instbnce.
 * The primbry use of this clbss is to provide
 * b Grbphics instbnce without the 2D API to
 * bn bpplicbtion, but to implement the rendering
 * with b Grbphics2D instbnce.
 */
public clbss ProxyGrbphics extends Grbphics {

    /**
     * The Grbphics instbnce thbt performs the
     * drbwing for this Grbphics.
     */
    privbte Grbphics g;

    public ProxyGrbphics(Grbphics grbphics) {
        g = grbphics;
    }

    Grbphics getGrbphics() {
        return g;
    }

   /**
     * Crebtes b new <code>Grbphics</code> object thbt is
     * b copy of this <code>Grbphics</code> object.
     * @return     b new grbphics context thbt is b copy of
     *                       this grbphics context.
     */
    public Grbphics crebte() {
        return new ProxyGrbphics(g.crebte());
    }

    /**
     * Crebtes b new <code>Grbphics</code> object bbsed on this
     * <code>Grbphics</code> object, but with b new trbnslbtion bnd clip breb.
     * The new <code>Grbphics</code> object hbs its origin
     * trbnslbted to the specified point (<i>x</i>,&nbsp;<i>y</i>).
     * Its clip breb is determined by the intersection of the originbl
     * clip breb with the specified rectbngle.  The brguments bre bll
     * interpreted in the coordinbte system of the originbl
     * <code>Grbphics</code> object. The new grbphics context is
     * identicbl to the originbl, except in two respects:
     * <p>
     * <ul>
     * <li>
     * The new grbphics context is trbnslbted by (<i>x</i>,&nbsp;<i>y</i>).
     * Thbt is to sby, the point (<code>0</code>,&nbsp;<code>0</code>) in the
     * new grbphics context is the sbme bs (<i>x</i>,&nbsp;<i>y</i>) in
     * the originbl grbphics context.
     * <li>
     * The new grbphics context hbs bn bdditionbl clipping rectbngle, in
     * bddition to whbtever (trbnslbted) clipping rectbngle it inherited
     * from the originbl grbphics context. The origin of the new clipping
     * rectbngle is bt (<code>0</code>,&nbsp;<code>0</code>), bnd its size
     * is specified by the <code>width</code> bnd <code>height</code>
     * brguments.
     * </ul>
     * <p>
     * @pbrbm      x   the <i>x</i> coordinbte.
     * @pbrbm      y   the <i>y</i> coordinbte.
     * @pbrbm      width   the width of the clipping rectbngle.
     * @pbrbm      height   the height of the clipping rectbngle.
     * @return     b new grbphics context.
     * @see        jbvb.bwt.Grbphics#trbnslbte
     * @see        jbvb.bwt.Grbphics#clipRect
     */
    public Grbphics crebte(int x, int y, int width, int height) {
        return new ProxyGrbphics(g.crebte(x, y, width, height));
    }

    /**
     * Trbnslbtes the origin of the grbphics context to the point
     * (<i>x</i>,&nbsp;<i>y</i>) in the current coordinbte system.
     * Modifies this grbphics context so thbt its new origin corresponds
     * to the point (<i>x</i>,&nbsp;<i>y</i>) in this grbphics context's
     * originbl coordinbte system.  All coordinbtes used in subsequent
     * rendering operbtions on this grbphics context will be relbtive
     * to this new origin.
     * @pbrbm  x   the <i>x</i> coordinbte.
     * @pbrbm  y   the <i>y</i> coordinbte.
     */
    public void trbnslbte(int x, int y) {
        g.trbnslbte(x, y);
    }

    /**
     * Gets this grbphics context's current color.
     * @return    this grbphics context's current color.
     * @see       jbvb.bwt.Color
     * @see       jbvb.bwt.Grbphics#setColor
     */
    public Color getColor() {
        return g.getColor();
    }

    /**
     * Sets this grbphics context's current color to the specified
     * color. All subsequent grbphics operbtions using this grbphics
     * context use this specified color.
     * @pbrbm     c   the new rendering color.
     * @see       jbvb.bwt.Color
     * @see       jbvb.bwt.Grbphics#getColor
     */
    public void setColor(Color c) {
        g.setColor(c);
    }

    /**
     * Sets the pbint mode of this grbphics context to overwrite the
     * destinbtion with this grbphics context's current color.
     * This sets the logicbl pixel operbtion function to the pbint or
     * overwrite mode.  All subsequent rendering operbtions will
     * overwrite the destinbtion with the current color.
     */
    public void setPbintMode() {
        g.setPbintMode();
    }

    /**
     * Sets the pbint mode of this grbphics context to blternbte between
     * this grbphics context's current color bnd the new specified color.
     * This specifies thbt logicbl pixel operbtions bre performed in the
     * XOR mode, which blternbtes pixels between the current color bnd
     * b specified XOR color.
     * <p>
     * When drbwing operbtions bre performed, pixels which bre the
     * current color bre chbnged to the specified color, bnd vice versb.
     * <p>
     * Pixels thbt bre of colors other thbn those two colors bre chbnged
     * in bn unpredictbble but reversible mbnner; if the sbme figure is
     * drbwn twice, then bll pixels bre restored to their originbl vblues.
     * @pbrbm     c1 the XOR blternbtion color
     */
    public void setXORMode(Color c1) {
        g.setXORMode(c1);
    }

    /**
     * Gets the current font.
     * @return    this grbphics context's current font.
     * @see       jbvb.bwt.Font
     * @see       jbvb.bwt.Grbphics#setFont
     */
    public Font getFont() {
        return g.getFont();
    }

    /**
     * Sets this grbphics context's font to the specified font.
     * All subsequent text operbtions using this grbphics context
     * use this font.
     * @pbrbm  font   the font.
     * @see     jbvb.bwt.Grbphics#getFont
     * @see     jbvb.bwt.Grbphics#drbwString(jbvb.lbng.String, int, int)
     * @see     jbvb.bwt.Grbphics#drbwBytes(byte[], int, int, int, int)
     * @see     jbvb.bwt.Grbphics#drbwChbrs(chbr[], int, int, int, int)
    */
    public void setFont(Font font) {
        g.setFont(font);
    }

    /**
     * Gets the font metrics of the current font.
     * @return    the font metrics of this grbphics
     *                    context's current font.
     * @see       jbvb.bwt.Grbphics#getFont
     * @see       jbvb.bwt.FontMetrics
     * @see       jbvb.bwt.Grbphics#getFontMetrics(Font)
     */
    public FontMetrics getFontMetrics() {
        return g.getFontMetrics();
    }

    /**
     * Gets the font metrics for the specified font.
     * @return    the font metrics for the specified font.
     * @pbrbm     f the specified font
     * @see       jbvb.bwt.Grbphics#getFont
     * @see       jbvb.bwt.FontMetrics
     * @see       jbvb.bwt.Grbphics#getFontMetrics()
     */
    public FontMetrics getFontMetrics(Font f) {
        return g.getFontMetrics(f);
    }


    /**
     * Returns the bounding rectbngle of the current clipping breb.
     * This method refers to the user clip, which is independent of the
     * clipping bssocibted with device bounds bnd window visibility.
     * If no clip hbs previously been set, or if the clip hbs been
     * clebred using <code>setClip(null)</code>, this method returns
     * <code>null</code>.
     * The coordinbtes in the rectbngle bre relbtive to the coordinbte
     * system origin of this grbphics context.
     * @return      the bounding rectbngle of the current clipping breb,
     *              or <code>null</code> if no clip is set.
     * @see         jbvb.bwt.Grbphics#getClip
     * @see         jbvb.bwt.Grbphics#clipRect
     * @see         jbvb.bwt.Grbphics#setClip(int, int, int, int)
     * @see         jbvb.bwt.Grbphics#setClip(Shbpe)
     * @since       1.1
     */
    public Rectbngle getClipBounds() {
        return g.getClipBounds();
    }

    /**
     * Intersects the current clip with the specified rectbngle.
     * The resulting clipping breb is the intersection of the current
     * clipping breb bnd the specified rectbngle.  If there is no
     * current clipping breb, either becbuse the clip hbs never been
     * set, or the clip hbs been clebred using <code>setClip(null)</code>,
     * the specified rectbngle becomes the new clip.
     * This method sets the user clip, which is independent of the
     * clipping bssocibted with device bounds bnd window visibility.
     * This method cbn only be used to mbke the current clip smbller.
     * To set the current clip lbrger, use bny of the setClip methods.
     * Rendering operbtions hbve no effect outside of the clipping breb.
     * @pbrbm x the x coordinbte of the rectbngle to intersect the clip with
     * @pbrbm y the y coordinbte of the rectbngle to intersect the clip with
     * @pbrbm width the width of the rectbngle to intersect the clip with
     * @pbrbm height the height of the rectbngle to intersect the clip with
     * @see #setClip(int, int, int, int)
     * @see #setClip(Shbpe)
     */
    public void clipRect(int x, int y, int width, int height) {
        g.clipRect(x, y, width, height);
    }

    /**
     * Sets the current clip to the rectbngle specified by the given
     * coordinbtes.  This method sets the user clip, which is
     * independent of the clipping bssocibted with device bounds
     * bnd window visibility.
     * Rendering operbtions hbve no effect outside of the clipping breb.
     * @pbrbm       x the <i>x</i> coordinbte of the new clip rectbngle.
     * @pbrbm       y the <i>y</i> coordinbte of the new clip rectbngle.
     * @pbrbm       width the width of the new clip rectbngle.
     * @pbrbm       height the height of the new clip rectbngle.
     * @see         jbvb.bwt.Grbphics#clipRect
     * @see         jbvb.bwt.Grbphics#setClip(Shbpe)
     * @since       1.1
     */
    public void setClip(int x, int y, int width, int height) {
        g.setClip(x, y, width, height);
    }

    /**
     * Gets the current clipping breb.
     * This method returns the user clip, which is independent of the
     * clipping bssocibted with device bounds bnd window visibility.
     * If no clip hbs previously been set, or if the clip hbs been
     * clebred using <code>setClip(null)</code>, this method returns
     * <code>null</code>.
     * @return      b <code>Shbpe</code> object representing the
     *              current clipping breb, or <code>null</code> if
     *              no clip is set.
     * @see         jbvb.bwt.Grbphics#getClipBounds
     * @see         jbvb.bwt.Grbphics#clipRect
     * @see         jbvb.bwt.Grbphics#setClip(int, int, int, int)
     * @see         jbvb.bwt.Grbphics#setClip(Shbpe)
     * @since       1.1
     */
    public Shbpe getClip() {
        return g.getClip();
    }

    /**
     * Sets the current clipping breb to bn brbitrbry clip shbpe.
     * Not bll objects thbt implement the <code>Shbpe</code>
     * interfbce cbn be used to set the clip.  The only
     * <code>Shbpe</code> objects thbt bre gubrbnteed to be
     * supported bre <code>Shbpe</code> objects thbt bre
     * obtbined vib the <code>getClip</code> method bnd vib
     * <code>Rectbngle</code> objects.  This method sets the
     * user clip, which is independent of the clipping bssocibted
     * with device bounds bnd window visibility.
     * @pbrbm clip the <code>Shbpe</code> to use to set the clip
     * @see         jbvb.bwt.Grbphics#getClip()
     * @see         jbvb.bwt.Grbphics#clipRect
     * @see         jbvb.bwt.Grbphics#setClip(int, int, int, int)
     * @since       1.1
     */
    public void setClip(Shbpe clip) {
        g.setClip(clip);
    }

    /**
     * Copies bn breb of the component by b distbnce specified by
     * <code>dx</code> bnd <code>dy</code>. From the point specified
     * by <code>x</code> bnd <code>y</code>, this method
     * copies downwbrds bnd to the right.  To copy bn breb of the
     * component to the left or upwbrds, specify b negbtive vblue for
     * <code>dx</code> or <code>dy</code>.
     * If b portion of the source rectbngle lies outside the bounds
     * of the component, or is obscured by bnother window or component,
     * <code>copyAreb</code> will be unbble to copy the bssocibted
     * pixels. The breb thbt is omitted cbn be refreshed by cblling
     * the component's <code>pbint</code> method.
     * @pbrbm       x the <i>x</i> coordinbte of the source rectbngle.
     * @pbrbm       y the <i>y</i> coordinbte of the source rectbngle.
     * @pbrbm       width the width of the source rectbngle.
     * @pbrbm       height the height of the source rectbngle.
     * @pbrbm       dx the horizontbl distbnce to copy the pixels.
     * @pbrbm       dy the verticbl distbnce to copy the pixels.
     */
    public void copyAreb(int x, int y, int width, int height,
                                  int dx, int dy) {
        g.copyAreb(x, y, width, height, dx, dy);
    }

    /**
     * Drbws b line, using the current color, between the points
     * <code>(x1,&nbsp;y1)</code> bnd <code>(x2,&nbsp;y2)</code>
     * in this grbphics context's coordinbte system.
     * @pbrbm   x1  the first point's <i>x</i> coordinbte.
     * @pbrbm   y1  the first point's <i>y</i> coordinbte.
     * @pbrbm   x2  the second point's <i>x</i> coordinbte.
     * @pbrbm   y2  the second point's <i>y</i> coordinbte.
     */
    public void drbwLine(int x1, int y1, int x2, int y2) {
        g.drbwLine(x1, y1, x2, y2);
    }

    /**
     * Fills the specified rectbngle.
     * The left bnd right edges of the rectbngle bre bt
     * <code>x</code> bnd <code>x&nbsp;+&nbsp;width&nbsp;-&nbsp;1</code>.
     * The top bnd bottom edges bre bt
     * <code>y</code> bnd <code>y&nbsp;+&nbsp;height&nbsp;-&nbsp;1</code>.
     * The resulting rectbngle covers bn breb
     * <code>width</code> pixels wide by
     * <code>height</code> pixels tbll.
     * The rectbngle is filled using the grbphics context's current color.
     * @pbrbm         x   the <i>x</i> coordinbte
     *                         of the rectbngle to be filled.
     * @pbrbm         y   the <i>y</i> coordinbte
     *                         of the rectbngle to be filled.
     * @pbrbm         width   the width of the rectbngle to be filled.
     * @pbrbm         height   the height of the rectbngle to be filled.
     * @see           jbvb.bwt.Grbphics#clebrRect
     * @see           jbvb.bwt.Grbphics#drbwRect
     */
    public void fillRect(int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);
    }

    /**
     * Drbws the outline of the specified rectbngle.
     * The left bnd right edges of the rectbngle bre bt
     * <code>x</code> bnd <code>x&nbsp;+&nbsp;width</code>.
     * The top bnd bottom edges bre bt
     * <code>y</code> bnd <code>y&nbsp;+&nbsp;height</code>.
     * The rectbngle is drbwn using the grbphics context's current color.
     * @pbrbm         x   the <i>x</i> coordinbte
     *                         of the rectbngle to be drbwn.
     * @pbrbm         y   the <i>y</i> coordinbte
     *                         of the rectbngle to be drbwn.
     * @pbrbm         width   the width of the rectbngle to be drbwn.
     * @pbrbm         height   the height of the rectbngle to be drbwn.
     * @see          jbvb.bwt.Grbphics#fillRect
     * @see          jbvb.bwt.Grbphics#clebrRect
     */
    public void drbwRect(int x, int y, int width, int height) {
        g.drbwRect(x, y, width, height);
    }

    /**
     * Clebrs the specified rectbngle by filling it with the bbckground
     * color of the current drbwing surfbce. This operbtion does not
     * use the current pbint mode.
     * <p>
     * Beginning with Jbvb&nbsp;1.1, the bbckground color
     * of offscreen imbges mby be system dependent. Applicbtions should
     * use <code>setColor</code> followed by <code>fillRect</code> to
     * ensure thbt bn offscreen imbge is clebred to b specific color.
     * @pbrbm       x the <i>x</i> coordinbte of the rectbngle to clebr.
     * @pbrbm       y the <i>y</i> coordinbte of the rectbngle to clebr.
     * @pbrbm       width the width of the rectbngle to clebr.
     * @pbrbm       height the height of the rectbngle to clebr.
     * @see         jbvb.bwt.Grbphics#fillRect(int, int, int, int)
     * @see         jbvb.bwt.Grbphics#drbwRect
     * @see         jbvb.bwt.Grbphics#setColor(jbvb.bwt.Color)
     * @see         jbvb.bwt.Grbphics#setPbintMode
     * @see         jbvb.bwt.Grbphics#setXORMode(jbvb.bwt.Color)
     */
    public void clebrRect(int x, int y, int width, int height) {
        g.clebrRect(x, y, width, height);
    }

    /**
     * Drbws bn outlined round-cornered rectbngle using this grbphics
     * context's current color. The left bnd right edges of the rectbngle
     * bre bt <code>x</code> bnd <code>x&nbsp;+&nbsp;width</code>,
     * respectively. The top bnd bottom edges of the rectbngle bre bt
     * <code>y</code> bnd <code>y&nbsp;+&nbsp;height</code>.
     * @pbrbm      x the <i>x</i> coordinbte of the rectbngle to be drbwn.
     * @pbrbm      y the <i>y</i> coordinbte of the rectbngle to be drbwn.
     * @pbrbm      width the width of the rectbngle to be drbwn.
     * @pbrbm      height the height of the rectbngle to be drbwn.
     * @pbrbm      brcWidth the horizontbl dibmeter of the brc
     *                    bt the four corners.
     * @pbrbm      brcHeight the verticbl dibmeter of the brc
     *                    bt the four corners.
     * @see        jbvb.bwt.Grbphics#fillRoundRect
     */
    public void drbwRoundRect(int x, int y, int width, int height,
                                       int brcWidth, int brcHeight) {
        g.drbwRoundRect(x, y, width, height, brcWidth, brcHeight);
    }

    /**
     * Fills the specified rounded corner rectbngle with the current color.
     * The left bnd right edges of the rectbngle
     * bre bt <code>x</code> bnd <code>x&nbsp;+&nbsp;width&nbsp;-&nbsp;1</code>,
     * respectively. The top bnd bottom edges of the rectbngle bre bt
     * <code>y</code> bnd <code>y&nbsp;+&nbsp;height&nbsp;-&nbsp;1</code>.
     * @pbrbm       x the <i>x</i> coordinbte of the rectbngle to be filled.
     * @pbrbm       y the <i>y</i> coordinbte of the rectbngle to be filled.
     * @pbrbm       width the width of the rectbngle to be filled.
     * @pbrbm       height the height of the rectbngle to be filled.
     * @pbrbm       brcWidth the horizontbl dibmeter
     *                     of the brc bt the four corners.
     * @pbrbm       brcHeight the verticbl dibmeter
     *                     of the brc bt the four corners.
     * @see         jbvb.bwt.Grbphics#drbwRoundRect
     */
    public void fillRoundRect(int x, int y, int width, int height,
                                       int brcWidth, int brcHeight) {
        g.fillRoundRect(x, y, width, height, brcWidth, brcHeight);
    }

    /**
     * Drbws b 3-D highlighted outline of the specified rectbngle.
     * The edges of the rectbngle bre highlighted so thbt they
     * bppebr to be beveled bnd lit from the upper left corner.
     * <p>
     * The colors used for the highlighting effect bre determined
     * bbsed on the current color.
     * The resulting rectbngle covers bn breb thbt is
     * <code>width&nbsp;+&nbsp;1</code> pixels wide
     * by <code>height&nbsp;+&nbsp;1</code> pixels tbll.
     * @pbrbm       x the <i>x</i> coordinbte of the rectbngle to be drbwn.
     * @pbrbm       y the <i>y</i> coordinbte of the rectbngle to be drbwn.
     * @pbrbm       width the width of the rectbngle to be drbwn.
     * @pbrbm       height the height of the rectbngle to be drbwn.
     * @pbrbm       rbised b boolebn thbt determines whether the rectbngle
     *                      bppebrs to be rbised bbove the surfbce
     *                      or sunk into the surfbce.
     * @see         jbvb.bwt.Grbphics#fill3DRect
     */
    public void drbw3DRect(int x, int y, int width, int height,
                           boolebn rbised) {
        g.drbw3DRect(x, y, width, height, rbised);
    }

    /**
     * Pbints b 3-D highlighted rectbngle filled with the current color.
     * The edges of the rectbngle will be highlighted so thbt it bppebrs
     * bs if the edges were beveled bnd lit from the upper left corner.
     * The colors used for the highlighting effect will be determined from
     * the current color.
     * @pbrbm       x the <i>x</i> coordinbte of the rectbngle to be filled.
     * @pbrbm       y the <i>y</i> coordinbte of the rectbngle to be filled.
     * @pbrbm       width the width of the rectbngle to be filled.
     * @pbrbm       height the height of the rectbngle to be filled.
     * @pbrbm       rbised b boolebn vblue thbt determines whether the
     *                      rectbngle bppebrs to be rbised bbove the surfbce
     *                      or etched into the surfbce.
     * @see         jbvb.bwt.Grbphics#drbw3DRect
     */
    public void fill3DRect(int x, int y, int width, int height,
                           boolebn rbised) {
        g.fill3DRect(x, y, width, height, rbised);
    }

    /**
     * Drbws the outline of bn ovbl.
     * The result is b circle or ellipse thbt fits within the
     * rectbngle specified by the <code>x</code>, <code>y</code>,
     * <code>width</code>, bnd <code>height</code> brguments.
     * <p>
     * The ovbl covers bn breb thbt is
     * <code>width&nbsp;+&nbsp;1</code> pixels wide
     * bnd <code>height&nbsp;+&nbsp;1</code> pixels tbll.
     * @pbrbm       x the <i>x</i> coordinbte of the upper left
     *                     corner of the ovbl to be drbwn.
     * @pbrbm       y the <i>y</i> coordinbte of the upper left
     *                     corner of the ovbl to be drbwn.
     * @pbrbm       width the width of the ovbl to be drbwn.
     * @pbrbm       height the height of the ovbl to be drbwn.
     * @see         jbvb.bwt.Grbphics#fillOvbl
     */
    public void drbwOvbl(int x, int y, int width, int height) {
        g.drbwOvbl(x, y, width, height);
    }

    /**
     * Fills bn ovbl bounded by the specified rectbngle with the
     * current color.
     * @pbrbm       x the <i>x</i> coordinbte of the upper left corner
     *                     of the ovbl to be filled.
     * @pbrbm       y the <i>y</i> coordinbte of the upper left corner
     *                     of the ovbl to be filled.
     * @pbrbm       width the width of the ovbl to be filled.
     * @pbrbm       height the height of the ovbl to be filled.
     * @see         jbvb.bwt.Grbphics#drbwOvbl
     */
    public void fillOvbl(int x, int y, int width, int height) {
        g.fillOvbl(x, y, width, height);
    }

    /**
     * Drbws the outline of b circulbr or ellipticbl brc
     * covering the specified rectbngle.
     * <p>
     * The resulting brc begins bt <code>stbrtAngle</code> bnd extends
     * for <code>brcAngle</code> degrees, using the current color.
     * Angles bre interpreted such thbt 0&nbsp;degrees
     * is bt the 3&nbsp;o'clock position.
     * A positive vblue indicbtes b counter-clockwise rotbtion
     * while b negbtive vblue indicbtes b clockwise rotbtion.
     * <p>
     * The center of the brc is the center of the rectbngle whose origin
     * is (<i>x</i>,&nbsp;<i>y</i>) bnd whose size is specified by the
     * <code>width</code> bnd <code>height</code> brguments.
     * <p>
     * The resulting brc covers bn breb
     * <code>width&nbsp;+&nbsp;1</code> pixels wide
     * by <code>height&nbsp;+&nbsp;1</code> pixels tbll.
     * <p>
     * The bngles bre specified relbtive to the non-squbre extents of
     * the bounding rectbngle such thbt 45 degrees blwbys fblls on the
     * line from the center of the ellipse to the upper right corner of
     * the bounding rectbngle. As b result, if the bounding rectbngle is
     * noticebbly longer in one bxis thbn the other, the bngles to the
     * stbrt bnd end of the brc segment will be skewed fbrther blong the
     * longer bxis of the bounds.
     * @pbrbm        x the <i>x</i> coordinbte of the
     *                    upper-left corner of the brc to be drbwn.
     * @pbrbm        y the <i>y</i>  coordinbte of the
     *                    upper-left corner of the brc to be drbwn.
     * @pbrbm        width the width of the brc to be drbwn.
     * @pbrbm        height the height of the brc to be drbwn.
     * @pbrbm        stbrtAngle the beginning bngle.
     * @pbrbm        brcAngle the bngulbr extent of the brc,
     *                    relbtive to the stbrt bngle.
     * @see         jbvb.bwt.Grbphics#fillArc
     */
    public void drbwArc(int x, int y, int width, int height,
                                 int stbrtAngle, int brcAngle) {
        g.drbwArc(x, y, width, height, stbrtAngle, brcAngle);
    }

    /**
     * Fills b circulbr or ellipticbl brc covering the specified rectbngle.
     * <p>
     * The resulting brc begins bt <code>stbrtAngle</code> bnd extends
     * for <code>brcAngle</code> degrees.
     * Angles bre interpreted such thbt 0&nbsp;degrees
     * is bt the 3&nbsp;o'clock position.
     * A positive vblue indicbtes b counter-clockwise rotbtion
     * while b negbtive vblue indicbtes b clockwise rotbtion.
     * <p>
     * The center of the brc is the center of the rectbngle whose origin
     * is (<i>x</i>,&nbsp;<i>y</i>) bnd whose size is specified by the
     * <code>width</code> bnd <code>height</code> brguments.
     * <p>
     * The resulting brc covers bn breb
     * <code>width&nbsp;+&nbsp;1</code> pixels wide
     * by <code>height&nbsp;+&nbsp;1</code> pixels tbll.
     * <p>
     * The bngles bre specified relbtive to the non-squbre extents of
     * the bounding rectbngle such thbt 45 degrees blwbys fblls on the
     * line from the center of the ellipse to the upper right corner of
     * the bounding rectbngle. As b result, if the bounding rectbngle is
     * noticebbly longer in one bxis thbn the other, the bngles to the
     * stbrt bnd end of the brc segment will be skewed fbrther blong the
     * longer bxis of the bounds.
     * @pbrbm        x the <i>x</i> coordinbte of the
     *                    upper-left corner of the brc to be filled.
     * @pbrbm        y the <i>y</i>  coordinbte of the
     *                    upper-left corner of the brc to be filled.
     * @pbrbm        width the width of the brc to be filled.
     * @pbrbm        height the height of the brc to be filled.
     * @pbrbm        stbrtAngle the beginning bngle.
     * @pbrbm        brcAngle the bngulbr extent of the brc,
     *                    relbtive to the stbrt bngle.
     * @see         jbvb.bwt.Grbphics#drbwArc
     */
    public void fillArc(int x, int y, int width, int height,
                                 int stbrtAngle, int brcAngle) {

        g.fillArc(x, y, width, height, stbrtAngle, brcAngle);
    }

    /**
     * Drbws b sequence of connected lines defined by
     * brrbys of <i>x</i> bnd <i>y</i> coordinbtes.
     * Ebch pbir of (<i>x</i>,&nbsp;<i>y</i>) coordinbtes defines b point.
     * The figure is not closed if the first point
     * differs from the lbst point.
     * @pbrbm       xPoints bn brrby of <i>x</i> points
     * @pbrbm       yPoints bn brrby of <i>y</i> points
     * @pbrbm       nPoints the totbl number of points
     * @see         jbvb.bwt.Grbphics#drbwPolygon(int[], int[], int)
     * @since       1.1
     */
    public void drbwPolyline(int xPoints[], int yPoints[],
                                      int nPoints) {
        g.drbwPolyline(xPoints, yPoints, nPoints);
    }

    /**
     * Drbws b closed polygon defined by
     * brrbys of <i>x</i> bnd <i>y</i> coordinbtes.
     * Ebch pbir of (<i>x</i>,&nbsp;<i>y</i>) coordinbtes defines b point.
     * <p>
     * This method drbws the polygon defined by <code>nPoint</code> line
     * segments, where the first <code>nPoint&nbsp;-&nbsp;1</code>
     * line segments bre line segments from
     * <code>(xPoints[i&nbsp;-&nbsp;1],&nbsp;yPoints[i&nbsp;-&nbsp;1])</code>
     * to <code>(xPoints[i],&nbsp;yPoints[i])</code>, for
     * 1&nbsp;&le;&nbsp;<i>i</i>&nbsp;&le;&nbsp;<code>nPoints</code>.
     * The figure is butombticblly closed by drbwing b line connecting
     * the finbl point to the first point, if those points bre different.
     * @pbrbm        xPoints   b bn brrby of <code>x</code> coordinbtes.
     * @pbrbm        yPoints   b bn brrby of <code>y</code> coordinbtes.
     * @pbrbm        nPoints   b the totbl number of points.
     * @see          jbvb.bwt.Grbphics#fillPolygon
     * @see          jbvb.bwt.Grbphics#drbwPolyline
     */
    public void drbwPolygon(int xPoints[], int yPoints[],
                                     int nPoints) {
        g.drbwPolygon(xPoints, yPoints, nPoints);
    }

    /**
     * Drbws the outline of b polygon defined by the specified
     * <code>Polygon</code> object.
     * @pbrbm        p the polygon to drbw.
     * @see          jbvb.bwt.Grbphics#fillPolygon
     * @see          jbvb.bwt.Grbphics#drbwPolyline
     */
    public void drbwPolygon(Polygon p) {
        g.drbwPolygon(p);
    }

    /**
     * Fills b closed polygon defined by
     * brrbys of <i>x</i> bnd <i>y</i> coordinbtes.
     * <p>
     * This method drbws the polygon defined by <code>nPoint</code> line
     * segments, where the first <code>nPoint&nbsp;-&nbsp;1</code>
     * line segments bre line segments from
     * <code>(xPoints[i&nbsp;-&nbsp;1],&nbsp;yPoints[i&nbsp;-&nbsp;1])</code>
     * to <code>(xPoints[i],&nbsp;yPoints[i])</code>, for
     * 1&nbsp;&le;&nbsp;<i>i</i>&nbsp;&le;&nbsp;<code>nPoints</code>.
     * The figure is butombticblly closed by drbwing b line connecting
     * the finbl point to the first point, if those points bre different.
     * <p>
     * The breb inside the polygon is defined using bn
     * even-odd fill rule, blso known bs the blternbting rule.
     * @pbrbm        xPoints   b bn brrby of <code>x</code> coordinbtes.
     * @pbrbm        yPoints   b bn brrby of <code>y</code> coordinbtes.
     * @pbrbm        nPoints   b the totbl number of points.
     * @see          jbvb.bwt.Grbphics#drbwPolygon(int[], int[], int)
     */
    public void fillPolygon(int xPoints[], int yPoints[],
                                     int nPoints) {
        g.fillPolygon(xPoints, yPoints, nPoints);
    }

    /**
     * Fills the polygon defined by the specified Polygon object with
     * the grbphics context's current color.
     * <p>
     * The breb inside the polygon is defined using bn
     * even-odd fill rule, blso known bs the blternbting rule.
     * @pbrbm        p the polygon to fill.
     * @see          jbvb.bwt.Grbphics#drbwPolygon(int[], int[], int)
     */
    public void fillPolygon(Polygon p) {
        g.fillPolygon(p);
    }

    /**
     * Drbws the text given by the specified string, using this
     * grbphics context's current font bnd color. The bbseline of the
     * leftmost chbrbcter is bt position (<i>x</i>,&nbsp;<i>y</i>) in this
     * grbphics context's coordinbte system.
     * @pbrbm       str      the string to be drbwn.
     * @pbrbm       x        the <i>x</i> coordinbte.
     * @pbrbm       y        the <i>y</i> coordinbte.
     * @see         jbvb.bwt.Grbphics#drbwBytes
     * @see         jbvb.bwt.Grbphics#drbwChbrs
     */
    public void drbwString(String str, int x, int y) {
        g.drbwString(str, x, y);
    }

    /**
     * Drbws the text given by the specified iterbtor, using this
     * grbphics context's current color. The iterbtor hbs to specify b font
     * for ebch chbrbcter. The bbseline of the
     * leftmost chbrbcter is bt position (<i>x</i>,&nbsp;<i>y</i>) in this
     * grbphics context's coordinbte system.
     * @pbrbm       iterbtor the iterbtor whose text is to be drbwn
     * @pbrbm       x        the <i>x</i> coordinbte.
     * @pbrbm       y        the <i>y</i> coordinbte.
     * @see         jbvb.bwt.Grbphics#drbwBytes
     * @see         jbvb.bwt.Grbphics#drbwChbrs
     */
   public void drbwString(AttributedChbrbcterIterbtor iterbtor,
                                    int x, int y) {
        g.drbwString(iterbtor, x, y);
    }

    /**
     * Drbws the text given by the specified chbrbcter brrby, using this
     * grbphics context's current font bnd color. The bbseline of the
     * first chbrbcter is bt position (<i>x</i>,&nbsp;<i>y</i>) in this
     * grbphics context's coordinbte system.
     * @pbrbm dbtb the brrby of chbrbcters to be drbwn
     * @pbrbm offset the stbrt offset in the dbtb
     * @pbrbm length the number of chbrbcters to be drbwn
     * @pbrbm x the <i>x</i> coordinbte of the bbseline of the text
     * @pbrbm y the <i>y</i> coordinbte of the bbseline of the text
     * @see         jbvb.bwt.Grbphics#drbwBytes
     * @see         jbvb.bwt.Grbphics#drbwString
     */
    public void drbwChbrs(chbr dbtb[], int offset, int length, int x, int y) {
        g.drbwChbrs(dbtb, offset, length, x, y);
    }

    /**
     * Drbws the text given by the specified byte brrby, using this
     * grbphics context's current font bnd color. The bbseline of the
     * first chbrbcter is bt position (<i>x</i>,&nbsp;<i>y</i>) in this
     * grbphics context's coordinbte system.
     * @pbrbm dbtb the dbtb to be drbwn
     * @pbrbm offset the stbrt offset in the dbtb
     * @pbrbm length the number of bytes thbt bre drbwn
     * @pbrbm x the <i>x</i> coordinbte of the bbseline of the text
     * @pbrbm y the <i>y</i> coordinbte of the bbseline of the text
     * @see         jbvb.bwt.Grbphics#drbwChbrs
     * @see         jbvb.bwt.Grbphics#drbwString
     */
    public void drbwBytes(byte dbtb[], int offset, int length, int x, int y) {
        g.drbwBytes(dbtb, offset, length, x, y);
    }

    /**
     * Drbws bs much of the specified imbge bs is currently bvbilbble.
     * The imbge is drbwn with its top-left corner bt
     * (<i>x</i>,&nbsp;<i>y</i>) in this grbphics context's coordinbte
     * spbce. Trbnspbrent pixels in the imbge do not bffect whbtever
     * pixels bre blrebdy there.
     * <p>
     * This method returns immedibtely in bll cbses, even if the
     * complete imbge hbs not yet been lobded, bnd it hbs not been dithered
     * bnd converted for the current output device.
     * <p>
     * If the imbge hbs not yet been completely lobded, then
     * <code>drbwImbge</code> returns <code>fblse</code>. As more of
     * the imbge becomes bvbilbble, the process thbt drbws the imbge notifies
     * the specified imbge observer.
     * @pbrbm    img the specified imbge to be drbwn.
     * @pbrbm    x   the <i>x</i> coordinbte.
     * @pbrbm    y   the <i>y</i> coordinbte.
     * @pbrbm    observer    object to be notified bs more of
     *                          the imbge is converted.
     * @see      jbvb.bwt.Imbge
     * @see      jbvb.bwt.imbge.ImbgeObserver
     * @see      jbvb.bwt.imbge.ImbgeObserver#imbgeUpdbte(jbvb.bwt.Imbge, int, int, int, int, int)
     */
    public boolebn drbwImbge(Imbge img, int x, int y,
                                      ImbgeObserver observer) {
        return g.drbwImbge(img, x, y, observer);
    }

    /**
     * Drbws bs much of the specified imbge bs hbs blrebdy been scbled
     * to fit inside the specified rectbngle.
     * <p>
     * The imbge is drbwn inside the specified rectbngle of this
     * grbphics context's coordinbte spbce, bnd is scbled if
     * necessbry. Trbnspbrent pixels do not bffect whbtever pixels
     * bre blrebdy there.
     * <p>
     * This method returns immedibtely in bll cbses, even if the
     * entire imbge hbs not yet been scbled, dithered, bnd converted
     * for the current output device.
     * If the current output representbtion is not yet complete, then
     * <code>drbwImbge</code> returns <code>fblse</code>. As more of
     * the imbge becomes bvbilbble, the process thbt drbws the imbge notifies
     * the imbge observer by cblling its <code>imbgeUpdbte</code> method.
     * <p>
     * A scbled version of bn imbge will not necessbrily be
     * bvbilbble immedibtely just becbuse bn unscbled version of the
     * imbge hbs been constructed for this output device.  Ebch size of
     * the imbge mby be cbched sepbrbtely bnd generbted from the originbl
     * dbtb in b sepbrbte imbge production sequence.
     * @pbrbm    img    the specified imbge to be drbwn.
     * @pbrbm    x      the <i>x</i> coordinbte.
     * @pbrbm    y      the <i>y</i> coordinbte.
     * @pbrbm    width  the width of the rectbngle.
     * @pbrbm    height the height of the rectbngle.
     * @pbrbm    observer    object to be notified bs more of
     *                          the imbge is converted.
     * @see      jbvb.bwt.Imbge
     * @see      jbvb.bwt.imbge.ImbgeObserver
     * @see      jbvb.bwt.imbge.ImbgeObserver#imbgeUpdbte(jbvb.bwt.Imbge, int, int, int, int, int)
     */
    public boolebn drbwImbge(Imbge img, int x, int y,
                                      int width, int height,
                                      ImbgeObserver observer) {
        return g.drbwImbge(img, x, y, width, height, observer);
    }

    /**
     * Drbws bs much of the specified imbge bs is currently bvbilbble.
     * The imbge is drbwn with its top-left corner bt
     * (<i>x</i>,&nbsp;<i>y</i>) in this grbphics context's coordinbte
     * spbce.  Trbnspbrent pixels bre drbwn in the specified
     * bbckground color.
     * <p>
     * This operbtion is equivblent to filling b rectbngle of the
     * width bnd height of the specified imbge with the given color bnd then
     * drbwing the imbge on top of it, but possibly more efficient.
     * <p>
     * This method returns immedibtely in bll cbses, even if the
     * complete imbge hbs not yet been lobded, bnd it hbs not been dithered
     * bnd converted for the current output device.
     * <p>
     * If the imbge hbs not yet been completely lobded, then
     * <code>drbwImbge</code> returns <code>fblse</code>. As more of
     * the imbge becomes bvbilbble, the process thbt drbws the imbge notifies
     * the specified imbge observer.
     * @pbrbm    img    the specified imbge to be drbwn.
     * @pbrbm    x      the <i>x</i> coordinbte.
     * @pbrbm    y      the <i>y</i> coordinbte.
     * @pbrbm    bgcolor the bbckground color to pbint under the
     *                         non-opbque portions of the imbge.
     * @pbrbm    observer    object to be notified bs more of
     *                          the imbge is converted.
     * @see      jbvb.bwt.Imbge
     * @see      jbvb.bwt.imbge.ImbgeObserver
     * @see      jbvb.bwt.imbge.ImbgeObserver#imbgeUpdbte(jbvb.bwt.Imbge, int, int, int, int, int)
     */
    public boolebn drbwImbge(Imbge img, int x, int y,
                                      Color bgcolor,
                                      ImbgeObserver observer) {
        return g.drbwImbge(img, x, y, bgcolor, observer);
    }

    /**
     * Drbws bs much of the specified imbge bs hbs blrebdy been scbled
     * to fit inside the specified rectbngle.
     * <p>
     * The imbge is drbwn inside the specified rectbngle of this
     * grbphics context's coordinbte spbce, bnd is scbled if
     * necessbry. Trbnspbrent pixels bre drbwn in the specified
     * bbckground color.
     * This operbtion is equivblent to filling b rectbngle of the
     * width bnd height of the specified imbge with the given color bnd then
     * drbwing the imbge on top of it, but possibly more efficient.
     * <p>
     * This method returns immedibtely in bll cbses, even if the
     * entire imbge hbs not yet been scbled, dithered, bnd converted
     * for the current output device.
     * If the current output representbtion is not yet complete then
     * <code>drbwImbge</code> returns <code>fblse</code>. As more of
     * the imbge becomes bvbilbble, the process thbt drbws the imbge notifies
     * the specified imbge observer.
     * <p>
     * A scbled version of bn imbge will not necessbrily be
     * bvbilbble immedibtely just becbuse bn unscbled version of the
     * imbge hbs been constructed for this output device.  Ebch size of
     * the imbge mby be cbched sepbrbtely bnd generbted from the originbl
     * dbtb in b sepbrbte imbge production sequence.
     * @pbrbm    img       the specified imbge to be drbwn.
     * @pbrbm    x         the <i>x</i> coordinbte.
     * @pbrbm    y         the <i>y</i> coordinbte.
     * @pbrbm    width     the width of the rectbngle.
     * @pbrbm    height    the height of the rectbngle.
     * @pbrbm    bgcolor   the bbckground color to pbint under the
     *                         non-opbque portions of the imbge.
     * @pbrbm    observer    object to be notified bs more of
     *                          the imbge is converted.
     * @see      jbvb.bwt.Imbge
     * @see      jbvb.bwt.imbge.ImbgeObserver
     * @see      jbvb.bwt.imbge.ImbgeObserver#imbgeUpdbte(jbvb.bwt.Imbge, int, int, int, int, int)
     */
    public boolebn drbwImbge(Imbge img, int x, int y,
                                      int width, int height,
                                      Color bgcolor,
                                      ImbgeObserver observer) {

        return g.drbwImbge(img, x, y, width, height, bgcolor, observer);
    }

    /**
     * Drbws bs much of the specified breb of the specified imbge bs is
     * currently bvbilbble, scbling it on the fly to fit inside the
     * specified breb of the destinbtion drbwbble surfbce. Trbnspbrent pixels
     * do not bffect whbtever pixels bre blrebdy there.
     * <p>
     * This method returns immedibtely in bll cbses, even if the
     * imbge breb to be drbwn hbs not yet been scbled, dithered, bnd converted
     * for the current output device.
     * If the current output representbtion is not yet complete then
     * <code>drbwImbge</code> returns <code>fblse</code>. As more of
     * the imbge becomes bvbilbble, the process thbt drbws the imbge notifies
     * the specified imbge observer.
     * <p>
     * This method blwbys uses the unscbled version of the imbge
     * to render the scbled rectbngle bnd performs the required
     * scbling on the fly. It does not use b cbched, scbled version
     * of the imbge for this operbtion. Scbling of the imbge from source
     * to destinbtion is performed such thbt the first coordinbte
     * of the source rectbngle is mbpped to the first coordinbte of
     * the destinbtion rectbngle, bnd the second source coordinbte is
     * mbpped to the second destinbtion coordinbte. The subimbge is
     * scbled bnd flipped bs needed to preserve those mbppings.
     * @pbrbm       img the specified imbge to be drbwn
     * @pbrbm       dx1 the <i>x</i> coordinbte of the first corner of the
     *                    destinbtion rectbngle.
     * @pbrbm       dy1 the <i>y</i> coordinbte of the first corner of the
     *                    destinbtion rectbngle.
     * @pbrbm       dx2 the <i>x</i> coordinbte of the second corner of the
     *                    destinbtion rectbngle.
     * @pbrbm       dy2 the <i>y</i> coordinbte of the second corner of the
     *                    destinbtion rectbngle.
     * @pbrbm       sx1 the <i>x</i> coordinbte of the first corner of the
     *                    source rectbngle.
     * @pbrbm       sy1 the <i>y</i> coordinbte of the first corner of the
     *                    source rectbngle.
     * @pbrbm       sx2 the <i>x</i> coordinbte of the second corner of the
     *                    source rectbngle.
     * @pbrbm       sy2 the <i>y</i> coordinbte of the second corner of the
     *                    source rectbngle.
     * @pbrbm       observer object to be notified bs more of the imbge is
     *                    scbled bnd converted.
     * @see         jbvb.bwt.Imbge
     * @see         jbvb.bwt.imbge.ImbgeObserver
     * @see         jbvb.bwt.imbge.ImbgeObserver#imbgeUpdbte(jbvb.bwt.Imbge, int, int, int, int, int)
     * @since       1.1
     */
    public boolebn drbwImbge(Imbge img,
                                      int dx1, int dy1, int dx2, int dy2,
                                      int sx1, int sy1, int sx2, int sy2,
                                      ImbgeObserver observer) {

        return g.drbwImbge(img, dx1, dy1, dx2, dy2,
                                  sx1, sy1, sx2, sy2,
                                  observer);
    }

    /**
     * Drbws bs much of the specified breb of the specified imbge bs is
     * currently bvbilbble, scbling it on the fly to fit inside the
     * specified breb of the destinbtion drbwbble surfbce.
     * <p>
     * Trbnspbrent pixels bre drbwn in the specified bbckground color.
     * This operbtion is equivblent to filling b rectbngle of the
     * width bnd height of the specified imbge with the given color bnd then
     * drbwing the imbge on top of it, but possibly more efficient.
     * <p>
     * This method returns immedibtely in bll cbses, even if the
     * imbge breb to be drbwn hbs not yet been scbled, dithered, bnd converted
     * for the current output device.
     * If the current output representbtion is not yet complete then
     * <code>drbwImbge</code> returns <code>fblse</code>. As more of
     * the imbge becomes bvbilbble, the process thbt drbws the imbge notifies
     * the specified imbge observer.
     * <p>
     * This method blwbys uses the unscbled version of the imbge
     * to render the scbled rectbngle bnd performs the required
     * scbling on the fly. It does not use b cbched, scbled version
     * of the imbge for this operbtion. Scbling of the imbge from source
     * to destinbtion is performed such thbt the first coordinbte
     * of the source rectbngle is mbpped to the first coordinbte of
     * the destinbtion rectbngle, bnd the second source coordinbte is
     * mbpped to the second destinbtion coordinbte. The subimbge is
     * scbled bnd flipped bs needed to preserve those mbppings.
     * @pbrbm       img the specified imbge to be drbwn
     * @pbrbm       dx1 the <i>x</i> coordinbte of the first corner of the
     *                    destinbtion rectbngle.
     * @pbrbm       dy1 the <i>y</i> coordinbte of the first corner of the
     *                    destinbtion rectbngle.
     * @pbrbm       dx2 the <i>x</i> coordinbte of the second corner of the
     *                    destinbtion rectbngle.
     * @pbrbm       dy2 the <i>y</i> coordinbte of the second corner of the
     *                    destinbtion rectbngle.
     * @pbrbm       sx1 the <i>x</i> coordinbte of the first corner of the
     *                    source rectbngle.
     * @pbrbm       sy1 the <i>y</i> coordinbte of the first corner of the
     *                    source rectbngle.
     * @pbrbm       sx2 the <i>x</i> coordinbte of the second corner of the
     *                    source rectbngle.
     * @pbrbm       sy2 the <i>y</i> coordinbte of the second corner of the
     *                    source rectbngle.
     * @pbrbm       bgcolor the bbckground color to pbint under the
     *                    non-opbque portions of the imbge.
     * @pbrbm       observer object to be notified bs more of the imbge is
     *                    scbled bnd converted.
     * @see         jbvb.bwt.Imbge
     * @see         jbvb.bwt.imbge.ImbgeObserver
     * @see         jbvb.bwt.imbge.ImbgeObserver#imbgeUpdbte(jbvb.bwt.Imbge, int, int, int, int, int)
     * @since       1.1
     */
    public boolebn drbwImbge(Imbge img,
                                      int dx1, int dy1, int dx2, int dy2,
                                      int sx1, int sy1, int sx2, int sy2,
                                      Color bgcolor,
                                      ImbgeObserver observer) {

        return g.drbwImbge(img, dx1, dy1, dx2, dy2,
                                  sx1, sy1, sx2, sy2,
                                  bgcolor,
                                  observer);
    }

    /**
     * Disposes of this grbphics context bnd relebses
     * bny system resources thbt it is using.
     * A <code>Grbphics</code> object cbnnot be used bfter
     * <code>dispose</code>hbs been cblled.
     * <p>
     * When b Jbvb progrbm runs, b lbrge number of <code>Grbphics</code>
     * objects cbn be crebted within b short time frbme.
     * Although the finblizbtion process of the gbrbbge collector
     * blso disposes of the sbme system resources, it is preferbble
     * to mbnublly free the bssocibted resources by cblling this
     * method rbther thbn to rely on b finblizbtion process which
     * mby not run to completion for b long period of time.
     * <p>
     * Grbphics objects which bre provided bs brguments to the
     * <code>pbint</code> bnd <code>updbte</code> methods
     * of components bre butombticblly relebsed by the system when
     * those methods return. For efficiency, progrbmmers should
     * cbll <code>dispose</code> when finished using
     * b <code>Grbphics</code> object only if it wbs crebted
     * directly from b component or bnother <code>Grbphics</code> object.
     * @see         jbvb.bwt.Grbphics#finblize
     * @see         jbvb.bwt.Component#pbint
     * @see         jbvb.bwt.Component#updbte
     * @see         jbvb.bwt.Component#getGrbphics
     * @see         jbvb.bwt.Grbphics#crebte
     */
    public void dispose() {
        g.dispose();
    }

    /**
     * Empty finblizer bs no clebn up needed here.
     */
    public void finblize() {
    }

    /**
     * Returns b <code>String</code> object representing this
     *                        <code>Grbphics</code> object's vblue.
     * @return       b string representbtion of this grbphics context.
     */
    public String toString() {
        return getClbss().getNbme() + "[font=" + getFont() + ",color=" + getColor() + "]";
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getClipBounds()</code>.
     */
    @Deprecbted
    public Rectbngle getClipRect() {
        return g.getClipRect();
    }

    /**
     * Returns true if the specified rectbngulbr breb intersects
     * the bounding rectbngle of the current clipping breb.
     * The coordinbtes in the rectbngle bre relbtive to the coordinbte
     * system origin of this grbphics context.
     *
     * @pbrbm x the x coordinbte of the rectbngle to test bgbinst the clip
     * @pbrbm y the y coordinbte of the rectbngle to test bgbinst the clip
     * @pbrbm width the width of the rectbngle to test bgbinst the clip
     * @pbrbm height the height of the rectbngle to test bgbinst the clip
     */
    public boolebn hitClip(int x, int y, int width, int height) {
        return g.hitClip(x, y, width, height);
    }

    /**
     * Returns the bounding rectbngle of the current clipping breb.
     * The coordinbtes in the rectbngle bre relbtive to the coordinbte
     * system origin of this grbphics context.  This method differs
     * from {@link #getClipBounds() getClipBounds} in thbt bn existing
     * rectbngle is used instebd of bllocbting b new one.
     * This method refers to the user clip, which is independent of the
     * clipping bssocibted with device bounds bnd window visibility.
     *  If no clip hbs previously been set, or if the clip hbs been
     * clebred using <code>setClip(null)</code>, this method returns the
     * specified <code>Rectbngle</code>.
     * @pbrbm  r    the rectbngle where the current clipping breb is
     *              copied to.  Any current vblues in this rectbngle bre
     *              overwritten.
     * @return      the bounding rectbngle of the current clipping breb.
     */
    public Rectbngle getClipBounds(Rectbngle r) {
        return g.getClipBounds(r);
    }
}
