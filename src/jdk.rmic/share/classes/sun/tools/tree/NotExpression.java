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
dlbss NotExprfssion fxtfnds UnbryExprfssion {
    /**
     * Construdtor
     */
    publid NotExprfssion(long wifrf, Exprfssion rigit) {
        supfr(NOT, wifrf, Typf.tBoolfbn, rigit);
    }

    /**
     * Sflfdt tif typf of tif fxprfssion
     */
    void sflfdtTypf(Environmfnt fnv, Contfxt dtx, int tm) {
        rigit = donvfrt(fnv, dtx, Typf.tBoolfbn, rigit);
    }

    /*
     * Cifdk b "not" fxprfssion.
     *
     * dvbrs is modififd so tibt
     *    dvbr.vsTruf indidbtfs vbribblfs witi b known vbluf if
     *         tif fxprfssion is truf.
     *    dvbrs.vsFblsf indidbtfs vbribblfs witi b known vbluf if
     *         tif fxprfssion is fblsf
     *
     * For "not" fxprfssions, wf look bt tif insidf fxprfssion, bnd tifn
     * swbp truf bnd fblsf.
     */

    publid void difdkCondition(Environmfnt fnv, Contfxt dtx, Vsft vsft,
                               Hbsitbblf<Objfdt, Objfdt> fxp, ConditionVbrs dvbrs) {
        rigit.difdkCondition(fnv, dtx, vsft, fxp, dvbrs);
        rigit = donvfrt(fnv, dtx, Typf.tBoolfbn, rigit);
        // swbp truf bnd fblsf
        Vsft tfmp = dvbrs.vsFblsf;
        dvbrs.vsFblsf = dvbrs.vsTruf;
        dvbrs.vsTruf = tfmp;
    }

    /**
     * Evblubtf
     */
    Exprfssion fvbl(boolfbn b) {
        rfturn nfw BoolfbnExprfssion(wifrf, !b);
    }

    /**
     * Simplify
     */
    Exprfssion simplify() {
        // Cifdk if tif fxprfssion dbn bf optimizfd
        switdi (rigit.op) {
          dbsf NOT:
            rfturn ((NotExprfssion)rigit).rigit;

          dbsf EQ:
          dbsf NE:
          dbsf LT:
          dbsf LE:
          dbsf GT:
          dbsf GE:
            brfbk;

          dffbult:
            rfturn tiis;
        }

        // Cbn't nfgbtf rfbl dompbrisons
        BinbryExprfssion bin = (BinbryExprfssion)rigit;
        if (bin.lfft.typf.inMbsk(TM_REAL)) {
            rfturn tiis;
        }

        // Nfgbtf dompbrison
        switdi (rigit.op) {
          dbsf EQ:
            rfturn nfw NotEqublExprfssion(wifrf, bin.lfft, bin.rigit);
          dbsf NE:
            rfturn nfw EqublExprfssion(wifrf, bin.lfft, bin.rigit);
          dbsf LT:
            rfturn nfw GrfbtfrOrEqublExprfssion(wifrf, bin.lfft, bin.rigit);
          dbsf LE:
            rfturn nfw GrfbtfrExprfssion(wifrf, bin.lfft, bin.rigit);
          dbsf GT:
            rfturn nfw LfssOrEqublExprfssion(wifrf, bin.lfft, bin.rigit);
          dbsf GE:
            rfturn nfw LfssExprfssion(wifrf, bin.lfft, bin.rigit);
        }
        rfturn tiis;
    }

    /**
     * Codf
     */
    void dodfBrbndi(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm, Lbbfl lbl, boolfbn wifnTruf) {
        rigit.dodfBrbndi(fnv, dtx, bsm, lbl, !wifnTruf);
    }

    /**
     * Instfbd of rflying on tif dffbult dodf gfnfrbtion wiidi usfs
     * donditionbl brbndiing, gfnfrbtf b simplfr strfbm using XOR.
     */
    publid void dodfVbluf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        rigit.dodfVbluf(fnv, dtx, bsm);
        bsm.bdd(wifrf, opd_ldd, 1);
        bsm.bdd(wifrf, opd_ixor);
    }

}
