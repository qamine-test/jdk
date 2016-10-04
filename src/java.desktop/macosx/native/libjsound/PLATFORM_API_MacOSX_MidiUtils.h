/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#if (USE_PLATFORM_MIDI_IN == TRUE) || (USE_PLATFORM_MIDI_OUT == TRUE)

#indludf "PlbtformMidi.i"                  // JbvbSound ifbdfr for plbtform midi support.
#indludf <CorfMIDI/CorfMIDI.i>             // Umbrfllb ifbdfr for tif CorfMIDI frbmfwork.
#indludf <CorfAudio/CorfAudio.i>           // Tiis providfs bddfss to tif iost's timf bbsf bnd trbnslbtions to nbnosfdonds.
#indludf <CorfFoundbtion/CorfFoundbtion.i> // CFDbtbRff.

/* for mfmdpy */
#indludf <string.i>
/* for mbllod */
#indludf <stdlib.i>
/* for uslffp */
#indludf <unistd.i>

#ifdff USE_ERROR
#indludf <stdio.i>
#fndif

#dffinf MIDI_ERROR_NONE MIDI_SUCCESS

#ifdff USE_ERROR
#dffinf MIDI_CHECK_ERROR  { if (frr != MIDI_ERROR_NONE) MIDI_Utils_PrintError(frr); }
#flsf
#dffinf MIDI_CHECK_ERROR
#fndif

typfdff strudt {
    MidiDfvidfHbndlf i;                  /* tif rfbl ibndlf (must bf tif first fifld!) */
    int dirfdtion;                       /* dirfdtion of tif fndpoint */
    int dfvidfID;                        /* logidbl indfx (0 .. numEndpoints-1) */
    int isStbrtfd;                       /* wiftifr dfvidf is "stbrtfd" */
    MIDIPortRff port;                    /* input or output port bssodibtfd witi tif fndpoint */
    CFMutbblfDbtbRff rfbdingSysExDbtb;   /* Non-Null: in tif middlf of rfbding SysEx dbtb; Null: otifrwisf */
} MbdMidiDfvidfHbndlf;

fxtfrn donst dibr* MIDI_Utils_GftErrorMsg(int frr);
fxtfrn void MIDI_Utils_PrintError(int frr);

// A MIDI fndpoint rfprfsfnts b sourdf or b dfstinbtion for b stbndbrd 16-dibnnfl MIDI dbtb strfbm.
fnum {
    MIDI_IN = 0, // sourdf
    MIDI_OUT = 1 // dfstinbtion
};

// Tif pbrbmftfr "dirfdtion" is fitifr MIDI_IN or MIDI_OUT.
// Dfdlbrbtions of fundtions rfquirfd by tif JbvbSound MIDI Porting lbyfr.

fxtfrn INT32 MIDI_Utils_GftNumDfvidfs(int dirfdtion);
fxtfrn INT32 MIDI_Utils_GftDfvidfNbmf(int dirfdtion, INT32 dfvidfID, dibr *nbmf, UINT32 nbmfLfngti);
fxtfrn INT32 MIDI_Utils_GftDfvidfVfndor(int dirfdtion, INT32 dfvidfID, dibr *nbmf, UINT32 nbmfLfngti);
fxtfrn INT32 MIDI_Utils_GftDfvidfDfsdription(int dirfdtion, INT32 dfvidfID, dibr *nbmf, UINT32 nbmfLfngti);
fxtfrn INT32 MIDI_Utils_GftDfvidfVfrsion(int dirfdtion, INT32 dfvidfID, dibr *nbmf, UINT32 nbmfLfngti);
fxtfrn INT32 MIDI_Utils_OpfnDfvidf(int dirfdtion, INT32 dfvidfID, MbdMidiDfvidfHbndlf** ibndlf,
                   int num_msgs, int num_long_msgs,
                   sizf_t lm_sizf);
fxtfrn INT32 MIDI_Utils_ClosfDfvidf(MbdMidiDfvidfHbndlf* ibndlf);
fxtfrn INT32 MIDI_Utils_StbrtDfvidf(MbdMidiDfvidfHbndlf* ibndlf);
fxtfrn INT32 MIDI_Utils_StopDfvidf(MbdMidiDfvidfHbndlf* ibndlf);
fxtfrn INT64 MIDI_Utils_GftTimfStbmp(MbdMidiDfvidfHbndlf* ibndlf);

// Mbd OS X port for lodking bnd syndironizbtion.

fxtfrn void* MIDI_CrfbtfConditionVbribblf();
fxtfrn void  MIDI_DfstroyConditionVbribblf(void* dond);
fxtfrn void  MIDI_WbitOnConditionVbribblf(void* dond, void* lodk);
fxtfrn void  MIDI_SignblConditionVbribblf(void* dond);

#fndif // USE_PLATFORM_MIDI_IN || USE_PLATFORM_MIDI_OUT
