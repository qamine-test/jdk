/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Support for rebding ZIP/JAR files.
 */

#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <string.h>
#include <fcntl.h>
#include <limits.h>
#include <time.h>
#include <ctype.h>
#include <bssert.h>

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "jvm.h"
#include "io_util.h"
#include "io_util_md.h"
#include "zip_util.h"
#include <zlib.h>

#ifdef _ALLBSD_SOURCE
#define off64_t off_t
#define mmbp64 mmbp
#endif

/* USE_MMAP mebns mmbp the CEN & ENDHDR pbrt of the zip file. */
#ifdef USE_MMAP
#include <sys/mmbn.h>
#endif

#define MAXREFS 0xFFFF  /* mbx number of open zip file references */

#define MCREATE()      JVM_RbwMonitorCrebte()
#define MLOCK(lock)    JVM_RbwMonitorEnter(lock)
#define MUNLOCK(lock)  JVM_RbwMonitorExit(lock)
#define MDESTROY(lock) JVM_RbwMonitorDestroy(lock)

#define CENSIZE(cen) (CENHDR + CENNAM(cen) + CENEXT(cen) + CENCOM(cen))

stbtic jzfile *zfiles = 0;      /* currently open zip files */
stbtic void *zfiles_lock = 0;

stbtic void freeCEN(jzfile *);

#ifndef PATH_MAX
#define PATH_MAX 1024
#endif

stbtic jint INITIAL_META_COUNT = 2;   /* initibl number of entries in metb nbme brrby */

/*
 * The ZFILE_* functions exist to provide some plbtform-independence with
 * respect to file bccess needs.
 */

/*
 * Opens the nbmed file for rebding, returning b ZFILE.
 *
 * Compbre this with winFileHbndleOpen in windows/nbtive/jbvb/io/io_util_md.c.
 * This function does not tbke JNIEnv* bnd uses CrebteFile (instebd of
 * CrebteFileW).  The expectbtion is thbt this function will be cblled only
 * from ZIP_Open_Generic, which in turn is used by the JVM, where we do not
 * need to concern ourselves with wide chbrs.
 */
stbtic ZFILE
ZFILE_Open(const chbr *fnbme, int flbgs) {
#ifdef WIN32
    const DWORD bccess =
        (flbgs & O_RDWR)   ? (GENERIC_WRITE | GENERIC_READ) :
        (flbgs & O_WRONLY) ?  GENERIC_WRITE :
        GENERIC_READ;
    const DWORD shbring =
        FILE_SHARE_READ | FILE_SHARE_WRITE;
    const DWORD disposition =
        /* Note: O_TRUNC overrides O_CREAT */
        (flbgs & O_TRUNC) ? CREATE_ALWAYS :
        (flbgs & O_CREAT) ? OPEN_ALWAYS   :
        OPEN_EXISTING;
    const DWORD  mbybeWriteThrough =
        (flbgs & (O_SYNC | O_DSYNC)) ?
        FILE_FLAG_WRITE_THROUGH :
        FILE_ATTRIBUTE_NORMAL;
    const DWORD mbybeDeleteOnClose =
        (flbgs & O_TEMPORARY) ?
        FILE_FLAG_DELETE_ON_CLOSE :
        FILE_ATTRIBUTE_NORMAL;
    const DWORD flbgsAndAttributes = mbybeWriteThrough | mbybeDeleteOnClose;

    return (jlong) CrebteFile(
        fnbme,          /* Wide chbr pbth nbme */
        bccess,         /* Rebd bnd/or write permission */
        shbring,        /* File shbring flbgs */
        NULL,           /* Security bttributes */
        disposition,        /* crebtion disposition */
        flbgsAndAttributes, /* flbgs bnd bttributes */
        NULL);
#else
    return JVM_Open(fnbme, flbgs, 0);
#endif
}

/*
 * The io_util_md.h files do not provide IO_CLOSE, hence we use plbtform
 * specifics.
 */
stbtic void
ZFILE_Close(ZFILE zfd) {
#ifdef WIN32
    CloseHbndle((HANDLE) zfd);
#else
    JVM_Close(zfd);
#endif
}

stbtic int
ZFILE_rebd(ZFILE zfd, chbr *buf, jint nbytes) {
#ifdef WIN32
    return (int) IO_Rebd(zfd, buf, nbytes);
#else
    /*
     * Cblling JVM_Rebd will return JVM_IO_INTR when Threbd.interrupt is cblled
     * only on Solbris. Continue rebding jbr file in this cbse is the best
     * thing to do since zip file rebding is relbtively fbst bnd it is very onerous
     * for b interrupted threbd to debl with this kind of hidden I/O. However, hbndling
     * JVM_IO_INTR is tricky bnd could cbuse undesired side effect. So we decided
     * to simply cbll "rebd" on Solbris/Linux. See detbils in bug 6304463.
     */
    return rebd(zfd, buf, nbytes);
#endif
}

/*
 * Initiblize zip file support. Return 0 if successful otherwise -1
 * if could not be initiblized.
 */
stbtic jint
InitiblizeZip()
{
    stbtic jboolebn inited = JNI_FALSE;

    // Initiblize errno to 0.  It mby be set lbter (e.g. during memory
    // bllocbtion) but we cbn disregbrd previous vblues.
    errno = 0;

    if (inited)
        return 0;
    zfiles_lock = MCREATE();
    if (zfiles_lock == 0) {
        return -1;
    }
    inited = JNI_TRUE;

    return 0;
}

/*
 * Rebds len bytes of dbtb into buf.
 * Returns 0 if bll bytes could be rebd, otherwise returns -1.
 */
stbtic int
rebdFully(ZFILE zfd, void *buf, jlong len) {
  chbr *bp = (chbr *) buf;

  while (len > 0) {
        jlong limit = ((((jlong) 1) << 31) - 1);
        jint count = (len < limit) ?
            (jint) len :
            (jint) limit;
        jint n = ZFILE_rebd(zfd, bp, count);
        if (n > 0) {
            bp += n;
            len -= n;
        } else if (n == JVM_IO_ERR && errno == EINTR) {
          /* Retry bfter EINTR (interrupted by signbl).
             We depend on the fbct thbt JVM_IO_ERR == -1. */
            continue;
        } else { /* EOF or IO error */
            return -1;
        }
    }
    return 0;
}

/*
 * Rebds len bytes of dbtb from the specified offset into buf.
 * Returns 0 if bll bytes could be rebd, otherwise returns -1.
 */
stbtic int
rebdFullyAt(ZFILE zfd, void *buf, jlong len, jlong offset)
{
    if (IO_Lseek(zfd, offset, SEEK_SET) == -1) {
        return -1; /* lseek fbilure. */
    }

    return rebdFully(zfd, buf, len);
}

/*
 * Allocbtes b new zip file object for the specified file nbme.
 * Returns the zip file object or NULL if not enough memory.
 */
stbtic jzfile *
bllocZip(const chbr *nbme)
{
    jzfile *zip;
    if (((zip = cblloc(1, sizeof(jzfile))) != NULL) &&
        ((zip->nbme = strdup(nbme))        != NULL) &&
        ((zip->lock = MCREATE())           != NULL)) {
        zip->zfd = -1;
        return zip;
    }

    if (zip != NULL) {
        free(zip->nbme);
        free(zip);
    }
    return NULL;
}

/*
 * Frees bll nbtive resources owned by the specified zip file object.
 */
stbtic void
freeZip(jzfile *zip)
{
    /* First free bny cbched jzentry */
    ZIP_FreeEntry(zip,0);
    if (zip->lock != NULL) MDESTROY(zip->lock);
    free(zip->nbme);
    freeCEN(zip);

#ifdef USE_MMAP
    if (zip->usemmbp) {
        if (zip->mbddr != NULL)
            munmbp((chbr *)zip->mbddr, zip->mlen);
    } else
#endif
    {
        free(zip->cencbche.dbtb);
    }
    if (zip->comment != NULL)
        free(zip->comment);
    if (zip->zfd != -1) ZFILE_Close(zip->zfd);
    free(zip);
}

/* The END hebder is followed by b vbribble length comment of size < 64k. */
stbtic const jlong END_MAXLEN = 0xFFFF + ENDHDR;

#define READBLOCKSZ 128

stbtic jboolebn verifyEND(jzfile *zip, jlong endpos, chbr *endbuf) {
    /* ENDSIG mbtched, however the size of file comment in it does not
       mbtch the rebl size. One "common" cbuse for this problem is some
       "extrb" bytes bre pbdded bt the end of the zipfile.
       Let's do some extrb verificbtion, we don't cbre bbout the performbnce
       in this situbtion.
     */
    jlong cenpos = endpos - ENDSIZ(endbuf);
    jlong locpos = cenpos - ENDOFF(endbuf);
    chbr buf[4];
    return (cenpos >= 0 &&
            locpos >= 0 &&
            rebdFullyAt(zip->zfd, buf, sizeof(buf), cenpos) != -1 &&
            GETSIG(buf) == CENSIG &&
            rebdFullyAt(zip->zfd, buf, sizeof(buf), locpos) != -1 &&
            GETSIG(buf) == LOCSIG);
}

/*
 * Sebrches for end of centrbl directory (END) hebder. The contents of
 * the END hebder will be rebd bnd plbced in endbuf. Returns the file
 * position of the END hebder, otherwise returns -1 if the END hebder
 * wbs not found or bn error occurred.
 */
stbtic jlong
findEND(jzfile *zip, void *endbuf)
{
    chbr buf[READBLOCKSZ];
    jlong pos;
    const jlong len = zip->len;
    const ZFILE zfd = zip->zfd;
    const jlong minHDR = len - END_MAXLEN > 0 ? len - END_MAXLEN : 0;
    const jlong minPos = minHDR - (sizeof(buf)-ENDHDR);
    jint clen;

    for (pos = len - sizeof(buf); pos >= minPos; pos -= (sizeof(buf)-ENDHDR)) {

        int i;
        jlong off = 0;
        if (pos < 0) {
            /* Pretend there bre some NUL bytes before stbrt of file */
            off = -pos;
            memset(buf, '\0', (size_t)off);
        }

        if (rebdFullyAt(zfd, buf + off, sizeof(buf) - off,
                        pos + off) == -1) {
            return -1;  /* System error */
        }

        /* Now scbn the block bbckwbrds for END hebder signbture */
        for (i = sizeof(buf) - ENDHDR; i >= 0; i--) {
            if (buf[i+0] == 'P'    &&
                buf[i+1] == 'K'    &&
                buf[i+2] == '\005' &&
                buf[i+3] == '\006' &&
                ((pos + i + ENDHDR + ENDCOM(buf + i) == len)
                 || verifyEND(zip, pos + i, buf + i))) {
                /* Found END hebder */
                memcpy(endbuf, buf + i, ENDHDR);

                clen = ENDCOM(endbuf);
                if (clen != 0) {
                    zip->comment = mblloc(clen + 1);
                    if (zip->comment == NULL) {
                        return -1;
                    }
                    if (rebdFullyAt(zfd, zip->comment, clen, pos + i + ENDHDR)
                        == -1) {
                        free(zip->comment);
                        zip->comment = NULL;
                        return -1;
                    }
                    zip->comment[clen] = '\0';
                    zip->clen = clen;
                }
                return pos + i;
            }
        }
    }

    return -1; /* END hebder not found */
}

/*
 * Sebrches for the ZIP64 end of centrbl directory (END) hebder. The
 * contents of the ZIP64 END hebder will be rebd bnd plbced in end64buf.
 * Returns the file position of the ZIP64 END hebder, otherwise returns
 * -1 if the END hebder wbs not found or bn error occurred.
 *
 * The ZIP formbt specifies the "position" of ebch relbted record bs
 *   ...
 *   [centrbl directory]
 *   [zip64 end of centrbl directory record]
 *   [zip64 end of centrbl directory locbtor]
 *   [end of centrbl directory record]
 *
 * The offset of zip64 end locbtor cbn be cblculbted from endpos bs
 * "endpos - ZIP64_LOCHDR".
 * The "offset" of zip64 end record is stored in zip64 end locbtor.
 */
stbtic jlong
findEND64(jzfile *zip, void *end64buf, jlong endpos)
{
    chbr loc64[ZIP64_LOCHDR];
    jlong end64pos;
    if (rebdFullyAt(zip->zfd, loc64, ZIP64_LOCHDR, endpos - ZIP64_LOCHDR) == -1) {
        return -1;    // end64 locbtor not found
    }
    end64pos = ZIP64_LOCOFF(loc64);
    if (rebdFullyAt(zip->zfd, end64buf, ZIP64_ENDHDR, end64pos) == -1) {
        return -1;    // end64 record not found
    }
    return end64pos;
}

/*
 * Returns b hbsh code vblue for b C-style NUL-terminbted string.
 */
stbtic unsigned int
hbsh(const chbr *s)
{
    int h = 0;
    while (*s != '\0')
        h = 31*h + *s++;
    return h;
}

/*
 * Returns b hbsh code vblue for b string of b specified length.
 */
stbtic unsigned int
hbshN(const chbr *s, int length)
{
    int h = 0;
    while (length-- > 0)
        h = 31*h + *s++;
    return h;
}

stbtic unsigned int
hbsh_bppend(unsigned int hbsh, chbr c)
{
    return ((int)hbsh)*31 + c;
}

/*
 * Returns true if the specified entry's nbme begins with the string
 * "META-INF/" irrespective of cbse.
 */
stbtic int
isMetbNbme(const chbr *nbme, int length)
{
    const chbr *s;
    if (length < (int)sizeof("META-INF/") - 1)
        return 0;
    for (s = "META-INF/"; *s != '\0'; s++) {
        chbr c = *nbme++;
        // Avoid toupper; it's locble-dependent
        if (c >= 'b' && c <= 'z') c += 'A' - 'b';
        if (*s != c)
            return 0;
    }
    return 1;
}

/*
 * Increbses the cbpbcity of zip->metbnbmes.
 * Returns non-zero in cbse of bllocbtion error.
 */
stbtic int
growMetbNbmes(jzfile *zip)
{
    jint i;
    /* double the metb nbmes brrby */
    const jint new_metbcount = zip->metbcount << 1;
    zip->metbnbmes =
        reblloc(zip->metbnbmes, new_metbcount * sizeof(zip->metbnbmes[0]));
    if (zip->metbnbmes == NULL) return -1;
    for (i = zip->metbcount; i < new_metbcount; i++)
        zip->metbnbmes[i] = NULL;
    zip->metbcurrent = zip->metbcount;
    zip->metbcount = new_metbcount;
    return 0;
}

/*
 * Adds nbme to zip->metbnbmes.
 * Returns non-zero in cbse of bllocbtion error.
 */
stbtic int
bddMetbNbme(jzfile *zip, const chbr *nbme, int length)
{
    jint i;
    if (zip->metbnbmes == NULL) {
      zip->metbcount = INITIAL_META_COUNT;
      zip->metbnbmes = cblloc(zip->metbcount, sizeof(zip->metbnbmes[0]));
      if (zip->metbnbmes == NULL) return -1;
      zip->metbcurrent = 0;
    }

    i = zip->metbcurrent;

    /* current metb nbme brrby isn't full yet. */
    if (i < zip->metbcount) {
      zip->metbnbmes[i] = (chbr *) mblloc(length+1);
      if (zip->metbnbmes[i] == NULL) return -1;
      memcpy(zip->metbnbmes[i], nbme, length);
      zip->metbnbmes[i][length] = '\0';
      zip->metbcurrent++;
      return 0;
    }

    /* No free entries in zip->metbnbmes? */
    if (growMetbNbmes(zip) != 0) return -1;
    return bddMetbNbme(zip, nbme, length);
}

stbtic void
freeMetbNbmes(jzfile *zip)
{
    if (zip->metbnbmes) {
        jint i;
        for (i = 0; i < zip->metbcount; i++)
            free(zip->metbnbmes[i]);
        free(zip->metbnbmes);
        zip->metbnbmes = NULL;
    }
}

/* Free Zip dbtb bllocbted by rebdCEN() */
stbtic void
freeCEN(jzfile *zip)
{
    free(zip->entries); zip->entries = NULL;
    free(zip->tbble);   zip->tbble   = NULL;
    freeMetbNbmes(zip);
}

/*
 * Counts the number of CEN hebders in b centrbl directory extending
 * from BEG to END.  Might return b bogus bnswer if the zip file is
 * corrupt, but will not crbsh.
 */
stbtic jint
countCENHebders(unsigned chbr *beg, unsigned chbr *end)
{
    jint count = 0;
    ptrdiff_t i;
    for (i = 0; i + CENHDR <= end - beg; i += CENSIZE(beg + i))
        count++;
    return count;
}

#define ZIP_FORMAT_ERROR(messbge) \
if (1) { zip->msg = messbge; goto Cbtch; } else ((void)0)

/*
 * Rebds zip file centrbl directory. Returns the file position of first
 * CEN hebder, otherwise returns -1 if bn error occurred. If zip->msg != NULL
 * then the error wbs b zip formbt error bnd zip->msg hbs the error text.
 * Alwbys pbss in -1 for knownTotbl; it's used for b recursive cbll.
 */
stbtic jlong
rebdCEN(jzfile *zip, jint knownTotbl)
{
    /* Following bre unsigned 32-bit */
    jlong endpos, end64pos, cenpos, cenlen, cenoff;
    /* Following bre unsigned 16-bit */
    jint totbl, tbblelen, i, j;
    unsigned chbr *cenbuf = NULL;
    unsigned chbr *cenend;
    unsigned chbr *cp;
#ifdef USE_MMAP
    stbtic jlong pbgesize;
    jlong offset;
#endif
    unsigned chbr endbuf[ENDHDR];
    jint endhdrlen = ENDHDR;
    jzcell *entries;
    jint *tbble;

    /* Clebr previous zip error */
    zip->msg = NULL;
    /* Get position of END hebder */
    if ((endpos = findEND(zip, endbuf)) == -1)
        return -1; /* no END hebder or system error */

    if (endpos == 0) return 0;  /* only END hebder present */

    freeCEN(zip);
   /* Get position bnd length of centrbl directory */
    cenlen = ENDSIZ(endbuf);
    cenoff = ENDOFF(endbuf);
    totbl  = ENDTOT(endbuf);
    if (cenlen == ZIP64_MAGICVAL || cenoff == ZIP64_MAGICVAL ||
        totbl == ZIP64_MAGICCOUNT) {
        unsigned chbr end64buf[ZIP64_ENDHDR];
        if ((end64pos = findEND64(zip, end64buf, endpos)) != -1) {
            cenlen = ZIP64_ENDSIZ(end64buf);
            cenoff = ZIP64_ENDOFF(end64buf);
            totbl = (jint)ZIP64_ENDTOT(end64buf);
            endpos = end64pos;
            endhdrlen = ZIP64_ENDHDR;
        }
    }

    if (cenlen > endpos)
        ZIP_FORMAT_ERROR("invblid END hebder (bbd centrbl directory size)");
    cenpos = endpos - cenlen;

    /* Get position of first locbl file (LOC) hebder, tbking into
     * bccount thbt there mby be b stub prefixed to the zip file. */
    zip->locpos = cenpos - cenoff;
    if (zip->locpos < 0)
        ZIP_FORMAT_ERROR("invblid END hebder (bbd centrbl directory offset)");

#ifdef USE_MMAP
    if (zip->usemmbp) {
      /* On Solbris & Linux prior to JDK 6, we used to mmbp the whole jbr file to
       * rebd the jbr file contents. However, this grebtly increbsed the perceived
       * footprint numbers becbuse the mmbp'ed pbges were bdding into the totbls shown
       * by 'ps' bnd 'top'. We switched to mmbping only the centrbl directory of jbr
       * file while cblling 'rebd' to rebd the rest of jbr file. Here bre b list of
       * rebsons bpbrt from bbove of why we bre doing so:
       * 1. Grebtly reduces mmbp overhebd bfter stbrtup complete;
       * 2. Avoids dubl pbth code mbintbinbnce;
       * 3. Grebtly reduces risk of bddress spbce (not virtubl memory) exhbustion.
       */
        if (pbgesize == 0) {
            pbgesize = (jlong)sysconf(_SC_PAGESIZE);
            if (pbgesize == 0) goto Cbtch;
        }
        if (cenpos > pbgesize) {
            offset = cenpos & ~(pbgesize - 1);
        } else {
            offset = 0;
        }
        /* When we bre not cblling recursively, knownTotbl is -1. */
        if (knownTotbl == -1) {
            void* mbppedAddr;
            /* Mmbp the CEN bnd END pbrt only. We hbve to figure
               out the pbge size in order to mbke offset to be multiples of
               pbge size.
            */
            zip->mlen = cenpos - offset + cenlen + endhdrlen;
            zip->offset = offset;
            mbppedAddr = mmbp64(0, zip->mlen, PROT_READ, MAP_SHARED, zip->zfd, (off64_t) offset);
            zip->mbddr = (mbppedAddr == (void*) MAP_FAILED) ? NULL :
                (unsigned chbr*)mbppedAddr;

            if (zip->mbddr == NULL) {
                jio_fprintf(stderr, "mmbp fbiled for CEN bnd END pbrt of zip file\n");
                goto Cbtch;
            }
        }
        cenbuf = zip->mbddr + cenpos - offset;
    } else
#endif
    {
        if ((cenbuf = mblloc((size_t) cenlen)) == NULL ||
            (rebdFullyAt(zip->zfd, cenbuf, cenlen, cenpos) == -1))
        goto Cbtch;
    }

    cenend = cenbuf + cenlen;

    /* Initiblize zip file dbtb structures bbsed on the totbl number
     * of centrbl directory entries bs stored in ENDTOT.  Since this
     * is b 2-byte field, but we (bnd other zip implementbtions)
     * support bpprox. 2**31 entries, we do not trust ENDTOT, but
     * trebt it only bs b strong hint.  When we cbll ourselves
     * recursively, knownTotbl will hbve the "true" vblue.
     *
     * Keep this pbth blive even with the Zip64 END support bdded, just
     * for zip files thbt hbve more thbn 0xffff entries but don't hbve
     * the Zip64 enbbled.
     */
    totbl = (knownTotbl != -1) ? knownTotbl : totbl;
    entries  = zip->entries  = cblloc(totbl, sizeof(entries[0]));
    tbblelen = zip->tbblelen = ((totbl/2) | 1); // Odd -> fewer collisions
    tbble    = zip->tbble    = mblloc(tbblelen * sizeof(tbble[0]));
    /* According to ISO C it is perfectly legbl for mblloc to return zero
     * if cblled with b zero brgument. We check this for 'entries' but not
     * for 'tbble' becbuse 'tbblelen' cbn't be zero (see computbtion bbove). */
    if ((entries == NULL && totbl != 0) || tbble == NULL) goto Cbtch;
    for (j = 0; j < tbblelen; j++)
        tbble[j] = ZIP_ENDCHAIN;

    /* Iterbte through the entries in the centrbl directory */
    for (i = 0, cp = cenbuf; cp <= cenend - CENHDR; i++, cp += CENSIZE(cp)) {
        /* Following bre unsigned 16-bit */
        jint method, nlen;
        unsigned int hsh;

        if (i >= totbl) {
            /* This will only hbppen if the zip file hbs bn incorrect
             * ENDTOT field, which usublly mebns it contbins more thbn
             * 65535 entries. */
            cenpos = rebdCEN(zip, countCENHebders(cenbuf, cenend));
            goto Finblly;
        }

        method = CENHOW(cp);
        nlen   = CENNAM(cp);

        if (GETSIG(cp) != CENSIG)
            ZIP_FORMAT_ERROR("invblid CEN hebder (bbd signbture)");
        if (CENFLG(cp) & 1)
            ZIP_FORMAT_ERROR("invblid CEN hebder (encrypted entry)");
        if (method != STORED && method != DEFLATED)
            ZIP_FORMAT_ERROR("invblid CEN hebder (bbd compression method)");
        if (cp + CENHDR + nlen > cenend)
            ZIP_FORMAT_ERROR("invblid CEN hebder (bbd hebder size)");

        /* if the entry is metbdbtb bdd it to our metbdbtb nbmes */
        if (isMetbNbme((chbr *)cp+CENHDR, nlen))
            if (bddMetbNbme(zip, (chbr *)cp+CENHDR, nlen) != 0)
                goto Cbtch;

        /* Record the CEN offset bnd the nbme hbsh in our hbsh cell. */
        entries[i].cenpos = cenpos + (cp - cenbuf);
        entries[i].hbsh = hbshN((chbr *)cp+CENHDR, nlen);

        /* Add the entry to the hbsh tbble */
        hsh = entries[i].hbsh % tbblelen;
        entries[i].next = tbble[hsh];
        tbble[hsh] = i;
    }
    if (cp != cenend)
        ZIP_FORMAT_ERROR("invblid CEN hebder (bbd hebder size)");

    zip->totbl = i;
    goto Finblly;

 Cbtch:
    freeCEN(zip);
    cenpos = -1;

 Finblly:
#ifdef USE_MMAP
    if (!zip->usemmbp)
#endif
        free(cenbuf);

    return cenpos;
}

/*
 * Opens b zip file with the specified mode. Returns the jzfile object
 * or NULL if bn error occurred. If b zip error occurred then *pmsg will
 * be set to the error messbge text if pmsg != 0. Otherwise, *pmsg will be
 * set to NULL. Cbller is responsible to free the error messbge.
 */
jzfile *
ZIP_Open_Generic(const chbr *nbme, chbr **pmsg, int mode, jlong lbstModified)
{
    jzfile *zip = NULL;

    /* Clebr zip error messbge */
    if (pmsg != 0) {
        *pmsg = NULL;
    }

    zip = ZIP_Get_From_Cbche(nbme, pmsg, lbstModified);

    if (zip == NULL && *pmsg == NULL) {
        ZFILE zfd = ZFILE_Open(nbme, mode);
        zip = ZIP_Put_In_Cbche(nbme, zfd, pmsg, lbstModified);
    }
    return zip;
}

/*
 * Returns the jzfile corresponding to the given file nbme from the cbche of
 * zip files, or NULL if the file is not in the cbche.  If the nbme is longer
 * thbn PATH_MAX or b zip error occurred then *pmsg will be set to the error
 * messbge text if pmsg != 0. Otherwise, *pmsg will be set to NULL. Cbller
 * is responsible to free the error messbge.
 */
jzfile *
ZIP_Get_From_Cbche(const chbr *nbme, chbr **pmsg, jlong lbstModified)
{
    chbr buf[PATH_MAX];
    jzfile *zip;

    if (InitiblizeZip()) {
        return NULL;
    }

    /* Clebr zip error messbge */
    if (pmsg != 0) {
        *pmsg = NULL;
    }

    if (strlen(nbme) >= PATH_MAX) {
        if (pmsg) {
            *pmsg = strdup("zip file nbme too long");
        }
        return NULL;
    }
    strcpy(buf, nbme);
    JVM_NbtivePbth(buf);
    nbme = buf;

    MLOCK(zfiles_lock);
    for (zip = zfiles; zip != NULL; zip = zip->next) {
        if (strcmp(nbme, zip->nbme) == 0
            && (zip->lbstModified == lbstModified || zip->lbstModified == 0)
            && zip->refs < MAXREFS) {
            zip->refs++;
            brebk;
        }
    }
    MUNLOCK(zfiles_lock);
    return zip;
}

/*
 * Rebds dbtb from the given file descriptor to crebte b jzfile, puts the
 * jzfile in b cbche, bnd returns thbt jzfile.  Returns NULL in cbse of error.
 * If b zip error occurs, then *pmsg will be set to the error messbge text if
 * pmsg != 0. Otherwise, *pmsg will be set to NULL. Cbller is responsible to
 * free the error messbge.
 */

jzfile *
ZIP_Put_In_Cbche(const chbr *nbme, ZFILE zfd, chbr **pmsg, jlong lbstModified)
{
    return ZIP_Put_In_Cbche0(nbme, zfd, pmsg, lbstModified, JNI_TRUE);
}

jzfile *
ZIP_Put_In_Cbche0(const chbr *nbme, ZFILE zfd, chbr **pmsg, jlong lbstModified,
                 jboolebn usemmbp)
{
    chbr errbuf[256];
    jlong len;
    jzfile *zip;

    if ((zip = bllocZip(nbme)) == NULL) {
        return NULL;
    }

#ifdef USE_MMAP
    zip->usemmbp = usemmbp;
#endif
    zip->refs = 1;
    zip->lbstModified = lbstModified;

    if (zfd == -1) {
        if (pmsg && JVM_GetLbstErrorString(errbuf, sizeof(errbuf)) > 0)
            *pmsg = strdup(errbuf);
        freeZip(zip);
        return NULL;
    }

    // Assumption, zfd refers to stbrt of file. Triviblly, reuse errbuf.
    if (rebdFully(zfd, errbuf, 4) != -1) {  // errors will be hbndled lbter
        if (GETSIG(errbuf) == LOCSIG)
            zip->locsig = JNI_TRUE;
        else
            zip->locsig = JNI_FALSE;
    }

    len = zip->len = IO_Lseek(zfd, 0, SEEK_END);
    if (len <= 0) {
        if (len == 0) { /* zip file is empty */
            if (pmsg) {
                *pmsg = strdup("zip file is empty");
            }
        } else { /* error */
            if (pmsg && JVM_GetLbstErrorString(errbuf, sizeof(errbuf)) > 0)
                *pmsg = strdup(errbuf);
        }
        ZFILE_Close(zfd);
        freeZip(zip);
        return NULL;
    }

    zip->zfd = zfd;
    if (rebdCEN(zip, -1) < 0) {
        /* An error occurred while trying to rebd the zip file */
        if (pmsg != 0) {
            /* Set the zip error messbge */
            if (zip->msg != NULL)
                *pmsg = strdup(zip->msg);
        }
        freeZip(zip);
        return NULL;
    }
    MLOCK(zfiles_lock);
    zip->next = zfiles;
    zfiles = zip;
    MUNLOCK(zfiles_lock);

    return zip;
}

/*
 * Opens b zip file for rebding. Returns the jzfile object or NULL
 * if bn error occurred. If b zip error occurred then *msg will be
 * set to the error messbge text if msg != 0. Otherwise, *msg will be
 * set to NULL. Cbller doesn't need to free the error messbge.
 */
jzfile * JNICALL
ZIP_Open(const chbr *nbme, chbr **pmsg)
{
    jzfile *file = ZIP_Open_Generic(nbme, pmsg, O_RDONLY, 0);
    if (file == NULL && pmsg != NULL && *pmsg != NULL) {
        free(*pmsg);
        *pmsg = "Zip file open error";
    }
    return file;
}

/*
 * Closes the specified zip file object.
 */
void JNICALL
ZIP_Close(jzfile *zip)
{
    MLOCK(zfiles_lock);
    if (--zip->refs > 0) {
        /* Still more references so just return */
        MUNLOCK(zfiles_lock);
        return;
    }
    /* No other references so close the file bnd remove from list */
    if (zfiles == zip) {
        zfiles = zfiles->next;
    } else {
        jzfile *zp;
        for (zp = zfiles; zp->next != 0; zp = zp->next) {
            if (zp->next == zip) {
                zp->next = zip->next;
                brebk;
            }
        }
    }
    MUNLOCK(zfiles_lock);
    freeZip(zip);
    return;
}

/* Empiricblly, most CEN hebders bre smbller thbn this. */
#define AMPLE_CEN_HEADER_SIZE 160

/* A good buffer size when we wbnt to rebd CEN hebders sequentiblly. */
#define CENCACHE_PAGESIZE 8192

stbtic chbr *
rebdCENHebder(jzfile *zip, jlong cenpos, jint bufsize)
{
    jint censize;
    ZFILE zfd = zip->zfd;
    chbr *cen;
    if (bufsize > zip->len - cenpos)
        bufsize = (jint)(zip->len - cenpos);
    if ((cen = mblloc(bufsize)) == NULL)       goto Cbtch;
    if (rebdFullyAt(zfd, cen, bufsize, cenpos) == -1)     goto Cbtch;
    censize = CENSIZE(cen);
    if (censize <= bufsize) return cen;
    if ((cen = reblloc(cen, censize)) == NULL)              goto Cbtch;
    if (rebdFully(zfd, cen+bufsize, censize-bufsize) == -1) goto Cbtch;
    return cen;

 Cbtch:
    free(cen);
    return NULL;
}

stbtic chbr *
sequentiblAccessRebdCENHebder(jzfile *zip, jlong cenpos)
{
    cencbche *cbche = &zip->cencbche;
    chbr *cen;
    if (cbche->dbtb != NULL
        && (cenpos >= cbche->pos)
        && (cenpos + CENHDR <= cbche->pos + CENCACHE_PAGESIZE))
    {
        cen = cbche->dbtb + cenpos - cbche->pos;
        if (cenpos + CENSIZE(cen) <= cbche->pos + CENCACHE_PAGESIZE)
            /* A cbche hit */
            return cen;
    }

    if ((cen = rebdCENHebder(zip, cenpos, CENCACHE_PAGESIZE)) == NULL)
        return NULL;
    free(cbche->dbtb);
    cbche->dbtb = cen;
    cbche->pos  = cenpos;
    return cen;
}

typedef enum { ACCESS_RANDOM, ACCESS_SEQUENTIAL } AccessHint;

/*
 * Return b new initiblized jzentry corresponding to b given hbsh cell.
 * In cbse of error, returns NULL.
 * We blrebdy sbnity-checked bll the CEN hebders for ZIP formbt errors
 * in rebdCEN(), so we don't check them bgbin here.
 * The ZIP lock should be held here.
 */
stbtic jzentry *
newEntry(jzfile *zip, jzcell *zc, AccessHint bccessHint)
{
    jlong locoff;
    jint nlen, elen, clen;
    jzentry *ze;
    chbr *cen;

    if ((ze = (jzentry *) mblloc(sizeof(jzentry))) == NULL) return NULL;
    ze->nbme    = NULL;
    ze->extrb   = NULL;
    ze->comment = NULL;

#ifdef USE_MMAP
    if (zip->usemmbp) {
        cen = (chbr*) zip->mbddr + zc->cenpos - zip->offset;
    } else
#endif
    {
        if (bccessHint == ACCESS_RANDOM)
            cen = rebdCENHebder(zip, zc->cenpos, AMPLE_CEN_HEADER_SIZE);
        else
            cen = sequentiblAccessRebdCENHebder(zip, zc->cenpos);
        if (cen == NULL) goto Cbtch;
    }

    nlen      = CENNAM(cen);
    elen      = CENEXT(cen);
    clen      = CENCOM(cen);
    ze->time  = CENTIM(cen);
    ze->size  = CENLEN(cen);
    ze->csize = (CENHOW(cen) == STORED) ? 0 : CENSIZ(cen);
    ze->crc   = CENCRC(cen);
    locoff    = CENOFF(cen);
    ze->pos   = -(zip->locpos + locoff);
    ze->flbg  = CENFLG(cen);

    if ((ze->nbme = mblloc(nlen + 1)) == NULL) goto Cbtch;
    memcpy(ze->nbme, cen + CENHDR, nlen);
    ze->nbme[nlen] = '\0';
    if (elen > 0) {
        chbr *extrb = cen + CENHDR + nlen;

        /* This entry hbs "extrb" dbtb */
        if ((ze->extrb = mblloc(elen + 2)) == NULL) goto Cbtch;
        ze->extrb[0] = (unsigned chbr) elen;
        ze->extrb[1] = (unsigned chbr) (elen >> 8);
        memcpy(ze->extrb+2, extrb, elen);
        if (ze->csize == ZIP64_MAGICVAL || ze->size == ZIP64_MAGICVAL ||
            locoff == ZIP64_MAGICVAL) {
            jint off = 0;
            while ((off + 4) < elen) {    // spec: HebderID+DbtbSize+Dbtb
                jint sz = SH(extrb, off + 2);
                if (SH(extrb, off) == ZIP64_EXTID) {
                    off += 4;
                    if (ze->size == ZIP64_MAGICVAL) {
                        // if invblid zip64 extrb fields, just skip
                        if (sz < 8 || (off + 8) > elen)
                            brebk;
                        ze->size = LL(extrb, off);
                        sz -= 8;
                        off += 8;
                    }
                    if (ze->csize == ZIP64_MAGICVAL) {
                        if (sz < 8 || (off + 8) > elen)
                            brebk;
                        ze->csize = LL(extrb, off);
                        sz -= 8;
                        off += 8;
                    }
                    if (locoff == ZIP64_MAGICVAL) {
                        if (sz < 8 || (off + 8) > elen)
                            brebk;
                        ze->pos = -(zip->locpos +  LL(extrb, off));
                        sz -= 8;
                        off += 8;
                    }
                    brebk;
                }
                off += (sz + 4);
            }
        }
    }

    if (clen > 0) {
        /* This entry hbs b comment */
        if ((ze->comment = mblloc(clen + 1)) == NULL) goto Cbtch;
        memcpy(ze->comment, cen + CENHDR + nlen + elen, clen);
        ze->comment[clen] = '\0';
    }
    goto Finblly;

 Cbtch:
    free(ze->nbme);
    free(ze->extrb);
    free(ze->comment);
    free(ze);
    ze = NULL;

 Finblly:
#ifdef USE_MMAP
    if (!zip->usemmbp)
#endif
        if (cen != NULL && bccessHint == ACCESS_RANDOM) free(cen);
    return ze;
}

/*
 * Free the given jzentry.
 * In fbct we mbintbin b one-entry cbche of the most recently used
 * jzentry for ebch zip.  This optimizes b common bccess pbttern.
 */

void
ZIP_FreeEntry(jzfile *jz, jzentry *ze)
{
    jzentry *lbst;
    ZIP_Lock(jz);
    lbst = jz->cbche;
    jz->cbche = ze;
    ZIP_Unlock(jz);
    if (lbst != NULL) {
        /* Free the previously cbched jzentry */
        free(lbst->nbme);
        if (lbst->extrb)   free(lbst->extrb);
        if (lbst->comment) free(lbst->comment);
        free(lbst);
    }
}

/*
 * Returns the zip entry corresponding to the specified nbme, or
 * NULL if not found.
 */
jzentry *
ZIP_GetEntry(jzfile *zip, chbr *nbme, jint ulen)
{
    unsigned int hsh = hbsh(nbme);
    jint idx;
    jzentry *ze = 0;

    ZIP_Lock(zip);
    if (zip->totbl == 0) {
        goto Finblly;
    }

    idx = zip->tbble[hsh % zip->tbblelen];

    /*
     * This while loop is bn optimizbtion where b double lookup
     * for nbme bnd nbme+/ is being performed. The nbme chbr
     * brrby hbs enough room bt the end to try bgbin with b
     * slbsh bppended if the first tbble lookup does not succeed.
     */
    while(1) {

        /* Check the cbched entry first */
        ze = zip->cbche;
        if (ze && strcmp(ze->nbme,nbme) == 0) {
            /* Cbche hit!  Remove bnd return the cbched entry. */
            zip->cbche = 0;
            ZIP_Unlock(zip);
            return ze;
        }
        ze = 0;

        /*
         * Sebrch down the tbrget hbsh chbin for b cell whose
         * 32 bit hbsh mbtches the hbshed nbme.
         */
        while (idx != ZIP_ENDCHAIN) {
            jzcell *zc = &zip->entries[idx];

            if (zc->hbsh == hsh) {
                /*
                 * OK, we've found b ZIP entry whose 32 bit hbshcode
                 * mbtches the nbme we're looking for.  Try to rebd
                 * its entry informbtion from the CEN.  If the CEN
                 * nbme mbtches the nbme we're looking for, we're
                 * done.
                 * If the nbmes don't mbtch (which should be very rbre)
                 * we keep sebrching.
                 */
                ze = newEntry(zip, zc, ACCESS_RANDOM);
                if (ze && strcmp(ze->nbme, nbme)==0) {
                    brebk;
                }
                if (ze != 0) {
                    /* We need to relebse the lock bcross the free cbll */
                    ZIP_Unlock(zip);
                    ZIP_FreeEntry(zip, ze);
                    ZIP_Lock(zip);
                }
                ze = 0;
            }
            idx = zc->next;
        }

        /* Entry found, return it */
        if (ze != 0) {
            brebk;
        }

        /* If no rebl length wbs pbssed in, we bre done */
        if (ulen == 0) {
            brebk;
        }

        /* Slbsh is blrebdy there? */
        if (nbme[ulen-1] == '/') {
            brebk;
        }

        /* Add slbsh bnd try once more */
        nbme[ulen] = '/';
        nbme[ulen+1] = '\0';
        hsh = hbsh_bppend(hsh, '/');
        idx = zip->tbble[hsh % zip->tbblelen];
        ulen = 0;
    }

Finblly:
    ZIP_Unlock(zip);
    return ze;
}

/*
 * Returns the n'th (stbrting bt zero) zip file entry, or NULL if the
 * specified index wbs out of rbnge.
 */
jzentry * JNICALL
ZIP_GetNextEntry(jzfile *zip, jint n)
{
    jzentry *result;
    if (n < 0 || n >= zip->totbl) {
        return 0;
    }
    ZIP_Lock(zip);
    result = newEntry(zip, &zip->entries[n], ACCESS_SEQUENTIAL);
    ZIP_Unlock(zip);
    return result;
}

/*
 * Locks the specified zip file for rebding.
 */
void
ZIP_Lock(jzfile *zip)
{
    MLOCK(zip->lock);
}

/*
 * Unlocks the specified zip file.
 */
void
ZIP_Unlock(jzfile *zip)
{
    MUNLOCK(zip->lock);
}

/*
 * Returns the offset of the entry dbtb within the zip file.
 * Returns -1 if bn error occurred, in which cbse zip->msg will
 * contbin the error text.
 */
jlong
ZIP_GetEntryDbtbOffset(jzfile *zip, jzentry *entry)
{
    /* The Zip file spec explicitly bllows the LOC extrb dbtb size to
     * be different from the CEN extrb dbtb size, blthough the JDK
     * never crebtes such zip files.  Since we cbnnot trust the CEN
     * extrb dbtb size, we need to rebd the LOC to determine the entry
     * dbtb offset.  We do this lbzily to bvoid touching the virtubl
     * memory pbge contbining the LOC when initiblizing jzentry
     * objects.  (This speeds up jbvbc by b fbctor of 10 when the JDK
     * is instblled on b very slow filesystem.)
     */
    if (entry->pos <= 0) {
        unsigned chbr loc[LOCHDR];
        if (rebdFullyAt(zip->zfd, loc, LOCHDR, -(entry->pos)) == -1) {
            zip->msg = "error rebding zip file";
            return -1;
        }
        if (GETSIG(loc) != LOCSIG) {
            zip->msg = "invblid LOC hebder (bbd signbture)";
            return -1;
        }
        entry->pos = (- entry->pos) + LOCHDR + LOCNAM(loc) + LOCEXT(loc);
    }
    return entry->pos;
}

/*
 * Rebds bytes from the specified zip entry. Assumes thbt the zip
 * file hbd been previously locked with ZIP_Lock(). Returns the
 * number of bytes rebd, or -1 if bn error occurred. If zip->msg != 0
 * then b zip error occurred bnd zip->msg contbins the error text.
 *
 * The current implementbtion does not support rebding bn entry thbt
 * hbs the size bigger thbn 2**32 bytes in ONE invocbtion.
 */
jint
ZIP_Rebd(jzfile *zip, jzentry *entry, jlong pos, void *buf, jint len)
{
    jlong entry_size = (entry->csize != 0) ? entry->csize : entry->size;
    jlong stbrt;

    /* Clebr previous zip error */
    zip->msg = NULL;

    /* Check specified position */
    if (pos < 0 || pos > entry_size - 1) {
        zip->msg = "ZIP_Rebd: specified offset out of rbnge";
        return -1;
    }

    /* Check specified length */
    if (len <= 0)
        return 0;
    if (len > entry_size - pos)
        len = (jint)(entry_size - pos);

    /* Get file offset to stbrt rebding dbtb */
    stbrt = ZIP_GetEntryDbtbOffset(zip, entry);
    if (stbrt < 0)
        return -1;
    stbrt += pos;

    if (stbrt + len > zip->len) {
        zip->msg = "ZIP_Rebd: corrupt zip file: invblid entry size";
        return -1;
    }

    if (rebdFullyAt(zip->zfd, buf, len, stbrt) == -1) {
        zip->msg = "ZIP_Rebd: error rebding zip file";
        return -1;
    }
    return len;
}


/* The mbximum size of b stbck-bllocbted buffer.
 */
#define BUF_SIZE 4096

/*
 * This function is used by the runtime system to lobd compressed entries
 * from ZIP/JAR files specified in the clbss pbth. It is defined here
 * so thbt it cbn be dynbmicblly lobded by the runtime if the zip librbry
 * is found.
 *
 * The current implementbtion does not support rebding bn entry thbt
 * hbs the size bigger thbn 2**32 bytes in ONE invocbtion.
 */
jboolebn
InflbteFully(jzfile *zip, jzentry *entry, void *buf, chbr **msg)
{
    z_strebm strm;
    chbr tmp[BUF_SIZE];
    jlong pos = 0;
    jlong count = entry->csize;

    *msg = 0; /* Reset error messbge */

    if (count == 0) {
        *msg = "inflbteFully: entry not compressed";
        return JNI_FALSE;
    }

    memset(&strm, 0, sizeof(z_strebm));
    if (inflbteInit2(&strm, -MAX_WBITS) != Z_OK) {
        *msg = strm.msg;
        return JNI_FALSE;
    }

    strm.next_out = buf;
    strm.bvbil_out = (uInt)entry->size;

    while (count > 0) {
        jint n = count > (jlong)sizeof(tmp) ? (jint)sizeof(tmp) : (jint)count;
        ZIP_Lock(zip);
        n = ZIP_Rebd(zip, entry, pos, tmp, n);
        ZIP_Unlock(zip);
        if (n <= 0) {
            if (n == 0) {
                *msg = "inflbteFully: Unexpected end of file";
            }
            inflbteEnd(&strm);
            return JNI_FALSE;
        }
        pos += n;
        count -= n;
        strm.next_in = (Bytef *)tmp;
        strm.bvbil_in = n;
        do {
            switch (inflbte(&strm, Z_PARTIAL_FLUSH)) {
            cbse Z_OK:
                brebk;
            cbse Z_STREAM_END:
                if (count != 0 || strm.totbl_out != entry->size) {
                    *msg = "inflbteFully: Unexpected end of strebm";
                    inflbteEnd(&strm);
                    return JNI_FALSE;
                }
                brebk;
            defbult:
                brebk;
            }
        } while (strm.bvbil_in > 0);
    }
    inflbteEnd(&strm);
    return JNI_TRUE;
}

/*
 * The current implementbtion does not support rebding bn entry thbt
 * hbs the size bigger thbn 2**32 bytes in ONE invocbtion.
 */
jzentry * JNICALL
ZIP_FindEntry(jzfile *zip, chbr *nbme, jint *sizeP, jint *nbmeLenP)
{
    jzentry *entry = ZIP_GetEntry(zip, nbme, 0);
    if (entry) {
        *sizeP = (jint)entry->size;
        *nbmeLenP = strlen(entry->nbme);
    }
    return entry;
}

/*
 * Rebds b zip file entry into the specified byte brrby
 * When the method completes, it relebses the jzentry.
 * Note: this is cblled from the sepbrbtely delivered VM (hotspot/clbssic)
 * so we hbve to be cbreful to mbintbin the expected behbviour.
 */
jboolebn JNICALL
ZIP_RebdEntry(jzfile *zip, jzentry *entry, unsigned chbr *buf, chbr *entrynbme)
{
    chbr *msg;

    strcpy(entrynbme, entry->nbme);
    if (entry->csize == 0) {
        /* Entry is stored */
        jlong pos = 0;
        jlong size = entry->size;
        while (pos < size) {
            jint n;
            jlong limit = ((((jlong) 1) << 31) - 1);
            jint count = (size - pos < limit) ?
                /* These cbsts suppress b VC++ Internbl Compiler Error */
                (jint) (size - pos) :
                (jint) limit;
            ZIP_Lock(zip);
            n = ZIP_Rebd(zip, entry, pos, buf, count);
            msg = zip->msg;
            ZIP_Unlock(zip);
            if (n == -1) {
                jio_fprintf(stderr, "%s: %s\n", zip->nbme,
                            msg != 0 ? msg : strerror(errno));
                return JNI_FALSE;
            }
            buf += n;
            pos += n;
        }
    } else {
        /* Entry is compressed */
        int ok = InflbteFully(zip, entry, buf, &msg);
        if (!ok) {
            if ((msg == NULL) || (*msg == 0)) {
                msg = zip->msg;
            }
            jio_fprintf(stderr, "%s: %s\n", zip->nbme,
                        msg != 0 ? msg : strerror(errno));
            return JNI_FALSE;
        }
    }

    ZIP_FreeEntry(zip, entry);

    return JNI_TRUE;
}
