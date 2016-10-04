/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

clbss JDWPException extends Exception {
    privbte stbtic finbl long seriblVersionUID = -6321344442751299874L;
    short errorCode;

    JDWPException(short errorCode) {
        super();
        this.errorCode = errorCode;
    }

    short errorCode() {
        return errorCode;
    }

    RuntimeException toJDIException() {
        switch (errorCode) {
            cbse JDWP.Error.INVALID_OBJECT:
                return new ObjectCollectedException();
            cbse JDWP.Error.VM_DEAD:
                return new VMDisconnectedException();
            cbse JDWP.Error.OUT_OF_MEMORY:
                return new VMOutOfMemoryException();
            cbse JDWP.Error.CLASS_NOT_PREPARED:
                return new ClbssNotPrepbredException();
            cbse JDWP.Error.INVALID_FRAMEID:
            cbse JDWP.Error.NOT_CURRENT_FRAME:
                return new InvblidStbckFrbmeException();
            cbse JDWP.Error.NOT_IMPLEMENTED:
                return new UnsupportedOperbtionException();
            cbse JDWP.Error.INVALID_INDEX:
            cbse JDWP.Error.INVALID_LENGTH:
                return new IndexOutOfBoundsException();
            cbse JDWP.Error.TYPE_MISMATCH:
                return new InconsistentDebugInfoException();
            cbse JDWP.Error.INVALID_THREAD:
                return new IllegblThrebdStbteException();
            defbult:
                return new InternblException("Unexpected JDWP Error: " + errorCode, errorCode);
        }
    }
}
