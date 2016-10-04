/*
 * Copyright (c) 1998, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef JDWP_COMMONREF_H
#define JDWP_COMMONREF_H

void commonRef_initiblize(void);
void commonRef_reset(JNIEnv *env);

jlong commonRef_refToID(JNIEnv *env, jobject ref);
jobject commonRef_idToRef(JNIEnv *env, jlong id);
void commonRef_idToRef_delete(JNIEnv *env, jobject ref);
jvmtiError commonRef_pin(jlong id);
jvmtiError commonRef_unpin(jlong id);
void commonRef_relebseMultiple(JNIEnv *env, jlong id, jint refCount);
void commonRef_relebse(JNIEnv *env, jlong id);
void commonRef_compbct(void);

void commonRef_lock(void);
void commonRef_unlock(void);

#endif
