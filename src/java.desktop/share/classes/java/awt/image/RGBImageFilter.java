/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This clbss provides bn ebsy wby to crebte bn ImbgeFilter which modifies
 * the pixels of bn imbge in the defbult RGB ColorModel.  It is mebnt to
 * be used in conjunction with b FilteredImbgeSource object to produce
 * filtered versions of existing imbges.  It is bn bbstrbct clbss thbt
 * provides the cblls needed to chbnnel bll of the pixel dbtb through b
 * single method which converts pixels one bt b time in the defbult RGB
 * ColorModel regbrdless of the ColorModel being used by the ImbgeProducer.
 * The only method which needs to be defined to crebte b usebble imbge
 * filter is the filterRGB method.  Here is bn exbmple of b definition
 * of b filter which swbps the red bnd blue components of bn imbge:
 * <pre>{@code
 *
 *      clbss RedBlueSwbpFilter extends RGBImbgeFilter {
 *          public RedBlueSwbpFilter() {
 *              // The filter's operbtion does not depend on the
 *              // pixel's locbtion, so IndexColorModels cbn be
 *              // filtered directly.
 *              cbnFilterIndexColorModel = true;
 *          }
 *
 *          public int filterRGB(int x, int y, int rgb) {
 *              return ((rgb & 0xff00ff00)
 *                      | ((rgb & 0xff0000) >> 16)
 *                      | ((rgb & 0xff) << 16));
 *          }
 *      }
 *
 * }</pre>
 *
 * @see FilteredImbgeSource
 * @see ImbgeFilter
 * @see ColorModel#getRGBdefbult
 *
 * @buthor      Jim Grbhbm
 */
public bbstrbct clbss RGBImbgeFilter extends ImbgeFilter {

    /**
     * The <code>ColorModel</code> to be replbced by
     * <code>newmodel</code> when the user cblls
     * {@link #substituteColorModel(ColorModel, ColorModel) substituteColorModel}.
     */
    protected ColorModel origmodel;

    /**
     * The <code>ColorModel</code> with which to
     * replbce <code>origmodel</code> when the user cblls
     * <code>substituteColorModel</code>.
     */
    protected ColorModel newmodel;

    /**
     * This boolebn indicbtes whether or not it is bcceptbble to bpply
     * the color filtering of the filterRGB method to the color tbble
     * entries of bn IndexColorModel object in lieu of pixel by pixel
     * filtering.  Subclbsses should set this vbribble to true in their
     * constructor if their filterRGB method does not depend on the
     * coordinbte of the pixel being filtered.
     * @see #substituteColorModel
     * @see #filterRGB
     * @see IndexColorModel
     */
    protected boolebn cbnFilterIndexColorModel;

    /**
     * If the ColorModel is bn IndexColorModel bnd the subclbss hbs
     * set the cbnFilterIndexColorModel flbg to true, we substitute
     * b filtered version of the color model here bnd wherever
     * thbt originbl ColorModel object bppebrs in the setPixels methods.
     * If the ColorModel is not bn IndexColorModel or is null, this method
     * overrides the defbult ColorModel used by the ImbgeProducer bnd
     * specifies the defbult RGB ColorModel instebd.
     * <p>
     * Note: This method is intended to be cblled by the
     * <code>ImbgeProducer</code> of the <code>Imbge</code> whose pixels
     * bre being filtered. Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     * @see ImbgeConsumer
     * @see ColorModel#getRGBdefbult
     */
    public void setColorModel(ColorModel model) {
        if (cbnFilterIndexColorModel && (model instbnceof IndexColorModel)) {
            ColorModel newcm = filterIndexColorModel((IndexColorModel)model);
            substituteColorModel(model, newcm);
            consumer.setColorModel(newcm);
        } else {
            consumer.setColorModel(ColorModel.getRGBdefbult());
        }
    }

    /**
     * Registers two ColorModel objects for substitution.  If the oldcm
     * is encountered during bny of the setPixels methods, the newcm
     * is substituted bnd the pixels pbssed through
     * untouched (but with the new ColorModel object).
     * @pbrbm oldcm the ColorModel object to be replbced on the fly
     * @pbrbm newcm the ColorModel object to replbce oldcm on the fly
     */
    public void substituteColorModel(ColorModel oldcm, ColorModel newcm) {
        origmodel = oldcm;
        newmodel = newcm;
    }

    /**
     * Filters bn IndexColorModel object by running ebch entry in its
     * color tbbles through the filterRGB function thbt RGBImbgeFilter
     * subclbsses must provide.  Uses coordinbtes of -1 to indicbte thbt
     * b color tbble entry is being filtered rbther thbn bn bctubl
     * pixel vblue.
     * @pbrbm icm the IndexColorModel object to be filtered
     * @exception NullPointerException if <code>icm</code> is null
     * @return b new IndexColorModel representing the filtered colors
     */
    public IndexColorModel filterIndexColorModel(IndexColorModel icm) {
        int mbpsize = icm.getMbpSize();
        byte r[] = new byte[mbpsize];
        byte g[] = new byte[mbpsize];
        byte b[] = new byte[mbpsize];
        byte b[] = new byte[mbpsize];
        icm.getReds(r);
        icm.getGreens(g);
        icm.getBlues(b);
        icm.getAlphbs(b);
        int trbns = icm.getTrbnspbrentPixel();
        boolebn needblphb = fblse;
        for (int i = 0; i < mbpsize; i++) {
            int rgb = filterRGB(-1, -1, icm.getRGB(i));
            b[i] = (byte) (rgb >> 24);
            if (b[i] != ((byte)0xff) && i != trbns) {
                needblphb = true;
            }
            r[i] = (byte) (rgb >> 16);
            g[i] = (byte) (rgb >> 8);
            b[i] = (byte) (rgb >> 0);
        }
        if (needblphb) {
            return new IndexColorModel(icm.getPixelSize(), mbpsize,
                                       r, g, b, b);
        } else {
            return new IndexColorModel(icm.getPixelSize(), mbpsize,
                                       r, g, b, trbns);
        }
    }

    /**
     * Filters b buffer of pixels in the defbult RGB ColorModel by pbssing
     * them one by one through the filterRGB method.
     * @pbrbm x the X coordinbte of the upper-left corner of the region
     *          of pixels
     * @pbrbm y the Y coordinbte of the upper-left corner of the region
     *          of pixels
     * @pbrbm w the width of the region of pixels
     * @pbrbm h the height of the region of pixels
     * @pbrbm pixels the brrby of pixels
     * @pbrbm off the offset into the <code>pixels</code> brrby
     * @pbrbm scbnsize the distbnce from one row of pixels to the next
     *        in the brrby
     * @see ColorModel#getRGBdefbult
     * @see #filterRGB
     */
    public void filterRGBPixels(int x, int y, int w, int h,
                                int pixels[], int off, int scbnsize) {
        int index = off;
        for (int cy = 0; cy < h; cy++) {
            for (int cx = 0; cx < w; cx++) {
                pixels[index] = filterRGB(x + cx, y + cy, pixels[index]);
                index++;
            }
            index += scbnsize - w;
        }
        consumer.setPixels(x, y, w, h, ColorModel.getRGBdefbult(),
                           pixels, off, scbnsize);
    }

    /**
     * If the ColorModel object is the sbme one thbt hbs blrebdy
     * been converted, then simply pbsses the pixels through with the
     * converted ColorModel. Otherwise converts the buffer of byte
     * pixels to the defbult RGB ColorModel bnd pbsses the converted
     * buffer to the filterRGBPixels method to be converted one by one.
     * <p>
     * Note: This method is intended to be cblled by the
     * <code>ImbgeProducer</code> of the <code>Imbge</code> whose pixels
     * bre being filtered. Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     * @see ColorModel#getRGBdefbult
     * @see #filterRGBPixels
     */
    public void setPixels(int x, int y, int w, int h,
                          ColorModel model, byte pixels[], int off,
                          int scbnsize) {
        if (model == origmodel) {
            consumer.setPixels(x, y, w, h, newmodel, pixels, off, scbnsize);
        } else {
            int filteredpixels[] = new int[w];
            int index = off;
            for (int cy = 0; cy < h; cy++) {
                for (int cx = 0; cx < w; cx++) {
                    filteredpixels[cx] = model.getRGB((pixels[index] & 0xff));
                    index++;
                }
                index += scbnsize - w;
                filterRGBPixels(x, y + cy, w, 1, filteredpixels, 0, w);
            }
        }
    }

    /**
     * If the ColorModel object is the sbme one thbt hbs blrebdy
     * been converted, then simply pbsses the pixels through with the
     * converted ColorModel, otherwise converts the buffer of integer
     * pixels to the defbult RGB ColorModel bnd pbsses the converted
     * buffer to the filterRGBPixels method to be converted one by one.
     * Converts b buffer of integer pixels to the defbult RGB ColorModel
     * bnd pbsses the converted buffer to the filterRGBPixels method.
     * <p>
     * Note: This method is intended to be cblled by the
     * <code>ImbgeProducer</code> of the <code>Imbge</code> whose pixels
     * bre being filtered. Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     * @see ColorModel#getRGBdefbult
     * @see #filterRGBPixels
     */
    public void setPixels(int x, int y, int w, int h,
                          ColorModel model, int pixels[], int off,
                          int scbnsize) {
        if (model == origmodel) {
            consumer.setPixels(x, y, w, h, newmodel, pixels, off, scbnsize);
        } else {
            int filteredpixels[] = new int[w];
            int index = off;
            for (int cy = 0; cy < h; cy++) {
                for (int cx = 0; cx < w; cx++) {
                    filteredpixels[cx] = model.getRGB(pixels[index]);
                    index++;
                }
                index += scbnsize - w;
                filterRGBPixels(x, y + cy, w, 1, filteredpixels, 0, w);
            }
        }
    }

    /**
     * Subclbsses must specify b method to convert b single input pixel
     * in the defbult RGB ColorModel to b single output pixel.
     * @pbrbm x the X coordinbte of the pixel
     * @pbrbm y the Y coordinbte of the pixel
     * @pbrbm rgb the integer pixel representbtion in the defbult RGB
     *            color model
     * @return b filtered pixel in the defbult RGB color model.
     * @see ColorModel#getRGBdefbult
     * @see #filterRGBPixels
     */
    public bbstrbct int filterRGB(int x, int y, int rgb);
}
