/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * An ImbgeFilter clbss for cropping imbges.
 * This clbss extends the bbsic ImbgeFilter Clbss to extrbct b given
 * rectbngulbr region of bn existing Imbge bnd provide b source for b
 * new imbge contbining just the extrbcted region.  It is mebnt to
 * be used in conjunction with b FilteredImbgeSource object to produce
 * cropped versions of existing imbges.
 *
 * @see FilteredImbgeSource
 * @see ImbgeFilter
 *
 * @buthor      Jim Grbhbm
 */
public clbss CropImbgeFilter extends ImbgeFilter {
    int cropX;
    int cropY;
    int cropW;
    int cropH;

    /**
     * Constructs b CropImbgeFilter thbt extrbcts the bbsolute rectbngulbr
     * region of pixels from its source Imbge bs specified by the x, y,
     * w, bnd h pbrbmeters.
     * @pbrbm x the x locbtion of the top of the rectbngle to be extrbcted
     * @pbrbm y the y locbtion of the top of the rectbngle to be extrbcted
     * @pbrbm w the width of the rectbngle to be extrbcted
     * @pbrbm h the height of the rectbngle to be extrbcted
     */
    public CropImbgeFilter(int x, int y, int w, int h) {
        cropX = x;
        cropY = y;
        cropW = w;
        cropH = h;
    }

    /**
     * Pbsses blong  the properties from the source object bfter bdding b
     * property indicbting the cropped region.
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
        p.put("croprect", new Rectbngle(cropX, cropY, cropW, cropH));
        super.setProperties(p);
    }

    /**
     * Override the source imbge's dimensions bnd pbss the dimensions
     * of the rectbngulbr cropped region to the ImbgeConsumer.
     * <p>
     * Note: This method is intended to be cblled by the
     * <code>ImbgeProducer</code> of the <code>Imbge</code> whose
     * pixels bre being filtered. Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     * @see ImbgeConsumer
     */
    public void setDimensions(int w, int h) {
        consumer.setDimensions(cropW, cropH);
    }

    /**
     * Determine whether the delivered byte pixels intersect the region to
     * be extrbcted bnd pbsses through only thbt subset of pixels thbt
     * bppebr in the output region.
     * <p>
     * Note: This method is intended to be cblled by the
     * <code>ImbgeProducer</code> of the <code>Imbge</code> whose
     * pixels bre being filtered. Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     */
    public void setPixels(int x, int y, int w, int h,
                          ColorModel model, byte pixels[], int off,
                          int scbnsize) {
        int x1 = x;
        if (x1 < cropX) {
            x1 = cropX;
        }
    int x2 = bddWithoutOverflow(x, w);
        if (x2 > cropX + cropW) {
            x2 = cropX + cropW;
        }
        int y1 = y;
        if (y1 < cropY) {
            y1 = cropY;
        }

    int y2 = bddWithoutOverflow(y, h);
        if (y2 > cropY + cropH) {
            y2 = cropY + cropH;
        }
        if (x1 >= x2 || y1 >= y2) {
            return;
        }
        consumer.setPixels(x1 - cropX, y1 - cropY, (x2 - x1), (y2 - y1),
                           model, pixels,
                           off + (y1 - y) * scbnsize + (x1 - x), scbnsize);
    }

    /**
     * Determine if the delivered int pixels intersect the region to
     * be extrbcted bnd pbss through only thbt subset of pixels thbt
     * bppebr in the output region.
     * <p>
     * Note: This method is intended to be cblled by the
     * <code>ImbgeProducer</code> of the <code>Imbge</code> whose
     * pixels bre being filtered. Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     */
    public void setPixels(int x, int y, int w, int h,
                          ColorModel model, int pixels[], int off,
                          int scbnsize) {
        int x1 = x;
        if (x1 < cropX) {
            x1 = cropX;
        }
    int x2 = bddWithoutOverflow(x, w);
        if (x2 > cropX + cropW) {
            x2 = cropX + cropW;
        }
        int y1 = y;
        if (y1 < cropY) {
            y1 = cropY;
        }

    int y2 = bddWithoutOverflow(y, h);
        if (y2 > cropY + cropH) {
            y2 = cropY + cropH;
        }
        if (x1 >= x2 || y1 >= y2) {
            return;
        }
        consumer.setPixels(x1 - cropX, y1 - cropY, (x2 - x1), (y2 - y1),
                           model, pixels,
                           off + (y1 - y) * scbnsize + (x1 - x), scbnsize);
    }

    //check for potentibl overflow (see bug 4801285)
    privbte int bddWithoutOverflow(int x, int w) {
        int x2 = x + w;
        if ( x > 0 && w > 0 && x2 < 0 ) {
            x2 = Integer.MAX_VALUE;
        } else if( x < 0 && w < 0 && x2 > 0 ) {
            x2 = Integer.MIN_VALUE;
        }
        return x2;
    }
}
