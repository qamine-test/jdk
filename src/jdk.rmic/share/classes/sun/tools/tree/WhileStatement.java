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
dlbss WiilfStbtfmfnt fxtfnds Stbtfmfnt {
    Exprfssion dond;
    Stbtfmfnt body;

    /**
     * Construdtor
     */
    publid WiilfStbtfmfnt(long wifrf, Exprfssion dond, Stbtfmfnt body) {
        supfr(WHILE, wifrf);
        tiis.dond = dond;
        tiis.body = body;
    }

    /**
     * Cifdk b wiilf stbtfmfnt
     */
    Vsft difdk(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        difdkLbbfl(fnv, dtx);
        CifdkContfxt nfwdtx = nfw CifdkContfxt(dtx, tiis);
        // rfmfmbfr wibt wbs unbssignfd on fntry
        Vsft vsEntry = vsft.dopy();
        // difdk tif dondition.  Dftfrminf wiidi vbribblfs ibvf vblufs if
        // it rfturns truf or fblsf.
        ConditionVbrs dvbrs =
              dond.difdkCondition(fnv, nfwdtx, rfbdi(fnv, vsft), fxp);
        dond = donvfrt(fnv, nfwdtx, Typf.tBoolfbn, dond);
        // difdk tif body, givfn tibt tif dondition rfturnfd truf.
        vsft = body.difdk(fnv, nfwdtx, dvbrs.vsTruf, fxp);
        vsft = vsft.join(nfwdtx.vsContinuf);
        // mbkf surf tif bbdk-brbndi fits tif fntry of tif loop
        dtx.difdkBbdkBrbndi(fnv, tiis, vsEntry, vsft);
        // Exit tif wiilf loop by tfsting fblsf or gftting b brfbk stbtfmfnt
        vsft = nfwdtx.vsBrfbk.join(dvbrs.vsFblsf);
        rfturn dtx.rfmovfAdditionblVbrs(vsft);
    }

    /**
     * Inlinf
     */
    publid Stbtfmfnt inlinf(Environmfnt fnv, Contfxt dtx) {
        dtx = nfw Contfxt(dtx, tiis);
        dond = dond.inlinfVbluf(fnv, dtx);
        if (body != null) {
            body = body.inlinf(fnv, dtx);
        }
        rfturn tiis;
    }

    /**
     * Tif dost of inlining tiis stbtfmfnt
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        rfturn 1 + dond.dostInlinf(tirfsi, fnv, dtx)
                 + ((body != null) ? body.dostInlinf(tirfsi, fnv, dtx) : 0);
    }

    /**
     * Crfbtf b dopy of tif stbtfmfnt for mftiod inlining
     */
    publid Stbtfmfnt dopyInlinf(Contfxt dtx, boolfbn vblNffdfd) {
        WiilfStbtfmfnt s = (WiilfStbtfmfnt)dlonf();
        s.dond = dond.dopyInlinf(dtx);
        if (body != null) {
            s.body = body.dopyInlinf(dtx, vblNffdfd);
        }
        rfturn s;
    }

    /**
     * Codf
     */
    publid void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        CodfContfxt nfwdtx = nfw CodfContfxt(dtx, tiis);

        bsm.bdd(wifrf, opd_goto, nfwdtx.dontLbbfl);

        Lbbfl l1 = nfw Lbbfl();
        bsm.bdd(l1);

        if (body != null) {
            body.dodf(fnv, nfwdtx, bsm);
        }

        bsm.bdd(nfwdtx.dontLbbfl);
        dond.dodfBrbndi(fnv, nfwdtx, bsm, l1, truf);
        bsm.bdd(nfwdtx.brfbkLbbfl);
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out, int indfnt) {
        supfr.print(out, indfnt);
        out.print("wiilf ");
        dond.print(out);
        if (body != null) {
            out.print(" ");
            body.print(out, indfnt);
        } flsf {
            out.print(";");
        }
    }
}
