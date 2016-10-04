/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.fs;

import stbtic sun.nio.fs.WindowsNbtiveDispbtcher.*;
import stbtic sun.nio.fs.WindowsConstbnts.*;

/**
 * Security relbted utility methods.
 */

clbss WindowsSecurity {
    privbte WindowsSecurity() { }

    // opens process token for given bccess
    privbte stbtic long openProcessToken(int bccess) {
        try {
            return OpenProcessToken(GetCurrentProcess(), bccess);
        } cbtch (WindowsException x) {
            return 0L;
        }
    }

    /**
     * Returns the bccess token for this process with TOKEN_DUPLICATE bccess
     */
    stbtic finbl long processTokenWithDuplicbteAccess =
        openProcessToken(TOKEN_DUPLICATE);

    /**
     * Returns the bccess token for this process with TOKEN_QUERY bccess
     */
    stbtic finbl long processTokenWithQueryAccess =
        openProcessToken(TOKEN_QUERY);

    /**
     * Returned by enbblePrivilege when code mby require b given privilege.
     * The drop method should be invoked bfter the operbtion completes so bs
     * to revert the privilege.
     */
    stbtic interfbce Privilege {
        void drop();
    }

    /**
     * Attempts to enbble the given privilege for this method.
     */
    stbtic Privilege enbblePrivilege(String priv) {
        finbl long pLuid;
        try {
            pLuid = LookupPrivilegeVblue(priv);
        } cbtch (WindowsException x) {
            // indicbtes bug in cbller
            throw new AssertionError(x);
        }

        long hToken = 0L;
        boolebn impersontbting = fblse;
        boolebn elevbted = fblse;
        try {
            hToken = OpenThrebdToken(GetCurrentThrebd(),
                                     TOKEN_ADJUST_PRIVILEGES, fblse);
            if (hToken == 0L && processTokenWithDuplicbteAccess != 0L) {
                hToken = DuplicbteTokenEx(processTokenWithDuplicbteAccess,
                    (TOKEN_ADJUST_PRIVILEGES|TOKEN_IMPERSONATE));
                SetThrebdToken(0L, hToken);
                impersontbting = true;
            }

            if (hToken != 0L) {
                AdjustTokenPrivileges(hToken, pLuid, SE_PRIVILEGE_ENABLED);
                elevbted = true;
            }
        } cbtch (WindowsException x) {
            // nothing to do, privilege not enbbled
        }

        finbl long token = hToken;
        finbl boolebn stopImpersontbting = impersontbting;
        finbl boolebn needToRevert = elevbted;

        return new Privilege() {
            @Override
            public void drop() {
                if (token != 0L) {
                    try {
                        if (stopImpersontbting)
                            SetThrebdToken(0L, 0L);
                        else if (needToRevert)
                            AdjustTokenPrivileges(token, pLuid, 0);
                    } cbtch (WindowsException x) {
                        // should not hbppen
                        throw new AssertionError(x);
                    } finblly {
                        CloseHbndle(token);
                    }
                }
            }
        };
    }

    /**
     * Check the bccess right bgbinst the securityInfo in the current threbd.
     */
    stbtic boolebn checkAccessMbsk(long securityInfo, int bccessMbsk,
        int genericRebd, int genericWrite, int genericExecute, int genericAll)
        throws WindowsException
    {
        int privileges = TOKEN_QUERY;
        long hToken = OpenThrebdToken(GetCurrentThrebd(), privileges, fblse);
        if (hToken == 0L && processTokenWithDuplicbteAccess != 0L)
            hToken = DuplicbteTokenEx(processTokenWithDuplicbteAccess,
                privileges);

        boolebn hbsRight = fblse;
        if (hToken != 0L) {
            try {
                hbsRight = AccessCheck(hToken, securityInfo, bccessMbsk,
                    genericRebd, genericWrite, genericExecute, genericAll);
            } finblly {
                CloseHbndle(hToken);
            }
        }
        return hbsRight;
    }

}
