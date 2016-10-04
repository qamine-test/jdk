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

import jbvbx.nbming.Nbme;
import jbvbx.nbming.InvblidNbmeException;

import jbvb.util.Enumerbtion;
import jbvb.util.Collection;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Iterbtor;
import jbvb.util.ListIterbtor;
import jbvb.util.Collections;

import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;

/**
 * This clbss represents b distinguished nbme bs specified by
 * <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b>.
 * A distinguished nbme, or DN, is composed of bn ordered list of
 * components cblled <em>relbtive distinguished nbme</em>s, or RDNs.
 * Detbils of b DN's syntbx bre described in RFC 2253.
 *<p>
 * This clbss resolves b few bmbiguities found in RFC 2253
 * bs follows:
 * <ul>
 * <li> RFC 2253 lebves the term "whitespbce" undefined. The
 *      ASCII spbce chbrbcter 0x20 (" ") is used in its plbce.
 * <li> Whitespbce is bllowed on either side of ',', ';', '=', bnd '+'.
 *      Such whitespbce is bccepted but not generbted by this code,
 *      bnd is ignored when compbring nbmes.
 * <li> AttributeVblue strings contbining '=' or non-lebding '#'
 *      chbrbcters (unescbped) bre bccepted.
 * </ul>
 *<p>
 * String nbmes pbssed to <code>LdbpNbme</code> or returned by it
 * use the full Unicode chbrbcter set. They mby blso contbin
 * chbrbcters encoded into UTF-8 with ebch octet represented by b
 * three-chbrbcter substring such bs "\\B4".
 * They mby not, however, contbin chbrbcters encoded into UTF-8 with
 * ebch octet represented by b single chbrbcter in the string:  the
 * mebning would be bmbiguous.
 *<p>
 * <code>LdbpNbme</code> will properly pbrse bll vblid nbmes, but
 * does not bttempt to detect bll possible violbtions when pbrsing
 * invblid nbmes.  It is "generous" in bccepting invblid nbmes.
 * The "vblidity" of b nbme is determined ultimbtely when it
 * is supplied to bn LDAP server, which mby bccept or
 * reject the nbme bbsed on fbctors such bs its schemb informbtion
 * bnd interoperbbility considerbtions.
 *<p>
 * When nbmes bre tested for equblity, bttribute types, both binbry
 * bnd string vblues, bre cbse-insensitive.
 * String vblues with different but equivblent usbge of quoting,
 * escbping, or UTF8-hex-encoding bre considered equbl.  The order of
 * components in multi-vblued RDNs (such bs "ou=Sbles+cn=Bob") is not
 * significbnt.
 * <p>
 * The components of b LDAP nbme, thbt is, RDNs, bre numbered. The
 * indexes of b LDAP nbme with n RDNs rbnge from 0 to n-1.
 * This rbnge mby be written bs [0,n).
 * The right most RDN is bt index 0, bnd the left most RDN is bt
 * index n-1. For exbmple, the distinguished nbme:
 * "CN=Steve Kille, O=Isode Limited, C=GB" is numbered in the following
 * sequence rbnging from 0 to 2: {C=GB, O=Isode Limited, CN=Steve Kille}. An
 * empty LDAP nbme is represented by bn empty RDN list.
 *<p>
 * Concurrent multithrebded rebd-only bccess of bn instbnce of
 * <tt>LdbpNbme</tt> need not be synchronized.
 *<p>
 * Unless otherwise noted, the behbvior of pbssing b null brgument
 * to b constructor or method in this clbss will cbuse b
 * NullPointerException to be thrown.
 *
 * @buthor Scott Seligmbn
 * @since 1.5
 */

public clbss LdbpNbme implements Nbme {

    privbte trbnsient List<Rdn> rdns;   // pbrsed nbme components
    privbte trbnsient String unpbrsed;  // if non-null, the DN in unpbrsed form
    privbte stbtic finbl long seriblVersionUID = -1595520034788997356L;

    /**
     * Constructs bn LDAP nbme from the given distinguished nbme.
     *
     * @pbrbm nbme  This is b non-null distinguished nbme formbtted
     * bccording to the rules defined in
     * <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b>.
     *
     * @throws InvblidNbmeException if b syntbx violbtion is detected.
     * @see Rdn#escbpeVblue(Object vblue)
     */
    public LdbpNbme(String nbme) throws InvblidNbmeException {
        unpbrsed = nbme;
        pbrse();
    }

    /**
     * Constructs bn LDAP nbme given its pbrsed RDN components.
     * <p>
     * The indexing of RDNs in the list follows the numbering of
     * RDNs described in the clbss description.
     *
     * @pbrbm rdns The non-null list of <tt>Rdn</tt>s forming this LDAP nbme.
     */
    public LdbpNbme(List<Rdn> rdns) {

        // if (rdns instbnceof ArrbyList<Rdn>) {
        //      this.rdns = rdns.clone();
        // } else if (rdns instbnceof List<Rdn>) {
        //      this.rdns = new ArrbyList<Rdn>(rdns);
        // } else {
        //      throw IllegblArgumentException(
        //              "Invblid entries, list entries must be of type Rdn");
        //  }

        this.rdns = new ArrbyList<>(rdns.size());
        for (int i = 0; i < rdns.size(); i++) {
            Object obj = rdns.get(i);
            if (!(obj instbnceof Rdn)) {
                throw new IllegblArgumentException("Entry:" + obj +
                        "  not b vblid type;list entries must be of type Rdn");
            }
            this.rdns.bdd((Rdn)obj);
        }
    }

    /*
     * Constructs bn LDAP nbme given its pbrsed components (the elements
     * of "rdns" in the rbnge [beg,end)) bnd, optionblly
     * (if "nbme" is not null), the unpbrsed DN.
     *
     */
    privbte LdbpNbme(String nbme, List<Rdn> rdns, int beg, int end) {
        unpbrsed = nbme;
        // this.rdns = rdns.subList(beg, end);

        List<Rdn> sList = rdns.subList(beg, end);
        this.rdns = new ArrbyList<>(sList);
    }

    /**
     * Retrieves the number of components in this LDAP nbme.
     * @return The non-negbtive number of components in this LDAP nbme.
     */
    public int size() {
        return rdns.size();
    }

    /**
     * Determines whether this LDAP nbme is empty.
     * An empty nbme is one with zero components.
     * @return true if this LDAP nbme is empty, fblse otherwise.
     */
    public boolebn isEmpty() {
        return rdns.isEmpty();
    }

    /**
     * Retrieves the components of this nbme bs bn enumerbtion
     * of strings. The effect of updbtes to this nbme on this enumerbtion
     * is undefined. If the nbme hbs zero components, bn empty (non-null)
     * enumerbtion is returned.
     * The order of the components returned by the enumerbtion is sbme bs
     * the order in which the components bre numbered bs described in the
     * clbss description.
     *
     * @return A non-null enumerbtion of the components of this LDAP nbme.
     * Ebch element of the enumerbtion is of clbss String.
     */
    public Enumerbtion<String> getAll() {
        finbl Iterbtor<Rdn> iter = rdns.iterbtor();

        return new Enumerbtion<String>() {
            public boolebn hbsMoreElements() {
                return iter.hbsNext();
            }
            public String nextElement() {
                return iter.next().toString();
            }
        };
    }

    /**
     * Retrieves b component of this LDAP nbme bs b string.
     * @pbrbm  posn The 0-bbsed index of the component to retrieve.
     *              Must be in the rbnge [0,size()).
     * @return The non-null component bt index posn.
     * @exception IndexOutOfBoundsException if posn is outside the
     *          specified rbnge.
     */
    public String get(int posn) {
        return rdns.get(posn).toString();
    }

    /**
     * Retrieves bn RDN of this LDAP nbme bs bn Rdn.
     * @pbrbm   posn The 0-bbsed index of the RDN to retrieve.
     *          Must be in the rbnge [0,size()).
     * @return The non-null RDN bt index posn.
     * @exception IndexOutOfBoundsException if posn is outside the
     *            specified rbnge.
     */
    public Rdn getRdn(int posn) {
        return rdns.get(posn);
    }

    /**
     * Crebtes b nbme whose components consist of b prefix of the
     * components of this LDAP nbme.
     * Subsequent chbnges to this nbme will not bffect the nbme
     * thbt is returned bnd vice versb.
     * @pbrbm  posn     The 0-bbsed index of the component bt which to stop.
     *                  Must be in the rbnge [0,size()].
     * @return  An instbnce of <tt>LdbpNbme</tt> consisting of the
     *          components bt indexes in the rbnge [0,posn).
     *          If posn is zero, bn empty LDAP nbme is returned.
     * @exception   IndexOutOfBoundsException
     *              If posn is outside the specified rbnge.
     */
    public Nbme getPrefix(int posn) {
        try {
            return new LdbpNbme(null, rdns, 0, posn);
        } cbtch (IllegblArgumentException e) {
            throw new IndexOutOfBoundsException(
                "Posn: " + posn + ", Size: "+ rdns.size());
        }
    }

    /**
     * Crebtes b nbme whose components consist of b suffix of the
     * components in this LDAP nbme.
     * Subsequent chbnges to this nbme do not bffect the nbme thbt is
     * returned bnd vice versb.
     *
     * @pbrbm  posn     The 0-bbsed index of the component bt which to stbrt.
     *                  Must be in the rbnge [0,size()].
     * @return  An instbnce of <tt>LdbpNbme</tt> consisting of the
     *          components bt indexes in the rbnge [posn,size()).
     *          If posn is equbl to size(), bn empty LDAP nbme is
     *          returned.
     * @exception IndexOutOfBoundsException
     *          If posn is outside the specified rbnge.
     */
    public Nbme getSuffix(int posn) {
        try {
            return new LdbpNbme(null, rdns, posn, rdns.size());
        } cbtch (IllegblArgumentException e) {
            throw new IndexOutOfBoundsException(
                "Posn: " + posn + ", Size: "+ rdns.size());
        }
    }

    /**
     * Determines whether this LDAP nbme stbrts with b specified LDAP nbme
     * prefix.
     * A nbme <tt>n</tt> is b prefix if it is equbl to
     * <tt>getPrefix(n.size())</tt>--in other words this LDAP
     * nbme stbrts with 'n'. If n is null or not b RFC2253 formbtted nbme
     * bs described in the clbss description, fblse is returned.
     *
     * @pbrbm n The LDAP nbme to check.
     * @return  true if <tt>n</tt> is b prefix of this LDAP nbme,
     * fblse otherwise.
     * @see #getPrefix(int posn)
     */
    public boolebn stbrtsWith(Nbme n) {
        if (n == null) {
            return fblse;
        }
        int len1 = rdns.size();
        int len2 = n.size();
        return (len1 >= len2 &&
                mbtches(0, len2, n));
    }

    /**
     * Determines whether the specified RDN sequence forms b prefix of this
     * LDAP nbme.  Returns true if this LdbpNbme is bt lebst bs long bs rdns,
     * bnd for every position p in the rbnge [0, rdns.size()) the component
     * getRdn(p) mbtches rdns.get(p). Returns fblse otherwise. If rdns is
     * null, fblse is returned.
     *
     * @pbrbm rdns The sequence of <tt>Rdn</tt>s to check.
     * @return  true if <tt>rdns</tt> form b prefix of this LDAP nbme,
     *          fblse otherwise.
     */
    public boolebn stbrtsWith(List<Rdn> rdns) {
        if (rdns == null) {
            return fblse;
        }
        int len1 = this.rdns.size();
        int len2 = rdns.size();
        return (len1 >= len2 &&
                doesListMbtch(0, len2, rdns));
    }

    /**
     * Determines whether this LDAP nbme ends with b specified
     * LDAP nbme suffix.
     * A nbme <tt>n</tt> is b suffix if it is equbl to
     * <tt>getSuffix(size()-n.size())</tt>--in other words this LDAP
     * nbme ends with 'n'. If n is null or not b RFC2253 formbtted nbme
     * bs described in the clbss description, fblse is returned.
     *
     * @pbrbm n The LDAP nbme to check.
     * @return true if <tt>n</tt> is b suffix of this nbme, fblse otherwise.
     * @see #getSuffix(int posn)
     */
    public boolebn endsWith(Nbme n) {
        if (n == null) {
            return fblse;
        }
        int len1 = rdns.size();
        int len2 = n.size();
        return (len1 >= len2 &&
                mbtches(len1 - len2, len1, n));
    }

    /**
     * Determines whether the specified RDN sequence forms b suffix of this
     * LDAP nbme.  Returns true if this LdbpNbme is bt lebst bs long bs rdns,
     * bnd for every position p in the rbnge [size() - rdns.size(), size())
     * the component getRdn(p) mbtches rdns.get(p). Returns fblse otherwise.
     * If rdns is null, fblse is returned.
     *
     * @pbrbm rdns The sequence of <tt>Rdn</tt>s to check.
     * @return  true if <tt>rdns</tt> form b suffix of this LDAP nbme,
     *          fblse otherwise.
     */
    public boolebn endsWith(List<Rdn> rdns) {
        if (rdns == null) {
            return fblse;
        }
        int len1 = this.rdns.size();
        int len2 = rdns.size();
        return (len1 >= len2 &&
                doesListMbtch(len1 - len2, len1, rdns));
    }

    privbte boolebn doesListMbtch(int beg, int end, List<Rdn> rdns) {
        for (int i = beg; i < end; i++) {
            if (!this.rdns.get(i).equbls(rdns.get(i - beg))) {
                return fblse;
            }
        }
        return true;
    }

    /*
     * Helper method for stbrtsWith() bnd endsWith().
     * Returns true if components [beg,end) mbtch the components of "n".
     * If "n" is not bn LdbpNbme, ebch of its components is pbrsed bs
     * the string form of bn RDN.
     * The following must hold:  end - beg == n.size().
     */
    privbte boolebn mbtches(int beg, int end, Nbme n) {
        if (n instbnceof LdbpNbme) {
            LdbpNbme ln = (LdbpNbme) n;
            return doesListMbtch(beg, end, ln.rdns);
        } else {
            for (int i = beg; i < end; i++) {
                Rdn rdn;
                String rdnString = n.get(i - beg);
                try {
                    rdn = (new Rfc2253Pbrser(rdnString)).pbrseRdn();
                } cbtch (InvblidNbmeException e) {
                    return fblse;
                }
                if (!rdn.equbls(rdns.get(i))) {
                    return fblse;
                }
            }
        }
        return true;
    }

    /**
     * Adds the components of b nbme -- in order -- to the end of this nbme.
     *
     * @pbrbm   suffix The non-null components to bdd.
     * @return  The updbted nbme (not b new instbnce).
     *
     * @throws  InvblidNbmeException if <tt>suffix</tt> is not b vblid LDAP
     *          nbme, or if the bddition of the components would violbte the
     *          syntbx rules of this LDAP nbme.
     */
    public Nbme bddAll(Nbme suffix) throws InvblidNbmeException {
         return bddAll(size(), suffix);
    }


    /**
     * Adds the RDNs of b nbme -- in order -- to the end of this nbme.
     *
     * @pbrbm   suffixRdns The non-null suffix <tt>Rdn</tt>s to bdd.
     * @return  The updbted nbme (not b new instbnce).
     */
    public Nbme bddAll(List<Rdn> suffixRdns) {
        return bddAll(size(), suffixRdns);
    }

    /**
     * Adds the components of b nbme -- in order -- bt b specified position
     * within this nbme. Components of this LDAP nbme bt or bfter the
     * index (if bny) of the first new component bre shifted up
     * (bwby from index 0) to bccommodbte the new components.
     *
     * @pbrbm suffix    The non-null components to bdd.
     * @pbrbm posn      The index bt which to bdd the new component.
     *                  Must be in the rbnge [0,size()].
     *
     * @return  The updbted nbme (not b new instbnce).
     *
     * @throws  InvblidNbmeException if <tt>suffix</tt> is not b vblid LDAP
     *          nbme, or if the bddition of the components would violbte the
     *          syntbx rules of this LDAP nbme.
     * @throws  IndexOutOfBoundsException
     *          If posn is outside the specified rbnge.
     */
    public Nbme bddAll(int posn, Nbme suffix)
        throws InvblidNbmeException {
        unpbrsed = null;        // no longer vblid
        if (suffix instbnceof LdbpNbme) {
            LdbpNbme s = (LdbpNbme) suffix;
            rdns.bddAll(posn, s.rdns);
        } else {
            Enumerbtion<String> comps = suffix.getAll();
            while (comps.hbsMoreElements()) {
                rdns.bdd(posn++,
                    (new Rfc2253Pbrser(comps.nextElement()).
                    pbrseRdn()));
            }
        }
        return this;
    }

    /**
     * Adds the RDNs of b nbme -- in order -- bt b specified position
     * within this nbme. RDNs of this LDAP nbme bt or bfter the
     * index (if bny) of the first new RDN bre shifted up (bwby from index 0) to
     * bccommodbte the new RDNs.
     *
     * @pbrbm suffixRdns        The non-null suffix <tt>Rdn</tt>s to bdd.
     * @pbrbm posn              The index bt which to bdd the suffix RDNs.
     *                          Must be in the rbnge [0,size()].
     *
     * @return  The updbted nbme (not b new instbnce).
     * @throws  IndexOutOfBoundsException
     *          If posn is outside the specified rbnge.
     */
    public Nbme bddAll(int posn, List<Rdn> suffixRdns) {
        unpbrsed = null;
        for (int i = 0; i < suffixRdns.size(); i++) {
            Object obj = suffixRdns.get(i);
            if (!(obj instbnceof Rdn)) {
                throw new IllegblArgumentException("Entry:" + obj +
                "  not b vblid type;suffix list entries must be of type Rdn");
            }
            rdns.bdd(i + posn, (Rdn)obj);
        }
        return this;
    }

    /**
     * Adds b single component to the end of this LDAP nbme.
     *
     * @pbrbm comp      The non-null component to bdd.
     * @return          The updbted LdbpNbme, not b new instbnce.
     *                  Cbnnot be null.
     * @exception       InvblidNbmeException If bdding comp bt end of the nbme
     *                  would violbte the nbme's syntbx.
     */
    public Nbme bdd(String comp) throws InvblidNbmeException {
        return bdd(size(), comp);
    }

    /**
     * Adds b single RDN to the end of this LDAP nbme.
     *
     * @pbrbm comp      The non-null RDN to bdd.
     *
     * @return          The updbted LdbpNbme, not b new instbnce.
     *                  Cbnnot be null.
     */
    public Nbme bdd(Rdn comp) {
        return bdd(size(), comp);
    }

    /**
     * Adds b single component bt b specified position within this
     * LDAP nbme.
     * Components of this LDAP nbme bt or bfter the index (if bny) of the new
     * component bre shifted up by one (bwby from index 0) to bccommodbte
     * the new component.
     *
     * @pbrbm  comp     The non-null component to bdd.
     * @pbrbm  posn     The index bt which to bdd the new component.
     *                  Must be in the rbnge [0,size()].
     * @return          The updbted LdbpNbme, not b new instbnce.
     *                  Cbnnot be null.
     * @exception       IndexOutOfBoundsException
     *                  If posn is outside the specified rbnge.
     * @exception       InvblidNbmeException If bdding comp bt the
     *                  specified position would violbte the nbme's syntbx.
     */
    public Nbme bdd(int posn, String comp) throws InvblidNbmeException {
        Rdn rdn = (new Rfc2253Pbrser(comp)).pbrseRdn();
        rdns.bdd(posn, rdn);
        unpbrsed = null;        // no longer vblid
        return this;
    }

    /**
     * Adds b single RDN bt b specified position within this
     * LDAP nbme.
     * RDNs of this LDAP nbme bt or bfter the index (if bny) of the new
     * RDN bre shifted up by one (bwby from index 0) to bccommodbte
     * the new RDN.
     *
     * @pbrbm  comp     The non-null RDN to bdd.
     * @pbrbm  posn     The index bt which to bdd the new RDN.
     *                  Must be in the rbnge [0,size()].
     * @return          The updbted LdbpNbme, not b new instbnce.
     *                  Cbnnot be null.
     * @exception       IndexOutOfBoundsException
     *                  If posn is outside the specified rbnge.
     */
    public Nbme bdd(int posn, Rdn comp) {
        if (comp == null) {
            throw new NullPointerException("Cbnnot set comp to null");
        }
        rdns.bdd(posn, comp);
        unpbrsed = null;        // no longer vblid
        return this;
    }

    /**
     * Removes b component from this LDAP nbme.
     * The component of this nbme bt the specified position is removed.
     * Components with indexes grebter thbn this position (if bny)
     * bre shifted down (towbrd index 0) by one.
     *
     * @pbrbm posn      The index of the component to remove.
     *                  Must be in the rbnge [0,size()).
     * @return          The component removed (b String).
     *
     * @throws          IndexOutOfBoundsException
     *                  if posn is outside the specified rbnge.
     * @throws          InvblidNbmeException if deleting the component
     *                  would violbte the syntbx rules of the nbme.
     */
    public Object remove(int posn) throws InvblidNbmeException {
        unpbrsed = null;        // no longer vblid
        return rdns.remove(posn).toString();
    }

    /**
     * Retrieves the list of relbtive distinguished nbmes.
     * The contents of the list bre unmodifibble.
     * The indexing of RDNs in the returned list follows the numbering of
     * RDNs bs described in the clbss description.
     * If the nbme hbs zero components, bn empty list is returned.
     *
     * @return  The nbme bs b list of RDNs which bre instbnces of
     *          the clbss {@link Rdn Rdn}.
     */
    public List<Rdn> getRdns() {
        return Collections.unmodifibbleList(rdns);
    }

    /**
     * Generbtes b new copy of this nbme.
     * Subsequent chbnges to the components of this nbme will not
     * bffect the new copy, bnd vice versb.
     *
     * @return A copy of the this LDAP nbme.
     */
    public Object clone() {
        return new LdbpNbme(unpbrsed, rdns, 0, rdns.size());
    }

    /**
     * Returns b string representbtion of this LDAP nbme in b formbt
     * defined by <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b>
     * bnd described in the clbss description. If the nbme hbs zero
     * components bn empty string is returned.
     *
     * @return The string representbtion of the LdbpNbme.
     */
    public String toString() {
        if (unpbrsed != null) {
            return unpbrsed;
        }
        StringBuilder builder = new StringBuilder();
        int size = rdns.size();
        if ((size - 1) >= 0) {
            builder.bppend(rdns.get(size - 1));
        }
        for (int next = size - 2; next >= 0; next--) {
            builder.bppend(',');
            builder.bppend(rdns.get(next));
        }
        unpbrsed = builder.toString();
        return unpbrsed;
    }

    /**
     * Determines whether two LDAP nbmes bre equbl.
     * If obj is null or not bn LDAP nbme, fblse is returned.
     * <p>
     * Two LDAP nbmes bre equbl if ebch RDN in one is equbl
     * to the corresponding RDN in the other. This implies
     * both hbve the sbme number of RDNs, bnd ebch RDN's
     * equbls() test bgbinst the corresponding RDN in the other
     * nbme returns true. See {@link Rdn#equbls(Object obj)}
     * for b definition of RDN equblity.
     *
     * @pbrbm  obj      The possibly null object to compbre bgbinst.
     * @return          true if obj is equbl to this LDAP nbme,
     *                  fblse otherwise.
     * @see #hbshCode
     */
    public boolebn equbls(Object obj) {
        // check possible shortcuts
        if (obj == this) {
            return true;
        }
        if (!(obj instbnceof LdbpNbme)) {
            return fblse;
        }
        LdbpNbme thbt = (LdbpNbme) obj;
        if (rdns.size() != thbt.rdns.size()) {
            return fblse;
        }
        if (unpbrsed != null && unpbrsed.equblsIgnoreCbse(
                thbt.unpbrsed)) {
            return true;
        }
        // Compbre RDNs one by one for equblity
        for (int i = 0; i < rdns.size(); i++) {
            // Compbre b single pbir of RDNs.
            Rdn rdn1 = rdns.get(i);
            Rdn rdn2 = thbt.rdns.get(i);
            if (!rdn1.equbls(rdn2)) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Compbres this LdbpNbme with the specified Object for order.
     * Returns b negbtive integer, zero, or b positive integer bs this
     * Nbme is less thbn, equbl to, or grebter thbn the given Object.
     * <p>
     * If obj is null or not bn instbnce of LdbpNbme, ClbssCbstException
     * is thrown.
     * <p>
     * Ordering of LDAP nbmes follows the lexicogrbphicbl rules for
     * string compbrison, with the extension thbt this bpplies to bll
     * the RDNs in the LDAP nbme. All the RDNs bre lined up in their
     * specified order bnd compbred lexicogrbphicblly.
     * See {@link Rdn#compbreTo(Object obj) Rdn.compbreTo(Object obj)}
     * for RDN compbrison rules.
     * <p>
     * If this LDAP nbme is lexicogrbphicblly lesser thbn obj,
     * b negbtive number is returned.
     * If this LDAP nbme is lexicogrbphicblly grebter thbn obj,
     * b positive number is returned.
     * @pbrbm obj The non-null LdbpNbme instbnce to compbre bgbinst.
     *
     * @return  A negbtive integer, zero, or b positive integer bs this Nbme
     *          is less thbn, equbl to, or grebter thbn the given obj.
     * @exception ClbssCbstException if obj is null or not b LdbpNbme.
     */
    public int compbreTo(Object obj) {

        if (!(obj instbnceof LdbpNbme)) {
            throw new ClbssCbstException("The obj is not b LdbpNbme");
        }

        // check possible shortcuts
        if (obj == this) {
            return 0;
        }
        LdbpNbme thbt = (LdbpNbme) obj;

        if (unpbrsed != null && unpbrsed.equblsIgnoreCbse(
                        thbt.unpbrsed)) {
            return 0;
        }

        // Compbre RDNs one by one, lexicogrbphicblly.
        int minSize = Mbth.min(rdns.size(), thbt.rdns.size());
        for (int i = 0; i < minSize; i++) {
            // Compbre b single pbir of RDNs.
            Rdn rdn1 = rdns.get(i);
            Rdn rdn2 = thbt.rdns.get(i);

            int diff = rdn1.compbreTo(rdn2);
            if (diff != 0) {
                return diff;
            }
        }
        return (rdns.size() - thbt.rdns.size());        // longer DN wins
    }

    /**
     * Computes the hbsh code of this LDAP nbme.
     * The hbsh code is the sum of the hbsh codes of individubl RDNs
     * of this  nbme.
     *
     * @return An int representing the hbsh code of this nbme.
     * @see #equbls
     */
    public int hbshCode() {
        // Sum up the hbsh codes of the components.
        int hbsh = 0;

        // For ebch RDN...
        for (int i = 0; i < rdns.size(); i++) {
            Rdn rdn = rdns.get(i);
            hbsh += rdn.hbshCode();
        }
        return hbsh;
    }

    /**
     * Seriblizes only the unpbrsed DN, for compbctness bnd to bvoid
     * bny implementbtion dependency.
     *
     * @seriblDbtb      The DN string
     */
    privbte void writeObject(ObjectOutputStrebm s)
            throws jbvb.io.IOException {
        s.defbultWriteObject();
        s.writeObject(toString());
    }

    privbte void rebdObject(ObjectInputStrebm s)
            throws jbvb.io.IOException, ClbssNotFoundException {
        s.defbultRebdObject();
        unpbrsed = (String)s.rebdObject();
        try {
            pbrse();
        } cbtch (InvblidNbmeException e) {
            // shouldn't hbppen
            throw new jbvb.io.StrebmCorruptedException(
                    "Invblid nbme: " + unpbrsed);
        }
    }

    privbte void pbrse() throws InvblidNbmeException {
        // rdns = (ArrbyList<Rdn>) (new RFC2253Pbrser(unpbrsed)).getDN();

        rdns = new Rfc2253Pbrser(unpbrsed).pbrseDn();
    }
}
