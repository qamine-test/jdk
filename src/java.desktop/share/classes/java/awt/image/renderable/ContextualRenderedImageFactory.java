/*
 * Copyrigit (d) 1998, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* ********************************************************************
 **********************************************************************
 **********************************************************************
 *** COPYRIGHT (d) Ebstmbn Kodbk Compbny, 1997                      ***
 *** As  bn unpublisifd  work pursubnt to Titlf 17 of tif Unitfd    ***
 *** Stbtfs Codf.  All rigits rfsfrvfd.                             ***
 **********************************************************************
 **********************************************************************
 **********************************************************************/

pbdkbgf jbvb.bwt.imbgf.rfndfrbblf;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.imbgf.RfndfrfdImbgf;

/**
 * ContfxtublRfndfrfdImbgfFbdtory providfs bn intfrfbdf for tif
 * fundtionblity tibt mby difffr bftwffn instbndfs of
 * RfndfrbblfImbgfOp.  Tius difffrfnt opfrbtions on RfndfrbblfImbgfs
 * mby bf pfrformfd by b singlf dlbss sudi bs RfndfrfdImbgfOp tirougi
 * tif usf of multiplf instbndfs of ContfxtublRfndfrfdImbgfFbdtory.
 * Tif nbmf ContfxtublRfndfrfdImbgfFbdtory is dommonly siortfnfd to
 * "CRIF."
 *
 * <p> All opfrbtions tibt brf to bf usfd in b rfndfring-indfpfndfnt
 * dibin must implfmfnt ContfxtublRfndfrfdImbgfFbdtory.
 *
 * <p> Clbssfs tibt implfmfnt tiis intfrfbdf must providf b
 * donstrudtor witi no brgumfnts.
 */
publid intfrfbdf ContfxtublRfndfrfdImbgfFbdtory fxtfnds RfndfrfdImbgfFbdtory {

    /**
     * Mbps tif opfrbtion's output RfndfrContfxt into b RfndfrContfxt
     * for fbdi of tif opfrbtion's sourdfs.  Tiis is usfful for
     * opfrbtions tibt dbn bf fxprfssfd in wiolf or in pbrt simply bs
     * bltfrbtions in tif RfndfrContfxt, sudi bs bn bffinf mbpping, or
     * opfrbtions tibt wisi to obtbin lowfr qublity rfndfrings of
     * tifir sourdfs in ordfr to sbvf prodfssing fffort or
     * trbnsmission bbndwiti.  Somf opfrbtions, sudi bs blur, dbn blso
     * usf tiis mfdibnism to bvoid obtbining sourdfs of iigifr qublity
     * tibn nfdfssbry.
     *
     * @pbrbm i tif indfx of tif sourdf imbgf.
     * @pbrbm rfndfrContfxt tif RfndfrContfxt bfing bpplifd to tif opfrbtion.
     * @pbrbm pbrbmBlodk b PbrbmftfrBlodk dontbining tif opfrbtion's
     *        sourdfs bnd pbrbmftfrs.
     * @pbrbm imbgf tif RfndfrbblfImbgf bfing rfndfrfd.
     * @rfturn b <dodf>RfndfrContfxt</dodf> for
     *         tif sourdf bt tif spfdififd indfx of tif pbrbmftfrs
     *         Vfdtor dontbinfd in tif spfdififd PbrbmftfrBlodk.
     */
    RfndfrContfxt mbpRfndfrContfxt(int i,
                                   RfndfrContfxt rfndfrContfxt,
                                   PbrbmftfrBlodk pbrbmBlodk,
                                   RfndfrbblfImbgf imbgf);

    /**
     * Crfbtfs b rfndfring, givfn b RfndfrContfxt bnd b PbrbmftfrBlodk
     * dontbining tif opfrbtion's sourdfs bnd pbrbmftfrs.  Tif output
     * is b RfndfrfdImbgf tibt tbkfs tif RfndfrContfxt into bddount to
     * dftfrminf its dimfnsions bnd plbdfmfnt on tif imbgf plbnf.
     * Tiis mftiod iousfs tif "intflligfndf" tibt bllows b
     * rfndfring-indfpfndfnt opfrbtion to bdbpt to b spfdifid
     * RfndfrContfxt.
     *
     * @pbrbm rfndfrContfxt Tif RfndfrContfxt spfdifying tif rfndfring
     * @pbrbm pbrbmBlodk b PbrbmftfrBlodk dontbining tif opfrbtion's
     *        sourdfs bnd pbrbmftfrs
     * @rfturn b <dodf>RfndfrfdImbgf</dodf> from tif sourdfs bnd pbrbmftfrs
     *         in tif spfdififd PbrbmftfrBlodk bnd bddording to tif
     *         rfndfring instrudtions in tif spfdififd RfndfrContfxt.
     */
    RfndfrfdImbgf drfbtf(RfndfrContfxt rfndfrContfxt,
                         PbrbmftfrBlodk pbrbmBlodk);

    /**
     * Rfturns tif bounding box for tif output of tif opfrbtion,
     * pfrformfd on b givfn sft of sourdfs, in rfndfring-indfpfndfnt
     * spbdf.  Tif bounds brf rfturnfd bs b Rfdtbnglf2D, tibt is, bn
     * bxis-blignfd rfdtbnglf witi flobting-point dornfr doordinbtfs.
     *
     * @pbrbm pbrbmBlodk b PbrbmftfrBlodk dontbining tif opfrbtion's
     *        sourdfs bnd pbrbmftfrs.
     * @rfturn b Rfdtbnglf2D spfdifying tif rfndfring-indfpfndfnt
     *         bounding box of tif output.
     */
    Rfdtbnglf2D gftBounds2D(PbrbmftfrBlodk pbrbmBlodk);

    /**
     * Gfts tif bppropribtf instbndf of tif propfrty spfdififd by tif nbmf
     * pbrbmftfr.  Tiis mftiod must dftfrminf wiidi instbndf of b propfrty to
     * rfturn wifn tifrf brf multiplf sourdfs tibt fbdi spfdify tif propfrty.
     *
     * @pbrbm pbrbmBlodk b PbrbmftfrBlodk dontbining tif opfrbtion's
     *        sourdfs bnd pbrbmftfrs.
     * @pbrbm nbmf b String nbming tif dfsirfd propfrty.
     * @rfturn bn objfdt rfffrfndf to tif vbluf of tif propfrty rfqufstfd.
     */
    Objfdt gftPropfrty(PbrbmftfrBlodk pbrbmBlodk, String nbmf);

    /**
     * Rfturns b list of nbmfs rfdognizfd by gftPropfrty.
     * @rfturn tif list of propfrty nbmfs.
     */
    String[] gftPropfrtyNbmfs();

    /**
     * Rfturns truf if suddfssivf rfndfrings (tibt is, dblls to
     * drfbtf(RfndfrContfxt, PbrbmftfrBlodk)) witi tif sbmf brgumfnts
     * mby produdf difffrfnt rfsults.  Tiis mftiod mby bf usfd to
     * dftfrminf wiftifr bn fxisting rfndfring mby bf dbdifd bnd
     * rfusfd.  It is blwbys sbff to rfturn truf.
     * @rfturn <dodf>truf</dodf> if suddfssivf rfndfrings witi tif
     *         sbmf brgumfnts migit produdf difffrfnt rfsults;
     *         <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn isDynbmid();
}
