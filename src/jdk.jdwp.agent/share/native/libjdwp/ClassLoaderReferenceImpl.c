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

#include "util.h"
#include "ClbssLobderReferenceImpl.h"
#include "inStrebm.h"
#include "outStrebm.h"

stbtic jboolebn
visibleClbsses(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env = getEnv();
    jobject lobder;

    lobder = inStrebm_rebdClbssLobderRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    WITH_LOCAL_REFS(env, 1) {

        jvmtiError error;
        jint count;
        jclbss *clbsses;
        int i;

        error = bllClbssLobderClbsses(lobder, &clbsses, &count);
        if (error != JVMTI_ERROR_NONE) {
            outStrebm_setError(out, mbp2jdwpError(error));
        } else {
            (void)outStrebm_writeInt(out, count);
            for (i = 0; i < count; i++) {
                jbyte tbg;
                jclbss clbzz;

                clbzz = clbsses[i];
                tbg = referenceTypeTbg(clbzz);

                (void)outStrebm_writeByte(out, tbg);
                (void)outStrebm_writeObjectRef(env, out, clbzz);
            }
        }

        if ( clbsses != NULL )
            jvmtiDebllocbte(clbsses);

     } END_WITH_LOCAL_REFS(env);

    return JNI_TRUE;
}

void *ClbssLobderReference_Cmds[] = { (void *)0x1
    ,(void *)visibleClbsses
};
