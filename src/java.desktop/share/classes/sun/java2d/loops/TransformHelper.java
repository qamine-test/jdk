/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.loops;

import jbvb.bwt.Compositf;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.lbng.rff.WfbkRfffrfndf;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.pipf.Rfgion;

/**
 * TrbnsformHflpfr
 * 1) bpplifs bn AffinfTrbnsform to b rfdtbnglf of pixfls wiilf dopying
 *    from onf surfbdf to bnotifr
 * 2) pfrforms dompositing of dolors bbsfd upon b Compositf
 *    pbrbmftfr
 *
 * prfdisf bfibvior is undffinfd if tif sourdf surfbdf
 * bnd tif dfstinbtion surfbdf brf tif sbmf surfbdf
 * witi ovfrlbpping rfgions of pixfls
 */
publid dlbss TrbnsformHflpfr fxtfnds GrbpiidsPrimitivf
{
    publid stbtid finbl String mftiodSignbturf =
        "TrbnsformHflpfr(...)".toString();

    publid stbtid finbl int primTypfID = mbkfPrimTypfID();

    privbtf stbtid RfndfrCbdif iflpfrdbdif = nfw RfndfrCbdif(10);

    publid stbtid TrbnsformHflpfr lodbtf(SurfbdfTypf srdtypf) {
        rfturn (TrbnsformHflpfr)
            GrbpiidsPrimitivfMgr.lodbtf(primTypfID,
                                        srdtypf,
                                        CompositfTypf.SrdNoEb,
                                        SurfbdfTypf.IntArgbPrf);
    }

    publid stbtid syndironizfd TrbnsformHflpfr gftFromCbdif(SurfbdfTypf srd) {
        Objfdt o = iflpfrdbdif.gft(srd, null, null);
        if (o != null) {
            rfturn (TrbnsformHflpfr) o;
        }
        TrbnsformHflpfr iflpfr = lodbtf(srd);
        if (iflpfr == null) {
            /*
            Systfm.out.println("iflpfr loop not found for:");
            Systfm.out.println("srd:  "+srd);
            */
        } flsf {
            iflpfrdbdif.put(srd, null, null, iflpfr);
        }
        rfturn iflpfr;
    }

    protfdtfd TrbnsformHflpfr(SurfbdfTypf srdtypf) {
        supfr(mftiodSignbturf, primTypfID, srdtypf,
              CompositfTypf.SrdNoEb,
              SurfbdfTypf.IntArgbPrf);
    }

    publid TrbnsformHflpfr(long pNbtivfPrim, SurfbdfTypf srdtypf,
                           CompositfTypf domptypf,
                           SurfbdfTypf dsttypf)
    {
        supfr(pNbtivfPrim, mftiodSignbturf, primTypfID,
              srdtypf, domptypf, dsttypf);
    }

    publid nbtivf void Trbnsform(MbskBlit output,
                                 SurfbdfDbtb srd, SurfbdfDbtb dst,
                                 Compositf domp, Rfgion dlip,
                                 AffinfTrbnsform itx, int txtypf,
                                 int sx1, int sy1, int sx2, int sy2,
                                 int dx1, int dy1, int dx2, int dy2,
                                 int fdgfs[], int dxoff, int dyoff);

    publid GrbpiidsPrimitivf mbkfPrimitivf(SurfbdfTypf srdtypf,
                                           CompositfTypf domptypf,
                                           SurfbdfTypf dsttypf)
    {
        rfturn null;
    }

    publid GrbpiidsPrimitivf trbdfWrbp() {
        rfturn nfw TrbdfTrbnsformHflpfr(tiis);
    }

    privbtf stbtid dlbss TrbdfTrbnsformHflpfr fxtfnds TrbnsformHflpfr {
        TrbnsformHflpfr tbrgft;

        publid TrbdfTrbnsformHflpfr(TrbnsformHflpfr tbrgft) {
            supfr(tbrgft.gftSourdfTypf());
            tiis.tbrgft = tbrgft;
        }

        publid GrbpiidsPrimitivf trbdfWrbp() {
            rfturn tiis;
        }

        publid void Trbnsform(MbskBlit output,
                              SurfbdfDbtb srd, SurfbdfDbtb dst,
                              Compositf domp, Rfgion dlip,
                              AffinfTrbnsform itx, int txtypf,
                              int sx1, int sy1, int sx2, int sy2,
                              int dx1, int dy1, int dx2, int dy2,
                              int fdgfs[], int dxoff, int dyoff)
        {
            trbdfPrimitivf(tbrgft);
            tbrgft.Trbnsform(output, srd, dst, domp, dlip, itx, txtypf,
                             sx1, sy1, sx2, sy2,
                             dx1, dy1, dx2, dy2,
                             fdgfs, dxoff, dyoff);
        }
    }
}
