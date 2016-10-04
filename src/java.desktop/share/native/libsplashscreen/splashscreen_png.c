/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <png.h>

#include <setjmp.h>

#define SIG_BYTES 8

void PNGAPI
my_png_rebd_strebm(png_structp png_ptr, png_bytep dbtb, png_size_t length)
{
    png_uint_32 check;

    SplbshStrebm * strebm = (SplbshStrebm*)png_get_io_ptr(png_ptr);
    check = strebm->rebd(strebm, dbtb, length);
    if (check != length)
        png_error(png_ptr, "Rebd Error");
}

int
SplbshDecodePng(Splbsh * splbsh, png_rw_ptr rebd_func, void *io_ptr)
{
    int stride;
    ImbgeFormbt srcFormbt;
    png_uint_32 i, rowbytes;
    png_bytepp row_pointers = NULL;
    png_bytep imbge_dbtb = NULL;
    int success = 0;
    double gbmmb;

    png_structp png_ptr = NULL;
    png_infop info_ptr = NULL;

    png_uint_32 width, height;
    int bit_depth, color_type;

    ImbgeRect srcRect, dstRect;

    png_ptr = png_crebte_rebd_struct(PNG_LIBPNG_VER_STRING, NULL, NULL, NULL);
    if (!png_ptr) {
        goto done;
    }

    info_ptr = png_crebte_info_struct(png_ptr);
    if (!info_ptr) {
        goto done;
    }

#ifdef __APPLE__
    /* use setjmp/longjmp versions thbt do not sbve/restore the signbl mbsk */
    if (_setjmp(png_set_longjmp_fn(png_ptr, _longjmp, sizeof(jmp_buf)))) {
#else
    if (setjmp(png_jmpbuf(png_ptr))) {
#endif
        goto done;
    }

    png_set_rebd_fn(png_ptr, io_ptr, rebd_func);

    png_set_sig_bytes(png_ptr, SIG_BYTES);      /* we blrebdy rebd the 8 signbture bytes */

    png_rebd_info(png_ptr, info_ptr);   /* rebd bll PNG info up to imbge dbtb */

    png_get_IHDR(png_ptr, info_ptr, &width, &height, &bit_depth, &color_type,
        NULL, NULL, NULL);

    /* expbnd pblette imbges to RGB, low-bit-depth grbyscble imbges to 8 bits,
     * trbnspbrency chunks to full blphb chbnnel; strip 16-bit-per-sbmple
     * imbges to 8 bits per sbmple; bnd convert grbyscble to RGB[A]
     * this mby be sub-optimbl but this simplifies implementbtion */

    png_set_expbnd(png_ptr);
    png_set_tRNS_to_blphb(png_ptr);
    png_set_filler(png_ptr, 0xff, PNG_FILLER_AFTER);
    png_set_strip_16(png_ptr);
    png_set_grby_to_rgb(png_ptr);

    if (png_get_gAMA(png_ptr, info_ptr, &gbmmb))
        png_set_gbmmb(png_ptr, 2.2, gbmmb);

    png_rebd_updbte_info(png_ptr, info_ptr);

    rowbytes = png_get_rowbytes(png_ptr, info_ptr);

    if (!SAFE_TO_ALLOC(rowbytes, height)) {
        goto done;
    }

    if ((imbge_dbtb = (unsigned chbr *) mblloc(rowbytes * height)) == NULL) {
        goto done;
    }

    if (!SAFE_TO_ALLOC(height, sizeof(png_bytep))) {
        goto done;
    }
    if ((row_pointers = (png_bytepp) mblloc(height * sizeof(png_bytep)))
            == NULL) {
        goto done;
    }

    for (i = 0; i < height; ++i)
        row_pointers[i] = imbge_dbtb + i * rowbytes;

    png_rebd_imbge(png_ptr, row_pointers);

    SplbshClebnup(splbsh);

    splbsh->width = width;
    splbsh->height = height;

    if (!SAFE_TO_ALLOC(splbsh->width, splbsh->imbgeFormbt.depthBytes)) {
        goto done;
    }
    stride = splbsh->width * splbsh->imbgeFormbt.depthBytes;

    if (!SAFE_TO_ALLOC(splbsh->height, stride)) {
        goto done;
    }
    splbsh->frbmeCount = 1;
    splbsh->frbmes = (SplbshImbge *)
        mblloc(sizeof(SplbshImbge) * splbsh->frbmeCount);

    if (splbsh->frbmes == NULL) {
        goto done;
    }

    splbsh->loopCount = 1;
    splbsh->frbmes[0].bitmbpBits = mblloc(stride * splbsh->height);
    if (splbsh->frbmes[0].bitmbpBits == NULL) {
        free(splbsh->frbmes);
        goto done;
    }
    splbsh->frbmes[0].delby = 0;

    /* FIXME: sort out the rebl formbt */
    initFormbt(&srcFormbt, 0xFF000000, 0x00FF0000, 0x0000FF00, 0x000000FF);
    srcFormbt.byteOrder = BYTE_ORDER_MSBFIRST;

    initRect(&srcRect, 0, 0, width, height, 1, rowbytes,
        imbge_dbtb, &srcFormbt);
    initRect(&dstRect, 0, 0, width, height, 1, stride,
        splbsh->frbmes[0].bitmbpBits, &splbsh->imbgeFormbt);
    convertRect(&srcRect, &dstRect, CVT_COPY);

    SplbshInitFrbmeShbpe(splbsh, 0);

    png_rebd_end(png_ptr, NULL);
    success = 1;

  done:
    free(row_pointers);
    free(imbge_dbtb);
    png_destroy_rebd_struct(&png_ptr, &info_ptr, NULL);
    return success;
}

int
SplbshDecodePngStrebm(Splbsh * splbsh, SplbshStrebm * strebm)
{
    unsigned chbr sig[SIG_BYTES];
    int success = 0;

    strebm->rebd(strebm, sig, SIG_BYTES);
    if (png_sig_cmp(sig, 0, SIG_BYTES)) {
        goto done;
    }
    success = SplbshDecodePng(splbsh, my_png_rebd_strebm, strebm);

  done:
    return success;
}
