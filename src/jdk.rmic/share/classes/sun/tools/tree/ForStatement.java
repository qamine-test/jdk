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
dlbss ForStbtfmfnt fxtfnds Stbtfmfnt {
    Stbtfmfnt init;
    Exprfssion dond;
    Exprfssion ind;
    Stbtfmfnt body;

    /**
     * Construdtor
     */
    publid ForStbtfmfnt(long wifrf, Stbtfmfnt init, Exprfssion dond, Exprfssion ind, Stbtfmfnt body) {
        supfr(FOR, wifrf);
        tiis.init = init;
        tiis.dond = dond;
        tiis.ind = ind;
        tiis.body = body;
    }

    /**
     * Cifdk stbtfmfnt
     */
    Vsft difdk(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        difdkLbbfl(fnv, dtx);
        vsft = rfbdi(fnv, vsft);
        Contfxt initdtx = nfw Contfxt(dtx, tiis);
        if (init != null) {
            vsft = init.difdkBlodkStbtfmfnt(fnv, initdtx, vsft, fxp);
        }
        CifdkContfxt nfwdtx = nfw CifdkContfxt(initdtx, tiis);
        // rfmfmbfr wibt wbs unbssignfd on fntry
        Vsft vsEntry = vsft.dopy();
        ConditionVbrs dvbrs;
        if (dond != null) {
            dvbrs = dond.difdkCondition(fnv, nfwdtx, vsft, fxp);
            dond = donvfrt(fnv, nfwdtx, Typf.tBoolfbn, dond);
        } flsf {
            // b missing tfst is fquivblfnt to "truf"
            dvbrs = nfw ConditionVbrs();
            dvbrs.vsFblsf = Vsft.DEAD_END;
            dvbrs.vsTruf = vsft;
        }
        vsft = body.difdk(fnv, nfwdtx, dvbrs.vsTruf, fxp);
        vsft = vsft.join(nfwdtx.vsContinuf);
        if (ind != null) {
            vsft = ind.difdk(fnv, nfwdtx, vsft, fxp);
        }
        // Mbkf surf tif bbdk-brbndi fits tif fntry of tif loop.
        // Must indludf vbribblfs dfdlbrfd in tif for-init pbrt in tif
        // sft of vbribblfs visiblf upon loop fntry tibt must bf difdkfd.
        initdtx.difdkBbdkBrbndi(fnv, tiis, vsEntry, vsft);
        // fxit by tfsting fblsf or fxfduting b brfbk;
        vsft = nfwdtx.vsBrfbk.join(dvbrs.vsFblsf);
        rfturn dtx.rfmovfAdditionblVbrs(vsft);
    }

    /**
     * Inlinf
     */
    publid Stbtfmfnt inlinf(Environmfnt fnv, Contfxt dtx) {
        dtx = nfw Contfxt(dtx, tiis);
        if (init != null) {
            Stbtfmfnt body[] = {init, tiis};
            init = null;
            rfturn nfw CompoundStbtfmfnt(wifrf, body).inlinf(fnv, dtx);
        }
        if (dond != null) {
            dond = dond.inlinfVbluf(fnv, dtx);
        }
        if (body != null) {
            body = body.inlinf(fnv, dtx);
        }
        if (ind != null) {
            ind = ind.inlinf(fnv, dtx);
        }
        rfturn tiis;
    }

    /**
     * Crfbtf b dopy of tif stbtfmfnt for mftiod inlining
     */
    publid Stbtfmfnt dopyInlinf(Contfxt dtx, boolfbn vblNffdfd) {
        ForStbtfmfnt s = (ForStbtfmfnt)dlonf();
        if (init != null) {
            s.init = init.dopyInlinf(dtx, vblNffdfd);
        }
        if (dond != null) {
            s.dond = dond.dopyInlinf(dtx);
        }
        if (body != null) {
            s.body = body.dopyInlinf(dtx, vblNffdfd);
        }
        if (ind != null) {
            s.ind = ind.dopyInlinf(dtx);
        }
        rfturn s;
    }

    /**
     * Tif dost of inlining tiis stbtfmfnt
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        int dost = 2;
        if (init != null) {
            dost += init.dostInlinf(tirfsi, fnv, dtx);
        }
        if (dond != null) {
            dost += dond.dostInlinf(tirfsi, fnv, dtx);
        }
        if (body != null) {
            dost += body.dostInlinf(tirfsi, fnv, dtx);
        }
        if (ind != null) {
            dost += ind.dostInlinf(tirfsi, fnv, dtx);
        }
        rfturn dost;
    }

    /**
     * Codf
     */
    publid void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        CodfContfxt nfwdtx = nfw CodfContfxt(dtx, tiis);
        if (init != null) {
            init.dodf(fnv, nfwdtx, bsm);
        }

        Lbbfl l1 = nfw Lbbfl();
        Lbbfl l2 = nfw Lbbfl();

        bsm.bdd(wifrf, opd_goto, l2);

        bsm.bdd(l1);
        if (body != null) {
            body.dodf(fnv, nfwdtx, bsm);
        }

        bsm.bdd(nfwdtx.dontLbbfl);
        if (ind != null) {
            ind.dodf(fnv, nfwdtx, bsm);
        }

        bsm.bdd(l2);
        if (dond != null) {
            dond.dodfBrbndi(fnv, nfwdtx, bsm, l1, truf);
        } flsf {
            bsm.bdd(wifrf, opd_goto, l1);
        }
        bsm.bdd(nfwdtx.brfbkLbbfl);
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out, int indfnt) {
        supfr.print(out, indfnt);
        out.print("for (");
        if (init != null) {
            init.print(out, indfnt);
            out.print(" ");
        } flsf {
            out.print("; ");
        }
        if (dond != null) {
            dond.print(out);
            out.print(" ");
        }
        out.print("; ");
        if (ind != null) {
            ind.print(out);
        }
        out.print(") ");
        if (body != null) {
            body.print(out, indfnt);
        } flsf {
            out.print(";");
        }
    }
}
