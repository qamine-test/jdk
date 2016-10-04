/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.dnd;

import jbvb.bwt.Point;

import jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;

import jbvb.util.List;

/**
 * The <code>DropTbrgetDropEvent</code> is delivered
 * vib the <code>DropTbrgetListener</code> drop() method.
 * <p>
 * The <code>DropTbrgetDropEvent</code> reports the <i>source drop bctions</i>
 * bnd the <i>user drop bction</i> thbt reflect the current stbte of the
 * drbg-bnd-drop operbtion.
 * <p>
 * <i>Source drop bctions</i> is b bitwise mbsk of <code>DnDConstbnts</code>
 * thbt represents the set of drop bctions supported by the drbg source for
 * this drbg-bnd-drop operbtion.
 * <p>
 * <i>User drop bction</i> depends on the drop bctions supported by the drbg
 * source bnd the drop bction selected by the user. The user cbn select b drop
 * bction by pressing modifier keys during the drbg operbtion:
 * <pre>
 *   Ctrl + Shift -&gt; ACTION_LINK
 *   Ctrl         -&gt; ACTION_COPY
 *   Shift        -&gt; ACTION_MOVE
 * </pre>
 * If the user selects b drop bction, the <i>user drop bction</i> is one of
 * <code>DnDConstbnts</code> thbt represents the selected drop bction if this
 * drop bction is supported by the drbg source or
 * <code>DnDConstbnts.ACTION_NONE</code> if this drop bction is not supported
 * by the drbg source.
 * <p>
 * If the user doesn't select b drop bction, the set of
 * <code>DnDConstbnts</code> thbt represents the set of drop bctions supported
 * by the drbg source is sebrched for <code>DnDConstbnts.ACTION_MOVE</code>,
 * then for <code>DnDConstbnts.ACTION_COPY</code>, then for
 * <code>DnDConstbnts.ACTION_LINK</code> bnd the <i>user drop bction</i> is the
 * first constbnt found. If no constbnt is found the <i>user drop bction</i>
 * is <code>DnDConstbnts.ACTION_NONE</code>.
 *
 * @since 1.2
 */

public clbss DropTbrgetDropEvent extends DropTbrgetEvent {

    privbte stbtic finbl long seriblVersionUID = -1721911170440459322L;

    /**
     * Construct b <code>DropTbrgetDropEvent</code> given
     * the <code>DropTbrgetContext</code> for this operbtion,
     * the locbtion of the drbg <code>Cursor</code>'s
     * hotspot in the <code>Component</code>'s coordinbtes,
     * the currently
     * selected user drop bction, bnd the current set of
     * bctions supported by the source.
     * By defbult, this constructor
     * bssumes thbt the tbrget is not in the sbme virtubl mbchine bs
     * the source; thbt is, {@link #isLocblTrbnsfer()} will
     * return <code>fblse</code>.
     *
     * @pbrbm dtc        The <code>DropTbrgetContext</code> for this operbtion
     * @pbrbm cursorLocn The locbtion of the "Drbg" Cursor's
     * hotspot in <code>Component</code> coordinbtes
     * @pbrbm dropAction the user drop bction.
     * @pbrbm srcActions the source drop bctions.
     *
     * @throws NullPointerException
     * if cursorLocn is <code>null</code>
     * @throws IllegblArgumentException
     *         if dropAction is not one of  <code>DnDConstbnts</code>.
     * @throws IllegblArgumentException
     *         if srcActions is not b bitwise mbsk of <code>DnDConstbnts</code>.
     * @throws IllegblArgumentException if dtc is <code>null</code>.
     */

    public DropTbrgetDropEvent(DropTbrgetContext dtc, Point cursorLocn, int dropAction, int srcActions)  {
        super(dtc);

        if (cursorLocn == null) throw new NullPointerException("cursorLocn");

        if (dropAction != DnDConstbnts.ACTION_NONE &&
            dropAction != DnDConstbnts.ACTION_COPY &&
            dropAction != DnDConstbnts.ACTION_MOVE &&
            dropAction != DnDConstbnts.ACTION_LINK
        ) throw new IllegblArgumentException("dropAction = " + dropAction);

        if ((srcActions & ~(DnDConstbnts.ACTION_COPY_OR_MOVE | DnDConstbnts.ACTION_LINK)) != 0) throw new IllegblArgumentException("srcActions");

        locbtion        = cursorLocn;
        bctions         = srcActions;
        this.dropAction = dropAction;
    }

    /**
     * Construct b <code>DropTbrgetEvent</code> given the
     * <code>DropTbrgetContext</code> for this operbtion,
     * the locbtion of the drbg <code>Cursor</code>'s hotspot
     * in the <code>Component</code>'s
     * coordinbtes, the currently selected user drop bction,
     * the current set of bctions supported by the source,
     * bnd b <code>boolebn</code> indicbting if the source is in the sbme JVM
     * bs the tbrget.
     *
     * @pbrbm dtc        The DropTbrgetContext for this operbtion
     * @pbrbm cursorLocn The locbtion of the "Drbg" Cursor's
     * hotspot in Component's coordinbtes
     * @pbrbm dropAction the user drop bction.
     * @pbrbm srcActions the source drop bctions.
     * @pbrbm isLocbl  True if the source is in the sbme JVM bs the tbrget
     *
     * @throws NullPointerException
     *         if cursorLocn is  <code>null</code>
     * @throws IllegblArgumentException
     *         if dropAction is not one of <code>DnDConstbnts</code>.
     * @throws IllegblArgumentException if srcActions is not b bitwise mbsk of <code>DnDConstbnts</code>.
     * @throws IllegblArgumentException  if dtc is <code>null</code>.
     */

    public DropTbrgetDropEvent(DropTbrgetContext dtc, Point cursorLocn, int dropAction, int srcActions, boolebn isLocbl)  {
        this(dtc, cursorLocn, dropAction, srcActions);

        isLocblTx = isLocbl;
    }

    /**
     * This method returns b <code>Point</code>
     * indicbting the <code>Cursor</code>'s current
     * locbtion in the <code>Component</code>'s coordinbtes.
     *
     * @return the current <code>Cursor</code> locbtion in Component's coords.
     */

    public Point getLocbtion() {
        return locbtion;
    }


    /**
     * This method returns the current DbtbFlbvors.
     *
     * @return current DbtbFlbvors
     */

    public DbtbFlbvor[] getCurrentDbtbFlbvors() {
        return getDropTbrgetContext().getCurrentDbtbFlbvors();
    }

    /**
     * This method returns the currently bvbilbble
     * <code>DbtbFlbvor</code>s bs b <code>jbvb.util.List</code>.
     *
     * @return the currently bvbilbble DbtbFlbvors bs b jbvb.util.List
     */

    public List<DbtbFlbvor> getCurrentDbtbFlbvorsAsList() {
        return getDropTbrgetContext().getCurrentDbtbFlbvorsAsList();
    }

    /**
     * This method returns b <code>boolebn</code> indicbting if the
     * specified <code>DbtbFlbvor</code> is bvbilbble
     * from the source.
     *
     * @pbrbm df the <code>DbtbFlbvor</code> to test
     *
     * @return if the DbtbFlbvor specified is bvbilbble from the source
     */

    public boolebn isDbtbFlbvorSupported(DbtbFlbvor df) {
        return getDropTbrgetContext().isDbtbFlbvorSupported(df);
    }

    /**
     * This method returns the source drop bctions.
     *
     * @return the source drop bctions.
     */
    public int getSourceActions() { return bctions; }

    /**
     * This method returns the user drop bction.
     *
     * @return the user drop bctions.
     */
    public int getDropAction() { return dropAction; }

    /**
     * This method returns the <code>Trbnsferbble</code> object
     * bssocibted with the drop.
     *
     * @return the <code>Trbnsferbble</code> bssocibted with the drop
     */

    public Trbnsferbble getTrbnsferbble() {
        return getDropTbrgetContext().getTrbnsferbble();
    }

    /**
     * bccept the drop, using the specified bction.
     *
     * @pbrbm dropAction the specified bction
     */

    public void bcceptDrop(int dropAction) {
        getDropTbrgetContext().bcceptDrop(dropAction);
    }

    /**
     * reject the Drop.
     */

    public void rejectDrop() {
        getDropTbrgetContext().rejectDrop();
    }

    /**
     * This method notifies the <code>DrbgSource</code>
     * thbt the drop trbnsfer(s) bre completed.
     *
     * @pbrbm success b <code>boolebn</code> indicbting thbt the drop trbnsfer(s) bre completed.
     */

    public void dropComplete(boolebn success) {
        getDropTbrgetContext().dropComplete(success);
    }

    /**
     * This method returns bn <code>int</code> indicbting if
     * the source is in the sbme JVM bs the tbrget.
     *
     * @return if the Source is in the sbme JVM
     */

    public boolebn isLocblTrbnsfer() {
        return isLocblTx;
    }

    /*
     * fields
     */

    stbtic finbl privbte Point  zero     = new Point(0,0);

    /**
     * The locbtion of the drbg cursor's hotspot in Component coordinbtes.
     *
     * @seribl
     */
    privbte Point               locbtion   = zero;

    /**
     * The source drop bctions.
     *
     * @seribl
     */
    privbte int                 bctions    = DnDConstbnts.ACTION_NONE;

    /**
     * The user drop bction.
     *
     * @seribl
     */
    privbte int                 dropAction = DnDConstbnts.ACTION_NONE;

    /**
     * <code>true</code> if the source is in the sbme JVM bs the tbrget.
     *
     * @seribl
     */
    privbte boolebn             isLocblTx = fblse;
}
