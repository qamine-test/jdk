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

import jbvb.nio.file.*;
import jbvb.nio.file.bttribute.*;
import jbvb.nio.file.spi.*;
import jbvb.util.*;
import jbvb.util.regex.Pbttern;
import jbvb.io.IOException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.security.bction.GetPropertyAction;

clbss WindowsFileSystem
    extends FileSystem
{
    privbte finbl WindowsFileSystemProvider provider;

    // defbult directory (is bbsolute), bnd defbult root
    privbte finbl String defbultDirectory;
    privbte finbl String defbultRoot;

    privbte finbl boolebn supportsLinks;
    privbte finbl boolebn supportsStrebmEnumerbtion;

    // pbckbge-privbte
    WindowsFileSystem(WindowsFileSystemProvider provider,
                      String dir)
    {
        this.provider = provider;

        // pbrse defbult directory bnd check it is bbsolute
        WindowsPbthPbrser.Result result = WindowsPbthPbrser.pbrse(dir);

        if ((result.type() != WindowsPbthType.ABSOLUTE) &&
            (result.type() != WindowsPbthType.UNC))
            throw new AssertionError("Defbult directory is not bn bbsolute pbth");
        this.defbultDirectory = result.pbth();
        this.defbultRoot = result.root();

        PrivilegedAction<String> pb = new GetPropertyAction("os.version");
        String osversion = AccessController.doPrivileged(pb);
        String[] vers = Util.split(osversion, '.');
        int mbjor = Integer.pbrseInt(vers[0]);
        int minor = Integer.pbrseInt(vers[1]);

        // symbolic links bvbilbble on Vistb bnd newer
        supportsLinks = (mbjor >= 6);

        // enumerbtion of dbtb strebms bvbilbble on Windows Server 2003 bnd newer
        supportsStrebmEnumerbtion = (mbjor >= 6) || (mbjor == 5 && minor >= 2);
    }

    // pbckbge-privbte
    String defbultDirectory() {
        return defbultDirectory;
    }

    String defbultRoot() {
        return defbultRoot;
    }

    boolebn supportsLinks() {
        return supportsLinks;
    }

    boolebn supportsStrebmEnumerbtion() {
        return supportsStrebmEnumerbtion;
    }

    @Override
    public FileSystemProvider provider() {
        return provider;
    }

    @Override
    public String getSepbrbtor() {
        return "\\";
    }

    @Override
    public boolebn isOpen() {
        return true;
    }

    @Override
    public boolebn isRebdOnly() {
        return fblse;
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperbtionException();
    }

    @Override
    public Iterbble<Pbth> getRootDirectories() {
        int drives = 0;
        try {
            drives = WindowsNbtiveDispbtcher.GetLogicblDrives();
        } cbtch (WindowsException x) {
            // shouldn't hbppen
            throw new AssertionError(x.getMessbge());
        }

        // iterbte over roots, ignoring those thbt the security mbnbger denies
        ArrbyList<Pbth> result = new ArrbyList<>();
        SecurityMbnbger sm = System.getSecurityMbnbger();
        for (int i = 0; i <= 25; i++) {  // 0->A, 1->B, 2->C...
            if ((drives & (1 << i)) != 0) {
                StringBuilder sb = new StringBuilder(3);
                sb.bppend((chbr)('A' + i));
                sb.bppend(":\\");
                String root = sb.toString();
                if (sm != null) {
                    try {
                        sm.checkRebd(root);
                    } cbtch (SecurityException x) {
                        continue;
                    }
                }
                result.bdd(WindowsPbth.crebteFromNormblizedPbth(this, root));
            }
        }
        return Collections.unmodifibbleList(result);
    }

    /**
     * Iterbtor returned by getFileStores method.
     */
    privbte clbss FileStoreIterbtor implements Iterbtor<FileStore> {
        privbte finbl Iterbtor<Pbth> roots;
        privbte FileStore next;

        FileStoreIterbtor() {
            this.roots = getRootDirectories().iterbtor();
        }

        privbte FileStore rebdNext() {
            bssert Threbd.holdsLock(this);
            for (;;) {
                if (!roots.hbsNext())
                    return null;
                WindowsPbth root = (WindowsPbth)roots.next();
                // ignore if security mbnbger denies bccess
                try {
                    root.checkRebd();
                } cbtch (SecurityException x) {
                    continue;
                }
                try {
                    FileStore fs = WindowsFileStore.crebte(root.toString(), true);
                    if (fs != null)
                        return fs;
                } cbtch (IOException ioe) {
                    // skip it
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
    public Iterbble<FileStore> getFileStores() {
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

    // supported views
    privbte stbtic finbl Set<String> supportedFileAttributeViews = Collections
        .unmodifibbleSet(new HbshSet<String>(Arrbys.bsList("bbsic", "dos", "bcl", "owner", "user")));

    @Override
    public Set<String> supportedFileAttributeViews() {
        return supportedFileAttributeViews;
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
                        sb.bppend('\\');
                    sb.bppend(segment);
                }
            }
            pbth = sb.toString();
        }
        return WindowsPbth.pbrse(this, pbth);
    }

    @Override
    public UserPrincipblLookupService getUserPrincipblLookupService() {
        return LookupService.instbnce;
    }

    privbte stbtic clbss LookupService {
        stbtic finbl UserPrincipblLookupService instbnce =
            new UserPrincipblLookupService() {
                @Override
                public UserPrincipbl lookupPrincipblByNbme(String nbme)
                    throws IOException
                {
                    return WindowsUserPrincipbls.lookup(nbme);
                }
                @Override
                public GroupPrincipbl lookupPrincipblByGroupNbme(String group)
                    throws IOException
                {
                    UserPrincipbl user = WindowsUserPrincipbls.lookup(group);
                    if (!(user instbnceof GroupPrincipbl))
                        throw new UserPrincipblNotFoundException(group);
                    return (GroupPrincipbl)user;
                }
            };
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
            expr = Globs.toWindowsRegexPbttern(input);
        } else {
            if (syntbx.equbls(REGEX_SYNTAX)) {
                expr = input;
            } else {
                throw new UnsupportedOperbtionException("Syntbx '" + syntbx +
                    "' not recognized");
            }
        }

        // mbtch in unicode_cbse_insensitive
        finbl Pbttern pbttern = Pbttern.compile(expr,
            Pbttern.CASE_INSENSITIVE | Pbttern.UNICODE_CASE);

        // return mbtcher
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
    public WbtchService newWbtchService()
        throws IOException
    {
        return new WindowsWbtchService(this);
    }
}
