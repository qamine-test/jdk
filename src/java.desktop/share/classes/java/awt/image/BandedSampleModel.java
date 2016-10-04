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
 *  This clbss represents imbge dbtb which is stored in b bbnd interlebved
 *  fbshion bnd for
 *  which ebch sbmple of b pixel occupies one dbtb element of the DbtbBuffer.
 *  It subclbsses ComponentSbmpleModel but provides b more efficient
 *  implementbtion for bccessing bbnd interlebved imbge dbtb thbn is provided
 *  by ComponentSbmpleModel.  This clbss should typicblly be used when working
 *  with imbges which store sbmple dbtb for ebch bbnd in b different bbnk of the
 *  DbtbBuffer. Accessor methods bre provided so thbt imbge dbtb cbn be
 *  mbnipulbted directly. Pixel stride is the number of
 *  dbtb brrby elements between two sbmples for the sbme bbnd on the sbme
 *  scbnline. The pixel stride for b BbndedSbmpleModel is one.
 *  Scbnline stride is the number of dbtb brrby elements between
 *  b given sbmple bnd the corresponding sbmple in the sbme column of the next
 *  scbnline.  Bbnd offsets denote the number
 *  of dbtb brrby elements from the first dbtb brrby element of the bbnk
 *  of the DbtbBuffer holding ebch bbnd to the first sbmple of the bbnd.
 *  The bbnds bre numbered from 0 to N-1.
 *  Bbnk indices denote the correspondence between b bbnk of the dbtb buffer
 *  bnd b bbnd of imbge dbtb.  This clbss supports
 *  {@link DbtbBuffer#TYPE_BYTE TYPE_BYTE},
 *  {@link DbtbBuffer#TYPE_USHORT TYPE_USHORT},
 *  {@link DbtbBuffer#TYPE_SHORT TYPE_SHORT},
 *  {@link DbtbBuffer#TYPE_INT TYPE_INT},
 *  {@link DbtbBuffer#TYPE_FLOAT TYPE_FLOAT}, bnd
 *  {@link DbtbBuffer#TYPE_DOUBLE TYPE_DOUBLE} dbtbtypes
 */


public finbl clbss BbndedSbmpleModel extends ComponentSbmpleModel
{

    /**
     * Constructs b BbndedSbmpleModel with the specified pbrbmeters.
     * The pixel stride will be one dbtb element.  The scbnline stride
     * will be the sbme bs the width.  Ebch bbnd will be stored in
     * b sepbrbte bbnk bnd bll bbnd offsets will be zero.
     * @pbrbm dbtbType  The dbtb type for storing sbmples.
     * @pbrbm w         The width (in pixels) of the region of
     *                  imbge dbtb described.
     * @pbrbm h         The height (in pixels) of the region of imbge
     *                  dbtb described.
     * @pbrbm numBbnds  The number of bbnds for the imbge dbtb.
     * @throws IllegblArgumentException if <code>dbtbType</code> is not
     *         one of the supported dbtb types
     */
    public BbndedSbmpleModel(int dbtbType, int w, int h, int numBbnds) {
        super(dbtbType, w, h, 1, w,
              BbndedSbmpleModel.crebteIndicesArrby(numBbnds),
              BbndedSbmpleModel.crebteOffsetArrby(numBbnds));
    }

    /**
     * Constructs b BbndedSbmpleModel with the specified pbrbmeters.
     * The number of bbnds will be inferred from the lengths of the
     * bbndOffsets bbnkIndices brrbys, which must be equbl.  The pixel
     * stride will be one dbtb element.
     * @pbrbm dbtbType  The dbtb type for storing sbmples.
     * @pbrbm w         The width (in pixels) of the region of
     *                  imbge dbtb described.
     * @pbrbm h         The height (in pixels) of the region of
     *                  imbge dbtb described.
     * @pbrbm scbnlineStride The line stride of the of the imbge dbtb.
     * @pbrbm bbnkIndices The bbnk index for ebch bbnd.
     * @pbrbm bbndOffsets The bbnd offset for ebch bbnd.
     * @throws IllegblArgumentException if <code>dbtbType</code> is not
     *         one of the supported dbtb types
     */
    public BbndedSbmpleModel(int dbtbType,
                             int w, int h,
                             int scbnlineStride,
                             int bbnkIndices[],
                             int bbndOffsets[]) {

        super(dbtbType, w, h, 1,scbnlineStride, bbnkIndices, bbndOffsets);
    }

    /**
     * Crebtes b new BbndedSbmpleModel with the specified
     * width bnd height.  The new BbndedSbmpleModel will hbve the sbme
     * number of bbnds, storbge dbtb type, bnd bbnk indices
     * bs this BbndedSbmpleModel.  The bbnd offsets will be compressed
     * such thbt the offset between bbnds will be w*pixelStride bnd
     * the minimum of bll of the bbnd offsets is zero.
     * @pbrbm w the width of the resulting <code>BbndedSbmpleModel</code>
     * @pbrbm h the height of the resulting <code>BbndedSbmpleModel</code>
     * @return b new <code>BbndedSbmpleModel</code> with the specified
     *         width bnd height.
     * @throws IllegblArgumentException if <code>w</code> or
     *         <code>h</code> equbls either
     *         <code>Integer.MAX_VALUE</code> or
     *         <code>Integer.MIN_VALUE</code>
     * @throws IllegblArgumentException if <code>dbtbType</code> is not
     *         one of the supported dbtb types
     */
    public SbmpleModel crebteCompbtibleSbmpleModel(int w, int h) {
        int[] bbndOffs;

        if (numBbnks == 1) {
            bbndOffs = orderBbnds(bbndOffsets, w*h);
        }
        else {
            bbndOffs = new int[bbndOffsets.length];
        }

        SbmpleModel sbmpleModel =
            new BbndedSbmpleModel(dbtbType, w, h, w, bbnkIndices, bbndOffs);
        return sbmpleModel;
    }

    /**
     * Crebtes b new BbndedSbmpleModel with b subset of the bbnds of this
     * BbndedSbmpleModel.  The new BbndedSbmpleModel cbn be
     * used with bny DbtbBuffer thbt the existing BbndedSbmpleModel
     * cbn be used with.  The new BbndedSbmpleModel/DbtbBuffer
     * combinbtion will represent bn imbge with b subset of the bbnds
     * of the originbl BbndedSbmpleModel/DbtbBuffer combinbtion.
     * @throws RbsterFormbtException if the number of bbnds is grebter thbn
     *                               the number of bbnks in this sbmple model.
     * @throws IllegblArgumentException if <code>dbtbType</code> is not
     *         one of the supported dbtb types
     */
    public SbmpleModel crebteSubsetSbmpleModel(int bbnds[]) {
        if (bbnds.length > bbnkIndices.length)
            throw new RbsterFormbtException("There bre only " +
                                            bbnkIndices.length +
                                            " bbnds");
        int newBbnkIndices[] = new int[bbnds.length];
        int newBbndOffsets[] = new int[bbnds.length];

        for (int i=0; i<bbnds.length; i++) {
            newBbnkIndices[i] = bbnkIndices[bbnds[i]];
            newBbndOffsets[i] = bbndOffsets[bbnds[i]];
        }

        return new BbndedSbmpleModel(this.dbtbType, width, height,
                                     this.scbnlineStride,
                                     newBbnkIndices, newBbndOffsets);
    }

    /**
     * Crebtes b DbtbBuffer thbt corresponds to this BbndedSbmpleModel,
     * The DbtbBuffer's dbtb type, number of bbnks, bnd size
     * will be consistent with this BbndedSbmpleModel.
     * @throws IllegblArgumentException if <code>dbtbType</code> is not
     *         one of the supported types.
     */
    public DbtbBuffer crebteDbtbBuffer() {
        DbtbBuffer dbtbBuffer = null;

        int size = scbnlineStride * height;
        switch (dbtbType) {
        cbse DbtbBuffer.TYPE_BYTE:
            dbtbBuffer = new DbtbBufferByte(size, numBbnks);
            brebk;
        cbse DbtbBuffer.TYPE_USHORT:
            dbtbBuffer = new DbtbBufferUShort(size, numBbnks);
            brebk;
        cbse DbtbBuffer.TYPE_SHORT:
            dbtbBuffer = new DbtbBufferShort(size, numBbnks);
            brebk;
        cbse DbtbBuffer.TYPE_INT:
            dbtbBuffer = new DbtbBufferInt(size, numBbnks);
            brebk;
        cbse DbtbBuffer.TYPE_FLOAT:
            dbtbBuffer = new DbtbBufferFlobt(size, numBbnks);
            brebk;
        cbse DbtbBuffer.TYPE_DOUBLE:
            dbtbBuffer = new DbtbBufferDouble(size, numBbnks);
            brebk;
        defbult:
            throw new IllegblArgumentException("dbtbType is not one " +
                "of the supported types.");
        }

        return dbtbBuffer;
    }


    /**
     * Returns dbtb for b single pixel in b primitive brrby of type
     * TrbnsferType.  For b BbndedSbmpleModel, this will be the sbme
     * bs the dbtb type, bnd sbmples will be returned one per brrby
     * element.  Generblly, obj
     * should be pbssed in bs null, so thbt the Object will be crebted
     * butombticblly bnd will be of the right primitive dbtb type.
     * <p>
     * The following code illustrbtes trbnsferring dbtb for one pixel from
     * DbtbBuffer <code>db1</code>, whose storbge lbyout is described by
     * BbndedSbmpleModel <code>bsm1</code>, to DbtbBuffer <code>db2</code>,
     * whose storbge lbyout is described by
     * BbndedSbmpleModel <code>bsm2</code>.
     * The trbnsfer will generblly be more efficient thbn using
     * getPixel/setPixel.
     * <pre>
     *       BbndedSbmpleModel bsm1, bsm2;
     *       DbtbBufferInt db1, db2;
     *       bsm2.setDbtbElements(x, y, bsm1.getDbtbElements(x, y, null, db1),
     *                            db2);
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
     * @pbrbm x         The X coordinbte of the pixel locbtion
     * @pbrbm y         The Y coordinbte of the pixel locbtion
     * @pbrbm obj       If non-null, b primitive brrby in which to return
     *                  the pixel dbtb.
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb.
     * @return the dbtb for the specified pixel.
     * @see #setDbtbElements(int, int, Object, DbtbBuffer)
     */
    public Object getDbtbElements(int x, int y, Object obj, DbtbBuffer dbtb) {
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int type = getTrbnsferType();
        int numDbtbElems = getNumDbtbElements();
        int pixelOffset = y*scbnlineStride + x;

        switch(type) {

        cbse DbtbBuffer.TYPE_BYTE:

            byte[] bdbtb;

            if (obj == null) {
                bdbtb = new byte[numDbtbElems];
            } else {
                bdbtb = (byte[])obj;
            }

            for (int i=0; i<numDbtbElems; i++) {
                bdbtb[i] = (byte)dbtb.getElem(bbnkIndices[i],
                                              pixelOffset + bbndOffsets[i]);
            }

            obj = (Object)bdbtb;
            brebk;

        cbse DbtbBuffer.TYPE_USHORT:
        cbse DbtbBuffer.TYPE_SHORT:

            short[] sdbtb;

            if (obj == null) {
                sdbtb = new short[numDbtbElems];
            } else {
                sdbtb = (short[])obj;
            }

            for (int i=0; i<numDbtbElems; i++) {
                sdbtb[i] = (short)dbtb.getElem(bbnkIndices[i],
                                               pixelOffset + bbndOffsets[i]);
            }

            obj = (Object)sdbtb;
            brebk;

        cbse DbtbBuffer.TYPE_INT:

            int[] idbtb;

            if (obj == null) {
                idbtb = new int[numDbtbElems];
            } else {
                idbtb = (int[])obj;
            }

            for (int i=0; i<numDbtbElems; i++) {
                idbtb[i] = dbtb.getElem(bbnkIndices[i],
                                        pixelOffset + bbndOffsets[i]);
            }

            obj = (Object)idbtb;
            brebk;

        cbse DbtbBuffer.TYPE_FLOAT:

            flobt[] fdbtb;

            if (obj == null) {
                fdbtb = new flobt[numDbtbElems];
            } else {
                fdbtb = (flobt[])obj;
            }

            for (int i=0; i<numDbtbElems; i++) {
                fdbtb[i] = dbtb.getElemFlobt(bbnkIndices[i],
                                             pixelOffset + bbndOffsets[i]);
            }

            obj = (Object)fdbtb;
            brebk;

        cbse DbtbBuffer.TYPE_DOUBLE:

            double[] ddbtb;

            if (obj == null) {
                ddbtb = new double[numDbtbElems];
            } else {
                ddbtb = (double[])obj;
            }

            for (int i=0; i<numDbtbElems; i++) {
                ddbtb[i] = dbtb.getElemDouble(bbnkIndices[i],
                                              pixelOffset + bbndOffsets[i]);
            }

            obj = (Object)ddbtb;
            brebk;
        }

        return obj;
    }

    /**
     * Returns bll sbmples for the specified pixel in bn int brrby.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the pixel locbtion
     * @pbrbm y         The Y coordinbte of the pixel locbtion
     * @pbrbm iArrby    If non-null, returns the sbmples in this brrby
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb
     * @return the sbmples for the specified pixel.
     * @see #setPixel(int, int, int[], DbtbBuffer)
     */
    public int[] getPixel(int x, int y, int iArrby[], DbtbBuffer dbtb) {
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }

        int[] pixels;

        if (iArrby != null) {
           pixels = iArrby;
        } else {
           pixels = new int [numBbnds];
        }

        int pixelOffset = y*scbnlineStride + x;
        for (int i=0; i<numBbnds; i++) {
            pixels[i] = dbtb.getElem(bbnkIndices[i],
                                     pixelOffset + bbndOffsets[i]);
        }
        return pixels;
    }

    /**
     * Returns bll sbmples for the specified rectbngle of pixels in
     * bn int brrby, one sbmple per dbtb brrby element.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the upper left pixel locbtion
     * @pbrbm y         The Y coordinbte of the upper left pixel locbtion
     * @pbrbm w         The width of the pixel rectbngle
     * @pbrbm h         The height of the pixel rectbngle
     * @pbrbm iArrby    If non-null, returns the sbmples in this brrby
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb
     * @return the sbmples for the pixels within the specified region.
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
        int[] pixels;

        if (iArrby != null) {
           pixels = iArrby;
        } else {
           pixels = new int[w*h*numBbnds];
        }

        for (int k = 0; k < numBbnds; k++) {
            int lineOffset = y*scbnlineStride + x + bbndOffsets[k];
            int srcOffset = k;
            int bbnk = bbnkIndices[k];

            for (int i = 0; i < h; i++) {
                int pixelOffset = lineOffset;
                for (int j = 0; j < w; j++) {
                    pixels[srcOffset] = dbtb.getElem(bbnk, pixelOffset++);
                    srcOffset += numBbnds;
                }
                lineOffset += scbnlineStride;
            }
        }
        return pixels;
    }

    /**
     * Returns bs int the sbmple in b specified bbnd for the pixel
     * locbted bt (x,y).
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the pixel locbtion
     * @pbrbm y         The Y coordinbte of the pixel locbtion
     * @pbrbm b         The bbnd to return
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb
     * @return the sbmple in the specified bbnd for the specified pixel.
     * @see #setSbmple(int, int, int, int, DbtbBuffer)
     */
    public int getSbmple(int x, int y, int b, DbtbBuffer dbtb) {
        // Bounds check for 'b' will be performed butombticblly
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int sbmple =
            dbtb.getElem(bbnkIndices[b],
                         y*scbnlineStride + x + bbndOffsets[b]);
        return sbmple;
    }

    /**
     * Returns the sbmple in b specified bbnd
     * for the pixel locbted bt (x,y) bs b flobt.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the pixel locbtion
     * @pbrbm y         The Y coordinbte of the pixel locbtion
     * @pbrbm b         The bbnd to return
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb
     * @return b flobt vblue thbt represents the sbmple in the specified
     * bbnd for the specified pixel.
     */
    public flobt getSbmpleFlobt(int x, int y, int b, DbtbBuffer dbtb) {
        // Bounds check for 'b' will be performed butombticblly
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }

        flobt sbmple = dbtb.getElemFlobt(bbnkIndices[b],
                                    y*scbnlineStride + x + bbndOffsets[b]);
        return sbmple;
    }

    /**
     * Returns the sbmple in b specified bbnd
     * for b pixel locbted bt (x,y) bs b double.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the pixel locbtion
     * @pbrbm y         The Y coordinbte of the pixel locbtion
     * @pbrbm b         The bbnd to return
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb
     * @return b double vblue thbt represents the sbmple in the specified
     * bbnd for the specified pixel.
     */
    public double getSbmpleDouble(int x, int y, int b, DbtbBuffer dbtb) {
        // Bounds check for 'b' will be performed butombticblly
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }

        double sbmple = dbtb.getElemDouble(bbnkIndices[b],
                                       y*scbnlineStride + x + bbndOffsets[b]);
        return sbmple;
    }

    /**
     * Returns the sbmples in b specified bbnd for the specified rectbngle
     * of pixels in bn int brrby, one sbmple per dbtb brrby element.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the upper left pixel locbtion
     * @pbrbm y         The Y coordinbte of the upper left pixel locbtion
     * @pbrbm w         The width of the pixel rectbngle
     * @pbrbm h         The height of the pixel rectbngle
     * @pbrbm b         The bbnd to return
     * @pbrbm iArrby    If non-null, returns the sbmples in this brrby
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb
     * @return the sbmples in the specified bbnd for the pixels within
     * the specified region.
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

        int lineOffset = y*scbnlineStride + x + bbndOffsets[b];
        int srcOffset = 0;
        int bbnk = bbnkIndices[b];

        for (int i = 0; i < h; i++) {
           int sbmpleOffset = lineOffset;
           for (int j = 0; j < w; j++) {
               sbmples[srcOffset++] = dbtb.getElem(bbnk, sbmpleOffset++);
           }
           lineOffset += scbnlineStride;
        }
        return sbmples;
    }

    /**
     * Sets the dbtb for b single pixel in the specified DbtbBuffer from b
     * primitive brrby of type TrbnsferType.  For b BbndedSbmpleModel,
     * this will be the sbme bs the dbtb type, bnd sbmples bre trbnsferred
     * one per brrby element.
     * <p>
     * The following code illustrbtes trbnsferring dbtb for one pixel from
     * DbtbBuffer <code>db1</code>, whose storbge lbyout is described by
     * BbndedSbmpleModel <code>bsm1</code>, to DbtbBuffer <code>db2</code>,
     * whose storbge lbyout is described by
     * BbndedSbmpleModel <code>bsm2</code>.
     * The trbnsfer will generblly be more efficient thbn using
     * getPixel/setPixel.
     * <pre>
     *       BbndedSbmpleModel bsm1, bsm2;
     *       DbtbBufferInt db1, db2;
     *       bsm2.setDbtbElements(x, y, bsm1.getDbtbElements(x, y, null, db1),
     *                            db2);
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
     * @pbrbm x         The X coordinbte of the pixel locbtion
     * @pbrbm y         The Y coordinbte of the pixel locbtion
     * @pbrbm obj       If non-null, returns the primitive brrby in this
     *                  object
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb
     * @see #getDbtbElements(int, int, Object, DbtbBuffer)
     */
    public void setDbtbElements(int x, int y, Object obj, DbtbBuffer dbtb) {
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int type = getTrbnsferType();
        int numDbtbElems = getNumDbtbElements();
        int pixelOffset = y*scbnlineStride + x;

        switch(type) {

        cbse DbtbBuffer.TYPE_BYTE:

            byte[] bbrrby = (byte[])obj;

            for (int i=0; i<numDbtbElems; i++) {
                dbtb.setElem(bbnkIndices[i], pixelOffset + bbndOffsets[i],
                             bbrrby[i] & 0xff);
            }
            brebk;

        cbse DbtbBuffer.TYPE_USHORT:
        cbse DbtbBuffer.TYPE_SHORT:

            short[] sbrrby = (short[])obj;

            for (int i=0; i<numDbtbElems; i++) {
                dbtb.setElem(bbnkIndices[i], pixelOffset + bbndOffsets[i],
                             sbrrby[i] & 0xffff);
            }
            brebk;

        cbse DbtbBuffer.TYPE_INT:

            int[] ibrrby = (int[])obj;

            for (int i=0; i<numDbtbElems; i++) {
                dbtb.setElem(bbnkIndices[i], pixelOffset + bbndOffsets[i],
                             ibrrby[i]);
            }
            brebk;

        cbse DbtbBuffer.TYPE_FLOAT:

            flobt[] fbrrby = (flobt[])obj;

            for (int i=0; i<numDbtbElems; i++) {
                dbtb.setElemFlobt(bbnkIndices[i], pixelOffset + bbndOffsets[i],
                                  fbrrby[i]);
            }
            brebk;

        cbse DbtbBuffer.TYPE_DOUBLE:

            double[] dbrrby = (double[])obj;

            for (int i=0; i<numDbtbElems; i++) {
                dbtb.setElemDouble(bbnkIndices[i], pixelOffset + bbndOffsets[i],
                                   dbrrby[i]);
            }
            brebk;

        }
    }

    /**
     * Sets b pixel in the DbtbBuffer using bn int brrby of sbmples for input.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the pixel locbtion
     * @pbrbm y         The Y coordinbte of the pixel locbtion
     * @pbrbm iArrby    The input sbmples in bn int brrby
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb
     * @see #getPixel(int, int, int[], DbtbBuffer)
     */
    public void setPixel(int x, int y, int iArrby[], DbtbBuffer dbtb) {
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
       int pixelOffset = y*scbnlineStride + x;
       for (int i=0; i<numBbnds; i++) {
           dbtb.setElem(bbnkIndices[i], pixelOffset + bbndOffsets[i],
                        iArrby[i]);
       }
    }

    /**
     * Sets bll sbmples for b rectbngle of pixels from bn int brrby contbining
     * one sbmple per brrby element.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the upper left pixel locbtion
     * @pbrbm y         The Y coordinbte of the upper left pixel locbtion
     * @pbrbm w         The width of the pixel rectbngle
     * @pbrbm h         The height of the pixel rectbngle
     * @pbrbm iArrby    The input sbmples in bn int brrby
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb
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

        for (int k = 0; k < numBbnds; k++) {
            int lineOffset = y*scbnlineStride + x + bbndOffsets[k];
            int srcOffset = k;
            int bbnk = bbnkIndices[k];

            for (int i = 0; i < h; i++) {
                int pixelOffset = lineOffset;
                for (int j = 0; j < w; j++) {
                    dbtb.setElem(bbnk, pixelOffset++, iArrby[srcOffset]);
                    srcOffset += numBbnds;
                }
                lineOffset += scbnlineStride;
           }
        }
    }

    /**
     * Sets b sbmple in the specified bbnd for the pixel locbted bt (x,y)
     * in the DbtbBuffer using bn int for input.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the pixel locbtion
     * @pbrbm y         The Y coordinbte of the pixel locbtion
     * @pbrbm b         The bbnd to set
     * @pbrbm s         The input sbmple bs bn int
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb
     * @see #getSbmple(int, int, int, DbtbBuffer)
     */
    public void setSbmple(int x, int y, int b, int s,
                          DbtbBuffer dbtb) {
        // Bounds check for 'b' will be performed butombticblly
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        dbtb.setElem(bbnkIndices[b],
                     y*scbnlineStride + x + bbndOffsets[b], s);
    }

    /**
     * Sets b sbmple in the specified bbnd for the pixel locbted bt (x,y)
     * in the DbtbBuffer using b flobt for input.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the pixel locbtion
     * @pbrbm y         The Y coordinbte of the pixel locbtion
     * @pbrbm b         The bbnd to set
     * @pbrbm s         The input sbmple bs b flobt
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb
     * @see #getSbmple(int, int, int, DbtbBuffer)
     */
    public void setSbmple(int x, int y, int b,
                          flobt s ,
                          DbtbBuffer dbtb) {
        // Bounds check for 'b' will be performed butombticblly
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        dbtb.setElemFlobt(bbnkIndices[b],
                          y*scbnlineStride + x + bbndOffsets[b], s);
    }

    /**
     * Sets b sbmple in the specified bbnd for the pixel locbted bt (x,y)
     * in the DbtbBuffer using b double for input.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the pixel locbtion
     * @pbrbm y         The Y coordinbte of the pixel locbtion
     * @pbrbm b         The bbnd to set
     * @pbrbm s         The input sbmple bs b double
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb
     * @see #getSbmple(int, int, int, DbtbBuffer)
     */
    public void setSbmple(int x, int y, int b,
                          double s,
                          DbtbBuffer dbtb) {
        // Bounds check for 'b' will be performed butombticblly
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        dbtb.setElemDouble(bbnkIndices[b],
                          y*scbnlineStride + x + bbndOffsets[b], s);
    }

    /**
     * Sets the sbmples in the specified bbnd for the specified rectbngle
     * of pixels from bn int brrby contbining one sbmple per dbtb brrby element.
     * ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x         The X coordinbte of the upper left pixel locbtion
     * @pbrbm y         The Y coordinbte of the upper left pixel locbtion
     * @pbrbm w         The width of the pixel rectbngle
     * @pbrbm h         The height of the pixel rectbngle
     * @pbrbm b         The bbnd to set
     * @pbrbm iArrby    The input sbmple brrby
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb
     * @see #getSbmples(int, int, int, int, int, int[], DbtbBuffer)
     */
    public void setSbmples(int x, int y, int w, int h, int b,
                           int iArrby[], DbtbBuffer dbtb) {
        // Bounds check for 'b' will be performed butombticblly
        if ((x < 0) || (y < 0) || (x + w > width) || (y + h > height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int lineOffset = y*scbnlineStride + x + bbndOffsets[b];
        int srcOffset = 0;
        int bbnk = bbnkIndices[b];

        for (int i = 0; i < h; i++) {
           int sbmpleOffset = lineOffset;
           for (int j = 0; j < w; j++) {
              dbtb.setElem(bbnk, sbmpleOffset++, iArrby[srcOffset++]);
           }
           lineOffset += scbnlineStride;
        }
    }

    privbte stbtic int[] crebteOffsetArrby(int numBbnds) {
        int[] bbndOffsets = new int[numBbnds];
        for (int i=0; i < numBbnds; i++) {
            bbndOffsets[i] = 0;
        }
        return bbndOffsets;
    }

    privbte stbtic int[] crebteIndicesArrby(int numBbnds) {
        int[] bbnkIndices = new int[numBbnds];
        for (int i=0; i < numBbnds; i++) {
            bbnkIndices[i] = i;
        }
        return bbnkIndices;
    }

    // Differentibte hbsh code from other ComponentSbmpleModel subclbsses
    public int hbshCode() {
        return super.hbshCode() ^ 0x2;
    }
}
