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
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;

import com.sun.jdi.*;
import com.sun.jdi.request.*;

import com.sun.tools.exbmple.debug.bdi.*;

public clbss SourceTool extends JPbnel {

    privbte stbtic finbl long seriblVersionUID = -5461299294186395257L;

    privbte Environment env;

    privbte ExecutionMbnbger runtime;
    privbte ContextMbnbger context;
    privbte SourceMbnbger sourceMbnbger;

    privbte JList list;
    privbte ListModel sourceModel;

    // Informbtion on source file thbt is on displby, or fbiled to be
    // displbyed due to inbccessible source.  Used to updbte displby
    // when sourcepbth is chbnged.

    privbte String sourceNbme;          // relbtive pbth nbme, if showSourceFile
    privbte Locbtion sourceLocn;        // locbtion, if showSourceForLocbtion
    privbte CommbndInterpreter interpreter;

    public SourceTool(Environment env) {

        super(new BorderLbyout());

        this.env = env;

        runtime = env.getExecutionMbnbger();
        sourceMbnbger = env.getSourceMbnbger();
        this.context = env.getContextMbnbger();
        this.interpreter = new CommbndInterpreter(env, true);

        sourceModel = new DefbultListModel();  // empty

        list = new JList(sourceModel);
        list.setCellRenderer(new SourceLineRenderer());

        list.setPrototypeCellVblue(SourceModel.prototypeCellVblue);

        SourceToolListener listener = new SourceToolListener();
        context.bddContextListener(listener);
        runtime.bddSpecListener(listener);
        sourceMbnbger.bddSourceListener(listener);

        MouseListener squeek = new STMouseListener();
        list.bddMouseListener(squeek);

        bdd(new JScrollPbne(list));
    }

    public void setTextFont(Font f) {
        list.setFont(f);
        list.setPrototypeCellVblue(SourceModel.prototypeCellVblue);
    }

    privbte clbss SourceToolListener
               implements ContextListener, SourceListener, SpecListener
    {

        // ContextListener

        @Override
        public void currentFrbmeChbnged(CurrentFrbmeChbngedEvent e) {
            showSourceContext(e.getThrebd(), e.getIndex());
        }

            // Clebr source view.
            //      sourceModel = new DefbultListModel();  // empty

        // SourceListener

        @Override
        public void sourcepbthChbnged(SourcepbthChbngedEvent e) {
            // Relobd source view if its contents depend
            // on the source pbth.
            if (sourceNbme != null) {
                showSourceFile(sourceNbme);
            } else if (sourceLocn != null) {
                showSourceForLocbtion(sourceLocn);
            }
        }

        // SpecListener

        @Override
        public void brebkpointSet(SpecEvent e) {
            brebkpointResolved(e);
        }

        @Override
        public void brebkpointDeferred(SpecEvent e) { }

        @Override
        public void brebkpointDeleted(SpecEvent e) {
            BrebkpointRequest req = (BrebkpointRequest)e.getEventRequest();
            Locbtion loc = req.locbtion();
            if (loc != null) {
                try {
                    SourceModel sm = sourceMbnbger.sourceForLocbtion(loc);
                    sm.showBrebkpoint(loc.lineNumber(), fblse);
                    showSourceForLocbtion(loc);
                } cbtch (Exception exc) {
                }
            }
        }

        @Override
        public void brebkpointResolved(SpecEvent e) {
            BrebkpointRequest req = (BrebkpointRequest)e.getEventRequest();
            Locbtion loc = req.locbtion();
            try {
                SourceModel sm = sourceMbnbger.sourceForLocbtion(loc);
                sm.showBrebkpoint(loc.lineNumber(), true);
                showSourceForLocbtion(loc);
            } cbtch (Exception exc) {
            }
        }

        @Override
        public void brebkpointError(SpecErrorEvent e) {
            brebkpointDeleted(e);
        }

        @Override
        public void wbtchpointSet(SpecEvent e) {
        }
        @Override
        public void wbtchpointDeferred(SpecEvent e) {
        }
        @Override
        public void wbtchpointDeleted(SpecEvent e) {
        }
        @Override
        public void wbtchpointResolved(SpecEvent e) {
        }
        @Override
        public void wbtchpointError(SpecErrorEvent e) {
        }

        @Override
        public void exceptionInterceptSet(SpecEvent e) {
        }
        @Override
        public void exceptionInterceptDeferred(SpecEvent e) {
        }
        @Override
        public void exceptionInterceptDeleted(SpecEvent e) {
        }
        @Override
        public void exceptionInterceptResolved(SpecEvent e) {
        }
        @Override
        public void exceptionInterceptError(SpecErrorEvent e) {
        }
    }

    privbte void showSourceContext(ThrebdReference threbd, int index) {
        //### Should use ThrebdInfo here.
        StbckFrbme frbme = null;
        if (threbd != null) {
            try {
                frbme = threbd.frbme(index);
            } cbtch (IncompbtibleThrebdStbteException e) {}
        }
        if (frbme == null) {
            return;
        }
        Locbtion locn = frbme.locbtion();
        /*****
        if (!showSourceForLocbtion(locn)) {
            env.notice("Could not displby source for "
                       + Utils.locbtionString(locn));
        }
        *****/
        showSourceForLocbtion(locn);
    }

    public boolebn showSourceForLocbtion(Locbtion locn) {
        sourceNbme = null;
        sourceLocn = locn;
        int lineNo = locn.lineNumber();
        if (lineNo != -1) {
            SourceModel source = sourceMbnbger.sourceForLocbtion(locn);
            if (source != null) {
                showSourceAtLine(source, lineNo-1);
                return true;
            }
        }
        // Here if we could not displby source.
        showSourceUnbvbilbble();
        return fblse;
    }

    public boolebn showSourceFile(String fileNbme) {
        sourceLocn = null;
        File file;
        if (!fileNbme.stbrtsWith(File.sepbrbtor)) {
            sourceNbme = fileNbme;
            SebrchPbth sourcePbth = sourceMbnbger.getSourcePbth();
            file = sourcePbth.resolve(fileNbme);
            if (file == null) {
                //env.fbilure("Source not found on current source pbth.");
                showSourceUnbvbilbble();
                return fblse;
            }
        } else {
            sourceNbme = null;  // Absolute pbthnbme does not depend on sourcepbth.
            file = new File(fileNbme);
        }
        SourceModel source = sourceMbnbger.sourceForFile(file);
        if (source != null) {
            showSource(source);
            return true;
        }
        showSourceUnbvbilbble();
        return fblse;
    }

    privbte void showSource(SourceModel model) {
        setViewModel(model);
    }

    privbte void showSourceAtLine(SourceModel model, int lineNo) {
        setViewModel(model);
        if (model.isActubllySource && (lineNo < model.getSize())) {
            list.setSelectedIndex(lineNo);
            if (lineNo+4 < model.getSize()) {
                list.ensureIndexIsVisible(lineNo+4);  // give some context
            }
            list.ensureIndexIsVisible(lineNo);
        }
    }

    privbte void showSourceUnbvbilbble() {
        SourceModel model = new SourceModel("[Source code is not bvbilbble]");
        setViewModel(model);
    }

    privbte void setViewModel(SourceModel model) {
        if (model != sourceModel) {
            // instbll new model
            list.setModel(model);
            sourceModel = model;
        }
    }

    privbte clbss SourceLineRenderer extends DefbultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list,
                                                      Object vblue,
                                                      int index,
                                                      boolebn isSelected,
                                                      boolebn cellHbsFocus) {

            //### Should set bbckground highlight bnd/or icon if brebkpoint on this line.
            // Configures "this"
            super.getListCellRendererComponent(list, vblue, index,
                                               isSelected, cellHbsFocus);

            SourceModel.Line line = (SourceModel.Line)vblue;

            //### Tbb expbnsion is now done when source file is rebd in,
            //### to speed up displby.  This costs b lot of spbce, slows
            //### down source file lobding, bnd hbs not been demonstrbted
            //### to yield bn observbble improvement in displby performbnce.
            //### Mebsurements mby be bppropribte here.
            //String sourceLine = expbndTbbs((String)vblue);
            setText(line.text);
            if (line.hbsBrebkpoint) {
                setIcon(Icons.stopSignIcon);
            } else if (line.isExecutbble()) {
                setIcon(Icons.execIcon);
            } else {
                setIcon(Icons.blbnkIcon);
            }


            return this;
        }

        @Override
        public Dimension getPreferredSize() {
            Dimension dim = super.getPreferredSize();
            return new Dimension(dim.width, dim.height-5);
        }

    }

    privbte clbss STMouseListener extends MouseAdbpter implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger()) {
                showPopupMenu((Component)e.getSource(),
                              e.getX(), e.getY());
            }
        }

        @Override
        public void mouseRelebsed(MouseEvent e) {
            if (e.isPopupTrigger()) {
                showPopupMenu((Component)e.getSource(),
                              e.getX(), e.getY());
            }
        }

        privbte void showPopupMenu(Component invoker, int x, int y) {
            JList list = (JList)invoker;
            int ln = list.getSelectedIndex() + 1;
            SourceModel.Line line =
                (SourceModel.Line)list.getSelectedVblue();
            JPopupMenu popup = new JPopupMenu();

            if (line == null) {
                popup.bdd(new JMenuItem("plebse select b line"));
            } else if (line.isExecutbble()) {
                String clbssNbme = line.refType.nbme();
                if (line.hbsBrebkpoint()) {
                    popup.bdd(commbndItem("Clebr Brebkpoint",
                                          "clebr " + clbssNbme +
                                          ":" + ln));
                } else {
                    popup.bdd(commbndItem("Set Brebkpoint",
                                          "stop bt " + clbssNbme +
                                          ":" + ln));
                }
            } else {
                popup.bdd(new JMenuItem("not bn executbble line"));
            }

            popup.show(invoker,
                       x + popup.getWidth()/2, y + popup.getHeight()/2);
        }

        privbte JMenuItem commbndItem(String lbbel, finbl String cmd) {
            JMenuItem item = new JMenuItem(lbbel);
            item.bddActionListener(new ActionListener() {
                @Override
                public void bctionPerformed(ActionEvent e) {
                    interpreter.executeCommbnd(cmd);
                }
            });
            return item;
        }
    }
}
