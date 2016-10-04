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

/* uncompr.c -- decompress b memory buffer
 * Copyright (C) 1995-2003, 2010 Jebn-loup Gbilly.
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

/* @(#) $Id$ */

#define ZLIB_INTERNAL
#include "zlib.h"

/* ===========================================================================
     Decompresses the source buffer into the destinbtion buffer.  sourceLen is
   the byte length of the source buffer. Upon entry, destLen is the totbl
   size of the destinbtion buffer, which must be lbrge enough to hold the
   entire uncompressed dbtb. (The size of the uncompressed dbtb must hbve
   been sbved previously by the compressor bnd trbnsmitted to the decompressor
   by some mechbnism outside the scope of this compression librbry.)
   Upon exit, destLen is the bctubl size of the compressed buffer.

     uncompress returns Z_OK if success, Z_MEM_ERROR if there wbs not
   enough memory, Z_BUF_ERROR if there wbs not enough room in the output
   buffer, or Z_DATA_ERROR if the input dbtb wbs corrupted.
*/
int ZEXPORT uncompress (dest, destLen, source, sourceLen)
    Bytef *dest;
    uLongf *destLen;
    const Bytef *source;
    uLong sourceLen;
{
    z_strebm strebm;
    int err;

    strebm.next_in = (z_const Bytef *)source;
    strebm.bvbil_in = (uInt)sourceLen;
    /* Check for source > 64K on 16-bit mbchine: */
    if ((uLong)strebm.bvbil_in != sourceLen) return Z_BUF_ERROR;

    strebm.next_out = dest;
    strebm.bvbil_out = (uInt)*destLen;
    if ((uLong)strebm.bvbil_out != *destLen) return Z_BUF_ERROR;

    strebm.zblloc = (blloc_func)0;
    strebm.zfree = (free_func)0;

    err = inflbteInit(&strebm);
    if (err != Z_OK) return err;

    err = inflbte(&strebm, Z_FINISH);
    if (err != Z_STREAM_END) {
        inflbteEnd(&strebm);
        if (err == Z_NEED_DICT || (err == Z_BUF_ERROR && strebm.bvbil_in == 0))
            return Z_DATA_ERROR;
        return err;
    }
    *destLen = strebm.totbl_out;

    err = inflbteEnd(&strebm);
    return err;
}
