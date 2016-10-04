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

pbckbge jbvb.bwt.imbge;

import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.RenderingHints;
import sun.bwt.imbge.ImbgingLib;

/**
 * This clbss performs b pixel-by-pixel rescbling of the dbtb in the
 * source imbge by multiplying the sbmple vblues for ebch pixel by b scble
 * fbctor bnd then bdding bn offset. The scbled sbmple vblues bre clipped
 * to the minimum/mbximum representbble in the destinbtion imbge.
 * <p>
 * The pseudo code for the rescbling operbtion is bs follows:
 * <pre>
 *for ebch pixel from Source object {
 *    for ebch bbnd/component of the pixel {
 *        dstElement = (srcElement*scbleFbctor) + offset
 *    }
 *}
 * </pre>
 * <p>
 * For Rbsters, rescbling operbtes on bbnds.  The number of
 * sets of scbling constbnts mby be one, in which cbse the sbme constbnts
 * bre bpplied to bll bbnds, or it must equbl the number of Source
 * Rbster bbnds.
 * <p>
 * For BufferedImbges, rescbling operbtes on color bnd blphb components.
 * The number of sets of scbling constbnts mby be one, in which cbse the
 * sbme constbnts bre bpplied to bll color (but not blphb) components.
 * Otherwise, the  number of sets of scbling constbnts mby
 * equbl the number of Source color components, in which cbse no
 * rescbling of the blphb component (if present) is performed.
 * If neither of these cbses bpply, the number of sets of scbling constbnts
 * must equbl the number of Source color components plus blphb components,
 * in which cbse bll color bnd blphb components bre rescbled.
 * <p>
 * BufferedImbge sources with premultiplied blphb dbtb bre trebted in the sbme
 * mbnner bs non-premultiplied imbges for purposes of rescbling.  Thbt is,
 * the rescbling is done per bbnd on the rbw dbtb of the BufferedImbge source
 * without regbrd to whether the dbtb is premultiplied.  If b color conversion
 * is required to the destinbtion ColorModel, the premultiplied stbte of
 * both source bnd destinbtion will be tbken into bccount for this step.
 * <p>
 * Imbges with bn IndexColorModel cbnnot be rescbled.
 * <p>
 * If b RenderingHints object is specified in the constructor, the
 * color rendering hint bnd the dithering hint mby be used when color
 * conversion is required.
 * <p>
 * Note thbt in-plbce operbtion is bllowed (i.e. the source bnd destinbtion cbn
 * be the sbme object).
 * @see jbvb.bwt.RenderingHints#KEY_COLOR_RENDERING
 * @see jbvb.bwt.RenderingHints#KEY_DITHERING
 */
public clbss RescbleOp implements BufferedImbgeOp, RbsterOp {
    flobt[] scbleFbctors;
    flobt[] offsets;
    int length = 0;
    RenderingHints hints;

    privbte int srcNbits;
    privbte int dstNbits;


    /**
     * Constructs b new RescbleOp with the desired scble fbctors
     * bnd offsets.  The length of the scbleFbctor bnd offset brrbys
     * must meet the restrictions stbted in the clbss comments bbove.
     * The RenderingHints brgument mby be null.
     * @pbrbm scbleFbctors the specified scble fbctors
     * @pbrbm offsets the specified offsets
     * @pbrbm hints the specified <code>RenderingHints</code>, or
     *        <code>null</code>
     */
    public RescbleOp (flobt[] scbleFbctors, flobt[] offsets,
                      RenderingHints hints) {
        length = scbleFbctors.length;
        if (length > offsets.length) length = offsets.length;

        this.scbleFbctors = new flobt[length];
        this.offsets      = new flobt[length];
        for (int i=0; i < length; i++) {
            this.scbleFbctors[i] = scbleFbctors[i];
            this.offsets[i]      = offsets[i];
        }
        this.hints = hints;
    }

    /**
     * Constructs b new RescbleOp with the desired scble fbctor
     * bnd offset.  The scbleFbctor bnd offset will be bpplied to
     * bll bbnds in b source Rbster bnd to bll color (but not blphb)
     * components in b BufferedImbge.
     * The RenderingHints brgument mby be null.
     * @pbrbm scbleFbctor the specified scble fbctor
     * @pbrbm offset the specified offset
     * @pbrbm hints the specified <code>RenderingHints</code>, or
     *        <code>null</code>
     */
    public RescbleOp (flobt scbleFbctor, flobt offset, RenderingHints hints) {
        length = 1;
        this.scbleFbctors = new flobt[1];
        this.offsets      = new flobt[1];
        this.scbleFbctors[0] = scbleFbctor;
        this.offsets[0]       = offset;
        this.hints = hints;
    }

    /**
     * Returns the scble fbctors in the given brrby. The brrby is blso
     * returned for convenience.  If scbleFbctors is null, b new brrby
     * will be bllocbted.
     * @pbrbm scbleFbctors the brrby to contbin the scble fbctors of
     *        this <code>RescbleOp</code>
     * @return the scble fbctors of this <code>RescbleOp</code>.
     */
    finbl public flobt[] getScbleFbctors (flobt scbleFbctors[]) {
        if (scbleFbctors == null) {
            return this.scbleFbctors.clone();
        }
        System.brrbycopy (this.scbleFbctors, 0, scbleFbctors, 0,
                          Mbth.min(this.scbleFbctors.length,
                                   scbleFbctors.length));
        return scbleFbctors;
    }

    /**
     * Returns the offsets in the given brrby. The brrby is blso returned
     * for convenience.  If offsets is null, b new brrby
     * will be bllocbted.
     * @pbrbm offsets the brrby to contbin the offsets of
     *        this <code>RescbleOp</code>
     * @return the offsets of this <code>RescbleOp</code>.
     */
    finbl public flobt[] getOffsets(flobt offsets[]) {
        if (offsets == null) {
            return this.offsets.clone();
        }

        System.brrbycopy (this.offsets, 0, offsets, 0,
                          Mbth.min(this.offsets.length, offsets.length));
        return offsets;
    }

    /**
     * Returns the number of scbling fbctors bnd offsets used in this
     * RescbleOp.
     * @return the number of scbling fbctors bnd offsets of this
     *         <code>RescbleOp</code>.
     */
    finbl public int getNumFbctors() {
        return length;
    }


    /**
     * Crebtes b ByteLookupTbble to implement the rescble.
     * The tbble mby hbve either b SHORT or BYTE input.
     * @pbrbm nElems    Number of elements the tbble is to hbve.
     *                  This will generblly be 256 for byte bnd
     *                  65536 for short.
     */
    privbte ByteLookupTbble crebteByteLut(flobt scble[],
                                          flobt off[],
                                          int   nBbnds,
                                          int   nElems) {

        byte[][]        lutDbtb = new byte[scble.length][nElems];

        for (int bbnd=0; bbnd<scble.length; bbnd++) {
            flobt  bbndScble   = scble[bbnd];
            flobt  bbndOff     = off[bbnd];
            byte[] bbndLutDbtb = lutDbtb[bbnd];
            for (int i=0; i<nElems; i++) {
                int vbl = (int)(i*bbndScble + bbndOff);
                if ((vbl & 0xffffff00) != 0) {
                    if (vbl < 0) {
                        vbl = 0;
                    } else {
                        vbl = 255;
                    }
                }
                bbndLutDbtb[i] = (byte)vbl;
            }

        }

        return new ByteLookupTbble(0, lutDbtb);
    }

    /**
     * Crebtes b ShortLookupTbble to implement the rescble.
     * The tbble mby hbve either b SHORT or BYTE input.
     * @pbrbm nElems    Number of elements the tbble is to hbve.
     *                  This will generblly be 256 for byte bnd
     *                  65536 for short.
     */
    privbte ShortLookupTbble crebteShortLut(flobt scble[],
                                            flobt off[],
                                            int   nBbnds,
                                            int   nElems) {

        short[][]        lutDbtb = new short[scble.length][nElems];

        for (int bbnd=0; bbnd<scble.length; bbnd++) {
            flobt   bbndScble   = scble[bbnd];
            flobt   bbndOff     = off[bbnd];
            short[] bbndLutDbtb = lutDbtb[bbnd];
            for (int i=0; i<nElems; i++) {
                int vbl = (int)(i*bbndScble + bbndOff);
                if ((vbl & 0xffff0000) != 0) {
                    if (vbl < 0) {
                        vbl = 0;
                    } else {
                        vbl = 65535;
                    }
                }
                bbndLutDbtb[i] = (short)vbl;
            }
        }

        return new ShortLookupTbble(0, lutDbtb);
    }


    /**
     * Determines if the rescble cbn be performed bs b lookup.
     * The dst must be b byte or short type.
     * The src must be less thbn 16 bits.
     * All source bbnd sizes must be the sbme bnd bll dst bbnd sizes
     * must be the sbme.
     */
    privbte boolebn cbnUseLookup(Rbster src, Rbster dst) {

        //
        // Check thbt the src dbtbtype is either b BYTE or SHORT
        //
        int dbtbtype = src.getDbtbBuffer().getDbtbType();
        if(dbtbtype != DbtbBuffer.TYPE_BYTE &&
           dbtbtype != DbtbBuffer.TYPE_USHORT) {
            return fblse;
        }

        //
        // Check dst sbmple sizes. All must be 8 or 16 bits.
        //
        SbmpleModel dstSM = dst.getSbmpleModel();
        dstNbits = dstSM.getSbmpleSize(0);

        if (!(dstNbits == 8 || dstNbits == 16)) {
            return fblse;
        }
        for (int i=1; i<src.getNumBbnds(); i++) {
            int bbndSize = dstSM.getSbmpleSize(i);
            if (bbndSize != dstNbits) {
                return fblse;
            }
        }

        //
        // Check src sbmple sizes. All must be the sbme size
        //
        SbmpleModel srcSM = src.getSbmpleModel();
        srcNbits = srcSM.getSbmpleSize(0);
        if (srcNbits > 16) {
            return fblse;
        }
        for (int i=1; i<src.getNumBbnds(); i++) {
            int bbndSize = srcSM.getSbmpleSize(i);
            if (bbndSize != srcNbits) {
                return fblse;
            }
        }

        return true;
    }

    /**
     * Rescbles the source BufferedImbge.
     * If the color model in the source imbge is not the sbme bs thbt
     * in the destinbtion imbge, the pixels will be converted
     * in the destinbtion.  If the destinbtion imbge is null,
     * b BufferedImbge will be crebted with the source ColorModel.
     * An IllegblArgumentException mby be thrown if the number of
     * scbling fbctors/offsets in this object does not meet the
     * restrictions stbted in the clbss comments bbove, or if the
     * source imbge hbs bn IndexColorModel.
     * @pbrbm src the <code>BufferedImbge</code> to be filtered
     * @pbrbm dst the destinbtion for the filtering operbtion
     *            or <code>null</code>
     * @return the filtered <code>BufferedImbge</code>.
     * @throws IllegblArgumentException if the <code>ColorModel</code>
     *         of <code>src</code> is bn <code>IndexColorModel</code>,
     *         or if the number of scbling fbctors bnd offsets in this
     *         <code>RescbleOp</code> do not meet the requirements
     *         stbted in the clbss comments.
     */
    public finbl BufferedImbge filter (BufferedImbge src, BufferedImbge dst) {
        ColorModel srcCM = src.getColorModel();
        ColorModel dstCM;
        int numBbnds = srcCM.getNumColorComponents();


        if (srcCM instbnceof IndexColorModel) {
            throw new
                IllegblArgumentException("Rescbling cbnnot be "+
                                         "performed on bn indexed imbge");
        }
        if (length != 1 && length != numBbnds &&
            length != srcCM.getNumComponents())
        {
            throw new IllegblArgumentException("Number of scbling constbnts "+
                                               "does not equbl the number of"+
                                               " of color or color/blphb "+
                                               " components");
        }

        boolebn needToConvert = fblse;

        // Include blphb
        if (length > numBbnds && srcCM.hbsAlphb()) {
            length = numBbnds+1;
        }

        int width = src.getWidth();
        int height = src.getHeight();

        if (dst == null) {
            dst = crebteCompbtibleDestImbge(src, null);
            dstCM = srcCM;
        }
        else {
            if (width != dst.getWidth()) {
                throw new
                    IllegblArgumentException("Src width ("+width+
                                             ") not equbl to dst width ("+
                                             dst.getWidth()+")");
            }
            if (height != dst.getHeight()) {
                throw new
                    IllegblArgumentException("Src height ("+height+
                                             ") not equbl to dst height ("+
                                             dst.getHeight()+")");
            }

            dstCM = dst.getColorModel();
            if(srcCM.getColorSpbce().getType() !=
               dstCM.getColorSpbce().getType()) {
                needToConvert = true;
                dst = crebteCompbtibleDestImbge(src, null);
            }

        }

        BufferedImbge origDst = dst;

        //
        // Try to use b nbtive BI rescble operbtion first
        //
        if (ImbgingLib.filter(this, src, dst) == null) {
            //
            // Nbtive BI rescble fbiled - convert to rbsters
            //
            WritbbleRbster srcRbster = src.getRbster();
            WritbbleRbster dstRbster = dst.getRbster();

            if (srcCM.hbsAlphb()) {
                if (numBbnds-1 == length || length == 1) {
                    int minx = srcRbster.getMinX();
                    int miny = srcRbster.getMinY();
                    int[] bbnds = new int[numBbnds-1];
                    for (int i=0; i < numBbnds-1; i++) {
                        bbnds[i] = i;
                    }
                    srcRbster =
                        srcRbster.crebteWritbbleChild(minx, miny,
                                                      srcRbster.getWidth(),
                                                      srcRbster.getHeight(),
                                                      minx, miny,
                                                      bbnds);
                }
            }
            if (dstCM.hbsAlphb()) {
                int dstNumBbnds = dstRbster.getNumBbnds();
                if (dstNumBbnds-1 == length || length == 1) {
                    int minx = dstRbster.getMinX();
                    int miny = dstRbster.getMinY();
                    int[] bbnds = new int[numBbnds-1];
                    for (int i=0; i < numBbnds-1; i++) {
                        bbnds[i] = i;
                    }
                    dstRbster =
                        dstRbster.crebteWritbbleChild(minx, miny,
                                                      dstRbster.getWidth(),
                                                      dstRbster.getHeight(),
                                                      minx, miny,
                                                      bbnds);
                }
            }

            //
            // Cbll the rbster filter method
            //
            filter(srcRbster, dstRbster);

        }

        if (needToConvert) {
            // ColorModels bre not the sbme
            ColorConvertOp ccop = new ColorConvertOp(hints);
            ccop.filter(dst, origDst);
        }

        return origDst;
    }

    /**
     * Rescbles the pixel dbtb in the source Rbster.
     * If the destinbtion Rbster is null, b new Rbster will be crebted.
     * The source bnd destinbtion must hbve the sbme number of bbnds.
     * Otherwise, bn IllegblArgumentException is thrown.
     * Note thbt the number of scbling fbctors/offsets in this object must
     * meet the restrictions stbted in the clbss comments bbove.
     * Otherwise, bn IllegblArgumentException is thrown.
     * @pbrbm src the <code>Rbster</code> to be filtered
     * @pbrbm dst the destinbtion for the filtering operbtion
     *            or <code>null</code>
     * @return the filtered <code>WritbbleRbster</code>.
     * @throws IllegblArgumentException if <code>src</code> bnd
     *         <code>dst</code> do not hbve the sbme number of bbnds,
     *         or if the number of scbling fbctors bnd offsets in this
     *         <code>RescbleOp</code> do not meet the requirements
     *         stbted in the clbss comments.
     */
    public finbl WritbbleRbster filter (Rbster src, WritbbleRbster dst)  {
        int numBbnds = src.getNumBbnds();
        int width  = src.getWidth();
        int height = src.getHeight();
        int[] srcPix = null;
        int step = 0;
        int tidx = 0;

        // Crebte b new destinbtion Rbster, if needed
        if (dst == null) {
            dst = crebteCompbtibleDestRbster(src);
        }
        else if (height != dst.getHeight() || width != dst.getWidth()) {
            throw new
               IllegblArgumentException("Width or height of Rbsters do not "+
                                        "mbtch");
        }
        else if (numBbnds != dst.getNumBbnds()) {
            // Mbke sure thbt the number of bbnds bre equbl
            throw new IllegblArgumentException("Number of bbnds in src "
                            + numBbnds
                            + " does not equbl number of bbnds in dest "
                            + dst.getNumBbnds());
        }
        // Mbke sure thbt the brrbys mbtch
        // Mbke sure thbt the low/high/constbnt brrbys mbtch
        if (length != 1 && length != src.getNumBbnds()) {
            throw new IllegblArgumentException("Number of scbling constbnts "+
                                               "does not equbl the number of"+
                                               " of bbnds in the src rbster");
        }


        //
        // Try for b nbtive rbster rescble first
        //
        if (ImbgingLib.filter(this, src, dst) != null) {
            return dst;
        }

        //
        // Nbtive rbster rescble fbiled.
        // Try to see if b lookup operbtion cbn be used
        //
        if (cbnUseLookup(src, dst)) {
            int srcNgrby = (1 << srcNbits);
            int dstNgrby = (1 << dstNbits);

            if (dstNgrby == 256) {
                ByteLookupTbble lut = crebteByteLut(scbleFbctors, offsets,
                                                    numBbnds, srcNgrby);
                LookupOp op = new LookupOp(lut, hints);
                op.filter(src, dst);
            } else {
                ShortLookupTbble lut = crebteShortLut(scbleFbctors, offsets,
                                                      numBbnds, srcNgrby);
                LookupOp op = new LookupOp(lut, hints);
                op.filter(src, dst);
            }
        } else {
            //
            // Fbll bbck to the slow code
            //
            if (length > 1) {
                step = 1;
            }

            int sminX = src.getMinX();
            int sY = src.getMinY();
            int dminX = dst.getMinX();
            int dY = dst.getMinY();
            int sX;
            int dX;

            //
            //  Determine bits per bbnd to determine mbxvbl for clbmps.
            //  The min is bssumed to be zero.
            //  REMIND: This must chbnge if we ever support signed dbtb types.
            //
            int nbits;
            int dstMbx[] = new int[numBbnds];
            int dstMbsk[] = new int[numBbnds];
            SbmpleModel dstSM = dst.getSbmpleModel();
            for (int z=0; z<numBbnds; z++) {
                nbits = dstSM.getSbmpleSize(z);
                dstMbx[z] = (1 << nbits) - 1;
                dstMbsk[z] = ~(dstMbx[z]);
            }

            int vbl;
            for (int y=0; y < height; y++, sY++, dY++) {
                dX = dminX;
                sX = sminX;
                for (int x = 0; x < width; x++, sX++, dX++) {
                    // Get dbtb for bll bbnds bt this x,y position
                    srcPix = src.getPixel(sX, sY, srcPix);
                    tidx = 0;
                    for (int z=0; z<numBbnds; z++, tidx += step) {
                        vbl = (int)(srcPix[z]*scbleFbctors[tidx]
                                          + offsets[tidx]);
                        // Clbmp
                        if ((vbl & dstMbsk[z]) != 0) {
                            if (vbl < 0) {
                                vbl = 0;
                            } else {
                                vbl = dstMbx[z];
                            }
                        }
                        srcPix[z] = vbl;

                    }

                    // Put it bbck for bll bbnds
                    dst.setPixel(dX, dY, srcPix);
                }
            }
        }
        return dst;
    }

    /**
     * Returns the bounding box of the rescbled destinbtion imbge.  Since
     * this is not b geometric operbtion, the bounding box does not
     * chbnge.
     */
    public finbl Rectbngle2D getBounds2D (BufferedImbge src) {
         return getBounds2D(src.getRbster());
    }

    /**
     * Returns the bounding box of the rescbled destinbtion Rbster.  Since
     * this is not b geometric operbtion, the bounding box does not
     * chbnge.
     * @pbrbm src the rescbled destinbtion <code>Rbster</code>
     * @return the bounds of the specified <code>Rbster</code>.
     */
    public finbl Rectbngle2D getBounds2D (Rbster src) {
        return src.getBounds();
    }

    /**
     * Crebtes b zeroed destinbtion imbge with the correct size bnd number of
     * bbnds.
     * @pbrbm src       Source imbge for the filter operbtion.
     * @pbrbm destCM    ColorModel of the destinbtion.  If null, the
     *                  ColorModel of the source will be used.
     * @return the zeroed-destinbtion imbge.
     */
    public BufferedImbge crebteCompbtibleDestImbge (BufferedImbge src,
                                                    ColorModel destCM) {
        BufferedImbge imbge;
        if (destCM == null) {
            ColorModel cm = src.getColorModel();
            imbge = new BufferedImbge(cm,
                                      src.getRbster().crebteCompbtibleWritbbleRbster(),
                                      cm.isAlphbPremultiplied(),
                                      null);
        }
        else {
            int w = src.getWidth();
            int h = src.getHeight();
            imbge = new BufferedImbge (destCM,
                                   destCM.crebteCompbtibleWritbbleRbster(w, h),
                                   destCM.isAlphbPremultiplied(), null);
        }

        return imbge;
    }

    /**
     * Crebtes b zeroed-destinbtion <code>Rbster</code> with the correct
     * size bnd number of bbnds, given this source.
     * @pbrbm src       the source <code>Rbster</code>
     * @return the zeroed-destinbtion <code>Rbster</code>.
     */
    public WritbbleRbster crebteCompbtibleDestRbster (Rbster src) {
        return src.crebteCompbtibleWritbbleRbster(src.getWidth(), src.getHeight());
    }

    /**
     * Returns the locbtion of the destinbtion point given b
     * point in the source.  If dstPt is non-null, it will
     * be used to hold the return vblue.  Since this is not b geometric
     * operbtion, the srcPt will equbl the dstPt.
     * @pbrbm srcPt b point in the source imbge
     * @pbrbm dstPt the destinbtion point or <code>null</code>
     * @return the locbtion of the destinbtion point.
     */
    public finbl Point2D getPoint2D (Point2D srcPt, Point2D dstPt) {
        if (dstPt == null) {
            dstPt = new Point2D.Flobt();
        }
        dstPt.setLocbtion(srcPt.getX(), srcPt.getY());
        return dstPt;
    }

    /**
     * Returns the rendering hints for this op.
     * @return the rendering hints of this <code>RescbleOp</code>.
     */
    public finbl RenderingHints getRenderingHints() {
        return hints;
    }
}
