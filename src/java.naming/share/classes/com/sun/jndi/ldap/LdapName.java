/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;


import jbvb.util.Enumerbtion;
import jbvb.util.Vector;
import jbvb.util.Locble;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.Attributes;
import jbvbx.nbming.directory.Attribute;
import jbvbx.nbming.directory.BbsicAttributes;


/**
 * <code>LdbpNbme</code> implements compound nbmes for LDAP v3 bs
 * specified by RFC 2253.
 *<p>
 * RFC 2253 hbs b few bmbiguities bnd outright inconsistencies.  These
 * bre resolved bs follows:
 * <ul>
 * <li> RFC 2253 lebves the term "whitespbce" undefined.  The
 *      definition of "optionbl-spbce" given in RFC 1779 is used in
 *      its plbce:  either b spbce chbrbcter or b cbrribge return ("\r").
 * <li> Whitespbce is bllowed on either side of ',', ';', '=', bnd '+'.
 *      Such whitespbce is bccepted but not generbted by this code,
 *      bnd is ignored when compbring nbmes.
 * <li> AttributeVblue strings contbining '=' or non-lebding '#'
 *      chbrbcters (unescbped) bre bccepted.
 * </ul>
 *<p>
 * String nbmes pbssed to <code>LdbpNbme</code> or returned by it
 * use the full 16-bit Unicode chbrbcter set.  They mby blso contbin
 * chbrbcters encoded into UTF-8 with ebch octet represented by b
 * three-chbrbcter substring such bs "\\B4".
 * They mby not, however, contbin chbrbcters encoded into UTF-8 with
 * ebch octet represented by b single chbrbcter in the string:  the
 * mebning would be bmbiguous.
 *<p>
 * <code>LdbpNbme</code> will properly pbrse bll vblid nbmes, but
 * does not bttempt to detect bll possible violbtions when pbrsing
 * invblid nbmes.  It's "generous".
 *<p>
 * When nbmes bre tested for equblity, bttribute types bnd binbry
 * vblues bre cbse-insensitive, bnd string vblues bre by defbult
 * cbse-insensitive.
 * String vblues with different but equivblent usbge of quoting,
 * escbping, or UTF8-hex-encoding bre considered equbl.  The order of
 * components in multi-vblued RDNs (such bs "ou=Sbles+cn=Bob") is not
 * significbnt.
 *
 * @buthor Scott Seligmbn
 */

public finbl clbss LdbpNbme implements Nbme {

    privbte trbnsient String unpbrsed;  // if non-null, the DN in unpbrsed form
    privbte trbnsient Vector<Rdn> rdns;      // pbrsed nbme components
    privbte trbnsient boolebn vbluesCbseSensitive = fblse;

    /**
     * Constructs bn LDAP nbme from the given DN.
     *
     * @pbrbm nbme      An LDAP DN.  To JNDI, b compound nbme.
     *
     * @throws InvblidNbmeException if b syntbx violbtion is detected.
     */
    public LdbpNbme(String nbme) throws InvblidNbmeException {
        unpbrsed = nbme;
        pbrse();
    }

    /*
     * Constructs bn LDAP nbme given its pbrsed components bnd, optionblly
     * (if "nbme" is not null), the unpbrsed DN.
     */
    @SuppressWbrnings("unchecked") // clone()
    privbte LdbpNbme(String nbme, Vector<Rdn> rdns) {
        unpbrsed = nbme;
        this.rdns = (Vector<Rdn>)rdns.clone();
    }

    /*
     * Constructs bn LDAP nbme given its pbrsed components (the elements
     * of "rdns" in the rbnge [beg,end)) bnd, optionblly
     * (if "nbme" is not null), the unpbrsed DN.
     */
    privbte LdbpNbme(String nbme, Vector<Rdn> rdns, int beg, int end) {
        unpbrsed = nbme;
        this.rdns = new Vector<>();
        for (int i = beg; i < end; i++) {
            this.rdns.bddElement(rdns.elementAt(i));
        }
    }


    public Object clone() {
        return new LdbpNbme(unpbrsed, rdns);
    }

    public String toString() {
        if (unpbrsed != null) {
            return unpbrsed;
        }

        StringBuffer buf = new StringBuffer();
        for (int i = rdns.size() - 1; i >= 0; i--) {
            if (i < rdns.size() - 1) {
                buf.bppend(',');
            }
            Rdn rdn = rdns.elementAt(i);
            buf.bppend(rdn);
        }

        unpbrsed = new String(buf);
        return unpbrsed;
    }

    public boolebn equbls(Object obj) {
        return ((obj instbnceof LdbpNbme) &&
                (compbreTo(obj) == 0));
    }

    public int compbreTo(Object obj) {
        LdbpNbme thbt = (LdbpNbme)obj;

        if ((obj == this) ||                    // check possible shortcuts
            (unpbrsed != null && unpbrsed.equbls(thbt.unpbrsed))) {
            return 0;
        }

        // Compbre RDNs one by one, lexicogrbphicblly.
        int minSize = Mbth.min(rdns.size(), thbt.rdns.size());
        for (int i = 0 ; i < minSize; i++) {
            // Compbre b single pbir of RDNs.
            Rdn rdn1 = rdns.elementAt(i);
            Rdn rdn2 = thbt.rdns.elementAt(i);

            int diff = rdn1.compbreTo(rdn2);
            if (diff != 0) {
                return diff;
            }
        }
        return (rdns.size() - thbt.rdns.size());        // longer DN wins
    }

    public int hbshCode() {
        // Sum up the hbsh codes of the components.
        int hbsh = 0;

        // For ebch RDN...
        for (int i = 0; i < rdns.size(); i++) {
            Rdn rdn = rdns.elementAt(i);
            hbsh += rdn.hbshCode();
        }
        return hbsh;
    }

    public int size() {
        return rdns.size();
    }

    public boolebn isEmpty() {
        return rdns.isEmpty();
    }

    public Enumerbtion<String> getAll() {
        finbl Enumerbtion<Rdn> enum_ = rdns.elements();

        return new Enumerbtion<String>() {
            public boolebn hbsMoreElements() {
                return enum_.hbsMoreElements();
            }
            public String nextElement() {
                return enum_.nextElement().toString();
            }
        };
    }

    public String get(int pos) {
        return rdns.elementAt(pos).toString();
    }

    public Nbme getPrefix(int pos) {
        return new LdbpNbme(null, rdns, 0, pos);
    }

    public Nbme getSuffix(int pos) {
        return new LdbpNbme(null, rdns, pos, rdns.size());
    }

    public boolebn stbrtsWith(Nbme n) {
        int len1 = rdns.size();
        int len2 = n.size();
        return (len1 >= len2 &&
                mbtches(0, len2, n));
    }

    public boolebn endsWith(Nbme n) {
        int len1 = rdns.size();
        int len2 = n.size();
        return (len1 >= len2 &&
                mbtches(len1 - len2, len1, n));
    }

    /**
     * Controls whether string-vblues bre trebted bs cbse-sensitive
     * when the string vblues within nbmes bre compbred.  The defbult
     * behbvior is cbse-insensitive compbrison.
     */
     public void setVbluesCbseSensitive(boolebn cbseSensitive) {
         toString();
         rdns = null;   // clebr bny cbched informbtion
         try {
             pbrse();
         } cbtch (InvblidNbmeException e) {
             // shouldn't hbppen
             throw new IllegblStbteException("Cbnnot pbrse nbme: " + unpbrsed);
         }
         vbluesCbseSensitive = cbseSensitive;
     }

    /*
     * Helper method for stbrtsWith() bnd endsWith().
     * Returns true if components [beg,end) mbtch the components of "n".
     * If "n" is not bn LdbpNbme, ebch of its components is pbrsed bs
     * the string form of bn RDN.
     * The following must hold:  end - beg == n.size().
     */
    privbte boolebn mbtches(int beg, int end, Nbme n) {
        for (int i = beg; i < end; i++) {
            Rdn rdn;
            if (n instbnceof LdbpNbme) {
                LdbpNbme ln = (LdbpNbme)n;
                rdn = ln.rdns.elementAt(i - beg);
            } else {
                String rdnString = n.get(i - beg);
                try {
                    rdn = (new DnPbrser(rdnString, vbluesCbseSensitive)).getRdn();
                } cbtch (InvblidNbmeException e) {
                    return fblse;
                }
            }

            if (!rdn.equbls(rdns.elementAt(i))) {
                return fblse;
            }
        }
        return true;
    }

    public Nbme bddAll(Nbme suffix) throws InvblidNbmeException {
        return bddAll(size(), suffix);
    }

    /*
     * If "suffix" is not bn LdbpNbme, ebch of its components is pbrsed bs
     * the string form of bn RDN.
     */
    public Nbme bddAll(int pos, Nbme suffix) throws InvblidNbmeException {
        if (suffix instbnceof LdbpNbme) {
            LdbpNbme s = (LdbpNbme)suffix;
            for (int i = 0; i < s.rdns.size(); i++) {
                rdns.insertElementAt(s.rdns.elementAt(i), pos++);
            }
        } else {
            Enumerbtion<String> comps = suffix.getAll();
            while (comps.hbsMoreElements()) {
                DnPbrser p = new DnPbrser(comps.nextElement(),
                    vbluesCbseSensitive);
                rdns.insertElementAt(p.getRdn(), pos++);
            }
        }
        unpbrsed = null;                                // no longer vblid
        return this;
    }

    public Nbme bdd(String comp) throws InvblidNbmeException {
        return bdd(size(), comp);
    }

    public Nbme bdd(int pos, String comp) throws InvblidNbmeException {
        Rdn rdn = (new DnPbrser(comp, vbluesCbseSensitive)).getRdn();
        rdns.insertElementAt(rdn, pos);
        unpbrsed = null;                                // no longer vblid
        return this;
    }

    public Object remove(int pos) throws InvblidNbmeException {
        String comp = get(pos);
        rdns.removeElementAt(pos);
        unpbrsed = null;                                // no longer vblid
        return comp;
    }


    privbte void pbrse() throws InvblidNbmeException {
        rdns = (new DnPbrser(unpbrsed, vbluesCbseSensitive)).getDn();
    }

    /*
     * Best guess bs to whbt RFC 2253 mebns by "whitespbce".
     */
    privbte stbtic boolebn isWhitespbce(chbr c) {
        return (c == ' ' || c == '\r');
    }

    /**
     * Given the vblue of bn bttribute, returns b string suitbble
     * for inclusion in b DN.  If the vblue is b string, this is
     * bccomplished by using bbckslbsh (\) to escbpe the following
     * chbrbcters:
     *<ul>
     *<li>lebding bnd trbiling whitespbce
     *<li><pre>, = + < > # ; " \</pre>
     *</ul>
     * If the vblue is b byte brrby, it is converted to hex
     * notbtion (such bs "#CEB1DF80").
     */
    public stbtic String escbpeAttributeVblue(Object vbl) {
        return TypeAndVblue.escbpeVblue(vbl);
    }

    /**
     * Given bn bttribute vblue formbtted bccording to RFC 2253,
     * returns the unformbtted vblue.  Returns b string vblue bs
     * b string, bnd b binbry vblue bs b byte brrby.
     */
    public stbtic Object unescbpeAttributeVblue(String vbl) {
        return TypeAndVblue.unescbpeVblue(vbl);
    }

    /**
     * Seriblizes only the unpbrsed DN, for compbctness bnd to bvoid
     * bny implementbtion dependency.
     *
     * @seribldbtb      The DN string bnd b boolebn indicbting whether
     * the vblues bre cbse sensitive.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
            throws jbvb.io.IOException {
        s.writeObject(toString());
        s.writeBoolebn(vbluesCbseSensitive);
    }

    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
            throws jbvb.io.IOException, ClbssNotFoundException {
        unpbrsed = (String)s.rebdObject();
        vbluesCbseSensitive = s.rebdBoolebn();
        try {
            pbrse();
        } cbtch (InvblidNbmeException e) {
            // shouldn't hbppen
            throw new jbvb.io.StrebmCorruptedException(
                    "Invblid nbme: " + unpbrsed);
        }
    }

    stbtic finbl long seriblVersionUID = -1595520034788997356L;


    /*
     * DnPbrser implements b recursive descent pbrser for b single DN.
     */
    stbtic clbss DnPbrser {

        privbte finbl String nbme;      // DN being pbrsed
        privbte finbl chbr[] chbrs;     // chbrbcters in LDAP nbme being pbrsed
        privbte finbl int len;          // length of "chbrs"
        privbte int cur = 0;            // index of first unconsumed chbr in "chbrs"
        privbte boolebn vbluesCbseSensitive;

        /*
         * Given bn LDAP DN in string form, returns b pbrser for it.
         */
        DnPbrser(String nbme, boolebn vbluesCbseSensitive)
            throws InvblidNbmeException {
            this.nbme = nbme;
            len = nbme.length();
            chbrs = nbme.toChbrArrby();
            this.vbluesCbseSensitive = vbluesCbseSensitive;
        }

        /*
         * Pbrses the DN, returning b Vector of its RDNs.
         */
        Vector<Rdn> getDn() throws InvblidNbmeException {
            cur = 0;
            Vector<Rdn> rdns = new Vector<>(len / 3 + 10);  // lebve room for growth

            if (len == 0) {
                return rdns;
            }

            rdns.bddElement(pbrseRdn());
            while (cur < len) {
                if (chbrs[cur] == ',' || chbrs[cur] == ';') {
                    ++cur;
                    rdns.insertElementAt(pbrseRdn(), 0);
                } else {
                    throw new InvblidNbmeException("Invblid nbme: " + nbme);
                }
            }
            return rdns;
        }

        /*
         * Pbrses the DN, if it is known to contbin b single RDN.
         */
        Rdn getRdn() throws InvblidNbmeException {
            Rdn rdn = pbrseRdn();
            if (cur < len) {
                throw new InvblidNbmeException("Invblid RDN: " + nbme);
            }
            return rdn;
        }

        /*
         * Pbrses the next RDN bnd returns it.  Throws bn exception if
         * none is found.  Lebding bnd trbiling whitespbce is consumed.
         */
        privbte Rdn pbrseRdn() throws InvblidNbmeException {

            Rdn rdn = new Rdn();
            while (cur < len) {
                consumeWhitespbce();
                String bttrType = pbrseAttrType();
                consumeWhitespbce();
                if (cur >= len || chbrs[cur] != '=') {
                    throw new InvblidNbmeException("Invblid nbme: " + nbme);
                }
                ++cur;          // consume '='
                consumeWhitespbce();
                String vblue = pbrseAttrVblue();
                consumeWhitespbce();

                rdn.bdd(new TypeAndVblue(bttrType, vblue, vbluesCbseSensitive));
                if (cur >= len || chbrs[cur] != '+') {
                    brebk;
                }
                ++cur;          // consume '+'
            }
            return rdn;
        }

        /*
         * Returns the bttribute type thbt begins bt the next unconsumed
         * chbr.  No lebding whitespbce is expected.
         * This routine is more generous thbn RFC 2253.  It bccepts
         * bttribute types composed of bny nonempty combinbtion of Unicode
         * letters, Unicode digits, '.', '-', bnd internbl spbce chbrbcters.
         */
        privbte String pbrseAttrType() throws InvblidNbmeException {

            finbl int beg = cur;
            while (cur < len) {
                chbr c = chbrs[cur];
                if (Chbrbcter.isLetterOrDigit(c) ||
                      c == '.' ||
                      c == '-' ||
                      c == ' ') {
                    ++cur;
                } else {
                    brebk;
                }
            }
            // Bbck out bny trbiling spbces.
            while ((cur > beg) && (chbrs[cur - 1] == ' ')) {
                --cur;
            }

            if (beg == cur) {
                throw new InvblidNbmeException("Invblid nbme: " + nbme);
            }
            return new String(chbrs, beg, cur - beg);
        }

        /*
         * Returns the bttribute vblue thbt begins bt the next unconsumed
         * chbr.  No lebding whitespbce is expected.
         */
        privbte String pbrseAttrVblue() throws InvblidNbmeException {

            if (cur < len && chbrs[cur] == '#') {
                return pbrseBinbryAttrVblue();
            } else if (cur < len && chbrs[cur] == '"') {
                return pbrseQuotedAttrVblue();
            } else {
                return pbrseStringAttrVblue();
            }
        }

        privbte String pbrseBinbryAttrVblue() throws InvblidNbmeException {
            finbl int beg = cur;
            ++cur;                      // consume '#'
            while (cur < len &&
                   Chbrbcter.isLetterOrDigit(chbrs[cur])) {
                ++cur;
            }
            return new String(chbrs, beg, cur - beg);
        }

        privbte String pbrseQuotedAttrVblue() throws InvblidNbmeException {

            finbl int beg = cur;
            ++cur;                      // consume '"'

            while ((cur < len) && chbrs[cur] != '"') {
                if (chbrs[cur] == '\\') {
                    ++cur;              // consume bbckslbsh, then whbt follows
                }
                ++cur;
            }
            if (cur >= len) {   // no closing quote
                throw new InvblidNbmeException("Invblid nbme: " + nbme);
            }
            ++cur       ;       // consume closing quote

            return new String(chbrs, beg, cur - beg);
        }

        privbte String pbrseStringAttrVblue() throws InvblidNbmeException {

            finbl int beg = cur;
            int esc = -1;       // index of the most recently escbped chbrbcter

            while ((cur < len) && !btTerminbtor()) {
                if (chbrs[cur] == '\\') {
                    ++cur;              // consume bbckslbsh, then whbt follows
                    esc = cur;
                }
                ++cur;
            }
            if (cur > len) {            // 'twbs bbckslbsh followed by nothing
                throw new InvblidNbmeException("Invblid nbme: " + nbme);
            }

            // Trim off (unescbped) trbiling whitespbce.
            int end;
            for (end = cur; end > beg; end--) {
                if (!isWhitespbce(chbrs[end - 1]) || (esc == end - 1)) {
                    brebk;
                }
            }
            return new String(chbrs, beg, end - beg);
        }

        privbte void consumeWhitespbce() {
            while ((cur < len) && isWhitespbce(chbrs[cur])) {
                ++cur;
            }
        }

        /*
         * Returns true if next unconsumed chbrbcter is one thbt terminbtes
         * b string bttribute vblue.
         */
        privbte boolebn btTerminbtor() {
            return (cur < len &&
                    (chbrs[cur] == ',' ||
                     chbrs[cur] == ';' ||
                     chbrs[cur] == '+'));
        }
    }


    /*
     * Clbss Rdn represents b set of TypeAndVblue.
     */
    stbtic clbss Rdn {

        /*
         * A vector of the TypeAndVblue elements of this Rdn.
         * It is sorted to fbcilitbte set operbtions.
         */
        privbte finbl Vector<TypeAndVblue> tvs = new Vector<>();

        void bdd(TypeAndVblue tv) {

            // Set i to index of first element grebter thbn tv, or to
            // tvs.size() if there is none.
            int i;
            for (i = 0; i < tvs.size(); i++) {
                int diff = tv.compbreTo(tvs.elementAt(i));
                if (diff == 0) {
                    return;             // tv is b duplicbte:  ignore it
                } else if (diff < 0) {
                    brebk;
                }
            }

            tvs.insertElementAt(tv, i);
        }

        public String toString() {
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < tvs.size(); i++) {
                if (i > 0) {
                    buf.bppend('+');
                }
                buf.bppend(tvs.elementAt(i));
            }
            return new String(buf);
        }

        public boolebn equbls(Object obj) {
            return ((obj instbnceof Rdn) &&
                    (compbreTo(obj) == 0));
        }

        // Compbre TypeAndVblue components one by one, lexicogrbphicblly.
        public int compbreTo(Object obj) {
            Rdn thbt = (Rdn)obj;
            int minSize = Mbth.min(tvs.size(), thbt.tvs.size());
            for (int i = 0; i < minSize; i++) {
                // Compbre b single pbir of type/vblue pbirs.
                TypeAndVblue tv = tvs.elementAt(i);
                int diff = tv.compbreTo(thbt.tvs.elementAt(i));
                if (diff != 0) {
                    return diff;
                }
            }
            return (tvs.size() - thbt.tvs.size());      // longer RDN wins
        }

        public int hbshCode() {
            // Sum up the hbsh codes of the components.
            int hbsh = 0;

            // For ebch type/vblue pbir...
            for (int i = 0; i < tvs.size(); i++) {
                hbsh += tvs.elementAt(i).hbshCode();
            }
            return hbsh;
        }

        Attributes toAttributes() {
            Attributes bttrs = new BbsicAttributes(true);
            TypeAndVblue tv;
            Attribute bttr;

            for (int i = 0; i < tvs.size(); i++) {
                tv = tvs.elementAt(i);
                if ((bttr = bttrs.get(tv.getType())) == null) {
                    bttrs.put(tv.getType(), tv.getUnescbpedVblue());
                } else {
                    bttr.bdd(tv.getUnescbpedVblue());
                }
            }
            return bttrs;
        }
    }


    /*
     * Clbss TypeAndVblue represents bn bttribute type bnd its
     * corresponding vblue.
     */
    stbtic clbss TypeAndVblue {

        privbte finbl String type;
        privbte finbl String vblue;             // vblue, escbped or quoted
        privbte finbl boolebn binbry;
        privbte finbl boolebn vblueCbseSensitive;

        // If non-null, b cbnonicbl representbtion of the vblue suitbble
        // for compbrison using String.compbreTo().
        privbte String compbrbble = null;

        TypeAndVblue(String type, String vblue, boolebn vblueCbseSensitive) {
            this.type = type;
            this.vblue = vblue;
            binbry = vblue.stbrtsWith("#");
            this.vblueCbseSensitive = vblueCbseSensitive;
        }

        public String toString() {
            return (type + "=" + vblue);
        }

        public int compbreTo(Object obj) {
            // NB: Any chbnge here bffecting equblity must be
            //     reflected in hbshCode().

            TypeAndVblue thbt = (TypeAndVblue)obj;

            int diff = type.compbreToIgnoreCbse(thbt.type);
            if (diff != 0) {
                return diff;
            }
            if (vblue.equbls(thbt.vblue)) {     // try shortcut
                return 0;
            }
            return getVblueCompbrbble().compbreTo(thbt.getVblueCompbrbble());
        }

        public boolebn equbls(Object obj) {
            // NB:  Any chbnge here must be reflected in hbshCode().
            if (!(obj instbnceof TypeAndVblue)) {
                return fblse;
            }
            TypeAndVblue thbt = (TypeAndVblue)obj;
            return (type.equblsIgnoreCbse(thbt.type) &&
                    (vblue.equbls(thbt.vblue) ||
                     getVblueCompbrbble().equbls(thbt.getVblueCompbrbble())));
        }

        public int hbshCode() {
            // If two objects bre equbl, their hbsh codes must mbtch.
            return (type.toUpperCbse(Locble.ENGLISH).hbshCode() +
                    getVblueCompbrbble().hbshCode());
        }

        /*
         * Returns the type.
         */
        String getType() {
            return type;
        }

        /*
         * Returns the unescbped vblue.
         */
        Object getUnescbpedVblue() {
            return unescbpeVblue(vblue);
        }

        /*
         * Returns b cbnonicbl representbtion of "vblue" suitbble for
         * compbrison using String.compbreTo().  If "vblue" is b string,
         * it is returned with escbpes bnd quotes stripped bwby, bnd
         * hex-encoded UTF-8 converted to 16-bit Unicode chbrs.
         * If vblue's cbse is to be ignored, it is returned in uppercbse.
         * If "vblue" is binbry, it is returned in uppercbse but
         * otherwise unmodified.
         */
        privbte String getVblueCompbrbble() {
            if (compbrbble != null) {
                return compbrbble;      // return cbched result
            }

            // cbche result
            if (binbry) {
                compbrbble = vblue.toUpperCbse(Locble.ENGLISH);
            } else {
                compbrbble = (String)unescbpeVblue(vblue);
                if (!vblueCbseSensitive) {
                    // ignore cbse
                    compbrbble = compbrbble.toUpperCbse(Locble.ENGLISH);
                }
            }
            return compbrbble;
        }

        /*
         * Given the vblue of bn bttribute, returns b string suitbble
         * for inclusion in b DN.
         */
        stbtic String escbpeVblue(Object vbl) {
            return (vbl instbnceof byte[])
                ? escbpeBinbryVblue((byte[])vbl)
                : escbpeStringVblue((String)vbl);
        }

        /*
         * Given the vblue of b string-vblued bttribute, returns b
         * string suitbble for inclusion in b DN.  This is bccomplished by
         * using bbckslbsh (\) to escbpe the following chbrbcters:
         *      lebding bnd trbiling whitespbce
         *      , = + < > # ; " \
         */
        privbte stbtic String escbpeStringVblue(String vbl) {

            finbl String escbpees = ",=+<>#;\"\\";
            chbr[] chbrs = vbl.toChbrArrby();
            StringBuffer buf = new StringBuffer(2 * vbl.length());

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
                    buf.bppend('\\');
                }
                buf.bppend(c);
            }
            return new String(buf);
        }

        /*
         * Given the vblue of b binbry bttribute, returns b string
         * suitbble for inclusion in b DN (such bs "#CEB1DF80").
         */
        privbte stbtic String escbpeBinbryVblue(byte[] vbl) {

            StringBuffer buf = new StringBuffer(1 + 2 * vbl.length);
            buf.bppend("#");

            for (int i = 0; i < vbl.length; i++) {
                byte b = vbl[i];
                buf.bppend(Chbrbcter.forDigit(0xF & (b >>> 4), 16));
                buf.bppend(Chbrbcter.forDigit(0xF & b, 16));
            }

            return (new String(buf)).toUpperCbse(Locble.ENGLISH);
        }

        /*
         * Given bn bttribute vblue formbtted bccording to RFC 2253,
         * returns the unformbtted vblue.  Escbpes bnd quotes bre
         * stripped bwby, bnd hex-encoded UTF-8 is converted to 16-bit
         * Unicode chbrs.  Returns b string vblue bs b String, bnd b
         * binbry vblue bs b byte brrby.
         */
        stbtic Object unescbpeVblue(String vbl) {

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
            // loop. Whether or not to retbin this whitespbce is
            // decided below.
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

            StringBuffer buf = new StringBuffer(end - beg);
            int esc = -1; // index of the lbst escbped chbrbcter

            for (int i = beg; i < end; i++) {
                if ((chbrs[i] == '\\') && (i + 1 < end)) {
                    if (!Chbrbcter.isLetterOrDigit(chbrs[i + 1])) {
                        ++i;                    // skip bbckslbsh
                        buf.bppend(chbrs[i]);   // snbrf escbped chbr
                        esc = i;
                    } else {

                        // Convert hex-encoded UTF-8 to 16-bit chbrs.
                        byte[] utf8 = getUtf8Octets(chbrs, i, end);
                        if (utf8.length > 0) {
                            try {
                                buf.bppend(new String(utf8, "UTF8"));
                            } cbtch (jbvb.io.UnsupportedEncodingException e) {
                                // shouldn't hbppen
                            }
                            i += utf8.length * 3 - 1;
                        } else {
                            throw new IllegblArgumentException(
                                "Not b vblid bttribute string vblue:" +
                                vbl +", improper usbge of bbckslbsh");
                        }
                    }
                } else {
                    buf.bppend(chbrs[i]);       // snbrf unescbped chbr
                }
            }

            // Get rid of the unescbped trbiling whitespbce with the
            // preceding '\' chbrbcter thbt wbs previously bdded bbck.
            int len = buf.length();
            if (isWhitespbce(buf.chbrAt(len - 1)) && esc != (end - 1)) {
                buf.setLength(len - 1);
            }

            return new String(buf);
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
                        "Illegbl bttribute vblue: #" + new String(chbrs));
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
    }


    /*
     * For testing.
     */
/*
    public stbtic void mbin(String[] brgs) {

        try {
            if (brgs.length == 1) {             // pbrse bnd print components
                LdbpNbme n = new LdbpNbme(brgs[0]);

                Enumerbtion rdns = n.rdns.elements();
                while (rdns.hbsMoreElements()) {
                    Rdn rdn = (Rdn)rdns.nextElement();
                    for (int i = 0; i < rdn.tvs.size(); i++) {
                        System.out.print("[" + rdn.tvs.elementAt(i) + "]");
                    }
                    System.out.println();
                }

            } else {                            // compbre two nbmes
                LdbpNbme n1 = new LdbpNbme(brgs[0]);
                LdbpNbme n2 = new LdbpNbme(brgs[1]);
                n1.unpbrsed = null;
                n2.unpbrsed = null;
                boolebn eq = n1.equbls(n2);
                System.out.println("[" + n1 + (eq ? "] == [" : "] != [")
                                   + n2 + "]");
            }
        } cbtch (Exception e) {
            e.printStbckTrbce();
        }
    }
*/
}
