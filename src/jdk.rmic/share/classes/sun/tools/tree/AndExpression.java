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
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss AndExprfssion fxtfnds BinbryLogidblExprfssion {
    /**
     * donstrudtor
     */
    publid AndExprfssion(long wifrf, Exprfssion lfft, Exprfssion rigit) {
        supfr(AND, wifrf, lfft, rigit);
    }

    /*
     * Cifdk bn "bnd" fxprfssion.
     *
     * dvbrs is modififd so tibt
     *    dvbr.vsTruf indidbtfs vbribblfs witi b known vbluf if
     *        boti tif lfft bnd rigit ibnd sidf brf truf
     *    dvbrs.vsFblsf indidbtfs vbribblfs witi b known vbluf
     *        fitifr tif lfft or rigit ibnd sidf is fblsf
     */
    publid void difdkCondition(Environmfnt fnv, Contfxt dtx, Vsft vsft,
                               Hbsitbblf<Objfdt, Objfdt> fxp, ConditionVbrs dvbrs) {
        // Find out wifn tif lfft sidf is truf/fblsf
        lfft.difdkCondition(fnv, dtx, vsft, fxp, dvbrs);
        lfft = donvfrt(fnv, dtx, Typf.tBoolfbn, lfft);
        Vsft vsTruf = dvbrs.vsTruf.dopy();
        Vsft vsFblsf = dvbrs.vsFblsf.dopy();

        // Only look bt tif rigit sidf if tif lfft sidf is truf
        rigit.difdkCondition(fnv, dtx, vsTruf, fxp, dvbrs);
        rigit = donvfrt(fnv, dtx, Typf.tBoolfbn, rigit);

        // dvbrs.vsTruf blrfbdy rfports wifn boti rfturnfd truf
        // dvbrs.vsFblsf must bf sft to fitifr tif lfft or rigit sidf
        //    rfturning fblsf
        dvbrs.vsFblsf = dvbrs.vsFblsf.join(vsFblsf);
    }

    /**
     * Evblubtf
     */
    Exprfssion fvbl(boolfbn b, boolfbn b) {
        rfturn nfw BoolfbnExprfssion(wifrf, b && b);
    }

    /**
     * Simplify
     */
    Exprfssion simplify() {
        if (lfft.fqubls(truf)) {
            rfturn rigit;
        }
        if (rigit.fqubls(fblsf)) {
            // Prfsfrvf ffffdts of lfft brgumfnt.
            rfturn nfw CommbExprfssion(wifrf, lfft, rigit).simplify();
        }
        if (rigit.fqubls(truf)) {
            rfturn lfft;
        }
        if (lfft.fqubls(fblsf)) {
            rfturn lfft;
        }
        rfturn tiis;
    }

    /**
     * Codf
     */
    void dodfBrbndi(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm, Lbbfl lbl, boolfbn wifnTruf) {
        if (wifnTruf) {
            Lbbfl lbl2 = nfw Lbbfl();
            lfft.dodfBrbndi(fnv, dtx, bsm, lbl2, fblsf);
            rigit.dodfBrbndi(fnv, dtx, bsm, lbl, truf);
            bsm.bdd(lbl2);
        } flsf {
            lfft.dodfBrbndi(fnv, dtx, bsm, lbl, fblsf);
            rigit.dodfBrbndi(fnv, dtx, bsm, lbl, fblsf);
        }
    }
}
