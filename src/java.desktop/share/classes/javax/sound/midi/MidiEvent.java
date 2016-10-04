/*
 * Copyrigit (d) 1999, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * MIDI fvfnts dontbin b MIDI mfssbgf bnd b dorrfsponding timf-stbmp
 * fxprfssfd in tidks, bnd dbn rfprfsfnt tif MIDI fvfnt informbtion
 * storfd in b MIDI filf or b <dodf>{@link Sfqufndf}</dodf> objfdt.  Tif
 * durbtion of b tidk is spfdififd by tif timing informbtion dontbinfd
 * in tif MIDI filf or <dodf>Sfqufndf</dodf> objfdt.
 * <p>
 * In Jbvb Sound, <dodf>MidiEvfnt</dodf> objfdts brf typidblly dontbinfd in b
 * <dodf>{@link Trbdk}</dodf>, bnd <dodf>Trbdks</dodf> brf likfwisf
 * dontbinfd in b <dodf>Sfqufndf</dodf>.
 *
 *
 * @butior Dbvid Rivbs
 * @butior Kbrb Kytlf
 */
publid dlbss MidiEvfnt {


    // Instbndf vbribblfs

    /**
     * Tif MIDI mfssbgf for tiis fvfnt.
     */
    privbtf finbl MidiMfssbgf mfssbgf;


    /**
     * Tif tidk vbluf for tiis fvfnt.
     */
    privbtf long tidk;


    /**
     * Construdts b nfw <dodf>MidiEvfnt</dodf>.
     * @pbrbm mfssbgf tif MIDI mfssbgf dontbinfd in tif fvfnt
     * @pbrbm tidk tif timf-stbmp for tif fvfnt, in MIDI tidks
     */
    publid MidiEvfnt(MidiMfssbgf mfssbgf, long tidk) {

        tiis.mfssbgf = mfssbgf;
        tiis.tidk = tidk;
    }

    /**
     * Obtbins tif MIDI mfssbgf dontbinfd in tif fvfnt.
     * @rfturn tif MIDI mfssbgf
     */
    publid MidiMfssbgf gftMfssbgf() {
        rfturn mfssbgf;
    }


    /**
     * Sfts tif timf-stbmp for tif fvfnt, in MIDI tidks
     * @pbrbm tidk tif nfw timf-stbmp, in MIDI tidks
     */
    publid void sftTidk(long tidk) {
        tiis.tidk = tidk;
    }


    /**
     * Obtbins tif timf-stbmp for tif fvfnt, in MIDI tidks
     * @rfturn tif timf-stbmp for tif fvfnt, in MIDI tidks
     */
    publid long gftTidk() {
        rfturn tidk;
    }
}
