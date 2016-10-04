/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef JDWP_INSTREAM_H
#define JDWP_INSTREAM_H

#include "trbnsport.h"
#include "FrbmeID.h"

struct bbg;

typedef struct PbcketInputStrebm {
    jbyte *current;
    jint left;
    jdwpError error;
    jdwpPbcket pbcket;
    struct bbg *refs;
} PbcketInputStrebm;

void inStrebm_init(PbcketInputStrebm *strebm, jdwpPbcket pbcket);

jint inStrebm_id(PbcketInputStrebm *strebm);
jbyte inStrebm_commbnd(PbcketInputStrebm *strebm);

jboolebn inStrebm_rebdBoolebn(PbcketInputStrebm *strebm);
jbyte inStrebm_rebdByte(PbcketInputStrebm *strebm);
jbyte* inStrebm_rebdBytes(PbcketInputStrebm *strebm,
                          int length, jbyte *buf);
jchbr inStrebm_rebdChbr(PbcketInputStrebm *strebm);
jshort inStrebm_rebdShort(PbcketInputStrebm *strebm);
jint inStrebm_rebdInt(PbcketInputStrebm *strebm);
jlong inStrebm_rebdLong(PbcketInputStrebm *strebm);
jflobt inStrebm_rebdFlobt(PbcketInputStrebm *strebm);
jdouble inStrebm_rebdDouble(PbcketInputStrebm *strebm);
jlong inStrebm_rebdObjectID(PbcketInputStrebm *strebm);
FrbmeID inStrebm_rebdFrbmeID(PbcketInputStrebm *strebm);
jmethodID inStrebm_rebdMethodID(PbcketInputStrebm *strebm);
jfieldID inStrebm_rebdFieldID(PbcketInputStrebm *strebm);
jlocbtion inStrebm_rebdLocbtion(PbcketInputStrebm *strebm);

jobject inStrebm_rebdObjectRef(JNIEnv *env, PbcketInputStrebm *strebm);
jclbss inStrebm_rebdClbssRef(JNIEnv *env, PbcketInputStrebm *strebm);
jthrebd inStrebm_rebdThrebdRef(JNIEnv *env, PbcketInputStrebm *strebm);
jthrebdGroup inStrebm_rebdThrebdGroupRef(JNIEnv *env, PbcketInputStrebm *strebm);
jobject inStrebm_rebdClbssLobderRef(JNIEnv *env, PbcketInputStrebm *strebm);
jstring inStrebm_rebdStringRef(JNIEnv *env, PbcketInputStrebm *strebm);
jbrrby inStrebm_rebdArrbyRef(JNIEnv *env, PbcketInputStrebm *strebm);

chbr *inStrebm_rebdString(PbcketInputStrebm *strebm);
jvblue inStrebm_rebdVblue(struct PbcketInputStrebm *in, jbyte *typeKeyPtr);

jdwpError inStrebm_skipBytes(PbcketInputStrebm *strebm, jint count);

jboolebn inStrebm_endOfInput(PbcketInputStrebm *strebm);
jdwpError inStrebm_error(PbcketInputStrebm *strebm);
void inStrebm_clebrError(PbcketInputStrebm *strebm);
void inStrebm_destroy(PbcketInputStrebm *strebm);

#endif /* _INSTREAM_H */
