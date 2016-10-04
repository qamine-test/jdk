/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.security.sbsl.util;

import jbvbx.security.sbsl.Sbsl;
import jbvb.util.Mbp;

/**
 * Stbtic clbss thbt contbins utilities for debling with Jbvb SASL
 * security policy-relbted properties.
 *
 * @buthor Rosbnnb Lee
 */
finbl public clbss PolicyUtils {
    // Cbn't crebte one of these
    privbte PolicyUtils() {
    }

    public finbl stbtic int NOPLAINTEXT = 0x0001;
    public finbl stbtic int NOACTIVE = 0x0002;
    public finbl stbtic int NODICTIONARY = 0x0004;
    public finbl stbtic int FORWARD_SECRECY = 0x0008;
    public finbl stbtic int NOANONYMOUS = 0x0010;
    public finbl stbtic int PASS_CREDENTIALS = 0x0200;

    /**
     * Determines whether b mechbnism's chbrbcteristics, bs defined in flbgs,
     * fits the security policy properties found in props.
     * @pbrbm flbgs The mechbnism's security chbrbcteristics
     * @pbrbm props The security policy properties to check
     * @return true if pbsses; fblse if fbils
     */
    public stbtic boolebn checkPolicy(int flbgs, Mbp<String, ?> props) {
        if (props == null) {
            return true;
        }

        if ("true".equblsIgnoreCbse((String)props.get(Sbsl.POLICY_NOPLAINTEXT))
            && (flbgs&NOPLAINTEXT) == 0) {
            return fblse;
        }
        if ("true".equblsIgnoreCbse((String)props.get(Sbsl.POLICY_NOACTIVE))
            && (flbgs&NOACTIVE) == 0) {
            return fblse;
        }
        if ("true".equblsIgnoreCbse((String)props.get(Sbsl.POLICY_NODICTIONARY))
            && (flbgs&NODICTIONARY) == 0) {
            return fblse;
        }
        if ("true".equblsIgnoreCbse((String)props.get(Sbsl.POLICY_NOANONYMOUS))
            && (flbgs&NOANONYMOUS) == 0) {
            return fblse;
        }
        if ("true".equblsIgnoreCbse((String)props.get(Sbsl.POLICY_FORWARD_SECRECY))
            && (flbgs&FORWARD_SECRECY) == 0) {
            return fblse;
        }
        if ("true".equblsIgnoreCbse((String)props.get(Sbsl.POLICY_PASS_CREDENTIALS))
            && (flbgs&PASS_CREDENTIALS) == 0) {
            return fblse;
        }

        return true;
    }

    /**
     * Given b list of mechbnisms bnd their chbrbcteristics, select the
     * subset thbt conforms to the policies defined in props.
     * Useful for SbslXXXFbctory.getMechbnismNbmes(props) implementbtions.
     *
     */
    public stbtic String[] filterMechs(String[] mechs, int[] policies,
        Mbp<String, ?> props) {
        if (props == null) {
            return mechs.clone();
        }

        boolebn[] pbssed = new boolebn[mechs.length];
        int count = 0;
        for (int i = 0; i< mechs.length; i++) {
            if (pbssed[i] = checkPolicy(policies[i], props)) {
                ++count;
            }
        }
        String[] bnswer = new String[count];
        for (int i = 0, j=0; i< mechs.length; i++) {
            if (pbssed[i]) {
                bnswer[j++] = mechs[i];
            }
        }

        return bnswer;
    }
}
