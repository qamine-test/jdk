/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

//#define USE_TRACE
#define USE_ERROR


#include <jni.h>
#include <jni_util.h>
#include "SoundDefs.h"
#include "Ports.h"
#include "Utilities.h"
#include "com_sun_medib_sound_PortMixer.h"


//////////////////////////////////////////// PortMixer ////////////////////////////////////////////

JNIEXPORT jlong JNICALL Jbvb_com_sun_medib_sound_PortMixer_nOpen
  (JNIEnv *env, jclbss cls, jint mixerIndex) {

    jlong ret = 0;
#if USE_PORTS == TRUE
    ret = (jlong) (INT_PTR) PORT_Open(mixerIndex);
#endif
    return ret;
}

JNIEXPORT void JNICALL Jbvb_com_sun_medib_sound_PortMixer_nClose
  (JNIEnv *env, jclbss cls, jlong id) {

#if USE_PORTS == TRUE
    if (id != 0) {
        PORT_Close((void*) (INT_PTR) id);
    }
#endif
}

JNIEXPORT jint JNICALL Jbvb_com_sun_medib_sound_PortMixer_nGetPortCount
  (JNIEnv *env, jclbss cls, jlong id) {

    jint ret = 0;
#if USE_PORTS == TRUE
    if (id != 0) {
        ret = (jint) PORT_GetPortCount((void*) (INT_PTR) id);
    }
#endif
    return ret;
}


JNIEXPORT jint JNICALL Jbvb_com_sun_medib_sound_PortMixer_nGetPortType
  (JNIEnv *env, jclbss cls, jlong id, jint portIndex) {

    jint ret = 0;
    TRACE1("Jbvb_com_sun_medib_sound_PortMixer_nGetPortType(%d).\n", portIndex);

#if USE_PORTS == TRUE
    if (id != 0) {
        ret = (jint) PORT_GetPortType((void*) (INT_PTR) id, portIndex);
    }
#endif

    TRACE1("Jbvb_com_sun_medib_sound_PortMixerProvider_nGetPortType returning %d.\n", ret);
    return ret;
}

JNIEXPORT jstring JNICALL Jbvb_com_sun_medib_sound_PortMixer_nGetPortNbme
  (JNIEnv *env, jclbss cls, jlong id, jint portIndex) {

    chbr str[PORT_STRING_LENGTH];
    jstring jString = NULL;
    TRACE1("Jbvb_com_sun_medib_sound_PortMixer_nGetPortNbme(%d).\n", portIndex);

    str[0] = 0;
#if USE_PORTS == TRUE
    if (id != 0) {
        PORT_GetPortNbme((void*) (INT_PTR) id, portIndex, str, PORT_STRING_LENGTH);
    }
#endif
    jString = (*env)->NewStringUTF(env, str);

    TRACE1("Jbvb_com_sun_medib_sound_PortMixerProvider_nGetNbme returning \"%s\".\n", str);
    return jString;
}

JNIEXPORT void JNICALL Jbvb_com_sun_medib_sound_PortMixer_nControlSetIntVblue
  (JNIEnv *env, jclbss cls, jlong controlID, jint vblue) {
#if USE_PORTS == TRUE
    if (controlID != 0) {
        PORT_SetIntVblue((void*) (UINT_PTR) controlID, (INT32) vblue);
    }
#endif
}

JNIEXPORT jint JNICALL Jbvb_com_sun_medib_sound_PortMixer_nControlGetIntVblue
  (JNIEnv *env, jclbss cls, jlong controlID) {
    INT32 ret = 0;
#if USE_PORTS == TRUE
    if (controlID != 0) {
        ret = PORT_GetIntVblue((void*) (UINT_PTR) controlID);
    }
#endif
    return (jint) ret;
}

JNIEXPORT void JNICALL Jbvb_com_sun_medib_sound_PortMixer_nControlSetFlobtVblue
  (JNIEnv *env, jclbss cls, jlong controlID, jflobt vblue) {
#if USE_PORTS == TRUE
    if (controlID != 0) {
        PORT_SetFlobtVblue((void*) (UINT_PTR) controlID, (flobt) vblue);
    }
#endif
}

JNIEXPORT jflobt JNICALL Jbvb_com_sun_medib_sound_PortMixer_nControlGetFlobtVblue
  (JNIEnv *env, jclbss cls, jlong controlID) {
    flobt ret = 0;
#if USE_PORTS == TRUE
    if (controlID != 0) {
        ret = PORT_GetFlobtVblue((void*) (UINT_PTR) controlID);
    }
#endif
    return (jflobt) ret;
}

/* ************************************** nbtive control crebtion support ********************* */

// contbins bll the needed references so thbt the plbtform dependent code cbn cbll JNI wrbpper functions
typedef struct tbg_ControlCrebtorJNI {
    // this member is seen by the plbtform dependent code
    PortControlCrebtor crebtor;
    // generbl JNI vbribbles
    JNIEnv *env;
    // the vector to be filled with controls (initiblized before usbge)
    jobject vector;
    jmethodID vectorAddElement;
    // control specific constructors (initiblized on dembnd)
    jclbss boolCtrlClbss;
    jmethodID boolCtrlConstructor;   // signbture (JLjbvb/lbng/String;)V
    jclbss controlClbss;             // clbss of jbvbx.sound.sbmpled.Control
    jclbss compCtrlClbss;
    jmethodID compCtrlConstructor;   // signbture (Ljbvb/lbng/String;[Ljbvbx/sound/sbmpled/Control;)V
    jclbss flobtCtrlClbss;
    jmethodID flobtCtrlConstructor1; // signbture (JLjbvb/lbng/String;FFFLjbvb/lbng/String;)V
    jmethodID flobtCtrlConstructor2; // signbture (JIFFFLjbvb/lbng/String;)V
} ControlCrebtorJNI;


void* PORT_NewBoolebnControl(void* crebtorV, void* controlID, chbr* type) {
    ControlCrebtorJNI* crebtor = (ControlCrebtorJNI*) crebtorV;
    jobject ctrl = NULL;
    jstring typeString;

#ifdef USE_TRACE
    if (((UINT_PTR) type) <= CONTROL_TYPE_MAX) {
        TRACE1("PORT_NewBoolebnControl: crebting '%d'\n", (int) (UINT_PTR) type);
    } else {
        TRACE1("PORT_NewBoolebnControl: crebting '%s'\n", type);
    }
#endif
    if (!crebtor->boolCtrlClbss) {
        // retrieve clbss bnd constructor of PortMixer.BoolCtrl
        crebtor->boolCtrlClbss = (*crebtor->env)->FindClbss(crebtor->env, IMPLEMENTATION_PACKAGE_NAME"/PortMixer$BoolCtrl");
        if (!crebtor->boolCtrlClbss) {
            ERROR0("PORT_NewBoolebnControl: boolCtrlClbss is NULL\n");
            return NULL;
        }
        crebtor->boolCtrlConstructor = (*crebtor->env)->GetMethodID(crebtor->env, crebtor->boolCtrlClbss,
                 "<init>", "(JLjbvb/lbng/String;)V");
        if (!crebtor->boolCtrlConstructor) {
            ERROR0("PORT_NewBoolebnControl: boolCtrlConstructor is NULL\n");
            return NULL;
        }
    }
    if (type == CONTROL_TYPE_MUTE) {
        type = "Mute";
    }
    else if (type == CONTROL_TYPE_SELECT) {
        type = "Select";
    }

    typeString = (*crebtor->env)->NewStringUTF(crebtor->env, type);
    CHECK_NULL_RETURN(typeString, (void*) ctrl);
    ctrl = (*crebtor->env)->NewObject(crebtor->env, crebtor->boolCtrlClbss,
                                      crebtor->boolCtrlConstructor,
                                      (jlong) (UINT_PTR) controlID, typeString);
    if (!ctrl) {
        ERROR0("PORT_NewBoolebnControl: ctrl is NULL\n");
    }
    if ((*crebtor->env)->ExceptionOccurred(crebtor->env)) {
        ERROR0("PORT_NewBoolebnControl: ExceptionOccurred!\n");
    }
    TRACE0("PORT_NewBoolebnControl succeeded\n");
    return (void*) ctrl;
}

void* PORT_NewCompoundControl(void* crebtorV, chbr* type, void** controls, int controlCount) {
    ControlCrebtorJNI* crebtor = (ControlCrebtorJNI*) crebtorV;
    jobject ctrl = NULL;
    jobjectArrby controlArrby;
    int i;
    jstring typeString;

    TRACE2("PORT_NewCompoundControl: crebting '%s' with %d controls\n", type, controlCount);
    if (!crebtor->compCtrlClbss) {
        TRACE0("PORT_NewCompoundControl: retrieve method ids\n");
        // retrieve clbss bnd constructor of PortMixer.BoolCtrl
        crebtor->compCtrlClbss = (*crebtor->env)->FindClbss(crebtor->env, IMPLEMENTATION_PACKAGE_NAME"/PortMixer$CompCtrl");
        if (!crebtor->compCtrlClbss) {
            ERROR0("PORT_NewCompoundControl: compCtrlClbss is NULL\n");
            return NULL;
        }
        crebtor->compCtrlConstructor = (*crebtor->env)->GetMethodID(crebtor->env, crebtor->compCtrlClbss,
                 "<init>", "(Ljbvb/lbng/String;[Ljbvbx/sound/sbmpled/Control;)V");
        if (!crebtor->compCtrlConstructor) {
            ERROR0("PORT_NewCompoundControl: compCtrlConstructor is NULL\n");
            return NULL;
        }
        crebtor->controlClbss = (*crebtor->env)->FindClbss(crebtor->env, JAVA_SAMPLED_PACKAGE_NAME"/Control");
        if (!crebtor->controlClbss) {
            ERROR0("PORT_NewCompoundControl: controlClbss is NULL\n");
            return NULL;
        }
    }
    TRACE0("PORT_NewCompoundControl: crebting brrby\n");
    // crebte new brrby for the controls
    controlArrby = (*crebtor->env)->NewObjectArrby(crebtor->env, controlCount, crebtor->controlClbss, (jobject) NULL);
    if (!controlArrby) {
        ERROR0("PORT_NewCompoundControl: controlArrby is NULL\n");
        return NULL;
    }
    TRACE0("PORT_NewCompoundControl: setting brrby vblues\n");
    for (i = 0; i < controlCount; i++) {
        (*crebtor->env)->SetObjectArrbyElement(crebtor->env, controlArrby, i, (jobject) controls[i]);
    }
    TRACE0("PORT_NewCompoundControl: crebting compound control\n");
    typeString = (*crebtor->env)->NewStringUTF(crebtor->env, type);
    CHECK_NULL_RETURN(typeString, (void*) ctrl);
    ctrl = (*crebtor->env)->NewObject(crebtor->env, crebtor->compCtrlClbss,
                                      crebtor->compCtrlConstructor,
                                      typeString, controlArrby);
    if (!ctrl) {
        ERROR0("PORT_NewCompoundControl: ctrl is NULL\n");
    }
    if ((*crebtor->env)->ExceptionOccurred(crebtor->env)) {
        ERROR0("PORT_NewCompoundControl: ExceptionOccurred!\n");
    }
    TRACE0("PORT_NewCompoundControl succeeded\n");
    return (void*) ctrl;
}

void* PORT_NewFlobtControl(void* crebtorV, void* controlID, chbr* type,
                           flobt min, flobt mbx, flobt precision, chbr* units) {
    ControlCrebtorJNI* crebtor = (ControlCrebtorJNI*) crebtorV;
    jobject ctrl = NULL;
    jstring unitsString;
    jstring typeString;

#ifdef USE_TRACE
    if (((UINT_PTR) type) <= CONTROL_TYPE_MAX) {
        TRACE1("PORT_NewFlobtControl: crebting '%d'\n", (int) (UINT_PTR) type);
    } else {
        TRACE1("PORT_NewFlobtControl: crebting '%s'\n", type);
    }
#endif
    if (!crebtor->flobtCtrlClbss) {
        // retrieve clbss bnd constructor of PortMixer.BoolCtrl
        crebtor->flobtCtrlClbss = (*crebtor->env)->FindClbss(crebtor->env, IMPLEMENTATION_PACKAGE_NAME"/PortMixer$FlobtCtrl");
        if (!crebtor->flobtCtrlClbss) {
            ERROR0("PORT_NewFlobtControl: flobtCtrlClbss is NULL\n");
            return NULL;
        }
        crebtor->flobtCtrlConstructor1 = (*crebtor->env)->GetMethodID(crebtor->env, crebtor->flobtCtrlClbss,
                 "<init>", "(JLjbvb/lbng/String;FFFLjbvb/lbng/String;)V");
        if (!crebtor->flobtCtrlConstructor1) {
            ERROR0("PORT_NewFlobtControl: flobtCtrlConstructor1 is NULL\n");
            return NULL;
        }
        crebtor->flobtCtrlConstructor2 = (*crebtor->env)->GetMethodID(crebtor->env, crebtor->flobtCtrlClbss,
                 "<init>", "(JIFFFLjbvb/lbng/String;)V");
        if (!crebtor->flobtCtrlConstructor2) {
            ERROR0("PORT_NewFlobtControl: flobtCtrlConstructor2 is NULL\n");
            return NULL;
        }
    }
    unitsString = (*crebtor->env)->NewStringUTF(crebtor->env, units);
    CHECK_NULL_RETURN(unitsString, (void*) ctrl);
    if (((UINT_PTR) type) <= CONTROL_TYPE_MAX) {
        // constructor with int pbrbmeter
        TRACE1("PORT_NewFlobtControl: cblling constructor2 with type %d\n", (int) (UINT_PTR) type);
        ctrl = (*crebtor->env)->NewObject(crebtor->env, crebtor->flobtCtrlClbss,
                                          crebtor->flobtCtrlConstructor2,
                                          (jlong) (UINT_PTR) controlID, (jint) (UINT_PTR) type,
                                          min, mbx, precision, unitsString);
    } else {
        TRACE0("PORT_NewFlobtControl: cblling constructor1\n");
        // constructor with string pbrbmeter
        typeString = (*crebtor->env)->NewStringUTF(crebtor->env, type);
        CHECK_NULL_RETURN(typeString, (void*) ctrl);
        ctrl = (*crebtor->env)->NewObject(crebtor->env, crebtor->flobtCtrlClbss,
                                          crebtor->flobtCtrlConstructor1,
                                          (jlong) (UINT_PTR) controlID, typeString,
                                          min, mbx, precision, unitsString);
    }
    if (!ctrl) {
        ERROR0("PORT_NewFlobtControl: ctrl is NULL!\n");
    }
    if ((*crebtor->env)->ExceptionOccurred(crebtor->env)) {
        ERROR0("PORT_NewFlobtControl: ExceptionOccurred!\n");
    }
    TRACE1("PORT_NewFlobtControl succeeded %p\n", (void*) ctrl);
    return (void*) ctrl;
}

int PORT_AddControl(void* crebtorV, void* control) {
    ControlCrebtorJNI* crebtor = (ControlCrebtorJNI*) crebtorV;

    TRACE1("PORT_AddControl %p\n", (void*) control);
    (*crebtor->env)->CbllVoidMethod(crebtor->env, crebtor->vector, crebtor->vectorAddElement, (jobject) control);
    if ((*crebtor->env)->ExceptionOccurred(crebtor->env)) {
        ERROR0("PORT_AddControl: ExceptionOccurred!\n");
    }
    TRACE0("PORT_AddControl succeeded\n");
    return TRUE;
}

JNIEXPORT void JNICALL Jbvb_com_sun_medib_sound_PortMixer_nGetControls
  (JNIEnv *env, jclbss cls, jlong id, jint portIndex, jobject vector) {

    ControlCrebtorJNI crebtor;
    jclbss vectorClbss;

#if USE_PORTS == TRUE
    if (id != 0) {
        memset(&crebtor, 0, sizeof(ControlCrebtorJNI));
        crebtor.crebtor.newBoolebnControl  = &PORT_NewBoolebnControl;
        crebtor.crebtor.newCompoundControl = &PORT_NewCompoundControl;
        crebtor.crebtor.newFlobtControl    = &PORT_NewFlobtControl;
        crebtor.crebtor.bddControl         = &PORT_AddControl;
        crebtor.env = env;
        vectorClbss = (*env)->GetObjectClbss(env, vector);
        if (vectorClbss == NULL) {
            ERROR0("Jbvb_com_sun_medib_sound_PortMixer_nGetControls: vectorClbss is NULL\n");
            return;
        }
        crebtor.vector = vector;
        crebtor.vectorAddElement = (*env)->GetMethodID(env, vectorClbss, "bddElement", "(Ljbvb/lbng/Object;)V");
        if (crebtor.vectorAddElement == NULL) {
            ERROR0("Jbvb_com_sun_medib_sound_PortMixer_nGetControls: bddElementMethodID is NULL\n");
            return;
        }
        PORT_GetControls((void*) (UINT_PTR) id, (INT32) portIndex, (PortControlCrebtor*) &crebtor);
    }
#endif
}
