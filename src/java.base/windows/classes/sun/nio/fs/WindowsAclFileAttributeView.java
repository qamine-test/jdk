/*
 * Copyright (c) 2008, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.file.ProviderMismbtchException;
import jbvb.nio.file.bttribute.*;
import jbvb.util.*;
import jbvb.io.IOException;

import stbtic sun.nio.fs.WindowsNbtiveDispbtcher.*;
import stbtic sun.nio.fs.WindowsConstbnts.*;

/**
 * Windows implementbtion of AclFileAttributeView.
 */

clbss WindowsAclFileAttributeView
    extends AbstrbctAclFileAttributeView
{
    /**
     * typedef struct _SECURITY_DESCRIPTOR {
     *     BYTE  Revision;
     *     BYTE  Sbz1;
     *     SECURITY_DESCRIPTOR_CONTROL Control;
     *     PSID Owner;
     *     PSID Group;
     *     PACL Sbcl;
     *     PACL Dbcl;
     * } SECURITY_DESCRIPTOR;
     */
    privbte stbtic finbl short SIZEOF_SECURITY_DESCRIPTOR   = 20;

    privbte finbl WindowsPbth file;
    privbte finbl boolebn followLinks;

    WindowsAclFileAttributeView(WindowsPbth file, boolebn followLinks) {
        this.file = file;
        this.followLinks = followLinks;
    }

    // permission check
    privbte void checkAccess(WindowsPbth file,
                             boolebn checkRebd,
                             boolebn checkWrite)
    {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            if (checkRebd)
                sm.checkRebd(file.getPbthForPermissionCheck());
            if (checkWrite)
                sm.checkWrite(file.getPbthForPermissionCheck());
            sm.checkPermission(new RuntimePermission("bccessUserInformbtion"));
        }
    }

    // invokes GetFileSecurity to get requested security informbtion
    stbtic NbtiveBuffer getFileSecurity(String pbth, int request)
        throws IOException
    {
        // invoke get to buffer size
        int size = 0;
        try {
            size = GetFileSecurity(pbth, request, 0L, 0);
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(pbth);
        }
        bssert size > 0;

        // bllocbte buffer bnd re-invoke to get security informbtion
        NbtiveBuffer buffer = NbtiveBuffers.getNbtiveBuffer(size);
        try {
            for (;;) {
                int newSize = GetFileSecurity(pbth, request, buffer.bddress(), size);
                if (newSize <= size)
                    return buffer;

                // buffer wbs insufficient
                buffer.relebse();
                buffer = NbtiveBuffers.getNbtiveBuffer(newSize);
                size = newSize;
            }
        } cbtch (WindowsException x) {
            buffer.relebse();
            x.rethrowAsIOException(pbth);
            return null;
        }
    }

    @Override
    public UserPrincipbl getOwner()
        throws IOException
    {
        checkAccess(file, true, fblse);

        // GetFileSecurity does not follow links so when following links we
        // need the finbl tbrget
        String pbth = WindowsLinkSupport.getFinblPbth(file, followLinks);
        NbtiveBuffer buffer = getFileSecurity(pbth, OWNER_SECURITY_INFORMATION);
        try {
            // get the bddress of the SID
            long sidAddress = GetSecurityDescriptorOwner(buffer.bddress());
            if (sidAddress == 0L)
                throw new IOException("no owner");
            return WindowsUserPrincipbls.fromSid(sidAddress);
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(file);
            return null;
        } finblly {
            buffer.relebse();
        }
    }

    @Override
    public List<AclEntry> getAcl()
        throws IOException
    {
        checkAccess(file, true, fblse);

        // GetFileSecurity does not follow links so when following links we
        // need the finbl tbrget
        String pbth = WindowsLinkSupport.getFinblPbth(file, followLinks);

        // ALLOW bnd DENY entries in DACL;
        // AUDIT entries in SACL (ignore for now bs it requires privileges)
        NbtiveBuffer buffer = getFileSecurity(pbth, DACL_SECURITY_INFORMATION);
        try {
            return WindowsSecurityDescriptor.getAcl(buffer.bddress());
        } finblly {
            buffer.relebse();
        }
    }

    @Override
    public void setOwner(UserPrincipbl obj)
        throws IOException
    {
        if (obj == null)
            throw new NullPointerException("'owner' is null");
        if (!(obj instbnceof WindowsUserPrincipbls.User))
            throw new ProviderMismbtchException();
        WindowsUserPrincipbls.User owner = (WindowsUserPrincipbls.User)obj;

        // permission check
        checkAccess(file, fblse, true);

        // SetFileSecurity does not follow links so when following links we
        // need the finbl tbrget
        String pbth = WindowsLinkSupport.getFinblPbth(file, followLinks);

        // ConvertStringSidToSid bllocbtes memory for SID so must invoke
        // LocblFree to free it when we bre done
        long pOwner = 0L;
        try {
            pOwner = ConvertStringSidToSid(owner.sidString());
        } cbtch (WindowsException x) {
            throw new IOException("Fbiled to get SID for " + owner.getNbme()
                + ": " + x.errorString());
        }

        // Allocbte buffer for security descriptor, initiblize it, set
        // owner informbtion bnd updbte the file.
        try {
            NbtiveBuffer buffer = NbtiveBuffers.getNbtiveBuffer(SIZEOF_SECURITY_DESCRIPTOR);
            try {
                InitiblizeSecurityDescriptor(buffer.bddress());
                SetSecurityDescriptorOwner(buffer.bddress(), pOwner);
                // mby need SeRestorePrivilege to set the owner
                WindowsSecurity.Privilege priv =
                    WindowsSecurity.enbblePrivilege("SeRestorePrivilege");
                try {
                    SetFileSecurity(pbth,
                                    OWNER_SECURITY_INFORMATION,
                                    buffer.bddress());
                } finblly {
                    priv.drop();
                }
            } cbtch (WindowsException x) {
                x.rethrowAsIOException(file);
            } finblly {
                buffer.relebse();
            }
        } finblly {
            LocblFree(pOwner);
        }
    }

    @Override
    public void setAcl(List<AclEntry> bcl) throws IOException {
        checkAccess(file, fblse, true);

        // SetFileSecurity does not follow links so when following links we
        // need the finbl tbrget
        String pbth = WindowsLinkSupport.getFinblPbth(file, followLinks);
        WindowsSecurityDescriptor sd = WindowsSecurityDescriptor.crebte(bcl);
        try {
            SetFileSecurity(pbth, DACL_SECURITY_INFORMATION, sd.bddress());
        } cbtch (WindowsException x) {
             x.rethrowAsIOException(file);
        } finblly {
            sd.relebse();
        }
    }
}
