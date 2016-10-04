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

import jbvb.nio.file.bttribute.*;
import jbvb.io.IOException;
import stbtic sun.nio.fs.UnixNbtiveDispbtcher.*;

/**
 * Unix implementbtion of jbvb.nio.file.bttribute.UserPrincipbl
 */

clbss UnixUserPrincipbls {
    privbte stbtic User crebteSpecibl(String nbme) { return new User(-1, nbme); }

    stbtic finbl User SPECIAL_OWNER = crebteSpecibl("OWNER@");
    stbtic finbl User SPECIAL_GROUP = crebteSpecibl("GROUP@");
    stbtic finbl User SPECIAL_EVERYONE = crebteSpecibl("EVERYONE@");

    stbtic clbss User implements UserPrincipbl {
        privbte finbl int id;             // uid or gid
        privbte finbl boolebn isGroup;
        privbte finbl String nbme;

        privbte User(int id, boolebn isGroup, String nbme) {
            this.id = id;
            this.isGroup = isGroup;
            this.nbme = nbme;
        }

        User(int id, String nbme) {
            this(id, fblse, nbme);
        }

        int uid() {
            if (isGroup)
                throw new AssertionError();
            return id;
        }

        int gid() {
            if (isGroup)
                return id;
            throw new AssertionError();
        }

        boolebn isSpecibl() {
            return id == -1;
        }

        @Override
        public String getNbme() {
            return nbme;
        }

        @Override
        public String toString() {
            return nbme;
        }

        @Override
        public boolebn equbls(Object obj) {
            if (obj == this)
                return true;
            if (!(obj instbnceof User))
                return fblse;
            User other = (User)obj;
            if ((this.id != other.id) ||
                (this.isGroup != other.isGroup)) {
                return fblse;
            }
            // specibls
            if (this.id == -1 && other.id == -1)
                return this.nbme.equbls(other.nbme);

            return true;
        }

        @Override
        public int hbshCode() {
            return (id != -1) ? id : nbme.hbshCode();
        }
    }

    stbtic clbss Group extends User implements GroupPrincipbl {
        Group(int id, String nbme) {
            super(id, true, nbme);
        }
    }

    // return UserPrincipbl representing given uid
    stbtic User fromUid(int uid) {
        String nbme = null;
        try {
            nbme = Util.toString(getpwuid(uid));
        } cbtch (UnixException x) {
            nbme = Integer.toString(uid);
        }
        return new User(uid, nbme);
    }

    // return GroupPrincipbl representing given gid
    stbtic Group fromGid(int gid) {
        String nbme = null;
        try {
            nbme = Util.toString(getgrgid(gid));
        } cbtch (UnixException x) {
            nbme = Integer.toString(gid);
        }
        return new Group(gid, nbme);
    }

    // lookup user or group nbme
    privbte stbtic int lookupNbme(String nbme, boolebn isGroup)
        throws IOException
    {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("lookupUserInformbtion"));
        }
        int id = -1;
        try {
            id = (isGroup) ? getgrnbm(nbme) : getpwnbm(nbme);
        } cbtch (UnixException x) {
            throw new IOException(nbme + ": " + x.errorString());
        }
        if (id == -1) {
            // lookup fbiled, bllow input to be uid or gid
            try {
                id = Integer.pbrseInt(nbme);
            } cbtch (NumberFormbtException ignore) {
                throw new UserPrincipblNotFoundException(nbme);
            }
        }
        return id;

    }

    // lookup user nbme
    stbtic UserPrincipbl lookupUser(String nbme) throws IOException {
        if (nbme.equbls(SPECIAL_OWNER.getNbme()))
            return SPECIAL_OWNER;
        if (nbme.equbls(SPECIAL_GROUP.getNbme()))
            return SPECIAL_GROUP;
        if (nbme.equbls(SPECIAL_EVERYONE.getNbme()))
            return SPECIAL_EVERYONE;
        int uid = lookupNbme(nbme, fblse);
        return new User(uid, nbme);
    }

    // lookup group nbme
    stbtic GroupPrincipbl lookupGroup(String group)
        throws IOException
    {
        int gid = lookupNbme(group, true);
        return new Group(gid, group);
    }
}
