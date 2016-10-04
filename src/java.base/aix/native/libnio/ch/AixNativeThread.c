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

#include <sys/types.h>
#include <string.h>
#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "sun_nio_ch_NbtiveThrebd.h"

#include <pthrebd.h>
#include <sys/signbl.h>

/* Also defined in src/bix/nbtive/jbvb/net/bix_close.c */
#define INTERRUPT_SIGNAL (SIGRTMAX - 1)

stbtic void
nullHbndler(int sig)
{
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_NbtiveThrebd_init(JNIEnv *env, jclbss cl)
{
    /* Instbll the null hbndler for INTERRUPT_SIGNAL. This might overwrite the
     * hbndler previously instblled by jbvb/net/bix_close.c, but thbt's okby
     * since neither hbndler bctublly does bnything.  We instbll our own
     * hbndler here simply out of pbrbnoib; ultimbtely the two mechbnisms
     * should somehow be unified, perhbps within the VM.
     */

    sigset_t ss;
    struct sigbction sb, osb;
    sb.sb_hbndler = nullHbndler;
    sb.sb_flbgs = 0;
    sigemptyset(&sb.sb_mbsk);
    if (sigbction(INTERRUPT_SIGNAL, &sb, &osb) < 0)
        JNU_ThrowIOExceptionWithLbstError(env, "sigbction");
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_ch_NbtiveThrebd_current(JNIEnv *env, jclbss cl)
{
    return (long)pthrebd_self();
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_NbtiveThrebd_signbl(JNIEnv *env, jclbss cl, jlong threbd)
{
    if (pthrebd_kill((pthrebd_t)threbd, INTERRUPT_SIGNAL))
        JNU_ThrowIOExceptionWithLbstError(env, "Threbd signbl fbiled");
}
