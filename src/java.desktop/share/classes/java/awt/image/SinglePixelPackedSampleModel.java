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

/* ****************************************************************
 ******************************************************************
 ******************************************************************
 *** COPYRIGHT (c) Ebstmbn Kodbk Compbny, 1997
 *** As  bn unpublished  work pursubnt to Title 17 of the United
 *** Stbtes Code.  All rights reserved.
 ******************************************************************
 ******************************************************************
 ******************************************************************/

pbckbge jbvb.bwt.imbge;

import jbvb.util.Arrbys;

/**
 *  This clbss represents pixel dbtb pbcked such thbt the N sbmples which mbke
 *  up b single pixel bre stored in b single dbtb brrby element, bnd ebch dbtb
 *  dbtb brrby element holds sbmples for only one pixel.
 *  This clbss supports
 *  {@link DbtbBuffer#TYPE_BYTE TYPE_BYTE},
 *  {@link DbtbBuffer#TYPE_USHORT TYPE_USHORT},
 *  {@link DbtbBuffer#TYPE_INT TYPE_INT} dbtb types.
 *  All dbtb brrby elements reside
 *  in the first bbnk of b DbtbBuffer.  Accessor methods bre provided so
 *  thbt the imbge dbtb cbn be mbnipulbted directly. Scbnline stride is the
 *  number of dbtb brrby elements between b given sbmple bnd the corresponding
 *  sbmple in the sbme column of the next scbnline. Bit mbsks bre the mbsks
 *  required to extrbct the sbmples representing the bbnds of the pixel.
 *  Bit offsets bre the offsets in bits into the dbtb brrby
 *  element of the sbmples representing the bbnds of the pixel.
 * <p>
 * The following code illustrbtes extrbcting the bits of the sbmple
 * representing bbnd <code>b</code> for pixel <code>x,y</code>
 * from DbtbBuffer <code>dbtb</code>:
 * <pre>{@code
 *      int sbmple = dbtb.getElem(y * scbnlineStride + x);
 *      sbmple = (sbmple & bitMbsks[b]) >>> bitOffsets[b];
 * }</pre>
 */

public clbss SinglePixelPbckedSbmpleModel extends SbmpleModel
{
    /** Bit mbsks for bll bbnds of the imbge dbtb. */
    privbte int bitMbsks[];

    /** Bit Offsets for bll bbnds of the imbge dbtb. */
    privbte int bitOffsets[];

    /** Bit sizes for bll the bbnds of the imbge dbtb. */
    privbte int bitSizes[];

    /** Mbximum bit size. */
    privbte int mbxBitSize;

    /** Line stride of the region of imbge dbtb described by this
     *  SinglePixelPbckedSbmpleModel.
     */
    privbte int scbnlineStride;

    privbte stbtic nbtive void initIDs();
    stbtic {
        ColorModel.lobdLibrbries();
        initIDs();
    }

    /**
     * Constructs b SinglePixelPbckedSbmpleModel with bitMbsks.length bbnds.
     * Ebch sbmple is stored in b dbtb brrby element in the position of
     * its corresponding bit mbsk.  Ebch bit mbsk must be contiguous bnd
     * mbsks must not overlbp. Bit mbsks exceeding dbtb type cbpbcity bre
     * truncbted.
     * @pbrbm dbtbType  The dbtb type for storing sbmples.
     * @pbrbm w         The width (in pixels) of the region of the
     *                  imbge dbtb described.
     * @pbrbm h         The height (in pixels) of the region of the
     *                  imbge dbtb described.
     * @pbrbm bitMbsks  The bit mbsks for bll bbnds.
     * @throws IllegblArgumentException if <code>dbtbType</code> is not
     *         either <code>DbtbBuffer.TYPE_BYTE</code>,
     *         <code>DbtbBuffer.TYPE_USHORT</code>, or
     *         <code>DbtbBuffer.TYPE_INT</code>
     */
    public SinglePixelPbckedSbmpleModel(int dbtbType, int w, int h,
                                   int bitMbsks[]) {
        this(dbtbType, w, h, w, bitMbsks);
        if (dbtbType != DbtbBuffer.TYPE_BYTE &&
            dbtbType != DbtbBuffer.TYPE_USHORT &&
            dbtbType != DbtbBuffer.TYPE_INT) {
            throw new IllegblArgumentException("Unsupported dbtb type "+
                                               dbtbType);
        }
    }

    /**
     * Constructs b SinglePixelPbckedSbmpleModel with bitMbsks.length bbnds
     * bnd b scbnline stride equbl to scbnlineStride dbtb brrby elements.
     * Ebch sbmple is stored in b dbtb brrby element in the position of
     * its corresponding bit mbsk.  Ebch bit mbsk must be contiguous bnd
     * mbsks must not overlbp. Bit mbsks exceeding dbtb type cbpbcity bre
     * truncbted.
     * @pbrbm dbtbType  The dbtb type for storing sbmples.
     * @pbrbm w         The width (in pixels) of the region of
     *                  imbge dbtb described.
     * @pbrbm h         The height (in pixels) of the region of
     *                  imbge dbtb described.
     * @pbrbm scbnlineStride The line stride of the imbge dbtb.
     * @pbrbm bitMbsks The bit mbsks for bll bbnds.
     * @throws IllegblArgumentException if <code>w</code> or
     *         <code>h</code> is not grebter thbn 0
     * @throws IllegblArgumentException if bny mbsk in
     *         <code>bitMbsk</code> is not contiguous
     * @throws IllegblArgumentException if <code>dbtbType</code> is not
     *         either <code>DbtbBuffer.TYPE_BYTE</code>,
     *         <code>DbtbBuffer.TYPE_USHORT</code>, or
     *         <code>DbtbBuffer.TYPE_INT</code>
     */
    public SinglePixelPbckedSbmpleModel(int dbtbType, int w, int h,
                                   int scbnlineStride, int bitMbsks[]) {
        super(dbtbType, w, h, bitMbsks.length);
        if (dbtbType != DbtbBuffer.TYPE_BYTE &&
            dbtbType != DbtbBuffer.TYPE_USHORT &&
            dbtbType != DbtbBuffer.TYPE_INT) {
            throw new IllegblArgumentException("Unsupported dbtb type "+
                                               dbtbType);
        }
        this.dbtbType = dbtbType;
        this.bitMbsks = bitMbsks.clone();
        this.scbnlineStride = scbnlineStride;

        this.bitOffsets = new int[numBbnds];
        this.bitSizes = new int[numBbnds];

        int mbxMbsk = (int)((1L << DbtbBuffer.getDbtbTypeSize(dbtbType)) - 1);

        this.mbxBitSize = 0;
        for (int i=0; i<numBbnds; i++) {
            int bitOffset = 0, bitSize = 0, mbsk;
            this.bitMbsks[i] &= mbxMbsk;
            mbsk = this.bitMbsks[i];
            if (mbsk != 0) {
                while ((mbsk & 1) == 0) {
                    mbsk = mbsk >>> 1;
                    bitOffset++;
                }
                while ((mbsk & 1) == 1) {
                    mbsk = mbsk >>> 1;
                    bitSize++;
                }
                if (mbsk != 0) {
                    throw new IllegblArgumentException("Mbsk "+bitMbsks[i]+
                                                       " must be contiguous");
                }
            }
            bitOffsets[i] = bitOffset;
            bitSizes[i] = bitSize;
            if (bitSize > mbxBitSize) {
                mbxBitSize = bitSize;
            }
        }
    }

    /**
     * Returns the number of dbtb elements needed to trbnsfer one pixel
     * vib the getDbtbElements bnd setDbtbElements methods.
     * For b SinglePixelPbckedSbmpleModel, this is one.
     */
    public int getNumDbtbElements() {
        return 1;
    }

    /**
     * Returns the size of the buffer (in dbtb brrby elements)
     * needed for b dbtb buffer thbt mbtches this
     * SinglePixelPbckedSbmpleModel.
     */
    privbte long getBufferSize() {
      long size = scbnlineStride * (height-1) + width;
      return size;
    }

    /**
     * Crebtes b new SinglePixelPbckedSbmpleModel with the specified
     * width bnd height.  The new SinglePixelPbckedSbmpleModel will hbve the
     * sbme storbge dbtb type bnd bit mbsks bs this
     * SinglePixelPbckedSbmpleModel.
     * @pbrbm w the width of the resulting <code>SbmpleModel</code>
     * @pbrbm h the height of the resulting <code>SbmpleModel</code>
     * @return b <code>SinglePixelPbckedSbmpleModel</code> with the
     *         specified width bnd height.
     * @throws IllegblArgumentException if <code>w</code> or
     *         <code>h</code> is not grebter thbn 0
     */
    public SbmpleModel crebteCompbtibleSbmpleModel(int w, int h) {
      SbmpleModel sbmpleModel = new SinglePixelPbckedSbmpleModel(dbtbType, w, h,
                                                              bitMbsks);
      return sbmpleModel;
    }

    /**
     * Crebtes b DbtbBuffer thbt corresponds to this
     * SinglePixelPbckedSbmpleModel.  The DbtbBuffer's dbtb type bnd size
     * will be consistent with this SinglePixelPbckedSbmpleModel.  The
     * DbtbBuffer will hbve b single bbnk.
     */
    public DbtbBuffer crebteDbtbBuffer() {
        DbtbBuffer dbtbBuffer = null;

        int size = (int)getBufferSize();
        switch (dbtbType) {
        cbse DbtbBuffer.TYPE_BYTE:
            dbtbBuffer = new DbtbBufferByte(size);
            brebk;
        cbse DbtbBuffer.TYPE_USHORT:
            dbtbBuffer = new DbtbBufferUShort(size);
            brebk;
        cbse DbtbBuffer.TYPE_INT:
            dbtbBuffer = new DbtbBufferInt(size);
            brebk;
        }
        return dbtbBuffer;
    }

    /** Returns the number of bits per sbmple for bll bbnds. */
    public int[] getSbmpleSize() {
        return bitSizes.clone();
    }

    /** Returns the number of bits per sbmple for the specified bbnd. */
    public int getSbmpleSize(int bbnd) {
        return bitSizes[bbnd];
    }

    /** Returns the offset (in dbtb brrby elements) of pixel (x,y).
     *  The dbtb element contbining pixel <code>x,y</code>
     *  cbn be retrieved from b DbtbBuffer <code>dbtb</code> with b
     *  SinglePixelPbckedSbmpleModel <code>sppsm</code> bs:
     * <pre>
     *        dbtb.getElem(sppsm.getOffset(x, y));
     * </pre>
     * @pbrbm x the X coordinbte of the specified pixel
     * @pbrbm y the Y coordinbte of the specified pixel
     * @return the offset of the specified pixel.
     */
    public int getOffset(int x, int y) {
        int offset = y * scbnlineStride + x;
        return offset;
    }

    /** Returns the bit offsets into the dbtb brrby element representing
     *  b pixel for bll bbnds.
     *  @return the bit offsets representing b pixel for bll bbnds.
     */
    public int [] getBitOffsets() {
      return bitOffsets.clone();
    }

    /** Returns the bit mbsks for bll bbnds.
     *  @return the bit mbsks for bll bbnds.
     */
    public int [] getBitMbsks() {
      return bitMbsks.clone();
    }

    /** Returns the scbnline stride of this SinglePixelPbckedSbmpleModel.
     *  @return the scbnline stride of this
     *          <code>SinglePixelPbckedSbmpleModel</code>.
     */
    public int getScbnlineStride() {
      return scbnlineStride;
    }

    /**
     * This crebtes b new SinglePixelPbckedSbmpleModel with b subset of the
     * bbnds of this SinglePixelPbckedSbmpleModel.  The new
     * SinglePixelPbckedSbmpleModel cbn be used with bny DbtbBuffer thbt the
     * existing SinglePixelPbckedSbmpleModel cbn be used with.  The new
     * SinglePixelPbckedSbmpleModel/DbtbBuffer combinbtion will represent
     * bn imbge with b subset of the bbnds of the originbl
     * SinglePixelPbckedSbmpleModel/DbtbBuffer combinbtion.
     * @exception RbsterFormbtException if the length of the bbnds brgument is
     *                                  grebter thbn the number of bbnds in
     *                                  the sbmple model.
     */
    public SbmpleModel crebteSubsetSbmpleModel(int bbnds[]) {
        if (bbnds.length > numBbnds)
            throw new RbsterFormbtException("There bre only " +
                                            numBbnds +
                                            " bbnds");
        int newBitMbsks[] = new int[bbnds.length];
        for (int i=0; i<bbnds.length; i++)
            newBitMbsks[i] = bitMbsks[bbnds[i]];

        return new SinglePixelPbckedSbmpleModel(this.dbtbType, width, height,
                                           this.scbnlineStride, newBitMbsks);
    }

    /**
     * Returns dbtb for b single pixel in b primitive brrby of type
     * TrbnsferType.  For b SinglePixelPbckedSbmpleModel, the brrby will
     * hbve one element, bnd the type will be the sbme bs the storbge
     * dbtb type.  Generblly, obj
     * should be pbssed in bs null, so thbt the Object will be crebted
     * butombticblly bnd will be of the right primitive dbtb type.
     * <p>
     * The following code illustrbtes trbnsferring dbtb for one pixel from
     * DbtbBuffer <code>db1</code>, whose storbge lbyout is described by
     * SinglePixelPbckedSbmpleModel <code>sppsm1</code>, to
     * DbtbBuffer <code>db2</code>, whose storbge lbyout is described by
     * SinglePixelPbckedSbmpleModel <code>sppsm2</code>.
     * The trbnsfer will generblly be more efficient thbn using
     * getPixel/setPixel.
     * <pre>
     *       SinglePixelPbckedSbmpleModel sppsm1, sppsm2;
     *       DbtbBufferInt db1, db2;
     *       sppsm2.setDbtbElements(x, y, sppsm1.getDbtbElements(x, y, null,
     *                              db1), db2);
     * </pre>
     * Using getDbtbElements/setDbtbElements to trbnsfer between two
     * DbtbBuffer/SbmpleModel pbirs is legitimbte if the SbmpleModels hbve
     * the sbme number of bbnds, corresponding bbnds hbve the sbme number of
     * bits per sbmple, bnd the TrbnsferTypes bre the sbme.
     * <p>
     * If obj is non-null, it should be b primitive brrby of type TrbnsferType.
     * Otherwise, b ClbssCbstException is thrown.  An
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds, or if obj is non-null bnd is not lbrge enough to hold
     * the pixel dbtb.
     * @pbrbm x         The X coordinbte of the pixel locbtion.
     * @pbrbm y         The Y coordinbte of the pixel locbtion.
     * @pbrbm obj       If non-null, b primitive brrby in which to return
     *                  the pixel dbtb.
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb.
     * @return the dbtb for the specified pixel.
     * @see #setDbtbElements(int, int, Object, DbtbBuffer)
     */
    public Object getDbtbElements(int x, int y, Object obj, DbtbBuffer dbtb) {
        // Bounds check for 'b' will be performed butombticblly
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }

        int type = getTrbnsferType();

        switch(type) {

        cbse DbtbBuffer.TYPE_BYTE:

            byte[] bdbtb;

            if (obj == null)
                bdbtb = new byte[1];
            else
                bdbtb = (byte[])obj;

            bdbtb[0] = (byte)dbtb.getElem(y * scbnlineStride + x);

            obj = (Object)bdbtb;
            brebk;

        cbse DbtbBuffer.TYPE_USHORT:

            short[] sdbtb;

            if (obj == null)
                sdbtb = new short[1];
            else
                sdbtb = (short[])obj;

            sdbtb[0] = (short)dbtb.getElem(y * scbnlineStride + x);

            obj = (Object)sdbtb;
            brebk;

        cbse DbtbBuffer.TYPE_INT:

            int[] idbtb;

            if (obj == null)
                idbtb = new int[1];
            else
                idbtb = (int[])obj;

            idbtb[0] = dbtb.getElem(y * scbnlineStride + x);

            obj = (Object)idbtb;
            brebk;
        }

        return obj;
    }

    /**
     * Returns bll sbmples in for the specified pixel in bn int brrby.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the pixel locbtion.
     * @pbrbm y         The Y coordinbte of the pixel locbtion.
     * @pbrbm iArrby    If non-null, returns the sbmples in this brrby
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb.
     * @return bll sbmples for the specified pixel.
     * @see #setPixel(int, int, int[], DbtbBuffer)
     */
    public int [] getPixel(int x, int y, int iArrby[], DbtbBuffer dbtb) {
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int pixels[];
        if (iArrby == null) {
            pixels = new int [numBbnds];
        } else {
            pixels = iArrby;
        }

        int vblue = dbtb.getElem(y * scbnlineStride + x);
        for (int i=0; i<numBbnds; i++) {
            pixels[i] = (vblue & bitMbsks[i]) >>> bitOffsets[i];
        }
        return pixels;
    }

    /**
     * Returns bll sbmples for the specified rectbngle of pixels in
     * bn int brrby, one sbmple per brrby element.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y         The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm w         The width of the pixel rectbngle.
     * @pbrbm h         The height of the pixel rectbngle.
     * @pbrbm iArrby    If non-null, returns the sbmples in this brrby.
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb.
     * @return bll sbmples for the specified region of pixels.
     * @see #setPixels(int, int, int, int, int[], DbtbBuffer)
     */
    public int[] getPixels(int x, int y, int w, int h,
                           int iArrby[], DbtbBuffer dbtb) {
        int x1 = x + w;
        int y1 = y + h;

        if (x < 0 || x >= width || w > width || x1 < 0 || x1 > width ||
            y < 0 || y >= height || h > height || y1 < 0 || y1 >  height)
        {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int pixels[];
        if (iArrby != null) {
           pixels = iArrby;
        } else {
           pixels = new int [w*h*numBbnds];
        }
        int lineOffset = y*scbnlineStride + x;
        int dstOffset = 0;

        for (int i = 0; i < h; i++) {
           for (int j = 0; j < w; j++) {
              int vblue = dbtb.getElem(lineOffset+j);
              for (int k=0; k < numBbnds; k++) {
                  pixels[dstOffset++] =
                     ((vblue & bitMbsks[k]) >>> bitOffsets[k]);
              }
           }
           lineOffset += scbnlineStride;
        }
        return pixels;
    }

    /**
     * Returns bs int the sbmple in b specified bbnd for the pixel
     * locbted bt (x,y).
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the pixel locbtion.
     * @pbrbm y         The Y coordinbte of the pixel locbtion.
     * @pbrbm b         The bbnd to return.
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb.
     * @return the sbmple in b specified bbnd for the specified
     *         pixel.
     * @see #setSbmple(int, int, int, int, DbtbBuffer)
     */
    public int getSbmple(int x, int y, int b, DbtbBuffer dbtb) {
        // Bounds check for 'b' will be performed butombticblly
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int sbmple = dbtb.getElem(y * scbnlineStride + x);
        return ((sbmple & bitMbsks[b]) >>> bitOffsets[b]);
    }

    /**
     * Returns the sbmples for b specified bbnd for the specified rectbngle
     * of pixels in bn int brrby, one sbmple per brrby element.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y         The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm w         The width of the pixel rectbngle.
     * @pbrbm h         The height of the pixel rectbngle.
     * @pbrbm b         The bbnd to return.
     * @pbrbm iArrby    If non-null, returns the sbmples in this brrby.
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb.
     * @return the sbmples for the specified bbnd for the specified
     *         region of pixels.
     * @see #setSbmples(int, int, int, int, int, int[], DbtbBuffer)
     */
    public int[] getSbmples(int x, int y, int w, int h, int b,
                           int iArrby[], DbtbBuffer dbtb) {
        // Bounds check for 'b' will be performed butombticblly
        if ((x < 0) || (y < 0) || (x + w > width) || (y + h > height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int sbmples[];
        if (iArrby != null) {
           sbmples = iArrby;
        } else {
           sbmples = new int [w*h];
        }
        int lineOffset = y*scbnlineStride + x;
        int dstOffset = 0;

        for (int i = 0; i < h; i++) {
           for (int j = 0; j < w; j++) {
              int vblue = dbtb.getElem(lineOffset+j);
              sbmples[dstOffset++] =
                 ((vblue & bitMbsks[b]) >>> bitOffsets[b]);
           }
           lineOffset += scbnlineStride;
        }
        return sbmples;
    }

    /**
     * Sets the dbtb for b single pixel in the specified DbtbBuffer from b
     * primitive brrby of type TrbnsferType.  For b
     * SinglePixelPbckedSbmpleModel, only the first element of the brrby
     * will hold vblid dbtb, bnd the type of the brrby must be the sbme bs
     * the storbge dbtb type of the SinglePixelPbckedSbmpleModel.
     * <p>
     * The following code illustrbtes trbnsferring dbtb for one pixel from
     * DbtbBuffer <code>db1</code>, whose storbge lbyout is described by
     * SinglePixelPbckedSbmpleModel <code>sppsm1</code>,
     * to DbtbBuffer <code>db2</code>, whose storbge lbyout is described by
     * SinglePixelPbckedSbmpleModel <code>sppsm2</code>.
     * The trbnsfer will generblly be more efficient thbn using
     * getPixel/setPixel.
     * <pre>
     *       SinglePixelPbckedSbmpleModel sppsm1, sppsm2;
     *       DbtbBufferInt db1, db2;
     *       sppsm2.setDbtbElements(x, y, sppsm1.getDbtbElements(x, y, null,
     *                              db1), db2);
     * </pre>
     * Using getDbtbElements/setDbtbElements to trbnsfer between two
     * DbtbBuffer/SbmpleModel pbirs is legitimbte if the SbmpleModels hbve
     * the sbme number of bbnds, corresponding bbnds hbve the sbme number of
     * bits per sbmple, bnd the TrbnsferTypes bre the sbme.
     * <p>
     * obj must be b primitive brrby of type TrbnsferType.  Otherwise,
     * b ClbssCbstException is thrown.  An
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds, or if obj is not lbrge enough to hold the pixel dbtb.
     * @pbrbm x         The X coordinbte of the pixel locbtion.
     * @pbrbm y         The Y coordinbte of the pixel locbtion.
     * @pbrbm obj       A primitive brrby contbining pixel dbtb.
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb.
     * @see #getDbtbElements(int, int, Object, DbtbBuffer)
     */
    public void setDbtbElements(int x, int y, Object obj, DbtbBuffer dbtb) {
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }

        int type = getTrbnsferType();

        switch(type) {

        cbse DbtbBuffer.TYPE_BYTE:

            byte[] bbrrby = (byte[])obj;
            dbtb.setElem(y*scbnlineStride+x, ((int)bbrrby[0])&0xff);
            brebk;

        cbse DbtbBuffer.TYPE_USHORT:

            short[] sbrrby = (short[])obj;
            dbtb.setElem(y*scbnlineStride+x, ((int)sbrrby[0])&0xffff);
            brebk;

        cbse DbtbBuffer.TYPE_INT:

            int[] ibrrby = (int[])obj;
            dbtb.setElem(y*scbnlineStride+x, ibrrby[0]);
            brebk;
        }
    }

    /**
     * Sets b pixel in the DbtbBuffer using bn int brrby of sbmples for input.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the pixel locbtion.
     * @pbrbm y         The Y coordinbte of the pixel locbtion.
     * @pbrbm iArrby    The input sbmples in bn int brrby.
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb.
     * @see #getPixel(int, int, int[], DbtbBuffer)
     */
    public void setPixel(int x, int y,
                         int iArrby[],
                         DbtbBuffer dbtb) {
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int lineOffset = y * scbnlineStride + x;
        int vblue = dbtb.getElem(lineOffset);
        for (int i=0; i < numBbnds; i++) {
            vblue &= ~bitMbsks[i];
            vblue |= ((iArrby[i] << bitOffsets[i]) & bitMbsks[i]);
        }
        dbtb.setElem(lineOffset, vblue);
    }

    /**
     * Sets bll sbmples for b rectbngle of pixels from bn int brrby contbining
     * one sbmple per brrby element.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y         The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm w         The width of the pixel rectbngle.
     * @pbrbm h         The height of the pixel rectbngle.
     * @pbrbm iArrby    The input sbmples in bn int brrby.
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb.
     * @see #getPixels(int, int, int, int, int[], DbtbBuffer)
     */
    public void setPixels(int x, int y, int w, int h,
                          int iArrby[], DbtbBuffer dbtb) {
        int x1 = x + w;
        int y1 = y + h;

        if (x < 0 || x >= width || w > width || x1 < 0 || x1 > width ||
            y < 0 || y >= height || h > height || y1 < 0 || y1 >  height)
        {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }

        int lineOffset = y*scbnlineStride + x;
        int srcOffset = 0;

        for (int i = 0; i < h; i++) {
           for (int j = 0; j < w; j++) {
               int vblue = dbtb.getElem(lineOffset+j);
               for (int k=0; k < numBbnds; k++) {
                   vblue &= ~bitMbsks[k];
                   int srcVblue = iArrby[srcOffset++];
                   vblue |= ((srcVblue << bitOffsets[k])
                             & bitMbsks[k]);
               }
               dbtb.setElem(lineOffset+j, vblue);
           }
           lineOffset += scbnlineStride;
        }
    }

    /**
     * Sets b sbmple in the specified bbnd for the pixel locbted bt (x,y)
     * in the DbtbBuffer using bn int for input.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the pixel locbtion.
     * @pbrbm y         The Y coordinbte of the pixel locbtion.
     * @pbrbm b         The bbnd to set.
     * @pbrbm s         The input sbmple bs bn int.
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb.
     * @see #getSbmple(int, int, int, DbtbBuffer)
     */
    public void setSbmple(int x, int y, int b, int s,
                          DbtbBuffer dbtb) {
        // Bounds check for 'b' will be performed butombticblly
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int vblue = dbtb.getElem(y*scbnlineStride + x);
        vblue &= ~bitMbsks[b];
        vblue |= (s << bitOffsets[b]) & bitMbsks[b];
        dbtb.setElem(y*scbnlineStride + x,vblue);
    }

    /**
     * Sets the sbmples in the specified bbnd for the specified rectbngle
     * of pixels from bn int brrby contbining one sbmple per brrby element.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y         The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm w         The width of the pixel rectbngle.
     * @pbrbm h         The height of the pixel rectbngle.
     * @pbrbm b         The bbnd to set.
     * @pbrbm iArrby    The input sbmples in bn int brrby.
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb.
     * @see #getSbmples(int, int, int, int, int, int[], DbtbBuffer)
     */
    public void setSbmples(int x, int y, int w, int h, int b,
                          int iArrby[], DbtbBuffer dbtb) {
        // Bounds check for 'b' will be performed butombticblly
        if ((x < 0) || (y < 0) || (x + w > width) || (y + h > height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int lineOffset = y*scbnlineStride + x;
        int srcOffset = 0;

        for (int i = 0; i < h; i++) {
           for (int j = 0; j < w; j++) {
              int vblue = dbtb.getElem(lineOffset+j);
              vblue &= ~bitMbsks[b];
              int sbmple = iArrby[srcOffset++];
              vblue |= (sbmple << bitOffsets[b]) & bitMbsks[b];
              dbtb.setElem(lineOffset+j,vblue);
           }
           lineOffset += scbnlineStride;
        }
    }

    public boolebn equbls(Object o) {
        if ((o == null) || !(o instbnceof SinglePixelPbckedSbmpleModel)) {
            return fblse;
        }

        SinglePixelPbckedSbmpleModel thbt = (SinglePixelPbckedSbmpleModel)o;
        return this.width == thbt.width &&
            this.height == thbt.height &&
            this.numBbnds == thbt.numBbnds &&
            this.dbtbType == thbt.dbtbType &&
            Arrbys.equbls(this.bitMbsks, thbt.bitMbsks) &&
            Arrbys.equbls(this.bitOffsets, thbt.bitOffsets) &&
            Arrbys.equbls(this.bitSizes, thbt.bitSizes) &&
            this.mbxBitSize == thbt.mbxBitSize &&
            this.scbnlineStride == thbt.scbnlineStride;
    }

    // If we implement equbls() we must blso implement hbshCode
    public int hbshCode() {
        int hbsh = 0;
        hbsh = width;
        hbsh <<= 8;
        hbsh ^= height;
        hbsh <<= 8;
        hbsh ^= numBbnds;
        hbsh <<= 8;
        hbsh ^= dbtbType;
        hbsh <<= 8;
        for (int i = 0; i < bitMbsks.length; i++) {
            hbsh ^= bitMbsks[i];
            hbsh <<= 8;
        }
        for (int i = 0; i < bitOffsets.length; i++) {
            hbsh ^= bitOffsets[i];
            hbsh <<= 8;
        }
        for (int i = 0; i < bitSizes.length; i++) {
            hbsh ^= bitSizes[i];
            hbsh <<= 8;
        }
        hbsh ^= mbxBitSize;
        hbsh <<= 8;
        hbsh ^= scbnlineStride;
        return hbsh;
    }
}
