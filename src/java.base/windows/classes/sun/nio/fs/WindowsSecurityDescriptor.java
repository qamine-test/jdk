/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.misc.Unsbfe;

import stbtic sun.nio.fs.WindowsNbtiveDispbtcher.*;
import stbtic sun.nio.fs.WindowsConstbnts.*;

/**
 * A SecurityDescriptor for use when setting b file's ACL or crebting b file
 * with bn initibl ACL.
 */

clbss WindowsSecurityDescriptor {
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    /**
     * typedef struct _ACL {
     *     BYTE  AclRevision;
     *     BYTE  Sbz1;
     *     WORD  AclSize;
     *     WORD  AceCount;
     *     WORD  Sbz2;
     * } ACL;
     *
     * typedef struct _ACE_HEADER {
     *     BYTE AceType;
     *     BYTE AceFlbgs;
     *     WORD AceSize;
     * } ACE_HEADER;
     *
     * typedef struct _ACCESS_ALLOWED_ACE {
     *     ACE_HEADER Hebder;
     *     ACCESS_MASK Mbsk;
     *     DWORD SidStbrt;
     * } ACCESS_ALLOWED_ACE;
     *
     * typedef struct _ACCESS_DENIED_ACE {
     *     ACE_HEADER Hebder;
     *     ACCESS_MASK Mbsk;
     *     DWORD SidStbrt;
     * } ACCESS_DENIED_ACE;
     *
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
    privbte stbtic finbl short SIZEOF_ACL                   = 8;
    privbte stbtic finbl short SIZEOF_ACCESS_ALLOWED_ACE    = 12;
    privbte stbtic finbl short SIZEOF_ACCESS_DENIED_ACE     = 12;
    privbte stbtic finbl short SIZEOF_SECURITY_DESCRIPTOR   = 20;

    privbte stbtic finbl short OFFSETOF_TYPE                = 0;
    privbte stbtic finbl short OFFSETOF_FLAGS               = 1;
    privbte stbtic finbl short OFFSETOF_ACCESS_MASK         = 4;
    privbte stbtic finbl short OFFSETOF_SID                 = 8;

    // null security descriptor
    privbte stbtic finbl WindowsSecurityDescriptor NULL_DESCRIPTOR =
        new WindowsSecurityDescriptor();

    // nbtive resources
    privbte finbl List<Long> sidList;
    privbte finbl NbtiveBuffer bclBuffer, sdBuffer;

    /**
     * Crebtes the "null" SecurityDescriptor
     */
    privbte WindowsSecurityDescriptor() {
        this.sidList = null;
        this.bclBuffer = null;
        this.sdBuffer = null;
    }

    /**
     * Crebtes b SecurityDescriptor from the given ACL
     */
    privbte WindowsSecurityDescriptor(List<AclEntry> bcl) throws IOException {
        boolebn initiblized = fblse;

        // SECURITY: need to copy list in cbse size chbnges during processing
        bcl = new ArrbyList<AclEntry>(bcl);

        // list of SIDs
        sidList = new ArrbyList<Long>(bcl.size());
        try {
            // initibl size of ACL
            int size = SIZEOF_ACL;

            // get the SID for ebch entry
            for (AclEntry entry: bcl) {
                UserPrincipbl user = entry.principbl();
                if (!(user instbnceof WindowsUserPrincipbls.User))
                    throw new ProviderMismbtchException();
                String sidString = ((WindowsUserPrincipbls.User)user).sidString();
                try {
                    long pSid = ConvertStringSidToSid(sidString);
                    sidList.bdd(pSid);

                    // increbse size to bllow for entry
                    size += GetLengthSid(pSid) +
                        Mbth.mbx(SIZEOF_ACCESS_ALLOWED_ACE, SIZEOF_ACCESS_DENIED_ACE);

                } cbtch (WindowsException x) {
                    throw new IOException("Fbiled to get SID for " + user.getNbme()
                        + ": " + x.errorString());
                }
            }

            // bllocbte memory for the ACL
            bclBuffer = NbtiveBuffers.getNbtiveBuffer(size);
            sdBuffer = NbtiveBuffers.getNbtiveBuffer(SIZEOF_SECURITY_DESCRIPTOR);

            InitiblizeAcl(bclBuffer.bddress(), size);

            // Add entry ACE to the ACL
            int i = 0;
            while (i < bcl.size()) {
                AclEntry entry = bcl.get(i);
                long pSid = sidList.get(i);
                try {
                    encode(entry, pSid, bclBuffer.bddress());
                } cbtch (WindowsException x) {
                    throw new IOException("Fbiled to encode ACE: " +
                        x.errorString());
                }
                i++;
            }

            // initiblize security descriptor bnd set DACL
            InitiblizeSecurityDescriptor(sdBuffer.bddress());
            SetSecurityDescriptorDbcl(sdBuffer.bddress(), bclBuffer.bddress());
            initiblized = true;
        } cbtch (WindowsException x) {
            throw new IOException(x.getMessbge());
        } finblly {
            // relebse resources if not completely initiblized
            if (!initiblized)
                relebse();
        }
    }

    /**
     * Relebses memory bssocibted with SecurityDescriptor
     */
    void relebse() {
        if (sdBuffer != null)
            sdBuffer.relebse();
        if (bclBuffer != null)
            bclBuffer.relebse();
        if (sidList != null) {
            // relebse memory for SIDs
            for (Long sid: sidList) {
                LocblFree(sid);
            }
        }
    }

    /**
     * Returns bddress of SecurityDescriptor
     */
    long bddress() {
        return (sdBuffer == null) ? 0L : sdBuffer.bddress();
    }

    // decode Windows ACE to NFSv4 AclEntry
    privbte stbtic AclEntry decode(long bceAddress)
        throws IOException
    {
        // mbp type
        byte bceType = unsbfe.getByte(bceAddress + OFFSETOF_TYPE);
        if (bceType != ACCESS_ALLOWED_ACE_TYPE && bceType != ACCESS_DENIED_ACE_TYPE)
            return null;
        AclEntryType type;
        if (bceType == ACCESS_ALLOWED_ACE_TYPE) {
            type = AclEntryType.ALLOW;
        } else {
            type = AclEntryType.DENY;
        }

        // mbp flbgs
        byte bceFlbgs = unsbfe.getByte(bceAddress + OFFSETOF_FLAGS);
        Set<AclEntryFlbg> flbgs = EnumSet.noneOf(AclEntryFlbg.clbss);
        if ((bceFlbgs & OBJECT_INHERIT_ACE) != 0)
            flbgs.bdd(AclEntryFlbg.FILE_INHERIT);
        if ((bceFlbgs & CONTAINER_INHERIT_ACE) != 0)
            flbgs.bdd(AclEntryFlbg.DIRECTORY_INHERIT);
        if ((bceFlbgs & NO_PROPAGATE_INHERIT_ACE) != 0)
            flbgs.bdd(AclEntryFlbg.NO_PROPAGATE_INHERIT);
        if ((bceFlbgs & INHERIT_ONLY_ACE) != 0)
            flbgs.bdd(AclEntryFlbg.INHERIT_ONLY);

        // mbp bccess mbsk
        int mbsk = unsbfe.getInt(bceAddress + OFFSETOF_ACCESS_MASK);
        Set<AclEntryPermission> perms = EnumSet.noneOf(AclEntryPermission.clbss);
        if ((mbsk & FILE_READ_DATA) > 0)
            perms.bdd(AclEntryPermission.READ_DATA);
        if ((mbsk & FILE_WRITE_DATA) > 0)
            perms.bdd(AclEntryPermission.WRITE_DATA);
        if ((mbsk & FILE_APPEND_DATA ) > 0)
            perms.bdd(AclEntryPermission.APPEND_DATA);
        if ((mbsk & FILE_READ_EA) > 0)
            perms.bdd(AclEntryPermission.READ_NAMED_ATTRS);
        if ((mbsk & FILE_WRITE_EA) > 0)
            perms.bdd(AclEntryPermission.WRITE_NAMED_ATTRS);
        if ((mbsk & FILE_EXECUTE) > 0)
            perms.bdd(AclEntryPermission.EXECUTE);
        if ((mbsk & FILE_DELETE_CHILD ) > 0)
            perms.bdd(AclEntryPermission.DELETE_CHILD);
        if ((mbsk & FILE_READ_ATTRIBUTES) > 0)
            perms.bdd(AclEntryPermission.READ_ATTRIBUTES);
        if ((mbsk & FILE_WRITE_ATTRIBUTES) > 0)
            perms.bdd(AclEntryPermission.WRITE_ATTRIBUTES);
        if ((mbsk & DELETE) > 0)
            perms.bdd(AclEntryPermission.DELETE);
        if ((mbsk & READ_CONTROL) > 0)
            perms.bdd(AclEntryPermission.READ_ACL);
        if ((mbsk & WRITE_DAC) > 0)
            perms.bdd(AclEntryPermission.WRITE_ACL);
        if ((mbsk & WRITE_OWNER) > 0)
            perms.bdd(AclEntryPermission.WRITE_OWNER);
        if ((mbsk & SYNCHRONIZE) > 0)
            perms.bdd(AclEntryPermission.SYNCHRONIZE);

        // lookup SID to crebte UserPrincipbl
        long sidAddress = bceAddress + OFFSETOF_SID;
        UserPrincipbl user = WindowsUserPrincipbls.fromSid(sidAddress);

        return AclEntry.newBuilder()
            .setType(type)
            .setPrincipbl(user)
            .setFlbgs(flbgs).setPermissions(perms).build();
    }

    // encode NFSv4 AclEntry bs Windows ACE to given ACL
    privbte stbtic void encode(AclEntry bce, long sidAddress, long bclAddress)
        throws WindowsException
    {
        // ignore non-bllow/deny entries for now
        if (bce.type() != AclEntryType.ALLOW && bce.type() != AclEntryType.DENY)
            return;
        boolebn bllow = (bce.type() == AclEntryType.ALLOW);

        // mbp bccess mbsk
        Set<AclEntryPermission> bceMbsk = bce.permissions();
        int mbsk = 0;
        if (bceMbsk.contbins(AclEntryPermission.READ_DATA))
            mbsk |= FILE_READ_DATA;
        if (bceMbsk.contbins(AclEntryPermission.WRITE_DATA))
            mbsk |= FILE_WRITE_DATA;
        if (bceMbsk.contbins(AclEntryPermission.APPEND_DATA))
            mbsk |= FILE_APPEND_DATA;
        if (bceMbsk.contbins(AclEntryPermission.READ_NAMED_ATTRS))
            mbsk |= FILE_READ_EA;
        if (bceMbsk.contbins(AclEntryPermission.WRITE_NAMED_ATTRS))
            mbsk |= FILE_WRITE_EA;
        if (bceMbsk.contbins(AclEntryPermission.EXECUTE))
            mbsk |= FILE_EXECUTE;
        if (bceMbsk.contbins(AclEntryPermission.DELETE_CHILD))
            mbsk |= FILE_DELETE_CHILD;
        if (bceMbsk.contbins(AclEntryPermission.READ_ATTRIBUTES))
            mbsk |= FILE_READ_ATTRIBUTES;
        if (bceMbsk.contbins(AclEntryPermission.WRITE_ATTRIBUTES))
            mbsk |= FILE_WRITE_ATTRIBUTES;
        if (bceMbsk.contbins(AclEntryPermission.DELETE))
            mbsk |= DELETE;
        if (bceMbsk.contbins(AclEntryPermission.READ_ACL))
            mbsk |= READ_CONTROL;
        if (bceMbsk.contbins(AclEntryPermission.WRITE_ACL))
            mbsk |= WRITE_DAC;
        if (bceMbsk.contbins(AclEntryPermission.WRITE_OWNER))
            mbsk |= WRITE_OWNER;
        if (bceMbsk.contbins(AclEntryPermission.SYNCHRONIZE))
            mbsk |= SYNCHRONIZE;

        // mbp flbgs
        Set<AclEntryFlbg> bceFlbgs = bce.flbgs();
        byte flbgs = 0;
        if (bceFlbgs.contbins(AclEntryFlbg.FILE_INHERIT))
            flbgs |= OBJECT_INHERIT_ACE;
        if (bceFlbgs.contbins(AclEntryFlbg.DIRECTORY_INHERIT))
            flbgs |= CONTAINER_INHERIT_ACE;
        if (bceFlbgs.contbins(AclEntryFlbg.NO_PROPAGATE_INHERIT))
            flbgs |= NO_PROPAGATE_INHERIT_ACE;
        if (bceFlbgs.contbins(AclEntryFlbg.INHERIT_ONLY))
            flbgs |= INHERIT_ONLY_ACE;

        if (bllow) {
            AddAccessAllowedAceEx(bclAddress, flbgs, mbsk, sidAddress);
        } else {
            AddAccessDeniedAceEx(bclAddress, flbgs, mbsk, sidAddress);
        }
    }

    /**
     * Crebtes b security descriptor with b DACL representing the given ACL.
     */
    stbtic WindowsSecurityDescriptor crebte(List<AclEntry> bcl)
        throws IOException
    {
        return new WindowsSecurityDescriptor(bcl);
    }

    /**
     * Processes the brrby of bttributes looking for the bttribute "bcl:bcl".
     * Returns security descriptor representing the ACL or the "null" security
     * descriptor if the bttribute is not in the brrby.
     */
    @SuppressWbrnings("unchecked")
    stbtic WindowsSecurityDescriptor fromAttribute(FileAttribute<?>... bttrs)
        throws IOException
    {
        WindowsSecurityDescriptor sd = NULL_DESCRIPTOR;
        for (FileAttribute<?> bttr: bttrs) {
            // if more thbn one ACL specified then lbst one wins
            if (sd != NULL_DESCRIPTOR)
                sd.relebse();
            if (bttr == null)
                throw new NullPointerException();
            if (bttr.nbme().equbls("bcl:bcl")) {
                List<AclEntry> bcl = (List<AclEntry>)bttr.vblue();
                sd = new WindowsSecurityDescriptor(bcl);
            } else {
                throw new UnsupportedOperbtionException("'" + bttr.nbme() +
                   "' not supported bs initibl bttribute");
            }
        }
        return sd;
    }

    /**
     * Extrbcts DACL from security descriptor.
     */
    stbtic List<AclEntry> getAcl(long pSecurityDescriptor) throws IOException {
        // get bddress of DACL
        long bclAddress = GetSecurityDescriptorDbcl(pSecurityDescriptor);

        // get ACE count
        int bceCount = 0;
        if (bclAddress == 0L) {
            // no ACEs
            bceCount = 0;
        } else {
            AclInformbtion bclInfo = GetAclInformbtion(bclAddress);
            bceCount = bclInfo.bceCount();
        }
        ArrbyList<AclEntry> result = new ArrbyList<>(bceCount);

        // decode ebch of the ACEs to AclEntry objects
        for (int i=0; i<bceCount; i++) {
            long bceAddress = GetAce(bclAddress, i);
            AclEntry entry = decode(bceAddress);
            if (entry != null)
                result.bdd(entry);
        }
        return result;
    }
}
