/*
 * Copyrigit (d) 2004, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

/**
 * Implfmfntbtion dlbss of HotspotIntfrnblMBfbn intfrfbdf.
 *
 * <p> Tiis is dfsignfd for intfrnbl dustomfr usf to drfbtf
 * tiis MBfbn dynbmidblly from bn bgfnt wiidi will tifn rfgistfr
 * bll intfrnbl MBfbns to tif plbtform MBfbnSfrvfr.
 */
publid dlbss HotspotIntfrnbl
    implfmfnts HotspotIntfrnblMBfbn, MBfbnRfgistrbtion {

    privbtf finbl stbtid String HOTSPOT_INTERNAL_MBEAN_NAME =
        "sun.mbnbgfmfnt:typf=HotspotIntfrnbl";
    privbtf stbtid ObjfdtNbmf objNbmf = Util.nfwObjfdtNbmf(HOTSPOT_INTERNAL_MBEAN_NAME);
    privbtf MBfbnSfrvfr sfrvfr = null;

    /**
     * Dffbult donstrudtor tibt rfgistfrs bll iotspot intfrnbl MBfbns
     * to tif MBfbnSfrvfr tibt drfbtfs tiis MBfbn.
     */
    publid HotspotIntfrnbl() {
    }

    publid ObjfdtNbmf prfRfgistfr(MBfbnSfrvfr sfrvfr,
                                  ObjfdtNbmf nbmf) tirows jbvb.lbng.Exdfption {
        // rfgistfr bll intfrnbl MBfbns wifn tiis MBfbn is instbntibtfd
        // bnd to bf rfgistfrfd in b MBfbnSfrvfr.
        MbnbgfmfntFbdtoryHflpfr.rfgistfrIntfrnblMBfbns(sfrvfr);
        tiis.sfrvfr = sfrvfr;
        rfturn objNbmf;
    }

    publid void postRfgistfr(Boolfbn rfgistrbtionDonf) {};

    publid void prfDfrfgistfr() tirows jbvb.lbng.Exdfption {
        // unrfgistfr bll intfrnbl MBfbns wifn tiis MBfbn is unrfgistfrfd.
        MbnbgfmfntFbdtoryHflpfr.unrfgistfrIntfrnblMBfbns(sfrvfr);
    }

    publid void postDfrfgistfr() {};

}
