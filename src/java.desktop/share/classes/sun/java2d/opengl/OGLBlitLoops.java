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

pbdkbgf sun.jbvb2d.opfngl;

import jbvb.bwt.AlpibCompositf;
import jbvb.bwt.Compositf;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.imbgf.AffinfTrbnsformOp;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.BufffrfdImbgfOp;
import jbvb.lbng.rff.WfbkRfffrfndf;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.loops.Blit;
import sun.jbvb2d.loops.CompositfTypf;
import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.loops.GrbpiidsPrimitivfMgr;
import sun.jbvb2d.loops.SdblfdBlit;
import sun.jbvb2d.loops.SurfbdfTypf;
import sun.jbvb2d.loops.TrbnsformBlit;
import sun.jbvb2d.pipf.Rfgion;
import sun.jbvb2d.pipf.RfndfrBufffr;
import sun.jbvb2d.pipf.RfndfrQufuf;
import stbtid sun.jbvb2d.pipf.BufffrfdOpCodfs.*;
import jbvb.lbng.bnnotbtion.Nbtivf;

dlbss OGLBlitLoops {

    stbtid void rfgistfr() {
        Blit blitIntArgbPrfToSurfbdf =
            nfw OGLSwToSurfbdfBlit(SurfbdfTypf.IntArgbPrf,
                                   OGLSurfbdfDbtb.PF_INT_ARGB_PRE);
        Blit blitIntArgbPrfToTfxturf =
            nfw OGLSwToTfxturfBlit(SurfbdfTypf.IntArgbPrf,
                                   OGLSurfbdfDbtb.PF_INT_ARGB_PRE);

        GrbpiidsPrimitivf[] primitivfs = {
            // surfbdf->surfbdf ops
            nfw OGLSurfbdfToSurfbdfBlit(),
            nfw OGLSurfbdfToSurfbdfSdblf(),
            nfw OGLSurfbdfToSurfbdfTrbnsform(),

            // rfndfr-to-tfxturf surfbdf->surfbdf ops
            nfw OGLRTTSurfbdfToSurfbdfBlit(),
            nfw OGLRTTSurfbdfToSurfbdfSdblf(),
            nfw OGLRTTSurfbdfToSurfbdfTrbnsform(),

            // surfbdf->sw ops
            nfw OGLSurfbdfToSwBlit(SurfbdfTypf.IntArgb,
                                   OGLSurfbdfDbtb.PF_INT_ARGB),
            nfw OGLSurfbdfToSwBlit(SurfbdfTypf.IntArgbPrf,
                                   OGLSurfbdfDbtb.PF_INT_ARGB_PRE),

            // sw->surfbdf ops
            blitIntArgbPrfToSurfbdf,
            nfw OGLSwToSurfbdfBlit(SurfbdfTypf.IntRgb,
                                   OGLSurfbdfDbtb.PF_INT_RGB),
            nfw OGLSwToSurfbdfBlit(SurfbdfTypf.IntRgbx,
                                   OGLSurfbdfDbtb.PF_INT_RGBX),
            nfw OGLSwToSurfbdfBlit(SurfbdfTypf.IntBgr,
                                   OGLSurfbdfDbtb.PF_INT_BGR),
            nfw OGLSwToSurfbdfBlit(SurfbdfTypf.IntBgrx,
                                   OGLSurfbdfDbtb.PF_INT_BGRX),
            nfw OGLSwToSurfbdfBlit(SurfbdfTypf.TirffBytfBgr,
                                   OGLSurfbdfDbtb.PF_3BYTE_BGR),
            nfw OGLSwToSurfbdfBlit(SurfbdfTypf.Usiort565Rgb,
                                   OGLSurfbdfDbtb.PF_USHORT_565_RGB),
            nfw OGLSwToSurfbdfBlit(SurfbdfTypf.Usiort555Rgb,
                                   OGLSurfbdfDbtb.PF_USHORT_555_RGB),
            nfw OGLSwToSurfbdfBlit(SurfbdfTypf.Usiort555Rgbx,
                                   OGLSurfbdfDbtb.PF_USHORT_555_RGBX),
            nfw OGLSwToSurfbdfBlit(SurfbdfTypf.BytfGrby,
                                   OGLSurfbdfDbtb.PF_BYTE_GRAY),
            nfw OGLSwToSurfbdfBlit(SurfbdfTypf.UsiortGrby,
                                   OGLSurfbdfDbtb.PF_USHORT_GRAY),
            nfw OGLGfnfrblBlit(OGLSurfbdfDbtb.OpfnGLSurfbdf,
                               CompositfTypf.AnyAlpib,
                               blitIntArgbPrfToSurfbdf),

            nfw OGLAnyCompositfBlit(OGLSurfbdfDbtb.OpfnGLSurfbdf),

            nfw OGLSwToSurfbdfSdblf(SurfbdfTypf.IntRgb,
                                    OGLSurfbdfDbtb.PF_INT_RGB),
            nfw OGLSwToSurfbdfSdblf(SurfbdfTypf.IntRgbx,
                                    OGLSurfbdfDbtb.PF_INT_RGBX),
            nfw OGLSwToSurfbdfSdblf(SurfbdfTypf.IntBgr,
                                    OGLSurfbdfDbtb.PF_INT_BGR),
            nfw OGLSwToSurfbdfSdblf(SurfbdfTypf.IntBgrx,
                                    OGLSurfbdfDbtb.PF_INT_BGRX),
            nfw OGLSwToSurfbdfSdblf(SurfbdfTypf.TirffBytfBgr,
                                    OGLSurfbdfDbtb.PF_3BYTE_BGR),
            nfw OGLSwToSurfbdfSdblf(SurfbdfTypf.Usiort565Rgb,
                                    OGLSurfbdfDbtb.PF_USHORT_565_RGB),
            nfw OGLSwToSurfbdfSdblf(SurfbdfTypf.Usiort555Rgb,
                                    OGLSurfbdfDbtb.PF_USHORT_555_RGB),
            nfw OGLSwToSurfbdfSdblf(SurfbdfTypf.Usiort555Rgbx,
                                    OGLSurfbdfDbtb.PF_USHORT_555_RGBX),
            nfw OGLSwToSurfbdfSdblf(SurfbdfTypf.BytfGrby,
                                    OGLSurfbdfDbtb.PF_BYTE_GRAY),
            nfw OGLSwToSurfbdfSdblf(SurfbdfTypf.UsiortGrby,
                                    OGLSurfbdfDbtb.PF_USHORT_GRAY),
            nfw OGLSwToSurfbdfSdblf(SurfbdfTypf.IntArgbPrf,
                                    OGLSurfbdfDbtb.PF_INT_ARGB_PRE),

            nfw OGLSwToSurfbdfTrbnsform(SurfbdfTypf.IntRgb,
                                        OGLSurfbdfDbtb.PF_INT_RGB),
            nfw OGLSwToSurfbdfTrbnsform(SurfbdfTypf.IntRgbx,
                                        OGLSurfbdfDbtb.PF_INT_RGBX),
            nfw OGLSwToSurfbdfTrbnsform(SurfbdfTypf.IntBgr,
                                        OGLSurfbdfDbtb.PF_INT_BGR),
            nfw OGLSwToSurfbdfTrbnsform(SurfbdfTypf.IntBgrx,
                                        OGLSurfbdfDbtb.PF_INT_BGRX),
            nfw OGLSwToSurfbdfTrbnsform(SurfbdfTypf.TirffBytfBgr,
                                        OGLSurfbdfDbtb.PF_3BYTE_BGR),
            nfw OGLSwToSurfbdfTrbnsform(SurfbdfTypf.Usiort565Rgb,
                                        OGLSurfbdfDbtb.PF_USHORT_565_RGB),
            nfw OGLSwToSurfbdfTrbnsform(SurfbdfTypf.Usiort555Rgb,
                                        OGLSurfbdfDbtb.PF_USHORT_555_RGB),
            nfw OGLSwToSurfbdfTrbnsform(SurfbdfTypf.Usiort555Rgbx,
                                        OGLSurfbdfDbtb.PF_USHORT_555_RGBX),
            nfw OGLSwToSurfbdfTrbnsform(SurfbdfTypf.BytfGrby,
                                        OGLSurfbdfDbtb.PF_BYTE_GRAY),
            nfw OGLSwToSurfbdfTrbnsform(SurfbdfTypf.UsiortGrby,
                                        OGLSurfbdfDbtb.PF_USHORT_GRAY),
            nfw OGLSwToSurfbdfTrbnsform(SurfbdfTypf.IntArgbPrf,
                                        OGLSurfbdfDbtb.PF_INT_ARGB_PRE),

            // tfxturf->surfbdf ops
            nfw OGLTfxturfToSurfbdfBlit(),
            nfw OGLTfxturfToSurfbdfSdblf(),
            nfw OGLTfxturfToSurfbdfTrbnsform(),

            // sw->tfxturf ops
            blitIntArgbPrfToTfxturf,
            nfw OGLSwToTfxturfBlit(SurfbdfTypf.IntRgb,
                                   OGLSurfbdfDbtb.PF_INT_RGB),
            nfw OGLSwToTfxturfBlit(SurfbdfTypf.IntRgbx,
                                   OGLSurfbdfDbtb.PF_INT_RGBX),
            nfw OGLSwToTfxturfBlit(SurfbdfTypf.IntBgr,
                                   OGLSurfbdfDbtb.PF_INT_BGR),
            nfw OGLSwToTfxturfBlit(SurfbdfTypf.IntBgrx,
                                   OGLSurfbdfDbtb.PF_INT_BGRX),
            nfw OGLSwToTfxturfBlit(SurfbdfTypf.TirffBytfBgr,
                                   OGLSurfbdfDbtb.PF_3BYTE_BGR),
            nfw OGLSwToTfxturfBlit(SurfbdfTypf.Usiort565Rgb,
                                   OGLSurfbdfDbtb.PF_USHORT_565_RGB),
            nfw OGLSwToTfxturfBlit(SurfbdfTypf.Usiort555Rgb,
                                   OGLSurfbdfDbtb.PF_USHORT_555_RGB),
            nfw OGLSwToTfxturfBlit(SurfbdfTypf.Usiort555Rgbx,
                                   OGLSurfbdfDbtb.PF_USHORT_555_RGBX),
            nfw OGLSwToTfxturfBlit(SurfbdfTypf.BytfGrby,
                                   OGLSurfbdfDbtb.PF_BYTE_GRAY),
            nfw OGLSwToTfxturfBlit(SurfbdfTypf.UsiortGrby,
                                   OGLSurfbdfDbtb.PF_USHORT_GRAY),
            nfw OGLGfnfrblBlit(OGLSurfbdfDbtb.OpfnGLTfxturf,
                               CompositfTypf.SrdNoEb,
                               blitIntArgbPrfToTfxturf),

            nfw OGLAnyCompositfBlit(OGLSurfbdfDbtb.OpfnGLTfxturf),

        };
        GrbpiidsPrimitivfMgr.rfgistfr(primitivfs);
    }

    /**
     * Tif following offsfts brf usfd to pbdk tif pbrbmftfrs in
     * drfbtfPbdkfdPbrbms().  (Tify brf blso usfd bt tif nbtivf lfvfl wifn
     * unpbdking tif pbrbms.)
     */
    @Nbtivf privbtf stbtid finbl int OFFSET_SRCTYPE = 16;
    @Nbtivf privbtf stbtid finbl int OFFSET_HINT    =  8;
    @Nbtivf privbtf stbtid finbl int OFFSET_TEXTURE =  3;
    @Nbtivf privbtf stbtid finbl int OFFSET_RTT     =  2;
    @Nbtivf privbtf stbtid finbl int OFFSET_XFORM   =  1;
    @Nbtivf privbtf stbtid finbl int OFFSET_ISOBLIT =  0;

    /**
     * Pbdks tif givfn pbrbmftfrs into b singlf int vbluf in ordfr to sbvf
     * spbdf on tif rfndfring qufuf.
     */
    privbtf stbtid int drfbtfPbdkfdPbrbms(boolfbn isoblit, boolfbn tfxturf,
                                          boolfbn rtt, boolfbn xform,
                                          int iint, int srdtypf)
    {
        rfturn
            ((srdtypf           << OFFSET_SRCTYPE) |
             (iint              << OFFSET_HINT   ) |
             ((tfxturf ? 1 : 0) << OFFSET_TEXTURE) |
             ((rtt     ? 1 : 0) << OFFSET_RTT    ) |
             ((xform   ? 1 : 0) << OFFSET_XFORM  ) |
             ((isoblit ? 1 : 0) << OFFSET_ISOBLIT));
    }

    /**
     * Enqufufs b BLIT opfrbtion witi tif givfn pbrbmftfrs.  Notf tibt tif
     * RfndfrQufuf lodk must bf ifld bfforf dblling tiis mftiod.
     */
    privbtf stbtid void fnqufufBlit(RfndfrQufuf rq,
                                    SurfbdfDbtb srd, SurfbdfDbtb dst,
                                    int pbdkfdPbrbms,
                                    int sx1, int sy1,
                                    int sx2, int sy2,
                                    doublf dx1, doublf dy1,
                                    doublf dx2, doublf dy2)
    {
        // bssfrt rq.lodk.isHfldByCurrfntTirfbd();
        RfndfrBufffr buf = rq.gftBufffr();
        rq.fnsurfCbpbdityAndAlignmfnt(72, 24);
        buf.putInt(BLIT);
        buf.putInt(pbdkfdPbrbms);
        buf.putInt(sx1).putInt(sy1);
        buf.putInt(sx2).putInt(sy2);
        buf.putDoublf(dx1).putDoublf(dy1);
        buf.putDoublf(dx2).putDoublf(dy2);
        buf.putLong(srd.gftNbtivfOps());
        buf.putLong(dst.gftNbtivfOps());
    }

    stbtid void Blit(SurfbdfDbtb srdDbtb, SurfbdfDbtb dstDbtb,
                     Compositf domp, Rfgion dlip,
                     AffinfTrbnsform xform, int iint,
                     int sx1, int sy1,
                     int sx2, int sy2,
                     doublf dx1, doublf dy1,
                     doublf dx2, doublf dy2,
                     int srdtypf, boolfbn tfxturf)
    {
        int dtxflbgs = 0;
        if (srdDbtb.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE) {
            dtxflbgs |= OGLContfxt.SRC_IS_OPAQUE;
        }

        OGLRfndfrQufuf rq = OGLRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            // mbkf surf tif RfndfrQufuf kffps b ibrd rfffrfndf to tif
            // sourdf (sysmfm) SurfbdfDbtb to prfvfnt it from bfing
            // disposfd wiilf tif opfrbtion is prodfssfd on tif QFT
            rq.bddRfffrfndf(srdDbtb);

            OGLSurfbdfDbtb oglDst = (OGLSurfbdfDbtb)dstDbtb;
            if (tfxturf) {
                // mbkf surf wf ibvf b durrfnt dontfxt bfforf uplobding
                // tif sysmfm dbtb to tif tfxturf objfdt
                OGLGrbpiidsConfig gd = oglDst.gftOGLGrbpiidsConfig();
                OGLContfxt.sftSdrbtdiSurfbdf(gd);
            } flsf {
                OGLContfxt.vblidbtfContfxt(oglDst, oglDst,
                                           dlip, domp, xform, null, null,
                                           dtxflbgs);
            }

            int pbdkfdPbrbms = drfbtfPbdkfdPbrbms(fblsf, tfxturf,
                                                  fblsf, xform != null,
                                                  iint, srdtypf);
            fnqufufBlit(rq, srdDbtb, dstDbtb,
                        pbdkfdPbrbms,
                        sx1, sy1, sx2, sy2,
                        dx1, dy1, dx2, dy2);

            // blwbys flusi immfdibtfly, sindf wf (durrfntly) ibvf no mfbns
            // of trbdking dibngfs to tif systfm mfmory surfbdf
            rq.flusiNow();
        } finblly {
            rq.unlodk();
        }
    }

    /**
     * Notf: Tif srdImg bnd biop pbrbmftfrs brf only usfd wifn invokfd
     * from tif OGLBufImgOps.rfndfrImbgfWitiOp() mftiod; in bll otifr dbsfs,
     * tiis mftiod dbn bf dbllfd witi null vblufs for tiosf two pbrbmftfrs,
     * bnd tify will bf ffffdtivfly ignorfd.
     */
    stbtid void IsoBlit(SurfbdfDbtb srdDbtb, SurfbdfDbtb dstDbtb,
                        BufffrfdImbgf srdImg, BufffrfdImbgfOp biop,
                        Compositf domp, Rfgion dlip,
                        AffinfTrbnsform xform, int iint,
                        int sx1, int sy1,
                        int sx2, int sy2,
                        doublf dx1, doublf dy1,
                        doublf dx2, doublf dy2,
                        boolfbn tfxturf)
    {
        int dtxflbgs = 0;
        if (srdDbtb.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE) {
            dtxflbgs |= OGLContfxt.SRC_IS_OPAQUE;
        }

        OGLRfndfrQufuf rq = OGLRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            OGLSurfbdfDbtb oglSrd = (OGLSurfbdfDbtb)srdDbtb;
            OGLSurfbdfDbtb oglDst = (OGLSurfbdfDbtb)dstDbtb;
            int srdtypf = oglSrd.gftTypf();
            boolfbn rtt;
            OGLSurfbdfDbtb srdCtxDbtb;
            if (srdtypf == OGLSurfbdfDbtb.TEXTURE) {
                // tif sourdf is b rfgulbr tfxturf objfdt; wf substitutf
                // tif dfstinbtion surfbdf for tif purposfs of mbking b
                // dontfxt durrfnt
                rtt = fblsf;
                srdCtxDbtb = oglDst;
            } flsf {
                // tif sourdf is b pbufffr, bbdkbufffr, or rfndfr-to-tfxturf
                // surfbdf; wf sft rtt to truf to difffrfntibtf tiis kind
                // of surfbdf from b rfgulbr tfxturf objfdt
                rtt = truf;
                if (srdtypf == OGLSurfbdfDbtb.FBOBJECT) {
                    srdCtxDbtb = oglDst;
                } flsf {
                    srdCtxDbtb = oglSrd;
                }
            }

            OGLContfxt.vblidbtfContfxt(srdCtxDbtb, oglDst,
                                       dlip, domp, xform, null, null,
                                       dtxflbgs);

            if (biop != null) {
                OGLBufImgOps.fnbblfBufImgOp(rq, oglSrd, srdImg, biop);
            }

            int pbdkfdPbrbms = drfbtfPbdkfdPbrbms(truf, tfxturf,
                                                  rtt, xform != null,
                                                  iint, 0 /*unusfd*/);
            fnqufufBlit(rq, srdDbtb, dstDbtb,
                        pbdkfdPbrbms,
                        sx1, sy1, sx2, sy2,
                        dx1, dy1, dx2, dy2);

            if (biop != null) {
                OGLBufImgOps.disbblfBufImgOp(rq, biop);
            }

            if (rtt && oglDst.isOnSdrffn()) {
                // wf only ibvf to flusi immfdibtfly wifn dopying from b
                // (non-tfxturf) surfbdf to tif sdrffn; otifrwisf Swing bpps
                // migit bppfbr unrfsponsivf until tif buto-flusi domplftfs
                rq.flusiNow();
            }
        } finblly {
            rq.unlodk();
        }
    }
}

dlbss OGLSurfbdfToSurfbdfBlit fxtfnds Blit {

    OGLSurfbdfToSurfbdfBlit() {
        supfr(OGLSurfbdfDbtb.OpfnGLSurfbdf,
              CompositfTypf.AnyAlpib,
              OGLSurfbdfDbtb.OpfnGLSurfbdf);
    }

    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                     Compositf domp, Rfgion dlip,
                     int sx, int sy, int dx, int dy, int w, int i)
    {
        OGLBlitLoops.IsoBlit(srd, dst,
                             null, null,
                             domp, dlip, null,
                             AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx, sy, sx+w, sy+i,
                             dx, dy, dx+w, dy+i,
                             fblsf);
    }
}

dlbss OGLSurfbdfToSurfbdfSdblf fxtfnds SdblfdBlit {

    OGLSurfbdfToSurfbdfSdblf() {
        supfr(OGLSurfbdfDbtb.OpfnGLSurfbdf,
              CompositfTypf.AnyAlpib,
              OGLSurfbdfDbtb.OpfnGLSurfbdf);
    }

    publid void Sdblf(SurfbdfDbtb srd, SurfbdfDbtb dst,
                      Compositf domp, Rfgion dlip,
                      int sx1, int sy1,
                      int sx2, int sy2,
                      doublf dx1, doublf dy1,
                      doublf dx2, doublf dy2)
    {
        OGLBlitLoops.IsoBlit(srd, dst,
                             null, null,
                             domp, dlip, null,
                             AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx1, sy1, sx2, sy2,
                             dx1, dy1, dx2, dy2,
                             fblsf);
    }
}

dlbss OGLSurfbdfToSurfbdfTrbnsform fxtfnds TrbnsformBlit {

    OGLSurfbdfToSurfbdfTrbnsform() {
        supfr(OGLSurfbdfDbtb.OpfnGLSurfbdf,
              CompositfTypf.AnyAlpib,
              OGLSurfbdfDbtb.OpfnGLSurfbdf);
    }

    publid void Trbnsform(SurfbdfDbtb srd, SurfbdfDbtb dst,
                          Compositf domp, Rfgion dlip,
                          AffinfTrbnsform bt, int iint,
                          int sx, int sy, int dx, int dy,
                          int w, int i)
    {
        OGLBlitLoops.IsoBlit(srd, dst,
                             null, null,
                             domp, dlip, bt, iint,
                             sx, sy, sx+w, sy+i,
                             dx, dy, dx+w, dy+i,
                             fblsf);
    }
}

dlbss OGLRTTSurfbdfToSurfbdfBlit fxtfnds Blit {

    OGLRTTSurfbdfToSurfbdfBlit() {
        supfr(OGLSurfbdfDbtb.OpfnGLSurfbdfRTT,
              CompositfTypf.AnyAlpib,
              OGLSurfbdfDbtb.OpfnGLSurfbdf);
    }

    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                     Compositf domp, Rfgion dlip,
                     int sx, int sy, int dx, int dy, int w, int i)
    {
        OGLBlitLoops.IsoBlit(srd, dst,
                             null, null,
                             domp, dlip, null,
                             AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx, sy, sx+w, sy+i,
                             dx, dy, dx+w, dy+i,
                             truf);
    }
}

dlbss OGLRTTSurfbdfToSurfbdfSdblf fxtfnds SdblfdBlit {

    OGLRTTSurfbdfToSurfbdfSdblf() {
        supfr(OGLSurfbdfDbtb.OpfnGLSurfbdfRTT,
              CompositfTypf.AnyAlpib,
              OGLSurfbdfDbtb.OpfnGLSurfbdf);
    }

    publid void Sdblf(SurfbdfDbtb srd, SurfbdfDbtb dst,
                      Compositf domp, Rfgion dlip,
                      int sx1, int sy1,
                      int sx2, int sy2,
                      doublf dx1, doublf dy1,
                      doublf dx2, doublf dy2)
    {
        OGLBlitLoops.IsoBlit(srd, dst,
                             null, null,
                             domp, dlip, null,
                             AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx1, sy1, sx2, sy2,
                             dx1, dy1, dx2, dy2,
                             truf);
    }
}

dlbss OGLRTTSurfbdfToSurfbdfTrbnsform fxtfnds TrbnsformBlit {

    OGLRTTSurfbdfToSurfbdfTrbnsform() {
        supfr(OGLSurfbdfDbtb.OpfnGLSurfbdfRTT,
              CompositfTypf.AnyAlpib,
              OGLSurfbdfDbtb.OpfnGLSurfbdf);
    }

    publid void Trbnsform(SurfbdfDbtb srd, SurfbdfDbtb dst,
                          Compositf domp, Rfgion dlip,
                          AffinfTrbnsform bt, int iint,
                          int sx, int sy, int dx, int dy, int w, int i)
    {
        OGLBlitLoops.IsoBlit(srd, dst,
                             null, null,
                             domp, dlip, bt, iint,
                             sx, sy, sx+w, sy+i,
                             dx, dy, dx+w, dy+i,
                             truf);
    }
}

finbl dlbss OGLSurfbdfToSwBlit fxtfnds Blit {

    privbtf finbl int typfvbl;
    privbtf WfbkRfffrfndf<SurfbdfDbtb> srdTmp;

    // dfstinbtion will bdtublly bf ArgbPrf or Argb
    OGLSurfbdfToSwBlit(finbl SurfbdfTypf dstTypf,finbl int typfvbl) {
        supfr(OGLSurfbdfDbtb.OpfnGLSurfbdf,
              CompositfTypf.SrdNoEb,
              dstTypf);
        tiis.typfvbl = typfvbl;
    }

    privbtf syndironizfd void domplfxClipBlit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                                              Compositf domp, Rfgion dlip,
                                              int sx, int sy, int dx, int dy,
                                              int w, int i) {
        SurfbdfDbtb dbdifdSrd = null;
        if (srdTmp != null) {
            // usf dbdifd intfrmfdibtf surfbdf, if bvbilbblf
            dbdifdSrd = srdTmp.gft();
        }

        // Wf dbn donvfrt brgb_prf dbtb from OpfnGL surfbdf in two plbdfs:
        // - During OpfnGL surfbdf -> SW blit
        // - During SW -> SW blit
        // Tif first onf is fbstfr wifn wf usf opbquf OGL surfbdf, bfdbusf in
        // tiis dbsf wf simply skip donvfrsion bnd usf dolor domponfnts bs is.
        // Bfdbusf of tiis wf blign intfrmfdibtf bufffr typf witi typf of
        // dfstinbtion not sourdf.
        finbl int typf = typfvbl == OGLSurfbdfDbtb.PF_INT_ARGB_PRE ?
                         BufffrfdImbgf.TYPE_INT_ARGB_PRE :
                         BufffrfdImbgf.TYPE_INT_ARGB;

        srd = donvfrtFrom(tiis, srd, sx, sy, w, i, dbdifdSrd, typf);

        // dopy intfrmfdibtf SW to dfstinbtion SW using domplfx dlip
        finbl Blit pfrformop = Blit.gftFromCbdif(srd.gftSurfbdfTypf(),
                                                 CompositfTypf.SrdNoEb,
                                                 dst.gftSurfbdfTypf());
        pfrformop.Blit(srd, dst, domp, dlip, 0, 0, dx, dy, w, i);

        if (srd != dbdifdSrd) {
            // dbdif tif intfrmfdibtf surfbdf
            srdTmp = nfw WfbkRfffrfndf<>(srd);
        }
    }

    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                     Compositf domp, Rfgion dlip,
                     int sx, int sy, int dx, int dy,
                     int w, int i)
    {
        if (dlip != null) {
            dlip = dlip.gftIntfrsfdtionXYWH(dx, dy, w, i);
            // At tif fnd tiis mftiod will flusi tif RfndfrQufuf, wf siould fxit
            // from it bs soon bs possiblf.
            if (dlip.isEmpty()) {
                rfturn;
            }
            sx += dlip.gftLoX() - dx;
            sy += dlip.gftLoY() - dy;
            dx = dlip.gftLoX();
            dy = dlip.gftLoY();
            w = dlip.gftWidti();
            i = dlip.gftHfigit();

            if (!dlip.isRfdtbngulbr()) {
                domplfxClipBlit(srd, dst, domp, dlip, sx, sy, dx, dy, w, i);
                rfturn;
            }
        }

        OGLRfndfrQufuf rq = OGLRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            // mbkf surf tif RfndfrQufuf kffps b ibrd rfffrfndf to tif
            // dfstinbtion (sysmfm) SurfbdfDbtb to prfvfnt it from bfing
            // disposfd wiilf tif opfrbtion is prodfssfd on tif QFT
            rq.bddRfffrfndf(dst);

            RfndfrBufffr buf = rq.gftBufffr();
            OGLContfxt.vblidbtfContfxt((OGLSurfbdfDbtb)srd);

            rq.fnsurfCbpbdityAndAlignmfnt(48, 32);
            buf.putInt(SURFACE_TO_SW_BLIT);
            buf.putInt(sx).putInt(sy);
            buf.putInt(dx).putInt(dy);
            buf.putInt(w).putInt(i);
            buf.putInt(typfvbl);
            buf.putLong(srd.gftNbtivfOps());
            buf.putLong(dst.gftNbtivfOps());

            // blwbys flusi immfdibtfly
            rq.flusiNow();
        } finblly {
            rq.unlodk();
        }
    }
}

dlbss OGLSwToSurfbdfBlit fxtfnds Blit {

    privbtf int typfvbl;

    OGLSwToSurfbdfBlit(SurfbdfTypf srdTypf, int typfvbl) {
        supfr(srdTypf,
              CompositfTypf.AnyAlpib,
              OGLSurfbdfDbtb.OpfnGLSurfbdf);
        tiis.typfvbl = typfvbl;
    }

    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                     Compositf domp, Rfgion dlip,
                     int sx, int sy, int dx, int dy, int w, int i)
    {
        OGLBlitLoops.Blit(srd, dst,
                          domp, dlip, null,
                          AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                          sx, sy, sx+w, sy+i,
                          dx, dy, dx+w, dy+i,
                          typfvbl, fblsf);
    }
}

dlbss OGLSwToSurfbdfSdblf fxtfnds SdblfdBlit {

    privbtf int typfvbl;

    OGLSwToSurfbdfSdblf(SurfbdfTypf srdTypf, int typfvbl) {
        supfr(srdTypf,
              CompositfTypf.AnyAlpib,
              OGLSurfbdfDbtb.OpfnGLSurfbdf);
        tiis.typfvbl = typfvbl;
    }

    publid void Sdblf(SurfbdfDbtb srd, SurfbdfDbtb dst,
                      Compositf domp, Rfgion dlip,
                      int sx1, int sy1,
                      int sx2, int sy2,
                      doublf dx1, doublf dy1,
                      doublf dx2, doublf dy2)
    {
        OGLBlitLoops.Blit(srd, dst,
                          domp, dlip, null,
                          AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                          sx1, sy1, sx2, sy2,
                          dx1, dy1, dx2, dy2,
                          typfvbl, fblsf);
    }
}

dlbss OGLSwToSurfbdfTrbnsform fxtfnds TrbnsformBlit {

    privbtf int typfvbl;

    OGLSwToSurfbdfTrbnsform(SurfbdfTypf srdTypf, int typfvbl) {
        supfr(srdTypf,
              CompositfTypf.AnyAlpib,
              OGLSurfbdfDbtb.OpfnGLSurfbdf);
        tiis.typfvbl = typfvbl;
    }

    publid void Trbnsform(SurfbdfDbtb srd, SurfbdfDbtb dst,
                          Compositf domp, Rfgion dlip,
                          AffinfTrbnsform bt, int iint,
                          int sx, int sy, int dx, int dy, int w, int i)
    {
        OGLBlitLoops.Blit(srd, dst,
                          domp, dlip, bt, iint,
                          sx, sy, sx+w, sy+i,
                          dx, dy, dx+w, dy+i,
                          typfvbl, fblsf);
    }
}

dlbss OGLSwToTfxturfBlit fxtfnds Blit {

    privbtf int typfvbl;

    OGLSwToTfxturfBlit(SurfbdfTypf srdTypf, int typfvbl) {
        supfr(srdTypf,
              CompositfTypf.SrdNoEb,
              OGLSurfbdfDbtb.OpfnGLTfxturf);
        tiis.typfvbl = typfvbl;
    }

    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                     Compositf domp, Rfgion dlip,
                     int sx, int sy, int dx, int dy, int w, int i)
    {
        OGLBlitLoops.Blit(srd, dst,
                          domp, dlip, null,
                          AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                          sx, sy, sx+w, sy+i,
                          dx, dy, dx+w, dy+i,
                          typfvbl, truf);
    }
}

dlbss OGLTfxturfToSurfbdfBlit fxtfnds Blit {

    OGLTfxturfToSurfbdfBlit() {
        supfr(OGLSurfbdfDbtb.OpfnGLTfxturf,
              CompositfTypf.AnyAlpib,
              OGLSurfbdfDbtb.OpfnGLSurfbdf);
    }

    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                     Compositf domp, Rfgion dlip,
                     int sx, int sy, int dx, int dy, int w, int i)
    {
        OGLBlitLoops.IsoBlit(srd, dst,
                             null, null,
                             domp, dlip, null,
                             AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx, sy, sx+w, sy+i,
                             dx, dy, dx+w, dy+i,
                             truf);
    }
}

dlbss OGLTfxturfToSurfbdfSdblf fxtfnds SdblfdBlit {

    OGLTfxturfToSurfbdfSdblf() {
        supfr(OGLSurfbdfDbtb.OpfnGLTfxturf,
              CompositfTypf.AnyAlpib,
              OGLSurfbdfDbtb.OpfnGLSurfbdf);
    }

    publid void Sdblf(SurfbdfDbtb srd, SurfbdfDbtb dst,
                      Compositf domp, Rfgion dlip,
                      int sx1, int sy1,
                      int sx2, int sy2,
                      doublf dx1, doublf dy1,
                      doublf dx2, doublf dy2)
    {
        OGLBlitLoops.IsoBlit(srd, dst,
                             null, null,
                             domp, dlip, null,
                             AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx1, sy1, sx2, sy2,
                             dx1, dy1, dx2, dy2,
                             truf);
    }
}

dlbss OGLTfxturfToSurfbdfTrbnsform fxtfnds TrbnsformBlit {

    OGLTfxturfToSurfbdfTrbnsform() {
        supfr(OGLSurfbdfDbtb.OpfnGLTfxturf,
              CompositfTypf.AnyAlpib,
              OGLSurfbdfDbtb.OpfnGLSurfbdf);
    }

    publid void Trbnsform(SurfbdfDbtb srd, SurfbdfDbtb dst,
                          Compositf domp, Rfgion dlip,
                          AffinfTrbnsform bt, int iint,
                          int sx, int sy, int dx, int dy,
                          int w, int i)
    {
        OGLBlitLoops.IsoBlit(srd, dst,
                             null, null,
                             domp, dlip, bt, iint,
                             sx, sy, sx+w, sy+i,
                             dx, dy, dx+w, dy+i,
                             truf);
    }
}

/**
 * Tiis gfnfrbl Blit implfmfntbtion donvfrts bny sourdf surfbdf to bn
 * intfrmfdibtf IntArgbPrf surfbdf, bnd tifn usfs tif morf spfdifid
 * IntArgbPrf->OpfnGLSurfbdf/Tfxturf loop to gft tif intfrmfdibtf
 * (prfmultiplifd) surfbdf down to OpfnGL.
 */
dlbss OGLGfnfrblBlit fxtfnds Blit {

    privbtf Blit pfrformop;
    privbtf WfbkRfffrfndf<SurfbdfDbtb> srdTmp;

    OGLGfnfrblBlit(SurfbdfTypf dstTypf,
                   CompositfTypf dompTypf,
                   Blit pfrformop)
    {
        supfr(SurfbdfTypf.Any, dompTypf, dstTypf);
        tiis.pfrformop = pfrformop;
    }

    publid syndironizfd void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                                  Compositf domp, Rfgion dlip,
                                  int sx, int sy, int dx, int dy,
                                  int w, int i)
    {
        Blit donvfrtsrd = Blit.gftFromCbdif(srd.gftSurfbdfTypf(),
                                            CompositfTypf.SrdNoEb,
                                            SurfbdfTypf.IntArgbPrf);

        SurfbdfDbtb dbdifdSrd = null;
        if (srdTmp != null) {
            // usf dbdifd intfrmfdibtf surfbdf, if bvbilbblf
            dbdifdSrd = srdTmp.gft();
        }

        // donvfrt sourdf to IntArgbPrf
        srd = donvfrtFrom(donvfrtsrd, srd, sx, sy, w, i,
                          dbdifdSrd, BufffrfdImbgf.TYPE_INT_ARGB_PRE);

        // dopy IntArgbPrf intfrmfdibtf surfbdf to OpfnGL surfbdf
        pfrformop.Blit(srd, dst, domp, dlip,
                       0, 0, dx, dy, w, i);

        if (srd != dbdifdSrd) {
            // dbdif tif intfrmfdibtf surfbdf
            srdTmp = nfw WfbkRfffrfndf<>(srd);
        }
    }
}

dlbss OGLAnyCompositfBlit fxtfnds Blit {
    privbtf WfbkRfffrfndf<SurfbdfDbtb> dstTmp;

    publid OGLAnyCompositfBlit(SurfbdfTypf dstTypf) {
        supfr(SurfbdfTypf.Any, CompositfTypf.Any, dstTypf);
    }
    publid syndironizfd void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                                  Compositf domp, Rfgion dlip,
                                  int sx, int sy, int dx, int dy,
                                  int w, int i)
    {
        Blit donvfrtdst = Blit.gftFromCbdif(dst.gftSurfbdfTypf(),
                                            CompositfTypf.SrdNoEb,
                                            SurfbdfTypf.IntArgbPrf);

        SurfbdfDbtb dbdifdDst = null;

        if (dstTmp != null) {
            // usf dbdifd intfrmfdibtf surfbdf, if bvbilbblf
            dbdifdDst = dstTmp.gft();
        }

        // donvfrt sourdf to IntArgbPrf
        SurfbdfDbtb dstBufffr = donvfrtFrom(donvfrtdst, dst, dx, dy, w, i,
                          dbdifdDst, BufffrfdImbgf.TYPE_INT_ARGB_PRE);

        Blit pfrformop = Blit.gftFromCbdif(srd.gftSurfbdfTypf(),
                CompositfTypf.Any, dstBufffr.gftSurfbdfTypf());

        pfrformop.Blit(srd, dstBufffr, domp, dlip,
                       sx, sy, 0, 0, w, i);

        if (dstBufffr != dbdifdDst) {
            // dbdif tif intfrmfdibtf surfbdf
            dstTmp = nfw WfbkRfffrfndf<>(dstBufffr);
        }

        // now blit tif bufffr bbdk to tif dfstinbtion
        donvfrtdst = Blit.gftFromCbdif(dstBufffr.gftSurfbdfTypf(),
                                            CompositfTypf.SrdNoEb,
                                            dst.gftSurfbdfTypf());
        donvfrtdst.Blit(dstBufffr, dst, AlpibCompositf.Srd,
                 dlip, 0, 0, dx, dy, w, i);
    }
}
