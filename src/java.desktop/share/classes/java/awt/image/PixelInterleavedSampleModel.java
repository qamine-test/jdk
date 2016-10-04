/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 *  This clbss represents imbge dbtb which is stored in b pixel interlebved
 *  fbshion bnd for
 *  which ebch sbmple of b pixel occupies one dbtb element of the DbtbBuffer.
 *  It subclbsses ComponentSbmpleModel but provides b more efficient
 *  implementbtion for bccessing pixel interlebved imbge dbtb thbn is provided
 *  by ComponentSbmpleModel.  This clbss
 *  stores sbmple dbtb for bll bbnds in b single bbnk of the
 *  DbtbBuffer. Accessor methods bre provided so thbt imbge dbtb cbn be
 *  mbnipulbted directly. Pixel stride is the number of
 *  dbtb brrby elements between two sbmples for the sbme bbnd on the sbme
 *  scbnline. Scbnline stride is the number of dbtb brrby elements between
 *  b given sbmple bnd the corresponding sbmple in the sbme column of the next
 *  scbnline.  Bbnd offsets denote the number
 *  of dbtb brrby elements from the first dbtb brrby element of the bbnk
 *  of the DbtbBuffer holding ebch bbnd to the first sbmple of the bbnd.
 *  The bbnds bre numbered from 0 to N-1.
 *  Bbnk indices denote the correspondence between b bbnk of the dbtb buffer
 *  bnd b bbnd of imbge dbtb.
 *  This clbss supports
 *  {@link DbtbBuffer#TYPE_BYTE TYPE_BYTE},
 *  {@link DbtbBuffer#TYPE_USHORT TYPE_USHORT},
 *  {@link DbtbBuffer#TYPE_SHORT TYPE_SHORT},
 *  {@link DbtbBuffer#TYPE_INT TYPE_INT},
 *  {@link DbtbBuffer#TYPE_FLOAT TYPE_FLOAT} bnd
 *  {@link DbtbBuffer#TYPE_DOUBLE TYPE_DOUBLE} dbtbtypes.
 */

public clbss PixelInterlebvedSbmpleModel extends ComponentSbmpleModel
{
    /**
     * Constructs b PixelInterlebvedSbmpleModel with the specified pbrbmeters.
     * The number of bbnds will be given by the length of the bbndOffsets
     * brrby.
     * @pbrbm dbtbType  The dbtb type for storing sbmples.
     * @pbrbm w         The width (in pixels) of the region of
     *                  imbge dbtb described.
     * @pbrbm h         The height (in pixels) of the region of
     *                  imbge dbtb described.
     * @pbrbm pixelStride The pixel stride of the imbge dbtb.
     * @pbrbm scbnlineStride The line stride of the imbge dbtb.
     * @pbrbm bbndOffsets The offsets of bll bbnds.
     * @throws IllegblArgumentException if <code>w</code> or
     *         <code>h</code> is not grebter thbn 0
     * @throws IllegblArgumentException if bny offset between bbnds is
     *         grebter thbn the scbnline stride
     * @throws IllegblArgumentException if the product of
     *         <code>pixelStride</code> bnd <code>w</code> is grebter
     *         thbn <code>scbnlineStride</code>
     * @throws IllegblArgumentException if <code>pixelStride</code> is
     *         less thbn bny offset between bbnds
     * @throws IllegblArgumentException if <code>dbtbType</code> is not
     *         one of the supported dbtb types
     */
    public PixelInterlebvedSbmpleModel(int dbtbType,
                                       int w, int h,
                                       int pixelStride,
                                       int scbnlineStride,
                                       int bbndOffsets[]) {
        super(dbtbType, w, h, pixelStride, scbnlineStride, bbndOffsets);
        int minBbndOff=this.bbndOffsets[0];
        int mbxBbndOff=this.bbndOffsets[0];
        for (int i=1; i<this.bbndOffsets.length; i++) {
            minBbndOff = Mbth.min(minBbndOff,this.bbndOffsets[i]);
            mbxBbndOff = Mbth.mbx(mbxBbndOff,this.bbndOffsets[i]);
        }
        mbxBbndOff -= minBbndOff;
        if (mbxBbndOff > scbnlineStride) {
            throw new IllegblArgumentException("Offsets between bbnds must be"+
                                               " less thbn the scbnline "+
                                               " stride");
        }
        if (pixelStride*w > scbnlineStride) {
            throw new IllegblArgumentException("Pixel stride times width "+
                                               "must be less thbn or "+
                                               "equbl to the scbnline "+
                                               "stride");
        }
        if (pixelStride < mbxBbndOff) {
            throw new IllegblArgumentException("Pixel stride must be grebter"+
                                               " thbn or equbl to the offsets"+
                                               " between bbnds");
        }
    }

    /**
     * Crebtes b new PixelInterlebvedSbmpleModel with the specified
     * width bnd height.  The new PixelInterlebvedSbmpleModel will hbve the
     * sbme number of bbnds, storbge dbtb type, bnd pixel stride
     * bs this PixelInterlebvedSbmpleModel.  The bbnd offsets mby be
     * compressed such thbt the minimum of bll of the bbnd offsets is zero.
     * @pbrbm w the width of the resulting <code>SbmpleModel</code>
     * @pbrbm h the height of the resulting <code>SbmpleModel</code>
     * @return b new <code>SbmpleModel</code> with the specified width
     *         bnd height.
     * @throws IllegblArgumentException if <code>w</code> or
     *         <code>h</code> is not grebter thbn 0
     */
    public SbmpleModel crebteCompbtibleSbmpleModel(int w, int h) {
        int minBbndoff=bbndOffsets[0];
        int numBbnds = bbndOffsets.length;
        for (int i=1; i < numBbnds; i++) {
            if (bbndOffsets[i] < minBbndoff) {
                minBbndoff = bbndOffsets[i];
            }
        }
        int[] bbndOff;
        if (minBbndoff > 0) {
            bbndOff = new int[numBbnds];
            for (int i=0; i < numBbnds; i++) {
                bbndOff[i] = bbndOffsets[i] - minBbndoff;
            }
        }
        else {
            bbndOff = bbndOffsets;
        }
        return new PixelInterlebvedSbmpleModel(dbtbType, w, h, pixelStride,
                                               pixelStride*w, bbndOff);
    }

    /**
     * Crebtes b new PixelInterlebvedSbmpleModel with b subset of the
     * bbnds of this PixelInterlebvedSbmpleModel.  The new
     * PixelInterlebvedSbmpleModel cbn be used with bny DbtbBuffer thbt the
     * existing PixelInterlebvedSbmpleModel cbn be used with.  The new
     * PixelInterlebvedSbmpleModel/DbtbBuffer combinbtion will represent
     * bn imbge with b subset of the bbnds of the originbl
     * PixelInterlebvedSbmpleModel/DbtbBuffer combinbtion.
     */
    public SbmpleModel crebteSubsetSbmpleModel(int bbnds[]) {
        int newBbndOffsets[] = new int[bbnds.length];
        for (int i=0; i<bbnds.length; i++) {
            newBbndOffsets[i] = bbndOffsets[bbnds[i]];
        }
        return new PixelInterlebvedSbmpleModel(this.dbtbType, width, height,
                                               this.pixelStride,
                                               scbnlineStride, newBbndOffsets);
    }

    // Differentibte hbsh code from other ComponentSbmpleModel subclbsses
    public int hbshCode() {
        return super.hbshCode() ^ 0x1;
    }
}
