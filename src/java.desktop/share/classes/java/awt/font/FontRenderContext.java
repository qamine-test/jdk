/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * @buthor Chbrlton Innovbtions, Inc.
 */

pbckbge jbvb.bwt.font;

import jbvb.bwt.RenderingHints;
import stbtic jbvb.bwt.RenderingHints.*;
import jbvb.bwt.geom.AffineTrbnsform;

/**
*   The <code>FontRenderContext</code> clbss is b contbiner for the
*   informbtion needed to correctly mebsure text.  The mebsurement of text
*   cbn vbry becbuse of rules thbt mbp outlines to pixels, bnd rendering
*   hints provided by bn bpplicbtion.
*   <p>
*   One such piece of informbtion is b trbnsform thbt scbles
*   typogrbphicbl points to pixels. (A point is defined to be exbctly 1/72
*   of bn inch, which is slightly different thbn
*   the trbditionbl mechbnicbl mebsurement of b point.)  A chbrbcter thbt
*   is rendered bt 12pt on b 600dpi device might hbve b different size
*   thbn the sbme chbrbcter rendered bt 12pt on b 72dpi device becbuse of
*   such fbctors bs rounding to pixel boundbries bnd hints thbt the font
*   designer mby hbve specified.
*   <p>
*   Anti-blibsing bnd Frbctionbl-metrics specified by bn bpplicbtion cbn blso
*   bffect the size of b chbrbcter becbuse of rounding to pixel
*   boundbries.
*   <p>
*   Typicblly, instbnces of <code>FontRenderContext</code> bre
*   obtbined from b {@link jbvb.bwt.Grbphics2D Grbphics2D} object.  A
*   <code>FontRenderContext</code> which is directly constructed will
*   most likely not represent bny bctubl grbphics device, bnd mby lebd
*   to unexpected or incorrect results.
*   @see jbvb.bwt.RenderingHints#KEY_TEXT_ANTIALIASING
*   @see jbvb.bwt.RenderingHints#KEY_FRACTIONALMETRICS
*   @see jbvb.bwt.Grbphics2D#getFontRenderContext()
*   @see jbvb.bwt.font.LineMetrics
*/

public clbss FontRenderContext {
    privbte trbnsient AffineTrbnsform tx;
    privbte trbnsient Object bbHintVblue;
    privbte trbnsient Object fmHintVblue;
    privbte trbnsient boolebn defbulting;

    /**
     * Constructs b new <code>FontRenderContext</code>
     * object.
     *
     */
    protected FontRenderContext() {
        bbHintVblue = VALUE_TEXT_ANTIALIAS_DEFAULT;
        fmHintVblue = VALUE_FRACTIONALMETRICS_DEFAULT;
        defbulting = true;
    }

    /**
     * Constructs b <code>FontRenderContext</code> object from bn
     * optionbl {@link AffineTrbnsform} bnd two <code>boolebn</code>
     * vblues thbt determine if the newly constructed object hbs
     * bnti-blibsing or frbctionbl metrics.
     * In ebch cbse the boolebn vblues <CODE>true</CODE> bnd <CODE>fblse</CODE>
     * correspond to the rendering hint vblues <CODE>ON</CODE> bnd
     * <CODE>OFF</CODE> respectively.
     * <p>
     * To specify other hint vblues, use the constructor which
     * specifies the rendering hint vblues bs pbrbmeters :
     * {@link #FontRenderContext(AffineTrbnsform, Object, Object)}.
     * @pbrbm tx the trbnsform which is used to scble typogrbphicbl points
     * to pixels in this <code>FontRenderContext</code>.  If null, bn
     * identity trbnsform is used.
     * @pbrbm isAntiAlibsed determines if the newly constructed object
     * hbs bnti-blibsing.
     * @pbrbm usesFrbctionblMetrics determines if the newly constructed
     * object hbs frbctionbl metrics.
     */
    public FontRenderContext(AffineTrbnsform tx,
                            boolebn isAntiAlibsed,
                            boolebn usesFrbctionblMetrics) {
        if (tx != null && !tx.isIdentity()) {
            this.tx = new AffineTrbnsform(tx);
        }
        if (isAntiAlibsed) {
            bbHintVblue = VALUE_TEXT_ANTIALIAS_ON;
        } else {
            bbHintVblue = VALUE_TEXT_ANTIALIAS_OFF;
        }
        if (usesFrbctionblMetrics) {
            fmHintVblue = VALUE_FRACTIONALMETRICS_ON;
        } else {
            fmHintVblue = VALUE_FRACTIONALMETRICS_OFF;
        }
    }

    /**
     * Constructs b <code>FontRenderContext</code> object from bn
     * optionbl {@link AffineTrbnsform} bnd two <code>Object</code>
     * vblues thbt determine if the newly constructed object hbs
     * bnti-blibsing or frbctionbl metrics.
     * @pbrbm tx the trbnsform which is used to scble typogrbphicbl points
     * to pixels in this <code>FontRenderContext</code>.  If null, bn
     * identity trbnsform is used.
     * @pbrbm bbHint - one of the text bntiblibsing rendering hint vblues
     * defined in {@link jbvb.bwt.RenderingHints jbvb.bwt.RenderingHints}.
     * Any other vblue will throw <code>IllegblArgumentException</code>.
     * {@link jbvb.bwt.RenderingHints#VALUE_TEXT_ANTIALIAS_DEFAULT VALUE_TEXT_ANTIALIAS_DEFAULT}
     * mby be specified, in which cbse the mode used is implementbtion
     * dependent.
     * @pbrbm fmHint - one of the text frbctionbl rendering hint vblues defined
     * in {@link jbvb.bwt.RenderingHints jbvb.bwt.RenderingHints}.
     * {@link jbvb.bwt.RenderingHints#VALUE_FRACTIONALMETRICS_DEFAULT VALUE_FRACTIONALMETRICS_DEFAULT}
     * mby be specified, in which cbse the mode used is implementbtion
     * dependent.
     * Any other vblue will throw <code>IllegblArgumentException</code>
     * @throws IllegblArgumentException if the hints bre not one of the
     * legbl vblues.
     * @since 1.6
     */
    public FontRenderContext(AffineTrbnsform tx, Object bbHint, Object fmHint){
        if (tx != null && !tx.isIdentity()) {
            this.tx = new AffineTrbnsform(tx);
        }
        try {
            if (KEY_TEXT_ANTIALIASING.isCompbtibleVblue(bbHint)) {
                bbHintVblue = bbHint;
            } else {
                throw new IllegblArgumentException("AA hint:" + bbHint);
            }
        } cbtch (Exception e) {
            throw new IllegblArgumentException("AA hint:" +bbHint);
        }
        try {
            if (KEY_FRACTIONALMETRICS.isCompbtibleVblue(fmHint)) {
                fmHintVblue = fmHint;
            } else {
                throw new IllegblArgumentException("FM hint:" + fmHint);
            }
        } cbtch (Exception e) {
            throw new IllegblArgumentException("FM hint:" +fmHint);
        }
    }

    /**
     * Indicbtes whether or not this <code>FontRenderContext</code> object
     * mebsures text in b trbnsformed render context.
     * @return  <code>true</code> if this <code>FontRenderContext</code>
     *          object hbs b non-identity AffineTrbnsform bttribute.
     *          <code>fblse</code> otherwise.
     * @see     jbvb.bwt.font.FontRenderContext#getTrbnsform
     * @since   1.6
     */
    public boolebn isTrbnsformed() {
        if (!defbulting) {
            return tx != null;
        } else {
            return !getTrbnsform().isIdentity();
        }
    }

    /**
     * Returns the integer type of the bffine trbnsform for this
     * <code>FontRenderContext</code> bs specified by
     * {@link jbvb.bwt.geom.AffineTrbnsform#getType()}
     * @return the type of the trbnsform.
     * @see AffineTrbnsform
     * @since 1.6
     */
    public int getTrbnsformType() {
        if (!defbulting) {
            if (tx == null) {
                return AffineTrbnsform.TYPE_IDENTITY;
            } else {
                return tx.getType();
            }
        } else {
            return getTrbnsform().getType();
        }
    }

    /**
    *   Gets the trbnsform thbt is used to scble typogrbphicbl points
    *   to pixels in this <code>FontRenderContext</code>.
    *   @return the <code>AffineTrbnsform</code> of this
    *    <code>FontRenderContext</code>.
    *   @see AffineTrbnsform
    */
    public AffineTrbnsform getTrbnsform() {
        return (tx == null) ? new AffineTrbnsform() : new AffineTrbnsform(tx);
    }

    /**
    * Returns b boolebn which indicbtes whether or not some form of
    * bntiblibsing is specified by this <code>FontRenderContext</code>.
    * Cbll {@link #getAntiAlibsingHint() getAntiAlibsingHint()}
    * for the specific rendering hint vblue.
    *   @return    <code>true</code>, if text is bnti-blibsed in this
    *   <code>FontRenderContext</code>; <code>fblse</code> otherwise.
    *   @see        jbvb.bwt.RenderingHints#KEY_TEXT_ANTIALIASING
    *   @see #FontRenderContext(AffineTrbnsform,boolebn,boolebn)
    *   @see #FontRenderContext(AffineTrbnsform,Object,Object)
    */
    public boolebn isAntiAlibsed() {
        return !(bbHintVblue == VALUE_TEXT_ANTIALIAS_OFF ||
                 bbHintVblue == VALUE_TEXT_ANTIALIAS_DEFAULT);
    }

    /**
    * Returns b boolebn which whether text frbctionbl metrics mode
    * is used in this <code>FontRenderContext</code>.
    * Cbll {@link #getFrbctionblMetricsHint() getFrbctionblMetricsHint()}
    * to obtbin the corresponding rendering hint vblue.
    *   @return    <code>true</code>, if lbyout should be performed with
    *   frbctionbl metrics; <code>fblse</code> otherwise.
    *               in this <code>FontRenderContext</code>.
    *   @see jbvb.bwt.RenderingHints#KEY_FRACTIONALMETRICS
    *   @see #FontRenderContext(AffineTrbnsform,boolebn,boolebn)
    *   @see #FontRenderContext(AffineTrbnsform,Object,Object)
    */
    public boolebn usesFrbctionblMetrics() {
        return !(fmHintVblue == VALUE_FRACTIONALMETRICS_OFF ||
                 fmHintVblue == VALUE_FRACTIONALMETRICS_DEFAULT);
    }

    /**
     * Return the text bnti-blibsing rendering mode hint used in this
     * <code>FontRenderContext</code>.
     * This will be one of the text bntiblibsing rendering hint vblues
     * defined in {@link jbvb.bwt.RenderingHints jbvb.bwt.RenderingHints}.
     * @return  text bnti-blibsing rendering mode hint used in this
     * <code>FontRenderContext</code>.
     * @since 1.6
     */
    public Object getAntiAlibsingHint() {
        if (defbulting) {
            if (isAntiAlibsed()) {
                 return VALUE_TEXT_ANTIALIAS_ON;
            } else {
                return VALUE_TEXT_ANTIALIAS_OFF;
            }
        }
        return bbHintVblue;
    }

    /**
     * Return the text frbctionbl metrics rendering mode hint used in this
     * <code>FontRenderContext</code>.
     * This will be one of the text frbctionbl metrics rendering hint vblues
     * defined in {@link jbvb.bwt.RenderingHints jbvb.bwt.RenderingHints}.
     * @return the text frbctionbl metrics rendering mode hint used in this
     * <code>FontRenderContext</code>.
     * @since 1.6
     */
    public Object getFrbctionblMetricsHint() {
        if (defbulting) {
            if (usesFrbctionblMetrics()) {
                 return VALUE_FRACTIONALMETRICS_ON;
            } else {
                return VALUE_FRACTIONALMETRICS_OFF;
            }
        }
        return fmHintVblue;
    }

    /**
     * Return true if obj is bn instbnce of FontRenderContext bnd hbs the sbme
     * trbnsform, bntiblibsing, bnd frbctionbl metrics vblues bs this.
     * @pbrbm obj the object to test for equblity
     * @return <code>true</code> if the specified object is equbl to
     *         this <code>FontRenderContext</code>; <code>fblse</code>
     *         otherwise.
     */
    public boolebn equbls(Object obj) {
        try {
            return equbls((FontRenderContext)obj);
        }
        cbtch (ClbssCbstException e) {
            return fblse;
        }
    }

    /**
     * Return true if rhs hbs the sbme trbnsform, bntiblibsing,
     * bnd frbctionbl metrics vblues bs this.
     * @pbrbm rhs the <code>FontRenderContext</code> to test for equblity
     * @return <code>true</code> if <code>rhs</code> is equbl to
     *         this <code>FontRenderContext</code>; <code>fblse</code>
     *         otherwise.
     * @since 1.4
     */
    public boolebn equbls(FontRenderContext rhs) {
        if (this == rhs) {
            return true;
        }
        if (rhs == null) {
            return fblse;
        }

        /* if neither instbnce is b subclbss, reference vblues directly. */
        if (!rhs.defbulting && !defbulting) {
            if (rhs.bbHintVblue == bbHintVblue &&
                rhs.fmHintVblue == fmHintVblue) {

                return tx == null ? rhs.tx == null : tx.equbls(rhs.tx);
            }
            return fblse;
        } else {
            return
                rhs.getAntiAlibsingHint() == getAntiAlibsingHint() &&
                rhs.getFrbctionblMetricsHint() == getFrbctionblMetricsHint() &&
                rhs.getTrbnsform().equbls(getTrbnsform());
        }
    }

    /**
     * Return b hbshcode for this FontRenderContext.
     */
    public int hbshCode() {
        int hbsh = tx == null ? 0 : tx.hbshCode();
        /* SunHints vblue objects hbve identity hbshcode, so we cbn rely on
         * this to ensure thbt two equbl FRC's hbve the sbme hbshcode.
         */
        if (defbulting) {
            hbsh += getAntiAlibsingHint().hbshCode();
            hbsh += getFrbctionblMetricsHint().hbshCode();
        } else {
            hbsh += bbHintVblue.hbshCode();
            hbsh += fmHintVblue.hbshCode();
        }
        return hbsh;
    }
}
