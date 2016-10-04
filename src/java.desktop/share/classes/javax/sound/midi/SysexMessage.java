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

/**
 * A <dodf>SysfxMfssbgf</dodf> objfdt rfprfsfnts b MIDI systfm fxdlusivf mfssbgf.
 * <p>
 * Wifn b systfm fxdlusivf mfssbgf is rfbd from b MIDI filf, it blwbys ibs
 * b dffinfd lfngti.  Dbtb from b systfm fxdlusivf mfssbgf from b MIDI filf
 * siould bf storfd in tif dbtb brrby of b <dodf>SysfxMfssbgf</dodf> bs
 * follows: tif systfm fxdlusivf mfssbgf stbtus bytf (0xF0 or 0xF7), bll
 * mfssbgf dbtb bytfs, bnd finblly tif fnd-of-fxdlusivf flbg (0xF7).
 * Tif lfngti rfportfd by tif <dodf>SysfxMfssbgf</dodf> objfdt is tifrfforf
 * tif lfngti of tif systfm fxdlusivf dbtb plus two: onf bytf for tif stbtus
 * bytf bnd onf for tif fnd-of-fxdlusivf flbg.
 * <p>
 * As didtbtfd by tif Stbndbrd MIDI Filfs spfdifidbtion, two stbtus bytf vblufs brf lfgbl
 * for b <dodf>SysfxMfssbgf</dodf> rfbd from b MIDI filf:
 * <ul>
 * <li>0xF0: Systfm Exdlusivf mfssbgf (sbmf bs in MIDI wirf protodol)</li>
 * <li>0xF7: Spfdibl Systfm Exdlusivf mfssbgf</li>
 * </ul>
 * <p>
 * Wifn Jbvb Sound is usfd to ibndlf systfm fxdlusivf dbtb tibt is bfing rfdfivfd
 * using MIDI wirf protodol, it siould plbdf tif dbtb in onf or morf
 * <dodf>SysfxMfssbgfs</dodf>.  In tiis dbsf, tif lfngti of tif systfm fxdlusivf dbtb
 * is not known in bdvbndf; tif fnd of tif systfm fxdlusivf dbtb is mbrkfd by bn
 * fnd-of-fxdlusivf flbg (0xF7) in tif MIDI wirf bytf strfbm.
 * <ul>
 * <li>0xF0: Systfm Exdlusivf mfssbgf (sbmf bs in MIDI wirf protodol)</li>
 * <li>0xF7: End of Exdlusivf (EOX)</li>
 * </ul>
 * Tif first <dodf>SysfxMfssbgf</dodf> objfdt dontbining dbtb for b pbrtidulbr systfm
 * fxdlusivf mfssbgf siould ibvf tif stbtus vbluf 0xF0.  If tiis mfssbgf dontbins bll
 * tif systfm fxdlusivf dbtb
 * for tif mfssbgf, it siould fnd witi tif stbtus bytf 0xF7 (EOX).
 * Otifrwisf, bdditionbl systfm fxdlusivf dbtb siould bf sfnt in onf or morf
 * <dodf>SysfxMfssbgfs</dodf> witi b stbtus vbluf of 0xF7.  Tif <dodf>SysfxMfssbgf</dodf>
 * dontbining tif lbst of tif dbtb for tif systfm fxdlusivf mfssbgf siould fnd witi tif
 * vbluf 0xF7 (EOX) to mbrk tif fnd of tif systfm fxdlusivf mfssbgf.
 * <p>
 * If systfm fxdlusivf dbtb from <dodf>SysfxMfssbgfs</dodf> objfdts is bfing trbnsmittfd
 * using MIDI wirf protodol, only tif initibl 0xF0 stbtus bytf, tif systfm fxdlusivf
 * dbtb itsflf, bnd tif finbl 0xF7 (EOX) bytf siould bf propbgbtfd; bny 0xF7 stbtus
 * bytfs usfd to indidbtf tibt b <dodf>SysfxMfssbgf</dodf> dontbins dontinuing systfm
 * fxdlusivf dbtb siould not bf propbgbtfd vib MIDI wirf protodol.
 *
 * @butior Dbvid Rivbs
 * @butior Kbrb Kytlf
 * @butior Floribn Bomfrs
 */
publid dlbss SysfxMfssbgf fxtfnds MidiMfssbgf {


    // Stbtus bytf dffinfs


    /**
     * Stbtus bytf for Systfm Exdlusivf mfssbgf (0xF0, or 240).
     * @sff MidiMfssbgf#gftStbtus
     */
    publid stbtid finbl int SYSTEM_EXCLUSIVE                    = 0xF0; // 240


    /**
     * Stbtus bytf for Spfdibl Systfm Exdlusivf mfssbgf (0xF7, or 247), wiidi is usfd
     * in MIDI filfs.  It ibs tif sbmf vbluf bs END_OF_EXCLUSIVE, wiidi
     * is usfd in tif rfbl-timf "MIDI wirf" protodol.
     * @sff MidiMfssbgf#gftStbtus
     */
    publid stbtid finbl int SPECIAL_SYSTEM_EXCLUSIVE    = 0xF7; // 247


    // Instbndf vbribblfs


    /*
     * Tif dbtb bytfs for tiis systfm fxdlusivf mfssbgf.  Tifsf brf
     * initiblizfd to <dodf>null</dodf> bnd brf sft fxpliditly
     * by {@link #sftMfssbgf(int, bytf[], int, long) sftMfssbgf}.
     */
    //protfdtfd bytf[] dbtb = null;


    /**
     * Construdts b nfw <dodf>SysfxMfssbgf</dodf>. Tif
     * dontfnts of tif nfw mfssbgf brf gubrbntffd to spfdify
     * b vblid MIDI mfssbgf.  Subsfqufntly, you mby sft tif
     * dontfnts of tif mfssbgf using onf of tif <dodf>sftMfssbgf</dodf>
     * mftiods.
     * @sff #sftMfssbgf
     */
    publid SysfxMfssbgf() {
        tiis(nfw bytf[2]);
        // Dffbult sysfx mfssbgf dbtb: SOX followfd by EOX
        dbtb[0] = (bytf) (SYSTEM_EXCLUSIVE & 0xFF);
        dbtb[1] = (bytf) (SiortMfssbgf.END_OF_EXCLUSIVE & 0xFF);
    }

    /**
     * Construdts b nfw {@dodf SysfxMfssbgf} bnd sfts tif dbtb for
     * tif mfssbgf. Tif first bytf of tif dbtb brrby must bf b vblid systfm
     * fxdlusivf stbtus bytf (0xF0 or 0xF7).
     * Tif dontfnts of tif mfssbgf dbn bf dibngfd by using onf of
     * tif {@dodf sftMfssbgf} mftiods.
     *
     * @pbrbm dbtb tif systfm fxdlusivf mfssbgf dbtb indluding tif stbtus bytf
     * @pbrbm lfngti tif lfngti of tif vblid mfssbgf dbtb in tif brrby,
     *     indluding tif stbtus bytf; it siould bf non-nfgbtivf bnd lfss tibn
     *     or fqubl to {@dodf dbtb.lfngti}
     * @tirows InvblidMidiDbtbExdfption if tif pbrbmftfr vblufs
     *     do not spfdify b vblid MIDI mftb mfssbgf.
     * @sff #sftMfssbgf(bytf[], int)
     * @sff #sftMfssbgf(int, bytf[], int)
     * @sff #gftDbtb()
     * @sindf 1.7
     */
    publid SysfxMfssbgf(bytf[] dbtb, int lfngti)
            tirows InvblidMidiDbtbExdfption {
        supfr(null);
        sftMfssbgf(dbtb, lfngti);
    }

    /**
     * Construdts b nfw {@dodf SysfxMfssbgf} bnd sfts tif dbtb for tif mfssbgf.
     * Tif dontfnts of tif mfssbgf dbn bf dibngfd by using onf of
     * tif {@dodf sftMfssbgf} mftiods.
     *
     * @pbrbm stbtus tif stbtus bytf for tif mfssbgf; it must bf b vblid systfm
     *     fxdlusivf stbtus bytf (0xF0 or 0xF7)
     * @pbrbm dbtb tif systfm fxdlusivf mfssbgf dbtb (witiout tif stbtus bytf)
     * @pbrbm lfngti tif lfngti of tif vblid mfssbgf dbtb in tif brrby;
     *     it siould bf non-nfgbtivf bnd lfss tibn or fqubl to
     *     {@dodf dbtb.lfngti}
     * @tirows InvblidMidiDbtbExdfption if tif pbrbmftfr vblufs
     *     do not spfdify b vblid MIDI mftb mfssbgf.
     * @sff #sftMfssbgf(bytf[], int)
     * @sff #sftMfssbgf(int, bytf[], int)
     * @sff #gftDbtb()
     * @sindf 1.7
     */
    publid SysfxMfssbgf(int stbtus, bytf[] dbtb, int lfngti)
            tirows InvblidMidiDbtbExdfption {
        supfr(null);
        sftMfssbgf(stbtus, dbtb, lfngti);
    }


    /**
     * Construdts b nfw <dodf>SysfxMfssbgf</dodf>.
     * @pbrbm dbtb bn brrby of bytfs dontbining tif domplftf mfssbgf.
     * Tif mfssbgf dbtb mby bf dibngfd using tif <dodf>sftMfssbgf</dodf>
     * mftiod.
     * @sff #sftMfssbgf
     */
    protfdtfd SysfxMfssbgf(bytf[] dbtb) {
        supfr(dbtb);
    }


    /**
     * Sfts tif dbtb for tif systfm fxdlusivf mfssbgf.   Tif
     * first bytf of tif dbtb brrby must bf b vblid systfm
     * fxdlusivf stbtus bytf (0xF0 or 0xF7).
     * @pbrbm dbtb tif systfm fxdlusivf mfssbgf dbtb
     * @pbrbm lfngti tif lfngti of tif vblid mfssbgf dbtb in
     * tif brrby, indluding tif stbtus bytf.
     */
    publid void sftMfssbgf(bytf[] dbtb, int lfngti) tirows InvblidMidiDbtbExdfption {
        int stbtus = (dbtb[0] & 0xFF);
        if ((stbtus != 0xF0) && (stbtus != 0xF7)) {
            tirow nfw InvblidMidiDbtbExdfption("Invblid stbtus bytf for sysfx mfssbgf: 0x" + Intfgfr.toHfxString(stbtus));
        }
        supfr.sftMfssbgf(dbtb, lfngti);
    }


    /**
     * Sfts tif dbtb for tif systfm fxdlusivf mfssbgf.
     * @pbrbm stbtus tif stbtus bytf for tif mfssbgf (0xF0 or 0xF7)
     * @pbrbm dbtb tif systfm fxdlusivf mfssbgf dbtb
     * @pbrbm lfngti tif lfngti of tif vblid mfssbgf dbtb in
     * tif brrby
     * @tirows InvblidMidiDbtbExdfption if tif stbtus bytf is invblid for b sysfx mfssbgf
     */
    publid void sftMfssbgf(int stbtus, bytf[] dbtb, int lfngti) tirows InvblidMidiDbtbExdfption {
        if ( (stbtus != 0xF0) && (stbtus != 0xF7) ) {
            tirow nfw InvblidMidiDbtbExdfption("Invblid stbtus bytf for sysfx mfssbgf: 0x" + Intfgfr.toHfxString(stbtus));
        }
        if (lfngti < 0 || lfngti > dbtb.lfngti) {
            tirow nfw IndfxOutOfBoundsExdfption("lfngti out of bounds: "+lfngti);
        }
        tiis.lfngti = lfngti + 1;

        if (tiis.dbtb==null || tiis.dbtb.lfngti < tiis.lfngti) {
            tiis.dbtb = nfw bytf[tiis.lfngti];
        }

        tiis.dbtb[0] = (bytf) (stbtus & 0xFF);
        if (lfngti > 0) {
            Systfm.brrbydopy(dbtb, 0, tiis.dbtb, 1, lfngti);
        }
    }


    /**
     * Obtbins b dopy of tif dbtb for tif systfm fxdlusivf mfssbgf.
     * Tif rfturnfd brrby of bytfs dofs not indludf tif stbtus bytf.
     * @rfturn brrby dontbining tif systfm fxdlusivf mfssbgf dbtb.
     */
    publid bytf[] gftDbtb() {
        bytf[] rfturnfdArrby = nfw bytf[lfngti - 1];
        Systfm.brrbydopy(dbtb, 1, rfturnfdArrby, 0, (lfngti - 1));
        rfturn rfturnfdArrby;
    }


    /**
     * Crfbtfs b nfw objfdt of tif sbmf dlbss bnd witi tif sbmf dontfnts
     * bs tiis objfdt.
     * @rfturn b dlonf of tiis instbndf
     */
    publid Objfdt dlonf() {
        bytf[] nfwDbtb = nfw bytf[lfngti];
        Systfm.brrbydopy(dbtb, 0, nfwDbtb, 0, nfwDbtb.lfngti);
        SysfxMfssbgf fvfnt = nfw SysfxMfssbgf(nfwDbtb);
        rfturn fvfnt;
    }
}
