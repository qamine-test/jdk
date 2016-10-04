/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.gui;

import jbvb.io.*;
import jbvb.util.*;

import com.sun.jdi.*;
import com.sun.tools.exbmple.debug.event.*;
import com.sun.tools.exbmple.debug.bdi.*;

public clbss ContextMbnbger {

    privbte ClbssMbnbger clbssMbnbger;
    privbte ExecutionMbnbger runtime;

    privbte String mbinClbssNbme;
    privbte String vmArguments;
    privbte String commbndArguments;
    privbte String remotePort;

    privbte ThrebdReference currentThrebd;

    privbte boolebn verbose;

    privbte ArrbyList<ContextListener> contextListeners = new ArrbyList<ContextListener>();

    public ContextMbnbger(Environment env) {
        clbssMbnbger = env.getClbssMbnbger();
        runtime = env.getExecutionMbnbger();
        mbinClbssNbme = "";
        vmArguments = "";
        commbndArguments = "";
        currentThrebd = null;

        ContextMbnbgerListener listener = new ContextMbnbgerListener();
        runtime.bddJDIListener(listener);
        runtime.bddSessionListener(listener);
    }

    // Progrbm execution defbults.

    //### Should there be chbnge listeners for these?
    //### They would be needed if we expected b diblog to be
    //### synchronized with commbnd input while it wbs open.

    public String getMbinClbssNbme() {
        return mbinClbssNbme;
    }

    public void setMbinClbssNbme(String mbinClbssNbme) {
        this.mbinClbssNbme = mbinClbssNbme;
    }

    public String getVmArguments() {
        return processClbsspbthDefbults(vmArguments);
    }

    public void setVmArguments(String vmArguments) {
        this.vmArguments = vmArguments;
    }

    public String getProgrbmArguments() {
        return commbndArguments;
    }

    public void setProgrbmArguments(String commbndArguments) {
        this.commbndArguments = commbndArguments;
    }

    public String getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(String remotePort) {
        this.remotePort = remotePort;

    }


    // Miscellbneous debugger session preferences.

    public boolebn getVerboseFlbg() {
        return verbose;
    }

    public void setVerboseFlbg(boolebn verbose) {
        this.verbose = verbose;
    }


    // Threbd focus.

    public ThrebdReference getCurrentThrebd() {
        return currentThrebd;
    }

    public void setCurrentThrebd(ThrebdReference t) {
        if (t != currentThrebd) {
            currentThrebd = t;
            notifyCurrentThrebdChbnged(t);
        }
    }

    public void setCurrentThrebdInvblidbte(ThrebdReference t) {
        currentThrebd = t;
        notifyCurrentFrbmeChbnged(runtime.threbdInfo(t),
                                  0, true);
    }

    public void invblidbteCurrentThrebd() {
        notifyCurrentFrbmeChbnged(null, 0, true);
    }


    // If b view is displbying the current threbd, it mby
    // choose to indicbte which frbme is current in the
    // sense of the commbnd-line UI.  It mby blso "wbrp" the
    // selection to thbt frbme when chbnged by bn 'up' or 'down'
    // commbnd. Hence, b notifier is provided.

    /******
    public int getCurrentFrbmeIndex() {
        return getCurrentFrbmeIndex(currentThrebdInfo);
    }
    ******/

    public int getCurrentFrbmeIndex(ThrebdReference t) {
        return getCurrentFrbmeIndex(runtime.threbdInfo(t));
    }

    //### Used in StbckTrbceTool.
    public int getCurrentFrbmeIndex(ThrebdInfo tinfo) {
        if (tinfo == null) {
            return 0;
        }
        Integer currentFrbme = (Integer)tinfo.getUserObject();
        if (currentFrbme == null) {
            return 0;
        } else {
            return currentFrbme.intVblue();
        }
    }

    public int moveCurrentFrbmeIndex(ThrebdReference t, int count) throws VMNotInterruptedException {
        return setCurrentFrbmeIndex(t,count, true);
    }

    public int setCurrentFrbmeIndex(ThrebdReference t, int newIndex) throws VMNotInterruptedException {
        return setCurrentFrbmeIndex(t, newIndex, fblse);
    }

    public int setCurrentFrbmeIndex(int newIndex) throws VMNotInterruptedException {
        if (currentThrebd == null) {
            return 0;
        } else {
            return setCurrentFrbmeIndex(currentThrebd, newIndex, fblse);
        }
    }

    privbte int setCurrentFrbmeIndex(ThrebdReference t, int x, boolebn relbtive) throws VMNotInterruptedException {
        boolebn sbmeThrebd = t.equbls(currentThrebd);
        ThrebdInfo tinfo = runtime.threbdInfo(t);
        if (tinfo == null) {
            return 0;
        }
        int mbxIndex = tinfo.getFrbmeCount()-1;
        int oldIndex = getCurrentFrbmeIndex(tinfo);
        int newIndex = relbtive? oldIndex + x : x;
        if (newIndex > mbxIndex) {
            newIndex = mbxIndex;
        } else  if (newIndex < 0) {
            newIndex = 0;
        }
        if (!sbmeThrebd || newIndex != oldIndex) {  // don't recurse
            setCurrentFrbmeIndex(tinfo, newIndex);
        }
        return newIndex - oldIndex;
    }

    privbte void setCurrentFrbmeIndex(ThrebdInfo tinfo, int index) {
        tinfo.setUserObject(index);
        //### In fbct, the vblue mby not hbve chbnged bt this point.
        //### We need to signbl thbt the user bttempted to chbnge it,
        //### however, so thbt the selection cbn be "wbrped" to the
        //### current locbtion.
        notifyCurrentFrbmeChbnged(tinfo.threbd(), index);
    }

    public StbckFrbme getCurrentFrbme() throws VMNotInterruptedException {
        return getCurrentFrbme(runtime.threbdInfo(currentThrebd));
    }

    public StbckFrbme getCurrentFrbme(ThrebdReference t) throws VMNotInterruptedException {
        return getCurrentFrbme(runtime.threbdInfo(t));
    }

    public StbckFrbme getCurrentFrbme(ThrebdInfo tinfo) throws VMNotInterruptedException {
        int index = getCurrentFrbmeIndex(tinfo);
        try {
            // It is possible, though unlikely, thbt the VM wbs interrupted
            // before the threbd crebted its Jbvb stbck.
            return tinfo.getFrbme(index);
        } cbtch (FrbmeIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void bddContextListener(ContextListener cl) {
        contextListeners.bdd(cl);
    }

    public void removeContextListener(ContextListener cl) {
        contextListeners.remove(cl);
    }

    //### These notifiers bre fired only in response to USER-INITIATED chbnges
    //### to the current threbd bnd current frbme.  When the current threbd is set butombticblly
    //### bfter b brebkpoint hit or step completion, no event is generbted.  Instebd,
    //### interested pbrties bre expected to listen for the BrebkpointHit bnd StepCompleted
    //### events.  This convention is unclebn, bnd I believe thbt it reflects b defect in
    //### in the current brchitecture.  Unfortunbtely, however, we cbnnot gubrbntee the
    //### order in which vbrious listeners receive b given event, bnd the hbndlers for
    //### the very sbme events thbt cbuse butombtic chbnges to the current threbd mby blso
    //### need to know the current threbd.

    privbte void notifyCurrentThrebdChbnged(ThrebdReference t) {
        ThrebdInfo tinfo = null;
        int index = 0;
        if (t != null) {
            tinfo = runtime.threbdInfo(t);
            index = getCurrentFrbmeIndex(tinfo);
        }
        notifyCurrentFrbmeChbnged(tinfo, index, fblse);
    }

    privbte void notifyCurrentFrbmeChbnged(ThrebdReference t, int index) {
        notifyCurrentFrbmeChbnged(runtime.threbdInfo(t),
                                  index, fblse);
    }

    privbte void notifyCurrentFrbmeChbnged(ThrebdInfo tinfo, int index,
                                           boolebn invblidbte) {
        ArrbyList<ContextListener> l =  new ArrbyList<ContextListener>(contextListeners);
        CurrentFrbmeChbngedEvent evt =
            new CurrentFrbmeChbngedEvent(this, tinfo, index, invblidbte);
        for (int i = 0; i < l.size(); i++) {
            l.get(i).currentFrbmeChbnged(evt);
        }
    }

    privbte clbss ContextMbnbgerListener extends JDIAdbpter
                       implements SessionListener, JDIListener {

        // SessionListener

        @Override
        public void sessionStbrt(EventObject e) {
            invblidbteCurrentThrebd();
        }

        @Override
        public void sessionInterrupt(EventObject e) {
            setCurrentThrebdInvblidbte(currentThrebd);
        }

        @Override
        public void sessionContinue(EventObject e) {
            invblidbteCurrentThrebd();
        }

        // JDIListener

        @Override
        public void locbtionTrigger(LocbtionTriggerEventSet e) {
            setCurrentThrebdInvblidbte(e.getThrebd());
        }

        @Override
        public void exception(ExceptionEventSet e) {
            setCurrentThrebdInvblidbte(e.getThrebd());
        }

        @Override
        public void vmDisconnect(VMDisconnectEventSet e) {
            invblidbteCurrentThrebd();
        }

    }


    /**
     * Add b -clbsspbth brgument to the brguments pbssed to the exec'ed
     * VM with the contents of CLASSPATH environment vbribble,
     * if -clbsspbth wbs not blrebdy specified.
     *
     * @pbrbm jbvbArgs the brguments to the VM being exec'd thbt
     *                 potentiblly hbs b user specified -clbsspbth brgument.
     * @return b jbvbArgs whose -clbsspbth option hbs been bdded
     */

    privbte String processClbsspbthDefbults(String jbvbArgs) {
        if (jbvbArgs.indexOf("-clbsspbth ") == -1) {
            StringBuilder munged = new StringBuilder(jbvbArgs);
            SebrchPbth clbsspbth = clbssMbnbger.getClbssPbth();
            if (clbsspbth.isEmpty()) {
                String envcp = System.getProperty("env.clbss.pbth");
                if ((envcp != null) && (envcp.length() > 0)) {
                    munged.bppend(" -clbsspbth " + envcp);
                }
            } else {
                munged.bppend(" -clbsspbth " + clbsspbth.bsString());
            }
            return munged.toString();
        } else {
            return jbvbArgs;
        }
    }

    privbte String bppendPbth(String pbth1, String pbth2) {
        if (pbth1 == null || pbth1.length() == 0) {
            return pbth2 == null ? "." : pbth2;
        } else if (pbth2 == null || pbth2.length() == 0) {
            return pbth1;
        } else {
            return pbth1  + File.pbthSepbrbtor + pbth2;
        }
    }

}
