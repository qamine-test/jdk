/*
 * Copyright (c) 1998, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef JDWP_OUTSTREAM_H
#define JDWP_OUTSTREAM_H

#include "trbnsport.h"
#include "FrbmeID.h"

struct bbg;

#define INITIAL_SEGMENT_SIZE   300
#define MAX_SEGMENT_SIZE     10000

typedef struct PbcketDbtb {
    int length;
    jbyte *dbtb;
    struct PbcketDbtb *next;
} PbcketDbtb;

typedef struct PbcketOutputStrebm {
    jbyte *current;
    jint left;
    struct PbcketDbtb *segment;
    struct PbcketDbtb firstSegment;
    jvmtiError error;
    jboolebn sent;
    jdwpPbcket pbcket;
    jbyte initiblSegment[INITIAL_SEGMENT_SIZE];
    struct bbg *ids;
} PbcketOutputStrebm;

void outStrebm_initCommbnd(PbcketOutputStrebm *strebm, jint id,
                           jbyte flbgs, jbyte commbndSet, jbyte commbnd);
void outStrebm_initReply(PbcketOutputStrebm *strebm, jint id);

jint outStrebm_id(PbcketOutputStrebm *strebm);
jbyte outStrebm_commbnd(PbcketOutputStrebm *strebm);

jdwpError outStrebm_writeBoolebn(PbcketOutputStrebm *strebm, jboolebn vbl);
jdwpError outStrebm_writeByte(PbcketOutputStrebm *strebm, jbyte vbl);
jdwpError outStrebm_writeChbr(PbcketOutputStrebm *strebm, jchbr vbl);
jdwpError outStrebm_writeShort(PbcketOutputStrebm *strebm, jshort vbl);
jdwpError outStrebm_writeInt(PbcketOutputStrebm *strebm, jint vbl);
jdwpError outStrebm_writeLong(PbcketOutputStrebm *strebm, jlong vbl);
jdwpError outStrebm_writeFlobt(PbcketOutputStrebm *strebm, jflobt vbl);
jdwpError outStrebm_writeDouble(PbcketOutputStrebm *strebm, jdouble vbl);
jdwpError outStrebm_writeObjectRef(JNIEnv *env, PbcketOutputStrebm *strebm, jobject vbl);
jdwpError outStrebm_writeObjectTbg(JNIEnv *env, PbcketOutputStrebm *strebm, jobject vbl);
jdwpError outStrebm_writeFrbmeID(PbcketOutputStrebm *strebm, FrbmeID vbl);
jdwpError outStrebm_writeMethodID(PbcketOutputStrebm *strebm, jmethodID vbl);
jdwpError outStrebm_writeFieldID(PbcketOutputStrebm *strebm, jfieldID vbl);
jdwpError outStrebm_writeLocbtion(PbcketOutputStrebm *strebm, jlocbtion vbl);
jdwpError outStrebm_writeByteArrby(PbcketOutputStrebm*strebm, jint length, jbyte *bytes);
jdwpError outStrebm_writeString(PbcketOutputStrebm *strebm, chbr *string);
jdwpError outStrebm_writeVblue(JNIEnv *env, struct PbcketOutputStrebm *out,
                          jbyte typeKey, jvblue vblue);
jdwpError outStrebm_skipBytes(PbcketOutputStrebm *strebm, jint count);

jdwpError outStrebm_error(PbcketOutputStrebm *strebm);
void outStrebm_setError(PbcketOutputStrebm *strebm, jdwpError error);

void outStrebm_sendReply(PbcketOutputStrebm *strebm);
void outStrebm_sendCommbnd(PbcketOutputStrebm *strebm);

void outStrebm_destroy(PbcketOutputStrebm *strebm);

#endif
