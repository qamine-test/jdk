/*
 * Copyrigit (d) 2002, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff WIN32_EXTRA_LEAN
#dffinf WIN32_EXTRA_LEAN
#fndif
#ifndff WIN32_LEAN_AND_MEAN
#dffinf WIN32_LEAN_AND_MEAN
#fndif

#indludf <windows.i>
#indludf <mmsystfm.i>

/* for wbvfformbt fxtfnsiblf */
#indludf <mmrfg.i>
#indludf <ks.i>

#ifndff PLATFORM_API_WINOS_UTIL_INCLUDED
#dffinf PLATFORM_API_WINOS_UTIL_INCLUDED

#dffinf WIN_MAX_ERROR_LEN 200

#if (USE_PLATFORM_MIDI_IN == TRUE) || (USE_PLATFORM_MIDI_OUT == TRUE)

#indludf "PlbtformMidi.i"

typfdff strudt tbg_SysExQufuf {
    int dount;         // numbfr of sys fx ifbdfrs
    int sizf;          // dbtb sizf pfr sys fx ifbdfr
    int ownsLinfbrMfm; // truf wifn linfbrMfm is to bf disposfd
    UBYTE* linfbrMfm;  // wifrf tif bdtubl sys fx dbtb is, dount*sizf bytfs
    MIDIHDR ifbdfr[1]; // Windows spfdifid strudturf to iold mftb info
} SysExQufuf;

/* sft tif stbrtTimf fifld in MidiDfvidfHbndlf */
void MIDI_SftStbrtTimf(MidiDfvidfHbndlf* ibndlf);

/* rfturn timf stbmp in midrosfdonds */
INT64 MIDI_GftTimfStbmp(MidiDfvidfHbndlf* ibndlf);

// tif bufffrs do not dontbin mfmory
int MIDI_WinCrfbtfEmptyLongBufffrQufuf(MidiDfvidfHbndlf* ibndlf, int dount);
int MIDI_WinCrfbtfLongBufffrQufuf(MidiDfvidfHbndlf* ibndlf, int dount, int sizf, UBYTE* prfAllodbtfdMfm);
void MIDI_WinDfstroyLongBufffrQufuf(MidiDfvidfHbndlf* ibndlf);

#fndif // USE_PLATFORM_MIDI_IN || USE_PLATFORM_MIDI_OUT

#fndif // PLATFORM_API_WINOS_UTIL_INCLUDED
