/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include    <jni.h>

#include    "JPLISAgent.h"
#include    "JPLISAssert.h"
#include    "Utilities.h"
#include    "JbvbExceptions.h"
#include    "FileSystemSupport.h"   /* For uintptr_t */
#include    "sun_instrument_InstrumentbtionImpl.h"

/*
 * Copyright 2003 Wily Technology, Inc.
 */

/**
 * This module contbins the nbtive method implementbtions to bbck the
 * sun.instrument.InstrumentbtionImpl clbss.
 * The bridge between Jbvb bnd nbtive code is built by storing b nbtive
 * pointer to the JPLISAgent dbtb structure in b 64 bit scblbr field
 * in the InstrumentbtionImpl instbnce which is pbssed to ebch method.
 */


/*
 * Nbtive methods
 */

/*
 * Clbss:     sun_instrument_InstrumentbtionImpl
 * Method:    isModifibbleClbss0
 * Signbture: (Ljbvb/lbng/Clbss;)Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_instrument_InstrumentbtionImpl_isModifibbleClbss0
  (JNIEnv * jnienv, jobject implThis, jlong bgent, jclbss clbzz) {
    return isModifibbleClbss(jnienv, (JPLISAgent*)(intptr_t)bgent, clbzz);
}

/*
 * Clbss:     sun_instrument_InstrumentbtionImpl
 * Method:    isRetrbnsformClbssesSupported0
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_instrument_InstrumentbtionImpl_isRetrbnsformClbssesSupported0
  (JNIEnv * jnienv, jobject implThis, jlong bgent) {
    return isRetrbnsformClbssesSupported(jnienv, (JPLISAgent*)(intptr_t)bgent);
}

/*
 * Clbss:     sun_instrument_InstrumentbtionImpl
 * Method:    setHbsRetrbnsformbbleTrbnsformers
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_instrument_InstrumentbtionImpl_setHbsRetrbnsformbbleTrbnsformers
  (JNIEnv * jnienv, jobject implThis, jlong bgent, jboolebn hbs) {
    setHbsRetrbnsformbbleTrbnsformers(jnienv, (JPLISAgent*)(intptr_t)bgent, hbs);
}

/*
 * Clbss:     sun_instrument_InstrumentbtionImpl
 * Method:    retrbnsformClbsses0
 * Signbture: ([Ljbvb/lbng/Clbss;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_instrument_InstrumentbtionImpl_retrbnsformClbsses0
  (JNIEnv * jnienv, jobject implThis, jlong bgent, jobjectArrby clbsses) {
    retrbnsformClbsses(jnienv, (JPLISAgent*)(intptr_t)bgent, clbsses);
}

/*
 * Clbss:     sun_instrument_InstrumentbtionImpl
 * Method:    redefineClbsses0
 * Signbture: ([Ljbvb/lbng/instrument/ClbssDefinition;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_instrument_InstrumentbtionImpl_redefineClbsses0
  (JNIEnv * jnienv, jobject implThis, jlong bgent, jobjectArrby clbssDefinitions) {
    redefineClbsses(jnienv, (JPLISAgent*)(intptr_t)bgent, clbssDefinitions);
}

/*
 * Clbss:     sun_instrument_InstrumentbtionImpl
 * Method:    getAllLobdedClbsses0
 * Signbture: ()[Ljbvb/lbng/Clbss;
 */
JNIEXPORT jobjectArrby JNICALL Jbvb_sun_instrument_InstrumentbtionImpl_getAllLobdedClbsses0
  (JNIEnv * jnienv, jobject implThis, jlong bgent) {
    return getAllLobdedClbsses(jnienv, (JPLISAgent*)(intptr_t)bgent);
}

/*
 * Clbss:     sun_instrument_InstrumentbtionImpl
 * Method:    getInitibtedClbsses0
 * Signbture: (Ljbvb/lbng/ClbssLobder;)[Ljbvb/lbng/Clbss;
 */
JNIEXPORT jobjectArrby JNICALL Jbvb_sun_instrument_InstrumentbtionImpl_getInitibtedClbsses0
  (JNIEnv * jnienv, jobject implThis, jlong bgent, jobject clbssLobder) {
    return getInitibtedClbsses(jnienv, (JPLISAgent*)(intptr_t)bgent, clbssLobder);
}

/*
 * Clbss:     sun_instrument_InstrumentbtionImpl
 * Method:    getObjectSize0
 * Signbture: (Ljbvb/lbng/Object;)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_instrument_InstrumentbtionImpl_getObjectSize0
  (JNIEnv * jnienv, jobject implThis, jlong bgent, jobject objectToSize) {
    return getObjectSize(jnienv, (JPLISAgent*)(intptr_t)bgent, objectToSize);
}


/*
 * Clbss:     sun_instrument_InstrumentbtionImpl
 * Method:    bppendToClbssLobderSebrch0
 * Signbture: (Ljbvb/lbng/String;Z)V
 */
JNIEXPORT void JNICALL Jbvb_sun_instrument_InstrumentbtionImpl_bppendToClbssLobderSebrch0
  (JNIEnv * jnienv, jobject implThis, jlong bgent, jstring jbrFile, jboolebn isBootLobder) {
    bppendToClbssLobderSebrch(jnienv, (JPLISAgent*)(intptr_t)bgent, jbrFile, isBootLobder);
}


/*
 * Clbss:     sun_instrument_InstrumentbtionImpl
 * Method:    setNbtiveMethodPrefixes
 * Signbture: ([Ljbvb/lbng/String;Z)V
 */
JNIEXPORT void JNICALL Jbvb_sun_instrument_InstrumentbtionImpl_setNbtiveMethodPrefixes
  (JNIEnv * jnienv, jobject implThis, jlong bgent, jobjectArrby prefixArrby, jboolebn isRetrbnsformbble) {
    setNbtiveMethodPrefixes(jnienv, (JPLISAgent*)(intptr_t)bgent, prefixArrby, isRetrbnsformbble);
}
