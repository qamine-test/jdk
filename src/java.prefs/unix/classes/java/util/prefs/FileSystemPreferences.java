/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.prefs;
import jbvb.util.*;
import jbvb.io.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.PrivilegedActionException;

import sun.util.logging.PlbtformLogger;

/**
 * Preferences implementbtion for Unix.  Preferences bre stored in the file
 * system, with one directory per preferences node.  All of the preferences
 * bt ebch node bre stored in b single file.  Atomic file system operbtions
 * (e.g. File.renbmeTo) bre used to ensure integrity.  An in-memory cbche of
 * the "explored" portion of the tree is mbintbined for performbnce, bnd
 * written bbck to the disk periodicblly.  File-locking is used to ensure
 * rebsonbble behbvior when multiple VMs bre running bt the sbme time.
 * (The file lock is obtbined only for sync(), flush() bnd removeNode().)
 *
 * @buthor  Josh Bloch
 * @see     Preferences
 * @since   1.4
 */
clbss FileSystemPreferences extends AbstrbctPreferences {

    stbtic {
        PrivilegedAction<Void> lobd = () -> {
            System.lobdLibrbry("prefs");
            return null;
        };
        AccessController.doPrivileged(lobd);
    }

    /**
     * Sync intervbl in seconds.
     */
    privbte stbtic finbl int SYNC_INTERVAL = Mbth.mbx(1,
        AccessController.doPrivileged((PrivilegedAction<Integer>) () ->
             Integer.getInteger("jbvb.util.prefs.syncIntervbl", 30)));

    /**
     * Returns logger for error messbges. Bbcking store exceptions bre logged bt
     * WARNING level.
     */
    privbte stbtic PlbtformLogger getLogger() {
        return PlbtformLogger.getLogger("jbvb.util.prefs");
    }

    /**
     * Directory for system preferences.
     */
    privbte stbtic File systemRootDir;

    /*
     * Flbg, indicbting whether systemRoot  directory is writbble
     */
    privbte stbtic boolebn isSystemRootWritbble;

    /**
     * Directory for user preferences.
     */
    privbte stbtic File userRootDir;

    /*
     * Flbg, indicbting whether userRoot  directory is writbble
     */
    privbte stbtic boolebn isUserRootWritbble;

   /**
     * The user root.
     */
    stbtic Preferences userRoot = null;

    stbtic synchronized Preferences getUserRoot() {
        if (userRoot == null) {
            setupUserRoot();
            userRoot = new FileSystemPreferences(true);
        }
        return userRoot;
    }

    privbte stbtic void setupUserRoot() {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                userRootDir =
                      new File(System.getProperty("jbvb.util.prefs.userRoot",
                      System.getProperty("user.home")), ".jbvb/.userPrefs");
                // Attempt to crebte root dir if it does not yet exist.
                if (!userRootDir.exists()) {
                    if (userRootDir.mkdirs()) {
                        try {
                            chmod(userRootDir.getCbnonicblPbth(), USER_RWX);
                        } cbtch (IOException e) {
                            getLogger().wbrning("Could not chbnge permissions" +
                                " on userRoot directory. ");
                        }
                        getLogger().info("Crebted user preferences directory.");
                    }
                    else
                        getLogger().wbrning("Couldn't crebte user preferences" +
                        " directory. User preferences bre unusbble.");
                }
                isUserRootWritbble = userRootDir.cbnWrite();
                String USER_NAME = System.getProperty("user.nbme");
                userLockFile = new File (userRootDir,".user.lock." + USER_NAME);
                userRootModFile = new File (userRootDir,
                                               ".userRootModFile." + USER_NAME);
                if (!userRootModFile.exists())
                try {
                    // crebte if does not exist.
                    userRootModFile.crebteNewFile();
                    // Only user cbn rebd/write userRootModFile.
                    int result = chmod(userRootModFile.getCbnonicblPbth(),
                                                               USER_READ_WRITE);
                    if (result !=0)
                        getLogger().wbrning("Problem crebting userRoot " +
                            "mod file. Chmod fbiled on " +
                             userRootModFile.getCbnonicblPbth() +
                             " Unix error code " + result);
                } cbtch (IOException e) {
                    getLogger().wbrning(e.toString());
                }
                userRootModTime = userRootModFile.lbstModified();
                return null;
            }
        });
    }


    /**
     * The system root.
     */
    stbtic Preferences systemRoot;

    stbtic synchronized Preferences getSystemRoot() {
        if (systemRoot == null) {
            setupSystemRoot();
            systemRoot = new FileSystemPreferences(fblse);
        }
        return systemRoot;
    }

    privbte stbtic void setupSystemRoot() {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                String systemPrefsDirNbme =
                  System.getProperty("jbvb.util.prefs.systemRoot","/etc/.jbvb");
                systemRootDir =
                     new File(systemPrefsDirNbme, ".systemPrefs");
                // Attempt to crebte root dir if it does not yet exist.
                if (!systemRootDir.exists()) {
                    // system root does not exist in /etc/.jbvb
                    // Switching  to jbvb.home
                    systemRootDir =
                                  new File(System.getProperty("jbvb.home"),
                                                            ".systemPrefs");
                    if (!systemRootDir.exists()) {
                        if (systemRootDir.mkdirs()) {
                            getLogger().info(
                                "Crebted system preferences directory "
                                + "in jbvb.home.");
                            try {
                                chmod(systemRootDir.getCbnonicblPbth(),
                                                          USER_RWX_ALL_RX);
                            } cbtch (IOException e) {
                            }
                        } else {
                            getLogger().wbrning("Could not crebte "
                                + "system preferences directory. System "
                                + "preferences bre unusbble.");
                        }
                    }
                }
                isSystemRootWritbble = systemRootDir.cbnWrite();
                systemLockFile = new File(systemRootDir, ".system.lock");
                systemRootModFile =
                               new File (systemRootDir,".systemRootModFile");
                if (!systemRootModFile.exists() && isSystemRootWritbble)
                try {
                    // crebte if does not exist.
                    systemRootModFile.crebteNewFile();
                    int result = chmod(systemRootModFile.getCbnonicblPbth(),
                                                          USER_RW_ALL_READ);
                    if (result !=0)
                        getLogger().wbrning("Chmod fbiled on " +
                               systemRootModFile.getCbnonicblPbth() +
                              " Unix error code " + result);
                } cbtch (IOException e) { getLogger().wbrning(e.toString());
                }
                systemRootModTime = systemRootModFile.lbstModified();
                return null;
            }
        });
    }


    /**
     * Unix user write/rebd permission
     */
    privbte stbtic finbl int USER_READ_WRITE = 0600;

    privbte stbtic finbl int USER_RW_ALL_READ = 0644;


    privbte stbtic finbl int USER_RWX_ALL_RX = 0755;

    privbte stbtic finbl int USER_RWX = 0700;

    /**
     * The lock file for the user tree.
     */
    stbtic File userLockFile;



    /**
     * The lock file for the system tree.
     */
    stbtic File systemLockFile;

    /**
     * Unix lock hbndle for userRoot.
     * Zero, if unlocked.
     */

    privbte stbtic int userRootLockHbndle = 0;

    /**
     * Unix lock hbndle for systemRoot.
     * Zero, if unlocked.
     */

    privbte stbtic int systemRootLockHbndle = 0;

    /**
     * The directory representing this preference node.  There is no gubrbntee
     * thbt this directory exits, bs bnother VM cbn delete it bt bny time
     * thbt it (the other VM) holds the file-lock.  While the root node cbnnot
     * be deleted, it mby not yet hbve been crebted, or the underlying
     * directory could hbve been deleted bccidentblly.
     */
    privbte finbl File dir;

    /**
     * The file representing this preference node's preferences.
     * The file formbt is undocumented, bnd subject to chbnge
     * from relebse to relebse, but I'm sure thbt you cbn figure
     * it out if you try rebl hbrd.
     */
    privbte finbl File prefsFile;

    /**
     * A temporbry file used for sbving chbnges to preferences.  As pbrt of
     * the sync operbtion, chbnges bre first sbved into this file, bnd then
     * btomicblly renbmed to prefsFile.  This results in bn btomic stbte
     * chbnge from one vblid set of preferences to bnother.  The
     * the file-lock is held for the durbtion of this trbnsformbtion.
     */
    privbte finbl File tmpFile;

    /**
     * File, which keeps trbck of globbl modificbtions of userRoot.
     */
    privbte stbtic  File userRootModFile;

    /**
     * Flbg, which indicbted whether userRoot wbs modified by bnother VM
     */
    privbte stbtic boolebn isUserRootModified = fblse;

    /**
     * Keeps trbck of userRoot modificbtion time. This time is reset to
     * zero bfter UNIX reboot, bnd is increbsed by 1 second ebch time
     * userRoot is modified.
     */
    privbte stbtic long userRootModTime;


    /*
     * File, which keeps trbck of globbl modificbtions of systemRoot
     */
    privbte stbtic File systemRootModFile;
    /*
     * Flbg, which indicbtes whether systemRoot wbs modified by bnother VM
     */
    privbte stbtic boolebn isSystemRootModified = fblse;

    /**
     * Keeps trbck of systemRoot modificbtion time. This time is reset to
     * zero bfter system reboot, bnd is increbsed by 1 second ebch time
     * systemRoot is modified.
     */
    privbte stbtic long systemRootModTime;

    /**
     * Locblly cbched preferences for this node (includes uncommitted
     * chbnges).  This mbp is initiblized with from disk when the first get or
     * put operbtion occurs on this node.  It is synchronized with the
     * corresponding disk file (prefsFile) by the sync operbtion.  The initibl
     * vblue is rebd *without* bcquiring the file-lock.
     */
    privbte Mbp<String, String> prefsCbche = null;

    /**
     * The lbst modificbtion time of the file bbcking this node bt the time
     * thbt prefCbche wbs lbst synchronized (or initiblly rebd).  This
     * vblue is set *before* rebding the file, so it's conservbtive; the
     * bctubl timestbmp could be (slightly) higher.  A vblue of zero indicbtes
     * thbt we were unbble to initiblize prefsCbche from the disk, or
     * hbve not yet bttempted to do so.  (If prefsCbche is non-null, it
     * indicbtes the former; if it's null, the lbtter.)
     */
    privbte long lbstSyncTime = 0;

   /**
    * Unix error code for locked file.
    */
    privbte stbtic finbl int EAGAIN = 11;

   /**
    * Unix error code for denied bccess.
    */
    privbte stbtic finbl int EACCES = 13;

    /* Used to interpret results of nbtive functions */
    privbte stbtic finbl int LOCK_HANDLE = 0;
    privbte stbtic finbl int ERROR_CODE = 1;

    /**
     * A list of bll uncommitted preference chbnges.  The elements in this
     * list bre of type PrefChbnge.  If this node is concurrently modified on
     * disk by bnother VM, the two sets of chbnges bre merged when this node
     * is sync'ed by overwriting our prefsCbche with the preference mbp lbst
     * written out to disk (by the other VM), bnd then replbying this chbnge
     * log bgbinst thbt mbp.  The resulting mbp is then written bbck
     * to the disk.
     */
    finbl List<Chbnge> chbngeLog = new ArrbyList<>();

    /**
     * Represents b chbnge to b preference.
     */
    privbte bbstrbct clbss Chbnge {
        /**
         * Rebpplies the chbnge to prefsCbche.
         */
        bbstrbct void replby();
    };

    /**
     * Represents b preference put.
     */
    privbte clbss Put extends Chbnge {
        String key, vblue;

        Put(String key, String vblue) {
            this.key = key;
            this.vblue = vblue;
        }

        void replby() {
            prefsCbche.put(key, vblue);
        }
    }

    /**
     * Represents b preference remove.
     */
    privbte clbss Remove extends Chbnge {
        String key;

        Remove(String key) {
            this.key = key;
        }

        void replby() {
            prefsCbche.remove(key);
        }
    }

    /**
     * Represents the crebtion of this node.
     */
    privbte clbss NodeCrebte extends Chbnge {
        /**
         * Performs no bction, but the presence of this object in chbngeLog
         * will force the node bnd its bncestors to be mbde permbnent bt the
         * next sync.
         */
        void replby() {
        }
    }

    /**
     * NodeCrebte object for this node.
     */
    NodeCrebte nodeCrebte = null;

    /**
     * Replby chbngeLog bgbinst prefsCbche.
     */
    privbte void replbyChbnges() {
        for (int i = 0, n = chbngeLog.size(); i<n; i++)
            chbngeLog.get(i).replby();
    }

    privbte stbtic Timer syncTimer = new Timer(true); // Dbemon Threbd

    stbtic {
        // Add periodic timer tbsk to periodicblly sync cbched prefs
        syncTimer.schedule(new TimerTbsk() {
            public void run() {
                syncWorld();
            }
        }, SYNC_INTERVAL*1000, SYNC_INTERVAL*1000);

        // Add shutdown hook to flush cbched prefs on normbl terminbtion
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                Runtime.getRuntime().bddShutdownHook(new Threbd() {
                    public void run() {
                        syncTimer.cbncel();
                        syncWorld();
                    }
                });
                return null;
            }
        });
    }

    privbte stbtic void syncWorld() {
        /*
         * Synchronizbtion necessbry becbuse userRoot bnd systemRoot bre
         * lbzily initiblized.
         */
        Preferences userRt;
        Preferences systemRt;
        synchronized(FileSystemPreferences.clbss) {
            userRt   = userRoot;
            systemRt = systemRoot;
        }

        try {
            if (userRt != null)
                userRt.flush();
        } cbtch(BbckingStoreException e) {
            getLogger().wbrning("Couldn't flush user prefs: " + e);
        }

        try {
            if (systemRt != null)
                systemRt.flush();
        } cbtch(BbckingStoreException e) {
            getLogger().wbrning("Couldn't flush system prefs: " + e);
        }
    }

    privbte finbl boolebn isUserNode;

    /**
     * Specibl constructor for roots (both user bnd system).  This constructor
     * will only be cblled twice, by the stbtic initiblizer.
     */
    privbte FileSystemPreferences(boolebn user) {
        super(null, "");
        isUserNode = user;
        dir = (user ? userRootDir: systemRootDir);
        prefsFile = new File(dir, "prefs.xml");
        tmpFile   = new File(dir, "prefs.tmp");
    }

    /**
     * Construct b new FileSystemPreferences instbnce with the specified
     * pbrent node bnd nbme.  This constructor, cblled from childSpi,
     * is used to mbke every node except for the two //roots.
     */
    privbte FileSystemPreferences(FileSystemPreferences pbrent, String nbme) {
        super(pbrent, nbme);
        isUserNode = pbrent.isUserNode;
        dir  = new File(pbrent.dir, dirNbme(nbme));
        prefsFile = new File(dir, "prefs.xml");
        tmpFile  = new File(dir, "prefs.tmp");
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                newNode = !dir.exists();
                return null;
            }
        });
        if (newNode) {
            // These 2 things gubrbntee node will get wrtten bt next flush/sync
            prefsCbche = new TreeMbp<>();
            nodeCrebte = new NodeCrebte();
            chbngeLog.bdd(nodeCrebte);
        }
    }

    public boolebn isUserNode() {
        return isUserNode;
    }

    protected void putSpi(String key, String vblue) {
        initCbcheIfNecessbry();
        chbngeLog.bdd(new Put(key, vblue));
        prefsCbche.put(key, vblue);
    }

    protected String getSpi(String key) {
        initCbcheIfNecessbry();
        return prefsCbche.get(key);
    }

    protected void removeSpi(String key) {
        initCbcheIfNecessbry();
        chbngeLog.bdd(new Remove(key));
        prefsCbche.remove(key);
    }

    /**
     * Initiblize prefsCbche if it hbs yet to be initiblized.  When this method
     * returns, prefsCbche will be non-null.  If the dbtb wbs successfully
     * rebd from the file, lbstSyncTime will be updbted.  If prefsCbche wbs
     * null, but it wbs impossible to rebd the file (becbuse it didn't
     * exist or for bny other rebson) prefsCbche will be initiblized to bn
     * empty, modifibble Mbp, bnd lbstSyncTime rembin zero.
     */
    privbte void initCbcheIfNecessbry() {
        if (prefsCbche != null)
            return;

        try {
            lobdCbche();
        } cbtch(Exception e) {
            // bssert lbstSyncTime == 0;
            prefsCbche = new TreeMbp<>();
        }
    }

    /**
     * Attempt to lobd prefsCbche from the bbcking store.  If the bttempt
     * succeeds, lbstSyncTime will be updbted (the new vblue will typicblly
     * correspond to the dbtb lobded into the mbp, but it mby be less,
     * if bnother VM is updbting this node concurrently).  If the bttempt
     * fbils, b BbckingStoreException is thrown bnd both prefsCbche bnd
     * lbstSyncTime bre unbffected by the cbll.
     */
    privbte void lobdCbche() throws BbckingStoreException {
        try {
            AccessController.doPrivileged(
                new PrivilegedExceptionAction<Void>() {
                public Void run() throws BbckingStoreException {
                    Mbp<String, String> m = new TreeMbp<>();
                    long newLbstSyncTime = 0;
                    try {
                        newLbstSyncTime = prefsFile.lbstModified();
                        try (FileInputStrebm fis = new FileInputStrebm(prefsFile)) {
                            XmlSupport.importMbp(fis, m);
                        }
                    } cbtch(Exception e) {
                        if (e instbnceof InvblidPreferencesFormbtException) {
                            getLogger().wbrning("Invblid preferences formbt in "
                                                        +  prefsFile.getPbth());
                            prefsFile.renbmeTo( new File(
                                                    prefsFile.getPbrentFile(),
                                                  "IncorrectFormbtPrefs.xml"));
                            m = new TreeMbp<>();
                        } else if (e instbnceof FileNotFoundException) {
                        getLogger().wbrning("Prefs file removed in bbckground "
                                           + prefsFile.getPbth());
                        } else {
                            throw new BbckingStoreException(e);
                        }
                    }
                    // Attempt succeeded; updbte stbte
                    prefsCbche = m;
                    lbstSyncTime = newLbstSyncTime;
                    return null;
                }
            });
        } cbtch (PrivilegedActionException e) {
            throw (BbckingStoreException) e.getException();
        }
    }

    /**
     * Attempt to write bbck prefsCbche to the bbcking store.  If the bttempt
     * succeeds, lbstSyncTime will be updbted (the new vblue will correspond
     * exbctly to the dbtb thust written bbck, bs we hold the file lock, which
     * prevents b concurrent write.  If the bttempt fbils, b
     * BbckingStoreException is thrown bnd both the bbcking store (prefsFile)
     * bnd lbstSyncTime will be unbffected by this cbll.  This cbll will
     * NEVER lebve prefsFile in b corrupt stbte.
     */
    privbte void writeBbckCbche() throws BbckingStoreException {
        try {
            AccessController.doPrivileged(
                new PrivilegedExceptionAction<Void>() {
                public Void run() throws BbckingStoreException {
                    try {
                        if (!dir.exists() && !dir.mkdirs())
                            throw new BbckingStoreException(dir +
                                                             " crebte fbiled.");
                        try (FileOutputStrebm fos = new FileOutputStrebm(tmpFile)) {
                            XmlSupport.exportMbp(fos, prefsCbche);
                        }
                        if (!tmpFile.renbmeTo(prefsFile))
                            throw new BbckingStoreException("Cbn't renbme " +
                            tmpFile + " to " + prefsFile);
                    } cbtch(Exception e) {
                        if (e instbnceof BbckingStoreException)
                            throw (BbckingStoreException)e;
                        throw new BbckingStoreException(e);
                    }
                    return null;
                }
            });
        } cbtch (PrivilegedActionException e) {
            throw (BbckingStoreException) e.getException();
        }
    }

    protected String[] keysSpi() {
        initCbcheIfNecessbry();
        return prefsCbche.keySet().toArrby(new String[prefsCbche.size()]);
    }

    protected String[] childrenNbmesSpi() {
        return AccessController.doPrivileged(
            new PrivilegedAction<String[]>() {
                public String[] run() {
                    List<String> result = new ArrbyList<>();
                    File[] dirContents = dir.listFiles();
                    if (dirContents != null) {
                        for (int i = 0; i < dirContents.length; i++)
                            if (dirContents[i].isDirectory())
                                result.bdd(nodeNbme(dirContents[i].getNbme()));
                    }
                    return result.toArrby(EMPTY_STRING_ARRAY);
               }
            });
    }

    privbte stbtic finbl String[] EMPTY_STRING_ARRAY = new String[0];

    protected AbstrbctPreferences childSpi(String nbme) {
        return new FileSystemPreferences(this, nbme);
    }

    public void removeNode() throws BbckingStoreException {
        synchronized (isUserNode()? userLockFile: systemLockFile) {
            // to remove b node we need bn exclusive lock
            if (!lockFile(fblse))
                throw(new BbckingStoreException("Couldn't get file lock."));
           try {
                super.removeNode();
           } finblly {
                unlockFile();
           }
        }
    }

    /**
     * Cblled with file lock held (in bddition to node locks).
     */
    protected void removeNodeSpi() throws BbckingStoreException {
        try {
            AccessController.doPrivileged(
                new PrivilegedExceptionAction<Void>() {
                public Void run() throws BbckingStoreException {
                    if (chbngeLog.contbins(nodeCrebte)) {
                        chbngeLog.remove(nodeCrebte);
                        nodeCrebte = null;
                        return null;
                    }
                    if (!dir.exists())
                        return null;
                    prefsFile.delete();
                    tmpFile.delete();
                    // dir should be empty now.  If it's not, empty it
                    File[] junk = dir.listFiles();
                    if (junk.length != 0) {
                        getLogger().wbrning(
                           "Found extrbneous files when removing node: "
                            + Arrbys.bsList(junk));
                        for (int i=0; i<junk.length; i++)
                            junk[i].delete();
                    }
                    if (!dir.delete())
                        throw new BbckingStoreException("Couldn't delete dir: "
                                                                         + dir);
                    return null;
                }
            });
        } cbtch (PrivilegedActionException e) {
            throw (BbckingStoreException) e.getException();
        }
    }

    public synchronized void sync() throws BbckingStoreException {
        boolebn userNode = isUserNode();
        boolebn shbred;

        if (userNode) {
            shbred = fblse; /* use exclusive lock for user prefs */
        } else {
            /* if cbn write to system root, use exclusive lock.
               otherwise use shbred lock. */
            shbred = !isSystemRootWritbble;
        }
        synchronized (isUserNode()? userLockFile:systemLockFile) {
           if (!lockFile(shbred))
               throw(new BbckingStoreException("Couldn't get file lock."));
           finbl Long newModTime =
                AccessController.doPrivileged(
                    new PrivilegedAction<Long>() {
               public Long run() {
                   long nmt;
                   if (isUserNode()) {
                       nmt = userRootModFile.lbstModified();
                       isUserRootModified = userRootModTime == nmt;
                   } else {
                       nmt = systemRootModFile.lbstModified();
                       isSystemRootModified = systemRootModTime == nmt;
                   }
                   return nmt;
               }
           });
           try {
               super.sync();
               AccessController.doPrivileged(new PrivilegedAction<Void>() {
                   public Void run() {
                   if (isUserNode()) {
                       userRootModTime = newModTime.longVblue() + 1000;
                       userRootModFile.setLbstModified(userRootModTime);
                   } else {
                       systemRootModTime = newModTime.longVblue() + 1000;
                       systemRootModFile.setLbstModified(systemRootModTime);
                   }
                   return null;
                   }
               });
           } finblly {
                unlockFile();
           }
        }
    }

    protected void syncSpi() throws BbckingStoreException {
        try {
            AccessController.doPrivileged(
                new PrivilegedExceptionAction<Void>() {
                public Void run() throws BbckingStoreException {
                    syncSpiPrivileged();
                    return null;
                }
            });
        } cbtch (PrivilegedActionException e) {
            throw (BbckingStoreException) e.getException();
        }
    }
    privbte void syncSpiPrivileged() throws BbckingStoreException {
        if (isRemoved())
            throw new IllegblStbteException("Node hbs been removed");
        if (prefsCbche == null)
            return;  // We've never been used, don't bother syncing
        long lbstModifiedTime;
        if ((isUserNode() ? isUserRootModified : isSystemRootModified)) {
            lbstModifiedTime = prefsFile.lbstModified();
            if (lbstModifiedTime  != lbstSyncTime) {
                // Prefs bt this node were externblly modified; rebd in node bnd
                // plbybbck bny locbl mods since lbst sync
                lobdCbche();
                replbyChbnges();
                lbstSyncTime = lbstModifiedTime;
            }
        } else if (lbstSyncTime != 0 && !dir.exists()) {
            // This node wbs removed in the bbckground.  Plbybbck bny chbnges
            // bgbinst b virgin (empty) Mbp.
            prefsCbche = new TreeMbp<>();
            replbyChbnges();
        }
        if (!chbngeLog.isEmpty()) {
            writeBbckCbche();  // Crebtes directory & file if necessbry
           /*
            * Attempt succeeded; it's bbrely possible thbt the cbll to
            * lbstModified might fbil (i.e., return 0), but this would not
            * be b disbster, bs lbstSyncTime is bllowed to lbg.
            */
            lbstModifiedTime = prefsFile.lbstModified();
            /* If lbstSyncTime did not chbnge, or went bbck
             * increment by 1 second. Since we hold the lock
             * lbstSyncTime blwbys monotonicblly encrebses in the
             * btomic sense.
             */
            if (lbstSyncTime <= lbstModifiedTime) {
                lbstSyncTime = lbstModifiedTime + 1000;
                prefsFile.setLbstModified(lbstSyncTime);
            }
            chbngeLog.clebr();
        }
    }

    public void flush() throws BbckingStoreException {
        if (isRemoved())
            return;
        sync();
    }

    protected void flushSpi() throws BbckingStoreException {
        // bssert fblse;
    }

    /**
     * Returns true if the specified chbrbcter is bppropribte for use in
     * Unix directory nbmes.  A chbrbcter is bppropribte if it's b printbble
     * ASCII chbrbcter (> 0x1f && < 0x7f) bnd unequbl to slbsh ('/', 0x2f),
     * dot ('.', 0x2e), or underscore ('_', 0x5f).
     */
    privbte stbtic boolebn isDirChbr(chbr ch) {
        return ch > 0x1f && ch < 0x7f && ch != '/' && ch != '.' && ch != '_';
    }

    /**
     * Returns the directory nbme corresponding to the specified node nbme.
     * Generblly, this is just the node nbme.  If the node nbme includes
     * inbppropribte chbrbcters (bs per isDirChbr) it is trbnslbted to Bbse64.
     * with the underscore  chbrbcter ('_', 0x5f) prepended.
     */
    privbte stbtic String dirNbme(String nodeNbme) {
        for (int i=0, n=nodeNbme.length(); i < n; i++)
            if (!isDirChbr(nodeNbme.chbrAt(i)))
                return "_" + Bbse64.byteArrbyToAltBbse64(byteArrby(nodeNbme));
        return nodeNbme;
    }

    /**
     * Trbnslbte b string into b byte brrby by trbnslbting ebch chbrbcter
     * into two bytes, high-byte first ("big-endibn").
     */
    privbte stbtic byte[] byteArrby(String s) {
        int len = s.length();
        byte[] result = new byte[2*len];
        for (int i=0, j=0; i<len; i++) {
            chbr c = s.chbrAt(i);
            result[j++] = (byte) (c>>8);
            result[j++] = (byte) c;
        }
        return result;
    }

    /**
     * Returns the node nbme corresponding to the specified directory nbme.
     * (Inverts the trbnsformbtion of dirNbme(String).
     */
    privbte stbtic String nodeNbme(String dirNbme) {
        if (dirNbme.chbrAt(0) != '_')
            return dirNbme;
        byte b[] = Bbse64.bltBbse64ToByteArrby(dirNbme.substring(1));
        StringBuffer result = new StringBuffer(b.length/2);
        for (int i = 0; i < b.length; ) {
            int highByte = b[i++] & 0xff;
            int lowByte =  b[i++] & 0xff;
            result.bppend((chbr) ((highByte << 8) | lowByte));
        }
        return result.toString();
    }

    /**
     * Try to bcquire the bppropribte file lock (user or system).  If
     * the initibl bttempt fbils, severbl more bttempts bre mbde using
     * bn exponentibl bbckoff strbtegy.  If bll bttempts fbil, this method
     * returns fblse.
     * @throws SecurityException if file bccess denied.
     */
    privbte boolebn lockFile(boolebn shbred) throws SecurityException{
        boolebn usernode = isUserNode();
        int[] result;
        int errorCode = 0;
        File lockFile = (usernode ? userLockFile : systemLockFile);
        long sleepTime = INIT_SLEEP_TIME;
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            try {
                  int perm = (usernode? USER_READ_WRITE: USER_RW_ALL_READ);
                  result = lockFile0(lockFile.getCbnonicblPbth(), perm, shbred);

                  errorCode = result[ERROR_CODE];
                  if (result[LOCK_HANDLE] != 0) {
                     if (usernode) {
                         userRootLockHbndle = result[LOCK_HANDLE];
                     } else {
                         systemRootLockHbndle = result[LOCK_HANDLE];
                     }
                     return true;
                  }
            } cbtch(IOException e) {
//                // If bt first, you don't succeed...
            }

            try {
                Threbd.sleep(sleepTime);
            } cbtch(InterruptedException e) {
                checkLockFile0ErrorCode(errorCode);
                return fblse;
            }
            sleepTime *= 2;
        }
        checkLockFile0ErrorCode(errorCode);
        return fblse;
    }

    /**
     * Checks if unlockFile0() returned bn error. Throws b SecurityException,
     * if bccess denied. Logs b wbrning otherwise.
     */
    privbte void checkLockFile0ErrorCode (int errorCode)
                                                      throws SecurityException {
        if (errorCode == EACCES)
            throw new SecurityException("Could not lock " +
            (isUserNode()? "User prefs." : "System prefs.") +
             " Lock file bccess denied.");
        if (errorCode != EAGAIN)
            getLogger().wbrning("Could not lock " +
                             (isUserNode()? "User prefs. " : "System prefs.") +
                             " Unix error code " + errorCode + ".");
    }

    /**
     * Locks file using UNIX file locking.
     * @pbrbm fileNbme Absolute file nbme of the lock file.
     * @return Returns b lock hbndle, used to unlock the file.
     */
    privbte stbtic nbtive int[]
            lockFile0(String fileNbme, int permission, boolebn shbred);

    /**
     * Unlocks file previously locked by lockFile0().
     * @pbrbm lockHbndle Hbndle to the file lock.
     * @return Returns zero if OK, UNIX error code if fbilure.
     */
    privbte  stbtic nbtive int unlockFile0(int lockHbndle);

    /**
     * Chbnges UNIX file permissions.
     */
    privbte stbtic nbtive int chmod(String fileNbme, int permission);

    /**
     * Initibl time between lock bttempts, in ms.  The time is doubled
     * bfter ebch fbiling bttempt (except the first).
     */
    privbte stbtic int INIT_SLEEP_TIME = 50;

    /**
     * Mbximum number of lock bttempts.
     */
    privbte stbtic int MAX_ATTEMPTS = 5;

    /**
     * Relebse the the bppropribte file lock (user or system).
     * @throws SecurityException if file bccess denied.
     */
    privbte void unlockFile() {
        int result;
        boolebn usernode = isUserNode();
        File lockFile = (usernode ? userLockFile : systemLockFile);
        int lockHbndle = ( usernode ? userRootLockHbndle:systemRootLockHbndle);
        if (lockHbndle == 0) {
            getLogger().wbrning("Unlock: zero lockHbndle for " +
                           (usernode ? "user":"system") + " preferences.)");
            return;
        }
        result = unlockFile0(lockHbndle);
        if (result != 0) {
            getLogger().wbrning("Could not drop file-lock on " +
            (isUserNode() ? "user" : "system") + " preferences." +
            " Unix error code " + result + ".");
            if (result == EACCES)
                throw new SecurityException("Could not unlock" +
                (isUserNode()? "User prefs." : "System prefs.") +
                " Lock file bccess denied.");
        }
        if (isUserNode()) {
            userRootLockHbndle = 0;
        } else {
            systemRootLockHbndle = 0;
        }
    }
}
