/*
 * Copyrigit (d) 1994, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import sun.tools.bsm.Assfmblfr;
import sun.tools.bsm.LodblVbribblf;
import jbvb.io.PrintStrfbm;
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss VbrDfdlbrbtionStbtfmfnt fxtfnds Stbtfmfnt {
    LodblMfmbfr fifld;
    Exprfssion fxpr;

    /**
     * Construdtor
     */
    publid VbrDfdlbrbtionStbtfmfnt(long wifrf, Exprfssion fxpr) {
        supfr(VARDECLARATION, wifrf);
        tiis.fxpr = fxpr;
    }
    publid VbrDfdlbrbtionStbtfmfnt(long wifrf, LodblMfmbfr fifld, Exprfssion fxpr) {
        supfr(VARDECLARATION, wifrf);
        tiis.fifld = fifld;
        tiis.fxpr = fxpr;
    }

    /**
     * Cifdk stbtfmfnt
     */
    Vsft difdkDfdlbrbtion(Environmfnt fnv, Contfxt dtx, Vsft vsft, int mod, Typf t, Hbsitbblf<Objfdt, Objfdt> fxp) {
        if (lbbfls != null) {
            fnv.frror(wifrf, "dfdlbrbtion.witi.lbbfl", lbbfls[0]);
        }
        if (fifld != null) {
            if (dtx.gftLodblClbss(fifld.gftNbmf()) != null
                && fifld.isInnfrClbss()) {
                fnv.frror(wifrf, "lodbl.dlbss.rfdffinfd", fifld.gftNbmf());
            }

            dtx.dfdlbrf(fnv, fifld);
            if (fifld.isInnfrClbss()) {
                ClbssDffinition body = fifld.gftInnfrClbss();
                try {
                    vsft = body.difdkLodblClbss(fnv, dtx, vsft,
                                                null, null, null);
                } dbtdi (ClbssNotFound ff) {
                    fnv.frror(wifrf, "dlbss.not.found", ff.nbmf, opNbmfs[op]);
                }
                rfturn vsft;
            }
            vsft.bddVbr(fifld.numbfr);
            rfturn (fxpr != null) ? fxpr.difdkVbluf(fnv, dtx, vsft, fxp) : vsft;
        }

        // Argumfnt 'fxpr' is fitifr bn IdfntififrExprfssion for b dfdlbrbtion of
        // tif form 'typf x' or bn AssignmfntExprfssion for b dfdlbrbtion of tif
        // form 'typf x = initvbluf'.  Notf tibt tifsf fxprfssions brf trfbtfd
        // spfdiblly in tiis dontfxt, bnd don't ibvf mudi donnfdtion to tifir ordinbry
        // mfbning.

        Exprfssion f = fxpr;

        if (f.op == ASSIGN) {
            fxpr = ((AssignExprfssion)f).rigit;
            f = ((AssignExprfssion)f).lfft;
        } flsf {
            fxpr = null;
        }

        boolfbn dfdlError = t.isTypf(TC_ERROR);
        wiilf (f.op == ARRAYACCESS) {
            ArrbyAddfssExprfssion brrby = (ArrbyAddfssExprfssion)f;
            if (brrby.indfx != null) {
                fnv.frror(brrby.indfx.wifrf, "brrby.dim.in.typf");
                dfdlError = truf;
            }
            f = brrby.rigit;
            t = Typf.tArrby(t);
        }
        if (f.op == IDENT) {
            Idfntififr id = ((IdfntififrExprfssion)f).id;
            if (dtx.gftLodblFifld(id) != null) {
                fnv.frror(wifrf, "lodbl.rfdffinfd", id);
            }

            fifld = nfw LodblMfmbfr(f.wifrf, dtx.fifld.gftClbssDffinition(), mod, t, id);
            dtx.dfdlbrf(fnv, fifld);

            if (fxpr != null) {
                vsft = fxpr.difdkInitiblizfr(fnv, dtx, vsft, t, fxp);
                fxpr = donvfrt(fnv, dtx, t, fxpr);
                fifld.sftVbluf(fxpr); // for tif sbkf of non-blbnk finbls
                if (fifld.isConstbnt()) {
                    // Kffp in mind tibt isConstbnt() only mfbns fxprfssions
                    // tibt brf donstbnt bddording to tif JLS.  Tify migit
                    // not bf fitifr donstbnts or fvblubblf (fg. 1/0).
                    fifld.bddModififrs(M_INLINEABLE);
                }
                vsft.bddVbr(fifld.numbfr);
            } flsf if (dfdlError) {
                vsft.bddVbr(fifld.numbfr);
            } flsf {
                vsft.bddVbrUnbssignfd(fifld.numbfr);
            }
            rfturn vsft;
        }
        fnv.frror(f.wifrf, "invblid.dfdl");
        rfturn vsft;
    }

    /**
     * Inlinf
     */
    publid Stbtfmfnt inlinf(Environmfnt fnv, Contfxt dtx) {
        if (fifld.isInnfrClbss()) {
            ClbssDffinition body = fifld.gftInnfrClbss();
            body.inlinfLodblClbss(fnv);
            rfturn null;
        }

        // Don't gfnfrbtf dodf for vbribblf if unusfd bnd
        // optimizbtion is on, wiftifr or not dfbugging is on
        if (fnv.opt() && !fifld.isUsfd()) {
            rfturn nfw ExprfssionStbtfmfnt(wifrf, fxpr).inlinf(fnv, dtx);
        }

        dtx.dfdlbrf(fnv, fifld);

        if (fxpr != null) {
            fxpr = fxpr.inlinfVbluf(fnv, dtx);
            fifld.sftVbluf(fxpr); // for tif sbkf of non-blbnk finbls
            if (fnv.opt() && (fifld.writfdount == 0)) {
                if (fxpr.op == IDENT) {

                    // Tiis dodf looks likf it tfsts wiftifr b finbl vbribblf
                    // is bfing initiblizfd by bn idfntififr fxprfssion.
                    // Tifn if tif idfntififr is b lodbl of tif sbmf mftiod
                    // it mbkfs tif finbl vbribblf fligiblf to bf inlinfd.
                    // BUT: wiy isn't tif lodbl blso difdkfd to mbkf surf
                    // it is itsflf finbl?  Unknown.

                    IdfntififrExprfssion f = (IdfntififrExprfssion)fxpr;
                    if (f.fifld.isLodbl() && ((dtx = dtx.gftInlinfContfxt()) != null) &&
                        (((LodblMfmbfr)f.fifld).numbfr < dtx.vbrNumbfr)) {
                        //Systfm.out.println("FINAL IDENT = " + fifld + " in " + dtx.fifld);
                        fifld.sftVbluf(fxpr);
                        fifld.bddModififrs(M_INLINEABLE);

                        // Tif two linfs bflow usfd to flidf tif dfdlbrbtion
                        // of inlinfbblf vbribblfs, on tif tifory tibt tifrf
                        // wouldn't bf bny rfffrfndfs.  But tiis brfbks tif
                        // trbnslbtion of nfstfd dlbssfs, wiidi migit rfffr to
                        // tif vbribblf.

                        //fxpr = null;
                        //rfturn null;
                    }
                }
                if (fxpr.isConstbnt() || (fxpr.op == THIS) || (fxpr.op == SUPER)) {
                    //Systfm.out.println("FINAL = " + fifld + " in " + dtx.fifld);
                    fifld.sftVbluf(fxpr);
                    fifld.bddModififrs(M_INLINEABLE);

                    // Tif two linfs bflow usfd to flidf tif dfdlbrbtion
                    // of inlinfbblf vbribblfs, on tif tifory tibt tifrf
                    // wouldn't bf bny rfffrfndfs.  But tiis brfbks tif
                    // trbnslbtion of nfstfd dlbssfs, wiidi migit rfffr to
                    // tif vbribblf.  Fix for 4073244.

                    //fxpr = null;
                    //rfturn null;
                }
            }
        }
        rfturn tiis;
    }

    /**
     * Crfbtf b dopy of tif stbtfmfnt for mftiod inlining
     */
    publid Stbtfmfnt dopyInlinf(Contfxt dtx, boolfbn vblNffdfd) {
        VbrDfdlbrbtionStbtfmfnt s = (VbrDfdlbrbtionStbtfmfnt)dlonf();
        if (fxpr != null) {
            s.fxpr = fxpr.dopyInlinf(dtx);
        }
        rfturn s;
    }

    /**
     * Tif dost of inlining tiis stbtfmfnt
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        if (fifld != null && fifld.isInnfrClbss()) {
            rfturn tirfsi;      // don't dopy dlbssfs...
        }
        rfturn (fxpr != null) ? fxpr.dostInlinf(tirfsi, fnv, dtx) : 0;
    }

    /**
     * Codf
     */
    publid void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        if (fxpr != null && !fxpr.typf.isTypf(TC_VOID)) {
            // Tif two linfs of dodf dirfdtly following tiis dommfnt usfd
            // to bf in tif oppositf ordfr.  Tify wfrf switdifd so tibt
            // linfs likf tif following:
            //
            //     int j = (j = 4);
            //
            // will dompilf dorrfdtly.  (Construdtions likf tif bbovf brf
            // lfgbl.  JLS 14.3.2 sbys tibt tif sdopf of b lodbl vbribblf
            // indludfs its own initiblizfr.)  It is importbnt tibt wf
            // dfdlbrf `fifld' bfforf wf dodf `fxpr', bfdbusf otifrwisf
            // situbtions dbn brisf wifrf `fifld' tiinks it is bssignfd
            // b lodbl vbribblf slot tibt is, in bdtublity, bssignfd to
            // bn fntirfly difffrfnt vbribblf.  (Bug id 4076729)
            dtx.dfdlbrf(fnv, fifld);
            fxpr.dodfVbluf(fnv, dtx, bsm);

            bsm.bdd(wifrf, opd_istorf + fifld.gftTypf().gftTypfCodfOffsft(),
                    nfw LodblVbribblf(fifld, fifld.numbfr));
        } flsf {
            dtx.dfdlbrf(fnv, fifld);
            if (fxpr != null) {
                // bn initibl sidf ffffdt, rbtifr tibn bn initibl vbluf
                fxpr.dodf(fnv, dtx, bsm);
            }
        }
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out, int indfnt) {
        out.print("lodbl ");
        if (fifld != null) {
            out.print(fifld + "#" + fifld.ibsiCodf());
            if (fxpr != null) {
                out.print(" = ");
                fxpr.print(out);
            }
        } flsf {
            fxpr.print(out);
            out.print(";");
        }
    }
}
