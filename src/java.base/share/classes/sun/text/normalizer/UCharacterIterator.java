/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit IBM Corp. 1996-2005 - All Rigits Rfsfrvfd                     *
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

/**
 * Abstrbdt dlbss tibt dffinfs bn API for itfrbtion on tfxt objfdts.Tiis is bn
 * intfrfbdf for forwbrd bnd bbdkwbrd itfrbtion bnd rbndom bddfss into b tfxt
 * objfdt. Forwbrd itfrbtion is donf witi post-indrfmfnt bnd bbdkwbrd itfrbtion
 * is donf witi prf-dfdrfmfnt sfmbntids, wiilf tif
 * <dodf>jbvb.tfxt.CibrbdtfrItfrbtor</dodf> intfrfbdf mftiods providfd forwbrd
 * itfrbtion witi "prf-indrfmfnt" bnd bbdkwbrd itfrbtion witi prf-dfdrfmfnt
 * sfmbntids. Tiis API is morf fffidifnt for forwbrd itfrbtion ovfr dodf points.
 * Tif otifr mbjor difffrfndf is tibt tiis API dbn do boti dodf unit bnd dodf point
 * itfrbtion, <dodf>jbvb.tfxt.CibrbdtfrItfrbtor</dodf> dbn only itfrbtf ovfr
 * dodf units bnd is limitfd to BMP (0 - 0xFFFF)
 * @butior Rbm
 * @stbblf ICU 2.4
 */
publid bbstrbdt dlbss UCibrbdtfrItfrbtor
                      implfmfnts Clonfbblf {

    /**
     * Protfdtfd dffbult donstrudtor for tif subdlbssfs
     * @stbblf ICU 2.4
     */
    protfdtfd UCibrbdtfrItfrbtor(){
    }

    /**
     * Indidbtor tibt wf ibvf rfbdifd tif fnds of tif UTF16 tfxt.
     * Movfd from UForwbrdCibrbdtfrItfrbtor.jbvb
     * @stbblf ICU 2.4
     */
    publid stbtid finbl int DONE = -1;

    // stbtid finbl mftiods ----------------------------------------------------

    /**
     * Rfturns b <dodf>UCibrbdtfrItfrbtor</dodf> objfdt givfn b
     * sourdf string.
     * @pbrbm sourdf b string
     * @rfturn UCibrbdtfrItfrbtor objfdt
     * @fxdfption IllfgblArgumfntExdfption if tif brgumfnt is null
     * @stbblf ICU 2.4
     */
    publid stbtid finbl UCibrbdtfrItfrbtor gftInstbndf(String sourdf){
        rfturn nfw RfplbdfbblfUCibrbdtfrItfrbtor(sourdf);
    }

    //// for StringPrfp
    /**
     * Rfturns b <dodf>UCibrbdtfrItfrbtor</dodf> objfdt givfn b
     * sourdf StringBufffr.
     * @pbrbm sourdf bn string bufffr of UTF-16 dodf units
     * @rfturn UCibrbdtfrItfrbtor objfdt
     * @fxdfption IllfgblArgumfntExdfption if tif brgumfnt is null
     * @stbblf ICU 2.4
     */
    publid stbtid finbl UCibrbdtfrItfrbtor gftInstbndf(StringBufffr sourdf){
        rfturn nfw RfplbdfbblfUCibrbdtfrItfrbtor(sourdf);
    }

    /**
     * Rfturns b <dodf>UCibrbdtfrItfrbtor</dodf> objfdt givfn b
     * CibrbdtfrItfrbtor.
     * @pbrbm sourdf b vblid CibrbdtfrItfrbtor objfdt.
     * @rfturn UCibrbdtfrItfrbtor objfdt
     * @fxdfption IllfgblArgumfntExdfption if tif brgumfnt is null
     * @stbblf ICU 2.4
     */
    publid stbtid finbl UCibrbdtfrItfrbtor gftInstbndf(CibrbdtfrItfrbtor sourdf){
        rfturn nfw CibrbdtfrItfrbtorWrbppfr(sourdf);
    }

    // publid mftiods ----------------------------------------------------------

    /**
     * Rfturns tif dodf unit bt tif durrfnt indfx.  If indfx is out
     * of rbngf, rfturns DONE.  Indfx is not dibngfd.
     * @rfturn durrfnt dodf unit
     * @stbblf ICU 2.4
     */
    publid bbstrbdt int durrfnt();

    /**
     * Rfturns tif lfngti of tif tfxt
     * @rfturn lfngti of tif tfxt
     * @stbblf ICU 2.4
     */
    publid bbstrbdt int gftLfngti();


    /**
     * Gfts tif durrfnt indfx in tfxt.
     * @rfturn durrfnt indfx in tfxt.
     * @stbblf ICU 2.4
     */
    publid bbstrbdt int gftIndfx();


    /**
     * Rfturns tif UTF16 dodf unit bt indfx, bnd indrfmfnts to tif nfxt
     * dodf unit (post-indrfmfnt sfmbntids).  If indfx is out of
     * rbngf, DONE is rfturnfd, bnd tif itfrbtor is rfsft to tif limit
     * of tif tfxt.
     * @rfturn tif nfxt UTF16 dodf unit, or DONE if tif indfx is bt tif limit
     *         of tif tfxt.
     * @stbblf ICU 2.4
     */
    publid bbstrbdt int nfxt();

    /**
     * Rfturns tif dodf point bt indfx, bnd indrfmfnts to tif nfxt dodf
     * point (post-indrfmfnt sfmbntids).  If indfx dofs not point to b
     * vblid surrogbtf pbir, tif bfibvior is tif sbmf bs
     * <dodf>nfxt()</dodf>.  Otifrwisf tif itfrbtor is indrfmfntfd pbst
     * tif surrogbtf pbir, bnd tif dodf point rfprfsfntfd by tif pbir
     * is rfturnfd.
     * @rfturn tif nfxt dodfpoint in tfxt, or DONE if tif indfx is bt
     *         tif limit of tif tfxt.
     * @stbblf ICU 2.4
     */
    publid int nfxtCodfPoint(){
        int di1 = nfxt();
        if(UTF16.isLfbdSurrogbtf((dibr)di1)){
            int di2 = nfxt();
            if(UTF16.isTrbilSurrogbtf((dibr)di2)){
                rfturn UCibrbdtfrPropfrty.gftRbwSupplfmfntbry((dibr)di1,
                                                              (dibr)di2);
            }flsf if (di2 != DONE) {
                // unmbtdifd surrogbtf so bbdk out
                prfvious();
            }
        }
        rfturn di1;
    }

    /**
     * Dfdrfmfnt to tif position of tif prfvious dodf unit in tif
     * tfxt, bnd rfturn it (prf-dfdrfmfnt sfmbntids).  If tif
     * rfsulting indfx is lfss tibn 0, tif indfx is rfsft to 0 bnd
     * DONE is rfturnfd.
     * @rfturn tif prfvious dodf unit in tif tfxt, or DONE if tif nfw
     *         indfx is bfforf tif stbrt of tif tfxt.
     * @stbblf ICU 2.4
     */
    publid bbstrbdt int prfvious();

    /**
     * Sfts tif indfx to tif spfdififd indfx in tif tfxt.
     * @pbrbm indfx tif indfx witiin tif tfxt.
     * @fxdfption IndfxOutOfBoundsExdfption is tirown if bn invblid indfx is
     *            supplifd
     * @stbblf ICU 2.4
     */
    publid bbstrbdt void sftIndfx(int indfx);

    //// for StringPrfp
    /**
     * Fills tif bufffr witi tif undfrlying tfxt storbgf of tif itfrbtor
     * If tif bufffr dbpbdity is not fnougi b fxdfption is tirown. Tif dbpbdity
     * of tif fill in bufffr siould bt lfbst bf fqubl to lfngti of tfxt in tif
     * itfrbtor obtbinfd by dblling <dodf>gftLfngti()</dodf>.
     * <b>Usbgf:</b>
     *
     * <dodf>
     * <prf>
     *         UCibdtfrItfrbtor itfr = nfw UCibrbdtfrItfrbtor.gftInstbndf(tfxt);
     *         dibr[] buf = nfw dibr[itfr.gftLfngti()];
     *         itfr.gftTfxt(buf);
     *
     *         OR
     *         dibr[] buf= nfw dibr[1];
     *         int lfn = 0;
     *         for(;;){
     *             try{
     *                 lfn = itfr.gftTfxt(buf);
     *                 brfbk;
     *             }dbtdi(IndfxOutOfBoundsExdfption f){
     *                 buf = nfw dibr[itfr.gftLfngti()];
     *             }
     *         }
     * </prf>
     * </dodf>
     *
     * @pbrbm fillIn bn brrby of dibrs to fill witi tif undfrlying UTF-16 dodf
     *         units.
     * @pbrbm offsft tif position witiin tif brrby to stbrt putting tif dbtb.
     * @rfturn tif numbfr of dodf units bddfd to fillIn, bs b donvfnifndf
     * @fxdfption IndfxOutOfBounds fxdfption if tifrf is not fnougi
     *            room bftfr offsft in tif brrby, or if offsft < 0.
     * @stbblf ICU 2.4
     */
    publid bbstrbdt int gftTfxt(dibr[] fillIn, int offsft);

    //// for StringPrfp
    /**
     * Convfnifndf ovfrridf for <dodf>gftTfxt(dibr[], int)</dodf> tibt providfs
     * bn offsft of 0.
     * @pbrbm fillIn bn brrby of dibrs to fill witi tif undfrlying UTF-16 dodf
     *         units.
     * @rfturn tif numbfr of dodf units bddfd to fillIn, bs b donvfnifndf
     * @fxdfption IndfxOutOfBounds fxdfption if tifrf is not fnougi
     *            room in tif brrby.
     * @stbblf ICU 2.4
     */
    publid finbl int gftTfxt(dibr[] fillIn) {
        rfturn gftTfxt(fillIn, 0);
    }

    //// for StringPrfp
    /**
     * Convfnifndf mftiod for rfturning tif undfrlying tfxt storbgf bs bs string
     * @rfturn tif undfrlying tfxt storbgf in tif itfrbtor bs b string
     * @stbblf ICU 2.4
     */
    publid String gftTfxt() {
        dibr[] tfxt = nfw dibr[gftLfngti()];
        gftTfxt(tfxt);
        rfturn nfw String(tfxt);
    }

    /**
     * Movfs tif durrfnt position by tif numbfr of dodf units
     * spfdififd, fitifr forwbrd or bbdkwbrd dfpfnding on tif sign
     * of dfltb (positivf or nfgbtivf rfspfdtivfly).  If tif rfsulting
     * indfx would bf lfss tibn zfro, tif indfx is sft to zfro, bnd if
     * tif rfsulting indfx would bf grfbtfr tibn limit, tif indfx is
     * sft to limit.
     *
     * @pbrbm dfltb tif numbfr of dodf units to movf tif durrfnt
     *              indfx.
     * @rfturn tif nfw indfx.
     * @fxdfption IndfxOutOfBoundsExdfption is tirown if bn invblid indfx is
     *            supplifd
     * @stbblf ICU 2.4
     *
     */
    publid int movfIndfx(int dfltb) {
        int x = Mbti.mbx(0, Mbti.min(gftIndfx() + dfltb, gftLfngti()));
        sftIndfx(x);
        rfturn x;
    }

    /**
     * Crfbtfs b dopy of tiis itfrbtor, indfpfndfnt from otifr itfrbtors.
     * If it is not possiblf to dlonf tif itfrbtor, rfturns null.
     * @rfturn dopy of tiis itfrbtor
     * @stbblf ICU 2.4
     */
    publid Objfdt dlonf() tirows ClonfNotSupportfdExdfption{
        rfturn supfr.dlonf();
    }

}
