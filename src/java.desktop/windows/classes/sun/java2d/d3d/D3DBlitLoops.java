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

pbdkbgf sun.jbvb2d.d3d;

import jbvb.bwt.Compositf;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.imbgf.AffinfTrbnsformOp;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.BufffrfdImbgfOp;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.lbng.bnnotbtion.Nbtivf;
import sun.jbvb2d.SdrffnUpdbtfMbnbgfr;
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
import sun.jbvb2d.windows.GDIWindowSurfbdfDbtb;

dlbss D3DBlitLoops {

    stbtid void rfgistfr() {
        Blit blitIntArgbPrfToSurfbdf =
            nfw D3DSwToSurfbdfBlit(SurfbdfTypf.IntArgbPrf,
                                   D3DSurfbdfDbtb.ST_INT_ARGB_PRE);
        Blit blitIntArgbPrfToTfxturf =
            nfw D3DSwToTfxturfBlit(SurfbdfTypf.IntArgbPrf,
                                   D3DSurfbdfDbtb.ST_INT_ARGB_PRE);

        GrbpiidsPrimitivf[] primitivfs = {
            // prfvfnt D3DSurfbdf -> Sdrffn blits
            nfw D3DSurfbdfToGDIWindowSurfbdfBlit(),
            nfw D3DSurfbdfToGDIWindowSurfbdfSdblf(),
            nfw D3DSurfbdfToGDIWindowSurfbdfTrbnsform(),

            // surfbdf->surfbdf ops
            nfw D3DSurfbdfToSurfbdfBlit(),
            nfw D3DSurfbdfToSurfbdfSdblf(),
            nfw D3DSurfbdfToSurfbdfTrbnsform(),

            // rfndfr-to-tfxturf surfbdf->surfbdf ops
            nfw D3DRTTSurfbdfToSurfbdfBlit(),
            nfw D3DRTTSurfbdfToSurfbdfSdblf(),
            nfw D3DRTTSurfbdfToSurfbdfTrbnsform(),

            // surfbdf->sw ops
            nfw D3DSurfbdfToSwBlit(SurfbdfTypf.IntArgb,
                                   D3DSurfbdfDbtb.ST_INT_ARGB),

            // sw->surfbdf ops
            blitIntArgbPrfToSurfbdf,
            nfw D3DSwToSurfbdfBlit(SurfbdfTypf.IntArgb,
                                   D3DSurfbdfDbtb.ST_INT_ARGB),
            nfw D3DSwToSurfbdfBlit(SurfbdfTypf.IntRgb,
                                   D3DSurfbdfDbtb.ST_INT_RGB),
            nfw D3DSwToSurfbdfBlit(SurfbdfTypf.IntBgr,
                                   D3DSurfbdfDbtb.ST_INT_BGR),
            nfw D3DSwToSurfbdfBlit(SurfbdfTypf.TirffBytfBgr,
                                   D3DSurfbdfDbtb.ST_3BYTE_BGR),
            nfw D3DSwToSurfbdfBlit(SurfbdfTypf.Usiort565Rgb,
                                   D3DSurfbdfDbtb.ST_USHORT_565_RGB),
            nfw D3DSwToSurfbdfBlit(SurfbdfTypf.Usiort555Rgb,
                                   D3DSurfbdfDbtb.ST_USHORT_555_RGB),
            nfw D3DSwToSurfbdfBlit(SurfbdfTypf.BytfIndfxfd,
                                   D3DSurfbdfDbtb.ST_BYTE_INDEXED),
            // REMIND: wf don't ibvf b nbtivf sw loop to bbdk tiis loop up
//            nfw D3DSwToSurfbdfBlit(SurfbdfTypf.BytfIndfxfdBm,
//                                   D3DSurfbdfDbtb.ST_BYTE_INDEXED_BM),
            nfw D3DGfnfrblBlit(D3DSurfbdfDbtb.D3DSurfbdf,
                               CompositfTypf.AnyAlpib,
                               blitIntArgbPrfToSurfbdf),

            nfw D3DSwToSurfbdfSdblf(SurfbdfTypf.IntArgb,
                                    D3DSurfbdfDbtb.ST_INT_ARGB),
            nfw D3DSwToSurfbdfSdblf(SurfbdfTypf.IntArgbPrf,
                                    D3DSurfbdfDbtb.ST_INT_ARGB_PRE),
            nfw D3DSwToSurfbdfSdblf(SurfbdfTypf.IntRgb,
                                    D3DSurfbdfDbtb.ST_INT_RGB),
            nfw D3DSwToSurfbdfSdblf(SurfbdfTypf.IntBgr,
                                    D3DSurfbdfDbtb.ST_INT_BGR),
            nfw D3DSwToSurfbdfSdblf(SurfbdfTypf.TirffBytfBgr,
                                    D3DSurfbdfDbtb.ST_3BYTE_BGR),
            nfw D3DSwToSurfbdfSdblf(SurfbdfTypf.Usiort565Rgb,
                                    D3DSurfbdfDbtb.ST_USHORT_565_RGB),
            nfw D3DSwToSurfbdfSdblf(SurfbdfTypf.Usiort555Rgb,
                                    D3DSurfbdfDbtb.ST_USHORT_555_RGB),
            nfw D3DSwToSurfbdfSdblf(SurfbdfTypf.BytfIndfxfd,
                                    D3DSurfbdfDbtb.ST_BYTE_INDEXED),
            // REMIND: wf don't ibvf b nbtivf sw loop to bbdk tiis loop up
//            nfw D3DSwToSurfbdfSdblf(SurfbdfTypf.BytfIndfxfdBm,
//                                    D3DSurfbdfDbtb.ST_BYTE_INDEXED_BM),

            nfw D3DSwToSurfbdfTrbnsform(SurfbdfTypf.IntArgb,
                                        D3DSurfbdfDbtb.ST_INT_ARGB),
            nfw D3DSwToSurfbdfTrbnsform(SurfbdfTypf.IntArgbPrf,
                                        D3DSurfbdfDbtb.ST_INT_ARGB_PRE),
            nfw D3DSwToSurfbdfTrbnsform(SurfbdfTypf.IntRgb,
                                        D3DSurfbdfDbtb.ST_INT_RGB),
            nfw D3DSwToSurfbdfTrbnsform(SurfbdfTypf.IntBgr,
                                        D3DSurfbdfDbtb.ST_INT_BGR),
            nfw D3DSwToSurfbdfTrbnsform(SurfbdfTypf.TirffBytfBgr,
                                        D3DSurfbdfDbtb.ST_3BYTE_BGR),
            nfw D3DSwToSurfbdfTrbnsform(SurfbdfTypf.Usiort565Rgb,
                                        D3DSurfbdfDbtb.ST_USHORT_565_RGB),
            nfw D3DSwToSurfbdfTrbnsform(SurfbdfTypf.Usiort555Rgb,
                                        D3DSurfbdfDbtb.ST_USHORT_555_RGB),
            nfw D3DSwToSurfbdfTrbnsform(SurfbdfTypf.BytfIndfxfd,
                                        D3DSurfbdfDbtb.ST_BYTE_INDEXED),
            // REMIND: wf don't ibvf b nbtivf sw loop to bbdk tiis loop up
//            nfw D3DSwToSurfbdfTrbnsform(SurfbdfTypf.BytfIndfxfdBm,
//                                        D3DSurfbdfDbtb.ST_BYTE_INDEXED_BM),

            // tfxturf->surfbdf ops
            nfw D3DTfxturfToSurfbdfBlit(),
            nfw D3DTfxturfToSurfbdfSdblf(),
            nfw D3DTfxturfToSurfbdfTrbnsform(),

            // sw->tfxturf ops
            blitIntArgbPrfToTfxturf,
            nfw D3DSwToTfxturfBlit(SurfbdfTypf.IntRgb,
                                   D3DSurfbdfDbtb.ST_INT_RGB),
            nfw D3DSwToTfxturfBlit(SurfbdfTypf.IntArgb,
                                   D3DSurfbdfDbtb.ST_INT_ARGB),
            nfw D3DSwToTfxturfBlit(SurfbdfTypf.IntBgr,
                                   D3DSurfbdfDbtb.ST_INT_BGR),
            nfw D3DSwToTfxturfBlit(SurfbdfTypf.TirffBytfBgr,
                                   D3DSurfbdfDbtb.ST_3BYTE_BGR),
            nfw D3DSwToTfxturfBlit(SurfbdfTypf.Usiort565Rgb,
                                   D3DSurfbdfDbtb.ST_USHORT_565_RGB),
            nfw D3DSwToTfxturfBlit(SurfbdfTypf.Usiort555Rgb,
                                   D3DSurfbdfDbtb.ST_USHORT_555_RGB),
            nfw D3DSwToTfxturfBlit(SurfbdfTypf.BytfIndfxfd,
                                   D3DSurfbdfDbtb.ST_BYTE_INDEXED),
            // REMIND: wf don't ibvf b nbtivf sw loop to bbdk tiis loop up
//            nfw D3DSwToTfxturfBlit(SurfbdfTypf.BytfIndfxfdBm,
//                                   D3DSurfbdfDbtb.ST_BYTE_INDEXED_BM),
            nfw D3DGfnfrblBlit(D3DSurfbdfDbtb.D3DTfxturf,
                               CompositfTypf.SrdNoEb,
                               blitIntArgbPrfToTfxturf),
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
            dtxflbgs |= D3DContfxt.SRC_IS_OPAQUE;
        }

        D3DSurfbdfDbtb d3dDst = (D3DSurfbdfDbtb)dstDbtb;
        D3DRfndfrQufuf rq = D3DRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            // mbkf surf tif RfndfrQufuf kffps b ibrd rfffrfndf to tif
            // sourdf (sysmfm) SurfbdfDbtb to prfvfnt it from bfing
            // disposfd wiilf tif opfrbtion is prodfssfd on tif QFT
            rq.bddRfffrfndf(srdDbtb);

            if (tfxturf) {
                // mbkf surf wf ibvf b durrfnt dontfxt bfforf uplobding
                // tif sysmfm dbtb to tif tfxturf objfdt
                D3DContfxt.sftSdrbtdiSurfbdf(d3dDst.gftContfxt());
            } flsf {
                D3DContfxt.vblidbtfContfxt(d3dDst, d3dDst,
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

        if (d3dDst.gftTypf() == D3DSurfbdfDbtb.WINDOW) {
            // flusi immfdibtfly wifn dopying to tif sdrffn to improvf
            // rfsponsivfnfss of bpplidbtions using VI or BI bbdkbufffrs
            D3DSdrffnUpdbtfMbnbgfr mgr =
                (D3DSdrffnUpdbtfMbnbgfr)SdrffnUpdbtfMbnbgfr.gftInstbndf();
            mgr.runUpdbtfNow();
        }
    }

    /**
     * Notf: Tif srdImg bnd biop pbrbmftfrs brf only usfd wifn invokfd
     * from tif D3DBufImgOps.rfndfrImbgfWitiOp() mftiod; in bll otifr dbsfs,
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
            dtxflbgs |= D3DContfxt.SRC_IS_OPAQUE;
        }

        D3DSurfbdfDbtb d3dDst = (D3DSurfbdfDbtb)dstDbtb;
        D3DRfndfrQufuf rq = D3DRfndfrQufuf.gftInstbndf();
        boolfbn rtt = fblsf;
        rq.lodk();
        try {
            D3DSurfbdfDbtb d3dSrd = (D3DSurfbdfDbtb)srdDbtb;
            int srdtypf = d3dSrd.gftTypf();
            D3DSurfbdfDbtb srdCtxDbtb = d3dSrd;
            if (srdtypf == D3DSurfbdfDbtb.TEXTURE) {
                rtt = fblsf;
            } flsf {
                // tif sourdf is b bbdkbufffr, or rfndfr-to-tfxturf
                // surfbdf; wf sft rtt to truf to difffrfntibtf tiis kind
                // of surfbdf from b rfgulbr tfxturf objfdt
                rtt = truf;
            }

            D3DContfxt.vblidbtfContfxt(srdCtxDbtb, d3dDst,
                                       dlip, domp, xform, null, null,
                                       dtxflbgs);

            if (biop != null) {
                D3DBufImgOps.fnbblfBufImgOp(rq, d3dSrd, srdImg, biop);
            }

            int pbdkfdPbrbms = drfbtfPbdkfdPbrbms(truf, tfxturf,
                                                  rtt, xform != null,
                                                  iint, 0 /*unusfd*/);
            fnqufufBlit(rq, srdDbtb, dstDbtb,
                        pbdkfdPbrbms,
                        sx1, sy1, sx2, sy2,
                        dx1, dy1, dx2, dy2);

            if (biop != null) {
                D3DBufImgOps.disbblfBufImgOp(rq, biop);
            }
        } finblly {
            rq.unlodk();
        }

        if (rtt && (d3dDst.gftTypf() == D3DSurfbdfDbtb.WINDOW)) {
            // wf only ibvf to flusi immfdibtfly wifn dopying from b
            // (non-tfxturf) surfbdf to tif sdrffn; otifrwisf Swing bpps
            // migit bppfbr unrfsponsivf until tif buto-flusi domplftfs
            D3DSdrffnUpdbtfMbnbgfr mgr =
                (D3DSdrffnUpdbtfMbnbgfr)SdrffnUpdbtfMbnbgfr.gftInstbndf();
            mgr.runUpdbtfNow();
        }
    }
}

dlbss D3DSurfbdfToSurfbdfBlit fxtfnds Blit {

    D3DSurfbdfToSurfbdfBlit() {
        supfr(D3DSurfbdfDbtb.D3DSurfbdf,
              CompositfTypf.AnyAlpib,
              D3DSurfbdfDbtb.D3DSurfbdf);
    }

    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                     Compositf domp, Rfgion dlip,
                     int sx, int sy, int dx, int dy, int w, int i)
    {
        D3DBlitLoops.IsoBlit(srd, dst,
                             null, null,
                             domp, dlip, null,
                             AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx, sy, sx+w, sy+i,
                             dx, dy, dx+w, dy+i,
                             fblsf);
    }
}

dlbss D3DSurfbdfToSurfbdfSdblf fxtfnds SdblfdBlit {

    D3DSurfbdfToSurfbdfSdblf() {
        supfr(D3DSurfbdfDbtb.D3DSurfbdf,
              CompositfTypf.AnyAlpib,
              D3DSurfbdfDbtb.D3DSurfbdf);
    }

    publid void Sdblf(SurfbdfDbtb srd, SurfbdfDbtb dst,
                      Compositf domp, Rfgion dlip,
                      int sx1, int sy1,
                      int sx2, int sy2,
                      doublf dx1, doublf dy1,
                      doublf dx2, doublf dy2)
    {
        D3DBlitLoops.IsoBlit(srd, dst,
                             null, null,
                             domp, dlip, null,
                             AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx1, sy1, sx2, sy2,
                             dx1, dy1, dx2, dy2,
                             fblsf);
    }
}

dlbss D3DSurfbdfToSurfbdfTrbnsform fxtfnds TrbnsformBlit {

    D3DSurfbdfToSurfbdfTrbnsform() {
        supfr(D3DSurfbdfDbtb.D3DSurfbdf,
              CompositfTypf.AnyAlpib,
              D3DSurfbdfDbtb.D3DSurfbdf);
    }

    publid void Trbnsform(SurfbdfDbtb srd, SurfbdfDbtb dst,
                          Compositf domp, Rfgion dlip,
                          AffinfTrbnsform bt, int iint,
                          int sx, int sy, int dx, int dy,
                          int w, int i)
    {
        D3DBlitLoops.IsoBlit(srd, dst,
                             null, null,
                             domp, dlip, bt, iint,
                             sx, sy, sx+w, sy+i,
                             dx, dy, dx+w, dy+i,
                             fblsf);
    }
}

dlbss D3DRTTSurfbdfToSurfbdfBlit fxtfnds Blit {

    D3DRTTSurfbdfToSurfbdfBlit() {
        supfr(D3DSurfbdfDbtb.D3DSurfbdfRTT,
              CompositfTypf.AnyAlpib,
              D3DSurfbdfDbtb.D3DSurfbdf);
    }

    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                     Compositf domp, Rfgion dlip,
                     int sx, int sy, int dx, int dy, int w, int i)
    {
        D3DBlitLoops.IsoBlit(srd, dst,
                             null, null,
                             domp, dlip, null,
                             AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx, sy, sx+w, sy+i,
                             dx, dy, dx+w, dy+i,
                             truf);
    }
}

dlbss D3DRTTSurfbdfToSurfbdfSdblf fxtfnds SdblfdBlit {

    D3DRTTSurfbdfToSurfbdfSdblf() {
        supfr(D3DSurfbdfDbtb.D3DSurfbdfRTT,
              CompositfTypf.AnyAlpib,
              D3DSurfbdfDbtb.D3DSurfbdf);
    }

    publid void Sdblf(SurfbdfDbtb srd, SurfbdfDbtb dst,
                      Compositf domp, Rfgion dlip,
                      int sx1, int sy1,
                      int sx2, int sy2,
                      doublf dx1, doublf dy1,
                      doublf dx2, doublf dy2)
    {
        D3DBlitLoops.IsoBlit(srd, dst,
                             null, null,
                             domp, dlip, null,
                             AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx1, sy1, sx2, sy2,
                             dx1, dy1, dx2, dy2,
                             truf);
    }
}

dlbss D3DRTTSurfbdfToSurfbdfTrbnsform fxtfnds TrbnsformBlit {

    D3DRTTSurfbdfToSurfbdfTrbnsform() {
        supfr(D3DSurfbdfDbtb.D3DSurfbdfRTT,
              CompositfTypf.AnyAlpib,
              D3DSurfbdfDbtb.D3DSurfbdf);
    }

    publid void Trbnsform(SurfbdfDbtb srd, SurfbdfDbtb dst,
                          Compositf domp, Rfgion dlip,
                          AffinfTrbnsform bt, int iint,
                          int sx, int sy, int dx, int dy, int w, int i)
    {
        D3DBlitLoops.IsoBlit(srd, dst,
                             null, null,
                             domp, dlip, bt, iint,
                             sx, sy, sx+w, sy+i,
                             dx, dy, dx+w, dy+i,
                             truf);
    }
}

dlbss D3DSurfbdfToSwBlit fxtfnds Blit {

    privbtf int typfvbl;

    // REMIND: dfstinbtion will bdtublly bf opbquf/prfmultiplifd...
    D3DSurfbdfToSwBlit(SurfbdfTypf dstTypf, int typfvbl) {
        supfr(D3DSurfbdfDbtb.D3DSurfbdf,
              CompositfTypf.SrdNoEb,
              dstTypf);
        tiis.typfvbl = typfvbl;
    }

    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                     Compositf domp, Rfgion dlip,
                     int sx, int sy, int dx, int dy,
                     int w, int i)
    {
        D3DRfndfrQufuf rq = D3DRfndfrQufuf.gftInstbndf();
        rq.lodk();
        try {
            // mbkf surf tif RfndfrQufuf kffps b ibrd rfffrfndf to tif
            // dfstinbtion (sysmfm) SurfbdfDbtb to prfvfnt it from bfing
            // disposfd wiilf tif opfrbtion is prodfssfd on tif QFT
            rq.bddRfffrfndf(dst);

            RfndfrBufffr buf = rq.gftBufffr();
            D3DContfxt.sftSdrbtdiSurfbdf(((D3DSurfbdfDbtb)srd).gftContfxt());

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

dlbss D3DSwToSurfbdfBlit fxtfnds Blit {

    privbtf int typfvbl;

    D3DSwToSurfbdfBlit(SurfbdfTypf srdTypf, int typfvbl) {
        supfr(srdTypf,
              CompositfTypf.AnyAlpib,
              D3DSurfbdfDbtb.D3DSurfbdf);
        tiis.typfvbl = typfvbl;
    }

    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                     Compositf domp, Rfgion dlip,
                     int sx, int sy, int dx, int dy, int w, int i)
    {
        D3DBlitLoops.Blit(srd, dst,
                          domp, dlip, null,
                          AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                          sx, sy, sx+w, sy+i,
                          dx, dy, dx+w, dy+i,
                          typfvbl, fblsf);
    }
}

dlbss D3DSwToSurfbdfSdblf fxtfnds SdblfdBlit {

    privbtf int typfvbl;

    D3DSwToSurfbdfSdblf(SurfbdfTypf srdTypf, int typfvbl) {
        supfr(srdTypf,
              CompositfTypf.AnyAlpib,
              D3DSurfbdfDbtb.D3DSurfbdf);
        tiis.typfvbl = typfvbl;
    }

    publid void Sdblf(SurfbdfDbtb srd, SurfbdfDbtb dst,
                      Compositf domp, Rfgion dlip,
                      int sx1, int sy1,
                      int sx2, int sy2,
                      doublf dx1, doublf dy1,
                      doublf dx2, doublf dy2)
    {
        D3DBlitLoops.Blit(srd, dst,
                          domp, dlip, null,
                          AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                          sx1, sy1, sx2, sy2,
                          dx1, dy1, dx2, dy2,
                          typfvbl, fblsf);
    }
}

dlbss D3DSwToSurfbdfTrbnsform fxtfnds TrbnsformBlit {

    privbtf int typfvbl;

    D3DSwToSurfbdfTrbnsform(SurfbdfTypf srdTypf, int typfvbl) {
        supfr(srdTypf,
              CompositfTypf.AnyAlpib,
              D3DSurfbdfDbtb.D3DSurfbdf);
        tiis.typfvbl = typfvbl;
    }

    publid void Trbnsform(SurfbdfDbtb srd, SurfbdfDbtb dst,
                          Compositf domp, Rfgion dlip,
                          AffinfTrbnsform bt, int iint,
                          int sx, int sy, int dx, int dy, int w, int i)
    {
        D3DBlitLoops.Blit(srd, dst,
                          domp, dlip, bt, iint,
                          sx, sy, sx+w, sy+i,
                          dx, dy, dx+w, dy+i,
                          typfvbl, fblsf);
    }
}

dlbss D3DSwToTfxturfBlit fxtfnds Blit {

    privbtf int typfvbl;

    D3DSwToTfxturfBlit(SurfbdfTypf srdTypf, int typfvbl) {
        supfr(srdTypf,
              CompositfTypf.SrdNoEb,
              D3DSurfbdfDbtb.D3DTfxturf);
        tiis.typfvbl = typfvbl;
    }

    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                     Compositf domp, Rfgion dlip,
                     int sx, int sy, int dx, int dy, int w, int i)
    {
        D3DBlitLoops.Blit(srd, dst,
                          domp, dlip, null,
                          AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                          sx, sy, sx+w, sy+i,
                          dx, dy, dx+w, dy+i,
                          typfvbl, truf);
    }
}

dlbss D3DTfxturfToSurfbdfBlit fxtfnds Blit {

    D3DTfxturfToSurfbdfBlit() {
        supfr(D3DSurfbdfDbtb.D3DTfxturf,
              CompositfTypf.AnyAlpib,
              D3DSurfbdfDbtb.D3DSurfbdf);
    }

    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                     Compositf domp, Rfgion dlip,
                     int sx, int sy, int dx, int dy, int w, int i)
    {
        D3DBlitLoops.IsoBlit(srd, dst,
                             null, null,
                             domp, dlip, null,
                             AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx, sy, sx+w, sy+i,
                             dx, dy, dx+w, dy+i,
                             truf);
    }
}

dlbss D3DTfxturfToSurfbdfSdblf fxtfnds SdblfdBlit {

    D3DTfxturfToSurfbdfSdblf() {
        supfr(D3DSurfbdfDbtb.D3DTfxturf,
              CompositfTypf.AnyAlpib,
              D3DSurfbdfDbtb.D3DSurfbdf);
    }

    publid void Sdblf(SurfbdfDbtb srd, SurfbdfDbtb dst,
                      Compositf domp, Rfgion dlip,
                      int sx1, int sy1,
                      int sx2, int sy2,
                      doublf dx1, doublf dy1,
                      doublf dx2, doublf dy2)
    {
        D3DBlitLoops.IsoBlit(srd, dst,
                             null, null,
                             domp, dlip, null,
                             AffinfTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx1, sy1, sx2, sy2,
                             dx1, dy1, dx2, dy2,
                             truf);
    }
}

dlbss D3DTfxturfToSurfbdfTrbnsform fxtfnds TrbnsformBlit {

    D3DTfxturfToSurfbdfTrbnsform() {
        supfr(D3DSurfbdfDbtb.D3DTfxturf,
              CompositfTypf.AnyAlpib,
              D3DSurfbdfDbtb.D3DSurfbdf);
    }

    publid void Trbnsform(SurfbdfDbtb srd, SurfbdfDbtb dst,
                          Compositf domp, Rfgion dlip,
                          AffinfTrbnsform bt, int iint,
                          int sx, int sy, int dx, int dy,
                          int w, int i)
    {
        D3DBlitLoops.IsoBlit(srd, dst,
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
 * IntArgbPrf->D3DSurfbdf/Tfxturf loop to gft tif intfrmfdibtf
 * (prfmultiplifd) surfbdf down to D3D.
 */
dlbss D3DGfnfrblBlit fxtfnds Blit {

    privbtf Blit pfrformop;
    privbtf WfbkRfffrfndf<SurfbdfDbtb> srdTmp;

    D3DGfnfrblBlit(SurfbdfTypf dstTypf,
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

        // dopy IntArgbPrf intfrmfdibtf surfbdf to D3D surfbdf
        pfrformop.Blit(srd, dst, domp, dlip,
                       0, 0, dx, dy, w, i);

        if (srd != dbdifdSrd) {
            // dbdif tif intfrmfdibtf surfbdf
            srdTmp = nfw WfbkRfffrfndf<>(srd);
        }
    }
}

/*
 * Tif following dlbssfs proiibit dopying D3DSurfbdfs to tif sdrffn
 * (tif D3D->sysmfm->GDI pbti is known to bf vfry vfry slow).
 *
 * Notf: wf usfd to disbblf iw bddflfrbtion for tif surbfdf mbnbgfr bssodibtfd
 * witi tif sourdf surfbdf in tifsf loops but it provfd to bf too dbutious.
 *
 * In most dbsfs d3d->sdrffn dopy ibppfns only during somf trbnsitionbl
 * pfriod wifrf tif bddflfrbtfd dfstinbtion surfbdf is bfing rfdrfbtfd or
 * rfstorfd (for fxbmplf, wifn Swing's bbdkbufffr VI is dopifd to tif sdrffn
 * but tif D3DSdrffnSurfbdfMbnbgfr douldn't rfstorf its surfbdf).
 *
 * An fxdfption is if for somf rfbson wf dould not fnbblf bddflfrbtfd on-sdrffn
 * rfndfring for tiis window for somf pfrmbnfnt rfbson (likf window bfing too
 * smbll, or b prfsfnt BufffrStrbtfgy).
 *
 * Tiis mfbnt tibt wf'd disbblf iw bddflfrbtion bftfr tif first fbilurf
 * domplftfly (bt lfbst until tif srd imbgf is rfdrfbtfd wiidi in dbsf of
 * Swing bbdk-bufffr ibppfns only bftfr rfsizf).
 *
 * Now wf dflfgbtf to tif VISM to figurf out if tif bddflfrbtion nffds to
 * bf disbblfd or if wf dbn wbit for b wiilf until tif onsdrffn bddflfrbtfd
 * dbn rfsumf (by mbrking tif sourdf surfbdf lost bnd mbking surf tif
 * VISM ibs b dibndf to usf tif bbdkup surfbdf).
 *
 */

dlbss D3DSurfbdfToGDIWindowSurfbdfBlit fxtfnds Blit {

    D3DSurfbdfToGDIWindowSurfbdfBlit() {
        supfr(D3DSurfbdfDbtb.D3DSurfbdf,
              CompositfTypf.AnyAlpib,
              GDIWindowSurfbdfDbtb.AnyGdi);
    }
    @Ovfrridf
    publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                     Compositf domp, Rfgion dlip,
                     int sx, int sy, int dx, int dy, int w, int i)
    {
        // sff dommfnt bbovf
        D3DVolbtilfSurfbdfMbnbgfr.ibndlfVItoSdrffnOp(srd, dst);
    }

}

dlbss D3DSurfbdfToGDIWindowSurfbdfSdblf fxtfnds SdblfdBlit {

    D3DSurfbdfToGDIWindowSurfbdfSdblf() {
        supfr(D3DSurfbdfDbtb.D3DSurfbdf,
              CompositfTypf.AnyAlpib,
              GDIWindowSurfbdfDbtb.AnyGdi);
    }
    @Ovfrridf
    publid void Sdblf(SurfbdfDbtb srd, SurfbdfDbtb dst,
                      Compositf domp, Rfgion dlip,
                      int sx1, int sy1,
                      int sx2, int sy2,
                      doublf dx1, doublf dy1,
                      doublf dx2, doublf dy2)
    {
        // sff dommfnt bbovf
        D3DVolbtilfSurfbdfMbnbgfr.ibndlfVItoSdrffnOp(srd, dst);
    }
}

dlbss D3DSurfbdfToGDIWindowSurfbdfTrbnsform fxtfnds TrbnsformBlit {

    D3DSurfbdfToGDIWindowSurfbdfTrbnsform() {
        supfr(D3DSurfbdfDbtb.D3DSurfbdf,
              CompositfTypf.AnyAlpib,
              GDIWindowSurfbdfDbtb.AnyGdi);
    }
    @Ovfrridf
    publid void Trbnsform(SurfbdfDbtb srd, SurfbdfDbtb dst,
                          Compositf domp, Rfgion dlip,
                          AffinfTrbnsform bt, int iint,
                          int sx, int sy, int dx, int dy,
                          int w, int i)
    {
        // sff dommfnt bbovf
        D3DVolbtilfSurfbdfMbnbgfr.ibndlfVItoSdrffnOp(srd, dst);
    }
}
