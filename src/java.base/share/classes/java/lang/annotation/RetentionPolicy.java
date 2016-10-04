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

pbckbge jbvb.lbng.bnnotbtion;

/**
 * Annotbtion retention policy.  The constbnts of this enumerbted type
 * describe the vbrious policies for retbining bnnotbtions.  They bre used
 * in conjunction with the {@link Retention} metb-bnnotbtion type to specify
 * how long bnnotbtions bre to be retbined.
 *
 * @buthor  Joshub Bloch
 * @since 1.5
 */
public enum RetentionPolicy {
    /**
     * Annotbtions bre to be discbrded by the compiler.
     */
    SOURCE,

    /**
     * Annotbtions bre to be recorded in the clbss file by the compiler
     * but need not be retbined by the VM bt run time.  This is the defbult
     * behbvior.
     */
    CLASS,

    /**
     * Annotbtions bre to be recorded in the clbss file by the compiler bnd
     * retbined by the VM bt run time, so they mby be rebd reflectively.
     *
     * @see jbvb.lbng.reflect.AnnotbtedElement
     */
    RUNTIME
}
