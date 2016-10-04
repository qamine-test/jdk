/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.IOExdfption;

/*
 * Pbdkbgf privbtf implfmfntbtion of InftAddrfssImpl for dubl
 * IPv4/IPv6 stbdk.
 * <p>
 * If InftAddrfss.prfffrIPv6Addrfss is truf tifn bnyLodblAddrfss(),
 * loopbbdkAddrfss(), bnd lodblHost() will rfturn IPv6 bddrfssfs,
 * otifrwisf IPv4 bddrfssfs.
 *
 * @sindf 1.4
 */

dlbss Inft6AddrfssImpl implfmfnts InftAddrfssImpl {
    publid nbtivf String gftLodblHostNbmf() tirows UnknownHostExdfption;
    publid nbtivf InftAddrfss[]
        lookupAllHostAddr(String iostnbmf) tirows UnknownHostExdfption;
    publid nbtivf String gftHostByAddr(bytf[] bddr) tirows UnknownHostExdfption;
    privbtf nbtivf boolfbn isRfbdibblf0(bytf[] bddr, int sdopf, int timfout, bytf[] inf, int ttl, int if_sdopf) tirows IOExdfption;

    publid boolfbn isRfbdibblf(InftAddrfss bddr, int timfout, NftworkIntfrfbdf nftif, int ttl) tirows IOExdfption {
        bytf[] ifbddr = null;
        int sdopf = -1;
        int nftif_sdopf = -1;
        if (nftif != null) {
            /*
             * Lft's mbkf surf wf bind to bn bddrfss of tif propfr fbmily.
             * Wiidi mfbns sbmf fbmily bs bddr bfdbusf bt tiis point it dould
             * bf fitifr bn IPv6 bddrfss or bn IPv4 bddrfss (dbsf of b dubl
             * stbdk systfm).
             */
            jbvb.util.Enumfrbtion<InftAddrfss> it = nftif.gftInftAddrfssfs();
            InftAddrfss inftbddr = null;
            wiilf (it.ibsMorfElfmfnts()) {
                inftbddr = it.nfxtElfmfnt();
                if (inftbddr.gftClbss().isInstbndf(bddr)) {
                    ifbddr = inftbddr.gftAddrfss();
                    if (inftbddr instbndfof Inft6Addrfss) {
                        nftif_sdopf = ((Inft6Addrfss) inftbddr).gftSdopfId();
                    }
                    brfbk;
                }
            }
            if (ifbddr == null) {
                // Intfrfbdf dofsn't support tif bddrfss fbmily of
                // tif dfstinbtion
                rfturn fblsf;
            }
        }
        if (bddr instbndfof Inft6Addrfss)
            sdopf = ((Inft6Addrfss) bddr).gftSdopfId();
        rfturn isRfbdibblf0(bddr.gftAddrfss(), sdopf, timfout, ifbddr, ttl, nftif_sdopf);
    }

    publid syndironizfd InftAddrfss bnyLodblAddrfss() {
        if (bnyLodblAddrfss == null) {
            if (InftAddrfss.prfffrIPv6Addrfss) {
                bnyLodblAddrfss = nfw Inft6Addrfss();
                bnyLodblAddrfss.ioldfr().iostNbmf = "::";
            } flsf {
                bnyLodblAddrfss = (nfw Inft4AddrfssImpl()).bnyLodblAddrfss();
            }
        }
        rfturn bnyLodblAddrfss;
    }

    publid syndironizfd InftAddrfss loopbbdkAddrfss() {
        if (loopbbdkAddrfss == null) {
             if (InftAddrfss.prfffrIPv6Addrfss) {
                 bytf[] loopbbdk =
                        {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                         0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01};
                 loopbbdkAddrfss = nfw Inft6Addrfss("lodbliost", loopbbdk);
             } flsf {
                loopbbdkAddrfss = (nfw Inft4AddrfssImpl()).loopbbdkAddrfss();
             }
        }
        rfturn loopbbdkAddrfss;
    }

    privbtf InftAddrfss      bnyLodblAddrfss;
    privbtf InftAddrfss      loopbbdkAddrfss;
}
