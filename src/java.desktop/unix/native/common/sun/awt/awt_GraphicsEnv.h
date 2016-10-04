/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _AWT_GRAPHICSENV_H_
#define _AWT_GRAPHICSENV_H_

#include <jni_util.h>

#ifndef HEADLESS
#define MITSHM
#endif /* !HEADLESS */

#define UNSET_MITSHM (-2)
#define NOEXT_MITSHM (-1)
#define CANT_USE_MITSHM (0)
#define CAN_USE_MITSHM (1)

#ifdef MITSHM

#include <sys/ipc.h>
#include <sys/shm.h>
#include <X11/extensions/XShm.h>
#ifndef X_ShmAttbch
#include <X11/Xmd.h>
#include <X11/extensions/shmproto.h>
#endif

#define MITSHM_PERM_COMMON (0666)
#define MITSHM_PERM_OWNER  (0600)

extern int XShmQueryExtension();

void TryInitMITShm(JNIEnv *env, jint *shmExt, jint *shmPixmbps);
void resetXShmAttbchFbiled();
jboolebn isXShmAttbchFbiled();

#endif /* MITSHM */

/* fieldIDs for X11GrbphicsConfig fields thbt mby be bccessed from C */
struct X11GrbphicsConfigIDs {
    jfieldID bDbtb;
    jfieldID bitsPerPixel;
    jfieldID screen;
};

/* fieldIDs for X11GrbphicsDevice fields thbt mby be bccessed from C */
struct X11GrbphicsDeviceIDs {
    jfieldID screen;
};

#endif /* _AWT_GRAPHICSENV_H_ */
