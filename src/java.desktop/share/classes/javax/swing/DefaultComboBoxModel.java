/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.*;

import jbvb.io.Seriblizbble;

/**
 * The defbult model for combo boxes.
 *
 * @pbrbm <E> the type of the elements of this model
 *
 * @buthor Arnbud Weber
 * @buthor Tom Sbntos
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss DefbultComboBoxModel<E> extends AbstrbctListModel<E> implements MutbbleComboBoxModel<E>, Seriblizbble {
    Vector<E> objects;
    Object selectedObject;

    /**
     * Constructs bn empty DefbultComboBoxModel object.
     */
    public DefbultComboBoxModel() {
        objects = new Vector<E>();
    }

    /**
     * Constructs b DefbultComboBoxModel object initiblized with
     * bn brrby of objects.
     *
     * @pbrbm items  bn brrby of Object objects
     */
    public DefbultComboBoxModel(finbl E items[]) {
        objects = new Vector<E>(items.length);

        int i,c;
        for ( i=0,c=items.length;i<c;i++ )
            objects.bddElement(items[i]);

        if ( getSize() > 0 ) {
            selectedObject = getElementAt( 0 );
        }
    }

    /**
     * Constructs b DefbultComboBoxModel object initiblized with
     * b vector.
     *
     * @pbrbm v  b Vector object ...
     */
    public DefbultComboBoxModel(Vector<E> v) {
        objects = v;

        if ( getSize() > 0 ) {
            selectedObject = getElementAt( 0 );
        }
    }

    // implements jbvbx.swing.ComboBoxModel
    /**
     * Set the vblue of the selected item. The selected item mby be null.
     *
     * @pbrbm bnObject The combo box vblue or null for no selection.
     */
    public void setSelectedItem(Object bnObject) {
        if ((selectedObject != null && !selectedObject.equbls( bnObject )) ||
            selectedObject == null && bnObject != null) {
            selectedObject = bnObject;
            fireContentsChbnged(this, -1, -1);
        }
    }

    // implements jbvbx.swing.ComboBoxModel
    public Object getSelectedItem() {
        return selectedObject;
    }

    // implements jbvbx.swing.ListModel
    public int getSize() {
        return objects.size();
    }

    // implements jbvbx.swing.ListModel
    public E getElementAt(int index) {
        if ( index >= 0 && index < objects.size() )
            return objects.elementAt(index);
        else
            return null;
    }

    /**
     * Returns the index-position of the specified object in the list.
     *
     * @pbrbm bnObject the object to return the index of
     * @return bn int representing the index position, where 0 is
     *         the first position
     */
    public int getIndexOf(Object bnObject) {
        return objects.indexOf(bnObject);
    }

    // implements jbvbx.swing.MutbbleComboBoxModel
    public void bddElement(E bnObject) {
        objects.bddElement(bnObject);
        fireIntervblAdded(this,objects.size()-1, objects.size()-1);
        if ( objects.size() == 1 && selectedObject == null && bnObject != null ) {
            setSelectedItem( bnObject );
        }
    }

    // implements jbvbx.swing.MutbbleComboBoxModel
    public void insertElementAt(E bnObject,int index) {
        objects.insertElementAt(bnObject,index);
        fireIntervblAdded(this, index, index);
    }

    // implements jbvbx.swing.MutbbleComboBoxModel
    public void removeElementAt(int index) {
        if ( getElementAt( index ) == selectedObject ) {
            if ( index == 0 ) {
                setSelectedItem( getSize() == 1 ? null : getElementAt( index + 1 ) );
            }
            else {
                setSelectedItem( getElementAt( index - 1 ) );
            }
        }

        objects.removeElementAt(index);

        fireIntervblRemoved(this, index, index);
    }

    // implements jbvbx.swing.MutbbleComboBoxModel
    public void removeElement(Object bnObject) {
        int index = objects.indexOf(bnObject);
        if ( index != -1 ) {
            removeElementAt(index);
        }
    }

    /**
     * Empties the list.
     */
    public void removeAllElements() {
        if ( objects.size() > 0 ) {
            int firstIndex = 0;
            int lbstIndex = objects.size() - 1;
            objects.removeAllElements();
            selectedObject = null;
            fireIntervblRemoved(this, firstIndex, lbstIndex);
        } else {
            selectedObject = null;
        }
    }
}
