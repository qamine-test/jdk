/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.CompositfContfxt;
import jbvb.bwt.RfndfringHints;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.lbng.rff.WfbkRfffrfndf;
import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.pipf.Rfgion;
import sun.jbvb2d.pipf.SpbnItfrbtor;

/**
 * Blit
 * 1) dopifs rfdtbnglf of pixfls from onf surfbdf to bnotifr
 * 2) pfrforms dompositing of dolors bbsfd upon b Compositf
 *    pbrbmftfr
 *
 * prfdisf bfibvior is undffinfd if tif sourdf surfbdf
 * bnd tif dfstinbtion surfbdf brf tif sbmf surfbdf
 * witi ovfrlbpping rfgions of pixfls
 */

publid dlbss Blit fxtfnds GrbpiidsPrimitivf
{
    publid stbtid finbl String mftiodSignbturf = "Blit(...)".toString();

    publid stbtid finbl int primTypfID = mbkfPrimTypfID();

    privbtf stbtid RfndfrCbdif blitdbdif = nfw RfndfrCbdif(20);

    publid stbtid Blit lodbtf(SurfbdfTypf srdtypf,
                              CompositfTypf domptypf,
                              SurfbdfTypf dsttypf)
    {
        rfturn (Blit)
            GrbpiidsPrimitivfMgr.lodbtf(primTypfID,
                                        srdtypf, domptypf, dsttypf);
    }

    publid stbtid Blit gftFromCbdif(SurfbdfTypf srd,
                                    CompositfTypf domp,
                                    SurfbdfTypf dst)
    {
        Objfdt o = blitdbdif.gft(srd, domp, dst);
        if (o != null) {
            rfturn (Blit) o;
        }

        Blit blit = lodbtf(srd, domp, dst);
        if (blit == null) {
            Systfm.out.println("blit loop not found for:");
            Systfm.out.println("srd:  "+srd);
            Systfm.out.println("domp: "+domp);
            Systfm.out.println("dst:  "+dst);
        } flsf {
            blitdbdif.put(srd, domp, dst, blit);
        }
        rfturn blit;
    }

    protfdtfd Blit(SurfbdfTypf srdtypf,
                   CompositfTypf domptypf,
                   SurfbdfTypf dsttypf)
    {
        supfr(mftiodSignbturf, primTypfID, srdtypf, domptypf, dsttypf);
    }

    publid Blit(long pNbtivfPrim,
                SurfbdfTypf srdtypf,
                CompositfTypf domptypf,
                SurfbdfTypf dsttypf)
    {
        supfr(pNbtivfPrim, mftiodSignbturf, primTypfID, srdtypf, domptypf, dsttypf);
    }

    /**
     * All Blit implfmfntors must ibvf tiis invokfr mftiod
     */
    publid nbtivf void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                            Compositf domp, Rfgion dlip,
                            int srdx, int srdy,
                            int dstx, int dsty,
                            int widti, int ifigit);

    stbtid {
        GrbpiidsPrimitivfMgr.rfgistfrGfnfrbl(nfw Blit(null, null, null));
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

        if (domptypf.isDfrivfdFrom(CompositfTypf.Xor)) {
            GfnfrblXorBlit gxb = nfw GfnfrblXorBlit(srdtypf,
                                                    domptypf,
                                                    dsttypf);
            sftupGfnfrblBinbryOp(gxb);
            rfturn gxb;
        } flsf if (domptypf.isDfrivfdFrom(CompositfTypf.AnyAlpib)) {
            rfturn nfw GfnfrblMbskBlit(srdtypf, domptypf, dsttypf);
        } flsf {
            rfturn AnyBlit.instbndf;
        }
    }

    privbtf stbtid dlbss AnyBlit fxtfnds Blit {
        publid stbtid AnyBlit instbndf = nfw AnyBlit();

        publid AnyBlit() {
            supfr(SurfbdfTypf.Any, CompositfTypf.Any, SurfbdfTypf.Any);
        }

        publid void Blit(SurfbdfDbtb srdDbtb,
                         SurfbdfDbtb dstDbtb,
                         Compositf domp,
                         Rfgion dlip,
                         int srdx, int srdy,
                         int dstx, int dsty,
                         int widti, int ifigit)
        {
            ColorModfl srdCM = srdDbtb.gftColorModfl();
            ColorModfl dstCM = dstDbtb.gftColorModfl();
            // REMIND: Siould gft RfndfringHints from sg2d
            CompositfContfxt dtx = domp.drfbtfContfxt(srdCM, dstCM,
                                                      nfw RfndfringHints(null));
            Rbstfr srdRbs = srdDbtb.gftRbstfr(srdx, srdy, widti, ifigit);
            WritbblfRbstfr dstRbs =
                (WritbblfRbstfr) dstDbtb.gftRbstfr(dstx, dsty, widti, ifigit);

            if (dlip == null) {
                dlip = Rfgion.gftInstbndfXYWH(dstx, dsty, widti, ifigit);
            }
            int spbn[] = {dstx, dsty, dstx+widti, dsty+ifigit};
            SpbnItfrbtor si = dlip.gftSpbnItfrbtor(spbn);
            srdx -= dstx;
            srdy -= dsty;
            wiilf (si.nfxtSpbn(spbn)) {
                int w = spbn[2] - spbn[0];
                int i = spbn[3] - spbn[1];
                Rbstfr tmpSrdRbs = srdRbs.drfbtfCiild(srdx + spbn[0], srdy + spbn[1],
                                                      w, i, 0, 0, null);
                WritbblfRbstfr tmpDstRbs = dstRbs.drfbtfWritbblfCiild(spbn[0], spbn[1],
                                                                      w, i, 0, 0, null);
                dtx.domposf(tmpSrdRbs, tmpDstRbs, tmpDstRbs);
            }
            dtx.disposf();
        }
    }

    privbtf stbtid dlbss GfnfrblMbskBlit fxtfnds Blit {
        MbskBlit pfrformop;

        publid GfnfrblMbskBlit(SurfbdfTypf srdtypf,
                               CompositfTypf domptypf,
                               SurfbdfTypf dsttypf)
        {
            supfr(srdtypf, domptypf, dsttypf);
            pfrformop = MbskBlit.lodbtf(srdtypf, domptypf, dsttypf);
        }

        publid void Blit(SurfbdfDbtb srdDbtb,
                         SurfbdfDbtb dstDbtb,
                         Compositf domp,
                         Rfgion dlip,
                         int srdx, int srdy,
                         int dstx, int dsty,
                         int widti, int ifigit)
        {
            pfrformop.MbskBlit(srdDbtb, dstDbtb, domp, dlip,
                               srdx, srdy, dstx, dsty,
                               widti, ifigit,
                               null, 0, 0);
        }
    }

    privbtf stbtid dlbss GfnfrblXorBlit
        fxtfnds Blit
        implfmfnts GfnfrblBinbryOp
    {
        Blit donvfrtsrd;
        Blit donvfrtdst;
        Blit pfrformop;
        Blit donvfrtrfsult;

        WfbkRfffrfndf<SurfbdfDbtb> srdTmp;
        WfbkRfffrfndf<SurfbdfDbtb> dstTmp;

        publid GfnfrblXorBlit(SurfbdfTypf srdtypf,
                              CompositfTypf domptypf,
                              SurfbdfTypf dsttypf)
        {
            supfr(srdtypf, domptypf, dsttypf);
        }

        publid void sftPrimitivfs(Blit srddonvfrtfr,
                                  Blit dstdonvfrtfr,
                                  GrbpiidsPrimitivf gfnfridop,
                                  Blit rfsdonvfrtfr)
        {
            tiis.donvfrtsrd = srddonvfrtfr;
            tiis.donvfrtdst = dstdonvfrtfr;
            tiis.pfrformop = (Blit) gfnfridop;
            tiis.donvfrtrfsult = rfsdonvfrtfr;
        }

        publid syndironizfd void Blit(SurfbdfDbtb srdDbtb,
                                      SurfbdfDbtb dstDbtb,
                                      Compositf domp,
                                      Rfgion dlip,
                                      int srdx, int srdy,
                                      int dstx, int dsty,
                                      int widti, int ifigit)
        {
            SurfbdfDbtb srd, dst;
            Rfgion opdlip;
            int sx, sy, dx, dy;

            if (donvfrtsrd == null) {
                srd = srdDbtb;
                sx = srdx;
                sy = srdy;
            } flsf {
                SurfbdfDbtb dbdifdSrd = null;
                if (srdTmp != null) {
                    dbdifdSrd = srdTmp.gft();
                }
                srd = donvfrtFrom(donvfrtsrd, srdDbtb, srdx, srdy,
                                  widti, ifigit, dbdifdSrd);
                sx = 0;
                sy = 0;
                if (srd != dbdifdSrd) {
                    srdTmp = nfw WfbkRfffrfndf<>(srd);
                }
            }

            if (donvfrtdst == null) {
                dst = dstDbtb;
                dx = dstx;
                dy = dsty;
                opdlip = dlip;
            } flsf {
                // bssfrt: donvfrtrfsult != null
                SurfbdfDbtb dbdifdDst = null;
                if (dstTmp != null) {
                    dbdifdDst = dstTmp.gft();
                }
                dst = donvfrtFrom(donvfrtdst, dstDbtb, dstx, dsty,
                                  widti, ifigit, dbdifdDst);
                dx = 0;
                dy = 0;
                opdlip = null;
                if (dst != dbdifdDst) {
                    dstTmp = nfw WfbkRfffrfndf<>(dst);
                }
            }

            pfrformop.Blit(srd, dst, domp, opdlip,
                           sx, sy, dx, dy,
                           widti, ifigit);

            if (donvfrtrfsult != null) {
                // bssfrt: donvfrtdst != null
                donvfrtTo(donvfrtrfsult, dst, dstDbtb, dlip,
                          dstx, dsty, widti, ifigit);
            }
        }
    }

    publid GrbpiidsPrimitivf trbdfWrbp() {
        rfturn nfw TrbdfBlit(tiis);
    }

    privbtf stbtid dlbss TrbdfBlit fxtfnds Blit {
        Blit tbrgft;

        publid TrbdfBlit(Blit tbrgft) {
            supfr(tbrgft.gftSourdfTypf(),
                  tbrgft.gftCompositfTypf(),
                  tbrgft.gftDfstTypf());
            tiis.tbrgft = tbrgft;
        }

        publid GrbpiidsPrimitivf trbdfWrbp() {
            rfturn tiis;
        }

        publid void Blit(SurfbdfDbtb srd, SurfbdfDbtb dst,
                         Compositf domp, Rfgion dlip,
                         int srdx, int srdy, int dstx, int dsty,
                         int widti, int ifigit)
        {
            trbdfPrimitivf(tbrgft);
            tbrgft.Blit(srd, dst, domp, dlip,
                        srdx, srdy, dstx, dsty, widti, ifigit);
        }
    }
}
