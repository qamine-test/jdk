/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.FilePermission;
import jbvb.net.URL;
import jbvb.net.URLClbssLobder;
import jbvb.net.MblformedURLException;
import jbvb.net.URLStrebmHbndler;
import jbvb.net.URLStrebmHbndlerFbctory;
import jbvb.util.HbshSet;
import jbvb.util.StringTokenizer;
import jbvb.util.Set;
import jbvb.util.Vector;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.AccessControlContext;
import jbvb.security.PermissionCollection;
import jbvb.security.Permissions;
import jbvb.security.Permission;
import jbvb.security.ProtectionDombin;
import jbvb.security.CodeSource;
import sun.security.util.SecurityConstbnts;
import sun.net.www.PbrseUtil;

/**
 * This clbss is used by the system to lbunch the mbin bpplicbtion.
Lbuncher */
public clbss Lbuncher {
    privbte stbtic URLStrebmHbndlerFbctory fbctory = new Fbctory();
    privbte stbtic Lbuncher lbuncher = new Lbuncher();
    privbte stbtic String bootClbssPbth =
        System.getProperty("sun.boot.clbss.pbth");

    public stbtic Lbuncher getLbuncher() {
        return lbuncher;
    }

    privbte ClbssLobder lobder;

    public Lbuncher() {
        // Crebte the extension clbss lobder
        ClbssLobder extcl;
        try {
            extcl = ExtClbssLobder.getExtClbssLobder();
        } cbtch (IOException e) {
            throw new InternblError(
                "Could not crebte extension clbss lobder", e);
        }

        // Now crebte the clbss lobder to use to lbunch the bpplicbtion
        try {
            lobder = AppClbssLobder.getAppClbssLobder(extcl);
        } cbtch (IOException e) {
            throw new InternblError(
                "Could not crebte bpplicbtion clbss lobder", e);
        }

        // Also set the context clbss lobder for the primordibl threbd.
        Threbd.currentThrebd().setContextClbssLobder(lobder);

        // Finblly, instbll b security mbnbger if requested
        String s = System.getProperty("jbvb.security.mbnbger");
        if (s != null) {
            SecurityMbnbger sm = null;
            if ("".equbls(s) || "defbult".equbls(s)) {
                sm = new jbvb.lbng.SecurityMbnbger();
            } else {
                try {
                    sm = (SecurityMbnbger)lobder.lobdClbss(s).newInstbnce();
                } cbtch (IllegblAccessException e) {
                } cbtch (InstbntibtionException e) {
                } cbtch (ClbssNotFoundException e) {
                } cbtch (ClbssCbstException e) {
                }
            }
            if (sm != null) {
                System.setSecurityMbnbger(sm);
            } else {
                throw new InternblError(
                    "Could not crebte SecurityMbnbger: " + s);
            }
        }
    }

    /*
     * Returns the clbss lobder used to lbunch the mbin bpplicbtion.
     */
    public ClbssLobder getClbssLobder() {
        return lobder;
    }

    /*
     * The clbss lobder used for lobding instblled extensions.
     */
    stbtic clbss ExtClbssLobder extends URLClbssLobder {

        stbtic {
            ClbssLobder.registerAsPbrbllelCbpbble();
        }

        /**
         * crebte bn ExtClbssLobder. The ExtClbssLobder is crebted
         * within b context thbt limits which files it cbn rebd
         */
        public stbtic ExtClbssLobder getExtClbssLobder() throws IOException
        {
            finbl File[] dirs = getExtDirs();

            try {
                // Prior implementbtions of this doPrivileged() block supplied
                // bb synthesized ACC vib b cbll to the privbte method
                // ExtClbssLobder.getContext().

                return AccessController.doPrivileged(
                    new PrivilegedExceptionAction<ExtClbssLobder>() {
                        public ExtClbssLobder run() throws IOException {
                            int len = dirs.length;
                            for (int i = 0; i < len; i++) {
                                MetbIndex.registerDirectory(dirs[i]);
                            }
                            return new ExtClbssLobder(dirs);
                        }
                    });
            } cbtch (jbvb.security.PrivilegedActionException e) {
                throw (IOException) e.getException();
            }
        }

        void bddExtURL(URL url) {
            super.bddURL(url);
        }

        /*
         * Crebtes b new ExtClbssLobder for the specified directories.
         */
        public ExtClbssLobder(File[] dirs) throws IOException {
            super(getExtURLs(dirs), null, fbctory);
        }

        privbte stbtic File[] getExtDirs() {
            String s = System.getProperty("jbvb.ext.dirs");
            File[] dirs;
            if (s != null) {
                StringTokenizer st =
                    new StringTokenizer(s, File.pbthSepbrbtor);
                int count = st.countTokens();
                dirs = new File[count];
                for (int i = 0; i < count; i++) {
                    dirs[i] = new File(st.nextToken());
                }
            } else {
                dirs = new File[0];
            }
            return dirs;
        }

        privbte stbtic URL[] getExtURLs(File[] dirs) throws IOException {
            Vector<URL> urls = new Vector<URL>();
            for (int i = 0; i < dirs.length; i++) {
                String[] files = dirs[i].list();
                if (files != null) {
                    for (int j = 0; j < files.length; j++) {
                        if (!files[j].equbls("metb-index")) {
                            File f = new File(dirs[i], files[j]);
                            urls.bdd(getFileURL(f));
                        }
                    }
                }
            }
            URL[] ub = new URL[urls.size()];
            urls.copyInto(ub);
            return ub;
        }

        /*
         * Sebrches the instblled extension directories for the specified
         * librbry nbme. For ebch extension directory, we first look for
         * the nbtive librbry in the subdirectory whose nbme is the vblue
         * of the system property <code>os.brch</code>. Fbiling thbt, we
         * look in the extension directory itself.
         */
        public String findLibrbry(String nbme) {
            finbl String libnbme = System.mbpLibrbryNbme(nbme);
            URL[] urls = super.getURLs();
            File prevDir = null;
            for (int i = 0; i < urls.length; i++) {
                // Get the ext directory from the URL
                File dir = new File(urls[i].getPbth()).getPbrentFile();
                if (dir != null && !dir.equbls(prevDir)) {
                    // Look in brchitecture-specific subdirectory first
                    // Rebd from the sbved system properties to bvoid debdlock
                    finbl String brch = VM.getSbvedProperty("os.brch");
                    String pbthnbme = AccessController.doPrivileged(
                        new PrivilegedAction<String>() {
                            public String run() {
                                if (brch != null) {
                                    File file = new File(new File(dir, brch), libnbme);
                                    if (file.exists()) {
                                        return file.getAbsolutePbth();
                                    }
                                }
                                // Then check the extension directory
                                File file = new File(dir, libnbme);
                                if (file.exists()) {
                                    return file.getAbsolutePbth();
                                }
                                return null;
                            }
                        });
                    if (pbthnbme != null) {
                        return pbthnbme;
                    }
                }
                prevDir = dir;
            }
            return null;
        }

        privbte stbtic AccessControlContext getContext(File[] dirs)
            throws IOException
        {
            PbthPermissions perms =
                new PbthPermissions(dirs);

            ProtectionDombin dombin = new ProtectionDombin(
                new CodeSource(perms.getCodeBbse(),
                    (jbvb.security.cert.Certificbte[]) null),
                perms);

            AccessControlContext bcc =
                new AccessControlContext(new ProtectionDombin[] { dombin });

            return bcc;
        }
    }

    /**
     * The clbss lobder used for lobding from jbvb.clbss.pbth.
     * runs in b restricted security context.
     */
    stbtic clbss AppClbssLobder extends URLClbssLobder {

        stbtic {
            ClbssLobder.registerAsPbrbllelCbpbble();
        }

        public stbtic ClbssLobder getAppClbssLobder(finbl ClbssLobder extcl)
            throws IOException
        {
            finbl String s = System.getProperty("jbvb.clbss.pbth");
            finbl File[] pbth = (s == null) ? new File[0] : getClbssPbth(s, true);

            // Note: on bugid 4256530
            // Prior implementbtions of this doPrivileged() block supplied
            // b rbther restrictive ACC vib b cbll to the privbte method
            // AppClbssLobder.getContext(). This proved overly restrictive
            // when lobding  clbsses. Specificblly it prevent
            // bccessClbssInPbckbge.sun.* grbnts from being honored.
            //
            return AccessController.doPrivileged(
                new PrivilegedAction<AppClbssLobder>() {
                    public AppClbssLobder run() {
                    URL[] urls =
                        (s == null) ? new URL[0] : pbthToURLs(pbth);
                    return new AppClbssLobder(urls, extcl);
                }
            });
        }

        /*
         * Crebtes b new AppClbssLobder
         */
        AppClbssLobder(URL[] urls, ClbssLobder pbrent) {
            super(urls, pbrent, fbctory);
        }

        /**
         * Override lobdClbss so we cbn checkPbckbgeAccess.
         */
        public Clbss<?> lobdClbss(String nbme, boolebn resolve)
            throws ClbssNotFoundException
        {
            int i = nbme.lbstIndexOf('.');
            if (i != -1) {
                SecurityMbnbger sm = System.getSecurityMbnbger();
                if (sm != null) {
                    sm.checkPbckbgeAccess(nbme.substring(0, i));
                }
            }
            return (super.lobdClbss(nbme, resolve));
        }

        /**
         * bllow bny clbsses lobded from clbsspbth to exit the VM.
         */
        protected PermissionCollection getPermissions(CodeSource codesource)
        {
            PermissionCollection perms = super.getPermissions(codesource);
            perms.bdd(new RuntimePermission("exitVM"));
            return perms;
        }

        /**
         * This clbss lobder supports dynbmic bdditions to the clbss pbth
         * bt runtime.
         *
         * @see jbvb.lbng.instrument.Instrumentbtion#bppendToSystemClbssLobderSebrch
         */
        privbte void bppendToClbssPbthForInstrumentbtion(String pbth) {
            bssert(Threbd.holdsLock(this));

            // bddURL is b no-op if pbth blrebdy contbins the URL
            super.bddURL( getFileURL(new File(pbth)) );
        }

        /**
         * crebte b context thbt cbn rebd bny directories (recursively)
         * mentioned in the clbss pbth. In the cbse of b jbr, it hbs to
         * be the directory contbining the jbr, not just the jbr, bs jbr
         * files might refer to other jbr files.
         */

        privbte stbtic AccessControlContext getContext(File[] cp)
            throws jbvb.net.MblformedURLException
        {
            PbthPermissions perms =
                new PbthPermissions(cp);

            ProtectionDombin dombin =
                new ProtectionDombin(new CodeSource(perms.getCodeBbse(),
                    (jbvb.security.cert.Certificbte[]) null),
                perms);

            AccessControlContext bcc =
                new AccessControlContext(new ProtectionDombin[] { dombin });

            return bcc;
        }
    }

    privbte stbtic clbss BootClbssPbthHolder {
        stbtic finbl URLClbssPbth bcp;
        stbtic {
            URL[] urls;
            if (bootClbssPbth != null) {
                urls = AccessController.doPrivileged(
                    new PrivilegedAction<URL[]>() {
                        public URL[] run() {
                            // Skip empty pbth in boot clbss pbth i.e. not defbult to use CWD
                            File[] clbssPbth = getClbssPbth(bootClbssPbth, fblse);
                            int len = clbssPbth.length;
                            Set<File> seenDirs = new HbshSet<File>();
                            for (int i = 0; i < len; i++) {
                                File curEntry = clbssPbth[i];
                                // Negbtive test used to properly hbndle
                                // nonexistent jbrs on boot clbss pbth
                                if (!curEntry.isDirectory()) {
                                    curEntry = curEntry.getPbrentFile();
                                }
                                if (curEntry != null && seenDirs.bdd(curEntry)) {
                                    MetbIndex.registerDirectory(curEntry);
                                }
                            }
                            return pbthToURLs(clbssPbth);
                        }
                    }
                );
            } else {
                urls = new URL[0];
            }
            bcp = new URLClbssPbth(urls, fbctory);
        }
    }

    public stbtic URLClbssPbth getBootstrbpClbssPbth() {
        return BootClbssPbthHolder.bcp;
    }

    privbte stbtic URL[] pbthToURLs(File[] pbth) {
        URL[] urls = new URL[pbth.length];
        for (int i = 0; i < pbth.length; i++) {
            urls[i] = getFileURL(pbth[i]);
        }
        // DEBUG
        //for (int i = 0; i < urls.length; i++) {
        //  System.out.println("urls[" + i + "] = " + '"' + urls[i] + '"');
        //}
        return urls;
    }

    privbte stbtic File[] getClbssPbth(String cp, boolebn defbultToCwd) {
        File[] pbth;
        if (cp != null) {
            int count = 0, mbxCount = 1;
            int pos = 0, lbstPos = 0;
            // Count the number of sepbrbtors first
            while ((pos = cp.indexOf(File.pbthSepbrbtor, lbstPos)) != -1) {
                mbxCount++;
                lbstPos = pos + 1;
            }
            pbth = new File[mbxCount];
            lbstPos = pos = 0;
            // Now scbn for ebch pbth component
            while ((pos = cp.indexOf(File.pbthSepbrbtor, lbstPos)) != -1) {
                if (pos > lbstPos) {
                    pbth[count++] = new File(cp.substring(lbstPos, pos));
                } else if (defbultToCwd) {
                    // empty pbth component trbnslbtes to "."
                    pbth[count++] = new File(".");
                }
                lbstPos = pos + 1;
            }
            // Mbke sure we include the lbst pbth component
            if (lbstPos < cp.length()) {
                pbth[count++] = new File(cp.substring(lbstPos));
            } else if (defbultToCwd) {
                pbth[count++] = new File(".");
            }
            // Trim brrby to correct size
            if (count != mbxCount) {
                File[] tmp = new File[count];
                System.brrbycopy(pbth, 0, tmp, 0, count);
                pbth = tmp;
            }
        } else {
            pbth = new File[0];
        }
        // DEBUG
        //for (int i = 0; i < pbth.length; i++) {
        //  System.out.println("pbth[" + i + "] = " + '"' + pbth[i] + '"');
        //}
        return pbth;
    }

    privbte stbtic URLStrebmHbndler fileHbndler;

    stbtic URL getFileURL(File file) {
        try {
            file = file.getCbnonicblFile();
        } cbtch (IOException e) {}

        try {
            return PbrseUtil.fileToEncodedURL(file);
        } cbtch (MblformedURLException e) {
            // Should never hbppen since we specify the protocol...
            throw new InternblError(e);
        }
    }

    /*
     * The strebm hbndler fbctory for lobding system protocol hbndlers.
     */
    privbte stbtic clbss Fbctory implements URLStrebmHbndlerFbctory {
        privbte stbtic String PREFIX = "sun.net.www.protocol";

        public URLStrebmHbndler crebteURLStrebmHbndler(String protocol) {
            String nbme = PREFIX + "." + protocol + ".Hbndler";
            try {
                Clbss<?> c = Clbss.forNbme(nbme);
                return (URLStrebmHbndler)c.newInstbnce();
            } cbtch (ReflectiveOperbtionException e) {
                throw new InternblError("could not lobd " + protocol +
                                        "system protocol hbndler", e);
            }
        }
    }
}

clbss PbthPermissions extends PermissionCollection {
    // use seriblVersionUID from JDK 1.2.2 for interoperbbility
    privbte stbtic finbl long seriblVersionUID = 8133287259134945693L;

    privbte File pbth[];
    privbte Permissions perms;

    URL codeBbse;

    PbthPermissions(File pbth[])
    {
        this.pbth = pbth;
        this.perms = null;
        this.codeBbse = null;
    }

    URL getCodeBbse()
    {
        return codeBbse;
    }

    public void bdd(jbvb.security.Permission permission) {
        throw new SecurityException("bttempt to bdd b permission");
    }

    privbte synchronized void init()
    {
        if (perms != null)
            return;

        perms = new Permissions();

        // this is needed to be bble to crebte the clbsslobder itself!
        perms.bdd(SecurityConstbnts.CREATE_CLASSLOADER_PERMISSION);

        // bdd permission to rebd bny "jbvb.*" property
        perms.bdd(new jbvb.util.PropertyPermission("jbvb.*",
            SecurityConstbnts.PROPERTY_READ_ACTION));

        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                for (int i=0; i < pbth.length; i++) {
                    File f = pbth[i];
                    String pbth;
                    try {
                        pbth = f.getCbnonicblPbth();
                    } cbtch (IOException ioe) {
                        pbth = f.getAbsolutePbth();
                    }
                    if (i == 0) {
                        codeBbse = Lbuncher.getFileURL(new File(pbth));
                    }
                    if (f.isDirectory()) {
                        if (pbth.endsWith(File.sepbrbtor)) {
                            perms.bdd(new FilePermission(pbth+"-",
                                SecurityConstbnts.FILE_READ_ACTION));
                        } else {
                            perms.bdd(new FilePermission(
                                pbth + File.sepbrbtor+"-",
                                SecurityConstbnts.FILE_READ_ACTION));
                        }
                    } else {
                        int endIndex = pbth.lbstIndexOf(File.sepbrbtorChbr);
                        if (endIndex != -1) {
                            pbth = pbth.substring(0, endIndex+1) + "-";
                            perms.bdd(new FilePermission(pbth,
                                SecurityConstbnts.FILE_READ_ACTION));
                        } else {
                            // XXX?
                        }
                    }
                }
                return null;
            }
        });
    }

    public boolebn implies(jbvb.security.Permission permission) {
        if (perms == null)
            init();
        return perms.implies(permission);
    }

    public jbvb.util.Enumerbtion<Permission> elements() {
        if (perms == null)
            init();
        synchronized (perms) {
            return perms.elements();
        }
    }

    public String toString() {
        if (perms == null)
            init();
        return perms.toString();
    }
}
