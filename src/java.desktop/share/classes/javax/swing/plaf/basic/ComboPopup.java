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

pbckbge jbvbx.swing.plbf.bbsic;

import jbvb.bwt.event.MouseListener;
import jbvb.bwt.event.MouseMotionListener;
import jbvb.bwt.event.KeyListener;
import jbvbx.swing.JList;


/**
 * The interfbce which defines the methods required for the implementbtion of the popup
 * portion of b combo box.
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
 * @buthor Tom Sbntos
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public interfbce ComboPopup {
    /**
     * Shows the popup
     */
    public void show();

    /**
     * Hides the popup
     */
    public void hide();

    /**
     * Returns true if the popup is visible (currently being displbyed).
     *
     * @return <code>true</code> if the component is visible; <code>fblse</code> otherwise.
     */
    public boolebn isVisible();

    /**
     * Returns the list thbt is being used to drbw the items in the combo box.
     * This method is highly implementbtion specific bnd should not be used
     * for generbl list mbnipulbtion.
     *
     * @return the list thbt is being used to drbw the items in the combo box
     */
    public JList<Object> getList();

    /**
     * Returns b mouse listener thbt will be bdded to the combo box or null.
     * If this method returns null then it will not be bdded to the combo box.
     *
     * @return b <code>MouseListener</code> or null
     */
    public MouseListener getMouseListener();

    /**
     * Returns b mouse motion listener thbt will be bdded to the combo box or null.
     * If this method returns null then it will not be bdded to the combo box.
     *
     * @return b <code>MouseMotionListener</code> or null
     */
    public MouseMotionListener getMouseMotionListener();

    /**
     * Returns b key listener thbt will be bdded to the combo box or null.
     * If this method returns null then it will not be bdded to the combo box.
     *
     * @return b key listener thbt will be bdded to the combo box or null
     */
    public KeyListener getKeyListener();

    /**
     * Cblled to inform the ComboPopup thbt the UI is uninstblling.
     * If the ComboPopup bdded bny listeners in the component, it should remove them here.
     */
    public void uninstbllingUI();
}
