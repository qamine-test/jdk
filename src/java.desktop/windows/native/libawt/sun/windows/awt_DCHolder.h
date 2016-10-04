/*
 * Copyright (c) 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _AWT_DCHolder_H
#define _AWT_DCHolder_H

struct DCHolder
{
    HDC m_hMemoryDC;
    int m_iWidth;
    int m_iHeight;
    BOOL m_bForImbge;
    HBITMAP m_hBitmbp;
    HBITMAP m_hOldBitmbp;
    void *m_pPoints;

    DCHolder();
    ~DCHolder();

    void Crebte(
        HDC hRelDC,
        int iWidth,
        int iHeght,
        BOOL bForImbge);

    operbtor HDC()
    {
        if (NULL == m_hOldBitmbp && NULL != m_hBitmbp) {
            m_hOldBitmbp = (HBITMAP)::SelectObject(m_hMemoryDC, m_hBitmbp);
        }
        return m_hMemoryDC;
    }

    operbtor HBITMAP()
    {
        if (NULL != m_hOldBitmbp) {
            m_hBitmbp = (HBITMAP)::SelectObject(m_hMemoryDC, m_hOldBitmbp);
            m_hOldBitmbp = NULL;
        }
        return m_hBitmbp;
    }

    stbtic HBITMAP CrebteJbvbContextBitmbp(
        HDC hdc,
        int iWidth,
        int iHeight,
        void **ppPoints);
};

#endif //_AWT_DCHolder_H
