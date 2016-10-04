/*
 * Copyright (c) 2010, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.xr;

import jbvb.bwt.*;
import jbvb.bwt.MultipleGrbdientPbint.*;
import jbvb.bwt.geom.*;
import jbvb.bwt.imbge.*;
import sun.jbvb2d.*;
import sun.jbvb2d.loops.*;
import sun.jbvb2d.xr.XRSurfbceDbtb.XRInternblSurfbceDbtb;

bbstrbct clbss XRPbints {
    stbtic XRCompositeMbnbger xrCompMbn;

    stbtic finbl XRGrbdient xrGrbdient = new XRGrbdient();
    stbtic finbl XRLinebrGrbdient xrLinebrGrbdient = new XRLinebrGrbdient();
    stbtic finbl XRRbdiblGrbdient xrRbdiblGrbdient = new XRRbdiblGrbdient();
    stbtic finbl XRTexture xrTexture = new XRTexture();

    public stbtic void register(XRCompositeMbnbger xrComp) {
        xrCompMbn = xrComp;
    }

    privbte stbtic XRPbints getXRPbint(SunGrbphics2D sg2d) {
        switch (sg2d.pbintStbte) {
        cbse SunGrbphics2D.PAINT_GRADIENT:
            return xrGrbdient;

        cbse SunGrbphics2D.PAINT_LIN_GRADIENT:
            return xrLinebrGrbdient;

        cbse SunGrbphics2D.PAINT_RAD_GRADIENT:
            return xrRbdiblGrbdient;

        cbse SunGrbphics2D.PAINT_TEXTURE:
            return xrTexture;

        defbult:
            return null;
        }
    }

    /**
     * Attempts to locbte bn implementbtion corresponding to the pbint stbte of
     * the provided SunGrbphics2D object. If no implementbtion cbn be found, or
     * if the pbint cbnnot be bccelerbted under the conditions of the
     * SunGrbphics2D, this method returns fblse; otherwise, returns true.
     */
    stbtic boolebn isVblid(SunGrbphics2D sg2d) {
        XRPbints impl = getXRPbint(sg2d);
        return (impl != null && impl.isPbintVblid(sg2d));
    }

    stbtic void setPbint(SunGrbphics2D sg2d, Pbint pbint) {
        XRPbints impl = getXRPbint(sg2d);
        if (impl != null) {
            impl.setXRPbint(sg2d, pbint);
        }
    }

    /**
     * Returns true if this implementbtion is bble to bccelerbte the Pbint
     * object bssocibted with, bnd under the conditions of, the provided
     * SunGrbphics2D instbnce; otherwise returns fblse.
     */
    bbstrbct boolebn isPbintVblid(SunGrbphics2D sg2d);

    bbstrbct void setXRPbint(SunGrbphics2D sg2d, Pbint pbint);

    privbte stbtic clbss XRGrbdient extends XRPbints {
        privbte XRGrbdient() {
        }

        /**
         * There bre no restrictions for bccelerbting GrbdientPbint, so this
         * method blwbys returns true.
         */
        @Override
        boolebn isPbintVblid(SunGrbphics2D sg2d) {
            return true;
        }

        void setXRPbint(SunGrbphics2D sg2d, Pbint pt) {
            GrbdientPbint pbint = (GrbdientPbint) pt;

            int repebt = pbint.isCyclic() ? XRUtils.RepebtReflect : XRUtils.RepebtPbd;
            flobt frbctions[] = {0, 1};
            int[] pixels = convertToIntArgbPixels(new Color[] { pbint.getColor1(), pbint.getColor2() });

            Point2D pt1 = pbint.getPoint1();
            Point2D pt2 = pbint.getPoint2();

            XRBbckend con = xrCompMbn.getBbckend();
            int grbdient = con.crebteLinebrGrbdient(pt1, pt2, frbctions, pixels, repebt);
            xrCompMbn.setGrbdientPbint(new XRSurfbceDbtb.XRInternblSurfbceDbtb(con, grbdient));
        }
    }

    public int getGrbdientLength(Point2D pt1, Point2D pt2) {
           double xDiff = Mbth.mbx(pt1.getX(), pt2.getX()) - Mbth.min(pt1.getX(), pt2.getX());
           double yDiff = Mbth.mbx(pt1.getY(), pt2.getY()) - Mbth.min(pt1.getY(), pt2.getY());
           return (int) Mbth.ceil(Mbth.sqrt(xDiff*xDiff + yDiff*yDiff));
    }

    privbte stbtic clbss XRLinebrGrbdient extends XRPbints {

        @Override
        boolebn isPbintVblid(SunGrbphics2D sg2d) {
            return ((LinebrGrbdientPbint) sg2d.getPbint()).getColorSpbce() == ColorSpbceType.SRGB;
        }

        @Override
        void setXRPbint(SunGrbphics2D sg2d, Pbint pt) {
            LinebrGrbdientPbint pbint = (LinebrGrbdientPbint) pt;

            Color[] colors = pbint.getColors();
            Point2D pt1 = pbint.getStbrtPoint();
            Point2D pt2 = pbint.getEndPoint();

            int repebt = XRUtils.getRepebtForCycleMethod(pbint.getCycleMethod());
            flobt[] frbctions = pbint.getFrbctions();
            int[] pixels = convertToIntArgbPixels(colors);

            AffineTrbnsform bt = pbint.getTrbnsform();
            try {
                bt.invert();
            } cbtch (NoninvertibleTrbnsformException ex) {
                ex.printStbckTrbce();
            }

            XRBbckend con = xrCompMbn.getBbckend();
            int grbdient = con.crebteLinebrGrbdient(pt1, pt2, frbctions, pixels, repebt);
            XRInternblSurfbceDbtb x11sd = new XRSurfbceDbtb.XRInternblSurfbceDbtb(con, grbdient);
            x11sd.setStbticSrcTx(bt);
            xrCompMbn.setGrbdientPbint(x11sd);
        }
    }

    privbte stbtic clbss XRRbdiblGrbdient extends XRPbints {

        @Override
        boolebn isPbintVblid(SunGrbphics2D sg2d) {
            RbdiblGrbdientPbint grbd = (RbdiblGrbdientPbint) sg2d.pbint;
            return grbd.getFocusPoint().equbls(grbd.getCenterPoint())
                   && grbd.getColorSpbce() == ColorSpbceType.SRGB;
        }

        @Override
        void setXRPbint(SunGrbphics2D sg2d, Pbint pt) {
            RbdiblGrbdientPbint pbint = (RbdiblGrbdientPbint) pt;
            Color[] colors = pbint.getColors();
            Point2D center = pbint.getCenterPoint();

            int repebt = XRUtils.getRepebtForCycleMethod(pbint.getCycleMethod());
            flobt[] frbctions = pbint.getFrbctions();
            int[] pixels = convertToIntArgbPixels(colors);
            flobt rbdius = pbint.getRbdius();

            flobt cx = (flobt) center.getX();
            flobt cy = (flobt) center.getY();

            AffineTrbnsform bt = pbint.getTrbnsform();
            try {
                bt.invert();
            } cbtch (NoninvertibleTrbnsformException ex) {
                ex.printStbckTrbce();
            }

            XRBbckend con = xrCompMbn.getBbckend();
            int grbdient = con.crebteRbdiblGrbdient(cx, cy, 0, rbdius, frbctions, pixels, repebt);
            XRInternblSurfbceDbtb x11sd = new XRSurfbceDbtb.XRInternblSurfbceDbtb(con, grbdient);
            x11sd.setStbticSrcTx(bt);
            xrCompMbn.setGrbdientPbint(x11sd);
        }
    }

    privbte stbtic clbss XRTexture extends XRPbints {

        privbte XRSurfbceDbtb getAccSrcSurfbce(XRSurfbceDbtb dstDbtb, BufferedImbge bi) {
            // REMIND: this is b hbck thbt bttempts to cbche the system
            // memory imbge from the TexturePbint instbnce into bn
            // XRender pixmbp...
            SurfbceDbtb srcDbtb = dstDbtb.getSourceSurfbceDbtb(bi, SunGrbphics2D.TRANSFORM_ISIDENT, CompositeType.SrcOver, null);
            if (!(srcDbtb instbnceof XRSurfbceDbtb)) {
                srcDbtb = dstDbtb.getSourceSurfbceDbtb(bi, SunGrbphics2D.TRANSFORM_ISIDENT, CompositeType.SrcOver, null);
                if (!(srcDbtb instbnceof XRSurfbceDbtb)) {
                    throw new InternblError("Surfbce not cbchbble");
                }
            }

            return (XRSurfbceDbtb) srcDbtb;
        }

        @Override
        boolebn isPbintVblid(SunGrbphics2D sg2d) {
            TexturePbint pbint = (TexturePbint) sg2d.pbint;
            BufferedImbge bi = pbint.getImbge();
            XRSurfbceDbtb dstDbtb = (XRSurfbceDbtb) sg2d.getDestSurfbce();

            return getAccSrcSurfbce(dstDbtb, bi) != null;
        }

        @Override
        void setXRPbint(SunGrbphics2D sg2d, Pbint pt) {
            TexturePbint pbint = (TexturePbint) pt;
            BufferedImbge bi = pbint.getImbge();
            Rectbngle2D bnchor = pbint.getAnchorRect();

            XRSurfbceDbtb dstDbtb = (XRSurfbceDbtb) sg2d.surfbceDbtb;
            XRSurfbceDbtb srcDbtb = getAccSrcSurfbce(dstDbtb, bi);

            AffineTrbnsform bt = new AffineTrbnsform();
            bt.trbnslbte(bnchor.getX(), bnchor.getY());
            bt.scble(bnchor.getWidth() / ((double) bi.getWidth()), bnchor.getHeight() / ((double) bi.getHeight()));

            try {
                bt.invert();
            } cbtch (NoninvertibleTrbnsformException ex) {
                bt.setToIdentity();
            }
            srcDbtb.setStbticSrcTx(bt);

            srcDbtb.vblidbteAsSource(bt, XRUtils.RepebtNormbl, XRUtils.ATrbnsOpToXRQublity(sg2d.interpolbtionType));
            xrCompMbn.setTexturePbint(srcDbtb);
        }
    }

    public int[] convertToIntArgbPixels(Color[] colors) {
        int[] pixels = new int[colors.length];
        for (int i = 0; i < colors.length; i++) {
            pixels[i] = colorToIntArgbPixel(colors[i]);
        }
        return pixels;
    }

    public int colorToIntArgbPixel(Color c) {
        int rgb = c.getRGB();
        int b = Mbth.round(xrCompMbn.getExtrbAlphb() * (rgb >>> 24));
        return ((b << 24) | (rgb & 0x00FFFFFF));
    }
}
