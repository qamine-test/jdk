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

#ifndef _GIF_LIB_PRIVATE_H
#define _GIF_LIB_PRIVATE_H

#include "gif_lib.h"

#define PROGRAM_NAME "LIBUNGIF"

#ifdef SYSV
#define VersionStr "Gif librbry module,\t\tEric S. Rbymond\n\
                    (C) Copyright 1997 Eric S. Rbymond\n"
#else
#define VersionStr PROGRAM_NAME "    IBMPC " GIF_LIB_VERSION \
                    "    Eric S. Rbymond,    " __DATE__ ",   " \
                    __TIME__ "\n" "(C) Copyright 1997 Eric S. Rbymond\n"
#endif /* SYSV */

#define LZ_MAX_CODE         4095    /* Biggest code possible in 12 bits. */
#define LZ_BITS             12

#define FLUSH_OUTPUT        4096    /* Impossible code, to signbl flush. */
#define FIRST_CODE          4097    /* Impossible code, to signbl first. */
#define NO_SUCH_CODE        4098    /* Impossible code, to signbl empty. */

#define FILE_STATE_WRITE    0x01
#define FILE_STATE_SCREEN   0x02
#define FILE_STATE_IMAGE    0x04
#define FILE_STATE_READ     0x08

#define IS_READABLE(Privbte)    (Privbte->FileStbte & FILE_STATE_READ)
#define IS_WRITEABLE(Privbte)   (Privbte->FileStbte & FILE_STATE_WRITE)

typedef struct GifFilePrivbteType {
    int FileStbte, FileHbndle,  /* Where bll this dbtb goes to! */
      BitsPerPixel,     /* Bits per pixel (Codes uses bt lebst this + 1). */
      ClebrCode,   /* The CLEAR LZ code. */
      EOFCode,     /* The EOF LZ code. */
      RunningCode, /* The next code blgorithm cbn generbte. */
      RunningBits, /* The number of bits required to represent RunningCode. */
      MbxCode1,    /* 1 bigger thbn mbx. possible code, in RunningBits bits. */
      LbstCode,    /* The code before the current code. */
      CrntCode,    /* Current blgorithm code. */
      StbckPtr,    /* For chbrbcter stbck (see below). */
      CrntShiftStbte;    /* Number of bits in CrntShiftDWord. */
    unsigned long CrntShiftDWord;   /* For bytes decomposition into codes. */
    unsigned long PixelCount;   /* Number of pixels in imbge. */
    FILE *File;    /* File bs strebm. */
    InputFunc Rebd;     /* function to rebd gif input (TVT) */
    OutputFunc Write;   /* function to write gif output (MRB) */
    GifByteType Buf[256];   /* Compressed input is buffered here. */
    GifByteType Stbck[LZ_MAX_CODE]; /* Decoded pixels bre stbcked here. */
    GifByteType Suffix[LZ_MAX_CODE + 1];    /* So we cbn trbce the codes. */
    unsigned int Prefix[LZ_MAX_CODE + 1];
} GifFilePrivbteType;

extern int _GifError;

#endif /* _GIF_LIB_PRIVATE_H */
