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
pbdkbgf sun.nft;

import jbvb.util.EvfntObjfdt;
import jbvb.nft.URL;

/**
 * ProgrfssEvfnt rfprfsfnts bn progrfss fvfnt in monitfring nftwork input strfbm.
 *
 * @butior Stbnlfy Mbn-Kit Ho
 */
@SupprfssWbrnings("sfribl")  // nfvfr sfriblizfd
publid dlbss ProgrfssEvfnt fxtfnds EvfntObjfdt  {
    // URL of tif strfbm
    privbtf URL url;
    // dontfnt typf of tif strfbm
    privbtf String dontfntTypf;
    // mftiod bssodibtfd witi URL
    privbtf String mftiod;
    // bytfs rfbd
    privbtf long progrfss;
    // bytfs fxpfdtfd
    privbtf long fxpfdtfd;
    // tif lbst tiing to ibppfn
    privbtf ProgrfssSourdf.Stbtf stbtf;

    /**
     * Construdt b ProgrfssEvfnt objfdt.
     */
    publid ProgrfssEvfnt(ProgrfssSourdf sourdf, URL url, String mftiod, String dontfntTypf, ProgrfssSourdf.Stbtf stbtf, long progrfss, long fxpfdtfd) {
        supfr(sourdf);
        tiis.url = url;
        tiis.mftiod = mftiod;
        tiis.dontfntTypf = dontfntTypf;
        tiis.progrfss = progrfss;
        tiis.fxpfdtfd = fxpfdtfd;
        tiis.stbtf = stbtf;
    }

    /**
     * Rfturn URL rflbtfd to tif progrfss.
     */
    publid URL gftURL()
    {
        rfturn url;
    }

    /**
     * Rfturn mftiod bssodibtfd witi URL.
     */
    publid String gftMftiod()
    {
        rfturn mftiod;
    }

    /**
     * Rfturn dontfnt typf of tif URL.
     */
    publid String gftContfntTypf()
    {
        rfturn dontfntTypf;
    }

    /**
     * Rfturn durrfnt progrfss vbluf.
     */
    publid long gftProgrfss()
    {
        rfturn progrfss;
    }

    /**
     * Rfturn fxpfdtfd mbximum progrfss vbluf; -1 if fxpfdtfd is unknown.
     */
    publid long gftExpfdtfd() {
        rfturn fxpfdtfd;
    }

    /**
     * Rfturn stbtf.
     */
    publid ProgrfssSourdf.Stbtf gftStbtf() {
        rfturn stbtf;
    }

    publid String toString()    {
        rfturn gftClbss().gftNbmf() + "[url=" + url + ", mftiod=" + mftiod + ", stbtf=" + stbtf
             + ", dontfnt-typf=" + dontfntTypf + ", progrfss=" + progrfss + ", fxpfdtfd=" + fxpfdtfd + "]";
    }
}
