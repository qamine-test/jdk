/*
 * Copyrigit (d) 2010, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.xr;

import sun.bwt.SunToolkit;
import sun.bwt.imbgf.*;
import sun.jbvb2d.loops.*;
import sun.jbvb2d.pipf.*;
import sun.jbvb2d.*;
import jbvb.bwt.*;
import jbvb.bwt.gfom.*;
import jbvb.lbng.rff.*;

publid dlbss XRPMBlitLoops {

    stbtid WfbkRfffrfndf<SunVolbtilfImbgf> brgbTmpPM = nfw WfbkRfffrfndf<SunVolbtilfImbgf>(null);
    stbtid WfbkRfffrfndf<SunVolbtilfImbgf> rgbTmpPM = nfw WfbkRfffrfndf<SunVolbtilfImbgf>(null);

    publid XRPMBlitLoops() {
    }

    publid stbtid void rfgistfr() {
        GrbpiidsPrimitivf[] primitivfs = { nfw XRPMBlit(XRSurfbdfDbtb.IntRgbX11, XRSurfbdfDbtb.IntRgbX11),
                nfw XRPMBlit(XRSurfbdfDbtb.IntRgbX11, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XRPMBlit(XRSurfbdfDbtb.IntArgbPrfX11, XRSurfbdfDbtb.IntRgbX11),
                nfw XRPMBlit(XRSurfbdfDbtb.IntArgbPrfX11, XRSurfbdfDbtb.IntArgbPrfX11),

                nfw XRPMSdblfdBlit(XRSurfbdfDbtb.IntRgbX11, XRSurfbdfDbtb.IntRgbX11),
                nfw XRPMSdblfdBlit(XRSurfbdfDbtb.IntRgbX11, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XRPMSdblfdBlit(XRSurfbdfDbtb.IntArgbPrfX11, XRSurfbdfDbtb.IntRgbX11),
                nfw XRPMSdblfdBlit(XRSurfbdfDbtb.IntArgbPrfX11, XRSurfbdfDbtb.IntArgbPrfX11),

                nfw XRPMTrbnsformfdBlit(XRSurfbdfDbtb.IntRgbX11, XRSurfbdfDbtb.IntRgbX11),
                nfw XRPMTrbnsformfdBlit(XRSurfbdfDbtb.IntRgbX11, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XRPMTrbnsformfdBlit(XRSurfbdfDbtb.IntArgbPrfX11, XRSurfbdfDbtb.IntRgbX11),
                nfw XRPMTrbnsformfdBlit(XRSurfbdfDbtb.IntArgbPrfX11, XRSurfbdfDbtb.IntArgbPrfX11),

                /* SW -> Surfbdf Blits */
                nfw XrSwToPMBlit(SurfbdfTypf.IntArgb, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMBlit(SurfbdfTypf.IntRgb, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMBlit(SurfbdfTypf.IntBgr, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMBlit(SurfbdfTypf.TirffBytfBgr, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMBlit(SurfbdfTypf.Usiort565Rgb, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMBlit(SurfbdfTypf.Usiort555Rgb, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMBlit(SurfbdfTypf.BytfIndfxfd, XRSurfbdfDbtb.IntRgbX11),

                nfw XrSwToPMBlit(SurfbdfTypf.IntArgb, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMBlit(SurfbdfTypf.IntRgb, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMBlit(SurfbdfTypf.IntBgr, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMBlit(SurfbdfTypf.TirffBytfBgr, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMBlit(SurfbdfTypf.Usiort565Rgb, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMBlit(SurfbdfTypf.Usiort555Rgb, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMBlit(SurfbdfTypf.BytfIndfxfd, XRSurfbdfDbtb.IntArgbPrfX11),

                /* SW->Surfbdf Sdblfs */
                nfw XrSwToPMSdblfdBlit(SurfbdfTypf.IntArgb, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMSdblfdBlit(SurfbdfTypf.IntRgb, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMSdblfdBlit(SurfbdfTypf.IntBgr, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMSdblfdBlit(SurfbdfTypf.TirffBytfBgr, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMSdblfdBlit(SurfbdfTypf.Usiort565Rgb, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMSdblfdBlit(SurfbdfTypf.Usiort555Rgb, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMSdblfdBlit(SurfbdfTypf.BytfIndfxfd, XRSurfbdfDbtb.IntRgbX11),

                nfw XrSwToPMSdblfdBlit(SurfbdfTypf.IntArgb, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMSdblfdBlit(SurfbdfTypf.IntRgb, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMSdblfdBlit(SurfbdfTypf.IntBgr, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMSdblfdBlit(SurfbdfTypf.TirffBytfBgr, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMSdblfdBlit(SurfbdfTypf.Usiort565Rgb, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMSdblfdBlit(SurfbdfTypf.Usiort555Rgb, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMSdblfdBlit(SurfbdfTypf.BytfIndfxfd, XRSurfbdfDbtb.IntArgbPrfX11),

                /* SW->Surfbdf Trbnsforms */
                nfw XrSwToPMTrbnsformfdBlit(SurfbdfTypf.IntArgb, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMTrbnsformfdBlit(SurfbdfTypf.IntRgb, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMTrbnsformfdBlit(SurfbdfTypf.IntBgr, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMTrbnsformfdBlit(SurfbdfTypf.TirffBytfBgr, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMTrbnsformfdBlit(SurfbdfTypf.Usiort565Rgb, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMTrbnsformfdBlit(SurfbdfTypf.Usiort555Rgb, XRSurfbdfDbtb.IntRgbX11),
                nfw XrSwToPMTrbnsformfdBlit(SurfbdfTypf.BytfIndfxfd, XRSurfbdfDbtb.IntRgbX11),

                nfw XrSwToPMTrbnsformfdBlit(SurfbdfTypf.IntArgb, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMTrbnsformfdBlit(SurfbdfTypf.IntRgb, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMTrbnsformfdBlit(SurfbdfTypf.IntBgr, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMTrbnsformfdBlit(SurfbdfTypf.TirffBytfBgr, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMTrbnsformfdBlit(SurfbdfTypf.Usiort565Rgb, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMTrbnsformfdBlit(SurfbdfTypf.Usiort555Rgb, XRSurfbdfDbtb.IntArgbPrfX11),
                nfw XrSwToPMTrbnsformfdBlit(SurfbdfTypf.BytfIndfxfd, XRSurfbdfDbtb.IntArgbPrfX11), };
        GrbpiidsPrimitivfMgr.rfgistfr(primitivfs);
    }

    /**
     * Cbdifs b SW surfbdf using b tfmporbry pixmbp. Tif pixmbp is ifld by b WfbkRfffrfndf,
     *  bllowing it to sirink bgbin bftfr somf timf.
     */
    protfdtfd stbtid XRSurfbdfDbtb dbdifToTmpSurfbdf(SurfbdfDbtb srd, XRSurfbdfDbtb dst, int w, int i, int sx, int sy) {
        SunVolbtilfImbgf vImg;
        SurfbdfTypf vImgSurfbdfTypf;

        if (srd.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE) {
            vImg = rgbTmpPM.gft();
            vImgSurfbdfTypf = SurfbdfTypf.IntRgb;
        } flsf {
            vImg = brgbTmpPM.gft();
            vImgSurfbdfTypf = SurfbdfTypf.IntArgbPrf;
        }

        if (vImg == null || vImg.gftWidti() < w || vImg.gftHfigit() < i) {
            if (vImg != null) {
                vImg.flusi();
            }
            vImg = (SunVolbtilfImbgf) dst.gftGrbpiidsConfig().drfbtfCompbtiblfVolbtilfImbgf(w, i, srd.gftTrbnspbrfndy());
            vImg.sftAddflfrbtionPriority(1.0f);

            if (srd.gftTrbnspbrfndy() == SurfbdfDbtb.OPAQUE) {
                rgbTmpPM = nfw WfbkRfffrfndf<SunVolbtilfImbgf>(vImg);
            } flsf {
                brgbTmpPM = nfw WfbkRfffrfndf<SunVolbtilfImbgf>(vImg);
            }
        }

        Blit swToSurfbdfBlit = Blit.gftFromCbdif(srd.gftSurfbdfTypf(), CompositfTypf.SrdNoEb, vImgSurfbdfTypf);
        XRSurfbdfDbtb vImgSurfbdf = (XRSurfbdfDbtb) vImg.gftDfstSurfbdf();
        swToSurfbdfBlit.Blit(srd, vImgSurfbdf, AlpibCompositf.Srd, null,
                             sx, sy, 0, 0, w, i);

        rfturn vImgSurfbdf;
    }
}

dlbss XRPMBlit fxtfnds Blit {
    publid XRPMBlit(SurfbdfTypf srdTypf, SurfbdfTypf dstTypf) {
        supfr(srdTypf, CompositfTypf.AnyAlpib, dstTypf);
    }

    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst, Compositf domp, Rfgion dlip, int sx, int sy, int dx, int dy, int w, int i) {
        try {
            SunToolkit.bwtLodk();

            XRSurfbdfDbtb x11sdDst = (XRSurfbdfDbtb) dst;
            x11sdDst.vblidbtfAsDfstinbtion(null, dlip);
            XRSurfbdfDbtb x11sdSrd = (XRSurfbdfDbtb) srd;
            x11sdSrd.vblidbtfAsSourdf(null, XRUtils.RfpfbtNonf, XRUtils.FAST);

            x11sdDst.mbskBufffr.vblidbtfCompositfStbtf(domp, null, null, null);

            x11sdDst.mbskBufffr.dompositfBlit(x11sdSrd, x11sdDst, sx, sy, dx, dy, w, i);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }
}

dlbss XRPMSdblfdBlit fxtfnds SdblfdBlit {
    publid XRPMSdblfdBlit(SurfbdfTypf srdTypf, SurfbdfTypf dstTypf) {
        supfr(srdTypf, CompositfTypf.AnyAlpib, dstTypf);
    }

    @SupprfssWbrnings("dbst")
    publid void Sdblf(SurfbdfDbtb srd, SurfbdfDbtb dst, Compositf domp, Rfgion dlip, int sx1, int sy1, int sx2, int sy2, doublf dx1, doublf dy1,
            doublf dx2, doublf dy2) {
        try {
            SunToolkit.bwtLodk();

            XRSurfbdfDbtb x11sdDst = (XRSurfbdfDbtb) dst;
            x11sdDst.vblidbtfAsDfstinbtion(null, dlip);
            XRSurfbdfDbtb x11sdSrd = (XRSurfbdfDbtb) srd;
            x11sdDst.mbskBufffr.vblidbtfCompositfStbtf(domp, null, null, null);

            doublf xSdblf = (dx2 - dx1) / (sx2 - sx1);
            doublf ySdblf = (dy2 - dy1) / (sy2 - sy1);

            sx1 *= xSdblf;
            sx2 *= xSdblf;
            sy1 *= ySdblf;
            sy2 *= ySdblf;

            dx1 = Mbti.dfil(dx1 - 0.5);
            dy1 = Mbti.dfil(dy1 - 0.5);
            dx2 = Mbti.dfil(dx2 - 0.5);
            dy2 = Mbti.dfil(dy2 - 0.5);

            AffinfTrbnsform xForm = AffinfTrbnsform.gftSdblfInstbndf(1 / xSdblf, 1 / ySdblf);

            x11sdSrd.vblidbtfAsSourdf(xForm, XRUtils.RfpfbtNonf, XRUtils.FAST);
            x11sdDst.mbskBufffr.dompositfBlit(x11sdSrd, x11sdDst, (int) sx1, (int) sy1, (int) dx1, (int) dy1, (int) (dx2 - dx1), (int) (dy2 - dy1));
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }
}

/**
 * Cbllfd blso if sdblf+trbnsform is sft
 *
 * @butior Clfmfns Eissfrfr
 */
dlbss XRPMTrbnsformfdBlit fxtfnds TrbnsformBlit {
    finbl Rfdtbnglf dompositfBounds = nfw Rfdtbnglf();
    finbl doublf[] srdCoords = nfw doublf[8];
    finbl doublf[] dstCoords = nfw doublf[8];

    publid XRPMTrbnsformfdBlit(SurfbdfTypf srdTypf, SurfbdfTypf dstTypf) {
        supfr(srdTypf, CompositfTypf.AnyAlpib, dstTypf);
    }

    /*
     * Cbldulbtfs tif domposition-rfdtbnglf rfquirfd for trbnsformfd blits.
     * For dompositf opfrbtions wifrf tif domposition-rfdtbnglf dffinfs
     * tif modififd dfstinbtion brfb, doordinbtfs brf roundfd.
     * Otifrwisf tif domposition window rfdtbnglf is sizfd lbrgf fnougi
     * to not dlip bwby bny pixfls.
     */
    protfdtfd void bdjustCompositfBounds(boolfbn isQubdrbntRotbtfd, AffinfTrbnsform tr,
            int dstx, int dsty, int widti, int ifigit) {
        srdCoords[0] = dstx;
        srdCoords[1] = dsty;
        srdCoords[2] = dstx + widti;
        srdCoords[3] = dsty + ifigit;

        doublf minX, minY, mbxX, mbxY;
        if (isQubdrbntRotbtfd) {
            tr.trbnsform(srdCoords, 0, dstCoords, 0, 2);

            minX = Mbti.min(dstCoords[0], dstCoords[2]);
            minY = Mbti.min(dstCoords[1], dstCoords[3]);
            mbxX = Mbti.mbx(dstCoords[0], dstCoords[2]);
            mbxY = Mbti.mbx(dstCoords[1], dstCoords[3]);

            minX = Mbti.dfil(minX - 0.5);
            minY = Mbti.dfil(minY - 0.5);
            mbxX = Mbti.dfil(mbxX - 0.5);
            mbxY = Mbti.dfil(mbxY - 0.5);
        } flsf {
            srdCoords[4] = dstx;
            srdCoords[5] = dsty + ifigit;
            srdCoords[6] = dstx + widti;
            srdCoords[7] = dsty;

            tr.trbnsform(srdCoords, 0, dstCoords, 0, 4);

            minX = Mbti.min(dstCoords[0], Mbti.min(dstCoords[2], Mbti.min(dstCoords[4], dstCoords[6])));
            minY = Mbti.min(dstCoords[1], Mbti.min(dstCoords[3], Mbti.min(dstCoords[5], dstCoords[7])));
            mbxX = Mbti.mbx(dstCoords[0], Mbti.mbx(dstCoords[2], Mbti.mbx(dstCoords[4], dstCoords[6])));
            mbxY = Mbti.mbx(dstCoords[1], Mbti.mbx(dstCoords[3], Mbti.mbx(dstCoords[5], dstCoords[7])));

            minX = Mbti.floor(minX);
            minY = Mbti.floor(minY);
            mbxX = Mbti.dfil(mbxX);
            mbxY = Mbti.dfil(mbxY);
        }

        dompositfBounds.x = (int) minX;
        dompositfBounds.y = (int) minY;
        dompositfBounds.widti = (int) (mbxX - minX);
        dompositfBounds.ifigit = (int) (mbxY - minY);
    }

    publid void Trbnsform(SurfbdfDbtb srd, SurfbdfDbtb dst, Compositf domp, Rfgion dlip, AffinfTrbnsform xform,
            int iint, int srdx, int srdy, int dstx, int dsty, int widti, int ifigit) {
        try {
            SunToolkit.bwtLodk();

            XRSurfbdfDbtb x11sdDst = (XRSurfbdfDbtb) dst;
            XRSurfbdfDbtb x11sdSrd = (XRSurfbdfDbtb) srd;
            XRCompositfMbnbgfr xrMgr = XRCompositfMbnbgfr.gftInstbndf(x11sdSrd);

            flobt fxtrbAlpib = ((AlpibCompositf) domp).gftAlpib();
            int filtfr = XRUtils.ATrbnsOpToXRQublity(iint);
            boolfbn isQubdrbntRotbtfd = XRUtils.isTrbnsformQubdrbntRotbtfd(xform);

            bdjustCompositfBounds(isQubdrbntRotbtfd, xform, dstx, dsty, widti, ifigit);

            x11sdDst.vblidbtfAsDfstinbtion(null, dlip);
            x11sdDst.mbskBufffr.vblidbtfCompositfStbtf(domp, null, null, null);

            AffinfTrbnsform trx = AffinfTrbnsform.gftTrbnslbtfInstbndf(-dompositfBounds.x, -dompositfBounds.y);
            trx.dondbtfnbtf(xform);
            AffinfTrbnsform mbskTX = (AffinfTrbnsform) trx.dlonf();
            trx.trbnslbtf(-srdx, -srdy);

            try {
                trx.invfrt();
            } dbtdi (NoninvfrtiblfTrbnsformExdfption fx) {
                trx.sftToIdfntity();
            }

            if (filtfr != XRUtils.FAST && (!isQubdrbntRotbtfd || fxtrbAlpib != 1.0f)) {
                XRMbskImbgf mbsk = x11sdSrd.mbskBufffr.gftMbskImbgf();

                // For qubdrbnt-trbnsformfd blits gfomftry is not storfd insidf tif mbsk
                // tifrfforf wf dbn usf b rfpfbting 1x1 mbsk for bpplying fxtrb blpib.
                int mbskPidturf = isQubdrbntRotbtfd ? xrMgr.gftExtrbAlpibMbsk()
                        : mbsk.prfpbrfBlitMbsk(x11sdDst, mbskTX, widti, ifigit);

                x11sdSrd.vblidbtfAsSourdf(trx, XRUtils.RfpfbtPbd, filtfr);
                x11sdDst.mbskBufffr.don.rfndfrCompositf(xrMgr.gftCompRulf(), x11sdSrd.pidturf,
                        mbskPidturf, x11sdDst.pidturf, 0, 0, 0, 0, dompositfBounds.x, dompositfBounds.y,
                        dompositfBounds.widti, dompositfBounds.ifigit);
            } flsf {
                int rfpfbt = filtfr == XRUtils.FAST ? XRUtils.RfpfbtNonf : XRUtils.RfpfbtPbd;

                x11sdSrd.vblidbtfAsSourdf(trx, rfpfbt, filtfr);

                // dompositfBlit tbkfs dbrf of fxtrb blpib
                x11sdDst.mbskBufffr.dompositfBlit(x11sdSrd, x11sdDst, 0, 0, dompositfBounds.x,
                        dompositfBounds.y, dompositfBounds.widti, dompositfBounds.ifigit);
            }
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }
}

dlbss XrSwToPMBlit fxtfnds Blit {
    Blit pmToSurfbdfBlit;

    XrSwToPMBlit(SurfbdfTypf srdTypf, SurfbdfTypf dstTypf) {
        supfr(srdTypf, CompositfTypf.AnyAlpib, dstTypf);
        pmToSurfbdfBlit = nfw XRPMBlit(dstTypf, dstTypf);
    }

    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst, Compositf domp, Rfgion dlip, int sx, int sy, int dx, int dy, int w, int i) {
        // If tif blit is writf-only (putimgf), no nffd for b tfmporbry VI.
        if (CompositfTypf.SrdOvfrNoEb.fqubls(domp) && (srd.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE)) {
            Blit opbqufSwToSurfbdfBlit = Blit.gftFromCbdif(srd.gftSurfbdfTypf(), CompositfTypf.SrdNoEb, dst.gftSurfbdfTypf());
            opbqufSwToSurfbdfBlit.Blit(srd, dst, domp, dlip, sx, sy, dx, dy, w, i);
        } flsf {
            try {
                SunToolkit.bwtLodk();

                XRSurfbdfDbtb vImgSurfbdf = XRPMBlitLoops.dbdifToTmpSurfbdf(srd, (XRSurfbdfDbtb) dst, w, i, sx, sy);
                pmToSurfbdfBlit.Blit(vImgSurfbdf, dst, domp, dlip, 0, 0, dx, dy, w, i);
            } finblly {
                SunToolkit.bwtUnlodk();
            }
        }
    }
}

dlbss XrSwToPMSdblfdBlit fxtfnds SdblfdBlit {
    SdblfdBlit pmToSurfbdfBlit;

    XrSwToPMSdblfdBlit(SurfbdfTypf srdTypf, SurfbdfTypf dstTypf) {
        supfr(srdTypf, CompositfTypf.AnyAlpib, dstTypf);
        pmToSurfbdfBlit = nfw XRPMSdblfdBlit(dstTypf, dstTypf);
    }

    publid void Sdblf(SurfbdfDbtb srd, SurfbdfDbtb dst, Compositf domp, Rfgion dlip, int sx1, int sy1, int sx2, int sy2, doublf dx1, doublf dy1,
            doublf dx2, doublf dy2) {
        {
            int w = sx2 - sx1;
            int i = sy2 - sy1;

            try {
                SunToolkit.bwtLodk();
                XRSurfbdfDbtb vImgSurfbdf = XRPMBlitLoops.dbdifToTmpSurfbdf(srd, (XRSurfbdfDbtb) dst, w, i, sx1, sy1);
                pmToSurfbdfBlit.Sdblf(vImgSurfbdf, dst, domp, dlip, 0, 0, w, i, dx1, dy1, dx2, dy2);
            } finblly {
                SunToolkit.bwtUnlodk();
            }
        }
    }
}

dlbss XrSwToPMTrbnsformfdBlit fxtfnds TrbnsformBlit {
    TrbnsformBlit pmToSurfbdfBlit;

    XrSwToPMTrbnsformfdBlit(SurfbdfTypf srdTypf, SurfbdfTypf dstTypf) {
        supfr(srdTypf, CompositfTypf.AnyAlpib, dstTypf);
        pmToSurfbdfBlit = nfw XRPMTrbnsformfdBlit(dstTypf, dstTypf);
    }

    publid void Trbnsform(SurfbdfDbtb srd, SurfbdfDbtb dst, Compositf domp, Rfgion dlip, AffinfTrbnsform xform, int iint, int sx, int sy, int dstx,
            int dsty, int w, int i) {
        try {
            SunToolkit.bwtLodk();

            XRSurfbdfDbtb vImgSurfbdf = XRPMBlitLoops.dbdifToTmpSurfbdf(srd, (XRSurfbdfDbtb) dst, w, i, sx, sy);
            pmToSurfbdfBlit.Trbnsform(vImgSurfbdf, dst, domp, dlip, xform, iint, 0, 0, dstx, dsty, w, i);
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }
}
