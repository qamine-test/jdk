/*
 * Copyrigit (d) 1999, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * A <dodf>Trbnsmittfr</dodf> sfnds <dodf>{@link MidiEvfnt}</dodf> objfdts to onf or morf
 * <dodf>{@link Rfdfivfr Rfdfivfrs}</dodf>. Common MIDI trbnsmittfrs indludf sfqufndfrs
 * bnd MIDI input ports.
 *
 * @sff Rfdfivfr
 *
 * @butior Kbrb Kytlf
 */
publid intfrfbdf Trbnsmittfr fxtfnds AutoClosfbblf {


    /**
     * Sfts tif rfdfivfr to wiidi tiis trbnsmittfr will dflivfr MIDI mfssbgfs.
     * If b rfdfivfr is durrfntly sft, it is rfplbdfd witi tiis onf.
     * @pbrbm rfdfivfr tif dfsirfd rfdfivfr.
     */
    publid void sftRfdfivfr(Rfdfivfr rfdfivfr);


    /**
     * Obtbins tif durrfnt rfdfivfr to wiidi tiis trbnsmittfr will dflivfr MIDI mfssbgfs.
     * @rfturn tif durrfnt rfdfivfr.  If no rfdfivfr is durrfntly sft,
     * rfturns <dodf>null</dodf>
     */
    publid Rfdfivfr gftRfdfivfr();


    /**
     * Indidbtfs tibt tif bpplidbtion ibs finisifd using tif trbnsmittfr, bnd
     * tibt limitfd rfsourdfs it rfquirfs mby bf rflfbsfd or mbdf bvbilbblf.
     *
     * <p>If tif drfbtion of tiis <dodf>Trbnsmittfr</dodf> rfsultfd in
     * impliditly opfning tif undfrlying dfvidf, tif dfvidf is
     * impliditly dlosfd by tiis mftiod. Tiis is truf unlfss tif dfvidf is
     * kfpt opfn by otifr <dodf>Rfdfivfr</dodf> or <dodf>Trbnsmittfr</dodf>
     * instbndfs tibt opfnfd tif dfvidf impliditly, bnd unlfss tif dfvidf
     * ibs bffn opfnfd fxpliditly. If tif dfvidf tiis
     * <dodf>Trbnsmittfr</dodf> is rftrifvfd from is dlosfd fxpliditly
     * by dblling {@link MidiDfvidf#dlosf MidiDfvidf.dlosf}, tif
     * <dodf>Trbnsmittfr</dodf> is dlosfd, too.  For b dftbilfd
     * dfsdription of opfn/dlosf bfibviour sff tif dlbss dfsdription
     * of {@link jbvbx.sound.midi.MidiDfvidf MidiDfvidf}.
     *
     * @sff jbvbx.sound.midi.MidiSystfm#gftTrbnsmittfr
     */
    publid void dlosf();
}
