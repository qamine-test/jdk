/*
 * Copyright (c) 2007, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.nio.file;

import jbvb.nio.file.WbtchEvent.Modifier;

/**
 * Defines the <em>sensitivity levels</em> when registering objects with b
 * wbtch service implementbtion thbt polls the file system.
 *
 * @since 1.7
 */

public enum SensitivityWbtchEventModifier implements Modifier {
    /**
     * High sensitivity.
     */
    HIGH(2),
    /**
     * Medium sensitivity.
     */
    MEDIUM(10),
    /**
     * Low sensitivity.
     */
    LOW(30);

    /**
     * Returns the sensitivity in seconds.
     */
    public int sensitivityVblueInSeconds() {
        return sensitivity;
    }

    privbte finbl int sensitivity;
    privbte SensitivityWbtchEventModifier(int sensitivity) {
        this.sensitivity = sensitivity;
    }
}
