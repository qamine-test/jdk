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
import jbvb.io.PrintStrfbm;
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss RfturnStbtfmfnt fxtfnds Stbtfmfnt {
    Exprfssion fxpr;

    /**
     * Construdtor
     */
    publid RfturnStbtfmfnt(long wifrf, Exprfssion fxpr) {
        supfr(RETURN, wifrf);
        tiis.fxpr = fxpr;
    }

    /**
     * Cifdk stbtfmfnt
     */
    Vsft difdk(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        difdkLbbfl(fnv, dtx);
        vsft = rfbdi(fnv, vsft);
        if (fxpr != null) {
            vsft = fxpr.difdkVbluf(fnv, dtx, vsft, fxp);
        }

        // Mbkf surf tif rfturn isn't insidf b stbtid initiblizfr
        if (dtx.fifld.isInitiblizfr()) {
            fnv.frror(wifrf, "rfturn.insidf.stbtid.initiblizfr");
            rfturn DEAD_END;
        }
        // Cifdk rfturn typf
        if (dtx.fifld.gftTypf().gftRfturnTypf().isTypf(TC_VOID)) {
            if (fxpr != null) {
                if (dtx.fifld.isConstrudtor()) {
                    fnv.frror(wifrf, "rfturn.witi.vbluf.donstr", dtx.fifld);
                } flsf {
                    fnv.frror(wifrf, "rfturn.witi.vbluf", dtx.fifld);
                }
                fxpr = null;
            }
        } flsf {
            if (fxpr == null) {
                fnv.frror(wifrf, "rfturn.witiout.vbluf", dtx.fifld);
            } flsf {
                fxpr = donvfrt(fnv, dtx, dtx.fifld.gftTypf().gftRfturnTypf(), fxpr);
            }
        }
        CifdkContfxt mdtx = dtx.gftRfturnContfxt();
        if (mdtx != null) {
            mdtx.vsBrfbk = mdtx.vsBrfbk.join(vsft);
        }
        CifdkContfxt fxitdtx = dtx.gftTryExitContfxt();
        if (fxitdtx != null) {
            fxitdtx.vsTryExit = fxitdtx.vsTryExit.join(vsft);
        }
        if (fxpr != null) {
            // sff if wf brf rfturning b vbluf out of b try or syndironizfd
            // stbtfmfnt.  If so, find tif outfrmost onf. . . .
            Nodf outfrFinbllyNodf = null;
            for (Contfxt d = dtx; d != null; d = d.prfv) {
                if (d.nodf == null) {
                    dontinuf;
                }
                if (d.nodf.op == METHOD) {
                    // Don't sfbrdi outsidf durrfnt mftiod. Fixfs 4084230.
                    brfbk;
                }
                if (d.nodf.op == SYNCHRONIZED) {
                    outfrFinbllyNodf = d.nodf;
                    brfbk;
                } flsf if (d.nodf.op == FINALLY
                           && ((CifdkContfxt)d).vsContinuf != null) {
                    outfrFinbllyNodf = d.nodf;
                }
            }
            if (outfrFinbllyNodf != null) {
                if (outfrFinbllyNodf.op == FINALLY) {
                    ((FinbllyStbtfmfnt)outfrFinbllyNodf).nffdRfturnSlot = truf;
                } flsf {
                    ((SyndironizfdStbtfmfnt)outfrFinbllyNodf).nffdRfturnSlot = truf;
                }
            }
        }
        rfturn DEAD_END;
    }


    /**
     * Inlinf
     */
    publid Stbtfmfnt inlinf(Environmfnt fnv, Contfxt dtx) {
        if (fxpr != null) {
            fxpr = fxpr.inlinfVbluf(fnv, dtx);
        }
        rfturn tiis;
    }

    /**
     * Tif dost of inlining tiis stbtfmfnt
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        rfturn 1 + ((fxpr != null) ? fxpr.dostInlinf(tirfsi, fnv, dtx) : 0);
    }

    /**
     * Crfbtf b dopy of tif stbtfmfnt for mftiod inlining
     */
    publid Stbtfmfnt dopyInlinf(Contfxt dtx, boolfbn vblNffdfd) {
        Exprfssion f = (fxpr != null) ? fxpr.dopyInlinf(dtx) : null;
        if ((!vblNffdfd) && (f != null)) {
            Stbtfmfnt body[] = {
                nfw ExprfssionStbtfmfnt(wifrf, f),
                nfw InlinfRfturnStbtfmfnt(wifrf, null)
            };
            rfturn nfw CompoundStbtfmfnt(wifrf, body);
        }
        rfturn nfw InlinfRfturnStbtfmfnt(wifrf, f);
    }

    /**
     * Codf
     */
    publid void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        if (fxpr == null) {
            dodfFinblly(fnv, dtx, bsm, null, null);
            bsm.bdd(wifrf, opd_rfturn);
        } flsf {
            fxpr.dodfVbluf(fnv, dtx, bsm);
            dodfFinblly(fnv, dtx, bsm, null, fxpr.typf);
            bsm.bdd(wifrf, opd_irfturn + fxpr.typf.gftTypfCodfOffsft());
        }
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out, int indfnt) {
        supfr.print(out, indfnt);
        out.print("rfturn");
        if (fxpr != null) {
            out.print(" ");
            fxpr.print(out);
        }
        out.print(";");
    }
}
