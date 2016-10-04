/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf dom.sun.imbgfio.plugins.bmp;

import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.dolor.ICC_ColorSpbdf;
import jbvb.bwt.dolor.ICC_Profilf;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.ComponfntColorModfl;
import jbvb.bwt.imbgf.ComponfntSbmplfModfl;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.DbtbBufffrBytf;
import jbvb.bwt.imbgf.DbtbBufffrInt;
import jbvb.bwt.imbgf.DbtbBufffrUSiort;
import jbvb.bwt.imbgf.DirfdtColorModfl;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.imbgf.MultiPixflPbdkfdSbmplfModfl;
import jbvb.bwt.imbgf.PixflIntfrlfbvfdSbmplfModfl;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.SbmplfModfl;
import jbvb.bwt.imbgf.SinglfPixflPbdkfdSbmplfModfl;
import jbvb.bwt.imbgf.WritbblfRbstfr;

import jbvbx.imbgfio.IIOExdfption;
import jbvbx.imbgfio.ImbgfIO;
import jbvbx.imbgfio.ImbgfRfbdfr;
import jbvbx.imbgfio.ImbgfRfbdPbrbm;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtb;
import jbvbx.imbgfio.spi.ImbgfRfbdfrSpi;
import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;
import jbvbx.imbgfio.fvfnt.IIORfbdProgrfssListfnfr;
import jbvbx.imbgfio.fvfnt.IIORfbdUpdbtfListfnfr;
import jbvbx.imbgfio.fvfnt.IIORfbdWbrningListfnfr;

import jbvb.io.*;
import jbvb.nio.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;
import jbvb.util.StringTokfnizfr;

import dom.sun.imbgfio.plugins.dommon.ImbgfUtil;
import dom.sun.imbgfio.plugins.dommon.I18N;

/** Tiis dlbss is tif Jbvb Imbgf IO plugin rfbdfr for BMP imbgfs.
 *  It mby subsbmplf tif imbgf, dlip tif imbgf, sflfdt sub-bbnds,
 *  bnd siift tif dfdodfd imbgf origin if tif propfr dfdoding pbrbmftfr
 *  brf sft in tif providfd <dodf>ImbgfRfbdPbrbm</dodf>.
 *
 *  Tiis dlbss supports Midrosoft Windows Bitmbp Vfrsion 3-5,
 *  bs wfll bs OS/2 Bitmbp Vfrsion 2.x (for singlf-imbgf BMP filf).
 */
publid dlbss BMPImbgfRfbdfr fxtfnds ImbgfRfbdfr implfmfnts BMPConstbnts {
    // BMP Imbgf typfs
    privbtf stbtid finbl int VERSION_2_1_BIT = 0;
    privbtf stbtid finbl int VERSION_2_4_BIT = 1;
    privbtf stbtid finbl int VERSION_2_8_BIT = 2;
    privbtf stbtid finbl int VERSION_2_24_BIT = 3;

    privbtf stbtid finbl int VERSION_3_1_BIT = 4;
    privbtf stbtid finbl int VERSION_3_4_BIT = 5;
    privbtf stbtid finbl int VERSION_3_8_BIT = 6;
    privbtf stbtid finbl int VERSION_3_24_BIT = 7;

    privbtf stbtid finbl int VERSION_3_NT_16_BIT = 8;
    privbtf stbtid finbl int VERSION_3_NT_32_BIT = 9;

    privbtf stbtid finbl int VERSION_4_1_BIT = 10;
    privbtf stbtid finbl int VERSION_4_4_BIT = 11;
    privbtf stbtid finbl int VERSION_4_8_BIT = 12;
    privbtf stbtid finbl int VERSION_4_16_BIT = 13;
    privbtf stbtid finbl int VERSION_4_24_BIT = 14;
    privbtf stbtid finbl int VERSION_4_32_BIT = 15;

    privbtf stbtid finbl int VERSION_3_XP_EMBEDDED = 16;
    privbtf stbtid finbl int VERSION_4_XP_EMBEDDED = 17;
    privbtf stbtid finbl int VERSION_5_XP_EMBEDDED = 18;

    // BMP vbribblfs
    privbtf long bitmbpFilfSizf;
    privbtf long bitmbpOffsft;
    privbtf long domprfssion;
    privbtf long imbgfSizf;
    privbtf bytf pblfttf[];
    privbtf int imbgfTypf;
    privbtf int numBbnds;
    privbtf boolfbn isBottomUp;
    privbtf int bitsPfrPixfl;
    privbtf int rfdMbsk, grffnMbsk, blufMbsk, blpibMbsk;

    privbtf SbmplfModfl sbmplfModfl, originblSbmplfModfl;
    privbtf ColorModfl dolorModfl, originblColorModfl;

    /** Tif input strfbm wifrf rfbds from */
    privbtf ImbgfInputStrfbm iis = null;

    /** Indidbtfs wiftifr tif ifbdfr is rfbd. */
    privbtf boolfbn gotHfbdfr = fblsf;

    /** Tif originbl imbgf widti. */
    privbtf int widti;

    /** Tif originbl imbgf ifigit. */
    privbtf int ifigit;

    /** Tif dfstinbtion rfgion. */
    privbtf Rfdtbnglf dfstinbtionRfgion;

    /** Tif sourdf rfgion. */
    privbtf Rfdtbnglf sourdfRfgion;

    /** Tif mftbdbtb from tif strfbm. */
    privbtf BMPMftbdbtb mftbdbtb;

    /** Tif dfstinbtion imbgf. */
    privbtf BufffrfdImbgf bi;

    /** Indidbtfs wiftifr subsbmplfd, subrfgion is rfquirfd, bnd offsft is
     *  dffinfd
     */
    privbtf boolfbn noTrbnsform = truf;

    /** Indidbtfs wiftifr subbbnd is sflfdtfd. */
    privbtf boolfbn sflfBbnd = fblsf;

    /** Tif sdbling fbdtors. */
    privbtf int sdblfX, sdblfY;

    /** sourdf bnd dfstinbtion bbnds. */
    privbtf int[] sourdfBbnds, dfstBbnds;

    /** Construdts <dodf>BMPImbgfRfbdfr</dodf> from tif providfd
     *  <dodf>ImbgfRfbdfrSpi</dodf>.
     */
    publid BMPImbgfRfbdfr(ImbgfRfbdfrSpi originbtor) {
        supfr(originbtor);
    }

    /** Ovfrridfs tif mftiod dffinfd in tif supfrdlbss. */
    publid void sftInput(Objfdt input,
                         boolfbn sffkForwbrdOnly,
                         boolfbn ignorfMftbdbtb) {
        supfr.sftInput(input, sffkForwbrdOnly, ignorfMftbdbtb);
        iis = (ImbgfInputStrfbm) input; // Alwbys works
        if(iis != null)
            iis.sftBytfOrdfr(BytfOrdfr.LITTLE_ENDIAN);
        rfsftHfbdfrInfo();
    }

    /** Ovfrridfs tif mftiod dffinfd in tif supfrdlbss. */
    publid int gftNumImbgfs(boolfbn bllowSfbrdi) tirows IOExdfption {
        if (iis == null) {
            tirow nfw IllfgblStbtfExdfption(I18N.gftString("GftNumImbgfs0"));
        }
        if (sffkForwbrdOnly && bllowSfbrdi) {
            tirow nfw IllfgblStbtfExdfption(I18N.gftString("GftNumImbgfs1"));
        }
        rfturn 1;
    }

    @Ovfrridf
    publid int gftWidti(int imbgfIndfx) tirows IOExdfption {
        difdkIndfx(imbgfIndfx);
        try {
            rfbdHfbdfr();
        } dbtdi (IllfgblArgumfntExdfption f) {
            tirow nfw IIOExdfption(I18N.gftString("BMPImbgfRfbdfr6"), f);
        }
        rfturn widti;
    }

    publid int gftHfigit(int imbgfIndfx) tirows IOExdfption {
        difdkIndfx(imbgfIndfx);
        try {
            rfbdHfbdfr();
        } dbtdi (IllfgblArgumfntExdfption f) {
            tirow nfw IIOExdfption(I18N.gftString("BMPImbgfRfbdfr6"), f);
        }
        rfturn ifigit;
    }

    privbtf void difdkIndfx(int imbgfIndfx) {
        if (imbgfIndfx != 0) {
            tirow nfw IndfxOutOfBoundsExdfption(I18N.gftString("BMPImbgfRfbdfr0"));
        }
    }

    /**
     * Prodfss tif imbgf ifbdfr.
     *
     * @fxdfption IllfgblStbtfExdfption if sourdf strfbm is not sft.
     *
     * @fxdfption IOExdfption if imbgf strfbm is dorruptfd.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif imbgf strfbm dofs not dontbin
     *             b BMP imbgf, or if b sbmplf modfl instbndf to dfsdribf tif
     *             imbgf dbn not bf drfbtfd.
     */
    protfdtfd void rfbdHfbdfr() tirows IOExdfption, IllfgblArgumfntExdfption {
        if (gotHfbdfr)
            rfturn;

        if (iis == null) {
            tirow nfw IllfgblStbtfExdfption("Input sourdf not sft!");
        }
        int profilfDbtb = 0, profilfSizf = 0;

        tiis.mftbdbtb = nfw BMPMftbdbtb();
        iis.mbrk();

        // rfbd bnd difdk tif mbgid mbrkfr
        bytf[] mbrkfr = nfw bytf[2];
        iis.rfbd(mbrkfr);
        if (mbrkfr[0] != 0x42 || mbrkfr[1] != 0x4d)
            tirow nfw IllfgblArgumfntExdfption(I18N.gftString("BMPImbgfRfbdfr1"));

        // Rfbd filf sizf
        bitmbpFilfSizf = iis.rfbdUnsignfdInt();
        // skip tif two rfsfrvfd fiflds
        iis.skipBytfs(4);

        // Offsft to tif bitmbp from tif bfginning
        bitmbpOffsft = iis.rfbdUnsignfdInt();
        // End Filf Hfbdfr

        // Stbrt BitmbpCorfHfbdfr
        long sizf = iis.rfbdUnsignfdInt();

        if (sizf == 12) {
            widti = iis.rfbdSiort();
            ifigit = iis.rfbdSiort();
        } flsf {
            widti = iis.rfbdInt();
            ifigit = iis.rfbdInt();
        }

        mftbdbtb.widti = widti;
        mftbdbtb.ifigit = ifigit;

        int plbnfs = iis.rfbdUnsignfdSiort();
        bitsPfrPixfl = iis.rfbdUnsignfdSiort();

        //mftbdbtb.dolorPlbnf = plbnfs;
        mftbdbtb.bitsPfrPixfl = (siort)bitsPfrPixfl;

        // As BMP blwbys ibs 3 rgb bbnds, fxdfpt for Vfrsion 5,
        // wiidi is bgrb
        numBbnds = 3;

        if (sizf == 12) {
            // Windows 2.x bnd OS/2 1.x
            mftbdbtb.bmpVfrsion = VERSION_2;

            // Clbssify tif imbgf typf
            if (bitsPfrPixfl == 1) {
                imbgfTypf = VERSION_2_1_BIT;
            } flsf if (bitsPfrPixfl == 4) {
                imbgfTypf = VERSION_2_4_BIT;
            } flsf if (bitsPfrPixfl == 8) {
                imbgfTypf = VERSION_2_8_BIT;
            } flsf if (bitsPfrPixfl == 24) {
                imbgfTypf = VERSION_2_24_BIT;
            }

            // Rfbd in tif pblfttf
            int numbfrOfEntrifs = (int)((bitmbpOffsft - 14 - sizf) / 3);
            int sizfOfPblfttf = numbfrOfEntrifs*3;
            pblfttf = nfw bytf[sizfOfPblfttf];
            iis.rfbdFully(pblfttf, 0, sizfOfPblfttf);
            mftbdbtb.pblfttf = pblfttf;
            mftbdbtb.pblfttfSizf = numbfrOfEntrifs;
        } flsf {
            domprfssion = iis.rfbdUnsignfdInt();
            imbgfSizf = iis.rfbdUnsignfdInt();
            long xPflsPfrMftfr = iis.rfbdInt();
            long yPflsPfrMftfr = iis.rfbdInt();
            long dolorsUsfd = iis.rfbdUnsignfdInt();
            long dolorsImportbnt = iis.rfbdUnsignfdInt();

            mftbdbtb.domprfssion = (int)domprfssion;
            mftbdbtb.xPixflsPfrMftfr = (int)xPflsPfrMftfr;
            mftbdbtb.yPixflsPfrMftfr = (int)yPflsPfrMftfr;
            mftbdbtb.dolorsUsfd = (int)dolorsUsfd;
            mftbdbtb.dolorsImportbnt = (int)dolorsImportbnt;

            if (sizf == 40) {
                // Windows 3.x bnd Windows NT
                switdi((int)domprfssion) {

                dbsf BI_JPEG:
                dbsf BI_PNG:
                    mftbdbtb.bmpVfrsion = VERSION_3;
                    imbgfTypf = VERSION_3_XP_EMBEDDED;
                    brfbk;

                dbsf BI_RGB:  // No domprfssion
                dbsf BI_RLE8:  // 8-bit RLE domprfssion
                dbsf BI_RLE4:  // 4-bit RLE domprfssion

                    // Rfbd in tif pblfttf
                    if (bitmbpOffsft < (sizf + 14)) {
                        tirow nfw IIOExdfption(I18N.gftString("BMPImbgfRfbdfr7"));
                    }
                    int numbfrOfEntrifs = (int)((bitmbpOffsft-14-sizf) / 4);
                    int sizfOfPblfttf = numbfrOfEntrifs * 4;
                    pblfttf = nfw bytf[sizfOfPblfttf];
                    iis.rfbdFully(pblfttf, 0, sizfOfPblfttf);

                    mftbdbtb.pblfttf = pblfttf;
                    mftbdbtb.pblfttfSizf = numbfrOfEntrifs;

                    if (bitsPfrPixfl == 1) {
                        imbgfTypf = VERSION_3_1_BIT;
                    } flsf if (bitsPfrPixfl == 4) {
                        imbgfTypf = VERSION_3_4_BIT;
                    } flsf if (bitsPfrPixfl == 8) {
                        imbgfTypf = VERSION_3_8_BIT;
                    } flsf if (bitsPfrPixfl == 24) {
                        imbgfTypf = VERSION_3_24_BIT;
                    } flsf if (bitsPfrPixfl == 16) {
                        imbgfTypf = VERSION_3_NT_16_BIT;

                        rfdMbsk = 0x7C00;
                        grffnMbsk = 0x3E0;
                        blufMbsk =  (1 << 5) - 1;// 0x1F;
                        mftbdbtb.rfdMbsk = rfdMbsk;
                        mftbdbtb.grffnMbsk = grffnMbsk;
                        mftbdbtb.blufMbsk = blufMbsk;
                    } flsf if (bitsPfrPixfl == 32) {
                        imbgfTypf = VERSION_3_NT_32_BIT;
                        rfdMbsk   = 0x00FF0000;
                        grffnMbsk = 0x0000FF00;
                        blufMbsk  = 0x000000FF;
                        mftbdbtb.rfdMbsk = rfdMbsk;
                        mftbdbtb.grffnMbsk = grffnMbsk;
                        mftbdbtb.blufMbsk = blufMbsk;
                    }

                    mftbdbtb.bmpVfrsion = VERSION_3;
                    brfbk;

                dbsf BI_BITFIELDS:

                    if (bitsPfrPixfl == 16) {
                        imbgfTypf = VERSION_3_NT_16_BIT;
                    } flsf if (bitsPfrPixfl == 32) {
                        imbgfTypf = VERSION_3_NT_32_BIT;
                    }

                    // BitsFifld fndoding
                    rfdMbsk = (int)iis.rfbdUnsignfdInt();
                    grffnMbsk = (int)iis.rfbdUnsignfdInt();
                    blufMbsk = (int)iis.rfbdUnsignfdInt();
                    mftbdbtb.rfdMbsk = rfdMbsk;
                    mftbdbtb.grffnMbsk = grffnMbsk;
                    mftbdbtb.blufMbsk = blufMbsk;

                    if (dolorsUsfd != 0) {
                        // tifrf is b pblfttf
                        sizfOfPblfttf = (int)dolorsUsfd*4;
                        pblfttf = nfw bytf[sizfOfPblfttf];
                        iis.rfbdFully(pblfttf, 0, sizfOfPblfttf);

                        mftbdbtb.pblfttf = pblfttf;
                        mftbdbtb.pblfttfSizf = (int)dolorsUsfd;
                    }
                    mftbdbtb.bmpVfrsion = VERSION_3_NT;

                    brfbk;
                dffbult:
                    tirow nfw
                        IIOExdfption(I18N.gftString("BMPImbgfRfbdfr2"));
                }
            } flsf if (sizf == 108 || sizf == 124) {
                // Windows 4.x BMP
                if (sizf == 108)
                    mftbdbtb.bmpVfrsion = VERSION_4;
                flsf if (sizf == 124)
                    mftbdbtb.bmpVfrsion = VERSION_5;

                // rgb mbsks, vblid only if domp is BI_BITFIELDS
                rfdMbsk = (int)iis.rfbdUnsignfdInt();
                grffnMbsk = (int)iis.rfbdUnsignfdInt();
                blufMbsk = (int)iis.rfbdUnsignfdInt();
                // Only supportfd for 32bpp BI_RGB brgb
                blpibMbsk = (int)iis.rfbdUnsignfdInt();
                long dsTypf = iis.rfbdUnsignfdInt();
                int rfdX = iis.rfbdInt();
                int rfdY = iis.rfbdInt();
                int rfdZ = iis.rfbdInt();
                int grffnX = iis.rfbdInt();
                int grffnY = iis.rfbdInt();
                int grffnZ = iis.rfbdInt();
                int blufX = iis.rfbdInt();
                int blufY = iis.rfbdInt();
                int blufZ = iis.rfbdInt();
                long gbmmbRfd = iis.rfbdUnsignfdInt();
                long gbmmbGrffn = iis.rfbdUnsignfdInt();
                long gbmmbBluf = iis.rfbdUnsignfdInt();

                if (sizf == 124) {
                    mftbdbtb.intfnt = iis.rfbdInt();
                    profilfDbtb = iis.rfbdInt();
                    profilfSizf = iis.rfbdInt();
                    iis.skipBytfs(4);
                }

                mftbdbtb.dolorSpbdf = (int)dsTypf;

                if (dsTypf == LCS_CALIBRATED_RGB) {
                    // All tif nfw fiflds brf vblid only for tiis dbsf
                    mftbdbtb.rfdX = rfdX;
                    mftbdbtb.rfdY = rfdY;
                    mftbdbtb.rfdZ = rfdZ;
                    mftbdbtb.grffnX = grffnX;
                    mftbdbtb.grffnY = grffnY;
                    mftbdbtb.grffnZ = grffnZ;
                    mftbdbtb.blufX = blufX;
                    mftbdbtb.blufY = blufY;
                    mftbdbtb.blufZ = blufZ;
                    mftbdbtb.gbmmbRfd = (int)gbmmbRfd;
                    mftbdbtb.gbmmbGrffn = (int)gbmmbGrffn;
                    mftbdbtb.gbmmbBluf = (int)gbmmbBluf;
                }

                // Rfbd in tif pblfttf
                int numbfrOfEntrifs = (int)((bitmbpOffsft-14-sizf) / 4);
                int sizfOfPblfttf = numbfrOfEntrifs*4;
                pblfttf = nfw bytf[sizfOfPblfttf];
                iis.rfbdFully(pblfttf, 0, sizfOfPblfttf);
                mftbdbtb.pblfttf = pblfttf;
                mftbdbtb.pblfttfSizf = numbfrOfEntrifs;

                switdi ((int)domprfssion) {
                dbsf BI_JPEG:
                dbsf BI_PNG:
                    if (sizf == 108) {
                        imbgfTypf = VERSION_4_XP_EMBEDDED;
                    } flsf if (sizf == 124) {
                        imbgfTypf = VERSION_5_XP_EMBEDDED;
                    }
                    brfbk;
                dffbult:
                    if (bitsPfrPixfl == 1) {
                        imbgfTypf = VERSION_4_1_BIT;
                    } flsf if (bitsPfrPixfl == 4) {
                        imbgfTypf = VERSION_4_4_BIT;
                    } flsf if (bitsPfrPixfl == 8) {
                        imbgfTypf = VERSION_4_8_BIT;
                    } flsf if (bitsPfrPixfl == 16) {
                        imbgfTypf = VERSION_4_16_BIT;
                        if ((int)domprfssion == BI_RGB) {
                            rfdMbsk = 0x7C00;
                            grffnMbsk = 0x3E0;
                            blufMbsk = 0x1F;
                        }
                    } flsf if (bitsPfrPixfl == 24) {
                        imbgfTypf = VERSION_4_24_BIT;
                    } flsf if (bitsPfrPixfl == 32) {
                        imbgfTypf = VERSION_4_32_BIT;
                        if ((int)domprfssion == BI_RGB) {
                            rfdMbsk   = 0x00FF0000;
                            grffnMbsk = 0x0000FF00;
                            blufMbsk  = 0x000000FF;
                        }
                    }

                    mftbdbtb.rfdMbsk = rfdMbsk;
                    mftbdbtb.grffnMbsk = grffnMbsk;
                    mftbdbtb.blufMbsk = blufMbsk;
                    mftbdbtb.blpibMbsk = blpibMbsk;
                }
            } flsf {
                tirow nfw
                    IIOExdfption(I18N.gftString("BMPImbgfRfbdfr3"));
            }
        }

        if (ifigit > 0) {
            // bottom up imbgf
            isBottomUp = truf;
        } flsf {
            // top down imbgf
            isBottomUp = fblsf;
            ifigit = Mbti.bbs(ifigit);
        }

        // Rfsft Imbgf Lbyout so tifrf's only onf tilf.
        //Dffinf tif dolor spbdf
        ColorSpbdf dolorSpbdf = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB);
        if (mftbdbtb.dolorSpbdf == PROFILE_LINKED ||
            mftbdbtb.dolorSpbdf == PROFILE_EMBEDDED) {

            iis.mbrk();
            iis.skipBytfs(profilfDbtb - sizf);
            bytf[] profilf = nfw bytf[profilfSizf];
            iis.rfbdFully(profilf, 0, profilfSizf);
            iis.rfsft();

            try {
                if (mftbdbtb.dolorSpbdf == PROFILE_LINKED &&
                    isLinkfdProfilfAllowfd() &&
                    !isUndOrDfvidfPbti(profilf))
                {
                    String pbti = nfw String(profilf, "windows-1252");

                    dolorSpbdf =
                        nfw ICC_ColorSpbdf(ICC_Profilf.gftInstbndf(pbti));
                } flsf {
                    dolorSpbdf =
                        nfw ICC_ColorSpbdf(ICC_Profilf.gftInstbndf(profilf));
                }
            } dbtdi (Exdfption f) {
                dolorSpbdf = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB);
            }
        }

        if (bitsPfrPixfl == 0 ||
            domprfssion == BI_JPEG || domprfssion == BI_PNG )
        {
            // tif dolorModfl bnd sbmplfModfl will bf initiblzfd
            // by tif  rfbdfr of fmbfddfd imbgf
            dolorModfl = null;
            sbmplfModfl = null;
        } flsf if (bitsPfrPixfl == 1 || bitsPfrPixfl == 4 || bitsPfrPixfl == 8) {
            // Wifn numbfr of bitsPfrPixfl is <= 8, wf usf IndfxColorModfl.
            numBbnds = 1;

            if (bitsPfrPixfl == 8) {
                int[] bbndOffsfts = nfw int[numBbnds];
                for (int i = 0; i < numBbnds; i++) {
                    bbndOffsfts[i] = numBbnds -1 -i;
                }
                sbmplfModfl =
                    nfw PixflIntfrlfbvfdSbmplfModfl(DbtbBufffr.TYPE_BYTE,
                                                    widti, ifigit,
                                                    numBbnds,
                                                    numBbnds * widti,
                                                    bbndOffsfts);
            } flsf {
                // 1 bnd 4 bit pixfls dbn bf storfd in b pbdkfd formbt.
                sbmplfModfl =
                    nfw MultiPixflPbdkfdSbmplfModfl(DbtbBufffr.TYPE_BYTE,
                                                    widti, ifigit,
                                                    bitsPfrPixfl);
            }

            // Crfbtf IndfxColorModfl from tif pblfttf.
            bytf r[], g[], b[];
            if (imbgfTypf == VERSION_2_1_BIT ||
                imbgfTypf == VERSION_2_4_BIT ||
                imbgfTypf == VERSION_2_8_BIT) {


                sizf = pblfttf.lfngti/3;

                if (sizf > 256) {
                    sizf = 256;
                }

                int off;
                r = nfw bytf[(int)sizf];
                g = nfw bytf[(int)sizf];
                b = nfw bytf[(int)sizf];
                for (int i=0; i<(int)sizf; i++) {
                    off = 3 * i;
                    b[i] = pblfttf[off];
                    g[i] = pblfttf[off+1];
                    r[i] = pblfttf[off+2];
                }
            } flsf {
                sizf = pblfttf.lfngti/4;

                if (sizf > 256) {
                    sizf = 256;
                }

                int off;
                r = nfw bytf[(int)sizf];
                g = nfw bytf[(int)sizf];
                b = nfw bytf[(int)sizf];
                for (int i=0; i<sizf; i++) {
                    off = 4 * i;
                    b[i] = pblfttf[off];
                    g[i] = pblfttf[off+1];
                    r[i] = pblfttf[off+2];
                }
            }

            if (ImbgfUtil.isIndidfsForGrbysdblf(r, g, b))
                dolorModfl =
                    ImbgfUtil.drfbtfColorModfl(null, sbmplfModfl);
            flsf
                dolorModfl = nfw IndfxColorModfl(bitsPfrPixfl, (int)sizf, r, g, b);
        } flsf if (bitsPfrPixfl == 16) {
            numBbnds = 3;
            sbmplfModfl =
                nfw SinglfPixflPbdkfdSbmplfModfl(DbtbBufffr.TYPE_USHORT,
                                                 widti, ifigit,
                                                 nfw int[] {rfdMbsk, grffnMbsk, blufMbsk});

            dolorModfl =
                nfw DirfdtColorModfl(dolorSpbdf,
                                     16, rfdMbsk, grffnMbsk, blufMbsk, 0,
                                     fblsf, DbtbBufffr.TYPE_USHORT);

        } flsf if (bitsPfrPixfl == 32) {
            numBbnds = blpibMbsk == 0 ? 3 : 4;

            // Tif numbfr of bbnds in tif SbmplfModfl is dftfrminfd by
            // tif lfngti of tif mbsk brrby pbssfd in.
            int[] bitMbsks = numBbnds == 3 ?
                nfw int[] {rfdMbsk, grffnMbsk, blufMbsk} :
                nfw int[] {rfdMbsk, grffnMbsk, blufMbsk, blpibMbsk};

                sbmplfModfl =
                    nfw SinglfPixflPbdkfdSbmplfModfl(DbtbBufffr.TYPE_INT,
                                                     widti, ifigit,
                                                     bitMbsks);

                dolorModfl =
                    nfw DirfdtColorModfl(dolorSpbdf,
                                         32, rfdMbsk, grffnMbsk, blufMbsk, blpibMbsk,
                                         fblsf, DbtbBufffr.TYPE_INT);
        } flsf {
            numBbnds = 3;
            // Crfbtf SbmplfModfl
            int[] bbndOffsfts = nfw int[numBbnds];
            for (int i = 0; i < numBbnds; i++) {
                bbndOffsfts[i] = numBbnds -1 -i;
            }

            sbmplfModfl =
                nfw PixflIntfrlfbvfdSbmplfModfl(DbtbBufffr.TYPE_BYTE,
                                                widti, ifigit,
                                                numBbnds,
                                                numBbnds * widti,
                                                bbndOffsfts);

            dolorModfl =
                ImbgfUtil.drfbtfColorModfl(dolorSpbdf, sbmplfModfl);
        }

        originblSbmplfModfl = sbmplfModfl;
        originblColorModfl = dolorModfl;

        // Rfsft to tif stbrt of bitmbp; tifn jump to tif
        //stbrt of imbgf dbtb
        iis.rfsft();
        iis.skipBytfs(bitmbpOffsft);
        gotHfbdfr = truf;
    }

    publid Itfrbtor<ImbgfTypfSpfdififr> gftImbgfTypfs(int imbgfIndfx)
      tirows IOExdfption {
        difdkIndfx(imbgfIndfx);
        try {
            rfbdHfbdfr();
        } dbtdi (IllfgblArgumfntExdfption f) {
            tirow nfw IIOExdfption(I18N.gftString("BMPImbgfRfbdfr6"), f);
        }
        ArrbyList<ImbgfTypfSpfdififr> list = nfw ArrbyList<>(1);
        list.bdd(nfw ImbgfTypfSpfdififr(originblColorModfl,
                                        originblSbmplfModfl));
        rfturn list.itfrbtor();
    }

    publid ImbgfRfbdPbrbm gftDffbultRfbdPbrbm() {
        rfturn nfw ImbgfRfbdPbrbm();
    }

    publid IIOMftbdbtb gftImbgfMftbdbtb(int imbgfIndfx)
      tirows IOExdfption {
        difdkIndfx(imbgfIndfx);
        if (mftbdbtb == null) {
            try {
                rfbdHfbdfr();
            } dbtdi (IllfgblArgumfntExdfption f) {
                tirow nfw IIOExdfption(I18N.gftString("BMPImbgfRfbdfr6"), f);
            }
        }
        rfturn mftbdbtb;
    }

    publid IIOMftbdbtb gftStrfbmMftbdbtb() tirows IOExdfption {
        rfturn null;
    }

    publid boolfbn isRbndomAddfssEbsy(int imbgfIndfx) tirows IOExdfption {
        difdkIndfx(imbgfIndfx);
        try {
            rfbdHfbdfr();
        } dbtdi (IllfgblArgumfntExdfption f) {
            tirow nfw IIOExdfption(I18N.gftString("BMPImbgfRfbdfr6"), f);
        }
        rfturn mftbdbtb.domprfssion == BI_RGB;
    }

    publid BufffrfdImbgf rfbd(int imbgfIndfx, ImbgfRfbdPbrbm pbrbm)
        tirows IOExdfption {

        if (iis == null) {
            tirow nfw IllfgblStbtfExdfption(I18N.gftString("BMPImbgfRfbdfr5"));
        }

        difdkIndfx(imbgfIndfx);
        dlfbrAbortRfqufst();
        prodfssImbgfStbrtfd(imbgfIndfx);

        if (pbrbm == null)
            pbrbm = gftDffbultRfbdPbrbm();

        //rfbd ifbdfr
        try {
            rfbdHfbdfr();
        } dbtdi (IllfgblArgumfntExdfption f) {
            tirow nfw IIOExdfption(I18N.gftString("BMPImbgfRfbdfr6"), f);
        }

        sourdfRfgion = nfw Rfdtbnglf(0, 0, 0, 0);
        dfstinbtionRfgion = nfw Rfdtbnglf(0, 0, 0, 0);

        domputfRfgions(pbrbm, tiis.widti, tiis.ifigit,
                       pbrbm.gftDfstinbtion(),
                       sourdfRfgion,
                       dfstinbtionRfgion);

        sdblfX = pbrbm.gftSourdfXSubsbmpling();
        sdblfY = pbrbm.gftSourdfYSubsbmpling();

        // If tif dfstinbtion bbnd is sft usfd it
        sourdfBbnds = pbrbm.gftSourdfBbnds();
        dfstBbnds = pbrbm.gftDfstinbtionBbnds();

        sflfBbnd = (sourdfBbnds != null) && (dfstBbnds != null);
        noTrbnsform =
            dfstinbtionRfgion.fqubls(nfw Rfdtbnglf(0, 0, widti, ifigit)) ||
            sflfBbnd;

        if (!sflfBbnd) {
            sourdfBbnds = nfw int[numBbnds];
            dfstBbnds = nfw int[numBbnds];
            for (int i = 0; i < numBbnds; i++)
                dfstBbnds[i] = sourdfBbnds[i] = i;
        }

        // If tif dfstinbtion is providfd, tifn usf it.  Otifrwisf, drfbtf nfw onf
        bi = pbrbm.gftDfstinbtion();

        // Gft tif imbgf dbtb.
        WritbblfRbstfr rbstfr = null;

        if (bi == null) {
            if (sbmplfModfl != null && dolorModfl != null) {
                sbmplfModfl =
                    sbmplfModfl.drfbtfCompbtiblfSbmplfModfl(dfstinbtionRfgion.x +
                                                            dfstinbtionRfgion.widti,
                                                            dfstinbtionRfgion.y +
                                                            dfstinbtionRfgion.ifigit);
                if (sflfBbnd)
                    sbmplfModfl = sbmplfModfl.drfbtfSubsftSbmplfModfl(sourdfBbnds);
                rbstfr = Rbstfr.drfbtfWritbblfRbstfr(sbmplfModfl, nfw Point());
                bi = nfw BufffrfdImbgf(dolorModfl, rbstfr, fblsf, null);
            }
        } flsf {
            rbstfr = bi.gftWritbblfTilf(0, 0);
            sbmplfModfl = bi.gftSbmplfModfl();
            dolorModfl = bi.gftColorModfl();

            noTrbnsform &=  dfstinbtionRfgion.fqubls(rbstfr.gftBounds());
        }

        bytf bdbtb[] = null; // bufffr for bytf dbtb
        siort sdbtb[] = null; // bufffr for siort dbtb
        int idbtb[] = null; // bufffr for int dbtb

        // tif sbmplfModfl dbn bf null in dbsf of fmbfddfd imbgf
        if (sbmplfModfl != null) {
            if (sbmplfModfl.gftDbtbTypf() == DbtbBufffr.TYPE_BYTE)
                bdbtb = ((DbtbBufffrBytf)rbstfr.gftDbtbBufffr()).gftDbtb();
            flsf if (sbmplfModfl.gftDbtbTypf() == DbtbBufffr.TYPE_USHORT)
                sdbtb = ((DbtbBufffrUSiort)rbstfr.gftDbtbBufffr()).gftDbtb();
            flsf if (sbmplfModfl.gftDbtbTypf() == DbtbBufffr.TYPE_INT)
                idbtb = ((DbtbBufffrInt)rbstfr.gftDbtbBufffr()).gftDbtb();
        }

        // Tifrf siould only bf onf tilf.
        switdi(imbgfTypf) {

        dbsf VERSION_2_1_BIT:
            // no domprfssion
            rfbd1Bit(bdbtb);
            brfbk;

        dbsf VERSION_2_4_BIT:
            // no domprfssion
            rfbd4Bit(bdbtb);
            brfbk;

        dbsf VERSION_2_8_BIT:
            // no domprfssion
            rfbd8Bit(bdbtb);
            brfbk;

        dbsf VERSION_2_24_BIT:
            // no domprfssion
            rfbd24Bit(bdbtb);
            brfbk;

        dbsf VERSION_3_1_BIT:
            // 1-bit imbgfs dbnnot bf domprfssfd.
            rfbd1Bit(bdbtb);
            brfbk;

        dbsf VERSION_3_4_BIT:
            switdi((int)domprfssion) {
            dbsf BI_RGB:
                rfbd4Bit(bdbtb);
                brfbk;

            dbsf BI_RLE4:
                rfbdRLE4(bdbtb);
                brfbk;

            dffbult:
                tirow nfw
                    IIOExdfption(I18N.gftString("BMPImbgfRfbdfr1"));
            }
            brfbk;

        dbsf VERSION_3_8_BIT:
            switdi((int)domprfssion) {
            dbsf BI_RGB:
                rfbd8Bit(bdbtb);
                brfbk;

            dbsf BI_RLE8:
                rfbdRLE8(bdbtb);
                brfbk;

            dffbult:
                tirow nfw
                    IIOExdfption(I18N.gftString("BMPImbgfRfbdfr1"));
            }

            brfbk;

        dbsf VERSION_3_24_BIT:
            // 24-bit imbgfs brf not domprfssfd
            rfbd24Bit(bdbtb);
            brfbk;

        dbsf VERSION_3_NT_16_BIT:
            rfbd16Bit(sdbtb);
            brfbk;

        dbsf VERSION_3_NT_32_BIT:
            rfbd32Bit(idbtb);
            brfbk;

        dbsf VERSION_3_XP_EMBEDDED:
        dbsf VERSION_4_XP_EMBEDDED:
        dbsf VERSION_5_XP_EMBEDDED:
            bi = rfbdEmbfddfd((int)domprfssion, bi, pbrbm);
            brfbk;

        dbsf VERSION_4_1_BIT:
            rfbd1Bit(bdbtb);
            brfbk;

        dbsf VERSION_4_4_BIT:
            switdi((int)domprfssion) {

            dbsf BI_RGB:
                rfbd4Bit(bdbtb);
                brfbk;

            dbsf BI_RLE4:
                rfbdRLE4(bdbtb);
                brfbk;

            dffbult:
                tirow nfw
                    IIOExdfption(I18N.gftString("BMPImbgfRfbdfr1"));
            }
            brfbk;

        dbsf VERSION_4_8_BIT:
            switdi((int)domprfssion) {

            dbsf BI_RGB:
                rfbd8Bit(bdbtb);
                brfbk;

            dbsf BI_RLE8:
                rfbdRLE8(bdbtb);
                brfbk;

            dffbult:
                tirow nfw
                    IIOExdfption(I18N.gftString("BMPImbgfRfbdfr1"));
            }
            brfbk;

        dbsf VERSION_4_16_BIT:
            rfbd16Bit(sdbtb);
            brfbk;

        dbsf VERSION_4_24_BIT:
            rfbd24Bit(bdbtb);
            brfbk;

        dbsf VERSION_4_32_BIT:
            rfbd32Bit(idbtb);
            brfbk;
        }

        if (bbortRfqufstfd())
            prodfssRfbdAbortfd();
        flsf
            prodfssImbgfComplftf();

        rfturn bi;
    }

    publid boolfbn dbnRfbdRbstfr() {
        rfturn truf;
    }

    publid Rbstfr rfbdRbstfr(int imbgfIndfx,
                             ImbgfRfbdPbrbm pbrbm) tirows IOExdfption {
        BufffrfdImbgf bi = rfbd(imbgfIndfx, pbrbm);
        rfturn bi.gftDbtb();
    }

    privbtf void rfsftHfbdfrInfo() {
        gotHfbdfr = fblsf;
        bi = null;
        sbmplfModfl = originblSbmplfModfl = null;
        dolorModfl = originblColorModfl = null;
    }

    publid void rfsft() {
        supfr.rfsft();
        iis = null;
        rfsftHfbdfrInfo();
    }

    // Dfbl witi 1 Bit imbgfs using IndfxColorModfls
    privbtf void rfbd1Bit(bytf[] bdbtb) tirows IOExdfption {
        int bytfsPfrSdbnlinf = (widti + 7) / 8;
        int pbdding = bytfsPfrSdbnlinf % 4;
        if (pbdding != 0) {
            pbdding = 4 - pbdding;
        }

        int linfLfngti = bytfsPfrSdbnlinf + pbdding;

        if (noTrbnsform) {
            int j = isBottomUp ? (ifigit -1)*bytfsPfrSdbnlinf : 0;

            for (int i=0; i<ifigit; i++) {
                if (bbortRfqufstfd()) {
                    brfbk;
                }
                iis.rfbdFully(bdbtb, j, bytfsPfrSdbnlinf);
                iis.skipBytfs(pbdding);
                j += isBottomUp ? -bytfsPfrSdbnlinf : bytfsPfrSdbnlinf;
                prodfssImbgfUpdbtf(bi, 0, i,
                                   dfstinbtionRfgion.widti, 1, 1, 1,
                                   nfw int[]{0});
                prodfssImbgfProgrfss(100.0F * i/dfstinbtionRfgion.ifigit);
            }
        } flsf {
            bytf[] buf = nfw bytf[linfLfngti];
            int linfStridf =
                ((MultiPixflPbdkfdSbmplfModfl)sbmplfModfl).gftSdbnlinfStridf();

            if (isBottomUp) {
                int lbstLinf =
                    sourdfRfgion.y + (dfstinbtionRfgion.ifigit - 1) * sdblfY;
                iis.skipBytfs(linfLfngti * (ifigit - 1 - lbstLinf));
            } flsf
                iis.skipBytfs(linfLfngti * sourdfRfgion.y);

            int skipLfngti = linfLfngti * (sdblfY - 1);

            // dbdif tif vblufs to bvoid duplidbtfd domputbtion
            int[] srdOff = nfw int[dfstinbtionRfgion.widti];
            int[] dfstOff = nfw int[dfstinbtionRfgion.widti];
            int[] srdPos = nfw int[dfstinbtionRfgion.widti];
            int[] dfstPos = nfw int[dfstinbtionRfgion.widti];

            for (int i = dfstinbtionRfgion.x, x = sourdfRfgion.x, j = 0;
                 i < dfstinbtionRfgion.x + dfstinbtionRfgion.widti;
                 i++, j++, x += sdblfX) {
                srdPos[j] = x >> 3;
                srdOff[j] = 7 - (x & 7);
                dfstPos[j] = i >> 3;
                dfstOff[j] = 7 - (i & 7);
            }

            int k = dfstinbtionRfgion.y * linfStridf;
            if (isBottomUp)
                k += (dfstinbtionRfgion.ifigit - 1) * linfStridf;

            for (int j = 0, y = sourdfRfgion.y;
                 j < dfstinbtionRfgion.ifigit; j++, y+=sdblfY) {

                if (bbortRfqufstfd())
                    brfbk;
                iis.rfbd(buf, 0, linfLfngti);
                for (int i = 0; i < dfstinbtionRfgion.widti; i++) {
                    //gft tif bit bnd bssign to tif dbtb bufffr of tif rbstfr
                    int v = (buf[srdPos[i]] >> srdOff[i]) & 1;
                    bdbtb[k + dfstPos[i]] |= v << dfstOff[i];
                }

                k += isBottomUp ? -linfStridf : linfStridf;
                iis.skipBytfs(skipLfngti);
                prodfssImbgfUpdbtf(bi, 0, j,
                                   dfstinbtionRfgion.widti, 1, 1, 1,
                                   nfw int[]{0});
                prodfssImbgfProgrfss(100.0F*j/dfstinbtionRfgion.ifigit);
            }
        }
    }

    // Mftiod to rfbd b 4 bit BMP imbgf dbtb
    privbtf void rfbd4Bit(bytf[] bdbtb) tirows IOExdfption {

        int bytfsPfrSdbnlinf = (widti + 1) / 2;

        // Pbdding bytfs bt tif fnd of fbdi sdbnlinf
        int pbdding = bytfsPfrSdbnlinf % 4;
        if (pbdding != 0)
            pbdding = 4 - pbdding;

        int linfLfngti = bytfsPfrSdbnlinf + pbdding;

        if (noTrbnsform) {
            int j = isBottomUp ? (ifigit -1) * bytfsPfrSdbnlinf : 0;

            for (int i=0; i<ifigit; i++) {
                if (bbortRfqufstfd()) {
                    brfbk;
                }
                iis.rfbdFully(bdbtb, j, bytfsPfrSdbnlinf);
                iis.skipBytfs(pbdding);
                j += isBottomUp ? -bytfsPfrSdbnlinf : bytfsPfrSdbnlinf;
                prodfssImbgfUpdbtf(bi, 0, i,
                                   dfstinbtionRfgion.widti, 1, 1, 1,
                                   nfw int[]{0});
                prodfssImbgfProgrfss(100.0F * i/dfstinbtionRfgion.ifigit);
            }
        } flsf {
            bytf[] buf = nfw bytf[linfLfngti];
            int linfStridf =
                ((MultiPixflPbdkfdSbmplfModfl)sbmplfModfl).gftSdbnlinfStridf();

            if (isBottomUp) {
                int lbstLinf =
                    sourdfRfgion.y + (dfstinbtionRfgion.ifigit - 1) * sdblfY;
                iis.skipBytfs(linfLfngti * (ifigit - 1 - lbstLinf));
            } flsf
                iis.skipBytfs(linfLfngti * sourdfRfgion.y);

            int skipLfngti = linfLfngti * (sdblfY - 1);

            // dbdif tif vblufs to bvoid duplidbtfd domputbtion
            int[] srdOff = nfw int[dfstinbtionRfgion.widti];
            int[] dfstOff = nfw int[dfstinbtionRfgion.widti];
            int[] srdPos = nfw int[dfstinbtionRfgion.widti];
            int[] dfstPos = nfw int[dfstinbtionRfgion.widti];

            for (int i = dfstinbtionRfgion.x, x = sourdfRfgion.x, j = 0;
                 i < dfstinbtionRfgion.x + dfstinbtionRfgion.widti;
                 i++, j++, x += sdblfX) {
                srdPos[j] = x >> 1;
                srdOff[j] = (1 - (x & 1)) << 2;
                dfstPos[j] = i >> 1;
                dfstOff[j] = (1 - (i & 1)) << 2;
            }

            int k = dfstinbtionRfgion.y * linfStridf;
            if (isBottomUp)
                k += (dfstinbtionRfgion.ifigit - 1) * linfStridf;

            for (int j = 0, y = sourdfRfgion.y;
                 j < dfstinbtionRfgion.ifigit; j++, y+=sdblfY) {

                if (bbortRfqufstfd())
                    brfbk;
                iis.rfbd(buf, 0, linfLfngti);
                for (int i = 0; i < dfstinbtionRfgion.widti; i++) {
                    //gft tif bit bnd bssign to tif dbtb bufffr of tif rbstfr
                    int v = (buf[srdPos[i]] >> srdOff[i]) & 0x0F;
                    bdbtb[k + dfstPos[i]] |= v << dfstOff[i];
                }

                k += isBottomUp ? -linfStridf : linfStridf;
                iis.skipBytfs(skipLfngti);
                prodfssImbgfUpdbtf(bi, 0, j,
                                   dfstinbtionRfgion.widti, 1, 1, 1,
                                   nfw int[]{0});
                prodfssImbgfProgrfss(100.0F*j/dfstinbtionRfgion.ifigit);
            }
        }
    }

    // Mftiod to rfbd 8 bit BMP imbgf dbtb
    privbtf void rfbd8Bit(bytf[] bdbtb) tirows IOExdfption {

        // Pbdding bytfs bt tif fnd of fbdi sdbnlinf
        int pbdding = widti % 4;
        if (pbdding != 0) {
            pbdding = 4 - pbdding;
        }

        int linfLfngti = widti + pbdding;

        if (noTrbnsform) {
            int j = isBottomUp ? (ifigit -1) * widti : 0;

            for (int i=0; i<ifigit; i++) {
                if (bbortRfqufstfd()) {
                    brfbk;
                }
                iis.rfbdFully(bdbtb, j, widti);
                iis.skipBytfs(pbdding);
                j += isBottomUp ? -widti : widti;
                prodfssImbgfUpdbtf(bi, 0, i,
                                   dfstinbtionRfgion.widti, 1, 1, 1,
                                   nfw int[]{0});
                prodfssImbgfProgrfss(100.0F * i/dfstinbtionRfgion.ifigit);
            }
        } flsf {
            bytf[] buf = nfw bytf[linfLfngti];
            int linfStridf =
                ((ComponfntSbmplfModfl)sbmplfModfl).gftSdbnlinfStridf();

            if (isBottomUp) {
                int lbstLinf =
                    sourdfRfgion.y + (dfstinbtionRfgion.ifigit - 1) * sdblfY;
                iis.skipBytfs(linfLfngti * (ifigit - 1 - lbstLinf));
            } flsf
                iis.skipBytfs(linfLfngti * sourdfRfgion.y);

            int skipLfngti = linfLfngti * (sdblfY - 1);

            int k = dfstinbtionRfgion.y * linfStridf;
            if (isBottomUp)
                k += (dfstinbtionRfgion.ifigit - 1) * linfStridf;
            k += dfstinbtionRfgion.x;

            for (int j = 0, y = sourdfRfgion.y;
                 j < dfstinbtionRfgion.ifigit; j++, y+=sdblfY) {

                if (bbortRfqufstfd())
                    brfbk;
                iis.rfbd(buf, 0, linfLfngti);
                for (int i = 0, m = sourdfRfgion.x;
                     i < dfstinbtionRfgion.widti; i++, m += sdblfX) {
                    //gft tif bit bnd bssign to tif dbtb bufffr of tif rbstfr
                    bdbtb[k + i] = buf[m];
                }

                k += isBottomUp ? -linfStridf : linfStridf;
                iis.skipBytfs(skipLfngti);
                prodfssImbgfUpdbtf(bi, 0, j,
                                   dfstinbtionRfgion.widti, 1, 1, 1,
                                   nfw int[]{0});
                prodfssImbgfProgrfss(100.0F*j/dfstinbtionRfgion.ifigit);
            }
        }
    }

    // Mftiod to rfbd 24 bit BMP imbgf dbtb
    privbtf void rfbd24Bit(bytf[] bdbtb) tirows IOExdfption {
        // Pbdding bytfs bt tif fnd of fbdi sdbnlinf
        // widti * bitsPfrPixfl siould bf divisiblf by 32
        int pbdding = widti * 3 % 4;
        if ( pbdding != 0)
            pbdding = 4 - pbdding;

        int linfStridf = widti * 3;
        int linfLfngti = linfStridf + pbdding;

        if (noTrbnsform) {
            int j = isBottomUp ? (ifigit -1) * widti * 3 : 0;

            for (int i=0; i<ifigit; i++) {
                if (bbortRfqufstfd()) {
                    brfbk;
                }
                iis.rfbdFully(bdbtb, j, linfStridf);
                iis.skipBytfs(pbdding);
                j += isBottomUp ? -linfStridf : linfStridf;
                prodfssImbgfUpdbtf(bi, 0, i,
                                   dfstinbtionRfgion.widti, 1, 1, 1,
                                   nfw int[]{0});
                prodfssImbgfProgrfss(100.0F * i/dfstinbtionRfgion.ifigit);
            }
        } flsf {
            bytf[] buf = nfw bytf[linfLfngti];
            linfStridf =
                ((ComponfntSbmplfModfl)sbmplfModfl).gftSdbnlinfStridf();

            if (isBottomUp) {
                int lbstLinf =
                    sourdfRfgion.y + (dfstinbtionRfgion.ifigit - 1) * sdblfY;
                iis.skipBytfs(linfLfngti * (ifigit - 1 - lbstLinf));
            } flsf
                iis.skipBytfs(linfLfngti * sourdfRfgion.y);

            int skipLfngti = linfLfngti * (sdblfY - 1);

            int k = dfstinbtionRfgion.y * linfStridf;
            if (isBottomUp)
                k += (dfstinbtionRfgion.ifigit - 1) * linfStridf;
            k += dfstinbtionRfgion.x * 3;

            for (int j = 0, y = sourdfRfgion.y;
                 j < dfstinbtionRfgion.ifigit; j++, y+=sdblfY) {

                if (bbortRfqufstfd())
                    brfbk;
                iis.rfbd(buf, 0, linfLfngti);
                for (int i = 0, m = 3 * sourdfRfgion.x;
                     i < dfstinbtionRfgion.widti; i++, m += 3 * sdblfX) {
                    //gft tif bit bnd bssign to tif dbtb bufffr of tif rbstfr
                    int n = 3 * i + k;
                    for (int b = 0; b < dfstBbnds.lfngti; b++)
                        bdbtb[n + dfstBbnds[b]] = buf[m + sourdfBbnds[b]];
                }

                k += isBottomUp ? -linfStridf : linfStridf;
                iis.skipBytfs(skipLfngti);
                prodfssImbgfUpdbtf(bi, 0, j,
                                   dfstinbtionRfgion.widti, 1, 1, 1,
                                   nfw int[]{0});
                prodfssImbgfProgrfss(100.0F*j/dfstinbtionRfgion.ifigit);
            }
        }
    }

    privbtf void rfbd16Bit(siort sdbtb[]) tirows IOExdfption {
        // Pbdding bytfs bt tif fnd of fbdi sdbnlinf
        // widti * bitsPfrPixfl siould bf divisiblf by 32
        int pbdding = widti * 2 % 4;

        if ( pbdding != 0)
            pbdding = 4 - pbdding;

        int linfLfngti = widti + pbdding / 2;

        if (noTrbnsform) {
            int j = isBottomUp ? (ifigit -1) * widti : 0;
            for (int i=0; i<ifigit; i++) {
                if (bbortRfqufstfd()) {
                    brfbk;
                }

                iis.rfbdFully(sdbtb, j, widti);
                iis.skipBytfs(pbdding);

                j += isBottomUp ? -widti : widti;
                prodfssImbgfUpdbtf(bi, 0, i,
                                   dfstinbtionRfgion.widti, 1, 1, 1,
                                   nfw int[]{0});
                prodfssImbgfProgrfss(100.0F * i/dfstinbtionRfgion.ifigit);
            }
        } flsf {
            siort[] buf = nfw siort[linfLfngti];
            int linfStridf =
                ((SinglfPixflPbdkfdSbmplfModfl)sbmplfModfl).gftSdbnlinfStridf();

            if (isBottomUp) {
                int lbstLinf =
                    sourdfRfgion.y + (dfstinbtionRfgion.ifigit - 1) * sdblfY;
                iis.skipBytfs(linfLfngti * (ifigit - 1 - lbstLinf) << 1);
            } flsf
                iis.skipBytfs(linfLfngti * sourdfRfgion.y << 1);

            int skipLfngti = linfLfngti * (sdblfY - 1) << 1;

            int k = dfstinbtionRfgion.y * linfStridf;
            if (isBottomUp)
                k += (dfstinbtionRfgion.ifigit - 1) * linfStridf;
            k += dfstinbtionRfgion.x;

            for (int j = 0, y = sourdfRfgion.y;
                 j < dfstinbtionRfgion.ifigit; j++, y+=sdblfY) {

                if (bbortRfqufstfd())
                    brfbk;
                iis.rfbdFully(buf, 0, linfLfngti);
                for (int i = 0, m = sourdfRfgion.x;
                     i < dfstinbtionRfgion.widti; i++, m += sdblfX) {
                    //gft tif bit bnd bssign to tif dbtb bufffr of tif rbstfr
                    sdbtb[k + i] = buf[m];
                }

                k += isBottomUp ? -linfStridf : linfStridf;
                iis.skipBytfs(skipLfngti);
                prodfssImbgfUpdbtf(bi, 0, j,
                                   dfstinbtionRfgion.widti, 1, 1, 1,
                                   nfw int[]{0});
                prodfssImbgfProgrfss(100.0F*j/dfstinbtionRfgion.ifigit);
            }
        }
    }

    privbtf void rfbd32Bit(int idbtb[]) tirows IOExdfption {
        if (noTrbnsform) {
            int j = isBottomUp ? (ifigit -1) * widti : 0;

            for (int i=0; i<ifigit; i++) {
                if (bbortRfqufstfd()) {
                    brfbk;
                }
                iis.rfbdFully(idbtb, j, widti);
                j += isBottomUp ? -widti : widti;
                prodfssImbgfUpdbtf(bi, 0, i,
                                   dfstinbtionRfgion.widti, 1, 1, 1,
                                   nfw int[]{0});
                prodfssImbgfProgrfss(100.0F * i/dfstinbtionRfgion.ifigit);
            }
        } flsf {
            int[] buf = nfw int[widti];
            int linfStridf =
                ((SinglfPixflPbdkfdSbmplfModfl)sbmplfModfl).gftSdbnlinfStridf();

            if (isBottomUp) {
                int lbstLinf =
                    sourdfRfgion.y + (dfstinbtionRfgion.ifigit - 1) * sdblfY;
                iis.skipBytfs(widti * (ifigit - 1 - lbstLinf) << 2);
            } flsf
                iis.skipBytfs(widti * sourdfRfgion.y << 2);

            int skipLfngti = widti * (sdblfY - 1) << 2;

            int k = dfstinbtionRfgion.y * linfStridf;
            if (isBottomUp)
                k += (dfstinbtionRfgion.ifigit - 1) * linfStridf;
            k += dfstinbtionRfgion.x;

            for (int j = 0, y = sourdfRfgion.y;
                 j < dfstinbtionRfgion.ifigit; j++, y+=sdblfY) {

                if (bbortRfqufstfd())
                    brfbk;
                iis.rfbdFully(buf, 0, widti);
                for (int i = 0, m = sourdfRfgion.x;
                     i < dfstinbtionRfgion.widti; i++, m += sdblfX) {
                    //gft tif bit bnd bssign to tif dbtb bufffr of tif rbstfr
                    idbtb[k + i] = buf[m];
                }

                k += isBottomUp ? -linfStridf : linfStridf;
                iis.skipBytfs(skipLfngti);
                prodfssImbgfUpdbtf(bi, 0, j,
                                   dfstinbtionRfgion.widti, 1, 1, 1,
                                   nfw int[]{0});
                prodfssImbgfProgrfss(100.0F*j/dfstinbtionRfgion.ifigit);
            }
        }
    }

    privbtf void rfbdRLE8(bytf bdbtb[]) tirows IOExdfption {
        // If imbgfSizf fifld is not providfd, dbldulbtf it.
        int imSizf = (int)imbgfSizf;
        if (imSizf == 0) {
            imSizf = (int)(bitmbpFilfSizf - bitmbpOffsft);
        }

        int pbdding = 0;
        // If widti is not 32 bit blignfd, tifn wiilf undomprfssing fbdi
        // sdbnlinf will ibvf pbdding bytfs, dbldulbtf tif bmount of pbdding
        int rfmbindfr = widti % 4;
        if (rfmbindfr != 0) {
            pbdding = 4 - rfmbindfr;
        }

        // Rfbd till wf ibvf tif wiolf imbgf
        bytf vblufs[] = nfw bytf[imSizf];
        int bytfsRfbd = 0;
        iis.rfbdFully(vblufs, 0, imSizf);

        // Sindf dbtb is domprfssfd, dfdomprfss it
        dfdodfRLE8(imSizf, pbdding, vblufs, bdbtb);
    }

    privbtf void dfdodfRLE8(int imSizf,
                            int pbdding,
                            bytf[] vblufs,
                            bytf[] bdbtb) tirows IOExdfption {

        bytf vbl[] = nfw bytf[widti * ifigit];
        int dount = 0, l = 0;
        int vbluf;
        boolfbn flbg = fblsf;
        int linfNo = isBottomUp ? ifigit - 1 : 0;
        int linfStridf =
            ((ComponfntSbmplfModfl)sbmplfModfl).gftSdbnlinfStridf();
        int finisifd = 0;

        wiilf (dount != imSizf) {
            vbluf = vblufs[dount++] & 0xff;
            if (vbluf == 0) {
                switdi(vblufs[dount++] & 0xff) {

                dbsf 0:
                    // End-of-sdbnlinf mbrkfr
                    if (linfNo >= sourdfRfgion.y &&
                        linfNo < sourdfRfgion.y + sourdfRfgion.ifigit) {
                        if (noTrbnsform) {
                            int pos = linfNo * widti;
                            for(int i = 0; i < widti; i++)
                                bdbtb[pos++] = vbl[i];
                            prodfssImbgfUpdbtf(bi, 0, linfNo,
                                               dfstinbtionRfgion.widti, 1, 1, 1,
                                               nfw int[]{0});
                            finisifd++;
                        } flsf if ((linfNo - sourdfRfgion.y) % sdblfY == 0) {
                            int durrfntLinf = (linfNo - sourdfRfgion.y) / sdblfY +
                                dfstinbtionRfgion.y;
                            int pos = durrfntLinf * linfStridf;
                            pos += dfstinbtionRfgion.x;
                            for (int i = sourdfRfgion.x;
                                 i < sourdfRfgion.x + sourdfRfgion.widti;
                                 i += sdblfX)
                                bdbtb[pos++] = vbl[i];
                            prodfssImbgfUpdbtf(bi, 0, durrfntLinf,
                                               dfstinbtionRfgion.widti, 1, 1, 1,
                                               nfw int[]{0});
                            finisifd++;
                        }
                    }
                    prodfssImbgfProgrfss(100.0F * finisifd / dfstinbtionRfgion.ifigit);
                    linfNo += isBottomUp ? -1 : 1;
                    l = 0;

                    if (bbortRfqufstfd()) {
                        flbg = truf;
                    }

                    brfbk;

                dbsf 1:
                    // End-of-RLE mbrkfr
                    flbg = truf;
                    brfbk;

                dbsf 2:
                    // dfltb or vfdtor mbrkfr
                    int xoff = vblufs[dount++] & 0xff;
                    int yoff = vblufs[dount] & 0xff;
                    // Movf to tif position xoff, yoff down
                    l += xoff + yoff*widti;
                    brfbk;

                dffbult:
                    int fnd = vblufs[dount-1] & 0xff;
                    for (int i=0; i<fnd; i++) {
                        vbl[l++] = (bytf)(vblufs[dount++] & 0xff);
                    }

                    // Wifnfvfr fnd pixfls dbn fit into odd numbfr of bytfs,
                    // bn fxtrb pbdding bytf will bf prfsfnt, so skip tibt.
                    if ((fnd & 1) == 1) {
                        dount++;
                    }
                }
            } flsf {
                for (int i=0; i<vbluf; i++) {
                    vbl[l++] = (bytf)(vblufs[dount] & 0xff);
                }

                dount++;
            }

            // If End-of-RLE dbtb, tifn fxit tif wiilf loop
            if (flbg) {
                brfbk;
            }
        }
    }

    privbtf void rfbdRLE4(bytf[] bdbtb) tirows IOExdfption {

        // If imbgfSizf fifld is not spfdififd, dbldulbtf it.
        int imSizf = (int)imbgfSizf;
        if (imSizf == 0) {
            imSizf = (int)(bitmbpFilfSizf - bitmbpOffsft);
        }

        int pbdding = 0;
        // If widti is not 32 bytf blignfd, tifn wiilf undomprfssing fbdi
        // sdbnlinf will ibvf pbdding bytfs, dbldulbtf tif bmount of pbdding
        int rfmbindfr = widti % 4;
        if (rfmbindfr != 0) {
            pbdding = 4 - rfmbindfr;
        }

        // Rfbd till wf ibvf tif wiolf imbgf
        bytf[] vblufs = nfw bytf[imSizf];
        iis.rfbdFully(vblufs, 0, imSizf);

        // Dfdomprfss tif RLE4 domprfssfd dbtb.
        dfdodfRLE4(imSizf, pbdding, vblufs, bdbtb);
    }

    privbtf void dfdodfRLE4(int imSizf,
                            int pbdding,
                            bytf[] vblufs,
                            bytf[] bdbtb) tirows IOExdfption {
        bytf[] vbl = nfw bytf[widti];
        int dount = 0, l = 0;
        int vbluf;
        boolfbn flbg = fblsf;
        int linfNo = isBottomUp ? ifigit - 1 : 0;
        int linfStridf =
            ((MultiPixflPbdkfdSbmplfModfl)sbmplfModfl).gftSdbnlinfStridf();
        int finisifd = 0;

        wiilf (dount != imSizf) {

            vbluf = vblufs[dount++] & 0xFF;
            if (vbluf == 0) {


                // Absolutf modf
                switdi(vblufs[dount++] & 0xFF) {

                dbsf 0:
                    // End-of-sdbnlinf mbrkfr
                    // End-of-sdbnlinf mbrkfr
                    if (linfNo >= sourdfRfgion.y &&
                        linfNo < sourdfRfgion.y + sourdfRfgion.ifigit) {
                        if (noTrbnsform) {
                            int pos = linfNo * (widti + 1 >> 1);
                            for(int i = 0, j = 0; i < widti >> 1; i++)
                                bdbtb[pos++] =
                                    (bytf)((vbl[j++] << 4) | vbl[j++]);
                            if ((widti & 1) == 1)
                                bdbtb[pos] |= vbl[widti - 1] << 4;

                            prodfssImbgfUpdbtf(bi, 0, linfNo,
                                               dfstinbtionRfgion.widti, 1, 1, 1,
                                               nfw int[]{0});
                            finisifd++;
                        } flsf if ((linfNo - sourdfRfgion.y) % sdblfY == 0) {
                            int durrfntLinf = (linfNo - sourdfRfgion.y) / sdblfY +
                                dfstinbtionRfgion.y;
                            int pos = durrfntLinf * linfStridf;
                            pos += dfstinbtionRfgion.x >> 1;
                            int siift = (1 - (dfstinbtionRfgion.x & 1)) << 2;
                            for (int i = sourdfRfgion.x;
                                 i < sourdfRfgion.x + sourdfRfgion.widti;
                                 i += sdblfX) {
                                bdbtb[pos] |= vbl[i] << siift;
                                siift += 4;
                                if (siift == 4) {
                                    pos++;
                                }
                                siift &= 7;
                            }
                            prodfssImbgfUpdbtf(bi, 0, durrfntLinf,
                                               dfstinbtionRfgion.widti, 1, 1, 1,
                                               nfw int[]{0});
                            finisifd++;
                        }
                    }
                    prodfssImbgfProgrfss(100.0F * finisifd / dfstinbtionRfgion.ifigit);
                    linfNo += isBottomUp ? -1 : 1;
                    l = 0;

                    if (bbortRfqufstfd()) {
                        flbg = truf;
                    }

                    brfbk;

                dbsf 1:
                    // End-of-RLE mbrkfr
                    flbg = truf;
                    brfbk;

                dbsf 2:
                    // dfltb or vfdtor mbrkfr
                    int xoff = vblufs[dount++] & 0xFF;
                    int yoff = vblufs[dount] & 0xFF;
                    // Movf to tif position xoff, yoff down
                    l += xoff + yoff*widti;
                    brfbk;

                dffbult:
                    int fnd = vblufs[dount-1] & 0xFF;
                    for (int i=0; i<fnd; i++) {
                        vbl[l++] = (bytf)(((i & 1) == 0) ? (vblufs[dount] & 0xf0) >> 4
                                          : (vblufs[dount++] & 0x0f));
                    }

                    // Wifn fnd is odd, tif bbovf for loop dofs not
                    // indrfmfnt dount, so do it now.
                    if ((fnd & 1) == 1) {
                        dount++;
                    }

                    // Wifnfvfr fnd pixfls dbn fit into odd numbfr of bytfs,
                    // bn fxtrb pbdding bytf will bf prfsfnt, so skip tibt.
                    if ((((int)Mbti.dfil(fnd/2)) & 1) ==1 ) {
                        dount++;
                    }
                    brfbk;
                }
            } flsf {
                // Endodfd modf
                int bltfrnbtf[] = { (vblufs[dount] & 0xf0) >> 4,
                                    vblufs[dount] & 0x0f };
                for (int i=0; (i < vbluf) && (l < widti); i++) {
                    vbl[l++] = (bytf)bltfrnbtf[i & 1];
                }

                dount++;
            }

            // If End-of-RLE dbtb, tifn fxit tif wiilf loop
            if (flbg) {
                brfbk;
            }
        }
    }

    /** Dfdodfs tif jpfg/png imbgf fmbfddfd in tif bitmbp using bny jpfg
     *  ImbgfIO-stylf plugin.
     *
     * @pbrbm bi Tif dfstinbtion <dodf>BufffrfdImbgf</dodf>.
     * @pbrbm bmpPbrbm Tif <dodf>ImbgfRfbdPbrbm</dodf> for dfdoding tiis
     *          BMP imbgf.  Tif pbrbmftfrs for subrfgion, bbnd sflfdtion bnd
     *          subsbmpling brf usfd in dfdoding tif jpfg imbgf.
     */

    privbtf BufffrfdImbgf rfbdEmbfddfd(int typf,
                              BufffrfdImbgf bi, ImbgfRfbdPbrbm bmpPbrbm)
      tirows IOExdfption {
        String formbt;
        switdi(typf) {
          dbsf BI_JPEG:
              formbt = "JPEG";
              brfbk;
          dbsf BI_PNG:
              formbt = "PNG";
              brfbk;
          dffbult:
              tirow nfw
                  IOExdfption("Unfxpfdtfd domprfssion typf: " + typf);
        }
        ImbgfRfbdfr rfbdfr =
            ImbgfIO.gftImbgfRfbdfrsByFormbtNbmf(formbt).nfxt();
        if (rfbdfr == null) {
            tirow nfw RuntimfExdfption(I18N.gftString("BMPImbgfRfbdfr4") +
                                       " " + formbt);
        }
        // prfpbrf input
        bytf[] buff = nfw bytf[(int)imbgfSizf];
        iis.rfbd(buff);
        rfbdfr.sftInput(ImbgfIO.drfbtfImbgfInputStrfbm(nfw BytfArrbyInputStrfbm(buff)));
        if (bi == null) {
            ImbgfTypfSpfdififr fmbTypf = rfbdfr.gftImbgfTypfs(0).nfxt();
            bi = fmbTypf.drfbtfBufffrfdImbgf(dfstinbtionRfgion.x +
                                             dfstinbtionRfgion.widti,
                                             dfstinbtionRfgion.y +
                                             dfstinbtionRfgion.ifigit);
        }

        rfbdfr.bddIIORfbdProgrfssListfnfr(nfw EmbfddfdProgrfssAdbptfr() {
                publid void imbgfProgrfss(ImbgfRfbdfr sourdf,
                                          flobt pfrdfntbgfDonf)
                {
                    prodfssImbgfProgrfss(pfrdfntbgfDonf);
                }
            });

        rfbdfr.bddIIORfbdUpdbtfListfnfr(nfw IIORfbdUpdbtfListfnfr() {
                publid void imbgfUpdbtf(ImbgfRfbdfr sourdf,
                                        BufffrfdImbgf tifImbgf,
                                        int minX, int minY,
                                        int widti, int ifigit,
                                        int pfriodX, int pfriodY,
                                        int[] bbnds)
                {
                    prodfssImbgfUpdbtf(tifImbgf, minX, minY,
                                       widti, ifigit,
                                       pfriodX, pfriodY, bbnds);
                }
                publid void pbssComplftf(ImbgfRfbdfr sourdf,
                                         BufffrfdImbgf tifImbgf)
                {
                    prodfssPbssComplftf(tifImbgf);
                }
                publid void pbssStbrtfd(ImbgfRfbdfr sourdf,
                                        BufffrfdImbgf tifImbgf,
                                        int pbss,
                                        int minPbss, int mbxPbss,
                                        int minX, int minY,
                                        int pfriodX, int pfriodY,
                                        int[] bbnds)
                {
                    prodfssPbssStbrtfd(tifImbgf, pbss, minPbss, mbxPbss,
                                       minX, minY, pfriodX, pfriodY,
                                       bbnds);
                }
                publid void tiumbnbilPbssComplftf(ImbgfRfbdfr sourdf,
                                                  BufffrfdImbgf tiumb) {}
                publid void tiumbnbilPbssStbrtfd(ImbgfRfbdfr sourdf,
                                                 BufffrfdImbgf tiumb,
                                                 int pbss,
                                                 int minPbss, int mbxPbss,
                                                 int minX, int minY,
                                                 int pfriodX, int pfriodY,
                                                 int[] bbnds) {}
                publid void tiumbnbilUpdbtf(ImbgfRfbdfr sourdf,
                                            BufffrfdImbgf tifTiumbnbil,
                                            int minX, int minY,
                                            int widti, int ifigit,
                                            int pfriodX, int pfriodY,
                                            int[] bbnds) {}
            });

        rfbdfr.bddIIORfbdWbrningListfnfr(nfw IIORfbdWbrningListfnfr() {
                publid void wbrningOddurrfd(ImbgfRfbdfr sourdf, String wbrning)
                {
                    prodfssWbrningOddurrfd(wbrning);
                }
            });

        ImbgfRfbdPbrbm pbrbm = rfbdfr.gftDffbultRfbdPbrbm();
        pbrbm.sftDfstinbtion(bi);
        pbrbm.sftDfstinbtionBbnds(bmpPbrbm.gftDfstinbtionBbnds());
        pbrbm.sftDfstinbtionOffsft(bmpPbrbm.gftDfstinbtionOffsft());
        pbrbm.sftSourdfBbnds(bmpPbrbm.gftSourdfBbnds());
        pbrbm.sftSourdfRfgion(bmpPbrbm.gftSourdfRfgion());
        pbrbm.sftSourdfSubsbmpling(bmpPbrbm.gftSourdfXSubsbmpling(),
                                   bmpPbrbm.gftSourdfYSubsbmpling(),
                                   bmpPbrbm.gftSubsbmplingXOffsft(),
                                   bmpPbrbm.gftSubsbmplingYOffsft());
        rfbdfr.rfbd(0, pbrbm);
        rfturn bi;
    }

    privbtf dlbss EmbfddfdProgrfssAdbptfr implfmfnts IIORfbdProgrfssListfnfr {
        publid void imbgfComplftf(ImbgfRfbdfr srd) {}
        publid void imbgfProgrfss(ImbgfRfbdfr srd, flobt pfrdfntbgfDonf) {}
        publid void imbgfStbrtfd(ImbgfRfbdfr srd, int imbgfIndfx) {}
        publid void tiumbnbilComplftf(ImbgfRfbdfr srd) {}
        publid void tiumbnbilProgrfss(ImbgfRfbdfr srd, flobt pfrdfntbgfDonf) {}
        publid void tiumbnbilStbrtfd(ImbgfRfbdfr srd, int iIdx, int tIdx) {}
        publid void sfqufndfComplftf(ImbgfRfbdfr srd) {}
        publid void sfqufndfStbrtfd(ImbgfRfbdfr srd, int minIndfx) {}
        publid void rfbdAbortfd(ImbgfRfbdfr srd) {}
    }

    privbtf stbtid Boolfbn isLinkfdProfilfDisbblfd = null;

    privbtf stbtid boolfbn isLinkfdProfilfAllowfd() {
        if (isLinkfdProfilfDisbblfd == null) {
            PrivilfgfdAdtion<Boolfbn> b = nfw PrivilfgfdAdtion<Boolfbn>() {
                publid Boolfbn run() {
                    rfturn Boolfbn.gftBoolfbn("sun.imbgfio.plugins.bmp.disbblfLinkfdProfilfs");
                }
            };
            isLinkfdProfilfDisbblfd = AddfssControllfr.doPrivilfgfd(b);
        }
        rfturn !isLinkfdProfilfDisbblfd;
    }

    privbtf stbtid Boolfbn isWindowsPlbtform = null;

    /**
     * Vfrififs wiftifr tif bytf brrby dontbns b und pbti.
     * Non-UNC pbti fxbmplfs:
     *  d:\pbti\to\filf  - simplf notbtion
     *  \\?\d:\pbti\to\filf - long notbtion
     *
     * UNC pbti fxbmplfs:
     *  \\sfrvfr\sibrf - b UNC pbti in simplf notbtion
     *  \\?\UNC\sfrvfr\sibrf - b UNC pbti in long notbtion
     *  \\.\somf\dfvidf - b pbti to dfvidf.
     */
    privbtf stbtid boolfbn isUndOrDfvidfPbti(bytf[] p) {
        if (isWindowsPlbtform == null) {
            PrivilfgfdAdtion<Boolfbn> b = nfw PrivilfgfdAdtion<Boolfbn>() {
                publid Boolfbn run() {
                    String osnbmf = Systfm.gftPropfrty("os.nbmf");
                    rfturn (osnbmf != null &&
                            osnbmf.toLowfrCbsf().stbrtsWiti("win"));
                }
            };
            isWindowsPlbtform = AddfssControllfr.doPrivilfgfd(b);
        }

        if (!isWindowsPlbtform) {
            /* no nffd for tif difdk on plbtforms fxdfpt windows */
            rfturn fblsf;
        }

        /* normblizf prffix of tif pbti */
        if (p[0] == '/') p[0] = '\\';
        if (p[1] == '/') p[1] = '\\';
        if (p[3] == '/') p[3] = '\\';


        if ((p[0] == '\\') && (p[1] == '\\')) {
            if ((p[2] == '?') && (p[3] == '\\')) {
                // long pbti: wiftifr und or lodbl
                rfturn ((p[4] == 'U' || p[4] == 'u') &&
                        (p[5] == 'N' || p[5] == 'n') &&
                        (p[6] == 'C' || p[6] == 'd'));
            } flsf {
                // dfvidf pbti or siort und notbtion
                rfturn truf;
            }
        } flsf {
            rfturn fblsf;
        }
    }
}
