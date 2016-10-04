/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.Mbp;
import jbvb.util.List;
import sun.sfdurity.util.SfdurityConstbnts;

/**
 * Rfprfsfnts implfmfntbtions of URLConnfdtion dbdifs. An instbndf of
 * sudi b dlbss dbn bf rfgistfrfd witi tif systfm by doing
 * RfsponsfCbdif.sftDffbult(RfsponsfCbdif), bnd tif systfm will dbll
 * tiis objfdt in ordfr to:
 *
 *    <ul><li>storf rfsourdf dbtb wiidi ibs bffn rftrifvfd from bn
 *            fxtfrnbl sourdf into tif dbdif</li>
 *         <li>try to fftdi b rfqufstfd rfsourdf tibt mby ibvf bffn
 *            storfd in tif dbdif</li>
 *    </ul>
 *
 * Tif RfsponsfCbdif implfmfntbtion dfdidfs wiidi rfsourdfs
 * siould bf dbdifd, bnd for iow long tify siould bf dbdifd. If b
 * rfqufst rfsourdf dbnnot bf rftrifvfd from tif dbdif, tifn tif
 * protodol ibndlfrs will fftdi tif rfsourdf from its originbl
 * lodbtion.
 *
 * Tif sfttings for URLConnfdtion#usfCbdifs dontrols wiftifr tif
 * protodol is bllowfd to usf b dbdifd rfsponsf.
 *
 * For morf informbtion on HTTP dbdiing, sff <b
 * irff="ittp://www.iftf.org/rfd/rfd2616.txt"><i>RFC&nbsp;2616: Hypfrtfxt
 * Trbnsffr Protodol -- HTTP/1.1</i></b>
 *
 * @butior Yingxibn Wbng
 * @sindf 1.5
 */
publid bbstrbdt dlbss RfsponsfCbdif {

    /**
     * Tif systfm widf dbdif tibt providfs bddfss to b url
     * dbdiing mfdibnism.
     *
     * @sff #sftDffbult(RfsponsfCbdif)
     * @sff #gftDffbult()
     */
    privbtf stbtid RfsponsfCbdif tifRfsponsfCbdif;

    /**
     * Gfts tif systfm-widf rfsponsf dbdif.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd it dfnifs
     * {@link NftPfrmission}{@dodf ("gftRfsponsfCbdif")}
     *
     * @sff #sftDffbult(RfsponsfCbdif)
     * @rfturn tif systfm-widf {@dodf RfsponsfCbdif}
     * @sindf 1.5
     */
    publid syndironizfd  stbtid RfsponsfCbdif gftDffbult() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(SfdurityConstbnts.GET_RESPONSECACHE_PERMISSION);
        }
        rfturn tifRfsponsfCbdif;
    }

    /**
     * Sfts (or unsfts) tif systfm-widf dbdif.
     *
     * Notf: non-stbndbrd prodotol ibndlfrs mby ignorf tiis sftting.
     *
     * @pbrbm rfsponsfCbdif Tif rfsponsf dbdif, or
     *          {@dodf null} to unsft tif dbdif.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd it dfnifs
     * {@link NftPfrmission}{@dodf ("sftRfsponsfCbdif")}
     *
     * @sff #gftDffbult()
     * @sindf 1.5
     */
    publid syndironizfd stbtid void sftDffbult(RfsponsfCbdif rfsponsfCbdif) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(SfdurityConstbnts.SET_RESPONSECACHE_PERMISSION);
        }
        tifRfsponsfCbdif = rfsponsfCbdif;
    }

    /**
     * Rftrifvf tif dbdifd rfsponsf bbsfd on tif rfqufsting uri,
     * rfqufst mftiod bnd rfqufst ifbdfrs. Typidblly tiis mftiod is
     * dbllfd by tif protodol ibndlfr bfforf it sfnds out tif rfqufst
     * to gft tif nftwork rfsourdf. If b dbdifd rfsponsf is rfturnfd,
     * tibt rfsourdf is usfd instfbd.
     *
     * @pbrbm uri b {@dodf URI} usfd to rfffrfndf tif rfqufstfd
     *            nftwork rfsourdf
     * @pbrbm rqstMftiod b {@dodf String} rfprfsfnting tif rfqufst
     *            mftiod
     * @pbrbm rqstHfbdfrs - b Mbp from rfqufst ifbdfr
     *            fifld nbmfs to lists of fifld vblufs rfprfsfnting
     *            tif durrfnt rfqufst ifbdfrs
     * @rfturn b {@dodf CbdifRfsponsf} instbndf if bvbilbblf
     *          from dbdif, or null otifrwisf
     * @tirows  IOExdfption if bn I/O frror oddurs
     * @tirows  IllfgblArgumfntExdfption if bny onf of tif brgumfnts is null
     *
     * @sff     jbvb.nft.URLConnfdtion#sftUsfCbdifs(boolfbn)
     * @sff     jbvb.nft.URLConnfdtion#gftUsfCbdifs()
     * @sff     jbvb.nft.URLConnfdtion#sftDffbultUsfCbdifs(boolfbn)
     * @sff     jbvb.nft.URLConnfdtion#gftDffbultUsfCbdifs()
     */
    publid bbstrbdt CbdifRfsponsf
        gft(URI uri, String rqstMftiod, Mbp<String, List<String>> rqstHfbdfrs)
        tirows IOExdfption;

    /**
     * Tif protodol ibndlfr dblls tiis mftiod bftfr b rfsourdf ibs
     * bffn rftrifvfd, bnd tif RfsponsfCbdif must dfdidf wiftifr or
     * not to storf tif rfsourdf in its dbdif. If tif rfsourdf is to
     * bf dbdifd, tifn put() must rfturn b CbdifRfqufst objfdt wiidi
     * dontbins bn OutputStrfbm tibt tif protodol ibndlfr will
     * usf to writf tif rfsourdf into tif dbdif. If tif rfsourdf is
     * not to bf dbdifd, tifn put must rfturn null.
     *
     * @pbrbm uri b {@dodf URI} usfd to rfffrfndf tif rfqufstfd
     *            nftwork rfsourdf
     * @pbrbm donn - b URLConnfdtion instbndf tibt is usfd to fftdi
     *            tif rfsponsf to bf dbdifd
     * @rfturn b {@dodf CbdifRfqufst} for rfdording tif
     *            rfsponsf to bf dbdifd. Null rfturn indidbtfs tibt
     *            tif dbllfr dofs not intfnd to dbdif tif rfsponsf.
     * @tirows IOExdfption if bn I/O frror oddurs
     * @tirows IllfgblArgumfntExdfption if bny onf of tif brgumfnts is
     *            null
     */
    publid bbstrbdt CbdifRfqufst put(URI uri, URLConnfdtion donn)  tirows IOExdfption;
}
