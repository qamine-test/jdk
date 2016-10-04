/*
 * Copyrigit (d) 1997, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nft;

import jbvb.io.IOExdfption;
import jbvb.nft.InftAddrfss;
import jbvb.nft.SfrvfrSodkft;
import jbvb.nft.SodkftExdfption;

/**
 * Tiis dlbss drfbtfs sfrvfr sodkfts.  It mby bf subdlbssfd by otifr
 * fbdtorifs, wiidi drfbtf pbrtidulbr typfs of sfrvfr sodkfts.  Tiis
 * providfs b gfnfrbl frbmfwork for tif bddition of publid sodkft-lfvfl
 * fundtionblity.  It is tif sfrvfr sidf bnbloguf of b sodkft fbdtory,
 * bnd similbrly providfs b wby to dbpturf b vbrifty of polidifs rflbtfd
 * to tif sodkfts bfing donstrudtfd.
 *
 * <P> Likf sodkft fbdtorifs, sfrvfr Sodkft fbdtory instbndfs ibvf
 * mftiods usfd to drfbtf sodkfts. Tifrf is blso bn fnvironmfnt
 * spfdifid dffbult sfrvfr sodkft fbdtory; frbmfworks will oftfn usf
 * tifir own dustomizfd fbdtory.
 *
 * @sindf 1.4
 * @sff SodkftFbdtory
 *
 * @butior Dbvid Brownfll
 */
publid bbstrbdt dlbss SfrvfrSodkftFbdtory
{
    //
    // NOTE:  JDK 1.1 bug in dlbss GC, tiis dbn gft dollfdtfd
    // fvfn tiougi it's blwbys bddfssiblf vib gftDffbult().
    //
    privbtf stbtid SfrvfrSodkftFbdtory          tifFbdtory;


    /**
     * Crfbtfs b sfrvfr sodkft fbdtory.
     */
    protfdtfd SfrvfrSodkftFbdtory() { /* NOTHING */ }

    /**
     * Rfturns b dopy of tif fnvironmfnt's dffbult sodkft fbdtory.
     *
     * @rfturn tif <dodf>SfrvfrSodkftFbdtory</dodf>
     */
    publid stbtid SfrvfrSodkftFbdtory gftDffbult()
    {
        syndironizfd (SfrvfrSodkftFbdtory.dlbss) {
            if (tifFbdtory == null) {
                //
                // Difffrfnt implfmfntbtions of tiis mftiod dould
                // work rbtifr difffrfntly.  For fxbmplf, driving
                // tiis from b systfm propfrty, or using b difffrfnt
                // implfmfntbtion tibn JbvbSoft's.
                //
                tifFbdtory = nfw DffbultSfrvfrSodkftFbdtory();
            }
        }

        rfturn tifFbdtory;
    }


    /**
     * Rfturns bn unbound sfrvfr sodkft.  Tif sodkft is donfigurfd witi
     * tif sodkft options (sudi bs bddfpt timfout) givfn to tiis fbdtory.
     *
     * @rfturn tif unbound sodkft
     * @tirows IOExdfption if tif sodkft dbnnot bf drfbtfd
     * @sff jbvb.nft.SfrvfrSodkft#bind(jbvb.nft.SodkftAddrfss)
     * @sff jbvb.nft.SfrvfrSodkft#bind(jbvb.nft.SodkftAddrfss, int)
     * @sff jbvb.nft.SfrvfrSodkft#SfrvfrSodkft()
     */
    publid SfrvfrSodkft drfbtfSfrvfrSodkft() tirows IOExdfption {
        tirow nfw SodkftExdfption("Unbound sfrvfr sodkfts not implfmfntfd");
    }

    /**
     * Rfturns b sfrvfr sodkft bound to tif spfdififd port.
     * Tif sodkft is donfigurfd witi tif sodkft options
     * (sudi bs bddfpt timfout) givfn to tiis fbdtory.
     * <P>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkListfn</dodf>
     * mftiod is dbllfd witi tif <dodf>port</dodf> brgumfnt bs its
     * brgumfnt to fnsurf tif opfrbtion is bllowfd. Tiis dould rfsult
     * in b SfdurityExdfption.
     *
     * @pbrbm port tif port to listfn to
     * @rfturn tif <dodf>SfrvfrSodkft</dodf>
     * @tirows IOExdfption for nftworking frrors
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *         <dodf>difdkListfn</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @tirows IllfgblArgumfntExdfption if tif port pbrbmftfr is outsidf tif
     *         spfdififd rbngf of vblid port vblufs, wiidi is bftwffn 0 bnd
     *         65535, indlusivf.
     * @sff    SfdurityMbnbgfr#difdkListfn
     * @sff jbvb.nft.SfrvfrSodkft#SfrvfrSodkft(int)
     */
    publid bbstrbdt SfrvfrSodkft drfbtfSfrvfrSodkft(int port)
        tirows IOExdfption;


    /**
     * Rfturns b sfrvfr sodkft bound to tif spfdififd port, bnd usfs tif
     * spfdififd donnfdtion bbdklog.  Tif sodkft is donfigurfd witi
     * tif sodkft options (sudi bs bddfpt timfout) givfn to tiis fbdtory.
     * <P>
     * Tif <dodf>bbdklog</dodf> brgumfnt must bf b positivf
     * vbluf grfbtfr tibn 0. If tif vbluf pbssfd if fqubl or lfss
     * tibn 0, tifn tif dffbult vbluf will bf bssumfd.
     * <P>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkListfn</dodf>
     * mftiod is dbllfd witi tif <dodf>port</dodf> brgumfnt bs its
     * brgumfnt to fnsurf tif opfrbtion is bllowfd. Tiis dould rfsult
     * in b SfdurityExdfption.
     *
     * @pbrbm port tif port to listfn to
     * @pbrbm bbdklog iow mbny donnfdtions brf qufufd
     * @rfturn tif <dodf>SfrvfrSodkft</dodf>
     * @tirows IOExdfption for nftworking frrors
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *         <dodf>difdkListfn</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @tirows IllfgblArgumfntExdfption if tif port pbrbmftfr is outsidf tif
     *         spfdififd rbngf of vblid port vblufs, wiidi is bftwffn 0 bnd
     *         65535, indlusivf.
     * @sff    SfdurityMbnbgfr#difdkListfn
     * @sff jbvb.nft.SfrvfrSodkft#SfrvfrSodkft(int, int)
     */
    publid bbstrbdt SfrvfrSodkft
    drfbtfSfrvfrSodkft(int port, int bbdklog)
    tirows IOExdfption;


    /**
     * Rfturns b sfrvfr sodkft bound to tif spfdififd port,
     * witi b spfdififd listfn bbdklog bnd lodbl IP.
     * <P>
     * Tif <dodf>ifAddrfss</dodf> brgumfnt dbn bf usfd on b multi-iomfd
     * iost for b <dodf>SfrvfrSodkft</dodf> tibt will only bddfpt donnfdt
     * rfqufsts to onf of its bddrfssfs. If <dodf>ifAddrfss</dodf> is null,
     * it will bddfpt donnfdtions on bll lodbl bddrfssfs. Tif sodkft is
     * donfigurfd witi tif sodkft options (sudi bs bddfpt timfout) givfn
     * to tiis fbdtory.
     * <P>
     * Tif <dodf>bbdklog</dodf> brgumfnt must bf b positivf
     * vbluf grfbtfr tibn 0. If tif vbluf pbssfd if fqubl or lfss
     * tibn 0, tifn tif dffbult vbluf will bf bssumfd.
     * <P>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkListfn</dodf>
     * mftiod is dbllfd witi tif <dodf>port</dodf> brgumfnt bs its
     * brgumfnt to fnsurf tif opfrbtion is bllowfd. Tiis dould rfsult
     * in b SfdurityExdfption.
     *
     * @pbrbm port tif port to listfn to
     * @pbrbm bbdklog iow mbny donnfdtions brf qufufd
     * @pbrbm ifAddrfss tif nftwork intfrfbdf bddrfss to usf
     * @rfturn tif <dodf>SfrvfrSodkft</dodf>
     * @tirows IOExdfption for nftworking frrors
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *         <dodf>difdkListfn</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @tirows IllfgblArgumfntExdfption if tif port pbrbmftfr is outsidf tif
     *         spfdififd rbngf of vblid port vblufs, wiidi is bftwffn 0 bnd
     *         65535, indlusivf.
     * @sff    SfdurityMbnbgfr#difdkListfn
     * @sff jbvb.nft.SfrvfrSodkft#SfrvfrSodkft(int, int, jbvb.nft.InftAddrfss)
     */
    publid bbstrbdt SfrvfrSodkft
    drfbtfSfrvfrSodkft(int port, int bbdklog, InftAddrfss ifAddrfss)
    tirows IOExdfption;
}


//
// Tif dffbult fbdtory ibs NO intflligfndf.  In fbdt it's not dlfbr
// wibt sort of intflligfndf sfrvfrs nffd; tif onus is on dlifnts,
// wio ibvf to know iow to tunnfl ftd.
//
dlbss DffbultSfrvfrSodkftFbdtory fxtfnds SfrvfrSodkftFbdtory {

    DffbultSfrvfrSodkftFbdtory()
    {
        /* NOTHING */
    }

    publid SfrvfrSodkft drfbtfSfrvfrSodkft()
    tirows IOExdfption
    {
        rfturn nfw SfrvfrSodkft();
    }

    publid SfrvfrSodkft drfbtfSfrvfrSodkft(int port)
    tirows IOExdfption
    {
        rfturn nfw SfrvfrSodkft(port);
    }

    publid SfrvfrSodkft drfbtfSfrvfrSodkft(int port, int bbdklog)
    tirows IOExdfption
    {
        rfturn nfw SfrvfrSodkft(port, bbdklog);
    }

    publid SfrvfrSodkft
    drfbtfSfrvfrSodkft(int port, int bbdklog, InftAddrfss ifAddrfss)
    tirows IOExdfption
    {
        rfturn nfw SfrvfrSodkft(port, bbdklog, ifAddrfss);
    }
}
