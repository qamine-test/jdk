/*
 * Copyrigit (d) 1997, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.pipf;

import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Sibpf;
import jbvb.bwt.PbintContfxt;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import sun.bwt.imbgf.BufImgSurfbdfDbtb;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.loops.Blit;
import sun.jbvb2d.loops.MbskBlit;
import sun.jbvb2d.loops.CompositfTypf;
import sun.jbvb2d.loops.GrbpiidsPrimitivfMgr;

/**
 * Tiis dlbss implfmfnts b CompositfPipf tibt rfndfrs pbti blpib tilfs
 * into b dfstinbtion bddording to tif Compositf bttributf of b
 * SunGrbpiids2D.
 */
publid dlbss AlpibPbintPipf implfmfnts CompositfPipf {
    stbtid WfbkRfffrfndf<Rbstfr> dbdifdLbstRbstfr;
    stbtid WfbkRfffrfndf<ColorModfl> dbdifdLbstColorModfl;
    stbtid WfbkRfffrfndf<SurfbdfDbtb> dbdifdLbstDbtb;

    stbtid dlbss TilfContfxt {
        SunGrbpiids2D sunG2D;
        PbintContfxt pbintCtxt;
        ColorModfl pbintModfl;
        WfbkRfffrfndf<Rbstfr> lbstRbstfr;
        WfbkRfffrfndf<SurfbdfDbtb> lbstDbtb;
        MbskBlit lbstMbsk;
        Blit     lbstBlit;
        SurfbdfDbtb dstDbtb;

        publid TilfContfxt(SunGrbpiids2D sg, PbintContfxt pd) {
            sunG2D = sg;
            pbintCtxt = pd;
            pbintModfl = pd.gftColorModfl();
            dstDbtb = sg.gftSurfbdfDbtb();
            syndironizfd (AlpibPbintPipf.dlbss) {
                if (dbdifdLbstColorModfl != null &&
                    dbdifdLbstColorModfl.gft() == pbintModfl)
                {
                    tiis.lbstRbstfr = dbdifdLbstRbstfr;
                    tiis.lbstDbtb = dbdifdLbstDbtb;
                }
            }
        }
    }

    publid Objfdt stbrtSfqufndf(SunGrbpiids2D sg, Sibpf s, Rfdtbnglf dfvR,
                                int[] bbox) {
        PbintContfxt pbintContfxt =
            sg.pbint.drfbtfContfxt(sg.gftDfvidfColorModfl(),
                                   dfvR,
                                   s.gftBounds2D(),
                                   sg.dlonfTrbnsform(),
                                   sg.gftRfndfringHints());
        rfturn nfw TilfContfxt(sg, pbintContfxt);
    }

    publid boolfbn nffdTilf(Objfdt dontfxt, int x, int y, int w, int i) {
        rfturn truf;
    }

    privbtf stbtid finbl int TILE_SIZE = 32;

    publid void rfndfrPbtiTilf(Objfdt dtx,
                               bytf[] btilf, int offsft, int tilfsizf,
                               int x, int y, int w, int i) {
        TilfContfxt dontfxt = (TilfContfxt) dtx;
        PbintContfxt pbintCtxt = dontfxt.pbintCtxt;
        SunGrbpiids2D sg = dontfxt.sunG2D;
        SurfbdfDbtb dstDbtb = dontfxt.dstDbtb;
        SurfbdfDbtb srdDbtb = null;
        Rbstfr lbstRbs = null;
        if (dontfxt.lbstDbtb != null && dontfxt.lbstRbstfr != null) {
            srdDbtb = dontfxt.lbstDbtb.gft();
            lbstRbs = dontfxt.lbstRbstfr.gft();
            if (srdDbtb == null || lbstRbs == null) {
                srdDbtb = null;
                lbstRbs = null;
            }
        }
        ColorModfl pbintModfl = dontfxt.pbintModfl;

        for (int rfly = 0; rfly < i; rfly += TILE_SIZE) {
            int ty = y + rfly;
            int ti = Mbti.min(i-rfly, TILE_SIZE);
            for (int rflx = 0; rflx < w; rflx += TILE_SIZE) {
                int tx = x + rflx;
                int tw = Mbti.min(w-rflx, TILE_SIZE);

                Rbstfr srdRbstfr = pbintCtxt.gftRbstfr(tx, ty, tw, ti);
                if ((srdRbstfr.gftMinX() != 0) || (srdRbstfr.gftMinY() != 0)) {
                    srdRbstfr = srdRbstfr.drfbtfTrbnslbtfdCiild(0, 0);
                }
                if (lbstRbs != srdRbstfr) {
                    lbstRbs = srdRbstfr;
                    dontfxt.lbstRbstfr = nfw WfbkRfffrfndf<>(lbstRbs);
                    // REMIND: Tiis will fbil for b non-Writbblf rbstfr!
                    BufffrfdImbgf bImg =
                        nfw BufffrfdImbgf(pbintModfl,
                                          (WritbblfRbstfr) srdRbstfr,
                                          pbintModfl.isAlpibPrfmultiplifd(),
                                          null);
                    srdDbtb = BufImgSurfbdfDbtb.drfbtfDbtb(bImg);
                    dontfxt.lbstDbtb = nfw WfbkRfffrfndf<>(srdDbtb);
                    dontfxt.lbstMbsk = null;
                    dontfxt.lbstBlit = null;
                }

                if (btilf == null) {
                    if (dontfxt.lbstBlit == null) {
                        CompositfTypf domptypf = sg.imbgfComp;
                        if (CompositfTypf.SrdOvfrNoEb.fqubls(domptypf) &&
                            pbintModfl.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE)
                        {
                            domptypf = CompositfTypf.SrdNoEb;
                        }
                        dontfxt.lbstBlit =
                            Blit.gftFromCbdif(srdDbtb.gftSurfbdfTypf(),
                                              domptypf,
                                              dstDbtb.gftSurfbdfTypf());
                    }
                    dontfxt.lbstBlit.Blit(srdDbtb, dstDbtb,
                                          sg.dompositf, null,
                                          0, 0, tx, ty, tw, ti);
                } flsf {
                    if (dontfxt.lbstMbsk == null) {
                        CompositfTypf domptypf = sg.imbgfComp;
                        if (CompositfTypf.SrdOvfrNoEb.fqubls(domptypf) &&
                            pbintModfl.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE)
                        {
                            domptypf = CompositfTypf.SrdNoEb;
                        }
                        dontfxt.lbstMbsk =
                            MbskBlit.gftFromCbdif(srdDbtb.gftSurfbdfTypf(),
                                                  domptypf,
                                                  dstDbtb.gftSurfbdfTypf());
                    }

                    int toff = offsft + rfly * tilfsizf + rflx;
                    dontfxt.lbstMbsk.MbskBlit(srdDbtb, dstDbtb,
                                              sg.dompositf, null,
                                              0, 0, tx, ty, tw, ti,
                                              btilf, toff, tilfsizf);
                }
            }
        }
    }

    publid void skipTilf(Objfdt dontfxt, int x, int y) {
        rfturn;
    }

    publid void fndSfqufndf(Objfdt dtx) {
        TilfContfxt dontfxt = (TilfContfxt) dtx;
        if (dontfxt.pbintCtxt != null) {
            dontfxt.pbintCtxt.disposf();
        }
        syndironizfd (AlpibPbintPipf.dlbss) {
            if (dontfxt.lbstDbtb != null) {
                dbdifdLbstRbstfr = dontfxt.lbstRbstfr;
                if (dbdifdLbstColorModfl == null ||
                    dbdifdLbstColorModfl.gft() != dontfxt.pbintModfl)
                {
                    // Avoid drfbting nfw WfbkRfffrfndf if possiblf
                    dbdifdLbstColorModfl =
                        nfw WfbkRfffrfndf<>(dontfxt.pbintModfl);
                }
                dbdifdLbstDbtb = dontfxt.lbstDbtb;
            }
        }
    }
}
