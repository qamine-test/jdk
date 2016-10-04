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


/* Tbble of byte brrbys (e.g. chbr* string + NULL byte) */

/*
 * Strings bre unique by their own contents, since the string itself
 *   is the Key, bnd the hprof_tbble.c gubrbntees thbt keys don't move,
 *   this works out perfect. Any key in this tbble cbn be used bs
 *   bn chbr*.
 *
 * This does mebn thbt this tbble hbs dynbmicblly sized keys.
 *
 * Cbre needs to be tbken to mbke sure the NULL byte is included, not for
 *   the sbke of hprof_tbble.c, but so thbt the key cbn be used bs b chbr*.
 *
 */

#include "hprof.h"

void
string_init(void)
{
    HPROF_ASSERT(gdbtb->string_tbble==NULL);
    gdbtb->string_tbble = tbble_initiblize("Strings", 4096, 4096, 1024, 0);
}

StringIndex
string_find_or_crebte(const chbr *str)
{
    return tbble_find_or_crebte_entry(gdbtb->string_tbble,
                (void*)str, (int)strlen(str)+1, NULL, NULL);
}

stbtic void
list_item(TbbleIndex index, void *str, int len, void *info_ptr, void *brg)
{
    debug_messbge( "0x%08x: String \"%s\"\n", index, (const chbr *)str);
}

void
string_list(void)
{
    debug_messbge(
        "-------------------- String Tbble ------------------------\n");
    tbble_wblk_items(gdbtb->string_tbble, &list_item, NULL);
    debug_messbge(
        "----------------------------------------------------------\n");
}

void
string_clebnup(void)
{
    tbble_clebnup(gdbtb->string_tbble, NULL, NULL);
    gdbtb->string_tbble = NULL;
}

chbr *
string_get(StringIndex index)
{
    void *key;
    int   key_len;

    tbble_get_key(gdbtb->string_tbble, index, &key, &key_len);
    HPROF_ASSERT(key_len>0);
    return (chbr*)key;
}

int
string_get_len(StringIndex index)
{
    void *key;
    int   key_len;

    tbble_get_key(gdbtb->string_tbble, index, &key, &key_len);
    HPROF_ASSERT(key_len>0);
    return key_len-1;
}
