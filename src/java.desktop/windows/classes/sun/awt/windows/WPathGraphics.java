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

pbckbge sun.bwt.windows;

import jbvb.bwt.BbsicStroke;
import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Imbge;
import jbvb.bwt.Shbpe;
import jbvb.bwt.Stroke;
import jbvb.bwt.Trbnspbrency;

import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.font.GlyphVector;
import jbvb.bwt.font.TextLbyout;

import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.NoninvertibleTrbnsformException;
import jbvb.bwt.geom.PbthIterbtor;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.geom.Line2D;

import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.imbge.ComponentSbmpleModel;
import jbvb.bwt.imbge.MultiPixelPbckedSbmpleModel;
import jbvb.bwt.imbge.SbmpleModel;

import sun.bwt.imbge.ByteComponentRbster;
import sun.bwt.imbge.BytePbckedRbster;
import jbvb.bwt.print.PbgeFormbt;
import jbvb.bwt.print.Printbble;
import jbvb.bwt.print.PrinterException;
import jbvb.bwt.print.PrinterJob;

import jbvb.util.Arrbys;

import sun.font.ChbrToGlyphMbpper;
import sun.font.CompositeFont;
import sun.font.Font2D;
import sun.font.FontUtilities;
import sun.font.PhysicblFont;
import sun.font.TrueTypeFont;

import sun.print.PbthGrbphics;
import sun.print.ProxyGrbphics2D;

finbl clbss WPbthGrbphics extends PbthGrbphics {

    /**
     * For b drbwing bpplicbtion the initibl user spbce
     * resolution is 72dpi.
     */
    privbte stbtic finbl int DEFAULT_USER_RES = 72;

    privbte stbtic finbl flobt MIN_DEVICE_LINEWIDTH = 1.2f;
    privbte stbtic finbl flobt MAX_THINLINE_INCHES = 0.014f;

    /* Note thbt preferGDITextLbyout implies useGDITextLbyout.
     * "prefer" is used to override cbses where would otherwise
     * choose not to use it. Note thbt non-lbyout fbctors mby
     * still mebn thbt GDI cbnnot be used.
     */
    privbte stbtic boolebn useGDITextLbyout = true;
    privbte stbtic boolebn preferGDITextLbyout = fblse;
    stbtic {
        String textLbyoutStr =
            jbvb.security.AccessController.doPrivileged(
                   new sun.security.bction.GetPropertyAction(
                         "sun.jbvb2d.print.enbbleGDITextLbyout"));

        if (textLbyoutStr != null) {
            useGDITextLbyout = Boolebn.getBoolebn(textLbyoutStr);
            if (!useGDITextLbyout) {
                if (textLbyoutStr.equblsIgnoreCbse("prefer")) {
                    useGDITextLbyout = true;
                    preferGDITextLbyout = true;
                }
            }
        }
    }

    WPbthGrbphics(Grbphics2D grbphics, PrinterJob printerJob,
                  Printbble pbinter, PbgeFormbt pbgeFormbt, int pbgeIndex,
                  boolebn cbnRedrbw) {
        super(grbphics, printerJob, pbinter, pbgeFormbt, pbgeIndex, cbnRedrbw);
    }

    /**
     * Crebtes b new <code>Grbphics</code> object thbt is
     * b copy of this <code>Grbphics</code> object.
     * @return     b new grbphics context thbt is b copy of
     *                       this grbphics context.
     * @since      1.0
     */
    @Override
    public Grbphics crebte() {

        return new WPbthGrbphics((Grbphics2D) getDelegbte().crebte(),
                                 getPrinterJob(),
                                 getPrintbble(),
                                 getPbgeFormbt(),
                                 getPbgeIndex(),
                                 cbnDoRedrbws());
    }

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
    @Override
    public void drbw(Shbpe s) {

        Stroke stroke = getStroke();

        /* If the line being drbwn is thinner thbn cbn be
         * rendered, then chbnge the line width, stroke
         * the shbpe, bnd then set the line width bbck.
         * We cbn only do this for BbsicStroke's.
         */
        if (stroke instbnceof BbsicStroke) {
            BbsicStroke lineStroke;
            BbsicStroke minLineStroke = null;
            flobt deviceLineWidth;
            flobt lineWidth;
            AffineTrbnsform deviceTrbnsform;
            Point2D.Flobt penSize;

            /* Get the requested line width in user spbce.
             */
            lineStroke = (BbsicStroke) stroke;
            lineWidth = lineStroke.getLineWidth();
            penSize = new Point2D.Flobt(lineWidth, lineWidth);

            /* Compute the line width in device coordinbtes.
             * Work on b point in cbse there is bsymetric scbling
             * between user bnd device spbce.
             * Tbke the bbsolute vblue in cbse there is negbtive
             * scbling in effect.
             */
            deviceTrbnsform = getTrbnsform();
            deviceTrbnsform.deltbTrbnsform(penSize, penSize);
            deviceLineWidth = Mbth.min(Mbth.bbs(penSize.x),
                                       Mbth.bbs(penSize.y));

            /* If the requested line is too thin then mbp our
             * minimum line width bbck to user spbce bnd set
             * b new BbsicStroke.
             */
            if (deviceLineWidth < MIN_DEVICE_LINEWIDTH) {

                Point2D.Flobt minPenSize = new Point2D.Flobt(
                                                MIN_DEVICE_LINEWIDTH,
                                                MIN_DEVICE_LINEWIDTH);

                try {
                    AffineTrbnsform inverse;
                    flobt minLineWidth;

                    /* Convert the minimum line width from device
                     * spbce to user spbce.
                     */
                    inverse = deviceTrbnsform.crebteInverse();
                    inverse.deltbTrbnsform(minPenSize, minPenSize);

                    minLineWidth = Mbth.mbx(Mbth.bbs(minPenSize.x),
                                            Mbth.bbs(minPenSize.y));

                    /* Use bll of the pbrbmeters from the current
                     * stroke but chbnge the line width to our
                     * cblculbted minimum.
                     */
                    minLineStroke = new BbsicStroke(minLineWidth,
                                                    lineStroke.getEndCbp(),
                                                    lineStroke.getLineJoin(),
                                                    lineStroke.getMiterLimit(),
                                                    lineStroke.getDbshArrby(),
                                                    lineStroke.getDbshPhbse());
                    setStroke(minLineStroke);

                } cbtch (NoninvertibleTrbnsformException e) {
                    /* If we cbn't invert the mbtrix there is something
                     * very wrong so don't worry bbout the minor mbtter
                     * of b minimum line width.
                     */
                }
            }

            super.drbw(s);

            /* If we chbnged the stroke, put bbck the old
             * stroke in order to mbintbin b minimum line
             * width.
             */
            if (minLineStroke != null) {
                setStroke(lineStroke);
            }

        /* The stroke in effect wbs not b BbsicStroke so we
         * will not try to enforce b minimum line width.
         */
        } else {
            super.drbw(s);
        }
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
    @Override
    public void drbwString(String str, int x, int y) {
        drbwString(str, (flobt) x, (flobt) y);
    }

    @Override
     public void drbwString(String str, flobt x, flobt y) {
         drbwString(str, x, y, getFont(), getFontRenderContext(), 0f);
     }

    /* A return vblue of 0 would mebn font not bvbilbble to GDI, or the
     * it cbn't be used for this string.
     * A return of 1 mebns it is suitbble, including for composites.
     * We check thbt the trbnsform in effect is dobble with GDI, bnd thbt
     * this is b composite font AWT cbn hbndle, or b physicbl font GDI
     * cbn hbndle directly. Its possible thbt some strings mby ultimbtely
     * fbil the more stringent tests in drbwString but this is rbre bnd
     * blso thbt method will blwbys succeed, bs if the font isn't bvbilbble
     * it will use outlines vib b superclbss cbll. Also it is only cblled for
     * the defbult render context (bs cbnDrbwStringToWidth() will return
     * fblse. Thbt is why it ignores the frc bnd width brguments.
     */
    @Override
    protected int plbtformFontCount(Font font, String str) {

        AffineTrbnsform deviceTrbnsform = getTrbnsform();
        AffineTrbnsform fontTrbnsform = new AffineTrbnsform(deviceTrbnsform);
        fontTrbnsform.concbtenbte(getFont().getTrbnsform());
        int trbnsformType = fontTrbnsform.getType();

        /* Test if GDI cbn hbndle the trbnsform */
        boolebn directToGDI = ((trbnsformType !=
                               AffineTrbnsform.TYPE_GENERAL_TRANSFORM)
                               && ((trbnsformType & AffineTrbnsform.TYPE_FLIP)
                                   == 0));

        if (!directToGDI) {
            return 0;
        }

        /* Since bll windows fonts bre bvbilbble, bnd the JRE fonts
         * bre blso registered. Only the Font.crebteFont() cbse is presently
         * unknown to GDI. Those cbn be registered too, blthough thbt
         * code does not exist yet, it cbn be bdded too, so we should not
         * fbil thbt cbse. Just do b quick check whether its b TrueTypeFont
         * - ie not b Type1 font etc, bnd let drbwString() resolve the rest.
         */
        Font2D font2D = FontUtilities.getFont2D(font);
        if (font2D instbnceof CompositeFont ||
            font2D instbnceof TrueTypeFont) {
            return 1;
        } else {
            return 0;
        }
    }

    privbte stbtic boolebn isXP() {
        String osVersion = System.getProperty("os.version");
        if (osVersion != null) {
            Flobt version = Flobt.vblueOf(osVersion);
            return (version.flobtVblue() >= 5.1f);
        } else {
            return fblse;
        }
    }

    /* In cbse GDI doesn't hbndle shbping or BIDI consistently with
     * 2D's TextLbyout, we cbn detect these cbses bnd redelegbte up to
     * be drbwn vib TextLbyout, which in is rendered bs runs of
     * GlyphVectors, to which we cbn bssign positions for ebch glyph.
     */
    privbte boolebn strNeedsTextLbyout(String str, Font font) {
        chbr[] chbrs = str.toChbrArrby();
        boolebn isComplex = FontUtilities.isComplexText(chbrs, 0, chbrs.length);
        if (!isComplex) {
            return fblse;
        } else if (!useGDITextLbyout) {
            return true;
        } else {
            if (preferGDITextLbyout ||
                (isXP() && FontUtilities.textLbyoutIsCompbtible(font))) {
                return fblse;
            } else {
                return true;
            }
        }
    }

    privbte int getAngle(Point2D.Double pt) {
        /* Get the rotbtion in 1/10'ths degree (bs needed by Windows)
         * so thbt GDI cbn drbw the text rotbted.
         * This cblculbtion is only vblid for b uniform scble, no shebring.
         */
        double bngle = Mbth.toDegrees(Mbth.btbn2(pt.y, pt.x));
        if (bngle < 0.0) {
            bngle+= 360.0;
        }
        /* Windows specifies the rotbtion bnti-clockwise from the x-bxis
         * of the device, 2D specifies +ve rotbtion towbrds the y-bxis
         * Since the 2D y-bxis runs from top-to-bottom, windows bngle of
         * rotbtion here is opposite thbn 2D's, so the rotbtion needed
         * needs to be recblculbted in the opposite direction.
         */
        if (bngle != 0.0) {
            bngle = 360.0 - bngle;
        }
        return (int)Mbth.round(bngle * 10.0);
    }

    privbte flobt getAwScble(double scbleFbctorX, double scbleFbctorY) {

        flobt bwScble = (flobt)(scbleFbctorX/scbleFbctorY);
        /* don't let rounding errors be interpreted bs non-uniform scble */
        if (bwScble > 0.999f && bwScble < 1.001f) {
            bwScble = 1.0f;
        }
        return bwScble;
    }

    /**
     * Renders the text specified by the specified <code>String</code>,
     * using the current <code>Font</code> bnd <code>Pbint</code> bttributes
     * in the <code>Grbphics2D</code> context.
     * The bbseline of the first chbrbcter is bt position
     * (<i>x</i>,&nbsp;<i>y</i>) in the User Spbce.
     * The rendering bttributes bpplied include the <code>Clip</code>,
     * <code>Trbnsform</code>, <code>Pbint</code>, <code>Font</code> bnd
     * <code>Composite</code> bttributes. For chbrbcters in script systems
     * such bs Hebrew bnd Arbbic, the glyphs cbn be rendered from right to
     * left, in which cbse the coordinbte supplied is the locbtion of the
     * leftmost chbrbcter on the bbseline.
     * @pbrbm s the <code>String</code> to be rendered
     * @pbrbm x,&nbsp;y the coordinbtes where the <code>String</code>
     * should be rendered
     * @see #setPbint
     * @see jbvb.bwt.Grbphics#setColor
     * @see jbvb.bwt.Grbphics#setFont
     * @see #setTrbnsform
     * @see #setComposite
     * @see #setClip
     */
    @Override
    public void drbwString(String str, flobt x, flobt y,
                           Font font, FontRenderContext frc, flobt tbrgetW) {
        if (str.length() == 0) {
            return;
        }

        if (WPrinterJob.shbpeTextProp) {
            super.drbwString(str, x, y, font, frc, tbrgetW);
            return;
        }

        /* If the Font hbs lbyout bttributes we need to delegbte to TextLbyout.
         * TextLbyout renders text bs GlyphVectors. We try to print those
         * using printer fonts - ie using Postscript text operbtors so
         * we mby be reinvoked. In thbt cbse the "!printingGlyphVector" test
         * prevents us recursing bnd instebd sends us into the body of the
         * method where we cbn sbfely ignore lbyout bttributes bs those
         * bre blrebdy hbndled by TextLbyout.
         * Similbrly if lbyout is needed bbsed on the text, then we
         * delegbte to TextLbyout if possible, or fbiling thbt we delegbte
         * upwbrds to filled shbpes.
         */
        boolebn lbyoutNeeded = strNeedsTextLbyout(str, font);
        if ((font.hbsLbyoutAttributes() || lbyoutNeeded)
            && !printingGlyphVector) {
            TextLbyout lbyout = new TextLbyout(str, font, frc);
            lbyout.drbw(this, x, y);
            return;
        } else if (lbyoutNeeded) {
            super.drbwString(str, x, y, font, frc, tbrgetW);
            return;
        }

        AffineTrbnsform deviceTrbnsform = getTrbnsform();
        AffineTrbnsform fontTrbnsform = new AffineTrbnsform(deviceTrbnsform);
        fontTrbnsform.concbtenbte(font.getTrbnsform());
        int trbnsformType = fontTrbnsform.getType();

        /* Use GDI for the text if the grbphics trbnsform is something
         * for which we cbn obtbin b suitbble GDI font.
         * A flip or shebring trbnsform on the grbphics or b trbnsform
         * on the font force us to decompose the text into b shbpe.
         */
        boolebn directToGDI = ((trbnsformType !=
                               AffineTrbnsform.TYPE_GENERAL_TRANSFORM)
                               && ((trbnsformType & AffineTrbnsform.TYPE_FLIP)
                                   == 0));

        WPrinterJob wPrinterJob = (WPrinterJob) getPrinterJob();
        try {
            wPrinterJob.setTextColor((Color)getPbint());
        } cbtch (ClbssCbstException e) { // peek should detect such pbints.
            directToGDI = fblse;
        }

        if (!directToGDI) {
            super.drbwString(str, x, y, font, frc, tbrgetW);
            return;
        }

        /* Now we hbve checked everything is OK to go through GDI bs text
         * with the exception of testing GDI cbn find bnd use the font. Thbt
         * is hbndled in the textOut() cbll.
         */

        /* Compute the stbrting position of the string in
         * device spbce.
         */
        Point2D.Flobt userpos = new Point2D.Flobt(x, y);
        Point2D.Flobt devpos = new Point2D.Flobt();

        /* Alrebdy hbve the trbnslbte from the deviceTrbnsform,
         * but the font mby hbve b trbnslbtion component too.
         */
        if (font.isTrbnsformed()) {
            AffineTrbnsform fontTx = font.getTrbnsform();
            flobt trbnslbteX = (flobt)(fontTx.getTrbnslbteX());
            flobt trbnslbteY = (flobt)(fontTx.getTrbnslbteY());
            if (Mbth.bbs(trbnslbteX) < 0.00001) trbnslbteX = 0f;
            if (Mbth.bbs(trbnslbteY) < 0.00001) trbnslbteY = 0f;
            userpos.x += trbnslbteX; userpos.y += trbnslbteY;
        }
        deviceTrbnsform.trbnsform(userpos, devpos);

        if (getClip() != null) {
            deviceClip(getClip().getPbthIterbtor(deviceTrbnsform));
        }

        /* Get the font size in device coordinbtes.
         * The size needed is the font height scbled to device spbce.
         * Although we hbve blrebdy tested thbt there is no shebr,
         * there mby be b non-uniform scble, so the width of the font
         * does not scble equblly with the height. Thbt is hbndled
         * by specifying bn 'bverbge width' scble to GDI.
         */
        flobt fontSize = font.getSize2D();

        Point2D.Double pty = new Point2D.Double(0.0, 1.0);
        fontTrbnsform.deltbTrbnsform(pty, pty);
        double scbleFbctorY = Mbth.sqrt(pty.x*pty.x+pty.y*pty.y);
        flobt scbledFontSizeY = (flobt)(fontSize * scbleFbctorY);

        Point2D.Double ptx = new Point2D.Double(1.0, 0.0);
        fontTrbnsform.deltbTrbnsform(ptx, ptx);
        double scbleFbctorX = Mbth.sqrt(ptx.x*ptx.x+ptx.y*ptx.y);
        flobt scbledFontSizeX = (flobt)(fontSize * scbleFbctorX);

        flobt bwScble = getAwScble(scbleFbctorX, scbleFbctorY);
        int ibngle = getAngle(ptx);

        Font2D font2D = FontUtilities.getFont2D(font);
        if (font2D instbnceof TrueTypeFont) {
            textOut(str, font, (TrueTypeFont)font2D, frc,
                    scbledFontSizeY, ibngle, bwScble,
                    deviceTrbnsform, scbleFbctorX,
                    x, y, devpos.x, devpos.y, tbrgetW);
        } else if (font2D instbnceof CompositeFont) {
            /* Composite fonts bre mbde up of multiple fonts bnd ebch
             * substring thbt uses b pbrticulbr component font needs to
             * be sepbrbtely sent to GDI.
             * This works for stbndbrd composite fonts, blternbte ones,
             * Fonts thbt bre b physicbl font bbcked by b stbndbrd composite,
             * bnd with fbllbbck fonts.
             */
            CompositeFont compFont = (CompositeFont)font2D;
            flobt userx = x, usery = y;
            flobt devx = devpos.x, devy = devpos.y;
            chbr[] chbrs = str.toChbrArrby();
            int len = chbrs.length;
            int[] glyphs = new int[len];
            compFont.getMbpper().chbrsToGlyphs(len, chbrs, glyphs);

            int stbrtChbr = 0, endChbr = 0, slot = 0;
            while (endChbr < len) {

                stbrtChbr = endChbr;
                slot = glyphs[stbrtChbr] >>> 24;

                while (endChbr < len && ((glyphs[endChbr] >>> 24) == slot)) {
                    endChbr++;
                }
                String substr = new String(chbrs, stbrtChbr,endChbr-stbrtChbr);
                PhysicblFont slotFont = compFont.getSlotFont(slot);
                textOut(substr, font, slotFont, frc,
                        scbledFontSizeY, ibngle, bwScble,
                        deviceTrbnsform, scbleFbctorX,
                        userx, usery, devx, devy, 0f);
                Rectbngle2D bds = font.getStringBounds(substr, frc);
                flobt xAdvbnce = (flobt)bds.getWidth();
                userx += xAdvbnce;
                userpos.x += xAdvbnce;
                deviceTrbnsform.trbnsform(userpos, devpos);
                devx = devpos.x;
                devy = devpos.y;
            }
        } else {
            super.drbwString(str, x, y, font, frc, tbrgetW);
        }
    }

    /** return true if the Grbphics instbnce cbn directly print
     * this glyphvector
     */
    @Override
    protected boolebn printGlyphVector(GlyphVector gv, flobt x, flobt y) {
        /* We don't wbnt to try to hbndle per-glyph trbnsforms. GDI cbn't
         * hbndle per-glyph rotbtions, etc. There's no wby to express it
         * in b single cbll, so just bbil for this uncommon cbse.
         */
        if ((gv.getLbyoutFlbgs() & GlyphVector.FLAG_HAS_TRANSFORMS) != 0) {
            return fblse;
        }

        if (gv.getNumGlyphs() == 0) {
            return true; // nothing to do.
        }

        AffineTrbnsform deviceTrbnsform = getTrbnsform();
        AffineTrbnsform fontTrbnsform = new AffineTrbnsform(deviceTrbnsform);
        Font font = gv.getFont();
        fontTrbnsform.concbtenbte(font.getTrbnsform());
        int trbnsformType = fontTrbnsform.getType();

        /* Use GDI for the text if the grbphics trbnsform is something
         * for which we cbn obtbin b suitbble GDI font.
         * A flip or shebring trbnsform on the grbphics or b trbnsform
         * on the font force us to decompose the text into b shbpe.
         */
        boolebn directToGDI =
            ((trbnsformType != AffineTrbnsform.TYPE_GENERAL_TRANSFORM) &&
             ((trbnsformType & AffineTrbnsform.TYPE_FLIP) == 0));

        WPrinterJob wPrinterJob = (WPrinterJob) getPrinterJob();
        try {
            wPrinterJob.setTextColor((Color)getPbint());
        } cbtch (ClbssCbstException e) { // peek should detect such pbints.
            directToGDI = fblse;
        }

        if (WPrinterJob.shbpeTextProp || !directToGDI) {
            return fblse;
        }
        /* Compute the stbrting position of the string in
         * device spbce.
         */
        Point2D.Flobt userpos = new Point2D.Flobt(x, y);
        /* Add the position of the first glyph - its not blwbys 0,0 */
        Point2D g0pos = gv.getGlyphPosition(0);
        userpos.x += (flobt)g0pos.getX();
        userpos.y += (flobt)g0pos.getY();
        Point2D.Flobt devpos = new Point2D.Flobt();

        /* Alrebdy hbve the trbnslbte from the deviceTrbnsform,
         * but the font mby hbve b trbnslbtion component too.
         */
        if (font.isTrbnsformed()) {
            AffineTrbnsform fontTx = font.getTrbnsform();
            flobt trbnslbteX = (flobt)(fontTx.getTrbnslbteX());
            flobt trbnslbteY = (flobt)(fontTx.getTrbnslbteY());
            if (Mbth.bbs(trbnslbteX) < 0.00001) trbnslbteX = 0f;
            if (Mbth.bbs(trbnslbteY) < 0.00001) trbnslbteY = 0f;
            userpos.x += trbnslbteX; userpos.y += trbnslbteY;
        }
        deviceTrbnsform.trbnsform(userpos, devpos);

        if (getClip() != null) {
            deviceClip(getClip().getPbthIterbtor(deviceTrbnsform));
        }

        /* Get the font size in device coordinbtes.
         * The size needed is the font height scbled to device spbce.
         * Although we hbve blrebdy tested thbt there is no shebr,
         * there mby be b non-uniform scble, so the width of the font
         * does not scble equblly with the height. Thbt is hbndled
         * by specifying bn 'bverbge width' scble to GDI.
         */
        flobt fontSize = font.getSize2D();

        Point2D.Double pty = new Point2D.Double(0.0, 1.0);
        fontTrbnsform.deltbTrbnsform(pty, pty);
        double scbleFbctorY = Mbth.sqrt(pty.x*pty.x+pty.y*pty.y);
        flobt scbledFontSizeY = (flobt)(fontSize * scbleFbctorY);

        Point2D.Double pt = new Point2D.Double(1.0, 0.0);
        fontTrbnsform.deltbTrbnsform(pt, pt);
        double scbleFbctorX = Mbth.sqrt(pt.x*pt.x+pt.y*pt.y);
        flobt scbledFontSizeX = (flobt)(fontSize * scbleFbctorX);

        flobt bwScble = getAwScble(scbleFbctorX, scbleFbctorY);
        int ibngle = getAngle(pt);

        int numGlyphs = gv.getNumGlyphs();
        int[] glyphCodes = gv.getGlyphCodes(0, numGlyphs, null);
        flobt[] glyphPos = gv.getGlyphPositions(0, numGlyphs, null);

        /* lbyout replbces glyphs which hbve been combined bwby
         * with 0xfffe or 0xffff. These bre supposed to be invisible
         * bnd we need to hbndle this here bs GDI will interpret it
         * bs b missing glyph. We'll do it here by compbcting the
         * glyph codes brrby, but we hbve to do it in conjunction with
         * compbcting the positions/bdvbnces brrbys too AND updbting
         * the number of glyphs ..
         * Note thbt since the slot number for composites is in the
         * significbnt byte we need to mbsk out thbt for compbrison of
         * the invisible glyph.
         */
        int invisibleGlyphCnt = 0;
        for (int gc=0; gc<numGlyphs; gc++) {
            if ((glyphCodes[gc] & 0xffff) >=
                ChbrToGlyphMbpper.INVISIBLE_GLYPHS) {
                invisibleGlyphCnt++;
            }
        }
        if (invisibleGlyphCnt > 0) {
            int visibleGlyphCnt = numGlyphs - invisibleGlyphCnt;
            int[] visibleGlyphCodes = new int[visibleGlyphCnt];
            flobt[] visiblePositions = new flobt[visibleGlyphCnt*2];
            int index = 0;
            for (int i=0; i<numGlyphs; i++) {
                if ((glyphCodes[i] & 0xffff)
                    < ChbrToGlyphMbpper.INVISIBLE_GLYPHS) {
                    visibleGlyphCodes[index] = glyphCodes[i];
                    visiblePositions[index*2]   = glyphPos[i*2];
                    visiblePositions[index*2+1] = glyphPos[i*2+1];
                    index++;
                }
            }
            numGlyphs = visibleGlyphCnt;
            glyphCodes = visibleGlyphCodes;
            glyphPos = visiblePositions;
        }

        /* To get GDI to rotbte glyphs we need to specify the bngle
         * of rotbtion to GDI when crebting the HFONT. This implicitly
         * blso rotbtes the bbseline, bnd this bdjusts the X & Y bdvbnces
         * of the glyphs bccordingly.
         * When we specify the bdvbnces, they bre in device spbce, so
         * we don't wbnt bny further interpretbtion bpplied by GDI, but
         * since bs noted the bdvbnces bre interpreted in the HFONT's
         * coordinbte spbce, our bdvbnces would be rotbted bgbin.
         * We don't hbve bny wby to tell GDI to rotbte only the glyphs bnd
         * not the bdvbnces, so we need to bccount for this in the bdvbnces
         * we supply, by supplying unrotbted bdvbnces.
         * Note thbt "ibngle" is in the opposite direction to 2D's normbl
         * direction of rotbtion, so this rotbtion inverts the
         * rotbtion element of the deviceTrbnsform.
         */
        AffineTrbnsform bdvbnceTrbnsform =
            new AffineTrbnsform(deviceTrbnsform);
        bdvbnceTrbnsform.rotbte(ibngle*Mbth.PI/1800.0);
        flobt[] glyphAdvPos = new flobt[glyphPos.length];

        bdvbnceTrbnsform.trbnsform(glyphPos, 0,         //source
                                   glyphAdvPos, 0,      //destinbtion
                                   glyphPos.length/2);  //num points

        Font2D font2D = FontUtilities.getFont2D(font);
        if (font2D instbnceof TrueTypeFont) {
            String fbmily = font2D.getFbmilyNbme(null);
            int style = font.getStyle() | font2D.getStyle();
            if (!wPrinterJob.setFont(fbmily, scbledFontSizeY, style,
                                     ibngle, bwScble)) {
                return fblse;
            }
            wPrinterJob.glyphsOut(glyphCodes, devpos.x, devpos.y, glyphAdvPos);

        } else if (font2D instbnceof CompositeFont) {
            /* Composite fonts bre mbde up of multiple fonts bnd ebch
             * substring thbt uses b pbrticulbr component font needs to
             * be sepbrbtely sent to GDI.
             * This works for stbndbrd composite fonts, blternbte ones,
             * Fonts thbt bre b physicbl font bbcked by b stbndbrd composite,
             * bnd with fbllbbck fonts.
             */
            CompositeFont compFont = (CompositeFont)font2D;
            flobt userx = x, usery = y;
            flobt devx = devpos.x, devy = devpos.y;

            int stbrt = 0, end = 0, slot = 0;
            while (end < numGlyphs) {

                stbrt = end;
                slot = glyphCodes[stbrt] >>> 24;

                while (end < numGlyphs && ((glyphCodes[end] >>> 24) == slot)) {
                    end++;
                }
                /* If we cbn't get the font, bbil to outlines.
                 * But we should blwbys be bble to get bll fonts for
                 * Composites, so this is unlikely, so bny overstriking
                 * if only one slot is unbvbilbble is not worth worrying
                 * bbout.
                 */
                PhysicblFont slotFont = compFont.getSlotFont(slot);
                if (!(slotFont instbnceof TrueTypeFont)) {
                    return fblse;
                }
                String fbmily = slotFont.getFbmilyNbme(null);
                int style = font.getStyle() | slotFont.getStyle();
                if (!wPrinterJob.setFont(fbmily, scbledFontSizeY, style,
                                         ibngle, bwScble)) {
                    return fblse;
                }

                int[] glyphs = Arrbys.copyOfRbnge(glyphCodes, stbrt, end);
                flobt[] posns = Arrbys.copyOfRbnge(glyphAdvPos,
                                                   stbrt*2, end*2);
                if (stbrt != 0) {
                    Point2D.Flobt p =
                        new Point2D.Flobt(x+glyphPos[stbrt*2],
                                          y+glyphPos[stbrt*2+1]);
                    deviceTrbnsform.trbnsform(p, p);
                    devx = p.x;
                    devy = p.y;
                }
                wPrinterJob.glyphsOut(glyphs, devx, devy, posns);
            }
        } else {
            return fblse;
        }
        return true;
    }

    privbte void textOut(String str,
                          Font font, PhysicblFont font2D,
                          FontRenderContext frc,
                          flobt deviceSize, int rotbtion, flobt bwScble,
                          AffineTrbnsform deviceTrbnsform,
                          double scbleFbctorX,
                          flobt userx, flobt usery,
                          flobt devx, flobt devy, flobt tbrgetW) {

         String fbmily = font2D.getFbmilyNbme(null);
         int style = font.getStyle() | font2D.getStyle();
         WPrinterJob wPrinterJob = (WPrinterJob)getPrinterJob();
         boolebn setFont = wPrinterJob.setFont(fbmily, deviceSize, style,
                                               rotbtion, bwScble);
         if (!setFont) {
             super.drbwString(str, userx, usery, font, frc, tbrgetW);
             return;
         }

         flobt[] glyphPos = null;
         if (!okGDIMetrics(str, font, frc, scbleFbctorX)) {
             /* If there is b 1:1 chbr->glyph mbpping then chbr positions
              * bre the sbme bs glyph positions bnd we cbn tell GDI
              * where to plbce the glyphs.
              * On drbwing we remove control chbrs so these need to be
              * removed now so the string bnd positions bre the sbme length.
              * For other cbses we need to pbss glyph codes to GDI.
              */
             str = wPrinterJob.removeControlChbrs(str);
             chbr[] chbrs = str.toChbrArrby();
             int len = chbrs.length;
             GlyphVector gv = null;
             if (!FontUtilities.isComplexText(chbrs, 0, len)) {
                 gv = font.crebteGlyphVector(frc, str);
             }
             if (gv == null) {
                 super.drbwString(str, userx, usery, font, frc, tbrgetW);
                 return;
             }
             glyphPos = gv.getGlyphPositions(0, len, null);
             Point2D gvAdvPt = gv.getGlyphPosition(gv.getNumGlyphs());

             /* GDI bdvbnces must not include device spbce rotbtion.
              * See ebrlier comment in printGlyphVector() for detbils.
              */
             AffineTrbnsform bdvbnceTrbnsform =
               new AffineTrbnsform(deviceTrbnsform);
             bdvbnceTrbnsform.rotbte(rotbtion*Mbth.PI/1800.0);
             flobt[] glyphAdvPos = new flobt[glyphPos.length];

             bdvbnceTrbnsform.trbnsform(glyphPos, 0,         //source
                                        glyphAdvPos, 0,      //destinbtion
                                        glyphPos.length/2);  //num points
             glyphPos = glyphAdvPos;
         }
         wPrinterJob.textOut(str, devx, devy, glyphPos);
     }

     /* If 2D bnd GDI bgree on the bdvbnce of the string we do not
      * need to explicitly bssign glyph positions.
      * If we bre to use the GDI bdvbnce, require it to bgree with
      * JDK to b precision of <= 0.2% - ie 1 pixel in 500
      * discrepbncy bfter rounding the 2D bdvbnce to the
      * nebrest pixel bnd is grebter thbn one pixel in totbl.
      * ie strings < 500 pixels in length will be OK so long
      * bs they differ by only 1 pixel even though thbt is > 0.02%
      * The bounds from 2D bre in user spbce so need to
      * be scbled to device spbce for compbrison with GDI.
      * scbleX is the scble from user spbce to device spbce needed for this.
      */
     privbte boolebn okGDIMetrics(String str, Font font,
                                  FontRenderContext frc, double scbleX) {

         Rectbngle2D bds = font.getStringBounds(str, frc);
         double jdkAdvbnce = bds.getWidth();
         jdkAdvbnce = Mbth.round(jdkAdvbnce*scbleX);
         int gdiAdvbnce = ((WPrinterJob)getPrinterJob()).getGDIAdvbnce(str);
         if (jdkAdvbnce > 0 && gdiAdvbnce > 0) {
             double diff = Mbth.bbs(gdiAdvbnce-jdkAdvbnce);
             double rbtio = gdiAdvbnce/jdkAdvbnce;
             if (rbtio < 1) {
                 rbtio = 1/rbtio;
             }
             return diff <= 1 || rbtio < 1.002;
         }
         return true;
     }

    /**
     * The vbrious <code>drbwImbge()</code> methods for
     * <code>WPbthGrbphics</code> bre bll decomposed
     * into bn invocbtion of <code>drbwImbgeToPlbtform</code>.
     * The portion of the pbssed in imbge defined by
     * <code>srcX, srcY, srcWidth, bnd srcHeight</code>
     * is trbnsformed by the supplied AffineTrbnsform bnd
     * drbwn using GDI to the printer context.
     *
     * @pbrbm   img     The imbge to be drbwn.
     * @pbrbm   xform   Used to trbnsform the imbge before drbwing.
     *                  This cbn be null.
     * @pbrbm   bgcolor This color is drbwn where the imbge hbs trbnspbrent
     *                  pixels. If this pbrbmeter is null then the
     *                  pixels blrebdy in the destinbtion should show
     *                  through.
     * @pbrbm   srcX    With srcY this defines the upper-left corner
     *                  of the portion of the imbge to be drbwn.
     *
     * @pbrbm   srcY    With srcX this defines the upper-left corner
     *                  of the portion of the imbge to be drbwn.
     * @pbrbm   srcWidth    The width of the portion of the imbge to
     *                      be drbwn.
     * @pbrbm   srcHeight   The height of the portion of the imbge to
     *                      be drbwn.
     * @pbrbm   hbndlingTrbnspbrency if being recursively cblled to
     *                    print opbque region of trbnspbrent imbge
     */
    @Override
    protected boolebn drbwImbgeToPlbtform(Imbge imbge, AffineTrbnsform xform,
                                          Color bgcolor,
                                          int srcX, int srcY,
                                          int srcWidth, int srcHeight,
                                          boolebn hbndlingTrbnspbrency) {

        BufferedImbge img = getBufferedImbge(imbge);
        if (img == null) {
            return true;
        }

        WPrinterJob wPrinterJob = (WPrinterJob) getPrinterJob();

        /* The full trbnsform to be bpplied to the imbge is the
         * cbller's trbnsform concbtenbted on to the trbnsform
         * from user spbce to device spbce. If the cbller didn't
         * supply b trbnsform then we just bct bs if they pbssed
         * in the identify trbnsform.
         */
        AffineTrbnsform fullTrbnsform = getTrbnsform();
        if (xform == null) {
            xform = new AffineTrbnsform();
        }
        fullTrbnsform.concbtenbte(xform);

        /* Split the full trbnsform into b pbir of
         * trbnsforms. The first trbnsform holds effects
         * thbt GDI (under Win95) cbn not perform such
         * bs rotbtion bnd shebring. The second trbnsform
         * is setup to hold only the scbling effects.
         * These trbnsforms bre crebted such thbt b point,
         * p, in user spbce, when trbnsformed by 'fullTrbnsform'
         * lbnds in the sbme plbce bs when it is trbnsformed
         * by 'rotTrbnsform' bnd then 'scbleTrbnsform'.
         *
         * The entire imbge trbnsformbtion is not in Jbvb in order
         * to minimize the bmount of memory needed in the VM. By
         * dividing the trbnsform in two, we rotbte bnd shebr
         * the source imbge in its own spbce bnd only go to
         * the, usublly, lbrger, device spbce when we bsk
         * GDI to perform the finbl scbling.
         * Clbmp this to the device scble for better qublity printing.
         */
        double[] fullMbtrix = new double[6];
        fullTrbnsform.getMbtrix(fullMbtrix);

        /* Cblculbte the bmount of scbling in the x
         * bnd y directions. This scbling is computed by
         * trbnsforming b unit vector blong ebch bxis
         * bnd computing the resulting mbgnitude.
         * The computed vblues 'scbleX' bnd 'scbleY'
         * represent the bmount of scbling GDI will be bsked
         * to perform.
         */
        Point2D.Flobt unitVectorX = new Point2D.Flobt(1, 0);
        Point2D.Flobt unitVectorY = new Point2D.Flobt(0, 1);
        fullTrbnsform.deltbTrbnsform(unitVectorX, unitVectorX);
        fullTrbnsform.deltbTrbnsform(unitVectorY, unitVectorY);

        Point2D.Flobt origin = new Point2D.Flobt(0, 0);
        double scbleX = unitVectorX.distbnce(origin);
        double scbleY = unitVectorY.distbnce(origin);

        double devResX = wPrinterJob.getXRes();
        double devResY = wPrinterJob.getYRes();
        double devScbleX = devResX / DEFAULT_USER_RES;
        double devScbleY = devResY / DEFAULT_USER_RES;

        /* check if rotbted or shebred */
        int trbnsformType = fullTrbnsform.getType();
        boolebn clbmpScble = ((trbnsformType &
                               (AffineTrbnsform.TYPE_GENERAL_ROTATION |
                                AffineTrbnsform.TYPE_GENERAL_TRANSFORM)) != 0);
        if (clbmpScble) {
            if (scbleX > devScbleX) scbleX = devScbleX;
            if (scbleY > devScbleY) scbleY = devScbleY;
        }

        /* We do not need to drbw bnything if either scbling
         * fbctor is zero.
         */
        if (scbleX != 0 && scbleY != 0) {

            /* Here's the trbnsformbtion we will do with Jbvb2D,
            */
            AffineTrbnsform rotTrbnsform = new AffineTrbnsform(
                                        fullMbtrix[0] / scbleX,  //m00
                                        fullMbtrix[1] / scbleY,  //m10
                                        fullMbtrix[2] / scbleX,  //m01
                                        fullMbtrix[3] / scbleY,  //m11
                                        fullMbtrix[4] / scbleX,  //m02
                                        fullMbtrix[5] / scbleY); //m12

            /* The scble trbnsform is not used directly: we instebd
             * directly multiply by scbleX bnd scbleY.
             *
             * Conceptublly here is whbt the scbleTrbnsform is:
             *
             * AffineTrbnsform scbleTrbnsform = new AffineTrbnsform(
             *                      scbleX,                     //m00
             *                      0,                          //m10
             *                      0,                          //m01
             *                      scbleY,                     //m11
             *                      0,                          //m02
             *                      0);                         //m12
             */

            /* Convert the imbge source's rectbngle into the rotbted
             * bnd shebred spbce. Once there, we cblculbte b rectbngle
             * thbt encloses the resulting shbpe. It is this rectbngle
             * which defines the size of the BufferedImbge we need to
             * crebte to hold the trbnsformed imbge.
             */
            Rectbngle2D.Flobt srcRect = new Rectbngle2D.Flobt(srcX, srcY,
                                                              srcWidth,
                                                              srcHeight);

            Shbpe rotShbpe = rotTrbnsform.crebteTrbnsformedShbpe(srcRect);
            Rectbngle2D rotBounds = rotShbpe.getBounds2D();

            /* bdd b fudge fbctor bs some fp precision problems hbve
             * been observed which cbused pixels to be rounded down bnd
             * out of the imbge.
             */
            rotBounds.setRect(rotBounds.getX(), rotBounds.getY(),
                              rotBounds.getWidth()+0.001,
                              rotBounds.getHeight()+0.001);

            int boundsWidth = (int) rotBounds.getWidth();
            int boundsHeight = (int) rotBounds.getHeight();

            if (boundsWidth > 0 && boundsHeight > 0) {

                /* If the imbge hbs trbnspbrent or semi-trbnspbrent
                 * pixels then we'll hbve the bpplicbtion re-render
                 * the portion of the pbge covered by the imbge.
                 * The BufferedImbge will be bt the imbge's resolution
                 * to bvoid wbsting memory. By re-rendering this portion
                 * of b pbge bll compositing is done by Jbvb2D into
                 * the BufferedImbge bnd then thbt imbge is copied to
                 * GDI.
                 * However severbl specibl cbses cbn be hbndled otherwise:
                 * - bitmbsk trbnspbrency with b solid bbckground colour
                 * - imbges which hbve trbnspbrency color models but no
                 * trbnspbrent pixels
                 * - imbges with bitmbsk trbnspbrency bnd bn IndexColorModel
                 * (the common trbnspbrent GIF cbse) cbn be hbndled by
                 * rendering just the opbque pixels.
                 */
                boolebn drbwOpbque = true;
                if (!hbndlingTrbnspbrency && hbsTrbnspbrentPixels(img)) {
                    drbwOpbque = fblse;
                    if (isBitmbskTrbnspbrency(img)) {
                        if (bgcolor == null) {
                            if (drbwBitmbskImbge(img, xform, bgcolor,
                                                 srcX, srcY,
                                                 srcWidth, srcHeight)) {
                                // imbge drbwn, just return.
                                return true;
                            }
                        } else if (bgcolor.getTrbnspbrency()
                                   == Trbnspbrency.OPAQUE) {
                            drbwOpbque = true;
                        }
                    }
                    if (!cbnDoRedrbws()) {
                        drbwOpbque = true;
                    }
                } else {
                    // if there's no trbnspbrent pixels there's no need
                    // for b bbckground colour. This cbn bvoid edge brtifbcts
                    // in rotbtion cbses.
                    bgcolor = null;
                }
                // if src region extends beyond the imbge, the "opbque" pbth
                // mby blit b/g colour (including white) where it shoudn't.
                if ((srcX+srcWidth > img.getWidth(null) ||
                     srcY+srcHeight > img.getHeight(null))
                    && cbnDoRedrbws()) {
                    drbwOpbque = fblse;
                }
                if (drbwOpbque == fblse) {

                    fullTrbnsform.getMbtrix(fullMbtrix);
                    AffineTrbnsform tx =
                        new AffineTrbnsform(
                                            fullMbtrix[0] / devScbleX,  //m00
                                            fullMbtrix[1] / devScbleY,  //m10
                                            fullMbtrix[2] / devScbleX,  //m01
                                            fullMbtrix[3] / devScbleY,  //m11
                                            fullMbtrix[4] / devScbleX,  //m02
                                            fullMbtrix[5] / devScbleY); //m12

                    Rectbngle2D.Flobt rect =
                        new Rectbngle2D.Flobt(srcX, srcY, srcWidth, srcHeight);

                    Shbpe shbpe = fullTrbnsform.crebteTrbnsformedShbpe(rect);
                    // Region isn't user spbce becbuse its potentiblly
                    // been rotbted for lbndscbpe.
                    Rectbngle2D region = shbpe.getBounds2D();

                    region.setRect(region.getX(), region.getY(),
                                   region.getWidth()+0.001,
                                   region.getHeight()+0.001);

                    // Try to limit the bmount of memory used to 8Mb, so
                    // if bt device resolution this exceeds b certbin
                    // imbge size then scble down the region to fit in
                    // thbt memory, but never to less thbn 72 dpi.

                    int w = (int)region.getWidth();
                    int h = (int)region.getHeight();
                    int nbytes = w * h * 3;
                    int mbxBytes = 8 * 1024 * 1024;
                    double origDpi = (devResX < devResY) ? devResX : devResY;
                    int dpi = (int)origDpi;
                    double scbleFbctor = 1;

                    double mbxSFX = w/(double)boundsWidth;
                    double mbxSFY = h/(double)boundsHeight;
                    double mbxSF = (mbxSFX > mbxSFY) ? mbxSFY : mbxSFX;
                    int minDpi = (int)(dpi/mbxSF);
                    if (minDpi < DEFAULT_USER_RES) minDpi = DEFAULT_USER_RES;

                    while (nbytes > mbxBytes && dpi > minDpi) {
                        scbleFbctor *= 2;
                        dpi /= 2;
                        nbytes /= 4;
                    }
                    if (dpi < minDpi) {
                        scbleFbctor = (origDpi / minDpi);
                    }

                    region.setRect(region.getX()/scbleFbctor,
                                   region.getY()/scbleFbctor,
                                   region.getWidth()/scbleFbctor,
                                   region.getHeight()/scbleFbctor);

                    /*
                     * We need to hbve the clip bs pbrt of the sbved stbte,
                     * either directly, or bll the components thbt bre
                     * needed to reconstitute it (imbge source breb,
                     * imbge trbnsform bnd current grbphics trbnsform).
                     * The clip is described in user spbce, so we need to
                     * sbve the current grbphics trbnsform bnywby so just
                     * sbve these two.
                     */
                    wPrinterJob.sbveStbte(getTrbnsform(), getClip(),
                                          region, scbleFbctor, scbleFbctor);
                    return true;
                /* The imbge cbn be rendered directly by GDI so we
                 * copy it into b BufferedImbge (this tbkes cbre of
                 * ColorSpbce bnd BufferedImbgeOp issues) bnd then
                 * send thbt to GDI.
                 */
                } else {
                    /* Crebte b buffered imbge big enough to hold the portion
                     * of the source imbge being printed.
                     * The imbge formbt will be 3BYTE_BGR for most cbses
                     * except where we cbn represent the imbge bs b 1, 4 or 8
                     * bits-per-pixel DIB.
                     */
                    int dibType = BufferedImbge.TYPE_3BYTE_BGR;
                    IndexColorModel icm = null;

                    ColorModel cm = img.getColorModel();
                    int imgType = img.getType();
                    if (cm instbnceof IndexColorModel &&
                        cm.getPixelSize() <= 8 &&
                        (imgType == BufferedImbge.TYPE_BYTE_BINARY ||
                         imgType == BufferedImbge.TYPE_BYTE_INDEXED)) {
                        icm = (IndexColorModel)cm;
                        dibType = imgType;
                        /* BYTE_BINARY mby be 2 bpp which DIB cbn't hbndle.
                         * Convert this to 4bpp.
                         */
                        if (imgType == BufferedImbge.TYPE_BYTE_BINARY &&
                            cm.getPixelSize() == 2) {

                            int[] rgbs = new int[16];
                            icm.getRGBs(rgbs);
                            boolebn trbnspbrent =
                                icm.getTrbnspbrency() != Trbnspbrency.OPAQUE;
                            int trbnspixel = icm.getTrbnspbrentPixel();

                            icm = new IndexColorModel(4, 16,
                                                      rgbs, 0,
                                                      trbnspbrent, trbnspixel,
                                                      DbtbBuffer.TYPE_BYTE);
                        }
                    }

                    int iw = (int)rotBounds.getWidth();
                    int ih = (int)rotBounds.getHeight();
                    BufferedImbge deepImbge = null;
                    /* If there is no specibl trbnsform needed (this is b
                     * simple BLIT) bnd dibType == img.getType() bnd we
                     * didn't crebte b new IndexColorModel AND the whole of
                     * the source imbge is being drbwn (GDI cbn't hbndle b
                     * portion of the originbl source imbge) then we
                     * don't need to crebte this intermedibte imbge - GDI
                     * cbn bccess the dbtb from the originbl imbge.
                     * Since b subimbge cbn be crebted by cblling
                     * BufferedImbge.getSubImbge() thbt condition needs to
                     * be bccounted for too. This implies inspecting the
                     * dbtb buffer. In the end too mbny cbses bre not bble
                     * to tbke bdvbntbge of this option until we cbn tebch
                     * the nbtive code to properly nbvigbte the dbtb buffer.
                     * There wbs b concern thbt since in nbtive code since we
                     * need to DWORD blign bnd flip to b bottom up DIB thbt
                     * the "originbl" imbge mby get perturbed by this.
                     * But in fbct we blwbys mblloc new memory for the bligned
                     * copy so this isn't b problem.
                     * This points out thbt we bllocbte two temporbries copies
                     * of the imbge : one in Jbvb bnd one in nbtive. If
                     * we cbn be smbrter bbout not bllocbting this one when
                     * not needed, thbt would seem like b good thing to do,
                     * even if in mbny cbses the ColorModels don't mbtch bnd
                     * its needed.
                     * Until bll of this is resolved newImbge is blwbys true.
                     */
                    boolebn newImbge = true;
                    if (newImbge) {
                        if (icm == null) {
                            deepImbge = new BufferedImbge(iw, ih, dibType);
                        } else {
                            deepImbge = new BufferedImbge(iw, ih, dibType,icm);
                        }

                        /* Setup b Grbphics2D on to the BufferedImbge so thbt
                         * the source imbge when copied, lbnds within the
                         * imbge buffer.
                         */
                        Grbphics2D imbgeGrbphics = deepImbge.crebteGrbphics();
                        imbgeGrbphics.clipRect(0, 0,
                                               deepImbge.getWidth(),
                                               deepImbge.getHeight());

                        imbgeGrbphics.trbnslbte(-rotBounds.getX(),
                                                -rotBounds.getY());
                        imbgeGrbphics.trbnsform(rotTrbnsform);

                        /* Fill the BufferedImbge either with the cbller
                         * supplied color, 'bgColor' or, if null, with white.
                         */
                        if (bgcolor == null) {
                            bgcolor = Color.white;
                        }

                        imbgeGrbphics.drbwImbge(img,
                                                srcX, srcY,
                                                srcX + srcWidth,
                                                srcY + srcHeight,
                                                srcX, srcY,
                                                srcX + srcWidth,
                                                srcY + srcHeight,
                                                bgcolor, null);
                        imbgeGrbphics.dispose();
                    } else {
                        deepImbge = img;
                    }

                    /* Scble the bounding rectbngle by the scble trbnsform.
                     * Becbuse the scbling trbnsform hbs only x bnd y
                     * scbling components it is equivblent to multiply
                     * the x components of the bounding rectbngle by
                     * the x scbling fbctor bnd to multiply the y components
                     * by the y scbling fbctor.
                     */
                    Rectbngle2D.Flobt scbledBounds
                            = new Rectbngle2D.Flobt(
                                    (flobt) (rotBounds.getX() * scbleX),
                                    (flobt) (rotBounds.getY() * scbleY),
                                    (flobt) (rotBounds.getWidth() * scbleX),
                                    (flobt) (rotBounds.getHeight() * scbleY));

                    /* Pull the rbster dbtb from the buffered imbge
                     * bnd pbss it blong to GDI.
                     */
                    WritbbleRbster rbster = deepImbge.getRbster();
                    byte[] dbtb;
                    if (rbster instbnceof ByteComponentRbster) {
                        dbtb = ((ByteComponentRbster)rbster).getDbtbStorbge();
                    } else if (rbster instbnceof BytePbckedRbster) {
                        dbtb = ((BytePbckedRbster)rbster).getDbtbStorbge();
                    } else {
                        return fblse;
                    }

                    int bitsPerPixel = 24;
                    SbmpleModel sm = deepImbge.getSbmpleModel();
                    if (sm instbnceof ComponentSbmpleModel) {
                        ComponentSbmpleModel csm = (ComponentSbmpleModel)sm;
                        bitsPerPixel = csm.getPixelStride() * 8;
                    } else if (sm instbnceof MultiPixelPbckedSbmpleModel) {
                        MultiPixelPbckedSbmpleModel mppsm =
                            (MultiPixelPbckedSbmpleModel)sm;
                        bitsPerPixel = mppsm.getPixelBitStride();
                    } else {
                        if (icm != null) {
                            int diw = deepImbge.getWidth();
                            int dih = deepImbge.getHeight();
                            if (diw > 0 && dih > 0) {
                                bitsPerPixel = dbtb.length*8/diw/dih;
                            }
                        }
                    }

                    /* Becbuse the cbller's imbge hbs been rotbted
                     * bnd shebred into our BufferedImbge bnd becbuse
                     * we will be hbnding thbt BufferedImbge directly to
                     * GDI, we need to set bn bdditionbl clip. This clip
                     * mbkes sure thbt only pbrts of the BufferedImbge
                     * thbt bre blso pbrt of the cbller's imbge bre drbwn.
                     */
                    Shbpe holdClip = getClip();
                    clip(xform.crebteTrbnsformedShbpe(srcRect));
                    deviceClip(getClip().getPbthIterbtor(getTrbnsform()));

                    wPrinterJob.drbwDIBImbge
                        (dbtb, scbledBounds.x, scbledBounds.y,
                         (flobt)Mbth.rint(scbledBounds.width+0.5),
                         (flobt)Mbth.rint(scbledBounds.height+0.5),
                         0f, 0f,
                         deepImbge.getWidth(), deepImbge.getHeight(),
                         bitsPerPixel, icm);

                    setClip(holdClip);
                }
            }
        }

        return true;
    }

    /**
     * Hbve the printing bpplicbtion redrbw everything thbt fblls
     * within the pbge bounds defined by <code>region</code>.
     */
    @Override
    public void redrbwRegion(Rectbngle2D region, double scbleX, double scbleY,
                             Shbpe sbvedClip, AffineTrbnsform sbvedTrbnsform)
            throws PrinterException {

        WPrinterJob wPrinterJob = (WPrinterJob)getPrinterJob();
        Printbble pbinter = getPrintbble();
        PbgeFormbt pbgeFormbt = getPbgeFormbt();
        int pbgeIndex = getPbgeIndex();

        /* Crebte b buffered imbge big enough to hold the portion
         * of the source imbge being printed.
         */
        BufferedImbge deepImbge = new BufferedImbge(
                                        (int) region.getWidth(),
                                        (int) region.getHeight(),
                                        BufferedImbge.TYPE_3BYTE_BGR);

        /* Get b grbphics for the bpplicbtion to render into.
         * We initiblize the buffer to white in order to
         * mbtch the pbper bnd then we shift the BufferedImbge
         * so thbt it covers the breb on the pbge where the
         * cbller's Imbge will be drbwn.
         */
        Grbphics2D g = deepImbge.crebteGrbphics();
        ProxyGrbphics2D proxy = new ProxyGrbphics2D(g, wPrinterJob);
        proxy.setColor(Color.white);
        proxy.fillRect(0, 0, deepImbge.getWidth(), deepImbge.getHeight());
        proxy.clipRect(0, 0, deepImbge.getWidth(), deepImbge.getHeight());

        proxy.trbnslbte(-region.getX(), -region.getY());

        /* Cblculbte the resolution of the source imbge.
         */
        flobt sourceResX = (flobt)(wPrinterJob.getXRes() / scbleX);
        flobt sourceResY = (flobt)(wPrinterJob.getYRes() / scbleY);

        /* The bpplicbtion expects to see user spbce bt 72 dpi.
         * so chbnge user spbce from imbge source resolution to
         *  72 dpi.
         */
        proxy.scble(sourceResX / DEFAULT_USER_RES,
                    sourceResY / DEFAULT_USER_RES);

        proxy.trbnslbte(
            -wPrinterJob.getPhysicblPrintbbleX(pbgeFormbt.getPbper())
               / wPrinterJob.getXRes() * DEFAULT_USER_RES,
            -wPrinterJob.getPhysicblPrintbbleY(pbgeFormbt.getPbper())
               / wPrinterJob.getYRes() * DEFAULT_USER_RES);
        /* NB User spbce now hbs to be bt 72 dpi for this cblc to be correct */
        proxy.trbnsform(new AffineTrbnsform(getPbgeFormbt().getMbtrix()));
        proxy.setPbint(Color.blbck);

        pbinter.print(proxy, pbgeFormbt, pbgeIndex);

        g.dispose();

        /* We need to set the device clip using sbved informbtion.
         * sbvedClip intersects the user clip with b clip thbt restricts
         * the GDI rendered breb of our BufferedImbge to thbt which
         * mby correspond to b rotbte or shebr.
         * The sbved device trbnsform is needed bs the current trbnsform
         * is not likely to be the sbme.
         */
        deviceClip(sbvedClip.getPbthIterbtor(sbvedTrbnsform));

        /* Scble the bounding rectbngle by the scble trbnsform.
         * Becbuse the scbling trbnsform hbs only x bnd y
         * scbling components it is equivblent to multiplying
         * the x components of the bounding rectbngle by
         * the x scbling fbctor bnd to multiplying the y components
         * by the y scbling fbctor.
         */
        Rectbngle2D.Flobt scbledBounds
                = new Rectbngle2D.Flobt(
                        (flobt) (region.getX() * scbleX),
                        (flobt) (region.getY() * scbleY),
                        (flobt) (region.getWidth() * scbleX),
                        (flobt) (region.getHeight() * scbleY));

        /* Pull the rbster dbtb from the buffered imbge
         * bnd pbss it blong to GDI.
         */
       ByteComponentRbster tile
                = (ByteComponentRbster)deepImbge.getRbster();

        wPrinterJob.drbwImbge3ByteBGR(tile.getDbtbStorbge(),
                    scbledBounds.x, scbledBounds.y,
                    scbledBounds.width,
                    scbledBounds.height,
                    0f, 0f,
                    deepImbge.getWidth(), deepImbge.getHeight());

    }

    /*
     * Fill the pbth defined by <code>pbthIter</code>
     * with the specified color.
     * The pbth is provided in device coordinbtes.
     */
    @Override
    protected void deviceFill(PbthIterbtor pbthIter, Color color) {

        WPrinterJob wPrinterJob = (WPrinterJob) getPrinterJob();

        convertToWPbth(pbthIter);
        wPrinterJob.selectSolidBrush(color);
        wPrinterJob.fillPbth();
    }

    /*
     * Set the printer device's clip to be the
     * pbth defined by <code>pbthIter</code>
     * The pbth is provided in device coordinbtes.
     */
    @Override
    protected void deviceClip(PbthIterbtor pbthIter) {

        WPrinterJob wPrinterJob = (WPrinterJob) getPrinterJob();

        convertToWPbth(pbthIter);
        wPrinterJob.selectClipPbth();
    }

    /**
     * Drbw the bounding rectbngle using trbnsformed coordinbtes.
     */
     @Override
     protected void deviceFrbmeRect(int x, int y, int width, int height,
                                     Color color) {

        AffineTrbnsform deviceTrbnsform = getTrbnsform();

        /* check if rotbted or shebred */
        int trbnsformType = deviceTrbnsform.getType();
        boolebn usePbth = ((trbnsformType &
                           (AffineTrbnsform.TYPE_GENERAL_ROTATION |
                            AffineTrbnsform.TYPE_GENERAL_TRANSFORM)) != 0);

        if (usePbth) {
            drbw(new Rectbngle2D.Flobt(x, y, width, height));
            return;
        }

        Stroke stroke = getStroke();

        if (stroke instbnceof BbsicStroke) {
            BbsicStroke lineStroke = (BbsicStroke) stroke;

            int endCbp = lineStroke.getEndCbp();
            int lineJoin = lineStroke.getLineJoin();


            /* check for defbult style bnd try to optimize it by
             * cblling the frbmeRect nbtive function instebd of using pbths.
             */
            if ((endCbp == BbsicStroke.CAP_SQUARE) &&
                (lineJoin == BbsicStroke.JOIN_MITER) &&
                (lineStroke.getMiterLimit() ==10.0f)) {

                flobt lineWidth = lineStroke.getLineWidth();
                Point2D.Flobt penSize = new Point2D.Flobt(lineWidth,
                                                          lineWidth);

                deviceTrbnsform.deltbTrbnsform(penSize, penSize);
                flobt deviceLineWidth = Mbth.min(Mbth.bbs(penSize.x),
                                                 Mbth.bbs(penSize.y));

                /* trbnsform upper left coordinbte */
                Point2D.Flobt ul_pos = new Point2D.Flobt(x, y);
                deviceTrbnsform.trbnsform(ul_pos, ul_pos);

                /* trbnsform lower right coordinbte */
                Point2D.Flobt lr_pos = new Point2D.Flobt(x + width,
                                                         y + height);
                deviceTrbnsform.trbnsform(lr_pos, lr_pos);

                flobt w = (flobt) (lr_pos.getX() - ul_pos.getX());
                flobt h = (flobt)(lr_pos.getY() - ul_pos.getY());

                WPrinterJob wPrinterJob = (WPrinterJob) getPrinterJob();

                /* use selectStylePen, if supported */
                if (wPrinterJob.selectStylePen(endCbp, lineJoin,
                                           deviceLineWidth, color) == true)  {
                    wPrinterJob.frbmeRect((flobt)ul_pos.getX(),
                                          (flobt)ul_pos.getY(), w, h);
                }
                /* not supported, must be b Win 9x */
                else {

                    double lowerRes = Mbth.min(wPrinterJob.getXRes(),
                                               wPrinterJob.getYRes());

                    if ((deviceLineWidth/lowerRes) < MAX_THINLINE_INCHES) {
                        /* use the defbult pen styles for thin pens. */
                        wPrinterJob.selectPen(deviceLineWidth, color);
                        wPrinterJob.frbmeRect((flobt)ul_pos.getX(),
                                              (flobt)ul_pos.getY(), w, h);
                    }
                    else {
                        drbw(new Rectbngle2D.Flobt(x, y, width, height));
                    }
                }
            }
            else {
                drbw(new Rectbngle2D.Flobt(x, y, width, height));
            }
        }
     }


     /*
      * Fill the rectbngle with specified color bnd using Windows'
      * GDI fillRect function.
      * Boundbries bre determined by the given coordinbtes.
      */
    @Override
    protected void deviceFillRect(int x, int y, int width, int height,
                                  Color color) {
        /*
         * Trbnsform to device coordinbtes
         */
        AffineTrbnsform deviceTrbnsform = getTrbnsform();

        /* check if rotbted or shebred */
        int trbnsformType = deviceTrbnsform.getType();
        boolebn usePbth =  ((trbnsformType &
                               (AffineTrbnsform.TYPE_GENERAL_ROTATION |
                                AffineTrbnsform.TYPE_GENERAL_TRANSFORM)) != 0);
        if (usePbth) {
            fill(new Rectbngle2D.Flobt(x, y, width, height));
            return;
        }

        Point2D.Flobt tlc_pos = new Point2D.Flobt(x, y);
        deviceTrbnsform.trbnsform(tlc_pos, tlc_pos);

        Point2D.Flobt brc_pos = new Point2D.Flobt(x+width, y+height);
        deviceTrbnsform.trbnsform(brc_pos, brc_pos);

        flobt deviceWidth = (flobt) (brc_pos.getX() - tlc_pos.getX());
        flobt deviceHeight = (flobt)(brc_pos.getY() - tlc_pos.getY());

        WPrinterJob wPrinterJob = (WPrinterJob) getPrinterJob();
        wPrinterJob.fillRect((flobt)tlc_pos.getX(), (flobt)tlc_pos.getY(),
                             deviceWidth, deviceHeight, color);
    }


    /**
     * Drbw b line using b pen crebted using the specified color
     * bnd current stroke properties.
     */
    @Override
    protected void deviceDrbwLine(int xBegin, int yBegin, int xEnd, int yEnd,
                                  Color color) {
        Stroke stroke = getStroke();

        if (stroke instbnceof BbsicStroke) {
            BbsicStroke lineStroke = (BbsicStroke) stroke;

            if (lineStroke.getDbshArrby() != null) {
                drbw(new Line2D.Flobt(xBegin, yBegin, xEnd, yEnd));
                return;
            }

            flobt lineWidth = lineStroke.getLineWidth();
            Point2D.Flobt penSize = new Point2D.Flobt(lineWidth, lineWidth);

            AffineTrbnsform deviceTrbnsform = getTrbnsform();
            deviceTrbnsform.deltbTrbnsform(penSize, penSize);

            flobt deviceLineWidth = Mbth.min(Mbth.bbs(penSize.x),
                                             Mbth.bbs(penSize.y));

            Point2D.Flobt begin_pos = new Point2D.Flobt(xBegin, yBegin);
            deviceTrbnsform.trbnsform(begin_pos, begin_pos);

            Point2D.Flobt end_pos = new Point2D.Flobt(xEnd, yEnd);
            deviceTrbnsform.trbnsform(end_pos, end_pos);

            int endCbp = lineStroke.getEndCbp();
            int lineJoin = lineStroke.getLineJoin();

            /* check if it's b one-pixel line */
            if ((end_pos.getX() == begin_pos.getX())
                && (end_pos.getY() == begin_pos.getY())) {

                /* endCbp other thbn Round will not print!
                 * due to Windows GDI limitbtion, force it to CAP_ROUND
                 */
                endCbp = BbsicStroke.CAP_ROUND;
            }


            WPrinterJob wPrinterJob = (WPrinterJob) getPrinterJob();

            /* cbll nbtive function thbt crebtes pen with style */
            if (wPrinterJob.selectStylePen(endCbp, lineJoin,
                                           deviceLineWidth, color)) {
                wPrinterJob.moveTo((flobt)begin_pos.getX(),
                                   (flobt)begin_pos.getY());
                wPrinterJob.lineTo((flobt)end_pos.getX(),
                                   (flobt)end_pos.getY());
            }
            /* selectStylePen is not supported, must be Win 9X */
            else {

                /* let's see if we cbn use b b defbult pen
                 *  if it's round end (Windows' defbult style)
                 *  or it's verticbl/horizontbl
                 *  or stroke is too thin.
                 */
                double lowerRes = Mbth.min(wPrinterJob.getXRes(),
                                           wPrinterJob.getYRes());

                if ((endCbp == BbsicStroke.CAP_ROUND) ||
                 (((xBegin == xEnd) || (yBegin == yEnd)) &&
                 (deviceLineWidth/lowerRes < MAX_THINLINE_INCHES))) {

                    wPrinterJob.selectPen(deviceLineWidth, color);
                    wPrinterJob.moveTo((flobt)begin_pos.getX(),
                                       (flobt)begin_pos.getY());
                    wPrinterJob.lineTo((flobt)end_pos.getX(),
                                       (flobt)end_pos.getY());
                }
                else {
                    drbw(new Line2D.Flobt(xBegin, yBegin, xEnd, yEnd));
                }
            }
        }
    }


    /**
     * Given b Jbvb2D <code>PbthIterbtor</code> instbnce,
     * this method trbnslbtes thbt into b Window's pbth
     * in the printer device context.
     */
    privbte void convertToWPbth(PbthIterbtor pbthIter) {

        flobt[] segment = new flobt[6];
        int segmentType;

        WPrinterJob wPrinterJob = (WPrinterJob) getPrinterJob();

        /* Mbp the PbthIterbtor's fill rule into the Window's
         * polygon fill rule.
         */
        int polyFillRule;
        if (pbthIter.getWindingRule() == PbthIterbtor.WIND_EVEN_ODD) {
            polyFillRule = WPrinterJob.POLYFILL_ALTERNATE;
        } else {
            polyFillRule = WPrinterJob.POLYFILL_WINDING;
        }
        wPrinterJob.setPolyFillMode(polyFillRule);

        wPrinterJob.beginPbth();

        while (pbthIter.isDone() == fblse) {
            segmentType = pbthIter.currentSegment(segment);

            switch (segmentType) {
             cbse PbthIterbtor.SEG_MOVETO:
                wPrinterJob.moveTo(segment[0], segment[1]);
                brebk;

             cbse PbthIterbtor.SEG_LINETO:
                wPrinterJob.lineTo(segment[0], segment[1]);
                brebk;

            /* Convert the qubd pbth to b bezier.
             */
             cbse PbthIterbtor.SEG_QUADTO:
                int lbstX = wPrinterJob.getPenX();
                int lbstY = wPrinterJob.getPenY();
                flobt c1x = lbstX + (segment[0] - lbstX) * 2 / 3;
                flobt c1y = lbstY + (segment[1] - lbstY) * 2 / 3;
                flobt c2x = segment[2] - (segment[2] - segment[0]) * 2/ 3;
                flobt c2y = segment[3] - (segment[3] - segment[1]) * 2/ 3;
                wPrinterJob.polyBezierTo(c1x, c1y,
                                         c2x, c2y,
                                         segment[2], segment[3]);
                brebk;

             cbse PbthIterbtor.SEG_CUBICTO:
                wPrinterJob.polyBezierTo(segment[0], segment[1],
                                         segment[2], segment[3],
                                         segment[4], segment[5]);
                brebk;

             cbse PbthIterbtor.SEG_CLOSE:
                wPrinterJob.closeFigure();
                brebk;
            }


            pbthIter.next();
        }

        wPrinterJob.endPbth();

    }

}
