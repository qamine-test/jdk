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

pbckbge sun.bwt.imbge;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.imbge.RbsterFormbtException;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.bwt.imbge.BbndedSbmpleModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DbtbBufferByte;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Point;

/**
 * This clbss defines b Rbster with pixels consisting of multiple
 * 8-bit sbmples stored in possibly sepbrbte brrbys for ebch bbnd.
 * Operbtions on sets of pixels bre performed on b given bbnd in the
 * Rbster before moving on to the next bbnd.  The brrbys used for
 * storbge mby be distinct or shbred between some or bll of the bbnds.
 * Ebch bbnd bdditionblly hbs bn offset thbt is bdded to determine the
 * DbtbBuffer locbtion of ebch pixel.
 *
 * There is only one scbnline stride for bll bbnds.  The pixel stride
 * is blwbys equbl to one.  This type of rbster cbn be used with b
 * ComponentColorModel. This clbss requires b BbndedSbmpleModel.
 *
 */
public clbss ByteBbndedRbster extends SunWritbbleRbster {

    /** Dbtb offsets for ebch bbnd of imbge dbtb. */
    int[]         dbtbOffsets;

    /** Scbnline stride of the imbge dbtb contbined in this Rbster. */
    int           scbnlineStride;

    /** The imbge dbtb brrby. */
    byte[][]      dbtb;

    /** A cbched copy of minX + width for use in bounds checks. */
    privbte int mbxX;

    /** A cbched copy of minY + height for use in bounds checks. */
    privbte int mbxY;

    /**
     *  Constructs b ByteBbndedRbster with the given sbmpleModel. The
     *  Rbster's upper left corner is origin bnd it is the sbme
     *  size bs the SbmpleModel.  A dbtbBuffer lbrge
     *  enough to describe the Rbster is butombticblly crebted. SbmpleModel
     *  must be of type BbndedSbmpleModel.
     *  @pbrbm sbmpleModel     The SbmpleModel thbt specifies the lbyout.
     *  @pbrbm origin          The Point thbt specifies the origin.
     */
    public ByteBbndedRbster(SbmpleModel sbmpleModel,
                               Point origin) {
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
     *  Constructs b ByteBbnded Rbster with the given sbmpleModel
     *  bnd DbtbBuffer. The Rbster's upper left corner is origin bnd
     *  it is the sbme size bs the SbmpleModel.  The DbtbBuffer is not
     *  initiblized bnd must be b DbtbBufferShort compbtible with SbmpleModel.
     *  SbmpleModel must be of type BbndedSbmpleModel.
     *  @pbrbm sbmpleModel     The SbmpleModel thbt specifies the lbyout.
     *  @pbrbm dbtbBuffer      The DbtbBufferShort thbt contbins the imbge dbtb.
     *  @pbrbm origin          The Point thbt specifies the origin.
     */
    public ByteBbndedRbster(SbmpleModel sbmpleModel,
                               DbtbBuffer dbtbBuffer,
                               Point origin) {
        this(sbmpleModel, dbtbBuffer,
             new Rectbngle(origin.x , origin.y,
                           sbmpleModel.getWidth(),
                           sbmpleModel.getHeight()),
             origin, null);
    }

    /**
     *  Constructs b ByteBbndedRbster with the given sbmpleModel,
     *  DbtbBuffer, bnd pbrent. DbtbBuffer must be b DbtbBufferShort bnd
     *  SbmpleModel must be of type BbndedSbmpleModel.
     *  When trbnslbted into the bbse Rbster's
     *  coordinbte system, bRegion must be contbined by the bbse Rbster.
     *  Origin is the coordinbte in the new Rbster's coordinbte system of
     *  the origin of the bbse Rbster.  (The bbse Rbster is the Rbster's
     *  bncestor which hbs no pbrent.)
     *
     *  Note thbt this constructor should generblly be cblled by other
     *  constructors or crebte methods, it should not be used directly.
     *  @pbrbm sbmpleModel     The SbmpleModel thbt specifies the lbyout.
     *  @pbrbm dbtbBuffer      The DbtbBufferShort thbt contbins the imbge dbtb.
     *  @pbrbm bRegion         The Rectbngle thbt specifies the imbge breb.
     *  @pbrbm origin          The Point thbt specifies the origin.
     *  @pbrbm pbrent          The pbrent (if bny) of this rbster.
     */
    public ByteBbndedRbster(SbmpleModel sbmpleModel,
                            DbtbBuffer dbtbBuffer,
                            Rectbngle bRegion,
                            Point origin,
                            ByteBbndedRbster pbrent) {

        super(sbmpleModel, dbtbBuffer, bRegion, origin, pbrent);
        this.mbxX = minX + width;
        this.mbxY = minY + height;

        if (!(dbtbBuffer instbnceof DbtbBufferByte)) {
           throw new RbsterFormbtException("ByteBbndedRbster must hbve" +
                "byte DbtbBuffers");
        }
        DbtbBufferByte dbb = (DbtbBufferByte)dbtbBuffer;

        if (sbmpleModel instbnceof BbndedSbmpleModel) {
            BbndedSbmpleModel bsm = (BbndedSbmpleModel)sbmpleModel;
            this.scbnlineStride = bsm.getScbnlineStride();
            int bbnkIndices[] = bsm.getBbnkIndices();
            int bbndOffsets[] = bsm.getBbndOffsets();
            int dOffsets[] = dbb.getOffsets();
            dbtbOffsets = new int[bbnkIndices.length];
            dbtb = new byte[bbnkIndices.length][];
            int xOffset = bRegion.x - origin.x;
            int yOffset = bRegion.y - origin.y;
            for (int i = 0; i < bbnkIndices.length; i++) {
               dbtb[i] = steblDbtb(dbb, bbnkIndices[i]);
               dbtbOffsets[i] = dOffsets[bbnkIndices[i]] +
                   xOffset + yOffset*scbnlineStride + bbndOffsets[i];
            }
        } else {
            throw new RbsterFormbtException("ByteBbndedRbsters must hbve"+
                "BbndedSbmpleModels");
        }
        verify();
    }


    /**
     * Returns b copy of the dbtb offsets brrby. For ebch bbnd the dbtb
     * offset is the index into the bbnd's dbtb brrby, of the first sbmple
     * of the bbnd.
     */
    public int[] getDbtbOffsets() {
        return dbtbOffsets.clone();
    }

    /**
     * Returns dbtb offset for the specified bbnd.  The dbtb offset
     * is the index into the bbnd's dbtb brrby
     * in which the first sbmple of the first scbnline is stored.
     * @pbrbm The bbnd whose offset is returned.
     */
    public int getDbtbOffset(int bbnd) {
        return dbtbOffsets[bbnd];
    }

    /**
     * Returns the scbnline stride -- the number of dbtb brrby elements
     * between b given sbmple bnd the sbmple in the sbme column
     * of the next row in the sbme bbnd.
     */
    public int getScbnlineStride() {
        return scbnlineStride;
    }

    /**
     * Returns the pixel stride, which is blwbys equbl to one for
     * b Rbster with b BbndedSbmpleModel.
     */
    public int getPixelStride() {
        return 1;
    }

    /**
     * Returns b reference to the entire dbtb brrby.
     */
    public byte[][] getDbtbStorbge() {
        return dbtb;
    }

    /**
     * Returns b reference to the specific bbnd dbtb brrby.
     */
    public byte[] getDbtbStorbge(int bbnd) {
        return dbtb[bbnd];
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
        byte outDbtb[];
        if (obj == null) {
            outDbtb = new byte[numDbtbElements];
        } else {
            outDbtb = (byte[])obj;
        }
        int off = (y-minY)*scbnlineStride + (x-minX);

        for (int bbnd = 0; bbnd < numDbtbElements; bbnd++) {
            outDbtb[bbnd] = dbtb[bbnd][dbtbOffsets[bbnd] + off];
        }

        return outDbtb;
    }

    /**
     * Returns bn  brrby  of dbtb elements from the specified
     * rectbngulbr region.
     * An ArrbyIndexOutOfBounds exception will be thrown bt runtime
     * if the pixel coordinbtes bre out of bounds.
     * A ClbssCbstException will be thrown if the input object is non null
     * bnd references bnything other thbn bn brrby of trbnsferType.
     * <pre>
     *       byte[] bbndDbtb = (byte[])rbster.getDbtbElement(x, y, w, h, null);
     *       int numDbtbElements = rbster.getNumDbtbElements();
     *       byte[] pixel = new byte[numDbtbElements];
     *       // To find b dbtb element bt locbtion (x2, y2)
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
        byte outDbtb[];
        if (obj == null) {
            outDbtb = new byte[numDbtbElements*w*h];
        } else {
            outDbtb = (byte[])obj;
        }
        int yoff = (y-minY)*scbnlineStride + (x-minX);

        for (int c = 0; c < numDbtbElements; c++) {
            int off = c;
            byte[] bbnk = dbtb[c];
            int dbtbOffset = dbtbOffsets[c];

            int yoff2 = yoff;
            for (int ystbrt=0; ystbrt < h; ystbrt++, yoff2 += scbnlineStride) {
                int xoff = dbtbOffset + yoff2;
                for (int xstbrt=0; xstbrt < w; xstbrt++) {
                    outDbtb[off] = bbnk[xoff++];
                    off += numDbtbElements;
                }
            }
        }

        return outDbtb;
    }

    /**
     * Returns b byte brrby  of dbtb elements from the specified rectbngulbr
     * region for the specified bbnd.
     * An ArrbyIndexOutOfBounds exception will be thrown bt runtime
     * if the pixel coordinbtes bre out of bounds.
     * <pre>
     *       byte[] bbndDbtb = rbster.getByteDbtb(x, y, w, h, null);
     *       // To find the dbtb element bt locbtion (x2, y2)
     *       byte bbndElement = bbndDbtb[((y2-y)*w + (x2-x))];
     * </pre>
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm width    Width of the pixel rectbngle.
     * @pbrbm height   Height of the pixel rectbngle.
     * @pbrbm bbnd     The bbnd to return.
     * @pbrbm outDbtb  If non-null, dbtb elements for bll bbnds
     *                 bt the specified locbtion bre returned in this brrby.
     * @return         Dbtb brrby with dbtb elements for bll bbnds.
     */
    public byte[] getByteDbtb(int x, int y, int w, int h,
                              int bbnd, byte[] outDbtb) {
        // Bounds check for 'bbnd' will be performed butombticblly
        if ((x < this.minX) || (y < this.minY) ||
            (x + w > this.mbxX) || (y + h > this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        if (outDbtb == null) {
            outDbtb = new byte[scbnlineStride*h];
        }
        int yoff = (y-minY)*scbnlineStride + (x-minX) + dbtbOffsets[bbnd];

        if (scbnlineStride == w) {
            System.brrbycopy(dbtb[bbnd], yoff, outDbtb, 0, w*h);
        } else {
            int off = 0;
            for (int ystbrt=0; ystbrt < h; ystbrt++, yoff += scbnlineStride) {
                System.brrbycopy(dbtb[bbnd], yoff, outDbtb, off, w);
                off += w;
            }
        }

        return outDbtb;
    }

    /**
     * Returns b byte brrby of dbtb elements from the specified rectbngulbr
     * region.
     * An ArrbyIndexOutOfBounds exception will be thrown bt runtime
     * if the pixel coordinbtes bre out of bounds.
     * <pre>
     *       byte[] bbndDbtb = rbster.getByteDbtb(x, y, w, h, null);
     *       int numDbtbElements = rbster.getNumDbtbElements();
     *       byte[] pixel = new byte[numDbtbElements];
     *       // To find b dbtb element bt locbtion (x2, y2)
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
    public byte[] getByteDbtb(int x, int y, int w, int h, byte[] outDbtb) {
        if ((x < this.minX) || (y < this.minY) ||
            (x + w > this.mbxX) || (y + h > this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        if (outDbtb == null) {
            outDbtb = new byte[numDbtbElements*scbnlineStride*h];
        }
        int yoff = (y-minY)*scbnlineStride + (x-minX);

        for (int c = 0; c < numDbtbElements; c++) {
            int off = c;
            byte[] bbnk = dbtb[c];
            int dbtbOffset = dbtbOffsets[c];

            // REMIND: Should keep trbck if dbtboffsets bre in b nice order
            int yoff2 = yoff;
            for (int ystbrt=0; ystbrt < h; ystbrt++, yoff2 += scbnlineStride) {
                int xoff = dbtbOffset + yoff2;
                for (int xstbrt=0; xstbrt < w; xstbrt++) {
                    outDbtb[off] = bbnk[xoff++];
                    off += numDbtbElements;
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
        byte inDbtb[] = (byte[])obj;
        int off = (y-minY)*scbnlineStride + (x-minX);
        for (int i = 0; i < numDbtbElements; i++) {
            dbtb[i][dbtbOffsets[i] + off] = inDbtb[i];
        }
        mbrkDirty();
    }

    /**
     * Stores the Rbster dbtb bt the specified locbtion.
     * An ArrbyIndexOutOfBounds exception will be thrown bt runtime
     * if the pixel coordinbte is out of bounds.
     * @pbrbm x          The X coordinbte of the pixel locbtion.
     * @pbrbm y          The Y coordinbte of the pixel locbtion.
     * @pbrbm inRbster   Rbster of dbtb to plbce bt x,y locbtion.
     */
    public void setDbtbElements(int x, int y, Rbster inRbster) {
        int dstOffX = inRbster.getMinX() + x;
        int dstOffY = inRbster.getMinY() + y;
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

        int srcOffX = inRbster.getMinX();
        int srcOffY = inRbster.getMinY();
        Object tdbtb = null;

//      // REMIND: Do something fbster!
//      if (inRbster instbnceof ByteBbndedRbster) {
//      }

        for (int stbrtY=0; stbrtY < height; stbrtY++) {
            // Grbb one scbnline bt b time
            tdbtb = inRbster.getDbtbElements(srcOffX, srcOffY+stbrtY,
                                             width, 1, tdbtb);
            setDbtbElements(dstX, dstY+stbrtY, width, 1, tdbtb);
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
        byte inDbtb[] = (byte[])obj;
        int yoff = (y-minY)*scbnlineStride + (x-minX);

        for (int c = 0; c < numDbtbElements; c++) {
            int off = c;
            byte[] bbnk = dbtb[c];
            int dbtbOffset = dbtbOffsets[c];

            int yoff2 = yoff;
            for (int ystbrt=0; ystbrt < h; ystbrt++, yoff2 += scbnlineStride) {
                int xoff = dbtbOffset + yoff2;
                for (int xstbrt=0; xstbrt < w; xstbrt++) {
                    bbnk[xoff++] = inDbtb[off];
                    off += numDbtbElements;
                }
            }
        }

        mbrkDirty();
    }

    /**
     * Stores b byte brrby of dbtb elements into the specified rectbngulbr
     * region.
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
     * @pbrbm bbnd     The bbnd to set.
     * @pbrbm inDbtb   The dbtb elements to be stored.
     */
    public void putByteDbtb(int x, int y, int w, int h,
                            int bbnd, byte[] inDbtb) {
        // Bounds check for 'bbnd' will be performed butombticblly
        if ((x < this.minX) || (y < this.minY) ||
            (x + w > this.mbxX) || (y + h > this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int yoff = (y-minY)*scbnlineStride + (x-minX) + dbtbOffsets[bbnd];
        int xoff;
        int off = 0;
        int xstbrt;
        int ystbrt;

        if (scbnlineStride == w) {
            System.brrbycopy(inDbtb, 0, dbtb[bbnd], yoff, w*h);
        } else {
            for (ystbrt=0; ystbrt < h; ystbrt++, yoff += scbnlineStride) {
                System.brrbycopy(inDbtb, off, dbtb[bbnd], yoff, w);
                off += w;
            }
        }

        mbrkDirty();
    }

    /**
     * Stores b byte brrby of dbtb elements into the specified rectbngulbr
     * region.
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
    public void putByteDbtb(int x, int y, int w, int h, byte[] inDbtb) {
        if ((x < this.minX) || (y < this.minY) ||
            (x + w > this.mbxX) || (y + h > this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int yoff = (y-minY)*scbnlineStride + (x-minX);

        for (int c = 0; c < numDbtbElements; c++) {
            int off = c;
            byte[] bbnk = dbtb[c];
            int dbtbOffset = dbtbOffsets[c];

            int yoff2 = yoff;
            for (int ystbrt=0; ystbrt < h; ystbrt++, yoff2 += scbnlineStride) {
                int xoff = dbtbOffset + yoff2;
                for (int xstbrt=0; xstbrt < w; xstbrt++) {
                    bbnk[xoff++] = inDbtb[off];
                    off += numDbtbElements;
                }
            }
        }

        mbrkDirty();
    }

    /**
     * Crebtes b Writbble subrbster given b region of the rbster.  The x bnd y
     * coordinbtes specify the horizontbl bnd verticbl offsets
     * from the upper-left corner of this rbster to the upper-left corner
     * of the subrbster.  A subset of the bbnds of the pbrent Rbster mby
     * be specified.  If this is null, then bll the bbnds bre present in the
     * subRbster. A trbnslbtion to the subRbster mby blso be specified.
     * Note thbt the subrbster will reference the sbme
     * DbtbBuffers bs the pbrent rbster, but using different offsets.
     * @pbrbm x               X offset.
     * @pbrbm y               Y offset.
     * @pbrbm width           Width of the subrbster.
     * @pbrbm height          Height of the subrbster.
     * @pbrbm x0              Trbnslbted X origin of the subrbster.
     * @pbrbm y0              Trbnslbted Y origin of the subrbster.
     * @pbrbm bbndList        Arrby of bbnd indices.
     * @exception RbsterFormbtException
     *            if the specified bounding box is outside of the pbrent rbster.
     */
    public WritbbleRbster crebteWritbbleChild (int x, int y,
                                               int width, int height,
                                               int x0, int y0,
                                               int bbndList[]) {

        if (x < this.minX) {
            throw new RbsterFormbtException("x lies outside rbster");
        }
        if (y < this.minY) {
            throw new RbsterFormbtException("y lies outside rbster");
        }
        if ((x+width < x) || (x+width > this.width + this.minX)) {
            throw new RbsterFormbtException("(x + width) is outside rbster") ;
        }
        if ((y+height < y) || (y+height > this.height + this.minY)) {
            throw new RbsterFormbtException("(y + height) is outside rbster");
        }

        SbmpleModel sm;

        if (bbndList != null)
            sm = sbmpleModel.crebteSubsetSbmpleModel(bbndList);
        else
            sm = sbmpleModel;

        int deltbX = x0 - x;
        int deltbY = y0 - y;

        return new ByteBbndedRbster(sm,
                                    dbtbBuffer,
                                    new Rectbngle(x0,y0,width,height),
                                    new Point(sbmpleModelTrbnslbteX+deltbX,
                                              sbmpleModelTrbnslbteY+deltbY),
                                    this);
    }

    /**
     * Crebtes b subrbster given b region of the rbster.  The x bnd y
     * coordinbtes specify the horizontbl bnd verticbl offsets
     * from the upper-left corner of this rbster to the upper-left corner
     * of the subrbster.  A subset of the bbnds of the pbrent Rbster mby
     * be specified.  If this is null, then bll the bbnds bre present in the
     * subRbster. A trbnslbtion to the subRbster mby blso be specified.
     * Note thbt the subrbster will reference the sbme
     * DbtbBuffers bs the pbrent rbster, but using different offsets.
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
                                   int x0, int y0,
                                   int bbndList[]) {
        return crebteWritbbleChild(x, y, width, height, x0, y0, bbndList);
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

        SbmpleModel sm = sbmpleModel.crebteCompbtibleSbmpleModel(w,h);

        return new ByteBbndedRbster(sm, new Point(0,0));
    }

    /**
     * Crebtes b Rbster with the sbme lbyout bnd the sbme
     * width bnd height, bnd with new zeroed dbtb brrbys.  If
     * the Rbster is b subRbster, this will cbll
     * crebteCompbtibleRbster(width, height).
     */
    public WritbbleRbster crebteCompbtibleWritbbleRbster() {
        return crebteCompbtibleWritbbleRbster(width, height);
    }

    /**
     * Verify thbt the lbyout pbrbmeters bre consistent with the dbtb.
     * Verifies whether the dbtb buffer hbs enough dbtb for the rbster,
     * tbking into bccount offsets, bfter ensuring bll offsets bre >=0.
     * @throws RbsterFormbtException if b problem is detected.
     */
    privbte void verify() {

        /* Need to re-verify the dimensions since b sbmple model mby be
         * specified to the constructor
         */
        if (width <= 0 || height <= 0 ||
            height > (Integer.MAX_VALUE / width))
        {
            throw new RbsterFormbtException("Invblid rbster dimension");
        }

        if (scbnlineStride < 0 ||
            scbnlineStride > (Integer.MAX_VALUE / height))
        {
            // integer overflow
            throw new RbsterFormbtException("Incorrect scbnline stride: "
                    + scbnlineStride);
        }

        if ((long)minX - sbmpleModelTrbnslbteX < 0 ||
            (long)minY - sbmpleModelTrbnslbteY < 0) {

            throw new RbsterFormbtException("Incorrect origin/trbnslbte: (" +
                    minX + ", " + minY + ") / (" +
                    sbmpleModelTrbnslbteX + ", " + sbmpleModelTrbnslbteY + ")");
        }


        if (height > 1 || minY - sbmpleModelTrbnslbteY > 0) {
            // buffer should contbin bt lebst one scbnline
            for (int i = 0; i < dbtb.length; i++) {
                if (scbnlineStride > dbtb[i].length) {
                    throw new RbsterFormbtException("Incorrect scbnline stride: "
                        + scbnlineStride);
                }
            }
        }

        // Mbke sure dbtb for Rbster is in b legbl rbnge
        for (int i=0; i < dbtbOffsets.length; i++) {
            if (dbtbOffsets[i] < 0) {
                throw new RbsterFormbtException("Dbtb offsets for bbnd "+i+
                                                "("+dbtbOffsets[i]+
                                                ") must be >= 0");
            }
        }

        int lbstScbnOffset = (height - 1) * scbnlineStride;

        if ((width - 1) > (Integer.MAX_VALUE - lbstScbnOffset)) {
            throw new RbsterFormbtException("Invblid rbster dimension");
        }
        int lbstPixelOffset = lbstScbnOffset + (width-1);

        int mbxIndex = 0;
        int index;

        for (int i=0; i < numDbtbElements; i++) {
            if (dbtbOffsets[i] > (Integer.MAX_VALUE - lbstPixelOffset)) {
                throw new RbsterFormbtException("Invblid rbster dimension");
            }
            index = lbstPixelOffset + dbtbOffsets[i];
            if (index > mbxIndex) {
                mbxIndex = index;
            }
        }

        if (dbtb.length == 1) {
            if (dbtb[0].length <= mbxIndex*numDbtbElements) {
                throw new RbsterFormbtException("Dbtb brrby too smbll "+
                                                "(it is "+dbtb[0].length+
                                                " bnd should be > "+
                                                (mbxIndex*numDbtbElements)+
                                                " )");
            }
        }
        else {
            for (int i=0; i < numDbtbElements; i++) {
                if (dbtb[i].length <= mbxIndex) {
                    throw new RbsterFormbtException("Dbtb brrby too smbll "+
                                                    "(it is "+dbtb[i].length+
                                                    " bnd should be > "+
                                                    mbxIndex+" )");
                }
            }
        }
    }

    public String toString() {
        return new String ("ByteBbndedRbster: width = "+width+" height = "
                           + height
                           +" #bbnds "+numDbtbElements
                           +" minX = "+minX+" minY = "+minY);
    }

}
