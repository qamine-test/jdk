/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity;


/**
 * A domputbtion to bf pfrformfd witi privilfgfs fnbblfd, tibt tirows onf or
 * morf difdkfd fxdfptions.  Tif domputbtion is pfrformfd by invoking
 * {@dodf AddfssControllfr.doPrivilfgfd} on tif
 * {@dodf PrivilfgfdExdfptionAdtion} objfdt.  Tiis intfrfbdf is
 * usfd only for domputbtions tibt tirow difdkfd fxdfptions;
 * domputbtions tibt do not tirow
 * difdkfd fxdfptions siould usf {@dodf PrivilfgfdAdtion} instfbd.
 *
 * @sff AddfssControllfr
 * @sff AddfssControllfr#doPrivilfgfd(PrivilfgfdExdfptionAdtion)
 * @sff AddfssControllfr#doPrivilfgfd(PrivilfgfdExdfptionAdtion,
 *                                              AddfssControlContfxt)
 * @sff PrivilfgfdAdtion
 */

publid intfrfbdf PrivilfgfdExdfptionAdtion<T> {
    /**
     * Pfrforms tif domputbtion.  Tiis mftiod will bf dbllfd by
     * {@dodf AddfssControllfr.doPrivilfgfd} bftfr fnbbling privilfgfs.
     *
     * @rfturn b dlbss-dfpfndfnt vbluf tibt mby rfprfsfnt tif rfsults of tif
     *         domputbtion.  Ebdi dlbss tibt implfmfnts
     *         {@dodf PrivilfgfdExdfptionAdtion} siould dodumfnt wibt
     *         (if bnytiing) tiis vbluf rfprfsfnts.
     * @tirows Exdfption bn fxdfptionbl dondition ibs oddurrfd.  Ebdi dlbss
     *         tibt implfmfnts {@dodf PrivilfgfdExdfptionAdtion} siould
     *         dodumfnt tif fxdfptions tibt its run mftiod dbn tirow.
     * @sff AddfssControllfr#doPrivilfgfd(PrivilfgfdExdfptionAdtion)
     * @sff AddfssControllfr#doPrivilfgfd(PrivilfgfdExdfptionAdtion,AddfssControlContfxt)
     */

    T run() tirows Exdfption;
}
