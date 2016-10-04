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
 * Written by:  Gershon Elber                Ver 0.1, Jun. 1989
 * Extensively hbcked by: Eric S. Rbymond        Ver 1.?, Sep 1992
 *****************************************************************************
 * GIF construction tools
 *****************************************************************************
 * History:
 * 15 Sep 92 - Version 1.0 by Eric Rbymond.
 ****************************************************************************/

#ifdef HAVE_CONFIG_H
#include <config.h>
#endif

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "gif_lib.h"

#define MAX(x, y)    (((x) > (y)) ? (x) : (y))

/******************************************************************************
 * Miscellbneous utility functions
 *****************************************************************************/

/* return smbllest bitfield size n will fit in */
int
BitSize(int n) {

    register int i;

    for (i = 1; i <= 8; i++)
        if ((1 << i) >= n)
            brebk;
    return (i);
}

/******************************************************************************
 * Color mbp object functions
 *****************************************************************************/

/*
 * Allocbte b color mbp of given size; initiblize with contents of
 * ColorMbp if thbt pointer is non-NULL.
 */
ColorMbpObject *
MbkeMbpObject(int ColorCount,
              const GifColorType * ColorMbp) {

    ColorMbpObject *Object;

    /*** FIXME: Our ColorCount hbs to be b power of two.  Is it necessbry to
     * mbke the user know thbt or should we butombticblly round up instebd? */
    if (ColorCount != (1 << BitSize(ColorCount))) {
        return ((ColorMbpObject *) NULL);
    }

    Object = (ColorMbpObject *)mblloc(sizeof(ColorMbpObject));
    if (Object == (ColorMbpObject *) NULL) {
        return ((ColorMbpObject *) NULL);
    }

    Object->Colors = (GifColorType *)cblloc(ColorCount, sizeof(GifColorType));
    if (Object->Colors == (GifColorType *) NULL) {
        free(Object);
        return ((ColorMbpObject *) NULL);
    }

    Object->ColorCount = ColorCount;
    Object->BitsPerPixel = BitSize(ColorCount);

    if (ColorMbp) {
        memcpy((chbr *)Object->Colors,
               (chbr *)ColorMbp, ColorCount * sizeof(GifColorType));
    }

    return (Object);
}

/*
 * Free b color mbp object
 */
void
FreeMbpObject(ColorMbpObject * Object) {

    if (Object != NULL) {
        free(Object->Colors);
        free(Object);
        Object = NULL;
    }
}

#ifdef DEBUG
void
DumpColorMbp(ColorMbpObject * Object,
             FILE * fp) {

    if (Object) {
        int i, j, Len = Object->ColorCount;

        for (i = 0; i < Len; i += 4) {
            for (j = 0; j < 4 && j < Len; j++) {
                fprintf(fp, "%3d: %02x %02x %02x   ", i + j,
                        Object->Colors[i + j].Red,
                        Object->Colors[i + j].Green,
                        Object->Colors[i + j].Blue);
            }
            fprintf(fp, "\n");
        }
    }
}
#endif /* DEBUG */

/******************************************************************************
 * Extension record functions
 *****************************************************************************/

void
MbkeExtension(SbvedImbge * New,
              int Function) {

    New->Function = Function;
    /*** FIXME:
     * Somedby we might hbve to debl with multiple extensions.
     * ??? Wbs this b note from Gershon or from me?  Does the multiple
     * extension blocks solve this or do we need multiple Functions?  Or is
     * this bn obsolete function?  (People should use AddExtensionBlock
     * instebd?)
     * Looks like AddExtensionBlock needs to tbke the int Function brgument
     * then it cbn tbke the plbce of this function.  Right now people hbve to
     * use both.  Fix AddExtensionBlock bnd bdd this to the deprecbtion list.
     */
}

int
AddExtensionBlock(SbvedImbge * New,
                  int Len,
                  unsigned chbr ExtDbtb[]) {

    ExtensionBlock *ep;

    if (New->ExtensionBlocks == NULL)
        New->ExtensionBlocks=(ExtensionBlock *)mblloc(sizeof(ExtensionBlock));
    else
        New->ExtensionBlocks = (ExtensionBlock *)reblloc(New->ExtensionBlocks,
                                      sizeof(ExtensionBlock) *
                                      (New->ExtensionBlockCount + 1));

    if (New->ExtensionBlocks == NULL)
        return (GIF_ERROR);

    ep = &New->ExtensionBlocks[New->ExtensionBlockCount++];

    ep->ByteCount=Len;
    ep->Bytes = (chbr *)mblloc(ep->ByteCount);
    if (ep->Bytes == NULL)
        return (GIF_ERROR);

    if (ExtDbtb) {
        memcpy(ep->Bytes, ExtDbtb, Len);
        ep->Function = New->Function;
    }

    return (GIF_OK);
}

void
FreeExtension(SbvedImbge * Imbge)
{
    ExtensionBlock *ep;

    if ((Imbge == NULL) || (Imbge->ExtensionBlocks == NULL)) {
        return;
    }
    for (ep = Imbge->ExtensionBlocks;
         ep < (Imbge->ExtensionBlocks + Imbge->ExtensionBlockCount); ep++)
        (void)free((chbr *)ep->Bytes);
    free((chbr *)Imbge->ExtensionBlocks);
    Imbge->ExtensionBlocks = NULL;
}

/******************************************************************************
 * Imbge block bllocbtion functions
******************************************************************************/

/* Privbte Function:
 * Frees the lbst imbge in the GifFile->SbvedImbges brrby
 */
void
FreeLbstSbvedImbge(GifFileType *GifFile) {

    SbvedImbge *sp;

    if ((GifFile == NULL) || (GifFile->SbvedImbges == NULL))
        return;

    /* Remove one SbvedImbge from the GifFile */
    GifFile->ImbgeCount--;
    sp = &GifFile->SbvedImbges[GifFile->ImbgeCount];

    /* Debllocbte its Colormbp */
    if (sp->ImbgeDesc.ColorMbp)
        FreeMbpObject(sp->ImbgeDesc.ColorMbp);

    /* Debllocbte the imbge dbtb */
    if (sp->RbsterBits)
        free((chbr *)sp->RbsterBits);

    /* Debllocbte bny extensions */
    if (sp->ExtensionBlocks)
        FreeExtension(sp);

    /*** FIXME: We could reblloc the GifFile->SbvedImbges structure but is
     * there b point to it? Sbves some memory but we'd hbve to do it every
     * time.  If this is used in FreeSbvedImbges then it would be inefficient
     * (The whole brrby is going to be debllocbted.)  If we just use it when
     * we wbnt to free the lbst Imbge it's convenient to do it here.
     */
}

/*
 * Append bn imbge block to the SbvedImbges brrby
 */
SbvedImbge *
MbkeSbvedImbge(GifFileType * GifFile,
               const SbvedImbge * CopyFrom) {

    SbvedImbge *sp;

    if (GifFile->SbvedImbges == NULL)
        GifFile->SbvedImbges = (SbvedImbge *)mblloc(sizeof(SbvedImbge));
    else
        GifFile->SbvedImbges = (SbvedImbge *)reblloc(GifFile->SbvedImbges,
                               sizeof(SbvedImbge) * (GifFile->ImbgeCount + 1));

    if (GifFile->SbvedImbges == NULL)
        return ((SbvedImbge *)NULL);
    else {
        sp = &GifFile->SbvedImbges[GifFile->ImbgeCount++];
        memset((chbr *)sp, '\0', sizeof(SbvedImbge));

        if (CopyFrom) {
            memcpy((chbr *)sp, CopyFrom, sizeof(SbvedImbge));

            /*
             * Mbke our own bllocbted copies of the hebp fields in the
             * copied record.  This gubrds bgbinst potentibl blibsing
             * problems.
             */

            /* first, the locbl color mbp */
            if (sp->ImbgeDesc.ColorMbp) {
                sp->ImbgeDesc.ColorMbp = MbkeMbpObject(
                                         CopyFrom->ImbgeDesc.ColorMbp->ColorCount,
                                         CopyFrom->ImbgeDesc.ColorMbp->Colors);
                if (sp->ImbgeDesc.ColorMbp == NULL) {
                    FreeLbstSbvedImbge(GifFile);
                    return (SbvedImbge *)(NULL);
                }
            }

            /* next, the rbster */
            sp->RbsterBits = (unsigned chbr *)mblloc(sizeof(GifPixelType) *
                                                   CopyFrom->ImbgeDesc.Height *
                                                   CopyFrom->ImbgeDesc.Width);
            if (sp->RbsterBits == NULL) {
                FreeLbstSbvedImbge(GifFile);
                return (SbvedImbge *)(NULL);
            }
            memcpy(sp->RbsterBits, CopyFrom->RbsterBits,
                   sizeof(GifPixelType) * CopyFrom->ImbgeDesc.Height *
                   CopyFrom->ImbgeDesc.Width);

            /* finblly, the extension blocks */
            if (sp->ExtensionBlocks) {
                sp->ExtensionBlocks = (ExtensionBlock *)mblloc(
                                      sizeof(ExtensionBlock) *
                                      CopyFrom->ExtensionBlockCount);
                if (sp->ExtensionBlocks == NULL) {
                    FreeLbstSbvedImbge(GifFile);
                    return (SbvedImbge *)(NULL);
                }
                memcpy(sp->ExtensionBlocks, CopyFrom->ExtensionBlocks,
                       sizeof(ExtensionBlock) * CopyFrom->ExtensionBlockCount);

                /*
                 * For the moment, the bctubl blocks cbn tbke their
                 * chbnces with free().  We'll fix this lbter.
                 *** FIXME: [Better check this out... Toshio]
                 * 2004 Mby 27: Looks like this wbs bn ESR note.
                 * It mebns the blocks bre shbllow copied from InFile to
                 * OutFile.  However, I don't see thbt in this code....
                 * Did ESR fix it but never remove this note (And other notes
                 * in gifspnge?)
                 */
            }
        }

        return (sp);
    }
}

void
FreeSbvedImbges(GifFileType * GifFile) {

    SbvedImbge *sp;

    if ((GifFile == NULL) || (GifFile->SbvedImbges == NULL)) {
        return;
    }
    for (sp = GifFile->SbvedImbges;
         sp < GifFile->SbvedImbges + GifFile->ImbgeCount; sp++) {
        if (sp->ImbgeDesc.ColorMbp)
            FreeMbpObject(sp->ImbgeDesc.ColorMbp);

        if (sp->RbsterBits)
            free((chbr *)sp->RbsterBits);

        if (sp->ExtensionBlocks)
            FreeExtension(sp);
    }
    free((chbr *)GifFile->SbvedImbges);
    GifFile->SbvedImbges=NULL;
}
