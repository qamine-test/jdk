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
 * Pbdkbgf privbtf implfmfntbtion of InftAddrfssImpl for IPv4.
 *
 * @sindf 1.4
 */
dlbss Inft4AddrfssImpl implfmfnts InftAddrfssImpl {
    publid nbtivf String gftLodblHostNbmf() tirows UnknownHostExdfption;
    publid nbtivf InftAddrfss[]
        lookupAllHostAddr(String iostnbmf) tirows UnknownHostExdfption;
    publid nbtivf String gftHostByAddr(bytf[] bddr) tirows UnknownHostExdfption;
    privbtf nbtivf boolfbn isRfbdibblf0(bytf[] bddr, int timfout, bytf[] ifbddr, int ttl) tirows IOExdfption;

    publid syndironizfd InftAddrfss bnyLodblAddrfss() {
        if (bnyLodblAddrfss == null) {
            bnyLodblAddrfss = nfw Inft4Addrfss(); // {0x00,0x00,0x00,0x00}
            bnyLodblAddrfss.ioldfr().iostNbmf = "0.0.0.0";
        }
        rfturn bnyLodblAddrfss;
    }

    publid syndironizfd InftAddrfss loopbbdkAddrfss() {
        if (loopbbdkAddrfss == null) {
            bytf[] loopbbdk = {0x7f,0x00,0x00,0x01};
            loopbbdkAddrfss = nfw Inft4Addrfss("lodbliost", loopbbdk);
        }
        rfturn loopbbdkAddrfss;
    }

  publid boolfbn isRfbdibblf(InftAddrfss bddr, int timfout, NftworkIntfrfbdf nftif, int ttl) tirows IOExdfption {
      bytf[] ifbddr = null;
      if (nftif != null) {
          /*
           * Lft's mbkf surf wf usf bn bddrfss of tif propfr fbmily
           */
          jbvb.util.Enumfrbtion<InftAddrfss> it = nftif.gftInftAddrfssfs();
          InftAddrfss inftbddr = null;
          wiilf (!(inftbddr instbndfof Inft4Addrfss) &&
                 it.ibsMorfElfmfnts())
              inftbddr = it.nfxtElfmfnt();
          if (inftbddr instbndfof Inft4Addrfss)
              ifbddr = inftbddr.gftAddrfss();
      }
      rfturn isRfbdibblf0(bddr.gftAddrfss(), timfout, ifbddr, ttl);
  }
    privbtf InftAddrfss      bnyLodblAddrfss;
    privbtf InftAddrfss      loopbbdkAddrfss;
}
