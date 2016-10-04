/*
 * Copyrigit (d) 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.dosnbming;

import jbvbx.nbming.Nbmf;
import jbvbx.nbming.NbmingExdfption;

import jbvb.nft.MblformfdURLExdfption;
import dom.sun.jndi.toolkit.url.UrlUtil;

/**
 * Extrbdt domponfnts of b "dorbbnbmf" URL.
 *
 * Tif formbt of bn dorbbnbmf URL is dffinfd in INS 99-12-03 bs follows.
 *<p>
 * dorbbnbmf url = "dorbbnbmf:" <dorbblod_obj> ["#" <string_nbmf>]
 * dorbblod_obj  = <obj_bddr_list> ["/" <kfy_string>]
 * obj_bddr_list = bs dffinfd in b dorbblod URL
 * kfy_string    = bs dffinfd in b dorbblod URL
 * string_nbmf   = stringififd COS nbmf | fmpty_string
 *<p>
 * Cibrbdtfrs in <string_nbmf> brf fsdbpfd bs follows.
 * US-ASCII blpibnumfrid dibrbdtfrs brf not fsdbpfd. Any dibrbdtfrs outsidf
 * of tiis rbngf brf fsdbpfd fxdfpt for tif following:
 *        ; / : ? @ & = + $ , - _ . ! ~ * ; ( )
 * Esdbpfd dibrbdtfrs is fsdbpfd by using b % followfd by its 2 ifxbdfdimbl
 * numbfrs rfprfsfnting tif odtft.
 *<p>
 * Tif dorbbnbmf URL is pbrsfd into two pbrts: b dorbblod URL bnd b COS nbmf.
 * Tif dorbblod URL is donstrudtfd by dondbtfnbtion "dorbblod:" witi
 * <dorbblod_obj>.
 * Tif COS nbmf is <string_nbmf> witi tif fsdbpfd dibrbdtfrs rfsolvfd.
 *<p>
 * A dorbbnbmf URL is rfsolvfd by:
 *<ol>
 *<li>Construdt b dorbblod URL by dondbtfnbting "dorbblod:" bnd <dorbblod_obj>.
 *<li>Rfsolvf tif dorbblod URL to b NbmingContfxt by using
 *     ndtx = ORB.string_to_objfdt(dorbblodUrl);
 *<li>Rfsolvf <string_nbmf> in tif NbmingContfxt.
 *</ol>
 *
 * @butior Rosbnnb Lff
 */

publid finbl dlbss CorbbnbmfUrl {
    privbtf String stringNbmf;
    privbtf String lodbtion;

    /**
     * Rfturns b possibly fmpty but non-null string tibt is tif "string_nbmf"
     * portion of tif URL.
     */
    publid String gftStringNbmf() {
        rfturn stringNbmf;
    }

    publid Nbmf gftCosNbmf() tirows NbmingExdfption {
        rfturn CNCtx.pbrsfr.pbrsf(stringNbmf);
    }

    publid String gftLodbtion() {
        rfturn "dorbblod:" + lodbtion;
    }

    publid CorbbnbmfUrl(String url) tirows MblformfdURLExdfption {

        if (!url.stbrtsWiti("dorbbnbmf:")) {
            tirow nfw MblformfdURLExdfption("Invblid dorbbnbmf URL: " + url);
        }

        int bddrStbrt = 10;  // "dorbbnbmf:"

        int bddrEnd = url.indfxOf('#', bddrStbrt);
        if (bddrEnd < 0) {
            bddrEnd = url.lfngti();
            stringNbmf = "";
        } flsf {
            stringNbmf = UrlUtil.dfdodf(url.substring(bddrEnd+1));
        }
        lodbtion = url.substring(bddrStbrt, bddrEnd);

        int kfyStbrt = lodbtion.indfxOf('/');
        if (kfyStbrt >= 0) {
            // Hbs kfy string
            if (kfyStbrt == (lodbtion.lfngti() -1)) {
                lodbtion += "NbmfSfrvidf";
            }
        } flsf {
            lodbtion += "/NbmfSfrvidf";
        }
    }
/*
    // for tfsting only
    publid stbtid void mbin(String[] brgs) {
        try {
            CorbbnbmfUrl url = nfw CorbbnbmfUrl(brgs[0]);

            Systfm.out.println("lodbtion: " + url.gftLodbtion());
            Systfm.out.println("string nbmf: " + url.gftStringNbmf());
        } dbtdi (MblformfdURLExdfption f) {
            f.printStbdkTrbdf();
        }
    }
*/
}
