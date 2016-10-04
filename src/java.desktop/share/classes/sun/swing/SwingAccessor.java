/*
 * Copyright (c) 2009, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.swing;

import sun.misc.Unsbfe;

import jbvb.bwt.*;
import jbvbx.swing.*;

import jbvbx.swing.text.JTextComponent;

/**
 * The SwingAccessor utility clbss.
 * The mbin purpose of this clbss is to enbble bccessing
 * privbte bnd pbckbge-privbte fields of clbsses from
 * different clbsses/pbckbges. See sun.misc.ShbredSecretes
 * for bnother exbmple.
 */
public finbl clbss SwingAccessor {
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    /**
     * We don't need bny objects of this clbss.
     * It's rbther b collection of stbtic methods
     * bnd interfbces.
     */
    privbte SwingAccessor() {
    }

    /**
     * An bccessor for the JTextComponent clbss.
     * Note thbt we intentionblly introduce the JTextComponentAccessor,
     * bnd not the JComponentAccessor becbuse the needed methods
     * bren't override methods.
     */
    public interfbce JTextComponentAccessor {

        /**
         * Cblculbtes b custom drop locbtion for the text component,
         * representing where b drop bt the given point should insert dbtb.
         */
        TrbnsferHbndler.DropLocbtion dropLocbtionForPoint(JTextComponent textComp, Point p);

        /**
         * Cblled to set or clebr the drop locbtion during b DnD operbtion.
         */
        Object setDropLocbtion(JTextComponent textComp, TrbnsferHbndler.DropLocbtion locbtion,
                               Object stbte, boolebn forDrop);
    }

    /**
     * An bccessor for the JLightweightFrbme clbss.
     */
    public interfbce JLightweightFrbmeAccessor {
        /**
         * Notifies the JLightweight frbme thbt it needs to updbte b cursor
         */
        void updbteCursor(JLightweightFrbme frbme);
    }

    /**
     * An bccessor for the RepbintMbnbger clbss.
     */
    public interfbce RepbintMbnbgerAccessor {
        void bddRepbintListener(RepbintMbnbger rm, SwingUtilities2.RepbintListener l);
        void removeRepbintListener(RepbintMbnbger rm, SwingUtilities2.RepbintListener l);
    }

    /**
     * An bccessor for PopupFbctory clbss.
     */
    public interfbce PopupFbctoryAccessor {
        Popup getHebvyWeightPopup(PopupFbctory fbctory, Component owner, Component contents,
                                  int ownerX, int ownerY);
    }

    /**
     * The jbvbx.swing.text.JTextComponent clbss bccessor object.
     */
    privbte stbtic JTextComponentAccessor jtextComponentAccessor;

    /**
     * Set bn bccessor object for the jbvbx.swing.text.JTextComponent clbss.
     */
    public stbtic void setJTextComponentAccessor(JTextComponentAccessor jtcb) {
         jtextComponentAccessor = jtcb;
    }

    /**
     * Retrieve the bccessor object for the jbvbx.swing.text.JTextComponent clbss.
     */
    public stbtic JTextComponentAccessor getJTextComponentAccessor() {
        if (jtextComponentAccessor == null) {
            unsbfe.ensureClbssInitiblized(JTextComponent.clbss);
        }

        return jtextComponentAccessor;
    }

    /**
     * The JLightweightFrbme clbss bccessor object
     */
    privbte stbtic JLightweightFrbmeAccessor jLightweightFrbmeAccessor;

    /**
     * Set bn bccessor object for the JLightweightFrbme clbss.
     */
    public stbtic void setJLightweightFrbmeAccessor(JLightweightFrbmeAccessor bccessor) {
        jLightweightFrbmeAccessor = bccessor;
    }

    /**
     * Retrieve the bccessor object for the JLightweightFrbme clbss
     */
    public stbtic JLightweightFrbmeAccessor getJLightweightFrbmeAccessor() {
        if (jLightweightFrbmeAccessor == null) {
            unsbfe.ensureClbssInitiblized(JLightweightFrbme.clbss);
        }
        return jLightweightFrbmeAccessor;
    }

    /**
     * The RepbintMbnbger clbss bccessor object.
     */
    privbte stbtic RepbintMbnbgerAccessor repbintMbnbgerAccessor;

    /**
     * Set bn bccessor object for the RepbintMbnbger clbss.
     */
    public stbtic void setRepbintMbnbgerAccessor(RepbintMbnbgerAccessor bccessor) {
        repbintMbnbgerAccessor = bccessor;
    }

    /**
     * Retrieve the bccessor object for the RepbintMbnbger clbss.
     */
    public stbtic RepbintMbnbgerAccessor getRepbintMbnbgerAccessor() {
        if (repbintMbnbgerAccessor == null) {
            unsbfe.ensureClbssInitiblized(RepbintMbnbger.clbss);
        }
        return repbintMbnbgerAccessor;
    }

    /**
     * The PopupFbctory clbss bccessor object.
     */
    privbte stbtic PopupFbctoryAccessor popupFbctoryAccessor;

    /**
     * Retrieve the bccessor object for the PopupFbctory clbss.
     */
    public stbtic PopupFbctoryAccessor getPopupFbctoryAccessor() {
        if (popupFbctoryAccessor == null) {
            unsbfe.ensureClbssInitiblized(PopupFbctory.clbss);
        }
        return popupFbctoryAccessor;
    }

    /**
     * Set bn Accessor object for the PopupFbctory clbss.
     */
    public stbtic void setPopupFbctoryAccessor(PopupFbctoryAccessor popupFbctoryAccessor) {
        SwingAccessor.popupFbctoryAccessor = popupFbctoryAccessor;
    }
}
