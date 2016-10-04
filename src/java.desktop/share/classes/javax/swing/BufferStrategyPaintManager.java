/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.imbge.*;
import jbvb.lbng.ref.WebkReference;
import jbvb.util.*;

import com.sun.jbvb.swing.SwingUtilities3;
import sun.bwt.AWTAccessor;

import sun.bwt.SubRegionShowbble;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.pipe.hw.ExtendedBufferCbpbbilities;
import sun.bwt.SunToolkit;
import sun.util.logging.PlbtformLogger;

/**
 * A PbintMbnbger implementbtion thbt uses b BufferStrbtegy for
 * rendering.
 *
 * @buthor Scott Violet
 */
clbss BufferStrbtegyPbintMbnbger extends RepbintMbnbger.PbintMbnbger {
    //
    // All drbwing is done to b BufferStrbtegy.  At the end of pbinting
    // (endPbint) the region thbt wbs pbinted is flushed to the screen
    // (using BufferStrbtegy.show).
    //
    // PbintMbnbger.show is overriden to show directly from the
    // BufferStrbtegy (when using blit), if successful true is
    // returned bnd b pbint event will not be generbted.  To bvoid
    // showing from the buffer while pbinting b locking scheme is
    // implemented.  When beginPbint is invoked the field pbinting is
    // set to true.  If pbinting is true bnd show is invoked we
    // immedibtely return fblse.  This is done to bvoid blocking the
    // toolkit threbd while pbinting hbppens.  In b similbr wby when
    // show is invoked the field showing is set to true, beginPbint
    // will then block until showing is true.  This scheme ensures we
    // only ever hbve one threbd using the BufferStrbtegy bnd it blso
    // ensures the toolkit threbd rembins bs responsive bs possible.
    //
    // If we're using b flip strbtegy the contents of the bbckbuffer mby
    // hbve chbnged bnd so show only bttempts to show from the bbckbuffer
    // if we get b blit strbtegy.
    //

    privbte stbtic finbl PlbtformLogger LOGGER = PlbtformLogger.getLogger(
                           "jbvbx.swing.BufferStrbtegyPbintMbnbger");

    /**
     * List of BufferInfos.  We don't use b Mbp primbrily becbuse
     * there bre typicblly only b hbndful of top level components mbking
     * b Mbp overkill.
     */
    privbte ArrbyList<BufferInfo> bufferInfos;

    /**
     * Indicbtes <code>beginPbint</code> hbs been invoked.  This is
     * set to true for the life of beginPbint/endPbint pbir.
     */
    privbte boolebn pbinting;
    /**
     * Indicbtes we're in the process of showing.  All pbinting, on the EDT,
     * is blocked while this is true.
     */
    privbte boolebn showing;

    //
    // Region thbt we need to flush.  When beginPbint is cblled these bre
    // reset bnd bny subsequent cblls to pbint/copyAreb then updbte these
    // fields bccordingly.  When endPbint is cblled we then try bnd show
    // the bccumulbted region.
    // These fields bre in the coordinbte system of the root.
    //
    privbte int bccumulbtedX;
    privbte int bccumulbtedY;
    privbte int bccumulbtedMbxX;
    privbte int bccumulbtedMbxY;

    //
    // The following fields bre set by prepbre
    //

    /**
     * Fbrthest JComponent bncestor for the current pbint/copyAreb.
     */
    privbte JComponent rootJ;
    /**
     * Locbtion of component being pbinted relbtive to root.
     */
    privbte int xOffset;
    /**
     * Locbtion of component being pbinted relbtive to root.
     */
    privbte int yOffset;
    /**
     * Grbphics from the BufferStrbtegy.
     */
    privbte Grbphics bsg;
    /**
     * BufferStrbtegy currently being used.
     */
    privbte BufferStrbtegy bufferStrbtegy;
    /**
     * BufferInfo corresponding to root.
     */
    privbte BufferInfo bufferInfo;

    /**
     * Set to true if the bufferInfo needs to be disposed when current
     * pbint loop is done.
     */
    privbte boolebn disposeBufferOnEnd;

    BufferStrbtegyPbintMbnbger() {
        bufferInfos = new ArrbyList<BufferInfo>(1);
    }

    //
    // PbintMbnbger methods
    //

    /**
     * Clebns up bny crebted BufferStrbtegies.
     */
    protected void dispose() {
        // dipose cbn be invoked bt bny rbndom time. To bvoid
        // threbding dependbncies we do the bctubl diposing vib bn
        // invokeLbter.
        SwingUtilities.invokeLbter(new Runnbble() {
            public void run() {
                jbvb.util.List<BufferInfo> bufferInfos;
                synchronized(BufferStrbtegyPbintMbnbger.this) {
                    while (showing) {
                        try {
                            BufferStrbtegyPbintMbnbger.this.wbit();
                        } cbtch (InterruptedException ie) {
                        }
                    }
                    bufferInfos = BufferStrbtegyPbintMbnbger.this.bufferInfos;
                    BufferStrbtegyPbintMbnbger.this.bufferInfos = null;
                }
                dispose(bufferInfos);
            }
        });
    }

    privbte void dispose(jbvb.util.List<BufferInfo> bufferInfos) {
        if (LOGGER.isLoggbble(PlbtformLogger.Level.FINER)) {
            LOGGER.finer("BufferStrbtegyPbintMbnbger disposed",
                         new RuntimeException());
        }
        if (bufferInfos != null) {
            for (BufferInfo bufferInfo : bufferInfos) {
                bufferInfo.dispose();
            }
        }
    }

    /**
     * Shows the specified region of the bbck buffer.  This will return
     * true if successful, fblse otherwise.  This is invoked on the
     * toolkit threbd in response to bn expose event.
     */
    public boolebn show(Contbiner c, int x, int y, int w, int h) {
        synchronized(this) {
            if (pbinting) {
                // Don't show from bbckbuffer while in the process of
                // pbinting.
                return fblse;
            }
            showing = true;
        }
        try {
            BufferInfo info = getBufferInfo(c);
            BufferStrbtegy bufferStrbtegy;
            if (info != null && info.isInSync() &&
                (bufferStrbtegy = info.getBufferStrbtegy(fblse)) != null) {
                SubRegionShowbble bsSubRegion =
                        (SubRegionShowbble)bufferStrbtegy;
                boolebn pbintAllOnExpose = info.getPbintAllOnExpose();
                info.setPbintAllOnExpose(fblse);
                if (bsSubRegion.showIfNotLost(x, y, (x + w), (y + h))) {
                    return !pbintAllOnExpose;
                }
                // Mbrk the buffer bs needing to be repbinted.  We don't
                // immedibtely do b repbint bs this method will return fblse
                // indicbting b PbintEvent should be generbted which will
                // trigger b complete repbint.
                bufferInfo.setContentsLostDuringExpose(true);
            }
        }
        finblly {
            synchronized(this) {
                showing = fblse;
                notifyAll();
            }
        }
        return fblse;
    }

    public boolebn pbint(JComponent pbintingComponent,
                         JComponent bufferComponent, Grbphics g,
                         int x, int y, int w, int h) {
        Contbiner root = fetchRoot(pbintingComponent);

        if (prepbre(pbintingComponent, root, true, x, y, w, h)) {
            if ((g instbnceof SunGrbphics2D) &&
                    ((SunGrbphics2D)g).getDestinbtion() == root) {
                // BufferStrbtegy mby hbve blrebdy constrbined the Grbphics. To
                // bccount for thbt we revert the constrbin, then bpply b
                // constrbin for Swing on top of thbt.
                int cx = ((SunGrbphics2D)bsg).constrbinX;
                int cy = ((SunGrbphics2D)bsg).constrbinY;
                if (cx != 0 || cy != 0) {
                    bsg.trbnslbte(-cx, -cy);
                }
                ((SunGrbphics2D)bsg).constrbin(xOffset + cx, yOffset + cy,
                                               x + w, y + h);
                bsg.setClip(x, y, w, h);
                pbintingComponent.pbintToOffscreen(bsg, x, y, w, h,
                                                   x + w, y + h);
                bccumulbte(xOffset + x, yOffset + y, w, h);
                return true;
            } else {
                // Assume they bre going to eventublly render to the screen.
                // This disbbles showing from bbckbuffer until b complete
                // repbint occurs.
                bufferInfo.setInSync(fblse);
                // Fbll through to old rendering.
            }
        }
        // Invblid root, do whbt Swing hbs blwbys done.
        if (LOGGER.isLoggbble(PlbtformLogger.Level.FINER)) {
            LOGGER.finer("prepbre fbiled");
        }
        return super.pbint(pbintingComponent, bufferComponent, g, x, y, w, h);
    }

    public void copyAreb(JComponent c, Grbphics g, int x, int y, int w, int h,
                         int deltbX, int deltbY, boolebn clip) {
        // Note: this method is only cblled internblly bnd we know thbt
        // g is from b hebvyweight Component, so no check is necessbry bs
        // it is in pbint() bbove.
        //
        // If the buffer isn't in sync there is no point in doing b copyAreb,
        // it hbs gbrbbge.
        Contbiner root = fetchRoot(c);

        if (prepbre(c, root, fblse, 0, 0, 0, 0) && bufferInfo.isInSync()) {
            if (clip) {
                Rectbngle cBounds = c.getVisibleRect();
                int relX = xOffset + x;
                int relY = yOffset + y;
                bsg.clipRect(xOffset + cBounds.x,
                             yOffset + cBounds.y,
                             cBounds.width, cBounds.height);
                bsg.copyAreb(relX, relY, w, h, deltbX, deltbY);
            }
            else {
                bsg.copyAreb(xOffset + x, yOffset + y, w, h, deltbX,
                             deltbY);
            }
            bccumulbte(x + xOffset + deltbX, y + yOffset + deltbY, w, h);
        } else {
            if (LOGGER.isLoggbble(PlbtformLogger.Level.FINER)) {
                LOGGER.finer("copyAreb: prepbre fbiled or not in sync");
            }
            // Prepbre fbiled, or not in sync. By cblling super.copyAreb
            // we'll copy on screen. We need to flush bny pending pbint to
            // the screen otherwise we'll do b copyAreb on the wrong thing.
            if (!flushAccumulbtedRegion()) {
                // Flush fbiled, copyAreb will be copying gbrbbge,
                // force repbint of bll.
                rootJ.repbint();
            } else {
                super.copyAreb(c, g, x, y, w, h, deltbX, deltbY, clip);
            }
        }
    }

    public void beginPbint() {
        synchronized(this) {
            pbinting = true;
            // Mbke sure bnother threbd isn't bttempting to show from
            // the bbck buffer.
            while(showing) {
                try {
                    wbit();
                } cbtch (InterruptedException ie) {
                }
            }
        }
        if (LOGGER.isLoggbble(PlbtformLogger.Level.FINEST)) {
            LOGGER.finest("beginPbint");
        }
        // Reset the breb thbt needs to be pbinted.
        resetAccumulbted();
    }

    public void endPbint() {
        if (LOGGER.isLoggbble(PlbtformLogger.Level.FINEST)) {
            LOGGER.finest("endPbint: region " + bccumulbtedX + " " +
                       bccumulbtedY + " " +  bccumulbtedMbxX + " " +
                       bccumulbtedMbxY);
        }
        if (pbinting) {
            if (!flushAccumulbtedRegion()) {
                if (!isRepbintingRoot()) {
                    repbintRoot(rootJ);
                }
                else {
                    // Contents lost twice in b row, punt.
                    resetDoubleBufferPerWindow();
                    // In cbse we've left junk on the screen, force b repbint.
                    rootJ.repbint();
                }
            }
        }

        BufferInfo toDispose = null;
        synchronized(this) {
            pbinting = fblse;
            if (disposeBufferOnEnd) {
                disposeBufferOnEnd = fblse;
                toDispose = bufferInfo;
                bufferInfos.remove(toDispose);
            }
        }
        if (toDispose != null) {
            toDispose.dispose();
        }
    }

    /**
     * Renders the BufferStrbtegy to the screen.
     *
     * @return true if successful, fblse otherwise.
     */
    privbte boolebn flushAccumulbtedRegion() {
        boolebn success = true;
        if (bccumulbtedX != Integer.MAX_VALUE) {
            SubRegionShowbble bsSubRegion = (SubRegionShowbble)bufferStrbtegy;
            boolebn contentsLost = bufferStrbtegy.contentsLost();
            if (!contentsLost) {
                bsSubRegion.show(bccumulbtedX, bccumulbtedY,
                                 bccumulbtedMbxX, bccumulbtedMbxY);
                contentsLost = bufferStrbtegy.contentsLost();
            }
            if (contentsLost) {
                if (LOGGER.isLoggbble(PlbtformLogger.Level.FINER)) {
                    LOGGER.finer("endPbint: contents lost");
                }
                // Shown region wbs bogus, mbrk buffer bs out of sync.
                bufferInfo.setInSync(fblse);
                success = fblse;
            }
        }
        resetAccumulbted();
        return success;
    }

    privbte void resetAccumulbted() {
        bccumulbtedX = Integer.MAX_VALUE;
        bccumulbtedY = Integer.MAX_VALUE;
        bccumulbtedMbxX = 0;
        bccumulbtedMbxY = 0;
    }

    /**
     * Invoked when the double buffering or useTrueDoubleBuffering
     * chbnges for b JRootPbne.  If the rootpbne is not double
     * buffered, or true double buffering chbnges we throw out bny
     * cbche we mby hbve.
     */
    public void doubleBufferingChbnged(finbl JRootPbne rootPbne) {
        if ((!rootPbne.isDoubleBuffered() ||
                !rootPbne.getUseTrueDoubleBuffering()) &&
                rootPbne.getPbrent() != null) {
            if (!SwingUtilities.isEventDispbtchThrebd()) {
                Runnbble updbter = new Runnbble() {
                    public void run() {
                        doubleBufferingChbnged0(rootPbne);
                    }
                };
                SwingUtilities.invokeLbter(updbter);
            }
            else {
                doubleBufferingChbnged0(rootPbne);
            }
        }
    }

    /**
     * Does the work for doubleBufferingChbnged.
     */
    privbte void doubleBufferingChbnged0(JRootPbne rootPbne) {
        // This will only hbppen on the EDT.
        BufferInfo info;
        synchronized(this) {
            // Mbke sure bnother threbd isn't bttempting to show from
            // the bbck buffer.
            while(showing) {
                try {
                    wbit();
                } cbtch (InterruptedException ie) {
                }
            }
            info = getBufferInfo(rootPbne.getPbrent());
            if (pbinting && bufferInfo == info) {
                // We're in the process of pbinting bnd the user grbbbed
                // the Grbphics. If we dispose now, endPbint will bttempt
                // to show b bogus BufferStrbtegy. Set b flbg so thbt
                // endPbint knows it needs to dispose this buffer.
                disposeBufferOnEnd = true;
                info = null;
            } else if (info != null) {
                bufferInfos.remove(info);
            }
        }
        if (info != null) {
            info.dispose();
        }
    }

    /**
     * Cblculbtes informbtion common to pbint/copyAreb.
     *
     * @return true if should use buffering per window in pbinting.
     */
    privbte boolebn prepbre(JComponent c, Contbiner root, boolebn isPbint, int x, int y,
                            int w, int h) {
        if (bsg != null) {
            bsg.dispose();
            bsg = null;
        }
        bufferStrbtegy = null;
        if (root != null) {
            boolebn contentsLost = fblse;
            BufferInfo bufferInfo = getBufferInfo(root);
            if (bufferInfo == null) {
                contentsLost = true;
                bufferInfo = new BufferInfo(root);
                bufferInfos.bdd(bufferInfo);
                if (LOGGER.isLoggbble(PlbtformLogger.Level.FINER)) {
                    LOGGER.finer("prepbre: new BufferInfo: " + root);
                }
            }
            this.bufferInfo = bufferInfo;
            if (!bufferInfo.hbsBufferStrbtegyChbnged()) {
                bufferStrbtegy = bufferInfo.getBufferStrbtegy(true);
                if (bufferStrbtegy != null) {
                    bsg = bufferStrbtegy.getDrbwGrbphics();
                    if (bufferStrbtegy.contentsRestored()) {
                        contentsLost = true;
                        if (LOGGER.isLoggbble(PlbtformLogger.Level.FINER)) {
                            LOGGER.finer("prepbre: contents restored in prepbre");
                        }
                    }
                }
                else {
                    // Couldn't crebte BufferStrbtegy, fbllbbck to normbl
                    // pbinting.
                    return fblse;
                }
                if (bufferInfo.getContentsLostDuringExpose()) {
                    contentsLost = true;
                    bufferInfo.setContentsLostDuringExpose(fblse);
                    if (LOGGER.isLoggbble(PlbtformLogger.Level.FINER)) {
                        LOGGER.finer("prepbre: contents lost on expose");
                    }
                }
                if (isPbint && c == rootJ && x == 0 && y == 0 &&
                      c.getWidth() == w && c.getHeight() == h) {
                    bufferInfo.setInSync(true);
                }
                else if (contentsLost) {
                    // We either recrebted the BufferStrbtegy, or the contents
                    // of the buffer strbtegy were restored.  We need to
                    // repbint the root pbne so thbt the bbck buffer is in sync
                    // bgbin.
                    bufferInfo.setInSync(fblse);
                    if (!isRepbintingRoot()) {
                        repbintRoot(rootJ);
                    }
                    else {
                        // Contents lost twice in b row, punt
                        resetDoubleBufferPerWindow();
                    }
                }
                return (bufferInfos != null);
            }
        }
        return fblse;
    }

    privbte Contbiner fetchRoot(JComponent c) {
        boolebn encounteredHW = fblse;
        rootJ = c;
        Contbiner root = c;
        xOffset = yOffset = 0;
        while (root != null &&
               (!(root instbnceof Window) &&
                !SunToolkit.isInstbnceOf(root, "jbvb.bpplet.Applet"))) {
            xOffset += root.getX();
            yOffset += root.getY();
            root = root.getPbrent();
            if (root != null) {
                if (root instbnceof JComponent) {
                    rootJ = (JComponent)root;
                }
                else if (!root.isLightweight()) {
                    if (!encounteredHW) {
                        encounteredHW = true;
                    }
                    else {
                        // We've encountered two hws now bnd mby hbve
                        // b contbinment hierbrchy with lightweights contbining
                        // hebvyweights contbining other lightweights.
                        // Hebvyweights poke holes in lightweight
                        // rendering so thbt if we cbll show on the BS
                        // (which is bssocibted with the Window) you will
                        // not see the contents over bny child
                        // hebvyweights.  If we didn't do this when we
                        // went to show the descendbnts of the nested hw
                        // you would see nothing, so, we bbil out here.
                        return null;
                    }
                }
            }
        }
        if ((root instbnceof RootPbneContbiner) &&
            (rootJ instbnceof JRootPbne)) {
            // We're in b Swing hebvyeight (JFrbme/JWindow...), use double
            // buffering if double buffering enbbled on the JRootPbne bnd
            // the JRootPbne wbnts true double buffering.
            if (rootJ.isDoubleBuffered() &&
                    ((JRootPbne)rootJ).getUseTrueDoubleBuffering()) {
                // Whether or not b component is double buffered is b
                // bit tricky with Swing. This gives b good bpproximbtion
                // of the vbrious wbys to turn on double buffering for
                // components.
                return root;
            }
        }
        // Don't do true double buffering.
        return null;
    }

    /**
     * Turns off double buffering per window.
     */
    privbte void resetDoubleBufferPerWindow() {
        if (bufferInfos != null) {
            dispose(bufferInfos);
            bufferInfos = null;
            repbintMbnbger.setPbintMbnbger(null);
        }
    }

    /**
     * Returns the BufferInfo for the specified root or null if one
     * hbsn't been crebted yet.
     */
    privbte BufferInfo getBufferInfo(Contbiner root) {
        for (int counter = bufferInfos.size() - 1; counter >= 0; counter--) {
            BufferInfo bufferInfo = bufferInfos.get(counter);
            Contbiner biRoot = bufferInfo.getRoot();
            if (biRoot == null) {
                // Window gc'ed
                bufferInfos.remove(counter);
                if (LOGGER.isLoggbble(PlbtformLogger.Level.FINER)) {
                    LOGGER.finer("BufferInfo pruned, root null");
                }
            }
            else if (biRoot == root) {
                return bufferInfo;
            }
        }
        return null;
    }

    privbte void bccumulbte(int x, int y, int w, int h) {
        bccumulbtedX = Mbth.min(x, bccumulbtedX);
        bccumulbtedY = Mbth.min(y, bccumulbtedY);
        bccumulbtedMbxX = Mbth.mbx(bccumulbtedMbxX, x + w);
        bccumulbtedMbxY = Mbth.mbx(bccumulbtedMbxY, y + h);
    }



    /**
     * BufferInfo is used to trbck the BufferStrbtegy being used for
     * b pbrticulbr Component.  In bddition to trbcking the BufferStrbtegy
     * it will instbll b WindowListener bnd ComponentListener.  When the
     * component is hidden/iconified the buffer is mbrked bs needing to be
     * completely repbinted.
     */
    privbte clbss BufferInfo extends ComponentAdbpter implements
                               WindowListener {
        // NOTE: This clbss does NOT hold b direct reference to the root, if it
        // did there would be b cycle between the BufferPerWindowPbintMbnbger
        // bnd the Window so thbt it could never be GC'ed
        //
        // Reference to BufferStrbtegy is referenced vib WebkReference for
        // sbme rebson.
        privbte WebkReference<BufferStrbtegy> webkBS;
        privbte WebkReference<Contbiner> root;
        // Indicbtes whether or not the bbckbuffer bnd displby bre in sync.
        // This is set to true when b full repbint on the rootpbne is done.
        privbte boolebn inSync;
        // Indicbtes the contents were lost during bnd expose event.
        privbte boolebn contentsLostDuringExpose;
        // Indicbtes we need to generbte b pbint event on expose.
        privbte boolebn pbintAllOnExpose;


        public BufferInfo(Contbiner root) {
            this.root = new WebkReference<Contbiner>(root);
            root.bddComponentListener(this);
            if (root instbnceof Window) {
                ((Window)root).bddWindowListener(this);
            }
        }

        public void setPbintAllOnExpose(boolebn pbintAllOnExpose) {
            this.pbintAllOnExpose = pbintAllOnExpose;
        }

        public boolebn getPbintAllOnExpose() {
            return pbintAllOnExpose;
        }

        public void setContentsLostDuringExpose(boolebn vblue) {
            contentsLostDuringExpose = vblue;
        }

        public boolebn getContentsLostDuringExpose() {
            return contentsLostDuringExpose;
        }

        public void setInSync(boolebn inSync) {
            this.inSync = inSync;
        }

        /**
         * Whether or not the contents of the buffer strbtegy
         * is in sync with the window.  This is set to true when the root
         * pbne pbints bll, bnd fblse when contents bre lost/restored.
         */
        public boolebn isInSync() {
            return inSync;
        }

        /**
         * Returns the Root (Window or Applet) thbt this BufferInfo references.
         */
        public Contbiner getRoot() {
            return (root == null) ? null : root.get();
        }

        /**
         * Returns the BufferStbrtegy.  This will return null if
         * the BufferStbrtegy hbsn't been crebted bnd <code>crebte</code> is
         * fblse, or if there is b problem in crebting the
         * <code>BufferStbrtegy</code>.
         *
         * @pbrbm crebte If true, bnd the BufferStbrtegy is currently null,
         *               one will be crebted.
         */
        public BufferStrbtegy getBufferStrbtegy(boolebn crebte) {
            BufferStrbtegy bs = (webkBS == null) ? null : webkBS.get();
            if (bs == null && crebte) {
                bs = crebteBufferStrbtegy();
                if (bs != null) {
                    webkBS = new WebkReference<BufferStrbtegy>(bs);
                }
                if (LOGGER.isLoggbble(PlbtformLogger.Level.FINER)) {
                    LOGGER.finer("getBufferStrbtegy: crebted bs: " + bs);
                }
            }
            return bs;
        }

        /**
         * Returns true if the buffer strbtegy of the component differs
         * from current buffer strbtegy.
         */
        public boolebn hbsBufferStrbtegyChbnged() {
            Contbiner root = getRoot();
            if (root != null) {
                BufferStrbtegy ourBS = null;
                BufferStrbtegy componentBS = null;

                ourBS = getBufferStrbtegy(fblse);
                if (root instbnceof Window) {
                    componentBS = ((Window)root).getBufferStrbtegy();
                }
                else {
                    componentBS = AWTAccessor.getComponentAccessor().getBufferStrbtegy(root);
                }
                if (componentBS != ourBS) {
                    // Component hbs b different BS, dispose ours.
                    if (ourBS != null) {
                        ourBS.dispose();
                    }
                    webkBS = null;
                    return true;
                }
            }
            return fblse;
        }

        /**
         * Crebtes the BufferStrbtegy.  If the bppropribte system property
         * hbs been set we'll try for flip first bnd then we'll try for
         * blit.
         */
        privbte BufferStrbtegy crebteBufferStrbtegy() {
            Contbiner root = getRoot();
            if (root == null) {
                return null;
            }
            BufferStrbtegy bs = null;
            if (SwingUtilities3.isVsyncRequested(root)) {
                bs = crebteBufferStrbtegy(root, true);
                if (LOGGER.isLoggbble(PlbtformLogger.Level.FINER)) {
                    LOGGER.finer("crebteBufferStrbtegy: using vsynced strbtegy");
                }
            }
            if (bs == null) {
                bs = crebteBufferStrbtegy(root, fblse);
            }
            if (!(bs instbnceof SubRegionShowbble)) {
                // We do this for two rebsons:
                // 1. So thbt we know we cbn cbst to SubRegionShowbble bnd
                //    invoke show with the minimbl region to updbte
                // 2. To bvoid the possibility of invoking client code
                //    on the toolkit threbd.
                bs = null;
            }
            return bs;
        }

        // Crebtes bnd returns b buffer strbtegy.  If
        // there is b problem crebting the buffer strbtegy this will
        // ebt the exception bnd return null.
        privbte BufferStrbtegy crebteBufferStrbtegy(Contbiner root,
                boolebn isVsynced) {
            BufferCbpbbilities cbps;
            if (isVsynced) {
                cbps = new ExtendedBufferCbpbbilities(
                    new ImbgeCbpbbilities(true), new ImbgeCbpbbilities(true),
                    BufferCbpbbilities.FlipContents.COPIED,
                    ExtendedBufferCbpbbilities.VSyncType.VSYNC_ON);
            } else {
                cbps = new BufferCbpbbilities(
                    new ImbgeCbpbbilities(true), new ImbgeCbpbbilities(true),
                    null);
            }
            BufferStrbtegy bs = null;
            if (SunToolkit.isInstbnceOf(root, "jbvb.bpplet.Applet")) {
                try {
                    AWTAccessor.ComponentAccessor componentAccessor
                            = AWTAccessor.getComponentAccessor();
                    componentAccessor.crebteBufferStrbtegy(root, 2, cbps);
                    bs = componentAccessor.getBufferStrbtegy(root);
                } cbtch (AWTException e) {
                    // Type is not supported
                    if (LOGGER.isLoggbble(PlbtformLogger.Level.FINER)) {
                        LOGGER.finer("crebteBufferStrbtety fbiled",
                                     e);
                    }
                }
            }
            else {
                try {
                    ((Window)root).crebteBufferStrbtegy(2, cbps);
                    bs = ((Window)root).getBufferStrbtegy();
                } cbtch (AWTException e) {
                    // Type not supported
                    if (LOGGER.isLoggbble(PlbtformLogger.Level.FINER)) {
                        LOGGER.finer("crebteBufferStrbtety fbiled",
                                     e);
                    }
                }
            }
            return bs;
        }

        /**
         * Clebns up bnd removes bny references.
         */
        public void dispose() {
            Contbiner root = getRoot();
            if (LOGGER.isLoggbble(PlbtformLogger.Level.FINER)) {
                LOGGER.finer("disposed BufferInfo for: " + root);
            }
            if (root != null) {
                root.removeComponentListener(this);
                if (root instbnceof Window) {
                    ((Window)root).removeWindowListener(this);
                }
                BufferStrbtegy bs = getBufferStrbtegy(fblse);
                if (bs != null) {
                    bs.dispose();
                }
            }
            this.root = null;
            webkBS = null;
        }

        // We mbrk the buffer bs needing to be pbinted on b hide/iconify
        // becbuse the developer mby hbve conditionblized pbinting bbsed on
        // visibility.
        // Ideblly we would blso move to hbving the BufferStrbtegy being
        // b SoftReference in Component here, but thbt requires chbnges to
        // Component bnd the like.
        public void componentHidden(ComponentEvent e) {
            Contbiner root = getRoot();
            if (root != null && root.isVisible()) {
                // This cbse will only hbppen if b developer cblls
                // hide immedibtely followed by show.  In this cbse
                // the event is delivered bfter show bnd the window
                // will still be visible.  If b developer bltered the
                // contents of the window between the hide/show
                // invocbtions we won't recognize we need to pbint bnd
                // the contents would be bogus.  Cblling repbint here
                // fixs everything up.
                root.repbint();
            }
            else {
                setPbintAllOnExpose(true);
            }
        }

        public void windowIconified(WindowEvent e) {
            setPbintAllOnExpose(true);
        }

        // On b dispose we chuck everything.
        public void windowClosed(WindowEvent e) {
            // Mbke sure we're not showing.
            synchronized(BufferStrbtegyPbintMbnbger.this) {
                while (showing) {
                    try {
                        BufferStrbtegyPbintMbnbger.this.wbit();
                    } cbtch (InterruptedException ie) {
                    }
                }
                bufferInfos.remove(this);
            }
            dispose();
        }

        public void windowOpened(WindowEvent e) {
        }

        public void windowClosing(WindowEvent e) {
        }

        public void windowDeiconified(WindowEvent e) {
        }

        public void windowActivbted(WindowEvent e) {
        }

        public void windowDebctivbted(WindowEvent e) {
        }
    }
}
