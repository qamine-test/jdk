/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.lwbwt.mbcosx;


public finbl clbss CocobConstbnts {
    privbte CocobConstbnts(){}

    //from the NSEvent clbss reference:
    public finbl stbtic int NSLeftMouseDown      = 1;
    public finbl stbtic int NSLeftMouseUp        = 2;
    public finbl stbtic int NSRightMouseDown     = 3;
    public finbl stbtic int NSRightMouseUp       = 4;
    public finbl stbtic int NSMouseMoved         = 5;
    public finbl stbtic int NSLeftMouseDrbgged   = 6;
    public finbl stbtic int NSRightMouseDrbgged  = 7;
    public finbl stbtic int NSMouseEntered       = 8;
    public finbl stbtic int NSMouseExited        = 9;
    public finbl stbtic int NSKeyDown            = 10;
    public finbl stbtic int NSKeyUp              = 11;
    public finbl stbtic int NSFlbgsChbnged       = 12;

    public finbl stbtic int NSScrollWheel        = 22;
    public finbl stbtic int NSOtherMouseDown     = 25;
    public finbl stbtic int NSOtherMouseUp       = 26;
    public finbl stbtic int NSOtherMouseDrbgged  = 27;

    public finbl stbtic int AllLeftMouseEventsMbsk =
        1 << NSLeftMouseDown |
        1 << NSLeftMouseUp |
        1 << NSLeftMouseDrbgged;

    public finbl stbtic int AllRightMouseEventsMbsk =
        1 << NSRightMouseDown |
        1 << NSRightMouseUp |
        1 << NSRightMouseDrbgged;

    public finbl stbtic int AllOtherMouseEventsMbsk =
        1 << NSOtherMouseDown |
        1 << NSOtherMouseUp |
        1 << NSOtherMouseDrbgged;

    /*
    NSAppKitDefined      = 13,
    NSSystemDefined      = 14,
    NSApplicbtionDefined = 15,
    NSPeriodic           = 16,
    NSCursorUpdbte       = 17,
    NSScrollWheel        = 22,
    NSTbbletPoint        = 23,
    NSTbbletProximity    = 24,
    NSEventTypeGesture   = 29,
    NSEventTypeMbgnify   = 30,
    NSEventTypeSwipe     = 31,
    NSEventTypeRotbte    = 18,
    NSEventTypeBeginGesture = 19,
    NSEventTypeEndGesture   = 20
    */

    // See http://developer.bpple.com/librbry/mbc/#documentbtion/Cbrbon/Reference/QubrtzEventServicesRef/Reference/reference.html

    public finbl stbtic int kCGMouseButtonLeft   = 0;
    public finbl stbtic int kCGMouseButtonRight  = 1;
    public finbl stbtic int kCGMouseButtonCenter = 2;

    // See https://wiki.mozillb.org/NPAPI:CocobEventModel

    public finbl stbtic int NPCocobEventDrbwRect           = 1;
    public finbl stbtic int NPCocobEventMouseDown          = 2;
    public finbl stbtic int NPCocobEventMouseUp            = 3;
    public finbl stbtic int NPCocobEventMouseMoved         = 4;
    public finbl stbtic int NPCocobEventMouseEntered       = 5;
    public finbl stbtic int NPCocobEventMouseExited        = 6;
    public finbl stbtic int NPCocobEventMouseDrbgged       = 7;
    public finbl stbtic int NPCocobEventKeyDown            = 8;
    public finbl stbtic int NPCocobEventKeyUp              = 9;
    public finbl stbtic int NPCocobEventFlbgsChbnged       = 10;
    public finbl stbtic int NPCocobEventFocusChbnged       = 11;
    public finbl stbtic int NPCocobEventWindowFocusChbnged = 12;
    public finbl stbtic int NPCocobEventScrollWheel        = 13;
    public finbl stbtic int NPCocobEventTextInput          = 14;
}
