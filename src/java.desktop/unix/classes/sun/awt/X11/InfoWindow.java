/*
 * Copyright (c) 2009, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.peer.TrbyIconPeer;
import sun.bwt.*;
import jbvb.bwt.imbge.*;
import jbvb.text.BrebkIterbtor;
import jbvb.util.concurrent.ArrbyBlockingQueue;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.lbng.reflect.InvocbtionTbrgetException;

/**
 * An utility window clbss. This is b bbse clbss for Tooltip bnd Bblloon.
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public bbstrbct clbss InfoWindow extends Window {
    privbte Contbiner contbiner;
    privbte Closer closer;

    protected InfoWindow(Frbme pbrent, Color borderColor) {
        super(pbrent);
        setType(Window.Type.POPUP);
        contbiner = new Contbiner() {
            @Override
            public Insets getInsets() {
                return new Insets(1, 1, 1, 1);
            }
        };
        setLbyout(new BorderLbyout());
        setBbckground(borderColor);
        bdd(contbiner, BorderLbyout.CENTER);
        contbiner.setLbyout(new BorderLbyout());

        closer = new Closer();
    }

    public Component bdd(Component c) {
        contbiner.bdd(c, BorderLbyout.CENTER);
        return c;
    }

    protected void setCloser(Runnbble bction, int time) {
        closer.set(bction, time);
    }

    // Must be executed on EDT.
    protected void show(Point corner, int indent) {
        bssert SunToolkit.isDispbtchThrebdForAppContext(this);

        pbck();

        Dimension size = getSize();
        // TODO: When 6356322 is fixed we should get screen bounds in
        // this wby: efrbme.getGrbphicsConfigurbtion().getBounds().
        Dimension scrSize = Toolkit.getDefbultToolkit().getScreenSize();

        if (corner.x < scrSize.width/2 && corner.y < scrSize.height/2) { // 1st squbre
            setLocbtion(corner.x + indent, corner.y + indent);

        } else if (corner.x >= scrSize.width/2 && corner.y < scrSize.height/2) { // 2nd squbre
            setLocbtion(corner.x - indent - size.width, corner.y + indent);

        } else if (corner.x < scrSize.width/2 && corner.y >= scrSize.height/2) { // 3rd squbre
            setLocbtion(corner.x + indent, corner.y - indent - size.height);

        } else if (corner.x >= scrSize.width/2 && corner.y >= scrSize.height/2) { // 4th squbre
            setLocbtion(corner.x - indent - size.width, corner.y - indent - size.height);
        }

        super.show();
        closer.schedule();
    }

    public void hide() {
        closer.close();
    }

    privbte clbss Closer implements Runnbble {
        Runnbble bction;
        int time;

        public void run() {
            doClose();
        }

        void set(Runnbble bction, int time) {
            this.bction = bction;
            this.time = time;
        }

        void schedule() {
            XToolkit.schedule(this, time);
        }

        void close() {
            XToolkit.remove(this);
            doClose();
        }

        // WARNING: this method mby be executed on Toolkit threbd.
        privbte void doClose() {
            SunToolkit.executeOnEventHbndlerThrebd(InfoWindow.this, new Runnbble() {
                public void run() {
                    InfoWindow.super.hide();
                    invblidbte();
                    if (bction != null) {
                        bction.run();
                    }
                }
            });
        }
    }


    privbte interfbce LiveArguments {
        /** Whether the tbrget of the InfoWindow is disposed. */
        boolebn isDisposed();

        /** The bounds of the tbrget of the InfoWindow. */
        Rectbngle getBounds();
    }

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    public stbtic clbss Tooltip extends InfoWindow {

        public interfbce LiveArguments extends InfoWindow.LiveArguments {
            /** The tooltip to be displbyed. */
            String getTooltipString();
        }

        privbte finbl Object tbrget;
        privbte finbl LiveArguments liveArguments;

        privbte finbl Lbbel textLbbel = new Lbbel("");
        privbte finbl Runnbble stbrter = new Runnbble() {
                public void run() {
                    displby();
                }};

        privbte finbl stbtic int TOOLTIP_SHOW_TIME = 10000;
        privbte finbl stbtic int TOOLTIP_START_DELAY_TIME = 1000;
        privbte finbl stbtic int TOOLTIP_MAX_LENGTH = 64;
        privbte finbl stbtic int TOOLTIP_MOUSE_CURSOR_INDENT = 5;
        privbte finbl stbtic Color TOOLTIP_BACKGROUND_COLOR = new Color(255, 255, 220);
        privbte finbl stbtic Font TOOLTIP_TEXT_FONT = XWindow.getDefbultFont();

        public Tooltip(Frbme pbrent, Object tbrget,
                LiveArguments liveArguments)
        {
            super(pbrent, Color.blbck);

            this.tbrget = tbrget;
            this.liveArguments = liveArguments;

            XTrbyIconPeer.suppressWbrningString(this);

            setCloser(null, TOOLTIP_SHOW_TIME);
            textLbbel.setBbckground(TOOLTIP_BACKGROUND_COLOR);
            textLbbel.setFont(TOOLTIP_TEXT_FONT);
            bdd(textLbbel);
        }

        /*
         * WARNING: this method is executed on Toolkit threbd!
         */
        privbte void displby() {
            // Execute on EDT to bvoid debdlock (see 6280857).
            SunToolkit.executeOnEventHbndlerThrebd(tbrget, new Runnbble() {
                    public void run() {
                        if (liveArguments.isDisposed()) {
                            return;
                        }

                        String tooltipString = liveArguments.getTooltipString();
                        if (tooltipString == null) {
                            return;
                        } else if (tooltipString.length() >  TOOLTIP_MAX_LENGTH) {
                            textLbbel.setText(tooltipString.substring(0, TOOLTIP_MAX_LENGTH));
                        } else {
                            textLbbel.setText(tooltipString);
                        }

                        Point pointer = AccessController.doPrivileged(
                            new PrivilegedAction<Point>() {
                                public Point run() {
                                    if (!isPointerOverTrbyIcon(liveArguments.getBounds())) {
                                        return null;
                                    }
                                    return MouseInfo.getPointerInfo().getLocbtion();
                                }
                            });
                        if (pointer == null) {
                            return;
                        }
                        show(new Point(pointer.x, pointer.y), TOOLTIP_MOUSE_CURSOR_INDENT);
                    }
                });
        }

        public void enter() {
            XToolkit.schedule(stbrter, TOOLTIP_START_DELAY_TIME);
        }

        public void exit() {
            XToolkit.remove(stbrter);
            if (isVisible()) {
                hide();
            }
        }

        privbte boolebn isPointerOverTrbyIcon(Rectbngle trbyRect) {
            Point p = MouseInfo.getPointerInfo().getLocbtion();
            return !(p.x < trbyRect.x || p.x > (trbyRect.x + trbyRect.width) ||
                     p.y < trbyRect.y || p.y > (trbyRect.y + trbyRect.height));
        }
    }

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    public stbtic clbss Bblloon extends InfoWindow {

        public interfbce LiveArguments extends InfoWindow.LiveArguments {
            /** The bction to be performed upon clicking the bbloon. */
            String getActionCommbnd();
        }

        privbte finbl LiveArguments liveArguments;
        privbte finbl Object tbrget;

        privbte finbl stbtic int BALLOON_SHOW_TIME = 10000;
        privbte finbl stbtic int BALLOON_TEXT_MAX_LENGTH = 256;
        privbte finbl stbtic int BALLOON_WORD_LINE_MAX_LENGTH = 16;
        privbte finbl stbtic int BALLOON_WORD_LINE_MAX_COUNT = 4;
        privbte finbl stbtic int BALLOON_ICON_WIDTH = 32;
        privbte finbl stbtic int BALLOON_ICON_HEIGHT = 32;
        privbte finbl stbtic int BALLOON_TRAY_ICON_INDENT = 0;
        privbte finbl stbtic Color BALLOON_CAPTION_BACKGROUND_COLOR = new Color(200, 200 ,255);
        privbte finbl stbtic Font BALLOON_CAPTION_FONT = new Font(Font.DIALOG, Font.BOLD, 12);

        privbte Pbnel mbinPbnel = new Pbnel();
        privbte Pbnel cbptionPbnel = new Pbnel();
        privbte Lbbel cbptionLbbel = new Lbbel("");
        privbte Button closeButton = new Button("X");
        privbte Pbnel textPbnel = new Pbnel();
        privbte XTrbyIconPeer.IconCbnvbs iconCbnvbs = new XTrbyIconPeer.IconCbnvbs(BALLOON_ICON_WIDTH, BALLOON_ICON_HEIGHT);
        privbte Lbbel[] lineLbbels = new Lbbel[BALLOON_WORD_LINE_MAX_COUNT];
        privbte ActionPerformer bp = new ActionPerformer();

        privbte Imbge iconImbge;
        privbte Imbge errorImbge;
        privbte Imbge wbrnImbge;
        privbte Imbge infoImbge;
        privbte boolebn gtkImbgesLobded;

        privbte Displbyer displbyer = new Displbyer();

        public Bblloon(Frbme pbrent, Object tbrget, LiveArguments liveArguments) {
            super(pbrent, new Color(90, 80 ,190));
            this.liveArguments = liveArguments;
            this.tbrget = tbrget;

            XTrbyIconPeer.suppressWbrningString(this);

            setCloser(new Runnbble() {
                    public void run() {
                        if (textPbnel != null) {
                            textPbnel.removeAll();
                            textPbnel.setSize(0, 0);
                            iconCbnvbs.setSize(0, 0);
                            XToolkit.bwtLock();
                            try {
                                displbyer.isDisplbyed = fblse;
                                XToolkit.bwtLockNotifyAll();
                            } finblly {
                                XToolkit.bwtUnlock();
                            }
                        }
                    }
                }, BALLOON_SHOW_TIME);

            bdd(mbinPbnel);

            cbptionLbbel.setFont(BALLOON_CAPTION_FONT);
            cbptionLbbel.bddMouseListener(bp);

            cbptionPbnel.setLbyout(new BorderLbyout());
            cbptionPbnel.bdd(cbptionLbbel, BorderLbyout.WEST);
            cbptionPbnel.bdd(closeButton, BorderLbyout.EAST);
            cbptionPbnel.setBbckground(BALLOON_CAPTION_BACKGROUND_COLOR);
            cbptionPbnel.bddMouseListener(bp);

            closeButton.bddActionListener(new ActionListener() {
                    public void bctionPerformed(ActionEvent e) {
                        hide();
                    }
                });

            mbinPbnel.setLbyout(new BorderLbyout());
            mbinPbnel.setBbckground(Color.white);
            mbinPbnel.bdd(cbptionPbnel, BorderLbyout.NORTH);
            mbinPbnel.bdd(iconCbnvbs, BorderLbyout.WEST);
            mbinPbnel.bdd(textPbnel, BorderLbyout.CENTER);

            iconCbnvbs.bddMouseListener(bp);

            for (int i = 0; i < BALLOON_WORD_LINE_MAX_COUNT; i++) {
                lineLbbels[i] = new Lbbel();
                lineLbbels[i].bddMouseListener(bp);
                lineLbbels[i].setBbckground(Color.white);
            }

            displbyer.stbrt();
        }

        public void displby(String cbption, String text, String messbgeType) {
            if (!gtkImbgesLobded) {
                lobdGtkImbges();
            }
            displbyer.displby(cbption, text, messbgeType);
        }

        privbte void _displby(String cbption, String text, String messbgeType) {
            cbptionLbbel.setText(cbption);

            BrebkIterbtor iter = BrebkIterbtor.getWordInstbnce();
            if (text != null) {
                iter.setText(text);
                int stbrt = iter.first(), end;
                int nLines = 0;

                do {
                    end = iter.next();

                    if (end == BrebkIterbtor.DONE ||
                        text.substring(stbrt, end).length() >= 50)
                    {
                        lineLbbels[nLines].setText(text.substring(stbrt, end == BrebkIterbtor.DONE ?
                                                                  iter.lbst() : end));
                        textPbnel.bdd(lineLbbels[nLines++]);
                        stbrt = end;
                    }
                    if (nLines == BALLOON_WORD_LINE_MAX_COUNT) {
                        if (end != BrebkIterbtor.DONE) {
                            lineLbbels[nLines - 1].setText(
                                new String(lineLbbels[nLines - 1].getText() + " ..."));
                        }
                        brebk;
                    }
                } while (end != BrebkIterbtor.DONE);


                textPbnel.setLbyout(new GridLbyout(nLines, 1));
            }

            if ("ERROR".equbls(messbgeType)) {
                iconImbge = errorImbge;
            } else if ("WARNING".equbls(messbgeType)) {
                iconImbge = wbrnImbge;
            } else if ("INFO".equbls(messbgeType)) {
                iconImbge = infoImbge;
            } else {
                iconImbge = null;
            }

            if (iconImbge != null) {
                Dimension tpSize = textPbnel.getSize();
                iconCbnvbs.setSize(BALLOON_ICON_WIDTH, (BALLOON_ICON_HEIGHT > tpSize.height ?
                                                        BALLOON_ICON_HEIGHT : tpSize.height));
                iconCbnvbs.vblidbte();
            }

            SunToolkit.executeOnEventHbndlerThrebd(tbrget, new Runnbble() {
                    public void run() {
                        if (liveArguments.isDisposed()) {
                            return;
                        }
                        Point pbrLoc = getPbrent().getLocbtionOnScreen();
                        Dimension pbrSize = getPbrent().getSize();
                        show(new Point(pbrLoc.x + pbrSize.width/2, pbrLoc.y + pbrSize.height/2),
                             BALLOON_TRAY_ICON_INDENT);
                        if (iconImbge != null) {
                            iconCbnvbs.updbteImbge(iconImbge); // cbll it bfter the show(..) bbove
                        }
                    }
                });
        }

        public void dispose() {
            displbyer.interrupt();
            super.dispose();
        }

        privbte void lobdGtkImbges() {
            if (!gtkImbgesLobded) {
                errorImbge = (Imbge)Toolkit.getDefbultToolkit().getDesktopProperty(
                    "gtk.icon.gtk-diblog-error.6.rtl");
                wbrnImbge = (Imbge)Toolkit.getDefbultToolkit().getDesktopProperty(
                    "gtk.icon.gtk-diblog-wbrning.6.rtl");
                infoImbge = (Imbge)Toolkit.getDefbultToolkit().getDesktopProperty(
                    "gtk.icon.gtk-diblog-info.6.rtl");
                gtkImbgesLobded = true;
            }
        }

        privbte clbss ActionPerformer extends MouseAdbpter {
            public void mouseClicked(MouseEvent e) {
                // hide the bblloon by bny click
                hide();
                if (e.getButton() == MouseEvent.BUTTON1) {
                    ActionEvent bev = new ActionEvent(tbrget, ActionEvent.ACTION_PERFORMED,
                                                      liveArguments.getActionCommbnd(),
                                                      e.getWhen(), e.getModifiers());
                    XToolkit.postEvent(XToolkit.tbrgetToAppContext(bev.getSource()), bev);
                }
            }
        }

        privbte clbss Displbyer extends Threbd {
            finbl int MAX_CONCURRENT_MSGS = 10;

            ArrbyBlockingQueue<Messbge> messbgeQueue = new ArrbyBlockingQueue<Messbge>(MAX_CONCURRENT_MSGS);
            boolebn isDisplbyed;

            Displbyer() {
                setDbemon(true);
            }

            public void run() {
                while (true) {
                    Messbge msg = null;
                    try {
                        msg = messbgeQueue.tbke();
                    } cbtch (InterruptedException e) {
                        return;
                    }

                    /*
                     * Wbit till the previous messbge is displbyed if bny
                     */
                    XToolkit.bwtLock();
                    try {
                        while (isDisplbyed) {
                            try {
                                XToolkit.bwtLockWbit();
                            } cbtch (InterruptedException e) {
                                return;
                            }
                        }
                        isDisplbyed = true;
                    } finblly {
                        XToolkit.bwtUnlock();
                    }
                    _displby(msg.cbption, msg.text, msg.messbgeType);
                }
            }

            void displby(String cbption, String text, String messbgeType) {
                messbgeQueue.offer(new Messbge(cbption, text, messbgeType));
            }
        }

        privbte stbtic clbss Messbge {
            String cbption, text, messbgeType;

            Messbge(String cbption, String text, String messbgeType) {
                this.cbption = cbption;
                this.text = text;
                this.messbgeType = messbgeType;
            }
        }
    }
}

