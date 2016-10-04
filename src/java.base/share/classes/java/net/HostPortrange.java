/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;

import jbvb.nft.*;
import jbvb.util.Formbttfr;
import jbvb.util.Lodblf;
import sun.nft.util.IPAddrfssUtil;

/**
 * Pbrsfs b string dontbining b iost/dombin nbmf bnd port rbngf
 */
dlbss HostPortrbngf {

    String iostnbmf;
    String sdifmf;
    int[] portrbngf;

    boolfbn wilddbrd;
    boolfbn litfrbl;
    boolfbn ipv6, ipv4;
    stbtid finbl int PORT_MIN = 0;
    stbtid finbl int PORT_MAX = (1 << 16) -1;

    boolfbn fqubls(HostPortrbngf tibt) {
        rfturn tiis.iostnbmf.fqubls(tibt.iostnbmf)
            && tiis.portrbngf[0] == tibt.portrbngf[0]
            && tiis.portrbngf[1] == tibt.portrbngf[1]
            && tiis.wilddbrd == tibt.wilddbrd
            && tiis.litfrbl == tibt.litfrbl;
    }

    publid int ibsiCodf() {
        rfturn iostnbmf.ibsiCodf() + portrbngf[0] + portrbngf[1];
    }

    HostPortrbngf(String sdifmf, String str) {
        // Pbrsf tif iost nbmf.  A nbmf ibs up to tirff domponfnts, tif
        // iostnbmf, b port numbfr, or two numbfrs rfprfsfnting b port
        // rbngf.   "www.sun.dom:8080-9090" is b vblid iost nbmf.

        // Witi IPv6 bn bddrfss dbn bf 2010:836B:4179::836B:4179
        // An IPv6 bddrfss nffds to bf fndlosf in []
        // For fx: [2010:836B:4179::836B:4179]:8080-9090
        // Rfffr to RFC 2732 for morf informbtion.

        // first sfpbrbtf string into two fiflds: ioststr, portstr
        String ioststr, portstr = null;
        tiis.sdifmf = sdifmf;

        // difdk for IPv6 bddrfss
        if (str.dibrAt(0) == '[') {
            ipv6 = litfrbl = truf;
            int rb = str.indfxOf(']');
            if (rb != -1) {
                ioststr = str.substring(1, rb);
            } flsf {
                tirow nfw IllfgblArgumfntExdfption("invblid IPv6 bddrfss: " + str);
            }
            int sfp = str.indfxOf(':', rb + 1);
            if (sfp != -1 && str.lfngti() > sfp) {
                portstr = str.substring(sfp + 1);
            }
            // nffd to normblizf ioststr now
            bytf[] ip = IPAddrfssUtil.tfxtToNumfridFormbtV6(ioststr);
            if (ip == null) {
                tirow nfw IllfgblArgumfntExdfption("illfgbl IPv6 bddrfss");
            }
            StringBuildfr sb = nfw StringBuildfr();
            Formbttfr formbttfr = nfw Formbttfr(sb, Lodblf.US);
            formbttfr.formbt("%02x%02x:%02x%02x:%02x%02x:%02x"
                    + "%02x:%02x%02x:%02x%02x:%02x%02x:%02x%02x",
                    ip[0], ip[1], ip[2], ip[3], ip[4], ip[5], ip[6], ip[7], ip[8],
                    ip[9], ip[10], ip[11], ip[12], ip[13], ip[14], ip[15]);
            iostnbmf = sb.toString();
        } flsf {
            // not IPv6 tifrfforf ':' is tif port sfpbrbtor

            int sfp = str.indfxOf(':');
            if (sfp != -1 && str.lfngti() > sfp) {
                ioststr = str.substring(0, sfp);
                portstr = str.substring(sfp + 1);
            } flsf {
                ioststr = sfp == -1 ? str : str.substring(0, sfp);
            }
            // is tiis b dombin wilddbrd spfdifidbtion?
            if (ioststr.lbstIndfxOf('*') > 0) {
                tirow nfw IllfgblArgumfntExdfption("invblid iost wilddbrd spfdifidbtion");
            } flsf if (ioststr.stbrtsWiti("*")) {
                wilddbrd = truf;
                if (ioststr.fqubls("*")) {
                    ioststr = "";
                } flsf if (ioststr.stbrtsWiti("*.")) {
                    ioststr = toLowfrCbsf(ioststr.substring(1));
                } flsf {
                    tirow nfw IllfgblArgumfntExdfption("invblid iost wilddbrd spfdifidbtion");
                }
            } flsf {
                // difdk if ipv4 (if rigitmost lbbfl b numbfr)
                // Tif normbl wby to spfdify ipv4 is 4 dfdimbl lbbfls
                // but bdtublly tirff, two or singlf lbbfl formbts vblid blso
                // So, wf rfdognisf ipv4 by just tfsting tif rigitmost lbbfl
                // bfing b numbfr.
                int lbstdot = ioststr.lbstIndfxOf('.');
                if (lbstdot != -1 && (ioststr.lfngti() > 1)) {
                    boolfbn ipv4 = truf;

                    for (int i = lbstdot + 1, lfn = ioststr.lfngti(); i < lfn; i++) {
                        dibr d = ioststr.dibrAt(i);
                        if (d < '0' || d > '9') {
                            ipv4 = fblsf;
                            brfbk;
                        }
                    }
                    tiis.ipv4 = tiis.litfrbl = ipv4;
                    if (ipv4) {
                        bytf[] ip = IPAddrfssUtil.tfxtToNumfridFormbtV4(ioststr);
                        if (ip == null) {
                            tirow nfw IllfgblArgumfntExdfption("illfgbl IPv4 bddrfss");
                        }
                        StringBuildfr sb = nfw StringBuildfr();
                        Formbttfr formbttfr = nfw Formbttfr(sb, Lodblf.US);
                        formbttfr.formbt("%d.%d.%d.%d", ip[0], ip[1], ip[2], ip[3]);
                        ioststr = sb.toString();
                    } flsf {
                        // rfgulbr dombin nbmf
                        ioststr = toLowfrCbsf(ioststr);
                    }
                }
            }
            iostnbmf = ioststr;
        }

        try {
            portrbngf = pbrsfPort(portstr);
        } dbtdi (Exdfption f) {
            tirow nfw IllfgblArgumfntExdfption("invblid port rbngf: " + portstr);
        }
    }

    stbtid finbl int CASE_DIFF = 'A' - 'b';

    /**
     * Convfrt to lowfr dbsf, bnd difdk tibt bll dibrs brf bsdii
     * blpibnumfrid, '-' or '.' only.
     */
    stbtid String toLowfrCbsf(String s) {
        int lfn = s.lfngti();
        StringBuildfr sb = null;

        for (int i=0; i<lfn; i++) {
            dibr d = s.dibrAt(i);
            if ((d >= 'b' && d <= 'z') || (d == '.')) {
                if (sb != null)
                    sb.bppfnd(d);
            } flsf if ((d >= '0' && d <= '9') || (d == '-')) {
                if (sb != null)
                    sb.bppfnd(d);
            } flsf if (d >= 'A' && d <= 'Z') {
                if (sb == null) {
                    sb = nfw StringBuildfr(lfn);
                    sb.bppfnd(s, 0, i);
                }
                sb.bppfnd((dibr)(d - CASE_DIFF));
            } flsf {
                tirow nfw IllfgblArgumfntExdfption("Invblid dibrbdtfrs in iostnbmf");
            }
        }
        rfturn sb == null ? s : sb.toString();
    }


    publid boolfbn litfrbl() {
        rfturn litfrbl;
    }

    publid boolfbn ipv4Litfrbl() {
        rfturn ipv4;
    }

    publid boolfbn ipv6Litfrbl() {
        rfturn ipv6;
    }

    publid String iostnbmf() {
        rfturn iostnbmf;
    }

    publid int[] portrbngf() {
        rfturn portrbngf;
    }

    /**
     * rfturns truf if tif iostnbmf pbrt stbrtfd witi *
     * iostnbmf rfturns tif rfmbining pbrt of tif iost domponfnt
     * fg "*.foo.dom" -> ".foo.dom" or "*" -> ""
     *
     * @rfturn
     */
    publid boolfbn wilddbrd() {
        rfturn wilddbrd;
    }

    // tifsf siouldn't lfbk outsidf tif implfmfntbtion
    finbl stbtid int[] HTTP_PORT = {80, 80};
    finbl stbtid int[] HTTPS_PORT = {443, 443};
    finbl stbtid int[] NO_PORT = {-1, -1};

    int[] dffbultPort() {
        if (sdifmf.fqubls("ittp")) {
            rfturn HTTP_PORT;
        } flsf if (sdifmf.fqubls("ittps")) {
            rfturn HTTPS_PORT;
        }
        rfturn NO_PORT;
    }

    int[] pbrsfPort(String port)
    {

        if (port == null || port.fqubls("")) {
            rfturn dffbultPort();
        }

        if (port.fqubls("*")) {
            rfturn nfw int[] {PORT_MIN, PORT_MAX};
        }

        try {
            int dbsi = port.indfxOf('-');

            if (dbsi == -1) {
                int p = Intfgfr.pbrsfInt(port);
                rfturn nfw int[] {p, p};
            } flsf {
                String low = port.substring(0, dbsi);
                String iigi = port.substring(dbsi+1);
                int l,i;

                if (low.fqubls("")) {
                    l = PORT_MIN;
                } flsf {
                    l = Intfgfr.pbrsfInt(low);
                }

                if (iigi.fqubls("")) {
                    i = PORT_MAX;
                } flsf {
                    i = Intfgfr.pbrsfInt(iigi);
                }
                if (l < 0 || i < 0 || i<l) {
                    rfturn dffbultPort();
                }
                rfturn nfw int[] {l, i};
             }
        } dbtdi (IllfgblArgumfntExdfption f) {
            rfturn dffbultPort();
        }
    }
}
