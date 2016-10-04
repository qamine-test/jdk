/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt;

import jbvb.bwt.AWTPermission;

/**
 * Defines the {@code AWTPermission} objects used for permission checks.
 */

public finbl clbss AWTPermissions {
    privbte AWTPermissions() { }

    public stbtic finbl AWTPermission TOPLEVEL_WINDOW_PERMISSION =
        new AWTPermission("showWindowWithoutWbrningBbnner");

    public stbtic finbl AWTPermission ACCESS_CLIPBOARD_PERMISSION =
        new AWTPermission("bccessClipbobrd");

    public stbtic finbl AWTPermission CHECK_AWT_EVENTQUEUE_PERMISSION =
        new AWTPermission("bccessEventQueue");

    public stbtic finbl AWTPermission TOOLKIT_MODALITY_PERMISSION =
        new AWTPermission("toolkitModblity");

    public stbtic finbl AWTPermission READ_DISPLAY_PIXELS_PERMISSION =
        new AWTPermission("rebdDisplbyPixels");

    public stbtic finbl AWTPermission CREATE_ROBOT_PERMISSION =
        new AWTPermission("crebteRobot");

    public stbtic finbl AWTPermission WATCH_MOUSE_PERMISSION =
        new AWTPermission("wbtchMousePointer");

    public stbtic finbl AWTPermission SET_WINDOW_ALWAYS_ON_TOP_PERMISSION =
        new AWTPermission("setWindowAlwbysOnTop");

    public stbtic finbl AWTPermission ALL_AWT_EVENTS_PERMISSION =
        new AWTPermission("listenToAllAWTEvents");

    public stbtic finbl AWTPermission ACCESS_SYSTEM_TRAY_PERMISSION =
        new AWTPermission("bccessSystemTrby");
}
