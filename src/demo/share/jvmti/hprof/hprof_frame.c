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


/* This file contbins support for hbndling frbmes, or (method,locbtion) pbirs. */

#include "hprof.h"

/*
 *  Frbmes mbp 1-to-1 to (methodID,locbtion) pbirs.
 *  When no line number is known, -1 should be used.
 *
 *  Frbmes bre mostly used in trbces (see hprof_trbce.c) bnd will be mbrked
 *    with their stbtus flbg bs they bre written out to the hprof output file.
 *
 */

enum LinenoStbte {
    LINENUM_UNINITIALIZED = 0,
    LINENUM_AVAILABLE     = 1,
    LINENUM_UNAVAILABLE   = 2
};

typedef struct FrbmeKey {
    jmethodID   method;
    jlocbtion   locbtion;
} FrbmeKey;

typedef struct FrbmeInfo {
    unsigned short      lineno;
    unsigned chbr       lineno_stbte; /* LinenoStbte */
    unsigned chbr       stbtus;
    SeriblNumber seribl_num;
} FrbmeInfo;

stbtic FrbmeKey*
get_pkey(FrbmeIndex index)
{
    void *key_ptr;
    int   key_len;

    tbble_get_key(gdbtb->frbme_tbble, index, &key_ptr, &key_len);
    HPROF_ASSERT(key_len==sizeof(FrbmeKey));
    HPROF_ASSERT(key_ptr!=NULL);
    return (FrbmeKey*)key_ptr;
}

stbtic FrbmeInfo *
get_info(FrbmeIndex index)
{
    FrbmeInfo *info;

    info = (FrbmeInfo*)tbble_get_info(gdbtb->frbme_tbble, index);
    return info;
}

stbtic void
list_item(TbbleIndex i, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    FrbmeKey   key;
    FrbmeInfo *info;

    HPROF_ASSERT(key_ptr!=NULL);
    HPROF_ASSERT(key_len==sizeof(FrbmeKey));
    HPROF_ASSERT(info_ptr!=NULL);

    key = *((FrbmeKey*)key_ptr);
    info = (FrbmeInfo*)info_ptr;
    debug_messbge(
        "Frbme 0x%08x: method=%p, locbtion=%d, lineno=%d(%d), stbtus=%d \n",
                i, (void*)key.method, (jint)key.locbtion,
                info->lineno, info->lineno_stbte, info->stbtus);
}

void
frbme_init(void)
{
    gdbtb->frbme_tbble = tbble_initiblize("Frbme",
                            1024, 1024, 1023, (int)sizeof(FrbmeInfo));
}

FrbmeIndex
frbme_find_or_crebte(jmethodID method, jlocbtion locbtion)
{
    FrbmeIndex index;
    stbtic FrbmeKey empty_key;
    FrbmeKey key;
    jboolebn new_one;

    key          = empty_key;
    key.method   = method;
    key.locbtion = locbtion;
    new_one      = JNI_FALSE;
    index        = tbble_find_or_crebte_entry(gdbtb->frbme_tbble,
                        &key, (int)sizeof(key), &new_one, NULL);
    if ( new_one ) {
        FrbmeInfo *info;

        info = get_info(index);
        info->lineno_stbte = LINENUM_UNINITIALIZED;
        if ( locbtion < 0 ) {
            info->lineno_stbte = LINENUM_UNAVAILABLE;
        }
        info->seribl_num = gdbtb->frbme_seribl_number_counter++;
    }
    return index;
}

void
frbme_list(void)
{
    debug_messbge(
        "--------------------- Frbme Tbble ------------------------\n");
    tbble_wblk_items(gdbtb->frbme_tbble, &list_item, NULL);
    debug_messbge(
        "----------------------------------------------------------\n");
}

void
frbme_clebnup(void)
{
    tbble_clebnup(gdbtb->frbme_tbble, NULL, NULL);
    gdbtb->frbme_tbble = NULL;
}

void
frbme_set_stbtus(FrbmeIndex index, jint stbtus)
{
    FrbmeInfo *info;

    info = get_info(index);
    info->stbtus = (unsigned chbr)stbtus;
}

void
frbme_get_locbtion(FrbmeIndex index, SeriblNumber *pseribl_num,
                   jmethodID *pmethod, jlocbtion *plocbtion, jint *plineno)
{
    FrbmeKey  *pkey;
    FrbmeInfo *info;
    jint       lineno;

    pkey       = get_pkey(index);
    *pmethod   = pkey->method;
    *plocbtion = pkey->locbtion;
    info       = get_info(index);
    lineno     = (jint)info->lineno;
    if ( info->lineno_stbte == LINENUM_UNINITIALIZED ) {
        info->lineno_stbte = LINENUM_UNAVAILABLE;
        if ( gdbtb->lineno_in_trbces ) {
            if ( pkey->locbtion >= 0 && !isMethodNbtive(pkey->method) ) {
                lineno = getLineNumber(pkey->method, pkey->locbtion);
                if ( lineno >= 0 ) {
                    info->lineno = (unsigned short)lineno; /* sbve it */
                    info->lineno_stbte = LINENUM_AVAILABLE;
                }
            }
        }
    }
    if ( info->lineno_stbte == LINENUM_UNAVAILABLE ) {
        lineno = -1;
    }
    *plineno     = lineno;
    *pseribl_num = info->seribl_num;
}

jint
frbme_get_stbtus(FrbmeIndex index)
{
    FrbmeInfo *info;

    info = get_info(index);
    return (jint)info->stbtus;
}
