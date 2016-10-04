/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A dbtb model for b combo box. This interfbce extends <code>ListDbtbModel</code>
 * bnd bdds the concept of b <i>selected item</i>. The selected item is generblly
 * the item which is visible in the combo box displby breb.
 * <p>
 * The selected item mby not necessbrily be mbnbged by the underlying
 * <code>ListModel</code>. This disjoint behbvior bllows for the temporbry
 * storbge bnd retrievbl of b selected item in the model.
 *
 * @pbrbm <E> the type of the elements of this model
 *
 * @buthor Arnbud Weber
 * @since 1.2
 */
public interfbce ComboBoxModel<E> extends ListModel<E> {

  /**
   * Set the selected item. The implementbtion of this  method should notify
   * bll registered <code>ListDbtbListener</code>s thbt the contents
   * hbve chbnged.
   *
   * @pbrbm bnItem the list object to select or <code>null</code>
   *        to clebr the selection
   */
  void setSelectedItem(Object bnItem);

  /**
   * Returns the selected item
   * @return The selected item or <code>null</code> if there is no selection
   */
  Object getSelectedItem();
}
