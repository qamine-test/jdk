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

pbckbge sun.bwt.imbge;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.imbge.RbsterFormbtException;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.bwt.imbge.MultiPixelPbckedSbmpleModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DbtbBufferByte;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Point;

/**
 * This clbss is useful for describing 1, 2, or 4 bit imbge dbtb
 * elements.  This rbster hbs one bbnd whose pixels bre pbcked
 * together into individubl bytes in b single byte brrby.  This type
 * of rbster cbn be used with bn IndexColorModel. This rbster uses b
 * MultiPixelPbckedSbmpleModel.
 *
 */
public clbss BytePbckedRbster extends SunWritbbleRbster {

    /** The dbtb bit offset for ebch pixel. */
    int           dbtbBitOffset;

    /** Scbnline stride of the imbge dbtb contbined in this Rbster. */
    int           scbnlineStride;

    /**
     * The bit stride of b pixel, equbl to the totbl number of bits
     * required to store b pixel.
     */
    int           pixelBitStride;

    /** The bit mbsk for extrbcting the pixel. */
    int           bitMbsk;

    /** The imbge dbtb brrby. */
    byte[]        dbtb;

    /** 8 minus the pixel bit stride. */
    int shiftOffset;

    int type;

    /** A cbched copy of minX + width for use in bounds checks. */
    privbte int mbxX;

    /** A cbched copy of minY + height for use in bounds checks. */
    privbte int mbxY;

    stbtic privbte nbtive void initIDs();
    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        NbtiveLibLobder.lobdLibrbries();
        initIDs();
    }

    /**
     * Constructs b BytePbckedRbster with the given SbmpleModel.
     * The Rbster's upper left corner is origin bnd it is the sbme
     * size bs the SbmpleModel.  A DbtbBuffer lbrge enough to describe the
     * Rbster is butombticblly crebted.  SbmpleModel must be of type
     * MultiPixelPbckedSbmpleModel.
     * @pbrbm sbmpleModel     The SbmpleModel thbt specifies the lbyout.
     * @pbrbm origin          The Point thbt specified the origin.
     */
    public BytePbckedRbster(SbmpleModel sbmpleModel,
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
     * Constructs b BytePbckedRbster with the given SbmpleModel
     * bnd DbtbBuffer.  The Rbster's upper left corner is origin bnd
     * it is the sbme size bs the SbmpleModel.  The DbtbBuffer is not
     * initiblized bnd must be b DbtbBufferByte compbtible with SbmpleModel.
     * SbmpleModel must be of type MultiPixelPbckedSbmpleModel.
     * @pbrbm sbmpleModel     The SbmpleModel thbt specifies the lbyout.
     * @pbrbm dbtbBuffer      The DbtbBufferShort thbt contbins the imbge dbtb.
     * @pbrbm origin          The Point thbt specifies the origin.
     */
    public BytePbckedRbster(SbmpleModel sbmpleModel,
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
     * Constructs b BytePbckedRbster with the given SbmpleModel,
     * DbtbBuffer, bnd pbrent.  DbtbBuffer must be b DbtbBufferByte bnd
     * SbmpleModel must be of type MultiPixelPbckedSbmpleModel.
     * When trbnslbted into the bbse Rbster's
     * coordinbte system, bRegion must be contbined by the bbse Rbster.
     * Origin is the coordinbte in the new Rbster's coordinbte system of
     * the origin of the bbse Rbster.  (The bbse Rbster is the Rbster's
     * bncestor which hbs no pbrent.)
     *
     * Note thbt this constructor should generblly be cblled by other
     * constructors or crebte methods, it should not be used directly.
     * @pbrbm sbmpleModel     The SbmpleModel thbt specifies the lbyout.
     * @pbrbm dbtbBuffer      The DbtbBufferShort thbt contbins the imbge dbtb.
     * @pbrbm bRegion         The Rectbngle thbt specifies the imbge breb.
     * @pbrbm origin          The Point thbt specifies the origin.
     * @pbrbm pbrent          The pbrent (if bny) of this rbster.
     *
     * @exception RbsterFormbtException if the pbrbmeters do not conform
     * to requirements of this Rbster type.
     */
    public BytePbckedRbster(SbmpleModel sbmpleModel,
                            DbtbBuffer dbtbBuffer,
                            Rectbngle bRegion,
                            Point origin,
                            BytePbckedRbster pbrent){
        super(sbmpleModel,dbtbBuffer,bRegion,origin, pbrent);
        this.mbxX = minX + width;
        this.mbxY = minY + height;

        if (!(dbtbBuffer instbnceof DbtbBufferByte)) {
           throw new RbsterFormbtException("BytePbckedRbsters must hbve" +
                "byte DbtbBuffers");
        }
        DbtbBufferByte dbb = (DbtbBufferByte)dbtbBuffer;
        this.dbtb = steblDbtb(dbb, 0);
        if (dbb.getNumBbnks() != 1) {
            throw new
                RbsterFormbtException("DbtbBuffer for BytePbckedRbsters"+
                                      " must only hbve 1 bbnk.");
        }
        int dbOffset = dbb.getOffset();

        if (sbmpleModel instbnceof MultiPixelPbckedSbmpleModel) {
            MultiPixelPbckedSbmpleModel mppsm =
                (MultiPixelPbckedSbmpleModel)sbmpleModel;
            this.type = IntegerComponentRbster.TYPE_BYTE_BINARY_SAMPLES;
            pixelBitStride = mppsm.getPixelBitStride();
            if (pixelBitStride != 1 &&
                pixelBitStride != 2 &&
                pixelBitStride != 4) {
                throw new RbsterFormbtException
                  ("BytePbckedRbsters must hbve b bit depth of 1, 2, or 4");
            }
            scbnlineStride = mppsm.getScbnlineStride();
            dbtbBitOffset = mppsm.getDbtbBitOffset() + dbOffset*8;
            int xOffset = bRegion.x - origin.x;
            int yOffset = bRegion.y - origin.y;
            dbtbBitOffset += xOffset*pixelBitStride + yOffset*scbnlineStride*8;
            bitMbsk = (1 << pixelBitStride) -1;
            shiftOffset = 8 - pixelBitStride;
        } else {
            throw new RbsterFormbtException("BytePbckedRbsters must hbve"+
                "MultiPixelPbckedSbmpleModel");
        }
        verify(fblse);
    }

    /**
     * Returns the dbtb bit offset for the Rbster.  The dbtb
     * bit offset is the bit index into the dbtb brrby element
     * corresponding to the first sbmple of the first scbnline.
     */
    public int getDbtbBitOffset() {
        return dbtbBitOffset;
    }

    /**
     * Returns the scbnline stride -- the number of dbtb brrby elements between
     * b given sbmple bnd the sbmple in the sbme column
     * of the next row.
     */
    public int getScbnlineStride() {
        return scbnlineStride;
    }

    /**
     * Returns pixel bit stride -- the number of bits between two
     * sbmples on the sbme scbnline.
     */
    public int getPixelBitStride() {
        return pixelBitStride;
    }

    /**
     * Returns b reference to the entire dbtb brrby.
     */
    public byte[] getDbtbStorbge() {
        return dbtb;
    }

    /**
     * Returns the dbtb element bt the specified
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
        int bitnum = dbtbBitOffset + (x-minX) * pixelBitStride;
        // Fix 4184283
        int element = dbtb[(y-minY) * scbnlineStride + (bitnum >> 3)] & 0xff;
        int shift = shiftOffset - (bitnum & 7);
        outDbtb[0] = (byte)((element >> shift) & bitMbsk);
        return outDbtb;
    }

    /**
     * Returns the pixel dbtb for the specified rectbngle of pixels in b
     * primitive brrby of type TrbnsferType.
     * For imbge dbtb supported by the Jbvb 2D API, this
     * will be one of DbtbBuffer.TYPE_BYTE, DbtbBuffer.TYPE_USHORT, or
     * DbtbBuffer.TYPE_INT.  Dbtb mby be returned in b pbcked formbt,
     * thus increbsing efficiency for dbtb trbnsfers.
     *
     * An ArrbyIndexOutOfBoundsException mby be thrown
     * if the coordinbtes bre not in bounds.
     * A ClbssCbstException will be thrown if the input object is non null
     * bnd references bnything other thbn bn brrby of TrbnsferType.
     * @see jbvb.bwt.imbge.SbmpleModel#getDbtbElements(int, int, int, int, Object, DbtbBuffer)
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm w        Width of the pixel rectbngle.
     * @pbrbm h        Height of the pixel rectbngle.
     * @pbrbm outDbtb  An object reference to bn brrby of type defined by
     *                 getTrbnsferType() bnd length w*h*getNumDbtbElements().
     *                 If null, bn brrby of bppropribte type bnd size will be
     *                 bllocbted.
     * @return         An object reference to bn brrby of type defined by
     *                 getTrbnsferType() with the requested pixel dbtb.
     */
    public Object getDbtbElements(int x, int y, int w, int h,
                                  Object outDbtb) {
        return getByteDbtb(x, y, w, h, (byte[])outDbtb);
    }

    /**
     * Returns bn brrby  of dbtb elements from the specified rectbngulbr
     * region.
     *
     * An ArrbyIndexOutOfBounds exception will be thrown bt runtime
     * if the pixel coordinbtes bre out of bounds.
     * A ClbssCbstException will be thrown if the input object is non null
     * bnd references bnything other thbn bn brrby of trbnsferType.
     * <pre>
     *       byte[] bbndDbtb = (byte[])rbster.getPixelDbtb(x, y, w, h, null);
     *       int pixel;
     *       // To find b dbtb element bt locbtion (x2, y2)
     *       pixel = bbndDbtb[((y2-y)*w + (x2-x))];
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
    public Object getPixelDbtb(int x, int y, int w, int h, Object obj) {
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
        int pixbits = pixelBitStride;
        int scbnbit = dbtbBitOffset + (x-minX) * pixbits;
        int index = (y-minY) * scbnlineStride;
        int outindex = 0;
        byte dbtb[] = this.dbtb;

        for (int j = 0; j < h; j++) {
            int bitnum = scbnbit;
            for (int i = 0; i < w; i++) {
                int shift = shiftOffset - (bitnum & 7);
                outDbtb[outindex++] =
                    (byte)(bitMbsk & (dbtb[index + (bitnum >> 3)] >> shift));
                bitnum += pixbits;
            }
            index += scbnlineStride;
        }
        return outDbtb;
    }

    /**
     * Returns b byte brrby contbining the specified dbtb elements
     * from the dbtb brrby.  The bbnd index will be ignored.
     * An ArrbyIndexOutOfBounds exception will be thrown bt runtime
     * if the pixel coordinbtes bre out of bounds.
     * <pre>
     *       byte[] byteDbtb = getByteDbtb(x, y, bbnd, w, h, null);
     *       // To find b dbtb element bt locbtion (x2, y2)
     *       byte element = byteDbtb[(y2-y)*w + (x2-x)];
     * </pre>
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm width    Width of the pixel rectbngle.
     * @pbrbm height   Height of the pixel rectbngle.
     * @pbrbm bbnd     The bbnd to return, is ignored.
     * @pbrbm outDbtb  If non-null, dbtb elements
     *                 bt the specified locbtions bre returned in this brrby.
     * @return         Byte brrby with dbtb elements.
     */
    public byte[] getByteDbtb(int x, int y, int w, int h,
                              int bbnd, byte[] outDbtb) {
        return getByteDbtb(x, y, w, h, outDbtb);
    }

    /**
     * Returns b byte brrby contbining the specified dbtb elements
     * from the dbtb brrby.
     * An ArrbyIndexOutOfBounds exception will be thrown bt runtime
     * if the pixel coordinbtes bre out of bounds.
     * <pre>
     *       byte[] byteDbtb = rbster.getByteDbtb(x, y, w, h, null);
     *       byte pixel;
     *       // To find b dbtb element bt locbtion (x2, y2)
     *       pixel = byteDbtb[((y2-y)*w + (x2-x))];
     * </pre>
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm width    Width of the pixel rectbngle.
     * @pbrbm height   Height of the pixel rectbngle.
     * @pbrbm outDbtb  If non-null, dbtb elements
     *                 bt the specified locbtions bre returned in this brrby.
     * @return         Byte brrby with dbtb elements.
     */
    public byte[] getByteDbtb(int x, int y, int w, int h, byte[] outDbtb) {
        if ((x < this.minX) || (y < this.minY) ||
            (x + w > this.mbxX) || (y + h > this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        if (outDbtb == null) {
            outDbtb = new byte[w * h];
        }
        int pixbits = pixelBitStride;
        int scbnbit = dbtbBitOffset + (x-minX) * pixbits;
        int index = (y-minY) * scbnlineStride;
        int outindex = 0;
        byte dbtb[] = this.dbtb;

        for (int j = 0; j < h; j++) {
            int bitnum = scbnbit;
            int element;

            // Process initibl portion of scbnline
            int i = 0;
            while ((i < w) && ((bitnum & 7) != 0)) {
                int shift = shiftOffset - (bitnum & 7);
                outDbtb[outindex++] =
                    (byte)(bitMbsk & (dbtb[index + (bitnum >> 3)] >> shift));
                bitnum += pixbits;
                i++;
            }

            // Process centrbl portion of scbnline 8 pixels bt b time
            int inIndex = index + (bitnum >> 3);
            switch (pixbits) {
            cbse 1:
                for (; i < w - 7; i += 8) {
                    element = dbtb[inIndex++];
                    outDbtb[outindex++] = (byte)((element >> 7) & 1);
                    outDbtb[outindex++] = (byte)((element >> 6) & 1);
                    outDbtb[outindex++] = (byte)((element >> 5) & 1);
                    outDbtb[outindex++] = (byte)((element >> 4) & 1);
                    outDbtb[outindex++] = (byte)((element >> 3) & 1);
                    outDbtb[outindex++] = (byte)((element >> 2) & 1);
                    outDbtb[outindex++] = (byte)((element >> 1) & 1);
                    outDbtb[outindex++] = (byte)(element & 1);
                    bitnum += 8;
                }
                brebk;

            cbse 2:
                for (; i < w - 7; i += 8) {
                    element = dbtb[inIndex++];
                    outDbtb[outindex++] = (byte)((element >> 6) & 3);
                    outDbtb[outindex++] = (byte)((element >> 4) & 3);
                    outDbtb[outindex++] = (byte)((element >> 2) & 3);
                    outDbtb[outindex++] = (byte)(element & 3);

                    element = dbtb[inIndex++];
                    outDbtb[outindex++] = (byte)((element >> 6) & 3);
                    outDbtb[outindex++] = (byte)((element >> 4) & 3);
                    outDbtb[outindex++] = (byte)((element >> 2) & 3);
                    outDbtb[outindex++] = (byte)(element & 3);

                    bitnum += 16;
                }
                brebk;

            cbse 4:
                for (; i < w - 7; i += 8) {
                    element = dbtb[inIndex++];
                    outDbtb[outindex++] = (byte)((element >> 4) & 0xf);
                    outDbtb[outindex++] = (byte)(element & 0xf);

                    element = dbtb[inIndex++];
                    outDbtb[outindex++] = (byte)((element >> 4) & 0xf);
                    outDbtb[outindex++] = (byte)(element & 0xf);

                    element = dbtb[inIndex++];
                    outDbtb[outindex++] = (byte)((element >> 4) & 0xf);
                    outDbtb[outindex++] = (byte)(element & 0xf);

                    element = dbtb[inIndex++];
                    outDbtb[outindex++] = (byte)((element >> 4) & 0xf);
                    outDbtb[outindex++] = (byte)(element & 0xf);

                    bitnum += 32;
                }
                brebk;
            }

            // Process finbl portion of scbnline
            for (; i < w; i++) {
                int shift = shiftOffset - (bitnum & 7);
                outDbtb[outindex++] =
                    (byte) (bitMbsk & (dbtb[index + (bitnum >> 3)] >> shift));
                bitnum += pixbits;
            }

            index += scbnlineStride;
        }

        return outDbtb;
    }

    /**
     * Stores the dbtb elements bt the specified locbtion.
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
        int bitnum = dbtbBitOffset + (x-minX) * pixelBitStride;
        int index = (y-minY) * scbnlineStride + (bitnum >> 3);
        int shift = shiftOffset - (bitnum & 7);

        byte element = dbtb[index];
        element &= ~(bitMbsk << shift);
        element |= (inDbtb[0] & bitMbsk) << shift;
        dbtb[index] = element;

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
        // Check if we cbn use fbst code
        if (!(inRbster instbnceof BytePbckedRbster) ||
            ((BytePbckedRbster)inRbster).pixelBitStride != pixelBitStride) {
            super.setDbtbElements(x, y, inRbster);
            return;
        }

        int srcOffX = inRbster.getMinX();
        int srcOffY = inRbster.getMinY();
        int dstOffX = srcOffX + x;
        int dstOffY = srcOffY + y;
        int width = inRbster.getWidth();
        int height = inRbster.getHeight();
        if ((dstOffX < this.minX) || (dstOffY < this.minY) ||
            (dstOffX + width > this.mbxX) || (dstOffY + height > this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        setDbtbElements(dstOffX, dstOffY,
                        srcOffX, srcOffY,
                        width, height,
                        (BytePbckedRbster)inRbster);
    }

    /**
     * Stores the Rbster dbtb bt the specified locbtion.
     * @pbrbm dstX The bbsolute X coordinbte of the destinbtion pixel
     * thbt will receive b copy of the upper-left pixel of the
     * inRbster
     * @pbrbm dstY The bbsolute Y coordinbte of the destinbtion pixel
     * thbt will receive b copy of the upper-left pixel of the
     * inRbster
     * @pbrbm srcX The bbsolute X coordinbte of the upper-left source
     * pixel thbt will be copied into this Rbster
     * @pbrbm srcY The bbsolute Y coordinbte of the upper-left source
     * pixel thbt will be copied into this Rbster
     * @pbrbm width      The number of pixels to store horizontblly
     * @pbrbm height     The number of pixels to store verticblly
     * @pbrbm inRbster   BytePbckedRbster of dbtb to plbce bt x,y locbtion.
     */
    privbte void setDbtbElements(int dstX, int dstY,
                                 int srcX, int srcY,
                                 int width, int height,
                                 BytePbckedRbster inRbster) {
        // Assume bounds checking hbs been performed previously
        if (width <= 0 || height <= 0) {
            return;
        }

        byte[] inDbtb = inRbster.dbtb;
        byte[] outDbtb = this.dbtb;

        int inscbn = inRbster.scbnlineStride;
        int outscbn = this.scbnlineStride;
        int inbit = inRbster.dbtbBitOffset +
                      8 * (srcY - inRbster.minY) * inscbn +
                      (srcX - inRbster.minX) * inRbster.pixelBitStride;
        int outbit = (this.dbtbBitOffset +
                      8 * (dstY - minY) * outscbn +
                      (dstX - minX) * this.pixelBitStride);
        int copybits = width * pixelBitStride;

        // Check whether the sbme bit blignment is present in both
        // Rbsters; if so, we cbn copy whole bytes using
        // System.brrbycopy.  If not, we must do b "funnel shift"
        // where bdjbcent bytes contribute to ebch destinbtion byte.
        if ((inbit & 7) == (outbit & 7)) {
            // copy is bit bligned
            int bitpos = outbit & 7;
            if (bitpos != 0) {
                int bits = 8 - bitpos;
                // Copy pbrtibl bytes on left
                int inbyte = inbit >> 3;
                int outbyte = outbit >> 3;
                int mbsk = 0xff >> bitpos;
                if (copybits < bits) {
                    // Fix bug 4399076: previously hbd '8 - copybits' instebd
                    // of 'bits - copybits'.
                    //
                    // Prior to the this expression, 'mbsk' hbs its rightmost
                    // 'bits' bits set to '1'.  We wbnt it to hbve b totbl
                    // of 'copybits' bits set, therefore we wbnt to introduce
                    // 'bits - copybits' zeroes on the right.
                    mbsk &= 0xff << (bits - copybits);
                    bits = copybits;
                }
                for (int j = 0; j < height; j++) {
                    int element = outDbtb[outbyte];
                    element &= ~mbsk;
                    element |= (inDbtb[inbyte] & mbsk);
                    outDbtb[outbyte] = (byte) element;
                    inbyte += inscbn;
                    outbyte += outscbn;
                }
                inbit += bits;
                outbit += bits;
                copybits -= bits;
            }
            if (copybits >= 8) {
                // Copy whole bytes
                int inbyte = inbit >> 3;
                int outbyte = outbit >> 3;
                int copybytes = copybits >> 3;
                if (copybytes == inscbn && inscbn == outscbn) {
                    System.brrbycopy(inDbtb, inbyte,
                                     outDbtb, outbyte,
                                     inscbn * height);
                } else {
                    for (int j = 0; j < height; j++) {
                        System.brrbycopy(inDbtb, inbyte,
                                         outDbtb, outbyte,
                                         copybytes);
                        inbyte += inscbn;
                        outbyte += outscbn;
                    }
                }

                int bits = copybytes*8;
                inbit += bits;
                outbit += bits;
                copybits -= bits;
            }
            if (copybits > 0) {
                // Copy pbrtibl bytes on right
                int inbyte = inbit >> 3;
                int outbyte = outbit >> 3;
                int mbsk = (0xff00 >> copybits) & 0xff;
                for (int j = 0; j < height; j++) {
                    int element = outDbtb[outbyte];
                    element &= ~mbsk;
                    element |= (inDbtb[inbyte] & mbsk);
                    outDbtb[outbyte] = (byte) element;
                    inbyte += inscbn;
                    outbyte += outscbn;
                }
            }
        } else {
            // Unbligned cbse, see RFE #4284166
            // Note thbt the code in thbt RFE is not correct

            // Insert bits into the first byte of the output
            // if either the stbrting bit position is not zero or
            // we bre writing fewer thbn 8 bits in totbl
            int bitpos = outbit & 7;
            if (bitpos != 0 || copybits < 8) {
                int bits = 8 - bitpos;
                int inbyte = inbit >> 3;
                int outbyte = outbit >> 3;

                int lshift = inbit & 7;
                int rshift = 8 - lshift;
                int mbsk = 0xff >> bitpos;
                if (copybits < bits) {
                    // Fix mbsk if we're only writing b pbrtibl byte
                    mbsk &= 0xff << (bits - copybits);
                    bits = copybits;
                }
                int lbstByte = inDbtb.length - 1;
                for (int j = 0; j < height; j++) {
                    // Rebd two bytes from the source if possible
                    // Don't worry bbout going over b scbnline boundbry
                    // since bny extrb bits won't get used bnywby
                    byte inDbtb0 = inDbtb[inbyte];
                    byte inDbtb1 = (byte)0;
                    if (inbyte < lbstByte) {
                        inDbtb1 = inDbtb[inbyte + 1];
                    }

                    // Insert the new bits into the output
                    int element = outDbtb[outbyte];
                    element &= ~mbsk;
                    element |= (((inDbtb0 << lshift) |
                                 ((inDbtb1 & 0xff) >> rshift))
                                >> bitpos) & mbsk;
                    outDbtb[outbyte] = (byte)element;
                    inbyte += inscbn;
                    outbyte += outscbn;
                }

                inbit += bits;
                outbit += bits;
                copybits -= bits;
            }

            // Now we hbve outbit & 7 == 0 so we cbn write
            // complete bytes for b while

            // Mbke sure we hbve work to do in the centrbl loop
            // to bvoid rebding pbst the end of the scbnline
            if (copybits >= 8) {
                int inbyte = inbit >> 3;
                int outbyte = outbit >> 3;
                int copybytes = copybits >> 3;
                int lshift = inbit & 7;
                int rshift = 8 - lshift;

                for (int j = 0; j < height; j++) {
                    int ibyte = inbyte + j*inscbn;
                    int obyte = outbyte + j*outscbn;

                    int inDbtb0 = inDbtb[ibyte];
                    // Combine bdjbcent bytes while 8 or more bits left
                    for (int i = 0; i < copybytes; i++) {
                        int inDbtb1 = inDbtb[ibyte + 1];
                        int vbl = (inDbtb0 << lshift) |
                            ((inDbtb1 & 0xff) >> rshift);
                        outDbtb[obyte] = (byte)vbl;
                        inDbtb0 = inDbtb1;

                        ++ibyte;
                        ++obyte;
                    }
                }

                int bits = copybytes*8;
                inbit += bits;
                outbit += bits;
                copybits -= bits;
            }

            // Finish lbst byte
            if (copybits > 0) {
                int inbyte = inbit >> 3;
                int outbyte = outbit >> 3;
                int mbsk = (0xff00 >> copybits) & 0xff;
                int lshift = inbit & 7;
                int rshift = 8 - lshift;

                int lbstByte = inDbtb.length - 1;
                for (int j = 0; j < height; j++) {
                    byte inDbtb0 = inDbtb[inbyte];
                    byte inDbtb1 = (byte)0;
                    if (inbyte < lbstByte) {
                        inDbtb1 = inDbtb[inbyte + 1];
                    }

                    // Insert the new bits into the output
                    int element = outDbtb[outbyte];
                    element &= ~mbsk;
                    element |= ((inDbtb0 << lshift) |
                                ((inDbtb1 & 0xff) >> rshift)) & mbsk;
                    outDbtb[outbyte] = (byte)element;

                    inbyte += inscbn;
                    outbyte += outscbn;
                }
            }
        }

        mbrkDirty();
    }

    /**
     * Copies pixels from Rbster srcRbster to this WritbbleRbster.
     * For ebch (x, y) bddress in srcRbster, the corresponding pixel
     * is copied to bddress (x+dx, y+dy) in this WritbbleRbster,
     * unless (x+dx, y+dy) fblls outside the bounds of this rbster.
     * srcRbster must hbve the sbme number of bbnds bs this WritbbleRbster.
     * The copy is b simple copy of source sbmples to the corresponding
     * destinbtion sbmples.  For detbils, see
     * {@link WritbbleRbster#setRect(Rbster)}.
     *
     * @pbrbm dx        The X trbnslbtion fbctor from src spbce to dst spbce
     *                  of the copy.
     * @pbrbm dy        The Y trbnslbtion fbctor from src spbce to dst spbce
     *                  of the copy.
     * @pbrbm srcRbster The Rbster from which to copy pixels.
     */
    public void setRect(int dx, int dy, Rbster srcRbster) {
        // Check if we cbn use fbst code
        if (!(srcRbster instbnceof BytePbckedRbster) ||
            ((BytePbckedRbster)srcRbster).pixelBitStride != pixelBitStride) {
            super.setRect(dx, dy, srcRbster);
            return;
        }

        int width  = srcRbster.getWidth();
        int height = srcRbster.getHeight();
        int srcOffX = srcRbster.getMinX();
        int srcOffY = srcRbster.getMinY();
        int dstOffX = dx+srcOffX;
        int dstOffY = dy+srcOffY;

        // Clip to this rbster
        if (dstOffX < this.minX) {
            int skipX = this.minX - dstOffX;
            width -= skipX;
            srcOffX += skipX;
            dstOffX = this.minX;
        }
        if (dstOffY < this.minY) {
            int skipY = this.minY - dstOffY;
            height -= skipY;
            srcOffY += skipY;
            dstOffY = this.minY;
        }
        if (dstOffX+width > this.mbxX) {
            width = this.mbxX - dstOffX;
        }
        if (dstOffY+height > this.mbxY) {
            height = this.mbxY - dstOffY;
        }

        setDbtbElements(dstOffX, dstOffY,
                        srcOffX, srcOffY,
                        width, height,
                        (BytePbckedRbster)srcRbster);
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
     * bt locbtion (x2, y2) would be found bt:
     * <pre>
     *      inDbtb[((y2-y)*w + (x2-x))]
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
        putByteDbtb(x, y, w, h, (byte[])obj);
    }

    /**
     * Stores b byte brrby of dbtb elements into the specified rectbngulbr
     * region.  The bbnd index will be ignored.
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
     * @pbrbm bbnd     The bbnd to set, is ignored.
     * @pbrbm inDbtb   The dbtb elements to be stored.
     */
    public void putByteDbtb(int x, int y, int w, int h,
                            int bbnd, byte[] inDbtb) {
        putByteDbtb(x, y, w, h, inDbtb);
    }

    /**
     * Stores b byte brrby of dbtb elements into the specified rectbngulbr
     * region.
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
     * @pbrbm inDbtb   The dbtb elements to be stored.
     */
    public void putByteDbtb(int x, int y, int w, int h, byte[] inDbtb) {
        if ((x < this.minX) || (y < this.minY) ||
            (x + w > this.mbxX) || (y + h > this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        if (w == 0 || h == 0) {
            return;
        }

        int pixbits = pixelBitStride;
        int scbnbit = dbtbBitOffset + (x - minX) * pixbits;
        int index = (y - minY) * scbnlineStride;
        int outindex = 0;
        byte dbtb[] = this.dbtb;
        for (int j = 0; j < h; j++) {
            int bitnum = scbnbit;
            int element;

            // Process initibl portion of scbnline
            int i = 0;
            while ((i < w) && ((bitnum & 7) != 0)) {
                int shift = shiftOffset - (bitnum & 7);
                element = dbtb[index + (bitnum >> 3)];
                element &= ~(bitMbsk << shift);
                element |= (inDbtb[outindex++] & bitMbsk) << shift;
                dbtb[index + (bitnum >> 3)] = (byte)element;

                bitnum += pixbits;
                i++;
            }

            // Process centrbl portion of scbnline 8 pixels bt b time
            int inIndex = index + (bitnum >> 3);
            switch (pixbits) {
            cbse 1:
                for (; i < w - 7; i += 8) {
                    element = (inDbtb[outindex++] & 1) << 7;
                    element |= (inDbtb[outindex++] & 1) << 6;
                    element |= (inDbtb[outindex++] & 1) << 5;
                    element |= (inDbtb[outindex++] & 1) << 4;
                    element |= (inDbtb[outindex++] & 1) << 3;
                    element |= (inDbtb[outindex++] & 1) << 2;
                    element |= (inDbtb[outindex++] & 1) << 1;
                    element |= (inDbtb[outindex++] & 1);

                    dbtb[inIndex++] = (byte)element;

                    bitnum += 8;
                }
                brebk;

            cbse 2:
                for (; i < w - 7; i += 8) {
                    element = (inDbtb[outindex++] & 3) << 6;
                    element |= (inDbtb[outindex++] & 3) << 4;
                    element |= (inDbtb[outindex++] & 3) << 2;
                    element |= (inDbtb[outindex++] & 3);
                    dbtb[inIndex++] = (byte)element;

                    element = (inDbtb[outindex++] & 3) << 6;
                    element |= (inDbtb[outindex++] & 3) << 4;
                    element |= (inDbtb[outindex++] & 3) << 2;
                    element |= (inDbtb[outindex++] & 3);
                    dbtb[inIndex++] = (byte)element;

                    bitnum += 16;
                }
                brebk;

            cbse 4:
                for (; i < w - 7; i += 8) {
                    element = (inDbtb[outindex++] & 0xf) << 4;
                    element |= (inDbtb[outindex++] & 0xf);
                    dbtb[inIndex++] = (byte)element;

                    element = (inDbtb[outindex++] & 0xf) << 4;
                    element |= (inDbtb[outindex++] & 0xf);
                    dbtb[inIndex++] = (byte)element;

                    element = (inDbtb[outindex++] & 0xf) << 4;
                    element |= (inDbtb[outindex++] & 0xf);
                    dbtb[inIndex++] = (byte)element;

                    element = (inDbtb[outindex++] & 0xf) << 4;
                    element |= (inDbtb[outindex++] & 0xf);
                    dbtb[inIndex++] = (byte)element;

                    bitnum += 32;
                }
                brebk;
            }

            // Process finbl portion of scbnline
            for (; i < w; i++) {
                int shift = shiftOffset - (bitnum & 7);

                element = dbtb[index + (bitnum >> 3)];
                element &= ~(bitMbsk << shift);
                element |= (inDbtb[outindex++] & bitMbsk) << shift;
                dbtb[index + (bitnum >> 3)] = (byte)element;

                bitnum += pixbits;
            }

            index += scbnlineStride;
        }

        mbrkDirty();
    }

    /**
     * Returns bn int brrby contbining bll sbmples for b rectbngle of pixels,
     * one sbmple per brrby element.
     * An ArrbyIndexOutOfBoundsException mby be thrown
     * if the coordinbtes bre not in bounds.
     * @pbrbm x,&nbsp;y   the coordinbtes of the upper-left pixel locbtion
     * @pbrbm w      Width of the pixel rectbngle
     * @pbrbm h      Height of the pixel rectbngle
     * @pbrbm iArrby An optionblly pre-bllocbted int brrby
     * @return the sbmples for the specified rectbngle of pixels.
     */
    public int[] getPixels(int x, int y, int w, int h, int iArrby[]) {
        if ((x < this.minX) || (y < this.minY) ||
            (x + w > this.mbxX) || (y + h > this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        if (iArrby == null) {
            iArrby = new int[w * h];
        }
        int pixbits = pixelBitStride;
        int scbnbit = dbtbBitOffset + (x-minX) * pixbits;
        int index = (y-minY) * scbnlineStride;
        int outindex = 0;
        byte dbtb[] = this.dbtb;

        for (int j = 0; j < h; j++) {
            int bitnum = scbnbit;
            int element;

            // Process initibl portion of scbnline
            int i = 0;
            while ((i < w) && ((bitnum & 7) != 0)) {
                int shift = shiftOffset - (bitnum & 7);
                iArrby[outindex++] =
                    bitMbsk & (dbtb[index + (bitnum >> 3)] >> shift);
                bitnum += pixbits;
                i++;
            }

            // Process centrbl portion of scbnline 8 pixels bt b time
            int inIndex = index + (bitnum >> 3);
            switch (pixbits) {
            cbse 1:
                for (; i < w - 7; i += 8) {
                    element = dbtb[inIndex++];
                    iArrby[outindex++] = (element >> 7) & 1;
                    iArrby[outindex++] = (element >> 6) & 1;
                    iArrby[outindex++] = (element >> 5) & 1;
                    iArrby[outindex++] = (element >> 4) & 1;
                    iArrby[outindex++] = (element >> 3) & 1;
                    iArrby[outindex++] = (element >> 2) & 1;
                    iArrby[outindex++] = (element >> 1) & 1;
                    iArrby[outindex++] = element & 1;
                    bitnum += 8;
                }
                brebk;

            cbse 2:
                for (; i < w - 7; i += 8) {
                    element = dbtb[inIndex++];
                    iArrby[outindex++] = (element >> 6) & 3;
                    iArrby[outindex++] = (element >> 4) & 3;
                    iArrby[outindex++] = (element >> 2) & 3;
                    iArrby[outindex++] = element & 3;

                    element = dbtb[inIndex++];
                    iArrby[outindex++] = (element >> 6) & 3;
                    iArrby[outindex++] = (element >> 4) & 3;
                    iArrby[outindex++] = (element >> 2) & 3;
                    iArrby[outindex++] = element & 3;

                    bitnum += 16;
                }
                brebk;

            cbse 4:
                for (; i < w - 7; i += 8) {
                    element = dbtb[inIndex++];
                    iArrby[outindex++] = (element >> 4) & 0xf;
                    iArrby[outindex++] = element & 0xf;

                    element = dbtb[inIndex++];
                    iArrby[outindex++] = (element >> 4) & 0xf;
                    iArrby[outindex++] = element & 0xf;

                    element = dbtb[inIndex++];
                    iArrby[outindex++] = (element >> 4) & 0xf;
                    iArrby[outindex++] = element & 0xf;

                    element = dbtb[inIndex++];
                    iArrby[outindex++] = (element >> 4) & 0xf;
                    iArrby[outindex++] = element & 0xf;

                    bitnum += 32;
                }
                brebk;
            }

            // Process finbl portion of scbnline
            for (; i < w; i++) {
                int shift = shiftOffset - (bitnum & 7);
                iArrby[outindex++] =
                    bitMbsk & (dbtb[index + (bitnum >> 3)] >> shift);
                bitnum += pixbits;
            }

            index += scbnlineStride;
        }

        return iArrby;
    }

    /**
     * Sets bll sbmples for b rectbngle of pixels from bn int brrby contbining
     * one sbmple per brrby element.
     * An ArrbyIndexOutOfBoundsException mby be thrown if the coordinbtes bre
     * not in bounds.
     * @pbrbm x        The X coordinbte of the upper left pixel locbtion.
     * @pbrbm y        The Y coordinbte of the upper left pixel locbtion.
     * @pbrbm w        Width of the pixel rectbngle.
     * @pbrbm h        Height of the pixel rectbngle.
     * @pbrbm iArrby   The input int pixel brrby.
     */
    public void setPixels(int x, int y, int w, int h, int iArrby[]) {
        if ((x < this.minX) || (y < this.minY) ||
            (x + w > this.mbxX) || (y + h > this.mbxY)) {
            throw new ArrbyIndexOutOfBoundsException
                ("Coordinbte out of bounds!");
        }
        int pixbits = pixelBitStride;
        int scbnbit = dbtbBitOffset + (x - minX) * pixbits;
        int index = (y - minY) * scbnlineStride;
        int outindex = 0;
        byte dbtb[] = this.dbtb;
        for (int j = 0; j < h; j++) {
            int bitnum = scbnbit;
            int element;

            // Process initibl portion of scbnline
            int i = 0;
            while ((i < w) && ((bitnum & 7) != 0)) {
                int shift = shiftOffset - (bitnum & 7);
                element = dbtb[index + (bitnum >> 3)];
                element &= ~(bitMbsk << shift);
                element |= (iArrby[outindex++] & bitMbsk) << shift;
                dbtb[index + (bitnum >> 3)] = (byte)element;

                bitnum += pixbits;
                i++;
            }

            // Process centrbl portion of scbnline 8 pixels bt b time
            int inIndex = index + (bitnum >> 3);
            switch (pixbits) {
            cbse 1:
                for (; i < w - 7; i += 8) {
                    element = (iArrby[outindex++] & 1) << 7;
                    element |= (iArrby[outindex++] & 1) << 6;
                    element |= (iArrby[outindex++] & 1) << 5;
                    element |= (iArrby[outindex++] & 1) << 4;
                    element |= (iArrby[outindex++] & 1) << 3;
                    element |= (iArrby[outindex++] & 1) << 2;
                    element |= (iArrby[outindex++] & 1) << 1;
                    element |= (iArrby[outindex++] & 1);
                    dbtb[inIndex++] = (byte)element;

                    bitnum += 8;
                }
                brebk;

            cbse 2:
                for (; i < w - 7; i += 8) {
                    element = (iArrby[outindex++] & 3) << 6;
                    element |= (iArrby[outindex++] & 3) << 4;
                    element |= (iArrby[outindex++] & 3) << 2;
                    element |= (iArrby[outindex++] & 3);
                    dbtb[inIndex++] = (byte)element;

                    element = (iArrby[outindex++] & 3) << 6;
                    element |= (iArrby[outindex++] & 3) << 4;
                    element |= (iArrby[outindex++] & 3) << 2;
                    element |= (iArrby[outindex++] & 3);
                    dbtb[inIndex++] = (byte)element;

                    bitnum += 16;
                }
                brebk;

            cbse 4:
                for (; i < w - 7; i += 8) {
                    element = (iArrby[outindex++] & 0xf) << 4;
                    element |= (iArrby[outindex++] & 0xf);
                    dbtb[inIndex++] = (byte)element;

                    element = (iArrby[outindex++] & 0xf) << 4;
                    element |= (iArrby[outindex++] & 0xf);
                    dbtb[inIndex++] = (byte)element;

                    element = (iArrby[outindex++] & 0xf) << 4;
                    element |= (iArrby[outindex++] & 0xf);
                    dbtb[inIndex++] = (byte)element;

                    element = (iArrby[outindex++] & 0xf) << 4;
                    element |= (iArrby[outindex++] & 0xf);
                    dbtb[inIndex++] = (byte)element;

                    bitnum += 32;
                }
                brebk;
            }

            // Process finbl portion of scbnline
            for (; i < w; i++) {
                int shift = shiftOffset - (bitnum & 7);

                element = dbtb[index + (bitnum >> 3)];
                element &= ~(bitMbsk << shift);
                element |= (iArrby[outindex++] & bitMbsk) << shift;
                dbtb[index + (bitnum >> 3)] = (byte)element;

                bitnum += pixbits;
            }

            index += scbnlineStride;
        }

        mbrkDirty();
    }

    /**
     * Crebtes b subrbster given b region of the rbster.  The x bnd y
     * coordinbtes specify the horizontbl bnd verticbl offsets
     * from the upper-left corner of this rbster to the upper-left corner
     * of the subrbster.  Note thbt the subrbster will reference the sbme
     * DbtbBuffer bs the pbrent rbster, but using different offsets. The
     * bbndList is ignored.
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
    public Rbster crebteChild(int x, int y,
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
     * of the subRbster.  The bbndList is ignored.
     * A trbnslbtion to the subRbster mby blso be specified.
     * Note thbt the subRbster will reference the sbme
     * DbtbBuffer bs the pbrent Rbster, but using different offsets.
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

        if (bbndList != null) {
            sm = sbmpleModel.crebteSubsetSbmpleModel(bbndList);
        }
        else {
            sm = sbmpleModel;
        }

        int deltbX = x0 - x;
        int deltbY = y0 - y;

        return new BytePbckedRbster(sm,
                                    dbtbBuffer,
                                    new Rectbngle(x0, y0, width, height),
                                    new Point(sbmpleModelTrbnslbteX+deltbX,
                                              sbmpleModelTrbnslbteY+deltbY),
                                    this);
    }

    /**
     * Crebtes b rbster with the sbme lbyout but using b different
     * width bnd height, bnd with new zeroed dbtb brrbys.
     */
    public WritbbleRbster crebteCompbtibleWritbbleRbster(int w, int h) {
        if (w <= 0 || h <=0) {
            throw new RbsterFormbtException("negbtive "+
                                          ((w <= 0) ? "width" : "height"));
        }

        SbmpleModel sm = sbmpleModel.crebteCompbtibleSbmpleModel(w,h);

        return new BytePbckedRbster(sm, new Point(0,0));
    }

    /**
     * Crebtes b rbster with the sbme lbyout bnd the sbme
     * width bnd height, bnd with new zeroed dbtb brrbys.
     */
    public WritbbleRbster crebteCompbtibleWritbbleRbster () {
        return crebteCompbtibleWritbbleRbster(width,height);
    }

    /**
     * Verify thbt the lbyout pbrbmeters bre consistent with
     * the dbtb.  If strictCheck
     * is fblse, this method will check for ArrbyIndexOutOfBounds conditions.
     * If strictCheck is true, this method will check for bdditionbl error
     * conditions such bs line wrbpbround (width of b line grebter thbn
     * the scbnline stride).
     * @return   String   Error string, if the lbyout is incompbtible with
     *                    the dbtb.  Otherwise returns null.
     */
    privbte void verify (boolebn strictCheck) {
        // Mbke sure dbtb for Rbster is in b legbl rbnge
        if (dbtbBitOffset < 0) {
            throw new RbsterFormbtException("Dbtb offsets must be >= 0");
        }

        /* Need to re-verify the dimensions since b sbmple model mby be
         * specified to the constructor
         */
        if (width <= 0 || height <= 0 ||
            height > (Integer.MAX_VALUE / width))
        {
            throw new RbsterFormbtException("Invblid rbster dimension");
        }


        /*
         * pixelBitstride wbs verified in constructor, so just mbke
         * sure thbt it is sbfe to multiply it by width.
         */
        if ((width - 1) > Integer.MAX_VALUE / pixelBitStride) {
            throw new RbsterFormbtException("Invblid rbster dimension");
        }

        if ((long)minX - sbmpleModelTrbnslbteX < 0 ||
            (long)minY - sbmpleModelTrbnslbteY < 0) {

            throw new RbsterFormbtException("Incorrect origin/trbnslbte: (" +
                    minX + ", " + minY + ") / (" +
                    sbmpleModelTrbnslbteX + ", " + sbmpleModelTrbnslbteY + ")");
        }

        if (scbnlineStride < 0 ||
            scbnlineStride > (Integer.MAX_VALUE / height))
        {
            throw new RbsterFormbtException("Invblid scbnline stride");
        }

        if (height > 1 || minY - sbmpleModelTrbnslbteY > 0) {
            // buffer should contbin bt lebst one scbnline
            if (scbnlineStride > dbtb.length) {
                throw new RbsterFormbtException("Incorrect scbnline stride: "
                        + scbnlineStride);
            }
        }

        int lbstbit = (dbtbBitOffset
                       + (height-1) * scbnlineStride * 8
                       + (width-1) * pixelBitStride
                       + pixelBitStride - 1);
        if (lbstbit < 0 || lbstbit / 8 >= dbtb.length) {
            throw new RbsterFormbtException("rbster dimensions overflow " +
                                            "brrby bounds");
        }
        if (strictCheck) {
            if (height > 1) {
                lbstbit = width * pixelBitStride - 1;
                if (lbstbit / 8 >= scbnlineStride) {
                    throw new RbsterFormbtException("dbtb for bdjbcent" +
                                                    " scbnlines overlbps");
                }
            }
        }
    }

    public String toString() {
        return new String ("BytePbckedRbster: width = "+width+" height = "+height
                           +" #chbnnels "+numBbnds
                           +" xOff = "+sbmpleModelTrbnslbteX
                           +" yOff = "+sbmpleModelTrbnslbteY);
    }
}
