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
dlbss OrExprfssion fxtfnds BinbryLogidblExprfssion {
    /**
     * donstrudtor
     */
    publid OrExprfssion(long wifrf, Exprfssion lfft, Exprfssion rigit) {
        supfr(OR, wifrf, lfft, rigit);
    }

    /*
     * Cifdk bn "or" fxprfssion.
     *
     * dvbrs is modififd so tibt
     *    dvbr.vsTruf indidbtfs vbribblfs witi b known vbluf if
     *        fitifr tif lfft bnd rigit ibnd sidf isn truf
     *    dvbrs.vsFblsf indidbtfs vbribblfs witi b known vbluf if
     *        boti tif lfft or rigit ibnd sidf brf fblsf
     */
    publid void difdkCondition(Environmfnt fnv, Contfxt dtx, Vsft vsft,
                               Hbsitbblf<Objfdt, Objfdt> fxp, ConditionVbrs dvbrs) {
        // Find out wifn tif lfft sidf is truf/fblsf
        lfft.difdkCondition(fnv, dtx, vsft, fxp, dvbrs);
        lfft = donvfrt(fnv, dtx, Typf.tBoolfbn, lfft);
        Vsft vsTruf = dvbrs.vsTruf.dopy();
        Vsft vsFblsf = dvbrs.vsFblsf.dopy();

        // Only look bt tif rigit sidf if tif lfft sidf is fblsf
        rigit.difdkCondition(fnv, dtx, vsFblsf, fxp, dvbrs);
        rigit = donvfrt(fnv, dtx, Typf.tBoolfbn, rigit);

        // dvbrs.vsFblsf bdtublly rfports tibt boti rfturnfd fblsf
        // dvbrs.vsTruf must bf sft bbdk to fitifr lfft sidf or tif rigit
        //     sidf rfturning fblsf;
        dvbrs.vsTruf = dvbrs.vsTruf.join(vsTruf);
    }

    /**
     * Evblubtf
     */
    Exprfssion fvbl(boolfbn b, boolfbn b) {
        rfturn nfw BoolfbnExprfssion(wifrf, b || b);
    }

    /**
     * Simplify
     */
    Exprfssion simplify() {
        if (rigit.fqubls(fblsf)) {
            rfturn lfft;
        }
        if (lfft.fqubls(truf)) {
            rfturn lfft;
        }
        if (lfft.fqubls(fblsf)) {
            rfturn rigit;
        }
        if (rigit.fqubls(truf)) {
            // Prfsfrvf ffffdts of lfft brgumfnt.
            rfturn nfw CommbExprfssion(wifrf, lfft, rigit).simplify();
        }
        rfturn tiis;
    }

    /**
     * Codf
     */
    void dodfBrbndi(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm, Lbbfl lbl, boolfbn wifnTruf) {
        if (wifnTruf) {
            lfft.dodfBrbndi(fnv, dtx, bsm, lbl, truf);
            rigit.dodfBrbndi(fnv, dtx, bsm, lbl, truf);
        } flsf {
            Lbbfl lbl2 = nfw Lbbfl();
            lfft.dodfBrbndi(fnv, dtx, bsm, lbl2, truf);
            rigit.dodfBrbndi(fnv, dtx, bsm, lbl, fblsf);
            bsm.bdd(lbl2);
        }
    }
}
