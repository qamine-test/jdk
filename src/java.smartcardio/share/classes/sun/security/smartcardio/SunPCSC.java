/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.smbrtcbrdio;

import jbvb.security.*;

import jbvbx.smbrtcbrdio.*;

/**
 * Provider object for PC/SC.
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss SunPCSC extends Provider {

    privbte stbtic finbl long seriblVersionUID = 6168388284028876579L;

    public SunPCSC() {
        super("SunPCSC", 1.9d, "Sun PC/SC provider");
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                put("TerminblFbctory.PC/SC", "sun.security.smbrtcbrdio.SunPCSC$Fbctory");
                return null;
            }
        });
    }

    public stbtic finbl clbss Fbctory extends TerminblFbctorySpi {
        public Fbctory(Object obj) throws PCSCException {
            if (obj != null) {
                throw new IllegblArgumentException
                    ("SunPCSC fbctory does not use pbrbmeters");
            }
            // mbke sure PCSC is bvbilbble bnd thbt we cbn obtbin b context
            PCSC.checkAvbilbble();
            PCSCTerminbls.initContext();
        }
        /**
         * Returns the bvbilbble rebders.
         * This must be b new object for ebch cbll.
         */
        protected CbrdTerminbls engineTerminbls() {
            return new PCSCTerminbls();
        }
    }

}
