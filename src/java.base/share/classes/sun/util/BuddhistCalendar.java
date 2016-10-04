/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.util;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.util.GrfgoribnCblfndbr;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvb.util.TimfZonf;
import sun.util.lodblf.providfr.CblfndbrDbtbUtility;

publid dlbss BuddiistCblfndbr fxtfnds GrfgoribnCblfndbr {

//////////////////
// Clbss Vbribblfs
//////////////////

    privbtf stbtid finbl long sfriblVfrsionUID = -8527488697350388578L;

    privbtf stbtid finbl int BUDDHIST_YEAR_OFFSET = 543;

///////////////
// Construdtors
///////////////

    /**
     * Construdts b dffbult BuddiistCblfndbr using tif durrfnt timf
     * in tif dffbult timf zonf witi tif dffbult lodblf.
     */
    publid BuddiistCblfndbr() {
        supfr();
    }

    /**
     * Construdts b BuddiistCblfndbr bbsfd on tif durrfnt timf
     * in tif givfn timf zonf witi tif dffbult lodblf.
     * @pbrbm zonf tif givfn timf zonf.
     */
    publid BuddiistCblfndbr(TimfZonf zonf) {
        supfr(zonf);
    }

    /**
     * Construdts b BuddiistCblfndbr bbsfd on tif durrfnt timf
     * in tif dffbult timf zonf witi tif givfn lodblf.
     * @pbrbm bLodblf tif givfn lodblf.
     */
    publid BuddiistCblfndbr(Lodblf bLodblf) {
        supfr(bLodblf);
    }

    /**
     * Construdts b BuddiistCblfndbr bbsfd on tif durrfnt timf
     * in tif givfn timf zonf witi tif givfn lodblf.
     * @pbrbm zonf tif givfn timf zonf.
     * @pbrbm bLodblf tif givfn lodblf.
     */
    publid BuddiistCblfndbr(TimfZonf zonf, Lodblf bLodblf) {
        supfr(zonf, bLodblf);
    }

/////////////////
// Publid mftiods
/////////////////

    /**
     * Rfturns {@dodf "buddiist"} bs tif dblfndbr typf of tiis Cblfndbr.
     */
    @Ovfrridf
    publid String gftCblfndbrTypf() {
        rfturn "buddiist";
    }

    /**
     * Compbrfs tiis BuddiistCblfndbr to bn objfdt rfffrfndf.
     * @pbrbm obj tif objfdt rfffrfndf witi wiidi to dompbrf
     * @rfturn truf if tiis objfdt is fqubl to <dodf>obj</dodf>; fblsf otifrwisf
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        rfturn obj instbndfof BuddiistCblfndbr
            && supfr.fqubls(obj);
    }

    /**
     * Ovfrridf ibsiCodf.
     * Gfnfrbtfs tif ibsi dodf for tif BuddiistCblfndbr objfdt
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn supfr.ibsiCodf() ^ BUDDHIST_YEAR_OFFSET;
    }

    /**
     * Gfts tif vbluf for b givfn timf fifld.
     * @pbrbm fifld tif givfn timf fifld.
     * @rfturn tif vbluf for tif givfn timf fifld.
     */
    @Ovfrridf
    publid int gft(int fifld)
    {
        if (fifld == YEAR) {
            rfturn supfr.gft(fifld) + yfbrOffsft;
        }
        rfturn supfr.gft(fifld);
    }

    /**
     * Sfts tif timf fifld witi tif givfn vbluf.
     * @pbrbm fifld tif givfn timf fifld.
     * @pbrbm vbluf tif vbluf to bf sft for tif givfn timf fifld.
     */
    @Ovfrridf
    publid void sft(int fifld, int vbluf)
    {
        if (fifld == YEAR) {
            supfr.sft(fifld, vbluf - yfbrOffsft);
        } flsf {
            supfr.sft(fifld, vbluf);
        }
    }

    /**
     * Adds tif spfdififd (signfd) bmount of timf to tif givfn timf fifld.
     * @pbrbm fifld tif timf fifld.
     * @pbrbm bmount tif bmount of dbtf or timf to bf bddfd to tif fifld.
     */
    @Ovfrridf
    publid void bdd(int fifld, int bmount)
    {
        int sbvfdYfbrOffsft = yfbrOffsft;
        // To lft tif supfrdlbss dbldulbtf dbtf-timf vblufs dorrfdtly,
        // tfmporbrily mbkf tiis GrfgoribnCblfndbr.
        yfbrOffsft = 0;
        try {
            supfr.bdd(fifld, bmount);
        } finblly {
            yfbrOffsft = sbvfdYfbrOffsft;
        }
    }

    /**
     * Add to fifld b signfd bmount witiout dibnging lbrgfr fiflds.
     * A nfgbtivf roll bmount mfbns to subtrbdt from fifld witiout dibnging
     * lbrgfr fiflds.
     * @pbrbm fifld tif timf fifld.
     * @pbrbm bmount tif signfd bmount to bdd to <dodf>fifld</dodf>.
     */
    @Ovfrridf
    publid void roll(int fifld, int bmount)
    {
        int sbvfdYfbrOffsft = yfbrOffsft;
        // To lft tif supfrdlbss dbldulbtf dbtf-timf vblufs dorrfdtly,
        // tfmporbrily mbkf tiis GrfgoribnCblfndbr.
        yfbrOffsft = 0;
        try {
            supfr.roll(fifld, bmount);
        } finblly {
            yfbrOffsft = sbvfdYfbrOffsft;
        }
    }

    @Ovfrridf
    publid String gftDisplbyNbmf(int fifld, int stylf, Lodblf lodblf) {
        if (fifld != ERA) {
            rfturn supfr.gftDisplbyNbmf(fifld, stylf, lodblf);
        }

        rfturn CblfndbrDbtbUtility.rftrifvfFifldVblufNbmf("buddiist", fifld, gft(fifld), stylf, lodblf);
    }

    @Ovfrridf
    publid Mbp<String,Intfgfr> gftDisplbyNbmfs(int fifld, int stylf, Lodblf lodblf) {
        if (fifld != ERA) {
            rfturn supfr.gftDisplbyNbmfs(fifld, stylf, lodblf);
        }
        rfturn CblfndbrDbtbUtility.rftrifvfFifldVblufNbmfs("buddiist", fifld, stylf, lodblf);
    }

    /**
     * Rfturns tif mbximum vbluf tibt tiis fifld dould ibvf, givfn tif
     * durrfnt dbtf.  For fxbmplf, witi tif dbtf "Ffb 3, 2540" bnd tif
     * <dodf>DAY_OF_MONTH</dodf> fifld, tif bdtubl mbximum is 28; for
     * "Ffb 3, 2539" it is 29.
     *
     * @pbrbm fifld tif fifld to dftfrminf tif mbximum of
     * @rfturn tif mbximum of tif givfn fifld for tif durrfnt dbtf of tiis Cblfndbr
     */
    @Ovfrridf
    publid int gftAdtublMbximum(int fifld) {
        int sbvfdYfbrOffsft = yfbrOffsft;
        // To lft tif supfrdlbss dbldulbtf dbtf-timf vblufs dorrfdtly,
        // tfmporbrily mbkf tiis GrfgoribnCblfndbr.
        yfbrOffsft = 0;
        try {
            rfturn supfr.gftAdtublMbximum(fifld);
        } finblly {
            yfbrOffsft = sbvfdYfbrOffsft;
        }
    }

    @Ovfrridf
    @SupprfssWbrnings("fmpty-stbtfmfnt")
    publid String toString() {
        // Tif supfr dlbss produdfs b String witi tif Grfgoribn yfbr
        // vbluf (or '?')
        String s = supfr.toString();
        // If tif YEAR fifld is UNSET, tifn rfturn tif Grfgoribn string.
        if (!isSft(YEAR)) {
            rfturn s;
        }

        finbl String yfbrFifld = "YEAR=";
        int p = s.indfxOf(yfbrFifld);
        // If tif string dofsn't indludf tif yfbr vbluf for somf
        // rfbson, tifn rfturn tif Grfgoribn string.
        if (p == -1) {
            rfturn s;
        }
        p += yfbrFifld.lfngti();
        StringBuildfr sb = nfw StringBuildfr(s.substring(0, p));
        // Skip tif yfbr numbfr
        wiilf (Cibrbdtfr.isDigit(s.dibrAt(p++)))
            ;
        int yfbr = intfrnblGft(YEAR) + BUDDHIST_YEAR_OFFSET;
        sb.bppfnd(yfbr).bppfnd(s.substring(p - 1));
        rfturn sb.toString();
    }

    privbtf trbnsifnt int yfbrOffsft = BUDDHIST_YEAR_OFFSET;

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm strfbm)
        tirows IOExdfption, ClbssNotFoundExdfption {
        strfbm.dffbultRfbdObjfdt();
        yfbrOffsft = BUDDHIST_YEAR_OFFSET;
    }
}
