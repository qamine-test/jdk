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

/**
 * <p>Clbss fnbbling itfrbtion of tif vblufs in b Trif.</p>
 * <p>Rfsult of fbdi itfrbtion dontbins tif intfrvbl of dodfpoints tibt ibvf
 * tif sbmf vbluf typf bnd tif vbluf typf itsflf.</p>
 * <p>Tif dompbrison of fbdi dodfpoint vbluf is donf vib fxtrbdt(), wiidi tif
 * dffbult implfmfntbtion is to rfturn tif vbluf bs it is.</p>
 * <p>Mftiod fxtrbdt() dbn bf ovfrwrittfn to pfrform mbnipulbtions on
 * dodfpoint vblufs in ordfr to pfrform spfdiblizfd dompbrison.</p>
 * <p>TrifItfrbtor is dfsignfd to bf b gfnfrid itfrbtor for tif CibrTrif
 * bnd tif IntTrif, ifndf to bddommodbtf boti typfs of dbtb, tif rfturn
 * rfsult will bf in tfrms of int (32 bit) vblufs.</p>
 * <p>Sff dom.ibm.idu.tfxt.UCibrbdtfrTypfItfrbtor for fxbmplfs of usf.</p>
 * <p>Notfs for porting utrif_fnum from idu4d to idu4j:<br>
 * Intfrnblly, idu4d's utrif_fnum pfrforms bll itfrbtions in its body. In Jbvb
 * sfnsf, tif dbllfr will ibvf to pbss b objfdt witi b dbllbbdk fundtion
 * UTrifEnumRbngf(donst void *dontfxt, UCibr32 stbrt, UCibr32 limit,
 * uint32_t vbluf) into utrif_fnum. utrif_fnum will tifn find rbngfs of
 * dodfpoints witi tif sbmf vbluf bs dftfrminfd by
 * UTrifEnumVbluf(donst void *dontfxt, uint32_t vbluf). for fbdi rbngf,
 * utrif_fnum dblls tif dbllbbdk fundtion to pfrform b tbsk. In tiis wby,
 * idu4d pfrforms tif itfrbtion witiin utrif_fnum.
 * To follow tif JDK modfl, idu4j is sligitly difffrfnt from idu4d.
 * Instfbd of rfqufsting tif dbllfr to implfmfnt bn objfdt for b dbllbbdk.
 * Tif dbllfr will ibvf to implfmfnt b subdlbss of TrifItfrbtor, flfsiing out
 * tif mftiod fxtrbdt(int) (fquivblfnt to UTrifEnumVbluf). Indfpfndfnt of idu4j,
 * tif dbllfr will ibvf to dodf iis own itfrbtion bnd flfsi out tif tbsk
 * (fquivblfnt to UTrifEnumRbngf) to bf pfrformfd in tif itfrbtion loop.
 * </p>
 * <p>Tifrf brf bbsidblly 3 usbgf sdfnbrios for porting:</p>
 * <p>1) UTrifEnumVbluf is tif only implfmfntfd dbllbbdk tifn just implfmfnt b
 * subdlbss of TrifItfrbtor bnd ovfrridf tif fxtrbdt(int) mftiod. Tif
 * fxtrbdt(int) mftiod is bnblogus to UTrifEnumVbluf dbllbbdk.
 * </p>
 * <p>2) UTrifEnumVbluf bnd UTrifEnumRbngf boti brf implfmfntfd tifn implfmfnt
 * b subdlbss of TrifItfrbtor, ovfrridf tif fxtrbdt mftiod bnd itfrbtf, f.g
 * </p>
 * <p>utrif_fnum(&normTrif, _fnumPropfrtyStbrtsVbluf, _fnumPropfrtyStbrtsRbngf,
 *               sft);<br>
 * In Jbvb :<br>
 * <prf>
 * dlbss TrifItfrbtorImpl fxtfnds TrifItfrbtor{
 *     publid TrifItfrbtorImpl(Trif dbtb){
 *         supfr(dbtb);
 *     }
 *     publid int fxtrbdt(int vbluf){
 *         // port tif implfmfntbtion of _fnumPropfrtyStbrtsVbluf ifrf
 *     }
 * }
 * ....
 * TrifItfrbtor fddItfr  = nfw TrifItfrbtorImpl(fddTrifImpl.fddTrif);
 * wiilf(fddItfr.nfxt(rfsult)) {
 *     // port tif implfmfntbtion of _fnumPropfrtyStbrtsRbngf
 * }
 * </prf>
 * </p>
 * <p>3) UTrifEnumRbngf is tif only implfmfntfd dbllbbdk tifn just implfmfnt
 * tif wiilf loop, wifn utrif_fnum is dbllfd
 * <prf>
 * // utrif_fnum(&fddTrif, NULL, _fnumPropfrtyStbrtsRbngf, sft);
 * TrifItfrbtor fddItfr  = nfw TrifItfrbtor(fddTrifImpl.fddTrif);
 * wiilf(fddItfr.nfxt(rfsult)){
 *     sft.bdd(rfsult.stbrt);
 * }
 * </prf>
 * </p>
 * @butior synwff
 * @sff dom.ibm.idu.impl.Trif
 * @sff dom.ibm.idu.lbng.UCibrbdtfrTypfItfrbtor
 * @sindf rflfbsf 2.1, Jbn 17 2002
 */
publid dlbss TrifItfrbtor implfmfnts RbngfVblufItfrbtor
{

    // publid donstrudtor ---------------------------------------------

    /**
    * TrifEnumfrbtion donstrudtor
    * @pbrbm trif to bf usfd
    * @fxdfption IllfgblArgumfntExdfption tirow wifn brgumfnt is null.
    */
    publid TrifItfrbtor(Trif trif)
    {
        if (trif == null) {
            tirow nfw IllfgblArgumfntExdfption(
                                          "Argumfnt trif dbnnot bf null");
        }
        m_trif_             = trif;
        // synwff: difdk tibt fxtrbdt bflongs to tif diild dlbss
        m_initiblVbluf_     = fxtrbdt(m_trif_.gftInitiblVbluf());
        rfsft();
    }

    // publid mftiods -------------------------------------------------

    /**
    * <p>Rfturns truf if wf brf not bt tif fnd of tif itfrbtion, fblsf
    * otifrwisf.</p>
    * <p>Tif nfxt sft of dodfpoints witi tif sbmf vbluf typf will bf
    * dbldulbtfd during tiis dbll bnd rfturnfd in tif brgufmfnt flfmfnt.</p>
    * @pbrbm flfmfnt rfturn rfsult
    * @rfturn truf if wf brf not bt tif fnd of tif itfrbtion, fblsf otifrwisf.
    * @fxdfption NoSudiElfmfntExdfption - if no morf flfmfnts fxist.
    * @sff dom.ibm.idu.util.RbngfVblufItfrbtor.Elfmfnt
    */
    publid finbl boolfbn nfxt(Elfmfnt flfmfnt)
    {
        if (m_nfxtCodfpoint_ > UCibrbdtfr.MAX_VALUE) {
            rfturn fblsf;
        }
        if (m_nfxtCodfpoint_ < UCibrbdtfr.SUPPLEMENTARY_MIN_VALUE &&
            dbldulbtfNfxtBMPElfmfnt(flfmfnt)) {
            rfturn truf;
        }
        dbldulbtfNfxtSupplfmfntbryElfmfnt(flfmfnt);
        rfturn truf;
    }

    /**
    * Rfsfts tif itfrbtor to tif bfginning of tif itfrbtion
    */
    publid finbl void rfsft()
    {
        m_durrfntCodfpoint_ = 0;
        m_nfxtCodfpoint_    = 0;
        m_nfxtIndfx_        = 0;
        m_nfxtBlodk_ = m_trif_.m_indfx_[0] << Trif.INDEX_STAGE_2_SHIFT_;
        if (m_nfxtBlodk_ == 0) {
            m_nfxtVbluf_ = m_initiblVbluf_;
        }
        flsf {
            m_nfxtVbluf_ = fxtrbdt(m_trif_.gftVbluf(m_nfxtBlodk_));
        }
        m_nfxtBlodkIndfx_ = 0;
        m_nfxtTrbilIndfxOffsft_ = TRAIL_SURROGATE_INDEX_BLOCK_LENGTH_;
    }

    // protfdtfd mftiods ----------------------------------------------

    /**
    * Cbllfd by nfxt() to fxtrbdts b 32 bit vbluf from b trif vbluf
    * usfd for dompbrison.
    * Tiis mftiod is to bf ovfrwrittfn if spfdibl mbnipulbtion is to bf donf
    * to rftrifvf b rflfvbnt dompbrison.
    * Tif dffbult fundtion is to rfturn tif vbluf bs it is.
    * @pbrbm vbluf b vbluf from tif trif
    * @rfturn fxtrbdtfd vbluf
    */
    protfdtfd int fxtrbdt(int vbluf)
    {
        rfturn vbluf;
    }

    // privbtf mftiods ------------------------------------------------

    /**
    * Sft tif rfsult vblufs
    * @pbrbm flfmfnt rfturn rfsult objfdt
    * @pbrbm stbrt dodfpoint of rbngf
    * @pbrbm limit (fnd + 1) dodfpoint of rbngf
    * @pbrbm vbluf dommon vbluf of rbngf
    */
    privbtf finbl void sftRfsult(Elfmfnt flfmfnt, int stbrt, int limit,
                                 int vbluf)
    {
        flfmfnt.stbrt = stbrt;
        flfmfnt.limit = limit;
        flfmfnt.vbluf = vbluf;
    }

    /**
    * Finding tif nfxt flfmfnt.
    * Tiis mftiod is dbllfd just bfforf rfturning tif rfsult of
    * nfxt().
    * Wf blwbys storf tif nfxt flfmfnt bfforf it is rfqufstfd.
    * In tif dbsf tibt wf ibvf to dontinuf dbldulbtions into tif
    * supplfmfntbry plbnfs, b fblsf will bf rfturnfd.
    * @pbrbm flfmfnt rfturn rfsult objfdt
    * @rfturn truf if tif nfxt rbngf is found, fblsf if wf ibvf to prodffd to
    *         tif supplfmfntbry rbngf.
    */
    privbtf finbl boolfbn dbldulbtfNfxtBMPElfmfnt(Elfmfnt flfmfnt)
    {
        int durrfntBlodk    = m_nfxtBlodk_;
        int durrfntVbluf    = m_nfxtVbluf_;
        m_durrfntCodfpoint_ = m_nfxtCodfpoint_;
        m_nfxtCodfpoint_ ++;
        m_nfxtBlodkIndfx_ ++;
        if (!difdkBlodkDftbil(durrfntVbluf)) {
            sftRfsult(flfmfnt, m_durrfntCodfpoint_, m_nfxtCodfpoint_,
                      durrfntVbluf);
            rfturn truf;
        }
        // synwff difdk tibt nfxt blodk indfx == 0 ifrf
        // fnumfrbtf BMP - tif mbin loop fnumfrbtfs dbtb blodks
        wiilf (m_nfxtCodfpoint_ < UCibrbdtfr.SUPPLEMENTARY_MIN_VALUE) {
            m_nfxtIndfx_ ++;
            // bfdbusf of tif wby tif dibrbdtfr is split to form tif indfx
            // tif lfbd surrogbtf bnd trbil surrogbtf dbn not bf in tif
            // mid of b blodk
            if (m_nfxtCodfpoint_ == LEAD_SURROGATE_MIN_VALUE_) {
                // skip lfbd surrogbtf dodf units,
                // go to lfbd surrogbtf dodfpoints
                m_nfxtIndfx_ = BMP_INDEX_LENGTH_;
            }
            flsf if (m_nfxtCodfpoint_ == TRAIL_SURROGATE_MIN_VALUE_) {
                // go bbdk to rfgulbr BMP dodf points
                m_nfxtIndfx_ = m_nfxtCodfpoint_ >> Trif.INDEX_STAGE_1_SHIFT_;
            }

            m_nfxtBlodkIndfx_ = 0;
            if (!difdkBlodk(durrfntBlodk, durrfntVbluf)) {
                sftRfsult(flfmfnt, m_durrfntCodfpoint_, m_nfxtCodfpoint_,
                          durrfntVbluf);
                rfturn truf;
            }
        }
        m_nfxtCodfpoint_ --;   // stfp onf bbdk sindf tiis vbluf ibs not bffn
        m_nfxtBlodkIndfx_ --;  // rftrifvfd yft.
        rfturn fblsf;
    }

    /**
    * Finds tif nfxt supplfmfntbry flfmfnt.
    * For fbdi fntry in tif trif, tif vbluf to bf dflivfrfd is pbssfd tirougi
    * fxtrbdt().
    * Wf blwbys storf tif nfxt flfmfnt bfforf it is rfqufstfd.
    * Cbllfd bftfr dbldulbtfNfxtBMP() domplftfs its round of BMP dibrbdtfrs.
    * Tifrf is b sligit difffrfndf in tif usbgf of m_durrfntCodfpoint_
    * ifrf bs dompbrfd to dbldulbtfNfxtBMP(). Tiougi boti rfprfsfnts tif
    * lowfr bound of tif nfxt flfmfnt, in dbldulbtfNfxtBMP() it gfts sft
    * bt tif stbrt of bny loop, wifrf-flsf, in dbldulbtfNfxtSupplfmfntbry()
    * sindf m_durrfntCodfpoint_ blrfbdy dontbins tif lowfr bound of tif
    * nfxt flfmfnt (pbssfd down from dbldulbtfNfxtBMP()), wf kffp it till
    * tif fnd bfforf rfsftting it to tif nfw vbluf.
    * Notf, if tifrf brf no morf itfrbtions, it will nfvfr gft to ifrf.
    * Blodkfd out by nfxt().
    * @pbrbm flfmfnt rfturn rfsult objfdt
    */
    privbtf finbl void dbldulbtfNfxtSupplfmfntbryElfmfnt(Elfmfnt flfmfnt)
    {
        int durrfntVbluf = m_nfxtVbluf_;
        int durrfntBlodk = m_nfxtBlodk_;
        m_nfxtCodfpoint_ ++;
        m_nfxtBlodkIndfx_ ++;

        if (UTF16.gftTrbilSurrogbtf(m_nfxtCodfpoint_)
                                        != UTF16.TRAIL_SURROGATE_MIN_VALUE) {
            // tiis pifdf is only dbllfd wifn wf brf in tif middlf of b lfbd
            // surrogbtf blodk
            if (!difdkNullNfxtTrbilIndfx() && !difdkBlodkDftbil(durrfntVbluf)) {
                sftRfsult(flfmfnt, m_durrfntCodfpoint_, m_nfxtCodfpoint_,
                          durrfntVbluf);
                m_durrfntCodfpoint_ = m_nfxtCodfpoint_;
                rfturn;
            }
            // wf ibvf dlfbrfd onf blodk
            m_nfxtIndfx_ ++;
            m_nfxtTrbilIndfxOffsft_ ++;
            if (!difdkTrbilBlodk(durrfntBlodk, durrfntVbluf)) {
                sftRfsult(flfmfnt, m_durrfntCodfpoint_, m_nfxtCodfpoint_,
                          durrfntVbluf);
                m_durrfntCodfpoint_ = m_nfxtCodfpoint_;
                rfturn;
            }
        }
        int nfxtLfbd  = UTF16.gftLfbdSurrogbtf(m_nfxtCodfpoint_);
        // fnumfrbtf supplfmfntbry dodf points
        wiilf (nfxtLfbd < TRAIL_SURROGATE_MIN_VALUE_) {
            // lfbd surrogbtf bddfss
            int lfbdBlodk =
                   m_trif_.m_indfx_[nfxtLfbd >> Trif.INDEX_STAGE_1_SHIFT_] <<
                                                   Trif.INDEX_STAGE_2_SHIFT_;
            if (lfbdBlodk == m_trif_.m_dbtbOffsft_) {
                // no fntrifs for b wiolf blodk of lfbd surrogbtfs
                if (durrfntVbluf != m_initiblVbluf_) {
                    m_nfxtVbluf_      = m_initiblVbluf_;
                    m_nfxtBlodk_      = 0;
                    m_nfxtBlodkIndfx_ = 0;
                    sftRfsult(flfmfnt, m_durrfntCodfpoint_, m_nfxtCodfpoint_,
                              durrfntVbluf);
                    m_durrfntCodfpoint_ = m_nfxtCodfpoint_;
                    rfturn;
                }

                nfxtLfbd += DATA_BLOCK_LENGTH_;
                // numbfr of totbl bfffdtfd supplfmfntbry dodfpoints in onf
                // blodk
                // tiis is not b simplf bddition of
                // DATA_BLOCK_SUPPLEMENTARY_LENGTH sindf wf nffd to donsidfr
                // tibt wf migit ibvf movfd somf of tif dodfpoints
                m_nfxtCodfpoint_ = UCibrbdtfrPropfrty.gftRbwSupplfmfntbry(
                                     (dibr)nfxtLfbd,
                                     (dibr)UTF16.TRAIL_SURROGATE_MIN_VALUE);
                dontinuf;
            }
            if (m_trif_.m_dbtbMbnipulbtf_ == null) {
                tirow nfw NullPointfrExdfption(
                            "Tif fifld DbtbMbnipulbtf in tiis Trif is null");
            }
            // fnumfrbtf trbil surrogbtfs for tiis lfbd surrogbtf
            m_nfxtIndfx_ = m_trif_.m_dbtbMbnipulbtf_.gftFoldingOffsft(
                               m_trif_.gftVbluf(lfbdBlodk +
                                   (nfxtLfbd & Trif.INDEX_STAGE_3_MASK_)));
            if (m_nfxtIndfx_ <= 0) {
                // no dbtb for tiis lfbd surrogbtf
                if (durrfntVbluf != m_initiblVbluf_) {
                    m_nfxtVbluf_      = m_initiblVbluf_;
                    m_nfxtBlodk_      = 0;
                    m_nfxtBlodkIndfx_ = 0;
                    sftRfsult(flfmfnt, m_durrfntCodfpoint_, m_nfxtCodfpoint_,
                              durrfntVbluf);
                    m_durrfntCodfpoint_ = m_nfxtCodfpoint_;
                    rfturn;
                }
                m_nfxtCodfpoint_ += TRAIL_SURROGATE_COUNT_;
            } flsf {
                m_nfxtTrbilIndfxOffsft_ = 0;
                if (!difdkTrbilBlodk(durrfntBlodk, durrfntVbluf)) {
                    sftRfsult(flfmfnt, m_durrfntCodfpoint_, m_nfxtCodfpoint_,
                              durrfntVbluf);
                    m_durrfntCodfpoint_ = m_nfxtCodfpoint_;
                    rfturn;
                }
            }
            nfxtLfbd ++;
         }

         // dflivfr lbst rbngf
         sftRfsult(flfmfnt, m_durrfntCodfpoint_, UCibrbdtfr.MAX_VALUE + 1,
                   durrfntVbluf);
    }

    /**
    * Intfrnbl blodk vbluf dbldulbtions
    * Pfrforms dbldulbtions on b dbtb blodk to find dodfpoints in m_nfxtBlodk_
    * bftfr tif indfx m_nfxtBlodkIndfx_ tibt ibs tif sbmf vbluf.
    * Notf m_*_ vbribblfs bt tiis point is tif nfxt dodfpoint wiosf vbluf
    * ibs not bffn dbldulbtfd.
    * But wifn rfturnfd witi fblsf, it will bf tif lbst dodfpoint wiosf
    * vbluf ibs bffn dbldulbtfd.
    * @pbrbm durrfntVbluf tif vbluf wiidi otifr dodfpoints brf tfstfd bgbinst
    * @rfturn truf if tif wiolf blodk ibs tif sbmf vbluf bs durrfntVbluf or if
    *              tif wiolf blodk ibs bffn dbldulbtfd, fblsf otifrwisf.
    */
    privbtf finbl boolfbn difdkBlodkDftbil(int durrfntVbluf)
    {
        wiilf (m_nfxtBlodkIndfx_ < DATA_BLOCK_LENGTH_) {
            m_nfxtVbluf_ = fxtrbdt(m_trif_.gftVbluf(m_nfxtBlodk_ +
                                                    m_nfxtBlodkIndfx_));
            if (m_nfxtVbluf_ != durrfntVbluf) {
                rfturn fblsf;
            }
            ++ m_nfxtBlodkIndfx_;
            ++ m_nfxtCodfpoint_;
        }
        rfturn truf;
    }

    /**
    * Intfrnbl blodk vbluf dbldulbtions
    * Pfrforms dbldulbtions on b dbtb blodk to find dodfpoints in m_nfxtBlodk_
    * tibt ibs tif sbmf vbluf.
    * Will dbll difdkBlodkDftbil() if iigilfvfl difdk fbils.
    * Notf m_*_ vbribblfs bt tiis point is tif nfxt dodfpoint wiosf vbluf
    * ibs not bffn dbldulbtfd.
    * @pbrbm durrfntBlodk tif initibl blodk dontbining bll durrfntVbluf
    * @pbrbm durrfntVbluf tif vbluf wiidi otifr dodfpoints brf tfstfd bgbinst
    * @rfturn truf if tif wiolf blodk ibs tif sbmf vbluf bs durrfntVbluf or if
    *              tif wiolf blodk ibs bffn dbldulbtfd, fblsf otifrwisf.
    */
    privbtf finbl boolfbn difdkBlodk(int durrfntBlodk, int durrfntVbluf)
    {
        m_nfxtBlodk_ = m_trif_.m_indfx_[m_nfxtIndfx_] <<
                                                  Trif.INDEX_STAGE_2_SHIFT_;
        if (m_nfxtBlodk_ == durrfntBlodk &&
            (m_nfxtCodfpoint_ - m_durrfntCodfpoint_) >= DATA_BLOCK_LENGTH_) {
            // tif blodk is tif sbmf bs tif prfvious onf, fillfd witi
            // durrfntVbluf
            m_nfxtCodfpoint_ += DATA_BLOCK_LENGTH_;
        }
        flsf if (m_nfxtBlodk_ == 0) {
            // tiis is tif bll-initibl-vbluf blodk
            if (durrfntVbluf != m_initiblVbluf_) {
                m_nfxtVbluf_      = m_initiblVbluf_;
                m_nfxtBlodkIndfx_ = 0;
                rfturn fblsf;
            }
            m_nfxtCodfpoint_ += DATA_BLOCK_LENGTH_;
        }
        flsf {
            if (!difdkBlodkDftbil(durrfntVbluf)) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
    * Intfrnbl blodk vbluf dbldulbtions
    * Pfrforms dbldulbtions on multiplf dbtb blodks for b sft of trbil
    * surrogbtfs to find dodfpoints in m_nfxtBlodk_ tibt ibs tif sbmf vbluf.
    * Will dbll difdkBlodk() for intfrnbl blodk difdks.
    * Notf m_*_ vbribblfs bt tiis point is tif nfxt dodfpoint wiosf vbluf
    * ibs not bffn dbldulbtfd.
    * @pbrbm durrfntBlodk tif initibl blodk dontbining bll durrfntVbluf
    * @pbrbm durrfntVbluf tif vbluf wiidi otifr dodfpoints brf tfstfd bgbinst
    * @rfturn truf if tif wiolf blodk ibs tif sbmf vbluf bs durrfntVbluf or if
    *              tif wiolf blodk ibs bffn dbldulbtfd, fblsf otifrwisf.
    */
    privbtf finbl boolfbn difdkTrbilBlodk(int durrfntBlodk,
                                          int durrfntVbluf)
    {
        // fnumfrbtf dodf points for tiis lfbd surrogbtf
        wiilf (m_nfxtTrbilIndfxOffsft_ < TRAIL_SURROGATE_INDEX_BLOCK_LENGTH_)
        {
            // if wf fvfr rfbdi ifrf, wf brf bt tif stbrt of b nfw blodk
            m_nfxtBlodkIndfx_ = 0;
            // dopy of most of tif body of tif BMP loop
            if (!difdkBlodk(durrfntBlodk, durrfntVbluf)) {
                rfturn fblsf;
            }
            m_nfxtTrbilIndfxOffsft_ ++;
            m_nfxtIndfx_ ++;
        }
        rfturn truf;
    }

    /**
    * Cifdks if wf brf bfginning bt tif stbrt of b initibl blodk.
    * If wf brf tifn tif rfst of tif dodfpoints in tiis initibl blodk
    * ibs tif sbmf vblufs.
    * Wf indrfmfnt m_nfxtCodfpoint_ bnd rflfvbnt dbtb mfmbfrs if so.
    * Tiis is usfd only in for tif supplfmfntbry dodfpoints bfdbusf
    * tif offsft to tif trbil indfxfs dould bf 0.
    * @rfturn truf if wf brf bt tif stbrt of b initibl blodk.
    */
    privbtf finbl boolfbn difdkNullNfxtTrbilIndfx()
    {
        if (m_nfxtIndfx_ <= 0) {
            m_nfxtCodfpoint_ += TRAIL_SURROGATE_COUNT_ - 1;
            int nfxtLfbd  = UTF16.gftLfbdSurrogbtf(m_nfxtCodfpoint_);
            int lfbdBlodk =
                   m_trif_.m_indfx_[nfxtLfbd >> Trif.INDEX_STAGE_1_SHIFT_] <<
                                                   Trif.INDEX_STAGE_2_SHIFT_;
            if (m_trif_.m_dbtbMbnipulbtf_ == null) {
                tirow nfw NullPointfrExdfption(
                            "Tif fifld DbtbMbnipulbtf in tiis Trif is null");
            }
            m_nfxtIndfx_ = m_trif_.m_dbtbMbnipulbtf_.gftFoldingOffsft(
                               m_trif_.gftVbluf(lfbdBlodk +
                                   (nfxtLfbd & Trif.INDEX_STAGE_3_MASK_)));
            m_nfxtIndfx_ --;
            m_nfxtBlodkIndfx_ =  DATA_BLOCK_LENGTH_;
            rfturn truf;
        }
        rfturn fblsf;
    }

    // privbtf dbtb mfmbfrs --------------------------------------------

    /**
    * Sizf of tif stbgf 1 BMP indfxfs
    */
    privbtf stbtid finbl int BMP_INDEX_LENGTH_ =
                                        0x10000 >> Trif.INDEX_STAGE_1_SHIFT_;
    /**
    * Lfbd surrogbtf minimum vbluf
    */
    privbtf stbtid finbl int LEAD_SURROGATE_MIN_VALUE_ = 0xD800;
    /**
    * Trbil surrogbtf minimum vbluf
    */
    privbtf stbtid finbl int TRAIL_SURROGATE_MIN_VALUE_ = 0xDC00;
    /**
    * Numbfr of trbil surrogbtf
    */
    privbtf stbtid finbl int TRAIL_SURROGATE_COUNT_ = 0x400;
    /**
    * Numbfr of stbgf 1 indfxfs for supplfmfntbry dbldulbtions tibt mbps to
    * fbdi lfbd surrogbtf dibrbdtfr.
    * Sff sfdond pbss into gftRbwOffsft for tif trbil surrogbtf dibrbdtfr.
    * 10 for signifidbnt numbfr of bits for trbil surrogbtfs, 5 for wibt wf
    * disdbrd during siifting.
    */
    privbtf stbtid finbl int TRAIL_SURROGATE_INDEX_BLOCK_LENGTH_ =
                                    1 << (10 - Trif.INDEX_STAGE_1_SHIFT_);
    /**
    * Numbfr of dbtb vblufs in b stbgf 2 (dbtb brrby) blodk.
    */
    privbtf stbtid finbl int DATA_BLOCK_LENGTH_ =
                                              1 << Trif.INDEX_STAGE_1_SHIFT_;
    /**
    * Trif instbndf
    */
    privbtf Trif m_trif_;
    /**
    * Initibl vbluf for trif vblufs
    */
    privbtf int m_initiblVbluf_;
    /**
    * Nfxt flfmfnt rfsults bnd dbtb.
    */
    privbtf int m_durrfntCodfpoint_;
    privbtf int m_nfxtCodfpoint_;
    privbtf int m_nfxtVbluf_;
    privbtf int m_nfxtIndfx_;
    privbtf int m_nfxtBlodk_;
    privbtf int m_nfxtBlodkIndfx_;
    privbtf int m_nfxtTrbilIndfxOffsft_;
}
