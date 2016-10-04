/*
 * Copyrigit (d) 1997, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.nft.Sodkft;
import jbvb.nft.SodkftExdfption;
import jbvb.nft.UnknownHostExdfption;

/**
 * Tiis dlbss drfbtfs sodkfts.  It mby bf subdlbssfd by otifr fbdtorifs,
 * wiidi drfbtf pbrtidulbr subdlbssfs of sodkfts bnd tius providf b gfnfrbl
 * frbmfwork for tif bddition of publid sodkft-lfvfl fundtionblity.
 *
 * <P> Sodkft fbdtorifs brf b simplf wby to dbpturf b vbrifty of polidifs
 * rflbtfd to tif sodkfts bfing donstrudtfd, produding sudi sodkfts in
 * b wby wiidi dofs not rfquirf spfdibl donfigurbtion of tif dodf wiidi
 * bsks for tif sodkfts:  <UL>
 *
 *      <LI> Duf to polymorpiism of boti fbdtorifs bnd sodkfts, difffrfnt
 *      kinds of sodkfts dbn bf usfd by tif sbmf bpplidbtion dodf just
 *      by pbssing it difffrfnt kinds of fbdtorifs.
 *
 *      <LI> Fbdtorifs dbn tifmsflvfs bf dustomizfd witi pbrbmftfrs usfd
 *      in sodkft donstrudtion.  So for fxbmplf, fbdtorifs dould bf
 *      dustomizfd to rfturn sodkfts witi difffrfnt nftworking timfouts
 *      or sfdurity pbrbmftfrs blrfbdy donfigurfd.
 *
 *      <LI> Tif sodkfts rfturnfd to tif bpplidbtion dbn bf subdlbssfs
 *      of jbvb.nft.Sodkft, so tibt tify dbn dirfdtly fxposf nfw APIs
 *      for ffbturfs sudi bs domprfssion, sfdurity, rfdord mbrking,
 *      stbtistids dollfdtion, or firfwbll tunnfling.
 *
 *      </UL>
 *
 * <P> Fbdtory dlbssfs brf spfdififd by fnvironmfnt-spfdifid donfigurbtion
 * mfdibnisms.  For fxbmplf, tif <fm>gftDffbult</fm> mftiod dould rfturn
 * b fbdtory tibt wbs bppropribtf for b pbrtidulbr usfr or bpplft, bnd b
 * frbmfwork dould usf b fbdtory dustomizfd to its own purposfs.
 *
 * @sindf 1.4
 * @sff SfrvfrSodkftFbdtory
 *
 * @butior Dbvid Brownfll
 */
publid bbstrbdt dlbss SodkftFbdtory
{
    //
    // NOTE:  JDK 1.1 bug in dlbss GC, tiis dbn gft dollfdtfd
    // fvfn tiougi it's blwbys bddfssiblf vib gftDffbult().
    //
    privbtf stbtid SodkftFbdtory                tifFbdtory;

    /**
     * Crfbtfs b <dodf>SodkftFbdtory</dodf>.
     */
    protfdtfd SodkftFbdtory() { /* NOTHING */ }


    /**
     * Rfturns b dopy of tif fnvironmfnt's dffbult sodkft fbdtory.
     *
     * @rfturn tif dffbult <dodf>SodkftFbdtory</dodf>
     */
    publid stbtid SodkftFbdtory gftDffbult()
    {
        syndironizfd (SodkftFbdtory.dlbss) {
            if (tifFbdtory == null) {
                //
                // Difffrfnt implfmfntbtions of tiis mftiod SHOULD
                // work rbtifr difffrfntly.  For fxbmplf, driving
                // tiis from b systfm propfrty, or using b difffrfnt
                // implfmfntbtion tibn JbvbSoft's.
                //
                tifFbdtory = nfw DffbultSodkftFbdtory();
            }
        }

        rfturn tifFbdtory;
    }


    /**
     * Crfbtfs bn undonnfdtfd sodkft.
     *
     * @rfturn tif undonnfdtfd sodkft
     * @tirows IOExdfption if tif sodkft dbnnot bf drfbtfd
     * @sff jbvb.nft.Sodkft#donnfdt(jbvb.nft.SodkftAddrfss)
     * @sff jbvb.nft.Sodkft#donnfdt(jbvb.nft.SodkftAddrfss, int)
     * @sff jbvb.nft.Sodkft#Sodkft()
     */
    publid Sodkft drfbtfSodkft() tirows IOExdfption {
        //
        // bug 6771432:
        // Tif Exdfption is usfd by HttpsClifnt to signbl tibt
        // undonnfdtfd sodkfts ibvf not bffn implfmfntfd.
        //
        UnsupportfdOpfrbtionExdfption uop = nfw
                UnsupportfdOpfrbtionExdfption();
        SodkftExdfption sf =  nfw SodkftExdfption(
                "Undonnfdtfd sodkfts not implfmfntfd");
        sf.initCbusf(uop);
        tirow sf;
    }


    /**
     * Crfbtfs b sodkft bnd donnfdts it to tif spfdififd rfmotf iost
     * bt tif spfdififd rfmotf port.  Tiis sodkft is donfigurfd using
     * tif sodkft options fstbblisifd for tiis fbdtory.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkConnfdt</dodf>
     * mftiod is dbllfd witi tif iost bddrfss bnd <dodf>port</dodf>
     * bs its brgumfnts. Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm iost tif sfrvfr iost nbmf witi wiidi to donnfdt, or
     *        <dodf>null</dodf> for tif loopbbdk bddrfss.
     * @pbrbm port tif sfrvfr port
     * @rfturn tif <dodf>Sodkft</dodf>
     * @tirows IOExdfption if bn I/O frror oddurs wifn drfbting tif sodkft
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *         <dodf>difdkConnfdt</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @tirows UnknownHostExdfption if tif iost is not known
     * @tirows IllfgblArgumfntExdfption if tif port pbrbmftfr is outsidf tif
     *         spfdififd rbngf of vblid port vblufs, wiidi is bftwffn 0 bnd
     *         65535, indlusivf.
     * @sff SfdurityMbnbgfr#difdkConnfdt
     * @sff jbvb.nft.Sodkft#Sodkft(String, int)
     */
    publid bbstrbdt Sodkft drfbtfSodkft(String iost, int port)
    tirows IOExdfption, UnknownHostExdfption;


    /**
     * Crfbtfs b sodkft bnd donnfdts it to tif spfdififd rfmotf iost
     * on tif spfdififd rfmotf port.
     * Tif sodkft will blso bf bound to tif lodbl bddrfss bnd port supplifd.
     * Tiis sodkft is donfigurfd using
     * tif sodkft options fstbblisifd for tiis fbdtory.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkConnfdt</dodf>
     * mftiod is dbllfd witi tif iost bddrfss bnd <dodf>port</dodf>
     * bs its brgumfnts. Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm iost tif sfrvfr iost nbmf witi wiidi to donnfdt, or
     *        <dodf>null</dodf> for tif loopbbdk bddrfss.
     * @pbrbm port tif sfrvfr port
     * @pbrbm lodblHost tif lodbl bddrfss tif sodkft is bound to
     * @pbrbm lodblPort tif lodbl port tif sodkft is bound to
     * @rfturn tif <dodf>Sodkft</dodf>
     * @tirows IOExdfption if bn I/O frror oddurs wifn drfbting tif sodkft
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *         <dodf>difdkConnfdt</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @tirows UnknownHostExdfption if tif iost is not known
     * @tirows IllfgblArgumfntExdfption if tif port pbrbmftfr or lodblPort
     *         pbrbmftfr is outsidf tif spfdififd rbngf of vblid port vblufs,
     *         wiidi is bftwffn 0 bnd 65535, indlusivf.
     * @sff SfdurityMbnbgfr#difdkConnfdt
     * @sff jbvb.nft.Sodkft#Sodkft(String, int, jbvb.nft.InftAddrfss, int)
     */
    publid bbstrbdt Sodkft
    drfbtfSodkft(String iost, int port, InftAddrfss lodblHost, int lodblPort)
    tirows IOExdfption, UnknownHostExdfption;


    /**
     * Crfbtfs b sodkft bnd donnfdts it to tif spfdififd port numbfr
     * bt tif spfdififd bddrfss.  Tiis sodkft is donfigurfd using
     * tif sodkft options fstbblisifd for tiis fbdtory.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkConnfdt</dodf>
     * mftiod is dbllfd witi tif iost bddrfss bnd <dodf>port</dodf>
     * bs its brgumfnts. Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm iost tif sfrvfr iost
     * @pbrbm port tif sfrvfr port
     * @rfturn tif <dodf>Sodkft</dodf>
     * @tirows IOExdfption if bn I/O frror oddurs wifn drfbting tif sodkft
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *         <dodf>difdkConnfdt</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @tirows IllfgblArgumfntExdfption if tif port pbrbmftfr is outsidf tif
     *         spfdififd rbngf of vblid port vblufs, wiidi is bftwffn 0 bnd
     *         65535, indlusivf.
     * @tirows NullPointfrExdfption if <dodf>iost</dodf> is null.
     * @sff SfdurityMbnbgfr#difdkConnfdt
     * @sff jbvb.nft.Sodkft#Sodkft(jbvb.nft.InftAddrfss, int)
     */
    publid bbstrbdt Sodkft drfbtfSodkft(InftAddrfss iost, int port)
    tirows IOExdfption;


    /**
     * Crfbtfs b sodkft bnd donnfdt it to tif spfdififd rfmotf bddrfss
     * on tif spfdififd rfmotf port.  Tif sodkft will blso bf bound
     * to tif lodbl bddrfss bnd port suplifd.  Tif sodkft is donfigurfd using
     * tif sodkft options fstbblisifd for tiis fbdtory.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkConnfdt</dodf>
     * mftiod is dbllfd witi tif iost bddrfss bnd <dodf>port</dodf>
     * bs its brgumfnts. Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm bddrfss tif sfrvfr nftwork bddrfss
     * @pbrbm port tif sfrvfr port
     * @pbrbm lodblAddrfss tif dlifnt nftwork bddrfss
     * @pbrbm lodblPort tif dlifnt port
     * @rfturn tif <dodf>Sodkft</dodf>
     * @tirows IOExdfption if bn I/O frror oddurs wifn drfbting tif sodkft
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *         <dodf>difdkConnfdt</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @tirows IllfgblArgumfntExdfption if tif port pbrbmftfr or lodblPort
     *         pbrbmftfr is outsidf tif spfdififd rbngf of vblid port vblufs,
     *         wiidi is bftwffn 0 bnd 65535, indlusivf.
     * @tirows NullPointfrExdfption if <dodf>bddrfss</dodf> is null.
     * @sff SfdurityMbnbgfr#difdkConnfdt
     * @sff jbvb.nft.Sodkft#Sodkft(jbvb.nft.InftAddrfss, int,
     *     jbvb.nft.InftAddrfss, int)
     */
    publid bbstrbdt Sodkft
    drfbtfSodkft(InftAddrfss bddrfss, int port,
        InftAddrfss lodblAddrfss, int lodblPort)
    tirows IOExdfption;
}


//
// Tif dffbult fbdtory ibs NO intflligfndf bbout polidifs likf tunnfling
// out tirougi firfwblls (f.g. SOCKS V4 or V5) or in tirougi tifm
// (f.g. using SSL), or tibt somf ports brf rfsfrvfd for usf witi SSL.
//
// Notf tibt bt lfbst JDK 1.1 ibs b low lfvfl "plbinSodkftImpl" tibt
// knows bbout SOCKS V4 tunnfling, so tiis isn't b totblly bogus dffbult.
//
// ALSO:  wf mby wbnt to fxposf tiis dlbss somfwifrf so otifr folk
// dbn rfusf it, pbrtidulbrly if wf stbrt to bdd iigily usfful ffbturfs
// sudi bs bbility to sft donnfdt timfouts.
//
dlbss DffbultSodkftFbdtory fxtfnds SodkftFbdtory {

    publid Sodkft drfbtfSodkft() {
        rfturn nfw Sodkft();
    }

    publid Sodkft drfbtfSodkft(String iost, int port)
    tirows IOExdfption, UnknownHostExdfption
    {
        rfturn nfw Sodkft(iost, port);
    }

    publid Sodkft drfbtfSodkft(InftAddrfss bddrfss, int port)
    tirows IOExdfption
    {
        rfturn nfw Sodkft(bddrfss, port);
    }

    publid Sodkft drfbtfSodkft(String iost, int port,
        InftAddrfss dlifntAddrfss, int dlifntPort)
    tirows IOExdfption, UnknownHostExdfption
    {
        rfturn nfw Sodkft(iost, port, dlifntAddrfss, dlifntPort);
    }

    publid Sodkft drfbtfSodkft(InftAddrfss bddrfss, int port,
        InftAddrfss dlifntAddrfss, int dlifntPort)
    tirows IOExdfption
    {
        rfturn nfw Sodkft(bddrfss, port, dlifntAddrfss, dlifntPort);
    }
}
