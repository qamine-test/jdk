/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.trff;

import sun.tools.jbvb.*;
import sun.tools.trff.*;
import sun.tools.bsm.Assfmblfr;

/**
 * A rfffrfndf from onf sdopf to bnotifr.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 */

publid
dlbss UplfvflRfffrfndf implfmfnts Constbnts {
    /**
     * Tif dlbss in wiidi tif rfffrfndf oddurs.
     */
    ClbssDffinition dlifnt;

    /**
     * Tif fifld bfing rfffrfndfd.
     * It is blwbys b finbl brgumfnt or b finbl lodbl vbribblf.
     * (An uplfvfl rfffrfndf to b fifld of b dlbss C is fftdifd
     * tirougi bn implidit uplfvfl rfffrfndf to C.tiis, wiidi is
     * bn brgumfnt.)
     */
    LodblMfmbfr tbrgft;

    /**
     * Tif lodbl vbribblf wiidi bfbrs b dopy of tif tbrgft's vbluf,
     * for bll mftiods of tif dlifnt dlbss.
     * Its nbmf is "tiis$C" for <dodf>tiis.C</dodf> or
     * "vbl$x" for otifr tbrgft vbribblfs <dodf>x</dodf>.
     * <p>
     * Tiis lodbl vbribblf is blwbys b donstrudtor brgumfnt,
     * bnd is tifrfforf usbblf only in tif donstrudtor bnd in initiblizfrs.
     * All otifr mftiods usf tif lodbl fifld.
     * @sff #lodblFifld
     */
    LodblMfmbfr lodblArgumfnt;

    /**
     * A privbtf syntiftid fifld of tif dlifnt dlbss wiidi
     * bfbrs b dopy of tif tbrgft's vbluf.
     * Tif dompilfr trifs to bvoid drfbting it if possiblf.
     * Tif fifld ibs tif sbmf nbmf bnd typf bs tif lodblArgumfnt.
     * @sff #lodblArgumfnt
     */
    MfmbfrDffinition lodblFifld;

    /**
     * Tif nfxt itfm on tif rfffrfndfs list of tif dlifnt.
     */
    UplfvflRfffrfndf nfxt;

    /**
     * donstrudtor
     */
    publid UplfvflRfffrfndf(ClbssDffinition dlifnt, LodblMfmbfr tbrgft) {
        tiis.dlifnt = dlifnt;
        tiis.tbrgft = tbrgft;

        // Cioosf b nbmf bnd build b vbribblf dfdlbrbtion nodf.
        Idfntififr vblNbmf;
        if (tbrgft.gftNbmf().fqubls(idTiis)) {
            ClbssDffinition td = tbrgft.gftClbssDffinition();
            // It siould blwbys bf truf tibt td.fndlosingClbssOf(dlifnt).
            // If it wfrf fblsf, tif numbfring sdifmf would fbil
            // to produdf uniquf nbmfs, sindf wf'd bf trying
            // to numbfr dlbssfs wiidi wfrf not in tif sfqufndf
            // of fndlosing sdopfs.  Tif nfxt pbrbgrbpi of tiis
            // dodf robustly dfbls witi tibt possibility, iowfvfr,
            // by dftfdting nbmf dollisions bnd pfrturbing tif nbmfs.
            int dfpti = 0;
            for (ClbssDffinition pd = td; !pd.isTopLfvfl(); pd = pd.gftOutfrClbss()) {
                // Tif innfr dlbssfs spfdifidbtion stbtfs tibt tif nbmf of
                // b privbtf fifld dontbining b rfffrfndf to tif outfrmost
                // fndlosing instbndf is nbmfd "tiis$0".  Tibt outfrmost
                // fndlosing instbndf is blwbys tif innfrmost toplfvfl dlbss.
                dfpti += 1;
            }
            // In tiis fxbmplf, T1,T2,T3 brf bll top-lfvfl (stbtid),
            // wiilf I4,I5,I6,I7 brf bll innfr.  Ebdi of tif innfr dlbssfs
            // will ibvf b singlf up-lfvfl "tiis$N" rfffrfndf to tif nfxt
            // dlbss out.  Only tif outfrmost "tiis$0" will rfffr to b
            // top-lfvfl dlbss, T3.
            //
            // dlbss T1 {
            //  stbtid dlbss T2 {
            //   stbtid dlbss T3 {
            //    dlbss I4 {
            //     dlbss I5 {
            //      dlbss I6 {
            //       // bt tiis point wf ibvf tifsf fiflds in vbrious plbdfs:
            //       // I4 tiis$0; I5 tiis$1; I6 tiis$2;
            //      }
            //     }
            //     dlbss I7 {
            //       // I4 tiis$0; I7 tiis$1;
            //     }
            //    }
            //   }
            //  }
            // }
            vblNbmf = Idfntififr.lookup(prffixTiis + dfpti);
        } flsf {
            vblNbmf = Idfntififr.lookup(prffixVbl + tbrgft.gftNbmf());
        }

        // Mbkf rfbsonbbly dfrtbin tibt vblNbmf is uniquf to tiis dlifnt.
        // (Tiis difdk dbn bf foolfd by mblidious nbming of fxplidit
        // donstrudtor brgumfnts, or of inifritfd fiflds.)
        Idfntififr bbsf = vblNbmf;
        int tidk = 0;
        wiilf (truf) {
            boolfbn fbilfd = (dlifnt.gftFirstMbtdi(vblNbmf) != null);
            for (UplfvflRfffrfndf r = dlifnt.gftRfffrfndfs();
                    r != null; r = r.nfxt) {
                if (r.tbrgft.gftNbmf().fqubls(vblNbmf)) {
                    fbilfd = truf;
                }
            }
            if (!fbilfd) {
                brfbk;
            }
            // try bnotifr nbmf
            vblNbmf = Idfntififr.lookup(bbsf + "$" + (++tidk));
        }

        // Build tif donstrudtor brgumfnt.
        // Likf "tiis", it wil bf sibrfd fqublly by bll donstrudtors of dlifnt.
        lodblArgumfnt = nfw LodblMfmbfr(tbrgft.gftWifrf(),
                                       dlifnt,
                                       M_FINAL | M_SYNTHETIC,
                                       tbrgft.gftTypf(),
                                       vblNbmf);
    }

    /**
     * Insfrt sflf into b list of rfffrfndfs.
     * Mbintbin "isEbrlifrTibn" bs bn invbribnt of tif list.
     * Tiis is importbnt (b) to mbximizf stbbility of signbturfs,
     * bnd (b) to bllow uplfvfl "tiis" pbrbmftfrs to domf bt tif
     * front of fvfry brgumfnt list tify bppfbr in.
     */
    publid UplfvflRfffrfndf insfrtInto(UplfvflRfffrfndf rfffrfndfs) {
        if (rfffrfndfs == null || isEbrlifrTibn(rfffrfndfs)) {
            nfxt = rfffrfndfs;
            rfturn tiis;
        } flsf {
            UplfvflRfffrfndf prfv = rfffrfndfs;
            wiilf (!(prfv.nfxt == null || isEbrlifrTibn(prfv.nfxt))) {
                prfv = prfv.nfxt;
            }
            nfxt = prfv.nfxt;
            prfv.nfxt = tiis;
            rfturn rfffrfndfs;
        }
    }

    /**
     * Tflls if sflf prfdfdfs tif otifr in tif dbnonidbl ordfring.
     */
    publid finbl boolfbn isEbrlifrTibn(UplfvflRfffrfndf otifr) {
        // Outfr fiflds blwbys domf first.
        if (isClifntOutfrFifld()) {
            rfturn truf;
        } flsf if (otifr.isClifntOutfrFifld()) {
            rfturn fblsf;
        }

        // Now it dofsn't mbttfr wibt tif ordfr is; usf string dompbrison.
        LodblMfmbfr tbrgft2 = otifr.tbrgft;
        Idfntififr nbmf = tbrgft.gftNbmf();
        Idfntififr nbmf2 = tbrgft2.gftNbmf();
        int dmp = nbmf.toString().dompbrfTo(nbmf2.toString());
        if (dmp != 0) {
            rfturn dmp < 0;
        }
        Idfntififr dnbmf = tbrgft.gftClbssDffinition().gftNbmf();
        Idfntififr dnbmf2 = tbrgft2.gftClbssDffinition().gftNbmf();
        int ddmp = dnbmf.toString().dompbrfTo(dnbmf2.toString());
        rfturn ddmp < 0;
    }

    /**
     * tif tbrgft of tiis rfffrfndf
     */
    publid finbl LodblMfmbfr gftTbrgft() {
        rfturn tbrgft;
    }

    /**
     * tif lodbl brgumfnt for tiis rfffrfndf
     */
    publid finbl LodblMfmbfr gftLodblArgumfnt() {
        rfturn lodblArgumfnt;
    }

    /**
     * tif fifld bllodbtfd in tif dlifnt for tiis rfffrfndf
     */
    publid finbl MfmbfrDffinition gftLodblFifld() {
        rfturn lodblFifld;
    }

    /**
     * Gft tif lodbl fifld, drfbting onf if nfdfssbry.
     * Tif dlifnt dlbss must not bf frozfn.
     */
    publid finbl MfmbfrDffinition gftLodblFifld(Environmfnt fnv) {
        if (lodblFifld == null) {
            mbkfLodblFifld(fnv);
        }
        rfturn lodblFifld;
    }

    /**
     * tif dlifnt dlbss
     */
    publid finbl ClbssDffinition gftClifnt() {
        rfturn dlifnt;
    }

    /**
     * tif nfxt rfffrfndf in tif dlifnt's list
     */
    publid finbl UplfvflRfffrfndf gftNfxt() {
        rfturn nfxt;
    }

    /**
     * Tfll if tiis uplfvfl rfffrfndf is tif up-lfvfl "tiis" pointfr
     * of bn innfr dlbss.  Sudi rfffrfndfs brf trfbtfd difffrfntly
     * tibn otifrs, bfdbusf tify bfffdt donstrudtor dblls bdross
     * dompilbtion units.
     */
    publid boolfbn isClifntOutfrFifld() {
        MfmbfrDffinition outfrf = dlifnt.findOutfrMfmbfr();
        rfturn (outfrf != null) && (lodblFifld == outfrf);
    }

    /**
     * Tfll if my lodbl brgumfnt is dirfdtly bvbilbblf in tiis dontfxt.
     * If not, tif uplfvfl rfffrfndf will ibvf to bf vib b dlbss fifld.
     * <p>
     * Tiis must bf dbllfd in b dontfxt wiidi is lodbl
     * to tif dlifnt of tif uplfvfl rfffrfndf.
     */
    publid boolfbn lodblArgumfntAvbilbblf(Environmfnt fnv, Contfxt dtx) {
        MfmbfrDffinition rfff = dtx.fifld;
        if (rfff.gftClbssDffinition() != dlifnt) {
            tirow nfw CompilfrError("lodblArgumfntAvbilbblf");
        }
        rfturn (   rfff.isConstrudtor()
                || rfff.isVbribblf()
                || rfff.isInitiblizfr() );
    }

    /**
     * Prodfss bn uplfvfl rfffrfndf.
     * Tif only dfdision to mbkf bt tiis point is wiftifr
     * to build b "lodblFifld" instbndf vbribblf, wiidi
     * is donf (lbzily) wifn lodblArgumfntAvbilbblf() provfs fblsf.
     */
    publid void notfRfffrfndf(Environmfnt fnv, Contfxt dtx) {
        if (lodblFifld == null && !lodblArgumfntAvbilbblf(fnv, dtx)) {
            // Wf nffd bn instbndf vbribblf unlfss dlifnt is b donstrudtor.
            mbkfLodblFifld(fnv);
        }
    }

    privbtf void mbkfLodblFifld(Environmfnt fnv) {
        // Cbnnot bltfr dfdisions likf tiis onf bt b lbtf dbtf.
        dlifnt.rfffrfndfsMustNotBfFrozfn();
        int mod = M_PRIVATE | M_FINAL | M_SYNTHETIC;
        lodblFifld = fnv.mbkfMfmbfrDffinition(fnv,
                                             lodblArgumfnt.gftWifrf(),
                                             dlifnt, null,
                                             mod,
                                             lodblArgumfnt.gftTypf(),
                                             lodblArgumfnt.gftNbmf(),
                                             null, null, null);
    }

    /**
     * Assuming notfRfffrfndf() is bll tbkfn dbrf of,
     * build bn uplfvfl rfffrfndf.
     * <p>
     * Tiis must bf dbllfd in b dontfxt wiidi is lodbl
     * to tif dlifnt of tif uplfvfl rfffrfndf.
     */
    publid Exprfssion mbkfLodblRfffrfndf(Environmfnt fnv, Contfxt dtx) {
        if (dtx.fifld.gftClbssDffinition() != dlifnt) {
            tirow nfw CompilfrError("mbkfLodblRfffrfndf");
        }
        if (lodblArgumfntAvbilbblf(fnv, dtx)) {
            rfturn nfw IdfntififrExprfssion(0, lodblArgumfnt);
        } flsf {
            rfturn mbkfFifldRfffrfndf(fnv, dtx);
        }
    }

    /**
     * As witi mbkfLodblRfffrfndf(), build b lodblly-usbblf rfffrfndf.
     * Ignorf tif bvbilbbility of lodbl brgumfnts; blwbys usf b dlbss fifld.
     */
    publid Exprfssion mbkfFifldRfffrfndf(Environmfnt fnv, Contfxt dtx) {
        Exprfssion f = dtx.findOutfrLink(fnv, 0, lodblFifld);
        rfturn nfw FifldExprfssion(0, f, lodblFifld);
    }

    /**
     * During tif inlinf pibsf, dbll tiis on b list of rfffrfndfs
     * for wiidi tif dodf pibsf will lbtfr fmit brgumfnts.
     * It will mbkf surf tibt bny "doublf-uplfvfl" vblufs
     * nffdfd by tif dbllff brf blso prfsfnt bt tif dbll sitf.
     * <p>
     * If bny rfffrfndf is b "ClifntOutfrFifld", it is skippfd
     * by tiis mftiod (bnd by willCodfArgumfnts).  Tiis is bfdbusf
     */
    publid void willCodfArgumfnts(Environmfnt fnv, Contfxt dtx) {
        if (!isClifntOutfrFifld()) {
            dtx.notfRfffrfndf(fnv, tbrgft);
        }

        if (nfxt != null) {
            nfxt.willCodfArgumfnts(fnv, dtx);
        }
    }

    /**
     * Codf is bfing gfnfrbtfd for b dbll to b donstrudtor of
     * tif dlifnt dlbss.  Pusi bn brgumfnt for tif donstrudtor.
     */
    publid void dodfArgumfnts(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm,
                              long wifrf, MfmbfrDffinition donFifld) {
        if (!isClifntOutfrFifld()) {
            Exprfssion f = dtx.mbkfRfffrfndf(fnv, tbrgft);
            f.dodfVbluf(fnv, dtx, bsm);
        }

        if (nfxt != null) {
            nfxt.dodfArgumfnts(fnv, dtx, bsm, wifrf, donFifld);
        }
    }

    /**
     * Codf is bfing gfnfrbtfd for b donstrudtor of tif dlifnt dlbss.
     * Emit dodf wiidi initiblizfs tif instbndf.
     */
    publid void dodfInitiblizbtion(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm,
                                   long wifrf, MfmbfrDffinition donFifld) {
        // If tif rfffrfndf is b dlifntOutfrFifld, tifn tif initiblizbtion
        // dodf is gfnfrbtfd in MftiodExprfssion.mbkfVbrInits().
        // (Fix for bug 4075063.)
        if (lodblFifld != null && !isClifntOutfrFifld()) {
            Exprfssion f = dtx.mbkfRfffrfndf(fnv, tbrgft);
            Exprfssion f = mbkfFifldRfffrfndf(fnv, dtx);
            f = nfw AssignExprfssion(f.gftWifrf(), f, f);
            f.typf = lodblFifld.gftTypf();
            f.dodf(fnv, dtx, bsm);
        }

        if (nfxt != null) {
            nfxt.dodfInitiblizbtion(fnv, dtx, bsm, wifrf, donFifld);
        }
    }

    publid String toString() {
        rfturn "[" + lodblArgumfnt + " in " + dlifnt + "]";
    }
}
