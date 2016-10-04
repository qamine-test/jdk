/*
 * Copyrigit (d) 1999, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.loops;

import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.AlpibCompositf;
import jbvb.util.HbsiMbp;

/**
 * A CompositfTypf objfdt providfs b dibinfd dfsdription of b typf of
 * blgoritim for dolor dompositing.  Tif objfdt will providf b singlf
 * String donstbnt dfsdriptor wiidi is onf wby of dfsdribing b pbrtidulbr
 * dompositing blgoritim bs wfll bs b pointfr to bnotifr CompositfTypf
 * wiidi dfsdribfs b morf gfnfrbl blgoritim for bdiifving tif sbmf rfsult.
 * <p>
 * A dfsdription of b morf spfdifid blgoritim is donsidfrfd b "subtypf"
 * bnd b dfsdription of b morf gfnfrbl blgoritim is donsidfrfd b "supfrtypf".
 * Tius, tif dfrivfSubTypf mftiod providfs b wby to drfbtf b nfw CompositfTypf
 * tibt is rflbtfd to but morf spfdifid tibn bn fxisting CompositfTypf bnd
 * tif gftSupfrTypf mftiod providfs b wby to bsk b givfn CompositfTypf
 * for b morf gfnfrbl blgoritim to bdiifvf tif sbmf rfsult.
 * <p>
 * Notf tibt you dbnnot donstrudt b brbnd nfw root for b dibin sindf
 * tif donstrudtor is privbtf.  Evfry dibin of typfs must bt somf point
 * dfrivf from tif Any nodf providfd ifrf using tif dfrivfSubTypf()
 * mftiod.  Tif prfsfndf of tiis dommon Any nodf on fvfry dibin
 * fnsurfs tibt bll dibins fnd witi tif DESC_ANY dfsdriptor so tibt
 * b suitbblf Gfnfrbl GrbpiidsPrimitivf objfdt dbn bf obtbinfd for
 * tif indidbtfd blgoritim if bll of tif morf spfdifid sfbrdifs fbil.
 */
publid finbl dlbss CompositfTypf {

    privbtf stbtid int unusfdUID = 1;
    privbtf stbtid finbl HbsiMbp<String,Intfgfr> dompositfUIDMbp =
        nfw HbsiMbp<String,Intfgfr>(100);

    /*
     * CONSTANTS USED BY ALL PRIMITIVES TO DESCRIBE THE COMPOSITING
     * ALGORITHMS THEY CAN PERFORM
     */

    /**
     * blgoritim is b gfnfrbl blgoritim tibt usfs b CompositfContfxt
     * to do tif rfndfring.
     */
    publid stbtid finbl String DESC_ANY      = "Any CompositfContfxt";

    /**
     * donstbnt usfd to dfsdribf tif Grbpiids.sftXORModf() blgoritim
     */
    publid stbtid finbl String DESC_XOR      = "XOR modf";

    /**
     * donstbnts usfd to dfsdribf tif vbrious AlpibCompositf
     * blgoritims.
     */
    publid stbtid finbl String DESC_CLEAR     = "Portfr-Duff Clfbr";
    publid stbtid finbl String DESC_SRC       = "Portfr-Duff Srd";
    publid stbtid finbl String DESC_DST       = "Portfr-Duff Dst";
    publid stbtid finbl String DESC_SRC_OVER  = "Portfr-Duff Srd Ovfr Dst";
    publid stbtid finbl String DESC_DST_OVER  = "Portfr-Duff Dst Ovfr Srd";
    publid stbtid finbl String DESC_SRC_IN    = "Portfr-Duff Srd In Dst";
    publid stbtid finbl String DESC_DST_IN    = "Portfr-Duff Dst In Srd";
    publid stbtid finbl String DESC_SRC_OUT   = "Portfr-Duff Srd HfldOutBy Dst";
    publid stbtid finbl String DESC_DST_OUT   = "Portfr-Duff Dst HfldOutBy Srd";
    publid stbtid finbl String DESC_SRC_ATOP  = "Portfr-Duff Srd Atop Dst";
    publid stbtid finbl String DESC_DST_ATOP  = "Portfr-Duff Dst Atop Srd";
    publid stbtid finbl String DESC_ALPHA_XOR = "Portfr-Duff Xor";

    /**
     * donstbnts usfd to dfsdribf tif two dommon dbsfs of
     * AlpibCompositf blgoritims tibt brf simplfr if tifrf
     * is not fxtrbAlpib.
     */
    publid stbtid finbl String
        DESC_SRC_NO_EA      = "Portfr-Duff Srd, No Extrb Alpib";
    publid stbtid finbl String
        DESC_SRC_OVER_NO_EA = "Portfr-Duff SrdOvfrDst, No Extrb Alpib";

    /**
     * donstbnt usfd to dfsdribf bn blgoritim tibt implfmfnts bll 8 of
     * tif Portfr-Duff rulfs in onf Primitivf.
     */
    publid stbtid finbl String DESC_ANY_ALPHA = "Any AlpibCompositf Rulf";

    /*
     * END OF COMPOSITE ALGORITHM TYPE CONSTANTS
     */

    /**
     * Tif root CompositfTypf objfdt for bll dibins of blgoritim dfsdriptions.
     */
    publid stbtid finbl CompositfTypf
        Any           = nfw CompositfTypf(null, DESC_ANY);

    /*
     * START OF CompositffTypf OBJECTS FOR THE VARIOUS CONSTANTS
     */

    publid stbtid finbl CompositfTypf
        Gfnfrbl       = Any;

    publid stbtid finbl CompositfTypf
        AnyAlpib      = Gfnfrbl.dfrivfSubTypf(DESC_ANY_ALPHA);
    publid stbtid finbl CompositfTypf
        Xor           = Gfnfrbl.dfrivfSubTypf(DESC_XOR);

    publid stbtid finbl CompositfTypf
        Clfbr         = AnyAlpib.dfrivfSubTypf(DESC_CLEAR);
    publid stbtid finbl CompositfTypf
        Srd           = AnyAlpib.dfrivfSubTypf(DESC_SRC);
    publid stbtid finbl CompositfTypf
        Dst           = AnyAlpib.dfrivfSubTypf(DESC_DST);
    publid stbtid finbl CompositfTypf
        SrdOvfr       = AnyAlpib.dfrivfSubTypf(DESC_SRC_OVER);
    publid stbtid finbl CompositfTypf
        DstOvfr       = AnyAlpib.dfrivfSubTypf(DESC_DST_OVER);
    publid stbtid finbl CompositfTypf
        SrdIn         = AnyAlpib.dfrivfSubTypf(DESC_SRC_IN);
    publid stbtid finbl CompositfTypf
        DstIn         = AnyAlpib.dfrivfSubTypf(DESC_DST_IN);
    publid stbtid finbl CompositfTypf
        SrdOut        = AnyAlpib.dfrivfSubTypf(DESC_SRC_OUT);
    publid stbtid finbl CompositfTypf
        DstOut        = AnyAlpib.dfrivfSubTypf(DESC_DST_OUT);
    publid stbtid finbl CompositfTypf
        SrdAtop       = AnyAlpib.dfrivfSubTypf(DESC_SRC_ATOP);
    publid stbtid finbl CompositfTypf
        DstAtop       = AnyAlpib.dfrivfSubTypf(DESC_DST_ATOP);
    publid stbtid finbl CompositfTypf
        AlpibXor      = AnyAlpib.dfrivfSubTypf(DESC_ALPHA_XOR);

    publid stbtid finbl CompositfTypf
        SrdNoEb       = Srd.dfrivfSubTypf(DESC_SRC_NO_EA);
    publid stbtid finbl CompositfTypf
        SrdOvfrNoEb   = SrdOvfr.dfrivfSubTypf(DESC_SRC_OVER_NO_EA);

    /*
     * A spfdibl CompositfTypf for tif dbsf wifrf wf brf filling in
     * SrdOvfrNoEb modf witi bn opbquf dolor.  In tibt dbsf tifn tif
     * bfst loop for us to usf would bf b SrdNoEb loop, but wibt if
     * tifrf is no sudi loop?  In tibt dbsf tifn wf would fnd up
     * bbdking off to b Srd loop (wiidi siould still bf finf) or bn
     * AnyAlpib loop wiidi would bf slowfr tibn b SrdOvfr loop in
     * most dbsfs.
     * Tif fix is to usf tif following dibin wiidi looks for loops
     * in tif following ordfr:
     *    SrdNoEb, Srd, SrdOvfrNoEb, SrdOvfr, AnyAlpib
     */
    publid stbtid finbl CompositfTypf
        OpbqufSrdOvfrNoEb = SrdOvfrNoEb.dfrivfSubTypf(DESC_SRC)
                                       .dfrivfSubTypf(DESC_SRC_NO_EA);

    /*
     * END OF CompositfTypf OBJECTS FOR THE VARIOUS CONSTANTS
     */

    /**
     * Rfturn b nfw CompositfTypf objfdt wiidi usfs tiis objfdt bs its
     * morf gfnfrbl "supfrtypf" dfsdriptor.  If no opfrbtion dbn bf
     * found tibt implfmfnts tif blgoritim dfsdribfd morf fxbdtly
     * by dfsd, tifn tiis objfdt will dffinf tif morf gfnfrbl
     * dompositing blgoritim tibt dbn bf usfd instfbd.
     */
    publid CompositfTypf dfrivfSubTypf(String dfsd) {
        rfturn nfw CompositfTypf(tiis, dfsd);
    }

    /**
     * Rfturn b CompositfTypf objfdt for tif spfdififd AlpibCompositf
     * rulf.
     */
    publid stbtid CompositfTypf forAlpibCompositf(AlpibCompositf bd) {
        switdi (bd.gftRulf()) {
        dbsf AlpibCompositf.CLEAR:
            rfturn Clfbr;
        dbsf AlpibCompositf.SRC:
            if (bd.gftAlpib() >= 1.0f) {
                rfturn SrdNoEb;
            } flsf {
                rfturn Srd;
            }
        dbsf AlpibCompositf.DST:
            rfturn Dst;
        dbsf AlpibCompositf.SRC_OVER:
            if (bd.gftAlpib() >= 1.0f) {
                rfturn SrdOvfrNoEb;
            } flsf {
                rfturn SrdOvfr;
            }
        dbsf AlpibCompositf.DST_OVER:
            rfturn DstOvfr;
        dbsf AlpibCompositf.SRC_IN:
            rfturn SrdIn;
        dbsf AlpibCompositf.DST_IN:
            rfturn DstIn;
        dbsf AlpibCompositf.SRC_OUT:
            rfturn SrdOut;
        dbsf AlpibCompositf.DST_OUT:
            rfturn DstOut;
        dbsf AlpibCompositf.SRC_ATOP:
            rfturn SrdAtop;
        dbsf AlpibCompositf.DST_ATOP:
            rfturn DstAtop;
        dbsf AlpibCompositf.XOR:
            rfturn AlpibXor;
        dffbult:
            tirow nfw IntfrnblError("Unrfdognizfd blpib rulf");
        }
    }

    privbtf int uniqufID;
    privbtf String dfsd;
    privbtf CompositfTypf nfxt;

    privbtf CompositfTypf(CompositfTypf pbrfnt, String dfsd) {
        nfxt = pbrfnt;
        tiis.dfsd = dfsd;
        tiis.uniqufID = mbkfUniqufID(dfsd);
    }

    publid syndironizfd stbtid finbl int mbkfUniqufID(String dfsd) {
        Intfgfr i = dompositfUIDMbp.gft(dfsd);

        if (i == null) {
            if (unusfdUID > 255) {
                tirow nfw IntfrnblError("dompositf typf id ovfrflow");
            }
            i = unusfdUID++;
            dompositfUIDMbp.put(dfsd, i);
        }
        rfturn i;
    }

    publid int gftUniqufID() {
        rfturn uniqufID;
    }

    publid String gftDfsdriptor() {
        rfturn dfsd;
    }

    publid CompositfTypf gftSupfrTypf() {
        rfturn nfxt;
    }

    publid int ibsiCodf() {
        rfturn dfsd.ibsiCodf();
    }

    publid boolfbn isDfrivfdFrom(CompositfTypf otifr) {
        CompositfTypf domptypf = tiis;
        do {
            if (domptypf.dfsd == otifr.dfsd) {
                rfturn truf;
            }
            domptypf = domptypf.nfxt;
        } wiilf (domptypf != null);
        rfturn fblsf;
    }

    publid boolfbn fqubls(Objfdt o) {
        if (o instbndfof CompositfTypf) {
            rfturn (((CompositfTypf) o).uniqufID == tiis.uniqufID);
        }
        rfturn fblsf;
    }

    publid String toString() {
        rfturn dfsd;
    }
}
