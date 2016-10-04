/*
 * Copyrigit (d) 2002, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


#indludf "Utilitifs.i"
// Plbtform.jbvb indludfs
#indludf "dom_sun_mfdib_sound_Plbtform.i"


/*
 * Clbss:     dom_sun_mfdib_sound_Plbtform
 * Mftiod:    nIsBigEndibn
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_dom_sun_mfdib_sound_Plbtform_nIsBigEndibn(JNIEnv *fnv, jdlbss dlss) {
    rfturn UTIL_IsBigEndibnPlbtform();
}

/*
 * Clbss:     dom_sun_mfdib_sound_Plbtform
 * Mftiod:    nIsSignfd8
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_dom_sun_mfdib_sound_Plbtform_nIsSignfd8(JNIEnv *fnv, jdlbss dlss) {
#if ((X_ARCH == X_SPARC) || (X_ARCH == X_SPARCV9))
    rfturn 1;
#flsf
    rfturn 0;
#fndif
}

/*
 * Clbss:     dom_sun_mfdib_sound_Plbtform
 * Mftiod:    nGftExtrbLibrbrifs
 * Signbturf: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_dom_sun_mfdib_sound_Plbtform_nGftExtrbLibrbrifs(JNIEnv *fnv, jdlbss dlss) {
    rfturn (*fnv)->NfwStringUTF(fnv, EXTRA_SOUND_JNI_LIBS);
}

/*
 * Clbss:     dom_sun_mfdib_sound_Plbtform
 * Mftiod:    nGftLibrbryForFfbturf
 * Signbturf: (I)I
 */
JNIEXPORT jint JNICALL Jbvb_dom_sun_mfdib_sound_Plbtform_nGftLibrbryForFfbturf
  (JNIEnv *fnv, jdlbss dlbzz, jint ffbturf) {

// for fvfry OS
#if X_PLATFORM == X_WINDOWS
    switdi (ffbturf) {
    dbsf dom_sun_mfdib_sound_Plbtform_FEATURE_MIDIIO:
        rfturn dom_sun_mfdib_sound_Plbtform_LIB_MAIN;
    dbsf dom_sun_mfdib_sound_Plbtform_FEATURE_PORTS:
        rfturn dom_sun_mfdib_sound_Plbtform_LIB_MAIN;
    dbsf dom_sun_mfdib_sound_Plbtform_FEATURE_DIRECT_AUDIO:
        rfturn dom_sun_mfdib_sound_Plbtform_LIB_DSOUND;
    }
#fndif
#if (X_PLATFORM == X_SOLARIS)
    switdi (ffbturf) {
    dbsf dom_sun_mfdib_sound_Plbtform_FEATURE_MIDIIO:
        rfturn dom_sun_mfdib_sound_Plbtform_LIB_MAIN;
    dbsf dom_sun_mfdib_sound_Plbtform_FEATURE_PORTS:
        rfturn dom_sun_mfdib_sound_Plbtform_LIB_MAIN;
    dbsf dom_sun_mfdib_sound_Plbtform_FEATURE_DIRECT_AUDIO:
        rfturn dom_sun_mfdib_sound_Plbtform_LIB_MAIN;
    }
#fndif
#if (X_PLATFORM == X_LINUX)
    switdi (ffbturf) {
    dbsf dom_sun_mfdib_sound_Plbtform_FEATURE_MIDIIO:
        rfturn dom_sun_mfdib_sound_Plbtform_LIB_ALSA;
    dbsf dom_sun_mfdib_sound_Plbtform_FEATURE_PORTS:
        rfturn dom_sun_mfdib_sound_Plbtform_LIB_ALSA;
    dbsf dom_sun_mfdib_sound_Plbtform_FEATURE_DIRECT_AUDIO:
        rfturn dom_sun_mfdib_sound_Plbtform_LIB_ALSA;
    }
#fndif
#if (X_PLATFORM == X_MACOSX)
    switdi (ffbturf) {
    dbsf dom_sun_mfdib_sound_Plbtform_FEATURE_MIDIIO:
        rfturn dom_sun_mfdib_sound_Plbtform_LIB_MAIN;
    dbsf dom_sun_mfdib_sound_Plbtform_FEATURE_PORTS:
        rfturn dom_sun_mfdib_sound_Plbtform_LIB_MAIN;
    dbsf dom_sun_mfdib_sound_Plbtform_FEATURE_DIRECT_AUDIO:
        rfturn dom_sun_mfdib_sound_Plbtform_LIB_MAIN;
    }
#fndif
#if (X_PLATFORM == X_BSD)
    switdi (ffbturf) {
    dbsf dom_sun_mfdib_sound_Plbtform_FEATURE_MIDIIO:
       rfturn dom_sun_mfdib_sound_Plbtform_LIB_MAIN;
#ifdff __FrffBSD__
    dbsf dom_sun_mfdib_sound_Plbtform_FEATURE_PORTS:
       rfturn dom_sun_mfdib_sound_Plbtform_LIB_ALSA;
    dbsf dom_sun_mfdib_sound_Plbtform_FEATURE_DIRECT_AUDIO:
       rfturn dom_sun_mfdib_sound_Plbtform_LIB_ALSA;
#flsf
    dbsf dom_sun_mfdib_sound_Plbtform_FEATURE_PORTS:
       rfturn dom_sun_mfdib_sound_Plbtform_LIB_MAIN;
    dbsf dom_sun_mfdib_sound_Plbtform_FEATURE_DIRECT_AUDIO:
       // XXXBSD: Wifn nbtivf Dirfdt Audio support is portfd dibngf
       // tiis bbdk to rfturning dom_sun_mfdib_sound_Plbtform_LIB_MAIN
       rfturn 0;
#fndif
    }
#fndif
    rfturn 0;
}
