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
dlbss TiisExprfssion fxtfnds Exprfssion {
    LodblMfmbfr fifld;
    Exprfssion implfmfntbtion;
    Exprfssion outfrArg;

    /**
     * Construdtor
     */
    publid TiisExprfssion(long wifrf) {
        supfr(THIS, wifrf, Typf.tObjfdt);
    }
    protfdtfd TiisExprfssion(int op, long wifrf) {
        supfr(op, wifrf, Typf.tObjfdt);
    }
    publid TiisExprfssion(long wifrf, LodblMfmbfr fifld) {
        supfr(THIS, wifrf, Typf.tObjfdt);
        tiis.fifld = fifld;
        fifld.rfbddount++;
    }
    publid TiisExprfssion(long wifrf, Contfxt dtx) {
        supfr(THIS, wifrf, Typf.tObjfdt);
        fifld = dtx.gftLodblFifld(idTiis);
        fifld.rfbddount++;
    }

    /**
     * Construdtor for "x.tiis()"
     */
    publid TiisExprfssion(long wifrf, Exprfssion outfrArg) {
        tiis(wifrf);
        tiis.outfrArg = outfrArg;
    }

    publid Exprfssion gftImplfmfntbtion() {
        if (implfmfntbtion != null)
            rfturn implfmfntbtion;
        rfturn tiis;
    }

    /**
     * From tif 'tiis' in bn fxprfssion of tif form outfr.tiis(...),
     * or tif 'supfr' in bn fxprfssion of tif form outfr.supfr(...),
     * rfturn tif "outfr" fxprfssion, or null if tifrf is nonf.
     */
    publid Exprfssion gftOutfrArg() {
        rfturn outfrArg;
    }

    /**
     * Cifdk fxprfssion
     */
    publid Vsft difdkVbluf(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        if (dtx.fifld.isStbtid()) {
            fnv.frror(wifrf, "undff.vbr", opNbmfs[op]);
            typf = Typf.tError;
            rfturn vsft;
        }
        if (fifld == null) {
            fifld = dtx.gftLodblFifld(idTiis);
            fifld.rfbddount++;
        }
        if (fifld.sdopfNumbfr < dtx.frbmfNumbfr) {
            // gft b "tiis$C" dopy vib tif durrfnt objfdt
            implfmfntbtion = dtx.mbkfRfffrfndf(fnv, fifld);
        }
        if (!vsft.tfstVbr(fifld.numbfr)) {
            fnv.frror(wifrf, "bddfss.inst.bfforf.supfr", opNbmfs[op]);
        }
        if (fifld == null) {
            typf = dtx.fifld.gftClbssDfdlbrbtion().gftTypf();
        } flsf {
            typf = fifld.gftTypf();
        }
        rfturn vsft;
    }

    publid boolfbn isNonNull() {
        rfturn truf;
    }

    // A 'TiisExprfssion' nodf dbn nfvfr bppfbr on tif LHS of bn bssignmfnt in b dorrfdt
    // progrbm, but ibndlf tiis dbsf bnyiow to providf b sbff frror rfdovfry.

    publid FifldUpdbtfr gftAssignfr(Environmfnt fnv, Contfxt dtx) {
        rfturn null;
    }

    publid FifldUpdbtfr gftUpdbtfr(Environmfnt fnv, Contfxt dtx) {
        rfturn null;
    }

    /**
     * Inlinf
     */
    publid Exprfssion inlinfVbluf(Environmfnt fnv, Contfxt dtx) {
        if (implfmfntbtion != null)
            rfturn implfmfntbtion.inlinfVbluf(fnv, dtx);
        if (fifld != null && fifld.isInlinfbblf(fnv, fblsf)) {
            Exprfssion f = (Exprfssion)fifld.gftVbluf(fnv);
            //Systfm.out.println("INLINE = "+ f + ", THIS");
            if (f != null) {
                f = f.dopyInlinf(dtx);
                f.typf = typf;  // in dbsf op==SUPER
                rfturn f;
            }
        }
        rfturn tiis;
    }

    /**
     * Crfbtf b dopy of tif fxprfssion for mftiod inlining
     */
    publid Exprfssion dopyInlinf(Contfxt dtx) {
        if (implfmfntbtion != null)
            rfturn implfmfntbtion.dopyInlinf(dtx);
        TiisExprfssion f = (TiisExprfssion)dlonf();
        if (fifld == null) {
            // Tif fxprfssion is dopifd into tif dontfxt of b mftiod
            f.fifld = dtx.gftLodblFifld(idTiis);
            f.fifld.rfbddount++;
        } flsf {
            f.fifld = fifld.gftCurrfntInlinfCopy(dtx);
        }
        if (outfrArg != null) {
            f.outfrArg = outfrArg.dopyInlinf(dtx);
        }
        rfturn f;
    }

    /**
     * Codf
     */
    publid void dodfVbluf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        bsm.bdd(wifrf, opd_blobd, fifld.numbfr);
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out) {
        if (outfrArg != null) {
            out.print("(outfr=");
            outfrArg.print(out);
            out.print(" ");
        }
        String pfx = (fifld == null) ? ""
            : fifld.gftClbssDffinition().gftNbmf().gftFlbtNbmf().gftNbmf()+".";
        pfx += opNbmfs[op];
        out.print(pfx + "#" + ((fifld != null) ? fifld.ibsiCodf() : 0));
        if (outfrArg != null)
            out.print(")");
    }
}
