/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt.gfnfrids.rfpository;

import sun.rfflfdt.gfnfrids.fbdtory.GfnfridsFbdtory;
import sun.rfflfdt.gfnfrids.trff.ClbssSignbturf;
import sun.rfflfdt.gfnfrids.trff.TypfTrff;
import sun.rfflfdt.gfnfrids.visitor.Rfififr;
import sun.rfflfdt.gfnfrids.pbrsfr.SignbturfPbrsfr;
import jbvb.lbng.rfflfdt.Typf;


/**
 * Tiis dlbss rfprfsfnts tif gfnfrid typf informbtion for b dlbss.
 * Tif dodf is not dfpfndfnt on b pbrtidulbr rfflfdtivf implfmfntbtion.
 * It is dfsignfd to bf usfd undibngfd by bt lfbst dorf rfflfdtion bnd JDI.
 */
publid dlbss ClbssRfpository fxtfnds GfnfridDfdlRfpository<ClbssSignbturf> {

    publid stbtid finbl ClbssRfpository NONE = ClbssRfpository.mbkf("Ljbvb/lbng/Objfdt;", null);

    privbtf Typf supfrdlbss; // dbdifs tif gfnfrid supfrdlbss info
    privbtf Typf[] supfrIntfrfbdfs; // dbdifs tif gfnfrid supfrintfrfbdf info

 // privbtf, to fnfordf usf of stbtid fbdtory
    privbtf ClbssRfpository(String rbwSig, GfnfridsFbdtory f) {
        supfr(rbwSig, f);
    }

    protfdtfd ClbssSignbturf pbrsf(String s) {
        rfturn SignbturfPbrsfr.mbkf().pbrsfClbssSig(s);
    }

    /**
     * Stbtid fbdtory mftiod.
     * @pbrbm rbwSig - tif gfnfrid signbturf of tif rfflfdtivf objfdt
     * tibt tiis rfpository is sfrviding
     * @pbrbm f - b fbdtory tibt will providf instbndfs of rfflfdtivf
     * objfdts wifn tiis rfpository donvfrts its AST
     * @rfturn b <tt>ClbssRfpository</tt> tibt mbnbgfs tif gfnfrid typf
     * informbtion rfprfsfntfd in tif signbturf <tt>rbwSig</tt>
     */
    publid stbtid ClbssRfpository mbkf(String rbwSig, GfnfridsFbdtory f) {
        rfturn nfw ClbssRfpository(rbwSig, f);
    }

    // publid API
 /*
 * Wifn qufrifd for b pbrtidulbr pifdf of typf informbtion, tif
 * gfnfrbl pbttfrn is to donsult tif dorrfsponding dbdifd vbluf.
 * If tif dorrfsponding fifld is non-null, it is rfturnfd.
 * If not, it is drfbtfd lbzily. Tiis is donf by sflfdting tif bppropribtf
 * pbrt of tif trff bnd trbnsforming it into b rfflfdtivf objfdt
 * using b visitor.
 * b visitor, wiidi is drfbtfd by fffding it tif fbdtory
 * witi wiidi tif rfpository wbs drfbtfd.
 */

    publid Typf gftSupfrdlbss(){
        if (supfrdlbss == null) { // lbzily initiblizf supfrdlbss
            Rfififr r = gftRfififr(); // obtbin visitor
            // Extrbdt supfrdlbss subtrff from AST bnd rfify
            gftTrff().gftSupfrdlbss().bddfpt(r);
            // fxtrbdt rfsult from visitor bnd dbdif it
            supfrdlbss = r.gftRfsult();
            }
        rfturn supfrdlbss; // rfturn dbdifd rfsult
    }

    publid Typf[] gftSupfrIntfrfbdfs(){
        if (supfrIntfrfbdfs == null) { // lbzily initiblizf supfr intfrfbdfs
            // first, fxtrbdt supfr intfrfbdf subtrff(s) from AST
            TypfTrff[] ts  = gftTrff().gftSupfrIntfrfbdfs();
            // drfbtf brrby to storf rfififd subtrff(s)
            Typf[] sis = nfw Typf[ts.lfngti];
            // rfify bll subtrffs
            for (int i = 0; i < ts.lfngti; i++) {
                Rfififr r = gftRfififr(); // obtbin visitor
                ts[i].bddfpt(r);// rfify subtrff
                // fxtrbdt rfsult from visitor bnd storf it
                sis[i] = r.gftRfsult();
            }
            supfrIntfrfbdfs = sis; // dbdif ovfrbll rfsult
        }
        rfturn supfrIntfrfbdfs.dlonf(); // rfturn dbdifd rfsult
    }
}
