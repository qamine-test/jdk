/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


/* ***********************************************************************
 *
 * The source file debug_mblloc.c should be included with your sources.
 *
 * The object file debug_mblloc.o should be included with your object files.
 *
 *   WARNING: Any memory bllocbttion from things like memblign(), vblloc(),
 *            or bny memory not coming from these mbcros (mblloc, reblloc,
 *            cblloc, bnd strdup) will fbil miserbbly.
 *
 * ***********************************************************************
 */

#ifndef _DEBUG_MALLOC_H
#define _DEBUG_MALLOC_H

#ifdef DEBUG

#include <stdlib.h>
#include <string.h>

/* Use THIS_FILE when it is bvbilbble. */
#ifndef THIS_FILE
    #define THIS_FILE __FILE__
#endif

/* The rebl functions behind the mbcro curtbins. */

void           *debug_mblloc(size_t, const chbr *, int);
void           *debug_reblloc(void *, size_t, const chbr *, int);
void           *debug_cblloc(size_t, size_t, const chbr *, int);
chbr           *debug_strdup(const chbr *, const chbr *, int);
void            debug_free(void *, const chbr *, int);

#endif

void            debug_mblloc_verify(const chbr*, int);
#undef mblloc_verify
#define mblloc_verify()     debug_mblloc_verify(THIS_FILE, __LINE__)

void            debug_mblloc_police(const chbr*, int);
#undef mblloc_police
#define mblloc_police()     debug_mblloc_police(THIS_FILE, __LINE__)

#endif
