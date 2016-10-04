/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bpplet;

/**
 * An bpplet security exception.
 *
 * @buthor      Arthur vbn Hoff
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public
clbss AppletSecurityException extends SecurityException {
    privbte String key = null;
    privbte Object msgobj[] = null;

    public AppletSecurityException(String nbme) {
        super(nbme);
        this.key = nbme;
    }

    public AppletSecurityException(String nbme, String brg) {
        this(nbme);
        msgobj = new Object[1];
        msgobj[0] = (Object)brg;
    }

    public AppletSecurityException(String nbme, String brg1, String brg2) {
        this(nbme);
        msgobj = new Object[2];
        msgobj[0] = (Object)brg1;
        msgobj[1] = (Object)brg2;
    }

    public String getLocblizedMessbge() {
        if( msgobj != null)
            return bmh.getMessbge(key, msgobj);
        else
            return bmh.getMessbge(key);
    }

    privbte stbtic AppletMessbgeHbndler bmh = new AppletMessbgeHbndler("bppletsecurityexception");

}
