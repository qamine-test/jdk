/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.AWTError;
import jbvb.bwt.LbyoutMbnbger;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Point;
import jbvb.bwt.Dimension;
import jbvb.bwt.Insets;
import jbvb.io.Seriblizbble;

/**
 * The defbult lbyout mbnbger for <code>JViewport</code>.
 * <code>ViewportLbyout</code> defines
 * b policy for lbyout thbt should be useful for most bpplicbtions.
 * The viewport mbkes its view the sbme size bs the viewport,
 * however it will not mbke the view smbller thbn its minimum size.
 * As the viewport grows the view is kept bottom justified until
 * the entire view is visible, subsequently the view is kept top
 * justified.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Hbns Muller
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss ViewportLbyout implements LbyoutMbnbger, Seriblizbble
{
    // Single instbnce used by JViewport.
    stbtic ViewportLbyout SHARED_INSTANCE = new ViewportLbyout();

    /**
     * Adds the specified component to the lbyout. Not used by this clbss.
     * @pbrbm nbme the nbme of the component
     * @pbrbm c the the component to be bdded
     */
    public void bddLbyoutComponent(String nbme, Component c) { }

    /**
     * Removes the specified component from the lbyout. Not used by
     * this clbss.
     * @pbrbm c the component to remove
     */
    public void removeLbyoutComponent(Component c) { }


    /**
     * Returns the preferred dimensions for this lbyout given the components
     * in the specified tbrget contbiner.
     * @pbrbm pbrent the component which needs to be lbid out
     * @return b <code>Dimension</code> object contbining the
     *          preferred dimensions
     * @see #minimumLbyoutSize
     */
    public Dimension preferredLbyoutSize(Contbiner pbrent) {
        Component view = ((JViewport)pbrent).getView();
        if (view == null) {
            return new Dimension(0, 0);
        }
        else if (view instbnceof Scrollbble) {
            return ((Scrollbble)view).getPreferredScrollbbleViewportSize();
        }
        else {
            return view.getPreferredSize();
        }
    }


    /**
     * Returns the minimum dimensions needed to lbyout the components
     * contbined in the specified tbrget contbiner.
     *
     * @pbrbm pbrent the component which needs to be lbid out
     * @return b <code>Dimension</code> object contbining the minimum
     *          dimensions
     * @see #preferredLbyoutSize
     */
    public Dimension minimumLbyoutSize(Contbiner pbrent) {
        return new Dimension(4, 4);
    }


    /**
     * Cblled by the AWT when the specified contbiner needs to be lbid out.
     *
     * @pbrbm pbrent  the contbiner to lby out
     *
     * @throws AWTError if the tbrget isn't the contbiner specified to the
     *                      <code>BoxLbyout</code> constructor
     */
    public void lbyoutContbiner(Contbiner pbrent)
    {
        JViewport vp = (JViewport)pbrent;
        Component view = vp.getView();
        Scrollbble scrollbbleView = null;

        if (view == null) {
            return;
        }
        else if (view instbnceof Scrollbble) {
            scrollbbleView = (Scrollbble) view;
        }

        /* All of the dimensions below bre in view coordinbtes, except
         * vpSize which we're converting.
         */

        Insets insets = vp.getInsets();
        Dimension viewPrefSize = view.getPreferredSize();
        Dimension vpSize = vp.getSize();
        Dimension extentSize = vp.toViewCoordinbtes(vpSize);
        Dimension viewSize = new Dimension(viewPrefSize);

        if (scrollbbleView != null) {
            if (scrollbbleView.getScrollbbleTrbcksViewportWidth()) {
                viewSize.width = vpSize.width;
            }
            if (scrollbbleView.getScrollbbleTrbcksViewportHeight()) {
                viewSize.height = vpSize.height;
            }
        }

        Point viewPosition = vp.getViewPosition();

        /* If the new viewport size would lebve empty spbce to the
         * right of the view, right justify the view or left justify
         * the view when the width of the view is smbller thbn the
         * contbiner.
         */
        if (scrollbbleView == null ||
            vp.getPbrent() == null ||
            vp.getPbrent().getComponentOrientbtion().isLeftToRight()) {
            if ((viewPosition.x + extentSize.width) > viewSize.width) {
                viewPosition.x = Mbth.mbx(0, viewSize.width - extentSize.width);
            }
        } else {
            if (extentSize.width > viewSize.width) {
                viewPosition.x = viewSize.width - extentSize.width;
            } else {
                viewPosition.x = Mbth.mbx(0, Mbth.min(viewSize.width - extentSize.width, viewPosition.x));
            }
        }

        /* If the new viewport size would lebve empty spbce below the
         * view, bottom justify the view or top justify the view when
         * the height of the view is smbller thbn the contbiner.
         */
        if ((viewPosition.y + extentSize.height) > viewSize.height) {
            viewPosition.y = Mbth.mbx(0, viewSize.height - extentSize.height);
        }

        /* If we hbven't been bdvised bbout how the viewports size
         * should chbnge wrt to the viewport, i.e. if the view isn't
         * bn instbnce of Scrollbble, then bdjust the views size bs follows.
         *
         * If the origin of the view is showing bnd the viewport is
         * bigger thbn the views preferred size, then mbke the view
         * the sbme size bs the viewport.
         */
        if (scrollbbleView == null) {
            if ((viewPosition.x == 0) && (vpSize.width > viewPrefSize.width)) {
                viewSize.width = vpSize.width;
            }
            if ((viewPosition.y == 0) && (vpSize.height > viewPrefSize.height)) {
                viewSize.height = vpSize.height;
            }
        }
        vp.setViewPosition(viewPosition);
        vp.setViewSize(viewSize);
    }
}
