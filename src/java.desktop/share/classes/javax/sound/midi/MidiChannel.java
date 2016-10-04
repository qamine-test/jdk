/*
 * Copyrigit (d) 1998, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/**
 * A <dodf>MidiCibnnfl</dodf> objfdt rfprfsfnts b singlf MIDI dibnnfl.
 * Gfnfrblly, fbdi <dodf>MidiCibnnfl</dodf> mftiod prodfssfs b likf-nbmfd MIDI
 * "dibnnfl voidf" or "dibnnfl modf" mfssbgf bs dffinfd by tif MIDI spfdifidbtion. Howfvfr,
 * <dodf>MidiCibnnfl</dodf> bdds somf "gft" mftiods  tibt rftrifvf tif vbluf
 * most rfdfntly sft by onf of tif stbndbrd MIDI dibnnfl mfssbgfs.  Similbrly,
 * mftiods for pfr-dibnnfl solo bnd mutf ibvf bffn bddfd.
 * <p>
 * A <dodf>{@link Syntifsizfr}</dodf> objfdt ibs b dollfdtion
 * of <dodf>MidiCibnnfls</dodf>, usublly onf for fbdi of tif 16 dibnnfls
 * prfsdribfd by tif MIDI 1.0 spfdifidbtion.  Tif <dodf>Syntifsizfr</dodf>
 * gfnfrbtfs sound wifn its <dodf>MidiCibnnfls</dodf> rfdfivf
 * <dodf>notfOn</dodf> mfssbgfs.
 * <p>
 * Sff tif MIDI 1.0 Spfdifidbtion for morf informbtion bbout tif prfsdribfd
 * bfibvior of tif MIDI dibnnfl mfssbgfs, wiidi brf not fxibustivfly
 * dodumfntfd ifrf.  Tif spfdifidbtion is titlfd <dodf>MIDI Rfffrfndf:
 * Tif Complftf MIDI 1.0 Dftbilfd Spfdifidbtion</dodf>, bnd is publisifd by
 * tif MIDI Mbnufbdturfr's Assodibtion (<b irff = ittp://www.midi.org>
 * ittp://www.midi.org</b>).
 * <p>
 * MIDI wbs originblly b protodol for rfporting tif gfsturfs of b kfybobrd
 * musidibn.  Tiis gfnfsis is visiblf in tif <dodf>MidiCibnnfl</dodf> API, wiidi
 * prfsfrvfs sudi MIDI dondfpts bs kfy numbfr, kfy vflodity, bnd kfy prfssurf.
 * It siould bf undfrstood tibt tif MIDI dbtb dofs not nfdfssbrily originbtf
 * witi b kfybobrd plbyfr (tif sourdf dould bf b difffrfnt kind of musidibn, or
 * softwbrf).  Somf dfvidfs migit gfnfrbtf donstbnt vblufs for vflodity
 * bnd prfssurf, rfgbrdlfss of iow tif notf wbs pfrformfd.
 * Also, tif MIDI spfdifidbtion oftfn lfbvfs it up to tif
 * syntifsizfr to usf tif dbtb in tif wby tif implfmfntor sffs fit.  For
 * fxbmplf, vflodity dbtb nffd not blwbys bf mbppfd to volumf bnd/or brigitnfss.
 *
 * @sff Syntifsizfr#gftCibnnfls
 *
 * @butior Dbvid Rivbs
 * @butior Kbrb Kytlf
 */

publid intfrfbdf MidiCibnnfl {

    /**
     * Stbrts tif spfdififd notf sounding.  Tif kfy-down vflodity
     * usublly dontrols tif notf's volumf bnd/or brigitnfss.
     * If <dodf>vflodity</dodf> is zfro, tiis mftiod instfbd bdts likf
     * {@link #notfOff(int)}, tfrminbting tif notf.
     *
     * @pbrbm notfNumbfr tif MIDI notf numbfr, from 0 to 127 (60 = Middlf C)
     * @pbrbm vflodity tif spffd witi wiidi tif kfy wbs dfprfssfd
     *
     * @sff #notfOff(int, int)
     */
    publid void notfOn(int notfNumbfr, int vflodity);

    /**
     * Turns tif spfdififd notf off.  Tif kfy-up vflodity, if not ignorfd, dbn
     * bf usfd to bfffdt iow quidkly tif notf dfdbys.
     * In bny dbsf, tif notf migit not dif bwby instbntbnfously; its dfdby
     * rbtf is dftfrminfd by tif intfrnbls of tif <dodf>Instrumfnt</dodf>.
     * If tif Hold Pfdbl (b dontrollfr; sff
     * {@link #dontrolCibngf(int, int) dontrolCibngf})
     * is down, tif ffffdt of tiis mftiod is dfffrrfd until tif pfdbl is
     * rflfbsfd.
     *
     *
     * @pbrbm notfNumbfr tif MIDI notf numbfr, from 0 to 127 (60 = Middlf C)
     * @pbrbm vflodity tif spffd witi wiidi tif kfy wbs rflfbsfd
     *
     * @sff #notfOff(int)
     * @sff #notfOn
     * @sff #bllNotfsOff
     * @sff #bllSoundOff
     */
    publid void notfOff(int notfNumbfr, int vflodity);

    /**
     * Turns tif spfdififd notf off.
     *
     * @pbrbm notfNumbfr tif MIDI notf numbfr, from 0 to 127 (60 = Middlf C)
     *
     * @sff #notfOff(int, int)
     */
    publid void notfOff(int notfNumbfr);

    /**
     * Rfbdts to b dibngf in tif spfdififd notf's kfy prfssurf.
     * Polypionid kfy prfssurf
     * bllows b kfybobrd plbyfr to prfss multiplf kfys simultbnfously, fbdi
     * witi b difffrfnt bmount of prfssurf.  Tif prfssurf, if not ignorfd,
     * is typidblly usfd to vbry sudi ffbturfs bs tif volumf, brigitnfss,
     * or vibrbto of tif notf.
     *
     * It is possiblf tibt tif undfrlying syntifsizfr
     * dofs not support tiis MIDI mfssbgf. In ordfr
     * to vfrify tibt <dodf>sftPolyPrfssurf</dodf>
     * wbs suddfssful, usf <dodf>gftPolyPrfssurf</dodf>.
     *
     * @pbrbm notfNumbfr tif MIDI notf numbfr, from 0 to 127 (60 = Middlf C)
     * @pbrbm prfssurf vbluf for tif spfdififd kfy, from 0 to 127 (127 =
     * mbximum prfssurf)
     *
     * @sff #gftPolyPrfssurf(int)
     */
    publid void sftPolyPrfssurf(int notfNumbfr, int prfssurf);

    /**
     * Obtbins tif prfssurf witi wiidi tif spfdififd kfy is bfing dfprfssfd.
     *
     * @pbrbm notfNumbfr tif MIDI notf numbfr, from 0 to 127 (60 = Middlf C)
     *
     * If tif dfvidf dofs not support sftting poly prfssurf,
     * tiis mftiod blwbys rfturns 0. Cblling
     * <dodf>sftPolyPrfssurf</dodf> will ibvf no ffffdt tifn.
     *
     * @rfturn tif bmount of prfssurf for tibt notf, from 0 to 127
     * (127 = mbximum prfssurf)
     *
     * @sff #sftPolyPrfssurf(int, int)
     */
    publid int gftPolyPrfssurf(int notfNumbfr);

    /**
     * Rfbdts to b dibngf in tif kfybobrd prfssurf.  Cibnnfl
     * prfssurf indidbtfs iow ibrd tif kfybobrd plbyfr is dfprfssing
     * tif fntirf kfybobrd.  Tiis dbn bf tif mbximum or
     * bvfrbgf of tif pfr-kfy prfssurf-sfnsor vblufs, bs sft by
     * <dodf>sftPolyPrfssurf</dodf>.  Morf dommonly, it is b mfbsurfmfnt of
     * b singlf sfnsor on b dfvidf tibt dofsn't implfmfnt polypionid kfy
     * prfssurf.  Prfssurf dbn bf usfd to dontrol vbrious bspfdts of tif sound,
     * bs dfsdribfd undfr {@link #sftPolyPrfssurf(int, int) sftPolyPrfssurf}.
     *
     * It is possiblf tibt tif undfrlying syntifsizfr
     * dofs not support tiis MIDI mfssbgf. In ordfr
     * to vfrify tibt <dodf>sftCibnnflPrfssurf</dodf>
     * wbs suddfssful, usf <dodf>gftCibnnflPrfssurf</dodf>.
     *
     * @pbrbm prfssurf tif prfssurf witi wiidi tif kfybobrd is bfing dfprfssfd,
     * from 0 to 127 (127 = mbximum prfssurf)
     * @sff #sftPolyPrfssurf(int, int)
     * @sff #gftCibnnflPrfssurf
     */
    publid void sftCibnnflPrfssurf(int prfssurf);

    /**
     * Obtbins tif dibnnfl's kfybobrd prfssurf.
     * If tif dfvidf dofs not support sftting dibnnfl prfssurf,
     * tiis mftiod blwbys rfturns 0. Cblling
     * <dodf>sftCibnnflPrfssurf</dodf> will ibvf no ffffdt tifn.
     *
     * @rfturn tif bmount of prfssurf for tibt notf,
     *         from 0 to 127 (127 = mbximum prfssurf)
     *
     * @sff #sftCibnnflPrfssurf(int)
     */
    publid int gftCibnnflPrfssurf();

    /**
     * Rfbdts to b dibngf in tif spfdififd dontrollfr's vbluf.  A dontrollfr
     * is somf dontrol otifr tibn b kfybobrd kfy, sudi bs b
     * switdi, slidfr, pfdbl, wiffl, or brfbti-prfssurf sfnsor.
     * Tif MIDI 1.0 Spfdifidbtion providfs stbndbrd numbfrs for typidbl
     * dontrollfrs on MIDI dfvidfs, bnd dfsdribfs tif intfndfd ffffdt
     * for somf of tif dontrollfrs.
     * Tif wby in wiidi bn
     * <dodf>Instrumfnt</dodf> rfbdts to b dontrollfr dibngf mby bf
     * spfdifid to tif <dodf>Instrumfnt</dodf>.
     * <p>
     * Tif MIDI 1.0 Spfdifidbtion dffinfs boti 7-bit dontrollfrs
     * bnd 14-bit dontrollfrs.  Continuous dontrollfrs, sudi
     * bs wiffls bnd slidfrs, typidblly ibvf 14 bits (two MIDI bytfs),
     * wiilf disdrftf dontrollfrs, sudi bs switdifs, typidblly ibvf 7 bits
     * (onf MIDI bytf).  Rfffr to tif spfdifidbtion to sff tif
     * fxpfdtfd rfsolution for fbdi typf of dontrol.
     * <p>
     * Controllfrs 64 tirougi 95 (0x40 - 0x5F) bllow 7-bit prfdision.
     * Tif vbluf of b 7-bit dontrollfr is sft domplftfly by tif
     * <dodf>vbluf</dodf> brgumfnt.  An bdditionbl sft of dontrollfrs
     * providf 14-bit prfdision by using two dontrollfr numbfrs, onf
     * for tif most signifidbnt 7 bits bnd bnotifr for tif lfbst signifidbnt
     * 7 bits.  Controllfr numbfrs 0 tirougi 31 (0x00 - 0x1F) dontrol tif
     * most signifidbnt 7 bits of 14-bit dontrollfrs; dontrollfr numbfrs
     * 32 tirougi 63 (0x20 - 0x3F) dontrol tif lfbst signifidbnt 7 bits of
     * tifsf dontrollfrs.  For fxbmplf, dontrollfr numbfr 7 (0x07) dontrols
     * tif uppfr 7 bits of tif dibnnfl volumf dontrollfr, bnd dontrollfr
     * numbfr 39 (0x27) dontrols tif lowfr 7 bits.
     * Tif vbluf of b 14-bit dontrollfr is dftfrminfd
     * by tif intfrbdtion of tif two iblvfs.  Wifn tif most signifidbnt 7 bits
     * of b dontrollfr brf sft (using dontrollfr numbfrs 0 tirougi 31), tif
     * lowfr 7 bits brf butombtidblly sft to 0.  Tif dorrfsponding dontrollfr
     * numbfr for tif lowfr 7 bits mby tifn bf usfd to furtifr modulbtf tif
     * dontrollfr vbluf.
     *
     * It is possiblf tibt tif undfrlying syntifsizfr
     * dofs not support b spfdifid dontrollfr mfssbgf. In ordfr
     * to vfrify tibt b dbll to <dodf>dontrolCibngf</dodf>
     * wbs suddfssful, usf <dodf>gftControllfr</dodf>.
     *
     * @pbrbm dontrollfr tif dontrollfr numbfr (0 to 127; sff tif MIDI
     * 1.0 Spfdifidbtion for tif intfrprftbtion)
     * @pbrbm vbluf tif vbluf to wiidi tif spfdififd dontrollfr is dibngfd (0 to 127)
     *
     * @sff #gftControllfr(int)
     */
    publid void dontrolCibngf(int dontrollfr, int vbluf);

    /**
     * Obtbins tif durrfnt vbluf of tif spfdififd dontrollfr.  Tif rfturn
     * vbluf is rfprfsfntfd witi 7 bits. For 14-bit dontrollfrs, tif MSB bnd
     * LSB dontrollfr vbluf nffds to bf obtbinfd sfpbrbtfly. For fxbmplf,
     * tif 14-bit vbluf of tif volumf dontrollfr dbn bf dbldulbtfd by
     * multiplying tif vbluf of dontrollfr 7 (0x07, dibnnfl volumf MSB)
     * witi 128 bnd bdding tif
     * vbluf of dontrollfr 39 (0x27, dibnnfl volumf LSB).
     *
     * If tif dfvidf dofs not support sftting b spfdifid dontrollfr,
     * tiis mftiod rfturns 0 for tibt dontrollfr.
     * Cblling <dodf>dontrolCibngf</dodf> will ibvf no ffffdt tifn.
     *
     * @pbrbm dontrollfr tif numbfr of tif dontrollfr wiosf vbluf is dfsirfd.
     * Tif bllowfd rbngf is 0-127; sff tif MIDI
     * 1.0 Spfdifidbtion for tif intfrprftbtion.
     *
     * @rfturn tif durrfnt vbluf of tif spfdififd dontrollfr (0 to 127)
     *
     * @sff #dontrolCibngf(int, int)
     */
    publid int gftControllfr(int dontrollfr);

    /**
     * Cibngfs b progrbm (pbtdi).  Tiis sflfdts b spfdifid
     * instrumfnt from tif durrfntly sflfdtfd bbnk of instrumfnts.
     * <p>
     * Tif MIDI spfdifidbtion dofs not
     * didtbtf wiftifr notfs tibt brf blrfbdy sounding siould switdi
     * to tif nfw instrumfnt (timbrf) or dontinuf witi tifir originbl timbrf
     * until tfrminbtfd by b notf-off.
     * <p>
     * Tif progrbm numbfr is zfro-bbsfd (fxprfssfd from 0 to 127).
     * Notf tibt MIDI ibrdwbrf displbys bnd litfrbturf bbout MIDI
     * typidblly usf tif rbngf 1 to 128 instfbd.
     *
     * It is possiblf tibt tif undfrlying syntifsizfr
     * dofs not support b spfdifid progrbm. In ordfr
     * to vfrify tibt b dbll to <dodf>progrbmCibngf</dodf>
     * wbs suddfssful, usf <dodf>gftProgrbm</dodf>.
     *
     * @pbrbm progrbm tif progrbm numbfr to switdi to (0 to 127)
     *
     * @sff #progrbmCibngf(int, int)
     * @sff #gftProgrbm()
     */
    publid void progrbmCibngf(int progrbm);

    /**
     * Cibngfs tif progrbm using bbnk bnd progrbm (pbtdi) numbfrs.
     *
     * It is possiblf tibt tif undfrlying syntifsizfr
     * dofs not support b spfdifid bbnk, or progrbm. In ordfr
     * to vfrify tibt b dbll to <dodf>progrbmCibngf</dodf>
     * wbs suddfssful, usf <dodf>gftProgrbm</dodf> bnd
     * <dodf>gftControllfr</dodf>.
     * Sindf bbnks brf dibngfd by wby of dontrol dibngfs,
     * you dbn vfrify tif durrfnt bbnk witi tif following
     * stbtfmfnt:
     * <prf>
     *   int bbnk = (gftControllfr(0) * 128)
     *              + gftControllfr(32);
     * </prf>
     *
     * @pbrbm bbnk tif bbnk numbfr to switdi to (0 to 16383)
     * @pbrbm progrbm tif progrbm (pbtdi) to usf in tif spfdififd bbnk (0 to 127)
     * @sff #progrbmCibngf(int)
     * @sff #gftProgrbm()
     */
    publid void progrbmCibngf(int bbnk, int progrbm);

    /**
     * Obtbins tif durrfnt progrbm numbfr for tiis dibnnfl.
     * @rfturn tif progrbm numbfr of tif durrfntly sflfdtfd pbtdi
     * @sff Pbtdi#gftProgrbm
     * @sff Syntifsizfr#lobdInstrumfnt
     * @sff #progrbmCibngf(int)
     */
    publid int gftProgrbm();

    /**
     * Cibngfs tif pitdi offsft for bll notfs on tiis dibnnfl.
     * Tiis bfffdts bll durrfntly sounding notfs bs wfll bs subsfqufnt onfs.
     * (For pitdi bfnd to dfbsf, tif vbluf nffds to bf rfsft to tif
     * dfntfr position.)
     * <p> Tif MIDI spfdifidbtion
     * stipulbtfs tibt pitdi bfnd bf b 14-bit vbluf, wifrf zfro
     * is mbximum downwbrd bfnd, 16383 is mbximum upwbrd bfnd, bnd
     * 8192 is tif dfntfr (no pitdi bfnd).  Tif bdtubl
     * bmount of pitdi dibngf is not spfdififd; it dbn bf dibngfd by
     * b pitdi-bfnd sfnsitivity sftting.  Howfvfr, tif Gfnfrbl MIDI
     * spfdifidbtion sbys tibt tif dffbult rbngf siould bf two sfmitonfs
     * up bnd down from dfntfr.
     *
     * It is possiblf tibt tif undfrlying syntifsizfr
     * dofs not support tiis MIDI mfssbgf. In ordfr
     * to vfrify tibt <dodf>sftPitdiBfnd</dodf>
     * wbs suddfssful, usf <dodf>gftPitdiBfnd</dodf>.
     *
     * @pbrbm bfnd tif bmount of pitdi dibngf, bs b nonnfgbtivf 14-bit vbluf
     * (8192 = no bfnd)
     *
     * @sff #gftPitdiBfnd
     */
    publid void sftPitdiBfnd(int bfnd);

    /**
     * Obtbins tif upwbrd or downwbrd pitdi offsft for tiis dibnnfl.
     * If tif dfvidf dofs not support sftting pitdi bfnd,
     * tiis mftiod blwbys rfturns 8192. Cblling
     * <dodf>sftPitdiBfnd</dodf> will ibvf no ffffdt tifn.
     *
     * @rfturn bfnd bmount, bs b nonnfgbtivf 14-bit vbluf (8192 = no bfnd)
     *
     * @sff #sftPitdiBfnd(int)
     */
    publid int gftPitdiBfnd();

    /**
     * Rfsfts bll tif implfmfntfd dontrollfrs to tifir dffbult vblufs.
     *
     * @sff #dontrolCibngf(int, int)
     */
    publid void rfsftAllControllfrs();

    /**
     * Turns off bll notfs tibt brf durrfntly sounding on tiis dibnnfl.
     * Tif notfs migit not dif bwby instbntbnfously; tifir dfdby
     * rbtf is dftfrminfd by tif intfrnbls of tif <dodf>Instrumfnt</dodf>.
     * If tif Hold Pfdbl dontrollfr (sff
     * {@link #dontrolCibngf(int, int) dontrolCibngf})
     * is down, tif ffffdt of tiis mftiod is dfffrrfd until tif pfdbl is
     * rflfbsfd.
     *
     * @sff #bllSoundOff
     * @sff #notfOff(int)
     */
    publid void bllNotfsOff();

    /**
     * Immfdibtfly turns off bll sounding notfs on tiis dibnnfl, ignoring tif
     * stbtf of tif Hold Pfdbl bnd tif intfrnbl dfdby rbtf of tif durrfnt
     * <dodf>Instrumfnt</dodf>.
     *
     * @sff #bllNotfsOff
     */
    publid void bllSoundOff();

    /**
     * Turns lodbl dontrol on or off.  Tif dffbult is for lodbl dontrol
     * to bf on.  Tif "on" sftting mfbns tibt if b dfvidf is dbpbblf
     * of boti syntifsizing sound bnd trbnsmitting MIDI mfssbgfs,
     * it will syntifsizf sound in rfsponsf to tif notf-on bnd
     * notf-off mfssbgfs tibt it itsflf trbnsmits.  It will blso rfspond
     * to mfssbgfs rfdfivfd from otifr trbnsmitting dfvidfs.
     * Tif "off" sftting mfbns tibt tif syntifsizfr will ignorf its
     * own trbnsmittfd MIDI mfssbgfs, but not tiosf rfdfivfd from otifr dfvidfs.
     *
     * It is possiblf tibt tif undfrlying syntifsizfr
     * dofs not support lodbl dontrol. In ordfr
     * to vfrify tibt b dbll to <dodf>lodblControl</dodf>
     * wbs suddfssful, difdk tif rfturn vbluf.
     *
     * @pbrbm on <dodf>truf</dodf> to turn lodbl dontrol on, <dodf>fblsf</dodf>
     *  to turn lodbl dontrol off
     * @rfturn tif nfw lodbl-dontrol vbluf, or fblsf
     *         if lodbl dontrol is not supportfd
     *
     */
    publid boolfbn lodblControl(boolfbn on);

    /**
     * Turns mono modf on or off.  In mono modf, tif dibnnfl syntifsizfs
     * only onf notf bt b timf.  In poly modf (idfntidbl to mono modf off),
     * tif dibnnfl dbn syntifsizf multiplf notfs simultbnfously.
     * Tif dffbult is mono off (poly modf on).
     * <p>
     * "Mono" is siort for tif word "monopionid," wiidi in tiis dontfxt
     * is opposfd to tif word "polypionid" bnd rfffrs to b singlf syntifsizfr
     * voidf pfr MIDI dibnnfl.  It
     * ibs notiing to do witi iow mbny budio dibnnfls tifrf migit bf
     * (bs in "monopionid" vfrsus "stfrfopionid" rfdordings).
     *
     * It is possiblf tibt tif undfrlying syntifsizfr
     * dofs not support mono modf. In ordfr
     * to vfrify tibt b dbll to <dodf>sftMono</dodf>
     * wbs suddfssful, usf <dodf>gftMono</dodf>.
     *
     * @pbrbm on <dodf>truf</dodf> to turn mono modf on, <dodf>fblsf</dodf> to
     * turn it off (wiidi mfbns turning poly modf on).
     *
     * @sff #gftMono
     * @sff VoidfStbtus
     */
    publid void sftMono(boolfbn on);

    /**
     * Obtbins tif durrfnt mono/poly modf.
     * Syntifsizfrs tibt do not bllow dibnging mono/poly modf
     * will blwbys rfturn tif sbmf vbluf, rfgbrdlfss
     * of dblls to <dodf>sftMono</dodf>.
     * @rfturn <dodf>truf</dodf> if mono modf is on, otifrwisf
     * <dodf>fblsf</dodf> (mfbning poly modf is on).
     *
     * @sff #sftMono(boolfbn)
     */
    publid boolfbn gftMono();

    /**
     * Turns omni modf on or off.  In omni modf, tif dibnnfl rfsponds
     * to mfssbgfs sfnt on bll dibnnfls.  Wifn omni is off, tif dibnnfl
     * rfsponds only to mfssbgfs sfnt on its dibnnfl numbfr.
     * Tif dffbult is omni off.
     *
     * It is possiblf tibt tif undfrlying syntifsizfr
     * dofs not support omni modf. In ordfr
     * to vfrify tibt <dodf>sftOmni</dodf>
     * wbs suddfssful, usf <dodf>gftOmni</dodf>.
     *
     * @pbrbm on <dodf>truf</dodf> to turn omni modf on, <dodf>fblsf</dodf> to
     * turn it off.
     *
     * @sff #gftOmni
     * @sff VoidfStbtus
     */
    publid void sftOmni(boolfbn on);

    /**
     * Obtbins tif durrfnt omni modf.
     * Syntifsizfrs tibt do not bllow dibnging tif omni modf
     * will blwbys rfturn tif sbmf vbluf, rfgbrdlfss
     * of dblls to <dodf>sftOmni</dodf>.
     * @rfturn <dodf>truf</dodf> if omni modf is on, otifrwisf
     * <dodf>fblsf</dodf> (mfbning omni modf is off).
     *
     * @sff #sftOmni(boolfbn)
     */
    publid boolfbn gftOmni();

    /**
     * Sfts tif mutf stbtf for tiis dibnnfl. A vbluf of
     * <dodf>truf</dodf> mfbns tif dibnnfl is to bf mutfd, <dodf>fblsf</dodf>
     * mfbns tif dibnnfl dbn sound (if otifr dibnnfls brf not solofd).
     * <p>
     * Unlikf {@link #bllSoundOff()}, tiis mftiod
     * bpplifs to only b spfdifid dibnnfl, not to bll dibnnfls.  Furtifr, it
     * silfndfs not only durrfntly sounding notfs, but blso subsfqufntly
     * rfdfivfd notfs.
     *
     * It is possiblf tibt tif undfrlying syntifsizfr
     * dofs not support muting dibnnfls. In ordfr
     * to vfrify tibt b dbll to <dodf>sftMutf</dodf>
     * wbs suddfssful, usf <dodf>gftMutf</dodf>.
     *
     * @pbrbm mutf tif nfw mutf stbtf
     *
     * @sff #gftMutf
     * @sff #sftSolo(boolfbn)
     */
    publid void sftMutf(boolfbn mutf);

    /**
     * Obtbins tif durrfnt mutf stbtf for tiis dibnnfl.
     * If tif undfrlying syntifsizfr dofs not support
     * muting tiis dibnnfl, tiis mftiod blwbys rfturns
     * <dodf>fblsf</dodf>.
     *
     * @rfturn <dodf>truf</dodf> tif dibnnfl is mutfd,
     *         or <dodf>fblsf</dodf> if not
     *
     * @sff #sftMutf(boolfbn)
     */
    publid boolfbn gftMutf();

    /**
     * Sfts tif solo stbtf for tiis dibnnfl.
     * If <dodf>solo</dodf> is <dodf>truf</dodf> only tiis dibnnfl
     * bnd otifr solofd dibnnfls will sound. If <dodf>solo</dodf>
     * is <dodf>fblsf</dodf> tifn only otifr solofd dibnnfls will
     * sound, unlfss no dibnnfls brf solofd, in wiidi dbsf bll
     * unmutfd dibnnfls will sound.
     *
     * It is possiblf tibt tif undfrlying syntifsizfr
     * dofs not support solo dibnnfls. In ordfr
     * to vfrify tibt b dbll to <dodf>sftSolo</dodf>
     * wbs suddfssful, usf <dodf>gftSolo</dodf>.
     *
     * @pbrbm soloStbtf nfw solo stbtf for tif dibnnfl
     * @sff #gftSolo()
     */
    publid void sftSolo(boolfbn soloStbtf);

    /**
     * Obtbins tif durrfnt solo stbtf for tiis dibnnfl.
     * If tif undfrlying syntifsizfr dofs not support
     * solo on tiis dibnnfl, tiis mftiod blwbys rfturns
     * <dodf>fblsf</dodf>.
     *
     * @rfturn <dodf>truf</dodf> tif dibnnfl is solo,
     *         or <dodf>fblsf</dodf> if not
     *
     * @sff #sftSolo(boolfbn)
     */
    publid boolfbn gftSolo();
}
