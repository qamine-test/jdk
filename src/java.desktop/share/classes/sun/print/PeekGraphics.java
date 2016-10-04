/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Mbp;

import jbvb.bwt.BbsicStroke;
import jbvb.bwt.Color;
import jbvb.bwt.Composite;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Font;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.Grbphics;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.Imbge;
import jbvb.bwt.Pbint;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;
import jbvb.bwt.Stroke;
import jbvb.bwt.RenderingHints;
import jbvb.bwt.RenderingHints.Key;

import jbvb.bwt.font.GlyphVector;
import jbvb.bwt.font.TextLbyout;

import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.Line2D;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.geom.RoundRectbngle2D;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.BufferedImbgeOp;
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.bwt.imbge.RenderedImbge;
import jbvb.bwt.imbge.renderbble.RenderbbleImbge;
import jbvb.bwt.print.PrinterGrbphics;
import jbvb.bwt.print.PrinterJob;

import jbvb.text.AttributedChbrbcterIterbtor;

import sun.jbvb2d.Spbns;

public clbss PeekGrbphics extends Grbphics2D
                          implements PrinterGrbphics,
                                     ImbgeObserver,
                                     Clonebble {

    /**
     * Drbwing methods will be forwbrded to this object.
     */
    Grbphics2D mGrbphics;

    /**
     * The PrinterJob controlling the current printing.
     */
    PrinterJob mPrinterJob;

    /**
     * Keeps trbck of where drbwing occurs on the pbge.
     */
    privbte Spbns mDrbwingAreb = new Spbns();

    /**
     * Trbck informbtion bbout the types of drbwing
     * performed by the printing bpplicbtion.
     */
    privbte PeekMetrics mPrintMetrics = new PeekMetrics();

    /**
     * If true the bpplicbtion will only be drbwing AWT style
     * grbphics, no Jbvb2D grbphics.
     */
    privbte boolebn mAWTDrbwingOnly = fblse;

    /**
     * The new PeekGrbphics2D will forwbrd stbte chbnging
     * cblls to 'grbphics'. 'printerJob' is stored bwby
     * so thbt the printing bpplicbtion cbn get the PrinterJob
     * if needed.
     */
    public PeekGrbphics(Grbphics2D grbphics, PrinterJob printerJob) {

        mGrbphics = grbphics;
        mPrinterJob = printerJob;
    }

    /**
     * Return the Grbphics2D object thbt does the drbwing
     * for this instbnce.
     */
    public Grbphics2D getDelegbte() {
        return mGrbphics;
    }

    /**
     * Set the Grbphics2D instbnce which will do the
     * drbwing.
     */
    public void setDelegbte(Grbphics2D grbphics) {
        mGrbphics = grbphics;
    }

    public PrinterJob getPrinterJob() {
        return mPrinterJob;
    }

    /**
     * The cbller promises thbt only AWT grbphics will be drbwn.
     * The print system cbn use this informbtion to mbke generbl
     * bssumptions bbout the types of grbphics to be drbwn without
     * requiring the bpplicbtion to drbw the contents multiple
     * times.
     */
    public void setAWTDrbwingOnly() {
        mAWTDrbwingOnly = true;
    }

    public boolebn getAWTDrbwingOnly() {
        return mAWTDrbwingOnly;
    }

    /**
     * Return b Spbns instbnce describing the pbrts of the pbge in
     * to which drbwing occurred.
     */
    public Spbns getDrbwingAreb() {
        return mDrbwingAreb;
    }

    /**
     * Returns the device configurbtion bssocibted with this Grbphics2D.
     */
    public GrbphicsConfigurbtion getDeviceConfigurbtion() {
        return ((RbsterPrinterJob)mPrinterJob).getPrinterGrbphicsConfig();
    }

/* The Delegbted Grbphics Methods */

    /**
     * Crebtes b new <code>Grbphics</code> object thbt is
     * b copy of this <code>Grbphics</code> object.
     * @return     b new grbphics context thbt is b copy of
     *                       this grbphics context.
     * @since      1.0
     */
    public Grbphics crebte() {
        PeekGrbphics newGrbphics = null;

        try {
            newGrbphics = (PeekGrbphics) clone();
            newGrbphics.mGrbphics = (Grbphics2D) mGrbphics.crebte();

        /* This exception cbn not hbppen unless this
         * clbss no longer implements the Clonebble
         * interfbce.
         */
        } cbtch (CloneNotSupportedException e) {
            // cbn never hbppen.
        }

        return newGrbphics;
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
     * @since   1.0
     */
    public void trbnslbte(int x, int y) {
        mGrbphics.trbnslbte(x, y);
    }

    /**
     * Concbtenbtes the current trbnsform of this Grbphics2D with b
     * trbnslbtion trbnsformbtion.
     * This is equivblent to cblling trbnsform(T), where T is bn
     * AffineTrbnsform represented by the following mbtrix:
     * <pre>
     *          [   1    0    tx  ]
     *          [   0    1    ty  ]
     *          [   0    0    1   ]
     * </pre>
     */
    public void trbnslbte(double tx, double ty) {
        mGrbphics.trbnslbte(tx, ty);
    }

    /**
     * Concbtenbtes the current trbnsform of this Grbphics2D with b
     * rotbtion trbnsformbtion.
     * This is equivblent to cblling trbnsform(R), where R is bn
     * AffineTrbnsform represented by the following mbtrix:
     * <pre>
     *          [   cos(thetb)    -sin(thetb)    0   ]
     *          [   sin(thetb)     cos(thetb)    0   ]
     *          [       0              0         1   ]
     * </pre>
     * Rotbting with b positive bngle thetb rotbtes points on the positive
     * x bxis towbrd the positive y bxis.
     * @pbrbm thetb The bngle of rotbtion in rbdibns.
     */
    public void rotbte(double thetb) {
        mGrbphics.rotbte(thetb);
    }

    /**
     * Concbtenbtes the current trbnsform of this Grbphics2D with b
     * trbnslbted rotbtion trbnsformbtion.
     * This is equivblent to the following sequence of cblls:
     * <pre>
     *          trbnslbte(x, y);
     *          rotbte(thetb);
     *          trbnslbte(-x, -y);
     * </pre>
     * Rotbting with b positive bngle thetb rotbtes points on the positive
     * x bxis towbrd the positive y bxis.
     * @pbrbm thetb The bngle of rotbtion in rbdibns.
     * @pbrbm x The x coordinbte of the origin of the rotbtion
     * @pbrbm y The x coordinbte of the origin of the rotbtion
     */
    public void rotbte(double thetb, double x, double y) {
        mGrbphics.rotbte(thetb, x, y);
    }

    /**
     * Concbtenbtes the current trbnsform of this Grbphics2D with b
     * scbling trbnsformbtion.
     * This is equivblent to cblling trbnsform(S), where S is bn
     * AffineTrbnsform represented by the following mbtrix:
     * <pre>
     *          [   sx   0    0   ]
     *          [   0    sy   0   ]
     *          [   0    0    1   ]
     * </pre>
     */
    public void scble(double sx, double sy) {
        mGrbphics.scble(sx, sy);
    }

    /**
     * Concbtenbtes the current trbnsform of this Grbphics2D with b
     * shebring trbnsformbtion.
     * This is equivblent to cblling trbnsform(SH), where SH is bn
     * AffineTrbnsform represented by the following mbtrix:
     * <pre>
     *          [   1   shx   0   ]
     *          [  shy   1    0   ]
     *          [   0    0    1   ]
     * </pre>
     * @pbrbm shx The fbctor by which coordinbtes bre shifted towbrds the
     * positive X bxis direction bccording to their Y coordinbte
     * @pbrbm shy The fbctor by which coordinbtes bre shifted towbrds the
     * positive Y bxis direction bccording to their X coordinbte
     */
    public void shebr(double shx, double shy) {
        mGrbphics.shebr(shx, shy);
    }

    /**
     * Gets this grbphics context's current color.
     * @return    this grbphics context's current color.
     * @see       jbvb.bwt.Color
     * @see       jbvb.bwt.Grbphics#setColor
     * @since     1.0
     */
    public Color getColor() {
        return mGrbphics.getColor();
    }

    /**
     * Sets this grbphics context's current color to the specified
     * color. All subsequent grbphics operbtions using this grbphics
     * context use this specified color.
     * @pbrbm     c   the new rendering color.
     * @see       jbvb.bwt.Color
     * @see       jbvb.bwt.Grbphics#getColor
     * @since     1.0
     */
    public void setColor(Color c) {
        mGrbphics.setColor(c);
    }

    /**
     * Sets the pbint mode of this grbphics context to overwrite the
     * destinbtion with this grbphics context's current color.
     * This sets the logicbl pixel operbtion function to the pbint or
     * overwrite mode.  All subsequent rendering operbtions will
     * overwrite the destinbtion with the current color.
     * @since   1.0
     */
    public void setPbintMode() {
        mGrbphics.setPbintMode();
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
     * @since     1.0
     */
    public void setXORMode(Color c1) {
        mGrbphics.setXORMode(c1);
    }

    /**
     * Gets the current font.
     * @return    this grbphics context's current font.
     * @see       jbvb.bwt.Font
     * @see       jbvb.bwt.Grbphics#setFont
     * @since     1.0
     */
    public Font getFont() {
        return mGrbphics.getFont();
    }

    /**
     * Sets this grbphics context's font to the specified font.
     * All subsequent text operbtions using this grbphics context
     * use this font.
     * @pbrbm  font   the font.
     * @see     jbvb.bwt.Grbphics#getFont
     * @see     jbvb.bwt.Grbphics#drbwChbrs(jbvb.lbng.String, int, int)
     * @see     jbvb.bwt.Grbphics#drbwString(byte[], int, int, int, int)
     * @see     jbvb.bwt.Grbphics#drbwBytes(chbr[], int, int, int, int)
     * @since   1.0
    */
    public void setFont(Font font) {
        mGrbphics.setFont(font);
    }

    /**
     * Gets the font metrics for the specified font.
     * @return    the font metrics for the specified font.
     * @pbrbm     f the specified font
     * @see       jbvb.bwt.Grbphics#getFont
     * @see       jbvb.bwt.FontMetrics
     * @see       jbvb.bwt.Grbphics#getFontMetrics()
     * @since     1.0
     */
    public FontMetrics getFontMetrics(Font f) {
        return mGrbphics.getFontMetrics(f);
    }

    /**
    * Get the rendering context of the font
    * within this Grbphics2D context.
    */
    public FontRenderContext getFontRenderContext() {
        return mGrbphics.getFontRenderContext();
    }

    /**
     * Returns the bounding rectbngle of the current clipping breb.
     * The coordinbtes in the rectbngle bre relbtive to the coordinbte
     * system origin of this grbphics context.
     * @return      the bounding rectbngle of the current clipping breb.
     * @see         jbvb.bwt.Grbphics#getClip
     * @see         jbvb.bwt.Grbphics#clipRect
     * @see         jbvb.bwt.Grbphics#setClip(int, int, int, int)
     * @see         jbvb.bwt.Grbphics#setClip(Shbpe)
     * @since       1.1
     */
    public Rectbngle getClipBounds() {
        return mGrbphics.getClipBounds();
    }


    /**
     * Intersects the current clip with the specified rectbngle.
     * The resulting clipping breb is the intersection of the current
     * clipping breb bnd the specified rectbngle.
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
        mGrbphics.clipRect(x, y, width, height);
    }


    /**
     * Sets the current clip to the rectbngle specified by the given
     * coordinbtes.
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
        mGrbphics.setClip(x, y, width, height);
    }

    /**
     * Gets the current clipping breb.
     * @return      b <code>Shbpe</code> object representing the
     *                      current clipping breb.
     * @see         jbvb.bwt.Grbphics#getClipBounds
     * @see         jbvb.bwt.Grbphics#clipRect
     * @see         jbvb.bwt.Grbphics#setClip(int, int, int, int)
     * @see         jbvb.bwt.Grbphics#setClip(Shbpe)
     * @since       1.1
     */
    public Shbpe getClip() {
        return mGrbphics.getClip();
    }


    /**
     * Sets the current clipping breb to bn brbitrbry clip shbpe.
     * Not bll objects which implement the <code>Shbpe</code>
     * interfbce cbn be used to set the clip.  The only
     * <code>Shbpe</code> objects which bre gubrbnteed to be
     * supported bre <code>Shbpe</code> objects which bre
     * obtbined vib the <code>getClip</code> method bnd vib
     * <code>Rectbngle</code> objects.
     * @see         jbvb.bwt.Grbphics#getClip()
     * @see         jbvb.bwt.Grbphics#clipRect
     * @see         jbvb.bwt.Grbphics#setClip(int, int, int, int)
     * @since       1.1
     */
    public void setClip(Shbpe clip) {
        mGrbphics.setClip(clip);
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
     * @since       1.0
     */
    public void copyAreb(int x, int y, int width, int height,
                         int dx, int dy) {
        // This method is not supported for printing so we do nothing here.
    }

    /**
     * Drbws b line, using the current color, between the points
     * <code>(x1,&nbsp;y1)</code> bnd <code>(x2,&nbsp;y2)</code>
     * in this grbphics context's coordinbte system.
     * @pbrbm   x1  the first point's <i>x</i> coordinbte.
     * @pbrbm   y1  the first point's <i>y</i> coordinbte.
     * @pbrbm   x2  the second point's <i>x</i> coordinbte.
     * @pbrbm   y2  the second point's <i>y</i> coordinbte.
     * @since   1.0
     */
    public void drbwLine(int x1, int y1, int x2, int y2) {
        bddStrokeShbpe(new Line2D.Flobt(x1, y1, x2, y2));
        mPrintMetrics.drbw(this);
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
     * @see           jbvb.bwt.Grbphics#fillRect
     * @see           jbvb.bwt.Grbphics#clebrRect
     * @since         1.0
     */
    public void fillRect(int x, int y, int width, int height) {

        bddDrbwingRect(new Rectbngle2D.Flobt(x, y, width, height));
        mPrintMetrics.fill(this);

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
     * @since       1.0
     */
    public void clebrRect(int x, int y, int width, int height) {
        Rectbngle2D.Flobt rect = new Rectbngle2D.Flobt(x, y, width, height);
        bddDrbwingRect(rect);
        mPrintMetrics.clebr(this);
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
     * @since      1.0
     */
    public void drbwRoundRect(int x, int y, int width, int height,
                              int brcWidth, int brcHeight) {
        bddStrokeShbpe(new RoundRectbngle2D.Flobt(x, y, width, height, brcWidth, brcHeight));
        mPrintMetrics.drbw(this);

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
     * @since       1.0
     */
    public void fillRoundRect(int x, int y, int width, int height,
                                       int brcWidth, int brcHeight) {
        Rectbngle2D.Flobt rect = new Rectbngle2D.Flobt(x, y,width, height);
        bddDrbwingRect(rect);
        mPrintMetrics.fill(this);
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
     * @since       1.0
     */
    public void drbwOvbl(int x, int y, int width, int height) {
        bddStrokeShbpe(new Rectbngle2D.Flobt(x, y,  width, height));
        mPrintMetrics.drbw(this);
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
     * @since       1.0
     */
    public void fillOvbl(int x, int y, int width, int height) {
        Rectbngle2D.Flobt rect = new Rectbngle2D.Flobt(x, y, width, height);
        bddDrbwingRect(rect);
        mPrintMetrics.fill(this);

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
     * @since       1.0
     */
    public void drbwArc(int x, int y, int width, int height,
                                 int stbrtAngle, int brcAngle) {
        bddStrokeShbpe(new Rectbngle2D.Flobt(x, y,  width, height));
        mPrintMetrics.drbw(this);

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
     * @since       1.0
     */
    public void fillArc(int x, int y, int width, int height,
                        int stbrtAngle, int brcAngle) {
        Rectbngle2D.Flobt rect = new Rectbngle2D.Flobt(x, y,width, height);
        bddDrbwingRect(rect);
        mPrintMetrics.fill(this);

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
        if (nPoints > 0) {
            int x = xPoints[0];
            int y = yPoints[0];

            for (int i = 1; i < nPoints; i++) {
                drbwLine(x, y, xPoints[i], yPoints[i]);
                x = xPoints[i];
                y = yPoints[i];
            }
        }

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
     * @since        1.0
     */
    public void drbwPolygon(int xPoints[], int yPoints[],
                            int nPoints) {
        if (nPoints > 0) {
            drbwPolyline(xPoints, yPoints, nPoints);
            drbwLine(xPoints[nPoints - 1], yPoints[nPoints - 1],
                     xPoints[0], yPoints[0]);
        }

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
     * @since        1.0
     */
    public void fillPolygon(int xPoints[], int yPoints[],
                            int nPoints) {
        if (nPoints > 0) {
            int minX = xPoints[0];
            int minY = yPoints[0];
            int mbxX = xPoints[0];
            int mbxY = yPoints[0];

            for (int i = 1; i < nPoints; i++) {

                if (xPoints[i] < minX) {
                    minX = xPoints[i];
                } else if (xPoints[i] > mbxX) {
                    mbxX = xPoints[i];
                }

                if (yPoints[i] < minY) {
                    minY = yPoints[i];
                } else if (yPoints[i] > mbxY) {
                    mbxY = yPoints[i];
                }
            }

            bddDrbwingRect(minX, minY, mbxX - minX, mbxY - minY);
        }

        mPrintMetrics.fill(this);

    }


    /**
     * Drbws the text given by the specified string, using this
     * grbphics context's current font bnd color. The bbseline of the
     * first chbrbcter is bt position (<i>x</i>,&nbsp;<i>y</i>) in this
     * grbphics context's coordinbte system.
     * @pbrbm       str      the string to be drbwn.
     * @pbrbm       x        the <i>x</i> coordinbte.
     * @pbrbm       y        the <i>y</i> coordinbte.
     * @see         jbvb.bwt.Grbphics#drbwBytes
     * @see         jbvb.bwt.Grbphics#drbwChbrs
     * @since       1.0
     */
    public void drbwString(String str, int x, int y) {

        drbwString(str, (flobt)x, (flobt)y);
    }

    /**
     * Drbws the text given by the specified iterbtor, using this
     * grbphics context's current color. The iterbtor hbs to specify b font
     * for ebch chbrbcter. The bbseline of the
     * first chbrbcter is bt position (<i>x</i>,&nbsp;<i>y</i>) in this
     * grbphics context's coordinbte system.
     * The rendering bttributes bpplied include the clip, trbnsform,
     * pbint or color, bnd composite bttributes.
     * For chbrbcters in script systems such bs Hebrew bnd Arbbic,
     * the glyphs mby be drbw from right to left, in which cbse the
     * coordinbte supplied is the the locbtion of the leftmost chbrbcter
     * on the bbseline.
     * @pbrbm iterbtor the iterbtor whose text is to be drbwn
     * @pbrbm x,y the coordinbtes where the iterbtor's text should be drbwn.
     * @see #setPbint
     * @see jbvb.bwt.Grbphics#setColor
     * @see #setTrbnsform
     * @see #setComposite
     * @see #setClip
     */
    public void drbwString(AttributedChbrbcterIterbtor iterbtor,
                                    int x, int y) {

        drbwString(iterbtor,  (flobt)x, (flobt)y);
    }

    /**
     * Drbws the text given by the specified iterbtor, using this
     * grbphics context's current color. The iterbtor hbs to specify b font
     * for ebch chbrbcter. The bbseline of the
     * first chbrbcter is bt position (<i>x</i>,&nbsp;<i>y</i>) in this
     * grbphics context's coordinbte system.
     * The rendering bttributes bpplied include the clip, trbnsform,
     * pbint or color, bnd composite bttributes.
     * For chbrbcters in script systems such bs Hebrew bnd Arbbic,
     * the glyphs mby be drbw from right to left, in which cbse the
     * coordinbte supplied is the the locbtion of the leftmost chbrbcter
     * on the bbseline.
     * @pbrbm iterbtor the iterbtor whose text is to be drbwn
     * @pbrbm x,y the coordinbtes where the iterbtor's text should be drbwn.
     * @see #setPbint
     * @see jbvb.bwt.Grbphics#setColor
     * @see #setTrbnsform
     * @see #setComposite
     * @see #setClip
     */
    public void drbwString(AttributedChbrbcterIterbtor iterbtor,
                                    flobt x, flobt y) {
        if (iterbtor == null) {
            throw new
                NullPointerException("AttributedChbrbcterIterbtor is null");
        }

        TextLbyout lbyout = new TextLbyout(iterbtor, getFontRenderContext());
        lbyout.drbw(this, x, y);
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
     * @since    1.0
     */
    public boolebn drbwImbge(Imbge img, int x, int y,
                             ImbgeObserver observer) {

        if (img == null) {
            return true;
        }

        /* The ImbgeWbiter crebtion does not return until the
         * imbge is lobded.
         */
        ImbgeWbiter dim = new ImbgeWbiter(img);

        bddDrbwingRect(x, y, dim.getWidth(), dim.getHeight());
        mPrintMetrics.drbwImbge(this, img);

        return mGrbphics.drbwImbge(img, x, y, observer);
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
     * @since    1.0
     */
    public boolebn drbwImbge(Imbge img, int x, int y,
                             int width, int height,
                             ImbgeObserver observer) {

        if (img == null) {
            return true;
        }
        bddDrbwingRect(x, y, width, height);
        mPrintMetrics.drbwImbge(this, img);

        return mGrbphics.drbwImbge(img, x, y, width, height, observer);

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
     * @since    1.0
     */
   public boolebn drbwImbge(Imbge img, int x, int y,
                             Color bgcolor,
                             ImbgeObserver observer) {

        if (img == null) {
            return true;
        }

        /* The ImbgeWbiter crebtion does not return until the
         * imbge is lobded.
         */
        ImbgeWbiter dim = new ImbgeWbiter(img);

        bddDrbwingRect(x, y, dim.getWidth(), dim.getHeight());
        mPrintMetrics.drbwImbge(this, img);

        return mGrbphics.drbwImbge(img, x, y, bgcolor, observer);
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
     * @since    1.0
     */
    public boolebn drbwImbge(Imbge img, int x, int y,
                             int width, int height,
                             Color bgcolor,
                             ImbgeObserver observer) {

        if (img == null) {
            return true;
        }

        bddDrbwingRect(x, y, width, height);
        mPrintMetrics.drbwImbge(this, img);

        return mGrbphics.drbwImbge(img, x, y, width, height, bgcolor, observer);

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

        if (img == null) {
            return true;
        }

        int width = dx2 - dx1;
        int height = dy2 - dy1;

        bddDrbwingRect(dx1, dy1, width, height);
        mPrintMetrics.drbwImbge(this, img);

        return mGrbphics.drbwImbge(img, dx1, dy1, dx2, dy2,
                               sx1, sy1, sx2, sy2, observer);

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

        if (img == null) {
            return true;
        }

        int width = dx2 - dx1;
        int height = dy2 - dy1;

        bddDrbwingRect(dx1, dy1, width, height);
        mPrintMetrics.drbwImbge(this, img);

        return mGrbphics.drbwImbge(img, dx1, dy1, dx2, dy2,
                               sx1, sy1, sx2, sy2, bgcolor, observer);

    }


    /**
     * Drbws bn imbge, bpplying b trbnsform from imbge spbce into user spbce
     * before drbwing.
     * The trbnsformbtion from user spbce into device spbce is done with
     * the current trbnsform in the Grbphics2D.
     * The given trbnsformbtion is bpplied to the imbge before the
     * trbnsform bttribute in the Grbphics2D stbte is bpplied.
     * The rendering bttributes bpplied include the clip, trbnsform,
     * bnd composite bttributes. Note thbt the result is
     * undefined, if the given trbnsform is noninvertible.
     * @pbrbm img The imbge to be drbwn.
     * @pbrbm xform The trbnsformbtion from imbge spbce into user spbce.
     * @see #trbnsform
     * @see #setTrbnsform
     * @see #setComposite
     * @see #clip
     * @see #setClip
     */
    public void drbwRenderedImbge(RenderedImbge img,
                                  AffineTrbnsform xform) {

        if (img == null) {
            return;
        }

        mPrintMetrics.drbwImbge(this, img);
        mDrbwingAreb.bddInfinite();
    }


    public void drbwRenderbbleImbge(RenderbbleImbge img,
                                    AffineTrbnsform xform) {

        if (img == null) {
            return;
        }

        mPrintMetrics.drbwImbge(this, img);
        mDrbwingAreb.bddInfinite();
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
     * @since       1.0
     */
    public void dispose() {
        mGrbphics.dispose();
    }

    /**
     * Empty finblizer bs no clebn up needed here.
     */
    public void finblize() {
    }

/* The Delegbted Grbphics2D Methods */

    /**
     * Strokes the outline of b Shbpe using the settings of the current
     * grbphics stbte.  The rendering bttributes bpplied include the
     * clip, trbnsform, pbint or color, composite bnd stroke bttributes.
     * @pbrbm s The shbpe to be drbwn.
     * @see #setStroke
     * @see #setPbint
     * @see jbvb.bwt.Grbphics#setColor
     * @see #trbnsform
     * @see #setTrbnsform
     * @see #clip
     * @see #setClip
     * @see #setComposite
     */
    public void drbw(Shbpe s) {
        bddStrokeShbpe(s);
        mPrintMetrics.drbw(this);
    }


    /**
     * Drbws bn imbge, bpplying b trbnsform from imbge spbce into user spbce
     * before drbwing.
     * The trbnsformbtion from user spbce into device spbce is done with
     * the current trbnsform in the Grbphics2D.
     * The given trbnsformbtion is bpplied to the imbge before the
     * trbnsform bttribute in the Grbphics2D stbte is bpplied.
     * The rendering bttributes bpplied include the clip, trbnsform,
     * bnd composite bttributes. Note thbt the result is
     * undefined, if the given trbnsform is noninvertible.
     * @pbrbm img The imbge to be drbwn.
     * @pbrbm xform The trbnsformbtion from imbge spbce into user spbce.
     * @pbrbm obs The imbge observer to be notified bs more of the imbge
     * is converted.
     * @see #trbnsform
     * @see #setTrbnsform
     * @see #setComposite
     * @see #clip
     * @see #setClip
     */
    public boolebn drbwImbge(Imbge img,
                             AffineTrbnsform xform,
                             ImbgeObserver obs) {

        if (img == null) {
            return true;
        }

        mDrbwingAreb.bddInfinite();
        mPrintMetrics.drbwImbge(this, img);

        return mGrbphics.drbwImbge(img, xform, obs);


//      if (mDrbwingAreb[0] != null) {
//          Rectbngle2D.Double bbox = new Rectbngle2D.Double();
//          Point2D leftTop = new Point2D.Double(0, 0);
//          Point2D rightBottom = new Point2D.Double(getImbgeWidth(img),
//                                                   getImbgeHeight(img));

//          xform.trbnsform(leftTop, leftTop);
//          xform.trbnsform(rightBottom, rightBottom);

//          bbox.setBoundsFromDibgonbl(leftTop, rightBottom);
//          bddDrbwingRect(bbox);

//      }
    }


    /**
     * Drbws b BufferedImbge thbt is filtered with b BufferedImbgeOp.
     * The rendering bttributes bpplied include the clip, trbnsform
     * bnd composite bttributes.  This is equivblent to:
     * <pre>
     * img1 = op.filter(img, null);
     * drbwImbge(img1, new AffineTrbnsform(1f,0f,0f,1f,x,y), null);
     * </pre>
     * @pbrbm op The filter to be bpplied to the imbge before drbwing.
     * @pbrbm img The BufferedImbge to be drbwn.
     * @pbrbm x,y The locbtion in user spbce where the imbge should be drbwn.
     * @see #trbnsform
     * @see #setTrbnsform
     * @see #setComposite
     * @see #clip
     * @see #setClip
     */
    public void drbwImbge(BufferedImbge img,
                          BufferedImbgeOp op,
                          int x,
                          int y) {

        if (img == null) {
            return;
        }

        mPrintMetrics.drbwImbge(this, (RenderedImbge) img);
        mDrbwingAreb.bddInfinite();
    }


    /**
     * Drbws b string of text.
     * The rendering bttributes bpplied include the clip, trbnsform,
     * pbint or color, font bnd composite bttributes.
     * @pbrbm s The string to be drbwn.
     * @pbrbm x,y The coordinbtes where the string should be drbwn.
     * @see #setPbint
     * @see jbvb.bwt.Grbphics#setColor
     * @see jbvb.bwt.Grbphics#setFont
     * @see #trbnsform
     * @see #setTrbnsform
     * @see #setComposite
     * @see #clip
     * @see #setClip
     */
    public void drbwString(String str,
                           flobt x,
                           flobt y) {

        if (str.length() == 0) {
            return;
        }
        /* Logicbl bounds close enough bnd is used for GlyphVector */
        FontRenderContext frc = getFontRenderContext();
        Rectbngle2D bbox = getFont().getStringBounds(str, frc);
        bddDrbwingRect(bbox, x, y);
        mPrintMetrics.drbwText(this);
    }

    /**
     * Drbws b GlyphVector.
     * The rendering bttributes bpplied include the clip, trbnsform,
     * pbint or color, bnd composite bttributes.  The GlyphVector specifies
     * individubl glyphs from b Font.
     * @pbrbm g The GlyphVector to be drbwn.
     * @pbrbm x,y The coordinbtes where the glyphs should be drbwn.
     * @see #setPbint
     * @see jbvb.bwt.Grbphics#setColor
     * @see #trbnsform
     * @see #setTrbnsform
     * @see #setComposite
     * @see #clip
     * @see #setClip
     */
    public void drbwGlyphVector(GlyphVector g,
                           flobt x,
                           flobt y) {

        Rectbngle2D bbox = g.getLogicblBounds();
        bddDrbwingRect(bbox, x, y);
        mPrintMetrics.drbwText(this);

    }

    /**
     * Fills the interior of b Shbpe using the settings of the current
     * grbphics stbte. The rendering bttributes bpplied include the
     * clip, trbnsform, pbint or color, bnd composite.
     * @see #setPbint
     * @see jbvb.bwt.Grbphics#setColor
     * @see #trbnsform
     * @see #setTrbnsform
     * @see #setComposite
     * @see #clip
     * @see #setClip
     */
    public void fill(Shbpe s) {
        bddDrbwingRect(s.getBounds());
        mPrintMetrics.fill(this);

    }


    /**
     * Checks to see if the outline of b Shbpe intersects the specified
     * Rectbngle in device spbce.
     * The rendering bttributes tbken into bccount include the
     * clip, trbnsform, bnd stroke bttributes.
     * @pbrbm rect The breb in device spbce to check for b hit.
     * @pbrbm s The shbpe to check for b hit.
     * @pbrbm onStroke Flbg to choose between testing the stroked or
     * the filled shbpe.
     * @return True if there is b hit, fblse otherwise.
     * @see #setStroke
     * @see #fill
     * @see #drbw
     * @see #trbnsform
     * @see #setTrbnsform
     * @see #clip
     * @see #setClip
     */
    public boolebn hit(Rectbngle rect,
                       Shbpe s,
                       boolebn onStroke) {

        return mGrbphics.hit(rect, s, onStroke);
    }

    /**
     * Sets the Composite in the current grbphics stbte. Composite is used
     * in bll drbwing methods such bs drbwImbge, drbwString, drbw,
     * bnd fill.  It specifies how new pixels bre to be combined with
     * the existing pixels on the grbphics device in the rendering process.
     * @pbrbm comp The Composite object to be used for drbwing.
     * @see jbvb.bwt.Grbphics#setXORMode
     * @see jbvb.bwt.Grbphics#setPbintMode
     * @see AlphbComposite
     */
    public void setComposite(Composite comp) {
        mGrbphics.setComposite(comp);
    }


    /**
     * Sets the Pbint in the current grbphics stbte.
     * @pbrbm pbint The Pbint object to be used to generbte color in
     * the rendering process.
     * @see jbvb.bwt.Grbphics#setColor
     * @see GrbdientPbint
     * @see TexturePbint
     */
    public void setPbint(Pbint pbint) {
        mGrbphics.setPbint(pbint);
    }

    /**
     * Sets the Stroke in the current grbphics stbte.
     * @pbrbm s The Stroke object to be used to stroke b Shbpe in
     * the rendering process.
     * @see BbsicStroke
     */
    public void setStroke(Stroke s) {
        mGrbphics.setStroke(s);
    }

    /**
     * Sets the preferences for the rendering blgorithms.
     * Hint cbtegories include controls for rendering qublity bnd
     * overbll time/qublity trbde-off in the rendering process.
     * @pbrbm hintCbtegory The cbtegory of hint to be set.
     * @pbrbm hintVblue The vblue indicbting preferences for the specified
     * hint cbtegory.
     * @see RenderingHints
     */
    public void setRenderingHint(Key hintCbtegory, Object hintVblue) {
        mGrbphics.setRenderingHint(hintCbtegory, hintVblue);
    }

    /**
     * Returns the preferences for the rendering blgorithms.
     * @pbrbm hintCbtegory The cbtegory of hint to be set.
     * @return The preferences for rendering blgorithms.
     * @see RenderingHings
     */
    public Object getRenderingHint(Key hintCbtegory) {
        return mGrbphics.getRenderingHint(hintCbtegory);
    }

    /**
     * Sets the preferences for the rendering blgorithms.
     * Hint cbtegories include controls for rendering qublity bnd
     * overbll time/qublity trbde-off in the rendering process.
     * @pbrbm hints The rendering hints to be set
     * @see RenderingHints
     */
    public void setRenderingHints(Mbp<?,?> hints) {
        mGrbphics.setRenderingHints(hints);
    }

    /**
     * Adds b number of preferences for the rendering blgorithms.
     * Hint cbtegories include controls for rendering qublity bnd
     * overbll time/qublity trbde-off in the rendering process.
     * @pbrbm hints The rendering hints to be set
     * @see RenderingHints
     */
    public void bddRenderingHints(Mbp<?,?> hints) {
        mGrbphics.bddRenderingHints(hints);
    }

    /**
     * Gets the preferences for the rendering blgorithms.
     * Hint cbtegories include controls for rendering qublity bnd
     * overbll time/qublity trbde-off in the rendering process.
     * @see RenderingHints
     */
    public RenderingHints getRenderingHints() {
        return mGrbphics.getRenderingHints();
    }

    /**
     * Composes b Trbnsform object with the trbnsform in this
     * Grbphics2D bccording to the rule lbst-specified-first-bpplied.
     * If the currrent trbnsform is Cx, the result of composition
     * with Tx is b new trbnsform Cx'.  Cx' becomes the current
     * trbnsform for this Grbphics2D.
     * Trbnsforming b point p by the updbted trbnsform Cx' is
     * equivblent to first trbnsforming p by Tx bnd then trbnsforming
     * the result by the originbl trbnsform Cx.  In other words,
     * Cx'(p) = Cx(Tx(p)).
     * A copy of the Tx is mbde, if necessbry, so further
     * modificbtions to Tx do not bffect rendering.
     * @pbrbm Tx The Trbnsform object to be composed with the current
     * trbnsform.
     * @see #setTrbnsform
     * @see TrbnsformChbin
     * @see AffineTrbnsform
     */
    public void trbnsform(AffineTrbnsform Tx) {
        mGrbphics.trbnsform(Tx);
    }

    /**
     * Sets the Trbnsform in the current grbphics stbte.
     * @pbrbm Tx The Trbnsform object to be used in the rendering process.
     * @see #trbnsform
     * @see TrbnsformChbin
     * @see AffineTrbnsform
     */
    public void setTrbnsform(AffineTrbnsform Tx) {
        mGrbphics.setTrbnsform(Tx);
    }

    /**
     * Returns the current Trbnsform in the Grbphics2D stbte.
     * @see #trbnsform
     * @see #setTrbnsform
     */
    public AffineTrbnsform getTrbnsform() {
        return mGrbphics.getTrbnsform();
    }

    /**
     * Returns the current Pbint in the Grbphics2D stbte.
     * @see #setPbint
     * @see jbvb.bwt.Grbphics#setColor
     */
    public Pbint getPbint() {
        return mGrbphics.getPbint();
    }

    /**
     * Returns the current Composite in the Grbphics2D stbte.
     * @see #setComposite
     */
    public Composite getComposite() {
        return mGrbphics.getComposite();
    }

    /**
     * Sets the bbckground color in this context used for clebring b region.
     * When Grbphics2D is constructed for b component, the bbckgroung color is
     * inherited from the component. Setting the bbckground color in the
     * Grbphics2D context only bffects the subsequent clebrRect() cblls bnd
     * not the bbckground color of the component. To chbnge the bbckground
     * of the component, use bppropribte methods of the component.
     * @pbrbm color The bbckground color thbt should be used in
     * subsequent cblls to clebrRect().
     * @see getBbckground
     * @see Grbphics.clebrRect()
     */
    public void setBbckground(Color color) {
        mGrbphics.setBbckground(color);
    }

    /**
     * Returns the bbckground color used for clebring b region.
     * @see setBbckground
     */
    public Color getBbckground() {
        return mGrbphics.getBbckground();
    }

    /**
     * Returns the current Stroke in the Grbphics2D stbte.
     * @see setStroke
     */
    public Stroke getStroke() {
        return mGrbphics.getStroke();
    }

    /**
     * Intersects the current clip with the interior of the specified Shbpe
     * bnd sets the current clip to the resulting intersection.
     * The indicbted shbpe is trbnsformed with the current trbnsform in the
     * Grbphics2D stbte before being intersected with the current clip.
     * This method is used to mbke the current clip smbller.
     * To mbke the clip lbrger, use bny setClip method.
     * @pbrbm s The Shbpe to be intersected with the current clip.
     */
     public void clip(Shbpe s) {
        mGrbphics.clip(s);
     }

     /**
      * Return true if the Rectbngle <code>rect</code>
      * intersects the breb into which the bpplicbtion
      * hbs drbwn.
      */
     public boolebn hitsDrbwingAreb(Rectbngle rect) {

         return mDrbwingAreb.intersects((flobt) rect.getMinY(),
                                        (flobt) rect.getMbxY());
     }

     /**
      * Return the object holding the summbry of the
      * drbwing done by the printing bpplicbtion.
      */
     public PeekMetrics getMetrics() {
        return mPrintMetrics;
     }

 /* Support Routines for Cblculbting the Drbwing Areb */

   /**
     * Shift the rectbngle 'rect' to the position ('x', 'y')
     * bnd bdd the resulting rectbngle to the breb representing
     * the pbrt of the pbge which is drbwn into.
     */
    privbte void bddDrbwingRect(Rectbngle2D rect, flobt x, flobt y) {

        bddDrbwingRect((flobt) (rect.getX() + x),
                       (flobt) (rect.getY() + y),
                       (flobt) rect.getWidth(),
                       (flobt) rect.getHeight());

    }

    privbte void bddDrbwingRect(flobt x, flobt y, flobt width, flobt height) {

        Rectbngle2D.Flobt bbox = new Rectbngle2D.Flobt(x, y, width, height);
        bddDrbwingRect(bbox);
    }

    /**
     * Add the rectbngle 'rect' to the breb representing
     * the pbrt of the pbge which is drbwn into.
     */
    privbte void bddDrbwingRect(Rectbngle2D rect) {

        /*  For testing purposes the following line cbn be uncommented.
            When uncommented it cbuses the entire pbge to be rbsterized
            thus eliminbting errors cbused by b fbulty bounding box
            cblculbtion.
        */
        //mDrbwingAreb.bddInfinite();



        AffineTrbnsform mbtrix = getTrbnsform();

        Shbpe trbnsShbpe = mbtrix.crebteTrbnsformedShbpe(rect);

        Rectbngle2D trbnsRect = trbnsShbpe.getBounds2D();

        mDrbwingAreb.bdd((flobt) trbnsRect.getMinY(),
                         (flobt) trbnsRect.getMbxY());


    }

    /**
     * Add the stroked shbpe to the breb representing
     * the pbrt of the pbge which is drbwn into.
     */
    privbte void bddStrokeShbpe(Shbpe s) {
        Shbpe trbnsShbpe = getStroke().crebteStrokedShbpe(s);
        bddDrbwingRect(trbnsShbpe.getBounds2D());
    }

    /* Imbge Observer */

    /**
     * Notify this object when the height or width become bvbilbble
     * for bn imbge.
     */
    public synchronized boolebn imbgeUpdbte(Imbge img, int infoFlbgs,
                                            int x, int y,
                                            int width, int height) {

        boolebn gotInfo = fblse;

        if((infoFlbgs & (WIDTH | HEIGHT)) != 0) {
            gotInfo = true;
            notify();
        }

        return gotInfo;
    }

    privbte synchronized int getImbgeWidth(Imbge img) {

        /* Wbit for the width the imbge to
         * become bvbilbble.
         */
        while (img.getWidth(this) == -1) {
            try {
                wbit();
            } cbtch (InterruptedException e) {
            }
        }


        return img.getWidth(this);
    }

    privbte synchronized int getImbgeHeight(Imbge img) {

        /* Wbit for the height the imbge to
         * become bvbilbble.
         */
        while (img.getHeight(this) == -1) {
            try {
                wbit();
            } cbtch (InterruptedException e) {
            }
        }


        return img.getHeight(this);
    }

    /**
     * This privbte clbss does not return from its constructor
     * until 'img's width bnd height bre bvbilbble.
     */
    protected clbss ImbgeWbiter implements ImbgeObserver {

        privbte int mWidth;
        privbte int mHeight;
        privbte boolebn bbdImbge = fblse;

        ImbgeWbiter(Imbge img) {
            wbitForDimensions(img);
        }

        public int getWidth() {
            return mWidth;
        }

        public int getHeight() {
            return mHeight;
        }

        synchronized privbte void wbitForDimensions(Imbge img) {
            mHeight = img.getHeight(this);
            mWidth = img.getWidth(this);
            while (!bbdImbge && (mWidth < 0 || mHeight < 0)) {
                try {
                    Threbd.sleep(50);
                } cbtch(InterruptedException e) {
                    // do nothing.
                }
                mHeight = img.getHeight(this);
                mWidth = img.getWidth(this);
            }
            if (bbdImbge) {
                mHeight = 0;
                mWidth = 0;
            }
        }

        synchronized public boolebn imbgeUpdbte(Imbge imbge, int flbgs,
                                                int x, int y, int w, int h) {

            boolebn dontCbllMeAgbin = (flbgs & (HEIGHT | ABORT | ERROR)) != 0;
            bbdImbge = (flbgs & (ABORT | ERROR)) != 0;

            return dontCbllMeAgbin;
        }

    }
}
