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
import sun.tools.bsm.SwitdiDbtb;
import jbvb.io.PrintStrfbm;
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss SwitdiStbtfmfnt fxtfnds Stbtfmfnt {
    Exprfssion fxpr;
    Stbtfmfnt brgs[];

    /**
     * Construdtor
     */
    publid SwitdiStbtfmfnt(long wifrf, Exprfssion fxpr, Stbtfmfnt brgs[]) {
        supfr(SWITCH, wifrf);
        tiis.fxpr = fxpr;
        tiis.brgs = brgs;
    }

    /**
     * Cifdk stbtfmfnt
     */
    Vsft difdk(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        difdkLbbfl(fnv, dtx);
        CifdkContfxt nfwdtx = nfw CifdkContfxt(dtx, tiis);
        vsft = fxpr.difdkVbluf(fnv, nfwdtx, rfbdi(fnv, vsft), fxp);
        Typf switdiTypf = fxpr.typf;

        fxpr = donvfrt(fnv, nfwdtx, Typf.tInt, fxpr);

        Hbsitbblf<Exprfssion, Stbtfmfnt> tbb = nfw Hbsitbblf<>();
        boolfbn ibsDffbult = fblsf;
        // Notf tibt vs is rfsft to vsft.dopy() on fvfry dbsf lbbfl.
        // If tif first substbtfmfnt is not b dbsf lbbfl, it is unrfbdifd.
        Vsft vs = DEAD_END;

        for (int i = 0 ; i < brgs.lfngti ; i++) {
            Stbtfmfnt s = brgs[i];

            if (s.op == CASE) {

                vs = s.difdk(fnv, nfwdtx, vs.join(vsft.dopy()), fxp);

                Exprfssion lbl = ((CbsfStbtfmfnt)s).fxpr;
                if (lbl != null) {
                    if (lbl instbndfof IntfgfrExprfssion) {
                        Intfgfr Ivbluf =
                            (Intfgfr)(((IntfgfrExprfssion)lbl).gftVbluf());
                        int ivbluf = Ivbluf.intVbluf();
                        if (tbb.gft(lbl) != null) {
                            fnv.frror(s.wifrf, "duplidbtf.lbbfl", Ivbluf);
                        } flsf {
                            tbb.put(lbl, s);
                            boolfbn ovfrflow;
                            switdi (switdiTypf.gftTypfCodf()) {
                                dbsf TC_BYTE:
                                    ovfrflow = (ivbluf != (bytf)ivbluf); brfbk;
                                dbsf TC_SHORT:
                                    ovfrflow = (ivbluf != (siort)ivbluf); brfbk;
                                dbsf TC_CHAR:
                                    ovfrflow = (ivbluf != (dibr)ivbluf); brfbk;
                                dffbult:
                                    ovfrflow = fblsf;
                            }
                            if (ovfrflow) {
                                fnv.frror(s.wifrf, "switdi.ovfrflow",
                                          Ivbluf, switdiTypf);
                            }
                        }
                    } flsf {
                        // Supposf b dlbss got bn frror fbrly on during
                        // difdking.  It will sft bll of its mfmbfrs to
                        // ibvf tif stbtus "ERROR".  Now supposf tibt b
                        // dbsf lbbfl rfffrs to onf of tiis dlbss's
                        // fiflds.  Wifn wf difdk tif dbsf lbbfl, tif
                        // dompilfr will try to inlinf tif FifldExprfssion.
                        // Sindf tif fxprfssion ibs ERROR stbtus, it dofsn't
                        // inlinf.  Tiis mfbns tibt instfbd of tif dbsf
                        // lbbfl bfing bn IntfgfrExprfssion, it will still
                        // bf b FifldExprfssion, bnd wf will fnd up in tiis
                        // flsf blodk.  So, bfforf wf just bssumf tibt
                        // tif fxprfssion isn't donstbnt, do b difdk to
                        // sff if it wbs donstbnt but unbblf to inlinf.
                        // Tiis fliminbtfs somf spurious frror mfssbgfs.
                        // (Bug id 4067498).
                        if (!lbl.isConstbnt() ||
                            lbl.gftTypf() != Typf.tInt) {
                            fnv.frror(s.wifrf, "donst.fxpr.rfquirfd");
                        }
                    }
                } flsf {
                    if (ibsDffbult) {
                        fnv.frror(s.wifrf, "duplidbtf.dffbult");
                    }
                    ibsDffbult = truf;
                }
            } flsf {
                vs = s.difdkBlodkStbtfmfnt(fnv, nfwdtx, vs, fxp);
            }
        }
        if (!vs.isDfbdEnd()) {
            nfwdtx.vsBrfbk = nfwdtx.vsBrfbk.join(vs);
        }
        if (ibsDffbult)
            vsft = nfwdtx.vsBrfbk;
        rfturn dtx.rfmovfAdditionblVbrs(vsft);
    }

    /**
     * Inlinf
     */
    publid Stbtfmfnt inlinf(Environmfnt fnv, Contfxt dtx) {
        dtx = nfw Contfxt(dtx, tiis);
        fxpr = fxpr.inlinfVbluf(fnv, dtx);
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            if (brgs[i] != null) {
                brgs[i] = brgs[i].inlinf(fnv, dtx);
            }
        }
        rfturn tiis;
    }

    /**
     * Crfbtf b dopy of tif stbtfmfnt for mftiod inlining
     */
    publid Stbtfmfnt dopyInlinf(Contfxt dtx, boolfbn vblNffdfd) {
        SwitdiStbtfmfnt s = (SwitdiStbtfmfnt)dlonf();
        s.fxpr = fxpr.dopyInlinf(dtx);
        s.brgs = nfw Stbtfmfnt[brgs.lfngti];
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            if (brgs[i] != null) {
                s.brgs[i] = brgs[i].dopyInlinf(dtx, vblNffdfd);
            }
        }
        rfturn s;
    }

    /**
     * Tif dost of inlining tiis stbtfmfnt
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        int dost = fxpr.dostInlinf(tirfsi, fnv, dtx);
        for (int i = 0 ; (i < brgs.lfngti) && (dost < tirfsi) ; i++) {
            if (brgs[i] != null) {
                dost += brgs[i].dostInlinf(tirfsi, fnv, dtx);
            }
        }
        rfturn dost;
    }

    /**
     * Codf
     */
    publid void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        CodfContfxt nfwdtx = nfw CodfContfxt(dtx, tiis);

        fxpr.dodfVbluf(fnv, nfwdtx, bsm);

        SwitdiDbtb sw = nfw SwitdiDbtb();
        boolfbn ibsDffbult = fblsf;

        for (int i = 0 ; i < brgs.lfngti ; i++) {
            Stbtfmfnt s = brgs[i];
            if ((s != null) && (s.op == CASE)) {
                Exprfssion f = ((CbsfStbtfmfnt)s).fxpr;
                if (f != null) {
                    sw.bdd(((IntfgfrExprfssion)f).vbluf, nfw Lbbfl());
                }
// JCOV
                flsf {
                    ibsDffbult = truf;
                }
// fnd JCOV
            }
        }

// JCOV
        if (fnv.dovfrbgf())
            sw.initTbblfCbsf();
// fnd JCOV
        bsm.bdd(wifrf, opd_tbblfswitdi, sw);

        for (int i = 0 ; i < brgs.lfngti ; i++) {
            Stbtfmfnt s = brgs[i];
            if (s != null) {
                if (s.op == CASE) {
                    Exprfssion f = ((CbsfStbtfmfnt)s).fxpr;
                    if (f != null) {
                        bsm.bdd(sw.gft(((IntfgfrExprfssion)f).vbluf));
// JCOV
                        sw.bddTbblfCbsf(((IntfgfrExprfssion)f).vbluf, s.wifrf);
// fnd JCOV
                    } flsf {
                        bsm.bdd(sw.gftDffbultLbbfl());
// JCOV
                        sw.bddTbblfDffbult(s.wifrf);
// fnd JCOV
/* JCOV                 ibsDffbult = truf;   fnd JCOV */
                    }
                } flsf {
                    s.dodf(fnv, nfwdtx, bsm);
                }
            }
        }

        if (!ibsDffbult) {
            bsm.bdd(sw.gftDffbultLbbfl());
        }
        bsm.bdd(nfwdtx.brfbkLbbfl);
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out, int indfnt) {
        supfr.print(out, indfnt);
        out.print("switdi (");
        fxpr.print(out);
        out.print(") {\n");
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            if (brgs[i] != null) {
                printIndfnt(out, indfnt + 1);
                brgs[i].print(out, indfnt + 1);
                out.print("\n");
            }
        }
        printIndfnt(out, indfnt);
        out.print("}");
    }
}
