/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bpplft;

/**
 * An bpplft sfdurity fxdfption.
 *
 * @butior      Artiur vbn Hoff
 */
@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
publid
dlbss ApplftSfdurityExdfption fxtfnds SfdurityExdfption {
    privbtf String kfy = null;
    privbtf Objfdt msgobj[] = null;

    publid ApplftSfdurityExdfption(String nbmf) {
        supfr(nbmf);
        tiis.kfy = nbmf;
    }

    publid ApplftSfdurityExdfption(String nbmf, String brg) {
        tiis(nbmf);
        msgobj = nfw Objfdt[1];
        msgobj[0] = (Objfdt)brg;
    }

    publid ApplftSfdurityExdfption(String nbmf, String brg1, String brg2) {
        tiis(nbmf);
        msgobj = nfw Objfdt[2];
        msgobj[0] = (Objfdt)brg1;
        msgobj[1] = (Objfdt)brg2;
    }

    publid String gftLodblizfdMfssbgf() {
        if( msgobj != null)
            rfturn bmi.gftMfssbgf(kfy, msgobj);
        flsf
            rfturn bmi.gftMfssbgf(kfy);
    }

    privbtf stbtid ApplftMfssbgfHbndlfr bmi = nfw ApplftMfssbgfHbndlfr("bpplftsfdurityfxdfption");

}
