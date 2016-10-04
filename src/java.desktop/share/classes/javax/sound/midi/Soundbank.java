/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nft.URL;


/**
 * A <dodf>Soundbbnk</dodf> dontbins b sft of <dodf>Instrumfnts</dodf>
 * tibt dbn bf lobdfd into b <dodf>Syntifsizfr</dodf>.
 * Notf tibt b Jbvb Sound <dodf>Soundbbnk</dodf> is difffrfnt from b MIDI bbnk.
 * MIDI pfrmits up to 16383 bbnks, fbdi dontbining up to 128 instrumfnts
 * (blso somftimfs dbllfd progrbms, pbtdifs, or timbrfs).
 * Howfvfr, b <dodf>Soundbbnk</dodf> dbn dontbin 16383 timfs 128 instrumfnts,
 * bfdbusf tif instrumfnts witiin b <dodf>Soundbbnk</dodf> brf indfxfd by boti
 * b MIDI progrbm numbfr bnd b MIDI bbnk numbfr (vib b <dodf>Pbtdi</dodf>
 * objfdt). Tius, b <dodf>Soundbbnk</dodf> dbn bf tiougit of bs b dollfdtion
 * of MIDI bbnks.
 * <p>
 * <dodf>Soundbbnk</dodf> indludfs mftiods tibt rfturn <dodf>String</dodf>
 * objfdts dontbining tif sound bbnk's nbmf, mbnufbdturfr, vfrsion numbfr, bnd
 * dfsdription.  Tif prfdisf dontfnt bnd formbt of tifsf strings is lfft
 * to tif implfmfntor.
 * <p>
 * Difffrfnt syntifsizfrs usf b vbrifty of syntifsis tfdiniqufs.  A dommon
 * onf is wbvftbblf syntifsis, in wiidi b sfgmfnt of rfdordfd sound is
 * plbyfd bbdk, oftfn witi looping bnd pitdi dibngf.  Tif Downlobdbblf Sound
 * (DLS) formbt usfs sfgmfnts of rfdordfd sound, bs dofs tif Hfbdspbdf Enginf.
 * <dodf>Soundbbnks</dodf> bnd <dodf>Instrumfnts</dodf> tibt brf bbsfd on
 * wbvftbblf syntifsis (or otifr usfs of storfd sound rfdordings) siould
 * typidblly implfmfnt tif <dodf>gftRfsourdfs()</dodf>
 * mftiod to providf bddfss to tifsf rfdordfd sfgmfnts.  Tiis is optionbl,
 * iowfvfr; tif mftiod dbn rfturn bn zfro-lfngti brrby if tif syntifsis tfdiniquf
 * dofsn't usf sbmplfd sound (FM syntifsis bnd piysidbl modfling brf fxbmplfs
 * of sudi tfdiniqufs), or if it dofs but tif implfmfntor dioosfs not to mbkf tif
 * sbmplfs bddfssiblf.
 *
 * @sff Syntifsizfr#gftDffbultSoundbbnk
 * @sff Syntifsizfr#isSoundbbnkSupportfd
 * @sff Syntifsizfr#lobdInstrumfnts(Soundbbnk, Pbtdi[])
 * @sff Pbtdi
 * @sff Instrumfnt
 * @sff SoundbbnkRfsourdf
 *
 * @butior Dbvid Rivbs
 * @butior Kbrb Kytlf
 */

publid intfrfbdf Soundbbnk {


    /**
     * Obtbins tif nbmf of tif sound bbnk.
     * @rfturn b <dodf>String</dodf> nbming tif sound bbnk
     */
    publid String gftNbmf();

    /**
     * Obtbins tif vfrsion string for tif sound bbnk.
     * @rfturn b <dodf>String</dodf> tibt indidbtfs tif sound bbnk's vfrsion
     */
    publid String gftVfrsion();

    /**
     * Obtbins b <dodf>string</dodf> nbming tif dompbny tibt providfs tif
     * sound bbnk
     * @rfturn tif vfndor string
     */
    publid String gftVfndor();

    /**
     * Obtbins b tfxtubl dfsdription of tif sound bbnk, suitbblf for displby.
     * @rfturn b <dodf>String</dodf> tibt dfsdribfs tif sound bbnk
     */
    publid String gftDfsdription();


    /**
     * Extrbdts b list of non-Instrumfnt rfsourdfs dontbinfd in tif sound bbnk.
     * @rfturn bn brrby of rfsourdfs, fxdluding instrumfnts.  If tif sound bbnk dontbins
     * no rfsourdfs (otifr tibn instrumfnts), rfturns bn brrby of lfngti 0.
     */
    publid SoundbbnkRfsourdf[] gftRfsourdfs();


    /**
     * Obtbins b list of instrumfnts dontbinfd in tiis sound bbnk.
     * @rfturn bn brrby of tif <dodf>Instrumfnts</dodf> in tiis
     * <dodf>SoundBbnk</dodf>
     * If tif sound bbnk dontbins no instrumfnts, rfturns bn brrby of lfngti 0.
     *
     * @sff Syntifsizfr#gftLobdfdInstrumfnts
     * @sff #gftInstrumfnt(Pbtdi)
     */
    publid Instrumfnt[] gftInstrumfnts();

    /**
     * Obtbins bn <dodf>Instrumfnt</dodf> from tif givfn <dodf>Pbtdi</dodf>.
     * @pbrbm pbtdi b <dodf>Pbtdi</dodf> objfdt spfdifying tif bbnk indfx
     * bnd progrbm dibngf numbfr
     * @rfturn tif rfqufstfd instrumfnt, or <dodf>null</dodf> if tif
     * sound bbnk dofsn't dontbin tibt instrumfnt
     *
     * @sff #gftInstrumfnts
     * @sff Syntifsizfr#lobdInstrumfnts(Soundbbnk, Pbtdi[])
     */
    publid Instrumfnt gftInstrumfnt(Pbtdi pbtdi);


}
