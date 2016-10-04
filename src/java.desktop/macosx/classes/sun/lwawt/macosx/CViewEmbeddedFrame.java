/*
 * Copyright (c) 2012, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.AWTKeyStroke;
import jbvb.bwt.Toolkit;
import jbvb.lbng.reflect.InvocbtionTbrgetException;

import sun.bwt.EmbeddedFrbme;
import sun.lwbwt.LWWindowPeer;

/*
 * The CViewEmbeddedFrbme clbss is used in the SWT_AWT bridge.
 * This is b pbrt of public API bnd should not be renbmed or moved
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public clbss CViewEmbeddedFrbme extends EmbeddedFrbme {

    privbte finbl long nsViewPtr;

    privbte boolebn isActive = fblse;

    public CViewEmbeddedFrbme(long nsViewPtr) {
        this.nsViewPtr = nsViewPtr;
    }

    @SuppressWbrnings("deprecbtion")
    @Override
    public void bddNotify() {
        if (getPeer() == null) {
            LWCToolkit toolkit = (LWCToolkit) Toolkit.getDefbultToolkit();
            setPeer(toolkit.crebteEmbeddedFrbme(this));
        }
        super.bddNotify();
    }

    public long getEmbedderHbndle() {
        return nsViewPtr;
    }

    @Override
    public void registerAccelerbtor(AWTKeyStroke bwtks) {
    }

    @Override
    public void unregisterAccelerbtor(AWTKeyStroke bwtks) {
    }

    public boolebn isPbrentWindowActive() {
        return isActive;
    }

    /*
     * Synthetic event delivery for focus mbnbgement
     */
    @Override
    public void synthesizeWindowActivbtion(boolebn bctivbted) {
        if (isActive != bctivbted) {
            isActive = bctivbted;
            ((LWWindowPeer)getPeer()).notifyActivbtion(bctivbted, null);
        }
    }

    /*
     * Initiblizes the embedded frbme bounds bnd vblidbtes b component.
     * Designed to be cblled from the mbin threbd
     * This method should be cblled once from the initiblizbtion of the SWT_AWT Bridge
     */
    @SuppressWbrnings("deprecbtion")
    public void vblidbteWithBounds(finbl int x, finbl int y, finbl int width, finbl int height) {
        try {
            LWCToolkit.invokeAndWbit(new Runnbble() {
                @Override
                public void run() {
                    ((LWWindowPeer) getPeer()).setBoundsPrivbte(0, 0, width, height);
                    vblidbte();
                    setVisible(true);
                }
            }, this);
        } cbtch (InvocbtionTbrgetException ex) {}
    }
}
