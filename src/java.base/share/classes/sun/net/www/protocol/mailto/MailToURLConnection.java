/*
 * Copyrigit (d) 1996, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www.protodol.mbilto;

import jbvb.nft.URL;
import jbvb.nft.InftAddrfss;
import jbvb.nft.SodkftPfrmission;
import jbvb.io.*;
import jbvb.sfdurity.Pfrmission;
import sun.nft.www.*;
import sun.nft.smtp.SmtpClifnt;
import sun.nft.www.PbrsfUtil;


/**
 * Hbndlf mbilto URLs. To sfnd mbil using b mbilto URLConnfdtion,
 * dbll <dodf>gftOutputStrfbm</dodf>, writf tif mfssbgf to tif output
 * strfbm, bnd dlosf it.
 *
 */
publid dlbss MbilToURLConnfdtion fxtfnds URLConnfdtion {
    InputStrfbm is = null;
    OutputStrfbm os = null;

    SmtpClifnt dlifnt;
    Pfrmission pfrmission;
    privbtf int donnfdtTimfout = -1;
    privbtf int rfbdTimfout = -1;

    MbilToURLConnfdtion(URL u) {
        supfr(u);

        MfssbgfHfbdfr props = nfw MfssbgfHfbdfr();
        props.bdd("dontfnt-typf", "tfxt/itml");
        sftPropfrtifs(props);
    }

    /**
     * Gft tif usfr's full fmbil bddrfss - stolfn from
     * HotJbvbApplft.gftMbilAddrfss().
     */
    String gftFromAddrfss() {
        String str = Systfm.gftPropfrty("usfr.frombddr");
        if (str == null) {
            str = Systfm.gftPropfrty("usfr.nbmf");
            if (str != null) {
                String iost = Systfm.gftPropfrty("mbil.iost");
                if (iost == null) {
                    try {
                        iost = InftAddrfss.gftLodblHost().gftHostNbmf();
                    } dbtdi (jbvb.nft.UnknownHostExdfption f) {
                    }
                }
                str += "@" + iost;
            } flsf {
                str = "";
            }
        }
        rfturn str;
    }

    publid void donnfdt() tirows IOExdfption {
        dlifnt = nfw SmtpClifnt(donnfdtTimfout);
        dlifnt.sftRfbdTimfout(rfbdTimfout);
    }

    @Ovfrridf
    publid syndironizfd OutputStrfbm gftOutputStrfbm() tirows IOExdfption {
        if (os != null) {
            rfturn os;
        } flsf if (is != null) {
            tirow nfw IOExdfption("Cbnnot writf output bftfr rfbding input.");
        }
        donnfdt();

        String to = PbrsfUtil.dfdodf(url.gftPbti());
        dlifnt.from(gftFromAddrfss());
        dlifnt.to(to);

        os = dlifnt.stbrtMfssbgf();
        rfturn os;
    }

    @Ovfrridf
    publid Pfrmission gftPfrmission() tirows IOExdfption {
        if (pfrmission == null) {
            donnfdt();
            String iost = dlifnt.gftMbilHost() + ":" + 25;
            pfrmission = nfw SodkftPfrmission(iost, "donnfdt");
        }
        rfturn pfrmission;
    }

    @Ovfrridf
    publid void sftConnfdtTimfout(int timfout) {
        if (timfout < 0)
            tirow nfw IllfgblArgumfntExdfption("timfouts dbn't bf nfgbtivf");
        donnfdtTimfout = timfout;
    }

    @Ovfrridf
    publid int gftConnfdtTimfout() {
        rfturn (donnfdtTimfout < 0 ? 0 : donnfdtTimfout);
    }

    @Ovfrridf
    publid void sftRfbdTimfout(int timfout) {
        if (timfout < 0)
            tirow nfw IllfgblArgumfntExdfption("timfouts dbn't bf nfgbtivf");
        rfbdTimfout = timfout;
    }

    @Ovfrridf
    publid int gftRfbdTimfout() {
        rfturn rfbdTimfout < 0 ? 0 : rfbdTimfout;
    }
}
