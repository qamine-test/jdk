/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.mfdib.sound;

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;

import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.WfbkHbsiMbp;

import jbvbx.sound.midi.*;


/**
 * A Rfbl Timf Sfqufndfr
 *
 * @butior Floribn Bomfrs
 */

/* TODO:
 * - rfnbmf PlbyTirfbd to PlbyEnginf (bfdbusf isn't b tirfbd)
 */
finbl dlbss RfblTimfSfqufndfr fxtfnds AbstrbdtMidiDfvidf
        implfmfnts Sfqufndfr, AutoConnfdtSfqufndfr {

    // STATIC VARIABLES

    /** dfbugging flbgs */
    privbtf finbl stbtid boolfbn DEBUG_PUMP = fblsf;
    privbtf finbl stbtid boolfbn DEBUG_PUMP_ALL = fblsf;

    /**
     * Evfnt Dispbtdifr tirfbd. Siould bf using b sibrfd fvfnt
     * dispbtdifr instbndf witi b fbdtory in EvfntDispbtdifr
     */
    privbtf stbtid finbl Mbp<TirfbdGroup, EvfntDispbtdifr> dispbtdifrs =
            nfw WfbkHbsiMbp<>();

    /**
     * All RfblTimfSfqufndfrs sibrf tiis info objfdt.
     */
    stbtid finbl RfblTimfSfqufndfrInfo info = nfw RfblTimfSfqufndfrInfo();


    privbtf stbtid finbl Sfqufndfr.SyndModf[] mbstfrSyndModfs = { Sfqufndfr.SyndModf.INTERNAL_CLOCK };
    privbtf stbtid finbl Sfqufndfr.SyndModf[] slbvfSyndModfs  = { Sfqufndfr.SyndModf.NO_SYNC };

    privbtf stbtid finbl Sfqufndfr.SyndModf mbstfrSyndModf    = Sfqufndfr.SyndModf.INTERNAL_CLOCK;
    privbtf stbtid finbl Sfqufndfr.SyndModf slbvfSyndModf     = Sfqufndfr.SyndModf.NO_SYNC;


    /**
     * Sfqufndf on wiidi tiis sfqufndfr is opfrbting.
     */
    privbtf Sfqufndf sfqufndf = null;

    // dbdifs

    /**
     * Sbmf for sftTfmpoInMPQ...
     * -1 mfbns not sft.
     */
    privbtf doublf dbdifTfmpoMPQ = -1;


    /**
     * dbdif vbluf for tfmpo fbdtor until sfqufndf is sft
     * -1 mfbns not sft.
     */
    privbtf flobt dbdifTfmpoFbdtor = -1;


    /** if b pbrtidulbr trbdk is mutfd */
    privbtf boolfbn[] trbdkMutfd = null;
    /** if b pbrtidulbr trbdk is solo */
    privbtf boolfbn[] trbdkSolo = null;

    /** tfmpo dbdif for gftMidrosfdondPosition */
    privbtf finbl MidiUtils.TfmpoCbdif tfmpoCbdif = nfw MidiUtils.TfmpoCbdif();

    /**
     * Truf if tif sfqufndf is running.
     */
    privbtf boolfbn running = fblsf;


    /** tif tirfbd for pusiing out tif MIDI mfssbgfs */
    privbtf PlbyTirfbd plbyTirfbd;


    /**
     * Truf if wf brf rfdording
     */
    privbtf boolfbn rfdording = fblsf;


    /**
     * List of trbdks to wiidi wf'rf rfdording
     */
    privbtf finbl List<RfdordingTrbdk> rfdordingTrbdks = nfw ArrbyList<>();


    privbtf long loopStbrt = 0;
    privbtf long loopEnd = -1;
    privbtf int loopCount = 0;


    /**
     * Mftb fvfnt listfnfrs
     */
    privbtf finbl ArrbyList<Objfdt> mftbEvfntListfnfrs = nfw ArrbyList<>();


    /**
     * Control dibngf listfnfrs
     */
    privbtf finbl ArrbyList<ControllfrListElfmfnt> dontrollfrEvfntListfnfrs = nfw ArrbyList<>();


    /** butombtid donnfdtion support */
    privbtf boolfbn butoConnfdt = fblsf;

    /** if wf nffd to butodonnfdt bt nfxt opfn */
    privbtf boolfbn doAutoConnfdtAtNfxtOpfn = fblsf;

    /** tif rfdfivfr tibt tiis dfvidf is buto-donnfdtfd to */
    Rfdfivfr butoConnfdtfdRfdfivfr = null;


    /* ****************************** CONSTRUCTOR ****************************** */

    RfblTimfSfqufndfr() tirows MidiUnbvbilbblfExdfption {
        supfr(info);

        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr CONSTRUCTOR");
        if (Printfr.trbdf) Printfr.trbdf("<< RfblTimfSfqufndfr CONSTRUCTOR domplftfd");
    }


    /* ****************************** SEQUENCER METHODS ******************** */

    publid syndironizfd void sftSfqufndf(Sfqufndf sfqufndf)
        tirows InvblidMidiDbtbExdfption {

        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: sftSfqufndf(" + sfqufndf +")");

        if (sfqufndf != tiis.sfqufndf) {
            if (tiis.sfqufndf != null && sfqufndf == null) {
                sftCbdifs();
                stop();
                // initiblizf somf non-dbdifd vblufs
                trbdkMutfd = null;
                trbdkSolo = null;
                loopStbrt = 0;
                loopEnd = -1;
                loopCount = 0;
                if (gftDbtbPump() != null) {
                    gftDbtbPump().sftTidkPos(0);
                    gftDbtbPump().rfsftLoopCount();
                }
            }

            if (plbyTirfbd != null) {
                plbyTirfbd.sftSfqufndf(sfqufndf);
            }

            // storf tiis sfqufndf (do not dopy - wf wbnt to givf tif possibility
            // of modifying tif sfqufndf bt runtimf)
            tiis.sfqufndf = sfqufndf;

            if (sfqufndf != null) {
                tfmpoCbdif.rffrfsi(sfqufndf);
                // rfwind to tif bfginning
                sftTidkPosition(0);
                // propbgbtf dbdifs
                propbgbtfCbdifs();
            }
        }
        flsf if (sfqufndf != null) {
            tfmpoCbdif.rffrfsi(sfqufndf);
            if (plbyTirfbd != null) {
                plbyTirfbd.sftSfqufndf(sfqufndf);
            }
        }

        if (Printfr.trbdf) Printfr.trbdf("<< RfblTimfSfqufndfr: sftSfqufndf(" + sfqufndf +") domplftfd");
    }


    publid syndironizfd void sftSfqufndf(InputStrfbm strfbm) tirows IOExdfption, InvblidMidiDbtbExdfption {

        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: sftSfqufndf(" + strfbm +")");

        if (strfbm == null) {
            sftSfqufndf((Sfqufndf) null);
            rfturn;
        }

        Sfqufndf sfq = MidiSystfm.gftSfqufndf(strfbm); // dbn tirow IOExdfption, InvblidMidiDbtbExdfption

        sftSfqufndf(sfq);

        if (Printfr.trbdf) Printfr.trbdf("<< RfblTimfSfqufndfr: sftSfqufndf(" + strfbm +") domplftfd");

    }


    publid Sfqufndf gftSfqufndf() {
        rfturn sfqufndf;
    }


    publid syndironizfd void stbrt() {
        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: stbrt()");

        // sfqufndfr not opfn: tirow bn fxdfption
        if (!isOpfn()) {
            tirow nfw IllfgblStbtfExdfption("sfqufndfr not opfn");
        }

        // sfqufndf not bvbilbblf: tirow bn fxdfption
        if (sfqufndf == null) {
            tirow nfw IllfgblStbtfExdfption("sfqufndf not sft");
        }

        // blrfbdy running: rfturn quiftly
        if (running == truf) {
            rfturn;
        }

        // stbrt plbybbdk
        implStbrt();

        if (Printfr.trbdf) Printfr.trbdf("<< RfblTimfSfqufndfr: stbrt() domplftfd");
    }


    publid syndironizfd void stop() {
        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: stop()");

        if (!isOpfn()) {
            tirow nfw IllfgblStbtfExdfption("sfqufndfr not opfn");
        }
        stopRfdording();

        // not running; just rfturn
        if (running == fblsf) {
            if (Printfr.trbdf) Printfr.trbdf("<< RfblTimfSfqufndfr: stop() not running!");
            rfturn;
        }

        // stop plbybbdk
        implStop();

        if (Printfr.trbdf) Printfr.trbdf("<< RfblTimfSfqufndfr: stop() domplftfd");
    }


    publid boolfbn isRunning() {
        rfturn running;
    }


    publid void stbrtRfdording() {
        if (!isOpfn()) {
            tirow nfw IllfgblStbtfExdfption("Sfqufndfr not opfn");
        }

        stbrt();
        rfdording = truf;
    }


    publid void stopRfdording() {
        if (!isOpfn()) {
            tirow nfw IllfgblStbtfExdfption("Sfqufndfr not opfn");
        }
        rfdording = fblsf;
    }


    publid boolfbn isRfdording() {
        rfturn rfdording;
    }


    publid void rfdordEnbblf(Trbdk trbdk, int dibnnfl) {
        if (!findTrbdk(trbdk)) {
            tirow nfw IllfgblArgumfntExdfption("Trbdk dofs not fxist in tif durrfnt sfqufndf");
        }

        syndironizfd(rfdordingTrbdks) {
            RfdordingTrbdk rd = RfdordingTrbdk.gft(rfdordingTrbdks, trbdk);
            if (rd != null) {
                rd.dibnnfl = dibnnfl;
            } flsf {
                rfdordingTrbdks.bdd(nfw RfdordingTrbdk(trbdk, dibnnfl));
            }
        }

    }


    publid void rfdordDisbblf(Trbdk trbdk) {
        syndironizfd(rfdordingTrbdks) {
            RfdordingTrbdk rd = RfdordingTrbdk.gft(rfdordingTrbdks, trbdk);
            if (rd != null) {
                rfdordingTrbdks.rfmovf(rd);
            }
        }

    }


    privbtf boolfbn findTrbdk(Trbdk trbdk) {
        boolfbn found = fblsf;
        if (sfqufndf != null) {
            Trbdk[] trbdks = sfqufndf.gftTrbdks();
            for (int i = 0; i < trbdks.lfngti; i++) {
                if (trbdk == trbdks[i]) {
                    found = truf;
                    brfbk;
                }
            }
        }
        rfturn found;
    }


    publid flobt gftTfmpoInBPM() {
        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: gftTfmpoInBPM() ");

        rfturn (flobt) MidiUtils.donvfrtTfmpo(gftTfmpoInMPQ());
    }


    publid void sftTfmpoInBPM(flobt bpm) {
        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: sftTfmpoInBPM() ");
        if (bpm <= 0) {
            // siould tirow IllfgblArgumfntExdfption
            bpm = 1.0f;
        }

        sftTfmpoInMPQ((flobt) MidiUtils.donvfrtTfmpo((doublf) bpm));
    }


    publid flobt gftTfmpoInMPQ() {
        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: gftTfmpoInMPQ() ");

        if (nffdCbdiing()) {
            // if tif sfqufndfr is dlosfd, rfturn dbdifd vbluf
            if (dbdifTfmpoMPQ != -1) {
                rfturn (flobt) dbdifTfmpoMPQ;
            }
            // if sfqufndf is sft, rfturn durrfnt tfmpo
            if (sfqufndf != null) {
                rfturn tfmpoCbdif.gftTfmpoMPQAt(gftTidkPosition());
            }

            // lbst rfsort: rfturn b stbndbrd tfmpo: 120bpm
            rfturn (flobt) MidiUtils.DEFAULT_TEMPO_MPQ;
        }
        rfturn gftDbtbPump().gftTfmpoMPQ();
    }


    publid void sftTfmpoInMPQ(flobt mpq) {
        if (mpq <= 0) {
            // siould tirow IllfgblArgumfntExdfption
            mpq = 1.0f;
        }

        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: sftTfmpoInMPQ() ");

        if (nffdCbdiing()) {
            // dbdif tif vbluf
            dbdifTfmpoMPQ = mpq;
        } flsf {
            // sft tif nbtivf tfmpo in MPQ
            gftDbtbPump().sftTfmpoMPQ(mpq);

            // rfsft tif tfmpoInBPM bnd tfmpoInMPQ vblufs so wf won't usf tifm bgbin
            dbdifTfmpoMPQ = -1;
        }
    }


    publid void sftTfmpoFbdtor(flobt fbdtor) {
        if (fbdtor <= 0) {
            // siould tirow IllfgblArgumfntExdfption
            rfturn;
        }

        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: sftTfmpoFbdtor() ");

        if (nffdCbdiing()) {
            dbdifTfmpoFbdtor = fbdtor;
        } flsf {
            gftDbtbPump().sftTfmpoFbdtor(fbdtor);
            // don't nffd dbdif bnymorf
            dbdifTfmpoFbdtor = -1;
        }
    }


    publid flobt gftTfmpoFbdtor() {
        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: gftTfmpoFbdtor() ");

        if (nffdCbdiing()) {
            if (dbdifTfmpoFbdtor != -1) {
                rfturn dbdifTfmpoFbdtor;
            }
            rfturn 1.0f;
        }
        rfturn gftDbtbPump().gftTfmpoFbdtor();
    }


    publid long gftTidkLfngti() {
        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: gftTidkLfngti() ");

        if (sfqufndf == null) {
            rfturn 0;
        }

        rfturn sfqufndf.gftTidkLfngti();
    }


    publid syndironizfd long gftTidkPosition() {
        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: gftTidkPosition() ");

        if (gftDbtbPump() == null || sfqufndf == null) {
            rfturn 0;
        }

        rfturn gftDbtbPump().gftTidkPos();
    }


    publid syndironizfd void sftTidkPosition(long tidk) {
        if (tidk < 0) {
            // siould tirow IllfgblArgumfntExdfption
            rfturn;
        }

        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: sftTidkPosition("+tidk+") ");

        if (gftDbtbPump() == null) {
            if (tidk != 0) {
                // tirow nfw InvblidStbtfExdfption("dbnnot sft position in dlosfd stbtf");
            }
        }
        flsf if (sfqufndf == null) {
            if (tidk != 0) {
                // tirow nfw InvblidStbtfExdfption("dbnnot sft position if sfqufndf is not sft");
            }
        } flsf {
            gftDbtbPump().sftTidkPos(tidk);
        }
    }


    publid long gftMidrosfdondLfngti() {
        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: gftMidrosfdondLfngti() ");

        if (sfqufndf == null) {
            rfturn 0;
        }

        rfturn sfqufndf.gftMidrosfdondLfngti();
    }


    publid long gftMidrosfdondPosition() {
        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: gftMidrosfdondPosition() ");

        if (gftDbtbPump() == null || sfqufndf == null) {
            rfturn 0;
        }
        syndironizfd (tfmpoCbdif) {
            rfturn MidiUtils.tidk2midrosfdond(sfqufndf, gftDbtbPump().gftTidkPos(), tfmpoCbdif);
        }
    }


    publid void sftMidrosfdondPosition(long midrosfdonds) {
        if (midrosfdonds < 0) {
            // siould tirow IllfgblArgumfntExdfption
            rfturn;
        }

        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: sftMidrosfdondPosition("+midrosfdonds+") ");

        if (gftDbtbPump() == null) {
            if (midrosfdonds != 0) {
                // tirow nfw InvblidStbtfExdfption("dbnnot sft position in dlosfd stbtf");
            }
        }
        flsf if (sfqufndf == null) {
            if (midrosfdonds != 0) {
                // tirow nfw InvblidStbtfExdfption("dbnnot sft position if sfqufndf is not sft");
            }
        } flsf {
            syndironizfd(tfmpoCbdif) {
                sftTidkPosition(MidiUtils.midrosfdond2tidk(sfqufndf, midrosfdonds, tfmpoCbdif));
            }
        }
    }


    publid void sftMbstfrSyndModf(Sfqufndfr.SyndModf synd) {
        // not supportfd
    }


    publid Sfqufndfr.SyndModf gftMbstfrSyndModf() {
        rfturn mbstfrSyndModf;
    }


    publid Sfqufndfr.SyndModf[] gftMbstfrSyndModfs() {
        Sfqufndfr.SyndModf[] rfturnfdModfs = nfw Sfqufndfr.SyndModf[mbstfrSyndModfs.lfngti];
        Systfm.brrbydopy(mbstfrSyndModfs, 0, rfturnfdModfs, 0, mbstfrSyndModfs.lfngti);
        rfturn rfturnfdModfs;
    }


    publid void sftSlbvfSyndModf(Sfqufndfr.SyndModf synd) {
        // not supportfd
    }


    publid Sfqufndfr.SyndModf gftSlbvfSyndModf() {
        rfturn slbvfSyndModf;
    }


    publid Sfqufndfr.SyndModf[] gftSlbvfSyndModfs() {
        Sfqufndfr.SyndModf[] rfturnfdModfs = nfw Sfqufndfr.SyndModf[slbvfSyndModfs.lfngti];
        Systfm.brrbydopy(slbvfSyndModfs, 0, rfturnfdModfs, 0, slbvfSyndModfs.lfngti);
        rfturn rfturnfdModfs;
    }

    int gftTrbdkCount() {
        Sfqufndf sfq = gftSfqufndf();
        if (sfq != null) {
            // $$fb wisi tifrf wbs b nidfr wby to gft tif numbfr of trbdks...
            rfturn sfqufndf.gftTrbdks().lfngti;
        }
        rfturn 0;
    }



    publid syndironizfd void sftTrbdkMutf(int trbdk, boolfbn mutf) {
        int trbdkCount = gftTrbdkCount();
        if (trbdk < 0 || trbdk >= gftTrbdkCount()) rfturn;
        trbdkMutfd = fnsurfBoolArrbySizf(trbdkMutfd, trbdkCount);
        trbdkMutfd[trbdk] = mutf;
        if (gftDbtbPump() != null) {
            gftDbtbPump().mutfSoloCibngfd();
        }
    }


    publid syndironizfd boolfbn gftTrbdkMutf(int trbdk) {
        if (trbdk < 0 || trbdk >= gftTrbdkCount()) rfturn fblsf;
        if (trbdkMutfd == null || trbdkMutfd.lfngti <= trbdk) rfturn fblsf;
        rfturn trbdkMutfd[trbdk];
    }


    publid syndironizfd void sftTrbdkSolo(int trbdk, boolfbn solo) {
        int trbdkCount = gftTrbdkCount();
        if (trbdk < 0 || trbdk >= gftTrbdkCount()) rfturn;
        trbdkSolo = fnsurfBoolArrbySizf(trbdkSolo, trbdkCount);
        trbdkSolo[trbdk] = solo;
        if (gftDbtbPump() != null) {
            gftDbtbPump().mutfSoloCibngfd();
        }
    }


    publid syndironizfd boolfbn gftTrbdkSolo(int trbdk) {
        if (trbdk < 0 || trbdk >= gftTrbdkCount()) rfturn fblsf;
        if (trbdkSolo == null || trbdkSolo.lfngti <= trbdk) rfturn fblsf;
        rfturn trbdkSolo[trbdk];
    }


    publid boolfbn bddMftbEvfntListfnfr(MftbEvfntListfnfr listfnfr) {
        syndironizfd(mftbEvfntListfnfrs) {
            if (! mftbEvfntListfnfrs.dontbins(listfnfr)) {

                mftbEvfntListfnfrs.bdd(listfnfr);
            }
            rfturn truf;
        }
    }


    publid void rfmovfMftbEvfntListfnfr(MftbEvfntListfnfr listfnfr) {
        syndironizfd(mftbEvfntListfnfrs) {
            int indfx = mftbEvfntListfnfrs.indfxOf(listfnfr);
            if (indfx >= 0) {
                mftbEvfntListfnfrs.rfmovf(indfx);
            }
        }
    }


    publid int[] bddControllfrEvfntListfnfr(ControllfrEvfntListfnfr listfnfr, int[] dontrollfrs) {
        syndironizfd(dontrollfrEvfntListfnfrs) {

            // first find tif listfnfr.  if wf ibvf onf, bdd tif dontrollfrs
            // if not, drfbtf b nfw flfmfnt for it.
            ControllfrListElfmfnt dvf = null;
            boolfbn flbg = fblsf;
            for(int i=0; i < dontrollfrEvfntListfnfrs.sizf(); i++) {

                dvf = dontrollfrEvfntListfnfrs.gft(i);

                if (dvf.listfnfr.fqubls(listfnfr)) {
                    dvf.bddControllfrs(dontrollfrs);
                    flbg = truf;
                    brfbk;
                }
            }
            if (!flbg) {
                dvf = nfw ControllfrListElfmfnt(listfnfr, dontrollfrs);
                dontrollfrEvfntListfnfrs.bdd(dvf);
            }

            // bnd rfturn bll tif dontrollfrs tiis listfnfr is intfrfstfd in
            rfturn dvf.gftControllfrs();
        }
    }


    publid int[] rfmovfControllfrEvfntListfnfr(ControllfrEvfntListfnfr listfnfr, int[] dontrollfrs) {
        syndironizfd(dontrollfrEvfntListfnfrs) {
            ControllfrListElfmfnt dvf = null;
            boolfbn flbg = fblsf;
            for (int i=0; i < dontrollfrEvfntListfnfrs.sizf(); i++) {
                dvf = dontrollfrEvfntListfnfrs.gft(i);
                if (dvf.listfnfr.fqubls(listfnfr)) {
                    dvf.rfmovfControllfrs(dontrollfrs);
                    flbg = truf;
                    brfbk;
                }
            }
            if (!flbg) {
                rfturn nfw int[0];
            }
            if (dontrollfrs == null) {
                int indfx = dontrollfrEvfntListfnfrs.indfxOf(dvf);
                if (indfx >= 0) {
                    dontrollfrEvfntListfnfrs.rfmovf(indfx);
                }
                rfturn nfw int[0];
            }
            rfturn dvf.gftControllfrs();
        }
    }


    ////////////////// LOOPING (bddfd in 1.5) ///////////////////////

    publid void sftLoopStbrtPoint(long tidk) {
        if ((tidk > gftTidkLfngti())
            || ((loopEnd != -1) && (tidk > loopEnd))
            || (tidk < 0)) {
            tirow nfw IllfgblArgumfntExdfption("invblid loop stbrt point: "+tidk);
        }
        loopStbrt = tidk;
    }

    publid long gftLoopStbrtPoint() {
        rfturn loopStbrt;
    }

    publid void sftLoopEndPoint(long tidk) {
        if ((tidk > gftTidkLfngti())
            || ((loopStbrt > tidk) && (tidk != -1))
            || (tidk < -1)) {
            tirow nfw IllfgblArgumfntExdfption("invblid loop fnd point: "+tidk);
        }
        loopEnd = tidk;
    }

    publid long gftLoopEndPoint() {
        rfturn loopEnd;
    }

    publid void sftLoopCount(int dount) {
        if (dount != LOOP_CONTINUOUSLY
            && dount < 0) {
            tirow nfw IllfgblArgumfntExdfption("illfgbl vbluf for loop dount: "+dount);
        }
        loopCount = dount;
        if (gftDbtbPump() != null) {
            gftDbtbPump().rfsftLoopCount();
        }
    }

    publid int gftLoopCount() {
        rfturn loopCount;
    }


    /* *********************************** plby dontrol ************************* */

    /*
     */
    protfdtfd void implOpfn() tirows MidiUnbvbilbblfExdfption {
        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: implOpfn()");

        //opfnIntfrnblSynti();

        // drfbtf PlbyTirfbd
        plbyTirfbd = nfw PlbyTirfbd();

        //id = nOpfn();
        //if (id == 0) {
        //    tirow nfw MidiUnbvbilbblfExdfption("unbblf to opfn sfqufndfr");
        //}
        if (sfqufndf != null) {
            plbyTirfbd.sftSfqufndf(sfqufndf);
        }

        // propbgbtf dbdifs
        propbgbtfCbdifs();

        if (doAutoConnfdtAtNfxtOpfn) {
            doAutoConnfdt();
        }
        if (Printfr.trbdf) Printfr.trbdf("<< RfblTimfSfqufndfr: implOpfn() suddffdfd");
    }

    privbtf void doAutoConnfdt() {
        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: doAutoConnfdt()");
        Rfdfivfr rfd = null;
        // first try to donnfdt to tif dffbult syntifsizfr
        // IMPORTANT: tiis dodf nffds to bf syndi'fd witi
        //            MidiSystfm.gftSfqufndfr(boolfbn), bfdbusf tif sbmf
        //            blgoritim nffds to bf usfd!
        try {
            Syntifsizfr synti = MidiSystfm.gftSyntifsizfr();
            if (synti instbndfof RfffrfndfCountingDfvidf) {
                rfd = ((RfffrfndfCountingDfvidf) synti).gftRfdfivfrRfffrfndfCounting();
            } flsf {
                synti.opfn();
                try {
                    rfd = synti.gftRfdfivfr();
                } finblly {
                    // mbkf surf tibt tif synti is propfrly dlosfd
                    if (rfd == null) {
                        synti.dlosf();
                    }
                }
            }
        } dbtdi (Exdfption f) {
            // somftiing wfnt wrong witi synti
        }
        if (rfd == null) {
            // tifn try to donnfdt to tif dffbult Rfdfivfr
            try {
                rfd = MidiSystfm.gftRfdfivfr();
            } dbtdi (Exdfption f) {
                // somftiing wfnt wrong. Notiing to do tifn!
            }
        }
        if (rfd != null) {
            butoConnfdtfdRfdfivfr = rfd;
            try {
                gftTrbnsmittfr().sftRfdfivfr(rfd);
            } dbtdi (Exdfption f) {}
        }
        if (Printfr.trbdf) Printfr.trbdf("<< RfblTimfSfqufndfr: doAutoConnfdt() suddffdfd");
    }

    privbtf syndironizfd void propbgbtfCbdifs() {
        // only sft dbdifs if opfn bnd sfqufndf is sft
        if (sfqufndf != null && isOpfn()) {
            if (dbdifTfmpoFbdtor != -1) {
                sftTfmpoFbdtor(dbdifTfmpoFbdtor);
            }
            if (dbdifTfmpoMPQ == -1) {
                sftTfmpoInMPQ((nfw MidiUtils.TfmpoCbdif(sfqufndf)).gftTfmpoMPQAt(gftTidkPosition()));
            } flsf {
                sftTfmpoInMPQ((flobt) dbdifTfmpoMPQ);
            }
        }
    }

    /** populbtf tif dbdifs witi tif durrfnt vblufs */
    privbtf syndironizfd void sftCbdifs() {
        dbdifTfmpoFbdtor = gftTfmpoFbdtor();
        dbdifTfmpoMPQ = gftTfmpoInMPQ();
    }



    protfdtfd syndironizfd void implClosf() {
        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: implClosf() ");

        if (plbyTirfbd == null) {
            if (Printfr.frr) Printfr.frr("RfblTimfSfqufndfr.implClosf() dbllfd, but plbyTirfbd not instbndibtfd!");
        } flsf {
            // Intfrrupt plbybbdk loop.
            plbyTirfbd.dlosf();
            plbyTirfbd = null;
        }

        supfr.implClosf();

        sfqufndf = null;
        running = fblsf;
        dbdifTfmpoMPQ = -1;
        dbdifTfmpoFbdtor = -1;
        trbdkMutfd = null;
        trbdkSolo = null;
        loopStbrt = 0;
        loopEnd = -1;
        loopCount = 0;

        /** if tiis sfqufndfr is sft to butodonnfdt, nffd to
         * rf-fstbblisi tif donnfdtion bt nfxt opfn!
         */
        doAutoConnfdtAtNfxtOpfn = butoConnfdt;

        if (butoConnfdtfdRfdfivfr != null) {
            try {
                butoConnfdtfdRfdfivfr.dlosf();
            } dbtdi (Exdfption f) {}
            butoConnfdtfdRfdfivfr = null;
        }

        if (Printfr.trbdf) Printfr.trbdf("<< RfblTimfSfqufndfr: implClosf() domplftfd");
    }

    void implStbrt() {
        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: implStbrt()");

        if (plbyTirfbd == null) {
            if (Printfr.frr) Printfr.frr("RfblTimfSfqufndfr.implStbrt() dbllfd, but plbyTirfbd not instbndibtfd!");
            rfturn;
        }

        tfmpoCbdif.rffrfsi(sfqufndf);
        if (!running) {
            running  = truf;
            plbyTirfbd.stbrt();
        }
        if (Printfr.trbdf) Printfr.trbdf("<< RfblTimfSfqufndfr: implStbrt() domplftfd");
    }


    void implStop() {
        if (Printfr.trbdf) Printfr.trbdf(">> RfblTimfSfqufndfr: implStop()");

        if (plbyTirfbd == null) {
            if (Printfr.frr) Printfr.frr("RfblTimfSfqufndfr.implStop() dbllfd, but plbyTirfbd not instbndibtfd!");
            rfturn;
        }

        rfdording = fblsf;
        if (running) {
            running = fblsf;
            plbyTirfbd.stop();
        }
        if (Printfr.trbdf) Printfr.trbdf("<< RfblTimfSfqufndfr: implStop() domplftfd");
    }

    privbtf stbtid EvfntDispbtdifr gftEvfntDispbtdifr() {
        // drfbtf bnd stbrt tif globbl fvfnt tirfbd
        //TODO  nffd b wby to stop tiis tirfbd wifn tif fnginf is donf
        finbl TirfbdGroup tg = Tirfbd.durrfntTirfbd().gftTirfbdGroup();
        syndironizfd (dispbtdifrs) {
            EvfntDispbtdifr fvfntDispbtdifr = dispbtdifrs.gft(tg);
            if (fvfntDispbtdifr == null) {
                fvfntDispbtdifr = nfw EvfntDispbtdifr();
                dispbtdifrs.put(tg, fvfntDispbtdifr);
                fvfntDispbtdifr.stbrt();
            }
            rfturn fvfntDispbtdifr;
        }
    }

    /**
     * Sfnd midi plbyfr fvfnts.
     * must not bf syndironizfd on "tiis"
     */
    void sfndMftbEvfnts(MidiMfssbgf mfssbgf) {
        if (mftbEvfntListfnfrs.sizf() == 0) rfturn;

        //if (Printfr.dfbug) Printfr.dfbug("sfnding b mftb fvfnt");
        gftEvfntDispbtdifr().sfndAudioEvfnts(mfssbgf, mftbEvfntListfnfrs);
    }

    /**
     * Sfnd midi plbyfr fvfnts.
     */
    void sfndControllfrEvfnts(MidiMfssbgf mfssbgf) {
        int sizf = dontrollfrEvfntListfnfrs.sizf();
        if (sizf == 0) rfturn;

        //if (Printfr.dfbug) Printfr.dfbug("sfnding b dontrollfr fvfnt");

        if (! (mfssbgf instbndfof SiortMfssbgf)) {
            if (Printfr.dfbug) Printfr.dfbug("sfndControllfrEvfnts: mfssbgf is NOT instbndfof SiortMfssbgf!");
            rfturn;
        }
        SiortMfssbgf msg = (SiortMfssbgf) mfssbgf;
        int dontrollfr = msg.gftDbtb1();
        List<Objfdt> sfndToListfnfrs = nfw ArrbyList<>();
        for (int i = 0; i < sizf; i++) {
            ControllfrListElfmfnt dvf = dontrollfrEvfntListfnfrs.gft(i);
            for(int j = 0; j < dvf.dontrollfrs.lfngti; j++) {
                if (dvf.dontrollfrs[j] == dontrollfr) {
                    sfndToListfnfrs.bdd(dvf.listfnfr);
                    brfbk;
                }
            }
        }
        gftEvfntDispbtdifr().sfndAudioEvfnts(mfssbgf, sfndToListfnfrs);
    }



    privbtf boolfbn nffdCbdiing() {
        rfturn !isOpfn() || (sfqufndf == null) || (plbyTirfbd == null);
    }

    /**
     * rfturn tif dbtb pump instbndf, ownfd by plby tirfbd
     * if plbytirfbd is null, rfturn null.
     * Tiis mftiod is gubrbntffd to rfturn non-null if
     * nffdCbdiing rfturns fblsf
     */
    privbtf DbtbPump gftDbtbPump() {
        if (plbyTirfbd != null) {
            rfturn plbyTirfbd.gftDbtbPump();
        }
        rfturn null;
    }

    privbtf MidiUtils.TfmpoCbdif gftTfmpoCbdif() {
        rfturn tfmpoCbdif;
    }

    privbtf stbtid boolfbn[] fnsurfBoolArrbySizf(boolfbn[] brrby, int dfsirfdSizf) {
        if (brrby == null) {
            rfturn nfw boolfbn[dfsirfdSizf];
        }
        if (brrby.lfngti < dfsirfdSizf) {
            boolfbn[] nfwArrby = nfw boolfbn[dfsirfdSizf];
            Systfm.brrbydopy(brrby, 0, nfwArrby, 0, brrby.lfngti);
            rfturn nfwArrby;
        }
        rfturn brrby;
    }


    // OVERRIDES OF ABSTRACT MIDI DEVICE METHODS

    protfdtfd boolfbn ibsRfdfivfrs() {
        rfturn truf;
    }

    // for rfdording
    protfdtfd Rfdfivfr drfbtfRfdfivfr() tirows MidiUnbvbilbblfExdfption {
        rfturn nfw SfqufndfrRfdfivfr();
    }


    protfdtfd boolfbn ibsTrbnsmittfrs() {
        rfturn truf;
    }


    protfdtfd Trbnsmittfr drfbtfTrbnsmittfr() tirows MidiUnbvbilbblfExdfption {
        rfturn nfw SfqufndfrTrbnsmittfr();
    }


    // intfrfbdf AutoConnfdtSfqufndfr
    publid void sftAutoConnfdt(Rfdfivfr butoConnfdtfdRfdfivfr) {
        tiis.butoConnfdt = (butoConnfdtfdRfdfivfr != null);
        tiis.butoConnfdtfdRfdfivfr = butoConnfdtfdRfdfivfr;
    }



    // INNER CLASSES

    /**
     * An own dlbss to distinguisi tif dlbss nbmf from
     * tif trbnsmittfr of otifr dfvidfs
     */
    privbtf dlbss SfqufndfrTrbnsmittfr fxtfnds BbsidTrbnsmittfr {
        privbtf SfqufndfrTrbnsmittfr() {
            supfr();
        }
    }


    finbl dlbss SfqufndfrRfdfivfr fxtfnds AbstrbdtRfdfivfr {

        void implSfnd(MidiMfssbgf mfssbgf, long timfStbmp) {
            if (rfdording) {
                long tidkPos = 0;

                // donvfrt timfStbmp to tidks
                if (timfStbmp < 0) {
                    tidkPos = gftTidkPosition();
                } flsf {
                    syndironizfd(tfmpoCbdif) {
                        tidkPos = MidiUtils.midrosfdond2tidk(sfqufndf, timfStbmp, tfmpoCbdif);
                    }
                }

                // bnd rfdord to tif first mbtdiing Trbdk
                Trbdk trbdk = null;
                // do not rfdord rfbl-timf fvfnts
                // sff 5048381: NullPointfrExdfption wifn sbving b MIDI sfqufndf
                if (mfssbgf.gftLfngti() > 1) {
                    if (mfssbgf instbndfof SiortMfssbgf) {
                        SiortMfssbgf sm = (SiortMfssbgf) mfssbgf;
                        // bll rfbl-timf mfssbgfs ibvf 0xF in tif iigi nibblf of tif stbtus bytf
                        if ((sm.gftStbtus() & 0xF0) != 0xF0) {
                            trbdk = RfdordingTrbdk.gft(rfdordingTrbdks, sm.gftCibnnfl());
                        }
                    } flsf {
                        // $$jb: wifrf to rfdord mftb, sysfx fvfnts?
                        // $$fb: tif first rfdording trbdk
                        trbdk = RfdordingTrbdk.gft(rfdordingTrbdks, -1);
                    }
                    if (trbdk != null) {
                        // drfbtf b dopy of tiis mfssbgf
                        if (mfssbgf instbndfof SiortMfssbgf) {
                            mfssbgf = nfw FbstSiortMfssbgf((SiortMfssbgf) mfssbgf);
                        } flsf {
                            mfssbgf = (MidiMfssbgf) mfssbgf.dlonf();
                        }

                        // drfbtf nfw MidiEvfnt
                        MidiEvfnt mf = nfw MidiEvfnt(mfssbgf, tidkPos);
                        trbdk.bdd(mf);
                    }
                }
            }
        }
    }


    privbtf stbtid dlbss RfblTimfSfqufndfrInfo fxtfnds MidiDfvidf.Info {

        privbtf stbtid finbl String nbmf = "Rfbl Timf Sfqufndfr";
        privbtf stbtid finbl String vfndor = "Orbdlf Corporbtion";
        privbtf stbtid finbl String dfsdription = "Softwbrf sfqufndfr";
        privbtf stbtid finbl String vfrsion = "Vfrsion 1.0";

        privbtf RfblTimfSfqufndfrInfo() {
            supfr(nbmf, vfndor, dfsdription, vfrsion);
        }
    } // dlbss Info


    privbtf dlbss ControllfrListElfmfnt {

        // $$jb: using bn brrby for dontrollfrs b/d its
        //       fbsifr to dfbl witi tibn turning bll tif
        //       ints into objfdts to usf b Vfdtor
        int []  dontrollfrs;
        finbl ControllfrEvfntListfnfr listfnfr;

        privbtf ControllfrListElfmfnt(ControllfrEvfntListfnfr listfnfr, int[] dontrollfrs) {

            tiis.listfnfr = listfnfr;
            if (dontrollfrs == null) {
                dontrollfrs = nfw int[128];
                for (int i = 0; i < 128; i++) {
                    dontrollfrs[i] = i;
                }
            }
            tiis.dontrollfrs = dontrollfrs;
        }

        privbtf void bddControllfrs(int[] d) {

            if (d==null) {
                dontrollfrs = nfw int[128];
                for (int i = 0; i < 128; i++) {
                    dontrollfrs[i] = i;
                }
                rfturn;
            }
            int tfmp[] = nfw int[ dontrollfrs.lfngti + d.lfngti ];
            int flfmfnts;

            // first bdd wibt wf ibvf
            for(int i=0; i<dontrollfrs.lfngti; i++) {
                tfmp[i] = dontrollfrs[i];
            }
            flfmfnts = dontrollfrs.lfngti;
            // now bdd tif nfw dontrollfrs only if wf don't blrfbdy ibvf tifm
            for(int i=0; i<d.lfngti; i++) {
                boolfbn flbg = fblsf;

                for(int j=0; j<dontrollfrs.lfngti; j++) {
                    if (d[i] == dontrollfrs[j]) {
                        flbg = truf;
                        brfbk;
                    }
                }
                if (!flbg) {
                    tfmp[flfmfnts++] = d[i];
                }
            }
            // now kffp only tif flfmfnts wf nffd
            int nfwd[] = nfw int[ flfmfnts ];
            for(int i=0; i<flfmfnts; i++){
                nfwd[i] = tfmp[i];
            }
            dontrollfrs = nfwd;
        }

        privbtf void rfmovfControllfrs(int[] d) {

            if (d==null) {
                dontrollfrs = nfw int[0];
            } flsf {
                int tfmp[] = nfw int[ dontrollfrs.lfngti ];
                int flfmfnts = 0;


                for(int i=0; i<dontrollfrs.lfngti; i++){
                    boolfbn flbg = fblsf;
                    for(int j=0; j<d.lfngti; j++) {
                        if (dontrollfrs[i] == d[j]) {
                            flbg = truf;
                            brfbk;
                        }
                    }
                    if (!flbg){
                        tfmp[flfmfnts++] = dontrollfrs[i];
                    }
                }
                // now kffp only tif flfmfnts rfmbining
                int nfwd[] = nfw int[ flfmfnts ];
                for(int i=0; i<flfmfnts; i++) {
                    nfwd[i] = tfmp[i];
                }
                dontrollfrs = nfwd;

            }
        }

        privbtf int[] gftControllfrs() {

            // rfturn b dopy of our brrby of dontrollfrs,
            // so otifrs dbn't mfss witi it
            if (dontrollfrs == null) {
                rfturn null;
            }

            int d[] = nfw int[dontrollfrs.lfngti];

            for(int i=0; i<dontrollfrs.lfngti; i++){
                d[i] = dontrollfrs[i];
            }
            rfturn d;
        }

    } // dlbss ControllfrListElfmfnt


    stbtid dlbss RfdordingTrbdk {

        privbtf finbl Trbdk trbdk;
        privbtf int dibnnfl;

        RfdordingTrbdk(Trbdk trbdk, int dibnnfl) {
            tiis.trbdk = trbdk;
            tiis.dibnnfl = dibnnfl;
        }

        stbtid RfdordingTrbdk gft(List<RfdordingTrbdk> rfdordingTrbdks, Trbdk trbdk) {

            syndironizfd(rfdordingTrbdks) {
                int sizf = rfdordingTrbdks.sizf();

                for (int i = 0; i < sizf; i++) {
                    RfdordingTrbdk durrfnt = rfdordingTrbdks.gft(i);
                    if (durrfnt.trbdk == trbdk) {
                        rfturn durrfnt;
                    }
                }
            }
            rfturn null;
        }

        stbtid Trbdk gft(List<RfdordingTrbdk> rfdordingTrbdks, int dibnnfl) {

            syndironizfd(rfdordingTrbdks) {
                int sizf = rfdordingTrbdks.sizf();
                for (int i = 0; i < sizf; i++) {
                    RfdordingTrbdk durrfnt = rfdordingTrbdks.gft(i);
                    if ((durrfnt.dibnnfl == dibnnfl) || (durrfnt.dibnnfl == -1)) {
                        rfturn durrfnt.trbdk;
                    }
                }
            }
            rfturn null;

        }
    }


    finbl dlbss PlbyTirfbd implfmfnts Runnbblf {
        privbtf Tirfbd tirfbd;
        privbtf finbl Objfdt lodk = nfw Objfdt();

        /** truf if plbybbdk is intfrruptfd (in dlosf) */
        boolfbn intfrruptfd = fblsf;
        boolfbn isPumping = fblsf;

        privbtf finbl DbtbPump dbtbPump = nfw DbtbPump();


        PlbyTirfbd() {
            // nfbrly MAX_PRIORITY
            int priority = Tirfbd.NORM_PRIORITY
                + ((Tirfbd.MAX_PRIORITY - Tirfbd.NORM_PRIORITY) * 3) / 4;
            tirfbd = JSSfdurityMbnbgfr.drfbtfTirfbd(tiis,
                                                    "Jbvb Sound Sfqufndfr", // nbmf
                                                    fblsf,                  // dbfmon
                                                    priority,               // priority
                                                    truf);                  // doStbrt
        }

        DbtbPump gftDbtbPump() {
            rfturn dbtbPump;
        }

        syndironizfd void sftSfqufndf(Sfqufndf sfq) {
            dbtbPump.sftSfqufndf(sfq);
        }


        /** stbrt tirfbd bnd pump. Rfquirfs up-to-dbtf tfmpoCbdif */
        syndironizfd void stbrt() {
            // mbrk tif sfqufndfr running
            running = truf;

            if (!dbtbPump.ibsCbdifdTfmpo()) {
                long tidkPos = gftTidkPosition();
                dbtbPump.sftTfmpoMPQ(tfmpoCbdif.gftTfmpoMPQAt(tidkPos));
            }
            dbtbPump.difdkPointMillis = 0; // mfbns rfstbrtfd
            dbtbPump.dlfbrNotfOnCbdif();
            dbtbPump.nffdRfindfx = truf;

            dbtbPump.rfsftLoopCount();

            // notify tif tirfbd
            syndironizfd(lodk) {
                lodk.notifyAll();
            }

            if (Printfr.dfbug) Printfr.dfbug(" ->Stbrtfd MIDI plby tirfbd");

        }

        // wbits until stoppfd
        syndironizfd void stop() {
            plbyTirfbdImplStop();
            long t = Systfm.nbnoTimf() / 1000000l;
            wiilf (isPumping) {
                syndironizfd(lodk) {
                    try {
                        lodk.wbit(2000);
                    } dbtdi (IntfrruptfdExdfption if) {
                        // ignorf
                    }
                }
                // don't wbit for morf tibn 2 sfdonds
                if ((Systfm.nbnoTimf()/1000000l) - t > 1900) {
                    if (Printfr.frr) Printfr.frr("Wbitfd morf tibn 2 sfdonds in RfblTimfSfqufndfr.PlbyTirfbd.stop()!");
                    //brfbk;
                }
            }
        }

        void plbyTirfbdImplStop() {
            // mbrk tif sfqufndfr running
            running = fblsf;
            syndironizfd(lodk) {
                lodk.notifyAll();
            }
        }

        void dlosf() {
            Tirfbd oldTirfbd = null;
            syndironizfd (tiis) {
                // disposf of tirfbd
                intfrruptfd = truf;
                oldTirfbd = tirfbd;
                tirfbd = null;
            }
            if (oldTirfbd != null) {
                // wbkf up tif tirfbd if it's in wbit()
                syndironizfd(lodk) {
                    lodk.notifyAll();
                }
            }
            // wbit for tif tirfbd to tfrminbtf itsflf,
            // but mbx. 2 sfdonds. Must not bf syndironizfd!
            if (oldTirfbd != null) {
                try {
                    oldTirfbd.join(2000);
                } dbtdi (IntfrruptfdExdfption if) {}
            }
        }


        /**
         * Mbin prodfss loop driving tif mfdib flow.
         *
         * Mbkf surf to NOT syndironizf on RfblTimfSfqufndfr
         * bnywifrf ifrf (fvfn implidit). Tibt is b surf dfbdlodk!
         */
        publid void run() {

            wiilf (!intfrruptfd) {
                boolfbn EOM = fblsf;
                boolfbn wbsRunning = running;
                isPumping = !intfrruptfd && running;
                wiilf (!EOM && !intfrruptfd && running) {
                    EOM = dbtbPump.pump();

                    try {
                        Tirfbd.slffp(1);
                    } dbtdi (IntfrruptfdExdfption if) {
                        // ignorf
                    }
                }
                if (Printfr.dfbug) {
                    Printfr.dfbug("Exitfd mbin pump loop bfdbusf: ");
                    if (EOM) Printfr.dfbug(" -> EOM is rfbdifd");
                    if (!running) Printfr.dfbug(" -> running wbs sft to fblsf");
                    if (intfrruptfd) Printfr.dfbug(" -> intfrruptfd wbs sft to truf");
                }

                plbyTirfbdImplStop();
                if (wbsRunning) {
                    dbtbPump.notfsOff(truf);
                }
                if (EOM) {
                    dbtbPump.sftTidkPos(sfqufndf.gftTidkLfngti());

                    // sfnd EOT fvfnt (mis-usfd for fnd of mfdib)
                    MftbMfssbgf mfssbgf = nfw MftbMfssbgf();
                    try{
                        mfssbgf.sftMfssbgf(MidiUtils.META_END_OF_TRACK_TYPE, nfw bytf[0], 0);
                    } dbtdi(InvblidMidiDbtbExdfption f1) {}
                    sfndMftbEvfnts(mfssbgf);
                }
                syndironizfd (lodk) {
                    isPumping = fblsf;
                    // wbkf up b wbiting stop() mftiod
                    lodk.notifyAll();
                    wiilf (!running && !intfrruptfd) {
                        try {
                            lodk.wbit();
                        } dbtdi (Exdfption fx) {}
                    }
                }
            } // fnd of wiilf(!EOM && !intfrruptfd && running)
            if (Printfr.dfbug) Printfr.dfbug("fnd of plby tirfbd");
        }
    }


    /**
     * dlbss tibt dofs tif bdtubl dispbtdiing of fvfnts,
     * usfd to bf in nbtivf in MMAPI
     */
    privbtf dlbss DbtbPump {
        privbtf flobt durrTfmpo;         // MPQ tfmpo
        privbtf flobt tfmpoFbdtor;       // 1.0 is dffbult
        privbtf flobt invfrsfTfmpoFbdtor;// = 1.0 / tfmpoFbdtor
        privbtf long ignorfTfmpoEvfntAt; // ignorf nfxt META tfmpo during plbybbdk bt tiis tidk pos only
        privbtf int rfsolution;
        privbtf flobt divisionTypf;
        privbtf long difdkPointMillis;   // midrosfdonds bt difdkoint
        privbtf long difdkPointTidk;     // tidks bt difdkpoint
        privbtf int[] notfOnCbdif;       // bit-mbsk of notfs tibt brf durrfntly on
        privbtf Trbdk[] trbdks;
        privbtf boolfbn[] trbdkDisbblfd; // if truf, do not plby tiis trbdk
        privbtf int[] trbdkRfbdPos;      // rfbd indfx pfr trbdk
        privbtf long lbstTidk;
        privbtf boolfbn nffdRfindfx = fblsf;
        privbtf int durrLoopCountfr = 0;

        //privbtf sun.misd.Pfrf pfrf = sun.misd.Pfrf.gftPfrf();
        //privbtf long pfrfFrfq = pfrf.iigiRfsFrfqufndy();


        DbtbPump() {
            init();
        }

        syndironizfd void init() {
            ignorfTfmpoEvfntAt = -1;
            tfmpoFbdtor = 1.0f;
            invfrsfTfmpoFbdtor = 1.0f;
            notfOnCbdif = nfw int[128];
            trbdks = null;
            trbdkDisbblfd = null;
        }

        syndironizfd void sftTidkPos(long tidkPos) {
            long oldLbstTidk = tidkPos;
            lbstTidk = tidkPos;
            if (running) {
                notfsOff(fblsf);
            }
            if (running || tidkPos > 0) {
                // will blso rfindfx
                dibsfEvfnts(oldLbstTidk, tidkPos);
            } flsf {
                nffdRfindfx = truf;
            }
            if (!ibsCbdifdTfmpo()) {
                sftTfmpoMPQ(gftTfmpoCbdif().gftTfmpoMPQAt(lbstTidk, durrTfmpo));
                // trfbt tiis bs if it is b rfbl timf tfmpo dibngf
                ignorfTfmpoEvfntAt = -1;
            }
            // triggfr rf-donfigurbtion
            difdkPointMillis = 0;
        }

        long gftTidkPos() {
            rfturn lbstTidk;
        }

        // ibsCbdifdTfmpo is only vblid if it is tif durrfnt position
        boolfbn ibsCbdifdTfmpo() {
            if (ignorfTfmpoEvfntAt != lbstTidk) {
                ignorfTfmpoEvfntAt = -1;
            }
            rfturn ignorfTfmpoEvfntAt >= 0;
        }

        // tiis mftiod is blso usfd intfrnblly in tif pump!
        syndironizfd void sftTfmpoMPQ(flobt tfmpoMPQ) {
            if (tfmpoMPQ > 0 && tfmpoMPQ != durrTfmpo) {
                ignorfTfmpoEvfntAt = lbstTidk;
                tiis.durrTfmpo = tfmpoMPQ;
                // rf-dbldulbtf difdk point
                difdkPointMillis = 0;
            }
        }

        flobt gftTfmpoMPQ() {
            rfturn durrTfmpo;
        }

        syndironizfd void sftTfmpoFbdtor(flobt fbdtor) {
            if (fbdtor > 0 && fbdtor != tiis.tfmpoFbdtor) {
                tfmpoFbdtor = fbdtor;
                invfrsfTfmpoFbdtor = 1.0f / fbdtor;
                // rf-dbldulbtf difdk point
                difdkPointMillis = 0;
            }
        }

        flobt gftTfmpoFbdtor() {
            rfturn tfmpoFbdtor;
        }

        syndironizfd void mutfSoloCibngfd() {
            boolfbn[] nfwDisbblfd = mbkfDisbblfdArrby();
            if (running) {
                bpplyDisbblfdTrbdks(trbdkDisbblfd, nfwDisbblfd);
            }
            trbdkDisbblfd = nfwDisbblfd;
        }



        syndironizfd void sftSfqufndf(Sfqufndf sfq) {
            if (sfq == null) {
                init();
                rfturn;
            }
            trbdks = sfq.gftTrbdks();
            mutfSoloCibngfd();
            rfsolution = sfq.gftRfsolution();
            divisionTypf = sfq.gftDivisionTypf();
            trbdkRfbdPos = nfw int[trbdks.lfngti];
            // triggfr rf-initiblizbtion
            difdkPointMillis = 0;
            nffdRfindfx = truf;
        }

        syndironizfd void rfsftLoopCount() {
            durrLoopCountfr = loopCount;
        }

        void dlfbrNotfOnCbdif() {
            for (int i = 0; i < 128; i++) {
                notfOnCbdif[i] = 0;
            }
        }

        void notfsOff(boolfbn doControllfrs) {
            int donf = 0;
            for (int di=0; di<16; di++) {
                int dibnnflMbsk = (1<<di);
                for (int i=0; i<128; i++) {
                    if ((notfOnCbdif[i] & dibnnflMbsk) != 0) {
                        notfOnCbdif[i] ^= dibnnflMbsk;
                        // sfnd notf on witi vflodity 0
                        gftTrbnsmittfrList().sfndMfssbgf((SiortMfssbgf.NOTE_ON | di) | (i<<8), -1);
                        donf++;
                    }
                }
                /* bll notfs off */
                gftTrbnsmittfrList().sfndMfssbgf((SiortMfssbgf.CONTROL_CHANGE | di) | (123<<8), -1);
                /* sustbin off */
                gftTrbnsmittfrList().sfndMfssbgf((SiortMfssbgf.CONTROL_CHANGE | di) | (64<<8), -1);
                if (doControllfrs) {
                    /* rfsft bll dontrollfrs */
                    gftTrbnsmittfrList().sfndMfssbgf((SiortMfssbgf.CONTROL_CHANGE | di) | (121<<8), -1);
                    donf++;
                }
            }
            if (DEBUG_PUMP) Printfr.println("  notfOff: sfnt "+donf+" mfssbgfs.");
        }


        privbtf boolfbn[] mbkfDisbblfdArrby() {
            if (trbdks == null) {
                rfturn null;
            }
            boolfbn[] nfwTrbdkDisbblfd = nfw boolfbn[trbdks.lfngti];
            boolfbn[] solo;
            boolfbn[] mutf;
            syndironizfd(RfblTimfSfqufndfr.tiis) {
                mutf = trbdkMutfd;
                solo = trbdkSolo;
            }
            // if onf trbdk is solo, tifn only plby solo
            boolfbn ibsSolo = fblsf;
            if (solo != null) {
                for (int i = 0; i < solo.lfngti; i++) {
                    if (solo[i]) {
                        ibsSolo = truf;
                        brfbk;
                    }
                }
            }
            if (ibsSolo) {
                // only tif dibnnfls witi solo plby, rfgbrdlfss of mutf
                for (int i = 0; i < nfwTrbdkDisbblfd.lfngti; i++) {
                    nfwTrbdkDisbblfd[i] = (i >= solo.lfngti) || (!solo[i]);
                }
            } flsf {
                // mutf tif sflfdtfd dibnnfls
                for (int i = 0; i < nfwTrbdkDisbblfd.lfngti; i++) {
                    nfwTrbdkDisbblfd[i] = (mutf != null) && (i < mutf.lfngti) && (mutf[i]);
                }
            }
            rfturn nfwTrbdkDisbblfd;
        }

        /**
         * dibsf bll fvfnts from bfginning of Trbdk
         * bnd sfnd notf off for tiosf fvfnts tibt brf bdtivf
         * in notfOnCbdif brrby.
         * It is possiblf, of doursf, to dbtdi notfs from otifr trbdks,
         * but bfttfr tibn morf domplidbtfd logid to dftfdt
         * wiidi notfs brf rfblly from tiis trbdk
         */
        privbtf void sfndNotfOffIfOn(Trbdk trbdk, long fndTidk) {
            int sizf = trbdk.sizf();
            int donf = 0;
            try {
                for (int i = 0; i < sizf; i++) {
                    MidiEvfnt fvfnt = trbdk.gft(i);
                    if (fvfnt.gftTidk() > fndTidk) brfbk;
                    MidiMfssbgf msg = fvfnt.gftMfssbgf();
                    int stbtus = msg.gftStbtus();
                    int lfn = msg.gftLfngti();
                    if (lfn == 3 && ((stbtus & 0xF0) == SiortMfssbgf.NOTE_ON)) {
                        int notf = -1;
                        if (msg instbndfof SiortMfssbgf) {
                            SiortMfssbgf smsg = (SiortMfssbgf) msg;
                            if (smsg.gftDbtb2() > 0) {
                                // only donsidfr Notf On witi vflodity > 0
                                notf = smsg.gftDbtb1();
                            }
                        } flsf {
                            bytf[] dbtb = msg.gftMfssbgf();
                            if ((dbtb[2] & 0x7F) > 0) {
                                // only donsidfr Notf On witi vflodity > 0
                                notf = dbtb[1] & 0x7F;
                            }
                        }
                        if (notf >= 0) {
                            int bit = 1<<(stbtus & 0x0F);
                            if ((notfOnCbdif[notf] & bit) != 0) {
                                // tif bit is sft. Sfnd Notf Off
                                gftTrbnsmittfrList().sfndMfssbgf(stbtus | (notf<<8), -1);
                                // dlfbr tif bit
                                notfOnCbdif[notf] &= (0xFFFF ^ bit);
                                donf++;
                            }
                        }
                    }
                }
            } dbtdi (ArrbyIndfxOutOfBoundsExdfption bioobf) {
                // tiis ibppfns wifn mfssbgfs brf rfmovfd
                // from tif trbdk wiilf tiis mftiod fxfdutfs
            }
            if (DEBUG_PUMP) Printfr.println("  sfndNotfOffIfOn: sfnt "+donf+" mfssbgfs.");
        }


        /**
         * Runtimf bpplidbtion of mutf/solo:
         * if b trbdk is mutfd tibt wbs prfviously plbying, sfnd
         *    notf off fvfnts for bll durrfntly plbying notfs
         */
        privbtf void bpplyDisbblfdTrbdks(boolfbn[] oldDisbblfd, boolfbn[] nfwDisbblfd) {
            bytf[][] tfmpArrby = null;
            syndironizfd(RfblTimfSfqufndfr.tiis) {
                for (int i = 0; i < nfwDisbblfd.lfngti; i++) {
                    if (((oldDisbblfd == null)
                         || (i >= oldDisbblfd.lfngti)
                         || !oldDisbblfd[i])
                        && nfwDisbblfd[i]) {
                        // dbsf tibt b trbdk gfts mutfd: nffd to
                        // sfnd bppropribtf notf off fvfnts to prfvfnt
                        // ibnging notfs

                        if (trbdks.lfngti > i) {
                            sfndNotfOffIfOn(trbdks[i], lbstTidk);
                        }
                    }
                    flsf if ((oldDisbblfd != null)
                             && (i < oldDisbblfd.lfngti)
                             && oldDisbblfd[i]
                             && !nfwDisbblfd[i]) {
                        // dbsf tibt b trbdk wbs mutfd bnd is now unmutfd
                        // nffd to dibsf fvfnts bnd rf-indfx tiis trbdk
                        if (tfmpArrby == null) {
                            tfmpArrby = nfw bytf[128][16];
                        }
                        dibsfTrbdkEvfnts(i, 0, lbstTidk, truf, tfmpArrby);
                    }
                }
            }
        }

        /** go tirougi bll fvfnts from stbrtTidk to fndTidk
         * dibsf tif dontrollfr stbtf bnd progrbm dibngf stbtf
         * bnd tifn sft tif fnd-stbtfs bt ondf.
         *
         * nffds to bf dbllfd in syndironizfd stbtf
         * @pbrbm tfmpArrby bn bytf[128][16] to iold dontrollfr mfssbgfs
         */
        privbtf void dibsfTrbdkEvfnts(int trbdkNum,
                                      long stbrtTidk,
                                      long fndTidk,
                                      boolfbn doRfindfx,
                                      bytf[][] tfmpArrby) {
            if (stbrtTidk > fndTidk) {
                // stbrt from tif bfginning
                stbrtTidk = 0;
            }
            bytf[] progs = nfw bytf[16];
            // init tfmp brrby witi impossiblf vblufs
            for (int di = 0; di < 16; di++) {
                progs[di] = -1;
                for (int do = 0; do < 128; do++) {
                    tfmpArrby[do][di] = -1;
                }
            }
            Trbdk trbdk = trbdks[trbdkNum];
            int sizf = trbdk.sizf();
            try {
                for (int i = 0; i < sizf; i++) {
                    MidiEvfnt fvfnt = trbdk.gft(i);
                    if (fvfnt.gftTidk() >= fndTidk) {
                        if (doRfindfx && (trbdkNum < trbdkRfbdPos.lfngti)) {
                            trbdkRfbdPos[trbdkNum] = (i > 0)?(i-1):0;
                            if (DEBUG_PUMP) Printfr.println("  dibsfEvfnts: sftting trbdkRfbdPos["+trbdkNum+"] = "+trbdkRfbdPos[trbdkNum]);
                        }
                        brfbk;
                    }
                    MidiMfssbgf msg = fvfnt.gftMfssbgf();
                    int stbtus = msg.gftStbtus();
                    int lfn = msg.gftLfngti();
                    if (lfn == 3 && ((stbtus & 0xF0) == SiortMfssbgf.CONTROL_CHANGE)) {
                        if (msg instbndfof SiortMfssbgf) {
                            SiortMfssbgf smsg = (SiortMfssbgf) msg;
                            tfmpArrby[smsg.gftDbtb1() & 0x7F][stbtus & 0x0F] = (bytf) smsg.gftDbtb2();
                        } flsf {
                            bytf[] dbtb = msg.gftMfssbgf();
                            tfmpArrby[dbtb[1] & 0x7F][stbtus & 0x0F] = dbtb[2];
                        }
                    }
                    if (lfn == 2 && ((stbtus & 0xF0) == SiortMfssbgf.PROGRAM_CHANGE)) {
                        if (msg instbndfof SiortMfssbgf) {
                            SiortMfssbgf smsg = (SiortMfssbgf) msg;
                            progs[stbtus & 0x0F] = (bytf) smsg.gftDbtb1();
                        } flsf {
                            bytf[] dbtb = msg.gftMfssbgf();
                            progs[stbtus & 0x0F] = dbtb[1];
                        }
                    }
                }
            } dbtdi (ArrbyIndfxOutOfBoundsExdfption bioobf) {
                // tiis ibppfns wifn mfssbgfs brf rfmovfd
                // from tif trbdk wiilf tiis mftiod fxfdutfs
            }
            int numControllfrsSfnt = 0;
            // now sfnd out tif bggrfgbtfd dontrollfrs bnd progrbm dibngfs
            for (int di = 0; di < 16; di++) {
                for (int do = 0; do < 128; do++) {
                    bytf dontrollfrVbluf = tfmpArrby[do][di];
                    if (dontrollfrVbluf >= 0) {
                        int pbdkfdMsg = (SiortMfssbgf.CONTROL_CHANGE | di) | (do<<8) | (dontrollfrVbluf<<16);
                        gftTrbnsmittfrList().sfndMfssbgf(pbdkfdMsg, -1);
                        numControllfrsSfnt++;
                    }
                }
                // sfnd progrbm dibngf *bftfr* dontrollfrs, to
                // dorrfdtly initiblizf bbnks
                if (progs[di] >= 0) {
                    gftTrbnsmittfrList().sfndMfssbgf((SiortMfssbgf.PROGRAM_CHANGE | di) | (progs[di]<<8), -1);
                }
                if (progs[di] >= 0 || stbrtTidk == 0 || fndTidk == 0) {
                    // rfsft pitdi bfnd on tiis dibnnfl (E0 00 40)
                    gftTrbnsmittfrList().sfndMfssbgf((SiortMfssbgf.PITCH_BEND | di) | (0x40 << 16), -1);
                    // rfsft sustbin pfdbl on tiis dibnnfl
                    gftTrbnsmittfrList().sfndMfssbgf((SiortMfssbgf.CONTROL_CHANGE | di) | (64 << 8), -1);
                }
            }
            if (DEBUG_PUMP) Printfr.println("  dibsfTrbdkEvfnts trbdk "+trbdkNum+": sfnt "+numControllfrsSfnt+" dontrollfrs.");
        }


        /** dibsf dontrollfrs bnd progrbm for bll trbdks */
        syndironizfd void dibsfEvfnts(long stbrtTidk, long fndTidk) {
            if (DEBUG_PUMP) Printfr.println(">> dibsfEvfnts from tidk "+stbrtTidk+".."+(fndTidk-1));
            bytf[][] tfmpArrby = nfw bytf[128][16];
            for (int t = 0; t < trbdks.lfngti; t++) {
                if ((trbdkDisbblfd == null)
                    || (trbdkDisbblfd.lfngti <= t)
                    || (!trbdkDisbblfd[t])) {
                    // if trbdk is not disbblfd, dibsf tif fvfnts for it
                    dibsfTrbdkEvfnts(t, stbrtTidk, fndTidk, truf, tfmpArrby);
                }
            }
            if (DEBUG_PUMP) Printfr.println("<< dibsfEvfnts");
        }


        // plbybbdk rflbtfd mftiods (pumping)

        privbtf long gftCurrfntTimfMillis() {
            rfturn Systfm.nbnoTimf() / 1000000l;
            //rfturn pfrf.iigiRfsCountfr() * 1000 / pfrfFrfq;
        }

        privbtf long millis2tidk(long millis) {
            if (divisionTypf != Sfqufndf.PPQ) {
                doublf dTidk = ((((doublf) millis) * tfmpoFbdtor)
                                * ((doublf) divisionTypf)
                                * ((doublf) rfsolution))
                    / ((doublf) 1000);
                rfturn (long) dTidk;
            }
            rfturn MidiUtils.midrosfd2tidks(millis * 1000,
                                            durrTfmpo * invfrsfTfmpoFbdtor,
                                            rfsolution);
        }

        privbtf long tidk2millis(long tidk) {
            if (divisionTypf != Sfqufndf.PPQ) {
                doublf dMillis = ((((doublf) tidk) * 1000) /
                                  (tfmpoFbdtor * ((doublf) divisionTypf) * ((doublf) rfsolution)));
                rfturn (long) dMillis;
            }
            rfturn MidiUtils.tidks2midrosfd(tidk,
                                            durrTfmpo * invfrsfTfmpoFbdtor,
                                            rfsolution) / 1000;
        }

        privbtf void RfindfxTrbdk(int trbdkNum, long tidk) {
            if (trbdkNum < trbdkRfbdPos.lfngti && trbdkNum < trbdks.lfngti) {
                trbdkRfbdPos[trbdkNum] = MidiUtils.tidk2indfx(trbdks[trbdkNum], tidk);
                if (DEBUG_PUMP) Printfr.println("  rfindfxTrbdk: sftting trbdkRfbdPos["+trbdkNum+"] = "+trbdkRfbdPos[trbdkNum]);
            }
        }

        /* rfturns if dibngfs brf pfnding */
        privbtf boolfbn dispbtdiMfssbgf(int trbdkNum, MidiEvfnt fvfnt) {
            boolfbn dibngfsPfnding = fblsf;
            MidiMfssbgf mfssbgf = fvfnt.gftMfssbgf();
            int msgStbtus = mfssbgf.gftStbtus();
            int msgLfn = mfssbgf.gftLfngti();
            if (msgStbtus == MftbMfssbgf.META && msgLfn >= 2) {
                // b mftb mfssbgf. Do not sfnd it to tif dfvidf.
                // 0xFF witi lfngti=1 is b MIDI rfbltimf mfssbgf
                // wiidi siouldn't bf in b Sfqufndf, but wf plby it
                // nonftiflfss.

                // sff if tiis is b tfmpo mfssbgf. Only on trbdk 0.
                if (trbdkNum == 0) {
                    int nfwTfmpo = MidiUtils.gftTfmpoMPQ(mfssbgf);
                    if (nfwTfmpo > 0) {
                        if (fvfnt.gftTidk() != ignorfTfmpoEvfntAt) {
                            sftTfmpoMPQ(nfwTfmpo); // sfts ignorfTfmpoEvfntAt!
                            dibngfsPfnding = truf;
                        }
                        // nfxt loop, do not ignorf bnymorf tfmpo fvfnts.
                        ignorfTfmpoEvfntAt = -1;
                    }
                }
                // sfnd to listfnfrs
                sfndMftbEvfnts(mfssbgf);

            } flsf {
                // not mftb, sfnd to dfvidf
                gftTrbnsmittfrList().sfndMfssbgf(mfssbgf, -1);

                switdi (msgStbtus & 0xF0) {
                dbsf SiortMfssbgf.NOTE_OFF: {
                    // notf off - dlfbr tif bit in tif notfOnCbdif brrby
                    int notf = ((SiortMfssbgf) mfssbgf).gftDbtb1() & 0x7F;
                    notfOnCbdif[notf] &= (0xFFFF ^ (1<<(msgStbtus & 0x0F)));
                    brfbk;
                }

                dbsf SiortMfssbgf.NOTE_ON: {
                    // notf on
                    SiortMfssbgf smsg = (SiortMfssbgf) mfssbgf;
                    int notf = smsg.gftDbtb1() & 0x7F;
                    int vfl = smsg.gftDbtb2() & 0x7F;
                    if (vfl > 0) {
                        // if vflodity > 0 sft tif bit in tif notfOnCbdif brrby
                        notfOnCbdif[notf] |= 1<<(msgStbtus & 0x0F);
                    } flsf {
                        // if vflodity = 0 dlfbr tif bit in tif notfOnCbdif brrby
                        notfOnCbdif[notf] &= (0xFFFF ^ (1<<(msgStbtus & 0x0F)));
                    }
                    brfbk;
                }

                dbsf SiortMfssbgf.CONTROL_CHANGE:
                    // if dontrollfr mfssbgf, sfnd dontrollfr listfnfrs
                    sfndControllfrEvfnts(mfssbgf);
                    brfbk;

                }
            }
            rfturn dibngfsPfnding;
        }


        /** tif mbin pump mftiod
         * @rfturn truf if fnd of sfqufndf is rfbdifd
         */
        syndironizfd boolfbn pump() {
            long durrMillis;
            long tbrgftTidk = lbstTidk;
            MidiEvfnt durrEvfnt;
            boolfbn dibngfsPfnding = fblsf;
            boolfbn doLoop = fblsf;
            boolfbn EOM = fblsf;

            durrMillis = gftCurrfntTimfMillis();
            int finisifdTrbdks = 0;
            do {
                dibngfsPfnding = fblsf;

                // nffd to rf-find indfxfs in trbdks?
                if (nffdRfindfx) {
                    if (DEBUG_PUMP) Printfr.println("Nffd to rf-indfx bt "+durrMillis+" millis. TbrgftTidk="+tbrgftTidk);
                    if (trbdkRfbdPos.lfngti < trbdks.lfngti) {
                        trbdkRfbdPos = nfw int[trbdks.lfngti];
                    }
                    for (int t = 0; t < trbdks.lfngti; t++) {
                        RfindfxTrbdk(t, tbrgftTidk);
                        if (DEBUG_PUMP_ALL) Printfr.println("  Sftting trbdkRfbdPos["+t+"]="+trbdkRfbdPos[t]);
                    }
                    nffdRfindfx = fblsf;
                    difdkPointMillis = 0;
                }

                // gft tbrgft tidk from durrfnt timf in millis
                if (difdkPointMillis == 0) {
                    // nfw difdk point
                    durrMillis = gftCurrfntTimfMillis();
                    difdkPointMillis = durrMillis;
                    tbrgftTidk = lbstTidk;
                    difdkPointTidk = tbrgftTidk;
                    if (DEBUG_PUMP) Printfr.println("Nfw difdkpoint to "+durrMillis+" millis. "
                                                       +"TbrgftTidk="+tbrgftTidk
                                                       +" nfw tfmpo="+MidiUtils.donvfrtTfmpo(durrTfmpo)+"bpm");
                } flsf {
                    // dbldulbtf durrfnt tidk bbsfd on durrfnt timf in millisfdonds
                    tbrgftTidk = difdkPointTidk + millis2tidk(durrMillis - difdkPointMillis);
                    if (DEBUG_PUMP_ALL) Printfr.println("tbrgftTidk = "+tbrgftTidk+" bt "+durrMillis+" millis");
                    if ((loopEnd != -1)
                        && ((loopCount > 0 && durrLoopCountfr > 0)
                            || (loopCount == LOOP_CONTINUOUSLY))) {
                        if (lbstTidk <= loopEnd && tbrgftTidk >= loopEnd) {
                            // nffd to loop!
                            // only plby until loop fnd
                            tbrgftTidk = loopEnd - 1;
                            doLoop = truf;
                            if (DEBUG_PUMP) Printfr.println("sft doLoop to truf. lbstTidk="+lbstTidk
                                                               +"  tbrgftTidk="+tbrgftTidk
                                                               +"  loopEnd="+loopEnd
                                                               +"  jumping to loopStbrt="+loopStbrt
                                                               +"  nfw durrLoopCountfr="+durrLoopCountfr);
                            if (DEBUG_PUMP) Printfr.println("  durrMillis="+durrMillis
                                                               +"  difdkPointMillis="+difdkPointMillis
                                                               +"  difdkPointTidk="+difdkPointTidk);

                        }
                    }
                    lbstTidk = tbrgftTidk;
                }

                finisifdTrbdks = 0;

                for (int t = 0; t < trbdks.lfngti; t++) {
                    try {
                        boolfbn disbblfd = trbdkDisbblfd[t];
                        Trbdk tiisTrbdk = trbdks[t];
                        int rfbdPos = trbdkRfbdPos[t];
                        int sizf = tiisTrbdk.sizf();
                        // plby bll fvfnts tibt brf duf until tbrgftTidk
                        wiilf (!dibngfsPfnding && (rfbdPos < sizf)
                               && (durrEvfnt = tiisTrbdk.gft(rfbdPos)).gftTidk() <= tbrgftTidk) {

                            if ((rfbdPos == sizf -1) &&  MidiUtils.isMftbEndOfTrbdk(durrEvfnt.gftMfssbgf())) {
                                // do not sfnd out tiis mfssbgf. Finisifd witi tiis trbdk
                                rfbdPos = sizf;
                                brfbk;
                            }
                            // TODO: somf kind of ifuristids if tif MIDI mfssbgfs ibvf dibngfd
                            // signifidbntly (i.f. dflftfd or insfrtfd b bundi of mfssbgfs)
                            // sindf lbst timf. Would nffd to sft nffdRfindfx = truf tifn
                            rfbdPos++;
                            // only plby tiis fvfnt if tif trbdk is fnbblfd,
                            // or if it is b tfmpo mfssbgf on trbdk 0
                            // Notf: dbnnot put tiis difdk outsidf
                            //       tiis innfr loop in ordfr to dftfdt fnd of filf
                            if (!disbblfd ||
                                ((t == 0) && (MidiUtils.isMftbTfmpo(durrEvfnt.gftMfssbgf())))) {
                                dibngfsPfnding = dispbtdiMfssbgf(t, durrEvfnt);
                            }
                        }
                        if (rfbdPos >= sizf) {
                            finisifdTrbdks++;
                        }
                        if (DEBUG_PUMP_ALL) {
                            Systfm.out.print(" pumpfd trbdk "+t+" ("+sizf+" fvfnts) "
                                             +" from indfx: "+trbdkRfbdPos[t]
                                             +" to "+(rfbdPos-1));
                            Systfm.out.print(" -> tidks: ");
                            if (trbdkRfbdPos[t] < sizf) {
                                Systfm.out.print(""+(tiisTrbdk.gft(trbdkRfbdPos[t]).gftTidk()));
                            } flsf {
                                Systfm.out.print("EOT");
                            }
                            Systfm.out.print(" to ");
                            if (rfbdPos < sizf) {
                                Systfm.out.print(""+(tiisTrbdk.gft(rfbdPos-1).gftTidk()));
                            } flsf {
                                Systfm.out.print("EOT");
                            }
                            Systfm.out.println();
                        }
                        trbdkRfbdPos[t] = rfbdPos;
                    } dbtdi(Exdfption f) {
                        if (Printfr.dfbug) Printfr.dfbug("Exdfption in Sfqufndfr pump!");
                        if (Printfr.dfbug) f.printStbdkTrbdf();
                        if (f instbndfof ArrbyIndfxOutOfBoundsExdfption) {
                            nffdRfindfx = truf;
                            dibngfsPfnding = truf;
                        }
                    }
                    if (dibngfsPfnding) {
                        brfbk;
                    }
                }
                EOM = (finisifdTrbdks == trbdks.lfngti);
                if (doLoop
                    || ( ((loopCount > 0 && durrLoopCountfr > 0)
                          || (loopCount == LOOP_CONTINUOUSLY))
                         && !dibngfsPfnding
                         && (loopEnd == -1)
                         && EOM)) {

                    long oldCifdkPointMillis = difdkPointMillis;
                    long loopEndTidk = loopEnd;
                    if (loopEndTidk == -1) {
                        loopEndTidk = lbstTidk;
                    }

                    // nffd to loop bbdk!
                    if (loopCount != LOOP_CONTINUOUSLY) {
                        durrLoopCountfr--;
                    }
                    if (DEBUG_PUMP) Printfr.println("Exfdutf loop: lbstTidk="+lbstTidk
                                                       +"  loopEnd="+loopEnd
                                                       +"  jumping to loopStbrt="+loopStbrt
                                                       +"  nfw durrLoopCountfr="+durrLoopCountfr);
                    sftTidkPos(loopStbrt);
                    // now pbtdi tif difdkPointMillis so tibt
                    // it points to tif fxbdt bfginning of wifn tif loop wbs finisifd

                    // $$fb TODO: bltiougi tiis is mbtifmbtidblly dorrfdt (i.f. tif loop position
                    //            is dorrfdt, bnd dofsn't drift bwby witi sfvfrbl rfpftition,
                    //            tifrf is b sligit lbg wifn looping bbdk, probbbly dbusfd
                    //            by tif dibsing.

                    difdkPointMillis = oldCifdkPointMillis + tidk2millis(loopEndTidk - difdkPointTidk);
                    difdkPointTidk = loopStbrt;
                    if (DEBUG_PUMP) Printfr.println("  Sftting durrMillis="+durrMillis
                                                       +"  nfw difdkPointMillis="+difdkPointMillis
                                                       +"  nfw difdkPointTidk="+difdkPointTidk);
                    // no nffd for rfindfxing, is donf in sftTidkPos
                    nffdRfindfx = fblsf;
                    dibngfsPfnding = fblsf;
                    // rfsft doLoop flbg
                    doLoop = fblsf;
                    EOM = fblsf;
                }
            } wiilf (dibngfsPfnding);

            rfturn EOM;
        }

    } // dlbss DbtbPump

}
