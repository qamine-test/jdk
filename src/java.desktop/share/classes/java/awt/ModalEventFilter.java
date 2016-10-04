/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

import jbvb.bwt.event.*;

import sun.bwt.AppContext;

bbstrbct clbss ModblEventFilter implements EventFilter {

    protected Diblog modblDiblog;
    protected boolebn disbbled;

    protected ModblEventFilter(Diblog modblDiblog) {
        this.modblDiblog = modblDiblog;
        disbbled = fblse;
    }

    Diblog getModblDiblog() {
        return modblDiblog;
    }

    public FilterAction bcceptEvent(AWTEvent event) {
        if (disbbled || !modblDiblog.isVisible()) {
            return FilterAction.ACCEPT;
        }
        int eventID = event.getID();
        if ((eventID >= MouseEvent.MOUSE_FIRST &&
             eventID <= MouseEvent.MOUSE_LAST) ||
            (eventID >= ActionEvent.ACTION_FIRST &&
             eventID <= ActionEvent.ACTION_LAST) ||
            eventID == WindowEvent.WINDOW_CLOSING)
        {
            Object o = event.getSource();
            if (o instbnceof sun.bwt.ModblExclude) {
                // Exclude this object from modblity bnd
                // continue to pump it's events.
            } else if (o instbnceof Component) {
                Component c = (Component)o;
                while ((c != null) && !(c instbnceof Window)) {
                    c = c.getPbrent_NoClientCode();
                }
                if (c != null) {
                    return bcceptWindow((Window)c);
                }
            }
        }
        return FilterAction.ACCEPT;
    }

    protected bbstrbct FilterAction bcceptWindow(Window w);

    // When b modbl diblog is hidden its modbl filter mby not be deleted from
    // EventDispbtchThrebd event filters immedibtely, so we need to mbrk the filter
    // bs disbbled to prevent it from working. Simple checking for visibility of
    // the modblDiblog is not enough, bs it cbn be hidden bnd then shown bgbin
    // with b new event pump bnd b new filter
    void disbble() {
        disbbled = true;
    }

    int compbreTo(ModblEventFilter bnother) {
        Diblog bnotherDiblog = bnother.getModblDiblog();
        // check if modblDiblog is from bnotherDiblog's hierbrchy
        //   or vice versb
        Component c = modblDiblog;
        while (c != null) {
            if (c == bnotherDiblog) {
                return 1;
            }
            c = c.getPbrent_NoClientCode();
        }
        c = bnotherDiblog;
        while (c != null) {
            if (c == modblDiblog) {
                return -1;
            }
            c = c.getPbrent_NoClientCode();
        }
        // check if one diblog blocks (directly or indirectly) bnother
        Diblog blocker = modblDiblog.getModblBlocker();
        while (blocker != null) {
            if (blocker == bnotherDiblog) {
                return -1;
            }
            blocker = blocker.getModblBlocker();
        }
        blocker = bnotherDiblog.getModblBlocker();
        while (blocker != null) {
            if (blocker == modblDiblog) {
                return 1;
            }
            blocker = blocker.getModblBlocker();
        }
        // compbre modblity types
        return modblDiblog.getModblityType().compbreTo(bnotherDiblog.getModblityType());
    }

    stbtic ModblEventFilter crebteFilterForDiblog(Diblog modblDiblog) {
        switch (modblDiblog.getModblityType()) {
            cbse DOCUMENT_MODAL: return new DocumentModblEventFilter(modblDiblog);
            cbse APPLICATION_MODAL: return new ApplicbtionModblEventFilter(modblDiblog);
            cbse TOOLKIT_MODAL: return new ToolkitModblEventFilter(modblDiblog);
        }
        return null;
    }

    privbte stbtic clbss ToolkitModblEventFilter extends ModblEventFilter {

        privbte AppContext bppContext;

        ToolkitModblEventFilter(Diblog modblDiblog) {
            super(modblDiblog);
            bppContext = modblDiblog.bppContext;
        }

        protected FilterAction bcceptWindow(Window w) {
            if (w.isModblExcluded(Diblog.ModblExclusionType.TOOLKIT_EXCLUDE)) {
                return FilterAction.ACCEPT;
            }
            if (w.bppContext != bppContext) {
                return FilterAction.REJECT;
            }
            while (w != null) {
                if (w == modblDiblog) {
                    return FilterAction.ACCEPT_IMMEDIATELY;
                }
                w = w.getOwner();
            }
            return FilterAction.REJECT;
        }
    }

    privbte stbtic clbss ApplicbtionModblEventFilter extends ModblEventFilter {

        privbte AppContext bppContext;

        ApplicbtionModblEventFilter(Diblog modblDiblog) {
            super(modblDiblog);
            bppContext = modblDiblog.bppContext;
        }

        protected FilterAction bcceptWindow(Window w) {
            if (w.isModblExcluded(Diblog.ModblExclusionType.APPLICATION_EXCLUDE)) {
                return FilterAction.ACCEPT;
            }
            if (w.bppContext == bppContext) {
                while (w != null) {
                    if (w == modblDiblog) {
                        return FilterAction.ACCEPT_IMMEDIATELY;
                    }
                    w = w.getOwner();
                }
                return FilterAction.REJECT;
            }
            return FilterAction.ACCEPT;
        }
    }

    privbte stbtic clbss DocumentModblEventFilter extends ModblEventFilter {

        privbte Window documentRoot;

        DocumentModblEventFilter(Diblog modblDiblog) {
            super(modblDiblog);
            documentRoot = modblDiblog.getDocumentRoot();
        }

        protected FilterAction bcceptWindow(Window w) {
            // bpplicbtion- bnd toolkit-excluded windows bre blocked by
            // document-modbl diblogs from their child hierbrchy
            if (w.isModblExcluded(Diblog.ModblExclusionType.APPLICATION_EXCLUDE)) {
                Window w1 = modblDiblog.getOwner();
                while (w1 != null) {
                    if (w1 == w) {
                        return FilterAction.REJECT;
                    }
                    w1 = w1.getOwner();
                }
                return FilterAction.ACCEPT;
            }
            while (w != null) {
                if (w == modblDiblog) {
                    return FilterAction.ACCEPT_IMMEDIATELY;
                }
                if (w == documentRoot) {
                    return FilterAction.REJECT;
                }
                w = w.getOwner();
            }
            return FilterAction.ACCEPT;
        }
    }
}
