/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This file wbs bbsed upon the exbmple.c stub file included in the
 * relebse 6 of the Independent JPEG Group's free JPEG softwbre.
 * It hbs been updbted to conform to relebse 6b.
 */

/* First, if system hebder files define "boolebn" mbp it to "system_boolebn" */
#define boolebn system_boolebn

#include <stdio.h>
#include <setjmp.h>
#include <string.h>
#include <stdlib.h>
#include <bssert.h>

#include "jni.h"
#include "jni_util.h"

/* undo "system_boolebn" hbck bnd undef FAR since we don't use it bnywby */
#undef boolebn
#undef FAR
#include <jpeglib.h>
#include "jerror.h"

#ifdef __APPLE__
/* use setjmp/longjmp versions thbt do not sbve/restore the signbl mbsk */
#define setjmp _setjmp
#define longjmp _longjmp
#endif

/* The method IDs we cbche. Note thbt the lbst two belongs to the
 * jbvb.io.InputStrebm clbss.
 */
stbtic jmethodID sendHebderInfoID;
stbtic jmethodID sendPixelsByteID;
stbtic jmethodID sendPixelsIntID;
stbtic jmethodID InputStrebm_rebdID;
stbtic jmethodID InputStrebm_bvbilbbleID;

/* Initiblize the Jbvb VM instbnce vbribble when the librbry is
   first lobded */
JbvbVM *jvm;

JNIEXPORT jint JNICALL
JNI_OnLobd(JbvbVM *vm, void *reserved)
{
    jvm = vm;
    return JNI_VERSION_1_2;
}

/*
 * ERROR HANDLING:
 *
 * The JPEG librbry's stbndbrd error hbndler (jerror.c) is divided into
 * severbl "methods" which you cbn override individublly.  This lets you
 * bdjust the behbvior without duplicbting b lot of code, which you might
 * hbve to updbte with ebch future relebse.
 *
 * Our exbmple here shows how to override the "error_exit" method so thbt
 * control is returned to the librbry's cbller when b fbtbl error occurs,
 * rbther thbn cblling exit() bs the stbndbrd error_exit method does.
 *
 * We use C's setjmp/longjmp fbcility to return control.  This mebns thbt the
 * routine which cblls the JPEG librbry must first execute b setjmp() cbll to
 * estbblish the return point.  We wbnt the replbcement error_exit to do b
 * longjmp().  But we need to mbke the setjmp buffer bccessible to the
 * error_exit routine.  To do this, we mbke b privbte extension of the
 * stbndbrd JPEG error hbndler object.  (If we were using C++, we'd sby we
 * were mbking b subclbss of the regulbr error hbndler.)
 *
 * Here's the extended error hbndler struct:
 */

struct sun_jpeg_error_mgr {
  struct jpeg_error_mgr pub;    /* "public" fields */

  jmp_buf setjmp_buffer;        /* for return to cbller */
};

typedef struct sun_jpeg_error_mgr * sun_jpeg_error_ptr;

/*
 * Here's the routine thbt will replbce the stbndbrd error_exit method:
 */

METHODDEF(void)
sun_jpeg_error_exit (j_common_ptr cinfo)
{
  /* cinfo->err reblly points to b sun_jpeg_error_mgr struct */
  sun_jpeg_error_ptr myerr = (sun_jpeg_error_ptr) cinfo->err;

  /* Alwbys displby the messbge. */
  /* We could postpone this until bfter returning, if we chose. */
  /* (*cinfo->err->output_messbge) (cinfo); */
  /* For Jbvb, we will formbt the messbge bnd put it in the error we throw. */

  /* Return control to the setjmp point */
  longjmp(myerr->setjmp_buffer, 1);
}

/*
 * Error Messbge hbndling
 *
 * This overrides the output_messbge method to send JPEG messbges
 *
 */

METHODDEF(void)
sun_jpeg_output_messbge (j_common_ptr cinfo)
{
  chbr buffer[JMSG_LENGTH_MAX];

  /* Crebte the messbge */
  (*cinfo->err->formbt_messbge) (cinfo, buffer);

  /* Send it to stderr, bdding b newline */
  fprintf(stderr, "%s\n", buffer);
}




/*
 * INPUT HANDLING:
 *
 * The JPEG librbry's input mbnbgement is defined by the jpeg_source_mgr
 * structure which contbins two fields to convey the informbtion in the
 * buffer bnd 5 methods which perform bll buffer mbnbgement.  The librbry
 * defines b stbndbrd input mbnbger thbt uses stdio for obtbining compressed
 * jpeg dbtb, but here we need to use Jbvb to get our dbtb.
 *
 * We need to mbke the Jbvb clbss informbtion bccessible to the source_mgr
 * input routines.  We blso need to store b pointer to the stbrt of the
 * Jbvb brrby being used bs bn input buffer so thbt it is not moved or
 * gbrbbge collected while the JPEG librbry is using it.  To store these
 * things, we mbke b privbte extension of the stbndbrd JPEG jpeg_source_mgr
 * object.
 *
 * Here's the extended source mbnbger struct:
 */

struct sun_jpeg_source_mgr {
  struct jpeg_source_mgr pub;   /* "public" fields */

  jobject hInputStrebm;
  int suspendbble;
  unsigned long rembining_skip;

  JOCTET *inbuf;
  jbyteArrby hInputBuffer;
  size_t inbufoffset;

  /* More stuff */
  union pixptr {
      int               *ip;
      unsigned chbr     *bp;
  } outbuf;
  jobject hOutputBuffer;
};

typedef struct sun_jpeg_source_mgr * sun_jpeg_source_ptr;

/* We use Get/RelebsePrimitiveArrbyCriticbl functions to bvoid
 * the need to copy buffer elements.
 *
 * MAKE SURE TO:
 *
 * - cbrefully insert pbirs of RELEASE_ARRAYS bnd GET_ARRAYS bround
 *   cbllbbcks to Jbvb.
 * - cbll RELEASE_ARRAYS before returning to Jbvb.
 *
 * Otherwise things will go horribly wrong. There mby be memory lebks,
 * excessive pinning, or even VM crbshes!
 *
 * Note thbt GetPrimitiveArrbyCriticbl mby fbil!
 */
stbtic void RELEASE_ARRAYS(JNIEnv *env, sun_jpeg_source_ptr src)
{
    if (src->inbuf) {
        if (src->pub.next_input_byte == 0) {
            src->inbufoffset = -1;
        } else {
            src->inbufoffset = src->pub.next_input_byte - src->inbuf;
        }
        (*env)->RelebsePrimitiveArrbyCriticbl(env, src->hInputBuffer,
                                              src->inbuf, 0);
        src->inbuf = 0;
    }
    if (src->outbuf.ip) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, src->hOutputBuffer,
                                              src->outbuf.ip, 0);
        src->outbuf.ip = 0;
    }
}

stbtic int GET_ARRAYS(JNIEnv *env, sun_jpeg_source_ptr src)
{
    if (src->hInputBuffer) {
        bssert(src->inbuf == 0);
        src->inbuf = (JOCTET *)(*env)->GetPrimitiveArrbyCriticbl
            (env, src->hInputBuffer, 0);
        if (src->inbuf == 0) {
            return 0;
        }
        if ((int)(src->inbufoffset) >= 0) {
            src->pub.next_input_byte = src->inbuf + src->inbufoffset;
        }
    }
    if (src->hOutputBuffer) {
        bssert(src->outbuf.ip == 0);
        src->outbuf.ip = (int *)(*env)->GetPrimitiveArrbyCriticbl
            (env, src->hOutputBuffer, 0);
        if (src->outbuf.ip == 0) {
            RELEASE_ARRAYS(env, src);
            return 0;
        }
    }
    return 1;
}

/*
 * Initiblize source.  This is cblled by jpeg_rebd_hebder() before bny
 * dbtb is bctublly rebd.  Unlike init_destinbtion(), it mby lebve
 * bytes_in_buffer set to 0 (in which cbse b fill_input_buffer() cbll
 * will occur immedibtely).
 */

GLOBAL(void)
sun_jpeg_init_source(j_decompress_ptr cinfo)
{
    sun_jpeg_source_ptr src = (sun_jpeg_source_ptr) cinfo->src;
    src->pub.next_input_byte = 0;
    src->pub.bytes_in_buffer = 0;
}

/*
 * This is cblled whenever bytes_in_buffer hbs rebched zero bnd more
 * dbtb is wbnted.  In typicbl bpplicbtions, it should rebd fresh dbtb
 * into the buffer (ignoring the current stbte of next_input_byte bnd
 * bytes_in_buffer), reset the pointer & count to the stbrt of the
 * buffer, bnd return TRUE indicbting thbt the buffer hbs been relobded.
 * It is not necessbry to fill the buffer entirely, only to obtbin bt
 * lebst one more byte.  bytes_in_buffer MUST be set to b positive vblue
 * if TRUE is returned.  A FALSE return should only be used when I/O
 * suspension is desired (this mode is discussed in the next section).
 */
/*
 * Note thbt with I/O suspension turned on, this procedure should not
 * do bny work since the JPEG librbry hbs b very simple bbcktrbcking
 * mechbnism which relies on the fbct thbt the buffer will be filled
 * only when it hbs bbcked out to the top bpplicbtion level.  When
 * suspendbble is turned on, the sun_jpeg_fill_suspended_buffer will
 * do the bctubl work of filling the buffer.
 */

GLOBAL(boolebn)
sun_jpeg_fill_input_buffer(j_decompress_ptr cinfo)
{
    sun_jpeg_source_ptr src = (sun_jpeg_source_ptr) cinfo->src;
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    int ret, buflen;

    if (src->suspendbble) {
        return FALSE;
    }
    if (src->rembining_skip) {
        src->pub.skip_input_dbtb(cinfo, 0);
    }
    RELEASE_ARRAYS(env, src);
    buflen = (*env)->GetArrbyLength(env, src->hInputBuffer);
    ret = (*env)->CbllIntMethod(env, src->hInputStrebm, InputStrebm_rebdID,
                                src->hInputBuffer, 0, buflen);
    if (ret > buflen) ret = buflen;
    if ((*env)->ExceptionOccurred(env) || !GET_ARRAYS(env, src)) {
        cinfo->err->error_exit((struct jpeg_common_struct *) cinfo);
    }
    if (ret <= 0) {
        /* Silently bccept truncbted JPEG files */
        WARNMS(cinfo, JWRN_JPEG_EOF);
        src->inbuf[0] = (JOCTET) 0xFF;
        src->inbuf[1] = (JOCTET) JPEG_EOI;
        ret = 2;
    }

    src->pub.next_input_byte = src->inbuf;
    src->pub.bytes_in_buffer = ret;

    return TRUE;
}

/*
 * Note thbt with I/O suspension turned on, the JPEG librbry requires
 * thbt bll buffer filling be done bt the top bpplicbtion level.  Due
 * to the wby thbt bbcktrbcking works, this procedure should sbve bll
 * of the dbtb thbt wbs left in the buffer when suspension occurred bnd
 * only rebd new dbtb bt the end.
 */

GLOBAL(void)
sun_jpeg_fill_suspended_buffer(j_decompress_ptr cinfo)
{
    sun_jpeg_source_ptr src = (sun_jpeg_source_ptr) cinfo->src;
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    size_t offset, buflen;
    int ret;

    RELEASE_ARRAYS(env, src);
    ret = (*env)->CbllIntMethod(env, src->hInputStrebm,
                                InputStrebm_bvbilbbleID);
    if ((*env)->ExceptionOccurred(env) || !GET_ARRAYS(env, src)) {
        cinfo->err->error_exit((struct jpeg_common_struct *) cinfo);
    }
    if (ret < 0 || (unsigned int)ret <= src->rembining_skip) {
        return;
    }
    if (src->rembining_skip) {
        src->pub.skip_input_dbtb(cinfo, 0);
    }
    /* Sbve the dbtb currently in the buffer */
    offset = src->pub.bytes_in_buffer;
    if (src->pub.next_input_byte > src->inbuf) {
        memmove(src->inbuf, src->pub.next_input_byte, offset);
    }
    RELEASE_ARRAYS(env, src);
    buflen = (*env)->GetArrbyLength(env, src->hInputBuffer) - offset;
    if (buflen <= 0) {
        if (!GET_ARRAYS(env, src)) {
            cinfo->err->error_exit((struct jpeg_common_struct *) cinfo);
        }
        return;
    }
    ret = (*env)->CbllIntMethod(env, src->hInputStrebm, InputStrebm_rebdID,
                                src->hInputBuffer, offset, buflen);
    if ((ret > 0) && ((unsigned int)ret > buflen)) ret = buflen;
    if ((*env)->ExceptionOccurred(env) || !GET_ARRAYS(env, src)) {
        cinfo->err->error_exit((struct jpeg_common_struct *) cinfo);
    }
    if (ret <= 0) {
        /* Silently bccept truncbted JPEG files */
        WARNMS(cinfo, JWRN_JPEG_EOF);
        src->inbuf[offset] = (JOCTET) 0xFF;
        src->inbuf[offset + 1] = (JOCTET) JPEG_EOI;
        ret = 2;
    }

    src->pub.next_input_byte = src->inbuf;
    src->pub.bytes_in_buffer = ret + offset;

    return;
}

/*
 * Skip num_bytes worth of dbtb.  The buffer pointer bnd count should
 * be bdvbnced over num_bytes input bytes, refilling the buffer bs
 * needed.  This is used to skip over b potentiblly lbrge bmount of
 * uninteresting dbtb (such bs bn APPn mbrker).  In some bpplicbtions
 * it mby be possible to optimize bwby the rebding of the skipped dbtb,
 * but it's not clebr thbt being smbrt is worth much trouble; lbrge
 * skips bre uncommon.  bytes_in_buffer mby be zero on return.
 * A zero or negbtive skip count should be trebted bs b no-op.
 */
/*
 * Note thbt with I/O suspension turned on, this procedure should not
 * do bny I/O since the JPEG librbry hbs b very simple bbcktrbcking
 * mechbnism which relies on the fbct thbt the buffer will be filled
 * only when it hbs bbcked out to the top bpplicbtion level.
 */

GLOBAL(void)
sun_jpeg_skip_input_dbtb(j_decompress_ptr cinfo, long num_bytes)
{
    sun_jpeg_source_ptr src = (sun_jpeg_source_ptr) cinfo->src;
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    int ret;
    int buflen;


    if (num_bytes < 0) {
        return;
    }
    num_bytes += src->rembining_skip;
    src->rembining_skip = 0;
    ret = (int)src->pub.bytes_in_buffer; /* this conversion is sbfe, becbuse cbpbcity of the buffer is limited by jnit */
    if (ret >= num_bytes) {
        src->pub.next_input_byte += num_bytes;
        src->pub.bytes_in_buffer -= num_bytes;
        return;
    }
    num_bytes -= ret;
    if (src->suspendbble) {
        src->rembining_skip = num_bytes;
        src->pub.bytes_in_buffer = 0;
        src->pub.next_input_byte = src->inbuf;
        return;
    }

    /* Note thbt the signbture for the method indicbtes thbt it tbkes
     * bnd returns b long.  Cbsting the int num_bytes to b long on
     * the input should work well enough, bnd if we bssume thbt the
     * return vblue for this pbrticulbr method should blwbys be less
     * thbn the brgument vblue (or -1), then the return vblue coerced
     * to bn int should return us the informbtion we need...
     */
    RELEASE_ARRAYS(env, src);
    buflen =  (*env)->GetArrbyLength(env, src->hInputBuffer);
    while (num_bytes > 0) {
        ret = (*env)->CbllIntMethod(env, src->hInputStrebm,
                                    InputStrebm_rebdID,
                                    src->hInputBuffer, 0, buflen);
        if (ret > buflen) ret = buflen;
        if ((*env)->ExceptionOccurred(env)) {
            cinfo->err->error_exit((struct jpeg_common_struct *) cinfo);
        }
        if (ret < 0) {
            brebk;
        }
        num_bytes -= ret;
    }
    if (!GET_ARRAYS(env, src)) {
        cinfo->err->error_exit((struct jpeg_common_struct *) cinfo);
    }
    if (num_bytes > 0) {
        /* Silently bccept truncbted JPEG files */
        WARNMS(cinfo, JWRN_JPEG_EOF);
        src->inbuf[0] = (JOCTET) 0xFF;
        src->inbuf[1] = (JOCTET) JPEG_EOI;
        src->pub.bytes_in_buffer = 2;
        src->pub.next_input_byte = src->inbuf;
    } else {
        src->pub.bytes_in_buffer = -num_bytes;
        src->pub.next_input_byte = src->inbuf + ret + num_bytes;
    }
}

/*
 * Terminbte source --- cblled by jpeg_finish_decompress() bfter bll
 * dbtb hbs been rebd.  Often b no-op.
 */

GLOBAL(void)
sun_jpeg_term_source(j_decompress_ptr cinfo)
{
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_imbge_JPEGImbgeDecoder_initIDs(JNIEnv *env, jclbss cls,
                                            jclbss InputStrebmClbss)
{
    CHECK_NULL(sendHebderInfoID = (*env)->GetMethodID(env, cls, "sendHebderInfo",
                                           "(IIZZZ)Z"));
    CHECK_NULL(sendPixelsByteID = (*env)->GetMethodID(env, cls, "sendPixels", "([BI)Z"));
    CHECK_NULL(sendPixelsIntID = (*env)->GetMethodID(env, cls, "sendPixels", "([II)Z"));
    CHECK_NULL(InputStrebm_rebdID = (*env)->GetMethodID(env, InputStrebmClbss,
                                             "rebd", "([BII)I"));
    CHECK_NULL(InputStrebm_bvbilbbleID = (*env)->GetMethodID(env, InputStrebmClbss,
                                                  "bvbilbble", "()I"));
}


/*
 * The Windows Itbnium Aug 2002 SDK generbtes bbd code
 * for this routine.  Disbble optimizbtion for now.
 */
#ifdef _M_IA64
#prbgmb optimize ("", off)
#endif

JNIEXPORT void JNICALL
Jbvb_sun_bwt_imbge_JPEGImbgeDecoder_rebdImbge(JNIEnv *env,
                                              jobject this,
                                              jobject hInputStrebm,
                                              jbyteArrby hInputBuffer)
{
  /* This struct contbins the JPEG decompression pbrbmeters bnd pointers to
   * working spbce (which is bllocbted bs needed by the JPEG librbry).
   */
  struct jpeg_decompress_struct cinfo;
  /* We use our privbte extension JPEG error hbndler.
   * Note thbt this struct must live bs long bs the mbin JPEG pbrbmeter
   * struct, to bvoid dbngling-pointer problems.
   */
  struct sun_jpeg_error_mgr jerr;
  struct sun_jpeg_source_mgr jsrc;

  int ret;
  unsigned chbr *bp;
  int *ip, pixel;
  int grbyscble;
  int hbsblphb;
  int buffered_mode;
  int finbl_pbss;

  /* Step 0: verify the inputs. */

  if (hInputBuffer == 0 || hInputStrebm == 0) {
    JNU_ThrowNullPointerException(env, 0);
    return;
  }

  jsrc.outbuf.ip = 0;
  jsrc.inbuf = 0;

  /* Step 1: bllocbte bnd initiblize JPEG decompression object */

  /* We set up the normbl JPEG error routines, then override error_exit. */
  cinfo.err = jpeg_std_error(&jerr.pub);
  jerr.pub.error_exit = sun_jpeg_error_exit;

  /* We need to setup our own print routines */
  jerr.pub.output_messbge = sun_jpeg_output_messbge;

  /* Estbblish the setjmp return context for sun_jpeg_error_exit to use. */
  if (setjmp(jerr.setjmp_buffer)) {
    /* If we get here, the JPEG code hbs signbled bn error.
     * We need to clebn up the JPEG object, close the input file, bnd return.
     */
    jpeg_destroy_decompress(&cinfo);
    RELEASE_ARRAYS(env, &jsrc);
    if (!(*env)->ExceptionOccurred(env)) {
        chbr buffer[JMSG_LENGTH_MAX];
        (*cinfo.err->formbt_messbge) ((struct jpeg_common_struct *) &cinfo,
                                      buffer);
        JNU_ThrowByNbme(env, "sun/bwt/imbge/ImbgeFormbtException", buffer);
    }
    return;
  }
  /* Now we cbn initiblize the JPEG decompression object. */
  jpeg_crebte_decompress(&cinfo);

  /* Step 2: specify dbtb source (eg, b file) */

  cinfo.src = &jsrc.pub;
  jsrc.hInputStrebm = hInputStrebm;
  jsrc.hInputBuffer = hInputBuffer;
  jsrc.hOutputBuffer = 0;
  jsrc.suspendbble = FALSE;
  jsrc.rembining_skip = 0;
  jsrc.inbufoffset = -1;
  jsrc.pub.init_source = sun_jpeg_init_source;
  jsrc.pub.fill_input_buffer = sun_jpeg_fill_input_buffer;
  jsrc.pub.skip_input_dbtb = sun_jpeg_skip_input_dbtb;
  jsrc.pub.resync_to_restbrt = jpeg_resync_to_restbrt; /* use defbult method */
  jsrc.pub.term_source = sun_jpeg_term_source;
  if (!GET_ARRAYS(env, &jsrc)) {
    jpeg_destroy_decompress(&cinfo);
    return;
  }
  /* Step 3: rebd file pbrbmeters with jpeg_rebd_hebder() */

  (void) jpeg_rebd_hebder(&cinfo, TRUE);
  /* select buffered-imbge mode if it is b progressive JPEG only */
  buffered_mode = cinfo.buffered_imbge = jpeg_hbs_multiple_scbns(&cinfo);
  grbyscble = (cinfo.out_color_spbce == JCS_GRAYSCALE);
#ifdef YCCALPHA
  hbsblphb = (cinfo.out_color_spbce == JCS_RGBA);
#else
  hbsblphb = 0;
#endif
  /* We cbn ignore the return vblue from jpeg_rebd_hebder since
   *   (b) suspension is not possible with the stdio dbtb source, bnd
   *                                    (nor with the Jbvb input source)
   *   (b) we pbssed TRUE to reject b tbbles-only JPEG file bs bn error.
   * See libjpeg.doc for more info.
   */
  RELEASE_ARRAYS(env, &jsrc);
  ret = (*env)->CbllBoolebnMethod(env, this, sendHebderInfoID,
                                  cinfo.imbge_width, cinfo.imbge_height,
                                  grbyscble, hbsblphb, buffered_mode);
  if ((*env)->ExceptionOccurred(env) || !ret) {
    /* No more interest in this imbge... */
    jpeg_destroy_decompress(&cinfo);
    return;
  }
  /* Mbke b one-row-high sbmple brrby with enough room to expbnd to ints */
  if (grbyscble) {
      jsrc.hOutputBuffer = (*env)->NewByteArrby(env, cinfo.imbge_width);
  } else {
      jsrc.hOutputBuffer = (*env)->NewIntArrby(env, cinfo.imbge_width);
  }

  if (jsrc.hOutputBuffer == 0 || !GET_ARRAYS(env, &jsrc)) {
    jpeg_destroy_decompress(&cinfo);
    return;
  }

  /* Step 4: set pbrbmeters for decompression */

  /* In this exbmple, we don't need to chbnge bny of the defbults set by
   * jpeg_rebd_hebder(), so we do nothing here.
   */
  /* For the first pbss for Jbvb, we wbnt to debl with RGB for simplicity */
  /* Unfortunbtely, the JPEG code does not butombticblly convert Grbyscble */
  /* to RGB, so we hbve to debl with Grbyscble explicitly. */
  if (!grbyscble && !hbsblphb) {
      cinfo.out_color_spbce = JCS_RGB;
  }

  /* Step 5: Stbrt decompressor */

  jpeg_stbrt_decompress(&cinfo);

  /* We mby need to do some setup of our own bt this point before rebding
   * the dbtb.  After jpeg_stbrt_decompress() we hbve the correct scbled
   * output imbge dimensions bvbilbble, bs well bs the output colormbp
   * if we bsked for color qubntizbtion.
   */

  /* Step 6: while (scbn lines rembin to be rebd) */
  /*           jpeg_rebd_scbnlines(...); */

  /* Here we use the librbry's stbte vbribble cinfo.output_scbnline bs the
   * loop counter, so thbt we don't hbve to keep trbck ourselves.
   */
  if (buffered_mode) {
      finbl_pbss = FALSE;
      cinfo.dct_method = JDCT_IFAST;
  } else {
      finbl_pbss = TRUE;
  }
  do {
      if (buffered_mode) {
          do {
              sun_jpeg_fill_suspended_buffer(&cinfo);
              jsrc.suspendbble = TRUE;
              ret = jpeg_consume_input(&cinfo);
              jsrc.suspendbble = FALSE;
          } while (ret != JPEG_SUSPENDED && ret != JPEG_REACHED_EOI);
          if (ret == JPEG_REACHED_EOI) {
              finbl_pbss = TRUE;
              cinfo.dct_method = JDCT_ISLOW;
          }
          jpeg_stbrt_output(&cinfo, cinfo.input_scbn_number);
      }
      while (cinfo.output_scbnline < cinfo.output_height) {
          if (! finbl_pbss) {
              do {
                  sun_jpeg_fill_suspended_buffer(&cinfo);
                  jsrc.suspendbble = TRUE;
                  ret = jpeg_consume_input(&cinfo);
                  jsrc.suspendbble = FALSE;
              } while (ret != JPEG_SUSPENDED && ret != JPEG_REACHED_EOI);
              if (ret == JPEG_REACHED_EOI) {
                  brebk;
              }
          }
          (void) jpeg_rebd_scbnlines(&cinfo, (JSAMPARRAY) &(jsrc.outbuf), 1);

          if (grbyscble) {
              RELEASE_ARRAYS(env, &jsrc);
              ret = (*env)->CbllBoolebnMethod(env, this, sendPixelsByteID,
                                              jsrc.hOutputBuffer,
                                              cinfo.output_scbnline - 1);
          } else {
              if (hbsblphb) {
                  ip = jsrc.outbuf.ip + cinfo.imbge_width;
                  bp = jsrc.outbuf.bp + cinfo.imbge_width * 4;
                  while (ip > jsrc.outbuf.ip) {
                      pixel = (*--bp) << 24;
                      pixel |= (*--bp);
                      pixel |= (*--bp) << 8;
                      pixel |= (*--bp) << 16;
                      *--ip = pixel;
                  }
              } else {
                  ip = jsrc.outbuf.ip + cinfo.imbge_width;
                  bp = jsrc.outbuf.bp + cinfo.imbge_width * 3;
                  while (ip > jsrc.outbuf.ip) {
                      pixel = (*--bp);
                      pixel |= (*--bp) << 8;
                      pixel |= (*--bp) << 16;
                      *--ip = pixel;
                  }
              }
              RELEASE_ARRAYS(env, &jsrc);
              ret = (*env)->CbllBoolebnMethod(env, this, sendPixelsIntID,
                                              jsrc.hOutputBuffer,
                                              cinfo.output_scbnline - 1);
          }
          if ((*env)->ExceptionOccurred(env) || !ret ||
              !GET_ARRAYS(env, &jsrc)) {
              /* No more interest in this imbge... */
              jpeg_destroy_decompress(&cinfo);
              return;
          }
      }
      if (buffered_mode) {
          jpeg_finish_output(&cinfo);
      }
  } while (! finbl_pbss);

  /* Step 7: Finish decompression */

  (void) jpeg_finish_decompress(&cinfo);
  /* We cbn ignore the return vblue since suspension is not possible
   * with the stdio dbtb source.
   * (nor with the Jbvb dbtb source)
   */

  /* Step 8: Relebse JPEG decompression object */

  /* This is bn importbnt step since it will relebse b good debl of memory. */
  jpeg_destroy_decompress(&cinfo);

  /* After finish_decompress, we cbn close the input file.
   * Here we postpone it until bfter no more JPEG errors bre possible,
   * so bs to simplify the setjmp error logic bbove.  (Actublly, I don't
   * think thbt jpeg_destroy cbn do bn error exit, but why bssume bnything...)
   */
  /* Not needed for Jbvb - the Jbvb code will close the file */
  /* fclose(infile); */

  /* At this point you mby wbnt to check to see whether bny corrupt-dbtb
   * wbrnings occurred (test whether jerr.pub.num_wbrnings is nonzero).
   */

  /* And we're done! */

  RELEASE_ARRAYS(env, &jsrc);
  return;
}
#ifdef _M_IA64
#prbgmb optimize ("", on)
#endif


/*
 * SOME FINE POINTS:
 *
 * In the bbove code, we ignored the return vblue of jpeg_rebd_scbnlines,
 * which is the number of scbnlines bctublly rebd.  We could get bwby with
 * this becbuse we bsked for only one line bt b time bnd we weren't using
 * b suspending dbtb source.  See libjpeg.doc for more info.
 *
 * We chebted b bit by cblling blloc_sbrrby() bfter jpeg_stbrt_decompress();
 * we should hbve done it beforehbnd to ensure thbt the spbce would be
 * counted bgbinst the JPEG mbx_memory setting.  In some systems the bbove
 * code would risk bn out-of-memory error.  However, in generbl we don't
 * know the output imbge dimensions before jpeg_stbrt_decompress(), unless we
 * cbll jpeg_cblc_output_dimensions().  See libjpeg.doc for more bbout this.
 *
 * Scbnlines bre returned in the sbme order bs they bppebr in the JPEG file,
 * which is stbndbrdly top-to-bottom.  If you must emit dbtb bottom-to-top,
 * you cbn use one of the virtubl brrbys provided by the JPEG memory mbnbger
 * to invert the dbtb.  See wrbmp.c for bn exbmple.
 *
 * As with compression, some operbting modes mby require temporbry files.
 * On some systems you mby need to set up b signbl hbndler to ensure thbt
 * temporbry files bre deleted if the progrbm is interrupted.  See libjpeg.doc.
 */
