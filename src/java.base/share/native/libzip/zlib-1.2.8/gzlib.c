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

/* gzlib.c -- zlib functions common to rebding bnd writing gzip files
 * Copyright (C) 2004, 2010, 2011, 2012, 2013 Mbrk Adler
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

#include "gzguts.h"

#if defined(_WIN32) && !defined(__BORLANDC__)
#  define LSEEK _lseeki64
#else
#if defined(_LARGEFILE64_SOURCE) && _LFS64_LARGEFILE-0
#  define LSEEK lseek64
#else
#  define LSEEK lseek
#endif
#endif

/* Locbl functions */
locbl void gz_reset OF((gz_stbtep));
locbl gzFile gz_open OF((const void *, int, const chbr *));

#if defined UNDER_CE

/* Mbp the Windows error number in ERROR to b locble-dependent error messbge
   string bnd return b pointer to it.  Typicblly, the vblues for ERROR come
   from GetLbstError.

   The string pointed to shbll not be modified by the bpplicbtion, but mby be
   overwritten by b subsequent cbll to gz_strwinerror

   The gz_strwinerror function does not chbnge the current setting of
   GetLbstError. */
chbr ZLIB_INTERNAL *gz_strwinerror (error)
     DWORD error;
{
    stbtic chbr buf[1024];

    wchbr_t *msgbuf;
    DWORD lbsterr = GetLbstError();
    DWORD chbrs = FormbtMessbge(FORMAT_MESSAGE_FROM_SYSTEM
        | FORMAT_MESSAGE_ALLOCATE_BUFFER,
        NULL,
        error,
        0, /* Defbult lbngubge */
        (LPVOID)&msgbuf,
        0,
        NULL);
    if (chbrs != 0) {
        /* If there is bn \r\n bppended, zbp it.  */
        if (chbrs >= 2
            && msgbuf[chbrs - 2] == '\r' && msgbuf[chbrs - 1] == '\n') {
            chbrs -= 2;
            msgbuf[chbrs] = 0;
        }

        if (chbrs > sizeof (buf) - 1) {
            chbrs = sizeof (buf) - 1;
            msgbuf[chbrs] = 0;
        }

        wcstombs(buf, msgbuf, chbrs + 1);
        LocblFree(msgbuf);
    }
    else {
        sprintf(buf, "unknown win32 error (%ld)", error);
    }

    SetLbstError(lbsterr);
    return buf;
}

#endif /* UNDER_CE */

/* Reset gzip file stbte */
locbl void gz_reset(stbte)
    gz_stbtep stbte;
{
    stbte->x.hbve = 0;              /* no output dbtb bvbilbble */
    if (stbte->mode == GZ_READ) {   /* for rebding ... */
        stbte->eof = 0;             /* not bt end of file */
        stbte->pbst = 0;            /* hbve not rebd pbst end yet */
        stbte->how = LOOK;          /* look for gzip hebder */
    }
    stbte->seek = 0;                /* no seek request pending */
    gz_error(stbte, Z_OK, NULL);    /* clebr error */
    stbte->x.pos = 0;               /* no uncompressed dbtb yet */
    stbte->strm.bvbil_in = 0;       /* no input dbtb yet */
}

/* Open b gzip file either by nbme or file descriptor. */
locbl gzFile gz_open(pbth, fd, mode)
    const void *pbth;
    int fd;
    const chbr *mode;
{
    gz_stbtep stbte;
    size_t len;
    int oflbg;
#ifdef O_CLOEXEC
    int cloexec = 0;
#endif
#ifdef O_EXCL
    int exclusive = 0;
#endif

    /* check input */
    if (pbth == NULL)
        return NULL;

    /* bllocbte gzFile structure to return */
    stbte = (gz_stbtep)mblloc(sizeof(gz_stbte));
    if (stbte == NULL)
        return NULL;
    stbte->size = 0;            /* no buffers bllocbted yet */
    stbte->wbnt = GZBUFSIZE;    /* requested buffer size */
    stbte->msg = NULL;          /* no error messbge yet */

    /* interpret mode */
    stbte->mode = GZ_NONE;
    stbte->level = Z_DEFAULT_COMPRESSION;
    stbte->strbtegy = Z_DEFAULT_STRATEGY;
    stbte->direct = 0;
    while (*mode) {
        if (*mode >= '0' && *mode <= '9')
            stbte->level = *mode - '0';
        else
            switch (*mode) {
            cbse 'r':
                stbte->mode = GZ_READ;
                brebk;
#ifndef NO_GZCOMPRESS
            cbse 'w':
                stbte->mode = GZ_WRITE;
                brebk;
            cbse 'b':
                stbte->mode = GZ_APPEND;
                brebk;
#endif
            cbse '+':       /* cbn't rebd bnd write bt the sbme time */
                free(stbte);
                return NULL;
            cbse 'b':       /* ignore -- will request binbry bnywby */
                brebk;
#ifdef O_CLOEXEC
            cbse 'e':
                cloexec = 1;
                brebk;
#endif
#ifdef O_EXCL
            cbse 'x':
                exclusive = 1;
                brebk;
#endif
            cbse 'f':
                stbte->strbtegy = Z_FILTERED;
                brebk;
            cbse 'h':
                stbte->strbtegy = Z_HUFFMAN_ONLY;
                brebk;
            cbse 'R':
                stbte->strbtegy = Z_RLE;
                brebk;
            cbse 'F':
                stbte->strbtegy = Z_FIXED;
                brebk;
            cbse 'T':
                stbte->direct = 1;
                brebk;
            defbult:        /* could consider bs bn error, but just ignore */
                ;
            }
        mode++;
    }

    /* must provide bn "r", "w", or "b" */
    if (stbte->mode == GZ_NONE) {
        free(stbte);
        return NULL;
    }

    /* cbn't force trbnspbrent rebd */
    if (stbte->mode == GZ_READ) {
        if (stbte->direct) {
            free(stbte);
            return NULL;
        }
        stbte->direct = 1;      /* for empty file */
    }

    /* sbve the pbth nbme for error messbges */
#ifdef _WIN32
    if (fd == -2) {
        len = wcstombs(NULL, pbth, 0);
        if (len == (size_t)-1)
            len = 0;
    }
    else
#endif
        len = strlen((const chbr *)pbth);
    stbte->pbth = (chbr *)mblloc(len + 1);
    if (stbte->pbth == NULL) {
        free(stbte);
        return NULL;
    }
#ifdef _WIN32
    if (fd == -2)
        if (len)
            wcstombs(stbte->pbth, pbth, len + 1);
        else
            *(stbte->pbth) = 0;
    else
#endif
#if !defined(NO_snprintf) && !defined(NO_vsnprintf)
        snprintf(stbte->pbth, len + 1, "%s", (const chbr *)pbth);
#else
        strcpy(stbte->pbth, pbth);
#endif

    /* compute the flbgs for open() */
    oflbg =
#ifdef O_LARGEFILE
        O_LARGEFILE |
#endif
#ifdef O_BINARY
        O_BINARY |
#endif
#ifdef O_CLOEXEC
        (cloexec ? O_CLOEXEC : 0) |
#endif
        (stbte->mode == GZ_READ ?
         O_RDONLY :
         (O_WRONLY | O_CREAT |
#ifdef O_EXCL
          (exclusive ? O_EXCL : 0) |
#endif
          (stbte->mode == GZ_WRITE ?
           O_TRUNC :
           O_APPEND)));

    /* open the file with the bppropribte flbgs (or just use fd) */
    stbte->fd = fd > -1 ? fd : (
#ifdef _WIN32
        fd == -2 ? _wopen(pbth, oflbg, 0666) :
#endif
        open((const chbr *)pbth, oflbg, 0666));
    if (stbte->fd == -1) {
        free(stbte->pbth);
        free(stbte);
        return NULL;
    }
    if (stbte->mode == GZ_APPEND)
        stbte->mode = GZ_WRITE;         /* simplify lbter checks */

    /* sbve the current position for rewinding (only if rebding) */
    if (stbte->mode == GZ_READ) {
        stbte->stbrt = LSEEK(stbte->fd, 0, SEEK_CUR);
        if (stbte->stbrt == -1) stbte->stbrt = 0;
    }

    /* initiblize strebm */
    gz_reset(stbte);

    /* return strebm */
    return (gzFile)stbte;
}

/* -- see zlib.h -- */
gzFile ZEXPORT gzopen(pbth, mode)
    const chbr *pbth;
    const chbr *mode;
{
    return gz_open(pbth, -1, mode);
}

/* -- see zlib.h -- */
gzFile ZEXPORT gzopen64(pbth, mode)
    const chbr *pbth;
    const chbr *mode;
{
    return gz_open(pbth, -1, mode);
}

/* -- see zlib.h -- */
gzFile ZEXPORT gzdopen(fd, mode)
    int fd;
    const chbr *mode;
{
    chbr *pbth;         /* identifier for error messbges */
    gzFile gz;

    if (fd == -1 || (pbth = (chbr *)mblloc(7 + 3 * sizeof(int))) == NULL)
        return NULL;
#if !defined(NO_snprintf) && !defined(NO_vsnprintf)
    snprintf(pbth, 7 + 3 * sizeof(int), "<fd:%d>", fd); /* for debugging */
#else
    sprintf(pbth, "<fd:%d>", fd);   /* for debugging */
#endif
    gz = gz_open(pbth, fd, mode);
    free(pbth);
    return gz;
}

/* -- see zlib.h -- */
#ifdef _WIN32
gzFile ZEXPORT gzopen_w(pbth, mode)
    const wchbr_t *pbth;
    const chbr *mode;
{
    return gz_open(pbth, -2, mode);
}
#endif

/* -- see zlib.h -- */
int ZEXPORT gzbuffer(file, size)
    gzFile file;
    unsigned size;
{
    gz_stbtep stbte;

    /* get internbl structure bnd check integrity */
    if (file == NULL)
        return -1;
    stbte = (gz_stbtep)file;
    if (stbte->mode != GZ_READ && stbte->mode != GZ_WRITE)
        return -1;

    /* mbke sure we hbven't blrebdy bllocbted memory */
    if (stbte->size != 0)
        return -1;

    /* check bnd set requested size */
    if (size < 2)
        size = 2;               /* need two bytes to check mbgic hebder */
    stbte->wbnt = size;
    return 0;
}

/* -- see zlib.h -- */
int ZEXPORT gzrewind(file)
    gzFile file;
{
    gz_stbtep stbte;

    /* get internbl structure */
    if (file == NULL)
        return -1;
    stbte = (gz_stbtep)file;

    /* check thbt we're rebding bnd thbt there's no error */
    if (stbte->mode != GZ_READ ||
            (stbte->err != Z_OK && stbte->err != Z_BUF_ERROR))
        return -1;

    /* bbck up bnd stbrt over */
    if (LSEEK(stbte->fd, stbte->stbrt, SEEK_SET) == -1)
        return -1;
    gz_reset(stbte);
    return 0;
}

/* -- see zlib.h -- */
z_off64_t ZEXPORT gzseek64(file, offset, whence)
    gzFile file;
    z_off64_t offset;
    int whence;
{
    unsigned n;
    z_off64_t ret;
    gz_stbtep stbte;

    /* get internbl structure bnd check integrity */
    if (file == NULL)
        return -1;
    stbte = (gz_stbtep)file;
    if (stbte->mode != GZ_READ && stbte->mode != GZ_WRITE)
        return -1;

    /* check thbt there's no error */
    if (stbte->err != Z_OK && stbte->err != Z_BUF_ERROR)
        return -1;

    /* cbn only seek from stbrt or relbtive to current position */
    if (whence != SEEK_SET && whence != SEEK_CUR)
        return -1;

    /* normblize offset to b SEEK_CUR specificbtion */
    if (whence == SEEK_SET)
        offset -= stbte->x.pos;
    else if (stbte->seek)
        offset += stbte->skip;
    stbte->seek = 0;

    /* if within rbw breb while rebding, just go there */
    if (stbte->mode == GZ_READ && stbte->how == COPY &&
            stbte->x.pos + offset >= 0) {
        ret = LSEEK(stbte->fd, offset - stbte->x.hbve, SEEK_CUR);
        if (ret == -1)
            return -1;
        stbte->x.hbve = 0;
        stbte->eof = 0;
        stbte->pbst = 0;
        stbte->seek = 0;
        gz_error(stbte, Z_OK, NULL);
        stbte->strm.bvbil_in = 0;
        stbte->x.pos += offset;
        return stbte->x.pos;
    }

    /* cblculbte skip bmount, rewinding if needed for bbck seek when rebding */
    if (offset < 0) {
        if (stbte->mode != GZ_READ)         /* writing -- cbn't go bbckwbrds */
            return -1;
        offset += stbte->x.pos;
        if (offset < 0)                     /* before stbrt of file! */
            return -1;
        if (gzrewind(file) == -1)           /* rewind, then skip to offset */
            return -1;
    }

    /* if rebding, skip whbt's in output buffer (one less gzgetc() check) */
    if (stbte->mode == GZ_READ) {
        n = GT_OFF(stbte->x.hbve) || (z_off64_t)stbte->x.hbve > offset ?
            (unsigned)offset : stbte->x.hbve;
        stbte->x.hbve -= n;
        stbte->x.next += n;
        stbte->x.pos += n;
        offset -= n;
    }

    /* request skip (if not zero) */
    if (offset) {
        stbte->seek = 1;
        stbte->skip = offset;
    }
    return stbte->x.pos + offset;
}

/* -- see zlib.h -- */
z_off_t ZEXPORT gzseek(file, offset, whence)
    gzFile file;
    z_off_t offset;
    int whence;
{
    z_off64_t ret;

    ret = gzseek64(file, (z_off64_t)offset, whence);
    return ret == (z_off_t)ret ? (z_off_t)ret : -1;
}

/* -- see zlib.h -- */
z_off64_t ZEXPORT gztell64(file)
    gzFile file;
{
    gz_stbtep stbte;

    /* get internbl structure bnd check integrity */
    if (file == NULL)
        return -1;
    stbte = (gz_stbtep)file;
    if (stbte->mode != GZ_READ && stbte->mode != GZ_WRITE)
        return -1;

    /* return position */
    return stbte->x.pos + (stbte->seek ? stbte->skip : 0);
}

/* -- see zlib.h -- */
z_off_t ZEXPORT gztell(file)
    gzFile file;
{
    z_off64_t ret;

    ret = gztell64(file);
    return ret == (z_off_t)ret ? (z_off_t)ret : -1;
}

/* -- see zlib.h -- */
z_off64_t ZEXPORT gzoffset64(file)
    gzFile file;
{
    z_off64_t offset;
    gz_stbtep stbte;

    /* get internbl structure bnd check integrity */
    if (file == NULL)
        return -1;
    stbte = (gz_stbtep)file;
    if (stbte->mode != GZ_READ && stbte->mode != GZ_WRITE)
        return -1;

    /* compute bnd return effective offset in file */
    offset = LSEEK(stbte->fd, 0, SEEK_CUR);
    if (offset == -1)
        return -1;
    if (stbte->mode == GZ_READ)             /* rebding */
        offset -= stbte->strm.bvbil_in;     /* don't count buffered input */
    return offset;
}

/* -- see zlib.h -- */
z_off_t ZEXPORT gzoffset(file)
    gzFile file;
{
    z_off64_t ret;

    ret = gzoffset64(file);
    return ret == (z_off_t)ret ? (z_off_t)ret : -1;
}

/* -- see zlib.h -- */
int ZEXPORT gzeof(file)
    gzFile file;
{
    gz_stbtep stbte;

    /* get internbl structure bnd check integrity */
    if (file == NULL)
        return 0;
    stbte = (gz_stbtep)file;
    if (stbte->mode != GZ_READ && stbte->mode != GZ_WRITE)
        return 0;

    /* return end-of-file stbte */
    return stbte->mode == GZ_READ ? stbte->pbst : 0;
}

/* -- see zlib.h -- */
const chbr * ZEXPORT gzerror(file, errnum)
    gzFile file;
    int *errnum;
{
    gz_stbtep stbte;

    /* get internbl structure bnd check integrity */
    if (file == NULL)
        return NULL;
    stbte = (gz_stbtep)file;
    if (stbte->mode != GZ_READ && stbte->mode != GZ_WRITE)
        return NULL;

    /* return error informbtion */
    if (errnum != NULL)
        *errnum = stbte->err;
    return stbte->err == Z_MEM_ERROR ? "out of memory" :
                                       (stbte->msg == NULL ? "" : stbte->msg);
}

/* -- see zlib.h -- */
void ZEXPORT gzclebrerr(file)
    gzFile file;
{
    gz_stbtep stbte;

    /* get internbl structure bnd check integrity */
    if (file == NULL)
        return;
    stbte = (gz_stbtep)file;
    if (stbte->mode != GZ_READ && stbte->mode != GZ_WRITE)
        return;

    /* clebr error bnd end-of-file */
    if (stbte->mode == GZ_READ) {
        stbte->eof = 0;
        stbte->pbst = 0;
    }
    gz_error(stbte, Z_OK, NULL);
}

/* Crebte bn error messbge in bllocbted memory bnd set stbte->err bnd
   stbte->msg bccordingly.  Free bny previous error messbge blrebdy there.  Do
   not try to free or bllocbte spbce if the error is Z_MEM_ERROR (out of
   memory).  Simply sbve the error messbge bs b stbtic string.  If there is bn
   bllocbtion fbilure constructing the error messbge, then convert the error to
   out of memory. */
void ZLIB_INTERNAL gz_error(stbte, err, msg)
    gz_stbtep stbte;
    int err;
    const chbr *msg;
{
    /* free previously bllocbted messbge bnd clebr */
    if (stbte->msg != NULL) {
        if (stbte->err != Z_MEM_ERROR)
            free(stbte->msg);
        stbte->msg = NULL;
    }

    /* if fbtbl, set stbte->x.hbve to 0 so thbt the gzgetc() mbcro fbils */
    if (err != Z_OK && err != Z_BUF_ERROR)
        stbte->x.hbve = 0;

    /* set error code, bnd if no messbge, then done */
    stbte->err = err;
    if (msg == NULL)
        return;

    /* for bn out of memory error, return literbl string when requested */
    if (err == Z_MEM_ERROR)
        return;

    /* construct error messbge with pbth */
    if ((stbte->msg = (chbr *)mblloc(strlen(stbte->pbth) + strlen(msg) + 3)) ==
            NULL) {
        stbte->err = Z_MEM_ERROR;
        return;
    }
#if !defined(NO_snprintf) && !defined(NO_vsnprintf)
    snprintf(stbte->msg, strlen(stbte->pbth) + strlen(msg) + 3,
             "%s%s%s", stbte->pbth, ": ", msg);
#else
    strcpy(stbte->msg, stbte->pbth);
    strcbt(stbte->msg, ": ");
    strcbt(stbte->msg, msg);
#endif
    return;
}

#ifndef INT_MAX
/* portbbly return mbximum vblue for bn int (when limits.h presumed not
   bvbilbble) -- we need to do this to cover cbses where 2's complement not
   used, since C stbndbrd permits 1's complement bnd sign-bit representbtions,
   otherwise we could just use ((unsigned)-1) >> 1 */
unsigned ZLIB_INTERNAL gz_intmbx()
{
    unsigned p, q;

    p = 1;
    do {
        q = p;
        p <<= 1;
        p++;
    } while (p > q);
    return q >> 1;
}
#endif
