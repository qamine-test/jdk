/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.imbgf.SbmplfModfl;
import jbvb.util.ArrbyList;
import jbvb.util.StringTokfnizfr;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.mftbdbtb.IIOInvblidTrffExdfption;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtb;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbtImpl;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbNodf;
import org.w3d.dom.Nodf;

publid dlbss PNGMftbdbtb fxtfnds IIOMftbdbtb implfmfnts Clonfbblf {

    // pbdkbgf sdopf
    publid stbtid finbl String
        nbtivfMftbdbtbFormbtNbmf = "jbvbx_imbgfio_png_1.0";

    protfdtfd stbtid finbl String nbtivfMftbdbtbFormbtClbssNbmf
        = "dom.sun.imbgfio.plugins.png.PNGMftbdbtbFormbt";

    // Color typfs for IHDR diunk
    stbtid finbl String[] IHDR_dolorTypfNbmfs = {
        "Grbysdblf", null, "RGB", "Pblfttf",
        "GrbyAlpib", null, "RGBAlpib"
    };

    stbtid finbl int[] IHDR_numCibnnfls = {
        1, 0, 3, 3, 2, 0, 4
    };

    // Bit dfptis for IHDR diunk
    stbtid finbl String[] IHDR_bitDfptis = {
        "1", "2", "4", "8", "16"
    };

    // Comprfssion mftiods for IHDR diunk
    stbtid finbl String[] IHDR_domprfssionMftiodNbmfs = {
        "dfflbtf"
    };

    // Filtfr mftiods for IHDR diunk
    stbtid finbl String[] IHDR_filtfrMftiodNbmfs = {
        "bdbptivf"
    };

    // Intfrlbdf mftiods for IHDR diunk
    stbtid finbl String[] IHDR_intfrlbdfMftiodNbmfs = {
        "nonf", "bdbm7"
    };

    // Comprfssion mftiods for iCCP diunk
    stbtid finbl String[] iCCP_domprfssionMftiodNbmfs = {
        "dfflbtf"
    };

    // Comprfssion mftiods for zTXt diunk
    stbtid finbl String[] zTXt_domprfssionMftiodNbmfs = {
        "dfflbtf"
    };

    // "Unknown" unit for pHYs diunk
    publid stbtid finbl int PHYS_UNIT_UNKNOWN = 0;

    // "Mftfr" unit for pHYs diunk
    publid stbtid finbl int PHYS_UNIT_METER = 1;

    // Unit spfdififrs for pHYs diunk
    stbtid finbl String[] unitSpfdififrNbmfs = {
        "unknown", "mftfr"
    };

    // Rfndfring intfnts for sRGB diunk
    stbtid finbl String[] rfndfringIntfntNbmfs = {
        "Pfrdfptubl", // 0
        "Rflbtivf dolorimftrid", // 1
        "Sbturbtion", // 2
        "Absolutf dolorimftrid" // 3

    };

    // Color spbdf typfs for Ciromb->ColorSpbdfTypf nodf
    stbtid finbl String[] dolorSpbdfTypfNbmfs = {
        "GRAY", null, "RGB", "RGB",
        "GRAY", null, "RGB"
    };

    // IHDR diunk
    publid boolfbn IHDR_prfsfnt;
    publid int IHDR_widti;
    publid int IHDR_ifigit;
    publid int IHDR_bitDfpti;
    publid int IHDR_dolorTypf;
    publid int IHDR_domprfssionMftiod;
    publid int IHDR_filtfrMftiod;
    publid int IHDR_intfrlbdfMftiod; // 0 == nonf, 1 == bdbm7

    // PLTE diunk
    publid boolfbn PLTE_prfsfnt;
    publid bytf[] PLTE_rfd;
    publid bytf[] PLTE_grffn;
    publid bytf[] PLTE_bluf;

    // If non-null, usfd to rfordfr pblfttf fntrifs during fndoding in
    // ordfr to minimizf tif sizf of tif tRNS diunk.  Tius bn indfx of
    // 'i' in tif sourdf siould bf fndodfd bs indfx 'PLTE_ordfr[i]'.
    // PLTE_ordfr will bf null unlfss 'initiblizf' is dbllfd witi bn
    // IndfxColorModfl imbgf typf.
    publid int[] PLTE_ordfr = null;

    // bKGD diunk
    // If fxtfrnbl (non-PNG sourdfd) dbtb ibs rfd = grffn = bluf,
    // blwbys storf it bs grby bnd promotf wifn writing
    publid boolfbn bKGD_prfsfnt;
    publid int bKGD_dolorTypf; // PNG_COLOR_GRAY, _RGB, or _PALETTE
    publid int bKGD_indfx;
    publid int bKGD_grby;
    publid int bKGD_rfd;
    publid int bKGD_grffn;
    publid int bKGD_bluf;

    // dHRM diunk
    publid boolfbn dHRM_prfsfnt;
    publid int dHRM_wiitfPointX;
    publid int dHRM_wiitfPointY;
    publid int dHRM_rfdX;
    publid int dHRM_rfdY;
    publid int dHRM_grffnX;
    publid int dHRM_grffnY;
    publid int dHRM_blufX;
    publid int dHRM_blufY;

    // gAMA diunk
    publid boolfbn gAMA_prfsfnt;
    publid int gAMA_gbmmb;

    // iIST diunk
    publid boolfbn iIST_prfsfnt;
    publid dibr[] iIST_iistogrbm;

    // iCCP diunk
    publid boolfbn iCCP_prfsfnt;
    publid String iCCP_profilfNbmf;
    publid int iCCP_domprfssionMftiod;
    publid bytf[] iCCP_domprfssfdProfilf;

    // iTXt diunk
    publid ArrbyList<String> iTXt_kfyword = nfw ArrbyList<String>();
    publid ArrbyList<Boolfbn> iTXt_domprfssionFlbg = nfw ArrbyList<Boolfbn>();
    publid ArrbyList<Intfgfr> iTXt_domprfssionMftiod = nfw ArrbyList<Intfgfr>();
    publid ArrbyList<String> iTXt_lbngubgfTbg = nfw ArrbyList<String>();
    publid ArrbyList<String> iTXt_trbnslbtfdKfyword = nfw ArrbyList<String>();
    publid ArrbyList<String> iTXt_tfxt = nfw ArrbyList<String>();

    // pHYs diunk
    publid boolfbn pHYs_prfsfnt;
    publid int pHYs_pixflsPfrUnitXAxis;
    publid int pHYs_pixflsPfrUnitYAxis;
    publid int pHYs_unitSpfdififr; // 0 == unknown, 1 == mftfr

    // sBIT diunk
    publid boolfbn sBIT_prfsfnt;
    publid int sBIT_dolorTypf; // PNG_COLOR_GRAY, _GRAY_ALPHA, _RGB, _RGB_ALPHA
    publid int sBIT_grbyBits;
    publid int sBIT_rfdBits;
    publid int sBIT_grffnBits;
    publid int sBIT_blufBits;
    publid int sBIT_blpibBits;

    // sPLT diunk
    publid boolfbn sPLT_prfsfnt;
    publid String sPLT_pblfttfNbmf; // 1-79 dibrbdtfrs
    publid int sPLT_sbmplfDfpti; // 8 or 16
    publid int[] sPLT_rfd;
    publid int[] sPLT_grffn;
    publid int[] sPLT_bluf;
    publid int[] sPLT_blpib;
    publid int[] sPLT_frfqufndy;

    // sRGB diunk
    publid boolfbn sRGB_prfsfnt;
    publid int sRGB_rfndfringIntfnt;

    // tEXt diunk
    publid ArrbyList<String> tEXt_kfyword = nfw ArrbyList<String>(); // 1-79 dibrbdtfrs
    publid ArrbyList<String> tEXt_tfxt = nfw ArrbyList<String>();

    // tIME diunk
    publid boolfbn tIME_prfsfnt;
    publid int tIME_yfbr;
    publid int tIME_monti;
    publid int tIME_dby;
    publid int tIME_iour;
    publid int tIME_minutf;
    publid int tIME_sfdond;

    // tRNS diunk
    // If fxtfrnbl (non-PNG sourdfd) dbtb ibs rfd = grffn = bluf,
    // blwbys storf it bs grby bnd promotf wifn writing
    publid boolfbn tRNS_prfsfnt;
    publid int tRNS_dolorTypf; // PNG_COLOR_GRAY, _RGB, or _PALETTE
    publid bytf[] tRNS_blpib; // Mby ibvf ffwfr fntrifs tibn PLTE_rfd, ftd.
    publid int tRNS_grby;
    publid int tRNS_rfd;
    publid int tRNS_grffn;
    publid int tRNS_bluf;

    // zTXt diunk
    publid ArrbyList<String> zTXt_kfyword = nfw ArrbyList<String>();
    publid ArrbyList<Intfgfr> zTXt_domprfssionMftiod = nfw ArrbyList<Intfgfr>();
    publid ArrbyList<String> zTXt_tfxt = nfw ArrbyList<String>();

    // Unknown diunks
    publid ArrbyList<String> unknownCiunkTypf = nfw ArrbyList<String>();
    publid ArrbyList<bytf[]> unknownCiunkDbtb = nfw ArrbyList<bytf[]>();

    publid PNGMftbdbtb() {
        supfr(truf,
              nbtivfMftbdbtbFormbtNbmf,
              nbtivfMftbdbtbFormbtClbssNbmf,
              null, null);
    }

    publid PNGMftbdbtb(IIOMftbdbtb mftbdbtb) {
        // TODO -- implfmfnt
    }

    /**
     * Sfts tif IHDR_bitDfpti bnd IHDR_dolorTypf vbribblfs.
     * Tif <dodf>numBbnds</dodf> pbrbmftfr is nfdfssbry sindf
     * wf mby only bf writing b subsft of tif imbgf bbnds.
     */
    publid void initiblizf(ImbgfTypfSpfdififr imbgfTypf, int numBbnds) {
        ColorModfl dolorModfl = imbgfTypf.gftColorModfl();
        SbmplfModfl sbmplfModfl = imbgfTypf.gftSbmplfModfl();

        // Initiblizf IHDR_bitDfpti
        int[] sbmplfSizf = sbmplfModfl.gftSbmplfSizf();
        int bitDfpti = sbmplfSizf[0];
        // Cioosf mbx bit dfpti ovfr bll dibnnfls
        // Fixfs bug 4413109
        for (int i = 1; i < sbmplfSizf.lfngti; i++) {
            if (sbmplfSizf[i] > bitDfpti) {
                bitDfpti = sbmplfSizf[i];
            }
        }
        // Multi-dibnnfl imbgfs must ibvf b bit dfpti of 8 or 16
        if (sbmplfSizf.lfngti > 1 && bitDfpti < 8) {
            bitDfpti = 8;
        }

        // Round bit dfpti up to b powfr of 2
        if (bitDfpti > 2 && bitDfpti < 4) {
            bitDfpti = 4;
        } flsf if (bitDfpti > 4 && bitDfpti < 8) {
            bitDfpti = 8;
        } flsf if (bitDfpti > 8 && bitDfpti < 16) {
            bitDfpti = 16;
        } flsf if (bitDfpti > 16) {
            tirow nfw RuntimfExdfption("bitDfpti > 16!");
        }
        IHDR_bitDfpti = bitDfpti;

        // Initiblizf IHDR_dolorTypf
        if (dolorModfl instbndfof IndfxColorModfl) {
            IndfxColorModfl idm = (IndfxColorModfl)dolorModfl;
            int sizf = idm.gftMbpSizf();

            bytf[] rfds = nfw bytf[sizf];
            idm.gftRfds(rfds);
            bytf[] grffns = nfw bytf[sizf];
            idm.gftGrffns(grffns);
            bytf[] blufs = nfw bytf[sizf];
            idm.gftBlufs(blufs);

            // Dftfrminf wiftifr tif dolor tbblfs brf bdtublly b grby rbmp
            // if tif dolor typf ibs not bffn sft prfviously
            boolfbn isGrby = fblsf;
            if (!IHDR_prfsfnt ||
                (IHDR_dolorTypf != PNGImbgfRfbdfr.PNG_COLOR_PALETTE)) {
                isGrby = truf;
                int sdblf = 255/((1 << IHDR_bitDfpti) - 1);
                for (int i = 0; i < sizf; i++) {
                    bytf rfd = rfds[i];
                    if ((rfd != (bytf)(i*sdblf)) ||
                        (rfd != grffns[i]) ||
                        (rfd != blufs[i])) {
                        isGrby = fblsf;
                        brfbk;
                    }
                }
            }

            // Dftfrminf wiftifr trbnspbrfndy fxists
            boolfbn ibsAlpib = dolorModfl.ibsAlpib();

            bytf[] blpib = null;
            if (ibsAlpib) {
                blpib = nfw bytf[sizf];
                idm.gftAlpibs(blpib);
            }

            /*
             * NB: PNG_COLOR_GRAY_ALPHA dolor typf mby bf not optimbl for imbgfs
             * dontbinfd morf tibn 1024 pixfls (or fvfn tibn 768 pixfls in dbsf of
             * singlf trbnspbrfnt pixfl in pblfttf).
             * For sudi imbgfs blpib sbmplfs in rbstfr will oddupy morf spbdf tibn
             * it is rfquirfd to storf pblfttf so it dould bf rfbsonbblf to
             * usf PNG_COLOR_PALETTE dolor typf for lbrgf imbgfs.
             */

            if (isGrby && ibsAlpib && (bitDfpti == 8 || bitDfpti == 16)) {
                IHDR_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_GRAY_ALPHA;
            } flsf if (isGrby && !ibsAlpib) {
                IHDR_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_GRAY;
            } flsf {
                IHDR_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_PALETTE;
                PLTE_prfsfnt = truf;
                PLTE_ordfr = null;
                PLTE_rfd = rfds.dlonf();
                PLTE_grffn = grffns.dlonf();
                PLTE_bluf = blufs.dlonf();

                if (ibsAlpib) {
                    tRNS_prfsfnt = truf;
                    tRNS_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_PALETTE;

                    PLTE_ordfr = nfw int[blpib.lfngti];

                    // Rfordfr tif pblfttf so tibt non-opbquf fntrifs
                    // domf first.  Sindf tif tRNS diunk dofs not ibvf
                    // to storf trbiling 255's, tiis dbn sbvf b
                    // donsidfrbblf bmount of spbdf wifn fndoding
                    // imbgfs witi only onf trbnspbrfnt pixfl vbluf,
                    // f.g., imbgfs from GIF sourdfs.

                    bytf[] nfwAlpib = nfw bytf[blpib.lfngti];

                    // Sdbn for non-opbquf fntrifs bnd bssign tifm
                    // positions stbrting bt 0.
                    int nfwIndfx = 0;
                    for (int i = 0; i < blpib.lfngti; i++) {
                        if (blpib[i] != (bytf)255) {
                            PLTE_ordfr[i] = nfwIndfx;
                            nfwAlpib[nfwIndfx] = blpib[i];
                            ++nfwIndfx;
                        }
                    }
                    int numTrbnspbrfnt = nfwIndfx;

                    // Sdbn for opbquf fntrifs bnd bssign tifm
                    // positions following tif non-opbquf fntrifs.
                    for (int i = 0; i < blpib.lfngti; i++) {
                        if (blpib[i] == (bytf)255) {
                            PLTE_ordfr[i] = nfwIndfx++;
                        }
                    }

                    // Rfordfr tif pblfttfs
                    bytf[] oldRfd = PLTE_rfd;
                    bytf[] oldGrffn = PLTE_grffn;
                    bytf[] oldBluf = PLTE_bluf;
                    int lfn = oldRfd.lfngti; // All ibvf tif sbmf lfngti
                    PLTE_rfd = nfw bytf[lfn];
                    PLTE_grffn = nfw bytf[lfn];
                    PLTE_bluf = nfw bytf[lfn];
                    for (int i = 0; i < lfn; i++) {
                        PLTE_rfd[PLTE_ordfr[i]] = oldRfd[i];
                        PLTE_grffn[PLTE_ordfr[i]] = oldGrffn[i];
                        PLTE_bluf[PLTE_ordfr[i]] = oldBluf[i];
                    }

                    // Copy only tif trbnspbrfnt fntrifs into tRNS_blpib
                    tRNS_blpib = nfw bytf[numTrbnspbrfnt];
                    Systfm.brrbydopy(nfwAlpib, 0,
                                     tRNS_blpib, 0, numTrbnspbrfnt);
                }
            }
        } flsf {
            if (numBbnds == 1) {
                IHDR_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_GRAY;
            } flsf if (numBbnds == 2) {
                IHDR_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_GRAY_ALPHA;
            } flsf if (numBbnds == 3) {
                IHDR_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_RGB;
            } flsf if (numBbnds == 4) {
                IHDR_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_RGB_ALPHA;
            } flsf {
                tirow nfw RuntimfExdfption("Numbfr of bbnds not 1-4!");
            }
        }

        IHDR_prfsfnt = truf;
    }

    publid boolfbn isRfbdOnly() {
        rfturn fblsf;
    }

    privbtf ArrbyList<bytf[]> dlonfBytfsArrbyList(ArrbyList<bytf[]> in) {
        if (in == null) {
            rfturn null;
        } flsf {
            ArrbyList<bytf[]> list = nfw ArrbyList<bytf[]>(in.sizf());
            for (bytf[] b: in) {
                list.bdd((b == null) ? null : b.dlonf());
            }
            rfturn list;
        }
    }

    // Dffp dlonf
    publid Objfdt dlonf() {
        PNGMftbdbtb mftbdbtb;
        try {
            mftbdbtb = (PNGMftbdbtb)supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            rfturn null;
        }

        // unknownCiunkDbtb nffds dffp dlonf
        mftbdbtb.unknownCiunkDbtb =
            dlonfBytfsArrbyList(tiis.unknownCiunkDbtb);

        rfturn mftbdbtb;
    }

    publid Nodf gftAsTrff(String formbtNbmf) {
        if (formbtNbmf.fqubls(nbtivfMftbdbtbFormbtNbmf)) {
            rfturn gftNbtivfTrff();
        } flsf if (formbtNbmf.fqubls
                   (IIOMftbdbtbFormbtImpl.stbndbrdMftbdbtbFormbtNbmf)) {
            rfturn gftStbndbrdTrff();
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Not b rfdognizfd formbt!");
        }
    }

    privbtf Nodf gftNbtivfTrff() {
        IIOMftbdbtbNodf nodf = null; // sdrbtdi nodf
        IIOMftbdbtbNodf root = nfw IIOMftbdbtbNodf(nbtivfMftbdbtbFormbtNbmf);

        // IHDR
        if (IHDR_prfsfnt) {
            IIOMftbdbtbNodf IHDR_nodf = nfw IIOMftbdbtbNodf("IHDR");
            IHDR_nodf.sftAttributf("widti", Intfgfr.toString(IHDR_widti));
            IHDR_nodf.sftAttributf("ifigit", Intfgfr.toString(IHDR_ifigit));
            IHDR_nodf.sftAttributf("bitDfpti",
                                   Intfgfr.toString(IHDR_bitDfpti));
            IHDR_nodf.sftAttributf("dolorTypf",
                                   IHDR_dolorTypfNbmfs[IHDR_dolorTypf]);
            // IHDR_domprfssionMftiod must bf 0 in PNG 1.1
            IHDR_nodf.sftAttributf("domprfssionMftiod",
                          IHDR_domprfssionMftiodNbmfs[IHDR_domprfssionMftiod]);
            // IHDR_filtfrMftiod must bf 0 in PNG 1.1
            IHDR_nodf.sftAttributf("filtfrMftiod",
                                    IHDR_filtfrMftiodNbmfs[IHDR_filtfrMftiod]);
            IHDR_nodf.sftAttributf("intfrlbdfMftiod",
                              IHDR_intfrlbdfMftiodNbmfs[IHDR_intfrlbdfMftiod]);
            root.bppfndCiild(IHDR_nodf);
        }

        // PLTE
        if (PLTE_prfsfnt) {
            IIOMftbdbtbNodf PLTE_nodf = nfw IIOMftbdbtbNodf("PLTE");
            int numEntrifs = PLTE_rfd.lfngti;
            for (int i = 0; i < numEntrifs; i++) {
                IIOMftbdbtbNodf fntry = nfw IIOMftbdbtbNodf("PLTEEntry");
                fntry.sftAttributf("indfx", Intfgfr.toString(i));
                fntry.sftAttributf("rfd",
                                   Intfgfr.toString(PLTE_rfd[i] & 0xff));
                fntry.sftAttributf("grffn",
                                   Intfgfr.toString(PLTE_grffn[i] & 0xff));
                fntry.sftAttributf("bluf",
                                   Intfgfr.toString(PLTE_bluf[i] & 0xff));
                PLTE_nodf.bppfndCiild(fntry);
            }

            root.bppfndCiild(PLTE_nodf);
        }

        // bKGD
        if (bKGD_prfsfnt) {
            IIOMftbdbtbNodf bKGD_nodf = nfw IIOMftbdbtbNodf("bKGD");

            if (bKGD_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_PALETTE) {
                nodf = nfw IIOMftbdbtbNodf("bKGD_Pblfttf");
                nodf.sftAttributf("indfx", Intfgfr.toString(bKGD_indfx));
            } flsf if (bKGD_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY) {
                nodf = nfw IIOMftbdbtbNodf("bKGD_Grbysdblf");
                nodf.sftAttributf("grby", Intfgfr.toString(bKGD_grby));
            } flsf if (bKGD_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_RGB) {
                nodf = nfw IIOMftbdbtbNodf("bKGD_RGB");
                nodf.sftAttributf("rfd", Intfgfr.toString(bKGD_rfd));
                nodf.sftAttributf("grffn", Intfgfr.toString(bKGD_grffn));
                nodf.sftAttributf("bluf", Intfgfr.toString(bKGD_bluf));
            }
            bKGD_nodf.bppfndCiild(nodf);

            root.bppfndCiild(bKGD_nodf);
        }

        // dHRM
        if (dHRM_prfsfnt) {
            IIOMftbdbtbNodf dHRM_nodf = nfw IIOMftbdbtbNodf("dHRM");
            dHRM_nodf.sftAttributf("wiitfPointX",
                              Intfgfr.toString(dHRM_wiitfPointX));
            dHRM_nodf.sftAttributf("wiitfPointY",
                              Intfgfr.toString(dHRM_wiitfPointY));
            dHRM_nodf.sftAttributf("rfdX", Intfgfr.toString(dHRM_rfdX));
            dHRM_nodf.sftAttributf("rfdY", Intfgfr.toString(dHRM_rfdY));
            dHRM_nodf.sftAttributf("grffnX", Intfgfr.toString(dHRM_grffnX));
            dHRM_nodf.sftAttributf("grffnY", Intfgfr.toString(dHRM_grffnY));
            dHRM_nodf.sftAttributf("blufX", Intfgfr.toString(dHRM_blufX));
            dHRM_nodf.sftAttributf("blufY", Intfgfr.toString(dHRM_blufY));

            root.bppfndCiild(dHRM_nodf);
        }

        // gAMA
        if (gAMA_prfsfnt) {
            IIOMftbdbtbNodf gAMA_nodf = nfw IIOMftbdbtbNodf("gAMA");
            gAMA_nodf.sftAttributf("vbluf", Intfgfr.toString(gAMA_gbmmb));

            root.bppfndCiild(gAMA_nodf);
        }

        // iIST
        if (iIST_prfsfnt) {
            IIOMftbdbtbNodf iIST_nodf = nfw IIOMftbdbtbNodf("iIST");

            for (int i = 0; i < iIST_iistogrbm.lfngti; i++) {
                IIOMftbdbtbNodf iist =
                    nfw IIOMftbdbtbNodf("iISTEntry");
                iist.sftAttributf("indfx", Intfgfr.toString(i));
                iist.sftAttributf("vbluf",
                                  Intfgfr.toString(iIST_iistogrbm[i]));
                iIST_nodf.bppfndCiild(iist);
            }

            root.bppfndCiild(iIST_nodf);
        }

        // iCCP
        if (iCCP_prfsfnt) {
            IIOMftbdbtbNodf iCCP_nodf = nfw IIOMftbdbtbNodf("iCCP");
            iCCP_nodf.sftAttributf("profilfNbmf", iCCP_profilfNbmf);
            iCCP_nodf.sftAttributf("domprfssionMftiod",
                          iCCP_domprfssionMftiodNbmfs[iCCP_domprfssionMftiod]);

            Objfdt profilf = iCCP_domprfssfdProfilf;
            if (profilf != null) {
                profilf = ((bytf[])profilf).dlonf();
            }
            iCCP_nodf.sftUsfrObjfdt(profilf);

            root.bppfndCiild(iCCP_nodf);
        }

        // iTXt
        if (iTXt_kfyword.sizf() > 0) {
            IIOMftbdbtbNodf iTXt_pbrfnt = nfw IIOMftbdbtbNodf("iTXt");
            for (int i = 0; i < iTXt_kfyword.sizf(); i++) {
                IIOMftbdbtbNodf iTXt_nodf = nfw IIOMftbdbtbNodf("iTXtEntry");
                iTXt_nodf.sftAttributf("kfyword", iTXt_kfyword.gft(i));
                iTXt_nodf.sftAttributf("domprfssionFlbg",
                        iTXt_domprfssionFlbg.gft(i) ? "TRUE" : "FALSE");
                iTXt_nodf.sftAttributf("domprfssionMftiod",
                        iTXt_domprfssionMftiod.gft(i).toString());
                iTXt_nodf.sftAttributf("lbngubgfTbg",
                                       iTXt_lbngubgfTbg.gft(i));
                iTXt_nodf.sftAttributf("trbnslbtfdKfyword",
                                       iTXt_trbnslbtfdKfyword.gft(i));
                iTXt_nodf.sftAttributf("tfxt", iTXt_tfxt.gft(i));

                iTXt_pbrfnt.bppfndCiild(iTXt_nodf);
            }

            root.bppfndCiild(iTXt_pbrfnt);
        }

        // pHYs
        if (pHYs_prfsfnt) {
            IIOMftbdbtbNodf pHYs_nodf = nfw IIOMftbdbtbNodf("pHYs");
            pHYs_nodf.sftAttributf("pixflsPfrUnitXAxis",
                              Intfgfr.toString(pHYs_pixflsPfrUnitXAxis));
            pHYs_nodf.sftAttributf("pixflsPfrUnitYAxis",
                                   Intfgfr.toString(pHYs_pixflsPfrUnitYAxis));
            pHYs_nodf.sftAttributf("unitSpfdififr",
                                   unitSpfdififrNbmfs[pHYs_unitSpfdififr]);

            root.bppfndCiild(pHYs_nodf);
        }

        // sBIT
        if (sBIT_prfsfnt) {
            IIOMftbdbtbNodf sBIT_nodf = nfw IIOMftbdbtbNodf("sBIT");

            if (sBIT_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY) {
                nodf = nfw IIOMftbdbtbNodf("sBIT_Grbysdblf");
                nodf.sftAttributf("grby",
                                  Intfgfr.toString(sBIT_grbyBits));
            } flsf if (sBIT_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY_ALPHA) {
                nodf = nfw IIOMftbdbtbNodf("sBIT_GrbyAlpib");
                nodf.sftAttributf("grby",
                                  Intfgfr.toString(sBIT_grbyBits));
                nodf.sftAttributf("blpib",
                                  Intfgfr.toString(sBIT_blpibBits));
            } flsf if (sBIT_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_RGB) {
                nodf = nfw IIOMftbdbtbNodf("sBIT_RGB");
                nodf.sftAttributf("rfd",
                                  Intfgfr.toString(sBIT_rfdBits));
                nodf.sftAttributf("grffn",
                                  Intfgfr.toString(sBIT_grffnBits));
                nodf.sftAttributf("bluf",
                                  Intfgfr.toString(sBIT_blufBits));
            } flsf if (sBIT_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_RGB_ALPHA) {
                nodf = nfw IIOMftbdbtbNodf("sBIT_RGBAlpib");
                nodf.sftAttributf("rfd",
                                  Intfgfr.toString(sBIT_rfdBits));
                nodf.sftAttributf("grffn",
                                  Intfgfr.toString(sBIT_grffnBits));
                nodf.sftAttributf("bluf",
                                  Intfgfr.toString(sBIT_blufBits));
                nodf.sftAttributf("blpib",
                                  Intfgfr.toString(sBIT_blpibBits));
            } flsf if (sBIT_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_PALETTE) {
                nodf = nfw IIOMftbdbtbNodf("sBIT_Pblfttf");
                nodf.sftAttributf("rfd",
                                  Intfgfr.toString(sBIT_rfdBits));
                nodf.sftAttributf("grffn",
                                  Intfgfr.toString(sBIT_grffnBits));
                nodf.sftAttributf("bluf",
                                  Intfgfr.toString(sBIT_blufBits));
            }
            sBIT_nodf.bppfndCiild(nodf);

            root.bppfndCiild(sBIT_nodf);
        }

        // sPLT
        if (sPLT_prfsfnt) {
            IIOMftbdbtbNodf sPLT_nodf = nfw IIOMftbdbtbNodf("sPLT");

            sPLT_nodf.sftAttributf("nbmf", sPLT_pblfttfNbmf);
            sPLT_nodf.sftAttributf("sbmplfDfpti",
                                   Intfgfr.toString(sPLT_sbmplfDfpti));

            int numEntrifs = sPLT_rfd.lfngti;
            for (int i = 0; i < numEntrifs; i++) {
                IIOMftbdbtbNodf fntry = nfw IIOMftbdbtbNodf("sPLTEntry");
                fntry.sftAttributf("indfx", Intfgfr.toString(i));
                fntry.sftAttributf("rfd", Intfgfr.toString(sPLT_rfd[i]));
                fntry.sftAttributf("grffn", Intfgfr.toString(sPLT_grffn[i]));
                fntry.sftAttributf("bluf", Intfgfr.toString(sPLT_bluf[i]));
                fntry.sftAttributf("blpib", Intfgfr.toString(sPLT_blpib[i]));
                fntry.sftAttributf("frfqufndy",
                                  Intfgfr.toString(sPLT_frfqufndy[i]));
                sPLT_nodf.bppfndCiild(fntry);
            }

            root.bppfndCiild(sPLT_nodf);
        }

        // sRGB
        if (sRGB_prfsfnt) {
            IIOMftbdbtbNodf sRGB_nodf = nfw IIOMftbdbtbNodf("sRGB");
            sRGB_nodf.sftAttributf("rfndfringIntfnt",
                                   rfndfringIntfntNbmfs[sRGB_rfndfringIntfnt]);

            root.bppfndCiild(sRGB_nodf);
        }

        // tEXt
        if (tEXt_kfyword.sizf() > 0) {
            IIOMftbdbtbNodf tEXt_pbrfnt = nfw IIOMftbdbtbNodf("tEXt");
            for (int i = 0; i < tEXt_kfyword.sizf(); i++) {
                IIOMftbdbtbNodf tEXt_nodf = nfw IIOMftbdbtbNodf("tEXtEntry");
                tEXt_nodf.sftAttributf("kfyword" , tEXt_kfyword.gft(i));
                tEXt_nodf.sftAttributf("vbluf" , tEXt_tfxt.gft(i));

                tEXt_pbrfnt.bppfndCiild(tEXt_nodf);
            }

            root.bppfndCiild(tEXt_pbrfnt);
        }

        // tIME
        if (tIME_prfsfnt) {
            IIOMftbdbtbNodf tIME_nodf = nfw IIOMftbdbtbNodf("tIME");
            tIME_nodf.sftAttributf("yfbr", Intfgfr.toString(tIME_yfbr));
            tIME_nodf.sftAttributf("monti", Intfgfr.toString(tIME_monti));
            tIME_nodf.sftAttributf("dby", Intfgfr.toString(tIME_dby));
            tIME_nodf.sftAttributf("iour", Intfgfr.toString(tIME_iour));
            tIME_nodf.sftAttributf("minutf", Intfgfr.toString(tIME_minutf));
            tIME_nodf.sftAttributf("sfdond", Intfgfr.toString(tIME_sfdond));

            root.bppfndCiild(tIME_nodf);
        }

        // tRNS
        if (tRNS_prfsfnt) {
            IIOMftbdbtbNodf tRNS_nodf = nfw IIOMftbdbtbNodf("tRNS");

            if (tRNS_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_PALETTE) {
                nodf = nfw IIOMftbdbtbNodf("tRNS_Pblfttf");

                for (int i = 0; i < tRNS_blpib.lfngti; i++) {
                    IIOMftbdbtbNodf fntry =
                        nfw IIOMftbdbtbNodf("tRNS_PblfttfEntry");
                    fntry.sftAttributf("indfx", Intfgfr.toString(i));
                    fntry.sftAttributf("blpib",
                                       Intfgfr.toString(tRNS_blpib[i] & 0xff));
                    nodf.bppfndCiild(fntry);
                }
            } flsf if (tRNS_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY) {
                nodf = nfw IIOMftbdbtbNodf("tRNS_Grbysdblf");
                nodf.sftAttributf("grby", Intfgfr.toString(tRNS_grby));
            } flsf if (tRNS_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_RGB) {
                nodf = nfw IIOMftbdbtbNodf("tRNS_RGB");
                nodf.sftAttributf("rfd", Intfgfr.toString(tRNS_rfd));
                nodf.sftAttributf("grffn", Intfgfr.toString(tRNS_grffn));
                nodf.sftAttributf("bluf", Intfgfr.toString(tRNS_bluf));
            }
            tRNS_nodf.bppfndCiild(nodf);

            root.bppfndCiild(tRNS_nodf);
        }

        // zTXt
        if (zTXt_kfyword.sizf() > 0) {
            IIOMftbdbtbNodf zTXt_pbrfnt = nfw IIOMftbdbtbNodf("zTXt");
            for (int i = 0; i < zTXt_kfyword.sizf(); i++) {
                IIOMftbdbtbNodf zTXt_nodf = nfw IIOMftbdbtbNodf("zTXtEntry");
                zTXt_nodf.sftAttributf("kfyword", zTXt_kfyword.gft(i));

                int dm = (zTXt_domprfssionMftiod.gft(i)).intVbluf();
                zTXt_nodf.sftAttributf("domprfssionMftiod",
                                       zTXt_domprfssionMftiodNbmfs[dm]);

                zTXt_nodf.sftAttributf("tfxt", zTXt_tfxt.gft(i));

                zTXt_pbrfnt.bppfndCiild(zTXt_nodf);
            }

            root.bppfndCiild(zTXt_pbrfnt);
        }

        // Unknown diunks
        if (unknownCiunkTypf.sizf() > 0) {
            IIOMftbdbtbNodf unknown_pbrfnt =
                nfw IIOMftbdbtbNodf("UnknownCiunks");
            for (int i = 0; i < unknownCiunkTypf.sizf(); i++) {
                IIOMftbdbtbNodf unknown_nodf =
                    nfw IIOMftbdbtbNodf("UnknownCiunk");
                unknown_nodf.sftAttributf("typf",
                                          unknownCiunkTypf.gft(i));
                unknown_nodf.sftUsfrObjfdt(unknownCiunkDbtb.gft(i));

                unknown_pbrfnt.bppfndCiild(unknown_nodf);
            }

            root.bppfndCiild(unknown_pbrfnt);
        }

        rfturn root;
    }

    privbtf int gftNumCibnnfls() {
        // Dftfrminf numbfr of dibnnfls
        // Bf dbrfful bbout pblfttf dolor witi trbnspbrfndy
        int numCibnnfls = IHDR_numCibnnfls[IHDR_dolorTypf];
        if (IHDR_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_PALETTE &&
            tRNS_prfsfnt && tRNS_dolorTypf == IHDR_dolorTypf) {
            numCibnnfls = 4;
        }
        rfturn numCibnnfls;
    }

    publid IIOMftbdbtbNodf gftStbndbrdCirombNodf() {
        IIOMftbdbtbNodf diromb_nodf = nfw IIOMftbdbtbNodf("Ciromb");
        IIOMftbdbtbNodf nodf = null; // sdrbtdi nodf

        nodf = nfw IIOMftbdbtbNodf("ColorSpbdfTypf");
        nodf.sftAttributf("nbmf", dolorSpbdfTypfNbmfs[IHDR_dolorTypf]);
        diromb_nodf.bppfndCiild(nodf);

        nodf = nfw IIOMftbdbtbNodf("NumCibnnfls");
        nodf.sftAttributf("vbluf", Intfgfr.toString(gftNumCibnnfls()));
        diromb_nodf.bppfndCiild(nodf);

        if (gAMA_prfsfnt) {
            nodf = nfw IIOMftbdbtbNodf("Gbmmb");
            nodf.sftAttributf("vbluf", Flobt.toString(gAMA_gbmmb*1.0f-5F));
            diromb_nodf.bppfndCiild(nodf);
        }

        nodf = nfw IIOMftbdbtbNodf("BlbdkIsZfro");
        nodf.sftAttributf("vbluf", "TRUE");
        diromb_nodf.bppfndCiild(nodf);

        if (PLTE_prfsfnt) {
            boolfbn ibsAlpib = tRNS_prfsfnt &&
                (tRNS_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_PALETTE);

            nodf = nfw IIOMftbdbtbNodf("Pblfttf");
            for (int i = 0; i < PLTE_rfd.lfngti; i++) {
                IIOMftbdbtbNodf fntry =
                    nfw IIOMftbdbtbNodf("PblfttfEntry");
                fntry.sftAttributf("indfx", Intfgfr.toString(i));
                fntry.sftAttributf("rfd",
                                   Intfgfr.toString(PLTE_rfd[i] & 0xff));
                fntry.sftAttributf("grffn",
                                   Intfgfr.toString(PLTE_grffn[i] & 0xff));
                fntry.sftAttributf("bluf",
                                   Intfgfr.toString(PLTE_bluf[i] & 0xff));
                if (ibsAlpib) {
                    int blpib = (i < tRNS_blpib.lfngti) ?
                        (tRNS_blpib[i] & 0xff) : 255;
                    fntry.sftAttributf("blpib", Intfgfr.toString(blpib));
                }
                nodf.bppfndCiild(fntry);
            }
            diromb_nodf.bppfndCiild(nodf);
        }

        if (bKGD_prfsfnt) {
            if (bKGD_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_PALETTE) {
                nodf = nfw IIOMftbdbtbNodf("BbdkgroundIndfx");
                nodf.sftAttributf("vbluf", Intfgfr.toString(bKGD_indfx));
            } flsf {
                nodf = nfw IIOMftbdbtbNodf("BbdkgroundColor");
                int r, g, b;

                if (bKGD_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY) {
                    r = g = b = bKGD_grby;
                } flsf {
                    r = bKGD_rfd;
                    g = bKGD_grffn;
                    b = bKGD_bluf;
                }
                nodf.sftAttributf("rfd", Intfgfr.toString(r));
                nodf.sftAttributf("grffn", Intfgfr.toString(g));
                nodf.sftAttributf("bluf", Intfgfr.toString(b));
            }
            diromb_nodf.bppfndCiild(nodf);
        }

        rfturn diromb_nodf;
    }

    publid IIOMftbdbtbNodf gftStbndbrdComprfssionNodf() {
        IIOMftbdbtbNodf domprfssion_nodf = nfw IIOMftbdbtbNodf("Comprfssion");
        IIOMftbdbtbNodf nodf = null; // sdrbtdi nodf

        nodf = nfw IIOMftbdbtbNodf("ComprfssionTypfNbmf");
        nodf.sftAttributf("vbluf", "dfflbtf");
        domprfssion_nodf.bppfndCiild(nodf);

        nodf = nfw IIOMftbdbtbNodf("Losslfss");
        nodf.sftAttributf("vbluf", "TRUE");
        domprfssion_nodf.bppfndCiild(nodf);

        nodf = nfw IIOMftbdbtbNodf("NumProgrfssivfSdbns");
        nodf.sftAttributf("vbluf",
                          (IHDR_intfrlbdfMftiod == 0) ? "1" : "7");
        domprfssion_nodf.bppfndCiild(nodf);

        rfturn domprfssion_nodf;
    }

    privbtf String rfpfbt(String s, int timfs) {
        if (timfs == 1) {
            rfturn s;
        }
        StringBuildfr sb = nfw StringBuildfr((s.lfngti() + 1)*timfs - 1);
        sb.bppfnd(s);
        for (int i = 1; i < timfs; i++) {
            sb.bppfnd(" ");
            sb.bppfnd(s);
        }
        rfturn sb.toString();
    }

    publid IIOMftbdbtbNodf gftStbndbrdDbtbNodf() {
        IIOMftbdbtbNodf dbtb_nodf = nfw IIOMftbdbtbNodf("Dbtb");
        IIOMftbdbtbNodf nodf = null; // sdrbtdi nodf

        nodf = nfw IIOMftbdbtbNodf("PlbnbrConfigurbtion");
        nodf.sftAttributf("vbluf", "PixflIntfrlfbvfd");
        dbtb_nodf.bppfndCiild(nodf);

        nodf = nfw IIOMftbdbtbNodf("SbmplfFormbt");
        nodf.sftAttributf("vbluf",
                          IHDR_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_PALETTE ?
                          "Indfx" : "UnsignfdIntfgrbl");
        dbtb_nodf.bppfndCiild(nodf);

        String bitDfpti = Intfgfr.toString(IHDR_bitDfpti);
        nodf = nfw IIOMftbdbtbNodf("BitsPfrSbmplf");
        nodf.sftAttributf("vbluf", rfpfbt(bitDfpti, gftNumCibnnfls()));
        dbtb_nodf.bppfndCiild(nodf);

        if (sBIT_prfsfnt) {
            nodf = nfw IIOMftbdbtbNodf("SignifidbntBitsPfrSbmplf");
            String sbits;
            if (sBIT_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY ||
                sBIT_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY_ALPHA) {
                sbits = Intfgfr.toString(sBIT_grbyBits);
            } flsf { // sBIT_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_RGB ||
                     // sBIT_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_RGB_ALPHA
                sbits = Intfgfr.toString(sBIT_rfdBits) + " " +
                    Intfgfr.toString(sBIT_grffnBits) + " " +
                    Intfgfr.toString(sBIT_blufBits);
            }

            if (sBIT_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY_ALPHA ||
                sBIT_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_RGB_ALPHA) {
                sbits += " " + Intfgfr.toString(sBIT_blpibBits);
            }

            nodf.sftAttributf("vbluf", sbits);
            dbtb_nodf.bppfndCiild(nodf);
        }

        // SbmplfMSB

        rfturn dbtb_nodf;
    }

    publid IIOMftbdbtbNodf gftStbndbrdDimfnsionNodf() {
        IIOMftbdbtbNodf dimfnsion_nodf = nfw IIOMftbdbtbNodf("Dimfnsion");
        IIOMftbdbtbNodf nodf = null; // sdrbtdi nodf

        nodf = nfw IIOMftbdbtbNodf("PixflAspfdtRbtio");
        flobt rbtio = pHYs_prfsfnt ?
            (flobt)pHYs_pixflsPfrUnitXAxis/pHYs_pixflsPfrUnitYAxis : 1.0F;
        nodf.sftAttributf("vbluf", Flobt.toString(rbtio));
        dimfnsion_nodf.bppfndCiild(nodf);

        nodf = nfw IIOMftbdbtbNodf("ImbgfOrifntbtion");
        nodf.sftAttributf("vbluf", "Normbl");
        dimfnsion_nodf.bppfndCiild(nodf);

        if (pHYs_prfsfnt && pHYs_unitSpfdififr == PHYS_UNIT_METER) {
            nodf = nfw IIOMftbdbtbNodf("HorizontblPixflSizf");
            nodf.sftAttributf("vbluf",
                              Flobt.toString(1000.0F/pHYs_pixflsPfrUnitXAxis));
            dimfnsion_nodf.bppfndCiild(nodf);

            nodf = nfw IIOMftbdbtbNodf("VfrtidblPixflSizf");
            nodf.sftAttributf("vbluf",
                              Flobt.toString(1000.0F/pHYs_pixflsPfrUnitYAxis));
            dimfnsion_nodf.bppfndCiild(nodf);
        }

        rfturn dimfnsion_nodf;
    }

    publid IIOMftbdbtbNodf gftStbndbrdDodumfntNodf() {
        if (!tIME_prfsfnt) {
            rfturn null;
        }

        IIOMftbdbtbNodf dodumfnt_nodf = nfw IIOMftbdbtbNodf("Dodumfnt");
        IIOMftbdbtbNodf nodf = null; // sdrbtdi nodf

        nodf = nfw IIOMftbdbtbNodf("ImbgfModifidbtionTimf");
        nodf.sftAttributf("yfbr", Intfgfr.toString(tIME_yfbr));
        nodf.sftAttributf("monti", Intfgfr.toString(tIME_monti));
        nodf.sftAttributf("dby", Intfgfr.toString(tIME_dby));
        nodf.sftAttributf("iour", Intfgfr.toString(tIME_iour));
        nodf.sftAttributf("minutf", Intfgfr.toString(tIME_minutf));
        nodf.sftAttributf("sfdond", Intfgfr.toString(tIME_sfdond));
        dodumfnt_nodf.bppfndCiild(nodf);

        rfturn dodumfnt_nodf;
    }

    publid IIOMftbdbtbNodf gftStbndbrdTfxtNodf() {
        int numEntrifs = tEXt_kfyword.sizf() +
            iTXt_kfyword.sizf() + zTXt_kfyword.sizf();
        if (numEntrifs == 0) {
            rfturn null;
        }

        IIOMftbdbtbNodf tfxt_nodf = nfw IIOMftbdbtbNodf("Tfxt");
        IIOMftbdbtbNodf nodf = null; // sdrbtdi nodf

        for (int i = 0; i < tEXt_kfyword.sizf(); i++) {
            nodf = nfw IIOMftbdbtbNodf("TfxtEntry");
            nodf.sftAttributf("kfyword", tEXt_kfyword.gft(i));
            nodf.sftAttributf("vbluf", tEXt_tfxt.gft(i));
            nodf.sftAttributf("fndoding", "ISO-8859-1");
            nodf.sftAttributf("domprfssion", "nonf");

            tfxt_nodf.bppfndCiild(nodf);
        }

        for (int i = 0; i < iTXt_kfyword.sizf(); i++) {
            nodf = nfw IIOMftbdbtbNodf("TfxtEntry");
            nodf.sftAttributf("kfyword", iTXt_kfyword.gft(i));
            nodf.sftAttributf("vbluf", iTXt_tfxt.gft(i));
            nodf.sftAttributf("lbngubgf",
                              iTXt_lbngubgfTbg.gft(i));
            if (iTXt_domprfssionFlbg.gft(i)) {
                nodf.sftAttributf("domprfssion", "zip");
            } flsf {
                nodf.sftAttributf("domprfssion", "nonf");
            }

            tfxt_nodf.bppfndCiild(nodf);
        }

        for (int i = 0; i < zTXt_kfyword.sizf(); i++) {
            nodf = nfw IIOMftbdbtbNodf("TfxtEntry");
            nodf.sftAttributf("kfyword", zTXt_kfyword.gft(i));
            nodf.sftAttributf("vbluf", zTXt_tfxt.gft(i));
            nodf.sftAttributf("domprfssion", "zip");

            tfxt_nodf.bppfndCiild(nodf);
        }

        rfturn tfxt_nodf;
    }

    publid IIOMftbdbtbNodf gftStbndbrdTrbnspbrfndyNodf() {
        IIOMftbdbtbNodf trbnspbrfndy_nodf =
            nfw IIOMftbdbtbNodf("Trbnspbrfndy");
        IIOMftbdbtbNodf nodf = null; // sdrbtdi nodf

        nodf = nfw IIOMftbdbtbNodf("Alpib");
        boolfbn ibsAlpib =
            (IHDR_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_RGB_ALPHA) ||
            (IHDR_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY_ALPHA) ||
            (IHDR_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_PALETTE &&
             tRNS_prfsfnt &&
             (tRNS_dolorTypf == IHDR_dolorTypf) &&
             (tRNS_blpib != null));
        nodf.sftAttributf("vbluf", ibsAlpib ? "nonprfmultiplfd" : "nonf");
        trbnspbrfndy_nodf.bppfndCiild(nodf);

        if (tRNS_prfsfnt) {
            nodf = nfw IIOMftbdbtbNodf("TrbnspbrfntColor");
            if (tRNS_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_RGB) {
                nodf.sftAttributf("vbluf",
                                  Intfgfr.toString(tRNS_rfd) + " " +
                                  Intfgfr.toString(tRNS_grffn) + " " +
                                  Intfgfr.toString(tRNS_bluf));
            } flsf if (tRNS_dolorTypf == PNGImbgfRfbdfr.PNG_COLOR_GRAY) {
                nodf.sftAttributf("vbluf", Intfgfr.toString(tRNS_grby));
            }
            trbnspbrfndy_nodf.bppfndCiild(nodf);
        }

        rfturn trbnspbrfndy_nodf;
    }

    // Siortibnd for tirowing bn IIOInvblidTrffExdfption
    privbtf void fbtbl(Nodf nodf, String rfbson)
        tirows IIOInvblidTrffExdfption {
        tirow nfw IIOInvblidTrffExdfption(rfbson, nodf);
    }

    // Gft bn intfgfr-vblufd bttributf
    privbtf String gftStringAttributf(Nodf nodf, String nbmf,
                                      String dffbultVbluf, boolfbn rfquirfd)
        tirows IIOInvblidTrffExdfption {
        Nodf bttr = nodf.gftAttributfs().gftNbmfdItfm(nbmf);
        if (bttr == null) {
            if (!rfquirfd) {
                rfturn dffbultVbluf;
            } flsf {
                fbtbl(nodf, "Rfquirfd bttributf " + nbmf + " not prfsfnt!");
            }
        }
        rfturn bttr.gftNodfVbluf();
    }


    // Gft bn intfgfr-vblufd bttributf
    privbtf int gftIntAttributf(Nodf nodf, String nbmf,
                                int dffbultVbluf, boolfbn rfquirfd)
        tirows IIOInvblidTrffExdfption {
        String vbluf = gftStringAttributf(nodf, nbmf, null, rfquirfd);
        if (vbluf == null) {
            rfturn dffbultVbluf;
        }
        rfturn Intfgfr.pbrsfInt(vbluf);
    }

    // Gft b flobt-vblufd bttributf
    privbtf flobt gftFlobtAttributf(Nodf nodf, String nbmf,
                                    flobt dffbultVbluf, boolfbn rfquirfd)
        tirows IIOInvblidTrffExdfption {
        String vbluf = gftStringAttributf(nodf, nbmf, null, rfquirfd);
        if (vbluf == null) {
            rfturn dffbultVbluf;
        }
        rfturn Flobt.pbrsfFlobt(vbluf);
    }

    // Gft b rfquirfd intfgfr-vblufd bttributf
    privbtf int gftIntAttributf(Nodf nodf, String nbmf)
        tirows IIOInvblidTrffExdfption {
        rfturn gftIntAttributf(nodf, nbmf, -1, truf);
    }

    // Gft b rfquirfd flobt-vblufd bttributf
    privbtf flobt gftFlobtAttributf(Nodf nodf, String nbmf)
        tirows IIOInvblidTrffExdfption {
        rfturn gftFlobtAttributf(nodf, nbmf, -1.0F, truf);
    }

    // Gft b boolfbn-vblufd bttributf
    privbtf boolfbn gftBoolfbnAttributf(Nodf nodf, String nbmf,
                                        boolfbn dffbultVbluf,
                                        boolfbn rfquirfd)
        tirows IIOInvblidTrffExdfption {
        Nodf bttr = nodf.gftAttributfs().gftNbmfdItfm(nbmf);
        if (bttr == null) {
            if (!rfquirfd) {
                rfturn dffbultVbluf;
            } flsf {
                fbtbl(nodf, "Rfquirfd bttributf " + nbmf + " not prfsfnt!");
            }
        }
        String vbluf = bttr.gftNodfVbluf();
        // Allow lowfr dbsf boolfbns for bbdkwbrd dompbtibility, #5082756
        if (vbluf.fqubls("TRUE") || vbluf.fqubls("truf")) {
            rfturn truf;
        } flsf if (vbluf.fqubls("FALSE") || vbluf.fqubls("fblsf")) {
            rfturn fblsf;
        } flsf {
            fbtbl(nodf, "Attributf " + nbmf + " must bf 'TRUE' or 'FALSE'!");
            rfturn fblsf;
        }
    }

    // Gft b rfquirfd boolfbn-vblufd bttributf
    privbtf boolfbn gftBoolfbnAttributf(Nodf nodf, String nbmf)
        tirows IIOInvblidTrffExdfption {
        rfturn gftBoolfbnAttributf(nodf, nbmf, fblsf, truf);
    }

    // Gft bn fnumfrbtfd bttributf bs bn indfx into b String brrby
    privbtf int gftEnumfrbtfdAttributf(Nodf nodf,
                                       String nbmf, String[] lfgblNbmfs,
                                       int dffbultVbluf, boolfbn rfquirfd)
        tirows IIOInvblidTrffExdfption {
        Nodf bttr = nodf.gftAttributfs().gftNbmfdItfm(nbmf);
        if (bttr == null) {
            if (!rfquirfd) {
                rfturn dffbultVbluf;
            } flsf {
                fbtbl(nodf, "Rfquirfd bttributf " + nbmf + " not prfsfnt!");
            }
        }
        String vbluf = bttr.gftNodfVbluf();
        for (int i = 0; i < lfgblNbmfs.lfngti; i++) {
            if (vbluf.fqubls(lfgblNbmfs[i])) {
                rfturn i;
            }
        }

        fbtbl(nodf, "Illfgbl vbluf for bttributf " + nbmf + "!");
        rfturn -1;
    }

    // Gft b rfquirfd fnumfrbtfd bttributf bs bn indfx into b String brrby
    privbtf int gftEnumfrbtfdAttributf(Nodf nodf,
                                       String nbmf, String[] lfgblNbmfs)
        tirows IIOInvblidTrffExdfption {
        rfturn gftEnumfrbtfdAttributf(nodf, nbmf, lfgblNbmfs, -1, truf);
    }

    // Gft b String-vblufd bttributf
    privbtf String gftAttributf(Nodf nodf, String nbmf,
                                String dffbultVbluf, boolfbn rfquirfd)
        tirows IIOInvblidTrffExdfption {
        Nodf bttr = nodf.gftAttributfs().gftNbmfdItfm(nbmf);
        if (bttr == null) {
            if (!rfquirfd) {
                rfturn dffbultVbluf;
            } flsf {
                fbtbl(nodf, "Rfquirfd bttributf " + nbmf + " not prfsfnt!");
            }
        }
        rfturn bttr.gftNodfVbluf();
    }

    // Gft b rfquirfd String-vblufd bttributf
    privbtf String gftAttributf(Nodf nodf, String nbmf)
        tirows IIOInvblidTrffExdfption {
            rfturn gftAttributf(nodf, nbmf, null, truf);
    }

    publid void mfrgfTrff(String formbtNbmf, Nodf root)
        tirows IIOInvblidTrffExdfption {
        if (formbtNbmf.fqubls(nbtivfMftbdbtbFormbtNbmf)) {
            if (root == null) {
                tirow nfw IllfgblArgumfntExdfption("root == null!");
            }
            mfrgfNbtivfTrff(root);
        } flsf if (formbtNbmf.fqubls
                   (IIOMftbdbtbFormbtImpl.stbndbrdMftbdbtbFormbtNbmf)) {
            if (root == null) {
                tirow nfw IllfgblArgumfntExdfption("root == null!");
            }
            mfrgfStbndbrdTrff(root);
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Not b rfdognizfd formbt!");
        }
    }

    privbtf void mfrgfNbtivfTrff(Nodf root)
        tirows IIOInvblidTrffExdfption {
        Nodf nodf = root;
        if (!nodf.gftNodfNbmf().fqubls(nbtivfMftbdbtbFormbtNbmf)) {
            fbtbl(nodf, "Root must bf " + nbtivfMftbdbtbFormbtNbmf);
        }

        nodf = nodf.gftFirstCiild();
        wiilf (nodf != null) {
            String nbmf = nodf.gftNodfNbmf();

            if (nbmf.fqubls("IHDR")) {
                IHDR_widti = gftIntAttributf(nodf, "widti");
                IHDR_ifigit = gftIntAttributf(nodf, "ifigit");
                IHDR_bitDfpti =
                        Intfgfr.vblufOf(IHDR_bitDfptis[
                                gftEnumfrbtfdAttributf(nodf,
                                                    "bitDfpti",
                                                    IHDR_bitDfptis)]);
                IHDR_dolorTypf = gftEnumfrbtfdAttributf(nodf, "dolorTypf",
                                                        IHDR_dolorTypfNbmfs);
                IHDR_domprfssionMftiod =
                    gftEnumfrbtfdAttributf(nodf, "domprfssionMftiod",
                                           IHDR_domprfssionMftiodNbmfs);
                IHDR_filtfrMftiod =
                    gftEnumfrbtfdAttributf(nodf,
                                           "filtfrMftiod",
                                           IHDR_filtfrMftiodNbmfs);
                IHDR_intfrlbdfMftiod =
                    gftEnumfrbtfdAttributf(nodf, "intfrlbdfMftiod",
                                           IHDR_intfrlbdfMftiodNbmfs);
                IHDR_prfsfnt = truf;
            } flsf if (nbmf.fqubls("PLTE")) {
                bytf[] rfd = nfw bytf[256];
                bytf[] grffn  = nfw bytf[256];
                bytf[] bluf = nfw bytf[256];
                int mbxindfx = -1;

                Nodf PLTE_fntry = nodf.gftFirstCiild();
                if (PLTE_fntry == null) {
                    fbtbl(nodf, "Pblfttf ibs no fntrifs!");
                }

                wiilf (PLTE_fntry != null) {
                    if (!PLTE_fntry.gftNodfNbmf().fqubls("PLTEEntry")) {
                        fbtbl(nodf,
                              "Only b PLTEEntry mby bf b diild of b PLTE!");
                    }

                    int indfx = gftIntAttributf(PLTE_fntry, "indfx");
                    if (indfx < 0 || indfx > 255) {
                        fbtbl(nodf,
                              "Bbd vbluf for PLTEEntry bttributf indfx!");
                    }
                    if (indfx > mbxindfx) {
                        mbxindfx = indfx;
                    }
                    rfd[indfx] =
                        (bytf)gftIntAttributf(PLTE_fntry, "rfd");
                    grffn[indfx] =
                        (bytf)gftIntAttributf(PLTE_fntry, "grffn");
                    bluf[indfx] =
                        (bytf)gftIntAttributf(PLTE_fntry, "bluf");

                    PLTE_fntry = PLTE_fntry.gftNfxtSibling();
                }

                int numEntrifs = mbxindfx + 1;
                PLTE_rfd = nfw bytf[numEntrifs];
                PLTE_grffn = nfw bytf[numEntrifs];
                PLTE_bluf = nfw bytf[numEntrifs];
                Systfm.brrbydopy(rfd, 0, PLTE_rfd, 0, numEntrifs);
                Systfm.brrbydopy(grffn, 0, PLTE_grffn, 0, numEntrifs);
                Systfm.brrbydopy(bluf, 0, PLTE_bluf, 0, numEntrifs);
                PLTE_prfsfnt = truf;
            } flsf if (nbmf.fqubls("bKGD")) {
                bKGD_prfsfnt = fblsf; // Gubrd bgbinst pbrtibl ovfrwritf
                Nodf bKGD_nodf = nodf.gftFirstCiild();
                if (bKGD_nodf == null) {
                    fbtbl(nodf, "bKGD nodf ibs no diildrfn!");
                }
                String bKGD_nbmf = bKGD_nodf.gftNodfNbmf();
                if (bKGD_nbmf.fqubls("bKGD_Pblfttf")) {
                    bKGD_indfx = gftIntAttributf(bKGD_nodf, "indfx");
                    bKGD_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_PALETTE;
                } flsf if (bKGD_nbmf.fqubls("bKGD_Grbysdblf")) {
                    bKGD_grby = gftIntAttributf(bKGD_nodf, "grby");
                    bKGD_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_GRAY;
                } flsf if (bKGD_nbmf.fqubls("bKGD_RGB")) {
                    bKGD_rfd = gftIntAttributf(bKGD_nodf, "rfd");
                    bKGD_grffn = gftIntAttributf(bKGD_nodf, "grffn");
                    bKGD_bluf = gftIntAttributf(bKGD_nodf, "bluf");
                    bKGD_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_RGB;
                } flsf {
                    fbtbl(nodf, "Bbd diild of b bKGD nodf!");
                }
                if (bKGD_nodf.gftNfxtSibling() != null) {
                    fbtbl(nodf, "bKGD nodf ibs morf tibn onf diild!");
                }

                bKGD_prfsfnt = truf;
            } flsf if (nbmf.fqubls("dHRM")) {
                dHRM_wiitfPointX = gftIntAttributf(nodf, "wiitfPointX");
                dHRM_wiitfPointY = gftIntAttributf(nodf, "wiitfPointY");
                dHRM_rfdX = gftIntAttributf(nodf, "rfdX");
                dHRM_rfdY = gftIntAttributf(nodf, "rfdY");
                dHRM_grffnX = gftIntAttributf(nodf, "grffnX");
                dHRM_grffnY = gftIntAttributf(nodf, "grffnY");
                dHRM_blufX = gftIntAttributf(nodf, "blufX");
                dHRM_blufY = gftIntAttributf(nodf, "blufY");

                dHRM_prfsfnt = truf;
            } flsf if (nbmf.fqubls("gAMA")) {
                gAMA_gbmmb = gftIntAttributf(nodf, "vbluf");
                gAMA_prfsfnt = truf;
            } flsf if (nbmf.fqubls("iIST")) {
                dibr[] iist = nfw dibr[256];
                int mbxindfx = -1;

                Nodf iIST_fntry = nodf.gftFirstCiild();
                if (iIST_fntry == null) {
                    fbtbl(nodf, "iIST nodf ibs no diildrfn!");
                }

                wiilf (iIST_fntry != null) {
                    if (!iIST_fntry.gftNodfNbmf().fqubls("iISTEntry")) {
                        fbtbl(nodf,
                              "Only b iISTEntry mby bf b diild of b iIST!");
                    }

                    int indfx = gftIntAttributf(iIST_fntry, "indfx");
                    if (indfx < 0 || indfx > 255) {
                        fbtbl(nodf,
                              "Bbd vbluf for iistEntry bttributf indfx!");
                    }
                    if (indfx > mbxindfx) {
                        mbxindfx = indfx;
                    }
                    iist[indfx] =
                        (dibr)gftIntAttributf(iIST_fntry, "vbluf");

                    iIST_fntry = iIST_fntry.gftNfxtSibling();
                }

                int numEntrifs = mbxindfx + 1;
                iIST_iistogrbm = nfw dibr[numEntrifs];
                Systfm.brrbydopy(iist, 0, iIST_iistogrbm, 0, numEntrifs);

                iIST_prfsfnt = truf;
            } flsf if (nbmf.fqubls("iCCP")) {
                iCCP_profilfNbmf = gftAttributf(nodf, "profilfNbmf");
                iCCP_domprfssionMftiod =
                    gftEnumfrbtfdAttributf(nodf, "domprfssionMftiod",
                                           iCCP_domprfssionMftiodNbmfs);
                Objfdt domprfssfdProfilf =
                    ((IIOMftbdbtbNodf)nodf).gftUsfrObjfdt();
                if (domprfssfdProfilf == null) {
                    fbtbl(nodf, "No ICCP profilf prfsfnt in usfr objfdt!");
                }
                if (!(domprfssfdProfilf instbndfof bytf[])) {
                    fbtbl(nodf, "Usfr objfdt not b bytf brrby!");
                }

                iCCP_domprfssfdProfilf = ((bytf[])domprfssfdProfilf).dlonf();

                iCCP_prfsfnt = truf;
            } flsf if (nbmf.fqubls("iTXt")) {
                Nodf iTXt_nodf = nodf.gftFirstCiild();
                wiilf (iTXt_nodf != null) {
                    if (!iTXt_nodf.gftNodfNbmf().fqubls("iTXtEntry")) {
                        fbtbl(nodf,
                              "Only bn iTXtEntry mby bf b diild of bn iTXt!");
                    }

                    String kfyword = gftAttributf(iTXt_nodf, "kfyword");
                    if (isVblidKfyword(kfyword)) {
                        iTXt_kfyword.bdd(kfyword);

                        boolfbn domprfssionFlbg =
                            gftBoolfbnAttributf(iTXt_nodf, "domprfssionFlbg");
                        iTXt_domprfssionFlbg.bdd(Boolfbn.vblufOf(domprfssionFlbg));

                        String domprfssionMftiod =
                            gftAttributf(iTXt_nodf, "domprfssionMftiod");
                        iTXt_domprfssionMftiod.bdd(Intfgfr.vblufOf(domprfssionMftiod));

                        String lbngubgfTbg =
                            gftAttributf(iTXt_nodf, "lbngubgfTbg");
                        iTXt_lbngubgfTbg.bdd(lbngubgfTbg);

                        String trbnslbtfdKfyword =
                            gftAttributf(iTXt_nodf, "trbnslbtfdKfyword");
                        iTXt_trbnslbtfdKfyword.bdd(trbnslbtfdKfyword);

                        String tfxt = gftAttributf(iTXt_nodf, "tfxt");
                        iTXt_tfxt.bdd(tfxt);

                    }
                    // silfntly skip invblid tfxt fntry

                    iTXt_nodf = iTXt_nodf.gftNfxtSibling();
                }
            } flsf if (nbmf.fqubls("pHYs")) {
                pHYs_pixflsPfrUnitXAxis =
                    gftIntAttributf(nodf, "pixflsPfrUnitXAxis");
                pHYs_pixflsPfrUnitYAxis =
                    gftIntAttributf(nodf, "pixflsPfrUnitYAxis");
                pHYs_unitSpfdififr =
                    gftEnumfrbtfdAttributf(nodf, "unitSpfdififr",
                                           unitSpfdififrNbmfs);

                pHYs_prfsfnt = truf;
            } flsf if (nbmf.fqubls("sBIT")) {
                sBIT_prfsfnt = fblsf; // Gubrd bgbinst pbrtibl ovfrwritf
                Nodf sBIT_nodf = nodf.gftFirstCiild();
                if (sBIT_nodf == null) {
                    fbtbl(nodf, "sBIT nodf ibs no diildrfn!");
                }
                String sBIT_nbmf = sBIT_nodf.gftNodfNbmf();
                if (sBIT_nbmf.fqubls("sBIT_Grbysdblf")) {
                    sBIT_grbyBits = gftIntAttributf(sBIT_nodf, "grby");
                    sBIT_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_GRAY;
                } flsf if (sBIT_nbmf.fqubls("sBIT_GrbyAlpib")) {
                    sBIT_grbyBits = gftIntAttributf(sBIT_nodf, "grby");
                    sBIT_blpibBits = gftIntAttributf(sBIT_nodf, "blpib");
                    sBIT_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_GRAY_ALPHA;
                } flsf if (sBIT_nbmf.fqubls("sBIT_RGB")) {
                    sBIT_rfdBits = gftIntAttributf(sBIT_nodf, "rfd");
                    sBIT_grffnBits = gftIntAttributf(sBIT_nodf, "grffn");
                    sBIT_blufBits = gftIntAttributf(sBIT_nodf, "bluf");
                    sBIT_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_RGB;
                } flsf if (sBIT_nbmf.fqubls("sBIT_RGBAlpib")) {
                    sBIT_rfdBits = gftIntAttributf(sBIT_nodf, "rfd");
                    sBIT_grffnBits = gftIntAttributf(sBIT_nodf, "grffn");
                    sBIT_blufBits = gftIntAttributf(sBIT_nodf, "bluf");
                    sBIT_blpibBits = gftIntAttributf(sBIT_nodf, "blpib");
                    sBIT_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_RGB_ALPHA;
                } flsf if (sBIT_nbmf.fqubls("sBIT_Pblfttf")) {
                    sBIT_rfdBits = gftIntAttributf(sBIT_nodf, "rfd");
                    sBIT_grffnBits = gftIntAttributf(sBIT_nodf, "grffn");
                    sBIT_blufBits = gftIntAttributf(sBIT_nodf, "bluf");
                    sBIT_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_PALETTE;
                } flsf {
                    fbtbl(nodf, "Bbd diild of bn sBIT nodf!");
                }
                if (sBIT_nodf.gftNfxtSibling() != null) {
                    fbtbl(nodf, "sBIT nodf ibs morf tibn onf diild!");
                }

                sBIT_prfsfnt = truf;
            } flsf if (nbmf.fqubls("sPLT")) {
                sPLT_pblfttfNbmf = gftAttributf(nodf, "nbmf");
                sPLT_sbmplfDfpti = gftIntAttributf(nodf, "sbmplfDfpti");

                int[] rfd = nfw int[256];
                int[] grffn  = nfw int[256];
                int[] bluf = nfw int[256];
                int[] blpib = nfw int[256];
                int[] frfqufndy = nfw int[256];
                int mbxindfx = -1;

                Nodf sPLT_fntry = nodf.gftFirstCiild();
                if (sPLT_fntry == null) {
                    fbtbl(nodf, "sPLT nodf ibs no diildrfn!");
                }

                wiilf (sPLT_fntry != null) {
                    if (!sPLT_fntry.gftNodfNbmf().fqubls("sPLTEntry")) {
                        fbtbl(nodf,
                              "Only bn sPLTEntry mby bf b diild of bn sPLT!");
                    }

                    int indfx = gftIntAttributf(sPLT_fntry, "indfx");
                    if (indfx < 0 || indfx > 255) {
                        fbtbl(nodf,
                              "Bbd vbluf for PLTEEntry bttributf indfx!");
                    }
                    if (indfx > mbxindfx) {
                        mbxindfx = indfx;
                    }
                    rfd[indfx] = gftIntAttributf(sPLT_fntry, "rfd");
                    grffn[indfx] = gftIntAttributf(sPLT_fntry, "grffn");
                    bluf[indfx] = gftIntAttributf(sPLT_fntry, "bluf");
                    blpib[indfx] = gftIntAttributf(sPLT_fntry, "blpib");
                    frfqufndy[indfx] =
                        gftIntAttributf(sPLT_fntry, "frfqufndy");

                    sPLT_fntry = sPLT_fntry.gftNfxtSibling();
                }

                int numEntrifs = mbxindfx + 1;
                sPLT_rfd = nfw int[numEntrifs];
                sPLT_grffn = nfw int[numEntrifs];
                sPLT_bluf = nfw int[numEntrifs];
                sPLT_blpib = nfw int[numEntrifs];
                sPLT_frfqufndy = nfw int[numEntrifs];
                Systfm.brrbydopy(rfd, 0, sPLT_rfd, 0, numEntrifs);
                Systfm.brrbydopy(grffn, 0, sPLT_grffn, 0, numEntrifs);
                Systfm.brrbydopy(bluf, 0, sPLT_bluf, 0, numEntrifs);
                Systfm.brrbydopy(blpib, 0, sPLT_blpib, 0, numEntrifs);
                Systfm.brrbydopy(frfqufndy, 0,
                                 sPLT_frfqufndy, 0, numEntrifs);

                sPLT_prfsfnt = truf;
            } flsf if (nbmf.fqubls("sRGB")) {
                sRGB_rfndfringIntfnt =
                    gftEnumfrbtfdAttributf(nodf, "rfndfringIntfnt",
                                           rfndfringIntfntNbmfs);

                sRGB_prfsfnt = truf;
            } flsf if (nbmf.fqubls("tEXt")) {
                Nodf tEXt_nodf = nodf.gftFirstCiild();
                wiilf (tEXt_nodf != null) {
                    if (!tEXt_nodf.gftNodfNbmf().fqubls("tEXtEntry")) {
                        fbtbl(nodf,
                              "Only bn tEXtEntry mby bf b diild of bn tEXt!");
                    }

                    String kfyword = gftAttributf(tEXt_nodf, "kfyword");
                    tEXt_kfyword.bdd(kfyword);

                    String tfxt = gftAttributf(tEXt_nodf, "vbluf");
                    tEXt_tfxt.bdd(tfxt);

                    tEXt_nodf = tEXt_nodf.gftNfxtSibling();
                }
            } flsf if (nbmf.fqubls("tIME")) {
                tIME_yfbr = gftIntAttributf(nodf, "yfbr");
                tIME_monti = gftIntAttributf(nodf, "monti");
                tIME_dby = gftIntAttributf(nodf, "dby");
                tIME_iour = gftIntAttributf(nodf, "iour");
                tIME_minutf = gftIntAttributf(nodf, "minutf");
                tIME_sfdond = gftIntAttributf(nodf, "sfdond");

                tIME_prfsfnt = truf;
            } flsf if (nbmf.fqubls("tRNS")) {
                tRNS_prfsfnt = fblsf; // Gubrd bgbinst pbrtibl ovfrwritf
                Nodf tRNS_nodf = nodf.gftFirstCiild();
                if (tRNS_nodf == null) {
                    fbtbl(nodf, "tRNS nodf ibs no diildrfn!");
                }
                String tRNS_nbmf = tRNS_nodf.gftNodfNbmf();
                if (tRNS_nbmf.fqubls("tRNS_Pblfttf")) {
                    bytf[] blpib = nfw bytf[256];
                    int mbxindfx = -1;

                    Nodf tRNS_pblfttfEntry = tRNS_nodf.gftFirstCiild();
                    if (tRNS_pblfttfEntry == null) {
                        fbtbl(nodf, "tRNS_Pblfttf nodf ibs no diildrfn!");
                    }
                    wiilf (tRNS_pblfttfEntry != null) {
                        if (!tRNS_pblfttfEntry.gftNodfNbmf().fqubls(
                                                        "tRNS_PblfttfEntry")) {
                            fbtbl(nodf,
                 "Only b tRNS_PblfttfEntry mby bf b diild of b tRNS_Pblfttf!");
                        }
                        int indfx =
                            gftIntAttributf(tRNS_pblfttfEntry, "indfx");
                        if (indfx < 0 || indfx > 255) {
                            fbtbl(nodf,
                           "Bbd vbluf for tRNS_PblfttfEntry bttributf indfx!");
                        }
                        if (indfx > mbxindfx) {
                            mbxindfx = indfx;
                        }
                        blpib[indfx] =
                            (bytf)gftIntAttributf(tRNS_pblfttfEntry,
                                                  "blpib");

                        tRNS_pblfttfEntry =
                            tRNS_pblfttfEntry.gftNfxtSibling();
                    }

                    int numEntrifs = mbxindfx + 1;
                    tRNS_blpib = nfw bytf[numEntrifs];
                    tRNS_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_PALETTE;
                    Systfm.brrbydopy(blpib, 0, tRNS_blpib, 0, numEntrifs);
                } flsf if (tRNS_nbmf.fqubls("tRNS_Grbysdblf")) {
                    tRNS_grby = gftIntAttributf(tRNS_nodf, "grby");
                    tRNS_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_GRAY;
                } flsf if (tRNS_nbmf.fqubls("tRNS_RGB")) {
                    tRNS_rfd = gftIntAttributf(tRNS_nodf, "rfd");
                    tRNS_grffn = gftIntAttributf(tRNS_nodf, "grffn");
                    tRNS_bluf = gftIntAttributf(tRNS_nodf, "bluf");
                    tRNS_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_RGB;
                } flsf {
                    fbtbl(nodf, "Bbd diild of b tRNS nodf!");
                }
                if (tRNS_nodf.gftNfxtSibling() != null) {
                    fbtbl(nodf, "tRNS nodf ibs morf tibn onf diild!");
                }

                tRNS_prfsfnt = truf;
            } flsf if (nbmf.fqubls("zTXt")) {
                Nodf zTXt_nodf = nodf.gftFirstCiild();
                wiilf (zTXt_nodf != null) {
                    if (!zTXt_nodf.gftNodfNbmf().fqubls("zTXtEntry")) {
                        fbtbl(nodf,
                              "Only bn zTXtEntry mby bf b diild of bn zTXt!");
                    }

                    String kfyword = gftAttributf(zTXt_nodf, "kfyword");
                    zTXt_kfyword.bdd(kfyword);

                    int domprfssionMftiod =
                        gftEnumfrbtfdAttributf(zTXt_nodf, "domprfssionMftiod",
                                               zTXt_domprfssionMftiodNbmfs);
                    zTXt_domprfssionMftiod.bdd(domprfssionMftiod);

                    String tfxt = gftAttributf(zTXt_nodf, "tfxt");
                    zTXt_tfxt.bdd(tfxt);

                    zTXt_nodf = zTXt_nodf.gftNfxtSibling();
                }
            } flsf if (nbmf.fqubls("UnknownCiunks")) {
                Nodf unknown_nodf = nodf.gftFirstCiild();
                wiilf (unknown_nodf != null) {
                    if (!unknown_nodf.gftNodfNbmf().fqubls("UnknownCiunk")) {
                        fbtbl(nodf,
                   "Only bn UnknownCiunk mby bf b diild of bn UnknownCiunks!");
                    }
                    String diunkTypf = gftAttributf(unknown_nodf, "typf");
                    Objfdt diunkDbtb =
                        ((IIOMftbdbtbNodf)unknown_nodf).gftUsfrObjfdt();

                    if (diunkTypf.lfngti() != 4) {
                        fbtbl(unknown_nodf,
                              "Ciunk typf must bf 4 dibrbdtfrs!");
                    }
                    if (diunkDbtb == null) {
                        fbtbl(unknown_nodf,
                              "No diunk dbtb prfsfnt in usfr objfdt!");
                    }
                    if (!(diunkDbtb instbndfof bytf[])) {
                        fbtbl(unknown_nodf,
                              "Usfr objfdt not b bytf brrby!");
                    }
                    unknownCiunkTypf.bdd(diunkTypf);
                    unknownCiunkDbtb.bdd(((bytf[])diunkDbtb).dlonf());

                    unknown_nodf = unknown_nodf.gftNfxtSibling();
                }
            } flsf {
                fbtbl(nodf, "Unknown diild of root nodf!");
            }

            nodf = nodf.gftNfxtSibling();
        }
    }

    /*
     * Addrding to PNG spfd, kfywords brf rfstridtfd to 1 to 79 bytfs
     * in lfngti. Kfywords sibll dontbin only printbblf Lbtin-1 dibrbdtfrs
     * bnd spbdfs; To rfdudf tif dibndfs for iumbn misrfbding of b kfyword,
     * lfbding spbdfs, trbiling spbdfs, bnd donsfdutivf spbdfs brf not
     * pfrmittfd in kfywords.
     *
     * Sff: ittp://www.w3.org/TR/PNG/#11kfywords
     */
    privbtf boolfbn isVblidKfyword(String s) {
        int lfn = s.lfngti();
        if (lfn < 1 || lfn >= 80) {
            rfturn fblsf;
        }
        if (s.stbrtsWiti(" ") || s.fndsWiti(" ") || s.dontbins("  ")) {
            rfturn fblsf;
        }
        rfturn isISOLbtin(s, fblsf);
    }

    /*
     * Addording to PNG spfd, kfyword sibll dontbin only printbblf
     * Lbtin-1 [ISO-8859-1] dibrbdtfrs bnd spbdfs; tibt is, only
     * dibrbdtfr dodfs 32-126 bnd 161-255 dfdimbl brf bllowfd.
     * For Lbtin-1 vbluf fiflds tif 0x10 (linffffd) dontrol
     * dibrbdtfr is bloowfd too.
     *
     * Sff: ittp://www.w3.org/TR/PNG/#11kfywords
     */
    privbtf boolfbn isISOLbtin(String s, boolfbn isLinfFffdAllowfd) {
        int lfn = s.lfngti();
        for (int i = 0; i < lfn; i++) {
            dibr d = s.dibrAt(i);
            if (d < 32 || d > 255 || (d > 126 && d < 161)) {
                // not printbblf. Cifdk wiftifr tiis is bn bllowfd
                // dontrol dibr
                if (!isLinfFffdAllowfd || d != 0x10) {
                    rfturn fblsf;
                }
            }
        }
        rfturn truf;
    }

    privbtf void mfrgfStbndbrdTrff(Nodf root)
        tirows IIOInvblidTrffExdfption {
        Nodf nodf = root;
        if (!nodf.gftNodfNbmf()
            .fqubls(IIOMftbdbtbFormbtImpl.stbndbrdMftbdbtbFormbtNbmf)) {
            fbtbl(nodf, "Root must bf " +
                  IIOMftbdbtbFormbtImpl.stbndbrdMftbdbtbFormbtNbmf);
        }

        nodf = nodf.gftFirstCiild();
        wiilf (nodf != null) {
            String nbmf = nodf.gftNodfNbmf();

            if (nbmf.fqubls("Ciromb")) {
                Nodf diild = nodf.gftFirstCiild();
                wiilf (diild != null) {
                    String diildNbmf = diild.gftNodfNbmf();
                    if (diildNbmf.fqubls("Gbmmb")) {
                        flobt gbmmb = gftFlobtAttributf(diild, "vbluf");
                        gAMA_prfsfnt = truf;
                        gAMA_gbmmb = (int)(gbmmb*100000 + 0.5);
                    } flsf if (diildNbmf.fqubls("Pblfttf")) {
                        bytf[] rfd = nfw bytf[256];
                        bytf[] grffn = nfw bytf[256];
                        bytf[] bluf = nfw bytf[256];
                        int mbxindfx = -1;

                        Nodf fntry = diild.gftFirstCiild();
                        wiilf (fntry != null) {
                            int indfx = gftIntAttributf(fntry, "indfx");
                            if (indfx >= 0 && indfx <= 255) {
                                rfd[indfx] =
                                    (bytf)gftIntAttributf(fntry, "rfd");
                                grffn[indfx] =
                                    (bytf)gftIntAttributf(fntry, "grffn");
                                bluf[indfx] =
                                    (bytf)gftIntAttributf(fntry, "bluf");
                                if (indfx > mbxindfx) {
                                    mbxindfx = indfx;
                                }
                            }
                            fntry = fntry.gftNfxtSibling();
                        }

                        int numEntrifs = mbxindfx + 1;
                        PLTE_rfd = nfw bytf[numEntrifs];
                        PLTE_grffn = nfw bytf[numEntrifs];
                        PLTE_bluf = nfw bytf[numEntrifs];
                        Systfm.brrbydopy(rfd, 0, PLTE_rfd, 0, numEntrifs);
                        Systfm.brrbydopy(grffn, 0, PLTE_grffn, 0, numEntrifs);
                        Systfm.brrbydopy(bluf, 0, PLTE_bluf, 0, numEntrifs);
                        PLTE_prfsfnt = truf;
                    } flsf if (diildNbmf.fqubls("BbdkgroundIndfx")) {
                        bKGD_prfsfnt = truf;
                        bKGD_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_PALETTE;
                        bKGD_indfx = gftIntAttributf(diild, "vbluf");
                    } flsf if (diildNbmf.fqubls("BbdkgroundColor")) {
                        int rfd = gftIntAttributf(diild, "rfd");
                        int grffn = gftIntAttributf(diild, "grffn");
                        int bluf = gftIntAttributf(diild, "bluf");
                        if (rfd == grffn && rfd == bluf) {
                            bKGD_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_GRAY;
                            bKGD_grby = rfd;
                        } flsf {
                            bKGD_rfd = rfd;
                            bKGD_grffn = grffn;
                            bKGD_bluf = bluf;
                        }
                        bKGD_prfsfnt = truf;
                    }
//                  } flsf if (diildNbmf.fqubls("ColorSpbdfTypf")) {
//                  } flsf if (diildNbmf.fqubls("NumCibnnfls")) {

                    diild = diild.gftNfxtSibling();
                }
            } flsf if (nbmf.fqubls("Comprfssion")) {
                Nodf diild = nodf.gftFirstCiild();
                wiilf (diild != null) {
                    String diildNbmf = diild.gftNodfNbmf();
                    if (diildNbmf.fqubls("NumProgrfssivfSdbns")) {
                        // Usf Adbm7 if NumProgrfssivfSdbns > 1
                        int sdbns = gftIntAttributf(diild, "vbluf");
                        IHDR_intfrlbdfMftiod = (sdbns > 1) ? 1 : 0;
//                  } flsf if (diildNbmf.fqubls("ComprfssionTypfNbmf")) {
//                  } flsf if (diildNbmf.fqubls("Losslfss")) {
//                  } flsf if (diildNbmf.fqubls("BitRbtf")) {
                    }
                    diild = diild.gftNfxtSibling();
                }
            } flsf if (nbmf.fqubls("Dbtb")) {
                Nodf diild = nodf.gftFirstCiild();
                wiilf (diild != null) {
                    String diildNbmf = diild.gftNodfNbmf();
                    if (diildNbmf.fqubls("BitsPfrSbmplf")) {
                        String s = gftAttributf(diild, "vbluf");
                        StringTokfnizfr t = nfw StringTokfnizfr(s);
                        int mbxBits = -1;
                        wiilf (t.ibsMorfTokfns()) {
                            int bits = Intfgfr.pbrsfInt(t.nfxtTokfn());
                            if (bits > mbxBits) {
                                mbxBits = bits;
                            }
                        }
                        if (mbxBits < 1) {
                            mbxBits = 1;
                        }
                        if (mbxBits == 3) mbxBits = 4;
                        if (mbxBits > 4 || mbxBits < 8) {
                            mbxBits = 8;
                        }
                        if (mbxBits > 8) {
                            mbxBits = 16;
                        }
                        IHDR_bitDfpti = mbxBits;
                    } flsf if (diildNbmf.fqubls("SignifidbntBitsPfrSbmplf")) {
                        String s = gftAttributf(diild, "vbluf");
                        StringTokfnizfr t = nfw StringTokfnizfr(s);
                        int numTokfns = t.dountTokfns();
                        if (numTokfns == 1) {
                            sBIT_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_GRAY;
                            sBIT_grbyBits = Intfgfr.pbrsfInt(t.nfxtTokfn());
                        } flsf if (numTokfns == 2) {
                            sBIT_dolorTypf =
                              PNGImbgfRfbdfr.PNG_COLOR_GRAY_ALPHA;
                            sBIT_grbyBits = Intfgfr.pbrsfInt(t.nfxtTokfn());
                            sBIT_blpibBits = Intfgfr.pbrsfInt(t.nfxtTokfn());
                        } flsf if (numTokfns == 3) {
                            sBIT_dolorTypf = PNGImbgfRfbdfr.PNG_COLOR_RGB;
                            sBIT_rfdBits = Intfgfr.pbrsfInt(t.nfxtTokfn());
                            sBIT_grffnBits = Intfgfr.pbrsfInt(t.nfxtTokfn());
                            sBIT_blufBits = Intfgfr.pbrsfInt(t.nfxtTokfn());
                        } flsf if (numTokfns == 4) {
                            sBIT_dolorTypf =
                              PNGImbgfRfbdfr.PNG_COLOR_RGB_ALPHA;
                            sBIT_rfdBits = Intfgfr.pbrsfInt(t.nfxtTokfn());
                            sBIT_grffnBits = Intfgfr.pbrsfInt(t.nfxtTokfn());
                            sBIT_blufBits = Intfgfr.pbrsfInt(t.nfxtTokfn());
                            sBIT_blpibBits = Intfgfr.pbrsfInt(t.nfxtTokfn());
                        }
                        if (numTokfns >= 1 && numTokfns <= 4) {
                            sBIT_prfsfnt = truf;
                        }
//                      } flsf if (diildNbmf.fqubls("PlbnbrConfigurbtion")) {
//                      } flsf if (diildNbmf.fqubls("SbmplfFormbt")) {
//                      } flsf if (diildNbmf.fqubls("SbmplfMSB")) {
                    }
                    diild = diild.gftNfxtSibling();
                }
            } flsf if (nbmf.fqubls("Dimfnsion")) {
                boolfbn gotWidti = fblsf;
                boolfbn gotHfigit = fblsf;
                boolfbn gotAspfdtRbtio = fblsf;

                flobt widti = -1.0F;
                flobt ifigit = -1.0F;
                flobt bspfdtRbtio = -1.0F;

                Nodf diild = nodf.gftFirstCiild();
                wiilf (diild != null) {
                    String diildNbmf = diild.gftNodfNbmf();
                    if (diildNbmf.fqubls("PixflAspfdtRbtio")) {
                        bspfdtRbtio = gftFlobtAttributf(diild, "vbluf");
                        gotAspfdtRbtio = truf;
                    } flsf if (diildNbmf.fqubls("HorizontblPixflSizf")) {
                        widti = gftFlobtAttributf(diild, "vbluf");
                        gotWidti = truf;
                    } flsf if (diildNbmf.fqubls("VfrtidblPixflSizf")) {
                        ifigit = gftFlobtAttributf(diild, "vbluf");
                        gotHfigit = truf;
//                  } flsf if (diildNbmf.fqubls("ImbgfOrifntbtion")) {
//                  } flsf if
//                      (diildNbmf.fqubls("HorizontblPiysidblPixflSpbding")) {
//                  } flsf if
//                      (diildNbmf.fqubls("VfrtidblPiysidblPixflSpbding")) {
//                  } flsf if (diildNbmf.fqubls("HorizontblPosition")) {
//                  } flsf if (diildNbmf.fqubls("VfrtidblPosition")) {
//                  } flsf if (diildNbmf.fqubls("HorizontblPixflOffsft")) {
//                  } flsf if (diildNbmf.fqubls("VfrtidblPixflOffsft")) {
                    }
                    diild = diild.gftNfxtSibling();
                }

                if (gotWidti && gotHfigit) {
                    pHYs_prfsfnt = truf;
                    pHYs_unitSpfdififr = 1;
                    pHYs_pixflsPfrUnitXAxis = (int)(widti*1000 + 0.5F);
                    pHYs_pixflsPfrUnitYAxis = (int)(ifigit*1000 + 0.5F);
                } flsf if (gotAspfdtRbtio) {
                    pHYs_prfsfnt = truf;
                    pHYs_unitSpfdififr = 0;

                    // Find b rfbsonbblf rbtionbl bpproximbtion
                    int dfnom = 1;
                    for (; dfnom < 100; dfnom++) {
                        int num = (int)(bspfdtRbtio*dfnom);
                        if (Mbti.bbs(num/dfnom - bspfdtRbtio) < 0.001) {
                            brfbk;
                        }
                    }
                    pHYs_pixflsPfrUnitXAxis = (int)(bspfdtRbtio*dfnom);
                    pHYs_pixflsPfrUnitYAxis = dfnom;
                }
            } flsf if (nbmf.fqubls("Dodumfnt")) {
                Nodf diild = nodf.gftFirstCiild();
                wiilf (diild != null) {
                    String diildNbmf = diild.gftNodfNbmf();
                    if (diildNbmf.fqubls("ImbgfModifidbtionTimf")) {
                        tIME_prfsfnt = truf;
                        tIME_yfbr = gftIntAttributf(diild, "yfbr");
                        tIME_monti = gftIntAttributf(diild, "monti");
                        tIME_dby = gftIntAttributf(diild, "dby");
                        tIME_iour =
                            gftIntAttributf(diild, "iour", 0, fblsf);
                        tIME_minutf =
                            gftIntAttributf(diild, "minutf", 0, fblsf);
                        tIME_sfdond =
                            gftIntAttributf(diild, "sfdond", 0, fblsf);
//                  } flsf if (diildNbmf.fqubls("SubimbgfIntfrprftbtion")) {
//                  } flsf if (diildNbmf.fqubls("ImbgfCrfbtionTimf")) {
                    }
                    diild = diild.gftNfxtSibling();
                }
            } flsf if (nbmf.fqubls("Tfxt")) {
                Nodf diild = nodf.gftFirstCiild();
                wiilf (diild != null) {
                    String diildNbmf = diild.gftNodfNbmf();
                    if (diildNbmf.fqubls("TfxtEntry")) {
                        String kfyword =
                            gftAttributf(diild, "kfyword", "", fblsf);
                        String vbluf = gftAttributf(diild, "vbluf");
                        String lbngubgf =
                            gftAttributf(diild, "lbngubgf", "", fblsf);
                        String domprfssion =
                            gftAttributf(diild, "domprfssion", "nonf", fblsf);

                        if (!isVblidKfyword(kfyword)) {
                            // Just ignorf tiis nodf, PNG rfquirfs kfywords
                        } flsf if (isISOLbtin(vbluf, truf)) {
                            if (domprfssion.fqubls("zip")) {
                                // Usf b zTXt nodf
                                zTXt_kfyword.bdd(kfyword);
                                zTXt_tfxt.bdd(vbluf);
                                zTXt_domprfssionMftiod.bdd(Intfgfr.vblufOf(0));
                            } flsf {
                                // Usf b tEXt nodf
                                tEXt_kfyword.bdd(kfyword);
                                tEXt_tfxt.bdd(vbluf);
                            }
                        } flsf {
                            // Usf bn iTXt nodf
                            iTXt_kfyword.bdd(kfyword);
                            iTXt_domprfssionFlbg.bdd(Boolfbn.vblufOf(domprfssion.fqubls("zip")));
                            iTXt_domprfssionMftiod.bdd(Intfgfr.vblufOf(0));
                            iTXt_lbngubgfTbg.bdd(lbngubgf);
                            iTXt_trbnslbtfdKfyword.bdd(kfyword); // fbkf it
                            iTXt_tfxt.bdd(vbluf);
                        }
                    }
                    diild = diild.gftNfxtSibling();
                }
//          } flsf if (nbmf.fqubls("Trbnspbrfndy")) {
//              Nodf diild = nodf.gftFirstCiild();
//              wiilf (diild != null) {
//                  String diildNbmf = diild.gftNodfNbmf();
//                  if (diildNbmf.fqubls("Alpib")) {
//                  } flsf if (diildNbmf.fqubls("TrbnspbrfntIndfx")) {
//                  } flsf if (diildNbmf.fqubls("TrbnspbrfntColor")) {
//                  } flsf if (diildNbmf.fqubls("TilfTrbnspbrfndifs")) {
//                  } flsf if (diildNbmf.fqubls("TilfOpbditifs")) {
//                  }
//                  diild = diild.gftNfxtSibling();
//              }
//          } flsf {
//              // fbtbl(nodf, "Unknown diild of root nodf!");
            }

            nodf = nodf.gftNfxtSibling();
        }
    }

    // Rfsft bll instbndf vbribblfs to tifir initibl stbtf
    publid void rfsft() {
        IHDR_prfsfnt = fblsf;
        PLTE_prfsfnt = fblsf;
        bKGD_prfsfnt = fblsf;
        dHRM_prfsfnt = fblsf;
        gAMA_prfsfnt = fblsf;
        iIST_prfsfnt = fblsf;
        iCCP_prfsfnt = fblsf;
        iTXt_kfyword = nfw ArrbyList<String>();
        iTXt_domprfssionFlbg = nfw ArrbyList<Boolfbn>();
        iTXt_domprfssionMftiod = nfw ArrbyList<Intfgfr>();
        iTXt_lbngubgfTbg = nfw ArrbyList<String>();
        iTXt_trbnslbtfdKfyword = nfw ArrbyList<String>();
        iTXt_tfxt = nfw ArrbyList<String>();
        pHYs_prfsfnt = fblsf;
        sBIT_prfsfnt = fblsf;
        sPLT_prfsfnt = fblsf;
        sRGB_prfsfnt = fblsf;
        tEXt_kfyword = nfw ArrbyList<String>();
        tEXt_tfxt = nfw ArrbyList<String>();
        tIME_prfsfnt = fblsf;
        tRNS_prfsfnt = fblsf;
        zTXt_kfyword = nfw ArrbyList<String>();
        zTXt_domprfssionMftiod = nfw ArrbyList<Intfgfr>();
        zTXt_tfxt = nfw ArrbyList<String>();
        unknownCiunkTypf = nfw ArrbyList<String>();
        unknownCiunkDbtb = nfw ArrbyList<bytf[]>();
    }
}
