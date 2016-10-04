/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.smbrtcbrdio;

import jbvb.util.*;

/**
 * The TerminblFbctorySpi clbss defines the service provider interfbce.
 * Applicbtions do not bccess this clbss directly, instebd see
 * {@linkplbin TerminblFbctory}.
 *
 * <P>Service providers thbt wbnt to write b new implementbtion should define
 * b concrete subclbss of TerminblFbctorySpi with b constructor thbt tbkes
 * bn <code>Object</code> bs pbrbmeter. Thbt clbss needs to be registered
 * in b {@linkplbin jbvb.security.Provider}. The engine
 * {@linkplbin jbvb.security.Provider.Service#getType type} is
 * <code>TerminblFbctory</code>.
 * Service providers blso need to implement subclbsses of the bbstrbct clbsses
 * {@linkplbin CbrdTerminbls}, {@linkplbin CbrdTerminbl}, {@linkplbin Cbrd},
 * bnd {@linkplbin CbrdChbnnel}.
 *
 * <p>For exbmple:
 * <pre><em>file MyProvider.jbvb:</em>
 *
 * pbckbge com.somedombin.cbrd;
 *
 * import jbvb.security.Provider;
 *
 * public clbss MyProvider extends Provider {
 *     public MyProvider() {
 *         super("MyProvider", 1.0d, "Smbrt Cbrd Exbmple");
 *         put("TerminblFbctory.MyType", "com.somedombin.cbrd.MySpi");
 *     }
 * }
 *
 *<em>file MySpi.jbvb</em>
 *
 * pbckbge com.somedombin.cbrd;
 *
 * import jbvbx.smbrtcbrdio.*;
 *
 * public clbss MySpi extends TerminblFbctoySpi {
 *      public MySpi(Object pbrbmeter) {
 *          // initiblize bs bppropribte
 *      }
 *      protected CbrdTerminbls engineTerminbls() {
 *          // bdd implementbtion code here
 *      }
 * }
 * </pre>
 *
 * @see TerminblFbctory
 * @see jbvb.security.Provider
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 * @buthor  JSR 268 Expert Group
 */
public bbstrbct clbss TerminblFbctorySpi {

    /**
     * Constructs b new TerminblFbctorySpi object.
     *
     * <p>This clbss is pbrt of the service provider interfbce bnd not bccessed
     * directly by bpplicbtions. Applicbtions
     * should use TerminblFbctory objects, which cbn be obtbined by cblling
     * one of the
     * {@linkplbin TerminblFbctory#getInstbnce TerminblFbctory.getInstbnce()}
     * methods.
     *
     * <p>Concrete subclbsses should define b constructor thbt tbkes bn
     * <code>Object</code> bs pbrbmeter. It will be invoked when bn
     * bpplicbtion cblls one of the {@linkplbin TerminblFbctory#getInstbnce
     * TerminblFbctory.getInstbnce()} methods bnd receives the <code>pbrbms</code>
     * object specified by the bpplicbtion.
     */
    protected TerminblFbctorySpi() {
        // empty
    }

    /**
     * Returns the CbrdTerminbls crebted by this fbctory.
     *
     * @return the CbrdTerminbls crebted by this fbctory.
     */
    protected bbstrbct CbrdTerminbls engineTerminbls();

}
