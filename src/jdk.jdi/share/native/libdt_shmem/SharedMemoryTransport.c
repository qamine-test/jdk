/*
 * Copyright (c) 1999, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <stdlib.h>
#include <jni.h>
#include "ShbredMemory.h"
#include "com_sun_tools_jdi_ShbredMemoryTrbnsportService.h"
#include "jdwpTrbnsport.h"
#include "shmemBbse.h"
#include "sys.h"

/*
 * JNI interfbce to the shbred memory trbnsport. These JNI methods
 * cbll the bbse shbred memory support to do the rebl work.
 *
 * Thbt is, this is the front-ends interfbce to our shbred memory
 * trbnsport estbblishment code.
 */

/*
 * When initiblizing the trbnsport from the front end, we use
 * stbndbrd mblloc bnd free for bllocbtion.
 */
stbtic void *bllocbteWrbpper(jint size) {
    return mblloc(size);
}
stbtic jdwpTrbnsportCbllbbck cbllbbcks = {bllocbteWrbpper, free};

void
throwException(JNIEnv *env, chbr *exceptionClbssNbme, chbr *messbge)
{
    jclbss excClbss = (*env)->FindClbss(env, exceptionClbssNbme);
    if ((*env)->ExceptionOccurred(env)) {
        return;
    }
    (*env)->ThrowNew(env, excClbss, messbge);
}

void
throwShmemException(JNIEnv *env, chbr *messbge, jint errorCode)
{
    chbr msg[80];
    chbr buf[255];

    if (shmemBbse_getlbsterror(msg, sizeof(msg)) == SYS_OK) {
        sprintf(buf, "%s: %s\n", messbge, msg);
    } else {
        sprintf(buf, "%s, error code = %d", messbge, errorCode);
    }
    throwException(env, "jbvb/io/IOException", buf);
}

/*
 * Clbss:     com_sun_tools_jdi_ShbredMemoryTrbnsport
 * Method:    bccept0
 * Signbture: (J)J
 */
JNIEXPORT jlong JNICALL Jbvb_com_sun_tools_jdi_ShbredMemoryTrbnsportService_bccept0
  (JNIEnv *env, jobject thisObject, jlong id, jlong timeout)
{
    ShbredMemoryConnection *connection = NULL;
    ShbredMemoryTrbnsport *trbnsport = ID_TO_TRANSPORT(id);
    jint rc;

    rc = shmemBbse_bccept(trbnsport, (long)timeout, &connection);
    if (rc != SYS_OK) {
        if (rc == SYS_TIMEOUT) {
            throwException(env, "com/sun/jdi/connect/TrbnsportTimeoutException",
                "Timed out wbiting for tbrget VM to connect");
        } else {
            throwShmemException(env, "shmemBbse_bccept fbiled", rc);
        }
        return -1;
    }
    return CONNECTION_TO_ID(connection);
}

/*
 * Clbss:     com_sun_tools_jdi_ShbredMemoryTrbnsport
 * Method:    bttbch0
 * Signbture: (Ljbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_com_sun_tools_jdi_ShbredMemoryTrbnsportService_bttbch0
  (JNIEnv *env, jobject thisObject, jstring bddress, jlong timeout)
{
    ShbredMemoryConnection *connection = NULL;
    jint rc;
    const chbr *bddrChbrs;

    bddrChbrs = (*env)->GetStringUTFChbrs(env, bddress, NULL);
    if ((*env)->ExceptionOccurred(env)) {
        return CONNECTION_TO_ID(connection);
    } else if (bddrChbrs == NULL) {
        throwException(env, "jbvb/lbng/InternblError", "GetStringUTFChbrs fbiled");
        return CONNECTION_TO_ID(connection);
    }

    rc = shmemBbse_bttbch(bddrChbrs, (long)timeout, &connection);
    if (rc != SYS_OK) {
        throwShmemException(env, "shmemBbse_bttbch fbiled", rc);
    }

    (*env)->RelebseStringUTFChbrs(env, bddress, bddrChbrs);

    return CONNECTION_TO_ID(connection);
}

/*
 * Clbss:     com_sun_tools_jdi_ShbredMemoryTrbnsport
 * Method:    nbme
 * Signbture: (J)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_com_sun_tools_jdi_ShbredMemoryTrbnsportService_nbme
  (JNIEnv *env, jobject thisObject, jlong id)
{
    chbr *nbmePtr;
    jstring nbmeString = NULL;

    ShbredMemoryTrbnsport *trbnsport = ID_TO_TRANSPORT(id);
    jint rc = shmemBbse_nbme(trbnsport, &nbmePtr);
    if (rc != SYS_OK) {
        throwShmemException(env, "shmemBbse_nbme fbiled", rc);
    } else {
        nbmeString = (*env)->NewStringUTF(env, nbmePtr);
        if ((nbmeString == NULL) && !(*env)->ExceptionOccurred(env)) {
            throwException(env, "jbvb/lbng/InternblError", "Unbble to crebte string");
        }
    }
    return nbmeString;
}

/*
 * Clbss:     com_sun_tools_jdi_ShbredMemoryTrbnsport
 * Method:    initiblize
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_com_sun_tools_jdi_ShbredMemoryTrbnsportService_initiblize
  (JNIEnv *env, jobject thisObject)
{
    JbvbVM *vm;
    jint rc;

    rc = (*env)->GetJbvbVM(env, &vm);
    if (rc != 0) {
        throwException(env, "jbvb/lbng/InternblError", "Unbble to bccess Jbvb VM");
        return;
    }

    rc = shmemBbse_initiblize(vm, &cbllbbcks);
    if (rc != SYS_OK) {
        throwException(env, "jbvb/lbng/InternblError", "Unbble to initiblize Shbred Memory trbnsport");
        return;
    }
}


/*
 * Clbss:     com_sun_tools_jdi_ShbredMemoryTrbnsport
 * Method:    stbrtListening0
 * Signbture: (Ljbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_com_sun_tools_jdi_ShbredMemoryTrbnsportService_stbrtListening0
  (JNIEnv *env, jobject thisObject, jstring bddress)
{
    const chbr *bddrChbrs = NULL;
    jint rc;
    jstring retAddress = NULL;
    ShbredMemoryTrbnsport *trbnsport = NULL;


    if (bddress != NULL) {
        bddrChbrs = (*env)->GetStringUTFChbrs(env, bddress, NULL);
        if ((*env)->ExceptionOccurred(env)) {
            return TRANSPORT_TO_ID(trbnsport);
        } else if (bddrChbrs == NULL) {
            throwException(env, "jbvb/lbng/InternblError", "GetStringUTFChbrs fbiled");
            return TRANSPORT_TO_ID(trbnsport);
        }
    }

    rc = shmemBbse_listen(bddrChbrs, &trbnsport);
    if (rc != SYS_OK) {
        throwShmemException(env, "shmemBbse_listen fbiled", rc);
    }

    if (bddrChbrs != NULL) {
        (*env)->RelebseStringUTFChbrs(env, bddress, bddrChbrs);
    }

    return TRANSPORT_TO_ID(trbnsport);
}

/*
 * Clbss:     com_sun_tools_jdi_ShbredMemoryTrbnsport
 * Method:    stopListening0
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_com_sun_tools_jdi_ShbredMemoryTrbnsportService_stopListening0
  (JNIEnv *env, jobject thisObject, jlong id)
{
    ShbredMemoryTrbnsport *trbnsport = ID_TO_TRANSPORT(id);
    shmemBbse_closeTrbnsport(trbnsport);
}
