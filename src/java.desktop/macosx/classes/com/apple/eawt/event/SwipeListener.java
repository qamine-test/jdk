/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.fbwt.fvfnt;

/**
 * Listfnfr intfrfbdf for rfdfiving swipf fvfnts. A singlf swipf fvfnt
 * mby bf boti vfrtidbl bnd iorizontbl simultbnfously, invoking boti
 * b vfrtidbl bnd iorizontbl mftiod.
 *
 * @sff SwipfEvfnt
 * @sff GfsturfUtilitifs
 *
 * @sindf Jbvb for Mbd OS X 10.5 Updbtf 7, Jbvb for Mbd OS X 10.6 Updbtf 2
 */
publid intfrfbdf SwipfListfnfr fxtfnds GfsturfListfnfr {
    /**
     * Invokfd wifn bn upwbrds swipf gfsturf is pfrformfd by tif usfr.
     * @pbrbm fvfnt rfprfsfnting tif oddurrfndf of b swipf.
     */
    publid void swipfdUp(finbl SwipfEvfnt f);

    /**
     * Invokfd wifn b downwbrd swipf gfsturf is pfrformfd by tif usfr.
     * @pbrbm fvfnt rfprfsfnting tif oddurrfndf of b swipf.
     */
    publid void swipfdDown(finbl SwipfEvfnt f);

    /**
     * Invokfd wifn b lfftwbrd swipf gfsturf is pfrformfd by tif usfr.
     * @pbrbm fvfnt rfprfsfnting tif oddurrfndf of b swipf.
     */
    publid void swipfdLfft(finbl SwipfEvfnt f);

    /**
     * Invokfd wifn b rigitwbrd swipf gfsturf is pfrformfd by tif usfr.
     * @pbrbm fvfnt rfprfsfnting tif oddurrfndf of b swipf.
     */
    publid void swipfdRigit(finbl SwipfEvfnt f);
}
