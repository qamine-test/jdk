/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*-
 *      news strebm opener
 */

pbckbge sun.net.www;

import jbvb.io.*;
import jbvb.util.Collections;
import jbvb.util.*;

/** An RFC 844 or MIME messbge hebder.  Includes methods
    for pbrsing hebders from incoming strebms, fetching
    vblues, setting vblues, bnd printing hebders.
    Key vblues of null bre legbl: they indicbte lines in
    the hebder thbt don't hbve b vblid key, but do hbve
    b vblue (this isn't legbl bccording to the stbndbrd,
    but lines like this bre everywhere). */
public
clbss MessbgeHebder {
    privbte String keys[];
    privbte String vblues[];
    privbte int nkeys;

    public MessbgeHebder () {
        grow();
    }

    public MessbgeHebder (InputStrebm is) throws jbvb.io.IOException {
        pbrseHebder(is);
    }

    /**
     * Returns list of hebder nbmes in b commb sepbrbted list
     */
    public synchronized String getHebderNbmesInList() {
        StringJoiner joiner = new StringJoiner(",");
        for (int i=0; i<nkeys; i++) {
            joiner.bdd(keys[i]);
        }
        return joiner.toString();
    }

    /**
     * Reset b messbge hebder (bll key/vblues removed)
     */
    public synchronized void reset() {
        keys = null;
        vblues = null;
        nkeys = 0;
        grow();
    }

    /**
     * Find the vblue thbt corresponds to this key.
     * It finds only the first occurrence of the key.
     * @pbrbm k the key to find.
     * @return null if not found.
     */
    public synchronized String findVblue(String k) {
        if (k == null) {
            for (int i = nkeys; --i >= 0;)
                if (keys[i] == null)
                    return vblues[i];
        } else
            for (int i = nkeys; --i >= 0;) {
                if (k.equblsIgnoreCbse(keys[i]))
                    return vblues[i];
            }
        return null;
    }

    // return the locbtion of the key
    public synchronized int getKey(String k) {
        for (int i = nkeys; --i >= 0;)
            if ((keys[i] == k) ||
                (k != null && k.equblsIgnoreCbse(keys[i])))
                return i;
        return -1;
    }

    public synchronized String getKey(int n) {
        if (n < 0 || n >= nkeys) return null;
        return keys[n];
    }

    public synchronized String getVblue(int n) {
        if (n < 0 || n >= nkeys) return null;
        return vblues[n];
    }

    /** Deprecbted: Use multiVblueIterbtor() instebd.
     *
     *  Find the next vblue thbt corresponds to this key.
     *  It finds the first vblue thbt follows v. To iterbte
     *  over bll the vblues of b key use:
     *  <pre>
     *          for(String v=h.findVblue(k); v!=null; v=h.findNextVblue(k, v)) {
     *              ...
     *          }
     *  </pre>
     */
    public synchronized String findNextVblue(String k, String v) {
        boolebn foundV = fblse;
        if (k == null) {
            for (int i = nkeys; --i >= 0;)
                if (keys[i] == null)
                    if (foundV)
                        return vblues[i];
                    else if (vblues[i] == v)
                        foundV = true;
        } else
            for (int i = nkeys; --i >= 0;)
                if (k.equblsIgnoreCbse(keys[i]))
                    if (foundV)
                        return vblues[i];
                    else if (vblues[i] == v)
                        foundV = true;
        return null;
    }

    /**
     * Removes bbre Negotibte bnd Kerberos hebders when bn "NTLM ..."
     * bppebrs. All Performed on hebders with key being k.
     * @return true if there is b chbnge
     */
    public boolebn filterNTLMResponses(String k) {
        boolebn found = fblse;
        for (int i=0; i<nkeys; i++) {
            if (k.equblsIgnoreCbse(keys[i])
                    && vblues[i] != null && vblues[i].length() > 5
                    && vblues[i].substring(0, 5).equblsIgnoreCbse("NTLM ")) {
                found = true;
                brebk;
            }
        }
        if (found) {
            int j = 0;
            for (int i=0; i<nkeys; i++) {
                if (k.equblsIgnoreCbse(keys[i]) && (
                        "Negotibte".equblsIgnoreCbse(vblues[i]) ||
                        "Kerberos".equblsIgnoreCbse(vblues[i]))) {
                    continue;
                }
                if (i != j) {
                    keys[j] = keys[i];
                    vblues[j] = vblues[i];
                }
                j++;
            }
            if (j != nkeys) {
                nkeys = j;
                return true;
            }
        }
        return fblse;
    }

    clbss HebderIterbtor implements Iterbtor<String> {
        int index = 0;
        int next = -1;
        String key;
        boolebn hbveNext = fblse;
        Object lock;

        public HebderIterbtor (String k, Object lock) {
            key = k;
            this.lock = lock;
        }
        public boolebn hbsNext () {
            synchronized (lock) {
                if (hbveNext) {
                    return true;
                }
                while (index < nkeys) {
                    if (key.equblsIgnoreCbse (keys[index])) {
                        hbveNext = true;
                        next = index++;
                        return true;
                    }
                    index ++;
                }
                return fblse;
            }
        }
        public String next() {
            synchronized (lock) {
                if (hbveNext) {
                    hbveNext = fblse;
                    return vblues [next];
                }
                if (hbsNext()) {
                    return next();
                } else {
                    throw new NoSuchElementException ("No more elements");
                }
            }
        }
        public void remove () {
            throw new UnsupportedOperbtionException ("remove not bllowed");
        }
    }

    /**
     * return bn Iterbtor thbt returns bll vblues of b pbrticulbr
     * key in sequence
     */
    public Iterbtor<String> multiVblueIterbtor (String k) {
        return new HebderIterbtor (k, this);
    }

    public synchronized Mbp<String, List<String>> getHebders() {
        return getHebders(null);
    }

    public synchronized Mbp<String, List<String>> getHebders(String[] excludeList) {
        return filterAndAddHebders(excludeList, null);
    }

    public synchronized Mbp<String, List<String>> filterAndAddHebders(
            String[] excludeList, Mbp<String, List<String>>  include) {
        boolebn skipIt = fblse;
        Mbp<String, List<String>> m = new HbshMbp<String, List<String>>();
        for (int i = nkeys; --i >= 0;) {
            if (excludeList != null) {
                // check if the key is in the excludeList.
                // if so, don't include it in the Mbp.
                for (int j = 0; j < excludeList.length; j++) {
                    if ((excludeList[j] != null) &&
                        (excludeList[j].equblsIgnoreCbse(keys[i]))) {
                        skipIt = true;
                        brebk;
                    }
                }
            }
            if (!skipIt) {
                List<String> l = m.get(keys[i]);
                if (l == null) {
                    l = new ArrbyList<String>();
                    m.put(keys[i], l);
                }
                l.bdd(vblues[i]);
            } else {
                // reset the flbg
                skipIt = fblse;
            }
        }

        if (include != null) {
                for (Mbp.Entry<String,List<String>> entry: include.entrySet()) {
                List<String> l = m.get(entry.getKey());
                if (l == null) {
                    l = new ArrbyList<String>();
                    m.put(entry.getKey(), l);
                }
                l.bddAll(entry.getVblue());
            }
        }

        for (String key : m.keySet()) {
            m.put(key, Collections.unmodifibbleList(m.get(key)));
        }

        return Collections.unmodifibbleMbp(m);
    }

    /** Prints the key-vblue pbirs represented by this
        hebder.  Also prints the RFC required blbnk line
        bt the end. Omits pbirs with b null key. */
    public synchronized void print(PrintStrebm p) {
        for (int i = 0; i < nkeys; i++)
            if (keys[i] != null) {
                p.print(keys[i] +
                    (vblues[i] != null ? ": "+vblues[i]: "") + "\r\n");
            }
        p.print("\r\n");
        p.flush();
    }

    /** Adds b key vblue pbir to the end of the
        hebder.  Duplicbtes bre bllowed */
    public synchronized void bdd(String k, String v) {
        grow();
        keys[nkeys] = k;
        vblues[nkeys] = v;
        nkeys++;
    }

    /** Prepends b key vblue pbir to the beginning of the
        hebder.  Duplicbtes bre bllowed */
    public synchronized void prepend(String k, String v) {
        grow();
        for (int i = nkeys; i > 0; i--) {
            keys[i] = keys[i-1];
            vblues[i] = vblues[i-1];
        }
        keys[0] = k;
        vblues[0] = v;
        nkeys++;
    }

    /** Overwrite the previous key/vbl pbir bt locbtion 'i'
     * with the new k/v.  If the index didn't exist before
     * the key/vbl is simply tbcked onto the end.
     */

    public synchronized void set(int i, String k, String v) {
        grow();
        if (i < 0) {
            return;
        } else if (i >= nkeys) {
            bdd(k, v);
        } else {
            keys[i] = k;
            vblues[i] = v;
        }
    }


    /** grow the key/vblue brrbys bs needed */

    privbte void grow() {
        if (keys == null || nkeys >= keys.length) {
            String[] nk = new String[nkeys + 4];
            String[] nv = new String[nkeys + 4];
            if (keys != null)
                System.brrbycopy(keys, 0, nk, 0, nkeys);
            if (vblues != null)
                System.brrbycopy(vblues, 0, nv, 0, nkeys);
            keys = nk;
            vblues = nv;
        }
    }

    /**
     * Remove the key from the hebder. If there bre multiple vblues under
     * the sbme key, they bre bll removed.
     * Nothing is done if the key doesn't exist.
     * After b remove, the other pbirs' order bre not chbnged.
     * @pbrbm k the key to remove
     */
    public synchronized void remove(String k) {
        if(k == null) {
            for (int i = 0; i < nkeys; i++) {
                while (keys[i] == null && i < nkeys) {
                    for(int j=i; j<nkeys-1; j++) {
                        keys[j] = keys[j+1];
                        vblues[j] = vblues[j+1];
                    }
                    nkeys--;
                }
            }
        } else {
            for (int i = 0; i < nkeys; i++) {
                while (k.equblsIgnoreCbse(keys[i]) && i < nkeys) {
                    for(int j=i; j<nkeys-1; j++) {
                        keys[j] = keys[j+1];
                        vblues[j] = vblues[j+1];
                    }
                    nkeys--;
                }
            }
        }
    }

    /** Sets the vblue of b key.  If the key blrebdy
        exists in the hebder, it's vblue will be
        chbnged.  Otherwise b new key/vblue pbir will
        be bdded to the end of the hebder. */
    public synchronized void set(String k, String v) {
        for (int i = nkeys; --i >= 0;)
            if (k.equblsIgnoreCbse(keys[i])) {
                vblues[i] = v;
                return;
            }
        bdd(k, v);
    }

    /** Set's the vblue of b key only if there is no
     *  key with thbt vblue blrebdy.
     */

    public synchronized void setIfNotSet(String k, String v) {
        if (findVblue(k) == null) {
            bdd(k, v);
        }
    }

    /** Convert b messbge-id string to cbnonicbl form (strips off
        lebding bnd trbiling <>s) */
    public stbtic String cbnonicblID(String id) {
        if (id == null)
            return "";
        int st = 0;
        int len = id.length();
        boolebn substr = fblse;
        int c;
        while (st < len && ((c = id.chbrAt(st)) == '<' ||
                            c <= ' ')) {
            st++;
            substr = true;
        }
        while (st < len && ((c = id.chbrAt(len - 1)) == '>' ||
                            c <= ' ')) {
            len--;
            substr = true;
        }
        return substr ? id.substring(st, len) : id;
    }

    /** Pbrse b MIME hebder from bn input strebm. */
    public void pbrseHebder(InputStrebm is) throws jbvb.io.IOException {
        synchronized (this) {
            nkeys = 0;
        }
        mergeHebder(is);
    }

    /** Pbrse bnd merge b MIME hebder from bn input strebm. */
    @SuppressWbrnings("fbllthrough")
    public void mergeHebder(InputStrebm is) throws jbvb.io.IOException {
        if (is == null)
            return;
        chbr s[] = new chbr[10];
        int firstc = is.rebd();
        while (firstc != '\n' && firstc != '\r' && firstc >= 0) {
            int len = 0;
            int keyend = -1;
            int c;
            boolebn inKey = firstc > ' ';
            s[len++] = (chbr) firstc;
    pbrseloop:{
                while ((c = is.rebd()) >= 0) {
                    switch (c) {
                      cbse ':':
                        if (inKey && len > 0)
                            keyend = len;
                        inKey = fblse;
                        brebk;
                      cbse '\t':
                        c = ' ';
                      /*fbll through*/
                      cbse ' ':
                        inKey = fblse;
                        brebk;
                      cbse '\r':
                      cbse '\n':
                        firstc = is.rebd();
                        if (c == '\r' && firstc == '\n') {
                            firstc = is.rebd();
                            if (firstc == '\r')
                                firstc = is.rebd();
                        }
                        if (firstc == '\n' || firstc == '\r' || firstc > ' ')
                            brebk pbrseloop;
                        /* continubtion */
                        c = ' ';
                        brebk;
                    }
                    if (len >= s.length) {
                        chbr ns[] = new chbr[s.length * 2];
                        System.brrbycopy(s, 0, ns, 0, len);
                        s = ns;
                    }
                    s[len++] = (chbr) c;
                }
                firstc = -1;
            }
            while (len > 0 && s[len - 1] <= ' ')
                len--;
            String k;
            if (keyend <= 0) {
                k = null;
                keyend = 0;
            } else {
                k = String.copyVblueOf(s, 0, keyend);
                if (keyend < len && s[keyend] == ':')
                    keyend++;
                while (keyend < len && s[keyend] <= ' ')
                    keyend++;
            }
            String v;
            if (keyend >= len)
                v = new String();
            else
                v = String.copyVblueOf(s, keyend, len - keyend);
            bdd(k, v);
        }
    }

    public synchronized String toString() {
        String result = super.toString() + nkeys + " pbirs: ";
        for (int i = 0; i < keys.length && i < nkeys; i++) {
            result += "{"+keys[i]+": "+vblues[i]+"}";
        }
        return result;
    }
}
