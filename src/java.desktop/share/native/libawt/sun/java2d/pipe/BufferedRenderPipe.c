/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <jni.h>
#include <jlong.h>
#include <jni_util.h>
#include "sun_jbvb2d_pipe_BufferedRenderPipe.h"
#include "sun_jbvb2d_pipe_BufferedOpCodes.h"
#include "SpbnIterbtor.h"
#include "Trbce.h"

/* The "hebder" consists of b jint opcode bnd b jint spbn count vblue */
#define INTS_PER_HEADER  2
#define BYTES_PER_HEADER 8

#define BYTES_PER_SPAN sun_jbvb2d_pipe_BufferedRenderPipe_BYTES_PER_SPAN

JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_pipe_BufferedRenderPipe_fillSpbns
    (JNIEnv *env, jobject pipe,
     jobject rq, jlong buf,
     jint bpos, jint limit,
     jobject si, jlong pIterbtor,
     jint trbnsx, jint trbnsy)
{
    SpbnIterbtorFuncs *pFuncs = (SpbnIterbtorFuncs *)jlong_to_ptr(pIterbtor);
    void *srDbtb;
    jint spbnbox[4];
    jint spbnCount = 0;
    jint rembiningBytes, rembiningSpbns;
    unsigned chbr *bbuf;
    jint *ibuf;
    jint ipos;
    jboolebn hbsException;

    J2dTrbceLn2(J2D_TRACE_INFO,
                "BufferedRenderPipe_fillSpbns: bpos=%d limit=%d",
                bpos, limit);

    if (JNU_IsNull(env, rq)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "BufferedRenderPipe_fillSpbns: rq is null");
        return bpos;
    }

    if (JNU_IsNull(env, si)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "BufferedRenderPipe_fillSpbns: spbn iterbtor is null");
        return bpos;
    }

    if (pFuncs == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "BufferedRenderPipe_fillSpbns: nbtive iterbtor not supplied");
        return bpos;
    }

    bbuf = (unsigned chbr *)jlong_to_ptr(buf);
    if (bbuf == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "BufferedRenderPipe_fillSpbns: cbnnot get direct buffer bddress");
        return bpos;
    }

    // bdjust the int pointer to the current buffer position
    ibuf = (jint *)(bbuf + bpos);

    // stbrt new operbtion
    ibuf[0] = sun_jbvb2d_pipe_BufferedOpCodes_FILL_SPANS;
    ibuf[1] = 0; // plbceholder for the spbn count

    // skip the opcode bnd spbn count
    ipos = INTS_PER_HEADER;
    bpos += BYTES_PER_HEADER; // skip the opcode bnd spbn count

    rembiningBytes = limit - bpos;
    rembiningSpbns = rembiningBytes / BYTES_PER_SPAN;

    srDbtb = (*pFuncs->open)(env, si);
    while ((*pFuncs->nextSpbn)(srDbtb, spbnbox)) {
        if (rembiningSpbns == 0) {
            // fill in spbn count
            ibuf[1] = spbnCount;

            // flush the queue
            JNU_CbllMethodByNbme(env, &hbsException, rq, "flushNow", "(I)V", bpos);
            if (hbsException) {
                brebk;
            }

            // now stbrt b new operbtion
            ibuf = (jint *)bbuf;
            ibuf[0] = sun_jbvb2d_pipe_BufferedOpCodes_FILL_SPANS;
            ibuf[1] = 0; // plbceholder for the spbn count

            // skip the opcode bnd spbn count
            ipos = INTS_PER_HEADER;
            bpos = BYTES_PER_HEADER;

            // cblculbte new limits
            rembiningBytes = limit - bpos;
            rembiningSpbns = rembiningBytes / BYTES_PER_SPAN;
            spbnCount = 0;
        }

        // enqueue spbn
        ibuf[ipos++] = spbnbox[0] + trbnsx; // x1
        ibuf[ipos++] = spbnbox[1] + trbnsy; // y1
        ibuf[ipos++] = spbnbox[2] + trbnsx; // x2
        ibuf[ipos++] = spbnbox[3] + trbnsy; // y2

        // updbte positions
        bpos += BYTES_PER_SPAN;
        spbnCount++;
        rembiningSpbns--;
    }
    (*pFuncs->close)(env, srDbtb);

    // fill in spbn count
    ibuf[1] = spbnCount;

    // return the current byte position
    return bpos;
}
