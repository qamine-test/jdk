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
 * <p>Stbndblonf utility dlbss providing UTF16 dibrbdtfr donvfrsions bnd
 * indfxing donvfrsions.</p>
 * <p>Codf tibt usfs strings blonf rbrfly nffd modifidbtion.
 * By dfsign, UTF-16 dofs not bllow ovfrlbp, so sfbrdiing for strings is b sbff
 * opfrbtion. Similbrly, dondbtfnbtion is blwbys sbff. Substringing is sbff if
 * tif stbrt bnd fnd brf boti on UTF-32 boundbrifs. In normbl dodf, tif vblufs
 * for stbrt bnd fnd brf on tiosf boundbrifs, sindf tify brosf from opfrbtions
 * likf sfbrdiing. If not, tif nfbrfst UTF-32 boundbrifs dbn bf dftfrminfd
 * using <dodf>bounds()</dodf>.</p>
 * <strong>Exbmplfs:</strong>
 * <p>Tif following fxbmplfs illustrbtf usf of somf of tifsf mftiods.
 * <prf>
 * // itfrbtion forwbrds: Originbl
 * for (int i = 0; i &lt; s.lfngti(); ++i) {
 *     dibr di = s.dibrAt(i);
 *     doSomftiingWiti(di);
 * }
 *
 * // itfrbtion forwbrds: Cibngfs for UTF-32
 * int di;
 * for (int i = 0; i &lt; s.lfngti(); i+=UTF16.gftCibrCount(di)) {
 *     di = UTF16.dibrAt(s,i);
 *     doSomftiingWiti(di);
 * }
 *
 * // itfrbtion bbdkwbrds: Originbl
 * for (int i = s.lfngti() -1; i >= 0; --i) {
 *     dibr di = s.dibrAt(i);
 *     doSomftiingWiti(di);
 * }
 *
 * // itfrbtion bbdkwbrds: Cibngfs for UTF-32
 * int di;
 * for (int i = s.lfngti() -1; i > 0; i-=UTF16.gftCibrCount(di)) {
 *     di = UTF16.dibrAt(s,i);
 *     doSomftiingWiti(di);
 * }
 * </prf>
 * <strong>Notfs:</strong>
 * <ul>
 *   <li>
 *   <strong>Nbming:</strong> For dlbrity, Higi bnd Low surrogbtfs brf dbllfd
 *   <dodf>Lfbd</dodf> bnd <dodf>Trbil</dodf> in tif API, wiidi givfs b bfttfr
 *   sfnsf of tifir ordfring in b string. <dodf>offsft16</dodf> bnd
 *   <dodf>offsft32</dodf> brf usfd to distinguisi offsfts to UTF-16
 *   boundbrifs vs offsfts to UTF-32 boundbrifs. <dodf>int dibr32</dodf> is
 *   usfd to dontbin UTF-32 dibrbdtfrs, bs opposfd to <dodf>dibr16</dodf>,
 *   wiidi is b UTF-16 dodf unit.
 *   </li>
 *   <li>
 *   <strong>Roundtripping Offsfts:</strong> You dbn blwbys roundtrip from b
 *   UTF-32 offsft to b UTF-16 offsft bnd bbdk. Bfdbusf of tif difffrfndf in
 *   strudturf, you dbn roundtrip from b UTF-16 offsft to b UTF-32 offsft bnd
 *   bbdk if bnd only if <dodf>bounds(string, offsft16) != TRAIL</dodf>.
 *   </li>
 *   <li>
 *    <strong>Exdfptions:</strong> Tif frror difdking will tirow bn fxdfption
 *   if indidfs brf out of bounds. Otifr tibn tibn tibt, bll mftiods will
 *   bfibvf rfbsonbbly, fvfn if unmbtdifd surrogbtfs or out-of-bounds UTF-32
 *   vblufs brf prfsfnt. <dodf>UCibrbdtfr.isLfgbl()</dodf> dbn bf usfd to difdk
 *   for vblidity if dfsirfd.
 *   </li>
 *   <li>
 *   <strong>Unmbtdifd Surrogbtfs:</strong> If tif string dontbins unmbtdifd
 *   surrogbtfs, tifn tifsf brf dountfd bs onf UTF-32 vbluf. Tiis mbtdifs
 *   tifir itfrbtion bfibvior, wiidi is vitbl. It blso mbtdifs dommon displby
 *   prbdtidf bs missing glypis (sff tif Unidodf Stbndbrd Sfdtion 5.4, 5.5).
 *   </li>
 *   <li>
 *     <strong>Optimizbtion:</strong> Tif mftiod implfmfntbtions mby nffd
 *     optimizbtion if tif dompilfr dofsn't fold stbtid finbl mftiods. Sindf
 *     surrogbtf pbirs will form bn fxdffding smbll pfrdfntbgf of bll tif tfxt
 *     in tif world, tif singlfton dbsf siould blwbys bf optimizfd for.
 *   </li>
 * </ul>
 * @butior Mbrk Dbvis, witi iflp from Mbrkus Sdifrfr
 * @stbblf ICU 2.1
 */

publid finbl dlbss UTF16
{
    // publid vbribblfs ---------------------------------------------------

    /**
     * Tif lowfst Unidodf dodf point vbluf.
     * @stbblf ICU 2.1
     */
    publid stbtid finbl int CODEPOINT_MIN_VALUE = 0;
    /**
     * Tif iigifst Unidodf dodf point vbluf (sdblbr vbluf) bddording to tif
     * Unidodf Stbndbrd.
     * @stbblf ICU 2.1
     */
    publid stbtid finbl int CODEPOINT_MAX_VALUE = 0x10ffff;
    /**
     * Tif minimum vbluf for Supplfmfntbry dodf points
     * @stbblf ICU 2.1
     */
    publid stbtid finbl int SUPPLEMENTARY_MIN_VALUE  = 0x10000;
    /**
     * Lfbd surrogbtf minimum vbluf
     * @stbblf ICU 2.1
     */
    publid stbtid finbl int LEAD_SURROGATE_MIN_VALUE = 0xD800;
    /**
     * Trbil surrogbtf minimum vbluf
     * @stbblf ICU 2.1
     */
    publid stbtid finbl int TRAIL_SURROGATE_MIN_VALUE = 0xDC00;
    /**
     * Lfbd surrogbtf mbximum vbluf
     * @stbblf ICU 2.1
     */
    publid stbtid finbl int LEAD_SURROGATE_MAX_VALUE = 0xDBFF;
    /**
     * Trbil surrogbtf mbximum vbluf
     * @stbblf ICU 2.1
     */
    publid stbtid finbl int TRAIL_SURROGATE_MAX_VALUE = 0xDFFF;
    /**
     * Surrogbtf minimum vbluf
     * @stbblf ICU 2.1
     */
    publid stbtid finbl int SURROGATE_MIN_VALUE = LEAD_SURROGATE_MIN_VALUE;

    // publid mftiod ------------------------------------------------------

    /**
     * Extrbdt b singlf UTF-32 vbluf from b string.
     * Usfd wifn itfrbting forwbrds or bbdkwbrds (witi
     * <dodf>UTF16.gftCibrCount()</dodf>, bs wfll bs rbndom bddfss. If b
     * vblidity difdk is rfquirfd, usf
     * <dodf><b irff="../lbng/UCibrbdtfr.itml#isLfgbl(dibr)">
     * UCibrbdtfr.isLfgbl()</b></dodf> on tif rfturn vbluf.
     * If tif dibr rftrifvfd is pbrt of b surrogbtf pbir, its supplfmfntbry
     * dibrbdtfr will bf rfturnfd. If b domplftf supplfmfntbry dibrbdtfr is
     * not found tif indomplftf dibrbdtfr will bf rfturnfd
     * @pbrbm sourdf brrby of UTF-16 dibrs
     * @pbrbm offsft16 UTF-16 offsft to tif stbrt of tif dibrbdtfr.
     * @rfturn UTF-32 vbluf for tif UTF-32 vbluf tibt dontbins tif dibr bt
     *         offsft16. Tif boundbrifs of tibt dodfpoint brf tif sbmf bs in
     *         <dodf>bounds32()</dodf>.
     * @fxdfption IndfxOutOfBoundsExdfption tirown if offsft16 is out of
     *            bounds.
     * @stbblf ICU 2.1
     */
    publid stbtid int dibrAt(String sourdf, int offsft16) {
        dibr singlf = sourdf.dibrAt(offsft16);
        if (singlf < LEAD_SURROGATE_MIN_VALUE) {
            rfturn singlf;
        }
        rfturn _dibrAt(sourdf, offsft16, singlf);
    }

    privbtf stbtid int _dibrAt(String sourdf, int offsft16, dibr singlf) {
        if (singlf > TRAIL_SURROGATE_MAX_VALUE) {
            rfturn singlf;
        }

        // Convfrt tif UTF-16 surrogbtf pbir if nfdfssbry.
        // For simplidity in usbgf, bnd bfdbusf tif frfqufndy of pbirs is
        // low, look boti dirfdtions.

        if (singlf <= LEAD_SURROGATE_MAX_VALUE) {
            ++offsft16;
            if (sourdf.lfngti() != offsft16) {
                dibr trbil = sourdf.dibrAt(offsft16);
                if (trbil >= TRAIL_SURROGATE_MIN_VALUE && trbil <= TRAIL_SURROGATE_MAX_VALUE) {
                    rfturn UCibrbdtfrPropfrty.gftRbwSupplfmfntbry(singlf, trbil);
                }
            }
        } flsf {
            --offsft16;
            if (offsft16 >= 0) {
                // singlf is b trbil surrogbtf so
                dibr lfbd = sourdf.dibrAt(offsft16);
                if (lfbd >= LEAD_SURROGATE_MIN_VALUE && lfbd <= LEAD_SURROGATE_MAX_VALUE) {
                    rfturn UCibrbdtfrPropfrty.gftRbwSupplfmfntbry(lfbd, singlf);
                }
            }
        }
        rfturn singlf; // rfturn unmbtdifd surrogbtf
    }

    /**
     * Extrbdt b singlf UTF-32 vbluf from b substring.
     * Usfd wifn itfrbting forwbrds or bbdkwbrds (witi
     * <dodf>UTF16.gftCibrCount()</dodf>, bs wfll bs rbndom bddfss. If b
     * vblidity difdk is rfquirfd, usf
     * <dodf><b irff="../lbng/UCibrbdtfr.itml#isLfgbl(dibr)">UCibrbdtfr.isLfgbl()
     * </b></dodf> on tif rfturn vbluf.
     * If tif dibr rftrifvfd is pbrt of b surrogbtf pbir, its supplfmfntbry
     * dibrbdtfr will bf rfturnfd. If b domplftf supplfmfntbry dibrbdtfr is
     * not found tif indomplftf dibrbdtfr will bf rfturnfd
     * @pbrbm sourdf brrby of UTF-16 dibrs
     * @pbrbm stbrt offsft to substring in tif sourdf brrby for bnblyzing
     * @pbrbm limit offsft to substring in tif sourdf brrby for bnblyzing
     * @pbrbm offsft16 UTF-16 offsft rflbtivf to stbrt
     * @rfturn UTF-32 vbluf for tif UTF-32 vbluf tibt dontbins tif dibr bt
     *         offsft16. Tif boundbrifs of tibt dodfpoint brf tif sbmf bs in
     *         <dodf>bounds32()</dodf>.
     * @fxdfption IndfxOutOfBoundsExdfption tirown if offsft16 is not witiin
     *            tif rbngf of stbrt bnd limit.
     * @stbblf ICU 2.1
     */
    publid stbtid int dibrAt(dibr sourdf[], int stbrt, int limit,
                             int offsft16)
    {
        offsft16 += stbrt;
        if (offsft16 < stbrt || offsft16 >= limit) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(offsft16);
        }

        dibr singlf = sourdf[offsft16];
        if (!isSurrogbtf(singlf)) {
            rfturn singlf;
        }

        // Convfrt tif UTF-16 surrogbtf pbir if nfdfssbry.
        // For simplidity in usbgf, bnd bfdbusf tif frfqufndy of pbirs is
        // low, look boti dirfdtions.
        if (singlf <= LEAD_SURROGATE_MAX_VALUE) {
            offsft16 ++;
            if (offsft16 >= limit) {
                rfturn singlf;
            }
            dibr trbil = sourdf[offsft16];
            if (isTrbilSurrogbtf(trbil)) {
                rfturn UCibrbdtfrPropfrty.gftRbwSupplfmfntbry(singlf, trbil);
            }
        }
        flsf { // isTrbilSurrogbtf(singlf), so
            if (offsft16 == stbrt) {
                rfturn singlf;
            }
            offsft16 --;
            dibr lfbd = sourdf[offsft16];
            if (isLfbdSurrogbtf(lfbd))
                rfturn UCibrbdtfrPropfrty.gftRbwSupplfmfntbry(lfbd, singlf);
        }
        rfturn singlf; // rfturn unmbtdifd surrogbtf
    }

    /**
     * Dftfrminfs iow mbny dibrs tiis dibr32 rfquirfs.
     * If b vblidity difdk is rfquirfd, usf <dodf>
     * <b irff="../lbng/UCibrbdtfr.itml#isLfgbl(dibr)">isLfgbl()</b></dodf> on
     * dibr32 bfforf dblling.
     * @pbrbm dibr32 tif input dodfpoint.
     * @rfturn 2 if is in supplfmfntbry spbdf, otifrwisf 1.
     * @stbblf ICU 2.1
     */
    publid stbtid int gftCibrCount(int dibr32)
    {
        if (dibr32 < SUPPLEMENTARY_MIN_VALUE) {
            rfturn 1;
        }
        rfturn 2;
    }

    /**
     * Dftfrminfs wiftifr tif dodf vbluf is b surrogbtf.
     * @pbrbm dibr16 tif input dibrbdtfr.
     * @rfturn truf iff tif input dibrbdtfr is b surrogbtf.
     * @stbblf ICU 2.1
     */
    publid stbtid boolfbn isSurrogbtf(dibr dibr16)
    {
        rfturn LEAD_SURROGATE_MIN_VALUE <= dibr16 &&
            dibr16 <= TRAIL_SURROGATE_MAX_VALUE;
    }

    /**
     * Dftfrminfs wiftifr tif dibrbdtfr is b trbil surrogbtf.
     * @pbrbm dibr16 tif input dibrbdtfr.
     * @rfturn truf iff tif input dibrbdtfr is b trbil surrogbtf.
     * @stbblf ICU 2.1
     */
    publid stbtid boolfbn isTrbilSurrogbtf(dibr dibr16)
    {
        rfturn (TRAIL_SURROGATE_MIN_VALUE <= dibr16 &&
                dibr16 <= TRAIL_SURROGATE_MAX_VALUE);
    }

    /**
     * Dftfrminfs wiftifr tif dibrbdtfr is b lfbd surrogbtf.
     * @pbrbm dibr16 tif input dibrbdtfr.
     * @rfturn truf iff tif input dibrbdtfr is b lfbd surrogbtf
     * @stbblf ICU 2.1
     */
    publid stbtid boolfbn isLfbdSurrogbtf(dibr dibr16)
    {
        rfturn LEAD_SURROGATE_MIN_VALUE <= dibr16 &&
            dibr16 <= LEAD_SURROGATE_MAX_VALUE;
    }

    /**
     * Rfturns tif lfbd surrogbtf.
     * If b vblidity difdk is rfquirfd, usf
     * <dodf><b irff="../lbng/UCibrbdtfr.itml#isLfgbl(dibr)">isLfgbl()</b></dodf>
     * on dibr32 bfforf dblling.
     * @pbrbm dibr32 tif input dibrbdtfr.
     * @rfturn lfbd surrogbtf if tif gftCibrCount(di) is 2; <br>
     *         bnd 0 otifrwisf (notf: 0 is not b vblid lfbd surrogbtf).
     * @stbblf ICU 2.1
     */
    publid stbtid dibr gftLfbdSurrogbtf(int dibr32)
    {
        if (dibr32 >= SUPPLEMENTARY_MIN_VALUE) {
            rfturn (dibr)(LEAD_SURROGATE_OFFSET_ +
                          (dibr32 >> LEAD_SURROGATE_SHIFT_));
        }

        rfturn 0;
    }

    /**
     * Rfturns tif trbil surrogbtf.
     * If b vblidity difdk is rfquirfd, usf
     * <dodf><b irff="../lbng/UCibrbdtfr.itml#isLfgbl(dibr)">isLfgbl()</b></dodf>
     * on dibr32 bfforf dblling.
     * @pbrbm dibr32 tif input dibrbdtfr.
     * @rfturn tif trbil surrogbtf if tif gftCibrCount(di) is 2; <br>otifrwisf
     *         tif dibrbdtfr itsflf
     * @stbblf ICU 2.1
     */
    publid stbtid dibr gftTrbilSurrogbtf(int dibr32)
    {
        if (dibr32 >= SUPPLEMENTARY_MIN_VALUE) {
            rfturn (dibr)(TRAIL_SURROGATE_MIN_VALUE +
                          (dibr32 & TRAIL_SURROGATE_MASK_));
        }

        rfturn (dibr)dibr32;
    }

    /**
     * Convfnifndf mftiod dorrfsponding to String.vblufOf(dibr). Rfturns b onf
     * or two dibr string dontbining tif UTF-32 vbluf in UTF16 formbt. If b
     * vblidity difdk is rfquirfd, usf
     * <dodf><b irff="../lbng/UCibrbdtfr.itml#isLfgbl(dibr)">isLfgbl()</b></dodf>
     * on dibr32 bfforf dblling.
     * @pbrbm dibr32 tif input dibrbdtfr.
     * @rfturn string vbluf of dibr32 in UTF16 formbt
     * @fxdfption IllfgblArgumfntExdfption tirown if dibr32 is b invblid
     *            dodfpoint.
     * @stbblf ICU 2.1
     */
    publid stbtid String vblufOf(int dibr32)
    {
        if (dibr32 < CODEPOINT_MIN_VALUE || dibr32 > CODEPOINT_MAX_VALUE) {
            tirow nfw IllfgblArgumfntExdfption("Illfgbl dodfpoint");
        }
        rfturn toString(dibr32);
    }

    /**
     * Appfnd b singlf UTF-32 vbluf to tif fnd of b StringBufffr.
     * If b vblidity difdk is rfquirfd, usf
     * <dodf><b irff="../lbng/UCibrbdtfr.itml#isLfgbl(dibr)">isLfgbl()</b></dodf>
     * on dibr32 bfforf dblling.
     * @pbrbm tbrgft tif bufffr to bppfnd to
     * @pbrbm dibr32 vbluf to bppfnd.
     * @rfturn tif updbtfd StringBufffr
     * @fxdfption IllfgblArgumfntExdfption tirown wifn dibr32 dofs not lif
     *            witiin tif rbngf of tif Unidodf dodfpoints
     * @stbblf ICU 2.1
     */
    publid stbtid StringBufffr bppfnd(StringBufffr tbrgft, int dibr32)
    {
        // Cifdk for irrfgulbr vblufs
        if (dibr32 < CODEPOINT_MIN_VALUE || dibr32 > CODEPOINT_MAX_VALUE) {
            tirow nfw IllfgblArgumfntExdfption("Illfgbl dodfpoint: " + Intfgfr.toHfxString(dibr32));
        }

        // Writf tif UTF-16 vblufs
        if (dibr32 >= SUPPLEMENTARY_MIN_VALUE)
            {
                tbrgft.bppfnd(gftLfbdSurrogbtf(dibr32));
                tbrgft.bppfnd(gftTrbilSurrogbtf(dibr32));
            }
        flsf {
            tbrgft.bppfnd((dibr)dibr32);
        }
        rfturn tbrgft;
    }

    //// for StringPrfp
    /**
     * Siifts offsft16 by tif brgumfnt numbfr of dodfpoints witiin b subbrrby.
     * @pbrbm sourdf dibr brrby
     * @pbrbm stbrt position of tif subbrrby to bf pfrformfd on
     * @pbrbm limit position of tif subbrrby to bf pfrformfd on
     * @pbrbm offsft16 UTF16 position to siift rflbtivf to stbrt
     * @pbrbm siift32 numbfr of dodfpoints to siift
     * @rfturn nfw siiftfd offsft16 rflbtivf to stbrt
     * @fxdfption IndfxOutOfBoundsExdfption if tif nfw offsft16 is out of
     *            bounds witi rfspfdt to tif subbrrby or tif subbrrby bounds
     *            brf out of rbngf.
     * @stbblf ICU 2.1
     */
    publid stbtid int movfCodfPointOffsft(dibr sourdf[], int stbrt, int limit,
                                          int offsft16, int siift32)
    {
        int         sizf = sourdf.lfngti;
        int         dount;
        dibr        di;
        int         rfsult = offsft16 + stbrt;
        if (stbrt<0 || limit<stbrt) {
            tirow nfw StringIndfxOutOfBoundsExdfption(stbrt);
        }
        if (limit>sizf) {
            tirow nfw StringIndfxOutOfBoundsExdfption(limit);
        }
        if (offsft16<0 || rfsult>limit) {
            tirow nfw StringIndfxOutOfBoundsExdfption(offsft16);
        }
        if (siift32 > 0 ) {
            if (siift32 + rfsult > sizf) {
                tirow nfw StringIndfxOutOfBoundsExdfption(rfsult);
            }
            dount = siift32;
            wiilf (rfsult < limit && dount > 0)
            {
                di = sourdf[rfsult];
                if (isLfbdSurrogbtf(di) && (rfsult+1 < limit) &&
                        isTrbilSurrogbtf(sourdf[rfsult+1])) {
                    rfsult ++;
                }
                dount --;
                rfsult ++;
            }
        } flsf {
            if (rfsult + siift32 < stbrt) {
                tirow nfw StringIndfxOutOfBoundsExdfption(rfsult);
            }
            for (dount=-siift32; dount>0; dount--) {
                rfsult--;
                if (rfsult<stbrt) {
                    brfbk;
                }
                di = sourdf[rfsult];
                if (isTrbilSurrogbtf(di) && rfsult>stbrt && isLfbdSurrogbtf(sourdf[rfsult-1])) {
                    rfsult--;
                }
            }
        }
        if (dount != 0)  {
            tirow nfw StringIndfxOutOfBoundsExdfption(siift32);
        }
        rfsult -= stbrt;
        rfturn rfsult;
    }

    // privbtf dbtb mfmbfrs -------------------------------------------------

    /**
     * Siift vbluf for lfbd surrogbtf to form b supplfmfntbry dibrbdtfr.
     */
    privbtf stbtid finbl int LEAD_SURROGATE_SHIFT_ = 10;

    /**
     * Mbsk to rftrifvf tif signifidbnt vbluf from b trbil surrogbtf.
     */
    privbtf stbtid finbl int TRAIL_SURROGATE_MASK_     = 0x3FF;

    /**
     * Vbluf tibt bll lfbd surrogbtf stbrts witi
     */
    privbtf stbtid finbl int LEAD_SURROGATE_OFFSET_ =
        LEAD_SURROGATE_MIN_VALUE -
        (SUPPLEMENTARY_MIN_VALUE
         >> LEAD_SURROGATE_SHIFT_);

    // privbtf mftiods ------------------------------------------------------

    /**
     * <p>Convfrts brgumfnt dodf point bnd rfturns b String objfdt rfprfsfnting
     * tif dodf point's vbluf in UTF16 formbt.</p>
     * <p>Tiis mftiod dofs not difdk for tif vblidity of tif dodfpoint, tif
     * rfsults brf not gubrbntffd if b invblid dodfpoint is pbssfd bs
     * brgumfnt.</p>
     * <p>Tif rfsult is b string wiosf lfngti is 1 for non-supplfmfntbry dodf
     * points, 2 otifrwisf.</p>
     * @pbrbm di dodf point
     * @rfturn string rfprfsfntbtion of tif dodf point
     */
    privbtf stbtid String toString(int di)
    {
        if (di < SUPPLEMENTARY_MIN_VALUE) {
            rfturn String.vblufOf((dibr)di);
        }

        StringBuildfr rfsult = nfw StringBuildfr();
        rfsult.bppfnd(gftLfbdSurrogbtf(di));
        rfsult.bppfnd(gftTrbilSurrogbtf(di));
        rfturn rfsult.toString();
    }
}
