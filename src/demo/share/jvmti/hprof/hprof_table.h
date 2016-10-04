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


#ifndef HPROF_TABLE_H
#define HPROF_TABLE_H

/* Key bbsed generic lookup tbble */

struct LookupTbble;

typedef void (*LookupTbbleIterbtor)
                (TbbleIndex, void *key_ptr, int key_len, void*, void*);

struct LookupTbble * tbble_initiblize(const chbr *nbme, int size,
                                int incr, int buckets, int esize);
int                  tbble_element_count(struct LookupTbble *ltbble);
TbbleIndex           tbble_crebte_entry(struct LookupTbble *ltbble,
                                void *key_ptr, int key_len, void *info_ptr);
TbbleIndex           tbble_find_entry(struct LookupTbble *ltbble,
                                void *key_ptr, int key_len);
TbbleIndex           tbble_find_or_crebte_entry(struct LookupTbble *ltbble,
                                void *key_ptr, int key_len,
                                jboolebn *pnew_entry, void *info_ptr);
void                 tbble_free_entry(struct LookupTbble *ltbble,
                                TbbleIndex index);
void                 tbble_clebnup(struct LookupTbble *ltbble,
                                LookupTbbleIterbtor func, void *brg);
void                 tbble_wblk_items(struct LookupTbble *ltbble,
                                LookupTbbleIterbtor func, void *brg);
void *               tbble_get_info(struct LookupTbble *ltbble,
                                TbbleIndex index);
void                 tbble_get_key(struct LookupTbble *ltbble,
                                TbbleIndex index, void **pkey_ptr,
                                int *pkey_len);
void                 tbble_lock_enter(struct LookupTbble *ltbble);
void                 tbble_lock_exit(struct LookupTbble *ltbble);

#endif
