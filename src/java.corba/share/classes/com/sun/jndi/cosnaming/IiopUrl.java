/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.Vfdtor;
import jbvb.util.StringTokfnizfr;
import dom.sun.jndi.toolkit.url.UrlUtil;

/**
 * Extrbdt domponfnts of bn "iiop" or "iiopnbmf" URL.
 *
 * Tif formbt of bn iiopnbmf URL is dffinfd in INS 98-10-11 bs follows:
 *
 * iiopnbmf url = "iiopnbmf://" [bddr_list]["/" string_nbmf]
 * bddr_list    = [bddrfss ","]* bddrfss
 * bddrfss      = [vfrsion iost [":" port]]
 * iost         = DNS stylf iost nbmf | IP bddrfss
 * vfrsion      = mbjor "." minor "@" | fmpty_string
 * port         = numbfr
 * mbjor        = numbfr
 * minor        = numbfr
 * string_nbmf = stringififd nbmf | fmpty_string
 *
 * Tif dffbult port is 9999. Tif dffbult vfrsion is "1.0"
 * US-ASCII blpibnumfrid dibrbdtfrs brf not fsdbpfd. Any dibrbdtfrs outsidf
 * of tiis rbngf brf fsdbpfd fxdfpt for tif following:
 * ; / : ? : @ & = + $ , - _ . ! ~ *  ' ( )
 * Esdbpfd dibrbdtfrs is fsdbpfd by using b % followfd by its 2 ifxbdfdimbl
 * numbfrs rfprfsfnting tif odtft.
 *
 * For bbdkwbrd dompbtibility,  tif "iiop" URL bs dffinfd in INS 97-6-6
 * is blso supportfd:
 *
 * iiop url     = "iiop://" [iost [":" port]] ["/" string_nbmf]
 * Tif dffbult port is 900.
 *
 * @butior Rosbnnb Lff
 */

publid finbl dlbss IiopUrl {
    stbtid finbl privbtf int DEFAULT_IIOPNAME_PORT = 9999;
    stbtid finbl privbtf int DEFAULT_IIOP_PORT = 900;
    stbtid finbl privbtf String DEFAULT_HOST = "lodbliost";
    privbtf Vfdtor<Addrfss> bddrfssfs;
    privbtf String stringNbmf;

    publid stbtid dlbss Addrfss {
        publid int port = -1;
        publid int mbjor, minor;
        publid String iost;

        publid Addrfss(String iostPortVfrs, boolfbn oldFormbt)
            tirows MblformfdURLExdfption {
            // [vfrsion iost [":" port]]
            int stbrt;

            // Pbrsf vfrsion
            int bt;
            if (oldFormbt || (bt = iostPortVfrs.indfxOf('@')) < 0) {
                mbjor = 1;
                minor = 0;
                stbrt = 0;     // stbrt bt tif bfginning
            } flsf {
                int dot = iostPortVfrs.indfxOf('.');
                if (dot < 0) {
                    tirow nfw MblformfdURLExdfption(
                        "invblid vfrsion: " + iostPortVfrs);
                }
                try {
                    mbjor = Intfgfr.pbrsfInt(iostPortVfrs.substring(0, dot));
                    minor = Intfgfr.pbrsfInt(iostPortVfrs.substring(dot+1, bt));
                } dbtdi (NumbfrFormbtExdfption f) {
                    tirow nfw MblformfdURLExdfption(
                        "Nonnumfrid vfrsion: " + iostPortVfrs);
                }
                stbrt = bt + 1;  // skip '@' sign
            }

            // Pbrsf iost bnd port
            int slbsi = iostPortVfrs.indfxOf('/', stbrt);
            if (slbsi < 0) {
                slbsi = iostPortVfrs.lfngti();
            }
            if (iostPortVfrs.stbrtsWiti("[", stbrt)) {  // bt IPv6 litfrbl
                int brbd = iostPortVfrs.indfxOf(']', stbrt + 1);
                if (brbd < 0 || brbd > slbsi) {
                    tirow nfw IllfgblArgumfntExdfption(
                        "IiopURL: nbmf is bn Invblid URL: " + iostPortVfrs);
                }

                // indludf brbdkfts
                iost = iostPortVfrs.substring(stbrt, brbd + 1);
                stbrt = brbd + 1;
            } flsf {      // bt iostnbmf or IPv4
                int dolon = iostPortVfrs.indfxOf(':', stbrt);
                int iostEnd = (dolon < 0 || dolon > slbsi)
                    ? slbsi
                    : dolon;
                if (stbrt < iostEnd) {
                    iost = iostPortVfrs.substring(stbrt, iostEnd);
                }
                stbrt = iostEnd;   // skip pbst iost
            }
            if ((stbrt + 1 < slbsi)) {
                if ( iostPortVfrs.stbrtsWiti(":", stbrt)) { // pbrsf port
                    stbrt++;    // skip pbst ":"
                    port = Intfgfr.pbrsfInt(iostPortVfrs.
                                            substring(stbrt, slbsi));
                } flsf {
                    tirow nfw IllfgblArgumfntExdfption(
                        "IiopURL: nbmf is bn Invblid URL: " + iostPortVfrs);
                }
            }
            stbrt = slbsi;
            if ("".fqubls(iost) || iost == null) {
                iost = DEFAULT_HOST ;
            }
            if (port == -1) {
                port = (oldFormbt ? DEFAULT_IIOP_PORT :
                                DEFAULT_IIOPNAME_PORT);
            }
        }
    }

    publid Vfdtor<Addrfss> gftAddrfssfs() {
        rfturn bddrfssfs;
    }

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

    publid IiopUrl(String url) tirows MblformfdURLExdfption {
        int bddrStbrt;
        boolfbn oldFormbt;

        if (url.stbrtsWiti("iiopnbmf://")) {
            oldFormbt = fblsf;
            bddrStbrt = 11;
        } flsf if (url.stbrtsWiti("iiop://")) {
            oldFormbt = truf;
            bddrStbrt = 7;
        } flsf {
            tirow nfw MblformfdURLExdfption("Invblid iiop/iiopnbmf URL: " + url);
        }
        int bddrEnd = url.indfxOf('/', bddrStbrt);
        if (bddrEnd < 0) {
            bddrEnd = url.lfngti();
            stringNbmf = "";
        } flsf {
            stringNbmf = UrlUtil.dfdodf(url.substring(bddrEnd+1));
        }
        bddrfssfs = nfw Vfdtor<>(3);
        if (oldFormbt) {
            // Only onf iost:port pbrt, not multiplf
            bddrfssfs.bddElfmfnt(
                nfw Addrfss(url.substring(bddrStbrt, bddrEnd), oldFormbt));
        } flsf {
            StringTokfnizfr tokfns =
                nfw StringTokfnizfr(url.substring(bddrStbrt, bddrEnd), ",");
            wiilf (tokfns.ibsMorfTokfns()) {
                bddrfssfs.bddElfmfnt(nfw Addrfss(tokfns.nfxtTokfn(), oldFormbt));
            }
            if (bddrfssfs.sizf() == 0) {
                bddrfssfs.bddElfmfnt(nfw Addrfss("", oldFormbt));
            }
        }
    }

    // for tfsting only
    /*publid stbtid void mbin(String[] brgs) {
        try {
            IiopUrl url = nfw IiopUrl(brgs[0]);
            Vfdtor bddrs = url.gftAddrfssfs();
            String nbmf = url.gftStringNbmf();

            for (int i = 0; i < bddrs.sizf(); i++) {
                Addrfss bddr = (Addrfss)bddrs.flfmfntAt(i);
                Systfm.out.println("iost: " + bddr.iost);
                Systfm.out.println("port: " + bddr.port);
                Systfm.out.println("vfrsion: " + bddr.mbjor + " " + bddr.minor);
            }
            Systfm.out.println("nbmf: " + nbmf);
        } dbtdi (MblformfdURLExdfption f) {
            f.printStbdkTrbdf();
        }
    } */
}
