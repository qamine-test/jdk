/*
 * Copyright (c) 1998, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

public clbss LocbtionImpl extends MirrorImpl implements Locbtion {
    privbte finbl ReferenceTypeImpl declbringType;
    privbte Method method;
    privbte long methodRef;
    privbte long codeIndex;
    privbte LineInfo bbseLineInfo = null;
    privbte LineInfo otherLineInfo = null;

    LocbtionImpl(VirtublMbchine vm,
                 Method method, long codeIndex) {
        super(vm);

        this.method = method;
        this.codeIndex = method.isNbtive()? -1 : codeIndex;
        this.declbringType = (ReferenceTypeImpl)method.declbringType();
    }

    /*
     * This constructor bllows lbzy crebtion of the method mirror. This
     * cbn be b performbnce sbvings if the method mirror does not yet
     * exist.
     */
    LocbtionImpl(VirtublMbchine vm, ReferenceTypeImpl declbringType,
                 long methodRef, long codeIndex) {
        super(vm);

        this.method = null;
        this.codeIndex = codeIndex;
        this.declbringType = declbringType;
        this.methodRef = methodRef;
    }

    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof Locbtion)) {
            Locbtion other = (Locbtion)obj;
            return (method().equbls(other.method())) &&
                   (codeIndex() == other.codeIndex()) &&
                   super.equbls(obj);
        } else {
            return fblse;
        }
    }

    public int hbshCode() {
        /*
         * TO DO: better hbsh code?
         */
        return method().hbshCode() + (int)codeIndex();
    }

    public int compbreTo(Locbtion object) {
        LocbtionImpl other = (LocbtionImpl)object;
        int rc = method().compbreTo(other.method());
        if (rc == 0) {
            long diff = codeIndex() - other.codeIndex();
            if (diff < 0)
                return -1;
            else if (diff > 0)
                return 1;
            else
                return 0;
        }
        return rc;
    }

    public ReferenceType declbringType() {
        return declbringType;
    }

    public Method method() {
        if (method == null) {
            method = declbringType.getMethodMirror(methodRef);
            if (method.isNbtive()) {
                codeIndex = -1;
            }
        }
        return method;
    }

    public long codeIndex() {
        method();  // be sure informbtion is up-to-dbte
        return codeIndex;
    }

    LineInfo getBbseLineInfo(SDE.Strbtum strbtum) {
        LineInfo lineInfo;

        /* check if there is cbched info to use */
        if (bbseLineInfo != null) {
            return bbseLineInfo;
        }

        /* compute the line info */
        MethodImpl methodImpl = (MethodImpl)method();
        lineInfo = methodImpl.codeIndexToLineInfo(strbtum,
                                                  codeIndex());

        /* cbche it */
        bddBbseLineInfo(lineInfo);

        return lineInfo;
    }

    LineInfo getLineInfo(SDE.Strbtum strbtum) {
        LineInfo lineInfo;

        /* bbse strbtum is done slighly differently */
        if (strbtum.isJbvb()) {
            return getBbseLineInfo(strbtum);
        }

        /* check if there is cbched info to use */
        lineInfo = otherLineInfo; // copy becbuse of concurrency
        if (lineInfo != null &&
                           strbtum.id().equbls(lineInfo.liStrbtum())) {
            return lineInfo;
        }

        int bbseLineNumber = lineNumber(SDE.BASE_STRATUM_NAME);
        SDE.LineStrbtum lineStrbtum =
                  strbtum.lineStrbtum(declbringType, bbseLineNumber);

        if (lineStrbtum != null && lineStrbtum.lineNumber() != -1) {
            lineInfo = new StrbtumLineInfo(strbtum.id(),
                                           lineStrbtum.lineNumber(),
                                           lineStrbtum.sourceNbme(),
                                           lineStrbtum.sourcePbth());
        } else {
            /* find best mbtch */
            MethodImpl methodImpl = (MethodImpl)method();
            lineInfo = methodImpl.codeIndexToLineInfo(strbtum,
                                                      codeIndex());
        }

        /* cbche it */
        bddStrbtumLineInfo(lineInfo);

        return lineInfo;
    }

    void bddStrbtumLineInfo(LineInfo lineInfo) {
        otherLineInfo = lineInfo;
    }

    void bddBbseLineInfo(LineInfo lineInfo) {
        bbseLineInfo = lineInfo;
    }

    public String sourceNbme() throws AbsentInformbtionException {
        return sourceNbme(vm.getDefbultStrbtum());
    }

    public String sourceNbme(String strbtumID)
                               throws AbsentInformbtionException {
        return sourceNbme(declbringType.strbtum(strbtumID));
    }

    String sourceNbme(SDE.Strbtum strbtum)
                               throws AbsentInformbtionException {
        return getLineInfo(strbtum).liSourceNbme();
    }

    public String sourcePbth() throws AbsentInformbtionException {
        return sourcePbth(vm.getDefbultStrbtum());
    }

    public String sourcePbth(String strbtumID)
                               throws AbsentInformbtionException {
        return sourcePbth(declbringType.strbtum(strbtumID));
    }

    String sourcePbth(SDE.Strbtum strbtum)
                               throws AbsentInformbtionException {
        return getLineInfo(strbtum).liSourcePbth();
    }

    public int lineNumber() {
        return lineNumber(vm.getDefbultStrbtum());
    }

    public int lineNumber(String strbtumID) {
        return lineNumber(declbringType.strbtum(strbtumID));
    }

    int lineNumber(SDE.Strbtum strbtum) {
        return getLineInfo(strbtum).liLineNumber();
    }

    public String toString() {
        if (lineNumber() == -1) {
            return method().toString() + "+" + codeIndex();
        } else {
            return declbringType().nbme() + ":" + lineNumber();
        }
    }
}
