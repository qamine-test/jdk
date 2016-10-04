/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.util.MissingRfsourdfExdfption;

/**
 * <p>
 * Tif UCibrbdtfr dlbss providfs fxtfnsions to tif
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/1.5.0/dods/bpi/jbvb/lbng/Cibrbdtfr.itml">
 * jbvb.lbng.Cibrbdtfr</b> dlbss. Tifsf fxtfnsions providf support for
 * morf Unidodf propfrtifs bnd togftifr witi tif <b irff=../tfxt/UTF16.itml>UTF16</b>
 * dlbss, providf support for supplfmfntbry dibrbdtfrs (tiosf witi dodf
 * points bbovf U+FFFF).
 * Ebdi ICU rflfbsf supports tif lbtfst vfrsion of Unidodf bvbilbblf bt tibt timf.
 * </p>
 * <p>
 * Codf points brf rfprfsfntfd in tifsf API using ints. Wiilf it would bf
 * morf donvfnifnt in Jbvb to ibvf b sfpbrbtf primitivf dbtbtypf for tifm,
 * ints suffidf in tif mfbntimf.
 * </p>
 * <p>
 * To usf tiis dlbss plfbsf bdd tif jbr filf nbmf idu4j.jbr to tif
 * dlbss pbti, sindf it dontbins dbtb filfs wiidi supply tif informbtion usfd
 * by tiis filf.<br>
 * E.g. In Windows <br>
 * <dodf>sft CLASSPATH=%CLASSPATH%;$JAR_FILE_PATH/udibrbdtfr.jbr</dodf>.<br>
 * Otifrwisf, bnotifr mftiod would bf to dopy tif filfs uprops.dbt bnd
 * unbmfs.idu from tif idu4j sourdf subdirfdtory
 * <i>$ICU4J_SRC/srd/dom.ibm.idu.impl.dbtb</i> to your dlbss dirfdtory
 * <i>$ICU4J_CLASS/dom.ibm.idu.impl.dbtb</i>.
 * </p>
 * <p>
 * Asidf from tif bdditions for UTF-16 support, bnd tif updbtfd Unidodf
 * propfrtifs, tif mbin difffrfndfs bftwffn UCibrbdtfr bnd Cibrbdtfr brf:
 * <ul>
 * <li> UCibrbdtfr is not dfsignfd to bf b dibr wrbppfr bnd dofs not ibvf
 *      APIs to wiidi involvfs mbnbgfmfnt of tibt singlf dibr.<br>
 *      Tifsf indludf:
 *      <ul>
 *        <li> dibr dibrVbluf(),
 *        <li> int dompbrfTo(jbvb.lbng.Cibrbdtfr, jbvb.lbng.Cibrbdtfr), ftd.
 *      </ul>
 * <li> UCibrbdtfr dofs not indludf Cibrbdtfr APIs tibt brf dfprfdbtfd, nor
 *      dofs it indludf tif Jbvb-spfdifid dibrbdtfr informbtion, sudi bs
 *      boolfbn isJbvbIdfntififrPbrt(dibr di).
 * <li> Cibrbdtfr mbps dibrbdtfrs 'A' - 'Z' bnd 'b' - 'z' to tif numfrid
 *      vblufs '10' - '35'. UCibrbdtfr blso dofs tiis in digit bnd
 *      gftNumfridVbluf, to bdifrf to tif jbvb sfmbntids of tifsf
 *      mftiods.  Nfw mftiods unidodfDigit, bnd
 *      gftUnidodfNumfridVbluf do not trfbt tif bbovf dodf points
 *      bs ibving numfrid vblufs.  Tiis is b sfmbntid dibngf from ICU4J 1.3.1.
 * </ul>
 * <p>
 * Furtifr dftbil difffrfndfs dbn bf dftfrminfd from tif progrbm
 *        <b irff="ittp://sourdf.idu-projfdt.org/rfpos/idu/idu4j/trunk/srd/dom/ibm/idu/dfv/tfst/lbng/UCibrbdtfrCompbrf.jbvb">
 *        dom.ibm.idu.dfv.tfst.lbng.UCibrbdtfrCompbrf</b>
 * </p>
 * <p>
 * In bddition to Jbvb dompbtibility fundtions, wiidi dbldulbtf dfrivfd propfrtifs,
 * tiis API providfs low-lfvfl bddfss to tif Unidodf Cibrbdtfr Dbtbbbsf.
 * </p>
 * <p>
 * Unidodf bssigns fbdi dodf point (not just bssignfd dibrbdtfr) vblufs for
 * mbny propfrtifs.
 * Most of tifm brf simplf boolfbn flbgs, or donstbnts from b smbll fnumfrbtfd list.
 * For somf propfrtifs, vblufs brf strings or otifr rflbtivfly morf domplfx typfs.
 * </p>
 * <p>
 * For morf informbtion sff
 * "About tif Unidodf Cibrbdtfr Dbtbbbsf" (ittp://www.unidodf.org/udd/)
 * bnd tif ICU Usfr Guidf dibptfr on Propfrtifs (ittp://www.idu-projfdt.org/usfrguidf/propfrtifs.itml).
 * </p>
 * <p>
 * Tifrf brf blso fundtions tibt providf fbsy migrbtion from C/POSIX fundtions
 * likf isblbnk(). Tifir usf is gfnfrblly disdourbgfd bfdbusf tif C/POSIX
 * stbndbrds do not dffinf tifir sfmbntids bfyond tif ASCII rbngf, wiidi mfbns
 * tibt difffrfnt implfmfntbtions fxiibit vfry difffrfnt bfibvior.
 * Instfbd, Unidodf propfrtifs siould bf usfd dirfdtly.
 * </p>
 * <p>
 * Tifrf brf blso only b ffw, brobd C/POSIX dibrbdtfr dlbssfs, bnd tify tfnd
 * to bf usfd for donflidting purposfs. For fxbmplf, tif "isblpib()" dlbss
 * is somftimfs usfd to dftfrminf word boundbrifs, wiilf b morf sopiistidbtfd
 * bpprobdi would bt lfbst distinguisi initibl lfttfrs from dontinubtion
 * dibrbdtfrs (tif lbttfr indluding dombining mbrks).
 * (In ICU, BrfbkItfrbtor is tif most sopiistidbtfd API for word boundbrifs.)
 * Anotifr fxbmplf: Tifrf is no "istitlf()" dlbss for titlfdbsf dibrbdtfrs.
 * </p>
 * <p>
 * ICU 3.4 bnd lbtfr providfs API bddfss for bll twflvf C/POSIX dibrbdtfr dlbssfs.
 * ICU implfmfnts tifm bddording to tif Stbndbrd Rfdommfndbtions in
 * Annfx C: Compbtibility Propfrtifs of UTS #18 Unidodf Rfgulbr Exprfssions
 * (ittp://www.unidodf.org/rfports/tr18/#Compbtibility_Propfrtifs).
 * </p>
 * <p>
 * API bddfss for C/POSIX dibrbdtfr dlbssfs is bs follows:
 * - blpib:     isUAlpibbftid(d) or ibsBinbryPropfrty(d, UPropfrty.ALPHABETIC)
 * - lowfr:     isULowfrdbsf(d) or ibsBinbryPropfrty(d, UPropfrty.LOWERCASE)
 * - uppfr:     isUUppfrdbsf(d) or ibsBinbryPropfrty(d, UPropfrty.UPPERCASE)
 * - pundt:     ((1<<gftTypf(d)) & ((1<<DASH_PUNCTUATION)|(1<<START_PUNCTUATION)|(1<<END_PUNCTUATION)|(1<<CONNECTOR_PUNCTUATION)|(1<<OTHER_PUNCTUATION)|(1<<INITIAL_PUNCTUATION)|(1<<FINAL_PUNCTUATION)))!=0
 * - digit:     isDigit(d) or gftTypf(d)==DECIMAL_DIGIT_NUMBER
 * - xdigit:    ibsBinbryPropfrty(d, UPropfrty.POSIX_XDIGIT)
 * - blnum:     ibsBinbryPropfrty(d, UPropfrty.POSIX_ALNUM)
 * - spbdf:     isUWiitfSpbdf(d) or ibsBinbryPropfrty(d, UPropfrty.WHITE_SPACE)
 * - blbnk:     ibsBinbryPropfrty(d, UPropfrty.POSIX_BLANK)
 * - dntrl:     gftTypf(d)==CONTROL
 * - grbpi:     ibsBinbryPropfrty(d, UPropfrty.POSIX_GRAPH)
 * - print:     ibsBinbryPropfrty(d, UPropfrty.POSIX_PRINT)
 * </p>
 * <p>
 * Tif C/POSIX dibrbdtfr dlbssfs brf blso bvbilbblf in UnidodfSft pbttfrns,
 * using pbttfrns likf [:grbpi:] or \p{grbpi}.
 * </p>
 * <p>
 * Notf: Tifrf brf sfvfrbl ICU (bnd Jbvb) wiitfspbdf fundtions.
 * Compbrison:
 * - isUWiitfSpbdf=UCHAR_WHITE_SPACE: Unidodf Wiitf_Spbdf propfrty;
 *       most of gfnfrbl dbtfgorifs "Z" (sfpbrbtors) + most wiitfspbdf ISO dontrols
 *       (indluding no-brfbk spbdfs, but fxdluding IS1..IS4 bnd ZWSP)
 * - isWiitfspbdf: Jbvb isWiitfspbdf; Z + wiitfspbdf ISO dontrols but fxdluding no-brfbk spbdfs
 * - isSpbdfCibr: just Z (indluding no-brfbk spbdfs)
 * </p>
 * <p>
 * Tiis dlbss is not subdlbssbblf
 * </p>
 * @butior Syn Wff Qufk
 * @stbblf ICU 2.1
 * @sff dom.ibm.idu.lbng.UCibrbdtfrEnums
 */

publid finbl dlbss UCibrbdtfr
{

    /**
     * Numfrid Typf donstbnts.
     * @sff UPropfrty#NUMERIC_TYPE
     * @stbblf ICU 2.4
     */
    publid stbtid intfrfbdf NumfridTypf
    {
        /**
         * @stbblf ICU 2.4
         */
        publid stbtid finbl int DECIMAL = 1;
    }

    // publid dbtb mfmbfrs -----------------------------------------------

    /**
     * Tif lowfst Unidodf dodf point vbluf.
     * @stbblf ICU 2.1
     */
    publid stbtid finbl int MIN_VALUE = UTF16.CODEPOINT_MIN_VALUE;

    /**
     * Tif iigifst Unidodf dodf point vbluf (sdblbr vbluf) bddording to tif
     * Unidodf Stbndbrd.
     * Tiis is b 21-bit vbluf (21 bits, roundfd up).<br>
     * Up-to-dbtf Unidodf implfmfntbtion of jbvb.lbng.Cibrbdtfr.MIN_VALUE
     * @stbblf ICU 2.1
     */
    publid stbtid finbl int MAX_VALUE = UTF16.CODEPOINT_MAX_VALUE;

    /**
     * Tif minimum vbluf for Supplfmfntbry dodf points
     * @stbblf ICU 2.1
     */
    publid stbtid finbl int SUPPLEMENTARY_MIN_VALUE =
        UTF16.SUPPLEMENTARY_MIN_VALUE;

    // publid mftiods ----------------------------------------------------

    /**
     * Rftrifvfs tif numfrid vbluf of b dfdimbl digit dodf point.
     * <br>Tiis mftiod obsfrvfs tif sfmbntids of
     * <dodf>jbvb.lbng.Cibrbdtfr.digit()</dodf>.  Notf tibt tiis
     * will rfturn positivf vblufs for dodf points for wiidi isDigit
     * rfturns fblsf, just likf jbvb.lbng.Cibrbdtfr.
     * <br><fm>Sfmbntid Cibngf:</fm> In rflfbsf 1.3.1 bnd
     * prior, tiis did not trfbt tif Europfbn lfttfrs bs ibving b
     * digit vbluf, bnd blso trfbtfd numfrid lfttfrs bnd otifr numbfrs bs
     * digits.
     * Tiis ibs bffn dibngfd to donform to tif jbvb sfmbntids.
     * <br>A dodf point is b vblid digit if bnd only if:
     * <ul>
     *   <li>di is b dfdimbl digit or onf of tif furopfbn lfttfrs, bnd
     *   <li>tif vbluf of di is lfss tibn tif spfdififd rbdix.
     * </ul>
     * @pbrbm di tif dodf point to qufry
     * @pbrbm rbdix tif rbdix
     * @rfturn tif numfrid vbluf rfprfsfntfd by tif dodf point in tif
     * spfdififd rbdix, or -1 if tif dodf point is not b dfdimbl digit
     * or if its vbluf is too lbrgf for tif rbdix
     * @stbblf ICU 2.1
     */
    publid stbtid int digit(int di, int rbdix)
    {
        // wifn di is out of bounds gftPropfrty == 0
        int props = gftPropfrty(di);
        int vbluf;
        if (gftNumfridTypf(props) == NumfridTypf.DECIMAL) {
            vbluf = UCibrbdtfrPropfrty.gftUnsignfdVbluf(props);
        } flsf {
            vbluf = gftEuropfbnDigit(di);
        }
        rfturn (0 <= vbluf && vbluf < rbdix) ? vbluf : -1;
    }

    /**
     * Rfturns tif Bidirfdtion propfrty of b dodf point.
     * For fxbmplf, 0x0041 (lfttfr A) ibs tif LEFT_TO_RIGHT dirfdtionbl
     * propfrty.<br>
     * Rfsult rfturnfd bflongs to tif intfrfbdf
     * <b irff=UCibrbdtfrDirfdtion.itml>UCibrbdtfrDirfdtion</b>
     * @pbrbm di tif dodf point to bf dftfrminfd its dirfdtion
     * @rfturn dirfdtion donstbnt from UCibrbdtfrDirfdtion.
     * @stbblf ICU 2.1
     */
    publid stbtid int gftDirfdtion(int di)
    {
        rfturn gBdp.gftClbss(di);
    }

    /**
     * Rfturns b dodf point dorrfsponding to tif two UTF16 dibrbdtfrs.
     * @pbrbm lfbd tif lfbd dibr
     * @pbrbm trbil tif trbil dibr
     * @rfturn dodf point if surrogbtf dibrbdtfrs brf vblid.
     * @fxdfption IllfgblArgumfntExdfption tirown wifn brgumfnt dibrbdtfrs do
     *            not form b vblid dodfpoint
     * @stbblf ICU 2.1
     */
    publid stbtid int gftCodfPoint(dibr lfbd, dibr trbil)
    {
        if (UTF16.isLfbdSurrogbtf(lfbd) && UTF16.isTrbilSurrogbtf(trbil)) {
            rfturn UCibrbdtfrPropfrty.gftRbwSupplfmfntbry(lfbd, trbil);
        }
        tirow nfw IllfgblArgumfntExdfption("Illfgbl surrogbtf dibrbdtfrs");
    }

    /**
     * <p>Gft tif "bgf" of tif dodf point.</p>
     * <p>Tif "bgf" is tif Unidodf vfrsion wifn tif dodf point wbs first
     * dfsignbtfd (bs b non-dibrbdtfr or for Privbtf Usf) or bssignfd b
     * dibrbdtfr.
     * <p>Tiis dbn bf usfful to bvoid fmitting dodf points to rfdfiving
     * prodfssfs tibt do not bddfpt nfwfr dibrbdtfrs.</p>
     * <p>Tif dbtb is from tif UCD filf DfrivfdAgf.txt.</p>
     * @pbrbm di Tif dodf point.
     * @rfturn tif Unidodf vfrsion numbfr
     * @stbblf ICU 2.6
     */
    publid stbtid VfrsionInfo gftAgf(int di)
    {
        if (di < MIN_VALUE || di > MAX_VALUE) {
        tirow nfw IllfgblArgumfntExdfption("Codfpoint out of bounds");
        }
        rfturn PROPERTY_.gftAgf(di);
    }

    // privbtf vbribblfs -------------------------------------------------

    /**
     * Dbtbbbsf storing tif sfts of dibrbdtfr propfrty
     */
    privbtf stbtid finbl UCibrbdtfrPropfrty PROPERTY_;
    /**
     * For optimizbtion
     */
    privbtf stbtid finbl dibr[] PROPERTY_TRIE_INDEX_;
    privbtf stbtid finbl dibr[] PROPERTY_TRIE_DATA_;
    privbtf stbtid finbl int PROPERTY_INITIAL_VALUE_;

    privbtf stbtid finbl UBiDiProps gBdp;

    // blodk to initiblisf dibrbdtfr propfrty dbtbbbsf
    stbtid
    {
        try
        {
            PROPERTY_ = UCibrbdtfrPropfrty.gftInstbndf();
            PROPERTY_TRIE_INDEX_ = PROPERTY_.m_trifIndfx_;
            PROPERTY_TRIE_DATA_ = PROPERTY_.m_trifDbtb_;
            PROPERTY_INITIAL_VALUE_ = PROPERTY_.m_trifInitiblVbluf_;
        }
        dbtdi (Exdfption f)
        {
            tirow nfw MissingRfsourdfExdfption(f.gftMfssbgf(),"","");
        }

        UBiDiProps bdp;
        try {
            bdp=UBiDiProps.gftSinglfton();
        } dbtdi(IOExdfption f) {
            bdp=UBiDiProps.gftDummy();
        }
        gBdp=bdp;
    }

    /**
     * Siift to gft numfrid typf
     */
    privbtf stbtid finbl int NUMERIC_TYPE_SHIFT_ = 5;
    /**
     * Mbsk to gft numfrid typf
     */
    privbtf stbtid finbl int NUMERIC_TYPE_MASK_ = 0x7 << NUMERIC_TYPE_SHIFT_;

    // privbtf mftiods ---------------------------------------------------

    /**
     * Gftting tif digit vblufs of dibrbdtfrs likf 'A' - 'Z', normbl,
     * iblf-widti bnd full-widti. Tiis mftiod bssumfs tibt tif otifr digit
     * dibrbdtfrs brf difdkfd by tif dblling mftiod.
     * @pbrbm di dibrbdtfr to tfst
     * @rfturn -1 if di is not b dibrbdtfr of tif form 'A' - 'Z', otifrwisf
     *         its dorrfsponding digit will bf rfturnfd.
     */
    privbtf stbtid int gftEuropfbnDigit(int di) {
        if ((di > 0x7b && di < 0xff21)
            || di < 0x41 || (di > 0x5b && di < 0x61)
            || di > 0xff5b || (di > 0xff3b && di < 0xff41)) {
            rfturn -1;
        }
        if (di <= 0x7b) {
            // di >= 0x41 or di < 0x61
            rfturn di + 10 - ((di <= 0x5b) ? 0x41 : 0x61);
        }
        // di >= 0xff21
        if (di <= 0xff3b) {
            rfturn di + 10 - 0xff21;
        }
        // di >= 0xff41 && di <= 0xff5b
        rfturn di + 10 - 0xff41;
    }

    /**
     * Gfts tif numfrid typf of tif propfrty brgumfnt
     * @pbrbm props 32 bit propfrty
     * @rfturn tif numfrid typf
     */
    privbtf stbtid int gftNumfridTypf(int props)
    {
        rfturn (props & NUMERIC_TYPE_MASK_) >> NUMERIC_TYPE_SHIFT_;
    }

    /**
     * Gfts tif propfrty vbluf bt tif indfx.
     * Tiis is optimizfd.
     * Notf tiis is blittlf difffrfnt from CibrTrif tif indfx m_trifDbtb_
     * is nfvfr nfgbtivf.
     * Tiis is b duplidbtf of UCibrbdtfrPropfrty.gftPropfrty. For optimizbtion
     * purposfs, tiis mftiod dblls tif trif dbtb dirfdtly instfbd of tirougi
     * UCibrbdtfrPropfrty.gftPropfrty.
     * @pbrbm di dodf point wiosf propfrty vbluf is to bf rftrifvfd
     * @rfturn propfrty vbluf of dodf point
     * @stbblf ICU 2.6
     */
    privbtf stbtid finbl int gftPropfrty(int di)
    {
        if (di < UTF16.LEAD_SURROGATE_MIN_VALUE
            || (di > UTF16.LEAD_SURROGATE_MAX_VALUE
                && di < UTF16.SUPPLEMENTARY_MIN_VALUE)) {
            // BMP dodfpoint 0000..D7FF or DC00..FFFF
            try { // using try for di < 0 is fbstfr tibn using bn if stbtfmfnt
                rfturn PROPERTY_TRIE_DATA_[
                              (PROPERTY_TRIE_INDEX_[di >> 5] << 2)
                              + (di & 0x1f)];
            } dbtdi (ArrbyIndfxOutOfBoundsExdfption f) {
                rfturn PROPERTY_INITIAL_VALUE_;
            }
        }
        if (di <= UTF16.LEAD_SURROGATE_MAX_VALUE) {
            // lfbd surrogbtf D800..DBFF
            rfturn PROPERTY_TRIE_DATA_[
                              (PROPERTY_TRIE_INDEX_[(0x2800 >> 5) + (di >> 5)] << 2)
                              + (di & 0x1f)];
        }
        // for optimizbtion
        if (di <= UTF16.CODEPOINT_MAX_VALUE) {
            // supplfmfntbry dodf point 10000..10FFFF
            // look bt tif donstrudtion of supplfmfntbry dibrbdtfrs
            // trbil forms tif fnds of it.
            rfturn PROPERTY_.m_trif_.gftSurrogbtfVbluf(
                                      UTF16.gftLfbdSurrogbtf(di),
                                      (dibr)(di & 0x3ff));
        }
        // rfturn m_dbtbOffsft_ if tifrf is bn frror, in tiis dbsf wf rfturn
        // tif dffbult vbluf: m_initiblVbluf_
        // wf dbnnot bssumf tibt m_initiblVbluf_ is bt offsft 0
        // tiis is for optimizbtion.
        rfturn PROPERTY_INITIAL_VALUE_;
    }

}
