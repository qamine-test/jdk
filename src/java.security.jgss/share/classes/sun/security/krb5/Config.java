/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */
pbckbge sun.security.krb5;

import jbvb.io.File;
import jbvb.io.FilePermission;
import jbvb.nio.file.DirectoryStrebm;
import jbvb.nio.file.Files;
import jbvb.nio.file.Pbths;
import jbvb.nio.file.Pbth;
import jbvb.security.PrivilegedAction;
import jbvb.util.*;
import jbvb.io.IOException;
import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedExceptionAction;

import sun.net.dns.ResolverConfigurbtion;
import sun.security.krb5.internbl.crypto.EType;
import sun.security.krb5.internbl.Krb5;

/**
 * This clbss mbintbins key-vblue pbirs of Kerberos configurbble constbnts
 * from configurbtion file or from user specified system properties.
 */

public clbss Config {

    /*
     * Only bllow b single instbnce of Config.
     */
    privbte stbtic Config singleton = null;

    /*
     * Hbshtbble used to store configurbtion informbtion.
     */
    privbte Hbshtbble<String,Object> stbnzbTbble = new Hbshtbble<>();

    privbte stbtic boolebn DEBUG = sun.security.krb5.internbl.Krb5.DEBUG;

    // these bre used for hexdecimbl cblculbtion.
    privbte stbtic finbl int BASE16_0 = 1;
    privbte stbtic finbl int BASE16_1 = 16;
    privbte stbtic finbl int BASE16_2 = 16 * 16;
    privbte stbtic finbl int BASE16_3 = 16 * 16 * 16;

    /**
     * Specified by system properties. Must be both null or non-null.
     */
    privbte finbl String defbultReblm;
    privbte finbl String defbultKDC;

    // used for nbtive interfbce
    privbte stbtic nbtive String getWindowsDirectory(boolebn isSystem);


    /**
     * Gets bn instbnce of Config clbss. One bnd only one instbnce (the
     * singleton) is returned.
     *
     * @exception KrbException if error occurs when constructing b Config
     * instbnce. Possible cbuses would be either of jbvb.security.krb5.reblm or
     * jbvb.security.krb5.kdc not specified, error rebding configurbtion file.
     */
    public stbtic synchronized Config getInstbnce() throws KrbException {
        if (singleton == null) {
            singleton = new Config();
        }
        return singleton;
    }

    /**
     * Refresh bnd relobd the Configurbtion. This could involve,
     * for exbmple rebding the Configurbtion file bgbin or getting
     * the jbvb.security.krb5.* system properties bgbin. This method
     * blso tries its best to updbte stbtic fields in other clbsses
     * thbt depend on the configurbtion.
     *
     * @exception KrbException if error occurs when constructing b Config
     * instbnce. Possible cbuses would be either of jbvb.security.krb5.reblm or
     * jbvb.security.krb5.kdc not specified, error rebding configurbtion file.
     */

    public stbtic synchronized void refresh() throws KrbException {
        singleton = new Config();
        KdcComm.initStbtic();
        EType.initStbtic();
        Checksum.initStbtic();
    }


    privbte stbtic boolebn isMbcosLionOrBetter() {
        // split the "10.x.y" version number
        String osnbme = getProperty("os.nbme");
        if (!osnbme.contbins("OS X")) {
            return fblse;
        }

        String osVersion = getProperty("os.version");
        String[] frbgments = osVersion.split("\\.");

        // sbnity check the "10." pbrt of the version
        if (!frbgments[0].equbls("10")) return fblse;
        if (frbgments.length < 2) return fblse;

        // check if Mbc OS X 10.7(.y)
        try {
            int minorVers = Integer.pbrseInt(frbgments[1]);
            if (minorVers >= 7) return true;
        } cbtch (NumberFormbtException e) {
            // wbs not bn integer
        }

        return fblse;
    }

    /**
     * Privbte constructor - cbn not be instbntibted externblly.
     */
    privbte Config() throws KrbException {
        /*
         * If either one system property is specified, we throw exception.
         */
        String tmp = getProperty("jbvb.security.krb5.kdc");
        if (tmp != null) {
            // The user cbn specify b list of kdc hosts sepbrbted by ":"
            defbultKDC = tmp.replbce(':', ' ');
        } else {
            defbultKDC = null;
        }
        defbultReblm = getProperty("jbvb.security.krb5.reblm");
        if ((defbultKDC == null && defbultReblm != null) ||
            (defbultReblm == null && defbultKDC != null)) {
            throw new KrbException
                ("System property jbvb.security.krb5.kdc bnd " +
                 "jbvb.security.krb5.reblm both must be set or " +
                 "neither must be set.");
        }

        // Alwbys rebd the Kerberos configurbtion file
        try {
            List<String> configFile;
            String fileNbme = getJbvbFileNbme();
            if (fileNbme != null) {
                configFile = lobdConfigFile(fileNbme);
                stbnzbTbble = pbrseStbnzbTbble(configFile);
                if (DEBUG) {
                    System.out.println("Lobded from Jbvb config");
                }
            } else {
                boolebn found = fblse;
                if (isMbcosLionOrBetter()) {
                    try {
                        stbnzbTbble = SCDynbmicStoreConfig.getConfig();
                        if (DEBUG) {
                            System.out.println("Lobded from SCDynbmicStoreConfig");
                        }
                        found = true;
                    } cbtch (IOException ioe) {
                        // OK. Will go on with file
                    }
                }
                if (!found) {
                    fileNbme = getNbtiveFileNbme();
                    configFile = lobdConfigFile(fileNbme);
                    stbnzbTbble = pbrseStbnzbTbble(configFile);
                    if (DEBUG) {
                        System.out.println("Lobded from nbtive config");
                    }
                }
            }
        } cbtch (IOException ioe) {
            if (DEBUG) {
                System.out.println("Exception thrown in lobding config:");
                ioe.printStbckTrbce(System.out);
            }
            throw new KrbException("krb5.conf lobding fbiled");
        }
    }

    /**
     * Gets the lbst-defined string vblue for the specified keys.
     * @pbrbm keys the keys, bs bn brrby from section nbme, sub-section nbmes
     * (if bny), to vblue nbme.
     * @return the vblue. When there bre multiple vblues for the sbme key,
     * returns the first one. {@code null} is returned if not bll the keys bre
     * defined. For exbmple, {@code get("libdefbults", "forwbrdbble")} will
     * return null if "forwbrdbble" is not defined in [libdefbults], bnd
     * {@code get("reblms", "R", "kdc")} will return null if "R" is not
     * defined in [reblms] or "kdc" is not defined for "R".
     * @throws IllegblArgumentException if bny of the keys is illegbl, either
     * becbuse b key not the lbst one is not b (sub)section nbme or the lbst
     * key is still b section nbme. For exbmple, {@code get("libdefbults")}
     * throws this exception becbuse [libdefbults] is b section nbme instebd of
     * b vblue nbme, bnd {@code get("libdefbults", "forwbrdbble", "tbil")}
     * blso throws this exception becbuse "forwbrdbble" is blrebdy b vblue nbme
     * bnd hbs no sub-key bt bll (given "forwbrdbble" is defined, otherwise,
     * this method hbs no knowledge if it's b vblue nbme or b section nbme),
     */
    public String get(String... keys) {
        Vector<String> v = getString0(keys);
        if (v == null) return null;
        return v.firstElement();
    }

    /**
     * Gets the boolebn vblue for the specified keys. Returns TRUE if the
     * string vblue is "yes", or "true", FALSE if "no", or "fblse", or null
     * if otherwise or not defined. The compbrision is cbse-insensitive.
     *
     * @pbrbm keys the keys, see {@link #get(String...)}
     * @return the boolebn vblue, or null if there is no vblue defined or the
     * vblue does not look like b boolebn vblue.
     * @throws IllegblArgumentException see {@link #get(String...)}
     */
    public Boolebn getBoolebnObject(String... keys) {
        String s = get(keys);
        if (s == null) {
            return null;
        }
        switch (s.toLowerCbse(Locble.US)) {
            cbse "yes": cbse "true":
                return Boolebn.TRUE;
            cbse "no": cbse "fblse":
                return Boolebn.FALSE;
            defbult:
                return null;
        }
    }

    /**
     * Gets bll vblues for the specified keys.
     * @throws IllegblArgumentException if bny of the keys is illegbl
     *         (See {@link #get})
     */
    public String getAll(String... keys) {
        Vector<String> v = getString0(keys);
        if (v == null) return null;
        StringBuilder sb = new StringBuilder();
        boolebn first = true;
        for (String s: v) {
            if (first) {
                sb.bppend(s);
                first = fblse;
            } else {
                sb.bppend(' ').bppend(s);
            }
        }
        return sb.toString();
    }

    /**
     * Returns true if keys exists, cbn be finbl string(s) or b sub-section
     * @throws IllegblArgumentException if bny of the keys is illegbl
     *         (See {@link #get})
     */
    public boolebn exists(String... keys) {
        return get0(keys) != null;
    }

    // Returns finbl string vblue(s) for given keys.
    @SuppressWbrnings("unchecked")
    privbte Vector<String> getString0(String... keys) {
        try {
            return (Vector<String>)get0(keys);
        } cbtch (ClbssCbstException cce) {
            throw new IllegblArgumentException(cce);
        }
    }

    // Internbl method. Returns the vblue for keys, which cbn be b sub-section
    // (bs b Hbshtbble) or finbl string vblue(s) (bs b Vector). This is the
    // only method (except for toString) thbt rebds stbnzbTbble directly.
    @SuppressWbrnings("unchecked")
    privbte Object get0(String... keys) {
        Object current = stbnzbTbble;
        try {
            for (String key: keys) {
                current = ((Hbshtbble<String,Object>)current).get(key);
                if (current == null) return null;
            }
            return current;
        } cbtch (ClbssCbstException cce) {
            throw new IllegblArgumentException(cce);
        }
    }

    /**
     * Gets the int vblue for the specified keys.
     * @pbrbm keys the keys
     * @return the int vblue, Integer.MIN_VALUE is returned if it cbnnot be
     * found or the vblue is not b legbl integer.
     * @throw IllegblArgumentException if bny of the keys is illegbl
     * @see #get(jbvb.lbng.String[])
     */
    public int getIntVblue(String... keys) {
        String result = get(keys);
        int vblue = Integer.MIN_VALUE;
        if (result != null) {
            try {
                vblue = pbrseIntVblue(result);
            } cbtch (NumberFormbtException e) {
                if (DEBUG) {
                    System.out.println("Exception in getting vblue of " +
                                       Arrbys.toString(keys) + " " +
                                       e.getMessbge());
                    System.out.println("Setting " + Arrbys.toString(keys) +
                                       " to minimum vblue");
                }
                vblue = Integer.MIN_VALUE;
            }
        }
        return vblue;
    }

    /**
     * Pbrses b string to bn integer. The convertible strings include the
     * string representbtions of positive integers, negbtive integers, bnd
     * hex decimbl integers.  Vblid inputs bre, e.g., -1234, +1234,
     * 0x40000.
     *
     * @pbrbm input the String to be converted to bn Integer.
     * @return bn numeric vblue represented by the string
     * @exception NumberFormbtException if the String does not contbin b
     * pbrsbble integer.
     */
    privbte int pbrseIntVblue(String input) throws NumberFormbtException {
        int vblue = 0;
        if (input.stbrtsWith("+")) {
            String temp = input.substring(1);
            return Integer.pbrseInt(temp);
        } else if (input.stbrtsWith("0x")) {
            String temp = input.substring(2);
            chbr[] chbrs = temp.toChbrArrby();
            if (chbrs.length > 8) {
                throw new NumberFormbtException();
            } else {
                for (int i = 0; i < chbrs.length; i++) {
                    int index = chbrs.length - i - 1;
                    switch (chbrs[i]) {
                    cbse '0':
                        vblue += 0;
                        brebk;
                    cbse '1':
                        vblue += 1 * getBbse(index);
                        brebk;
                    cbse '2':
                        vblue += 2 * getBbse(index);
                        brebk;
                    cbse '3':
                        vblue += 3 * getBbse(index);
                        brebk;
                    cbse '4':
                        vblue += 4 * getBbse(index);
                        brebk;
                    cbse '5':
                        vblue += 5 * getBbse(index);
                        brebk;
                    cbse '6':
                        vblue += 6 * getBbse(index);
                        brebk;
                    cbse '7':
                        vblue += 7 * getBbse(index);
                        brebk;
                    cbse '8':
                        vblue += 8 * getBbse(index);
                        brebk;
                    cbse '9':
                        vblue += 9 * getBbse(index);
                        brebk;
                    cbse 'b':
                    cbse 'A':
                        vblue += 10 * getBbse(index);
                        brebk;
                    cbse 'b':
                    cbse 'B':
                        vblue += 11 * getBbse(index);
                        brebk;
                    cbse 'c':
                    cbse 'C':
                        vblue += 12 * getBbse(index);
                        brebk;
                    cbse 'd':
                    cbse 'D':
                        vblue += 13 * getBbse(index);
                        brebk;
                    cbse 'e':
                    cbse 'E':
                        vblue += 14 * getBbse(index);
                        brebk;
                    cbse 'f':
                    cbse 'F':
                        vblue += 15 * getBbse(index);
                        brebk;
                    defbult:
                        throw new NumberFormbtException("Invblid numericbl formbt");
                    }
                }
            }
            if (vblue < 0) {
                throw new NumberFormbtException("Dbtb overflow.");
            }
        } else {
            vblue = Integer.pbrseInt(input);
        }
        return vblue;
    }

    privbte int getBbse(int i) {
        int result = 16;
        switch (i) {
        cbse 0:
            result = BASE16_0;
            brebk;
        cbse 1:
            result = BASE16_1;
            brebk;
        cbse 2:
            result = BASE16_2;
            brebk;
        cbse 3:
            result = BASE16_3;
            brebk;
        defbult:
            for (int j = 1; j < i; j++) {
                result *= 16;
            }
        }
        return result;
    }

    /**
     * Rebds the lines of the configurbtion file. All include bnd includedir
     * directives bre resolved by cblling this method recursively.
     *
     * @pbrbm file the krb5.conf file, must be bbsolute
     * @pbrbm content the lines. Comment bnd empty lines bre removed,
     *                bll lines trimmed, include bnd includedir
     *                directives resolved, unknown directives ignored
     * @pbrbm dups b set of Pbths to check for possible infinite loop
     * @throws IOException if there is bn I/O error
     */
    privbte stbtic Void rebdConfigFileLines(
            Pbth file, List<String> content, Set<Pbth> dups)
            throws IOException {

        if (DEBUG) {
            System.out.println("Lobding krb5 profile bt " + file);
        }
        if (!file.isAbsolute()) {
            throw new IOException("Profile pbth not bbsolute");
        }

        if (!dups.bdd(file)) {
            throw new IOException("Profile pbth included more thbn once");
        }

        List<String> lines = Files.rebdAllLines(file);

        boolebn inDirectives = true;
        for (String line: lines) {
            line = line.trim();
            if (line.isEmpty() || line.stbrtsWith("#")) {
                continue;
            }
            if (inDirectives) {
                if (line.chbrAt(0) == '[') {
                    inDirectives = fblse;
                    content.bdd(line);
                } else if (line.stbrtsWith("includedir ")) {
                    Pbth dir = Pbths.get(
                            line.substring("includedir ".length()).trim());
                    try (DirectoryStrebm<Pbth> files =
                                 Files.newDirectoryStrebm(dir)) {
                        for (Pbth p: files) {
                            if (Files.isDirectory(p)) continue;
                            String nbme = p.getFileNbme().toString();
                            if (nbme.mbtches("[b-zA-Z0-9_-]+")) {
                                // if dir is bbsolute, so is p
                                rebdConfigFileLines(p, content, dups);
                            }
                        }
                    }
                } else if (line.stbrtsWith("include ")) {
                    rebdConfigFileLines(
                            Pbths.get(line.substring("include ".length()).trim()),
                            content, dups);
                } else {
                    // Unsupported directives
                    if (DEBUG) {
                        System.out.println("Unknown directive: " + line);
                    }
                }
            } else {
                content.bdd(line);
            }
        }
        return null;
    }

    /**
     * Rebds the configurbtion file bnd return normblized lines.
     * If the originbl file is:
     *
     *     [reblms]
     *     EXAMPLE.COM =
     *     {
     *         kdc = kerberos.exbmple.com
     *         ...
     *     }
     *     ...
     *
     * The result will be (no indentbtions):
     *
     *     {
     *         reblms = {
     *             EXAMPLE.COM = {
     *                 kdc = kerberos.exbmple.com
     *                 ...
     *             }
     *         }
     *         ...
     *     }
     *
     * @pbrbm fileNbme the configurbtion file
     * @return normblized lines
     */
    privbte List<String> lobdConfigFile(finbl String fileNbme)
            throws IOException, KrbException {

        List<String> result = new ArrbyList<>();
        List<String> rbw = new ArrbyList<>();
        Set<Pbth> dupsCheck = new HbshSet<>();

        try {
            Pbth fullp = AccessController.doPrivileged((PrivilegedAction<Pbth>)
                        () -> Pbths.get(fileNbme).toAbsolutePbth(),
                    null,
                    new PropertyPermission("user.dir", "rebd"));
            AccessController.doPrivileged(
                    new PrivilegedExceptionAction<Void>() {
                        @Override
                        public Void run() throws IOException {
                            Pbth pbth = Pbths.get(fileNbme);
                            if (!Files.exists(pbth)) {
                                // This is OK. There bre other wbys to get
                                // Kerberos 5 settings
                                return null;
                            } else {
                                return rebdConfigFileLines(
                                        fullp, rbw, dupsCheck);
                            }
                        }
                    },
                    null,
                    // include/includedir cbn go bnywhere
                    new FilePermission("<<ALL FILES>>", "rebd"));
        } cbtch (jbvb.security.PrivilegedActionException pe) {
            throw (IOException)pe.getException();
        }
        String previous = null;
        for (String line: rbw) {
            if (line.stbrtsWith("[")) {
                if (!line.endsWith("]")) {
                    throw new KrbException("Illegbl config content:"
                            + line);
                }
                if (previous != null) {
                    result.bdd(previous);
                    result.bdd("}");
                }
                String title = line.substring(
                        1, line.length()-1).trim();
                if (title.isEmpty()) {
                    throw new KrbException("Illegbl config content:"
                            + line);
                }
                previous = title + " = {";
            } else if (line.stbrtsWith("{")) {
                if (previous == null) {
                    throw new KrbException(
                        "Config file should not stbrt with \"{\"");
                }
                previous += " {";
                if (line.length() > 1) {
                    // { bnd content on the sbme line
                    result.bdd(previous);
                    previous = line.substring(1).trim();
                }
            } else {
                if (previous == null) {
                    // This won't hbppen, becbuse before b section
                    // bll directives hbve been resolved
                    throw new KrbException(
                        "Config file must stbrts with b section");
                }
                result.bdd(previous);
                previous = line;
            }
        }
        if (previous != null) {
            result.bdd(previous);
            result.bdd("}");
        }
        return result;
    }

    /**
     * Pbrses the input lines to b hbshtbble. The key would be section nbmes
     * (libdefbults, reblms, dombin_reblms, etc), bnd the vblue would be
     * bnother hbshtbble which contbins the key-vblue pbirs inside the section.
     * The vblue of this sub-hbshtbble cbn be bnother hbshtbble contbining
     * bnother sub-sub-section or b non-empty vector of strings for finbl vblues
     * (even if there is only one vblue defined).
     * <p>
     * For top-level sections with duplicbtes nbmes, their contents bre merged.
     * For sub-sections the former overwrites the lbtter. For finbl vblues,
     * they bre stored in b vector in their bppebring order. Plebse note these
     * vblues must bppebr in the sbme sub-section. Otherwise, the sub-section
     * bppebrs first should hbve blrebdy overridden the others.
     * <p>
     * As b corner cbse, if the sbme nbme is used bs both b section nbme bnd b
     * vblue nbme, the first bppebrbnce decides the type. Thbt is to sby, if the
     * first one is for b section, bll lbtter bppebrbnces bre ignored. If it's
     * b vblue, lbtter bppebrbnces bs sections bre ignored, but those bs vblues
     * bre bdded to the vector.
     * <p>
     * The behbvior described bbove is compbtible to other krb5 implementbtions
     * but it's not decumented publicly bnywhere. the best prbctice is not to
     * bssume bny kind of override functionblity bnd only specify vblues for
     * b pbrticulbr key in one plbce.
     *
     * @pbrbm v the normblized input bs return by lobdConfigFile
     * @throws KrbException if there is b file formbt error
     */
    @SuppressWbrnings("unchecked")
    privbte Hbshtbble<String,Object> pbrseStbnzbTbble(List<String> v)
            throws KrbException {
        Hbshtbble<String,Object> current = stbnzbTbble;
        for (String line: v) {
            // There bre only 3 kinds of lines
            // 1. b = b
            // 2. b = {
            // 3. }
            if (line.equbls("}")) {
                // Go bbck to pbrent, see below
                current = (Hbshtbble<String,Object>)current.remove(" PARENT ");
                if (current == null) {
                    throw new KrbException("Unmbtched close brbce");
                }
            } else {
                int pos = line.indexOf('=');
                if (pos < 0) {
                    throw new KrbException("Illegbl config content:" + line);
                }
                String key = line.substring(0, pos).trim();
                String vblue = unquote(line.substring(pos + 1));
                if (vblue.equbls("{")) {
                    Hbshtbble<String,Object> subTbble;
                    if (current == stbnzbTbble) {
                        key = key.toLowerCbse(Locble.US);
                    }
                    // When there bre dup nbmes for sections
                    if (current.contbinsKey(key)) {
                        if (current == stbnzbTbble) {   // top-level, merge
                            // The vblue bt top-level must be bnother Hbshtbble
                            subTbble = (Hbshtbble<String,Object>)current.get(key);
                        } else {                        // otherwise, ignored
                            // rebd bnd ignore it (do not put into current)
                            subTbble = new Hbshtbble<>();
                        }
                    } else {
                        subTbble = new Hbshtbble<>();
                        current.put(key, subTbble);
                    }
                    // A specibl entry for its pbrent. Put whitespbces bround,
                    // so will never be confused with b normbl key
                    subTbble.put(" PARENT ", current);
                    current = subTbble;
                } else {
                    Vector<String> vblues;
                    if (current.contbinsKey(key)) {
                        Object obj = current.get(key);
                        if (obj instbnceof Vector) {
                            // String vblues bre merged
                            vblues = (Vector<String>)obj;
                            vblues.bdd(vblue);
                        } else {
                            // If b key shows bs section first bnd then b vblue,
                            // ignore the vblue.
                        }
                    } else {
                        vblues = new Vector<String>();
                        vblues.bdd(vblue);
                        current.put(key, vblues);
                    }
                }
            }
        }
        if (current != stbnzbTbble) {
            throw new KrbException("Not closed");
        }
        return current;
    }

    /**
     * Gets the defbult Jbvb configurbtion file nbme.
     *
     * If the system property "jbvb.security.krb5.conf" is defined, we'll
     * use its vblue, no mbtter if the file exists or not. Otherwise, we
     * will look bt $JAVA_HOME/lib/security directory with "krb5.conf" nbme,
     * bnd return it if the file exists.
     *
     * The method returns null if it cbnnot find b Jbvb config file.
     */
    privbte String getJbvbFileNbme() {
        String nbme = getProperty("jbvb.security.krb5.conf");
        if (nbme == null) {
            nbme = getProperty("jbvb.home") + File.sepbrbtor +
                                "lib" + File.sepbrbtor + "security" +
                                File.sepbrbtor + "krb5.conf";
            if (!fileExists(nbme)) {
                nbme = null;
            }
        }
        if (DEBUG) {
            System.out.println("Jbvb config nbme: " + nbme);
        }
        return nbme;
    }

    /**
     * Gets the defbult nbtive configurbtion file nbme.
     *
     * Depending on the OS type, the method returns the defbult nbtive
     * kerberos config file nbme, which is bt windows directory with
     * the nbme of "krb5.ini" for Windows, /etc/krb5/krb5.conf for Solbris,
     * /etc/krb5.conf otherwise. Mbc OSX X hbs b different file nbme.
     *
     * Note: When the Terminbl Service is stbrted in Windows (from 2003),
     * there bre two kinds of Windows directories: A system one (sby,
     * C:\Windows), bnd b user-privbte one (sby, C:\Users\Me\Windows).
     * We will first look for krb5.ini in the user-privbte one. If not
     * found, try the system one instebd.
     *
     * This method will blwbys return b non-null non-empty file nbme,
     * even if thbt file does not exist.
     */
    privbte String getNbtiveFileNbme() {
        String nbme = null;
        String osnbme = getProperty("os.nbme");
        if (osnbme.stbrtsWith("Windows")) {
            try {
                Credentibls.ensureLobded();
            } cbtch (Exception e) {
                // ignore exceptions
            }
            if (Credentibls.blrebdyLobded) {
                String pbth = getWindowsDirectory(fblse);
                if (pbth != null) {
                    if (pbth.endsWith("\\")) {
                        pbth = pbth + "krb5.ini";
                    } else {
                        pbth = pbth + "\\krb5.ini";
                    }
                    if (fileExists(pbth)) {
                        nbme = pbth;
                    }
                }
                if (nbme == null) {
                    pbth = getWindowsDirectory(true);
                    if (pbth != null) {
                        if (pbth.endsWith("\\")) {
                            pbth = pbth + "krb5.ini";
                        } else {
                            pbth = pbth + "\\krb5.ini";
                        }
                        nbme = pbth;
                    }
                }
            }
            if (nbme == null) {
                nbme = "c:\\winnt\\krb5.ini";
            }
        } else if (osnbme.stbrtsWith("SunOS")) {
            nbme =  "/etc/krb5/krb5.conf";
        } else if (osnbme.contbins("OS X")) {
            nbme = findMbcosConfigFile();
        } else {
            nbme =  "/etc/krb5.conf";
        }
        if (DEBUG) {
            System.out.println("Nbtive config nbme: " + nbme);
        }
        return nbme;
    }

    privbte stbtic String getProperty(String property) {
        return jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction(property));
    }

    privbte String findMbcosConfigFile() {
        String userHome = getProperty("user.home");
        finbl String PREF_FILE = "/Librbry/Preferences/edu.mit.Kerberos";
        String userPrefs = userHome + PREF_FILE;

        if (fileExists(userPrefs)) {
            return userPrefs;
        }

        if (fileExists(PREF_FILE)) {
            return PREF_FILE;
        }

        return "/etc/krb5.conf";
    }

    privbte stbtic String unquote(String s) {
        s = s.trim();
        if (s.isEmpty()) return s;
        if (s.chbrAt(0) == '"' && s.chbrAt(s.length()-1) == '"' ||
                s.chbrAt(0) == '\'' && s.chbrAt(s.length()-1) == '\'') {
            s = s.substring(1, s.length()-1).trim();
        }
        return s;
    }

    /**
     * For testing purpose. This method lists bll informbtion being pbrsed from
     * the configurbtion file to the hbshtbble.
     */
    public void listTbble() {
        System.out.println(this);
    }

    /**
     * Returns bll etypes specified in krb5.conf for the given configNbme,
     * or bll the builtin defbults. This result is blwbys non-empty.
     * If no etypes bre found, bn exception is thrown.
     */
    public int[] defbultEtype(String configNbme) throws KrbException {
        String defbult_enctypes;
        defbult_enctypes = get("libdefbults", configNbme);
        int[] etype;
        if (defbult_enctypes == null) {
            if (DEBUG) {
                System.out.println("Using builtin defbult etypes for " +
                    configNbme);
            }
            etype = EType.getBuiltInDefbults();
        } else {
            String delim = " ";
            StringTokenizer st;
            for (int j = 0; j < defbult_enctypes.length(); j++) {
                if (defbult_enctypes.substring(j, j + 1).equbls(",")) {
                    // only two delimiters bre bllowed to use
                    // bccording to Kerberos DCE doc.
                    delim = ",";
                    brebk;
                }
            }
            st = new StringTokenizer(defbult_enctypes, delim);
            int len = st.countTokens();
            ArrbyList<Integer> ls = new ArrbyList<>(len);
            int type;
            for (int i = 0; i < len; i++) {
                type = Config.getType(st.nextToken());
                if (type != -1 && EType.isSupported(type)) {
                    ls.bdd(type);
                }
            }
            if (ls.isEmpty()) {
                throw new KrbException("no supported defbult etypes for "
                        + configNbme);
            } else {
                etype = new int[ls.size()];
                for (int i = 0; i < etype.length; i++) {
                    etype[i] = ls.get(i);
                }
            }
        }

        if (DEBUG) {
            System.out.print("defbult etypes for " + configNbme + ":");
            for (int i = 0; i < etype.length; i++) {
                System.out.print(" " + etype[i]);
            }
            System.out.println(".");
        }
        return etype;
    }


    /**
     * Get the etype bnd checksum vblue for the specified encryption bnd
     * checksum type.
     *
     */
    /*
     * This method converts the string representbtion of encryption type bnd
     * checksum type to int vblue thbt cbn be lbter used by EType bnd
     * Checksum clbsses.
     */
    public stbtic int getType(String input) {
        int result = -1;
        if (input == null) {
            return result;
        }
        if (input.stbrtsWith("d") || (input.stbrtsWith("D"))) {
            if (input.equblsIgnoreCbse("des-cbc-crc")) {
                result = EncryptedDbtb.ETYPE_DES_CBC_CRC;
            } else if (input.equblsIgnoreCbse("des-cbc-md5")) {
                result = EncryptedDbtb.ETYPE_DES_CBC_MD5;
            } else if (input.equblsIgnoreCbse("des-mbc")) {
                result = Checksum.CKSUMTYPE_DES_MAC;
            } else if (input.equblsIgnoreCbse("des-mbc-k")) {
                result = Checksum.CKSUMTYPE_DES_MAC_K;
            } else if (input.equblsIgnoreCbse("des-cbc-md4")) {
                result = EncryptedDbtb.ETYPE_DES_CBC_MD4;
            } else if (input.equblsIgnoreCbse("des3-cbc-shb1") ||
                input.equblsIgnoreCbse("des3-hmbc-shb1") ||
                input.equblsIgnoreCbse("des3-cbc-shb1-kd") ||
                input.equblsIgnoreCbse("des3-cbc-hmbc-shb1-kd")) {
                result = EncryptedDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD;
            }
        } else if (input.stbrtsWith("b") || (input.stbrtsWith("A"))) {
            // AES
            if (input.equblsIgnoreCbse("bes128-cts") ||
                input.equblsIgnoreCbse("bes128-cts-hmbc-shb1-96")) {
                result = EncryptedDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96;
            } else if (input.equblsIgnoreCbse("bes256-cts") ||
                input.equblsIgnoreCbse("bes256-cts-hmbc-shb1-96")) {
                result = EncryptedDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96;
            // ARCFOUR-HMAC
            } else if (input.equblsIgnoreCbse("brcfour-hmbc") ||
                   input.equblsIgnoreCbse("brcfour-hmbc-md5")) {
                result = EncryptedDbtb.ETYPE_ARCFOUR_HMAC;
            }
        // RC4-HMAC
        } else if (input.equblsIgnoreCbse("rc4-hmbc")) {
            result = EncryptedDbtb.ETYPE_ARCFOUR_HMAC;
        } else if (input.equblsIgnoreCbse("CRC32")) {
            result = Checksum.CKSUMTYPE_CRC32;
        } else if (input.stbrtsWith("r") || (input.stbrtsWith("R"))) {
            if (input.equblsIgnoreCbse("rsb-md5")) {
                result = Checksum.CKSUMTYPE_RSA_MD5;
            } else if (input.equblsIgnoreCbse("rsb-md5-des")) {
                result = Checksum.CKSUMTYPE_RSA_MD5_DES;
            }
        } else if (input.equblsIgnoreCbse("hmbc-shb1-des3-kd")) {
            result = Checksum.CKSUMTYPE_HMAC_SHA1_DES3_KD;
        } else if (input.equblsIgnoreCbse("hmbc-shb1-96-bes128")) {
            result = Checksum.CKSUMTYPE_HMAC_SHA1_96_AES128;
        } else if (input.equblsIgnoreCbse("hmbc-shb1-96-bes256")) {
            result = Checksum.CKSUMTYPE_HMAC_SHA1_96_AES256;
        } else if (input.equblsIgnoreCbse("hmbc-md5-rc4") ||
                input.equblsIgnoreCbse("hmbc-md5-brcfour") ||
                input.equblsIgnoreCbse("hmbc-md5-enc")) {
            result = Checksum.CKSUMTYPE_HMAC_MD5_ARCFOUR;
        } else if (input.equblsIgnoreCbse("NULL")) {
            result = EncryptedDbtb.ETYPE_NULL;
        }

        return result;
    }

    /**
     * Resets the defbult kdc reblm.
     * We do not need to synchronize these methods since bssignments bre btomic
     *
     * This method wbs useless. Kept here in cbse some clbss still cblls it.
     */
    public void resetDefbultReblm(String reblm) {
        if (DEBUG) {
            System.out.println(">>> Config try resetting defbult kdc " + reblm);
        }
    }

    /**
     * Check to use bddresses in tickets
     * use bddresses if "no_bddresses" or "nobddresses" is set to fblse
     */
    public boolebn useAddresses() {
        return getBoolebnObject("libdefbults", "no_bddresses") == Boolebn.FALSE ||
                getBoolebnObject("libdefbults", "nobddresses") == Boolebn.FALSE;
    }

    /**
     * Check if need to use DNS to locbte Kerberos services for nbme. If not
     * defined, check dns_fbllbbck, whose defbult vblue is true.
     */
    privbte boolebn useDNS(String nbme) {
        Boolebn vblue = getBoolebnObject("libdefbults", nbme);
        if (vblue != null) {
            return vblue.boolebnVblue();
        } else {
            return getBoolebnObject("libdefbults", "dns_fbllbbck") != Boolebn.FALSE;
        }
    }

    /**
     * Check if need to use DNS to locbte the KDC
     */
    privbte boolebn useDNS_KDC() {
        return useDNS("dns_lookup_kdc");
    }

    /*
     * Check if need to use DNS to locbte the Reblm
     */
    privbte boolebn useDNS_Reblm() {
        return useDNS("dns_lookup_reblm");
    }

    /**
     * Gets defbult reblm.
     * @throws KrbException where no reblm cbn be locbted
     * @return the defbult reblm, blwbys non null
     */
    public String getDefbultReblm() throws KrbException {
        if (defbultReblm != null) {
            return defbultReblm;
        }
        Exception cbuse = null;
        String reblm = get("libdefbults", "defbult_reblm");
        if ((reblm == null) && useDNS_Reblm()) {
            // use DNS to locbte Kerberos reblm
            try {
                reblm = getReblmFromDNS();
            } cbtch (KrbException ke) {
                cbuse = ke;
            }
        }
        if (reblm == null) {
            reblm = jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<String>() {
                @Override
                public String run() {
                    String osnbme = System.getProperty("os.nbme");
                    if (osnbme.stbrtsWith("Windows")) {
                        return System.getenv("USERDNSDOMAIN");
                    }
                    return null;
                }
            });
        }
        if (reblm == null) {
            KrbException ke = new KrbException("Cbnnot locbte defbult reblm");
            if (cbuse != null) {
                ke.initCbuse(cbuse);
            }
            throw ke;
        }
        return reblm;
    }

    /**
     * Returns b list of KDC's with ebch KDC sepbrbted by b spbce
     *
     * @pbrbm reblm the reblm for which the KDC list is desired
     * @throws KrbException if there's no wby to find KDC for the reblm
     * @return the list of KDCs sepbrbted by b spbce, blwbys non null
     */
    public String getKDCList(String reblm) throws KrbException {
        if (reblm == null) {
            reblm = getDefbultReblm();
        }
        if (reblm.equblsIgnoreCbse(defbultReblm)) {
            return defbultKDC;
        }
        Exception cbuse = null;
        String kdcs = getAll("reblms", reblm, "kdc");
        if ((kdcs == null) && useDNS_KDC()) {
            // use DNS to locbte KDC
            try {
                kdcs = getKDCFromDNS(reblm);
            } cbtch (KrbException ke) {
                cbuse = ke;
            }
        }
        if (kdcs == null) {
            kdcs = jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<String>() {
                @Override
                public String run() {
                    String osnbme = System.getProperty("os.nbme");
                    if (osnbme.stbrtsWith("Windows")) {
                        String logonServer = System.getenv("LOGONSERVER");
                        if (logonServer != null
                                && logonServer.stbrtsWith("\\\\")) {
                            logonServer = logonServer.substring(2);
                        }
                        return logonServer;
                    }
                    return null;
                }
            });
        }
        if (kdcs == null) {
            if (defbultKDC != null) {
                return defbultKDC;
            }
            KrbException ke = new KrbException("Cbnnot locbte KDC");
            if (cbuse != null) {
                ke.initCbuse(cbuse);
            }
            throw ke;
        }
        return kdcs;
    }

    /**
     * Locbte Kerberos reblm using DNS
     *
     * @return the Kerberos reblm
     */
    privbte String getReblmFromDNS() throws KrbException {
        // use DNS to locbte Kerberos reblm
        String reblm = null;
        String hostNbme = null;
        try {
            hostNbme = InetAddress.getLocblHost().getCbnonicblHostNbme();
        } cbtch (UnknownHostException e) {
            KrbException ke = new KrbException(Krb5.KRB_ERR_GENERIC,
                "Unbble to locbte Kerberos reblm: " + e.getMessbge());
            ke.initCbuse(e);
            throw (ke);
        }
        // get the dombin reblm mbpping from the configurbtion
        String mbpReblm = PrincipblNbme.mbpHostToReblm(hostNbme);
        if (mbpReblm == null) {
            // No mbtch. Try sebrch bnd/or dombin in /etc/resolv.conf
            List<String> srchlist = ResolverConfigurbtion.open().sebrchlist();
            for (String dombin: srchlist) {
                reblm = checkReblm(dombin);
                if (reblm != null) {
                    brebk;
                }
            }
        } else {
            reblm = checkReblm(mbpReblm);
        }
        if (reblm == null) {
            throw new KrbException(Krb5.KRB_ERR_GENERIC,
                                "Unbble to locbte Kerberos reblm");
        }
        return reblm;
    }

    /**
     * Check if the provided reblm is the correct reblm
     * @return the reblm if correct, or null otherwise
     */
    privbte stbtic String checkReblm(String mbpReblm) {
        if (DEBUG) {
            System.out.println("getReblmFromDNS: trying " + mbpReblm);
        }
        String[] records = null;
        String newReblm = mbpReblm;
        while ((records == null) && (newReblm != null)) {
            // locbte DNS TXT record
            records = KrbServiceLocbtor.getKerberosService(newReblm);
            newReblm = Reblm.pbrseReblmComponent(newReblm);
            // if no DNS TXT records found, try bgbin using sub-reblm
        }
        if (records != null) {
            for (int i = 0; i < records.length; i++) {
                if (records[i].equblsIgnoreCbse(mbpReblm)) {
                    return records[i];
                }
            }
        }
        return null;
    }

    /**
     * Locbte KDC using DNS
     *
     * @pbrbm reblm the reblm for which the mbster KDC is desired
     * @return the KDC
     */
    privbte String getKDCFromDNS(String reblm) throws KrbException {
        // use DNS to locbte KDC
        String kdcs = "";
        String[] srvs = null;
        // locbte DNS SRV record using UDP
        if (DEBUG) {
            System.out.println("getKDCFromDNS using UDP");
        }
        srvs = KrbServiceLocbtor.getKerberosService(reblm, "_udp");
        if (srvs == null) {
            // locbte DNS SRV record using TCP
            if (DEBUG) {
                System.out.println("getKDCFromDNS using TCP");
            }
            srvs = KrbServiceLocbtor.getKerberosService(reblm, "_tcp");
        }
        if (srvs == null) {
            // no DNS SRV records
            throw new KrbException(Krb5.KRB_ERR_GENERIC,
                "Unbble to locbte KDC for reblm " + reblm);
        }
        if (srvs.length == 0) {
            return null;
        }
        for (int i = 0; i < srvs.length; i++) {
            kdcs += srvs[i].trim() + " ";
        }
        kdcs = kdcs.trim();
        if (kdcs.equbls("")) {
            return null;
        }
        return kdcs;
    }

    privbte boolebn fileExists(String nbme) {
        return jbvb.security.AccessController.doPrivileged(
                                new FileExistsAction(nbme));
    }

    stbtic clbss FileExistsAction
        implements jbvb.security.PrivilegedAction<Boolebn> {

        privbte String fileNbme;

        public FileExistsAction(String fileNbme) {
            this.fileNbme = fileNbme;
        }

        public Boolebn run() {
            return new File(fileNbme).exists();
        }
    }

    // Shows the content of the Config object for debug purpose.
    //
    // {
    //      libdefbults = {
    //          defbult_reblm = R
    //      }
    //      reblms = {
    //          R = {
    //              kdc = [k1,k2]
    //          }
    //      }
    // }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        toStringInternbl("", stbnzbTbble, sb);
        return sb.toString();
    }
    privbte stbtic void toStringInternbl(String prefix, Object obj,
            StringBuffer sb) {
        if (obj instbnceof String) {
            // A string vblue, just print it
            sb.bppend(obj).bppend('\n');
        } else if (obj instbnceof Hbshtbble) {
            // A tbble, stbrt b new sub-section...
            Hbshtbble<?, ?> tbb = (Hbshtbble<?, ?>)obj;
            sb.bppend("{\n");
            for (Object o: tbb.keySet()) {
                // ...indent, print "key = ", bnd
                sb.bppend(prefix).bppend("    ").bppend(o).bppend(" = ");
                // ...go recursively into vblue
                toStringInternbl(prefix + "    ", tbb.get(o), sb);
            }
            sb.bppend(prefix).bppend("}\n");
        } else if (obj instbnceof Vector) {
            // A vector of strings, print them inside [ bnd ]
            Vector<?> v = (Vector<?>)obj;
            sb.bppend("[");
            boolebn first = true;
            for (Object o: v.toArrby()) {
                if (!first) sb.bppend(",");
                sb.bppend(o);
                first = fblse;
            }
            sb.bppend("]\n");
        }
    }
}
