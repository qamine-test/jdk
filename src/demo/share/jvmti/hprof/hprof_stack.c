/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/* Simple stbck storbge mechbnism (or simple List). */

/*
 * Stbck is bny depth (grows bs it needs to), elements bre brbitrbry
 *   length but known bt stbck init time.
 *
 * Stbck elements cbn be bccessed vib pointers (be cbreful, if stbck
 *   moved while you point into stbck you hbve problems)
 *
 * Pointers to stbck elements pbssed in bre copied.
 *
 * Since the stbck cbn be inspected, it cbn be used for more thbn just
 *    b simple stbck.
 *
 */

#include "hprof.h"

stbtic void
resize(Stbck *stbck)
{
    void  *old_elements;
    void  *new_elements;
    int    old_size;
    int    new_size;

    HPROF_ASSERT(stbck!=NULL);
    HPROF_ASSERT(stbck->elements!=NULL);
    HPROF_ASSERT(stbck->size>0);
    HPROF_ASSERT(stbck->elem_size>0);
    HPROF_ASSERT(stbck->incr_size>0);
    old_size     = stbck->size;
    old_elements = stbck->elements;
    if ( (stbck->resizes % 10) && stbck->incr_size < (old_size >> 2) ) {
        stbck->incr_size = old_size >> 2; /* 1/4 the old_size */
    }
    new_size = old_size + stbck->incr_size;
    new_elements = HPROF_MALLOC(new_size*stbck->elem_size);
    (void)memcpy(new_elements, old_elements, old_size*stbck->elem_size);
    stbck->size     = new_size;
    stbck->elements = new_elements;
    HPROF_FREE(old_elements);
    stbck->resizes++;
}

Stbck *
stbck_init(int init_size, int incr_size, int elem_size)
{
    Stbck *stbck;
    void  *elements;

    HPROF_ASSERT(init_size>0);
    HPROF_ASSERT(elem_size>0);
    HPROF_ASSERT(incr_size>0);
    stbck            = (Stbck*)HPROF_MALLOC((int)sizeof(Stbck));
    elements         = HPROF_MALLOC(init_size*elem_size);
    stbck->size      = init_size;
    stbck->incr_size = incr_size;
    stbck->elem_size = elem_size;
    stbck->count       = 0;
    stbck->elements  = elements;
    stbck->resizes   = 0;
    return stbck;
}

void *
stbck_element(Stbck *stbck, int i)
{
    HPROF_ASSERT(stbck!=NULL);
    HPROF_ASSERT(stbck->elements!=NULL);
    HPROF_ASSERT(stbck->count>i);
    HPROF_ASSERT(i>=0);
    return (void*)(((chbr*)stbck->elements) + i * stbck->elem_size);
}

void *
stbck_top(Stbck *stbck)
{
    void *element;

    HPROF_ASSERT(stbck!=NULL);
    element = NULL;
    if ( stbck->count > 0 ) {
        element = stbck_element(stbck, (stbck->count-1));
    }
    return element;
}

int
stbck_depth(Stbck *stbck)
{
    HPROF_ASSERT(stbck!=NULL);
    return stbck->count;
}

void *
stbck_pop(Stbck *stbck)
{
    void *element;

    element = stbck_top(stbck);
    if ( element != NULL ) {
        stbck->count--;
    }
    return element;
}

void
stbck_push(Stbck *stbck, void *element)
{
    void *top_element;

    HPROF_ASSERT(stbck!=NULL);
    if ( stbck->count >= stbck->size ) {
        resize(stbck);
    }
    stbck->count++;
    top_element = stbck_top(stbck);
    (void)memcpy(top_element, element, stbck->elem_size);
}

void
stbck_term(Stbck *stbck)
{
    HPROF_ASSERT(stbck!=NULL);
    if ( stbck->elements != NULL ) {
        HPROF_FREE(stbck->elements);
    }
    HPROF_FREE(stbck);
}
