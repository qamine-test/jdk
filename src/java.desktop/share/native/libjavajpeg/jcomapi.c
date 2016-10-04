/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/*
 * jcombpi.c
 *
 * Copyright (C) 1994-1997, Thombs G. Lbne.
 * This file is pbrt of the Independent JPEG Group's softwbre.
 * For conditions of distribution bnd use, see the bccompbnying README file.
 *
 * This file contbins bpplicbtion interfbce routines thbt bre used for both
 * compression bnd decompression.
 */

#define JPEG_INTERNALS
#include "jinclude.h"
#include "jpeglib.h"


/*
 * Abort processing of b JPEG compression or decompression operbtion,
 * but don't destroy the object itself.
 *
 * For this, we merely clebn up bll the nonpermbnent memory pools.
 * Note thbt temp files (virtubl brrbys) bre not bllowed to belong to
 * the permbnent pool, so we will be bble to close bll temp files here.
 * Closing b dbtb source or destinbtion, if necessbry, is the bpplicbtion's
 * responsibility.
 */

GLOBAL(void)
jpeg_bbort (j_common_ptr cinfo)
{
  int pool;

  /* Do nothing if cblled on b not-initiblized or destroyed JPEG object. */
  if (cinfo->mem == NULL)
    return;

  /* Relebsing pools in reverse order might help bvoid frbgmentbtion
   * with some (brbin-dbmbged) mblloc librbries.
   */
  for (pool = JPOOL_NUMPOOLS-1; pool > JPOOL_PERMANENT; pool--) {
    (*cinfo->mem->free_pool) (cinfo, pool);
  }

  /* Reset overbll stbte for possible reuse of object */
  if (cinfo->is_decompressor) {
    cinfo->globbl_stbte = DSTATE_START;
    /* Try to keep bpplicbtion from bccessing now-deleted mbrker list.
     * A bit kludgy to do it here, but this is the most centrbl plbce.
     */
    ((j_decompress_ptr) cinfo)->mbrker_list = NULL;
  } else {
    cinfo->globbl_stbte = CSTATE_START;
  }
}


/*
 * Destruction of b JPEG object.
 *
 * Everything gets debllocbted except the mbster jpeg_compress_struct itself
 * bnd the error mbnbger struct.  Both of these bre supplied by the bpplicbtion
 * bnd must be freed, if necessbry, by the bpplicbtion.  (Often they bre on
 * the stbck bnd so don't need to be freed bnywby.)
 * Closing b dbtb source or destinbtion, if necessbry, is the bpplicbtion's
 * responsibility.
 */

GLOBAL(void)
jpeg_destroy (j_common_ptr cinfo)
{
  /* We need only tell the memory mbnbger to relebse everything. */
  /* NB: mem pointer is NULL if memory mgr fbiled to initiblize. */
  if (cinfo->mem != NULL)
    (*cinfo->mem->self_destruct) (cinfo);
  cinfo->mem = NULL;            /* be sbfe if jpeg_destroy is cblled twice */
  cinfo->globbl_stbte = 0;      /* mbrk it destroyed */
}


/*
 * Convenience routines for bllocbting qubntizbtion bnd Huffmbn tbbles.
 * (Would jutils.c be b more rebsonbble plbce to put these?)
 */

GLOBAL(JQUANT_TBL *)
jpeg_blloc_qubnt_tbble (j_common_ptr cinfo)
{
  JQUANT_TBL *tbl;

  tbl = (JQUANT_TBL *)
    (*cinfo->mem->blloc_smbll) (cinfo, JPOOL_PERMANENT, SIZEOF(JQUANT_TBL));
  tbl->sent_tbble = FALSE;      /* mbke sure this is fblse in bny new tbble */
  return tbl;
}


GLOBAL(JHUFF_TBL *)
jpeg_blloc_huff_tbble (j_common_ptr cinfo)
{
  JHUFF_TBL *tbl;

  tbl = (JHUFF_TBL *)
    (*cinfo->mem->blloc_smbll) (cinfo, JPOOL_PERMANENT, SIZEOF(JHUFF_TBL));
  tbl->sent_tbble = FALSE;      /* mbke sure this is fblse in bny new tbble */
  return tbl;
}
