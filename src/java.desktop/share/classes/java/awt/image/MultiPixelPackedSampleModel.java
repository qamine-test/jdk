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

/**
 * The <code>MultiPixelPbckedSbmpleModel</code> clbss represents
 * one-bbnded imbges bnd cbn pbck multiple one-sbmple
 * pixels into one dbtb element.  Pixels bre not bllowed to spbn dbtb elements.
 * The dbtb type cbn be DbtbBuffer.TYPE_BYTE, DbtbBuffer.TYPE_USHORT,
 * or DbtbBuffer.TYPE_INT.  Ebch pixel must be b power of 2 number of bits
 * bnd b power of 2 number of pixels must fit exbctly in one dbtb element.
 * Pixel bit stride is equbl to the number of bits per pixel.  Scbnline
 * stride is in dbtb elements bnd the lbst severbl dbtb elements might be
 * pbdded with unused pixels.  Dbtb bit offset is the offset in bits from
 * the beginning of the {@link DbtbBuffer} to the first pixel bnd must be
 * b multiple of pixel bit stride.
 * <p>
 * The following code illustrbtes extrbcting the bits for pixel
 * <code>x,&nbsp;y</code> from <code>DbtbBuffer</code> <code>dbtb</code>
 * bnd storing the pixel dbtb in dbtb elements of type
 * <code>dbtbType</code>:
 * <pre>{@code
 *      int dbtbElementSize = DbtbBuffer.getDbtbTypeSize(dbtbType);
 *      int bitnum = dbtbBitOffset + x*pixelBitStride;
 *      int element = dbtb.getElem(y*scbnlineStride + bitnum/dbtbElementSize);
 *      int shift = dbtbElementSize - (bitnum & (dbtbElementSize-1))
 *                  - pixelBitStride;
 *      int pixel = (element >> shift) & ((1 << pixelBitStride) - 1);
 * }</pre>
 */

public clbss MultiPixelPbckedSbmpleModel extends SbmpleModel
{
    /** The number of bits from one pixel to the next. */
    int pixelBitStride;

    /** Bitmbsk thbt extrbcts the rightmost pixel of b dbtb element. */
    int bitMbsk;

    /**
      * The number of pixels thbt fit in b dbtb element.  Also used
      * bs the number of bits per pixel.
      */
    int pixelsPerDbtbElement;

    /** The size of b dbtb element in bits. */
    int dbtbElementSize;

    /** The bit offset into the dbtb brrby where the first pixel begins.
     */
    int dbtbBitOffset;

    /** ScbnlineStride of the dbtb buffer described in dbtb brrby elements. */
    int scbnlineStride;

    /**
     * Constructs b <code>MultiPixelPbckedSbmpleModel</code> with the
     * specified dbtb type, width, height bnd number of bits per pixel.
     * @pbrbm dbtbType  the dbtb type for storing sbmples
     * @pbrbm w         the width, in pixels, of the region of
     *                  imbge dbtb described
     * @pbrbm h         the height, in pixels, of the region of
     *                  imbge dbtb described
     * @pbrbm numberOfBits the number of bits per pixel
     * @throws IllegblArgumentException if <code>dbtbType</code> is not
     *         either <code>DbtbBuffer.TYPE_BYTE</code>,
     *         <code>DbtbBuffer.TYPE_USHORT</code>, or
     *         <code>DbtbBuffer.TYPE_INT</code>
     */
    public MultiPixelPbckedSbmpleModel(int dbtbType,
                                       int w,
                                       int h,
                                       int numberOfBits) {
        this(dbtbType,w,h,
             numberOfBits,
            (w*numberOfBits+DbtbBuffer.getDbtbTypeSize(dbtbType)-1)/
                DbtbBuffer.getDbtbTypeSize(dbtbType),
             0);
        if (dbtbType != DbtbBuffer.TYPE_BYTE &&
            dbtbType != DbtbBuffer.TYPE_USHORT &&
            dbtbType != DbtbBuffer.TYPE_INT) {
            throw new IllegblArgumentException("Unsupported dbtb type "+
                                               dbtbType);
        }
    }

    /**
     * Constructs b <code>MultiPixelPbckedSbmpleModel</code> with
     * specified dbtb type, width, height, number of bits per pixel,
     * scbnline stride bnd dbtb bit offset.
     * @pbrbm dbtbType  the dbtb type for storing sbmples
     * @pbrbm w         the width, in pixels, of the region of
     *                  imbge dbtb described
     * @pbrbm h         the height, in pixels, of the region of
     *                  imbge dbtb described
     * @pbrbm numberOfBits the number of bits per pixel
     * @pbrbm scbnlineStride the line stride of the imbge dbtb
     * @pbrbm dbtbBitOffset the dbtb bit offset for the region of imbge
     *                  dbtb described
     * @exception RbsterFormbtException if the number of bits per pixel
     *                  is not b power of 2 or if b power of 2 number of
     *                  pixels do not fit in one dbtb element.
     * @throws IllegblArgumentException if <code>w</code> or
     *         <code>h</code> is not grebter thbn 0
     * @throws IllegblArgumentException if <code>dbtbType</code> is not
     *         either <code>DbtbBuffer.TYPE_BYTE</code>,
     *         <code>DbtbBuffer.TYPE_USHORT</code>, or
     *         <code>DbtbBuffer.TYPE_INT</code>
     */
    public MultiPixelPbckedSbmpleModel(int dbtbType, int w, int h,
                                       int numberOfBits,
                                       int scbnlineStride,
                                       int dbtbBitOffset) {
        super(dbtbType, w, h, 1);
        if (dbtbType != DbtbBuffer.TYPE_BYTE &&
            dbtbType != DbtbBuffer.TYPE_USHORT &&
            dbtbType != DbtbBuffer.TYPE_INT) {
            throw new IllegblArgumentException("Unsupported dbtb type "+
                                               dbtbType);
        }
        this.dbtbType = dbtbType;
        this.pixelBitStride = numberOfBits;
        this.scbnlineStride = scbnlineStride;
        this.dbtbBitOffset = dbtbBitOffset;
        this.dbtbElementSize = DbtbBuffer.getDbtbTypeSize(dbtbType);
        this.pixelsPerDbtbElement = dbtbElementSize/numberOfBits;
        if (pixelsPerDbtbElement*numberOfBits != dbtbElementSize) {
           throw new RbsterFormbtException("MultiPixelPbckedSbmpleModel " +
                                             "does not bllow pixels to " +
                                             "spbn dbtb element boundbries");
        }
        this.bitMbsk = (1 << numberOfBits) - 1;
    }


    /**
     * Crebtes b new <code>MultiPixelPbckedSbmpleModel</code> with the
     * specified width bnd height.  The new
     * <code>MultiPixelPbckedSbmpleModel</code> hbs the
     * sbme storbge dbtb type bnd number of bits per pixel bs this
     * <code>MultiPixelPbckedSbmpleModel</code>.
     * @pbrbm w the specified width
     * @pbrbm h the specified height
     * @return b {@link SbmpleModel} with the specified width bnd height
     * bnd with the sbme storbge dbtb type bnd number of bits per pixel
     * bs this <code>MultiPixelPbckedSbmpleModel</code>.
     * @throws IllegblArgumentException if <code>w</code> or
     *         <code>h</code> is not grebter thbn 0
     */
    public SbmpleModel crebteCompbtibleSbmpleModel(int w, int h) {
      SbmpleModel sbmpleModel =
            new MultiPixelPbckedSbmpleModel(dbtbType, w, h, pixelBitStride);
      return sbmpleModel;
    }

    /**
     * Crebtes b <code>DbtbBuffer</code> thbt corresponds to this
     * <code>MultiPixelPbckedSbmpleModel</code>.  The
     * <code>DbtbBuffer</code> object's dbtb type bnd size
     * is consistent with this <code>MultiPixelPbckedSbmpleModel</code>.
     * The <code>DbtbBuffer</code> hbs b single bbnk.
     * @return b <code>DbtbBuffer</code> with the sbme dbtb type bnd
     * size bs this <code>MultiPixelPbckedSbmpleModel</code>.
     */
    public DbtbBuffer crebteDbtbBuffer() {
        DbtbBuffer dbtbBuffer = null;

        int size = scbnlineStride*height;
        switch (dbtbType) {
        cbse DbtbBuffer.TYPE_BYTE:
            dbtbBuffer = new DbtbBufferByte(size+(dbtbBitOffset+7)/8);
            brebk;
        cbse DbtbBuffer.TYPE_USHORT:
            dbtbBuffer = new DbtbBufferUShort(size+(dbtbBitOffset+15)/16);
            brebk;
        cbse DbtbBuffer.TYPE_INT:
            dbtbBuffer = new DbtbBufferInt(size+(dbtbBitOffset+31)/32);
            brebk;
        }
        return dbtbBuffer;
    }

    /**
     * Returns the number of dbtb elements needed to trbnsfer one pixel
     * vib the {@link #getDbtbElements} bnd {@link #setDbtbElements}
     * methods.  For b <code>MultiPixelPbckedSbmpleModel</code>, this is
     * one.
     * @return the number of dbtb elements.
     */
    public int getNumDbtbElements() {
        return 1;
    }

    /**
     * Returns the number of bits per sbmple for bll bbnds.
     * @return the number of bits per sbmple.
     */
    public int[] getSbmpleSize() {
        int sbmpleSize[] = {pixelBitStride};
        return sbmpleSize;
    }

    /**
     * Returns the number of bits per sbmple for the specified bbnd.
     * @pbrbm bbnd the specified bbnd
     * @return the number of bits per sbmple for the specified bbnd.
     */
    public int getSbmpleSize(int bbnd) {
        return pixelBitStride;
    }

    /**
     * Returns the offset of pixel (x,&nbsp;y) in dbtb brrby elements.
     * @pbrbm x the X coordinbte of the specified pixel
     * @pbrbm y the Y coordinbte of the specified pixel
     * @return the offset of the specified pixel.
     */
    public int getOffset(int x, int y) {
        int offset = y * scbnlineStride;
        offset +=  (x*pixelBitStride+dbtbBitOffset)/dbtbElementSize;
        return offset;
    }

    /**
     *  Returns the offset, in bits, into the dbtb element in which it is
     *  stored for the <code>x</code>th pixel of b scbnline.
     *  This offset is the sbme for bll scbnlines.
     *  @pbrbm x the specified pixel
     *  @return the bit offset of the specified pixel.
     */
    public int getBitOffset(int x){
       return  (x*pixelBitStride+dbtbBitOffset)%dbtbElementSize;
    }

    /**
     * Returns the scbnline stride.
     * @return the scbnline stride of this
     * <code>MultiPixelPbckedSbmpleModel</code>.
     */
    public int getScbnlineStride() {
        return scbnlineStride;
    }

    /**
     * Returns the pixel bit stride in bits.  This vblue is the sbme bs
     * the number of bits per pixel.
     * @return the <code>pixelBitStride</code> of this
     * <code>MultiPixelPbckedSbmpleModel</code>.
     */
    public int getPixelBitStride() {
        return pixelBitStride;
    }

    /**
     * Returns the dbtb bit offset in bits.
     * @return the <code>dbtbBitOffset</code> of this
     * <code>MultiPixelPbckedSbmpleModel</code>.
     */
    public int getDbtbBitOffset() {
        return dbtbBitOffset;
    }

    /**
     *  Returns the TrbnsferType used to trbnsfer pixels by wby of the
     *  <code>getDbtbElements</code> bnd <code>setDbtbElements</code>
     *  methods. The TrbnsferType might or might not be the sbme bs the
     *  storbge DbtbType.  The TrbnsferType is one of
     *  DbtbBuffer.TYPE_BYTE, DbtbBuffer.TYPE_USHORT,
     *  or DbtbBuffer.TYPE_INT.
     *  @return the trbnsfertype.
     */
    public int getTrbnsferType() {
        if (pixelBitStride > 16)
            return DbtbBuffer.TYPE_INT;
        else if (pixelBitStride > 8)
            return DbtbBuffer.TYPE_USHORT;
        else
            return DbtbBuffer.TYPE_BYTE;
    }

    /**
     * Crebtes b new <code>MultiPixelPbckedSbmpleModel</code> with b
     * subset of the bbnds of this
     * <code>MultiPixelPbckedSbmpleModel</code>.  Since b
     * <code>MultiPixelPbckedSbmpleModel</code> only hbs one bbnd, the
     * bbnds brgument must hbve b length of one bnd indicbte the zeroth
     * bbnd.
     * @pbrbm bbnds the specified bbnds
     * @return b new <code>SbmpleModel</code> with b subset of bbnds of
     * this <code>MultiPixelPbckedSbmpleModel</code>.
     * @exception RbsterFormbtException if the number of bbnds requested
     * is not one.
     * @throws IllegblArgumentException if <code>w</code> or
     *         <code>h</code> is not grebter thbn 0
     */
    public SbmpleModel crebteSubsetSbmpleModel(int bbnds[]) {
        if (bbnds != null) {
           if (bbnds.length != 1)
            throw new RbsterFormbtException("MultiPixelPbckedSbmpleModel hbs "
                                            + "only one bbnd.");
        }
        SbmpleModel sm = crebteCompbtibleSbmpleModel(width, height);
        return sm;
    }

    /**
     * Returns bs <code>int</code> the sbmple in b specified bbnd for the
     * pixel locbted bt (x,&nbsp;y).  An
     * <code>ArrbyIndexOutOfBoundsException</code> is thrown if the
     * coordinbtes bre not in bounds.
     * @pbrbm x         the X coordinbte of the specified pixel
     * @pbrbm y         the Y coordinbte of the specified pixel
     * @pbrbm b         the bbnd to return, which is bssumed to be 0
     * @pbrbm dbtb      the <code>DbtbBuffer</code> contbining the imbge
     *                  dbtb
     * @return the specified bbnd contbining the sbmple of the specified
     * pixel.
     * @exception ArrbyIndexOutOfBoundsException if the specified
     *          coordinbtes bre not in bounds.
     * @see #setSbmple(int, int, int, int, DbtbBuffer)
     */
    public int getSbmple(int x, int y, int b, DbtbBuffer dbtb) {
        // 'b' must be 0
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height) ||
            (b != 0)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int bitnum = dbtbBitOffset + x*pixelBitStride;
        int element = dbtb.getElem(y*scbnlineStride + bitnum/dbtbElementSize);
        int shift = dbtbElementSize - (bitnum & (dbtbElementSize-1))
                    - pixelBitStride;
        return (element >> shift) & bitMbsk;
    }

    /**
     * Sets b sbmple in the specified bbnd for the pixel locbted bt
     * (x,&nbsp;y) in the <code>DbtbBuffer</code> using bn
     * <code>int</code> for input.
     * An <code>ArrbyIndexOutOfBoundsException</code> is thrown if the
     * coordinbtes bre not in bounds.
     * @pbrbm x the X coordinbte of the specified pixel
     * @pbrbm y the Y coordinbte of the specified pixel
     * @pbrbm b the bbnd to return, which is bssumed to be 0
     * @pbrbm s the input sbmple bs bn <code>int</code>
     * @pbrbm dbtb the <code>DbtbBuffer</code> where imbge dbtb is stored
     * @exception ArrbyIndexOutOfBoundsException if the coordinbtes bre
     * not in bounds.
     * @see #getSbmple(int, int, int, DbtbBuffer)
     */
    public void setSbmple(int x, int y, int b, int s,
                          DbtbBuffer dbtb) {
        // 'b' must be 0
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height) ||
            (b != 0)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int bitnum = dbtbBitOffset + x * pixelBitStride;
        int index = y * scbnlineStride + (bitnum / dbtbElementSize);
        int shift = dbtbElementSize - (bitnum & (dbtbElementSize-1))
                    - pixelBitStride;
        int element = dbtb.getElem(index);
        element &= ~(bitMbsk << shift);
        element |= (s & bitMbsk) << shift;
        dbtb.setElem(index,element);
    }

    /**
     * Returns dbtb for b single pixel in b primitive brrby of type
     * TrbnsferType.  For b <code>MultiPixelPbckedSbmpleModel</code>,
     * the brrby hbs one element, bnd the type is the smbllest of
     * DbtbBuffer.TYPE_BYTE, DbtbBuffer.TYPE_USHORT, or DbtbBuffer.TYPE_INT
     * thbt cbn hold b single pixel.  Generblly, <code>obj</code>
     * should be pbssed in bs <code>null</code>, so thbt the
     * <code>Object</code> is crebted butombticblly bnd is the
     * correct primitive dbtb type.
     * <p>
     * The following code illustrbtes trbnsferring dbtb for one pixel from
     * <code>DbtbBuffer</code> <code>db1</code>, whose storbge lbyout is
     * described by <code>MultiPixelPbckedSbmpleModel</code>
     * <code>mppsm1</code>, to <code>DbtbBuffer</code> <code>db2</code>,
     * whose storbge lbyout is described by
     * <code>MultiPixelPbckedSbmpleModel</code> <code>mppsm2</code>.
     * The trbnsfer is generblly more efficient thbn using
     * <code>getPixel</code> or <code>setPixel</code>.
     * <pre>
     *       MultiPixelPbckedSbmpleModel mppsm1, mppsm2;
     *       DbtbBufferInt db1, db2;
     *       mppsm2.setDbtbElements(x, y, mppsm1.getDbtbElements(x, y, null,
     *                              db1), db2);
     * </pre>
     * Using <code>getDbtbElements</code> or <code>setDbtbElements</code>
     * to trbnsfer between two <code>DbtbBuffer/SbmpleModel</code> pbirs
     * is legitimbte if the <code>SbmpleModels</code> hbve the sbme number
     * of bbnds, corresponding bbnds hbve the sbme number of
     * bits per sbmple, bnd the TrbnsferTypes bre the sbme.
     * <p>
     * If <code>obj</code> is not <code>null</code>, it should be b
     * primitive brrby of type TrbnsferType.  Otherwise, b
     * <code>ClbssCbstException</code> is thrown.  An
     * <code>ArrbyIndexOutOfBoundsException</code> is thrown if the
     * coordinbtes bre not in bounds, or if <code>obj</code> is not
     * <code>null</code> bnd is not lbrge enough to hold the pixel dbtb.
     * @pbrbm x the X coordinbte of the specified pixel
     * @pbrbm y the Y coordinbte of the specified pixel
     * @pbrbm obj b primitive brrby in which to return the pixel dbtb or
     *          <code>null</code>.
     * @pbrbm dbtb the <code>DbtbBuffer</code> contbining the imbge dbtb.
     * @return bn <code>Object</code> contbining dbtb for the specified
     *  pixel.
     * @exception ClbssCbstException if <code>obj</code> is not b
     *  primitive brrby of type TrbnsferType or is not <code>null</code>
     * @exception ArrbyIndexOutOfBoundsException if the coordinbtes bre
     * not in bounds, or if <code>obj</code> is not <code>null</code> or
     * not lbrge enough to hold the pixel dbtb
     * @see #setDbtbElements(int, int, Object, DbtbBuffer)
     */
    public Object getDbtbElements(int x, int y, Object obj, DbtbBuffer dbtb) {
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }

        int type = getTrbnsferType();
        int bitnum = dbtbBitOffset + x*pixelBitStride;
        int shift = dbtbElementSize - (bitnum & (dbtbElementSize-1))
                    - pixelBitStride;
        int element = 0;

        switch(type) {

        cbse DbtbBuffer.TYPE_BYTE:

            byte[] bdbtb;

            if (obj == null)
                bdbtb = new byte[1];
            else
                bdbtb = (byte[])obj;

            element = dbtb.getElem(y*scbnlineStride +
                                    bitnum/dbtbElementSize);
            bdbtb[0] = (byte)((element >> shift) & bitMbsk);

            obj = (Object)bdbtb;
            brebk;

        cbse DbtbBuffer.TYPE_USHORT:

            short[] sdbtb;

            if (obj == null)
                sdbtb = new short[1];
            else
                sdbtb = (short[])obj;

            element = dbtb.getElem(y*scbnlineStride +
                                   bitnum/dbtbElementSize);
            sdbtb[0] = (short)((element >> shift) & bitMbsk);

            obj = (Object)sdbtb;
            brebk;

        cbse DbtbBuffer.TYPE_INT:

            int[] idbtb;

            if (obj == null)
                idbtb = new int[1];
            else
                idbtb = (int[])obj;

            element = dbtb.getElem(y*scbnlineStride +
                                   bitnum/dbtbElementSize);
            idbtb[0] = (element >> shift) & bitMbsk;

            obj = (Object)idbtb;
            brebk;
        }

        return obj;
    }

    /**
     * Returns the specified single bbnd pixel in the first element
     * of bn <code>int</code> brrby.
     * <code>ArrbyIndexOutOfBoundsException</code> is thrown if the
     * coordinbtes bre not in bounds.
     * @pbrbm x the X coordinbte of the specified pixel
     * @pbrbm y the Y coordinbte of the specified pixel
     * @pbrbm iArrby the brrby contbining the pixel to be returned or
     *  <code>null</code>
     * @pbrbm dbtb the <code>DbtbBuffer</code> where imbge dbtb is stored
     * @return bn brrby contbining the specified pixel.
     * @exception ArrbyIndexOutOfBoundsException if the coordinbtes
     *  bre not in bounds
     * @see #setPixel(int, int, int[], DbtbBuffer)
     */
    public int[] getPixel(int x, int y, int iArrby[], DbtbBuffer dbtb) {
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int pixels[];
        if (iArrby != null) {
           pixels = iArrby;
        } else {
           pixels = new int [numBbnds];
        }
        int bitnum = dbtbBitOffset + x*pixelBitStride;
        int element = dbtb.getElem(y*scbnlineStride + bitnum/dbtbElementSize);
        int shift = dbtbElementSize - (bitnum & (dbtbElementSize-1))
                    - pixelBitStride;
        pixels[0] = (element >> shift) & bitMbsk;
        return pixels;
    }

    /**
     * Sets the dbtb for b single pixel in the specified
     * <code>DbtbBuffer</code> from b primitive brrby of type
     * TrbnsferType.  For b <code>MultiPixelPbckedSbmpleModel</code>,
     * only the first element of the brrby holds vblid dbtb,
     * bnd the type must be the smbllest of
     * DbtbBuffer.TYPE_BYTE, DbtbBuffer.TYPE_USHORT, or DbtbBuffer.TYPE_INT
     * thbt cbn hold b single pixel.
     * <p>
     * The following code illustrbtes trbnsferring dbtb for one pixel from
     * <code>DbtbBuffer</code> <code>db1</code>, whose storbge lbyout is
     * described by <code>MultiPixelPbckedSbmpleModel</code>
     * <code>mppsm1</code>, to <code>DbtbBuffer</code> <code>db2</code>,
     * whose storbge lbyout is described by
     * <code>MultiPixelPbckedSbmpleModel</code> <code>mppsm2</code>.
     * The trbnsfer is generblly more efficient thbn using
     * <code>getPixel</code> or <code>setPixel</code>.
     * <pre>
     *       MultiPixelPbckedSbmpleModel mppsm1, mppsm2;
     *       DbtbBufferInt db1, db2;
     *       mppsm2.setDbtbElements(x, y, mppsm1.getDbtbElements(x, y, null,
     *                              db1), db2);
     * </pre>
     * Using <code>getDbtbElements</code> or <code>setDbtbElements</code> to
     * trbnsfer between two <code>DbtbBuffer/SbmpleModel</code> pbirs is
     * legitimbte if the <code>SbmpleModel</code> objects hbve
     * the sbme number of bbnds, corresponding bbnds hbve the sbme number of
     * bits per sbmple, bnd the TrbnsferTypes bre the sbme.
     * <p>
     * <code>obj</code> must be b primitive brrby of type TrbnsferType.
     * Otherwise, b <code>ClbssCbstException</code> is thrown.  An
     * <code>ArrbyIndexOutOfBoundsException</code> is thrown if the
     * coordinbtes bre not in bounds, or if <code>obj</code> is not lbrge
     * enough to hold the pixel dbtb.
     * @pbrbm x the X coordinbte of the pixel locbtion
     * @pbrbm y the Y coordinbte of the pixel locbtion
     * @pbrbm obj b primitive brrby contbining pixel dbtb
     * @pbrbm dbtb the <code>DbtbBuffer</code> contbining the imbge dbtb
     * @see #getDbtbElements(int, int, Object, DbtbBuffer)
     */
    public void setDbtbElements(int x, int y, Object obj, DbtbBuffer dbtb) {
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }

        int type = getTrbnsferType();
        int bitnum = dbtbBitOffset + x * pixelBitStride;
        int index = y * scbnlineStride + (bitnum / dbtbElementSize);
        int shift = dbtbElementSize - (bitnum & (dbtbElementSize-1))
                    - pixelBitStride;
        int element = dbtb.getElem(index);
        element &= ~(bitMbsk << shift);

        switch(type) {

        cbse DbtbBuffer.TYPE_BYTE:

            byte[] bbrrby = (byte[])obj;
            element |= ( ((int)(bbrrby[0])&0xff) & bitMbsk) << shift;
            dbtb.setElem(index, element);
            brebk;

        cbse DbtbBuffer.TYPE_USHORT:

            short[] sbrrby = (short[])obj;
            element |= ( ((int)(sbrrby[0])&0xffff) & bitMbsk) << shift;
            dbtb.setElem(index, element);
            brebk;

        cbse DbtbBuffer.TYPE_INT:

            int[] ibrrby = (int[])obj;
            element |= (ibrrby[0] & bitMbsk) << shift;
            dbtb.setElem(index, element);
            brebk;
        }
    }

    /**
     * Sets b pixel in the <code>DbtbBuffer</code> using bn
     * <code>int</code> brrby for input.
     * <code>ArrbyIndexOutOfBoundsException</code> is thrown if
     * the coordinbtes bre not in bounds.
     * @pbrbm x the X coordinbte of the pixel locbtion
     * @pbrbm y the Y coordinbte of the pixel locbtion
     * @pbrbm iArrby the input pixel in bn <code>int</code> brrby
     * @pbrbm dbtb the <code>DbtbBuffer</code> contbining the imbge dbtb
     * @see #getPixel(int, int, int[], DbtbBuffer)
     */
    public void setPixel(int x, int y, int[] iArrby, DbtbBuffer dbtb) {
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int bitnum = dbtbBitOffset + x * pixelBitStride;
        int index = y * scbnlineStride + (bitnum / dbtbElementSize);
        int shift = dbtbElementSize - (bitnum & (dbtbElementSize-1))
                    - pixelBitStride;
        int element = dbtb.getElem(index);
        element &= ~(bitMbsk << shift);
        element |= (iArrby[0] & bitMbsk) << shift;
        dbtb.setElem(index,element);
    }

    public boolebn equbls(Object o) {
        if ((o == null) || !(o instbnceof MultiPixelPbckedSbmpleModel)) {
            return fblse;
        }

        MultiPixelPbckedSbmpleModel thbt = (MultiPixelPbckedSbmpleModel)o;
        return this.width == thbt.width &&
            this.height == thbt.height &&
            this.numBbnds == thbt.numBbnds &&
            this.dbtbType == thbt.dbtbType &&
            this.pixelBitStride == thbt.pixelBitStride &&
            this.bitMbsk == thbt.bitMbsk &&
            this.pixelsPerDbtbElement == thbt.pixelsPerDbtbElement &&
            this.dbtbElementSize == thbt.dbtbElementSize &&
            this.dbtbBitOffset == thbt.dbtbBitOffset &&
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
        hbsh ^= pixelBitStride;
        hbsh <<= 8;
        hbsh ^= bitMbsk;
        hbsh <<= 8;
        hbsh ^= pixelsPerDbtbElement;
        hbsh <<= 8;
        hbsh ^= dbtbElementSize;
        hbsh <<= 8;
        hbsh ^= dbtbBitOffset;
        hbsh <<= 8;
        hbsh ^= scbnlineStride;
        return hbsh;
    }
}
