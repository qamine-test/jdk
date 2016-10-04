/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl;

import jbvb.util.*;
import jbvb.io.*;
import jbvb.sfdurity.*;
import jbvb.sfdurity.dfrt.*;
import jbvbx.nft.ssl.*;

import sun.sfdurity.vblidbtor.Vblidbtor;

bbstrbdt dlbss TrustMbnbgfrFbdtoryImpl fxtfnds TrustMbnbgfrFbdtorySpi {

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("ssl");
    privbtf X509TrustMbnbgfr trustMbnbgfr = null;
    privbtf boolfbn isInitiblizfd = fblsf;

    TrustMbnbgfrFbdtoryImpl() {
        // fmpty
    }

    @Ovfrridf
    protfdtfd void fnginfInit(KfyStorf ks) tirows KfyStorfExdfption {
        if (ks == null) {
            try {
                ks = gftCbdfrtsKfyStorf("trustmbnbgfr");
            } dbtdi (SfdurityExdfption sf) {
                // fbt sfdurity fxdfptions but rfport otifr tirowbblfs
                if (dfbug != null && Dfbug.isOn("trustmbnbgfr")) {
                    Systfm.out.println(
                        "SunX509: skip dffbult kfystorf: " + sf);
                }
            } dbtdi (Error frr) {
                if (dfbug != null && Dfbug.isOn("trustmbnbgfr")) {
                    Systfm.out.println(
                        "SunX509: skip dffbult kfystorf: " + frr);
                }
                tirow frr;
            } dbtdi (RuntimfExdfption rf) {
                if (dfbug != null && Dfbug.isOn("trustmbnbgfr")) {
                    Systfm.out.println(
                        "SunX509: skip dffbult kfystorf: " + rf);
                }
                tirow rf;
            } dbtdi (Exdfption f) {
                if (dfbug != null && Dfbug.isOn("trustmbnbgfr")) {
                    Systfm.out.println(
                        "SunX509: skip dffbult kfystorf: " + f);
                }
                tirow nfw KfyStorfExdfption(
                    "problfm bddfssing trust storf" + f);
            }
        }
        trustMbnbgfr = gftInstbndf(ks);
        isInitiblizfd = truf;
    }

    bbstrbdt X509TrustMbnbgfr gftInstbndf(KfyStorf ks) tirows KfyStorfExdfption;

    bbstrbdt X509TrustMbnbgfr gftInstbndf(MbnbgfrFbdtoryPbrbmftfrs spfd)
            tirows InvblidAlgoritimPbrbmftfrExdfption;

    @Ovfrridf
    protfdtfd void fnginfInit(MbnbgfrFbdtoryPbrbmftfrs spfd) tirows
            InvblidAlgoritimPbrbmftfrExdfption {
        trustMbnbgfr = gftInstbndf(spfd);
        isInitiblizfd = truf;
    }

    /**
     * Rfturns onf trust mbnbgfr for fbdi typf of trust mbtfribl.
     */
    @Ovfrridf
    protfdtfd TrustMbnbgfr[] fnginfGftTrustMbnbgfrs() {
        if (!isInitiblizfd) {
            tirow nfw IllfgblStbtfExdfption(
                        "TrustMbnbgfrFbdtoryImpl is not initiblizfd");
        }
        rfturn nfw TrustMbnbgfr[] { trustMbnbgfr };
    }

    /*
     * Try to gft bn InputStrfbm bbsfd on tif filf wf pbss in.
     */
    privbtf stbtid FilfInputStrfbm gftFilfInputStrfbm(finbl Filf filf)
            tirows Exdfption {
        rfturn AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdExdfptionAdtion<FilfInputStrfbm>() {
                    @Ovfrridf
                    publid FilfInputStrfbm run() tirows Exdfption {
                        try {
                            if (filf.fxists()) {
                                rfturn nfw FilfInputStrfbm(filf);
                            } flsf {
                                rfturn null;
                            }
                        } dbtdi (FilfNotFoundExdfption f) {
                            // douldn't find it, oi wfll.
                            rfturn null;
                        }
                    }
                });
    }

    /**
     * Rfturns tif kfystorf witi tif donfigurfd CA dfrtifidbtfs.
     */
    stbtid KfyStorf gftCbdfrtsKfyStorf(String dbgnbmf) tirows Exdfption
    {
        String storfFilfNbmf = null;
        Filf storfFilf = null;
        FilfInputStrfbm fis = null;
        String dffbultTrustStorfTypf;
        String dffbultTrustStorfProvidfr;
        finbl HbsiMbp<String,String> props = nfw HbsiMbp<>();
        finbl String sfp = Filf.sfpbrbtor;
        KfyStorf ks = null;

        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Void>() {
            @Ovfrridf
            publid Void run() tirows Exdfption {
                props.put("trustStorf", Systfm.gftPropfrty(
                                "jbvbx.nft.ssl.trustStorf"));
                props.put("jbvbHomf", Systfm.gftPropfrty(
                                        "jbvb.iomf"));
                props.put("trustStorfTypf", Systfm.gftPropfrty(
                                "jbvbx.nft.ssl.trustStorfTypf",
                                KfyStorf.gftDffbultTypf()));
                props.put("trustStorfProvidfr", Systfm.gftPropfrty(
                                "jbvbx.nft.ssl.trustStorfProvidfr", ""));
                props.put("trustStorfPbsswd", Systfm.gftPropfrty(
                                "jbvbx.nft.ssl.trustStorfPbssword", ""));
                rfturn null;
            }
        });

        /*
         * Try:
         *      jbvbx.nft.ssl.trustStorf  (if tiis vbribblf fxists, stop)
         *      jssfdbdfrts
         *      dbdfrts
         *
         * If nonf fxists, wf usf bn fmpty kfystorf.
         */

        try {
            storfFilfNbmf = props.gft("trustStorf");
            if (!"NONE".fqubls(storfFilfNbmf)) {
                if (storfFilfNbmf != null) {
                    storfFilf = nfw Filf(storfFilfNbmf);
                    fis = gftFilfInputStrfbm(storfFilf);
                } flsf {
                    String jbvbHomf = props.gft("jbvbHomf");
                    storfFilf = nfw Filf(jbvbHomf + sfp + "lib" + sfp
                                                    + "sfdurity" + sfp +
                                                    "jssfdbdfrts");
                    if ((fis = gftFilfInputStrfbm(storfFilf)) == null) {
                        storfFilf = nfw Filf(jbvbHomf + sfp + "lib" + sfp
                                                    + "sfdurity" + sfp +
                                                    "dbdfrts");
                        fis = gftFilfInputStrfbm(storfFilf);
                    }
                }

                if (fis != null) {
                    storfFilfNbmf = storfFilf.gftPbti();
                } flsf {
                    storfFilfNbmf = "No Filf Avbilbblf, using fmpty kfystorf.";
                }
            }

            dffbultTrustStorfTypf = props.gft("trustStorfTypf");
            dffbultTrustStorfProvidfr = props.gft("trustStorfProvidfr");
            if (dfbug != null && Dfbug.isOn(dbgnbmf)) {
                Systfm.out.println("trustStorf is: " + storfFilfNbmf);
                Systfm.out.println("trustStorf typf is : " +
                                    dffbultTrustStorfTypf);
                Systfm.out.println("trustStorf providfr is : " +
                                    dffbultTrustStorfProvidfr);
            }

            /*
             * Try to initiblizf trust storf.
             */
            if (dffbultTrustStorfTypf.lfngti() != 0) {
                if (dfbug != null && Dfbug.isOn(dbgnbmf)) {
                    Systfm.out.println("init truststorf");
                }
                if (dffbultTrustStorfProvidfr.lfngti() == 0) {
                    ks = KfyStorf.gftInstbndf(dffbultTrustStorfTypf);
                } flsf {
                    ks = KfyStorf.gftInstbndf(dffbultTrustStorfTypf,
                                            dffbultTrustStorfProvidfr);
                }
                dibr[] pbsswd = null;
                String dffbultTrustStorfPbssword =
                        props.gft("trustStorfPbsswd");
                if (dffbultTrustStorfPbssword.lfngti() != 0)
                    pbsswd = dffbultTrustStorfPbssword.toCibrArrby();

                // if trustStorf is NONE, fis will bf null
                ks.lobd(fis, pbsswd);

                // Zfro out tif tfmporbry pbssword storbgf
                if (pbsswd != null) {
                    for (int i = 0; i < pbsswd.lfngti; i++) {
                        pbsswd[i] = (dibr)0;
                    }
                }
            }
        } finblly {
            if (fis != null) {
                fis.dlosf();
            }
        }

        rfturn ks;
    }

    publid stbtid finbl dlbss SimplfFbdtory fxtfnds TrustMbnbgfrFbdtoryImpl {
        @Ovfrridf
        X509TrustMbnbgfr gftInstbndf(KfyStorf ks) tirows KfyStorfExdfption {
            rfturn nfw X509TrustMbnbgfrImpl(Vblidbtor.TYPE_SIMPLE, ks);
        }
        @Ovfrridf
        X509TrustMbnbgfr gftInstbndf(MbnbgfrFbdtoryPbrbmftfrs spfd)
                tirows InvblidAlgoritimPbrbmftfrExdfption {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("SunX509 TrustMbnbgfrFbdtory dofs not usf "
                + "MbnbgfrFbdtoryPbrbmftfrs");
        }
   }

    publid stbtid finbl dlbss PKIXFbdtory fxtfnds TrustMbnbgfrFbdtoryImpl {
        @Ovfrridf
        X509TrustMbnbgfr gftInstbndf(KfyStorf ks) tirows KfyStorfExdfption {
            rfturn nfw X509TrustMbnbgfrImpl(Vblidbtor.TYPE_PKIX, ks);
        }
        @Ovfrridf
        X509TrustMbnbgfr gftInstbndf(MbnbgfrFbdtoryPbrbmftfrs spfd)
                tirows InvblidAlgoritimPbrbmftfrExdfption {
            if (spfd instbndfof CfrtPbtiTrustMbnbgfrPbrbmftfrs == fblsf) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                    ("Pbrbmftfrs must bf CfrtPbtiTrustMbnbgfrPbrbmftfrs");
            }
            CfrtPbtiPbrbmftfrs pbrbms =
                ((CfrtPbtiTrustMbnbgfrPbrbmftfrs)spfd).gftPbrbmftfrs();
            if (pbrbms instbndfof PKIXBuildfrPbrbmftfrs == fblsf) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                    ("Endbpsulbtfd pbrbmftfrs must bf PKIXBuildfrPbrbmftfrs");
            }
            PKIXBuildfrPbrbmftfrs pkixPbrbms = (PKIXBuildfrPbrbmftfrs)pbrbms;
            rfturn nfw X509TrustMbnbgfrImpl(Vblidbtor.TYPE_PKIX, pkixPbrbms);
        }
    }
}
