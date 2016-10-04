/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.wbmp;

import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DbtbBufferByte;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.MultiPixelPbckedSbmpleModel;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.RenderedImbge;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.bwt.imbge.WritbbleRbster;

import jbvb.io.IOException;

import jbvbx.imbgeio.IIOImbge;
import jbvbx.imbgeio.IIOException;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.ImbgeWritePbrbm;
import jbvbx.imbgeio.ImbgeWriter;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtb;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbtImpl;
import jbvbx.imbgeio.metbdbtb.IIOInvblidTreeException;
import jbvbx.imbgeio.spi.ImbgeWriterSpi;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;

import com.sun.imbgeio.plugins.common.I18N;

/**
 * The Jbvb Imbge IO plugin writer for encoding b binbry RenderedImbge into
 * b WBMP formbt.
 *
 * The encoding process mby clip, subsbmple using the pbrbmeters
 * specified in the <code>ImbgeWritePbrbm</code>.
 *
 * @see com.sun.medib.imbgeio.plugins.WBMPImbgeWritePbrbm
 */
public clbss WBMPImbgeWriter extends ImbgeWriter {
    /** The output strebm to write into */
    privbte ImbgeOutputStrebm strebm = null;

    // Get the number of bits required to represent bn int.
    privbte stbtic int getNumBits(int intVblue) {
        int numBits = 32;
        int mbsk = 0x80000000;
        while(mbsk != 0 && (intVblue & mbsk) == 0) {
            numBits--;
            mbsk >>>= 1;
        }
        return numBits;
    }

    // Convert bn int vblue to WBMP multi-byte formbt.
    privbte stbtic byte[] intToMultiByte(int intVblue) {
        int numBitsLeft = getNumBits(intVblue);
        byte[] multiBytes = new byte[(numBitsLeft + 6)/7];

        int mbxIndex = multiBytes.length - 1;
        for(int b = 0; b <= mbxIndex; b++) {
            multiBytes[b] = (byte)((intVblue >>> ((mbxIndex - b)*7))&0x7f);
            if(b != mbxIndex) {
                multiBytes[b] |= (byte)0x80;
            }
        }

        return multiBytes;
    }

    /** Constructs <code>WBMPImbgeWriter</code> bbsed on the provided
     *  <code>ImbgeWriterSpi</code>.
     */
    public WBMPImbgeWriter(ImbgeWriterSpi originbtor) {
        super(originbtor);
    }

    public void setOutput(Object output) {
        super.setOutput(output); // vblidbtes output
        if (output != null) {
            if (!(output instbnceof ImbgeOutputStrebm))
                throw new IllegblArgumentException(I18N.getString("WBMPImbgeWriter"));
            this.strebm = (ImbgeOutputStrebm)output;
        } else
            this.strebm = null;
    }

    public IIOMetbdbtb getDefbultStrebmMetbdbtb(ImbgeWritePbrbm pbrbm) {
        return null;
    }

    public IIOMetbdbtb getDefbultImbgeMetbdbtb(ImbgeTypeSpecifier imbgeType,
                                               ImbgeWritePbrbm pbrbm) {
        WBMPMetbdbtb metb = new WBMPMetbdbtb();
        metb.wbmpType = 0; // defbult wbmp level
        return metb;
    }

    public IIOMetbdbtb convertStrebmMetbdbtb(IIOMetbdbtb inDbtb,
                                             ImbgeWritePbrbm pbrbm) {
        return null;
    }

    public IIOMetbdbtb convertImbgeMetbdbtb(IIOMetbdbtb metbdbtb,
                                            ImbgeTypeSpecifier type,
                                            ImbgeWritePbrbm pbrbm) {
        return null;
    }

    public boolebn cbnWriteRbsters() {
        return true;
    }

    public void write(IIOMetbdbtb strebmMetbdbtb,
                      IIOImbge imbge,
                      ImbgeWritePbrbm pbrbm) throws IOException {

        if (strebm == null) {
            throw new IllegblStbteException(I18N.getString("WBMPImbgeWriter3"));
        }

        if (imbge == null) {
            throw new IllegblArgumentException(I18N.getString("WBMPImbgeWriter4"));
        }

        clebrAbortRequest();
        processImbgeStbrted(0);
        if (pbrbm == null)
            pbrbm = getDefbultWritePbrbm();

        RenderedImbge input = null;
        Rbster inputRbster = null;
        boolebn writeRbster = imbge.hbsRbster();
        Rectbngle sourceRegion = pbrbm.getSourceRegion();
        SbmpleModel sbmpleModel = null;

        if (writeRbster) {
            inputRbster = imbge.getRbster();
            sbmpleModel = inputRbster.getSbmpleModel();
        } else {
            input = imbge.getRenderedImbge();
            sbmpleModel = input.getSbmpleModel();

            inputRbster = input.getDbtb();
        }

        checkSbmpleModel(sbmpleModel);
        if (sourceRegion == null)
            sourceRegion = inputRbster.getBounds();
        else
            sourceRegion = sourceRegion.intersection(inputRbster.getBounds());

        if (sourceRegion.isEmpty())
            throw new RuntimeException(I18N.getString("WBMPImbgeWriter1"));

        int scbleX = pbrbm.getSourceXSubsbmpling();
        int scbleY = pbrbm.getSourceYSubsbmpling();
        int xOffset = pbrbm.getSubsbmplingXOffset();
        int yOffset = pbrbm.getSubsbmplingYOffset();

        sourceRegion.trbnslbte(xOffset, yOffset);
        sourceRegion.width -= xOffset;
        sourceRegion.height -= yOffset;

        int minX = sourceRegion.x / scbleX;
        int minY = sourceRegion.y / scbleY;
        int w = (sourceRegion.width + scbleX - 1) / scbleX;
        int h = (sourceRegion.height + scbleY - 1) / scbleY;

        Rectbngle destinbtionRegion = new Rectbngle(minX, minY, w, h);
        sbmpleModel = sbmpleModel.crebteCompbtibleSbmpleModel(w, h);

        SbmpleModel destSM= sbmpleModel;

        // If the dbtb bre not formbtted nominblly then reformbt.
        if(sbmpleModel.getDbtbType() != DbtbBuffer.TYPE_BYTE ||
           !(sbmpleModel instbnceof MultiPixelPbckedSbmpleModel) ||
           ((MultiPixelPbckedSbmpleModel)sbmpleModel).getDbtbBitOffset() != 0) {
           destSM =
                new MultiPixelPbckedSbmpleModel(DbtbBuffer.TYPE_BYTE,
                                                w, h, 1,
                                                w + 7 >> 3, 0);
        }

        if (!destinbtionRegion.equbls(sourceRegion)) {
            if (scbleX == 1 && scbleY == 1)
                inputRbster = inputRbster.crebteChild(inputRbster.getMinX(),
                                                      inputRbster.getMinY(),
                                                      w, h, minX, minY, null);
            else {
                WritbbleRbster rbs = Rbster.crebteWritbbleRbster(destSM,
                                                                 new Point(minX, minY));

                byte[] dbtb = ((DbtbBufferByte)rbs.getDbtbBuffer()).getDbtb();

                for(int j = minY, y = sourceRegion.y, k = 0;
                    j < minY + h; j++, y += scbleY) {

                    for (int i = 0, x = sourceRegion.x;
                        i <w; i++, x +=scbleX) {
                        int v = inputRbster.getSbmple(x, y, 0);
                        dbtb[k + (i >> 3)] |= v << (7 - (i & 7));
                    }
                    k += w + 7 >> 3;
                }
                inputRbster = rbs;
            }
        }

        // If the dbtb bre not formbtted nominblly then reformbt.
        if(!destSM.equbls(inputRbster.getSbmpleModel())) {
            WritbbleRbster rbster =
                Rbster.crebteWritbbleRbster(destSM,
                                            new Point(inputRbster.getMinX(),
                                                      inputRbster.getMinY()));
            rbster.setRect(inputRbster);
            inputRbster = rbster;
        }

        // Check whether the imbge is white-is-zero.
        boolebn isWhiteZero = fblse;
        if(!writeRbster && input.getColorModel() instbnceof IndexColorModel) {
            IndexColorModel icm = (IndexColorModel)input.getColorModel();
            isWhiteZero = icm.getRed(0) > icm.getRed(1);
        }

        // Get the line stride, bytes per row, bnd dbtb brrby.
        int lineStride =
            ((MultiPixelPbckedSbmpleModel)destSM).getScbnlineStride();
        int bytesPerRow = (w + 7)/8;
        byte[] bdbtb = ((DbtbBufferByte)inputRbster.getDbtbBuffer()).getDbtb();

        // Write WBMP hebder.
        strebm.write(0); // TypeField
        strebm.write(0); // FixHebderField
        strebm.write(intToMultiByte(w)); // width
        strebm.write(intToMultiByte(h)); // height

        // Write the dbtb.
        if(!isWhiteZero && lineStride == bytesPerRow) {
            // Write the entire imbge.
            strebm.write(bdbtb, 0, h * bytesPerRow);
            processImbgeProgress(100.0F);
        } else {
            // Write the imbge row-by-row.
            int offset = 0;
            if(!isWhiteZero) {
                // Blbck-is-zero
                for(int row = 0; row < h; row++) {
                    if (bbortRequested())
                        brebk;
                    strebm.write(bdbtb, offset, bytesPerRow);
                    offset += lineStride;
                    processImbgeProgress(100.0F * row / h);
                }
            } else {
                // White-is-zero: need to invert dbtb.
                byte[] inverted = new byte[bytesPerRow];
                for(int row = 0; row < h; row++) {
                    if (bbortRequested())
                        brebk;
                    for(int col = 0; col < bytesPerRow; col++) {
                        inverted[col] = (byte)(~(bdbtb[col+offset]));
                    }
                    strebm.write(inverted, 0, bytesPerRow);
                    offset += lineStride;
                    processImbgeProgress(100.0F * row / h);
                }
            }
        }

        if (bbortRequested())
            processWriteAborted();
        else {
            processImbgeComplete();
            strebm.flushBefore(strebm.getStrebmPosition());
        }
    }

    public void reset() {
        super.reset();
        strebm = null;
    }

    privbte void checkSbmpleModel(SbmpleModel sm) {
        int type = sm.getDbtbType();
        if (type < DbtbBuffer.TYPE_BYTE || type > DbtbBuffer.TYPE_INT
            || sm.getNumBbnds() != 1 || sm.getSbmpleSize(0) != 1)
            throw new IllegblArgumentException(I18N.getString("WBMPImbgeWriter2"));
    }
}
