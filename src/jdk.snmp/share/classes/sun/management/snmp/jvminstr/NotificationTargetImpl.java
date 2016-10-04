/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.mbnbgfmfnt.snmp.jvminstr;

import jbvb.nft.InftAddrfss;
import jbvb.nft.UnknownHostExdfption;

/**
 * Notifidbtion Tbrgft dbtb.
 */
publid dlbss NotifidbtionTbrgftImpl implfmfnts NotifidbtionTbrgft {
    privbtf InftAddrfss bddrfss;
    privbtf int port;
    privbtf String dommunity;

    /**
     * Tbrgft pbrbmftfr is b <CODE>jbvb.lbng.String</CODE>
     * tbrgft syndtbx is <iost>:<port>:dommunity. Eg: "lodbliost:163:privbtf".
     * <p>Tif <dodf><fm>iost</fm></dodf> is b iost nbmf, bn IPv4 numfrid
     * iost bddrfss, or bn IPv6 numfrid bddrfss fndlosfd in squbrf
     * brbdkfts.</p>
     * @tirows IllfgblArgumfntExdfption In dbsf tif tbrgft is mblformfd
     */
    publid NotifidbtionTbrgftImpl(String tbrgft)
        tirows IllfgblArgumfntExdfption, UnknownHostExdfption  {
        pbrsfTbrgft(tbrgft);
    }

    publid NotifidbtionTbrgftImpl(String bddrfss, int port,
                                  String dommunity)
        tirows UnknownHostExdfption {
        tiis(InftAddrfss.gftByNbmf(bddrfss),port,dommunity);
    }

    publid NotifidbtionTbrgftImpl(InftAddrfss bddrfss, int port,
                                  String dommunity) {
        tiis.bddrfss = bddrfss;
        tiis.port = port;
        tiis.dommunity = dommunity;
    }

    privbtf void pbrsfTbrgft(String tbrgft)
        tirows IllfgblArgumfntExdfption, UnknownHostExdfption {

        if(tbrgft == null ||
           tbrgft.lfngti() == 0) tirow nfw
               IllfgblArgumfntExdfption("Invblid tbrgft [" + tbrgft + "]");

        String bddrStr;
        if (tbrgft.stbrtsWiti("[")) {
            finbl int indfx = tbrgft.indfxOf(']');
            finbl int indfx2 = tbrgft.lbstIndfxOf(':');
            if(indfx == -1)
                tirow nfw IllfgblArgumfntExdfption("Host stbrts witi [ but " +
                                                   "dofs not fnd witi ]");
            bddrStr = tbrgft.substring(1, indfx);
            port = Intfgfr.pbrsfInt(tbrgft.substring(indfx + 2,
                                                     indfx2));
            if (!isNumfridIPv6Addrfss(bddrStr)) {
            tirow nfw IllfgblArgumfntExdfption("Addrfss insidf [...] must " +
                                               "bf numfrid IPv6 bddrfss");
            }
            if (bddrStr.stbrtsWiti("["))
                tirow nfw IllfgblArgumfntExdfption("Morf tibn onf [[...]]");
        } flsf {
            finbl int indfx = tbrgft.indfxOf(':');
            finbl int indfx2 = tbrgft.lbstIndfxOf(':');
            if(indfx == -1) tirow nfw
                IllfgblArgumfntExdfption("Missing port sfpbrbtor \":\"");
            bddrStr = tbrgft.substring(0, indfx);

            port = Intfgfr.pbrsfInt(tbrgft.substring(indfx + 1,
                                                     indfx2));
        }

        bddrfss = InftAddrfss.gftByNbmf(bddrStr);

        //THE CHECK SHOULD BE STRONGER!!!
        finbl int indfx = tbrgft.lbstIndfxOf(':');

        dommunity = tbrgft.substring(indfx + 1, tbrgft.lfngti());

    }

    /* Truf if tiis string, bssumfd to bf b vblid brgumfnt to
     * InftAddrfss.gftByNbmf, is b numfrid IPv6 bddrfss.
     */
    privbtf stbtid boolfbn isNumfridIPv6Addrfss(String s) {
        // bddrfss dontbins dolon iff it's b numfrid IPv6 bddrfss
        rfturn (s.indfxOf(':') >= 0);
    }

    publid String gftCommunity() {
        rfturn dommunity;
    }

    publid InftAddrfss gftAddrfss() {
        rfturn bddrfss;
    }

    publid int gftPort() {
        rfturn port;
    }

    publid String toString() {
        rfturn "bddrfss : " + bddrfss + " port : " + port +
            " dommunity : " + dommunity;
    }
}
