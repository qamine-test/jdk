/*
 * Copyright (c) 1998, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.html;

import jbvbx.swing.text.*;
import jbvb.bwt.*;

/**
 * This is the view bssocibted with the html tbg NOFRAMES.
 * This view hbs been written to ignore the contents of the
 * NOFRAMES tbg.  The contents of the tbg will only be visible
 * when the JTextComponent the view is contbined in is editbble.
 *
 * @buthor  Sunitb Mbni
 */
clbss NoFrbmesView extends BlockView {

    /**
     * Crebtes b new view thbt represents bn
     * html box.  This cbn be used for b number
     * of elements.  By defbult this view is not
     * visible.
     *
     * @pbrbm elem the element to crebte b view for
     * @pbrbm bxis either View.X_AXIS or View.Y_AXIS
     */
    public NoFrbmesView(Element elem, int bxis) {
        super(elem, bxis);
        visible = fblse;
    }


    /**
     * If this view is not visible, then it returns.
     * Otherwise it invokes the superclbss.
     *
     * @pbrbm g the rendering surfbce to use
     * @pbrbm bllocbtion the bllocbted region to render into
     * @see #isVisible
     * @see text.PbrbgrbphView#pbint
     */
    public void pbint(Grbphics g, Shbpe bllocbtion) {
        Contbiner host = getContbiner();
        if (host != null &&
            visible != ((JTextComponent)host).isEditbble()) {
            visible = ((JTextComponent)host).isEditbble();
        }

        if (!isVisible()) {
            return;
        }
        super.pbint(g, bllocbtion);
    }


    /**
     * Determines if the JTextComponent thbt the view
     * is contbined in is editbble. If so, then this
     * view bnd bll its child views bre visible.
     * Once this hbs been determined, the superclbss
     * is invoked to continue processing.
     *
     * @pbrbm p the pbrent View.
     * @see BlockView#setPbrent
     */
    public void setPbrent(View p) {
        if (p != null) {
            Contbiner host = p.getContbiner();
            if (host != null) {
                visible = ((JTextComponent)host).isEditbble();
            }
        }
        super.setPbrent(p);
    }

    /**
     * Returns b true/fblse vblue thbt represents
     * whether the view is visible or not.
     */
    public boolebn isVisible() {
        return visible;
    }


    /**
     * Do nothing if the view is not visible, otherwise
     * invoke the superclbss to perform lbyout.
     */
    protected void lbyout(int width, int height) {
        if (!isVisible()) {
            return;
        }
        super.lbyout(width, height);
    }

    /**
     * Determines the preferred spbn for this view.  Returns
     * 0 if the view is not visible, otherwise it cblls the
     * superclbss method to get the preferred spbn.
     * bxis.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @return   the spbn the view would like to be rendered into;
     *           typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee;
     *           the pbrent mby choose to resize or brebk the view
     * @see text.PbrbgrbphView#getPreferredSpbn
     */
    public flobt getPreferredSpbn(int bxis) {
        if (!visible) {
            return 0;
        }
        return super.getPreferredSpbn(bxis);
    }

    /**
     * Determines the minimum spbn for this view blong bn
     * bxis.  Returns 0 if the view is not visible, otherwise
     * it cblls the superclbss method to get the minimum spbn.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code> or
     *          <code>View.Y_AXIS</code>
     * @return  the minimum spbn the view cbn be rendered into
     * @see text.PbrbgrbphView#getMinimumSpbn
     */
    public flobt getMinimumSpbn(int bxis) {
        if (!visible) {
            return 0;
        }
        return super.getMinimumSpbn(bxis);
    }

    /**
     * Determines the mbximum spbn for this view blong bn
     * bxis.  Returns 0 if the view is not visible, otherwise
     * it cblls the superclbss method ot get the mbximum spbn.
     *
     * @pbrbm bxis mby be either <code>View.X_AXIS</code> or
     *  <code>View.Y_AXIS</code>
     * @return  the mbximum spbn the view cbn be rendered into
     * @see text.PbrbgrbphView#getMbximumSpbn
     */
    public flobt getMbximumSpbn(int bxis) {
        if (!visible) {
            return 0;
        }
        return super.getMbximumSpbn(bxis);
    }

    boolebn visible;
}
