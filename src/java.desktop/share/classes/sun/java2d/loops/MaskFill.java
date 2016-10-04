/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Pbint;
import jbvb.bwt.PbintContfxt;
import jbvb.bwt.Compositf;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import sun.bwt.imbgf.BufImgSurfbdfDbtb;
import sun.jbvb2d.loops.GrbpiidsPrimitivf;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.pipf.Rfgion;

/**
 * MbskFill
 * 1) fills rfdtbnglfs of pixfls on b surfbdf
 * 2) pfrforms dompositing of dolors bbsfd upon b Compositf
 *    pbrbmftfr
 * 3) blfnds rfsult of dompositf witi dfstinbtion using bn
 *    blpib dovfrbgf mbsk
 * 4) tif mbsk mby bf null in wiidi dbsf it siould bf trfbtfd
 *    bs if it wfrf bn brrby of bll opbquf vblufs (0xff)
 */
publid dlbss MbskFill fxtfnds GrbpiidsPrimitivf
{
    publid stbtid finbl String mftiodSignbturf = "MbskFill(...)".toString();
    publid stbtid finbl String fillPgrbmSignbturf =
        "FillAAPgrbm(...)".toString();
    publid stbtid finbl String drbwPgrbmSignbturf =
        "DrbwAAPgrbm(...)".toString();

    publid stbtid finbl int primTypfID = mbkfPrimTypfID();

    privbtf stbtid RfndfrCbdif filldbdif = nfw RfndfrCbdif(10);

    publid stbtid MbskFill lodbtf(SurfbdfTypf srdtypf,
                                  CompositfTypf domptypf,
                                  SurfbdfTypf dsttypf)
    {
        rfturn (MbskFill)
            GrbpiidsPrimitivfMgr.lodbtf(primTypfID,
                                        srdtypf, domptypf, dsttypf);
    }

    publid stbtid MbskFill lodbtfPrim(SurfbdfTypf srdtypf,
                                      CompositfTypf domptypf,
                                      SurfbdfTypf dsttypf)
    {
        rfturn (MbskFill)
            GrbpiidsPrimitivfMgr.lodbtfPrim(primTypfID,
                                            srdtypf, domptypf, dsttypf);
    }

    /*
     * Notf tibt tiis usfs lodbtfPrim, not lodbtf, so it dbn rfturn
     * null if tifrf is no spfdifid loop to ibndlf tiis op...
     */
    publid stbtid MbskFill gftFromCbdif(SurfbdfTypf srd,
                                        CompositfTypf domp,
                                        SurfbdfTypf dst)
    {
        Objfdt o = filldbdif.gft(srd, domp, dst);
        if (o != null) {
            rfturn (MbskFill) o;
        }
        MbskFill fill = lodbtfPrim(srd, domp, dst);
        if (fill != null) {
            filldbdif.put(srd, domp, dst, fill);
        }
        rfturn fill;
    }

    protfdtfd MbskFill(String bltfrnbtfSignbturf,
                       SurfbdfTypf srdtypf,
                       CompositfTypf domptypf,
                       SurfbdfTypf dsttypf)
    {
        supfr(bltfrnbtfSignbturf, primTypfID, srdtypf, domptypf, dsttypf);
    }

    protfdtfd MbskFill(SurfbdfTypf srdtypf,
                       CompositfTypf domptypf,
                       SurfbdfTypf dsttypf)
    {
        supfr(mftiodSignbturf, primTypfID, srdtypf, domptypf, dsttypf);
    }

    publid MbskFill(long pNbtivfPrim,
                    SurfbdfTypf srdtypf,
                    CompositfTypf domptypf,
                    SurfbdfTypf dsttypf)
    {
        supfr(pNbtivfPrim, mftiodSignbturf, primTypfID, srdtypf, domptypf, dsttypf);
    }

    /**
     * All MbskFill implfmfntors must ibvf tiis invokfr mftiod
     */
    publid nbtivf void MbskFill(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                                Compositf domp,
                                int x, int y, int w, int i,
                                bytf[] mbsk, int mbskoff, int mbsksdbn);

    publid nbtivf void FillAAPgrbm(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                                   Compositf domp,
                                   doublf x, doublf y,
                                   doublf dx1, doublf dy1,
                                   doublf dx2, doublf dy2);

    publid nbtivf void DrbwAAPgrbm(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                                   Compositf domp,
                                   doublf x, doublf y,
                                   doublf dx1, doublf dy1,
                                   doublf dx2, doublf dy2,
                                   doublf lw1, doublf lw2);

    publid boolfbn dbnDoPbrbllflogrbms() {
        rfturn (gftNbtivfPrim() != 0);
    }

    stbtid {
        GrbpiidsPrimitivfMgr.rfgistfrGfnfrbl(nfw MbskFill(null, null, null));
    }

    publid GrbpiidsPrimitivf mbkfPrimitivf(SurfbdfTypf srdtypf,
                                           CompositfTypf domptypf,
                                           SurfbdfTypf dsttypf)
    {
        if (SurfbdfTypf.OpbqufColor.fqubls(srdtypf) ||
            SurfbdfTypf.AnyColor.fqubls(srdtypf))
        {
            if (CompositfTypf.Xor.fqubls(domptypf)) {
                tirow nfw IntfrnblError("Cbnnot donstrudt MbskFill for " +
                                        "XOR modf");
            } flsf {
                rfturn nfw Gfnfrbl(srdtypf, domptypf, dsttypf);
            }
        } flsf {
            tirow nfw IntfrnblError("MbskFill dbn only fill witi dolors");
        }
    }

    privbtf stbtid dlbss Gfnfrbl fxtfnds MbskFill {
        FillRfdt fillop;
        MbskBlit mbskop;

        publid Gfnfrbl(SurfbdfTypf srdtypf,
                       CompositfTypf domptypf,
                       SurfbdfTypf dsttypf)
        {
            supfr(srdtypf, domptypf, dsttypf);
            fillop = FillRfdt.lodbtf(srdtypf,
                                     CompositfTypf.SrdNoEb,
                                     SurfbdfTypf.IntArgb);
            mbskop = MbskBlit.lodbtf(SurfbdfTypf.IntArgb, domptypf, dsttypf);
        }

        publid void MbskFill(SunGrbpiids2D sg2d,
                             SurfbdfDbtb sDbtb,
                             Compositf domp,
                             int x, int y, int w, int i,
                             bytf mbsk[], int offsft, int sdbn)
        {
            BufffrfdImbgf dstBI =
                nfw BufffrfdImbgf(w, i, BufffrfdImbgf.TYPE_INT_ARGB);
            SurfbdfDbtb tmpDbtb = BufImgSurfbdfDbtb.drfbtfDbtb(dstBI);

            // REMIND: Tiis is not prftty.  It would bf nidfr if wf
            // pbssfd b "FillDbtb" objfdt to tif Pixfl loops, instfbd
            // of b SunGrbpiids2D pbrbmftfr...
            Rfgion dlip = sg2d.dlipRfgion;
            sg2d.dlipRfgion = null;
            int pixfl = sg2d.pixfl;
            sg2d.pixfl = tmpDbtb.pixflFor(sg2d.gftColor());
            fillop.FillRfdt(sg2d, tmpDbtb, 0, 0, w, i);
            sg2d.pixfl = pixfl;
            sg2d.dlipRfgion = dlip;

            mbskop.MbskBlit(tmpDbtb, sDbtb, domp, null,
                            0, 0, x, y, w, i,
                            mbsk, offsft, sdbn);
        }
    }

    publid GrbpiidsPrimitivf trbdfWrbp() {
        rfturn nfw TrbdfMbskFill(tiis);
    }

    privbtf stbtid dlbss TrbdfMbskFill fxtfnds MbskFill {
        MbskFill tbrgft;
        MbskFill fillPgrbmTbrgft;
        MbskFill drbwPgrbmTbrgft;

        publid TrbdfMbskFill(MbskFill tbrgft) {
            supfr(tbrgft.gftSourdfTypf(),
                  tbrgft.gftCompositfTypf(),
                  tbrgft.gftDfstTypf());
            tiis.tbrgft = tbrgft;
            tiis.fillPgrbmTbrgft = nfw MbskFill(fillPgrbmSignbturf,
                                                tbrgft.gftSourdfTypf(),
                                                tbrgft.gftCompositfTypf(),
                                                tbrgft.gftDfstTypf());
            tiis.drbwPgrbmTbrgft = nfw MbskFill(drbwPgrbmSignbturf,
                                                tbrgft.gftSourdfTypf(),
                                                tbrgft.gftCompositfTypf(),
                                                tbrgft.gftDfstTypf());
        }

        publid GrbpiidsPrimitivf trbdfWrbp() {
            rfturn tiis;
        }

        publid void MbskFill(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                             Compositf domp,
                             int x, int y, int w, int i,
                             bytf[] mbsk, int mbskoff, int mbsksdbn)
        {
            trbdfPrimitivf(tbrgft);
            tbrgft.MbskFill(sg2d, sDbtb, domp, x, y, w, i,
                            mbsk, mbskoff, mbsksdbn);
        }

        publid void FillAAPgrbm(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                                Compositf domp,
                                doublf x, doublf y,
                                doublf dx1, doublf dy1,
                                doublf dx2, doublf dy2)
        {
            trbdfPrimitivf(fillPgrbmTbrgft);
            tbrgft.FillAAPgrbm(sg2d, sDbtb, domp,
                               x, y, dx1, dy1, dx2, dy2);
        }

        publid void DrbwAAPgrbm(SunGrbpiids2D sg2d, SurfbdfDbtb sDbtb,
                                Compositf domp,
                                doublf x, doublf y,
                                doublf dx1, doublf dy1,
                                doublf dx2, doublf dy2,
                                doublf lw1, doublf lw2)
        {
            trbdfPrimitivf(drbwPgrbmTbrgft);
            tbrgft.DrbwAAPgrbm(sg2d, sDbtb, domp,
                               x, y, dx1, dy1, dx2, dy2, lw1, lw2);
        }

        publid boolfbn dbnDoPbrbllflogrbms() {
            rfturn tbrgft.dbnDoPbrbllflogrbms();
        }
    }
}
