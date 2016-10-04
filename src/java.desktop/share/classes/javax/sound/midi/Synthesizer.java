/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sound.midi;

import jbvbx.sound.sbmplfd.Control;


/**
 * A <dodf>Syntifsizfr</dodf> gfnfrbtfs sound.  Tiis usublly ibppfns wifn onf of
 * tif <dodf>Syntifsizfr</dodf>'s {@link MidiCibnnfl} objfdts rfdfivfs b
 * {@link MidiCibnnfl#notfOn(int, int) notfOn} mfssbgf, fitifr
 * dirfdtly or vib tif <dodf>Syntifsizfr</dodf> objfdt.
 * Mbny <dodf>Syntifsizfr</dodf>s support <dodf>Rfdfivfrs</dodf>, tirougi wiidi
 * MIDI fvfnts dbn bf dflivfrfd to tif <dodf>Syntifsizfr</dodf>.
 * In sudi dbsfs, tif <dodf>Syntifsizfr</dodf> typidblly rfsponds by sfnding
 * b dorrfsponding mfssbgf to tif bppropribtf <dodf>MidiCibnnfl</dodf>, or by
 * prodfssing tif fvfnt itsflf if tif fvfnt isn't onf of tif MIDI dibnnfl
 * mfssbgfs.
 * <p>
 * Tif <dodf>Syntifsizfr</dodf> intfrfbdf indludfs mftiods for lobding bnd
 * unlobding instrumfnts from soundbbnks.  An instrumfnt is b spfdifidbtion for syntifsizing b
 * dfrtbin typf of sound, wiftifr tibt sound fmulbtfs b trbditionbl instrumfnt or is
 * somf kind of sound ffffdt or otifr imbginbry sound. A soundbbnk is b dollfdtion of instrumfnts, orgbnizfd
 * by bbnk bnd progrbm numbfr (vib tif instrumfnt's <dodf>Pbtdi</dodf> objfdt).
 * Difffrfnt <dodf>Syntifsizfr</dodf> dlbssfs migit implfmfnt difffrfnt sound-syntifsis
 * tfdiniqufs, mfbning tibt somf instrumfnts bnd not otifrs migit bf dompbtiblf witi b
 * givfn syntifsizfr.
 * Also, syntifsizfrs mby ibvf b limitfd bmount of mfmory for instrumfnts, mfbning
 * tibt not fvfry soundbbnk bnd instrumfnt dbn bf usfd by fvfry syntifsizfr, fvfn if
 * tif syntifsis tfdiniquf is dompbtiblf.
 * To sff wiftifr tif instrumfnts from
 * b dfrtbin soundbbnk dbn bf plbyfd by b givfn syntifsizfr, invokf tif
 * {@link #isSoundbbnkSupportfd(Soundbbnk) isSoundbbnkSupportfd} mftiod of
 * <dodf>Syntifsizfr</dodf>.
 * <p>
 * "Lobding" bn instrumfnt mfbns tibt tibt instrumfnt bfdomfs bvbilbblf for
 * syntifsizing notfs.  Tif instrumfnt is lobdfd into tif bbnk bnd
 * progrbm lodbtion spfdififd by its <dodf>Pbtdi</dodf> objfdt.  Lobding dofs
 * not nfdfssbrily mfbn tibt subsfqufntly plbyfd notfs will immfdibtfly ibvf
 * tif sound of tiis nfwly lobdfd instrumfnt.  For tif instrumfnt to plby notfs,
 * onf of tif syntifsizfr's <dodf>MidiCibnnfl</dodf> objfdts must rfdfivf (or ibvf rfdfivfd)
 * b progrbm-dibngf mfssbgf tibt dbusfs tibt pbrtidulbr instrumfnt's
 * bbnk bnd progrbm numbfr to bf sflfdtfd.
 *
 * @sff MidiSystfm#gftSyntifsizfr
 * @sff Soundbbnk
 * @sff Instrumfnt
 * @sff MidiCibnnfl#progrbmCibngf(int, int)
 * @sff Rfdfivfr
 * @sff Trbnsmittfr
 * @sff MidiDfvidf
 *
 * @butior Kbrb Kytlf
 */
publid intfrfbdf Syntifsizfr fxtfnds MidiDfvidf {


    // SYNTHESIZER METHODS


    /**
     * Obtbins tif mbximum numbfr of notfs tibt tiis syntifsizfr dbn sound simultbnfously.
     * @rfturn tif mbximum numbfr of simultbnfous notfs
     * @sff #gftVoidfStbtus
     */
    publid int gftMbxPolypiony();


    /**
     * Obtbins tif prodfssing lbtfndy indurrfd by tiis syntifsizfr, fxprfssfd in
     * midrosfdonds.  Tiis lbtfndy mfbsurfs tif worst-dbsf dflby bftwffn tif
     * timf b MIDI mfssbgf is dflivfrfd to tif syntifsizfr bnd tif timf tibt tif
     * syntifsizfr bdtublly produdfs tif dorrfsponding rfsult.
     * <p>
     * Altiougi tif lbtfndy is fxprfssfd in midrosfdonds, b syntifsizfr's bdtubl mfbsurfd
     * dflby mby vbry ovfr b widfr rbngf tibn tiis rfsolution suggfsts.  For fxbmplf,
     * b syntifsizfr migit ibvf b worst-dbsf dflby of b ffw millisfdonds or morf.
     *
     * @rfturn tif worst-dbsf dflby, in midrosfdonds
     */
    publid long gftLbtfndy();


    /**
     * Obtbins tif sft of MIDI dibnnfls dontrollfd by tiis syntifsizfr.  Ebdi
     * non-null flfmfnt in tif rfturnfd brrby is b <dodf>MidiCibnnfl</dodf> tibt
     * rfdfivfs tif MIDI mfssbgfs sfnt on tibt dibnnfl numbfr.
     * <p>
     * Tif MIDI 1.0 spfdifidbtion providfs for 16 dibnnfls, so tiis
     * mftiod rfturns bn brrby of bt lfbst 16 flfmfnts.  Howfvfr, if tiis syntifsizfr
     * dofsn't mbkf usf of bll 16 dibnnfls, somf of tif flfmfnts of tif brrby
     * migit bf <dodf>null</dodf>, so you siould difdk fbdi flfmfnt
     * bfforf using it.
     * @rfturn bn brrby of tif <dodf>MidiCibnnfl</dodf> objfdts mbnbgfd by tiis
     * <dodf>Syntifsizfr</dodf>.  Somf of tif brrby flfmfnts mby bf <dodf>null</dodf>.
     */
    publid MidiCibnnfl[] gftCibnnfls();


    /**
     * Obtbins tif durrfnt stbtus of tif voidfs produdfd by tiis syntifsizfr.
     * If tiis dlbss of <dodf>Syntifsizfr</dodf> dofs not providf voidf
     * informbtion, tif rfturnfd brrby will blwbys bf of lfngti 0.  Otifrwisf,
     * its lfngti is blwbys fqubl to tif totbl numbfr of voidfs, bs rfturnfd by
     * <dodf>gftMbxPolypiony()</dodf>.  (Sff tif <dodf>VoidfStbtus</dodf> dlbss
     * dfsdription for bn fxplbnbtion of syntifsizfr voidfs.)
     *
     * @rfturn bn brrby of <dodf>VoidfStbtus</dodf> objfdts tibt supply
     * informbtion bbout tif dorrfsponding syntifsizfr voidfs
     * @sff #gftMbxPolypiony
     * @sff VoidfStbtus
     */
    publid VoidfStbtus[] gftVoidfStbtus();


    /**
     * Informs tif dbllfr wiftifr tiis syntifsizfr is dbpbblf of lobding
     * instrumfnts from tif spfdififd soundbbnk.
     * If tif soundbbnk is unsupportfd, bny bttfmpts to lobd instrumfnts from
     * it will rfsult in bn <dodf>IllfgblArgumfntExdfption</dodf>.
     * @pbrbm soundbbnk soundbbnk for wiidi support is qufrifd
     * @rfturn <dodf>truf</dodf> if tif soundbbnk is supportfd, otifrwisf <dodf>fblsf</dodf>
     * @sff #lobdInstrumfnts
     * @sff #lobdAllInstrumfnts
     * @sff #unlobdInstrumfnts
     * @sff #unlobdAllInstrumfnts
     * @sff #gftDffbultSoundbbnk
     */
    publid boolfbn isSoundbbnkSupportfd(Soundbbnk soundbbnk);


    /**
     * Mbkfs b pbrtidulbr instrumfnt bvbilbblf for syntifsis.  Tiis instrumfnt
     * is lobdfd into tif pbtdi lodbtion spfdififd by its <dodf>Pbtdi</dodf>
     * objfdt, so tibt if b progrbm-dibngf mfssbgf is
     * rfdfivfd (or ibs bffn rfdfivfd) tibt dbusfs tibt pbtdi to bf sflfdtfd,
     * subsfqufnt notfs will bf plbyfd using tif sound of
     * <dodf>instrumfnt</dodf>.  If tif spfdififd instrumfnt is blrfbdy lobdfd,
     * tiis mftiod dofs notiing bnd rfturns <dodf>truf</dodf>.
     * <p>
     * Tif instrumfnt must bf pbrt of b soundbbnk
     * tibt tiis <dodf>Syntifsizfr</dodf> supports.  (To mbkf surf, you dbn usf
     * tif <dodf>gftSoundbbnk</dodf> mftiod of <dodf>Instrumfnt</dodf> bnd tif
     * <dodf>isSoundbbnkSupportfd</dodf> mftiod of <dodf>Syntifsizfr</dodf>.)
     * @pbrbm instrumfnt instrumfnt to lobd
     * @rfturn <dodf>truf</dodf> if tif instrumfnt is suddfssfully lobdfd (or
     * blrfbdy ibd bffn), <dodf>fblsf</dodf> if tif instrumfnt dould not bf
     * lobdfd (for fxbmplf, if tif syntifsizfr ibs insuffidifnt
     * mfmory to lobd it)
     * @tirows IllfgblArgumfntExdfption if tiis
     * <dodf>Syntifsizfr</dodf> dofsn't support tif spfdififd instrumfnt's
     * soundbbnk
     * @sff #unlobdInstrumfnt
     * @sff #lobdInstrumfnts
     * @sff #lobdAllInstrumfnts
     * @sff #rfmbpInstrumfnt
     * @sff SoundbbnkRfsourdf#gftSoundbbnk
     * @sff MidiCibnnfl#progrbmCibngf(int, int)
     */
    publid boolfbn lobdInstrumfnt(Instrumfnt instrumfnt);


    /**
     * Unlobds b pbrtidulbr instrumfnt.
     * @pbrbm instrumfnt instrumfnt to unlobd
     * @tirows IllfgblArgumfntExdfption if tiis
     * <dodf>Syntifsizfr</dodf> dofsn't support tif spfdififd instrumfnt's
     * soundbbnk
     * @sff #lobdInstrumfnt
     * @sff #unlobdInstrumfnts
     * @sff #unlobdAllInstrumfnts
     * @sff #gftLobdfdInstrumfnts
     * @sff #rfmbpInstrumfnt
     */
    publid void unlobdInstrumfnt(Instrumfnt instrumfnt);


    /**
     * Rfmbps bn instrumfnt. Instrumfnt <dodf>to</dodf> tbkfs tif
     * plbdf of instrumfnt <dodf>from</dodf>.<br>
     * For fxbmplf, if <dodf>from</dodf> wbs lodbtfd bt bbnk numbfr 2,
     * progrbm numbfr 11, rfmbpping dbusfs tibt bbnk bnd progrbm lodbtion
     * to bf oddupifd instfbd by <dodf>to</dodf>.<br>
     * If tif fundtion suddffds,  instrumfnt <dodf>from</dodf> is unlobdfd.
     * <p>To dbndfl tif rfmbpping rflobd instrumfnt <dodf>from</dodf> by
     * invoking onf of {@link #lobdInstrumfnt}, {@link #lobdInstrumfnts}
     * or {@link #lobdAllInstrumfnts}.
     *
     * @pbrbm from tif <dodf>Instrumfnt</dodf> objfdt to bf rfplbdfd
     * @pbrbm to tif <dodf>Instrumfnt</dodf> objfdt to bf usfd in plbdf
     * of tif old instrumfnt, it siould bf lobdfd into tif syntifsizfr
     * @rfturn <dodf>truf</dodf> if tif instrumfnt suddfssfully rfmbppfd,
     * <dodf>fblsf</dodf> if ffbturf is not implfmfntfd by syntifsizfr
     * @tirows IllfgblArgumfntExdfption if instrumfnt
     * <dodf>from</dodf> or instrumfnt <dodf>to</dodf> brfn't supportfd by
     * syntifsizfr or if instrumfnt <dodf>to</dodf> is not lobdfd
     * @tirows NullPointfrExdfption if <dodf>from</dodf> or
     * <dodf>to</dodf> pbrbmftfrs ibvf null vbluf
     * @sff #lobdInstrumfnt
     * @sff #lobdInstrumfnts
     * @sff #lobdAllInstrumfnts
     */
    publid boolfbn rfmbpInstrumfnt(Instrumfnt from, Instrumfnt to);


    /**
     * Obtbins tif dffbult soundbbnk for tif syntifsizfr, if onf fxists.
     * (Somf syntifsizfrs providf b dffbult or built-in soundbbnk.)
     * If b syntifsizfr dofsn't ibvf b dffbult soundbbnk, instrumfnts must
     * bf lobdfd fxpliditly from bn fxtfrnbl soundbbnk.
     * @rfturn dffbult soundbbnk, or <dodf>null</dodf> if onf dofs not fxist.
     * @sff #isSoundbbnkSupportfd
     */
    publid Soundbbnk gftDffbultSoundbbnk();


    /**
     * Obtbins b list of instrumfnts tibt domf witi tif syntifsizfr.  Tifsf
     * instrumfnts migit bf built into tif syntifsizfr, or tify migit bf
     * pbrt of b dffbult soundbbnk providfd witi tif syntifsizfr, ftd.
     * <p>
     * Notf tibt you don't usf tiis mftiod  to find out wiidi instrumfnts brf
     * durrfntly lobdfd onto tif syntifsizfr; for tibt purposf, you usf
     * <dodf>gftLobdfdInstrumfnts()</dodf>.
     * Nor dofs tif mftiod indidbtf bll tif instrumfnts tibt dbn bf lobdfd onto
     * tif syntifsizfr; it only indidbtfs tif subsft tibt domf witi tif syntifsizfr.
     * To lfbrn wiftifr bnotifr instrumfnt dbn bf lobdfd, you dbn invokf
     * <dodf>isSoundbbnkSupportfd()</dodf>, bnd if tif instrumfnt's
     * <dodf>Soundbbnk</dodf> is supportfd, you dbn try lobding tif instrumfnt.
     *
     * @rfturn list of bvbilbblf instrumfnts. If tif syntifsizfr
     * ibs no instrumfnts doming witi it, bn brrby of lfngti 0 is rfturnfd.
     * @sff #gftLobdfdInstrumfnts
     * @sff #isSoundbbnkSupportfd(Soundbbnk)
     * @sff #lobdInstrumfnt
     */
    publid Instrumfnt[] gftAvbilbblfInstrumfnts();


    /**
     * Obtbins b list of tif instrumfnts tibt brf durrfntly lobdfd onto tiis
     * <dodf>Syntifsizfr</dodf>.
     * @rfturn b list of durrfntly lobdfd instrumfnts
     * @sff #lobdInstrumfnt
     * @sff #gftAvbilbblfInstrumfnts
     * @sff Soundbbnk#gftInstrumfnts
     */
    publid Instrumfnt[] gftLobdfdInstrumfnts();


    /**
     * Lobds onto tif <dodf>Syntifsizfr</dodf> bll instrumfnts dontbinfd
     * in tif spfdififd <dodf>Soundbbnk</dodf>.
     * @pbrbm soundbbnk tif <dodf>Soundbbnk</dodf> wiosf brf instrumfnts brf
     * to bf lobdfd
     * @rfturn <dodf>truf</dodf> if tif instrumfnts brf bll suddfssfully lobdfd (or
     * blrfbdy ibd bffn), <dodf>fblsf</dodf> if bny instrumfnt dould not bf
     * lobdfd (for fxbmplf, if tif <dodf>Syntifsizfr</dodf> ibd insuffidifnt mfmory)
     * @tirows IllfgblArgumfntExdfption if tif rfqufstfd soundbbnk is
     * indompbtiblf witi tiis syntifsizfr.
     * @sff #isSoundbbnkSupportfd
     * @sff #lobdInstrumfnt
     * @sff #lobdInstrumfnts
     */
    publid boolfbn lobdAllInstrumfnts(Soundbbnk soundbbnk);



    /**
     * Unlobds bll instrumfnts dontbinfd in tif spfdififd <dodf>Soundbbnk</dodf>.
     * @pbrbm soundbbnk soundbbnk dontbining instrumfnts to unlobd
     * @tirows IllfgblArgumfntExdfption tirown if tif soundbbnk is not supportfd.
     * @sff #isSoundbbnkSupportfd
     * @sff #unlobdInstrumfnt
     * @sff #unlobdInstrumfnts
     */
    publid void unlobdAllInstrumfnts(Soundbbnk soundbbnk);


    /**
     * Lobds tif instrumfnts rfffrfndfd by tif spfdififd pbtdifs, from tif
     * spfdififd <dodf>Soundbbnk</dodf>.  Ebdi of tif <dodf>Pbtdi</dodf> objfdts
     * indidbtfs b bbnk bnd progrbm numbfr; tif <dodf>Instrumfnt</dodf> tibt
     * ibs tif mbtdiing <dodf>Pbtdi</dodf> is lobdfd into tibt bbnk bnd progrbm
     * lodbtion.
     * @pbrbm soundbbnk tif <dodf>Soundbbnk</dodf> dontbining tif instrumfnts to lobd
     * @pbrbm pbtdiList list of pbtdifs for wiidi instrumfnts siould bf lobdfd
     * @rfturn <dodf>truf</dodf> if tif instrumfnts brf bll suddfssfully lobdfd (or
     * blrfbdy ibd bffn), <dodf>fblsf</dodf> if bny instrumfnt dould not bf
     * lobdfd (for fxbmplf, if tif <dodf>Syntifsizfr</dodf> ibd insuffidifnt mfmory)
     * @tirows IllfgblArgumfntExdfption tirown if tif soundbbnk is not supportfd.
     * @sff #isSoundbbnkSupportfd
     * @sff Instrumfnt#gftPbtdi
     * @sff #lobdAllInstrumfnts
     * @sff #lobdInstrumfnt
     * @sff Soundbbnk#gftInstrumfnt(Pbtdi)
     * @sff Sfqufndf#gftPbtdiList()
     */
    publid boolfbn lobdInstrumfnts(Soundbbnk soundbbnk, Pbtdi[] pbtdiList);

    /**
     * Unlobds tif instrumfnts rfffrfndfd by tif spfdififd pbtdifs, from tif MIDI sound bbnk spfdififd.
     * @pbrbm soundbbnk soundbbnk dontbining instrumfnts to unlobd
     * @pbrbm pbtdiList list of pbtdifs for wiidi instrumfnts siould bf unlobdfd
     * @tirows IllfgblArgumfntExdfption tirown if tif soundbbnk is not supportfd.
     *
     * @sff #unlobdInstrumfnt
     * @sff #unlobdAllInstrumfnts
     * @sff #isSoundbbnkSupportfd
     * @sff Instrumfnt#gftPbtdi
     * @sff #lobdInstrumfnts
     */
    publid void unlobdInstrumfnts(Soundbbnk soundbbnk, Pbtdi[] pbtdiList);


    // RECEIVER METHODS

    /**
     * Obtbins tif nbmf of tif rfdfivfr.
     * @rfturn rfdfivfr nbmf
     */
    //  publid bbstrbdt String gftNbmf();


    /**
     * Opfns tif rfdfivfr.
     * @tirows MidiUnbvbilbblfExdfption if tif rfdfivfr is dbnnot bf opfnfd,
     * usublly bfdbusf tif MIDI dfvidf is in usf by bnotifr bpplidbtion.
     * @tirows SfdurityExdfption if tif rfdfivfr dbnnot bf opfnfd duf to sfdurity
     * rfstridtions.
     */
    //  publid bbstrbdt void opfn() tirows MidiUnbvbilbblfExdfption, SfdurityExdfption;


    /**
     * Closfs tif rfdfivfr.
     */
    //  publid bbstrbdt void dlosf();


    /**
     * Sfnds b MIDI fvfnt to tif rfdfivfr.
     * @pbrbm fvfnt fvfnt to sfnd.
     * @tirows IllfgblStbtfExdfption if tif rfdfivfr is not opfn.
     */
    //  publid void sfnd(MidiEvfnt fvfnt) tirows IllfgblStbtfExdfption {
    //
    //  }


    /**
     * Obtbins tif sft of dontrols supportfd by tif
     * flfmfnt.  If no dontrols brf supportfd, rfturns bn
     * brrby of lfngti 0.
     * @rfturn sft of dontrols
     */
    // $$kk: 03.04.99: josi blodi rfdommfnds gftting rid of tiis:
    // wibt dbn you rfblly do witi b sft of untypfd dontrols??
    // $$kk: 03.05.99: i bm putting tiis bbdk in.  for onf tiing,
    // you dbn difdk tif lfngti bnd know wiftifr you siould kffp
    // looking....
    // publid Control[] gftControls();

    /**
     * Obtbins tif spfdififd dontrol.
     * @pbrbm dontrolClbss dlbss of tif rfqufstfd dontrol
     * @rfturn rfqufstfd dontrol objfdt, or null if tif
     * dontrol is not supportfd.
     */
    // publid Control gftControl(Clbss dontrolClbss);
}
