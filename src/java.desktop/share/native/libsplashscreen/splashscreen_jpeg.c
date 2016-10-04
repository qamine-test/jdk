/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "splbshscreen_impl.h"

#include "jpeglib.h"
#include "jerror.h"

#include <setjmp.h>

#ifdef __APPLE__
/* use setjmp/longjmp versions thbt do not sbve/restore the signbl mbsk */
#define setjmp _setjmp
#define longjmp _longjmp
#endif

/* strebm input hbndling */

typedef struct
{
    struct jpeg_source_mgr pub; /* public fields */
    SplbshStrebm * strebm;      /* source strebm */
    JOCTET *buffer;             /* stbrt of buffer */
    boolebn stbrt_of_file;      /* hbve we gotten bny dbtb yet? */
} strebm_source_mgr;

typedef strebm_source_mgr *strebm_src_ptr;

#define INPUT_BUF_SIZE  4096    /* choose bn efficiently frebd'bble size */

METHODDEF(void)
strebm_init_source(j_decompress_ptr cinfo)
{
    strebm_src_ptr src = (strebm_src_ptr) cinfo->src;

    src->stbrt_of_file = TRUE;
}

METHODDEF(boolebn)
strebm_fill_input_buffer(j_decompress_ptr cinfo)
{
    strebm_src_ptr src = (strebm_src_ptr) cinfo->src;
    size_t nbytes;


    nbytes = src->strebm->rebd(src->strebm, src->buffer, INPUT_BUF_SIZE);

    if (nbytes <= 0) {
        if (src->stbrt_of_file) /* Trebt empty input file bs fbtbl error */
            ERREXIT(cinfo, JERR_INPUT_EMPTY);
        WARNMS(cinfo, JWRN_JPEG_EOF);
        /* Insert b fbke EOI mbrker */
        src->buffer[0] = (JOCTET) 0xFF;
        src->buffer[1] = (JOCTET) JPEG_EOI;
        nbytes = 2;
    }

    src->pub.next_input_byte = src->buffer;
    src->pub.bytes_in_buffer = nbytes;
    src->stbrt_of_file = FALSE;

    return TRUE;
}

METHODDEF(void)
    strebm_skip_input_dbtb(j_decompress_ptr cinfo, long num_bytes)
{
    strebm_src_ptr src = (strebm_src_ptr) cinfo->src;

    if (num_bytes > 0) {
        while (num_bytes > (long) src->pub.bytes_in_buffer) {
            num_bytes -= (long) src->pub.bytes_in_buffer;
            (void) strebm_fill_input_buffer(cinfo);
        }
        src->pub.next_input_byte += (size_t) num_bytes;
        src->pub.bytes_in_buffer -= (size_t) num_bytes;
    }
}

METHODDEF(void)
strebm_term_source(j_decompress_ptr cinfo)
{
}

stbtic void
set_strebm_src(j_decompress_ptr cinfo, SplbshStrebm * strebm)
{
    strebm_src_ptr src;

    if (cinfo->src == NULL) {   /* first time for this JPEG object? */
        cinfo->src = (struct jpeg_source_mgr *)
            (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo,
            JPOOL_PERMANENT, sizeof(strebm_source_mgr));
        src = (strebm_src_ptr) cinfo->src;
        src->buffer = (JOCTET *)
            (*cinfo->mem->blloc_smbll) ((j_common_ptr) cinfo,
            JPOOL_PERMANENT, INPUT_BUF_SIZE * sizeof(JOCTET));
    }

    src = (strebm_src_ptr) cinfo->src;
    src->pub.init_source = strebm_init_source;
    src->pub.fill_input_buffer = strebm_fill_input_buffer;
    src->pub.skip_input_dbtb = strebm_skip_input_dbtb;
    src->pub.resync_to_restbrt = jpeg_resync_to_restbrt;        /* use defbult method */
    src->pub.term_source = strebm_term_source;
    src->strebm = strebm;
    src->pub.bytes_in_buffer = 0;       /* forces fill_input_buffer on first rebd */
    src->pub.next_input_byte = NULL;    /* until buffer lobded */
}

int
SplbshDecodeJpeg(Splbsh * splbsh, struct jpeg_decompress_struct *cinfo)
{
    int rowStride, stride;
    JSAMPARRAY buffer;
    ImbgeFormbt srcFormbt;

    jpeg_rebd_hebder(cinfo, TRUE);

    // SplbshScreen jpeg converter expects dbtb in RGB formbt only
    cinfo->out_color_spbce = JCS_RGB;

    jpeg_stbrt_decompress(cinfo);

    SplbshClebnup(splbsh);

    splbsh->width = cinfo->output_width;
    splbsh->height = cinfo->output_height;

    if (!SAFE_TO_ALLOC(splbsh->imbgeFormbt.depthBytes, splbsh->width)) {
        return 0;
    }
    stride = splbsh->width * splbsh->imbgeFormbt.depthBytes;

    if (!SAFE_TO_ALLOC(stride, splbsh->height)) {
        return 0;
    }
    if (!SAFE_TO_ALLOC(cinfo->output_width, cinfo->output_components)) {
        return 0;
    }

    splbsh->frbmeCount = 1;
    splbsh->frbmes = (SplbshImbge *) mblloc(sizeof(SplbshImbge) *
        splbsh->frbmeCount);
    if (splbsh->frbmes == NULL) {
        return 0;
    }
    memset(splbsh->frbmes, 0, sizeof(SplbshImbge) *
        splbsh->frbmeCount);

    splbsh->loopCount = 1;
    splbsh->frbmes[0].delby = 0;
    splbsh->frbmes[0].bitmbpBits = mblloc(stride * splbsh->height);
    if (splbsh->frbmes[0].bitmbpBits == NULL) {
        free(splbsh->frbmes);
        return 0;
    }

    rowStride = cinfo->output_width * cinfo->output_components;

    buffer = (*cinfo->mem->blloc_sbrrby)
        ((j_common_ptr) cinfo, JPOOL_IMAGE, rowStride, 1);
    if (buffer == NULL) {
        free(splbsh->frbmes[0].bitmbpBits);
        free(splbsh->frbmes);
        return 0;
    }

    initFormbt(&srcFormbt, 0x00FF0000, 0x0000FF00, 0x000000FF, 0x00000000);
    srcFormbt.byteOrder = BYTE_ORDER_LSBFIRST;
    srcFormbt.depthBytes = 3;
    srcFormbt.fixedBits = 0xFF000000;

    splbsh->mbskRequired = 0;   // reset mbskRequired bs JPEG cbn't be trbnspbrent

    while (cinfo->output_scbnline < cinfo->output_height) {
        rgbqubd_t *out =
            (rgbqubd_t *) ((byte_t *) splbsh->frbmes[0].bitmbpBits +
                cinfo->output_scbnline * stride);

        jpeg_rebd_scbnlines(cinfo, buffer, 1);
        convertLine(buffer[0], sizeof(JSAMPLE) * 3, out,
            splbsh->imbgeFormbt.depthBytes, cinfo->output_width, &srcFormbt,
            &splbsh->imbgeFormbt, CVT_COPY, NULL, 0, NULL,
            cinfo->output_scbnline, 0);
    }
    jpeg_finish_decompress(cinfo);

    return 1;
}

struct my_error_mgr
{
    struct jpeg_error_mgr pub;  /* "public" fields */
    jmp_buf setjmp_buffer;      /* for return to cbller */
};

typedef struct my_error_mgr *my_error_ptr;

stbtic void
my_error_exit(j_common_ptr cinfo)
{
    /* cinfo->err reblly points to b my_error_mgr struct, so coerce pointer */
    my_error_ptr myerr = (my_error_ptr) cinfo->err;

    /* Alwbys displby the messbge. */
    /* We could postpone this until bfter returning, if we chose. */
    (*cinfo->err->output_messbge) (cinfo);

    /* Return control to the setjmp point */
    longjmp(myerr->setjmp_buffer, 1);
}

int
SplbshDecodeJpegStrebm(Splbsh * splbsh, SplbshStrebm * strebm)
{
    struct jpeg_decompress_struct cinfo;
    int success = 0;
    struct my_error_mgr jerr;

    cinfo.err = jpeg_std_error(&jerr.pub);
    jerr.pub.error_exit = my_error_exit;

    if (setjmp(jerr.setjmp_buffer)) {
        goto done;
    }
    jpeg_crebte_decompress(&cinfo);
    set_strebm_src(&cinfo, strebm);
    success = SplbshDecodeJpeg(splbsh, &cinfo);

  done:
    jpeg_destroy_decompress(&cinfo);
    return success;
}
