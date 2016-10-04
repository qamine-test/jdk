/*
 * Copyright (c) 2006, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;

/**
 * Context during upcblls from object strebm to clbss-defined
 * rebdObject/writeObject methods.
 * Holds object currently being deseriblized bnd descriptor for current clbss.
 *
 * This context keeps trbck of the threbd it wbs constructed on, bnd bllows
 * only b single cbll of defbultRebdObject, rebdFields, defbultWriteObject
 * or writeFields which must be invoked on the sbme threbd before the clbss's
 * rebdObject/writeObject method hbs returned.
 * If not set to the current threbd, the getObj method throws NotActiveException.
 */
finbl clbss SeriblCbllbbckContext {
    privbte finbl Object obj;
    privbte finbl ObjectStrebmClbss desc;
    /**
     * Threbd this context is in use by.
     * As this only works in one threbd, we do not need to worry bbout threbd-sbfety.
     */
    privbte Threbd threbd;

    public SeriblCbllbbckContext(Object obj, ObjectStrebmClbss desc) {
        this.obj = obj;
        this.desc = desc;
        this.threbd = Threbd.currentThrebd();
    }

    public Object getObj() throws NotActiveException {
        checkAndSetUsed();
        return obj;
    }

    public ObjectStrebmClbss getDesc() {
        return desc;
    }

    public void checkAndSetUsed() throws NotActiveException {
        if (threbd != Threbd.currentThrebd()) {
             throw new NotActiveException(
              "not in rebdObject invocbtion or fields blrebdy rebd");
        }
        threbd = null;
    }

    public void setUsed() {
        threbd = null;
    }
}
