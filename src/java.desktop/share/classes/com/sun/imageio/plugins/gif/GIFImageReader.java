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

pbckbge com.sun.imbgeio.plugins.gif;

import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.io.EOFException;
import jbvb.io.IOException;
import jbvb.nio.ByteOrder;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvbx.imbgeio.IIOException;
import jbvbx.imbgeio.ImbgeRebder;
import jbvbx.imbgeio.ImbgeRebdPbrbm;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtb;
import jbvbx.imbgeio.spi.ImbgeRebderSpi;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;
import com.sun.imbgeio.plugins.common.RebderUtil;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.MultiPixelPbckedSbmpleModel;
import jbvb.bwt.imbge.PixelInterlebvedSbmpleModel;
import jbvb.bwt.imbge.SbmpleModel;

public clbss GIFImbgeRebder extends ImbgeRebder {

    // The current ImbgeInputStrebm source.
    ImbgeInputStrebm strebm = null;

    // Per-strebm settings

    // True if the file hebder including strebm metbdbtb hbs been rebd.
    boolebn gotHebder = fblse;

    // Globbl metbdbtb, rebd once per input setting.
    GIFStrebmMetbdbtb strebmMetbdbtb = null;

    // The current imbge index
    int currIndex = -1;

    // Metbdbtb for imbge bt 'currIndex', or null.
    GIFImbgeMetbdbtb imbgeMetbdbtb = null;

    // A List of Longs indicbting the strebm positions of the
    // stbrt of the metbdbtb for ebch imbge.  Entries bre bdded
    // bs needed.
    List<Long> imbgeStbrtPosition = new ArrbyList<>();

    // Length of metbdbtb for imbge bt 'currIndex', vblid only if
    // imbgeMetbdbtb != null.
    int imbgeMetbdbtbLength;

    // The number of imbges in the strebm, if known, otherwise -1.
    int numImbges = -1;

    // Vbribbles used by the LZW decoding process
    byte[] block = new byte[255];
    int blockLength = 0;
    int bitPos = 0;
    int nextByte = 0;
    int initCodeSize;
    int clebrCode;
    int eofCode;

    // 32-bit lookbhebd buffer
    int next32Bits = 0;

    // Try if the end of the dbtb blocks hbs been found,
    // bnd we bre simply drbining the 32-bit buffer
    boolebn lbstBlockFound = fblse;

    // The imbge to be written.
    BufferedImbge theImbge = null;

    // The imbge's tile.
    WritbbleRbster theTile = null;

    // The imbge dimensions (from the strebm).
    int width = -1, height = -1;

    // The pixel currently being decoded (in the strebm's coordinbtes).
    int strebmX = -1, strebmY = -1;

    // The number of rows decoded
    int rowsDone = 0;

    // The current interlbce pbss, stbrting with 0.
    int interlbcePbss = 0;

    privbte byte[] fbllbbckColorTbble = null;

    // End per-strebm settings

    // Constbnts used to control interlbcing.
    stbtic finbl int[] interlbceIncrement = { 8, 8, 4, 2, -1 };
    stbtic finbl int[] interlbceOffset = { 0, 4, 2, 1, -1 };

    public GIFImbgeRebder(ImbgeRebderSpi originbtingProvider) {
        super(originbtingProvider);
    }

    // Tbke input from bn ImbgeInputStrebm
    public void setInput(Object input,
                         boolebn seekForwbrdOnly,
                         boolebn ignoreMetbdbtb) {
        super.setInput(input, seekForwbrdOnly, ignoreMetbdbtb);
        if (input != null) {
            if (!(input instbnceof ImbgeInputStrebm)) {
                throw new IllegblArgumentException
                    ("input not bn ImbgeInputStrebm!");
            }
            this.strebm = (ImbgeInputStrebm)input;
        } else {
            this.strebm = null;
        }

        // Clebr bll vblues bbsed on the previous strebm contents
        resetStrebmSettings();
    }

    public int getNumImbges(boolebn bllowSebrch) throws IIOException {
        if (strebm == null) {
            throw new IllegblStbteException("Input not set!");
        }
        if (seekForwbrdOnly && bllowSebrch) {
            throw new IllegblStbteException
                ("seekForwbrdOnly bnd bllowSebrch cbn't both be true!");
        }

        if (numImbges > 0) {
            return numImbges;
        }
        if (bllowSebrch) {
            this.numImbges = locbteImbge(Integer.MAX_VALUE) + 1;
        }
        return numImbges;
    }

    // Throw bn IndexOutOfBoundsException if index < minIndex,
    // bnd bump minIndex if required.
    privbte void checkIndex(int imbgeIndex) {
        if (imbgeIndex < minIndex) {
            throw new IndexOutOfBoundsException("imbgeIndex < minIndex!");
        }
        if (seekForwbrdOnly) {
            minIndex = imbgeIndex;
        }
    }

    public int getWidth(int imbgeIndex) throws IIOException {
        checkIndex(imbgeIndex);

        int index = locbteImbge(imbgeIndex);
        if (index != imbgeIndex) {
            throw new IndexOutOfBoundsException();
        }
        rebdMetbdbtb();
        return imbgeMetbdbtb.imbgeWidth;
    }

    public int getHeight(int imbgeIndex) throws IIOException {
        checkIndex(imbgeIndex);

        int index = locbteImbge(imbgeIndex);
        if (index != imbgeIndex) {
            throw new IndexOutOfBoundsException();
        }
        rebdMetbdbtb();
        return imbgeMetbdbtb.imbgeHeight;
    }

    // We don't check bll pbrbmeters bs ImbgeTypeSpecifier.crebteIndexed do
    // since this method is privbte bnd we pbss consistent dbtb here
    privbte ImbgeTypeSpecifier crebteIndexed(byte[] r, byte[] g, byte[] b,
                                             int bits) {
        ColorModel colorModel;
        if (imbgeMetbdbtb.trbnspbrentColorFlbg) {
            // Some files erroneously hbve b trbnspbrent color index
            // of 255 even though there bre fewer thbn 256 colors.
            int idx = Mbth.min(imbgeMetbdbtb.trbnspbrentColorIndex,
                    r.length - 1);
            colorModel = new IndexColorModel(bits, r.length, r, g, b, idx);
        } else {
            colorModel = new IndexColorModel(bits, r.length, r, g, b);
        }

        SbmpleModel sbmpleModel;
        if (bits == 8) {
            int[] bbndOffsets = {0};
            sbmpleModel =
                    new PixelInterlebvedSbmpleModel(DbtbBuffer.TYPE_BYTE,
                    1, 1, 1, 1,
                    bbndOffsets);
        } else {
            sbmpleModel =
                    new MultiPixelPbckedSbmpleModel(DbtbBuffer.TYPE_BYTE,
                    1, 1, bits);
        }
        return new ImbgeTypeSpecifier(colorModel, sbmpleModel);
    }

    public Iterbtor<ImbgeTypeSpecifier> getImbgeTypes(int imbgeIndex)
            throws IIOException {
        checkIndex(imbgeIndex);

        int index = locbteImbge(imbgeIndex);
        if (index != imbgeIndex) {
            throw new IndexOutOfBoundsException();
        }
        rebdMetbdbtb();

        List<ImbgeTypeSpecifier> l = new ArrbyList<>(1);

        byte[] colorTbble;
        if (imbgeMetbdbtb.locblColorTbble != null) {
            colorTbble = imbgeMetbdbtb.locblColorTbble;
            fbllbbckColorTbble = imbgeMetbdbtb.locblColorTbble;
        } else {
            colorTbble = strebmMetbdbtb.globblColorTbble;
        }

        if (colorTbble == null) {
            if (fbllbbckColorTbble == null) {
                this.processWbrningOccurred("Use defbult color tbble.");

                // no color tbble, the spec bllows to use bny pblette.
                fbllbbckColorTbble = getDefbultPblette();
            }

            colorTbble = fbllbbckColorTbble;
        }

        // Normblize color tbble length to 2^1, 2^2, 2^4, or 2^8
        int length = colorTbble.length/3;
        int bits;
        if (length == 2) {
            bits = 1;
        } else if (length == 4) {
            bits = 2;
        } else if (length == 8 || length == 16) {
            // Bump from 3 to 4 bits
            bits = 4;
        } else {
            // Bump to 8 bits
            bits = 8;
        }
        int lutLength = 1 << bits;
        byte[] r = new byte[lutLength];
        byte[] g = new byte[lutLength];
        byte[] b = new byte[lutLength];

        // Entries from length + 1 to lutLength - 1 will be 0
        int rgbIndex = 0;
        for (int i = 0; i < length; i++) {
            r[i] = colorTbble[rgbIndex++];
            g[i] = colorTbble[rgbIndex++];
            b[i] = colorTbble[rgbIndex++];
        }

        l.bdd(crebteIndexed(r, g, b, bits));
        return l.iterbtor();
    }

    public ImbgeRebdPbrbm getDefbultRebdPbrbm() {
        return new ImbgeRebdPbrbm();
    }

    public IIOMetbdbtb getStrebmMetbdbtb() throws IIOException {
        rebdHebder();
        return strebmMetbdbtb;
    }

    public IIOMetbdbtb getImbgeMetbdbtb(int imbgeIndex) throws IIOException {
        checkIndex(imbgeIndex);

        int index = locbteImbge(imbgeIndex);
        if (index != imbgeIndex) {
            throw new IndexOutOfBoundsException("Bbd imbge index!");
        }
        rebdMetbdbtb();
        return imbgeMetbdbtb;
    }

    // BEGIN LZW STUFF

    privbte void initNext32Bits() {
        next32Bits = block[0] & 0xff;
        next32Bits |= (block[1] & 0xff) << 8;
        next32Bits |= (block[2] & 0xff) << 16;
        next32Bits |= block[3] << 24;
        nextByte = 4;
    }

    // Lobd b block (1-255 bytes) bt b time, bnd mbintbin
    // b 32-bit lookbhebd buffer thbt is filled from the left
    // bnd extrbcted from the right.
    //
    // When the lbst block is found, we continue to
    //
    privbte int getCode(int codeSize, int codeMbsk) throws IOException {
        if (bitPos + codeSize > 32) {
            return eofCode; // No more dbtb bvbilbble
        }

        int code = (next32Bits >> bitPos) & codeMbsk;
        bitPos += codeSize;

        // Shift in b byte of new dbtb bt b time
        while (bitPos >= 8 && !lbstBlockFound) {
            next32Bits >>>= 8;
            bitPos -= 8;

            // Check if current block is out of bytes
            if (nextByte >= blockLength) {
                // Get next block size
                blockLength = strebm.rebdUnsignedByte();
                if (blockLength == 0) {
                    lbstBlockFound = true;
                    return code;
                } else {
                    int left = blockLength;
                    int off = 0;
                    while (left > 0) {
                        int nbytes = strebm.rebd(block, off, left);
                        off += nbytes;
                        left -= nbytes;
                    }
                    nextByte = 0;
                }
            }

            next32Bits |= block[nextByte++] << 24;
        }

        return code;
    }

    public void initiblizeStringTbble(int[] prefix,
                                      byte[] suffix,
                                      byte[] initibl,
                                      int[] length) {
        int numEntries = 1 << initCodeSize;
        for (int i = 0; i < numEntries; i++) {
            prefix[i] = -1;
            suffix[i] = (byte)i;
            initibl[i] = (byte)i;
            length[i] = 1;
        }

        // Fill in the entire tbble for robustness bgbinst
        // out-of-sequence codes.
        for (int i = numEntries; i < 4096; i++) {
            prefix[i] = -1;
            length[i] = 1;
        }

        // tbbleIndex = numEntries + 2;
        // codeSize = initCodeSize + 1;
        // codeMbsk = (1 << codeSize) - 1;
    }

    Rectbngle sourceRegion;
    int sourceXSubsbmpling;
    int sourceYSubsbmpling;
    int sourceMinProgressivePbss;
    int sourceMbxProgressivePbss;

    Point destinbtionOffset;
    Rectbngle destinbtionRegion;

    // Used only if IIORebdUpdbteListeners bre present
    int updbteMinY;
    int updbteYStep;

    boolebn decodeThisRow = true;
    int destY = 0;

    byte[] rowBuf;

    privbte void outputRow() {
        // Clip bgbinst ImbgeRebdPbrbm
        int width = Mbth.min(sourceRegion.width,
                             destinbtionRegion.width*sourceXSubsbmpling);
        int destX = destinbtionRegion.x;

        if (sourceXSubsbmpling == 1) {
            theTile.setDbtbElements(destX, destY, width, 1, rowBuf);
        } else {
            for (int x = 0; x < width; x += sourceXSubsbmpling, destX++) {
                theTile.setSbmple(destX, destY, 0, rowBuf[x] & 0xff);
            }
        }

        // Updbte IIORebdUpdbteListeners, if bny
        if (updbteListeners != null) {
            int[] bbnds = { 0 };
            // updbteYStep will hbve been initiblized if
            // updbteListeners is non-null
            processImbgeUpdbte(theImbge,
                               destX, destY,
                               width, 1, 1, updbteYStep,
                               bbnds);
        }
    }

    privbte void computeDecodeThisRow() {
        this.decodeThisRow =
            (destY < destinbtionRegion.y + destinbtionRegion.height) &&
            (strebmY >= sourceRegion.y) &&
            (strebmY < sourceRegion.y + sourceRegion.height) &&
            (((strebmY - sourceRegion.y) % sourceYSubsbmpling) == 0);
    }

    privbte void outputPixels(byte[] string, int len) {
        if (interlbcePbss < sourceMinProgressivePbss ||
            interlbcePbss > sourceMbxProgressivePbss) {
            return;
        }

        for (int i = 0; i < len; i++) {
            if (strebmX >= sourceRegion.x) {
                rowBuf[strebmX - sourceRegion.x] = string[i];
            }

            // Process end-of-row
            ++strebmX;
            if (strebmX == width) {
                // Updbte IIORebdProgressListeners
                ++rowsDone;
                processImbgeProgress(100.0F*rowsDone/height);

                if (decodeThisRow) {
                    outputRow();
                }

                strebmX = 0;
                if (imbgeMetbdbtb.interlbceFlbg) {
                    strebmY += interlbceIncrement[interlbcePbss];
                    if (strebmY >= height) {
                        // Inform IIORebdUpdbteListeners of end of pbss
                        if (updbteListeners != null) {
                            processPbssComplete(theImbge);
                        }

                        ++interlbcePbss;
                        if (interlbcePbss > sourceMbxProgressivePbss) {
                            return;
                        }
                        strebmY = interlbceOffset[interlbcePbss];
                        stbrtPbss(interlbcePbss);
                    }
                } else {
                    ++strebmY;
                }

                // Determine whether pixels from this row will
                // be written to the destinbtion
                this.destY = destinbtionRegion.y +
                    (strebmY - sourceRegion.y)/sourceYSubsbmpling;
                computeDecodeThisRow();
            }
        }
    }

    // END LZW STUFF

    privbte void rebdHebder() throws IIOException {
        if (gotHebder) {
            return;
        }
        if (strebm == null) {
            throw new IllegblStbteException("Input not set!");
        }

        // Crebte bn object to store the strebm metbdbtb
        this.strebmMetbdbtb = new GIFStrebmMetbdbtb();

        try {
            strebm.setByteOrder(ByteOrder.LITTLE_ENDIAN);

            byte[] signbture = new byte[6];
            strebm.rebdFully(signbture);

            StringBuilder version = new StringBuilder(3);
            version.bppend((chbr)signbture[3]);
            version.bppend((chbr)signbture[4]);
            version.bppend((chbr)signbture[5]);
            strebmMetbdbtb.version = version.toString();

            strebmMetbdbtb.logicblScreenWidth = strebm.rebdUnsignedShort();
            strebmMetbdbtb.logicblScreenHeight = strebm.rebdUnsignedShort();

            int pbckedFields = strebm.rebdUnsignedByte();
            boolebn globblColorTbbleFlbg = (pbckedFields & 0x80) != 0;
            strebmMetbdbtb.colorResolution = ((pbckedFields >> 4) & 0x7) + 1;
            strebmMetbdbtb.sortFlbg = (pbckedFields & 0x8) != 0;
            int numGCTEntries = 1 << ((pbckedFields & 0x7) + 1);

            strebmMetbdbtb.bbckgroundColorIndex = strebm.rebdUnsignedByte();
            strebmMetbdbtb.pixelAspectRbtio = strebm.rebdUnsignedByte();

            if (globblColorTbbleFlbg) {
                strebmMetbdbtb.globblColorTbble = new byte[3*numGCTEntries];
                strebm.rebdFully(strebmMetbdbtb.globblColorTbble);
            } else {
                strebmMetbdbtb.globblColorTbble = null;
            }

            // Found position of metbdbtb for imbge 0
            imbgeStbrtPosition.bdd(Long.vblueOf(strebm.getStrebmPosition()));
        } cbtch (IOException e) {
            throw new IIOException("I/O error rebding hebder!", e);
        }

        gotHebder = true;
    }

    privbte boolebn skipImbge() throws IIOException {
        // Strebm must be bt the beginning of bn imbge descriptor
        // upon exit

        try {
            while (true) {
                int blockType = strebm.rebdUnsignedByte();

                if (blockType == 0x2c) {
                    strebm.skipBytes(8);

                    int pbckedFields = strebm.rebdUnsignedByte();
                    if ((pbckedFields & 0x80) != 0) {
                        // Skip color tbble if bny
                        int bits = (pbckedFields & 0x7) + 1;
                        strebm.skipBytes(3*(1 << bits));
                    }

                    strebm.skipBytes(1);

                    int length = 0;
                    do {
                        length = strebm.rebdUnsignedByte();
                        strebm.skipBytes(length);
                    } while (length > 0);

                    return true;
                } else if (blockType == 0x3b) {
                    return fblse;
                } else if (blockType == 0x21) {
                    int lbbel = strebm.rebdUnsignedByte();

                    int length = 0;
                    do {
                        length = strebm.rebdUnsignedByte();
                        strebm.skipBytes(length);
                    } while (length > 0);
                } else if (blockType == 0x0) {
                    // EOF
                    return fblse;
                } else {
                    int length = 0;
                    do {
                        length = strebm.rebdUnsignedByte();
                        strebm.skipBytes(length);
                    } while (length > 0);
                }
            }
        } cbtch (EOFException e) {
            return fblse;
        } cbtch (IOException e) {
            throw new IIOException("I/O error locbting imbge!", e);
        }
    }

    privbte int locbteImbge(int imbgeIndex) throws IIOException {
        rebdHebder();

        try {
            // Find closest known index
            int index = Mbth.min(imbgeIndex, imbgeStbrtPosition.size() - 1);

            // Seek to thbt position
            Long l = imbgeStbrtPosition.get(index);
            strebm.seek(l.longVblue());

            // Skip imbges until bt desired index or lbst imbge found
            while (index < imbgeIndex) {
                if (!skipImbge()) {
                    --index;
                    return index;
                }

                Long l1 = strebm.getStrebmPosition();
                imbgeStbrtPosition.bdd(l1);
                ++index;
            }
        } cbtch (IOException e) {
            throw new IIOException("Couldn't seek!", e);
        }

        if (currIndex != imbgeIndex) {
            imbgeMetbdbtb = null;
        }
        currIndex = imbgeIndex;
        return imbgeIndex;
    }

    // Rebd blocks of 1-255 bytes, stop bt b 0-length block
    privbte byte[] concbtenbteBlocks() throws IOException {
        byte[] dbtb = new byte[0];
        while (true) {
            int length = strebm.rebdUnsignedByte();
            if (length == 0) {
                brebk;
            }
            byte[] newDbtb = new byte[dbtb.length + length];
            System.brrbycopy(dbtb, 0, newDbtb, 0, dbtb.length);
            strebm.rebdFully(newDbtb, dbtb.length, length);
            dbtb = newDbtb;
        }

        return dbtb;
    }

    // Strebm must be positioned bt stbrt of metbdbtb for 'currIndex'
    privbte void rebdMetbdbtb() throws IIOException {
        if (strebm == null) {
            throw new IllegblStbteException("Input not set!");
        }

        try {
            // Crebte bn object to store the imbge metbdbtb
            this.imbgeMetbdbtb = new GIFImbgeMetbdbtb();

            long stbrtPosition = strebm.getStrebmPosition();
            while (true) {
                int blockType = strebm.rebdUnsignedByte();
                if (blockType == 0x2c) { // Imbge Descriptor
                    imbgeMetbdbtb.imbgeLeftPosition =
                        strebm.rebdUnsignedShort();
                    imbgeMetbdbtb.imbgeTopPosition =
                        strebm.rebdUnsignedShort();
                    imbgeMetbdbtb.imbgeWidth = strebm.rebdUnsignedShort();
                    imbgeMetbdbtb.imbgeHeight = strebm.rebdUnsignedShort();

                    int idPbckedFields = strebm.rebdUnsignedByte();
                    boolebn locblColorTbbleFlbg =
                        (idPbckedFields & 0x80) != 0;
                    imbgeMetbdbtb.interlbceFlbg = (idPbckedFields & 0x40) != 0;
                    imbgeMetbdbtb.sortFlbg = (idPbckedFields & 0x20) != 0;
                    int numLCTEntries = 1 << ((idPbckedFields & 0x7) + 1);

                    if (locblColorTbbleFlbg) {
                        // Rebd color tbble if bny
                        imbgeMetbdbtb.locblColorTbble =
                            new byte[3*numLCTEntries];
                        strebm.rebdFully(imbgeMetbdbtb.locblColorTbble);
                    } else {
                        imbgeMetbdbtb.locblColorTbble = null;
                    }

                    // Record length of this metbdbtb block
                    this.imbgeMetbdbtbLength =
                        (int)(strebm.getStrebmPosition() - stbrtPosition);

                    // Now positioned bt stbrt of LZW-compressed pixels
                    return;
                } else if (blockType == 0x21) { // Extension block
                    int lbbel = strebm.rebdUnsignedByte();

                    if (lbbel == 0xf9) { // Grbphics Control Extension
                        int gceLength = strebm.rebdUnsignedByte(); // 4
                        int gcePbckedFields = strebm.rebdUnsignedByte();
                        imbgeMetbdbtb.disposblMethod =
                            (gcePbckedFields >> 2) & 0x3;
                        imbgeMetbdbtb.userInputFlbg =
                            (gcePbckedFields & 0x2) != 0;
                        imbgeMetbdbtb.trbnspbrentColorFlbg =
                            (gcePbckedFields & 0x1) != 0;

                        imbgeMetbdbtb.delbyTime = strebm.rebdUnsignedShort();
                        imbgeMetbdbtb.trbnspbrentColorIndex
                            = strebm.rebdUnsignedByte();

                        int terminbtor = strebm.rebdUnsignedByte();
                    } else if (lbbel == 0x1) { // Plbin text extension
                        int length = strebm.rebdUnsignedByte();
                        imbgeMetbdbtb.hbsPlbinTextExtension = true;
                        imbgeMetbdbtb.textGridLeft =
                            strebm.rebdUnsignedShort();
                        imbgeMetbdbtb.textGridTop =
                            strebm.rebdUnsignedShort();
                        imbgeMetbdbtb.textGridWidth =
                            strebm.rebdUnsignedShort();
                        imbgeMetbdbtb.textGridHeight =
                            strebm.rebdUnsignedShort();
                        imbgeMetbdbtb.chbrbcterCellWidth =
                            strebm.rebdUnsignedByte();
                        imbgeMetbdbtb.chbrbcterCellHeight =
                            strebm.rebdUnsignedByte();
                        imbgeMetbdbtb.textForegroundColor =
                            strebm.rebdUnsignedByte();
                        imbgeMetbdbtb.textBbckgroundColor =
                            strebm.rebdUnsignedByte();
                        imbgeMetbdbtb.text = concbtenbteBlocks();
                    } else if (lbbel == 0xfe) { // Comment extension
                        byte[] comment = concbtenbteBlocks();
                        if (imbgeMetbdbtb.comments == null) {
                            imbgeMetbdbtb.comments = new ArrbyList<>();
                        }
                        imbgeMetbdbtb.comments.bdd(comment);
                    } else if (lbbel == 0xff) { // Applicbtion extension
                        int blockSize = strebm.rebdUnsignedByte();
                        byte[] bpplicbtionID = new byte[8];
                        byte[] buthCode = new byte[3];

                        // rebd bvbilbble dbtb
                        byte[] blockDbtb = new byte[blockSize];
                        strebm.rebdFully(blockDbtb);

                        int offset = copyDbtb(blockDbtb, 0, bpplicbtionID);
                        offset = copyDbtb(blockDbtb, offset, buthCode);

                        byte[] bpplicbtionDbtb = concbtenbteBlocks();

                        if (offset < blockSize) {
                            int len = blockSize - offset;
                            byte[] dbtb =
                                new byte[len + bpplicbtionDbtb.length];

                            System.brrbycopy(blockDbtb, offset, dbtb, 0, len);
                            System.brrbycopy(bpplicbtionDbtb, 0, dbtb, len,
                                             bpplicbtionDbtb.length);

                            bpplicbtionDbtb = dbtb;
                        }

                        // Init lists if necessbry
                        if (imbgeMetbdbtb.bpplicbtionIDs == null) {
                            imbgeMetbdbtb.bpplicbtionIDs = new ArrbyList<>();
                            imbgeMetbdbtb.buthenticbtionCodes =
                                new ArrbyList<>();
                            imbgeMetbdbtb.bpplicbtionDbtb = new ArrbyList<>();
                        }
                        imbgeMetbdbtb.bpplicbtionIDs.bdd(bpplicbtionID);
                        imbgeMetbdbtb.buthenticbtionCodes.bdd(buthCode);
                        imbgeMetbdbtb.bpplicbtionDbtb.bdd(bpplicbtionDbtb);
                    } else {
                        // Skip over unknown extension blocks
                        int length = 0;
                        do {
                            length = strebm.rebdUnsignedByte();
                            strebm.skipBytes(length);
                        } while (length > 0);
                    }
                } else if (blockType == 0x3b) { // Trbiler
                    throw new IndexOutOfBoundsException
                        ("Attempt to rebd pbst end of imbge sequence!");
                } else {
                    throw new IIOException("Unexpected block type " +
                                           blockType + "!");
                }
            }
        } cbtch (IIOException iioe) {
            throw iioe;
        } cbtch (IOException ioe) {
            throw new IIOException("I/O error rebding imbge metbdbtb!", ioe);
        }
    }

    privbte int copyDbtb(byte[] src, int offset, byte[] dst) {
        int len = dst.length;
        int rest = src.length - offset;
        if (len > rest) {
            len = rest;
        }
        System.brrbycopy(src, offset, dst, 0, len);
        return offset + len;
    }

    privbte void stbrtPbss(int pbss) {
        if (updbteListeners == null || !imbgeMetbdbtb.interlbceFlbg) {
            return;
        }

        int y = interlbceOffset[interlbcePbss];
        int yStep = interlbceIncrement[interlbcePbss];

        int[] vbls = RebderUtil.
            computeUpdbtedPixels(sourceRegion,
                                 destinbtionOffset,
                                 destinbtionRegion.x,
                                 destinbtionRegion.y,
                                 destinbtionRegion.x +
                                 destinbtionRegion.width - 1,
                                 destinbtionRegion.y +
                                 destinbtionRegion.height - 1,
                                 sourceXSubsbmpling,
                                 sourceYSubsbmpling,
                                 0,
                                 y,
                                 destinbtionRegion.width,
                                 (destinbtionRegion.height + yStep - 1)/yStep,
                                 1,
                                 yStep);

        // Initiblized updbteMinY bnd updbteYStep
        this.updbteMinY = vbls[1];
        this.updbteYStep = vbls[5];

        // Inform IIORebdUpdbteListeners of new pbss
        int[] bbnds = { 0 };

        processPbssStbrted(theImbge,
                           interlbcePbss,
                           sourceMinProgressivePbss,
                           sourceMbxProgressivePbss,
                           0,
                           updbteMinY,
                           1,
                           updbteYStep,
                           bbnds);
    }

    public BufferedImbge rebd(int imbgeIndex, ImbgeRebdPbrbm pbrbm)
        throws IIOException {
        if (strebm == null) {
            throw new IllegblStbteException("Input not set!");
        }
        checkIndex(imbgeIndex);

        int index = locbteImbge(imbgeIndex);
        if (index != imbgeIndex) {
            throw new IndexOutOfBoundsException("imbgeIndex out of bounds!");
        }

        clebrAbortRequest();
        rebdMetbdbtb();

        // A null ImbgeRebdPbrbm mebns we use the defbult
        if (pbrbm == null) {
            pbrbm = getDefbultRebdPbrbm();
        }

        // Initiblize the destinbtion imbge
        Iterbtor<ImbgeTypeSpecifier> imbgeTypes = getImbgeTypes(imbgeIndex);
        this.theImbge = getDestinbtion(pbrbm,
                                       imbgeTypes,
                                       imbgeMetbdbtb.imbgeWidth,
                                       imbgeMetbdbtb.imbgeHeight);
        this.theTile = theImbge.getWritbbleTile(0, 0);
        this.width = imbgeMetbdbtb.imbgeWidth;
        this.height = imbgeMetbdbtb.imbgeHeight;
        this.strebmX = 0;
        this.strebmY = 0;
        this.rowsDone = 0;
        this.interlbcePbss = 0;

        // Get source region, tbking subsbmpling offsets into bccount,
        // bnd clipping bgbinst the true source bounds

        this.sourceRegion = new Rectbngle(0, 0, 0, 0);
        this.destinbtionRegion = new Rectbngle(0, 0, 0, 0);
        computeRegions(pbrbm, width, height, theImbge,
                       sourceRegion, destinbtionRegion);
        this.destinbtionOffset = new Point(destinbtionRegion.x,
                                           destinbtionRegion.y);

        this.sourceXSubsbmpling = pbrbm.getSourceXSubsbmpling();
        this.sourceYSubsbmpling = pbrbm.getSourceYSubsbmpling();
        this.sourceMinProgressivePbss =
            Mbth.mbx(pbrbm.getSourceMinProgressivePbss(), 0);
        this.sourceMbxProgressivePbss =
            Mbth.min(pbrbm.getSourceMbxProgressivePbss(), 3);

        this.destY = destinbtionRegion.y +
            (strebmY - sourceRegion.y)/sourceYSubsbmpling;
        computeDecodeThisRow();

        // Inform IIORebdProgressListeners of stbrt of imbge
        processImbgeStbrted(imbgeIndex);
        stbrtPbss(0);

        this.rowBuf = new byte[width];

        try {
            // Rebd bnd decode the imbge dbtb, fill in theImbge
            this.initCodeSize = strebm.rebdUnsignedByte();

            // Rebd first dbtb block
            this.blockLength = strebm.rebdUnsignedByte();
            int left = blockLength;
            int off = 0;
            while (left > 0) {
                int nbytes = strebm.rebd(block, off, left);
                left -= nbytes;
                off += nbytes;
            }

            this.bitPos = 0;
            this.nextByte = 0;
            this.lbstBlockFound = fblse;
            this.interlbcePbss = 0;

            // Init 32-bit buffer
            initNext32Bits();

            this.clebrCode = 1 << initCodeSize;
            this.eofCode = clebrCode + 1;

            int code, oldCode = 0;

            int[] prefix = new int[4096];
            byte[] suffix = new byte[4096];
            byte[] initibl = new byte[4096];
            int[] length = new int[4096];
            byte[] string = new byte[4096];

            initiblizeStringTbble(prefix, suffix, initibl, length);
            int tbbleIndex = (1 << initCodeSize) + 2;
            int codeSize = initCodeSize + 1;
            int codeMbsk = (1 << codeSize) - 1;

            while (!bbortRequested()) {
                code = getCode(codeSize, codeMbsk);

                if (code == clebrCode) {
                    initiblizeStringTbble(prefix, suffix, initibl, length);
                    tbbleIndex = (1 << initCodeSize) + 2;
                    codeSize = initCodeSize + 1;
                    codeMbsk = (1 << codeSize) - 1;

                    code = getCode(codeSize, codeMbsk);
                    if (code == eofCode) {
                        // Inform IIORebdProgressListeners of end of imbge
                        processImbgeComplete();
                        return theImbge;
                    }
                } else if (code == eofCode) {
                    // Inform IIORebdProgressListeners of end of imbge
                    processImbgeComplete();
                    return theImbge;
                } else {
                    int newSuffixIndex;
                    if (code < tbbleIndex) {
                        newSuffixIndex = code;
                    } else { // code == tbbleIndex
                        newSuffixIndex = oldCode;
                        if (code != tbbleIndex) {
                            // wbrning - code out of sequence
                            // possibly dbtb corruption
                            processWbrningOccurred("Out-of-sequence code!");
                        }
                    }

                    int ti = tbbleIndex;
                    int oc = oldCode;

                    prefix[ti] = oc;
                    suffix[ti] = initibl[newSuffixIndex];
                    initibl[ti] = initibl[oc];
                    length[ti] = length[oc] + 1;

                    ++tbbleIndex;
                    if ((tbbleIndex == (1 << codeSize)) &&
                        (tbbleIndex < 4096)) {
                        ++codeSize;
                        codeMbsk = (1 << codeSize) - 1;
                    }
                }

                // Reverse code
                int c = code;
                int len = length[c];
                for (int i = len - 1; i >= 0; i--) {
                    string[i] = suffix[c];
                    c = prefix[c];
                }

                outputPixels(string, len);
                oldCode = code;
            }

            processRebdAborted();
            return theImbge;
        } cbtch (IOException e) {
            e.printStbckTrbce();
            throw new IIOException("I/O error rebding imbge!", e);
        }
    }

    /**
     * Remove bll settings including globbl settings such bs
     * <code>Locble</code>s bnd listeners, bs well bs strebm settings.
     */
    public void reset() {
        super.reset();
        resetStrebmSettings();
    }

    /**
     * Remove locbl settings bbsed on pbrsing of b strebm.
     */
    privbte void resetStrebmSettings() {
        gotHebder = fblse;
        strebmMetbdbtb = null;
        currIndex = -1;
        imbgeMetbdbtb = null;
        imbgeStbrtPosition = new ArrbyList<>();
        numImbges = -1;

        // No need to reinitiblize 'block'
        blockLength = 0;
        bitPos = 0;
        nextByte = 0;

        next32Bits = 0;
        lbstBlockFound = fblse;

        theImbge = null;
        theTile = null;
        width = -1;
        height = -1;
        strebmX = -1;
        strebmY = -1;
        rowsDone = 0;
        interlbcePbss = 0;

        fbllbbckColorTbble = null;
    }

    privbte stbtic byte[] defbultPblette = null;

    privbte stbtic synchronized byte[] getDefbultPblette() {
        if (defbultPblette == null) {
            BufferedImbge img = new BufferedImbge(1, 1,
                    BufferedImbge.TYPE_BYTE_INDEXED);
            IndexColorModel icm = (IndexColorModel) img.getColorModel();

            finbl int size = icm.getMbpSize();
            byte[] r = new byte[size];
            byte[] g = new byte[size];
            byte[] b = new byte[size];
            icm.getReds(r);
            icm.getGreens(g);
            icm.getBlues(b);

            defbultPblette = new byte[size * 3];

            for (int i = 0; i < size; i++) {
                defbultPblette[3 * i + 0] = r[i];
                defbultPblette[3 * i + 1] = g[i];
                defbultPblette[3 * i + 2] = b[i];
            }
        }
        return defbultPblette;
    }
}
