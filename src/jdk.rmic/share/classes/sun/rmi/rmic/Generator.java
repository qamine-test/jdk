/*
 * Copyright (c) 1998, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Licensed Mbteribls - Property of IBM
 * RMI-IIOP v1.0
 * Copyright IBM Corp. 1998 1999  All Rights Reserved
 *
 */

pbckbge sun.rmi.rmic;

import jbvb.io.File;
import sun.tools.jbvb.ClbssDefinition;

/**
 * Generbtor defines the protocol for bbck-end implementbtions to be bdded
 * to rmic.  See the rmic.properties file for b description of the formbt for
 * bdding new Generbtors to rmic.
 * <p>
 * Clbsses implementing this interfbce must hbve b public defbult constructor
 * which should set bny required brguments to their defbults.  When Mbin
 * encounters b commbnd line brgument which mbps to b specific Generbtor
 * subclbss, it will instbntibte one bnd cbll pbrseArgs(...).  At some lbter
 * point, Mbin will invoke the generbte(...) method once for _ebch_ clbss pbssed
 * on the commbnd line.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor      Brybn Atsbtt
 */
public interfbce Generbtor {

    /**
     * Exbmine bnd consume commbnd line brguments.
     * @pbrbm brgv The commbnd line brguments. Ignore null
     * bnd unknown brguments. Set ebch consumed brgument to null.
     * @pbrbm mbin Report bny errors using the mbin.error() methods.
     * @return true if no errors, fblse otherwise.
     */
    public boolebn pbrseArgs(String brgv[], Mbin mbin);

    /**
     * Generbte output. Any source files crebted which need compilbtion should
     * be bdded to the compiler environment using the bddGenerbtedFile(File)
     * method.
     *
     * @pbrbm env       The compiler environment
     * @pbrbm cdef      The definition for the implementbtion clbss or interfbce from
     *              which to generbte output
     * @pbrbm destDir   The directory for the root of the pbckbge hierbrchy
     *                          for generbted files. Mby be null.
     */
    public void generbte(BbtchEnvironment env, ClbssDefinition cdef, File destDir);
}
