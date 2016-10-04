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

typedef LONG (*FPTR_SCbrdEstbblishContext)(ULONG dwScope,
                const void *pvReserved1,
                const void *pvReserved2,
                LONG *phContext);

typedef LONG (*FPTR_SCbrdConnect)(LONG hContext,
                const chbr *szRebder,
                ULONG dwShbreMode,
                ULONG dwPreferredProtocols,
                LONG *phCbrd, ULONG *pdwActiveProtocol);

typedef LONG (*FPTR_SCbrdDisconnect)(LONG hCbrd, ULONG dwDisposition);

typedef LONG (*FPTR_SCbrdStbtus)(LONG hCbrd,
                chbr *mszRebderNbmes,
                ULONG *pcchRebderLen,
                ULONG *pdwStbte,
                ULONG *pdwProtocol,
                unsigned chbr *pbAtr, ULONG *pcbAtrLen);

typedef LONG (*FPTR_SCbrdGetStbtusChbnge)(LONG hContext,
                ULONG dwTimeout,
                LPSCARD_READERSTATE_A rgRebderStbtes, ULONG cRebders);

typedef LONG (*FPTR_SCbrdTrbnsmit)(LONG hCbrd,
                LPCSCARD_IO_REQUEST pioSendPci,
                const unsigned chbr *pbSendBuffer,
                ULONG cbSendLength,
                LPSCARD_IO_REQUEST pioRecvPci,
                unsigned chbr *pbRecvBuffer, ULONG *pcbRecvLength);

typedef LONG (*FPTR_SCbrdListRebders)(LONG hContext,
                const chbr *mszGroups,
                chbr *mszRebders, ULONG *pcchRebders);

typedef LONG (*FPTR_SCbrdBeginTrbnsbction)(LONG hCbrd);

typedef LONG (*FPTR_SCbrdEndTrbnsbction)(LONG hCbrd, ULONG dwDisposition);

typedef LONG (*FPTR_SCbrdControl)(LONG hCbrd, ULONG dwControlCode,
    const void* pbSendBuffer, ULONG cbSendLength, const void* pbRecvBuffer,
    ULONG pcbRecvLength, ULONG *lpBytesReturned);

#define CALL_SCbrdEstbblishContext(dwScope, pvReserved1, pvReserved2, phContext) \
    ((scbrdEstbblishContext)(dwScope, pvReserved1, pvReserved2, phContext))

#define CALL_SCbrdConnect(hContext, szRebder, dwShbredMode, dwPreferredProtocols, phCbrd, pdwActiveProtocols) \
    ((scbrdConnect)(hContext, szRebder, dwShbredMode, dwPreferredProtocols, phCbrd, pdwActiveProtocols))

#define CALL_SCbrdDisconnect(hCbrd, dwDisposition) \
    ((scbrdDisconnect)(hCbrd, dwDisposition))

#define CALL_SCbrdStbtus(hCbrd, mszRebderNbmes, pcchRebderLen, pdwStbte, pdwProtocol, pbAtr, pcbAtrLen) \
    ((scbrdStbtus)(hCbrd, mszRebderNbmes, pcchRebderLen, pdwStbte, pdwProtocol, pbAtr, pcbAtrLen))

#define CALL_SCbrdGetStbtusChbnge(hContext, dwTimeout, rgRebderStbtes, cRebders) \
    ((scbrdGetStbtusChbnge)(hContext, dwTimeout, rgRebderStbtes, cRebders))

#define CALL_SCbrdTrbnsmit(hCbrd, pioSendPci, pbSendBuffer, cbSendLength, \
                            pioRecvPci, pbRecvBuffer, pcbRecvLength) \
    ((scbrdTrbnsmit)(hCbrd, pioSendPci, pbSendBuffer, cbSendLength, \
                            pioRecvPci, pbRecvBuffer, pcbRecvLength))

#define CALL_SCbrdListRebders(hContext, mszGroups, mszRebders, pcchRebders) \
    ((scbrdListRebders)(hContext, mszGroups, mszRebders, pcchRebders))

#define CALL_SCbrdBeginTrbnsbction(hCbrd) \
    ((scbrdBeginTrbnsbction)(hCbrd))

#define CALL_SCbrdEndTrbnsbction(hCbrd, dwDisposition) \
    ((scbrdEndTrbnsbction)(hCbrd, dwDisposition))

#define CALL_SCbrdControl(hCbrd, dwControlCode, pbSendBuffer, cbSendLength, \
            pbRecvBuffer, pcbRecvLength, lpBytesReturned) \
    ((scbrdControl)(hCbrd, dwControlCode, pbSendBuffer, cbSendLength, \
            pbRecvBuffer, pcbRecvLength, lpBytesReturned))

extern FPTR_SCbrdEstbblishContext scbrdEstbblishContext;
extern FPTR_SCbrdConnect scbrdConnect;
extern FPTR_SCbrdDisconnect scbrdDisconnect;
extern FPTR_SCbrdStbtus scbrdStbtus;
extern FPTR_SCbrdGetStbtusChbnge scbrdGetStbtusChbnge;
extern FPTR_SCbrdTrbnsmit scbrdTrbnsmit;
extern FPTR_SCbrdListRebders scbrdListRebders;
extern FPTR_SCbrdBeginTrbnsbction scbrdBeginTrbnsbction;
extern FPTR_SCbrdEndTrbnsbction scbrdEndTrbnsbction;
extern FPTR_SCbrdControl scbrdControl;
