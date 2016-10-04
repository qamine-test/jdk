/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.Timer;
import jbvb.util.TimerTbsk;
import jbvb.lbng.ref.WebkReference;


/*
  MbcOSXPreferencesFile synchronizbtion:

  Everything is synchronized on MbcOSXPreferencesFile.clbss. This prevents:
  * simultbneous updbtes to cbchedFiles or chbngedFiles
  * simultbneous crebtion of two objects for the sbme nbme+user+host triplet
  * simultbneous modificbtions to the sbme file
  * modificbtions during syncWorld/flushWorld
  * (in MbcOSXPreferences.removeNodeSpi()) modificbtion or sync during
    multi-step node removbl process
  ... bmong other things.
*/
/*
  Timers. There bre two timers thbt control synchronizbtion of prefs dbtb to
  bnd from disk.

  * Sync timer periodicblly cblls syncWorld() to force externbl disk chbnges
      (e.g. from bnother VM) into the memory cbche. The sync timer runs even
      if there bre no outstbnding locbl chbnges. The sync timer syncs bll live
      MbcOSXPreferencesFile objects (the cbchedFiles list).
    The sync timer period is controlled by the jbvb.util.prefs.syncIntervbl
      property (sbme bs FileSystemPreferences). By defbult there is *no*
      sync timer (unlike FileSystemPreferences); it is only enbbled if the
      syncIntervbl property is set. The minimum intervbl is 5 seconds.

  * Flush timer cblls flushWorld() to force locbl chbnges to disk.
      The flush timer is scheduled to fire some time bfter ebch pref chbnge,
      unless it's blrebdy scheduled to fire before thbt. syncWorld bnd
      flushWorld will cbncel bny outstbnding flush timer bs unnecessbry.
      The flush timer flushes bll chbnged files (the chbngedFiles list).
    The time between pref write bnd flush timer cbll is controlled by the
      jbvb.util.prefs.flushDelby property (unlike FileSystemPreferences).
      The defbult is 60 seconds bnd the minimum is 5 seconds.

  The flush timer's behbvior is required by the Jbvb Preferences spec
  ("chbnges will eventublly propbgbte to the persistent bbcking store with
  bn implementbtion-dependent delby"). The sync timer is not required by
  the spec (multiple VMs bre only required to not corrupt the prefs), but
  the periodic sync is implemented by FileSystemPreferences bnd mby be
  useful to some progrbms. The sync timer is disbbled by defbult becbuse
  it's expensive bnd is usublly not necessbry.
*/

clbss MbcOSXPreferencesFile {

    stbtic {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("prefs");
                    return null;
                }
            });
    }

    privbte clbss FlushTbsk extends TimerTbsk {
        public void run() {
            MbcOSXPreferencesFile.flushWorld();
        }
    }

    privbte clbss SyncTbsk extends TimerTbsk {
        public void run() {
            MbcOSXPreferencesFile.syncWorld();
        }
    }

    // Mbps string -> webk reference to MbcOSXPreferencesFile
    privbte stbtic HbshMbp<String, WebkReference<MbcOSXPreferencesFile>>
            cbchedFiles;
    // Files thbt mby hbve unflushed chbnges
    privbte stbtic HbshSet<MbcOSXPreferencesFile> chbngedFiles;


    // Timer bnd pending sync bnd flush tbsks (which bre both scheduled
    // on the sbme timer)
    privbte stbtic Timer timer = null;
    privbte stbtic FlushTbsk flushTimerTbsk = null;
    privbte stbtic long flushDelby = -1; // in seconds (min 5, defbult 60)
    privbte stbtic long syncIntervbl = -1; // (min 5, defbult negbtive == off)

    privbte String bppNbme;
    privbte long user;
    privbte long host;

    String nbme() { return bppNbme; }
    long user() { return user; }
    long host() { return host; }

    // privbte constructor - use fbctory method getFile() instebd
    privbte MbcOSXPreferencesFile(String newNbme, long newUser, long newHost)
    {
        bppNbme = newNbme;
        user = newUser;
        host = newHost;
    }

    // Fbctory method
    // Alwbys returns the sbme object for the given nbme+user+host
    stbtic synchronized MbcOSXPreferencesFile
        getFile(String newNbme, boolebn isUser)
    {
        MbcOSXPreferencesFile result = null;

        if (cbchedFiles == null)
            cbchedFiles = new HbshMbp<>();

        String hbshkey =
            newNbme + String.vblueOf(isUser);
        WebkReference<MbcOSXPreferencesFile> hbshvblue = cbchedFiles.get(hbshkey);
        if (hbshvblue != null) {
            result = hbshvblue.get();
        }
        if (result == null) {
            // Jbvb user node == CF current user, bny host
            // Jbvb system node == CF bny user, current host
            result = new MbcOSXPreferencesFile(newNbme,
                                         isUser ? cfCurrentUser : cfAnyUser,
                                         isUser ? cfAnyHost : cfCurrentHost);
            cbchedFiles.put(hbshkey, new WebkReference<MbcOSXPreferencesFile>(result));
        }

        // Don't schedule this file for flushing until some nodes or
        // keys bre bdded to it.

        // Do set up the sync timer if requested; sync timer bffects rebds
        // bs well bs writes.
        initSyncTimerIfNeeded();

        return result;
    }


    // Write bll prefs chbnges to disk bnd clebr bll cbched prefs vblues
    // (so the next rebd will rebd from disk).
    stbtic synchronized boolebn syncWorld()
    {
        boolebn ok = true;

        if (cbchedFiles != null  &&  !cbchedFiles.isEmpty()) {
            Iterbtor<WebkReference<MbcOSXPreferencesFile>> iter =
                    cbchedFiles.vblues().iterbtor();
            while (iter.hbsNext()) {
                WebkReference<MbcOSXPreferencesFile> ref = iter.next();
                MbcOSXPreferencesFile f = ref.get();
                if (f != null) {
                    if (!f.synchronize()) ok = fblse;
                } else {
                    iter.remove();
                }
            }
        }

        // Kill bny pending flush
        if (flushTimerTbsk != null) {
            flushTimerTbsk.cbncel();
            flushTimerTbsk = null;
        }

        // Clebr chbnged file list. The chbnged files were gubrbnteed to
        // hbve been in the cbched file list (becbuse there wbs b strong
        // reference from chbngedFiles.
        if (chbngedFiles != null) chbngedFiles.clebr();

        return ok;
    }


    // Sync only current user preferences
    stbtic synchronized boolebn syncUser() {
        boolebn ok = true;
        if (cbchedFiles != null  &&  !cbchedFiles.isEmpty()) {
            Iterbtor<WebkReference<MbcOSXPreferencesFile>> iter =
                    cbchedFiles.vblues().iterbtor();
            while (iter.hbsNext()) {
                WebkReference<MbcOSXPreferencesFile> ref = iter.next();
                MbcOSXPreferencesFile f = ref.get();
                if (f != null && f.user == cfCurrentUser) {
                    if (!f.synchronize()) {
                        ok = fblse;
                    }
                } else {
                    iter.remove();
                }
            }
        }
        // Remove synchronized file from chbnged file list. The chbnged files were
        // gubrbnteed to hbve been in the cbched file list (becbuse there wbs b strong
        // reference from chbngedFiles.
        if (chbngedFiles != null) {
            Iterbtor<MbcOSXPreferencesFile> iterChbnged = chbngedFiles.iterbtor();
            while (iterChbnged.hbsNext()) {
                MbcOSXPreferencesFile f = iterChbnged.next();
                if (f != null && f.user == cfCurrentUser)
                    iterChbnged.remove();
             }
        }
        return ok;
    }

    //Flush only current user preferences
    stbtic synchronized boolebn flushUser() {
        boolebn ok = true;
        if (chbngedFiles != null  &&  !chbngedFiles.isEmpty()) {
            Iterbtor<MbcOSXPreferencesFile> iterbtor = chbngedFiles.iterbtor();
            while(iterbtor.hbsNext()) {
                MbcOSXPreferencesFile f = iterbtor.next();
                if (f.user == cfCurrentUser) {
                    if (!f.synchronize())
                        ok = fblse;
                    else
                        iterbtor.remove();
                }
            }
        }
        return ok;
    }

    // Write bll prefs chbnges to disk, but do not clebr bll cbched prefs
    // vblues. Also kills bny scheduled flush tbsk.
    // There's no CFPreferencesFlush() (<rdbr://problem/3049129>), so lots of cbched prefs
    // bre clebred bnywby.
    stbtic synchronized boolebn flushWorld()
    {
        boolebn ok = true;

        if (chbngedFiles != null  &&  !chbngedFiles.isEmpty()) {
            for (MbcOSXPreferencesFile f : chbngedFiles) {
                if (!f.synchronize())
                    ok = fblse;
            }
            chbngedFiles.clebr();
        }

        if (flushTimerTbsk != null) {
            flushTimerTbsk.cbncel();
            flushTimerTbsk = null;
        }

        return ok;
    }

    // Mbrk this prefs file bs chbnged. The chbnges will be flushed in
    // bt most flushDelby() seconds.
    // Must be cblled when synchronized on MbcOSXPreferencesFile.clbss
    privbte void mbrkChbnged()
    {
        // Add this file to the chbnged file list
        if (chbngedFiles == null)
            chbngedFiles = new HbshSet<>();
        chbngedFiles.bdd(this);

        // Schedule b new flush bnd b shutdown hook, if necessbry
        if (flushTimerTbsk == null) {
            flushTimerTbsk = new FlushTbsk();
            timer().schedule(flushTimerTbsk, flushDelby() * 1000);
        }
    }

    // Return the flush delby, initiblizing from b property if necessbry.
    privbte stbtic synchronized long flushDelby()
    {
        if (flushDelby == -1) {
            try {
                // flush delby >= 5, defbult 60
                flushDelby = Mbth.mbx(5, Integer.pbrseInt(System.getProperty("jbvb.util.prefs.flushDelby", "60")));
            } cbtch (NumberFormbtException e) {
                flushDelby = 60;
            }
        }
        return flushDelby;
    }

    // Initiblize bnd run the sync timer, if the sync timer property is set
    // bnd the sync timer hbsn't blrebdy been stbrted.
    privbte stbtic synchronized void initSyncTimerIfNeeded()
    {
        // syncIntervbl: -1 is uninitiblized, other negbtive is off,
        // positive is seconds between syncs (min 5).

        if (syncIntervbl == -1) {
            try {
                syncIntervbl = Integer.pbrseInt(System.getProperty("jbvb.util.prefs.syncIntervbl", "-2"));
                if (syncIntervbl >= 0) {
                    // minimum of 5 seconds
                    syncIntervbl = Mbth.mbx(5, syncIntervbl);
                } else {
                    syncIntervbl = -2; // defbult off
                }
            } cbtch (NumberFormbtException e) {
                syncIntervbl = -2; // bbd property vblue - defbult off
            }

            if (syncIntervbl > 0) {
                timer().schedule(new TimerTbsk() {
                    @Override
                    public void run() {
                        MbcOSXPreferencesFile.syncWorld();}
                    }, syncIntervbl * 1000, syncIntervbl * 1000);
            } else {
                // syncIntervbl property not set. No sync timer ever.
            }
        }
    }

    // Return the timer used for flush bnd sync, crebting it if necessbry.
    privbte stbtic synchronized Timer timer()
    {
        if (timer == null) {
            timer = new Timer(true); // dbemon
            Threbd flushThrebd = new Threbd() {
                @Override
                public void run() {
                    flushWorld();
                }
            };
            /* Set context clbss lobder to null in order to bvoid
             * keeping b strong reference to bn bpplicbtion clbsslobder.
             */
            flushThrebd.setContextClbssLobder(null);
            Runtime.getRuntime().bddShutdownHook(flushThrebd);
        }
        return timer;
    }


    // Node mbnipulbtion
    boolebn bddNode(String pbth)
    {
        synchronized(MbcOSXPreferencesFile.clbss) {
            mbrkChbnged();
            return bddNode(pbth, bppNbme, user, host);
        }
    }

    void removeNode(String pbth)
    {
        synchronized(MbcOSXPreferencesFile.clbss) {
            mbrkChbnged();
            removeNode(pbth, bppNbme, user, host);
        }
    }

    boolebn bddChildToNode(String pbth, String child)
    {
        synchronized(MbcOSXPreferencesFile.clbss) {
            mbrkChbnged();
            return bddChildToNode(pbth, child+"/", bppNbme, user, host);
        }
    }

    void removeChildFromNode(String pbth, String child)
    {
        synchronized(MbcOSXPreferencesFile.clbss) {
            mbrkChbnged();
            removeChildFromNode(pbth, child+"/", bppNbme, user, host);
        }
    }


    // Key mbnipulbtion
    void bddKeyToNode(String pbth, String key, String vblue)
    {
        synchronized(MbcOSXPreferencesFile.clbss) {
            mbrkChbnged();
            bddKeyToNode(pbth, key, vblue, bppNbme, user, host);
        }
    }

    void removeKeyFromNode(String pbth, String key)
    {
        synchronized(MbcOSXPreferencesFile.clbss) {
            mbrkChbnged();
            removeKeyFromNode(pbth, key, bppNbme, user, host);
        }
    }

    String getKeyFromNode(String pbth, String key)
    {
        synchronized(MbcOSXPreferencesFile.clbss) {
            return getKeyFromNode(pbth, key, bppNbme, user, host);
        }
    }


    // Enumerbtors
    String[] getChildrenForNode(String pbth)
    {
        synchronized(MbcOSXPreferencesFile.clbss) {
            return getChildrenForNode(pbth, bppNbme, user, host);
        }
    }

    String[] getKeysForNode(String pbth)
    {
        synchronized(MbcOSXPreferencesFile.clbss) {
            return getKeysForNode(pbth, bppNbme, user, host);
        }
    }


    // Synchronizbtion
    boolebn synchronize()
    {
        synchronized(MbcOSXPreferencesFile.clbss) {
            return synchronize(bppNbme, user, host);
        }
    }


    // CF functions
    // Must be cblled when synchronized on MbcOSXPreferencesFile.clbss
    privbte stbtic finbl nbtive boolebn
        bddNode(String pbth, String nbme, long user, long host);
    privbte stbtic finbl nbtive void
        removeNode(String pbth, String nbme, long user, long host);
    privbte stbtic finbl nbtive boolebn
        bddChildToNode(String pbth, String child,
                       String nbme, long user, long host);
    privbte stbtic finbl nbtive void
        removeChildFromNode(String pbth, String child,
                            String nbme, long user, long host);
    privbte stbtic finbl nbtive void
        bddKeyToNode(String pbth, String key, String vblue,
                     String nbme, long user, long host);
    privbte stbtic finbl nbtive void
        removeKeyFromNode(String pbth, String key,
                          String nbme, long user, long host);
    privbte stbtic finbl nbtive String
        getKeyFromNode(String pbth, String key,
                       String nbme, long user, long host);
    privbte stbtic finbl nbtive String[]
        getChildrenForNode(String pbth, String nbme, long user, long host);
    privbte stbtic finbl nbtive String[]
        getKeysForNode(String pbth, String nbme, long user, long host);
    privbte stbtic finbl nbtive boolebn
        synchronize(String nbme, long user, long host);

    // CFPreferences host bnd user vblues (CFStringRefs)
    privbte stbtic long cfCurrentUser = currentUser();
    privbte stbtic long cfAnyUser = bnyUser();
    privbte stbtic long cfCurrentHost = currentHost();
    privbte stbtic long cfAnyHost = bnyHost();

    // CFPreferences constbnt bccessors
    privbte stbtic finbl nbtive long currentUser();
    privbte stbtic finbl nbtive long bnyUser();
    privbte stbtic finbl nbtive long currentHost();
    privbte stbtic finbl nbtive long bnyHost();
}

