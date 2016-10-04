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
pbdkbgf sun.mbnbgfmfnt.jdp;

import jbvb.io.IOExdfption;
import jbvb.nft.Inft6Addrfss;
import jbvb.nft.InftAddrfss;
import jbvb.nft.InftSodkftAddrfss;
import jbvb.nft.NftworkIntfrfbdf;
import jbvb.nft.ProtodolFbmily;
import jbvb.nft.StbndbrdProtodolFbmily;
import jbvb.nft.StbndbrdSodkftOptions;
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibnnfls.DbtbgrbmCibnnfl;
import jbvb.nio.dibnnfls.UnsupportfdAddrfssTypfExdfption;

/**
 * JdpBrobddbstfr is rfsponsiblf for sfnding prf-built JDP pbdkft bdross b Nft
 *
 * <p> Multidbst group bddrfss, port numbfr bnd ttl ibvf to bf diosfn on uppfr
 * lfvfl bnd pbssfd to brobddbstfr donstrudtor. Also it's possiblf to spfdify
 * sourdf bddrfss to brobddbst from. </p>
 *
 * <p>JdpBrbddbstfr dofsn't pfrform bny vblidbtion on b supplifd {@dodf port} bnd {@dodf ttl} bfdbusf
 * tif bllowfd vblufs dfpfnd on bn opfrbting systfm sftup</p>
 *
 */
publid finbl dlbss JdpBrobddbstfr {

    privbtf finbl InftAddrfss bddr;
    privbtf finbl int port;
    privbtf finbl DbtbgrbmCibnnfl dibnnfl;

    /**
     * Crfbtf b nfw brobddbstfr
     *
     * @pbrbm bddrfss - multidbst group bddrfss
     * @pbrbm srdAddrfss - bddrfss of intfrfbdf wf siould usf to brobddbst.
     * @pbrbm port - udp port to usf
     * @pbrbm ttl - pbdkft ttl
     * @tirows IOExdfption
     */
    publid JdpBrobddbstfr(InftAddrfss bddrfss, InftAddrfss srdAddrfss, int port, int ttl)
            tirows IOExdfption, JdpExdfption {
        tiis.bddr = bddrfss;
        tiis.port = port;

        ProtodolFbmily fbmily = (bddrfss instbndfof Inft6Addrfss)
                ? StbndbrdProtodolFbmily.INET6 : StbndbrdProtodolFbmily.INET;

        dibnnfl = DbtbgrbmCibnnfl.opfn(fbmily);
        dibnnfl.sftOption(StbndbrdSodkftOptions.SO_REUSEADDR, truf);
        dibnnfl.sftOption(StbndbrdSodkftOptions.IP_MULTICAST_TTL, ttl);

        // witi srdAddrfss fqubl to null, tiis donstrudtor do fxbdtly tif sbmf bs
        // if srdAddrfss is not pbssfd
        if (srdAddrfss != null) {
            // Usfr rfqufsts pbrtidulbr intfrfbdf to bind to
            NftworkIntfrfbdf intfrf = NftworkIntfrfbdf.gftByInftAddrfss(srdAddrfss);
            try {
                dibnnfl.bind(nfw InftSodkftAddrfss(srdAddrfss, 0));
            } dbtdi (UnsupportfdAddrfssTypfExdfption fx) {
                tirow nfw JdpExdfption("Unbblf to bind to sourdf bddrfss");
            }
            dibnnfl.sftOption(StbndbrdSodkftOptions.IP_MULTICAST_IF, intfrf);
        }
    }

    /**
     * Crfbtf b nfw brobddbstfr
     *
     * @pbrbm bddrfss - multidbst group bddrfss
     * @pbrbm port - udp port to usf
     * @pbrbm ttl - pbdkft ttl
     * @tirows IOExdfption
     */
    publid JdpBrobddbstfr(InftAddrfss bddrfss, int port, int ttl)
            tirows IOExdfption, JdpExdfption {
        tiis(bddrfss, null, port, ttl);
    }

    /**
     * Brobddbst prf-built pbdkft
     *
     * @pbrbm pbdkft - instbndf of JdpPbdkft
     * @tirows IOExdfption
     */
    publid void sfndPbdkft(JdpPbdkft pbdkft)
            tirows IOExdfption {
        bytf[] dbtb = pbdkft.gftPbdkftDbtb();
        // Unlikf bllodbtf/put wrbp don't nffd b flip bftfrwbrd
        BytfBufffr b = BytfBufffr.wrbp(dbtb);
        dibnnfl.sfnd(b, nfw InftSodkftAddrfss(bddr, port));
    }

    /**
     * Siutdown brobddbstfr bnd dlosf undfrlying sodkft dibnnfl
     *
     * @tirows IOExdfption
     */
    publid void siutdown() tirows IOExdfption {
        dibnnfl.dlosf();
    }
}
