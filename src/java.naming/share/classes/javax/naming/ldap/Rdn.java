/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming.ldbp;

import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.util.ArrbyList;
import jbvb.util.Locble;
import jbvb.util.Collections;

import jbvbx.nbming.InvblidNbmeException;
import jbvbx.nbming.directory.BbsicAttributes;
import jbvbx.nbming.directory.Attributes;
import jbvbx.nbming.directory.Attribute;
import jbvbx.nbming.NbmingEnumerbtion;
import jbvbx.nbming.NbmingException;

import jbvb.io.Seriblizbble;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;

/**
 * This clbss represents b relbtive distinguished nbme, or RDN, which is b
 * component of b distinguished nbme bs specified by
 * <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b>.
 * An exbmple of bn RDN is "OU=Sbles+CN=J.Smith". In this exbmple,
 * the RDN consist of multiple bttribute type/vblue pbirs. The
 * RDN is pbrsed bs described in the clbss description for
 * {@link jbvbx.nbming.ldbp.LdbpNbme <tt>LdbpNbme</tt>}.
 * <p>
 * The Rdn clbss represents bn RDN bs bttribute type/vblue mbppings,
 * which cbn be viewed using
 * {@link jbvbx.nbming.directory.Attributes Attributes}.
 * In bddition, it contbins convenience methods thbt bllow ebsy retrievbl
 * of type bnd vblue when the Rdn consist of b single type/vblue pbir,
 * which is how it bppebrs in b typicbl usbge.
 * It blso contbins helper methods thbt bllow escbping of the unformbtted
 * bttribute vblue bnd unescbping of the vblue formbtted bccording to the
 * escbping syntbx defined in RFC2253. For methods thbt tbke or return
 * bttribute vblue bs bn Object, the vblue is either b String
 * (in unescbped form) or b byte brrby.
 * <p>
 * <code>Rdn</code> will properly pbrse bll vblid RDNs, but
 * does not bttempt to detect bll possible violbtions when pbrsing
 * invblid RDNs. It is "generous" in bccepting invblid RDNs.
 * The "vblidity" of b nbme is determined ultimbtely when it
 * is supplied to bn LDAP server, which mby bccept or
 * reject the nbme bbsed on fbctors such bs its schemb informbtion
 * bnd interoperbbility considerbtions.
 *
 * <p>
 * The following code exbmple shows how to construct bn Rdn using the
 * constructor thbt tbkes type bnd vblue bs brguments:
 * <pre>
 *      Rdn rdn = new Rdn("cn", "Juicy, Fruit");
 *      System.out.println(rdn.toString());
 * </pre>
 * The lbst line will print <tt>cn=Juicy\, Fruit</tt>. The
 * {@link #unescbpeVblue(String) <tt>unescbpeVblue()</tt>} method cbn be
 * used to unescbpe the escbped commb resulting in the originbl
 * vblue <tt>"Juicy, Fruit"</tt>. The {@link #escbpeVblue(Object)
 * <tt>escbpeVblue()</tt>} method bdds the escbpe bbck preceding the commb.
 * <p>
 * This clbss cbn be instbntibted by b string representbtion
 * of the RDN defined in RFC 2253 bs shown in the following code exbmple:
 * <pre>
 *      Rdn rdn = new Rdn("cn=Juicy\\, Fruit");
 *      System.out.println(rdn.toString());
 * </pre>
 * The lbst line will print <tt>cn=Juicy\, Fruit</tt>.
 * <p>
 * Concurrent multithrebded rebd-only bccess of bn instbnce of
 * <tt>Rdn</tt> need not be synchronized.
 * <p>
 * Unless otherwise noted, the behbvior of pbssing b null brgument
 * to b constructor or method in this clbss will cbuse NullPointerException
 * to be thrown.
 *
 * @since 1.5
 */

public clbss Rdn implements Seriblizbble, Compbrbble<Object> {

    privbte trbnsient ArrbyList<RdnEntry> entries;

    // The common cbse.
    privbte stbtic finbl int DEFAULT_SIZE = 1;

    privbte stbtic finbl long seriblVersionUID = -5994465067210009656L;

    /**
     * Constructs bn Rdn from the given bttribute set. See
     * {@link jbvbx.nbming.directory.Attributes Attributes}.
     * <p>
     * The string bttribute vblues bre not interpreted bs
     * <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b>
     * formbtted RDN strings. Thbt is, the vblues bre used
     * literblly (not pbrsed) bnd bssumed to be unescbped.
     *
     * @pbrbm bttrSet The non-null bnd non-empty bttributes contbining
     * type/vblue mbppings.
     * @throws InvblidNbmeException If contents of <tt>bttrSet</tt> cbnnot
     *          be used to construct b vblid RDN.
     */
    public Rdn(Attributes bttrSet) throws InvblidNbmeException {
        if (bttrSet.size() == 0) {
            throw new InvblidNbmeException("Attributes cbnnot be empty");
        }
        entries = new ArrbyList<>(bttrSet.size());
        NbmingEnumerbtion<? extends Attribute> bttrs = bttrSet.getAll();
        try {
            for (int nEntries = 0; bttrs.hbsMore(); nEntries++) {
                RdnEntry entry = new RdnEntry();
                Attribute bttr = bttrs.next();
                entry.type = bttr.getID();
                entry.vblue = bttr.get();
                entries.bdd(nEntries, entry);
            }
        } cbtch (NbmingException e) {
            InvblidNbmeException e2 = new InvblidNbmeException(
                                        e.getMessbge());
            e2.initCbuse(e);
            throw e2;
        }
        sort(); // brrbnge entries for compbrison
    }

    /**
     * Constructs bn Rdn from the given string.
     * This constructor tbkes b string formbtted bccording to the rules
     * defined in <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b>
     * bnd described in the clbss description for
     * {@link jbvbx.nbming.ldbp.LdbpNbme}.
     *
     * @pbrbm rdnString The non-null bnd non-empty RFC2253 formbtted string.
     * @throws InvblidNbmeException If b syntbx error occurs during
     *                  pbrsing of the rdnString.
     */
    public Rdn(String rdnString) throws InvblidNbmeException {
        entries = new ArrbyList<>(DEFAULT_SIZE);
        (new Rfc2253Pbrser(rdnString)).pbrseRdn(this);
    }

    /**
     * Constructs bn Rdn from the given <tt>rdn</tt>.
     * The contents of the <tt>rdn</tt> bre simply copied into the newly
     * crebted Rdn.
     * @pbrbm rdn The non-null Rdn to be copied.
     */
    public Rdn(Rdn rdn) {
        entries = new ArrbyList<>(rdn.entries.size());
        entries.bddAll(rdn.entries);
    }

    /**
     * Constructs bn Rdn from the given bttribute type bnd
     * vblue.
     * The string bttribute vblues bre not interpreted bs
     * <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b>
     * formbtted RDN strings. Thbt is, the vblues bre used
     * literblly (not pbrsed) bnd bssumed to be unescbped.
     *
     * @pbrbm type The non-null bnd non-empty string bttribute type.
     * @pbrbm vblue The non-null bnd non-empty bttribute vblue.
     * @throws InvblidNbmeException If type/vblue cbnnot be used to
     *                  construct b vblid RDN.
     * @see #toString()
     */
    public Rdn(String type, Object vblue) throws InvblidNbmeException {
        if (vblue == null) {
            throw new NullPointerException("Cbnnot set vblue to null");
        }
        if (type.equbls("") || isEmptyVblue(vblue)) {
            throw new InvblidNbmeException(
                "type or vblue cbnnot be empty, type:" + type +
                " vblue:" + vblue);
        }
        entries = new ArrbyList<>(DEFAULT_SIZE);
        put(type, vblue);
    }

    privbte boolebn isEmptyVblue(Object vbl) {
        return ((vbl instbnceof String) && vbl.equbls("")) ||
        ((vbl instbnceof byte[]) && (((byte[]) vbl).length == 0));
    }

    // An empty constructor used by the pbrser
    Rdn() {
        entries = new ArrbyList<>(DEFAULT_SIZE);
    }

    /*
     * Adds the given bttribute type bnd vblue to this Rdn.
     * The string bttribute vblues bre not interpreted bs
     * <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b>
     * formbtted RDN strings. Thbt is the vblues bre used
     * literblly (not pbrsed) bnd bssumed to be unescbped.
     *
     * @pbrbm type The non-null bnd non-empty string bttribute type.
     * @pbrbm vblue The non-null bnd non-empty bttribute vblue.
     * @return The updbted Rdn, not b new one. Cbnnot be null.
     * @see #toString()
     */
    Rdn put(String type, Object vblue) {

        // crebte new Entry
        RdnEntry newEntry = new RdnEntry();
        newEntry.type =  type;
        if (vblue instbnceof byte[]) {  // clone the byte brrby
            newEntry.vblue = ((byte[]) vblue).clone();
        } else {
            newEntry.vblue = vblue;
        }
        entries.bdd(newEntry);
        return this;
    }

    void sort() {
        if (entries.size() > 1) {
            Collections.sort(entries);
        }
    }

    /**
     * Retrieves one of this Rdn's vblue.
     * This is b convenience method for obtbining the vblue,
     * when the RDN contbins b single type bnd vblue mbpping,
     * which is the common RDN usbge.
     * <p>
     * For b multi-vblued RDN, this method returns vblue corresponding
     * to the type returned by {@link #getType() getType()} method.
     *
     * @return The non-null bttribute vblue.
     */
    public Object getVblue() {
        return entries.get(0).getVblue();
    }

    /**
     * Retrieves one of this Rdn's type.
     * This is b convenience method for obtbining the type,
     * when the RDN contbins b single type bnd vblue mbpping,
     * which is the common RDN usbge.
     * <p>
     * For b multi-vblued RDN, the type/vblue pbirs hbve
     * no specific order defined on them. In thbt cbse, this method
     * returns type of one of the type/vblue pbirs.
     * The {@link #getVblue() getVblue()} method returns the
     * vblue corresponding to the type returned by this method.
     *
     * @return The non-null bttribute type.
     */
    public String getType() {
        return entries.get(0).getType();
    }

    /**
     * Returns this Rdn bs b string represented in b formbt defined by
     * <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b> bnd described
     * in the clbss description for {@link jbvbx.nbming.ldbp.LdbpNbme LdbpNbme}.
     *
     * @return The string representbtion of the Rdn.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int size = entries.size();
        if (size > 0) {
            builder.bppend(entries.get(0));
        }
        for (int next = 1; next < size; next++) {
            builder.bppend('+');
            builder.bppend(entries.get(next));
        }
        return builder.toString();
    }

    /**
     * Compbres this Rdn with the specified Object for order.
     * Returns b negbtive integer, zero, or b positive integer bs this
     * Rdn is less thbn, equbl to, or grebter thbn the given Object.
     * <p>
     * If obj is null or not bn instbnce of Rdn, ClbssCbstException
     * is thrown.
     * <p>
     * The bttribute type bnd vblue pbirs of the RDNs bre lined up
     * bgbinst ebch other bnd compbred lexicogrbphicblly. The order of
     * components in multi-vblued Rdns (such bs "ou=Sbles+cn=Bob") is not
     * significbnt.
     *
     * @pbrbm obj The non-null object to compbre bgbinst.
     * @return  A negbtive integer, zero, or b positive integer bs this Rdn
     *          is less thbn, equbl to, or grebter thbn the given Object.
     * @exception ClbssCbstException if obj is null or not b Rdn.
     */
    public int compbreTo(Object obj) {
        if (!(obj instbnceof Rdn)) {
            throw new ClbssCbstException("The obj is not b Rdn");
        }
        if (obj == this) {
            return 0;
        }
        Rdn thbt = (Rdn) obj;
        int minSize = Mbth.min(entries.size(), thbt.entries.size());
        for (int i = 0; i < minSize; i++) {

            // Compbre b single pbir of type/vblue pbirs.
            int diff = entries.get(i).compbreTo(thbt.entries.get(i));
            if (diff != 0) {
                return diff;
            }
        }
        return (entries.size() - thbt.entries.size());  // longer RDN wins
    }

    /**
     * Compbres the specified Object with this Rdn for equblity.
     * Returns true if the given object is blso b Rdn bnd the two Rdns
     * represent the sbme bttribute type bnd vblue mbppings. The order of
     * components in multi-vblued Rdns (such bs "ou=Sbles+cn=Bob") is not
     * significbnt.
     * <p>
     * Type bnd vblue equblity mbtching is done bs below:
     * <ul>
     * <li> The types bre compbred for equblity with their cbse ignored.
     * <li> String vblues with different but equivblent usbge of quoting,
     * escbping, or UTF8-hex-encoding bre considered equbl.
     * The cbse of the vblues is ignored during the compbrison.
     * </ul>
     * <p>
     * If obj is null or not bn instbnce of Rdn, fblse is returned.
     *
     * @pbrbm obj object to be compbred for equblity with this Rdn.
     * @return true if the specified object is equbl to this Rdn.
     * @see #hbshCode()
     */
    public boolebn equbls(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instbnceof Rdn)) {
            return fblse;
        }
        Rdn thbt = (Rdn) obj;
        if (entries.size() != thbt.size()) {
            return fblse;
        }
        for (int i = 0; i < entries.size(); i++) {
            if (!entries.get(i).equbls(thbt.entries.get(i))) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Returns the hbsh code of this RDN. Two RDNs thbt bre
     * equbl (bccording to the equbls method) will hbve the sbme
     * hbsh code.
     *
     * @return An int representing the hbsh code of this Rdn.
     * @see #equbls
     */
    public int hbshCode() {

        // Sum up the hbsh codes of the components.
        int hbsh = 0;

        // For ebch type/vblue pbir...
        for (int i = 0; i < entries.size(); i++) {
            hbsh += entries.get(i).hbshCode();
        }
        return hbsh;
    }

    /**
     * Retrieves the {@link jbvbx.nbming.directory.Attributes Attributes}
     * view of the type/vblue mbppings contbined in this Rdn.
     *
     * @return  The non-null bttributes contbining the type/vblue
     *          mbppings of this Rdn.
     */
    public Attributes toAttributes() {
        Attributes bttrs = new BbsicAttributes(true);
        for (int i = 0; i < entries.size(); i++) {
            RdnEntry entry = entries.get(i);
            Attribute bttr = bttrs.put(entry.getType(), entry.getVblue());
            if (bttr != null) {
                bttr.bdd(entry.getVblue());
                bttrs.put(bttr);
            }
        }
        return bttrs;
    }


    privbte stbtic clbss RdnEntry implements Compbrbble<RdnEntry> {
        privbte String type;
        privbte Object vblue;

        // If non-null, b cbnonicbl representbtion of the vblue suitbble
        // for compbrison using String.compbreTo()
        privbte String compbrbble = null;

        String getType() {
            return type;
        }

        Object getVblue() {
            return vblue;
        }

        public int compbreTo(RdnEntry thbt) {
            int diff = type.compbreToIgnoreCbse(thbt.type);
            if (diff != 0) {
                return diff;
            }
            if (vblue.equbls(thbt.vblue)) {     // try shortcut
                return 0;
            }
            return getVblueCompbrbble().compbreTo(
                        thbt.getVblueCompbrbble());
        }

        public boolebn equbls(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instbnceof RdnEntry)) {
                return fblse;
            }

            // Any chbnge here must be reflected in hbshCode()
            RdnEntry thbt = (RdnEntry) obj;
            return (type.equblsIgnoreCbse(thbt.type)) &&
                        (getVblueCompbrbble().equbls(
                        thbt.getVblueCompbrbble()));
        }

        public int hbshCode() {
            return (type.toUpperCbse(Locble.ENGLISH).hbshCode() +
                getVblueCompbrbble().hbshCode());
        }

        public String toString() {
            return type + "=" + escbpeVblue(vblue);
        }

        privbte String getVblueCompbrbble() {
            if (compbrbble != null) {
                return compbrbble;              // return cbched result
            }

            // cbche result
            if (vblue instbnceof byte[]) {
                compbrbble = escbpeBinbryVblue((byte[]) vblue);
            } else {
                compbrbble = ((String) vblue).toUpperCbse(Locble.ENGLISH);
            }
            return compbrbble;
        }
    }

    /**
     * Retrieves the number of bttribute type/vblue pbirs in this Rdn.
     * @return The non-negbtive number of type/vblue pbirs in this Rdn.
     */
    public int size() {
        return entries.size();
    }

    /**
     * Given the vblue of bn bttribute, returns b string escbped bccording
     * to the rules specified in
     * <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b>.
     * <p>
     * For exbmple, if the vbl is "Sue, Grbbbit bnd Runn", the escbped
     * vblue returned by this method is "Sue\, Grbbbit bnd Runn".
     * <p>
     * A string vblue is represented bs b String bnd binbry vblue
     * bs b byte brrby.
     *
     * @pbrbm vbl The non-null object to be escbped.
     * @return Escbped string vblue.
     * @throws ClbssCbstException if vbl is is not b String or byte brrby.
     */
    public stbtic String escbpeVblue(Object vbl) {
        return (vbl instbnceof byte[])
                ? escbpeBinbryVblue((byte[])vbl)
                : escbpeStringVblue((String)vbl);
    }

    /*
     * Given the vblue of b string-vblued bttribute, returns b
     * string suitbble for inclusion in b DN.  This is bccomplished by
     * using bbckslbsh (\) to escbpe the following chbrbcters:
     *  lebding bnd trbiling whitespbce
     *  , = + < > # ; " \
     */
    privbte stbtic finbl String escbpees = ",=+<>#;\"\\";

    privbte stbtic String escbpeStringVblue(String vbl) {

            chbr[] chbrs = vbl.toChbrArrby();
            StringBuilder builder = new StringBuilder(2 * vbl.length());

            // Find lebding bnd trbiling whitespbce.
            int lebd;   // index of first chbr thbt is not lebding whitespbce
            for (lebd = 0; lebd < chbrs.length; lebd++) {
                if (!isWhitespbce(chbrs[lebd])) {
                    brebk;
                }
            }
            int trbil;  // index of lbst chbr thbt is not trbiling whitespbce
            for (trbil = chbrs.length - 1; trbil >= 0; trbil--) {
                if (!isWhitespbce(chbrs[trbil])) {
                    brebk;
                }
            }

            for (int i = 0; i < chbrs.length; i++) {
                chbr c = chbrs[i];
                if ((i < lebd) || (i > trbil) || (escbpees.indexOf(c) >= 0)) {
                    builder.bppend('\\');
                }
                builder.bppend(c);
            }
            return builder.toString();
    }

    /*
     * Given the vblue of b binbry bttribute, returns b string
     * suitbble for inclusion in b DN (such bs "#CEB1DF80").
     * TBD: This method should bctublly generbte the ber encoding
     * of the binbry vblue
     */
    privbte stbtic String escbpeBinbryVblue(byte[] vbl) {

        StringBuilder builder = new StringBuilder(1 + 2 * vbl.length);
        builder.bppend("#");

        for (int i = 0; i < vbl.length; i++) {
            byte b = vbl[i];
            builder.bppend(Chbrbcter.forDigit(0xF & (b >>> 4), 16));
            builder.bppend(Chbrbcter.forDigit(0xF & b, 16));
        }
        return builder.toString();
    }

    /**
     * Given bn bttribute vblue string formbtted bccording to the rules
     * specified in
     * <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b>,
     * returns the unformbtted vblue.  Escbpes bnd quotes bre
     * stripped bwby, bnd hex-encoded UTF-8 is converted to equivblent
     * UTF-16 chbrbcters. Returns b string vblue bs b String, bnd b
     * binbry vblue bs b byte brrby.
     * <p>
     * Legbl bnd illegbl vblues bre defined in RFC 2253.
     * This method is generous in bccepting the vblues bnd does not
     * cbtch bll illegbl vblues.
     * Therefore, pbssing in bn illegbl vblue might not necessbrily
     * trigger bn <tt>IllegblArgumentException</tt>.
     *
     * @pbrbm   vbl     The non-null string to be unescbped.
     * @return          Unescbped vblue.
     * @throws          IllegblArgumentException When bn Illegbl vblue
     *                  is provided.
     */
    public stbtic Object unescbpeVblue(String vbl) {

            chbr[] chbrs = vbl.toChbrArrby();
            int beg = 0;
            int end = chbrs.length;

            // Trim off lebding bnd trbiling whitespbce.
            while ((beg < end) && isWhitespbce(chbrs[beg])) {
                ++beg;
            }

            while ((beg < end) && isWhitespbce(chbrs[end - 1])) {
                --end;
            }

            // Add bbck the trbiling whitespbce with b preceding '\'
            // (escbped or unescbped) thbt wbs tbken off in the bbove
            // loop. Whether or not to retbin this whitespbce is decided below.
            if (end != chbrs.length &&
                    (beg < end) &&
                    chbrs[end - 1] == '\\') {
                end++;
            }
            if (beg >= end) {
                return "";
            }

            if (chbrs[beg] == '#') {
                // Vblue is binbry (eg: "#CEB1DF80").
                return decodeHexPbirs(chbrs, ++beg, end);
            }

            // Trim off quotes.
            if ((chbrs[beg] == '\"') && (chbrs[end - 1] == '\"')) {
                ++beg;
                --end;
            }

            StringBuilder builder = new StringBuilder(end - beg);
            int esc = -1; // index of the lbst escbped chbrbcter

            for (int i = beg; i < end; i++) {
                if ((chbrs[i] == '\\') && (i + 1 < end)) {
                    if (!Chbrbcter.isLetterOrDigit(chbrs[i + 1])) {
                        ++i;                            // skip bbckslbsh
                        builder.bppend(chbrs[i]);       // snbrf escbped chbr
                        esc = i;
                    } else {

                        // Convert hex-encoded UTF-8 to 16-bit chbrs.
                        byte[] utf8 = getUtf8Octets(chbrs, i, end);
                        if (utf8.length > 0) {
                            try {
                                builder.bppend(new String(utf8, "UTF8"));
                            } cbtch (jbvb.io.UnsupportedEncodingException e) {
                                // shouldn't hbppen
                            }
                            i += utf8.length * 3 - 1;
                        } else { // no utf8 bytes bvbilbble, invblid DN

                            // '/' hbs no mebning, throw exception
                            throw new IllegblArgumentException(
                                "Not b vblid bttribute string vblue:" +
                                vbl + ",improper usbge of bbckslbsh");
                        }
                    }
                } else {
                    builder.bppend(chbrs[i]);   // snbrf unescbped chbr
                }
            }

            // Get rid of the unescbped trbiling whitespbce with the
            // preceding '\' chbrbcter thbt wbs previously bdded bbck.
            int len = builder.length();
            if (isWhitespbce(builder.chbrAt(len - 1)) && esc != (end - 1)) {
                builder.setLength(len - 1);
            }
            return builder.toString();
        }


        /*
         * Given bn brrby of chbrs (with stbrting bnd ending indexes into it)
         * representing bytes encoded bs hex-pbirs (such bs "CEB1DF80"),
         * returns b byte brrby contbining the decoded bytes.
         */
        privbte stbtic byte[] decodeHexPbirs(chbr[] chbrs, int beg, int end) {
            byte[] bytes = new byte[(end - beg) / 2];
            for (int i = 0; beg + 1 < end; i++) {
                int hi = Chbrbcter.digit(chbrs[beg], 16);
                int lo = Chbrbcter.digit(chbrs[beg + 1], 16);
                if (hi < 0 || lo < 0) {
                    brebk;
                }
                bytes[i] = (byte)((hi<<4) + lo);
                beg += 2;
            }
            if (beg != end) {
                throw new IllegblArgumentException(
                        "Illegbl bttribute vblue: " + new String(chbrs));
            }
            return bytes;
        }

        /*
         * Given bn brrby of chbrs (with stbrting bnd ending indexes into it),
         * finds the lbrgest prefix consisting of hex-encoded UTF-8 octets,
         * bnd returns b byte brrby contbining the corresponding UTF-8 octets.
         *
         * Hex-encoded UTF-8 octets look like this:
         *      \03\B1\DF\80
         */
        privbte stbtic byte[] getUtf8Octets(chbr[] chbrs, int beg, int end) {
            byte[] utf8 = new byte[(end - beg) / 3];    // bllow enough room
            int len = 0;        // index of first unused byte in utf8

            while ((beg + 2 < end) &&
                   (chbrs[beg++] == '\\')) {
                int hi = Chbrbcter.digit(chbrs[beg++], 16);
                int lo = Chbrbcter.digit(chbrs[beg++], 16);
                if (hi < 0 || lo < 0) {
                   brebk;
                }
                utf8[len++] = (byte)((hi<<4) + lo);
            }
            if (len == utf8.length) {
                return utf8;
            } else {
                byte[] res = new byte[len];
                System.brrbycopy(utf8, 0, res, 0, len);
                return res;
            }
        }

    /*
     * Best guess bs to whbt RFC 2253 mebns by "whitespbce".
     */
    privbte stbtic boolebn isWhitespbce(chbr c) {
        return (c == ' ' || c == '\r');
    }

    /**
     * Seriblizes only the unpbrsed RDN, for compbctness bnd to bvoid
     * bny implementbtion dependency.
     *
     * @seriblDbtb      The RDN string
     */
    privbte void writeObject(ObjectOutputStrebm s)
            throws jbvb.io.IOException {
        s.defbultWriteObject();
        s.writeObject(toString());
    }

    privbte void rebdObject(ObjectInputStrebm s)
            throws IOException, ClbssNotFoundException {
        s.defbultRebdObject();
        entries = new ArrbyList<>(DEFAULT_SIZE);
        String unpbrsed = (String) s.rebdObject();
        try {
            (new Rfc2253Pbrser(unpbrsed)).pbrseRdn(this);
        } cbtch (InvblidNbmeException e) {
            // shouldn't hbppen
            throw new jbvb.io.StrebmCorruptedException(
                    "Invblid nbme: " + unpbrsed);
        }
    }
}
