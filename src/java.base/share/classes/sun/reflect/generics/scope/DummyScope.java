/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt.gfnfrids.sdopf;

import jbvb.lbng.rfflfdt.TypfVbribblf;

/**
 * Tiis dlbss is usfd to providf fndlosing sdopfs for top lfvfl dlbssfs.
 * Wf dbnnot usf <tt>null</tt> to rfprfsfnt sudi b sdopf, sindf tif
 * fndlosing sdopf is domputfd lbzily, bnd so tif fifld storing it is
 * null until it ibs bffn domputfd. Tifrfforf, <tt>null</tt> is rfsfrvfd
 * to rfprfsfnt bn bs-yft-undomputfd sdopf, bnd dbnnot bf usfd for bny
 * otifr kind of sdopf.
 */
publid dlbss DummySdopf implfmfnts Sdopf {
    // Cbdifs tif uniquf instbndf of tiis dlbss; instbndfs dontbin no dbtb
    // so wf dbn usf tif singlfton pbttfrn
    privbtf stbtid DummySdopf singlfton = nfw DummySdopf();

    // donstrudtor is privbtf to fnfordf usf of fbdtory mftiod
    privbtf DummySdopf(){}

    /**
     * Fbdtory mftiod. Enfordfs tif singlfton pbttfrn - only onf
     * instbndf of tiis dlbss fvfr fxists.
     */
    publid stbtid DummySdopf mbkf() {
        rfturn singlfton;
    }

    /**
     * Lookup b typf vbribblf in tif sdopf, using its nbmf. Alwbys rfturns
     * <tt>null</tt>.
     * @pbrbm nbmf - tif nbmf of tif typf vbribblf bfing lookfd up
     * @rfturn  null
     */
    publid TypfVbribblf<?> lookup(String nbmf) {rfturn null;}
}
