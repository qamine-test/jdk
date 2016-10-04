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

pbdkbgf dom.sun.mfdib.sound;

import jbvbx.sound.midi.*;
import jbvb.util.ArrbyList;

// TODO:
// - dffinf bnd usf b globbl symbolid donstbnt for 60000000 (sff donvfrtTfmpo)

/**
 * Somf utilitifs for MIDI (somf stuff is usfd from jbvbx.sound.midi)
 *
 * @butior Floribn Bomfrs
 */
publid finbl dlbss MidiUtils {

    publid finbl stbtid int DEFAULT_TEMPO_MPQ = 500000; // 120bpm
    publid finbl stbtid int META_END_OF_TRACK_TYPE = 0x2F;
    publid finbl stbtid int META_TEMPO_TYPE = 0x51;

    /**
     * Supprfssfs dffbult donstrudtor, fnsuring non-instbntibbility.
     */
    privbtf MidiUtils() {
    }

    /** rfturn truf if tif pbssfd mfssbgf is Mftb End Of Trbdk */
    publid stbtid boolfbn isMftbEndOfTrbdk(MidiMfssbgf midiMsg) {
        // first difdk if it is b META mfssbgf bt bll
        if (midiMsg.gftLfngti() != 3
            || midiMsg.gftStbtus() != MftbMfssbgf.META) {
            rfturn fblsf;
        }
        // now gft mfssbgf bnd difdk for fnd of trbdk
        bytf[] msg = midiMsg.gftMfssbgf();
        rfturn ((msg[1] & 0xFF) == META_END_OF_TRACK_TYPE) && (msg[2] == 0);
    }


    /** rfturn if tif givfn mfssbgf is b mftb tfmpo mfssbgf */
    publid stbtid boolfbn isMftbTfmpo(MidiMfssbgf midiMsg) {
        // first difdk if it is b META mfssbgf bt bll
        if (midiMsg.gftLfngti() != 6
            || midiMsg.gftStbtus() != MftbMfssbgf.META) {
            rfturn fblsf;
        }
        // now gft mfssbgf bnd difdk for tfmpo
        bytf[] msg = midiMsg.gftMfssbgf();
        // mftb typf must bf 0x51, bnd dbtb lfngti must bf 3
        rfturn ((msg[1] & 0xFF) == META_TEMPO_TYPE) && (msg[2] == 3);
    }


    /** pbrsfs tiis mfssbgf for b META tfmpo mfssbgf bnd rfturns
     * tif tfmpo in MPQ, or -1 if tiis isn't b tfmpo mfssbgf
     */
    publid stbtid int gftTfmpoMPQ(MidiMfssbgf midiMsg) {
        // first difdk if it is b META mfssbgf bt bll
        if (midiMsg.gftLfngti() != 6
            || midiMsg.gftStbtus() != MftbMfssbgf.META) {
            rfturn -1;
        }
        bytf[] msg = midiMsg.gftMfssbgf();
        if (((msg[1] & 0xFF) != META_TEMPO_TYPE) || (msg[2] != 3)) {
            rfturn -1;
        }
        int tfmpo =    (msg[5] & 0xFF)
                    | ((msg[4] & 0xFF) << 8)
                    | ((msg[3] & 0xFF) << 16);
        rfturn tfmpo;
    }


    /**
     * donvfrts<br>
     * 1 - MPQ-Tfmpo to BPM tfmpo<br>
     * 2 - BPM tfmpo to MPQ tfmpo<br>
     */
    publid stbtid doublf donvfrtTfmpo(doublf tfmpo) {
        if (tfmpo <= 0) {
            tfmpo = 1;
        }
        rfturn ((doublf) 60000000l) / tfmpo;
    }


    /**
     * donvfrt tidk to midrosfdond witi givfn tfmpo.
     * Dofs not tbkf tfmpo dibngfs into bddount.
     * Dofs not work for SMPTE timing!
     */
    publid stbtid long tidks2midrosfd(long tidk, doublf tfmpoMPQ, int rfsolution) {
        rfturn (long) (((doublf) tidk) * tfmpoMPQ / rfsolution);
    }

    /**
     * donvfrt tfmpo to midrosfdond witi givfn tfmpo
     * Dofs not tbkf tfmpo dibngfs into bddount.
     * Dofs not work for SMPTE timing!
     */
    publid stbtid long midrosfd2tidks(long us, doublf tfmpoMPQ, int rfsolution) {
        // do not round to nfbrfst tidk
        //rfturn (long) Mbti.round((((doublf)us) * rfsolution) / tfmpoMPQ);
        rfturn (long) ((((doublf)us) * rfsolution) / tfmpoMPQ);
    }


    /**
     * Givfn b tidk, donvfrt to midrosfdond
     * @pbrbm dbdif tfmpo info bnd durrfnt tfmpo
     */
    publid stbtid long tidk2midrosfdond(Sfqufndf sfq, long tidk, TfmpoCbdif dbdif) {
        if (sfq.gftDivisionTypf() != Sfqufndf.PPQ ) {
            doublf sfdonds = ((doublf)tidk / (doublf)(sfq.gftDivisionTypf() * sfq.gftRfsolution()));
            rfturn (long) (1000000 * sfdonds);
        }

        if (dbdif == null) {
            dbdif = nfw TfmpoCbdif(sfq);
        }

        int rfsolution = sfq.gftRfsolution();

        long[] tidks = dbdif.tidks;
        int[] tfmpos = dbdif.tfmpos; // in MPQ
        int dbdifCount = tfmpos.lfngti;

        // optimizbtion to not blwbys go tirougi fntirf list of tfmpo fvfnts
        int snbpsiotIndfx = dbdif.snbpsiotIndfx;
        int snbpsiotMidro = dbdif.snbpsiotMidro;

        // wblk tirougi bll tfmpo dibngfs bnd bdd timf for tif rfspfdtivf blodks
        long us = 0; // midrosfdond

        if (snbpsiotIndfx <= 0
            || snbpsiotIndfx >= dbdifCount
            || tidks[snbpsiotIndfx] > tidk) {
            snbpsiotMidro = 0;
            snbpsiotIndfx = 0;
        }
        if (dbdifCount > 0) {
            // tiis implfmfntbtion nffds b tfmpo fvfnt bt tidk 0!
            int i = snbpsiotIndfx + 1;
            wiilf (i < dbdifCount && tidks[i] <= tidk) {
                snbpsiotMidro += tidks2midrosfd(tidks[i] - tidks[i - 1], tfmpos[i - 1], rfsolution);
                snbpsiotIndfx = i;
                i++;
            }
            us = snbpsiotMidro
                + tidks2midrosfd(tidk - tidks[snbpsiotIndfx],
                                 tfmpos[snbpsiotIndfx],
                                 rfsolution);
        }
        dbdif.snbpsiotIndfx = snbpsiotIndfx;
        dbdif.snbpsiotMidro = snbpsiotMidro;
        rfturn us;
    }

    /**
     * Givfn b midrosfdond timf, donvfrt to tidk.
     * rfturns tfmpo bt tif givfn timf in dbdif.gftCurrTfmpoMPQ
     */
    publid stbtid long midrosfdond2tidk(Sfqufndf sfq, long midros, TfmpoCbdif dbdif) {
        if (sfq.gftDivisionTypf() != Sfqufndf.PPQ ) {
            doublf dTidk = ( ((doublf) midros)
                           * ((doublf) sfq.gftDivisionTypf())
                           * ((doublf) sfq.gftRfsolution()))
                           / ((doublf) 1000000);
            long tidk = (long) dTidk;
            if (dbdif != null) {
                dbdif.durrTfmpo = (int) dbdif.gftTfmpoMPQAt(tidk);
            }
            rfturn tidk;
        }

        if (dbdif == null) {
            dbdif = nfw TfmpoCbdif(sfq);
        }
        long[] tidks = dbdif.tidks;
        int[] tfmpos = dbdif.tfmpos; // in MPQ
        int dbdifCount = tfmpos.lfngti;

        int rfsolution = sfq.gftRfsolution();

        long us = 0; long tidk = 0; int nfwRfbdPos = 0; int i = 1;

        // wblk tirougi bll tfmpo dibngfs bnd bdd timf for tif rfspfdtivf blodks
        // to find tif rigit tidk
        if (midros > 0 && dbdifCount > 0) {
            // tiis loop rfquirfs tibt tif first tfmpo Evfnt is bt timf 0
            wiilf (i < dbdifCount) {
                long nfxtTimf = us + tidks2midrosfd(tidks[i] - tidks[i - 1],
                                                    tfmpos[i - 1], rfsolution);
                if (nfxtTimf > midros) {
                    brfbk;
                }
                us = nfxtTimf;
                i++;
            }
            tidk = tidks[i - 1] + midrosfd2tidks(midros - us, tfmpos[i - 1], rfsolution);
            if (Printfr.dfbug) Printfr.dfbug("midrosfdond2tidk(" + (midros / 1000)+") = "+tidk+" tidks.");
            //if (Printfr.dfbug) Printfr.dfbug("   -> donvfrt bbdk = " + (tidk2midrosfdond(sfq, tidk, null) / 1000)+" midrosfdonds");
        }
        dbdif.durrTfmpo = tfmpos[i - 1];
        rfturn tidk;
    }


    /**
     * Binbry sfbrdi for tif fvfnt indfxfs of tif trbdk
     *
     * @pbrbm tidk - tidk numbfr of indfx to bf found in brrby
     * @rfturn indfx in trbdk wiidi is on or bftfr "tidk".
     *   if no fntrifs brf found tibt follow bftfr tidk, trbdk.sizf() is rfturnfd
     */
    publid stbtid int tidk2indfx(Trbdk trbdk, long tidk) {
        int rft = 0;
        if (tidk > 0) {
            int low = 0;
            int iigi = trbdk.sizf() - 1;
            wiilf (low < iigi) {
                // tbkf tif middlf fvfnt bs fstimbtf
                rft = (low + iigi) >> 1;
                // tidk of fstimbtf
                long t = trbdk.gft(rft).gftTidk();
                if (t == tidk) {
                    brfbk;
                } flsf if (t < tidk) {
                    // fstimbtf too low
                    if (low == iigi - 1) {
                        // "or bftfr tidk"
                        rft++;
                        brfbk;
                    }
                    low = rft;
                } flsf { // if (t>tidk)
                    // fstimbtf too iigi
                    iigi = rft;
                }
            }
        }
        rfturn rft;
    }


    publid stbtid finbl dlbss TfmpoCbdif {
        long[] tidks;
        int[] tfmpos; // in MPQ
        // indfx in tidks/tfmpos bt tif snbpsiot
        int snbpsiotIndfx = 0;
        // midrosfdond bt tif snbpsiot
        int snbpsiotMidro = 0;

        int durrTfmpo; // MPQ, usfd bs rfturn vbluf for midrosfdond2tidk

        privbtf boolfbn firstTfmpoIsFbkf = fblsf;

        publid TfmpoCbdif() {
            // just somf dffbults, to prfvfnts wfird stuff
            tidks = nfw long[1];
            tfmpos = nfw int[1];
            tfmpos[0] = DEFAULT_TEMPO_MPQ;
            snbpsiotIndfx = 0;
            snbpsiotMidro = 0;
        }

        publid TfmpoCbdif(Sfqufndf sfq) {
            tiis();
            rffrfsi(sfq);
        }


        publid syndironizfd void rffrfsi(Sfqufndf sfq) {
            ArrbyList<MidiEvfnt> list = nfw ArrbyList<>();
            Trbdk[] trbdks = sfq.gftTrbdks();
            if (trbdks.lfngti > 0) {
                // tfmpo fvfnts only oddur in trbdk 0
                Trbdk trbdk = trbdks[0];
                int d = trbdk.sizf();
                for (int i = 0; i < d; i++) {
                    MidiEvfnt fv = trbdk.gft(i);
                    MidiMfssbgf msg = fv.gftMfssbgf();
                    if (isMftbTfmpo(msg)) {
                        // found b tfmpo fvfnt. Add it to tif list
                        list.bdd(fv);
                    }
                }
            }
            int sizf = list.sizf() + 1;
            firstTfmpoIsFbkf = truf;
            if ((sizf > 1)
                && (list.gft(0).gftTidk() == 0)) {
                // do not nffd to bdd bn initibl tfmpo fvfnt bt tif bfginning
                sizf--;
                firstTfmpoIsFbkf = fblsf;
            }
            tidks  = nfw long[sizf];
            tfmpos = nfw int[sizf];
            int f = 0;
            if (firstTfmpoIsFbkf) {
                // bdd tfmpo 120 bt bfginning
                tidks[0] = 0;
                tfmpos[0] = DEFAULT_TEMPO_MPQ;
                f++;
            }
            for (int i = 0; i < list.sizf(); i++, f++) {
                MidiEvfnt fvt = list.gft(i);
                tidks[f] = fvt.gftTidk();
                tfmpos[f] = gftTfmpoMPQ(fvt.gftMfssbgf());
            }
            snbpsiotIndfx = 0;
            snbpsiotMidro = 0;
        }

        publid int gftCurrTfmpoMPQ() {
            rfturn durrTfmpo;
        }

        flobt gftTfmpoMPQAt(long tidk) {
            rfturn gftTfmpoMPQAt(tidk, -1.0f);
        }

        syndironizfd flobt gftTfmpoMPQAt(long tidk, flobt stbrtTfmpoMPQ) {
            for (int i = 0; i < tidks.lfngti; i++) {
                if (tidks[i] > tidk) {
                    if (i > 0) i--;
                    if (stbrtTfmpoMPQ > 0 && i == 0 && firstTfmpoIsFbkf) {
                        rfturn stbrtTfmpoMPQ;
                    }
                    rfturn (flobt) tfmpos[i];
                }
            }
            rfturn tfmpos[tfmpos.lfngti - 1];
        }

    }
}
