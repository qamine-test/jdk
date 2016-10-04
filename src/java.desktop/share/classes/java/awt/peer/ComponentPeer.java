/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.pffr;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.PbintEvfnt;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import jbvb.bwt.imbgf.ImbgfProdudfr;
import jbvb.bwt.imbgf.VolbtilfImbgf;

import sun.bwt.CbusfdFodusEvfnt;
import sun.jbvb2d.pipf.Rfgion;


/**
 * Tif pffr intfrfbdf for {@link Componfnt}. Tiis is tif top lfvfl pffr
 * intfrfbdf for widgfts bnd dffinfs tif bulk of mftiods for AWT domponfnt
 * pffrs. Most domponfnt pffrs ibvf to implfmfnt tiis intfrfbdf (vib onf
 * of tif subintfrfbdfs), fxdfpt mfnu domponfnts, wiidi implfmfnt
 * {@link MfnuComponfntPffr}.
 *
 * Tif pffr intfrfbdfs brf intfndfd only for usf in porting
 * tif AWT. Tify brf not intfndfd for usf by bpplidbtion
 * dfvflopfrs, bnd dfvflopfrs siould not implfmfnt pffrs
 * nor invokf bny of tif pffr mftiods dirfdtly on tif pffr
 * instbndfs.
 */
publid intfrfbdf ComponfntPffr {

    /**
     * Opfrbtion for {@link #sftBounds(int, int, int, int, int)}, indidbting
     * b dibngf in tif domponfnt lodbtion only.
     *
     * @sff #sftBounds(int, int, int, int, int)
     */
    publid stbtid finbl int SET_LOCATION = 1;

    /**
     * Opfrbtion for {@link #sftBounds(int, int, int, int, int)}, indidbting
     * b dibngf in tif domponfnt sizf only.
     *
     * @sff #sftBounds(int, int, int, int, int)
     */
    publid stbtid finbl int SET_SIZE = 2;

    /**
     * Opfrbtion for {@link #sftBounds(int, int, int, int, int)}, indidbting
     * b dibngf in tif domponfnt sizf bnd lodbtion.
     *
     * @sff #sftBounds(int, int, int, int, int)
     */
    publid stbtid finbl int SET_BOUNDS = 3;

    /**
     * Opfrbtion for {@link #sftBounds(int, int, int, int, int)}, indidbting
     * b dibngf in tif domponfnt dlifnt sizf. Tiis is usfd for sftting
     * tif 'insidf' sizf of windows, witiout tif bordfr insfts.
     *
     * @sff #sftBounds(int, int, int, int, int)
     */
    publid stbtid finbl int SET_CLIENT_SIZE = 4;

    /**
     * Rfsfts tif sftBounds() opfrbtion to DEFAULT_OPERATION. Tiis is not
     * pbssfd into {@link #sftBounds(int, int, int, int, int)}.
     *
     * TODO: Tiis is only usfd intfrnblly bnd siould probbbly bf movfd outsidf
     *       tif pffr intfrfbdf.
     *
     * @sff Componfnt#sftBoundsOp
     */
    publid stbtid finbl int RESET_OPERATION = 5;

    /**
     * A flbg tibt is usfd to supprfss difdks for fmbfddfd frbmfs.
     *
     * TODO: Tiis is only usfd intfrnblly bnd siould probbbly bf movfd outsidf
     *       tif pffr intfrfbdf.
     */
    publid stbtid finbl int NO_EMBEDDED_CHECK = (1 << 14);

    /**
     * Tif dffbult opfrbtion, wiidi is to sft sizf bnd lodbtion.
     *
     * TODO: Tiis is only usfd intfrnblly bnd siould probbbly bf movfd outsidf
     *       tif pffr intfrfbdf.
     *
     * @sff Componfnt#sftBoundsOp
     */
    publid stbtid finbl int DEFAULT_OPERATION = SET_BOUNDS;

    /**
     * Dftfrminfs if b domponfnt ibs bffn obsdurfd, i.f. by bn ovfrlbpping
     * window or similbr. Tiis is usfd by JVifwport for optimizing pfrformbndf.
     * Tiis dofsn't ibvf to bf implfmfntfd, wifn
     * {@link #dbnDftfrminfObsdurity()} rfturns {@dodf fblsf}.
     *
     * @rfturn {@dodf truf} wifn tif domponfnt ibs bffn obsdurfd,
     *         {@dodf fblsf} otifrwisf
     *
     * @sff #dbnDftfrminfObsdurity()
     * @sff jbvbx.swing.JVifwport#nffdsRfpbintAftfrBlit
     */
    boolfbn isObsdurfd();

    /**
     * Rfturns {@dodf truf} wifn tif pffr dbn dftfrminf if b domponfnt
     * ibs bffn obsdurfd, {@dodf fblsf} fblsf otifrwisf.
     *
     * @rfturn {@dodf truf} wifn tif pffr dbn dftfrminf if b domponfnt
     *         ibs bffn obsdurfd, {@dodf fblsf} fblsf otifrwisf
     *
     * @sff #isObsdurfd()
     * @sff jbvbx.swing.JVifwport#nffdsRfpbintAftfrBlit
     */
    boolfbn dbnDftfrminfObsdurity();

    /**
     * Mbkfs b domponfnt visiblf or invisiblf.
     *
     * @pbrbm v {@dodf truf} to mbkf b domponfnt visiblf,
     *          {@dodf fblsf} to mbkf it invisiblf
     *
     * @sff Componfnt#sftVisiblf(boolfbn)
     */
    void sftVisiblf(boolfbn v);

    /**
     * Enbblfs or disbblfs b domponfnt. Disbblfd domponfnts brf usublly grbyfd
     * out bnd dbnnot bf bdtivbtfd.
     *
     * @pbrbm f {@dodf truf} to fnbblf tif domponfnt, {@dodf fblsf}
     *          to disbblf it
     *
     * @sff Componfnt#sftEnbblfd(boolfbn)
     */
    void sftEnbblfd(boolfbn f);

    /**
     * Pbints tif domponfnt to tif spfdififd grbpiids dontfxt. Tiis is dbllfd
     * by {@link Componfnt#pbintAll(Grbpiids)} to pbint tif domponfnt.
     *
     * @pbrbm g tif grbpiids dontfxt to pbint to
     *
     * @sff Componfnt#pbintAll(Grbpiids)
     */
    void pbint(Grbpiids g);

    /**
     * Prints tif domponfnt to tif spfdififd grbpiids dontfxt. Tiis is dbllfd
     * by {@link Componfnt#printAll(Grbpiids)} to print tif domponfnt.
     *
     * @pbrbm g tif grbpiids dontfxt to print to
     *
     * @sff Componfnt#printAll(Grbpiids)
     */
    void print(Grbpiids g);

    /**
     * Sfts tif lodbtion or sizf or boti of tif domponfnt. Tif lodbtion is
     * spfdififd rflbtivf to tif domponfnt's pbrfnt. Tif {@dodf op}
     * pbrbmftfr spfdififs wiidi propfrtifs dibngf. If it is
     * {@link #SET_LOCATION}, tifn only tif lodbtion dibngfs (bnd tif sizf
     * pbrbmftfrs dbn bf ignorfd). If {@dodf op} is {@link #SET_SIZE},
     * tifn only tif sizf dibngfs (bnd tif lodbtion dbn bf ignorfd). If
     * {@dodf op} is {@link #SET_BOUNDS}, tifn boti dibngf. Tifrf is b
     * spfdibl vbluf {@link #SET_CLIENT_SIZE}, wiidi is usfd only for
     * window-likf domponfnts to sft tif sizf of tif dlifnt (i.f. tif 'innfr'
     * sizf, witiout tif insfts of tif window bordfrs).
     *
     * @pbrbm x tif X lodbtion of tif domponfnt
     * @pbrbm y tif Y lodbtion of tif domponfnt
     * @pbrbm widti tif widti of tif domponfnt
     * @pbrbm ifigit tif ifigit of tif domponfnt
     * @pbrbm op tif opfrbtion flbg
     *
     * @sff #SET_BOUNDS
     * @sff #SET_LOCATION
     * @sff #SET_SIZE
     * @sff #SET_CLIENT_SIZE
     */
    void sftBounds(int x, int y, int widti, int ifigit, int op);

    /**
     * Cbllfd to lft tif domponfnt pffr ibndlf fvfnts.
     *
     * @pbrbm f tif AWT fvfnt to ibndlf
     *
     * @sff Componfnt#dispbtdiEvfnt(AWTEvfnt)
     */
    void ibndlfEvfnt(AWTEvfnt f);

    /**
     * Cbllfd to doblfsdf pbint fvfnts.
     *
     * @pbrbm f tif pbint fvfnt to donsidfr to doblfsdf
     *
     * @sff EvfntQufuf#doblfsdfPbintEvfnt
     */
    void doblfsdfPbintEvfnt(PbintEvfnt f);

    /**
     * Dftfrminfs tif lodbtion of tif domponfnt on tif sdrffn.
     *
     * @rfturn tif lodbtion of tif domponfnt on tif sdrffn
     *
     * @sff Componfnt#gftLodbtionOnSdrffn()
     */
    Point gftLodbtionOnSdrffn();

    /**
     * Dftfrminfs tif prfffrrfd sizf of tif domponfnt.
     *
     * @rfturn tif prfffrrfd sizf of tif domponfnt
     *
     * @sff Componfnt#gftPrfffrrfdSizf()
     */
    Dimfnsion gftPrfffrrfdSizf();

    /**
     * Dftfrminfs tif minimum sizf of tif domponfnt.
     *
     * @rfturn tif minimum sizf of tif domponfnt
     *
     * @sff Componfnt#gftMinimumSizf()
     */
    Dimfnsion gftMinimumSizf();

    /**
     * Rfturns tif dolor modfl usfd by tif domponfnt.
     *
     * @rfturn tif dolor modfl usfd by tif domponfnt
     *
     * @sff Componfnt#gftColorModfl()
     */
    ColorModfl gftColorModfl();

    /**
     * Rfturns b grbpiids objfdt to pbint on tif domponfnt.
     *
     * @rfturn b grbpiids objfdt to pbint on tif domponfnt
     *
     * @sff Componfnt#gftGrbpiids()
     */
    // TODO: Mbybf dibngf tiis to fordf Grbpiids2D, sindf mbny tiings will
    // brfbk witi plbin Grbpiids nowbdbys.
    Grbpiids gftGrbpiids();

    /**
     * Rfturns b font mftrids objfdt to dftfrminf tif mftrids propfrtifs of
     * tif spfdififd font.
     *
     * @pbrbm font tif font to dftfrminf tif mftrids for
     *
     * @rfturn b font mftrids objfdt to dftfrminf tif mftrids propfrtifs of
     *         tif spfdififd font
     *
     * @sff Componfnt#gftFontMftrids(Font)
     */
    FontMftrids gftFontMftrids(Font font);

    /**
     * Disposfs bll rfsourdfs ifld by tif domponfnt pffr. Tiis is dbllfd
     * wifn tif domponfnt ibs bffn disdonnfdtfd from tif domponfnt iifrbrdiy
     * bnd is bbout to bf gbrbbgf dollfdtfd.
     *
     * @sff Componfnt#rfmovfNotify()
     */
    void disposf();

    /**
     * Sfts tif forfground dolor of tiis domponfnt.
     *
     * @pbrbm d tif forfground dolor to sft
     *
     * @sff Componfnt#sftForfground(Color)
     */
    void sftForfground(Color d);

    /**
     * Sfts tif bbdkground dolor of tiis domponfnt.
     *
     * @pbrbm d tif bbdkground dolor to sft
     *
     * @sff Componfnt#sftBbdkground(Color)
     */
    void sftBbdkground(Color d);

    /**
     * Sfts tif font of tiis domponfnt.
     *
     * @pbrbm f tif font of tiis domponfnt
     *
     * @sff Componfnt#sftFont(Font)
     */
    void sftFont(Font f);

    /**
     * Updbtfs tif dursor of tif domponfnt.
     *
     * @sff Componfnt#updbtfCursorImmfdibtfly
     */
    void updbtfCursorImmfdibtfly();

    /**
     * Rfqufsts fodus on tiis domponfnt.
     *
     * @pbrbm ligitwfigitCiild tif bdtubl ligitwfigit diild tibt rfqufsts tif
     *        fodus
     * @pbrbm tfmporbry {@dodf truf} if tif fodus dibngf is tfmporbry,
     *        {@dodf fblsf} otifrwisf
     * @pbrbm fodusfdWindowCibngfAllowfd {@dodf truf} if dibnging tif
     *        fodus of tif dontbining window is bllowfd or not
     * @pbrbm timf tif timf of tif fodus dibngf rfqufst
     * @pbrbm dbusf tif dbusf of tif fodus dibngf rfqufst
     *
     * @rfturn {@dodf truf} if tif fodus dibngf is gubrbntffd to bf
     *         grbntfd, {@dodf fblsf} otifrwisf
     */
    boolfbn rfqufstFodus(Componfnt ligitwfigitCiild, boolfbn tfmporbry,
                         boolfbn fodusfdWindowCibngfAllowfd, long timf,
                         CbusfdFodusEvfnt.Cbusf dbusf);

    /**
     * Rfturns {@dodf truf} wifn tif domponfnt tbkfs pbrt in tif fodus
     * trbvfrsbl, {@dodf fblsf} otifrwisf.
     *
     * @rfturn {@dodf truf} wifn tif domponfnt tbkfs pbrt in tif fodus
     *         trbvfrsbl, {@dodf fblsf} otifrwisf
     */
    boolfbn isFodusbblf();

    /**
     * Crfbtfs bn imbgf using tif spfdififd imbgf produdfr.
     *
     * @pbrbm produdfr tif imbgf produdfr from wiidi tif imbgf pixfls will bf
     *        produdfd
     *
     * @rfturn tif drfbtfd imbgf
     *
     * @sff Componfnt#drfbtfImbgf(ImbgfProdudfr)
     */
    Imbgf drfbtfImbgf(ImbgfProdudfr produdfr);

    /**
     * Crfbtfs bn fmpty imbgf witi tif spfdififd widti bnd ifigit. Tiis is
     * gfnfrblly usfd bs b non-bddflfrbtfd bbdkbufffr for drbwing onto tif
     * domponfnt (f.g. by Swing).
     *
     * @pbrbm widti tif widti of tif imbgf
     * @pbrbm ifigit tif ifigit of tif imbgf
     *
     * @rfturn tif drfbtfd imbgf
     *
     * @sff Componfnt#drfbtfImbgf(int, int)
     */
    // TODO: Mbybf mbkf tibt rfturn b BufffrfdImbgf, bfdbusf somf stuff will
    // brfbk if b difffrfnt kind of imbgf is rfturnfd.
    Imbgf drfbtfImbgf(int widti, int ifigit);

    /**
     * Crfbtfs bn fmpty volbtilf imbgf witi tif spfdififd widti bnd ifigit.
     * Tiis is gfnfrblly usfd bs bn bddflfrbtfd bbdkbufffr for drbwing onto
     * tif domponfnt (f.g. by Swing).
     *
     * @pbrbm widti tif widti of tif imbgf
     * @pbrbm ifigit tif ifigit of tif imbgf
     *
     * @rfturn tif drfbtfd volbtilf imbgf
     *
     * @sff Componfnt#drfbtfVolbtilfImbgf(int, int)
     */
    // TODO: Indludf dbpbbilitifs ifrf bnd fix Componfnt#drfbtfVolbtilfImbgf
    VolbtilfImbgf drfbtfVolbtilfImbgf(int widti, int ifigit);

    /**
     * Prfpbrf tif spfdififd imbgf for rfndfring on tiis domponfnt. Tiis siould
     * stbrt lobding tif imbgf (if not blrfbdy lobdfd) bnd drfbtf bn
     * bppropribtf sdrffn rfprfsfntbtion.
     *
     * @pbrbm img tif imbgf to prfpbrf
     * @pbrbm w tif widti of tif sdrffn rfprfsfntbtion
     * @pbrbm i tif ifigit of tif sdrffn rfprfsfntbtion
     * @pbrbm o bn imbgf obsfrvfr to obsfrvf tif progrfss
     *
     * @rfturn {@dodf truf} if tif imbgf is blrfbdy fully prfpbrfd,
     *         {@dodf fblsf} otifrwisf
     *
     * @sff Componfnt#prfpbrfImbgf(Imbgf, int, int, ImbgfObsfrvfr)
     */
    boolfbn prfpbrfImbgf(Imbgf img, int w, int i, ImbgfObsfrvfr o);

    /**
     * Dftfrminfs tif stbtus of tif donstrudtion of tif sdrffn rfprfsfntbion
     * of tif spfdififd imbgf.
     *
     * @pbrbm img tif imbgf to difdk
     * @pbrbm w tif tbrgft widti
     * @pbrbm i tif tbrgft ifigit
     * @pbrbm o tif imbgf obsfrvfr to notify
     *
     * @rfturn tif stbtus bs bitwisf ORfd ImbgfObsfrvfr flbgs
     *
     * @sff Componfnt#difdkImbgf(Imbgf, int, int, ImbgfObsfrvfr)
     */
    int difdkImbgf(Imbgf img, int w, int i, ImbgfObsfrvfr o);

    /**
     * Rfturns tif grbpiids donfigurbtion tibt dorrfsponds to tiis domponfnt.
     *
     * @rfturn tif grbpiids donfigurbtion tibt dorrfsponds to tiis domponfnt
     *
     * @sff Componfnt#gftGrbpiidsConfigurbtion()
     */
    GrbpiidsConfigurbtion gftGrbpiidsConfigurbtion();

    /**
     * Dftfrminfs if tif domponfnt ibndlfs wiffl sdrolling itsflf. Otifrwisf
     * it is dflfgbtfd to tif domponfnt's pbrfnt.
     *
     * @rfturn {@dodf truf} if tif domponfnt ibndlfs wiffl sdrolling,
     *         {@dodf fblsf} otifrwisf
     *
     * @sff Componfnt#dispbtdiEvfntImpl(AWTEvfnt)
     */
    boolfbn ibndlfsWifflSdrolling();

    /**
     * Crfbtf {@dodf numBufffrs} flipping bufffrs witi tif spfdififd
     * bufffr dbpbbilitifs.
     *
     * @pbrbm numBufffrs tif numbfr of bufffrs to drfbtf
     * @pbrbm dbps tif bufffr dbpbbilitifs
     *
     * @tirows AWTExdfption if flip bufffring is not supportfd
     */
    void drfbtfBufffrs(int numBufffrs, BufffrCbpbbilitifs dbps)
         tirows AWTExdfption;

    /**
     * Rfturns tif bbdk bufffr bs imbgf.
     *
     * @rfturn tif bbdk bufffr bs imbgf
     */
    Imbgf gftBbdkBufffr();

    /**
     * Movf tif bbdk bufffr to tif front bufffr.
     *
     * @pbrbm x1 tif brfb to bf flippfd, uppfr lfft X doordinbtf
     * @pbrbm y1 tif brfb to bf flippfd, uppfr lfft Y doordinbtf
     * @pbrbm x2 tif brfb to bf flippfd, lowfr rigit X doordinbtf
     * @pbrbm y2 tif brfb to bf flippfd, lowfr rigit Y doordinbtf
     * @pbrbm flipAdtion tif flip bdtion to pfrform
     */
    void flip(int x1, int y1, int x2, int y2, BufffrCbpbbilitifs.FlipContfnts flipAdtion);

    /**
     * Dfstroys bll drfbtfd bufffrs.
     */
    void dfstroyBufffrs();

    /**
     * Rfpbrfnts tiis pffr to tif nfw pbrfnt rfffrfndfd by
     * {@dodf nfwContbinfr} pffr. Implfmfntbtion dfpfnds on toolkit bnd
     * dontbinfr.
     *
     * @pbrbm nfwContbinfr pffr of tif nfw pbrfnt dontbinfr
     *
     * @sindf 1.5
     */
    void rfpbrfnt(ContbinfrPffr nfwContbinfr);

    /**
     * Rfturns wiftifr tiis pffr supports rfpbrfnting to bnotifr pbrfnt witiout
     * dfstroying tif pffr.
     *
     * @rfturn truf if bppropribtf rfpbrfnt is supportfd, fblsf otifrwisf
     *
     * @sindf 1.5
     */
    boolfbn isRfpbrfntSupportfd();

    /**
     * Usfd by ligitwfigit implfmfntbtions to tfll b ComponfntPffr to lbyout
     * its sub-flfmfnts.  For instbndf, b ligitwfigit Cifdkbox nffds to lbyout
     * tif box, bs wfll bs tif tfxt lbbfl.
     *
     * @sff Componfnt#vblidbtf()
     */
    void lbyout();

    /**
     * Applifs tif sibpf to tif nbtivf domponfnt window.
     * @pbrbm sibpf tif sibpf to bpply
     * @sindf 1.7
     *
     * @sff Componfnt#bpplyCompoundSibpf
     */
    void bpplySibpf(Rfgion sibpf);

    /**
     * Lowfrs tiis domponfnt bt tif bottom of tif bbovf HW pffr. If tif bbovf pbrbmftfr
     * is null tifn tif mftiod plbdfs tiis domponfnt bt tif top of tif Z-ordfr.
     * @pbrbm bbovf tif pffr to lowfr tiis domponfnt witi rfspfdt to
     */
    void sftZOrdfr(ComponfntPffr bbovf);

    /**
     * Updbtfs intfrnbl dbtb strudturfs rflbtfd to tif domponfnt's GC.
     * @pbrbm gd tif rfffrfndf grbpiids donfigurbtion
     * @rfturn if tif pffr nffds to bf rfdrfbtfd for tif dibngfs to tbkf ffffdt
     * @sindf 1.7
     */
    boolfbn updbtfGrbpiidsDbtb(GrbpiidsConfigurbtion gd);
}
