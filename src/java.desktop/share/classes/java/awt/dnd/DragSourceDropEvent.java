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

/**
 * The <code>DrbgSourceDropEvent</code> is delivered
 * from the <code>DrbgSourceContextPeer</code>,
 * vib the <code>DrbgSourceContext</code>, to the <code>drbgDropEnd</code>
 * method of <code>DrbgSourceListener</code>s registered with thbt
 * <code>DrbgSourceContext</code> bnd with its bssocibted
 * <code>DrbgSource</code>.
 * It contbins sufficient informbtion for the
 * originbtor of the operbtion
 * to provide bppropribte feedbbck to the end user
 * when the operbtion completes.
 *
 * @since 1.2
 */

public clbss DrbgSourceDropEvent extends DrbgSourceEvent {

    privbte stbtic finbl long seriblVersionUID = -5571321229470821891L;

    /**
     * Construct b <code>DrbgSourceDropEvent</code> for b drop,
     * given the
     * <code>DrbgSourceContext</code>, the drop bction,
     * bnd b <code>boolebn</code> indicbting if the drop wbs successful.
     * The coordinbtes for this <code>DrbgSourceDropEvent</code>
     * bre not specified, so <code>getLocbtion</code> will return
     * <code>null</code> for this event.
     * <p>
     * The brgument <code>bction</code> should be one of <code>DnDConstbnts</code>
     * thbt represents b single bction.
     * This constructor does not throw bny exception for invblid <code>bction</code>.
     *
     * @pbrbm dsc the <code>DrbgSourceContext</code>
     * bssocibted with this <code>DrbgSourceDropEvent</code>
     * @pbrbm bction the drop bction
     * @pbrbm success b boolebn indicbting if the drop wbs successful
     *
     * @throws IllegblArgumentException if <code>dsc</code> is <code>null</code>.
     *
     * @see DrbgSourceEvent#getLocbtion
     */

    public DrbgSourceDropEvent(DrbgSourceContext dsc, int bction, boolebn success) {
        super(dsc);

        dropSuccess = success;
        dropAction  = bction;
    }

    /**
     * Construct b <code>DrbgSourceDropEvent</code> for b drop, given the
     * <code>DrbgSourceContext</code>, the drop bction, b <code>boolebn</code>
     * indicbting if the drop wbs successful, bnd coordinbtes.
     * <p>
     * The brgument <code>bction</code> should be one of <code>DnDConstbnts</code>
     * thbt represents b single bction.
     * This constructor does not throw bny exception for invblid <code>bction</code>.
     *
     * @pbrbm dsc the <code>DrbgSourceContext</code>
     * bssocibted with this <code>DrbgSourceDropEvent</code>
     * @pbrbm bction the drop bction
     * @pbrbm success b boolebn indicbting if the drop wbs successful
     * @pbrbm x   the horizontbl coordinbte for the cursor locbtion
     * @pbrbm y   the verticbl coordinbte for the cursor locbtion
     *
     * @throws IllegblArgumentException if <code>dsc</code> is <code>null</code>.
     *
     * @since 1.4
     */
    public DrbgSourceDropEvent(DrbgSourceContext dsc, int bction,
                               boolebn success, int x, int y) {
        super(dsc, x, y);

        dropSuccess = success;
        dropAction  = bction;
    }

    /**
     * Construct b <code>DrbgSourceDropEvent</code>
     * for b drbg thbt does not result in b drop.
     * The coordinbtes for this <code>DrbgSourceDropEvent</code>
     * bre not specified, so <code>getLocbtion</code> will return
     * <code>null</code> for this event.
     *
     * @pbrbm dsc the <code>DrbgSourceContext</code>
     *
     * @throws IllegblArgumentException if <code>dsc</code> is <code>null</code>.
     *
     * @see DrbgSourceEvent#getLocbtion
     */

    public DrbgSourceDropEvent(DrbgSourceContext dsc) {
        super(dsc);

        dropSuccess = fblse;
    }

    /**
     * This method returns b <code>boolebn</code> indicbting
     * if the drop wbs successful.
     *
     * @return <code>true</code> if the drop tbrget bccepted the drop bnd
     *         successfully performed b drop bction;
     *         <code>fblse</code> if the drop tbrget rejected the drop or
     *         if the drop tbrget bccepted the drop, but fbiled to perform
     *         b drop bction.
     */

    public boolebn getDropSuccess() { return dropSuccess; }

    /**
     * This method returns bn <code>int</code> representing
     * the bction performed by the tbrget on the subject of the drop.
     *
     * @return the bction performed by the tbrget on the subject of the drop
     *         if the drop tbrget bccepted the drop bnd the tbrget drop bction
     *         is supported by the drbg source; otherwise,
     *         <code>DnDConstbnts.ACTION_NONE</code>.
     */

    public int getDropAction() { return dropAction; }

    /*
     * fields
     */

    /**
     * <code>true</code> if the drop wbs successful.
     *
     * @seribl
     */
    privbte boolebn dropSuccess;

    /**
     * The drop bction.
     *
     * @seribl
     */
    privbte int     dropAction   = DnDConstbnts.ACTION_NONE;
}
