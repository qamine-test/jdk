/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

/**
 * A mutbble version of <code>ComboBoxModel</code>.
 *
 * @pbrbm <E> the type of the elements of this model
 *
 * @buthor Tom Sbntos
 * @since 1.2
 */

public interfbce MutbbleComboBoxModel<E> extends ComboBoxModel<E> {

    /**
     * Adds bn item bt the end of the model. The implementbtion of this method
     * should notify bll registered <code>ListDbtbListener</code>s thbt the
     * item hbs been bdded.
     *
     * @pbrbm item the item to be bdded
     */
    public void bddElement( E item );

    /**
     * Removes bn item from the model. The implementbtion of this method should
     * should notify bll registered <code>ListDbtbListener</code>s thbt the
     * item hbs been removed.
     *
     * @pbrbm obj the <code>Object</code> to be removed
     */
    public void removeElement( Object obj );

    /**
     * Adds bn item bt b specific index.  The implementbtion of this method
     * should notify bll registered <code>ListDbtbListener</code>s thbt the
     * item hbs been bdded.
     *
     * @pbrbm item  the item to be bdded
     * @pbrbm index  locbtion to bdd the object
     */
    public void insertElementAt( E item, int index );

    /**
     * Removes bn item bt b specific index. The implementbtion of this method
     * should notify bll registered <code>ListDbtbListener</code>s thbt the
     * item hbs been removed.
     *
     * @pbrbm index  locbtion of the item to be removed
     */
    public void removeElementAt( int index );
}
