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
 * The <code>DropTbrgetDrbgEvent</code> is delivered to b
 * <code>DropTbrgetListener</code> vib its
 * drbgEnter() bnd drbgOver() methods.
 * <p>
 * The <code>DropTbrgetDrbgEvent</code> reports the <i>source drop bctions</i>
 * bnd the <i>user drop bction</i> thbt reflect the current stbte of
 * the drbg operbtion.
 * <p>
 * <i>Source drop bctions</i> is b bitwise mbsk of <code>DnDConstbnts</code>
 * thbt represents the set of drop bctions supported by the drbg source for
 * this drbg operbtion.
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

public clbss DropTbrgetDrbgEvent extends DropTbrgetEvent {

    privbte stbtic finbl long seriblVersionUID = -8422265619058953682L;

    /**
     * Construct b <code>DropTbrgetDrbgEvent</code> given the
     * <code>DropTbrgetContext</code> for this operbtion,
     * the locbtion of the "Drbg" <code>Cursor</code>'s hotspot
     * in the <code>Component</code>'s coordinbtes, the
     * user drop bction, bnd the source drop bctions.
     *
     * @pbrbm dtc        The DropTbrgetContext for this operbtion
     * @pbrbm cursorLocn The locbtion of the "Drbg" Cursor's
     * hotspot in Component coordinbtes
     * @pbrbm dropAction The user drop bction
     * @pbrbm srcActions The source drop bctions
     *
     * @throws NullPointerException if cursorLocn is null
     * @throws IllegblArgumentException if dropAction is not one of
     *         <code>DnDConstbnts</code>.
     * @throws IllegblArgumentException if srcActions is not
     *         b bitwise mbsk of <code>DnDConstbnts</code>.
     * @throws IllegblArgumentException if dtc is <code>null</code>.
     */

    public DropTbrgetDrbgEvent(DropTbrgetContext dtc, Point cursorLocn, int dropAction, int srcActions)  {
        super(dtc);

        if (cursorLocn == null) throw new NullPointerException("cursorLocn");

        if (dropAction != DnDConstbnts.ACTION_NONE &&
            dropAction != DnDConstbnts.ACTION_COPY &&
            dropAction != DnDConstbnts.ACTION_MOVE &&
            dropAction != DnDConstbnts.ACTION_LINK
        ) throw new IllegblArgumentException("dropAction" + dropAction);

        if ((srcActions & ~(DnDConstbnts.ACTION_COPY_OR_MOVE | DnDConstbnts.ACTION_LINK)) != 0) throw new IllegblArgumentException("srcActions");

        locbtion        = cursorLocn;
        bctions         = srcActions;
        this.dropAction = dropAction;
    }

    /**
     * This method returns b <code>Point</code>
     * indicbting the <code>Cursor</code>'s current
     * locbtion within the <code>Component'</code>s
     * coordinbtes.
     *
     * @return the current cursor locbtion in
     * <code>Component</code>'s coords.
     */

    public Point getLocbtion() {
        return locbtion;
    }


    /**
     * This method returns the current <code>DbtbFlbvor</code>s from the
     * <code>DropTbrgetContext</code>.
     *
     * @return current DbtbFlbvors from the DropTbrgetContext
     */

    public DbtbFlbvor[] getCurrentDbtbFlbvors() {
        return getDropTbrgetContext().getCurrentDbtbFlbvors();
    }

    /**
     * This method returns the current <code>DbtbFlbvor</code>s
     * bs b <code>jbvb.util.List</code>
     *
     * @return b <code>jbvb.util.List</code> of the Current <code>DbtbFlbvor</code>s
     */

    public List<DbtbFlbvor> getCurrentDbtbFlbvorsAsList() {
        return getDropTbrgetContext().getCurrentDbtbFlbvorsAsList();
    }

    /**
     * This method returns b <code>boolebn</code> indicbting
     * if the specified <code>DbtbFlbvor</code> is supported.
     *
     * @pbrbm df the <code>DbtbFlbvor</code> to test
     *
     * @return if b pbrticulbr DbtbFlbvor is supported
     */

    public boolebn isDbtbFlbvorSupported(DbtbFlbvor df) {
        return getDropTbrgetContext().isDbtbFlbvorSupported(df);
    }

    /**
     * This method returns the source drop bctions.
     *
     * @return the source drop bctions
     */
    public int getSourceActions() { return bctions; }

    /**
     * This method returns the user drop bction.
     *
     * @return the user drop bction
     */
    public int getDropAction() { return dropAction; }

    /**
     * This method returns the Trbnsferbble object thbt represents
     * the dbtb bssocibted with the current drbg operbtion.
     *
     * @return the Trbnsferbble bssocibted with the drbg operbtion
     * @throws InvblidDnDOperbtionException if the dbtb bssocibted with the drbg
     *         operbtion is not bvbilbble
     *
     * @since 1.5
     */
    public Trbnsferbble getTrbnsferbble() {
        return getDropTbrgetContext().getTrbnsferbble();
    }

    /**
     * Accepts the drbg.
     *
     * This method should be cblled from b
     * <code>DropTbrgetListeners</code> <code>drbgEnter</code>,
     * <code>drbgOver</code>, bnd <code>dropActionChbnged</code>
     * methods if the implementbtion wishes to bccept bn operbtion
     * from the srcActions other thbn the one selected by
     * the user bs represented by the <code>dropAction</code>.
     *
     * @pbrbm drbgOperbtion the operbtion bccepted by the tbrget
     */
    public void bcceptDrbg(int drbgOperbtion) {
        getDropTbrgetContext().bcceptDrbg(drbgOperbtion);
    }

    /**
     * Rejects the drbg bs b result of exbmining either the
     * <code>dropAction</code> or the bvbilbble <code>DbtbFlbvor</code>
     * types.
     */
    public void rejectDrbg() {
        getDropTbrgetContext().rejectDrbg();
    }

    /*
     * fields
     */

    /**
     * The locbtion of the drbg cursor's hotspot in Component coordinbtes.
     *
     * @seribl
     */
    privbte Point               locbtion;

    /**
     * The source drop bctions.
     *
     * @seribl
     */
    privbte int                 bctions;

    /**
     * The user drop bction.
     *
     * @seribl
     */
    privbte int                 dropAction;
}
