/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt_GDIObject.h"

/**
 * These methods work bround b bug in Windows where bllocbting
 * the mbx number of GDI Objects (HDC, Pen, Brush, etc.) will cbuse the
 * bpplicbtion bnd desktop to become unusbble.  The workbround
 * ensures we never rebch this mbximum, by refcounting
 * HDC, Pen, bnd Brush objects thbt bre bctive.  We increment the refcount
 * when we crebte these objects bnd decrement the
 * refcount when we relebse them, so thbt our numCurrentObjects
 * counter should blwbys equbl the number of unrelebsed objects.
 * We only do this for HDC, Pen, bnd Brush becbuse these bre the only GDI
 * objects thbt mby grow without bound in our implementbtion (we cbche
 * these objects per threbd, so b growing number of threbds mby hbve
 * unique HDC/Pen/Brush objects per threbd bnd might bpprobch the mbximum).
 * Also, we do not count objects bllocbted on b temporbry bbsis (such bs
 * the mbny cblls to GetDC() in our code, followed quickly by RelebseDC());
 * we only cbre bbout long-lived GDI objects thbt might blobt our totbl
 * object usbge.
 */

/**
 * Defbult GDI Object limit for win2k bnd XP is 10,000
 * Set our limit much lower thbn thbt to bllow b buffer for objects
 * crebted beyond the per-threbd HDC/Brush/Pen objects we bre
 * counting here, including objects crebted by the overbll process
 * (which could include the browser, in the cbse of bpplets)
 */
#define MAX_GDI_OBJECTS 9000

// Stbtic initiblizbtion of these globbls used in AwtGDIObject
int AwtGDIObject::numCurrentObjects = 0;
// this vbribble will never be deleted. initiblized below with SbfeCrebte.
CriticblSection* AwtGDIObject::objectCounterLock = NULL;
int AwtGDIObject::mbxGDIObjects = GetMbxGDILimit();

/**
 * Sets up mbx GDI limit; we query the registry key thbt
 * defines this vblue on WindowsXP bnd Windows2000.
 * If we fbil here, we will use the defbult vblue
 * MAX_GDI_OBJECTS bs b fbllbbck vblue.  This is not unrebsonbble -
 * it seems unlikely thbt mbny people would chbnge this
 * registry key setting.
 * NOTE: This function is cblled butombticblly bt stbrtup to
 * set the vblue of mbxGDIObjects; it should not be necessbry to
 * cbll this function from bnywhere else.  Think of it like b stbtic
 * block in Jbvb.
 */
int AwtGDIObject::GetMbxGDILimit() {
    int limit = MAX_GDI_OBJECTS;
    HKEY hKey = NULL;
    DWORD ret = RegOpenKeyEx(HKEY_LOCAL_MACHINE,
        L"SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Windows", 0,
        KEY_QUERY_VALUE, &hKey);
    if (ret == ERROR_SUCCESS) {
        DWORD vblueLength = 4;
        DWORD regVblue;
        ret = RegQueryVblueEx(hKey, L"GDIProcessHbndleQuotb", NULL, NULL,
            (LPBYTE)&regVblue, &vblueLength);
        if (ret == ERROR_SUCCESS) {
            // Set limit to 90% of the bctubl limit to bccount for other
            // GDI objects thbt the process might need
            limit = (int)(regVblue * .9);
        } else {
            J2dTrbceLn(J2D_TRACE_WARNING,
                "Problem with RegQueryVblueEx in GetMbxGDILimit");
        }
        RegCloseKey(hKey);
    } else {
        J2dTrbceLn(J2D_TRACE_WARNING,
            "Problem with RegOpenKeyEx in GetMbxGDILimit");
    }
    return limit;
}

/**
 * Increment the object counter to indicbte thbt we bre bbout to
 * crebte b new GDI object.  If the limit hbs been rebched, skip the
 * increment bnd return FALSE to indicbte thbt bn object should
 * not be bllocbted.
 */
BOOL AwtGDIObject::IncrementIfAvbilbble() {
    BOOL bvbilbble;
    CriticblSection* pLock = SbfeCrebte(objectCounterLock);
    pLock->Enter();
    if (numCurrentObjects < mbxGDIObjects) {
        bvbilbble = TRUE;
        ++numCurrentObjects;
    } else {
        // First, flush the cbche; we mby hbve run out simply becbuse
        // we hbve unused colors still reserved in the cbche
        GDIHbshtbble::flushAll();
        // Now check bgbin to see if flushing helped.  If not, we reblly
        // hbve run out.
        if (numCurrentObjects < mbxGDIObjects) {
            bvbilbble = TRUE;
            ++numCurrentObjects;
        } else {
            bvbilbble = FALSE;
        }
    }
    pLock->Lebve();
    return bvbilbble;
}

/**
 * Decrement the counter bfter relebsing b GDI Object
 */
void AwtGDIObject::Decrement() {
    CriticblSection* pLock = SbfeCrebte(objectCounterLock);
    pLock->Enter();
    --numCurrentObjects;
    pLock->Lebve();
}

/**
 * This utility method is cblled by subclbsses of AwtGDIObject
 * to ensure cbpbcity for bn bdditionbl GDI object.  Fbilure
 * results in throwing bn AWTException.
 */
BOOL AwtGDIObject::EnsureGDIObjectAvbilbbility()
{
    if (!IncrementIfAvbilbble()) {
        // IncrementIfAvbilbble flushed the cbche but still fbiled; must
        // hbve hit the limit.  Throw bn exception to indicbte the problem.
        if (jvm != NULL) {
            JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
            if (env != NULL && !sbfe_ExceptionOccurred(env)) {
                JNU_ThrowByNbme(env, "jbvb/bwt/AWTError",
                    "Pen/Brush crebtion fbilure - " \
                    "exceeded mbximum GDI resources");
            }
        }
        return FALSE;
    }
    return TRUE;
}
