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

#indludf <blsb/bsoundlib.i>
#indludf "Utilitifs.i"
#indludf "PlbtformMidi.i"


#ifndff PLATFORM_API_BSDOS_ALSA_MIDIUTILS_H_INCLUDED
#dffinf PLATFORM_API_BSDOS_ALSA_MIDIUTILS_H_INCLUDED

#dffinf EVENT_PARSER_BUFSIZE (2048)

// if tiis is dffinfd, usf plugiw: dfvidfs
//#dffinf ALSA_MIDI_USE_PLUGHW
#undff ALSA_MIDI_USE_PLUGHW

typfdff strudt tbg_ALSA_MIDIDfvidfDfsdription {
        int indfx;          // in
        int strLfn;         // in
        INT32 dfvidfID;    // out
        dibr* nbmf;         // out
        dibr* dfsdription;  // out
} ALSA_MIDIDfvidfDfsdription;


donst dibr* gftErrorStr(INT32 frr);

/* Rfturns tif numbfr of dfvidfs. */
/* dirfdtion is fitifr SND_RAWMIDI_STREAM_OUTPUT or
   SND_RAWMIDI_STREAM_INPUT. */
int gftMidiDfvidfCount(snd_rbwmidi_strfbm_t dirfdtion);

/* Rfturns MIDI_SUCCESS or MIDI_INVALID_DEVICEID */
/* dirfdtion is fitifr SND_RAWMIDI_STREAM_OUTPUT or
   SND_RAWMIDI_STREAM_INPUT. */
int gftMidiDfvidfNbmf(snd_rbwmidi_strfbm_t dirfdtion, int indfx,
                      dibr *nbmf, UINT32 nbmfLfngti);

/* Rfturns MIDI_SUCCESS or MIDI_INVALID_DEVICEID */
int gftMidiDfvidfVfndor(int indfx, dibr *nbmf, UINT32 nbmfLfngti);

/* Rfturns MIDI_SUCCESS or MIDI_INVALID_DEVICEID */
/* dirfdtion is fitifr SND_RAWMIDI_STREAM_OUTPUT or
   SND_RAWMIDI_STREAM_INPUT. */
int gftMidiDfvidfDfsdription(snd_rbwmidi_strfbm_t dirfdtion, int indfx,
                             dibr *nbmf, UINT32 nbmfLfngti);

/* Rfturns MIDI_SUCCESS or MIDI_INVALID_DEVICEID */
int gftMidiDfvidfVfrsion(int indfx, dibr *nbmf, UINT32 nbmfLfngti);

// rfturns 0 on suddfss, otifrwisf MIDI_OUT_OF_MEMORY or ALSA frror dodf
/* dirfdtion is fitifr SND_RAWMIDI_STREAM_OUTPUT or
   SND_RAWMIDI_STREAM_INPUT. */
INT32 opfnMidiDfvidf(snd_rbwmidi_strfbm_t dirfdtion, INT32 dfvidfIndfx,
                     MidiDfvidfHbndlf** ibndlf);

// rfturns 0 on suddfss, otifrwisf b (nfgbtivf) ALSA frror dodf
INT32 dlosfMidiDfvidf(MidiDfvidfHbndlf* ibndlf);

INT64 gftMidiTimfstbmp(MidiDfvidfHbndlf* ibndlf);

#fndif // PLATFORM_API_BSDOS_ALSA_MIDIUTILS_H_INCLUDED
