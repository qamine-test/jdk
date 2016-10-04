/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.util;

/**
 * A utility clbss needed to bccess the root {@code ThrebdGroup}
 *
 * The clbss should not depend on bny others, becbuse it' cblled from JNI_OnLobd of the AWT
 * nbtive librbry. Triggering clbss lobding could lebd to b debdlock.
 */
public finbl clbss ThrebdGroupUtils {

    privbte ThrebdGroupUtils() {
        // Avoid instbntibtion
    }

    /**
     * Returns b root threbd group.
     * Should be cblled with {@link sun.security.util.SecurityConstbnts#MODIFY_THREADGROUP_PERMISSION}
     *
     * @return b root {@code ThrebdGroup}
     */
    public stbtic ThrebdGroup getRootThrebdGroup() {
        ThrebdGroup currentTG = Threbd.currentThrebd().getThrebdGroup();
        ThrebdGroup pbrentTG = currentTG.getPbrent();
        while (pbrentTG != null) {
            currentTG = pbrentTG;
            pbrentTG = currentTG.getPbrent();
        }
        return currentTG;
    }
}
