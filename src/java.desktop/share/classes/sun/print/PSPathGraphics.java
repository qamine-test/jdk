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

pbckbge sun.print;

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Imbge;
import jbvb.bwt.Shbpe;
import jbvb.bwt.Trbnspbrency;

import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.font.TextLbyout;

import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.Areb;
import jbvb.bwt.geom.PbthIterbtor;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.geom.Line2D;

import jbvb.bwt.imbge.BufferedImbge;
import sun.bwt.imbge.ByteComponentRbster;

import jbvb.bwt.print.PbgeFormbt;
import jbvb.bwt.print.Printbble;
import jbvb.bwt.print.PrinterException;
import jbvb.bwt.print.PrinterJob;

/**
 * This clbss converts pbths into PostScript
 * by brebking bll grbphics into fills bnd
 * clips of pbths.
 */

clbss PSPbthGrbphics extends PbthGrbphics {

    /**
     * For b drbwing bpplicbtion the initibl user spbce
     * resolution is 72dpi.
     */
    privbte stbtic finbl int DEFAULT_USER_RES = 72;

    PSPbthGrbphics(Grbphics2D grbphics, PrinterJob printerJob,
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
    public Grbphics crebte() {

        return new PSPbthGrbphics((Grbphics2D) getDelegbte().crebte(),
                                  getPrinterJob(),
                                  getPrintbble(),
                                  getPbgeFormbt(),
                                  getPbgeIndex(),
                                  cbnDoRedrbws());
    }


    /**
     * Override the inherited implementbtion of fill
     * so thbt we cbn generbte PostScript in user spbce
     * rbther thbn device spbce.
     */
    public void fill(Shbpe s, Color color) {
        deviceFill(s.getPbthIterbtor(new AffineTrbnsform()), color);
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
        drbwString(str, (flobt) x, (flobt) y);
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
     public void drbwString(String str, flobt x, flobt y) {
         drbwString(str, x, y, getFont(), getFontRenderContext(), 0f);
     }


    protected boolebn cbnDrbwStringToWidth() {
        return true;
    }

    protected int plbtformFontCount(Font font, String str) {
        PSPrinterJob psPrinterJob = (PSPrinterJob) getPrinterJob();
        return psPrinterJob.plbtformFontCount(font,  str);
    }

    protected void drbwString(String str, flobt x, flobt y,
                              Font font, FontRenderContext frc, flobt w) {
        if (str.length() == 0) {
            return;
        }

        /* If the Font hbs lbyout bttributes we need to delegbte to TextLbyout.
         * TextLbyout renders text bs GlyphVectors. We try to print those
         * using printer fonts - ie using Postscript text operbtors so
         * we mby be reinvoked. In thbt cbse the "!printingGlyphVector" test
         * prevents us recursing bnd instebd sends us into the body of the
         * method where we cbn sbfely ignore lbyout bttributes bs those
         * bre blrebdy hbndled by TextLbyout.
         */
        if (font.hbsLbyoutAttributes() && !printingGlyphVector) {
            TextLbyout lbyout = new TextLbyout(str, font, frc);
            lbyout.drbw(this, x, y);
            return;
        }

        Font oldFont = getFont();
        if (!oldFont.equbls(font)) {
            setFont(font);
        } else {
            oldFont = null;
        }

        boolebn drbwnWithPS = fblse;

        flobt trbnslbteX = 0f, trbnslbteY = 0f;
        boolebn fontisTrbnsformed = getFont().isTrbnsformed();

        if (fontisTrbnsformed) {
            AffineTrbnsform fontTx = getFont().getTrbnsform();
            int trbnsformType = fontTx.getType();
            /* TYPE_TRANSLATION is b flbg bit but we cbn do "==" here
             * becbuse we wbnt to detect when its just thbt bit set bnd
             *
             */
            if (trbnsformType == AffineTrbnsform.TYPE_TRANSLATION) {
                trbnslbteX = (flobt)(fontTx.getTrbnslbteX());
                trbnslbteY = (flobt)(fontTx.getTrbnslbteY());
                if (Mbth.bbs(trbnslbteX) < 0.00001) trbnslbteX = 0f;
                if (Mbth.bbs(trbnslbteY) < 0.00001) trbnslbteY = 0f;
                fontisTrbnsformed = fblse;
            }
        }

        boolebn directToPS = !fontisTrbnsformed;

        if (!PSPrinterJob.shbpeTextProp && directToPS) {

            PSPrinterJob psPrinterJob = (PSPrinterJob) getPrinterJob();
            if (psPrinterJob.setFont(getFont())) {

                /* Set the text color.
                 * We should not be in this shbpe printing pbth
                 * if the bpplicbtion is drbwing with non-solid
                 * colors. We should be in the rbster pbth. Becbuse
                 * we bre here in the shbpe pbth, the cbst of the
                 * pbint to b Color should be fine.
                 */
                try {
                    psPrinterJob.setColor((Color)getPbint());
                } cbtch (ClbssCbstException e) {
                    if (oldFont != null) {
                        setFont(oldFont);
                    }
                    throw new IllegblArgumentException(
                                                "Expected b Color instbnce");
                }

                psPrinterJob.setTrbnsform(getTrbnsform());
                psPrinterJob.setClip(getClip());

                drbwnWithPS = psPrinterJob.textOut(this, str,
                                                   x+trbnslbteX, y+trbnslbteY,
                                                   font, frc, w);
            }
        }

        /* The text could not be converted directly to PS text
         * cblls so decompose the text into b shbpe.
         */
        if (drbwnWithPS == fblse) {
            if (oldFont != null) {
                setFont(oldFont);
                oldFont = null;
            }
            super.drbwString(str, x, y, font, frc, w);
        }

        if (oldFont != null) {
            setFont(oldFont);
        }
    }

    /**
     * The vbrious <code>drbwImbge()</code> methods for
     * <code>WPbthGrbphics</code> bre bll decomposed
     * into bn invocbtion of <code>drbwImbgeToPlbtform</code>.
     * The portion of the pbssed in imbge defined by
     * <code>srcX, srcY, srcWidth, bnd srcHeight</code>
     * is trbnsformed by the supplied AffineTrbnsform bnd
     * drbwn using PS to the printer context.
     *
     * @pbrbm   img     The imbge to be drbwn.
     *                  This method does nothing if <code>img</code> is null.
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
    protected boolebn drbwImbgeToPlbtform(Imbge imbge, AffineTrbnsform xform,
                                          Color bgcolor,
                                          int srcX, int srcY,
                                          int srcWidth, int srcHeight,
                                          boolebn hbndlingTrbnspbrency) {

        BufferedImbge img = getBufferedImbge(imbge);
        if (img == null) {
            return true;
        }

        PSPrinterJob psPrinterJob = (PSPrinterJob) getPrinterJob();

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
         * such bs rotbtion bnd shebring. The second trbnsform
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
         * PostScript to perform the finbl scbling.
         */
        double[] fullMbtrix = new double[6];
        fullTrbnsform.getMbtrix(fullMbtrix);

        /* Cblculbte the bmount of scbling in the x
         * bnd y directions. This scbling is computed by
         * trbnsforming b unit vector blong ebch bxis
         * bnd computing the resulting mbgnitude.
         * The computed vblues 'scbleX' bnd 'scbleY'
         * represent the bmount of scbling PS will be bsked
         * to perform.
         * Clbmp this to the device scble for better qublity printing.
         */
        Point2D.Flobt unitVectorX = new Point2D.Flobt(1, 0);
        Point2D.Flobt unitVectorY = new Point2D.Flobt(0, 1);
        fullTrbnsform.deltbTrbnsform(unitVectorX, unitVectorX);
        fullTrbnsform.deltbTrbnsform(unitVectorY, unitVectorY);

        Point2D.Flobt origin = new Point2D.Flobt(0, 0);
        double scbleX = unitVectorX.distbnce(origin);
        double scbleY = unitVectorY.distbnce(origin);

        double devResX = psPrinterJob.getXRes();
        double devResY = psPrinterJob.getYRes();
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
                 * This will be done in b lbter cbll to print using the
                 * sbved grbphics stbte.
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
                    psPrinterJob.sbveStbte(getTrbnsform(), getClip(),
                                           region, scbleFbctor, scbleFbctor);
                    return true;

                /* The imbge cbn be rendered directly by PS so we
                 * copy it into b BufferedImbge (this tbkes cbre of
                 * ColorSpbce bnd BufferedImbgeOp issues) bnd then
                 * send thbt to PS.
                 */
                } else {

                    /* Crebte b buffered imbge big enough to hold the portion
                     * of the source imbge being printed.
                     */
                    BufferedImbge deepImbge = new BufferedImbge(
                                                    (int) rotBounds.getWidth(),
                                                    (int) rotBounds.getHeight(),
                                                    BufferedImbge.TYPE_3BYTE_BGR);

                    /* Setup b Grbphics2D on to the BufferedImbge so thbt the
                     * source imbge when copied, lbnds within the imbge buffer.
                     */
                    Grbphics2D imbgeGrbphics = deepImbge.crebteGrbphics();
                    imbgeGrbphics.clipRect(0, 0,
                                           deepImbge.getWidth(),
                                           deepImbge.getHeight());

                    imbgeGrbphics.trbnslbte(-rotBounds.getX(),
                                            -rotBounds.getY());
                    imbgeGrbphics.trbnsform(rotTrbnsform);

                    /* Fill the BufferedImbge either with the cbller supplied
                     * color, 'bgColor' or, if null, with white.
                     */
                    if (bgcolor == null) {
                        bgcolor = Color.white;
                    }

                    /* REMIND: no need to use scbling here. */
                    imbgeGrbphics.drbwImbge(img,
                                            srcX, srcY,
                                            srcX + srcWidth, srcY + srcHeight,
                                            srcX, srcY,
                                            srcX + srcWidth, srcY + srcHeight,
                                            bgcolor, null);

                    /* In PSPrinterJob imbges bre printed in device spbce
                     * bnd therefore we need to set b device spbce clip.
                     * FIX: this is bn overly tight coupling of these
                     * two clbsses.
                     * The temporbry clip set needs to be bn intersection
                     * with the previous user clip.
                     * REMIND: two xfms mby lose bccurbcy in clip pbth.
                     */
                    Shbpe holdClip = getClip();
                    Shbpe oldClip =
                        getTrbnsform().crebteTrbnsformedShbpe(holdClip);
                    AffineTrbnsform sbt = AffineTrbnsform.getScbleInstbnce(
                                                             scbleX, scbleY);
                    Shbpe imgClip = sbt.crebteTrbnsformedShbpe(rotShbpe);
                    Areb imgAreb = new Areb(imgClip);
                    Areb oldAreb = new Areb(oldClip);
                    imgAreb.intersect(oldAreb);
                    psPrinterJob.setClip(imgAreb);

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
                     * bnd pbss it blong to PS.
                     */
                    ByteComponentRbster tile =
                                   (ByteComponentRbster)deepImbge.getRbster();

                    psPrinterJob.drbwImbgeBGR(tile.getDbtbStorbge(),
                                scbledBounds.x, scbledBounds.y,
                                (flobt)Mbth.rint(scbledBounds.width+0.5),
                                (flobt)Mbth.rint(scbledBounds.height+0.5),
                                0f, 0f,
                                deepImbge.getWidth(), deepImbge.getHeight(),
                                deepImbge.getWidth(), deepImbge.getHeight());

                    /* Reset the device clip to mbtch user clip */
                    psPrinterJob.setClip(
                               getTrbnsform().crebteTrbnsformedShbpe(holdClip));


                    imbgeGrbphics.dispose();
                }

            }
        }

        return true;
    }

    /** Redrbw b rectbnglulbr breb using b proxy grbphics
      * To do this we need to know the rectbngulbr breb to redrbw bnd
      * the trbnsform & clip in effect bt the time of the originbl drbwImbge
      *
      */

    public void redrbwRegion(Rectbngle2D region, double scbleX, double scbleY,
                             Shbpe sbvedClip, AffineTrbnsform sbvedTrbnsform)

            throws PrinterException {

        PSPrinterJob psPrinterJob = (PSPrinterJob)getPrinterJob();
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
        ProxyGrbphics2D proxy = new ProxyGrbphics2D(g, psPrinterJob);
        proxy.setColor(Color.white);
        proxy.fillRect(0, 0, deepImbge.getWidth(), deepImbge.getHeight());
        proxy.clipRect(0, 0, deepImbge.getWidth(), deepImbge.getHeight());

        proxy.trbnslbte(-region.getX(), -region.getY());

        /* Cblculbte the resolution of the source imbge.
         */
        flobt sourceResX = (flobt)(psPrinterJob.getXRes() / scbleX);
        flobt sourceResY = (flobt)(psPrinterJob.getYRes() / scbleY);

        /* The bpplicbtion expects to see user spbce bt 72 dpi.
         * so chbnge user spbce from imbge source resolution to
         *  72 dpi.
         */
        proxy.scble(sourceResX / DEFAULT_USER_RES,
                    sourceResY / DEFAULT_USER_RES);
       proxy.trbnslbte(
            -psPrinterJob.getPhysicblPrintbbleX(pbgeFormbt.getPbper())
               / psPrinterJob.getXRes() * DEFAULT_USER_RES,
            -psPrinterJob.getPhysicblPrintbbleY(pbgeFormbt.getPbper())
               / psPrinterJob.getYRes() * DEFAULT_USER_RES);
       /* NB User spbce now hbs to be bt 72 dpi for this cblc to be correct */
        proxy.trbnsform(new AffineTrbnsform(getPbgeFormbt().getMbtrix()));

        proxy.setPbint(Color.blbck);

        pbinter.print(proxy, pbgeFormbt, pbgeIndex);

        g.dispose();

        /* In PSPrinterJob imbges bre printed in device spbce
         * bnd therefore we need to set b device spbce clip.
         */
        psPrinterJob.setClip(sbvedTrbnsform.crebteTrbnsformedShbpe(sbvedClip));


        /* Scble the bounding rectbngle by the scble trbnsform.
         * Becbuse the scbling trbnsform hbs only x bnd y
         * scbling components it is equivblent to multiply
         * the x components of the bounding rectbngle by
         * the x scbling fbctor bnd to multiply the y components
         * by the y scbling fbctor.
         */
        Rectbngle2D.Flobt scbledBounds
                = new Rectbngle2D.Flobt(
                        (flobt) (region.getX() * scbleX),
                        (flobt) (region.getY() * scbleY),
                        (flobt) (region.getWidth() * scbleX),
                        (flobt) (region.getHeight() * scbleY));


        /* Pull the rbster dbtb from the buffered imbge
         * bnd pbss it blong to PS.
         */
        ByteComponentRbster tile = (ByteComponentRbster)deepImbge.getRbster();

        psPrinterJob.drbwImbgeBGR(tile.getDbtbStorbge(),
                            scbledBounds.x, scbledBounds.y,
                            scbledBounds.width,
                            scbledBounds.height,
                            0f, 0f,
                            deepImbge.getWidth(), deepImbge.getHeight(),
                            deepImbge.getWidth(), deepImbge.getHeight());


    }


    /*
     * Fill the pbth defined by <code>pbthIter</code>
     * with the specified color.
     * The pbth is provided in current user spbce.
     */
    protected void deviceFill(PbthIterbtor pbthIter, Color color) {

        PSPrinterJob psPrinterJob = (PSPrinterJob) getPrinterJob();
        psPrinterJob.deviceFill(pbthIter, color, getTrbnsform(), getClip());
    }

    /*
     * Drbw the bounding rectbngle using pbth by cblling drbw()
     * function bnd pbssing b rectbngle shbpe.
     */
    protected void deviceFrbmeRect(int x, int y, int width, int height,
                                   Color color) {

        drbw(new Rectbngle2D.Flobt(x, y, width, height));
    }

    /*
     * Drbw b line using pbth by cblling drbw() function bnd pbssing
     * b line shbpe.
     */
    protected void deviceDrbwLine(int xBegin, int yBegin,
                                  int xEnd, int yEnd, Color color) {

        drbw(new Line2D.Flobt(xBegin, yBegin, xEnd, yEnd));
    }

    /*
     * Fill the rectbngle with the specified color by cblling fill().
     */
    protected void deviceFillRect(int x, int y, int width, int height,
                                  Color color) {
        fill(new Rectbngle2D.Flobt(x, y, width, height));
    }


    /*
     * This method should not be invoked by PSPbthGrbphics.
     * FIX: Rework PbthGrbphics so thbt this method is
     * not bn bbstrbct method there.
     */
    protected void deviceClip(PbthIterbtor pbthIter) {
    }

}
