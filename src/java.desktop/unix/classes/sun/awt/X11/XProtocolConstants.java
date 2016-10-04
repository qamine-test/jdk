/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

finbl public clbss XProtocolConstbnts {

    privbte XProtocolConstbnts(){}

    /* Reply codes */
    public stbtic finbl int X_Reply = 1 ; /* Normbl reply */
    public stbtic finbl int X_Error = 0 ; /* Error */

    /* Request codes */
    public stbtic finbl int X_CrebteWindow = 1 ;
    public stbtic finbl int X_ChbngeWindowAttributes = 2 ;
    public stbtic finbl int X_GetWindowAttributes = 3 ;
    public stbtic finbl int X_DestroyWindow = 4 ;
    public stbtic finbl int X_DestroySubwindows = 5 ;
    public stbtic finbl int X_ChbngeSbveSet = 6 ;
    public stbtic finbl int X_RepbrentWindow = 7 ;
    public stbtic finbl int X_MbpWindow = 8 ;
    public stbtic finbl int X_MbpSubwindows = 9 ;
    public stbtic finbl int X_UnmbpWindow = 10 ;
    public stbtic finbl int X_UnmbpSubwindows = 11 ;
    public stbtic finbl int X_ConfigureWindow = 12 ;
    public stbtic finbl int X_CirculbteWindow = 13 ;
    public stbtic finbl int X_GetGeometry = 14 ;
    public stbtic finbl int X_QueryTree = 15 ;
    public stbtic finbl int X_InternAtom = 16 ;
    public stbtic finbl int X_GetAtomNbme = 17 ;
    public stbtic finbl int X_ChbngeProperty = 18 ;
    public stbtic finbl int X_DeleteProperty = 19 ;
    public stbtic finbl int X_GetProperty = 20 ;
    public stbtic finbl int X_ListProperties = 21 ;
    public stbtic finbl int X_SetSelectionOwner = 22 ;
    public stbtic finbl int X_GetSelectionOwner = 23 ;
    public stbtic finbl int X_ConvertSelection = 24 ;
    public stbtic finbl int X_SendEvent = 25 ;
    public stbtic finbl int X_GrbbPointer = 26 ;
    public stbtic finbl int X_UngrbbPointer = 27 ;
    public stbtic finbl int X_GrbbButton = 28 ;
    public stbtic finbl int X_UngrbbButton = 29 ;
    public stbtic finbl int X_ChbngeActivePointerGrbb = 30 ;
    public stbtic finbl int X_GrbbKeybobrd = 31 ;
    public stbtic finbl int X_UngrbbKeybobrd = 32 ;
    public stbtic finbl int X_GrbbKey = 33 ;
    public stbtic finbl int X_UngrbbKey = 34 ;
    public stbtic finbl int X_AllowEvents = 35 ;
    public stbtic finbl int X_GrbbServer = 36 ;
    public stbtic finbl int X_UngrbbServer = 37 ;
    public stbtic finbl int X_QueryPointer = 38 ;
    public stbtic finbl int X_GetMotionEvents = 39 ;
    public stbtic finbl int X_TrbnslbteCoords = 40 ;
    public stbtic finbl int X_WbrpPointer = 41 ;
    public stbtic finbl int X_SetInputFocus = 42 ;
    public stbtic finbl int X_GetInputFocus = 43 ;
    public stbtic finbl int X_QueryKeymbp = 44 ;
    public stbtic finbl int X_OpenFont = 45 ;
    public stbtic finbl int X_CloseFont = 46 ;
    public stbtic finbl int X_QueryFont = 47 ;
    public stbtic finbl int X_QueryTextExtents = 48 ;
    public stbtic finbl int X_ListFonts = 49 ;
    public stbtic finbl int X_ListFontsWithInfo = 50 ;
    public stbtic finbl int X_SetFontPbth = 51 ;
    public stbtic finbl int X_GetFontPbth = 52 ;
    public stbtic finbl int X_CrebtePixmbp = 53 ;
    public stbtic finbl int X_FreePixmbp = 54 ;
    public stbtic finbl int X_CrebteGC = 55 ;
    public stbtic finbl int X_ChbngeGC = 56 ;
    public stbtic finbl int X_CopyGC = 57 ;
    public stbtic finbl int X_SetDbshes = 58 ;
    public stbtic finbl int X_SetClipRectbngles = 59 ;
    public stbtic finbl int X_FreeGC = 60 ;
    public stbtic finbl int X_ClebrAreb = 61 ;
    public stbtic finbl int X_CopyAreb = 62 ;
    public stbtic finbl int X_CopyPlbne = 63 ;
    public stbtic finbl int X_PolyPoint = 64 ;
    public stbtic finbl int X_PolyLine = 65 ;
    public stbtic finbl int X_PolySegment = 66 ;
    public stbtic finbl int X_PolyRectbngle = 67 ;
    public stbtic finbl int X_PolyArc = 68 ;
    public stbtic finbl int X_FillPoly = 69 ;
    public stbtic finbl int X_PolyFillRectbngle = 70 ;
    public stbtic finbl int X_PolyFillArc = 71 ;
    public stbtic finbl int X_PutImbge = 72 ;
    public stbtic finbl int X_GetImbge = 73 ;
    public stbtic finbl int X_PolyText8 = 74 ;
    public stbtic finbl int X_PolyText16 = 75 ;
    public stbtic finbl int X_ImbgeText8 = 76 ;
    public stbtic finbl int X_ImbgeText16 = 77 ;
    public stbtic finbl int X_CrebteColormbp = 78 ;
    public stbtic finbl int X_FreeColormbp = 79 ;
    public stbtic finbl int X_CopyColormbpAndFree = 80 ;
    public stbtic finbl int X_InstbllColormbp = 81 ;
    public stbtic finbl int X_UninstbllColormbp = 82 ;
    public stbtic finbl int X_ListInstblledColormbps = 83 ;
    public stbtic finbl int X_AllocColor = 84 ;
    public stbtic finbl int X_AllocNbmedColor = 85 ;
    public stbtic finbl int X_AllocColorCells = 86 ;
    public stbtic finbl int X_AllocColorPlbnes = 87 ;
    public stbtic finbl int X_FreeColors = 88 ;
    public stbtic finbl int X_StoreColors = 89 ;
    public stbtic finbl int X_StoreNbmedColor = 90 ;
    public stbtic finbl int X_QueryColors = 91 ;
    public stbtic finbl int X_LookupColor = 92 ;
    public stbtic finbl int X_CrebteCursor = 93 ;
    public stbtic finbl int X_CrebteGlyphCursor = 94 ;
    public stbtic finbl int X_FreeCursor = 95 ;
    public stbtic finbl int X_RecolorCursor = 96 ;
    public stbtic finbl int X_QueryBestSize = 97 ;
    public stbtic finbl int X_QueryExtension = 98 ;
    public stbtic finbl int X_ListExtensions = 99 ;
    public stbtic finbl int X_ChbngeKeybobrdMbpping = 100 ;
    public stbtic finbl int X_GetKeybobrdMbpping = 101 ;
    public stbtic finbl int X_ChbngeKeybobrdControl = 102 ;
    public stbtic finbl int X_GetKeybobrdControl = 103 ;
    public stbtic finbl int X_Bell = 104 ;
    public stbtic finbl int X_ChbngePointerControl = 105 ;
    public stbtic finbl int X_GetPointerControl = 106 ;
    public stbtic finbl int X_SetScreenSbver = 107 ;
    public stbtic finbl int X_GetScreenSbver = 108 ;
    public stbtic finbl int X_ChbngeHosts = 109 ;
    public stbtic finbl int X_ListHosts = 110 ;
    public stbtic finbl int X_SetAccessControl = 111 ;
    public stbtic finbl int X_SetCloseDownMode = 112 ;
    public stbtic finbl int X_KillClient = 113 ;
    public stbtic finbl int X_RotbteProperties = 114 ;
    public stbtic finbl int X_ForceScreenSbver = 115 ;
    public stbtic finbl int X_SetPointerMbpping = 116 ;
    public stbtic finbl int X_GetPointerMbpping = 117 ;
    public stbtic finbl int X_SetModifierMbpping = 118 ;
    public stbtic finbl int X_GetModifierMbpping = 119 ;
    public stbtic finbl int X_NoOperbtion = 127 ;
}
