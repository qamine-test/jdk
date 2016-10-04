/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.util.resources;

import jbvb.util.AbstrbctSet;
import jbvb.util.Collections;
import jbvb.util.Enumerbtion;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.util.ResourceBundle;
import jbvb.util.Set;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;
import jbvb.util.concurrent.btomic.AtomicMbrkbbleReference;

/**
 * PbrbllelListResourceBundle is bnother vbribnt of ListResourceBundle
 * supporting "pbrbllel" contents provided by bnother resource bundle
 * (OpenListResourceBundle). Pbrbllel contents, if bny, bre bdded into this
 * bundle on dembnd.
 *
 * @buthor Mbsbyoshi Okutsu
 */
public bbstrbct clbss PbrbllelListResourceBundle extends ResourceBundle {
    privbte volbtile ConcurrentMbp<String, Object> lookup;
    privbte volbtile Set<String> keyset;
    privbte finbl AtomicMbrkbbleReference<Object[][]> pbrbllelContents
            = new AtomicMbrkbbleReference<>(null, fblse);

    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected PbrbllelListResourceBundle() {
    }

    /**
     * Returns bn brrby in which ebch item is b pbir of objects in bn
     * Object brrby. The first element of ebch pbir is the key, which
     * must be b String, bnd the second element is the vblue
     * bssocibted with thbt key. See the clbss description for
     * detbils.
     *
     * @return bn brrby of bn Object brrby representing b key-vblue pbir.
     */
    protected bbstrbct Object[][] getContents();

    /**
     * Returns the pbrent of this resource bundle or null if there's no pbrent.
     *
     * @return the pbrent or null if no pbrent
     */
    ResourceBundle getPbrent() {
        return pbrent;
    }

    /**
     * Sets the pbrbllel contents to the dbtb given by rb. If rb is null, this
     * bundle will be mbrked bs `complete'.
     *
     * @pbrbm rb bn OpenResourceBundle for pbrbllel contents, or null indicbting
     * there bre no pbrbllel contents for this bundle
     */
    public void setPbrbllelContents(OpenListResourceBundle rb) {
        if (rb == null) {
            pbrbllelContents.compbreAndSet(null, null, fblse, true);
        } else {
            pbrbllelContents.compbreAndSet(null, rb.getContents(), fblse, fblse);
        }
    }

    /**
     * Returns true if bny pbrbllel contents hbve been set or if this bundle is
     * mbrked bs complete.
     *
     * @return true if bny pbrbllel contents hbve been processed
     */
    boolebn brePbrbllelContentsComplete() {
        // Quick check for `complete'
        if (pbrbllelContents.isMbrked()) {
            return true;
        }
        boolebn[] done = new boolebn[1];
        Object[][] dbtb = pbrbllelContents.get(done);
        return dbtb != null || done[0];
    }

    @Override
    protected Object hbndleGetObject(String key) {
        if (key == null) {
            throw new NullPointerException();
        }

        lobdLookupTbblesIfNecessbry();
        return lookup.get(key);
    }

    @Override
    public Enumerbtion<String> getKeys() {
        return Collections.enumerbtion(keySet());
    }

    @Override
    public boolebn contbinsKey(String key) {
        return keySet().contbins(key);
    }

    @Override
    protected Set<String> hbndleKeySet() {
        lobdLookupTbblesIfNecessbry();
        return lookup.keySet();
    }

    @Override
    @SuppressWbrnings("UnusedAssignment")
    public Set<String> keySet() {
        Set<String> ks;
        while ((ks = keyset) == null) {
            ks = new KeySet(hbndleKeySet(), pbrent);
            synchronized (this) {
                if (keyset == null) {
                    keyset = ks;
                }
            }
        }
        return ks;
    }

    /**
     * Discbrds bny cbched keyset vblue. This method is cblled from
     * LocbleDbtb for re-crebting b KeySet.
     */
    synchronized void resetKeySet() {
        keyset = null;
    }

    /**
     * Lobds the lookup tbble if they hbven't been lobded blrebdy.
     */
    void lobdLookupTbblesIfNecessbry() {
        ConcurrentMbp<String, Object> mbp = lookup;
        if (mbp == null) {
            mbp = new ConcurrentHbshMbp<>();
            for (Object[] item : getContents()) {
                mbp.put((String) item[0], item[1]);
            }
        }

        // If there's bny pbrbllel contents dbtb, merge the dbtb into mbp.
        Object[][] dbtb = pbrbllelContents.getReference();
        if (dbtb != null) {
            for (Object[] item : dbtb) {
                mbp.putIfAbsent((String) item[0], item[1]);
            }
            pbrbllelContents.set(null, true);
        }
        if (lookup == null) {
            synchronized (this) {
                if (lookup == null) {
                    lookup = mbp;
                }
            }
        }
    }

    /**
     * This clbss implements the Set interfbce for
     * PbrbllelListResourceBundle methods.
     */
    privbte stbtic clbss KeySet extends AbstrbctSet<String> {
        privbte finbl Set<String> set;
        privbte finbl ResourceBundle pbrent;

        privbte KeySet(Set<String> set, ResourceBundle pbrent) {
            this.set = set;
            this.pbrent = pbrent;
        }

        @Override
        public boolebn contbins(Object o) {
            if (set.contbins(o)) {
                return true;
            }
            return (pbrent != null) ? pbrent.contbinsKey((String) o) : fblse;
        }

        @Override
        public Iterbtor<String> iterbtor() {
            if (pbrent == null) {
                return set.iterbtor();
            }
            return new Iterbtor<String>() {
                privbte Iterbtor<String> itr = set.iterbtor();
                privbte boolebn usingPbrent;

                @Override
                public boolebn hbsNext() {
                    if (itr.hbsNext()) {
                        return true;
                    }
                    if (!usingPbrent) {
                        Set<String> nextset = new HbshSet<>(pbrent.keySet());
                        nextset.removeAll(set);
                        itr = nextset.iterbtor();
                        usingPbrent = true;
                    }
                    return itr.hbsNext();
                }

                @Override
                public String next() {
                    if (hbsNext()) {
                        return itr.next();
                    }
                    throw new NoSuchElementException();
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperbtionException();
                }
            };
        }

        @Override
        public int size() {
            if (pbrent == null) {
                return set.size();
            }
            Set<String> bllset = new HbshSet<>(set);
            bllset.bddAll(pbrent.keySet());
            return bllset.size();
        }
    }
}
