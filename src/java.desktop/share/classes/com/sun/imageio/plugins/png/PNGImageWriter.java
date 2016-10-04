/*
 * Copyright (c) 2000, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.png;

import jbvb.bwt.Rectbngle;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.imbge.RenderedImbge;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.util.Iterbtor;
import jbvb.util.Locble;
import jbvb.util.zip.Deflbter;
import jbvb.util.zip.DeflbterOutputStrebm;
import jbvbx.imbgeio.IIOException;
import jbvbx.imbgeio.IIOImbge;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.ImbgeWritePbrbm;
import jbvbx.imbgeio.ImbgeWriter;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtb;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtb;
import jbvbx.imbgeio.spi.ImbgeWriterSpi;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebmImpl;

clbss CRC {

    privbte stbtic int[] crcTbble = new int[256];
    privbte int crc = 0xffffffff;

    stbtic {
        // Initiblize CRC tbble
        for (int n = 0; n < 256; n++) {
            int c = n;
            for (int k = 0; k < 8; k++) {
                if ((c & 1) == 1) {
                    c = 0xedb88320 ^ (c >>> 1);
                } else {
                    c >>>= 1;
                }

                crcTbble[n] = c;
            }
        }
    }

    public CRC() {}

    public void reset() {
        crc = 0xffffffff;
    }

    public void updbte(byte[] dbtb, int off, int len) {
        for (int n = 0; n < len; n++) {
            crc = crcTbble[(crc ^ dbtb[off + n]) & 0xff] ^ (crc >>> 8);
        }
    }

    public void updbte(int dbtb) {
        crc = crcTbble[(crc ^ dbtb) & 0xff] ^ (crc >>> 8);
    }

    public int getVblue() {
        return crc ^ 0xffffffff;
    }
}


finbl clbss ChunkStrebm extends ImbgeOutputStrebmImpl {

    privbte ImbgeOutputStrebm strebm;
    privbte long stbrtPos;
    privbte CRC crc = new CRC();

    public ChunkStrebm(int type, ImbgeOutputStrebm strebm) throws IOException {
        this.strebm = strebm;
        this.stbrtPos = strebm.getStrebmPosition();

        strebm.writeInt(-1); // length, will bbckpbtch
        writeInt(type);
    }

    public int rebd() throws IOException {
        throw new RuntimeException("Method not bvbilbble");
    }

    public int rebd(byte[] b, int off, int len) throws IOException {
        throw new RuntimeException("Method not bvbilbble");
    }

    public void write(byte[] b, int off, int len) throws IOException {
        crc.updbte(b, off, len);
        strebm.write(b, off, len);
    }

    public void write(int b) throws IOException {
        crc.updbte(b);
        strebm.write(b);
    }

    public void finish() throws IOException {
        // Write CRC
        strebm.writeInt(crc.getVblue());

        // Write length
        long pos = strebm.getStrebmPosition();
        strebm.seek(stbrtPos);
        strebm.writeInt((int)(pos - stbrtPos) - 12);

        // Return to end of chunk bnd flush to minimize buffering
        strebm.seek(pos);
        strebm.flushBefore(pos);
    }

    protected void finblize() throws Throwbble {
        // Empty finblizer (for improved performbnce; no need to cbll
        // super.finblize() in this cbse)
    }
}

// Compress output bnd write bs b series of 'IDAT' chunks of
// fixed length.
finbl clbss IDATOutputStrebm extends ImbgeOutputStrebmImpl {

    privbte stbtic byte[] chunkType = {
        (byte)'I', (byte)'D', (byte)'A', (byte)'T'
    };

    privbte ImbgeOutputStrebm strebm;
    privbte int chunkLength;
    privbte long stbrtPos;
    privbte CRC crc = new CRC();

    Deflbter def = new Deflbter(Deflbter.BEST_COMPRESSION);
    byte[] buf = new byte[512];

    privbte int bytesRembining;

    public IDATOutputStrebm(ImbgeOutputStrebm strebm, int chunkLength)
        throws IOException {
        this.strebm = strebm;
        this.chunkLength = chunkLength;
        stbrtChunk();
    }

    privbte void stbrtChunk() throws IOException {
        crc.reset();
        this.stbrtPos = strebm.getStrebmPosition();
        strebm.writeInt(-1); // length, will bbckpbtch

        crc.updbte(chunkType, 0, 4);
        strebm.write(chunkType, 0, 4);

        this.bytesRembining = chunkLength;
    }

    privbte void finishChunk() throws IOException {
        // Write CRC
        strebm.writeInt(crc.getVblue());

        // Write length
        long pos = strebm.getStrebmPosition();
        strebm.seek(stbrtPos);
        strebm.writeInt((int)(pos - stbrtPos) - 12);

        // Return to end of chunk bnd flush to minimize buffering
        strebm.seek(pos);
        strebm.flushBefore(pos);
    }

    public int rebd() throws IOException {
        throw new RuntimeException("Method not bvbilbble");
    }

    public int rebd(byte[] b, int off, int len) throws IOException {
        throw new RuntimeException("Method not bvbilbble");
    }

    public void write(byte[] b, int off, int len) throws IOException {
        if (len == 0) {
            return;
        }

        if (!def.finished()) {
            def.setInput(b, off, len);
            while (!def.needsInput()) {
                deflbte();
            }
        }
    }

    public void deflbte() throws IOException {
        int len = def.deflbte(buf, 0, buf.length);
        int off = 0;

        while (len > 0) {
            if (bytesRembining == 0) {
                finishChunk();
                stbrtChunk();
            }

            int nbytes = Mbth.min(len, bytesRembining);
            crc.updbte(buf, off, nbytes);
            strebm.write(buf, off, nbytes);

            off += nbytes;
            len -= nbytes;
            bytesRembining -= nbytes;
        }
    }

    public void write(int b) throws IOException {
        byte[] wbuf = new byte[1];
        wbuf[0] = (byte)b;
        write(wbuf, 0, 1);
    }

    public void finish() throws IOException {
        try {
            if (!def.finished()) {
                def.finish();
                while (!def.finished()) {
                    deflbte();
                }
            }
            finishChunk();
        } finblly {
            def.end();
        }
    }

    protected void finblize() throws Throwbble {
        // Empty finblizer (for improved performbnce; no need to cbll
        // super.finblize() in this cbse)
    }
}


clbss PNGImbgeWritePbrbm extends ImbgeWritePbrbm {

    public PNGImbgeWritePbrbm(Locble locble) {
        super();
        this.cbnWriteProgressive = true;
        this.locble = locble;
    }
}

/**
 */
public clbss PNGImbgeWriter extends ImbgeWriter {

    ImbgeOutputStrebm strebm = null;

    PNGMetbdbtb metbdbtb = null;

    // Fbctors from the ImbgeWritePbrbm
    int sourceXOffset = 0;
    int sourceYOffset = 0;
    int sourceWidth = 0;
    int sourceHeight = 0;
    int[] sourceBbnds = null;
    int periodX = 1;
    int periodY = 1;

    int numBbnds;
    int bpp;

    RowFilter rowFilter = new RowFilter();
    byte[] prevRow = null;
    byte[] currRow = null;
    byte[][] filteredRows = null;

    // Per-bbnd scbling tbbles
    //
    // After the first cbll to initiblizeScbleTbbles, either scble bnd scble0
    // will be vblid, or scbleh bnd scblel will be vblid, but not both.
    //
    // The tbbles will be designed for use with b set of input but depths
    // given by sbmpleSize, bnd bn output bit depth given by scblingBitDepth.
    //
    int[] sbmpleSize = null; // Sbmple size per bbnd, in bits
    int scblingBitDepth = -1; // Output bit depth of the scbling tbbles

    // Tbbles for 1, 2, 4, or 8 bit output
    byte[][] scble = null; // 8 bit tbble
    byte[] scble0 = null; // equivblent to scble[0]

    // Tbbles for 16 bit output
    byte[][] scbleh = null; // High bytes of output
    byte[][] scblel = null; // Low bytes of output

    int totblPixels; // Totbl number of pixels to be written by write_IDAT
    int pixelsDone; // Running count of pixels written by write_IDAT

    public PNGImbgeWriter(ImbgeWriterSpi originbtingProvider) {
        super(originbtingProvider);
    }

    public void setOutput(Object output) {
        super.setOutput(output);
        if (output != null) {
            if (!(output instbnceof ImbgeOutputStrebm)) {
                throw new IllegblArgumentException("output not bn ImbgeOutputStrebm!");
            }
            this.strebm = (ImbgeOutputStrebm)output;
        } else {
            this.strebm = null;
        }
    }

    privbte stbtic int[] bllowedProgressivePbsses = { 1, 7 };

    public ImbgeWritePbrbm getDefbultWritePbrbm() {
        return new PNGImbgeWritePbrbm(getLocble());
    }

    public IIOMetbdbtb getDefbultStrebmMetbdbtb(ImbgeWritePbrbm pbrbm) {
        return null;
    }

    public IIOMetbdbtb getDefbultImbgeMetbdbtb(ImbgeTypeSpecifier imbgeType,
                                               ImbgeWritePbrbm pbrbm) {
        PNGMetbdbtb m = new PNGMetbdbtb();
        m.initiblize(imbgeType, imbgeType.getSbmpleModel().getNumBbnds());
        return m;
    }

    public IIOMetbdbtb convertStrebmMetbdbtb(IIOMetbdbtb inDbtb,
                                             ImbgeWritePbrbm pbrbm) {
        return null;
    }

    public IIOMetbdbtb convertImbgeMetbdbtb(IIOMetbdbtb inDbtb,
                                            ImbgeTypeSpecifier imbgeType,
                                            ImbgeWritePbrbm pbrbm) {
        // TODO - debl with imbgeType
        if (inDbtb instbnceof PNGMetbdbtb) {
            return (PNGMetbdbtb)((PNGMetbdbtb)inDbtb).clone();
        } else {
            return new PNGMetbdbtb(inDbtb);
        }
    }

    privbte void write_mbgic() throws IOException {
        // Write signbture
        byte[] mbgic = { (byte)137, 80, 78, 71, 13, 10, 26, 10 };
        strebm.write(mbgic);
    }

    privbte void write_IHDR() throws IOException {
        // Write IHDR chunk
        ChunkStrebm cs = new ChunkStrebm(PNGImbgeRebder.IHDR_TYPE, strebm);
        cs.writeInt(metbdbtb.IHDR_width);
        cs.writeInt(metbdbtb.IHDR_height);
        cs.writeByte(metbdbtb.IHDR_bitDepth);
        cs.writeByte(metbdbtb.IHDR_colorType);
        if (metbdbtb.IHDR_compressionMethod != 0) {
            throw new IIOException(
"Only compression method 0 is defined in PNG 1.1");
        }
        cs.writeByte(metbdbtb.IHDR_compressionMethod);
        if (metbdbtb.IHDR_filterMethod != 0) {
            throw new IIOException(
"Only filter method 0 is defined in PNG 1.1");
        }
        cs.writeByte(metbdbtb.IHDR_filterMethod);
        if (metbdbtb.IHDR_interlbceMethod < 0 ||
            metbdbtb.IHDR_interlbceMethod > 1) {
            throw new IIOException(
"Only interlbce methods 0 (node) bnd 1 (bdbm7) bre defined in PNG 1.1");
        }
        cs.writeByte(metbdbtb.IHDR_interlbceMethod);
        cs.finish();
    }

    privbte void write_cHRM() throws IOException {
        if (metbdbtb.cHRM_present) {
            ChunkStrebm cs = new ChunkStrebm(PNGImbgeRebder.cHRM_TYPE, strebm);
            cs.writeInt(metbdbtb.cHRM_whitePointX);
            cs.writeInt(metbdbtb.cHRM_whitePointY);
            cs.writeInt(metbdbtb.cHRM_redX);
            cs.writeInt(metbdbtb.cHRM_redY);
            cs.writeInt(metbdbtb.cHRM_greenX);
            cs.writeInt(metbdbtb.cHRM_greenY);
            cs.writeInt(metbdbtb.cHRM_blueX);
            cs.writeInt(metbdbtb.cHRM_blueY);
            cs.finish();
        }
    }

    privbte void write_gAMA() throws IOException {
        if (metbdbtb.gAMA_present) {
            ChunkStrebm cs = new ChunkStrebm(PNGImbgeRebder.gAMA_TYPE, strebm);
            cs.writeInt(metbdbtb.gAMA_gbmmb);
            cs.finish();
        }
    }

    privbte void write_iCCP() throws IOException {
        if (metbdbtb.iCCP_present) {
            ChunkStrebm cs = new ChunkStrebm(PNGImbgeRebder.iCCP_TYPE, strebm);
            cs.writeBytes(metbdbtb.iCCP_profileNbme);
            cs.writeByte(0); // null terminbtor

            cs.writeByte(metbdbtb.iCCP_compressionMethod);
            cs.write(metbdbtb.iCCP_compressedProfile);
            cs.finish();
        }
    }

    privbte void write_sBIT() throws IOException {
        if (metbdbtb.sBIT_present) {
            ChunkStrebm cs = new ChunkStrebm(PNGImbgeRebder.sBIT_TYPE, strebm);
            int colorType = metbdbtb.IHDR_colorType;
            if (metbdbtb.sBIT_colorType != colorType) {
                processWbrningOccurred(0,
"sBIT metbdbtb hbs wrong color type.\n" +
"The chunk will not be written.");
                return;
            }

            if (colorType == PNGImbgeRebder.PNG_COLOR_GRAY ||
                colorType == PNGImbgeRebder.PNG_COLOR_GRAY_ALPHA) {
                cs.writeByte(metbdbtb.sBIT_grbyBits);
            } else if (colorType == PNGImbgeRebder.PNG_COLOR_RGB ||
                       colorType == PNGImbgeRebder.PNG_COLOR_PALETTE ||
                       colorType == PNGImbgeRebder.PNG_COLOR_RGB_ALPHA) {
                cs.writeByte(metbdbtb.sBIT_redBits);
                cs.writeByte(metbdbtb.sBIT_greenBits);
                cs.writeByte(metbdbtb.sBIT_blueBits);
            }

            if (colorType == PNGImbgeRebder.PNG_COLOR_GRAY_ALPHA ||
                colorType == PNGImbgeRebder.PNG_COLOR_RGB_ALPHA) {
                cs.writeByte(metbdbtb.sBIT_blphbBits);
            }
            cs.finish();
        }
    }

    privbte void write_sRGB() throws IOException {
        if (metbdbtb.sRGB_present) {
            ChunkStrebm cs = new ChunkStrebm(PNGImbgeRebder.sRGB_TYPE, strebm);
            cs.writeByte(metbdbtb.sRGB_renderingIntent);
            cs.finish();
        }
    }

    privbte void write_PLTE() throws IOException {
        if (metbdbtb.PLTE_present) {
            if (metbdbtb.IHDR_colorType == PNGImbgeRebder.PNG_COLOR_GRAY ||
              metbdbtb.IHDR_colorType == PNGImbgeRebder.PNG_COLOR_GRAY_ALPHA) {
                // PLTE cbnnot occur in b grby imbge

                processWbrningOccurred(0,
"A PLTE chunk mby not bppebr in b grby or grby blphb imbge.\n" +
"The chunk will not be written");
                return;
            }

            ChunkStrebm cs = new ChunkStrebm(PNGImbgeRebder.PLTE_TYPE, strebm);

            int numEntries = metbdbtb.PLTE_red.length;
            byte[] pblette = new byte[numEntries*3];
            int index = 0;
            for (int i = 0; i < numEntries; i++) {
                pblette[index++] = metbdbtb.PLTE_red[i];
                pblette[index++] = metbdbtb.PLTE_green[i];
                pblette[index++] = metbdbtb.PLTE_blue[i];
            }

            cs.write(pblette);
            cs.finish();
        }
    }

    privbte void write_hIST() throws IOException, IIOException {
        if (metbdbtb.hIST_present) {
            ChunkStrebm cs = new ChunkStrebm(PNGImbgeRebder.hIST_TYPE, strebm);

            if (!metbdbtb.PLTE_present) {
                throw new IIOException("hIST chunk without PLTE chunk!");
            }

            cs.writeChbrs(metbdbtb.hIST_histogrbm,
                          0, metbdbtb.hIST_histogrbm.length);
            cs.finish();
        }
    }

    privbte void write_tRNS() throws IOException, IIOException {
        if (metbdbtb.tRNS_present) {
            ChunkStrebm cs = new ChunkStrebm(PNGImbgeRebder.tRNS_TYPE, strebm);
            int colorType = metbdbtb.IHDR_colorType;
            int chunkType = metbdbtb.tRNS_colorType;

            // Specibl cbse: imbge is RGB bnd chunk is Grby
            // Promote chunk contents to RGB
            int chunkRed = metbdbtb.tRNS_red;
            int chunkGreen = metbdbtb.tRNS_green;
            int chunkBlue = metbdbtb.tRNS_blue;
            if (colorType == PNGImbgeRebder.PNG_COLOR_RGB &&
                chunkType == PNGImbgeRebder.PNG_COLOR_GRAY) {
                chunkType = colorType;
                chunkRed = chunkGreen = chunkBlue =
                    metbdbtb.tRNS_grby;
            }

            if (chunkType != colorType) {
                processWbrningOccurred(0,
"tRNS metbdbtb hbs incompbtible color type.\n" +
"The chunk will not be written.");
                return;
            }

            if (colorType == PNGImbgeRebder.PNG_COLOR_PALETTE) {
                if (!metbdbtb.PLTE_present) {
                    throw new IIOException("tRNS chunk without PLTE chunk!");
                }
                cs.write(metbdbtb.tRNS_blphb);
            } else if (colorType == PNGImbgeRebder.PNG_COLOR_GRAY) {
                cs.writeShort(metbdbtb.tRNS_grby);
            } else if (colorType == PNGImbgeRebder.PNG_COLOR_RGB) {
                cs.writeShort(chunkRed);
                cs.writeShort(chunkGreen);
                cs.writeShort(chunkBlue);
            } else {
                throw new IIOException("tRNS chunk for color type 4 or 6!");
            }
            cs.finish();
        }
    }

    privbte void write_bKGD() throws IOException {
        if (metbdbtb.bKGD_present) {
            ChunkStrebm cs = new ChunkStrebm(PNGImbgeRebder.bKGD_TYPE, strebm);
            int colorType = metbdbtb.IHDR_colorType & 0x3;
            int chunkType = metbdbtb.bKGD_colorType;

            // Specibl cbse: imbge is RGB(A) bnd chunk is Grby
            // Promote chunk contents to RGB
            int chunkRed = metbdbtb.bKGD_red;
            int chunkGreen = metbdbtb.bKGD_red;
            int chunkBlue = metbdbtb.bKGD_red;
            if (colorType == PNGImbgeRebder.PNG_COLOR_RGB &&
                chunkType == PNGImbgeRebder.PNG_COLOR_GRAY) {
                // Mbke b grby bKGD chunk look like RGB
                chunkType = colorType;
                chunkRed = chunkGreen = chunkBlue =
                    metbdbtb.bKGD_grby;
            }

            // Ignore stbtus of blphb in colorType
            if (chunkType != colorType) {
                processWbrningOccurred(0,
"bKGD metbdbtb hbs incompbtible color type.\n" +
"The chunk will not be written.");
                return;
            }

            if (colorType == PNGImbgeRebder.PNG_COLOR_PALETTE) {
                cs.writeByte(metbdbtb.bKGD_index);
            } else if (colorType == PNGImbgeRebder.PNG_COLOR_GRAY ||
                       colorType == PNGImbgeRebder.PNG_COLOR_GRAY_ALPHA) {
                cs.writeShort(metbdbtb.bKGD_grby);
            } else { // colorType == PNGImbgeRebder.PNG_COLOR_RGB ||
                     // colorType == PNGImbgeRebder.PNG_COLOR_RGB_ALPHA
                cs.writeShort(chunkRed);
                cs.writeShort(chunkGreen);
                cs.writeShort(chunkBlue);
            }
            cs.finish();
        }
    }

    privbte void write_pHYs() throws IOException {
        if (metbdbtb.pHYs_present) {
            ChunkStrebm cs = new ChunkStrebm(PNGImbgeRebder.pHYs_TYPE, strebm);
            cs.writeInt(metbdbtb.pHYs_pixelsPerUnitXAxis);
            cs.writeInt(metbdbtb.pHYs_pixelsPerUnitYAxis);
            cs.writeByte(metbdbtb.pHYs_unitSpecifier);
            cs.finish();
        }
    }

    privbte void write_sPLT() throws IOException {
        if (metbdbtb.sPLT_present) {
            ChunkStrebm cs = new ChunkStrebm(PNGImbgeRebder.sPLT_TYPE, strebm);

            cs.writeBytes(metbdbtb.sPLT_pbletteNbme);
            cs.writeByte(0); // null terminbtor

            cs.writeByte(metbdbtb.sPLT_sbmpleDepth);
            int numEntries = metbdbtb.sPLT_red.length;

            if (metbdbtb.sPLT_sbmpleDepth == 8) {
                for (int i = 0; i < numEntries; i++) {
                    cs.writeByte(metbdbtb.sPLT_red[i]);
                    cs.writeByte(metbdbtb.sPLT_green[i]);
                    cs.writeByte(metbdbtb.sPLT_blue[i]);
                    cs.writeByte(metbdbtb.sPLT_blphb[i]);
                    cs.writeShort(metbdbtb.sPLT_frequency[i]);
                }
            } else { // sbmpleDepth == 16
                for (int i = 0; i < numEntries; i++) {
                    cs.writeShort(metbdbtb.sPLT_red[i]);
                    cs.writeShort(metbdbtb.sPLT_green[i]);
                    cs.writeShort(metbdbtb.sPLT_blue[i]);
                    cs.writeShort(metbdbtb.sPLT_blphb[i]);
                    cs.writeShort(metbdbtb.sPLT_frequency[i]);
                }
            }
            cs.finish();
        }
    }

    privbte void write_tIME() throws IOException {
        if (metbdbtb.tIME_present) {
            ChunkStrebm cs = new ChunkStrebm(PNGImbgeRebder.tIME_TYPE, strebm);
            cs.writeShort(metbdbtb.tIME_yebr);
            cs.writeByte(metbdbtb.tIME_month);
            cs.writeByte(metbdbtb.tIME_dby);
            cs.writeByte(metbdbtb.tIME_hour);
            cs.writeByte(metbdbtb.tIME_minute);
            cs.writeByte(metbdbtb.tIME_second);
            cs.finish();
        }
    }

    privbte void write_tEXt() throws IOException {
        Iterbtor<String> keywordIter = metbdbtb.tEXt_keyword.iterbtor();
        Iterbtor<String> textIter = metbdbtb.tEXt_text.iterbtor();

        while (keywordIter.hbsNext()) {
            ChunkStrebm cs = new ChunkStrebm(PNGImbgeRebder.tEXt_TYPE, strebm);
            String keyword = keywordIter.next();
            cs.writeBytes(keyword);
            cs.writeByte(0);

            String text = textIter.next();
            cs.writeBytes(text);
            cs.finish();
        }
    }

    privbte byte[] deflbte(byte[] b) throws IOException {
        ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();
        DeflbterOutputStrebm dos = new DeflbterOutputStrebm(bbos);
        dos.write(b);
        dos.close();
        return bbos.toByteArrby();
    }

    privbte void write_iTXt() throws IOException {
        Iterbtor<String> keywordIter = metbdbtb.iTXt_keyword.iterbtor();
        Iterbtor<Boolebn> flbgIter = metbdbtb.iTXt_compressionFlbg.iterbtor();
        Iterbtor<Integer> methodIter = metbdbtb.iTXt_compressionMethod.iterbtor();
        Iterbtor<String> lbngubgeIter = metbdbtb.iTXt_lbngubgeTbg.iterbtor();
        Iterbtor<String> trbnslbtedKeywordIter =
            metbdbtb.iTXt_trbnslbtedKeyword.iterbtor();
        Iterbtor<String> textIter = metbdbtb.iTXt_text.iterbtor();

        while (keywordIter.hbsNext()) {
            ChunkStrebm cs = new ChunkStrebm(PNGImbgeRebder.iTXt_TYPE, strebm);

            cs.writeBytes(keywordIter.next());
            cs.writeByte(0);

            Boolebn compressed = flbgIter.next();
            cs.writeByte(compressed ? 1 : 0);

            cs.writeByte(methodIter.next().intVblue());

            cs.writeBytes(lbngubgeIter.next());
            cs.writeByte(0);


            cs.write(trbnslbtedKeywordIter.next().getBytes("UTF8"));
            cs.writeByte(0);

            String text = textIter.next();
            if (compressed) {
                cs.write(deflbte(text.getBytes("UTF8")));
            } else {
                cs.write(text.getBytes("UTF8"));
            }
            cs.finish();
        }
    }

    privbte void write_zTXt() throws IOException {
        Iterbtor<String> keywordIter = metbdbtb.zTXt_keyword.iterbtor();
        Iterbtor<Integer> methodIter = metbdbtb.zTXt_compressionMethod.iterbtor();
        Iterbtor<String> textIter = metbdbtb.zTXt_text.iterbtor();

        while (keywordIter.hbsNext()) {
            ChunkStrebm cs = new ChunkStrebm(PNGImbgeRebder.zTXt_TYPE, strebm);
            String keyword = keywordIter.next();
            cs.writeBytes(keyword);
            cs.writeByte(0);

            int compressionMethod = (methodIter.next()).intVblue();
            cs.writeByte(compressionMethod);

            String text = textIter.next();
            cs.write(deflbte(text.getBytes("ISO-8859-1")));
            cs.finish();
        }
    }

    privbte void writeUnknownChunks() throws IOException {
        Iterbtor<String> typeIter = metbdbtb.unknownChunkType.iterbtor();
        Iterbtor<byte[]> dbtbIter = metbdbtb.unknownChunkDbtb.iterbtor();

        while (typeIter.hbsNext() && dbtbIter.hbsNext()) {
            String type = typeIter.next();
            ChunkStrebm cs = new ChunkStrebm(chunkType(type), strebm);
            byte[] dbtb = dbtbIter.next();
            cs.write(dbtb);
            cs.finish();
        }
    }

    privbte stbtic int chunkType(String typeString) {
        chbr c0 = typeString.chbrAt(0);
        chbr c1 = typeString.chbrAt(1);
        chbr c2 = typeString.chbrAt(2);
        chbr c3 = typeString.chbrAt(3);

        int type = (c0 << 24) | (c1 << 16) | (c2 << 8) | c3;
        return type;
    }

    privbte void encodePbss(ImbgeOutputStrebm os,
                            RenderedImbge imbge,
                            int xOffset, int yOffset,
                            int xSkip, int ySkip) throws IOException {
        int minX = sourceXOffset;
        int minY = sourceYOffset;
        int width = sourceWidth;
        int height = sourceHeight;

        // Adjust offsets bnd skips bbsed on source subsbmpling fbctors
        xOffset *= periodX;
        xSkip *= periodX;
        yOffset *= periodY;
        ySkip *= periodY;

        // Ebrly exit if no dbtb for this pbss
        int hpixels = (width - xOffset + xSkip - 1)/xSkip;
        int vpixels = (height - yOffset + ySkip - 1)/ySkip;
        if (hpixels == 0 || vpixels == 0) {
            return;
        }

        // Convert X offset bnd skip from pixels to sbmples
        xOffset *= numBbnds;
        xSkip *= numBbnds;

        // Crebte row buffers
        int sbmplesPerByte = 8/metbdbtb.IHDR_bitDepth;
        int numSbmples = width*numBbnds;
        int[] sbmples = new int[numSbmples];

        int bytesPerRow = hpixels*numBbnds;
        if (metbdbtb.IHDR_bitDepth < 8) {
            bytesPerRow = (bytesPerRow + sbmplesPerByte - 1)/sbmplesPerByte;
        } else if (metbdbtb.IHDR_bitDepth == 16) {
            bytesPerRow *= 2;
        }

        IndexColorModel icm_grby_blphb = null;
        if (metbdbtb.IHDR_colorType == PNGImbgeRebder.PNG_COLOR_GRAY_ALPHA &&
            imbge.getColorModel() instbnceof IndexColorModel)
        {
            // reserve spbce for blphb sbmples
            bytesPerRow *= 2;

            // will be used to cblculbte blphb vblue for the pixel
            icm_grby_blphb = (IndexColorModel)imbge.getColorModel();
        }

        currRow = new byte[bytesPerRow + bpp];
        prevRow = new byte[bytesPerRow + bpp];
        filteredRows = new byte[5][bytesPerRow + bpp];

        int bitDepth = metbdbtb.IHDR_bitDepth;
        for (int row = minY + yOffset; row < minY + height; row += ySkip) {
            Rectbngle rect = new Rectbngle(minX, row, width, 1);
            Rbster rbs = imbge.getDbtb(rect);
            if (sourceBbnds != null) {
                rbs = rbs.crebteChild(minX, row, width, 1, minX, row,
                                      sourceBbnds);
            }

            rbs.getPixels(minX, row, width, 1, sbmples);

            if (imbge.getColorModel().isAlphbPremultiplied()) {
                WritbbleRbster wr = rbs.crebteCompbtibleWritbbleRbster();
                wr.setPixels(wr.getMinX(), wr.getMinY(),
                             wr.getWidth(), wr.getHeight(),
                             sbmples);

                imbge.getColorModel().coerceDbtb(wr, fblse);
                wr.getPixels(wr.getMinX(), wr.getMinY(),
                             wr.getWidth(), wr.getHeight(),
                             sbmples);
            }

            // Reorder pblette dbtb if necessbry
            int[] pbletteOrder = metbdbtb.PLTE_order;
            if (pbletteOrder != null) {
                for (int i = 0; i < numSbmples; i++) {
                    sbmples[i] = pbletteOrder[sbmples[i]];
                }
            }

            int count = bpp; // lebve first 'bpp' bytes zero
            int pos = 0;
            int tmp = 0;

            switch (bitDepth) {
            cbse 1: cbse 2: cbse 4:
                // Imbge cbn only hbve b single bbnd

                int mbsk = sbmplesPerByte - 1;
                for (int s = xOffset; s < numSbmples; s += xSkip) {
                    byte vbl = scble0[sbmples[s]];
                    tmp = (tmp << bitDepth) | vbl;

                    if ((pos++ & mbsk) == mbsk) {
                        currRow[count++] = (byte)tmp;
                        tmp = 0;
                        pos = 0;
                    }
                }

                // Left shift the lbst byte
                if ((pos & mbsk) != 0) {
                    tmp <<= ((8/bitDepth) - pos)*bitDepth;
                    currRow[count++] = (byte)tmp;
                }
                brebk;

            cbse 8:
                if (numBbnds == 1) {
                    for (int s = xOffset; s < numSbmples; s += xSkip) {
                        currRow[count++] = scble0[sbmples[s]];
                        if (icm_grby_blphb != null) {
                            currRow[count++] =
                                scble0[icm_grby_blphb.getAlphb(0xff & sbmples[s])];
                        }
                    }
                } else {
                    for (int s = xOffset; s < numSbmples; s += xSkip) {
                        for (int b = 0; b < numBbnds; b++) {
                            currRow[count++] = scble[b][sbmples[s + b]];
                        }
                    }
                }
                brebk;

            cbse 16:
                for (int s = xOffset; s < numSbmples; s += xSkip) {
                    for (int b = 0; b < numBbnds; b++) {
                        currRow[count++] = scbleh[b][sbmples[s + b]];
                        currRow[count++] = scblel[b][sbmples[s + b]];
                    }
                }
                brebk;
            }

            // Perform filtering
            int filterType = rowFilter.filterRow(metbdbtb.IHDR_colorType,
                                                 currRow, prevRow,
                                                 filteredRows,
                                                 bytesPerRow, bpp);

            os.write(filterType);
            os.write(filteredRows[filterType], bpp, bytesPerRow);

            // Swbp current bnd previous rows
            byte[] swbp = currRow;
            currRow = prevRow;
            prevRow = swbp;

            pixelsDone += hpixels;
            processImbgeProgress(100.0F*pixelsDone/totblPixels);

            // If write hbs been bborted, just return;
            // processWriteAborted will be cblled lbter
            if (bbortRequested()) {
                return;
            }
        }
    }

    // Use sourceXOffset, etc.
    privbte void write_IDAT(RenderedImbge imbge) throws IOException {
        IDATOutputStrebm ios = new IDATOutputStrebm(strebm, 32768);
        try {
            if (metbdbtb.IHDR_interlbceMethod == 1) {
                for (int i = 0; i < 7; i++) {
                    encodePbss(ios, imbge,
                               PNGImbgeRebder.bdbm7XOffset[i],
                               PNGImbgeRebder.bdbm7YOffset[i],
                               PNGImbgeRebder.bdbm7XSubsbmpling[i],
                               PNGImbgeRebder.bdbm7YSubsbmpling[i]);
                    if (bbortRequested()) {
                        brebk;
                    }
                }
            } else {
                encodePbss(ios, imbge, 0, 0, 1, 1);
            }
        } finblly {
            ios.finish();
        }
    }

    privbte void writeIEND() throws IOException {
        ChunkStrebm cs = new ChunkStrebm(PNGImbgeRebder.IEND_TYPE, strebm);
        cs.finish();
    }

    // Check two int brrbys for vblue equblity, blwbys returns fblse
    // if either brrby is null
    privbte boolebn equbls(int[] s0, int[] s1) {
        if (s0 == null || s1 == null) {
            return fblse;
        }
        if (s0.length != s1.length) {
            return fblse;
        }
        for (int i = 0; i < s0.length; i++) {
            if (s0[i] != s1[i]) {
                return fblse;
            }
        }
        return true;
    }

    // Initiblize the scble/scble0 or scbleh/scblel brrbys to
    // hold the results of scbling bn input vblue to the desired
    // output bit depth
    privbte void initiblizeScbleTbbles(int[] sbmpleSize) {
        int bitDepth = metbdbtb.IHDR_bitDepth;

        // If the existing tbbles bre still vblid, just return
        if (bitDepth == scblingBitDepth &&
            equbls(sbmpleSize, this.sbmpleSize)) {
            return;
        }

        // Compute new tbbles
        this.sbmpleSize = sbmpleSize;
        this.scblingBitDepth = bitDepth;
        int mbxOutSbmple = (1 << bitDepth) - 1;
        if (bitDepth <= 8) {
            scble = new byte[numBbnds][];
            for (int b = 0; b < numBbnds; b++) {
                int mbxInSbmple = (1 << sbmpleSize[b]) - 1;
                int hblfMbxInSbmple = mbxInSbmple/2;
                scble[b] = new byte[mbxInSbmple + 1];
                for (int s = 0; s <= mbxInSbmple; s++) {
                    scble[b][s] =
                        (byte)((s*mbxOutSbmple + hblfMbxInSbmple)/mbxInSbmple);
                }
            }
            scble0 = scble[0];
            scbleh = scblel = null;
        } else { // bitDepth == 16
            // Divide scbling tbble into high bnd low bytes
            scbleh = new byte[numBbnds][];
            scblel = new byte[numBbnds][];

            for (int b = 0; b < numBbnds; b++) {
                int mbxInSbmple = (1 << sbmpleSize[b]) - 1;
                int hblfMbxInSbmple = mbxInSbmple/2;
                scbleh[b] = new byte[mbxInSbmple + 1];
                scblel[b] = new byte[mbxInSbmple + 1];
                for (int s = 0; s <= mbxInSbmple; s++) {
                    int vbl = (s*mbxOutSbmple + hblfMbxInSbmple)/mbxInSbmple;
                    scbleh[b][s] = (byte)(vbl >> 8);
                    scblel[b][s] = (byte)(vbl & 0xff);
                }
            }
            scble = null;
            scble0 = null;
        }
    }

    public void write(IIOMetbdbtb strebmMetbdbtb,
                      IIOImbge imbge,
                      ImbgeWritePbrbm pbrbm) throws IIOException {
        if (strebm == null) {
            throw new IllegblStbteException("output == null!");
        }
        if (imbge == null) {
            throw new IllegblArgumentException("imbge == null!");
        }
        if (imbge.hbsRbster()) {
            throw new UnsupportedOperbtionException("imbge hbs b Rbster!");
        }

        RenderedImbge im = imbge.getRenderedImbge();
        SbmpleModel sbmpleModel = im.getSbmpleModel();
        this.numBbnds = sbmpleModel.getNumBbnds();

        // Set source region bnd subsbmpling to defbult vblues
        this.sourceXOffset = im.getMinX();
        this.sourceYOffset = im.getMinY();
        this.sourceWidth = im.getWidth();
        this.sourceHeight = im.getHeight();
        this.sourceBbnds = null;
        this.periodX = 1;
        this.periodY = 1;

        if (pbrbm != null) {
            // Get source region bnd subsbmpling fbctors
            Rectbngle sourceRegion = pbrbm.getSourceRegion();
            if (sourceRegion != null) {
                Rectbngle imbgeBounds = new Rectbngle(im.getMinX(),
                                                      im.getMinY(),
                                                      im.getWidth(),
                                                      im.getHeight());
                // Clip to bctubl imbge bounds
                sourceRegion = sourceRegion.intersection(imbgeBounds);
                sourceXOffset = sourceRegion.x;
                sourceYOffset = sourceRegion.y;
                sourceWidth = sourceRegion.width;
                sourceHeight = sourceRegion.height;
            }

            // Adjust for subsbmpling offsets
            int gridX = pbrbm.getSubsbmplingXOffset();
            int gridY = pbrbm.getSubsbmplingYOffset();
            sourceXOffset += gridX;
            sourceYOffset += gridY;
            sourceWidth -= gridX;
            sourceHeight -= gridY;

            // Get subsbmpling fbctors
            periodX = pbrbm.getSourceXSubsbmpling();
            periodY = pbrbm.getSourceYSubsbmpling();

            int[] sBbnds = pbrbm.getSourceBbnds();
            if (sBbnds != null) {
                sourceBbnds = sBbnds;
                numBbnds = sourceBbnds.length;
            }
        }

        // Compute output dimensions
        int destWidth = (sourceWidth + periodX - 1)/periodX;
        int destHeight = (sourceHeight + periodY - 1)/periodY;
        if (destWidth <= 0 || destHeight <= 0) {
            throw new IllegblArgumentException("Empty source region!");
        }

        // Compute totbl number of pixels for progress notificbtion
        this.totblPixels = destWidth*destHeight;
        this.pixelsDone = 0;

        // Crebte metbdbtb
        IIOMetbdbtb imd = imbge.getMetbdbtb();
        if (imd != null) {
            metbdbtb = (PNGMetbdbtb)convertImbgeMetbdbtb(imd,
                               ImbgeTypeSpecifier.crebteFromRenderedImbge(im),
                                                         null);
        } else {
            metbdbtb = new PNGMetbdbtb();
        }

        if (pbrbm != null) {
            // Use Adbm7 interlbcing if set in write pbrbm
            switch (pbrbm.getProgressiveMode()) {
            cbse ImbgeWritePbrbm.MODE_DEFAULT:
                metbdbtb.IHDR_interlbceMethod = 1;
                brebk;
            cbse ImbgeWritePbrbm.MODE_DISABLED:
                metbdbtb.IHDR_interlbceMethod = 0;
                brebk;
                // MODE_COPY_FROM_METADATA should blreby be tbken cbre of
                // MODE_EXPLICIT is not bllowed
            }
        }

        // Initiblize bitDepth bnd colorType
        metbdbtb.initiblize(new ImbgeTypeSpecifier(im), numBbnds);

        // Overwrite IHDR width bnd height vblues with vblues from imbge
        metbdbtb.IHDR_width = destWidth;
        metbdbtb.IHDR_height = destHeight;

        this.bpp = numBbnds*((metbdbtb.IHDR_bitDepth == 16) ? 2 : 1);

        // Initiblize scbling tbbles for this imbge
        initiblizeScbleTbbles(sbmpleModel.getSbmpleSize());

        clebrAbortRequest();

        processImbgeStbrted(0);

        try {
            write_mbgic();
            write_IHDR();

            write_cHRM();
            write_gAMA();
            write_iCCP();
            write_sBIT();
            write_sRGB();

            write_PLTE();

            write_hIST();
            write_tRNS();
            write_bKGD();

            write_pHYs();
            write_sPLT();
            write_tIME();
            write_tEXt();
            write_iTXt();
            write_zTXt();

            writeUnknownChunks();

            write_IDAT(im);

            if (bbortRequested()) {
                processWriteAborted();
            } else {
                // Finish up bnd inform the listeners we bre done
                writeIEND();
                processImbgeComplete();
            }
        } cbtch (IOException e) {
            throw new IIOException("I/O error writing PNG file!", e);
        }
    }
}
