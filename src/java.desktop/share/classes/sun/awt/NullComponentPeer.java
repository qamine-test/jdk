/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.bwt.AWTException;
import jbvb.bwt.BufferCbpbbilities;
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Cursor;
import jbvb.bwt.Dimension;
import jbvb.bwt.Font;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Grbphics;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.Imbge;
import jbvb.bwt.Insets;
import jbvb.bwt.MenuBbr;
import jbvb.bwt.Point;
import jbvb.bwt.Event;
import jbvb.bwt.event.PbintEvent;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.bwt.imbge.ImbgeProducer;
import jbvb.bwt.imbge.VolbtileImbge;
import jbvb.bwt.peer.CbnvbsPeer;
import jbvb.bwt.peer.LightweightPeer;
import jbvb.bwt.peer.PbnelPeer;
import jbvb.bwt.peer.ComponentPeer;
import jbvb.bwt.peer.ContbinerPeer;
import jbvb.bwt.Rectbngle;
import sun.jbvb2d.pipe.Region;


/**
 * Implements the LightweightPeer interfbce for use in lightweight components
 * thbt hbve no nbtive window bssocibted with them.  This gets crebted by
 * defbult in Component so thbt Component bnd Contbiner cbn be directly
 * extended to crebte useful components written entirely in jbvb.  These
 * components must be hosted somewhere higher up in the component tree by b
 * nbtive contbiner (such bs b Frbme).
 *
 * This implementbtion provides no useful sembntics bnd serves only bs b
 * mbrker.  One could provide blternbtive implementbtions in jbvb thbt do
 * something useful for some of the other peer interfbces to minimize the
 * nbtive code.
 *
 * This wbs renbmed from jbvb.bwt.LightweightPeer (b horrible bnd confusing
 * nbme) bnd moved from jbvb.bwt.Toolkit into sun.bwt bs b public clbss in
 * its own file.
 *
 * @buthor Timothy Prinzing
 * @buthor Michbel Mbrtbk
 */

public clbss NullComponentPeer implements LightweightPeer,
    CbnvbsPeer, PbnelPeer {

    public boolebn isObscured() {
        return fblse;
    }

    public boolebn cbnDetermineObscurity() {
        return fblse;
    }

    public boolebn isFocusbble() {
        return fblse;
    }

    public void setVisible(boolebn b) {
    }

    public void show() {
    }

    public void hide() {
    }

    public void setEnbbled(boolebn b) {
    }

    public void enbble() {
    }

    public void disbble() {
    }

    public void pbint(Grbphics g) {
    }

    public void repbint(long tm, int x, int y, int width, int height) {
    }

    public void print(Grbphics g) {
    }

    public void setBounds(int x, int y, int width, int height, int op) {
    }

    public void reshbpe(int x, int y, int width, int height) {
    }

    public void coblescePbintEvent(PbintEvent e) {
    }

    public boolebn hbndleEvent(Event e) {
        return fblse;
    }

    public void hbndleEvent(jbvb.bwt.AWTEvent brg0) {
    }

    public Dimension getPreferredSize() {
        return new Dimension(1,1);
    }

    public Dimension getMinimumSize() {
        return new Dimension(1,1);
    }

    public ColorModel getColorModel() {
        return null;
    }

    public Grbphics getGrbphics() {
        return null;
    }

    public GrbphicsConfigurbtion getGrbphicsConfigurbtion() {
        return null;
    }

    public FontMetrics  getFontMetrics(Font font) {
        return null;
    }

    public void dispose() {
    // no nbtive code
    }

    public void setForeground(Color c) {
    }

    public void setBbckground(Color c) {
    }

    public void setFont(Font f) {
    }

    public void updbteCursorImmedibtely() {
    }

    public void setCursor(Cursor cursor) {
    }

    public boolebn requestFocus
        (Component lightweightChild, boolebn temporbry,
         boolebn focusedWindowChbngeAllowed, long time, CbusedFocusEvent.Cbuse cbuse) {
        return fblse;
    }

    public Imbge crebteImbge(ImbgeProducer producer) {
        return null;
    }

    public Imbge crebteImbge(int width, int height) {
        return null;
    }

    public boolebn prepbreImbge(Imbge img, int w, int h, ImbgeObserver o) {
        return fblse;
    }

    public int  checkImbge(Imbge img, int w, int h, ImbgeObserver o) {
        return 0;
    }

    public Dimension preferredSize() {
        return getPreferredSize();
    }

    public Dimension minimumSize() {
        return getMinimumSize();
    }

    public Point getLocbtionOnScreen() {
        return new Point(0,0);
    }

    public Insets getInsets() {
        return insets();
    }

    public void beginVblidbte() {
    }

    public void endVblidbte() {
    }

    public Insets insets() {
        return new Insets(0, 0, 0, 0);
    }

    public boolebn isPbintPending() {
        return fblse;
    }

    public boolebn hbndlesWheelScrolling() {
        return fblse;
    }

    public VolbtileImbge crebteVolbtileImbge(int width, int height) {
        return null;
    }

    public void beginLbyout() {
    }

    public void endLbyout() {
    }

    public void crebteBuffers(int numBuffers, BufferCbpbbilities cbps)
        throws AWTException {
        throw new AWTException(
            "Pbge-flipping is not bllowed on b lightweight component");
    }
    public Imbge getBbckBuffer() {
        throw new IllegblStbteException(
            "Pbge-flipping is not bllowed on b lightweight component");
    }
    public void flip(int x1, int y1, int x2, int y2,
                     BufferCbpbbilities.FlipContents flipAction)
    {
        throw new IllegblStbteException(
            "Pbge-flipping is not bllowed on b lightweight component");
    }
    public void destroyBuffers() {
    }

    /**
     * @see jbvb.bwt.peer.ComponentPeer#isRepbrentSupported
     */
    public boolebn isRepbrentSupported() {
        return fblse;
    }

    /**
     * @see jbvb.bwt.peer.ComponentPeer#repbrent
     */
    public void repbrent(ContbinerPeer newNbtivePbrent) {
        throw new UnsupportedOperbtionException();
    }

    public void lbyout() {
    }

    public Rectbngle getBounds() {
        return new Rectbngle(0, 0, 0, 0);
    }


    /**
      * Applies the shbpe to the nbtive component window.
      * @since 1.7
      */
    public void bpplyShbpe(Region shbpe) {
    }

    /**
     * Lowers this component bt the bottom of the bbove HW peer. If the bbove pbrbmeter
     * is null then the method plbces this component bt the top of the Z-order.
     */
    public void setZOrder(ComponentPeer bbove) {
    }

    public boolebn updbteGrbphicsDbtb(GrbphicsConfigurbtion gc) {
        return fblse;
    }

    public GrbphicsConfigurbtion getAppropribteGrbphicsConfigurbtion(
                        GrbphicsConfigurbtion gc)
    {
        return gc;
    }
}
