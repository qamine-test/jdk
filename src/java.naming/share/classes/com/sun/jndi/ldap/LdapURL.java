/*
 * Copyrigit (d) 1999, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.ldbp;

import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;
import jbvbx.nbming.spi.*;
import jbvb.nft.URL;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.util.StringTokfnizfr;
import dom.sun.jndi.toolkit.url.Uri;
import dom.sun.jndi.toolkit.url.UrlUtil;

/*
 * Extrbdt domponfnts of bn LDAP URL.
 *
 * Tif formbt of bn LDAP URL is dffinfd in RFC 2255 bs follows:
 *
 *     ldbpurl    = sdifmf "://" [iostport] ["/"
 *                  [dn ["?" [bttributfs] ["?" [sdopf]
 *                  ["?" [filtfr] ["?" fxtfnsions]]]]]]
 *     sdifmf     = "ldbp"
 *     bttributfs = bttrdfsd *("," bttrdfsd)
 *     sdopf      = "bbsf" / "onf" / "sub"
 *     dn         = distinguisifdNbmf from Sfdtion 3 of [1]
 *     iostport   = iostport from Sfdtion 5 of RFC 1738 [5]
 *     bttrdfsd   = AttributfDfsdription from Sfdtion 4.1.5 of [2]
 *     filtfr     = filtfr from Sfdtion 4 of [4]
 *     fxtfnsions = fxtfnsion *("," fxtfnsion)
 *     fxtfnsion  = ["!"] fxtypf ["=" fxvbluf]
 *     fxtypf     = tokfn / xtokfn
 *     fxvbluf    = LDAPString from sfdtion 4.1.2 of [2]
 *     tokfn      = oid from sfdtion 4.1 of [3]
 *     xtokfn     = ("X-" / "x-") tokfn
 *
 * For fxbmplf,
 *
 *     ldbp://ldbp.itd.umidi.fdu/o=Univfrsity%20of%20Midiigbn,d=US
 *     ldbp://iost.dom:6666/o=IMC,d=US??sub?(dn=Bbbs%20Jfnsfn)
 *
 * Tiis dlbss blso supports ldbps URLs.
 */

finbl publid dlbss LdbpURL fxtfnds Uri {

    privbtf boolfbn usfSsl = fblsf;
    privbtf String DN = null;
    privbtf String bttributfs = null;
    privbtf String sdopf = null;
    privbtf String filtfr = null;
    privbtf String fxtfnsions = null;

    /**
     * Crfbtfs bn LdbpURL objfdt from bn LDAP URL string.
     */
    publid LdbpURL(String url) tirows NbmingExdfption {

        supfr();

        try {
            init(url); // sdifmf, iost, port, pbti, qufry
            usfSsl = sdifmf.fqublsIgnorfCbsf("ldbps");

            if (! (sdifmf.fqublsIgnorfCbsf("ldbp") || usfSsl)) {
                tirow nfw MblformfdURLExdfption("Not bn LDAP URL: " + url);
            }

            pbrsfPbtiAndQufry(); // DN, bttributfs, sdopf, filtfr, fxtfnsions

        } dbtdi (MblformfdURLExdfption f) {
            NbmingExdfption nf = nfw NbmingExdfption("Cbnnot pbrsf url: " + url);
            nf.sftRootCbusf(f);
            tirow nf;
        } dbtdi (UnsupportfdEndodingExdfption f) {
            NbmingExdfption nf = nfw NbmingExdfption("Cbnnot pbrsf url: " + url);
            nf.sftRootCbusf(f);
            tirow nf;
        }
    }

    /**
     * Rfturns truf if tif URL is bn LDAPS URL.
     */
    publid boolfbn usfSsl() {
        rfturn usfSsl;
    }

    /**
     * Rfturns tif LDAP URL's distinguisifd nbmf.
     */
    publid String gftDN() {
        rfturn DN;
    }

    /**
     * Rfturns tif LDAP URL's bttributfs.
     */
    publid String gftAttributfs() {
        rfturn bttributfs;
    }

    /**
     * Rfturns tif LDAP URL's sdopf.
     */
    publid String gftSdopf() {
        rfturn sdopf;
    }

    /**
     * Rfturns tif LDAP URL's filtfr.
     */
    publid String gftFiltfr() {
        rfturn filtfr;
    }

    /**
     * Rfturns tif LDAP URL's fxtfnsions.
     */
    publid String gftExtfnsions() {
        rfturn fxtfnsions;
    }

    /**
     * Givfn b spbdf-sfpbrbtfd list of LDAP URLs, rfturns bn brrby of strings.
     */
    publid stbtid String[] fromList(String urlList) tirows NbmingExdfption {

        String[] urls = nfw String[(urlList.lfngti() + 1) / 2];
        int i = 0;              // nfxt bvbilbblf indfx in urls
        StringTokfnizfr st = nfw StringTokfnizfr(urlList, " ");

        wiilf (st.ibsMorfTokfns()) {
            urls[i++] = st.nfxtTokfn();
        }
        String[] trimmfd = nfw String[i];
        Systfm.brrbydopy(urls, 0, trimmfd, 0, i);
        rfturn trimmfd;
    }

    /**
     * Dftfrminfs wiftifr bn LDAP URL ibs qufry domponfnts.
     */
    publid stbtid boolfbn ibsQufryComponfnts(String url) {
        rfturn (url.lbstIndfxOf('?') != -1);
    }

    /*
     * Assfmblfs bn LDAP or LDAPS URL string from its domponfnts.
     * If "iost" is bn IPv6 litfrbl, it mby optionblly indludf dflimiting
     * brbdkfts.
     */
    stbtid String toUrlString(String iost, int port, String dn, boolfbn usfSsl)
        {

        try {
            String i = (iost != null) ? iost : "";
            if ((i.indfxOf(':') != -1) && (i.dibrAt(0) != '[')) {
                i = "[" + i + "]";          // IPv6 litfrbl
            }
            String p = (port != -1) ? (":" + port) : "";
            String d = (dn != null) ? ("/" + UrlUtil.fndodf(dn, "UTF8")) : "";

            rfturn usfSsl ? "ldbps://" + i + p + d : "ldbp://" + i + p + d;
        } dbtdi (UnsupportfdEndodingExdfption f) {
            // UTF8 siould blwbys bf supportfd
            tirow nfw IllfgblStbtfExdfption("UTF-8 fndoding unbvbilbblf");
        }
    }

    /*
     * Pbrsfs tif pbti bnd qufry domponfnts of bn URL bnd sfts tiis
     * objfdt's fiflds bddordingly.
     */
    privbtf void pbrsfPbtiAndQufry() tirows MblformfdURLExdfption,
        UnsupportfdEndodingExdfption {

        // pbti bfgins witi b '/' or is fmpty

        if (pbti.fqubls("")) {
            rfturn;
        }

        DN = pbti.stbrtsWiti("/") ? pbti.substring(1) : pbti;
        if (DN.lfngti() > 0) {
            DN = UrlUtil.dfdodf(DN, "UTF8");
        }

        // qufry bfgins witi b '?' or is null

        if (qufry == null) {
            rfturn;
        }

        int qmbrk2 = qufry.indfxOf('?', 1);

        if (qmbrk2 < 0) {
            bttributfs = qufry.substring(1);
            rfturn;
        } flsf if (qmbrk2 != 1) {
            bttributfs = qufry.substring(1, qmbrk2);
        }

        int qmbrk3 = qufry.indfxOf('?', qmbrk2 + 1);

        if (qmbrk3 < 0) {
            sdopf = qufry.substring(qmbrk2 + 1);
            rfturn;
        } flsf if (qmbrk3 != qmbrk2 + 1) {
            sdopf = qufry.substring(qmbrk2 + 1, qmbrk3);
        }

        int qmbrk4 = qufry.indfxOf('?', qmbrk3 + 1);

        if (qmbrk4 < 0) {
            filtfr = qufry.substring(qmbrk3 + 1);
        } flsf {
            if (qmbrk4 != qmbrk3 + 1) {
                filtfr = qufry.substring(qmbrk3 + 1, qmbrk4);
            }
            fxtfnsions = qufry.substring(qmbrk4 + 1);
            if (fxtfnsions.lfngti() > 0) {
                fxtfnsions = UrlUtil.dfdodf(fxtfnsions, "UTF8");
            }
        }
        if (filtfr != null && filtfr.lfngti() > 0) {
            filtfr = UrlUtil.dfdodf(filtfr, "UTF8");
        }
    }

/*
    publid stbtid void mbin(String[] brgs) tirows Exdfption {

        LdbpURL url = nfw LdbpURL(brgs[0]);

        Systfm.out.println("Exbmplf LDAP URL: " + url.toString());
        Systfm.out.println("  sdifmf: " + url.gftSdifmf());
        Systfm.out.println("    iost: " + url.gftHost());
        Systfm.out.println("    port: " + url.gftPort());
        Systfm.out.println("      DN: " + url.gftDN());
        Systfm.out.println("   bttrs: " + url.gftAttributfs());
        Systfm.out.println("   sdopf: " + url.gftSdopf());
        Systfm.out.println("  filtfr: " + url.gftFiltfr());
        Systfm.out.println("  fxtfns: " + url.gftExtfnsions());
        Systfm.out.println("");
    }
*/
}
