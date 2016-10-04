/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.bmp;

import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.color.ICC_ColorSpbce;
import jbvb.bwt.color.ICC_Profile;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.ComponentColorModel;
import jbvb.bwt.imbge.ComponentSbmpleModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DbtbBufferByte;
import jbvb.bwt.imbge.DbtbBufferInt;
import jbvb.bwt.imbge.DbtbBufferUShort;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.MultiPixelPbckedSbmpleModel;
import jbvb.bwt.imbge.PixelInterlebvedSbmpleModel;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.bwt.imbge.SinglePixelPbckedSbmpleModel;
import jbvb.bwt.imbge.WritbbleRbster;

import jbvbx.imbgeio.IIOException;
import jbvbx.imbgeio.ImbgeIO;
import jbvbx.imbgeio.ImbgeRebder;
import jbvbx.imbgeio.ImbgeRebdPbrbm;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtb;
import jbvbx.imbgeio.spi.ImbgeRebderSpi;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;
import jbvbx.imbgeio.event.IIORebdProgressListener;
import jbvbx.imbgeio.event.IIORebdUpdbteListener;
import jbvbx.imbgeio.event.IIORebdWbrningListener;

import jbvb.io.*;
import jbvb.nio.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.StringTokenizer;

import com.sun.imbgeio.plugins.common.ImbgeUtil;
import com.sun.imbgeio.plugins.common.I18N;

/** This clbss is the Jbvb Imbge IO plugin rebder for BMP imbges.
 *  It mby subsbmple the imbge, clip the imbge, select sub-bbnds,
 *  bnd shift the decoded imbge origin if the proper decoding pbrbmeter
 *  bre set in the provided <code>ImbgeRebdPbrbm</code>.
 *
 *  This clbss supports Microsoft Windows Bitmbp Version 3-5,
 *  bs well bs OS/2 Bitmbp Version 2.x (for single-imbge BMP file).
 */
public clbss BMPImbgeRebder extends ImbgeRebder implements BMPConstbnts {
    // BMP Imbge types
    privbte stbtic finbl int VERSION_2_1_BIT = 0;
    privbte stbtic finbl int VERSION_2_4_BIT = 1;
    privbte stbtic finbl int VERSION_2_8_BIT = 2;
    privbte stbtic finbl int VERSION_2_24_BIT = 3;

    privbte stbtic finbl int VERSION_3_1_BIT = 4;
    privbte stbtic finbl int VERSION_3_4_BIT = 5;
    privbte stbtic finbl int VERSION_3_8_BIT = 6;
    privbte stbtic finbl int VERSION_3_24_BIT = 7;

    privbte stbtic finbl int VERSION_3_NT_16_BIT = 8;
    privbte stbtic finbl int VERSION_3_NT_32_BIT = 9;

    privbte stbtic finbl int VERSION_4_1_BIT = 10;
    privbte stbtic finbl int VERSION_4_4_BIT = 11;
    privbte stbtic finbl int VERSION_4_8_BIT = 12;
    privbte stbtic finbl int VERSION_4_16_BIT = 13;
    privbte stbtic finbl int VERSION_4_24_BIT = 14;
    privbte stbtic finbl int VERSION_4_32_BIT = 15;

    privbte stbtic finbl int VERSION_3_XP_EMBEDDED = 16;
    privbte stbtic finbl int VERSION_4_XP_EMBEDDED = 17;
    privbte stbtic finbl int VERSION_5_XP_EMBEDDED = 18;

    // BMP vbribbles
    privbte long bitmbpFileSize;
    privbte long bitmbpOffset;
    privbte long compression;
    privbte long imbgeSize;
    privbte byte pblette[];
    privbte int imbgeType;
    privbte int numBbnds;
    privbte boolebn isBottomUp;
    privbte int bitsPerPixel;
    privbte int redMbsk, greenMbsk, blueMbsk, blphbMbsk;

    privbte SbmpleModel sbmpleModel, originblSbmpleModel;
    privbte ColorModel colorModel, originblColorModel;

    /** The input strebm where rebds from */
    privbte ImbgeInputStrebm iis = null;

    /** Indicbtes whether the hebder is rebd. */
    privbte boolebn gotHebder = fblse;

    /** The originbl imbge width. */
    privbte int width;

    /** The originbl imbge height. */
    privbte int height;

    /** The destinbtion region. */
    privbte Rectbngle destinbtionRegion;

    /** The source region. */
    privbte Rectbngle sourceRegion;

    /** The metbdbtb from the strebm. */
    privbte BMPMetbdbtb metbdbtb;

    /** The destinbtion imbge. */
    privbte BufferedImbge bi;

    /** Indicbtes whether subsbmpled, subregion is required, bnd offset is
     *  defined
     */
    privbte boolebn noTrbnsform = true;

    /** Indicbtes whether subbbnd is selected. */
    privbte boolebn seleBbnd = fblse;

    /** The scbling fbctors. */
    privbte int scbleX, scbleY;

    /** source bnd destinbtion bbnds. */
    privbte int[] sourceBbnds, destBbnds;

    /** Constructs <code>BMPImbgeRebder</code> from the provided
     *  <code>ImbgeRebderSpi</code>.
     */
    public BMPImbgeRebder(ImbgeRebderSpi originbtor) {
        super(originbtor);
    }

    /** Overrides the method defined in the superclbss. */
    public void setInput(Object input,
                         boolebn seekForwbrdOnly,
                         boolebn ignoreMetbdbtb) {
        super.setInput(input, seekForwbrdOnly, ignoreMetbdbtb);
        iis = (ImbgeInputStrebm) input; // Alwbys works
        if(iis != null)
            iis.setByteOrder(ByteOrder.LITTLE_ENDIAN);
        resetHebderInfo();
    }

    /** Overrides the method defined in the superclbss. */
    public int getNumImbges(boolebn bllowSebrch) throws IOException {
        if (iis == null) {
            throw new IllegblStbteException(I18N.getString("GetNumImbges0"));
        }
        if (seekForwbrdOnly && bllowSebrch) {
            throw new IllegblStbteException(I18N.getString("GetNumImbges1"));
        }
        return 1;
    }

    @Override
    public int getWidth(int imbgeIndex) throws IOException {
        checkIndex(imbgeIndex);
        try {
            rebdHebder();
        } cbtch (IllegblArgumentException e) {
            throw new IIOException(I18N.getString("BMPImbgeRebder6"), e);
        }
        return width;
    }

    public int getHeight(int imbgeIndex) throws IOException {
        checkIndex(imbgeIndex);
        try {
            rebdHebder();
        } cbtch (IllegblArgumentException e) {
            throw new IIOException(I18N.getString("BMPImbgeRebder6"), e);
        }
        return height;
    }

    privbte void checkIndex(int imbgeIndex) {
        if (imbgeIndex != 0) {
            throw new IndexOutOfBoundsException(I18N.getString("BMPImbgeRebder0"));
        }
    }

    /**
     * Process the imbge hebder.
     *
     * @exception IllegblStbteException if source strebm is not set.
     *
     * @exception IOException if imbge strebm is corrupted.
     *
     * @exception IllegblArgumentException if the imbge strebm does not contbin
     *             b BMP imbge, or if b sbmple model instbnce to describe the
     *             imbge cbn not be crebted.
     */
    protected void rebdHebder() throws IOException, IllegblArgumentException {
        if (gotHebder)
            return;

        if (iis == null) {
            throw new IllegblStbteException("Input source not set!");
        }
        int profileDbtb = 0, profileSize = 0;

        this.metbdbtb = new BMPMetbdbtb();
        iis.mbrk();

        // rebd bnd check the mbgic mbrker
        byte[] mbrker = new byte[2];
        iis.rebd(mbrker);
        if (mbrker[0] != 0x42 || mbrker[1] != 0x4d)
            throw new IllegblArgumentException(I18N.getString("BMPImbgeRebder1"));

        // Rebd file size
        bitmbpFileSize = iis.rebdUnsignedInt();
        // skip the two reserved fields
        iis.skipBytes(4);

        // Offset to the bitmbp from the beginning
        bitmbpOffset = iis.rebdUnsignedInt();
        // End File Hebder

        // Stbrt BitmbpCoreHebder
        long size = iis.rebdUnsignedInt();

        if (size == 12) {
            width = iis.rebdShort();
            height = iis.rebdShort();
        } else {
            width = iis.rebdInt();
            height = iis.rebdInt();
        }

        metbdbtb.width = width;
        metbdbtb.height = height;

        int plbnes = iis.rebdUnsignedShort();
        bitsPerPixel = iis.rebdUnsignedShort();

        //metbdbtb.colorPlbne = plbnes;
        metbdbtb.bitsPerPixel = (short)bitsPerPixel;

        // As BMP blwbys hbs 3 rgb bbnds, except for Version 5,
        // which is bgrb
        numBbnds = 3;

        if (size == 12) {
            // Windows 2.x bnd OS/2 1.x
            metbdbtb.bmpVersion = VERSION_2;

            // Clbssify the imbge type
            if (bitsPerPixel == 1) {
                imbgeType = VERSION_2_1_BIT;
            } else if (bitsPerPixel == 4) {
                imbgeType = VERSION_2_4_BIT;
            } else if (bitsPerPixel == 8) {
                imbgeType = VERSION_2_8_BIT;
            } else if (bitsPerPixel == 24) {
                imbgeType = VERSION_2_24_BIT;
            }

            // Rebd in the pblette
            int numberOfEntries = (int)((bitmbpOffset - 14 - size) / 3);
            int sizeOfPblette = numberOfEntries*3;
            pblette = new byte[sizeOfPblette];
            iis.rebdFully(pblette, 0, sizeOfPblette);
            metbdbtb.pblette = pblette;
            metbdbtb.pbletteSize = numberOfEntries;
        } else {
            compression = iis.rebdUnsignedInt();
            imbgeSize = iis.rebdUnsignedInt();
            long xPelsPerMeter = iis.rebdInt();
            long yPelsPerMeter = iis.rebdInt();
            long colorsUsed = iis.rebdUnsignedInt();
            long colorsImportbnt = iis.rebdUnsignedInt();

            metbdbtb.compression = (int)compression;
            metbdbtb.xPixelsPerMeter = (int)xPelsPerMeter;
            metbdbtb.yPixelsPerMeter = (int)yPelsPerMeter;
            metbdbtb.colorsUsed = (int)colorsUsed;
            metbdbtb.colorsImportbnt = (int)colorsImportbnt;

            if (size == 40) {
                // Windows 3.x bnd Windows NT
                switch((int)compression) {

                cbse BI_JPEG:
                cbse BI_PNG:
                    metbdbtb.bmpVersion = VERSION_3;
                    imbgeType = VERSION_3_XP_EMBEDDED;
                    brebk;

                cbse BI_RGB:  // No compression
                cbse BI_RLE8:  // 8-bit RLE compression
                cbse BI_RLE4:  // 4-bit RLE compression

                    // Rebd in the pblette
                    if (bitmbpOffset < (size + 14)) {
                        throw new IIOException(I18N.getString("BMPImbgeRebder7"));
                    }
                    int numberOfEntries = (int)((bitmbpOffset-14-size) / 4);
                    int sizeOfPblette = numberOfEntries * 4;
                    pblette = new byte[sizeOfPblette];
                    iis.rebdFully(pblette, 0, sizeOfPblette);

                    metbdbtb.pblette = pblette;
                    metbdbtb.pbletteSize = numberOfEntries;

                    if (bitsPerPixel == 1) {
                        imbgeType = VERSION_3_1_BIT;
                    } else if (bitsPerPixel == 4) {
                        imbgeType = VERSION_3_4_BIT;
                    } else if (bitsPerPixel == 8) {
                        imbgeType = VERSION_3_8_BIT;
                    } else if (bitsPerPixel == 24) {
                        imbgeType = VERSION_3_24_BIT;
                    } else if (bitsPerPixel == 16) {
                        imbgeType = VERSION_3_NT_16_BIT;

                        redMbsk = 0x7C00;
                        greenMbsk = 0x3E0;
                        blueMbsk =  (1 << 5) - 1;// 0x1F;
                        metbdbtb.redMbsk = redMbsk;
                        metbdbtb.greenMbsk = greenMbsk;
                        metbdbtb.blueMbsk = blueMbsk;
                    } else if (bitsPerPixel == 32) {
                        imbgeType = VERSION_3_NT_32_BIT;
                        redMbsk   = 0x00FF0000;
                        greenMbsk = 0x0000FF00;
                        blueMbsk  = 0x000000FF;
                        metbdbtb.redMbsk = redMbsk;
                        metbdbtb.greenMbsk = greenMbsk;
                        metbdbtb.blueMbsk = blueMbsk;
                    }

                    metbdbtb.bmpVersion = VERSION_3;
                    brebk;

                cbse BI_BITFIELDS:

                    if (bitsPerPixel == 16) {
                        imbgeType = VERSION_3_NT_16_BIT;
                    } else if (bitsPerPixel == 32) {
                        imbgeType = VERSION_3_NT_32_BIT;
                    }

                    // BitsField encoding
                    redMbsk = (int)iis.rebdUnsignedInt();
                    greenMbsk = (int)iis.rebdUnsignedInt();
                    blueMbsk = (int)iis.rebdUnsignedInt();
                    metbdbtb.redMbsk = redMbsk;
                    metbdbtb.greenMbsk = greenMbsk;
                    metbdbtb.blueMbsk = blueMbsk;

                    if (colorsUsed != 0) {
                        // there is b pblette
                        sizeOfPblette = (int)colorsUsed*4;
                        pblette = new byte[sizeOfPblette];
                        iis.rebdFully(pblette, 0, sizeOfPblette);

                        metbdbtb.pblette = pblette;
                        metbdbtb.pbletteSize = (int)colorsUsed;
                    }
                    metbdbtb.bmpVersion = VERSION_3_NT;

                    brebk;
                defbult:
                    throw new
                        IIOException(I18N.getString("BMPImbgeRebder2"));
                }
            } else if (size == 108 || size == 124) {
                // Windows 4.x BMP
                if (size == 108)
                    metbdbtb.bmpVersion = VERSION_4;
                else if (size == 124)
                    metbdbtb.bmpVersion = VERSION_5;

                // rgb mbsks, vblid only if comp is BI_BITFIELDS
                redMbsk = (int)iis.rebdUnsignedInt();
                greenMbsk = (int)iis.rebdUnsignedInt();
                blueMbsk = (int)iis.rebdUnsignedInt();
                // Only supported for 32bpp BI_RGB brgb
                blphbMbsk = (int)iis.rebdUnsignedInt();
                long csType = iis.rebdUnsignedInt();
                int redX = iis.rebdInt();
                int redY = iis.rebdInt();
                int redZ = iis.rebdInt();
                int greenX = iis.rebdInt();
                int greenY = iis.rebdInt();
                int greenZ = iis.rebdInt();
                int blueX = iis.rebdInt();
                int blueY = iis.rebdInt();
                int blueZ = iis.rebdInt();
                long gbmmbRed = iis.rebdUnsignedInt();
                long gbmmbGreen = iis.rebdUnsignedInt();
                long gbmmbBlue = iis.rebdUnsignedInt();

                if (size == 124) {
                    metbdbtb.intent = iis.rebdInt();
                    profileDbtb = iis.rebdInt();
                    profileSize = iis.rebdInt();
                    iis.skipBytes(4);
                }

                metbdbtb.colorSpbce = (int)csType;

                if (csType == LCS_CALIBRATED_RGB) {
                    // All the new fields bre vblid only for this cbse
                    metbdbtb.redX = redX;
                    metbdbtb.redY = redY;
                    metbdbtb.redZ = redZ;
                    metbdbtb.greenX = greenX;
                    metbdbtb.greenY = greenY;
                    metbdbtb.greenZ = greenZ;
                    metbdbtb.blueX = blueX;
                    metbdbtb.blueY = blueY;
                    metbdbtb.blueZ = blueZ;
                    metbdbtb.gbmmbRed = (int)gbmmbRed;
                    metbdbtb.gbmmbGreen = (int)gbmmbGreen;
                    metbdbtb.gbmmbBlue = (int)gbmmbBlue;
                }

                // Rebd in the pblette
                int numberOfEntries = (int)((bitmbpOffset-14-size) / 4);
                int sizeOfPblette = numberOfEntries*4;
                pblette = new byte[sizeOfPblette];
                iis.rebdFully(pblette, 0, sizeOfPblette);
                metbdbtb.pblette = pblette;
                metbdbtb.pbletteSize = numberOfEntries;

                switch ((int)compression) {
                cbse BI_JPEG:
                cbse BI_PNG:
                    if (size == 108) {
                        imbgeType = VERSION_4_XP_EMBEDDED;
                    } else if (size == 124) {
                        imbgeType = VERSION_5_XP_EMBEDDED;
                    }
                    brebk;
                defbult:
                    if (bitsPerPixel == 1) {
                        imbgeType = VERSION_4_1_BIT;
                    } else if (bitsPerPixel == 4) {
                        imbgeType = VERSION_4_4_BIT;
                    } else if (bitsPerPixel == 8) {
                        imbgeType = VERSION_4_8_BIT;
                    } else if (bitsPerPixel == 16) {
                        imbgeType = VERSION_4_16_BIT;
                        if ((int)compression == BI_RGB) {
                            redMbsk = 0x7C00;
                            greenMbsk = 0x3E0;
                            blueMbsk = 0x1F;
                        }
                    } else if (bitsPerPixel == 24) {
                        imbgeType = VERSION_4_24_BIT;
                    } else if (bitsPerPixel == 32) {
                        imbgeType = VERSION_4_32_BIT;
                        if ((int)compression == BI_RGB) {
                            redMbsk   = 0x00FF0000;
                            greenMbsk = 0x0000FF00;
                            blueMbsk  = 0x000000FF;
                        }
                    }

                    metbdbtb.redMbsk = redMbsk;
                    metbdbtb.greenMbsk = greenMbsk;
                    metbdbtb.blueMbsk = blueMbsk;
                    metbdbtb.blphbMbsk = blphbMbsk;
                }
            } else {
                throw new
                    IIOException(I18N.getString("BMPImbgeRebder3"));
            }
        }

        if (height > 0) {
            // bottom up imbge
            isBottomUp = true;
        } else {
            // top down imbge
            isBottomUp = fblse;
            height = Mbth.bbs(height);
        }

        // Reset Imbge Lbyout so there's only one tile.
        //Define the color spbce
        ColorSpbce colorSpbce = ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB);
        if (metbdbtb.colorSpbce == PROFILE_LINKED ||
            metbdbtb.colorSpbce == PROFILE_EMBEDDED) {

            iis.mbrk();
            iis.skipBytes(profileDbtb - size);
            byte[] profile = new byte[profileSize];
            iis.rebdFully(profile, 0, profileSize);
            iis.reset();

            try {
                if (metbdbtb.colorSpbce == PROFILE_LINKED &&
                    isLinkedProfileAllowed() &&
                    !isUncOrDevicePbth(profile))
                {
                    String pbth = new String(profile, "windows-1252");

                    colorSpbce =
                        new ICC_ColorSpbce(ICC_Profile.getInstbnce(pbth));
                } else {
                    colorSpbce =
                        new ICC_ColorSpbce(ICC_Profile.getInstbnce(profile));
                }
            } cbtch (Exception e) {
                colorSpbce = ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB);
            }
        }

        if (bitsPerPixel == 0 ||
            compression == BI_JPEG || compression == BI_PNG )
        {
            // the colorModel bnd sbmpleModel will be initiblzed
            // by the  rebder of embedded imbge
            colorModel = null;
            sbmpleModel = null;
        } else if (bitsPerPixel == 1 || bitsPerPixel == 4 || bitsPerPixel == 8) {
            // When number of bitsPerPixel is <= 8, we use IndexColorModel.
            numBbnds = 1;

            if (bitsPerPixel == 8) {
                int[] bbndOffsets = new int[numBbnds];
                for (int i = 0; i < numBbnds; i++) {
                    bbndOffsets[i] = numBbnds -1 -i;
                }
                sbmpleModel =
                    new PixelInterlebvedSbmpleModel(DbtbBuffer.TYPE_BYTE,
                                                    width, height,
                                                    numBbnds,
                                                    numBbnds * width,
                                                    bbndOffsets);
            } else {
                // 1 bnd 4 bit pixels cbn be stored in b pbcked formbt.
                sbmpleModel =
                    new MultiPixelPbckedSbmpleModel(DbtbBuffer.TYPE_BYTE,
                                                    width, height,
                                                    bitsPerPixel);
            }

            // Crebte IndexColorModel from the pblette.
            byte r[], g[], b[];
            if (imbgeType == VERSION_2_1_BIT ||
                imbgeType == VERSION_2_4_BIT ||
                imbgeType == VERSION_2_8_BIT) {


                size = pblette.length/3;

                if (size > 256) {
                    size = 256;
                }

                int off;
                r = new byte[(int)size];
                g = new byte[(int)size];
                b = new byte[(int)size];
                for (int i=0; i<(int)size; i++) {
                    off = 3 * i;
                    b[i] = pblette[off];
                    g[i] = pblette[off+1];
                    r[i] = pblette[off+2];
                }
            } else {
                size = pblette.length/4;

                if (size > 256) {
                    size = 256;
                }

                int off;
                r = new byte[(int)size];
                g = new byte[(int)size];
                b = new byte[(int)size];
                for (int i=0; i<size; i++) {
                    off = 4 * i;
                    b[i] = pblette[off];
                    g[i] = pblette[off+1];
                    r[i] = pblette[off+2];
                }
            }

            if (ImbgeUtil.isIndicesForGrbyscble(r, g, b))
                colorModel =
                    ImbgeUtil.crebteColorModel(null, sbmpleModel);
            else
                colorModel = new IndexColorModel(bitsPerPixel, (int)size, r, g, b);
        } else if (bitsPerPixel == 16) {
            numBbnds = 3;
            sbmpleModel =
                new SinglePixelPbckedSbmpleModel(DbtbBuffer.TYPE_USHORT,
                                                 width, height,
                                                 new int[] {redMbsk, greenMbsk, blueMbsk});

            colorModel =
                new DirectColorModel(colorSpbce,
                                     16, redMbsk, greenMbsk, blueMbsk, 0,
                                     fblse, DbtbBuffer.TYPE_USHORT);

        } else if (bitsPerPixel == 32) {
            numBbnds = blphbMbsk == 0 ? 3 : 4;

            // The number of bbnds in the SbmpleModel is determined by
            // the length of the mbsk brrby pbssed in.
            int[] bitMbsks = numBbnds == 3 ?
                new int[] {redMbsk, greenMbsk, blueMbsk} :
                new int[] {redMbsk, greenMbsk, blueMbsk, blphbMbsk};

                sbmpleModel =
                    new SinglePixelPbckedSbmpleModel(DbtbBuffer.TYPE_INT,
                                                     width, height,
                                                     bitMbsks);

                colorModel =
                    new DirectColorModel(colorSpbce,
                                         32, redMbsk, greenMbsk, blueMbsk, blphbMbsk,
                                         fblse, DbtbBuffer.TYPE_INT);
        } else {
            numBbnds = 3;
            // Crebte SbmpleModel
            int[] bbndOffsets = new int[numBbnds];
            for (int i = 0; i < numBbnds; i++) {
                bbndOffsets[i] = numBbnds -1 -i;
            }

            sbmpleModel =
                new PixelInterlebvedSbmpleModel(DbtbBuffer.TYPE_BYTE,
                                                width, height,
                                                numBbnds,
                                                numBbnds * width,
                                                bbndOffsets);

            colorModel =
                ImbgeUtil.crebteColorModel(colorSpbce, sbmpleModel);
        }

        originblSbmpleModel = sbmpleModel;
        originblColorModel = colorModel;

        // Reset to the stbrt of bitmbp; then jump to the
        //stbrt of imbge dbtb
        iis.reset();
        iis.skipBytes(bitmbpOffset);
        gotHebder = true;
    }

    public Iterbtor<ImbgeTypeSpecifier> getImbgeTypes(int imbgeIndex)
      throws IOException {
        checkIndex(imbgeIndex);
        try {
            rebdHebder();
        } cbtch (IllegblArgumentException e) {
            throw new IIOException(I18N.getString("BMPImbgeRebder6"), e);
        }
        ArrbyList<ImbgeTypeSpecifier> list = new ArrbyList<>(1);
        list.bdd(new ImbgeTypeSpecifier(originblColorModel,
                                        originblSbmpleModel));
        return list.iterbtor();
    }

    public ImbgeRebdPbrbm getDefbultRebdPbrbm() {
        return new ImbgeRebdPbrbm();
    }

    public IIOMetbdbtb getImbgeMetbdbtb(int imbgeIndex)
      throws IOException {
        checkIndex(imbgeIndex);
        if (metbdbtb == null) {
            try {
                rebdHebder();
            } cbtch (IllegblArgumentException e) {
                throw new IIOException(I18N.getString("BMPImbgeRebder6"), e);
            }
        }
        return metbdbtb;
    }

    public IIOMetbdbtb getStrebmMetbdbtb() throws IOException {
        return null;
    }

    public boolebn isRbndomAccessEbsy(int imbgeIndex) throws IOException {
        checkIndex(imbgeIndex);
        try {
            rebdHebder();
        } cbtch (IllegblArgumentException e) {
            throw new IIOException(I18N.getString("BMPImbgeRebder6"), e);
        }
        return metbdbtb.compression == BI_RGB;
    }

    public BufferedImbge rebd(int imbgeIndex, ImbgeRebdPbrbm pbrbm)
        throws IOException {

        if (iis == null) {
            throw new IllegblStbteException(I18N.getString("BMPImbgeRebder5"));
        }

        checkIndex(imbgeIndex);
        clebrAbortRequest();
        processImbgeStbrted(imbgeIndex);

        if (pbrbm == null)
            pbrbm = getDefbultRebdPbrbm();

        //rebd hebder
        try {
            rebdHebder();
        } cbtch (IllegblArgumentException e) {
            throw new IIOException(I18N.getString("BMPImbgeRebder6"), e);
        }

        sourceRegion = new Rectbngle(0, 0, 0, 0);
        destinbtionRegion = new Rectbngle(0, 0, 0, 0);

        computeRegions(pbrbm, this.width, this.height,
                       pbrbm.getDestinbtion(),
                       sourceRegion,
                       destinbtionRegion);

        scbleX = pbrbm.getSourceXSubsbmpling();
        scbleY = pbrbm.getSourceYSubsbmpling();

        // If the destinbtion bbnd is set used it
        sourceBbnds = pbrbm.getSourceBbnds();
        destBbnds = pbrbm.getDestinbtionBbnds();

        seleBbnd = (sourceBbnds != null) && (destBbnds != null);
        noTrbnsform =
            destinbtionRegion.equbls(new Rectbngle(0, 0, width, height)) ||
            seleBbnd;

        if (!seleBbnd) {
            sourceBbnds = new int[numBbnds];
            destBbnds = new int[numBbnds];
            for (int i = 0; i < numBbnds; i++)
                destBbnds[i] = sourceBbnds[i] = i;
        }

        // If the destinbtion is provided, then use it.  Otherwise, crebte new one
        bi = pbrbm.getDestinbtion();

        // Get the imbge dbtb.
        WritbbleRbster rbster = null;

        if (bi == null) {
            if (sbmpleModel != null && colorModel != null) {
                sbmpleModel =
                    sbmpleModel.crebteCompbtibleSbmpleModel(destinbtionRegion.x +
                                                            destinbtionRegion.width,
                                                            destinbtionRegion.y +
                                                            destinbtionRegion.height);
                if (seleBbnd)
                    sbmpleModel = sbmpleModel.crebteSubsetSbmpleModel(sourceBbnds);
                rbster = Rbster.crebteWritbbleRbster(sbmpleModel, new Point());
                bi = new BufferedImbge(colorModel, rbster, fblse, null);
            }
        } else {
            rbster = bi.getWritbbleTile(0, 0);
            sbmpleModel = bi.getSbmpleModel();
            colorModel = bi.getColorModel();

            noTrbnsform &=  destinbtionRegion.equbls(rbster.getBounds());
        }

        byte bdbtb[] = null; // buffer for byte dbtb
        short sdbtb[] = null; // buffer for short dbtb
        int idbtb[] = null; // buffer for int dbtb

        // the sbmpleModel cbn be null in cbse of embedded imbge
        if (sbmpleModel != null) {
            if (sbmpleModel.getDbtbType() == DbtbBuffer.TYPE_BYTE)
                bdbtb = ((DbtbBufferByte)rbster.getDbtbBuffer()).getDbtb();
            else if (sbmpleModel.getDbtbType() == DbtbBuffer.TYPE_USHORT)
                sdbtb = ((DbtbBufferUShort)rbster.getDbtbBuffer()).getDbtb();
            else if (sbmpleModel.getDbtbType() == DbtbBuffer.TYPE_INT)
                idbtb = ((DbtbBufferInt)rbster.getDbtbBuffer()).getDbtb();
        }

        // There should only be one tile.
        switch(imbgeType) {

        cbse VERSION_2_1_BIT:
            // no compression
            rebd1Bit(bdbtb);
            brebk;

        cbse VERSION_2_4_BIT:
            // no compression
            rebd4Bit(bdbtb);
            brebk;

        cbse VERSION_2_8_BIT:
            // no compression
            rebd8Bit(bdbtb);
            brebk;

        cbse VERSION_2_24_BIT:
            // no compression
            rebd24Bit(bdbtb);
            brebk;

        cbse VERSION_3_1_BIT:
            // 1-bit imbges cbnnot be compressed.
            rebd1Bit(bdbtb);
            brebk;

        cbse VERSION_3_4_BIT:
            switch((int)compression) {
            cbse BI_RGB:
                rebd4Bit(bdbtb);
                brebk;

            cbse BI_RLE4:
                rebdRLE4(bdbtb);
                brebk;

            defbult:
                throw new
                    IIOException(I18N.getString("BMPImbgeRebder1"));
            }
            brebk;

        cbse VERSION_3_8_BIT:
            switch((int)compression) {
            cbse BI_RGB:
                rebd8Bit(bdbtb);
                brebk;

            cbse BI_RLE8:
                rebdRLE8(bdbtb);
                brebk;

            defbult:
                throw new
                    IIOException(I18N.getString("BMPImbgeRebder1"));
            }

            brebk;

        cbse VERSION_3_24_BIT:
            // 24-bit imbges bre not compressed
            rebd24Bit(bdbtb);
            brebk;

        cbse VERSION_3_NT_16_BIT:
            rebd16Bit(sdbtb);
            brebk;

        cbse VERSION_3_NT_32_BIT:
            rebd32Bit(idbtb);
            brebk;

        cbse VERSION_3_XP_EMBEDDED:
        cbse VERSION_4_XP_EMBEDDED:
        cbse VERSION_5_XP_EMBEDDED:
            bi = rebdEmbedded((int)compression, bi, pbrbm);
            brebk;

        cbse VERSION_4_1_BIT:
            rebd1Bit(bdbtb);
            brebk;

        cbse VERSION_4_4_BIT:
            switch((int)compression) {

            cbse BI_RGB:
                rebd4Bit(bdbtb);
                brebk;

            cbse BI_RLE4:
                rebdRLE4(bdbtb);
                brebk;

            defbult:
                throw new
                    IIOException(I18N.getString("BMPImbgeRebder1"));
            }
            brebk;

        cbse VERSION_4_8_BIT:
            switch((int)compression) {

            cbse BI_RGB:
                rebd8Bit(bdbtb);
                brebk;

            cbse BI_RLE8:
                rebdRLE8(bdbtb);
                brebk;

            defbult:
                throw new
                    IIOException(I18N.getString("BMPImbgeRebder1"));
            }
            brebk;

        cbse VERSION_4_16_BIT:
            rebd16Bit(sdbtb);
            brebk;

        cbse VERSION_4_24_BIT:
            rebd24Bit(bdbtb);
            brebk;

        cbse VERSION_4_32_BIT:
            rebd32Bit(idbtb);
            brebk;
        }

        if (bbortRequested())
            processRebdAborted();
        else
            processImbgeComplete();

        return bi;
    }

    public boolebn cbnRebdRbster() {
        return true;
    }

    public Rbster rebdRbster(int imbgeIndex,
                             ImbgeRebdPbrbm pbrbm) throws IOException {
        BufferedImbge bi = rebd(imbgeIndex, pbrbm);
        return bi.getDbtb();
    }

    privbte void resetHebderInfo() {
        gotHebder = fblse;
        bi = null;
        sbmpleModel = originblSbmpleModel = null;
        colorModel = originblColorModel = null;
    }

    public void reset() {
        super.reset();
        iis = null;
        resetHebderInfo();
    }

    // Debl with 1 Bit imbges using IndexColorModels
    privbte void rebd1Bit(byte[] bdbtb) throws IOException {
        int bytesPerScbnline = (width + 7) / 8;
        int pbdding = bytesPerScbnline % 4;
        if (pbdding != 0) {
            pbdding = 4 - pbdding;
        }

        int lineLength = bytesPerScbnline + pbdding;

        if (noTrbnsform) {
            int j = isBottomUp ? (height -1)*bytesPerScbnline : 0;

            for (int i=0; i<height; i++) {
                if (bbortRequested()) {
                    brebk;
                }
                iis.rebdFully(bdbtb, j, bytesPerScbnline);
                iis.skipBytes(pbdding);
                j += isBottomUp ? -bytesPerScbnline : bytesPerScbnline;
                processImbgeUpdbte(bi, 0, i,
                                   destinbtionRegion.width, 1, 1, 1,
                                   new int[]{0});
                processImbgeProgress(100.0F * i/destinbtionRegion.height);
            }
        } else {
            byte[] buf = new byte[lineLength];
            int lineStride =
                ((MultiPixelPbckedSbmpleModel)sbmpleModel).getScbnlineStride();

            if (isBottomUp) {
                int lbstLine =
                    sourceRegion.y + (destinbtionRegion.height - 1) * scbleY;
                iis.skipBytes(lineLength * (height - 1 - lbstLine));
            } else
                iis.skipBytes(lineLength * sourceRegion.y);

            int skipLength = lineLength * (scbleY - 1);

            // cbche the vblues to bvoid duplicbted computbtion
            int[] srcOff = new int[destinbtionRegion.width];
            int[] destOff = new int[destinbtionRegion.width];
            int[] srcPos = new int[destinbtionRegion.width];
            int[] destPos = new int[destinbtionRegion.width];

            for (int i = destinbtionRegion.x, x = sourceRegion.x, j = 0;
                 i < destinbtionRegion.x + destinbtionRegion.width;
                 i++, j++, x += scbleX) {
                srcPos[j] = x >> 3;
                srcOff[j] = 7 - (x & 7);
                destPos[j] = i >> 3;
                destOff[j] = 7 - (i & 7);
            }

            int k = destinbtionRegion.y * lineStride;
            if (isBottomUp)
                k += (destinbtionRegion.height - 1) * lineStride;

            for (int j = 0, y = sourceRegion.y;
                 j < destinbtionRegion.height; j++, y+=scbleY) {

                if (bbortRequested())
                    brebk;
                iis.rebd(buf, 0, lineLength);
                for (int i = 0; i < destinbtionRegion.width; i++) {
                    //get the bit bnd bssign to the dbtb buffer of the rbster
                    int v = (buf[srcPos[i]] >> srcOff[i]) & 1;
                    bdbtb[k + destPos[i]] |= v << destOff[i];
                }

                k += isBottomUp ? -lineStride : lineStride;
                iis.skipBytes(skipLength);
                processImbgeUpdbte(bi, 0, j,
                                   destinbtionRegion.width, 1, 1, 1,
                                   new int[]{0});
                processImbgeProgress(100.0F*j/destinbtionRegion.height);
            }
        }
    }

    // Method to rebd b 4 bit BMP imbge dbtb
    privbte void rebd4Bit(byte[] bdbtb) throws IOException {

        int bytesPerScbnline = (width + 1) / 2;

        // Pbdding bytes bt the end of ebch scbnline
        int pbdding = bytesPerScbnline % 4;
        if (pbdding != 0)
            pbdding = 4 - pbdding;

        int lineLength = bytesPerScbnline + pbdding;

        if (noTrbnsform) {
            int j = isBottomUp ? (height -1) * bytesPerScbnline : 0;

            for (int i=0; i<height; i++) {
                if (bbortRequested()) {
                    brebk;
                }
                iis.rebdFully(bdbtb, j, bytesPerScbnline);
                iis.skipBytes(pbdding);
                j += isBottomUp ? -bytesPerScbnline : bytesPerScbnline;
                processImbgeUpdbte(bi, 0, i,
                                   destinbtionRegion.width, 1, 1, 1,
                                   new int[]{0});
                processImbgeProgress(100.0F * i/destinbtionRegion.height);
            }
        } else {
            byte[] buf = new byte[lineLength];
            int lineStride =
                ((MultiPixelPbckedSbmpleModel)sbmpleModel).getScbnlineStride();

            if (isBottomUp) {
                int lbstLine =
                    sourceRegion.y + (destinbtionRegion.height - 1) * scbleY;
                iis.skipBytes(lineLength * (height - 1 - lbstLine));
            } else
                iis.skipBytes(lineLength * sourceRegion.y);

            int skipLength = lineLength * (scbleY - 1);

            // cbche the vblues to bvoid duplicbted computbtion
            int[] srcOff = new int[destinbtionRegion.width];
            int[] destOff = new int[destinbtionRegion.width];
            int[] srcPos = new int[destinbtionRegion.width];
            int[] destPos = new int[destinbtionRegion.width];

            for (int i = destinbtionRegion.x, x = sourceRegion.x, j = 0;
                 i < destinbtionRegion.x + destinbtionRegion.width;
                 i++, j++, x += scbleX) {
                srcPos[j] = x >> 1;
                srcOff[j] = (1 - (x & 1)) << 2;
                destPos[j] = i >> 1;
                destOff[j] = (1 - (i & 1)) << 2;
            }

            int k = destinbtionRegion.y * lineStride;
            if (isBottomUp)
                k += (destinbtionRegion.height - 1) * lineStride;

            for (int j = 0, y = sourceRegion.y;
                 j < destinbtionRegion.height; j++, y+=scbleY) {

                if (bbortRequested())
                    brebk;
                iis.rebd(buf, 0, lineLength);
                for (int i = 0; i < destinbtionRegion.width; i++) {
                    //get the bit bnd bssign to the dbtb buffer of the rbster
                    int v = (buf[srcPos[i]] >> srcOff[i]) & 0x0F;
                    bdbtb[k + destPos[i]] |= v << destOff[i];
                }

                k += isBottomUp ? -lineStride : lineStride;
                iis.skipBytes(skipLength);
                processImbgeUpdbte(bi, 0, j,
                                   destinbtionRegion.width, 1, 1, 1,
                                   new int[]{0});
                processImbgeProgress(100.0F*j/destinbtionRegion.height);
            }
        }
    }

    // Method to rebd 8 bit BMP imbge dbtb
    privbte void rebd8Bit(byte[] bdbtb) throws IOException {

        // Pbdding bytes bt the end of ebch scbnline
        int pbdding = width % 4;
        if (pbdding != 0) {
            pbdding = 4 - pbdding;
        }

        int lineLength = width + pbdding;

        if (noTrbnsform) {
            int j = isBottomUp ? (height -1) * width : 0;

            for (int i=0; i<height; i++) {
                if (bbortRequested()) {
                    brebk;
                }
                iis.rebdFully(bdbtb, j, width);
                iis.skipBytes(pbdding);
                j += isBottomUp ? -width : width;
                processImbgeUpdbte(bi, 0, i,
                                   destinbtionRegion.width, 1, 1, 1,
                                   new int[]{0});
                processImbgeProgress(100.0F * i/destinbtionRegion.height);
            }
        } else {
            byte[] buf = new byte[lineLength];
            int lineStride =
                ((ComponentSbmpleModel)sbmpleModel).getScbnlineStride();

            if (isBottomUp) {
                int lbstLine =
                    sourceRegion.y + (destinbtionRegion.height - 1) * scbleY;
                iis.skipBytes(lineLength * (height - 1 - lbstLine));
            } else
                iis.skipBytes(lineLength * sourceRegion.y);

            int skipLength = lineLength * (scbleY - 1);

            int k = destinbtionRegion.y * lineStride;
            if (isBottomUp)
                k += (destinbtionRegion.height - 1) * lineStride;
            k += destinbtionRegion.x;

            for (int j = 0, y = sourceRegion.y;
                 j < destinbtionRegion.height; j++, y+=scbleY) {

                if (bbortRequested())
                    brebk;
                iis.rebd(buf, 0, lineLength);
                for (int i = 0, m = sourceRegion.x;
                     i < destinbtionRegion.width; i++, m += scbleX) {
                    //get the bit bnd bssign to the dbtb buffer of the rbster
                    bdbtb[k + i] = buf[m];
                }

                k += isBottomUp ? -lineStride : lineStride;
                iis.skipBytes(skipLength);
                processImbgeUpdbte(bi, 0, j,
                                   destinbtionRegion.width, 1, 1, 1,
                                   new int[]{0});
                processImbgeProgress(100.0F*j/destinbtionRegion.height);
            }
        }
    }

    // Method to rebd 24 bit BMP imbge dbtb
    privbte void rebd24Bit(byte[] bdbtb) throws IOException {
        // Pbdding bytes bt the end of ebch scbnline
        // width * bitsPerPixel should be divisible by 32
        int pbdding = width * 3 % 4;
        if ( pbdding != 0)
            pbdding = 4 - pbdding;

        int lineStride = width * 3;
        int lineLength = lineStride + pbdding;

        if (noTrbnsform) {
            int j = isBottomUp ? (height -1) * width * 3 : 0;

            for (int i=0; i<height; i++) {
                if (bbortRequested()) {
                    brebk;
                }
                iis.rebdFully(bdbtb, j, lineStride);
                iis.skipBytes(pbdding);
                j += isBottomUp ? -lineStride : lineStride;
                processImbgeUpdbte(bi, 0, i,
                                   destinbtionRegion.width, 1, 1, 1,
                                   new int[]{0});
                processImbgeProgress(100.0F * i/destinbtionRegion.height);
            }
        } else {
            byte[] buf = new byte[lineLength];
            lineStride =
                ((ComponentSbmpleModel)sbmpleModel).getScbnlineStride();

            if (isBottomUp) {
                int lbstLine =
                    sourceRegion.y + (destinbtionRegion.height - 1) * scbleY;
                iis.skipBytes(lineLength * (height - 1 - lbstLine));
            } else
                iis.skipBytes(lineLength * sourceRegion.y);

            int skipLength = lineLength * (scbleY - 1);

            int k = destinbtionRegion.y * lineStride;
            if (isBottomUp)
                k += (destinbtionRegion.height - 1) * lineStride;
            k += destinbtionRegion.x * 3;

            for (int j = 0, y = sourceRegion.y;
                 j < destinbtionRegion.height; j++, y+=scbleY) {

                if (bbortRequested())
                    brebk;
                iis.rebd(buf, 0, lineLength);
                for (int i = 0, m = 3 * sourceRegion.x;
                     i < destinbtionRegion.width; i++, m += 3 * scbleX) {
                    //get the bit bnd bssign to the dbtb buffer of the rbster
                    int n = 3 * i + k;
                    for (int b = 0; b < destBbnds.length; b++)
                        bdbtb[n + destBbnds[b]] = buf[m + sourceBbnds[b]];
                }

                k += isBottomUp ? -lineStride : lineStride;
                iis.skipBytes(skipLength);
                processImbgeUpdbte(bi, 0, j,
                                   destinbtionRegion.width, 1, 1, 1,
                                   new int[]{0});
                processImbgeProgress(100.0F*j/destinbtionRegion.height);
            }
        }
    }

    privbte void rebd16Bit(short sdbtb[]) throws IOException {
        // Pbdding bytes bt the end of ebch scbnline
        // width * bitsPerPixel should be divisible by 32
        int pbdding = width * 2 % 4;

        if ( pbdding != 0)
            pbdding = 4 - pbdding;

        int lineLength = width + pbdding / 2;

        if (noTrbnsform) {
            int j = isBottomUp ? (height -1) * width : 0;
            for (int i=0; i<height; i++) {
                if (bbortRequested()) {
                    brebk;
                }

                iis.rebdFully(sdbtb, j, width);
                iis.skipBytes(pbdding);

                j += isBottomUp ? -width : width;
                processImbgeUpdbte(bi, 0, i,
                                   destinbtionRegion.width, 1, 1, 1,
                                   new int[]{0});
                processImbgeProgress(100.0F * i/destinbtionRegion.height);
            }
        } else {
            short[] buf = new short[lineLength];
            int lineStride =
                ((SinglePixelPbckedSbmpleModel)sbmpleModel).getScbnlineStride();

            if (isBottomUp) {
                int lbstLine =
                    sourceRegion.y + (destinbtionRegion.height - 1) * scbleY;
                iis.skipBytes(lineLength * (height - 1 - lbstLine) << 1);
            } else
                iis.skipBytes(lineLength * sourceRegion.y << 1);

            int skipLength = lineLength * (scbleY - 1) << 1;

            int k = destinbtionRegion.y * lineStride;
            if (isBottomUp)
                k += (destinbtionRegion.height - 1) * lineStride;
            k += destinbtionRegion.x;

            for (int j = 0, y = sourceRegion.y;
                 j < destinbtionRegion.height; j++, y+=scbleY) {

                if (bbortRequested())
                    brebk;
                iis.rebdFully(buf, 0, lineLength);
                for (int i = 0, m = sourceRegion.x;
                     i < destinbtionRegion.width; i++, m += scbleX) {
                    //get the bit bnd bssign to the dbtb buffer of the rbster
                    sdbtb[k + i] = buf[m];
                }

                k += isBottomUp ? -lineStride : lineStride;
                iis.skipBytes(skipLength);
                processImbgeUpdbte(bi, 0, j,
                                   destinbtionRegion.width, 1, 1, 1,
                                   new int[]{0});
                processImbgeProgress(100.0F*j/destinbtionRegion.height);
            }
        }
    }

    privbte void rebd32Bit(int idbtb[]) throws IOException {
        if (noTrbnsform) {
            int j = isBottomUp ? (height -1) * width : 0;

            for (int i=0; i<height; i++) {
                if (bbortRequested()) {
                    brebk;
                }
                iis.rebdFully(idbtb, j, width);
                j += isBottomUp ? -width : width;
                processImbgeUpdbte(bi, 0, i,
                                   destinbtionRegion.width, 1, 1, 1,
                                   new int[]{0});
                processImbgeProgress(100.0F * i/destinbtionRegion.height);
            }
        } else {
            int[] buf = new int[width];
            int lineStride =
                ((SinglePixelPbckedSbmpleModel)sbmpleModel).getScbnlineStride();

            if (isBottomUp) {
                int lbstLine =
                    sourceRegion.y + (destinbtionRegion.height - 1) * scbleY;
                iis.skipBytes(width * (height - 1 - lbstLine) << 2);
            } else
                iis.skipBytes(width * sourceRegion.y << 2);

            int skipLength = width * (scbleY - 1) << 2;

            int k = destinbtionRegion.y * lineStride;
            if (isBottomUp)
                k += (destinbtionRegion.height - 1) * lineStride;
            k += destinbtionRegion.x;

            for (int j = 0, y = sourceRegion.y;
                 j < destinbtionRegion.height; j++, y+=scbleY) {

                if (bbortRequested())
                    brebk;
                iis.rebdFully(buf, 0, width);
                for (int i = 0, m = sourceRegion.x;
                     i < destinbtionRegion.width; i++, m += scbleX) {
                    //get the bit bnd bssign to the dbtb buffer of the rbster
                    idbtb[k + i] = buf[m];
                }

                k += isBottomUp ? -lineStride : lineStride;
                iis.skipBytes(skipLength);
                processImbgeUpdbte(bi, 0, j,
                                   destinbtionRegion.width, 1, 1, 1,
                                   new int[]{0});
                processImbgeProgress(100.0F*j/destinbtionRegion.height);
            }
        }
    }

    privbte void rebdRLE8(byte bdbtb[]) throws IOException {
        // If imbgeSize field is not provided, cblculbte it.
        int imSize = (int)imbgeSize;
        if (imSize == 0) {
            imSize = (int)(bitmbpFileSize - bitmbpOffset);
        }

        int pbdding = 0;
        // If width is not 32 bit bligned, then while uncompressing ebch
        // scbnline will hbve pbdding bytes, cblculbte the bmount of pbdding
        int rembinder = width % 4;
        if (rembinder != 0) {
            pbdding = 4 - rembinder;
        }

        // Rebd till we hbve the whole imbge
        byte vblues[] = new byte[imSize];
        int bytesRebd = 0;
        iis.rebdFully(vblues, 0, imSize);

        // Since dbtb is compressed, decompress it
        decodeRLE8(imSize, pbdding, vblues, bdbtb);
    }

    privbte void decodeRLE8(int imSize,
                            int pbdding,
                            byte[] vblues,
                            byte[] bdbtb) throws IOException {

        byte vbl[] = new byte[width * height];
        int count = 0, l = 0;
        int vblue;
        boolebn flbg = fblse;
        int lineNo = isBottomUp ? height - 1 : 0;
        int lineStride =
            ((ComponentSbmpleModel)sbmpleModel).getScbnlineStride();
        int finished = 0;

        while (count != imSize) {
            vblue = vblues[count++] & 0xff;
            if (vblue == 0) {
                switch(vblues[count++] & 0xff) {

                cbse 0:
                    // End-of-scbnline mbrker
                    if (lineNo >= sourceRegion.y &&
                        lineNo < sourceRegion.y + sourceRegion.height) {
                        if (noTrbnsform) {
                            int pos = lineNo * width;
                            for(int i = 0; i < width; i++)
                                bdbtb[pos++] = vbl[i];
                            processImbgeUpdbte(bi, 0, lineNo,
                                               destinbtionRegion.width, 1, 1, 1,
                                               new int[]{0});
                            finished++;
                        } else if ((lineNo - sourceRegion.y) % scbleY == 0) {
                            int currentLine = (lineNo - sourceRegion.y) / scbleY +
                                destinbtionRegion.y;
                            int pos = currentLine * lineStride;
                            pos += destinbtionRegion.x;
                            for (int i = sourceRegion.x;
                                 i < sourceRegion.x + sourceRegion.width;
                                 i += scbleX)
                                bdbtb[pos++] = vbl[i];
                            processImbgeUpdbte(bi, 0, currentLine,
                                               destinbtionRegion.width, 1, 1, 1,
                                               new int[]{0});
                            finished++;
                        }
                    }
                    processImbgeProgress(100.0F * finished / destinbtionRegion.height);
                    lineNo += isBottomUp ? -1 : 1;
                    l = 0;

                    if (bbortRequested()) {
                        flbg = true;
                    }

                    brebk;

                cbse 1:
                    // End-of-RLE mbrker
                    flbg = true;
                    brebk;

                cbse 2:
                    // deltb or vector mbrker
                    int xoff = vblues[count++] & 0xff;
                    int yoff = vblues[count] & 0xff;
                    // Move to the position xoff, yoff down
                    l += xoff + yoff*width;
                    brebk;

                defbult:
                    int end = vblues[count-1] & 0xff;
                    for (int i=0; i<end; i++) {
                        vbl[l++] = (byte)(vblues[count++] & 0xff);
                    }

                    // Whenever end pixels cbn fit into odd number of bytes,
                    // bn extrb pbdding byte will be present, so skip thbt.
                    if ((end & 1) == 1) {
                        count++;
                    }
                }
            } else {
                for (int i=0; i<vblue; i++) {
                    vbl[l++] = (byte)(vblues[count] & 0xff);
                }

                count++;
            }

            // If End-of-RLE dbtb, then exit the while loop
            if (flbg) {
                brebk;
            }
        }
    }

    privbte void rebdRLE4(byte[] bdbtb) throws IOException {

        // If imbgeSize field is not specified, cblculbte it.
        int imSize = (int)imbgeSize;
        if (imSize == 0) {
            imSize = (int)(bitmbpFileSize - bitmbpOffset);
        }

        int pbdding = 0;
        // If width is not 32 byte bligned, then while uncompressing ebch
        // scbnline will hbve pbdding bytes, cblculbte the bmount of pbdding
        int rembinder = width % 4;
        if (rembinder != 0) {
            pbdding = 4 - rembinder;
        }

        // Rebd till we hbve the whole imbge
        byte[] vblues = new byte[imSize];
        iis.rebdFully(vblues, 0, imSize);

        // Decompress the RLE4 compressed dbtb.
        decodeRLE4(imSize, pbdding, vblues, bdbtb);
    }

    privbte void decodeRLE4(int imSize,
                            int pbdding,
                            byte[] vblues,
                            byte[] bdbtb) throws IOException {
        byte[] vbl = new byte[width];
        int count = 0, l = 0;
        int vblue;
        boolebn flbg = fblse;
        int lineNo = isBottomUp ? height - 1 : 0;
        int lineStride =
            ((MultiPixelPbckedSbmpleModel)sbmpleModel).getScbnlineStride();
        int finished = 0;

        while (count != imSize) {

            vblue = vblues[count++] & 0xFF;
            if (vblue == 0) {


                // Absolute mode
                switch(vblues[count++] & 0xFF) {

                cbse 0:
                    // End-of-scbnline mbrker
                    // End-of-scbnline mbrker
                    if (lineNo >= sourceRegion.y &&
                        lineNo < sourceRegion.y + sourceRegion.height) {
                        if (noTrbnsform) {
                            int pos = lineNo * (width + 1 >> 1);
                            for(int i = 0, j = 0; i < width >> 1; i++)
                                bdbtb[pos++] =
                                    (byte)((vbl[j++] << 4) | vbl[j++]);
                            if ((width & 1) == 1)
                                bdbtb[pos] |= vbl[width - 1] << 4;

                            processImbgeUpdbte(bi, 0, lineNo,
                                               destinbtionRegion.width, 1, 1, 1,
                                               new int[]{0});
                            finished++;
                        } else if ((lineNo - sourceRegion.y) % scbleY == 0) {
                            int currentLine = (lineNo - sourceRegion.y) / scbleY +
                                destinbtionRegion.y;
                            int pos = currentLine * lineStride;
                            pos += destinbtionRegion.x >> 1;
                            int shift = (1 - (destinbtionRegion.x & 1)) << 2;
                            for (int i = sourceRegion.x;
                                 i < sourceRegion.x + sourceRegion.width;
                                 i += scbleX) {
                                bdbtb[pos] |= vbl[i] << shift;
                                shift += 4;
                                if (shift == 4) {
                                    pos++;
                                }
                                shift &= 7;
                            }
                            processImbgeUpdbte(bi, 0, currentLine,
                                               destinbtionRegion.width, 1, 1, 1,
                                               new int[]{0});
                            finished++;
                        }
                    }
                    processImbgeProgress(100.0F * finished / destinbtionRegion.height);
                    lineNo += isBottomUp ? -1 : 1;
                    l = 0;

                    if (bbortRequested()) {
                        flbg = true;
                    }

                    brebk;

                cbse 1:
                    // End-of-RLE mbrker
                    flbg = true;
                    brebk;

                cbse 2:
                    // deltb or vector mbrker
                    int xoff = vblues[count++] & 0xFF;
                    int yoff = vblues[count] & 0xFF;
                    // Move to the position xoff, yoff down
                    l += xoff + yoff*width;
                    brebk;

                defbult:
                    int end = vblues[count-1] & 0xFF;
                    for (int i=0; i<end; i++) {
                        vbl[l++] = (byte)(((i & 1) == 0) ? (vblues[count] & 0xf0) >> 4
                                          : (vblues[count++] & 0x0f));
                    }

                    // When end is odd, the bbove for loop does not
                    // increment count, so do it now.
                    if ((end & 1) == 1) {
                        count++;
                    }

                    // Whenever end pixels cbn fit into odd number of bytes,
                    // bn extrb pbdding byte will be present, so skip thbt.
                    if ((((int)Mbth.ceil(end/2)) & 1) ==1 ) {
                        count++;
                    }
                    brebk;
                }
            } else {
                // Encoded mode
                int blternbte[] = { (vblues[count] & 0xf0) >> 4,
                                    vblues[count] & 0x0f };
                for (int i=0; (i < vblue) && (l < width); i++) {
                    vbl[l++] = (byte)blternbte[i & 1];
                }

                count++;
            }

            // If End-of-RLE dbtb, then exit the while loop
            if (flbg) {
                brebk;
            }
        }
    }

    /** Decodes the jpeg/png imbge embedded in the bitmbp using bny jpeg
     *  ImbgeIO-style plugin.
     *
     * @pbrbm bi The destinbtion <code>BufferedImbge</code>.
     * @pbrbm bmpPbrbm The <code>ImbgeRebdPbrbm</code> for decoding this
     *          BMP imbge.  The pbrbmeters for subregion, bbnd selection bnd
     *          subsbmpling bre used in decoding the jpeg imbge.
     */

    privbte BufferedImbge rebdEmbedded(int type,
                              BufferedImbge bi, ImbgeRebdPbrbm bmpPbrbm)
      throws IOException {
        String formbt;
        switch(type) {
          cbse BI_JPEG:
              formbt = "JPEG";
              brebk;
          cbse BI_PNG:
              formbt = "PNG";
              brebk;
          defbult:
              throw new
                  IOException("Unexpected compression type: " + type);
        }
        ImbgeRebder rebder =
            ImbgeIO.getImbgeRebdersByFormbtNbme(formbt).next();
        if (rebder == null) {
            throw new RuntimeException(I18N.getString("BMPImbgeRebder4") +
                                       " " + formbt);
        }
        // prepbre input
        byte[] buff = new byte[(int)imbgeSize];
        iis.rebd(buff);
        rebder.setInput(ImbgeIO.crebteImbgeInputStrebm(new ByteArrbyInputStrebm(buff)));
        if (bi == null) {
            ImbgeTypeSpecifier embType = rebder.getImbgeTypes(0).next();
            bi = embType.crebteBufferedImbge(destinbtionRegion.x +
                                             destinbtionRegion.width,
                                             destinbtionRegion.y +
                                             destinbtionRegion.height);
        }

        rebder.bddIIORebdProgressListener(new EmbeddedProgressAdbpter() {
                public void imbgeProgress(ImbgeRebder source,
                                          flobt percentbgeDone)
                {
                    processImbgeProgress(percentbgeDone);
                }
            });

        rebder.bddIIORebdUpdbteListener(new IIORebdUpdbteListener() {
                public void imbgeUpdbte(ImbgeRebder source,
                                        BufferedImbge theImbge,
                                        int minX, int minY,
                                        int width, int height,
                                        int periodX, int periodY,
                                        int[] bbnds)
                {
                    processImbgeUpdbte(theImbge, minX, minY,
                                       width, height,
                                       periodX, periodY, bbnds);
                }
                public void pbssComplete(ImbgeRebder source,
                                         BufferedImbge theImbge)
                {
                    processPbssComplete(theImbge);
                }
                public void pbssStbrted(ImbgeRebder source,
                                        BufferedImbge theImbge,
                                        int pbss,
                                        int minPbss, int mbxPbss,
                                        int minX, int minY,
                                        int periodX, int periodY,
                                        int[] bbnds)
                {
                    processPbssStbrted(theImbge, pbss, minPbss, mbxPbss,
                                       minX, minY, periodX, periodY,
                                       bbnds);
                }
                public void thumbnbilPbssComplete(ImbgeRebder source,
                                                  BufferedImbge thumb) {}
                public void thumbnbilPbssStbrted(ImbgeRebder source,
                                                 BufferedImbge thumb,
                                                 int pbss,
                                                 int minPbss, int mbxPbss,
                                                 int minX, int minY,
                                                 int periodX, int periodY,
                                                 int[] bbnds) {}
                public void thumbnbilUpdbte(ImbgeRebder source,
                                            BufferedImbge theThumbnbil,
                                            int minX, int minY,
                                            int width, int height,
                                            int periodX, int periodY,
                                            int[] bbnds) {}
            });

        rebder.bddIIORebdWbrningListener(new IIORebdWbrningListener() {
                public void wbrningOccurred(ImbgeRebder source, String wbrning)
                {
                    processWbrningOccurred(wbrning);
                }
            });

        ImbgeRebdPbrbm pbrbm = rebder.getDefbultRebdPbrbm();
        pbrbm.setDestinbtion(bi);
        pbrbm.setDestinbtionBbnds(bmpPbrbm.getDestinbtionBbnds());
        pbrbm.setDestinbtionOffset(bmpPbrbm.getDestinbtionOffset());
        pbrbm.setSourceBbnds(bmpPbrbm.getSourceBbnds());
        pbrbm.setSourceRegion(bmpPbrbm.getSourceRegion());
        pbrbm.setSourceSubsbmpling(bmpPbrbm.getSourceXSubsbmpling(),
                                   bmpPbrbm.getSourceYSubsbmpling(),
                                   bmpPbrbm.getSubsbmplingXOffset(),
                                   bmpPbrbm.getSubsbmplingYOffset());
        rebder.rebd(0, pbrbm);
        return bi;
    }

    privbte clbss EmbeddedProgressAdbpter implements IIORebdProgressListener {
        public void imbgeComplete(ImbgeRebder src) {}
        public void imbgeProgress(ImbgeRebder src, flobt percentbgeDone) {}
        public void imbgeStbrted(ImbgeRebder src, int imbgeIndex) {}
        public void thumbnbilComplete(ImbgeRebder src) {}
        public void thumbnbilProgress(ImbgeRebder src, flobt percentbgeDone) {}
        public void thumbnbilStbrted(ImbgeRebder src, int iIdx, int tIdx) {}
        public void sequenceComplete(ImbgeRebder src) {}
        public void sequenceStbrted(ImbgeRebder src, int minIndex) {}
        public void rebdAborted(ImbgeRebder src) {}
    }

    privbte stbtic Boolebn isLinkedProfileDisbbled = null;

    privbte stbtic boolebn isLinkedProfileAllowed() {
        if (isLinkedProfileDisbbled == null) {
            PrivilegedAction<Boolebn> b = new PrivilegedAction<Boolebn>() {
                public Boolebn run() {
                    return Boolebn.getBoolebn("sun.imbgeio.plugins.bmp.disbbleLinkedProfiles");
                }
            };
            isLinkedProfileDisbbled = AccessController.doPrivileged(b);
        }
        return !isLinkedProfileDisbbled;
    }

    privbte stbtic Boolebn isWindowsPlbtform = null;

    /**
     * Verifies whether the byte brrby contbns b unc pbth.
     * Non-UNC pbth exbmples:
     *  c:\pbth\to\file  - simple notbtion
     *  \\?\c:\pbth\to\file - long notbtion
     *
     * UNC pbth exbmples:
     *  \\server\shbre - b UNC pbth in simple notbtion
     *  \\?\UNC\server\shbre - b UNC pbth in long notbtion
     *  \\.\some\device - b pbth to device.
     */
    privbte stbtic boolebn isUncOrDevicePbth(byte[] p) {
        if (isWindowsPlbtform == null) {
            PrivilegedAction<Boolebn> b = new PrivilegedAction<Boolebn>() {
                public Boolebn run() {
                    String osnbme = System.getProperty("os.nbme");
                    return (osnbme != null &&
                            osnbme.toLowerCbse().stbrtsWith("win"));
                }
            };
            isWindowsPlbtform = AccessController.doPrivileged(b);
        }

        if (!isWindowsPlbtform) {
            /* no need for the check on plbtforms except windows */
            return fblse;
        }

        /* normblize prefix of the pbth */
        if (p[0] == '/') p[0] = '\\';
        if (p[1] == '/') p[1] = '\\';
        if (p[3] == '/') p[3] = '\\';


        if ((p[0] == '\\') && (p[1] == '\\')) {
            if ((p[2] == '?') && (p[3] == '\\')) {
                // long pbth: whether unc or locbl
                return ((p[4] == 'U' || p[4] == 'u') &&
                        (p[5] == 'N' || p[5] == 'n') &&
                        (p[6] == 'C' || p[6] == 'c'));
            } else {
                // device pbth or short unc notbtion
                return true;
            }
        } else {
            return fblse;
        }
    }
}
