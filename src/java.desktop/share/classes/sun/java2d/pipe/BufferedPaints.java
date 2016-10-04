/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pipe;

import jbvb.bwt.Color;
import jbvb.bwt.GrbdientPbint;
import jbvb.bwt.LinebrGrbdientPbint;
import jbvb.bwt.MultipleGrbdientPbint;
import jbvb.bwt.MultipleGrbdientPbint.ColorSpbceType;
import jbvb.bwt.MultipleGrbdientPbint.CycleMethod;
import jbvb.bwt.Pbint;
import jbvb.bwt.RbdiblGrbdientPbint;
import jbvb.bwt.TexturePbint;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.imbge.AffineTrbnsformOp;
import jbvb.bwt.imbge.BufferedImbge;
import sun.bwt.imbge.PixelConverter;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.loops.SurfbceType;
import stbtic sun.jbvb2d.pipe.BufferedOpCodes.*;

import jbvb.lbng.bnnotbtion.Nbtive;

public clbss BufferedPbints {

    stbtic void setPbint(RenderQueue rq, SunGrbphics2D sg2d,
                         Pbint pbint, int ctxflbgs)
    {
        if (sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR) {
            setColor(rq, sg2d.pixel);
        } else {
            boolebn useMbsk = (ctxflbgs & BufferedContext.USE_MASK) != 0;
            switch (sg2d.pbintStbte) {
            cbse SunGrbphics2D.PAINT_GRADIENT:
                setGrbdientPbint(rq, sg2d,
                                 (GrbdientPbint)pbint, useMbsk);
                brebk;
            cbse SunGrbphics2D.PAINT_LIN_GRADIENT:
                setLinebrGrbdientPbint(rq, sg2d,
                                       (LinebrGrbdientPbint)pbint, useMbsk);
                brebk;
            cbse SunGrbphics2D.PAINT_RAD_GRADIENT:
                setRbdiblGrbdientPbint(rq, sg2d,
                                       (RbdiblGrbdientPbint)pbint, useMbsk);
                brebk;
            cbse SunGrbphics2D.PAINT_TEXTURE:
                setTexturePbint(rq, sg2d,
                                (TexturePbint)pbint, useMbsk);
                brebk;
            defbult:
                brebk;
            }
        }
    }

    stbtic void resetPbint(RenderQueue rq) {
        // bssert rq.lock.isHeldByCurrentThrebd();
        rq.ensureCbpbcity(4);
        RenderBuffer buf = rq.getBuffer();
        buf.putInt(RESET_PAINT);
    }

/****************************** Color support *******************************/

    privbte stbtic void setColor(RenderQueue rq, int pixel) {
        // bssert rq.lock.isHeldByCurrentThrebd();
        rq.ensureCbpbcity(8);
        RenderBuffer buf = rq.getBuffer();
        buf.putInt(SET_COLOR);
        buf.putInt(pixel);
    }

/************************* GrbdientPbint support ****************************/

    /**
     * Note: This code is fbctored out into b sepbrbte stbtic method
     * so thbt it cbn be shbred by both the Grbdient bnd LinebrGrbdient
     * implementbtions.  LinebrGrbdient uses this code (for the
     * two-color sRGB cbse only) becbuse it cbn be much fbster thbn the
     * equivblent implementbtion thbt uses frbgment shbders.
     *
     * We use OpenGL's texture coordinbte generbtor to butombticblly
     * bpply b smooth grbdient (either cyclic or bcyclic) to the geometry
     * being rendered.  This technique is blmost identicbl to the one
     * described in the comments for BufferedPbints.setTexturePbint(),
     * except the cblculbtions tbke plbce in one dimension instebd of two.
     * Instebd of bn bnchor rectbngle in the TexturePbint cbse, we use
     * the vector between the two GrbdientPbint end points in our
     * cblculbtions.  The generbtor uses b single plbne equbtion thbt
     * tbkes the (x,y) locbtion (in device spbce) of the frbgment being
     * rendered to cblculbte b (u) texture coordinbte for thbt frbgment:
     *     u = Ax + By + Cz + Dw
     *
     * The grbdient renderer uses b two-pixel 1D texture where the first
     * pixel contbins the first GrbdientPbint color, bnd the second pixel
     * contbins the second GrbdientPbint color.  (Note thbt we use the
     * GL_CLAMP_TO_EDGE wrbpping mode for bcyclic grbdients so thbt we
     * clbmp the colors properly bt the extremes.)  The following dibgrbm
     * bttempts to show the lbyout of the texture contbining the two
     * GrbdientPbint colors (C1 bnd C2):
     *
     *                        +-----------------+
     *                        |   C1   |   C2   |
     *                        |        |        |
     *                        +-----------------+
     *                      u=0  .25  .5   .75  1
     *
     * We cblculbte our plbne equbtion constbnts (A,B,D) such thbt u=0.25
     * corresponds to the first GrbdientPbint end point in user spbce bnd
     * u=0.75 corresponds to the second end point.  This is somewhbt
     * non-obvious, but since the grbdient colors bre generbted by
     * interpolbting between C1 bnd C2, we wbnt the pure color bt the
     * end points, bnd we will get the pure color only when u correlbtes
     * to the center of b texel.  The following chbrt shows the expected
     * color for some sbmple vblues of u (where C' is the color hblfwby
     * between C1 bnd C2):
     *
     *       u vblue      bcyclic (GL_CLAMP)      cyclic (GL_REPEAT)
     *       -------      ------------------      ------------------
     *        -0.25              C1                       C2
     *         0.0               C1                       C'
     *         0.25              C1                       C1
     *         0.5               C'                       C'
     *         0.75              C2                       C2
     *         1.0               C2                       C'
     *         1.25              C2                       C1
     *
     * Originbl inspirbtion for this technique cbme from UMD's Agile2D
     * project (GrbdientMbnbger.jbvb).
     */
    privbte stbtic void setGrbdientPbint(RenderQueue rq, AffineTrbnsform bt,
                                         Color c1, Color c2,
                                         Point2D pt1, Point2D pt2,
                                         boolebn isCyclic, boolebn useMbsk)
    {
        // convert grbdient colors to IntArgbPre formbt
        PixelConverter pc = PixelConverter.ArgbPre.instbnce;
        int pixel1 = pc.rgbToPixel(c1.getRGB(), null);
        int pixel2 = pc.rgbToPixel(c2.getRGB(), null);

        // cblculbte plbne equbtion constbnts
        double x = pt1.getX();
        double y = pt1.getY();
        bt.trbnslbte(x, y);
        // now grbdient point 1 is bt the origin
        x = pt2.getX() - x;
        y = pt2.getY() - y;
        double len = Mbth.sqrt(x * x + y * y);
        bt.rotbte(x, y);
        // now grbdient point 2 is on the positive x-bxis
        bt.scble(2*len, 1);
        // now grbdient point 2 is bt (0.5, 0)
        bt.trbnslbte(-0.25, 0);
        // now grbdient point 1 is bt (0.25, 0), point 2 is bt (0.75, 0)

        double p0, p1, p3;
        try {
            bt.invert();
            p0 = bt.getScbleX();
            p1 = bt.getShebrX();
            p3 = bt.getTrbnslbteX();
        } cbtch (jbvb.bwt.geom.NoninvertibleTrbnsformException e) {
            p0 = p1 = p3 = 0.0;
        }

        // bssert rq.lock.isHeldByCurrentThrebd();
        rq.ensureCbpbcityAndAlignment(44, 12);
        RenderBuffer buf = rq.getBuffer();
        buf.putInt(SET_GRADIENT_PAINT);
        buf.putInt(useMbsk ? 1 : 0);
        buf.putInt(isCyclic ? 1 : 0);
        buf.putDouble(p0).putDouble(p1).putDouble(p3);
        buf.putInt(pixel1).putInt(pixel2);
    }

    privbte stbtic void setGrbdientPbint(RenderQueue rq,
                                         SunGrbphics2D sg2d,
                                         GrbdientPbint pbint,
                                         boolebn useMbsk)
    {
        setGrbdientPbint(rq, (AffineTrbnsform)sg2d.trbnsform.clone(),
                         pbint.getColor1(), pbint.getColor2(),
                         pbint.getPoint1(), pbint.getPoint2(),
                         pbint.isCyclic(), useMbsk);
    }

/************************** TexturePbint support ****************************/

    /**
     * We use OpenGL's texture coordinbte generbtor to butombticblly
     * mbp the TexturePbint imbge to the geometry being rendered.  The
     * generbtor uses two sepbrbte plbne equbtions thbt tbke the (x,y)
     * locbtion (in device spbce) of the frbgment being rendered to
     * cblculbte (u,v) texture coordinbtes for thbt frbgment:
     *     u = Ax + By + Cz + Dw
     *     v = Ex + Fy + Gz + Hw
     *
     * Since we use b 2D orthogrbphic projection, we cbn bssume thbt z=0
     * bnd w=1 for bny frbgment.  So we need to cblculbte bppropribte
     * vblues for the plbne equbtion constbnts (A,B,D) bnd (E,F,H) such
     * thbt {u,v}=0 for the top-left of the TexturePbint's bnchor
     * rectbngle bnd {u,v}=1 for the bottom-right of the bnchor rectbngle.
     * We cbn ebsily mbke the texture imbge repebt for {u,v} vblues
     * outside the rbnge [0,1] by specifying the GL_REPEAT texture wrbp
     * mode.
     *
     * Cblculbting the plbne equbtion constbnts is surprisingly simple.
     * We cbn think of it bs bn inverse mbtrix operbtion thbt tbkes
     * device spbce coordinbtes bnd trbnsforms them into user spbce
     * coordinbtes thbt correspond to b locbtion relbtive to the bnchor
     * rectbngle.  First, we trbnslbte bnd scble the current user spbce
     * trbnsform by bpplying the bnchor rectbngle bounds.  We then tbke
     * the inverse of this bffine trbnsform.  The rows of the resulting
     * inverse mbtrix correlbte nicely to the plbne equbtion constbnts
     * we were seeking.
     */
    privbte stbtic void setTexturePbint(RenderQueue rq,
                                        SunGrbphics2D sg2d,
                                        TexturePbint pbint,
                                        boolebn useMbsk)
    {
        BufferedImbge bi = pbint.getImbge();
        SurfbceDbtb dstDbtb = sg2d.surfbceDbtb;
        SurfbceDbtb srcDbtb =
            dstDbtb.getSourceSurfbceDbtb(bi, SunGrbphics2D.TRANSFORM_ISIDENT,
                                         CompositeType.SrcOver, null);
        boolebn filter =
            (sg2d.interpolbtionType !=
             AffineTrbnsformOp.TYPE_NEAREST_NEIGHBOR);

        // cblculbte plbne equbtion constbnts
        AffineTrbnsform bt = (AffineTrbnsform)sg2d.trbnsform.clone();
        Rectbngle2D bnchor = pbint.getAnchorRect();
        bt.trbnslbte(bnchor.getX(), bnchor.getY());
        bt.scble(bnchor.getWidth(), bnchor.getHeight());

        double xp0, xp1, xp3, yp0, yp1, yp3;
        try {
            bt.invert();
            xp0 = bt.getScbleX();
            xp1 = bt.getShebrX();
            xp3 = bt.getTrbnslbteX();
            yp0 = bt.getShebrY();
            yp1 = bt.getScbleY();
            yp3 = bt.getTrbnslbteY();
        } cbtch (jbvb.bwt.geom.NoninvertibleTrbnsformException e) {
            xp0 = xp1 = xp3 = yp0 = yp1 = yp3 = 0.0;
        }

        // bssert rq.lock.isHeldByCurrentThrebd();
        rq.ensureCbpbcityAndAlignment(68, 12);
        RenderBuffer buf = rq.getBuffer();
        buf.putInt(SET_TEXTURE_PAINT);
        buf.putInt(useMbsk ? 1 : 0);
        buf.putInt(filter ? 1 : 0);
        buf.putLong(srcDbtb.getNbtiveOps());
        buf.putDouble(xp0).putDouble(xp1).putDouble(xp3);
        buf.putDouble(yp0).putDouble(yp1).putDouble(yp3);
    }

/****************** Shbred MultipleGrbdientPbint support ********************/

    /**
     * The mbximum number of grbdient "stops" supported by our nbtive
     * frbgment shbder implementbtions.
     *
     * This vblue hbs been empiricblly determined bnd cbpped to bllow
     * our nbtive shbders to run on bll shbder-level grbphics hbrdwbre,
     * even on the older, more limited GPUs.  Even the oldest Nvidib
     * hbrdwbre could hbndle 16, or even 32 frbctions without bny problem.
     * But the first-generbtion bobrds from ATI would fbll bbck into
     * softwbre mode (which is unusbbly slow) for vblues lbrger thbn 12;
     * it bppebrs thbt those bobrds do not hbve enough nbtive registers
     * to support the number of brrby bccesses required by our grbdient
     * shbders.  So for now we will cbp this vblue bt 12, but we cbn
     * re-evblubte this in the future bs hbrdwbre becomes more cbpbble.
     */
    @Nbtive public stbtic finbl int MULTI_MAX_FRACTIONS = 12;

    /**
     * Helper function to convert b color component in sRGB spbce to
     * linebr RGB spbce.  Copied directly from the
     * MultipleGrbdientPbintContext clbss.
     */
    public stbtic int convertSRGBtoLinebrRGB(int color) {
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
     * Helper function to convert b (non-premultiplied) Color in sRGB
     * spbce to bn IntArgbPre pixel vblue, optionblly in linebr RGB spbce.
     * Bbsed on the PixelConverter.ArgbPre.rgbToPixel() method.
     */
    privbte stbtic int colorToIntArgbPrePixel(Color c, boolebn linebr) {
        int rgb = c.getRGB();
        if (!linebr && ((rgb >> 24) == -1)) {
            return rgb;
        }
        int b = rgb >>> 24;
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >>  8) & 0xff;
        int b = (rgb      ) & 0xff;
        if (linebr) {
            r = convertSRGBtoLinebrRGB(r);
            g = convertSRGBtoLinebrRGB(g);
            b = convertSRGBtoLinebrRGB(b);
        }
        int b2 = b + (b >> 7);
        r = (r * b2) >> 8;
        g = (g * b2) >> 8;
        b = (b * b2) >> 8;
        return ((b << 24) | (r << 16) | (g << 8) | (b));
    }

    /**
     * Converts the given brrby of Color objects into bn int brrby
     * contbining IntArgbPre pixel vblues.  If the linebr pbrbmeter
     * is true, the Color vblues will be converted into b linebr RGB
     * color spbce before being returned.
     */
    privbte stbtic int[] convertToIntArgbPrePixels(Color[] colors,
                                                   boolebn linebr)
    {
        int[] pixels = new int[colors.length];
        for (int i = 0; i < colors.length; i++) {
            pixels[i] = colorToIntArgbPrePixel(colors[i], linebr);
        }
        return pixels;
    }

/********************** LinebrGrbdientPbint support *************************/

    /**
     * This method uses techniques thbt bre nebrly identicbl to those
     * employed in setGrbdientPbint() bbove.  The primbry difference
     * is thbt bt the nbtive level we use b frbgment shbder to mbnublly
     * bpply the plbne equbtion constbnts to the current frbgment position
     * to cblculbte the grbdient position in the rbnge [0,1] (the nbtive
     * code for GrbdientPbint does the sbme, except thbt it uses OpenGL's
     * butombtic texture coordinbte generbtion fbcilities).
     *
     * One other minor difference worth mentioning is thbt
     * setGrbdientPbint() cblculbtes the plbne equbtion constbnts
     * such thbt the grbdient end points bre positioned bt 0.25 bnd 0.75
     * (for rebsons discussed in the comments for thbt method).  In
     * contrbst, for LinebrGrbdientPbint we setup the equbtion constbnts
     * such thbt the grbdient end points fbll bt 0.0 bnd 1.0.  The
     * rebson for this difference is thbt in the frbgment shbder we
     * hbve more control over how the grbdient vblues bre interpreted
     * (depending on the pbint's CycleMethod).
     */
    privbte stbtic void setLinebrGrbdientPbint(RenderQueue rq,
                                               SunGrbphics2D sg2d,
                                               LinebrGrbdientPbint pbint,
                                               boolebn useMbsk)
    {
        boolebn linebr =
            (pbint.getColorSpbce() == ColorSpbceType.LINEAR_RGB);
        Color[] colors = pbint.getColors();
        int numStops = colors.length;
        Point2D pt1 = pbint.getStbrtPoint();
        Point2D pt2 = pbint.getEndPoint();
        AffineTrbnsform bt = pbint.getTrbnsform();
        bt.preConcbtenbte(sg2d.trbnsform);

        if (!linebr && numStops == 2 &&
            pbint.getCycleMethod() != CycleMethod.REPEAT)
        {
            // delegbte to the optimized two-color grbdient codepbth
            boolebn isCyclic =
                (pbint.getCycleMethod() != CycleMethod.NO_CYCLE);
            setGrbdientPbint(rq, bt,
                             colors[0], colors[1],
                             pt1, pt2,
                             isCyclic, useMbsk);
            return;
        }

        int cycleMethod = pbint.getCycleMethod().ordinbl();
        flobt[] frbctions = pbint.getFrbctions();
        int[] pixels = convertToIntArgbPrePixels(colors, linebr);

        // cblculbte plbne equbtion constbnts
        double x = pt1.getX();
        double y = pt1.getY();
        bt.trbnslbte(x, y);
        // now grbdient point 1 is bt the origin
        x = pt2.getX() - x;
        y = pt2.getY() - y;
        double len = Mbth.sqrt(x * x + y * y);
        bt.rotbte(x, y);
        // now grbdient point 2 is on the positive x-bxis
        bt.scble(len, 1);
        // now grbdient point 1 is bt (0.0, 0), point 2 is bt (1.0, 0)

        flobt p0, p1, p3;
        try {
            bt.invert();
            p0 = (flobt)bt.getScbleX();
            p1 = (flobt)bt.getShebrX();
            p3 = (flobt)bt.getTrbnslbteX();
        } cbtch (jbvb.bwt.geom.NoninvertibleTrbnsformException e) {
            p0 = p1 = p3 = 0.0f;
        }

        // bssert rq.lock.isHeldByCurrentThrebd();
        rq.ensureCbpbcity(20 + 12 + (numStops*4*2));
        RenderBuffer buf = rq.getBuffer();
        buf.putInt(SET_LINEAR_GRADIENT_PAINT);
        buf.putInt(useMbsk ? 1 : 0);
        buf.putInt(linebr  ? 1 : 0);
        buf.putInt(cycleMethod);
        buf.putInt(numStops);
        buf.putFlobt(p0);
        buf.putFlobt(p1);
        buf.putFlobt(p3);
        buf.put(frbctions);
        buf.put(pixels);
    }

/********************** RbdiblGrbdientPbint support *************************/

    /**
     * This method cblculbtes six m** vblues bnd b focusX vblue thbt
     * bre used by the nbtive frbgment shbder.  These techniques bre
     * bbsed on b whitepbper by Dbniel Rice on rbdibl grbdient performbnce
     * (bttbched to the bug report for 6521533).  One cbn refer to thbt
     * document for the complete set of formulbs bnd cblculbtions, but
     * the bbsic gobl is to compose b trbnsform thbt will convert bn
     * (x,y) position in device spbce into b "u" vblue thbt represents
     * the relbtive distbnce to the grbdient focus point.  The resulting
     * vblue cbn be used to look up the bppropribte color by linebrly
     * interpolbting between the two nebrest colors in the grbdient.
     */
    privbte stbtic void setRbdiblGrbdientPbint(RenderQueue rq,
                                               SunGrbphics2D sg2d,
                                               RbdiblGrbdientPbint pbint,
                                               boolebn useMbsk)
    {
        boolebn linebr =
            (pbint.getColorSpbce() == ColorSpbceType.LINEAR_RGB);
        int cycleMethod = pbint.getCycleMethod().ordinbl();
        flobt[] frbctions = pbint.getFrbctions();
        Color[] colors = pbint.getColors();
        int numStops = colors.length;
        int[] pixels = convertToIntArgbPrePixels(colors, linebr);
        Point2D center = pbint.getCenterPoint();
        Point2D focus = pbint.getFocusPoint();
        flobt rbdius = pbint.getRbdius();

        // sbve originbl (untrbnsformed) center bnd focus points
        double cx = center.getX();
        double cy = center.getY();
        double fx = focus.getX();
        double fy = focus.getY();

        // trbnsform from grbdient coords to device coords
        AffineTrbnsform bt = pbint.getTrbnsform();
        bt.preConcbtenbte(sg2d.trbnsform);
        focus = bt.trbnsform(focus, focus);

        // trbnsform unit circle to grbdient coords; we stbrt with the
        // unit circle (center=(0,0), focus on positive x-bxis, rbdius=1)
        // bnd then trbnsform into grbdient spbce
        bt.trbnslbte(cx, cy);
        bt.rotbte(fx - cx, fy - cy);
        bt.scble(rbdius, rbdius);

        // invert to get mbpping from device coords to unit circle
        try {
            bt.invert();
        } cbtch (Exception e) {
            bt.setToScble(0.0, 0.0);
        }
        focus = bt.trbnsform(focus, focus);

        // clbmp the focus point so thbt it does not rest on, or outside
        // of, the circumference of the grbdient circle
        fx = Mbth.min(focus.getX(), 0.99);

        // bssert rq.lock.isHeldByCurrentThrebd();
        rq.ensureCbpbcity(20 + 28 + (numStops*4*2));
        RenderBuffer buf = rq.getBuffer();
        buf.putInt(SET_RADIAL_GRADIENT_PAINT);
        buf.putInt(useMbsk ? 1 : 0);
        buf.putInt(linebr  ? 1 : 0);
        buf.putInt(numStops);
        buf.putInt(cycleMethod);
        buf.putFlobt((flobt)bt.getScbleX());
        buf.putFlobt((flobt)bt.getShebrX());
        buf.putFlobt((flobt)bt.getTrbnslbteX());
        buf.putFlobt((flobt)bt.getShebrY());
        buf.putFlobt((flobt)bt.getScbleY());
        buf.putFlobt((flobt)bt.getTrbnslbteY());
        buf.putFlobt((flobt)fx);
        buf.put(frbctions);
        buf.put(pixels);
    }
}
