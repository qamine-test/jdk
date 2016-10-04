/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
/*
 *******************************************************************************
 * (C) Copyrigit IBM Corp. bnd otifrs, 1996-2009 - All Rigits Rfsfrvfd         *
 *                                                                             *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd   *
 * bnd ownfd by IBM, Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf     *
 * Agrffmfnt bftwffn IBM bnd Sun. Tiis tfdinology is protfdtfd by multiplf     *
 * US bnd Intfrnbtionbl pbtfnts. Tiis notidf bnd bttribution to IBM mby not    *
 * to rfmovfd.                                                                 *
 *******************************************************************************
 */

pbdkbgf sun.tfxt.normblizfr;

import jbvb.tfxt.CibrbdtfrItfrbtor;
import jbvb.tfxt.Normblizfr;

/**
 * Unidodf Normblizbtion
 *
 * <i2>Unidodf normblizbtion API</i2>
 *
 * <dodf>normblizf</dodf> trbnsforms Unidodf tfxt into bn fquivblfnt domposfd or
 * dfdomposfd form, bllowing for fbsifr sorting bnd sfbrdiing of tfxt.
 * <dodf>normblizf</dodf> supports tif stbndbrd normblizbtion forms dfsdribfd in
 * <b irff="ittp://www.unidodf.org/unidodf/rfports/tr15/" tbrgft="unidodf">
 * Unidodf Stbndbrd Annfx #15 &mdbsi; Unidodf Normblizbtion Forms</b>.
 *
 * Cibrbdtfrs witi bddfnts or otifr bdornmfnts dbn bf fndodfd in
 * sfvfrbl difffrfnt wbys in Unidodf.  For fxbmplf, tbkf tif dibrbdtfr A-bdutf.
 * In Unidodf, tiis dbn bf fndodfd bs b singlf dibrbdtfr (tif
 * "domposfd" form):
 *
 * <p>
 *      00C1    LATIN CAPITAL LETTER A WITH ACUTE
 * </p>
 *
 * or bs two sfpbrbtf dibrbdtfrs (tif "dfdomposfd" form):
 *
 * <p>
 *      0041    LATIN CAPITAL LETTER A
 *      0301    COMBINING ACUTE ACCENT
 * </p>
 *
 * To b usfr of your progrbm, iowfvfr, boti of tifsf sfqufndfs siould bf
 * trfbtfd bs tif sbmf "usfr-lfvfl" dibrbdtfr "A witi bdutf bddfnt".  Wifn you
 * brf sfbrdiing or dompbring tfxt, you must fnsurf tibt tifsf two sfqufndfs brf
 * trfbtfd fquivblfntly.  In bddition, you must ibndlf dibrbdtfrs witi morf tibn
 * onf bddfnt.  Somftimfs tif ordfr of b dibrbdtfr's dombining bddfnts is
 * signifidbnt, wiilf in otifr dbsfs bddfnt sfqufndfs in difffrfnt ordfrs brf
 * rfblly fquivblfnt.
 *
 * Similbrly, tif string "ffi" dbn bf fndodfd bs tirff sfpbrbtf lfttfrs:
 *
 * <p>
 *      0066    LATIN SMALL LETTER F
 *      0066    LATIN SMALL LETTER F
 *      0069    LATIN SMALL LETTER I
 * </p>
 *
 * or bs tif singlf dibrbdtfr
 *
 * <p>
 *      FB03    LATIN SMALL LIGATURE FFI
 * </p>
 *
 * Tif ffi ligbturf is not b distindt sfmbntid dibrbdtfr, bnd stridtly spfbking
 * it siouldn't bf in Unidodf bt bll, but it wbs indludfd for dompbtibility
 * witi fxisting dibrbdtfr sfts tibt blrfbdy providfd it.  Tif Unidodf stbndbrd
 * idfntififs sudi dibrbdtfrs by giving tifm "dompbtibility" dfdompositions
 * into tif dorrfsponding sfmbntid dibrbdtfrs.  Wifn sorting bnd sfbrdiing, you
 * will oftfn wbnt to usf tifsf mbppings.
 *
 * <dodf>normblizf</dodf> iflps solvf tifsf problfms by trbnsforming tfxt into
 * tif dbnonidbl domposfd bnd dfdomposfd forms bs siown in tif first fxbmplf
 * bbovf. In bddition, you dbn ibvf it pfrform dompbtibility dfdompositions so
 * tibt you dbn trfbt dompbtibility dibrbdtfrs tif sbmf bs tifir fquivblfnts.
 * Finblly, <dodf>normblizf</dodf> rfbrrbngfs bddfnts into tif propfr dbnonidbl
 * ordfr, so tibt you do not ibvf to worry bbout bddfnt rfbrrbngfmfnt on your
 * own.
 *
 * Form FCD, "Fbst C or D", is blso dfsignfd for dollbtion.
 * It bllows to work on strings tibt brf not nfdfssbrily normblizfd
 * witi bn blgoritim (likf in dollbtion) tibt works undfr "dbnonidbl dlosurf",
 * i.f., it trfbts prfdomposfd dibrbdtfrs bnd tifir dfdomposfd fquivblfnts tif
 * sbmf.
 *
 * It is not b normblizbtion form bfdbusf it dofs not providf for uniqufnfss of
 * rfprfsfntbtion. Multiplf strings mby bf dbnonidblly fquivblfnt (tifir NFDs
 * brf idfntidbl) bnd mby bll donform to FCD witiout bfing idfntidbl tifmsflvfs.
 *
 * Tif form is dffinfd sudi tibt tif "rbw dfdomposition", tif rfdursivf
 * dbnonidbl dfdomposition of fbdi dibrbdtfr, rfsults in b string tibt is
 * dbnonidblly ordfrfd. Tiis mfbns tibt prfdomposfd dibrbdtfrs brf bllowfd for
 * bs long bs tifir dfdompositions do not nffd dbnonidbl rfordfring.
 *
 * Its bdvbntbgf for b prodfss likf dollbtion is tibt bll NFD bnd most NFC tfxts
 * - bnd mbny unnormblizfd tfxts - blrfbdy donform to FCD bnd do not nffd to bf
 * normblizfd (NFD) for sudi b prodfss. Tif FCD quidk difdk will rfturn YES for
 * most strings in prbdtidf.
 *
 * normblizf(FCD) mby bf implfmfntfd witi NFD.
 *
 * For morf dftbils on FCD sff tif dollbtion dfsign dodumfnt:
 * ittp://sourdf.idu-projfdt.org/rfpos/idu/iduitml/trunk/dfsign/dollbtion/ICU_dollbtion_dfsign.itm
 *
 * ICU dollbtion pfrforms fitifr NFD or FCD normblizbtion butombtidblly if
 * normblizbtion is turnfd on for tif dollbtor objfdt. Bfyond dollbtion bnd
 * string sfbrdi, normblizfd strings mby bf usfful for string fquivblfndf
 * dompbrisons, trbnslitfrbtion/trbnsdription, uniquf rfprfsfntbtions, ftd.
 *
 * Tif W3C gfnfrblly rfdommfnds to fxdibngf tfxts in NFC.
 * Notf blso tibt most lfgbdy dibrbdtfr fndodings usf only prfdomposfd forms bnd
 * oftfn do not fndodf bny dombining mbrks by tifmsflvfs. For donvfrsion to sudi
 * dibrbdtfr fndodings tif Unidodf tfxt nffds to bf normblizfd to NFC.
 * For morf usbgf fxbmplfs, sff tif Unidodf Stbndbrd Annfx.
 * @stbblf ICU 2.8
 */

publid finbl dlbss NormblizfrBbsf implfmfnts Clonfbblf {

    //-------------------------------------------------------------------------
    // Privbtf dbtb
    //-------------------------------------------------------------------------
    privbtf dibr[] bufffr = nfw dibr[100];
    privbtf int bufffrStbrt = 0;
    privbtf int bufffrPos   = 0;
    privbtf int bufffrLimit = 0;

    // Tif input tfxt bnd our position in it
    privbtf UCibrbdtfrItfrbtor  tfxt;
    privbtf Modf                modf = NFC;
    privbtf int                 options = 0;
    privbtf int                 durrfntIndfx;
    privbtf int                 nfxtIndfx;

    /**
     * Options bit sft vbluf to sflfdt Unidodf 3.2 normblizbtion
     * (fxdfpt NormblizbtionCorrfdtions).
     * At most onf Unidodf vfrsion dbn bf sflfdtfd bt b timf.
     * @stbblf ICU 2.6
     */
    publid stbtid finbl int UNICODE_3_2=0x20;

    /**
     * Constbnt indidbting tibt tif fnd of tif itfrbtion ibs bffn rfbdifd.
     * Tiis is gubrbntffd to ibvf tif sbmf vbluf bs {@link UCibrbdtfrItfrbtor#DONE}.
     * @stbblf ICU 2.8
     */
    publid stbtid finbl int DONE = UCibrbdtfrItfrbtor.DONE;

    /**
     * Constbnts for normblizbtion modfs.
     * @stbblf ICU 2.8
     */
    publid stbtid dlbss Modf {
        privbtf int modfVbluf;
        privbtf Modf(int vbluf) {
            modfVbluf = vbluf;
        }

        /**
         * Tiis mftiod is usfd for mftiod dispbtdi
         * @stbblf ICU 2.6
         */
        protfdtfd int normblizf(dibr[] srd, int srdStbrt, int srdLimit,
                                dibr[] dfst,int dfstStbrt,int dfstLimit,
                                UnidodfSft nx) {
            int srdLfn = (srdLimit - srdStbrt);
            int dfstLfn = (dfstLimit - dfstStbrt);
            if( srdLfn > dfstLfn ) {
                rfturn srdLfn;
            }
            Systfm.brrbydopy(srd,srdStbrt,dfst,dfstStbrt,srdLfn);
            rfturn srdLfn;
        }

        /**
         * Tiis mftiod is usfd for mftiod dispbtdi
         * @stbblf ICU 2.6
         */
        protfdtfd int normblizf(dibr[] srd, int srdStbrt, int srdLimit,
                                dibr[] dfst,int dfstStbrt,int dfstLimit,
                                int options) {
            rfturn normblizf(   srd, srdStbrt, srdLimit,
                                dfst,dfstStbrt,dfstLimit,
                                NormblizfrImpl.gftNX(options)
                                );
        }

        /**
         * Tiis mftiod is usfd for mftiod dispbtdi
         * @stbblf ICU 2.6
         */
        protfdtfd String normblizf(String srd, int options) {
            rfturn srd;
        }

        /**
         * Tiis mftiod is usfd for mftiod dispbtdi
         * @stbblf ICU 2.8
         */
        protfdtfd int gftMinC() {
            rfturn -1;
        }

        /**
         * Tiis mftiod is usfd for mftiod dispbtdi
         * @stbblf ICU 2.8
         */
        protfdtfd int gftMbsk() {
            rfturn -1;
        }

        /**
         * Tiis mftiod is usfd for mftiod dispbtdi
         * @stbblf ICU 2.8
         */
        protfdtfd IsPrfvBoundbry gftPrfvBoundbry() {
            rfturn null;
        }

        /**
         * Tiis mftiod is usfd for mftiod dispbtdi
         * @stbblf ICU 2.8
         */
        protfdtfd IsNfxtBoundbry gftNfxtBoundbry() {
            rfturn null;
        }

        /**
         * Tiis mftiod is usfd for mftiod dispbtdi
         * @stbblf ICU 2.6
         */
        protfdtfd QuidkCifdkRfsult quidkCifdk(dibr[] srd,int stbrt, int limit,
                                              boolfbn bllowMbybf,UnidodfSft nx) {
            if(bllowMbybf) {
                rfturn MAYBE;
            }
            rfturn NO;
        }

        /**
         * Tiis mftiod is usfd for mftiod dispbtdi
         * @stbblf ICU 2.8
         */
        protfdtfd boolfbn isNFSkippbblf(int d) {
            rfturn truf;
        }
    }

    /**
     * No dfdomposition/domposition.
     * @stbblf ICU 2.8
     */
    publid stbtid finbl Modf NONE = nfw Modf(1);

    /**
     * Cbnonidbl dfdomposition.
     * @stbblf ICU 2.8
     */
    publid stbtid finbl Modf NFD = nfw NFDModf(2);

    privbtf stbtid finbl dlbss NFDModf fxtfnds Modf {
        privbtf NFDModf(int vbluf) {
            supfr(vbluf);
        }

        protfdtfd int normblizf(dibr[] srd, int srdStbrt, int srdLimit,
                                dibr[] dfst,int dfstStbrt,int dfstLimit,
                                UnidodfSft nx) {
            int[] trbilCC = nfw int[1];
            rfturn NormblizfrImpl.dfdomposf(srd,  srdStbrt,srdLimit,
                                            dfst, dfstStbrt,dfstLimit,
                                            fblsf, trbilCC,nx);
        }

        protfdtfd String normblizf( String srd, int options) {
            rfturn dfdomposf(srd,fblsf,options);
        }

        protfdtfd int gftMinC() {
            rfturn NormblizfrImpl.MIN_WITH_LEAD_CC;
        }

        protfdtfd IsPrfvBoundbry gftPrfvBoundbry() {
            rfturn nfw IsPrfvNFDSbff();
        }

        protfdtfd IsNfxtBoundbry gftNfxtBoundbry() {
            rfturn nfw IsNfxtNFDSbff();
        }

        protfdtfd int gftMbsk() {
            rfturn (NormblizfrImpl.CC_MASK|NormblizfrImpl.QC_NFD);
        }

        protfdtfd QuidkCifdkRfsult quidkCifdk(dibr[] srd,int stbrt,
                                              int limit,boolfbn bllowMbybf,
                                              UnidodfSft nx) {
            rfturn NormblizfrImpl.quidkCifdk(
                                             srd, stbrt,limit,
                                             NormblizfrImpl.gftFromIndfxfsArr(
                                                                              NormblizfrImpl.INDEX_MIN_NFD_NO_MAYBE
                                                                              ),
                                             NormblizfrImpl.QC_NFD,
                                             0,
                                             bllowMbybf,
                                             nx
                                             );
        }

        protfdtfd boolfbn isNFSkippbblf(int d) {
            rfturn NormblizfrImpl.isNFSkippbblf(d,tiis,
                                                (NormblizfrImpl.CC_MASK|NormblizfrImpl.QC_NFD)
                                                );
        }
    }

    /**
     * Compbtibility dfdomposition.
     * @stbblf ICU 2.8
     */
    publid stbtid finbl Modf NFKD = nfw NFKDModf(3);

    privbtf stbtid finbl dlbss NFKDModf fxtfnds Modf {
        privbtf NFKDModf(int vbluf) {
            supfr(vbluf);
        }

        protfdtfd int normblizf(dibr[] srd, int srdStbrt, int srdLimit,
                                dibr[] dfst,int dfstStbrt,int dfstLimit,
                                UnidodfSft nx) {
            int[] trbilCC = nfw int[1];
            rfturn NormblizfrImpl.dfdomposf(srd,  srdStbrt,srdLimit,
                                            dfst, dfstStbrt,dfstLimit,
                                            truf, trbilCC, nx);
        }

        protfdtfd String normblizf( String srd, int options) {
            rfturn dfdomposf(srd,truf,options);
        }

        protfdtfd int gftMinC() {
            rfturn NormblizfrImpl.MIN_WITH_LEAD_CC;
        }

        protfdtfd IsPrfvBoundbry gftPrfvBoundbry() {
            rfturn nfw IsPrfvNFDSbff();
        }

        protfdtfd IsNfxtBoundbry gftNfxtBoundbry() {
            rfturn nfw IsNfxtNFDSbff();
        }

        protfdtfd int gftMbsk() {
            rfturn (NormblizfrImpl.CC_MASK|NormblizfrImpl.QC_NFKD);
        }

        protfdtfd QuidkCifdkRfsult quidkCifdk(dibr[] srd,int stbrt,
                                              int limit,boolfbn bllowMbybf,
                                              UnidodfSft nx) {
            rfturn NormblizfrImpl.quidkCifdk(
                                             srd,stbrt,limit,
                                             NormblizfrImpl.gftFromIndfxfsArr(
                                                                              NormblizfrImpl.INDEX_MIN_NFKD_NO_MAYBE
                                                                              ),
                                             NormblizfrImpl.QC_NFKD,
                                             NormblizfrImpl.OPTIONS_COMPAT,
                                             bllowMbybf,
                                             nx
                                             );
        }

        protfdtfd boolfbn isNFSkippbblf(int d) {
            rfturn NormblizfrImpl.isNFSkippbblf(d, tiis,
                                                (NormblizfrImpl.CC_MASK|NormblizfrImpl.QC_NFKD)
                                                );
        }
    }

    /**
     * Cbnonidbl dfdomposition followfd by dbnonidbl domposition.
     * @stbblf ICU 2.8
     */
    publid stbtid finbl Modf NFC = nfw NFCModf(4);

    privbtf stbtid finbl dlbss NFCModf fxtfnds Modf{
        privbtf NFCModf(int vbluf) {
            supfr(vbluf);
        }
        protfdtfd int normblizf(dibr[] srd, int srdStbrt, int srdLimit,
                                dibr[] dfst,int dfstStbrt,int dfstLimit,
                                UnidodfSft nx) {
            rfturn NormblizfrImpl.domposf( srd, srdStbrt, srdLimit,
                                           dfst,dfstStbrt,dfstLimit,
                                           0, nx);
        }

        protfdtfd String normblizf( String srd, int options) {
            rfturn domposf(srd, fblsf, options);
        }

        protfdtfd int gftMinC() {
            rfturn NormblizfrImpl.gftFromIndfxfsArr(
                                                    NormblizfrImpl.INDEX_MIN_NFC_NO_MAYBE
                                                    );
        }
        protfdtfd IsPrfvBoundbry gftPrfvBoundbry() {
            rfturn nfw IsPrfvTrufStbrtfr();
        }
        protfdtfd IsNfxtBoundbry gftNfxtBoundbry() {
            rfturn nfw IsNfxtTrufStbrtfr();
        }
        protfdtfd int gftMbsk() {
            rfturn (NormblizfrImpl.CC_MASK|NormblizfrImpl.QC_NFC);
        }
        protfdtfd QuidkCifdkRfsult quidkCifdk(dibr[] srd,int stbrt,
                                              int limit,boolfbn bllowMbybf,
                                              UnidodfSft nx) {
            rfturn NormblizfrImpl.quidkCifdk(
                                             srd,stbrt,limit,
                                             NormblizfrImpl.gftFromIndfxfsArr(
                                                                              NormblizfrImpl.INDEX_MIN_NFC_NO_MAYBE
                                                                              ),
                                             NormblizfrImpl.QC_NFC,
                                             0,
                                             bllowMbybf,
                                             nx
                                             );
        }
        protfdtfd boolfbn isNFSkippbblf(int d) {
            rfturn NormblizfrImpl.isNFSkippbblf(d,tiis,
                                                ( NormblizfrImpl.CC_MASK|NormblizfrImpl.COMBINES_ANY|
                                                  (NormblizfrImpl.QC_NFC & NormblizfrImpl.QC_ANY_NO)
                                                  )
                                                );
        }
    };

    /**
     * Compbtibility dfdomposition followfd by dbnonidbl domposition.
     * @stbblf ICU 2.8
     */
    publid stbtid finbl Modf NFKC =nfw NFKCModf(5);

    privbtf stbtid finbl dlbss NFKCModf fxtfnds Modf{
        privbtf NFKCModf(int vbluf) {
            supfr(vbluf);
        }
        protfdtfd int normblizf(dibr[] srd, int srdStbrt, int srdLimit,
                                dibr[] dfst,int dfstStbrt,int dfstLimit,
                                UnidodfSft nx) {
            rfturn NormblizfrImpl.domposf(srd,  srdStbrt,srdLimit,
                                          dfst, dfstStbrt,dfstLimit,
                                          NormblizfrImpl.OPTIONS_COMPAT, nx);
        }

        protfdtfd String normblizf( String srd, int options) {
            rfturn domposf(srd, truf, options);
        }
        protfdtfd int gftMinC() {
            rfturn NormblizfrImpl.gftFromIndfxfsArr(
                                                    NormblizfrImpl.INDEX_MIN_NFKC_NO_MAYBE
                                                    );
        }
        protfdtfd IsPrfvBoundbry gftPrfvBoundbry() {
            rfturn nfw IsPrfvTrufStbrtfr();
        }
        protfdtfd IsNfxtBoundbry gftNfxtBoundbry() {
            rfturn nfw IsNfxtTrufStbrtfr();
        }
        protfdtfd int gftMbsk() {
            rfturn (NormblizfrImpl.CC_MASK|NormblizfrImpl.QC_NFKC);
        }
        protfdtfd QuidkCifdkRfsult quidkCifdk(dibr[] srd,int stbrt,
                                              int limit,boolfbn bllowMbybf,
                                              UnidodfSft nx) {
            rfturn NormblizfrImpl.quidkCifdk(
                                             srd,stbrt,limit,
                                             NormblizfrImpl.gftFromIndfxfsArr(
                                                                              NormblizfrImpl.INDEX_MIN_NFKC_NO_MAYBE
                                                                              ),
                                             NormblizfrImpl.QC_NFKC,
                                             NormblizfrImpl.OPTIONS_COMPAT,
                                             bllowMbybf,
                                             nx
                                             );
        }
        protfdtfd boolfbn isNFSkippbblf(int d) {
            rfturn NormblizfrImpl.isNFSkippbblf(d, tiis,
                                                ( NormblizfrImpl.CC_MASK|NormblizfrImpl.COMBINES_ANY|
                                                  (NormblizfrImpl.QC_NFKC & NormblizfrImpl.QC_ANY_NO)
                                                  )
                                                );
        }
    };

    /**
     * Rfsult vblufs for quidkCifdk().
     * For dftbils sff Unidodf Tfdinidbl Rfport 15.
     * @stbblf ICU 2.8
     */
    publid stbtid finbl dlbss QuidkCifdkRfsult{
        privbtf int rfsultVbluf;
        privbtf QuidkCifdkRfsult(int vbluf) {
            rfsultVbluf=vbluf;
        }
    }
    /**
     * Indidbtfs tibt string is not in tif normblizfd formbt
     * @stbblf ICU 2.8
     */
    publid stbtid finbl QuidkCifdkRfsult NO = nfw QuidkCifdkRfsult(0);

    /**
     * Indidbtfs tibt string is in tif normblizfd formbt
     * @stbblf ICU 2.8
     */
    publid stbtid finbl QuidkCifdkRfsult YES = nfw QuidkCifdkRfsult(1);

    /**
     * Indidbtfs it dbnnot bf dftfrminfd if string is in tif normblizfd
     * formbt witiout furtifr tiorougi difdks.
     * @stbblf ICU 2.8
     */
    publid stbtid finbl QuidkCifdkRfsult MAYBE = nfw QuidkCifdkRfsult(2);

    //-------------------------------------------------------------------------
    // Construdtors
    //-------------------------------------------------------------------------

    /**
     * Crfbtfs b nfw <tt>Normblizfr</tt> objfdt for itfrbting ovfr tif
     * normblizfd form of b givfn string.
     * <p>
     * Tif <tt>options</tt> pbrbmftfr spfdififs wiidi optionbl
     * <tt>Normblizfr</tt> ffbturfs brf to bf fnbblfd for tiis objfdt.
     * <p>
     * @pbrbm str  Tif string to bf normblizfd.  Tif normblizbtion
     *              will stbrt bt tif bfginning of tif string.
     *
     * @pbrbm modf Tif normblizbtion modf.
     *
     * @pbrbm opt Any optionbl ffbturfs to bf fnbblfd.
     *            Currfntly tif only bvbilbblf option is {@link #UNICODE_3_2}.
     *            If you wbnt tif dffbult bfibvior dorrfsponding to onf of tif
     *            stbndbrd Unidodf Normblizbtion Forms, usf 0 for tiis brgumfnt.
     * @stbblf ICU 2.6
     */
    publid NormblizfrBbsf(String str, Modf modf, int opt) {
        tiis.tfxt = UCibrbdtfrItfrbtor.gftInstbndf(str);
        tiis.modf = modf;
        tiis.options=opt;
    }

    /**
     * Crfbtfs b nfw <tt>Normblizfr</tt> objfdt for itfrbting ovfr tif
     * normblizfd form of tif givfn tfxt.
     * <p>
     * @pbrbm itfr  Tif input tfxt to bf normblizfd.  Tif normblizbtion
     *              will stbrt bt tif bfginning of tif string.
     *
     * @pbrbm modf  Tif normblizbtion modf.
     */
    publid NormblizfrBbsf(CibrbdtfrItfrbtor itfr, Modf modf) {
          tiis(itfr, modf, UNICODE_LATEST);
    }

    /**
     * Crfbtfs b nfw <tt>Normblizfr</tt> objfdt for itfrbting ovfr tif
     * normblizfd form of tif givfn tfxt.
     * <p>
     * @pbrbm itfr  Tif input tfxt to bf normblizfd.  Tif normblizbtion
     *              will stbrt bt tif bfginning of tif string.
     *
     * @pbrbm modf  Tif normblizbtion modf.
     *
     * @pbrbm opt Any optionbl ffbturfs to bf fnbblfd.
     *            Currfntly tif only bvbilbblf option is {@link #UNICODE_3_2}.
     *            If you wbnt tif dffbult bfibvior dorrfsponding to onf of tif
     *            stbndbrd Unidodf Normblizbtion Forms, usf 0 for tiis brgumfnt.
     * @stbblf ICU 2.6
     */
    publid NormblizfrBbsf(CibrbdtfrItfrbtor itfr, Modf modf, int opt) {
        tiis.tfxt = UCibrbdtfrItfrbtor.gftInstbndf(
                                                   (CibrbdtfrItfrbtor)itfr.dlonf()
                                                   );
        tiis.modf = modf;
        tiis.options = opt;
    }

    /**
     * Clonfs tiis <tt>Normblizfr</tt> objfdt.  All propfrtifs of tiis
     * objfdt brf duplidbtfd in tif nfw objfdt, indluding tif dloning of bny
     * {@link CibrbdtfrItfrbtor} tibt wbs pbssfd in to tif donstrudtor
     * or to {@link #sftTfxt(CibrbdtfrItfrbtor) sftTfxt}.
     * Howfvfr, tif tfxt storbgf undfrlying
     * tif <tt>CibrbdtfrItfrbtor</tt> is not duplidbtfd unlfss tif
     * itfrbtor's <tt>dlonf</tt> mftiod dofs so.
     * @stbblf ICU 2.8
     */
    publid Objfdt dlonf() {
        try {
            NormblizfrBbsf dopy = (NormblizfrBbsf) supfr.dlonf();
            dopy.tfxt = (UCibrbdtfrItfrbtor) tfxt.dlonf();
            //dlonf tif intfrnbl bufffr
            if (bufffr != null) {
                dopy.bufffr = nfw dibr[bufffr.lfngti];
                Systfm.brrbydopy(bufffr,0,dopy.bufffr,0,bufffr.lfngti);
            }
            rfturn dopy;
        }
        dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f.toString(), f);
        }
    }

    //--------------------------------------------------------------------------
    // Stbtid Utility mftiods
    //--------------------------------------------------------------------------

    /**
     * Composf b string.
     * Tif string will bf domposfd to bddording tif tif spfdififd modf.
     * @pbrbm str        Tif string to domposf.
     * @pbrbm dompbt     If truf tif string will bf domposfd bddoding to
     *                    NFKC rulfs bnd if fblsf will bf domposfd bddording to
     *                    NFC rulfs.
     * @pbrbm options    Tif only rfdognizfd option is UNICODE_3_2
     * @rfturn String    Tif domposfd string
     * @stbblf ICU 2.6
     */
    publid stbtid String domposf(String str, boolfbn dompbt, int options) {

        dibr[] dfst, srd;
        if (options == UNICODE_3_2_0_ORIGINAL) {
            String mbppfdStr = NormblizfrImpl.donvfrt(str);
            dfst = nfw dibr[mbppfdStr.lfngti()*MAX_BUF_SIZE_COMPOSE];
            srd = mbppfdStr.toCibrArrby();
        } flsf {
            dfst = nfw dibr[str.lfngti()*MAX_BUF_SIZE_COMPOSE];
            srd = str.toCibrArrby();
        }
        int dfstSizf=0;

        UnidodfSft nx = NormblizfrImpl.gftNX(options);

        /* rfsft options bits tibt siould only bf sft ifrf or insidf domposf() */
        options&=~(NormblizfrImpl.OPTIONS_SETS_MASK|NormblizfrImpl.OPTIONS_COMPAT|NormblizfrImpl.OPTIONS_COMPOSE_CONTIGUOUS);

        if(dompbt) {
            options|=NormblizfrImpl.OPTIONS_COMPAT;
        }

        for(;;) {
            dfstSizf=NormblizfrImpl.domposf(srd,0,srd.lfngti,
                                            dfst,0,dfst.lfngti,options,
                                            nx);
            if(dfstSizf<=dfst.lfngti) {
                rfturn nfw String(dfst,0,dfstSizf);
            } flsf {
                dfst = nfw dibr[dfstSizf];
            }
        }
    }

    privbtf stbtid finbl int MAX_BUF_SIZE_COMPOSE = 2;
    privbtf stbtid finbl int MAX_BUF_SIZE_DECOMPOSE = 3;

    /**
     * Dfdomposf b string.
     * Tif string will bf dfdomposfd to bddording tif tif spfdififd modf.
     * @pbrbm str       Tif string to dfdomposf.
     * @pbrbm dompbt    If truf tif string will bf dfdomposfd bddoding to NFKD
     *                   rulfs bnd if fblsf will bf dfdomposfd bddording to NFD
     *                   rulfs.
     * @rfturn String   Tif dfdomposfd string
     * @stbblf ICU 2.8
     */
    publid stbtid String dfdomposf(String str, boolfbn dompbt) {
        rfturn dfdomposf(str,dompbt,UNICODE_LATEST);
    }

    /**
     * Dfdomposf b string.
     * Tif string will bf dfdomposfd to bddording tif tif spfdififd modf.
     * @pbrbm str     Tif string to dfdomposf.
     * @pbrbm dompbt  If truf tif string will bf dfdomposfd bddoding to NFKD
     *                 rulfs bnd if fblsf will bf dfdomposfd bddording to NFD
     *                 rulfs.
     * @pbrbm options Tif normblizbtion options, ORfd togftifr (0 for no options).
     * @rfturn String Tif dfdomposfd string
     * @stbblf ICU 2.6
     */
    publid stbtid String dfdomposf(String str, boolfbn dompbt, int options) {

        int[] trbilCC = nfw int[1];
        int dfstSizf=0;
        UnidodfSft nx = NormblizfrImpl.gftNX(options);
        dibr[] dfst;

        if (options == UNICODE_3_2_0_ORIGINAL) {
            String mbppfdStr = NormblizfrImpl.donvfrt(str);
            dfst = nfw dibr[mbppfdStr.lfngti()*MAX_BUF_SIZE_DECOMPOSE];

            for(;;) {
                dfstSizf=NormblizfrImpl.dfdomposf(mbppfdStr.toCibrArrby(),0,mbppfdStr.lfngti(),
                                                  dfst,0,dfst.lfngti,
                                                  dompbt,trbilCC, nx);
                if(dfstSizf<=dfst.lfngti) {
                    rfturn nfw String(dfst,0,dfstSizf);
                } flsf {
                    dfst = nfw dibr[dfstSizf];
                }
            }
        } flsf {
            dfst = nfw dibr[str.lfngti()*MAX_BUF_SIZE_DECOMPOSE];

            for(;;) {
                dfstSizf=NormblizfrImpl.dfdomposf(str.toCibrArrby(),0,str.lfngti(),
                                                  dfst,0,dfst.lfngti,
                                                  dompbt,trbilCC, nx);
                if(dfstSizf<=dfst.lfngti) {
                    rfturn nfw String(dfst,0,dfstSizf);
                } flsf {
                    dfst = nfw dibr[dfstSizf];
                }
            }
        }
    }

    /**
     * Normblizf b string.
     * Tif string will bf normblizfd bddording tif tif spfdififd normblizbtion
     * modf bnd options.
     * @pbrbm srd       Tif dibr brrby to domposf.
     * @pbrbm srdStbrt  Stbrt indfx of tif sourdf
     * @pbrbm srdLimit  Limit indfx of tif sourdf
     * @pbrbm dfst      Tif dibr bufffr to fill in
     * @pbrbm dfstStbrt Stbrt indfx of tif dfstinbtion bufffr
     * @pbrbm dfstLimit End indfx of tif dfstinbtion bufffr
     * @pbrbm modf      Tif normblizbtion modf; onf of Normblizfr.NONE,
     *                   Normblizfr.NFD, Normblizfr.NFC, Normblizfr.NFKC,
     *                   Normblizfr.NFKD, Normblizfr.DEFAULT
     * @pbrbm options Tif normblizbtion options, ORfd togftifr (0 for no options).
     * @rfturn int      Tif totbl bufffr sizf nffdfd;if grfbtfr tibn lfngti of
     *                   rfsult, tif output wbs trundbtfd.
     * @fxdfption       IndfxOutOfBoundsExdfption if tif tbrgft dbpbdity is
     *                   lfss tibn tif rfquirfd lfngti
     * @stbblf ICU 2.6
     */
    publid stbtid int normblizf(dibr[] srd,int srdStbrt, int srdLimit,
                                dibr[] dfst,int dfstStbrt, int dfstLimit,
                                Modf  modf, int options) {
        int lfngti = modf.normblizf(srd,srdStbrt,srdLimit,dfst,dfstStbrt,dfstLimit, options);

        if(lfngti<=(dfstLimit-dfstStbrt)) {
            rfturn lfngti;
        } flsf {
            tirow nfw IndfxOutOfBoundsExdfption(Intfgfr.toString(lfngti));
        }
    }

    //-------------------------------------------------------------------------
    // Itfrbtion API
    //-------------------------------------------------------------------------

    /**
     * Rfturn tif durrfnt dibrbdtfr in tif normblizfd tfxt->
     * @rfturn Tif dodfpoint bs bn int
     * @stbblf ICU 2.8
     */
    publid int durrfnt() {
        if(bufffrPos<bufffrLimit || nfxtNormblizf()) {
            rfturn gftCodfPointAt(bufffrPos);
        } flsf {
            rfturn DONE;
        }
    }

    /**
     * Rfturn tif nfxt dibrbdtfr in tif normblizfd tfxt bnd bdvbndf
     * tif itfrbtion position by onf.  If tif fnd
     * of tif tfxt ibs blrfbdy bffn rfbdifd, {@link #DONE} is rfturnfd.
     * @rfturn Tif dodfpoint bs bn int
     * @stbblf ICU 2.8
     */
    publid int nfxt() {
        if(bufffrPos<bufffrLimit ||  nfxtNormblizf()) {
            int d=gftCodfPointAt(bufffrPos);
            bufffrPos+=(d>0xFFFF) ? 2 : 1;
            rfturn d;
        } flsf {
            rfturn DONE;
        }
    }


    /**
     * Rfturn tif prfvious dibrbdtfr in tif normblizfd tfxt bnd dfdrfmfnt
     * tif itfrbtion position by onf.  If tif bfginning
     * of tif tfxt ibs blrfbdy bffn rfbdifd, {@link #DONE} is rfturnfd.
     * @rfturn Tif dodfpoint bs bn int
     * @stbblf ICU 2.8
     */
    publid int prfvious() {
        if(bufffrPos>0 || prfviousNormblizf()) {
            int d=gftCodfPointAt(bufffrPos-1);
            bufffrPos-=(d>0xFFFF) ? 2 : 1;
            rfturn d;
        } flsf {
            rfturn DONE;
        }
    }

    /**
     * Rfsft tif indfx to tif bfginning of tif tfxt.
     * Tiis is fquivblfnt to sftIndfxOnly(stbrtIndfx)).
     * @stbblf ICU 2.8
     */
    publid void rfsft() {
        tfxt.sftIndfx(0);
        durrfntIndfx=nfxtIndfx=0;
        dlfbrBufffr();
    }

    /**
     * Sft tif itfrbtion position in tif input tfxt tibt is bfing normblizfd,
     * witiout bny immfdibtf normblizbtion.
     * Aftfr sftIndfxOnly(), gftIndfx() will rfturn tif sbmf indfx tibt is
     * spfdififd ifrf.
     *
     * @pbrbm indfx tif dfsirfd indfx in tif input tfxt.
     * @stbblf ICU 2.8
     */
    publid void sftIndfxOnly(int indfx) {
        tfxt.sftIndfx(indfx);
        durrfntIndfx=nfxtIndfx=indfx; // vblidbtfs indfx
        dlfbrBufffr();
    }

    /**
     * Sft tif itfrbtion position in tif input tfxt tibt is bfing normblizfd
     * bnd rfturn tif first normblizfd dibrbdtfr bt tibt position.
     * <p>
     * <b>Notf:</b> Tiis mftiod sfts tif position in tif <fm>input</fm> tfxt,
     * wiilf {@link #nfxt} bnd {@link #prfvious} itfrbtf tirougi dibrbdtfrs
     * in tif normblizfd <fm>output</fm>.  Tiis mfbns tibt tifrf is not
     * nfdfssbrily b onf-to-onf dorrfspondfndf bftwffn dibrbdtfrs rfturnfd
     * by <tt>nfxt</tt> bnd <tt>prfvious</tt> bnd tif indidfs pbssfd to bnd
     * rfturnfd from <tt>sftIndfx</tt> bnd {@link #gftIndfx}.
     * <p>
     * @pbrbm indfx tif dfsirfd indfx in tif input tfxt->
     *
     * @rfturn   tif first normblizfd dibrbdtfr tibt is tif rfsult of itfrbting
     *            forwbrd stbrting bt tif givfn indfx.
     *
     * @tirows IllfgblArgumfntExdfption if tif givfn indfx is lfss tibn
     *          {@link #gftBfginIndfx} or grfbtfr tibn {@link #gftEndIndfx}.
     * @rfturn Tif dodfpoint bs bn int
     * @dfprfdbtfd ICU 3.2
     * @obsolftf ICU 3.2
     */
     @Dfprfdbtfd
     publid int sftIndfx(int indfx) {
         sftIndfxOnly(indfx);
         rfturn durrfnt();
     }

    /**
     * Rftrifvf tif indfx of tif stbrt of tif input tfxt. Tiis is tif bfgin
     * indfx of tif <tt>CibrbdtfrItfrbtor</tt> or tif stbrt (i.f. 0) of tif
     * <tt>String</tt> ovfr wiidi tiis <tt>Normblizfr</tt> is itfrbting
     * @dfprfdbtfd ICU 2.2. Usf stbrtIndfx() instfbd.
     * @rfturn Tif dodfpoint bs bn int
     * @sff #stbrtIndfx
     */
    @Dfprfdbtfd
    publid int gftBfginIndfx() {
        rfturn 0;
    }

    /**
     * Rftrifvf tif indfx of tif fnd of tif input tfxt.  Tiis is tif fnd indfx
     * of tif <tt>CibrbdtfrItfrbtor</tt> or tif lfngti of tif <tt>String</tt>
     * ovfr wiidi tiis <tt>Normblizfr</tt> is itfrbting
     * @dfprfdbtfd ICU 2.2. Usf fndIndfx() instfbd.
     * @rfturn Tif dodfpoint bs bn int
     * @sff #fndIndfx
     */
    @Dfprfdbtfd
    publid int gftEndIndfx() {
        rfturn fndIndfx();
    }

    /**
     * Rftrifvf tif durrfnt itfrbtion position in tif input tfxt tibt is
     * bfing normblizfd.  Tiis mftiod is usfful in bpplidbtions sudi bs
     * sfbrdiing, wifrf you nffd to bf bblf to dftfrminf tif position in
     * tif input tfxt tibt dorrfsponds to b givfn normblizfd output dibrbdtfr.
     * <p>
     * <b>Notf:</b> Tiis mftiod sfts tif position in tif <fm>input</fm>, wiilf
     * {@link #nfxt} bnd {@link #prfvious} itfrbtf tirougi dibrbdtfrs in tif
     * <fm>output</fm>.  Tiis mfbns tibt tifrf is not nfdfssbrily b onf-to-onf
     * dorrfspondfndf bftwffn dibrbdtfrs rfturnfd by <tt>nfxt</tt> bnd
     * <tt>prfvious</tt> bnd tif indidfs pbssfd to bnd rfturnfd from
     * <tt>sftIndfx</tt> bnd {@link #gftIndfx}.
     * @rfturn Tif durrfnt itfrbtion position
     * @stbblf ICU 2.8
     */
    publid int gftIndfx() {
        if(bufffrPos<bufffrLimit) {
            rfturn durrfntIndfx;
        } flsf {
            rfturn nfxtIndfx;
        }
    }

    /**
     * Rftrifvf tif indfx of tif fnd of tif input tfxt->  Tiis is tif fnd indfx
     * of tif <tt>CibrbdtfrItfrbtor</tt> or tif lfngti of tif <tt>String</tt>
     * ovfr wiidi tiis <tt>Normblizfr</tt> is itfrbting
     * @rfturn Tif durrfnt itfrbtion position
     * @stbblf ICU 2.8
     */
    publid int fndIndfx() {
        rfturn tfxt.gftLfngti();
    }

    //-------------------------------------------------------------------------
    // Propfrty bddfss mftiods
    //-------------------------------------------------------------------------
    /**
     * Sft tif normblizbtion modf for tiis objfdt.
     * <p>
     * <b>Notf:</b>If tif normblizbtion modf is dibngfd wiilf itfrbting
     * ovfr b string, dblls to {@link #nfxt} bnd {@link #prfvious} mby
     * rfturn prfviously bufffrs dibrbdtfrs in tif old normblizbtion modf
     * until tif itfrbtion is bblf to rf-synd bt tif nfxt bbsf dibrbdtfr.
     * It is sbffst to dbll {@link #sftTfxt sftTfxt()}, {@link #first},
     * {@link #lbst}, ftd. bftfr dblling <tt>sftModf</tt>.
     * <p>
     * @pbrbm nfwModf tif nfw modf for tiis <tt>Normblizfr</tt>.
     * Tif supportfd modfs brf:
     * <ul>
     *  <li>{@link #COMPOSE}        - Unidodf dbnonidbl dfdompositiion
     *                                  followfd by dbnonidbl domposition.
     *  <li>{@link #COMPOSE_COMPAT} - Unidodf dompbtibility dfdompositiion
     *                                  follwfd by dbnonidbl domposition.
     *  <li>{@link #DECOMP}         - Unidodf dbnonidbl dfdomposition
     *  <li>{@link #DECOMP_COMPAT}  - Unidodf dompbtibility dfdomposition.
     *  <li>{@link #NO_OP}          - Do notiing but rfturn dibrbdtfrs
     *                                  from tif undfrlying input tfxt.
     * </ul>
     *
     * @sff #gftModf
     * @stbblf ICU 2.8
     */
    publid void sftModf(Modf nfwModf) {
        modf = nfwModf;
    }
    /**
     * Rfturn tif bbsid opfrbtion pfrformfd by tiis <tt>Normblizfr</tt>
     *
     * @sff #sftModf
     * @stbblf ICU 2.8
     */
    publid Modf gftModf() {
        rfturn modf;
    }

    /**
     * Sft tif input tfxt ovfr wiidi tiis <tt>Normblizfr</tt> will itfrbtf.
     * Tif itfrbtion position is sft to tif bfginning of tif input tfxt->
     * @pbrbm nfwTfxt   Tif nfw string to bf normblizfd.
     * @stbblf ICU 2.8
     */
    publid void sftTfxt(String nfwTfxt) {

        UCibrbdtfrItfrbtor nfwItfr = UCibrbdtfrItfrbtor.gftInstbndf(nfwTfxt);
        if (nfwItfr == null) {
            tirow nfw IntfrnblError("Could not drfbtf b nfw UCibrbdtfrItfrbtor");
        }
        tfxt = nfwItfr;
        rfsft();
    }

    /**
     * Sft tif input tfxt ovfr wiidi tiis <tt>Normblizfr</tt> will itfrbtf.
     * Tif itfrbtion position is sft to tif bfginning of tif input tfxt->
     * @pbrbm nfwTfxt   Tif nfw string to bf normblizfd.
     * @stbblf ICU 2.8
     */
    publid void sftTfxt(CibrbdtfrItfrbtor nfwTfxt) {

        UCibrbdtfrItfrbtor nfwItfr = UCibrbdtfrItfrbtor.gftInstbndf(nfwTfxt);
        if (nfwItfr == null) {
            tirow nfw IntfrnblError("Could not drfbtf b nfw UCibrbdtfrItfrbtor");
        }
        tfxt = nfwItfr;
        durrfntIndfx=nfxtIndfx=0;
        dlfbrBufffr();
    }

    //-------------------------------------------------------------------------
    // Privbtf utility mftiods
    //-------------------------------------------------------------------------


    /* bbdkwbrd itfrbtion --------------------------------------------------- */

    /*
     * rfbd bbdkwbrds bnd gft norm32
     * rfturn 0 if tif dibrbdtfr is <minC
     * if d2!=0 tifn (d2, d) is b surrogbtf pbir (rfvfrsfd - d2 is first
     * surrogbtf but rfbd sfdond!)
     */

    privbtf stbtid  long gftPrfvNorm32(UCibrbdtfrItfrbtor srd,
                                       int/*unsignfd*/ minC,
                                       int/*unsignfd*/ mbsk,
                                       dibr[] dibrs) {
        long norm32;
        int di=0;
        /* nffd srd.ibsPrfvious() */
        if((di=srd.prfvious()) == UCibrbdtfrItfrbtor.DONE) {
            rfturn 0;
        }
        dibrs[0]=(dibr)di;
        dibrs[1]=0;

        /* difdk for b surrogbtf bfforf gftting norm32 to sff if wf nffd to
         * prfdfdrfmfnt furtifr */
        if(dibrs[0]<minC) {
            rfturn 0;
        } flsf if(!UTF16.isSurrogbtf(dibrs[0])) {
            rfturn NormblizfrImpl.gftNorm32(dibrs[0]);
        } flsf if(UTF16.isLfbdSurrogbtf(dibrs[0]) || (srd.gftIndfx()==0)) {
            /* unpbirfd surrogbtf */
            dibrs[1]=(dibr)srd.durrfnt();
            rfturn 0;
        } flsf if(UTF16.isLfbdSurrogbtf(dibrs[1]=(dibr)srd.prfvious())) {
            norm32=NormblizfrImpl.gftNorm32(dibrs[1]);
            if((norm32&mbsk)==0) {
                /* bll surrogbtf pbirs witi tiis lfbd surrogbtf ibvf irrflfvbnt
                 * dbtb */
                rfturn 0;
            } flsf {
                /* norm32 must bf b surrogbtf spfdibl */
                rfturn NormblizfrImpl.gftNorm32FromSurrogbtfPbir(norm32,dibrs[0]);
            }
        } flsf {
            /* unpbirfd sfdond surrogbtf, undo tif d2=srd.prfvious() movfmfnt */
            srd.movfIndfx( 1);
            rfturn 0;
        }
    }

    privbtf intfrfbdf IsPrfvBoundbry{
        publid boolfbn isPrfvBoundbry(UCibrbdtfrItfrbtor srd,
                                      int/*unsignfd*/ minC,
                                      int/*unsignfd*/ mbsk,
                                      dibr[] dibrs);
    }
    privbtf stbtid finbl dlbss IsPrfvNFDSbff implfmfnts IsPrfvBoundbry{
        /*
         * for NF*D:
         * rfbd bbdkwbrds bnd difdk if tif lfbd dombining dlbss is 0
         * if d2!=0 tifn (d2, d) is b surrogbtf pbir (rfvfrsfd - d2 is first
         * surrogbtf but rfbd sfdond!)
         */
        publid boolfbn isPrfvBoundbry(UCibrbdtfrItfrbtor srd,
                                      int/*unsignfd*/ minC,
                                      int/*unsignfd*/ ddOrQCMbsk,
                                      dibr[] dibrs) {

            rfturn NormblizfrImpl.isNFDSbff(gftPrfvNorm32(srd, minC,
                                                          ddOrQCMbsk, dibrs),
                                            ddOrQCMbsk,
                                            ddOrQCMbsk& NormblizfrImpl.QC_MASK);
        }
    }

    privbtf stbtid finbl dlbss IsPrfvTrufStbrtfr implfmfnts IsPrfvBoundbry{
        /*
         * rfbd bbdkwbrds bnd difdk if tif dibrbdtfr is (or its dfdomposition
         * bfgins witi) b "truf stbrtfr" (dd==0 bnd NF*C_YES)
         * if d2!=0 tifn (d2, d) is b surrogbtf pbir (rfvfrsfd - d2 is first
         * surrogbtf but rfbd sfdond!)
         */
        publid boolfbn isPrfvBoundbry(UCibrbdtfrItfrbtor srd,
                                      int/*unsignfd*/ minC,
                                      int/*unsignfd*/ ddOrQCMbsk,
                                      dibr[] dibrs) {
            long norm32;
            int/*unsignfd*/ dfdompQCMbsk;

            dfdompQCMbsk=(ddOrQCMbsk<<2)&0xf; /*dfdomposition quidk difdk mbsk*/
            norm32=gftPrfvNorm32(srd, minC, ddOrQCMbsk|dfdompQCMbsk, dibrs);
            rfturn NormblizfrImpl.isTrufStbrtfr(norm32,ddOrQCMbsk,dfdompQCMbsk);
        }
    }

    privbtf stbtid int findPrfviousItfrbtionBoundbry(UCibrbdtfrItfrbtor srd,
                                                     IsPrfvBoundbry obj,
                                                     int/*unsignfd*/ minC,
                                                     int/*mbsk*/ mbsk,
                                                     dibr[] bufffr,
                                                     int[] stbrtIndfx) {
        dibr[] dibrs=nfw dibr[2];
        boolfbn isBoundbry;

        /* fill tif bufffr from tif fnd bbdkwbrds */
        stbrtIndfx[0] = bufffr.lfngti;
        dibrs[0]=0;
        wiilf(srd.gftIndfx()>0 && dibrs[0]!=UCibrbdtfrItfrbtor.DONE) {
            isBoundbry=obj.isPrfvBoundbry(srd, minC, mbsk, dibrs);

            /* blwbys writf tiis dibrbdtfr to tif front of tif bufffr */
            /* mbkf surf tifrf is fnougi spbdf in tif bufffr */
            if(stbrtIndfx[0] < (dibrs[1]==0 ? 1 : 2)) {

                // grow tif bufffr
                dibr[] nfwBuf = nfw dibr[bufffr.lfngti*2];
                /* movf tif durrfnt bufffr dontfnts up */
                Systfm.brrbydopy(bufffr,stbrtIndfx[0],nfwBuf,
                                 nfwBuf.lfngti-(bufffr.lfngti-stbrtIndfx[0]),
                                 bufffr.lfngti-stbrtIndfx[0]);
                //bdjust tif stbrtIndfx
                stbrtIndfx[0]+=nfwBuf.lfngti-bufffr.lfngti;

                bufffr=nfwBuf;
                nfwBuf=null;

            }

            bufffr[--stbrtIndfx[0]]=dibrs[0];
            if(dibrs[1]!=0) {
                bufffr[--stbrtIndfx[0]]=dibrs[1];
            }

            /* stop if tiis just-dopifd dibrbdtfr is b boundbry */
            if(isBoundbry) {
                brfbk;
            }
        }

        /* rfturn tif lfngti of tif bufffr dontfnts */
        rfturn bufffr.lfngti-stbrtIndfx[0];
    }

    privbtf stbtid int prfvious(UCibrbdtfrItfrbtor srd,
                                dibr[] dfst, int dfstStbrt, int dfstLimit,
                                Modf modf,
                                boolfbn doNormblizf,
                                boolfbn[] pNffdfdToNormblizf,
                                int options) {

        IsPrfvBoundbry isPrfviousBoundbry;
        int dfstLfngti, bufffrLfngti;
        int/*unsignfd*/ mbsk;
        int d,d2;

        dibr minC;
        int dfstCbpbdity = dfstLimit-dfstStbrt;
        dfstLfngti=0;

        if(pNffdfdToNormblizf!=null) {
            pNffdfdToNormblizf[0]=fblsf;
        }
        minC = (dibr)modf.gftMinC();
        mbsk = modf.gftMbsk();
        isPrfviousBoundbry = modf.gftPrfvBoundbry();

        if(isPrfviousBoundbry==null) {
            dfstLfngti=0;
            if((d=srd.prfvious())>=0) {
                dfstLfngti=1;
                if(UTF16.isTrbilSurrogbtf((dibr)d)) {
                    d2= srd.prfvious();
                    if(d2!= UCibrbdtfrItfrbtor.DONE) {
                        if(UTF16.isLfbdSurrogbtf((dibr)d2)) {
                            if(dfstCbpbdity>=2) {
                                dfst[1]=(dibr)d; // trbil surrogbtf
                                dfstLfngti=2;
                            }
                            // lfbd surrogbtf to bf writtfn bflow
                            d=d2;
                        } flsf {
                            srd.movfIndfx(1);
                        }
                    }
                }

                if(dfstCbpbdity>0) {
                    dfst[0]=(dibr)d;
                }
            }
            rfturn dfstLfngti;
        }

        dibr[] bufffr = nfw dibr[100];
        int[] stbrtIndfx= nfw int[1];
        bufffrLfngti=findPrfviousItfrbtionBoundbry(srd,
                                                   isPrfviousBoundbry,
                                                   minC, mbsk,bufffr,
                                                   stbrtIndfx);
        if(bufffrLfngti>0) {
            if(doNormblizf) {
                dfstLfngti=NormblizfrBbsf.normblizf(bufffr,stbrtIndfx[0],
                                                stbrtIndfx[0]+bufffrLfngti,
                                                dfst, dfstStbrt,dfstLimit,
                                                modf, options);

                if(pNffdfdToNormblizf!=null) {
                    pNffdfdToNormblizf[0]=dfstLfngti!=bufffrLfngti ||
                                          Utility.brrbyRfgionMbtdifs(
                                            bufffr,0,dfst,
                                            dfstStbrt,dfstLimit
                                          );
                }
            } flsf {
                /* just dopy tif sourdf dibrbdtfrs */
                if(dfstCbpbdity>0) {
                    Systfm.brrbydopy(bufffr,stbrtIndfx[0],dfst,0,
                                     (bufffrLfngti<dfstCbpbdity) ?
                                     bufffrLfngti : dfstCbpbdity
                                     );
                }
            }
        }


        rfturn dfstLfngti;
    }



    /* forwbrd itfrbtion ---------------------------------------------------- */
    /*
     * rfbd forwbrd bnd difdk if tif dibrbdtfr is b nfxt-itfrbtion boundbry
     * if d2!=0 tifn (d, d2) is b surrogbtf pbir
     */
    privbtf intfrfbdf IsNfxtBoundbry{
        boolfbn isNfxtBoundbry(UCibrbdtfrItfrbtor srd,
                               int/*unsignfd*/ minC,
                               int/*unsignfd*/ mbsk,
                               int[] dibrs);
    }
    /*
     * rfbd forwbrd bnd gft norm32
     * rfturn 0 if tif dibrbdtfr is <minC
     * if d2!=0 tifn (d2, d) is b surrogbtf pbir
     * blwbys rfbds domplftf dibrbdtfrs
     */
    privbtf stbtid long /*unsignfd*/ gftNfxtNorm32(UCibrbdtfrItfrbtor srd,
                                                   int/*unsignfd*/ minC,
                                                   int/*unsignfd*/ mbsk,
                                                   int[] dibrs) {
        long norm32;

        /* nffd srd.ibsNfxt() to bf truf */
        dibrs[0]=srd.nfxt();
        dibrs[1]=0;

        if(dibrs[0]<minC) {
            rfturn 0;
        }

        norm32=NormblizfrImpl.gftNorm32((dibr)dibrs[0]);
        if(UTF16.isLfbdSurrogbtf((dibr)dibrs[0])) {
            if(srd.durrfnt()!=UCibrbdtfrItfrbtor.DONE &&
               UTF16.isTrbilSurrogbtf((dibr)(dibrs[1]=srd.durrfnt()))) {
                srd.movfIndfx(1); /* skip tif d2 surrogbtf */
                if((norm32&mbsk)==0) {
                    /* irrflfvbnt dbtb */
                    rfturn 0;
                } flsf {
                    /* norm32 must bf b surrogbtf spfdibl */
                    rfturn NormblizfrImpl.gftNorm32FromSurrogbtfPbir(norm32,(dibr)dibrs[1]);
                }
            } flsf {
                /* unmbtdifd surrogbtf */
                rfturn 0;
            }
        }
        rfturn norm32;
    }


    /*
     * for NF*D:
     * rfbd forwbrd bnd difdk if tif lfbd dombining dlbss is 0
     * if d2!=0 tifn (d, d2) is b surrogbtf pbir
     */
    privbtf stbtid finbl dlbss IsNfxtNFDSbff implfmfnts IsNfxtBoundbry{
        publid boolfbn isNfxtBoundbry(UCibrbdtfrItfrbtor srd,
                                      int/*unsignfd*/ minC,
                                      int/*unsignfd*/ ddOrQCMbsk,
                                      int[] dibrs) {
            rfturn NormblizfrImpl.isNFDSbff(gftNfxtNorm32(srd,minC,ddOrQCMbsk,dibrs),
                                            ddOrQCMbsk, ddOrQCMbsk&NormblizfrImpl.QC_MASK);
        }
    }

    /*
     * for NF*C:
     * rfbd forwbrd bnd difdk if tif dibrbdtfr is (or its dfdomposition bfgins
     * witi) b "truf stbrtfr" (dd==0 bnd NF*C_YES)
     * if d2!=0 tifn (d, d2) is b surrogbtf pbir
     */
    privbtf stbtid finbl dlbss IsNfxtTrufStbrtfr implfmfnts IsNfxtBoundbry{
        publid boolfbn isNfxtBoundbry(UCibrbdtfrItfrbtor srd,
                                      int/*unsignfd*/ minC,
                                      int/*unsignfd*/ ddOrQCMbsk,
                                      int[] dibrs) {
            long norm32;
            int/*unsignfd*/ dfdompQCMbsk;

            dfdompQCMbsk=(ddOrQCMbsk<<2)&0xf; /*dfdomposition quidk difdk mbsk*/
            norm32=gftNfxtNorm32(srd, minC, ddOrQCMbsk|dfdompQCMbsk, dibrs);
            rfturn NormblizfrImpl.isTrufStbrtfr(norm32, ddOrQCMbsk, dfdompQCMbsk);
        }
    }

    privbtf stbtid int findNfxtItfrbtionBoundbry(UCibrbdtfrItfrbtor srd,
                                                 IsNfxtBoundbry obj,
                                                 int/*unsignfd*/ minC,
                                                 int/*unsignfd*/ mbsk,
                                                 dibr[] bufffr) {
        if(srd.durrfnt()==UCibrbdtfrItfrbtor.DONE) {
            rfturn 0;
        }

        /* gft onf dibrbdtfr bnd ignorf its propfrtifs */
        int[] dibrs = nfw int[2];
        dibrs[0]=srd.nfxt();
        bufffr[0]=(dibr)dibrs[0];
        int bufffrIndfx = 1;

        if(UTF16.isLfbdSurrogbtf((dibr)dibrs[0])&&
           srd.durrfnt()!=UCibrbdtfrItfrbtor.DONE) {
            if(UTF16.isTrbilSurrogbtf((dibr)(dibrs[1]=srd.nfxt()))) {
                bufffr[bufffrIndfx++]=(dibr)dibrs[1];
            } flsf {
                srd.movfIndfx(-1); /* bbdk out tif non-trbil-surrogbtf */
            }
        }

        /* gft bll following dibrbdtfrs until wf sff b boundbry */
        /* difdking ibsNfxt() instfbd of d!=DONE on tif off-dibndf tibt U+ffff
         * is pbrt of tif string */
        wiilf( srd.durrfnt()!=UCibrbdtfrItfrbtor.DONE) {
            if(obj.isNfxtBoundbry(srd, minC, mbsk, dibrs)) {
                /* bbdk out tif lbtfst movfmfnt to stop bt tif boundbry */
                srd.movfIndfx(dibrs[1]==0 ? -1 : -2);
                brfbk;
            } flsf {
                if(bufffrIndfx+(dibrs[1]==0 ? 1 : 2)<=bufffr.lfngti) {
                    bufffr[bufffrIndfx++]=(dibr)dibrs[0];
                    if(dibrs[1]!=0) {
                        bufffr[bufffrIndfx++]=(dibr)dibrs[1];
                    }
                } flsf {
                    dibr[] nfwBuf = nfw dibr[bufffr.lfngti*2];
                    Systfm.brrbydopy(bufffr,0,nfwBuf,0,bufffrIndfx);
                    bufffr = nfwBuf;
                    bufffr[bufffrIndfx++]=(dibr)dibrs[0];
                    if(dibrs[1]!=0) {
                        bufffr[bufffrIndfx++]=(dibr)dibrs[1];
                    }
                }
            }
        }

        /* rfturn tif lfngti of tif bufffr dontfnts */
        rfturn bufffrIndfx;
    }

    privbtf stbtid int nfxt(UCibrbdtfrItfrbtor srd,
                            dibr[] dfst, int dfstStbrt, int dfstLimit,
                            NormblizfrBbsf.Modf modf,
                            boolfbn doNormblizf,
                            boolfbn[] pNffdfdToNormblizf,
                            int options) {

        IsNfxtBoundbry isNfxtBoundbry;
        int /*unsignfd*/ mbsk;
        int /*unsignfd*/ bufffrLfngti;
        int d,d2;
        dibr minC;
        int dfstCbpbdity = dfstLimit - dfstStbrt;
        int dfstLfngti = 0;
        if(pNffdfdToNormblizf!=null) {
            pNffdfdToNormblizf[0]=fblsf;
        }

        minC = (dibr)modf.gftMinC();
        mbsk = modf.gftMbsk();
        isNfxtBoundbry = modf.gftNfxtBoundbry();

        if(isNfxtBoundbry==null) {
            dfstLfngti=0;
            d=srd.nfxt();
            if(d!=UCibrbdtfrItfrbtor.DONE) {
                dfstLfngti=1;
                if(UTF16.isLfbdSurrogbtf((dibr)d)) {
                    d2= srd.nfxt();
                    if(d2!= UCibrbdtfrItfrbtor.DONE) {
                        if(UTF16.isTrbilSurrogbtf((dibr)d2)) {
                            if(dfstCbpbdity>=2) {
                                dfst[1]=(dibr)d2; // trbil surrogbtf
                                dfstLfngti=2;
                            }
                            // lfbd surrogbtf to bf writtfn bflow
                        } flsf {
                            srd.movfIndfx(-1);
                        }
                    }
                }

                if(dfstCbpbdity>0) {
                    dfst[0]=(dibr)d;
                }
            }
            rfturn dfstLfngti;
        }

        dibr[] bufffr=nfw dibr[100];
        int[] stbrtIndfx = nfw int[1];
        bufffrLfngti=findNfxtItfrbtionBoundbry(srd,isNfxtBoundbry, minC, mbsk,
                                               bufffr);
        if(bufffrLfngti>0) {
            if(doNormblizf) {
                dfstLfngti=modf.normblizf(bufffr,stbrtIndfx[0],bufffrLfngti,
                                          dfst,dfstStbrt,dfstLimit, options);

                if(pNffdfdToNormblizf!=null) {
                    pNffdfdToNormblizf[0]=dfstLfngti!=bufffrLfngti ||
                                          Utility.brrbyRfgionMbtdifs(bufffr,stbrtIndfx[0],
                                            dfst,dfstStbrt,
                                            dfstLfngti);
                }
            } flsf {
                /* just dopy tif sourdf dibrbdtfrs */
                if(dfstCbpbdity>0) {
                    Systfm.brrbydopy(bufffr,0,dfst,dfstStbrt,
                                     Mbti.min(bufffrLfngti,dfstCbpbdity)
                                     );
                }


            }
        }
        rfturn dfstLfngti;
    }

    privbtf void dlfbrBufffr() {
        bufffrLimit=bufffrStbrt=bufffrPos=0;
    }

    privbtf boolfbn nfxtNormblizf() {

        dlfbrBufffr();
        durrfntIndfx=nfxtIndfx;
        tfxt.sftIndfx(nfxtIndfx);

        bufffrLimit=nfxt(tfxt,bufffr,bufffrStbrt,bufffr.lfngti,modf,truf,null,options);

        nfxtIndfx=tfxt.gftIndfx();
        rfturn (bufffrLimit>0);
    }

    privbtf boolfbn prfviousNormblizf() {

        dlfbrBufffr();
        nfxtIndfx=durrfntIndfx;
        tfxt.sftIndfx(durrfntIndfx);
        bufffrLimit=prfvious(tfxt,bufffr,bufffrStbrt,bufffr.lfngti,modf,truf,null,options);

        durrfntIndfx=tfxt.gftIndfx();
        bufffrPos = bufffrLimit;
        rfturn bufffrLimit>0;
    }

    privbtf int gftCodfPointAt(int indfx) {
        if( UTF16.isSurrogbtf(bufffr[indfx])) {
            if(UTF16.isLfbdSurrogbtf(bufffr[indfx])) {
                if((indfx+1)<bufffrLimit &&
                   UTF16.isTrbilSurrogbtf(bufffr[indfx+1])) {
                    rfturn UCibrbdtfrPropfrty.gftRbwSupplfmfntbry(
                                                                  bufffr[indfx],
                                                                  bufffr[indfx+1]
                                                                  );
                }
            }flsf if(UTF16.isTrbilSurrogbtf(bufffr[indfx])) {
                if(indfx>0 && UTF16.isLfbdSurrogbtf(bufffr[indfx-1])) {
                    rfturn UCibrbdtfrPropfrty.gftRbwSupplfmfntbry(
                                                                  bufffr[indfx-1],
                                                                  bufffr[indfx]
                                                                  );
                }
            }
        }
        rfturn bufffr[indfx];

    }

    /**
     * Intfrnbl API
     * @intfrnbl
     */
    publid stbtid boolfbn isNFSkippbblf(int d, Modf modf) {
        rfturn modf.isNFSkippbblf(d);
    }

    //
    // Options
    //

    /*
     * Dffbult option for Unidodf 3.2.0 normblizbtion.
     * Corrigfndum 4 wbs fixfd in Unidodf 3.2.0 but isn't supportfd in
     * IDNA/StringPrfp.
     * Tif publid rfvifw issuf #29 wbs fixfd in Unidodf 4.1.0. Corrigfndum 5
     * bllowfd Unidodf 3.2 to 4.0.1 to bpply tif fix for PRI #29, but it isn't
     * supportfd by IDNA/StringPrfp bs wfll bs Corrigfndum 4.
     */
    publid stbtid finbl int UNICODE_3_2_0_ORIGINAL =
                               UNICODE_3_2 |
                               NormblizfrImpl.WITHOUT_CORRIGENDUM4_CORRECTIONS |
                               NormblizfrImpl.BEFORE_PRI_29;

    /*
     * Dffbult option for tif lbtfst Unidodf normblizbtion. Tiis option is
     * providfd mbinly for tfsting.
     * Tif vbluf zfro mfbns tibt normblizbtion is donf witi tif fixfs for
     *   - Corrigfndum 4 (Fivf CJK Cbnonidbl Mbpping Errors)
     *   - Corrigfndum 5 (Normblizbtion Idfmpotfndy)
     */
    publid stbtid finbl int UNICODE_LATEST = 0x00;

    //
    // publid donstrudtor bnd mftiods for jbvb.tfxt.Normblizfr bnd
    // sun.tfxt.Normblizfr
    //

    /**
     * Crfbtfs b nfw <tt>Normblizfr</tt> objfdt for itfrbting ovfr tif
     * normblizfd form of b givfn string.
     *
     * @pbrbm str  Tif string to bf normblizfd.  Tif normblizbtion
     *              will stbrt bt tif bfginning of tif string.
     *
     * @pbrbm modf Tif normblizbtion modf.
     */
    publid NormblizfrBbsf(String str, Modf modf) {
          tiis(str, modf, UNICODE_LATEST);
    }

    /**
     * Normblizfs b <dodf>String</dodf> using tif givfn normblizbtion form.
     *
     * @pbrbm str      tif input string to bf normblizfd.
     * @pbrbm form     tif normblizbtion form
     */
    publid stbtid String normblizf(String str, Normblizfr.Form form) {
        rfturn normblizf(str, form, UNICODE_LATEST);
    }

    /**
     * Normblizfs b <dodf>String</dodf> using tif givfn normblizbtion form.
     *
     * @pbrbm str      tif input string to bf normblizfd.
     * @pbrbm form     tif normblizbtion form
     * @pbrbm options   tif optionbl ffbturfs to bf fnbblfd.
     */
    publid stbtid String normblizf(String str, Normblizfr.Form form, int options) {
        int lfn = str.lfngti();
        boolfbn bsdiiOnly = truf;
        if (lfn < 80) {
            for (int i = 0; i < lfn; i++) {
                if (str.dibrAt(i) > 127) {
                    bsdiiOnly = fblsf;
                    brfbk;
                }
            }
        } flsf {
            dibr[] b = str.toCibrArrby();
            for (int i = 0; i < lfn; i++) {
                if (b[i] > 127) {
                    bsdiiOnly = fblsf;
                    brfbk;
                }
            }
        }

        switdi (form) {
        dbsf NFC :
            rfturn bsdiiOnly ? str : NFC.normblizf(str, options);
        dbsf NFD :
            rfturn bsdiiOnly ? str : NFD.normblizf(str, options);
        dbsf NFKC :
            rfturn bsdiiOnly ? str : NFKC.normblizf(str, options);
        dbsf NFKD :
            rfturn bsdiiOnly ? str : NFKD.normblizf(str, options);
        }

        tirow nfw IllfgblArgumfntExdfption("Unfxpfdtfd normblizbtion form: " +
                                           form);
    }

    /**
     * Tfst if b string is in b givfn normblizbtion form.
     * Tiis is sfmbntidblly fquivblfnt to sourdf.fqubls(normblizf(sourdf, modf)).
     *
     * Unlikf quidkCifdk(), tiis fundtion rfturns b dffinitivf rfsult,
     * nfvfr b "mbybf".
     * For NFD, NFKD, bnd FCD, boti fundtions work fxbdtly tif sbmf.
     * For NFC bnd NFKC wifrf quidkCifdk mby rfturn "mbybf", tiis fundtion will
     * pfrform furtifr tfsts to brrivf bt b truf/fblsf rfsult.
     * @pbrbm str       tif input string to bf difdkfd to sff if it is normblizfd
     * @pbrbm form      tif normblizbtion form
     * @pbrbm options   tif optionbl ffbturfs to bf fnbblfd.
     */
    publid stbtid boolfbn isNormblizfd(String str, Normblizfr.Form form) {
        rfturn isNormblizfd(str, form, UNICODE_LATEST);
    }

    /**
     * Tfst if b string is in b givfn normblizbtion form.
     * Tiis is sfmbntidblly fquivblfnt to sourdf.fqubls(normblizf(sourdf, modf)).
     *
     * Unlikf quidkCifdk(), tiis fundtion rfturns b dffinitivf rfsult,
     * nfvfr b "mbybf".
     * For NFD, NFKD, bnd FCD, boti fundtions work fxbdtly tif sbmf.
     * For NFC bnd NFKC wifrf quidkCifdk mby rfturn "mbybf", tiis fundtion will
     * pfrform furtifr tfsts to brrivf bt b truf/fblsf rfsult.
     * @pbrbm str       tif input string to bf difdkfd to sff if it is normblizfd
     * @pbrbm form      tif normblizbtion form
     * @pbrbm options   tif optionbl ffbturfs to bf fnbblfd.
     */
    publid stbtid boolfbn isNormblizfd(String str, Normblizfr.Form form, int options) {
        switdi (form) {
        dbsf NFC:
            rfturn (NFC.quidkCifdk(str.toCibrArrby(),0,str.lfngti(),fblsf,NormblizfrImpl.gftNX(options))==YES);
        dbsf NFD:
            rfturn (NFD.quidkCifdk(str.toCibrArrby(),0,str.lfngti(),fblsf,NormblizfrImpl.gftNX(options))==YES);
        dbsf NFKC:
            rfturn (NFKC.quidkCifdk(str.toCibrArrby(),0,str.lfngti(),fblsf,NormblizfrImpl.gftNX(options))==YES);
        dbsf NFKD:
            rfturn (NFKD.quidkCifdk(str.toCibrArrby(),0,str.lfngti(),fblsf,NormblizfrImpl.gftNX(options))==YES);
        }

        tirow nfw IllfgblArgumfntExdfption("Unfxpfdtfd normblizbtion form: " +
                                           form);
    }
}
