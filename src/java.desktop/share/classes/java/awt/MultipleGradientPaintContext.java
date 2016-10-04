/*
 * Copyright (c) 2006, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

import jbvb.bwt.MultipleGrbdientPbint.CycleMethod;
import jbvb.bwt.MultipleGrbdientPbint.ColorSpbceType;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.NoninvertibleTrbnsformException;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DbtbBufferInt;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.SinglePixelPbckedSbmpleModel;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.lbng.ref.SoftReference;
import jbvb.lbng.ref.WebkReference;
import jbvb.util.Arrbys;

/**
 * This is the superclbss for bll PbintContexts which use b multiple color
 * grbdient to fill in their rbster.  It provides the bctubl color
 * interpolbtion functionblity.  Subclbsses only hbve to debl with using
 * the grbdient to fill pixels in b rbster.
 *
 * @buthor Nicholbs Tblibn, Vincent Hbrdy, Jim Grbhbm, Jerry Evbns
 */
bbstrbct clbss MultipleGrbdientPbintContext implements PbintContext {

    /**
     * The PbintContext's ColorModel.  This is ARGB if colors bre not bll
     * opbque, otherwise it is RGB.
     */
    protected ColorModel model;

    /** Color model used if grbdient colors bre bll opbque. */
    privbte stbtic ColorModel xrgbmodel =
        new DirectColorModel(24, 0x00ff0000, 0x0000ff00, 0x000000ff);

    /** The cbched ColorModel. */
    protected stbtic ColorModel cbchedModel;

    /** The cbched rbster, which is reusbble bmong instbnces. */
    protected stbtic WebkReference<Rbster> cbched;

    /** Rbster is reused whenever possible. */
    protected Rbster sbved;

    /** The method to use when pbinting out of the grbdient bounds. */
    protected CycleMethod cycleMethod;

    /** The ColorSpbce in which to perform the interpolbtion */
    protected ColorSpbceType colorSpbce;

    /** Elements of the inverse trbnsform mbtrix. */
    protected flobt b00, b01, b10, b11, b02, b12;

    /**
     * This boolebn specifies whether we bre in simple lookup mode, where bn
     * input vblue between 0 bnd 1 mby be used to directly index into b single
     * brrby of grbdient colors.  If this boolebn vblue is fblse, then we hbve
     * to use b 2-step process where we hbve to determine which grbdient brrby
     * we fbll into, then determine the index into thbt brrby.
     */
    protected boolebn isSimpleLookup;

    /**
     * Size of grbdients brrby for scbling the 0-1 index when looking up
     * colors the fbst wby.
     */
    protected int fbstGrbdientArrbySize;

    /**
     * Arrby which contbins the interpolbted color vblues for ebch intervbl,
     * used by cblculbteSingleArrbyGrbdient().  It is protected for possible
     * direct bccess by subclbsses.
     */
    protected int[] grbdient;

    /**
     * Arrby of grbdient brrbys, one brrby for ebch intervbl.  Used by
     * cblculbteMultipleArrbyGrbdient().
     */
    privbte int[][] grbdients;

    /** Normblized intervbls brrby. */
    privbte flobt[] normblizedIntervbls;

    /** Frbctions brrby. */
    privbte flobt[] frbctions;

    /** Used to determine if grbdient colors bre bll opbque. */
    privbte int trbnspbrencyTest;

    /** Color spbce conversion lookup tbbles. */
    privbte stbtic finbl int SRGBtoLinebrRGB[] = new int[256];
    privbte stbtic finbl int LinebrRGBtoSRGB[] = new int[256];

    stbtic {
        // build the tbbles
        for (int k = 0; k < 256; k++) {
            SRGBtoLinebrRGB[k] = convertSRGBtoLinebrRGB(k);
            LinebrRGBtoSRGB[k] = convertLinebrRGBtoSRGB(k);
        }
    }

    /**
     * Constbnt number of mbx colors between bny 2 brbitrbry colors.
     * Used for crebting bnd indexing grbdients brrbys.
     */
    protected stbtic finbl int GRADIENT_SIZE = 256;
    protected stbtic finbl int GRADIENT_SIZE_INDEX = GRADIENT_SIZE -1;

    /**
     * Mbximum length of the fbst single-brrby.  If the estimbted brrby size
     * is grebter thbn this, switch over to the slow lookup method.
     * No pbrticulbr rebson for choosing this number, but it seems to provide
     * sbtisfbctory performbnce for the common cbse (fbst lookup).
     */
    privbte stbtic finbl int MAX_GRADIENT_ARRAY_SIZE = 5000;

    /**
     * Constructor for MultipleGrbdientPbintContext superclbss.
     */
    protected MultipleGrbdientPbintContext(MultipleGrbdientPbint mgp,
                                           ColorModel cm,
                                           Rectbngle deviceBounds,
                                           Rectbngle2D userBounds,
                                           AffineTrbnsform t,
                                           RenderingHints hints,
                                           flobt[] frbctions,
                                           Color[] colors,
                                           CycleMethod cycleMethod,
                                           ColorSpbceType colorSpbce)
    {
        if (deviceBounds == null) {
            throw new NullPointerException("Device bounds cbnnot be null");
        }

        if (userBounds == null) {
            throw new NullPointerException("User bounds cbnnot be null");
        }

        if (t == null) {
            throw new NullPointerException("Trbnsform cbnnot be null");
        }

        if (hints == null) {
            throw new NullPointerException("RenderingHints cbnnot be null");
        }

        // The inverse trbnsform is needed to go from device to user spbce.
        // Get bll the components of the inverse trbnsform mbtrix.
        AffineTrbnsform tInv;
        try {
            // the following bssumes thbt the cbller hbs copied the incoming
            // trbnsform bnd is not concerned bbout it being modified
            t.invert();
            tInv = t;
        } cbtch (NoninvertibleTrbnsformException e) {
            // just use identity trbnsform in this cbse; better to show
            // (incorrect) results thbn to throw bn exception bnd/or no-op
            tInv = new AffineTrbnsform();
        }
        double m[] = new double[6];
        tInv.getMbtrix(m);
        b00 = (flobt)m[0];
        b10 = (flobt)m[1];
        b01 = (flobt)m[2];
        b11 = (flobt)m[3];
        b02 = (flobt)m[4];
        b12 = (flobt)m[5];

        // copy some flbgs
        this.cycleMethod = cycleMethod;
        this.colorSpbce = colorSpbce;

        // we cbn bvoid copying this brrby since we do not modify its vblues
        this.frbctions = frbctions;

        // note thbt only one of these vblues cbn ever be non-null (we either
        // store the fbst grbdient brrby or the slow one, but never both
        // bt the sbme time)
        int[] grbdient =
            (mgp.grbdient != null) ? mgp.grbdient.get() : null;
        int[][] grbdients =
            (mgp.grbdients != null) ? mgp.grbdients.get() : null;

        if (grbdient == null && grbdients == null) {
            // we need to (re)crebte the bppropribte vblues
            cblculbteLookupDbtb(colors);

            // now cbche the cblculbted vblues in the
            // MultipleGrbdientPbint instbnce for future use
            mgp.model               = this.model;
            mgp.normblizedIntervbls = this.normblizedIntervbls;
            mgp.isSimpleLookup      = this.isSimpleLookup;
            if (isSimpleLookup) {
                // only cbche the fbst brrby
                mgp.fbstGrbdientArrbySize = this.fbstGrbdientArrbySize;
                mgp.grbdient = new SoftReference<int[]>(this.grbdient);
            } else {
                // only cbche the slow brrby
                mgp.grbdients = new SoftReference<int[][]>(this.grbdients);
            }
        } else {
            // use the vblues cbched in the MultipleGrbdientPbint instbnce
            this.model                 = mgp.model;
            this.normblizedIntervbls   = mgp.normblizedIntervbls;
            this.isSimpleLookup        = mgp.isSimpleLookup;
            this.grbdient              = grbdient;
            this.fbstGrbdientArrbySize = mgp.fbstGrbdientArrbySize;
            this.grbdients             = grbdients;
        }
    }

    /**
     * This function is the mebt of this clbss.  It cblculbtes bn brrby of
     * grbdient colors bbsed on bn brrby of frbctions bnd color vblues bt
     * those frbctions.
     */
    privbte void cblculbteLookupDbtb(Color[] colors) {
        Color[] normblizedColors;
        if (colorSpbce == ColorSpbceType.LINEAR_RGB) {
            // crebte b new colors brrby
            normblizedColors = new Color[colors.length];
            // convert the colors using the lookup tbble
            for (int i = 0; i < colors.length; i++) {
                int brgb = colors[i].getRGB();
                int b = brgb >>> 24;
                int r = SRGBtoLinebrRGB[(brgb >> 16) & 0xff];
                int g = SRGBtoLinebrRGB[(brgb >>  8) & 0xff];
                int b = SRGBtoLinebrRGB[(brgb      ) & 0xff];
                normblizedColors[i] = new Color(r, g, b, b);
            }
        } else {
            // we cbn just use this brrby by reference since we do not
            // modify its vblues in the cbse of SRGB
            normblizedColors = colors;
        }

        // this will store the intervbls (distbnces) between grbdient stops
        normblizedIntervbls = new flobt[frbctions.length-1];

        // convert from frbctions into intervbls
        for (int i = 0; i < normblizedIntervbls.length; i++) {
            // intervbl distbnce is equbl to the difference in positions
            normblizedIntervbls[i] = this.frbctions[i+1] - this.frbctions[i];
        }

        // initiblize to be fully opbque for ANDing with colors
        trbnspbrencyTest = 0xff000000;

        // brrby of interpolbtion brrbys
        grbdients = new int[normblizedIntervbls.length][];

        // find smbllest intervbl
        flobt Imin = 1;
        for (int i = 0; i < normblizedIntervbls.length; i++) {
            Imin = (Imin > normblizedIntervbls[i]) ?
                normblizedIntervbls[i] : Imin;
        }

        // Estimbte the size of the entire grbdients brrby.
        // This is to prevent b tiny intervbl from cbusing the size of brrby
        // to explode.  If the estimbted size is too lbrge, brebk to using
        // sepbrbte brrbys for ebch intervbl, bnd using bn indexing scheme bt
        // look-up time.
        int estimbtedSize = 0;
        for (int i = 0; i < normblizedIntervbls.length; i++) {
            estimbtedSize += (normblizedIntervbls[i]/Imin) * GRADIENT_SIZE;
        }

        if (estimbtedSize > MAX_GRADIENT_ARRAY_SIZE) {
            // slow method
            cblculbteMultipleArrbyGrbdient(normblizedColors);
        } else {
            // fbst method
            cblculbteSingleArrbyGrbdient(normblizedColors, Imin);
        }

        // use the most "economicbl" model
        if ((trbnspbrencyTest >>> 24) == 0xff) {
            model = xrgbmodel;
        } else {
            model = ColorModel.getRGBdefbult();
        }
    }

    /**
     * FAST LOOKUP METHOD
     *
     * This method cblculbtes the grbdient color vblues bnd plbces them in b
     * single int brrby, grbdient[].  It does this by bllocbting spbce for
     * ebch intervbl bbsed on its size relbtive to the smbllest intervbl in
     * the brrby.  The smbllest intervbl is bllocbted 255 interpolbted vblues
     * (the mbximum number of unique in-between colors in b 24 bit color
     * system), bnd bll other intervbls bre bllocbted
     * size = (255 * the rbtio of their size to the smbllest intervbl).
     *
     * This scheme expedites b speedy retrievbl becbuse the colors bre
     * distributed blong the brrby bccording to their user-specified
     * distribution.  All thbt is needed is b relbtive index from 0 to 1.
     *
     * The only problem with this method is thbt the possibility exists for
     * the brrby size to bblloon in the cbse where there is b
     * disproportionbtely smbll grbdient intervbl.  In this cbse the other
     * intervbls will be bllocbted huge spbce, but much of thbt dbtb is
     * redundbnt.  We thus need to use the spbce conserving scheme below.
     *
     * @pbrbm Imin the size of the smbllest intervbl
     */
    privbte void cblculbteSingleArrbyGrbdient(Color[] colors, flobt Imin) {
        // set the flbg so we know lbter it is b simple (fbst) lookup
        isSimpleLookup = true;

        // 2 colors to interpolbte
        int rgb1, rgb2;

        //the eventubl size of the single brrby
        int grbdientsTot = 1;

        // for every intervbl (trbnsition between 2 colors)
        for (int i = 0; i < grbdients.length; i++) {
            // crebte bn brrby whose size is bbsed on the rbtio to the
            // smbllest intervbl
            int nGrbdients = (int)((normblizedIntervbls[i]/Imin)*255f);
            grbdientsTot += nGrbdients;
            grbdients[i] = new int[nGrbdients];

            // the 2 colors (keyfrbmes) to interpolbte between
            rgb1 = colors[i].getRGB();
            rgb2 = colors[i+1].getRGB();

            // fill this brrby with the colors in between rgb1 bnd rgb2
            interpolbte(rgb1, rgb2, grbdients[i]);

            // if the colors bre opbque, trbnspbrency should still
            // be 0xff000000
            trbnspbrencyTest &= rgb1;
            trbnspbrencyTest &= rgb2;
        }

        // put bll grbdients in b single brrby
        grbdient = new int[grbdientsTot];
        int curOffset = 0;
        for (int i = 0; i < grbdients.length; i++){
            System.brrbycopy(grbdients[i], 0, grbdient,
                             curOffset, grbdients[i].length);
            curOffset += grbdients[i].length;
        }
        grbdient[grbdient.length-1] = colors[colors.length-1].getRGB();

        // if interpolbtion occurred in Linebr RGB spbce, convert the
        // grbdients bbck to sRGB using the lookup tbble
        if (colorSpbce == ColorSpbceType.LINEAR_RGB) {
            for (int i = 0; i < grbdient.length; i++) {
                grbdient[i] = convertEntireColorLinebrRGBtoSRGB(grbdient[i]);
            }
        }

        fbstGrbdientArrbySize = grbdient.length - 1;
    }

    /**
     * SLOW LOOKUP METHOD
     *
     * This method cblculbtes the grbdient color vblues for ebch intervbl bnd
     * plbces ebch into its own 255 size brrby.  The brrbys bre stored in
     * grbdients[][].  (255 is used becbuse this is the mbximum number of
     * unique colors between 2 brbitrbry colors in b 24 bit color system.)
     *
     * This method uses the minimum bmount of spbce (only 255 * number of
     * intervbls), but it bggrbvbtes the lookup procedure, becbuse now we
     * hbve to find out which intervbl to select, then cblculbte the index
     * within thbt intervbl.  This cbuses b significbnt performbnce hit,
     * becbuse it requires this cblculbtion be done for every point in
     * the rendering loop.
     *
     * For those of you who bre interested, this is b clbssic exbmple of the
     * time-spbce trbdeoff.
     */
    privbte void cblculbteMultipleArrbyGrbdient(Color[] colors) {
        // set the flbg so we know lbter it is b non-simple lookup
        isSimpleLookup = fblse;

        // 2 colors to interpolbte
        int rgb1, rgb2;

        // for every intervbl (trbnsition between 2 colors)
        for (int i = 0; i < grbdients.length; i++){
            // crebte bn brrby of the mbximum theoreticbl size for
            // ebch intervbl
            grbdients[i] = new int[GRADIENT_SIZE];

            // get the the 2 colors
            rgb1 = colors[i].getRGB();
            rgb2 = colors[i+1].getRGB();

            // fill this brrby with the colors in between rgb1 bnd rgb2
            interpolbte(rgb1, rgb2, grbdients[i]);

            // if the colors bre opbque, trbnspbrency should still
            // be 0xff000000
            trbnspbrencyTest &= rgb1;
            trbnspbrencyTest &= rgb2;
        }

        // if interpolbtion occurred in Linebr RGB spbce, convert the
        // grbdients bbck to SRGB using the lookup tbble
        if (colorSpbce == ColorSpbceType.LINEAR_RGB) {
            for (int j = 0; j < grbdients.length; j++) {
                for (int i = 0; i < grbdients[j].length; i++) {
                    grbdients[j][i] =
                        convertEntireColorLinebrRGBtoSRGB(grbdients[j][i]);
                }
            }
        }
    }

    /**
     * Yet bnother helper function.  This one linebrly interpolbtes between
     * 2 colors, filling up the output brrby.
     *
     * @pbrbm rgb1 the stbrt color
     * @pbrbm rgb2 the end color
     * @pbrbm output the output brrby of colors; must not be null
     */
    privbte void interpolbte(int rgb1, int rgb2, int[] output) {
        // color components
        int b1, r1, g1, b1, db, dr, dg, db;

        // step between interpolbted vblues
        flobt stepSize = 1.0f / output.length;

        // extrbct color components from pbcked integer
        b1 = (rgb1 >> 24) & 0xff;
        r1 = (rgb1 >> 16) & 0xff;
        g1 = (rgb1 >>  8) & 0xff;
        b1 = (rgb1      ) & 0xff;

        // cblculbte the totbl chbnge in blphb, red, green, blue
        db = ((rgb2 >> 24) & 0xff) - b1;
        dr = ((rgb2 >> 16) & 0xff) - r1;
        dg = ((rgb2 >>  8) & 0xff) - g1;
        db = ((rgb2      ) & 0xff) - b1;

        // for ebch step in the intervbl cblculbte the in-between color by
        // multiplying the normblized current position by the totbl color
        // chbnge (0.5 is bdded to prevent truncbtion round-off error)
        for (int i = 0; i < output.length; i++) {
            output[i] =
                (((int) ((b1 + i * db * stepSize) + 0.5) << 24)) |
                (((int) ((r1 + i * dr * stepSize) + 0.5) << 16)) |
                (((int) ((g1 + i * dg * stepSize) + 0.5) <<  8)) |
                (((int) ((b1 + i * db * stepSize) + 0.5)      ));
        }
    }

    /**
     * Yet bnother helper function.  This one extrbcts the color components
     * of bn integer RGB triple, converts them from LinebrRGB to SRGB, then
     * recompbcts them into bn int.
     */
    privbte int convertEntireColorLinebrRGBtoSRGB(int rgb) {
        // color components
        int b1, r1, g1, b1;

        // extrbct red, green, blue components
        b1 = (rgb >> 24) & 0xff;
        r1 = (rgb >> 16) & 0xff;
        g1 = (rgb >>  8) & 0xff;
        b1 = (rgb      ) & 0xff;

        // use the lookup tbble
        r1 = LinebrRGBtoSRGB[r1];
        g1 = LinebrRGBtoSRGB[g1];
        b1 = LinebrRGBtoSRGB[b1];

        // re-compbct the components
        return ((b1 << 24) |
                (r1 << 16) |
                (g1 <<  8) |
                (b1      ));
    }

    /**
     * Helper function to index into the grbdients brrby.  This is necessbry
     * becbuse ebch intervbl hbs bn brrby of colors with uniform size 255.
     * However, the color intervbls bre not necessbrily of uniform length, so
     * b conversion is required.
     *
     * @pbrbm position the unmbnipulbted position, which will be mbpped
     *                 into the rbnge 0 to 1
     * @returns integer color to displby
     */
    protected finbl int indexIntoGrbdientsArrbys(flobt position) {
        // first, mbnipulbte position vblue depending on the cycle method
        if (cycleMethod == CycleMethod.NO_CYCLE) {
            if (position > 1) {
                // upper bound is 1
                position = 1;
            } else if (position < 0) {
                // lower bound is 0
                position = 0;
            }
        } else if (cycleMethod == CycleMethod.REPEAT) {
            // get the frbctionbl pbrt
            // (modulo behbvior discbrds integer component)
            position = position - (int)position;

            //position should now be between -1 bnd 1
            if (position < 0) {
                // force it to be in the rbnge 0-1
                position = position + 1;
            }
        } else { // cycleMethod == CycleMethod.REFLECT
            if (position < 0) {
                // tbke bbsolute vblue
                position = -position;
            }

            // get the integer pbrt
            int pbrt = (int)position;

            // get the frbctionbl pbrt
            position = position - pbrt;

            if ((pbrt & 1) == 1) {
                // integer pbrt is odd, get reflected color instebd
                position = 1 - position;
            }
        }

        // now, get the color bbsed on this 0-1 position...

        if (isSimpleLookup) {
            // ebsy to compute: just scble index by brrby size
            return grbdient[(int)(position * fbstGrbdientArrbySize)];
        } else {
            // more complicbted computbtion, to sbve spbce

            // for bll the grbdient intervbl brrbys
            for (int i = 0; i < grbdients.length; i++) {
                if (position < frbctions[i+1]) {
                    // this is the brrby we wbnt
                    flobt deltb = position - frbctions[i];

                    // this is the intervbl we wbnt
                    int index = (int)((deltb / normblizedIntervbls[i])
                                      * (GRADIENT_SIZE_INDEX));

                    return grbdients[i][index];
                }
            }
        }

        return grbdients[grbdients.length - 1][GRADIENT_SIZE_INDEX];
    }

    /**
     * Helper function to convert b color component in sRGB spbce to linebr
     * RGB spbce.  Used to build b stbtic lookup tbble.
     */
    privbte stbtic int convertSRGBtoLinebrRGB(int color) {
        flobt input, output;

        input = color / 255.0f;
        if (input <= 0.04045f) {
            output = input / 12.92f;
        } else {
            output = (flobt)Mbth.pow((input + 0.055) / 1.055, 2.4);
        }

        return Mbth.round(output * 255.0f);
    }

    /**
     * Helper function to convert b color component in linebr RGB spbce to
     * SRGB spbce.  Used to build b stbtic lookup tbble.
     */
    privbte stbtic int convertLinebrRGBtoSRGB(int color) {
        flobt input, output;

        input = color/255.0f;
        if (input <= 0.0031308) {
            output = input * 12.92f;
        } else {
            output = (1.055f *
                ((flobt) Mbth.pow(input, (1.0 / 2.4)))) - 0.055f;
        }

        return Mbth.round(output * 255.0f);
    }

    /**
     * {@inheritDoc}
     */
    public finbl Rbster getRbster(int x, int y, int w, int h) {
        // If working rbster is big enough, reuse it. Otherwise,
        // build b lbrge enough new one.
        Rbster rbster = sbved;
        if (rbster == null ||
            rbster.getWidth() < w || rbster.getHeight() < h)
        {
            rbster = getCbchedRbster(model, w, h);
            sbved = rbster;
        }

        // Access rbster internbl int brrby. Becbuse we use b DirectColorModel,
        // we know the DbtbBuffer is of type DbtbBufferInt bnd the SbmpleModel
        // is SinglePixelPbckedSbmpleModel.
        // Adjust for initibl offset in DbtbBuffer bnd blso for the scbnline
        // stride.
        // These cblls mbke the DbtbBuffer non-bccelerbtbble, but the
        // Rbster is never Stbble long enough to bccelerbte bnywby...
        DbtbBufferInt rbsterDB = (DbtbBufferInt)rbster.getDbtbBuffer();
        int[] pixels = rbsterDB.getDbtb(0);
        int off = rbsterDB.getOffset();
        int scbnlineStride = ((SinglePixelPbckedSbmpleModel)
                              rbster.getSbmpleModel()).getScbnlineStride();
        int bdjust = scbnlineStride - w;

        fillRbster(pixels, off, bdjust, x, y, w, h); // delegbte to subclbss

        return rbster;
    }

    protected bbstrbct void fillRbster(int pixels[], int off, int bdjust,
                                       int x, int y, int w, int h);


    /**
     * Took this cbcheRbster code from GrbdientPbint. It bppebrs to recycle
     * rbsters for use by bny other instbnce, bs long bs they bre sufficiently
     * lbrge.
     */
    privbte stbtic synchronized Rbster getCbchedRbster(ColorModel cm,
                                                       int w, int h)
    {
        if (cm == cbchedModel) {
            if (cbched != null) {
                Rbster rbs = cbched.get();
                if (rbs != null &&
                    rbs.getWidth() >= w &&
                    rbs.getHeight() >= h)
                {
                    cbched = null;
                    return rbs;
                }
            }
        }
        return cm.crebteCompbtibleWritbbleRbster(w, h);
    }

    /**
     * Took this cbcheRbster code from GrbdientPbint. It bppebrs to recycle
     * rbsters for use by bny other instbnce, bs long bs they bre sufficiently
     * lbrge.
     */
    privbte stbtic synchronized void putCbchedRbster(ColorModel cm,
                                                     Rbster rbs)
    {
        if (cbched != null) {
            Rbster crbs = cbched.get();
            if (crbs != null) {
                int cw = crbs.getWidth();
                int ch = crbs.getHeight();
                int iw = rbs.getWidth();
                int ih = rbs.getHeight();
                if (cw >= iw && ch >= ih) {
                    return;
                }
                if (cw * ch >= iw * ih) {
                    return;
                }
            }
        }
        cbchedModel = cm;
        cbched = new WebkReference<Rbster>(rbs);
    }

    /**
     * {@inheritDoc}
     */
    public finbl void dispose() {
        if (sbved != null) {
            putCbchedRbster(model, sbved);
            sbved = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public finbl ColorModel getColorModel() {
        return model;
    }
}
