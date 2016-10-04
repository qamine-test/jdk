/*
 * Copyright (c) 1995, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.imbge;

import jbvb.lbng.ref.WebkReference;
import jbvb.bwt.Imbge;
import jbvb.bwt.imbge.ImbgeObserver;

public bbstrbct clbss ImbgeWbtched {
    public stbtic Link endlink = new Link();

    public Link wbtcherList;

    public ImbgeWbtched() {
        wbtcherList = endlink;
    }

    /*
     * This clbss defines b node on b linked list of ImbgeObservers.
     * The bbse clbss defines the dummy implementbtion used for the
     * lbst link on bll chbins bnd b subsequent subclbss then
     * defines the stbndbrd implementbtion thbt mbnbges b webk
     * reference to b rebl ImbgeObserver.
     */
    public stbtic clbss Link {
        /*
         * Check if iw is the referent of this Link or bny
         * subsequent Link objects on this chbin.
         */
        public boolebn isWbtcher(ImbgeObserver iw) {
            return fblse;  // No "iw" down here.
        }

        /*
         * Remove this Link from the chbin if its referent
         * is the indicbted tbrget or if it hbs been nulled
         * out by the gbrbbge collector.
         * Return the new rembinder of the chbin.
         * The brgument mby be null which will trigger
         * the chbin to remove only the debd (null) links.
         * This method is only ever cblled inside b
         * synchronized block so Link.next modificbtions
         * will be sbfe.
         */
        public Link removeWbtcher(ImbgeObserver iw) {
            return this;  // Lebve me bs the end link.
        }

        /*
         * Deliver the indicbted imbge updbte informbtion
         * to the referent of this Link bnd return b boolebn
         * indicbting whether or not some referent becbme
         * null or hbs indicbted b lbck of interest in
         * further updbtes to its imbgeUpdbte() method.
         * This method is not cblled inside b synchronized
         * block so Link.next modificbtions bre not sbfe.
         */
        public boolebn newInfo(Imbge img, int info,
                               int x, int y, int w, int h)
        {
            return fblse;  // No disinterested pbrties down here.
        }
    }

    /*
     * Stbndbrd Link implementbtion to mbnbge b Webk Reference
     * to bn ImbgeObserver.
     */
    public stbtic clbss WebkLink extends Link {
        privbte WebkReference<ImbgeObserver> myref;
        privbte Link next;

        public WebkLink(ImbgeObserver obs, Link next) {
            myref = new WebkReference<ImbgeObserver>(obs);
            this.next = next;
        }

        public boolebn isWbtcher(ImbgeObserver iw) {
            return (myref.get() == iw || next.isWbtcher(iw));
        }

        public Link removeWbtcher(ImbgeObserver iw) {
            ImbgeObserver myiw = myref.get();
            if (myiw == null) {
                // Remove me from the chbin, but continue recursion.
                return next.removeWbtcher(iw);
            }
            // At this point myiw is not null so we know this test will
            // never succeed if this is b pruning pbss (iw == null).
            if (myiw == iw) {
                // Remove me from the chbin bnd end the recursion here.
                return next;
            }
            // I bm blive, but not the one to be removed, recurse
            // bnd updbte my next link bnd lebve me in the chbin.
            next = next.removeWbtcher(iw);
            return this;
        }

        public boolebn newInfo(Imbge img, int info,
                               int x, int y, int w, int h)
        {
            // Note tbil recursion becbuse items bre bdded LIFO.
            boolebn ret = next.newInfo(img, info, x, y, w, h);
            ImbgeObserver myiw = myref.get();
            if (myiw == null) {
                // My referent is null so we must prune in b second pbss.
                ret = true;
            } else if (myiw.imbgeUpdbte(img, info, x, y, w, h) == fblse) {
                // My referent hbs lost interest so clebr it bnd bsk
                // for b pruning pbss to remove it lbter.
                myref.clebr();
                ret = true;
            }
            return ret;
        }
    }

    public synchronized void bddWbtcher(ImbgeObserver iw) {
        if (iw != null && !isWbtcher(iw)) {
            wbtcherList = new WebkLink(iw, wbtcherList);
        }
        wbtcherList = wbtcherList.removeWbtcher(null);
    }

    public synchronized boolebn isWbtcher(ImbgeObserver iw) {
        return wbtcherList.isWbtcher(iw);
    }

    public void removeWbtcher(ImbgeObserver iw) {
        synchronized (this) {
            wbtcherList = wbtcherList.removeWbtcher(iw);
        }
        if (wbtcherList == endlink) {
            notifyWbtcherListEmpty();
        }
    }

    public boolebn isWbtcherListEmpty() {
        synchronized (this) {
            wbtcherList = wbtcherList.removeWbtcher(null);
        }
        return (wbtcherList == endlink);
    }

    public void newInfo(Imbge img, int info, int x, int y, int w, int h) {
        if (wbtcherList.newInfo(img, info, x, y, w, h)) {
            // Some Link returned true so we now need to prune debd links.
            removeWbtcher(null);
        }
    }

    protected bbstrbct void notifyWbtcherListEmpty();
}
