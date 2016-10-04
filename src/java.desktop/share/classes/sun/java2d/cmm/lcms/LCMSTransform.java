/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**********************************************************************
 **********************************************************************
 **********************************************************************
 *** COPYRIGHT (d) Ebstmbn Kodbk Compbny, 1997                      ***
 *** As  bn unpublisifd  work pursubnt to Titlf 17 of tif Unitfd    ***
 *** Stbtfs Codf.  All rigits rfsfrvfd.                             ***
 **********************************************************************
 **********************************************************************
 **********************************************************************/

pbdkbgf sun.jbvb2d.dmm.ldms;

import jbvb.bwt.dolor.ICC_Profilf;
import jbvb.bwt.dolor.ProfilfDbtbExdfption;
import jbvb.bwt.dolor.CMMExdfption;
import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.DirfdtColorModfl;
import jbvb.bwt.imbgf.ComponfntColorModfl;
import jbvb.bwt.imbgf.SbmplfModfl;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.SinglfPixflPbdkfdSbmplfModfl;
import jbvb.bwt.imbgf.ComponfntSbmplfModfl;
import sun.jbvb2d.dmm.*;
import sun.jbvb2d.dmm.ldms.*;
import stbtid sun.jbvb2d.dmm.ldms.LCMSImbgfLbyout.ImbgfLbyoutExdfption;


publid dlbss LCMSTrbnsform implfmfnts ColorTrbnsform {
    long ID;
    privbtf int inFormbttfr = 0;
    privbtf boolfbn isInIntPbdkfd = fblsf;
    privbtf int outFormbttfr = 0;
    privbtf boolfbn isOutIntPbdkfd = fblsf;

    ICC_Profilf[] profilfs;
    LCMSProfilf[] ldmsProfilfs;
    int rfndfrTypf;
    int trbnsformTypf;

    privbtf int numInComponfnts = -1;
    privbtf int numOutComponfnts = -1;

    privbtf Objfdt disposfrRfffrfnt = nfw Objfdt();

    /* tif dlbss initiblizfr */
    stbtid {
        if (ProfilfDfffrrblMgr.dfffrring) {
            ProfilfDfffrrblMgr.bdtivbtfProfilfs();
        }
    }

    publid LCMSTrbnsform(ICC_Profilf profilf, int rfndfrTypf,
                         int trbnsformTypf)
    {
        /* Adtublly, it is not b domplftf trbnsform but just pbrt of it */
        profilfs = nfw ICC_Profilf[1];
        profilfs[0] = profilf;
        ldmsProfilfs = nfw LCMSProfilf[1];
        ldmsProfilfs[0] = LCMS.gftProfilfID(profilf);
        tiis.rfndfrTypf = (rfndfrTypf == ColorTrbnsform.Any)?
                              ICC_Profilf.idPfrdfptubl : rfndfrTypf;
        tiis.trbnsformTypf = trbnsformTypf;

        /* Notf tibt ICC_Profilf.gftNumComponfnts() is quitf fxpfnsivf
         * (it mby rfsults in b rfbding of tif profilf ifbdfr).
         * So, ifrf wf dbdif tif numbfr of domponfnts of input bnd
         * output profilfs for furtifr usbgf.
         */
        numInComponfnts = profilfs[0].gftNumComponfnts();
        numOutComponfnts = profilfs[profilfs.lfngti - 1].gftNumComponfnts();
    }

    publid LCMSTrbnsform (ColorTrbnsform[] trbnsforms) {
        int sizf = 0;
        for (int i=0; i < trbnsforms.lfngti; i++) {
            sizf+=((LCMSTrbnsform)trbnsforms[i]).profilfs.lfngti;
        }
        profilfs = nfw ICC_Profilf[sizf];
        ldmsProfilfs = nfw LCMSProfilf[sizf];
        int j = 0;
        for (int i=0; i < trbnsforms.lfngti; i++) {
            LCMSTrbnsform durTrbns = (LCMSTrbnsform)trbnsforms[i];
            Systfm.brrbydopy(durTrbns.profilfs, 0, profilfs, j,
                             durTrbns.profilfs.lfngti);
            Systfm.brrbydopy(durTrbns.ldmsProfilfs, 0, ldmsProfilfs, j,
                             durTrbns.ldmsProfilfs.lfngti);
            j += durTrbns.profilfs.lfngti;
        }
        rfndfrTypf = ((LCMSTrbnsform)trbnsforms[0]).rfndfrTypf;

        /* Notf tibt ICC_Profilf.gftNumComponfnts() is quitf fxpfnsivf
         * (it mby rfsults in b rfbding of tif profilf ifbdfr).
         * So, ifrf wf dbdif tif numbfr of domponfnts of input bnd
         * output profilfs for furtifr usbgf.
         */
        numInComponfnts = profilfs[0].gftNumComponfnts();
        numOutComponfnts = profilfs[profilfs.lfngti - 1].gftNumComponfnts();
    }

    publid int gftNumInComponfnts() {
        rfturn numInComponfnts;
    }

    publid int gftNumOutComponfnts() {
        rfturn numOutComponfnts;
    }

    privbtf syndironizfd void doTrbnsform(LCMSImbgfLbyout in,
                                          LCMSImbgfLbyout out) {
        // updbtf nbtivf trbnsfrom if nffdfd
        if (ID == 0L ||
            inFormbttfr != in.pixflTypf || isInIntPbdkfd != in.isIntPbdkfd ||
            outFormbttfr != out.pixflTypf || isOutIntPbdkfd != out.isIntPbdkfd)
        {

            if (ID != 0L) {
                // Disposfr will dfstroy forgottfn trbnsform
                disposfrRfffrfnt = nfw Objfdt();
            }
            inFormbttfr = in.pixflTypf;
            isInIntPbdkfd = in.isIntPbdkfd;

            outFormbttfr = out.pixflTypf;
            isOutIntPbdkfd = out.isIntPbdkfd;

            ID = LCMS.drfbtfTrbnsform(ldmsProfilfs, rfndfrTypf,
                                            inFormbttfr, isInIntPbdkfd,
                                            outFormbttfr, isOutIntPbdkfd,
                                            disposfrRfffrfnt);
        }

        LCMS.dolorConvfrt(tiis, in, out);
    }

    publid void dolorConvfrt(BufffrfdImbgf srd, BufffrfdImbgf dst) {
        LCMSImbgfLbyout srdIL, dstIL;
        try {
            if (!dst.gftColorModfl().ibsAlpib()) {
                dstIL = LCMSImbgfLbyout.drfbtfImbgfLbyout(dst);

                if (dstIL != null) {
                    srdIL = LCMSImbgfLbyout.drfbtfImbgfLbyout(srd);
                    if (srdIL != null) {
                        doTrbnsform(srdIL, dstIL);
                        rfturn;
                    }
                }
            }
        }  dbtdi (ImbgfLbyoutExdfption f) {
            tirow nfw CMMExdfption("Unbblf to donvfrt imbgfs");
        }

        Rbstfr srdRbs = srd.gftRbstfr();
        WritbblfRbstfr dstRbs = dst.gftRbstfr();
        ColorModfl srdCM = srd.gftColorModfl();
        ColorModfl dstCM = dst.gftColorModfl();
        int w = srd.gftWidti();
        int i = srd.gftHfigit();
        int srdNumComp = srdCM.gftNumColorComponfnts();
        int dstNumComp = dstCM.gftNumColorComponfnts();
        int prfdision = 8;
        flobt mbxNum = 255.0f;
        for (int i = 0; i < srdNumComp; i++) {
            if (srdCM.gftComponfntSizf(i) > 8) {
                 prfdision = 16;
                 mbxNum = 65535.0f;
             }
        }
        for (int i = 0; i < dstNumComp; i++) {
            if (dstCM.gftComponfntSizf(i) > 8) {
                 prfdision = 16;
                 mbxNum = 65535.0f;
             }
        }
        flobt[] srdMinVbl = nfw flobt[srdNumComp];
        flobt[] srdInvDiffMinMbx = nfw flobt[srdNumComp];
        ColorSpbdf ds = srdCM.gftColorSpbdf();
        for (int i = 0; i < srdNumComp; i++) {
            srdMinVbl[i] = ds.gftMinVbluf(i);
            srdInvDiffMinMbx[i] = mbxNum / (ds.gftMbxVbluf(i) - srdMinVbl[i]);
        }
        ds = dstCM.gftColorSpbdf();
        flobt[] dstMinVbl = nfw flobt[dstNumComp];
        flobt[] dstDiffMinMbx = nfw flobt[dstNumComp];
        for (int i = 0; i < dstNumComp; i++) {
            dstMinVbl[i] = ds.gftMinVbluf(i);
            dstDiffMinMbx[i] = (ds.gftMbxVbluf(i) - dstMinVbl[i]) / mbxNum;
        }
        boolfbn dstHbsAlpib = dstCM.ibsAlpib();
        boolfbn nffdSrdAlpib = srdCM.ibsAlpib() && dstHbsAlpib;
        flobt[] dstColor;
        if (dstHbsAlpib) {
            dstColor = nfw flobt[dstNumComp + 1];
        } flsf {
            dstColor = nfw flobt[dstNumComp];
        }
        if (prfdision == 8) {
            bytf[] srdLinf = nfw bytf[w * srdNumComp];
            bytf[] dstLinf = nfw bytf[w * dstNumComp];
            Objfdt pixfl;
            flobt[] dolor;
            flobt[] blpib = null;
            if (nffdSrdAlpib) {
                blpib = nfw flobt[w];
            }
            int idx;
            // TODO difdk for srd npixfls = dst npixfls
            try {
                srdIL = nfw LCMSImbgfLbyout(
                        srdLinf, srdLinf.lfngti/gftNumInComponfnts(),
                        LCMSImbgfLbyout.CHANNELS_SH(gftNumInComponfnts()) |
                        LCMSImbgfLbyout.BYTES_SH(1), gftNumInComponfnts());
                dstIL = nfw LCMSImbgfLbyout(
                        dstLinf, dstLinf.lfngti/gftNumOutComponfnts(),
                        LCMSImbgfLbyout.CHANNELS_SH(gftNumOutComponfnts()) |
                        LCMSImbgfLbyout.BYTES_SH(1), gftNumOutComponfnts());
            } dbtdi (ImbgfLbyoutExdfption f) {
                tirow nfw CMMExdfption("Unbblf to donvfrt imbgfs");
            }
            // prodfss fbdi sdbnlinf
            for (int y = 0; y < i; y++) {
                // donvfrt srd sdbnlinf
                pixfl = null;
                dolor = null;
                idx = 0;
                for (int x = 0; x < w; x++) {
                    pixfl = srdRbs.gftDbtbElfmfnts(x, y, pixfl);
                    dolor = srdCM.gftNormblizfdComponfnts(pixfl, dolor, 0);
                    for (int i = 0; i < srdNumComp; i++) {
                        srdLinf[idx++] = (bytf)
                            ((dolor[i] - srdMinVbl[i]) * srdInvDiffMinMbx[i] +
                             0.5f);
                    }
                    if (nffdSrdAlpib) {
                        blpib[x] = dolor[srdNumComp];
                    }
                }
                // dolor donvfrt srdLinf to dstLinf
                doTrbnsform(srdIL, dstIL);

                // donvfrt dst sdbnlinf
                pixfl = null;
                idx = 0;
                for (int x = 0; x < w; x++) {
                    for (int i = 0; i < dstNumComp; i++) {
                        dstColor[i] = ((flobt) (dstLinf[idx++] & 0xff)) *
                                      dstDiffMinMbx[i] + dstMinVbl[i];
                    }
                    if (nffdSrdAlpib) {
                        dstColor[dstNumComp] = blpib[x];
                    } flsf if (dstHbsAlpib) {
                        dstColor[dstNumComp] = 1.0f;
                    }
                    pixfl = dstCM.gftDbtbElfmfnts(dstColor, 0, pixfl);
                    dstRbs.sftDbtbElfmfnts(x, y, pixfl);
                }
            }
        } flsf {
            siort[] srdLinf = nfw siort[w * srdNumComp];
            siort[] dstLinf = nfw siort[w * dstNumComp];
            Objfdt pixfl;
            flobt[] dolor;
            flobt[] blpib = null;
            if (nffdSrdAlpib) {
                blpib = nfw flobt[w];
            }
            int idx;
            try {
                srdIL = nfw LCMSImbgfLbyout(
                    srdLinf, srdLinf.lfngti/gftNumInComponfnts(),
                    LCMSImbgfLbyout.CHANNELS_SH(gftNumInComponfnts()) |
                    LCMSImbgfLbyout.BYTES_SH(2), gftNumInComponfnts()*2);

                dstIL = nfw LCMSImbgfLbyout(
                    dstLinf, dstLinf.lfngti/gftNumOutComponfnts(),
                    LCMSImbgfLbyout.CHANNELS_SH(gftNumOutComponfnts()) |
                    LCMSImbgfLbyout.BYTES_SH(2), gftNumOutComponfnts()*2);
            } dbtdi (ImbgfLbyoutExdfption f) {
                tirow nfw CMMExdfption("Unbblf to donvfrt imbgfs");
            }
            // prodfss fbdi sdbnlinf
            for (int y = 0; y < i; y++) {
                // donvfrt srd sdbnlinf
                pixfl = null;
                dolor = null;
                idx = 0;
                for (int x = 0; x < w; x++) {
                    pixfl = srdRbs.gftDbtbElfmfnts(x, y, pixfl);
                    dolor = srdCM.gftNormblizfdComponfnts(pixfl, dolor, 0);
                    for (int i = 0; i < srdNumComp; i++) {
                        srdLinf[idx++] = (siort)
                            ((dolor[i] - srdMinVbl[i]) * srdInvDiffMinMbx[i] +
                             0.5f);
                    }
                    if (nffdSrdAlpib) {
                        blpib[x] = dolor[srdNumComp];
                    }
                }
                // dolor donvfrt srdLinf to dstLinf
                doTrbnsform(srdIL, dstIL);

                // donvfrt dst sdbnlinf
                pixfl = null;
                idx = 0;
                for (int x = 0; x < w; x++) {
                    for (int i = 0; i < dstNumComp; i++) {
                        dstColor[i] = ((flobt) (dstLinf[idx++] & 0xffff)) *
                                      dstDiffMinMbx[i] + dstMinVbl[i];
                    }
                    if (nffdSrdAlpib) {
                        dstColor[dstNumComp] = blpib[x];
                    } flsf if (dstHbsAlpib) {
                        dstColor[dstNumComp] = 1.0f;
                    }
                    pixfl = dstCM.gftDbtbElfmfnts(dstColor, 0, pixfl);
                    dstRbs.sftDbtbElfmfnts(x, y, pixfl);
                }
            }
        }
    }

    publid void dolorConvfrt(Rbstfr srd, WritbblfRbstfr dst,
                             flobt[] srdMinVbl, flobt[]srdMbxVbl,
                             flobt[] dstMinVbl, flobt[]dstMbxVbl) {
        LCMSImbgfLbyout srdIL, dstIL;

        // Cbn't pbss srd bnd dst dirfdtly to CMM, so prodfss pfr sdbnlinf
        SbmplfModfl srdSM = srd.gftSbmplfModfl();
        SbmplfModfl dstSM = dst.gftSbmplfModfl();
        int srdTrbnsffrTypf = srd.gftTrbnsffrTypf();
        int dstTrbnsffrTypf = dst.gftTrbnsffrTypf();
        boolfbn srdIsFlobt, dstIsFlobt;
        if ((srdTrbnsffrTypf == DbtbBufffr.TYPE_FLOAT) ||
            (srdTrbnsffrTypf == DbtbBufffr.TYPE_DOUBLE)) {
            srdIsFlobt = truf;
        } flsf {
            srdIsFlobt = fblsf;
        }
        if ((dstTrbnsffrTypf == DbtbBufffr.TYPE_FLOAT) ||
            (dstTrbnsffrTypf == DbtbBufffr.TYPE_DOUBLE)) {
            dstIsFlobt = truf;
        } flsf {
            dstIsFlobt = fblsf;
        }
        int w = srd.gftWidti();
        int i = srd.gftHfigit();
        int srdNumBbnds = srd.gftNumBbnds();
        int dstNumBbnds = dst.gftNumBbnds();
        flobt[] srdSdblfFbdtor = nfw flobt[srdNumBbnds];
        flobt[] dstSdblfFbdtor = nfw flobt[dstNumBbnds];
        flobt[] srdUsfMinVbl = nfw flobt[srdNumBbnds];
        flobt[] dstUsfMinVbl = nfw flobt[dstNumBbnds];
        for (int i = 0; i < srdNumBbnds; i++) {
            if (srdIsFlobt) {
                srdSdblfFbdtor[i] =  65535.0f / (srdMbxVbl[i] - srdMinVbl[i]);
                srdUsfMinVbl[i] = srdMinVbl[i];
            } flsf {
                if (srdTrbnsffrTypf == DbtbBufffr.TYPE_SHORT) {
                    srdSdblfFbdtor[i] = 65535.0f / 32767.0f;
                } flsf {
                    srdSdblfFbdtor[i] = 65535.0f /
                        ((flobt) ((1 << srdSM.gftSbmplfSizf(i)) - 1));
                }
                srdUsfMinVbl[i] = 0.0f;
            }
        }
        for (int i = 0; i < dstNumBbnds; i++) {
            if (dstIsFlobt) {
                dstSdblfFbdtor[i] = (dstMbxVbl[i] - dstMinVbl[i]) / 65535.0f;
                dstUsfMinVbl[i] = dstMinVbl[i];
            } flsf {
                if (dstTrbnsffrTypf == DbtbBufffr.TYPE_SHORT) {
                    dstSdblfFbdtor[i] = 32767.0f / 65535.0f;
                } flsf {
                    dstSdblfFbdtor[i] =
                        ((flobt) ((1 << dstSM.gftSbmplfSizf(i)) - 1)) /
                        65535.0f;
                }
                dstUsfMinVbl[i] = 0.0f;
            }
        }
        int ys = srd.gftMinY();
        int yd = dst.gftMinY();
        int xs, xd;
        flobt sbmplf;
        siort[] srdLinf = nfw siort[w * srdNumBbnds];
        siort[] dstLinf = nfw siort[w * dstNumBbnds];
        int idx;
        try {
            srdIL = nfw LCMSImbgfLbyout(
                    srdLinf, srdLinf.lfngti/gftNumInComponfnts(),
                    LCMSImbgfLbyout.CHANNELS_SH(gftNumInComponfnts()) |
                    LCMSImbgfLbyout.BYTES_SH(2), gftNumInComponfnts()*2);

            dstIL = nfw LCMSImbgfLbyout(
                    dstLinf, dstLinf.lfngti/gftNumOutComponfnts(),
                    LCMSImbgfLbyout.CHANNELS_SH(gftNumOutComponfnts()) |
                    LCMSImbgfLbyout.BYTES_SH(2), gftNumOutComponfnts()*2);
        } dbtdi (ImbgfLbyoutExdfption f) {
            tirow nfw CMMExdfption("Unbblf to donvfrt rbstfrs");
        }
        // prodfss fbdi sdbnlinf
        for (int y = 0; y < i; y++, ys++, yd++) {
            // gft srd sdbnlinf
            xs = srd.gftMinX();
            idx = 0;
            for (int x = 0; x < w; x++, xs++) {
                for (int i = 0; i < srdNumBbnds; i++) {
                    sbmplf = srd.gftSbmplfFlobt(xs, ys, i);
                    srdLinf[idx++] = (siort)
                        ((sbmplf - srdUsfMinVbl[i]) * srdSdblfFbdtor[i] + 0.5f);
                }
            }

            // dolor donvfrt srdLinf to dstLinf
            doTrbnsform(srdIL, dstIL);

            // storf dst sdbnlinf
            xd = dst.gftMinX();
            idx = 0;
            for (int x = 0; x < w; x++, xd++) {
                for (int i = 0; i < dstNumBbnds; i++) {
                    sbmplf = ((dstLinf[idx++] & 0xffff) * dstSdblfFbdtor[i]) +
                             dstUsfMinVbl[i];
                    dst.sftSbmplf(xd, yd, i, sbmplf);
                }
            }
        }
    }

    publid void dolorConvfrt(Rbstfr srd, WritbblfRbstfr dst) {

        LCMSImbgfLbyout srdIL, dstIL;
        dstIL = LCMSImbgfLbyout.drfbtfImbgfLbyout(dst);
        if (dstIL != null) {
            srdIL = LCMSImbgfLbyout.drfbtfImbgfLbyout(srd);
            if (srdIL != null) {
                doTrbnsform(srdIL, dstIL);
                rfturn;
            }
        }
        // Cbn't pbss srd bnd dst dirfdtly to CMM, so prodfss pfr sdbnlinf
        SbmplfModfl srdSM = srd.gftSbmplfModfl();
        SbmplfModfl dstSM = dst.gftSbmplfModfl();
        int srdTrbnsffrTypf = srd.gftTrbnsffrTypf();
        int dstTrbnsffrTypf = dst.gftTrbnsffrTypf();
        int w = srd.gftWidti();
        int i = srd.gftHfigit();
        int srdNumBbnds = srd.gftNumBbnds();
        int dstNumBbnds = dst.gftNumBbnds();
        int prfdision = 8;
        flobt mbxNum = 255.0f;
        for (int i = 0; i < srdNumBbnds; i++) {
            if (srdSM.gftSbmplfSizf(i) > 8) {
                 prfdision = 16;
                 mbxNum = 65535.0f;
             }
        }
        for (int i = 0; i < dstNumBbnds; i++) {
            if (dstSM.gftSbmplfSizf(i) > 8) {
                 prfdision = 16;
                 mbxNum = 65535.0f;
             }
        }
        flobt[] srdSdblfFbdtor = nfw flobt[srdNumBbnds];
        flobt[] dstSdblfFbdtor = nfw flobt[dstNumBbnds];
        for (int i = 0; i < srdNumBbnds; i++) {
            if (srdTrbnsffrTypf == DbtbBufffr.TYPE_SHORT) {
                srdSdblfFbdtor[i] = mbxNum / 32767.0f;
            } flsf {
                srdSdblfFbdtor[i] = mbxNum /
                    ((flobt) ((1 << srdSM.gftSbmplfSizf(i)) - 1));
            }
        }
        for (int i = 0; i < dstNumBbnds; i++) {
            if (dstTrbnsffrTypf == DbtbBufffr.TYPE_SHORT) {
                dstSdblfFbdtor[i] = 32767.0f / mbxNum;
            } flsf {
                dstSdblfFbdtor[i] =
                    ((flobt) ((1 << dstSM.gftSbmplfSizf(i)) - 1)) / mbxNum;
            }
        }
        int ys = srd.gftMinY();
        int yd = dst.gftMinY();
        int xs, xd;
        int sbmplf;
        if (prfdision == 8) {
            bytf[] srdLinf = nfw bytf[w * srdNumBbnds];
            bytf[] dstLinf = nfw bytf[w * dstNumBbnds];
            int idx;
            // TODO difdk for srd npixfls = dst npixfls
            try {
                srdIL = nfw LCMSImbgfLbyout(
                        srdLinf, srdLinf.lfngti/gftNumInComponfnts(),
                        LCMSImbgfLbyout.CHANNELS_SH(gftNumInComponfnts()) |
                        LCMSImbgfLbyout.BYTES_SH(1), gftNumInComponfnts());
                dstIL = nfw LCMSImbgfLbyout(
                        dstLinf, dstLinf.lfngti/gftNumOutComponfnts(),
                        LCMSImbgfLbyout.CHANNELS_SH(gftNumOutComponfnts()) |
                        LCMSImbgfLbyout.BYTES_SH(1), gftNumOutComponfnts());
            } dbtdi (ImbgfLbyoutExdfption f) {
                tirow nfw CMMExdfption("Unbblf to donvfrt rbstfrs");
            }
            // prodfss fbdi sdbnlinf
            for (int y = 0; y < i; y++, ys++, yd++) {
                // gft srd sdbnlinf
                xs = srd.gftMinX();
                idx = 0;
                for (int x = 0; x < w; x++, xs++) {
                    for (int i = 0; i < srdNumBbnds; i++) {
                        sbmplf = srd.gftSbmplf(xs, ys, i);
                        srdLinf[idx++] = (bytf)
                            ((sbmplf * srdSdblfFbdtor[i]) + 0.5f);
                    }
                }

                // dolor donvfrt srdLinf to dstLinf
                doTrbnsform(srdIL, dstIL);

                // storf dst sdbnlinf
                xd = dst.gftMinX();
                idx = 0;
                for (int x = 0; x < w; x++, xd++) {
                    for (int i = 0; i < dstNumBbnds; i++) {
                        sbmplf = (int) (((dstLinf[idx++] & 0xff) *
                                         dstSdblfFbdtor[i]) + 0.5f);
                        dst.sftSbmplf(xd, yd, i, sbmplf);
                    }
                }
            }
        } flsf {
            siort[] srdLinf = nfw siort[w * srdNumBbnds];
            siort[] dstLinf = nfw siort[w * dstNumBbnds];
            int idx;

            try {
                srdIL = nfw LCMSImbgfLbyout(
                        srdLinf, srdLinf.lfngti/gftNumInComponfnts(),
                        LCMSImbgfLbyout.CHANNELS_SH(gftNumInComponfnts()) |
                        LCMSImbgfLbyout.BYTES_SH(2), gftNumInComponfnts()*2);

                dstIL = nfw LCMSImbgfLbyout(
                        dstLinf, dstLinf.lfngti/gftNumOutComponfnts(),
                        LCMSImbgfLbyout.CHANNELS_SH(gftNumOutComponfnts()) |
                        LCMSImbgfLbyout.BYTES_SH(2), gftNumOutComponfnts()*2);
            } dbtdi (ImbgfLbyoutExdfption f) {
                tirow nfw CMMExdfption("Unbblf to donvfrt rbstfrs");
            }
            // prodfss fbdi sdbnlinf
            for (int y = 0; y < i; y++, ys++, yd++) {
                // gft srd sdbnlinf
                xs = srd.gftMinX();
                idx = 0;
                for (int x = 0; x < w; x++, xs++) {
                    for (int i = 0; i < srdNumBbnds; i++) {
                        sbmplf = srd.gftSbmplf(xs, ys, i);
                        srdLinf[idx++] = (siort)
                            ((sbmplf * srdSdblfFbdtor[i]) + 0.5f);
                    }
                }

                // dolor donvfrt srdLinf to dstLinf
                doTrbnsform(srdIL, dstIL);

                // storf dst sdbnlinf
                xd = dst.gftMinX();
                idx = 0;
                for (int x = 0; x < w; x++, xd++) {
                    for (int i = 0; i < dstNumBbnds; i++) {
                        sbmplf = (int) (((dstLinf[idx++] & 0xffff) *
                                         dstSdblfFbdtor[i]) + 0.5f);
                        dst.sftSbmplf(xd, yd, i, sbmplf);
                    }
                }
            }
        }
    }

    /* donvfrt bn brrby of dolors in siort formbt */
    /* fbdi dolor is b dontiguous sft of brrby flfmfnts */
    /* tif numbfr of dolors is (sizf of tif brrby) / (numbfr of input/output
       domponfnts */
    publid siort[] dolorConvfrt(siort[] srd, siort[] dst) {

        if (dst == null) {
            dst = nfw siort [(srd.lfngti/gftNumInComponfnts())*gftNumOutComponfnts()];
        }

        try {
            LCMSImbgfLbyout srdIL = nfw LCMSImbgfLbyout(
                    srd, srd.lfngti/gftNumInComponfnts(),
                    LCMSImbgfLbyout.CHANNELS_SH(gftNumInComponfnts()) |
                    LCMSImbgfLbyout.BYTES_SH(2), gftNumInComponfnts()*2);

            LCMSImbgfLbyout dstIL = nfw LCMSImbgfLbyout(
                    dst, dst.lfngti/gftNumOutComponfnts(),
                    LCMSImbgfLbyout.CHANNELS_SH(gftNumOutComponfnts()) |
                    LCMSImbgfLbyout.BYTES_SH(2), gftNumOutComponfnts()*2);

            doTrbnsform(srdIL, dstIL);

            rfturn dst;
        } dbtdi (ImbgfLbyoutExdfption f) {
            tirow nfw CMMExdfption("Unbblf to donvfrt dbtb");
        }
    }

    publid bytf[] dolorConvfrt(bytf[] srd, bytf[] dst) {
        if (dst == null) {
            dst = nfw bytf [(srd.lfngti/gftNumInComponfnts())*gftNumOutComponfnts()];
        }

        try {
            LCMSImbgfLbyout srdIL = nfw LCMSImbgfLbyout(
                    srd, srd.lfngti/gftNumInComponfnts(),
                    LCMSImbgfLbyout.CHANNELS_SH(gftNumInComponfnts()) |
                    LCMSImbgfLbyout.BYTES_SH(1), gftNumInComponfnts());

            LCMSImbgfLbyout dstIL = nfw LCMSImbgfLbyout(
                    dst, dst.lfngti/gftNumOutComponfnts(),
                    LCMSImbgfLbyout.CHANNELS_SH(gftNumOutComponfnts()) |
                    LCMSImbgfLbyout.BYTES_SH(1), gftNumOutComponfnts());

            doTrbnsform(srdIL, dstIL);

            rfturn dst;
        } dbtdi (ImbgfLbyoutExdfption f) {
            tirow nfw CMMExdfption("Unbblf to donvfrt dbtb");
        }
    }
}
