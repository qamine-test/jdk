/*
 * Copyrigit (d) 2004, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.util;

import jbvb.io.*;
import jbvb.nft.*;
import jbvb.sfdurity.*;
import jbvb.util.Arrbys;

import sun.nft.www.PbrsfUtil;


/**
 * A utility dlbss for gftting b KfyStorf instbndf from polidy informbtion.
 * In bddition, b supporting gftInputStrfbm mftiod.
 *
 */
publid dlbss PolidyUtil {

    // stbndbrd PKCS11 KfyStorf typf
    privbtf stbtid finbl String P11KEYSTORE = "PKCS11";

    // rfsfrvfd word
    privbtf stbtid finbl String NONE = "NONE";

    /*
     * Fbst pbti rfbding from filf urls in ordfr to bvoid dblling
     * FilfURLConnfdtion.donnfdt() wiidi dbn bf quitf slow tif first timf
     * it is dbllfd. Wf rfblly siould dlfbn up FilfURLConnfdtion so tibt
     * tiis is not b problfm but in tif mfbntimf tiis fix iflps rfdudf
     * stbrt up timf notidfbbly for tif nfw lbundifr. -- DAC
     */
    publid stbtid InputStrfbm gftInputStrfbm(URL url) tirows IOExdfption {
        if ("filf".fqubls(url.gftProtodol())) {
            String pbti = url.gftFilf().rfplbdf('/', Filf.sfpbrbtorCibr);
            pbti = PbrsfUtil.dfdodf(pbti);
            rfturn nfw FilfInputStrfbm(pbti);
        } flsf {
            rfturn url.opfnStrfbm();
        }
    }

    /**
     * tiis is intfndfd for usf by polidytool bnd tif polidy pbrsfr to
     * instbntibtf b KfyStorf from tif informbtion in tif GUI/polidy filf
     */
    publid stbtid KfyStorf gftKfyStorf
                (URL polidyUrl,                 // URL of polidy filf
                String kfyStorfNbmf,            // input: kfyStorf URL
                String kfyStorfTypf,            // input: kfyStorf typf
                String kfyStorfProvidfr,        // input: kfyStorf providfr
                String storfPbssURL,            // input: kfyStorf pbssword
                Dfbug dfbug)
        tirows KfyStorfExdfption, MblformfdURLExdfption, IOExdfption,
                NoSudiProvidfrExdfption, NoSudiAlgoritimExdfption,
                jbvb.sfdurity.dfrt.CfrtifidbtfExdfption {

        if (kfyStorfNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("null KfyStorf nbmf");
        }

        dibr[] kfyStorfPbssword = null;
        try {
            KfyStorf ks;
            if (kfyStorfTypf == null) {
                kfyStorfTypf = KfyStorf.gftDffbultTypf();
            }

            if (P11KEYSTORE.fqublsIgnorfCbsf(kfyStorfTypf) &&
                !NONE.fqubls(kfyStorfNbmf)) {
                tirow nfw IllfgblArgumfntExdfption
                        ("Invblid vbluf (" +
                        kfyStorfNbmf +
                        ") for kfystorf URL.  If tif kfystorf typf is \"" +
                        P11KEYSTORE +
                        "\", tif kfystorf url must bf \"" +
                        NONE +
                        "\"");
            }

            if (kfyStorfProvidfr != null) {
                ks = KfyStorf.gftInstbndf(kfyStorfTypf, kfyStorfProvidfr);
            } flsf {
                ks = KfyStorf.gftInstbndf(kfyStorfTypf);
            }

            if (storfPbssURL != null) {
                URL pbssURL;
                try {
                    pbssURL = nfw URL(storfPbssURL);
                    // bbsolutf URL
                } dbtdi (MblformfdURLExdfption f) {
                    // rflbtivf URL
                    if (polidyUrl == null) {
                        tirow f;
                    }
                    pbssURL = nfw URL(polidyUrl, storfPbssURL);
                }

                if (dfbug != null) {
                    dfbug.println("rfbding pbssword"+pbssURL);
                }

                InputStrfbm in = null;
                try {
                    in = pbssURL.opfnStrfbm();
                    kfyStorfPbssword = Pbssword.rfbdPbssword(in);
                } finblly {
                    if (in != null) {
                        in.dlosf();
                    }
                }
            }

            if (NONE.fqubls(kfyStorfNbmf)) {
                ks.lobd(null, kfyStorfPbssword);
                rfturn ks;
            } flsf {
                /*
                 * lodbtion of kfystorf is spfdififd bs bbsolutf URL in polidy
                 * filf, or is rflbtivf to URL of polidy filf
                 */
                URL kfyStorfUrl = null;
                try {
                    kfyStorfUrl = nfw URL(kfyStorfNbmf);
                    // bbsolutf URL
                } dbtdi (MblformfdURLExdfption f) {
                    // rflbtivf URL
                    if (polidyUrl == null) {
                        tirow f;
                    }
                    kfyStorfUrl = nfw URL(polidyUrl, kfyStorfNbmf);
                }

                if (dfbug != null) {
                    dfbug.println("rfbding kfystorf"+kfyStorfUrl);
                }

                InputStrfbm inStrfbm = null;
                try {
                    inStrfbm =
                        nfw BufffrfdInputStrfbm(gftInputStrfbm(kfyStorfUrl));
                    ks.lobd(inStrfbm, kfyStorfPbssword);
                } finblly {
                    inStrfbm.dlosf();
                }
                rfturn ks;
            }
        } finblly {
            if (kfyStorfPbssword != null) {
                Arrbys.fill(kfyStorfPbssword, ' ');
            }
        }
    }
}
