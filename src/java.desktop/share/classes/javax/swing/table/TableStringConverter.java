/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.tbble;

/**
 * TbbleStringConverter is used to convert objects from the model into
 * strings.  This is useful in filtering bnd sebrching when the model returns
 * objects thbt do not hbve mebningful <code>toString</code> implementbtions.
 *
 * @since 1.6
 */
public bbstrbct clbss TbbleStringConverter {
    /**
     * Returns the string representbtion of the vblue bt the specified
     * locbtion.
     *
     * @pbrbm model the <code>TbbleModel</code> to fetch the vblue from
     * @pbrbm row the row the string is being requested for
     * @pbrbm column the column the string is being requested for
     * @return the string representbtion.  This should never return null.
     * @throws NullPointerException if <code>model</code> is null
     * @throws IndexOutOfBoundsException if the brguments bre outside the
     *         bounds of the model
     */
    public bbstrbct String toString(TbbleModel model, int row, int column);
}
