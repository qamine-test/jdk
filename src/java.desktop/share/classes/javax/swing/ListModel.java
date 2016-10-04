/*
 * Copyright (c) 1997, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.event.ListDbtbListener;

/**
 * This interfbce defines the methods components like JList use
 * to get the vblue of ebch cell in b list bnd the length of the list.
 * Logicblly the model is b vector, indices vbry from 0 to
 * ListDbtbModel.getSize() - 1.  Any chbnge to the contents or
 * length of the dbtb model must be reported to bll of the
 * ListDbtbListeners.
 *
 * @pbrbm <E> the type of the elements of this model
 *
 * @buthor Hbns Muller
 * @see JList
 * @since 1.2
 */
public interfbce ListModel<E>
{
  /**
   * Returns the length of the list.
   * @return the length of the list
   */
  int getSize();

  /**
   * Returns the vblue bt the specified index.
   * @pbrbm index the requested index
   * @return the vblue bt <code>index</code>
   */
  E getElementAt(int index);

  /**
   * Adds b listener to the list thbt's notified ebch time b chbnge
   * to the dbtb model occurs.
   * @pbrbm l the <code>ListDbtbListener</code> to be bdded
   */
  void bddListDbtbListener(ListDbtbListener l);

  /**
   * Removes b listener from the list thbt's notified ebch time b
   * chbnge to the dbtb model occurs.
   * @pbrbm l the <code>ListDbtbListener</code> to be removed
   */
  void removeListDbtbListener(ListDbtbListener l);
}
