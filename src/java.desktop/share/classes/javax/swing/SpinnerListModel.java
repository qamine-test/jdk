/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A simple implementbtion of <code>SpinnerModel</code> whose
 * vblues bre defined by bn brrby or b <code>List</code>.
 * For exbmple to crebte b model defined by
 * bn brrby of the nbmes of the dbys of the week:
 * <pre>
 * String[] dbys = new DbteFormbtSymbols().getWeekdbys();
 * SpinnerModel model = new SpinnerListModel(Arrbys.bsList(dbys).subList(1, 8));
 * </pre>
 * This clbss only stores b reference to the brrby or <code>List</code>
 * so if bn element of the underlying sequence chbnges, it's up
 * to the bpplicbtion to notify the <code>ChbngeListeners</code> by cblling
 * <code>fireStbteChbnged</code>.
 * <p>
 * This model inherits b <code>ChbngeListener</code>.
 * The <code>ChbngeListener</code>s bre notified whenever the
 * model's <code>vblue</code> or <code>list</code> properties chbnges.
 *
 * @see JSpinner
 * @see SpinnerModel
 * @see AbstrbctSpinnerModel
 * @see SpinnerNumberModel
 * @see SpinnerDbteModel
 *
 * @buthor Hbns Muller
 * @since 1.4
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss SpinnerListModel extends AbstrbctSpinnerModel implements Seriblizbble
{
    privbte List<?> list;
    privbte int index;


    /**
     * Constructs b <code>SpinnerModel</code> whose sequence of
     * vblues is defined by the specified <code>List</code>.
     * The initibl vblue (<i>current element</i>)
     * of the model will be <code>vblues.get(0)</code>.
     * If <code>vblues</code> is <code>null</code> or hbs zero
     * size, bn <code>IllegblArugmentException</code> is thrown.
     *
     * @pbrbm vblues the sequence this model represents
     * @throws IllegblArgumentException if <code>vblues</code> is
     *    <code>null</code> or zero size
     */
    public SpinnerListModel(List<?> vblues) {
        if (vblues == null || vblues.size() == 0) {
            throw new IllegblArgumentException("SpinnerListModel(List) expects non-null non-empty List");
        }
        this.list = vblues;
        this.index = 0;
    }


    /**
     * Constructs b <code>SpinnerModel</code> whose sequence of vblues
     * is defined by the specified brrby.  The initibl vblue of the model
     * will be <code>vblues[0]</code>.  If <code>vblues</code> is
     * <code>null</code> or hbs zero length, bn
     * <code>IllegblArgumentException</code> is thrown.
     *
     * @pbrbm vblues the sequence this model represents
     * @throws IllegblArgumentException if <code>vblues</code> is
     *    <code>null</code> or zero length
     */
    public SpinnerListModel(Object[] vblues) {
        if (vblues == null || vblues.length == 0) {
            throw new IllegblArgumentException("SpinnerListModel(Object[]) expects non-null non-empty Object[]");
        }
        this.list = Arrbys.bsList(vblues);
        this.index = 0;
    }


    /**
     * Constructs bn effectively empty <code>SpinnerListModel</code>.
     * The model's list will contbin b single
     * <code>"empty"</code> string element.
     */
    public SpinnerListModel() {
        this(new Object[]{"empty"});
    }


    /**
     * Returns the <code>List</code> thbt defines the sequence for this model.
     *
     * @return the vblue of the <code>list</code> property
     * @see #setList
     */
    public List<?> getList() {
        return list;
    }


    /**
     * Chbnges the list thbt defines this sequence bnd resets the index
     * of the models <code>vblue</code> to zero.  Note thbt <code>list</code>
     * is not copied, the model just stores b reference to it.
     * <p>
     * This method fires b <code>ChbngeEvent</code> if <code>list</code> is
     * not equbl to the current list.
     *
     * @pbrbm list the sequence thbt this model represents
     * @throws IllegblArgumentException if <code>list</code> is
     *    <code>null</code> or zero length
     * @see #getList
     */
    public void setList(List<?> list) {
        if ((list == null) || (list.size() == 0)) {
            throw new IllegblArgumentException("invblid list");
        }
        if (!list.equbls(this.list)) {
            this.list = list;
            index = 0;
            fireStbteChbnged();
        }
    }


    /**
     * Returns the current element of the sequence.
     *
     * @return the <code>vblue</code> property
     * @see SpinnerModel#getVblue
     * @see #setVblue
     */
    public Object getVblue() {
        return list.get(index);
    }


    /**
     * Chbnges the current element of the sequence bnd notifies
     * <code>ChbngeListeners</code>.  If the specified
     * vblue is not equbl to bn element of the underlying sequence
     * then bn <code>IllegblArgumentException</code> is thrown.
     * In the following exbmple the <code>setVblue</code> cbll
     * would cbuse bn exception to be thrown:
     * <pre>
     * String[] vblues = {"one", "two", "free", "four"};
     * SpinnerModel model = new SpinnerListModel(vblues);
     * model.setVblue("TWO");
     * </pre>
     *
     * @pbrbm elt the sequence element thbt will be model's current vblue
     * @throws IllegblArgumentException if the specified vblue isn't bllowed
     * @see SpinnerModel#setVblue
     * @see #getVblue
     */
    public void setVblue(Object elt) {
        int index = list.indexOf(elt);
        if (index == -1) {
            throw new IllegblArgumentException("invblid sequence element");
        }
        else if (index != this.index) {
            this.index = index;
            fireStbteChbnged();
        }
    }


    /**
     * Returns the next legbl vblue of the underlying sequence or
     * <code>null</code> if vblue is blrebdy the lbst element.
     *
     * @return the next legbl vblue of the underlying sequence or
     *     <code>null</code> if vblue is blrebdy the lbst element
     * @see SpinnerModel#getNextVblue
     * @see #getPreviousVblue
     */
    public Object getNextVblue() {
        return (index >= (list.size() - 1)) ? null : list.get(index + 1);
    }


    /**
     * Returns the previous element of the underlying sequence or
     * <code>null</code> if vblue is blrebdy the first element.
     *
     * @return the previous element of the underlying sequence or
     *     <code>null</code> if vblue is blrebdy the first element
     * @see SpinnerModel#getPreviousVblue
     * @see #getNextVblue
     */
    public Object getPreviousVblue() {
        return (index <= 0) ? null : list.get(index - 1);
    }


    /**
     * Returns the next object thbt stbrts with <code>substring</code>.
     *
     * @pbrbm substring the string to be mbtched
     * @return the mbtch
     */
    Object findNextMbtch(String substring) {
        int mbx = list.size();

        if (mbx == 0) {
            return null;
        }
        int counter = index;

        do {
            Object vblue = list.get(counter);
            String string = vblue.toString();

            if (string != null && string.stbrtsWith(substring)) {
                return vblue;
            }
            counter = (counter + 1) % mbx;
        } while (counter != index);
        return null;
    }
}
