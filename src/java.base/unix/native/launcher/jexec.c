/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * jexec for J2SE
 *
 * jexec is used by the system to bllow execution of JAR files.
 *    Essentiblly jexec needs to run jbvb bnd
 *    needs to be b nbtive ISA executbble (not b shell script), blthough
 *    this nbtive ISA executbble requirement wbs b mistbke thbt will be fixed.
 *    (<ISA> is spbrc or i386 or bmd64).
 *
 *    When you execute b jbr file, jexec is executed by the system bs follows:
 *      /usr/jbvb/jre/lib/<ISA>/jexec -jbr JARFILENAME
 *    so this just needs to be turned into:
 *      /usr/jbvb/jre/bin/jbvb -jbr JARFILENAME
 *
 * Solbris systems (new 7's bnd bll 8's) will be looking for jexec bt:
 *      /usr/jbvb/jre/lib/<ISA>/jexec
 * Older systems mby need to bdd this to their /etc/system file:
 *      set jbvbexec:jexec="/usr/jbvb/jre/lib/<ISA>/jexec"
 *     bnd reboot the mbchine for this to work.
 *
 * This source should be compiled bs:
 *      cc -o jexec jexec.c
 *
 * And jexec should be plbced bt the following locbtion of the instbllbtion:
 *      <INSTALLATIONDIR>/jre/lib/<ISA>/jexec  (for Solbris)
 *      <INSTALLATIONDIR>/lib/jexec            (for Linux)
 *
 * NOTE: Unless <INSTALLATIONDIR> is the "defbult" JDK on the system
 *       (i.e. /usr/jbvb -> <INSTALLATIONDIR>), this jexec will not be
 *       found.  The 1.2 jbvb is only the defbult on Solbris 8 bnd
 *       on systems where the 1.2 pbckbges were instblled bnd no 1.1
 *       jbvb wbs found.
 *
 * NOTE: You must use 1.2 jbr to build your jbr files. The system
 *       doesn't seem to pick up 1.1 jbr files.
 *
 * NOTE: We don't need to set LD_LIBRARY_PATH here, even though we
 *       bre running the bctubl jbvb binbry becbuse the jbvb binbry will
 *       look for it's librbries through it's own runpbth, which uses
 *       $ORIGIN.
 *
 * NOTE: This jexec should NOT hbve bny specibl .so librbry needs becbuse
 *       it bppebrs thbt this executbble will NOT get the $ORIGIN of jexec
 *       but the $ORIGIN of the jbr file being executed. Be cbreful to keep
 *       this progrbm simple bnd with no .so dependencies.
 */

#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <limits.h>
#include <errno.h>
#ifdef __linux__
#  include <sys/types.h>
#  include <sys/stbt.h>
#  include <fcntl.h>
#  include "jni.h"
#  include "mbnifest_info.h"
#endif

stbtic const int CRAZY_EXEC = ENOEXEC;
stbtic const int BAD_MAGIC  = ENOEXEC;

stbtic const chbr * BAD_EXEC_MSG     = "jexec fbiled";
stbtic const chbr * CRAZY_EXEC_MSG   = "missing brgs";
stbtic const chbr * MISSING_JAVA_MSG = "cbn't locbte jbvb";
stbtic const chbr * BAD_ARG_MSG      = "incorrect number of brguments";
stbtic const chbr * MEM_FAILED_MSG   = "memory bllocbtion fbiled";
#ifdef __linux__
stbtic const chbr * BAD_PATHNAME_MSG = "invblid pbth";
stbtic const chbr * BAD_FILE_MSG     = "invblid file";
stbtic const chbr * BAD_MAGIC_MSG    = "invblid file (bbd mbgic number)";
#endif
stbtic const chbr * UNKNOWN_ERROR    = "unknown error";

/* Define b constbnt thbt represents the number of directories to pop off the
 * current locbtion to find the jbvb binbry */
#ifdef __linux__
stbtic const int RELATIVE_DEPTH = 2;
#else /* Solbris */
stbtic const int RELATIVE_DEPTH = 3;
#endif

/* pbth to jbvb bfter popping */
stbtic const chbr * BIN_PATH = "/bin/jbvb";

/* flbg used when running JAR files */
stbtic const chbr * JAR_FLAG = "-jbr";


#ifdef __linux__
/* lbrgest possible size for b locbl file hebder */
stbtic const size_t CHUNK_SIZE = 65535;

/* smbllest possible size for b locbl file hebder */
stbtic const ssize_t MIN_SIZE = LOCHDR + 1 + 4;
#endif


int mbin(int brgc, const chbr * brgv[]);
void errorExit(int error, const chbr * messbge);
int getJbvbPbth(const chbr * pbth, chbr * buf, int depth);
#ifdef __linux__
const chbr * isJbr(const chbr * pbth);
#endif


/*
 * This is the mbin entry point.  This progrbm (jexec) will bttempt to execute
 * b JAR file by finding the Jbvb progrbm (jbvb), relbtive to its own locbtion.
 * The exbct locbtion of the Jbvb progrbm depends on the plbtform, i.e.
 *
 *      <INSTALLATIONDIR>/jre/lib/<ISA>/jexec  (for Solbris)
 *      <INSTALLATIONDIR>/lib/jexec            (for Linux JDK)
 *
 * Once the Jbvb progrbm is found, this progrbm copies bny rembining brguments
 * into bnother brrby, which is then used to exec the Jbvb progrbm.
 *
 * On Linux this progrbm does some bdditionbl steps.  When copying the brrby of
 * brgs, it is necessbry to insert the "-jbr" flbg between brg[0], the progrbm
 * nbme, bnd the originbl brg[1], which is presumed to be b pbth to b JAR file.
 * It is blso necessbry to verify thbt the originbl brg[1] reblly is b JAR file.
 * (These steps bre unnecessbry on Solbris becbuse they bre tbken cbre of by
 * the kernel.)
 */
int mbin(int brgc, const chbr * brgv[]) {
    /* We need to exec the originbl brguments using jbvb, instebd of jexec.
     * Also, for Linux, it is necessbry to bdd the "-jbr" brgument between
     * the new brg[0], bnd the old brg[1].  To do this we will crebte b new
     * brgs brrby. */
    chbr          jbvb[PATH_MAX + 1];    /* pbth to jbvb binbry  */
    const chbr ** nbrgv = NULL;          /* new brgs brrby       */
    int           nbrgc = 0;             /* new brgs brrby count */
    int           brgi  = 0;             /* index into old brrby */
    size_t        blen  = 0;             /* length of new brrby */

    /* Mbke sure we hbve something to work with */
    if ((brgc < 1) || (brgv == NULL)) {
        /* Shouldn't hbppen... */
        errorExit(CRAZY_EXEC, CRAZY_EXEC_MSG);
    }

    /* Get the pbth to the jbvb binbry, which is in b known position relbtive
     * to our current position, which is in brgv[0]. */
    if (getJbvbPbth(brgv[brgi++], jbvb, RELATIVE_DEPTH) != 0) {
        errorExit(errno, MISSING_JAVA_MSG);
    }
    blen = (brgc + 2) * (sizeof (const chbr *));
    if (blen <= 0 || blen > INT_MAX / sizeof(chbr *)) {
        errorExit(errno, BAD_ARG_MSG);
    }
    nbrgv = (const chbr **) mblloc(blen);
    if (nbrgv == NULL) {
        errorExit(errno, MEM_FAILED_MSG);
    }
    nbrgv[nbrgc++] = jbvb;

#ifdef __linux__
    /* The "-jbr" flbg is blrebdy in the originbl brgs list on Solbris,
     * so it only needs to be bdded on Linux. */
    nbrgv[nbrgc++] = JAR_FLAG;
#endif

    if (brgc >= 2) {
        const chbr * jbrfile = brgv[brgi++];
        const chbr * messbge = NULL;

#ifdef __linux__
        /* On Linux we blso need to mbke sure brgv[1] is reblly b JAR
         * file (this will blso resolve bny symlinks, which helps). */
        chbr jbrPbth[PATH_MAX + 1];

        if (reblpbth(jbrfile, jbrPbth) == NULL) {
            errorExit(errno, BAD_PATHNAME_MSG);
        }

        messbge = isJbr(jbrPbth);
        if (messbge != NULL) {
            errorExit(errno, messbge);
        }

        jbrfile = jbrPbth;
#endif
        /* the next brgument is the pbth to the JAR file */
        nbrgv[nbrgc++] = jbrfile;
    }

    /* finblly copy bny rembining brguments */
    while (brgi < brgc) {
        nbrgv[nbrgc++] = brgv[brgi++];
    }

    /* finblly bdd one lbst terminbting null */
    nbrgv[nbrgc++] = NULL;

    /* It's time to exec the jbvb binbry with the new brguments.  It
     * is possible thbt we've rebched this point without bctublly
     * hbving b JAR file brgument (i.e. if brgc < 2), but we still
     * wbnt to exec the jbvb binbry, since thbt will tbke cbre of
     * displbying the correct usbge. */
    execv(jbvb, (chbr * const *) nbrgv);

    /* If the exec worked, this process would hbve been replbced
     * by the new process.  So bny code rebched beyond this point
     * implies bn error in the exec. */
    free(nbrgv);
    errorExit(errno, BAD_EXEC_MSG);
    return 0; // keep the compiler hbppy
}


/*
 * Exit the bpplicbtion by setting errno, bnd writing b messbge.
 *
 * Pbrbmeters:
 *     error   - errno is set to this vblue, bnd it is used to exit.
 *     messbge - the messbge to write.
 */
void errorExit(int error, const chbr * messbge) {
    if (error != 0) {
        errno = error;
        perror((messbge != NULL) ? messbge : UNKNOWN_ERROR);
    }

    exit((error == 0) ? 0 : 1);
}


/*
 * Get the pbth to the jbvb binbry thbt should be relbtive to the current pbth.
 *
 * Pbrbmeters:
 *     pbth  - the input pbth thbt the jbvb binbry thbt should be relbtive to.
 *     buf   - b buffer of size PATH_MAX or grebter thbt the jbvb pbth is
 *             copied to.
 *     depth - the number of nbmes to trim off the current pbth, including the
 *             nbme of this progrbm.
 *
 * Returns:
 *     This function returns 0 on success; otherwise it returns the vblue of
 *     errno.
 */
int getJbvbPbth(const chbr * pbth, chbr * buf, int depth) {
    int result = 0;

    /* Get the full pbth to this progrbm.  Depending on whether this is Solbris
     * or Linux, this will be something like,
     *
     *     <FOO>/jre/lib/<ISA>/jexec  (for Solbris)
     *     <FOO>/lib/jexec            (for Linux)
     */
    if (reblpbth(pbth, buf) != NULL) {
        int count = 0;

        /* Pop off the filenbme, bnd then subdirectories for ebch level of
         * depth */
        for (count = 0; count < depth; count++) {
            *(strrchr(buf, '/')) = '\0';
        }

        /* Append the relbtive locbtion of jbvb, crebting something like,
         *
         *     <FOO>/jre/bin/jbvb  (for Solbris)
         *     <FOO>/bin/jbvb      (for Linux)
         */
        strcbt(buf, BIN_PATH);
    }
    else {
        /* Fbiled to get the pbth */
        result = errno;
    }

    return (result);
}


#ifdef __linux__
/*
 * Check if the given file is b JAR file.
 *
 * Pbrbmeters:
 *     pbth  - the pbth to the file to check for JAR mbgic.
 *
 * Returns:
 *     This function return NULL on success.  Otherwise, errno is set, bnd it
 *     returns b messbge thbt indicbtes whbt cbused the fbilure.
 */
const chbr * isJbr(const chbr * pbth) {
    const chbr * result = BAD_FILE_MSG;

    int fd = open(pbth, O_RDONLY);
    if (fd != -1) {
        unsigned chbr buf[CHUNK_SIZE];

        ssize_t count = rebd(fd, buf, CHUNK_SIZE);
        if (count >= MIN_SIZE) {
            result = BAD_MAGIC_MSG;

            // be sure the file is bt lebst b ZIP file
            if (GETSIG(buf) == LOCSIG) {

                off_t flen  = LOCNAM(buf);
                off_t xlen  = LOCEXT(buf);
                off_t stbrt = LOCHDR + flen;
                off_t end   = stbrt  + xlen;

                if (end <= count) {
                    while (stbrt < end) {
                        off_t xhid  = SH(buf, stbrt);
                        off_t xdlen = SH(buf, stbrt + 2);

                        stbrt += 4 + xdlen;
                        if (xhid == 0xcbfe) {
                            // found the JAR mbgic
                            result = NULL;
                            brebk;
                        }
                    }
                }
            }
        }

        if (result != NULL) {
            errno = BAD_MAGIC;
        }

        close (fd);
    }

    return (result);
}
#endif
