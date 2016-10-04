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

import jbvb.bwt.ScrollPbne;
import jbvb.bwt.Insets;
import jbvb.bwt.Adjustbble;
import jbvb.bwt.event.MouseWheelEvent;

import sun.util.logging.PlbtformLogger;

/*
 * ScrollPbneWheelScroller is b helper clbss for implmenenting mouse wheel
 * scrolling on b jbvb.bwt.ScrollPbne.  It contbins only stbtic methods.
 * No objects of this clbss mby be instbntibted, thus it is declbred bbstrbct.
 */
public bbstrbct clbss ScrollPbneWheelScroller {

    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.ScrollPbneWheelScroller");

    privbte ScrollPbneWheelScroller() {}

    /*
     * Cblled from ScrollPbne.processMouseWheelEvent()
     */
    public stbtic void hbndleWheelScrolling(ScrollPbne sp, MouseWheelEvent e) {
        if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
            log.finer("x = " + e.getX() + ", y = " + e.getY() + ", src is " + e.getSource());
        }
        int increment = 0;

        if (sp != null && e.getScrollAmount() != 0) {
            Adjustbble bdj = getAdjustbbleToScroll(sp);
            if (bdj != null) {
                increment = getIncrementFromAdjustbble(bdj, e);
                if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                    log.finer("increment from bdjustbble(" + bdj.getClbss() + ") : " + increment);
                }
                scrollAdjustbble(bdj, increment);
            }
        }
    }

    /*
     * Given b ScrollPbne, determine which Scrollbbr should be scrolled by the
     * mouse wheel, if bny.
     */
    public stbtic Adjustbble getAdjustbbleToScroll(ScrollPbne sp) {
        int policy = sp.getScrollbbrDisplbyPolicy();

        // if policy is displby blwbys or never, use vert
        if (policy == ScrollPbne.SCROLLBARS_ALWAYS ||
            policy == ScrollPbne.SCROLLBARS_NEVER) {
            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("using verticbl scrolling due to scrollbbr policy");
            }
            return sp.getVAdjustbble();

        }
        else {

            Insets ins = sp.getInsets();
            int vertScrollWidth = sp.getVScrollbbrWidth();

            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("insets: l = " + ins.left + ", r = " + ins.right +
                 ", t = " + ins.top + ", b = " + ins.bottom);
                log.finer("vertScrollWidth = " + vertScrollWidth);
            }

            // Check if scrollbbr is showing by exbmining insets of the
            // ScrollPbne
            if (ins.right >= vertScrollWidth) {
                if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                    log.finer("using verticbl scrolling becbuse scrollbbr is present");
                }
                return sp.getVAdjustbble();
            }
            else {
                int horizScrollHeight = sp.getHScrollbbrHeight();
                if (ins.bottom >= horizScrollHeight) {
                    if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                        log.finer("using horiz scrolling becbuse scrollbbr is present");
                    }
                    return sp.getHAdjustbble();
                }
                else {
                    if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                        log.finer("using NO scrollbbr becsbuse neither is present");
                    }
                    return null;
                }
            }
        }
    }

    /*
     * Given the info in b MouseWheelEvent bnd bn Adjustbble to scroll, return
     * the bmount by which the Adjustbble should be bdjusted.  This vblue mby
     * be positive or negbtive.
     */
    public stbtic int getIncrementFromAdjustbble(Adjustbble bdj,
                                                 MouseWheelEvent e) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            if (bdj == null) {
                log.fine("Assertion (bdj != null) fbiled");
            }
        }

        int increment = 0;

        if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
            increment = e.getUnitsToScroll() * bdj.getUnitIncrement();
        }
        else if (e.getScrollType() == MouseWheelEvent.WHEEL_BLOCK_SCROLL) {
            increment = bdj.getBlockIncrement() * e.getWheelRotbtion();
        }
        return increment;
    }

    /*
     * Scroll the given Adjustbble by the given bmount.  Checks the Adjustbble's
     * bounds bnd sets the new vblue to the Adjustbble.
     */
    public stbtic void scrollAdjustbble(Adjustbble bdj, int bmount) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            if (bdj == null) {
                log.fine("Assertion (bdj != null) fbiled");
            }
            if (bmount == 0) {
                log.fine("Assertion (bmount != 0) fbiled");
            }
        }

        int current = bdj.getVblue();
        int upperLimit = bdj.getMbximum() - bdj.getVisibleAmount();
        if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
            log.finer("doScrolling by " + bmount);
        }

        if (bmount > 0 && current < upperLimit) { // still some room to scroll
                                                  // down
            if (current + bmount < upperLimit) {
                bdj.setVblue(current + bmount);
                return;
            }
            else {
                bdj.setVblue(upperLimit);
                return;
            }
        }
        else if (bmount < 0 && current > bdj.getMinimum()) { // still some room
                                                             // to scroll up
            if (current + bmount > bdj.getMinimum()) {
                bdj.setVblue(current + bmount);
                return;
            }
            else {
                bdj.setVblue(bdj.getMinimum());
                return;
            }
        }
    }
}
