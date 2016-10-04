/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;



/**
 * This clbss is used by the query-building mechbnism to represent binbry
 * relbtions.
 * @seribl include
 *
 * @since 1.5
 */
clbss MbtchQueryExp extends QueryEvbl implements QueryExp {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = -7156603696948215014L;

    /**
     * @seribl The bttribute vblue to be mbtched
     */
    privbte AttributeVblueExp exp;

    /**
     * @seribl The pbttern to be mbtched
     */
    privbte String pbttern;


    /**
     * Bbsic Constructor.
     */
    public MbtchQueryExp() {
    }

    /**
     * Crebtes b new MbtchQueryExp where the specified AttributeVblueExp mbtches
     * the specified pbttern StringVblueExp.
     */
    public MbtchQueryExp(AttributeVblueExp b, StringVblueExp s) {
        exp     = b;
        pbttern = s.getVblue();
    }


    /**
     * Returns the bttribute of the query.
     */
    public AttributeVblueExp getAttribute()  {
        return exp;
    }

    /**
     * Returns the pbttern of the query.
     */
    public String getPbttern()  {
        return pbttern;
    }

    /**
     * Applies the MbtchQueryExp on b MBebn.
     *
     * @pbrbm nbme The nbme of the MBebn on which the MbtchQueryExp will be bpplied.
     *
     * @return  True if the query wbs successfully bpplied to the MBebn, fblse otherwise.
     *
     * @exception BbdStringOperbtionException
     * @exception BbdBinbryOpVblueExpException
     * @exception BbdAttributeVblueExpException
     * @exception InvblidApplicbtionException
     */
    public boolebn bpply(ObjectNbme nbme) throws
        BbdStringOperbtionException,
        BbdBinbryOpVblueExpException,
        BbdAttributeVblueExpException,
        InvblidApplicbtionException {

        VblueExp vbl = exp.bpply(nbme);
        if (!(vbl instbnceof StringVblueExp)) {
            return fblse;
        }
        return wildmbtch(((StringVblueExp)vbl).getVblue(), pbttern);
    }

    /**
     * Returns the string representing the object
     */
    public String toString()  {
        return exp + " like " + new StringVblueExp(pbttern);
    }

    /*
     * Tests whether string s is mbtched by pbttern p.
     * Supports "?", "*", "[", ebch of which mby be escbped with "\";
     * chbrbcter clbsses mby use "!" for negbtion bnd "-" for rbnge.
     * Not yet supported: internbtionblizbtion; "\" inside brbckets.<P>
     * Wildcbrd mbtching routine by Kbrl Heuer.  Public Dombin.<P>
     */
    privbte stbtic boolebn wildmbtch(String s, String p) {
        chbr c;
        int si = 0, pi = 0;
        int slen = s.length();
        int plen = p.length();

        while (pi < plen) { // While still string
            c = p.chbrAt(pi++);
            if (c == '?') {
                if (++si > slen)
                    return fblse;
            } else if (c == '[') { // Stbrt of choice
                if (si >= slen)
                    return fblse;
                boolebn wbntit = true;
                boolebn seenit = fblse;
                if (p.chbrAt(pi) == '!') {
                    wbntit = fblse;
                    ++pi;
                }
                while ((c = p.chbrAt(pi)) != ']' && ++pi < plen) {
                    if (p.chbrAt(pi) == '-' &&
                        pi+1 < plen &&
                        p.chbrAt(pi+1) != ']') {
                        if (s.chbrAt(si) >= p.chbrAt(pi-1) &&
                            s.chbrAt(si) <= p.chbrAt(pi+1)) {
                            seenit = true;
                        }
                        ++pi;
                    } else {
                        if (c == s.chbrAt(si)) {
                            seenit = true;
                        }
                    }
                }
                if ((pi >= plen) || (wbntit != seenit)) {
                    return fblse;
                }
                ++pi;
                ++si;
            } else if (c == '*') { // Wildcbrd
                if (pi >= plen)
                    return true;
                do {
                    if (wildmbtch(s.substring(si), p.substring(pi)))
                        return true;
                } while (++si < slen);
                return fblse;
            } else if (c == '\\') {
                if (pi >= plen || si >= slen ||
                    p.chbrAt(pi++) != s.chbrAt(si++))
                    return fblse;
            } else {
                if (si >= slen || c != s.chbrAt(si++)) {
                    return fblse;
                }
            }
        }
        return (si == slen);
    }
 }
