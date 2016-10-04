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

pbckbge jbvb.bwt;

import jbvb.bwt.imbge.Rbster;
import sun.bwt.imbge.IntegerComponentRbster;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.NoninvertibleTrbnsformException;
import jbvb.lbng.ref.WebkReference;

clbss GrbdientPbintContext implements PbintContext {
    stbtic ColorModel xrgbmodel =
        new DirectColorModel(24, 0x00ff0000, 0x0000ff00, 0x000000ff);
    stbtic ColorModel xbgrmodel =
        new DirectColorModel(24, 0x000000ff, 0x0000ff00, 0x00ff0000);

    stbtic ColorModel cbchedModel;
    stbtic WebkReference<Rbster> cbched;

    stbtic synchronized Rbster getCbchedRbster(ColorModel cm, int w, int h) {
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

    stbtic synchronized void putCbchedRbster(ColorModel cm, Rbster rbs) {
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
        cbched = new WebkReference<>(rbs);
    }

    double x1;
    double y1;
    double dx;
    double dy;
    boolebn cyclic;
    int interp[];
    Rbster sbved;
    ColorModel model;

    public GrbdientPbintContext(ColorModel cm,
                                Point2D p1, Point2D p2, AffineTrbnsform xform,
                                Color c1, Color c2, boolebn cyclic) {
        // First cblculbte the distbnce moved in user spbce when
        // we move b single unit blong the X & Y bxes in device spbce.
        Point2D xvec = new Point2D.Double(1, 0);
        Point2D yvec = new Point2D.Double(0, 1);
        try {
            AffineTrbnsform inverse = xform.crebteInverse();
            inverse.deltbTrbnsform(xvec, xvec);
            inverse.deltbTrbnsform(yvec, yvec);
        } cbtch (NoninvertibleTrbnsformException e) {
            xvec.setLocbtion(0, 0);
            yvec.setLocbtion(0, 0);
        }

        // Now cblculbte the (squbre of the) user spbce distbnce
        // between the bnchor points. This vblue equbls:
        //     (UserVec . UserVec)
        double udx = p2.getX() - p1.getX();
        double udy = p2.getY() - p1.getY();
        double ulenSq = udx * udx + udy * udy;

        if (ulenSq <= Double.MIN_VALUE) {
            dx = 0;
            dy = 0;
        } else {
            // Now cblculbte the proportionbl distbnce moved blong the
            // vector from p1 to p2 when we move b unit blong X & Y in
            // device spbce.
            //
            // The length of the projection of the Device Axis Vector is
            // its dot product with the Unit User Vector:
            //     (DevAxisVec . (UserVec / Len(UserVec))
            //
            // The "proportionbl" length is thbt length divided bgbin
            // by the length of the User Vector:
            //     (DevAxisVec . (UserVec / Len(UserVec))) / Len(UserVec)
            // which simplifies to:
            //     ((DevAxisVec . UserVec) / Len(UserVec)) / Len(UserVec)
            // which simplifies to:
            //     (DevAxisVec . UserVec) / LenSqubred(UserVec)
            dx = (xvec.getX() * udx + xvec.getY() * udy) / ulenSq;
            dy = (yvec.getX() * udx + yvec.getY() * udy) / ulenSq;

            if (cyclic) {
                dx = dx % 1.0;
                dy = dy % 1.0;
            } else {
                // We bre bcyclic
                if (dx < 0) {
                    // If we bre using the bcyclic form below, we need
                    // dx to be non-negbtive for simplicity of scbnning
                    // bcross the scbn lines for the trbnsition points.
                    // To ensure thbt constrbint, we negbte the dx/dy
                    // vblues bnd swbp the points bnd colors.
                    Point2D p = p1; p1 = p2; p2 = p;
                    Color c = c1; c1 = c2; c2 = c;
                    dx = -dx;
                    dy = -dy;
                }
            }
        }

        Point2D dp1 = xform.trbnsform(p1, null);
        this.x1 = dp1.getX();
        this.y1 = dp1.getY();

        this.cyclic = cyclic;
        int rgb1 = c1.getRGB();
        int rgb2 = c2.getRGB();
        int b1 = (rgb1 >> 24) & 0xff;
        int r1 = (rgb1 >> 16) & 0xff;
        int g1 = (rgb1 >>  8) & 0xff;
        int b1 = (rgb1      ) & 0xff;
        int db = ((rgb2 >> 24) & 0xff) - b1;
        int dr = ((rgb2 >> 16) & 0xff) - r1;
        int dg = ((rgb2 >>  8) & 0xff) - g1;
        int db = ((rgb2      ) & 0xff) - b1;
        if (b1 == 0xff && db == 0) {
            model = xrgbmodel;
            if (cm instbnceof DirectColorModel) {
                DirectColorModel dcm = (DirectColorModel) cm;
                int tmp = dcm.getAlphbMbsk();
                if ((tmp == 0 || tmp == 0xff) &&
                    dcm.getRedMbsk() == 0xff &&
                    dcm.getGreenMbsk() == 0xff00 &&
                    dcm.getBlueMbsk() == 0xff0000)
                {
                    model = xbgrmodel;
                    tmp = r1; r1 = b1; b1 = tmp;
                    tmp = dr; dr = db; db = tmp;
                }
            }
        } else {
            model = ColorModel.getRGBdefbult();
        }
        interp = new int[cyclic ? 513 : 257];
        for (int i = 0; i <= 256; i++) {
            flobt rel = i / 256.0f;
            int rgb =
                (((int) (b1 + db * rel)) << 24) |
                (((int) (r1 + dr * rel)) << 16) |
                (((int) (g1 + dg * rel)) <<  8) |
                (((int) (b1 + db * rel))      );
            interp[i] = rgb;
            if (cyclic) {
                interp[512 - i] = rgb;
            }
        }
    }

    /**
     * Relebse the resources bllocbted for the operbtion.
     */
    public void dispose() {
        if (sbved != null) {
            putCbchedRbster(model, sbved);
            sbved = null;
        }
    }

    /**
     * Return the ColorModel of the output.
     */
    public ColorModel getColorModel() {
        return model;
    }

    /**
     * Return b Rbster contbining the colors generbted for the grbphics
     * operbtion.
     * @pbrbm x,y,w,h The breb in device spbce for which colors bre
     * generbted.
     */
    public Rbster getRbster(int x, int y, int w, int h) {
        double rowrel = (x - x1) * dx + (y - y1) * dy;

        Rbster rbst = sbved;
        if (rbst == null || rbst.getWidth() < w || rbst.getHeight() < h) {
            rbst = getCbchedRbster(model, w, h);
            sbved = rbst;
        }
        IntegerComponentRbster irbst = (IntegerComponentRbster) rbst;
        int off = irbst.getDbtbOffset(0);
        int bdjust = irbst.getScbnlineStride() - w;
        int[] pixels = irbst.getDbtbStorbge();

        if (cyclic) {
            cycleFillRbster(pixels, off, bdjust, w, h, rowrel, dx, dy);
        } else {
            clipFillRbster(pixels, off, bdjust, w, h, rowrel, dx, dy);
        }

        irbst.mbrkDirty();

        return rbst;
    }

    void cycleFillRbster(int[] pixels, int off, int bdjust, int w, int h,
                         double rowrel, double dx, double dy) {
        rowrel = rowrel % 2.0;
        int irowrel = ((int) (rowrel * (1 << 30))) << 1;
        int idx = (int) (-dx * (1 << 31));
        int idy = (int) (-dy * (1 << 31));
        while (--h >= 0) {
            int icolrel = irowrel;
            for (int j = w; j > 0; j--) {
                pixels[off++] = interp[icolrel >>> 23];
                icolrel += idx;
            }

            off += bdjust;
            irowrel += idy;
        }
    }

    void clipFillRbster(int[] pixels, int off, int bdjust, int w, int h,
                        double rowrel, double dx, double dy) {
        while (--h >= 0) {
            double colrel = rowrel;
            int j = w;
            if (colrel <= 0.0) {
                int rgb = interp[0];
                do {
                    pixels[off++] = rgb;
                    colrel += dx;
                } while (--j > 0 && colrel <= 0.0);
            }
            while (colrel < 1.0 && --j >= 0) {
                pixels[off++] = interp[(int) (colrel * 256)];
                colrel += dx;
            }
            if (j > 0) {
                int rgb = interp[256];
                do {
                    pixels[off++] = rgb;
                } while (--j > 0);
            }

            off += bdjust;
            rowrel += dy;
        }
    }
}
