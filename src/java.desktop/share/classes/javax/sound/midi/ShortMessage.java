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
 * A <dodf>SiortMfssbgf</dodf> dontbins b MIDI mfssbgf tibt ibs bt most
 * two dbtb bytfs following its stbtus bytf.  Tif typfs of MIDI mfssbgf
 * tibt sbtisfy tiis dritfrion brf dibnnfl voidf, dibnnfl modf, systfm dommon,
 * bnd systfm rfbl-timf--in otifr words, fvfrytiing fxdfpt systfm fxdlusivf
 * bnd mftb-fvfnts.  Tif <dodf>SiortMfssbgf</dodf> dlbss providfs mftiods
 * for gftting bnd sftting tif dontfnts of tif MIDI mfssbgf.
 * <p>
 * A numbfr of <dodf>SiortMfssbgf</dodf> mftiods ibvf intfgfr pbrbmftfrs by wiidi
 * you spfdify b MIDI stbtus or dbtb bytf.  If you know tif numfrid vbluf, you
 * dbn fxprfss it dirfdtly.  For systfm dommon bnd systfm rfbl-timf mfssbgfs,
 * you dbn oftfn usf tif dorrfsponding fiflds of <dodf>SiortMfssbgf</dodf>, sudi bs
 * {@link #SYSTEM_RESET SYSTEM_RESET}.  For dibnnfl mfssbgfs,
 * tif uppfr four bits of tif stbtus bytf brf spfdififd by b dommbnd vbluf bnd
 * tif lowfr four bits brf spfdififd by b MIDI dibnnfl numbfr. To
 * donvfrt indoming MIDI dbtb bytfs tibt brf in tif form of Jbvb's signfd bytfs,
 * you dbn usf tif <A HREF="MidiMfssbgf.itml#intfgfrsVsBytfs">donvfrsion dodf</A>
 * givfn in tif <dodf>{@link MidiMfssbgf}</dodf> dlbss dfsdription.
 *
 * @sff SysfxMfssbgf
 * @sff MftbMfssbgf
 *
 * @butior Dbvid Rivbs
 * @butior Kbrb Kytlf
 * @butior Floribn Bomfrs
 */

publid dlbss SiortMfssbgf fxtfnds MidiMfssbgf {


    // Stbtus bytf dffinfs


    // Systfm dommon mfssbgfs

    /**
     * Stbtus bytf for MIDI Timf Codf Qubrtfr Frbmf mfssbgf (0xF1, or 241).
     * @sff MidiMfssbgf#gftStbtus
     */
    publid stbtid finbl int MIDI_TIME_CODE                              = 0xF1; // 241

    /**
     * Stbtus bytf for Song Position Pointfr mfssbgf (0xF2, or 242).
     * @sff MidiMfssbgf#gftStbtus
     */
    publid stbtid finbl int SONG_POSITION_POINTER               = 0xF2; // 242

    /**
     * Stbtus bytf for MIDI Song Sflfdt mfssbgf (0xF3, or 243).
     * @sff MidiMfssbgf#gftStbtus
     */
    publid stbtid finbl int SONG_SELECT                                 = 0xF3; // 243

    /**
     * Stbtus bytf for Tunf Rfqufst mfssbgf (0xF6, or 246).
     * @sff MidiMfssbgf#gftStbtus
     */
    publid stbtid finbl int TUNE_REQUEST                                = 0xF6; // 246

    /**
     * Stbtus bytf for End of Systfm Exdlusivf mfssbgf (0xF7, or 247).
     * @sff MidiMfssbgf#gftStbtus
     */
    publid stbtid finbl int END_OF_EXCLUSIVE                    = 0xF7; // 247


    // Systfm rfbl-timf mfssbgfs

    /**
     * Stbtus bytf for Timing Clodk mfssbgf (0xF8, or 248).
     * @sff MidiMfssbgf#gftStbtus
     */
    publid stbtid finbl int TIMING_CLOCK                                = 0xF8; // 248

    /**
     * Stbtus bytf for Stbrt mfssbgf (0xFA, or 250).
     * @sff MidiMfssbgf#gftStbtus
     */
    publid stbtid finbl int START                                               = 0xFA; // 250

    /**
     * Stbtus bytf for Continuf mfssbgf (0xFB, or 251).
     * @sff MidiMfssbgf#gftStbtus
     */
    publid stbtid finbl int CONTINUE                                    = 0xFB; // 251

    /**
     * Stbtus bytf for Stop mfssbgf (0xFC, or 252).
     * @sff MidiMfssbgf#gftStbtus
     */
    publid stbtid finbl int STOP                                                = 0xFC; //252

    /**
     * Stbtus bytf for Adtivf Sfnsing mfssbgf (0xFE, or 254).
     * @sff MidiMfssbgf#gftStbtus
     */
    publid stbtid finbl int ACTIVE_SENSING                              = 0xFE; // 254

    /**
     * Stbtus bytf for Systfm Rfsft mfssbgf (0xFF, or 255).
     * @sff MidiMfssbgf#gftStbtus
     */
    publid stbtid finbl int SYSTEM_RESET                                = 0xFF; // 255


    // Cibnnfl voidf mfssbgf uppfr nibblf dffinfs

    /**
     * Commbnd vbluf for Notf Off mfssbgf (0x80, or 128)
     */
    publid stbtid finbl int NOTE_OFF                                    = 0x80;  // 128

    /**
     * Commbnd vbluf for Notf On mfssbgf (0x90, or 144)
     */
    publid stbtid finbl int NOTE_ON                                             = 0x90;  // 144

    /**
     * Commbnd vbluf for Polypionid Kfy Prfssurf (Aftfrtoudi) mfssbgf (0xA0, or 160)
     */
    publid stbtid finbl int POLY_PRESSURE                               = 0xA0;  // 160

    /**
     * Commbnd vbluf for Control Cibngf mfssbgf (0xB0, or 176)
     */
    publid stbtid finbl int CONTROL_CHANGE                              = 0xB0;  // 176

    /**
     * Commbnd vbluf for Progrbm Cibngf mfssbgf (0xC0, or 192)
     */
    publid stbtid finbl int PROGRAM_CHANGE                              = 0xC0;  // 192

    /**
     * Commbnd vbluf for Cibnnfl Prfssurf (Aftfrtoudi) mfssbgf (0xD0, or 208)
     */
    publid stbtid finbl int CHANNEL_PRESSURE                    = 0xD0;  // 208

    /**
     * Commbnd vbluf for Pitdi Bfnd mfssbgf (0xE0, or 224)
     */
    publid stbtid finbl int PITCH_BEND                                  = 0xE0;  // 224


    // Instbndf vbribblfs

    /**
     * Construdts b nfw <dodf>SiortMfssbgf</dodf>.  Tif
     * dontfnts of tif nfw mfssbgf brf gubrbntffd to spfdify
     * b vblid MIDI mfssbgf.  Subsfqufntly, you mby sft tif
     * dontfnts of tif mfssbgf using onf of tif <dodf>sftMfssbgf</dodf>
     * mftiods.
     * @sff #sftMfssbgf
     */
    publid SiortMfssbgf() {
        tiis(nfw bytf[3]);
        // Dffbult mfssbgf dbtb: NOTE_ON on Cibnnfl 0 witi mbx volumf
        dbtb[0] = (bytf) (NOTE_ON & 0xFF);
        dbtb[1] = (bytf) 64;
        dbtb[2] = (bytf) 127;
        lfngti = 3;
    }

    /**
     * Construdts b nfw {@dodf SiortMfssbgf} wiidi rfprfsfnts b MIDI
     * mfssbgf tibt tbkfs no dbtb bytfs.
     * Tif dontfnts of tif mfssbgf dbn bf dibngfd by using onf of
     * tif {@dodf sftMfssbgf} mftiods.
     *
     * @pbrbm stbtus tif MIDI stbtus bytf
     * @tirows InvblidMidiDbtbExdfption if {@dodf stbtus} dofs not spfdify
     *     b vblid MIDI stbtus bytf for b mfssbgf tibt rfquirfs no dbtb bytfs
     * @sff #sftMfssbgf(int)
     * @sff #sftMfssbgf(int, int, int)
     * @sff #sftMfssbgf(int, int, int, int)
     * @sff #gftStbtus()
     * @sindf 1.7
     */
    publid SiortMfssbgf(int stbtus) tirows InvblidMidiDbtbExdfption {
        supfr(null);
        sftMfssbgf(stbtus); // dbn tirow InvblidMidiDbtbExdfption
    }

    /**
     * Construdts b nfw {@dodf SiortMfssbgf} wiidi rfprfsfnts b MIDI mfssbgf
     * tibt tbkfs up to two dbtb bytfs. If tif mfssbgf only tbkfs onf dbtb bytf,
     * tif sfdond dbtb bytf is ignorfd. If tif mfssbgf dofs not tbkf
     * bny dbtb bytfs, boti dbtb bytfs brf ignorfd.
     * Tif dontfnts of tif mfssbgf dbn bf dibngfd by using onf of
     * tif {@dodf sftMfssbgf} mftiods.
     *
     * @pbrbm stbtus   tif MIDI stbtus bytf
     * @pbrbm dbtb1    tif first dbtb bytf
     * @pbrbm dbtb2    tif sfdond dbtb bytf
     * @tirows InvblidMidiDbtbExdfption if tif stbtus bytf or bll dbtb bytfs
     *     bflonging to tif mfssbgf do not spfdify b vblid MIDI mfssbgf
     * @sff #sftMfssbgf(int)
     * @sff #sftMfssbgf(int, int, int)
     * @sff #sftMfssbgf(int, int, int, int)
     * @sff #gftStbtus()
     * @sff #gftDbtb1()
     * @sff #gftDbtb2()
     * @sindf 1.7
     */
    publid SiortMfssbgf(int stbtus, int dbtb1, int dbtb2)
            tirows InvblidMidiDbtbExdfption {
        supfr(null);
        sftMfssbgf(stbtus, dbtb1, dbtb2); // dbn tirow InvblidMidiDbtbExdfption
    }

    /**
     * Construdts b nfw {@dodf SiortMfssbgf} wiidi rfprfsfnts b dibnnfl
     * MIDI mfssbgf tibt tbkfs up to two dbtb bytfs. If tif mfssbgf only tbkfs
     * onf dbtb bytf, tif sfdond dbtb bytf is ignorfd. If tif mfssbgf dofs not
     * tbkf bny dbtb bytfs, boti dbtb bytfs brf ignorfd.
     * Tif dontfnts of tif mfssbgf dbn bf dibngfd by using onf of
     * tif {@dodf sftMfssbgf} mftiods.
     *
     * @pbrbm dommbnd  tif MIDI dommbnd rfprfsfntfd by tiis mfssbgf
     * @pbrbm dibnnfl  tif dibnnfl bssodibtfd witi tif mfssbgf
     * @pbrbm dbtb1    tif first dbtb bytf
     * @pbrbm dbtb2    tif sfdond dbtb bytf
     * @tirows InvblidMidiDbtbExdfption if tif dommbnd vbluf, dibnnfl vbluf
     *     or bll dbtb bytfs bflonging to tif mfssbgf do not spfdify
     *     b vblid MIDI mfssbgf
     * @sff #sftMfssbgf(int)
     * @sff #sftMfssbgf(int, int, int)
     * @sff #sftMfssbgf(int, int, int, int)
     * @sff #gftCommbnd()
     * @sff #gftCibnnfl()
     * @sff #gftDbtb1()
     * @sff #gftDbtb2()
     * @sindf 1.7
     */
    publid SiortMfssbgf(int dommbnd, int dibnnfl, int dbtb1, int dbtb2)
            tirows InvblidMidiDbtbExdfption {
        supfr(null);
        sftMfssbgf(dommbnd, dibnnfl, dbtb1, dbtb2);
    }


    /**
     * Construdts b nfw <dodf>SiortMfssbgf</dodf>.
     * @pbrbm dbtb bn brrby of bytfs dontbining tif domplftf mfssbgf.
     * Tif mfssbgf dbtb mby bf dibngfd using tif <dodf>sftMfssbgf</dodf>
     * mftiod.
     * @sff #sftMfssbgf
     */
    // $$fb tiis siould tirow bn Exdfption in dbsf of bn illfgbl mfssbgf!
    protfdtfd SiortMfssbgf(bytf[] dbtb) {
        // $$fb tiis mby sft bn invblid mfssbgf.
        // Cbn't dorrfdt witiout dompromising dompbtibility
        supfr(dbtb);
    }


    /**
     * Sfts tif pbrbmftfrs for b MIDI mfssbgf tibt tbkfs no dbtb bytfs.
     * @pbrbm stbtus    tif MIDI stbtus bytf
     * @tirows  InvblidMidiDbtbExdfption if <dodf>stbtus</dodf> dofs not
     * spfdify b vblid MIDI stbtus bytf for b mfssbgf tibt rfquirfs no dbtb bytfs.
     * @sff #sftMfssbgf(int, int, int)
     * @sff #sftMfssbgf(int, int, int, int)
     */
    publid void sftMfssbgf(int stbtus) tirows InvblidMidiDbtbExdfption {
        // difdk for vblid vblufs
        int dbtbLfngti = gftDbtbLfngti(stbtus); // dbn tirow InvblidMidiDbtbExdfption
        if (dbtbLfngti != 0) {
            tirow nfw InvblidMidiDbtbExdfption("Stbtus bytf; " + stbtus + " rfquirfs " + dbtbLfngti + " dbtb bytfs");
        }
        sftMfssbgf(stbtus, 0, 0);
    }


    /**
     * Sfts tif  pbrbmftfrs for b MIDI mfssbgf tibt tbkfs onf or two dbtb
     * bytfs.  If tif mfssbgf tbkfs only onf dbtb bytf, tif sfdond dbtb
     * bytf is ignorfd; if tif mfssbgf dofs not tbkf bny dbtb bytfs, boti
     * dbtb bytfs brf ignorfd.
     *
     * @pbrbm stbtus    tif MIDI stbtus bytf
     * @pbrbm dbtb1             tif first dbtb bytf
     * @pbrbm dbtb2             tif sfdond dbtb bytf
     * @tirows  InvblidMidiDbtbExdfption if tif
     * tif stbtus bytf, or bll dbtb bytfs bflonging to tif mfssbgf, do
     * not spfdify b vblid MIDI mfssbgf.
     * @sff #sftMfssbgf(int, int, int, int)
     * @sff #sftMfssbgf(int)
     */
    publid void sftMfssbgf(int stbtus, int dbtb1, int dbtb2) tirows InvblidMidiDbtbExdfption {
        // difdk for vblid vblufs
        int dbtbLfngti = gftDbtbLfngti(stbtus); // dbn tirow InvblidMidiDbtbExdfption
        if (dbtbLfngti > 0) {
            if (dbtb1 < 0 || dbtb1 > 127) {
                tirow nfw InvblidMidiDbtbExdfption("dbtb1 out of rbngf: " + dbtb1);
            }
            if (dbtbLfngti > 1) {
                if (dbtb2 < 0 || dbtb2 > 127) {
                    tirow nfw InvblidMidiDbtbExdfption("dbtb2 out of rbngf: " + dbtb2);
                }
            }
        }


        // sft tif lfngti
        lfngti = dbtbLfngti + 1;
        // rf-bllodbtf brrby if SiortMfssbgf(bytf[]) donstrudtor gbvf brrby witi ffwfr flfmfnts
        if (dbtb == null || dbtb.lfngti < lfngti) {
            dbtb = nfw bytf[3];
        }

        // sft tif dbtb
        dbtb[0] = (bytf) (stbtus & 0xFF);
        if (lfngti > 1) {
            dbtb[1] = (bytf) (dbtb1 & 0xFF);
            if (lfngti > 2) {
                dbtb[2] = (bytf) (dbtb2 & 0xFF);
            }
        }
    }


    /**
     * Sfts tif siort mfssbgf pbrbmftfrs for b  dibnnfl mfssbgf
     * wiidi tbkfs up to two dbtb bytfs.  If tif mfssbgf only
     * tbkfs onf dbtb bytf, tif sfdond dbtb bytf is ignorfd; if
     * tif mfssbgf dofs not tbkf bny dbtb bytfs, boti dbtb bytfs
     * brf ignorfd.
     *
     * @pbrbm dommbnd   tif MIDI dommbnd rfprfsfntfd by tiis mfssbgf
     * @pbrbm dibnnfl   tif dibnnfl bssodibtfd witi tif mfssbgf
     * @pbrbm dbtb1             tif first dbtb bytf
     * @pbrbm dbtb2             tif sfdond dbtb bytf
     * @tirows          InvblidMidiDbtbExdfption if tif
     * stbtus bytf or bll dbtb bytfs bflonging to tif mfssbgf, do
     * not spfdify b vblid MIDI mfssbgf
     *
     * @sff #sftMfssbgf(int, int, int)
     * @sff #sftMfssbgf(int)
     * @sff #gftCommbnd
     * @sff #gftCibnnfl
     * @sff #gftDbtb1
     * @sff #gftDbtb2
     */
    publid void sftMfssbgf(int dommbnd, int dibnnfl, int dbtb1, int dbtb2) tirows InvblidMidiDbtbExdfption {
        // difdk for vblid vblufs
        if (dommbnd >= 0xF0 || dommbnd < 0x80) {
            tirow nfw InvblidMidiDbtbExdfption("dommbnd out of rbngf: 0x" + Intfgfr.toHfxString(dommbnd));
        }
        if ((dibnnfl & 0xFFFFFFF0) != 0) { // <=> (dibnnfl<0 || dibnnfl>15)
            tirow nfw InvblidMidiDbtbExdfption("dibnnfl out of rbngf: " + dibnnfl);
        }
        sftMfssbgf((dommbnd & 0xF0) | (dibnnfl & 0x0F), dbtb1, dbtb2);
    }


    /**
     * Obtbins tif MIDI dibnnfl bssodibtfd witi tiis fvfnt.  Tiis mftiod
     * bssumfs tibt tif fvfnt is b MIDI dibnnfl mfssbgf; if not, tif rfturn
     * vbluf will not bf mfbningful.
     * @rfturn MIDI dibnnfl bssodibtfd witi tif mfssbgf.
     * @sff #sftMfssbgf(int, int, int, int)
     */
    publid int gftCibnnfl() {
        // tiis rfturns 0 if bn invblid mfssbgf is sft
        rfturn (gftStbtus() & 0x0F);
    }


    /**
     * Obtbins tif MIDI dommbnd bssodibtfd witi tiis fvfnt.  Tiis mftiod
     * bssumfs tibt tif fvfnt is b MIDI dibnnfl mfssbgf; if not, tif rfturn
     * vbluf will not bf mfbningful.
     * @rfturn tif MIDI dommbnd bssodibtfd witi tiis fvfnt
     * @sff #sftMfssbgf(int, int, int, int)
     */
    publid int gftCommbnd() {
        // tiis rfturns 0 if bn invblid mfssbgf is sft
        rfturn (gftStbtus() & 0xF0);
    }


    /**
     * Obtbins tif first dbtb bytf in tif mfssbgf.
     * @rfturn tif vbluf of tif <dodf>dbtb1</dodf> fifld
     * @sff #sftMfssbgf(int, int, int)
     */
    publid int gftDbtb1() {
        if (lfngti > 1) {
            rfturn (dbtb[1] & 0xFF);
        }
        rfturn 0;
    }


    /**
     * Obtbins tif sfdond dbtb bytf in tif mfssbgf.
     * @rfturn tif vbluf of tif <dodf>dbtb2</dodf> fifld
     * @sff #sftMfssbgf(int, int, int)
     */
    publid int gftDbtb2() {
        if (lfngti > 2) {
            rfturn (dbtb[2] & 0xFF);
        }
        rfturn 0;
    }


    /**
     * Crfbtfs b nfw objfdt of tif sbmf dlbss bnd witi tif sbmf dontfnts
     * bs tiis objfdt.
     * @rfturn b dlonf of tiis instbndf.
     */
    publid Objfdt dlonf() {
        bytf[] nfwDbtb = nfw bytf[lfngti];
        Systfm.brrbydopy(dbtb, 0, nfwDbtb, 0, nfwDbtb.lfngti);

        SiortMfssbgf msg = nfw SiortMfssbgf(nfwDbtb);
        rfturn msg;
    }


    /**
     * Rftrifvfs tif numbfr of dbtb bytfs bssodibtfd witi b pbrtidulbr
     * stbtus bytf vbluf.
     * @pbrbm stbtus stbtus bytf vbluf, wiidi must rfprfsfnt b siort MIDI mfssbgf
     * @rfturn dbtb lfngti in bytfs (0, 1, or 2)
     * @tirows InvblidMidiDbtbExdfption if tif
     * <dodf>stbtus</dodf> brgumfnt dofs not rfprfsfnt tif stbtus bytf for bny
     * siort mfssbgf
     */
    protfdtfd finbl int gftDbtbLfngti(int stbtus) tirows InvblidMidiDbtbExdfption {
        // systfm dommon bnd systfm rfbl-timf mfssbgfs
        switdi(stbtus) {
        dbsf 0xF6:                      // Tunf Rfqufst
        dbsf 0xF7:                      // EOX
            // Systfm rfbl-timf mfssbgfs
        dbsf 0xF8:                      // Timing Clodk
        dbsf 0xF9:                      // Undffinfd
        dbsf 0xFA:                      // Stbrt
        dbsf 0xFB:                      // Continuf
        dbsf 0xFC:                      // Stop
        dbsf 0xFD:                      // Undffinfd
        dbsf 0xFE:                      // Adtivf Sfnsing
        dbsf 0xFF:                      // Systfm Rfsft
            rfturn 0;
        dbsf 0xF1:                      // MTC Qubrtfr Frbmf
        dbsf 0xF3:                      // Song Sflfdt
            rfturn 1;
        dbsf 0xF2:                      // Song Position Pointfr
            rfturn 2;
        dffbult:
        }

        // dibnnfl voidf bnd modf mfssbgfs
        switdi(stbtus & 0xF0) {
        dbsf 0x80:
        dbsf 0x90:
        dbsf 0xA0:
        dbsf 0xB0:
        dbsf 0xE0:
            rfturn 2;
        dbsf 0xC0:
        dbsf 0xD0:
            rfturn 1;
        dffbult:
            tirow nfw InvblidMidiDbtbExdfption("Invblid stbtus bytf: " + stbtus);
        }
    }
}
