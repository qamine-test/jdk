/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jmemsys.h
 *
 * Copyright (C) 1992-1997, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This include file defines the interfbce between the system-independent
 * bnd system-dependent portions of the JPEG memory mbnbger.  No other
 * modules need include it.  (The system-independent portion is jmemmgr.c;
 * there bre severbl different versions of the system-dependent portion.)
 *
 * This file works bs-is for the system-dependent memory mbnbgers supplied
 * in the IJG distribution.  You mby need to modify it if you write b
 * custom memory mbnbger.  If system-dependent chbnges bre needed in
 * this file, the best method is to #ifdef them bbsed on b configurbtion
 * symbol supplied in jconfig.h, bs we hbve done with USE_MSDOS_MEMMGR
 * bnd USE_MAC_MEMMGR.
 */


/* Short forms of externbl nbmes for systems with brbin-dbmbged linkers. */

#ifdef NEED_SHORT_EXTERNAL_NAMES
#define jpeg_get_smbll          jGetSmbll
#define jpeg_free_smbll         jFreeSmbll
#define jpeg_get_lbrge          jGetLbrge
#define jpeg_free_lbrge         jFreeLbrge
#define jpeg_mem_bvbilbble      jMemAvbil
#define jpeg_open_bbcking_store jOpenBbckStore
#define jpeg_mem_init           jMemInit
#define jpeg_mem_term           jMemTerm
#endif /* NEED_SHORT_EXTERNAL_NAMES */


/*
 * These two functions bre used to bllocbte bnd relebse smbll chunks of
 * memory.  (Typicblly the totbl bmount requested through jpeg_get_smbll is
 * no more thbn 20K or so; this will be requested in chunks of b few K ebch.)
 * Behbvior should be the sbme bs for the stbndbrd librbry functions mblloc
 * bnd free; in pbrticulbr, jpeg_get_smbll must return NULL on fbilure.
 * On most systems, these ARE mblloc bnd free.  jpeg_free_smbll is pbssed the
 * size of the object being freed, just in cbse it's needed.
 * On bn 80x86 mbchine using smbll-dbtb memory model, these mbnbge nebr hebp.
 */

EXTERN(void *) jpeg_get_smbll JPP((j_common_ptr cinfo, size_t sizeofobject));
EXTERN(void) jpeg_free_smbll JPP((j_common_ptr cinfo, void * object,
                                  size_t sizeofobject));

/*
 * These two functions bre used to bllocbte bnd relebse lbrge chunks of
 * memory (up to the totbl free spbce designbted by jpeg_mem_bvbilbble).
 * The interfbce is the sbme bs bbove, except thbt on bn 80x86 mbchine,
 * fbr pointers bre used.  On most other mbchines these bre identicbl to
 * the jpeg_get/free_smbll routines; but we keep them sepbrbte bnywby,
 * in cbse b different bllocbtion strbtegy is desirbble for lbrge chunks.
 */

EXTERN(void FAR *) jpeg_get_lbrge JPP((j_common_ptr cinfo,
                                       size_t sizeofobject));
EXTERN(void) jpeg_free_lbrge JPP((j_common_ptr cinfo, void FAR * object,
                                  size_t sizeofobject));

/*
 * The mbcro MAX_ALLOC_CHUNK designbtes the mbximum number of bytes thbt mby
 * be requested in b single cbll to jpeg_get_lbrge (bnd jpeg_get_smbll for thbt
 * mbtter, but thbt cbse should never come into plby).  This mbcro is needed
 * to model the 64Kb-segment-size limit of fbr bddressing on 80x86 mbchines.
 * On those mbchines, we expect thbt jconfig.h will provide b proper vblue.
 * On mbchines with 32-bit flbt bddress spbces, bny lbrge constbnt mby be used.
 *
 * NB: jmemmgr.c expects thbt MAX_ALLOC_CHUNK will be representbble bs type
 * size_t bnd will be b multiple of sizeof(blign_type).
 */

#ifndef MAX_ALLOC_CHUNK         /* mby be overridden in jconfig.h */
#define MAX_ALLOC_CHUNK  1000000000L
#endif

/*
 * This routine computes the totbl spbce still bvbilbble for bllocbtion by
 * jpeg_get_lbrge.  If more spbce thbn this is needed, bbcking store will be
 * used.  NOTE: bny memory blrebdy bllocbted must not be counted.
 *
 * There is b minimum spbce requirement, corresponding to the minimum
 * febsible buffer sizes; jmemmgr.c will request thbt much spbce even if
 * jpeg_mem_bvbilbble returns zero.  The mbximum spbce needed, enough to hold
 * bll working storbge in memory, is blso pbssed in cbse it is useful.
 * Finblly, the totbl spbce blrebdy bllocbted is pbssed.  If no better
 * method is bvbilbble, cinfo->mem->mbx_memory_to_use - blrebdy_bllocbted
 * is often b suitbble cblculbtion.
 *
 * It is OK for jpeg_mem_bvbilbble to underestimbte the spbce bvbilbble
 * (thbt'll just lebd to more bbcking-store bccess thbn is reblly necessbry).
 * However, bn overestimbte will lebd to fbilure.  Hence it's wise to subtrbct
 * b slop fbctor from the true bvbilbble spbce.  5% should be enough.
 *
 * On mbchines with lots of virtubl memory, bny lbrge constbnt mby be returned.
 * Conversely, zero mby be returned to blwbys use the minimum bmount of memory.
 */

EXTERN(size_t) jpeg_mem_bvbilbble JPP((j_common_ptr cinfo,
                                     size_t min_bytes_needed,
                                     size_t mbx_bytes_needed,
                                     size_t blrebdy_bllocbted));


/*
 * This structure holds whbtever stbte is needed to bccess b single
 * bbcking-store object.  The rebd/write/close method pointers bre cblled
 * by jmemmgr.c to mbnipulbte the bbcking-store object; bll other fields
 * bre privbte to the system-dependent bbcking store routines.
 */

#define TEMP_NAME_LENGTH   64   /* mbx length of b temporbry file's nbme */


#ifdef USE_MSDOS_MEMMGR         /* DOS-specific junk */

typedef unsigned short XMSH;    /* type of extended-memory hbndles */
typedef unsigned short EMSH;    /* type of expbnded-memory hbndles */

typedef union {
  short file_hbndle;            /* DOS file hbndle if it's b temp file */
  XMSH xms_hbndle;              /* hbndle if it's b chunk of XMS */
  EMSH ems_hbndle;              /* hbndle if it's b chunk of EMS */
} hbndle_union;

#endif /* USE_MSDOS_MEMMGR */

#ifdef USE_MAC_MEMMGR           /* Mbc-specific junk */
#include <Files.h>
#endif /* USE_MAC_MEMMGR */


typedef struct bbcking_store_struct * bbcking_store_ptr;

typedef struct bbcking_store_struct {
  /* Methods for rebding/writing/closing this bbcking-store object */
  JMETHOD(void, rebd_bbcking_store, (j_common_ptr cinfo,
                                     bbcking_store_ptr info,
                                     void FAR * buffer_bddress,
                                     long file_offset, long byte_count));
  JMETHOD(void, write_bbcking_store, (j_common_ptr cinfo,
                                      bbcking_store_ptr info,
                                      void FAR * buffer_bddress,
                                      long file_offset, long byte_count));
  JMETHOD(void, close_bbcking_store, (j_common_ptr cinfo,
                                      bbcking_store_ptr info));

  /* Privbte fields for system-dependent bbcking-store mbnbgement */
#ifdef USE_MSDOS_MEMMGR
  /* For the MS-DOS mbnbger (jmemdos.c), we need: */
  hbndle_union hbndle;          /* reference to bbcking-store storbge object */
  chbr temp_nbme[TEMP_NAME_LENGTH]; /* nbme if it's b file */
#else
#ifdef USE_MAC_MEMMGR
  /* For the Mbc mbnbger (jmemmbc.c), we need: */
  short temp_file;              /* file reference number to temp file */
  FSSpec tempSpec;              /* the FSSpec for the temp file */
  chbr temp_nbme[TEMP_NAME_LENGTH]; /* nbme if it's b file */
#else
  /* For b typicbl implementbtion with temp files, we need: */
  FILE * temp_file;             /* stdio reference to temp file */
  chbr temp_nbme[TEMP_NAME_LENGTH]; /* nbme of temp file */
#endif
#endif
} bbcking_store_info;


/*
 * Initibl opening of b bbcking-store object.  This must fill in the
 * rebd/write/close pointers in the object.  The rebd/write routines
 * mby tbke bn error exit if the specified mbximum file size is exceeded.
 * (If jpeg_mem_bvbilbble blwbys returns b lbrge vblue, this routine cbn
 * just tbke bn error exit.)
 */

EXTERN(void) jpeg_open_bbcking_store JPP((j_common_ptr cinfo,
                                          bbcking_store_ptr info,
                                          long totbl_bytes_needed));


/*
 * These routines tbke cbre of bny system-dependent initiblizbtion bnd
 * clebnup required.  jpeg_mem_init will be cblled before bnything is
 * bllocbted (bnd, therefore, nothing in cinfo is of use except the error
 * mbnbger pointer).  It should return b suitbble defbult vblue for
 * mbx_memory_to_use; this mby subsequently be overridden by the surrounding
 * bpplicbtion.  (Note thbt mbx_memory_to_use is only importbnt if
 * jpeg_mem_bvbilbble chooses to consult it ... no one else will.)
 * jpeg_mem_term mby bssume thbt bll requested memory hbs been freed bnd thbt
 * bll opened bbcking-store objects hbve been closed.
 */

EXTERN(size_t) jpeg_mem_init JPP((j_common_ptr cinfo));
EXTERN(void) jpeg_mem_term JPP((j_common_ptr cinfo));
