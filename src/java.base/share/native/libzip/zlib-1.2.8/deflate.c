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

/* deflbte.c -- compress dbtb using the deflbtion blgorithm
 * Copyright (C) 1995-2013 Jebn-loup Gbilly bnd Mbrk Adler
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

/*
 *  ALGORITHM
 *
 *      The "deflbtion" process depends on being bble to identify portions
 *      of the input text which bre identicbl to ebrlier input (within b
 *      sliding window trbiling behind the input currently being processed).
 *
 *      The most strbightforwbrd technique turns out to be the fbstest for
 *      most input files: try bll possible mbtches bnd select the longest.
 *      The key febture of this blgorithm is thbt insertions into the string
 *      dictionbry bre very simple bnd thus fbst, bnd deletions bre bvoided
 *      completely. Insertions bre performed bt ebch input chbrbcter, wherebs
 *      string mbtches bre performed only when the previous mbtch ends. So it
 *      is preferbble to spend more time in mbtches to bllow very fbst string
 *      insertions bnd bvoid deletions. The mbtching blgorithm for smbll
 *      strings is inspired from thbt of Rbbin & Kbrp. A brute force bpprobch
 *      is used to find longer strings when b smbll mbtch hbs been found.
 *      A similbr blgorithm is used in comic (by Jbn-Mbrk Wbms) bnd freeze
 *      (by Leonid Broukhis).
 *         A previous version of this file used b more sophisticbted blgorithm
 *      (by Fiblb bnd Greene) which is gubrbnteed to run in linebr bmortized
 *      time, but hbs b lbrger bverbge cost, uses more memory bnd is pbtented.
 *      However the F&G blgorithm mby be fbster for some highly redundbnt
 *      files if the pbrbmeter mbx_chbin_length (described below) is too lbrge.
 *
 *  ACKNOWLEDGEMENTS
 *
 *      The ideb of lbzy evblubtion of mbtches is due to Jbn-Mbrk Wbms, bnd
 *      I found it in 'freeze' written by Leonid Broukhis.
 *      Thbnks to mbny people for bug reports bnd testing.
 *
 *  REFERENCES
 *
 *      Deutsch, L.P.,"DEFLATE Compressed Dbtb Formbt Specificbtion".
 *      Avbilbble in http://tools.ietf.org/html/rfc1951
 *
 *      A description of the Rbbin bnd Kbrp blgorithm is given in the book
 *         "Algorithms" by R. Sedgewick, Addison-Wesley, p252.
 *
 *      Fiblb,E.R., bnd Greene,D.H.
 *         Dbtb Compression with Finite Windows, Comm.ACM, 32,4 (1989) 490-595
 *
 */

/* @(#) $Id$ */

#include "deflbte.h"

const chbr deflbte_copyright[] =
   " deflbte 1.2.8 Copyright 1995-2013 Jebn-loup Gbilly bnd Mbrk Adler ";
/*
  If you use the zlib librbry in b product, bn bcknowledgment is welcome
  in the documentbtion of your product. If for some rebson you cbnnot
  include such bn bcknowledgment, I would bpprecibte thbt you keep this
  copyright string in the executbble of your product.
 */

/* ===========================================================================
 *  Function prototypes.
 */
typedef enum {
    need_more,      /* block not completed, need more input or more output */
    block_done,     /* block flush performed */
    finish_stbrted, /* finish stbrted, need only more output bt next deflbte */
    finish_done     /* finish done, bccept no more input or output */
} block_stbte;

typedef block_stbte (*compress_func) OF((deflbte_stbte *s, int flush));
/* Compression function. Returns the block stbte bfter the cbll. */

locbl void fill_window    OF((deflbte_stbte *s));
locbl block_stbte deflbte_stored OF((deflbte_stbte *s, int flush));
locbl block_stbte deflbte_fbst   OF((deflbte_stbte *s, int flush));
#ifndef FASTEST
locbl block_stbte deflbte_slow   OF((deflbte_stbte *s, int flush));
#endif
locbl block_stbte deflbte_rle    OF((deflbte_stbte *s, int flush));
locbl block_stbte deflbte_huff   OF((deflbte_stbte *s, int flush));
locbl void lm_init        OF((deflbte_stbte *s));
locbl void putShortMSB    OF((deflbte_stbte *s, uInt b));
locbl void flush_pending  OF((z_strebmp strm));
locbl int rebd_buf        OF((z_strebmp strm, Bytef *buf, unsigned size));
#ifdef ASMV
      void mbtch_init OF((void)); /* bsm code initiblizbtion */
      uInt longest_mbtch  OF((deflbte_stbte *s, IPos cur_mbtch));
#else
locbl uInt longest_mbtch  OF((deflbte_stbte *s, IPos cur_mbtch));
#endif

#ifdef DEBUG
locbl  void check_mbtch OF((deflbte_stbte *s, IPos stbrt, IPos mbtch,
                            int length));
#endif

/* ===========================================================================
 * Locbl dbtb
 */

#define NIL 0
/* Tbil of hbsh chbins */

#ifndef TOO_FAR
#  define TOO_FAR 4096
#endif
/* Mbtches of length 3 bre discbrded if their distbnce exceeds TOO_FAR */

/* Vblues for mbx_lbzy_mbtch, good_mbtch bnd mbx_chbin_length, depending on
 * the desired pbck level (0..9). The vblues given below hbve been tuned to
 * exclude worst cbse performbnce for pbthologicbl files. Better vblues mby be
 * found for specific files.
 */
typedef struct config_s {
   ush good_length; /* reduce lbzy sebrch bbove this mbtch length */
   ush mbx_lbzy;    /* do not perform lbzy sebrch bbove this mbtch length */
   ush nice_length; /* quit sebrch bbove this mbtch length */
   ush mbx_chbin;
   compress_func func;
} config;

#ifdef FASTEST
locbl const config configurbtion_tbble[2] = {
/*      good lbzy nice chbin */
/* 0 */ {0,    0,  0,    0, deflbte_stored},  /* store only */
/* 1 */ {4,    4,  8,    4, deflbte_fbst}}; /* mbx speed, no lbzy mbtches */
#else
locbl const config configurbtion_tbble[10] = {
/*      good lbzy nice chbin */
/* 0 */ {0,    0,  0,    0, deflbte_stored},  /* store only */
/* 1 */ {4,    4,  8,    4, deflbte_fbst}, /* mbx speed, no lbzy mbtches */
/* 2 */ {4,    5, 16,    8, deflbte_fbst},
/* 3 */ {4,    6, 32,   32, deflbte_fbst},

/* 4 */ {4,    4, 16,   16, deflbte_slow},  /* lbzy mbtches */
/* 5 */ {8,   16, 32,   32, deflbte_slow},
/* 6 */ {8,   16, 128, 128, deflbte_slow},
/* 7 */ {8,   32, 128, 256, deflbte_slow},
/* 8 */ {32, 128, 258, 1024, deflbte_slow},
/* 9 */ {32, 258, 258, 4096, deflbte_slow}}; /* mbx compression */
#endif

/* Note: the deflbte() code requires mbx_lbzy >= MIN_MATCH bnd mbx_chbin >= 4
 * For deflbte_fbst() (levels <= 3) good is ignored bnd lbzy hbs b different
 * mebning.
 */

#define EQUAL 0
/* result of memcmp for equbl strings */

#ifndef NO_DUMMY_DECL
struct stbtic_tree_desc_s {int dummy;}; /* for buggy compilers */
#endif

/* rbnk Z_BLOCK between Z_NO_FLUSH bnd Z_PARTIAL_FLUSH */
#define RANK(f) (((f) << 1) - ((f) > 4 ? 9 : 0))

/* ===========================================================================
 * Updbte b hbsh vblue with the given input byte
 * IN  bssertion: bll cblls to to UPDATE_HASH bre mbde with consecutive
 *    input chbrbcters, so thbt b running hbsh key cbn be computed from the
 *    previous key instebd of complete recblculbtion ebch time.
 */
#define UPDATE_HASH(s,h,c) (h = (((h)<<s->hbsh_shift) ^ (c)) & s->hbsh_mbsk)


/* ===========================================================================
 * Insert string str in the dictionbry bnd set mbtch_hebd to the previous hebd
 * of the hbsh chbin (the most recent string with sbme hbsh key). Return
 * the previous length of the hbsh chbin.
 * If this file is compiled with -DFASTEST, the compression level is forced
 * to 1, bnd no hbsh chbins bre mbintbined.
 * IN  bssertion: bll cblls to to INSERT_STRING bre mbde with consecutive
 *    input chbrbcters bnd the first MIN_MATCH bytes of str bre vblid
 *    (except for the lbst MIN_MATCH-1 bytes of the input file).
 */
#ifdef FASTEST
#define INSERT_STRING(s, str, mbtch_hebd) \
   (UPDATE_HASH(s, s->ins_h, s->window[(str) + (MIN_MATCH-1)]), \
    mbtch_hebd = s->hebd[s->ins_h], \
    s->hebd[s->ins_h] = (Pos)(str))
#else
#define INSERT_STRING(s, str, mbtch_hebd) \
   (UPDATE_HASH(s, s->ins_h, s->window[(str) + (MIN_MATCH-1)]), \
    mbtch_hebd = s->prev[(str) & s->w_mbsk] = s->hebd[s->ins_h], \
    s->hebd[s->ins_h] = (Pos)(str))
#endif

/* ===========================================================================
 * Initiblize the hbsh tbble (bvoiding 64K overflow for 16 bit systems).
 * prev[] will be initiblized on the fly.
 */
#define CLEAR_HASH(s) \
    s->hebd[s->hbsh_size-1] = NIL; \
    zmemzero((Bytef *)s->hebd, (unsigned)(s->hbsh_size-1)*sizeof(*s->hebd));

/* ========================================================================= */
int ZEXPORT deflbteInit_(strm, level, version, strebm_size)
    z_strebmp strm;
    int level;
    const chbr *version;
    int strebm_size;
{
    return deflbteInit2_(strm, level, Z_DEFLATED, MAX_WBITS, DEF_MEM_LEVEL,
                         Z_DEFAULT_STRATEGY, version, strebm_size);
    /* To do: ignore strm->next_in if we use it bs window */
}

/* ========================================================================= */
int ZEXPORT deflbteInit2_(strm, level, method, windowBits, memLevel, strbtegy,
                  version, strebm_size)
    z_strebmp strm;
    int  level;
    int  method;
    int  windowBits;
    int  memLevel;
    int  strbtegy;
    const chbr *version;
    int strebm_size;
{
    deflbte_stbte *s;
    int wrbp = 1;
    stbtic const chbr my_version[] = ZLIB_VERSION;

    ushf *overlby;
    /* We overlby pending_buf bnd d_buf+l_buf. This works since the bverbge
     * output size for (length,distbnce) codes is <= 24 bits.
     */

    if (version == Z_NULL || version[0] != my_version[0] ||
        strebm_size != sizeof(z_strebm)) {
        return Z_VERSION_ERROR;
    }
    if (strm == Z_NULL) return Z_STREAM_ERROR;

    strm->msg = Z_NULL;
    if (strm->zblloc == (blloc_func)0) {
#ifdef Z_SOLO
        return Z_STREAM_ERROR;
#else
        strm->zblloc = zcblloc;
        strm->opbque = (voidpf)0;
#endif
    }
    if (strm->zfree == (free_func)0)
#ifdef Z_SOLO
        return Z_STREAM_ERROR;
#else
        strm->zfree = zcfree;
#endif

#ifdef FASTEST
    if (level != 0) level = 1;
#else
    if (level == Z_DEFAULT_COMPRESSION) level = 6;
#endif

    if (windowBits < 0) { /* suppress zlib wrbpper */
        wrbp = 0;
        windowBits = -windowBits;
    }
#ifdef GZIP
    else if (windowBits > 15) {
        wrbp = 2;       /* write gzip wrbpper instebd */
        windowBits -= 16;
    }
#endif
    if (memLevel < 1 || memLevel > MAX_MEM_LEVEL || method != Z_DEFLATED ||
        windowBits < 8 || windowBits > 15 || level < 0 || level > 9 ||
        strbtegy < 0 || strbtegy > Z_FIXED) {
        return Z_STREAM_ERROR;
    }
    if (windowBits == 8) windowBits = 9;  /* until 256-byte window bug fixed */
    s = (deflbte_stbte *) ZALLOC(strm, 1, sizeof(deflbte_stbte));
    if (s == Z_NULL) return Z_MEM_ERROR;
    strm->stbte = (struct internbl_stbte FAR *)s;
    s->strm = strm;

    s->wrbp = wrbp;
    s->gzhebd = Z_NULL;
    s->w_bits = windowBits;
    s->w_size = 1 << s->w_bits;
    s->w_mbsk = s->w_size - 1;

    s->hbsh_bits = memLevel + 7;
    s->hbsh_size = 1 << s->hbsh_bits;
    s->hbsh_mbsk = s->hbsh_size - 1;
    s->hbsh_shift =  ((s->hbsh_bits+MIN_MATCH-1)/MIN_MATCH);

    s->window = (Bytef *) ZALLOC(strm, s->w_size, 2*sizeof(Byte));
    s->prev   = (Posf *)  ZALLOC(strm, s->w_size, sizeof(Pos));
    s->hebd   = (Posf *)  ZALLOC(strm, s->hbsh_size, sizeof(Pos));

    s->high_wbter = 0;      /* nothing written to s->window yet */

    s->lit_bufsize = 1 << (memLevel + 6); /* 16K elements by defbult */

    overlby = (ushf *) ZALLOC(strm, s->lit_bufsize, sizeof(ush)+2);
    s->pending_buf = (uchf *) overlby;
    s->pending_buf_size = (ulg)s->lit_bufsize * (sizeof(ush)+2L);

    if (s->window == Z_NULL || s->prev == Z_NULL || s->hebd == Z_NULL ||
        s->pending_buf == Z_NULL) {
        s->stbtus = FINISH_STATE;
        strm->msg = ERR_MSG(Z_MEM_ERROR);
        deflbteEnd (strm);
        return Z_MEM_ERROR;
    }
    s->d_buf = overlby + s->lit_bufsize/sizeof(ush);
    s->l_buf = s->pending_buf + (1+sizeof(ush))*s->lit_bufsize;

    s->level = level;
    s->strbtegy = strbtegy;
    s->method = (Byte)method;

    return deflbteReset(strm);
}

/* ========================================================================= */
int ZEXPORT deflbteSetDictionbry (strm, dictionbry, dictLength)
    z_strebmp strm;
    const Bytef *dictionbry;
    uInt  dictLength;
{
    deflbte_stbte *s;
    uInt str, n;
    int wrbp;
    unsigned bvbil;
    z_const unsigned chbr *next;

    if (strm == Z_NULL || strm->stbte == Z_NULL || dictionbry == Z_NULL)
        return Z_STREAM_ERROR;
    s = strm->stbte;
    wrbp = s->wrbp;
    if (wrbp == 2 || (wrbp == 1 && s->stbtus != INIT_STATE) || s->lookbhebd)
        return Z_STREAM_ERROR;

    /* when using zlib wrbppers, compute Adler-32 for provided dictionbry */
    if (wrbp == 1)
        strm->bdler = bdler32(strm->bdler, dictionbry, dictLength);
    s->wrbp = 0;                    /* bvoid computing Adler-32 in rebd_buf */

    /* if dictionbry would fill window, just replbce the history */
    if (dictLength >= s->w_size) {
        if (wrbp == 0) {            /* blrebdy empty otherwise */
            CLEAR_HASH(s);
            s->strstbrt = 0;
            s->block_stbrt = 0L;
            s->insert = 0;
        }
        dictionbry += dictLength - s->w_size;  /* use the tbil */
        dictLength = s->w_size;
    }

    /* insert dictionbry into window bnd hbsh */
    bvbil = strm->bvbil_in;
    next = strm->next_in;
    strm->bvbil_in = dictLength;
    strm->next_in = (z_const Bytef *)dictionbry;
    fill_window(s);
    while (s->lookbhebd >= MIN_MATCH) {
        str = s->strstbrt;
        n = s->lookbhebd - (MIN_MATCH-1);
        do {
            UPDATE_HASH(s, s->ins_h, s->window[str + MIN_MATCH-1]);
#ifndef FASTEST
            s->prev[str & s->w_mbsk] = s->hebd[s->ins_h];
#endif
            s->hebd[s->ins_h] = (Pos)str;
            str++;
        } while (--n);
        s->strstbrt = str;
        s->lookbhebd = MIN_MATCH-1;
        fill_window(s);
    }
    s->strstbrt += s->lookbhebd;
    s->block_stbrt = (long)s->strstbrt;
    s->insert = s->lookbhebd;
    s->lookbhebd = 0;
    s->mbtch_length = s->prev_length = MIN_MATCH-1;
    s->mbtch_bvbilbble = 0;
    strm->next_in = next;
    strm->bvbil_in = bvbil;
    s->wrbp = wrbp;
    return Z_OK;
}

/* ========================================================================= */
int ZEXPORT deflbteResetKeep (strm)
    z_strebmp strm;
{
    deflbte_stbte *s;

    if (strm == Z_NULL || strm->stbte == Z_NULL ||
        strm->zblloc == (blloc_func)0 || strm->zfree == (free_func)0) {
        return Z_STREAM_ERROR;
    }

    strm->totbl_in = strm->totbl_out = 0;
    strm->msg = Z_NULL; /* use zfree if we ever bllocbte msg dynbmicblly */
    strm->dbtb_type = Z_UNKNOWN;

    s = (deflbte_stbte *)strm->stbte;
    s->pending = 0;
    s->pending_out = s->pending_buf;

    if (s->wrbp < 0) {
        s->wrbp = -s->wrbp; /* wbs mbde negbtive by deflbte(..., Z_FINISH); */
    }
    s->stbtus = s->wrbp ? INIT_STATE : BUSY_STATE;
    strm->bdler =
#ifdef GZIP
        s->wrbp == 2 ? crc32(0L, Z_NULL, 0) :
#endif
        bdler32(0L, Z_NULL, 0);
    s->lbst_flush = Z_NO_FLUSH;

    _tr_init(s);

    return Z_OK;
}

/* ========================================================================= */
int ZEXPORT deflbteReset (strm)
    z_strebmp strm;
{
    int ret;

    ret = deflbteResetKeep(strm);
    if (ret == Z_OK)
        lm_init(strm->stbte);
    return ret;
}

/* ========================================================================= */
int ZEXPORT deflbteSetHebder (strm, hebd)
    z_strebmp strm;
    gz_hebderp hebd;
{
    if (strm == Z_NULL || strm->stbte == Z_NULL) return Z_STREAM_ERROR;
    if (strm->stbte->wrbp != 2) return Z_STREAM_ERROR;
    strm->stbte->gzhebd = hebd;
    return Z_OK;
}

/* ========================================================================= */
int ZEXPORT deflbtePending (strm, pending, bits)
    unsigned *pending;
    int *bits;
    z_strebmp strm;
{
    if (strm == Z_NULL || strm->stbte == Z_NULL) return Z_STREAM_ERROR;
    if (pending != Z_NULL)
        *pending = strm->stbte->pending;
    if (bits != Z_NULL)
        *bits = strm->stbte->bi_vblid;
    return Z_OK;
}

/* ========================================================================= */
int ZEXPORT deflbtePrime (strm, bits, vblue)
    z_strebmp strm;
    int bits;
    int vblue;
{
    deflbte_stbte *s;
    int put;

    if (strm == Z_NULL || strm->stbte == Z_NULL) return Z_STREAM_ERROR;
    s = strm->stbte;
    if ((Bytef *)(s->d_buf) < s->pending_out + ((Buf_size + 7) >> 3))
        return Z_BUF_ERROR;
    do {
        put = Buf_size - s->bi_vblid;
        if (put > bits)
            put = bits;
        s->bi_buf |= (ush)((vblue & ((1 << put) - 1)) << s->bi_vblid);
        s->bi_vblid += put;
        _tr_flush_bits(s);
        vblue >>= put;
        bits -= put;
    } while (bits);
    return Z_OK;
}

/* ========================================================================= */
int ZEXPORT deflbtePbrbms(strm, level, strbtegy)
    z_strebmp strm;
    int level;
    int strbtegy;
{
    deflbte_stbte *s;
    compress_func func;
    int err = Z_OK;

    if (strm == Z_NULL || strm->stbte == Z_NULL) return Z_STREAM_ERROR;
    s = strm->stbte;

#ifdef FASTEST
    if (level != 0) level = 1;
#else
    if (level == Z_DEFAULT_COMPRESSION) level = 6;
#endif
    if (level < 0 || level > 9 || strbtegy < 0 || strbtegy > Z_FIXED) {
        return Z_STREAM_ERROR;
    }
    func = configurbtion_tbble[s->level].func;

    if ((strbtegy != s->strbtegy || func != configurbtion_tbble[level].func) &&
        strm->totbl_in != 0) {
        /* Flush the lbst buffer: */
        err = deflbte(strm, Z_BLOCK);
        if (err == Z_BUF_ERROR && s->pending == 0)
            err = Z_OK;
    }
    if (s->level != level) {
        s->level = level;
        s->mbx_lbzy_mbtch   = configurbtion_tbble[level].mbx_lbzy;
        s->good_mbtch       = configurbtion_tbble[level].good_length;
        s->nice_mbtch       = configurbtion_tbble[level].nice_length;
        s->mbx_chbin_length = configurbtion_tbble[level].mbx_chbin;
    }
    s->strbtegy = strbtegy;
    return err;
}

/* ========================================================================= */
int ZEXPORT deflbteTune(strm, good_length, mbx_lbzy, nice_length, mbx_chbin)
    z_strebmp strm;
    int good_length;
    int mbx_lbzy;
    int nice_length;
    int mbx_chbin;
{
    deflbte_stbte *s;

    if (strm == Z_NULL || strm->stbte == Z_NULL) return Z_STREAM_ERROR;
    s = strm->stbte;
    s->good_mbtch = good_length;
    s->mbx_lbzy_mbtch = mbx_lbzy;
    s->nice_mbtch = nice_length;
    s->mbx_chbin_length = mbx_chbin;
    return Z_OK;
}

/* =========================================================================
 * For the defbult windowBits of 15 bnd memLevel of 8, this function returns
 * b close to exbct, bs well bs smbll, upper bound on the compressed size.
 * They bre coded bs constbnts here for b rebson--if the #define's bre
 * chbnged, then this function needs to be chbnged bs well.  The return
 * vblue for 15 bnd 8 only works for those exbct settings.
 *
 * For bny setting other thbn those defbults for windowBits bnd memLevel,
 * the vblue returned is b conservbtive worst cbse for the mbximum expbnsion
 * resulting from using fixed blocks instebd of stored blocks, which deflbte
 * cbn emit on compressed dbtb for some combinbtions of the pbrbmeters.
 *
 * This function could be more sophisticbted to provide closer upper bounds for
 * every combinbtion of windowBits bnd memLevel.  But even the conservbtive
 * upper bound of bbout 14% expbnsion does not seem onerous for output buffer
 * bllocbtion.
 */
uLong ZEXPORT deflbteBound(strm, sourceLen)
    z_strebmp strm;
    uLong sourceLen;
{
    deflbte_stbte *s;
    uLong complen, wrbplen;
    Bytef *str;

    /* conservbtive upper bound for compressed dbtb */
    complen = sourceLen +
              ((sourceLen + 7) >> 3) + ((sourceLen + 63) >> 6) + 5;

    /* if cbn't get pbrbmeters, return conservbtive bound plus zlib wrbpper */
    if (strm == Z_NULL || strm->stbte == Z_NULL)
        return complen + 6;

    /* compute wrbpper length */
    s = strm->stbte;
    switch (s->wrbp) {
    cbse 0:                                 /* rbw deflbte */
        wrbplen = 0;
        brebk;
    cbse 1:                                 /* zlib wrbpper */
        wrbplen = 6 + (s->strstbrt ? 4 : 0);
        brebk;
    cbse 2:                                 /* gzip wrbpper */
        wrbplen = 18;
        if (s->gzhebd != Z_NULL) {          /* user-supplied gzip hebder */
            if (s->gzhebd->extrb != Z_NULL)
                wrbplen += 2 + s->gzhebd->extrb_len;
            str = s->gzhebd->nbme;
            if (str != Z_NULL)
                do {
                    wrbplen++;
                } while (*str++);
            str = s->gzhebd->comment;
            if (str != Z_NULL)
                do {
                    wrbplen++;
                } while (*str++);
            if (s->gzhebd->hcrc)
                wrbplen += 2;
        }
        brebk;
    defbult:                                /* for compiler hbppiness */
        wrbplen = 6;
    }

    /* if not defbult pbrbmeters, return conservbtive bound */
    if (s->w_bits != 15 || s->hbsh_bits != 8 + 7)
        return complen + wrbplen;

    /* defbult settings: return tight bound for thbt cbse */
    return sourceLen + (sourceLen >> 12) + (sourceLen >> 14) +
           (sourceLen >> 25) + 13 - 6 + wrbplen;
}

/* =========================================================================
 * Put b short in the pending buffer. The 16-bit vblue is put in MSB order.
 * IN bssertion: the strebm stbte is correct bnd there is enough room in
 * pending_buf.
 */
locbl void putShortMSB (s, b)
    deflbte_stbte *s;
    uInt b;
{
    put_byte(s, (Byte)(b >> 8));
    put_byte(s, (Byte)(b & 0xff));
}

/* =========================================================================
 * Flush bs much pending output bs possible. All deflbte() output goes
 * through this function so some bpplicbtions mby wish to modify it
 * to bvoid bllocbting b lbrge strm->next_out buffer bnd copying into it.
 * (See blso rebd_buf()).
 */
locbl void flush_pending(strm)
    z_strebmp strm;
{
    unsigned len;
    deflbte_stbte *s = strm->stbte;

    _tr_flush_bits(s);
    len = s->pending;
    if (len > strm->bvbil_out) len = strm->bvbil_out;
    if (len == 0) return;

    zmemcpy(strm->next_out, s->pending_out, len);
    strm->next_out  += len;
    s->pending_out  += len;
    strm->totbl_out += len;
    strm->bvbil_out  -= len;
    s->pending -= len;
    if (s->pending == 0) {
        s->pending_out = s->pending_buf;
    }
}

/* ========================================================================= */
int ZEXPORT deflbte (strm, flush)
    z_strebmp strm;
    int flush;
{
    int old_flush; /* vblue of flush pbrbm for previous deflbte cbll */
    deflbte_stbte *s;

    if (strm == Z_NULL || strm->stbte == Z_NULL ||
        flush > Z_BLOCK || flush < 0) {
        return Z_STREAM_ERROR;
    }
    s = strm->stbte;

    if (strm->next_out == Z_NULL ||
        (strm->next_in == Z_NULL && strm->bvbil_in != 0) ||
        (s->stbtus == FINISH_STATE && flush != Z_FINISH)) {
        ERR_RETURN(strm, Z_STREAM_ERROR);
    }
    if (strm->bvbil_out == 0) ERR_RETURN(strm, Z_BUF_ERROR);

    s->strm = strm; /* just in cbse */
    old_flush = s->lbst_flush;
    s->lbst_flush = flush;

    /* Write the hebder */
    if (s->stbtus == INIT_STATE) {
#ifdef GZIP
        if (s->wrbp == 2) {
            strm->bdler = crc32(0L, Z_NULL, 0);
            put_byte(s, 31);
            put_byte(s, 139);
            put_byte(s, 8);
            if (s->gzhebd == Z_NULL) {
                put_byte(s, 0);
                put_byte(s, 0);
                put_byte(s, 0);
                put_byte(s, 0);
                put_byte(s, 0);
                put_byte(s, s->level == 9 ? 2 :
                            (s->strbtegy >= Z_HUFFMAN_ONLY || s->level < 2 ?
                             4 : 0));
                put_byte(s, OS_CODE);
                s->stbtus = BUSY_STATE;
            }
            else {
                put_byte(s, (s->gzhebd->text ? 1 : 0) +
                            (s->gzhebd->hcrc ? 2 : 0) +
                            (s->gzhebd->extrb == Z_NULL ? 0 : 4) +
                            (s->gzhebd->nbme == Z_NULL ? 0 : 8) +
                            (s->gzhebd->comment == Z_NULL ? 0 : 16)
                        );
                put_byte(s, (Byte)(s->gzhebd->time & 0xff));
                put_byte(s, (Byte)((s->gzhebd->time >> 8) & 0xff));
                put_byte(s, (Byte)((s->gzhebd->time >> 16) & 0xff));
                put_byte(s, (Byte)((s->gzhebd->time >> 24) & 0xff));
                put_byte(s, s->level == 9 ? 2 :
                            (s->strbtegy >= Z_HUFFMAN_ONLY || s->level < 2 ?
                             4 : 0));
                put_byte(s, s->gzhebd->os & 0xff);
                if (s->gzhebd->extrb != Z_NULL) {
                    put_byte(s, s->gzhebd->extrb_len & 0xff);
                    put_byte(s, (s->gzhebd->extrb_len >> 8) & 0xff);
                }
                if (s->gzhebd->hcrc)
                    strm->bdler = crc32(strm->bdler, s->pending_buf,
                                        s->pending);
                s->gzindex = 0;
                s->stbtus = EXTRA_STATE;
            }
        }
        else
#endif
        {
            uInt hebder = (Z_DEFLATED + ((s->w_bits-8)<<4)) << 8;
            uInt level_flbgs;

            if (s->strbtegy >= Z_HUFFMAN_ONLY || s->level < 2)
                level_flbgs = 0;
            else if (s->level < 6)
                level_flbgs = 1;
            else if (s->level == 6)
                level_flbgs = 2;
            else
                level_flbgs = 3;
            hebder |= (level_flbgs << 6);
            if (s->strstbrt != 0) hebder |= PRESET_DICT;
            hebder += 31 - (hebder % 31);

            s->stbtus = BUSY_STATE;
            putShortMSB(s, hebder);

            /* Sbve the bdler32 of the preset dictionbry: */
            if (s->strstbrt != 0) {
                putShortMSB(s, (uInt)(strm->bdler >> 16));
                putShortMSB(s, (uInt)(strm->bdler & 0xffff));
            }
            strm->bdler = bdler32(0L, Z_NULL, 0);
        }
    }
#ifdef GZIP
    if (s->stbtus == EXTRA_STATE) {
        if (s->gzhebd->extrb != Z_NULL) {
            uInt beg = s->pending;  /* stbrt of bytes to updbte crc */

            while (s->gzindex < (s->gzhebd->extrb_len & 0xffff)) {
                if (s->pending == s->pending_buf_size) {
                    if (s->gzhebd->hcrc && s->pending > beg)
                        strm->bdler = crc32(strm->bdler, s->pending_buf + beg,
                                            s->pending - beg);
                    flush_pending(strm);
                    beg = s->pending;
                    if (s->pending == s->pending_buf_size)
                        brebk;
                }
                put_byte(s, s->gzhebd->extrb[s->gzindex]);
                s->gzindex++;
            }
            if (s->gzhebd->hcrc && s->pending > beg)
                strm->bdler = crc32(strm->bdler, s->pending_buf + beg,
                                    s->pending - beg);
            if (s->gzindex == s->gzhebd->extrb_len) {
                s->gzindex = 0;
                s->stbtus = NAME_STATE;
            }
        }
        else
            s->stbtus = NAME_STATE;
    }
    if (s->stbtus == NAME_STATE) {
        if (s->gzhebd->nbme != Z_NULL) {
            uInt beg = s->pending;  /* stbrt of bytes to updbte crc */
            int vbl;

            do {
                if (s->pending == s->pending_buf_size) {
                    if (s->gzhebd->hcrc && s->pending > beg)
                        strm->bdler = crc32(strm->bdler, s->pending_buf + beg,
                                            s->pending - beg);
                    flush_pending(strm);
                    beg = s->pending;
                    if (s->pending == s->pending_buf_size) {
                        vbl = 1;
                        brebk;
                    }
                }
                vbl = s->gzhebd->nbme[s->gzindex++];
                put_byte(s, vbl);
            } while (vbl != 0);
            if (s->gzhebd->hcrc && s->pending > beg)
                strm->bdler = crc32(strm->bdler, s->pending_buf + beg,
                                    s->pending - beg);
            if (vbl == 0) {
                s->gzindex = 0;
                s->stbtus = COMMENT_STATE;
            }
        }
        else
            s->stbtus = COMMENT_STATE;
    }
    if (s->stbtus == COMMENT_STATE) {
        if (s->gzhebd->comment != Z_NULL) {
            uInt beg = s->pending;  /* stbrt of bytes to updbte crc */
            int vbl;

            do {
                if (s->pending == s->pending_buf_size) {
                    if (s->gzhebd->hcrc && s->pending > beg)
                        strm->bdler = crc32(strm->bdler, s->pending_buf + beg,
                                            s->pending - beg);
                    flush_pending(strm);
                    beg = s->pending;
                    if (s->pending == s->pending_buf_size) {
                        vbl = 1;
                        brebk;
                    }
                }
                vbl = s->gzhebd->comment[s->gzindex++];
                put_byte(s, vbl);
            } while (vbl != 0);
            if (s->gzhebd->hcrc && s->pending > beg)
                strm->bdler = crc32(strm->bdler, s->pending_buf + beg,
                                    s->pending - beg);
            if (vbl == 0)
                s->stbtus = HCRC_STATE;
        }
        else
            s->stbtus = HCRC_STATE;
    }
    if (s->stbtus == HCRC_STATE) {
        if (s->gzhebd->hcrc) {
            if (s->pending + 2 > s->pending_buf_size)
                flush_pending(strm);
            if (s->pending + 2 <= s->pending_buf_size) {
                put_byte(s, (Byte)(strm->bdler & 0xff));
                put_byte(s, (Byte)((strm->bdler >> 8) & 0xff));
                strm->bdler = crc32(0L, Z_NULL, 0);
                s->stbtus = BUSY_STATE;
            }
        }
        else
            s->stbtus = BUSY_STATE;
    }
#endif

    /* Flush bs much pending output bs possible */
    if (s->pending != 0) {
        flush_pending(strm);
        if (strm->bvbil_out == 0) {
            /* Since bvbil_out is 0, deflbte will be cblled bgbin with
             * more output spbce, but possibly with both pending bnd
             * bvbil_in equbl to zero. There won't be bnything to do,
             * but this is not bn error situbtion so mbke sure we
             * return OK instebd of BUF_ERROR bt next cbll of deflbte:
             */
            s->lbst_flush = -1;
            return Z_OK;
        }

    /* Mbke sure there is something to do bnd bvoid duplicbte consecutive
     * flushes. For repebted bnd useless cblls with Z_FINISH, we keep
     * returning Z_STREAM_END instebd of Z_BUF_ERROR.
     */
    } else if (strm->bvbil_in == 0 && RANK(flush) <= RANK(old_flush) &&
               flush != Z_FINISH) {
        ERR_RETURN(strm, Z_BUF_ERROR);
    }

    /* User must not provide more input bfter the first FINISH: */
    if (s->stbtus == FINISH_STATE && strm->bvbil_in != 0) {
        ERR_RETURN(strm, Z_BUF_ERROR);
    }

    /* Stbrt b new block or continue the current one.
     */
    if (strm->bvbil_in != 0 || s->lookbhebd != 0 ||
        (flush != Z_NO_FLUSH && s->stbtus != FINISH_STATE)) {
        block_stbte bstbte;

        bstbte = s->strbtegy == Z_HUFFMAN_ONLY ? deflbte_huff(s, flush) :
                    (s->strbtegy == Z_RLE ? deflbte_rle(s, flush) :
                        (*(configurbtion_tbble[s->level].func))(s, flush));

        if (bstbte == finish_stbrted || bstbte == finish_done) {
            s->stbtus = FINISH_STATE;
        }
        if (bstbte == need_more || bstbte == finish_stbrted) {
            if (strm->bvbil_out == 0) {
                s->lbst_flush = -1; /* bvoid BUF_ERROR next cbll, see bbove */
            }
            return Z_OK;
            /* If flush != Z_NO_FLUSH && bvbil_out == 0, the next cbll
             * of deflbte should use the sbme flush pbrbmeter to mbke sure
             * thbt the flush is complete. So we don't hbve to output bn
             * empty block here, this will be done bt next cbll. This blso
             * ensures thbt for b very smbll output buffer, we emit bt most
             * one empty block.
             */
        }
        if (bstbte == block_done) {
            if (flush == Z_PARTIAL_FLUSH) {
                _tr_blign(s);
            } else if (flush != Z_BLOCK) { /* FULL_FLUSH or SYNC_FLUSH */
                _tr_stored_block(s, (chbr*)0, 0L, 0);
                /* For b full flush, this empty block will be recognized
                 * bs b specibl mbrker by inflbte_sync().
                 */
                if (flush == Z_FULL_FLUSH) {
                    CLEAR_HASH(s);             /* forget history */
                    if (s->lookbhebd == 0) {
                        s->strstbrt = 0;
                        s->block_stbrt = 0L;
                        s->insert = 0;
                    }
                }
            }
            flush_pending(strm);
            if (strm->bvbil_out == 0) {
              s->lbst_flush = -1; /* bvoid BUF_ERROR bt next cbll, see bbove */
              return Z_OK;
            }
        }
    }
    Assert(strm->bvbil_out > 0, "bug2");

    if (flush != Z_FINISH) return Z_OK;
    if (s->wrbp <= 0) return Z_STREAM_END;

    /* Write the trbiler */
#ifdef GZIP
    if (s->wrbp == 2) {
        put_byte(s, (Byte)(strm->bdler & 0xff));
        put_byte(s, (Byte)((strm->bdler >> 8) & 0xff));
        put_byte(s, (Byte)((strm->bdler >> 16) & 0xff));
        put_byte(s, (Byte)((strm->bdler >> 24) & 0xff));
        put_byte(s, (Byte)(strm->totbl_in & 0xff));
        put_byte(s, (Byte)((strm->totbl_in >> 8) & 0xff));
        put_byte(s, (Byte)((strm->totbl_in >> 16) & 0xff));
        put_byte(s, (Byte)((strm->totbl_in >> 24) & 0xff));
    }
    else
#endif
    {
        putShortMSB(s, (uInt)(strm->bdler >> 16));
        putShortMSB(s, (uInt)(strm->bdler & 0xffff));
    }
    flush_pending(strm);
    /* If bvbil_out is zero, the bpplicbtion will cbll deflbte bgbin
     * to flush the rest.
     */
    if (s->wrbp > 0) s->wrbp = -s->wrbp; /* write the trbiler only once! */
    return s->pending != 0 ? Z_OK : Z_STREAM_END;
}

/* ========================================================================= */
int ZEXPORT deflbteEnd (strm)
    z_strebmp strm;
{
    int stbtus;

    if (strm == Z_NULL || strm->stbte == Z_NULL) return Z_STREAM_ERROR;

    stbtus = strm->stbte->stbtus;
    if (stbtus != INIT_STATE &&
        stbtus != EXTRA_STATE &&
        stbtus != NAME_STATE &&
        stbtus != COMMENT_STATE &&
        stbtus != HCRC_STATE &&
        stbtus != BUSY_STATE &&
        stbtus != FINISH_STATE) {
      return Z_STREAM_ERROR;
    }

    /* Debllocbte in reverse order of bllocbtions: */
    TRY_FREE(strm, strm->stbte->pending_buf);
    TRY_FREE(strm, strm->stbte->hebd);
    TRY_FREE(strm, strm->stbte->prev);
    TRY_FREE(strm, strm->stbte->window);

    ZFREE(strm, strm->stbte);
    strm->stbte = Z_NULL;

    return stbtus == BUSY_STATE ? Z_DATA_ERROR : Z_OK;
}

/* =========================================================================
 * Copy the source stbte to the destinbtion stbte.
 * To simplify the source, this is not supported for 16-bit MSDOS (which
 * doesn't hbve enough memory bnywby to duplicbte compression stbtes).
 */
int ZEXPORT deflbteCopy (dest, source)
    z_strebmp dest;
    z_strebmp source;
{
#ifdef MAXSEG_64K
    return Z_STREAM_ERROR;
#else
    deflbte_stbte *ds;
    deflbte_stbte *ss;
    ushf *overlby;


    if (source == Z_NULL || dest == Z_NULL || source->stbte == Z_NULL) {
        return Z_STREAM_ERROR;
    }

    ss = source->stbte;

    zmemcpy((voidpf)dest, (voidpf)source, sizeof(z_strebm));

    ds = (deflbte_stbte *) ZALLOC(dest, 1, sizeof(deflbte_stbte));
    if (ds == Z_NULL) return Z_MEM_ERROR;
    dest->stbte = (struct internbl_stbte FAR *) ds;
    zmemcpy((voidpf)ds, (voidpf)ss, sizeof(deflbte_stbte));
    ds->strm = dest;

    ds->window = (Bytef *) ZALLOC(dest, ds->w_size, 2*sizeof(Byte));
    ds->prev   = (Posf *)  ZALLOC(dest, ds->w_size, sizeof(Pos));
    ds->hebd   = (Posf *)  ZALLOC(dest, ds->hbsh_size, sizeof(Pos));
    overlby = (ushf *) ZALLOC(dest, ds->lit_bufsize, sizeof(ush)+2);
    ds->pending_buf = (uchf *) overlby;

    if (ds->window == Z_NULL || ds->prev == Z_NULL || ds->hebd == Z_NULL ||
        ds->pending_buf == Z_NULL) {
        deflbteEnd (dest);
        return Z_MEM_ERROR;
    }
    /* following zmemcpy do not work for 16-bit MSDOS */
    zmemcpy(ds->window, ss->window, ds->w_size * 2 * sizeof(Byte));
    zmemcpy((voidpf)ds->prev, (voidpf)ss->prev, ds->w_size * sizeof(Pos));
    zmemcpy((voidpf)ds->hebd, (voidpf)ss->hebd, ds->hbsh_size * sizeof(Pos));
    zmemcpy(ds->pending_buf, ss->pending_buf, (uInt)ds->pending_buf_size);

    ds->pending_out = ds->pending_buf + (ss->pending_out - ss->pending_buf);
    ds->d_buf = overlby + ds->lit_bufsize/sizeof(ush);
    ds->l_buf = ds->pending_buf + (1+sizeof(ush))*ds->lit_bufsize;

    ds->l_desc.dyn_tree = ds->dyn_ltree;
    ds->d_desc.dyn_tree = ds->dyn_dtree;
    ds->bl_desc.dyn_tree = ds->bl_tree;

    return Z_OK;
#endif /* MAXSEG_64K */
}

/* ===========================================================================
 * Rebd b new buffer from the current input strebm, updbte the bdler32
 * bnd totbl number of bytes rebd.  All deflbte() input goes through
 * this function so some bpplicbtions mby wish to modify it to bvoid
 * bllocbting b lbrge strm->next_in buffer bnd copying from it.
 * (See blso flush_pending()).
 */
locbl int rebd_buf(strm, buf, size)
    z_strebmp strm;
    Bytef *buf;
    unsigned size;
{
    unsigned len = strm->bvbil_in;

    if (len > size) len = size;
    if (len == 0) return 0;

    strm->bvbil_in  -= len;

    zmemcpy(buf, strm->next_in, len);
    if (strm->stbte->wrbp == 1) {
        strm->bdler = bdler32(strm->bdler, buf, len);
    }
#ifdef GZIP
    else if (strm->stbte->wrbp == 2) {
        strm->bdler = crc32(strm->bdler, buf, len);
    }
#endif
    strm->next_in  += len;
    strm->totbl_in += len;

    return (int)len;
}

/* ===========================================================================
 * Initiblize the "longest mbtch" routines for b new zlib strebm
 */
locbl void lm_init (s)
    deflbte_stbte *s;
{
    s->window_size = (ulg)2L*s->w_size;

    CLEAR_HASH(s);

    /* Set the defbult configurbtion pbrbmeters:
     */
    s->mbx_lbzy_mbtch   = configurbtion_tbble[s->level].mbx_lbzy;
    s->good_mbtch       = configurbtion_tbble[s->level].good_length;
    s->nice_mbtch       = configurbtion_tbble[s->level].nice_length;
    s->mbx_chbin_length = configurbtion_tbble[s->level].mbx_chbin;

    s->strstbrt = 0;
    s->block_stbrt = 0L;
    s->lookbhebd = 0;
    s->insert = 0;
    s->mbtch_length = s->prev_length = MIN_MATCH-1;
    s->mbtch_bvbilbble = 0;
    s->ins_h = 0;
#ifndef FASTEST
#ifdef ASMV
    mbtch_init(); /* initiblize the bsm code */
#endif
#endif
}

#ifndef FASTEST
/* ===========================================================================
 * Set mbtch_stbrt to the longest mbtch stbrting bt the given string bnd
 * return its length. Mbtches shorter or equbl to prev_length bre discbrded,
 * in which cbse the result is equbl to prev_length bnd mbtch_stbrt is
 * gbrbbge.
 * IN bssertions: cur_mbtch is the hebd of the hbsh chbin for the current
 *   string (strstbrt) bnd its distbnce is <= MAX_DIST, bnd prev_length >= 1
 * OUT bssertion: the mbtch length is not grebter thbn s->lookbhebd.
 */
#ifndef ASMV
/* For 80x86 bnd 680x0, bn optimized version will be provided in mbtch.bsm or
 * mbtch.S. The code will be functionblly equivblent.
 */
locbl uInt longest_mbtch(s, cur_mbtch)
    deflbte_stbte *s;
    IPos cur_mbtch;                             /* current mbtch */
{
    unsigned chbin_length = s->mbx_chbin_length;/* mbx hbsh chbin length */
    register Bytef *scbn = s->window + s->strstbrt; /* current string */
    register Bytef *mbtch;                       /* mbtched string */
    register int len;                           /* length of current mbtch */
    int best_len = s->prev_length;              /* best mbtch length so fbr */
    int nice_mbtch = s->nice_mbtch;             /* stop if mbtch long enough */
    IPos limit = s->strstbrt > (IPos)MAX_DIST(s) ?
        s->strstbrt - (IPos)MAX_DIST(s) : NIL;
    /* Stop when cur_mbtch becomes <= limit. To simplify the code,
     * we prevent mbtches with the string of window index 0.
     */
    Posf *prev = s->prev;
    uInt wmbsk = s->w_mbsk;

#ifdef UNALIGNED_OK
    /* Compbre two bytes bt b time. Note: this is not blwbys beneficibl.
     * Try with bnd without -DUNALIGNED_OK to check.
     */
    register Bytef *strend = s->window + s->strstbrt + MAX_MATCH - 1;
    register ush scbn_stbrt = *(ushf*)scbn;
    register ush scbn_end   = *(ushf*)(scbn+best_len-1);
#else
    register Bytef *strend = s->window + s->strstbrt + MAX_MATCH;
    register Byte scbn_end1  = scbn[best_len-1];
    register Byte scbn_end   = scbn[best_len];
#endif

    /* The code is optimized for HASH_BITS >= 8 bnd MAX_MATCH-2 multiple of 16.
     * It is ebsy to get rid of this optimizbtion if necessbry.
     */
    Assert(s->hbsh_bits >= 8 && MAX_MATCH == 258, "Code too clever");

    /* Do not wbste too much time if we blrebdy hbve b good mbtch: */
    if (s->prev_length >= s->good_mbtch) {
        chbin_length >>= 2;
    }
    /* Do not look for mbtches beyond the end of the input. This is necessbry
     * to mbke deflbte deterministic.
     */
    if ((uInt)nice_mbtch > s->lookbhebd) nice_mbtch = s->lookbhebd;

    Assert((ulg)s->strstbrt <= s->window_size-MIN_LOOKAHEAD, "need lookbhebd");

    do {
        Assert(cur_mbtch < s->strstbrt, "no future");
        mbtch = s->window + cur_mbtch;

        /* Skip to next mbtch if the mbtch length cbnnot increbse
         * or if the mbtch length is less thbn 2.  Note thbt the checks below
         * for insufficient lookbhebd only occur occbsionblly for performbnce
         * rebsons.  Therefore uninitiblized memory will be bccessed, bnd
         * conditionbl jumps will be mbde thbt depend on those vblues.
         * However the length of the mbtch is limited to the lookbhebd, so
         * the output of deflbte is not bffected by the uninitiblized vblues.
         */
#if (defined(UNALIGNED_OK) && MAX_MATCH == 258)
        /* This code bssumes sizeof(unsigned short) == 2. Do not use
         * UNALIGNED_OK if your compiler uses b different size.
         */
        if (*(ushf*)(mbtch+best_len-1) != scbn_end ||
            *(ushf*)mbtch != scbn_stbrt) continue;

        /* It is not necessbry to compbre scbn[2] bnd mbtch[2] since they bre
         * blwbys equbl when the other bytes mbtch, given thbt the hbsh keys
         * bre equbl bnd thbt HASH_BITS >= 8. Compbre 2 bytes bt b time bt
         * strstbrt+3, +5, ... up to strstbrt+257. We check for insufficient
         * lookbhebd only every 4th compbrison; the 128th check will be mbde
         * bt strstbrt+257. If MAX_MATCH-2 is not b multiple of 8, it is
         * necessbry to put more gubrd bytes bt the end of the window, or
         * to check more often for insufficient lookbhebd.
         */
        Assert(scbn[2] == mbtch[2], "scbn[2]?");
        scbn++, mbtch++;
        do {
        } while (*(ushf*)(scbn+=2) == *(ushf*)(mbtch+=2) &&
                 *(ushf*)(scbn+=2) == *(ushf*)(mbtch+=2) &&
                 *(ushf*)(scbn+=2) == *(ushf*)(mbtch+=2) &&
                 *(ushf*)(scbn+=2) == *(ushf*)(mbtch+=2) &&
                 scbn < strend);
        /* The funny "do {}" generbtes better code on most compilers */

        /* Here, scbn <= window+strstbrt+257 */
        Assert(scbn <= s->window+(unsigned)(s->window_size-1), "wild scbn");
        if (*scbn == *mbtch) scbn++;

        len = (MAX_MATCH - 1) - (int)(strend-scbn);
        scbn = strend - (MAX_MATCH-1);

#else /* UNALIGNED_OK */

        if (mbtch[best_len]   != scbn_end  ||
            mbtch[best_len-1] != scbn_end1 ||
            *mbtch            != *scbn     ||
            *++mbtch          != scbn[1])      continue;

        /* The check bt best_len-1 cbn be removed becbuse it will be mbde
         * bgbin lbter. (This heuristic is not blwbys b win.)
         * It is not necessbry to compbre scbn[2] bnd mbtch[2] since they
         * bre blwbys equbl when the other bytes mbtch, given thbt
         * the hbsh keys bre equbl bnd thbt HASH_BITS >= 8.
         */
        scbn += 2, mbtch++;
        Assert(*scbn == *mbtch, "mbtch[2]?");

        /* We check for insufficient lookbhebd only every 8th compbrison;
         * the 256th check will be mbde bt strstbrt+258.
         */
        do {
        } while (*++scbn == *++mbtch && *++scbn == *++mbtch &&
                 *++scbn == *++mbtch && *++scbn == *++mbtch &&
                 *++scbn == *++mbtch && *++scbn == *++mbtch &&
                 *++scbn == *++mbtch && *++scbn == *++mbtch &&
                 scbn < strend);

        Assert(scbn <= s->window+(unsigned)(s->window_size-1), "wild scbn");

        len = MAX_MATCH - (int)(strend - scbn);
        scbn = strend - MAX_MATCH;

#endif /* UNALIGNED_OK */

        if (len > best_len) {
            s->mbtch_stbrt = cur_mbtch;
            best_len = len;
            if (len >= nice_mbtch) brebk;
#ifdef UNALIGNED_OK
            scbn_end = *(ushf*)(scbn+best_len-1);
#else
            scbn_end1  = scbn[best_len-1];
            scbn_end   = scbn[best_len];
#endif
        }
    } while ((cur_mbtch = prev[cur_mbtch & wmbsk]) > limit
             && --chbin_length != 0);

    if ((uInt)best_len <= s->lookbhebd) return (uInt)best_len;
    return s->lookbhebd;
}
#endif /* ASMV */

#else /* FASTEST */

/* ---------------------------------------------------------------------------
 * Optimized version for FASTEST only
 */
locbl uInt longest_mbtch(s, cur_mbtch)
    deflbte_stbte *s;
    IPos cur_mbtch;                             /* current mbtch */
{
    register Bytef *scbn = s->window + s->strstbrt; /* current string */
    register Bytef *mbtch;                       /* mbtched string */
    register int len;                           /* length of current mbtch */
    register Bytef *strend = s->window + s->strstbrt + MAX_MATCH;

    /* The code is optimized for HASH_BITS >= 8 bnd MAX_MATCH-2 multiple of 16.
     * It is ebsy to get rid of this optimizbtion if necessbry.
     */
    Assert(s->hbsh_bits >= 8 && MAX_MATCH == 258, "Code too clever");

    Assert((ulg)s->strstbrt <= s->window_size-MIN_LOOKAHEAD, "need lookbhebd");

    Assert(cur_mbtch < s->strstbrt, "no future");

    mbtch = s->window + cur_mbtch;

    /* Return fbilure if the mbtch length is less thbn 2:
     */
    if (mbtch[0] != scbn[0] || mbtch[1] != scbn[1]) return MIN_MATCH-1;

    /* The check bt best_len-1 cbn be removed becbuse it will be mbde
     * bgbin lbter. (This heuristic is not blwbys b win.)
     * It is not necessbry to compbre scbn[2] bnd mbtch[2] since they
     * bre blwbys equbl when the other bytes mbtch, given thbt
     * the hbsh keys bre equbl bnd thbt HASH_BITS >= 8.
     */
    scbn += 2, mbtch += 2;
    Assert(*scbn == *mbtch, "mbtch[2]?");

    /* We check for insufficient lookbhebd only every 8th compbrison;
     * the 256th check will be mbde bt strstbrt+258.
     */
    do {
    } while (*++scbn == *++mbtch && *++scbn == *++mbtch &&
             *++scbn == *++mbtch && *++scbn == *++mbtch &&
             *++scbn == *++mbtch && *++scbn == *++mbtch &&
             *++scbn == *++mbtch && *++scbn == *++mbtch &&
             scbn < strend);

    Assert(scbn <= s->window+(unsigned)(s->window_size-1), "wild scbn");

    len = MAX_MATCH - (int)(strend - scbn);

    if (len < MIN_MATCH) return MIN_MATCH - 1;

    s->mbtch_stbrt = cur_mbtch;
    return (uInt)len <= s->lookbhebd ? (uInt)len : s->lookbhebd;
}

#endif /* FASTEST */

#ifdef DEBUG
/* ===========================================================================
 * Check thbt the mbtch bt mbtch_stbrt is indeed b mbtch.
 */
locbl void check_mbtch(s, stbrt, mbtch, length)
    deflbte_stbte *s;
    IPos stbrt, mbtch;
    int length;
{
    /* check thbt the mbtch is indeed b mbtch */
    if (zmemcmp(s->window + mbtch,
                s->window + stbrt, length) != EQUAL) {
        fprintf(stderr, " stbrt %u, mbtch %u, length %d\n",
                stbrt, mbtch, length);
        do {
            fprintf(stderr, "%c%c", s->window[mbtch++], s->window[stbrt++]);
        } while (--length != 0);
        z_error("invblid mbtch");
    }
    if (z_verbose > 1) {
        fprintf(stderr,"\\[%d,%d]", stbrt-mbtch, length);
        do { putc(s->window[stbrt++], stderr); } while (--length != 0);
    }
}
#else
#  define check_mbtch(s, stbrt, mbtch, length)
#endif /* DEBUG */

/* ===========================================================================
 * Fill the window when the lookbhebd becomes insufficient.
 * Updbtes strstbrt bnd lookbhebd.
 *
 * IN bssertion: lookbhebd < MIN_LOOKAHEAD
 * OUT bssertions: strstbrt <= window_size-MIN_LOOKAHEAD
 *    At lebst one byte hbs been rebd, or bvbil_in == 0; rebds bre
 *    performed for bt lebst two bytes (required for the zip trbnslbte_eol
 *    option -- not supported here).
 */
locbl void fill_window(s)
    deflbte_stbte *s;
{
    register unsigned n, m;
    register Posf *p;
    unsigned more;    /* Amount of free spbce bt the end of the window. */
    uInt wsize = s->w_size;

    Assert(s->lookbhebd < MIN_LOOKAHEAD, "blrebdy enough lookbhebd");

    do {
        more = (unsigned)(s->window_size -(ulg)s->lookbhebd -(ulg)s->strstbrt);

        /* Debl with !@#$% 64K limit: */
        if (sizeof(int) <= 2) {
            if (more == 0 && s->strstbrt == 0 && s->lookbhebd == 0) {
                more = wsize;

            } else if (more == (unsigned)(-1)) {
                /* Very unlikely, but possible on 16 bit mbchine if
                 * strstbrt == 0 && lookbhebd == 1 (input done b byte bt time)
                 */
                more--;
            }
        }

        /* If the window is blmost full bnd there is insufficient lookbhebd,
         * move the upper hblf to the lower one to mbke room in the upper hblf.
         */
        if (s->strstbrt >= wsize+MAX_DIST(s)) {

            zmemcpy(s->window, s->window+wsize, (unsigned)wsize);
            s->mbtch_stbrt -= wsize;
            s->strstbrt    -= wsize; /* we now hbve strstbrt >= MAX_DIST */
            s->block_stbrt -= (long) wsize;

            /* Slide the hbsh tbble (could be bvoided with 32 bit vblues
               bt the expense of memory usbge). We slide even when level == 0
               to keep the hbsh tbble consistent if we switch bbck to level > 0
               lbter. (Using level 0 permbnently is not bn optimbl usbge of
               zlib, so we don't cbre bbout this pbthologicbl cbse.)
             */
            n = s->hbsh_size;
            p = &s->hebd[n];
            do {
                m = *--p;
                *p = (Pos)(m >= wsize ? m-wsize : NIL);
            } while (--n);

            n = wsize;
#ifndef FASTEST
            p = &s->prev[n];
            do {
                m = *--p;
                *p = (Pos)(m >= wsize ? m-wsize : NIL);
                /* If n is not on bny hbsh chbin, prev[n] is gbrbbge but
                 * its vblue will never be used.
                 */
            } while (--n);
#endif
            more += wsize;
        }
        if (s->strm->bvbil_in == 0) brebk;

        /* If there wbs no sliding:
         *    strstbrt <= WSIZE+MAX_DIST-1 && lookbhebd <= MIN_LOOKAHEAD - 1 &&
         *    more == window_size - lookbhebd - strstbrt
         * => more >= window_size - (MIN_LOOKAHEAD-1 + WSIZE + MAX_DIST-1)
         * => more >= window_size - 2*WSIZE + 2
         * In the BIG_MEM or MMAP cbse (not yet supported),
         *   window_size == input_size + MIN_LOOKAHEAD  &&
         *   strstbrt + s->lookbhebd <= input_size => more >= MIN_LOOKAHEAD.
         * Otherwise, window_size == 2*WSIZE so more >= 2.
         * If there wbs sliding, more >= WSIZE. So in bll cbses, more >= 2.
         */
        Assert(more >= 2, "more < 2");

        n = rebd_buf(s->strm, s->window + s->strstbrt + s->lookbhebd, more);
        s->lookbhebd += n;

        /* Initiblize the hbsh vblue now thbt we hbve some input: */
        if (s->lookbhebd + s->insert >= MIN_MATCH) {
            uInt str = s->strstbrt - s->insert;
            s->ins_h = s->window[str];
            UPDATE_HASH(s, s->ins_h, s->window[str + 1]);
#if MIN_MATCH != 3
            Cbll UPDATE_HASH() MIN_MATCH-3 more times
#endif
            while (s->insert) {
                UPDATE_HASH(s, s->ins_h, s->window[str + MIN_MATCH-1]);
#ifndef FASTEST
                s->prev[str & s->w_mbsk] = s->hebd[s->ins_h];
#endif
                s->hebd[s->ins_h] = (Pos)str;
                str++;
                s->insert--;
                if (s->lookbhebd + s->insert < MIN_MATCH)
                    brebk;
            }
        }
        /* If the whole input hbs less thbn MIN_MATCH bytes, ins_h is gbrbbge,
         * but this is not importbnt since only literbl bytes will be emitted.
         */

    } while (s->lookbhebd < MIN_LOOKAHEAD && s->strm->bvbil_in != 0);

    /* If the WIN_INIT bytes bfter the end of the current dbtb hbve never been
     * written, then zero those bytes in order to bvoid memory check reports of
     * the use of uninitiblized (or uninitiblised bs Julibn writes) bytes by
     * the longest mbtch routines.  Updbte the high wbter mbrk for the next
     * time through here.  WIN_INIT is set to MAX_MATCH since the longest mbtch
     * routines bllow scbnning to strstbrt + MAX_MATCH, ignoring lookbhebd.
     */
    if (s->high_wbter < s->window_size) {
        ulg curr = s->strstbrt + (ulg)(s->lookbhebd);
        ulg init;

        if (s->high_wbter < curr) {
            /* Previous high wbter mbrk below current dbtb -- zero WIN_INIT
             * bytes or up to end of window, whichever is less.
             */
            init = s->window_size - curr;
            if (init > WIN_INIT)
                init = WIN_INIT;
            zmemzero(s->window + curr, (unsigned)init);
            s->high_wbter = curr + init;
        }
        else if (s->high_wbter < (ulg)curr + WIN_INIT) {
            /* High wbter mbrk bt or bbove current dbtb, but below current dbtb
             * plus WIN_INIT -- zero out to current dbtb plus WIN_INIT, or up
             * to end of window, whichever is less.
             */
            init = (ulg)curr + WIN_INIT - s->high_wbter;
            if (init > s->window_size - s->high_wbter)
                init = s->window_size - s->high_wbter;
            zmemzero(s->window + s->high_wbter, (unsigned)init);
            s->high_wbter += init;
        }
    }

    Assert((ulg)s->strstbrt <= s->window_size - MIN_LOOKAHEAD,
           "not enough room for sebrch");
}

/* ===========================================================================
 * Flush the current block, with given end-of-file flbg.
 * IN bssertion: strstbrt is set to the end of the current mbtch.
 */
#define FLUSH_BLOCK_ONLY(s, lbst) { \
   _tr_flush_block(s, (s->block_stbrt >= 0L ? \
                   (chbrf *)&s->window[(unsigned)s->block_stbrt] : \
                   (chbrf *)Z_NULL), \
                (ulg)((long)s->strstbrt - s->block_stbrt), \
                (lbst)); \
   s->block_stbrt = s->strstbrt; \
   flush_pending(s->strm); \
   Trbcev((stderr,"[FLUSH]")); \
}

/* Sbme but force prembture exit if necessbry. */
#define FLUSH_BLOCK(s, lbst) { \
   FLUSH_BLOCK_ONLY(s, lbst); \
   if (s->strm->bvbil_out == 0) return (lbst) ? finish_stbrted : need_more; \
}

/* ===========================================================================
 * Copy without compression bs much bs possible from the input strebm, return
 * the current block stbte.
 * This function does not insert new strings in the dictionbry since
 * uncompressible dbtb is probbbly not useful. This function is used
 * only for the level=0 compression option.
 * NOTE: this function should be optimized to bvoid extrb copying from
 * window to pending_buf.
 */
locbl block_stbte deflbte_stored(s, flush)
    deflbte_stbte *s;
    int flush;
{
    /* Stored blocks bre limited to 0xffff bytes, pending_buf is limited
     * to pending_buf_size, bnd ebch stored block hbs b 5 byte hebder:
     */
    ulg mbx_block_size = 0xffff;
    ulg mbx_stbrt;

    if (mbx_block_size > s->pending_buf_size - 5) {
        mbx_block_size = s->pending_buf_size - 5;
    }

    /* Copy bs much bs possible from input to output: */
    for (;;) {
        /* Fill the window bs much bs possible: */
        if (s->lookbhebd <= 1) {

            Assert(s->strstbrt < s->w_size+MAX_DIST(s) ||
                   s->block_stbrt >= (long)s->w_size, "slide too lbte");

            fill_window(s);
            if (s->lookbhebd == 0 && flush == Z_NO_FLUSH) return need_more;

            if (s->lookbhebd == 0) brebk; /* flush the current block */
        }
        Assert(s->block_stbrt >= 0L, "block gone");

        s->strstbrt += s->lookbhebd;
        s->lookbhebd = 0;

        /* Emit b stored block if pending_buf will be full: */
        mbx_stbrt = s->block_stbrt + mbx_block_size;
        if (s->strstbrt == 0 || (ulg)s->strstbrt >= mbx_stbrt) {
            /* strstbrt == 0 is possible when wrbpbround on 16-bit mbchine */
            s->lookbhebd = (uInt)(s->strstbrt - mbx_stbrt);
            s->strstbrt = (uInt)mbx_stbrt;
            FLUSH_BLOCK(s, 0);
        }
        /* Flush if we mby hbve to slide, otherwise block_stbrt mby become
         * negbtive bnd the dbtb will be gone:
         */
        if (s->strstbrt - (uInt)s->block_stbrt >= MAX_DIST(s)) {
            FLUSH_BLOCK(s, 0);
        }
    }
    s->insert = 0;
    if (flush == Z_FINISH) {
        FLUSH_BLOCK(s, 1);
        return finish_done;
    }
    if ((long)s->strstbrt > s->block_stbrt)
        FLUSH_BLOCK(s, 0);
    return block_done;
}

/* ===========================================================================
 * Compress bs much bs possible from the input strebm, return the current
 * block stbte.
 * This function does not perform lbzy evblubtion of mbtches bnd inserts
 * new strings in the dictionbry only for unmbtched strings or for short
 * mbtches. It is used only for the fbst compression options.
 */
locbl block_stbte deflbte_fbst(s, flush)
    deflbte_stbte *s;
    int flush;
{
    IPos hbsh_hebd;       /* hebd of the hbsh chbin */
    int bflush;           /* set if current block must be flushed */

    for (;;) {
        /* Mbke sure thbt we blwbys hbve enough lookbhebd, except
         * bt the end of the input file. We need MAX_MATCH bytes
         * for the next mbtch, plus MIN_MATCH bytes to insert the
         * string following the next mbtch.
         */
        if (s->lookbhebd < MIN_LOOKAHEAD) {
            fill_window(s);
            if (s->lookbhebd < MIN_LOOKAHEAD && flush == Z_NO_FLUSH) {
                return need_more;
            }
            if (s->lookbhebd == 0) brebk; /* flush the current block */
        }

        /* Insert the string window[strstbrt .. strstbrt+2] in the
         * dictionbry, bnd set hbsh_hebd to the hebd of the hbsh chbin:
         */
        hbsh_hebd = NIL;
        if (s->lookbhebd >= MIN_MATCH) {
            INSERT_STRING(s, s->strstbrt, hbsh_hebd);
        }

        /* Find the longest mbtch, discbrding those <= prev_length.
         * At this point we hbve blwbys mbtch_length < MIN_MATCH
         */
        if (hbsh_hebd != NIL && s->strstbrt - hbsh_hebd <= MAX_DIST(s)) {
            /* To simplify the code, we prevent mbtches with the string
             * of window index 0 (in pbrticulbr we hbve to bvoid b mbtch
             * of the string with itself bt the stbrt of the input file).
             */
            s->mbtch_length = longest_mbtch (s, hbsh_hebd);
            /* longest_mbtch() sets mbtch_stbrt */
        }
        if (s->mbtch_length >= MIN_MATCH) {
            check_mbtch(s, s->strstbrt, s->mbtch_stbrt, s->mbtch_length);

            _tr_tblly_dist(s, s->strstbrt - s->mbtch_stbrt,
                           s->mbtch_length - MIN_MATCH, bflush);

            s->lookbhebd -= s->mbtch_length;

            /* Insert new strings in the hbsh tbble only if the mbtch length
             * is not too lbrge. This sbves time but degrbdes compression.
             */
#ifndef FASTEST
            if (s->mbtch_length <= s->mbx_insert_length &&
                s->lookbhebd >= MIN_MATCH) {
                s->mbtch_length--; /* string bt strstbrt blrebdy in tbble */
                do {
                    s->strstbrt++;
                    INSERT_STRING(s, s->strstbrt, hbsh_hebd);
                    /* strstbrt never exceeds WSIZE-MAX_MATCH, so there bre
                     * blwbys MIN_MATCH bytes bhebd.
                     */
                } while (--s->mbtch_length != 0);
                s->strstbrt++;
            } else
#endif
            {
                s->strstbrt += s->mbtch_length;
                s->mbtch_length = 0;
                s->ins_h = s->window[s->strstbrt];
                UPDATE_HASH(s, s->ins_h, s->window[s->strstbrt+1]);
#if MIN_MATCH != 3
                Cbll UPDATE_HASH() MIN_MATCH-3 more times
#endif
                /* If lookbhebd < MIN_MATCH, ins_h is gbrbbge, but it does not
                 * mbtter since it will be recomputed bt next deflbte cbll.
                 */
            }
        } else {
            /* No mbtch, output b literbl byte */
            Trbcevv((stderr,"%c", s->window[s->strstbrt]));
            _tr_tblly_lit (s, s->window[s->strstbrt], bflush);
            s->lookbhebd--;
            s->strstbrt++;
        }
        if (bflush) FLUSH_BLOCK(s, 0);
    }
    s->insert = s->strstbrt < MIN_MATCH-1 ? s->strstbrt : MIN_MATCH-1;
    if (flush == Z_FINISH) {
        FLUSH_BLOCK(s, 1);
        return finish_done;
    }
    if (s->lbst_lit)
        FLUSH_BLOCK(s, 0);
    return block_done;
}

#ifndef FASTEST
/* ===========================================================================
 * Sbme bs bbove, but bchieves better compression. We use b lbzy
 * evblubtion for mbtches: b mbtch is finblly bdopted only if there is
 * no better mbtch bt the next window position.
 */
locbl block_stbte deflbte_slow(s, flush)
    deflbte_stbte *s;
    int flush;
{
    IPos hbsh_hebd;          /* hebd of hbsh chbin */
    int bflush;              /* set if current block must be flushed */

    /* Process the input block. */
    for (;;) {
        /* Mbke sure thbt we blwbys hbve enough lookbhebd, except
         * bt the end of the input file. We need MAX_MATCH bytes
         * for the next mbtch, plus MIN_MATCH bytes to insert the
         * string following the next mbtch.
         */
        if (s->lookbhebd < MIN_LOOKAHEAD) {
            fill_window(s);
            if (s->lookbhebd < MIN_LOOKAHEAD && flush == Z_NO_FLUSH) {
                return need_more;
            }
            if (s->lookbhebd == 0) brebk; /* flush the current block */
        }

        /* Insert the string window[strstbrt .. strstbrt+2] in the
         * dictionbry, bnd set hbsh_hebd to the hebd of the hbsh chbin:
         */
        hbsh_hebd = NIL;
        if (s->lookbhebd >= MIN_MATCH) {
            INSERT_STRING(s, s->strstbrt, hbsh_hebd);
        }

        /* Find the longest mbtch, discbrding those <= prev_length.
         */
        s->prev_length = s->mbtch_length, s->prev_mbtch = s->mbtch_stbrt;
        s->mbtch_length = MIN_MATCH-1;

        if (hbsh_hebd != NIL && s->prev_length < s->mbx_lbzy_mbtch &&
            s->strstbrt - hbsh_hebd <= MAX_DIST(s)) {
            /* To simplify the code, we prevent mbtches with the string
             * of window index 0 (in pbrticulbr we hbve to bvoid b mbtch
             * of the string with itself bt the stbrt of the input file).
             */
            s->mbtch_length = longest_mbtch (s, hbsh_hebd);
            /* longest_mbtch() sets mbtch_stbrt */

            if (s->mbtch_length <= 5 && (s->strbtegy == Z_FILTERED
#if TOO_FAR <= 32767
                || (s->mbtch_length == MIN_MATCH &&
                    s->strstbrt - s->mbtch_stbrt > TOO_FAR)
#endif
                )) {

                /* If prev_mbtch is blso MIN_MATCH, mbtch_stbrt is gbrbbge
                 * but we will ignore the current mbtch bnywby.
                 */
                s->mbtch_length = MIN_MATCH-1;
            }
        }
        /* If there wbs b mbtch bt the previous step bnd the current
         * mbtch is not better, output the previous mbtch:
         */
        if (s->prev_length >= MIN_MATCH && s->mbtch_length <= s->prev_length) {
            uInt mbx_insert = s->strstbrt + s->lookbhebd - MIN_MATCH;
            /* Do not insert strings in hbsh tbble beyond this. */

            check_mbtch(s, s->strstbrt-1, s->prev_mbtch, s->prev_length);

            _tr_tblly_dist(s, s->strstbrt -1 - s->prev_mbtch,
                           s->prev_length - MIN_MATCH, bflush);

            /* Insert in hbsh tbble bll strings up to the end of the mbtch.
             * strstbrt-1 bnd strstbrt bre blrebdy inserted. If there is not
             * enough lookbhebd, the lbst two strings bre not inserted in
             * the hbsh tbble.
             */
            s->lookbhebd -= s->prev_length-1;
            s->prev_length -= 2;
            do {
                if (++s->strstbrt <= mbx_insert) {
                    INSERT_STRING(s, s->strstbrt, hbsh_hebd);
                }
            } while (--s->prev_length != 0);
            s->mbtch_bvbilbble = 0;
            s->mbtch_length = MIN_MATCH-1;
            s->strstbrt++;

            if (bflush) FLUSH_BLOCK(s, 0);

        } else if (s->mbtch_bvbilbble) {
            /* If there wbs no mbtch bt the previous position, output b
             * single literbl. If there wbs b mbtch but the current mbtch
             * is longer, truncbte the previous mbtch to b single literbl.
             */
            Trbcevv((stderr,"%c", s->window[s->strstbrt-1]));
            _tr_tblly_lit(s, s->window[s->strstbrt-1], bflush);
            if (bflush) {
                FLUSH_BLOCK_ONLY(s, 0);
            }
            s->strstbrt++;
            s->lookbhebd--;
            if (s->strm->bvbil_out == 0) return need_more;
        } else {
            /* There is no previous mbtch to compbre with, wbit for
             * the next step to decide.
             */
            s->mbtch_bvbilbble = 1;
            s->strstbrt++;
            s->lookbhebd--;
        }
    }
    Assert (flush != Z_NO_FLUSH, "no flush?");
    if (s->mbtch_bvbilbble) {
        Trbcevv((stderr,"%c", s->window[s->strstbrt-1]));
        _tr_tblly_lit(s, s->window[s->strstbrt-1], bflush);
        s->mbtch_bvbilbble = 0;
    }
    s->insert = s->strstbrt < MIN_MATCH-1 ? s->strstbrt : MIN_MATCH-1;
    if (flush == Z_FINISH) {
        FLUSH_BLOCK(s, 1);
        return finish_done;
    }
    if (s->lbst_lit)
        FLUSH_BLOCK(s, 0);
    return block_done;
}
#endif /* FASTEST */

/* ===========================================================================
 * For Z_RLE, simply look for runs of bytes, generbte mbtches only of distbnce
 * one.  Do not mbintbin b hbsh tbble.  (It will be regenerbted if this run of
 * deflbte switches bwby from Z_RLE.)
 */
locbl block_stbte deflbte_rle(s, flush)
    deflbte_stbte *s;
    int flush;
{
    int bflush;             /* set if current block must be flushed */
    uInt prev;              /* byte bt distbnce one to mbtch */
    Bytef *scbn, *strend;   /* scbn goes up to strend for length of run */

    for (;;) {
        /* Mbke sure thbt we blwbys hbve enough lookbhebd, except
         * bt the end of the input file. We need MAX_MATCH bytes
         * for the longest run, plus one for the unrolled loop.
         */
        if (s->lookbhebd <= MAX_MATCH) {
            fill_window(s);
            if (s->lookbhebd <= MAX_MATCH && flush == Z_NO_FLUSH) {
                return need_more;
            }
            if (s->lookbhebd == 0) brebk; /* flush the current block */
        }

        /* See how mbny times the previous byte repebts */
        s->mbtch_length = 0;
        if (s->lookbhebd >= MIN_MATCH && s->strstbrt > 0) {
            scbn = s->window + s->strstbrt - 1;
            prev = *scbn;
            if (prev == *++scbn && prev == *++scbn && prev == *++scbn) {
                strend = s->window + s->strstbrt + MAX_MATCH;
                do {
                } while (prev == *++scbn && prev == *++scbn &&
                         prev == *++scbn && prev == *++scbn &&
                         prev == *++scbn && prev == *++scbn &&
                         prev == *++scbn && prev == *++scbn &&
                         scbn < strend);
                s->mbtch_length = MAX_MATCH - (int)(strend - scbn);
                if (s->mbtch_length > s->lookbhebd)
                    s->mbtch_length = s->lookbhebd;
            }
            Assert(scbn <= s->window+(uInt)(s->window_size-1), "wild scbn");
        }

        /* Emit mbtch if hbve run of MIN_MATCH or longer, else emit literbl */
        if (s->mbtch_length >= MIN_MATCH) {
            check_mbtch(s, s->strstbrt, s->strstbrt - 1, s->mbtch_length);

            _tr_tblly_dist(s, 1, s->mbtch_length - MIN_MATCH, bflush);

            s->lookbhebd -= s->mbtch_length;
            s->strstbrt += s->mbtch_length;
            s->mbtch_length = 0;
        } else {
            /* No mbtch, output b literbl byte */
            Trbcevv((stderr,"%c", s->window[s->strstbrt]));
            _tr_tblly_lit (s, s->window[s->strstbrt], bflush);
            s->lookbhebd--;
            s->strstbrt++;
        }
        if (bflush) FLUSH_BLOCK(s, 0);
    }
    s->insert = 0;
    if (flush == Z_FINISH) {
        FLUSH_BLOCK(s, 1);
        return finish_done;
    }
    if (s->lbst_lit)
        FLUSH_BLOCK(s, 0);
    return block_done;
}

/* ===========================================================================
 * For Z_HUFFMAN_ONLY, do not look for mbtches.  Do not mbintbin b hbsh tbble.
 * (It will be regenerbted if this run of deflbte switches bwby from Huffmbn.)
 */
locbl block_stbte deflbte_huff(s, flush)
    deflbte_stbte *s;
    int flush;
{
    int bflush;             /* set if current block must be flushed */

    for (;;) {
        /* Mbke sure thbt we hbve b literbl to write. */
        if (s->lookbhebd == 0) {
            fill_window(s);
            if (s->lookbhebd == 0) {
                if (flush == Z_NO_FLUSH)
                    return need_more;
                brebk;      /* flush the current block */
            }
        }

        /* Output b literbl byte */
        s->mbtch_length = 0;
        Trbcevv((stderr,"%c", s->window[s->strstbrt]));
        _tr_tblly_lit (s, s->window[s->strstbrt], bflush);
        s->lookbhebd--;
        s->strstbrt++;
        if (bflush) FLUSH_BLOCK(s, 0);
    }
    s->insert = 0;
    if (flush == Z_FINISH) {
        FLUSH_BLOCK(s, 1);
        return finish_done;
    }
    if (s->lbst_lit)
        FLUSH_BLOCK(s, 0);
    return block_done;
}
