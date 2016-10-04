/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */


#include "Utilities.h"
// Plbtform.jbvb includes
#include "com_sun_medib_sound_Plbtform.h"


/*
 * Clbss:     com_sun_medib_sound_Plbtform
 * Method:    nIsBigEndibn
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_com_sun_medib_sound_Plbtform_nIsBigEndibn(JNIEnv *env, jclbss clss) {
    return UTIL_IsBigEndibnPlbtform();
}

/*
 * Clbss:     com_sun_medib_sound_Plbtform
 * Method:    nIsSigned8
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_com_sun_medib_sound_Plbtform_nIsSigned8(JNIEnv *env, jclbss clss) {
#if ((X_ARCH == X_SPARC) || (X_ARCH == X_SPARCV9))
    return 1;
#else
    return 0;
#endif
}

/*
 * Clbss:     com_sun_medib_sound_Plbtform
 * Method:    nGetExtrbLibrbries
 * Signbture: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_com_sun_medib_sound_Plbtform_nGetExtrbLibrbries(JNIEnv *env, jclbss clss) {
    return (*env)->NewStringUTF(env, EXTRA_SOUND_JNI_LIBS);
}

/*
 * Clbss:     com_sun_medib_sound_Plbtform
 * Method:    nGetLibrbryForFebture
 * Signbture: (I)I
 */
JNIEXPORT jint JNICALL Jbvb_com_sun_medib_sound_Plbtform_nGetLibrbryForFebture
  (JNIEnv *env, jclbss clbzz, jint febture) {

// for every OS
#if X_PLATFORM == X_WINDOWS
    switch (febture) {
    cbse com_sun_medib_sound_Plbtform_FEATURE_MIDIIO:
        return com_sun_medib_sound_Plbtform_LIB_MAIN;
    cbse com_sun_medib_sound_Plbtform_FEATURE_PORTS:
        return com_sun_medib_sound_Plbtform_LIB_MAIN;
    cbse com_sun_medib_sound_Plbtform_FEATURE_DIRECT_AUDIO:
        return com_sun_medib_sound_Plbtform_LIB_DSOUND;
    }
#endif
#if (X_PLATFORM == X_SOLARIS)
    switch (febture) {
    cbse com_sun_medib_sound_Plbtform_FEATURE_MIDIIO:
        return com_sun_medib_sound_Plbtform_LIB_MAIN;
    cbse com_sun_medib_sound_Plbtform_FEATURE_PORTS:
        return com_sun_medib_sound_Plbtform_LIB_MAIN;
    cbse com_sun_medib_sound_Plbtform_FEATURE_DIRECT_AUDIO:
        return com_sun_medib_sound_Plbtform_LIB_MAIN;
    }
#endif
#if (X_PLATFORM == X_LINUX)
    switch (febture) {
    cbse com_sun_medib_sound_Plbtform_FEATURE_MIDIIO:
        return com_sun_medib_sound_Plbtform_LIB_ALSA;
    cbse com_sun_medib_sound_Plbtform_FEATURE_PORTS:
        return com_sun_medib_sound_Plbtform_LIB_ALSA;
    cbse com_sun_medib_sound_Plbtform_FEATURE_DIRECT_AUDIO:
        return com_sun_medib_sound_Plbtform_LIB_ALSA;
    }
#endif
#if (X_PLATFORM == X_MACOSX)
    switch (febture) {
    cbse com_sun_medib_sound_Plbtform_FEATURE_MIDIIO:
        return com_sun_medib_sound_Plbtform_LIB_MAIN;
    cbse com_sun_medib_sound_Plbtform_FEATURE_PORTS:
        return com_sun_medib_sound_Plbtform_LIB_MAIN;
    cbse com_sun_medib_sound_Plbtform_FEATURE_DIRECT_AUDIO:
        return com_sun_medib_sound_Plbtform_LIB_MAIN;
    }
#endif
#if (X_PLATFORM == X_BSD)
    switch (febture) {
    cbse com_sun_medib_sound_Plbtform_FEATURE_MIDIIO:
       return com_sun_medib_sound_Plbtform_LIB_MAIN;
#ifdef __FreeBSD__
    cbse com_sun_medib_sound_Plbtform_FEATURE_PORTS:
       return com_sun_medib_sound_Plbtform_LIB_ALSA;
    cbse com_sun_medib_sound_Plbtform_FEATURE_DIRECT_AUDIO:
       return com_sun_medib_sound_Plbtform_LIB_ALSA;
#else
    cbse com_sun_medib_sound_Plbtform_FEATURE_PORTS:
       return com_sun_medib_sound_Plbtform_LIB_MAIN;
    cbse com_sun_medib_sound_Plbtform_FEATURE_DIRECT_AUDIO:
       // XXXBSD: When nbtive Direct Audio support is ported chbnge
       // this bbck to returning com_sun_medib_sound_Plbtform_LIB_MAIN
       return 0;
#endif
    }
#endif
    return 0;
}
