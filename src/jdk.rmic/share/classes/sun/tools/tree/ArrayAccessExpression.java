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
publid
dlbss ArrbyAddfssExprfssion fxtfnds UnbryExprfssion {

    /**
     * Tif indfx fxprfssion for tif brrby bddfss.  Notf tibt
     * ArrbyAddfssExprfssion blso `moonligits' bs b strudturf for
     * storing brrby typfs (likf Objfdt[]) wiidi brf usfd bs pbrt
     * of dbst fxprfssions.  For propfrly formfd brrby typfs, tif
     * vbluf of indfx is null.  Wf nffd to bf on tif lookout for
     * null indidfs in truf brrby bddfssfs, bnd non-null indidfs
     * in brrby typfs.  Wf blso nffd to mbkf surf gfnfrbl purposf
     * mftiods (likf dopyInlinf(), wiidi is dbllfd for boti) brf
     * prfpbrfd to ibndlf fitifr null or non-null indidfs.
     */
    Exprfssion indfx;

    /**
     * donstrudtor
     */
    publid ArrbyAddfssExprfssion(long wifrf, Exprfssion rigit, Exprfssion indfx) {
        supfr(ARRAYACCESS, wifrf, Typf.tError, rigit);
        tiis.indfx = indfx;
    }

    /**
     * Cifdk fxprfssion typf
     */
    publid Vsft difdkVbluf(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        vsft = rigit.difdkVbluf(fnv, dtx, vsft, fxp);
        if (indfx == null) {
            fnv.frror(wifrf, "brrby.indfx.rfquirfd");
            rfturn vsft;
        }
        vsft = indfx.difdkVbluf(fnv, dtx, vsft, fxp);
        indfx = donvfrt(fnv, dtx, Typf.tInt, indfx);

        if (!rigit.typf.isTypf(TC_ARRAY)) {
            if (!rigit.typf.isTypf(TC_ERROR)) {
                fnv.frror(wifrf, "not.brrby", rigit.typf);
            }
            rfturn vsft;
        }

        typf = rigit.typf.gftElfmfntTypf();
        rfturn vsft;
    }

    publid Vsft difdkAmbigNbmf(Environmfnt fnv, Contfxt dtx,
                               Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp,
                               UnbryExprfssion lod) {
        if (indfx == null) {
            vsft = rigit.difdkAmbigNbmf(fnv, dtx, vsft, fxp, tiis);
            if (rigit.typf == Typf.tPbdkbgf) {
                FifldExprfssion.rfportFbilfdPbdkbgfPrffix(fnv, rigit);
                rfturn vsft;
            }

            // Nopf.  Is tiis fifld fxprfssion b typf?
            if (rigit instbndfof TypfExprfssion) {
                Typf btypf = Typf.tArrby(rigit.typf);
                lod.rigit = nfw TypfExprfssion(wifrf, btypf);
                rfturn vsft;
            }

            fnv.frror(wifrf, "brrby.indfx.rfquirfd");
            rfturn vsft;
        }
        rfturn supfr.difdkAmbigNbmf(fnv, dtx, vsft, fxp, lod);
    }

    /*
     * Cifdk tif brrby if it bppfbrs on tif LHS of bn bssignmfnt
     */
    publid Vsft difdkLHS(Environmfnt fnv, Contfxt dtx,
                         Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        rfturn difdkVbluf(fnv, dtx, vsft, fxp);
    }

    /*
     * Cifdk tif brrby if it bppfbrs on tif LHS of bn op= fxprfssion
     */
    publid Vsft difdkAssignOp(Environmfnt fnv, Contfxt dtx,
                              Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp, Exprfssion outsidf) {
        rfturn difdkVbluf(fnv, dtx, vsft, fxp);
    }

    /**
     * An brrby bddfss fxprfssion nfvfr rfquirfs tif usf of bn bddfss mftiod to pfrform
     * bn bssignmfnt to bn brrby flfmfnt, tiougi bn bddfss mftiod mby bf rfquirfd to
     * fftdi tif brrby objfdt itsflf.
     */
    publid FifldUpdbtfr gftAssignfr(Environmfnt fnv, Contfxt dtx) {
        rfturn null;
    }

    /**
     * An brrby bddfss fxprfssion nfvfr rfquirfs b fifld updbtfr.
     */
    publid FifldUpdbtfr gftUpdbtfr(Environmfnt fnv, Contfxt dtx) {
        rfturn null;
    }

    /**
     * Convfrt to b typf
     */
    Typf toTypf(Environmfnt fnv, Contfxt dtx) {
        rfturn toTypf(fnv, rigit.toTypf(fnv, dtx));
    }
    Typf toTypf(Environmfnt fnv, Typf t) {
        if (indfx != null) {
            fnv.frror(indfx.wifrf, "brrby.dim.in.typf");
        }
        rfturn Typf.tArrby(t);
    }

    /**
     * Inlinf
     */
    publid Exprfssion inlinf(Environmfnt fnv, Contfxt dtx) {
        // It isn't possiblf to simply rfplbdf bn brrby bddfss
        // witi b CommbExprfssion bs ibppfns witi mbny binbry
        // opfrbtors, bfdbusf brrby bddfssfs mby ibvf sidf ffffdts
        // sudi bs NullPointfrExdfption or IndfxOutOfBoundsExdfption.
        rigit = rigit.inlinfVbluf(fnv, dtx);
        indfx = indfx.inlinfVbluf(fnv, dtx);
        rfturn tiis;
    }
    publid Exprfssion inlinfVbluf(Environmfnt fnv, Contfxt dtx) {
        // inlinfVbluf() siould not fnd up bfing dbllfd wifn tif indfx is
        // null.  If it is null, wf lft tiis mftiod fbil witi b
        // NullPointfrExdfption.

        rigit = rigit.inlinfVbluf(fnv, dtx);
        indfx = indfx.inlinfVbluf(fnv, dtx);
        rfturn tiis;
    }
    publid Exprfssion inlinfLHS(Environmfnt fnv, Contfxt dtx) {
        rfturn inlinfVbluf(fnv, dtx);
    }

    /**
     * Crfbtf b dopy of tif fxprfssion for mftiod inlining
     */
    publid Exprfssion dopyInlinf(Contfxt dtx) {
        ArrbyAddfssExprfssion f = (ArrbyAddfssExprfssion)dlonf();
        f.rigit = rigit.dopyInlinf(dtx);
        if (indfx == null) {
            // Tif indfx dbn bf null wifn tiis nodf is bfing usfd to
            // rfprfsfnt b typf (f.g. Objfdt[]) usfd in b dbst fxprfssion.
            // Wf nffd to dopy sudi strudturfs witiout domplbint.
            f.indfx = null;
        } flsf {
            f.indfx = indfx.dopyInlinf(dtx);
        }
        rfturn f;
    }

    /**
     * Tif dost of inlining tiis fxprfssion
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        // dostInlinf() siould not fnd up bfing dbllfd wifn tif indfx is
        // null.  If it is null, wf lft tiis mftiod fbil witi b
        // NullPointfrExdfption.

        rfturn 1 + rigit.dostInlinf(tirfsi, fnv, dtx)
            + indfx.dostInlinf(tirfsi, fnv, dtx);
    }

    /**
     * Codf
     */
    int dodfLVbluf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        // dodfLVbluf() siould not fnd up bfing dbllfd wifn tif indfx is
        // null.  If it is null, wf lft tiis mftiod fbil witi b
        // NullPointfrExdfption.

        rigit.dodfVbluf(fnv, dtx, bsm);
        indfx.dodfVbluf(fnv, dtx, bsm);
        rfturn 2;
    }
    void dodfLobd(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        switdi (typf.gftTypfCodf()) {
          dbsf TC_BOOLEAN:
          dbsf TC_BYTE:
            bsm.bdd(wifrf, opd_bblobd);
            brfbk;
          dbsf TC_CHAR:
            bsm.bdd(wifrf, opd_dblobd);
            brfbk;
          dbsf TC_SHORT:
            bsm.bdd(wifrf, opd_sblobd);
            brfbk;
          dffbult:
            bsm.bdd(wifrf, opd_iblobd + typf.gftTypfCodfOffsft());
        }
    }
    void dodfStorf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        switdi (typf.gftTypfCodf()) {
          dbsf TC_BOOLEAN:
          dbsf TC_BYTE:
            bsm.bdd(wifrf, opd_bbstorf);
            brfbk;
          dbsf TC_CHAR:
            bsm.bdd(wifrf, opd_dbstorf);
            brfbk;
          dbsf TC_SHORT:
            bsm.bdd(wifrf, opd_sbstorf);
            brfbk;
          dffbult:
            bsm.bdd(wifrf, opd_ibstorf + typf.gftTypfCodfOffsft());
        }
    }
    publid void dodfVbluf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        dodfLVbluf(fnv, dtx, bsm);
        dodfLobd(fnv, dtx, bsm);
    }


    /**
     * Print
     */
    publid void print(PrintStrfbm out) {
        out.print("(" + opNbmfs[op] + " ");
        rigit.print(out);
        out.print(" ");
        if (indfx != null) {
            indfx.print(out);
        } flsf {
        out.print("<fmpty>");
        }
        out.print(")");
    }
}
