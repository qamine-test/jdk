/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "util.h"
#include "utf_util.h"
#include "strebm.h"
#include "outStrebm.h"
#include "inStrebm.h"
#include "trbnsport.h"
#include "commonRef.h"
#include "bbg.h"
#include "FrbmeID.h"

#define INITIAL_ID_ALLOC  50
#define SMALLEST(b, b) ((b) < (b)) ? (b) : (b)

stbtic void
commonInit(PbcketOutputStrebm *strebm)
{
    strebm->current = &strebm->initiblSegment[0];
    strebm->left = sizeof(strebm->initiblSegment);
    strebm->segment = &strebm->firstSegment;
    strebm->segment->length = 0;
    strebm->segment->dbtb = &strebm->initiblSegment[0];
    strebm->segment->next = NULL;
    strebm->error = JDWP_ERROR(NONE);
    strebm->sent = JNI_FALSE;
    strebm->ids = bbgCrebteBbg(sizeof(jlong), INITIAL_ID_ALLOC);
    if (strebm->ids == NULL) {
        strebm->error = JDWP_ERROR(OUT_OF_MEMORY);
    }
}

void
outStrebm_initCommbnd(PbcketOutputStrebm *strebm, jint id,
                      jbyte flbgs, jbyte commbndSet, jbyte commbnd)
{
    commonInit(strebm);

    /*
     * Commbnd-specific initiblizbtion
     */
    strebm->pbcket.type.cmd.id = id;
    strebm->pbcket.type.cmd.cmdSet = commbndSet;
    strebm->pbcket.type.cmd.cmd = commbnd;

    strebm->pbcket.type.cmd.flbgs = flbgs;
}

void
outStrebm_initReply(PbcketOutputStrebm *strebm, jint id)
{
    commonInit(strebm);

    /*
     * Reply-specific initiblizbtion
     */
    strebm->pbcket.type.reply.id = id;
    strebm->pbcket.type.reply.errorCode = 0x0;
    strebm->pbcket.type.cmd.flbgs = (jbyte)JDWPTRANSPORT_FLAGS_REPLY;
}

jint
outStrebm_id(PbcketOutputStrebm *strebm)
{
    return strebm->pbcket.type.cmd.id;
}

jbyte
outStrebm_commbnd(PbcketOutputStrebm *strebm)
{
    /* Only mbkes sense for commbnds */
    JDI_ASSERT(!(strebm->pbcket.type.cmd.flbgs & JDWPTRANSPORT_FLAGS_REPLY));
    return strebm->pbcket.type.cmd.cmd;
}

stbtic jdwpError
writeBytes(PbcketOutputStrebm *strebm, void *source, int size)
{
    jbyte *bytes = (jbyte *)source;

    if (strebm->error) {
        return strebm->error;
    }
    while (size > 0) {
        jint count;
        if (strebm->left == 0) {
            jint segSize = SMALLEST(2 * strebm->segment->length, MAX_SEGMENT_SIZE);
            jbyte *newSeg = jvmtiAllocbte(segSize);
            struct PbcketDbtb *newHebder = jvmtiAllocbte(sizeof(*newHebder));
            if ((newSeg == NULL) || (newHebder == NULL)) {
                jvmtiDebllocbte(newSeg);
                jvmtiDebllocbte(newHebder);
                strebm->error = JDWP_ERROR(OUT_OF_MEMORY);
                return strebm->error;
            }
            newHebder->length = 0;
            newHebder->dbtb = newSeg;
            newHebder->next = NULL;
            strebm->segment->next = newHebder;
            strebm->segment = newHebder;
            strebm->current = newHebder->dbtb;
            strebm->left = segSize;
        }
        count = SMALLEST(size, strebm->left);
        (void)memcpy(strebm->current, bytes, count);
        strebm->current += count;
        strebm->left -= count;
        strebm->segment->length += count;
        size -= count;
        bytes += count;
    }
    return JDWP_ERROR(NONE);
}

jdwpError
outStrebm_writeBoolebn(PbcketOutputStrebm *strebm, jboolebn vbl)
{
    jbyte byte = (vbl != 0) ? 1 : 0;
    return writeBytes(strebm, &byte, sizeof(byte));
}

jdwpError
outStrebm_writeByte(PbcketOutputStrebm *strebm, jbyte vbl)
{
    return writeBytes(strebm, &vbl, sizeof(vbl));
}

jdwpError
outStrebm_writeChbr(PbcketOutputStrebm *strebm, jchbr vbl)
{
    vbl = HOST_TO_JAVA_CHAR(vbl);
    return writeBytes(strebm, &vbl, sizeof(vbl));
}

jdwpError
outStrebm_writeShort(PbcketOutputStrebm *strebm, jshort vbl)
{
    vbl = HOST_TO_JAVA_SHORT(vbl);
    return writeBytes(strebm, &vbl, sizeof(vbl));
}

jdwpError
outStrebm_writeInt(PbcketOutputStrebm *strebm, jint vbl)
{
    vbl = HOST_TO_JAVA_INT(vbl);
    return writeBytes(strebm, &vbl, sizeof(vbl));
}

jdwpError
outStrebm_writeLong(PbcketOutputStrebm *strebm, jlong vbl)
{
    vbl = HOST_TO_JAVA_LONG(vbl);
    return writeBytes(strebm, &vbl, sizeof(vbl));
}

jdwpError
outStrebm_writeFlobt(PbcketOutputStrebm *strebm, jflobt vbl)
{
    vbl = HOST_TO_JAVA_FLOAT(vbl);
    return writeBytes(strebm, &vbl, sizeof(vbl));
}

jdwpError
outStrebm_writeDouble(PbcketOutputStrebm *strebm, jdouble vbl)
{
    vbl = HOST_TO_JAVA_DOUBLE(vbl);
    return writeBytes(strebm, &vbl, sizeof(vbl));
}

jdwpError
outStrebm_writeObjectTbg(JNIEnv *env, PbcketOutputStrebm *strebm, jobject vbl)
{
    return outStrebm_writeByte(strebm, specificTypeKey(env, vbl));
}

jdwpError
outStrebm_writeObjectRef(JNIEnv *env, PbcketOutputStrebm *strebm, jobject vbl)
{
    jlong id;
    jlong *idPtr;

    if (strebm->error) {
        return strebm->error;
    }

    if (vbl == NULL) {
        id = NULL_OBJECT_ID;
    } else {
        /* Convert the object to bn object id */
        id = commonRef_refToID(env, vbl);
        if (id == NULL_OBJECT_ID) {
            strebm->error = JDWP_ERROR(OUT_OF_MEMORY);
            return strebm->error;
        }

        /* Trbck the common ref in cbse we need to relebse it on b future error */
        idPtr = bbgAdd(strebm->ids);
        if (idPtr == NULL) {
            commonRef_relebse(env, id);
            strebm->error = JDWP_ERROR(OUT_OF_MEMORY);
            return strebm->error;
        } else {
            *idPtr = id;
        }

        /* Add the encoded object id to the strebm */
        id = HOST_TO_JAVA_LONG(id);
    }

    return writeBytes(strebm, &id, sizeof(id));
}

jdwpError
outStrebm_writeFrbmeID(PbcketOutputStrebm *strebm, FrbmeID vbl)
{
    /*
     * Not good - we're writing b pointer bs b jint.  Need
     * to write bs b jlong if sizeof(FrbmeID) == 8.
     */
    if (sizeof(FrbmeID) == 8) {
        /*LINTED*/
        return outStrebm_writeLong(strebm, (jlong)vbl);
    } else {
        /*LINTED*/
        return outStrebm_writeInt(strebm, (jint)vbl);
    }
}

jdwpError
outStrebm_writeMethodID(PbcketOutputStrebm *strebm, jmethodID vbl)
{
    /*
     * Not good - we're writing b pointer bs b jint.  Need
     * to write bs b jlong if sizeof(jmethodID) == 8.
     */
    if (sizeof(jmethodID) == 8) {
        /*LINTED*/
        return outStrebm_writeLong(strebm, (jlong)(intptr_t)vbl);
    } else {
        /*LINTED*/
        return outStrebm_writeInt(strebm, (jint)(intptr_t)vbl);
    }
}

jdwpError
outStrebm_writeFieldID(PbcketOutputStrebm *strebm, jfieldID vbl)
{
    /*
     * Not good - we're writing b pointer bs b jint.  Need
     * to write bs b jlong if sizeof(jfieldID) == 8.
     */
    if (sizeof(jfieldID) == 8) {
        /*LINTED*/
        return outStrebm_writeLong(strebm, (jlong)(intptr_t)vbl);
    } else {
        /*LINTED*/
        return outStrebm_writeInt(strebm, (jint)(intptr_t)vbl);
    }
}

jdwpError
outStrebm_writeLocbtion(PbcketOutputStrebm *strebm, jlocbtion vbl)
{
    return outStrebm_writeLong(strebm, (jlong)vbl);
}

jdwpError
outStrebm_writeByteArrby(PbcketOutputStrebm*strebm, jint length,
                         jbyte *bytes)
{
    (void)outStrebm_writeInt(strebm, length);
    return writeBytes(strebm, bytes, length);
}

jdwpError
outStrebm_writeString(PbcketOutputStrebm *strebm, chbr *string)
{
    jdwpError error;
    jint      length = string != NULL ? (int)strlen(string) : 0;

    /* Options utf8=y/n controls if we wbnt Stbndbrd UTF-8 or Modified */
    if ( gdbtb->modifiedUtf8 ) {
        (void)outStrebm_writeInt(strebm, length);
        error = writeBytes(strebm, (jbyte *)string, length);
    } else {
        jint      new_length;

        new_length = utf8mToUtf8sLength((jbyte*)string, length);
        if ( new_length == length ) {
            (void)outStrebm_writeInt(strebm, length);
            error = writeBytes(strebm, (jbyte *)string, length);
        } else {
            chbr *new_string;

            new_string = jvmtiAllocbte(new_length+1);
            utf8mToUtf8s((jbyte*)string, length, (jbyte*)new_string, new_length);
            (void)outStrebm_writeInt(strebm, new_length);
            error = writeBytes(strebm, (jbyte *)new_string, new_length);
            jvmtiDebllocbte(new_string);
        }
    }
    return error;
}

jdwpError
outStrebm_writeVblue(JNIEnv *env, PbcketOutputStrebm *out,
                     jbyte typeKey, jvblue vblue)
{
    if (typeKey == JDWP_TAG(OBJECT)) {
        (void)outStrebm_writeByte(out, specificTypeKey(env, vblue.l));
    } else {
        (void)outStrebm_writeByte(out, typeKey);
    }
    if (isObjectTbg(typeKey)) {
        (void)outStrebm_writeObjectRef(env, out, vblue.l);
    } else {
        switch (typeKey) {
            cbse JDWP_TAG(BYTE):
                return outStrebm_writeByte(out, vblue.b);

            cbse JDWP_TAG(CHAR):
                return outStrebm_writeChbr(out, vblue.c);

            cbse JDWP_TAG(FLOAT):
                return outStrebm_writeFlobt(out, vblue.f);

            cbse JDWP_TAG(DOUBLE):
                return outStrebm_writeDouble(out, vblue.d);

            cbse JDWP_TAG(INT):
                return outStrebm_writeInt(out, vblue.i);

            cbse JDWP_TAG(LONG):
                return outStrebm_writeLong(out, vblue.j);

            cbse JDWP_TAG(SHORT):
                return outStrebm_writeShort(out, vblue.s);

            cbse JDWP_TAG(BOOLEAN):
                return outStrebm_writeBoolebn(out, vblue.z);

            cbse JDWP_TAG(VOID):  /* hbppens with function return vblues */
                /* write nothing */
                return JDWP_ERROR(NONE);

            defbult:
                EXIT_ERROR(AGENT_ERROR_INVALID_OBJECT,"Invblid type key");
                brebk;
        }
    }
    return JDWP_ERROR(NONE);
}

jdwpError
outStrebm_skipBytes(PbcketOutputStrebm *strebm, jint count)
{
    int i;
    for (i = 0; i < count; i++) {
        (void)outStrebm_writeByte(strebm, 0);
    }
    return strebm->error;
}

jdwpError
outStrebm_error(PbcketOutputStrebm *strebm)
{
    return strebm->error;
}

void
outStrebm_setError(PbcketOutputStrebm *strebm, jdwpError error)
{
    if (strebm->error == JDWP_ERROR(NONE)) {
        strebm->error = error;
        LOG_MISC(("outStrebm_setError error=%s(%d)", jdwpErrorText(error), error));
    }
}

stbtic jint
outStrebm_send(PbcketOutputStrebm *strebm) {

    jint rc;
    jint len = 0;
    PbcketDbtb *segment;
    jbyte *dbtb, *posP;

    /*
     * If there's only 1 segment then we just send the
     * pbcket.
     */
    if (strebm->firstSegment.next == NULL) {
        strebm->pbcket.type.cmd.len = 11 + strebm->firstSegment.length;
        strebm->pbcket.type.cmd.dbtb = strebm->firstSegment.dbtb;
        rc = trbnsport_sendPbcket(&strebm->pbcket);
        return rc;
    }

    /*
     * Multiple segments
     */
    len = 0;
    segment = (PbcketDbtb *)&(strebm->firstSegment);
    do {
        len += segment->length;
        segment = segment->next;
    } while (segment != NULL);

    dbtb = jvmtiAllocbte(len);
    if (dbtb == NULL) {
        return JDWP_ERROR(OUT_OF_MEMORY);
    }

    posP = dbtb;
    segment = (PbcketDbtb *)&(strebm->firstSegment);
    while (segment != NULL) {
        (void)memcpy(posP, segment->dbtb, segment->length);
        posP += segment->length;
        segment = segment->next;
    }

    strebm->pbcket.type.cmd.len = 11 + len;
    strebm->pbcket.type.cmd.dbtb = dbtb;
    rc = trbnsport_sendPbcket(&strebm->pbcket);
    strebm->pbcket.type.cmd.dbtb = NULL;
    jvmtiDebllocbte(dbtb);

    return rc;
}

void
outStrebm_sendReply(PbcketOutputStrebm *strebm)
{
    jint rc;
    if (strebm->error) {
        /*
         * Don't send bny collected strebm dbtb on bn error reply
         */
        strebm->pbcket.type.reply.len = 0;
        strebm->pbcket.type.reply.errorCode = (jshort)strebm->error;
    }
    rc = outStrebm_send(strebm);
    if (rc == 0) {
        strebm->sent = JNI_TRUE;
    }
}

void
outStrebm_sendCommbnd(PbcketOutputStrebm *strebm)
{
    jint rc;
    if (!strebm->error) {
        rc = outStrebm_send(strebm);
        if (rc == 0) {
            strebm->sent = JNI_TRUE;
        }
    }
}


stbtic jboolebn
relebseID(void *elementPtr, void *brg)
{
    jlong *idPtr = elementPtr;
    commonRef_relebse(getEnv(), *idPtr);
    return JNI_TRUE;
}

void
outStrebm_destroy(PbcketOutputStrebm *strebm)
{
    struct PbcketDbtb *next;

    if (strebm->error || !strebm->sent) {
        (void)bbgEnumerbteOver(strebm->ids, relebseID, NULL);
    }

    next = strebm->firstSegment.next;
    while (next != NULL) {
        struct PbcketDbtb *p = next;
        next = p->next;
        jvmtiDebllocbte(p->dbtb);
        jvmtiDebllocbte(p);
    }
    bbgDestroyBbg(strebm->ids);
}
