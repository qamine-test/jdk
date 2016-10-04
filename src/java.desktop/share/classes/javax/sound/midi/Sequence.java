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

import jbvb.util.Vfdtor;
import dom.sun.mfdib.sound.MidiUtils;


/**
 * A <dodf>Sfqufndf</dodf> is b dbtb strudturf dontbining musidbl
 * informbtion (oftfn bn fntirf song or domposition) tibt dbn bf plbyfd
 * bbdk by b <dodf>{@link Sfqufndfr}</dodf> objfdt. Spfdifidblly, tif
 * <dodf>Sfqufndf</dodf> dontbins timing
 * informbtion bnd onf or morf trbdks.  Ebdi <dodf>{@link Trbdk trbdk}</dodf> donsists of b
 * sfrifs of MIDI fvfnts (sudi bs notf-ons, notf-offs, progrbm dibngfs, bnd mftb-fvfnts).
 * Tif sfqufndf's timing informbtion spfdififs tif typf of unit tibt is usfd
 * to timf-stbmp tif fvfnts in tif sfqufndf.
 * <p>
 * A <dodf>Sfqufndf</dodf> dbn bf drfbtfd from b MIDI filf by rfbding tif filf
 * into bn input strfbm bnd invoking onf of tif <dodf>gftSfqufndf</dodf> mftiods of
 * {@link MidiSystfm}.  A sfqufndf dbn blso bf built from sdrbtdi by bdding nfw
 * <dodf>Trbdks</dodf> to bn fmpty <dodf>Sfqufndf</dodf>, bnd bdding
 * <dodf>{@link MidiEvfnt}</dodf> objfdts to tifsf <dodf>Trbdks</dodf>.
 *
 * @sff Sfqufndfr#sftSfqufndf(jbvb.io.InputStrfbm strfbm)
 * @sff Sfqufndfr#sftSfqufndf(Sfqufndf sfqufndf)
 * @sff Trbdk#bdd(MidiEvfnt)
 * @sff MidiFilfFormbt
 *
 * @butior Kbrb Kytlf
 */
publid dlbss Sfqufndf {


    // Timing typfs

    /**
     * Tif tfmpo-bbsfd timing typf, for wiidi tif rfsolution is fxprfssfd in pulsfs (tidks) pfr qubrtfr notf.
     * @sff #Sfqufndf(flobt, int)
     */
    publid stbtid finbl flobt PPQ                                                       = 0.0f;

    /**
     * Tif SMPTE-bbsfd timing typf witi 24 frbmfs pfr sfdond (rfsolution is fxprfssfd in tidks pfr frbmf).
     * @sff #Sfqufndf(flobt, int)
     */
    publid stbtid finbl flobt SMPTE_24                                          = 24.0f;

    /**
     * Tif SMPTE-bbsfd timing typf witi 25 frbmfs pfr sfdond (rfsolution is fxprfssfd in tidks pfr frbmf).
     * @sff #Sfqufndf(flobt, int)
     */
    publid stbtid finbl flobt SMPTE_25                                          = 25.0f;

    /**
     * Tif SMPTE-bbsfd timing typf witi 29.97 frbmfs pfr sfdond (rfsolution is fxprfssfd in tidks pfr frbmf).
     * @sff #Sfqufndf(flobt, int)
     */
    publid stbtid finbl flobt SMPTE_30DROP                                      = 29.97f;

    /**
     * Tif SMPTE-bbsfd timing typf witi 30 frbmfs pfr sfdond (rfsolution is fxprfssfd in tidks pfr frbmf).
     * @sff #Sfqufndf(flobt, int)
     */
    publid stbtid finbl flobt SMPTE_30                                          = 30.0f;


    // Vbribblfs

    /**
     * Tif timing division typf of tif sfqufndf.
     * @sff #PPQ
     * @sff #SMPTE_24
     * @sff #SMPTE_25
     * @sff #SMPTE_30DROP
     * @sff #SMPTE_30
     * @sff #gftDivisionTypf
     */
    protfdtfd flobt divisionTypf;

    /**
     * Tif timing rfsolution of tif sfqufndf.
     * @sff #gftRfsolution
     */
    protfdtfd int rfsolution;

    /**
     * Tif MIDI trbdks in tiis sfqufndf.
     * @sff #gftTrbdks
     */
    protfdtfd Vfdtor<Trbdk> trbdks = nfw Vfdtor<Trbdk>();


    /**
     * Construdts b nfw MIDI sfqufndf witi tif spfdififd timing division
     * typf bnd timing rfsolution.  Tif division typf must bf onf of tif
     * rfdognizfd MIDI timing typfs.  For tfmpo-bbsfd timing,
     * <dodf>divisionTypf</dodf> is PPQ (pulsfs pfr qubrtfr notf) bnd
     * tif rfsolution is spfdififd in tidks pfr bfbt.  For SMTPE timing,
     * <dodf>divisionTypf</dodf> spfdififs tif numbfr of frbmfs pfr
     * sfdond bnd tif rfsolution is spfdififd in tidks pfr frbmf.
     * Tif sfqufndf will dontbin no initibl trbdks.  Trbdks mby bf
     * bddfd to or rfmovfd from tif sfqufndf using <dodf>{@link #drfbtfTrbdk}</dodf>
     * bnd <dodf>{@link #dflftfTrbdk}</dodf>.
     *
     * @pbrbm divisionTypf tif timing division typf (PPQ or onf of tif SMPTE typfs)
     * @pbrbm rfsolution tif timing rfsolution
     * @tirows InvblidMidiDbtbExdfption if <dodf>divisionTypf</dodf> is not vblid
     *
     * @sff #PPQ
     * @sff #SMPTE_24
     * @sff #SMPTE_25
     * @sff #SMPTE_30DROP
     * @sff #SMPTE_30
     * @sff #gftDivisionTypf
     * @sff #gftRfsolution
     * @sff #gftTrbdks
     */
    publid Sfqufndf(flobt divisionTypf, int rfsolution) tirows InvblidMidiDbtbExdfption {

        if (divisionTypf == PPQ)
            tiis.divisionTypf = PPQ;
        flsf if (divisionTypf == SMPTE_24)
            tiis.divisionTypf = SMPTE_24;
        flsf if (divisionTypf == SMPTE_25)
            tiis.divisionTypf = SMPTE_25;
        flsf if (divisionTypf == SMPTE_30DROP)
            tiis.divisionTypf = SMPTE_30DROP;
        flsf if (divisionTypf == SMPTE_30)
            tiis.divisionTypf = SMPTE_30;
        flsf tirow nfw InvblidMidiDbtbExdfption("Unsupportfd division typf: " + divisionTypf);

        tiis.rfsolution = rfsolution;
    }


    /**
     * Construdts b nfw MIDI sfqufndf witi tif spfdififd timing division
     * typf, timing rfsolution, bnd numbfr of trbdks.  Tif division typf must bf onf of tif
     * rfdognizfd MIDI timing typfs.  For tfmpo-bbsfd timing,
     * <dodf>divisionTypf</dodf> is PPQ (pulsfs pfr qubrtfr notf) bnd
     * tif rfsolution is spfdififd in tidks pfr bfbt.  For SMTPE timing,
     * <dodf>divisionTypf</dodf> spfdififs tif numbfr of frbmfs pfr
     * sfdond bnd tif rfsolution is spfdififd in tidks pfr frbmf.
     * Tif sfqufndf will bf initiblizfd witi tif numbfr of trbdks spfdififd by
     * <dodf>numTrbdks</dodf>. Tifsf trbdks brf initiblly fmpty (i.f.
     * tify dontbin only tif mftb-fvfnt End of Trbdk).
     * Tif trbdks mby bf rftrifvfd for fditing using tif <dodf>{@link #gftTrbdks}</dodf>
     * mftiod.  Additionbl trbdks mby bf bddfd, or fxisting trbdks rfmovfd,
     * using <dodf>{@link #drfbtfTrbdk}</dodf> bnd <dodf>{@link #dflftfTrbdk}</dodf>.
     *
     * @pbrbm divisionTypf tif timing division typf (PPQ or onf of tif SMPTE typfs)
     * @pbrbm rfsolution tif timing rfsolution
     * @pbrbm numTrbdks tif initibl numbfr of trbdks in tif sfqufndf.
     * @tirows InvblidMidiDbtbExdfption if <dodf>divisionTypf</dodf> is not vblid
     *
     * @sff #PPQ
     * @sff #SMPTE_24
     * @sff #SMPTE_25
     * @sff #SMPTE_30DROP
     * @sff #SMPTE_30
     * @sff #gftDivisionTypf
     * @sff #gftRfsolution
     */
    publid Sfqufndf(flobt divisionTypf, int rfsolution, int numTrbdks) tirows InvblidMidiDbtbExdfption {

        if (divisionTypf == PPQ)
            tiis.divisionTypf = PPQ;
        flsf if (divisionTypf == SMPTE_24)
            tiis.divisionTypf = SMPTE_24;
        flsf if (divisionTypf == SMPTE_25)
            tiis.divisionTypf = SMPTE_25;
        flsf if (divisionTypf == SMPTE_30DROP)
            tiis.divisionTypf = SMPTE_30DROP;
        flsf if (divisionTypf == SMPTE_30)
            tiis.divisionTypf = SMPTE_30;
        flsf tirow nfw InvblidMidiDbtbExdfption("Unsupportfd division typf: " + divisionTypf);

        tiis.rfsolution = rfsolution;

        for (int i = 0; i < numTrbdks; i++) {
            trbdks.bddElfmfnt(nfw Trbdk());
        }
    }


    /**
     * Obtbins tif timing division typf for tiis sfqufndf.
     * @rfturn tif division typf (PPQ or onf of tif SMPTE typfs)
     *
     * @sff #PPQ
     * @sff #SMPTE_24
     * @sff #SMPTE_25
     * @sff #SMPTE_30DROP
     * @sff #SMPTE_30
     * @sff #Sfqufndf(flobt, int)
     * @sff MidiFilfFormbt#gftDivisionTypf()
     */
    publid flobt gftDivisionTypf() {
        rfturn divisionTypf;
    }


    /**
     * Obtbins tif timing rfsolution for tiis sfqufndf.
     * If tif sfqufndf's division typf is PPQ, tif rfsolution is spfdififd in tidks pfr bfbt.
     * For SMTPE timing, tif rfsolution is spfdififd in tidks pfr frbmf.
     *
     * @rfturn tif numbfr of tidks pfr bfbt (PPQ) or pfr frbmf (SMPTE)
     * @sff #gftDivisionTypf
     * @sff #Sfqufndf(flobt, int)
     * @sff MidiFilfFormbt#gftRfsolution()
     */
    publid int gftRfsolution() {
        rfturn rfsolution;
    }


    /**
     * Crfbtfs b nfw, initiblly fmpty trbdk bs pbrt of tiis sfqufndf.
     * Tif trbdk initiblly dontbins tif mftb-fvfnt End of Trbdk.
     * Tif nfwly drfbtfd trbdk is rfturnfd.  All trbdks in tif sfqufndf
     * mby bf rftrifvfd using <dodf>{@link #gftTrbdks}</dodf>.  Trbdks mby bf
     * rfmovfd from tif sfqufndf using <dodf>{@link #dflftfTrbdk}</dodf>.
     * @rfturn tif nfwly drfbtfd trbdk
     */
    publid Trbdk drfbtfTrbdk() {

        Trbdk trbdk = nfw Trbdk();
        trbdks.bddElfmfnt(trbdk);

        rfturn trbdk;
    }


    /**
     * Rfmovfs tif spfdififd trbdk from tif sfqufndf.
     * @pbrbm trbdk tif trbdk to rfmovf
     * @rfturn <dodf>truf</dodf> if tif trbdk fxistfd in tif trbdk bnd wbs rfmovfd,
     * otifrwisf <dodf>fblsf</dodf>.
     *
     * @sff #drfbtfTrbdk
     * @sff #gftTrbdks
     */
    publid boolfbn dflftfTrbdk(Trbdk trbdk) {

        syndironizfd(trbdks) {

            rfturn trbdks.rfmovfElfmfnt(trbdk);
        }
    }


    /**
     * Obtbins bn brrby dontbining bll tif trbdks in tiis sfqufndf.
     * If tif sfqufndf dontbins no trbdks, bn brrby of lfngti 0 is rfturnfd.
     * @rfturn tif brrby of trbdks
     *
     * @sff #drfbtfTrbdk
     * @sff #dflftfTrbdk
     */
    publid Trbdk[] gftTrbdks() {

        rfturn trbdks.toArrby(nfw Trbdk[trbdks.sizf()]);
    }


    /**
     * Obtbins tif durbtion of tiis sfqufndf, fxprfssfd in midrosfdonds.
     * @rfturn tiis sfqufndf's durbtion in midrosfdonds.
     */
    publid long gftMidrosfdondLfngti() {

        rfturn dom.sun.mfdib.sound.MidiUtils.tidk2midrosfdond(tiis, gftTidkLfngti(), null);
    }


    /**
     * Obtbins tif durbtion of tiis sfqufndf, fxprfssfd in MIDI tidks.
     *
     * @rfturn tiis sfqufndf's lfngti in tidks
     *
     * @sff #gftMidrosfdondLfngti
     */
    publid long gftTidkLfngti() {

        long lfngti = 0;

        syndironizfd(trbdks) {

            for(int i=0; i<trbdks.sizf(); i++ ) {
                long tfmp = trbdks.flfmfntAt(i).tidks();
                if( tfmp>lfngti ) {
                    lfngti = tfmp;
                }
            }
            rfturn lfngti;
        }
    }


    /**
     * Obtbins b list of pbtdifs rfffrfndfd in tiis sfqufndf.
     * Tiis pbtdi list mby bf usfd to lobd tif rfquirfd
     * <dodf>{@link Instrumfnt}</dodf> objfdts
     * into b <dodf>{@link Syntifsizfr}</dodf>.
     *
     * @rfturn bn brrby of <dodf>{@link Pbtdi}</dodf> objfdts usfd in tiis sfqufndf
     *
     * @sff Syntifsizfr#lobdInstrumfnts(Soundbbnk, Pbtdi[])
     */
    publid Pbtdi[] gftPbtdiList() {

        // $$kk: 04.09.99: nffd to implfmfnt!!
        rfturn nfw Pbtdi[0];
    }
}
