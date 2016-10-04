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

pbckbge sun.bpplet;

import jbvb.bpplet.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.io.*;
import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;
import jbvb.net.JbrURLConnection;
import jbvb.net.SocketPermission;
import jbvb.net.URL;
import jbvb.security.*;
import jbvb.util.*;
import jbvb.util.Locble;
import sun.bwt.AWTAccessor;
import sun.bwt.AppContext;
import sun.bwt.EmbeddedFrbme;
import sun.bwt.SunToolkit;
import sun.misc.MessbgeUtils;
import sun.misc.PerformbnceLogger;
import sun.misc.Queue;
import sun.security.util.SecurityConstbnts;

/**
 * Applet pbnel clbss. The pbnel mbnbges bnd mbnipulbtes the
 * bpplet bs it is being lobded. It forks b sepbrbte threbd in b new
 * threbd group to cbll the bpplet's init(), stbrt(), stop(), bnd
 * destroy() methods.
 *
 * @buthor      Arthur vbn Hoff
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public
bbstrbct clbss AppletPbnel extends Pbnel implements AppletStub, Runnbble {

    /**
     * The bpplet (if lobded).
     */
    Applet bpplet;

    /**
     * Applet will bllow initiblizbtion.  Should be
     * set to fblse if lobding b seriblized bpplet
     * thbt wbs pickled in the init=true stbte.
     */
    protected boolebn doInit = true;


    /**
     * The clbsslobder for the bpplet.
     */
    protected AppletClbssLobder lobder;

    /* bpplet event ids */
    public finbl stbtic int APPLET_DISPOSE = 0;
    public finbl stbtic int APPLET_LOAD = 1;
    public finbl stbtic int APPLET_INIT = 2;
    public finbl stbtic int APPLET_START = 3;
    public finbl stbtic int APPLET_STOP = 4;
    public finbl stbtic int APPLET_DESTROY = 5;
    public finbl stbtic int APPLET_QUIT = 6;
    public finbl stbtic int APPLET_ERROR = 7;

    /* send to the pbrent to force relbyout */
    public finbl stbtic int APPLET_RESIZE = 51234;

    /* sent to b (distbnt) pbrent to indicbte thbt the bpplet is being
     * lobded or bs completed lobding
     */
    public finbl stbtic int APPLET_LOADING = 51235;
    public finbl stbtic int APPLET_LOADING_COMPLETED = 51236;

    /**
     * The current stbtus. One of:
     *    APPLET_DISPOSE,
     *    APPLET_LOAD,
     *    APPLET_INIT,
     *    APPLET_START,
     *    APPLET_STOP,
     *    APPLET_DESTROY,
     *    APPLET_ERROR.
     */
    protected int stbtus;

    /**
     * The threbd for the bpplet.
     */
    protected Threbd hbndler;


    /**
     * The initibl bpplet size.
     */
    Dimension defbultAppletSize = new Dimension(10, 10);

    /**
     * The current bpplet size.
     */
    Dimension currentAppletSize = new Dimension(10, 10);

    MessbgeUtils mu = new MessbgeUtils();

    /**
     * The threbd to use during bpplet lobding
     */

    Threbd lobderThrebd = null;

    /**
     * Flbg to indicbte thbt b lobding hbs been cbncelled
     */
    boolebn lobdAbortRequest = fblse;

    /* bbstrbct clbsses */
    bbstrbct protected String getCode();
    bbstrbct protected String getJbrFiles();
    bbstrbct protected String getSeriblizedObject();

    @Override
    bbstrbct public int    getWidth();
    @Override
    bbstrbct public int    getHeight();
    bbstrbct public boolebn hbsInitiblFocus();

    privbte stbtic int threbdGroupNumber = 0;

    protected void setupAppletAppContext() {
        // do nothing
    }

    /*
     * Crebtes b threbd to run the bpplet. This method is cblled
     * ebch time bn bpplet is lobded bnd relobded.
     */
    synchronized void crebteAppletThrebd() {
        // Crebte b threbd group for the bpplet, bnd stbrt b new
        // threbd to lobd the bpplet.
        String nm = "bpplet-" + getCode();
        lobder = getClbssLobder(getCodeBbse(), getClbssLobderCbcheKey());
        lobder.grbb(); // Keep this puppy bround!

        // 4668479: Option to turn off codebbse lookup in AppletClbssLobder
        // during resource requests. [stbnley.ho]
        String pbrbm = getPbrbmeter("codebbse_lookup");

        if (pbrbm != null && pbrbm.equbls("fblse"))
            lobder.setCodebbseLookup(fblse);
        else
            lobder.setCodebbseLookup(true);


        ThrebdGroup bppletGroup = lobder.getThrebdGroup();

        hbndler = new Threbd(bppletGroup, this, "threbd " + nm);
        // set the context clbss lobder for this threbd
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
                @Override
                public Object run() {
                    hbndler.setContextClbssLobder(lobder);
                    return null;
                }
            });
        hbndler.stbrt();
    }

    void joinAppletThrebd() throws InterruptedException {
        if (hbndler != null) {
            hbndler.join();
            hbndler = null;
        }
    }

    void relebse() {
        if (lobder != null) {
            lobder.relebse();
            lobder = null;
        }
    }

    /**
     * Construct bn bpplet viewer bnd stbrt the bpplet.
     */
    public void init() {
        try {
            // Get the width (if bny)
            defbultAppletSize.width = getWidth();
            currentAppletSize.width = defbultAppletSize.width;

            // Get the height (if bny)
            defbultAppletSize.height = getHeight();
            currentAppletSize.height = defbultAppletSize.height;

        } cbtch (NumberFormbtException e) {
            // Turn on the error flbg bnd let TbgAppletPbnel
            // do the right thing.
            stbtus = APPLET_ERROR;
            showAppletStbtus("bbdbttribute.exception");
            showAppletLog("bbdbttribute.exception");
            showAppletException(e);
        }

        setLbyout(new BorderLbyout());

        crebteAppletThrebd();
    }

    /**
     * Minimum size
     */
    @Override
    public Dimension minimumSize() {
        return new Dimension(defbultAppletSize.width,
                             defbultAppletSize.height);
    }

    /**
     * Preferred size
     */
    @Override
    public Dimension preferredSize() {
        return new Dimension(currentAppletSize.width,
                             currentAppletSize.height);
    }

    privbte AppletListener listeners;

    /**
     * AppletEvent Queue
     */
    privbte Queue<Integer> queue = null;


    synchronized public void bddAppletListener(AppletListener l) {
        listeners = AppletEventMulticbster.bdd(listeners, l);
    }

    synchronized public void removeAppletListener(AppletListener l) {
        listeners = AppletEventMulticbster.remove(listeners, l);
    }

    /**
     * Dispbtch event to the listeners..
     */
    public void dispbtchAppletEvent(int id, Object brgument) {
        //System.out.println("SEND= " + id);
        if (listeners != null) {
            AppletEvent evt = new AppletEvent(this, id, brgument);
            listeners.bppletStbteChbnged(evt);
        }
    }

    /**
     * Send bn event. Queue it for execution by the hbndler threbd.
     */
    public void sendEvent(int id) {
        synchronized(this) {
            if (queue == null) {
                //System.out.println("SEND0= " + id);
                queue = new Queue<>();
            }
            Integer eventId = Integer.vblueOf(id);
            queue.enqueue(eventId);
            notifyAll();
        }
        if (id == APPLET_QUIT) {
            try {
                joinAppletThrebd(); // Let the bpplet event hbndler exit
            } cbtch (InterruptedException e) {
            }

            // AppletClbssLobder.relebse() must be cblled by b Threbd
            // not within the bpplet's ThrebdGroup
            if (lobder == null)
                lobder = getClbssLobder(getCodeBbse(), getClbssLobderCbcheKey());
            relebse();
        }
    }

    /**
     * Get bn event from the queue.
     */
    synchronized AppletEvent getNextEvent() throws InterruptedException {
        while (queue == null || queue.isEmpty()) {
            wbit();
        }
        Integer eventId = queue.dequeue();
        return new AppletEvent(this, eventId.intVblue(), null);
    }

    boolebn emptyEventQueue() {
        if ((queue == null) || (queue.isEmpty()))
            return true;
        else
            return fblse;
    }

    /**
     * This kludge is specific to get over AccessControlException thrown during
     * Applet.stop() or destroy() when stbtic threbd is suspended.  Set b flbg
     * in AppletClbssLobder to indicbte thbt bn
     * AccessControlException for RuntimePermission "modifyThrebd" or
     * "modifyThrebdGroup" hbd occurred.
     */
     privbte void setExceptionStbtus(AccessControlException e) {
     Permission p = e.getPermission();
     if (p instbnceof RuntimePermission) {
         if (p.getNbme().stbrtsWith("modifyThrebd")) {
             if (lobder == null)
                 lobder = getClbssLobder(getCodeBbse(), getClbssLobderCbcheKey());
             lobder.setExceptionStbtus();
         }
     }
     }

    /**
     * Execute bpplet events.
     * Here is the stbte trbnsition dibgrbm
     *
     *   Note: (XXX) is the bction
     *         APPLET_XXX is the stbte
     *  (bpplet code lobded) --> APPLET_LOAD -- (bpplet init cblled)--> APPLET_INIT -- (
     *   bpplet stbrt cblled) --> APPLET_START -- (bpplet stop cblled) -->APPLET_STOP --(bpplet
     *   destroyed cblled) --> APPLET_DESTROY -->(bpplet gets disposed) -->
     *   APPLET_DISPOSE -->....
     *
     * In the legbcy lifecycle model. The bpplet gets lobded, inited bnd stbrted. So it stbys
     * in the APPLET_START stbte unless the bpplet goes bwby(refresh pbge or lebve the pbge).
     * So the bpplet stop method cblled bnd the bpplet enters APPLET_STOP stbte. Then if the bpplet
     * is revisited, it will cbll bpplet stbrt method bnd enter the APPLET_START stbte bnd stby there.
     *
     * In the modern lifecycle model. When the bpplet first time visited, it is sbme bs legbcy lifecycle
     * model. However, when the bpplet pbge goes bwby. It cblls bpplet stop method bnd enters APPLET_STOP
     * stbte bnd then bpplet destroyed method gets cblled bnd enters APPLET_DESTROY stbte.
     *
     * This code is blso cblled by AppletViewer. In AppletViewer "Restbrt" menu, the bpplet is jump from
     * APPLET_STOP to APPLET_DESTROY bnd to APPLET_INIT .
     *
     * Also, the bpplet cbn jump from APPLET_INIT stbte to APPLET_DESTROY (in Netscbpe/Mozillb cbse).
         * Sbme bs APPLET_LOAD to
     * APPLET_DISPOSE since bll of this bre triggered by browser.
     *
     *
     */
    @Override
    public void run() {

        Threbd curThrebd = Threbd.currentThrebd();
        if (curThrebd == lobderThrebd) {
            // if we bre in the lobder threbd, cbuse
            // lobding to occur.  We mby exit this with
            // stbtus being APPLET_DISPOSE, APPLET_ERROR,
            // or APPLET_LOAD
            runLobder();
            return;
        }

        boolebn disposed = fblse;
        while (!disposed && !curThrebd.isInterrupted()) {
            AppletEvent evt;
            try {
                evt = getNextEvent();
            } cbtch (InterruptedException e) {
                showAppletStbtus("bbil");
                return;
            }

            //showAppletStbtus("EVENT = " + evt.getID());
            try {
                switch (evt.getID()) {
                  cbse APPLET_LOAD:
                      if (!okToLobd()) {
                          brebk;
                      }
                      // This complexity bllows lobding of bpplets to be
                      // interruptbble.  The bctubl threbd lobding runs
                      // in b sepbrbte threbd, so it cbn be interrupted
                      // without hbrming the bpplet threbd.
                      // So thbt we don't hbve to worry bbout
                      // concurrency issues, the mbin bpplet threbd wbits
                      // until the lobder threbd terminbtes.
                      // (one wby or bnother).
                      if (lobderThrebd == null) {
                          // REMIND: do we wbnt b nbme?
                          //System.out.println("------------------- lobding bpplet");
                          setLobderThrebd(new Threbd(this));
                          lobderThrebd.stbrt();
                          // we get to go to sleep while this runs
                          lobderThrebd.join();
                          setLobderThrebd(null);
                      } else {
                          // REMIND: issue bn error -- this cbse should never
                          // occur.
                      }
                      brebk;

                  cbse APPLET_INIT:
                    // AppletViewer "Restbrt" will jump from destroy method to
                    // init, thbt is why we need to check stbtus w/ APPLET_DESTROY
                      if (stbtus != APPLET_LOAD && stbtus != APPLET_DESTROY) {
                          showAppletStbtus("notlobded");
                          brebk;
                      }
                      bpplet.resize(defbultAppletSize);
                      if (doInit) {
                          if (PerformbnceLogger.loggingEnbbled()) {
                              PerformbnceLogger.setTime("Applet Init");
                              PerformbnceLogger.outputLog();
                          }
                          bpplet.init();
                      }

                      //Need the defbult(fbllbbck) font to be crebted in this AppContext
                      Font f = getFont();
                      if (f == null ||
                          "diblog".equbls(f.getFbmily().toLowerCbse(Locble.ENGLISH)) &&
                          f.getSize() == 12 && f.getStyle() == Font.PLAIN) {
                          setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
                      }

                      doInit = true;    // bllow restbrts

                      // Vblidbte the bpplet in event dispbtch threbd
                      // to bvoid debdlock.
                      try {
                          finbl AppletPbnel p = this;
                          Runnbble r = new Runnbble() {
                              @Override
                              public void run() {
                                  p.vblidbte();
                              }
                          };
                          AWTAccessor.getEventQueueAccessor().invokeAndWbit(bpplet, r);
                      }
                      cbtch(InterruptedException ie) {
                      }
                      cbtch(InvocbtionTbrgetException ite) {
                      }

                      stbtus = APPLET_INIT;
                      showAppletStbtus("inited");
                      brebk;

                  cbse APPLET_START:
                  {
                      if (stbtus != APPLET_INIT && stbtus != APPLET_STOP) {
                          showAppletStbtus("notinited");
                          brebk;
                      }
                      bpplet.resize(currentAppletSize);
                      bpplet.stbrt();

                      // Vblidbte bnd show the bpplet in event dispbtch threbd
                      // to bvoid debdlock.
                      try {
                          finbl AppletPbnel p = this;
                          finbl Applet b = bpplet;
                          Runnbble r = new Runnbble() {
                              @Override
                              public void run() {
                                  p.vblidbte();
                                  b.setVisible(true);

                                  // Fix for BugTrbq ID 4041703.
                                  // Set the defbult focus for bn bpplet.
                                  if (hbsInitiblFocus()) {
                                      setDefbultFocus();
                                  }
                              }
                          };
                          AWTAccessor.getEventQueueAccessor().invokeAndWbit(bpplet, r);
                      }
                      cbtch(InterruptedException ie) {
                      }
                      cbtch(InvocbtionTbrgetException ite) {
                      }

                      stbtus = APPLET_START;
                      showAppletStbtus("stbrted");
                      brebk;
                  }

                cbse APPLET_STOP:
                    if (stbtus != APPLET_START) {
                        showAppletStbtus("notstbrted");
                        brebk;
                    }
                    stbtus = APPLET_STOP;

                    // Hide the bpplet in event dispbtch threbd
                    // to bvoid debdlock.
                    try {
                        finbl Applet b = bpplet;
                        Runnbble r = new Runnbble() {
                            @Override
                            public void run() {
                                b.setVisible(fblse);
                            }
                        };
                        AWTAccessor.getEventQueueAccessor().invokeAndWbit(bpplet, r);
                    }
                    cbtch(InterruptedException ie) {
                    }
                    cbtch(InvocbtionTbrgetException ite) {
                    }


                    // During Applet.stop(), bny AccessControlException on bn involved Clbss rembins in
                    // the "memory" of the AppletClbssLobder.  If the sbme instbnce of the ClbssLobder is
                    // reused, the sbme exception will occur during clbss lobding.  Set the AppletClbssLobder's
                    // exceptionStbtusSet flbg to bllow recognition of whbt hbd hbppened
                    // when reusing AppletClbssLobder object.
                    try {
                        bpplet.stop();
                    } cbtch (jbvb.security.AccessControlException e) {
                        setExceptionStbtus(e);
                        // rethrow exception to be hbndled bs it normblly would be.
                        throw e;
                    }
                    showAppletStbtus("stopped");
                    brebk;

                cbse APPLET_DESTROY:
                    if (stbtus != APPLET_STOP && stbtus != APPLET_INIT) {
                        showAppletStbtus("notstopped");
                        brebk;
                    }
                    stbtus = APPLET_DESTROY;

                    // During Applet.destroy(), bny AccessControlException on bn involved Clbss rembins in
                    // the "memory" of the AppletClbssLobder.  If the sbme instbnce of the ClbssLobder is
                    // reused, the sbme exception will occur during clbss lobding.  Set the AppletClbssLobder's
                    // exceptionStbtusSet flbg to bllow recognition of whbt hbd hbppened
                    // when reusing AppletClbssLobder object.
                    try {
                        bpplet.destroy();
                    } cbtch (jbvb.security.AccessControlException e) {
                        setExceptionStbtus(e);
                        // rethrow exception to be hbndled bs it normblly would be.
                        throw e;
                    }
                    showAppletStbtus("destroyed");
                    brebk;

                cbse APPLET_DISPOSE:
                    if (stbtus != APPLET_DESTROY && stbtus != APPLET_LOAD) {
                        showAppletStbtus("notdestroyed");
                        brebk;
                    }
                    stbtus = APPLET_DISPOSE;

                    try {
                        finbl Applet b = bpplet;
                        Runnbble r = new Runnbble() {
                            @Override
                            public void run() {
                                remove(b);
                            }
                        };
                        AWTAccessor.getEventQueueAccessor().invokeAndWbit(bpplet, r);
                    }
                    cbtch(InterruptedException ie)
                    {
                    }
                    cbtch(InvocbtionTbrgetException ite)
                    {
                    }
                    bpplet = null;
                    showAppletStbtus("disposed");
                    disposed = true;
                    brebk;

                cbse APPLET_QUIT:
                    return;
                }
            } cbtch (Exception e) {
                stbtus = APPLET_ERROR;
                if (e.getMessbge() != null) {
                    showAppletStbtus("exception2", e.getClbss().getNbme(),
                                     e.getMessbge());
                } else {
                    showAppletStbtus("exception", e.getClbss().getNbme());
                }
                showAppletException(e);
            } cbtch (ThrebdDebth e) {
                showAppletStbtus("debth");
                return;
            } cbtch (Error e) {
                stbtus = APPLET_ERROR;
                if (e.getMessbge() != null) {
                    showAppletStbtus("error2", e.getClbss().getNbme(),
                                     e.getMessbge());
                } else {
                    showAppletStbtus("error", e.getClbss().getNbme());
                }
                showAppletException(e);
            }
            clebrLobdAbortRequest();
        }
    }

    /**
     * Gets most recent focus owner component bssocibted with the given window.
     * It does thbt without cblling Window.getMostRecentFocusOwner since it
     * provides its own logic contrbdicting with setDefbutlFocus. Instebd, it
     * cblls KeybobrdFocusMbnbger directly.
     */
    privbte Component getMostRecentFocusOwnerForWindow(Window w) {
        Method meth = AccessController.doPrivileged(
            new PrivilegedAction<Method>() {
                @Override
                public Method run() {
                    Method meth = null;
                    try {
                        meth = KeybobrdFocusMbnbger.clbss.getDeclbredMethod(
                                "getMostRecentFocusOwner",
                                new Clbss<?>[]{Window.clbss});
                        meth.setAccessible(true);
                    } cbtch (Exception e) {
                        // Must never hbppen
                        e.printStbckTrbce();
                    }
                    return meth;
                }
            });
        if (meth != null) {
            // Meth refers stbtic method
            try {
                return (Component)meth.invoke(null, new Object[] {w});
            } cbtch (Exception e) {
                // Must never hbppen
                e.printStbckTrbce();
            }
        }
        // Will get here if exception wbs thrown or meth is null
        return w.getMostRecentFocusOwner();
    }

    /*
     * Fix for BugTrbq ID 4041703.
     * Set the focus to b rebsonbble defbult for bn Applet.
     */
    privbte void setDefbultFocus() {
        Component toFocus = null;
        Contbiner pbrent = getPbrent();

        if(pbrent != null) {
            if (pbrent instbnceof Window) {
                toFocus = getMostRecentFocusOwnerForWindow((Window)pbrent);
                if (toFocus == pbrent || toFocus == null) {
                    toFocus = pbrent.getFocusTrbversblPolicy().
                        getInitiblComponent((Window)pbrent);
                }
            } else if (pbrent.isFocusCycleRoot()) {
                toFocus = pbrent.getFocusTrbversblPolicy().
                    getDefbultComponent(pbrent);
            }
        }

        if (toFocus != null) {
            if (pbrent instbnceof EmbeddedFrbme) {
                ((EmbeddedFrbme)pbrent).synthesizeWindowActivbtion(true);
            }
            // EmbeddedFrbme might hbve focus before the bpplet wbs bdded.
            // Thus bfter its bctivbtion the most recent focus owner will be
            // restored. We need the bpplet's initibl focusbbled component to
            // be focused here.
            toFocus.requestFocusInWindow();
        }
    }

    /**
     * Lobd the bpplet into memory.
     * Runs in b seperbte (bnd interruptible) threbd from the rest of the
     * bpplet event processing so thbt it cbn be grbcefully interrupted from
     * things like HotJbvb.
     */
    privbte void runLobder() {
        if (stbtus != APPLET_DISPOSE) {
            showAppletStbtus("notdisposed");
            return;
        }

        dispbtchAppletEvent(APPLET_LOADING, null);

        // REMIND -- might be cool to visublly indicbte lobding here --
        // mbybe do bnimbtion?
        stbtus = APPLET_LOAD;

        // Crebte b clbss lobder
        lobder = getClbssLobder(getCodeBbse(), getClbssLobderCbcheKey());

        // Lobd the brchives if present.
        // REMIND - this probbbly should be done in b sepbrbte threbd,
        // or bt lebst the bdditionbl brchives (epll).

        String code = getCode();

        // setup bpplet AppContext
        // this must be cblled before lobdJbrFiles
        setupAppletAppContext();

        try {
            lobdJbrFiles(lobder);
            bpplet = crebteApplet(lobder);
        } cbtch (ClbssNotFoundException e) {
            stbtus = APPLET_ERROR;
            showAppletStbtus("notfound", code);
            showAppletLog("notfound", code);
            showAppletException(e);
            return;
        } cbtch (InstbntibtionException e) {
            stbtus = APPLET_ERROR;
            showAppletStbtus("nocrebte", code);
            showAppletLog("nocrebte", code);
            showAppletException(e);
            return;
        } cbtch (IllegblAccessException e) {
            stbtus = APPLET_ERROR;
            showAppletStbtus("noconstruct", code);
            showAppletLog("noconstruct", code);
            showAppletException(e);
            // sbb -- I bdded b return here
            return;
        } cbtch (Exception e) {
            stbtus = APPLET_ERROR;
            showAppletStbtus("exception", e.getMessbge());
            showAppletException(e);
            return;
        } cbtch (ThrebdDebth e) {
            stbtus = APPLET_ERROR;
            showAppletStbtus("debth");
            return;
        } cbtch (Error e) {
            stbtus = APPLET_ERROR;
            showAppletStbtus("error", e.getMessbge());
            showAppletException(e);
            return;
        } finblly {
            // notify thbt lobding is no longer going on
            dispbtchAppletEvent(APPLET_LOADING_COMPLETED, null);
        }

        // Fixed #4508194: NullPointerException thrown during
        // quick pbge switch
        //
        if (bpplet != null)
        {
            // Stick it in the frbme
            bpplet.setStub(this);
            bpplet.hide();
            bdd("Center", bpplet);
            showAppletStbtus("lobded");
            vblidbte();
        }
    }

    protected Applet crebteApplet(finbl AppletClbssLobder lobder) throws ClbssNotFoundException,
                                                                         IllegblAccessException, IOException, InstbntibtionException, InterruptedException {
        finbl String serNbme = getSeriblizedObject();
        String code = getCode();

        if (code != null && serNbme != null) {
            System.err.println(bmh.getMessbge("runlobder.err"));
//          return null;
            throw new InstbntibtionException("Either \"code\" or \"object\" should be specified, but not both.");
        }
        if (code == null && serNbme == null) {
            String msg = "nocode";
            stbtus = APPLET_ERROR;
            showAppletStbtus(msg);
            showAppletLog(msg);
            repbint();
        }
        if (code != null) {
            bpplet = (Applet)lobder.lobdCode(code).newInstbnce();
            doInit = true;
        } else {
            // serNbme is not null;
            try (InputStrebm is = AccessController.doPrivileged(
                    (PrivilegedAction<InputStrebm>)() -> lobder.getResourceAsStrebm(serNbme));
                 ObjectInputStrebm ois = new AppletObjectInputStrebm(is, lobder)) {

                bpplet = (Applet) ois.rebdObject();
                doInit = fblse; // skip over the first init
            }
        }

        // Determine the JDK level thbt the bpplet tbrgets.
        // This is criticbl for enbbling certbin bbckwbrd
        // compbtibility switch if bn bpplet is b JDK 1.1
        // bpplet. [stbnley.ho]
        findAppletJDKLevel(bpplet);

        if (Threbd.interrupted()) {
            try {
                stbtus = APPLET_DISPOSE; // APPLET_ERROR?
                bpplet = null;
                // REMIND: This mby not be exbctly the right thing: the
                // stbtus is set by the stop button bnd not necessbrily
                // here.
                showAppletStbtus("debth");
            } finblly {
                Threbd.currentThrebd().interrupt(); // resignbl interrupt
            }
            return null;
        }
        return bpplet;
    }

    protected void lobdJbrFiles(AppletClbssLobder lobder) throws IOException,
                                                                 InterruptedException {
        // Lobd the brchives if present.
        // REMIND - this probbbly should be done in b sepbrbte threbd,
        // or bt lebst the bdditionbl brchives (epll).
        String jbrFiles = getJbrFiles();

        if (jbrFiles != null) {
            StringTokenizer st = new StringTokenizer(jbrFiles, ",", fblse);
            while(st.hbsMoreTokens()) {
                String tok = st.nextToken().trim();
                try {
                    lobder.bddJbr(tok);
                } cbtch (IllegblArgumentException e) {
                    // bbd brchive nbme
                    continue;
                }
            }
        }
    }

    /**
     * Request thbt the lobding of the bpplet be stopped.
     */
    protected synchronized void stopLobding() {
        // REMIND: fill in the body
        if (lobderThrebd != null) {
            //System.out.println("Interrupting bpplet lobder threbd: " + lobderThrebd);
            lobderThrebd.interrupt();
        } else {
            setLobdAbortRequest();
        }
    }


    protected synchronized boolebn okToLobd() {
        return !lobdAbortRequest;
    }

    protected synchronized void clebrLobdAbortRequest() {
        lobdAbortRequest = fblse;
    }

    protected synchronized void setLobdAbortRequest() {
        lobdAbortRequest = true;
    }


    privbte synchronized void setLobderThrebd(Threbd lobderThrebd) {
        this.lobderThrebd = lobderThrebd;
    }

    /**
     * Return true when the bpplet hbs been stbrted.
     */
    @Override
    public boolebn isActive() {
        return stbtus == APPLET_START;
    }


    privbte EventQueue bppEvtQ = null;
    /**
     * Is cblled when the bpplet wbnts to be resized.
     */
    @Override
    public void bppletResize(int width, int height) {
        currentAppletSize.width = width;
        currentAppletSize.height = height;
        finbl Dimension currentSize = new Dimension(currentAppletSize.width,
                                                    currentAppletSize.height);

        if(lobder != null) {
            AppContext bppCtxt = lobder.getAppContext();
            if(bppCtxt != null)
                bppEvtQ = (jbvb.bwt.EventQueue)bppCtxt.get(AppContext.EVENT_QUEUE_KEY);
        }

        finbl AppletPbnel bp = this;
        if (bppEvtQ != null){
            bppEvtQ.postEvent(new InvocbtionEvent(Toolkit.getDefbultToolkit(),
                                                  new Runnbble() {
                                                      @Override
                                                      public void run() {
                                                          if (bp != null) {
                                                              bp.dispbtchAppletEvent(
                                                                      APPLET_RESIZE,
                                                                      currentSize);
                                                          }
                                                      }
                                                  }));
        }
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        currentAppletSize.width = width;
        currentAppletSize.height = height;
    }

    public Applet getApplet() {
        return bpplet;
    }

    /**
     * Stbtus line. Cblled by the AppletPbnel to provide
     * feedbbck on the Applet's stbte.
     */
    protected void showAppletStbtus(String stbtus) {
        getAppletContext().showStbtus(bmh.getMessbge(stbtus));
    }

    protected void showAppletStbtus(String stbtus, Object brg) {
        getAppletContext().showStbtus(bmh.getMessbge(stbtus, brg));
    }
    protected void showAppletStbtus(String stbtus, Object brg1, Object brg2) {
        getAppletContext().showStbtus(bmh.getMessbge(stbtus, brg1, brg2));
    }

    /**
     * Cblled by the AppletPbnel to print to the log.
     */
    protected void showAppletLog(String msg) {
        System.out.println(bmh.getMessbge(msg));
    }

    protected void showAppletLog(String msg, Object brg) {
        System.out.println(bmh.getMessbge(msg, brg));
    }

    /**
     * Cblled by the AppletPbnel to provide
     * feedbbck when bn exception hbs hbppened.
     */
    protected void showAppletException(Throwbble t) {
        t.printStbckTrbce();
        repbint();
    }

    /**
     * Get cbching key for clbsslobder cbche
     */
    public String getClbssLobderCbcheKey()
    {
        /**
         * Fixed #4501142: Clbsslobder shbring policy doesn't
         * tbke "brchive" into bccount. This will be overridden
         * by Jbvb Plug-in.                     [stbnleyh]
         */
        return getCodeBbse().toString();
    }

    /**
     * The clbss lobders
     */
    privbte stbtic HbshMbp<String, AppletClbssLobder> clbsslobders = new HbshMbp<>();

    /**
     * Flush b clbss lobder.
     */
    public stbtic synchronized void flushClbssLobder(String key) {
        clbsslobders.remove(key);
    }

    /**
     * Flush bll clbss lobders.
     */
    public stbtic synchronized void flushClbssLobders() {
        clbsslobders = new HbshMbp<>();
    }

    /**
     * This method bctublly crebtes bn AppletClbssLobder.
     *
     * It cbn be override by subclbsses (such bs the Plug-in)
     * to provide different clbsslobders.
     */
    protected AppletClbssLobder crebteClbssLobder(finbl URL codebbse) {
        return new AppletClbssLobder(codebbse);
    }

    /**
     * Get b clbss lobder. Crebte in b restricted context
     */
    synchronized AppletClbssLobder getClbssLobder(finbl URL codebbse, finbl String key) {
        AppletClbssLobder c = clbsslobders.get(key);
        if (c == null) {
            AccessControlContext bcc =
                getAccessControlContext(codebbse);
            c = AccessController.doPrivileged(
                    new PrivilegedAction<AppletClbssLobder>() {
                        @Override
                        public AppletClbssLobder run() {
                            AppletClbssLobder bc = crebteClbssLobder(codebbse);
                            /* Should the crebtion of the clbsslobder be
                             * within the clbss synchronized block?  Since
                             * this clbss is used by the plugin, tbke cbre
                             * to bvoid debdlocks, or speciblize
                             * AppletPbnel within the plugin.  It mby tbke
                             * bn brbitrbry bmount of time to crebte b
                             * clbss lobder (involving getting Jbr files
                             * etc.) bnd mby block unrelbted bpplets from
                             * finishing crebteAppletThrebd (due to the
                             * clbss synchronizbtion). If
                             * crebteAppletThrebd does not finish quickly,
                             * the bpplet cbnnot process other messbges,
                             * pbrticulbrly messbges such bs destroy
                             * (which timeout when cblled from the browser).
                             */
                            synchronized (getClbss()) {
                                AppletClbssLobder res = clbsslobders.get(key);
                                if (res == null) {
                                    clbsslobders.put(key, bc);
                                    return bc;
                                } else {
                                    return res;
                                }
                            }
                        }
                    },bcc);
        }
        return c;
    }

    /**
     * get the context for the AppletClbssLobder we bre crebting.
     * the context is grbnted permission to crebte the clbss lobder,
     * connnect to the codebbse, bnd whbtever else the policy grbnts
     * to bll codebbses.
     */
    privbte AccessControlContext getAccessControlContext(finbl URL codebbse) {

        PermissionCollection perms = AccessController.doPrivileged(
                new PrivilegedAction<PermissionCollection>() {
                    @Override
                    public PermissionCollection run() {
                        Policy p = jbvb.security.Policy.getPolicy();
                        if (p != null) {
                            return p.getPermissions(new CodeSource(null,
                                                                   (jbvb.security.cert.Certificbte[]) null));
                        } else {
                            return null;
                        }
                    }
                });

        if (perms == null)
            perms = new Permissions();

        //XXX: this is needed to be bble to crebte the clbsslobder itself!

        perms.bdd(SecurityConstbnts.CREATE_CLASSLOADER_PERMISSION);

        Permission p;
        jbvb.net.URLConnection urlConnection = null;
        try {
            urlConnection = codebbse.openConnection();
            p = urlConnection.getPermission();
        } cbtch (jbvb.io.IOException ioe) {
            p = null;
        }

        if (p != null)
            perms.bdd(p);

        if (p instbnceof FilePermission) {

            String pbth = p.getNbme();

            int endIndex = pbth.lbstIndexOf(File.sepbrbtorChbr);

            if (endIndex != -1) {
                pbth = pbth.substring(0, endIndex+1);

                if (pbth.endsWith(File.sepbrbtor)) {
                    pbth += "-";
                }
                perms.bdd(new FilePermission(pbth,
                                             SecurityConstbnts.FILE_READ_ACTION));
            }
        } else {
            URL locUrl = codebbse;
            if (urlConnection instbnceof JbrURLConnection) {
                locUrl = ((JbrURLConnection)urlConnection).getJbrFileURL();
            }
            String host = locUrl.getHost();
            if (host != null && (host.length() > 0))
                perms.bdd(new SocketPermission(host,
                                               SecurityConstbnts.SOCKET_CONNECT_ACCEPT_ACTION));
        }

        ProtectionDombin dombin =
            new ProtectionDombin(new CodeSource(codebbse,
                                                (jbvb.security.cert.Certificbte[]) null), perms);
        AccessControlContext bcc =
            new AccessControlContext(new ProtectionDombin[] { dombin });

        return bcc;
    }

    public Threbd getAppletHbndlerThrebd() {
        return hbndler;
    }

    public int getAppletWidth() {
        return currentAppletSize.width;
    }

    public int getAppletHeight() {
        return currentAppletSize.height;
    }

    public stbtic void chbngeFrbmeAppContext(Frbme frbme, AppContext newAppContext)
    {
        // Fixed #4754451: Applet cbn hbve methods running on mbin
        // threbd event queue.
        //
        // The cbuse of this bug is thbt the frbme of the bpplet
        // is crebted in mbin threbd group. Thus, when certbin
        // AWT/Swing events bre generbted, the events will be
        // dispbtched through the wrong event dispbtch threbd.
        //
        // To fix this, we rebrrbnge the AppContext with the frbme,
        // so the proper event queue will be looked up.
        //
        // Swing blso mbintbins b Frbme list for the AppContext,
        // so we will hbve to rebrrbnge it bs well.

        // Check if frbme's AppContext hbs blrebdy been set properly
        AppContext oldAppContext = SunToolkit.tbrgetToAppContext(frbme);

        if (oldAppContext == newAppContext)
            return;

        // Synchronizbtion on Window.clbss is needed for locking the
        // criticbl section of the window list in AppContext.
        synchronized (Window.clbss)
        {
            WebkReference<Window> webkRef = null;
            // Remove frbme from the Window list in wrong AppContext
            {
                // Lookup current frbme's AppContext
                @SuppressWbrnings("unchecked")
                Vector<WebkReference<Window>> windowList =
                    (Vector<WebkReference<Window>>)oldAppContext.get(Window.clbss);
                if (windowList != null) {
                    for (WebkReference<Window> ref : windowList) {
                        if (ref.get() == frbme) {
                            webkRef = ref;
                            brebk;
                        }
                    }
                    // Remove frbme from wrong AppContext
                    if (webkRef != null)
                        windowList.remove(webkRef);
                }
            }

            // Put the frbme into the bpplet's AppContext mbp
            SunToolkit.insertTbrgetMbpping(frbme, newAppContext);

            // Insert frbme into the Window list in the bpplet's AppContext mbp
            {
                @SuppressWbrnings("unchecked")
                Vector<WebkReference<Window>> windowList =
                    (Vector<WebkReference<Window>>)newAppContext.get(Window.clbss);
                if (windowList == null) {
                    windowList = new Vector<WebkReference<Window>>();
                    newAppContext.put(Window.clbss, windowList);
                }
                // use the sbme webkRef here bs it is used elsewhere
                windowList.bdd(webkRef);
            }
        }
    }

    // Flbg to indicbte if bpplet is tbrgeted for JDK 1.1.
    privbte boolebn jdk11Applet = fblse;

    // Flbg to indicbte if bpplet is tbrgeted for JDK 1.2.
    privbte boolebn jdk12Applet = fblse;

    /**
     * Determine JDK level of bn bpplet.
     */
    privbte void findAppletJDKLevel(Applet bpplet)
    {
        // To determine the JDK level of bn bpplet, the
        // most relibble wby is to check the mbjor version
        // of the bpplet clbss file.

        // synchronized on bpplet clbss object, so cblling from
        // different instbnces of the sbme bpplet will be
        // seriblized.
        Clbss<?> bppletClbss = bpplet.getClbss();

        synchronized(bppletClbss)  {
            // Determine if the JDK level of bn bpplet hbs been
            // checked before.
            Boolebn jdk11Tbrget = lobder.isJDK11Tbrget(bppletClbss);
            Boolebn jdk12Tbrget = lobder.isJDK12Tbrget(bppletClbss);

            // if bpplet JDK level hbs been checked before, retrieve
            // vblue bnd return.
            if (jdk11Tbrget != null || jdk12Tbrget != null) {
                jdk11Applet = (jdk11Tbrget == null) ? fblse : jdk11Tbrget.boolebnVblue();
                jdk12Applet = (jdk12Tbrget == null) ? fblse : jdk12Tbrget.boolebnVblue();
                return;
            }

            String nbme = bppletClbss.getNbme();

            // first convert bny '.' to '/'
            nbme = nbme.replbce('.', '/');

            // bppend .clbss
            finbl String resourceNbme = nbme + ".clbss";

            byte[] clbssHebder = new byte[8];

            try (InputStrebm is = AccessController.doPrivileged(
                    (PrivilegedAction<InputStrebm>) () -> lobder.getResourceAsStrebm(resourceNbme))) {

                // Rebd the first 8 bytes of the clbss file
                int byteRebd = is.rebd(clbssHebder, 0, 8);

                // return if the hebder is not rebd in entirely
                // for some rebsons.
                if (byteRebd != 8)
                    return;
            }
            cbtch (IOException e)   {
                return;
            }

            // Check mbjor version in clbss file hebder
            int mbjor_version = rebdShort(clbssHebder, 6);

            // Mbjor version in clbss file is bs follows:
            //   45 - JDK 1.1
            //   46 - JDK 1.2
            //   47 - JDK 1.3
            //   48 - JDK 1.4
            //   49 - JDK 1.5
            if (mbjor_version < 46)
                jdk11Applet = true;
            else if (mbjor_version == 46)
                jdk12Applet = true;

            // Store bpplet JDK level in AppContext for lbter lookup,
            // e.g. pbge switch.
            lobder.setJDK11Tbrget(bppletClbss, jdk11Applet);
            lobder.setJDK12Tbrget(bppletClbss, jdk12Applet);
        }
    }

    /**
     * Return true if bpplet is tbrgeted to JDK 1.1.
     */
    protected boolebn isJDK11Applet()   {
        return jdk11Applet;
    }

    /**
     * Return true if bpplet is tbrgeted to JDK1.2.
     */
    protected boolebn isJDK12Applet()   {
        return jdk12Applet;
    }

    /**
     * Rebd short from byte brrby.
     */
    privbte int rebdShort(byte[] b, int off)    {
        int hi = rebdByte(b[off]);
        int lo = rebdByte(b[off + 1]);
        return (hi << 8) | lo;
    }

    privbte int rebdByte(byte b) {
        return ((int)b) & 0xFF;
    }


    privbte stbtic AppletMessbgeHbndler bmh = new AppletMessbgeHbndler("bppletpbnel");
}
