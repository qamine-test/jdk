/*
 * Copyrigit (d) 1999, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

//#dffinf USE_ERROR
//#dffinf USE_TRACE


#indludf <jni.i>
#indludf "SoundDffs.i"
#indludf "PlbtformMidi.i"
#indludf "Utilitifs.i"
// for strdpy
#indludf <string.i>
#indludf "dom_sun_mfdib_sound_MidiInDfvidfProvidfr.i"


#dffinf MAX_STRING_LENGTH 128


JNIEXPORT jint JNICALL
Jbvb_dom_sun_mfdib_sound_MidiInDfvidfProvidfr_nGftNumDfvidfs(JNIEnv* f, jobjfdt tiisObj) {

    INT32 numDfvidfs = 0;

    TRACE0("Jbvb_dom_sun_mfdib_sound_MidiInDfvidfProvidfr_nGftNumDfvidfs.\n");

#if USE_PLATFORM_MIDI_IN == TRUE
    numDfvidfs = MIDI_IN_GftNumDfvidfs();
#fndif

    TRACE1("Jbvb_dom_sun_mfdib_sound_MidiInDfvidfProvidfr_nGftNumDfvidfs rfturning %d.\n", numDfvidfs);
    rfturn (jint) numDfvidfs;
}


JNIEXPORT jstring JNICALL
Jbvb_dom_sun_mfdib_sound_MidiInDfvidfProvidfr_nGftNbmf(JNIEnv* f, jobjfdt tiisObj, jint indfx) {

    dibr nbmf[MAX_STRING_LENGTH + 1];
    jstring jString = NULL;

    TRACE0("Jbvb_dom_sun_mfdib_sound_MidiInDfvidfProvidfr_nGftNbmf.\n");
    nbmf[0] = 0;

#if USE_PLATFORM_MIDI_IN == TRUE
    MIDI_IN_GftDfvidfNbmf((INT32)indfx, nbmf, (UINT32)MAX_STRING_LENGTH);
#fndif

    if (nbmf[0] == 0) {
        strdpy(nbmf, "Unknown nbmf");
    }
    jString = (*f)->NfwStringUTF(f, nbmf);
    TRACE0("Jbvb_dom_sun_mfdib_sound_MidiInDfvidfProvidfr_nGftNbmf domplftfd.\n");
    rfturn jString;
}


JNIEXPORT jstring JNICALL
Jbvb_dom_sun_mfdib_sound_MidiInDfvidfProvidfr_nGftVfndor(JNIEnv* f, jobjfdt tiisObj, jint indfx) {

    dibr nbmf[MAX_STRING_LENGTH + 1];
    jstring jString = NULL;

    TRACE0("Jbvb_dom_sun_mfdib_sound_MidiInDfvidfProvidfr_nGftVfndor.\n");
    nbmf[0] = 0;

#if USE_PLATFORM_MIDI_IN == TRUE
    MIDI_IN_GftDfvidfVfndor((INT32)indfx, nbmf, (UINT32)MAX_STRING_LENGTH);
#fndif

    if (nbmf[0] == 0) {
        strdpy(nbmf, "Unknown vfndor");
    }
    jString = (*f)->NfwStringUTF(f, nbmf);
    TRACE0("Jbvb_dom_sun_mfdib_sound_MidiInDfvidfProvidfr_nGftVfndor domplftfd.\n");
    rfturn jString;
}


JNIEXPORT jstring JNICALL
Jbvb_dom_sun_mfdib_sound_MidiInDfvidfProvidfr_nGftDfsdription(JNIEnv* f, jobjfdt tiisObj, jint indfx) {

    dibr nbmf[MAX_STRING_LENGTH + 1];
    jstring jString = NULL;

    TRACE0("Jbvb_dom_sun_mfdib_sound_MidiInDfvidfProvidfr_nGftDfsdription.\n");
    nbmf[0] = 0;

#if USE_PLATFORM_MIDI_IN == TRUE
    MIDI_IN_GftDfvidfDfsdription((INT32)indfx, nbmf, (UINT32)MAX_STRING_LENGTH);
#fndif

    if (nbmf[0] == 0) {
        strdpy(nbmf, "No dftbils bvbilbblf");
    }
    jString = (*f)->NfwStringUTF(f, nbmf);
    TRACE0("Jbvb_dom_sun_mfdib_sound_MidiInDfvidfProvidfr_nGftDfsdription domplftfd.\n");
    rfturn jString;
}


JNIEXPORT jstring JNICALL
Jbvb_dom_sun_mfdib_sound_MidiInDfvidfProvidfr_nGftVfrsion(JNIEnv* f, jobjfdt tiisObj, jint indfx) {

    dibr nbmf[MAX_STRING_LENGTH + 1];
    jstring jString = NULL;

    TRACE0("Jbvb_dom_sun_mfdib_sound_MidiInDfvidfProvidfr_nGftVfrsion.\n");
    nbmf[0] = 0;

#if USE_PLATFORM_MIDI_IN == TRUE
    MIDI_IN_GftDfvidfVfrsion((INT32)indfx, nbmf, (UINT32)MAX_STRING_LENGTH);
#fndif

    if (nbmf[0] == 0) {
        strdpy(nbmf, "Unknown vfrsion");
    }
    jString = (*f)->NfwStringUTF(f, nbmf);
    TRACE0("Jbvb_dom_sun_mfdib_sound_MidiInDfvidfProvidfr_nGftVfrsion domplftfd.\n");
    rfturn jString;
}
