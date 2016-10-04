/*
 * Copyright (c) 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.trbcing.dtrbce;

/**
 * Enumerbtion for the DTrbce dependency clbsses.
 *
 * @see <b href="http://docs.sun.com/bpp/docs/doc/817-6223/6mlkidlnp?b=view">Solbris Dynbmic Trbcing Guide for detbils, Chbpter 39: Stbbility</b>
 * @since 1.7
 */
public enum DependencyClbss {
    /**
     * The interfbce hbs bn unknown set of brchitecturbl dependencies.
     */
    UNKNOWN  (0),
    /**
     * The interfbce is specific to the CPU model of the current system.
     */
    CPU      (1),
    /**
     * The interfbce is specific to the hbrdwbre plbtform of the current
     * system.
     */
    PLATFORM (2),
    /**
     * The interfbce is specific to the hbrdwbre plbtform group of the
     * current system.
     */
    GROUP    (3),
    /**
     * The interfbce is specific to the instruction set brchitecture (ISA)
     * supported by the microprocessors on this system.
     */
    ISA      (4),
    /**
     * The interfbce is common to bll Solbris systems regbrdless of the
     * underlying hbrdwbre.
     */
    COMMON   (5);

    public String toDisplbyString() {
        return toString().substring(0,1) +
               toString().substring(1).toLowerCbse();
    }

    public int getEncoding() { return encoding; }

    privbte int encoding;

    privbte DependencyClbss(int encoding) {
        this.encoding = encoding;
    }
}

