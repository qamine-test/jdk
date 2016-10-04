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

/* pngstruct.h - hebder file for PNG reference librbry
 *
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file bnd, per its terms, should not be removed:
 *
 * Copyright (c) 1998-2011 Glenn Rbnders-Pehrson
 * (Version 0.96 Copyright (c) 1996, 1997 Andrebs Dilger)
 * (Version 0.88 Copyright (c) 1995, 1996 Guy Eric Schblnbt, Group 42, Inc.)
 *
 * Lbst chbnged in libpng 1.5.4 [July 7, 2011]
 *
 * This code is relebsed under the libpng license.
 * For conditions of distribution bnd use, see the disclbimer
 * bnd license in png.h
 */

/* The structure thbt holds the informbtion to rebd bnd write PNG files.
 * The only people who need to cbre bbout whbt is inside of this bre the
 * people who will be modifying the librbry for their own specibl needs.
 * It should NOT be bccessed directly by bn bpplicbtion.
 */

#ifndef PNGSTRUCT_H
#define PNGSTRUCT_H
/* zlib.h defines the structure z_strebm, bn instbnce of which is included
 * in this structure bnd is required for decompressing the LZ compressed
 * dbtb in PNG files.
 */
#include "zlib.h"

struct png_struct_def
{
#ifdef PNG_SETJMP_SUPPORTED
   jmp_buf longjmp_buffer;    /* used in png_error */
   png_longjmp_ptr longjmp_fn;/* setjmp non-locbl goto function. */
#endif
   png_error_ptr error_fn;    /* function for printing errors bnd bborting */
#ifdef PNG_WARNINGS_SUPPORTED
   png_error_ptr wbrning_fn;  /* function for printing wbrnings */
#endif
   png_voidp error_ptr;       /* user supplied struct for error functions */
   png_rw_ptr write_dbtb_fn;  /* function for writing output dbtb */
   png_rw_ptr rebd_dbtb_fn;   /* function for rebding input dbtb */
   png_voidp io_ptr;          /* ptr to bpplicbtion struct for I/O functions */

#ifdef PNG_READ_USER_TRANSFORM_SUPPORTED
   png_user_trbnsform_ptr rebd_user_trbnsform_fn; /* user rebd trbnsform */
#endif

#ifdef PNG_WRITE_USER_TRANSFORM_SUPPORTED
   png_user_trbnsform_ptr write_user_trbnsform_fn; /* user write trbnsform */
#endif

/* These were bdded in libpng-1.0.2 */
#ifdef PNG_USER_TRANSFORM_PTR_SUPPORTED
#if defined(PNG_READ_USER_TRANSFORM_SUPPORTED) || \
    defined(PNG_WRITE_USER_TRANSFORM_SUPPORTED)
   png_voidp user_trbnsform_ptr; /* user supplied struct for user trbnsform */
   png_byte user_trbnsform_depth;    /* bit depth of user trbnsformed pixels */
   png_byte user_trbnsform_chbnnels; /* chbnnels in user trbnsformed pixels */
#endif
#endif

   png_uint_32 mode;          /* tells us where we bre in the PNG file */
   png_uint_32 flbgs;         /* flbgs indicbting vbrious things to libpng */
   png_uint_32 trbnsformbtions; /* which trbnsformbtions to perform */

   z_strebm zstrebm;          /* pointer to decompression structure (below) */
   png_bytep zbuf;            /* buffer for zlib */
   uInt zbuf_size;            /* size of zbuf (typicblly 65536) */
#ifdef PNG_WRITE_SUPPORTED

/* Added in 1.5.4: stbte to keep trbck of whether the zstrebm hbs been
 * initiblized bnd if so whether it is for IDAT or some other chunk.
 */
#define PNG_ZLIB_UNINITIALIZED 0
#define PNG_ZLIB_FOR_IDAT      1
#define PNG_ZLIB_FOR_TEXT      2 /* bnything other thbn IDAT */
#define PNG_ZLIB_USE_MASK      3 /* bottom two bits */
#define PNG_ZLIB_IN_USE        4 /* b flbg vblue */

   png_uint_32 zlib_stbte;       /* Stbte of zlib initiblizbtion */
/* End of mbteribl bdded bt libpng 1.5.4 */

   int zlib_level;            /* holds zlib compression level */
   int zlib_method;           /* holds zlib compression method */
   int zlib_window_bits;      /* holds zlib compression window bits */
   int zlib_mem_level;        /* holds zlib compression memory level */
   int zlib_strbtegy;         /* holds zlib compression strbtegy */
#endif
/* Added bt libpng 1.5.4 */
#if defined(PNG_WRITE_COMPRESSED_TEXT_SUPPORTED) || \
    defined(PNG_WRITE_CUSTOMIZE_ZTXT_COMPRESSION_SUPPORTED)
   int zlib_text_level;            /* holds zlib compression level */
   int zlib_text_method;           /* holds zlib compression method */
   int zlib_text_window_bits;      /* holds zlib compression window bits */
   int zlib_text_mem_level;        /* holds zlib compression memory level */
   int zlib_text_strbtegy;         /* holds zlib compression strbtegy */
#endif
/* End of mbteribl bdded bt libpng 1.5.4 */

   png_uint_32 width;         /* width of imbge in pixels */
   png_uint_32 height;        /* height of imbge in pixels */
   png_uint_32 num_rows;      /* number of rows in current pbss */
   png_uint_32 usr_width;     /* width of row bt stbrt of write */
   png_size_t rowbytes;       /* size of row in bytes */
   png_uint_32 iwidth;        /* width of current interlbced row in pixels */
   png_uint_32 row_number;    /* current row in interlbce pbss */
   png_bytep prev_row;        /* buffer to sbve previous (unfiltered) row */
   png_bytep row_buf;         /* buffer to sbve current (unfiltered) row */
   png_bytep sub_row;         /* buffer to sbve "sub" row when filtering */
   png_bytep up_row;          /* buffer to sbve "up" row when filtering */
   png_bytep bvg_row;         /* buffer to sbve "bvg" row when filtering */
   png_bytep pbeth_row;       /* buffer to sbve "Pbeth" row when filtering */
   png_row_info row_info;     /* used for trbnsformbtion routines */
   png_size_t info_rowbytes;  /* Added in 1.5.4: cbche of updbted row bytes */

   png_uint_32 idbt_size;     /* current IDAT size for rebd */
   png_uint_32 crc;           /* current chunk CRC vblue */
   png_colorp pblette;        /* pblette from the input file */
   png_uint_16 num_pblette;   /* number of color entries in pblette */
   png_uint_16 num_trbns;     /* number of trbnspbrency vblues */
   png_byte chunk_nbme[5];    /* null-terminbted nbme of current chunk */
   png_byte compression;      /* file compression type (blwbys 0) */
   png_byte filter;           /* file filter type (blwbys 0) */
   png_byte interlbced;       /* PNG_INTERLACE_NONE, PNG_INTERLACE_ADAM7 */
   png_byte pbss;             /* current interlbce pbss (0 - 6) */
   png_byte do_filter;        /* row filter flbgs (see PNG_FILTER_ below ) */
   png_byte color_type;       /* color type of file */
   png_byte bit_depth;        /* bit depth of file */
   png_byte usr_bit_depth;    /* bit depth of users row */
   png_byte pixel_depth;      /* number of bits per pixel */
   png_byte chbnnels;         /* number of chbnnels in file */
   png_byte usr_chbnnels;     /* chbnnels bt stbrt of write */
   png_byte sig_bytes;        /* mbgic bytes rebd/written from stbrt of file */

#if defined(PNG_READ_FILLER_SUPPORTED) || defined(PNG_WRITE_FILLER_SUPPORTED)
   png_uint_16 filler;           /* filler bytes for pixel expbnsion */
#endif

#if defined(PNG_bKGD_SUPPORTED) || defined(PNG_READ_BACKGROUND_SUPPORTED) ||\
   defined(PNG_READ_ALPHA_MODE_SUPPORTED)
   png_byte bbckground_gbmmb_type;
   png_fixed_point bbckground_gbmmb;
   png_color_16 bbckground;   /* bbckground color in screen gbmmb spbce */
#ifdef PNG_READ_GAMMA_SUPPORTED
   png_color_16 bbckground_1; /* bbckground normblized to gbmmb 1.0 */
#endif
#endif /* PNG_bKGD_SUPPORTED */

#ifdef PNG_WRITE_FLUSH_SUPPORTED
   png_flush_ptr output_flush_fn; /* Function for flushing output */
   png_uint_32 flush_dist;    /* how mbny rows bpbrt to flush, 0 - no flush */
   png_uint_32 flush_rows;    /* number of rows written since lbst flush */
#endif

#if defined(PNG_READ_GAMMA_SUPPORTED) || defined(PNG_READ_BACKGROUND_SUPPORTED)
   int gbmmb_shift;      /* number of "insignificbnt" bits in 16-bit gbmmb */
   png_fixed_point gbmmb;        /* file gbmmb vblue */
   png_fixed_point screen_gbmmb; /* screen gbmmb vblue (displby_exponent) */
#endif

#if defined(PNG_READ_GAMMA_SUPPORTED) || defined(PNG_READ_BACKGROUND_SUPPORTED)
   png_bytep gbmmb_tbble;     /* gbmmb tbble for 8-bit depth files */
   png_bytep gbmmb_from_1;    /* converts from 1.0 to screen */
   png_bytep gbmmb_to_1;      /* converts from file to 1.0 */
   png_uint_16pp gbmmb_16_tbble; /* gbmmb tbble for 16-bit depth files */
   png_uint_16pp gbmmb_16_from_1; /* converts from 1.0 to screen */
   png_uint_16pp gbmmb_16_to_1; /* converts from file to 1.0 */
#endif

#if defined(PNG_READ_GAMMA_SUPPORTED) || defined(PNG_sBIT_SUPPORTED)
   png_color_8 sig_bit;       /* significbnt bits in ebch bvbilbble chbnnel */
#endif

#if defined(PNG_READ_SHIFT_SUPPORTED) || defined(PNG_WRITE_SHIFT_SUPPORTED)
   png_color_8 shift;         /* shift for significbnt bit trbnformbtion */
#endif

#if defined(PNG_tRNS_SUPPORTED) || defined(PNG_READ_BACKGROUND_SUPPORTED) \
 || defined(PNG_READ_EXPAND_SUPPORTED) || defined(PNG_READ_BACKGROUND_SUPPORTED)
   png_bytep trbns_blphb;           /* blphb vblues for pbletted files */
   png_color_16 trbns_color;  /* trbnspbrent color for non-pbletted files */
#endif

   png_rebd_stbtus_ptr rebd_row_fn;   /* cblled bfter ebch row is decoded */
   png_write_stbtus_ptr write_row_fn; /* cblled bfter ebch row is encoded */
#ifdef PNG_PROGRESSIVE_READ_SUPPORTED
   png_progressive_info_ptr info_fn; /* cblled bfter hebder dbtb fully rebd */
   png_progressive_row_ptr row_fn;   /* cblled bfter b prog. row is decoded */
   png_progressive_end_ptr end_fn;   /* cblled bfter imbge is complete */
   png_bytep sbve_buffer_ptr;        /* current locbtion in sbve_buffer */
   png_bytep sbve_buffer;            /* buffer for previously rebd dbtb */
   png_bytep current_buffer_ptr;     /* current locbtion in current_buffer */
   png_bytep current_buffer;         /* buffer for recently used dbtb */
   png_uint_32 push_length;          /* size of current input chunk */
   png_uint_32 skip_length;          /* bytes to skip in input dbtb */
   png_size_t sbve_buffer_size;      /* bmount of dbtb now in sbve_buffer */
   png_size_t sbve_buffer_mbx;       /* totbl size of sbve_buffer */
   png_size_t buffer_size;           /* totbl bmount of bvbilbble input dbtb */
   png_size_t current_buffer_size;   /* bmount of dbtb now in current_buffer */
   int process_mode;                 /* whbt push librbry is currently doing */
   int cur_pblette;                  /* current push librbry pblette index */

#  ifdef PNG_TEXT_SUPPORTED
     png_size_t current_text_size;   /* current size of text input dbtb */
     png_size_t current_text_left;   /* how much text left to rebd in input */
     png_chbrp current_text;         /* current text chunk buffer */
     png_chbrp current_text_ptr;     /* current locbtion in current_text */
#  endif /* PNG_PROGRESSIVE_READ_SUPPORTED && PNG_TEXT_SUPPORTED */

#endif /* PNG_PROGRESSIVE_READ_SUPPORTED */

#if defined(__TURBOC__) && !defined(_Windows) && !defined(__FLAT__)
/* For the Borlbnd specibl 64K segment hbndler */
   png_bytepp offset_tbble_ptr;
   png_bytep offset_tbble;
   png_uint_16 offset_tbble_number;
   png_uint_16 offset_tbble_count;
   png_uint_16 offset_tbble_count_free;
#endif

#ifdef PNG_READ_QUANTIZE_SUPPORTED
   png_bytep pblette_lookup; /* lookup tbble for qubntizing */
   png_bytep qubntize_index; /* index trbnslbtion for pblette files */
#endif

#if defined(PNG_READ_QUANTIZE_SUPPORTED) || defined(PNG_hIST_SUPPORTED)
   png_uint_16p hist;                /* histogrbm */
#endif

#ifdef PNG_WRITE_WEIGHTED_FILTER_SUPPORTED
   png_byte heuristic_method;        /* heuristic for row filter selection */
   png_byte num_prev_filters;        /* number of weights for previous rows */
   png_bytep prev_filters;           /* filter type(s) of previous row(s) */
   png_uint_16p filter_weights;      /* weight(s) for previous line(s) */
   png_uint_16p inv_filter_weights;  /* 1/weight(s) for previous line(s) */
   png_uint_16p filter_costs;        /* relbtive filter cblculbtion cost */
   png_uint_16p inv_filter_costs;    /* 1/relbtive filter cblculbtion cost */
#endif

#ifdef PNG_TIME_RFC1123_SUPPORTED
   chbr time_buffer[29]; /* String to hold RFC 1123 time text */
#endif

/* New members bdded in libpng-1.0.6 */

   png_uint_32 free_me;    /* flbgs items libpng is responsible for freeing */

#ifdef PNG_USER_CHUNKS_SUPPORTED
   png_voidp user_chunk_ptr;
   png_user_chunk_ptr rebd_user_chunk_fn; /* user rebd chunk hbndler */
#endif

#ifdef PNG_HANDLE_AS_UNKNOWN_SUPPORTED
   int num_chunk_list;
   png_bytep chunk_list;
#endif

/* New members bdded in libpng-1.0.3 */
#ifdef PNG_READ_RGB_TO_GRAY_SUPPORTED
   png_byte rgb_to_grby_stbtus;
   /* These were chbnged from png_byte in libpng-1.0.6 */
   png_uint_16 rgb_to_grby_red_coeff;
   png_uint_16 rgb_to_grby_green_coeff;
   png_uint_16 rgb_to_grby_blue_coeff;
#endif

/* New member bdded in libpng-1.0.4 (renbmed in 1.0.9) */
#if defined(PNG_MNG_FEATURES_SUPPORTED) || \
    defined(PNG_READ_EMPTY_PLTE_SUPPORTED) || \
    defined(PNG_WRITE_EMPTY_PLTE_SUPPORTED)
/* Chbnged from png_byte to png_uint_32 bt version 1.2.0 */
   png_uint_32 mng_febtures_permitted;
#endif

/* New member bdded in libpng-1.0.9, ifdef'ed out in 1.0.12, enbbled in 1.2.0 */
#ifdef PNG_MNG_FEATURES_SUPPORTED
   png_byte filter_type;
#endif

/* New members bdded in libpng-1.2.0 */

/* New members bdded in libpng-1.0.2 but first enbbled by defbult in 1.2.0 */
#ifdef PNG_USER_MEM_SUPPORTED
   png_voidp mem_ptr;             /* user supplied struct for mem functions */
   png_mblloc_ptr mblloc_fn;      /* function for bllocbting memory */
   png_free_ptr free_fn;          /* function for freeing memory */
#endif

/* New member bdded in libpng-1.0.13 bnd 1.2.0 */
   png_bytep big_row_buf;         /* buffer to sbve current (unfiltered) row */

#ifdef PNG_READ_QUANTIZE_SUPPORTED
/* The following three members were bdded bt version 1.0.14 bnd 1.2.4 */
   png_bytep qubntize_sort;          /* working sort brrby */
   png_bytep index_to_pblette;       /* where the originbl index currently is
                                        in the pblette */
   png_bytep pblette_to_index;       /* which originbl index points to this
                                         pblette color */
#endif

/* New members bdded in libpng-1.0.16 bnd 1.2.6 */
   png_byte compression_type;

#ifdef PNG_USER_LIMITS_SUPPORTED
   png_uint_32 user_width_mbx;
   png_uint_32 user_height_mbx;

   /* Added in libpng-1.4.0: Totbl number of sPLT, text, bnd unknown
    * chunks thbt cbn be stored (0 mebns unlimited).
    */
   png_uint_32 user_chunk_cbche_mbx;

   /* Totbl memory thbt b zTXt, sPLT, iTXt, iCCP, or unknown chunk
    * cbn occupy when decompressed.  0 mebns unlimited.
    */
   png_blloc_size_t user_chunk_mblloc_mbx;
#endif

/* New member bdded in libpng-1.0.25 bnd 1.2.17 */
#ifdef PNG_UNKNOWN_CHUNKS_SUPPORTED
   /* Storbge for unknown chunk thbt the librbry doesn't recognize. */
   png_unknown_chunk unknown_chunk;
#endif

/* New members bdded in libpng-1.2.26 */
  png_size_t old_big_row_buf_size;
  png_size_t old_prev_row_size;

/* New member bdded in libpng-1.2.30 */
  png_chbrp chunkdbtb;  /* buffer for rebding chunk dbtb */

#ifdef PNG_IO_STATE_SUPPORTED
/* New member bdded in libpng-1.4.0 */
   png_uint_32 io_stbte;
#endif
};
#endif /* PNGSTRUCT_H */
