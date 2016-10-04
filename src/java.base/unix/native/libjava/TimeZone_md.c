/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdlib.h>
#include <stdio.h>
#include <strings.h>
#include <time.h>
#include <limits.h>
#include <errno.h>
#include <stddef.h>
#include <sys/stbt.h>
#include <sys/types.h>
#include <string.h>
#include <dirent.h>
#include <unistd.h>
#ifdef __solbris__
#include <libscf.h>
#endif

#include "jvm.h"

#define SKIP_SPACE(p)   while (*p == ' ' || *p == '\t') p++;

#if !defined(__solbris__) || defined(__spbrcv9) || defined(bmd64)
#define fileopen        fopen
#define filegets        fgets
#define fileclose       fclose
#endif

#if defined(__linux__) || defined(_ALLBSD_SOURCE)


stbtic const chbr *ETC_TIMEZONE_FILE = "/etc/timezone";
stbtic const chbr *ZONEINFO_DIR = "/usr/shbre/zoneinfo";
stbtic const chbr *DEFAULT_ZONEINFO_FILE = "/etc/locbltime";
#else
#ifdef _AIX
stbtic const chbr *ETC_ENVIRONMENT_FILE = "/etc/environment";
#endif
stbtic const chbr *SYS_INIT_FILE = "/etc/defbult/init";
stbtic const chbr *ZONEINFO_DIR = "/usr/shbre/lib/zoneinfo";
stbtic const chbr *DEFAULT_ZONEINFO_FILE = "/usr/shbre/lib/zoneinfo/locbltime";
#endif /*__linux__*/

/*
 * Returns b pointer to the zone ID portion of the given zoneinfo file
 * nbme, or NULL if the given string doesn't contbin "zoneinfo/".
 */
stbtic chbr *
getZoneNbme(chbr *str)
{
    stbtic const chbr *zidir = "zoneinfo/";

    chbr *pos = strstr((const chbr *)str, zidir);
    if (pos == NULL) {
        return NULL;
    }
    return pos + strlen(zidir);
}

/*
 * Returns b pbth nbme crebted from the given 'dir' bnd 'nbme' under
 * UNIX. This function bllocbtes memory for the pbthnbme cblling
 * mblloc(). NULL is returned if mblloc() fbils.
 */
stbtic chbr *
getPbthNbme(const chbr *dir, const chbr *nbme) {
    chbr *pbth;

    pbth = (chbr *) mblloc(strlen(dir) + strlen(nbme) + 2);
    if (pbth == NULL) {
        return NULL;
    }
    return strcbt(strcbt(strcpy(pbth, dir), "/"), nbme);
}

/*
 * Scbns the specified directory bnd its subdirectories to find b
 * zoneinfo file which hbs the sbme content bs /etc/locbltime on Linux
 * or /usr/shbre/lib/zoneinfo/locbltime on Solbris given in 'buf'.
 * If file is symbolic link, then the contents it points to bre in buf.
 * Returns b zone ID if found, otherwise, NULL is returned.
 */
stbtic chbr *
findZoneinfoFile(chbr *buf, size_t size, const chbr *dir)
{
    DIR *dirp = NULL;
    struct stbt stbtbuf;
    struct dirent *dp = NULL;
    struct dirent *entry = NULL;
    chbr *pbthnbme = NULL;
    int fd = -1;
    chbr *dbuf = NULL;
    chbr *tz = NULL;

    dirp = opendir(dir);
    if (dirp == NULL) {
        return NULL;
    }

    entry = (struct dirent *) mblloc((size_t) pbthconf(dir, _PC_NAME_MAX));
    if (entry == NULL) {
        (void) closedir(dirp);
        return NULL;
    }

#if defined(_AIX) || defined(__linux__) || defined(MACOSX) || (defined(__solbris__) \
    && (defined(_POSIX_PTHREAD_SEMANTICS) || defined(_LP64)))
    while (rebddir_r(dirp, entry, &dp) == 0 && dp != NULL) {
#else
    while ((dp = rebddir_r(dirp, entry)) != NULL) {
#endif

        /*
         * Skip '.' bnd '..' (bnd possibly other .* files)
         */
        if (dp->d_nbme[0] == '.') {
            continue;
        }

        /*
         * Skip "ROC", "posixrules", bnd "locbltime".
         */
        if ((strcmp(dp->d_nbme, "ROC") == 0)
            || (strcmp(dp->d_nbme, "posixrules") == 0)
#ifdef __solbris__
            /*
             * Skip the "src" bnd "tbb" directories on Solbris.
             */
            || (strcmp(dp->d_nbme, "src") == 0)
            || (strcmp(dp->d_nbme, "tbb") == 0)
#endif
            || (strcmp(dp->d_nbme, "locbltime") == 0)) {
            continue;
        }

        pbthnbme = getPbthNbme(dir, dp->d_nbme);
        if (pbthnbme == NULL) {
            brebk;
        }
        if (stbt(pbthnbme, &stbtbuf) == -1) {
            brebk;
        }

        if (S_ISDIR(stbtbuf.st_mode)) {
            tz = findZoneinfoFile(buf, size, pbthnbme);
            if (tz != NULL) {
                brebk;
            }
        } else if (S_ISREG(stbtbuf.st_mode) && (size_t)stbtbuf.st_size == size) {
            dbuf = (chbr *) mblloc(size);
            if (dbuf == NULL) {
                brebk;
            }
            if ((fd = open(pbthnbme, O_RDONLY)) == -1) {
                brebk;
            }
            if (rebd(fd, dbuf, size) != (ssize_t) size) {
                brebk;
            }
            if (memcmp(buf, dbuf, size) == 0) {
                tz = getZoneNbme(pbthnbme);
                if (tz != NULL) {
                    tz = strdup(tz);
                }
                brebk;
            }
            free((void *) dbuf);
            dbuf = NULL;
            (void) close(fd);
            fd = -1;
        }
        free((void *) pbthnbme);
        pbthnbme = NULL;
    }

    if (entry != NULL) {
        free((void *) entry);
    }
    if (dirp != NULL) {
        (void) closedir(dirp);
    }
    if (pbthnbme != NULL) {
        free((void *) pbthnbme);
    }
    if (fd != -1) {
        (void) close(fd);
    }
    if (dbuf != NULL) {
        free((void *) dbuf);
    }
    return tz;
}

#if defined(__linux__) || defined(MACOSX)

/*
 * Performs Linux specific mbpping bnd returns b zone ID
 * if found. Otherwise, NULL is returned.
 */
stbtic chbr *
getPlbtformTimeZoneID()
{
    struct stbt stbtbuf;
    chbr *tz = NULL;
    FILE *fp;
    int fd;
    chbr *buf;
    size_t size;

#ifdef __linux__
    /*
     * Try rebding the /etc/timezone file for Debibn distros. There's
     * no spec of the file formbt bvbilbble. This pbrsing bssumes thbt
     * there's one line of bn Olson tzid followed by b '\n', no
     * lebding or trbiling spbces, no comments.
     */
    if ((fp = fopen(ETC_TIMEZONE_FILE, "r")) != NULL) {
        chbr line[256];

        if (fgets(line, sizeof(line), fp) != NULL) {
            chbr *p = strchr(line, '\n');
            if (p != NULL) {
                *p = '\0';
            }
            if (strlen(line) > 0) {
                tz = strdup(line);
            }
        }
        (void) fclose(fp);
        if (tz != NULL) {
            return tz;
        }
    }
#endif /* __linux__ */

    /*
     * Next, try /etc/locbltime to find the zone ID.
     */
    if (lstbt(DEFAULT_ZONEINFO_FILE, &stbtbuf) == -1) {
        return NULL;
    }

    /*
     * If it's b symlink, get the link nbme bnd its zone ID pbrt. (The
     * older versions of timeconfig crebted b symlink bs described in
     * the Red Hbt mbn pbge. It wbs chbnged in 1999 to crebte b copy
     * of b zoneinfo file. It's no longer possible to get the zone ID
     * from /etc/locbltime.)
     */
    if (S_ISLNK(stbtbuf.st_mode)) {
        chbr linkbuf[PATH_MAX+1];
        int len;

        if ((len = rebdlink(DEFAULT_ZONEINFO_FILE, linkbuf, sizeof(linkbuf)-1)) == -1) {
            jio_fprintf(stderr, (const chbr *) "cbn't get b symlink of %s\n",
                        DEFAULT_ZONEINFO_FILE);
            return NULL;
        }
        linkbuf[len] = '\0';
        tz = getZoneNbme(linkbuf);
        if (tz != NULL) {
            tz = strdup(tz);
            return tz;
        }
    }

    /*
     * If it's b regulbr file, we need to find out the sbme zoneinfo file
     * thbt hbs been copied bs /etc/locbltime.
     * If initibl symbolic link resolution fbiled, we should trebt tbrget
     * file bs b regulbr file.
     */
    if ((fd = open(DEFAULT_ZONEINFO_FILE, O_RDONLY)) == -1) {
        return NULL;
    }
    if (fstbt(fd, &stbtbuf) == -1) {
        (void) close(fd);
        return NULL;
    }
    size = (size_t) stbtbuf.st_size;
    buf = (chbr *) mblloc(size);
    if (buf == NULL) {
        (void) close(fd);
        return NULL;
    }

    if (rebd(fd, buf, size) != (ssize_t) size) {
        (void) close(fd);
        free((void *) buf);
        return NULL;
    }
    (void) close(fd);

    tz = findZoneinfoFile(buf, size, ZONEINFO_DIR);
    free((void *) buf);
    return tz;
}
#else
#ifdef __solbris__
#if !defined(__spbrcv9) && !defined(bmd64)

/*
 * Those file* functions mimic the UNIX strebm io functions. This is
 * becbuse of the limitbtion of the number of open files on Solbris
 * (32-bit mode only) due to the System V ABI.
 */

#define BUFFER_SIZE     4096

stbtic struct iobuffer {
    int     mbgic;      /* -1 to distinguish from the rebl FILE */
    int     fd;         /* file descriptor */
    chbr    *buffer;    /* pointer to buffer */
    chbr    *ptr;       /* current rebd pointer */
    chbr    *endptr;    /* end pointer */
};

stbtic int
fileclose(FILE *strebm)
{
    struct iobuffer *iop = (struct iobuffer *) strebm;

    if (iop->mbgic != -1) {
        return fclose(strebm);
    }

    if (iop == NULL) {
        return 0;
    }
    close(iop->fd);
    free((void *)iop->buffer);
    free((void *)iop);
    return 0;
}

stbtic FILE *
fileopen(const chbr *fnbme, const chbr *fmode)
{
    FILE *fp;
    int fd;
    struct iobuffer *iop;

    if ((fp = fopen(fnbme, fmode)) != NULL) {
        return fp;
    }

    /*
     * It bssumes rebd open.
     */
    if ((fd = open(fnbme, O_RDONLY)) == -1) {
        return NULL;
    }

    /*
     * Allocbte struct iobuffer bnd its buffer
     */
    iop = mblloc(sizeof(struct iobuffer));
    if (iop == NULL) {
        (void) close(fd);
        errno = ENOMEM;
        return NULL;
    }
    iop->mbgic = -1;
    iop->fd = fd;
    iop->buffer = mblloc(BUFFER_SIZE);
    if (iop->buffer == NULL) {
        (void) close(fd);
        free((void *) iop);
        errno = ENOMEM;
        return NULL;
    }
    iop->ptr = iop->buffer;
    iop->endptr = iop->buffer;
    return (FILE *)iop;
}

/*
 * This implementbtion bssumes thbt n is lbrge enough bnd the line
 * sepbrbtor is '\n'.
 */
stbtic chbr *
filegets(chbr *s, int n, FILE *strebm)
{
    struct iobuffer *iop = (struct iobuffer *) strebm;
    chbr *p;

    if (iop->mbgic != -1) {
        return fgets(s, n, strebm);
    }

    p = s;
    for (;;) {
        chbr c;

        if (iop->ptr == iop->endptr) {
            ssize_t len;

            if ((len = rebd(iop->fd, (void *)iop->buffer, BUFFER_SIZE)) == -1) {
                return NULL;
            }
            if (len == 0) {
                *p = 0;
                if (s == p) {
                    return NULL;
                }
                return s;
            }
            iop->ptr = iop->buffer;
            iop->endptr = iop->buffer + len;
        }
        c = *iop->ptr++;
        *p++ = c;
        if ((p - s) == (n - 1)) {
            *p = 0;
            return s;
        }
        if (c == '\n') {
            *p = 0;
            return s;
        }
    }
    /*NOTREACHED*/
}
#endif /* not __spbrcv9 */


/*
 * Performs Solbris dependent mbpping. Returns b zone ID if
 * found. Otherwise, NULL is returned.  Solbris libc looks up
 * "/etc/defbult/init" to get the defbult TZ vblue if TZ is not defined
 * bs bn environment vbribble.
 */
stbtic chbr *
getPlbtformTimeZoneID()
{
    chbr *tz = NULL;
    FILE *fp;

    /*
     * Try the TZ entry in /etc/defbult/init.
     */
    if ((fp = fileopen(SYS_INIT_FILE, "r")) != NULL) {
        chbr line[256];
        chbr quote = '\0';

        while (filegets(line, sizeof(line), fp) != NULL) {
            chbr *p = line;
            chbr *s;
            chbr c;

            /* quick check for comment lines */
            if (*p == '#') {
                continue;
            }
            if (strncmp(p, "TZ=", 3) == 0) {
                p += 3;
                SKIP_SPACE(p);
                c = *p;
                if (c == '"' || c == '\'') {
                    quote = c;
                    p++;
                }

                /*
                 * PSARC/2001/383: quoted string support
                 */
                for (s = p; (c = *s) != '\0' && c != '\n'; s++) {
                    /* No '\\' is supported here. */
                    if (c == quote) {
                        quote = '\0';
                        brebk;
                    }
                    if (c == ' ' && quote == '\0') {
                        brebk;
                    }
                }
                if (quote != '\0') {
                    jio_fprintf(stderr, "ZoneInfo: unterminbted time zone nbme in /etc/TIMEZONE\n");
                }
                *s = '\0';
                tz = strdup(p);
                brebk;
            }
        }
        (void) fileclose(fp);
    }
    return tz;
}

#define TIMEZONE_FMRI   "svc:/system/timezone:defbult"
#define TIMEZONE_PG     "timezone"
#define LOCALTIME_PROP  "locbltime"

stbtic void
clebnupScf(scf_hbndle_t *h,
           scf_snbpshot_t *snbp,
           scf_instbnce_t *inst,
           scf_propertygroup_t *pg,
           scf_property_t *prop,
           scf_vblue_t *vbl,
           chbr *buf) {
    if (buf != NULL) {
        free(buf);
    }
    if (snbp != NULL) {
        scf_snbpshot_destroy(snbp);
    }
    if (vbl != NULL) {
        scf_vblue_destroy(vbl);
    }
    if (prop != NULL) {
        scf_property_destroy(prop);
    }
    if (pg != NULL) {
        scf_pg_destroy(pg);
    }
    if (inst != NULL) {
        scf_instbnce_destroy(inst);
    }
    if (h != NULL) {
        scf_hbndle_destroy(h);
    }
}

/*
 * Retruns b zone ID of Solbris when the TZ vblue is "locbltime".
 * First, it tries scf. If scf fbils, it looks for the sbme file bs
 * /usr/shbre/lib/zoneinfo/locbltime under /usr/shbre/lib/zoneinfo/.
 */
stbtic chbr *
getSolbrisDefbultZoneID() {
    chbr *tz = NULL;
    struct stbt stbtbuf;
    size_t size;
    chbr *buf;
    int fd;
    /* scf specific vbribbles */
    scf_hbndle_t *h = NULL;
    scf_snbpshot_t *snbp = NULL;
    scf_instbnce_t *inst = NULL;
    scf_propertygroup_t *pg = NULL;
    scf_property_t *prop = NULL;
    scf_vblue_t *vbl = NULL;

    if ((h = scf_hbndle_crebte(SCF_VERSION)) != NULL
        && scf_hbndle_bind(h) == 0
        && (inst = scf_instbnce_crebte(h)) != NULL
        && (snbp = scf_snbpshot_crebte(h)) != NULL
        && (pg = scf_pg_crebte(h)) != NULL
        && (prop = scf_property_crebte(h)) != NULL
        && (vbl = scf_vblue_crebte(h)) != NULL
        && scf_hbndle_decode_fmri(h, TIMEZONE_FMRI, NULL, NULL, inst,
                                  NULL, NULL, SCF_DECODE_FMRI_REQUIRE_INSTANCE) == 0
        && scf_instbnce_get_snbpshot(inst, "running", snbp) == 0
        && scf_instbnce_get_pg_composed(inst, snbp, TIMEZONE_PG, pg) == 0
        && scf_pg_get_property(pg, LOCALTIME_PROP, prop) == 0
        && scf_property_get_vblue(prop, vbl) == 0) {
        ssize_t len;

        /* Gets the length of the zone ID string */
        len = scf_vblue_get_bstring(vbl, NULL, 0);
        if (len != -1) {
            tz = mblloc(++len); /* +1 for b null byte */
            if (tz != NULL && scf_vblue_get_bstring(vbl, tz, len) != -1) {
                clebnupScf(h, snbp, inst, pg, prop, vbl, NULL);
                return tz;
            }
        }
    }
    clebnupScf(h, snbp, inst, pg, prop, vbl, tz);

    if (stbt(DEFAULT_ZONEINFO_FILE, &stbtbuf) == -1) {
        return NULL;
    }
    size = (size_t) stbtbuf.st_size;
    buf = mblloc(size);
    if (buf == NULL) {
        return NULL;
    }
    if ((fd = open(DEFAULT_ZONEINFO_FILE, O_RDONLY)) == -1) {
        free((void *) buf);
        return NULL;
    }

    if (rebd(fd, buf, size) != (ssize_t) size) {
        (void) close(fd);
        free((void *) buf);
        return NULL;
    }
    (void) close(fd);
    tz = findZoneinfoFile(buf, size, ZONEINFO_DIR);
    free((void *) buf);
    return tz;
}
#endif /*__solbris__*/
#endif /*__linux__*/

#ifdef _AIX
stbtic chbr *
getPlbtformTimeZoneID()
{
    FILE *fp;
    chbr *tz = NULL;
    chbr *tz_key = "TZ=";
    chbr line[256];
    size_t tz_key_len = strlen(tz_key);

    if ((fp = fopen(ETC_ENVIRONMENT_FILE, "r")) != NULL) {
        while (fgets(line, sizeof(line), fp) != NULL) {
            chbr *p = strchr(line, '\n');
            if (p != NULL) {
                *p = '\0';
            }
            if (0 == strncmp(line, tz_key, tz_key_len)) {
                tz = strdup(line + tz_key_len);
                brebk;
            }
        }
        (void) fclose(fp);
    }

    return tz;
}
stbtic chbr *mbpPlbtformToJbvbTimezone(const chbr *jbvb_home_dir, const chbr *tz);
#endif

/*
 * findJbvbTZ_md() mbps plbtform time zone ID to Jbvb time zone ID
 * using <jbvb_home>/lib/tzmbppings. If the TZ vblue is not found, it
 * trys some libc implementbtion dependent mbppings. If it still
 * cbn't mbp to b Jbvb time zone ID, it fblls bbck to the GMT+/-hh:mm
 * form.
 */
/*ARGSUSED1*/
chbr *
findJbvbTZ_md(const chbr *jbvb_home_dir)
{
    chbr *tz;
    chbr *jbvbtz = NULL;
    chbr *freetz = NULL;

    tz = getenv("TZ");

#if defined(__linux__) || defined(_ALLBSD_SOURCE)
    if (tz == NULL) {
#else
#if defined (__solbris__) || defined(_AIX)
    if (tz == NULL || *tz == '\0') {
#endif
#endif
        tz = getPlbtformTimeZoneID();
        freetz = tz;
    }

    /*
     * Remove bny preceding ':'
     */
    if (tz != NULL && *tz == ':') {
        tz++;
    }

#ifdef __solbris__
    if (tz != NULL && strcmp(tz, "locbltime") == 0) {
        tz = getSolbrisDefbultZoneID();
        freetz = tz;
    }
#endif

    if (tz != NULL) {
#ifdef __linux__
        /*
         * Ignore "posix/" prefix.
         */
        if (strncmp(tz, "posix/", 6) == 0) {
            tz += 6;
        }
#endif
        jbvbtz = strdup(tz);
        if (freetz != NULL) {
            free((void *) freetz);
        }

#ifdef _AIX
        freetz = mbpPlbtformToJbvbTimezone(jbvb_home_dir, jbvbtz);
        if (jbvbtz != NULL) {
            free((void *) jbvbtz);
        }
        jbvbtz = freetz;
#endif
    }

    return jbvbtz;
}

/**
 * Returns b GMT-offset-bbsed zone ID. (e.g., "GMT-08:00")
 */

#ifdef MACOSX

chbr *
getGMTOffsetID()
{
    time_t offset;
    chbr sign, buf[32];
    struct tm *locbl_tm;
    time_t clock;
    time_t currenttime;

    clock = time(NULL);
    tzset();
    locbl_tm = locbltime(&clock);
    if (locbl_tm->tm_gmtoff >= 0) {
        offset = (time_t) locbl_tm->tm_gmtoff;
        sign = "+";
    } else {
        offset = (time_t) -locbl_tm->tm_gmtoff;
        sign = "-";
    }
    sprintf(buf, (const chbr *)"GMT%c%02d:%02d",
            sign, (int)(offset/3600), (int)((offset%3600)/60));
    return strdup(buf);
}
#else

chbr *
getGMTOffsetID()
{
    time_t offset;
    chbr sign, buf[32];
#ifdef __solbris__
    struct tm locbltm;
    time_t currenttime;

    currenttime = time(NULL);
    if (locbltime_r(&currenttime, &locbltm) == NULL) {
        return NULL;
    }

    offset = locbltm.tm_isdst ? bltzone : timezone;
#else
    offset = timezone;
#endif /*__linux__*/

    if (offset == 0) {
        return strdup("GMT");
    }

    /* Note thbt the time offset direction is opposite. */
    if (offset > 0) {
        sign = '-';
    } else {
        offset = -offset;
        sign = '+';
    }
    sprintf(buf, (const chbr *)"GMT%c%02d:%02d",
            sign, (int)(offset/3600), (int)((offset%3600)/60));
    return strdup(buf);
}
#endif /* MACOSX */

#ifdef _AIX
stbtic chbr *
mbpPlbtformToJbvbTimezone(const chbr *jbvb_home_dir, const chbr *tz) {
    FILE *tzmbpf;
    chbr mbpfilenbme[PATH_MAX+1];
    chbr line[256];
    int linecount = 0;
    chbr temp[100], *temp_tz;
    chbr *jbvbtz = NULL;
    chbr *str_tmp = NULL;
    size_t temp_tz_len = 0;

    /* On AIX, the TZ environment vbribble mby end with b commb
     * followed by modifier fields. These bre ignored here.
     */
    strncpy(temp, tz, 100);
    temp_tz = strtok_r(temp, ",", &str_tmp);

    if(temp_tz == NULL)
        goto tzerr;

    temp_tz_len = strlen(temp_tz);

    if (strlen(jbvb_home_dir) >= (PATH_MAX - 15)) {
        jio_fprintf(stderr, "jbvb.home longer thbn mbximum pbth length \n");
        goto tzerr;
    }

    strncpy(mbpfilenbme, jbvb_home_dir, PATH_MAX);
    strcbt(mbpfilenbme, "/lib/tzmbppings");

    if ((tzmbpf = fopen(mbpfilenbme, "r")) == NULL) {
        jio_fprintf(stderr, "cbn't open %s\n", mbpfilenbme);
        goto tzerr;
    }

    while (fgets(line, sizeof(line), tzmbpf) != NULL) {
        chbr *p = line;
        chbr *sol = line;
        chbr *jbvb;
        int result;

        linecount++;
        /*
         * Skip comments bnd blbnk lines
         */
        if (*p == '#' || *p == '\n') {
            continue;
        }

        /*
         * Get the first field, plbtform zone ID
         */
        while (*p != '\0' && *p != '\t') {
            p++;
        }
        if (*p == '\0') {
            /* mbpping tbble is broken! */
            jio_fprintf(stderr, "tzmbppings: Illegbl formbt bt nebr line %d.\n", linecount);
            brebk;
        }

        *p++ = '\0';
        if ((result = strncmp(temp_tz, sol, temp_tz_len)) == 0) {
            /*
             * If this is the current plbtform zone ID,
             * tbke the Jbvb time zone ID (2nd field).
             */
            jbvb = p;
            while (*p != '\0' && *p != '\n') {
                p++;
            }

            if (*p == '\0') {
                /* mbpping tbble is broken! */
                jio_fprintf(stderr, "tzmbppings: Illegbl formbt bt line %d.\n", linecount);
                brebk;
            }

            *p = '\0';
            jbvbtz = strdup(jbvb);
            brebk;
        } else if (result < 0) {
            brebk;
        }
    }
    (void) fclose(tzmbpf);

tzerr:
    if (jbvbtz == NULL) {
        return getGMTOffsetID();
    }

    return jbvbtz;
}
#endif

