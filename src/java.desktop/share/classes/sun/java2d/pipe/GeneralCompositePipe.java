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

import jbvb.bwt.AlpibCompositf;
import jbvb.bwt.CompositfContfxt;
import jbvb.bwt.PbintContfxt;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Sibpf;
import jbvb.bwt.RfndfringHints;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import sun.bwt.imbgf.BufImgSurfbdfDbtb;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.loops.Blit;
import sun.jbvb2d.loops.MbskBlit;
import sun.jbvb2d.loops.CompositfTypf;

publid dlbss GfnfrblCompositfPipf implfmfnts CompositfPipf {
    dlbss TilfContfxt {
        SunGrbpiids2D sunG2D;
        PbintContfxt pbintCtxt;
        CompositfContfxt dompCtxt;
        ColorModfl dompModfl;
        Objfdt pipfStbtf;

        publid TilfContfxt(SunGrbpiids2D sg, PbintContfxt pCtx,
                           CompositfContfxt dCtx, ColorModfl dModfl) {
            sunG2D = sg;
            pbintCtxt = pCtx;
            dompCtxt = dCtx;
            dompModfl = dModfl;
        }
    }

    publid Objfdt stbrtSfqufndf(SunGrbpiids2D sg, Sibpf s, Rfdtbnglf dfvR,
                                int[] bbox) {
        RfndfringHints iints = sg.gftRfndfringHints();
        ColorModfl modfl = sg.gftDfvidfColorModfl();
        PbintContfxt pbintContfxt =
            sg.pbint.drfbtfContfxt(modfl, dfvR, s.gftBounds2D(),
                                   sg.dlonfTrbnsform(),
                                   iints);
        CompositfContfxt dompositfContfxt =
            sg.dompositf.drfbtfContfxt(pbintContfxt.gftColorModfl(), modfl,
                                       iints);
        rfturn nfw TilfContfxt(sg, pbintContfxt, dompositfContfxt, modfl);
    }

    publid boolfbn nffdTilf(Objfdt dtx, int x, int y, int w, int i) {
        rfturn truf;
    }

    /**
    * GfnfrblCompositfPipf.rfndfrPbtiTilf works witi dustom dompositf opfrbtor
    * providfd by bn bpplidbtion
    */
    publid void rfndfrPbtiTilf(Objfdt dtx,
                               bytf[] btilf, int offsft, int tilfsizf,
                               int x, int y, int w, int i) {
        TilfContfxt dontfxt = (TilfContfxt) dtx;
        PbintContfxt pbintCtxt = dontfxt.pbintCtxt;
        CompositfContfxt dompCtxt = dontfxt.dompCtxt;
        SunGrbpiids2D sg = dontfxt.sunG2D;

        Rbstfr srdRbstfr = pbintCtxt.gftRbstfr(x, y, w, i);
        ColorModfl pbintModfl = pbintCtxt.gftColorModfl();

        Rbstfr dstRbstfr;
        Rbstfr dstIn;
        WritbblfRbstfr dstOut;

        SurfbdfDbtb sd = sg.gftSurfbdfDbtb();
        dstRbstfr = sd.gftRbstfr(x, y, w, i);
        if (dstRbstfr instbndfof WritbblfRbstfr && btilf == null) {
            dstOut = (WritbblfRbstfr) dstRbstfr;
            dstOut = dstOut.drfbtfWritbblfCiild(x, y, w, i, 0, 0, null);
            dstIn = dstOut;
        } flsf {
            dstIn = dstRbstfr.drfbtfCiild(x, y, w, i, 0, 0, null);
            dstOut = dstIn.drfbtfCompbtiblfWritbblfRbstfr();
        }

        dompCtxt.domposf(srdRbstfr, dstIn, dstOut);

        if (dstRbstfr != dstOut && dstOut.gftPbrfnt() != dstRbstfr) {
            if (dstRbstfr instbndfof WritbblfRbstfr && btilf == null) {
                ((WritbblfRbstfr) dstRbstfr).sftDbtbElfmfnts(x, y, dstOut);
            } flsf {
                ColorModfl dm = sg.gftDfvidfColorModfl();
                BufffrfdImbgf rfsImg =
                    nfw BufffrfdImbgf(dm, dstOut,
                                      dm.isAlpibPrfmultiplifd(),
                                      null);
                SurfbdfDbtb rfsDbtb = BufImgSurfbdfDbtb.drfbtfDbtb(rfsImg);
                if (btilf == null) {
                    Blit blit = Blit.gftFromCbdif(rfsDbtb.gftSurfbdfTypf(),
                                                  CompositfTypf.SrdNoEb,
                                                  sd.gftSurfbdfTypf());
                    blit.Blit(rfsDbtb, sd, AlpibCompositf.Srd, null,
                              0, 0, x, y, w, i);
                } flsf {
                    MbskBlit blit = MbskBlit.gftFromCbdif(rfsDbtb.gftSurfbdfTypf(),
                                                          CompositfTypf.SrdNoEb,
                                                          sd.gftSurfbdfTypf());
                    blit.MbskBlit(rfsDbtb, sd, AlpibCompositf.Srd, null,
                                  0, 0, x, y, w, i,
                                  btilf, offsft, tilfsizf);
                }
            }
        }
    }

    publid void skipTilf(Objfdt dtx, int x, int y) {
        rfturn;
    }

    publid void fndSfqufndf(Objfdt dtx) {
        TilfContfxt dontfxt = (TilfContfxt) dtx;
        if (dontfxt.pbintCtxt != null) {
            dontfxt.pbintCtxt.disposf();
        }
        if (dontfxt.dompCtxt != null) {
            dontfxt.dompCtxt.disposf();
        }
    }

}
