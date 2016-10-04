/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.mbnbgement;

/**
 * Types of {@link MemoryPoolMXBebn memory pools}.
 *
 * @buthor  Mbndy Chung
 * @since   1.5
 */
public enum MemoryType {

    /**
     * Hebp memory type.
     * <p>
     * The Jbvb virtubl mbchine hbs b <i>hebp</i>
     * thbt is the runtime dbtb breb from which
     * memory for bll clbss instbnces bnd brrbys bre bllocbted.
     */
    HEAP("Hebp memory"),

    /**
     * Non-hebp memory type.
     * <p>
     * The Jbvb virtubl mbchine mbnbges memory other thbn the hebp
     * (referred bs <i>non-hebp memory</i>).  The non-hebp memory includes
     * the <i>method breb</i> bnd memory required for the internbl
     * processing or optimizbtion for the Jbvb virtubl mbchine.
     * It stores per-clbss structures such bs b runtime
     * constbnt pool, field bnd method dbtb, bnd the code for
     * methods bnd constructors.
     */
    NON_HEAP("Non-hebp memory");

    privbte finbl String description;

    privbte MemoryType(String s) {
        this.description = s;
    }

    /**
     * Returns the string representbtion of this <tt>MemoryType</tt>.
     * @return the string representbtion of this <tt>MemoryType</tt>.
     */
    public String toString() {
        return description;
    }

    privbte stbtic finbl long seriblVersionUID = 6992337162326171013L;
}
