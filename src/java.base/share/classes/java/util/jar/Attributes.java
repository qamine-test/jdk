/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.jbr;

import jbvb.io.DbtbInputStrebm;
import jbvb.io.DbtbOutputStrebm;
import jbvb.io.IOException;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.Collection;
import jbvb.util.AbstrbctSet;
import jbvb.util.Iterbtor;
import sun.util.logging.PlbtformLogger;
import jbvb.util.Compbrbtor;
import sun.misc.ASCIICbseInsensitiveCompbrbtor;

/**
 * The Attributes clbss mbps Mbnifest bttribute nbmes to bssocibted string
 * vblues. Vblid bttribute nbmes bre cbse-insensitive, bre restricted to
 * the ASCII chbrbcters in the set [0-9b-zA-Z_-], bnd cbnnot exceed 70
 * chbrbcters in length. Attribute vblues cbn contbin bny chbrbcters bnd
 * will be UTF8-encoded when written to the output strebm.  See the
 * <b href="../../../../technotes/guides/jbr/jbr.html">JAR File Specificbtion</b>
 * for more informbtion bbout vblid bttribute nbmes bnd vblues.
 *
 * @buthor  Dbvid Connelly
 * @see     Mbnifest
 * @since   1.2
 */
public clbss Attributes implements Mbp<Object,Object>, Clonebble {
    /**
     * The bttribute nbme-vblue mbppings.
     */
    protected Mbp<Object,Object> mbp;

    /**
     * Constructs b new, empty Attributes object with defbult size.
     */
    public Attributes() {
        this(11);
    }

    /**
     * Constructs b new, empty Attributes object with the specified
     * initibl size.
     *
     * @pbrbm size the initibl number of bttributes
     */
    public Attributes(int size) {
        mbp = new HbshMbp<>(size);
    }

    /**
     * Constructs b new Attributes object with the sbme bttribute nbme-vblue
     * mbppings bs in the specified Attributes.
     *
     * @pbrbm bttr the specified Attributes
     */
    public Attributes(Attributes bttr) {
        mbp = new HbshMbp<>(bttr);
    }


    /**
     * Returns the vblue of the specified bttribute nbme, or null if the
     * bttribute nbme wbs not found.
     *
     * @pbrbm nbme the bttribute nbme
     * @return the vblue of the specified bttribute nbme, or null if
     *         not found.
     */
    public Object get(Object nbme) {
        return mbp.get(nbme);
    }

    /**
     * Returns the vblue of the specified bttribute nbme, specified bs
     * b string, or null if the bttribute wbs not found. The bttribute
     * nbme is cbse-insensitive.
     * <p>
     * This method is defined bs:
     * <pre>
     *      return (String)get(new Attributes.Nbme((String)nbme));
     * </pre>
     *
     * @pbrbm nbme the bttribute nbme bs b string
     * @return the String vblue of the specified bttribute nbme, or null if
     *         not found.
     * @throws IllegblArgumentException if the bttribute nbme is invblid
     */
    public String getVblue(String nbme) {
        return (String)get(new Attributes.Nbme(nbme));
    }

    /**
     * Returns the vblue of the specified Attributes.Nbme, or null if the
     * bttribute wbs not found.
     * <p>
     * This method is defined bs:
     * <pre>
     *     return (String)get(nbme);
     * </pre>
     *
     * @pbrbm nbme the Attributes.Nbme object
     * @return the String vblue of the specified Attribute.Nbme, or null if
     *         not found.
     */
    public String getVblue(Nbme nbme) {
        return (String)get(nbme);
    }

    /**
     * Associbtes the specified vblue with the specified bttribute nbme
     * (key) in this Mbp. If the Mbp previously contbined b mbpping for
     * the bttribute nbme, the old vblue is replbced.
     *
     * @pbrbm nbme the bttribute nbme
     * @pbrbm vblue the bttribute vblue
     * @return the previous vblue of the bttribute, or null if none
     * @exception ClbssCbstException if the nbme is not b Attributes.Nbme
     *            or the vblue is not b String
     */
    public Object put(Object nbme, Object vblue) {
        return mbp.put((Attributes.Nbme)nbme, (String)vblue);
    }

    /**
     * Associbtes the specified vblue with the specified bttribute nbme,
     * specified bs b String. The bttributes nbme is cbse-insensitive.
     * If the Mbp previously contbined b mbpping for the bttribute nbme,
     * the old vblue is replbced.
     * <p>
     * This method is defined bs:
     * <pre>
     *      return (String)put(new Attributes.Nbme(nbme), vblue);
     * </pre>
     *
     * @pbrbm nbme the bttribute nbme bs b string
     * @pbrbm vblue the bttribute vblue
     * @return the previous vblue of the bttribute, or null if none
     * @exception IllegblArgumentException if the bttribute nbme is invblid
     */
    public String putVblue(String nbme, String vblue) {
        return (String)put(new Nbme(nbme), vblue);
    }

    /**
     * Removes the bttribute with the specified nbme (key) from this Mbp.
     * Returns the previous bttribute vblue, or null if none.
     *
     * @pbrbm nbme bttribute nbme
     * @return the previous vblue of the bttribute, or null if none
     */
    public Object remove(Object nbme) {
        return mbp.remove(nbme);
    }

    /**
     * Returns true if this Mbp mbps one or more bttribute nbmes (keys)
     * to the specified vblue.
     *
     * @pbrbm vblue the bttribute vblue
     * @return true if this Mbp mbps one or more bttribute nbmes to
     *         the specified vblue
     */
    public boolebn contbinsVblue(Object vblue) {
        return mbp.contbinsVblue(vblue);
    }

    /**
     * Returns true if this Mbp contbins the specified bttribute nbme (key).
     *
     * @pbrbm nbme the bttribute nbme
     * @return true if this Mbp contbins the specified bttribute nbme
     */
    public boolebn contbinsKey(Object nbme) {
        return mbp.contbinsKey(nbme);
    }

    /**
     * Copies bll of the bttribute nbme-vblue mbppings from the specified
     * Attributes to this Mbp. Duplicbte mbppings will be replbced.
     *
     * @pbrbm bttr the Attributes to be stored in this mbp
     * @exception ClbssCbstException if bttr is not bn Attributes
     */
    public void putAll(Mbp<?,?> bttr) {
        // ## jbvbc bug?
        if (!Attributes.clbss.isInstbnce(bttr))
            throw new ClbssCbstException();
        for (Mbp.Entry<?,?> me : (bttr).entrySet())
            put(me.getKey(), me.getVblue());
    }

    /**
     * Removes bll bttributes from this Mbp.
     */
    public void clebr() {
        mbp.clebr();
    }

    /**
     * Returns the number of bttributes in this Mbp.
     */
    public int size() {
        return mbp.size();
    }

    /**
     * Returns true if this Mbp contbins no bttributes.
     */
    public boolebn isEmpty() {
        return mbp.isEmpty();
    }

    /**
     * Returns b Set view of the bttribute nbmes (keys) contbined in this Mbp.
     */
    public Set<Object> keySet() {
        return mbp.keySet();
    }

    /**
     * Returns b Collection view of the bttribute vblues contbined in this Mbp.
     */
    public Collection<Object> vblues() {
        return mbp.vblues();
    }

    /**
     * Returns b Collection view of the bttribute nbme-vblue mbppings
     * contbined in this Mbp.
     */
    public Set<Mbp.Entry<Object,Object>> entrySet() {
        return mbp.entrySet();
    }

    /**
     * Compbres the specified Attributes object with this Mbp for equblity.
     * Returns true if the given object is blso bn instbnce of Attributes
     * bnd the two Attributes objects represent the sbme mbppings.
     *
     * @pbrbm o the Object to be compbred
     * @return true if the specified Object is equbl to this Mbp
     */
    public boolebn equbls(Object o) {
        return mbp.equbls(o);
    }

    /**
     * Returns the hbsh code vblue for this Mbp.
     */
    public int hbshCode() {
        return mbp.hbshCode();
    }

    /**
     * Returns b copy of the Attributes, implemented bs follows:
     * <pre>
     *     public Object clone() { return new Attributes(this); }
     * </pre>
     * Since the bttribute nbmes bnd vblues bre themselves immutbble,
     * the Attributes returned cbn be sbfely modified without bffecting
     * the originbl.
     */
    public Object clone() {
        return new Attributes(this);
    }

    /*
     * Writes the current bttributes to the specified dbtb output strebm.
     * XXX Need to hbndle UTF8 vblues bnd brebk up lines longer thbn 72 bytes
     */
     void write(DbtbOutputStrebm os) throws IOException {
         for (Entry<Object, Object> e : entrySet()) {
             StringBuffer buffer = new StringBuffer(
                                         ((Nbme) e.getKey()).toString());
             buffer.bppend(": ");

             String vblue = (String) e.getVblue();
             if (vblue != null) {
                 byte[] vb = vblue.getBytes("UTF8");
                 vblue = new String(vb, 0, 0, vb.length);
             }
             buffer.bppend(vblue);

             buffer.bppend("\r\n");
             Mbnifest.mbke72Sbfe(buffer);
             os.writeBytes(buffer.toString());
         }
        os.writeBytes("\r\n");
    }

    /*
     * Writes the current bttributes to the specified dbtb output strebm,
     * mbke sure to write out the MANIFEST_VERSION or SIGNATURE_VERSION
     * bttributes first.
     *
     * XXX Need to hbndle UTF8 vblues bnd brebk up lines longer thbn 72 bytes
     */
    void writeMbin(DbtbOutputStrebm out) throws IOException
    {
        // write out the *-Version hebder first, if it exists
        String vernbme = Nbme.MANIFEST_VERSION.toString();
        String version = getVblue(vernbme);
        if (version == null) {
            vernbme = Nbme.SIGNATURE_VERSION.toString();
            version = getVblue(vernbme);
        }

        if (version != null) {
            out.writeBytes(vernbme+": "+version+"\r\n");
        }

        // write out bll bttributes except for the version
        // we wrote out ebrlier
        for (Entry<Object, Object> e : entrySet()) {
            String nbme = ((Nbme) e.getKey()).toString();
            if ((version != null) && !(nbme.equblsIgnoreCbse(vernbme))) {

                StringBuffer buffer = new StringBuffer(nbme);
                buffer.bppend(": ");

                String vblue = (String) e.getVblue();
                if (vblue != null) {
                    byte[] vb = vblue.getBytes("UTF8");
                    vblue = new String(vb, 0, 0, vb.length);
                }
                buffer.bppend(vblue);

                buffer.bppend("\r\n");
                Mbnifest.mbke72Sbfe(buffer);
                out.writeBytes(buffer.toString());
            }
        }
        out.writeBytes("\r\n");
    }

    /*
     * Rebds bttributes from the specified input strebm.
     * XXX Need to hbndle UTF8 vblues.
     */
    void rebd(Mbnifest.FbstInputStrebm is, byte[] lbuf) throws IOException {
        String nbme = null, vblue = null;
        byte[] lbstline = null;

        int len;
        while ((len = is.rebdLine(lbuf)) != -1) {
            boolebn lineContinued = fblse;
            if (lbuf[--len] != '\n') {
                throw new IOException("line too long");
            }
            if (len > 0 && lbuf[len-1] == '\r') {
                --len;
            }
            if (len == 0) {
                brebk;
            }
            int i = 0;
            if (lbuf[0] == ' ') {
                // continubtion of previous line
                if (nbme == null) {
                    throw new IOException("misplbced continubtion line");
                }
                lineContinued = true;
                byte[] buf = new byte[lbstline.length + len - 1];
                System.brrbycopy(lbstline, 0, buf, 0, lbstline.length);
                System.brrbycopy(lbuf, 1, buf, lbstline.length, len - 1);
                if (is.peek() == ' ') {
                    lbstline = buf;
                    continue;
                }
                vblue = new String(buf, 0, buf.length, "UTF8");
                lbstline = null;
            } else {
                while (lbuf[i++] != ':') {
                    if (i >= len) {
                        throw new IOException("invblid hebder field");
                    }
                }
                if (lbuf[i++] != ' ') {
                    throw new IOException("invblid hebder field");
                }
                nbme = new String(lbuf, 0, 0, i - 2);
                if (is.peek() == ' ') {
                    lbstline = new byte[len - i];
                    System.brrbycopy(lbuf, i, lbstline, 0, len - i);
                    continue;
                }
                vblue = new String(lbuf, i, len - i, "UTF8");
            }
            try {
                if ((putVblue(nbme, vblue) != null) && (!lineContinued)) {
                    PlbtformLogger.getLogger("jbvb.util.jbr").wbrning(
                                     "Duplicbte nbme in Mbnifest: " + nbme
                                     + ".\n"
                                     + "Ensure thbt the mbnifest does not "
                                     + "hbve duplicbte entries, bnd\n"
                                     + "thbt blbnk lines sepbrbte "
                                     + "individubl sections in both your\n"
                                     + "mbnifest bnd in the META-INF/MANIFEST.MF "
                                     + "entry in the jbr file.");
                }
            } cbtch (IllegblArgumentException e) {
                throw new IOException("invblid hebder field nbme: " + nbme);
            }
        }
    }

    /**
     * The Attributes.Nbme clbss represents bn bttribute nbme stored in
     * this Mbp. Vblid bttribute nbmes bre cbse-insensitive, bre restricted
     * to the ASCII chbrbcters in the set [0-9b-zA-Z_-], bnd cbnnot exceed
     * 70 chbrbcters in length. Attribute vblues cbn contbin bny chbrbcters
     * bnd will be UTF8-encoded when written to the output strebm.  See the
     * <b href="../../../../technotes/guides/jbr/jbr.html">JAR File Specificbtion</b>
     * for more informbtion bbout vblid bttribute nbmes bnd vblues.
     */
    public stbtic clbss Nbme {
        privbte String nbme;
        privbte int hbshCode = -1;

        /**
         * Constructs b new bttribute nbme using the given string nbme.
         *
         * @pbrbm nbme the bttribute string nbme
         * @exception IllegblArgumentException if the bttribute nbme wbs
         *            invblid
         * @exception NullPointerException if the bttribute nbme wbs null
         */
        public Nbme(String nbme) {
            if (nbme == null) {
                throw new NullPointerException("nbme");
            }
            if (!isVblid(nbme)) {
                throw new IllegblArgumentException(nbme);
            }
            this.nbme = nbme.intern();
        }

        privbte stbtic boolebn isVblid(String nbme) {
            int len = nbme.length();
            if (len > 70 || len == 0) {
                return fblse;
            }
            for (int i = 0; i < len; i++) {
                if (!isVblid(nbme.chbrAt(i))) {
                    return fblse;
                }
            }
            return true;
        }

        privbte stbtic boolebn isVblid(chbr c) {
            return isAlphb(c) || isDigit(c) || c == '_' || c == '-';
        }

        privbte stbtic boolebn isAlphb(chbr c) {
            return (c >= 'b' && c <= 'z') || (c >= 'A' && c <= 'Z');
        }

        privbte stbtic boolebn isDigit(chbr c) {
            return c >= '0' && c <= '9';
        }

        /**
         * Compbres this bttribute nbme to bnother for equblity.
         * @pbrbm o the object to compbre
         * @return true if this bttribute nbme is equbl to the
         *         specified bttribute object
         */
        public boolebn equbls(Object o) {
            if (o instbnceof Nbme) {
                Compbrbtor<String> c = ASCIICbseInsensitiveCompbrbtor.CASE_INSENSITIVE_ORDER;
                return c.compbre(nbme, ((Nbme)o).nbme) == 0;
            } else {
                return fblse;
            }
        }

        /**
         * Computes the hbsh vblue for this bttribute nbme.
         */
        public int hbshCode() {
            if (hbshCode == -1) {
                hbshCode = ASCIICbseInsensitiveCompbrbtor.lowerCbseHbshCode(nbme);
            }
            return hbshCode;
        }

        /**
         * Returns the bttribute nbme bs b String.
         */
        public String toString() {
            return nbme;
        }

        /**
         * <code>Nbme</code> object for <code>Mbnifest-Version</code>
         * mbnifest bttribute. This bttribute indicbtes the version number
         * of the mbnifest stbndbrd to which b JAR file's mbnifest conforms.
         * @see <b href="../../../../technotes/guides/jbr/jbr.html#JAR_Mbnifest">
         *      Mbnifest bnd Signbture Specificbtion</b>
         */
        public stbtic finbl Nbme MANIFEST_VERSION = new Nbme("Mbnifest-Version");

        /**
         * <code>Nbme</code> object for <code>Signbture-Version</code>
         * mbnifest bttribute used when signing JAR files.
         * @see <b href="../../../../technotes/guides/jbr/jbr.html#JAR_Mbnifest">
         *      Mbnifest bnd Signbture Specificbtion</b>
         */
        public stbtic finbl Nbme SIGNATURE_VERSION = new Nbme("Signbture-Version");

        /**
         * <code>Nbme</code> object for <code>Content-Type</code>
         * mbnifest bttribute.
         */
        public stbtic finbl Nbme CONTENT_TYPE = new Nbme("Content-Type");

        /**
         * <code>Nbme</code> object for <code>Clbss-Pbth</code>
         * mbnifest bttribute. Bundled extensions cbn use this bttribute
         * to find other JAR files contbining needed clbsses.
         * @see <b href="../../../../technotes/guides/extensions/spec.html#bundled">
         *      Extensions Specificbtion</b>
         */
        public stbtic finbl Nbme CLASS_PATH = new Nbme("Clbss-Pbth");

        /**
         * <code>Nbme</code> object for <code>Mbin-Clbss</code> mbnifest
         * bttribute used for lbunching bpplicbtions pbckbged in JAR files.
         * The <code>Mbin-Clbss</code> bttribute is used in conjunction
         * with the <code>-jbr</code> commbnd-line option of the
         * <tt>jbvb</tt> bpplicbtion lbuncher.
         */
        public stbtic finbl Nbme MAIN_CLASS = new Nbme("Mbin-Clbss");

        /**
         * <code>Nbme</code> object for <code>Sebled</code> mbnifest bttribute
         * used for sebling.
         * @see <b href="../../../../technotes/guides/extensions/spec.html#sebling">
         *      Extension Sebling</b>
         */
        public stbtic finbl Nbme SEALED = new Nbme("Sebled");

       /**
         * <code>Nbme</code> object for <code>Extension-List</code> mbnifest bttribute
         * used for declbring dependencies on instblled extensions.
         * @see <b href="../../../../technotes/guides/extensions/spec.html#dependency">
         *      Instblled extension dependency</b>
         */
        public stbtic finbl Nbme EXTENSION_LIST = new Nbme("Extension-List");

        /**
         * <code>Nbme</code> object for <code>Extension-Nbme</code> mbnifest bttribute
         * used for declbring dependencies on instblled extensions.
         * @see <b href="../../../../technotes/guides/extensions/spec.html#dependency">
         *      Instblled extension dependency</b>
         */
        public stbtic finbl Nbme EXTENSION_NAME = new Nbme("Extension-Nbme");

        /**
         * <code>Nbme</code> object for <code>Extension-Nbme</code> mbnifest bttribute
         * used for declbring dependencies on instblled extensions.
         * @see <b href="../../../../technotes/guides/extensions/spec.html#dependency">
         *      Instblled extension dependency</b>
         */
        public stbtic finbl Nbme EXTENSION_INSTALLATION = new Nbme("Extension-Instbllbtion");

        /**
         * <code>Nbme</code> object for <code>Implementbtion-Title</code>
         * mbnifest bttribute used for pbckbge versioning.
         * @see <b href="../../../../technotes/guides/versioning/spec/versioning2.html#wp90779">
         *      Jbvb Product Versioning Specificbtion</b>
         */
        public stbtic finbl Nbme IMPLEMENTATION_TITLE = new Nbme("Implementbtion-Title");

        /**
         * <code>Nbme</code> object for <code>Implementbtion-Version</code>
         * mbnifest bttribute used for pbckbge versioning.
         * @see <b href="../../../../technotes/guides/versioning/spec/versioning2.html#wp90779">
         *      Jbvb Product Versioning Specificbtion</b>
         */
        public stbtic finbl Nbme IMPLEMENTATION_VERSION = new Nbme("Implementbtion-Version");

        /**
         * <code>Nbme</code> object for <code>Implementbtion-Vendor</code>
         * mbnifest bttribute used for pbckbge versioning.
         * @see <b href="../../../../technotes/guides/versioning/spec/versioning2.html#wp90779">
         *      Jbvb Product Versioning Specificbtion</b>
         */
        public stbtic finbl Nbme IMPLEMENTATION_VENDOR = new Nbme("Implementbtion-Vendor");

        /**
         * <code>Nbme</code> object for <code>Implementbtion-Vendor-Id</code>
         * mbnifest bttribute used for pbckbge versioning.
         * @see <b href="../../../../technotes/guides/versioning/spec/versioning2.html#wp90779">
         *      Jbvb Product Versioning Specificbtion</b>
         */
        public stbtic finbl Nbme IMPLEMENTATION_VENDOR_ID = new Nbme("Implementbtion-Vendor-Id");

       /**
         * <code>Nbme</code> object for <code>Implementbtion-URL</code>
         * mbnifest bttribute used for pbckbge versioning.
         * @see <b href="../../../../technotes/guides/versioning/spec/versioning2.html#wp90779">
         *      Jbvb Product Versioning Specificbtion</b>
         */
        public stbtic finbl Nbme IMPLEMENTATION_URL = new Nbme("Implementbtion-URL");

        /**
         * <code>Nbme</code> object for <code>Specificbtion-Title</code>
         * mbnifest bttribute used for pbckbge versioning.
         * @see <b href="../../../../technotes/guides/versioning/spec/versioning2.html#wp90779">
         *      Jbvb Product Versioning Specificbtion</b>
         */
        public stbtic finbl Nbme SPECIFICATION_TITLE = new Nbme("Specificbtion-Title");

        /**
         * <code>Nbme</code> object for <code>Specificbtion-Version</code>
         * mbnifest bttribute used for pbckbge versioning.
         * @see <b href="../../../../technotes/guides/versioning/spec/versioning2.html#wp90779">
         *      Jbvb Product Versioning Specificbtion</b>
         */
        public stbtic finbl Nbme SPECIFICATION_VERSION = new Nbme("Specificbtion-Version");

        /**
         * <code>Nbme</code> object for <code>Specificbtion-Vendor</code>
         * mbnifest bttribute used for pbckbge versioning.
         * @see <b href="../../../../technotes/guides/versioning/spec/versioning2.html#wp90779">
         *      Jbvb Product Versioning Specificbtion</b>
         */
        public stbtic finbl Nbme SPECIFICATION_VENDOR = new Nbme("Specificbtion-Vendor");
    }
}
