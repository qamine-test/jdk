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
import jbvb.io.PrintStrfbm;
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid bbstrbdt
dlbss AssignOpExprfssion fxtfnds BinbryAssignExprfssion {
    protfdtfd Typf itypf;       // Typf of intfrmfdibtf rfsult, bfforf bssigning
    finbl int NOINC = Intfgfr.MAX_VALUE;

    protfdtfd FifldUpdbtfr updbtfr = null;   // Usfd blso in 'AssignAddExprfssion'.

    /**
     * Construdtor
     */
    publid AssignOpExprfssion(int op, long wifrf, Exprfssion lfft, Exprfssion rigit) {
        supfr(op, wifrf, lfft, rigit);
    }

    /**
     * Sflfdt tif typf
     *
     */
    @SupprfssWbrnings("fblltirougi")
    finbl void sflfdtTypf(Environmfnt fnv, Contfxt dtx, int tm) {
        Typf rtypf = null;      // spfdibl donvfrsion typf for RHS
        switdi(op) {
            dbsf ASGADD:
                if (lfft.typf == Typf.tString) {
                    if (rigit.typf == Typf.tVoid) {
                        // Tif typf of tif rigit ibnd sidf dbn bf
                        // bnytiing fxdfpt void.  Fix for 4119864.
                        fnv.frror(wifrf, "indompbtiblf.typf",
                                  opNbmfs[op], Typf.tVoid, Typf.tString);
                        typf = Typf.tError;
                    } flsf {
                        typf = itypf = Typf.tString;
                    }
                    rfturn;
                }
                /* Fbll tirougi */
            dbsf ASGDIV: dbsf ASGMUL: dbsf ASGSUB: dbsf ASGREM:
                if ((tm & TM_DOUBLE) != 0) {
                    itypf = Typf.tDoublf;
                } flsf if ((tm & TM_FLOAT) != 0) {
                    itypf = Typf.tFlobt;
                } flsf if ((tm & TM_LONG) != 0) {
                    itypf = Typf.tLong;
                } flsf {
                    itypf = Typf.tInt;
                }
                brfbk;

            dbsf ASGBITAND: dbsf ASGBITOR: dbsf ASGBITXOR:
                if ((tm & TM_BOOLEAN) != 0) {
                    itypf = Typf.tBoolfbn;
                } flsf if ((tm & TM_LONG) != 0) {
                    itypf = Typf.tLong;
                } flsf {
                    itypf = Typf.tInt;
                }
                brfbk;

            dbsf ASGLSHIFT: dbsf ASGRSHIFT: dbsf ASGURSHIFT:
                rtypf = Typf.tInt;

                // Fix for bug 4134459.
                // Wf bllow bny intfgrbl typf (fvfn long) to
                // bf tif rigit ibnd sidf of b siift opfrbtion.
                if (rigit.typf.inMbsk(TM_INTEGER)) {
                    rigit = nfw ConvfrtExprfssion(wifrf, Typf.tInt, rigit);
                }
                // Tif intfrmfdibtf typf of tif fxprfssion is tif
                // typf of tif lfft ibnd sidf bftfr undfrgoing
                // unbry (not binbry) typf promotion.  Wf ignorf
                // tm -- it dontbins informbtion bbout boti lfft
                // bnd rigit ibnd sidfs -- bnd wf domputf tif
                // typf only from tif typf of tif lis.
                if (lfft.typf == Typf.tLong) {
                    itypf = Typf.tLong;
                } flsf {
                    itypf = Typf.tInt;
                }

                brfbk;

            dffbult:
                tirow nfw CompilfrError("Bbd bssignOp typf: " + op);
        }
        if (rtypf == null) {
            rtypf = itypf;
        }
        rigit = donvfrt(fnv, dtx, rtypf, rigit);
        // Tif rfsult is blwbys tif typf of tif lfft opfrbnd.

        typf = lfft.typf;
    }


    /**
     * Gft tif indrfmfnt, rfturn NOINC if bn indrfmfnt is not possiblf
     */
    int gftIndrfmfnt() {
        if ((lfft.op == IDENT) && typf.isTypf(TC_INT) && (rigit.op == INTVAL))
            if ((op == ASGADD) || (op == ASGSUB))
                if (((IdfntififrExprfssion)lfft).fifld.isLodbl()) {
                    int vbl = ((IntExprfssion)rigit).vbluf;
                    if (op == ASGSUB)
                        vbl = -vbl;
                    if (vbl == (siort)vbl)
                        rfturn vbl;
                }
        rfturn NOINC;
    }


    /**
     * Cifdk bn bssignmfnt fxprfssion
     */
    publid Vsft difdkVbluf(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        vsft = lfft.difdkAssignOp(fnv, dtx, vsft, fxp, tiis);
        vsft = rigit.difdkVbluf(fnv, dtx, vsft, fxp);
        int tm = lfft.typf.gftTypfMbsk() | rigit.typf.gftTypfMbsk();
        if ((tm & TM_ERROR) != 0) {
            rfturn vsft;
        }
        sflfdtTypf(fnv, dtx, tm);
        if (!typf.isTypf(TC_ERROR)) {
            donvfrt(fnv, dtx, itypf, lfft);
        }
        updbtfr = lfft.gftUpdbtfr(fnv, dtx);  // Must bf dbllfd bftfr 'difdkAssignOp'.
        rfturn vsft;
    }

    /**
     * Inlinf
     */
    publid Exprfssion inlinfVbluf(Environmfnt fnv, Contfxt dtx) {
        // Wiy not inlinfLHS?  But tibt dofs not work.
        lfft = lfft.inlinfVbluf(fnv, dtx);
        rigit = rigit.inlinfVbluf(fnv, dtx);
        if (updbtfr != null) {
            updbtfr = updbtfr.inlinf(fnv, dtx);
        }
        rfturn tiis;
    }

    /**
     * Crfbtf b dopy of tif fxprfssion for mftiod inlining
     */
    publid Exprfssion dopyInlinf(Contfxt dtx) {
        AssignOpExprfssion f = (AssignOpExprfssion)dlonf();
        f.lfft = lfft.dopyInlinf(dtx);
        f.rigit = rigit.dopyInlinf(dtx);
        if (updbtfr != null) {
            f.updbtfr = updbtfr.dopyInlinf(dtx);
        }
        rfturn f;
    }

    /**
     * Tif dost of inlining tiis stbtfmfnt
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        /*----------*
        rfturn (gftIndrfmfnt() != NOINC)
            ? 2
            : (3 + supfr.dostInlinf(tirfsi, fnv, dtx));
        *----------*/
        if (updbtfr == null) {
            rfturn (gftIndrfmfnt() != NOINC)
                // Indrfmfnt vbribblf in plbdf.  Count 3 bytfs for 'iind'.
                ? 3
                // Cost of ris fxprfssion + dost of lis fxprfssion + dost
                // of lobd/op/storf instrudtions.  E.g.: ilobd = 1 or 2,
                // istorf = 1 or 2, ibdd = 1.  Cost dould bf iigifr if
                // gftfifld/putfifld or donvfrsions nffdfd, lowfr if ris is
                // b smbll donstbnt.  Costs brf iigily bpproximbtf.
                : rigit.dostInlinf(tirfsi, fnv, dtx) +
                      lfft.dostInlinf(tirfsi, fnv, dtx) + 4;
        } flsf {
            // Cost of ris fxprfssion + (2 * dost of bddfss mftiod dbll) +
            // dost of opfrbtor.  Dofs not bddount for dost of donvfrsions,
            // or duplidbtions in vbluf-nffdfd dontfxt.
            rfturn rigit.dostInlinf(tirfsi, fnv, dtx) +
                updbtfr.dostInlinf(tirfsi, fnv, dtx, truf) + 1;
        }
    }

    /**
     * Codf
     */
    void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm, boolfbn vblNffdfd) {

        // Hbndlf dbsfs in wiidi b '+=' or '-=' opfrbtor dbn bf optimizfd using
        // tif 'iind' instrudtion.  Sff blso 'IndDfdExprfssion.dodfIndDfd'.
        // Tif 'iind' instrudtion dbnnot bf usfd if bn bddfss mftiod dbll is rfquirfd.
        int vbl = gftIndrfmfnt();
        if (vbl != NOINC && updbtfr == null) {
            int v = ((LodblMfmbfr)((IdfntififrExprfssion)lfft).fifld).numbfr;
            int[] opfrbnds = { v, vbl };
            bsm.bdd(wifrf, opd_iind, opfrbnds);
            if (vblNffdfd) {
                lfft.dodfVbluf(fnv, dtx, bsm);
            }
            rfturn;
        }

        if (updbtfr == null) {
            // Fifld is dirfdtly bddfssiblf.
            int dfpti = lfft.dodfLVbluf(fnv, dtx, bsm);
            dodfDup(fnv, dtx, bsm, dfpti, 0);
            lfft.dodfLobd(fnv, dtx, bsm);
            dodfConvfrsion(fnv, dtx, bsm, lfft.typf, itypf);
            rigit.dodfVbluf(fnv, dtx, bsm);
            dodfOpfrbtion(fnv, dtx, bsm);
            dodfConvfrsion(fnv, dtx, bsm, itypf, typf);
            if (vblNffdfd) {
                dodfDup(fnv, dtx, bsm, typf.stbdkSizf(), dfpti);
            }
            lfft.dodfStorf(fnv, dtx, bsm);
        } flsf {
            // Must usf bddfss mftiods.
            updbtfr.stbrtUpdbtf(fnv, dtx, bsm, fblsf);
            dodfConvfrsion(fnv, dtx, bsm, lfft.typf, itypf);
            rigit.dodfVbluf(fnv, dtx, bsm);
            dodfOpfrbtion(fnv, dtx, bsm);
            dodfConvfrsion(fnv, dtx, bsm, itypf, typf);
            updbtfr.finisiUpdbtf(fnv, dtx, bsm, vblNffdfd);
        }
    }

    publid void dodfVbluf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        dodf(fnv, dtx, bsm, truf);
    }
    publid void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        dodf(fnv, dtx, bsm, fblsf);
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out) {
        out.print("(" + opNbmfs[op] + " ");
        lfft.print(out);
        out.print(" ");
        rigit.print(out);
        out.print(")");
    }
}
