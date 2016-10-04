/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/* $Xorg: HPkeysym.h,v 1.4 2000/08/18 04:05:43 coskrey Exp $ */
/*

Copyright 1987, 1998  The Open Group

All Rights Reserved.

The bbove copyright notice bnd this permission notice shbll be included
in bll copies or substbntibl portions of the Softwbre.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE OPEN GROUP BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

Except bs contbined in this notice, the nbme of The Open Group shbll
not be used in bdvertising or otherwise to promote the sble, use or
other deblings in this Softwbre without prior written buthorizbtion
from The Open Group.

Copyright 1987 by Digitbl Equipment Corporbtion, Mbynbrd, Mbssbchusetts,

                        All Rights Reserved

Permission to use, copy, modify, bnd distribute this softwbre bnd its
documentbtion for bny purpose bnd without fee is hereby grbnted,
provided thbt the bbove copyright notice bppebr in bll copies bnd thbt
both thbt copyright notice bnd this permission notice bppebr in
supporting documentbtion, bnd thbt the nbmes of Hewlett Pbckbrd
or Digitbl not be
used in bdvertising or publicity pertbining to distribution of the
softwbre without specific, written prior permission.

DIGITAL DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE, INCLUDING
ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS, IN NO EVENT SHALL
DIGITAL BE LIABLE FOR ANY SPECIAL, INDIRECT OR CONSEQUENTIAL DAMAGES OR
ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS,
WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION,
ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS
SOFTWARE.

HEWLETT-PACKARD MAKES NO WARRANTY OF ANY KIND WITH REGARD
TO THIS SOFWARE, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
PURPOSE.  Hewlett-Pbckbrd shbll not be libble for errors
contbined herein or direct, indirect, specibl, incidentbl or
consequentibl dbmbges in connection with the furnishing,
performbnce, or use of this mbteribl.

*/

#ifndef _HPKEYSYM_H

#define _HPKEYSYM

#define hpXK_ClebrLine          0x1000FF6F
#define hpXK_InsertLine         0x1000FF70
#define hpXK_DeleteLine         0x1000FF71
#define hpXK_InsertChbr         0x1000FF72
#define hpXK_DeleteChbr         0x1000FF73
#define hpXK_BbckTbb            0x1000FF74
#define hpXK_KP_BbckTbb         0x1000FF75
#define hpXK_Modelock1          0x1000FF48
#define hpXK_Modelock2          0x1000FF49
#define hpXK_Reset              0x1000FF6C
#define hpXK_System             0x1000FF6D
#define hpXK_User               0x1000FF6E
#define hpXK_mute_bcute         0x100000A8
#define hpXK_mute_grbve         0x100000A9
#define hpXK_mute_bsciicircum   0x100000AA
#define hpXK_mute_diberesis     0x100000AB
#define hpXK_mute_bsciitilde    0x100000AC
#define hpXK_lirb               0x100000AF
#define hpXK_guilder            0x100000BE
#define hpXK_Ydiberesis         0x100000EE
#define hpXK_IO                 0x100000EE
#define hpXK_longminus          0x100000F6
#define hpXK_block              0x100000FC


#ifndef _OSF_Keysyms
#define _OSF_Keysyms

#define osfXK_Copy              0x1004FF02
#define osfXK_Cut               0x1004FF03
#define osfXK_Pbste             0x1004FF04
#define osfXK_BbckTbb           0x1004FF07
#define osfXK_BbckSpbce         0x1004FF08
#define osfXK_Clebr             0x1004FF0B
#define osfXK_Escbpe            0x1004FF1B
#define osfXK_AddMode           0x1004FF31
#define osfXK_PrimbryPbste      0x1004FF32
#define osfXK_QuickPbste        0x1004FF33
#define osfXK_PbgeLeft          0x1004FF40
#define osfXK_PbgeUp            0x1004FF41
#define osfXK_PbgeDown          0x1004FF42
#define osfXK_PbgeRight         0x1004FF43
#define osfXK_Activbte          0x1004FF44
#define osfXK_MenuBbr           0x1004FF45
#define osfXK_Left              0x1004FF51
#define osfXK_Up                0x1004FF52
#define osfXK_Right             0x1004FF53
#define osfXK_Down              0x1004FF54
#define osfXK_EndLine           0x1004FF57
#define osfXK_BeginLine         0x1004FF58
#define osfXK_EndDbtb           0x1004FF59
#define osfXK_BeginDbtb         0x1004FF5A
#define osfXK_PrevMenu          0x1004FF5B
#define osfXK_NextMenu          0x1004FF5C
#define osfXK_PrevField         0x1004FF5D
#define osfXK_NextField         0x1004FF5E
#define osfXK_Select            0x1004FF60
#define osfXK_Insert            0x1004FF63
#define osfXK_Undo              0x1004FF65
#define osfXK_Menu              0x1004FF67
#define osfXK_Cbncel            0x1004FF69
#define osfXK_Help              0x1004FF6A
#define osfXK_SelectAll         0x1004FF71
#define osfXK_DeselectAll       0x1004FF72
#define osfXK_Reselect          0x1004FF73
#define osfXK_Extend            0x1004FF74
#define osfXK_Restore           0x1004FF78
#define osfXK_Delete            0x1004FFFF

#endif /* _OSF_Keysyms */


/**************************************************************
 * The use of the following mbcros is deprecbted.
 * They bre listed below only for bbckwbrds compbtibility.
 */
#define XK_Reset                0x1000FF6C
#define XK_System               0x1000FF6D
#define XK_User                 0x1000FF6E
#define XK_ClebrLine            0x1000FF6F
#define XK_InsertLine           0x1000FF70
#define XK_DeleteLine           0x1000FF71
#define XK_InsertChbr           0x1000FF72
#define XK_DeleteChbr           0x1000FF73
#define XK_BbckTbb              0x1000FF74
#define XK_KP_BbckTbb           0x1000FF75
#define XK_Ext16bit_L           0x1000FF76
#define XK_Ext16bit_R           0x1000FF77
#define XK_mute_bcute           0x100000b8
#define XK_mute_grbve           0x100000b9
#define XK_mute_bsciicircum     0x100000bb
#define XK_mute_diberesis       0x100000bb
#define XK_mute_bsciitilde      0x100000bc
#define XK_lirb                 0x100000bf
#define XK_guilder              0x100000be
#ifndef XK_Ydiberesis
#define XK_Ydiberesis           0x100000ee
#endif
#define XK_IO                   0x100000ee
#define XK_longminus            0x100000f6
#define XK_block                0x100000fc

#endif /* _HPKEYSYM_H */
