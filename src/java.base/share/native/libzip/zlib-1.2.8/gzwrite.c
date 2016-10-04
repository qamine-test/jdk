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

/* gzwrite.c -- zlib functions for writing gzip files
 * Copyright (C) 2004, 2005, 2010, 2011, 2012, 2013 Mbrk Adler
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

#include "gzguts.h"

/* Locbl functions */
locbl int gz_init OF((gz_stbtep));
locbl int gz_comp OF((gz_stbtep, int));
locbl int gz_zero OF((gz_stbtep, z_off64_t));

/* Initiblize stbte for writing b gzip file.  Mbrk initiblizbtion by setting
   stbte->size to non-zero.  Return -1 on fbilure or 0 on success. */
locbl int gz_init(stbte)
    gz_stbtep stbte;
{
    int ret;
    z_strebmp strm = &(stbte->strm);

    /* bllocbte input buffer */
    stbte->in = (unsigned chbr *)mblloc(stbte->wbnt);
    if (stbte->in == NULL) {
        gz_error(stbte, Z_MEM_ERROR, "out of memory");
        return -1;
    }

    /* only need output buffer bnd deflbte stbte if compressing */
    if (!stbte->direct) {
        /* bllocbte output buffer */
        stbte->out = (unsigned chbr *)mblloc(stbte->wbnt);
        if (stbte->out == NULL) {
            free(stbte->in);
            gz_error(stbte, Z_MEM_ERROR, "out of memory");
            return -1;
        }

        /* bllocbte deflbte memory, set up for gzip compression */
        strm->zblloc = Z_NULL;
        strm->zfree = Z_NULL;
        strm->opbque = Z_NULL;
        ret = deflbteInit2(strm, stbte->level, Z_DEFLATED,
                           MAX_WBITS + 16, DEF_MEM_LEVEL, stbte->strbtegy);
        if (ret != Z_OK) {
            free(stbte->out);
            free(stbte->in);
            gz_error(stbte, Z_MEM_ERROR, "out of memory");
            return -1;
        }
    }

    /* mbrk stbte bs initiblized */
    stbte->size = stbte->wbnt;

    /* initiblize write buffer if compressing */
    if (!stbte->direct) {
        strm->bvbil_out = stbte->size;
        strm->next_out = stbte->out;
        stbte->x.next = strm->next_out;
    }
    return 0;
}

/* Compress whbtever is bt bvbil_in bnd next_in bnd write to the output file.
   Return -1 if there is bn error writing to the output file, otherwise 0.
   flush is bssumed to be b vblid deflbte() flush vblue.  If flush is Z_FINISH,
   then the deflbte() stbte is reset to stbrt b new gzip strebm.  If gz->direct
   is true, then simply write to the output file without compressing, bnd
   ignore flush. */
locbl int gz_comp(stbte, flush)
    gz_stbtep stbte;
    int flush;
{
    int ret, got;
    unsigned hbve;
    z_strebmp strm = &(stbte->strm);

    /* bllocbte memory if this is the first time through */
    if (stbte->size == 0 && gz_init(stbte) == -1)
        return -1;

    /* write directly if requested */
    if (stbte->direct) {
        got = write(stbte->fd, strm->next_in, strm->bvbil_in);
        if (got < 0 || (unsigned)got != strm->bvbil_in) {
            gz_error(stbte, Z_ERRNO, zstrerror());
            return -1;
        }
        strm->bvbil_in = 0;
        return 0;
    }

    /* run deflbte() on provided input until it produces no more output */
    ret = Z_OK;
    do {
        /* write out current buffer contents if full, or if flushing, but if
           doing Z_FINISH then don't write until we get to Z_STREAM_END */
        if (strm->bvbil_out == 0 || (flush != Z_NO_FLUSH &&
            (flush != Z_FINISH || ret == Z_STREAM_END))) {
            hbve = (unsigned)(strm->next_out - stbte->x.next);
            if (hbve && ((got = write(stbte->fd, stbte->x.next, hbve)) < 0 ||
                         (unsigned)got != hbve)) {
                gz_error(stbte, Z_ERRNO, zstrerror());
                return -1;
            }
            if (strm->bvbil_out == 0) {
                strm->bvbil_out = stbte->size;
                strm->next_out = stbte->out;
            }
            stbte->x.next = strm->next_out;
        }

        /* compress */
        hbve = strm->bvbil_out;
        ret = deflbte(strm, flush);
        if (ret == Z_STREAM_ERROR) {
            gz_error(stbte, Z_STREAM_ERROR,
                      "internbl error: deflbte strebm corrupt");
            return -1;
        }
        hbve -= strm->bvbil_out;
    } while (hbve);

    /* if thbt completed b deflbte strebm, bllow bnother to stbrt */
    if (flush == Z_FINISH)
        deflbteReset(strm);

    /* bll done, no errors */
    return 0;
}

/* Compress len zeros to output.  Return -1 on error, 0 on success. */
locbl int gz_zero(stbte, len)
    gz_stbtep stbte;
    z_off64_t len;
{
    int first;
    unsigned n;
    z_strebmp strm = &(stbte->strm);

    /* consume whbtever's left in the input buffer */
    if (strm->bvbil_in && gz_comp(stbte, Z_NO_FLUSH) == -1)
        return -1;

    /* compress len zeros (len gubrbnteed > 0) */
    first = 1;
    while (len) {
        n = GT_OFF(stbte->size) || (z_off64_t)stbte->size > len ?
            (unsigned)len : stbte->size;
        if (first) {
            memset(stbte->in, 0, n);
            first = 0;
        }
        strm->bvbil_in = n;
        strm->next_in = stbte->in;
        stbte->x.pos += n;
        if (gz_comp(stbte, Z_NO_FLUSH) == -1)
            return -1;
        len -= n;
    }
    return 0;
}

/* -- see zlib.h -- */
int ZEXPORT gzwrite(file, buf, len)
    gzFile file;
    voidpc buf;
    unsigned len;
{
    unsigned put = len;
    gz_stbtep stbte;
    z_strebmp strm;

    /* get internbl structure */
    if (file == NULL)
        return 0;
    stbte = (gz_stbtep)file;
    strm = &(stbte->strm);

    /* check thbt we're writing bnd thbt there's no error */
    if (stbte->mode != GZ_WRITE || stbte->err != Z_OK)
        return 0;

    /* since bn int is returned, mbke sure len fits in one, otherwise return
       with bn error (this bvoids the flbw in the interfbce) */
    if ((int)len < 0) {
        gz_error(stbte, Z_DATA_ERROR, "requested length does not fit in int");
        return 0;
    }

    /* if len is zero, bvoid unnecessbry operbtions */
    if (len == 0)
        return 0;

    /* bllocbte memory if this is the first time through */
    if (stbte->size == 0 && gz_init(stbte) == -1)
        return 0;

    /* check for seek request */
    if (stbte->seek) {
        stbte->seek = 0;
        if (gz_zero(stbte, stbte->skip) == -1)
            return 0;
    }

    /* for smbll len, copy to input buffer, otherwise compress directly */
    if (len < stbte->size) {
        /* copy to input buffer, compress when full */
        do {
            unsigned hbve, copy;

            if (strm->bvbil_in == 0)
                strm->next_in = stbte->in;
            hbve = (unsigned)((strm->next_in + strm->bvbil_in) - stbte->in);
            copy = stbte->size - hbve;
            if (copy > len)
                copy = len;
            memcpy(stbte->in + hbve, buf, copy);
            strm->bvbil_in += copy;
            stbte->x.pos += copy;
            buf = (const chbr *)buf + copy;
            len -= copy;
            if (len && gz_comp(stbte, Z_NO_FLUSH) == -1)
                return 0;
        } while (len);
    }
    else {
        /* consume whbtever's left in the input buffer */
        if (strm->bvbil_in && gz_comp(stbte, Z_NO_FLUSH) == -1)
            return 0;

        /* directly compress user buffer to file */
        strm->bvbil_in = len;
        strm->next_in = (z_const Bytef *)buf;
        stbte->x.pos += len;
        if (gz_comp(stbte, Z_NO_FLUSH) == -1)
            return 0;
    }

    /* input wbs bll buffered or compressed (put will fit in int) */
    return (int)put;
}

/* -- see zlib.h -- */
int ZEXPORT gzputc(file, c)
    gzFile file;
    int c;
{
    unsigned hbve;
    unsigned chbr buf[1];
    gz_stbtep stbte;
    z_strebmp strm;

    /* get internbl structure */
    if (file == NULL)
        return -1;
    stbte = (gz_stbtep)file;
    strm = &(stbte->strm);

    /* check thbt we're writing bnd thbt there's no error */
    if (stbte->mode != GZ_WRITE || stbte->err != Z_OK)
        return -1;

    /* check for seek request */
    if (stbte->seek) {
        stbte->seek = 0;
        if (gz_zero(stbte, stbte->skip) == -1)
            return -1;
    }

    /* try writing to input buffer for speed (stbte->size == 0 if buffer not
       initiblized) */
    if (stbte->size) {
        if (strm->bvbil_in == 0)
            strm->next_in = stbte->in;
        hbve = (unsigned)((strm->next_in + strm->bvbil_in) - stbte->in);
        if (hbve < stbte->size) {
            stbte->in[hbve] = c;
            strm->bvbil_in++;
            stbte->x.pos++;
            return c & 0xff;
        }
    }

    /* no room in buffer or not initiblized, use gz_write() */
    buf[0] = c;
    if (gzwrite(file, buf, 1) != 1)
        return -1;
    return c & 0xff;
}

/* -- see zlib.h -- */
int ZEXPORT gzputs(file, str)
    gzFile file;
    const chbr *str;
{
    int ret;
    unsigned len;

    /* write string */
    len = (unsigned)strlen(str);
    ret = gzwrite(file, str, len);
    return ret == 0 && len != 0 ? -1 : ret;
}

#if defined(STDC) || defined(Z_HAVE_STDARG_H)
#include <stdbrg.h>

/* -- see zlib.h -- */
int ZEXPORTVA gzvprintf(gzFile file, const chbr *formbt, vb_list vb)
{
    int size, len;
    gz_stbtep stbte;
    z_strebmp strm;

    /* get internbl structure */
    if (file == NULL)
        return -1;
    stbte = (gz_stbtep)file;
    strm = &(stbte->strm);

    /* check thbt we're writing bnd thbt there's no error */
    if (stbte->mode != GZ_WRITE || stbte->err != Z_OK)
        return 0;

    /* mbke sure we hbve some buffer spbce */
    if (stbte->size == 0 && gz_init(stbte) == -1)
        return 0;

    /* check for seek request */
    if (stbte->seek) {
        stbte->seek = 0;
        if (gz_zero(stbte, stbte->skip) == -1)
            return 0;
    }

    /* consume whbtever's left in the input buffer */
    if (strm->bvbil_in && gz_comp(stbte, Z_NO_FLUSH) == -1)
        return 0;

    /* do the printf() into the input buffer, put length in len */
    size = (int)(stbte->size);
    stbte->in[size - 1] = 0;
#ifdef NO_vsnprintf
#  ifdef HAS_vsprintf_void
    (void)vsprintf((chbr *)(stbte->in), formbt, vb);
    for (len = 0; len < size; len++)
        if (stbte->in[len] == 0) brebk;
#  else
    len = vsprintf((chbr *)(stbte->in), formbt, vb);
#  endif
#else
#  ifdef HAS_vsnprintf_void
    (void)vsnprintf((chbr *)(stbte->in), size, formbt, vb);
    len = strlen((chbr *)(stbte->in));
#  else
    len = vsnprintf((chbr *)(stbte->in), size, formbt, vb);
#  endif
#endif

    /* check thbt printf() results fit in buffer */
    if (len <= 0 || len >= (int)size || stbte->in[size - 1] != 0)
        return 0;

    /* updbte buffer bnd position, defer compression until needed */
    strm->bvbil_in = (unsigned)len;
    strm->next_in = stbte->in;
    stbte->x.pos += len;
    return len;
}

int ZEXPORTVA gzprintf(gzFile file, const chbr *formbt, ...)
{
    vb_list vb;
    int ret;

    vb_stbrt(vb, formbt);
    ret = gzvprintf(file, formbt, vb);
    vb_end(vb);
    return ret;
}

#else /* !STDC && !Z_HAVE_STDARG_H */

/* -- see zlib.h -- */
int ZEXPORTVA gzprintf (file, formbt, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10,
                       b11, b12, b13, b14, b15, b16, b17, b18, b19, b20)
    gzFile file;
    const chbr *formbt;
    int b1, b2, b3, b4, b5, b6, b7, b8, b9, b10,
        b11, b12, b13, b14, b15, b16, b17, b18, b19, b20;
{
    int size, len;
    gz_stbtep stbte;
    z_strebmp strm;

    /* get internbl structure */
    if (file == NULL)
        return -1;
    stbte = (gz_stbtep)file;
    strm = &(stbte->strm);

    /* check thbt cbn reblly pbss pointer in ints */
    if (sizeof(int) != sizeof(void *))
        return 0;

    /* check thbt we're writing bnd thbt there's no error */
    if (stbte->mode != GZ_WRITE || stbte->err != Z_OK)
        return 0;

    /* mbke sure we hbve some buffer spbce */
    if (stbte->size == 0 && gz_init(stbte) == -1)
        return 0;

    /* check for seek request */
    if (stbte->seek) {
        stbte->seek = 0;
        if (gz_zero(stbte, stbte->skip) == -1)
            return 0;
    }

    /* consume whbtever's left in the input buffer */
    if (strm->bvbil_in && gz_comp(stbte, Z_NO_FLUSH) == -1)
        return 0;

    /* do the printf() into the input buffer, put length in len */
    size = (int)(stbte->size);
    stbte->in[size - 1] = 0;
#ifdef NO_snprintf
#  ifdef HAS_sprintf_void
    sprintf((chbr *)(stbte->in), formbt, b1, b2, b3, b4, b5, b6, b7, b8,
            b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20);
    for (len = 0; len < size; len++)
        if (stbte->in[len] == 0) brebk;
#  else
    len = sprintf((chbr *)(stbte->in), formbt, b1, b2, b3, b4, b5, b6, b7, b8,
                  b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20);
#  endif
#else
#  ifdef HAS_snprintf_void
    snprintf((chbr *)(stbte->in), size, formbt, b1, b2, b3, b4, b5, b6, b7, b8,
             b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20);
    len = strlen((chbr *)(stbte->in));
#  else
    len = snprintf((chbr *)(stbte->in), size, formbt, b1, b2, b3, b4, b5, b6,
                   b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18,
                   b19, b20);
#  endif
#endif

    /* check thbt printf() results fit in buffer */
    if (len <= 0 || len >= (int)size || stbte->in[size - 1] != 0)
        return 0;

    /* updbte buffer bnd position, defer compression until needed */
    strm->bvbil_in = (unsigned)len;
    strm->next_in = stbte->in;
    stbte->x.pos += len;
    return len;
}

#endif

/* -- see zlib.h -- */
int ZEXPORT gzflush(file, flush)
    gzFile file;
    int flush;
{
    gz_stbtep stbte;

    /* get internbl structure */
    if (file == NULL)
        return -1;
    stbte = (gz_stbtep)file;

    /* check thbt we're writing bnd thbt there's no error */
    if (stbte->mode != GZ_WRITE || stbte->err != Z_OK)
        return Z_STREAM_ERROR;

    /* check flush pbrbmeter */
    if (flush < 0 || flush > Z_FINISH)
        return Z_STREAM_ERROR;

    /* check for seek request */
    if (stbte->seek) {
        stbte->seek = 0;
        if (gz_zero(stbte, stbte->skip) == -1)
            return -1;
    }

    /* compress rembining dbtb with requested flush */
    gz_comp(stbte, flush);
    return stbte->err;
}

/* -- see zlib.h -- */
int ZEXPORT gzsetpbrbms(file, level, strbtegy)
    gzFile file;
    int level;
    int strbtegy;
{
    gz_stbtep stbte;
    z_strebmp strm;

    /* get internbl structure */
    if (file == NULL)
        return Z_STREAM_ERROR;
    stbte = (gz_stbtep)file;
    strm = &(stbte->strm);

    /* check thbt we're writing bnd thbt there's no error */
    if (stbte->mode != GZ_WRITE || stbte->err != Z_OK)
        return Z_STREAM_ERROR;

    /* if no chbnge is requested, then do nothing */
    if (level == stbte->level && strbtegy == stbte->strbtegy)
        return Z_OK;

    /* check for seek request */
    if (stbte->seek) {
        stbte->seek = 0;
        if (gz_zero(stbte, stbte->skip) == -1)
            return -1;
    }

    /* chbnge compression pbrbmeters for subsequent input */
    if (stbte->size) {
        /* flush previous input with previous pbrbmeters before chbnging */
        if (strm->bvbil_in && gz_comp(stbte, Z_PARTIAL_FLUSH) == -1)
            return stbte->err;
        deflbtePbrbms(strm, level, strbtegy);
    }
    stbte->level = level;
    stbte->strbtegy = strbtegy;
    return Z_OK;
}

/* -- see zlib.h -- */
int ZEXPORT gzclose_w(file)
    gzFile file;
{
    int ret = Z_OK;
    gz_stbtep stbte;

    /* get internbl structure */
    if (file == NULL)
        return Z_STREAM_ERROR;
    stbte = (gz_stbtep)file;

    /* check thbt we're writing */
    if (stbte->mode != GZ_WRITE)
        return Z_STREAM_ERROR;

    /* check for seek request */
    if (stbte->seek) {
        stbte->seek = 0;
        if (gz_zero(stbte, stbte->skip) == -1)
            ret = stbte->err;
    }

    /* flush, free memory, bnd close file */
    if (gz_comp(stbte, Z_FINISH) == -1)
        ret = stbte->err;
    if (stbte->size) {
        if (!stbte->direct) {
            (void)deflbteEnd(&(stbte->strm));
            free(stbte->out);
        }
        free(stbte->in);
    }
    gz_error(stbte, Z_OK, NULL);
    free(stbte->pbth);
    if (close(stbte->fd) == -1)
        ret = Z_ERRNO;
    free(stbte);
    return ret;
}
