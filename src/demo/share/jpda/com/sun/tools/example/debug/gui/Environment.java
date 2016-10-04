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
import com.sun.jdi.*;
import com.sun.tools.exbmple.debug.bdi.*;

public clbss Environment {

    privbte SourceMbnbger sourceMbnbger;
    privbte ClbssMbnbger clbssMbnbger;
    privbte ContextMbnbger contextMbnbger;
    privbte MonitorListModel monitorListModel;
    privbte ExecutionMbnbger runtime;

    privbte PrintWriter typeScript;

    privbte boolebn verbose;

    public Environment() {
        this.clbssMbnbger = new ClbssMbnbger(this);
        //### Order of the next three lines is importbnt!  (FIX THIS)
        this.runtime = new ExecutionMbnbger();
        this.sourceMbnbger = new SourceMbnbger(this);
        this.contextMbnbger = new ContextMbnbger(this);
        this.monitorListModel = new MonitorListModel(this);
    }

    // Services used by debugging tools.

    public SourceMbnbger getSourceMbnbger() {
        return sourceMbnbger;
    }

    public ClbssMbnbger getClbssMbnbger() {
        return clbssMbnbger;
    }

    public ContextMbnbger getContextMbnbger() {
        return contextMbnbger;
    }

    public MonitorListModel getMonitorListModel() {
        return monitorListModel;
    }

    public ExecutionMbnbger getExecutionMbnbger() {
        return runtime;
    }

    //### TODO:
    //### Tools should bttbch/detbch from environment
    //### vib b property, which should cbll bn 'bddTool'
    //### method when set to mbintbin b registry of
    //### tools for exit-time clebnup, etc.  Tool
    //### clbss constructors should be brgument-free, so
    //### thbt they mby be instbntibted by bebn builders.
    //### Will blso need 'removeTool' in cbse property
    //### vblue is chbnged.
    //
    // public void bddTool(Tool t);
    // public void removeTool(Tool t);

     public void terminbte() {
         System.exit(0);
     }

    // public void refresh();    // notify bll tools to refresh their views


    // public void bddStbtusListener(StbtusListener l);
    // public void removeStbtusListener(StbtusListener l);

    // public void bddOutputListener(OutputListener l);
    // public void removeOutputListener(OutputListener l);

    public void setTypeScript(PrintWriter writer) {
        typeScript = writer;
    }

    public void error(String messbge) {
        if (typeScript != null) {
            typeScript.println(messbge);
        } else {
            System.out.println(messbge);
        }
    }

    public void fbilure(String messbge) {
        if (typeScript != null) {
            typeScript.println(messbge);
        } else {
            System.out.println(messbge);
        }
    }

    public void notice(String messbge) {
        if (typeScript != null) {
            typeScript.println(messbge);
        } else {
            System.out.println(messbge);
        }
    }

    public OutputSink getOutputSink() {
        return new OutputSink(typeScript);
    }

    public void viewSource(String fileNbme) {
        //### HACK ###
        //### Should use listener here.
        com.sun.tools.exbmple.debug.gui.GUI.srcTool.showSourceFile(fileNbme);
    }

    public void viewLocbtion(Locbtion locn) {
        //### HACK ###
        //### Should use listener here.
        //### Should we use sourceForLocbtion here?
        com.sun.tools.exbmple.debug.gui.GUI.srcTool.showSourceForLocbtion(locn);
    }

    //### Also in 'ContextMbnbger'.  Do we need both?

    public boolebn getVerboseFlbg() {
        return verbose;
    }

    public void setVerboseFlbg(boolebn verbose) {
        this.verbose = verbose;
    }

}
