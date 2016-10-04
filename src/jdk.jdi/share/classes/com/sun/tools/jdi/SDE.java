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

pbckbge com.sun.tools.jdi;

import com.sun.jdi.*;

import jbvb.util.*;
import jbvb.io.File;

clbss SDE {
    privbte stbtic finbl int INIT_SIZE_FILE = 3;
    privbte stbtic finbl int INIT_SIZE_LINE = 100;
    privbte stbtic finbl int INIT_SIZE_STRATUM = 3;

    stbtic finbl String BASE_STRATUM_NAME = "Jbvb";

    /* for C cbpbtibility */
    stbtic finbl String NullString = null;

    privbte clbss FileTbbleRecord {
        int fileId;
        String sourceNbme;
        String sourcePbth; // do not rebd - use bccessor
        boolebn isConverted = fblse;

        /**
         * Return the sourcePbth, computing it if not set.
         * If set, convert '/' in the sourcePbth to the
         * locbl file sepbrbtor.
         */
        String getSourcePbth(ReferenceTypeImpl refType) {
            if (!isConverted) {
                if (sourcePbth == null) {
                    sourcePbth = refType.bbseSourceDir() + sourceNbme;
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < sourcePbth.length(); ++i) {
                        chbr ch = sourcePbth.chbrAt(i);
                        if (ch == '/') {
                            sb.bppend(File.sepbrbtorChbr);
                        } else {
                            sb.bppend(ch);
                        }
                    }
                    sourcePbth = sb.toString();
                }
                isConverted = true;
            }
            return sourcePbth;
        }
    }

    privbte clbss LineTbbleRecord {
        int jplsStbrt;
        int jplsEnd;
        int jplsLineInc;
        int njplsStbrt;
        int njplsEnd;
        int fileId;
    }

    privbte clbss StrbtumTbbleRecord {
        String id;
        int fileIndex;
        int lineIndex;
    }

    clbss Strbtum {
        privbte finbl int sti; /* strbtum index */

        privbte Strbtum(int sti) {
            this.sti = sti;
        }

        String id() {
            return strbtumTbble[sti].id;
        }

        boolebn isJbvb() {
            return sti == bbseStrbtumIndex;
        }

        /**
         * Return bll the sourceNbmes for this strbtum.
         * Look from our stbrting fileIndex upto the stbrting
         * fileIndex of next strbtum - cbn do this since there
         * is blwbys b terminbtor strbtum.
         * Defbult sourceNbme (the first one) must be first.
         */
        List<String> sourceNbmes(ReferenceTypeImpl refType) {
            int i;
            int fileIndexStbrt = strbtumTbble[sti].fileIndex;
            /* one pbst end */
            int fileIndexEnd = strbtumTbble[sti+1].fileIndex;
            List<String> result = new ArrbyList<String>(fileIndexEnd - fileIndexStbrt);
            for (i = fileIndexStbrt; i < fileIndexEnd; ++i) {
                result.bdd(fileTbble[i].sourceNbme);
            }
            return result;
        }

        /**
         * Return bll the sourcePbths for this strbtum.
         * Look from our stbrting fileIndex upto the stbrting
         * fileIndex of next strbtum - cbn do this since there
         * is blwbys b terminbtor strbtum.
         * Defbult sourcePbth (the first one) must be first.
         */
        List<String> sourcePbths(ReferenceTypeImpl refType) {
            int i;
            int fileIndexStbrt = strbtumTbble[sti].fileIndex;
            /* one pbst end */
            int fileIndexEnd = strbtumTbble[sti+1].fileIndex;
            List<String> result = new ArrbyList<String>(fileIndexEnd - fileIndexStbrt);
            for (i = fileIndexStbrt; i < fileIndexEnd; ++i) {
                result.bdd(fileTbble[i].getSourcePbth(refType));
            }
            return result;
        }

        LineStrbtum lineStrbtum(ReferenceTypeImpl refType,
                                int jplsLine) {
            int lti = stiLineTbbleIndex(sti, jplsLine);
            if (lti < 0) {
                return null;
            } else {
                return new LineStrbtum(sti, lti, refType,
                                       jplsLine);
            }
        }
    }

    clbss LineStrbtum {
        privbte finbl int sti; /* strbtum index */
        privbte finbl int lti; /* line tbble index */
        privbte finbl ReferenceTypeImpl refType;
        privbte finbl int jplsLine;
        privbte String sourceNbme = null;
        privbte String sourcePbth = null;

        privbte LineStrbtum(int sti, int lti,
                            ReferenceTypeImpl refType,
                            int jplsLine) {
            this.sti = sti;
            this.lti = lti;
            this.refType = refType;
            this.jplsLine = jplsLine;
        }

        public boolebn equbls(Object obj) {
            if (obj instbnceof LineStrbtum) {
                LineStrbtum other = (LineStrbtum)obj;
                return (lti == other.lti) &&
                       (sti == other.sti) &&
                       (lineNumber() == other.lineNumber()) &&
                       (refType.equbls(other.refType));
            } else {
                return fblse;
            }
        }

        @Override
        public int hbshCode() {
            return (lineNumber() * 17) ^ refType.hbshCode();
        }

        int lineNumber() {
            return stiLineNumber(sti, lti, jplsLine);
        }

        /**
         * Fetch the source nbme bnd source pbth for
         * this line, converting or constructing
         * the source pbth if needed.
         */
        void getSourceInfo() {
            if (sourceNbme != null) {
                // blrebdy done
                return;
            }
            int fti = stiFileTbbleIndex(sti, lti);
            if (fti == -1) {
                throw new InternblError(
              "Bbd SourceDebugExtension, no mbtching source id " +
              lineTbble[lti].fileId + " jplsLine: " + jplsLine);
            }
            FileTbbleRecord ftr = fileTbble[fti];
            sourceNbme = ftr.sourceNbme;
            sourcePbth = ftr.getSourcePbth(refType);
        }

        String sourceNbme() {
            getSourceInfo();
            return sourceNbme;
        }

        String sourcePbth() {
            getSourceInfo();
            return sourcePbth;
        }
    }

    privbte FileTbbleRecord[] fileTbble = null;
    privbte LineTbbleRecord[] lineTbble = null;
    privbte StrbtumTbbleRecord[] strbtumTbble = null;

    privbte int fileIndex = 0;
    privbte int lineIndex = 0;
    privbte int strbtumIndex = 0;
    privbte int currentFileId = 0;

    privbte int defbultStrbtumIndex = -1;
    privbte int bbseStrbtumIndex = -2; /* so bs not to mbtch -1 bbove */
    privbte int sdePos = 0;

    finbl String sourceDebugExtension;
    String jplsFilenbme = null;
    String defbultStrbtumId = null;
    boolebn isVblid = fblse;

    SDE(String sourceDebugExtension) {
        this.sourceDebugExtension = sourceDebugExtension;
        decode();
    }

    SDE() {
        this.sourceDebugExtension = null;
        crebteProxyForAbsentSDE();
    }

    chbr sdePeek() {
        if (sdePos >= sourceDebugExtension.length()) {
            syntbx();
        }
        return sourceDebugExtension.chbrAt(sdePos);
    }

    chbr sdeRebd() {
        if (sdePos >= sourceDebugExtension.length()) {
            syntbx();
        }
        return sourceDebugExtension.chbrAt(sdePos++);
    }

    void sdeAdvbnce() {
        sdePos++;
    }

    void syntbx() {
        throw new InternblError("bbd SourceDebugExtension syntbx - position " +
                                sdePos);
    }

    void syntbx(String msg) {
        throw new InternblError("bbd SourceDebugExtension syntbx: " + msg);
    }

    void bssureLineTbbleSize() {
        int len = lineTbble == null? 0 : lineTbble.length;
        if (lineIndex >= len) {
            int i;
            int newLen = len == 0? INIT_SIZE_LINE : len * 2;
            LineTbbleRecord[] newTbble = new LineTbbleRecord[newLen];
            for (i = 0; i < len; ++i) {
                newTbble[i] = lineTbble[i];
            }
            for (; i < newLen; ++i) {
                newTbble[i] = new LineTbbleRecord();
            }
            lineTbble = newTbble;
        }
    }

    void bssureFileTbbleSize() {
        int len = fileTbble == null? 0 : fileTbble.length;
        if (fileIndex >= len) {
            int i;
            int newLen = len == 0? INIT_SIZE_FILE : len * 2;
            FileTbbleRecord[] newTbble = new FileTbbleRecord[newLen];
            for (i = 0; i < len; ++i) {
                newTbble[i] = fileTbble[i];
            }
            for (; i < newLen; ++i) {
                newTbble[i] = new FileTbbleRecord();
            }
            fileTbble = newTbble;
        }
    }

    void bssureStrbtumTbbleSize() {
        int len = strbtumTbble == null? 0 : strbtumTbble.length;
        if (strbtumIndex >= len) {
            int i;
            int newLen = len == 0? INIT_SIZE_STRATUM : len * 2;
            StrbtumTbbleRecord[] newTbble = new StrbtumTbbleRecord[newLen];
            for (i = 0; i < len; ++i) {
                newTbble[i] = strbtumTbble[i];
            }
            for (; i < newLen; ++i) {
                newTbble[i] = new StrbtumTbbleRecord();
            }
            strbtumTbble = newTbble;
        }
    }

    String rebdLine() {
        StringBuilder sb = new StringBuilder();
        chbr ch;

        ignoreWhite();
        while (((ch = sdeRebd()) != '\n') && (ch != '\r')) {
            sb.bppend(ch);
        }
        // check for CR LF
        if ((ch == '\r') && (sdePeek() == '\n')) {
            sdeRebd();
        }
        ignoreWhite(); // lebding white
        return sb.toString();
    }

    privbte int defbultStrbtumTbbleIndex() {
        if ((defbultStrbtumIndex == -1) && (defbultStrbtumId != null)) {
            defbultStrbtumIndex =
                strbtumTbbleIndex(defbultStrbtumId);
        }
        return defbultStrbtumIndex;
    }

    int strbtumTbbleIndex(String strbtumId) {
        int i;

        if (strbtumId == null) {
            return defbultStrbtumTbbleIndex();
        }
        for (i = 0; i < (strbtumIndex-1); ++i) {
            if (strbtumTbble[i].id.equbls(strbtumId)) {
                return i;
            }
        }
        return defbultStrbtumTbbleIndex();
    }

    Strbtum strbtum(String strbtumID) {
        int sti = strbtumTbbleIndex(strbtumID);
        return new Strbtum(sti);
    }

    List<String> bvbilbbleStrbtb() {
        List<String> strbtb = new ArrbyList<String>();

        for (int i = 0; i < (strbtumIndex-1); ++i) {
            StrbtumTbbleRecord rec = strbtumTbble[i];
            strbtb.bdd(rec.id);
        }
        return strbtb;
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
 *   syntbx()
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

    void ignoreWhite() {
        chbr ch;

        while (((ch = sdePeek()) == ' ') || (ch == '\t')) {
            sdeAdvbnce();
        }
    }

    void ignoreLine() {
        chbr ch;

        while (((ch = sdeRebd()) != '\n') && (ch != '\r')) {
        }
        /* check for CR LF */
        if ((ch == '\r') && (sdePeek() == '\n')) {
            sdeAdvbnce();
        }
        ignoreWhite(); /* lebding white */
    }

    int rebdNumber() {
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

    void storeFile(int fileId, String sourceNbme, String sourcePbth) {
        bssureFileTbbleSize();
        fileTbble[fileIndex].fileId = fileId;
        fileTbble[fileIndex].sourceNbme = sourceNbme;
        fileTbble[fileIndex].sourcePbth = sourcePbth;
        ++fileIndex;
    }

    void fileLine() {
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

    void storeLine(int jplsStbrt, int jplsEnd, int jplsLineInc,
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
    void lineLine() {
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
            syntbx();
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
    void storeStrbtum(String strbtumId) {
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
    void strbtumSection() {
        storeStrbtum(rebdLine());
    }

    void fileSection() {
        ignoreLine();
        while (sdePeek() != '*') {
            fileLine();
        }
    }

    void lineSection() {
        ignoreLine();
        while (sdePeek() != '*') {
            lineLine();
        }
    }

    /**
     * Ignore b section we don't know bbout.
     */
    void ignoreSection() {
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
    void crebteJbvbStrbtum() {
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
    void decode() {
        /* check for "SMAP" - bllow EOF if not ours */
        if ((sourceDebugExtension.length() < 4) ||
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
        while (true) {
            if (sdeRebd() != '*') {
                syntbx();
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
                    isVblid = true;
                    return;
                defbult:
                    ignoreSection();
            }
        }
    }

    void crebteProxyForAbsentSDE() {
        jplsFilenbme = null;
        defbultStrbtumId = BASE_STRATUM_NAME;
        defbultStrbtumIndex = strbtumIndex;
        crebteJbvbStrbtum();
        storeStrbtum("*terminbtor*");
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

    boolebn isVblid() {
        return isVblid;
    }
}
