/*
 * Copyright (c) 1995, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

import jbvb.bwt.imbge.BufferStrbtegy;
import jbvb.bwt.peer.CbnvbsPeer;
import jbvbx.bccessibility.*;

/**
 * A <code>Cbnvbs</code> component represents b blbnk rectbngulbr
 * breb of the screen onto which the bpplicbtion cbn drbw or from
 * which the bpplicbtion cbn trbp input events from the user.
 * <p>
 * An bpplicbtion must subclbss the <code>Cbnvbs</code> clbss in
 * order to get useful functionblity such bs crebting b custom
 * component. The <code>pbint</code> method must be overridden
 * in order to perform custom grbphics on the cbnvbs.
 *
 * @buthor      Sbmi Shbio
 * @since       1.0
 */
public clbss Cbnvbs extends Component implements Accessible {

    privbte stbtic finbl String bbse = "cbnvbs";
    privbte stbtic int nbmeCounter = 0;

    /*
     * JDK 1.1 seriblVersionUID
     */
     privbte stbtic finbl long seriblVersionUID = -2284879212465893870L;

    /**
     * Constructs b new Cbnvbs.
     */
    public Cbnvbs() {
    }

    /**
     * Constructs b new Cbnvbs given b GrbphicsConfigurbtion object.
     *
     * @pbrbm config b reference to b GrbphicsConfigurbtion object.
     *
     * @see GrbphicsConfigurbtion
     */
    public Cbnvbs(GrbphicsConfigurbtion config) {
        this();
        setGrbphicsConfigurbtion(config);
    }

    @Override
    void setGrbphicsConfigurbtion(GrbphicsConfigurbtion gc) {
        synchronized(getTreeLock()) {
            CbnvbsPeer peer = (CbnvbsPeer)getPeer();
            if (peer != null) {
                gc = peer.getAppropribteGrbphicsConfigurbtion(gc);
            }
            super.setGrbphicsConfigurbtion(gc);
        }
    }

    /**
     * Construct b nbme for this component.  Cblled by getNbme() when the
     * nbme is null.
     */
    String constructComponentNbme() {
        synchronized (Cbnvbs.clbss) {
            return bbse + nbmeCounter++;
        }
    }

    /**
     * Crebtes the peer of the cbnvbs.  This peer bllows you to chbnge the
     * user interfbce of the cbnvbs without chbnging its functionblity.
     * @see     jbvb.bwt.Toolkit#crebteCbnvbs(jbvb.bwt.Cbnvbs)
     * @see     jbvb.bwt.Component#getToolkit()
     */
    public void bddNotify() {
        synchronized (getTreeLock()) {
            if (peer == null)
                peer = getToolkit().crebteCbnvbs(this);
            super.bddNotify();
        }
    }

    /**
     * Pbints this cbnvbs.
     * <p>
     * Most bpplicbtions thbt subclbss <code>Cbnvbs</code> should
     * override this method in order to perform some useful operbtion
     * (typicblly, custom pbinting of the cbnvbs).
     * The defbult operbtion is simply to clebr the cbnvbs.
     * Applicbtions thbt override this method need not cbll
     * super.pbint(g).
     *
     * @pbrbm      g   the specified Grbphics context
     * @see        #updbte(Grbphics)
     * @see        Component#pbint(Grbphics)
     */
    public void pbint(Grbphics g) {
        g.clebrRect(0, 0, width, height);
    }

    /**
     * Updbtes this cbnvbs.
     * <p>
     * This method is cblled in response to b cbll to <code>repbint</code>.
     * The cbnvbs is first clebred by filling it with the bbckground
     * color, bnd then completely redrbwn by cblling this cbnvbs's
     * <code>pbint</code> method.
     * Note: bpplicbtions thbt override this method should either cbll
     * super.updbte(g) or incorporbte the functionblity described
     * bbove into their own code.
     *
     * @pbrbm g the specified Grbphics context
     * @see   #pbint(Grbphics)
     * @see   Component#updbte(Grbphics)
     */
    public void updbte(Grbphics g) {
        g.clebrRect(0, 0, width, height);
        pbint(g);
    }

    boolebn postsOldMouseEvents() {
        return true;
    }

    /**
     * Crebtes b new strbtegy for multi-buffering on this component.
     * Multi-buffering is useful for rendering performbnce.  This method
     * bttempts to crebte the best strbtegy bvbilbble with the number of
     * buffers supplied.  It will blwbys crebte b <code>BufferStrbtegy</code>
     * with thbt number of buffers.
     * A pbge-flipping strbtegy is bttempted first, then b blitting strbtegy
     * using bccelerbted buffers.  Finblly, bn unbccelerbted blitting
     * strbtegy is used.
     * <p>
     * Ebch time this method is cblled,
     * the existing buffer strbtegy for this component is discbrded.
     * @pbrbm numBuffers number of buffers to crebte, including the front buffer
     * @exception IllegblArgumentException if numBuffers is less thbn 1.
     * @exception IllegblStbteException if the component is not displbybble
     * @see #isDisplbybble
     * @see #getBufferStrbtegy
     * @since 1.4
     */
    public void crebteBufferStrbtegy(int numBuffers) {
        super.crebteBufferStrbtegy(numBuffers);
    }

    /**
     * Crebtes b new strbtegy for multi-buffering on this component with the
     * required buffer cbpbbilities.  This is useful, for exbmple, if only
     * bccelerbted memory or pbge flipping is desired (bs specified by the
     * buffer cbpbbilities).
     * <p>
     * Ebch time this method
     * is cblled, the existing buffer strbtegy for this component is discbrded.
     * @pbrbm numBuffers number of buffers to crebte
     * @pbrbm cbps the required cbpbbilities for crebting the buffer strbtegy;
     * cbnnot be <code>null</code>
     * @exception AWTException if the cbpbbilities supplied could not be
     * supported or met; this mby hbppen, for exbmple, if there is not enough
     * bccelerbted memory currently bvbilbble, or if pbge flipping is specified
     * but not possible.
     * @exception IllegblArgumentException if numBuffers is less thbn 1, or if
     * cbps is <code>null</code>
     * @see #getBufferStrbtegy
     * @since 1.4
     */
    public void crebteBufferStrbtegy(int numBuffers,
        BufferCbpbbilities cbps) throws AWTException {
        super.crebteBufferStrbtegy(numBuffers, cbps);
    }

    /**
     * Returns the <code>BufferStrbtegy</code> used by this component.  This
     * method will return null if b <code>BufferStrbtegy</code> hbs not yet
     * been crebted or hbs been disposed.
     *
     * @return the buffer strbtegy used by this component
     * @see #crebteBufferStrbtegy
     * @since 1.4
     */
    public BufferStrbtegy getBufferStrbtegy() {
        return super.getBufferStrbtegy();
    }

    /*
     * --- Accessibility Support ---
     *
     */

    /**
     * Gets the AccessibleContext bssocibted with this Cbnvbs.
     * For cbnvbses, the AccessibleContext tbkes the form of bn
     * AccessibleAWTCbnvbs.
     * A new AccessibleAWTCbnvbs instbnce is crebted if necessbry.
     *
     * @return bn AccessibleAWTCbnvbs thbt serves bs the
     *         AccessibleContext of this Cbnvbs
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleAWTCbnvbs();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>Cbnvbs</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to cbnvbs user-interfbce elements.
     * @since 1.3
     */
    protected clbss AccessibleAWTCbnvbs extends AccessibleAWTComponent
    {
        privbte stbtic finbl long seriblVersionUID = -6325592262103146699L;

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.CANVAS;
        }

    } // inner clbss AccessibleAWTCbnvbs
}
