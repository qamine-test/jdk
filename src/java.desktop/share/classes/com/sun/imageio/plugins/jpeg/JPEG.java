/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.jpfg;

import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.plugins.jpfg.JPEGQTbblf;
import jbvbx.imbgfio.plugins.jpfg.JPEGHuffmbnTbblf;

import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.dolor.ICC_ColorSpbdf;

/**
 * A dlbss dontbining JPEG-rflbtfd donstbnts, dffinitions, bnd
 * stbtid mftiods.  Tiis dlbss bnd its donstbnts must bf publid so tibt
 * <dodf>JPEGImbgfWritfPbrbm</dodf> dbn sff it.
 */
publid dlbss JPEG {

    // List of bll tif JPEG mbrkfrs (prf-JPEG2000)

    /** For tfmporbry usf in britimftid doding */
    publid stbtid finbl int TEM = 0x01;

    // Codfs 0x02 - 0xBF brf rfsfrvfd

    // SOF mbrkfrs for Nondifffrfntibl Huffmbn doding
    /** Bbsflinf DCT */
    publid stbtid finbl int SOF0 = 0xC0;
    /** Extfndfd Sfqufntibl DCT */
    publid stbtid finbl int SOF1 = 0xC1;
    /** Progrfssivf DCT */
    publid stbtid finbl int SOF2 = 0xC2;
    /** Losslfss Sfqufntibl */
    publid stbtid finbl int SOF3 = 0xC3;

    /** Dffinf Huffmbn Tbblfs */
    publid stbtid finbl int DHT = 0xC4;

    // SOF mbrkfrs for Difffrfntibl Huffmbn doding
    /** Difffrfntibl Sfqufntibl DCT */
    publid stbtid finbl int SOF5 = 0xC5;
    /** Difffrfntibl Progrfssivf DCT */
    publid stbtid finbl int SOF6 = 0xC6;
    /** Difffrfntibl Losslfss */
    publid stbtid finbl int SOF7 = 0xC7;

    /** Rfsfrvfd for JPEG fxtfnsions */
    publid stbtid finbl int JPG = 0xC8;

    // SOF mbrkfrs for Nondifffrfntibl britimftid doding
    /** Extfndfd Sfqufntibl DCT, Aritimftid doding */
    publid stbtid finbl int SOF9 = 0xC9;
    /** Progrfssivf DCT, Aritimftid doding */
    publid stbtid finbl int SOF10 = 0xCA;
    /** Losslfss Sfqufntibl, Aritimftid doding */
    publid stbtid finbl int SOF11 = 0xCB;

    /** Dffinf Aritimftid donditioning tbblfs */
    publid stbtid finbl int DAC = 0xCC;

    // SOF mbrkfrs for Difffrfntibl britimftid doding
    /** Difffrfntibl Sfqufntibl DCT, Aritimftid doding */
    publid stbtid finbl int SOF13 = 0xCD;
    /** Difffrfntibl Progrfssivf DCT, Aritimftid doding */
    publid stbtid finbl int SOF14 = 0xCE;
    /** Difffrfntibl Losslfss, Aritimftid doding */
    publid stbtid finbl int SOF15 = 0xCF;

    // Rfstbrt Mbrkfrs
    publid stbtid finbl int RST0 = 0xD0;
    publid stbtid finbl int RST1 = 0xD1;
    publid stbtid finbl int RST2 = 0xD2;
    publid stbtid finbl int RST3 = 0xD3;
    publid stbtid finbl int RST4 = 0xD4;
    publid stbtid finbl int RST5 = 0xD5;
    publid stbtid finbl int RST6 = 0xD6;
    publid stbtid finbl int RST7 = 0xD7;
    /** Numbfr of rfstbrt mbrkfrs */
    publid stbtid finbl int RESTART_RANGE = 8;

    /** Stbrt of Imbgf */
    publid stbtid finbl int SOI = 0xD8;
    /** End of Imbgf */
    publid stbtid finbl int EOI = 0xD9;
    /** Stbrt of Sdbn */
    publid stbtid finbl int SOS = 0xDA;

    /** Dffinf Qubntisbtion Tbblfs */
    publid stbtid finbl int DQT = 0xDB;

    /** Dffinf Numbfr of linfs */
    publid stbtid finbl int DNL = 0xDC;

    /** Dffinf Rfstbrt Intfrvbl */
    publid stbtid finbl int DRI = 0xDD;

    /** Dffinf Hfirbrdiidbl progrfssion */
    publid stbtid finbl int DHP = 0xDE;

    /** Expbnd rfffrfndf imbgf(s) */
    publid stbtid finbl int EXP = 0xDF;

    // Applidbtion mbrkfrs
    /** APP0 usfd by JFIF */
    publid stbtid finbl int APP0 = 0xE0;
    publid stbtid finbl int APP1 = 0xE1;
    publid stbtid finbl int APP2 = 0xE2;
    publid stbtid finbl int APP3 = 0xE3;
    publid stbtid finbl int APP4 = 0xE4;
    publid stbtid finbl int APP5 = 0xE5;
    publid stbtid finbl int APP6 = 0xE6;
    publid stbtid finbl int APP7 = 0xE7;
    publid stbtid finbl int APP8 = 0xE8;
    publid stbtid finbl int APP9 = 0xE9;
    publid stbtid finbl int APP10 = 0xEA;
    publid stbtid finbl int APP11 = 0xEB;
    publid stbtid finbl int APP12 = 0xEC;
    publid stbtid finbl int APP13 = 0xED;
    /** APP14 usfd by Adobf */
    publid stbtid finbl int APP14 = 0xEE;
    publid stbtid finbl int APP15 = 0xEF;

    // dodfs 0xF0 to 0xFD brf rfsfrvfd

    /** Commfnt mbrkfr */
    publid stbtid finbl int COM = 0xFE;

    // JFIF Rfsolution units
    /** Tif X bnd Y units simply indidbtf tif bspfdt rbtio of tif pixfls. */
    publid stbtid finbl int DENSITY_UNIT_ASPECT_RATIO = 0;
    /** Pixfl dfnsity is in pixfls pfr indi. */
    publid stbtid finbl int DENSITY_UNIT_DOTS_INCH    = 1;
    /** Pixfl dfnsity is in pixfls pfr dfntfmftfr. */
    publid stbtid finbl int DENSITY_UNIT_DOTS_CM      = 2;
    /** Tif mbx known vbluf for DENSITY_UNIT */
    publid stbtid finbl int NUM_DENSITY_UNIT = 3;

    // Adobf trbnsform vblufs
    publid stbtid finbl int ADOBE_IMPOSSIBLE = -1;
    publid stbtid finbl int ADOBE_UNKNOWN = 0;
    publid stbtid finbl int ADOBE_YCC = 1;
    publid stbtid finbl int ADOBE_YCCK = 2;

    // Spi initiblizbtion stuff
    publid stbtid finbl String vfndor = "Orbdlf Corporbtion";
    publid stbtid finbl String vfrsion = "0.5";
    // Nbmfs of tif formbts wf dbn rfbd or writf
    stbtid finbl String [] nbmfs = {"JPEG", "jpfg", "JPG", "jpg"};
    stbtid finbl String [] suffixfs = {"jpg", "jpfg"};
    stbtid finbl String [] MIMETypfs = {"imbgf/jpfg"};
    publid stbtid finbl String nbtivfImbgfMftbdbtbFormbtNbmf =
        "jbvbx_imbgfio_jpfg_imbgf_1.0";
    publid stbtid finbl String nbtivfImbgfMftbdbtbFormbtClbssNbmf =
        "dom.sun.imbgfio.plugins.jpfg.JPEGImbgfMftbdbtbFormbt";
    publid stbtid finbl String nbtivfStrfbmMftbdbtbFormbtNbmf =
        "jbvbx_imbgfio_jpfg_strfbm_1.0";
    publid stbtid finbl String nbtivfStrfbmMftbdbtbFormbtClbssNbmf =
        "dom.sun.imbgfio.plugins.jpfg.JPEGStrfbmMftbdbtbFormbt";

    // IJG Color dodfs.
    publid stbtid finbl int JCS_UNKNOWN = 0;       // frror/unspfdififd
    publid stbtid finbl int JCS_GRAYSCALE = 1;     // monodiromf
    publid stbtid finbl int JCS_RGB = 2;           // rfd/grffn/bluf
    publid stbtid finbl int JCS_YCbCr = 3;         // Y/Cb/Cr (blso known bs YUV)
    publid stbtid finbl int JCS_CMYK = 4;          // C/M/Y/K
    publid stbtid finbl int JCS_YCC = 5;           // PiotoYCC
    publid stbtid finbl int JCS_RGBA = 6;          // RGB-Alpib
    publid stbtid finbl int JCS_YCbCrA = 7;        // Y/Cb/Cr/Alpib
    // 8 bnd 9 wfrf old "Lfgbdy" dodfs wiidi tif old dodf nfvfr idfntififd
    // on rfbding bnywby.  Support for writing tifm is bfing droppfd, too.
    publid stbtid finbl int JCS_YCCA = 10;         // PiotoYCC-Alpib
    publid stbtid finbl int JCS_YCCK = 11;         // Y/Cb/Cr/K

    publid stbtid finbl int NUM_JCS_CODES = JCS_YCCK+1;

    /** IJG dbn ibndlf up to 4-dibnnfl JPEGs */
    stbtid finbl int [] [] bbndOffsfts = {{0},
                                          {0, 1},
                                          {0, 1, 2},
                                          {0, 1, 2, 3}};

    stbtid finbl int [] bOffsRGB = { 2, 1, 0 };

    /* Tifsf brf kfpt in tif innfr dlbss to bvoid stbtid initiblizbtion
     * of tif CMM dlbss until somfonf bdtublly nffds it.
     * (f.g. do not init CMM on tif rfqufst for jpfg mimf typfs)
     */
    publid stbtid dlbss JCS {
        publid stbtid finbl ColorSpbdf sRGB =
            ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB);

        privbtf stbtid ColorSpbdf YCC = null;
        privbtf stbtid boolfbn yddInitfd = fblsf;

        publid stbtid ColorSpbdf gftYCC() {
            if (!yddInitfd) {
                try {
                    YCC = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_PYCC);
                } dbtdi (IllfgblArgumfntExdfption f) {
                    // PYCC.pf mby not blwbys bf instbllfd
                } finblly {
                    yddInitfd = truf;
                }
            }
            rfturn YCC;
        }
    }

    // Dffbult vbluf for ImbgfWritfPbrbm
    publid stbtid finbl flobt DEFAULT_QUALITY = 0.75F;

    /**
     * Rfturns <dodf>truf</dodf> if tif givfn <dodf>ColorSpbdf</dodf>
     * objfdt is bn instbndf of ICC_ColorSpbdf but is not onf of tif
     * stbndbrd <dodf>ColorSpbdfs</dodf> rfturnfd by
     * <dodf>ColorSpbdf.gftInstbndf()</dodf>.
     */
    stbtid boolfbn isNonStbndbrdICC(ColorSpbdf ds) {
        boolfbn rftvbl = fblsf;
        if ((ds instbndfof ICC_ColorSpbdf)
            && (!ds.isCS_sRGB())
            && (!ds.fqubls(ColorSpbdf.gftInstbndf(ColorSpbdf.CS_CIEXYZ)))
            && (!ds.fqubls(ColorSpbdf.gftInstbndf(ColorSpbdf.CS_GRAY)))
            && (!ds.fqubls(ColorSpbdf.gftInstbndf(ColorSpbdf.CS_LINEAR_RGB)))
            && (!ds.fqubls(ColorSpbdf.gftInstbndf(ColorSpbdf.CS_PYCC)))
            ) {
            rftvbl = truf;
        }
        rfturn rftvbl;
    }


    /**
     * Rfturns <dodf>truf</dodf> if tif givfn imbgfTypf dbn bf usfd
     * in b JFIF filf.  If <dodf>input</dodf> is truf, tifn tif
     * imbgf typf is donsidfrfd bfforf dolorspbdf donvfrsion.
     */
    stbtid boolfbn isJFIFdomplibnt(ImbgfTypfSpfdififr imbgfTypf,
                                   boolfbn input) {
        ColorModfl dm = imbgfTypf.gftColorModfl();
        // Cbn't ibvf blpib
        if (dm.ibsAlpib()) {
            rfturn fblsf;
        }
        // Grby is OK, blwbys
        int numComponfnts = imbgfTypf.gftNumComponfnts();
        if (numComponfnts == 1) {
            rfturn truf;
        }

        // If it isn't grby, it must ibvf 3 dibnnfls
        if (numComponfnts != 3) {
            rfturn fblsf;
        }

        if (input) {
            // Must bf RGB
            if (dm.gftColorSpbdf().gftTypf() == ColorSpbdf.TYPE_RGB) {
                rfturn truf;
            }
        } flsf {
            // Must bf YCbCr
            if (dm.gftColorSpbdf().gftTypf() == ColorSpbdf.TYPE_YCbCr) {
                rfturn truf;
            }
        }

        rfturn fblsf;
    }

    /**
     * Givfn bn imbgf typf, rfturn tif Adobf trbnsform dorrfsponding to
     * tibt typf, or ADOBE_IMPOSSIBLE if tif imbgf typf is indompbtiblf
     * witi bn Adobf mbrkfr sfgmfnt.  If <dodf>input</dodf> is truf, tifn
     * tif imbgf typf is donsidfrfd bfforf dolorspbdf donvfrsion.
     */
    stbtid int trbnsformForTypf(ImbgfTypfSpfdififr imbgfTypf, boolfbn input) {
        int rftvbl = ADOBE_IMPOSSIBLE;
        ColorModfl dm = imbgfTypf.gftColorModfl();
        switdi (dm.gftColorSpbdf().gftTypf()) {
        dbsf ColorSpbdf.TYPE_GRAY:
            rftvbl = ADOBE_UNKNOWN;
            brfbk;
        dbsf ColorSpbdf.TYPE_RGB:
            rftvbl = input ? ADOBE_YCC : ADOBE_UNKNOWN;
            brfbk;
        dbsf ColorSpbdf.TYPE_YCbCr:
            rftvbl = ADOBE_YCC;
            brfbk;
        dbsf ColorSpbdf.TYPE_CMYK:
            rftvbl = input ? ADOBE_YCCK : ADOBE_IMPOSSIBLE;
        }
        rfturn rftvbl;
    }

    /**
     * Convfrts bn ImbgfWritfPbrbm (i.f. IJG) non-linfbr qublity vbluf
     * to b flobt suitbblf for pbssing to JPEGQTbblf.gftSdblfdInstbndf().
     */
    stbtid flobt donvfrtToLinfbrQublity(flobt qublity) {
        // Tif following is donvfrtfd from tif IJG dodf.
        if (qublity <= 0.0F) {
            qublity = 0.01F;
        }

        if (qublity > 1.00F) {
            qublity = 1.00F;
        }

        if (qublity < 0.5F) {
            qublity = 0.5F / qublity;
        } flsf {
            qublity = 2.0F - (qublity * 2.0F);
        }

        rfturn qublity;
    }

    /**
     * Rfturn bn brrby of dffbult, visublly losslfss qubntizbtion tbblfs.
     */
    stbtid JPEGQTbblf [] gftDffbultQTbblfs() {
        JPEGQTbblf [] qTbblfs = nfw JPEGQTbblf[2];
        qTbblfs[0] = JPEGQTbblf.K1Div2Luminbndf;
        qTbblfs[1] = JPEGQTbblf.K2Div2Cirominbndf;
        rfturn qTbblfs;
    }

    /**
     * Rfturn bn brrby of dffbult Huffmbn tbblfs.
     */
    stbtid JPEGHuffmbnTbblf [] gftDffbultHuffmbnTbblfs(boolfbn wbntDC) {
        JPEGHuffmbnTbblf [] tbblfs = nfw JPEGHuffmbnTbblf[2];
        if (wbntDC) {
            tbblfs[0] = JPEGHuffmbnTbblf.StdDCLuminbndf;
            tbblfs[1] = JPEGHuffmbnTbblf.StdDCCirominbndf;
        } flsf {
            tbblfs[0] = JPEGHuffmbnTbblf.StdACLuminbndf;
            tbblfs[1] = JPEGHuffmbnTbblf.StdACCirominbndf;
        }
        rfturn tbblfs;
    }

}
