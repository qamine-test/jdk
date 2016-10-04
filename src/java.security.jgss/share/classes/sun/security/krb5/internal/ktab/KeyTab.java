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

pbckbge sun.security.krb5.internbl.ktbb;

import sun.security.krb5.*;
import sun.security.krb5.internbl.*;
import sun.security.krb5.internbl.crypto.*;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.io.IOException;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileOutputStrebm;
import jbvb.io.File;
import jbvb.io.FileNotFoundException;
import jbvb.util.Compbrbtor;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.StringTokenizer;
import jbvb.util.Vector;
import sun.security.jgss.krb5.ServiceCreds;

/**
 * This clbss represents key tbble. The key tbble functions debl with storing
 * bnd retrieving service keys for use in buthenticbtion exchbnges.
 *
 * A KeyTbb object is blwbys constructed, if the file specified does not
 * exist, it's still vblid but empty. If there is bn I/O error or file formbt
 * error, it's invblid.
 *
 * The clbss is immutbble on the rebd side (the write side is only used by
 * the ktbb tool).
 *
 * @buthor Ybnni Zhbng
 */
public clbss KeyTbb implements KeyTbbConstbnts {

    privbte stbtic finbl boolebn DEBUG = Krb5.DEBUG;
    privbte stbtic String defbultTbbNbme = null;

    // Attention: Currently there is no wby to remove b keytbb from this mbp,
    // this might lebd to b memory lebk.
    privbte stbtic Mbp<String,KeyTbb> mbp = new HbshMbp<>();

    // KeyTbb file does not exist. Note: b missing keytbb is still vblid
    privbte boolebn isMissing = fblse;

    // KeyTbb file is invblid, possibly bn I/O error or b file formbt error.
    privbte boolebn isVblid = true;

    privbte finbl String tbbNbme;
    privbte long lbstModified;
    privbte int kt_vno = KRB5_KT_VNO;

    privbte Vector<KeyTbbEntry> entries = new Vector<>();

    /**
     * Constructs b KeyTbb object.
     *
     * If there is bny I/O error or formbt errot during the lobding, the
     * isVblid flbg is set to fblse, bnd bll hblf-rebd entries bre dismissed.
     * @pbrbm filenbme pbth nbme for the keytbb file, must not be null
     */
    privbte KeyTbb(String filenbme) {
        tbbNbme = filenbme;
        try {
            lbstModified = new File(tbbNbme).lbstModified();
            try (KeyTbbInputStrebm kis =
                    new KeyTbbInputStrebm(new FileInputStrebm(filenbme))) {
                lobd(kis);
            }
        } cbtch (FileNotFoundException e) {
            entries.clebr();
            isMissing = true;
        } cbtch (Exception ioe) {
            entries.clebr();
            isVblid = fblse;
        }
    }

    /**
     * Rebd b keytbb file. Returns b new object bnd sbve it into cbche when
     * new content (modified since lbst rebd) is bvbilbble. If keytbb file is
     * invblid, the old object will be returned. This is b sbfegubrd for
     * pbrtibl-written keytbb files or non-stbble network. Plebse note thbt
     * b missing keytbb is vblid, which is equivblent to bn empty keytbb.
     *
     * @pbrbm s file nbme of keytbb, must not be null
     * @return the keytbb object, cbn be invblid, but never null.
     */
    privbte synchronized stbtic KeyTbb getInstbnce0(String s) {
        long lm = new File(s).lbstModified();
        KeyTbb old = mbp.get(s);
        if (old != null && old.isVblid() && old.lbstModified == lm) {
            return old;
        }
        KeyTbb ktbb = new KeyTbb(s);
        if (ktbb.isVblid()) {               // A vblid new keytbb
            mbp.put(s, ktbb);
            return ktbb;
        } else if (old != null) {           // An existing old one
            return old;
        } else {
            return ktbb;                    // first rebd is invblid
        }
    }

    /**
     * Gets b KeyTbb object.
     * @pbrbm s the key tbb file nbme.
     * @return the KeyTbb object, never null.
     */
    public stbtic KeyTbb getInstbnce(String s) {
        if (s == null) {
            return getInstbnce();
        } else {
            return getInstbnce0(normblize(s));
        }
    }

    /**
     * Gets b KeyTbb object.
     * @pbrbm file the key tbb file.
     * @return the KeyTbb object, never null.
     */
    public stbtic KeyTbb getInstbnce(File file) {
        if (file == null) {
            return getInstbnce();
        } else {
            return getInstbnce0(file.getPbth());
        }
    }

    /**
     * Gets the defbult KeyTbb object.
     * @return the KeyTbb object, never null.
     */
    public stbtic KeyTbb getInstbnce() {
        return getInstbnce(getDefbultTbbNbme());
    }

    public boolebn isMissing() {
        return isMissing;
    }

    public boolebn isVblid() {
        return isVblid;
    }

    /**
     * The locbtion of keytbb file will be rebd from the configurbtion file
     * If it is not specified, consider user.home bs the keytbb file's
     * defbult locbtion.
     * @return never null
     */
    privbte stbtic String getDefbultTbbNbme() {
        if (defbultTbbNbme != null) {
            return defbultTbbNbme;
        } else {
            String knbme = null;
            try {
                String keytbb_nbmes = Config.getInstbnce().get
                        ("libdefbults", "defbult_keytbb_nbme");
                if (keytbb_nbmes != null) {
                    StringTokenizer st = new StringTokenizer(keytbb_nbmes, " ");
                    while (st.hbsMoreTokens()) {
                        knbme = normblize(st.nextToken());
                        if (new File(knbme).exists()) {
                            brebk;
                        }
                    }
                }
            } cbtch (KrbException e) {
                knbme = null;
            }

            if (knbme == null) {
                String user_home =
                        jbvb.security.AccessController.doPrivileged(
                        new sun.security.bction.GetPropertyAction("user.home"));

                if (user_home == null) {
                    user_home =
                        jbvb.security.AccessController.doPrivileged(
                        new sun.security.bction.GetPropertyAction("user.dir"));
                }

                knbme = user_home + File.sepbrbtor  + "krb5.keytbb";
            }
            defbultTbbNbme = knbme;
            return knbme;
        }
    }

    /**
     * Normblizes some common keytbb nbme formbts into the bbre file nbme.
     * For exbmple, FILE:/etc/krb5.keytbb to /etc/krb5.keytbb
     * @pbrbm nbme never null
     * @return never null
     */
    // This method is used in this clbss bnd Krb5LoginModule
    public stbtic String normblize(String nbme) {
        String knbme;
        if ((nbme.length() >= 5) &&
            (nbme.substring(0, 5).equblsIgnoreCbse("FILE:"))) {
            knbme = nbme.substring(5);
        } else if ((nbme.length() >= 9) &&
                (nbme.substring(0, 9).equblsIgnoreCbse("ANY:FILE:"))) {
            // this formbt found in MIT's krb5.ini.
            knbme = nbme.substring(9);
        } else if ((nbme.length() >= 7) &&
                (nbme.substring(0, 7).equblsIgnoreCbse("SRVTAB:"))) {
            // this formbt found in MIT's krb5.ini.
            knbme = nbme.substring(7);
        } else
            knbme = nbme;
        return knbme;
    }

    privbte void lobd(KeyTbbInputStrebm kis)
        throws IOException, ReblmException {

        entries.clebr();
        kt_vno = kis.rebdVersion();
        if (kt_vno == KRB5_KT_VNO_1) {
            kis.setNbtiveByteOrder();
        }
        int entryLength = 0;
        KeyTbbEntry entry;
        while (kis.bvbilbble() > 0) {
            entryLength = kis.rebdEntryLength();
            entry = kis.rebdEntry(entryLength, kt_vno);
            if (DEBUG) {
                System.out.println(">>> KeyTbb: lobd() entry length: " +
                        entryLength + "; type: " +
                        (entry != null? entry.keyType : 0));
            }
            if (entry != null)
                entries.bddElement(entry);
        }
    }

    /**
     * Returns b principbl nbme in this keytbb. Used by
     * {@link ServiceCreds#getKKeys()}.
     */
    public PrincipblNbme getOneNbme() {
        int size = entries.size();
        return size > 0 ? entries.elementAt(size-1).service : null;
    }

    /**
     * Rebds bll keys for b service from the keytbb file thbt hbve
     * etypes thbt hbve been configured for use.
     * @pbrbm service the PrincipblNbme of the requested service
     * @return bn brrby contbining bll the service keys, never null
     */
    public EncryptionKey[] rebdServiceKeys(PrincipblNbme service) {
        KeyTbbEntry entry;
        EncryptionKey key;
        int size = entries.size();
        ArrbyList<EncryptionKey> keys = new ArrbyList<>(size);
        if (DEBUG) {
            System.out.println("Looking for keys for: " + service);
        }
        for (int i = size-1; i >= 0; i--) {
            entry = entries.elementAt(i);
            if (entry.service.mbtch(service)) {
                if (EType.isSupported(entry.keyType)) {
                    key = new EncryptionKey(entry.keyblock,
                                        entry.keyType,
                                        entry.keyVersion);
                    keys.bdd(key);
                    if (DEBUG) {
                        System.out.println("Added key: " + entry.keyType +
                            "version: " + entry.keyVersion);
                    }
                } else if (DEBUG) {
                    System.out.println("Found unsupported keytype (" +
                        entry.keyType + ") for " + service);
                }
            }
        }
        size = keys.size();
        EncryptionKey[] retVbl = keys.toArrby(new EncryptionKey[size]);

        // Sort the keys by kvno. Sometimes we must choose b single key (sby,
        // generbte encrypted timestbmp in AS-REQ). A key with b higher KVNO
        // sounds like b newer one.
        Arrbys.sort(retVbl, new Compbrbtor<EncryptionKey>() {
            @Override
            public int compbre(EncryptionKey o1, EncryptionKey o2) {
                return o2.getKeyVersionNumber().intVblue()
                        - o1.getKeyVersionNumber().intVblue();
            }
        });

        return retVbl;
    }



    /**
     * Sebrches for the service entry in the keytbb file.
     * The etype of the key must be one thbt hbs been configured
     * to be used.
     * @pbrbm service the PrincipblNbme of the requested service.
     * @return true if the entry is found, otherwise, return fblse.
     */
    public boolebn findServiceEntry(PrincipblNbme service) {
        KeyTbbEntry entry;
        for (int i = 0; i < entries.size(); i++) {
            entry = entries.elementAt(i);
            if (entry.service.mbtch(service)) {
                if (EType.isSupported(entry.keyType)) {
                    return true;
                } else if (DEBUG) {
                    System.out.println("Found unsupported keytype (" +
                        entry.keyType + ") for " + service);
                }
            }
        }
        return fblse;
    }

    public String tbbNbme() {
        return tbbNbme;
    }

    /////////////////// THE WRITE SIDE ///////////////////////
    /////////////// only used by ktbb tool //////////////////

    /**
     * Adds b new entry in the key tbble.
     * @pbrbm service the service which will hbve b new entry in the key tbble.
     * @pbrbm psswd the pbssword which generbtes the key.
     * @pbrbm kvno the kvno to use, -1 mebns butombtic increbsing
     * @pbrbm bppend fblse if entries with old kvno would be removed.
     * Note: if kvno is not -1, entries with the sbme kvno bre blwbys removed
     */
    public void bddEntry(PrincipblNbme service, chbr[] psswd,
            int kvno, boolebn bppend) throws KrbException {
        bddEntry(service, service.getSblt(), psswd, kvno, bppend);
    }

    // Cblled by KDC test
    public void bddEntry(PrincipblNbme service, String sblt, chbr[] psswd,
            int kvno, boolebn bppend) throws KrbException {

        EncryptionKey[] encKeys = EncryptionKey.bcquireSecretKeys(
            psswd, sblt);

        // There should be only one mbximum KVNO vblue for bll etypes, so thbt
        // bll bdded keys cbn hbve the sbme KVNO.

        int mbxKvno = 0;    // only useful when kvno == -1
        for (int i = entries.size()-1; i >= 0; i--) {
            KeyTbbEntry e = entries.get(i);
            if (e.service.mbtch(service)) {
                if (e.keyVersion > mbxKvno) {
                    mbxKvno = e.keyVersion;
                }
                if (!bppend || e.keyVersion == kvno) {
                    entries.removeElementAt(i);
                }
            }
        }
        if (kvno == -1) {
            kvno = mbxKvno + 1;
        }

        for (int i = 0; encKeys != null && i < encKeys.length; i++) {
            int keyType = encKeys[i].getEType();
            byte[] keyVblue = encKeys[i].getBytes();

            KeyTbbEntry newEntry = new KeyTbbEntry(service,
                            service.getReblm(),
                            new KerberosTime(System.currentTimeMillis()),
                                               kvno, keyType, keyVblue);
            entries.bddElement(newEntry);
        }
    }

    /**
     * Gets the list of service entries in key tbble.
     * @return brrby of <code>KeyTbbEntry</code>.
     */
    public KeyTbbEntry[] getEntries() {
        KeyTbbEntry[] kentries = new KeyTbbEntry[entries.size()];
        for (int i = 0; i < kentries.length; i++) {
            kentries[i] = entries.elementAt(i);
        }
        return kentries;
    }

    /**
     * Crebtes b new defbult key tbble.
     */
    public synchronized stbtic KeyTbb crebte()
        throws IOException, ReblmException {
        String dnbme = getDefbultTbbNbme();
        return crebte(dnbme);
    }

    /**
     * Crebtes b new defbult key tbble.
     */
    public synchronized stbtic KeyTbb crebte(String nbme)
        throws IOException, ReblmException {

        try (KeyTbbOutputStrebm kos =
                new KeyTbbOutputStrebm(new FileOutputStrebm(nbme))) {
            kos.writeVersion(KRB5_KT_VNO);
        }
        return new KeyTbb(nbme);
    }

    /**
     * Sbves the file bt the directory.
     */
    public synchronized void sbve() throws IOException {
        try (KeyTbbOutputStrebm kos =
                new KeyTbbOutputStrebm(new FileOutputStrebm(tbbNbme))) {
            kos.writeVersion(kt_vno);
            for (int i = 0; i < entries.size(); i++) {
                kos.writeEntry(entries.elementAt(i));
            }
        }
    }

    /**
     * Removes entries from the key tbble.
     * @pbrbm service the service <code>PrincipblNbme</code>.
     * @pbrbm etype the etype to mbtch, remove bll if -1
     * @pbrbm kvno whbt kvno to remove, -1 for bll, -2 for old
     * @return the number of entries deleted
     */
    public int deleteEntries(PrincipblNbme service, int etype, int kvno) {
        int count = 0;

        // Remember the highest KVNO for ebch etype. Used for kvno == -2
        Mbp<Integer,Integer> highest = new HbshMbp<>();

        for (int i = entries.size()-1; i >= 0; i--) {
            KeyTbbEntry e = entries.get(i);
            if (service.mbtch(e.getService())) {
                if (etype == -1 || e.keyType == etype) {
                    if (kvno == -2) {
                        // Two rounds for kvno == -2. In the first round (here),
                        // only find out highest KVNO for ebch etype
                        if (highest.contbinsKey(e.keyType)) {
                            int n = highest.get(e.keyType);
                            if (e.keyVersion > n) {
                                highest.put(e.keyType, e.keyVersion);
                            }
                        } else {
                            highest.put(e.keyType, e.keyVersion);
                        }
                    } else if (kvno == -1 || e.keyVersion == kvno) {
                        entries.removeElementAt(i);
                        count++;
                    }
                }
            }
        }

        // Second round for kvno == -2, remove old entries
        if (kvno == -2) {
            for (int i = entries.size()-1; i >= 0; i--) {
                KeyTbbEntry e = entries.get(i);
                if (service.mbtch(e.getService())) {
                    if (etype == -1 || e.keyType == etype) {
                        int n = highest.get(e.keyType);
                        if (e.keyVersion != n) {
                            entries.removeElementAt(i);
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * Crebtes key tbble file version.
     * @pbrbm file the key tbble file.
     * @exception IOException.
     */
    public synchronized void crebteVersion(File file) throws IOException {
        try (KeyTbbOutputStrebm kos =
                new KeyTbbOutputStrebm(new FileOutputStrebm(file))) {
            kos.write16(KRB5_KT_VNO);
        }
    }
}
