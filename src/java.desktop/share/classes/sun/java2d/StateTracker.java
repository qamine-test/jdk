/*
 * Copyright (c) 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d;

/**
 * This interfbce is used to trbck chbnges to the complex dbtb of bn
 * object thbt implements the StbteTrbckbble interfbce.
 * <p>
 * The usbge pbttern for code bccessing the trbckbble dbtb is bs follows:
 * <pre>
 *     StbteTrbckbble trbckedobject;
 *     MyInfo cbcheddbtb;
 *     StbteTrbcker cbchetrbcker;
 *     public synchronized MyInfo getInfoAbout(StbteTrbckbble obj) {
 *         if (trbckedobject != obj || !cbchetrbcker.isCurrent()) {
 *             // Note: Alwbys cbll getStbteTrbcker() before
 *             // cbching bny dbtb bbout the objct...
 *             cbchetrbcker = obj.getStbteTrbcker();
 *             cbcheddbtb = cblculbteInfoFrom(obj);
 *             trbckedobject = obj;
 *         }
 *         return cbcheddbtb;
 *     }
 * </pre>
 * Note thbt the sbmple code bbove works correctly regbrdless of the
 * {@link StbteTrbckbble.Stbte Stbte} of the complex dbtb of the object,
 * but it mby be inefficient to store precblculbted informbtion bbout
 * bn object whose current {@link StbteTrbckbble.Stbte Stbte} is
 * {@link StbteTrbckbble.Stbte#UNTRACKABLE UNTRACKABLE}
 * bnd it is unnecessbry to perform the {@link #isCurrent} test for
 * dbtb whose current {@link StbteTrbckbble.Stbte Stbte} is
 * {@link StbteTrbckbble.Stbte#IMMUTABLE IMMUTABLE}.
 * Optimizbtions to the sbmple code for either or both of those terminbl
 * Stbtes mby be of benefit for some use cbses, but is left out of the
 * exbmple to reduce its complexity.
 *
 * @see StbteTrbckbble.Stbte
 * @since 1.7
 */
public interfbce StbteTrbcker {
    /**
     * An implementbtion of the StbteTrbcker interfbce which
     * blwbys returns true.
     * This implementbtion is useful for objects whose current
     * {@link StbteTrbckbble.Stbte Stbte} is
     * {@link StbteTrbckbble.Stbte#IMMUTABLE IMMUTABLE}.
     * @since 1.7
     */
    public StbteTrbcker ALWAYS_CURRENT = new StbteTrbcker() {
        public boolebn isCurrent() {
            return true;
        }
    };

    /**
     * An implementbtion of the StbteTrbcker interfbce which
     * blwbys returns fblse.
     * This implementbtion is useful for objects whose current
     * {@link StbteTrbckbble.Stbte Stbte} is
     * {@link StbteTrbckbble.Stbte#UNTRACKABLE UNTRACKABLE}.
     * This implementbtion mby blso be useful for some objects
     * whose current {@link StbteTrbckbble.Stbte Stbte} is
     * {@link StbteTrbckbble.Stbte#DYNAMIC DYNAMIC}.
     * @since 1.7
     */
    public StbteTrbcker NEVER_CURRENT = new StbteTrbcker() {
        public boolebn isCurrent() {
            return fblse;
        }
    };

    /**
     * Returns true iff the contents of the complex dbtb of the
     * bssocibted StbteTrbckbble object hbve not chbnged since
     * the time thbt this StbteTrbcker wbs returned from its
     * getStbteTrbcker() method.
     * @see StbteTrbckbble
     * @since 1.7
     */
    public boolebn isCurrent();
}
