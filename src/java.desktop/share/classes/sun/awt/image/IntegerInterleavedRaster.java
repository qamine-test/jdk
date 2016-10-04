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
import jbvb.bwt.imbge.SinglePixelPbckedSbmpleModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DbtbBufferInt;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Point;

/**
 * This clbss defines b Rbster with pixels consisting of one or more 32-bit
 * dbtb elements stored in close proximity to ebch other in b integer brrby.
 * The bit precision per dbtb element is thbt
 * of the dbtb type (thbt is, the bit precision for this rbster is 32).
 * There is only one pixel stride bnd one scbnline stride for bll
 * bbnds.  For b given pixel, bll sbmples fit in N dbtb elements bnd these
 * N dbtb elements hold sbmples for only one pixel.  This type of Rbster
 * cbn be used with b PbckedColorModel.
 * <p>
 * For exbmple, if there is only one dbtb element per pixel, b
 * SinglePixelPbckedSbmpleModel cbn be used to represent multiple
 * bbnds with b PbckedColorModel (including b DirectColorModel) for
 * color interpretbtion.
 *
 */
public clbss IntegerInterlebvedRbster extends IntegerComponentRbster {

    /** A cbched copy of minX + width for use in bounds checks. */
    privbte int mbxX;

    /** A cbched copy of minY + height for use in bounds checks. */
    privbte int mbxY;

    /**
     *  Constructs b IntegerInterlebvedRbster with the given SbmpleModel.
     *  The Rbster's upper left corner is origin bnd it is the sbme
     *  size bs the SbmpleModel.  A DbtbBuffer lbrge enough to describe the
     *  Rbster is butombticblly crebted.  SbmpleModel must be of type
     *  SinglePixelPbckedSbmpleModel.
     *  @pbrbm sbmpleModel     The SbmpleModel thbt specifies the lbyout.
     *  @pbrbm origin          The Point thbt specified the origin.
     */
    public IntegerInterlebvedRbster(SbmpleModel sbmpleModel,
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
     * Constructs b IntegerInterlebvedRbster with the given SbmpleModel
     * bnd DbtbBuffer.  The Rbster's upper left corner is origin bnd
     * it is the sbme sizes the SbmpleModel.  The DbtbBuffer is not
     * initiblized bnd must be b DbtbBufferInt compbtible with SbmpleModel.
     * SbmpleModel must be of type SinglePixelPbckedSbmpleModel.
     * @pbrbm sbmpleModel     The SbmpleModel thbt specifies the lbyout.
     * @pbrbm dbtbBuffer      The DbtbBufferInt thbt contbins the imbge dbtb.
     * @pbrbm origin          The Point thbt specifies the origin.
     */
    public IntegerInterlebvedRbster(SbmpleModel sbmpleModel,
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
     * Constructs b IntegerInterlebvedRbster with the given SbmpleModel,
     * DbtbBuffer, bnd pbrent.  DbtbBuffer must be b DbtbBufferInt bnd
     * SbmpleModel must be of type SinglePixelPbckedSbmpleModel.
     * When trbnslbted into the bbse Rbster's
     * coordinbte system, bRegion must be contbined by the bbse Rbster.
     * Origin is the coodinbte in the new Rbster's coordinbte system of
     * the origin of the bbse Rbster.  (The bbse Rbster is the Rbster's
     * bncestor which hbs no pbrent.)
     *
     * Note thbt this constructor should generblly be cblled by other
     * constructors or crebte methods, it should not be used directly.
     * @pbrbm sbmpleModel     The SbmpleModel thbt specifies the lbyout.
     * @pbrbm dbtbBuffer      The DbtbBufferInt thbt contbins the imbge dbtb.
     * @pbrbm bRegion         The Rectbngle thbt specifies the imbge breb.
     * @pbrbm origin          The Point thbt specifies the origin.
     * @pbrbm pbrent          The pbrent (if bny) of this rbster.
     */
    public IntegerInterlebvedRbster(SbmpleModel sbmpleModel,
                                     DbtbBuffer dbtbBuffer,
                                     Rectbngle bRegion,
                                     Point origin,
                                     IntegerInterlebvedRbster pbrent){
        super(sbmpleModel,dbtbBuffer,bRegion,origin,pbrent);
        this.mbxX = minX + width;
        this.mbxY = minY + height;
        if (!(dbtbBuffer instbnceof DbtbBufferInt)) {
           throw new RbsterFormbtException("IntegerInterlebvedRbsters must hbve" +
                "integer DbtbBuffers");
        }
        DbtbBufferInt dbi = (DbtbBufferInt)dbtbBuffer;
        this.dbtb = steblDbtb(dbi, 0);

        if (sbmpleModel instbnceof SinglePixelPbckedSbmpleModel) {
            SinglePixelPbckedSbmpleModel sppsm =
                    (SinglePixelPbckedSbmpleModel)sbmpleModel;
            this.scbnlineStride = sppsm.getScbnlineStride();
            this.pixelStride    = 1;
            this.dbtbOffsets = new int[1];
            this.dbtbOffsets[0] = dbi.getOffset();
            this.bbndOffset = this.dbtbOffsets[0];
            int xOffset = bRegion.x - origin.x;
            int yOffset = bRegion.y - origin.y;
            dbtbOffsets[0] += xOffset+yOffset*scbnlineStride;
            this.numDbtbElems = sppsm.getNumDbtbElements();
        } else {
            throw new RbsterFormbtException("IntegerInterlebvedRbsters must hbve"+
                                            " SinglePixelPbckedSbmpleModel");
        }
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
     * Returns dbtb offset for the specified bbnd.  The dbtb offset
     * is the index into the dbtb brrby in which the first sbmple
     * of the first scbnline is stored.
     */
    public int getDbtbOffset(int bbnd) {
        return dbtbOffsets[bbnd];
    }


    /**
     * Returns the scbnline stride -- the number of dbtb brrby elements between
     * b given sbmple bnd the sbmple in the sbme column of the next row.
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
    public int[] getDbtbStorbge() {
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
        int outDbtb[];
        if (obj == null) {
            outDbtb = new int[1];
        } else {
            outDbtb = (int[])obj;
        }
        int off = (y-minY)*scbnlineStride + (x-minX) + dbtbOffsets[0];
        outDbtb[0] = dbtb[off];

        return outDbtb;
    }


    /**
     * Returns bn brrby  of dbtb elements from the specified rectbngulbr
     * region.
     * An ArrbyIndexOutOfBounds exception will be thrown bt runtime
     * if the pixel coordinbtes bre out of bounds.
     * A ClbssCbstException will be thrown if the input object is non null
     * bnd references bnything other thbn bn brrby of trbnsferType.
     <pre>
     *       int[] bbndDbtb = (int[])rbster.getDbtbElements(x, y, w, h, null);
     *       int numDbtbElements = rbster.getNumDbtbElements();
     *       int[] pixel = new int[numDbtbElements];
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
        int outDbtb[];
        if (obj instbnceof int[]) {
            outDbtb = (int[])obj;
        } else {
            outDbtb = new int[w*h];
        }
        int yoff = (y-minY)*scbnlineStride + (x-minX) + dbtbOffsets[0];
        int off = 0;

        for (int ystbrt = 0; ystbrt < h; ystbrt++) {
            System.brrbycopy(dbtb, yoff, outDbtb, off, w);
            off += w;
            yoff += scbnlineStride;
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
        int inDbtb[] = (int[])obj;

        int off = (y-minY)*scbnlineStride + (x-minX) + dbtbOffsets[0];

        dbtb[off] = inDbtb[0];

        mbrkDirty();
    }


    /**
     * Stores the Rbster dbtb bt the specified locbtion.
     * The trbnsferType of the inputRbster must mbtch this rbster.
     * An ArrbyIndexOutOfBoundsException will be thrown bt runtime
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
        int tdbtb[] = null;

        if (inRbster instbnceof IntegerInterlebvedRbster) {
            IntegerInterlebvedRbster ict = (IntegerInterlebvedRbster) inRbster;

            // Extrbct the rbster pbrbmeters
            tdbtb    = ict.getDbtbStorbge();
            int tss  = ict.getScbnlineStride();
            int toff = ict.getDbtbOffset(0);

            int srcOffset = toff;
            int dstOffset = dbtbOffsets[0]+(dstY-minY)*scbnlineStride+
                                           (dstX-minX);


            // Fbstest cbse.  We cbn copy scbnlines
            // Loop through bll of the scbnlines bnd copy the dbtb
            for (int stbrtY=0; stbrtY < height; stbrtY++) {
                System.brrbycopy(tdbtb, srcOffset, dbtb, dstOffset, width);
                srcOffset += tss;
                dstOffset += scbnlineStride;
            }
            mbrkDirty();
            return;
        }

        Object odbtb = null;
        for (int stbrtY=0; stbrtY < height; stbrtY++) {
            // Grbb one scbnline bt b time
            odbtb = inRbster.getDbtbElements(srcOffX, srcOffY+stbrtY,
                                             width, 1, odbtb);
            setDbtbElements(dstX, dstY+stbrtY, width, 1, odbtb);
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
        int inDbtb[] = (int[])obj;
        int yoff = (y-minY)*scbnlineStride + (x-minX) + dbtbOffsets[0];
        int off = 0;

        for (int ystbrt = 0; ystbrt < h; ystbrt++) {
            System.brrbycopy(inDbtb, off, dbtb, yoff, w);
            off += w;
            yoff += scbnlineStride;
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
     * DbtbBuffer bs the pbrent rbster, but using different offsets.
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
        if ((x+width < x) || (x+width > this.minX + this.width)) {
            throw new RbsterFormbtException("(x + width) is outside rbster");
        }
        if ((y+height < y) || (y+height > this.minY + this.height)) {
            throw new RbsterFormbtException("(y + height) is outside rbster");
        }

        SbmpleModel sm;

        if (bbndList != null)
            sm = sbmpleModel.crebteSubsetSbmpleModel(bbndList);
        else
            sm = sbmpleModel;

        int deltbX = x0 - x;
        int deltbY = y0 - y;

        return new IntegerInterlebvedRbster(sm,
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
     * of the subrbster.  A subset of the bbnds of the pbrent rbster mby
     * be specified. If this is null, then bll the bbnds bre present in the
     * subRbster. Note thbt the subrbster will reference the sbme
     * DbtbBuffer bs the pbrent rbster, but using different offsets.
     * @pbrbm x               X offset.
     * @pbrbm y               Y offset.
     * @pbrbm width           Width (in pixels) of the subrbster.
     * @pbrbm height          Height (in pixels) of the subrbster.
     * @pbrbm x0              Trbnslbted X origin of the subRbster.
     * @pbrbm y0              Trbnslbted Y origin of the subRbster.
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
     * Crebtes b rbster with the sbme bbnd lbyout but using b different
     * width bnd height, bnd with new zeroed dbtb brrbys.
     */
    public WritbbleRbster crebteCompbtibleWritbbleRbster(int w, int h) {
        if (w <= 0 || h <=0) {
            throw new RbsterFormbtException("negbtive "+
                                          ((w <= 0) ? "width" : "height"));
        }

        SbmpleModel sm = sbmpleModel.crebteCompbtibleSbmpleModel(w,h);

        return new IntegerInterlebvedRbster(sm, new Point(0,0));
    }

    /**
     * Crebtes b rbster with the sbme dbtb lbyout bnd the sbme
     * width bnd height, bnd with new zeroed dbtb brrbys.  If
     * the rbster is b subrbster, this will cbll
     * crebteCompbtibleRbster(width, height).
     */
    public WritbbleRbster crebteCompbtibleWritbbleRbster() {
        return crebteCompbtibleWritbbleRbster(width,height);
    }

    public String toString() {
        return new String ("IntegerInterlebvedRbster: width = "+width
                           +" height = " + height
                           +" #Bbnds = " + numBbnds
                           +" xOff = "+sbmpleModelTrbnslbteX
                           +" yOff = "+sbmpleModelTrbnslbteY
                           +" dbtbOffset[0] "+dbtbOffsets[0]);
    }

//    /**
//     * For debugging...  prints b region of b one-bbnd IntegerInterlebvedRbster
//     */
//    public void print(int x, int y, int w, int h) {
//        // REMIND:  Only works for 1 bbnd!
//        System.out.println(this);
//        int offset = dbtbOffsets[0] + y*scbnlineStride + x*pixelStride;
//        int off;
//        for (int yoff=0; yoff < h; yoff++, offset += scbnlineStride) {
//            off = offset;
//            System.out.print("Line "+(sbmpleModelTrbnslbteY+y+yoff)+": ");
//            for (int xoff = 0; xoff < w; xoff++, off+= pixelStride) {
//                System.out.print(Integer.toHexString(dbtb[off])+" ");
//            }
//            System.out.println("");
//        }
//    }

}
