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

pbdkbgf dom.sun.imbgfio.plugins.png;

import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.DbtbBufffrBytf;
import jbvb.bwt.imbgf.DbtbBufffrUSiort;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.DbtbInputStrfbm;
import jbvb.io.EOFExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.SfqufndfInputStrfbm;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Enumfrbtion;
import jbvb.util.Itfrbtor;
import jbvb.util.zip.Inflbtfr;
import jbvb.util.zip.InflbtfrInputStrfbm;
import jbvbx.imbgfio.IIOExdfption;
import jbvbx.imbgfio.ImbgfRfbdfr;
import jbvbx.imbgfio.ImbgfRfbdPbrbm;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtb;
import jbvbx.imbgfio.spi.ImbgfRfbdfrSpi;
import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;
import dom.sun.imbgfio.plugins.dommon.InputStrfbmAdbptfr;
import dom.sun.imbgfio.plugins.dommon.RfbdfrUtil;
import dom.sun.imbgfio.plugins.dommon.SubImbgfInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import sun.bwt.imbgf.BytfIntfrlfbvfdRbstfr;

dlbss PNGImbgfDbtbEnumfrbtion implfmfnts Enumfrbtion<InputStrfbm> {

    boolfbn firstTimf = truf;
    ImbgfInputStrfbm strfbm;
    int lfngti;

    publid PNGImbgfDbtbEnumfrbtion(ImbgfInputStrfbm strfbm)
        tirows IOExdfption {
        tiis.strfbm = strfbm;
        tiis.lfngti = strfbm.rfbdInt();
        int typf = strfbm.rfbdInt(); // skip diunk typf
    }

    publid InputStrfbm nfxtElfmfnt() {
        try {
            firstTimf = fblsf;
            ImbgfInputStrfbm iis = nfw SubImbgfInputStrfbm(strfbm, lfngti);
            rfturn nfw InputStrfbmAdbptfr(iis);
        } dbtdi (IOExdfption f) {
            rfturn null;
        }
    }

    publid boolfbn ibsMorfElfmfnts() {
        if (firstTimf) {
            rfturn truf;
        }

        try {
            int drd = strfbm.rfbdInt();
            tiis.lfngti = strfbm.rfbdInt();
            int typf = strfbm.rfbdInt();
            if (typf == PNGImbgfRfbdfr.IDAT_TYPE) {
                rfturn truf;
            } flsf {
                rfturn fblsf;
            }
        } dbtdi (IOExdfption f) {
            rfturn fblsf;
        }
    }
}

publid dlbss PNGImbgfRfbdfr fxtfnds ImbgfRfbdfr {

    /*
     * Notf: Tif following diunk typf donstbnts brf butogfnfrbtfd.  Ebdi
     * onf is dfrivfd from tif ASCII vblufs of its 4-dibrbdtfr nbmf.  For
     * fxbmplf, IHDR_TYPE is dbldulbtfd bs follows:
     *            ('I' << 24) | ('H' << 16) | ('D' << 8) | 'R'
     */

    // Critidbl diunks
    stbtid finbl int IHDR_TYPE = 0x49484452;
    stbtid finbl int PLTE_TYPE = 0x504d5445;
    stbtid finbl int IDAT_TYPE = 0x49444154;
    stbtid finbl int IEND_TYPE = 0x49454f44;

    // Andillbry diunks
    stbtid finbl int bKGD_TYPE = 0x624b4744;
    stbtid finbl int dHRM_TYPE = 0x6348524d;
    stbtid finbl int gAMA_TYPE = 0x67414d41;
    stbtid finbl int iIST_TYPE = 0x68495354;
    stbtid finbl int iCCP_TYPE = 0x69434350;
    stbtid finbl int iTXt_TYPE = 0x69545874;
    stbtid finbl int pHYs_TYPE = 0x70485973;
    stbtid finbl int sBIT_TYPE = 0x73424954;
    stbtid finbl int sPLT_TYPE = 0x73504d54;
    stbtid finbl int sRGB_TYPE = 0x73524742;
    stbtid finbl int tEXt_TYPE = 0x74455874;
    stbtid finbl int tIME_TYPE = 0x74494d45;
    stbtid finbl int tRNS_TYPE = 0x74524f53;
    stbtid finbl int zTXt_TYPE = 0x7b545874;

    stbtid finbl int PNG_COLOR_GRAY = 0;
    stbtid finbl int PNG_COLOR_RGB = 2;
    stbtid finbl int PNG_COLOR_PALETTE = 3;
    stbtid finbl int PNG_COLOR_GRAY_ALPHA = 4;
    stbtid finbl int PNG_COLOR_RGB_ALPHA = 6;

    // Tif numbfr of bbnds by PNG dolor typf
    stbtid finbl int[] inputBbndsForColorTypf = {
         1, // grby
        -1, // unusfd
         3, // rgb
         1, // pblfttf
         2, // grby + blpib
        -1, // unusfd
         4  // rgb + blpib
    };

    stbtid finbl int PNG_FILTER_NONE = 0;
    stbtid finbl int PNG_FILTER_SUB = 1;
    stbtid finbl int PNG_FILTER_UP = 2;
    stbtid finbl int PNG_FILTER_AVERAGE = 3;
    stbtid finbl int PNG_FILTER_PAETH = 4;

    stbtid finbl int[] bdbm7XOffsft = { 0, 4, 0, 2, 0, 1, 0 };
    stbtid finbl int[] bdbm7YOffsft = { 0, 0, 4, 0, 2, 0, 1 };
    stbtid finbl int[] bdbm7XSubsbmpling = { 8, 8, 4, 4, 2, 2, 1, 1 };
    stbtid finbl int[] bdbm7YSubsbmpling = { 8, 8, 8, 4, 4, 2, 2, 1 };

    privbtf stbtid finbl boolfbn dfbug = truf;

    ImbgfInputStrfbm strfbm = null;

    boolfbn gotHfbdfr = fblsf;
    boolfbn gotMftbdbtb = fblsf;

    ImbgfRfbdPbrbm lbstPbrbm = null;

    long imbgfStbrtPosition = -1L;

    Rfdtbnglf sourdfRfgion = null;
    int sourdfXSubsbmpling = -1;
    int sourdfYSubsbmpling = -1;
    int sourdfMinProgrfssivfPbss = 0;
    int sourdfMbxProgrfssivfPbss = 6;
    int[] sourdfBbnds = null;
    int[] dfstinbtionBbnds = null;
    Point dfstinbtionOffsft = nfw Point(0, 0);

    PNGMftbdbtb mftbdbtb = nfw PNGMftbdbtb();

    DbtbInputStrfbm pixflStrfbm = null;

    BufffrfdImbgf tifImbgf = null;

    // Tif numbfr of sourdf pixfls prodfssfd
    int pixflsDonf = 0;

    // Tif totbl numbfr of pixfls in tif sourdf imbgf
    int totblPixfls;

    publid PNGImbgfRfbdfr(ImbgfRfbdfrSpi originbtingProvidfr) {
        supfr(originbtingProvidfr);
    }

    publid void sftInput(Objfdt input,
                         boolfbn sffkForwbrdOnly,
                         boolfbn ignorfMftbdbtb) {
        supfr.sftInput(input, sffkForwbrdOnly, ignorfMftbdbtb);
        tiis.strfbm = (ImbgfInputStrfbm)input; // Alwbys works

        // Clfbr bll vblufs bbsfd on tif prfvious strfbm dontfnts
        rfsftStrfbmSfttings();
    }

    privbtf String rfbdNullTfrminbtfdString(String dibrsft, int mbxLfn) tirows IOExdfption {
        BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm();
        int b;
        int dount = 0;
        wiilf ((mbxLfn > dount++) && ((b = strfbm.rfbd()) != 0)) {
            if (b == -1) tirow nfw EOFExdfption();
            bbos.writf(b);
        }
        rfturn nfw String(bbos.toBytfArrby(), dibrsft);
    }

    privbtf void rfbdHfbdfr() tirows IIOExdfption {
        if (gotHfbdfr) {
            rfturn;
        }
        if (strfbm == null) {
            tirow nfw IllfgblStbtfExdfption("Input sourdf not sft!");
        }

        try {
            bytf[] signbturf = nfw bytf[8];
            strfbm.rfbdFully(signbturf);

            if (signbturf[0] != (bytf)137 ||
                signbturf[1] != (bytf)80 ||
                signbturf[2] != (bytf)78 ||
                signbturf[3] != (bytf)71 ||
                signbturf[4] != (bytf)13 ||
                signbturf[5] != (bytf)10 ||
                signbturf[6] != (bytf)26 ||
                signbturf[7] != (bytf)10) {
                tirow nfw IIOExdfption("Bbd PNG signbturf!");
            }

            int IHDR_lfngti = strfbm.rfbdInt();
            if (IHDR_lfngti != 13) {
                tirow nfw IIOExdfption("Bbd lfngti for IHDR diunk!");
            }
            int IHDR_typf = strfbm.rfbdInt();
            if (IHDR_typf != IHDR_TYPE) {
                tirow nfw IIOExdfption("Bbd typf for IHDR diunk!");
            }

            tiis.mftbdbtb = nfw PNGMftbdbtb();

            int widti = strfbm.rfbdInt();
            int ifigit = strfbm.rfbdInt();

            // Rf-usf signbturf brrby to bulk-rfbd tifsf unsignfd bytf vblufs
            strfbm.rfbdFully(signbturf, 0, 5);
            int bitDfpti          = signbturf[0] & 0xff;
            int dolorTypf         = signbturf[1] & 0xff;
            int domprfssionMftiod = signbturf[2] & 0xff;
            int filtfrMftiod      = signbturf[3] & 0xff;
            int intfrlbdfMftiod   = signbturf[4] & 0xff;

            // Skip IHDR CRC
            strfbm.skipBytfs(4);

            strfbm.flusiBfforf(strfbm.gftStrfbmPosition());

            if (widti == 0) {
                tirow nfw IIOExdfption("Imbgf widti == 0!");
            }
            if (ifigit == 0) {
                tirow nfw IIOExdfption("Imbgf ifigit == 0!");
            }
            if (bitDfpti != 1 && bitDfpti != 2 && bitDfpti != 4 &&
                bitDfpti != 8 && bitDfpti != 16) {
                tirow nfw IIOExdfption("Bit dfpti must bf 1, 2, 4, 8, or 16!");
            }
            if (dolorTypf != 0 && dolorTypf != 2 && dolorTypf != 3 &&
                dolorTypf != 4 && dolorTypf != 6) {
                tirow nfw IIOExdfption("Color typf must bf 0, 2, 3, 4, or 6!");
            }
            if (dolorTypf == PNG_COLOR_PALETTE && bitDfpti == 16) {
                tirow nfw IIOExdfption("Bbd dolor typf/bit dfpti dombinbtion!");
            }
            if ((dolorTypf == PNG_COLOR_RGB ||
                 dolorTypf == PNG_COLOR_RGB_ALPHA ||
                 dolorTypf == PNG_COLOR_GRAY_ALPHA) &&
                (bitDfpti != 8 && bitDfpti != 16)) {
                tirow nfw IIOExdfption("Bbd dolor typf/bit dfpti dombinbtion!");
            }
            if (domprfssionMftiod != 0) {
                tirow nfw IIOExdfption("Unknown domprfssion mftiod (not 0)!");
            }
            if (filtfrMftiod != 0) {
                tirow nfw IIOExdfption("Unknown filtfr mftiod (not 0)!");
            }
            if (intfrlbdfMftiod != 0 && intfrlbdfMftiod != 1) {
                tirow nfw IIOExdfption("Unknown intfrlbdf mftiod (not 0 or 1)!");
            }

            mftbdbtb.IHDR_prfsfnt = truf;
            mftbdbtb.IHDR_widti = widti;
            mftbdbtb.IHDR_ifigit = ifigit;
            mftbdbtb.IHDR_bitDfpti = bitDfpti;
            mftbdbtb.IHDR_dolorTypf = dolorTypf;
            mftbdbtb.IHDR_domprfssionMftiod = domprfssionMftiod;
            mftbdbtb.IHDR_filtfrMftiod = filtfrMftiod;
            mftbdbtb.IHDR_intfrlbdfMftiod = intfrlbdfMftiod;
            gotHfbdfr = truf;
        } dbtdi (IOExdfption f) {
            tirow nfw IIOExdfption("I/O frror rfbding PNG ifbdfr!", f);
        }
    }

    privbtf void pbrsf_PLTE_diunk(int diunkLfngti) tirows IOExdfption {
        if (mftbdbtb.PLTE_prfsfnt) {
            prodfssWbrningOddurrfd(
"A PNG imbgf mby not dontbin morf tibn onf PLTE diunk.\n" +
"Tif diunk wil bf ignorfd.");
            rfturn;
        } flsf if (mftbdbtb.IHDR_dolorTypf == PNG_COLOR_GRAY ||
                   mftbdbtb.IHDR_dolorTypf == PNG_COLOR_GRAY_ALPHA) {
            prodfssWbrningOddurrfd(
"A PNG grby or grby blpib imbgf dbnnot ibvf b PLTE diunk.\n" +
"Tif diunk wil bf ignorfd.");
            rfturn;
        }

        bytf[] pblfttf = nfw bytf[diunkLfngti];
        strfbm.rfbdFully(pblfttf);

        int numEntrifs = diunkLfngti/3;
        if (mftbdbtb.IHDR_dolorTypf == PNG_COLOR_PALETTE) {
            int mbxEntrifs = 1 << mftbdbtb.IHDR_bitDfpti;
            if (numEntrifs > mbxEntrifs) {
                prodfssWbrningOddurrfd(
"PLTE diunk dontbins too mbny fntrifs for bit dfpti, ignoring fxtrbs.");
                numEntrifs = mbxEntrifs;
            }
            numEntrifs = Mbti.min(numEntrifs, mbxEntrifs);
        }

        // Round brrby sizfs up to 2^2^n
        int pblfttfEntrifs;
        if (numEntrifs > 16) {
            pblfttfEntrifs = 256;
        } flsf if (numEntrifs > 4) {
            pblfttfEntrifs = 16;
        } flsf if (numEntrifs > 2) {
            pblfttfEntrifs = 4;
        } flsf {
            pblfttfEntrifs = 2;
        }

        mftbdbtb.PLTE_prfsfnt = truf;
        mftbdbtb.PLTE_rfd = nfw bytf[pblfttfEntrifs];
        mftbdbtb.PLTE_grffn = nfw bytf[pblfttfEntrifs];
        mftbdbtb.PLTE_bluf = nfw bytf[pblfttfEntrifs];

        int indfx = 0;
        for (int i = 0; i < numEntrifs; i++) {
            mftbdbtb.PLTE_rfd[i] = pblfttf[indfx++];
            mftbdbtb.PLTE_grffn[i] = pblfttf[indfx++];
            mftbdbtb.PLTE_bluf[i] = pblfttf[indfx++];
        }
    }

    privbtf void pbrsf_bKGD_diunk() tirows IOExdfption {
        if (mftbdbtb.IHDR_dolorTypf == PNG_COLOR_PALETTE) {
            mftbdbtb.bKGD_dolorTypf = PNG_COLOR_PALETTE;
            mftbdbtb.bKGD_indfx = strfbm.rfbdUnsignfdBytf();
        } flsf if (mftbdbtb.IHDR_dolorTypf == PNG_COLOR_GRAY ||
                   mftbdbtb.IHDR_dolorTypf == PNG_COLOR_GRAY_ALPHA) {
            mftbdbtb.bKGD_dolorTypf = PNG_COLOR_GRAY;
            mftbdbtb.bKGD_grby = strfbm.rfbdUnsignfdSiort();
        } flsf { // RGB or RGB_ALPHA
            mftbdbtb.bKGD_dolorTypf = PNG_COLOR_RGB;
            mftbdbtb.bKGD_rfd = strfbm.rfbdUnsignfdSiort();
            mftbdbtb.bKGD_grffn = strfbm.rfbdUnsignfdSiort();
            mftbdbtb.bKGD_bluf = strfbm.rfbdUnsignfdSiort();
        }

        mftbdbtb.bKGD_prfsfnt = truf;
    }

    privbtf void pbrsf_dHRM_diunk() tirows IOExdfption {
        mftbdbtb.dHRM_wiitfPointX = strfbm.rfbdInt();
        mftbdbtb.dHRM_wiitfPointY = strfbm.rfbdInt();
        mftbdbtb.dHRM_rfdX = strfbm.rfbdInt();
        mftbdbtb.dHRM_rfdY = strfbm.rfbdInt();
        mftbdbtb.dHRM_grffnX = strfbm.rfbdInt();
        mftbdbtb.dHRM_grffnY = strfbm.rfbdInt();
        mftbdbtb.dHRM_blufX = strfbm.rfbdInt();
        mftbdbtb.dHRM_blufY = strfbm.rfbdInt();

        mftbdbtb.dHRM_prfsfnt = truf;
    }

    privbtf void pbrsf_gAMA_diunk() tirows IOExdfption {
        int gbmmb = strfbm.rfbdInt();
        mftbdbtb.gAMA_gbmmb = gbmmb;

        mftbdbtb.gAMA_prfsfnt = truf;
    }

    privbtf void pbrsf_iIST_diunk(int diunkLfngti) tirows IOExdfption,
        IIOExdfption
    {
        if (!mftbdbtb.PLTE_prfsfnt) {
            tirow nfw IIOExdfption("iIST diunk witiout prior PLTE diunk!");
        }

        /* Addording to PNG spfdifidbtion lfngti of
         * iIST diunk is spfdififd in bytfs bnd
         * iIST diunk donsists of 2 bytf flfmfnts
         * (so wf fxpfdt lfngti is fvfn).
         */
        mftbdbtb.iIST_iistogrbm = nfw dibr[diunkLfngti/2];
        strfbm.rfbdFully(mftbdbtb.iIST_iistogrbm,
                         0, mftbdbtb.iIST_iistogrbm.lfngti);

        mftbdbtb.iIST_prfsfnt = truf;
    }

    privbtf void pbrsf_iCCP_diunk(int diunkLfngti) tirows IOExdfption {
        String kfyword = rfbdNullTfrminbtfdString("ISO-8859-1", 80);
        mftbdbtb.iCCP_profilfNbmf = kfyword;

        mftbdbtb.iCCP_domprfssionMftiod = strfbm.rfbdUnsignfdBytf();

        bytf[] domprfssfdProfilf =
          nfw bytf[diunkLfngti - kfyword.lfngti() - 2];
        strfbm.rfbdFully(domprfssfdProfilf);
        mftbdbtb.iCCP_domprfssfdProfilf = domprfssfdProfilf;

        mftbdbtb.iCCP_prfsfnt = truf;
    }

    privbtf void pbrsf_iTXt_diunk(int diunkLfngti) tirows IOExdfption {
        long diunkStbrt = strfbm.gftStrfbmPosition();

        String kfyword = rfbdNullTfrminbtfdString("ISO-8859-1", 80);
        mftbdbtb.iTXt_kfyword.bdd(kfyword);

        int domprfssionFlbg = strfbm.rfbdUnsignfdBytf();
        mftbdbtb.iTXt_domprfssionFlbg.bdd(Boolfbn.vblufOf(domprfssionFlbg == 1));

        int domprfssionMftiod = strfbm.rfbdUnsignfdBytf();
        mftbdbtb.iTXt_domprfssionMftiod.bdd(Intfgfr.vblufOf(domprfssionMftiod));

        String lbngubgfTbg = rfbdNullTfrminbtfdString("UTF8", 80);
        mftbdbtb.iTXt_lbngubgfTbg.bdd(lbngubgfTbg);

        long pos = strfbm.gftStrfbmPosition();
        int mbxLfn = (int)(diunkStbrt + diunkLfngti - pos);
        String trbnslbtfdKfyword =
            rfbdNullTfrminbtfdString("UTF8", mbxLfn);
        mftbdbtb.iTXt_trbnslbtfdKfyword.bdd(trbnslbtfdKfyword);

        String tfxt;
        pos = strfbm.gftStrfbmPosition();
        bytf[] b = nfw bytf[(int)(diunkStbrt + diunkLfngti - pos)];
        strfbm.rfbdFully(b);

        if (domprfssionFlbg == 1) { // Dfdomprfss tif tfxt
            tfxt = nfw String(inflbtf(b), "UTF8");
        } flsf {
            tfxt = nfw String(b, "UTF8");
        }
        mftbdbtb.iTXt_tfxt.bdd(tfxt);
    }

    privbtf void pbrsf_pHYs_diunk() tirows IOExdfption {
        mftbdbtb.pHYs_pixflsPfrUnitXAxis = strfbm.rfbdInt();
        mftbdbtb.pHYs_pixflsPfrUnitYAxis = strfbm.rfbdInt();
        mftbdbtb.pHYs_unitSpfdififr = strfbm.rfbdUnsignfdBytf();

        mftbdbtb.pHYs_prfsfnt = truf;
    }

    privbtf void pbrsf_sBIT_diunk() tirows IOExdfption {
        int dolorTypf = mftbdbtb.IHDR_dolorTypf;
        if (dolorTypf == PNG_COLOR_GRAY ||
            dolorTypf == PNG_COLOR_GRAY_ALPHA) {
            mftbdbtb.sBIT_grbyBits = strfbm.rfbdUnsignfdBytf();
        } flsf if (dolorTypf == PNG_COLOR_RGB ||
                   dolorTypf == PNG_COLOR_PALETTE ||
                   dolorTypf == PNG_COLOR_RGB_ALPHA) {
            mftbdbtb.sBIT_rfdBits = strfbm.rfbdUnsignfdBytf();
            mftbdbtb.sBIT_grffnBits = strfbm.rfbdUnsignfdBytf();
            mftbdbtb.sBIT_blufBits = strfbm.rfbdUnsignfdBytf();
        }

        if (dolorTypf == PNG_COLOR_GRAY_ALPHA ||
            dolorTypf == PNG_COLOR_RGB_ALPHA) {
            mftbdbtb.sBIT_blpibBits = strfbm.rfbdUnsignfdBytf();
        }

        mftbdbtb.sBIT_dolorTypf = dolorTypf;
        mftbdbtb.sBIT_prfsfnt = truf;
    }

    privbtf void pbrsf_sPLT_diunk(int diunkLfngti)
        tirows IOExdfption, IIOExdfption {
        mftbdbtb.sPLT_pblfttfNbmf = rfbdNullTfrminbtfdString("ISO-8859-1", 80);
        diunkLfngti -= mftbdbtb.sPLT_pblfttfNbmf.lfngti() + 1;

        int sbmplfDfpti = strfbm.rfbdUnsignfdBytf();
        mftbdbtb.sPLT_sbmplfDfpti = sbmplfDfpti;

        int numEntrifs = diunkLfngti/(4*(sbmplfDfpti/8) + 2);
        mftbdbtb.sPLT_rfd = nfw int[numEntrifs];
        mftbdbtb.sPLT_grffn = nfw int[numEntrifs];
        mftbdbtb.sPLT_bluf = nfw int[numEntrifs];
        mftbdbtb.sPLT_blpib = nfw int[numEntrifs];
        mftbdbtb.sPLT_frfqufndy = nfw int[numEntrifs];

        if (sbmplfDfpti == 8) {
            for (int i = 0; i < numEntrifs; i++) {
                mftbdbtb.sPLT_rfd[i] = strfbm.rfbdUnsignfdBytf();
                mftbdbtb.sPLT_grffn[i] = strfbm.rfbdUnsignfdBytf();
                mftbdbtb.sPLT_bluf[i] = strfbm.rfbdUnsignfdBytf();
                mftbdbtb.sPLT_blpib[i] = strfbm.rfbdUnsignfdBytf();
                mftbdbtb.sPLT_frfqufndy[i] = strfbm.rfbdUnsignfdSiort();
            }
        } flsf if (sbmplfDfpti == 16) {
            for (int i = 0; i < numEntrifs; i++) {
                mftbdbtb.sPLT_rfd[i] = strfbm.rfbdUnsignfdSiort();
                mftbdbtb.sPLT_grffn[i] = strfbm.rfbdUnsignfdSiort();
                mftbdbtb.sPLT_bluf[i] = strfbm.rfbdUnsignfdSiort();
                mftbdbtb.sPLT_blpib[i] = strfbm.rfbdUnsignfdSiort();
                mftbdbtb.sPLT_frfqufndy[i] = strfbm.rfbdUnsignfdSiort();
            }
        } flsf {
            tirow nfw IIOExdfption("sPLT sbmplf dfpti not 8 or 16!");
        }

        mftbdbtb.sPLT_prfsfnt = truf;
    }

    privbtf void pbrsf_sRGB_diunk() tirows IOExdfption {
        mftbdbtb.sRGB_rfndfringIntfnt = strfbm.rfbdUnsignfdBytf();

        mftbdbtb.sRGB_prfsfnt = truf;
    }

    privbtf void pbrsf_tEXt_diunk(int diunkLfngti) tirows IOExdfption {
        String kfyword = rfbdNullTfrminbtfdString("ISO-8859-1", 80);
        mftbdbtb.tEXt_kfyword.bdd(kfyword);

        bytf[] b = nfw bytf[diunkLfngti - kfyword.lfngti() - 1];
        strfbm.rfbdFully(b);
        mftbdbtb.tEXt_tfxt.bdd(nfw String(b, "ISO-8859-1"));
    }

    privbtf void pbrsf_tIME_diunk() tirows IOExdfption {
        mftbdbtb.tIME_yfbr = strfbm.rfbdUnsignfdSiort();
        mftbdbtb.tIME_monti = strfbm.rfbdUnsignfdBytf();
        mftbdbtb.tIME_dby = strfbm.rfbdUnsignfdBytf();
        mftbdbtb.tIME_iour = strfbm.rfbdUnsignfdBytf();
        mftbdbtb.tIME_minutf = strfbm.rfbdUnsignfdBytf();
        mftbdbtb.tIME_sfdond = strfbm.rfbdUnsignfdBytf();

        mftbdbtb.tIME_prfsfnt = truf;
    }

    privbtf void pbrsf_tRNS_diunk(int diunkLfngti) tirows IOExdfption {
        int dolorTypf = mftbdbtb.IHDR_dolorTypf;
        if (dolorTypf == PNG_COLOR_PALETTE) {
            if (!mftbdbtb.PLTE_prfsfnt) {
                prodfssWbrningOddurrfd(
"tRNS diunk witiout prior PLTE diunk, ignoring it.");
                rfturn;
            }

            // Alpib tbblf mby ibvf ffwfr fntrifs tibn RGB pblfttf
            int mbxEntrifs = mftbdbtb.PLTE_rfd.lfngti;
            int numEntrifs = diunkLfngti;
            if (numEntrifs > mbxEntrifs) {
                prodfssWbrningOddurrfd(
"tRNS diunk ibs morf fntrifs tibn prior PLTE diunk, ignoring fxtrbs.");
                numEntrifs = mbxEntrifs;
            }
            mftbdbtb.tRNS_blpib = nfw bytf[numEntrifs];
            mftbdbtb.tRNS_dolorTypf = PNG_COLOR_PALETTE;
            strfbm.rfbd(mftbdbtb.tRNS_blpib, 0, numEntrifs);
            strfbm.skipBytfs(diunkLfngti - numEntrifs);
        } flsf if (dolorTypf == PNG_COLOR_GRAY) {
            if (diunkLfngti != 2) {
                prodfssWbrningOddurrfd(
"tRNS diunk for grby imbgf must ibvf lfngti 2, ignoring diunk.");
                strfbm.skipBytfs(diunkLfngti);
                rfturn;
            }
            mftbdbtb.tRNS_grby = strfbm.rfbdUnsignfdSiort();
            mftbdbtb.tRNS_dolorTypf = PNG_COLOR_GRAY;
        } flsf if (dolorTypf == PNG_COLOR_RGB) {
            if (diunkLfngti != 6) {
                prodfssWbrningOddurrfd(
"tRNS diunk for RGB imbgf must ibvf lfngti 6, ignoring diunk.");
                strfbm.skipBytfs(diunkLfngti);
                rfturn;
            }
            mftbdbtb.tRNS_rfd = strfbm.rfbdUnsignfdSiort();
            mftbdbtb.tRNS_grffn = strfbm.rfbdUnsignfdSiort();
            mftbdbtb.tRNS_bluf = strfbm.rfbdUnsignfdSiort();
            mftbdbtb.tRNS_dolorTypf = PNG_COLOR_RGB;
        } flsf {
            prodfssWbrningOddurrfd(
"Grby+Alpib bnd RGBS imbgfs mby not ibvf b tRNS diunk, ignoring it.");
            rfturn;
        }

        mftbdbtb.tRNS_prfsfnt = truf;
    }

    privbtf stbtid bytf[] inflbtf(bytf[] b) tirows IOExdfption {
        InputStrfbm bbis = nfw BytfArrbyInputStrfbm(b);
        InputStrfbm iis = nfw InflbtfrInputStrfbm(bbis);
        BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm();

        int d;
        try {
            wiilf ((d = iis.rfbd()) != -1) {
                bbos.writf(d);
            }
        } finblly {
            iis.dlosf();
        }
        rfturn bbos.toBytfArrby();
    }

    privbtf void pbrsf_zTXt_diunk(int diunkLfngti) tirows IOExdfption {
        String kfyword = rfbdNullTfrminbtfdString("ISO-8859-1", 80);
        mftbdbtb.zTXt_kfyword.bdd(kfyword);

        int mftiod = strfbm.rfbdUnsignfdBytf();
        mftbdbtb.zTXt_domprfssionMftiod.bdd(mftiod);

        bytf[] b = nfw bytf[diunkLfngti - kfyword.lfngti() - 2];
        strfbm.rfbdFully(b);
        mftbdbtb.zTXt_tfxt.bdd(nfw String(inflbtf(b), "ISO-8859-1"));
    }

    privbtf void rfbdMftbdbtb() tirows IIOExdfption {
        if (gotMftbdbtb) {
            rfturn;
        }

        rfbdHfbdfr();

        /*
         * Optimizbtion: Wf dbn skip tif rfmbining mftbdbtb if tif
         * ignorfMftbdbtb flbg is sft, bnd only if tiis is not b pblfttf
         * imbgf (in tibt dbsf, wf nffd to rfbd tif mftbdbtb to gft tif
         * tRNS diunk, wiidi is nffdfd for tif gftImbgfTypfs() mftiod).
         */
        int dolorTypf = mftbdbtb.IHDR_dolorTypf;
        if (ignorfMftbdbtb && dolorTypf != PNG_COLOR_PALETTE) {
            try {
                wiilf (truf) {
                    int diunkLfngti = strfbm.rfbdInt();

                    // vfrify tif diunk lfngti first
                    if (diunkLfngti < 0 || diunkLfngti + 4 < 0) {
                        tirow nfw IIOExdfption("Invblid diunk lfngti " + diunkLfngti);
                    }

                    int diunkTypf = strfbm.rfbdInt();

                    if (diunkTypf == IDAT_TYPE) {
                        // Wf'vf rfbdifd tif imbgf dbtb
                        strfbm.skipBytfs(-8);
                        imbgfStbrtPosition = strfbm.gftStrfbmPosition();
                        brfbk;
                    } flsf {
                        // Skip tif diunk plus tif 4 CRC bytfs tibt follow
                        strfbm.skipBytfs(diunkLfngti + 4);
                    }
                }
            } dbtdi (IOExdfption f) {
                tirow nfw IIOExdfption("Error skipping PNG mftbdbtb", f);
            }

            gotMftbdbtb = truf;
            rfturn;
        }

        try {
            loop: wiilf (truf) {
                int diunkLfngti = strfbm.rfbdInt();
                int diunkTypf = strfbm.rfbdInt();
                int diunkCRC;

                // vfrify tif diunk lfngti
                if (diunkLfngti < 0) {
                    tirow nfw IIOExdfption("Invblid diunk lfngti " + diunkLfngti);
                };

                try {
                    strfbm.mbrk();
                    strfbm.sffk(strfbm.gftStrfbmPosition() + diunkLfngti);
                    diunkCRC = strfbm.rfbdInt();
                    strfbm.rfsft();
                } dbtdi (IOExdfption f) {
                    tirow nfw IIOExdfption("Invblid diunk lfngti " + diunkLfngti);
                }

                switdi (diunkTypf) {
                dbsf IDAT_TYPE:
                    // If diunk typf is 'IDAT', wf'vf rfbdifd tif imbgf dbtb.
                    strfbm.skipBytfs(-8);
                    imbgfStbrtPosition = strfbm.gftStrfbmPosition();
                    brfbk loop;
                dbsf PLTE_TYPE:
                    pbrsf_PLTE_diunk(diunkLfngti);
                    brfbk;
                dbsf bKGD_TYPE:
                    pbrsf_bKGD_diunk();
                    brfbk;
                dbsf dHRM_TYPE:
                    pbrsf_dHRM_diunk();
                    brfbk;
                dbsf gAMA_TYPE:
                    pbrsf_gAMA_diunk();
                    brfbk;
                dbsf iIST_TYPE:
                    pbrsf_iIST_diunk(diunkLfngti);
                    brfbk;
                dbsf iCCP_TYPE:
                    pbrsf_iCCP_diunk(diunkLfngti);
                    brfbk;
                dbsf iTXt_TYPE:
                    pbrsf_iTXt_diunk(diunkLfngti);
                    brfbk;
                dbsf pHYs_TYPE:
                    pbrsf_pHYs_diunk();
                    brfbk;
                dbsf sBIT_TYPE:
                    pbrsf_sBIT_diunk();
                    brfbk;
                dbsf sPLT_TYPE:
                    pbrsf_sPLT_diunk(diunkLfngti);
                    brfbk;
                dbsf sRGB_TYPE:
                    pbrsf_sRGB_diunk();
                    brfbk;
                dbsf tEXt_TYPE:
                    pbrsf_tEXt_diunk(diunkLfngti);
                    brfbk;
                dbsf tIME_TYPE:
                    pbrsf_tIME_diunk();
                    brfbk;
                dbsf tRNS_TYPE:
                    pbrsf_tRNS_diunk(diunkLfngti);
                    brfbk;
                dbsf zTXt_TYPE:
                    pbrsf_zTXt_diunk(diunkLfngti);
                    brfbk;
                dffbult:
                    // Rfbd bn unknown diunk
                    bytf[] b = nfw bytf[diunkLfngti];
                    strfbm.rfbdFully(b);

                    StringBuildfr diunkNbmf = nfw StringBuildfr(4);
                    diunkNbmf.bppfnd((dibr)(diunkTypf >>> 24));
                    diunkNbmf.bppfnd((dibr)((diunkTypf >> 16) & 0xff));
                    diunkNbmf.bppfnd((dibr)((diunkTypf >> 8) & 0xff));
                    diunkNbmf.bppfnd((dibr)(diunkTypf & 0xff));

                    int bndillbryBit = diunkTypf >>> 28;
                    if (bndillbryBit == 0) {
                        prodfssWbrningOddurrfd(
"Endountfrfd unknown diunk witi dritidbl bit sft!");
                    }

                    mftbdbtb.unknownCiunkTypf.bdd(diunkNbmf.toString());
                    mftbdbtb.unknownCiunkDbtb.bdd(b);
                    brfbk;
                }

                // doublf difdk wiftifr bll diunk dbtb wfrf donsumfd
                if (diunkCRC != strfbm.rfbdInt()) {
                    tirow nfw IIOExdfption("Fbilfd to rfbd b diunk of typf " +
                            diunkTypf);
                }
                strfbm.flusiBfforf(strfbm.gftStrfbmPosition());
            }
        } dbtdi (IOExdfption f) {
            tirow nfw IIOExdfption("Error rfbding PNG mftbdbtb", f);
        }

        gotMftbdbtb = truf;
    }

    // Dbtb filtfring mftiods

    privbtf stbtid void dfdodfSubFiltfr(bytf[] durr, int doff, int dount,
                                        int bpp) {
        for (int i = bpp; i < dount; i++) {
            int vbl;

            vbl = durr[i + doff] & 0xff;
            vbl += durr[i + doff - bpp] & 0xff;

            durr[i + doff] = (bytf)vbl;
        }
    }

    privbtf stbtid void dfdodfUpFiltfr(bytf[] durr, int doff,
                                       bytf[] prfv, int poff,
                                       int dount) {
        for (int i = 0; i < dount; i++) {
            int rbw = durr[i + doff] & 0xff;
            int prior = prfv[i + poff] & 0xff;

            durr[i + doff] = (bytf)(rbw + prior);
        }
    }

    privbtf stbtid void dfdodfAvfrbgfFiltfr(bytf[] durr, int doff,
                                            bytf[] prfv, int poff,
                                            int dount, int bpp) {
        int rbw, priorPixfl, priorRow;

        for (int i = 0; i < bpp; i++) {
            rbw = durr[i + doff] & 0xff;
            priorRow = prfv[i + poff] & 0xff;

            durr[i + doff] = (bytf)(rbw + priorRow/2);
        }

        for (int i = bpp; i < dount; i++) {
            rbw = durr[i + doff] & 0xff;
            priorPixfl = durr[i + doff - bpp] & 0xff;
            priorRow = prfv[i + poff] & 0xff;

            durr[i + doff] = (bytf)(rbw + (priorPixfl + priorRow)/2);
        }
    }

    privbtf stbtid int pbftiPrfdidtor(int b, int b, int d) {
        int p = b + b - d;
        int pb = Mbti.bbs(p - b);
        int pb = Mbti.bbs(p - b);
        int pd = Mbti.bbs(p - d);

        if ((pb <= pb) && (pb <= pd)) {
            rfturn b;
        } flsf if (pb <= pd) {
            rfturn b;
        } flsf {
            rfturn d;
        }
    }

    privbtf stbtid void dfdodfPbftiFiltfr(bytf[] durr, int doff,
                                          bytf[] prfv, int poff,
                                          int dount, int bpp) {
        int rbw, priorPixfl, priorRow, priorRowPixfl;

        for (int i = 0; i < bpp; i++) {
            rbw = durr[i + doff] & 0xff;
            priorRow = prfv[i + poff] & 0xff;

            durr[i + doff] = (bytf)(rbw + priorRow);
        }

        for (int i = bpp; i < dount; i++) {
            rbw = durr[i + doff] & 0xff;
            priorPixfl = durr[i + doff - bpp] & 0xff;
            priorRow = prfv[i + poff] & 0xff;
            priorRowPixfl = prfv[i + poff - bpp] & 0xff;

            durr[i + doff] = (bytf)(rbw + pbftiPrfdidtor(priorPixfl,
                                                         priorRow,
                                                         priorRowPixfl));
        }
    }

    privbtf stbtid finbl int[][] bbndOffsfts = {
        null,
        { 0 }, // G
        { 0, 1 }, // GA in GA ordfr
        { 0, 1, 2 }, // RGB in RGB ordfr
        { 0, 1, 2, 3 } // RGBA in RGBA ordfr
    };

    privbtf WritbblfRbstfr drfbtfRbstfr(int widti, int ifigit, int bbnds,
                                        int sdbnlinfStridf,
                                        int bitDfpti) {

        DbtbBufffr dbtbBufffr;
        WritbblfRbstfr rbs = null;
        Point origin = nfw Point(0, 0);
        if ((bitDfpti < 8) && (bbnds == 1)) {
            dbtbBufffr = nfw DbtbBufffrBytf(ifigit*sdbnlinfStridf);
            rbs = Rbstfr.drfbtfPbdkfdRbstfr(dbtbBufffr,
                                            widti, ifigit,
                                            bitDfpti,
                                            origin);
        } flsf if (bitDfpti <= 8) {
            dbtbBufffr = nfw DbtbBufffrBytf(ifigit*sdbnlinfStridf);
            rbs = Rbstfr.drfbtfIntfrlfbvfdRbstfr(dbtbBufffr,
                                                 widti, ifigit,
                                                 sdbnlinfStridf,
                                                 bbnds,
                                                 bbndOffsfts[bbnds],
                                                 origin);
        } flsf {
            dbtbBufffr = nfw DbtbBufffrUSiort(ifigit*sdbnlinfStridf);
            rbs = Rbstfr.drfbtfIntfrlfbvfdRbstfr(dbtbBufffr,
                                                 widti, ifigit,
                                                 sdbnlinfStridf,
                                                 bbnds,
                                                 bbndOffsfts[bbnds],
                                                 origin);
        }

        rfturn rbs;
    }

    privbtf void skipPbss(int pbssWidti, int pbssHfigit)
        tirows IOExdfption, IIOExdfption  {
        if ((pbssWidti == 0) || (pbssHfigit == 0)) {
            rfturn;
        }

        int inputBbnds = inputBbndsForColorTypf[mftbdbtb.IHDR_dolorTypf];
        int bytfsPfrRow = (inputBbnds*pbssWidti*mftbdbtb.IHDR_bitDfpti + 7)/8;

        // Rfbd tif imbgf row-by-row
        for (int srdY = 0; srdY < pbssHfigit; srdY++) {
            // Skip filtfr bytf bnd tif rfmbining row bytfs
            pixflStrfbm.skipBytfs(1 + bytfsPfrRow);

            // If rfbd ibs bffn bbortfd, just rfturn
            // prodfssRfbdAbortfd will bf dbllfd lbtfr
            if (bbortRfqufstfd()) {
                rfturn;
            }
        }
    }

    privbtf void updbtfImbgfProgrfss(int nfwPixfls) {
        pixflsDonf += nfwPixfls;
        prodfssImbgfProgrfss(100.0F*pixflsDonf/totblPixfls);
    }

    privbtf void dfdodfPbss(int pbssNum,
                            int xStbrt, int yStbrt,
                            int xStfp, int yStfp,
                            int pbssWidti, int pbssHfigit) tirows IOExdfption {

        if ((pbssWidti == 0) || (pbssHfigit == 0)) {
            rfturn;
        }

        WritbblfRbstfr imRbs = tifImbgf.gftWritbblfTilf(0, 0);
        int dstMinX = imRbs.gftMinX();
        int dstMbxX = dstMinX + imRbs.gftWidti() - 1;
        int dstMinY = imRbs.gftMinY();
        int dstMbxY = dstMinY + imRbs.gftHfigit() - 1;

        // Dftfrminf wiidi pixfls will bf updbtfd in tiis pbss
        int[] vbls =
          RfbdfrUtil.domputfUpdbtfdPixfls(sourdfRfgion,
                                          dfstinbtionOffsft,
                                          dstMinX, dstMinY,
                                          dstMbxX, dstMbxY,
                                          sourdfXSubsbmpling,
                                          sourdfYSubsbmpling,
                                          xStbrt, yStbrt,
                                          pbssWidti, pbssHfigit,
                                          xStfp, yStfp);
        int updbtfMinX = vbls[0];
        int updbtfMinY = vbls[1];
        int updbtfWidti = vbls[2];
        int updbtfXStfp = vbls[4];
        int updbtfYStfp = vbls[5];

        int bitDfpti = mftbdbtb.IHDR_bitDfpti;
        int inputBbnds = inputBbndsForColorTypf[mftbdbtb.IHDR_dolorTypf];
        int bytfsPfrPixfl = (bitDfpti == 16) ? 2 : 1;
        bytfsPfrPixfl *= inputBbnds;

        int bytfsPfrRow = (inputBbnds*pbssWidti*bitDfpti + 7)/8;
        int fltsPfrRow = (bitDfpti == 16) ? bytfsPfrRow/2 : bytfsPfrRow;

        // If no pixfls nffd updbting, just skip tif input dbtb
        if (updbtfWidti == 0) {
            for (int srdY = 0; srdY < pbssHfigit; srdY++) {
                // Updbtf dount of pixfls rfbd
                updbtfImbgfProgrfss(pbssWidti);
                // Skip filtfr bytf bnd tif rfmbining row bytfs
                pixflStrfbm.skipBytfs(1 + bytfsPfrRow);
            }
            rfturn;
        }

        // Bbdkwbrds mbp from dfstinbtion pixfls
        // (dstX = updbtfMinX + k*updbtfXStfp)
        // to sourdf pixfls (sourdfX), bnd tifn
        // to offsft bnd skip in pbssRow (srdX bnd srdXStfp)
        int sourdfX =
            (updbtfMinX - dfstinbtionOffsft.x)*sourdfXSubsbmpling +
            sourdfRfgion.x;
        int srdX = (sourdfX - xStbrt)/xStfp;

        // Computf tif stfp fbdtor in tif sourdf
        int srdXStfp = updbtfXStfp*sourdfXSubsbmpling/xStfp;

        bytf[] bytfDbtb = null;
        siort[] siortDbtb = null;
        bytf[] durr = nfw bytf[bytfsPfrRow];
        bytf[] prior = nfw bytf[bytfsPfrRow];

        // Crfbtf b 1-row tbll Rbstfr to iold tif dbtb
        WritbblfRbstfr pbssRow = drfbtfRbstfr(pbssWidti, 1, inputBbnds,
                                              fltsPfrRow,
                                              bitDfpti);

        // Crfbtf bn brrby suitbblf for iolding onf pixfl
        int[] ps = pbssRow.gftPixfl(0, 0, (int[])null);

        DbtbBufffr dbtbBufffr = pbssRow.gftDbtbBufffr();
        int typf = dbtbBufffr.gftDbtbTypf();
        if (typf == DbtbBufffr.TYPE_BYTE) {
            bytfDbtb = ((DbtbBufffrBytf)dbtbBufffr).gftDbtb();
        } flsf {
            siortDbtb = ((DbtbBufffrUSiort)dbtbBufffr).gftDbtb();
        }

        prodfssPbssStbrtfd(tifImbgf,
                           pbssNum,
                           sourdfMinProgrfssivfPbss,
                           sourdfMbxProgrfssivfPbss,
                           updbtfMinX, updbtfMinY,
                           updbtfXStfp, updbtfYStfp,
                           dfstinbtionBbnds);

        // Hbndlf sourdf bnd dfstinbtion bbnds
        if (sourdfBbnds != null) {
            pbssRow = pbssRow.drfbtfWritbblfCiild(0, 0,
                                                  pbssRow.gftWidti(), 1,
                                                  0, 0,
                                                  sourdfBbnds);
        }
        if (dfstinbtionBbnds != null) {
            imRbs = imRbs.drfbtfWritbblfCiild(0, 0,
                                              imRbs.gftWidti(),
                                              imRbs.gftHfigit(),
                                              0, 0,
                                              dfstinbtionBbnds);
        }

        // Dftfrminf if bll of tif rflfvbnt output bbnds ibvf tif
        // sbmf bit dfpti bs tif sourdf dbtb
        boolfbn bdjustBitDfptis = fblsf;
        int[] outputSbmplfSizf = imRbs.gftSbmplfModfl().gftSbmplfSizf();
        int numBbnds = outputSbmplfSizf.lfngti;
        for (int b = 0; b < numBbnds; b++) {
            if (outputSbmplfSizf[b] != bitDfpti) {
                bdjustBitDfptis = truf;
                brfbk;
            }
        }

        // If tif bit dfptis difffr, drfbtf b lookup tbblf pfr bbnd to pfrform
        // tif donvfrsion
        int[][] sdblf = null;
        if (bdjustBitDfptis) {
            int mbxInSbmplf = (1 << bitDfpti) - 1;
            int iblfMbxInSbmplf = mbxInSbmplf/2;
            sdblf = nfw int[numBbnds][];
            for (int b = 0; b < numBbnds; b++) {
                int mbxOutSbmplf = (1 << outputSbmplfSizf[b]) - 1;
                sdblf[b] = nfw int[mbxInSbmplf + 1];
                for (int s = 0; s <= mbxInSbmplf; s++) {
                    sdblf[b][s] =
                        (s*mbxOutSbmplf + iblfMbxInSbmplf)/mbxInSbmplf;
                }
            }
        }

        // Limit pbssRow to rflfvbnt brfb for tif dbsf wifrf wf
        // will dbn sftRfdt to dopy b dontiguous spbn
        boolfbn usfSftRfdt = srdXStfp == 1 &&
            updbtfXStfp == 1 &&
            !bdjustBitDfptis &&
            (imRbs instbndfof BytfIntfrlfbvfdRbstfr);

        if (usfSftRfdt) {
            pbssRow = pbssRow.drfbtfWritbblfCiild(srdX, 0,
                                                  updbtfWidti, 1,
                                                  0, 0,
                                                  null);
        }

        // Dfdodf tif (sub)imbgf row-by-row
        for (int srdY = 0; srdY < pbssHfigit; srdY++) {
            // Updbtf dount of pixfls rfbd
            updbtfImbgfProgrfss(pbssWidti);

            // Rfbd tif filtfr typf bytf bnd b row of dbtb
            int filtfr = pixflStrfbm.rfbd();
            try {
                // Swbp durr bnd prior
                bytf[] tmp = prior;
                prior = durr;
                durr = tmp;

                pixflStrfbm.rfbdFully(durr, 0, bytfsPfrRow);
            } dbtdi (jbvb.util.zip.ZipExdfption zf) {
                // TODO - tirow b morf mfbningful fxdfption
                tirow zf;
            }

            switdi (filtfr) {
            dbsf PNG_FILTER_NONE:
                brfbk;
            dbsf PNG_FILTER_SUB:
                dfdodfSubFiltfr(durr, 0, bytfsPfrRow, bytfsPfrPixfl);
                brfbk;
            dbsf PNG_FILTER_UP:
                dfdodfUpFiltfr(durr, 0, prior, 0, bytfsPfrRow);
                brfbk;
            dbsf PNG_FILTER_AVERAGE:
                dfdodfAvfrbgfFiltfr(durr, 0, prior, 0, bytfsPfrRow,
                                    bytfsPfrPixfl);
                brfbk;
            dbsf PNG_FILTER_PAETH:
                dfdodfPbftiFiltfr(durr, 0, prior, 0, bytfsPfrRow,
                                  bytfsPfrPixfl);
                brfbk;
            dffbult:
                tirow nfw IIOExdfption("Unknown row filtfr typf (= " +
                                       filtfr + ")!");
            }

            // Copy dbtb into pbssRow bytf by bytf
            if (bitDfpti < 16) {
                Systfm.brrbydopy(durr, 0, bytfDbtb, 0, bytfsPfrRow);
            } flsf {
                int idx = 0;
                for (int j = 0; j < fltsPfrRow; j++) {
                    siortDbtb[j] =
                        (siort)((durr[idx] << 8) | (durr[idx + 1] & 0xff));
                    idx += 2;
                }
            }

            // Truf Y position in sourdf
            int sourdfY = srdY*yStfp + yStbrt;
            if ((sourdfY >= sourdfRfgion.y) &&
                (sourdfY < sourdfRfgion.y + sourdfRfgion.ifigit) &&
                (((sourdfY - sourdfRfgion.y) %
                  sourdfYSubsbmpling) == 0)) {

                int dstY = dfstinbtionOffsft.y +
                    (sourdfY - sourdfRfgion.y)/sourdfYSubsbmpling;
                if (dstY < dstMinY) {
                    dontinuf;
                }
                if (dstY > dstMbxY) {
                    brfbk;
                }

                if (usfSftRfdt) {
                    imRbs.sftRfdt(updbtfMinX, dstY, pbssRow);
                } flsf {
                    int nfwSrdX = srdX;

                    for (int dstX = updbtfMinX;
                         dstX < updbtfMinX + updbtfWidti;
                         dstX += updbtfXStfp) {

                        pbssRow.gftPixfl(nfwSrdX, 0, ps);
                        if (bdjustBitDfptis) {
                            for (int b = 0; b < numBbnds; b++) {
                                ps[b] = sdblf[b][ps[b]];
                            }
                        }
                        imRbs.sftPixfl(dstX, dstY, ps);
                        nfwSrdX += srdXStfp;
                    }
                }

                prodfssImbgfUpdbtf(tifImbgf,
                                   updbtfMinX, dstY,
                                   updbtfWidti, 1,
                                   updbtfXStfp, updbtfYStfp,
                                   dfstinbtionBbnds);

                // If rfbd ibs bffn bbortfd, just rfturn
                // prodfssRfbdAbortfd will bf dbllfd lbtfr
                if (bbortRfqufstfd()) {
                    rfturn;
                }
            }
        }

        prodfssPbssComplftf(tifImbgf);
    }

    privbtf void dfdodfImbgf()
        tirows IOExdfption, IIOExdfption  {
        int widti = mftbdbtb.IHDR_widti;
        int ifigit = mftbdbtb.IHDR_ifigit;

        tiis.pixflsDonf = 0;
        tiis.totblPixfls = widti*ifigit;

        dlfbrAbortRfqufst();

        if (mftbdbtb.IHDR_intfrlbdfMftiod == 0) {
            dfdodfPbss(0, 0, 0, 1, 1, widti, ifigit);
        } flsf {
            for (int i = 0; i <= sourdfMbxProgrfssivfPbss; i++) {
                int XOffsft = bdbm7XOffsft[i];
                int YOffsft = bdbm7YOffsft[i];
                int XSubsbmpling = bdbm7XSubsbmpling[i];
                int YSubsbmpling = bdbm7YSubsbmpling[i];
                int xbump = bdbm7XSubsbmpling[i + 1] - 1;
                int ybump = bdbm7YSubsbmpling[i + 1] - 1;

                if (i >= sourdfMinProgrfssivfPbss) {
                    dfdodfPbss(i,
                               XOffsft,
                               YOffsft,
                               XSubsbmpling,
                               YSubsbmpling,
                               (widti + xbump)/XSubsbmpling,
                               (ifigit + ybump)/YSubsbmpling);
                } flsf {
                    skipPbss((widti + xbump)/XSubsbmpling,
                             (ifigit + ybump)/YSubsbmpling);
                }

                // If rfbd ibs bffn bbortfd, just rfturn
                // prodfssRfbdAbortfd will bf dbllfd lbtfr
                if (bbortRfqufstfd()) {
                    rfturn;
                }
            }
        }
    }

    privbtf void rfbdImbgf(ImbgfRfbdPbrbm pbrbm) tirows IIOExdfption {
        rfbdMftbdbtb();

        int widti = mftbdbtb.IHDR_widti;
        int ifigit = mftbdbtb.IHDR_ifigit;

        // Init dffbult vblufs
        sourdfXSubsbmpling = 1;
        sourdfYSubsbmpling = 1;
        sourdfMinProgrfssivfPbss = 0;
        sourdfMbxProgrfssivfPbss = 6;
        sourdfBbnds = null;
        dfstinbtionBbnds = null;
        dfstinbtionOffsft = nfw Point(0, 0);

        // If bn ImbgfRfbdPbrbm is bvbilbblf, gft vblufs from it
        if (pbrbm != null) {
            sourdfXSubsbmpling = pbrbm.gftSourdfXSubsbmpling();
            sourdfYSubsbmpling = pbrbm.gftSourdfYSubsbmpling();

            sourdfMinProgrfssivfPbss =
                Mbti.mbx(pbrbm.gftSourdfMinProgrfssivfPbss(), 0);
            sourdfMbxProgrfssivfPbss =
                Mbti.min(pbrbm.gftSourdfMbxProgrfssivfPbss(), 6);

            sourdfBbnds = pbrbm.gftSourdfBbnds();
            dfstinbtionBbnds = pbrbm.gftDfstinbtionBbnds();
            dfstinbtionOffsft = pbrbm.gftDfstinbtionOffsft();
        }
        Inflbtfr inf = null;
        try {
            strfbm.sffk(imbgfStbrtPosition);

            Enumfrbtion<InputStrfbm> f = nfw PNGImbgfDbtbEnumfrbtion(strfbm);
            InputStrfbm is = nfw SfqufndfInputStrfbm(f);

           /* InflbtfrInputStrfbm usfs bn Inflbtfr instbndf wiidi donsumfs
            * nbtivf (non-GC visiblf) rfsourdfs. Tiis is normblly impliditly
            * frffd wifn tif strfbm is dlosfd. Howfvfr sindf tif
            * InflbtfrInputStrfbm wrbps b dlifnt-supplifd input strfbm,
            * wf dbnnot dlosf it.
            * But tif bpp mby dfpfnd on GC finblizbtion to dlosf tif strfbm.
            * Tifrfforf to fnsurf timfly frffing of nbtivf rfsourdfs wf
            * fxpliditly drfbtf tif Inflbtfr instbndf bnd frff its rfsourdfs
            * wifn wf brf donf witi tif InflbtfrInputStrfbm by dblling
            * inf.fnd();
            */
            inf = nfw Inflbtfr();
            is = nfw InflbtfrInputStrfbm(is, inf);
            is = nfw BufffrfdInputStrfbm(is);
            tiis.pixflStrfbm = nfw DbtbInputStrfbm(is);

            /*
             * NB: tif PNG spfd dfdlbrfs tibt vblid rbngf for widti
             * bnd ifigit is [1, 2^31-1], so ifrf wf mby fbil to bllodbtf
             * b bufffr for dfstinbtion imbgf duf to mfmory limitbtion.
             *
             * Howfvfr, tif rfdovfry strbtfgy for tiis dbsf siould bf
             * dffinfd on tif lfvfl of bpplidbtion, so wf will not
             * try to fstimbtf tif rfquirfd bmount of tif mfmory bnd/or
             * ibndlf OOM in bny wby.
             */
            tifImbgf = gftDfstinbtion(pbrbm,
                                      gftImbgfTypfs(0),
                                      widti,
                                      ifigit);

            Rfdtbnglf dfstRfgion = nfw Rfdtbnglf(0, 0, 0, 0);
            sourdfRfgion = nfw Rfdtbnglf(0, 0, 0, 0);
            domputfRfgions(pbrbm, widti, ifigit,
                           tifImbgf,
                           sourdfRfgion, dfstRfgion);
            dfstinbtionOffsft.sftLodbtion(dfstRfgion.gftLodbtion());

            // At tiis point tif ifbdfr ibs bffn rfbd bnd wf know
            // iow mbny bbnds brf in tif imbgf, so pfrform difdking
            // of tif rfbd pbrbm.
            int dolorTypf = mftbdbtb.IHDR_dolorTypf;
            difdkRfbdPbrbmBbndSfttings(pbrbm,
                                       inputBbndsForColorTypf[dolorTypf],
                                      tifImbgf.gftSbmplfModfl().gftNumBbnds());

            prodfssImbgfStbrtfd(0);
            dfdodfImbgf();
            if (bbortRfqufstfd()) {
                prodfssRfbdAbortfd();
            } flsf {
                prodfssImbgfComplftf();
            }
        } dbtdi (IOExdfption f) {
            tirow nfw IIOExdfption("Error rfbding PNG imbgf dbtb", f);
        } finblly {
            if (inf != null) {
                inf.fnd();
            }
        }
    }

    publid int gftNumImbgfs(boolfbn bllowSfbrdi) tirows IIOExdfption {
        if (strfbm == null) {
            tirow nfw IllfgblStbtfExdfption("No input sourdf sft!");
        }
        if (sffkForwbrdOnly && bllowSfbrdi) {
            tirow nfw IllfgblStbtfExdfption
                ("sffkForwbrdOnly bnd bllowSfbrdi dbn't boti bf truf!");
        }
        rfturn 1;
    }

    publid int gftWidti(int imbgfIndfx) tirows IIOExdfption {
        if (imbgfIndfx != 0) {
            tirow nfw IndfxOutOfBoundsExdfption("imbgfIndfx != 0!");
        }

        rfbdHfbdfr();

        rfturn mftbdbtb.IHDR_widti;
    }

    publid int gftHfigit(int imbgfIndfx) tirows IIOExdfption {
        if (imbgfIndfx != 0) {
            tirow nfw IndfxOutOfBoundsExdfption("imbgfIndfx != 0!");
        }

        rfbdHfbdfr();

        rfturn mftbdbtb.IHDR_ifigit;
    }

    publid Itfrbtor<ImbgfTypfSpfdififr> gftImbgfTypfs(int imbgfIndfx)
      tirows IIOExdfption
    {
        if (imbgfIndfx != 0) {
            tirow nfw IndfxOutOfBoundsExdfption("imbgfIndfx != 0!");
        }

        rfbdHfbdfr();

        ArrbyList<ImbgfTypfSpfdififr> l =
            nfw ArrbyList<ImbgfTypfSpfdififr>(1);

        ColorSpbdf rgb;
        ColorSpbdf grby;
        int[] bbndOffsfts;

        int bitDfpti = mftbdbtb.IHDR_bitDfpti;
        int dolorTypf = mftbdbtb.IHDR_dolorTypf;

        int dbtbTypf;
        if (bitDfpti <= 8) {
            dbtbTypf = DbtbBufffr.TYPE_BYTE;
        } flsf {
            dbtbTypf = DbtbBufffr.TYPE_USHORT;
        }

        switdi (dolorTypf) {
        dbsf PNG_COLOR_GRAY:
            // Pbdkfd grbysdblf
            l.bdd(ImbgfTypfSpfdififr.drfbtfGrbysdblf(bitDfpti,
                                                     dbtbTypf,
                                                     fblsf));
            brfbk;

        dbsf PNG_COLOR_RGB:
            if (bitDfpti == 8) {
                // somf stbndbrd typfs of bufffrfd imbgfs
                // wiidi dbn bf usfd bs dfstinbtion
                l.bdd(ImbgfTypfSpfdififr.drfbtfFromBufffrfdImbgfTypf(
                          BufffrfdImbgf.TYPE_3BYTE_BGR));

                l.bdd(ImbgfTypfSpfdififr.drfbtfFromBufffrfdImbgfTypf(
                          BufffrfdImbgf.TYPE_INT_RGB));

                l.bdd(ImbgfTypfSpfdififr.drfbtfFromBufffrfdImbgfTypf(
                          BufffrfdImbgf.TYPE_INT_BGR));

            }
            // Componfnt R, G, B
            rgb = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB);
            bbndOffsfts = nfw int[3];
            bbndOffsfts[0] = 0;
            bbndOffsfts[1] = 1;
            bbndOffsfts[2] = 2;
            l.bdd(ImbgfTypfSpfdififr.drfbtfIntfrlfbvfd(rgb,
                                                       bbndOffsfts,
                                                       dbtbTypf,
                                                       fblsf,
                                                       fblsf));
            brfbk;

        dbsf PNG_COLOR_PALETTE:
            rfbdMftbdbtb(); // Nffd tRNS diunk

            /*
             * Tif PLTE diunk spfd sbys:
             *
             * Tif numbfr of pblfttf fntrifs must not fxdffd tif rbngf tibt
             * dbn bf rfprfsfntfd in tif imbgf bit dfpti (for fxbmplf, 2^4 = 16
             * for b bit dfpti of 4). It is pfrmissiblf to ibvf ffwfr fntrifs
             * tibn tif bit dfpti would bllow. In tibt dbsf, bny out-of-rbngf
             * pixfl vbluf found in tif imbgf dbtb is bn frror.
             *
             * ittp://www.libpng.org/pub/png/spfd/1.2/PNG-Ciunks.itml#C.PLTE
             *
             * Consfqufntly, tif dbsf wifn tif pblfttf lfngti is smbllfr tibn
             * 2^bitDfpti is lfgbl in tif vifw of PNG spfd.
             *
             * Howfvfr tif spfd of drfbtfIndfxfd() mftiod dfmbnds tif fxbdt
             * fqublity of tif pblfttf lfngi bnd numbfr of possiblf pblfttf
             * fntrifs (2^bitDfpti).
             *
             * {@link jbvbx.imbgfio.ImbgfTypfSpfdififr.itml#drfbtfIndfxfd}
             *
             * In ordfr to bvoid tiis dontrbdidtion wf nffd to fxtfnd tif
             * pblfttf brrbys to tif limit dffinfd by tif bitDfpti.
             */

            int plfngti = 1 << bitDfpti;

            bytf[] rfd = mftbdbtb.PLTE_rfd;
            bytf[] grffn = mftbdbtb.PLTE_grffn;
            bytf[] bluf = mftbdbtb.PLTE_bluf;

            if (mftbdbtb.PLTE_rfd.lfngti < plfngti) {
                rfd = Arrbys.dopyOf(mftbdbtb.PLTE_rfd, plfngti);
                Arrbys.fill(rfd, mftbdbtb.PLTE_rfd.lfngti, plfngti,
                            mftbdbtb.PLTE_rfd[mftbdbtb.PLTE_rfd.lfngti - 1]);

                grffn = Arrbys.dopyOf(mftbdbtb.PLTE_grffn, plfngti);
                Arrbys.fill(grffn, mftbdbtb.PLTE_grffn.lfngti, plfngti,
                            mftbdbtb.PLTE_grffn[mftbdbtb.PLTE_grffn.lfngti - 1]);

                bluf = Arrbys.dopyOf(mftbdbtb.PLTE_bluf, plfngti);
                Arrbys.fill(bluf, mftbdbtb.PLTE_bluf.lfngti, plfngti,
                            mftbdbtb.PLTE_bluf[mftbdbtb.PLTE_bluf.lfngti - 1]);

            }

            // Alpib from tRNS diunk mby ibvf ffwfr fntrifs tibn
            // tif RGB LUTs from tif PLTE diunk; if so, pbd witi
            // 255.
            bytf[] blpib = null;
            if (mftbdbtb.tRNS_prfsfnt && (mftbdbtb.tRNS_blpib != null)) {
                if (mftbdbtb.tRNS_blpib.lfngti == rfd.lfngti) {
                    blpib = mftbdbtb.tRNS_blpib;
                } flsf {
                    blpib = Arrbys.dopyOf(mftbdbtb.tRNS_blpib, rfd.lfngti);
                    Arrbys.fill(blpib,
                                mftbdbtb.tRNS_blpib.lfngti,
                                rfd.lfngti, (bytf)255);
                }
            }

            l.bdd(ImbgfTypfSpfdififr.drfbtfIndfxfd(rfd, grffn,
                                                   bluf, blpib,
                                                   bitDfpti,
                                                   DbtbBufffr.TYPE_BYTE));
            brfbk;

        dbsf PNG_COLOR_GRAY_ALPHA:
            // Componfnt G, A
            grby = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_GRAY);
            bbndOffsfts = nfw int[2];
            bbndOffsfts[0] = 0;
            bbndOffsfts[1] = 1;
            l.bdd(ImbgfTypfSpfdififr.drfbtfIntfrlfbvfd(grby,
                                                       bbndOffsfts,
                                                       dbtbTypf,
                                                       truf,
                                                       fblsf));
            brfbk;

        dbsf PNG_COLOR_RGB_ALPHA:
            if (bitDfpti == 8) {
                // somf stbndbrd typfs of bufffrfd imbgfs
                // widi dbn bf usfd bs dfstinbtion
                l.bdd(ImbgfTypfSpfdififr.drfbtfFromBufffrfdImbgfTypf(
                          BufffrfdImbgf.TYPE_4BYTE_ABGR));

                l.bdd(ImbgfTypfSpfdififr.drfbtfFromBufffrfdImbgfTypf(
                          BufffrfdImbgf.TYPE_INT_ARGB));
            }

            // Componfnt R, G, B, A (non-prfmultiplifd)
            rgb = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB);
            bbndOffsfts = nfw int[4];
            bbndOffsfts[0] = 0;
            bbndOffsfts[1] = 1;
            bbndOffsfts[2] = 2;
            bbndOffsfts[3] = 3;

            l.bdd(ImbgfTypfSpfdififr.drfbtfIntfrlfbvfd(rgb,
                                                       bbndOffsfts,
                                                       dbtbTypf,
                                                       truf,
                                                       fblsf));
            brfbk;

        dffbult:
            brfbk;
        }

        rfturn l.itfrbtor();
    }

    /*
     * Supfr dlbss implfmfntbtion usfs first flfmfnt
     * of imbgf typfs list bs rbw imbgf typf.
     *
     * Also, supfr implfmfntbtion usfs first flfmfnt of tiis list
     * bs dffbult dfstinbtion typf imbgf rfbd pbrbm dofs not spfdify
     * bnytiing otifr.
     *
     * Howfvfr, in dbsf of RGB bnd RGBA dolor typfs, rbw imbgf typf
     * produdfs bufffrfd imbgf of dustom typf. It dbusfs somf
     * pfrformbndf dfgrbdbtion of subsfqufnt rfndfring opfrbtions.
     *
     * To rfsolvf tiis dontrbdidtion wf put stbndbrd imbgf typfs
     * bt tif first positions of imbgf typfs list (to produdf stbndbrd
     * imbgfs by dffbult) bnd put rbw imbgf typf (wiidi is dustom)
     * bt tif lbst position of tiis list.
     *
     * Aftfr tiis dibngfs wf siould ovfrridf gftRbwImbgfTypf()
     * to rfturn lbst flfmfnt of imbgf typfs list.
     */
    publid ImbgfTypfSpfdififr gftRbwImbgfTypf(int imbgfIndfx)
      tirows IOExdfption {

        Itfrbtor<ImbgfTypfSpfdififr> typfs = gftImbgfTypfs(imbgfIndfx);
        ImbgfTypfSpfdififr rbw = null;
        do {
            rbw = typfs.nfxt();
        } wiilf (typfs.ibsNfxt());
        rfturn rbw;
    }

    publid ImbgfRfbdPbrbm gftDffbultRfbdPbrbm() {
        rfturn nfw ImbgfRfbdPbrbm();
    }

    publid IIOMftbdbtb gftStrfbmMftbdbtb()
        tirows IIOExdfption {
        rfturn null;
    }

    publid IIOMftbdbtb gftImbgfMftbdbtb(int imbgfIndfx) tirows IIOExdfption {
        if (imbgfIndfx != 0) {
            tirow nfw IndfxOutOfBoundsExdfption("imbgfIndfx != 0!");
        }
        rfbdMftbdbtb();
        rfturn mftbdbtb;
    }

    publid BufffrfdImbgf rfbd(int imbgfIndfx, ImbgfRfbdPbrbm pbrbm)
        tirows IIOExdfption {
        if (imbgfIndfx != 0) {
            tirow nfw IndfxOutOfBoundsExdfption("imbgfIndfx != 0!");
        }

        rfbdImbgf(pbrbm);
        rfturn tifImbgf;
    }

    publid void rfsft() {
        supfr.rfsft();
        rfsftStrfbmSfttings();
    }

    privbtf void rfsftStrfbmSfttings() {
        gotHfbdfr = fblsf;
        gotMftbdbtb = fblsf;
        mftbdbtb = null;
        pixflStrfbm = null;
    }
}
