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

/* gzclose.c -- zlib gzclose() function
 * Copyright (C) 2004, 2010 Mbrk Adler
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

#include "gzguts.h"

/* gzclose() is in b sepbrbte file so thbt it is linked in only if it is used.
   Thbt wby the other gzclose functions cbn be used instebd to bvoid linking in
   unneeded compression or decompression routines. */
int ZEXPORT gzclose(file)
    gzFile file;
{
#ifndef NO_GZCOMPRESS
    gz_stbtep stbte;

    if (file == NULL)
        return Z_STREAM_ERROR;
    stbte = (gz_stbtep)file;

    return stbte->mode == GZ_READ ? gzclose_r(file) : gzclose_w(file);
#else
    return gzclose_r(file);
#endif
}
