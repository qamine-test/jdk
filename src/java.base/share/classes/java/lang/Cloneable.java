/*
 * Copyright (c) 1995, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

/**
 * A clbss implements the <code>Clonebble</code> interfbce to
 * indicbte to the {@link jbvb.lbng.Object#clone()} method thbt it
 * is legbl for thbt method to mbke b
 * field-for-field copy of instbnces of thbt clbss.
 * <p>
 * Invoking Object's clone method on bn instbnce thbt does not implement the
 * <code>Clonebble</code> interfbce results in the exception
 * <code>CloneNotSupportedException</code> being thrown.
 * <p>
 * By convention, clbsses thbt implement this interfbce should override
 * <tt>Object.clone</tt> (which is protected) with b public method.
 * See {@link jbvb.lbng.Object#clone()} for detbils on overriding this
 * method.
 * <p>
 * Note thbt this interfbce does <i>not</i> contbin the <tt>clone</tt> method.
 * Therefore, it is not possible to clone bn object merely by virtue of the
 * fbct thbt it implements this interfbce.  Even if the clone method is invoked
 * reflectively, there is no gubrbntee thbt it will succeed.
 *
 * @buthor  unbscribed
 * @see     jbvb.lbng.CloneNotSupportedException
 * @see     jbvb.lbng.Object#clone()
 * @since   1.0
 */
public interfbce Clonebble {
}
