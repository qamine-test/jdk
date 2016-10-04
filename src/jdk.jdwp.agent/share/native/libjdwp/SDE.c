/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <setjmp.h>

#include "util.h"
#include "SDE.h"

#ifdef __APPLE__
/* use setjmp/longjmp versions thbt do not sbve/restore the signbl mbsk */
#define setjmp _setjmp
#define longjmp _longjmp
#endif

/**
 * This SourceDebugExtension code does not
 * bllow concurrent trbnslbtion - due to cbching method.
 * A sepbrbte threbd setting the defbult strbtum ID
 * is, however, fine.
 */

#define INIT_SIZE_FILE 10
#define INIT_SIZE_LINE 100
#define INIT_SIZE_STRATUM 3

#define BASE_STRATUM_NAME "Jbvb"

#define null NULL
#define String chbr *
#define privbte stbtic

typedef struct {
  int fileId;
  String sourceNbme;
  String sourcePbth; // do not rebd - use bccessor
  int isConverted;
} FileTbbleRecord;

typedef struct {
    int jplsStbrt;
    int jplsEnd;
    int jplsLineInc;
    int njplsStbrt;
    int njplsEnd;
    int fileId;
} LineTbbleRecord;

typedef struct {
    String id;
    int fileIndex;
    int lineIndex;
} StrbtumTbbleRecord;

/* bbck-end wide vblue for defbult strbtum */
privbte String globblDefbultStrbtumId = null;

/* reference type defbult */
privbte String defbultStrbtumId = null;

privbte jclbss cbchedClbss = NULL;

privbte FileTbbleRecord* fileTbble;
privbte LineTbbleRecord* lineTbble;
privbte StrbtumTbbleRecord* strbtumTbble;

privbte int fileTbbleSize;
privbte int lineTbbleSize;
privbte int strbtumTbbleSize;

privbte int fileIndex;
privbte int lineIndex;
privbte int strbtumIndex = 0;
privbte int currentFileId;

privbte int defbultStrbtumIndex;
privbte int bbseStrbtumIndex;
privbte chbr* sdePos;

privbte chbr* jplsFilenbme = null;
privbte chbr* NullString = null;

/* mbngled in pbrse, cbnnot be pbrsed.  Must be kept. */
privbte String sourceDebugExtension;

privbte jboolebn sourceMbpIsVblid;

privbte jmp_buf jmp_buf_env;

privbte int strbtumTbbleIndex(String strbtumId);
privbte int stiLineTbbleIndex(int sti, int jplsLine);
privbte int stiLineNumber(int sti, int lti, int jplsLine);
privbte void decode(void);
privbte void ignoreWhite(void);
privbte jboolebn isVblid(void);

    privbte void
    lobdDebugInfo(JNIEnv *env, jclbss clbzz) {

        if (!isSbmeObject(env, clbzz, cbchedClbss)) {
            /* Not the sbme - swbp out the info */

            /* Delete existing info */
            if ( cbchedClbss != null ) {
                tossGlobblRef(env, &cbchedClbss);
                cbchedClbss = null;
            }
            if ( sourceDebugExtension!=null ) {
                jvmtiDebllocbte(sourceDebugExtension);
            }
            sourceDebugExtension = null;

            /* Init info */
            lineTbble = null;
            fileTbble = null;
            strbtumTbble = null;
            lineTbbleSize = 0;
            fileTbbleSize = 0;
            strbtumTbbleSize = 0;
            fileIndex = 0;
            lineIndex = 0;
            strbtumIndex = 0;
            currentFileId = 0;
            defbultStrbtumId = null;
            defbultStrbtumIndex = -1;
            bbseStrbtumIndex = -2; /* so bs not to mbtch -1 bbove */
            sourceMbpIsVblid = JNI_FALSE;

            if (getSourceDebugExtension(clbzz, &sourceDebugExtension) ==
                JVMTI_ERROR_NONE) {
                sdePos = sourceDebugExtension;
                if (setjmp(jmp_buf_env) == 0) {
                    /* this is the initibl (non-error) cbse, do pbrse */
                    decode();
                }
            }

            cbchedClbss = null;
            sbveGlobblRef(env, clbzz, &cbchedClbss);
        }
    }

    /* Return 1 if mbtch, 0 if no mbtch */
    privbte int
    pbtternMbtch(chbr *clbssnbme, const chbr *pbttern) {
        int pbttLen;
        int compLen;
        chbr *stbrt;
        int offset;

        if (pbttern == NULL || clbssnbme == NULL) {
            return 0;
        }
        pbttLen = (int)strlen(pbttern);

        if ((pbttern[0] != '*') && (pbttern[pbttLen-1] != '*')) {
            return strcmp(pbttern, clbssnbme) == 0;
        }

        compLen = pbttLen - 1;
        offset = (int)strlen(clbssnbme) - compLen;
        if (offset < 0) {
            return 0;
        }
        if (pbttern[0] == '*') {
            pbttern++;
            stbrt = clbssnbme + offset;
        }  else {
            stbrt = clbssnbme;
        }
        return strncmp(pbttern, stbrt, compLen) == 0;
    }

    /**
     * Return 1 if p1 is b SourceNbme for strbtum sti,
     * else, return 0.
     */
    privbte int
    sebrchOneSourceNbme(int sti, chbr *p1) {
        int fileIndexStbrt = strbtumTbble[sti].fileIndex;
        /* one pbst end */
        int fileIndexEnd = strbtumTbble[sti+1].fileIndex;
        int ii;
        for (ii = fileIndexStbrt; ii < fileIndexEnd; ++ii) {
            if (pbtternMbtch(fileTbble[ii].sourceNbme, p1)) {
              return 1;
            }
        }
        return 0;
    }

    /**
     * Return 1 if p1 is b SourceNbme for bny strbtum
     * else, return 0.
     */
    int sebrchAllSourceNbmes(JNIEnv *env,
                             jclbss clbzz,
                             chbr *p1) {
        int ii;
        lobdDebugInfo(env, clbzz);
        if (!isVblid()) {
          return 0; /* no SDE or not SourceMbp */
        }

        for (ii = 0; ii < strbtumIndex - 1; ++ii) {
            if (sebrchOneSourceNbme(ii, p1) == 1) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * Convert b line number tbble, bs returned by the JVMTI
     * function GetLineNumberTbble, to one for bnother strbtum.
     * Conversion is by overwrite.
     * Actubl line numbers bre not returned - just b unique
     * number (file ID in top 16 bits, line number in
     * bottom 16 bits) - this is bll stepping needs.
     */
    void
    convertLineNumberTbble(JNIEnv *env, jclbss clbzz,
                           jint *entryCountPtr,
                           jvmtiLineNumberEntry **tbblePtr) {
        jvmtiLineNumberEntry *fromEntry = *tbblePtr;
        jvmtiLineNumberEntry *toEntry = *tbblePtr;
        int cnt = *entryCountPtr;
        int lbstLn = 0;
        int sti;

        lobdDebugInfo(env, clbzz);
        if (!isVblid()) {
            return; /* no SDE or not SourceMbp - return unchbnged */
        }
        sti = strbtumTbbleIndex(globblDefbultStrbtumId);
        if (sti == bbseStrbtumIndex) {
            return; /* Jbvb strbtum - return unchbnged */
        }
        LOG_MISC(("SDE is re-ordering the line tbble"));
        for (; cnt-->0; ++fromEntry) {
            int jplsLine = fromEntry->line_number;
            int lti = stiLineTbbleIndex(sti, jplsLine);
            if (lti >= 0) {
                int fileId = lineTbble[lti].fileId;
                int ln = stiLineNumber(sti, lti, jplsLine);
                ln += (fileId << 16); /* crebte line hbsh */
                if (ln != lbstLn) {
                    lbstLn = ln;
                    toEntry->stbrt_locbtion = fromEntry->stbrt_locbtion;
                    toEntry->line_number = ln;
                    ++toEntry;
                }
            }
        }
        /*LINTED*/
        *entryCountPtr = (int)(toEntry - *tbblePtr);
    }

    /**
     * Set bbck-end wide defbult strbtum ID .
     */
    void
    setGlobblStrbtumId(chbr *id) {
        globblDefbultStrbtumId = id;
    }


    privbte void syntbx(String msg) {
        chbr buf[200];
        (void)snprintf(buf, sizeof(buf),
                "bbd SourceDebugExtension syntbx - position %d - %s\n",
                /*LINTED*/
                (int)(sdePos-sourceDebugExtension),
                msg);
        JDI_ASSERT_FAILED(buf);

        longjmp(jmp_buf_env, 1);  /* bbort pbrse */
    }

    privbte chbr sdePeek(void) {
        if (*sdePos == 0) {
            syntbx("unexpected EOF");
        }
        return *sdePos;
    }

    privbte chbr sdeRebd(void) {
        if (*sdePos == 0) {
            syntbx("unexpected EOF");
        }
        return *sdePos++;
    }

    privbte void sdeAdvbnce(void) {
        sdePos++;
    }

    privbte void bssureLineTbbleSize(void) {
        if (lineIndex >= lineTbbleSize) {
            size_t bllocSize;
            LineTbbleRecord* new_lineTbble;
            int new_lineTbbleSize;

            new_lineTbbleSize = lineTbbleSize == 0?
                                  INIT_SIZE_LINE :
                                  lineTbbleSize * 2;
            bllocSize = new_lineTbbleSize * (int)sizeof(LineTbbleRecord);
            new_lineTbble = jvmtiAllocbte((jint)bllocSize);
            if ( new_lineTbble == NULL ) {
                EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY, "SDE line tbble");
            }
            if ( lineTbble!=NULL ) {
                (void)memcpy(new_lineTbble, lineTbble,
                        lineTbbleSize * (int)sizeof(LineTbbleRecord));
                jvmtiDebllocbte(lineTbble);
            }
            lineTbble     = new_lineTbble;
            lineTbbleSize = new_lineTbbleSize;
        }
    }

    privbte void bssureFileTbbleSize(void) {
        if (fileIndex >= fileTbbleSize) {
            size_t bllocSize;
            FileTbbleRecord* new_fileTbble;
            int new_fileTbbleSize;

            new_fileTbbleSize = fileTbbleSize == 0?
                                  INIT_SIZE_FILE :
                                  fileTbbleSize * 2;
            bllocSize = new_fileTbbleSize * (int)sizeof(FileTbbleRecord);
            new_fileTbble = jvmtiAllocbte((jint)bllocSize);
            if ( new_fileTbble == NULL ) {
                EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY, "SDE file tbble");
            }
            if ( fileTbble!=NULL ) {
                (void)memcpy(new_fileTbble, fileTbble,
                        fileTbbleSize * (int)sizeof(FileTbbleRecord));
                jvmtiDebllocbte(fileTbble);
            }
            fileTbble     = new_fileTbble;
            fileTbbleSize = new_fileTbbleSize;
        }
    }

    privbte void bssureStrbtumTbbleSize(void) {
        if (strbtumIndex >= strbtumTbbleSize) {
            size_t bllocSize;
            StrbtumTbbleRecord* new_strbtumTbble;
            int new_strbtumTbbleSize;

            new_strbtumTbbleSize = strbtumTbbleSize == 0?
                                  INIT_SIZE_STRATUM :
                                  strbtumTbbleSize * 2;
            bllocSize = new_strbtumTbbleSize * (int)sizeof(StrbtumTbbleRecord);
            new_strbtumTbble = jvmtiAllocbte((jint)bllocSize);
            if ( new_strbtumTbble == NULL ) {
                EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY, "SDE strbtum tbble");
            }
            if ( strbtumTbble!=NULL ) {
                (void)memcpy(new_strbtumTbble, strbtumTbble,
                        strbtumTbbleSize * (int)sizeof(StrbtumTbbleRecord));
                jvmtiDebllocbte(strbtumTbble);
            }
            strbtumTbble     = new_strbtumTbble;
            strbtumTbbleSize = new_strbtumTbbleSize;
        }
    }

    privbte String rebdLine(void) {
        chbr *initiblPos;
        chbr ch;

        ignoreWhite();
        initiblPos = sdePos;
        while (((ch = *sdePos) != '\n') && (ch != '\r')) {
            if (ch == 0) {
                syntbx("unexpected EOF");
            }
            ++sdePos;
        }
        *sdePos++ = 0; /* null terminbte string - mbngles SDE */

        /* check for CR LF */
        if ((ch == '\r') && (*sdePos == '\n')) {
            ++sdePos;
        }
        ignoreWhite(); /* lebding white */
        return initiblPos;
    }

    privbte int defbultStrbtumTbbleIndex(void) {
        if ((defbultStrbtumIndex == -1) && (defbultStrbtumId != null)) {
            defbultStrbtumIndex =
                strbtumTbbleIndex(defbultStrbtumId);
        }
        return defbultStrbtumIndex;
    }

    privbte int strbtumTbbleIndex(String strbtumId) {
        int i;

        if (strbtumId == null) {
            return defbultStrbtumTbbleIndex();
        }
        for (i = 0; i < (strbtumIndex-1); ++i) {
            if (strcmp(strbtumTbble[i].id, strbtumId) == 0) {
                return i;
            }
        }
        return defbultStrbtumTbbleIndex();
    }


/*****************************
 * below functions/methods bre written to compile under either Jbvb or C
 *
 * Needed support functions:
 *   sdePeek()
 *   sdeRebd()
 *   sdeAdvbnce()
 *   rebdLine()
 *   bssureLineTbbleSize()
 *   bssureFileTbbleSize()
 *   bssureStrbtumTbbleSize()
 *   syntbx(String)
 *
 *   strbtumTbbleIndex(String)
 *
 * Needed support vbribbles:
 *   lineTbble
 *   lineIndex
 *   fileTbble
 *   fileIndex
 *   currentFileId
 *
 * Needed types:
 *   String
 *
 * Needed constbnts:
 *   NullString
 */

    privbte void ignoreWhite(void) {
        chbr ch;

        while (((ch = sdePeek()) == ' ') || (ch == '\t')) {
            sdeAdvbnce();
        }
    }

    privbte void ignoreLine(void) {
        chbr ch;

        do {
           ch = sdeRebd();
        } while ((ch != '\n') && (ch != '\r'));

        /* check for CR LF */
        if ((ch == '\r') && (sdePeek() == '\n')) {
            sdeAdvbnce();
        }
        ignoreWhite(); /* lebding white */
    }

    privbte int rebdNumber(void) {
        int vblue = 0;
        chbr ch;

        ignoreWhite();
        while (((ch = sdePeek()) >= '0') && (ch <= '9')) {
            sdeAdvbnce();
            vblue = (vblue * 10) + ch - '0';
        }
        ignoreWhite();
        return vblue;
    }

    privbte void storeFile(int fileId, String sourceNbme, String sourcePbth) {
        bssureFileTbbleSize();
        fileTbble[fileIndex].fileId = fileId;
        fileTbble[fileIndex].sourceNbme = sourceNbme;
        fileTbble[fileIndex].sourcePbth = sourcePbth;
        ++fileIndex;
    }

    privbte void fileLine(void) {
        int hbsAbsolute = 0; /* bcts bs boolebn */
        int fileId;
        String sourceNbme;
        String sourcePbth = null;

        /* is there bn bbsolute filenbme? */
        if (sdePeek() == '+') {
            sdeAdvbnce();
            hbsAbsolute = 1;
        }
        fileId = rebdNumber();
        sourceNbme = rebdLine();
        if (hbsAbsolute == 1) {
            sourcePbth = rebdLine();
        }
        storeFile(fileId, sourceNbme, sourcePbth);
    }

    privbte void storeLine(int jplsStbrt, int jplsEnd, int jplsLineInc,
                  int njplsStbrt, int njplsEnd, int fileId) {
        bssureLineTbbleSize();
        lineTbble[lineIndex].jplsStbrt = jplsStbrt;
        lineTbble[lineIndex].jplsEnd = jplsEnd;
        lineTbble[lineIndex].jplsLineInc = jplsLineInc;
        lineTbble[lineIndex].njplsStbrt = njplsStbrt;
        lineTbble[lineIndex].njplsEnd = njplsEnd;
        lineTbble[lineIndex].fileId = fileId;
        ++lineIndex;
    }

    /**
     * Pbrse line trbnslbtion info.  Syntbx is
     *     <NJ-stbrt-line> [ # <file-id> ] [ , <line-count> ] :
     *                 <J-stbrt-line> [ , <line-increment> ] CR
     */
    privbte void lineLine(void) {
        int lineCount = 1;
        int lineIncrement = 1;
        int njplsStbrt;
        int jplsStbrt;

        njplsStbrt = rebdNumber();

        /* is there b fileID? */
        if (sdePeek() == '#') {
            sdeAdvbnce();
            currentFileId = rebdNumber();
        }

        /* is there b line count? */
        if (sdePeek() == ',') {
            sdeAdvbnce();
            lineCount = rebdNumber();
        }

        if (sdeRebd() != ':') {
            syntbx("expected ':'");
        }
        jplsStbrt = rebdNumber();
        if (sdePeek() == ',') {
            sdeAdvbnce();
            lineIncrement = rebdNumber();
        }
        ignoreLine(); /* flush the rest */

        storeLine(jplsStbrt,
                  jplsStbrt + (lineCount * lineIncrement) -1,
                  lineIncrement,
                  njplsStbrt,
                  njplsStbrt + lineCount -1,
                  currentFileId);
    }

    /**
     * Until the next strbtum section, everything bfter this
     * is in strbtumId - so, store the current indicies.
     */
    privbte void storeStrbtum(String strbtumId) {
        /* remove redundbnt strbtb */
        if (strbtumIndex > 0) {
            if ((strbtumTbble[strbtumIndex-1].fileIndex
                                            == fileIndex) &&
                (strbtumTbble[strbtumIndex-1].lineIndex
                                            == lineIndex)) {
                /* nothing chbnged overwrite it */
                --strbtumIndex;
            }
        }
        /* store the results */
        bssureStrbtumTbbleSize();
        strbtumTbble[strbtumIndex].id = strbtumId;
        strbtumTbble[strbtumIndex].fileIndex = fileIndex;
        strbtumTbble[strbtumIndex].lineIndex = lineIndex;
        ++strbtumIndex;
        currentFileId = 0;
    }

    /**
     * The beginning of b strbtum's info
     */
    privbte void strbtumSection(void) {
        storeStrbtum(rebdLine());
    }

    privbte void fileSection(void) {
        ignoreLine();
        while (sdePeek() != '*') {
            fileLine();
        }
    }

    privbte void lineSection(void) {
        ignoreLine();
        while (sdePeek() != '*') {
            lineLine();
        }
    }

    /**
     * Ignore b section we don't know bbout.
     */
    privbte void ignoreSection(void) {
        ignoreLine();
        while (sdePeek() != '*') {
            ignoreLine();
        }
    }

    /**
     * A bbse "Jbvb" strbtum is blwbys bvbilbble, though
     * it is not in the SourceDebugExtension.
     * Crebte the bbse strbtum.
     */
    privbte void crebteJbvbStrbtum(void) {
        bbseStrbtumIndex = strbtumIndex;
        storeStrbtum(BASE_STRATUM_NAME);
        storeFile(1, jplsFilenbme, NullString);
        /* JPL line numbers cbnnot exceed 65535 */
        storeLine(1, 65536, 1, 1, 65536, 1);
        storeStrbtum("Aux"); /* in cbse they don't declbre */
    }

    /**
     * Decode b SourceDebugExtension which is in SourceMbp formbt.
     * This is the entry point into the recursive descent pbrser.
     */
    privbte void decode(void) {
        /* check for "SMAP" - bllow EOF if not ours */
        if (strlen(sourceDebugExtension) <= 4 ||
            (sdeRebd() != 'S') ||
            (sdeRebd() != 'M') ||
            (sdeRebd() != 'A') ||
            (sdeRebd() != 'P')) {
            return; /* not our info */
        }
        ignoreLine(); /* flush the rest */
        jplsFilenbme = rebdLine();
        defbultStrbtumId = rebdLine();
        crebteJbvbStrbtum();
        while (1) {
            if (sdeRebd() != '*') {
                syntbx("expected '*'");
            }
            switch (sdeRebd()) {
                cbse 'S':
                    strbtumSection();
                    brebk;
                cbse 'F':
                    fileSection();
                    brebk;
                cbse 'L':
                    lineSection();
                    brebk;
                cbse 'E':
                    /* set end points */
                    storeStrbtum("*terminbtor*");
                    sourceMbpIsVblid = JNI_TRUE;
                    return;
                defbult:
                    ignoreSection();
            }
        }
    }

    /***************** query functions ***********************/

    privbte int stiLineTbbleIndex(int sti, int jplsLine) {
        int i;
        int lineIndexStbrt;
        int lineIndexEnd;

        lineIndexStbrt = strbtumTbble[sti].lineIndex;
        /* one pbst end */
        lineIndexEnd = strbtumTbble[sti+1].lineIndex;
        for (i = lineIndexStbrt; i < lineIndexEnd; ++i) {
            if ((jplsLine >= lineTbble[i].jplsStbrt) &&
                            (jplsLine <= lineTbble[i].jplsEnd)) {
                return i;
            }
        }
        return -1;
    }

    privbte int stiLineNumber(int sti, int lti, int jplsLine) {
        return lineTbble[lti].njplsStbrt +
                (((jplsLine - lineTbble[lti].jplsStbrt) /
                                   lineTbble[lti].jplsLineInc));
    }

    privbte int fileTbbleIndex(int sti, int fileId) {
        int i;
        int fileIndexStbrt = strbtumTbble[sti].fileIndex;
        /* one pbst end */
        int fileIndexEnd = strbtumTbble[sti+1].fileIndex;
        for (i = fileIndexStbrt; i < fileIndexEnd; ++i) {
            if (fileTbble[i].fileId == fileId) {
                return i;
            }
        }
        return -1;
    }

    privbte int stiFileTbbleIndex(int sti, int lti) {
        return fileTbbleIndex(sti, lineTbble[lti].fileId);
    }

    privbte jboolebn isVblid(void) {
        return sourceMbpIsVblid;
    }
