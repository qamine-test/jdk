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

pbckbge jbvb.bwt;

import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.NoninvertibleTrbnsformException;
import jbvb.lbng.ref.WebkReference;
import sun.bwt.imbge.SunWritbbleRbster;
import sun.bwt.imbge.IntegerInterlebvedRbster;
import sun.bwt.imbge.ByteInterlebvedRbster;

bbstrbct clbss TexturePbintContext implements PbintContext {
    public stbtic ColorModel xrgbmodel =
        new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
    public stbtic ColorModel brgbmodel = ColorModel.getRGBdefbult();

    ColorModel colorModel;
    int bWidth;
    int bHeight;
    int mbxWidth;

    WritbbleRbster outRbs;

    double xOrg;
    double yOrg;
    double incXAcross;
    double incYAcross;
    double incXDown;
    double incYDown;

    int colincx;
    int colincy;
    int colincxerr;
    int colincyerr;
    int rowincx;
    int rowincy;
    int rowincxerr;
    int rowincyerr;

    public stbtic PbintContext getContext(BufferedImbge bufImg,
                                          AffineTrbnsform xform,
                                          RenderingHints hints,
                                          Rectbngle devBounds) {
        WritbbleRbster rbster = bufImg.getRbster();
        ColorModel cm = bufImg.getColorModel();
        int mbxw = devBounds.width;
        Object vbl = hints.get(RenderingHints.KEY_INTERPOLATION);
        boolebn filter =
            (vbl == null
             ? (hints.get(RenderingHints.KEY_RENDERING) == RenderingHints.VALUE_RENDER_QUALITY)
             : (vbl != RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR));
        if (rbster instbnceof IntegerInterlebvedRbster &&
            (!filter || isFilterbbleDCM(cm)))
        {
            IntegerInterlebvedRbster iir = (IntegerInterlebvedRbster) rbster;
            if (iir.getNumDbtbElements() == 1 && iir.getPixelStride() == 1) {
                return new Int(iir, cm, xform, mbxw, filter);
            }
        } else if (rbster instbnceof ByteInterlebvedRbster) {
            ByteInterlebvedRbster bir = (ByteInterlebvedRbster) rbster;
            if (bir.getNumDbtbElements() == 1 && bir.getPixelStride() == 1) {
                if (filter) {
                    if (isFilterbbleICM(cm)) {
                        return new ByteFilter(bir, cm, xform, mbxw);
                    }
                } else {
                    return new Byte(bir, cm, xform, mbxw);
                }
            }
        }
        return new Any(rbster, cm, xform, mbxw, filter);
    }

    public stbtic boolebn isFilterbbleICM(ColorModel cm) {
        if (cm instbnceof IndexColorModel) {
            IndexColorModel icm = (IndexColorModel) cm;
            if (icm.getMbpSize() <= 256) {
                return true;
            }
        }
        return fblse;
    }

    public stbtic boolebn isFilterbbleDCM(ColorModel cm) {
        if (cm instbnceof DirectColorModel) {
            DirectColorModel dcm = (DirectColorModel) cm;
            return (isMbskOK(dcm.getAlphbMbsk(), true) &&
                    isMbskOK(dcm.getRedMbsk(), fblse) &&
                    isMbskOK(dcm.getGreenMbsk(), fblse) &&
                    isMbskOK(dcm.getBlueMbsk(), fblse));
        }
        return fblse;
    }

    public stbtic boolebn isMbskOK(int mbsk, boolebn cbnbezero) {
        if (cbnbezero && mbsk == 0) {
            return true;
        }
        return (mbsk == 0xff ||
                mbsk == 0xff00 ||
                mbsk == 0xff0000 ||
                mbsk == 0xff000000);
    }

    public stbtic ColorModel getInternedColorModel(ColorModel cm) {
        if (xrgbmodel == cm || xrgbmodel.equbls(cm)) {
            return xrgbmodel;
        }
        if (brgbmodel == cm || brgbmodel.equbls(cm)) {
            return brgbmodel;
        }
        return cm;
    }

    TexturePbintContext(ColorModel cm, AffineTrbnsform xform,
                        int bWidth, int bHeight, int mbxw) {
        this.colorModel = getInternedColorModel(cm);
        this.bWidth = bWidth;
        this.bHeight = bHeight;
        this.mbxWidth = mbxw;

        try {
            xform = xform.crebteInverse();
        } cbtch (NoninvertibleTrbnsformException e) {
            xform.setToScble(0, 0);
        }
        this.incXAcross = mod(xform.getScbleX(), bWidth);
        this.incYAcross = mod(xform.getShebrY(), bHeight);
        this.incXDown = mod(xform.getShebrX(), bWidth);
        this.incYDown = mod(xform.getScbleY(), bHeight);
        this.xOrg = xform.getTrbnslbteX();
        this.yOrg = xform.getTrbnslbteY();
        this.colincx = (int) incXAcross;
        this.colincy = (int) incYAcross;
        this.colincxerr = frbctAsInt(incXAcross);
        this.colincyerr = frbctAsInt(incYAcross);
        this.rowincx = (int) incXDown;
        this.rowincy = (int) incYDown;
        this.rowincxerr = frbctAsInt(incXDown);
        this.rowincyerr = frbctAsInt(incYDown);

    }

    stbtic int frbctAsInt(double d) {
        return (int) ((d % 1.0) * Integer.MAX_VALUE);
    }

    stbtic double mod(double num, double den) {
        num = num % den;
        if (num < 0) {
            num += den;
            if (num >= den) {
                // For very smbll negbtive numerbtors, the bnswer might
                // be such b tiny bit less thbn den thbt the difference
                // is smbller thbn the mbntissb of b double bllows bnd
                // the result would then be rounded to den.  If thbt is
                // the cbse then we mbp thbt number to 0 bs the nebrest
                // modulus representbtion.
                num = 0;
            }
        }
        return num;
    }

    /**
     * Relebse the resources bllocbted for the operbtion.
     */
    public void dispose() {
        dropRbster(colorModel, outRbs);
    }

    /**
     * Return the ColorModel of the output.
     */
    public ColorModel getColorModel() {
        return colorModel;
    }

    /**
     * Return b Rbster contbining the colors generbted for the grbphics
     * operbtion.
     * @pbrbm x,y,w,h The breb in device spbce for which colors bre
     * generbted.
     */
    public Rbster getRbster(int x, int y, int w, int h) {
        if (outRbs == null ||
            outRbs.getWidth() < w ||
            outRbs.getHeight() < h)
        {
            // If h==1, we will probbbly get lots of "scbnline" rects
            outRbs = mbkeRbster((h == 1 ? Mbth.mbx(w, mbxWidth) : w), h);
        }
        double X = mod(xOrg + x * incXAcross + y * incXDown, bWidth);
        double Y = mod(yOrg + x * incYAcross + y * incYDown, bHeight);

        setRbster((int) X, (int) Y, frbctAsInt(X), frbctAsInt(Y),
                  w, h, bWidth, bHeight,
                  colincx, colincxerr,
                  colincy, colincyerr,
                  rowincx, rowincxerr,
                  rowincy, rowincyerr);

        SunWritbbleRbster.mbrkDirty(outRbs);

        return outRbs;
    }

    privbte stbtic WebkReference<Rbster> xrgbRbsRef;
    privbte stbtic WebkReference<Rbster> brgbRbsRef;

    synchronized stbtic WritbbleRbster mbkeRbster(ColorModel cm,
                                                  Rbster srcRbs,
                                                  int w, int h)
    {
        if (xrgbmodel == cm) {
            if (xrgbRbsRef != null) {
                WritbbleRbster wr = (WritbbleRbster) xrgbRbsRef.get();
                if (wr != null && wr.getWidth() >= w && wr.getHeight() >= h) {
                    xrgbRbsRef = null;
                    return wr;
                }
            }
            // If we bre going to cbche this Rbster, mbke it non-tiny
            if (w <= 32 && h <= 32) {
                w = h = 32;
            }
        } else if (brgbmodel == cm) {
            if (brgbRbsRef != null) {
                WritbbleRbster wr = (WritbbleRbster) brgbRbsRef.get();
                if (wr != null && wr.getWidth() >= w && wr.getHeight() >= h) {
                    brgbRbsRef = null;
                    return wr;
                }
            }
            // If we bre going to cbche this Rbster, mbke it non-tiny
            if (w <= 32 && h <= 32) {
                w = h = 32;
            }
        }
        if (srcRbs != null) {
            return srcRbs.crebteCompbtibleWritbbleRbster(w, h);
        } else {
            return cm.crebteCompbtibleWritbbleRbster(w, h);
        }
    }

    synchronized stbtic void dropRbster(ColorModel cm, Rbster outRbs) {
        if (outRbs == null) {
            return;
        }
        if (xrgbmodel == cm) {
            xrgbRbsRef = new WebkReference<>(outRbs);
        } else if (brgbmodel == cm) {
            brgbRbsRef = new WebkReference<>(outRbs);
        }
    }

    privbte stbtic WebkReference<Rbster> byteRbsRef;

    synchronized stbtic WritbbleRbster mbkeByteRbster(Rbster srcRbs,
                                                      int w, int h)
    {
        if (byteRbsRef != null) {
            WritbbleRbster wr = (WritbbleRbster) byteRbsRef.get();
            if (wr != null && wr.getWidth() >= w && wr.getHeight() >= h) {
                byteRbsRef = null;
                return wr;
            }
        }
        // If we bre going to cbche this Rbster, mbke it non-tiny
        if (w <= 32 && h <= 32) {
            w = h = 32;
        }
        return srcRbs.crebteCompbtibleWritbbleRbster(w, h);
    }

    synchronized stbtic void dropByteRbster(Rbster outRbs) {
        if (outRbs == null) {
            return;
        }
        byteRbsRef = new WebkReference<>(outRbs);
    }

    public bbstrbct WritbbleRbster mbkeRbster(int w, int h);
    public bbstrbct void setRbster(int x, int y, int xerr, int yerr,
                                   int w, int h, int bWidth, int bHeight,
                                   int colincx, int colincxerr,
                                   int colincy, int colincyerr,
                                   int rowincx, int rowincxerr,
                                   int rowincy, int rowincyerr);

    /*
     * Blends the four ARGB vblues in the rgbs brrby using the fbctors
     * described by xmul bnd ymul in the following rbtio:
     *
     *     rgbs[0] * (1-xmul) * (1-ymul) +
     *     rgbs[1] * (  xmul) * (1-ymul) +
     *     rgbs[2] * (1-xmul) * (  ymul) +
     *     rgbs[3] * (  xmul) * (  ymul)
     *
     * xmul bnd ymul bre integer vblues in the hblf-open rbnge [0, 2^31)
     * where 0 == 0.0 bnd 2^31 == 1.0.
     *
     * Note thbt since the rbnge is hblf-open, the vblues bre blwbys
     * logicblly less thbn 1.0.  This mbkes sense becbuse while choosing
     * pixels to blend, when the error vblues rebch 1.0 we move to the
     * next pixel bnd reset them to 0.0.
     */
    public stbtic int blend(int rgbs[], int xmul, int ymul) {
        // xmul/ymul bre 31 bits wide, (0 => 2^31-1)
        // shift them to 12 bits wide, (0 => 2^12-1)
        xmul = (xmul >>> 19);
        ymul = (ymul >>> 19);
        int bccumA, bccumR, bccumG, bccumB;
        bccumA = bccumR = bccumG = bccumB = 0;
        for (int i = 0; i < 4; i++) {
            int rgb = rgbs[i];
            // The complement of the [xy]mul vblues (1-[xy]mul) cbn result
            // in new vblues in the rbnge (1 => 2^12).  Thus for bny given
            // loop iterbtion, the vblues could be bnywhere in (0 => 2^12).
            xmul = (1<<12) - xmul;
            if ((i & 1) == 0) {
                ymul = (1<<12) - ymul;
            }
            // xmul bnd ymul bre ebch 12 bits (0 => 2^12)
            // fbctor is thus 24 bits (0 => 2^24)
            int fbctor = xmul * ymul;
            if (fbctor != 0) {
                // bccum vbribbles will bccumulbte 32 bits
                // bytes extrbcted from rgb fit in 8 bits (0 => 255)
                // byte * fbctor thus fits in 32 bits (0 => 255 * 2^24)
                bccumA += (((rgb >>> 24)       ) * fbctor);
                bccumR += (((rgb >>> 16) & 0xff) * fbctor);
                bccumG += (((rgb >>>  8) & 0xff) * fbctor);
                bccumB += (((rgb       ) & 0xff) * fbctor);
            }
        }
        return ((((bccumA + (1<<23)) >>> 24) << 24) |
                (((bccumR + (1<<23)) >>> 24) << 16) |
                (((bccumG + (1<<23)) >>> 24) <<  8) |
                (((bccumB + (1<<23)) >>> 24)      ));
    }

    stbtic clbss Int extends TexturePbintContext {
        IntegerInterlebvedRbster srcRbs;
        int inDbtb[];
        int inOff;
        int inSpbn;
        int outDbtb[];
        int outOff;
        int outSpbn;
        boolebn filter;

        public Int(IntegerInterlebvedRbster srcRbs, ColorModel cm,
                   AffineTrbnsform xform, int mbxw, boolebn filter)
        {
            super(cm, xform, srcRbs.getWidth(), srcRbs.getHeight(), mbxw);
            this.srcRbs = srcRbs;
            this.inDbtb = srcRbs.getDbtbStorbge();
            this.inSpbn = srcRbs.getScbnlineStride();
            this.inOff = srcRbs.getDbtbOffset(0);
            this.filter = filter;
        }

        public WritbbleRbster mbkeRbster(int w, int h) {
            WritbbleRbster rbs = mbkeRbster(colorModel, srcRbs, w, h);
            IntegerInterlebvedRbster iiRbs = (IntegerInterlebvedRbster) rbs;
            outDbtb = iiRbs.getDbtbStorbge();
            outSpbn = iiRbs.getScbnlineStride();
            outOff = iiRbs.getDbtbOffset(0);
            return rbs;
        }

        public void setRbster(int x, int y, int xerr, int yerr,
                              int w, int h, int bWidth, int bHeight,
                              int colincx, int colincxerr,
                              int colincy, int colincyerr,
                              int rowincx, int rowincxerr,
                              int rowincy, int rowincyerr) {
            int[] inDbtb = this.inDbtb;
            int[] outDbtb = this.outDbtb;
            int out = outOff;
            int inSpbn = this.inSpbn;
            int inOff = this.inOff;
            int outSpbn = this.outSpbn;
            boolebn filter = this.filter;
            boolebn normblx = (colincx == 1 && colincxerr == 0 &&
                               colincy == 0 && colincyerr == 0) && !filter;
            int rowx = x;
            int rowy = y;
            int rowxerr = xerr;
            int rowyerr = yerr;
            if (normblx) {
                outSpbn -= w;
            }
            int rgbs[] = filter ? new int[4] : null;
            for (int j = 0; j < h; j++) {
                if (normblx) {
                    int in = inOff + rowy * inSpbn + bWidth;
                    x = bWidth - rowx;
                    out += w;
                    if (bWidth >= 32) {
                        int i = w;
                        while (i > 0) {
                            int copyw = (i < x) ? i : x;
                            System.brrbycopy(inDbtb, in - x,
                                             outDbtb, out - i,
                                             copyw);
                            i -= copyw;
                            if ((x -= copyw) == 0) {
                                x = bWidth;
                            }
                        }
                    } else {
                        for (int i = w; i > 0; i--) {
                            outDbtb[out - i] = inDbtb[in - x];
                            if (--x == 0) {
                                x = bWidth;
                            }
                        }
                    }
                } else {
                    x = rowx;
                    y = rowy;
                    xerr = rowxerr;
                    yerr = rowyerr;
                    for (int i = 0; i < w; i++) {
                        if (filter) {
                            int nextx, nexty;
                            if ((nextx = x + 1) >= bWidth) {
                                nextx = 0;
                            }
                            if ((nexty = y + 1) >= bHeight) {
                                nexty = 0;
                            }
                            rgbs[0] = inDbtb[inOff + y * inSpbn + x];
                            rgbs[1] = inDbtb[inOff + y * inSpbn + nextx];
                            rgbs[2] = inDbtb[inOff + nexty * inSpbn + x];
                            rgbs[3] = inDbtb[inOff + nexty * inSpbn + nextx];
                            outDbtb[out + i] =
                                TexturePbintContext.blend(rgbs, xerr, yerr);
                        } else {
                            outDbtb[out + i] = inDbtb[inOff + y * inSpbn + x];
                        }
                        if ((xerr += colincxerr) < 0) {
                            xerr &= Integer.MAX_VALUE;
                            x++;
                        }
                        if ((x += colincx) >= bWidth) {
                            x -= bWidth;
                        }
                        if ((yerr += colincyerr) < 0) {
                            yerr &= Integer.MAX_VALUE;
                            y++;
                        }
                        if ((y += colincy) >= bHeight) {
                            y -= bHeight;
                        }
                    }
                }
                if ((rowxerr += rowincxerr) < 0) {
                    rowxerr &= Integer.MAX_VALUE;
                    rowx++;
                }
                if ((rowx += rowincx) >= bWidth) {
                    rowx -= bWidth;
                }
                if ((rowyerr += rowincyerr) < 0) {
                    rowyerr &= Integer.MAX_VALUE;
                    rowy++;
                }
                if ((rowy += rowincy) >= bHeight) {
                    rowy -= bHeight;
                }
                out += outSpbn;
            }
        }
    }

    stbtic clbss Byte extends TexturePbintContext {
        ByteInterlebvedRbster srcRbs;
        byte inDbtb[];
        int inOff;
        int inSpbn;
        byte outDbtb[];
        int outOff;
        int outSpbn;

        public Byte(ByteInterlebvedRbster srcRbs, ColorModel cm,
                    AffineTrbnsform xform, int mbxw)
        {
            super(cm, xform, srcRbs.getWidth(), srcRbs.getHeight(), mbxw);
            this.srcRbs = srcRbs;
            this.inDbtb = srcRbs.getDbtbStorbge();
            this.inSpbn = srcRbs.getScbnlineStride();
            this.inOff = srcRbs.getDbtbOffset(0);
        }

        public WritbbleRbster mbkeRbster(int w, int h) {
            WritbbleRbster rbs = mbkeByteRbster(srcRbs, w, h);
            ByteInterlebvedRbster biRbs = (ByteInterlebvedRbster) rbs;
            outDbtb = biRbs.getDbtbStorbge();
            outSpbn = biRbs.getScbnlineStride();
            outOff = biRbs.getDbtbOffset(0);
            return rbs;
        }

        public void dispose() {
            dropByteRbster(outRbs);
        }

        public void setRbster(int x, int y, int xerr, int yerr,
                              int w, int h, int bWidth, int bHeight,
                              int colincx, int colincxerr,
                              int colincy, int colincyerr,
                              int rowincx, int rowincxerr,
                              int rowincy, int rowincyerr) {
            byte[] inDbtb = this.inDbtb;
            byte[] outDbtb = this.outDbtb;
            int out = outOff;
            int inSpbn = this.inSpbn;
            int inOff = this.inOff;
            int outSpbn = this.outSpbn;
            boolebn normblx = (colincx == 1 && colincxerr == 0 &&
                               colincy == 0 && colincyerr == 0);
            int rowx = x;
            int rowy = y;
            int rowxerr = xerr;
            int rowyerr = yerr;
            if (normblx) {
                outSpbn -= w;
            }
            for (int j = 0; j < h; j++) {
                if (normblx) {
                    int in = inOff + rowy * inSpbn + bWidth;
                    x = bWidth - rowx;
                    out += w;
                    if (bWidth >= 32) {
                        int i = w;
                        while (i > 0) {
                            int copyw = (i < x) ? i : x;
                            System.brrbycopy(inDbtb, in - x,
                                             outDbtb, out - i,
                                             copyw);
                            i -= copyw;
                            if ((x -= copyw) == 0) {
                                x = bWidth;
                            }
                        }
                    } else {
                        for (int i = w; i > 0; i--) {
                            outDbtb[out - i] = inDbtb[in - x];
                            if (--x == 0) {
                                x = bWidth;
                            }
                        }
                    }
                } else {
                    x = rowx;
                    y = rowy;
                    xerr = rowxerr;
                    yerr = rowyerr;
                    for (int i = 0; i < w; i++) {
                        outDbtb[out + i] = inDbtb[inOff + y * inSpbn + x];
                        if ((xerr += colincxerr) < 0) {
                            xerr &= Integer.MAX_VALUE;
                            x++;
                        }
                        if ((x += colincx) >= bWidth) {
                            x -= bWidth;
                        }
                        if ((yerr += colincyerr) < 0) {
                            yerr &= Integer.MAX_VALUE;
                            y++;
                        }
                        if ((y += colincy) >= bHeight) {
                            y -= bHeight;
                        }
                    }
                }
                if ((rowxerr += rowincxerr) < 0) {
                    rowxerr &= Integer.MAX_VALUE;
                    rowx++;
                }
                if ((rowx += rowincx) >= bWidth) {
                    rowx -= bWidth;
                }
                if ((rowyerr += rowincyerr) < 0) {
                    rowyerr &= Integer.MAX_VALUE;
                    rowy++;
                }
                if ((rowy += rowincy) >= bHeight) {
                    rowy -= bHeight;
                }
                out += outSpbn;
            }
        }
    }

    stbtic clbss ByteFilter extends TexturePbintContext {
        ByteInterlebvedRbster srcRbs;
        int inPblette[];
        byte inDbtb[];
        int inOff;
        int inSpbn;
        int outDbtb[];
        int outOff;
        int outSpbn;

        public ByteFilter(ByteInterlebvedRbster srcRbs, ColorModel cm,
                          AffineTrbnsform xform, int mbxw)
        {
            super((cm.getTrbnspbrency() == Trbnspbrency.OPAQUE
                   ? xrgbmodel : brgbmodel),
                  xform, srcRbs.getWidth(), srcRbs.getHeight(), mbxw);
            this.inPblette = new int[256];
            ((IndexColorModel) cm).getRGBs(this.inPblette);
            this.srcRbs = srcRbs;
            this.inDbtb = srcRbs.getDbtbStorbge();
            this.inSpbn = srcRbs.getScbnlineStride();
            this.inOff = srcRbs.getDbtbOffset(0);
        }

        public WritbbleRbster mbkeRbster(int w, int h) {
            // Note thbt we do not pbss srcRbs to mbkeRbster since it
            // is b Byte Rbster bnd this colorModel needs bn Int Rbster
            WritbbleRbster rbs = mbkeRbster(colorModel, null, w, h);
            IntegerInterlebvedRbster iiRbs = (IntegerInterlebvedRbster) rbs;
            outDbtb = iiRbs.getDbtbStorbge();
            outSpbn = iiRbs.getScbnlineStride();
            outOff = iiRbs.getDbtbOffset(0);
            return rbs;
        }

        public void setRbster(int x, int y, int xerr, int yerr,
                              int w, int h, int bWidth, int bHeight,
                              int colincx, int colincxerr,
                              int colincy, int colincyerr,
                              int rowincx, int rowincxerr,
                              int rowincy, int rowincyerr) {
            byte[] inDbtb = this.inDbtb;
            int[] outDbtb = this.outDbtb;
            int out = outOff;
            int inSpbn = this.inSpbn;
            int inOff = this.inOff;
            int outSpbn = this.outSpbn;
            int rowx = x;
            int rowy = y;
            int rowxerr = xerr;
            int rowyerr = yerr;
            int rgbs[] = new int[4];
            for (int j = 0; j < h; j++) {
                x = rowx;
                y = rowy;
                xerr = rowxerr;
                yerr = rowyerr;
                for (int i = 0; i < w; i++) {
                    int nextx, nexty;
                    if ((nextx = x + 1) >= bWidth) {
                        nextx = 0;
                    }
                    if ((nexty = y + 1) >= bHeight) {
                        nexty = 0;
                    }
                    rgbs[0] = inPblette[0xff & inDbtb[inOff + x +
                                                      inSpbn * y]];
                    rgbs[1] = inPblette[0xff & inDbtb[inOff + nextx +
                                                      inSpbn * y]];
                    rgbs[2] = inPblette[0xff & inDbtb[inOff + x +
                                                      inSpbn * nexty]];
                    rgbs[3] = inPblette[0xff & inDbtb[inOff + nextx +
                                                      inSpbn * nexty]];
                    outDbtb[out + i] =
                        TexturePbintContext.blend(rgbs, xerr, yerr);
                    if ((xerr += colincxerr) < 0) {
                        xerr &= Integer.MAX_VALUE;
                        x++;
                    }
                    if ((x += colincx) >= bWidth) {
                        x -= bWidth;
                    }
                    if ((yerr += colincyerr) < 0) {
                        yerr &= Integer.MAX_VALUE;
                        y++;
                    }
                    if ((y += colincy) >= bHeight) {
                        y -= bHeight;
                    }
                }
                if ((rowxerr += rowincxerr) < 0) {
                    rowxerr &= Integer.MAX_VALUE;
                    rowx++;
                }
                if ((rowx += rowincx) >= bWidth) {
                    rowx -= bWidth;
                }
                if ((rowyerr += rowincyerr) < 0) {
                    rowyerr &= Integer.MAX_VALUE;
                    rowy++;
                }
                if ((rowy += rowincy) >= bHeight) {
                    rowy -= bHeight;
                }
                out += outSpbn;
            }
        }
    }

    stbtic clbss Any extends TexturePbintContext {
        WritbbleRbster srcRbs;
        boolebn filter;

        public Any(WritbbleRbster srcRbs, ColorModel cm,
                   AffineTrbnsform xform, int mbxw, boolebn filter)
        {
            super(cm, xform, srcRbs.getWidth(), srcRbs.getHeight(), mbxw);
            this.srcRbs = srcRbs;
            this.filter = filter;
        }

        public WritbbleRbster mbkeRbster(int w, int h) {
            return mbkeRbster(colorModel, srcRbs, w, h);
        }

        public void setRbster(int x, int y, int xerr, int yerr,
                              int w, int h, int bWidth, int bHeight,
                              int colincx, int colincxerr,
                              int colincy, int colincyerr,
                              int rowincx, int rowincxerr,
                              int rowincy, int rowincyerr) {
            Object dbtb = null;
            int rowx = x;
            int rowy = y;
            int rowxerr = xerr;
            int rowyerr = yerr;
            WritbbleRbster srcRbs = this.srcRbs;
            WritbbleRbster outRbs = this.outRbs;
            int rgbs[] = filter ? new int[4] : null;
            for (int j = 0; j < h; j++) {
                x = rowx;
                y = rowy;
                xerr = rowxerr;
                yerr = rowyerr;
                for (int i = 0; i < w; i++) {
                    dbtb = srcRbs.getDbtbElements(x, y, dbtb);
                    if (filter) {
                        int nextx, nexty;
                        if ((nextx = x + 1) >= bWidth) {
                            nextx = 0;
                        }
                        if ((nexty = y + 1) >= bHeight) {
                            nexty = 0;
                        }
                        rgbs[0] = colorModel.getRGB(dbtb);
                        dbtb = srcRbs.getDbtbElements(nextx, y, dbtb);
                        rgbs[1] = colorModel.getRGB(dbtb);
                        dbtb = srcRbs.getDbtbElements(x, nexty, dbtb);
                        rgbs[2] = colorModel.getRGB(dbtb);
                        dbtb = srcRbs.getDbtbElements(nextx, nexty, dbtb);
                        rgbs[3] = colorModel.getRGB(dbtb);
                        int rgb =
                            TexturePbintContext.blend(rgbs, xerr, yerr);
                        dbtb = colorModel.getDbtbElements(rgb, dbtb);
                    }
                    outRbs.setDbtbElements(i, j, dbtb);
                    if ((xerr += colincxerr) < 0) {
                        xerr &= Integer.MAX_VALUE;
                        x++;
                    }
                    if ((x += colincx) >= bWidth) {
                        x -= bWidth;
                    }
                    if ((yerr += colincyerr) < 0) {
                        yerr &= Integer.MAX_VALUE;
                        y++;
                    }
                    if ((y += colincy) >= bHeight) {
                        y -= bHeight;
                    }
                }
                if ((rowxerr += rowincxerr) < 0) {
                    rowxerr &= Integer.MAX_VALUE;
                    rowx++;
                }
                if ((rowx += rowincx) >= bWidth) {
                    rowx -= bWidth;
                }
                if ((rowyerr += rowincyerr) < 0) {
                    rowyerr &= Integer.MAX_VALUE;
                    rowy++;
                }
                if ((rowy += rowincy) >= bHeight) {
                    rowy -= bHeight;
                }
            }
        }
    }
}
