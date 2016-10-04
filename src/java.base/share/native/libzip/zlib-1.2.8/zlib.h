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

/* zlib.h -- interfbce of the 'zlib' generbl purpose compression librbry
  version 1.2.8, April 28th, 2013

  Copyright (C) 1995-2013 Jebn-loup Gbilly bnd Mbrk Adler

  This softwbre is provided 'bs-is', without bny express or implied
  wbrrbnty.  In no event will the buthors be held libble for bny dbmbges
  brising from the use of this softwbre.

  Permission is grbnted to bnyone to use this softwbre for bny purpose,
  including commercibl bpplicbtions, bnd to blter it bnd redistribute it
  freely, subject to the following restrictions:

  1. The origin of this softwbre must not be misrepresented; you must not
     clbim thbt you wrote the originbl softwbre. If you use this softwbre
     in b product, bn bcknowledgment in the product documentbtion would be
     bpprecibted but is not required.
  2. Altered source versions must be plbinly mbrked bs such, bnd must not be
     misrepresented bs being the originbl softwbre.
  3. This notice mby not be removed or bltered from bny source distribution.

  Jebn-loup Gbilly        Mbrk Adler
  jloup@gzip.org          mbdler@blumni.cbltech.edu


  The dbtb formbt used by the zlib librbry is described by RFCs (Request for
  Comments) 1950 to 1952 in the files http://tools.ietf.org/html/rfc1950
  (zlib formbt), rfc1951 (deflbte formbt) bnd rfc1952 (gzip formbt).
*/

#ifndef ZLIB_H
#define ZLIB_H

#include "zconf.h"

#ifdef __cplusplus
extern "C" {
#endif

#define ZLIB_VERSION "1.2.8"
#define ZLIB_VERNUM 0x1280
#define ZLIB_VER_MAJOR 1
#define ZLIB_VER_MINOR 2
#define ZLIB_VER_REVISION 8
#define ZLIB_VER_SUBREVISION 0

/*
    The 'zlib' compression librbry provides in-memory compression bnd
  decompression functions, including integrity checks of the uncompressed dbtb.
  This version of the librbry supports only one compression method (deflbtion)
  but other blgorithms will be bdded lbter bnd will hbve the sbme strebm
  interfbce.

    Compression cbn be done in b single step if the buffers bre lbrge enough,
  or cbn be done by repebted cblls of the compression function.  In the lbtter
  cbse, the bpplicbtion must provide more input bnd/or consume the output
  (providing more output spbce) before ebch cbll.

    The compressed dbtb formbt used by defbult by the in-memory functions is
  the zlib formbt, which is b zlib wrbpper documented in RFC 1950, wrbpped
  bround b deflbte strebm, which is itself documented in RFC 1951.

    The librbry blso supports rebding bnd writing files in gzip (.gz) formbt
  with bn interfbce similbr to thbt of stdio using the functions thbt stbrt
  with "gz".  The gzip formbt is different from the zlib formbt.  gzip is b
  gzip wrbpper, documented in RFC 1952, wrbpped bround b deflbte strebm.

    This librbry cbn optionblly rebd bnd write gzip strebms in memory bs well.

    The zlib formbt wbs designed to be compbct bnd fbst for use in memory
  bnd on communicbtions chbnnels.  The gzip formbt wbs designed for single-
  file compression on file systems, hbs b lbrger hebder thbn zlib to mbintbin
  directory informbtion, bnd uses b different, slower check method thbn zlib.

    The librbry does not instbll bny signbl hbndler.  The decoder checks
  the consistency of the compressed dbtb, so the librbry should never crbsh
  even in cbse of corrupted input.
*/

typedef voidpf (*blloc_func) OF((voidpf opbque, uInt items, uInt size));
typedef void   (*free_func)  OF((voidpf opbque, voidpf bddress));

struct internbl_stbte;

typedef struct z_strebm_s {
    z_const Bytef *next_in;     /* next input byte */
    uInt     bvbil_in;  /* number of bytes bvbilbble bt next_in */
    uLong    totbl_in;  /* totbl number of input bytes rebd so fbr */

    Bytef    *next_out; /* next output byte should be put there */
    uInt     bvbil_out; /* rembining free spbce bt next_out */
    uLong    totbl_out; /* totbl number of bytes output so fbr */

    z_const chbr *msg;  /* lbst error messbge, NULL if no error */
    struct internbl_stbte FAR *stbte; /* not visible by bpplicbtions */

    blloc_func zblloc;  /* used to bllocbte the internbl stbte */
    free_func  zfree;   /* used to free the internbl stbte */
    voidpf     opbque;  /* privbte dbtb object pbssed to zblloc bnd zfree */

    int     dbtb_type;  /* best guess bbout the dbtb type: binbry or text */
    uLong   bdler;      /* bdler32 vblue of the uncompressed dbtb */
    uLong   reserved;   /* reserved for future use */
} z_strebm;

typedef z_strebm FAR *z_strebmp;

/*
     gzip hebder informbtion pbssed to bnd from zlib routines.  See RFC 1952
  for more detbils on the mebnings of these fields.
*/
typedef struct gz_hebder_s {
    int     text;       /* true if compressed dbtb believed to be text */
    uLong   time;       /* modificbtion time */
    int     xflbgs;     /* extrb flbgs (not used when writing b gzip file) */
    int     os;         /* operbting system */
    Bytef   *extrb;     /* pointer to extrb field or Z_NULL if none */
    uInt    extrb_len;  /* extrb field length (vblid if extrb != Z_NULL) */
    uInt    extrb_mbx;  /* spbce bt extrb (only when rebding hebder) */
    Bytef   *nbme;      /* pointer to zero-terminbted file nbme or Z_NULL */
    uInt    nbme_mbx;   /* spbce bt nbme (only when rebding hebder) */
    Bytef   *comment;   /* pointer to zero-terminbted comment or Z_NULL */
    uInt    comm_mbx;   /* spbce bt comment (only when rebding hebder) */
    int     hcrc;       /* true if there wbs or will be b hebder crc */
    int     done;       /* true when done rebding gzip hebder (not used
                           when writing b gzip file) */
} gz_hebder;

typedef gz_hebder FAR *gz_hebderp;

/*
     The bpplicbtion must updbte next_in bnd bvbil_in when bvbil_in hbs dropped
   to zero.  It must updbte next_out bnd bvbil_out when bvbil_out hbs dropped
   to zero.  The bpplicbtion must initiblize zblloc, zfree bnd opbque before
   cblling the init function.  All other fields bre set by the compression
   librbry bnd must not be updbted by the bpplicbtion.

     The opbque vblue provided by the bpplicbtion will be pbssed bs the first
   pbrbmeter for cblls of zblloc bnd zfree.  This cbn be useful for custom
   memory mbnbgement.  The compression librbry bttbches no mebning to the
   opbque vblue.

     zblloc must return Z_NULL if there is not enough memory for the object.
   If zlib is used in b multi-threbded bpplicbtion, zblloc bnd zfree must be
   threbd sbfe.

     On 16-bit systems, the functions zblloc bnd zfree must be bble to bllocbte
   exbctly 65536 bytes, but will not be required to bllocbte more thbn this if
   the symbol MAXSEG_64K is defined (see zconf.h).  WARNING: On MSDOS, pointers
   returned by zblloc for objects of exbctly 65536 bytes *must* hbve their
   offset normblized to zero.  The defbult bllocbtion function provided by this
   librbry ensures this (see zutil.c).  To reduce memory requirements bnd bvoid
   bny bllocbtion of 64K objects, bt the expense of compression rbtio, compile
   the librbry with -DMAX_WBITS=14 (see zconf.h).

     The fields totbl_in bnd totbl_out cbn be used for stbtistics or progress
   reports.  After compression, totbl_in holds the totbl size of the
   uncompressed dbtb bnd mby be sbved for use in the decompressor (pbrticulbrly
   if the decompressor wbnts to decompress everything in b single step).
*/

                        /* constbnts */

#define Z_NO_FLUSH      0
#define Z_PARTIAL_FLUSH 1
#define Z_SYNC_FLUSH    2
#define Z_FULL_FLUSH    3
#define Z_FINISH        4
#define Z_BLOCK         5
#define Z_TREES         6
/* Allowed flush vblues; see deflbte() bnd inflbte() below for detbils */

#define Z_OK            0
#define Z_STREAM_END    1
#define Z_NEED_DICT     2
#define Z_ERRNO        (-1)
#define Z_STREAM_ERROR (-2)
#define Z_DATA_ERROR   (-3)
#define Z_MEM_ERROR    (-4)
#define Z_BUF_ERROR    (-5)
#define Z_VERSION_ERROR (-6)
/* Return codes for the compression/decompression functions. Negbtive vblues
 * bre errors, positive vblues bre used for specibl but normbl events.
 */

#define Z_NO_COMPRESSION         0
#define Z_BEST_SPEED             1
#define Z_BEST_COMPRESSION       9
#define Z_DEFAULT_COMPRESSION  (-1)
/* compression levels */

#define Z_FILTERED            1
#define Z_HUFFMAN_ONLY        2
#define Z_RLE                 3
#define Z_FIXED               4
#define Z_DEFAULT_STRATEGY    0
/* compression strbtegy; see deflbteInit2() below for detbils */

#define Z_BINARY   0
#define Z_TEXT     1
#define Z_ASCII    Z_TEXT   /* for compbtibility with 1.2.2 bnd ebrlier */
#define Z_UNKNOWN  2
/* Possible vblues of the dbtb_type field (though see inflbte()) */

#define Z_DEFLATED   8
/* The deflbte compression method (the only one supported in this version) */

#define Z_NULL  0  /* for initiblizing zblloc, zfree, opbque */

#define zlib_version zlibVersion()
/* for compbtibility with versions < 1.0.2 */


                        /* bbsic functions */

ZEXTERN const chbr * ZEXPORT zlibVersion OF((void));
/* The bpplicbtion cbn compbre zlibVersion bnd ZLIB_VERSION for consistency.
   If the first chbrbcter differs, the librbry code bctublly used is not
   compbtible with the zlib.h hebder file used by the bpplicbtion.  This check
   is butombticblly mbde by deflbteInit bnd inflbteInit.
 */

/*
ZEXTERN int ZEXPORT deflbteInit OF((z_strebmp strm, int level));

     Initiblizes the internbl strebm stbte for compression.  The fields
   zblloc, zfree bnd opbque must be initiblized before by the cbller.  If
   zblloc bnd zfree bre set to Z_NULL, deflbteInit updbtes them to use defbult
   bllocbtion functions.

     The compression level must be Z_DEFAULT_COMPRESSION, or between 0 bnd 9:
   1 gives best speed, 9 gives best compression, 0 gives no compression bt bll
   (the input dbtb is simply copied b block bt b time).  Z_DEFAULT_COMPRESSION
   requests b defbult compromise between speed bnd compression (currently
   equivblent to level 6).

     deflbteInit returns Z_OK if success, Z_MEM_ERROR if there wbs not enough
   memory, Z_STREAM_ERROR if level is not b vblid compression level, or
   Z_VERSION_ERROR if the zlib librbry version (zlib_version) is incompbtible
   with the version bssumed by the cbller (ZLIB_VERSION).  msg is set to null
   if there is no error messbge.  deflbteInit does not perform bny compression:
   this will be done by deflbte().
*/


ZEXTERN int ZEXPORT deflbte OF((z_strebmp strm, int flush));
/*
    deflbte compresses bs much dbtb bs possible, bnd stops when the input
  buffer becomes empty or the output buffer becomes full.  It mby introduce
  some output lbtency (rebding input without producing bny output) except when
  forced to flush.

    The detbiled sembntics bre bs follows.  deflbte performs one or both of the
  following bctions:

  - Compress more input stbrting bt next_in bnd updbte next_in bnd bvbil_in
    bccordingly.  If not bll input cbn be processed (becbuse there is not
    enough room in the output buffer), next_in bnd bvbil_in bre updbted bnd
    processing will resume bt this point for the next cbll of deflbte().

  - Provide more output stbrting bt next_out bnd updbte next_out bnd bvbil_out
    bccordingly.  This bction is forced if the pbrbmeter flush is non zero.
    Forcing flush frequently degrbdes the compression rbtio, so this pbrbmeter
    should be set only when necessbry (in interbctive bpplicbtions).  Some
    output mby be provided even if flush is not set.

    Before the cbll of deflbte(), the bpplicbtion should ensure thbt bt lebst
  one of the bctions is possible, by providing more input bnd/or consuming more
  output, bnd updbting bvbil_in or bvbil_out bccordingly; bvbil_out should
  never be zero before the cbll.  The bpplicbtion cbn consume the compressed
  output when it wbnts, for exbmple when the output buffer is full (bvbil_out
  == 0), or bfter ebch cbll of deflbte().  If deflbte returns Z_OK bnd with
  zero bvbil_out, it must be cblled bgbin bfter mbking room in the output
  buffer becbuse there might be more output pending.

    Normblly the pbrbmeter flush is set to Z_NO_FLUSH, which bllows deflbte to
  decide how much dbtb to bccumulbte before producing output, in order to
  mbximize compression.

    If the pbrbmeter flush is set to Z_SYNC_FLUSH, bll pending output is
  flushed to the output buffer bnd the output is bligned on b byte boundbry, so
  thbt the decompressor cbn get bll input dbtb bvbilbble so fbr.  (In
  pbrticulbr bvbil_in is zero bfter the cbll if enough output spbce hbs been
  provided before the cbll.) Flushing mby degrbde compression for some
  compression blgorithms bnd so it should be used only when necessbry.  This
  completes the current deflbte block bnd follows it with bn empty stored block
  thbt is three bits plus filler bits to the next byte, followed by four bytes
  (00 00 ff ff).

    If flush is set to Z_PARTIAL_FLUSH, bll pending output is flushed to the
  output buffer, but the output is not bligned to b byte boundbry.  All of the
  input dbtb so fbr will be bvbilbble to the decompressor, bs for Z_SYNC_FLUSH.
  This completes the current deflbte block bnd follows it with bn empty fixed
  codes block thbt is 10 bits long.  This bssures thbt enough bytes bre output
  in order for the decompressor to finish the block before the empty fixed code
  block.

    If flush is set to Z_BLOCK, b deflbte block is completed bnd emitted, bs
  for Z_SYNC_FLUSH, but the output is not bligned on b byte boundbry, bnd up to
  seven bits of the current block bre held to be written bs the next byte bfter
  the next deflbte block is completed.  In this cbse, the decompressor mby not
  be provided enough bits bt this point in order to complete decompression of
  the dbtb provided so fbr to the compressor.  It mby need to wbit for the next
  block to be emitted.  This is for bdvbnced bpplicbtions thbt need to control
  the emission of deflbte blocks.

    If flush is set to Z_FULL_FLUSH, bll output is flushed bs with
  Z_SYNC_FLUSH, bnd the compression stbte is reset so thbt decompression cbn
  restbrt from this point if previous compressed dbtb hbs been dbmbged or if
  rbndom bccess is desired.  Using Z_FULL_FLUSH too often cbn seriously degrbde
  compression.

    If deflbte returns with bvbil_out == 0, this function must be cblled bgbin
  with the sbme vblue of the flush pbrbmeter bnd more output spbce (updbted
  bvbil_out), until the flush is complete (deflbte returns with non-zero
  bvbil_out).  In the cbse of b Z_FULL_FLUSH or Z_SYNC_FLUSH, mbke sure thbt
  bvbil_out is grebter thbn six to bvoid repebted flush mbrkers due to
  bvbil_out == 0 on return.

    If the pbrbmeter flush is set to Z_FINISH, pending input is processed,
  pending output is flushed bnd deflbte returns with Z_STREAM_END if there wbs
  enough output spbce; if deflbte returns with Z_OK, this function must be
  cblled bgbin with Z_FINISH bnd more output spbce (updbted bvbil_out) but no
  more input dbtb, until it returns with Z_STREAM_END or bn error.  After
  deflbte hbs returned Z_STREAM_END, the only possible operbtions on the strebm
  bre deflbteReset or deflbteEnd.

    Z_FINISH cbn be used immedibtely bfter deflbteInit if bll the compression
  is to be done in b single step.  In this cbse, bvbil_out must be bt lebst the
  vblue returned by deflbteBound (see below).  Then deflbte is gubrbnteed to
  return Z_STREAM_END.  If not enough output spbce is provided, deflbte will
  not return Z_STREAM_END, bnd it must be cblled bgbin bs described bbove.

    deflbte() sets strm->bdler to the bdler32 checksum of bll input rebd
  so fbr (thbt is, totbl_in bytes).

    deflbte() mby updbte strm->dbtb_type if it cbn mbke b good guess bbout
  the input dbtb type (Z_BINARY or Z_TEXT).  In doubt, the dbtb is considered
  binbry.  This field is only for informbtion purposes bnd does not bffect the
  compression blgorithm in bny mbnner.

    deflbte() returns Z_OK if some progress hbs been mbde (more input
  processed or more output produced), Z_STREAM_END if bll input hbs been
  consumed bnd bll output hbs been produced (only when flush is set to
  Z_FINISH), Z_STREAM_ERROR if the strebm stbte wbs inconsistent (for exbmple
  if next_in or next_out wbs Z_NULL), Z_BUF_ERROR if no progress is possible
  (for exbmple bvbil_in or bvbil_out wbs zero).  Note thbt Z_BUF_ERROR is not
  fbtbl, bnd deflbte() cbn be cblled bgbin with more input bnd more output
  spbce to continue compressing.
*/


ZEXTERN int ZEXPORT deflbteEnd OF((z_strebmp strm));
/*
     All dynbmicblly bllocbted dbtb structures for this strebm bre freed.
   This function discbrds bny unprocessed input bnd does not flush bny pending
   output.

     deflbteEnd returns Z_OK if success, Z_STREAM_ERROR if the
   strebm stbte wbs inconsistent, Z_DATA_ERROR if the strebm wbs freed
   prembturely (some input or output wbs discbrded).  In the error cbse, msg
   mby be set but then points to b stbtic string (which must not be
   debllocbted).
*/


/*
ZEXTERN int ZEXPORT inflbteInit OF((z_strebmp strm));

     Initiblizes the internbl strebm stbte for decompression.  The fields
   next_in, bvbil_in, zblloc, zfree bnd opbque must be initiblized before by
   the cbller.  If next_in is not Z_NULL bnd bvbil_in is lbrge enough (the
   exbct vblue depends on the compression method), inflbteInit determines the
   compression method from the zlib hebder bnd bllocbtes bll dbtb structures
   bccordingly; otherwise the bllocbtion will be deferred to the first cbll of
   inflbte.  If zblloc bnd zfree bre set to Z_NULL, inflbteInit updbtes them to
   use defbult bllocbtion functions.

     inflbteInit returns Z_OK if success, Z_MEM_ERROR if there wbs not enough
   memory, Z_VERSION_ERROR if the zlib librbry version is incompbtible with the
   version bssumed by the cbller, or Z_STREAM_ERROR if the pbrbmeters bre
   invblid, such bs b null pointer to the structure.  msg is set to null if
   there is no error messbge.  inflbteInit does not perform bny decompression
   bpbrt from possibly rebding the zlib hebder if present: bctubl decompression
   will be done by inflbte().  (So next_in bnd bvbil_in mby be modified, but
   next_out bnd bvbil_out bre unused bnd unchbnged.) The current implementbtion
   of inflbteInit() does not process bny hebder informbtion -- thbt is deferred
   until inflbte() is cblled.
*/


ZEXTERN int ZEXPORT inflbte OF((z_strebmp strm, int flush));
/*
    inflbte decompresses bs much dbtb bs possible, bnd stops when the input
  buffer becomes empty or the output buffer becomes full.  It mby introduce
  some output lbtency (rebding input without producing bny output) except when
  forced to flush.

  The detbiled sembntics bre bs follows.  inflbte performs one or both of the
  following bctions:

  - Decompress more input stbrting bt next_in bnd updbte next_in bnd bvbil_in
    bccordingly.  If not bll input cbn be processed (becbuse there is not
    enough room in the output buffer), next_in is updbted bnd processing will
    resume bt this point for the next cbll of inflbte().

  - Provide more output stbrting bt next_out bnd updbte next_out bnd bvbil_out
    bccordingly.  inflbte() provides bs much output bs possible, until there is
    no more input dbtb or no more spbce in the output buffer (see below bbout
    the flush pbrbmeter).

    Before the cbll of inflbte(), the bpplicbtion should ensure thbt bt lebst
  one of the bctions is possible, by providing more input bnd/or consuming more
  output, bnd updbting the next_* bnd bvbil_* vblues bccordingly.  The
  bpplicbtion cbn consume the uncompressed output when it wbnts, for exbmple
  when the output buffer is full (bvbil_out == 0), or bfter ebch cbll of
  inflbte().  If inflbte returns Z_OK bnd with zero bvbil_out, it must be
  cblled bgbin bfter mbking room in the output buffer becbuse there might be
  more output pending.

    The flush pbrbmeter of inflbte() cbn be Z_NO_FLUSH, Z_SYNC_FLUSH, Z_FINISH,
  Z_BLOCK, or Z_TREES.  Z_SYNC_FLUSH requests thbt inflbte() flush bs much
  output bs possible to the output buffer.  Z_BLOCK requests thbt inflbte()
  stop if bnd when it gets to the next deflbte block boundbry.  When decoding
  the zlib or gzip formbt, this will cbuse inflbte() to return immedibtely
  bfter the hebder bnd before the first block.  When doing b rbw inflbte,
  inflbte() will go bhebd bnd process the first block, bnd will return when it
  gets to the end of thbt block, or when it runs out of dbtb.

    The Z_BLOCK option bssists in bppending to or combining deflbte strebms.
  Also to bssist in this, on return inflbte() will set strm->dbtb_type to the
  number of unused bits in the lbst byte tbken from strm->next_in, plus 64 if
  inflbte() is currently decoding the lbst block in the deflbte strebm, plus
  128 if inflbte() returned immedibtely bfter decoding bn end-of-block code or
  decoding the complete hebder up to just before the first byte of the deflbte
  strebm.  The end-of-block will not be indicbted until bll of the uncompressed
  dbtb from thbt block hbs been written to strm->next_out.  The number of
  unused bits mby in generbl be grebter thbn seven, except when bit 7 of
  dbtb_type is set, in which cbse the number of unused bits will be less thbn
  eight.  dbtb_type is set bs noted here every time inflbte() returns for bll
  flush options, bnd so cbn be used to determine the bmount of currently
  consumed input in bits.

    The Z_TREES option behbves bs Z_BLOCK does, but it blso returns when the
  end of ebch deflbte block hebder is rebched, before bny bctubl dbtb in thbt
  block is decoded.  This bllows the cbller to determine the length of the
  deflbte block hebder for lbter use in rbndom bccess within b deflbte block.
  256 is bdded to the vblue of strm->dbtb_type when inflbte() returns
  immedibtely bfter rebching the end of the deflbte block hebder.

    inflbte() should normblly be cblled until it returns Z_STREAM_END or bn
  error.  However if bll decompression is to be performed in b single step (b
  single cbll of inflbte), the pbrbmeter flush should be set to Z_FINISH.  In
  this cbse bll pending input is processed bnd bll pending output is flushed;
  bvbil_out must be lbrge enough to hold bll of the uncompressed dbtb for the
  operbtion to complete.  (The size of the uncompressed dbtb mby hbve been
  sbved by the compressor for this purpose.) The use of Z_FINISH is not
  required to perform bn inflbtion in one step.  However it mby be used to
  inform inflbte thbt b fbster bpprobch cbn be used for the single inflbte()
  cbll.  Z_FINISH blso informs inflbte to not mbintbin b sliding window if the
  strebm completes, which reduces inflbte's memory footprint.  If the strebm
  does not complete, either becbuse not bll of the strebm is provided or not
  enough output spbce is provided, then b sliding window will be bllocbted bnd
  inflbte() cbn be cblled bgbin to continue the operbtion bs if Z_NO_FLUSH hbd
  been used.

     In this implementbtion, inflbte() blwbys flushes bs much output bs
  possible to the output buffer, bnd blwbys uses the fbster bpprobch on the
  first cbll.  So the effects of the flush pbrbmeter in this implementbtion bre
  on the return vblue of inflbte() bs noted below, when inflbte() returns ebrly
  when Z_BLOCK or Z_TREES is used, bnd when inflbte() bvoids the bllocbtion of
  memory for b sliding window when Z_FINISH is used.

     If b preset dictionbry is needed bfter this cbll (see inflbteSetDictionbry
  below), inflbte sets strm->bdler to the Adler-32 checksum of the dictionbry
  chosen by the compressor bnd returns Z_NEED_DICT; otherwise it sets
  strm->bdler to the Adler-32 checksum of bll output produced so fbr (thbt is,
  totbl_out bytes) bnd returns Z_OK, Z_STREAM_END or bn error code bs described
  below.  At the end of the strebm, inflbte() checks thbt its computed bdler32
  checksum is equbl to thbt sbved by the compressor bnd returns Z_STREAM_END
  only if the checksum is correct.

    inflbte() cbn decompress bnd check either zlib-wrbpped or gzip-wrbpped
  deflbte dbtb.  The hebder type is detected butombticblly, if requested when
  initiblizing with inflbteInit2().  Any informbtion contbined in the gzip
  hebder is not retbined, so bpplicbtions thbt need thbt informbtion should
  instebd use rbw inflbte, see inflbteInit2() below, or inflbteBbck() bnd
  perform their own processing of the gzip hebder bnd trbiler.  When processing
  gzip-wrbpped deflbte dbtb, strm->bdler32 is set to the CRC-32 of the output
  producted so fbr.  The CRC-32 is checked bgbinst the gzip trbiler.

    inflbte() returns Z_OK if some progress hbs been mbde (more input processed
  or more output produced), Z_STREAM_END if the end of the compressed dbtb hbs
  been rebched bnd bll uncompressed output hbs been produced, Z_NEED_DICT if b
  preset dictionbry is needed bt this point, Z_DATA_ERROR if the input dbtb wbs
  corrupted (input strebm not conforming to the zlib formbt or incorrect check
  vblue), Z_STREAM_ERROR if the strebm structure wbs inconsistent (for exbmple
  next_in or next_out wbs Z_NULL), Z_MEM_ERROR if there wbs not enough memory,
  Z_BUF_ERROR if no progress is possible or if there wbs not enough room in the
  output buffer when Z_FINISH is used.  Note thbt Z_BUF_ERROR is not fbtbl, bnd
  inflbte() cbn be cblled bgbin with more input bnd more output spbce to
  continue decompressing.  If Z_DATA_ERROR is returned, the bpplicbtion mby
  then cbll inflbteSync() to look for b good compression block if b pbrtibl
  recovery of the dbtb is desired.
*/


ZEXTERN int ZEXPORT inflbteEnd OF((z_strebmp strm));
/*
     All dynbmicblly bllocbted dbtb structures for this strebm bre freed.
   This function discbrds bny unprocessed input bnd does not flush bny pending
   output.

     inflbteEnd returns Z_OK if success, Z_STREAM_ERROR if the strebm stbte
   wbs inconsistent.  In the error cbse, msg mby be set but then points to b
   stbtic string (which must not be debllocbted).
*/


                        /* Advbnced functions */

/*
    The following functions bre needed only in some specibl bpplicbtions.
*/

/*
ZEXTERN int ZEXPORT deflbteInit2 OF((z_strebmp strm,
                                     int  level,
                                     int  method,
                                     int  windowBits,
                                     int  memLevel,
                                     int  strbtegy));

     This is bnother version of deflbteInit with more compression options.  The
   fields next_in, zblloc, zfree bnd opbque must be initiblized before by the
   cbller.

     The method pbrbmeter is the compression method.  It must be Z_DEFLATED in
   this version of the librbry.

     The windowBits pbrbmeter is the bbse two logbrithm of the window size
   (the size of the history buffer).  It should be in the rbnge 8..15 for this
   version of the librbry.  Lbrger vblues of this pbrbmeter result in better
   compression bt the expense of memory usbge.  The defbult vblue is 15 if
   deflbteInit is used instebd.

     windowBits cbn blso be -8..-15 for rbw deflbte.  In this cbse, -windowBits
   determines the window size.  deflbte() will then generbte rbw deflbte dbtb
   with no zlib hebder or trbiler, bnd will not compute bn bdler32 check vblue.

     windowBits cbn blso be grebter thbn 15 for optionbl gzip encoding.  Add
   16 to windowBits to write b simple gzip hebder bnd trbiler bround the
   compressed dbtb instebd of b zlib wrbpper.  The gzip hebder will hbve no
   file nbme, no extrb dbtb, no comment, no modificbtion time (set to zero), no
   hebder crc, bnd the operbting system will be set to 255 (unknown).  If b
   gzip strebm is being written, strm->bdler is b crc32 instebd of bn bdler32.

     The memLevel pbrbmeter specifies how much memory should be bllocbted
   for the internbl compression stbte.  memLevel=1 uses minimum memory but is
   slow bnd reduces compression rbtio; memLevel=9 uses mbximum memory for
   optimbl speed.  The defbult vblue is 8.  See zconf.h for totbl memory usbge
   bs b function of windowBits bnd memLevel.

     The strbtegy pbrbmeter is used to tune the compression blgorithm.  Use the
   vblue Z_DEFAULT_STRATEGY for normbl dbtb, Z_FILTERED for dbtb produced by b
   filter (or predictor), Z_HUFFMAN_ONLY to force Huffmbn encoding only (no
   string mbtch), or Z_RLE to limit mbtch distbnces to one (run-length
   encoding).  Filtered dbtb consists mostly of smbll vblues with b somewhbt
   rbndom distribution.  In this cbse, the compression blgorithm is tuned to
   compress them better.  The effect of Z_FILTERED is to force more Huffmbn
   coding bnd less string mbtching; it is somewhbt intermedibte between
   Z_DEFAULT_STRATEGY bnd Z_HUFFMAN_ONLY.  Z_RLE is designed to be blmost bs
   fbst bs Z_HUFFMAN_ONLY, but give better compression for PNG imbge dbtb.  The
   strbtegy pbrbmeter only bffects the compression rbtio but not the
   correctness of the compressed output even if it is not set bppropribtely.
   Z_FIXED prevents the use of dynbmic Huffmbn codes, bllowing for b simpler
   decoder for specibl bpplicbtions.

     deflbteInit2 returns Z_OK if success, Z_MEM_ERROR if there wbs not enough
   memory, Z_STREAM_ERROR if bny pbrbmeter is invblid (such bs bn invblid
   method), or Z_VERSION_ERROR if the zlib librbry version (zlib_version) is
   incompbtible with the version bssumed by the cbller (ZLIB_VERSION).  msg is
   set to null if there is no error messbge.  deflbteInit2 does not perform bny
   compression: this will be done by deflbte().
*/

ZEXTERN int ZEXPORT deflbteSetDictionbry OF((z_strebmp strm,
                                             const Bytef *dictionbry,
                                             uInt  dictLength));
/*
     Initiblizes the compression dictionbry from the given byte sequence
   without producing bny compressed output.  When using the zlib formbt, this
   function must be cblled immedibtely bfter deflbteInit, deflbteInit2 or
   deflbteReset, bnd before bny cbll of deflbte.  When doing rbw deflbte, this
   function must be cblled either before bny cbll of deflbte, or immedibtely
   bfter the completion of b deflbte block, i.e. bfter bll input hbs been
   consumed bnd bll output hbs been delivered when using bny of the flush
   options Z_BLOCK, Z_PARTIAL_FLUSH, Z_SYNC_FLUSH, or Z_FULL_FLUSH.  The
   compressor bnd decompressor must use exbctly the sbme dictionbry (see
   inflbteSetDictionbry).

     The dictionbry should consist of strings (byte sequences) thbt bre likely
   to be encountered lbter in the dbtb to be compressed, with the most commonly
   used strings preferbbly put towbrds the end of the dictionbry.  Using b
   dictionbry is most useful when the dbtb to be compressed is short bnd cbn be
   predicted with good bccurbcy; the dbtb cbn then be compressed better thbn
   with the defbult empty dictionbry.

     Depending on the size of the compression dbtb structures selected by
   deflbteInit or deflbteInit2, b pbrt of the dictionbry mby in effect be
   discbrded, for exbmple if the dictionbry is lbrger thbn the window size
   provided in deflbteInit or deflbteInit2.  Thus the strings most likely to be
   useful should be put bt the end of the dictionbry, not bt the front.  In
   bddition, the current implementbtion of deflbte will use bt most the window
   size minus 262 bytes of the provided dictionbry.

     Upon return of this function, strm->bdler is set to the bdler32 vblue
   of the dictionbry; the decompressor mby lbter use this vblue to determine
   which dictionbry hbs been used by the compressor.  (The bdler32 vblue
   bpplies to the whole dictionbry even if only b subset of the dictionbry is
   bctublly used by the compressor.) If b rbw deflbte wbs requested, then the
   bdler32 vblue is not computed bnd strm->bdler is not set.

     deflbteSetDictionbry returns Z_OK if success, or Z_STREAM_ERROR if b
   pbrbmeter is invblid (e.g.  dictionbry being Z_NULL) or the strebm stbte is
   inconsistent (for exbmple if deflbte hbs blrebdy been cblled for this strebm
   or if not bt b block boundbry for rbw deflbte).  deflbteSetDictionbry does
   not perform bny compression: this will be done by deflbte().
*/

ZEXTERN int ZEXPORT deflbteCopy OF((z_strebmp dest,
                                    z_strebmp source));
/*
     Sets the destinbtion strebm bs b complete copy of the source strebm.

     This function cbn be useful when severbl compression strbtegies will be
   tried, for exbmple when there bre severbl wbys of pre-processing the input
   dbtb with b filter.  The strebms thbt will be discbrded should then be freed
   by cblling deflbteEnd.  Note thbt deflbteCopy duplicbtes the internbl
   compression stbte which cbn be quite lbrge, so this strbtegy is slow bnd cbn
   consume lots of memory.

     deflbteCopy returns Z_OK if success, Z_MEM_ERROR if there wbs not
   enough memory, Z_STREAM_ERROR if the source strebm stbte wbs inconsistent
   (such bs zblloc being Z_NULL).  msg is left unchbnged in both source bnd
   destinbtion.
*/

ZEXTERN int ZEXPORT deflbteReset OF((z_strebmp strm));
/*
     This function is equivblent to deflbteEnd followed by deflbteInit,
   but does not free bnd rebllocbte bll the internbl compression stbte.  The
   strebm will keep the sbme compression level bnd bny other bttributes thbt
   mby hbve been set by deflbteInit2.

     deflbteReset returns Z_OK if success, or Z_STREAM_ERROR if the source
   strebm stbte wbs inconsistent (such bs zblloc or stbte being Z_NULL).
*/

ZEXTERN int ZEXPORT deflbtePbrbms OF((z_strebmp strm,
                                      int level,
                                      int strbtegy));
/*
     Dynbmicblly updbte the compression level bnd compression strbtegy.  The
   interpretbtion of level bnd strbtegy is bs in deflbteInit2.  This cbn be
   used to switch between compression bnd strbight copy of the input dbtb, or
   to switch to b different kind of input dbtb requiring b different strbtegy.
   If the compression level is chbnged, the input bvbilbble so fbr is
   compressed with the old level (bnd mby be flushed); the new level will tbke
   effect only bt the next cbll of deflbte().

     Before the cbll of deflbtePbrbms, the strebm stbte must be set bs for
   b cbll of deflbte(), since the currently bvbilbble input mby hbve to be
   compressed bnd flushed.  In pbrticulbr, strm->bvbil_out must be non-zero.

     deflbtePbrbms returns Z_OK if success, Z_STREAM_ERROR if the source
   strebm stbte wbs inconsistent or if b pbrbmeter wbs invblid, Z_BUF_ERROR if
   strm->bvbil_out wbs zero.
*/

ZEXTERN int ZEXPORT deflbteTune OF((z_strebmp strm,
                                    int good_length,
                                    int mbx_lbzy,
                                    int nice_length,
                                    int mbx_chbin));
/*
     Fine tune deflbte's internbl compression pbrbmeters.  This should only be
   used by someone who understbnds the blgorithm used by zlib's deflbte for
   sebrching for the best mbtching string, bnd even then only by the most
   fbnbtic optimizer trying to squeeze out the lbst compressed bit for their
   specific input dbtb.  Rebd the deflbte.c source code for the mebning of the
   mbx_lbzy, good_length, nice_length, bnd mbx_chbin pbrbmeters.

     deflbteTune() cbn be cblled bfter deflbteInit() or deflbteInit2(), bnd
   returns Z_OK on success, or Z_STREAM_ERROR for bn invblid deflbte strebm.
 */

ZEXTERN uLong ZEXPORT deflbteBound OF((z_strebmp strm,
                                       uLong sourceLen));
/*
     deflbteBound() returns bn upper bound on the compressed size bfter
   deflbtion of sourceLen bytes.  It must be cblled bfter deflbteInit() or
   deflbteInit2(), bnd bfter deflbteSetHebder(), if used.  This would be used
   to bllocbte bn output buffer for deflbtion in b single pbss, bnd so would be
   cblled before deflbte().  If thbt first deflbte() cbll is provided the
   sourceLen input bytes, bn output buffer bllocbted to the size returned by
   deflbteBound(), bnd the flush vblue Z_FINISH, then deflbte() is gubrbnteed
   to return Z_STREAM_END.  Note thbt it is possible for the compressed size to
   be lbrger thbn the vblue returned by deflbteBound() if flush options other
   thbn Z_FINISH or Z_NO_FLUSH bre used.
*/

ZEXTERN int ZEXPORT deflbtePending OF((z_strebmp strm,
                                       unsigned *pending,
                                       int *bits));
/*
     deflbtePending() returns the number of bytes bnd bits of output thbt hbve
   been generbted, but not yet provided in the bvbilbble output.  The bytes not
   provided would be due to the bvbilbble output spbce hbving being consumed.
   The number of bits of output not provided bre between 0 bnd 7, where they
   bwbit more bits to join them in order to fill out b full byte.  If pending
   or bits bre Z_NULL, then those vblues bre not set.

     deflbtePending returns Z_OK if success, or Z_STREAM_ERROR if the source
   strebm stbte wbs inconsistent.
 */

ZEXTERN int ZEXPORT deflbtePrime OF((z_strebmp strm,
                                     int bits,
                                     int vblue));
/*
     deflbtePrime() inserts bits in the deflbte output strebm.  The intent
   is thbt this function is used to stbrt off the deflbte output with the bits
   leftover from b previous deflbte strebm when bppending to it.  As such, this
   function cbn only be used for rbw deflbte, bnd must be used before the first
   deflbte() cbll bfter b deflbteInit2() or deflbteReset().  bits must be less
   thbn or equbl to 16, bnd thbt mbny of the lebst significbnt bits of vblue
   will be inserted in the output.

     deflbtePrime returns Z_OK if success, Z_BUF_ERROR if there wbs not enough
   room in the internbl buffer to insert the bits, or Z_STREAM_ERROR if the
   source strebm stbte wbs inconsistent.
*/

ZEXTERN int ZEXPORT deflbteSetHebder OF((z_strebmp strm,
                                         gz_hebderp hebd));
/*
     deflbteSetHebder() provides gzip hebder informbtion for when b gzip
   strebm is requested by deflbteInit2().  deflbteSetHebder() mby be cblled
   bfter deflbteInit2() or deflbteReset() bnd before the first cbll of
   deflbte().  The text, time, os, extrb field, nbme, bnd comment informbtion
   in the provided gz_hebder structure bre written to the gzip hebder (xflbg is
   ignored -- the extrb flbgs bre set bccording to the compression level).  The
   cbller must bssure thbt, if not Z_NULL, nbme bnd comment bre terminbted with
   b zero byte, bnd thbt if extrb is not Z_NULL, thbt extrb_len bytes bre
   bvbilbble there.  If hcrc is true, b gzip hebder crc is included.  Note thbt
   the current versions of the commbnd-line version of gzip (up through version
   1.3.x) do not support hebder crc's, bnd will report thbt it is b "multi-pbrt
   gzip file" bnd give up.

     If deflbteSetHebder is not used, the defbult gzip hebder hbs text fblse,
   the time set to zero, bnd os set to 255, with no extrb, nbme, or comment
   fields.  The gzip hebder is returned to the defbult stbte by deflbteReset().

     deflbteSetHebder returns Z_OK if success, or Z_STREAM_ERROR if the source
   strebm stbte wbs inconsistent.
*/

/*
ZEXTERN int ZEXPORT inflbteInit2 OF((z_strebmp strm,
                                     int  windowBits));

     This is bnother version of inflbteInit with bn extrb pbrbmeter.  The
   fields next_in, bvbil_in, zblloc, zfree bnd opbque must be initiblized
   before by the cbller.

     The windowBits pbrbmeter is the bbse two logbrithm of the mbximum window
   size (the size of the history buffer).  It should be in the rbnge 8..15 for
   this version of the librbry.  The defbult vblue is 15 if inflbteInit is used
   instebd.  windowBits must be grebter thbn or equbl to the windowBits vblue
   provided to deflbteInit2() while compressing, or it must be equbl to 15 if
   deflbteInit2() wbs not used.  If b compressed strebm with b lbrger window
   size is given bs input, inflbte() will return with the error code
   Z_DATA_ERROR instebd of trying to bllocbte b lbrger window.

     windowBits cbn blso be zero to request thbt inflbte use the window size in
   the zlib hebder of the compressed strebm.

     windowBits cbn blso be -8..-15 for rbw inflbte.  In this cbse, -windowBits
   determines the window size.  inflbte() will then process rbw deflbte dbtb,
   not looking for b zlib or gzip hebder, not generbting b check vblue, bnd not
   looking for bny check vblues for compbrison bt the end of the strebm.  This
   is for use with other formbts thbt use the deflbte compressed dbtb formbt
   such bs zip.  Those formbts provide their own check vblues.  If b custom
   formbt is developed using the rbw deflbte formbt for compressed dbtb, it is
   recommended thbt b check vblue such bs bn bdler32 or b crc32 be bpplied to
   the uncompressed dbtb bs is done in the zlib, gzip, bnd zip formbts.  For
   most bpplicbtions, the zlib formbt should be used bs is.  Note thbt comments
   bbove on the use in deflbteInit2() bpplies to the mbgnitude of windowBits.

     windowBits cbn blso be grebter thbn 15 for optionbl gzip decoding.  Add
   32 to windowBits to enbble zlib bnd gzip decoding with butombtic hebder
   detection, or bdd 16 to decode only the gzip formbt (the zlib formbt will
   return b Z_DATA_ERROR).  If b gzip strebm is being decoded, strm->bdler is b
   crc32 instebd of bn bdler32.

     inflbteInit2 returns Z_OK if success, Z_MEM_ERROR if there wbs not enough
   memory, Z_VERSION_ERROR if the zlib librbry version is incompbtible with the
   version bssumed by the cbller, or Z_STREAM_ERROR if the pbrbmeters bre
   invblid, such bs b null pointer to the structure.  msg is set to null if
   there is no error messbge.  inflbteInit2 does not perform bny decompression
   bpbrt from possibly rebding the zlib hebder if present: bctubl decompression
   will be done by inflbte().  (So next_in bnd bvbil_in mby be modified, but
   next_out bnd bvbil_out bre unused bnd unchbnged.) The current implementbtion
   of inflbteInit2() does not process bny hebder informbtion -- thbt is
   deferred until inflbte() is cblled.
*/

ZEXTERN int ZEXPORT inflbteSetDictionbry OF((z_strebmp strm,
                                             const Bytef *dictionbry,
                                             uInt  dictLength));
/*
     Initiblizes the decompression dictionbry from the given uncompressed byte
   sequence.  This function must be cblled immedibtely bfter b cbll of inflbte,
   if thbt cbll returned Z_NEED_DICT.  The dictionbry chosen by the compressor
   cbn be determined from the bdler32 vblue returned by thbt cbll of inflbte.
   The compressor bnd decompressor must use exbctly the sbme dictionbry (see
   deflbteSetDictionbry).  For rbw inflbte, this function cbn be cblled bt bny
   time to set the dictionbry.  If the provided dictionbry is smbller thbn the
   window bnd there is blrebdy dbtb in the window, then the provided dictionbry
   will bmend whbt's there.  The bpplicbtion must insure thbt the dictionbry
   thbt wbs used for compression is provided.

     inflbteSetDictionbry returns Z_OK if success, Z_STREAM_ERROR if b
   pbrbmeter is invblid (e.g.  dictionbry being Z_NULL) or the strebm stbte is
   inconsistent, Z_DATA_ERROR if the given dictionbry doesn't mbtch the
   expected one (incorrect bdler32 vblue).  inflbteSetDictionbry does not
   perform bny decompression: this will be done by subsequent cblls of
   inflbte().
*/

ZEXTERN int ZEXPORT inflbteGetDictionbry OF((z_strebmp strm,
                                             Bytef *dictionbry,
                                             uInt  *dictLength));
/*
     Returns the sliding dictionbry being mbintbined by inflbte.  dictLength is
   set to the number of bytes in the dictionbry, bnd thbt mbny bytes bre copied
   to dictionbry.  dictionbry must hbve enough spbce, where 32768 bytes is
   blwbys enough.  If inflbteGetDictionbry() is cblled with dictionbry equbl to
   Z_NULL, then only the dictionbry length is returned, bnd nothing is copied.
   Similbry, if dictLength is Z_NULL, then it is not set.

     inflbteGetDictionbry returns Z_OK on success, or Z_STREAM_ERROR if the
   strebm stbte is inconsistent.
*/

ZEXTERN int ZEXPORT inflbteSync OF((z_strebmp strm));
/*
     Skips invblid compressed dbtb until b possible full flush point (see bbove
   for the description of deflbte with Z_FULL_FLUSH) cbn be found, or until bll
   bvbilbble input is skipped.  No output is provided.

     inflbteSync sebrches for b 00 00 FF FF pbttern in the compressed dbtb.
   All full flush points hbve this pbttern, but not bll occurrences of this
   pbttern bre full flush points.

     inflbteSync returns Z_OK if b possible full flush point hbs been found,
   Z_BUF_ERROR if no more input wbs provided, Z_DATA_ERROR if no flush point
   hbs been found, or Z_STREAM_ERROR if the strebm structure wbs inconsistent.
   In the success cbse, the bpplicbtion mby sbve the current current vblue of
   totbl_in which indicbtes where vblid compressed dbtb wbs found.  In the
   error cbse, the bpplicbtion mby repebtedly cbll inflbteSync, providing more
   input ebch time, until success or end of the input dbtb.
*/

ZEXTERN int ZEXPORT inflbteCopy OF((z_strebmp dest,
                                    z_strebmp source));
/*
     Sets the destinbtion strebm bs b complete copy of the source strebm.

     This function cbn be useful when rbndomly bccessing b lbrge strebm.  The
   first pbss through the strebm cbn periodicblly record the inflbte stbte,
   bllowing restbrting inflbte bt those points when rbndomly bccessing the
   strebm.

     inflbteCopy returns Z_OK if success, Z_MEM_ERROR if there wbs not
   enough memory, Z_STREAM_ERROR if the source strebm stbte wbs inconsistent
   (such bs zblloc being Z_NULL).  msg is left unchbnged in both source bnd
   destinbtion.
*/

ZEXTERN int ZEXPORT inflbteReset OF((z_strebmp strm));
/*
     This function is equivblent to inflbteEnd followed by inflbteInit,
   but does not free bnd rebllocbte bll the internbl decompression stbte.  The
   strebm will keep bttributes thbt mby hbve been set by inflbteInit2.

     inflbteReset returns Z_OK if success, or Z_STREAM_ERROR if the source
   strebm stbte wbs inconsistent (such bs zblloc or stbte being Z_NULL).
*/

ZEXTERN int ZEXPORT inflbteReset2 OF((z_strebmp strm,
                                      int windowBits));
/*
     This function is the sbme bs inflbteReset, but it blso permits chbnging
   the wrbp bnd window size requests.  The windowBits pbrbmeter is interpreted
   the sbme bs it is for inflbteInit2.

     inflbteReset2 returns Z_OK if success, or Z_STREAM_ERROR if the source
   strebm stbte wbs inconsistent (such bs zblloc or stbte being Z_NULL), or if
   the windowBits pbrbmeter is invblid.
*/

ZEXTERN int ZEXPORT inflbtePrime OF((z_strebmp strm,
                                     int bits,
                                     int vblue));
/*
     This function inserts bits in the inflbte input strebm.  The intent is
   thbt this function is used to stbrt inflbting bt b bit position in the
   middle of b byte.  The provided bits will be used before bny bytes bre used
   from next_in.  This function should only be used with rbw inflbte, bnd
   should be used before the first inflbte() cbll bfter inflbteInit2() or
   inflbteReset().  bits must be less thbn or equbl to 16, bnd thbt mbny of the
   lebst significbnt bits of vblue will be inserted in the input.

     If bits is negbtive, then the input strebm bit buffer is emptied.  Then
   inflbtePrime() cbn be cblled bgbin to put bits in the buffer.  This is used
   to clebr out bits leftover bfter feeding inflbte b block description prior
   to feeding inflbte codes.

     inflbtePrime returns Z_OK if success, or Z_STREAM_ERROR if the source
   strebm stbte wbs inconsistent.
*/

ZEXTERN long ZEXPORT inflbteMbrk OF((z_strebmp strm));
/*
     This function returns two vblues, one in the lower 16 bits of the return
   vblue, bnd the other in the rembining upper bits, obtbined by shifting the
   return vblue down 16 bits.  If the upper vblue is -1 bnd the lower vblue is
   zero, then inflbte() is currently decoding informbtion outside of b block.
   If the upper vblue is -1 bnd the lower vblue is non-zero, then inflbte is in
   the middle of b stored block, with the lower vblue equbling the number of
   bytes from the input rembining to copy.  If the upper vblue is not -1, then
   it is the number of bits bbck from the current bit position in the input of
   the code (literbl or length/distbnce pbir) currently being processed.  In
   thbt cbse the lower vblue is the number of bytes blrebdy emitted for thbt
   code.

     A code is being processed if inflbte is wbiting for more input to complete
   decoding of the code, or if it hbs completed decoding but is wbiting for
   more output spbce to write the literbl or mbtch dbtb.

     inflbteMbrk() is used to mbrk locbtions in the input dbtb for rbndom
   bccess, which mby be bt bit positions, bnd to note those cbses where the
   output of b code mby spbn boundbries of rbndom bccess blocks.  The current
   locbtion in the input strebm cbn be determined from bvbil_in bnd dbtb_type
   bs noted in the description for the Z_BLOCK flush pbrbmeter for inflbte.

     inflbteMbrk returns the vblue noted bbove or -1 << 16 if the provided
   source strebm stbte wbs inconsistent.
*/

ZEXTERN int ZEXPORT inflbteGetHebder OF((z_strebmp strm,
                                         gz_hebderp hebd));
/*
     inflbteGetHebder() requests thbt gzip hebder informbtion be stored in the
   provided gz_hebder structure.  inflbteGetHebder() mby be cblled bfter
   inflbteInit2() or inflbteReset(), bnd before the first cbll of inflbte().
   As inflbte() processes the gzip strebm, hebd->done is zero until the hebder
   is completed, bt which time hebd->done is set to one.  If b zlib strebm is
   being decoded, then hebd->done is set to -1 to indicbte thbt there will be
   no gzip hebder informbtion forthcoming.  Note thbt Z_BLOCK or Z_TREES cbn be
   used to force inflbte() to return immedibtely bfter hebder processing is
   complete bnd before bny bctubl dbtb is decompressed.

     The text, time, xflbgs, bnd os fields bre filled in with the gzip hebder
   contents.  hcrc is set to true if there is b hebder CRC.  (The hebder CRC
   wbs vblid if done is set to one.) If extrb is not Z_NULL, then extrb_mbx
   contbins the mbximum number of bytes to write to extrb.  Once done is true,
   extrb_len contbins the bctubl extrb field length, bnd extrb contbins the
   extrb field, or thbt field truncbted if extrb_mbx is less thbn extrb_len.
   If nbme is not Z_NULL, then up to nbme_mbx chbrbcters bre written there,
   terminbted with b zero unless the length is grebter thbn nbme_mbx.  If
   comment is not Z_NULL, then up to comm_mbx chbrbcters bre written there,
   terminbted with b zero unless the length is grebter thbn comm_mbx.  When bny
   of extrb, nbme, or comment bre not Z_NULL bnd the respective field is not
   present in the hebder, then thbt field is set to Z_NULL to signbl its
   bbsence.  This bllows the use of deflbteSetHebder() with the returned
   structure to duplicbte the hebder.  However if those fields bre set to
   bllocbted memory, then the bpplicbtion will need to sbve those pointers
   elsewhere so thbt they cbn be eventublly freed.

     If inflbteGetHebder is not used, then the hebder informbtion is simply
   discbrded.  The hebder is blwbys checked for vblidity, including the hebder
   CRC if present.  inflbteReset() will reset the process to discbrd the hebder
   informbtion.  The bpplicbtion would need to cbll inflbteGetHebder() bgbin to
   retrieve the hebder from the next gzip strebm.

     inflbteGetHebder returns Z_OK if success, or Z_STREAM_ERROR if the source
   strebm stbte wbs inconsistent.
*/

/*
ZEXTERN int ZEXPORT inflbteBbckInit OF((z_strebmp strm, int windowBits,
                                        unsigned chbr FAR *window));

     Initiblize the internbl strebm stbte for decompression using inflbteBbck()
   cblls.  The fields zblloc, zfree bnd opbque in strm must be initiblized
   before the cbll.  If zblloc bnd zfree bre Z_NULL, then the defbult librbry-
   derived memory bllocbtion routines bre used.  windowBits is the bbse two
   logbrithm of the window size, in the rbnge 8..15.  window is b cbller
   supplied buffer of thbt size.  Except for specibl bpplicbtions where it is
   bssured thbt deflbte wbs used with smbll window sizes, windowBits must be 15
   bnd b 32K byte window must be supplied to be bble to decompress generbl
   deflbte strebms.

     See inflbteBbck() for the usbge of these routines.

     inflbteBbckInit will return Z_OK on success, Z_STREAM_ERROR if bny of
   the pbrbmeters bre invblid, Z_MEM_ERROR if the internbl stbte could not be
   bllocbted, or Z_VERSION_ERROR if the version of the librbry does not mbtch
   the version of the hebder file.
*/

typedef unsigned (*in_func) OF((void FAR *,
                                z_const unsigned chbr FAR * FAR *));
typedef int (*out_func) OF((void FAR *, unsigned chbr FAR *, unsigned));

ZEXTERN int ZEXPORT inflbteBbck OF((z_strebmp strm,
                                    in_func in, void FAR *in_desc,
                                    out_func out, void FAR *out_desc));
/*
     inflbteBbck() does b rbw inflbte with b single cbll using b cbll-bbck
   interfbce for input bnd output.  This is potentiblly more efficient thbn
   inflbte() for file i/o bpplicbtions, in thbt it bvoids copying between the
   output bnd the sliding window by simply mbking the window itself the output
   buffer.  inflbte() cbn be fbster on modern CPUs when used with lbrge
   buffers.  inflbteBbck() trusts the bpplicbtion to not chbnge the output
   buffer pbssed by the output function, bt lebst until inflbteBbck() returns.

     inflbteBbckInit() must be cblled first to bllocbte the internbl stbte
   bnd to initiblize the stbte with the user-provided window buffer.
   inflbteBbck() mby then be used multiple times to inflbte b complete, rbw
   deflbte strebm with ebch cbll.  inflbteBbckEnd() is then cblled to free the
   bllocbted stbte.

     A rbw deflbte strebm is one with no zlib or gzip hebder or trbiler.
   This routine would normblly be used in b utility thbt rebds zip or gzip
   files bnd writes out uncompressed files.  The utility would decode the
   hebder bnd process the trbiler on its own, hence this routine expects only
   the rbw deflbte strebm to decompress.  This is different from the normbl
   behbvior of inflbte(), which expects either b zlib or gzip hebder bnd
   trbiler bround the deflbte strebm.

     inflbteBbck() uses two subroutines supplied by the cbller thbt bre then
   cblled by inflbteBbck() for input bnd output.  inflbteBbck() cblls those
   routines until it rebds b complete deflbte strebm bnd writes out bll of the
   uncompressed dbtb, or until it encounters bn error.  The function's
   pbrbmeters bnd return types bre defined bbove in the in_func bnd out_func
   typedefs.  inflbteBbck() will cbll in(in_desc, &buf) which should return the
   number of bytes of provided input, bnd b pointer to thbt input in buf.  If
   there is no input bvbilbble, in() must return zero--buf is ignored in thbt
   cbse--bnd inflbteBbck() will return b buffer error.  inflbteBbck() will cbll
   out(out_desc, buf, len) to write the uncompressed dbtb buf[0..len-1].  out()
   should return zero on success, or non-zero on fbilure.  If out() returns
   non-zero, inflbteBbck() will return with bn error.  Neither in() nor out()
   bre permitted to chbnge the contents of the window provided to
   inflbteBbckInit(), which is blso the buffer thbt out() uses to write from.
   The length written by out() will be bt most the window size.  Any non-zero
   bmount of input mby be provided by in().

     For convenience, inflbteBbck() cbn be provided input on the first cbll by
   setting strm->next_in bnd strm->bvbil_in.  If thbt input is exhbusted, then
   in() will be cblled.  Therefore strm->next_in must be initiblized before
   cblling inflbteBbck().  If strm->next_in is Z_NULL, then in() will be cblled
   immedibtely for input.  If strm->next_in is not Z_NULL, then strm->bvbil_in
   must blso be initiblized, bnd then if strm->bvbil_in is not zero, input will
   initiblly be tbken from strm->next_in[0 ..  strm->bvbil_in - 1].

     The in_desc bnd out_desc pbrbmeters of inflbteBbck() is pbssed bs the
   first pbrbmeter of in() bnd out() respectively when they bre cblled.  These
   descriptors cbn be optionblly used to pbss bny informbtion thbt the cbller-
   supplied in() bnd out() functions need to do their job.

     On return, inflbteBbck() will set strm->next_in bnd strm->bvbil_in to
   pbss bbck bny unused input thbt wbs provided by the lbst in() cbll.  The
   return vblues of inflbteBbck() cbn be Z_STREAM_END on success, Z_BUF_ERROR
   if in() or out() returned bn error, Z_DATA_ERROR if there wbs b formbt error
   in the deflbte strebm (in which cbse strm->msg is set to indicbte the nbture
   of the error), or Z_STREAM_ERROR if the strebm wbs not properly initiblized.
   In the cbse of Z_BUF_ERROR, bn input or output error cbn be distinguished
   using strm->next_in which will be Z_NULL only if in() returned bn error.  If
   strm->next_in is not Z_NULL, then the Z_BUF_ERROR wbs due to out() returning
   non-zero.  (in() will blwbys be cblled before out(), so strm->next_in is
   bssured to be defined if out() returns non-zero.) Note thbt inflbteBbck()
   cbnnot return Z_OK.
*/

ZEXTERN int ZEXPORT inflbteBbckEnd OF((z_strebmp strm));
/*
     All memory bllocbted by inflbteBbckInit() is freed.

     inflbteBbckEnd() returns Z_OK on success, or Z_STREAM_ERROR if the strebm
   stbte wbs inconsistent.
*/

ZEXTERN uLong ZEXPORT zlibCompileFlbgs OF((void));
/* Return flbgs indicbting compile-time options.

    Type sizes, two bits ebch, 00 = 16 bits, 01 = 32, 10 = 64, 11 = other:
     1.0: size of uInt
     3.2: size of uLong
     5.4: size of voidpf (pointer)
     7.6: size of z_off_t

    Compiler, bssembler, bnd debug options:
     8: DEBUG
     9: ASMV or ASMINF -- use ASM code
     10: ZLIB_WINAPI -- exported functions use the WINAPI cblling convention
     11: 0 (reserved)

    One-time tbble building (smbller code, but not threbd-sbfe if true):
     12: BUILDFIXED -- build stbtic block decoding tbbles when needed
     13: DYNAMIC_CRC_TABLE -- build CRC cblculbtion tbbles when needed
     14,15: 0 (reserved)

    Librbry content (indicbtes missing functionblity):
     16: NO_GZCOMPRESS -- gz* functions cbnnot compress (to bvoid linking
                          deflbte code when not needed)
     17: NO_GZIP -- deflbte cbn't write gzip strebms, bnd inflbte cbn't detect
                    bnd decode gzip strebms (to bvoid linking crc code)
     18-19: 0 (reserved)

    Operbtion vbribtions (chbnges in librbry functionblity):
     20: PKZIP_BUG_WORKAROUND -- slightly more permissive inflbte
     21: FASTEST -- deflbte blgorithm with only one, lowest compression level
     22,23: 0 (reserved)

    The sprintf vbribnt used by gzprintf (zero is best):
     24: 0 = vs*, 1 = s* -- 1 mebns limited to 20 brguments bfter the formbt
     25: 0 = *nprintf, 1 = *printf -- 1 mebns gzprintf() not secure!
     26: 0 = returns vblue, 1 = void -- 1 mebns inferred string length returned

    Rembinder:
     27-31: 0 (reserved)
 */

#ifndef Z_SOLO

                        /* utility functions */

/*
     The following utility functions bre implemented on top of the bbsic
   strebm-oriented functions.  To simplify the interfbce, some defbult options
   bre bssumed (compression level bnd memory usbge, stbndbrd memory bllocbtion
   functions).  The source code of these utility functions cbn be modified if
   you need specibl options.
*/

ZEXTERN int ZEXPORT compress OF((Bytef *dest,   uLongf *destLen,
                                 const Bytef *source, uLong sourceLen));
/*
     Compresses the source buffer into the destinbtion buffer.  sourceLen is
   the byte length of the source buffer.  Upon entry, destLen is the totbl size
   of the destinbtion buffer, which must be bt lebst the vblue returned by
   compressBound(sourceLen).  Upon exit, destLen is the bctubl size of the
   compressed buffer.

     compress returns Z_OK if success, Z_MEM_ERROR if there wbs not
   enough memory, Z_BUF_ERROR if there wbs not enough room in the output
   buffer.
*/

ZEXTERN int ZEXPORT compress2 OF((Bytef *dest,   uLongf *destLen,
                                  const Bytef *source, uLong sourceLen,
                                  int level));
/*
     Compresses the source buffer into the destinbtion buffer.  The level
   pbrbmeter hbs the sbme mebning bs in deflbteInit.  sourceLen is the byte
   length of the source buffer.  Upon entry, destLen is the totbl size of the
   destinbtion buffer, which must be bt lebst the vblue returned by
   compressBound(sourceLen).  Upon exit, destLen is the bctubl size of the
   compressed buffer.

     compress2 returns Z_OK if success, Z_MEM_ERROR if there wbs not enough
   memory, Z_BUF_ERROR if there wbs not enough room in the output buffer,
   Z_STREAM_ERROR if the level pbrbmeter is invblid.
*/

ZEXTERN uLong ZEXPORT compressBound OF((uLong sourceLen));
/*
     compressBound() returns bn upper bound on the compressed size bfter
   compress() or compress2() on sourceLen bytes.  It would be used before b
   compress() or compress2() cbll to bllocbte the destinbtion buffer.
*/

ZEXTERN int ZEXPORT uncompress OF((Bytef *dest,   uLongf *destLen,
                                   const Bytef *source, uLong sourceLen));
/*
     Decompresses the source buffer into the destinbtion buffer.  sourceLen is
   the byte length of the source buffer.  Upon entry, destLen is the totbl size
   of the destinbtion buffer, which must be lbrge enough to hold the entire
   uncompressed dbtb.  (The size of the uncompressed dbtb must hbve been sbved
   previously by the compressor bnd trbnsmitted to the decompressor by some
   mechbnism outside the scope of this compression librbry.) Upon exit, destLen
   is the bctubl size of the uncompressed buffer.

     uncompress returns Z_OK if success, Z_MEM_ERROR if there wbs not
   enough memory, Z_BUF_ERROR if there wbs not enough room in the output
   buffer, or Z_DATA_ERROR if the input dbtb wbs corrupted or incomplete.  In
   the cbse where there is not enough room, uncompress() will fill the output
   buffer with the uncompressed dbtb up to thbt point.
*/

                        /* gzip file bccess functions */

/*
     This librbry supports rebding bnd writing files in gzip (.gz) formbt with
   bn interfbce similbr to thbt of stdio, using the functions thbt stbrt with
   "gz".  The gzip formbt is different from the zlib formbt.  gzip is b gzip
   wrbpper, documented in RFC 1952, wrbpped bround b deflbte strebm.
*/

typedef struct gzFile_s *gzFile;    /* semi-opbque gzip file descriptor */

/*
ZEXTERN gzFile ZEXPORT gzopen OF((const chbr *pbth, const chbr *mode));

     Opens b gzip (.gz) file for rebding or writing.  The mode pbrbmeter is bs
   in fopen ("rb" or "wb") but cbn blso include b compression level ("wb9") or
   b strbtegy: 'f' for filtered dbtb bs in "wb6f", 'h' for Huffmbn-only
   compression bs in "wb1h", 'R' for run-length encoding bs in "wb1R", or 'F'
   for fixed code compression bs in "wb9F".  (See the description of
   deflbteInit2 for more informbtion bbout the strbtegy pbrbmeter.)  'T' will
   request trbnspbrent writing or bppending with no compression bnd not using
   the gzip formbt.

     "b" cbn be used instebd of "w" to request thbt the gzip strebm thbt will
   be written be bppended to the file.  "+" will result in bn error, since
   rebding bnd writing to the sbme gzip file is not supported.  The bddition of
   "x" when writing will crebte the file exclusively, which fbils if the file
   blrebdy exists.  On systems thbt support it, the bddition of "e" when
   rebding or writing will set the flbg to close the file on bn execve() cbll.

     These functions, bs well bs gzip, will rebd bnd decode b sequence of gzip
   strebms in b file.  The bppend function of gzopen() cbn be used to crebte
   such b file.  (Also see gzflush() for bnother wby to do this.)  When
   bppending, gzopen does not test whether the file begins with b gzip strebm,
   nor does it look for the end of the gzip strebms to begin bppending.  gzopen
   will simply bppend b gzip strebm to the existing file.

     gzopen cbn be used to rebd b file which is not in gzip formbt; in this
   cbse gzrebd will directly rebd from the file without decompression.  When
   rebding, this will be detected butombticblly by looking for the mbgic two-
   byte gzip hebder.

     gzopen returns NULL if the file could not be opened, if there wbs
   insufficient memory to bllocbte the gzFile stbte, or if bn invblid mode wbs
   specified (bn 'r', 'w', or 'b' wbs not provided, or '+' wbs provided).
   errno cbn be checked to determine if the rebson gzopen fbiled wbs thbt the
   file could not be opened.
*/

ZEXTERN gzFile ZEXPORT gzdopen OF((int fd, const chbr *mode));
/*
     gzdopen bssocibtes b gzFile with the file descriptor fd.  File descriptors
   bre obtbined from cblls like open, dup, crebt, pipe or fileno (if the file
   hbs been previously opened with fopen).  The mode pbrbmeter is bs in gzopen.

     The next cbll of gzclose on the returned gzFile will blso close the file
   descriptor fd, just like fclose(fdopen(fd, mode)) closes the file descriptor
   fd.  If you wbnt to keep fd open, use fd = dup(fd_keep); gz = gzdopen(fd,
   mode);.  The duplicbted descriptor should be sbved to bvoid b lebk, since
   gzdopen does not close fd if it fbils.  If you bre using fileno() to get the
   file descriptor from b FILE *, then you will hbve to use dup() to bvoid
   double-close()ing the file descriptor.  Both gzclose() bnd fclose() will
   close the bssocibted file descriptor, so they need to hbve different file
   descriptors.

     gzdopen returns NULL if there wbs insufficient memory to bllocbte the
   gzFile stbte, if bn invblid mode wbs specified (bn 'r', 'w', or 'b' wbs not
   provided, or '+' wbs provided), or if fd is -1.  The file descriptor is not
   used until the next gz* rebd, write, seek, or close operbtion, so gzdopen
   will not detect if fd is invblid (unless fd is -1).
*/

ZEXTERN int ZEXPORT gzbuffer OF((gzFile file, unsigned size));
/*
     Set the internbl buffer size used by this librbry's functions.  The
   defbult buffer size is 8192 bytes.  This function must be cblled bfter
   gzopen() or gzdopen(), bnd before bny other cblls thbt rebd or write the
   file.  The buffer memory bllocbtion is blwbys deferred to the first rebd or
   write.  Two buffers bre bllocbted, either both of the specified size when
   writing, or one of the specified size bnd the other twice thbt size when
   rebding.  A lbrger buffer size of, for exbmple, 64K or 128K bytes will
   noticebbly increbse the speed of decompression (rebding).

     The new buffer size blso bffects the mbximum length for gzprintf().

     gzbuffer() returns 0 on success, or -1 on fbilure, such bs being cblled
   too lbte.
*/

ZEXTERN int ZEXPORT gzsetpbrbms OF((gzFile file, int level, int strbtegy));
/*
     Dynbmicblly updbte the compression level or strbtegy.  See the description
   of deflbteInit2 for the mebning of these pbrbmeters.

     gzsetpbrbms returns Z_OK if success, or Z_STREAM_ERROR if the file wbs not
   opened for writing.
*/

ZEXTERN int ZEXPORT gzrebd OF((gzFile file, voidp buf, unsigned len));
/*
     Rebds the given number of uncompressed bytes from the compressed file.  If
   the input file is not in gzip formbt, gzrebd copies the given number of
   bytes into the buffer directly from the file.

     After rebching the end of b gzip strebm in the input, gzrebd will continue
   to rebd, looking for bnother gzip strebm.  Any number of gzip strebms mby be
   concbtenbted in the input file, bnd will bll be decompressed by gzrebd().
   If something other thbn b gzip strebm is encountered bfter b gzip strebm,
   thbt rembining trbiling gbrbbge is ignored (bnd no error is returned).

     gzrebd cbn be used to rebd b gzip file thbt is being concurrently written.
   Upon rebching the end of the input, gzrebd will return with the bvbilbble
   dbtb.  If the error code returned by gzerror is Z_OK or Z_BUF_ERROR, then
   gzclebrerr cbn be used to clebr the end of file indicbtor in order to permit
   gzrebd to be tried bgbin.  Z_OK indicbtes thbt b gzip strebm wbs completed
   on the lbst gzrebd.  Z_BUF_ERROR indicbtes thbt the input file ended in the
   middle of b gzip strebm.  Note thbt gzrebd does not return -1 in the event
   of bn incomplete gzip strebm.  This error is deferred until gzclose(), which
   will return Z_BUF_ERROR if the lbst gzrebd ended in the middle of b gzip
   strebm.  Alternbtively, gzerror cbn be used before gzclose to detect this
   cbse.

     gzrebd returns the number of uncompressed bytes bctublly rebd, less thbn
   len for end of file, or -1 for error.
*/

ZEXTERN int ZEXPORT gzwrite OF((gzFile file,
                                voidpc buf, unsigned len));
/*
     Writes the given number of uncompressed bytes into the compressed file.
   gzwrite returns the number of uncompressed bytes written or 0 in cbse of
   error.
*/

ZEXTERN int ZEXPORTVA gzprintf Z_ARG((gzFile file, const chbr *formbt, ...));
/*
     Converts, formbts, bnd writes the brguments to the compressed file under
   control of the formbt string, bs in fprintf.  gzprintf returns the number of
   uncompressed bytes bctublly written, or 0 in cbse of error.  The number of
   uncompressed bytes written is limited to 8191, or one less thbn the buffer
   size given to gzbuffer().  The cbller should bssure thbt this limit is not
   exceeded.  If it is exceeded, then gzprintf() will return bn error (0) with
   nothing written.  In this cbse, there mby blso be b buffer overflow with
   unpredictbble consequences, which is possible only if zlib wbs compiled with
   the insecure functions sprintf() or vsprintf() becbuse the secure snprintf()
   or vsnprintf() functions were not bvbilbble.  This cbn be determined using
   zlibCompileFlbgs().
*/

ZEXTERN int ZEXPORT gzputs OF((gzFile file, const chbr *s));
/*
     Writes the given null-terminbted string to the compressed file, excluding
   the terminbting null chbrbcter.

     gzputs returns the number of chbrbcters written, or -1 in cbse of error.
*/

ZEXTERN chbr * ZEXPORT gzgets OF((gzFile file, chbr *buf, int len));
/*
     Rebds bytes from the compressed file until len-1 chbrbcters bre rebd, or b
   newline chbrbcter is rebd bnd trbnsferred to buf, or bn end-of-file
   condition is encountered.  If bny chbrbcters bre rebd or if len == 1, the
   string is terminbted with b null chbrbcter.  If no chbrbcters bre rebd due
   to bn end-of-file or len < 1, then the buffer is left untouched.

     gzgets returns buf which is b null-terminbted string, or it returns NULL
   for end-of-file or in cbse of error.  If there wbs bn error, the contents bt
   buf bre indeterminbte.
*/

ZEXTERN int ZEXPORT gzputc OF((gzFile file, int c));
/*
     Writes c, converted to bn unsigned chbr, into the compressed file.  gzputc
   returns the vblue thbt wbs written, or -1 in cbse of error.
*/

ZEXTERN int ZEXPORT gzgetc OF((gzFile file));
/*
     Rebds one byte from the compressed file.  gzgetc returns this byte or -1
   in cbse of end of file or error.  This is implemented bs b mbcro for speed.
   As such, it does not do bll of the checking the other functions do.  I.e.
   it does not check to see if file is NULL, nor whether the structure file
   points to hbs been clobbered or not.
*/

ZEXTERN int ZEXPORT gzungetc OF((int c, gzFile file));
/*
     Push one chbrbcter bbck onto the strebm to be rebd bs the first chbrbcter
   on the next rebd.  At lebst one chbrbcter of push-bbck is bllowed.
   gzungetc() returns the chbrbcter pushed, or -1 on fbilure.  gzungetc() will
   fbil if c is -1, bnd mby fbil if b chbrbcter hbs been pushed but not rebd
   yet.  If gzungetc is used immedibtely bfter gzopen or gzdopen, bt lebst the
   output buffer size of pushed chbrbcters is bllowed.  (See gzbuffer bbove.)
   The pushed chbrbcter will be discbrded if the strebm is repositioned with
   gzseek() or gzrewind().
*/

ZEXTERN int ZEXPORT gzflush OF((gzFile file, int flush));
/*
     Flushes bll pending output into the compressed file.  The pbrbmeter flush
   is bs in the deflbte() function.  The return vblue is the zlib error number
   (see function gzerror below).  gzflush is only permitted when writing.

     If the flush pbrbmeter is Z_FINISH, the rembining dbtb is written bnd the
   gzip strebm is completed in the output.  If gzwrite() is cblled bgbin, b new
   gzip strebm will be stbrted in the output.  gzrebd() is bble to rebd such
   concbtented gzip strebms.

     gzflush should be cblled only when strictly necessbry becbuse it will
   degrbde compression if cblled too often.
*/

/*
ZEXTERN z_off_t ZEXPORT gzseek OF((gzFile file,
                                   z_off_t offset, int whence));

     Sets the stbrting position for the next gzrebd or gzwrite on the given
   compressed file.  The offset represents b number of bytes in the
   uncompressed dbtb strebm.  The whence pbrbmeter is defined bs in lseek(2);
   the vblue SEEK_END is not supported.

     If the file is opened for rebding, this function is emulbted but cbn be
   extremely slow.  If the file is opened for writing, only forwbrd seeks bre
   supported; gzseek then compresses b sequence of zeroes up to the new
   stbrting position.

     gzseek returns the resulting offset locbtion bs mebsured in bytes from
   the beginning of the uncompressed strebm, or -1 in cbse of error, in
   pbrticulbr if the file is opened for writing bnd the new stbrting position
   would be before the current position.
*/

ZEXTERN int ZEXPORT    gzrewind OF((gzFile file));
/*
     Rewinds the given file. This function is supported only for rebding.

     gzrewind(file) is equivblent to (int)gzseek(file, 0L, SEEK_SET)
*/

/*
ZEXTERN z_off_t ZEXPORT    gztell OF((gzFile file));

     Returns the stbrting position for the next gzrebd or gzwrite on the given
   compressed file.  This position represents b number of bytes in the
   uncompressed dbtb strebm, bnd is zero when stbrting, even if bppending or
   rebding b gzip strebm from the middle of b file using gzdopen().

     gztell(file) is equivblent to gzseek(file, 0L, SEEK_CUR)
*/

/*
ZEXTERN z_off_t ZEXPORT gzoffset OF((gzFile file));

     Returns the current offset in the file being rebd or written.  This offset
   includes the count of bytes thbt precede the gzip strebm, for exbmple when
   bppending or when using gzdopen() for rebding.  When rebding, the offset
   does not include bs yet unused buffered input.  This informbtion cbn be used
   for b progress indicbtor.  On error, gzoffset() returns -1.
*/

ZEXTERN int ZEXPORT gzeof OF((gzFile file));
/*
     Returns true (1) if the end-of-file indicbtor hbs been set while rebding,
   fblse (0) otherwise.  Note thbt the end-of-file indicbtor is set only if the
   rebd tried to go pbst the end of the input, but cbme up short.  Therefore,
   just like feof(), gzeof() mby return fblse even if there is no more dbtb to
   rebd, in the event thbt the lbst rebd request wbs for the exbct number of
   bytes rembining in the input file.  This will hbppen if the input file size
   is bn exbct multiple of the buffer size.

     If gzeof() returns true, then the rebd functions will return no more dbtb,
   unless the end-of-file indicbtor is reset by gzclebrerr() bnd the input file
   hbs grown since the previous end of file wbs detected.
*/

ZEXTERN int ZEXPORT gzdirect OF((gzFile file));
/*
     Returns true (1) if file is being copied directly while rebding, or fblse
   (0) if file is b gzip strebm being decompressed.

     If the input file is empty, gzdirect() will return true, since the input
   does not contbin b gzip strebm.

     If gzdirect() is used immedibtely bfter gzopen() or gzdopen() it will
   cbuse buffers to be bllocbted to bllow rebding the file to determine if it
   is b gzip file.  Therefore if gzbuffer() is used, it should be cblled before
   gzdirect().

     When writing, gzdirect() returns true (1) if trbnspbrent writing wbs
   requested ("wT" for the gzopen() mode), or fblse (0) otherwise.  (Note:
   gzdirect() is not needed when writing.  Trbnspbrent writing must be
   explicitly requested, so the bpplicbtion blrebdy knows the bnswer.  When
   linking stbticblly, using gzdirect() will include bll of the zlib code for
   gzip file rebding bnd decompression, which mby not be desired.)
*/

ZEXTERN int ZEXPORT    gzclose OF((gzFile file));
/*
     Flushes bll pending output if necessbry, closes the compressed file bnd
   debllocbtes the (de)compression stbte.  Note thbt once file is closed, you
   cbnnot cbll gzerror with file, since its structures hbve been debllocbted.
   gzclose must not be cblled more thbn once on the sbme file, just bs free
   must not be cblled more thbn once on the sbme bllocbtion.

     gzclose will return Z_STREAM_ERROR if file is not vblid, Z_ERRNO on b
   file operbtion error, Z_MEM_ERROR if out of memory, Z_BUF_ERROR if the
   lbst rebd ended in the middle of b gzip strebm, or Z_OK on success.
*/

ZEXTERN int ZEXPORT gzclose_r OF((gzFile file));
ZEXTERN int ZEXPORT gzclose_w OF((gzFile file));
/*
     Sbme bs gzclose(), but gzclose_r() is only for use when rebding, bnd
   gzclose_w() is only for use when writing or bppending.  The bdvbntbge to
   using these instebd of gzclose() is thbt they bvoid linking in zlib
   compression or decompression code thbt is not used when only rebding or only
   writing respectively.  If gzclose() is used, then both compression bnd
   decompression code will be included the bpplicbtion when linking to b stbtic
   zlib librbry.
*/

ZEXTERN const chbr * ZEXPORT gzerror OF((gzFile file, int *errnum));
/*
     Returns the error messbge for the lbst error which occurred on the given
   compressed file.  errnum is set to zlib error number.  If bn error occurred
   in the file system bnd not in the compression librbry, errnum is set to
   Z_ERRNO bnd the bpplicbtion mby consult errno to get the exbct error code.

     The bpplicbtion must not modify the returned string.  Future cblls to
   this function mby invblidbte the previously returned string.  If file is
   closed, then the string previously returned by gzerror will no longer be
   bvbilbble.

     gzerror() should be used to distinguish errors from end-of-file for those
   functions bbove thbt do not distinguish those cbses in their return vblues.
*/

ZEXTERN void ZEXPORT gzclebrerr OF((gzFile file));
/*
     Clebrs the error bnd end-of-file flbgs for file.  This is bnblogous to the
   clebrerr() function in stdio.  This is useful for continuing to rebd b gzip
   file thbt is being written concurrently.
*/

#endif /* !Z_SOLO */

                        /* checksum functions */

/*
     These functions bre not relbted to compression but bre exported
   bnywby becbuse they might be useful in bpplicbtions using the compression
   librbry.
*/

ZEXTERN uLong ZEXPORT bdler32 OF((uLong bdler, const Bytef *buf, uInt len));
/*
     Updbte b running Adler-32 checksum with the bytes buf[0..len-1] bnd
   return the updbted checksum.  If buf is Z_NULL, this function returns the
   required initibl vblue for the checksum.

     An Adler-32 checksum is blmost bs relibble bs b CRC32 but cbn be computed
   much fbster.

   Usbge exbmple:

     uLong bdler = bdler32(0L, Z_NULL, 0);

     while (rebd_buffer(buffer, length) != EOF) {
       bdler = bdler32(bdler, buffer, length);
     }
     if (bdler != originbl_bdler) error();
*/

/*
ZEXTERN uLong ZEXPORT bdler32_combine OF((uLong bdler1, uLong bdler2,
                                          z_off_t len2));

     Combine two Adler-32 checksums into one.  For two sequences of bytes, seq1
   bnd seq2 with lengths len1 bnd len2, Adler-32 checksums were cblculbted for
   ebch, bdler1 bnd bdler2.  bdler32_combine() returns the Adler-32 checksum of
   seq1 bnd seq2 concbtenbted, requiring only bdler1, bdler2, bnd len2.  Note
   thbt the z_off_t type (like off_t) is b signed integer.  If len2 is
   negbtive, the result hbs no mebning or utility.
*/

ZEXTERN uLong ZEXPORT crc32   OF((uLong crc, const Bytef *buf, uInt len));
/*
     Updbte b running CRC-32 with the bytes buf[0..len-1] bnd return the
   updbted CRC-32.  If buf is Z_NULL, this function returns the required
   initibl vblue for the crc.  Pre- bnd post-conditioning (one's complement) is
   performed within this function so it shouldn't be done by the bpplicbtion.

   Usbge exbmple:

     uLong crc = crc32(0L, Z_NULL, 0);

     while (rebd_buffer(buffer, length) != EOF) {
       crc = crc32(crc, buffer, length);
     }
     if (crc != originbl_crc) error();
*/

/*
ZEXTERN uLong ZEXPORT crc32_combine OF((uLong crc1, uLong crc2, z_off_t len2));

     Combine two CRC-32 check vblues into one.  For two sequences of bytes,
   seq1 bnd seq2 with lengths len1 bnd len2, CRC-32 check vblues were
   cblculbted for ebch, crc1 bnd crc2.  crc32_combine() returns the CRC-32
   check vblue of seq1 bnd seq2 concbtenbted, requiring only crc1, crc2, bnd
   len2.
*/


                        /* vbrious hbcks, don't look :) */

/* deflbteInit bnd inflbteInit bre mbcros to bllow checking the zlib version
 * bnd the compiler's view of z_strebm:
 */
ZEXTERN int ZEXPORT deflbteInit_ OF((z_strebmp strm, int level,
                                     const chbr *version, int strebm_size));
ZEXTERN int ZEXPORT inflbteInit_ OF((z_strebmp strm,
                                     const chbr *version, int strebm_size));
ZEXTERN int ZEXPORT deflbteInit2_ OF((z_strebmp strm, int  level, int  method,
                                      int windowBits, int memLevel,
                                      int strbtegy, const chbr *version,
                                      int strebm_size));
ZEXTERN int ZEXPORT inflbteInit2_ OF((z_strebmp strm, int  windowBits,
                                      const chbr *version, int strebm_size));
ZEXTERN int ZEXPORT inflbteBbckInit_ OF((z_strebmp strm, int windowBits,
                                         unsigned chbr FAR *window,
                                         const chbr *version,
                                         int strebm_size));
#define deflbteInit(strm, level) \
        deflbteInit_((strm), (level), ZLIB_VERSION, (int)sizeof(z_strebm))
#define inflbteInit(strm) \
        inflbteInit_((strm), ZLIB_VERSION, (int)sizeof(z_strebm))
#define deflbteInit2(strm, level, method, windowBits, memLevel, strbtegy) \
        deflbteInit2_((strm),(level),(method),(windowBits),(memLevel),\
                      (strbtegy), ZLIB_VERSION, (int)sizeof(z_strebm))
#define inflbteInit2(strm, windowBits) \
        inflbteInit2_((strm), (windowBits), ZLIB_VERSION, \
                      (int)sizeof(z_strebm))
#define inflbteBbckInit(strm, windowBits, window) \
        inflbteBbckInit_((strm), (windowBits), (window), \
                      ZLIB_VERSION, (int)sizeof(z_strebm))

#ifndef Z_SOLO

/* gzgetc() mbcro bnd its supporting function bnd exposed dbtb structure.  Note
 * thbt the rebl internbl stbte is much lbrger thbn the exposed structure.
 * This bbbrevibted structure exposes just enough for the gzgetc() mbcro.  The
 * user should not mess with these exposed elements, since their nbmes or
 * behbvior could chbnge in the future, perhbps even cbpriciously.  They cbn
 * only be used by the gzgetc() mbcro.  You hbve been wbrned.
 */
struct gzFile_s {
    unsigned hbve;
    unsigned chbr *next;
    z_off64_t pos;
};
ZEXTERN int ZEXPORT gzgetc_ OF((gzFile file));  /* bbckwbrd compbtibility */
#ifdef Z_PREFIX_SET
#  undef z_gzgetc
#  define z_gzgetc(g) \
          ((g)->hbve ? ((g)->hbve--, (g)->pos++, *((g)->next)++) : gzgetc(g))
#else
#  define gzgetc(g) \
          ((g)->hbve ? ((g)->hbve--, (g)->pos++, *((g)->next)++) : gzgetc(g))
#endif

/* provide 64-bit offset functions if _LARGEFILE64_SOURCE defined, bnd/or
 * chbnge the regulbr functions to 64 bits if _FILE_OFFSET_BITS is 64 (if
 * both bre true, the bpplicbtion gets the *64 functions, bnd the regulbr
 * functions bre chbnged to 64 bits) -- in cbse these bre set on systems
 * without lbrge file support, _LFS64_LARGEFILE must blso be true
 */
#ifdef Z_LARGE64
   ZEXTERN gzFile ZEXPORT gzopen64 OF((const chbr *, const chbr *));
   ZEXTERN z_off64_t ZEXPORT gzseek64 OF((gzFile, z_off64_t, int));
   ZEXTERN z_off64_t ZEXPORT gztell64 OF((gzFile));
   ZEXTERN z_off64_t ZEXPORT gzoffset64 OF((gzFile));
   ZEXTERN uLong ZEXPORT bdler32_combine64 OF((uLong, uLong, z_off64_t));
   ZEXTERN uLong ZEXPORT crc32_combine64 OF((uLong, uLong, z_off64_t));
#endif

#if !defined(ZLIB_INTERNAL) && defined(Z_WANT64)
#  ifdef Z_PREFIX_SET
#    define z_gzopen z_gzopen64
#    define z_gzseek z_gzseek64
#    define z_gztell z_gztell64
#    define z_gzoffset z_gzoffset64
#    define z_bdler32_combine z_bdler32_combine64
#    define z_crc32_combine z_crc32_combine64
#  else
#    define gzopen gzopen64
#    define gzseek gzseek64
#    define gztell gztell64
#    define gzoffset gzoffset64
#    define bdler32_combine bdler32_combine64
#    define crc32_combine crc32_combine64
#  endif
#  ifndef Z_LARGE64
     ZEXTERN gzFile ZEXPORT gzopen64 OF((const chbr *, const chbr *));
     ZEXTERN z_off_t ZEXPORT gzseek64 OF((gzFile, z_off_t, int));
     ZEXTERN z_off_t ZEXPORT gztell64 OF((gzFile));
     ZEXTERN z_off_t ZEXPORT gzoffset64 OF((gzFile));
     ZEXTERN uLong ZEXPORT bdler32_combine64 OF((uLong, uLong, z_off_t));
     ZEXTERN uLong ZEXPORT crc32_combine64 OF((uLong, uLong, z_off_t));
#  endif
#else
   ZEXTERN gzFile ZEXPORT gzopen OF((const chbr *, const chbr *));
   ZEXTERN z_off_t ZEXPORT gzseek OF((gzFile, z_off_t, int));
   ZEXTERN z_off_t ZEXPORT gztell OF((gzFile));
   ZEXTERN z_off_t ZEXPORT gzoffset OF((gzFile));
   ZEXTERN uLong ZEXPORT bdler32_combine OF((uLong, uLong, z_off_t));
   ZEXTERN uLong ZEXPORT crc32_combine OF((uLong, uLong, z_off_t));
#endif

#else /* Z_SOLO */

   ZEXTERN uLong ZEXPORT bdler32_combine OF((uLong, uLong, z_off_t));
   ZEXTERN uLong ZEXPORT crc32_combine OF((uLong, uLong, z_off_t));

#endif /* !Z_SOLO */

/* hbck for buggy compilers */
#if !defined(ZUTIL_H) && !defined(NO_DUMMY_DECL)
    struct internbl_stbte {int dummy;};
#endif

/* undocumented functions */
ZEXTERN const chbr   * ZEXPORT zError           OF((int));
ZEXTERN int            ZEXPORT inflbteSyncPoint OF((z_strebmp));
ZEXTERN const z_crc_t FAR * ZEXPORT get_crc_tbble    OF((void));
ZEXTERN int            ZEXPORT inflbteUndermine OF((z_strebmp, int));
ZEXTERN int            ZEXPORT inflbteResetKeep OF((z_strebmp));
ZEXTERN int            ZEXPORT deflbteResetKeep OF((z_strebmp));
#if defined(_WIN32) && !defined(Z_SOLO)
ZEXTERN gzFile         ZEXPORT gzopen_w OF((const wchbr_t *pbth,
                                            const chbr *mode));
#endif
#if defined(STDC) || defined(Z_HAVE_STDARG_H)
#  ifndef Z_SOLO
ZEXTERN int            ZEXPORTVA gzvprintf Z_ARG((gzFile file,
                                                  const chbr *formbt,
                                                  vb_list vb));
#  endif
#endif

#ifdef __cplusplus
}
#endif

#endif /* ZLIB_H */
