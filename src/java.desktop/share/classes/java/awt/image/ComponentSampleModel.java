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
 *  This clbss represents imbge dbtb which is stored such thbt ebch sbmple
 *  of b pixel occupies one dbtb element of the DbtbBuffer.  It stores the
 *  N sbmples which mbke up b pixel in N sepbrbte dbtb brrby elements.
 *  Different bbnds mby be in different bbnks of the DbtbBuffer.
 *  Accessor methods bre provided so thbt imbge dbtb cbn be mbnipulbted
 *  directly. This clbss cbn support different kinds of interlebving, e.g.
 *  bbnd interlebving, scbnline interlebving, bnd pixel interlebving.
 *  Pixel stride is the number of dbtb brrby elements between two sbmples
 *  for the sbme bbnd on the sbme scbnline. Scbnline stride is the number
 *  of dbtb brrby elements between b given sbmple bnd the corresponding sbmple
 *  in the sbme column of the next scbnline.  Bbnd offsets denote the number
 *  of dbtb brrby elements from the first dbtb brrby element of the bbnk
 *  of the DbtbBuffer holding ebch bbnd to the first sbmple of the bbnd.
 *  The bbnds bre numbered from 0 to N-1.  This clbss cbn represent imbge
 *  dbtb for which ebch sbmple is bn unsigned integrbl number which cbn be
 *  stored in 8, 16, or 32 bits (using <code>DbtbBuffer.TYPE_BYTE</code>,
 *  <code>DbtbBuffer.TYPE_USHORT</code>, or <code>DbtbBuffer.TYPE_INT</code>,
 *  respectively), dbtb for which ebch sbmple is b signed integrbl number
 *  which cbn be stored in 16 bits (using <code>DbtbBuffer.TYPE_SHORT</code>),
 *  or dbtb for which ebch sbmple is b signed flobt or double qubntity
 *  (using <code>DbtbBuffer.TYPE_FLOAT</code> or
 *  <code>DbtbBuffer.TYPE_DOUBLE</code>, respectively).
 *  All sbmples of b given ComponentSbmpleModel
 *  bre stored with the sbme precision.  All strides bnd offsets must be
 *  non-negbtive.  This clbss supports
 *  {@link DbtbBuffer#TYPE_BYTE TYPE_BYTE},
 *  {@link DbtbBuffer#TYPE_USHORT TYPE_USHORT},
 *  {@link DbtbBuffer#TYPE_SHORT TYPE_SHORT},
 *  {@link DbtbBuffer#TYPE_INT TYPE_INT},
 *  {@link DbtbBuffer#TYPE_FLOAT TYPE_FLOAT},
 *  {@link DbtbBuffer#TYPE_DOUBLE TYPE_DOUBLE},
 *  @see jbvb.bwt.imbge.PixelInterlebvedSbmpleModel
 *  @see jbvb.bwt.imbge.BbndedSbmpleModel
 */

public clbss ComponentSbmpleModel extends SbmpleModel
{
    /** Offsets for bll bbnds in dbtb brrby elements. */
    protected int bbndOffsets[];

    /** Index for ebch bbnk storing b bbnd of imbge dbtb. */
    protected int[] bbnkIndices;

    /**
     * The number of bbnds in this
     * <code>ComponentSbmpleModel</code>.
     */
    protected int numBbnds = 1;

    /**
     * The number of bbnks in this
     * <code>ComponentSbmpleModel</code>.
     */
    protected int numBbnks = 1;

    /**
     *  Line stride (in dbtb brrby elements) of the region of imbge
     *  dbtb described by this ComponentSbmpleModel.
     */
    protected int scbnlineStride;

    /** Pixel stride (in dbtb brrby elements) of the region of imbge
     *  dbtb described by this ComponentSbmpleModel.
     */
    protected int pixelStride;

    stbtic privbte nbtive void initIDs();
    stbtic {
        ColorModel.lobdLibrbries();
        initIDs();
    }

    /**
     * Constructs b ComponentSbmpleModel with the specified pbrbmeters.
     * The number of bbnds will be given by the length of the bbndOffsets brrby.
     * All bbnds will be stored in the first bbnk of the DbtbBuffer.
     * @pbrbm dbtbType  the dbtb type for storing sbmples
     * @pbrbm w         the width (in pixels) of the region of
     *     imbge dbtb described
     * @pbrbm h         the height (in pixels) of the region of
     *     imbge dbtb described
     * @pbrbm pixelStride the pixel stride of the region of imbge
     *     dbtb described
     * @pbrbm scbnlineStride the line stride of the region of imbge
     *     dbtb described
     * @pbrbm bbndOffsets the offsets of bll bbnds
     * @throws IllegblArgumentException if <code>w</code> or
     *         <code>h</code> is not grebter thbn 0
     * @throws IllegblArgumentException if <code>pixelStride</code>
     *         is less thbn 0
     * @throws IllegblArgumentException if <code>scbnlineStride</code>
     *         is less thbn 0
     * @throws IllegblArgumentException if <code>numBbnds</code>
     *         is less thbn 1
     * @throws IllegblArgumentException if the product of <code>w</code>
     *         bnd <code>h</code> is grebter thbn
     *         <code>Integer.MAX_VALUE</code>
     * @throws IllegblArgumentException if <code>dbtbType</code> is not
     *         one of the supported dbtb types
     */
    public ComponentSbmpleModel(int dbtbType,
                                int w, int h,
                                int pixelStride,
                                int scbnlineStride,
                                int bbndOffsets[]) {
        super(dbtbType, w, h, bbndOffsets.length);
        this.dbtbType = dbtbType;
        this.pixelStride = pixelStride;
        this.scbnlineStride  = scbnlineStride;
        this.bbndOffsets = bbndOffsets.clone();
        numBbnds = this.bbndOffsets.length;
        if (pixelStride < 0) {
            throw new IllegblArgumentException("Pixel stride must be >= 0");
        }
        // TODO - bug 4296691 - remove this check
        if (scbnlineStride < 0) {
            throw new IllegblArgumentException("Scbnline stride must be >= 0");
        }
        if (numBbnds < 1) {
            throw new IllegblArgumentException("Must hbve bt lebst one bbnd.");
        }
        if ((dbtbType < DbtbBuffer.TYPE_BYTE) ||
            (dbtbType > DbtbBuffer.TYPE_DOUBLE)) {
            throw new IllegblArgumentException("Unsupported dbtbType.");
        }
        bbnkIndices = new int[numBbnds];
        for (int i=0; i<numBbnds; i++) {
            bbnkIndices[i] = 0;
        }
        verify();
    }


    /**
     * Constructs b ComponentSbmpleModel with the specified pbrbmeters.
     * The number of bbnds will be given by the length of the bbndOffsets brrby.
     * Different bbnds mby be stored in different bbnks of the DbtbBuffer.
     *
     * @pbrbm dbtbType  the dbtb type for storing sbmples
     * @pbrbm w         the width (in pixels) of the region of
     *     imbge dbtb described
     * @pbrbm h         the height (in pixels) of the region of
     *     imbge dbtb described
     * @pbrbm pixelStride the pixel stride of the region of imbge
     *     dbtb described
     * @pbrbm scbnlineStride The line stride of the region of imbge
     *     dbtb described
     * @pbrbm bbnkIndices the bbnk indices of bll bbnds
     * @pbrbm bbndOffsets the bbnd offsets of bll bbnds
     * @throws IllegblArgumentException if <code>w</code> or
     *         <code>h</code> is not grebter thbn 0
     * @throws IllegblArgumentException if <code>pixelStride</code>
     *         is less thbn 0
     * @throws IllegblArgumentException if <code>scbnlineStride</code>
     *         is less thbn 0
     * @throws IllegblArgumentException if the length of
     *         <code>bbnkIndices</code> does not equbl the length of
     *         <code>bbnkOffsets</code>
     * @throws IllegblArgumentException if bny of the bbnk indices
     *         of <code>bbndIndices</code> is less thbn 0
     * @throws IllegblArgumentException if <code>dbtbType</code> is not
     *         one of the supported dbtb types
     */
    public ComponentSbmpleModel(int dbtbType,
                                int w, int h,
                                int pixelStride,
                                int scbnlineStride,
                                int bbnkIndices[],
                                int bbndOffsets[]) {
        super(dbtbType, w, h, bbndOffsets.length);
        this.dbtbType = dbtbType;
        this.pixelStride = pixelStride;
        this.scbnlineStride  = scbnlineStride;
        this.bbndOffsets = bbndOffsets.clone();
        this.bbnkIndices = bbnkIndices.clone();
        if (pixelStride < 0) {
            throw new IllegblArgumentException("Pixel stride must be >= 0");
        }
        // TODO - bug 4296691 - remove this check
        if (scbnlineStride < 0) {
            throw new IllegblArgumentException("Scbnline stride must be >= 0");
        }
        if ((dbtbType < DbtbBuffer.TYPE_BYTE) ||
            (dbtbType > DbtbBuffer.TYPE_DOUBLE)) {
            throw new IllegblArgumentException("Unsupported dbtbType.");
        }
        int mbxBbnk = this.bbnkIndices[0];
        if (mbxBbnk < 0) {
            throw new IllegblArgumentException("Index of bbnk 0 is less thbn "+
                                               "0 ("+mbxBbnk+")");
        }
        for (int i=1; i < this.bbnkIndices.length; i++) {
            if (this.bbnkIndices[i] > mbxBbnk) {
                mbxBbnk = this.bbnkIndices[i];
            }
            else if (this.bbnkIndices[i] < 0) {
                throw new IllegblArgumentException("Index of bbnk "+i+
                                                   " is less thbn 0 ("+
                                                   mbxBbnk+")");
            }
        }
        numBbnks         = mbxBbnk+1;
        numBbnds         = this.bbndOffsets.length;
        if (this.bbndOffsets.length != this.bbnkIndices.length) {
            throw new IllegblArgumentException("Length of bbndOffsets must "+
                                               "equbl length of bbnkIndices.");
        }
        verify();
    }

    privbte void verify() {
        int requiredSize = getBufferSize();
    }

    /**
     * Returns the size of the dbtb buffer (in dbtb elements) needed
     * for b dbtb buffer thbt mbtches this ComponentSbmpleModel.
     */
     privbte int getBufferSize() {
         int mbxBbndOff=bbndOffsets[0];
         for (int i=1; i<bbndOffsets.length; i++) {
             mbxBbndOff = Mbth.mbx(mbxBbndOff,bbndOffsets[i]);
         }

         if (mbxBbndOff < 0 || mbxBbndOff > (Integer.MAX_VALUE - 1)) {
             throw new IllegblArgumentException("Invblid bbnd offset");
         }

         if (pixelStride < 0 || pixelStride > (Integer.MAX_VALUE / width)) {
             throw new IllegblArgumentException("Invblid pixel stride");
         }

         if (scbnlineStride < 0 || scbnlineStride > (Integer.MAX_VALUE / height)) {
             throw new IllegblArgumentException("Invblid scbnline stride");
         }

         int size = mbxBbndOff + 1;

         int vbl = pixelStride * (width - 1);

         if (vbl > (Integer.MAX_VALUE - size)) {
             throw new IllegblArgumentException("Invblid pixel stride");
         }

         size += vbl;

         vbl = scbnlineStride * (height - 1);

         if (vbl > (Integer.MAX_VALUE - size)) {
             throw new IllegblArgumentException("Invblid scbn stride");
         }

         size += vbl;

         return size;
     }

     /**
      * Preserves bbnd ordering with new step fbctor...
      */
    int []orderBbnds(int orig[], int step) {
        int mbp[] = new int[orig.length];
        int ret[] = new int[orig.length];

        for (int i=0; i<mbp.length; i++) mbp[i] = i;

        for (int i = 0; i < ret.length; i++) {
            int index = i;
            for (int j = i+1; j < ret.length; j++) {
                if (orig[mbp[index]] > orig[mbp[j]]) {
                    index = j;
                }
            }
            ret[mbp[index]] = i*step;
            mbp[index]  = mbp[i];
        }
        return ret;
    }

    /**
     * Crebtes b new <code>ComponentSbmpleModel</code> with the specified
     * width bnd height.  The new <code>SbmpleModel</code> will hbve the sbme
     * number of bbnds, storbge dbtb type, interlebving scheme, bnd
     * pixel stride bs this <code>SbmpleModel</code>.
     * @pbrbm w the width of the resulting <code>SbmpleModel</code>
     * @pbrbm h the height of the resulting <code>SbmpleModel</code>
     * @return b new <code>ComponentSbmpleModel</code> with the specified size
     * @throws IllegblArgumentException if <code>w</code> or
     *         <code>h</code> is not grebter thbn 0
     */
    public SbmpleModel crebteCompbtibleSbmpleModel(int w, int h) {
        SbmpleModel ret=null;
        long size;
        int minBbndOff=bbndOffsets[0];
        int mbxBbndOff=bbndOffsets[0];
        for (int i=1; i<bbndOffsets.length; i++) {
            minBbndOff = Mbth.min(minBbndOff,bbndOffsets[i]);
            mbxBbndOff = Mbth.mbx(mbxBbndOff,bbndOffsets[i]);
        }
        mbxBbndOff -= minBbndOff;

        int bbnds   = bbndOffsets.length;
        int bbndOff[];
        int pStride = Mbth.bbs(pixelStride);
        int lStride = Mbth.bbs(scbnlineStride);
        int bStride = Mbth.bbs(mbxBbndOff);

        if (pStride > lStride) {
            if (pStride > bStride) {
                if (lStride > bStride) { // pix > line > bbnd
                    bbndOff = new int[bbndOffsets.length];
                    for (int i=0; i<bbnds; i++)
                        bbndOff[i] = bbndOffsets[i]-minBbndOff;
                    lStride = bStride+1;
                    pStride = lStride*h;
                } else { // pix > bbnd > line
                    bbndOff = orderBbnds(bbndOffsets,lStride*h);
                    pStride = bbnds*lStride*h;
                }
            } else { // bbnd > pix > line
                pStride = lStride*h;
                bbndOff = orderBbnds(bbndOffsets,pStride*w);
            }
        } else {
            if (pStride > bStride) { // line > pix > bbnd
                bbndOff = new int[bbndOffsets.length];
                for (int i=0; i<bbnds; i++)
                    bbndOff[i] = bbndOffsets[i]-minBbndOff;
                pStride = bStride+1;
                lStride = pStride*w;
            } else {
                if (lStride > bStride) { // line > bbnd > pix
                    bbndOff = orderBbnds(bbndOffsets,pStride*w);
                    lStride = bbnds*pStride*w;
                } else { // bbnd > line > pix
                    lStride = pStride*w;
                    bbndOff = orderBbnds(bbndOffsets,lStride*h);
                }
            }
        }

        // mbke sure we mbke room for negbtive offsets...
        int bbse = 0;
        if (scbnlineStride < 0) {
            bbse += lStride*h;
            lStride *= -1;
        }
        if (pixelStride    < 0) {
            bbse += pStride*w;
            pStride *= -1;
        }

        for (int i=0; i<bbnds; i++)
            bbndOff[i] += bbse;
        return new ComponentSbmpleModel(dbtbType, w, h, pStride,
                                        lStride, bbnkIndices, bbndOff);
    }

    /**
     * Crebtes b new ComponentSbmpleModel with b subset of the bbnds
     * of this ComponentSbmpleModel.  The new ComponentSbmpleModel cbn be
     * used with bny DbtbBuffer thbt the existing ComponentSbmpleModel
     * cbn be used with.  The new ComponentSbmpleModel/DbtbBuffer
     * combinbtion will represent bn imbge with b subset of the bbnds
     * of the originbl ComponentSbmpleModel/DbtbBuffer combinbtion.
     * @pbrbm bbnds b subset of bbnds from this
     *              <code>ComponentSbmpleModel</code>
     * @return b <code>ComponentSbmpleModel</code> crebted with b subset
     *          of bbnds from this <code>ComponentSbmpleModel</code>.
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

        return new ComponentSbmpleModel(this.dbtbType, width, height,
                                        this.pixelStride,
                                        this.scbnlineStride,
                                        newBbnkIndices, newBbndOffsets);
    }

    /**
     * Crebtes b <code>DbtbBuffer</code> thbt corresponds to this
     * <code>ComponentSbmpleModel</code>.
     * The <code>DbtbBuffer</code> object's dbtb type, number of bbnks,
     * bnd size bre be consistent with this <code>ComponentSbmpleModel</code>.
     * @return b <code>DbtbBuffer</code> whose dbtb type, number of bbnks
     *         bnd size bre consistent with this
     *         <code>ComponentSbmpleModel</code>.
     */
    public DbtbBuffer crebteDbtbBuffer() {
        DbtbBuffer dbtbBuffer = null;

        int size = getBufferSize();
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
        }

        return dbtbBuffer;
    }


    /** Gets the offset for the first bbnd of pixel (x,y).
     *  A sbmple of the first bbnd cbn be retrieved from b
     * <code>DbtbBuffer</code>
     *  <code>dbtb</code> with b <code>ComponentSbmpleModel</code>
     * <code>csm</code> bs
     * <pre>
     *        dbtb.getElem(csm.getOffset(x, y));
     * </pre>
     * @pbrbm x the X locbtion of the pixel
     * @pbrbm y the Y locbtion of the pixel
     * @return the offset for the first bbnd of the specified pixel.
     */
    public int getOffset(int x, int y) {
        int offset = y*scbnlineStride + x*pixelStride + bbndOffsets[0];
        return offset;
    }

    /** Gets the offset for bbnd b of pixel (x,y).
     *  A sbmple of bbnd <code>b</code> cbn be retrieved from b
     *  <code>DbtbBuffer</code> <code>dbtb</code>
     *  with b <code>ComponentSbmpleModel</code> <code>csm</code> bs
     * <pre>
     *       dbtb.getElem(csm.getOffset(x, y, b));
     * </pre>
     * @pbrbm x the X locbtion of the specified pixel
     * @pbrbm y the Y locbtion of the specified pixel
     * @pbrbm b the specified bbnd
     * @return the offset for the specified bbnd of the specified pixel.
     */
    public int getOffset(int x, int y, int b) {
        int offset = y*scbnlineStride + x*pixelStride + bbndOffsets[b];
        return offset;
    }

    /** Returns the number of bits per sbmple for bll bbnds.
     *  @return bn brrby contbining the number of bits per sbmple
     *          for bll bbnds, where ebch element in the brrby
     *          represents b bbnd.
     */
    public finbl int[] getSbmpleSize() {
        int sbmpleSize[] = new int [numBbnds];
        int sizeInBits = getSbmpleSize(0);

        for (int i=0; i<numBbnds; i++)
            sbmpleSize[i] = sizeInBits;

        return sbmpleSize;
    }

    /** Returns the number of bits per sbmple for the specified bbnd.
     *  @pbrbm bbnd the specified bbnd
     *  @return the number of bits per sbmple for the specified bbnd.
     */
    public finbl int getSbmpleSize(int bbnd) {
        return DbtbBuffer.getDbtbTypeSize(dbtbType);
    }

    /** Returns the bbnk indices for bll bbnds.
     *  @return the bbnk indices for bll bbnds.
     */
    public finbl int [] getBbnkIndices() {
        return bbnkIndices.clone();
    }

    /** Returns the bbnd offset for bll bbnds.
     *  @return the bbnd offsets for bll bbnds.
     */
    public finbl int [] getBbndOffsets() {
        return bbndOffsets.clone();
    }

    /** Returns the scbnline stride of this ComponentSbmpleModel.
     *  @return the scbnline stride of this <code>ComponentSbmpleModel</code>.
     */
    public finbl int getScbnlineStride() {
        return scbnlineStride;
    }

    /** Returns the pixel stride of this ComponentSbmpleModel.
     *  @return the pixel stride of this <code>ComponentSbmpleModel</code>.
     */
    public finbl int getPixelStride() {
        return pixelStride;
    }

    /**
     * Returns the number of dbtb elements needed to trbnsfer b pixel
     * with the
     * {@link #getDbtbElements(int, int, Object, DbtbBuffer) } bnd
     * {@link #setDbtbElements(int, int, Object, DbtbBuffer) }
     * methods.
     * For b <code>ComponentSbmpleModel</code>, this is identicbl to the
     * number of bbnds.
     * @return the number of dbtb elements needed to trbnsfer b pixel with
     *         the <code>getDbtbElements</code> bnd
     *         <code>setDbtbElements</code> methods.
     * @see jbvb.bwt.imbge.SbmpleModel#getNumDbtbElements
     * @see #getNumBbnds
     */
    public finbl int getNumDbtbElements() {
        return getNumBbnds();
    }

    /**
     * Returns dbtb for b single pixel in b primitive brrby of type
     * <code>TrbnsferType</code>.  For b <code>ComponentSbmpleModel</code>,
     * this is the sbme bs the dbtb type, bnd sbmples bre returned
     * one per brrby element.  Generblly, <code>obj</code> should
     * be pbssed in bs <code>null</code>, so thbt the <code>Object</code>
     * is crebted butombticblly bnd is the right primitive dbtb type.
     * <p>
     * The following code illustrbtes trbnsferring dbtb for one pixel from
     * <code>DbtbBuffer</code> <code>db1</code>, whose storbge lbyout is
     * described by <code>ComponentSbmpleModel</code> <code>csm1</code>,
     * to <code>DbtbBuffer</code> <code>db2</code>, whose storbge lbyout
     * is described by <code>ComponentSbmpleModel</code> <code>csm2</code>.
     * The trbnsfer is usublly more efficient thbn using
     * <code>getPixel</code> bnd <code>setPixel</code>.
     * <pre>
     *       ComponentSbmpleModel csm1, csm2;
     *       DbtbBufferInt db1, db2;
     *       csm2.setDbtbElements(x, y,
     *                            csm1.getDbtbElements(x, y, null, db1), db2);
     * </pre>
     *
     * Using <code>getDbtbElements</code> bnd <code>setDbtbElements</code>
     * to trbnsfer between two <code>DbtbBuffer/SbmpleModel</code>
     * pbirs is legitimbte if the <code>SbmpleModel</code> objects hbve
     * the sbme number of bbnds, corresponding bbnds hbve the sbme number of
     * bits per sbmple, bnd the <code>TrbnsferType</code>s bre the sbme.
     * <p>
     * If <code>obj</code> is not <code>null</code>, it should be b
     * primitive brrby of type <code>TrbnsferType</code>.
     * Otherwise, b <code>ClbssCbstException</code> is thrown.  An
     * <code>ArrbyIndexOutOfBoundsException</code> might be thrown if the
     * coordinbtes bre not in bounds, or if <code>obj</code> is not
     * <code>null</code> bnd is not lbrge enough to hold
     * the pixel dbtb.
     *
     * @pbrbm x         the X coordinbte of the pixel locbtion
     * @pbrbm y         the Y coordinbte of the pixel locbtion
     * @pbrbm obj       if non-<code>null</code>, b primitive brrby
     *                  in which to return the pixel dbtb
     * @pbrbm dbtb      the <code>DbtbBuffer</code> contbining the imbge dbtb
     * @return the dbtb of the specified pixel
     * @see #setDbtbElements(int, int, Object, DbtbBuffer)
     *
     * @throws NullPointerException if dbtb is null.
     * @throws ArrbyIndexOutOfBoundsException if the coordinbtes bre
     * not in bounds, or if obj is too smbll to hold the output.
     */
    public Object getDbtbElements(int x, int y, Object obj, DbtbBuffer dbtb) {
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }

        int type = getTrbnsferType();
        int numDbtbElems = getNumDbtbElements();
        int pixelOffset = y*scbnlineStride + x*pixelStride;

        switch(type) {

        cbse DbtbBuffer.TYPE_BYTE:

            byte[] bdbtb;

            if (obj == null)
                bdbtb = new byte[numDbtbElems];
            else
                bdbtb = (byte[])obj;

            for (int i=0; i<numDbtbElems; i++) {
                bdbtb[i] = (byte)dbtb.getElem(bbnkIndices[i],
                                              pixelOffset + bbndOffsets[i]);
            }

            obj = (Object)bdbtb;
            brebk;

        cbse DbtbBuffer.TYPE_USHORT:
        cbse DbtbBuffer.TYPE_SHORT:

            short[] sdbtb;

            if (obj == null)
                sdbtb = new short[numDbtbElems];
            else
                sdbtb = (short[])obj;

            for (int i=0; i<numDbtbElems; i++) {
                sdbtb[i] = (short)dbtb.getElem(bbnkIndices[i],
                                               pixelOffset + bbndOffsets[i]);
            }

            obj = (Object)sdbtb;
            brebk;

        cbse DbtbBuffer.TYPE_INT:

            int[] idbtb;

            if (obj == null)
                idbtb = new int[numDbtbElems];
            else
                idbtb = (int[])obj;

            for (int i=0; i<numDbtbElems; i++) {
                idbtb[i] = dbtb.getElem(bbnkIndices[i],
                                        pixelOffset + bbndOffsets[i]);
            }

            obj = (Object)idbtb;
            brebk;

        cbse DbtbBuffer.TYPE_FLOAT:

            flobt[] fdbtb;

            if (obj == null)
                fdbtb = new flobt[numDbtbElems];
            else
                fdbtb = (flobt[])obj;

            for (int i=0; i<numDbtbElems; i++) {
                fdbtb[i] = dbtb.getElemFlobt(bbnkIndices[i],
                                             pixelOffset + bbndOffsets[i]);
            }

            obj = (Object)fdbtb;
            brebk;

        cbse DbtbBuffer.TYPE_DOUBLE:

            double[] ddbtb;

            if (obj == null)
                ddbtb = new double[numDbtbElems];
            else
                ddbtb = (double[])obj;

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
     * Returns bll sbmples for the specified pixel in bn int brrby,
     * one sbmple per brrby element.
     * An <code>ArrbyIndexOutOfBoundsException</code> might be thrown if
     * the coordinbtes bre not in bounds.
     * @pbrbm x         the X coordinbte of the pixel locbtion
     * @pbrbm y         the Y coordinbte of the pixel locbtion
     * @pbrbm iArrby    If non-null, returns the sbmples in this brrby
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb
     * @return the sbmples of the specified pixel.
     * @see #setPixel(int, int, int[], DbtbBuffer)
     *
     * @throws NullPointerException if dbtb is null.
     * @throws ArrbyIndexOutOfBoundsException if the coordinbtes bre
     * not in bounds, or if iArrby is too smbll to hold the output.
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
        int pixelOffset = y*scbnlineStride + x*pixelStride;
        for (int i=0; i<numBbnds; i++) {
            pixels[i] = dbtb.getElem(bbnkIndices[i],
                                     pixelOffset + bbndOffsets[i]);
        }
        return pixels;
    }

    /**
     * Returns bll sbmples for the specified rectbngle of pixels in
     * bn int brrby, one sbmple per brrby element.
     * An <code>ArrbyIndexOutOfBoundsException</code> might be thrown if
     * the coordinbtes bre not in bounds.
     * @pbrbm x         The X coordinbte of the upper left pixel locbtion
     * @pbrbm y         The Y coordinbte of the upper left pixel locbtion
     * @pbrbm w         The width of the pixel rectbngle
     * @pbrbm h         The height of the pixel rectbngle
     * @pbrbm iArrby    If non-null, returns the sbmples in this brrby
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb
     * @return the sbmples of the pixels within the specified region.
     * @see #setPixels(int, int, int, int, int[], DbtbBuffer)
     */
    public int[] getPixels(int x, int y, int w, int h,
                           int iArrby[], DbtbBuffer dbtb) {
        int x1 = x + w;
        int y1 = y + h;

        if (x < 0 || x >= width || w > width || x1 < 0 || x1 > width ||
            y < 0 || y >= height || y > height || y1 < 0 || y1 >  height)
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
        int lineOffset = y*scbnlineStride + x*pixelStride;
        int srcOffset = 0;

        for (int i = 0; i < h; i++) {
           int pixelOffset = lineOffset;
           for (int j = 0; j < w; j++) {
              for (int k=0; k < numBbnds; k++) {
                 pixels[srcOffset++] =
                    dbtb.getElem(bbnkIndices[k], pixelOffset + bbndOffsets[k]);
              }
              pixelOffset += pixelStride;
           }
           lineOffset += scbnlineStride;
        }
        return pixels;
    }

    /**
     * Returns bs int the sbmple in b specified bbnd for the pixel
     * locbted bt (x,y).
     * An <code>ArrbyIndexOutOfBoundsException</code> might be thrown if
     * the coordinbtes bre not in bounds.
     * @pbrbm x         the X coordinbte of the pixel locbtion
     * @pbrbm y         the Y coordinbte of the pixel locbtion
     * @pbrbm b         the bbnd to return
     * @pbrbm dbtb      the <code>DbtbBuffer</code> contbining the imbge dbtb
     * @return the sbmple in b specified bbnd for the specified pixel
     * @see #setSbmple(int, int, int, int, DbtbBuffer)
     */
    public int getSbmple(int x, int y, int b, DbtbBuffer dbtb) {
        // Bounds check for 'b' will be performed butombticblly
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int sbmple = dbtb.getElem(bbnkIndices[b],
                                  y*scbnlineStride + x*pixelStride +
                                  bbndOffsets[b]);
        return sbmple;
    }

    /**
     * Returns the sbmple in b specified bbnd
     * for the pixel locbted bt (x,y) bs b flobt.
     * An <code>ArrbyIndexOutOfBoundsException</code> might be
     * thrown if the coordinbtes bre not in bounds.
     * @pbrbm x         The X coordinbte of the pixel locbtion
     * @pbrbm y         The Y coordinbte of the pixel locbtion
     * @pbrbm b         The bbnd to return
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb
     * @return b flobt vblue representing the sbmple in the specified
     * bbnd for the specified pixel.
     */
    public flobt getSbmpleFlobt(int x, int y, int b, DbtbBuffer dbtb) {
        // Bounds check for 'b' will be performed butombticblly
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }

        flobt sbmple = dbtb.getElemFlobt(bbnkIndices[b],
                                         y*scbnlineStride + x*pixelStride +
                                         bbndOffsets[b]);
        return sbmple;
    }

    /**
     * Returns the sbmple in b specified bbnd
     * for b pixel locbted bt (x,y) bs b double.
     * An <code>ArrbyIndexOutOfBoundsException</code> might be
     * thrown if the coordinbtes bre not in bounds.
     * @pbrbm x         The X coordinbte of the pixel locbtion
     * @pbrbm y         The Y coordinbte of the pixel locbtion
     * @pbrbm b         The bbnd to return
     * @pbrbm dbtb      The DbtbBuffer contbining the imbge dbtb
     * @return b double vblue representing the sbmple in the specified
     * bbnd for the specified pixel.
     */
    public double getSbmpleDouble(int x, int y, int b, DbtbBuffer dbtb) {
        // Bounds check for 'b' will be performed butombticblly
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }

        double sbmple = dbtb.getElemDouble(bbnkIndices[b],
                                           y*scbnlineStride + x*pixelStride +
                                           bbndOffsets[b]);
        return sbmple;
    }

    /**
     * Returns the sbmples in b specified bbnd for the specified rectbngle
     * of pixels in bn int brrby, one sbmple per dbtb brrby element.
     * An <code>ArrbyIndexOutOfBoundsException</code> might be thrown if
     * the coordinbtes bre not in bounds.
     * @pbrbm x         The X coordinbte of the upper left pixel locbtion
     * @pbrbm y         The Y coordinbte of the upper left pixel locbtion
     * @pbrbm w         the width of the pixel rectbngle
     * @pbrbm h         the height of the pixel rectbngle
     * @pbrbm b         the bbnd to return
     * @pbrbm iArrby    if non-<code>null</code>, returns the sbmples
     *                  in this brrby
     * @pbrbm dbtb      the <code>DbtbBuffer</code> contbining the imbge dbtb
     * @return the sbmples in the specified bbnd of the specified pixel
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
        int lineOffset = y*scbnlineStride + x*pixelStride +  bbndOffsets[b];
        int srcOffset = 0;

        for (int i = 0; i < h; i++) {
           int sbmpleOffset = lineOffset;
           for (int j = 0; j < w; j++) {
              sbmples[srcOffset++] = dbtb.getElem(bbnkIndices[b],
                                                  sbmpleOffset);
              sbmpleOffset += pixelStride;
           }
           lineOffset += scbnlineStride;
        }
        return sbmples;
    }

    /**
     * Sets the dbtb for b single pixel in the specified
     * <code>DbtbBuffer</code> from b primitive brrby of type
     * <code>TrbnsferType</code>.  For b <code>ComponentSbmpleModel</code>,
     * this is the sbme bs the dbtb type, bnd sbmples bre trbnsferred
     * one per brrby element.
     * <p>
     * The following code illustrbtes trbnsferring dbtb for one pixel from
     * <code>DbtbBuffer</code> <code>db1</code>, whose storbge lbyout is
     * described by <code>ComponentSbmpleModel</code> <code>csm1</code>,
     * to <code>DbtbBuffer</code> <code>db2</code>, whose storbge lbyout
     * is described by <code>ComponentSbmpleModel</code> <code>csm2</code>.
     * The trbnsfer is usublly more efficient thbn using
     * <code>getPixel</code> bnd <code>setPixel</code>.
     * <pre>
     *       ComponentSbmpleModel csm1, csm2;
     *       DbtbBufferInt db1, db2;
     *       csm2.setDbtbElements(x, y, csm1.getDbtbElements(x, y, null, db1),
     *                            db2);
     * </pre>
     * Using <code>getDbtbElements</code> bnd <code>setDbtbElements</code>
     * to trbnsfer between two <code>DbtbBuffer/SbmpleModel</code> pbirs
     * is legitimbte if the <code>SbmpleModel</code> objects hbve
     * the sbme number of bbnds, corresponding bbnds hbve the sbme number of
     * bits per sbmple, bnd the <code>TrbnsferType</code>s bre the sbme.
     * <p>
     * A <code>ClbssCbstException</code> is thrown if <code>obj</code> is not
     * b primitive brrby of type <code>TrbnsferType</code>.
     * An <code>ArrbyIndexOutOfBoundsException</code> might be thrown if
     * the coordinbtes bre not in bounds, or if <code>obj</code> is not lbrge
     * enough to hold the pixel dbtb.
     * @pbrbm x         the X coordinbte of the pixel locbtion
     * @pbrbm y         the Y coordinbte of the pixel locbtion
     * @pbrbm obj       b primitive brrby contbining pixel dbtb
     * @pbrbm dbtb      the DbtbBuffer contbining the imbge dbtb
     * @see #getDbtbElements(int, int, Object, DbtbBuffer)
     */
    public void setDbtbElements(int x, int y, Object obj, DbtbBuffer dbtb) {
        if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }

        int type = getTrbnsferType();
        int numDbtbElems = getNumDbtbElements();
        int pixelOffset = y*scbnlineStride + x*pixelStride;

        switch(type) {

        cbse DbtbBuffer.TYPE_BYTE:

            byte[] bbrrby = (byte[])obj;

            for (int i=0; i<numDbtbElems; i++) {
                dbtb.setElem(bbnkIndices[i], pixelOffset + bbndOffsets[i],
                           ((int)bbrrby[i])&0xff);
            }
            brebk;

        cbse DbtbBuffer.TYPE_USHORT:
        cbse DbtbBuffer.TYPE_SHORT:

            short[] sbrrby = (short[])obj;

            for (int i=0; i<numDbtbElems; i++) {
                dbtb.setElem(bbnkIndices[i], pixelOffset + bbndOffsets[i],
                           ((int)sbrrby[i])&0xffff);
            }
            brebk;

        cbse DbtbBuffer.TYPE_INT:

            int[] ibrrby = (int[])obj;

            for (int i=0; i<numDbtbElems; i++) {
                dbtb.setElem(bbnkIndices[i],
                             pixelOffset + bbndOffsets[i], ibrrby[i]);
            }
            brebk;

        cbse DbtbBuffer.TYPE_FLOAT:

            flobt[] fbrrby = (flobt[])obj;

            for (int i=0; i<numDbtbElems; i++) {
                dbtb.setElemFlobt(bbnkIndices[i],
                             pixelOffset + bbndOffsets[i], fbrrby[i]);
            }
            brebk;

        cbse DbtbBuffer.TYPE_DOUBLE:

            double[] dbrrby = (double[])obj;

            for (int i=0; i<numDbtbElems; i++) {
                dbtb.setElemDouble(bbnkIndices[i],
                             pixelOffset + bbndOffsets[i], dbrrby[i]);
            }
            brebk;

        }
    }

    /**
     * Sets b pixel in the <code>DbtbBuffer</code> using bn int brrby of
     * sbmples for input.  An <code>ArrbyIndexOutOfBoundsException</code>
     * might be thrown if the coordinbtes bre
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
       int pixelOffset = y*scbnlineStride + x*pixelStride;
       for (int i=0; i<numBbnds; i++) {
           dbtb.setElem(bbnkIndices[i],
                        pixelOffset + bbndOffsets[i],iArrby[i]);
       }
    }

    /**
     * Sets bll sbmples for b rectbngle of pixels from bn int brrby contbining
     * one sbmple per brrby element.
     * An <code>ArrbyIndexOutOfBoundsException</code> might be thrown if the
     * coordinbtes bre not in bounds.
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

        int lineOffset = y*scbnlineStride + x*pixelStride;
        int srcOffset = 0;

        for (int i = 0; i < h; i++) {
           int pixelOffset = lineOffset;
           for (int j = 0; j < w; j++) {
              for (int k=0; k < numBbnds; k++) {
                 dbtb.setElem(bbnkIndices[k], pixelOffset + bbndOffsets[k],
                              iArrby[srcOffset++]);
              }
              pixelOffset += pixelStride;
           }
           lineOffset += scbnlineStride;
        }
    }

    /**
     * Sets b sbmple in the specified bbnd for the pixel locbted bt (x,y)
     * in the <code>DbtbBuffer</code> using bn int for input.
     * An <code>ArrbyIndexOutOfBoundsException</code> might be thrown if the
     * coordinbtes bre not in bounds.
     * @pbrbm x         The X coordinbte of the pixel locbtion
     * @pbrbm y         The Y coordinbte of the pixel locbtion
     * @pbrbm b         the bbnd to set
     * @pbrbm s         the input sbmple bs bn int
     * @pbrbm dbtb      the DbtbBuffer contbining the imbge dbtb
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
                     y*scbnlineStride + x*pixelStride + bbndOffsets[b], s);
    }

    /**
     * Sets b sbmple in the specified bbnd for the pixel locbted bt (x,y)
     * in the <code>DbtbBuffer</code> using b flobt for input.
     * An <code>ArrbyIndexOutOfBoundsException</code> might be thrown if
     * the coordinbtes bre not in bounds.
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
                          y*scbnlineStride + x*pixelStride + bbndOffsets[b],
                          s);
    }

    /**
     * Sets b sbmple in the specified bbnd for the pixel locbted bt (x,y)
     * in the <code>DbtbBuffer</code> using b double for input.
     * An <code>ArrbyIndexOutOfBoundsException</code> might be thrown if
     * the coordinbtes bre not in bounds.
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
                          y*scbnlineStride + x*pixelStride + bbndOffsets[b],
                          s);
    }

    /**
     * Sets the sbmples in the specified bbnd for the specified rectbngle
     * of pixels from bn int brrby contbining one sbmple per dbtb brrby element.
     * An <code>ArrbyIndexOutOfBoundsException</code> might be thrown if the
     * coordinbtes bre not in bounds.
     * @pbrbm x         The X coordinbte of the upper left pixel locbtion
     * @pbrbm y         The Y coordinbte of the upper left pixel locbtion
     * @pbrbm w         The width of the pixel rectbngle
     * @pbrbm h         The height of the pixel rectbngle
     * @pbrbm b         The bbnd to set
     * @pbrbm iArrby    The input sbmples in bn int brrby
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
        int lineOffset = y*scbnlineStride + x*pixelStride + bbndOffsets[b];
        int srcOffset = 0;

        for (int i = 0; i < h; i++) {
           int sbmpleOffset = lineOffset;
           for (int j = 0; j < w; j++) {
              dbtb.setElem(bbnkIndices[b], sbmpleOffset, iArrby[srcOffset++]);
              sbmpleOffset += pixelStride;
           }
           lineOffset += scbnlineStride;
        }
    }

    public boolebn equbls(Object o) {
        if ((o == null) || !(o instbnceof ComponentSbmpleModel)) {
            return fblse;
        }

        ComponentSbmpleModel thbt = (ComponentSbmpleModel)o;
        return this.width == thbt.width &&
            this.height == thbt.height &&
            this.numBbnds == thbt.numBbnds &&
            this.dbtbType == thbt.dbtbType &&
            Arrbys.equbls(this.bbndOffsets, thbt.bbndOffsets) &&
            Arrbys.equbls(this.bbnkIndices, thbt.bbnkIndices) &&
            this.numBbnds == thbt.numBbnds &&
            this.numBbnks == thbt.numBbnks &&
            this.scbnlineStride == thbt.scbnlineStride &&
            this.pixelStride == thbt.pixelStride;
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
        for (int i = 0; i < bbndOffsets.length; i++) {
            hbsh ^= bbndOffsets[i];
            hbsh <<= 8;
        }
        for (int i = 0; i < bbnkIndices.length; i++) {
            hbsh ^= bbnkIndices[i];
            hbsh <<= 8;
        }
        hbsh ^= numBbnds;
        hbsh <<= 8;
        hbsh ^= numBbnks;
        hbsh <<= 8;
        hbsh ^= scbnlineStride;
        hbsh <<= 8;
        hbsh ^= pixelStride;
        return hbsh;
    }
}
