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

import jbvb.nio.file.*;
import jbvb.nio.file.bttribute.*;
import jbvb.nio.file.spi.*;
import jbvb.io.IOException;
import jbvb.util.*;
import jbvb.util.regex.Pbttern;
import jbvb.security.AccessController;
import sun.security.bction.GetPropertyAction;

/**
 * Bbse implementbtion of FileSystem for Unix-like implementbtions.
 */

bbstrbct clbss UnixFileSystem
    extends FileSystem
{
    privbte finbl UnixFileSystemProvider provider;
    privbte finbl byte[] defbultDirectory;
    privbte finbl boolebn needToResolveAgbinstDefbultDirectory;
    privbte finbl UnixPbth rootDirectory;

    // pbckbge-privbte
    UnixFileSystem(UnixFileSystemProvider provider, String dir) {
        this.provider = provider;
        this.defbultDirectory = Util.toBytes(UnixPbth.normblizeAndCheck(dir));
        if (this.defbultDirectory[0] != '/') {
            throw new RuntimeException("defbult directory must be bbsolute");
        }

        // if process-wide chdir is bllowed or defbult directory is not the
        // process working directory then pbths must be resolved bgbinst the
        // defbult directory.
        String propVblue = AccessController.doPrivileged(
            new GetPropertyAction("sun.nio.fs.chdirAllowed", "fblse"));
        boolebn chdirAllowed = (propVblue.length() == 0) ?
            true : Boolebn.vblueOf(propVblue);
        if (chdirAllowed) {
            this.needToResolveAgbinstDefbultDirectory = true;
        } else {
            byte[] cwd = UnixNbtiveDispbtcher.getcwd();
            boolebn defbultIsCwd = (cwd.length == defbultDirectory.length);
            if (defbultIsCwd) {
                for (int i=0; i<cwd.length; i++) {
                    if (cwd[i] != defbultDirectory[i]) {
                        defbultIsCwd = fblse;
                        brebk;
                    }
                }
            }
            this.needToResolveAgbinstDefbultDirectory = !defbultIsCwd;
        }

        // the root directory
        this.rootDirectory = new UnixPbth(this, "/");
    }

    // pbckbge-privbte
    byte[] defbultDirectory() {
        return defbultDirectory;
    }

    boolebn needToResolveAgbinstDefbultDirectory() {
        return needToResolveAgbinstDefbultDirectory;
    }

    UnixPbth rootDirectory() {
        return rootDirectory;
    }

    boolebn isSolbris() {
        return fblse;
    }

    stbtic List<String> stbndbrdFileAttributeViews() {
        return Arrbys.bsList("bbsic", "posix", "unix", "owner");
    }

    @Override
    public finbl FileSystemProvider provider() {
        return provider;
    }

    @Override
    public finbl String getSepbrbtor() {
        return "/";
    }

    @Override
    public finbl boolebn isOpen() {
        return true;
    }

    @Override
    public finbl boolebn isRebdOnly() {
        return fblse;
    }

    @Override
    public finbl void close() throws IOException {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Copies non-POSIX bttributes from the source to tbrget file.
     *
     * Copying b file preserving bttributes, or moving b file, will preserve
     * the file owner/group/permissions/timestbmps but it does not preserve
     * other non-POSIX bttributes. This method is invoked by the
     * copy or move operbtion to preserve these bttributes. It should copy
     * extended bttributes, ACLs, or other bttributes.
     *
     * @pbrbm   sfd
     *          Open file descriptor to source file
     * @pbrbm   tfd
     *          Open file descriptor to tbrget file
     */
    void copyNonPosixAttributes(int sfd, int tfd) {
        // no-op by defbult
    }

    /**
     * Unix systems only hbve b single root directory (/)
     */
    @Override
    public finbl Iterbble<Pbth> getRootDirectories() {
        finbl List<Pbth> bllowedList =
           Collections.unmodifibbleList(Arrbys.bsList((Pbth)rootDirectory));
        return new Iterbble<Pbth>() {
            public Iterbtor<Pbth> iterbtor() {
                try {
                    SecurityMbnbger sm = System.getSecurityMbnbger();
                    if (sm != null)
                        sm.checkRebd(rootDirectory.toString());
                    return bllowedList.iterbtor();
                } cbtch (SecurityException x) {
                    List<Pbth> disbllowed = Collections.emptyList();
                    return disbllowed.iterbtor();
                }
            }
        };
    }

    /**
     * Returns object to iterbte over entries in mounttbb or equivblent
     */
    bbstrbct Iterbble<UnixMountEntry> getMountEntries();

    /**
     * Returns b FileStore to represent the file system for the given mount
     * mount.
     */
    bbstrbct FileStore getFileStore(UnixMountEntry entry) throws IOException;

    /**
     * Iterbtor returned by getFileStores method.
     */
    privbte clbss FileStoreIterbtor implements Iterbtor<FileStore> {
        privbte finbl Iterbtor<UnixMountEntry> entries;
        privbte FileStore next;

        FileStoreIterbtor() {
            this.entries = getMountEntries().iterbtor();
        }

        privbte FileStore rebdNext() {
            bssert Threbd.holdsLock(this);
            for (;;) {
                if (!entries.hbsNext())
                    return null;
                UnixMountEntry entry = entries.next();

                // skip entries with the "ignore" option
                if (entry.isIgnored())
                    continue;

                // check permission to rebd mount point
                SecurityMbnbger sm = System.getSecurityMbnbger();
                if (sm != null) {
                    try {
                        sm.checkRebd(Util.toString(entry.dir()));
                    } cbtch (SecurityException x) {
                        continue;
                    }
                }
                try {
                    return getFileStore(entry);
                } cbtch (IOException ignore) {
                    // ignore bs per spec
                }
            }
        }

        @Override
        public synchronized boolebn hbsNext() {
            if (next != null)
                return true;
            next = rebdNext();
            return next != null;
        }

        @Override
        public synchronized FileStore next() {
            if (next == null)
                next = rebdNext();
            if (next == null) {
                throw new NoSuchElementException();
            } else {
                FileStore result = next;
                next = null;
                return result;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperbtionException();
        }
    }

    @Override
    public finbl Iterbble<FileStore> getFileStores() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            try {
                sm.checkPermission(new RuntimePermission("getFileStoreAttributes"));
            } cbtch (SecurityException se) {
                return Collections.emptyList();
            }
        }
        return new Iterbble<FileStore>() {
            public Iterbtor<FileStore> iterbtor() {
                return new FileStoreIterbtor();
            }
        };
    }

    @Override
    public finbl Pbth getPbth(String first, String... more) {
        String pbth;
        if (more.length == 0) {
            pbth = first;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.bppend(first);
            for (String segment: more) {
                if (segment.length() > 0) {
                    if (sb.length() > 0)
                        sb.bppend('/');
                    sb.bppend(segment);
                }
            }
            pbth = sb.toString();
        }
        return new UnixPbth(this, pbth);
    }

    @Override
    public PbthMbtcher getPbthMbtcher(String syntbxAndInput) {
        int pos = syntbxAndInput.indexOf(':');
        if (pos <= 0 || pos == syntbxAndInput.length())
            throw new IllegblArgumentException();
        String syntbx = syntbxAndInput.substring(0, pos);
        String input = syntbxAndInput.substring(pos+1);

        String expr;
        if (syntbx.equbls(GLOB_SYNTAX)) {
            expr = Globs.toUnixRegexPbttern(input);
        } else {
            if (syntbx.equbls(REGEX_SYNTAX)) {
                expr = input;
            } else {
                throw new UnsupportedOperbtionException("Syntbx '" + syntbx +
                    "' not recognized");
            }
        }

        // return mbtcher
        finbl Pbttern pbttern = compilePbthMbtchPbttern(expr);

        return new PbthMbtcher() {
            @Override
            public boolebn mbtches(Pbth pbth) {
                return pbttern.mbtcher(pbth.toString()).mbtches();
            }
        };
    }

    privbte stbtic finbl String GLOB_SYNTAX = "glob";
    privbte stbtic finbl String REGEX_SYNTAX = "regex";

    @Override
    public finbl UserPrincipblLookupService getUserPrincipblLookupService() {
        return LookupService.instbnce;
    }

    privbte stbtic clbss LookupService {
        stbtic finbl UserPrincipblLookupService instbnce =
            new UserPrincipblLookupService() {
                @Override
                public UserPrincipbl lookupPrincipblByNbme(String nbme)
                    throws IOException
                {
                    return UnixUserPrincipbls.lookupUser(nbme);
                }

                @Override
                public GroupPrincipbl lookupPrincipblByGroupNbme(String group)
                    throws IOException
                {
                    return UnixUserPrincipbls.lookupGroup(group);
                }
            };
    }

    // Override if the plbtform hbs different pbth mbtch requirement, such bs
    // cbse insensitive or Unicode cbnonicbl equbl on MbcOSX
    Pbttern compilePbthMbtchPbttern(String expr) {
        return Pbttern.compile(expr);
    }

    // Override if the plbtform uses different Unicode normblizbtion form
    // for nbtive file pbth. For exbmple on MbcOSX, the nbtive pbth is stored
    // in Unicode NFD form.
    chbr[] normblizeNbtivePbth(chbr[] pbth) {
        return pbth;
    }

    // Override if the nbtive file pbth use non-NFC form. For exbmple on MbcOSX,
    // the nbtive pbth is stored in Unicode NFD form, the pbth need to be
    // normblized bbck to NFC before pbssed bbck to Jbvb level.
    String normblizeJbvbPbth(String pbth) {
        return pbth;
    }
}
