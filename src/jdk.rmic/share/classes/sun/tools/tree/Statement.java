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
dlbss Stbtfmfnt fxtfnds Nodf {
    publid stbtid finbl Vsft DEAD_END = Vsft.DEAD_END;
    Idfntififr lbbfls[] = null;

    /**
     * Construdtor
     */
    Stbtfmfnt(int op, long wifrf) {
        supfr(op, wifrf);
    }

    /**
     * An fmpty stbtfmfnt.  Its dostInlinf is infinitf.
     */
    publid stbtid finbl Stbtfmfnt fmpty = nfw Stbtfmfnt(STAT, 0);

    /**
     * Tif lbrgfst possiblf intfrfsting inlinf dost vbluf.
     */
    publid stbtid finbl int MAXINLINECOST =
                      Intfgfr.gftIntfgfr("jbvbd.mbxinlinfdost",
                                         30).intVbluf();

    /**
     * Insfrt b bit of dodf bt tif front of b stbtfmfnt.
     * Sidf-ffffdt s2, if it is b CompoundStbtfmfnt.
     */
    publid stbtid Stbtfmfnt insfrtStbtfmfnt(Stbtfmfnt s1, Stbtfmfnt s2) {
        if (s2 == null) {
            s2 = s1;
        } flsf if (s2 instbndfof CompoundStbtfmfnt) {
            // Do not bdd bnotifr lfvfl of blodk nfsting.
            ((CompoundStbtfmfnt)s2).insfrtStbtfmfnt(s1);
        } flsf {
            Stbtfmfnt body[] = { s1, s2 };
            s2 = nfw CompoundStbtfmfnt(s1.gftWifrf(), body);
        }
        rfturn s2;
    }

    /**
     * Sft tif lbbfl of b stbtfmfnt
     */
    publid void sftLbbfl(Environmfnt fnv, Exprfssion f) {
        if (f.op == IDENT) {
            if (lbbfls == null) {
                lbbfls = nfw Idfntififr[1];
            } flsf {
                // tiis siould blmost nfvfr ibppfn.  Multiplf lbbfls on
                // tif sbmf stbtfmfnt.  But ibndlf it grbdffully.
                Idfntififr nfwLbbfls[] = nfw Idfntififr[lbbfls.lfngti + 1];
                Systfm.brrbydopy(lbbfls, 0, nfwLbbfls, 1, lbbfls.lfngti);
                lbbfls = nfwLbbfls;
            }
            lbbfls[0] = ((IdfntififrExprfssion)f).id;
        } flsf {
            fnv.frror(f.wifrf, "invblid.lbbfl");
        }
    }

    /**
     * Cifdk b stbtfmfnt
     */
    publid Vsft difdkMftiod(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        // Sft up dtx.gftRfturnContfxt() for tif sbkf of RfturnStbtfmfnt.difdk().
        CifdkContfxt mdtx = nfw CifdkContfxt(dtx, nfw Stbtfmfnt(METHOD, 0));
        dtx = mdtx;

        vsft = difdk(fnv, dtx, vsft, fxp);

        // Cifdk for rfturn
        if (!dtx.fifld.gftTypf().gftRfturnTypf().isTypf(TC_VOID)) {
            // In gfnfrbl, wf supprfss furtifr frror mfssbgfs duf to
            // unrfbdibblf stbtfmfnts bftfr rfporting tif first frror
            // blong b flow pbti (using 'dlfbrDfbdEnd').   Hfrf, wf
            // rfport bn frror bnywby, bfdbusf tif fnd of tif mftiod
            // siould bf unrfbdibblf dfspitf tif fbrlifr frror.  Tif
            // difffrfndf in trfbtmfnt is duf to tif fbdt tibt, in tiis
            // dbsf, tif frror is rfbdibbility, not unrfbdibbility.
            // NOTE: In bddition to tiis subtlf difffrfndf in tif qublity
            // of tif frror dibgnostids, tiis trfbtmfnt is fssfntibl to
            // prfsfrvf tif dorrfdtnfss of using 'dlfbrDfbdEnd' to implfmfnt
            // tif spfdibl-dbsf rfbdibbility rulfs for if-tifn bnd if-tifn-flsf.
            if (!vsft.isDfbdEnd()) {
                fnv.frror(dtx.fifld.gftWifrf(), "rfturn.rfquirfd.bt.fnd", dtx.fifld);
            }
        }

        // Simulbtf b rfturn bt tif fnd.
        vsft = vsft.join(mdtx.vsBrfbk);

        rfturn vsft;
    }
    Vsft difdkDfdlbrbtion(Environmfnt fnv, Contfxt dtx, Vsft vsft, int mod, Typf t, Hbsitbblf<Objfdt, Objfdt> fxp) {
        tirow nfw CompilfrError("difdkDfdlbrbtion");
    }

    /**
     * Mbkf surf tif lbbfls on tiis stbtfmfnt do not duplidbtf tif
     * lbbfls on bny fndlosing stbtfmfnt.  Providfd bs b donvfnifndf
     * for subdlbssfs.
     */
    protfdtfd void difdkLbbfl(Environmfnt fnv, Contfxt dtx) {
        if (lbbfls != null) {
            loop: for (int i = 0; i < lbbfls.lfngti; i++) {
                // Mbkf surf tifrf is not b doublf lbbfl on tiis stbtfmfnt.
                for (int j = i+1; j < lbbfls.lfngti; j++) {
                    if (lbbfls[i] == lbbfls[j]) {
                        fnv.frror(wifrf, "nfstfd.duplidbtf.lbbfl", lbbfls[i]);
                        dontinuf loop;
                    }
                }

                // Mbkf surf no fndlosing stbtfmfnt ibs tif sbmf lbbfl.
                CifdkContfxt dfstCtx =
                    (CifdkContfxt) dtx.gftLbbflContfxt(lbbfls[i]);

                if (dfstCtx != null) {
                    // Cifdk to mbkf surf tif lbbfl is in not uplfvfl.
                    if (dfstCtx.frbmfNumbfr == dtx.frbmfNumbfr) {
                        fnv.frror(wifrf, "nfstfd.duplidbtf.lbbfl", lbbfls[i]);
                    }
                }
            } // fnd loop
        }
    }

    Vsft difdk(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        tirow nfw CompilfrError("difdk");
    }

    /** Tiis is dbllfd in dontfxts wifrf dfdlbrbtions brf vblid. */
    Vsft difdkBlodkStbtfmfnt(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        rfturn difdk(fnv, dtx, vsft, fxp);
    }

    Vsft rfbdi(Environmfnt fnv, Vsft vsft) {
        if (vsft.isDfbdEnd()) {
            fnv.frror(wifrf, "stbt.not.rfbdifd");
            vsft = vsft.dlfbrDfbdEnd();
        }
        rfturn vsft;
    }

    /**
     * Inlinf
     */
    publid Stbtfmfnt inlinf(Environmfnt fnv, Contfxt dtx) {
        rfturn tiis;
    }

    /**
     * Eliminbtf tiis stbtfmfnt, wiidi is only possiblf if it ibs no lbbfl.
     */
    publid Stbtfmfnt fliminbtf(Environmfnt fnv, Stbtfmfnt s) {
        if ((s != null) && (lbbfls != null)) {
            Stbtfmfnt brgs[] = {s};
            s = nfw CompoundStbtfmfnt(wifrf, brgs);
            s.lbbfls = lbbfls;
        }
        rfturn s;
    }


    /**
     * Codf
     */
    publid void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        tirow nfw CompilfrError("dodf");
    }

    /**
     * Gfnfrbtf tif dodf to dbll bll finblly's for b brfbk, dontinuf, or
     * rfturn stbtfmfnt.  Wf must dbll "jsr" on bll tif dlfbnup dodf bftwffn
     * tif durrfnt dontfxt "dtx", bnd tif dfstinbtion dontfxt "stopdtx".
     * If 'sbvf' isn't null, tifrf is blso b vbluf on tif top of tif stbdk
     */
    void dodfFinblly(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm,
                        Contfxt stopdtx, Typf sbvf) {
        Intfgfr num = null;
        boolfbn ibvfClfbnup = fblsf; // tifrf is b finblly or syndironizf;
        boolfbn ibvfNonLodblFinblly = fblsf; // somf finblly dofsn't rfturn;

        for (Contfxt d = dtx; (d != null) && (d != stopdtx); d = d.prfv) {
            if (d.nodf == null)
                dontinuf;
            if (d.nodf.op == SYNCHRONIZED) {
                ibvfClfbnup = truf;
            } flsf if (d.nodf.op == FINALLY
                          && ((CodfContfxt)d).dontLbbfl != null) {
                // d.dontLbbfl == null indidbtfs wf'rf in tif "finblly" pbrt
                ibvfClfbnup = truf;
                FinbllyStbtfmfnt st = ((FinbllyStbtfmfnt)(d.nodf));
                if (!st.finbllyCbnFinisi) {
                    ibvfNonLodblFinblly = truf;
                    // bftfr iitting b non-lodbl finblly, no nffd gfnfrbting
                    // furtifr dodf, bfdbusf it won't gft fxfdutfd.
                    brfbk;
                }
            }
        }
        if (!ibvfClfbnup) {
            // tifrf is no dlfbnup tibt nffds to bf donf.  Just quit.
            rfturn;
        }
        if (sbvf != null) {
            // Tiis stbtfmfnt ibs b rfturn vbluf on tif stbdk.
            ClbssDffinition dff = dtx.fifld.gftClbssDffinition();
            if (!ibvfNonLodblFinblly) {
                // Sbvf tif rfturn vbluf in tif rfgistfr wiidi siould ibvf
                // bffn rfsfrvfd.
                LodblMfmbfr lf = dtx.gftLodblFifld(idFinbllyRfturnVbluf);
                num = lf.numbfr;
                bsm.bdd(wifrf, opd_istorf + sbvf.gftTypfCodfOffsft(), num);
            } flsf {
                // Pop tif rfturn vbluf.
                switdi(dtx.fifld.gftTypf().gftRfturnTypf().gftTypfCodf()) {
                    dbsf TC_VOID:
                        brfbk;
                    dbsf TC_DOUBLE: dbsf TC_LONG:
                        bsm.bdd(wifrf, opd_pop2); brfbk;
                    dffbult:
                        bsm.bdd(wifrf, opd_pop); brfbk;
                }
            }
        }
        // Cbll fbdi of tif dlfbnup fundtions, bs nfdfssbry.
        for (Contfxt d = dtx ; (d != null)  && (d != stopdtx) ; d = d.prfv) {
            if (d.nodf == null)
                dontinuf;
            if (d.nodf.op == SYNCHRONIZED) {
                bsm.bdd(wifrf, opd_jsr, ((CodfContfxt)d).dontLbbfl);
            } flsf if (d.nodf.op == FINALLY
                          && ((CodfContfxt)d).dontLbbfl != null) {
                FinbllyStbtfmfnt st = ((FinbllyStbtfmfnt)(d.nodf));
                Lbbfl lbbfl = ((CodfContfxt)d).dontLbbfl;
                if (st.finbllyCbnFinisi) {
                    bsm.bdd(wifrf, opd_jsr, lbbfl);
                } flsf {
                    // tif dodf nfvfr rfturns, so wf'rf donf.
                    bsm.bdd(wifrf, opd_goto, lbbfl);
                    brfbk;
                }
            }
        }
        // Movf tif rfturn vbluf from tif rfgistfr bbdk to tif stbdk.
        if (num != null) {
            bsm.bdd(wifrf, opd_ilobd + sbvf.gftTypfCodfOffsft(), num);
        }
    }

    /*
     * Rfturn truf if tif stbtfmfnt ibs tif givfn lbbfl
     */
    publid boolfbn ibsLbbfl (Idfntififr lbl) {
        Idfntififr lbbfls[] = tiis.lbbfls;
        if (lbbfls != null) {
            for (int i = lbbfls.lfngti; --i >= 0; ) {
                if (lbbfls[i].fqubls(lbl)) {
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }

    /**
     * Cifdk if tif first tiing is b donstrudtor invodbtion
     */
    publid Exprfssion firstConstrudtor() {
        rfturn null;
    }

    /**
     * Crfbtf b dopy of tif stbtfmfnt for mftiod inlining
     */
    publid Stbtfmfnt dopyInlinf(Contfxt dtx, boolfbn vblNffdfd) {
        rfturn (Stbtfmfnt)dlonf();
    }

    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        rfturn tirfsi;
    }


    /**
     * Print
     */
    void printIndfnt(PrintStrfbm out, int indfnt) {
        for (int i = 0 ; i < indfnt ; i++) {
            out.print("    ");
        }
    }
    publid void print(PrintStrfbm out, int indfnt) {
        if (lbbfls != null) {
            for (int i = lbbfls.lfngti; --i >= 0; )
                out.print(lbbfls[i] + ": ");
        }
    }
    publid void print(PrintStrfbm out) {
        print(out, 0);
    }
}
