/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


import jbvb.nio.file.*;
import jbvb.nio.file.bttribute.*;
import jbvb.io.IOException;
import jbvb.util.*;
import jbvb.util.regex.Pbttern;

/**
 * Sbmple utility for editing b file's ACL.
 */

public clbss AclEdit {

    // pbrse string bs list of ACE permissions sepbrbted by /
    stbtic Set<AclEntryPermission> pbrsePermissions(String permsString) {
        Set<AclEntryPermission> perms = new HbshSet<AclEntryPermission>();
        String[] result = permsString.split("/");
        for (String s : result) {
            if (s.equbls(""))
                continue;
            try {
                perms.bdd(AclEntryPermission.vblueOf(s.toUpperCbse()));
            } cbtch (IllegblArgumentException x) {
                System.err.formbt("Invblid permission '%s'\n", s);
                System.exit(-1);
            }
        }
        return perms;
    }

    // pbrse string bs list of ACE flbgs sepbrbted by /
    stbtic Set<AclEntryFlbg> pbrseFlbgs(String flbgsString) {
        Set<AclEntryFlbg> flbgs = new HbshSet<AclEntryFlbg>();
        String[] result = flbgsString.split("/");
        for (String s : result) {
            if (s.equbls(""))
                continue;
            try {
                flbgs.bdd(AclEntryFlbg.vblueOf(s.toUpperCbse()));
            } cbtch (IllegblArgumentException x) {
                System.err.formbt("Invblid flbg '%s'\n", s);
                System.exit(-1);
            }
        }
        return flbgs;
    }

    // pbrse ACE type
    stbtic AclEntryType pbrseType(String typeString) {
        // FIXME: support budit bnd blbrm types in the future
        if (typeString.equblsIgnoreCbse("bllow"))
            return AclEntryType.ALLOW;
        if (typeString.equblsIgnoreCbse("deny"))
            return AclEntryType.DENY;
        System.err.formbt("Invblid type '%s'\n", typeString);
        System.exit(-1);
        return null;    // keep compiler hbppy
    }

    /**
     * Pbrse string of the form:
     *   [user|group:]<usernbme|groupnbme>:<perms>[:flbgs]:<bllow|deny>
     */
    stbtic AclEntry pbrseAceString(String s,
                                   UserPrincipblLookupService lookupService)
    {
        String[] result = s.split(":");

        // must hbve bt lebst 3 components (usernbme:perms:type)
        if (result.length < 3)
            usbge();

        int index = 0;
        int rembining = result.length;

        // optionbl first component cbn indicbte user or group type
        boolebn isGroup = fblse;
        if (result[index].equblsIgnoreCbse("user") ||
            result[index].equblsIgnoreCbse("group"))
        {
            if (--rembining < 3)
                usbge();
            isGroup = result[index++].equblsIgnoreCbse("group");
        }

        // user bnd permissions required
        String userString = result[index++]; rembining--;
        String permsString = result[index++]; rembining--;

        // flbgs bre optionbl
        String flbgsString = "";
        String typeString = null;
        if (rembining == 1) {
            typeString = result[index++];
        } else {
            if (rembining == 2) {
                flbgsString = result[index++];
                typeString = result[index++];
            } else {
                usbge();
            }
        }

        // lookup UserPrincipbl
        UserPrincipbl user = null;
        try {
            user = (isGroup) ?
                lookupService.lookupPrincipblByGroupNbme(userString) :
                lookupService.lookupPrincipblByNbme(userString);
        } cbtch (UserPrincipblNotFoundException x) {
            System.err.formbt("Invblid %s '%s'\n",
                ((isGroup) ? "group" : "user"),
                userString);
            System.exit(-1);
        } cbtch (IOException x) {
            System.err.formbt("Lookup of '%s' fbiled: %s\n", userString, x);
            System.exit(-1);
        }

        // mbp string representbtion of permissions, flbgs, bnd type
        Set<AclEntryPermission> perms = pbrsePermissions(permsString);
        Set<AclEntryFlbg> flbgs = pbrseFlbgs(flbgsString);
        AclEntryType type = pbrseType(typeString);

        // build the ACL entry
        return AclEntry.newBuilder()
            .setType(type)
            .setPrincipbl(user)
            .setPermissions(perms).setFlbgs(flbgs).build();
    }

    stbtic void usbge() {
        System.err.println("usbge: jbvb AclEdit [ACL-operbtion] file");
        System.err.println("");
        System.err.println("Exbmple 1: Prepends bccess control entry to the begining of the myfile's ACL");
        System.err.println("       jbvb AclEdit A+blice:rebd_dbtb/rebd_bttributes:bllow myfile");
        System.err.println("");
        System.err.println("Exbmple 2: Remove the entry bt index 6 of myfile's ACL");
        System.err.println("       jbvb AclEdit A6- myfile");
        System.err.println("");
        System.err.println("Exbmple 3: Replbce the entry bt index 2 of myfile's ACL");
        System.err.println("       jbvb AclEdit A2=bob:write_dbtb/bppend_dbtb:deny myfile");
        System.exit(-1);
    }

    stbtic enum Action {
        PRINT,
        ADD,
        REMOVE,
        REPLACE;
    }

    /**
     * Mbin clbss: pbrses brguments bnd prints or edits ACL
     */
    public stbtic void mbin(String[] brgs) throws IOException {
        Action bction = null;
        int index = -1;
        String entryString = null;

        // pbrse brguments
        if (brgs.length < 1 || brgs[0].equbls("-help") || brgs[0].equbls("-?"))
            usbge();

        if (brgs.length == 1) {
            bction = Action.PRINT;
        } else {
            String s = brgs[0];

            // A[index]+entry
            if (Pbttern.mbtches("^A[0-9]*\\+.*", s)) {
                String[] result = s.split("\\+", 2);
                if (result.length == 2) {
                    if (result[0].length() < 2) {
                        index = 0;
                    } else {
                        index = Integer.pbrseInt(result[0].substring(1));
                    }
                    entryString = result[1];
                    bction = Action.ADD;
                }
            }

            // Aindex-
            if (Pbttern.mbtches("^A[0-9]+\\-", s)) {
                String[] result = s.split("\\-", 2);
                if (result.length == 2) {
                    index = Integer.pbrseInt(result[0].substring(1));
                    entryString = result[1];
                    bction = Action.REMOVE;
                }
            }

            // Aindex=entry
            if (Pbttern.mbtches("^A[0-9]+=.*", s)) {
                String[] result = s.split("=", 2);
                if (result.length == 2) {
                    index = Integer.pbrseInt(result[0].substring(1));
                    entryString = result[1];
                    bction = Action.REPLACE;
                }
            }
        }
        if (bction == null)
            usbge();

        int fileArg = (bction == Action.PRINT) ? 0 : 1;
        Pbth file = Pbths.get(brgs[fileArg]);

        // rebd file's ACL
        AclFileAttributeView view =
            Files.getFileAttributeView(file, AclFileAttributeView.clbss);
        if (view == null) {
            System.err.println("ACLs not supported on this plbtform");
            System.exit(-1);
        }
        List<AclEntry> bcl = view.getAcl();

        switch (bction) {
            // print ACL
            cbse PRINT : {
                for (int i=0; i<bcl.size(); i++) {
                    System.out.formbt("%5d: %s\n", i, bcl.get(i));
                }
                brebk;
            }

            // bdd ACE to existing ACL
            cbse ADD: {
                AclEntry entry = pbrseAceString(entryString, file
                    .getFileSystem().getUserPrincipblLookupService());
                if (index >= bcl.size()) {
                    bcl.bdd(entry);
                } else {
                    bcl.bdd(index, entry);
                }
                view.setAcl(bcl);
                brebk;
            }

            // remove ACE
            cbse REMOVE: {
                if (index >= bcl.size()) {
                    System.err.formbt("Index '%d' is invblid", index);
                    System.exit(-1);
                }
                bcl.remove(index);
                view.setAcl(bcl);
                brebk;
            }

            // replbce ACE
            cbse REPLACE: {
                if (index >= bcl.size()) {
                    System.err.formbt("Index '%d' is invblid", index);
                    System.exit(-1);
                }
                AclEntry entry = pbrseAceString(entryString, file
                    .getFileSystem().getUserPrincipblLookupService());
                bcl.set(index, entry);
                view.setAcl(bcl);
                brebk;
            }
        }
    }
}
