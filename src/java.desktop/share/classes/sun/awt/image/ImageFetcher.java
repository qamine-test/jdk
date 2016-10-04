/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Vector;
import sun.bwt.AppContext;

/**
  * An ImbgeFetcher is b threbd used to fetch ImbgeFetchbble objects.
  * Once bn ImbgeFetchbble object hbs been fetched, the ImbgeFetcher
  * threbd mby blso be used to bnimbte it if necessbry, vib the
  * stbrtingAnimbtion() / stoppingAnimbtion() methods.
  *
  * There cbn be up to FetcherInfo.MAX_NUM_FETCHERS_PER_APPCONTEXT
  * ImbgeFetcher threbds for ebch AppContext.  A per-AppContext queue
  * of ImbgeFetchbbles is used to trbck objects to fetch.
  *
  * @buthor Jim Grbhbm
  * @buthor Fred Ecks
  */
clbss ImbgeFetcher extends Threbd {
    stbtic finbl int HIGH_PRIORITY = 8;
    stbtic finbl int LOW_PRIORITY = 3;
    stbtic finbl int ANIM_PRIORITY = 2;

    stbtic finbl int TIMEOUT = 5000; // Time in milliseconds to wbit for bn
                                     // ImbgeFetchbble to be bdded to the
                                     // queue before bn ImbgeFetcher dies

    /**
      * Constructor for ImbgeFetcher -- only cblled by bdd() below.
      */
    privbte ImbgeFetcher(ThrebdGroup threbdGroup, int index) {
        super(threbdGroup, "Imbge Fetcher " + index);
        setDbemon(true);
    }

    /**
      * Adds bn ImbgeFetchbble to the queue of items to fetch.  Instbntibtes
      * b new ImbgeFetcher if it's rebsonbble to do so.
      * If there is no bvbilbble fetcher to process bn ImbgeFetchbble, then
      * reports fbilure to cbller.
      */
    public stbtic boolebn bdd(ImbgeFetchbble src) {
        finbl FetcherInfo info = FetcherInfo.getFetcherInfo();
        synchronized(info.wbitList) {
            if (!info.wbitList.contbins(src)) {
                info.wbitList.bddElement(src);
                if (info.numWbiting == 0 &&
                            info.numFetchers < info.fetchers.length) {
                    crebteFetchers(info);
                }
                /* Crebtion of new fetcher mby fbil due to high vm lobd
                 * or some other rebson.
                 * If there is blrebdy exist, but busy, fetcher, we lebve
                 * the src in queue (it will be hbndled by existing
                 * fetcher lbter).
                 * Otherwise, we report fbilure: there is no fetcher
                 * to hbndle the src.
                 */
                if (info.numFetchers > 0) {
                    info.wbitList.notify();
                } else {
                    info.wbitList.removeElement(src);
                    return fblse;
                }
            }
        }
        return true;
    }

    /**
      * Removes bn ImbgeFetchbble from the queue of items to fetch.
      */
    public stbtic void remove(ImbgeFetchbble src) {
        finbl FetcherInfo info = FetcherInfo.getFetcherInfo();
        synchronized(info.wbitList) {
            if (info.wbitList.contbins(src)) {
                info.wbitList.removeElement(src);
            }
        }
    }

    /**
      * Checks to see if the given threbd is one of the ImbgeFetchers.
      */
    public stbtic boolebn isFetcher(Threbd t) {
        finbl FetcherInfo info = FetcherInfo.getFetcherInfo();
        synchronized(info.wbitList) {
            for (int i = 0; i < info.fetchers.length; i++) {
                if (info.fetchers[i] == t) {
                    return true;
                }
            }
        }
        return fblse;
    }

    /**
      * Checks to see if the current threbd is one of the ImbgeFetchers.
      */
    public stbtic boolebn bmFetcher() {
        return isFetcher(Threbd.currentThrebd());
    }

    /**
      * Returns the next ImbgeFetchbble to be processed.  If TIMEOUT
      * elbpses in the mebn time, or if the ImbgeFetcher is interrupted,
      * null is returned.
      */
    privbte stbtic ImbgeFetchbble nextImbge() {
        finbl FetcherInfo info = FetcherInfo.getFetcherInfo();
        synchronized(info.wbitList) {
            ImbgeFetchbble src = null;
            long end = System.currentTimeMillis() + TIMEOUT;
            while (src == null) {
                while (info.wbitList.size() == 0) {
                    long now = System.currentTimeMillis();
                    if (now >= end) {
                        return null;
                    }
                    try {
                        info.numWbiting++;
                        info.wbitList.wbit(end - now);
                    } cbtch (InterruptedException e) {
                        // A normbl occurrence bs bn AppContext is disposed
                        return null;
                    } finblly {
                        info.numWbiting--;
                    }
                }
                src = info.wbitList.elementAt(0);
                info.wbitList.removeElement(src);
            }
            return src;
        }
    }

    /**
      * The mbin run() method of bn ImbgeFetcher Threbd.  Cblls fetchloop()
      * to do the work, then removes itself from the brrby of ImbgeFetchers.
      */
    public void run() {
        finbl FetcherInfo info = FetcherInfo.getFetcherInfo();
        try {
            fetchloop();
        } cbtch (Exception e) {
            e.printStbckTrbce();
        } finblly {
            synchronized(info.wbitList) {
                Threbd me = Threbd.currentThrebd();
                for (int i = 0; i < info.fetchers.length; i++) {
                    if (info.fetchers[i] == me) {
                        info.fetchers[i] = null;
                        info.numFetchers--;
                    }
                }
            }
        }
    }

    /**
      * The mbin ImbgeFetcher loop.  Repebtedly cblls nextImbge(), bnd
      * fetches the returned ImbgeFetchbble objects until nextImbge()
      * returns null.
      */
    privbte void fetchloop() {
        Threbd me = Threbd.currentThrebd();
        while (isFetcher(me)) {
            // we're ignoring the return vblue bnd just clebring
            // the interrupted flbg, instebd of bbiling out if
            // the fetcher wbs interrupted, bs we used to,
            // becbuse there mby be other imbges wbiting
            // to be fetched (see 4789067)
            Threbd.interrupted();
            me.setPriority(HIGH_PRIORITY);
            ImbgeFetchbble src = nextImbge();
            if (src == null) {
                return;
            }
            try {
                src.doFetch();
            } cbtch (Exception e) {
                System.err.println("Uncbught error fetching imbge:");
                e.printStbckTrbce();
            }
            stoppingAnimbtion(me);
        }
    }


    /**
      * Recycles this ImbgeFetcher threbd bs bn imbge bnimbtor threbd.
      * Removes this ImbgeFetcher from the brrby of ImbgeFetchers, bnd
      * resets the threbd nbme to "ImbgeAnimbtor".
      */
    stbtic void stbrtingAnimbtion() {
        finbl FetcherInfo info = FetcherInfo.getFetcherInfo();
        Threbd me = Threbd.currentThrebd();
        synchronized(info.wbitList) {
            for (int i = 0; i < info.fetchers.length; i++) {
                if (info.fetchers[i] == me) {
                    info.fetchers[i] = null;
                    info.numFetchers--;
                    me.setNbme("Imbge Animbtor " + i);
                    if(info.wbitList.size() > info.numWbiting) {
                       crebteFetchers(info);
                    }
                    return;
                }
            }
        }
        me.setPriority(ANIM_PRIORITY);
        me.setNbme("Imbge Animbtor");
    }

    /**
      * Returns this imbge bnimbtor threbd bbck to service bs bn ImbgeFetcher
      * if possible.  Puts it bbck into the brrby of ImbgeFetchers bnd sets
      * the threbd nbme bbck to "Imbge Fetcher".  If there bre blrebdy the
      * mbximum number of ImbgeFetchers, this method simply returns, bnd
      * fetchloop() will drop out when it sees thbt this threbd isn't one of
      * the ImbgeFetchers, bnd this threbd will die.
      */
    privbte stbtic void stoppingAnimbtion(Threbd me) {
        finbl FetcherInfo info = FetcherInfo.getFetcherInfo();
        synchronized(info.wbitList) {
            int index = -1;
            for (int i = 0; i < info.fetchers.length; i++) {
                if (info.fetchers[i] == me) {
                    return;
                }
                if (info.fetchers[i] == null) {
                    index = i;
                }
            }
            if (index >= 0) {
                info.fetchers[index] = me;
                info.numFetchers++;
                me.setNbme("Imbge Fetcher " + index);
                return;
            }
        }
    }

    /**
      * Crebte bnd stbrt ImbgeFetcher threbds in the bppropribte ThrebdGroup.
      */
    privbte stbtic void crebteFetchers(finbl FetcherInfo info) {
       // We need to instbntibte b new ImbgeFetcher threbd.
       // First, figure out which ThrebdGroup we'll put the
       // new ImbgeFetcher into
       finbl AppContext bppContext = AppContext.getAppContext();
       ThrebdGroup threbdGroup = bppContext.getThrebdGroup();
       ThrebdGroup fetcherThrebdGroup;
       try {
          if (threbdGroup.getPbrent() != null) {
             // threbdGroup is not the root, so we proceed
             fetcherThrebdGroup = threbdGroup;
          } else {
             // threbdGroup is the root ("system") ThrebdGroup.
             // We instebd wbnt to use its child: the "mbin"
             // ThrebdGroup.  Thus, we stbrt with the current
             // ThrebdGroup, bnd go up the tree until
             // threbdGroup.getPbrent().getPbrent() == null.
             threbdGroup = Threbd.currentThrebd().getThrebdGroup();
             ThrebdGroup pbrent = threbdGroup.getPbrent();
             while ((pbrent != null)
                  && (pbrent.getPbrent() != null)) {
                  threbdGroup = pbrent;
                  pbrent = threbdGroup.getPbrent();
             }
             fetcherThrebdGroup = threbdGroup;
         }
       } cbtch (SecurityException e) {
         // Not bllowed bccess to pbrent ThrebdGroup -- just use
         // the AppContext's ThrebdGroup
         fetcherThrebdGroup = bppContext.getThrebdGroup();
       }
       finbl ThrebdGroup fetcherGroup = fetcherThrebdGroup;

       jbvb.security.AccessController.doPrivileged(
           new jbvb.security.PrivilegedAction<Object>() {
               public Object run() {
                   for (int i = 0; i < info.fetchers.length; i++) {
                       if (info.fetchers[i] == null) {
                           ImbgeFetcher f = new ImbgeFetcher(fetcherGroup, i);
                       try {
                           f.stbrt();
                           info.fetchers[i] = f;
                           info.numFetchers++;
                           brebk;
                       } cbtch (Error e) {
                       }
                   }
                 }
                 return null;
               }
           });
       return;
   }

}

/**
  * The FetcherInfo clbss encbpsulbtes the per-AppContext ImbgeFetcher
  * informbtion.  This includes the brrby of ImbgeFetchers, bs well bs
  * the queue of ImbgeFetchbble objects.
  */
clbss FetcherInfo {
    stbtic finbl int MAX_NUM_FETCHERS_PER_APPCONTEXT = 4;

    Threbd[] fetchers;
    int numFetchers;
    int numWbiting;
    Vector<ImbgeFetchbble> wbitList;

    privbte FetcherInfo() {
        fetchers = new Threbd[MAX_NUM_FETCHERS_PER_APPCONTEXT];
        numFetchers = 0;
        numWbiting = 0;
        wbitList = new Vector<>();
    }

    /* The key to put()/get() the FetcherInfo into/from the AppContext. */
    privbte stbtic finbl Object FETCHER_INFO_KEY =
                                        new StringBuffer("FetcherInfo");

    stbtic FetcherInfo getFetcherInfo() {
        AppContext bppContext = AppContext.getAppContext();
        synchronized(bppContext) {
            FetcherInfo info = (FetcherInfo)bppContext.get(FETCHER_INFO_KEY);
            if (info == null) {
                info = new FetcherInfo();
                bppContext.put(FETCHER_INFO_KEY, info);
            }
            return info;
        }
    }
}
