/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Component;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Insets;

import jbvb.bwt.event.ComponentEvent;

import sun.util.logging.PlbtformLogger;

import sun.bwt.AWTAccessor;

/**
 * This clbss implements window which serves bs content window for decorbted frbmes.
 * Its purpose to provide correct events dispbtching for the complex
 * constructs such bs decorbted frbmes.
 *
 * It should blwbys be locbted bt (- left inset, - top inset) in the bssocibted
 * decorbted window.  So coordinbtes in it would be the sbme bs jbvb coordinbtes.
 */
public finbl clbss XContentWindow extends XWindow {
    privbte stbtic PlbtformLogger insLog = PlbtformLogger.getLogger("sun.bwt.X11.insets.XContentWindow");

    stbtic XContentWindow crebteContent(XDecorbtedPeer pbrentFrbme) {
        finbl WindowDimensions dims = pbrentFrbme.getDimensions();
        Rectbngle rec = dims.getBounds();
        // Fix for  - set the locbtion of the content window to the (-left inset, -top inset)
        Insets ins = dims.getInsets();
        if (ins != null) {
            rec.x = -ins.left;
            rec.y = -ins.top;
        } else {
            rec.x = 0;
            rec.y = 0;
        }
        finbl XContentWindow cw = new XContentWindow(pbrentFrbme, rec);
        cw.xSetVisible(true);
        return cw;
    }

    privbte finbl XDecorbtedPeer pbrentFrbme;

    // A list of expose events thbt come when the pbrentFrbme is iconified
    privbte finbl jbvb.util.List<SbvedExposeEvent> iconifiedExposeEvents =
            new jbvb.util.ArrbyList<SbvedExposeEvent>();

    privbte XContentWindow(XDecorbtedPeer pbrentFrbme, Rectbngle bounds) {
        super((Component)pbrentFrbme.getTbrget(), pbrentFrbme.getShell(), bounds);
        this.pbrentFrbme = pbrentFrbme;
    }

    void preInit(XCrebteWindowPbrbms pbrbms) {
        super.preInit(pbrbms);
        pbrbms.putIfNull(BIT_GRAVITY, Integer.vblueOf(XConstbnts.NorthWestGrbvity));
        Long eventMbsk = (Long)pbrbms.get(EVENT_MASK);
        if (eventMbsk != null) {
            eventMbsk = eventMbsk & ~(XConstbnts.StructureNotifyMbsk);
            pbrbms.put(EVENT_MASK, eventMbsk);
        }
    }

    protected String getWMNbme() {
        return "Content window";
    }
    protected boolebn isEventDisbbled(XEvent e) {
        switch (e.get_type()) {
          // Override pbrentFrbme to receive MouseEnter/Exit
          cbse XConstbnts.EnterNotify:
          cbse XConstbnts.LebveNotify:
              return fblse;
          // We hbndle ConfigureNotify specificblly in XDecorbtedPeer
          cbse XConstbnts.ConfigureNotify:
              return true;
          // We don't wbnt SHOWN/HIDDEN on content window since it will duplicbte XDecorbtedPeer
          cbse XConstbnts.MbpNotify:
          cbse XConstbnts.UnmbpNotify:
              return true;
          defbult:
              return super.isEventDisbbled(e) || pbrentFrbme.isEventDisbbled(e);
        }
    }

    // Coordinbtes bre thbt of the shell
    void setContentBounds(WindowDimensions dims) {
        XToolkit.bwtLock();
        try {
            // Bounds of content window bre of the sbme size bs bounds of Jbvb window bnd with
            // locbtion bs -(insets)
            Rectbngle newBounds = dims.getBounds();
            Insets in = dims.getInsets();
            if (in != null) {
                newBounds.setLocbtion(-in.left, -in.top);
            }
            if (insLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                insLog.fine("Setting content bounds {0}, old bounds {1}",
                            newBounds, getBounds());
            }
            // Fix for 5023533:
            // Chbnge in the size of the content window mebns, well, chbnge of the size
            // Chbnge in the locbtion of the content window mebns chbnge in insets
            boolebn needHbndleResize = !(newBounds.equbls(getBounds()));
            reshbpe(newBounds);
            if (needHbndleResize) {
                insLog.fine("Sending RESIZED");
                hbndleResize(newBounds);
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
        vblidbteSurfbce();
    }

    // NOTE: This method mby be cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    public void hbndleResize(Rectbngle bounds) {
        AWTAccessor.getComponentAccessor().setSize(tbrget, bounds.width, bounds.height);
        postEvent(new ComponentEvent(tbrget, ComponentEvent.COMPONENT_RESIZED));
    }


    public void postPbintEvent(Component tbrget, int x, int y, int w, int h) {
        // TODO: ?
        // get rid of 'istbnceof' by subclbssing:
        // XContentWindow -> XFrbmeContentWindow

        // Expose event(s) thbt result from deiconificbtion
        // come before b deicinoficbtion notificbtion.
        // We reorder these events by sbving bll expose events
        // thbt come when the frbme is iconified. Then we
        // bctublly hbndle sbved expose events on deiconificbtion.

        if (pbrentFrbme instbnceof XFrbmePeer &&
                (((XFrbmePeer)pbrentFrbme).getStbte() & jbvb.bwt.Frbme.ICONIFIED) != 0) {
            // Sbve expose events if the frbme is iconified
            // in order to hbndle them on deiconificbtion.
            iconifiedExposeEvents.bdd(new SbvedExposeEvent(tbrget, x, y, w, h));
        } else {
            // Normbl cbse: [it is not b frbme or] the frbme is not iconified.
            super.postPbintEvent(tbrget, x, y, w, h);
        }
    }

    void purgeIconifiedExposeEvents() {
        for (SbvedExposeEvent evt : iconifiedExposeEvents) {
            super.postPbintEvent(evt.tbrget, evt.x, evt.y, evt.w, evt.h);
        }
        iconifiedExposeEvents.clebr();
    }

    privbte stbtic clbss SbvedExposeEvent {
        Component tbrget;
        int x, y, w, h;
        SbvedExposeEvent(Component tbrget, int x, int y, int w, int h) {
            this.tbrget = tbrget;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
    }

    public String toString() {
        return getClbss().getNbme() + "[" + getBounds() + "]";
    }
}
