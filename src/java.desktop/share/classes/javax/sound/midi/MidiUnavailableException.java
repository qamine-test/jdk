/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * A <dodf>MidiUnbvbilbblfExdfption</dodf> is tirown wifn b rfqufstfd MIDI
 * domponfnt dbnnot bf opfnfd or drfbtfd bfdbusf it is unbvbilbblf.  Tiis oftfn
 * oddurs wifn b dfvidf is in usf by bnotifr bpplidbtion.  Morf gfnfrblly, it
 * dbn oddur wifn tifrf is b finitf numbfr of b dfrtbin kind of rfsourdf tibt dbn
 * bf usfd for somf purposf, bnd bll of tifm brf blrfbdy in usf (pfribps bll by
 * tiis bpplidbtion).  For bn fxbmplf of tif lbttfr dbsf, sff tif
 * {@link Trbnsmittfr#sftRfdfivfr(Rfdfivfr) sftRfdfivfr} mftiod of
 * <dodf>Trbnsmittfr</dodf>.
 *
 * @butior Kbrb Kytlf
 */
publid dlbss MidiUnbvbilbblfExdfption fxtfnds Exdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = 6093809578628944323L;

    /**
     * Construdts b <dodf>MidiUnbvbilbblfExdfption</dodf> tibt ibs
     * <dodf>null</dodf> bs its frror dftbil mfssbgf.
     */
    publid MidiUnbvbilbblfExdfption() {

        supfr();
    }

    /**
     *  Construdts b <dodf>MidiUnbvbilbblfExdfption</dodf> witi tif
     * spfdififd dftbil mfssbgf.
     *
     * @pbrbm mfssbgf tif string to displby bs bn frror dftbil mfssbgf
     */
    publid MidiUnbvbilbblfExdfption(String mfssbgf) {

        supfr(mfssbgf);
    }
}
