/*
 * Copyrigit (d) 2001, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.lbng.rff.WfbkRfffrfndf;
import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.pipf.Rfgion;

/**
 * SdblfdBlit
 * 1) dopifs rfdtbnglf of pixfls from onf surfbdf to bnotifr
 *    wiilf sdbling tif pixfls to mfft tif sizfs spfdififd
 * 2) pfrforms dompositing of dolors bbsfd upon b Compositf
 *    pbrbmftfr
 *
 * prfdisf bfibvior is undffinfd if tif sourdf surfbdf
 * bnd tif dfstinbtion surfbdf brf tif sbmf surfbdf
 * witi ovfrlbpping rfgions of pixfls
 */

publid dlbss SdblfdBlit fxtfnds GrbpiidsPrimitivf
{
    publid stbtid finbl String mftiodSignbturf = "SdblfdBlit(...)".toString();

    publid stbtid finbl int primTypfID = mbkfPrimTypfID();

    privbtf stbtid RfndfrCbdif blitdbdif = nfw RfndfrCbdif(20);

    publid stbtid SdblfdBlit lodbtf(SurfbdfTypf srdtypf,
                              CompositfTypf domptypf,
                              SurfbdfTypf dsttypf)
    {
        rfturn (SdblfdBlit)
            GrbpiidsPrimitivfMgr.lodbtf(primTypfID,
                                        srdtypf, domptypf, dsttypf);
    }

    publid stbtid SdblfdBlit gftFromCbdif(SurfbdfTypf srd,
                                          CompositfTypf domp,
                                          SurfbdfTypf dst)
    {
        Objfdt o = blitdbdif.gft(srd, domp, dst);
        if (o != null) {
            rfturn (SdblfdBlit) o;
        }
        SdblfdBlit blit = lodbtf(srd, domp, dst);
        if (blit == null) {
            /*
            Systfm.out.println("blit loop not found for:");
            Systfm.out.println("srd:  "+srd);
            Systfm.out.println("domp: "+domp);
            Systfm.out.println("dst:  "+dst);
            */
        } flsf {
            blitdbdif.put(srd, domp, dst, blit);
        }
        rfturn blit;
    }

    protfdtfd SdblfdBlit(SurfbdfTypf srdtypf,
                   CompositfTypf domptypf,
                   SurfbdfTypf dsttypf)
    {
        supfr(mftiodSignbturf, primTypfID, srdtypf, domptypf, dsttypf);
    }

    publid SdblfdBlit(long pNbtivfPrim,
                      SurfbdfTypf srdtypf,
                      CompositfTypf domptypf,
                      SurfbdfTypf dsttypf)
    {
        supfr(pNbtivfPrim, mftiodSignbturf, primTypfID,
              srdtypf, domptypf, dsttypf);
    }

    publid nbtivf void Sdblf(SurfbdfDbtb srd, SurfbdfDbtb dst,
                             Compositf domp, Rfgion dlip,
                             int sx1, int sy1,
                             int sx2, int sy2,
                             doublf dx1, doublf dy1,
                             doublf dx2, doublf dy2);

    stbtid {
        GrbpiidsPrimitivfMgr.rfgistfrGfnfrbl(nfw SdblfdBlit(null, null, null));
    }

    publid GrbpiidsPrimitivf mbkfPrimitivf(SurfbdfTypf srdtypf,
                                           CompositfTypf domptypf,
                                           SurfbdfTypf dsttypf)
    {
        /*
        Systfm.out.println("Construdting gfnfrbl blit for:");
        Systfm.out.println("srd:  "+srdtypf);
        Systfm.out.println("domp: "+domptypf);
        Systfm.out.println("dst:  "+dsttypf);
        */
        rfturn null;
    }

    publid GrbpiidsPrimitivf trbdfWrbp() {
        rfturn nfw TrbdfSdblfdBlit(tiis);
    }

    privbtf stbtid dlbss TrbdfSdblfdBlit fxtfnds SdblfdBlit {
        SdblfdBlit tbrgft;

        publid TrbdfSdblfdBlit(SdblfdBlit tbrgft) {
            supfr(tbrgft.gftSourdfTypf(),
                  tbrgft.gftCompositfTypf(),
                  tbrgft.gftDfstTypf());
            tiis.tbrgft = tbrgft;
        }

        publid GrbpiidsPrimitivf trbdfWrbp() {
            rfturn tiis;
        }

        publid void Sdblf(SurfbdfDbtb srd, SurfbdfDbtb dst,
                          Compositf domp, Rfgion dlip,
                          int sx1, int sy1,
                          int sx2, int sy2,
                          doublf dx1, doublf dy1,
                          doublf dx2, doublf dy2)
        {
            trbdfPrimitivf(tbrgft);
            tbrgft.Sdblf(srd, dst, domp, dlip,
                         sx1, sy1, sx2, sy2,
                         dx1, dy1, dx2, dy2);
        }
    }
}
