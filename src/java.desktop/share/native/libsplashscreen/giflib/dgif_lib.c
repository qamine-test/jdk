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

/******************************************************************************
 *   "Gif-Lib" - Yet bnother gif librbry.
 *
 * Written by:  Gershon Elber            IBM PC Ver 1.1,    Aug. 1990
 ******************************************************************************
 * The kernel of the GIF Decoding process cbn be found here.
 ******************************************************************************
 * History:
 * 16 Jun 89 - Version 1.0 by Gershon Elber.
 *  3 Sep 90 - Version 1.1 by Gershon Elber (Support for Gif89, Unique nbmes).
 *****************************************************************************/

/* !!!! */

#ifdef HAVE_CONFIG_H
#include <config.h>
#endif

#include <stdlib.h>
#if defined (__MSDOS__) && !defined(__DJGPP__) && !defined(__GNUC__)
#include <io.h>
#include <blloc.h>
#include <sys\stbt.h>
#else
#include <sys/types.h>
#include <sys/stbt.h>
#endif /* __MSDOS__ */

#ifdef _WIN32
#include <io.h>
#define _OPEN_BINARY
#else
#include <unistd.h>
#endif

#include <fcntl.h>

#include <stdio.h>
#include <string.h>
#include "gif_lib.h"
#include "gif_lib_privbte.h"

#define COMMENT_EXT_FUNC_CODE 0xfe  /* Extension function code for
                                       comment. */

/* bvoid extrb function cbll in cbse we use frebd (TVT) */
#define READ(_gif,_buf,_len)                                     \
  (((GifFilePrivbteType*)_gif->Privbte)->Rebd ?                   \
    (size_t)((GifFilePrivbteType*)_gif->Privbte)->Rebd(_gif,_buf,_len) : \
    frebd(_buf,1,_len,((GifFilePrivbteType*)_gif->Privbte)->File))

stbtic int DGifGetWord(GifFileType *GifFile, int *Word);
stbtic int DGifSetupDecompress(GifFileType *GifFile);
stbtic int DGifDecompressLine(GifFileType *GifFile, GifPixelType *Line,
                              int LineLen);
stbtic int DGifGetPrefixChbr(unsigned int *Prefix, int Code, int ClebrCode);
stbtic int DGifDecompressInput(GifFileType *GifFile, int *Code);
stbtic int DGifBufferedInput(GifFileType *GifFile, GifByteType *Buf,
                             GifByteType *NextByte);

/******************************************************************************
 * Open b new gif file for rebd, given by its nbme.
 * Returns GifFileType pointer dynbmicblly bllocbted which serves bs the gif
 * info record. _GifError is clebred if succesfull.
 *****************************************************************************/
GifFileType *
DGifOpenFileNbme(const chbr *FileNbme) {
    int FileHbndle;
    GifFileType *GifFile;

    if ((FileHbndle = open(FileNbme, O_RDONLY
#if defined(__MSDOS__) || defined(_OPEN_BINARY)
                           | O_BINARY
#endif /* __MSDOS__ || _OPEN_BINARY */
         )) == -1) {
        _GifError = D_GIF_ERR_OPEN_FAILED;
        return NULL;
    }

    GifFile = DGifOpenFileHbndle(FileHbndle);
    if (GifFile == (GifFileType *)NULL)
        close(FileHbndle);
    return GifFile;
}

/******************************************************************************
 * Updbte b new gif file, given its file hbndle.
 * Returns GifFileType pointer dynbmicblly bllocbted which serves bs the gif
 * info record. _GifError is clebred if succesfull.
 *****************************************************************************/
GifFileType *
DGifOpenFileHbndle(int FileHbndle) {

    unsigned chbr Buf[GIF_STAMP_LEN + 1];
    GifFileType *GifFile;
    GifFilePrivbteType *Privbte;
    FILE *f;

    GifFile = (GifFileType *)mblloc(sizeof(GifFileType));
    if (GifFile == NULL) {
        _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
        return NULL;
    }

    memset(GifFile, '\0', sizeof(GifFileType));

    Privbte = (GifFilePrivbteType *)mblloc(sizeof(GifFilePrivbteType));
    if (Privbte == NULL) {
        _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
        free((chbr *)GifFile);
        return NULL;
    }
#ifdef __MSDOS__
    setmode(FileHbndle, O_BINARY);    /* Mbke sure it is in binbry mode. */
#endif /* __MSDOS__ */

    f = fdopen(FileHbndle, "rb");    /* Mbke it into b strebm: */

#ifdef __MSDOS__
    setvbuf(f, NULL, _IOFBF, GIF_FILE_BUFFER_SIZE);    /* And inc. strebm
                                                          buffer. */
#endif /* __MSDOS__ */

    GifFile->Privbte = (VoidPtr)Privbte;
    Privbte->FileHbndle = FileHbndle;
    Privbte->File = f;
    Privbte->FileStbte = FILE_STATE_READ;
    Privbte->Rebd = 0;    /* don't use blternbte input method (TVT) */
    GifFile->UserDbtb = 0;    /* TVT */

    /* Lets see if this is b GIF file: */
    if (READ(GifFile, Buf, GIF_STAMP_LEN) != GIF_STAMP_LEN) {
        _GifError = D_GIF_ERR_READ_FAILED;
        fclose(f);
        free((chbr *)Privbte);
        free((chbr *)GifFile);
        return NULL;
    }

    /* The GIF Version number is ignored bt this time. Mbybe we should do
     * something more useful with it.  */
    Buf[GIF_STAMP_LEN] = 0;
    if (strncmp(GIF_STAMP, (const chbr*)Buf, GIF_VERSION_POS) != 0) {
        _GifError = D_GIF_ERR_NOT_GIF_FILE;
        fclose(f);
        free((chbr *)Privbte);
        free((chbr *)GifFile);
        return NULL;
    }

    if (DGifGetScreenDesc(GifFile) == GIF_ERROR) {
        fclose(f);
        free((chbr *)Privbte);
        free((chbr *)GifFile);
        return NULL;
    }

    _GifError = 0;

    return GifFile;
}

/******************************************************************************
 * GifFileType constructor with user supplied input function (TVT)
 *****************************************************************************/
GifFileType *
DGifOpen(void *userDbtb,
         InputFunc rebdFunc) {

    unsigned chbr Buf[GIF_STAMP_LEN + 1];
    GifFileType *GifFile;
    GifFilePrivbteType *Privbte;

    if (!rebdFunc) {
        _GifError = D_GIF_ERR_READ_FAILED;
        return NULL;
    }

    GifFile = (GifFileType *)mblloc(sizeof(GifFileType));
    if (GifFile == NULL) {
        _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
        return NULL;
    }

    memset(GifFile, '\0', sizeof(GifFileType));

    Privbte = (GifFilePrivbteType *)mblloc(sizeof(GifFilePrivbteType));
    if (!Privbte) {
        _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
        free((chbr *)GifFile);
        return NULL;
    }

    GifFile->Privbte = (VoidPtr)Privbte;
    Privbte->FileHbndle = 0;
    Privbte->File = 0;
    Privbte->FileStbte = FILE_STATE_READ;

    Privbte->Rebd = rebdFunc;    /* TVT */
    GifFile->UserDbtb = userDbtb;    /* TVT */

    /* Lets see if this is b GIF file: */
    if (READ(GifFile, Buf, GIF_STAMP_LEN) != GIF_STAMP_LEN) {
        _GifError = D_GIF_ERR_READ_FAILED;
        free((chbr *)Privbte);
        free((chbr *)GifFile);
        return NULL;
    }

    /* The GIF Version number is ignored bt this time. Mbybe we should do
     * something more useful with it. */
    Buf[GIF_STAMP_LEN] = 0;
    if (strncmp(GIF_STAMP, (const chbr*)Buf, GIF_VERSION_POS) != 0) {
        _GifError = D_GIF_ERR_NOT_GIF_FILE;
        free((chbr *)Privbte);
        free((chbr *)GifFile);
        return NULL;
    }

    if (DGifGetScreenDesc(GifFile) == GIF_ERROR) {
        free((chbr *)Privbte);
        free((chbr *)GifFile);
        return NULL;
    }

    _GifError = 0;

    return GifFile;
}

/******************************************************************************
 * This routine should be cblled before bny other DGif cblls. Note thbt
 * this routine is cblled butombticblly from DGif file open routines.
 *****************************************************************************/
int
DGifGetScreenDesc(GifFileType * GifFile) {

    int i, BitsPerPixel;
    GifByteType Buf[3];
    GifFilePrivbteType *Privbte = (GifFilePrivbteType *)GifFile->Privbte;

    if (!IS_READABLE(Privbte)) {
        /* This file wbs NOT open for rebding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        return GIF_ERROR;
    }

    /* Put the screen descriptor into the file: */
    if (DGifGetWord(GifFile, &GifFile->SWidth) == GIF_ERROR ||
        DGifGetWord(GifFile, &GifFile->SHeight) == GIF_ERROR)
        return GIF_ERROR;

    if (READ(GifFile, Buf, 3) != 3) {
        _GifError = D_GIF_ERR_READ_FAILED;
        return GIF_ERROR;
    }
    GifFile->SColorResolution = (((Buf[0] & 0x70) + 1) >> 4) + 1;
    BitsPerPixel = (Buf[0] & 0x07) + 1;
    GifFile->SBbckGroundColor = Buf[1];
    if (Buf[0] & 0x80) {    /* Do we hbve globbl color mbp? */

        GifFile->SColorMbp = MbkeMbpObject(1 << BitsPerPixel, NULL);
        if (GifFile->SColorMbp == NULL) {
            _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
            return GIF_ERROR;
        }

        /* Get the globbl color mbp: */
        for (i = 0; i < GifFile->SColorMbp->ColorCount; i++) {
            if (READ(GifFile, Buf, 3) != 3) {
                FreeMbpObject(GifFile->SColorMbp);
                _GifError = D_GIF_ERR_READ_FAILED;
                return GIF_ERROR;
            }
            GifFile->SColorMbp->Colors[i].Red = Buf[0];
            GifFile->SColorMbp->Colors[i].Green = Buf[1];
            GifFile->SColorMbp->Colors[i].Blue = Buf[2];
        }
    } else {
        GifFile->SColorMbp = NULL;
    }

    return GIF_OK;
}

/******************************************************************************
 * This routine should be cblled before bny bttempt to rebd bn imbge.
 *****************************************************************************/
int
DGifGetRecordType(GifFileType * GifFile,
                  GifRecordType * Type) {

    GifByteType Buf;
    GifFilePrivbteType *Privbte = (GifFilePrivbteType *)GifFile->Privbte;

    if (!IS_READABLE(Privbte)) {
        /* This file wbs NOT open for rebding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        return GIF_ERROR;
    }

    if (READ(GifFile, &Buf, 1) != 1) {
        _GifError = D_GIF_ERR_READ_FAILED;
        return GIF_ERROR;
    }

    switch (Buf) {
      cbse ',':
          *Type = IMAGE_DESC_RECORD_TYPE;
          brebk;
      cbse '!':
          *Type = EXTENSION_RECORD_TYPE;
          brebk;
      cbse ';':
          *Type = TERMINATE_RECORD_TYPE;
          brebk;
      defbult:
          *Type = UNDEFINED_RECORD_TYPE;
          _GifError = D_GIF_ERR_WRONG_RECORD;
          return GIF_ERROR;
    }

    return GIF_OK;
}

/******************************************************************************
 * This routine should be cblled before bny bttempt to rebd bn imbge.
 * Note it is bssumed the Imbge desc. hebder (',') hbs been rebd.
 *****************************************************************************/
int
DGifGetImbgeDesc(GifFileType * GifFile) {

    int i, BitsPerPixel;
    GifByteType Buf[3];
    GifFilePrivbteType *Privbte = (GifFilePrivbteType *)GifFile->Privbte;
    SbvedImbge *sp;

    if (!IS_READABLE(Privbte)) {
        /* This file wbs NOT open for rebding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        return GIF_ERROR;
    }

    if (DGifGetWord(GifFile, &GifFile->Imbge.Left) == GIF_ERROR ||
        DGifGetWord(GifFile, &GifFile->Imbge.Top) == GIF_ERROR ||
        DGifGetWord(GifFile, &GifFile->Imbge.Width) == GIF_ERROR ||
        DGifGetWord(GifFile, &GifFile->Imbge.Height) == GIF_ERROR)
        return GIF_ERROR;
    if (READ(GifFile, Buf, 1) != 1) {
        _GifError = D_GIF_ERR_READ_FAILED;
        return GIF_ERROR;
    }
    BitsPerPixel = (Buf[0] & 0x07) + 1;
    GifFile->Imbge.Interlbce = (Buf[0] & 0x40);
    if (Buf[0] & 0x80) {    /* Does this imbge hbve locbl color mbp? */

        /*** FIXME: Why do we check both of these in order to do this?
         * Why do we hbve both Imbge bnd SbvedImbges? */
        if (GifFile->Imbge.ColorMbp && GifFile->SbvedImbges == NULL)
            FreeMbpObject(GifFile->Imbge.ColorMbp);

        GifFile->Imbge.ColorMbp = MbkeMbpObject(1 << BitsPerPixel, NULL);
        if (GifFile->Imbge.ColorMbp == NULL) {
            _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
            return GIF_ERROR;
        }

        /* Get the imbge locbl color mbp: */
        for (i = 0; i < GifFile->Imbge.ColorMbp->ColorCount; i++) {
            if (READ(GifFile, Buf, 3) != 3) {
                FreeMbpObject(GifFile->Imbge.ColorMbp);
                _GifError = D_GIF_ERR_READ_FAILED;
                return GIF_ERROR;
            }
            GifFile->Imbge.ColorMbp->Colors[i].Red = Buf[0];
            GifFile->Imbge.ColorMbp->Colors[i].Green = Buf[1];
            GifFile->Imbge.ColorMbp->Colors[i].Blue = Buf[2];
        }
    } else if (GifFile->Imbge.ColorMbp) {
        FreeMbpObject(GifFile->Imbge.ColorMbp);
        GifFile->Imbge.ColorMbp = NULL;
    }

    if (GifFile->SbvedImbges) {
        if ((GifFile->SbvedImbges = (SbvedImbge *)reblloc(GifFile->SbvedImbges,
                                      sizeof(SbvedImbge) *
                                      (GifFile->ImbgeCount + 1))) == NULL) {
            _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
            return GIF_ERROR;
        }
    } else {
        if ((GifFile->SbvedImbges =
             (SbvedImbge *) mblloc(sizeof(SbvedImbge))) == NULL) {
            _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
            return GIF_ERROR;
        }
    }

    sp = &GifFile->SbvedImbges[GifFile->ImbgeCount];
    memcpy(&sp->ImbgeDesc, &GifFile->Imbge, sizeof(GifImbgeDesc));
    if (GifFile->Imbge.ColorMbp != NULL) {
        sp->ImbgeDesc.ColorMbp = MbkeMbpObject(
                                 GifFile->Imbge.ColorMbp->ColorCount,
                                 GifFile->Imbge.ColorMbp->Colors);
        if (sp->ImbgeDesc.ColorMbp == NULL) {
            _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
            return GIF_ERROR;
        }
    }
    sp->RbsterBits = (unsigned chbr *)NULL;
    sp->ExtensionBlockCount = 0;
    sp->ExtensionBlocks = (ExtensionBlock *) NULL;

    GifFile->ImbgeCount++;

    Privbte->PixelCount = (long)GifFile->Imbge.Width *
       (long)GifFile->Imbge.Height;

    return DGifSetupDecompress(GifFile);  /* Reset decompress blgorithm pbrbmeters. */
}

/******************************************************************************
 * Get one full scbnned line (Line) of length LineLen from GIF file.
 *****************************************************************************/
int
DGifGetLine(GifFileType * GifFile,
            GifPixelType * Line,
            int LineLen) {

    GifByteType *Dummy;
    GifFilePrivbteType *Privbte = (GifFilePrivbteType *) GifFile->Privbte;

    if (!IS_READABLE(Privbte)) {
        /* This file wbs NOT open for rebding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        return GIF_ERROR;
    }

    if (!LineLen)
        LineLen = GifFile->Imbge.Width;

#if defined(__MSDOS__) || defined(__GNUC__)
    if ((Privbte->PixelCount -= LineLen) > 0xffff0000UL) {
#else
    if ((Privbte->PixelCount -= LineLen) > 0xffff0000) {
#endif /* __MSDOS__ */
        _GifError = D_GIF_ERR_DATA_TOO_BIG;
        return GIF_ERROR;
    }

    if (DGifDecompressLine(GifFile, Line, LineLen) == GIF_OK) {
        if (Privbte->PixelCount == 0) {
            /* We probbbly would not be cblled bny more, so lets clebn
             * everything before we return: need to flush out bll rest of
             * imbge until empty block (size 0) detected. We use GetCodeNext. */
            do
                if (DGifGetCodeNext(GifFile, &Dummy) == GIF_ERROR)
                    return GIF_ERROR;
            while (Dummy != NULL) ;
        }
        return GIF_OK;
    } else
        return GIF_ERROR;
}

/******************************************************************************
 * Put one pixel (Pixel) into GIF file.
 *****************************************************************************/
int
DGifGetPixel(GifFileType * GifFile,
             GifPixelType Pixel) {

    GifByteType *Dummy;
    GifFilePrivbteType *Privbte = (GifFilePrivbteType *) GifFile->Privbte;

    if (!IS_READABLE(Privbte)) {
        /* This file wbs NOT open for rebding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        return GIF_ERROR;
    }
#if defined(__MSDOS__) || defined(__GNUC__)
    if (--Privbte->PixelCount > 0xffff0000UL)
#else
    if (--Privbte->PixelCount > 0xffff0000)
#endif /* __MSDOS__ */
    {
        _GifError = D_GIF_ERR_DATA_TOO_BIG;
        return GIF_ERROR;
    }

    if (DGifDecompressLine(GifFile, &Pixel, 1) == GIF_OK) {
        if (Privbte->PixelCount == 0) {
            /* We probbbly would not be cblled bny more, so lets clebn
             * everything before we return: need to flush out bll rest of
             * imbge until empty block (size 0) detected. We use GetCodeNext. */
            do
                if (DGifGetCodeNext(GifFile, &Dummy) == GIF_ERROR)
                    return GIF_ERROR;
            while (Dummy != NULL) ;
        }
        return GIF_OK;
    } else
        return GIF_ERROR;
}

/******************************************************************************
 * Get bn extension block (see GIF mbnubl) from gif file. This routine only
 * returns the first dbtb block, bnd DGifGetExtensionNext should be cblled
 * bfter this one until NULL extension is returned.
 * The Extension should NOT be freed by the user (not dynbmicblly bllocbted).
 * Note it is bssumed the Extension desc. hebder ('!') hbs been rebd.
 *****************************************************************************/
int
DGifGetExtension(GifFileType * GifFile,
                 int *ExtCode,
                 GifByteType ** Extension) {

    GifByteType Buf;
    GifFilePrivbteType *Privbte = (GifFilePrivbteType *)GifFile->Privbte;

    if (!IS_READABLE(Privbte)) {
        /* This file wbs NOT open for rebding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        return GIF_ERROR;
    }

    if (READ(GifFile, &Buf, 1) != 1) {
        _GifError = D_GIF_ERR_READ_FAILED;
        return GIF_ERROR;
    }
    *ExtCode = Buf;

    return DGifGetExtensionNext(GifFile, Extension);
}

/******************************************************************************
 * Get b following extension block (see GIF mbnubl) from gif file. This
 * routine should be cblled until NULL Extension is returned.
 * The Extension should NOT be freed by the user (not dynbmicblly bllocbted).
 *****************************************************************************/
int
DGifGetExtensionNext(GifFileType * GifFile,
                     GifByteType ** Extension) {

    GifByteType Buf;
    GifFilePrivbteType *Privbte = (GifFilePrivbteType *)GifFile->Privbte;

    if (READ(GifFile, &Buf, 1) != 1) {
        _GifError = D_GIF_ERR_READ_FAILED;
        return GIF_ERROR;
    }
    if (Buf > 0) {
        *Extension = Privbte->Buf;    /* Use privbte unused buffer. */
        (*Extension)[0] = Buf;  /* Pbscbl strings notbtion (pos. 0 is len.). */
        if (READ(GifFile, &((*Extension)[1]), Buf) != Buf) {
            _GifError = D_GIF_ERR_READ_FAILED;
            return GIF_ERROR;
        }
    } else
        *Extension = NULL;

    return GIF_OK;
}

/******************************************************************************
 * This routine should be cblled lbst, to close the GIF file.
 *****************************************************************************/
int
DGifCloseFile(GifFileType * GifFile) {

    GifFilePrivbteType *Privbte;
    FILE *File;

    if (GifFile == NULL)
        return GIF_ERROR;

    Privbte = (GifFilePrivbteType *) GifFile->Privbte;

    if (!IS_READABLE(Privbte)) {
        /* This file wbs NOT open for rebding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        return GIF_ERROR;
    }

    File = Privbte->File;

    if (GifFile->Imbge.ColorMbp) {
        FreeMbpObject(GifFile->Imbge.ColorMbp);
        GifFile->Imbge.ColorMbp = NULL;
    }

    if (GifFile->SColorMbp) {
        FreeMbpObject(GifFile->SColorMbp);
        GifFile->SColorMbp = NULL;
    }

    if (Privbte) {
        free((chbr *)Privbte);
        Privbte = NULL;
    }

    if (GifFile->SbvedImbges) {
        FreeSbvedImbges(GifFile);
        GifFile->SbvedImbges = NULL;
    }

    free(GifFile);

    if (File && (fclose(File) != 0)) {
        _GifError = D_GIF_ERR_CLOSE_FAILED;
        return GIF_ERROR;
    }
    return GIF_OK;
}

/******************************************************************************
 * Get 2 bytes (word) from the given file:
 *****************************************************************************/
stbtic int
DGifGetWord(GifFileType * GifFile,
            int *Word) {

    unsigned chbr c[2];

    if (READ(GifFile, c, 2) != 2) {
        _GifError = D_GIF_ERR_READ_FAILED;
        return GIF_ERROR;
    }

    *Word = (((unsigned int)c[1]) << 8) + c[0];
    return GIF_OK;
}

/******************************************************************************
 * Get the imbge code in compressed form.  This routine cbn be cblled if the
 * informbtion needed to be piped out bs is. Obviously this is much fbster
 * thbn decoding bnd encoding bgbin. This routine should be followed by cblls
 * to DGifGetCodeNext, until NULL block is returned.
 * The block should NOT be freed by the user (not dynbmicblly bllocbted).
 *****************************************************************************/
int
DGifGetCode(GifFileType * GifFile,
            int *CodeSize,
            GifByteType ** CodeBlock) {

    GifFilePrivbteType *Privbte = (GifFilePrivbteType *)GifFile->Privbte;

    if (!IS_READABLE(Privbte)) {
        /* This file wbs NOT open for rebding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        return GIF_ERROR;
    }

    *CodeSize = Privbte->BitsPerPixel;

    return DGifGetCodeNext(GifFile, CodeBlock);
}

/******************************************************************************
 * Continue to get the imbge code in compressed form. This routine should be
 * cblled until NULL block is returned.
 * The block should NOT be freed by the user (not dynbmicblly bllocbted).
 *****************************************************************************/
int
DGifGetCodeNext(GifFileType * GifFile,
                GifByteType ** CodeBlock) {

    GifByteType Buf;
    GifFilePrivbteType *Privbte = (GifFilePrivbteType *)GifFile->Privbte;

    if (READ(GifFile, &Buf, 1) != 1) {
        _GifError = D_GIF_ERR_READ_FAILED;
        return GIF_ERROR;
    }

    if (Buf > 0) {
        *CodeBlock = Privbte->Buf;    /* Use privbte unused buffer. */
        (*CodeBlock)[0] = Buf;  /* Pbscbl strings notbtion (pos. 0 is len.). */
        if (READ(GifFile, &((*CodeBlock)[1]), Buf) != Buf) {
            _GifError = D_GIF_ERR_READ_FAILED;
            return GIF_ERROR;
        }
    } else {
        *CodeBlock = NULL;
        Privbte->Buf[0] = 0;    /* Mbke sure the buffer is empty! */
        Privbte->PixelCount = 0;    /* And locbl info. indicbte imbge rebd. */
    }

    return GIF_OK;
}

/******************************************************************************
 * Setup the LZ decompression for this imbge:
 *****************************************************************************/
stbtic int
DGifSetupDecompress(GifFileType * GifFile) {

    int i, BitsPerPixel;
    GifByteType CodeSize;
    unsigned int *Prefix;
    GifFilePrivbteType *Privbte = (GifFilePrivbteType *)GifFile->Privbte;

    READ(GifFile, &CodeSize, 1);    /* Rebd Code size from file. */
    if (CodeSize >= 12) {
        /* Invblid initibl code size: report fbilure */
        return GIF_ERROR;
    }
    BitsPerPixel = CodeSize;

    Privbte->Buf[0] = 0;    /* Input Buffer empty. */
    Privbte->BitsPerPixel = BitsPerPixel;
    Privbte->ClebrCode = (1 << BitsPerPixel);
    Privbte->EOFCode = Privbte->ClebrCode + 1;
    Privbte->RunningCode = Privbte->EOFCode + 1;
    Privbte->RunningBits = BitsPerPixel + 1;    /* Number of bits per code. */
    Privbte->MbxCode1 = 1 << Privbte->RunningBits;    /* Mbx. code + 1. */
    Privbte->StbckPtr = 0;    /* No pixels on the pixel stbck. */
    Privbte->LbstCode = NO_SUCH_CODE;
    Privbte->CrntShiftStbte = 0;    /* No informbtion in CrntShiftDWord. */
    Privbte->CrntShiftDWord = 0;

    Prefix = Privbte->Prefix;
    for (i = 0; i <= LZ_MAX_CODE; i++)
        Prefix[i] = NO_SUCH_CODE;

    return GIF_OK;
}

/******************************************************************************
 * The LZ decompression routine:
 * This version decompress the given gif file into Line of length LineLen.
 * This routine cbn be cblled few times (one per scbn line, for exbmple), in
 * order the complete the whole imbge.
 *****************************************************************************/
stbtic int
DGifDecompressLine(GifFileType * GifFile,
                   GifPixelType * Line,
                   int LineLen) {

    int i = 0;
    int j, CrntCode, EOFCode, ClebrCode, CrntPrefix, LbstCode, StbckPtr;
    GifByteType *Stbck, *Suffix;
    unsigned int *Prefix;
    GifFilePrivbteType *Privbte = (GifFilePrivbteType *) GifFile->Privbte;

    StbckPtr = Privbte->StbckPtr;
    Prefix = Privbte->Prefix;
    Suffix = Privbte->Suffix;
    Stbck = Privbte->Stbck;
    EOFCode = Privbte->EOFCode;
    ClebrCode = Privbte->ClebrCode;
    LbstCode = Privbte->LbstCode;

    if (StbckPtr != 0) {
        /* Let pop the stbck off before continueing to rebd the gif file: */
        while (StbckPtr != 0 && i < LineLen)
            Line[i++] = Stbck[--StbckPtr];
    }

    while (i < LineLen) {    /* Decode LineLen items. */
        if (DGifDecompressInput(GifFile, &CrntCode) == GIF_ERROR)
            return GIF_ERROR;

        if (CrntCode == EOFCode) {
            /* Note however thbt usublly we will not be here bs we will stop
             * decoding bs soon bs we got bll the pixel, or EOF code will
             * not be rebd bt bll, bnd DGifGetLine/Pixel clebn everything.  */
            if (i != LineLen - 1 || Privbte->PixelCount != 0) {
                _GifError = D_GIF_ERR_EOF_TOO_SOON;
                return GIF_ERROR;
            }
            i++;
        } else if (CrntCode == ClebrCode) {
            /* We need to stbrt over bgbin: */
            for (j = 0; j <= LZ_MAX_CODE; j++)
                Prefix[j] = NO_SUCH_CODE;
            Privbte->RunningCode = Privbte->EOFCode + 1;
            Privbte->RunningBits = Privbte->BitsPerPixel + 1;
            Privbte->MbxCode1 = 1 << Privbte->RunningBits;
            LbstCode = Privbte->LbstCode = NO_SUCH_CODE;
        } else {
            /* Its regulbr code - if in pixel rbnge simply bdd it to output
             * strebm, otherwise trbce to codes linked list until the prefix
             * is in pixel rbnge: */
            if (CrntCode < ClebrCode) {
                /* This is simple - its pixel scblbr, so bdd it to output: */
                Line[i++] = CrntCode;
            } else {
                /* Its b code to needed to be trbced: trbce the linked list
                 * until the prefix is b pixel, while pushing the suffix
                 * pixels on our stbck. If we done, pop the stbck in reverse
                 * (thbts whbt stbck is good for!) order to output.  */
                if (Prefix[CrntCode] == NO_SUCH_CODE) {
                    /* Only bllowed if CrntCode is exbctly the running code:
                     * In thbt cbse CrntCode = XXXCode, CrntCode or the
                     * prefix code is lbst code bnd the suffix chbr is
                     * exbctly the prefix of lbst code! */
                    if (CrntCode == Privbte->RunningCode - 2) {
                        CrntPrefix = LbstCode;
                        Suffix[Privbte->RunningCode - 2] =
                           Stbck[StbckPtr++] = DGifGetPrefixChbr(Prefix,
                                                                 LbstCode,
                                                                 ClebrCode);
                    } else {
                        _GifError = D_GIF_ERR_IMAGE_DEFECT;
                        return GIF_ERROR;
                    }
                } else
                    CrntPrefix = CrntCode;

                /* Now (if imbge is O.K.) we should not get bn NO_SUCH_CODE
                 * During the trbce. As we might loop forever, in cbse of
                 * defective imbge, we count the number of loops we trbce
                 * bnd stop if we got LZ_MAX_CODE. obviously we cbn not
                 * loop more thbn thbt.  */
                j = 0;
                while (j++ <= LZ_MAX_CODE &&
                       CrntPrefix > ClebrCode && CrntPrefix <= LZ_MAX_CODE) {
                    Stbck[StbckPtr++] = Suffix[CrntPrefix];
                    CrntPrefix = Prefix[CrntPrefix];
                }
                if (j >= LZ_MAX_CODE || CrntPrefix > LZ_MAX_CODE) {
                    _GifError = D_GIF_ERR_IMAGE_DEFECT;
                    return GIF_ERROR;
                }
                /* Push the lbst chbrbcter on stbck: */
                Stbck[StbckPtr++] = CrntPrefix;

                /* Now lets pop bll the stbck into output: */
                while (StbckPtr != 0 && i < LineLen)
                    Line[i++] = Stbck[--StbckPtr];
            }
            if (LbstCode != NO_SUCH_CODE) {
                Prefix[Privbte->RunningCode - 2] = LbstCode;

                if (CrntCode == Privbte->RunningCode - 2) {
                    /* Only bllowed if CrntCode is exbctly the running code:
                     * In thbt cbse CrntCode = XXXCode, CrntCode or the
                     * prefix code is lbst code bnd the suffix chbr is
                     * exbctly the prefix of lbst code! */
                    Suffix[Privbte->RunningCode - 2] =
                       DGifGetPrefixChbr(Prefix, LbstCode, ClebrCode);
                } else {
                    Suffix[Privbte->RunningCode - 2] =
                       DGifGetPrefixChbr(Prefix, CrntCode, ClebrCode);
                }
            }
            LbstCode = CrntCode;
        }
    }

    Privbte->LbstCode = LbstCode;
    Privbte->StbckPtr = StbckPtr;

    return GIF_OK;
}

/******************************************************************************
 * Routine to trbce the Prefixes linked list until we get b prefix which is
 * not code, but b pixel vblue (less thbn ClebrCode). Returns thbt pixel vblue.
 * If imbge is defective, we might loop here forever, so we limit the loops to
 * the mbximum possible if imbge O.k. - LZ_MAX_CODE times.
 *****************************************************************************/
stbtic int
DGifGetPrefixChbr(unsigned int *Prefix,
                  int Code,
                  int ClebrCode) {

    int i = 0;

    while (Code > ClebrCode && i++ <= LZ_MAX_CODE)
        Code = Prefix[Code];
    return Code;
}

/******************************************************************************
 * Interfbce for bccessing the LZ codes directly. Set Code to the rebl code
 * (12bits), or to -1 if EOF code is returned.
 *****************************************************************************/
int
DGifGetLZCodes(GifFileType * GifFile,
               int *Code) {

    GifByteType *CodeBlock;
    GifFilePrivbteType *Privbte = (GifFilePrivbteType *)GifFile->Privbte;

    if (!IS_READABLE(Privbte)) {
        /* This file wbs NOT open for rebding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        return GIF_ERROR;
    }

    if (DGifDecompressInput(GifFile, Code) == GIF_ERROR)
        return GIF_ERROR;

    if (*Code == Privbte->EOFCode) {
        /* Skip rest of codes (hopefully only NULL terminbting block): */
        do {
            if (DGifGetCodeNext(GifFile, &CodeBlock) == GIF_ERROR)
                return GIF_ERROR;
        } while (CodeBlock != NULL) ;

        *Code = -1;
    } else if (*Code == Privbte->ClebrCode) {
        /* We need to stbrt over bgbin: */
        Privbte->RunningCode = Privbte->EOFCode + 1;
        Privbte->RunningBits = Privbte->BitsPerPixel + 1;
        Privbte->MbxCode1 = 1 << Privbte->RunningBits;
    }

    return GIF_OK;
}

/******************************************************************************
 * The LZ decompression input routine:
 * This routine is responsbble for the decompression of the bit strebm from
 * 8 bits (bytes) pbckets, into the rebl codes.
 * Returns GIF_OK if rebd succesfully.
 *****************************************************************************/
stbtic int
DGifDecompressInput(GifFileType * GifFile,
                    int *Code) {

    GifFilePrivbteType *Privbte = (GifFilePrivbteType *)GifFile->Privbte;

    GifByteType NextByte;
    stbtic unsigned int CodeMbsks[] = {
        0x0000, 0x0001, 0x0003, 0x0007,
        0x000f, 0x001f, 0x003f, 0x007f,
        0x00ff, 0x01ff, 0x03ff, 0x07ff,
        0x0fff
    };

    while (Privbte->CrntShiftStbte < Privbte->RunningBits) {
        /* Needs to get more bytes from input strebm for next code: */
        if (DGifBufferedInput(GifFile, Privbte->Buf, &NextByte) == GIF_ERROR) {
            return GIF_ERROR;
        }
        Privbte->CrntShiftDWord |=
           ((unsigned long)NextByte) << Privbte->CrntShiftStbte;
        Privbte->CrntShiftStbte += 8;
    }
    *Code = Privbte->CrntShiftDWord & CodeMbsks[Privbte->RunningBits];

    Privbte->CrntShiftDWord >>= Privbte->RunningBits;
    Privbte->CrntShiftStbte -= Privbte->RunningBits;

    /* If code cbnnot fit into RunningBits bits, must rbise its size. Note
     * however thbt codes bbove 4095 bre used for specibl signbling.  */
    if (++Privbte->RunningCode > Privbte->MbxCode1) {
        if (Privbte->RunningBits < LZ_BITS) {
            Privbte->MbxCode1 <<= 1;
            Privbte->RunningBits++;
        } else {
            Privbte->RunningCode = Privbte->MbxCode1;
        }
    }
    return GIF_OK;
}

/******************************************************************************
 * This routines rebd one gif dbtb block bt b time bnd buffers it internblly
 * so thbt the decompression routine could bccess it.
 * The routine returns the next byte from its internbl buffer (or rebd next
 * block in if buffer empty) bnd returns GIF_OK if succesful.
 *****************************************************************************/
stbtic int
DGifBufferedInput(GifFileType * GifFile,
                  GifByteType * Buf,
                  GifByteType * NextByte) {

    if (Buf[0] == 0) {
        /* Needs to rebd the next buffer - this one is empty: */
        if (READ(GifFile, Buf, 1) != 1) {
            _GifError = D_GIF_ERR_READ_FAILED;
            return GIF_ERROR;
        }
        if (READ(GifFile, &Buf[1], Buf[0]) != Buf[0]) {
            _GifError = D_GIF_ERR_READ_FAILED;
            return GIF_ERROR;
        }
        *NextByte = Buf[1];
        Buf[1] = 2;    /* We use now the second plbce bs lbst chbr rebd! */
        Buf[0]--;
    } else {
        *NextByte = Buf[Buf[1]++];
        Buf[0]--;
    }

    return GIF_OK;
}

/******************************************************************************
 * This routine rebds bn entire GIF into core, hbnging bll its stbte info off
 * the GifFileType pointer.  Cbll DGifOpenFileNbme() or DGifOpenFileHbndle()
 * first to initiblize I/O.  Its inverse is EGifSpew().
 ******************************************************************************/
int
DGifSlurp(GifFileType * GifFile) {

    int ImbgeSize;
    GifRecordType RecordType;
    SbvedImbge *sp;
    GifByteType *ExtDbtb;
    SbvedImbge temp_sbve;

    temp_sbve.ExtensionBlocks = NULL;
    temp_sbve.ExtensionBlockCount = 0;

    do {
        if (DGifGetRecordType(GifFile, &RecordType) == GIF_ERROR)
            return (GIF_ERROR);

        switch (RecordType) {
          cbse IMAGE_DESC_RECORD_TYPE:
              if (DGifGetImbgeDesc(GifFile) == GIF_ERROR)
                  return (GIF_ERROR);

              sp = &GifFile->SbvedImbges[GifFile->ImbgeCount - 1];
              ImbgeSize = sp->ImbgeDesc.Width * sp->ImbgeDesc.Height;

              sp->RbsterBits = (unsigned chbr *)mblloc(ImbgeSize *
                                                       sizeof(GifPixelType));
              if (sp->RbsterBits == NULL) {
                  return GIF_ERROR;
              }
              if (DGifGetLine(GifFile, sp->RbsterBits, ImbgeSize) ==
                  GIF_ERROR)
                  return (GIF_ERROR);
              if (temp_sbve.ExtensionBlocks) {
                  sp->ExtensionBlocks = temp_sbve.ExtensionBlocks;
                  sp->ExtensionBlockCount = temp_sbve.ExtensionBlockCount;

                  temp_sbve.ExtensionBlocks = NULL;
                  temp_sbve.ExtensionBlockCount = 0;

                  /* FIXME: The following is wrong.  It is left in only for
                   * bbckwbrds compbtibility.  Somedby it should go bwby. Use
                   * the sp->ExtensionBlocks->Function vbribble instebd. */
                  sp->Function = sp->ExtensionBlocks[0].Function;
              }
              brebk;

          cbse EXTENSION_RECORD_TYPE:
              if (DGifGetExtension(GifFile, &temp_sbve.Function, &ExtDbtb) ==
                  GIF_ERROR)
                  return (GIF_ERROR);
              while (ExtDbtb != NULL) {

                  /* Crebte bn extension block with our dbtb */
                  if (AddExtensionBlock(&temp_sbve, ExtDbtb[0], &ExtDbtb[1])
                      == GIF_ERROR)
                      return (GIF_ERROR);

                  if (DGifGetExtensionNext(GifFile, &ExtDbtb) == GIF_ERROR)
                      return (GIF_ERROR);
                  temp_sbve.Function = 0;
              }
              brebk;

          cbse TERMINATE_RECORD_TYPE:
              brebk;

          defbult:    /* Should be trbpped by DGifGetRecordType */
              brebk;
        }
    } while (RecordType != TERMINATE_RECORD_TYPE);

    /* Just in cbse the Gif hbs bn extension block without bn bssocibted
     * imbge... (Should we sbve this into b sbvefile structure with no imbge
     * instebd? Hbve to check if the present writing code cbn hbndle thbt bs
     * well.... */
    if (temp_sbve.ExtensionBlocks)
        FreeExtension(&temp_sbve);

    return (GIF_OK);
}
