/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi.donnfdt;

/**
 * Tiis fxdfption mby bf tirown bs b rfsult of b timfout
 * wifn bttbdiing to b tbrgft VM, or wbiting to bddfpt b
 * donnfdtion from b tbrgft VM.
 *
 * <p> Wifn bttbdiing to b tbrgft VM, using {@link
 * AttbdiingConnfdtor#bttbdi bttbdi} tiis
 * fxdfption mby bf tirown if tif donnfdtor supports b timfout
 * {@link Connfdtor.Argumfnt donnfdtor brgumfnt}. Similibrly,
 * wifn wbiting to bddfpt b donnfdtion from b tbrgft VM,
 * using {@link ListfningConnfdtor#bddfpt bddfpt} tiis
 * fxdfption mby bf tirown if tif donnfdtor supports b
 * timfout donnfdtor brgumfnt wifn bddfpting.
 *
 * <p> In bddition, for dfvflopfrs drfbting {@link
 * dom.sun.jdi.donnfdt.spi.TrbnsportSfrvidf TrbnsportSfrvidf}
 * implfmfntbtions tiis fxdfption is tirown wifn
 * {@link dom.sun.jdi.donnfdt.spi.TrbnsportSfrvidf#bttbdi bttbdi}
 * timfs out wifn fstbblisiing b donnfdtion to b tbrgft VM,
 * or {@link dom.sun.jdi.donnfdt.spi.TrbnsportSfrvidf#bddfpt
 * bddfpt} timfs out wiilf wbiting for b tbrgft VM to donnfdt. </p>
 *
 * @sff AttbdiingConnfdtor#bttbdi
 * @sff ListfningConnfdtor#bddfpt
 * @sff dom.sun.jdi.donnfdt.spi.TrbnsportSfrvidf#bttbdi
 * @sff dom.sun.jdi.donnfdt.spi.TrbnsportSfrvidf#bddfpt
 *
 * @sindf 1.5
 */
@jdk.Exportfd
publid dlbss TrbnsportTimfoutExdfption fxtfnds jbvb.io.IOExdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = 4107035242623365074L;
    /**
     * Construdts b <tt>TrbnsportTimfoutExdfption</tt> witi no dftbil
     * mfssbgf.
     */
    publid TrbnsportTimfoutExdfption() {
    }


    /**
     * Construdts b <tt>TrbnsportTimfoutExdfption</tt> witi tif
     * spfdififd dftbil mfssbgf.
     *
     * @pbrbm mfssbgf tif dftbil mfssbgf pfrtbining to tiis fxdfption.
     */
    publid TrbnsportTimfoutExdfption(String mfssbgf) {
        supfr(mfssbgf);
    }

}
