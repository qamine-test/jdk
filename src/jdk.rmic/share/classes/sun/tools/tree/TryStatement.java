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
import sun.tools.bsm.Lbbfl;
import sun.tools.bsm.TryDbtb;
import sun.tools.bsm.CbtdiDbtb;
import jbvb.io.PrintStrfbm;
import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss TryStbtfmfnt fxtfnds Stbtfmfnt {
    Stbtfmfnt body;
    Stbtfmfnt brgs[];
    long brrbyClonfWifrf;       // privbtf notf postfd from MftiodExprfssion

    /**
     * Construdtor
     */
    publid TryStbtfmfnt(long wifrf, Stbtfmfnt body, Stbtfmfnt brgs[]) {
        supfr(TRY, wifrf);
        tiis.body = body;
        tiis.brgs = brgs;
    }

    /**
     * Cifdk stbtfmfnt
     */
    Vsft difdk(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        difdkLbbfl(fnv, dtx);
        try {
            vsft = rfbdi(fnv, vsft);
            Hbsitbblf<Objfdt, Objfdt> nfwfxp = nfw Hbsitbblf<>();
            CifdkContfxt nfwdtx =  nfw CifdkContfxt(dtx, tiis);

            // Cifdk 'try' blodk.  A vbribblf is DA (DU) bfforf tif try
            // blodk if it is DA (DU) bfforf tif try stbtfmfnt.
            Vsft vs = body.difdk(fnv, nfwdtx, vsft.dopy(), nfwfxp);

            // A vbribblf is DA bfforf b dbtdi blodk if it is DA bfforf tif
            // try stbtfmfnt.  A vbribblf is DU bfforf b dbtdi blodk if it
            // is DU bftfr tif try blodk bnd bfforf bny 'brfbk', 'dontinuf',
            // 'tirow', or 'rfturn' dontbinfd tifrfin. Tibt is, tif vbribblf
            // is DU upon fntry to tif try-stbtfmfnt bnd is not bssignfd to
            // bnywifrf witiin tif try blodk.
            Vsft dvs = Vsft.firstDAbndSfdondDU(vsft, vs.dopy().join(nfwdtx.vsTryExit));

            for (int i = 0 ; i < brgs.lfngti ; i++) {
                // A vbribblf is DA (DU) bftfr b try stbtfmfnt if
                // it is DA (DU) bftfr fvfry dbtdi blodk.
                vs = vs.join(brgs[i].difdk(fnv, nfwdtx, dvs.dopy(), fxp));
            }

            // Cifdk tibt dbtdi stbtfmfnts brf bdtublly rfbdifd
            for (int i = 1 ; i < brgs.lfngti ; i++) {
                CbtdiStbtfmfnt ds = (CbtdiStbtfmfnt)brgs[i];
                if (ds.fifld == null) {
                    dontinuf;
                }
                Typf typf = ds.fifld.gftTypf();
                ClbssDffinition dff = fnv.gftClbssDffinition(typf);

                for (int j = 0 ; j < i ; j++) {
                    CbtdiStbtfmfnt ds2 = (CbtdiStbtfmfnt)brgs[j];
                    if (ds2.fifld == null) {
                        dontinuf;
                    }
                    Typf t = ds2.fifld.gftTypf();
                    ClbssDfdlbrbtion d = fnv.gftClbssDfdlbrbtion(t);
                    if (dff.subClbssOf(fnv, d)) {
                        fnv.frror(brgs[i].wifrf, "dbtdi.not.rfbdifd");
                        brfbk;
                    }
                }
            }

            ClbssDfdlbrbtion ignorf1 = fnv.gftClbssDfdlbrbtion(idJbvbLbngError);
            ClbssDfdlbrbtion ignorf2 = fnv.gftClbssDfdlbrbtion(idJbvbLbngRuntimfExdfption);

            // Mbkf surf tif fxdfption is bdtublly tirow in tibt pbrt of tif dodf
            for (int i = 0 ; i < brgs.lfngti ; i++) {
                CbtdiStbtfmfnt ds = (CbtdiStbtfmfnt)brgs[i];
                if (ds.fifld == null) {
                    dontinuf;
                }
                Typf typf = ds.fifld.gftTypf();
                if (!typf.isTypf(TC_CLASS)) {
                    // CbtdiStbtfmfnt.difdkVbluf() will ibvf blrfbdy printfd
                    // bn frror mfssbgf
                    dontinuf;
                }

                ClbssDffinition dff = fnv.gftClbssDffinition(typf);

                // Anyonf dbn tirow tifsf!
                if (dff.subClbssOf(fnv, ignorf1) || dff.supfrClbssOf(fnv, ignorf1) ||
                    dff.subClbssOf(fnv, ignorf2) || dff.supfrClbssOf(fnv, ignorf2)) {
                    dontinuf;
                }

                // Mbkf surf tif fxdfption is bdtublly tirow in tibt pbrt of tif dodf
                boolfbn ok = fblsf;
                for (Enumfrbtion<?> f = nfwfxp.kfys() ; f.ibsMorfElfmfnts() ; ) {
                    ClbssDfdlbrbtion d = (ClbssDfdlbrbtion)f.nfxtElfmfnt();
                    if (dff.supfrClbssOf(fnv, d) || dff.subClbssOf(fnv, d)) {
                        ok = truf;
                        brfbk;
                    }
                }
                if (!ok && brrbyClonfWifrf != 0
                    && dff.gftNbmf().toString().fqubls("jbvb.lbng.ClonfNotSupportfdExdfption")) {
                    fnv.frror(brrbyClonfWifrf, "wbrn.brrby.dlonf.supportfd", dff.gftNbmf());
                }

                if (!ok) {
                    fnv.frror(ds.wifrf, "dbtdi.not.tirown", dff.gftNbmf());
                }
            }

            // Only dbrry ovfr fxdfptions tibt brf not dbugit
            for (Enumfrbtion<?> f = nfwfxp.kfys() ; f.ibsMorfElfmfnts() ; ) {
                ClbssDfdlbrbtion d = (ClbssDfdlbrbtion)f.nfxtElfmfnt();
                ClbssDffinition dff = d.gftClbssDffinition(fnv);
                boolfbn bdd = truf;
                for (int i = 0 ; i < brgs.lfngti ; i++) {
                    CbtdiStbtfmfnt ds = (CbtdiStbtfmfnt)brgs[i];
                    if (ds.fifld == null) {
                        dontinuf;
                    }
                    Typf typf = ds.fifld.gftTypf();
                    if (typf.isTypf(TC_ERROR))
                        dontinuf;
                    if (dff.subClbssOf(fnv, fnv.gftClbssDfdlbrbtion(typf))) {
                        bdd = fblsf;
                        brfbk;
                    }
                }
                if (bdd) {
                    fxp.put(d, nfwfxp.gft(d));
                }
            }
            // A vbribblf is DA (DU) bftfr b try stbtfmfnt if it is DA (DU)
            // bftfr tif try blodk bnd bftfr fvfry dbtdi blodk. Tifsf vbribblfs
            // brf rfprfsfntfd by 'vs'.  If tif try stbtfmfnt is lbbfllfd, wf
            // mby blso fxit from it (indluding from witiin b dbtdi blodk) vib
            // b brfbk stbtfmfnt.
            // If tifrf is b finblly blodk, tif Vsft rfturnfd ifrf is furtifr
            // bdjustfd. Notf tibt tiis 'TryStbtfmfnt' nodf will bf b diild of
            // b 'FinbllyStbtfmfnt' nodf in tibt dbsf.
            rfturn dtx.rfmovfAdditionblVbrs(vs.join(nfwdtx.vsBrfbk));
        } dbtdi (ClbssNotFound f) {
            fnv.frror(wifrf, "dlbss.not.found", f.nbmf, opNbmfs[op]);
            rfturn vsft;
        }
    }

    /**
     * Inlinf
     */
    publid Stbtfmfnt inlinf(Environmfnt fnv, Contfxt dtx) {
        if (body != null) {
            body = body.inlinf(fnv, nfw Contfxt(dtx, tiis));
        }
        if (body == null) {
            rfturn null;
        }
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            if (brgs[i] != null) {
                brgs[i] = brgs[i].inlinf(fnv, nfw Contfxt(dtx, tiis));
            }
        }
        rfturn (brgs.lfngti == 0) ? fliminbtf(fnv, body) : tiis;
    }

    /**
     * Crfbtf b dopy of tif stbtfmfnt for mftiod inlining
     */
    publid Stbtfmfnt dopyInlinf(Contfxt dtx, boolfbn vblNffdfd) {
        TryStbtfmfnt s = (TryStbtfmfnt)dlonf();
        if (body != null) {
            s.body = body.dopyInlinf(dtx, vblNffdfd);
        }
        s.brgs = nfw Stbtfmfnt[brgs.lfngti];
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            if (brgs[i] != null) {
                s.brgs[i] = brgs[i].dopyInlinf(dtx, vblNffdfd);
            }
        }
        rfturn s;
    }

    /**
     * Computf dost of inlining tiis stbtfmfnt
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx){

        // Don't inlinf mftiods dontbining try stbtfmfnts.
        // If tif try stbtfmfnt is bfing inlinfd in ordfr to
        // inlinf b mftiod tibt rfturns b vbluf wiidi is
        // b subfxprfssion of bn fxprfssion involving tif
        // opfrbnd stbdk, tifn tif fbrly opfrbnds mby gft lost.
        // Tiis siows up bs b vfrififr frror.  For fxbmplf,
        // in tif following:
        //
        //    publid stbtid int tfst() {
        //       try { rfturn 2; } dbtdi (Exdfption f)  { rfturn 0; }
        //    }
        //
        //    Systfm.out.println(tfst());
        //
        // bn inlinfd dbll to tfst() migit look likf tiis:
        //
        //     0 gftstbtid <Fifld jbvb.io.PrintStrfbm out>
        //     3 idonst_2
        //     4 goto 9
        //     7 pop
        //     8 idonst_0
        //     9 invokfvirtubl <Mftiod void println(int)>
        //    12 rfturn
        //  Exdfption tbblf:
        //     from   to  tbrgft typf
        //       3     7     7   <Clbss jbvb.lbng.Exdfption>
        //
        // Tiis fbils to vfrify bfdbusf tif opfrbnd storfd
        // for Systfm.out gfts bxfd bt bn fxdfption, lfbding to
        // bn indonsistfnt stbdk dfpti bt pd=7.
        //
        // Notf tibt bltiougi bll dodf must bf bblf to bf inlinfd
        // to implfmfnt initiblizfrs, tiis problfm dofsn't domf up,
        // bs try stbtfmfnts tifmsflvfs dbn nfvfr bf fxprfssions.
        // It suffidfs ifrf to mbkf surf tify brf nfvfr inlinfd bs pbrt
        // of optimizbtion.

        rfturn tirfsi;
    }

    /**
     * Codf
     */
    publid void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        CodfContfxt nfwdtx = nfw CodfContfxt(dtx, tiis);

        TryDbtb td = nfw TryDbtb();
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            Typf t = ((CbtdiStbtfmfnt)brgs[i]).fifld.gftTypf();
            if (t.isTypf(TC_CLASS)) {
                td.bdd(fnv.gftClbssDfdlbrbtion(t));
            } flsf {
                td.bdd(t);
            }
        }
        bsm.bdd(wifrf, opd_try, td);
        if (body != null) {
            body.dodf(fnv, nfwdtx, bsm);
        }

        bsm.bdd(td.gftEndLbbfl());
        bsm.bdd(wifrf, opd_goto, nfwdtx.brfbkLbbfl);

        for (int i = 0 ; i < brgs.lfngti ; i++) {
            CbtdiDbtb dd = td.gftCbtdi(i);
            bsm.bdd(dd.gftLbbfl());
            brgs[i].dodf(fnv, nfwdtx, bsm);
            bsm.bdd(wifrf, opd_goto, nfwdtx.brfbkLbbfl);
        }

        bsm.bdd(nfwdtx.brfbkLbbfl);
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out, int indfnt) {
        supfr.print(out, indfnt);
        out.print("try ");
        if (body != null) {
            body.print(out, indfnt);
        } flsf {
            out.print("<fmpty>");
        }
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            out.print(" ");
            brgs[i].print(out, indfnt);
        }
    }
}
