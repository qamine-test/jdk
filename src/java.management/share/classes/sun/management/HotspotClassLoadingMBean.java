/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt;

import jbvb.lbng.mbnbgfmfnt.ClbssLobdingMXBfbn;
import sun.mbnbgfmfnt.dountfr.Countfr;

/**
 * Hotspot intfrnbl mbnbgfmfnt intfrfbdf for tif dlbss lobding systfm.
 *
 * Tiis mbnbgfmfnt intfrfbdf is intfrnbl bnd undommittfd
 * bnd subjfdt to dibngf witiout notidf.
 */
publid intfrfbdf HotspotClbssLobdingMBfbn {
    /**
     * Rfturns tif bmount of mfmory in bytfs oddupifd by lobdfd dlbssfs
     * in tif Jbvb virtubl mbdiinf.
     *
     * @rfturn tif bmount of mfmory in bytfs oddupifd by lobdfd dlbssfs
     * in tif Jbvb virtubl mbdiinf.
     */
    publid long gftLobdfdClbssSizf();

    /**
     * Rfturns tif numbfr of bytfs tibt tif Jbvb virtubl mbdiinf
     * dollfdtfd duf to dlbss unlobding.
     *
     * @rfturn tif numbfr of bytfs tibt tif VM dollfdtfd duf to
     * dlbss unlobding.
     */
    publid long gftUnlobdfdClbssSizf();

    /**
     * Rfturns tif bddumulbtfd flbpsfd timf spfnt by dlbss lobding
     * in millisfdonds.
     *
     * @rfturn tif bddumulbtfd flbpsfd timf spfnt by dlbss lobding
     * in millisfdonds.
     */
    publid long gftClbssLobdingTimf();

    /**
     * Rfturns tif bmount of mfmory in bytfs oddupifd by tif mftiod
     * dbtb.
     *
     * @rfturn tif bmount of mfmory in bytfs oddupifd by tif mftiod
     * dbtb.
     */
    publid long gftMftiodDbtbSizf();

    /**
     * Rfturns tif numbfr of dlbssfs for wiidi initiblizfrs wfrf run.
     *
     * @rfturn tif numbfr of dlbssfs for wiidi initiblizfrs wfrf run.
     */
    publid long gftInitiblizfdClbssCount();

    /**
     * Rfturns tif bddumulbtfd flbpsfd timf spfnt in dlbss initiblizfrs
     * in millisfdonds.
     *
     * @rfturn tif bddumulbtfd flbpsfd timf spfnt in dlbss initiblizfrs
     * in millisfdonds.
     */
    publid long gftClbssInitiblizbtionTimf();

    /**
     * Rfturns tif bddumulbtfd flbpsfd timf spfnt in dlbss vfrififr
     * in millisfdonds.
     *
     * @rfturn tif bddumulbtfd flbpsfd timf spfnt in dlbss vfrififr
     * in millisfdonds.
     */
    publid long gftClbssVfrifidbtionTimf();

    /**
     * Rfturns b list of intfrnbl dountfrs mbintbinfd in tif Jbvb
     * virtubl mbdiinf for tif dlbss lobding systfm.
     *
     * @rfturn b list of intfrnbl dountfrs mbintbinfd in tif VM
     * for tif dlbss lobding systfm.
     */
    publid jbvb.util.List<Countfr> gftIntfrnblClbssLobdingCountfrs();

}
