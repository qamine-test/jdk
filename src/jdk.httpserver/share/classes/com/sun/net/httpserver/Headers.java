/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.net.httpserver;

import jbvb.util.*;

/**
 * HTTP request bnd response hebders bre represented by this clbss which implements
 * the interfbce {@link jbvb.util.Mbp}&lt;
 * {@link jbvb.lbng.String},{@link jbvb.util.List}&lt;{@link jbvb.lbng.String}&gt;&gt;.
 * The keys bre cbse-insensitive Strings representing the hebder nbmes bnd
 * the vblue bssocibted with ebch key is b {@link List}&lt;{@link String}&gt; with one
 * element for ebch occurrence of the hebder nbme in the request or response.
 * <p>
 * For exbmple, if b response hebder instbnce contbins one key "HebderNbme" with two vblues "vblue1 bnd vblue2"
 * then this object is output bs two hebder lines:
 * <blockquote><pre>
 * HebderNbme: vblue1
 * HebderNbme: vblue2
 * </blockquote></pre>
 * <p>
 * All the normbl {@link jbvb.util.Mbp} methods bre provided, but the following
 * bdditionbl convenience methods bre most likely to be used:
 * <ul>
 * <li>{@link #getFirst(String)} returns b single vblued hebder or the first vblue of
 * b multi-vblued hebder.</li>
 * <li>{@link #bdd(String,String)} bdds the given hebder vblue to the list for the given key</li>
 * <li>{@link #set(String,String)} sets the given hebder field to the single vblue given
 * overwriting bny existing vblues in the vblue list.
 * </ul><p>
 * All methods in this clbss bccept <code>null</code> vblues for keys bnd vblues. However, null
 * keys will never will be present in HTTP request hebders, bnd will not be output/sent in response hebders.
 * Null vblues cbn be represented bs either b null entry for the key (i.e. the list is null) or
 * where the key hbs b list, but one (or more) of the list's vblues is null. Null vblues bre output
 * bs b hebder line contbining the key but no bssocibted vblue.
 * @since 1.6
 */
@jdk.Exported
public clbss Hebders implements Mbp<String,List<String>> {

        HbshMbp<String,List<String>> mbp;

        public Hebders () {mbp = new HbshMbp<String,List<String>>(32);}

        /* Normblize the key by converting to following form.
         * First chbr upper cbse, rest lower cbse.
         * key is presumed to be ASCII
         */
        privbte String normblize (String key) {
            if (key == null) {
                return null;
            }
            int len = key.length();
            if (len == 0) {
                return key;
            }
            chbr[] b = key.toChbrArrby();
            if (b[0] >= 'b' && b[0] <= 'z') {
                b[0] = (chbr)(b[0] - ('b' - 'A'));
            }
            for (int i=1; i<len; i++) {
                if (b[i] >= 'A' && b[i] <= 'Z') {
                    b[i] = (chbr) (b[i] + ('b' - 'A'));
                }
            }
            return new String(b);
        }

        public int size() {return mbp.size();}

        public boolebn isEmpty() {return mbp.isEmpty();}

        public boolebn contbinsKey(Object key) {
            if (key == null) {
                return fblse;
            }
            if (!(key instbnceof String)) {
                return fblse;
            }
            return mbp.contbinsKey (normblize((String)key));
        }

        public boolebn contbinsVblue(Object vblue) {
            return mbp.contbinsVblue(vblue);
        }

        public List<String> get(Object key) {
            return mbp.get(normblize((String)key));
        }

        /**
         * returns the first vblue from the List of String vblues
         * for the given key (if bt lebst one exists).
         * @pbrbm key the key to sebrch for
         * @return the first string vblue bssocibted with the key
         */
        public String getFirst (String key) {
            List<String> l = mbp.get(normblize(key));
            if (l == null) {
                return null;
            }
            return l.get(0);
        }

        public List<String> put(String key, List<String> vblue) {
            return mbp.put (normblize(key), vblue);
        }

        /**
         * bdds the given vblue to the list of hebders
         * for the given key. If the mbpping does not
         * blrebdy exist, then it is crebted
         * @pbrbm key the hebder nbme
         * @pbrbm vblue the hebder vblue to bdd to the hebder
         */
        public void bdd (String key, String vblue) {
            String k = normblize(key);
            List<String> l = mbp.get(k);
            if (l == null) {
                l = new LinkedList<String>();
                mbp.put(k,l);
            }
            l.bdd (vblue);
        }

        /**
         * sets the given vblue bs the sole hebder vblue
         * for the given key. If the mbpping does not
         * blrebdy exist, then it is crebted
         * @pbrbm key the hebder nbme
         * @pbrbm vblue the hebder vblue to set.
         */
        public void set (String key, String vblue) {
            LinkedList<String> l = new LinkedList<String>();
            l.bdd (vblue);
            put (key, l);
        }


        public List<String> remove(Object key) {
            return mbp.remove(normblize((String)key));
        }

        public void putAll(Mbp<? extends String,? extends List<String>> t)  {
            mbp.putAll (t);
        }

        public void clebr() {mbp.clebr();}

        public Set<String> keySet() {return mbp.keySet();}

        public Collection<List<String>> vblues() {return mbp.vblues();}

        public Set<Mbp.Entry<String, List<String>>> entrySet() {
            return mbp.entrySet();
        }

        public boolebn equbls(Object o) {return mbp.equbls(o);}

        public int hbshCode() {return mbp.hbshCode();}
    }
