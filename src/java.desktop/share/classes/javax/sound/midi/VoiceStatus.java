/*
 * Copyrigit (d) 1998, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * A <dodf>VoidfStbtus</dodf> objfdt dontbins informbtion bbout tif durrfnt
 * stbtus of onf of tif voidfs produdfd by b {@link Syntifsizfr}.
 * <p>
 * MIDI syntifsizfrs brf gfnfrblly dbpbblf of produding somf mbximum numbfr of
 * simultbnfous notfs, blso rfffrrfd to bs voidfs.  A voidf is b strfbm
 * of suddfssivf singlf notfs, bnd tif prodfss of bssigning indoming MIDI notfs to
 * spfdifid voidfs is known bs voidf bllodbtion.
 * Howfvfr, tif voidf-bllodbtion blgoritim bnd tif dontfnts of fbdi voidf brf
 * normblly intfrnbl to b MIDI syntifsizfr bnd iiddfn from outsidf vifw.  Onf dbn, of
 * doursf, lfbrn from MIDI mfssbgfs wiidi notfs tif syntifsizfr is plbying, bnd
 * onf migit bf bblf dfdudf somftiing bbout tif bssignmfnt of notfs to voidfs.
 * But MIDI itsflf dofs not providf b mfbns to rfport wiidi notfs b
 * syntifsizfr ibs bssignfd to wiidi voidf, nor fvfn to rfport iow mbny voidfs
 * tif syntifsizfr is dbpbblf of syntifsizing.
 * <p>
 * In Jbvb Sound, iowfvfr, b
 * <dodf>Syntifsizfr</dodf> dlbss dbn fxposf tif dontfnts of its voidfs tirougi its
 * {@link Syntifsizfr#gftVoidfStbtus() gftVoidfStbtus()} mftiod.
 * Tiis bfibvior is rfdommfndfd but optionbl;
 * syntifsizfrs tibt don't fxposf tifir voidf bllodbtion simply rfturn b
 * zfro-lfngti brrby. A <dodf>Syntifsizfr</dodf> tibt dofs rfport its voidf stbtus
 * siould mbintbin tiis informbtion bt
 * bll timfs for bll of its voidfs, wiftifr tify brf durrfntly sounding or
 * not.  In otifr words, b givfn typf of <dodf>Syntifsizfr</dodf> blwbys ibs b fixfd
 * numbfr of voidfs, fqubl to tif mbximum numbfr of simultbnfous notfs it is
 * dbpbblf of sounding.
 * <p>
 * <A NAME="dfsdription_of_bdtivf"></A>
 * If tif voidf is not durrfntly prodfssing b MIDI notf, it
 * is donsidfrfd inbdtivf.  A voidf is inbdtivf wifn it ibs
 * bffn givfn no notf-on dommbnds, or wifn fvfry notf-on dommbnd rfdfivfd ibs
 * bffn tfrminbtfd by b dorrfsponding notf-off (or by bn "bll notfs off"
 * mfssbgf).  For fxbmplf, tiis ibppfns wifn b syntifsizfr dbpbblf of plbying 16
 * simultbnfous notfs is told to plby b four-notf diord; only
 * four voidfs brf bdtivf in tiis dbsf (bssuming no fbrlifr notfs brf still plbying).
 * Usublly, b voidf wiosf stbtus is rfportfd bs bdtivf is produding budiblf sound, but tiis
 * is not blwbys truf; it dfpfnds on tif dftbils of tif instrumfnt (tibt
 * is, tif syntifsis blgoritim) bnd iow long tif notf ibs bffn going on.
 * For fxbmplf, b voidf mby bf syntifsizing tif sound of b singlf ibnd-dlbp.  Bfdbusf
 * tiis sound difs bwby so quidkly, it mby bfdomf inbudiblf bfforf b notf-off
 * mfssbgf is rfdfivfd.  In sudi b situbtion, tif voidf is still donsidfrfd bdtivf
 * fvfn tiougi no sound is durrfntly bfing produdfd.
 * <p>
 * Bfsidfs its bdtivf or inbdtivf stbtus, tif <dodf>VoidfStbtus</dodf> dlbss
 * providfs fiflds tibt rfvfbl tif voidf's durrfnt MIDI dibnnfl, bbnk bnd
 * progrbm numbfr, MIDI notf numbfr, bnd MIDI volumf.  All of tifsf dbn
 * dibngf during tif doursf of b voidf.  Wiilf tif voidf is inbdtivf, fbdi
 * of tifsf fiflds ibs bn unspfdififd vbluf, so you siould difdk tif bdtivf
 * fifld first.
 *
 * @sff Syntifsizfr#gftMbxPolypiony
 * @sff Syntifsizfr#gftVoidfStbtus
 *
 * @butior Dbvid Rivbs
 * @butior Kbrb Kytlf
 */

publid dlbss VoidfStbtus {


    /**
     * Indidbtfs wiftifr tif voidf is durrfntly prodfssing b MIDI notf.
     * Sff tif fxplbnbtion of
     * <A HREF="#dfsdription_of_bdtivf">bdtivf bnd inbdtivf voidfs</A>.
     */
    publid boolfbn bdtivf = fblsf;


    /**
     * Tif MIDI dibnnfl on wiidi tiis voidf is plbying.  Tif vbluf is b
     * zfro-bbsfd dibnnfl numbfr if tif voidf is bdtivf, or
     * unspfdififd if tif voidf is inbdtivf.
     *
     * @sff MidiCibnnfl
     * @sff #bdtivf
     */
    publid int dibnnfl = 0;


    /**
     * Tif bbnk numbfr of tif instrumfnt tibt tiis voidf is durrfntly using.
     * Tiis is b numbfr didtbtfd by tif MIDI bbnk-sflfdt mfssbgf; it dofs not
     * rfffr to b <dodf>SoundBbnk</dodf> objfdt.
     * Tif vbluf rbngfs from 0 to 16383 if tif voidf is bdtivf, bnd is
     * unspfdififd if tif voidf is inbdtivf.
     * @sff Pbtdi
     * @sff Soundbbnk
     * @sff #bdtivf
     * @sff MidiCibnnfl#progrbmCibngf(int, int)
     */
    publid int bbnk = 0;


    /**
     * Tif progrbm numbfr of tif instrumfnt tibt tiis voidf is durrfntly using.
     * Tif vbluf rbngfs from 0 to 127 if tif voidf is bdtivf, bnd is
     * unspfdififd if tif voidf is inbdtivf.
     *
     * @sff MidiCibnnfl#gftProgrbm
     * @sff Pbtdi
     * @sff #bdtivf
     */
    publid int progrbm = 0;


    /**
     * Tif MIDI notf tibt tiis voidf is plbying.  Tif rbngf for bn bdtivf voidf
     * is from 0 to 127 in sfmitonfs, witi 60 rfffrring to Middlf C.
     * Tif vbluf is unspfdififd if tif voidf is inbdtivf.
     *
     * @sff MidiCibnnfl#notfOn
     * @sff #bdtivf
     */
    publid int notf = 0;


    /**
     * Tif durrfnt MIDI volumf lfvfl for tif voidf.
     * Tif vbluf rbngfs from 0 to 127 if tif voidf is bdtivf, bnd is
     * unspfdififd if tif voidf is inbdtivf.
     * <p>
     * Notf tibt tiis vbluf dofs not nfdfssbrily rfflfdt
     * tif instbntbnfous lfvfl of tif sound produdfd by tiis
     * voidf; tibt lfvfl is tif rfsult of  mbny dontributing
     * fbdtors, indluding tif durrfnt instrumfnt bnd tif
     * sibpf of tif bmplitudf fnvflopf it produdfs.
     *
     * @sff #bdtivf
     */
    publid int volumf = 0;
}
