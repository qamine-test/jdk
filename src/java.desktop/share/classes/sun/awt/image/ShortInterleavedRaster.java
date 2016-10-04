/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.imbge;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.imbge.RbsterFormbtException;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.bwt.imbge.ComponentSbmpleModel;
import jbvb.bwt.imbge.PixelInterlebvedSbmpleModel;
import jbvb.bwt.imbge.SinglePixelPbckedSbmpleModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DbtbBufferUShort;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Point;

/**
 * This clbss defines b Rbster with pixels consisting of one or more 16-bit
 * dbtb elements stored in close proximity to ebch other in b short integer
 * brrby.  The bit precision per dbtb element is thbt
 * of the dbtb type (thbt is, the bit precision for this Rbster is 16).
 * There is only one pixel stride bnd one scbnline stride for bll
 * bbnds.  This type of Rbster cbn be used with b
 * ComponentColorModel if there bre multiple bbnds, or b
 * IndexColorModel if there is only one bbnd.
 * <p>
 * For exbmple, 5-6-5 RGB imbge dbtb cbn be represented by b
 * ShortInterlebvedRbster using b SinglePixelPbckedSbmpleModel bnd
 * b ComponentColorModel.
 *
 *
 */
public clbss ShortInterlebvedRbster extends ShortComponentRbster {

    /** A cbched copy of minX + width for use in bounds checks. */
    privbte int mbxX;

    /** A cbched copy of minY + height for use in bounds checks. */
    privbte int mbxY;

    /**
     *  Constructs b ShortInterlebvedRbster with the given SbmpleModel.
     *  The Rbster's upper left corner is origin bnd it is the sbme
     *  size bs the SbmpleModel.  A DbtbBuffer lbrge enough to describe the
     *  Rbster is butombticblly crebted.  SbmpleModel must be of type
     *  PixelInterlebvedSbmpleModel or SinglePixelPbckedSbmpleModel.
     *  @pbrbm sbmpleModel     The SbmpleModel thbt specifies the lbyout.
     *  @pbrbm origin          The Point thbt specified the origin.
     */
    public ShortInterlebvedRbster(SbmpleModel sbmpleModel, Point origin) {
        this(sbmpleModel,
             sbmpleModel.crebteDbtbBuffer(),
             new Rectbngle(origin.x,
                           origin.y,
                           sbmpleModel.getWidth(),
                           sbmpleModel.getHeight()),
             origin,
             null);
    }

    /**
     * Constructs b ShortInterlebvedRbster with the given SbmpleModel
     * bnd DbtbBuffer.  The Rbster's upper left corner is origin bnd
     * it is the sbme sizes the SbmpleModel.  The DbtbBuffer is not
     * initiblized bnd must be b DbtbBufferUShort compbtible with SbmpleModel.
     * SbmpleModel must be of type PixelInterlebvedSbmpleModel or
     * SinglePixelPbckedSbmpleModel.
     * @pbrbm sbmpleModel     The SbmpleModel thbt specifies the lbyout.
     * @pbrbm dbtbBuffer      The DbtbBufferUShort thbt contbins the imbge dbtb.
     * @pbrbm origin          The Point thbt specifies the origin.
     */
    public ShortInterlebvedRbster(SbmpleModel sbmpleModel,
                                   DbtbBuffer dbtbBuffer,
                                   Point origin) {
        this(sbmpleModel,
             dbtbBuffer,
             new Rectbngle(origin.x,
                           origin.y,
                           sbmpleModel.getWidth(),
                           sbmpleModel.getHeight()),
             origin,
             null);
    }

    /**
     * Constructs b ShortInterlebvedRbster with the given SbmpleModel,
     * DbtbBuffer, bnd pbrent.  DbtbBuffer must be b DbtbBufferUShort bnd
     * SbmpleModel must be of type PixelInterlebvedSbmpleModel or
     * SinglePixelPbckedSbmpleModel.  When trbnslbted into the bbse Rbster's
     * coordinbte system, bRegion must be contbined by the bbse Rbster.
     * Origin is the coodinbte in the new Rbster's coordinbte system of
     * the origin of the bbse Rbster.  (The bbse Rbster is the Rbster's
     * bncestor which hbs no pbrent.)
     *
     * Note thbt this constructor should generblly be cblled by other
     * constructors or crebte methods, it should not be used directly.
     * @pbrbm sbmpleModel     The SbmpleModel thbt specifies the lbyout.
     * @pbrbm dbtbBuffer      The DbtbBufferUShort thbt contbins the imbge dbtb.
     * @pbrbm bRegion         The Rectbngle thbt specifies the imbge breb.
     * @pbrbm origin          The Point thbt specifies the origin.
     * @pbrbm pbrent          The pbrent (if bny) of this rbster.
     */
    public ShortInterlebvedRbster(SbmpleModel sbmpleModel,
                                   DbtbBuffer dbtbBuffer,
                                   Rectbngle bRegion,
                                   Point origin,
                                   ShortInterlebvedRbster pbrent) {

        super(sbmpleModel, dbtbBuffer, bRegion, origin, pbrent);
        this.mbxX = minX + width;
        this.mbxY = minY + height;

        if(!(dbtbBuffer instbnceof DbtbBufferUShort)) {
            throw new RbsterFormbtException("ShortInterlebvedRbsters must "+
                                            "hbve ushort DbtbBuffers");
        }

        DbtbBufferUShort dbus = (DbtbBufferUShort)dbtbBuffer;
        this.dbtb = steblDbtb(dbus, 0);

        // REMIND: need cbse for interlebved ComponentSbmpleModel
        if ((sbmpleModel instbnceof PixelInterlebvedSbmpleModel) ||
            (sbmpleModel instbnceof ComponentSbmpleModel &&
             sbmpleModel.getNumBbnds() == 1)) {
            ComponentSbmpleModel csm = (ComponentSbmpleModel)sbmpleModel;

            this.scbnlineStride = csm.getScbnlineStride();
            this.pixelStride = csm.getPixelStride();
            this.dbtbOffsets = csm.getBbndOffsets();
            int xOffset = bRegion.x - origin.x;
            int yOffset = bRegion.y - origin.y;
            for (int i = 0; i < getNumDbtbElements(); i++) {
                dbtbOffsets[i] += xOffset*pixelStride+yOffset*scbnlineStride;
            }
        } else if (sbmpleModel instbnceof SinglePixelPbckedSbmpleModel) {
            SinglePixelPbckedSbmpleModel sppsm =
                    (SinglePixelPbckedSbmpleModel)sbmpleModel;
            this.scbnlineStride = sppsm.getScbnlineStride();
            this.pixelStride    = 1;
            this.dbtbOffsets = new int[1];
            this.dbtbOffsets[0] = dbus.getOffset();
            int xOffset = bRegion.x - origin.x;
            int yOffset = bRegion.y - origin.y;
            dbtbOffsets[0] += xOffset+yOffset*scbnlineStride;
        } else {
            throw new RbsterFormbtException("ShortInterlebvedRbsters must "+
              "hbve PixelInterlebvedSbmpleModel, SinglePixelPbckedSbmpleModel"+
              " or 1 bbnd ComponentSbmpleModel.  Sbmple model is "+
              sbmpleModel);
        }
        this.bbndOffset = this.dbtbOffsets[0];
        verify();
    }

    /**
     * Returns b copy of the dbtb offsets brrby. For ebch bbnd the dbtb offset
     * is the index into the bbnd's dbtb brrby, of the first sbmple of the
     * bbnd.
     */
    public int[] getDbtbOffsets() {
        return dbtbOffsets.clone();
    }

    /**
     * Returns the dbtb offset for the specified bbnd.  The dbtb offset
     * is the index into the dbtb brrby in which the first sbmple
     * of the first scbnline is stored.
     * @pbrbm bbnd  The bbnd whose offset is returned.
     */
    public int getDbtbOffset(int bbnd) {
        return dbtbOffsets[bbnd];
    }

    /**
     * Returns the scbnline stride -- the number of dbtb brrby elements between
     * b given sbmple bnd the sbme sbmple in the sbme column of the next row.
     */
    public int getScbnlineStride() {
        return scbnlineStride;
    }

    /**
     * Returns pixel stride -- the number of dbtb brrby elements  between two
     * sbmples for the sbme bbnd on the sbme scbnline.
     */
    public int getPixelStride() {
        return pixelStride;
    }

    /**
     * Returns b reference to the dbtb brrby.
     */
    public short[] getDbtbStorbge() {
        return dbtb;
    }

    /**
     * Returns the dbtb elements for bll bbnds bt the specified
     * locbtion.
     * An ArrbyIndexOutOfBounds exception will be thrown bt runtime
     * if the pixel coordinbte is out of bounds.
     * A ClbssCbstException will be thrown if the input object is non null
     * bnd references bnything other thbn bn brrby of trbnsferType.
     * @pbrbm x        The X coordinbte of the pixel locbtion.
     * @pbrbm y        The Y coordinbte of the pixel locbtion.
     * @pbrbm outDbtb  An object reference to bn brrby of type defined by
     *                 getTrbnsferType() bnd length getNumDbtbElements().
     *                 If null bn brrby of bppropribte type bnd size will be
     *                 bllocbted.
     * @return         An object reference to bn brrby of type defined by
     *                 getTrbnsferType() with the request pixel dbtb.
     */
    public Object getDbtbElements(int x, int y, Object obj) {
        if ((x < this.minX) || (y < this.minY) ||
            (x >= this.mbxX) || (y >= this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        short outDbtb[];
        if (obj == null) {
            outDbtb = new short[numDbtbElements];
        } else {
            outDbtb = (short[])obj;
        }
        int off = (y-minY)*scbnlineStride +
                  (x-minX)*pixelStride;

        for (int bbnd = 0; bbnd < numDbtbElements; bbnd++) {
            outDbtb[bbnd] = dbtb[dbtbOffsets[bbnd] + off];
        }

        return outDbtb;
    }

    /**
     * Returns bn brrby  of dbtb elements from the specified rectbngulbr
     * region.
     * An ArrbyIndexOutOfBounds exception will be thrown bt runtime
     * if the pixel coordinbtes bre out of bounds.
     * A ClbssCbstException will be thrown if the input object is non null
     * bnd references bnything other thbn bn brrby of trbnsferType.
     * <pre>
     *       short[] bbndDbtb = (short[])Rbster.getDbtbElements(x, y, w, h, null);
     *       int numDbtbElements = Rbster.getBbnds();
     *       short[] pixel = new short[numDbtbElements];
     *       // To find the dbtb element bt locbtion (x2, y2)
     *       System.brrbycopy(bbndDbtb, ((y2-y)*w + (x2-x))*numDbtbElements,
     *                        pixel, 0, numDbtbElements);
     * </pre>
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm width    Width of the pixel rectbngle.
     * @pbrbm height   Height of the pixel rectbngle.
     * @pbrbm outDbtb  An object reference to bn brrby of type defined by
     *                 getTrbnsferType() bnd length w*h*getNumDbtbElements().
     *                 If null bn brrby of bppropribte type bnd size will be
     *                 bllocbted.
     * @return         An object reference to bn brrby of type defined by
     *                 getTrbnsferType() with the request pixel dbtb.
     */
    public Object getDbtbElements(int x, int y, int w, int h, Object obj) {
        if ((x < this.minX) || (y < this.minY) ||
            (x + w > this.mbxX) || (y + h > this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        short outDbtb[];
        if (obj == null) {
            outDbtb = new short[w*h*numDbtbElements];
        } else {
            outDbtb = (short[])obj;
        }
        int yoff = (y-minY)*scbnlineStride +
                   (x-minX)*pixelStride;

        int xoff;
        int off = 0;
        int xstbrt;
        int ystbrt;

        for (ystbrt=0; ystbrt < h; ystbrt++, yoff += scbnlineStride) {
            xoff = yoff;
            for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixelStride) {
                for (int c = 0; c < numDbtbElements; c++) {
                    outDbtb[off++] = dbtb[dbtbOffsets[c] + xoff];
                }
            }
        }

        return outDbtb;
    }

    /**
     * Returns b short integer brrby of dbtb elements from the
     * specified rectbngulbr region.
     * An ArrbyIndexOutOfBounds exception will be thrown bt runtime
     * if the pixel coordinbtes bre out of bounds.
     * <pre>
     *       short[] bbndDbtb = Rbster.getShortDbtb(x, y, w, h, null);
     *       // To find the dbtb element bt locbtion (x2, y2)
     *       short dbtbElenent = bbndDbtb[((y2-y)*w + (x2-x))];
     * </pre>
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm width    Width of the sbmple rectbngle.
     * @pbrbm height   Height of the sbmple rectbngle.
     * @pbrbm bbnd     The bbnd to return.
     * @pbrbm outDbtb  If non-null, dbtb elements for bll bbnds
     *                 bt the specified locbtion bre returned in this brrby.
     * @return         Dbtb brrby with dbtb elements for bll bbnds.
     */
    public short[] getShortDbtb(int x, int y, int w, int h,
                               int bbnd, short[] outDbtb) {
        // Bounds check for 'bbnd' will be performed butombticblly
        if ((x < this.minX) || (y < this.minY) ||
            (x + w > this.mbxX) || (y + h > this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        if (outDbtb == null) {
            outDbtb = new short[numDbtbElements*w*h];
        }
        int yoff =  (y-minY)*scbnlineStride +
                    (x-minX)*pixelStride+ dbtbOffsets[bbnd];
        int xoff;
        int off = 0;
        int xstbrt;
        int ystbrt;

        if (pixelStride == 1) {
            if (scbnlineStride == w) {
                System.brrbycopy(dbtb, yoff, outDbtb, 0, w*h);
            }
            else {
                for (ystbrt=0; ystbrt < h; ystbrt++, yoff += scbnlineStride) {
                    System.brrbycopy(dbtb, yoff, outDbtb, off, w);
                    off += w;
                }
            }
        }
        else {
            for (ystbrt=0; ystbrt < h; ystbrt++, yoff += scbnlineStride) {
                xoff = yoff;
                for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixelStride) {
                    outDbtb[off++] = dbtb[xoff];
                }
            }
        }

        return outDbtb;
    }

    /**
     * Returns b short integer brrby  of dbtb elements from the
     * specified rectbngulbr region.
     * An ArrbyIndexOutOfBounds exception will be thrown bt runtime
     * if the pixel coordinbtes bre out of bounds.
     * <pre>
     *       short[] bbndDbtb = Rbster.getShortDbtb(x, y, w, h, null);
     *       int numDbtbElements = Rbster.getNumBbnds();
     *       short[] pixel = new short[numDbtbElements];
     *       // To find the dbtb element bt locbtion (x2, y2)
     *       System.brrbycopy(bbndDbtb, ((y2-y)*w + (x2-x))*numDbtbElements,
     *                        pixel, 0, numDbtbElements);
     * </pre>
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm width    Width of the pixel rectbngle.
     * @pbrbm height   Height of the pixel rectbngle.
     * @pbrbm outDbtb  If non-null, dbtb elements for bll bbnds
     *                 bt the specified locbtion bre returned in this brrby.
     * @return         Dbtb brrby with dbtb elements for bll bbnds.
     */
    public short[] getShortDbtb(int x, int y, int w, int h, short[] outDbtb) {
        if ((x < this.minX) || (y < this.minY) ||
            (x + w > this.mbxX) || (y + h > this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        if (outDbtb == null) {
            outDbtb = new short[numDbtbElements*w*h];
        }
        int yoff = (y-minY)*scbnlineStride +
                   (x-minX)*pixelStride;
        int xoff;
        int off = 0;
        int xstbrt;
        int ystbrt;

        for (ystbrt=0; ystbrt < h; ystbrt++, yoff += scbnlineStride) {
            xoff = yoff;
            for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixelStride) {
                for (int c = 0; c < numDbtbElements; c++) {
                    outDbtb[off++] = dbtb[dbtbOffsets[c] + xoff];
                }
            }
        }

        return outDbtb;
    }

    /**
     * Stores the dbtb elements for bll bbnds bt the specified locbtion.
     * An ArrbyIndexOutOfBounds exception will be thrown bt runtime
     * if the pixel coordinbte is out of bounds.
     * A ClbssCbstException will be thrown if the input object is non null
     * bnd references bnything other thbn bn brrby of trbnsferType.
     * @pbrbm x        The X coordinbte of the pixel locbtion.
     * @pbrbm y        The Y coordinbte of the pixel locbtion.
     * @pbrbm inDbtb   An object reference to bn brrby of type defined by
     *                 getTrbnsferType() bnd length getNumDbtbElements()
     *                 contbining the pixel dbtb to plbce bt x,y.
     */
    public void setDbtbElements(int x, int y, Object obj) {
        if ((x < this.minX) || (y < this.minY) ||
            (x >= this.mbxX) || (y >= this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        short inDbtb[] = (short[])obj;
        int off = (y-minY)*scbnlineStride +
                  (x-minX)*pixelStride;
        for (int i = 0; i < numDbtbElements; i++) {
            dbtb[dbtbOffsets[i] + off] = inDbtb[i];
        }
        mbrkDirty();
    }

    /**
     * Stores the Rbster dbtb bt the specified locbtion.
     * An ArrbyIndexOutOfBounds exception will be thrown bt runtime
     * if the pixel coordinbtes bre out of bounds.
     * @pbrbm x          The X coordinbte of the pixel locbtion.
     * @pbrbm y          The Y coordinbte of the pixel locbtion.
     * @pbrbm inRbster   Rbster of dbtb to plbce bt x,y locbtion.
     */
    public void setDbtbElements(int x, int y, Rbster inRbster) {
        int dstOffX = x + inRbster.getMinX();
        int dstOffY = y + inRbster.getMinY();
        int width  = inRbster.getWidth();
        int height = inRbster.getHeight();
        if ((dstOffX < this.minX) || (dstOffY < this.minY) ||
            (dstOffX + width > this.mbxX) || (dstOffY + height > this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }

        setDbtbElements(dstOffX, dstOffY, width, height, inRbster);
    }

    /**
     * Stores the Rbster dbtb bt the specified locbtion.
     * @pbrbm dstX The bbsolute X coordinbte of the destinbtion pixel
     * thbt will receive b copy of the upper-left pixel of the
     * inRbster
     * @pbrbm dstY The bbsolute Y coordinbte of the destinbtion pixel
     * thbt will receive b copy of the upper-left pixel of the
     * inRbster
     * @pbrbm width      The number of pixels to store horizontblly
     * @pbrbm height     The number of pixels to store verticblly
     * @pbrbm inRbster   Rbster of dbtb to plbce bt x,y locbtion.
     */
    privbte void setDbtbElements(int dstX, int dstY,
                                 int width, int height,
                                 Rbster inRbster) {
        // Assume bounds checking hbs been performed previously
        if (width <= 0 || height <= 0) {
            return;
        }

        // Write inRbster (minX, minY) to (dstX, dstY)

        int srcOffX = inRbster.getMinX();
        int srcOffY = inRbster.getMinY();
        Object tdbtb = null;

//      REMIND: Do something fbster!
//      if (inRbster instbnceof ShortInterlebvedRbster) {
//      }

        for (int stbrtY=0; stbrtY < height; stbrtY++) {
            // Grbb one scbnline bt b time
            tdbtb = inRbster.getDbtbElements(srcOffX, srcOffY+stbrtY,
                                             width, 1, tdbtb);
            setDbtbElements(dstX, dstY + stbrtY, width, 1, tdbtb);
        }
    }

    /**
     * Stores bn brrby of dbtb elements into the specified rectbngulbr
     * region.
     * An ArrbyIndexOutOfBounds exception will be thrown bt runtime
     * if the pixel coordinbtes bre out of bounds.
     * A ClbssCbstException will be thrown if the input object is non null
     * bnd references bnything other thbn bn brrby of trbnsferType.
     * The dbtb elements in the
     * dbtb brrby bre bssumed to be pbcked.  Thbt is, b dbtb element
     * for the nth bbnd bt locbtion (x2, y2) would be found bt:
     * <pre>
     *      inDbtb[((y2-y)*w + (x2-x))*numDbtbElements + n]
     * </pre>
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm w        Width of the pixel rectbngle.
     * @pbrbm h        Height of the pixel rectbngle.
     * @pbrbm inDbtb   An object reference to bn brrby of type defined by
     *                 getTrbnsferType() bnd length w*h*getNumDbtbElements()
     *                 contbining the pixel dbtb to plbce between x,y bnd
     *                 x+h, y+h.
     */
    public void setDbtbElements(int x, int y, int w, int h, Object obj) {
        if ((x < this.minX) || (y < this.minY) ||
            (x + w > this.mbxX) || (y + h > this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        short inDbtb[] = (short[])obj;
        int yoff = (y-minY)*scbnlineStride +
                   (x-minX)*pixelStride;
        int xoff;
        int off = 0;
        int xstbrt;
        int ystbrt;

        for (ystbrt=0; ystbrt < h; ystbrt++, yoff += scbnlineStride) {
            xoff = yoff;
            for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixelStride) {
                for (int c = 0; c < numDbtbElements; c++) {
                    dbtb[dbtbOffsets[c] + xoff] = inDbtb[off++];
                }
            }
        }

        mbrkDirty();
    }

    /**
     * Stores b short integer brrby of dbtb elements into the
     * specified rectbngulbr region.
     * An ArrbyIndexOutOfBounds exception will be thrown bt runtime
     * if the pixel coordinbtes bre out of bounds.
     * The dbtb elements in the
     * dbtb brrby bre bssumed to be pbcked.  Thbt is, b dbtb element
     * bt locbtion (x2, y2) would be found bt:
     * <pre>
     *      inDbtb[((y2-y)*w + (x2-x))]
     * </pre>
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm w        Width of the pixel rectbngle.
     * @pbrbm h        Height of the pixel rectbngle.
     * @pbrbm bbnd     The bbnd to set.
     * @pbrbm inDbtb   The dbtb elements to be stored.
     */
    public void putShortDbtb(int x, int y, int w, int h,
                             int bbnd, short[] inDbtb) {
        // Bounds check for 'bbnd' will be performed butombticblly
        if ((x < this.minX) || (y < this.minY) ||
            (x + w > this.mbxX) || (y + h > this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int yoff =  (y-minY)*scbnlineStride +
                    (x-minX)*pixelStride + dbtbOffsets[bbnd];
        int xoff;
        int off = 0;
        int xstbrt;
        int ystbrt;

        if (pixelStride == 1) {
            if (scbnlineStride == w) {
                System.brrbycopy(inDbtb, 0, dbtb, yoff, w*h);
            }
            else {
                for (ystbrt=0; ystbrt < h; ystbrt++, yoff += scbnlineStride) {
                    System.brrbycopy(inDbtb, off, dbtb, yoff, w);
                    off += w;
                }
            }
        }
        else {
            for (ystbrt=0; ystbrt < h; ystbrt++, yoff += scbnlineStride) {
                xoff = yoff;
                for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixelStride) {
                    dbtb[xoff] = inDbtb[off++];
                }
            }
        }

        mbrkDirty();
    }

    /**
     * Stores b short integer brrby of dbtb elements into the
     * specified rectbngulbr region.
     * An ArrbyIndexOutOfBounds exception will be thrown bt runtime
     * if the pixel coordinbtes bre out of bounds.
     * The dbtb elements in the
     * dbtb brrby bre bssumed to be pbcked.  Thbt is, b dbtb element
     * for the nth bbnd bt locbtion (x2, y2) would be found bt:
     * <pre>
     *      inDbtb[((y2-y)*w + (x2-x))*numDbtbElements + n]
     * </pre>
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm w        Width of the pixel rectbngle.
     * @pbrbm h        Height of the pixel rectbngle.
     * @pbrbm inDbtb   The dbtb elements to be stored.
     */
    public void putShortDbtb(int x, int y, int w, int h, short[] inDbtb) {
        if ((x < this.minX) || (y < this.minY) ||
            (x + w > this.mbxX) || (y + h > this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int yoff = (y-minY)*scbnlineStride +
                   (x-minX)*pixelStride;
        int xoff;
        int off = 0;
        int xstbrt;
        int ystbrt;

        for (ystbrt=0; ystbrt < h; ystbrt++, yoff += scbnlineStride) {
            xoff = yoff;
            for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixelStride) {
                for (int c = 0; c < numDbtbElements; c++) {
                    dbtb[dbtbOffsets[c] + xoff] = inDbtb[off++];
                }
            }
        }

        mbrkDirty();
    }

    /**
     * Crebtes b subrbster given b region of the rbster.  The x bnd y
     * coordinbtes specify the horizontbl bnd verticbl offsets
     * from the upper-left corner of this rbster to the upper-left corner
     * of the subrbster.  A subset of the bbnds of the pbrent Rbster mby
     * be specified.  If this is null, then bll the bbnds bre present in the
     * subRbster. A trbnslbtion to the subRbster mby blso be specified.
     * Note thbt the subrbster will reference the sbme
     * bbnd objects bs the pbrent rbster, but using different offsets.
     * @pbrbm x               X offset.
     * @pbrbm y               Y offset.
     * @pbrbm width           Width (in pixels) of the subrbster.
     * @pbrbm height          Height (in pixels) of the subrbster.
     * @pbrbm x0              Trbnslbted X origin of the subrbster.
     * @pbrbm y0              Trbnslbted Y origin of the subrbster.
     * @pbrbm bbndList        Arrby of bbnd indices.
     * @exception RbsterFormbtException
     *            if the specified bounding box is outside of the pbrent rbster.
     */
    public Rbster crebteChild (int x, int y,
                               int width, int height,
                               int x0, int y0, int[] bbndList) {
        WritbbleRbster newRbster = crebteWritbbleChild(x, y,
                                                       width, height,
                                                       x0, y0,
                                                       bbndList);
        return (Rbster) newRbster;
    }

    /**
     * Crebtes b Writbble subRbster given b region of the Rbster. The x bnd y
     * coordinbtes specify the horizontbl bnd verticbl offsets
     * from the upper-left corner of this Rbster to the upper-left corner
     * of the subRbster.  A subset of the bbnds of the pbrent Rbster mby
     * be specified.  If this is null, then bll the bbnds bre present in the
     * subRbster. A trbnslbtion to the subRbster mby blso be specified.
     * Note thbt the subRbster will reference the sbme
     * DbtbBuffers bs the pbrent Rbster, but using different offsets.
     * @pbrbm x               X offset.
     * @pbrbm y               Y offset.
     * @pbrbm width           Width (in pixels) of the subrbster.
     * @pbrbm height          Height (in pixels) of the subrbster.
     * @pbrbm x0              Trbnslbted X origin of the subrbster.
     * @pbrbm y0              Trbnslbted Y origin of the subrbster.
     * @pbrbm bbndList        Arrby of bbnd indices.
     * @exception RbsterFormbtException
     *            if the specified bounding box is outside of the pbrent Rbster.
     */
    public WritbbleRbster crebteWritbbleChild(int x, int y,
                                              int width, int height,
                                              int x0, int y0,
                                              int[] bbndList) {
        if (x < this.minX) {
            throw new RbsterFormbtException("x lies outside the rbster");
        }
        if (y < this.minY) {
            throw new RbsterFormbtException("y lies outside the rbster");
        }
        if ((x+width < x) || (x+width > this.minX + this.width)) {
            throw new RbsterFormbtException("(x + width) is outside of Rbster");
        }
        if ((y+height < y) || (y+height > this.minY + this.height)) {
            throw new RbsterFormbtException("(y + height) is outside of Rbster");
        }

        SbmpleModel sm;

        if (bbndList != null)
            sm = sbmpleModel.crebteSubsetSbmpleModel(bbndList);
        else
            sm = sbmpleModel;

        int deltbX = x0 - x;
        int deltbY = y0 - y;

        return new ShortInterlebvedRbster(sm,
                                       dbtbBuffer,
                                       new Rectbngle(x0, y0, width, height),
                                       new Point(sbmpleModelTrbnslbteX+deltbX,
                                                 sbmpleModelTrbnslbteY+deltbY),
                                       this);
    }

    /**
     * Crebtes b Rbster with the sbme lbyout but using b different
     * width bnd height, bnd with new zeroed dbtb brrbys.
     */
    public WritbbleRbster crebteCompbtibleWritbbleRbster(int w, int h) {
        if (w <= 0 || h <=0) {
            throw new RbsterFormbtException("negbtive "+
                                          ((w <= 0) ? "width" : "height"));
        }

        SbmpleModel sm = sbmpleModel.crebteCompbtibleSbmpleModel(w, h);

        return new ShortInterlebvedRbster(sm, new Point(0, 0));
    }

    /**
     * Crebtes b Rbster with the sbme lbyout bnd the sbme
     * width bnd height, bnd with new zeroed dbtb brrbys.  If
     * the Rbster is b subRbster, this will cbll
     * crebteCompbtibleRbster(width, height).
     */
    public WritbbleRbster crebteCompbtibleWritbbleRbster() {
        return crebteCompbtibleWritbbleRbster(width,height);
    }

    public String toString() {
        return new String ("ShortInterlebvedRbster: width = "+width
                           +" height = " + height
                           +" #numDbtbElements "+numDbtbElements);
                           // +" xOff = "+xOffset+" yOff = "+yOffset);
    }

}
