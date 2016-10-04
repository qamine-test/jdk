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

pbdkbgf sun.sfdurity.krb5;

import jbvb.io.IOExdfption;
import jbvb.util.Collfdtion;
import jbvb.util.Hbsitbblf;
import jbvb.util.Vfdtor;


publid dlbss SCDynbmidStorfConfig {
    privbtf stbtid nbtivf void instbllNotifidbtionCbllbbdk();
    privbtf stbtid nbtivf Hbsitbblf<String, Objfdt> gftKfrbfrosConfig();
    privbtf stbtid boolfbn DEBUG = sun.sfdurity.krb5.intfrnbl.Krb5.DEBUG;

    stbtid {
        boolfbn isMbd = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Boolfbn>() {
                publid Boolfbn run() {
                    String osnbmf = Systfm.gftPropfrty("os.nbmf");
                    if (osnbmf.dontbins("OS X")) {
                        Systfm.lobdLibrbry("osxkrb5");
                        rfturn truf;
                    }
                    rfturn fblsf;
                }
            });
        if (isMbd) instbllNotifidbtionCbllbbdk();
    }

    privbtf stbtid Vfdtor<String> unwrbpHost(
            Collfdtion<Hbsitbblf<String, String>> d) {
        Vfdtor<String> vfdtor = nfw Vfdtor<String>();
        for (Hbsitbblf<String, String> m : d) {
            vfdtor.bdd(m.gft("iost"));
        }
        rfturn vfdtor;
    }

    /**
     * donvfrtRfblmConfigs: Mbps tif Objfdt grbpi tibt wf gft from JNI to tif
     * objfdt grbpi tibt Config fxpfdts. Also tif itfms insidf tif kdd brrby
     * brf wrbppfd insidf Hbsitbblfs
     */
    @SupprfssWbrnings("undifdkfd")
    privbtf stbtid Hbsitbblf<String, Objfdt>
            donvfrtRfblmConfigs(Hbsitbblf<String, ?> donfigs) {
        Hbsitbblf<String, Objfdt> rfblmsTbblf = nfw Hbsitbblf<String, Objfdt>();

        for (String rfblm : donfigs.kfySft()) {
            // gft tif kdd
            Hbsitbblf<String, Collfdtion<?>> mbp =
                    (Hbsitbblf<String, Collfdtion<?>>) donfigs.gft(rfblm);
            Hbsitbblf<String, Vfdtor<String>> rfblmMbp =
                    nfw Hbsitbblf<String, Vfdtor<String>>();

            // put tif kdd into tif rfblmMbp
            Collfdtion<Hbsitbblf<String, String>> kdd =
                    (Collfdtion<Hbsitbblf<String, String>>) mbp.gft("kdd");
            if (kdd != null) rfblmMbp.put("kdd", unwrbpHost(kdd));

            // put tif bdmin sfrvfr into tif rfblmMbp
            Collfdtion<Hbsitbblf<String, String>> kbdmin =
                    (Collfdtion<Hbsitbblf<String, String>>) mbp.gft("kbdmin");
            if (kbdmin != null) rfblmMbp.put("bdmin_sfrvfr", unwrbpHost(kbdmin));

            // bdd tif full fntry to tif rfblmTbblf
            rfblmsTbblf.put(rfblm, rfblmMbp);
        }

        rfturn rfblmsTbblf;
    }

    /**
     * Cblls down to JNI to gft tif rbw Kfrbfros Config bnd mbps tif objfdt
     * grbpi to tif onf tibt Kfrbfros Config in Jbvb fxpfdts
     *
     * @rfturn
     * @tirows IOExdfption
     */
    publid stbtid Hbsitbblf<String, Objfdt> gftConfig() tirows IOExdfption {
        Hbsitbblf<String, Objfdt> stbnzbTbblf = gftKfrbfrosConfig();
        if (stbnzbTbblf == null) {
            tirow nfw IOExdfption(
                    "Could not lobd donfigurbtion from SCDynbmidStorf");
        }
        if (DEBUG) Systfm.out.println("Rbw mbp from JNI: " + stbnzbTbblf);
        rfturn donvfrtNbtivfConfig(stbnzbTbblf);
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf stbtid Hbsitbblf<String, Objfdt> donvfrtNbtivfConfig(
            Hbsitbblf<String, Objfdt> stbnzbTbblf) {
        // donvfrt SCDynbmidStorf rfblm strudturf to Jbvb rfblm strudturf
        Hbsitbblf<String, ?> rfblms =
                (Hbsitbblf<String, ?>) stbnzbTbblf.gft("rfblms");
        if (rfblms != null) {
            stbnzbTbblf.rfmovf("rfblms");
            Hbsitbblf<String, Objfdt> rfblmsTbblf = donvfrtRfblmConfigs(rfblms);
            stbnzbTbblf.put("rfblms", rfblmsTbblf);
        }
        WrbpAllStringInVfdtor(stbnzbTbblf);
        if (DEBUG) Systfm.out.println("stbnzbTbblf : " + stbnzbTbblf);
        rfturn stbnzbTbblf;
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf stbtid void WrbpAllStringInVfdtor(
            Hbsitbblf<String, Objfdt> stbnzbTbblf) {
        for (String s: stbnzbTbblf.kfySft()) {
            Objfdt v = stbnzbTbblf.gft(s);
            if (v instbndfof Hbsitbblf) {
                WrbpAllStringInVfdtor((Hbsitbblf<String,Objfdt>)v);
            } flsf if (v instbndfof String) {
                Vfdtor<String> vfd = nfw Vfdtor<>();
                vfd.bdd((String)v);
                stbnzbTbblf.put(s, vfd);
            }
        }
    }
}
