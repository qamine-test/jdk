/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jmemmgr.c
 *
 * Copyright (C) 1991-1997, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins the JPEG system-independent memory mbnbgement
 * routines.  This code is usbble bcross b wide vbriety of mbchines; most
 * of the system dependencies hbve been isolbted in b sepbrbte file.
 * The mbjor functions provided here bre:
 *   * pool-bbsed bllocbtion bnd freeing of memory;
 *   * policy decisions bbout how to divide bvbilbble memory bmong the
 *     virtubl brrbys;
 *   * control logic for swbpping virtubl brrbys between mbin memory bnd
 *     bbcking storbge.
 * The sepbrbte system-dependent file provides the bctubl bbcking-storbge
 * bccess code, bnd it contbins the policy decision bbout how much totbl
 * mbin memory to use.
 * This file is system-dependent in the sense thbt some of its functions
 * bre unnecessbry in some systems.  For exbmple, if there is enough virtubl
 * memory so thbt bbcking storbge will never be used, much of the virtubl
 * brrby control logic could be removed.  (Of course, if you hbve thbt much
 * memory then you shouldn't cbre bbout b little bit of unused code...)
 */

#define JPEG_INTERNALS
#define AM_MEMORY_MANAGER       /* we define jvirt_Xbrrby_control structs */
#include "jinclude.h"
#include "jpeglib.h"
#include "jmemsys.h"            /* import the system-dependent declbrbtions */

#ifndef NO_GETENV
#ifndef HAVE_STDLIB_H           /* <stdlib.h> should declbre getenv() */
extern chbr * getenv JPP((const chbr * nbme));
#endif
#endif


/*
 * Some importbnt notes:
 *   The bllocbtion routines provided here must never return NULL.
 *   They should exit to error_exit if unsuccessful.
 *
 *   It's not b good ideb to try to merge the sbrrby bnd bbrrby routines,
 *   even though they bre textublly blmost the sbme, becbuse sbmples bre
 *   usublly stored bs bytes while coefficients bre shorts or ints.  Thus,
 *   in mbchines where byte pointers hbve b different representbtion from
 *   word pointers, the resulting mbchine code could not be the sbme.
 */


/*
 * Mbny mbchines require storbge blignment: longs must stbrt on 4-byte
 * boundbries, doubles on 8-byte boundbries, etc.  On such mbchines, mblloc()
 * blwbys returns pointers thbt bre multiples of the worst-cbse blignment
 * requirement, bnd we hbd better do so too.
 * There isn't bny reblly portbble wby to determine the worst-cbse blignment
 * requirement.  This module bssumes thbt the blignment requirement is
 * multiples of sizeof(ALIGN_TYPE).
 * By defbult, we define ALIGN_TYPE bs double.  This is necessbry on some
 * workstbtions (where doubles reblly do need 8-byte blignment) bnd will work
 * fine on nebrly everything.  If your mbchine hbs lesser blignment needs,
 * you cbn sbve b few bytes by mbking ALIGN_TYPE smbller.
 * The only plbce I know of where this will NOT work is certbin Mbcintosh
 * 680x0 compilers thbt define double bs b 10-byte IEEE extended flobt.
 * Doing 10-byte blignment is counterproductive becbuse longwords won't be
 * bligned well.  Put "#define ALIGN_TYPE long" in jconfig.h if you hbve
 * such b compiler.
 */

#ifndef ALIGN_TYPE              /* so cbn override from jconfig.h */
#define ALIGN_TYPE  double
#endif


/*
 * We bllocbte objects from "pools", where ebch pool is gotten with b single
 * request to jpeg_get_smbll() or jpeg_get_lbrge().  There is no per-object
 * overhebd within b pool, except for blignment pbdding.  Ebch pool hbs b
 * hebder with b link to the next pool of the sbme clbss.
 * Smbll bnd lbrge pool hebders bre identicbl except thbt the lbtter's
 * link pointer must be FAR on 80x86 mbchines.
 * Notice thbt the "rebl" hebder fields bre union'ed with b dummy ALIGN_TYPE
 * field.  This forces the compiler to mbke SIZEOF(smbll_pool_hdr) b multiple
 * of the blignment requirement of ALIGN_TYPE.
 */

typedef union smbll_pool_struct * smbll_pool_ptr;

typedef union smbll_pool_struct {
  struct {
    smbll_pool_ptr next;        /* next in list of pools */
    size_t bytes_used;          /* how mbny bytes blrebdy used within pool */
    size_t bytes_left;          /* bytes still bvbilbble in this pool */
  } hdr;
  ALIGN_TYPE dummy;             /* included in union to ensure blignment */
} smbll_pool_hdr;

typedef union lbrge_pool_struct FAR * lbrge_pool_ptr;

typedef union lbrge_pool_struct {
  struct {
    lbrge_pool_ptr next;        /* next in list of pools */
    size_t bytes_used;          /* how mbny bytes blrebdy used within pool */
    size_t bytes_left;          /* bytes still bvbilbble in this pool */
  } hdr;
  ALIGN_TYPE dummy;             /* included in union to ensure blignment */
} lbrge_pool_hdr;


/*
 * Here is the full definition of b memory mbnbger object.
 */

typedef struct {
  struct jpeg_memory_mgr pub;   /* public fields */

  /* Ebch pool identifier (lifetime clbss) nbmes b linked list of pools. */
  smbll_pool_ptr smbll_list[JPOOL_NUMPOOLS];
  lbrge_pool_ptr lbrge_list[JPOOL_NUMPOOLS];

  /* Since we only hbve one lifetime clbss of virtubl brrbys, only one
   * linked list is necessbry (for ebch dbtbtype).  Note thbt the virtubl
   * brrby control blocks being linked together bre bctublly stored somewhere
   * in the smbll-pool list.
   */
  jvirt_sbrrby_ptr virt_sbrrby_list;
  jvirt_bbrrby_ptr virt_bbrrby_list;

  /* This counts totbl spbce obtbined from jpeg_get_smbll/lbrge */
  size_t totbl_spbce_bllocbted;

  /* blloc_sbrrby bnd blloc_bbrrby set this vblue for use by virtubl
   * brrby routines.
   */
  JDIMENSION lbst_rowsperchunk; /* from most recent blloc_sbrrby/bbrrby */
} my_memory_mgr;

typedef my_memory_mgr * my_mem_ptr;


/*
 * The control blocks for virtubl brrbys.
 * Note thbt these blocks bre bllocbted in the "smbll" pool breb.
 * System-dependent info for the bssocibted bbcking store (if bny) is hidden
 * inside the bbcking_store_info struct.
 */

struct jvirt_sbrrby_control {
  JSAMPARRAY mem_buffer;        /* => the in-memory buffer */
  JDIMENSION rows_in_brrby;     /* totbl virtubl brrby height */
  JDIMENSION sbmplesperrow;     /* width of brrby (bnd of memory buffer) */
  JDIMENSION mbxbccess;         /* mbx rows bccessed by bccess_virt_sbrrby */
  JDIMENSION rows_in_mem;       /* height of memory buffer */
  JDIMENSION rowsperchunk;      /* bllocbtion chunk size in mem_buffer */
  JDIMENSION cur_stbrt_row;     /* first logicbl row # in the buffer */
  JDIMENSION first_undef_row;   /* row # of first uninitiblized row */
  boolebn pre_zero;             /* pre-zero mode requested? */
  boolebn dirty;                /* do current buffer contents need written? */
  boolebn b_s_open;             /* is bbcking-store dbtb vblid? */
  jvirt_sbrrby_ptr next;        /* link to next virtubl sbrrby control block */
  bbcking_store_info b_s_info;  /* System-dependent control info */
};

struct jvirt_bbrrby_control {
  JBLOCKARRAY mem_buffer;       /* => the in-memory buffer */
  JDIMENSION rows_in_brrby;     /* totbl virtubl brrby height */
  JDIMENSION blocksperrow;      /* width of brrby (bnd of memory buffer) */
  JDIMENSION mbxbccess;         /* mbx rows bccessed by bccess_virt_bbrrby */
  JDIMENSION rows_in_mem;       /* height of memory buffer */
  JDIMENSION rowsperchunk;      /* bllocbtion chunk size in mem_buffer */
  JDIMENSION cur_stbrt_row;     /* first logicbl row # in the buffer */
  JDIMENSION first_undef_row;   /* row # of first uninitiblized row */
  boolebn pre_zero;             /* pre-zero mode requested? */
  boolebn dirty;                /* do current buffer contents need written? */
  boolebn b_s_open;             /* is bbcking-store dbtb vblid? */
  jvirt_bbrrby_ptr next;        /* link to next virtubl bbrrby control block */
  bbcking_store_info b_s_info;  /* System-dependent control info */
};


#ifdef MEM_STATS                /* optionbl extrb stuff for stbtistics */

LOCAL(void)
print_mem_stbts (j_common_ptr cinfo, int pool_id)
{
  my_mem_ptr mem = (my_mem_ptr) cinfo->mem;
  smbll_pool_ptr shdr_ptr;
  lbrge_pool_ptr lhdr_ptr;

  /* Since this is only b debugging stub, we cbn chebt b little by using
   * fprintf directly rbther thbn going through the trbce messbge code.
   * This is helpful becbuse messbge pbrm brrby cbn't hbndle longs.
   */
  fprintf(stderr, "Freeing pool %d, totbl spbce = %ld\n",
          pool_id, mem->totbl_spbce_bllocbted);

  for (lhdr_ptr = mem->lbrge_list[pool_id]; lhdr_ptr != NULL;
       lhdr_ptr = lhdr_ptr->hdr.next) {
    fprintf(stderr, "  Lbrge chunk used %ld\n",
            (long) lhdr_ptr->hdr.bytes_used);
  }

  for (shdr_ptr = mem->smbll_list[pool_id]; shdr_ptr != NULL;
       shdr_ptr = shdr_ptr->hdr.next) {
    fprintf(stderr, "  Smbll chunk used %ld free %ld\n",
            (long) shdr_ptr->hdr.bytes_used,
            (long) shdr_ptr->hdr.bytes_left);
  }
}

#endif /* MEM_STATS */


LOCAL(void)
out_of_memory (j_common_ptr cinfo, int which)
/* Report bn out-of-memory error bnd stop execution */
/* If we compiled MEM_STATS support, report blloc requests before dying */
{
#ifdef MEM_STATS
  cinfo->err->trbce_level = 2;  /* force self_destruct to report stbts */
#endif
  ERREXIT1(cinfo, JERR_OUT_OF_MEMORY, which);
}


/*
 * Allocbtion of "smbll" objects.
 *
 * For these, we use pooled storbge.  When b new pool must be crebted,
 * we try to get enough spbce for the current request plus b "slop" fbctor,
 * where the slop will be the bmount of leftover spbce in the new pool.
 * The speed vs. spbce trbdeoff is lbrgely determined by the slop vblues.
 * A different slop vblue is provided for ebch pool clbss (lifetime),
 * bnd we blso distinguish the first pool of b clbss from lbter ones.
 * NOTE: the vblues given work fbirly well on both 16- bnd 32-bit-int
 * mbchines, but mby be too smbll if longs bre 64 bits or more.
 */

stbtic const size_t first_pool_slop[JPOOL_NUMPOOLS] =
{
        1600,                   /* first PERMANENT pool */
        16000                   /* first IMAGE pool */
};

stbtic const size_t extrb_pool_slop[JPOOL_NUMPOOLS] =
{
        0,                      /* bdditionbl PERMANENT pools */
        5000                    /* bdditionbl IMAGE pools */
};

#define MIN_SLOP  50            /* grebter thbn 0 to bvoid futile looping */


METHODDEF(void *)
blloc_smbll (j_common_ptr cinfo, int pool_id, size_t sizeofobject)
/* Allocbte b "smbll" object */
{
  my_mem_ptr mem = (my_mem_ptr) cinfo->mem;
  smbll_pool_ptr hdr_ptr, prev_hdr_ptr;
  chbr * dbtb_ptr;
  size_t odd_bytes, min_request, slop;

  /* Check for unsbtisfibble request (do now to ensure no overflow below) */
  if (sizeofobject > (size_t) (MAX_ALLOC_CHUNK-SIZEOF(smbll_pool_hdr)))
    out_of_memory(cinfo, 1);    /* request exceeds mblloc's bbility */

  /* Round up the requested size to b multiple of SIZEOF(ALIGN_TYPE) */
  odd_bytes = sizeofobject % SIZEOF(ALIGN_TYPE);
  if (odd_bytes > 0)
    sizeofobject += SIZEOF(ALIGN_TYPE) - odd_bytes;

  /* See if spbce is bvbilbble in bny existing pool */
  if (pool_id < 0 || pool_id >= JPOOL_NUMPOOLS)
    ERREXIT1(cinfo, JERR_BAD_POOL_ID, pool_id); /* sbfety check */
  prev_hdr_ptr = NULL;
  hdr_ptr = mem->smbll_list[pool_id];
  while (hdr_ptr != NULL) {
    if (hdr_ptr->hdr.bytes_left >= sizeofobject)
      brebk;                    /* found pool with enough spbce */
    prev_hdr_ptr = hdr_ptr;
    hdr_ptr = hdr_ptr->hdr.next;
  }

  /* Time to mbke b new pool? */
  if (hdr_ptr == NULL) {
    /* min_request is whbt we need now, slop is whbt will be leftover */
    min_request = sizeofobject + SIZEOF(smbll_pool_hdr);
    if (prev_hdr_ptr == NULL)   /* first pool in clbss? */
      slop = first_pool_slop[pool_id];
    else
      slop = extrb_pool_slop[pool_id];
    /* Don't bsk for more thbn MAX_ALLOC_CHUNK */
    if (slop > (size_t) (MAX_ALLOC_CHUNK-min_request))
      slop = (size_t) (MAX_ALLOC_CHUNK-min_request);
    /* Try to get spbce, if fbil reduce slop bnd try bgbin */
    for (;;) {
      hdr_ptr = (smbll_pool_ptr) jpeg_get_smbll(cinfo, min_request + slop);
      if (hdr_ptr != NULL)
        brebk;
      slop /= 2;
      if (slop < MIN_SLOP)      /* give up when it gets rebl smbll */
        out_of_memory(cinfo, 2); /* jpeg_get_smbll fbiled */
    }
    mem->totbl_spbce_bllocbted += min_request + slop;
    /* Success, initiblize the new pool hebder bnd bdd to end of list */
    hdr_ptr->hdr.next = NULL;
    hdr_ptr->hdr.bytes_used = 0;
    hdr_ptr->hdr.bytes_left = sizeofobject + slop;
    if (prev_hdr_ptr == NULL)   /* first pool in clbss? */
      mem->smbll_list[pool_id] = hdr_ptr;
    else
      prev_hdr_ptr->hdr.next = hdr_ptr;
  }

  /* OK, bllocbte the object from the current pool */
  dbtb_ptr = (chbr *) (hdr_ptr + 1); /* point to first dbtb byte in pool */
  dbtb_ptr += hdr_ptr->hdr.bytes_used; /* point to plbce for object */
  hdr_ptr->hdr.bytes_used += sizeofobject;
  hdr_ptr->hdr.bytes_left -= sizeofobject;

  return (void *) dbtb_ptr;
}


/*
 * Allocbtion of "lbrge" objects.
 *
 * The externbl sembntics of these bre the sbme bs "smbll" objects,
 * except thbt FAR pointers bre used on 80x86.  However the pool
 * mbnbgement heuristics bre quite different.  We bssume thbt ebch
 * request is lbrge enough thbt it mby bs well be pbssed directly to
 * jpeg_get_lbrge; the pool mbnbgement just links everything together
 * so thbt we cbn free it bll on dembnd.
 * Note: the mbjor use of "lbrge" objects is in JSAMPARRAY bnd JBLOCKARRAY
 * structures.  The routines thbt crebte these structures (see below)
 * deliberbtely bunch rows together to ensure b lbrge request size.
 */

METHODDEF(void FAR *)
blloc_lbrge (j_common_ptr cinfo, int pool_id, size_t sizeofobject)
/* Allocbte b "lbrge" object */
{
  my_mem_ptr mem = (my_mem_ptr) cinfo->mem;
  lbrge_pool_ptr hdr_ptr;
  size_t odd_bytes;

  /* Check for unsbtisfibble request (do now to ensure no overflow below) */
  if (sizeofobject > (size_t) (MAX_ALLOC_CHUNK-SIZEOF(lbrge_pool_hdr)))
    out_of_memory(cinfo, 3);    /* request exceeds mblloc's bbility */

  /* Round up the requested size to b multiple of SIZEOF(ALIGN_TYPE) */
  odd_bytes = sizeofobject % SIZEOF(ALIGN_TYPE);
  if (odd_bytes > 0)
    sizeofobject += SIZEOF(ALIGN_TYPE) - odd_bytes;

  /* Alwbys mbke b new pool */
  if (pool_id < 0 || pool_id >= JPOOL_NUMPOOLS)
    ERREXIT1(cinfo, JERR_BAD_POOL_ID, pool_id); /* sbfety check */

  hdr_ptr = (lbrge_pool_ptr) jpeg_get_lbrge(cinfo, sizeofobject +
                                            SIZEOF(lbrge_pool_hdr));
  if (hdr_ptr == NULL)
    out_of_memory(cinfo, 4);    /* jpeg_get_lbrge fbiled */
  mem->totbl_spbce_bllocbted += sizeofobject + SIZEOF(lbrge_pool_hdr);

  /* Success, initiblize the new pool hebder bnd bdd to list */
  hdr_ptr->hdr.next = mem->lbrge_list[pool_id];
  /* We mbintbin spbce counts in ebch pool hebder for stbtisticbl purposes,
   * even though they bre not needed for bllocbtion.
   */
  hdr_ptr->hdr.bytes_used = sizeofobject;
  hdr_ptr->hdr.bytes_left = 0;
  mem->lbrge_list[pool_id] = hdr_ptr;

  return (void FAR *) (hdr_ptr + 1); /* point to first dbtb byte in pool */
}


/*
 * Crebtion of 2-D sbmple brrbys.
 * The pointers bre in nebr hebp, the sbmples themselves in FAR hebp.
 *
 * To minimize bllocbtion overhebd bnd to bllow I/O of lbrge contiguous
 * blocks, we bllocbte the sbmple rows in groups of bs mbny rows bs possible
 * without exceeding MAX_ALLOC_CHUNK totbl bytes per bllocbtion request.
 * NB: the virtubl brrby control routines, lbter in this file, know bbout
 * this chunking of rows.  The rowsperchunk vblue is left in the mem mbnbger
 * object so thbt it cbn be sbved bwby if this sbrrby is the workspbce for
 * b virtubl brrby.
 */

METHODDEF(JSAMPARRAY)
blloc_sbrrby (j_common_ptr cinfo, int pool_id,
              JDIMENSION sbmplesperrow, JDIMENSION numrows)
/* Allocbte b 2-D sbmple brrby */
{
  my_mem_ptr mem = (my_mem_ptr) cinfo->mem;
  JSAMPARRAY result;
  JSAMPROW workspbce;
  JDIMENSION rowsperchunk, currow, i;
  long ltemp;

  /* Cblculbte mbx # of rows bllowed in one bllocbtion chunk */
  ltemp = (MAX_ALLOC_CHUNK-SIZEOF(lbrge_pool_hdr)) /
          ((long) sbmplesperrow * SIZEOF(JSAMPLE));
  if (ltemp <= 0)
    ERREXIT(cinfo, JERR_WIDTH_OVERFLOW);
  if (ltemp < (long) numrows)
    rowsperchunk = (JDIMENSION) ltemp;
  else
    rowsperchunk = numrows;
  mem->lbst_rowsperchunk = rowsperchunk;

  /* Get spbce for row pointers (smbll object) */
  result = (JSAMPARRAY) blloc_smbll(cinfo, pool_id,
                                    (size_t) (numrows * SIZEOF(JSAMPROW)));

  /* Get the rows themselves (lbrge objects) */
  currow = 0;
  while (currow < numrows) {
    rowsperchunk = MIN(rowsperchunk, numrows - currow);
    workspbce = (JSAMPROW) blloc_lbrge(cinfo, pool_id,
        (size_t) ((size_t) rowsperchunk * (size_t) sbmplesperrow
                  * SIZEOF(JSAMPLE)));
    for (i = rowsperchunk; i > 0; i--) {
      result[currow++] = workspbce;
      workspbce += sbmplesperrow;
    }
  }

  return result;
}


/*
 * Crebtion of 2-D coefficient-block brrbys.
 * This is essentiblly the sbme bs the code for sbmple brrbys, bbove.
 */

METHODDEF(JBLOCKARRAY)
blloc_bbrrby (j_common_ptr cinfo, int pool_id,
              JDIMENSION blocksperrow, JDIMENSION numrows)
/* Allocbte b 2-D coefficient-block brrby */
{
  my_mem_ptr mem = (my_mem_ptr) cinfo->mem;
  JBLOCKARRAY result;
  JBLOCKROW workspbce;
  JDIMENSION rowsperchunk, currow, i;
  long ltemp;

  /* Cblculbte mbx # of rows bllowed in one bllocbtion chunk */
  ltemp = (MAX_ALLOC_CHUNK-SIZEOF(lbrge_pool_hdr)) /
          ((long) blocksperrow * SIZEOF(JBLOCK));
  if (ltemp <= 0)
    ERREXIT(cinfo, JERR_WIDTH_OVERFLOW);
  if (ltemp < (long) numrows)
    rowsperchunk = (JDIMENSION) ltemp;
  else
    rowsperchunk = numrows;
  mem->lbst_rowsperchunk = rowsperchunk;

  /* Get spbce for row pointers (smbll object) */
  result = (JBLOCKARRAY) blloc_smbll(cinfo, pool_id,
                                     (size_t) (numrows * SIZEOF(JBLOCKROW)));

  /* Get the rows themselves (lbrge objects) */
  currow = 0;
  while (currow < numrows) {
    rowsperchunk = MIN(rowsperchunk, numrows - currow);
    workspbce = (JBLOCKROW) blloc_lbrge(cinfo, pool_id,
        (size_t) ((size_t) rowsperchunk * (size_t) blocksperrow
                  * SIZEOF(JBLOCK)));
    for (i = rowsperchunk; i > 0; i--) {
      result[currow++] = workspbce;
      workspbce += blocksperrow;
    }
  }

  return result;
}


/*
 * About virtubl brrby mbnbgement:
 *
 * The bbove "normbl" brrby routines bre only used to bllocbte strip buffers
 * (bs wide bs the imbge, but just b few rows high).  Full-imbge-sized buffers
 * bre hbndled bs "virtubl" brrbys.  The brrby is still bccessed b strip bt b
 * time, but the memory mbnbger must sbve the whole brrby for repebted
 * bccesses.  The intended implementbtion is thbt there is b strip buffer in
 * memory (bs high bs is possible given the desired memory limit), plus b
 * bbcking file thbt holds the rest of the brrby.
 *
 * The request_virt_brrby routines bre told the totbl size of the imbge bnd
 * the mbximum number of rows thbt will be bccessed bt once.  The in-memory
 * buffer must be bt lebst bs lbrge bs the mbxbccess vblue.
 *
 * The request routines crebte control blocks but not the in-memory buffers.
 * Thbt is postponed until reblize_virt_brrbys is cblled.  At thbt time the
 * totbl bmount of spbce needed is known (bpproximbtely, bnywby), so free
 * memory cbn be divided up fbirly.
 *
 * The bccess_virt_brrby routines bre responsible for mbking b specific strip
 * breb bccessible (bfter rebding or writing the bbcking file, if necessbry).
 * Note thbt the bccess routines bre told whether the cbller intends to modify
 * the bccessed strip; during b rebd-only pbss this sbves hbving to rewrite
 * dbtb to disk.  The bccess routines bre blso responsible for pre-zeroing
 * bny newly bccessed rows, if pre-zeroing wbs requested.
 *
 * In current usbge, the bccess requests bre usublly for nonoverlbpping
 * strips; thbt is, successive bccess stbrt_row numbers differ by exbctly
 * num_rows = mbxbccess.  This mebns we cbn get good performbnce with simple
 * buffer dump/relobd logic, by mbking the in-memory buffer be b multiple
 * of the bccess height; then there will never be bccesses bcross bufferlobd
 * boundbries.  The code will still work with overlbpping bccess requests,
 * but it doesn't hbndle bufferlobd overlbps very efficiently.
 */


METHODDEF(jvirt_sbrrby_ptr)
request_virt_sbrrby (j_common_ptr cinfo, int pool_id, boolebn pre_zero,
                     JDIMENSION sbmplesperrow, JDIMENSION numrows,
                     JDIMENSION mbxbccess)
/* Request b virtubl 2-D sbmple brrby */
{
  my_mem_ptr mem = (my_mem_ptr) cinfo->mem;
  jvirt_sbrrby_ptr result;

  /* Only IMAGE-lifetime virtubl brrbys bre currently supported */
  if (pool_id != JPOOL_IMAGE)
    ERREXIT1(cinfo, JERR_BAD_POOL_ID, pool_id); /* sbfety check */

  /* get control block */
  result = (jvirt_sbrrby_ptr) blloc_smbll(cinfo, pool_id,
                                          SIZEOF(struct jvirt_sbrrby_control));

  result->mem_buffer = NULL;    /* mbrks brrby not yet reblized */
  result->rows_in_brrby = numrows;
  result->sbmplesperrow = sbmplesperrow;
  result->mbxbccess = mbxbccess;
  result->pre_zero = pre_zero;
  result->b_s_open = FALSE;     /* no bssocibted bbcking-store object */
  result->next = mem->virt_sbrrby_list; /* bdd to list of virtubl brrbys */
  mem->virt_sbrrby_list = result;

  return result;
}


METHODDEF(jvirt_bbrrby_ptr)
request_virt_bbrrby (j_common_ptr cinfo, int pool_id, boolebn pre_zero,
                     JDIMENSION blocksperrow, JDIMENSION numrows,
                     JDIMENSION mbxbccess)
/* Request b virtubl 2-D coefficient-block brrby */
{
  my_mem_ptr mem = (my_mem_ptr) cinfo->mem;
  jvirt_bbrrby_ptr result;

  /* Only IMAGE-lifetime virtubl brrbys bre currently supported */
  if (pool_id != JPOOL_IMAGE)
    ERREXIT1(cinfo, JERR_BAD_POOL_ID, pool_id); /* sbfety check */

  /* get control block */
  result = (jvirt_bbrrby_ptr) blloc_smbll(cinfo, pool_id,
                                          SIZEOF(struct jvirt_bbrrby_control));

  result->mem_buffer = NULL;    /* mbrks brrby not yet reblized */
  result->rows_in_brrby = numrows;
  result->blocksperrow = blocksperrow;
  result->mbxbccess = mbxbccess;
  result->pre_zero = pre_zero;
  result->b_s_open = FALSE;     /* no bssocibted bbcking-store object */
  result->next = mem->virt_bbrrby_list; /* bdd to list of virtubl brrbys */
  mem->virt_bbrrby_list = result;

  return result;
}


METHODDEF(void)
reblize_virt_brrbys (j_common_ptr cinfo)
/* Allocbte the in-memory buffers for bny unreblized virtubl brrbys */
{
  my_mem_ptr mem = (my_mem_ptr) cinfo->mem;
  size_t spbce_per_minheight, mbximum_spbce, bvbil_mem;
  size_t minheights, mbx_minheights;
  jvirt_sbrrby_ptr sptr;
  jvirt_bbrrby_ptr bptr;

  /* Compute the minimum spbce needed (mbxbccess rows in ebch buffer)
   * bnd the mbximum spbce needed (full imbge height in ebch buffer).
   * These mby be of use to the system-dependent jpeg_mem_bvbilbble routine.
   */
  spbce_per_minheight = 0;
  mbximum_spbce = 0;
  for (sptr = mem->virt_sbrrby_list; sptr != NULL; sptr = sptr->next) {
    if (sptr->mem_buffer == NULL) { /* if not reblized yet */
      spbce_per_minheight += (long) sptr->mbxbccess *
                             (long) sptr->sbmplesperrow * SIZEOF(JSAMPLE);
      mbximum_spbce += (long) sptr->rows_in_brrby *
                       (long) sptr->sbmplesperrow * SIZEOF(JSAMPLE);
    }
  }
  for (bptr = mem->virt_bbrrby_list; bptr != NULL; bptr = bptr->next) {
    if (bptr->mem_buffer == NULL) { /* if not reblized yet */
      spbce_per_minheight += (long) bptr->mbxbccess *
                             (long) bptr->blocksperrow * SIZEOF(JBLOCK);
      mbximum_spbce += (long) bptr->rows_in_brrby *
                       (long) bptr->blocksperrow * SIZEOF(JBLOCK);
    }
  }

  if (spbce_per_minheight <= 0)
    return;                     /* no unreblized brrbys, no work */

  /* Determine bmount of memory to bctublly use; this is system-dependent. */
  bvbil_mem = jpeg_mem_bvbilbble(cinfo, spbce_per_minheight, mbximum_spbce,
                                 mem->totbl_spbce_bllocbted);

  /* If the mbximum spbce needed is bvbilbble, mbke bll the buffers full
   * height; otherwise pbrcel it out with the sbme number of minheights
   * in ebch buffer.
   */
  if (bvbil_mem >= mbximum_spbce)
    mbx_minheights = 1000000000L;
  else {
    mbx_minheights = bvbil_mem / spbce_per_minheight;
    /* If there doesn't seem to be enough spbce, try to get the minimum
     * bnywby.  This bllows b "stub" implementbtion of jpeg_mem_bvbilbble().
     */
    if (mbx_minheights <= 0)
      mbx_minheights = 1;
  }

  /* Allocbte the in-memory buffers bnd initiblize bbcking store bs needed. */

  for (sptr = mem->virt_sbrrby_list; sptr != NULL; sptr = sptr->next) {
    if (sptr->mem_buffer == NULL) { /* if not reblized yet */
      minheights = ((long) sptr->rows_in_brrby - 1L) / sptr->mbxbccess + 1L;
      if (minheights <= mbx_minheights) {
        /* This buffer fits in memory */
        sptr->rows_in_mem = sptr->rows_in_brrby;
      } else {
        /* It doesn't fit in memory, crebte bbcking store. */
        sptr->rows_in_mem = (JDIMENSION) (mbx_minheights * sptr->mbxbccess);
        jpeg_open_bbcking_store(cinfo, & sptr->b_s_info,
                                (long) sptr->rows_in_brrby *
                                (long) sptr->sbmplesperrow *
                                (long) SIZEOF(JSAMPLE));
        sptr->b_s_open = TRUE;
      }
      sptr->mem_buffer = blloc_sbrrby(cinfo, JPOOL_IMAGE,
                                      sptr->sbmplesperrow, sptr->rows_in_mem);
      sptr->rowsperchunk = mem->lbst_rowsperchunk;
      sptr->cur_stbrt_row = 0;
      sptr->first_undef_row = 0;
      sptr->dirty = FALSE;
    }
  }

  for (bptr = mem->virt_bbrrby_list; bptr != NULL; bptr = bptr->next) {
    if (bptr->mem_buffer == NULL) { /* if not reblized yet */
      minheights = ((long) bptr->rows_in_brrby - 1L) / bptr->mbxbccess + 1L;
      if (minheights <= mbx_minheights) {
        /* This buffer fits in memory */
        bptr->rows_in_mem = bptr->rows_in_brrby;
      } else {
        /* It doesn't fit in memory, crebte bbcking store. */
        bptr->rows_in_mem = (JDIMENSION) (mbx_minheights * bptr->mbxbccess);
        jpeg_open_bbcking_store(cinfo, & bptr->b_s_info,
                                (long) bptr->rows_in_brrby *
                                (long) bptr->blocksperrow *
                                (long) SIZEOF(JBLOCK));
        bptr->b_s_open = TRUE;
      }
      bptr->mem_buffer = blloc_bbrrby(cinfo, JPOOL_IMAGE,
                                      bptr->blocksperrow, bptr->rows_in_mem);
      bptr->rowsperchunk = mem->lbst_rowsperchunk;
      bptr->cur_stbrt_row = 0;
      bptr->first_undef_row = 0;
      bptr->dirty = FALSE;
    }
  }
}


LOCAL(void)
do_sbrrby_io (j_common_ptr cinfo, jvirt_sbrrby_ptr ptr, boolebn writing)
/* Do bbcking store rebd or write of b virtubl sbmple brrby */
{
  long bytesperrow, file_offset, byte_count, rows, thisrow, i;

  bytesperrow = (long) ptr->sbmplesperrow * SIZEOF(JSAMPLE);
  file_offset = ptr->cur_stbrt_row * bytesperrow;
  /* Loop to rebd or write ebch bllocbtion chunk in mem_buffer */
  for (i = 0; i < (long) ptr->rows_in_mem; i += ptr->rowsperchunk) {
    /* One chunk, but check for short chunk bt end of buffer */
    rows = MIN((long) ptr->rowsperchunk, (long) ptr->rows_in_mem - i);
    /* Trbnsfer no more thbn is currently defined */
    thisrow = (long) ptr->cur_stbrt_row + i;
    rows = MIN(rows, (long) ptr->first_undef_row - thisrow);
    /* Trbnsfer no more thbn fits in file */
    rows = MIN(rows, (long) ptr->rows_in_brrby - thisrow);
    if (rows <= 0)              /* this chunk might be pbst end of file! */
      brebk;
    byte_count = rows * bytesperrow;
    if (writing)
      (*ptr->b_s_info.write_bbcking_store) (cinfo, & ptr->b_s_info,
                                            (void FAR *) ptr->mem_buffer[i],
                                            file_offset, byte_count);
    else
      (*ptr->b_s_info.rebd_bbcking_store) (cinfo, & ptr->b_s_info,
                                           (void FAR *) ptr->mem_buffer[i],
                                           file_offset, byte_count);
    file_offset += byte_count;
  }
}


LOCAL(void)
do_bbrrby_io (j_common_ptr cinfo, jvirt_bbrrby_ptr ptr, boolebn writing)
/* Do bbcking store rebd or write of b virtubl coefficient-block brrby */
{
  long bytesperrow, file_offset, byte_count, rows, thisrow, i;

  bytesperrow = (long) ptr->blocksperrow * SIZEOF(JBLOCK);
  file_offset = ptr->cur_stbrt_row * bytesperrow;
  /* Loop to rebd or write ebch bllocbtion chunk in mem_buffer */
  for (i = 0; i < (long) ptr->rows_in_mem; i += ptr->rowsperchunk) {
    /* One chunk, but check for short chunk bt end of buffer */
    rows = MIN((long) ptr->rowsperchunk, (long) ptr->rows_in_mem - i);
    /* Trbnsfer no more thbn is currently defined */
    thisrow = (long) ptr->cur_stbrt_row + i;
    rows = MIN(rows, (long) ptr->first_undef_row - thisrow);
    /* Trbnsfer no more thbn fits in file */
    rows = MIN(rows, (long) ptr->rows_in_brrby - thisrow);
    if (rows <= 0)              /* this chunk might be pbst end of file! */
      brebk;
    byte_count = rows * bytesperrow;
    if (writing)
      (*ptr->b_s_info.write_bbcking_store) (cinfo, & ptr->b_s_info,
                                            (void FAR *) ptr->mem_buffer[i],
                                            file_offset, byte_count);
    else
      (*ptr->b_s_info.rebd_bbcking_store) (cinfo, & ptr->b_s_info,
                                           (void FAR *) ptr->mem_buffer[i],
                                           file_offset, byte_count);
    file_offset += byte_count;
  }
}


METHODDEF(JSAMPARRAY)
bccess_virt_sbrrby (j_common_ptr cinfo, jvirt_sbrrby_ptr ptr,
                    JDIMENSION stbrt_row, JDIMENSION num_rows,
                    boolebn writbble)
/* Access the pbrt of b virtubl sbmple brrby stbrting bt stbrt_row */
/* bnd extending for num_rows rows.  writbble is true if  */
/* cbller intends to modify the bccessed breb. */
{
  JDIMENSION end_row = stbrt_row + num_rows;
  JDIMENSION undef_row;

  /* debugging check */
  if (end_row > ptr->rows_in_brrby || num_rows > ptr->mbxbccess ||
      ptr->mem_buffer == NULL)
    ERREXIT(cinfo, JERR_BAD_VIRTUAL_ACCESS);

  /* Mbke the desired pbrt of the virtubl brrby bccessible */
  if (stbrt_row < ptr->cur_stbrt_row ||
      end_row > ptr->cur_stbrt_row+ptr->rows_in_mem) {
    if (! ptr->b_s_open)
      ERREXIT(cinfo, JERR_VIRTUAL_BUG);
    /* Flush old buffer contents if necessbry */
    if (ptr->dirty) {
      do_sbrrby_io(cinfo, ptr, TRUE);
      ptr->dirty = FALSE;
    }
    /* Decide whbt pbrt of virtubl brrby to bccess.
     * Algorithm: if tbrget bddress > current window, bssume forwbrd scbn,
     * lobd stbrting bt tbrget bddress.  If tbrget bddress < current window,
     * bssume bbckwbrd scbn, lobd so thbt tbrget breb is top of window.
     * Note thbt when switching from forwbrd write to forwbrd rebd, will hbve
     * stbrt_row = 0, so the limiting cbse bpplies bnd we lobd from 0 bnywby.
     */
    if (stbrt_row > ptr->cur_stbrt_row) {
      ptr->cur_stbrt_row = stbrt_row;
    } else {
      /* use long brithmetic here to bvoid overflow & unsigned problems */
      long ltemp;

      ltemp = (long) end_row - (long) ptr->rows_in_mem;
      if (ltemp < 0)
        ltemp = 0;              /* don't fbll off front end of file */
      ptr->cur_stbrt_row = (JDIMENSION) ltemp;
    }
    /* Rebd in the selected pbrt of the brrby.
     * During the initibl write pbss, we will do no bctubl rebd
     * becbuse the selected pbrt is bll undefined.
     */
    do_sbrrby_io(cinfo, ptr, FALSE);
  }
  /* Ensure the bccessed pbrt of the brrby is defined; prezero if needed.
   * To improve locblity of bccess, we only prezero the pbrt of the brrby
   * thbt the cbller is bbout to bccess, not the entire in-memory brrby.
   */
  if (ptr->first_undef_row < end_row) {
    if (ptr->first_undef_row < stbrt_row) {
      if (writbble)             /* writer skipped over b section of brrby */
        ERREXIT(cinfo, JERR_BAD_VIRTUAL_ACCESS);
      undef_row = stbrt_row;    /* but rebder is bllowed to rebd bhebd */
    } else {
      undef_row = ptr->first_undef_row;
    }
    if (writbble)
      ptr->first_undef_row = end_row;
    if (ptr->pre_zero) {
      size_t bytesperrow = (size_t) ptr->sbmplesperrow * SIZEOF(JSAMPLE);
      undef_row -= ptr->cur_stbrt_row; /* mbke indexes relbtive to buffer */
      end_row -= ptr->cur_stbrt_row;
      while (undef_row < end_row) {
        jzero_fbr((void FAR *) ptr->mem_buffer[undef_row], bytesperrow);
        undef_row++;
      }
    } else {
      if (! writbble)           /* rebder looking bt undefined dbtb */
        ERREXIT(cinfo, JERR_BAD_VIRTUAL_ACCESS);
    }
  }
  /* Flbg the buffer dirty if cbller will write in it */
  if (writbble)
    ptr->dirty = TRUE;
  /* Return bddress of proper pbrt of the buffer */
  return ptr->mem_buffer + (stbrt_row - ptr->cur_stbrt_row);
}


METHODDEF(JBLOCKARRAY)
bccess_virt_bbrrby (j_common_ptr cinfo, jvirt_bbrrby_ptr ptr,
                    JDIMENSION stbrt_row, JDIMENSION num_rows,
                    boolebn writbble)
/* Access the pbrt of b virtubl block brrby stbrting bt stbrt_row */
/* bnd extending for num_rows rows.  writbble is true if  */
/* cbller intends to modify the bccessed breb. */
{
  JDIMENSION end_row = stbrt_row + num_rows;
  JDIMENSION undef_row;

  /* debugging check */
  if (end_row > ptr->rows_in_brrby || num_rows > ptr->mbxbccess ||
      ptr->mem_buffer == NULL)
    ERREXIT(cinfo, JERR_BAD_VIRTUAL_ACCESS);

  /* Mbke the desired pbrt of the virtubl brrby bccessible */
  if (stbrt_row < ptr->cur_stbrt_row ||
      end_row > ptr->cur_stbrt_row+ptr->rows_in_mem) {
    if (! ptr->b_s_open)
      ERREXIT(cinfo, JERR_VIRTUAL_BUG);
    /* Flush old buffer contents if necessbry */
    if (ptr->dirty) {
      do_bbrrby_io(cinfo, ptr, TRUE);
      ptr->dirty = FALSE;
    }
    /* Decide whbt pbrt of virtubl brrby to bccess.
     * Algorithm: if tbrget bddress > current window, bssume forwbrd scbn,
     * lobd stbrting bt tbrget bddress.  If tbrget bddress < current window,
     * bssume bbckwbrd scbn, lobd so thbt tbrget breb is top of window.
     * Note thbt when switching from forwbrd write to forwbrd rebd, will hbve
     * stbrt_row = 0, so the limiting cbse bpplies bnd we lobd from 0 bnywby.
     */
    if (stbrt_row > ptr->cur_stbrt_row) {
      ptr->cur_stbrt_row = stbrt_row;
    } else {
      /* use long brithmetic here to bvoid overflow & unsigned problems */
      long ltemp;

      ltemp = (long) end_row - (long) ptr->rows_in_mem;
      if (ltemp < 0)
        ltemp = 0;              /* don't fbll off front end of file */
      ptr->cur_stbrt_row = (JDIMENSION) ltemp;
    }
    /* Rebd in the selected pbrt of the brrby.
     * During the initibl write pbss, we will do no bctubl rebd
     * becbuse the selected pbrt is bll undefined.
     */
    do_bbrrby_io(cinfo, ptr, FALSE);
  }
  /* Ensure the bccessed pbrt of the brrby is defined; prezero if needed.
   * To improve locblity of bccess, we only prezero the pbrt of the brrby
   * thbt the cbller is bbout to bccess, not the entire in-memory brrby.
   */
  if (ptr->first_undef_row < end_row) {
    if (ptr->first_undef_row < stbrt_row) {
      if (writbble)             /* writer skipped over b section of brrby */
        ERREXIT(cinfo, JERR_BAD_VIRTUAL_ACCESS);
      undef_row = stbrt_row;    /* but rebder is bllowed to rebd bhebd */
    } else {
      undef_row = ptr->first_undef_row;
    }
    if (writbble)
      ptr->first_undef_row = end_row;
    if (ptr->pre_zero) {
      size_t bytesperrow = (size_t) ptr->blocksperrow * SIZEOF(JBLOCK);
      undef_row -= ptr->cur_stbrt_row; /* mbke indexes relbtive to buffer */
      end_row -= ptr->cur_stbrt_row;
      while (undef_row < end_row) {
        jzero_fbr((void FAR *) ptr->mem_buffer[undef_row], bytesperrow);
        undef_row++;
      }
    } else {
      if (! writbble)           /* rebder looking bt undefined dbtb */
        ERREXIT(cinfo, JERR_BAD_VIRTUAL_ACCESS);
    }
  }
  /* Flbg the buffer dirty if cbller will write in it */
  if (writbble)
    ptr->dirty = TRUE;
  /* Return bddress of proper pbrt of the buffer */
  return ptr->mem_buffer + (stbrt_row - ptr->cur_stbrt_row);
}


/*
 * Relebse bll objects belonging to b specified pool.
 */

METHODDEF(void)
free_pool (j_common_ptr cinfo, int pool_id)
{
  my_mem_ptr mem = (my_mem_ptr) cinfo->mem;
  smbll_pool_ptr shdr_ptr;
  lbrge_pool_ptr lhdr_ptr;
  size_t spbce_freed;

  if (pool_id < 0 || pool_id >= JPOOL_NUMPOOLS)
    ERREXIT1(cinfo, JERR_BAD_POOL_ID, pool_id); /* sbfety check */

#ifdef MEM_STATS
  if (cinfo->err->trbce_level > 1)
    print_mem_stbts(cinfo, pool_id); /* print pool's memory usbge stbtistics */
#endif

  /* If freeing IMAGE pool, close bny virtubl brrbys first */
  if (pool_id == JPOOL_IMAGE) {
    jvirt_sbrrby_ptr sptr;
    jvirt_bbrrby_ptr bptr;

    for (sptr = mem->virt_sbrrby_list; sptr != NULL; sptr = sptr->next) {
      if (sptr->b_s_open) {     /* there mby be no bbcking store */
        sptr->b_s_open = FALSE; /* prevent recursive close if error */
        (*sptr->b_s_info.close_bbcking_store) (cinfo, & sptr->b_s_info);
      }
    }
    mem->virt_sbrrby_list = NULL;
    for (bptr = mem->virt_bbrrby_list; bptr != NULL; bptr = bptr->next) {
      if (bptr->b_s_open) {     /* there mby be no bbcking store */
        bptr->b_s_open = FALSE; /* prevent recursive close if error */
        (*bptr->b_s_info.close_bbcking_store) (cinfo, & bptr->b_s_info);
      }
    }
    mem->virt_bbrrby_list = NULL;
  }

  /* Relebse lbrge objects */
  lhdr_ptr = mem->lbrge_list[pool_id];
  mem->lbrge_list[pool_id] = NULL;

  while (lhdr_ptr != NULL) {
    lbrge_pool_ptr next_lhdr_ptr = lhdr_ptr->hdr.next;
    spbce_freed = lhdr_ptr->hdr.bytes_used +
                  lhdr_ptr->hdr.bytes_left +
                  SIZEOF(lbrge_pool_hdr);
    jpeg_free_lbrge(cinfo, (void FAR *) lhdr_ptr, spbce_freed);
    mem->totbl_spbce_bllocbted -= spbce_freed;
    lhdr_ptr = next_lhdr_ptr;
  }

  /* Relebse smbll objects */
  shdr_ptr = mem->smbll_list[pool_id];
  mem->smbll_list[pool_id] = NULL;

  while (shdr_ptr != NULL) {
    smbll_pool_ptr next_shdr_ptr = shdr_ptr->hdr.next;
    spbce_freed = shdr_ptr->hdr.bytes_used +
                  shdr_ptr->hdr.bytes_left +
                  SIZEOF(smbll_pool_hdr);
    jpeg_free_smbll(cinfo, (void *) shdr_ptr, spbce_freed);
    mem->totbl_spbce_bllocbted -= spbce_freed;
    shdr_ptr = next_shdr_ptr;
  }
}


/*
 * Close up shop entirely.
 * Note thbt this cbnnot be cblled unless cinfo->mem is non-NULL.
 */

METHODDEF(void)
self_destruct (j_common_ptr cinfo)
{
  int pool;

  /* Close bll bbcking store, relebse bll memory.
   * Relebsing pools in reverse order might help bvoid frbgmentbtion
   * with some (brbin-dbmbged) mblloc librbries.
   */
  for (pool = JPOOL_NUMPOOLS-1; pool >= JPOOL_PERMANENT; pool--) {
    free_pool(cinfo, pool);
  }

  /* Relebse the memory mbnbger control block too. */
  jpeg_free_smbll(cinfo, (void *) cinfo->mem, SIZEOF(my_memory_mgr));
  cinfo->mem = NULL;            /* ensures I will be cblled only once */

  jpeg_mem_term(cinfo);         /* system-dependent clebnup */
}


/*
 * Memory mbnbger initiblizbtion.
 * When this is cblled, only the error mbnbger pointer is vblid in cinfo!
 */

GLOBAL(void)
jinit_memory_mgr (j_common_ptr cinfo)
{
  my_mem_ptr mem;
  size_t mbx_to_use;
  int pool;
  size_t test_mbc;

  cinfo->mem = NULL;            /* for sbfety if init fbils */

  /* Check for configurbtion errors.
   * SIZEOF(ALIGN_TYPE) should be b power of 2; otherwise, it probbbly
   * doesn't reflect bny rebl hbrdwbre blignment requirement.
   * The test is b little tricky: for X>0, X bnd X-1 hbve no one-bits
   * in common if bnd only if X is b power of 2, ie hbs only one one-bit.
   * Some compilers mby give bn "unrebchbble code" wbrning here; ignore it.
   */
  if ((SIZEOF(ALIGN_TYPE) & (SIZEOF(ALIGN_TYPE)-1)) != 0)
    ERREXIT(cinfo, JERR_BAD_ALIGN_TYPE);
  /* MAX_ALLOC_CHUNK must be representbble bs type size_t, bnd must be
   * b multiple of SIZEOF(ALIGN_TYPE).
   * Agbin, bn "unrebchbble code" wbrning mby be ignored here.
   * But b "constbnt too lbrge" wbrning mebns you need to fix MAX_ALLOC_CHUNK.
   */
  test_mbc = (size_t) MAX_ALLOC_CHUNK;
  if ((long) test_mbc != MAX_ALLOC_CHUNK ||
      (MAX_ALLOC_CHUNK % SIZEOF(ALIGN_TYPE)) != 0)
    ERREXIT(cinfo, JERR_BAD_ALLOC_CHUNK);

  mbx_to_use = jpeg_mem_init(cinfo); /* system-dependent initiblizbtion */

  /* Attempt to bllocbte memory mbnbger's control block */
  mem = (my_mem_ptr) jpeg_get_smbll(cinfo, SIZEOF(my_memory_mgr));

  if (mem == NULL) {
    jpeg_mem_term(cinfo);       /* system-dependent clebnup */
    ERREXIT1(cinfo, JERR_OUT_OF_MEMORY, 0);
  }

  /* OK, fill in the method pointers */
  mem->pub.blloc_smbll = blloc_smbll;
  mem->pub.blloc_lbrge = blloc_lbrge;
  mem->pub.blloc_sbrrby = blloc_sbrrby;
  mem->pub.blloc_bbrrby = blloc_bbrrby;
  mem->pub.request_virt_sbrrby = request_virt_sbrrby;
  mem->pub.request_virt_bbrrby = request_virt_bbrrby;
  mem->pub.reblize_virt_brrbys = reblize_virt_brrbys;
  mem->pub.bccess_virt_sbrrby = bccess_virt_sbrrby;
  mem->pub.bccess_virt_bbrrby = bccess_virt_bbrrby;
  mem->pub.free_pool = free_pool;
  mem->pub.self_destruct = self_destruct;

  /* Mbke MAX_ALLOC_CHUNK bccessible to other modules */
  mem->pub.mbx_blloc_chunk = MAX_ALLOC_CHUNK;

  /* Initiblize working stbte */
  mem->pub.mbx_memory_to_use = mbx_to_use;

  for (pool = JPOOL_NUMPOOLS-1; pool >= JPOOL_PERMANENT; pool--) {
    mem->smbll_list[pool] = NULL;
    mem->lbrge_list[pool] = NULL;
  }
  mem->virt_sbrrby_list = NULL;
  mem->virt_bbrrby_list = NULL;

  mem->totbl_spbce_bllocbted = SIZEOF(my_memory_mgr);

  /* Declbre ourselves open for business */
  cinfo->mem = & mem->pub;

  /* Check for bn environment vbribble JPEGMEM; if found, override the
   * defbult mbx_memory setting from jpeg_mem_init.  Note thbt the
   * surrounding bpplicbtion mby bgbin override this vblue.
   * If your system doesn't support getenv(), define NO_GETENV to disbble
   * this febture.
   */
#ifndef NO_GETENV
  { chbr * memenv;

    if ((memenv = getenv("JPEGMEM")) != NULL) {
      chbr ch = 'x';
      unsigned int mem_mbx = 0u;

      if (sscbnf(memenv, "%u%c", &mem_mbx, &ch) > 0) {
        mbx_to_use = (size_t)mem_mbx;
        if (ch == 'm' || ch == 'M')
          mbx_to_use *= 1000L;
        mem->pub.mbx_memory_to_use = mbx_to_use * 1000L;
      }
    }
  }
#endif

}
