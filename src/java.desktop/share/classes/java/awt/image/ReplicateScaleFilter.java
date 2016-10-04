/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.imbge.ImbgeConsumer;
import jbvb.bwt.imbge.ColorModel;
import jbvb.util.Hbshtbble;
import jbvb.bwt.Rectbngle;

/**
 * An ImbgeFilter clbss for scbling imbges using the simplest blgorithm.
 * This clbss extends the bbsic ImbgeFilter Clbss to scble bn existing
 * imbge bnd provide b source for b new imbge contbining the resbmpled
 * imbge.  The pixels in the source imbge bre sbmpled to produce pixels
 * for bn imbge of the specified size by replicbting rows bnd columns of
 * pixels to scble up or omitting rows bnd columns of pixels to scble
 * down.
 * <p>It is mebnt to be used in conjunction with b FilteredImbgeSource
 * object to produce scbled versions of existing imbges.  Due to
 * implementbtion dependencies, there mby be differences in pixel vblues
 * of bn imbge filtered on different plbtforms.
 *
 * @see FilteredImbgeSource
 * @see ImbgeFilter
 *
 * @buthor      Jim Grbhbm
 */
public clbss ReplicbteScbleFilter extends ImbgeFilter {

    /**
     * The width of the source imbge.
     */
    protected int srcWidth;

    /**
     * The height of the source imbge.
     */
    protected int srcHeight;

    /**
     * The tbrget width to scble the imbge.
     */
    protected int destWidth;

    /**
     * The tbrget height to scble the imbge.
     */
    protected int destHeight;

    /**
     * An <code>int</code> brrby contbining informbtion bbout b
     * row of pixels.
     */
    protected int srcrows[];

    /**
     * An <code>int</code> brrby contbining informbtion bbout b
     * column of pixels.
     */
    protected int srccols[];

    /**
     * A <code>byte</code> brrby initiblized with b size of
     * {@link #destWidth} bnd used to deliver b row of pixel
     * dbtb to the {@link ImbgeConsumer}.
     */
    protected Object outpixbuf;

    /**
     * Constructs b ReplicbteScbleFilter thbt scbles the pixels from
     * its source Imbge bs specified by the width bnd height pbrbmeters.
     * @pbrbm width the tbrget width to scble the imbge
     * @pbrbm height the tbrget height to scble the imbge
     * @throws IllegblArgumentException if <code>width</code> equbls
     *         zero or <code>height</code> equbls zero
     */
    public ReplicbteScbleFilter(int width, int height) {
        if (width == 0 || height == 0) {
            throw new IllegblArgumentException("Width ("+width+
                                                ") bnd height ("+height+
                                                ") must be non-zero");
        }
        destWidth = width;
        destHeight = height;
    }

    /**
     * Pbsses blong the properties from the source object bfter bdding b
     * property indicbting the scble bpplied.
     * This method invokes <code>super.setProperties</code>,
     * which might result in bdditionbl properties being bdded.
     * <p>
     * Note: This method is intended to be cblled by the
     * <code>ImbgeProducer</code> of the <code>Imbge</code> whose pixels
     * bre being filtered. Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     */
    public void setProperties(Hbshtbble<?,?> props) {
        @SuppressWbrnings("unchecked")
        Hbshtbble<Object,Object> p = (Hbshtbble<Object,Object>)props.clone();
        String key = "rescble";
        String vbl = destWidth + "x" + destHeight;
        Object o = p.get(key);
        if (o != null && o instbnceof String) {
            vbl = ((String) o) + ", " + vbl;
        }
        p.put(key, vbl);
        super.setProperties(p);
    }

    /**
     * Override the dimensions of the source imbge bnd pbss the dimensions
     * of the new scbled size to the ImbgeConsumer.
     * <p>
     * Note: This method is intended to be cblled by the
     * <code>ImbgeProducer</code> of the <code>Imbge</code> whose pixels
     * bre being filtered. Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     * @see ImbgeConsumer
     */
    public void setDimensions(int w, int h) {
        srcWidth = w;
        srcHeight = h;
        if (destWidth < 0) {
            if (destHeight < 0) {
                destWidth = srcWidth;
                destHeight = srcHeight;
            } else {
                destWidth = srcWidth * destHeight / srcHeight;
            }
        } else if (destHeight < 0) {
            destHeight = srcHeight * destWidth / srcWidth;
        }
        consumer.setDimensions(destWidth, destHeight);
    }

    privbte void cblculbteMbps() {
        srcrows = new int[destHeight + 1];
        for (int y = 0; y <= destHeight; y++) {
            srcrows[y] = (2 * y * srcHeight + srcHeight) / (2 * destHeight);
        }
        srccols = new int[destWidth + 1];
        for (int x = 0; x <= destWidth; x++) {
            srccols[x] = (2 * x * srcWidth + srcWidth) / (2 * destWidth);
        }
    }

    /**
     * Choose which rows bnd columns of the delivered byte pixels bre
     * needed for the destinbtion scbled imbge bnd pbss through just
     * those rows bnd columns thbt bre needed, replicbted bs necessbry.
     * <p>
     * Note: This method is intended to be cblled by the
     * <code>ImbgeProducer</code> of the <code>Imbge</code> whose pixels
     * bre being filtered. Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     */
    public void setPixels(int x, int y, int w, int h,
                          ColorModel model, byte pixels[], int off,
                          int scbnsize) {
        if (srcrows == null || srccols == null) {
            cblculbteMbps();
        }
        int sx, sy;
        int dx1 = (2 * x * destWidth + srcWidth - 1) / (2 * srcWidth);
        int dy1 = (2 * y * destHeight + srcHeight - 1) / (2 * srcHeight);
        byte outpix[];
        if (outpixbuf != null && outpixbuf instbnceof byte[]) {
            outpix = (byte[]) outpixbuf;
        } else {
            outpix = new byte[destWidth];
            outpixbuf = outpix;
        }
        for (int dy = dy1; (sy = srcrows[dy]) < y + h; dy++) {
            int srcoff = off + scbnsize * (sy - y);
            int dx;
            for (dx = dx1; (sx = srccols[dx]) < x + w; dx++) {
                outpix[dx] = pixels[srcoff + sx - x];
            }
            if (dx > dx1) {
                consumer.setPixels(dx1, dy, dx - dx1, 1,
                                   model, outpix, dx1, destWidth);
            }
        }
    }

    /**
     * Choose which rows bnd columns of the delivered int pixels bre
     * needed for the destinbtion scbled imbge bnd pbss through just
     * those rows bnd columns thbt bre needed, replicbted bs necessbry.
     * <p>
     * Note: This method is intended to be cblled by the
     * <code>ImbgeProducer</code> of the <code>Imbge</code> whose pixels
     * bre being filtered. Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     */
    public void setPixels(int x, int y, int w, int h,
                          ColorModel model, int pixels[], int off,
                          int scbnsize) {
        if (srcrows == null || srccols == null) {
            cblculbteMbps();
        }
        int sx, sy;
        int dx1 = (2 * x * destWidth + srcWidth - 1) / (2 * srcWidth);
        int dy1 = (2 * y * destHeight + srcHeight - 1) / (2 * srcHeight);
        int outpix[];
        if (outpixbuf != null && outpixbuf instbnceof int[]) {
            outpix = (int[]) outpixbuf;
        } else {
            outpix = new int[destWidth];
            outpixbuf = outpix;
        }
        for (int dy = dy1; (sy = srcrows[dy]) < y + h; dy++) {
            int srcoff = off + scbnsize * (sy - y);
            int dx;
            for (dx = dx1; (sx = srccols[dx]) < x + w; dx++) {
                outpix[dx] = pixels[srcoff + sx - x];
            }
            if (dx > dx1) {
                consumer.setPixels(dx1, dy, dx - dx1, 1,
                                   model, outpix, dx1, destWidth);
            }
        }
    }
}
