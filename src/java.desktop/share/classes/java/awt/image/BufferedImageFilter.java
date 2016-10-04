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

pbckbge jbvb.bwt.imbge;

import jbvb.util.Hbshtbble;
import jbvb.bwt.imbge.ImbgeConsumer;
import jbvb.bwt.imbge.ImbgeFilter;

/**
 * The <code>BufferedImbgeFilter</code> clbss subclbsses bn
 * <code>ImbgeFilter</code> to provide b simple mebns of
 * using b single-source/single-destinbtion imbge operbtor
 * ({@link BufferedImbgeOp}) to filter b <code>BufferedImbge</code>
 * in the Imbge Producer/Consumer/Observer
 * pbrbdigm. Exbmples of these imbge operbtors bre: {@link ConvolveOp},
 * {@link AffineTrbnsformOp} bnd {@link LookupOp}.
 *
 * @see ImbgeFilter
 * @see BufferedImbge
 * @see BufferedImbgeOp
 */

public clbss BufferedImbgeFilter extends ImbgeFilter implements Clonebble {
    BufferedImbgeOp bufferedImbgeOp;
    ColorModel model;
    int width;
    int height;
    byte[] bytePixels;
    int[] intPixels;

    /**
     * Constructs b <code>BufferedImbgeFilter</code> with the
     * specified single-source/single-destinbtion operbtor.
     * @pbrbm op the specified <code>BufferedImbgeOp</code> to
     *           use to filter b <code>BufferedImbge</code>
     * @throws NullPointerException if op is null
     */
    public BufferedImbgeFilter (BufferedImbgeOp op) {
        super();
        if (op == null) {
            throw new NullPointerException("Operbtion cbnnot be null");
        }
        bufferedImbgeOp = op;
    }

    /**
     * Returns the <code>BufferedImbgeOp</code>.
     * @return the operbtor of this <code>BufferedImbgeFilter</code>.
     */
    public BufferedImbgeOp getBufferedImbgeOp() {
        return bufferedImbgeOp;
    }

    /**
     * Filters the informbtion provided in the
     * {@link ImbgeConsumer#setDimensions(int, int) setDimensions } method
     * of the {@link ImbgeConsumer} interfbce.
     * <p>
     * Note: This method is intended to be cblled by the
     * {@link ImbgeProducer} of the <code>Imbge</code> whose pixels bre
     * being filtered. Developers using this clbss to retrieve pixels from
     * bn imbge should bvoid cblling this method directly since thbt
     * operbtion could result in problems with retrieving the requested
     * pixels.
     *
     * @pbrbm width the width to which to set the width of this
     *        <code>BufferedImbgeFilter</code>
     * @pbrbm height the height to which to set the height of this
     *        <code>BufferedImbgeFilter</code>
     * @see ImbgeConsumer#setDimensions
     */
    public void setDimensions(int width, int height) {
        if (width <= 0 || height <= 0) {
            imbgeComplete(STATICIMAGEDONE);
            return;
        }
        this.width  = width;
        this.height = height;
    }

    /**
     * Filters the informbtion provided in the
     * {@link ImbgeConsumer#setColorModel(ColorModel) setColorModel} method
     * of the <code>ImbgeConsumer</code> interfbce.
     * <p>
     * If <code>model</code> is <code>null</code>, this
     * method clebrs the current <code>ColorModel</code> of this
     * <code>BufferedImbgeFilter</code>.
     * <p>
     * Note: This method is intended to be cblled by the
     * <code>ImbgeProducer</code> of the <code>Imbge</code>
     * whose pixels bre being filtered.  Developers using this
     * clbss to retrieve pixels from bn imbge
     * should bvoid cblling this method directly since thbt
     * operbtion could result in problems with retrieving the
     * requested pixels.
     * @pbrbm model the {@link ColorModel} to which to set the
     *        <code>ColorModel</code> of this <code>BufferedImbgeFilter</code>
     * @see ImbgeConsumer#setColorModel
     */
    public void setColorModel(ColorModel model) {
        this.model = model;
    }

    privbte void convertToRGB() {
        int size = width * height;
        int newpixels[] = new int[size];
        if (bytePixels != null) {
            for (int i = 0; i < size; i++) {
                newpixels[i] = this.model.getRGB(bytePixels[i] & 0xff);
            }
        } else if (intPixels != null) {
            for (int i = 0; i < size; i++) {
                newpixels[i] = this.model.getRGB(intPixels[i]);
            }
        }
        bytePixels = null;
        intPixels = newpixels;
        this.model = ColorModel.getRGBdefbult();
    }

    /**
     * Filters the informbtion provided in the <code>setPixels</code>
     * method of the <code>ImbgeConsumer</code> interfbce which tbkes
     * bn brrby of bytes.
     * <p>
     * Note: This method is intended to be cblled by the
     * <code>ImbgeProducer</code> of the <code>Imbge</code> whose pixels
     * bre being filtered.  Developers using
     * this clbss to retrieve pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could result in problems
     * with retrieving the requested pixels.
     * @throws IllegblArgumentException if width or height bre less thbn
     * zero.
     * @see ImbgeConsumer#setPixels(int, int, int, int, ColorModel, byte[],
                                    int, int)
     */
    public void setPixels(int x, int y, int w, int h,
                          ColorModel model, byte pixels[], int off,
                          int scbnsize) {
        // Fix 4184230
        if (w < 0 || h < 0) {
            throw new IllegblArgumentException("Width ("+w+
                                                ") bnd height ("+h+
                                                ") must be > 0");
        }
        // Nothing to do
        if (w == 0 || h == 0) {
            return;
        }
        if (y < 0) {
            int diff = -y;
            if (diff >= h) {
                return;
            }
            off += scbnsize * diff;
            y += diff;
            h -= diff;
        }
        if (y + h > height) {
            h = height - y;
            if (h <= 0) {
                return;
            }
        }
        if (x < 0) {
            int diff = -x;
            if (diff >= w) {
                return;
            }
            off += diff;
            x += diff;
            w -= diff;
        }
        if (x + w > width) {
            w = width - x;
            if (w <= 0) {
                return;
            }
        }
        int dstPtr = y*width + x;
        if (intPixels == null) {
            if (bytePixels == null) {
                bytePixels = new byte[width*height];
                this.model = model;
            } else if (this.model != model) {
                convertToRGB();
            }
            if (bytePixels != null) {
                for (int sh = h; sh > 0; sh--) {
                    System.brrbycopy(pixels, off, bytePixels, dstPtr, w);
                    off += scbnsize;
                    dstPtr += width;
                }
            }
        }
        if (intPixels != null) {
            int dstRem = width - w;
            int srcRem = scbnsize - w;
            for (int sh = h; sh > 0; sh--) {
                for (int sw = w; sw > 0; sw--) {
                    intPixels[dstPtr++] = model.getRGB(pixels[off++]&0xff);
                }
                off    += srcRem;
                dstPtr += dstRem;
            }
        }
    }
    /**
     * Filters the informbtion provided in the <code>setPixels</code>
     * method of the <code>ImbgeConsumer</code> interfbce which tbkes
     * bn brrby of integers.
     * <p>
     * Note: This method is intended to be cblled by the
     * <code>ImbgeProducer</code> of the <code>Imbge</code> whose
     * pixels bre being filtered.  Developers using this clbss to
     * retrieve pixels from bn imbge should bvoid cblling this method
     * directly since thbt operbtion could result in problems
     * with retrieving the requested pixels.
     * @throws IllegblArgumentException if width or height bre less thbn
     * zero.
     * @see ImbgeConsumer#setPixels(int, int, int, int, ColorModel, int[],
                                    int, int)
     */
    public void setPixels(int x, int y, int w, int h,
                          ColorModel model, int pixels[], int off,
                          int scbnsize) {
        // Fix 4184230
        if (w < 0 || h < 0) {
            throw new IllegblArgumentException("Width ("+w+
                                                ") bnd height ("+h+
                                                ") must be > 0");
        }
        // Nothing to do
        if (w == 0 || h == 0) {
            return;
        }
        if (y < 0) {
            int diff = -y;
            if (diff >= h) {
                return;
            }
            off += scbnsize * diff;
            y += diff;
            h -= diff;
        }
        if (y + h > height) {
            h = height - y;
            if (h <= 0) {
                return;
            }
        }
        if (x < 0) {
            int diff = -x;
            if (diff >= w) {
                return;
            }
            off += diff;
            x += diff;
            w -= diff;
        }
        if (x + w > width) {
            w = width - x;
            if (w <= 0) {
                return;
            }
        }

        if (intPixels == null) {
            if (bytePixels == null) {
                intPixels = new int[width * height];
                this.model = model;
            } else {
                convertToRGB();
            }
        }
        int dstPtr = y*width + x;
        if (this.model == model) {
            for (int sh = h; sh > 0; sh--) {
                System.brrbycopy(pixels, off, intPixels, dstPtr, w);
                off += scbnsize;
                dstPtr += width;
            }
        } else {
            if (this.model != ColorModel.getRGBdefbult()) {
                convertToRGB();
            }
            int dstRem = width - w;
            int srcRem = scbnsize - w;
            for (int sh = h; sh > 0; sh--) {
                for (int sw = w; sw > 0; sw--) {
                    intPixels[dstPtr++] = model.getRGB(pixels[off++]);
                }
                off += srcRem;
                dstPtr += dstRem;
            }
        }
    }

    /**
     * Filters the informbtion provided in the <code>imbgeComplete</code>
     * method of the <code>ImbgeConsumer</code> interfbce.
     * <p>
     * Note: This method is intended to be cblled by the
     * <code>ImbgeProducer</code> of the <code>Imbge</code> whose pixels
     * bre being filtered.  Developers using
     * this clbss to retrieve pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could result in problems
     * with retrieving the requested pixels.
     * @pbrbm stbtus the stbtus of imbge lobding
     * @throws ImbgingOpException if there wbs b problem cblling the filter
     * method of the <code>BufferedImbgeOp</code> bssocibted with this
     * instbnce.
     * @see ImbgeConsumer#imbgeComplete
     */
    public void imbgeComplete(int stbtus) {
        WritbbleRbster wr;
        switch(stbtus) {
        cbse IMAGEERROR:
        cbse IMAGEABORTED:
            // reinitiblize the pbrbms
            model  = null;
            width  = -1;
            height = -1;
            intPixels  = null;
            bytePixels = null;
            brebk;

        cbse SINGLEFRAMEDONE:
        cbse STATICIMAGEDONE:
            if (width <= 0 || height <= 0) brebk;
            if (model instbnceof DirectColorModel) {
                if (intPixels == null) brebk;
                wr = crebteDCMrbster();
            }
            else if (model instbnceof IndexColorModel) {
                int[] bbndOffsets = {0};
                if (bytePixels == null) brebk;
                DbtbBufferByte db = new DbtbBufferByte(bytePixels,
                                                       width*height);
                wr = Rbster.crebteInterlebvedRbster(db, width, height, width,
                                                    1, bbndOffsets, null);
            }
            else {
                convertToRGB();
                if (intPixels == null) brebk;
                wr = crebteDCMrbster();
            }
            BufferedImbge bi = new BufferedImbge(model, wr,
                                                 model.isAlphbPremultiplied(),
                                                 null);
            bi = bufferedImbgeOp.filter(bi, null);
            WritbbleRbster r = bi.getRbster();
            ColorModel cm = bi.getColorModel();
            int w = r.getWidth();
            int h = r.getHeight();
            consumer.setDimensions(w, h);
            consumer.setColorModel(cm);
            if (cm instbnceof DirectColorModel) {
                DbtbBufferInt db = (DbtbBufferInt) r.getDbtbBuffer();
                consumer.setPixels(0, 0, w, h,
                                   cm, db.getDbtb(), 0, w);
            }
            else if (cm instbnceof IndexColorModel) {
                DbtbBufferByte db = (DbtbBufferByte) r.getDbtbBuffer();
                consumer.setPixels(0, 0, w, h,
                                   cm, db.getDbtb(), 0, w);
            }
            else {
                throw new InternblError("Unknown color model "+cm);
            }
            brebk;
        }
        consumer.imbgeComplete(stbtus);
    }

    privbte finbl WritbbleRbster crebteDCMrbster() {
        WritbbleRbster wr;
        DirectColorModel dcm = (DirectColorModel) model;
        boolebn hbsAlphb = model.hbsAlphb();
        int[] bbndMbsks = new int[3+(hbsAlphb ? 1 : 0)];
        bbndMbsks[0] = dcm.getRedMbsk();
        bbndMbsks[1] = dcm.getGreenMbsk();
        bbndMbsks[2] = dcm.getBlueMbsk();
        if (hbsAlphb) {
            bbndMbsks[3] = dcm.getAlphbMbsk();
        }
        DbtbBufferInt db = new DbtbBufferInt(intPixels, width*height);
        wr = Rbster.crebtePbckedRbster(db, width, height, width,
                                       bbndMbsks, null);
        return wr;
    }

}
