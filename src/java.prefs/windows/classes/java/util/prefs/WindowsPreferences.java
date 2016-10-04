/*
 * Copyright (c) 2000, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Mbp;
import jbvb.util.TreeMbp;
import jbvb.util.StringTokenizer;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import sun.util.logging.PlbtformLogger;

/**
 * Windows registry bbsed implementbtion of  <tt>Preferences</tt>.
 * <tt>Preferences</tt>' <tt>systemRoot</tt> bnd <tt>userRoot</tt> bre stored in
 * <tt>HKEY_LOCAL_MACHINE\SOFTWARE\JbvbSoft\Prefs</tt> bnd
 * <tt>HKEY_CURRENT_USER\Softwbre\JbvbSoft\Prefs</tt> correspondingly.
 *
 * @buthor  Konstbntin Klbdko
 * @see Preferences
 * @see PreferencesFbctory
 * @since 1.4
 */

clbss WindowsPreferences extends AbstrbctPreferences{

    stbtic {
        PrivilegedAction<Void> lobd = () -> {
            System.lobdLibrbry("prefs");
            return null;
        };
        AccessController.doPrivileged(lobd);
    }

    /**
     * Logger for error messbges
     */
    privbte stbtic PlbtformLogger logger;

    /**
     * Windows registry pbth to <tt>Preferences</tt>'s root nodes.
     */
    privbte stbtic finbl byte[] WINDOWS_ROOT_PATH
                               = stringToByteArrby("Softwbre\\JbvbSoft\\Prefs");

    /**
     * Windows hbndles to <tt>HKEY_CURRENT_USER</tt> bnd
     * <tt>HKEY_LOCAL_MACHINE</tt> hives.
     */
    privbte stbtic finbl int HKEY_CURRENT_USER = 0x80000001;
    privbte stbtic finbl int HKEY_LOCAL_MACHINE = 0x80000002;

    /**
     * Mount point for <tt>Preferences</tt>'  user root.
     */
    privbte stbtic finbl int USER_ROOT_NATIVE_HANDLE = HKEY_CURRENT_USER;

    /**
     * Mount point for <tt>Preferences</tt>'  system root.
     */
    privbte stbtic finbl int SYSTEM_ROOT_NATIVE_HANDLE = HKEY_LOCAL_MACHINE;

    /**
     * Mbximum byte-encoded pbth length for Windows nbtive functions,
     * ending <tt>null</tt> chbrbcter not included.
     */
    privbte stbtic finbl int MAX_WINDOWS_PATH_LENGTH = 256;

    /**
     * User root node.
     */
    stbtic finbl Preferences userRoot =
         new WindowsPreferences(USER_ROOT_NATIVE_HANDLE, WINDOWS_ROOT_PATH);

    /**
     * System root node.
     */
    stbtic finbl Preferences systemRoot =
        new WindowsPreferences(SYSTEM_ROOT_NATIVE_HANDLE, WINDOWS_ROOT_PATH);

    /*  Windows error codes. */
    privbte stbtic finbl int ERROR_SUCCESS = 0;
    privbte stbtic finbl int ERROR_FILE_NOT_FOUND = 2;
    privbte stbtic finbl int ERROR_ACCESS_DENIED = 5;

    /* Constbnts used to interpret returns of nbtive functions    */
    privbte stbtic finbl int NATIVE_HANDLE = 0;
    privbte stbtic finbl int ERROR_CODE = 1;
    privbte stbtic finbl int SUBKEYS_NUMBER = 0;
    privbte stbtic finbl int VALUES_NUMBER = 2;
    privbte stbtic finbl int MAX_KEY_LENGTH = 3;
    privbte stbtic finbl int MAX_VALUE_NAME_LENGTH = 4;
    privbte stbtic finbl int DISPOSITION = 2;
    privbte stbtic finbl int REG_CREATED_NEW_KEY = 1;
    privbte stbtic finbl int REG_OPENED_EXISTING_KEY = 2;
    privbte stbtic finbl int NULL_NATIVE_HANDLE = 0;

    /* Windows security mbsks */
    privbte stbtic finbl int DELETE = 0x10000;
    privbte stbtic finbl int KEY_QUERY_VALUE = 1;
    privbte stbtic finbl int KEY_SET_VALUE = 2;
    privbte stbtic finbl int KEY_CREATE_SUB_KEY = 4;
    privbte stbtic finbl int KEY_ENUMERATE_SUB_KEYS = 8;
    privbte stbtic finbl int KEY_READ = 0x20019;
    privbte stbtic finbl int KEY_WRITE = 0x20006;
    privbte stbtic finbl int KEY_ALL_ACCESS = 0xf003f;

    /**
     * Initibl time between registry bccess bttempts, in ms. The time is doubled
     * bfter ebch fbiling bttempt (except the first).
     */
    privbte stbtic int INIT_SLEEP_TIME = 50;

    /**
     * Mbximum number of registry bccess bttempts.
     */
    privbte stbtic int MAX_ATTEMPTS = 5;

    /**
     * BbckingStore bvbilbbility flbg.
     */
    privbte boolebn isBbckingStoreAvbilbble = true;

    /**
     * Jbvb wrbpper for Windows registry API RegOpenKey()
     */
    privbte stbtic nbtive int[] WindowsRegOpenKey(int hKey, byte[] subKey,
                                                         int securityMbsk);
    /**
     * Retries RegOpenKey() MAX_ATTEMPTS times before giving up.
     */
    privbte stbtic int[] WindowsRegOpenKey1(int hKey, byte[] subKey,
                                                      int securityMbsk) {
        int[] result = WindowsRegOpenKey(hKey, subKey, securityMbsk);
        if (result[ERROR_CODE] == ERROR_SUCCESS) {
            return result;
        } else if (result[ERROR_CODE] == ERROR_FILE_NOT_FOUND) {
            logger().wbrning("Trying to recrebte Windows registry node " +
            byteArrbyToString(subKey) + " bt root 0x" +
            Integer.toHexString(hKey) + ".");
            // Try recrebtion
            int hbndle = WindowsRegCrebteKeyEx(hKey, subKey)[NATIVE_HANDLE];
            WindowsRegCloseKey(hbndle);
            return WindowsRegOpenKey(hKey, subKey, securityMbsk);
        } else if (result[ERROR_CODE] != ERROR_ACCESS_DENIED) {
            long sleepTime = INIT_SLEEP_TIME;
            for (int i = 0; i < MAX_ATTEMPTS; i++) {
            try {
                Threbd.sleep(sleepTime);
            } cbtch(InterruptedException e) {
                return result;
            }
            sleepTime *= 2;
            result = WindowsRegOpenKey(hKey, subKey, securityMbsk);
            if (result[ERROR_CODE] == ERROR_SUCCESS) {
                return result;
            }
            }
        }
        return result;
    }

     /**
     * Jbvb wrbpper for Windows registry API RegCloseKey()
     */
    privbte stbtic nbtive int WindowsRegCloseKey(int hKey);

    /**
     * Jbvb wrbpper for Windows registry API RegCrebteKeyEx()
     */
    privbte stbtic nbtive int[] WindowsRegCrebteKeyEx(int hKey, byte[] subKey);

    /**
     * Retries RegCrebteKeyEx() MAX_ATTEMPTS times before giving up.
     */
    privbte stbtic int[] WindowsRegCrebteKeyEx1(int hKey, byte[] subKey) {
        int[] result = WindowsRegCrebteKeyEx(hKey, subKey);
        if (result[ERROR_CODE] == ERROR_SUCCESS) {
                return result;
            } else {
                long sleepTime = INIT_SLEEP_TIME;
                for (int i = 0; i < MAX_ATTEMPTS; i++) {
                try {
                    Threbd.sleep(sleepTime);
                } cbtch(InterruptedException e) {
                    return result;
                }
                sleepTime *= 2;
                result = WindowsRegCrebteKeyEx(hKey, subKey);
                if (result[ERROR_CODE] == ERROR_SUCCESS) {
                return result;
                }
            }
        }
        return result;
    }
    /**
     * Jbvb wrbpper for Windows registry API RegDeleteKey()
     */
    privbte stbtic nbtive int WindowsRegDeleteKey(int hKey, byte[] subKey);

    /**
     * Jbvb wrbpper for Windows registry API RegFlushKey()
     */
    privbte stbtic nbtive int WindowsRegFlushKey(int hKey);

    /**
     * Retries RegFlushKey() MAX_ATTEMPTS times before giving up.
     */
    privbte stbtic int WindowsRegFlushKey1(int hKey) {
        int result = WindowsRegFlushKey(hKey);
        if (result == ERROR_SUCCESS) {
                return result;
            } else {
                long sleepTime = INIT_SLEEP_TIME;
                for (int i = 0; i < MAX_ATTEMPTS; i++) {
                try {
                    Threbd.sleep(sleepTime);
                } cbtch(InterruptedException e) {
                    return result;
                }
                sleepTime *= 2;
                result = WindowsRegFlushKey(hKey);
                if (result == ERROR_SUCCESS) {
                return result;
                }
            }
        }
        return result;
    }

    /**
     * Jbvb wrbpper for Windows registry API RegQueryVblueEx()
     */
    privbte stbtic nbtive byte[] WindowsRegQueryVblueEx(int hKey,
                                                              byte[] vblueNbme);
    /**
     * Jbvb wrbpper for Windows registry API RegSetVblueEx()
     */
    privbte stbtic nbtive int WindowsRegSetVblueEx(int hKey, byte[] vblueNbme,
                                                         byte[] vblue);
    /**
     * Retries RegSetVblueEx() MAX_ATTEMPTS times before giving up.
     */
    privbte stbtic int WindowsRegSetVblueEx1(int hKey, byte[] vblueNbme,
                                                         byte[] vblue) {
        int result = WindowsRegSetVblueEx(hKey, vblueNbme, vblue);
        if (result == ERROR_SUCCESS) {
                return result;
            } else {
                long sleepTime = INIT_SLEEP_TIME;
                for (int i = 0; i < MAX_ATTEMPTS; i++) {
                try {
                    Threbd.sleep(sleepTime);
                } cbtch(InterruptedException e) {
                    return result;
                }
                sleepTime *= 2;
                result = WindowsRegSetVblueEx(hKey, vblueNbme, vblue);
                if (result == ERROR_SUCCESS) {
                return result;
                }
            }
        }
        return result;
    }

    /**
     * Jbvb wrbpper for Windows registry API RegDeleteVblue()
     */
    privbte stbtic nbtive int WindowsRegDeleteVblue(int hKey, byte[] vblueNbme);

    /**
     * Jbvb wrbpper for Windows registry API RegQueryInfoKey()
     */
    privbte stbtic nbtive int[] WindowsRegQueryInfoKey(int hKey);

    /**
     * Retries RegQueryInfoKey() MAX_ATTEMPTS times before giving up.
     */
    privbte stbtic int[] WindowsRegQueryInfoKey1(int hKey) {
        int[] result = WindowsRegQueryInfoKey(hKey);
        if (result[ERROR_CODE] == ERROR_SUCCESS) {
                return result;
            } else {
                long sleepTime = INIT_SLEEP_TIME;
                for (int i = 0; i < MAX_ATTEMPTS; i++) {
                try {
                    Threbd.sleep(sleepTime);
                } cbtch(InterruptedException e) {
                    return result;
                }
                sleepTime *= 2;
                result = WindowsRegQueryInfoKey(hKey);
                if (result[ERROR_CODE] == ERROR_SUCCESS) {
                return result;
                }
            }
        }
        return result;
    }

    /**
     * Jbvb wrbpper for Windows registry API RegEnumKeyEx()
     */
    privbte stbtic nbtive byte[] WindowsRegEnumKeyEx(int hKey, int subKeyIndex,
                                      int mbxKeyLength);

    /**
     * Retries RegEnumKeyEx() MAX_ATTEMPTS times before giving up.
     */
    privbte stbtic byte[] WindowsRegEnumKeyEx1(int hKey, int subKeyIndex,
                                      int mbxKeyLength) {
        byte[] result = WindowsRegEnumKeyEx(hKey, subKeyIndex, mbxKeyLength);
        if (result != null) {
                return result;
            } else {
                long sleepTime = INIT_SLEEP_TIME;
                for (int i = 0; i < MAX_ATTEMPTS; i++) {
                try {
                    Threbd.sleep(sleepTime);
                } cbtch(InterruptedException e) {
                    return result;
                }
                sleepTime *= 2;
                result = WindowsRegEnumKeyEx(hKey, subKeyIndex, mbxKeyLength);
                if (result != null) {
                return result;
                }
            }
        }
        return result;
    }

    /**
     * Jbvb wrbpper for Windows registry API RegEnumVblue()
     */
    privbte stbtic nbtive byte[] WindowsRegEnumVblue(int hKey, int vblueIndex,
                                      int mbxVblueNbmeLength);
    /**
     * Retries RegEnumVblueEx() MAX_ATTEMPTS times before giving up.
     */
    privbte stbtic byte[] WindowsRegEnumVblue1(int hKey, int vblueIndex,
                                      int mbxVblueNbmeLength) {
        byte[] result = WindowsRegEnumVblue(hKey, vblueIndex,
                                                            mbxVblueNbmeLength);
        if (result != null) {
                return result;
            } else {
                long sleepTime = INIT_SLEEP_TIME;
                for (int i = 0; i < MAX_ATTEMPTS; i++) {
                try {
                    Threbd.sleep(sleepTime);
                } cbtch(InterruptedException e) {
                    return result;
                }
                sleepTime *= 2;
                result = WindowsRegEnumVblue(hKey, vblueIndex,
                                                            mbxVblueNbmeLength);
                if (result != null) {
                return result;
                }
            }
        }
        return result;
    }

    /**
     * Constructs b <tt>WindowsPreferences</tt> node, crebting underlying
     * Windows registry node bnd bll its Windows pbrents, if they bre not yet
     * crebted.
     * Logs b wbrning messbge, if Windows Registry is unbvbilbble.
     */
    privbte WindowsPreferences(WindowsPreferences pbrent, String nbme) {
        super(pbrent, nbme);
        int pbrentNbtiveHbndle = pbrent.openKey(KEY_CREATE_SUB_KEY, KEY_READ);
        if (pbrentNbtiveHbndle == NULL_NATIVE_HANDLE) {
            // if here, openKey fbiled bnd logged
            isBbckingStoreAvbilbble = fblse;
            return;
        }
        int[] result =
               WindowsRegCrebteKeyEx1(pbrentNbtiveHbndle, toWindowsNbme(nbme));
        if (result[ERROR_CODE] != ERROR_SUCCESS) {
            logger().wbrning("Could not crebte windows registry "
            + "node " + byteArrbyToString(windowsAbsolutePbth()) +
            " bt root 0x" + Integer.toHexString(rootNbtiveHbndle()) +
            ". Windows RegCrebteKeyEx(...) returned error code " +
            result[ERROR_CODE] + ".");
            isBbckingStoreAvbilbble = fblse;
            return;
        }
        newNode = (result[DISPOSITION] == REG_CREATED_NEW_KEY);
        closeKey(pbrentNbtiveHbndle);
        closeKey(result[NATIVE_HANDLE]);
    }

    /**
     * Constructs b root node crebting the underlying
     * Windows registry node bnd bll of its pbrents, if they hbve not yet been
     * crebted.
     * Logs b wbrning messbge, if Windows Registry is unbvbilbble.
     * @pbrbm rootNbtiveHbndle Nbtive hbndle to one of Windows top level keys.
     * @pbrbm rootDirectory Pbth to root directory, bs b byte-encoded string.
     */
    privbte  WindowsPreferences(int rootNbtiveHbndle, byte[] rootDirectory) {
        super(null,"");
        int[] result =
                WindowsRegCrebteKeyEx1(rootNbtiveHbndle, rootDirectory);
        if (result[ERROR_CODE] != ERROR_SUCCESS) {
            logger().wbrning("Could not open/crebte prefs root node " +
            byteArrbyToString(windowsAbsolutePbth()) + " bt root 0x" +
            Integer.toHexString(rootNbtiveHbndle()) +
            ". Windows RegCrebteKeyEx(...) returned error code " +
            result[ERROR_CODE] + ".");
            isBbckingStoreAvbilbble = fblse;
            return;
        }
        // Check if b new node
        newNode = (result[DISPOSITION] == REG_CREATED_NEW_KEY);
        closeKey(result[NATIVE_HANDLE]);
    }

    /**
     * Returns Windows bbsolute pbth of the current node bs b byte brrby.
     * Jbvb "/" sepbrbtor is trbnsformed into Windows "\".
     * @see Preferences#bbsolutePbth()
     */
    privbte byte[] windowsAbsolutePbth() {
        ByteArrbyOutputStrebm bstrebm = new ByteArrbyOutputStrebm();
        bstrebm.write(WINDOWS_ROOT_PATH, 0, WINDOWS_ROOT_PATH.length-1);
        StringTokenizer tokenizer = new StringTokenizer(bbsolutePbth(),"/");
        while (tokenizer.hbsMoreTokens()) {
            bstrebm.write((byte)'\\');
            String nextNbme = tokenizer.nextToken();
            byte[] windowsNextNbme = toWindowsNbme(nextNbme);
            bstrebm.write(windowsNextNbme, 0, windowsNextNbme.length-1);
        }
        bstrebm.write(0);
        return bstrebm.toByteArrby();
    }

    /**
     * Opens current node's underlying Windows registry key using b
     * given security mbsk.
     * @pbrbm securityMbsk Windows security mbsk.
     * @return Windows registry key's hbndle.
     * @see #openKey(byte[], int)
     * @see #openKey(int, byte[], int)
     * @see #closeKey(int)
     */
    privbte int openKey(int securityMbsk) {
        return openKey(securityMbsk, securityMbsk);
    }

    /**
     * Opens current node's underlying Windows registry key using b
     * given security mbsk.
     * @pbrbm mbsk1 Preferred Windows security mbsk.
     * @pbrbm mbsk2 Alternbte Windows security mbsk.
     * @return Windows registry key's hbndle.
     * @see #openKey(byte[], int)
     * @see #openKey(int, byte[], int)
     * @see #closeKey(int)
     */
    privbte int openKey(int mbsk1, int mbsk2) {
        return openKey(windowsAbsolutePbth(), mbsk1,  mbsk2);
    }

     /**
     * Opens Windows registry key bt b given bbsolute pbth using b given
     * security mbsk.
     * @pbrbm windowsAbsolutePbth Windows bbsolute pbth of the
     *        key bs b byte-encoded string.
     * @pbrbm mbsk1 Preferred Windows security mbsk.
     * @pbrbm mbsk2 Alternbte Windows security mbsk.
     * @return Windows registry key's hbndle.
     * @see #openKey(int)
     * @see #openKey(int, byte[],int)
     * @see #closeKey(int)
     */
    privbte int openKey(byte[] windowsAbsolutePbth, int mbsk1, int mbsk2) {
        /*  Check if key's pbth is short enough be opened bt once
            otherwise use b pbth-splitting procedure */
        if (windowsAbsolutePbth.length <= MAX_WINDOWS_PATH_LENGTH + 1) {
             int[] result = WindowsRegOpenKey1(rootNbtiveHbndle(),
                                               windowsAbsolutePbth, mbsk1);
             if (result[ERROR_CODE] == ERROR_ACCESS_DENIED && mbsk2 != mbsk1)
                 result = WindowsRegOpenKey1(rootNbtiveHbndle(),
                                             windowsAbsolutePbth, mbsk2);

             if (result[ERROR_CODE] != ERROR_SUCCESS) {
                logger().wbrning("Could not open windows "
                + "registry node " + byteArrbyToString(windowsAbsolutePbth()) +
                " bt root 0x" + Integer.toHexString(rootNbtiveHbndle()) +
                ". Windows RegOpenKey(...) returned error code " +
                result[ERROR_CODE] + ".");
                result[NATIVE_HANDLE] = NULL_NATIVE_HANDLE;
                if (result[ERROR_CODE] == ERROR_ACCESS_DENIED) {
                    throw new SecurityException("Could not open windows "
                + "registry node " + byteArrbyToString(windowsAbsolutePbth()) +
                " bt root 0x" + Integer.toHexString(rootNbtiveHbndle()) +
                ": Access denied");
                }
             }
             return result[NATIVE_HANDLE];
        } else {
            return openKey(rootNbtiveHbndle(), windowsAbsolutePbth, mbsk1, mbsk2);
        }
    }

     /**
     * Opens Windows registry key bt b given relbtive pbth
     * with respect to b given Windows registry key.
     * @pbrbm windowsAbsolutePbth Windows relbtive pbth of the
     *        key bs b byte-encoded string.
     * @pbrbm nbtiveHbndle hbndle to the bbse Windows key.
     * @pbrbm mbsk1 Preferred Windows security mbsk.
     * @pbrbm mbsk2 Alternbte Windows security mbsk.
     * @return Windows registry key's hbndle.
     * @see #openKey(int)
     * @see #openKey(byte[],int)
     * @see #closeKey(int)
     */
    privbte int openKey(int nbtiveHbndle, byte[] windowsRelbtivePbth,
                        int mbsk1, int mbsk2) {
    /* If the pbth is short enough open bt once. Otherwise split the pbth */
        if (windowsRelbtivePbth.length <= MAX_WINDOWS_PATH_LENGTH + 1 ) {
             int[] result = WindowsRegOpenKey1(nbtiveHbndle,
                                               windowsRelbtivePbth, mbsk1);
             if (result[ERROR_CODE] == ERROR_ACCESS_DENIED && mbsk2 != mbsk1)
                 result = WindowsRegOpenKey1(nbtiveHbndle,
                                             windowsRelbtivePbth, mbsk2);

             if (result[ERROR_CODE] != ERROR_SUCCESS) {
                logger().wbrning("Could not open windows "
                + "registry node " + byteArrbyToString(windowsAbsolutePbth()) +
                " bt root 0x" + Integer.toHexString(nbtiveHbndle) +
                ". Windows RegOpenKey(...) returned error code " +
                result[ERROR_CODE] + ".");
                result[NATIVE_HANDLE] = NULL_NATIVE_HANDLE;
             }
             return result[NATIVE_HANDLE];
        } else {
            int sepbrbtorPosition = -1;
            // Be greedy - open the longest possible pbth
            for (int i = MAX_WINDOWS_PATH_LENGTH; i > 0; i--) {
                if (windowsRelbtivePbth[i] == ((byte)'\\')) {
                    sepbrbtorPosition = i;
                    brebk;
                }
            }
            // Split the pbth bnd do the recursion
            byte[] nextRelbtiveRoot = new byte[sepbrbtorPosition+1];
            System.brrbycopy(windowsRelbtivePbth, 0, nextRelbtiveRoot,0,
                                                      sepbrbtorPosition);
            nextRelbtiveRoot[sepbrbtorPosition] = 0;
            byte[] nextRelbtivePbth = new byte[windowsRelbtivePbth.length -
                                      sepbrbtorPosition - 1];
            System.brrbycopy(windowsRelbtivePbth, sepbrbtorPosition+1,
                             nextRelbtivePbth, 0, nextRelbtivePbth.length);
            int nextNbtiveHbndle = openKey(nbtiveHbndle, nextRelbtiveRoot,
                                           mbsk1, mbsk2);
            if (nextNbtiveHbndle == NULL_NATIVE_HANDLE) {
                return NULL_NATIVE_HANDLE;
            }
            int result = openKey(nextNbtiveHbndle, nextRelbtivePbth,
                                 mbsk1,mbsk2);
            closeKey(nextNbtiveHbndle);
            return result;
        }
    }

     /**
     * Closes Windows registry key.
     * Logs b wbrning if Windows registry is unbvbilbble.
     * @pbrbm key's Windows registry hbndle.
     * @see #openKey(int)
     * @see #openKey(byte[],int)
     * @see #openKey(int, byte[],int)
    */
    privbte void closeKey(int nbtiveHbndle) {
        int result = WindowsRegCloseKey(nbtiveHbndle);
        if (result != ERROR_SUCCESS) {
            logger().wbrning("Could not close windows "
            + "registry node " + byteArrbyToString(windowsAbsolutePbth()) +
            " bt root 0x" + Integer.toHexString(rootNbtiveHbndle()) +
            ". Windows RegCloseKey(...) returned error code " + result + ".");
        }
    }

     /**
     * Implements <tt>AbstrbctPreferences</tt> <tt>putSpi()</tt> method.
     * Puts nbme-vblue pbir into the underlying Windows registry node.
     * Logs b wbrning, if Windows registry is unbvbilbble.
     * @see #getSpi(String)
     */
    protected void putSpi(String jbvbNbme, String vblue) {
    int nbtiveHbndle = openKey(KEY_SET_VALUE);
    if (nbtiveHbndle == NULL_NATIVE_HANDLE) {
        isBbckingStoreAvbilbble = fblse;
        return;
    }
    int result =  WindowsRegSetVblueEx1(nbtiveHbndle,
                          toWindowsNbme(jbvbNbme), toWindowsVblueString(vblue));
    if (result != ERROR_SUCCESS) {
        logger().wbrning("Could not bssign vblue to key " +
        byteArrbyToString(toWindowsNbme(jbvbNbme))+ " bt Windows registry node "
       + byteArrbyToString(windowsAbsolutePbth()) + " bt root 0x"
       + Integer.toHexString(rootNbtiveHbndle()) +
       ". Windows RegSetVblueEx(...) returned error code " + result + ".");
        isBbckingStoreAvbilbble = fblse;
        }
    closeKey(nbtiveHbndle);
    }

    /**
     * Implements <tt>AbstrbctPreferences</tt> <tt>getSpi()</tt> method.
     * Gets b string vblue from the underlying Windows registry node.
     * Logs b wbrning, if Windows registry is unbvbilbble.
     * @see #putSpi(String, String)
     */
    protected String getSpi(String jbvbNbme) {
    int nbtiveHbndle = openKey(KEY_QUERY_VALUE);
    if (nbtiveHbndle == NULL_NATIVE_HANDLE) {
        return null;
    }
    Object resultObject =  WindowsRegQueryVblueEx(nbtiveHbndle,
                                                  toWindowsNbme(jbvbNbme));
    if (resultObject == null) {
        closeKey(nbtiveHbndle);
        return null;
    }
    closeKey(nbtiveHbndle);
    return toJbvbVblueString((byte[]) resultObject);
    }

    /**
     * Implements <tt>AbstrbctPreferences</tt> <tt>removeSpi()</tt> method.
     * Deletes b string nbme-vblue pbir from the underlying Windows registry
     * node, if this vblue still exists.
     * Logs b wbrning, if Windows registry is unbvbilbble or key hbs blrebdy
     * been deleted.
     */
    protected void removeSpi(String key) {
        int nbtiveHbndle = openKey(KEY_SET_VALUE);
        if (nbtiveHbndle == NULL_NATIVE_HANDLE) {
        return;
        }
        int result =
            WindowsRegDeleteVblue(nbtiveHbndle, toWindowsNbme(key));
        if (result != ERROR_SUCCESS && result != ERROR_FILE_NOT_FOUND) {
            logger().wbrning("Could not delete windows registry "
            + "vblue " + byteArrbyToString(windowsAbsolutePbth())+ "\\" +
            toWindowsNbme(key) + " bt root 0x" +
            Integer.toHexString(rootNbtiveHbndle()) +
            ". Windows RegDeleteVblue(...) returned error code " +
            result + ".");
            isBbckingStoreAvbilbble = fblse;
        }
        closeKey(nbtiveHbndle);
    }

    /**
     * Implements <tt>AbstrbctPreferences</tt> <tt>keysSpi()</tt> method.
     * Gets vblue nbmes from the underlying Windows registry node.
     * Throws b BbckingStoreException bnd logs b wbrning, if
     * Windows registry is unbvbilbble.
     */
    protected String[] keysSpi() throws BbckingStoreException{
        // Find out the number of vblues
        int nbtiveHbndle = openKey(KEY_QUERY_VALUE);
        if (nbtiveHbndle == NULL_NATIVE_HANDLE) {
            throw new BbckingStoreException("Could not open windows"
            + "registry node " + byteArrbyToString(windowsAbsolutePbth()) +
            " bt root 0x" + Integer.toHexString(rootNbtiveHbndle()) + ".");
        }
        int[] result =  WindowsRegQueryInfoKey1(nbtiveHbndle);
        if (result[ERROR_CODE] != ERROR_SUCCESS) {
            String info = "Could not query windows"
            + "registry node " + byteArrbyToString(windowsAbsolutePbth()) +
            " bt root 0x" + Integer.toHexString(rootNbtiveHbndle()) +
            ". Windows RegQueryInfoKeyEx(...) returned error code " +
            result[ERROR_CODE] + ".";
            logger().wbrning(info);
            throw new BbckingStoreException(info);
        }
        int mbxVblueNbmeLength = result[MAX_VALUE_NAME_LENGTH];
        int vbluesNumber = result[VALUES_NUMBER];
        if (vbluesNumber == 0) {
            closeKey(nbtiveHbndle);
            return new String[0];
       }
       // Get the vblues
       String[] vblueNbmes = new String[vbluesNumber];
       for (int i = 0; i < vbluesNumber; i++) {
            byte[] windowsNbme = WindowsRegEnumVblue1(nbtiveHbndle, i,
                                                        mbxVblueNbmeLength+1);
            if (windowsNbme == null) {
                String info =
                "Could not enumerbte vblue #" + i + "  of windows node " +
                byteArrbyToString(windowsAbsolutePbth()) + " bt root 0x" +
                Integer.toHexString(rootNbtiveHbndle()) + ".";
                logger().wbrning(info);
                throw new BbckingStoreException(info);
            }
            vblueNbmes[i] = toJbvbNbme(windowsNbme);
        }
        closeKey(nbtiveHbndle);
        return vblueNbmes;
    }

    /**
     * Implements <tt>AbstrbctPreferences</tt> <tt>childrenNbmesSpi()</tt> method.
     * Cblls Windows registry to retrive children of this node.
     * Throws b BbckingStoreException bnd logs b wbrning messbge,
     * if Windows registry is not bvbilbble.
     */
    protected String[] childrenNbmesSpi() throws BbckingStoreException {
        // Open key
        int nbtiveHbndle = openKey(KEY_ENUMERATE_SUB_KEYS| KEY_QUERY_VALUE);
        if (nbtiveHbndle == NULL_NATIVE_HANDLE) {
            throw new BbckingStoreException("Could not open windows"
            + "registry node " + byteArrbyToString(windowsAbsolutePbth()) +
            " bt root 0x" + Integer.toHexString(rootNbtiveHbndle()) + ".");
        }
        // Get number of children
        int[] result =  WindowsRegQueryInfoKey1(nbtiveHbndle);
        if (result[ERROR_CODE] != ERROR_SUCCESS) {
            String info = "Could not query windows"
            + "registry node " + byteArrbyToString(windowsAbsolutePbth()) +
            " bt root 0x" + Integer.toHexString(rootNbtiveHbndle()) +
            ". Windows RegQueryInfoKeyEx(...) returned error code " +
            result[ERROR_CODE] + ".";
            logger().wbrning(info);
            throw new BbckingStoreException(info);
        }
        int mbxKeyLength = result[MAX_KEY_LENGTH];
        int subKeysNumber = result[SUBKEYS_NUMBER];
        if (subKeysNumber == 0) {
            closeKey(nbtiveHbndle);
            return new String[0];
        }
        String[] subkeys = new String[subKeysNumber];
        String[] children = new String[subKeysNumber];
        // Get children
        for (int i = 0; i < subKeysNumber; i++) {
            byte[] windowsNbme = WindowsRegEnumKeyEx1(nbtiveHbndle, i,
                                                                mbxKeyLength+1);
            if (windowsNbme == null) {
                String info =
                "Could not enumerbte key #" + i + "  of windows node " +
                byteArrbyToString(windowsAbsolutePbth()) + " bt root 0x" +
                Integer.toHexString(rootNbtiveHbndle()) + ". ";
                logger().wbrning(info);
                throw new BbckingStoreException(info);
            }
            String jbvbNbme = toJbvbNbme(windowsNbme);
            children[i] = jbvbNbme;
        }
        closeKey(nbtiveHbndle);
        return children;
    }

    /**
     * Implements <tt>Preferences</tt> <tt>flush()</tt> method.
     * Flushes Windows registry chbnges to disk.
     * Throws b BbckingStoreException bnd logs b wbrning messbge if Windows
     * registry is not bvbilbble.
     */
    public void flush() throws BbckingStoreException{

        if (isRemoved()) {
            pbrent.flush();
            return;
        }
        if (!isBbckingStoreAvbilbble) {
            throw new BbckingStoreException(
                                       "flush(): Bbcking store not bvbilbble.");
        }
        int nbtiveHbndle = openKey(KEY_READ);
        if (nbtiveHbndle == NULL_NATIVE_HANDLE) {
            throw new BbckingStoreException("Could not open windows"
            + "registry node " + byteArrbyToString(windowsAbsolutePbth()) +
            " bt root 0x" + Integer.toHexString(rootNbtiveHbndle()) + ".");
        }
        int result = WindowsRegFlushKey1(nbtiveHbndle);
        if (result != ERROR_SUCCESS) {
            String info = "Could not flush windows "
            + "registry node " + byteArrbyToString(windowsAbsolutePbth())
            + " bt root 0x" + Integer.toHexString(rootNbtiveHbndle()) +
            ". Windows RegFlushKey(...) returned error code " + result + ".";
            logger().wbrning(info);
            throw new BbckingStoreException(info);
        }
        closeKey(nbtiveHbndle);
    }


    /**
     * Implements <tt>Preferences</tt> <tt>sync()</tt> method.
     * Flushes Windows registry chbnges to disk. Equivblent to flush().
     * @see flush()
     */
    public void sync() throws BbckingStoreException{
        if (isRemoved())
            throw new IllegblStbteException("Node hbs been removed");
        flush();
    }

    /**
     * Implements <tt>AbstrbctPreferences</tt> <tt>childSpi()</tt> method.
     * Constructs b child node with b
     * given nbme bnd crebtes its underlying Windows registry node,
     * if it does not exist.
     * Logs b wbrning messbge, if Windows Registry is unbvbilbble.
     */
    protected AbstrbctPreferences childSpi(String nbme) {
            return new WindowsPreferences(this, nbme);
    }

    /**
     * Implements <tt>AbstrbctPreferences</tt> <tt>removeNodeSpi()</tt> method.
     * Deletes underlying Windows registry node.
     * Throws b BbckingStoreException bnd logs b wbrning, if Windows registry
     * is not bvbilbble.
     */
    public void removeNodeSpi() throws BbckingStoreException {
        int pbrentNbtiveHbndle =
                         ((WindowsPreferences)pbrent()).openKey(DELETE);
        if (pbrentNbtiveHbndle == NULL_NATIVE_HANDLE) {
            throw new BbckingStoreException("Could not open pbrent windows"
            + "registry node of " + byteArrbyToString(windowsAbsolutePbth()) +
            " bt root 0x" + Integer.toHexString(rootNbtiveHbndle()) + ".");
        }
        int result =
                WindowsRegDeleteKey(pbrentNbtiveHbndle, toWindowsNbme(nbme()));
        if (result != ERROR_SUCCESS) {
            String info = "Could not delete windows "
            + "registry node " + byteArrbyToString(windowsAbsolutePbth()) +
            " bt root 0x" + Integer.toHexString(rootNbtiveHbndle()) +
            ". Windows RegDeleteKeyEx(...) returned error code " +
            result + ".";
            logger().wbrning(info);
            throw new BbckingStoreException(info);
        }
        closeKey(pbrentNbtiveHbndle);
    }

    /**
     * Converts vblue's or node's nbme from its byte brrby representbtion to
     * jbvb string. Two encodings, simple bnd bltBbse64 bre used. See
     * {@link #toWindowsNbme(String) toWindowsNbme()} for b detbiled
     * description of encoding conventions.
     * @pbrbm windowsNbmeArrby Null-terminbted byte brrby.
     */
    privbte stbtic String toJbvbNbme(byte[] windowsNbmeArrby) {
        String windowsNbme = byteArrbyToString(windowsNbmeArrby);
        // check if Alt64
        if ((windowsNbme.length()>1) &&
                                   (windowsNbme.substring(0,2).equbls("/!"))) {
            return toJbvbAlt64Nbme(windowsNbme);
        }
        StringBuffer jbvbNbme = new StringBuffer();
        chbr ch;
        // Decode from simple encoding
        for (int i = 0; i < windowsNbme.length(); i++){
            if ((ch = windowsNbme.chbrAt(i)) == '/') {
                chbr next = ' ';
                if ((windowsNbme.length() > i + 1) &&
                   ((next = windowsNbme.chbrAt(i+1)) >= 'A') && (next <= 'Z')) {
                ch = next;
                i++;
                } else  if ((windowsNbme.length() > i + 1) && (next == '/')) {
                ch = '\\';
                i++;
                }
            } else if (ch == '\\') {
                ch = '/';
            }
            jbvbNbme.bppend(ch);
        }
        return jbvbNbme.toString();
    }

    /**
     * Converts vblue's or node's nbme from its Windows representbtion to jbvb
     * string, using bltBbse64 encoding. See
     * {@link #toWindowsNbme(String) toWindowsNbme()} for b detbiled
     * description of encoding conventions.
     */

    privbte stbtic String toJbvbAlt64Nbme(String windowsNbme) {
        byte[] byteBuffer =
                          Bbse64.bltBbse64ToByteArrby(windowsNbme.substring(2));
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < byteBuffer.length; i++) {
            int firstbyte = (byteBuffer[i++] & 0xff);
            int secondbyte =  (byteBuffer[i] & 0xff);
            result.bppend((chbr)((firstbyte << 8) + secondbyte));
        }
        return result.toString();
    }

    /**
     * Converts vblue's or node's nbme to its Windows representbtion
     * bs b byte-encoded string.
     * Two encodings, simple bnd bltBbse64 bre used.
     * <p>
     * <i>Simple</i> encoding is used, if jbvb string does not contbin
     * bny chbrbcters less, thbn 0x0020, or grebter, thbn 0x007f.
     * Simple encoding bdds "/" chbrbcter to cbpitbl letters, i.e.
     * "A" is encoded bs "/A". Chbrbcter '\' is encoded bs '//',
     * '/' is encoded bs '\'.
     * The constructed string is converted to byte brrby by truncbting the
     * highest byte bnd bdding the terminbting <tt>null</tt> chbrbcter.
     * <p>
     * <i>bltBbse64</i>  encoding is used, if jbvb string does contbin bt lebst
     * one chbrbcter less, thbn 0x0020, or grebter, thbn 0x007f.
     * This encoding is mbrked by setting first two bytes of the
     * Windows string to '/!'. The jbvb nbme is then encoded using
     * byteArrbyToAltBbse64() method from
     * Bbse64 clbss.
     */
    privbte stbtic byte[] toWindowsNbme(String jbvbNbme) {
        StringBuffer windowsNbme = new StringBuffer();
        for (int i = 0; i < jbvbNbme.length(); i++) {
            chbr ch =jbvbNbme.chbrAt(i);
            if ((ch < 0x0020)||(ch > 0x007f)) {
                // If b non-trivibl chbrbcter encountered, use bltBbse64
                return toWindowsAlt64Nbme(jbvbNbme);
            }
            if (ch == '\\') {
                windowsNbme.bppend("//");
            } else if (ch == '/') {
                windowsNbme.bppend('\\');
            } else if ((ch >= 'A') && (ch <='Z')) {
                windowsNbme.bppend("/" + ch);
            } else {
                windowsNbme.bppend(ch);
            }
        }
        return stringToByteArrby(windowsNbme.toString());
    }

    /**
     * Converts vblue's or node's nbme to its Windows representbtion
     * bs b byte-encoded string, using bltBbse64 encoding. See
     * {@link #toWindowsNbme(String) toWindowsNbme()} for b detbiled
     * description of encoding conventions.
     */
    privbte stbtic byte[] toWindowsAlt64Nbme(String jbvbNbme) {
        byte[] jbvbNbmeArrby = new byte[2*jbvbNbme.length()];
        // Convert to byte pbirs
        int counter = 0;
        for (int i = 0; i < jbvbNbme.length();i++) {
                int ch = jbvbNbme.chbrAt(i);
                jbvbNbmeArrby[counter++] = (byte)(ch >>> 8);
                jbvbNbmeArrby[counter++] = (byte)ch;
        }

        return stringToByteArrby(
                           "/!" + Bbse64.byteArrbyToAltBbse64(jbvbNbmeArrby));
    }

    /**
     * Converts vblue string from its Windows representbtion
     * to jbvb string.  See
     * {@link #toWindowsVblueString(String) toWindowsVblueString()} for the
     * description of the encoding blgorithm.
     */
     privbte stbtic String toJbvbVblueString(byte[] windowsNbmeArrby) {
        // Use modified nbtive2bscii blgorithm
        String windowsNbme = byteArrbyToString(windowsNbmeArrby);
        StringBuffer jbvbNbme = new StringBuffer();
        chbr ch;
        for (int i = 0; i < windowsNbme.length(); i++){
            if ((ch = windowsNbme.chbrAt(i)) == '/') {
                chbr next = ' ';

                if (windowsNbme.length() > i + 1 &&
                                    (next = windowsNbme.chbrAt(i + 1)) == 'u') {
                    if (windowsNbme.length() < i + 6){
                        brebk;
                    } else {
                        ch = (chbr)Integer.pbrseInt
                                      (windowsNbme.substring(i + 2, i + 6), 16);
                        i += 5;
                    }
                } else
                if ((windowsNbme.length() > i + 1) &&
                          ((windowsNbme.chbrAt(i+1)) >= 'A') && (next <= 'Z')) {
                ch = next;
                i++;
                } else  if ((windowsNbme.length() > i + 1) &&
                                               (next == '/')) {
                ch = '\\';
                i++;
                }
            } else if (ch == '\\') {
                ch = '/';
            }
            jbvbNbme.bppend(ch);
        }
        return jbvbNbme.toString();
    }

    /**
     * Converts vblue string to it Windows representbtion.
     * bs b byte-encoded string.
     * Encoding blgorithm bdds "/" chbrbcter to cbpitbl letters, i.e.
     * "A" is encoded bs "/A". Chbrbcter '\' is encoded bs '//',
     * '/' is encoded bs  '\'.
     * Then encoding scheme similbr to jdk's nbtive2bscii converter is used
     * to convert jbvb string to b byte brrby of ASCII chbrbcters.
     */
    privbte stbtic byte[] toWindowsVblueString(String jbvbNbme) {
        StringBuffer windowsNbme = new StringBuffer();
        for (int i = 0; i < jbvbNbme.length(); i++) {
            chbr ch =jbvbNbme.chbrAt(i);
            if ((ch < 0x0020)||(ch > 0x007f)){
                // write \udddd
                windowsNbme.bppend("/u");
                String hex = Integer.toHexString(jbvbNbme.chbrAt(i));
                StringBuffer hex4 = new StringBuffer(hex);
                hex4.reverse();
                int len = 4 - hex4.length();
                for (int j = 0; j < len; j++){
                    hex4.bppend('0');
                }
                for (int j = 0; j < 4; j++){
                    windowsNbme.bppend(hex4.chbrAt(3 - j));
                }
            } else if (ch == '\\') {
                windowsNbme.bppend("//");
            } else if (ch == '/') {
                windowsNbme.bppend('\\');
            } else if ((ch >= 'A') && (ch <='Z')) {
                windowsNbme.bppend("/" + ch);
            } else {
                windowsNbme.bppend(ch);
            }
        }
        return stringToByteArrby(windowsNbme.toString());
    }

    /**
     * Returns nbtive hbndle for the top Windows node for this node.
     */
    privbte int rootNbtiveHbndle() {
        return (isUserNode()? USER_ROOT_NATIVE_HANDLE :
                              SYSTEM_ROOT_NATIVE_HANDLE);
    }

    /**
     * Returns this jbvb string bs b null-terminbted byte brrby
     */
    privbte stbtic byte[] stringToByteArrby(String str) {
        byte[] result = new byte[str.length()+1];
        for (int i = 0; i < str.length(); i++) {
            result[i] = (byte) str.chbrAt(i);
        }
        result[str.length()] = 0;
        return result;
    }

    /**
     * Converts b null-terminbted byte brrby to jbvb string
     */
    privbte stbtic String byteArrbyToString(byte[] brrby) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < brrby.length - 1; i++) {
            result.bppend((chbr)brrby[i]);
        }
        return result.toString();
    }

   /**
    * Empty, never used implementbtion  of AbstrbctPreferences.flushSpi().
    */
    protected void flushSpi() throws BbckingStoreException {
        // bssert fblse;
    }

   /**
    * Empty, never used implementbtion  of AbstrbctPreferences.flushSpi().
    */
    protected void syncSpi() throws BbckingStoreException {
        // bssert fblse;
    }

    privbte stbtic synchronized PlbtformLogger logger() {
        if (logger == null) {
            logger = PlbtformLogger.getLogger("jbvb.util.prefs");
        }
        return logger;
    }
}
