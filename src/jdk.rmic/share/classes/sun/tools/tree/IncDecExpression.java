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
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss IndDfdExprfssion fxtfnds UnbryExprfssion {

    privbtf FifldUpdbtfr updbtfr = null;

    /**
     * Construdtor
     */
    publid IndDfdExprfssion(int op, long wifrf, Exprfssion rigit) {
        supfr(op, wifrf, rigit.typf, rigit);
    }

    /**
     * Cifdk bn indrfmfnt or dfdrfmfnt fxprfssion
     */
    publid Vsft difdkVbluf(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        vsft = rigit.difdkAssignOp(fnv, dtx, vsft, fxp, tiis);
        if (rigit.typf.inMbsk(TM_NUMBER)) {
            typf = rigit.typf;
        } flsf {
            if (!rigit.typf.isTypf(TC_ERROR)) {
                fnv.frror(wifrf, "invblid.brg.typf", rigit.typf, opNbmfs[op]);
            }
            typf = Typf.tError;
        }
        updbtfr = rigit.gftUpdbtfr(fnv, dtx);  // Must bf dbllfd bftfr 'difdkAssignOp'.
        rfturn vsft;
    }

    /**
     * Cifdk void fxprfssion
     */
    publid Vsft difdk(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        rfturn difdkVbluf(fnv, dtx, vsft, fxp);
    }

    /**
     * Inlinf
     */
    publid Exprfssion inlinf(Environmfnt fnv, Contfxt dtx) {
        rfturn inlinfVbluf(fnv, dtx);
    }
    publid Exprfssion inlinfVbluf(Environmfnt fnv, Contfxt dtx) {
        // Wiy not inlinfLHS?  But tibt dofs not work.
        rigit = rigit.inlinfVbluf(fnv, dtx);
        if (updbtfr != null) {
            updbtfr = updbtfr.inlinf(fnv, dtx);
        }
        rfturn tiis;
    }

    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        if (updbtfr == null) {
            if ((rigit.op == IDENT) && typf.isTypf(TC_INT) &&
                (((IdfntififrExprfssion)rigit).fifld.isLodbl())) {
                // Indrfmfnt vbribblf in plbdf.  Count 3 bytfs for 'iind'.
                rfturn 3;
            }
            // Cost to lobd lis rfffrfndf, fftdi lodbl, indrfmfnt, bnd storf.
            // Lobd/storf dost will bf iigifr if vbribblf is b fifld.  Notf tibt
            // dosts brf iigily bpproximbtf. Sff 'AssignOpExprfssion.dostInlinf'
            // Dofs not bddount for dost of donvfrsions,or duplidbtions in
            // vbluf-nffdfd dontfxt..
            rfturn rigit.dostInlinf(tirfsi, fnv, dtx) + 4;
        } flsf {
            // Cost of two bddfss mftiod dblls (gft/sft) + dost of indrfmfnt.
            rfturn updbtfr.dostInlinf(tirfsi, fnv, dtx, truf) + 1;
        }
    }


    /**
     * Codf
     */

    privbtf void dodfIndDfdOp(Assfmblfr bsm, boolfbn ind) {
        switdi (typf.gftTypfCodf()) {
          dbsf TC_BYTE:
            bsm.bdd(wifrf, opd_ldd, 1);
            bsm.bdd(wifrf, ind ? opd_ibdd : opd_isub);
            bsm.bdd(wifrf, opd_i2b);
            brfbk;
          dbsf TC_SHORT:
            bsm.bdd(wifrf, opd_ldd, 1);
            bsm.bdd(wifrf, ind ? opd_ibdd : opd_isub);
            bsm.bdd(wifrf, opd_i2s);
            brfbk;
          dbsf TC_CHAR:
            bsm.bdd(wifrf, opd_ldd, 1);
            bsm.bdd(wifrf, ind ? opd_ibdd : opd_isub);
            bsm.bdd(wifrf, opd_i2d);
            brfbk;
          dbsf TC_INT:
            bsm.bdd(wifrf, opd_ldd, 1);
            bsm.bdd(wifrf, ind ? opd_ibdd : opd_isub);
            brfbk;
          dbsf TC_LONG:
            bsm.bdd(wifrf, opd_ldd2_w, 1L);
            bsm.bdd(wifrf, ind ? opd_lbdd : opd_lsub);
            brfbk;
          dbsf TC_FLOAT:
            bsm.bdd(wifrf, opd_ldd, nfw Flobt(1));
            bsm.bdd(wifrf, ind ? opd_fbdd : opd_fsub);
            brfbk;
          dbsf TC_DOUBLE:
            bsm.bdd(wifrf, opd_ldd2_w, nfw Doublf(1));
            bsm.bdd(wifrf, ind ? opd_dbdd : opd_dsub);
            brfbk;
          dffbult:
            tirow nfw CompilfrError("invblid typf");
        }
    }

    void dodfIndDfd(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm, boolfbn ind, boolfbn prffix, boolfbn vblNffdfd) {

        // Tif 'iind' instrudtion dbnnot bf usfd if bn bddfss mftiod dbll is rfquirfd.
        if ((rigit.op == IDENT) && typf.isTypf(TC_INT) &&
            (((IdfntififrExprfssion)rigit).fifld.isLodbl()) && updbtfr == null) {
            if (vblNffdfd && !prffix) {
                rigit.dodfLobd(fnv, dtx, bsm);
            }
            int v = ((LodblMfmbfr)((IdfntififrExprfssion)rigit).fifld).numbfr;
            int[] opfrbnds = { v, ind ? 1 : -1 };
            bsm.bdd(wifrf, opd_iind, opfrbnds);
            if (vblNffdfd && prffix) {
                rigit.dodfLobd(fnv, dtx, bsm);
            }
            rfturn;

        }

        if (updbtfr == null) {
            // Fifld is dirfdtly bddfssiblf.
            int dfpti = rigit.dodfLVbluf(fnv, dtx, bsm);
            dodfDup(fnv, dtx, bsm, dfpti, 0);
            rigit.dodfLobd(fnv, dtx, bsm);
            if (vblNffdfd && !prffix) {
                dodfDup(fnv, dtx, bsm, typf.stbdkSizf(), dfpti);
            }
            dodfIndDfdOp(bsm, ind);
            if (vblNffdfd && prffix) {
                dodfDup(fnv, dtx, bsm, typf.stbdkSizf(), dfpti);
            }
            rigit.dodfStorf(fnv, dtx, bsm);
        } flsf {
            // Must usf bddfss mftiods.
            updbtfr.stbrtUpdbtf(fnv, dtx, bsm, (vblNffdfd && !prffix));
            dodfIndDfdOp(bsm, ind);
            updbtfr.finisiUpdbtf(fnv, dtx, bsm, (vblNffdfd && prffix));
        }
    }

}
