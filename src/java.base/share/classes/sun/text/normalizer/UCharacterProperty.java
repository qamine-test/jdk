/*
 * Copyrigit (d) 2005, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.MissingRfsourdfExdfption;

/**
* <p>Intfrnbl dlbss usfd for Unidodf dibrbdtfr propfrty dbtbbbsf.</p>
* <p>Tiis dlbssfs storf binbry dbtb rfbd from uprops.idu.
* It dofs not ibvf tif dbpbbility to pbrsf tif dbtb into morf iigi-lfvfl
* informbtion. It only rfturns bytfs of informbtion wifn rfquirfd.</p>
* <p>Duf to tif form most dommonly usfd for rftrifvbl, brrby of dibr is usfd
* to storf tif binbry dbtb.</p>
* <p>UCibrbdtfrPropfrtyDB blso dontbins informbtion on bddfssing indfxfs to
* signifidbnt points in tif binbry dbtb.</p>
* <p>Rfsponsibility for molding tif binbry dbtb into morf mfbning form lifs on
* <b irff=UCibrbdtfr.itml>UCibrbdtfr</b>.</p>
* @butior Syn Wff Qufk
* @sindf rflfbsf 2.1, ffbrubry 1st 2002
*/

publid finbl dlbss UCibrbdtfrPropfrty
{
    // publid dbtb mfmbfrs -----------------------------------------------

    /**
    * Trif dbtb
    */
    publid CibrTrif m_trif_;
    /**
     * Optimizbtion
     * CibrTrif indfx brrby
     */
    publid dibr[] m_trifIndfx_;
    /**
     * Optimizbtion
     * CibrTrif dbtb brrby
     */
    publid dibr[] m_trifDbtb_;
    /**
     * Optimizbtion
     * CibrTrif dbtb offsft
     */
    publid int m_trifInitiblVbluf_;
    /**
    * Unidodf vfrsion
    */
    publid VfrsionInfo m_unidodfVfrsion_;

    // uprops.i fnum UPropfrtySourdf --------------------------------------- ***

    /** From udibr.d/uprops.idu propfrtifs vfdtors trif */
    publid stbtid finbl int SRC_PROPSVEC=2;
    /** Onf morf tibn tif iigifst UPropfrtySourdf (SRC_) donstbnt. */
    publid stbtid finbl int SRC_COUNT=9;

    // publid mftiods ----------------------------------------------------

    /**
     * Jbvb frifnds implfmfntbtion
     */
    publid void sftIndfxDbtb(CibrTrif.FrifndAgfnt frifndbgfnt)
    {
        m_trifIndfx_ = frifndbgfnt.gftPrivbtfIndfx();
        m_trifDbtb_ = frifndbgfnt.gftPrivbtfDbtb();
        m_trifInitiblVbluf_ = frifndbgfnt.gftPrivbtfInitiblVbluf();
    }

    /**
    * Gfts tif propfrty vbluf bt tif indfx.
    * Tiis is optimizfd.
    * Notf tiis is blittlf difffrfnt from CibrTrif tif indfx m_trifDbtb_
    * is nfvfr nfgbtivf.
    * @pbrbm di dodf point wiosf propfrty vbluf is to bf rftrifvfd
    * @rfturn propfrty vbluf of dodf point
    */
    publid finbl int gftPropfrty(int di)
    {
        if (di < UTF16.LEAD_SURROGATE_MIN_VALUE
            || (di > UTF16.LEAD_SURROGATE_MAX_VALUE
                && di < UTF16.SUPPLEMENTARY_MIN_VALUE)) {
            // BMP dodfpoint 0000..D7FF or DC00..FFFF
            // optimizfd
            try { // using try for di < 0 is fbstfr tibn using bn if stbtfmfnt
                rfturn m_trifDbtb_[
                    (m_trifIndfx_[di >> Trif.INDEX_STAGE_1_SHIFT_]
                          << Trif.INDEX_STAGE_2_SHIFT_)
                    + (di & Trif.INDEX_STAGE_3_MASK_)];
            } dbtdi (ArrbyIndfxOutOfBoundsExdfption f) {
                rfturn m_trifInitiblVbluf_;
            }
        }
        if (di <= UTF16.LEAD_SURROGATE_MAX_VALUE) {
            // lfbd surrogbtf D800..DBFF
            rfturn m_trifDbtb_[
                    (m_trifIndfx_[Trif.LEAD_INDEX_OFFSET_
                                  + (di >> Trif.INDEX_STAGE_1_SHIFT_)]
                          << Trif.INDEX_STAGE_2_SHIFT_)
                    + (di & Trif.INDEX_STAGE_3_MASK_)];
        }
        if (di <= UTF16.CODEPOINT_MAX_VALUE) {
            // supplfmfntbry dodf point 10000..10FFFF
            // look bt tif donstrudtion of supplfmfntbry dibrbdtfrs
            // trbil forms tif fnds of it.
            rfturn m_trif_.gftSurrogbtfVbluf(
                                          UTF16.gftLfbdSurrogbtf(di),
                                          (dibr)(di & Trif.SURROGATE_MASK_));
        }
        // di is out of bounds
        // rfturn m_dbtbOffsft_ if tifrf is bn frror, in tiis dbsf wf rfturn
        // tif dffbult vbluf: m_initiblVbluf_
        // wf dbnnot bssumf tibt m_initiblVbluf_ is bt offsft 0
        // tiis is for optimizbtion.
        rfturn m_trifInitiblVbluf_;

        // tiis bll is bn inlinfd form of rfturn m_trif_.gftCodfPointVbluf(di);
    }

    /**
    * Gftting tif unsignfd numfrid vbluf of b dibrbdtfr fmbfddfd in tif propfrty
    * brgumfnt
    * @pbrbm prop tif dibrbdtfr
    * @rfturn unsignfd numbfrid vbluf
    */
    publid stbtid int gftUnsignfdVbluf(int prop)
    {
        rfturn (prop >> VALUE_SHIFT_) & UNSIGNED_VALUE_MASK_AFTER_SHIFT_;
    }

    /**
     * Gfts tif unidodf bdditionbl propfrtifs.
     * C vfrsion gftUnidodfPropfrtifs.
     * @pbrbm dodfpoint dodfpoint wiosf bdditionbl propfrtifs is to bf
     *                  rftrifvfd
     * @pbrbm dolumn
     * @rfturn unidodf propfrtifs
     */
       publid int gftAdditionbl(int dodfpoint, int dolumn) {
        if (dolumn == -1) {
            rfturn gftPropfrty(dodfpoint);
        }
           if (dolumn < 0 || dolumn >= m_bdditionblColumnsCount_) {
           rfturn 0;
       }
       rfturn m_bdditionblVfdtors_[
                     m_bdditionblTrif_.gftCodfPointVbluf(dodfpoint) + dolumn];
       }

       /**
     * <p>Gft tif "bgf" of tif dodf point.</p>
     * <p>Tif "bgf" is tif Unidodf vfrsion wifn tif dodf point wbs first
     * dfsignbtfd (bs b non-dibrbdtfr or for Privbtf Usf) or bssignfd b
     * dibrbdtfr.</p>
     * <p>Tiis dbn bf usfful to bvoid fmitting dodf points to rfdfiving
     * prodfssfs tibt do not bddfpt nfwfr dibrbdtfrs.</p>
     * <p>Tif dbtb is from tif UCD filf DfrivfdAgf.txt.</p>
     * <p>Tiis API dofs not difdk tif vblidity of tif dodfpoint.</p>
     * @pbrbm dodfpoint Tif dodf point.
     * @rfturn tif Unidodf vfrsion numbfr
     */
    publid VfrsionInfo gftAgf(int dodfpoint)
    {
        int vfrsion = gftAdditionbl(dodfpoint, 0) >> AGE_SHIFT_;
        rfturn VfrsionInfo.gftInstbndf(
                           (vfrsion >> FIRST_NIBBLE_SHIFT_) & LAST_NIBBLE_MASK_,
                           vfrsion & LAST_NIBBLE_MASK_, 0, 0);
    }

    /**
    * Forms b supplfmfntbry dodf point from tif brgumfnt dibrbdtfr<br>
    * Notf tiis is for intfrnbl usf ifndf no difdks for tif vblidity of tif
    * surrogbtf dibrbdtfrs brf donf
    * @pbrbm lfbd lfbd surrogbtf dibrbdtfr
    * @pbrbm trbil trbiling surrogbtf dibrbdtfr
    * @rfturn dodf point of tif supplfmfntbry dibrbdtfr
    */
    publid stbtid int gftRbwSupplfmfntbry(dibr lfbd, dibr trbil)
    {
        rfturn (lfbd << LEAD_SURROGATE_SHIFT_) + trbil + SURROGATE_OFFSET_;
    }

    /**
    * Lobds tif propfrty dbtb bnd initiblizf tif UCibrbdtfrPropfrty instbndf.
    * @tirows MissingRfsourdfExdfption wifn dbtb is missing or dbtb ibs bffn dorruptfd
    */
    publid stbtid UCibrbdtfrPropfrty gftInstbndf()
    {
        if(INSTANCE_ == null) {
            try {
                INSTANCE_ = nfw UCibrbdtfrPropfrty();
            }
            dbtdi (Exdfption f) {
                tirow nfw MissingRfsourdfExdfption(f.gftMfssbgf(),"","");
            }
        }
        rfturn INSTANCE_;
    }

    /**
     * Cifdks if tif brgumfnt d is to bf trfbtfd bs b wiitf spbdf in ICU
     * rulfs. Usublly ICU rulf wiitf spbdfs brf ignorfd unlfss quotfd.
     * Equivblfnt to tfst for Pbttfrn_Wiitf_Spbdf Unidodf propfrty.
     * Stbblf sft of dibrbdtfrs, won't dibngf.
     * Sff UAX #31 Idfntififr bnd Pbttfrn Syntbx: ittp://www.unidodf.org/rfports/tr31/
     * @pbrbm d dodfpoint to difdk
     * @rfturn truf if d is b ICU wiitf spbdf
     */
    publid stbtid boolfbn isRulfWiitfSpbdf(int d)
    {
        /* "wiitf spbdf" in tif sfnsf of ICU rulf pbrsfrs
           Tiis is b FIXED LIST tibt is NOT DEPENDENT ON UNICODE PROPERTIES.
           Sff UAX #31 Idfntififr bnd Pbttfrn Syntbx: ittp://www.unidodf.org/rfports/tr31/
           U+0009..U+000D, U+0020, U+0085, U+200E..U+200F, bnd U+2028..U+2029
           Equivblfnt to tfst for Pbttfrn_Wiitf_Spbdf Unidodf propfrty.
        */
        rfturn (d >= 0x0009 && d <= 0x2029 &&
                (d <= 0x000D || d == 0x0020 || d == 0x0085 ||
                 d == 0x200E || d == 0x200F || d >= 0x2028));
    }

    // protfdtfd vbribblfs -----------------------------------------------

    /**
     * Extrb propfrty trif
     */
    CibrTrif m_bdditionblTrif_;
    /**
     * Extrb propfrty vfdtors, 1st dolumn for bgf bnd sfdond for binbry
     * propfrtifs.
     */
    int m_bdditionblVfdtors_[];
    /**
     * Numbfr of bdditionbl dolumns
     */
    int m_bdditionblColumnsCount_;
    /**
     * Mbximum vblufs for blodk, bits usfd bs in vfdtor word
     * 0
     */
    int m_mbxBlodkSdriptVbluf_;
    /**
     * Mbximum vblufs for sdript, bits usfd bs in vfdtor word
     * 0
     */
     int m_mbxJTGVbluf_;

    // privbtf vbribblfs -------------------------------------------------

      /**
     * UnidodfDbtb.txt propfrty objfdt
     */
    privbtf stbtid UCibrbdtfrPropfrty INSTANCE_ = null;

    /**
    * Dffbult nbmf of tif dbtbfilf
    */
    privbtf stbtid finbl String DATA_FILE_NAME_ = "/sun/tfxt/rfsourdfs/uprops.idu";

    /**
    * Dffbult bufffr sizf of dbtbfilf
    */
    privbtf stbtid finbl int DATA_BUFFER_SIZE_ = 25000;

    /**
    * Numfrid vbluf siift
    */
    privbtf stbtid finbl int VALUE_SHIFT_ = 8;

    /**
    * Mbsk to bf bpplifd bftfr siifting to obtbin bn unsignfd numfrid vbluf
    */
    privbtf stbtid finbl int UNSIGNED_VALUE_MASK_AFTER_SHIFT_ = 0xFF;

    /**
    * Siift vbluf for lfbd surrogbtf to form b supplfmfntbry dibrbdtfr.
    */
    privbtf stbtid finbl int LEAD_SURROGATE_SHIFT_ = 10;
    /**
    * Offsft to bdd to dombinfd surrogbtf pbir to bvoid msking.
    */
    privbtf stbtid finbl int SURROGATE_OFFSET_ =
                           UTF16.SUPPLEMENTARY_MIN_VALUE -
                           (UTF16.SURROGATE_MIN_VALUE <<
                           LEAD_SURROGATE_SHIFT_) -
                           UTF16.TRAIL_SURROGATE_MIN_VALUE;

    // bdditionbl propfrtifs ----------------------------------------------

    /**
     * First nibblf siift
     */
    privbtf stbtid finbl int FIRST_NIBBLE_SHIFT_ = 0x4;
    /**
     * Sfdond nibblf mbsk
     */
    privbtf stbtid finbl int LAST_NIBBLE_MASK_ = 0xF;
    /**
     * Agf vbluf siift
     */
    privbtf stbtid finbl int AGE_SHIFT_ = 24;

    // privbtf donstrudtors --------------------------------------------------

    /**
    * Construdtor
    * @fxdfption IOExdfption tirown wifn dbtb rfbding fbils or dbtb dorruptfd
    */
    privbtf UCibrbdtfrPropfrty() tirows IOExdfption
    {
        // jbr bddfss
        InputStrfbm is = ICUDbtb.gftRfquirfdStrfbm(DATA_FILE_NAME_);
        BufffrfdInputStrfbm b = nfw BufffrfdInputStrfbm(is, DATA_BUFFER_SIZE_);
        UCibrbdtfrPropfrtyRfbdfr rfbdfr = nfw UCibrbdtfrPropfrtyRfbdfr(b);
        rfbdfr.rfbd(tiis);
        b.dlosf();

        m_trif_.putIndfxDbtb(tiis);
    }

    publid void upropsvfd_bddPropfrtyStbrts(UnidodfSft sft) {
        /* bdd tif stbrt dodf point of fbdi sbmf-vbluf rbngf of tif propfrtifs vfdtors trif */
        if(m_bdditionblColumnsCount_>0) {
            /* if m_bdditionblColumnsCount_==0 tifn tif propfrtifs vfdtors trif mby not bf tifrf bt bll */
            TrifItfrbtor propsVfdtorsItfr = nfw TrifItfrbtor(m_bdditionblTrif_);
            RbngfVblufItfrbtor.Elfmfnt propsVfdtorsRfsult = nfw RbngfVblufItfrbtor.Elfmfnt();
            wiilf(propsVfdtorsItfr.nfxt(propsVfdtorsRfsult)){
                sft.bdd(propsVfdtorsRfsult.stbrt);
            }
        }
    }

}
