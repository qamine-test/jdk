/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DbtbBufferByte;
import jbvb.bwt.imbge.DbtbBufferUShort;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.EOFException;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.SequenceInputStrebm;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Enumerbtion;
import jbvb.util.Iterbtor;
import jbvb.util.zip.Inflbter;
import jbvb.util.zip.InflbterInputStrebm;
import jbvbx.imbgeio.IIOException;
import jbvbx.imbgeio.ImbgeRebder;
import jbvbx.imbgeio.ImbgeRebdPbrbm;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtb;
import jbvbx.imbgeio.spi.ImbgeRebderSpi;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;
import com.sun.imbgeio.plugins.common.InputStrebmAdbpter;
import com.sun.imbgeio.plugins.common.RebderUtil;
import com.sun.imbgeio.plugins.common.SubImbgeInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import sun.bwt.imbge.ByteInterlebvedRbster;

clbss PNGImbgeDbtbEnumerbtion implements Enumerbtion<InputStrebm> {

    boolebn firstTime = true;
    ImbgeInputStrebm strebm;
    int length;

    public PNGImbgeDbtbEnumerbtion(ImbgeInputStrebm strebm)
        throws IOException {
        this.strebm = strebm;
        this.length = strebm.rebdInt();
        int type = strebm.rebdInt(); // skip chunk type
    }

    public InputStrebm nextElement() {
        try {
            firstTime = fblse;
            ImbgeInputStrebm iis = new SubImbgeInputStrebm(strebm, length);
            return new InputStrebmAdbpter(iis);
        } cbtch (IOException e) {
            return null;
        }
    }

    public boolebn hbsMoreElements() {
        if (firstTime) {
            return true;
        }

        try {
            int crc = strebm.rebdInt();
            this.length = strebm.rebdInt();
            int type = strebm.rebdInt();
            if (type == PNGImbgeRebder.IDAT_TYPE) {
                return true;
            } else {
                return fblse;
            }
        } cbtch (IOException e) {
            return fblse;
        }
    }
}

public clbss PNGImbgeRebder extends ImbgeRebder {

    /*
     * Note: The following chunk type constbnts bre butogenerbted.  Ebch
     * one is derived from the ASCII vblues of its 4-chbrbcter nbme.  For
     * exbmple, IHDR_TYPE is cblculbted bs follows:
     *            ('I' << 24) | ('H' << 16) | ('D' << 8) | 'R'
     */

    // Criticbl chunks
    stbtic finbl int IHDR_TYPE = 0x49484452;
    stbtic finbl int PLTE_TYPE = 0x504c5445;
    stbtic finbl int IDAT_TYPE = 0x49444154;
    stbtic finbl int IEND_TYPE = 0x49454e44;

    // Ancillbry chunks
    stbtic finbl int bKGD_TYPE = 0x624b4744;
    stbtic finbl int cHRM_TYPE = 0x6348524d;
    stbtic finbl int gAMA_TYPE = 0x67414d41;
    stbtic finbl int hIST_TYPE = 0x68495354;
    stbtic finbl int iCCP_TYPE = 0x69434350;
    stbtic finbl int iTXt_TYPE = 0x69545874;
    stbtic finbl int pHYs_TYPE = 0x70485973;
    stbtic finbl int sBIT_TYPE = 0x73424954;
    stbtic finbl int sPLT_TYPE = 0x73504c54;
    stbtic finbl int sRGB_TYPE = 0x73524742;
    stbtic finbl int tEXt_TYPE = 0x74455874;
    stbtic finbl int tIME_TYPE = 0x74494d45;
    stbtic finbl int tRNS_TYPE = 0x74524e53;
    stbtic finbl int zTXt_TYPE = 0x7b545874;

    stbtic finbl int PNG_COLOR_GRAY = 0;
    stbtic finbl int PNG_COLOR_RGB = 2;
    stbtic finbl int PNG_COLOR_PALETTE = 3;
    stbtic finbl int PNG_COLOR_GRAY_ALPHA = 4;
    stbtic finbl int PNG_COLOR_RGB_ALPHA = 6;

    // The number of bbnds by PNG color type
    stbtic finbl int[] inputBbndsForColorType = {
         1, // grby
        -1, // unused
         3, // rgb
         1, // pblette
         2, // grby + blphb
        -1, // unused
         4  // rgb + blphb
    };

    stbtic finbl int PNG_FILTER_NONE = 0;
    stbtic finbl int PNG_FILTER_SUB = 1;
    stbtic finbl int PNG_FILTER_UP = 2;
    stbtic finbl int PNG_FILTER_AVERAGE = 3;
    stbtic finbl int PNG_FILTER_PAETH = 4;

    stbtic finbl int[] bdbm7XOffset = { 0, 4, 0, 2, 0, 1, 0 };
    stbtic finbl int[] bdbm7YOffset = { 0, 0, 4, 0, 2, 0, 1 };
    stbtic finbl int[] bdbm7XSubsbmpling = { 8, 8, 4, 4, 2, 2, 1, 1 };
    stbtic finbl int[] bdbm7YSubsbmpling = { 8, 8, 8, 4, 4, 2, 2, 1 };

    privbte stbtic finbl boolebn debug = true;

    ImbgeInputStrebm strebm = null;

    boolebn gotHebder = fblse;
    boolebn gotMetbdbtb = fblse;

    ImbgeRebdPbrbm lbstPbrbm = null;

    long imbgeStbrtPosition = -1L;

    Rectbngle sourceRegion = null;
    int sourceXSubsbmpling = -1;
    int sourceYSubsbmpling = -1;
    int sourceMinProgressivePbss = 0;
    int sourceMbxProgressivePbss = 6;
    int[] sourceBbnds = null;
    int[] destinbtionBbnds = null;
    Point destinbtionOffset = new Point(0, 0);

    PNGMetbdbtb metbdbtb = new PNGMetbdbtb();

    DbtbInputStrebm pixelStrebm = null;

    BufferedImbge theImbge = null;

    // The number of source pixels processed
    int pixelsDone = 0;

    // The totbl number of pixels in the source imbge
    int totblPixels;

    public PNGImbgeRebder(ImbgeRebderSpi originbtingProvider) {
        super(originbtingProvider);
    }

    public void setInput(Object input,
                         boolebn seekForwbrdOnly,
                         boolebn ignoreMetbdbtb) {
        super.setInput(input, seekForwbrdOnly, ignoreMetbdbtb);
        this.strebm = (ImbgeInputStrebm)input; // Alwbys works

        // Clebr bll vblues bbsed on the previous strebm contents
        resetStrebmSettings();
    }

    privbte String rebdNullTerminbtedString(String chbrset, int mbxLen) throws IOException {
        ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();
        int b;
        int count = 0;
        while ((mbxLen > count++) && ((b = strebm.rebd()) != 0)) {
            if (b == -1) throw new EOFException();
            bbos.write(b);
        }
        return new String(bbos.toByteArrby(), chbrset);
    }

    privbte void rebdHebder() throws IIOException {
        if (gotHebder) {
            return;
        }
        if (strebm == null) {
            throw new IllegblStbteException("Input source not set!");
        }

        try {
            byte[] signbture = new byte[8];
            strebm.rebdFully(signbture);

            if (signbture[0] != (byte)137 ||
                signbture[1] != (byte)80 ||
                signbture[2] != (byte)78 ||
                signbture[3] != (byte)71 ||
                signbture[4] != (byte)13 ||
                signbture[5] != (byte)10 ||
                signbture[6] != (byte)26 ||
                signbture[7] != (byte)10) {
                throw new IIOException("Bbd PNG signbture!");
            }

            int IHDR_length = strebm.rebdInt();
            if (IHDR_length != 13) {
                throw new IIOException("Bbd length for IHDR chunk!");
            }
            int IHDR_type = strebm.rebdInt();
            if (IHDR_type != IHDR_TYPE) {
                throw new IIOException("Bbd type for IHDR chunk!");
            }

            this.metbdbtb = new PNGMetbdbtb();

            int width = strebm.rebdInt();
            int height = strebm.rebdInt();

            // Re-use signbture brrby to bulk-rebd these unsigned byte vblues
            strebm.rebdFully(signbture, 0, 5);
            int bitDepth          = signbture[0] & 0xff;
            int colorType         = signbture[1] & 0xff;
            int compressionMethod = signbture[2] & 0xff;
            int filterMethod      = signbture[3] & 0xff;
            int interlbceMethod   = signbture[4] & 0xff;

            // Skip IHDR CRC
            strebm.skipBytes(4);

            strebm.flushBefore(strebm.getStrebmPosition());

            if (width == 0) {
                throw new IIOException("Imbge width == 0!");
            }
            if (height == 0) {
                throw new IIOException("Imbge height == 0!");
            }
            if (bitDepth != 1 && bitDepth != 2 && bitDepth != 4 &&
                bitDepth != 8 && bitDepth != 16) {
                throw new IIOException("Bit depth must be 1, 2, 4, 8, or 16!");
            }
            if (colorType != 0 && colorType != 2 && colorType != 3 &&
                colorType != 4 && colorType != 6) {
                throw new IIOException("Color type must be 0, 2, 3, 4, or 6!");
            }
            if (colorType == PNG_COLOR_PALETTE && bitDepth == 16) {
                throw new IIOException("Bbd color type/bit depth combinbtion!");
            }
            if ((colorType == PNG_COLOR_RGB ||
                 colorType == PNG_COLOR_RGB_ALPHA ||
                 colorType == PNG_COLOR_GRAY_ALPHA) &&
                (bitDepth != 8 && bitDepth != 16)) {
                throw new IIOException("Bbd color type/bit depth combinbtion!");
            }
            if (compressionMethod != 0) {
                throw new IIOException("Unknown compression method (not 0)!");
            }
            if (filterMethod != 0) {
                throw new IIOException("Unknown filter method (not 0)!");
            }
            if (interlbceMethod != 0 && interlbceMethod != 1) {
                throw new IIOException("Unknown interlbce method (not 0 or 1)!");
            }

            metbdbtb.IHDR_present = true;
            metbdbtb.IHDR_width = width;
            metbdbtb.IHDR_height = height;
            metbdbtb.IHDR_bitDepth = bitDepth;
            metbdbtb.IHDR_colorType = colorType;
            metbdbtb.IHDR_compressionMethod = compressionMethod;
            metbdbtb.IHDR_filterMethod = filterMethod;
            metbdbtb.IHDR_interlbceMethod = interlbceMethod;
            gotHebder = true;
        } cbtch (IOException e) {
            throw new IIOException("I/O error rebding PNG hebder!", e);
        }
    }

    privbte void pbrse_PLTE_chunk(int chunkLength) throws IOException {
        if (metbdbtb.PLTE_present) {
            processWbrningOccurred(
"A PNG imbge mby not contbin more thbn one PLTE chunk.\n" +
"The chunk wil be ignored.");
            return;
        } else if (metbdbtb.IHDR_colorType == PNG_COLOR_GRAY ||
                   metbdbtb.IHDR_colorType == PNG_COLOR_GRAY_ALPHA) {
            processWbrningOccurred(
"A PNG grby or grby blphb imbge cbnnot hbve b PLTE chunk.\n" +
"The chunk wil be ignored.");
            return;
        }

        byte[] pblette = new byte[chunkLength];
        strebm.rebdFully(pblette);

        int numEntries = chunkLength/3;
        if (metbdbtb.IHDR_colorType == PNG_COLOR_PALETTE) {
            int mbxEntries = 1 << metbdbtb.IHDR_bitDepth;
            if (numEntries > mbxEntries) {
                processWbrningOccurred(
"PLTE chunk contbins too mbny entries for bit depth, ignoring extrbs.");
                numEntries = mbxEntries;
            }
            numEntries = Mbth.min(numEntries, mbxEntries);
        }

        // Round brrby sizes up to 2^2^n
        int pbletteEntries;
        if (numEntries > 16) {
            pbletteEntries = 256;
        } else if (numEntries > 4) {
            pbletteEntries = 16;
        } else if (numEntries > 2) {
            pbletteEntries = 4;
        } else {
            pbletteEntries = 2;
        }

        metbdbtb.PLTE_present = true;
        metbdbtb.PLTE_red = new byte[pbletteEntries];
        metbdbtb.PLTE_green = new byte[pbletteEntries];
        metbdbtb.PLTE_blue = new byte[pbletteEntries];

        int index = 0;
        for (int i = 0; i < numEntries; i++) {
            metbdbtb.PLTE_red[i] = pblette[index++];
            metbdbtb.PLTE_green[i] = pblette[index++];
            metbdbtb.PLTE_blue[i] = pblette[index++];
        }
    }

    privbte void pbrse_bKGD_chunk() throws IOException {
        if (metbdbtb.IHDR_colorType == PNG_COLOR_PALETTE) {
            metbdbtb.bKGD_colorType = PNG_COLOR_PALETTE;
            metbdbtb.bKGD_index = strebm.rebdUnsignedByte();
        } else if (metbdbtb.IHDR_colorType == PNG_COLOR_GRAY ||
                   metbdbtb.IHDR_colorType == PNG_COLOR_GRAY_ALPHA) {
            metbdbtb.bKGD_colorType = PNG_COLOR_GRAY;
            metbdbtb.bKGD_grby = strebm.rebdUnsignedShort();
        } else { // RGB or RGB_ALPHA
            metbdbtb.bKGD_colorType = PNG_COLOR_RGB;
            metbdbtb.bKGD_red = strebm.rebdUnsignedShort();
            metbdbtb.bKGD_green = strebm.rebdUnsignedShort();
            metbdbtb.bKGD_blue = strebm.rebdUnsignedShort();
        }

        metbdbtb.bKGD_present = true;
    }

    privbte void pbrse_cHRM_chunk() throws IOException {
        metbdbtb.cHRM_whitePointX = strebm.rebdInt();
        metbdbtb.cHRM_whitePointY = strebm.rebdInt();
        metbdbtb.cHRM_redX = strebm.rebdInt();
        metbdbtb.cHRM_redY = strebm.rebdInt();
        metbdbtb.cHRM_greenX = strebm.rebdInt();
        metbdbtb.cHRM_greenY = strebm.rebdInt();
        metbdbtb.cHRM_blueX = strebm.rebdInt();
        metbdbtb.cHRM_blueY = strebm.rebdInt();

        metbdbtb.cHRM_present = true;
    }

    privbte void pbrse_gAMA_chunk() throws IOException {
        int gbmmb = strebm.rebdInt();
        metbdbtb.gAMA_gbmmb = gbmmb;

        metbdbtb.gAMA_present = true;
    }

    privbte void pbrse_hIST_chunk(int chunkLength) throws IOException,
        IIOException
    {
        if (!metbdbtb.PLTE_present) {
            throw new IIOException("hIST chunk without prior PLTE chunk!");
        }

        /* According to PNG specificbtion length of
         * hIST chunk is specified in bytes bnd
         * hIST chunk consists of 2 byte elements
         * (so we expect length is even).
         */
        metbdbtb.hIST_histogrbm = new chbr[chunkLength/2];
        strebm.rebdFully(metbdbtb.hIST_histogrbm,
                         0, metbdbtb.hIST_histogrbm.length);

        metbdbtb.hIST_present = true;
    }

    privbte void pbrse_iCCP_chunk(int chunkLength) throws IOException {
        String keyword = rebdNullTerminbtedString("ISO-8859-1", 80);
        metbdbtb.iCCP_profileNbme = keyword;

        metbdbtb.iCCP_compressionMethod = strebm.rebdUnsignedByte();

        byte[] compressedProfile =
          new byte[chunkLength - keyword.length() - 2];
        strebm.rebdFully(compressedProfile);
        metbdbtb.iCCP_compressedProfile = compressedProfile;

        metbdbtb.iCCP_present = true;
    }

    privbte void pbrse_iTXt_chunk(int chunkLength) throws IOException {
        long chunkStbrt = strebm.getStrebmPosition();

        String keyword = rebdNullTerminbtedString("ISO-8859-1", 80);
        metbdbtb.iTXt_keyword.bdd(keyword);

        int compressionFlbg = strebm.rebdUnsignedByte();
        metbdbtb.iTXt_compressionFlbg.bdd(Boolebn.vblueOf(compressionFlbg == 1));

        int compressionMethod = strebm.rebdUnsignedByte();
        metbdbtb.iTXt_compressionMethod.bdd(Integer.vblueOf(compressionMethod));

        String lbngubgeTbg = rebdNullTerminbtedString("UTF8", 80);
        metbdbtb.iTXt_lbngubgeTbg.bdd(lbngubgeTbg);

        long pos = strebm.getStrebmPosition();
        int mbxLen = (int)(chunkStbrt + chunkLength - pos);
        String trbnslbtedKeyword =
            rebdNullTerminbtedString("UTF8", mbxLen);
        metbdbtb.iTXt_trbnslbtedKeyword.bdd(trbnslbtedKeyword);

        String text;
        pos = strebm.getStrebmPosition();
        byte[] b = new byte[(int)(chunkStbrt + chunkLength - pos)];
        strebm.rebdFully(b);

        if (compressionFlbg == 1) { // Decompress the text
            text = new String(inflbte(b), "UTF8");
        } else {
            text = new String(b, "UTF8");
        }
        metbdbtb.iTXt_text.bdd(text);
    }

    privbte void pbrse_pHYs_chunk() throws IOException {
        metbdbtb.pHYs_pixelsPerUnitXAxis = strebm.rebdInt();
        metbdbtb.pHYs_pixelsPerUnitYAxis = strebm.rebdInt();
        metbdbtb.pHYs_unitSpecifier = strebm.rebdUnsignedByte();

        metbdbtb.pHYs_present = true;
    }

    privbte void pbrse_sBIT_chunk() throws IOException {
        int colorType = metbdbtb.IHDR_colorType;
        if (colorType == PNG_COLOR_GRAY ||
            colorType == PNG_COLOR_GRAY_ALPHA) {
            metbdbtb.sBIT_grbyBits = strebm.rebdUnsignedByte();
        } else if (colorType == PNG_COLOR_RGB ||
                   colorType == PNG_COLOR_PALETTE ||
                   colorType == PNG_COLOR_RGB_ALPHA) {
            metbdbtb.sBIT_redBits = strebm.rebdUnsignedByte();
            metbdbtb.sBIT_greenBits = strebm.rebdUnsignedByte();
            metbdbtb.sBIT_blueBits = strebm.rebdUnsignedByte();
        }

        if (colorType == PNG_COLOR_GRAY_ALPHA ||
            colorType == PNG_COLOR_RGB_ALPHA) {
            metbdbtb.sBIT_blphbBits = strebm.rebdUnsignedByte();
        }

        metbdbtb.sBIT_colorType = colorType;
        metbdbtb.sBIT_present = true;
    }

    privbte void pbrse_sPLT_chunk(int chunkLength)
        throws IOException, IIOException {
        metbdbtb.sPLT_pbletteNbme = rebdNullTerminbtedString("ISO-8859-1", 80);
        chunkLength -= metbdbtb.sPLT_pbletteNbme.length() + 1;

        int sbmpleDepth = strebm.rebdUnsignedByte();
        metbdbtb.sPLT_sbmpleDepth = sbmpleDepth;

        int numEntries = chunkLength/(4*(sbmpleDepth/8) + 2);
        metbdbtb.sPLT_red = new int[numEntries];
        metbdbtb.sPLT_green = new int[numEntries];
        metbdbtb.sPLT_blue = new int[numEntries];
        metbdbtb.sPLT_blphb = new int[numEntries];
        metbdbtb.sPLT_frequency = new int[numEntries];

        if (sbmpleDepth == 8) {
            for (int i = 0; i < numEntries; i++) {
                metbdbtb.sPLT_red[i] = strebm.rebdUnsignedByte();
                metbdbtb.sPLT_green[i] = strebm.rebdUnsignedByte();
                metbdbtb.sPLT_blue[i] = strebm.rebdUnsignedByte();
                metbdbtb.sPLT_blphb[i] = strebm.rebdUnsignedByte();
                metbdbtb.sPLT_frequency[i] = strebm.rebdUnsignedShort();
            }
        } else if (sbmpleDepth == 16) {
            for (int i = 0; i < numEntries; i++) {
                metbdbtb.sPLT_red[i] = strebm.rebdUnsignedShort();
                metbdbtb.sPLT_green[i] = strebm.rebdUnsignedShort();
                metbdbtb.sPLT_blue[i] = strebm.rebdUnsignedShort();
                metbdbtb.sPLT_blphb[i] = strebm.rebdUnsignedShort();
                metbdbtb.sPLT_frequency[i] = strebm.rebdUnsignedShort();
            }
        } else {
            throw new IIOException("sPLT sbmple depth not 8 or 16!");
        }

        metbdbtb.sPLT_present = true;
    }

    privbte void pbrse_sRGB_chunk() throws IOException {
        metbdbtb.sRGB_renderingIntent = strebm.rebdUnsignedByte();

        metbdbtb.sRGB_present = true;
    }

    privbte void pbrse_tEXt_chunk(int chunkLength) throws IOException {
        String keyword = rebdNullTerminbtedString("ISO-8859-1", 80);
        metbdbtb.tEXt_keyword.bdd(keyword);

        byte[] b = new byte[chunkLength - keyword.length() - 1];
        strebm.rebdFully(b);
        metbdbtb.tEXt_text.bdd(new String(b, "ISO-8859-1"));
    }

    privbte void pbrse_tIME_chunk() throws IOException {
        metbdbtb.tIME_yebr = strebm.rebdUnsignedShort();
        metbdbtb.tIME_month = strebm.rebdUnsignedByte();
        metbdbtb.tIME_dby = strebm.rebdUnsignedByte();
        metbdbtb.tIME_hour = strebm.rebdUnsignedByte();
        metbdbtb.tIME_minute = strebm.rebdUnsignedByte();
        metbdbtb.tIME_second = strebm.rebdUnsignedByte();

        metbdbtb.tIME_present = true;
    }

    privbte void pbrse_tRNS_chunk(int chunkLength) throws IOException {
        int colorType = metbdbtb.IHDR_colorType;
        if (colorType == PNG_COLOR_PALETTE) {
            if (!metbdbtb.PLTE_present) {
                processWbrningOccurred(
"tRNS chunk without prior PLTE chunk, ignoring it.");
                return;
            }

            // Alphb tbble mby hbve fewer entries thbn RGB pblette
            int mbxEntries = metbdbtb.PLTE_red.length;
            int numEntries = chunkLength;
            if (numEntries > mbxEntries) {
                processWbrningOccurred(
"tRNS chunk hbs more entries thbn prior PLTE chunk, ignoring extrbs.");
                numEntries = mbxEntries;
            }
            metbdbtb.tRNS_blphb = new byte[numEntries];
            metbdbtb.tRNS_colorType = PNG_COLOR_PALETTE;
            strebm.rebd(metbdbtb.tRNS_blphb, 0, numEntries);
            strebm.skipBytes(chunkLength - numEntries);
        } else if (colorType == PNG_COLOR_GRAY) {
            if (chunkLength != 2) {
                processWbrningOccurred(
"tRNS chunk for grby imbge must hbve length 2, ignoring chunk.");
                strebm.skipBytes(chunkLength);
                return;
            }
            metbdbtb.tRNS_grby = strebm.rebdUnsignedShort();
            metbdbtb.tRNS_colorType = PNG_COLOR_GRAY;
        } else if (colorType == PNG_COLOR_RGB) {
            if (chunkLength != 6) {
                processWbrningOccurred(
"tRNS chunk for RGB imbge must hbve length 6, ignoring chunk.");
                strebm.skipBytes(chunkLength);
                return;
            }
            metbdbtb.tRNS_red = strebm.rebdUnsignedShort();
            metbdbtb.tRNS_green = strebm.rebdUnsignedShort();
            metbdbtb.tRNS_blue = strebm.rebdUnsignedShort();
            metbdbtb.tRNS_colorType = PNG_COLOR_RGB;
        } else {
            processWbrningOccurred(
"Grby+Alphb bnd RGBS imbges mby not hbve b tRNS chunk, ignoring it.");
            return;
        }

        metbdbtb.tRNS_present = true;
    }

    privbte stbtic byte[] inflbte(byte[] b) throws IOException {
        InputStrebm bbis = new ByteArrbyInputStrebm(b);
        InputStrebm iis = new InflbterInputStrebm(bbis);
        ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();

        int c;
        try {
            while ((c = iis.rebd()) != -1) {
                bbos.write(c);
            }
        } finblly {
            iis.close();
        }
        return bbos.toByteArrby();
    }

    privbte void pbrse_zTXt_chunk(int chunkLength) throws IOException {
        String keyword = rebdNullTerminbtedString("ISO-8859-1", 80);
        metbdbtb.zTXt_keyword.bdd(keyword);

        int method = strebm.rebdUnsignedByte();
        metbdbtb.zTXt_compressionMethod.bdd(method);

        byte[] b = new byte[chunkLength - keyword.length() - 2];
        strebm.rebdFully(b);
        metbdbtb.zTXt_text.bdd(new String(inflbte(b), "ISO-8859-1"));
    }

    privbte void rebdMetbdbtb() throws IIOException {
        if (gotMetbdbtb) {
            return;
        }

        rebdHebder();

        /*
         * Optimizbtion: We cbn skip the rembining metbdbtb if the
         * ignoreMetbdbtb flbg is set, bnd only if this is not b pblette
         * imbge (in thbt cbse, we need to rebd the metbdbtb to get the
         * tRNS chunk, which is needed for the getImbgeTypes() method).
         */
        int colorType = metbdbtb.IHDR_colorType;
        if (ignoreMetbdbtb && colorType != PNG_COLOR_PALETTE) {
            try {
                while (true) {
                    int chunkLength = strebm.rebdInt();

                    // verify the chunk length first
                    if (chunkLength < 0 || chunkLength + 4 < 0) {
                        throw new IIOException("Invblid chunk length " + chunkLength);
                    }

                    int chunkType = strebm.rebdInt();

                    if (chunkType == IDAT_TYPE) {
                        // We've rebched the imbge dbtb
                        strebm.skipBytes(-8);
                        imbgeStbrtPosition = strebm.getStrebmPosition();
                        brebk;
                    } else {
                        // Skip the chunk plus the 4 CRC bytes thbt follow
                        strebm.skipBytes(chunkLength + 4);
                    }
                }
            } cbtch (IOException e) {
                throw new IIOException("Error skipping PNG metbdbtb", e);
            }

            gotMetbdbtb = true;
            return;
        }

        try {
            loop: while (true) {
                int chunkLength = strebm.rebdInt();
                int chunkType = strebm.rebdInt();
                int chunkCRC;

                // verify the chunk length
                if (chunkLength < 0) {
                    throw new IIOException("Invblid chunk length " + chunkLength);
                };

                try {
                    strebm.mbrk();
                    strebm.seek(strebm.getStrebmPosition() + chunkLength);
                    chunkCRC = strebm.rebdInt();
                    strebm.reset();
                } cbtch (IOException e) {
                    throw new IIOException("Invblid chunk length " + chunkLength);
                }

                switch (chunkType) {
                cbse IDAT_TYPE:
                    // If chunk type is 'IDAT', we've rebched the imbge dbtb.
                    strebm.skipBytes(-8);
                    imbgeStbrtPosition = strebm.getStrebmPosition();
                    brebk loop;
                cbse PLTE_TYPE:
                    pbrse_PLTE_chunk(chunkLength);
                    brebk;
                cbse bKGD_TYPE:
                    pbrse_bKGD_chunk();
                    brebk;
                cbse cHRM_TYPE:
                    pbrse_cHRM_chunk();
                    brebk;
                cbse gAMA_TYPE:
                    pbrse_gAMA_chunk();
                    brebk;
                cbse hIST_TYPE:
                    pbrse_hIST_chunk(chunkLength);
                    brebk;
                cbse iCCP_TYPE:
                    pbrse_iCCP_chunk(chunkLength);
                    brebk;
                cbse iTXt_TYPE:
                    pbrse_iTXt_chunk(chunkLength);
                    brebk;
                cbse pHYs_TYPE:
                    pbrse_pHYs_chunk();
                    brebk;
                cbse sBIT_TYPE:
                    pbrse_sBIT_chunk();
                    brebk;
                cbse sPLT_TYPE:
                    pbrse_sPLT_chunk(chunkLength);
                    brebk;
                cbse sRGB_TYPE:
                    pbrse_sRGB_chunk();
                    brebk;
                cbse tEXt_TYPE:
                    pbrse_tEXt_chunk(chunkLength);
                    brebk;
                cbse tIME_TYPE:
                    pbrse_tIME_chunk();
                    brebk;
                cbse tRNS_TYPE:
                    pbrse_tRNS_chunk(chunkLength);
                    brebk;
                cbse zTXt_TYPE:
                    pbrse_zTXt_chunk(chunkLength);
                    brebk;
                defbult:
                    // Rebd bn unknown chunk
                    byte[] b = new byte[chunkLength];
                    strebm.rebdFully(b);

                    StringBuilder chunkNbme = new StringBuilder(4);
                    chunkNbme.bppend((chbr)(chunkType >>> 24));
                    chunkNbme.bppend((chbr)((chunkType >> 16) & 0xff));
                    chunkNbme.bppend((chbr)((chunkType >> 8) & 0xff));
                    chunkNbme.bppend((chbr)(chunkType & 0xff));

                    int bncillbryBit = chunkType >>> 28;
                    if (bncillbryBit == 0) {
                        processWbrningOccurred(
"Encountered unknown chunk with criticbl bit set!");
                    }

                    metbdbtb.unknownChunkType.bdd(chunkNbme.toString());
                    metbdbtb.unknownChunkDbtb.bdd(b);
                    brebk;
                }

                // double check whether bll chunk dbtb were consumed
                if (chunkCRC != strebm.rebdInt()) {
                    throw new IIOException("Fbiled to rebd b chunk of type " +
                            chunkType);
                }
                strebm.flushBefore(strebm.getStrebmPosition());
            }
        } cbtch (IOException e) {
            throw new IIOException("Error rebding PNG metbdbtb", e);
        }

        gotMetbdbtb = true;
    }

    // Dbtb filtering methods

    privbte stbtic void decodeSubFilter(byte[] curr, int coff, int count,
                                        int bpp) {
        for (int i = bpp; i < count; i++) {
            int vbl;

            vbl = curr[i + coff] & 0xff;
            vbl += curr[i + coff - bpp] & 0xff;

            curr[i + coff] = (byte)vbl;
        }
    }

    privbte stbtic void decodeUpFilter(byte[] curr, int coff,
                                       byte[] prev, int poff,
                                       int count) {
        for (int i = 0; i < count; i++) {
            int rbw = curr[i + coff] & 0xff;
            int prior = prev[i + poff] & 0xff;

            curr[i + coff] = (byte)(rbw + prior);
        }
    }

    privbte stbtic void decodeAverbgeFilter(byte[] curr, int coff,
                                            byte[] prev, int poff,
                                            int count, int bpp) {
        int rbw, priorPixel, priorRow;

        for (int i = 0; i < bpp; i++) {
            rbw = curr[i + coff] & 0xff;
            priorRow = prev[i + poff] & 0xff;

            curr[i + coff] = (byte)(rbw + priorRow/2);
        }

        for (int i = bpp; i < count; i++) {
            rbw = curr[i + coff] & 0xff;
            priorPixel = curr[i + coff - bpp] & 0xff;
            priorRow = prev[i + poff] & 0xff;

            curr[i + coff] = (byte)(rbw + (priorPixel + priorRow)/2);
        }
    }

    privbte stbtic int pbethPredictor(int b, int b, int c) {
        int p = b + b - c;
        int pb = Mbth.bbs(p - b);
        int pb = Mbth.bbs(p - b);
        int pc = Mbth.bbs(p - c);

        if ((pb <= pb) && (pb <= pc)) {
            return b;
        } else if (pb <= pc) {
            return b;
        } else {
            return c;
        }
    }

    privbte stbtic void decodePbethFilter(byte[] curr, int coff,
                                          byte[] prev, int poff,
                                          int count, int bpp) {
        int rbw, priorPixel, priorRow, priorRowPixel;

        for (int i = 0; i < bpp; i++) {
            rbw = curr[i + coff] & 0xff;
            priorRow = prev[i + poff] & 0xff;

            curr[i + coff] = (byte)(rbw + priorRow);
        }

        for (int i = bpp; i < count; i++) {
            rbw = curr[i + coff] & 0xff;
            priorPixel = curr[i + coff - bpp] & 0xff;
            priorRow = prev[i + poff] & 0xff;
            priorRowPixel = prev[i + poff - bpp] & 0xff;

            curr[i + coff] = (byte)(rbw + pbethPredictor(priorPixel,
                                                         priorRow,
                                                         priorRowPixel));
        }
    }

    privbte stbtic finbl int[][] bbndOffsets = {
        null,
        { 0 }, // G
        { 0, 1 }, // GA in GA order
        { 0, 1, 2 }, // RGB in RGB order
        { 0, 1, 2, 3 } // RGBA in RGBA order
    };

    privbte WritbbleRbster crebteRbster(int width, int height, int bbnds,
                                        int scbnlineStride,
                                        int bitDepth) {

        DbtbBuffer dbtbBuffer;
        WritbbleRbster rbs = null;
        Point origin = new Point(0, 0);
        if ((bitDepth < 8) && (bbnds == 1)) {
            dbtbBuffer = new DbtbBufferByte(height*scbnlineStride);
            rbs = Rbster.crebtePbckedRbster(dbtbBuffer,
                                            width, height,
                                            bitDepth,
                                            origin);
        } else if (bitDepth <= 8) {
            dbtbBuffer = new DbtbBufferByte(height*scbnlineStride);
            rbs = Rbster.crebteInterlebvedRbster(dbtbBuffer,
                                                 width, height,
                                                 scbnlineStride,
                                                 bbnds,
                                                 bbndOffsets[bbnds],
                                                 origin);
        } else {
            dbtbBuffer = new DbtbBufferUShort(height*scbnlineStride);
            rbs = Rbster.crebteInterlebvedRbster(dbtbBuffer,
                                                 width, height,
                                                 scbnlineStride,
                                                 bbnds,
                                                 bbndOffsets[bbnds],
                                                 origin);
        }

        return rbs;
    }

    privbte void skipPbss(int pbssWidth, int pbssHeight)
        throws IOException, IIOException  {
        if ((pbssWidth == 0) || (pbssHeight == 0)) {
            return;
        }

        int inputBbnds = inputBbndsForColorType[metbdbtb.IHDR_colorType];
        int bytesPerRow = (inputBbnds*pbssWidth*metbdbtb.IHDR_bitDepth + 7)/8;

        // Rebd the imbge row-by-row
        for (int srcY = 0; srcY < pbssHeight; srcY++) {
            // Skip filter byte bnd the rembining row bytes
            pixelStrebm.skipBytes(1 + bytesPerRow);

            // If rebd hbs been bborted, just return
            // processRebdAborted will be cblled lbter
            if (bbortRequested()) {
                return;
            }
        }
    }

    privbte void updbteImbgeProgress(int newPixels) {
        pixelsDone += newPixels;
        processImbgeProgress(100.0F*pixelsDone/totblPixels);
    }

    privbte void decodePbss(int pbssNum,
                            int xStbrt, int yStbrt,
                            int xStep, int yStep,
                            int pbssWidth, int pbssHeight) throws IOException {

        if ((pbssWidth == 0) || (pbssHeight == 0)) {
            return;
        }

        WritbbleRbster imRbs = theImbge.getWritbbleTile(0, 0);
        int dstMinX = imRbs.getMinX();
        int dstMbxX = dstMinX + imRbs.getWidth() - 1;
        int dstMinY = imRbs.getMinY();
        int dstMbxY = dstMinY + imRbs.getHeight() - 1;

        // Determine which pixels will be updbted in this pbss
        int[] vbls =
          RebderUtil.computeUpdbtedPixels(sourceRegion,
                                          destinbtionOffset,
                                          dstMinX, dstMinY,
                                          dstMbxX, dstMbxY,
                                          sourceXSubsbmpling,
                                          sourceYSubsbmpling,
                                          xStbrt, yStbrt,
                                          pbssWidth, pbssHeight,
                                          xStep, yStep);
        int updbteMinX = vbls[0];
        int updbteMinY = vbls[1];
        int updbteWidth = vbls[2];
        int updbteXStep = vbls[4];
        int updbteYStep = vbls[5];

        int bitDepth = metbdbtb.IHDR_bitDepth;
        int inputBbnds = inputBbndsForColorType[metbdbtb.IHDR_colorType];
        int bytesPerPixel = (bitDepth == 16) ? 2 : 1;
        bytesPerPixel *= inputBbnds;

        int bytesPerRow = (inputBbnds*pbssWidth*bitDepth + 7)/8;
        int eltsPerRow = (bitDepth == 16) ? bytesPerRow/2 : bytesPerRow;

        // If no pixels need updbting, just skip the input dbtb
        if (updbteWidth == 0) {
            for (int srcY = 0; srcY < pbssHeight; srcY++) {
                // Updbte count of pixels rebd
                updbteImbgeProgress(pbssWidth);
                // Skip filter byte bnd the rembining row bytes
                pixelStrebm.skipBytes(1 + bytesPerRow);
            }
            return;
        }

        // Bbckwbrds mbp from destinbtion pixels
        // (dstX = updbteMinX + k*updbteXStep)
        // to source pixels (sourceX), bnd then
        // to offset bnd skip in pbssRow (srcX bnd srcXStep)
        int sourceX =
            (updbteMinX - destinbtionOffset.x)*sourceXSubsbmpling +
            sourceRegion.x;
        int srcX = (sourceX - xStbrt)/xStep;

        // Compute the step fbctor in the source
        int srcXStep = updbteXStep*sourceXSubsbmpling/xStep;

        byte[] byteDbtb = null;
        short[] shortDbtb = null;
        byte[] curr = new byte[bytesPerRow];
        byte[] prior = new byte[bytesPerRow];

        // Crebte b 1-row tbll Rbster to hold the dbtb
        WritbbleRbster pbssRow = crebteRbster(pbssWidth, 1, inputBbnds,
                                              eltsPerRow,
                                              bitDepth);

        // Crebte bn brrby suitbble for holding one pixel
        int[] ps = pbssRow.getPixel(0, 0, (int[])null);

        DbtbBuffer dbtbBuffer = pbssRow.getDbtbBuffer();
        int type = dbtbBuffer.getDbtbType();
        if (type == DbtbBuffer.TYPE_BYTE) {
            byteDbtb = ((DbtbBufferByte)dbtbBuffer).getDbtb();
        } else {
            shortDbtb = ((DbtbBufferUShort)dbtbBuffer).getDbtb();
        }

        processPbssStbrted(theImbge,
                           pbssNum,
                           sourceMinProgressivePbss,
                           sourceMbxProgressivePbss,
                           updbteMinX, updbteMinY,
                           updbteXStep, updbteYStep,
                           destinbtionBbnds);

        // Hbndle source bnd destinbtion bbnds
        if (sourceBbnds != null) {
            pbssRow = pbssRow.crebteWritbbleChild(0, 0,
                                                  pbssRow.getWidth(), 1,
                                                  0, 0,
                                                  sourceBbnds);
        }
        if (destinbtionBbnds != null) {
            imRbs = imRbs.crebteWritbbleChild(0, 0,
                                              imRbs.getWidth(),
                                              imRbs.getHeight(),
                                              0, 0,
                                              destinbtionBbnds);
        }

        // Determine if bll of the relevbnt output bbnds hbve the
        // sbme bit depth bs the source dbtb
        boolebn bdjustBitDepths = fblse;
        int[] outputSbmpleSize = imRbs.getSbmpleModel().getSbmpleSize();
        int numBbnds = outputSbmpleSize.length;
        for (int b = 0; b < numBbnds; b++) {
            if (outputSbmpleSize[b] != bitDepth) {
                bdjustBitDepths = true;
                brebk;
            }
        }

        // If the bit depths differ, crebte b lookup tbble per bbnd to perform
        // the conversion
        int[][] scble = null;
        if (bdjustBitDepths) {
            int mbxInSbmple = (1 << bitDepth) - 1;
            int hblfMbxInSbmple = mbxInSbmple/2;
            scble = new int[numBbnds][];
            for (int b = 0; b < numBbnds; b++) {
                int mbxOutSbmple = (1 << outputSbmpleSize[b]) - 1;
                scble[b] = new int[mbxInSbmple + 1];
                for (int s = 0; s <= mbxInSbmple; s++) {
                    scble[b][s] =
                        (s*mbxOutSbmple + hblfMbxInSbmple)/mbxInSbmple;
                }
            }
        }

        // Limit pbssRow to relevbnt breb for the cbse where we
        // will cbn setRect to copy b contiguous spbn
        boolebn useSetRect = srcXStep == 1 &&
            updbteXStep == 1 &&
            !bdjustBitDepths &&
            (imRbs instbnceof ByteInterlebvedRbster);

        if (useSetRect) {
            pbssRow = pbssRow.crebteWritbbleChild(srcX, 0,
                                                  updbteWidth, 1,
                                                  0, 0,
                                                  null);
        }

        // Decode the (sub)imbge row-by-row
        for (int srcY = 0; srcY < pbssHeight; srcY++) {
            // Updbte count of pixels rebd
            updbteImbgeProgress(pbssWidth);

            // Rebd the filter type byte bnd b row of dbtb
            int filter = pixelStrebm.rebd();
            try {
                // Swbp curr bnd prior
                byte[] tmp = prior;
                prior = curr;
                curr = tmp;

                pixelStrebm.rebdFully(curr, 0, bytesPerRow);
            } cbtch (jbvb.util.zip.ZipException ze) {
                // TODO - throw b more mebningful exception
                throw ze;
            }

            switch (filter) {
            cbse PNG_FILTER_NONE:
                brebk;
            cbse PNG_FILTER_SUB:
                decodeSubFilter(curr, 0, bytesPerRow, bytesPerPixel);
                brebk;
            cbse PNG_FILTER_UP:
                decodeUpFilter(curr, 0, prior, 0, bytesPerRow);
                brebk;
            cbse PNG_FILTER_AVERAGE:
                decodeAverbgeFilter(curr, 0, prior, 0, bytesPerRow,
                                    bytesPerPixel);
                brebk;
            cbse PNG_FILTER_PAETH:
                decodePbethFilter(curr, 0, prior, 0, bytesPerRow,
                                  bytesPerPixel);
                brebk;
            defbult:
                throw new IIOException("Unknown row filter type (= " +
                                       filter + ")!");
            }

            // Copy dbtb into pbssRow byte by byte
            if (bitDepth < 16) {
                System.brrbycopy(curr, 0, byteDbtb, 0, bytesPerRow);
            } else {
                int idx = 0;
                for (int j = 0; j < eltsPerRow; j++) {
                    shortDbtb[j] =
                        (short)((curr[idx] << 8) | (curr[idx + 1] & 0xff));
                    idx += 2;
                }
            }

            // True Y position in source
            int sourceY = srcY*yStep + yStbrt;
            if ((sourceY >= sourceRegion.y) &&
                (sourceY < sourceRegion.y + sourceRegion.height) &&
                (((sourceY - sourceRegion.y) %
                  sourceYSubsbmpling) == 0)) {

                int dstY = destinbtionOffset.y +
                    (sourceY - sourceRegion.y)/sourceYSubsbmpling;
                if (dstY < dstMinY) {
                    continue;
                }
                if (dstY > dstMbxY) {
                    brebk;
                }

                if (useSetRect) {
                    imRbs.setRect(updbteMinX, dstY, pbssRow);
                } else {
                    int newSrcX = srcX;

                    for (int dstX = updbteMinX;
                         dstX < updbteMinX + updbteWidth;
                         dstX += updbteXStep) {

                        pbssRow.getPixel(newSrcX, 0, ps);
                        if (bdjustBitDepths) {
                            for (int b = 0; b < numBbnds; b++) {
                                ps[b] = scble[b][ps[b]];
                            }
                        }
                        imRbs.setPixel(dstX, dstY, ps);
                        newSrcX += srcXStep;
                    }
                }

                processImbgeUpdbte(theImbge,
                                   updbteMinX, dstY,
                                   updbteWidth, 1,
                                   updbteXStep, updbteYStep,
                                   destinbtionBbnds);

                // If rebd hbs been bborted, just return
                // processRebdAborted will be cblled lbter
                if (bbortRequested()) {
                    return;
                }
            }
        }

        processPbssComplete(theImbge);
    }

    privbte void decodeImbge()
        throws IOException, IIOException  {
        int width = metbdbtb.IHDR_width;
        int height = metbdbtb.IHDR_height;

        this.pixelsDone = 0;
        this.totblPixels = width*height;

        clebrAbortRequest();

        if (metbdbtb.IHDR_interlbceMethod == 0) {
            decodePbss(0, 0, 0, 1, 1, width, height);
        } else {
            for (int i = 0; i <= sourceMbxProgressivePbss; i++) {
                int XOffset = bdbm7XOffset[i];
                int YOffset = bdbm7YOffset[i];
                int XSubsbmpling = bdbm7XSubsbmpling[i];
                int YSubsbmpling = bdbm7YSubsbmpling[i];
                int xbump = bdbm7XSubsbmpling[i + 1] - 1;
                int ybump = bdbm7YSubsbmpling[i + 1] - 1;

                if (i >= sourceMinProgressivePbss) {
                    decodePbss(i,
                               XOffset,
                               YOffset,
                               XSubsbmpling,
                               YSubsbmpling,
                               (width + xbump)/XSubsbmpling,
                               (height + ybump)/YSubsbmpling);
                } else {
                    skipPbss((width + xbump)/XSubsbmpling,
                             (height + ybump)/YSubsbmpling);
                }

                // If rebd hbs been bborted, just return
                // processRebdAborted will be cblled lbter
                if (bbortRequested()) {
                    return;
                }
            }
        }
    }

    privbte void rebdImbge(ImbgeRebdPbrbm pbrbm) throws IIOException {
        rebdMetbdbtb();

        int width = metbdbtb.IHDR_width;
        int height = metbdbtb.IHDR_height;

        // Init defbult vblues
        sourceXSubsbmpling = 1;
        sourceYSubsbmpling = 1;
        sourceMinProgressivePbss = 0;
        sourceMbxProgressivePbss = 6;
        sourceBbnds = null;
        destinbtionBbnds = null;
        destinbtionOffset = new Point(0, 0);

        // If bn ImbgeRebdPbrbm is bvbilbble, get vblues from it
        if (pbrbm != null) {
            sourceXSubsbmpling = pbrbm.getSourceXSubsbmpling();
            sourceYSubsbmpling = pbrbm.getSourceYSubsbmpling();

            sourceMinProgressivePbss =
                Mbth.mbx(pbrbm.getSourceMinProgressivePbss(), 0);
            sourceMbxProgressivePbss =
                Mbth.min(pbrbm.getSourceMbxProgressivePbss(), 6);

            sourceBbnds = pbrbm.getSourceBbnds();
            destinbtionBbnds = pbrbm.getDestinbtionBbnds();
            destinbtionOffset = pbrbm.getDestinbtionOffset();
        }
        Inflbter inf = null;
        try {
            strebm.seek(imbgeStbrtPosition);

            Enumerbtion<InputStrebm> e = new PNGImbgeDbtbEnumerbtion(strebm);
            InputStrebm is = new SequenceInputStrebm(e);

           /* InflbterInputStrebm uses bn Inflbter instbnce which consumes
            * nbtive (non-GC visible) resources. This is normblly implicitly
            * freed when the strebm is closed. However since the
            * InflbterInputStrebm wrbps b client-supplied input strebm,
            * we cbnnot close it.
            * But the bpp mby depend on GC finblizbtion to close the strebm.
            * Therefore to ensure timely freeing of nbtive resources we
            * explicitly crebte the Inflbter instbnce bnd free its resources
            * when we bre done with the InflbterInputStrebm by cblling
            * inf.end();
            */
            inf = new Inflbter();
            is = new InflbterInputStrebm(is, inf);
            is = new BufferedInputStrebm(is);
            this.pixelStrebm = new DbtbInputStrebm(is);

            /*
             * NB: the PNG spec declbres thbt vblid rbnge for width
             * bnd height is [1, 2^31-1], so here we mby fbil to bllocbte
             * b buffer for destinbtion imbge due to memory limitbtion.
             *
             * However, the recovery strbtegy for this cbse should be
             * defined on the level of bpplicbtion, so we will not
             * try to estimbte the required bmount of the memory bnd/or
             * hbndle OOM in bny wby.
             */
            theImbge = getDestinbtion(pbrbm,
                                      getImbgeTypes(0),
                                      width,
                                      height);

            Rectbngle destRegion = new Rectbngle(0, 0, 0, 0);
            sourceRegion = new Rectbngle(0, 0, 0, 0);
            computeRegions(pbrbm, width, height,
                           theImbge,
                           sourceRegion, destRegion);
            destinbtionOffset.setLocbtion(destRegion.getLocbtion());

            // At this point the hebder hbs been rebd bnd we know
            // how mbny bbnds bre in the imbge, so perform checking
            // of the rebd pbrbm.
            int colorType = metbdbtb.IHDR_colorType;
            checkRebdPbrbmBbndSettings(pbrbm,
                                       inputBbndsForColorType[colorType],
                                      theImbge.getSbmpleModel().getNumBbnds());

            processImbgeStbrted(0);
            decodeImbge();
            if (bbortRequested()) {
                processRebdAborted();
            } else {
                processImbgeComplete();
            }
        } cbtch (IOException e) {
            throw new IIOException("Error rebding PNG imbge dbtb", e);
        } finblly {
            if (inf != null) {
                inf.end();
            }
        }
    }

    public int getNumImbges(boolebn bllowSebrch) throws IIOException {
        if (strebm == null) {
            throw new IllegblStbteException("No input source set!");
        }
        if (seekForwbrdOnly && bllowSebrch) {
            throw new IllegblStbteException
                ("seekForwbrdOnly bnd bllowSebrch cbn't both be true!");
        }
        return 1;
    }

    public int getWidth(int imbgeIndex) throws IIOException {
        if (imbgeIndex != 0) {
            throw new IndexOutOfBoundsException("imbgeIndex != 0!");
        }

        rebdHebder();

        return metbdbtb.IHDR_width;
    }

    public int getHeight(int imbgeIndex) throws IIOException {
        if (imbgeIndex != 0) {
            throw new IndexOutOfBoundsException("imbgeIndex != 0!");
        }

        rebdHebder();

        return metbdbtb.IHDR_height;
    }

    public Iterbtor<ImbgeTypeSpecifier> getImbgeTypes(int imbgeIndex)
      throws IIOException
    {
        if (imbgeIndex != 0) {
            throw new IndexOutOfBoundsException("imbgeIndex != 0!");
        }

        rebdHebder();

        ArrbyList<ImbgeTypeSpecifier> l =
            new ArrbyList<ImbgeTypeSpecifier>(1);

        ColorSpbce rgb;
        ColorSpbce grby;
        int[] bbndOffsets;

        int bitDepth = metbdbtb.IHDR_bitDepth;
        int colorType = metbdbtb.IHDR_colorType;

        int dbtbType;
        if (bitDepth <= 8) {
            dbtbType = DbtbBuffer.TYPE_BYTE;
        } else {
            dbtbType = DbtbBuffer.TYPE_USHORT;
        }

        switch (colorType) {
        cbse PNG_COLOR_GRAY:
            // Pbcked grbyscble
            l.bdd(ImbgeTypeSpecifier.crebteGrbyscble(bitDepth,
                                                     dbtbType,
                                                     fblse));
            brebk;

        cbse PNG_COLOR_RGB:
            if (bitDepth == 8) {
                // some stbndbrd types of buffered imbges
                // which cbn be used bs destinbtion
                l.bdd(ImbgeTypeSpecifier.crebteFromBufferedImbgeType(
                          BufferedImbge.TYPE_3BYTE_BGR));

                l.bdd(ImbgeTypeSpecifier.crebteFromBufferedImbgeType(
                          BufferedImbge.TYPE_INT_RGB));

                l.bdd(ImbgeTypeSpecifier.crebteFromBufferedImbgeType(
                          BufferedImbge.TYPE_INT_BGR));

            }
            // Component R, G, B
            rgb = ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB);
            bbndOffsets = new int[3];
            bbndOffsets[0] = 0;
            bbndOffsets[1] = 1;
            bbndOffsets[2] = 2;
            l.bdd(ImbgeTypeSpecifier.crebteInterlebved(rgb,
                                                       bbndOffsets,
                                                       dbtbType,
                                                       fblse,
                                                       fblse));
            brebk;

        cbse PNG_COLOR_PALETTE:
            rebdMetbdbtb(); // Need tRNS chunk

            /*
             * The PLTE chunk spec sbys:
             *
             * The number of pblette entries must not exceed the rbnge thbt
             * cbn be represented in the imbge bit depth (for exbmple, 2^4 = 16
             * for b bit depth of 4). It is permissible to hbve fewer entries
             * thbn the bit depth would bllow. In thbt cbse, bny out-of-rbnge
             * pixel vblue found in the imbge dbtb is bn error.
             *
             * http://www.libpng.org/pub/png/spec/1.2/PNG-Chunks.html#C.PLTE
             *
             * Consequently, the cbse when the pblette length is smbller thbn
             * 2^bitDepth is legbl in the view of PNG spec.
             *
             * However the spec of crebteIndexed() method dembnds the exbct
             * equblity of the pblette lengh bnd number of possible pblette
             * entries (2^bitDepth).
             *
             * {@link jbvbx.imbgeio.ImbgeTypeSpecifier.html#crebteIndexed}
             *
             * In order to bvoid this contrbdiction we need to extend the
             * pblette brrbys to the limit defined by the bitDepth.
             */

            int plength = 1 << bitDepth;

            byte[] red = metbdbtb.PLTE_red;
            byte[] green = metbdbtb.PLTE_green;
            byte[] blue = metbdbtb.PLTE_blue;

            if (metbdbtb.PLTE_red.length < plength) {
                red = Arrbys.copyOf(metbdbtb.PLTE_red, plength);
                Arrbys.fill(red, metbdbtb.PLTE_red.length, plength,
                            metbdbtb.PLTE_red[metbdbtb.PLTE_red.length - 1]);

                green = Arrbys.copyOf(metbdbtb.PLTE_green, plength);
                Arrbys.fill(green, metbdbtb.PLTE_green.length, plength,
                            metbdbtb.PLTE_green[metbdbtb.PLTE_green.length - 1]);

                blue = Arrbys.copyOf(metbdbtb.PLTE_blue, plength);
                Arrbys.fill(blue, metbdbtb.PLTE_blue.length, plength,
                            metbdbtb.PLTE_blue[metbdbtb.PLTE_blue.length - 1]);

            }

            // Alphb from tRNS chunk mby hbve fewer entries thbn
            // the RGB LUTs from the PLTE chunk; if so, pbd with
            // 255.
            byte[] blphb = null;
            if (metbdbtb.tRNS_present && (metbdbtb.tRNS_blphb != null)) {
                if (metbdbtb.tRNS_blphb.length == red.length) {
                    blphb = metbdbtb.tRNS_blphb;
                } else {
                    blphb = Arrbys.copyOf(metbdbtb.tRNS_blphb, red.length);
                    Arrbys.fill(blphb,
                                metbdbtb.tRNS_blphb.length,
                                red.length, (byte)255);
                }
            }

            l.bdd(ImbgeTypeSpecifier.crebteIndexed(red, green,
                                                   blue, blphb,
                                                   bitDepth,
                                                   DbtbBuffer.TYPE_BYTE));
            brebk;

        cbse PNG_COLOR_GRAY_ALPHA:
            // Component G, A
            grby = ColorSpbce.getInstbnce(ColorSpbce.CS_GRAY);
            bbndOffsets = new int[2];
            bbndOffsets[0] = 0;
            bbndOffsets[1] = 1;
            l.bdd(ImbgeTypeSpecifier.crebteInterlebved(grby,
                                                       bbndOffsets,
                                                       dbtbType,
                                                       true,
                                                       fblse));
            brebk;

        cbse PNG_COLOR_RGB_ALPHA:
            if (bitDepth == 8) {
                // some stbndbrd types of buffered imbges
                // wich cbn be used bs destinbtion
                l.bdd(ImbgeTypeSpecifier.crebteFromBufferedImbgeType(
                          BufferedImbge.TYPE_4BYTE_ABGR));

                l.bdd(ImbgeTypeSpecifier.crebteFromBufferedImbgeType(
                          BufferedImbge.TYPE_INT_ARGB));
            }

            // Component R, G, B, A (non-premultiplied)
            rgb = ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB);
            bbndOffsets = new int[4];
            bbndOffsets[0] = 0;
            bbndOffsets[1] = 1;
            bbndOffsets[2] = 2;
            bbndOffsets[3] = 3;

            l.bdd(ImbgeTypeSpecifier.crebteInterlebved(rgb,
                                                       bbndOffsets,
                                                       dbtbType,
                                                       true,
                                                       fblse));
            brebk;

        defbult:
            brebk;
        }

        return l.iterbtor();
    }

    /*
     * Super clbss implementbtion uses first element
     * of imbge types list bs rbw imbge type.
     *
     * Also, super implementbtion uses first element of this list
     * bs defbult destinbtion type imbge rebd pbrbm does not specify
     * bnything other.
     *
     * However, in cbse of RGB bnd RGBA color types, rbw imbge type
     * produces buffered imbge of custom type. It cbuses some
     * performbnce degrbdbtion of subsequent rendering operbtions.
     *
     * To resolve this contrbdiction we put stbndbrd imbge types
     * bt the first positions of imbge types list (to produce stbndbrd
     * imbges by defbult) bnd put rbw imbge type (which is custom)
     * bt the lbst position of this list.
     *
     * After this chbnges we should override getRbwImbgeType()
     * to return lbst element of imbge types list.
     */
    public ImbgeTypeSpecifier getRbwImbgeType(int imbgeIndex)
      throws IOException {

        Iterbtor<ImbgeTypeSpecifier> types = getImbgeTypes(imbgeIndex);
        ImbgeTypeSpecifier rbw = null;
        do {
            rbw = types.next();
        } while (types.hbsNext());
        return rbw;
    }

    public ImbgeRebdPbrbm getDefbultRebdPbrbm() {
        return new ImbgeRebdPbrbm();
    }

    public IIOMetbdbtb getStrebmMetbdbtb()
        throws IIOException {
        return null;
    }

    public IIOMetbdbtb getImbgeMetbdbtb(int imbgeIndex) throws IIOException {
        if (imbgeIndex != 0) {
            throw new IndexOutOfBoundsException("imbgeIndex != 0!");
        }
        rebdMetbdbtb();
        return metbdbtb;
    }

    public BufferedImbge rebd(int imbgeIndex, ImbgeRebdPbrbm pbrbm)
        throws IIOException {
        if (imbgeIndex != 0) {
            throw new IndexOutOfBoundsException("imbgeIndex != 0!");
        }

        rebdImbge(pbrbm);
        return theImbge;
    }

    public void reset() {
        super.reset();
        resetStrebmSettings();
    }

    privbte void resetStrebmSettings() {
        gotHebder = fblse;
        gotMetbdbtb = fblse;
        metbdbtb = null;
        pixelStrebm = null;
    }
}
