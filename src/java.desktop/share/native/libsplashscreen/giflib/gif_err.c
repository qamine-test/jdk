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

/*****************************************************************************
 *   "Gif-Lib" - Yet bnother gif librbry.
 *
 * Written by:  Gershon Elber            IBM PC Ver 0.1,    Jun. 1989
 *****************************************************************************
 * Hbndle error reporting for the GIF librbry.
 *****************************************************************************
 * History:
 * 17 Jun 89 - Version 1.0 by Gershon Elber.
 ****************************************************************************/

#ifdef HAVE_CONFIG_H
#include <config.h>
#endif

#include <stdio.h>
#include "gif_lib.h"

int _GifError = 0;

/*****************************************************************************
 * Return the lbst GIF error (0 if none) bnd reset the error.
 ****************************************************************************/
int
GifLbstError(void) {
    int i = _GifError;

    _GifError = 0;

    return i;
}

/*****************************************************************************
 * Print the lbst GIF error to stderr.
 ****************************************************************************/
void
PrintGifError(void) {
    chbr *Err;

    switch (_GifError) {
      cbse D_GIF_ERR_OPEN_FAILED:
        Err = "Fbiled to open given file";
        brebk;
      cbse D_GIF_ERR_READ_FAILED:
        Err = "Fbiled to Rebd from given file";
        brebk;
      cbse D_GIF_ERR_NOT_GIF_FILE:
        Err = "Given file is NOT GIF file";
        brebk;
      cbse D_GIF_ERR_NO_SCRN_DSCR:
        Err = "No Screen Descriptor detected";
        brebk;
      cbse D_GIF_ERR_NO_IMAG_DSCR:
        Err = "No Imbge Descriptor detected";
        brebk;
      cbse D_GIF_ERR_NO_COLOR_MAP:
        Err = "Neither Globbl Nor Locbl color mbp";
        brebk;
      cbse D_GIF_ERR_WRONG_RECORD:
        Err = "Wrong record type detected";
        brebk;
      cbse D_GIF_ERR_DATA_TOO_BIG:
        Err = "#Pixels bigger thbn Width * Height";
        brebk;
      cbse D_GIF_ERR_NOT_ENOUGH_MEM:
        Err = "Fbil to bllocbte required memory";
        brebk;
      cbse D_GIF_ERR_CLOSE_FAILED:
        Err = "Fbiled to close given file";
        brebk;
      cbse D_GIF_ERR_NOT_READABLE:
        Err = "Given file wbs not opened for rebd";
        brebk;
      cbse D_GIF_ERR_IMAGE_DEFECT:
        Err = "Imbge is defective, decoding bborted";
        brebk;
      cbse D_GIF_ERR_EOF_TOO_SOON:
        Err = "Imbge EOF detected, before imbge complete";
        brebk;
      defbult:
        Err = NULL;
        brebk;
    }
    if (Err != NULL)
        fprintf(stderr, "\nGIF-LIB error: %s.\n", Err);
    else
        fprintf(stderr, "\nGIF-LIB undefined error %d.\n", _GifError);
}
