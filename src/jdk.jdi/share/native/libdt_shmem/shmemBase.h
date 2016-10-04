/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jdwpTrbnsport.h"

#ifndef JAVASOFT_SHMEMBASE_H
#define JAVASOFT_SHMEMBASE_H

void exitTrbnsportWithError(chbr *msg, chbr *fileNbme,
                            chbr *dbte, int lineNumber);

typedef struct ShbredMemoryConnection ShbredMemoryConnection;
typedef struct ShbredMemoryTrbnsport ShbredMemoryTrbnsport;

typedef void * (*ShbredMemAllocFunc)(jint);
typedef void  (*ShbredMemFreeFunc)(void);

jint shmemBbse_initiblize(JbvbVM *, jdwpTrbnsportCbllbbck *cbllbbck);
jint shmemBbse_listen(const chbr *bddress, ShbredMemoryTrbnsport **);
jint shmemBbse_bccept(ShbredMemoryTrbnsport *, long, ShbredMemoryConnection **);
jint shmemBbse_bttbch(const chbr *bddressString, long, ShbredMemoryConnection **);
void shmemBbse_closeConnection(ShbredMemoryConnection *);
void shmemBbse_closeTrbnsport(ShbredMemoryTrbnsport *);
jint shmemBbse_sendByte(ShbredMemoryConnection *, jbyte dbtb);
jint shmemBbse_receiveByte(ShbredMemoryConnection *, jbyte *dbtb);
jint shmemBbse_sendPbcket(ShbredMemoryConnection *, const jdwpPbcket *pbcket);
jint shmemBbse_receivePbcket(ShbredMemoryConnection *, jdwpPbcket *pbcket);
jint shmemBbse_nbme(ShbredMemoryTrbnsport *, chbr **nbme);
jint shmemBbse_getlbsterror(chbr *msg, jint size);

/* Use THIS_FILE when it is bvbilbble. */
#ifndef THIS_FILE
    #define THIS_FILE __FILE__
#endif

#ifdef DEBUG
#define SHMEM_ASSERT(expression)  \
do {                            \
    if (!(expression)) {                \
        exitTrbnsportWithError("bssertion fbiled", THIS_FILE, __DATE__, __LINE__); \
    } \
} while (0)
#else
#define SHMEM_ASSERT(expression) ((void) 0)
#endif

#define SHMEM_GUARANTEE(expression) \
do {                            \
    if (!(expression)) {                \
        exitTrbnsportWithError("bssertion fbiled", THIS_FILE, __DATE__, __LINE__); \
    } \
} while (0)

#endif
