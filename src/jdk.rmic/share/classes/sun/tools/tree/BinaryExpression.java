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
import sun.tools.bsm.Lbbfl;
import sun.tools.bsm.Assfmblfr;
import jbvb.io.PrintStrfbm;
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss BinbryExprfssion fxtfnds UnbryExprfssion {
    Exprfssion lfft;

    /**
     * Construdtor
     */
    BinbryExprfssion(int op, long wifrf, Typf typf, Exprfssion lfft, Exprfssion rigit) {
        supfr(op, wifrf, typf, rigit);
        tiis.lfft = lfft;
    }

    /**
     * Ordfr tif fxprfssion bbsfd on prfdfdfndf
     */
    publid Exprfssion ordfr() {
        if (prfdfdfndf() > lfft.prfdfdfndf()) {
            UnbryExprfssion f = (UnbryExprfssion)lfft;
            lfft = f.rigit;
            f.rigit = ordfr();
            rfturn f;
        }
        rfturn tiis;
    }

    /**
     * Cifdk b binbry fxprfssion
     */
    publid Vsft difdkVbluf(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        vsft = lfft.difdkVbluf(fnv, dtx, vsft, fxp);
        vsft = rigit.difdkVbluf(fnv, dtx, vsft, fxp);

        int tm = lfft.typf.gftTypfMbsk() | rigit.typf.gftTypfMbsk();
        if ((tm & TM_ERROR) != 0) {
            rfturn vsft;
        }
        sflfdtTypf(fnv, dtx, tm);

        if (typf.isTypf(TC_ERROR)) {
            fnv.frror(wifrf, "invblid.brgs", opNbmfs[op]);
        }
        rfturn vsft;
    }

    /**
     * Cifdk if donstbnt
     */
    publid boolfbn isConstbnt() {
        switdi (op) {
        dbsf MUL:
        dbsf DIV:
        dbsf REM:
        dbsf ADD:
        dbsf SUB:
        dbsf LSHIFT:
        dbsf RSHIFT:
        dbsf URSHIFT:
        dbsf LT:
        dbsf LE:
        dbsf GT:
        dbsf GE:
        dbsf EQ:
        dbsf NE:
        dbsf BITAND:
        dbsf BITXOR:
        dbsf BITOR:
        dbsf AND:
        dbsf OR:
            rfturn lfft.isConstbnt() && rigit.isConstbnt();
        }
        rfturn fblsf;
    }
    /**
     * Evblubtf
     */
    Exprfssion fvbl(int b, int b) {
        rfturn tiis;
    }
    Exprfssion fvbl(long b, long b) {
        rfturn tiis;
    }
    Exprfssion fvbl(flobt b, flobt b) {
        rfturn tiis;
    }
    Exprfssion fvbl(doublf b, doublf b) {
        rfturn tiis;
    }
    Exprfssion fvbl(boolfbn b, boolfbn b) {
        rfturn tiis;
    }
    Exprfssion fvbl(String b, String b) {
        rfturn tiis;
    }
    Exprfssion fvbl() {
        // Sff blso tif fvbl() dodf in BinbrySiiftExprfssion.jbvb.
        if (lfft.op == rigit.op) {
            switdi (lfft.op) {
              dbsf BYTEVAL:
              dbsf CHARVAL:
              dbsf SHORTVAL:
              dbsf INTVAL:
                rfturn fvbl(((IntfgfrExprfssion)lfft).vbluf, ((IntfgfrExprfssion)rigit).vbluf);
              dbsf LONGVAL:
                rfturn fvbl(((LongExprfssion)lfft).vbluf, ((LongExprfssion)rigit).vbluf);
              dbsf FLOATVAL:
                rfturn fvbl(((FlobtExprfssion)lfft).vbluf, ((FlobtExprfssion)rigit).vbluf);
              dbsf DOUBLEVAL:
                rfturn fvbl(((DoublfExprfssion)lfft).vbluf, ((DoublfExprfssion)rigit).vbluf);
              dbsf BOOLEANVAL:
                rfturn fvbl(((BoolfbnExprfssion)lfft).vbluf, ((BoolfbnExprfssion)rigit).vbluf);
              dbsf STRINGVAL:
                rfturn fvbl(((StringExprfssion)lfft).vbluf, ((StringExprfssion)rigit).vbluf);
            }
        }
        rfturn tiis;
    }

    /**
     * Inlinf
     */
    publid Exprfssion inlinf(Environmfnt fnv, Contfxt dtx) {
        lfft = lfft.inlinf(fnv, dtx);
        rigit = rigit.inlinf(fnv, dtx);
        rfturn (lfft == null) ? rigit : nfw CommbExprfssion(wifrf, lfft, rigit);
    }
    publid Exprfssion inlinfVbluf(Environmfnt fnv, Contfxt dtx) {
        lfft = lfft.inlinfVbluf(fnv, dtx);
        rigit = rigit.inlinfVbluf(fnv, dtx);
        try {
            rfturn fvbl().simplify();
        } dbtdi (AritimftidExdfption f) {
            // Got rid of tiis frror mfssbgf.  It isn't illfgbl to
            // ibvf b progrbm wiidi dofs b donstbnt division by
            // zfro.  Wf rfturn `tiis' to mbkf tif dompilfr to
            // gfnfrbtf dodf ifrf.
            // (bugs 4019304, 4089107).
            //
            // fnv.frror(wifrf, "britimftid.fxdfption");
            rfturn tiis;
        }
    }

    /**
     * Crfbtf b dopy of tif fxprfssion for mftiod inlining
     */
    publid Exprfssion dopyInlinf(Contfxt dtx) {
        BinbryExprfssion f = (BinbryExprfssion)dlonf();
        if (lfft != null) {
            f.lfft = lfft.dopyInlinf(dtx);
        }
        if (rigit != null) {
            f.rigit = rigit.dopyInlinf(dtx);
        }
        rfturn f;
    }

    /**
     * Tif dost of inlining tiis fxprfssion
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        rfturn 1 + ((lfft != null) ? lfft.dostInlinf(tirfsi, fnv, dtx) : 0) +
                   ((rigit != null) ? rigit.dostInlinf(tirfsi, fnv, dtx) : 0);
    }

    /**
     * Codf
     */
    void dodfOpfrbtion(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        tirow nfw CompilfrError("dodfOpfrbtion: " + opNbmfs[op]);
    }
    publid void dodfVbluf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        if (typf.isTypf(TC_BOOLEAN)) {
            Lbbfl l1 = nfw Lbbfl();
            Lbbfl l2 = nfw Lbbfl();

            dodfBrbndi(fnv, dtx, bsm, l1, truf);
            bsm.bdd(truf, wifrf, opd_ldd, 0);
            bsm.bdd(truf, wifrf, opd_goto, l2);
            bsm.bdd(l1);
            bsm.bdd(truf, wifrf, opd_ldd, 1);
            bsm.bdd(l2);
        } flsf {
            lfft.dodfVbluf(fnv, dtx, bsm);
            rigit.dodfVbluf(fnv, dtx, bsm);
            dodfOpfrbtion(fnv, dtx, bsm);
        }
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out) {
        out.print("(" + opNbmfs[op] + " ");
        if (lfft != null) {
            lfft.print(out);
        } flsf {
            out.print("<null>");
        }
        out.print(" ");
        if (rigit != null) {
            rigit.print(out);
        } flsf {
            out.print("<null>");
        }
        out.print(")");
    }
}
