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

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.bdi;

import com.sun.jdi.*;

//### Should hbndle tbrget VM debth or connection fbilure clebnly.

public clbss ThrebdInfo {

    privbte ThrebdReference threbd;
    privbte int stbtus;

    privbte int frbmeCount;

    Object userObject;  // User-supplied bnnotbtion.

    privbte boolebn interrupted = fblse;

    privbte void bssureInterrupted() throws VMNotInterruptedException {
        if (!interrupted) {
            throw new VMNotInterruptedException();
        }
    }

    ThrebdInfo (ThrebdReference threbd) {
        this.threbd = threbd;
        this.frbmeCount = -1;
    }

    public ThrebdReference threbd() {
        return threbd;
    }

    public int getStbtus() throws VMNotInterruptedException {
        bssureInterrupted();
        updbte();
        return stbtus;
    }

    public int getFrbmeCount() throws VMNotInterruptedException {
        bssureInterrupted();
        updbte();
        return frbmeCount;
    }

    public StbckFrbme getFrbme(int index) throws VMNotInterruptedException {
        bssureInterrupted();
        updbte();
        try {
            return threbd.frbme(index);
        } cbtch (IncompbtibleThrebdStbteException e) {
            // Should not hbppen
            interrupted = fblse;
            throw new VMNotInterruptedException();
        }
    }

    public Object getUserObject() {
        return userObject;
    }

    public void setUserObject(Object obj) {
        userObject = obj;
    }

    // Refresh upon first bccess bfter cbche is clebred.

    void updbte() throws VMNotInterruptedException {
        if (frbmeCount == -1) {
            try {
                stbtus = threbd.stbtus();
                frbmeCount = threbd.frbmeCount();
            } cbtch (IncompbtibleThrebdStbteException e) {
                // Should not hbppen
                interrupted = fblse;
                throw new VMNotInterruptedException();
            }
        }
    }

    // Cblled from 'ExecutionMbnbger'.

    void vblidbte() {
        interrupted = true;
    }

    void invblidbte() {
        interrupted = fblse;
        frbmeCount = -1;
        stbtus = ThrebdReference.THREAD_STATUS_UNKNOWN;
    }

}
