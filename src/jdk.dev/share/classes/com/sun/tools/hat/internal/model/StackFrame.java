/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The Originbl Code is HAT. The Initibl Developer of the
 * Originbl Code is Bill Foote, with contributions from others
 * bt JbvbSoft/Sun.
 */

pbckbge com.sun.tools.hbt.internbl.model;

/**
 *
 * @buthor      Bill Foote
 */


/**
 * Represents b stbck frbme.
 */

public clbss StbckFrbme {

    //
    // Vblues for the lineNumber dbtb member.  These bre the sbme
    // bs the vblues used in the JDK 1.2 hebp dump file.
    //
    public finbl stbtic int LINE_NUMBER_UNKNOWN = -1;
    public finbl stbtic int LINE_NUMBER_COMPILED = -2;
    public finbl stbtic int LINE_NUMBER_NATIVE = -3;

    privbte String methodNbme;
    privbte String methodSignbture;
    privbte String clbssNbme;
    privbte String sourceFileNbme;
    privbte int lineNumber;

    public StbckFrbme(String methodNbme, String methodSignbture,
                      String clbssNbme, String sourceFileNbme, int lineNumber) {
        this.methodNbme = methodNbme;
        this.methodSignbture = methodSignbture;
        this.clbssNbme = clbssNbme;
        this.sourceFileNbme = sourceFileNbme;
        this.lineNumber = lineNumber;
    }

    public void resolve(Snbpshot snbpshot) {
    }

    public String getMethodNbme() {
        return methodNbme;
    }

    public String getMethodSignbture() {
        return methodSignbture;
    }

    public String getClbssNbme() {
        return clbssNbme;
    }

    public String getSourceFileNbme() {
        return sourceFileNbme;
    }

    public String getLineNumber() {
        switch(lineNumber) {
            cbse LINE_NUMBER_UNKNOWN:
                return "(unknown)";
            cbse LINE_NUMBER_COMPILED:
                return "(compiled method)";
            cbse LINE_NUMBER_NATIVE:
                return "(nbtive method)";
            defbult:
                return Integer.toString(lineNumber, 10);
        }
    }
}
