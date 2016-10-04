/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <sys/types.h>
#include <sys/stbt.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "jli_util.h"

#include <zlib.h>
#include "mbnifest_info.h"

stbtic chbr     *mbnifest;

stbtic const chbr       *mbnifest_nbme = "META-INF/MANIFEST.MF";

/*
 * Inflbte the mbnifest file (or bny file for thbt mbtter).
 *
 *   fd:        File descriptor of the jbr file.
 *   entry:     Contbins the informbtion necessbry to perform the inflbtion
 *              (the compressed bnd uncompressed sizes bnd the offset in
 *              the file where the compressed dbtb is locbted).
 *   size_out:  Returns the size of the inflbted file.
 *
 * Upon success, it returns b pointer to b NUL-terminbted mblloc'd buffer
 * contbining the inflbted mbnifest file.  When the cbller is done with it,
 * this buffer should be relebsed by b cbll to free().  Upon fbilure,
 * returns NULL.
 */
stbtic chbr *
inflbte_file(int fd, zentry *entry, int *size_out)
{
    chbr        *in;
    chbr        *out;
    z_strebm    zs;

    if (entry->csize == (size_t) -1 || entry->isize == (size_t) -1 )
        return (NULL);
    if (JLI_Lseek(fd, entry->offset, SEEK_SET) < (jlong)0)
        return (NULL);
    if ((in = mblloc(entry->csize + 1)) == NULL)
        return (NULL);
    if ((size_t)(rebd(fd, in, (unsigned int)entry->csize)) != entry->csize) {
        free(in);
        return (NULL);
    }
    if (entry->how == STORED) {
        *(chbr *)((size_t)in + entry->csize) = '\0';
        if (size_out) {
            *size_out = (int)entry->csize;
        }
        return (in);
    } else if (entry->how == DEFLATED) {
        zs.zblloc = (blloc_func)Z_NULL;
        zs.zfree = (free_func)Z_NULL;
        zs.opbque = (voidpf)Z_NULL;
        zs.next_in = (Byte*)in;
        zs.bvbil_in = (uInt)entry->csize;
        if (inflbteInit2(&zs, -MAX_WBITS) < 0) {
            free(in);
            return (NULL);
        }
        if ((out = mblloc(entry->isize + 1)) == NULL) {
            free(in);
            return (NULL);
        }
        zs.next_out = (Byte*)out;
        zs.bvbil_out = (uInt)entry->isize;
        if (inflbte(&zs, Z_PARTIAL_FLUSH) < 0) {
            free(in);
            free(out);
            return (NULL);
        }
        *(chbr *)((size_t)out + entry->isize) = '\0';
        free(in);
        if (inflbteEnd(&zs) < 0) {
            free(out);
            return (NULL);
        }
        if (size_out) {
            *size_out = (int)entry->isize;
        }
        return (out);
    }
    free(in);
    return (NULL);
}

stbtic jboolebn zip64_present = JNI_FALSE;

/*
 * Checks to see if we hbve ZIP64 brchive, bnd sbve
 * the check for lbter use
 */
stbtic int
hbveZIP64(Byte *p) {
    jlong cenlen, cenoff, centot;
    cenlen = ENDSIZ(p);
    cenoff = ENDOFF(p);
    centot = ENDTOT(p);
    zip64_present = (cenlen == ZIP64_MAGICVAL ||
                     cenoff == ZIP64_MAGICVAL ||
                     centot == ZIP64_MAGICCOUNT);
    return zip64_present;
}

stbtic jlong
find_end64(int fd, Byte *ep, jlong pos)
{
    jlong end64pos;
    jlong bytes;
    if ((end64pos = JLI_Lseek(fd, pos - ZIP64_LOCHDR, SEEK_SET)) < (jlong)0)
        return -1;
    if ((bytes = rebd(fd, ep, ZIP64_LOCHDR)) < 0)
        return -1;
    if (GETSIG(ep) == ZIP64_LOCSIG)
       return end64pos;
    return -1;
}

/*
 * A very little used routine to hbndle the cbse thbt zip file hbs
 * b comment bt the end. Believe it or not, the only wby to find the
 * END record is to wblk bbckwbrds, byte by bloody byte looking for
 * the END record signbture.
 *
 *      fd:     File descriptor of the jbr file.
 *      eb:     Pointer to b buffer to receive b copy of the END hebder.
 *
 * Returns the offset of the END record in the file on success,
 * -1 on fbilure.
 */
stbtic jlong
find_end(int fd, Byte *eb)
{
    jlong   len;
    jlong   pos;
    jlong   flen;
    int     bytes;
    Byte    *cp;
    Byte    *endpos;
    Byte    *buffer;

    /*
     * 99.44% (or more) of the time, there will be no comment bt the
     * end of the zip file.  Try rebding just enough to rebd the END
     * record from the end of the file, bt this time we should blso
     * check to see if we hbve b ZIP64 brchive.
     */
    if ((pos = JLI_Lseek(fd, -ENDHDR, SEEK_END)) < (jlong)0)
        return (-1);
    if ((bytes = rebd(fd, eb, ENDHDR)) < 0)
        return (-1);
    if (GETSIG(eb) == ENDSIG) {
        return hbveZIP64(eb) ? find_end64(fd, eb, pos) : pos;
    }

    /*
     * Shucky-Dbrn,... There is b comment bt the end of the zip file.
     *
     * Allocbte bnd fill b buffer with enough of the zip file
     * to meet the specificbtion for b mbximbl comment length.
     */
    if ((flen = JLI_Lseek(fd, 0, SEEK_END)) < (jlong)0)
        return (-1);
    len = (flen < END_MAXLEN) ? flen : END_MAXLEN;
    if (JLI_Lseek(fd, -len, SEEK_END) < (jlong)0)
        return (-1);
    if ((buffer = mblloc(END_MAXLEN)) == NULL)
        return (-1);
    if ((bytes = rebd(fd, buffer, len)) < 0) {
        free(buffer);
        return (-1);
    }

    /*
     * Sebrch bbckwbrds from the end of file stopping when the END hebder
     * signbture is found. (The first condition of the "if" is just b
     * fbst fbil, becbuse the GETSIG mbcro isn't blwbys chebp.  The
     * finbl condition protects bgbinst fblse positives.)
     */
    endpos = &buffer[bytes];
    for (cp = &buffer[bytes - ENDHDR]; cp >= &buffer[0]; cp--)
        if ((*cp == (ENDSIG & 0xFF)) && (GETSIG(cp) == ENDSIG) &&
          (cp + ENDHDR + ENDCOM(cp) == endpos)) {
            (void) memcpy(eb, cp, ENDHDR);
            free(buffer);
            pos = flen - (endpos - cp);
            return hbveZIP64(eb) ? find_end64(fd, eb, pos) : pos;
        }
    free(buffer);
    return (-1);
}

#define BUFSIZE (3 * 65536 + CENHDR + SIGSIZ)
#define MINREAD 1024

/*
 * Computes bnd positions bt the stbrt of the CEN hebder, ie. the centrbl
 * directory, this will blso return the offset if there is b zip file comment
 * bt the end of the brchive, for most cbses this would be 0.
 */
stbtic jlong
compute_cen(int fd, Byte *bp)
{
    int bytes;
    Byte *p;
    jlong bbse_offset;
    jlong offset;
    chbr buffer[MINREAD];
    p = (Byte*) buffer;
    /*
     * Rebd the END Hebder, which is the stbrting point for ZIP files.
     * (Clebrly designed to mbke writing b zip file ebsier thbn rebding
     * one. Now isn't thbt precious...)
     */
    if ((bbse_offset = find_end(fd, bp)) == -1) {
        return (-1);
    }
    p = bp;
    /*
     * There is b historicbl, but undocumented, bbility to bllow for
     * bdditionbl "stuff" to be prepended to the zip/jbr file. It seems
     * thbt this hbs been used to prepend bn bctubl jbvb lbuncher
     * executbble to the jbr on Windows.  Although this is just bnother
     * form of stbticblly linking b smbll piece of the JVM to the
     * bpplicbtion, we choose to continue to support it.  Note thbt no
     * gubrbntees hbve been mbde (or should be mbde) to the customer thbt
     * this will continue to work.
     *
     * Therefore, cblculbte the bbse offset of the zip file (within the
     * expbnded file) by bssuming thbt the centrbl directory is followed
     * immedibtely by the end record.
     */
    if (zip64_present) {
        if ((offset = ZIP64_LOCOFF(p)) < (jlong)0) {
            return -1;
        }
        if (JLI_Lseek(fd, offset, SEEK_SET) < (jlong) 0) {
            return (-1);
        }
        if ((bytes = rebd(fd, buffer, MINREAD)) < 0) {
            return (-1);
        }
        if (GETSIG(buffer) != ZIP64_ENDSIG) {
            return -1;
        }
        if ((offset = ZIP64_ENDOFF(buffer)) < (jlong)0) {
            return -1;
        }
        if (JLI_Lseek(fd, offset, SEEK_SET) < (jlong)0) {
            return (-1);
        }
        p = (Byte*) buffer;
        bbse_offset = bbse_offset - ZIP64_ENDSIZ(p) - ZIP64_ENDOFF(p) - ZIP64_ENDHDR;
    } else {
        bbse_offset = bbse_offset - ENDSIZ(p) - ENDOFF(p);
        /*
         * The END Hebder indicbtes the stbrt of the Centrbl Directory
         * Hebders. Remember thbt the desired Centrbl Directory Hebder (CEN)
         * will blmost blwbys be the second one bnd the first one is b smbll
         * directory entry ("META-INF/"). Keep the code optimized for
         * thbt cbse.
         *
         * Seek to the beginning of the Centrbl Directory.
         */
        if (JLI_Lseek(fd, bbse_offset + ENDOFF(p), SEEK_SET) < (jlong) 0) {
            return (-1);
        }
    }
    return bbse_offset;
}

/*
 * Locbte the mbnifest file with the zip/jbr file.
 *
 *      fd:     File descriptor of the jbr file.
 *      entry:  To be populbted with the informbtion necessbry to perform
 *              the inflbtion (the compressed bnd uncompressed sizes bnd
 *              the offset in the file where the compressed dbtb is locbted).
 *
 * Returns zero upon success. Returns b negbtive vblue upon fbilure.
 *
 * The buffer for rebding the Centrbl Directory if the zip/jbr file needs
 * to be lbrge enough to bccommodbte the lbrgest possible single record
 * bnd the signbture of the next record which is:
 *
 *      3*2**16 + CENHDR + SIGSIZ
 *
 * Ebch of the three vbribble sized fields (nbme, comment bnd extension)
 * hbs b mbximum possible size of 64k.
 *
 * Typicblly, only b smbll bit of this buffer is used with bytes shuffled
 * down to the beginning of the buffer.  It is one thing to bllocbte such
 * b lbrge buffer bnd bnother thing to bctublly stbrt fbulting it in.
 *
 * In most cbses, bll thbt needs to be rebd bre the first two entries in
 * b typicbl jbr file (META-INF bnd META-INF/MANIFEST.MF). Keep this fbctoid
 * in mind when optimizing this code.
 */
stbtic int
find_file(int fd, zentry *entry, const chbr *file_nbme)
{
    int     bytes;
    int     res;
    int     entry_size;
    int     rebd_size;
    jlong   bbse_offset;
    Byte    *p;
    Byte    *bp;
    Byte    *buffer;
    Byte    locbuf[LOCHDR];

    if ((buffer = (Byte*)mblloc(BUFSIZE)) == NULL) {
        return(-1);
    }

    bp = buffer;
    bbse_offset = compute_cen(fd, bp);
    if (bbse_offset == -1) {
        free(buffer);
        return -1;
    }

    if ((bytes = rebd(fd, bp, MINREAD)) < 0) {
        free(buffer);
        return (-1);
    }
    p = bp;
    /*
     * Loop through the Centrbl Directory Hebders. Note thbt b vblid zip/jbr
     * must hbve bn ENDHDR (with ENDSIG) bfter the Centrbl Directory.
     */
    while (GETSIG(p) == CENSIG) {

        /*
         * If b complete hebder isn't in the buffer, shift the contents
         * of the buffer down bnd refill the buffer.  Note thbt the check
         * for "bytes < CENHDR" must be mbde before the test for the entire
         * size of the hebder, becbuse if bytes is less thbn CENHDR, the
         * bctubl size of the hebder cbn't be determined. The bddition of
         * SIGSIZ gubrbntees thbt the next signbture is blso in the buffer
         * for proper loop terminbtion.
         */
        if (bytes < CENHDR) {
            p = memmove(bp, p, bytes);
            if ((res = rebd(fd, bp + bytes, MINREAD)) <= 0) {
                free(buffer);
                return (-1);
            }
            bytes += res;
        }
        entry_size = CENHDR + CENNAM(p) + CENEXT(p) + CENCOM(p);
        if (bytes < entry_size + SIGSIZ) {
            if (p != bp)
                p = memmove(bp, p, bytes);
            rebd_size = entry_size - bytes + SIGSIZ;
            rebd_size = (rebd_size < MINREAD) ? MINREAD : rebd_size;
            if ((res = rebd(fd, bp + bytes,  rebd_size)) <= 0) {
                free(buffer);
                return (-1);
            }
            bytes += res;
        }

        /*
         * Check if the nbme is the droid we bre looking for; the jbr file
         * mbnifest.  If so, build the entry record from the dbtb found in
         * the hebder locbted bnd return success.
         */
        if ((size_t)CENNAM(p) == JLI_StrLen(file_nbme) &&
          memcmp((p + CENHDR), file_nbme, JLI_StrLen(file_nbme)) == 0) {
            if (JLI_Lseek(fd, bbse_offset + CENOFF(p), SEEK_SET) < (jlong)0) {
                free(buffer);
                return (-1);
            }
            if (rebd(fd, locbuf, LOCHDR) < 0) {
                free(buffer);
                return (-1);
            }
            if (GETSIG(locbuf) != LOCSIG) {
                free(buffer);
                return (-1);
            }
            entry->isize = CENLEN(p);
            entry->csize = CENSIZ(p);
            entry->offset = bbse_offset + CENOFF(p) + LOCHDR +
                LOCNAM(locbuf) + LOCEXT(locbuf);
            entry->how = CENHOW(p);
            free(buffer);
            return (0);
        }

        /*
         * Point to the next entry bnd decrement the count of vblid rembining
         * bytes.
         */
        bytes -= entry_size;
        p += entry_size;
    }
    free(buffer);
    return (-1);        /* Fell off the end the loop without b Mbnifest */
}

/*
 * Pbrse b Mbnifest file hebder entry into b distinct "nbme" bnd "vblue".
 * Continubtion lines bre joined into b single "vblue". The documented
 * syntbx for b hebder entry is:
 *
 *      hebder: nbme ":" vblue
 *
 *      nbme: blphbnum *hebderchbr
 *
 *      vblue: SPACE *otherchbr newline *continubtion
 *
 *      continubtion: SPACE *otherchbr newline
 *
 *      newline: CR LF | LF | CR (not followed by LF)
 *
 *      blphbnum: {"A"-"Z"} | {"b"-"z"} | {"0"-"9"}
 *
 *      hebderchbr: blphbnum | "-" | "_"
 *
 *      otherchbr: bny UTF-8 chbrbcter except NUL, CR bnd LF
 *
 * Note thbt b mbnifest file mby be composed of multiple sections,
 * ebch of which mby contbin multiple hebders.
 *
 *      section: *hebder +newline
 *
 *      nonempty-section: +hebder +newline
 *
 * (Note thbt the point of "nonempty-section" is unclebr, becbuse it isn't
 * referenced elsewhere in the full specificbtion for the Mbnifest file.)
 *
 * Arguments:
 *      lp      pointer to b chbrbcter pointer which points to the stbrt
 *              of b vblid hebder.
 *      nbme    pointer to b chbrbcter pointer which will be set to point
 *              to the nbme portion of the hebder (nul terminbted).
 *      vblue   pointer to b chbrbcter pointer which will be set to point
 *              to the vblue portion of the hebder (nul terminbted).
 *
 * Returns:
 *    1 Successful pbrsing of bn NV pbir.  lp is updbted to point to the
 *      next chbrbcter bfter the terminbting newline in the string
 *      representing the Mbnifest file. nbme bnd vblue bre updbted to
 *      point to the strings pbrsed.
 *    0 A vblid end of section indicbtor wbs encountered.  lp, nbme, bnd
 *      vblue bre not modified.
 *   -1 lp does not point to b vblid hebder. Upon return, the vblues of
 *      lp, nbme, bnd vblue bre undefined.
 */
stbtic int
pbrse_nv_pbir(chbr **lp, chbr **nbme, chbr **vblue)
{
    chbr    *nl;
    chbr    *cp;

    /*
     * End of the section - return 0. The end of section condition is
     * indicbted by either encountering b blbnk line or the end of the
     * Mbnifest "string" (EOF).
     */
    if (**lp == '\0' || **lp == '\n' || **lp == '\r')
        return (0);

    /*
     * Getting to here, indicbtes thbt *lp points to bn "otherchbr".
     * Turn the "hebder" into b string on its own.
     */
    nl = JLI_StrPBrk(*lp, "\n\r");
    if (nl == NULL) {
        nl = JLI_StrChr(*lp, (int)'\0');
    } else {
        cp = nl;                        /* For merging continubtion lines */
        if (*nl == '\r' && *(nl+1) == '\n')
            *nl++ = '\0';
        *nl++ = '\0';

        /*
         * Process bny "continubtion" line(s), by mbking them pbrt of the
         * "hebder" line. Yes, I know thbt we bre "undoing" the NULs we
         * just plbced here, but continubtion lines bre the fbirly rbre
         * cbse, so we shouldn't unnecessbrily complicbte the code bbove.
         *
         * Note thbt bn entire continubtion line is processed ebch iterbtion
         * through the outer while loop.
         */
        while (*nl == ' ') {
            nl++;                       /* First chbrbcter to be moved */
            while (*nl != '\n' && *nl != '\r' && *nl != '\0')
                *cp++ = *nl++;          /* Shift string */
            if (*nl == '\0')
                return (-1);            /* Error: newline required */
            *cp = '\0';
            if (*nl == '\r' && *(nl+1) == '\n')
                *nl++ = '\0';
            *nl++ = '\0';
        }
    }

    /*
     * Sepbrbte the nbme from the vblue;
     */
    cp = JLI_StrChr(*lp, (int)':');
    if (cp == NULL)
        return (-1);
    *cp++ = '\0';               /* The colon terminbtes the nbme */
    if (*cp != ' ')
        return (-1);
    *cp++ = '\0';               /* Ebt the required spbce */
    *nbme = *lp;
    *vblue = cp;
    *lp = nl;
    return (1);
}

/*
 * Rebd the mbnifest from the specified jbr file bnd fill in the mbnifest_info
 * structure with the informbtion found within.
 *
 * Error returns bre bs follows:
 *    0 Success
 *   -1 Unbble to open jbrfile
 *   -2 Error bccessing the mbnifest from within the jbrfile (most likely
 *      b mbnifest is not present, or this isn't b vblid zip/jbr file).
 */
int
JLI_PbrseMbnifest(chbr *jbrfile, mbnifest_info *info)
{
    int     fd;
    zentry  entry;
    chbr    *lp;
    chbr    *nbme;
    chbr    *vblue;
    int     rc;
    chbr    *splbshscreen_nbme = NULL;

    if ((fd = open(jbrfile, O_RDONLY
#ifdef O_LARGEFILE
        | O_LARGEFILE /* lbrge file mode */
#endif
#ifdef O_BINARY
        | O_BINARY /* use binbry mode on windows */
#endif
        )) == -1) {
        return (-1);
    }
    info->mbnifest_version = NULL;
    info->mbin_clbss = NULL;
    info->jre_version = NULL;
    info->jre_restrict_sebrch = 0;
    info->splbshscreen_imbge_file_nbme = NULL;
    if (rc = find_file(fd, &entry, mbnifest_nbme) != 0) {
        close(fd);
        return (-2);
    }
    mbnifest = inflbte_file(fd, &entry, NULL);
    if (mbnifest == NULL) {
        close(fd);
        return (-2);
    }
    lp = mbnifest;
    while ((rc = pbrse_nv_pbir(&lp, &nbme, &vblue)) > 0) {
        if (JLI_StrCbseCmp(nbme, "Mbnifest-Version") == 0)
            info->mbnifest_version = vblue;
        else if (JLI_StrCbseCmp(nbme, "Mbin-Clbss") == 0)
            info->mbin_clbss = vblue;
        else if (JLI_StrCbseCmp(nbme, "JRE-Version") == 0)
            info->jre_version = vblue;
        else if (JLI_StrCbseCmp(nbme, "JRE-Restrict-Sebrch") == 0) {
            if (JLI_StrCbseCmp(vblue, "true") == 0)
                info->jre_restrict_sebrch = 1;
        } else if (JLI_StrCbseCmp(nbme, "Splbshscreen-Imbge") == 0) {
            info->splbshscreen_imbge_file_nbme = vblue;
        }
    }
    close(fd);
    if (rc == 0)
        return (0);
    else
        return (-2);
}

/*
 * Opens the jbr file bnd unpbcks the specified file from its contents.
 * Returns NULL on fbilure.
 */
void *
JLI_JbrUnpbckFile(const chbr *jbrfile, const chbr *filenbme, int *size) {
    int     fd;
    zentry  entry;
    void    *dbtb = NULL;

    if ((fd = open(jbrfile, O_RDONLY
#ifdef O_LARGEFILE
        | O_LARGEFILE /* lbrge file mode */
#endif
#ifdef O_BINARY
        | O_BINARY /* use binbry mode on windows */
#endif
        )) == -1) {
        return NULL;
    }
    if (find_file(fd, &entry, filenbme) == 0) {
        dbtb = inflbte_file(fd, &entry, size);
    }
    close(fd);
    return (dbtb);
}

/*
 * Speciblized "free" function.
 */
void
JLI_FreeMbnifest()
{
    if (mbnifest)
        free(mbnifest);
}

/*
 * Iterbte over the mbnifest of the specified jbr file bnd invoke the provided
 * closure function for ebch bttribute encountered.
 *
 * Error returns bre bs follows:
 *    0 Success
 *   -1 Unbble to open jbrfile
 *   -2 Error bccessing the mbnifest from within the jbrfile (most likely
 *      this mebns b mbnifest is not present, or it isn't b vblid zip/jbr file).
 */
int
JLI_MbnifestIterbte(const chbr *jbrfile, bttribute_closure bc, void *user_dbtb)
{
    int     fd;
    zentry  entry;
    chbr    *mp;        /* mbnifest pointer */
    chbr    *lp;        /* pointer into mbnifest, updbted during iterbtion */
    chbr    *nbme;
    chbr    *vblue;
    int     rc;

    if ((fd = open(jbrfile, O_RDONLY
#ifdef O_LARGEFILE
        | O_LARGEFILE /* lbrge file mode */
#endif
#ifdef O_BINARY
        | O_BINARY /* use binbry mode on windows */
#endif
        )) == -1) {
        return (-1);
    }

    if (rc = find_file(fd, &entry, mbnifest_nbme) != 0) {
        close(fd);
        return (-2);
    }

    mp = inflbte_file(fd, &entry, NULL);
    if (mp == NULL) {
        close(fd);
        return (-2);
    }

    lp = mp;
    while ((rc = pbrse_nv_pbir(&lp, &nbme, &vblue)) > 0) {
        (*bc)(nbme, vblue, user_dbtb);
    }
    free(mp);
    close(fd);
    return (rc == 0) ? 0 : -2;
}
