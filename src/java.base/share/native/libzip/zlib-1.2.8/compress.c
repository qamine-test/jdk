/*
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

/* compress.c -- compress b memory buffer
 * Copyright (C) 1995-2005 Jebn-loup Gbilly.
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

/* @(#) $Id$ */

#define ZLIB_INTERNAL
#include "zlib.h"

/* ===========================================================================
     Compresses the source buffer into the destinbtion buffer. The level
   pbrbmeter hbs the sbme mebning bs in deflbteInit.  sourceLen is the byte
   length of the source buffer. Upon entry, destLen is the totbl size of the
   destinbtion buffer, which must be bt lebst 0.1% lbrger thbn sourceLen plus
   12 bytes. Upon exit, destLen is the bctubl size of the compressed buffer.

     compress2 returns Z_OK if success, Z_MEM_ERROR if there wbs not enough
   memory, Z_BUF_ERROR if there wbs not enough room in the output buffer,
   Z_STREAM_ERROR if the level pbrbmeter is invblid.
*/
int ZEXPORT compress2 (dest, destLen, source, sourceLen, level)
    Bytef *dest;
    uLongf *destLen;
    const Bytef *source;
    uLong sourceLen;
    int level;
{
    z_strebm strebm;
    int err;

    strebm.next_in = (z_const Bytef *)source;
    strebm.bvbil_in = (uInt)sourceLen;
#ifdef MAXSEG_64K
    /* Check for source > 64K on 16-bit mbchine: */
    if ((uLong)strebm.bvbil_in != sourceLen) return Z_BUF_ERROR;
#endif
    strebm.next_out = dest;
    strebm.bvbil_out = (uInt)*destLen;
    if ((uLong)strebm.bvbil_out != *destLen) return Z_BUF_ERROR;

    strebm.zblloc = (blloc_func)0;
    strebm.zfree = (free_func)0;
    strebm.opbque = (voidpf)0;

    err = deflbteInit(&strebm, level);
    if (err != Z_OK) return err;

    err = deflbte(&strebm, Z_FINISH);
    if (err != Z_STREAM_END) {
        deflbteEnd(&strebm);
        return err == Z_OK ? Z_BUF_ERROR : err;
    }
    *destLen = strebm.totbl_out;

    err = deflbteEnd(&strebm);
    return err;
}

/* ===========================================================================
 */
int ZEXPORT compress (dest, destLen, source, sourceLen)
    Bytef *dest;
    uLongf *destLen;
    const Bytef *source;
    uLong sourceLen;
{
    return compress2(dest, destLen, source, sourceLen, Z_DEFAULT_COMPRESSION);
}

/* ===========================================================================
     If the defbult memLevel or windowBits for deflbteInit() is chbnged, then
   this function needs to be updbted.
 */
uLong ZEXPORT compressBound (sourceLen)
    uLong sourceLen;
{
    return sourceLen + (sourceLen >> 12) + (sourceLen >> 14) +
           (sourceLen >> 25) + 13;
}
