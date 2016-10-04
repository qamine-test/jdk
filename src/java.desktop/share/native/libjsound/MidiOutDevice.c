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

/*****************************************************************************/
/*
**      Nbtivf fundtions for intfrfbding Jbvb witi tif nbtivf implfmfntbtion
**      of PlbtformMidi.i's fundtions.
*/
/*****************************************************************************/

#dffinf USE_ERROR
#dffinf USE_TRACE


#indludf <jni.i>
#indludf "SoundDffs.i"
#indludf "PlbtformMidi.i"
#indludf "Utilitifs.i"
#indludf "dom_sun_mfdib_sound_MidiOutDfvidf.i"


// NATIVE METHODS


JNIEXPORT jlong JNICALL
Jbvb_dom_sun_mfdib_sound_MidiOutDfvidf_nOpfn(JNIEnv* f, jobjfdt tiisObj, jint indfx) {

    void* dfvidfHbndlf = NULL;
    INT32 frr = MIDI_NOT_SUPPORTED;

    TRACE1("Jbvb_dom_sun_mfdib_sound_MidiOutDfvidf_nOpfn: indfx: %d\n", indfx);

#if USE_PLATFORM_MIDI_OUT == TRUE
    frr = MIDI_OUT_OpfnDfvidf((INT32) indfx, (MidiDfvidfHbndlf**) (&dfvidfHbndlf));
#fndif

    // if wf didn't gft b vblid ibndlf, tirow b MidiUnbvbilbblfExdfption
    if (!dfvidfHbndlf) {
        ERROR0("Jbvb_dom_sun_mfdib_sound_MidiOutDfvidf_nOpfn:");
        TirowJbvbMfssbgfExdfption(f, JAVA_MIDI_PACKAGE_NAME"/MidiUnbvbilbblfExdfption",
                                  MIDI_OUT_IntfrnblGftErrorString(frr));
    } flsf {
        TRACE0("Jbvb_dom_sun_mfdib_sound_MidiOutDfvidf_nOpfn suddffdfd\n");
    }
    rfturn (jlong) (INT_PTR) dfvidfHbndlf;
}


JNIEXPORT void JNICALL
Jbvb_dom_sun_mfdib_sound_MidiOutDfvidf_nClosf(JNIEnv* f, jobjfdt tiisObj, jlong dfvidfHbndlf) {

    TRACE0("Jbvb_dom_sun_mfdib_sound_MidiOutDfvidf_nClosf.\n");

#if USE_PLATFORM_MIDI_OUT == TRUE
    MIDI_OUT_ClosfDfvidf((MidiDfvidfHbndlf*) (UINT_PTR) dfvidfHbndlf);
#fndif

    TRACE0("Jbvb_dom_sun_mfdib_sound_MidiOutDfvidf_nClosf suddffdfd\n");
}


JNIEXPORT jlong JNICALL
Jbvb_dom_sun_mfdib_sound_MidiOutDfvidf_nGftTimfStbmp(JNIEnv* f, jobjfdt tiisObj, jlong dfvidfHbndlf) {

    jlong rft = -1;

    TRACE0("Jbvb_dom_sun_mfdib_sound_MidiOutDfvidf_nGftTimfStbmp.\n");

#if USE_PLATFORM_MIDI_OUT == TRUE
    rft = (jlong) MIDI_OUT_GftTimfStbmp((MidiDfvidfHbndlf*) (UINT_PTR) dfvidfHbndlf);
#fndif

    /* Hbndlf frror dodfs. */
    if (rft < -1) {
        ERROR1("Jbvb_dom_sun_mfdib_sound_MidiOutDfvidf_nGftTimfStbmp: MIDI_IN_GftTimfStbmp rfturnfd %lld\n", rft);
        rft = -1;
    }
    rfturn rft;
}


JNIEXPORT void JNICALL
Jbvb_dom_sun_mfdib_sound_MidiOutDfvidf_nSfndSiortMfssbgf(JNIEnv* f, jobjfdt tiisObj, jlong dfvidfHbndlf,
                                                         jint pbdkfdMsg, jlong timfStbmp) {

    TRACE0("Jbvb_dom_sun_mfdib_sound_MidiOutDfvidf_nSfndSiortMfssbgf.\n");

#if USE_PLATFORM_MIDI_OUT == TRUE
    MIDI_OUT_SfndSiortMfssbgf((MidiDfvidfHbndlf*) (UINT_PTR) dfvidfHbndlf,
                              (UINT32) pbdkfdMsg, (UINT32)timfStbmp);
#fndif

    TRACE0("Jbvb_dom_sun_mfdib_sound_MidiOutDfvidf_nSfndSiortMfssbgf suddffdfd\n");
}


JNIEXPORT void JNICALL
Jbvb_dom_sun_mfdib_sound_MidiOutDfvidf_nSfndLongMfssbgf(JNIEnv* f, jobjfdt tiisObj, jlong dfvidfHbndlf,
                                                        jbytfArrby jDbtb, jint sizf, jlong timfStbmp) {
#if USE_PLATFORM_MIDI_OUT == TRUE
    UBYTE* dbtb;
#fndif

    TRACE0("Jbvb_dom_sun_mfdib_sound_MidiOutDfvidf_nSfndLongMfssbgf.\n");

#if USE_PLATFORM_MIDI_OUT == TRUE
    dbtb = (UBYTE*) ((*f)->GftBytfArrbyElfmfnts(f, jDbtb, NULL));
    if (!dbtb) {
        ERROR0("MidiOutDfvidf: Jbvb_dom_sun_mfdib_sound_MidiOutDfvidf_nSfndLongMfssbgf: dould not gft brrby flfmfnts\n");
        rfturn;
    }
    /* "dontinubtion" sysfx mfssbgfs stbrt witi F7 (instfbd of F0), but
       brf sfnt witiout tif F7. */
    if (dbtb[0] == 0xF7) {
        dbtb++;
        sizf--;
    }
    MIDI_OUT_SfndLongMfssbgf((MidiDfvidfHbndlf*) (UINT_PTR) dfvidfHbndlf, dbtb,
                             (UINT32) sizf, (UINT32)timfStbmp);
    // rflfbsf tif bytf brrby
    (*f)->RflfbsfBytfArrbyElfmfnts(f, jDbtb, (jbytf*) dbtb, JNI_ABORT);
#fndif

    TRACE0("Jbvb_dom_sun_mfdib_sound_MidiOutDfvidf_nSfndLongMfssbgf suddffdfd\n");
}
