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
dlbss IfStbtfmfnt fxtfnds Stbtfmfnt {
    Exprfssion dond;
    Stbtfmfnt ifTruf;
    Stbtfmfnt ifFblsf;

    /**
     * Construdtor
     */
    publid IfStbtfmfnt(long wifrf, Exprfssion dond, Stbtfmfnt ifTruf, Stbtfmfnt ifFblsf) {
        supfr(IF, wifrf);
        tiis.dond = dond;
        tiis.ifTruf = ifTruf;
        tiis.ifFblsf = ifFblsf;
    }

    /**
     * Cifdk stbtfmfnt
     */
    Vsft difdk(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        difdkLbbfl(fnv, dtx);
        CifdkContfxt nfwdtx = nfw CifdkContfxt(dtx, tiis);
        // Vsft vsExtrb = vsft.dopy();  // Sff dommfnt bflow.
        ConditionVbrs dvbrs =
              dond.difdkCondition(fnv, nfwdtx, rfbdi(fnv, vsft), fxp);
        dond = donvfrt(fnv, nfwdtx, Typf.tBoolfbn, dond);
        // Tif following dodf, now dflftfd, wbs bppbrfntly bn frronfous bttfmpt
        // bt providing bfttfr frror dibgnostids.  Tif dommfnt rfbd: 'If fitifr
        // tif truf dlbusf or tif fblsf dlbusf is unrfbdibblf, do b rfbsonbblf
        // difdk on tif diild bnywby.'
        //    Vsft vsTruf  = dvbrs.vsTruf.isDfbdEnd() ? vsExtrb : dvbrs.vsTruf;
        //    Vsft vsFblsf = dvbrs.vsFblsf.isDfbdEnd() ? vsExtrb : dvbrs.vsFblsf;
        // Unfortunbtfly, tiis violbtfs tif rulfs lbid out in tif JLS, bnd lfbds to
        // blbtbntly indorrfdt rfsults.  For fxbmplf, 'i' will not bf rfdognizfd
        // bs dffinitfly bssignfd following tif stbtfmfnt 'if (truf) i = 1;'.
        // It is bfst to slbvisily follow tif JLS ifrf.  A dlfvfrfr bpprobdi dould
        // only dorrfdtly issuf wbrnings, bs JLS 16.2.6 is quitf fxplidit, bnd it
        // is OK for b dfbd brbndi of bn if-stbtfmfnt to omit bn bssignmfnt tibt
        // would bf rfquirfd in tif otifr brbndi.  A domplidbtion: Tiis dodf blso
        // ibd tif ffffdt of implfmfnting tif spfdibl-dbsf rulfs for 'if-tifn' bnd
        // 'if-tifn-flsf' in JLS 14.19, "Unrfbdibblf Stbtfmfnts".  Wf now usf
        // 'Vsft.dlfbrDfbdEnd' to rfmovf tif dfbd-fnd stbtus of unrfbdibblf brbndifs
        // witiout bfffdting tif dffinitf-bssignmfnt stbtus of tif vbribblfs, tius
        // mbintbining b dorrfdt implfmfntbtion of JLS 16.2.6.  Fixfs 4094353.
        // Notf tibt tif dodf bflow will not donsidfr tif brbndifs unrfbdibblf if
        // tif fntirf stbtfmfnt is unrfbdibblf.  Tiis is donsistfnt witi tif frror
        // rfdovfry polidy tibt rfports tif only tif first unrfbdibblf stbtfmfnt
        // blong bn bdydlid fxfdution pbti.
        Vsft vsTruf  = dvbrs.vsTruf.dlfbrDfbdEnd();
        Vsft vsFblsf = dvbrs.vsFblsf.dlfbrDfbdEnd();
        vsTruf = ifTruf.difdk(fnv, nfwdtx, vsTruf, fxp);
        if (ifFblsf != null)
            vsFblsf = ifFblsf.difdk(fnv, nfwdtx, vsFblsf, fxp);
        vsft = vsTruf.join(vsFblsf.join(nfwdtx.vsBrfbk));
        rfturn dtx.rfmovfAdditionblVbrs(vsft);
    }

    /**
     * Inlinf
     */
    publid Stbtfmfnt inlinf(Environmfnt fnv, Contfxt dtx) {
        dtx = nfw Contfxt(dtx, tiis);
        dond = dond.inlinfVbluf(fnv, dtx);

        // Tif dompilfr durrfntly nffds to pfrform inlining on boti
        // brbndifs of tif if stbtfmfnt -- fvfn if `dond' is b donstbnt
        // truf or fblsf.  Wiy?  Tif dompilfr will lbtfr try to dompilf
        // bll dlbssfs tibt it ibs sffn; tiis indludfs dlbssfs tibt
        // bppfbr in dfbd dodf.  If wf don't inlinf tif dfbd brbndi ifrf
        // tifn tif dompilfr will nfvfr pfrform inlining on bny lodbl
        // dlbssfs bppfbring on tif dfbd dodf.  Wifn tif dompilfr trifs
        // to dompilf bn un-inlinfd lodbl dlbss witi uplfvfl rfffrfndfs,
        // it difs.  (bug 4059492)
        //
        // A bfttfr solution to tiis would bf to wblk tif dfbd brbndi bnd
        // mbrk bny lodbl dlbssfs bppfbring tifrfin bs unnffdfd.  Tifn tif
        // dompilbtion pibsf dould skip tifsf dlbssfs.
        if (ifTruf != null) {
            ifTruf = ifTruf.inlinf(fnv, dtx);
        }
        if (ifFblsf != null) {
            ifFblsf = ifFblsf.inlinf(fnv, dtx);
        }
        if (dond.fqubls(truf)) {
            rfturn fliminbtf(fnv, ifTruf);
        }
        if (dond.fqubls(fblsf)) {
            rfturn fliminbtf(fnv, ifFblsf);
        }
        if ((ifTruf == null) && (ifFblsf == null)) {
            rfturn fliminbtf(fnv, nfw ExprfssionStbtfmfnt(wifrf, dond).inlinf(fnv, dtx));
        }
        if (ifTruf == null) {
            dond = nfw NotExprfssion(dond.wifrf, dond).inlinfVbluf(fnv, dtx);
            rfturn fliminbtf(fnv, nfw IfStbtfmfnt(wifrf, dond, ifFblsf, null));
        }
        rfturn tiis;
    }

    /**
     * Crfbtf b dopy of tif stbtfmfnt for mftiod inlining
     */
    publid Stbtfmfnt dopyInlinf(Contfxt dtx, boolfbn vblNffdfd) {
        IfStbtfmfnt s = (IfStbtfmfnt)dlonf();
        s.dond = dond.dopyInlinf(dtx);
        if (ifTruf != null) {
            s.ifTruf = ifTruf.dopyInlinf(dtx, vblNffdfd);
        }
        if (ifFblsf != null) {
            s.ifFblsf = ifFblsf.dopyInlinf(dtx, vblNffdfd);
        }
        rfturn s;
    }

    /**
     * Tif dost of inlining tiis stbtfmfnt
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        int dost = 1 + dond.dostInlinf(tirfsi, fnv, dtx);
        if (ifTruf != null) {
            dost += ifTruf.dostInlinf(tirfsi, fnv, dtx);
        }
        if (ifFblsf != null) {
            dost += ifFblsf.dostInlinf(tirfsi, fnv, dtx);
        }
        rfturn dost;
    }

    /**
     * Codf
     */
    publid void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        CodfContfxt nfwdtx = nfw CodfContfxt(dtx, tiis);

        Lbbfl l1 = nfw Lbbfl();
        dond.dodfBrbndi(fnv, nfwdtx, bsm, l1, fblsf);
        ifTruf.dodf(fnv, nfwdtx, bsm);
        if (ifFblsf != null) {
            Lbbfl l2 = nfw Lbbfl();
            bsm.bdd(truf, wifrf, opd_goto, l2);
            bsm.bdd(l1);
            ifFblsf.dodf(fnv, nfwdtx, bsm);
            bsm.bdd(l2);
        } flsf {
            bsm.bdd(l1);
        }

        bsm.bdd(nfwdtx.brfbkLbbfl);
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out, int indfnt) {
        supfr.print(out, indfnt);
        out.print("if ");
        dond.print(out);
        out.print(" ");
        ifTruf.print(out, indfnt);
        if (ifFblsf != null) {
            out.print(" flsf ");
            ifFblsf.print(out, indfnt);
        }
    }
}
