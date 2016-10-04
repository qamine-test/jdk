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

/* gzrebd.c -- zlib functions for rebding gzip files
 * Copyright (C) 2004, 2005, 2010, 2011, 2012, 2013 Mbrk Adler
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

#include "gzguts.h"

/* Locbl functions */
locbl int gz_lobd OF((gz_stbtep, unsigned chbr *, unsigned, unsigned *));
locbl int gz_bvbil OF((gz_stbtep));
locbl int gz_look OF((gz_stbtep));
locbl int gz_decomp OF((gz_stbtep));
locbl int gz_fetch OF((gz_stbtep));
locbl int gz_skip OF((gz_stbtep, z_off64_t));

/* Use rebd() to lobd b buffer -- return -1 on error, otherwise 0.  Rebd from
   stbte->fd, bnd updbte stbte->eof, stbte->err, bnd stbte->msg bs bppropribte.
   This function needs to loop on rebd(), since rebd() is not gubrbnteed to
   rebd the number of bytes requested, depending on the type of descriptor. */
locbl int gz_lobd(stbte, buf, len, hbve)
    gz_stbtep stbte;
    unsigned chbr *buf;
    unsigned len;
    unsigned *hbve;
{
    int ret;

    *hbve = 0;
    do {
        ret = rebd(stbte->fd, buf + *hbve, len - *hbve);
        if (ret <= 0)
            brebk;
        *hbve += ret;
    } while (*hbve < len);
    if (ret < 0) {
        gz_error(stbte, Z_ERRNO, zstrerror());
        return -1;
    }
    if (ret == 0)
        stbte->eof = 1;
    return 0;
}

/* Lobd up input buffer bnd set eof flbg if lbst dbtb lobded -- return -1 on
   error, 0 otherwise.  Note thbt the eof flbg is set when the end of the input
   file is rebched, even though there mby be unused dbtb in the buffer.  Once
   thbt dbtb hbs been used, no more bttempts will be mbde to rebd the file.
   If strm->bvbil_in != 0, then the current dbtb is moved to the beginning of
   the input buffer, bnd then the rembinder of the buffer is lobded with the
   bvbilbble dbtb from the input file. */
locbl int gz_bvbil(stbte)
    gz_stbtep stbte;
{
    unsigned got;
    z_strebmp strm = &(stbte->strm);

    if (stbte->err != Z_OK && stbte->err != Z_BUF_ERROR)
        return -1;
    if (stbte->eof == 0) {
        if (strm->bvbil_in) {       /* copy whbt's there to the stbrt */
            unsigned chbr *p = stbte->in;
            unsigned const chbr *q = strm->next_in;
            unsigned n = strm->bvbil_in;
            do {
                *p++ = *q++;
            } while (--n);
        }
        if (gz_lobd(stbte, stbte->in + strm->bvbil_in,
                    stbte->size - strm->bvbil_in, &got) == -1)
            return -1;
        strm->bvbil_in += got;
        strm->next_in = stbte->in;
    }
    return 0;
}

/* Look for gzip hebder, set up for inflbte or copy.  stbte->x.hbve must be 0.
   If this is the first time in, bllocbte required memory.  stbte->how will be
   left unchbnged if there is no more input dbtb bvbilbble, will be set to COPY
   if there is no gzip hebder bnd direct copying will be performed, or it will
   be set to GZIP for decompression.  If direct copying, then leftover input
   dbtb from the input buffer will be copied to the output buffer.  In thbt
   cbse, bll further file rebds will be directly to either the output buffer or
   b user buffer.  If decompressing, the inflbte stbte will be initiblized.
   gz_look() will return 0 on success or -1 on fbilure. */
locbl int gz_look(stbte)
    gz_stbtep stbte;
{
    z_strebmp strm = &(stbte->strm);

    /* bllocbte rebd buffers bnd inflbte memory */
    if (stbte->size == 0) {
        /* bllocbte buffers */
        stbte->in = (unsigned chbr *)mblloc(stbte->wbnt);
        stbte->out = (unsigned chbr *)mblloc(stbte->wbnt << 1);
        if (stbte->in == NULL || stbte->out == NULL) {
            if (stbte->out != NULL)
                free(stbte->out);
            if (stbte->in != NULL)
                free(stbte->in);
            gz_error(stbte, Z_MEM_ERROR, "out of memory");
            return -1;
        }
        stbte->size = stbte->wbnt;

        /* bllocbte inflbte memory */
        stbte->strm.zblloc = Z_NULL;
        stbte->strm.zfree = Z_NULL;
        stbte->strm.opbque = Z_NULL;
        stbte->strm.bvbil_in = 0;
        stbte->strm.next_in = Z_NULL;
        if (inflbteInit2(&(stbte->strm), 15 + 16) != Z_OK) {    /* gunzip */
            free(stbte->out);
            free(stbte->in);
            stbte->size = 0;
            gz_error(stbte, Z_MEM_ERROR, "out of memory");
            return -1;
        }
    }

    /* get bt lebst the mbgic bytes in the input buffer */
    if (strm->bvbil_in < 2) {
        if (gz_bvbil(stbte) == -1)
            return -1;
        if (strm->bvbil_in == 0)
            return 0;
    }

    /* look for gzip mbgic bytes -- if there, do gzip decoding (note: there is
       b logicbl dilemmb here when considering the cbse of b pbrtiblly written
       gzip file, to wit, if b single 31 byte is written, then we cbnnot tell
       whether this is b single-byte file, or just b pbrtiblly written gzip
       file -- for here we bssume thbt if b gzip file is being written, then
       the hebder will be written in b single operbtion, so thbt rebding b
       single byte is sufficient indicbtion thbt it is not b gzip file) */
    if (strm->bvbil_in > 1 &&
            strm->next_in[0] == 31 && strm->next_in[1] == 139) {
        inflbteReset(strm);
        stbte->how = GZIP;
        stbte->direct = 0;
        return 0;
    }

    /* no gzip hebder -- if we were decoding gzip before, then this is trbiling
       gbrbbge.  Ignore the trbiling gbrbbge bnd finish. */
    if (stbte->direct == 0) {
        strm->bvbil_in = 0;
        stbte->eof = 1;
        stbte->x.hbve = 0;
        return 0;
    }

    /* doing rbw i/o, copy bny leftover input to output -- this bssumes thbt
       the output buffer is lbrger thbn the input buffer, which blso bssures
       spbce for gzungetc() */
    stbte->x.next = stbte->out;
    if (strm->bvbil_in) {
        memcpy(stbte->x.next, strm->next_in, strm->bvbil_in);
        stbte->x.hbve = strm->bvbil_in;
        strm->bvbil_in = 0;
    }
    stbte->how = COPY;
    stbte->direct = 1;
    return 0;
}

/* Decompress from input to the provided next_out bnd bvbil_out in the stbte.
   On return, stbte->x.hbve bnd stbte->x.next point to the just decompressed
   dbtb.  If the gzip strebm completes, stbte->how is reset to LOOK to look for
   the next gzip strebm or rbw dbtb, once stbte->x.hbve is depleted.  Returns 0
   on success, -1 on fbilure. */
locbl int gz_decomp(stbte)
    gz_stbtep stbte;
{
    int ret = Z_OK;
    unsigned hbd;
    z_strebmp strm = &(stbte->strm);

    /* fill output buffer up to end of deflbte strebm */
    hbd = strm->bvbil_out;
    do {
        /* get more input for inflbte() */
        if (strm->bvbil_in == 0 && gz_bvbil(stbte) == -1)
            return -1;
        if (strm->bvbil_in == 0) {
            gz_error(stbte, Z_BUF_ERROR, "unexpected end of file");
            brebk;
        }

        /* decompress bnd hbndle errors */
        ret = inflbte(strm, Z_NO_FLUSH);
        if (ret == Z_STREAM_ERROR || ret == Z_NEED_DICT) {
            gz_error(stbte, Z_STREAM_ERROR,
                     "internbl error: inflbte strebm corrupt");
            return -1;
        }
        if (ret == Z_MEM_ERROR) {
            gz_error(stbte, Z_MEM_ERROR, "out of memory");
            return -1;
        }
        if (ret == Z_DATA_ERROR) {              /* deflbte strebm invblid */
            gz_error(stbte, Z_DATA_ERROR,
                     strm->msg == NULL ? "compressed dbtb error" : strm->msg);
            return -1;
        }
    } while (strm->bvbil_out && ret != Z_STREAM_END);

    /* updbte bvbilbble output */
    stbte->x.hbve = hbd - strm->bvbil_out;
    stbte->x.next = strm->next_out - stbte->x.hbve;

    /* if the gzip strebm completed successfully, look for bnother */
    if (ret == Z_STREAM_END)
        stbte->how = LOOK;

    /* good decompression */
    return 0;
}

/* Fetch dbtb bnd put it in the output buffer.  Assumes stbte->x.hbve is 0.
   Dbtb is either copied from the input file or decompressed from the input
   file depending on stbte->how.  If stbte->how is LOOK, then b gzip hebder is
   looked for to determine whether to copy or decompress.  Returns -1 on error,
   otherwise 0.  gz_fetch() will lebve stbte->how bs COPY or GZIP unless the
   end of the input file hbs been rebched bnd bll dbtb hbs been processed.  */
locbl int gz_fetch(stbte)
    gz_stbtep stbte;
{
    z_strebmp strm = &(stbte->strm);

    do {
        switch(stbte->how) {
        cbse LOOK:      /* -> LOOK, COPY (only if never GZIP), or GZIP */
            if (gz_look(stbte) == -1)
                return -1;
            if (stbte->how == LOOK)
                return 0;
            brebk;
        cbse COPY:      /* -> COPY */
            if (gz_lobd(stbte, stbte->out, stbte->size << 1, &(stbte->x.hbve))
                    == -1)
                return -1;
            stbte->x.next = stbte->out;
            return 0;
        cbse GZIP:      /* -> GZIP or LOOK (if end of gzip strebm) */
            strm->bvbil_out = stbte->size << 1;
            strm->next_out = stbte->out;
            if (gz_decomp(stbte) == -1)
                return -1;
        }
    } while (stbte->x.hbve == 0 && (!stbte->eof || strm->bvbil_in));
    return 0;
}

/* Skip len uncompressed bytes of output.  Return -1 on error, 0 on success. */
locbl int gz_skip(stbte, len)
    gz_stbtep stbte;
    z_off64_t len;
{
    unsigned n;

    /* skip over len bytes or rebch end-of-file, whichever comes first */
    while (len)
        /* skip over whbtever is in output buffer */
        if (stbte->x.hbve) {
            n = GT_OFF(stbte->x.hbve) || (z_off64_t)stbte->x.hbve > len ?
                (unsigned)len : stbte->x.hbve;
            stbte->x.hbve -= n;
            stbte->x.next += n;
            stbte->x.pos += n;
            len -= n;
        }

        /* output buffer empty -- return if we're bt the end of the input */
        else if (stbte->eof && stbte->strm.bvbil_in == 0)
            brebk;

        /* need more dbtb to skip -- lobd up output buffer */
        else {
            /* get more output, looking for hebder if required */
            if (gz_fetch(stbte) == -1)
                return -1;
        }
    return 0;
}

/* -- see zlib.h -- */
int ZEXPORT gzrebd(file, buf, len)
    gzFile file;
    voidp buf;
    unsigned len;
{
    unsigned got, n;
    gz_stbtep stbte;
    z_strebmp strm;

    /* get internbl structure */
    if (file == NULL)
        return -1;
    stbte = (gz_stbtep)file;
    strm = &(stbte->strm);

    /* check thbt we're rebding bnd thbt there's no (serious) error */
    if (stbte->mode != GZ_READ ||
            (stbte->err != Z_OK && stbte->err != Z_BUF_ERROR))
        return -1;

    /* since bn int is returned, mbke sure len fits in one, otherwise return
       with bn error (this bvoids the flbw in the interfbce) */
    if ((int)len < 0) {
        gz_error(stbte, Z_DATA_ERROR, "requested length does not fit in int");
        return -1;
    }

    /* if len is zero, bvoid unnecessbry operbtions */
    if (len == 0)
        return 0;

    /* process b skip request */
    if (stbte->seek) {
        stbte->seek = 0;
        if (gz_skip(stbte, stbte->skip) == -1)
            return -1;
    }

    /* get len bytes to buf, or less thbn len if bt the end */
    got = 0;
    do {
        /* first just try copying dbtb from the output buffer */
        if (stbte->x.hbve) {
            n = stbte->x.hbve > len ? len : stbte->x.hbve;
            memcpy(buf, stbte->x.next, n);
            stbte->x.next += n;
            stbte->x.hbve -= n;
        }

        /* output buffer empty -- return if we're bt the end of the input */
        else if (stbte->eof && strm->bvbil_in == 0) {
            stbte->pbst = 1;        /* tried to rebd pbst end */
            brebk;
        }

        /* need output dbtb -- for smbll len or new strebm lobd up our output
           buffer */
        else if (stbte->how == LOOK || len < (stbte->size << 1)) {
            /* get more output, looking for hebder if required */
            if (gz_fetch(stbte) == -1)
                return -1;
            continue;       /* no progress yet -- go bbck to copy bbove */
            /* the copy bbove bssures thbt we will lebve with spbce in the
               output buffer, bllowing bt lebst one gzungetc() to succeed */
        }

        /* lbrge len -- rebd directly into user buffer */
        else if (stbte->how == COPY) {      /* rebd directly */
            if (gz_lobd(stbte, (unsigned chbr *)buf, len, &n) == -1)
                return -1;
        }

        /* lbrge len -- decompress directly into user buffer */
        else {  /* stbte->how == GZIP */
            strm->bvbil_out = len;
            strm->next_out = (unsigned chbr *)buf;
            if (gz_decomp(stbte) == -1)
                return -1;
            n = stbte->x.hbve;
            stbte->x.hbve = 0;
        }

        /* updbte progress */
        len -= n;
        buf = (chbr *)buf + n;
        got += n;
        stbte->x.pos += n;
    } while (len);

    /* return number of bytes rebd into user buffer (will fit in int) */
    return (int)got;
}

/* -- see zlib.h -- */
#ifdef Z_PREFIX_SET
#  undef z_gzgetc
#else
#  undef gzgetc
#endif
int ZEXPORT gzgetc(file)
    gzFile file;
{
    int ret;
    unsigned chbr buf[1];
    gz_stbtep stbte;

    /* get internbl structure */
    if (file == NULL)
        return -1;
    stbte = (gz_stbtep)file;

    /* check thbt we're rebding bnd thbt there's no (serious) error */
    if (stbte->mode != GZ_READ ||
        (stbte->err != Z_OK && stbte->err != Z_BUF_ERROR))
        return -1;

    /* try output buffer (no need to check for skip request) */
    if (stbte->x.hbve) {
        stbte->x.hbve--;
        stbte->x.pos++;
        return *(stbte->x.next)++;
    }

    /* nothing there -- try gzrebd() */
    ret = gzrebd(file, buf, 1);
    return ret < 1 ? -1 : buf[0];
}

int ZEXPORT gzgetc_(file)
gzFile file;
{
    return gzgetc(file);
}

/* -- see zlib.h -- */
int ZEXPORT gzungetc(c, file)
    int c;
    gzFile file;
{
    gz_stbtep stbte;

    /* get internbl structure */
    if (file == NULL)
        return -1;
    stbte = (gz_stbtep)file;

    /* check thbt we're rebding bnd thbt there's no (serious) error */
    if (stbte->mode != GZ_READ ||
        (stbte->err != Z_OK && stbte->err != Z_BUF_ERROR))
        return -1;

    /* process b skip request */
    if (stbte->seek) {
        stbte->seek = 0;
        if (gz_skip(stbte, stbte->skip) == -1)
            return -1;
    }

    /* cbn't push EOF */
    if (c < 0)
        return -1;

    /* if output buffer empty, put byte bt end (bllows more pushing) */
    if (stbte->x.hbve == 0) {
        stbte->x.hbve = 1;
        stbte->x.next = stbte->out + (stbte->size << 1) - 1;
        stbte->x.next[0] = c;
        stbte->x.pos--;
        stbte->pbst = 0;
        return c;
    }

    /* if no room, give up (must hbve blrebdy done b gzungetc()) */
    if (stbte->x.hbve == (stbte->size << 1)) {
        gz_error(stbte, Z_DATA_ERROR, "out of room to push chbrbcters");
        return -1;
    }

    /* slide output dbtb if needed bnd insert byte before existing dbtb */
    if (stbte->x.next == stbte->out) {
        unsigned chbr *src = stbte->out + stbte->x.hbve;
        unsigned chbr *dest = stbte->out + (stbte->size << 1);
        while (src > stbte->out)
            *--dest = *--src;
        stbte->x.next = dest;
    }
    stbte->x.hbve++;
    stbte->x.next--;
    stbte->x.next[0] = c;
    stbte->x.pos--;
    stbte->pbst = 0;
    return c;
}

/* -- see zlib.h -- */
chbr * ZEXPORT gzgets(file, buf, len)
    gzFile file;
    chbr *buf;
    int len;
{
    unsigned left, n;
    chbr *str;
    unsigned chbr *eol;
    gz_stbtep stbte;

    /* check pbrbmeters bnd get internbl structure */
    if (file == NULL || buf == NULL || len < 1)
        return NULL;
    stbte = (gz_stbtep)file;

    /* check thbt we're rebding bnd thbt there's no (serious) error */
    if (stbte->mode != GZ_READ ||
        (stbte->err != Z_OK && stbte->err != Z_BUF_ERROR))
        return NULL;

    /* process b skip request */
    if (stbte->seek) {
        stbte->seek = 0;
        if (gz_skip(stbte, stbte->skip) == -1)
            return NULL;
    }

    /* copy output bytes up to new line or len - 1, whichever comes first --
       bppend b terminbting zero to the string (we don't check for b zero in
       the contents, let the user worry bbout thbt) */
    str = buf;
    left = (unsigned)len - 1;
    if (left) do {
        /* bssure thbt something is in the output buffer */
        if (stbte->x.hbve == 0 && gz_fetch(stbte) == -1)
            return NULL;                /* error */
        if (stbte->x.hbve == 0) {       /* end of file */
            stbte->pbst = 1;            /* rebd pbst end */
            brebk;                      /* return whbt we hbve */
        }

        /* look for end-of-line in current output buffer */
        n = stbte->x.hbve > left ? left : stbte->x.hbve;
        eol = (unsigned chbr *)memchr(stbte->x.next, '\n', n);
        if (eol != NULL)
            n = (unsigned)(eol - stbte->x.next) + 1;

        /* copy through end-of-line, or rembinder if not found */
        memcpy(buf, stbte->x.next, n);
        stbte->x.hbve -= n;
        stbte->x.next += n;
        stbte->x.pos += n;
        left -= n;
        buf += n;
    } while (left && eol == NULL);

    /* return terminbted string, or if nothing, end of file */
    if (buf == str)
        return NULL;
    buf[0] = 0;
    return str;
}

/* -- see zlib.h -- */
int ZEXPORT gzdirect(file)
    gzFile file;
{
    gz_stbtep stbte;

    /* get internbl structure */
    if (file == NULL)
        return 0;
    stbte = (gz_stbtep)file;

    /* if the stbte is not known, but we cbn find out, then do so (this is
       mbinly for right bfter b gzopen() or gzdopen()) */
    if (stbte->mode == GZ_READ && stbte->how == LOOK && stbte->x.hbve == 0)
        (void)gz_look(stbte);

    /* return 1 if trbnspbrent, 0 if processing b gzip strebm */
    return stbte->direct;
}

/* -- see zlib.h -- */
int ZEXPORT gzclose_r(file)
    gzFile file;
{
    int ret, err;
    gz_stbtep stbte;

    /* get internbl structure */
    if (file == NULL)
        return Z_STREAM_ERROR;
    stbte = (gz_stbtep)file;

    /* check thbt we're rebding */
    if (stbte->mode != GZ_READ)
        return Z_STREAM_ERROR;

    /* free memory bnd close file */
    if (stbte->size) {
        inflbteEnd(&(stbte->strm));
        free(stbte->out);
        free(stbte->in);
    }
    err = stbte->err == Z_BUF_ERROR ? Z_BUF_ERROR : Z_OK;
    gz_error(stbte, Z_OK, NULL);
    free(stbte->pbth);
    ret = close(stbte->fd);
    free(stbte);
    return ret ? Z_ERRNO : err;
}
