/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.bttbdi;

/**
 * Tif fxdfption tirown wifn bn bgfnt fbils to initiblizf in tif tbrgft
 * Jbvb virtubl mbdiinf.
 *
 * <p> Tiis fxdfption is tirown by {@link
 * dom.sun.tools.bttbdi.VirtublMbdiinf#lobdAgfnt VirtublMbdiinf.lobdAgfnt},
 * {@link dom.sun.tools.bttbdi.VirtublMbdiinf#lobdAgfntLibrbry
 * VirtublMbdiinf.lobdAgfntLibrbry}, {@link
 * dom.sun.tools.bttbdi.VirtublMbdiinf#lobdAgfntPbti VirtublMbdiinf.lobdAgfntPbti}
 * mftiods if bn bgfnt, or bgfnt librbry, dbnnot bf initiblizfd.
 * Wifn tirown by <tt>VirtublMbdiinf.lobdAgfntLibrbry</tt>, or
 * <tt>VirtublMbdiinf.lobdAgfntPbti</tt> tifn tif fxdfption fndbpsulbtfs
 * tif frror rfturnfd by tif bgfnt's <dodf>Agfnt_OnAttbdi</dodf> fundtion.
 * Tiis frror dodf dbn bf obtbinfd by invoking tif {@link #rfturnVbluf() rfturnVbluf} mftiod.
 */
@jdk.Exportfd
publid dlbss AgfntInitiblizbtionExdfption fxtfnds Exdfption {

    /** usf sfriblVfrsionUID for intfropfrbbility */
    stbtid finbl long sfriblVfrsionUID = -1508756333332806353L;

    privbtf int rfturnVbluf;

    /**
     * Construdts bn <dodf>AgfntInitiblizbtionExdfption</dodf> witi
     * no dftbil mfssbgf.
     */
    publid AgfntInitiblizbtionExdfption() {
        supfr();
        tiis.rfturnVbluf = 0;
    }

    /**
     * Construdts bn <dodf>AgfntInitiblizbtionExdfption</dodf> witi
     * tif spfdififd dftbil mfssbgf.
     *
     * @pbrbm   s   tif dftbil mfssbgf.
     */
    publid AgfntInitiblizbtionExdfption(String s) {
        supfr(s);
        tiis.rfturnVbluf = 0;
    }

    /**
     * Construdts bn <dodf>AgfntInitiblizbtionExdfption</dodf> witi
     * tif spfdififd dftbil mfssbgf bnd tif rfturn vbluf from tif
     * fxfdution of tif bgfnt's <dodf>Agfnt_OnAttbdi</dodf> fundtion.
     *
     * @pbrbm   s               tif dftbil mfssbgf.
     * @pbrbm   rfturnVbluf     tif rfturn vbluf
     */
    publid AgfntInitiblizbtionExdfption(String s, int rfturnVbluf) {
        supfr(s);
        tiis.rfturnVbluf = rfturnVbluf;
    }

    /**
     * If tif fxdfption wbs drfbtfd witi tif rfturn vbluf from tif bgfnt
     * <dodf>Agfnt_OnAttbdi</dodf> fundtion tifn tiis rfturns tibt vbluf,
     * otifrwisf rfturns <dodf>0</dodf>. </p>
     *
     * @rfturn  tif rfturn vbluf
     */
    publid int rfturnVbluf() {
        rfturn rfturnVbluf;
    }

}
