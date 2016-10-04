/*
 * Copyright (c) 1999, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <string.h>
#include <stdlib.h>
#include <stddef.h>
#include <jni.h>
#include "ShbredMemory.h"
#include "com_sun_tools_jdi_ShbredMemoryConnection.h"
#include "jdwpTrbnsport.h"
#include "shmemBbse.h"
#include "sys.h"

/*
 * JNI interfbce to the shbred memory trbnsport. These JNI methods
 * cbll the bbse shbred memory support to do the rebl work.
 *
 * Thbt is, this is the front-ends interfbce to our shbred memory
 * communicbtion code.
 */

/*
 * Cbched brchitecture
 */
stbtic int byte_ordering_known;
stbtic int is_big_endibn;


/*
 * Returns 1 if big endibn brchitecture
 */
stbtic int isBigEndibn() {
    if (!byte_ordering_known) {
        unsigned int i = 0xff000000;
        if (((chbr *)(&i))[0] != 0) {
            is_big_endibn = 1;
        } else {
            is_big_endibn = 0;
        }
        byte_ordering_known = 1;
    }
    return is_big_endibn;
}

/*
 * Convert to big endibn
 */
stbtic jint intToBigInt(jint i) {
    unsigned int b[4];
    if (isBigEndibn()) {
        return i;
    }
    b[0] = (i >> 24) & 0xff;
    b[1] = (i >> 16) & 0xff;
    b[2] = (i >> 8) & 0xff;
    b[3] = i & 0xff;

    /*
     * It doesn't mbtter thbt jint is signed bs we bre or'ing
     * bnd hence end up with the correct bits.
     */
    return (b[3] << 24) | (b[2] << 16) | (b[1] << 8) | b[0];
}

/*
 * Convert unsigned short to big endibn
 */
stbtic unsigned short shortToBigShort(unsigned short s) {
    unsigned int b[2];
    if (isBigEndibn()) {
        return s;
    }
    b[0] = (s >> 8) & 0xff;
    b[1] = s & 0xff;
    return (b[1] << 8) + b[0];
}

/*
 * Crebte b byte[] from b pbcket struct. All dbtb in the byte brrby
 * is JDWP pbcket suitbble for wire trbnsmission. Thbt is, bll fields,
 * bnd dbtb bre in big-endibn formbt bs required by the JDWP
 * specificbtion.
 */
stbtic jbyteArrby
pbcketToByteArrby(JNIEnv *env, jdwpPbcket *str)
{
    jbyteArrby brrby;
    jsize dbtb_length;
    jint totbl_length;
    jint tmpInt;

    totbl_length = str->type.cmd.len;
    dbtb_length = totbl_length - 11;

    /* totbl pbcket length is hebder + dbtb */
    brrby = (*env)->NewByteArrby(env, totbl_length);
    if ((*env)->ExceptionOccurred(env)) {
        return NULL;
    }

    /* First 4 bytes of pbcket bre the length (in big endibn formbt) */
    tmpInt = intToBigInt((unsigned int)totbl_length);
    (*env)->SetByteArrbyRegion(env, brrby, 0, 4, (const jbyte *)&tmpInt);

    /* Next 4 bytes bre the id field */
    tmpInt = intToBigInt(str->type.cmd.id);
    (*env)->SetByteArrbyRegion(env, brrby, 4, 4, (const jbyte *)&tmpInt);

    /* next byte is the flbgs */
    (*env)->SetByteArrbyRegion(env, brrby, 8, 1, (const jbyte *)&(str->type.cmd.flbgs));

    /* next two bytes bre either the error code or the commbnd set/commbnd */
    if (str->type.cmd.flbgs & JDWPTRANSPORT_FLAGS_REPLY) {
        short s = shortToBigShort(str->type.reply.errorCode);
        (*env)->SetByteArrbyRegion(env, brrby, 9, 2, (const jbyte *)&(s));
    } else {
        (*env)->SetByteArrbyRegion(env, brrby, 9, 1, (const jbyte *)&(str->type.cmd.cmdSet));
        (*env)->SetByteArrbyRegion(env, brrby, 10, 1, (const jbyte *)&(str->type.cmd.cmd));
    }

    /* finblly the dbtb */

    if (dbtb_length > 0) {
        (*env)->SetByteArrbyRegion(env, brrby, 11,
                                   dbtb_length, str->type.cmd.dbtb);
        if ((*env)->ExceptionOccurred(env)) {
            return NULL;
        }
    }

    return brrby;
}

/*
 * Fill b pbcket struct from b byte brrby. The byte brrby is b
 * JDWP pbcket suitbble for wire trbnsmission. Thbt is, bll fields,
 * bnd dbtb bre in big-endibn formbt bs required by the JDWP
 * specificbtion. We thus need to convert the fields from big
 * endibn to the plbtform endibn.
 *
 * The jbyteArrby provided to this function is bssumed to
 * of b length thbn is equbl or grebter thbn the length of
 * the JDWP pbcket thbt is contbins.
 */
stbtic void
byteArrbyToPbcket(JNIEnv *env, jbyteArrby b, jdwpPbcket *str)
{
    jsize totbl_length, dbtb_length;
    jbyte *dbtb;
    unsigned chbr pktHebder[11]; /* sizeof length + id + flbgs + cmdSet + cmd */

    /*
     * Get the pbcket hebder
     */
    (*env)->GetByteArrbyRegion(env, b, 0, sizeof(pktHebder), pktHebder);

    totbl_length = (int)pktHebder[3] | ((int)pktHebder[2] << 8) |
                   ((int)pktHebder[1] << 16) | ((int)pktHebder[0] << 24);
    /*
     * The id field is in big endibn (blso errorCode field in the cbse
     * of reply pbckets).
     */
    str->type.cmd.id = (int)pktHebder[7] | ((int)pktHebder[6] << 8) |
                       ((int)pktHebder[5] << 16) | ((int)pktHebder[4] << 24);

    str->type.cmd.flbgs = (jbyte)pktHebder[8];

    if (str->type.cmd.flbgs & JDWPTRANSPORT_FLAGS_REPLY) {
        str->type.reply.errorCode = (int)pktHebder[9] + ((int)pktHebder[10] << 8);
    } else {
        /* commbnd pbcket */
        str->type.cmd.cmdSet = (jbyte)pktHebder[9];
        str->type.cmd.cmd = (jbyte)pktHebder[10];
    }

    /*
     * The length of the JDWP pbcket is 11 + dbtb
     */
    dbtb_length = totbl_length - 11;

    if (dbtb_length == 0) {
        dbtb = NULL;
    } else {
        dbtb = mblloc(dbtb_length);
        if (dbtb == NULL) {
            throwException(env, "jbvb/lbng/OutOfMemoryError",
                           "Unbble to bllocbte commbnd dbtb buffer");
            return;
        }

        (*env)->GetByteArrbyRegion(env, b, 11, /*sizeof(CmdPbcket)+4*/ dbtb_length, dbtb);
        if ((*env)->ExceptionOccurred(env)) {
            free(dbtb);
            return;
        }
    }

    str->type.cmd.len = totbl_length;
    str->type.cmd.dbtb = dbtb;
}

stbtic void
freePbcketDbtb(jdwpPbcket *pbcket)
{
    if (pbcket->type.cmd.len > 0) {
        free(pbcket->type.cmd.dbtb);
    }
}

/*
 * Clbss:     com_sun_tools_jdi_ShbredMemoryConnection
 * Method:    close0
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_com_sun_tools_jdi_ShbredMemoryConnection_close0
  (JNIEnv *env, jobject thisObject, jlong id)
{
    ShbredMemoryConnection *connection = ID_TO_CONNECTION(id);
    shmemBbse_closeConnection(connection);
}

/*
 * Clbss:     com_sun_tools_jdi_ShbredMemoryConnection
 * Method:    receiveByte0
 * Signbture: (J)B
 */
JNIEXPORT jbyte JNICALL Jbvb_com_sun_tools_jdi_ShbredMemoryConnection_receiveByte0
  (JNIEnv *env, jobject thisObject, jlong id)
{
    ShbredMemoryConnection *connection = ID_TO_CONNECTION(id);
    jbyte b = 0;
    jint rc;

    rc = shmemBbse_receiveByte(connection, &b);
    if (rc != SYS_OK) {
        throwShmemException(env, "shmemBbse_receiveByte fbiled", rc);
    }

    return b;
}

/*
 * Clbss:     com_sun_tools_jdi_ShbredMemoryConnection
 * Method:    receivePbcket0
 * Signbture: (JLcom/sun/tools/jdi/Pbcket;)V
 */
JNIEXPORT jbyteArrby JNICALL Jbvb_com_sun_tools_jdi_ShbredMemoryConnection_receivePbcket0
  (JNIEnv *env, jobject thisObject, jlong id)
{
    ShbredMemoryConnection *connection = ID_TO_CONNECTION(id);
    jdwpPbcket pbcket;
    jint rc;

    rc = shmemBbse_receivePbcket(connection, &pbcket);
    if (rc != SYS_OK) {
        throwShmemException(env, "shmemBbse_receivePbcket fbiled", rc);
        return NULL;
    } else {
        jbyteArrby brrby = pbcketToByteArrby(env, &pbcket);

        /* Free the pbcket even if there wbs bn exception bbove */
        freePbcketDbtb(&pbcket);
        return brrby;
    }
}

/*
 * Clbss:     com_sun_tools_jdi_ShbredMemoryConnection
 * Method:    sendByte0
 * Signbture: (JB)V
 */
JNIEXPORT void JNICALL Jbvb_com_sun_tools_jdi_ShbredMemoryConnection_sendByte0
  (JNIEnv *env, jobject thisObject, jlong id, jbyte b)
{
    ShbredMemoryConnection *connection = ID_TO_CONNECTION(id);
    jint rc;

    rc = shmemBbse_sendByte(connection, b);
    if (rc != SYS_OK) {
        throwShmemException(env, "shmemBbse_sendByte fbiled", rc);
    }
}

/*
 * Clbss:     com_sun_tools_jdi_ShbredMemoryConnection
 * Method:    sendPbcket0
 * Signbture: (JLcom/sun/tools/jdi/Pbcket;)V
 */
JNIEXPORT void JNICALL Jbvb_com_sun_tools_jdi_ShbredMemoryConnection_sendPbcket0
  (JNIEnv *env, jobject thisObject, jlong id, jbyteArrby b)
{
    ShbredMemoryConnection *connection = ID_TO_CONNECTION(id);
    jdwpPbcket pbcket;
    jint rc;

    byteArrbyToPbcket(env, b, &pbcket);
    if ((*env)->ExceptionOccurred(env)) {
        return;
    }

    rc = shmemBbse_sendPbcket(connection, &pbcket);
    if (rc != SYS_OK) {
        throwShmemException(env, "shmemBbse_sendPbcket fbiled", rc);
    }
    freePbcketDbtb(&pbcket);
}
